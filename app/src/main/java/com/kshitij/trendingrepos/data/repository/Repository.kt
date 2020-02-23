package com.kshitij.trendingrepos.data.repository

import com.kshitij.trendingrepos.data.model.TrendingRepositoryResponse
import io.reactivex.Single

interface Repository {
    fun getTrendingRepositories(): Single<List<TrendingRepositoryResponse>>
    fun getTrendingRepositoriesForceRefresh(): Single<List<TrendingRepositoryResponse>>
}