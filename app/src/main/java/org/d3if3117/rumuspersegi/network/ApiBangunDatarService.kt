package org.d3if3117.rumuspersegi.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import org.d3if3117.rumuspersegi.model.BangunDatar
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

private const val BASE_URL = "https://raw.githubusercontent.com/Putrimaulidza/assesment-2/master/"
private const val BASE_URL_IMG = "https://raw.githubusercontent.com/Putrimaulidza/assesment-2/master/img/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface ApiBangunDatarService {
    @GET("bangun-datar.json")
    suspend fun getResult(): List<BangunDatar>
}

object ApiBangunDatar {
    val service: ApiBangunDatarService by lazy {
        retrofit.create(ApiBangunDatarService::class.java)
    }
    fun getBangunDatarUrl(gambar: String): String {
        return "$BASE_URL_IMG$gambar"
    }
}

enum class ApiStatus { LOADING, SUCCESS, FAILED }