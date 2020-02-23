package com.kshitij.trendingrepos.di.modules

import com.kshitij.trendingrepos.data.repository.Repository
import com.kshitij.trendingrepos.data.repository.RepositoryImpl
import com.kshitij.trendingrepos.data.repository.remote.RemoteDataProvider
import com.kshitij.trendingrepos.data.repository.remote.RemoteDataProviderImpl
import com.kshitij.trendingrepos.data.services.ApiService
import dagger.Module
import dagger.Provides

@Module
class RepositoryModule {

    @Provides
    fun provideRemoteDataProvider(apiService: ApiService): RemoteDataProvider {
        return RemoteDataProviderImpl(apiService)
    }

    @Provides
    fun provideRepository(remoteDataProvider: RemoteDataProvider): Repository {
        return RepositoryImpl(remoteDataProvider)
    }

}