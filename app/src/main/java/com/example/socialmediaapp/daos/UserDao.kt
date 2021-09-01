package com.example.socialmediaapp.daos

import android.annotation.SuppressLint
import com.example.socialmediaapp.models.Users
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.auth.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class UserDao {
    val db = FirebaseFirestore.getInstance()
    val userCollection = db.collection("users")

    @SuppressLint("RestrictedApi")
    fun addUser(user : Users?){
        user?.let {
            GlobalScope.launch(Dispatchers.IO){
            userCollection.document(user.uid.toString()).set(it)
        }
        }
    }

    fun getUserbyId(uid : String): Task<DocumentSnapshot> {
        return userCollection.document(uid).get()
    }
}