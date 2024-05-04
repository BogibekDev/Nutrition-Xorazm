package dev.bogibek.nutritionxorazm.data.remote

import dev.bogibek.nutritionxorazm.models.AdviceResponse
import dev.bogibek.nutritionxorazm.models.FoodListResponse
import dev.bogibek.nutritionxorazm.models.History
import dev.bogibek.nutritionxorazm.models.HistoryModel
import dev.bogibek.nutritionxorazm.models.Responses
import dev.bogibek.nutritionxorazm.models.User
import dev.bogibek.nutritionxorazm.models.UserData
import dev.bogibek.nutritionxorazm.models.UserRequest
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface ApiService {

    @POST("personcheck/")
    fun personcheck(
        @Body user: UserRequest
    ): Call<Responses<User>>

    @POST("api/person/post/")
    fun createUser(
        @Body user: User
    ): Call<User>

    @GET("product/list/")
    fun getProducts(): Call<Responses<FoodListResponse>>

    @GET("weeklycaloriesget/{id}/")
    fun getAdvice(
        @Path("id") id: Long
    ): Call<AdviceResponse>

    @POST("history/post/{id}/")
    fun addHistory(
        @Path("id") id: Long,
        @Body addProduct: History
    ): Call<Responses<Any>>

    @PUT("api/person/getupdate/{id}/")
    fun updatePersonField(
        @Path("id") id: Long,
        @Body data: UserData
    ): Call<User>

    @GET("history/get/{id}/")
    fun getHistoryDate(@Path("id") id: Long): Call<HistoryModel>

    @GET("history/get/1/")
    fun getHistoryWeekly(): Call<HistoryModel>
}