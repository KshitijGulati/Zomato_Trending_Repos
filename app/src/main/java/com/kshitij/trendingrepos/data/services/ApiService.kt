package com.kshitij.trendingrepos.data.services

import com.kshitij.trendingrepos.data.model.TrendingRepositoryResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Header

interface ApiService {
    @GET("/repositories")
    fun getTrendingRepositories(@Header("Cache-Control") cacheControl: String? = null): Single<List<TrendingRepositoryResponse>>
}