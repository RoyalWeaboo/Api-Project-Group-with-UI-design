package com.example.api_project_group.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.api_project_group.model.RestponseDataFilmItem
import com.example.api_project_group.network.RetrofitFilm
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ViewModelFilm : ViewModel() {

    lateinit var liveDataFilm : MutableLiveData<List<RestponseDataFilmItem>>

    init {
        liveDataFilm = MutableLiveData()
//        addLiveDataCar = MutableLiveData()
//        updLDcar = MutableLiveData()
//        delCar = MutableLiveData()
    }
    fun getliveDataFilm() :MutableLiveData<List<RestponseDataFilmItem>>{
        return  liveDataFilm
    }

    fun callApiFilm(){
        RetrofitFilm.instance.getAllFilm()
            .enqueue(object : Callback<List<RestponseDataFilmItem>>{
                override fun onResponse(
                    call: Call<List<RestponseDataFilmItem>>,
                    response: Response<List<RestponseDataFilmItem>>
                ) {
                    if (response.isSuccessful){
                        liveDataFilm.postValue(response.body())
                    }else{
                        liveDataFilm.postValue(null)
                    }
                }

                override fun onFailure(call: Call<List<RestponseDataFilmItem>>, t: Throwable) {
                    liveDataFilm.postValue(null)
                }

            })

    }
}