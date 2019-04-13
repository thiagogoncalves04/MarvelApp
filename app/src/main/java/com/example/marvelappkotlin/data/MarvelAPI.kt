package com.example.marvelappkotlin.data

import com.example.marvelappkotlin.model.Response
import com.example.marvelappkotlin.util.Crypto
import com.google.gson.GsonBuilder
import io.reactivex.Observable
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import java.util.*

interface MarvelAPI {

    object DbConstants {
        val API_KEY = "d97020a743c92e542183a15420263360"
        val PRIVATE_KEY = "671558ebe670619c5b3e7e5cd7fd7abc66ee97a1"
    }

    @GET("characters")
    fun allCharacters(@Query("offset") offset: Int? = 0): Observable<Response>

    companion object {
        fun getService(): MarvelAPI {
            val logging = HttpLoggingInterceptor()
            logging.level = HttpLoggingInterceptor.Level.BODY

            val httpClient = OkHttpClient.Builder()
            httpClient.addInterceptor(logging)
            httpClient.addInterceptor { chain ->
                val original = chain.request()
                val originalHttpUrl = original.url()

                val ts = (Calendar.getInstance(TimeZone.getTimeZone("UTC")).timeInMillis / 1000L).toString()
                val url = originalHttpUrl.newBuilder()
                    .addQueryParameter("apikey", DbConstants.API_KEY)
                    .addQueryParameter("ts", ts)
                    .addQueryParameter("hash", Crypto.md5("$ts${DbConstants.PRIVATE_KEY}${DbConstants.API_KEY}"))
                    .build()

                chain.proceed(original.newBuilder().url(url).build())
            }
            val gson = GsonBuilder().setLenient().create()
            val retrofit = Retrofit.Builder()
                .baseUrl("http://gateway.marvel.com/v1/public/")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(httpClient.build())
                .build()

            return retrofit.create<MarvelAPI>(MarvelAPI::class.java)
        }
    }
}
