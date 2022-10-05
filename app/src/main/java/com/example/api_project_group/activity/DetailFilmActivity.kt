package com.example.api_project_group.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.api_project_group.R
import com.example.api_project_group.databinding.ActivityDetailFilmBinding
import com.example.api_project_group.model.RestponseDataFilmItem
import com.example.api_project_group.viewmodel.ViewModelFilm

class DetailFilmActivity : AppCompatActivity() {

    private lateinit var binding : ActivityDetailFilmBinding
    private lateinit var dataFilm: RestponseDataFilmItem

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailFilmBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var bun = intent.extras
        var idd = bun!!.getInt("okedetail")

        Log.d("idd", idd.toString())
        getDetailCar(idd)

    }

    fun getDetailCar(id : Int){
        val viewModel = ViewModelProvider(this).get(ViewModelFilm::class.java)
        viewModel.callDetailApiFilm(id)
        viewModel.getliveDataFilm().observe(this, Observer {

        })

    }


}