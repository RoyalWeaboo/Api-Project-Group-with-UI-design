package com.example.api_project_group.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.api_project_group.model.ResponseDataUserItem
import com.example.api_project_group.model.User
import com.example.api_project_group.network.RetrofitClientUser
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ViewModelUser : ViewModel() {
    lateinit var liveDataUser : MutableLiveData<List<ResponseDataUserItem>>
    lateinit var postLDUser : MutableLiveData<ResponseDataUserItem>

    init {
        liveDataUser = MutableLiveData()
        postLDUser = MutableLiveData()
    }

    fun ambilLiveDataUser() : MutableLiveData<List<ResponseDataUserItem>> {
        return liveDataUser
    }

    fun addLiveDataUser() : MutableLiveData<ResponseDataUserItem>{
        return postLDUser
    }

    fun callPostAPICar(name:String, username:String, password:String){
        RetrofitClientUser.instance.addUser(User(name, username, password)).enqueue(object :Callback<ResponseDataUserItem>{
            override fun onResponse(call: Call<ResponseDataUserItem>, response: Response<ResponseDataUserItem>) {
                if(response.isSuccessful) postLDUser.postValue(response.body())
            }
            override fun onFailure(call: Call<ResponseDataUserItem>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })
    }

    fun callAPIUser(){
        RetrofitClientUser.instance.getAllUser().enqueue(object  : Callback<List<ResponseDataUserItem>> {
            override fun onResponse(call: Call<List<ResponseDataUserItem>>, response: Response<List<ResponseDataUserItem>>) {
                if(response.isSuccessful){
                    liveDataUser.postValue(response.body())
                }
                else {
                }
            }
            override fun onFailure(call: Call<List<ResponseDataUserItem>>, t: Throwable) {

            }

        })
    }

    fun requestLogin(id:Int, name:String, username:String, password:String) {
        RetrofitClientUser.instance.putUser(id, User(name, username, password)).enqueue(object : Callback<List<ResponseDataUserItem>> {
            override fun onResponse(call: Call<List<ResponseDataUserItem>>, response: Response<List<ResponseDataUserItem>>) {
                liveDataUser.postValue(response.body())
            }

            override fun onFailure(call: Call<List<ResponseDataUserItem>>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })
    }
}