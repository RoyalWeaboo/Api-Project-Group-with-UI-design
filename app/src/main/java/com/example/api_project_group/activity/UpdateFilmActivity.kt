package com.example.api_project_group.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.api_project_group.databinding.ActivityUpdateFilmBinding
import com.example.api_project_group.viewmodel.ViewModelFilm

class UpdateFilmActivity : AppCompatActivity() {

    lateinit var binding : ActivityUpdateFilmBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUpdateFilmBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnUpdate.setOnClickListener {
            var fetId = intent.getIntExtra("update", 0)
            var filmName = binding.nameEditInput.text.toString()
            var filmDirector = binding.directorEditInput.text.toString()
            var filmPict = binding.pictureEditInput.text.toString()
            var filmDesc = binding.descEditInput.text.toString()


        }
    }

    fun updateDataFilm(id : Int, name : String, director : String,  img : String, desc : String) {
        var viewModel = ViewModelProvider(this).get(ViewModelFilm :: class.java)
//        viewModel.updateApiFilm(id, name, director, img, desc)
        viewModel
    }
}