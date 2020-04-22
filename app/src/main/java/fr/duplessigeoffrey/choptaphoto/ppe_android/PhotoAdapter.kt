package fr.duplessigeoffrey.choptaphoto.ppe_android


import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.Target
import kotlinx.android.synthetic.main.photo_row.view.*
import java.io.File
import java.util.concurrent.Executors


class PhotoViewHolder(v : View) : RecyclerView.ViewHolder(v){
    val titleTextView = v.photoTitleTextView
    val photoImageView = v.PhotoimageView
    val likeImageView = v.likeImageView
    val buttonShare = v.buttonShare
    val shareimageView = v.shareimageView
}
// on passe l'activité
class PhotoAdapter(val photos: List<Photo>, private val activity: AppCompatActivity): RecyclerView.Adapter<PhotoViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewHolder {
        // inflater permet d'instancier un layout
        val  inflater = LayoutInflater.from(parent.context)
        // inflate permet de générer une vue Android à partir d'un layout xml
        val itemRowView = inflater.inflate(R.layout.photo_row, parent, false)
        // on met la vue dans un nouveaux view holder
        val PhotoViewHolder = PhotoViewHolder(itemRowView)
        // return
        return PhotoViewHolder
    }

    override fun getItemCount(): Int {  // ou override fun getItemCount() = photo.count
        // on recupere les photos
        return photos.count()
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {
        Log.d("APP", "${photos[position]}")
        when(position % 4){
            0 -> holder.itemView.setBackgroundResource(R.color.colorCellColor1)
            1 -> holder.itemView.setBackgroundResource(R.color.colorCellColor2)
            2 -> holder.itemView.setBackgroundResource(R.color.colorCellColor3)
            3 -> holder.itemView.setBackgroundResource(R.color.colorOrange)
        }
        // charger le texte
        holder.titleTextView.text =
            activity.getString(R.string.photo_url_name) + photos[position].url
        // on ajoute une photo basic
        //  val imageUrl = "https://yostane.alwaysdata.net/" + photos[position].url;

        val imageUrl = "https://duplessigeoffrey.fr/api2/photos/" + photos[position].url;
        Glide.with(holder.photoImageView).load(imageUrl).into(holder.photoImageView)
        toogleLikeImage(position, holder)
        val executor = Executors.newSingleThreadExecutor()

        holder.likeImageView.setOnClickListener {
            photos[position].estAime = !photos[position].estAime
            toogleLikeImage(position, holder)

            executor.submit{
                val call = RetrofitHelper.getToogleLikeCall(photos[position].id)
                call.execute()
            }
        }
        // ouvrir une nouvelle view, lors du click
        holder.itemView.setOnClickListener {
            // crée un Intent, avec l'activité source et l'activité final
            val intent = Intent(activity, FullScreenPhotoActivity::class.java)
            //c'est lui qui permet de passer des parametre
            intent.putExtra("URL", imageUrl)
            // activité qui fait la demande, un apel vers le système android
            activity.startActivity(intent)
        }
        // quand je click sur mon image de partage
        holder.shareimageView.setOnClickListener {

            executor.submit {
                // Il telecharge l'image avec la taille original, et le mettre dans le chemin
                val imageFile = Glide.with(holder.photoImageView).load(imageUrl)
                    .downloadOnly(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL)
                    // return le chemin du fichier
                    .get()
                // renome le fichier
                val renameFile = File(
                    //crée un nouveaux chemin dans ce dossier
                    activity.getFilesDir(),
                    "share_images" + System.currentTimeMillis() + ".png"
                )
                //La fonction renameTo () est utilisée pour renommer le nom de chemin abstrait d'un
                //fichier en un nom de chemin donné. La fonction renvoie true si le fichier est renommé
                // sinon renvoie false
                imageFile.renameTo(renameFile)
                //
                val mediaUrl = MediaStore.Images.Media.insertImage(
                    activity.contentResolver,
                    renameFile.absolutePath,
                    photos[position].url,
                    "choptaphoto"
                )
                //supprime l'image
                renameFile.delete()
                // lance le traitement dans le Thread Principale
                activity.runOnUiThread {
                    val shareIntent = Intent().apply {
                        action = Intent.ACTION_SEND
                        putExtra(Intent.EXTRA_STREAM, Uri.parse(mediaUrl))
                        type = "image/png"
                    }
                    // chooser va démarrer une activité
                    val chooser = Intent.createChooser(shareIntent, activity.getString(R.string.share_photo_title))
                    activity.startActivity(chooser)
                }
            }
        }
    }

    private fun toogleLikeImage(position: Int,holder: PhotoViewHolder) {
        if (photos[position].estAime) {
            holder.likeImageView.setImageResource(R.drawable.ic_like)
        } else {
            holder.likeImageView.setImageResource(R.drawable.ic_not_like)
        }
    }

}