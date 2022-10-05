package com.example.api_project_group.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
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

        binding.mainToolbar.setTitle("List Film")

        setVmToAdapter()
    }

    private fun setVmToAdapter() {
        val viewModel =ViewModelProvider(this).get(ViewModelFilm::class.java)
        viewModel.getliveDataFilm().observe(this, Observer {
            filmAdapter = AdapterFilm(it)
            if (it != null) {
                binding.rvFilm.layoutManager = LinearLayoutManager(
                    this, LinearLayoutManager.VERTICAL, false
                )
                filmAdapter = AdapterFilm(it)
                binding.rvFilm.adapter = filmAdapter

                filmAdapter.onDelete={
                    viewModel.callDeleteFilm(it)
                    viewModel.getdeleteFilm().observe(this, Observer {
                        if (it == null){
                            Toast.makeText(this, "Deleted Film with id : $it", Toast.LENGTH_LONG).show()
                        }
                    })
                }
                filmAdapter.notifyDataSetChanged()
            }else{
                Toast.makeText(this, "There is no data to show", Toast.LENGTH_LONG).show()
            }

            filmAdapter.onDetail ={
                var getData = it
                var intent = Intent(this, DetailFIlmActivity::class.java)

                intent.putExtra("det", getData)
                startActivity(intent)
            }
        })
        viewModel.callApiFilm()
    }

}