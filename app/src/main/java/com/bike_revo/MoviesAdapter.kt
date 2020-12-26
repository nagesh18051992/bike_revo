package com.bike_revo

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.NonNull
import androidx.recyclerview.widget.RecyclerView


internal class MoviesAdapter(var mContext: Context, private var moviesList: List<MovieModel>) :
    RecyclerView.Adapter<MoviesAdapter.MyViewHolder>() {
    internal inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        var bike_image: ImageView
        var bike_name: TextView
        init {
            bike_image = view.findViewById(R.id.bike_image)
            bike_name = view.findViewById(R.id.bike_name)


            itemView.setOnClickListener {
                mContext.startActivity(Intent(mContext,  Detailactivity::class.java))
            }
        }

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