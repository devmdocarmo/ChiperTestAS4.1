package com.devmdocarmo.chipertestmovies.ui

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.devmdocarmo.chipertestmovies.R
import com.devmdocarmo.chipertestmovies.adapters.BestMoviesAdapter
import com.devmdocarmo.chipertestmovies.viewmodel.MainViewModel
import com.devmdocarmo.chipertestmovies.models.ListMovies.Result
import kotlin.properties.Delegates

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    var listofMovies: ArrayList<Result?> = ArrayList()
    private lateinit var viewModel: MainViewModel
    private lateinit var recyclerView: RecyclerView
    private lateinit var moviesAdapter: BestMoviesAdapter
    var isLoading by Delegates.notNull<Boolean>()
    var pageToView = 1
    var isOver = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = view.findViewById(R.id.list_movies)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        viewModel.loadAListofMovies(pageToView)
        moviesAdapter = BestMoviesAdapter(listofMovies, context){ movie ->
            activity?.supportFragmentManager?.beginTransaction()
                ?.replace(R.id.container, MovieDetailsFragment.newInstance(movie))
                ?.addToBackStack(null)
                ?.commit()
        }
        recyclerView.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = moviesAdapter
        }
        initViewModel()
        isLoading = false
        initScrollListener()
    }

    private fun initScrollListener() {
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val linearLayoutManager = recyclerView.layoutManager as LinearLayoutManager?
                if (!isLoading) {
                    if (linearLayoutManager != null &&
                        linearLayoutManager.findLastCompletelyVisibleItemPosition() == listofMovies.size- 1
                        && !isOver) {
                        viewModel.loadAListofMovies(pageNumber = pageToView)
                        isLoading = true
                    }
                }
            }
        })
    }

    private fun initViewModel() {
        viewModel.getMovies().observe(viewLifecycleOwner){
            Log.d("RECYCLER","LISTA ANTES DE CAMBIO\n" +
                    "tam: ${listofMovies.size}, tam comming: ${it.results.size}\n"+
                    "page: $pageToView")
            val volatil =ArrayList<Result>()
            val lastPositionOld = listofMovies.size
            volatil.addAll(it.results)
            isOver = it.results.isEmpty()
            listofMovies.addAll(volatil)
            pageToView++
            Log.d("RECYCLER","LISTA DESPUES DE CAMBIO\n" +
                    "tam: ${listofMovies.size}")
            moviesAdapter.notifyItemInserted(lastPositionOld)
            isLoading = false
        }
    }
}