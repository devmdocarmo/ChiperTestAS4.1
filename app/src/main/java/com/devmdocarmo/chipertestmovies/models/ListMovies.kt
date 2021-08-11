package com.devmdocarmo.chipertestmovies.models


import com.google.gson.annotations.SerializedName

data class ListMovies(
    @SerializedName("average_rating")
    val averageRating: Double,
    @SerializedName("backdrop_path")
    val backdropPath: String,
    @SerializedName("comments")
    val comments: Any?,
    @SerializedName("created_by")
    val createdBy: CreatedBy,
    @SerializedName("description")
    val description: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("iso_3166_1")
    val iso31661: String,
    @SerializedName("iso_639_1")
    val iso6391: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("object_ids")
    val objectIds: Any?,
    @SerializedName("page")
    val page: Int,
    @SerializedName("poster_path")
    val posterPath: String,
    @SerializedName("public")
    val publicData: Boolean,
    @SerializedName("results")
    val results: ArrayList<Result>,
    @SerializedName("revenue")
    val revenue: Long,
    @SerializedName("runtime")
    val runtime: Int,
    @SerializedName("sort_by")
    val sortBy: String,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("total_results")
    val totalResults: Int
) {

    data class CreatedBy(
        @SerializedName("gravatar_hash")
        val gravatarHash: String,
        @SerializedName("id")
        val id: String,
        @SerializedName("name")
        val name: String,
        @SerializedName("username")
        val username: String
    )

    data class Result(
        @SerializedName("adult")
        val adult: Boolean,
        @SerializedName("backdrop_path")
        val backdropPath: String,
        @SerializedName("genre_ids")
        val genreIds: List<Int>,
        @SerializedName("id")
        val id: Int,
        @SerializedName("media_type")
        val mediaType: String,
        @SerializedName("original_language")
        val originalLanguage: String,
        @SerializedName("original_title")
        val originalTitle: String,
        @SerializedName("overview")
        val overview: String,
        @SerializedName("popularity")
        val popularity: Double,
        @SerializedName("poster_path")
        val posterPath: String,
        @SerializedName("release_date")
        val releaseDate: String,
        @SerializedName("title")
        val title: String,
        @SerializedName("video")
        val video: Boolean,
        @SerializedName("vote_average")
        val voteAverage: Double,
        @SerializedName("vote_count")
        val voteCount: Int
    )
}