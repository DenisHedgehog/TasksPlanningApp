package org.hedgehog.tasksplanningapp.utils

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.database.FirebaseDatabase
import kotlinx.coroutines.experimental.async
import kotlinx.coroutines.experimental.runBlocking

val mAuth = FirebaseAuth.getInstance()!!
val database = FirebaseDatabase.getInstance()!!




fun getAllUsersTasks() = database