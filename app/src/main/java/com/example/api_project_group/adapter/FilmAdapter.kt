package com.example.api_project_group.adapter

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.api_project_group.activity.DetailFIlmActivity
import com.example.api_project_group.databinding.ItemFilmBinding
import com.example.api_project_group.model.RestponseDataFilmItem

class FilmAdapter(var listFilm: List<RestponseDataFilmItem>): RecyclerView.Adapter<FilmAdapter.ViewHolder>() {

    var onDelete : ((RestponseDataFilmItem)->Unit)? = null
    var onDetail : ((RestponseDataFilmItem)->Unit)? = null

    class ViewHolder(var binding : ItemFilmBinding): RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilmAdapter.ViewHolder {
        var view = ItemFilmBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return  ViewHolder(view)
    }

    override fun onBindViewHolder(holder: FilmAdapter.ViewHolder, @SuppressLint("RecyclerView") position: Int) {
        holder.binding.textItemName.text = listFilm!![position].name
        holder.binding.textItemDate.text = listFilm!![position].date
        Glide.with(holder.itemView.context).load(listFilm!![position].image).into(holder.binding.imageItemFilm)

        holder.binding.cardItemFilm.setOnClickListener{
            var bundle = Bundle()
            val intent = Intent(it.context, DetailFIlmActivity::class.java)
            bundle.putInt("detail", listFilm[position].id)
            intent.putExtras(bundle)
            it.context.startActivity(intent)
        }

    }

    override fun getItemCount(): Int {
        return listFilm.size
    }
}