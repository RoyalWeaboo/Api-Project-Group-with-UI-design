package com.example.api_project_group.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.api_project_group.model.RestponseDataFilmItem
import com.example.api_project_group.network.RetrofitFilm
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ViewModelFilm : ViewModel() {

    lateinit var liveDataFilm : MutableLiveData<List<RestponseDataFilmItem>>
    var deleteFilm : MutableLiveData<Int>

    init {
        liveDataFilm = MutableLiveData()
//        addLiveDataCar = MutableLiveData()
//        updLDcar = MutableLiveData()
        deleteFilm = MutableLiveData()
        callApiFilm()
    }
    fun getliveDataFilm() :MutableLiveData<List<RestponseDataFilmItem>>{
        return  liveDataFilm
    }

    fun getdeleteFilm(): MutableLiveData<Int> {
        return deleteFilm
    }

    fun callApiFilm(){
        GlobalScope.async {
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


    fun callDeleteFilm(id: Int) {
        GlobalScope.async {
            RetrofitFilm.instance.deleteFilm(id)
                .enqueue(object : Callback<Int> {
                    override fun onResponse(
                        call: Call<Int>,
                        response: Response<Int>
                    ) {
                        if (response.isSuccessful) {
                            deleteFilm.postValue(response.body())
                        } else {
                            deleteFilm.postValue(null)
                        }
                        callApiFilm()
                    }

                    override fun onFailure(call: Call<Int>, t: Throwable) {
                        deleteFilm.postValue(null)
                        callApiFilm()
                    }
                })
        }
    }

}