package fr.duplessigeoffrey.choptaphoto.ppe_android

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.android.synthetic.main.activity_photo_list.*
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.Executors

class PhotoListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_photo_list)
        Log.d("app", "========> Premier log")

        val code = intent.getStringExtra("CODE_EVENT")
        if (code != null){
            val photoCall = RetrofitHelper.getListPhotoCall(code)
            Log.d("APP", code)
            // thread, elle crée un nouveau thread, avec la possibilité d'instruction
            val executors = Executors.newSingleThreadExecutor()
            executors.submit {
                try {
                    //recupere nos photo
                    val photos = photoCall.execute().body()
                    if (photos != null){
                        Log.d("APP", photos.joinToString())
                        runOnUiThread {
                            // helloTextView.text = "${photos.body()}"
                            // adpater
                            recyclerView.adapter = PhotoAdapter(photos, this)
                            recyclerView.layoutManager = GridLayoutManager(this, 2)
                        }
                        Log.d("APP", "Size : ${photos.size}") // affiche 2
                        Log.d("APP", "Body : ${photos}") // affiche un tableau avec deux chose dedans
                    }
                }catch (e: Exception){
                    Log.e("APP", e.message)
                }
            }
        }

    }
}
