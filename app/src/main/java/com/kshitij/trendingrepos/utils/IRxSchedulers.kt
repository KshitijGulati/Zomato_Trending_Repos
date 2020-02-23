package com.kshitij.trendingrepos.utils

import io.reactivex.Scheduler



interface IRxSchedulers {
    fun main(): Scheduler
    fun io(): Scheduler
    fun compute(): Scheduler
}