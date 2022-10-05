package com.example.api_project_group.model


import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class RestponseDataFilmItem(
    @SerializedName("date")
    val date: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("director")
    val director: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("image")
    val image: String,
    @SerializedName("name")
    val name: String
): Serializable