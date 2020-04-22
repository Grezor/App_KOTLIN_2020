package fr.duplessigeoffrey.choptaphoto.ppe_android

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class RetrofitHelper {

    companion object {
            private val photoService: PhotoService = Retrofit.Builder()
            // le serveur api
             .baseUrl("https://duplessigeoffrey.fr/api2/")
            //.baseUrl("https://yostane.alwaysdata.net/")
            // .baseUrl("https://grezor.alwaysdata.net/")
            // apel dependance mochi
            .addConverterFactory(MoshiConverterFactory.create())
            .build().create(PhotoService::class.java)

        fun getListPhotoCall(codeEvent: String) = photoService.listPhotos(codeEvent)

        fun getToogleLikeCall(photoId: Int) =  photoService.toogleLike(photoId)
    }
}