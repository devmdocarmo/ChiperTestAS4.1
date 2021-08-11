package com.devmdocarmo.chipertestmovies.api

import com.devmdocarmo.chipertestmovies.models.ListMovies
import retrofit2.Call
import retrofit2.http.*

interface TMDBAPI {

    @GET("list/{list_id}")
    fun getListofMovies(@Path("list_id")list_id: Int,
                        @Query("page") page: Int,
                        @Query("api_key") apiKey: String): Call<ListMovies>

}