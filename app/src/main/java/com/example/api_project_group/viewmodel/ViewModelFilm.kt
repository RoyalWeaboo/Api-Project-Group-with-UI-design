package com.example.api_project_group.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.api_project_group.model.Film
import com.example.api_project_group.model.PutFilmResponse
import com.example.api_project_group.model.PutFilmResponseItem
import com.example.api_project_group.model.RestponseDataFilmItem
import com.example.api_project_group.network.RetrofitFilm
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ViewModelFilm : ViewModel() {

    lateinit var liveDataFilm : MutableLiveData<List<RestponseDataFilmItem>>
    lateinit var updateFilm : MutableLiveData<List<PutFilmResponseItem>>

    init {
        liveDataFilm = MutableLiveData()
        updateFilm = MutableLiveData()
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

    fun updateApiFilm(id : Int, name : String, image : String , director: String, description : String) {
        RetrofitFilm.instance.updateDataFilm(id, Film(name, image, director, description))
            .enqueue(object : Callback<List<PutFilmResponse>> {
                override fun onResponse(
                    call: Call<List<PutFilmResponse>>,
                    response: Response<List<PutFilmResponse>>
                ) {
                    if (response.isSuccessful) {
                        updateFilm.postValue(response.body())
                    } else {
                        updateFilm.postValue(null)
                    }
                }

                override fun onFailure(call: Call<List<PutFilmResponse>>, t: Throwable) {
                    updateFilm.postValue(null)
                }
            })
    }
}