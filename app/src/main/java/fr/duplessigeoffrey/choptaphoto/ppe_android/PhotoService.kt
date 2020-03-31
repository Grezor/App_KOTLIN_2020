package fr.duplessigeoffrey.choptaphoto.ppe_android

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*

interface PhotoService{
    @GET("photos.php")
    fun listPhotos(@Query("code") codeEvent: String): Call<List<Photo>>

    @POST("like.php")
    @FormUrlEncoded
    fun toogleLike(@Field("photo_id") photoId: Int): Call<ResponseBody>
}