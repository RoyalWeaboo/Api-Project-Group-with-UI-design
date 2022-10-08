package com.example.api_project_group.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.api_project_group.R
import com.example.api_project_group.adapter.AdapterFilm
import com.example.api_project_group.databinding.ActivityMainBinding
import com.example.api_project_group.viewmodel.ViewModelFilm
import com.google.android.material.snackbar.Snackbar
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var filmAdapter: AdapterFilm
    var currLang: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setVmToAdapter()

        binding.addButton.setOnClickListener {
            startActivity(Intent(this, AddFilmActivity::class.java))
        }

        binding.changeLang.setOnClickListener {
            getLocale()
            if (currLang == "in") {
                setLocale("en")
            } else {
                setLocale("in")
            }
        }
    }

    private fun setVmToAdapter() {
        val viewModel = ViewModelProvider(this).get(ViewModelFilm::class.java)

        viewModel.getliveDataFilm().observe(this, Observer {
            filmAdapter = AdapterFilm(it)

            viewModel.loading.observe(this, Observer {
                when (it) {
                    true -> binding.progressBarMain.visibility = View.VISIBLE
                    false -> binding.progressBarMain.visibility = View.GONE
                }
            })

            if (it != null) {
                binding.rvFilm.layoutManager = LinearLayoutManager(
                    this, LinearLayoutManager.VERTICAL, false
                )

                filmAdapter = AdapterFilm(it)
                binding.rvFilm.adapter = filmAdapter

                filmAdapter.onDelete = {
                    viewModel.callDeleteFilm(it)
                    viewModel.getdeleteFilm().observe(this, Observer {
                        viewModel.loading.observe(this, Observer {
                            when (it) {
                                true -> binding.progressBarMain.visibility = View.VISIBLE
                                false -> binding.progressBarMain.visibility = View.GONE
                            }
                            Toast.makeText(this, "Film Deleted", Toast.LENGTH_SHORT).show()
                        })
                    })
                }
                filmAdapter.notifyDataSetChanged()
            } else {
                Toast.makeText(this, "There is no data to show", Toast.LENGTH_SHORT).show()
            }

            filmAdapter.onDetail = {
                var getData = it
                var intent = Intent(this, DetailFilmActivity::class.java)

                intent.putExtra("det", getData)
                startActivity(intent)
            }

        })

        viewModel.callApiFilm()
    }

    fun setLocale(lang: String?) {
        val myLocale = Locale(lang)
        val res = resources
        val conf = res.configuration
        conf.locale = myLocale
        res.updateConfiguration(conf, res.displayMetrics)
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }

    fun getLocale() {
        val lang = resources.getConfiguration().locale.getLanguage();
        currLang = lang
    }


}