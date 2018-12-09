package com.dream.architecturecomponents.data.remote

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import io.reactivex.Observable
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieService {

    @GET("movie/top_rated")
    fun getTopRatedMovies(@Query("language") language: String = "fr-FR",
                          @Query("page") page: Int = 1
    ): Observable<MoviesListResponse>

    @GET("movie/{movie}")
    fun getMovie(@Path("movie") id: Int): Observable<MovieResponse>

    companion object {

        fun create(): MovieService {

            val client = OkHttpClient.Builder().addInterceptor { chain ->
                val request = chain.request()
                val newUrl = request.url().newBuilder().addQueryParameter("api_key", "f6ab71f10397e83586ed2ff2a109582f").build()
                chain.proceed(request.newBuilder().url(newUrl).build())
            }.build()


            val retrofit = Retrofit.Builder()
                .baseUrl("https://api.themoviedb.org/3/")
                .client(client)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            return retrofit.create(MovieService::class.java)
        }
    }
}