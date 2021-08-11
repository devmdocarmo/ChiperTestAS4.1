package com.devmdocarmo.chipertestmovies.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.devmdocarmo.chipertestmovies.BuildConfig
import com.devmdocarmo.chipertestmovies.R
import com.devmdocarmo.chipertestmovies.models.ListMovies.Result
import com.squareup.picasso.Picasso

class BestMoviesAdapter(var listofMovies: ArrayList<Result?>, val context: Context?, val callback : (movie: Result?) -> Unit): RecyclerView.Adapter<BestMoviesAdapter.MovieHolder>() {

    inner class MovieHolder(view: View): RecyclerView.ViewHolder(view) {
        var image = view.findViewById<ImageView>(R.id.image_movie)
        var title = view.findViewById<TextView>(R.id.text_movie_title)
        var release = view.findViewById<TextView>(R.id.text_movie_release)
        var language = view.findViewById<TextView>(R.id.text_movie_language)
        var average = view.findViewById<TextView>(R.id.text_movie_average)
        fun bindView(element: Result){
            Picasso.get().load(BuildConfig.IMAGE_URL+element.posterPath).into(image)
            title.text = element.originalTitle
            release.text = context?.getString(R.string.released_on, element.releaseDate)
            language.text = context?.getString(R.string.language, element.originalLanguage)
            average.text = context?.getString(R.string.average_vote, element.voteAverage)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieHolder {
        return MovieHolder(LayoutInflater.from(parent.context)
            .inflate(R.layout.item_movies, parent, false))
    }

    override fun onBindViewHolder(holder: MovieHolder, position: Int) {
        listofMovies[position]?.let { holder.bindView(it) }
        holder.itemView.setOnClickListener {
            callback.invoke(listofMovies[position])
        }
    }

    override fun getItemCount(): Int= listofMovies.size
}