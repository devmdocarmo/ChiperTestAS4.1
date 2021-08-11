package com.devmdocarmo.chipertestmovies.viewmodel

import org.junit.Test

import org.junit.Assert.*
import org.junit.Before

class MainViewModelTest {
    private lateinit var mViewModel: MainViewModel
    @Before
    fun setUp(){
        mViewModel= MainViewModel()
    }

    @Test
    fun modelNotNull(){
        assertNotNull(mViewModel)
    }

    @Test
    fun getMovies() {
        assertNotNull(mViewModel.getMovies())
    }

}