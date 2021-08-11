package com.devmdocarmo.chipertestmovies.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.devmdocarmo.chipertestmovies.BuildConfig
import com.devmdocarmo.chipertestmovies.R
import com.devmdocarmo.chipertestmovies.models.ListMovies
import com.google.gson.Gson
import com.squareup.picasso.Picasso


class MovieDetailsFragment : Fragment() {
    private var movie: ListMovies.Result? = null
    private var image: ImageView? = null
    private var title: TextView? = null
    private var release: TextView? = null
    private var language: TextView? = null
    private var average: TextView? = null
    private var count: TextView? = null
    private var popularity: TextView? = null
    private var overview: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            movie = Gson().fromJson(it.getString(ARG_PARAM1), ListMovies.Result::class.java)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movie_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        image = view.findViewById(R.id.image_movie)
        title = view.findViewById(R.id.text_movie_title)
        release = view.findViewById(R.id.text_movie_release)
        language = view.findViewById(R.id.text_movie_language)
        average = view.findViewById(R.id.text_movie_average)
        count = view.findViewById(R.id.text_movie_votes)
        popularity = view.findViewById(R.id.text_movie_popularity)
        overview = view.findViewById(R.id.text_movie_overview)

        Picasso.get().load(BuildConfig.IMAGE_URL+movie?.posterPath).into(image)
        title?.text = movie?.originalTitle
        release?.text = context?.getString(R.string.released_on, movie?.releaseDate)
        language?.text = context?.getString(R.string.language, movie?.originalLanguage)
        average?.text = context?.getString(R.string.average_vote, movie?.voteAverage)
        count?.text = context?.getString(R.string.vote_count, movie?.voteCount)
        popularity?.text = context?.getString(R.string.movie_popularity, movie?.popularity)
        overview?.text = context?.getString(R.string.movie_overview, movie?.overview)
    }

    companion object {
        private const val ARG_PARAM1 = "MOVIE"

        @JvmStatic
        fun newInstance(movieToPass: ListMovies.Result?) =
            MovieDetailsFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, Gson().toJson(movieToPass))
                }
            }
    }
}