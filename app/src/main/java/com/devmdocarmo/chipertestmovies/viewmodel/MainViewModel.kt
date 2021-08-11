package com.devmdocarmo.chipertestmovies.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.devmdocarmo.chipertestmovies.BuildConfig
import com.devmdocarmo.chipertestmovies.api.TMDBAPI
import com.devmdocarmo.chipertestmovies.models.ListMovies
import com.devmdocarmo.chipertestmovies.provider.RetrofitService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.create

class MainViewModel : ViewModel() {
    //instance Retrofit
    private val retrofit = RetrofitService().instance.create<TMDBAPI>()
    private var page: Int= 1
    //instance LiveData
    private val listMovies: MutableLiveData<ListMovies> by lazy {
        MutableLiveData<ListMovies>()
    }

    fun getMovies(): LiveData<ListMovies> {
        //get LiveData values
        return listMovies
    }

    fun loadAListofMovies(pageNumber: Int) {
        // Do an asynchronous operation to fetch movies.
        retrofit.getListofMovies(10, page = pageNumber, apiKey = BuildConfig.ACCESS_TOKEN)
            .enqueue(object : Callback<ListMovies> {
                override fun onResponse(call: Call<ListMovies>, response: Response<ListMovies>) {
                    if(response.isSuccessful) {
                        Log.d("RESPONSE", response.body().toString())
                        listMovies.value?.results?.clear()
                        listMovies.value = response.body()
                    }else{
                        Log.e("RESPONSE", response.errorBody().toString())
                    }
                }

                override fun onFailure(call: Call<ListMovies>, t: Throwable) {
                    Log.e("RESPONSE", t.message.toString())
                }

            }
        )
    }
}