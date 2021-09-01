package com.example.socialmediaapp.daos

import com.example.socialmediaapp.models.Post
import com.example.socialmediaapp.models.Users
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.auth.User
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class PostDao {
    val db = FirebaseFirestore.getInstance()
    val postCollection = db.collection("posts")
    val auth = Firebase.auth

    fun addPost(text : String){
        val currentUserId = auth.currentUser!!.uid
        GlobalScope.launch {
            val userDao =  UserDao()
            val user = userDao.getUserbyId(currentUserId).await().toObject(Users::class.java)!!
            val post = Post(text,user)
            postCollection.document().set(post)
        }


    }
}