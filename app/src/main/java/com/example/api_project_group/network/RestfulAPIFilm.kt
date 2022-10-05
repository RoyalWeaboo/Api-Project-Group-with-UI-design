package com.example.api_project_group.network

import com.example.api_project_group.model.RestponseDataFilmItem
import retrofit2.Call
import retrofit2.http.GET


interface RestfulAPIFilm {

    @GET("film")
    fun getAllFilm() : Call<List<RestponseDataFilmItem>>
}