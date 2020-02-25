package fr.duplessigeoffrey.choptaphoto.ppe_android

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.photo_row.view.*

class PhotoViewHolder(v : View) : RecyclerView.ViewHolder(v){
    val titleTextView = v.photoTitleTextView
    val photoImageView = v.PhotoimageView
}

class PhotoAdapter(val photos: List<Photo>): RecyclerView.Adapter<PhotoViewHolder>() {
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
        // on recupere les photo
        return photos.count()
    }

    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {
        //
        when(position % 3){
            0 -> holder.itemView.setBackgroundColor(Color.parseColor("#f99999"))
            1 -> holder.itemView.setBackgroundColor(Color.parseColor("#99ff99"))
            2 -> holder.itemView.setBackgroundColor(Color.parseColor("#9999ff"))
        }
        holder.titleTextView.text = "Photo : ${photos[position].id}"
        // on ajoute une photo basic
        val imageUrl = "https://cdn.shopify.com/s/files/1/1087/6636/products/Captain-America-Icon_01_Top-View_600x600.png?v=1558444338"
        Glide.with(holder.photoImageView).load(imageUrl).into(holder.photoImageView)
    }
}