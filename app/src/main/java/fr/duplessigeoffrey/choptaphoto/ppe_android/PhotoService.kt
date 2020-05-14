package fr.duplessigeoffrey.choptaphoto.ppe_android

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*

interface PhotoService{
    @GET("photos.php")
     fun listPhotos(@Query("code") codeEvent: String): Call<List<Photo>>

    //@POST("like.php")
    //@FormUrlEncoded
    //fun toogleLike(@Field("photo_id") photoId: Int): Call<ResponseBody>

   // @GET("photos_event.php")
   // fun listPhotos(@Query("code") codeEvent: String): Call<List<Photo>>

   // @POST("toogle_like.php")
   // @FormUrlEncoded
  //  fun toogleLike(@Field("id_photo") photoId: Int): Call<ResponseBody>

    @POST("like.php")
    @FormUrlEncoded
    fun toogleLike(
        @Field("photo_id") photoId: Int,
        @Field("token") token: String
    ): Call<ResponseBody>

    @POST("login_mobile.php")
    @FormUrlEncoded
    fun login(
        @Field("nickname") login: String,
        @Field("password") password: String
    ): Call<ResponseBody>

    @POST("logout_mobile.php")
    @FormUrlEncoded
    fun logout(@Field("token") token: String)
}