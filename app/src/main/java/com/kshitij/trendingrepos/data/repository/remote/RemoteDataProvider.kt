package com.kshitij.trendingrepos.data.repository.remote

import com.kshitij.trendingrepos.data.model.TrendingRepositoryResponse
import io.reactivex.Single

interface RemoteDataProvider {
    fun getTrendingRepositories(forceRefresh: Boolean = false): Single<List<TrendingRepositoryResponse>>
}