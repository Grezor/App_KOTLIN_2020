package fr.duplessigeoffrey.choptaphoto.ppe_android

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

class Photo(val id:Int, val url: String)
// ajouter
public interface PhotoService{
    // nom de notre verbes
    @GET("photos.php")
    fun listPhotos(@Query("code") codeEvent: String): Call<List<Photo>>
}
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.d("app", "========> Premier log")
        //ajouter
        val retrofit = Retrofit.Builder()
            // le serveur api
            .baseUrl("https://grezor.alwaysdata.net/")
            .addConverterFactory(MoshiConverterFactory.create())
            .build()


        // instensier apel api
        val photoService = retrofit.create(PhotoService::class.java)
        // instancie l'apel de photo.php avec le code SONA
        val photoCall = photoService.listPhotos("SONA")
        //recupere nos photo
        val photos = photoCall.execute()
        Log.d("APP", "${photos.body()?.size}")
    }
}
