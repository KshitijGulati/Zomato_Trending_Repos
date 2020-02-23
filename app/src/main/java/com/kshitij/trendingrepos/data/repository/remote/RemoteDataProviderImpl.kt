package com.kshitij.trendingrepos.data.repository.remote

import com.kshitij.trendingrepos.data.model.TrendingRepositoryResponse
import com.kshitij.trendingrepos.data.services.ApiService
import io.reactivex.Single
import javax.inject.Inject

class RemoteDataProviderImpl @Inject constructor(private val apiService: ApiService) :
    RemoteDataProvider {

    override fun getTrendingRepositories(forceRefresh: Boolean): Single<List<TrendingRepositoryResponse>> {
        return if (forceRefresh) apiService.getTrendingRepositories("no-cache")
        else apiService.getTrendingRepositories()
    }
}