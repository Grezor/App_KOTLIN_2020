package fr.duplessigeoffrey.choptaphoto.ppe_android

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_full_screen_photo.*
import kotlin.math.log10

class FullScreenPhotoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_full_screen_photo)
        // recupere l'url
        val imageUrl = intent.getStringExtra("URL")
        //on affiche l'url
        Log.d("APP", imageUrl)
        // il prend l'activité, et charge image de l'url puis il met l'image dans le photo wiew
        // initialise avec with, 2 récuperer l'image, et la met dans une vue
        Glide.with(this).load(imageUrl).into(photo_view);
        // photo_view.setImageResource(R.mipmap.ic_launcher)
    }
}
