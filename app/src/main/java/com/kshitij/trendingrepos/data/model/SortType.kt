package com.kshitij.trendingrepos.data.model

sealed class SortType {
    object SortByName : SortType()
    object SortByStars : SortType()
}