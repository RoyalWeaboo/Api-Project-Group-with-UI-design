package com.example.api_project_group.network

import com.example.api_project_group.model.Film
import com.example.api_project_group.model.PutFilmResponseItem
import com.example.api_project_group.model.RestponseDataFilmItem
import retrofit2.Call
import retrofit2.http.*


interface RestfulAPIFilm {

    @GET("film")
    fun getAllFilm() : Call<List<RestponseDataFilmItem>>

    @PUT("film/{id}")
    fun updateDataFilm(@Path("id") id : Int, @Body request : Film) : Call<List<PutFilmResponseItem>>
}