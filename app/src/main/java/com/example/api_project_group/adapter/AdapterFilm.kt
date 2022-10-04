package com.example.api_project_group.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.api_project_group.databinding.ItemFilmBinding
import com.example.api_project_group.model.RestponseDataFilmItem

class AdapterFilm (var listFilm : List<RestponseDataFilmItem>): RecyclerView.Adapter<AdapterFilm.ViewHolder>() {

    var onDelete : ((RestponseDataFilmItem)->Unit)? = null
    var onEdit : ((RestponseDataFilmItem)->Unit)? = null
    var onDetail : ((RestponseDataFilmItem)->Unit)? = null


    class ViewHolder(var binding : ItemFilmBinding): RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var view = ItemFilmBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return  ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.nameFilm.text = listFilm!![position].name
        holder.binding.directorFilm.text = listFilm!![position].director
        holder.binding.dateFilm.text = listFilm!![position].date
        Glide.with(holder.itemView.context).load(listFilm!![position].image).into(holder.binding.imgFilm)
    }

    override fun getItemCount(): Int {
        return listFilm.size
    }

}