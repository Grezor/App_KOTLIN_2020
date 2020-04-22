package fr.duplessigeoffrey.choptaphoto.ppe_android

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.GlideBuilder
import com.bumptech.glide.load.DecodeFormat
import com.bumptech.glide.module.AppGlideModule
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.photo_row.view.*
import java.util.concurrent.Executors


class PhotoViewHolder(v : View) : RecyclerView.ViewHolder(v){
    val titleTextView = v.photoTitleTextView
    val photoImageView = v.PhotoimageView
    val likeImageView = v.likeImageView
    val buttonShare = v.buttonShare
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


    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {
        Log.d("APP", "${photos[position]}")
        when(position % 3){
            0 -> holder.itemView.setBackgroundResource(R.color.colorCellColor1)
            1 -> holder.itemView.setBackgroundResource(R.color.colorCellColor2)
            2 -> holder.itemView.setBackgroundResource(R.color.colorCellColor3)
        }
        // charger le texte
        holder.titleTextView.text =
            activity.getString(R.string.photo_url_name) + photos[position].url
        // on ajoute une photo basic
       //  val imageUrl = "https://yostane.alwaysdata.net/" + photos[position].url;
        val imageUrl = "https://duplessigeoffrey.fr/api2/photos/" + photos[position].url;
        Glide.with(holder.photoImageView).load(imageUrl).into(holder.photoImageView)

        toogleLikeImage(position, holder)

        holder.likeImageView.setOnClickListener {
            photos[position].estAime = !photos[position].estAime
            toogleLikeImage(position, holder)
            val executor = Executors.newSingleThreadExecutor()
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

        //holder.buttonShare.setOnClickListener {
        //    sharePicture()
        // }
    }

    private fun toogleLikeImage(
        position: Int,
        holder: PhotoViewHolder
    ) {
        if (photos[position].estAime) {
            holder.likeImageView.setImageResource(R.drawable.ic_like)
        } else {
            holder.likeImageView.setImageResource(R.drawable.ic_not_like)
        }
    }
    //fun sharePicture() {
    // partager les photos

        // val sendIntent = Intent()
        // sendIntent.action = Intent.ACTION_SEND
        // sendIntent.putExtra(
        //     Intent.EXTRA_STREAM, "http://duplessigeoffrey.fr/api2/photos/10.png"
        //  )
        // sendIntent.type = "image/png"
        // activity.startActivity(sendIntent)

   // }

}