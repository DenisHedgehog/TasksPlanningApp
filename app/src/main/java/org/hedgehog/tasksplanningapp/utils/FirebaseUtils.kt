package org.hedgehog.tasksplanningapp.utils

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

val mAuth = FirebaseAuth.getInstance()!!
val database = FirebaseDatabase.getInstance()!!

fun getAllUsersTasks() = database