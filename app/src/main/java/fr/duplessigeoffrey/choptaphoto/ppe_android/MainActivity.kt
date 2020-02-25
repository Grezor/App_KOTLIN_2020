package fr.duplessigeoffrey.choptaphoto.ppe_android

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import java.util.concurrent.Executors
import kotlin.math.log

class Photo(val id:Int, val url: String){
    // affiche le resultat plus proprement
    override fun toString(): String {
        return "identifiant: $id ===== url: $url" // [identifiant: 1 ===== url: 1.png, identifiant: 2 ===== url: 2.png]
    }
}
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
            // apel dependance mochi
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
        // instensier apel api
        val photoService = retrofit.create(PhotoService::class.java)
        // instancie l'apel de photo.php avec le code SONA
        val photoCall = photoService.listPhotos("SONA")

        //helloTextView.text = "un truc dedans"

        // thread, elle crée un nouveau thread, avec la possibilité d'instruction
        val executors = Executors.newSingleThreadExecutor()
        executors.submit {
            try {
                //recupere nos photo
                val photos = photoCall.execute()
                Log.d("APP", "Size : ${photos.body()?.size}") // affiche 2
                Log.d("APP", "Body : ${photos.body()}") // affiche un tableau avec deux chose dedans
                runOnUiThread {
                   // helloTextView.text = "${photos.body()}"
                }
            }catch (e: Exception){
                Log.e("APP", e.message)
            }
        }
    }
}
