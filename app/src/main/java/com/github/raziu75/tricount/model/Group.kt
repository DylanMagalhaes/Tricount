package com.github.raziu75.tricount.model

data class Group (
    val title: String,
    val description: String,
    val numberUser: Int,
    var listUser: List<User> = listOf()
)

