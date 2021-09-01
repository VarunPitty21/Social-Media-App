package com.example.socialmediaapp.models

data class Post (
    val text : String = "",
    val createdby : Users = Users(),
    val Likedby : ArrayList<String> = ArrayList())