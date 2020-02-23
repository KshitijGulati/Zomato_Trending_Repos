package com.kshitij.trendingrepos.di.components

import com.kshitij.trendingrepos.di.modules.ApplicationModule
import com.kshitij.trendingrepos.di.modules.NetworkModule
import com.kshitij.trendingrepos.di.modules.RepositoryModule
import com.kshitij.trendingrepos.di.modules.ViewModelModule
import com.kshitij.trendingrepos.ui.homescreen.HomeFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class, NetworkModule::class, RepositoryModule::class, ViewModelModule::class])
interface ApplicationComponent {
    fun inject(target: HomeFragment)
}