package fr.duplessigeoffrey.choptaphoto.ppe_android

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
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
    }
}