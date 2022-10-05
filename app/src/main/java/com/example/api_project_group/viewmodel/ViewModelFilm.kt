package com.example.api_project_group.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.api_project_group.model.Film
import com.example.api_project_group.model.PutFilmResponse
import com.example.api_project_group.model.RestponseDataFilmItem
import com.example.api_project_group.network.RetrofitFilm
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ViewModelFilm : ViewModel() {

    var liveDataFilm : MutableLiveData<List<RestponseDataFilmItem>>
    var deleteFilm : MutableLiveData<Int>
    var updFilm : MutableLiveData<List<PutFilmResponse>>
    lateinit var addFilm : MutableLiveData<RestponseDataFilmItem>
    var loading = MutableLiveData<Boolean>()

    init {
        liveDataFilm = MutableLiveData()
        addFilm = MutableLiveData()
        updFilm = MutableLiveData()
        deleteFilm = MutableLiveData()
        callApiFilm()
    }
    fun getliveDataFilm() :MutableLiveData<List<RestponseDataFilmItem>>{
        return  liveDataFilm
    }

    fun getdeleteFilm(): MutableLiveData<Int> {
        return deleteFilm
    }

    fun postDataFilm() : MutableLiveData<RestponseDataFilmItem>{
        return addFilm
    }

    fun updateDataFilm() : MutableLiveData<List<PutFilmResponse>> {
        return updFilm
    }

    fun callApiFilm(){
        GlobalScope.async {
            loading.postValue(true)
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
                        loading.postValue(false)
                    }

                    override fun onFailure(call: Call<List<RestponseDataFilmItem>>, t: Throwable) {
                        liveDataFilm.postValue(null)
                        loading.postValue(false)
                    }

                })
        }
    }

    fun callDetailApiFilm(id : Int){
        RetrofitFilm.instance.getDetailFilm(id)
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

    fun callDeleteFilm(id: Int) {
        GlobalScope.async {
            loading.postValue(true)
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
                        loading.postValue(false)
                    }

                    override fun onFailure(call: Call<Int>, t: Throwable) {
                        deleteFilm.postValue(null)
                        callApiFilm()
                        loading.postValue(false)
                    }
                })
        }
    }

    fun postApiCar(name : String, director : String, image : String, description : String){
        RetrofitFilm.instance.postFilm(Film(name, director, image, description ))
            .enqueue(object : Callback<RestponseDataFilmItem>{
                override fun onResponse(
                    call: Call<RestponseDataFilmItem>,
                    response: Response<RestponseDataFilmItem>
                ) {
                    if (response.isSuccessful){
                        addFilm.postValue(response.body())
                    }else{
                        addFilm.postValue(null)
                    }
                }

                override fun onFailure(call: Call<RestponseDataFilmItem>, t: Throwable) {
                    addFilm.postValue(null)
                }

            })
    }

    fun updateApiFilm(id: Int, name : String, director : String, image : String, description : String) {
        RetrofitFilm.instance.updateFilm(id, Film(name, director, image, description))
            .enqueue(object : Callback<List<PutFilmResponse>> {
                override fun onResponse(
                    call: Call<List<PutFilmResponse>>,
                    response: Response<List<PutFilmResponse>>
                ) {
                    if (response.isSuccessful) {
                        updFilm.postValue(response.body())
                    } else {
                        updFilm.postValue(null)
                    }
                }

                override fun onFailure(call: Call<List<PutFilmResponse>>, t: Throwable) {
                    updFilm.postValue(null)
                }
            })
    }

}