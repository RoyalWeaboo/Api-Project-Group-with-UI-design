package com.example.api_project_group.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.api_project_group.adapter.AdapterFilm
import com.example.api_project_group.databinding.ActivityMainBinding
import com.example.api_project_group.viewmodel.ViewModelFilm

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    private lateinit var filmAdapter: AdapterFilm

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setVmToAdapter()
    }

    private fun setVmToAdapter() {
        val viewModel =ViewModelProvider(this).get(ViewModelFilm::class.java)
        viewModel.callApiFilm()
        viewModel.getliveDataFilm().observe(this, Observer {
            filmAdapter = AdapterFilm(it)
            if (it != null){
                binding.rvFilm.layoutManager = LinearLayoutManager(
                    this, LinearLayoutManager.VERTICAL, false
                )
                binding.rvFilm.adapter = AdapterFilm(it)

            }

            filmAdapter.onDetail ={
                var getData = it
                var intent = Intent(this, DetailFIlmActivity::class.java)

                intent.putExtra("det", getData)
                startActivity(intent)
            }
        })
    }

}