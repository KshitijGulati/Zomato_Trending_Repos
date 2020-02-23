package com.kshitij.trendingrepos.data.repository

import com.kshitij.trendingrepos.data.model.TrendingRepositoryResponse
import com.kshitij.trendingrepos.data.repository.remote.RemoteDataProvider
import io.reactivex.Single
import javax.inject.Inject

class RepositoryImpl @Inject constructor(private val remoteDataProvider: RemoteDataProvider) :
    Repository {
    override fun getTrendingRepositoriesForceRefresh(): Single<List<TrendingRepositoryResponse>> {
        return remoteDataProvider.getTrendingRepositories(true)
    }

    override fun getTrendingRepositories(): Single<List<TrendingRepositoryResponse>> {
        return remoteDataProvider.getTrendingRepositories()
    }
}