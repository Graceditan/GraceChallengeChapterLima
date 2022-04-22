package binar.and.lima.challengechapterlima.network

import binar.and.lima.challengechapterlima.model.GetAllFilmResponseItem
import binar.and.lima.challengechapterlima.model.GetAllUserResponseItem
import binar.and.lima.challengechapterlima.model.LoginResponse
import binar.and.lima.challengechapterlima.model.RegisterResponse
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {

    @GET("apifilm.php")
    fun getAllFilm() : Call<List<GetAllFilmResponseItem>>

    @GET("apiuser.php")
    fun getAllUser(): Call<List<GetAllUserResponseItem>>

    @POST("updateuser.php")
    @FormUrlEncoded
    fun updateUser(
        @Field("id") id: Int,
        @Field("username") username: String,
        @Field("complete_name") completename: String,
        @Field("address") address: String,
        @Field("dateofbirth") dateofbirth: String,
    ): Call<List<GetAllUserResponseItem>>


    @POST("register.php/")
    @FormUrlEncoded
    fun register(
        @Field("username") username: String,
        @Field("email") email: String,
        @Field("password") password: String,

        ): Call<RegisterResponse>

    @POST("login.php")
    @FormUrlEncoded
    fun login(
        @Field("email") email: String,
        @Field("password") password: String
    ): Call<LoginResponse>
}