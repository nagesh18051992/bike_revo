package com.bike_revo

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.NonNull
import androidx.recyclerview.widget.RecyclerView





internal class MoviesAdapter(private var moviesList: List<MovieModel>) :
    RecyclerView.Adapter<MoviesAdapter.MyViewHolder>() {
    internal inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var bike_name: TextView = view.findViewById(R.id.bike_name)
        var bike_image: ImageView = view.findViewById(R.id.bike_image)
    }
    @NonNull
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.movie_list, parent, false)
        return MyViewHolder(itemView)
    }
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val movie = moviesList[position]
        holder.bike_name.text = movie.getTitle()
        holder.bike_image.setImageResource(movie.getImageview()!!)
    }
    override fun getItemCount(): Int {
        return moviesList.size
    }
}