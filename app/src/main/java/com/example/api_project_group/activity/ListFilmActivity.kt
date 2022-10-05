package com.example.api_project_group.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.api_project_group.R
import com.example.api_project_group.adapter.FilmAdapter
import com.example.api_project_group.databinding.ActivityListFilmBinding
import com.example.api_project_group.model.RestponseDataFilmItem
import com.example.api_project_group.network.RetrofitFilm
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit

class ListFilmActivity : AppCompatActivity() {

    lateinit var binding : ActivityListFilmBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_film)

        binding = ActivityListFilmBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getDataFilm()
    }

    fun getDataFilm(){
        RetrofitFilm.instance.getAllFilm()
            .enqueue(object : Callback<List<RestponseDataFilmItem>>{
                override fun onResponse(
                    call: Call<List<RestponseDataFilmItem>>,
                    response: Response<List<RestponseDataFilmItem>>
                ) {
                    if (response.isSuccessful){
                        var dataFilm = response.body()
                        binding.rvFilm.layoutManager = LinearLayoutManager(this@ListFilmActivity, LinearLayoutManager.VERTICAL, false)
                        binding.rvFilm.adapter = FilmAdapter(dataFilm!!)
                    }else{
                        Toast.makeText(this@ListFilmActivity, "Failed to Load Data", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<List<RestponseDataFilmItem>>, t: Throwable) {
                    Toast.makeText(this@ListFilmActivity, "Something Wrong", Toast.LENGTH_SHORT).show()
                }

            })
    }
}