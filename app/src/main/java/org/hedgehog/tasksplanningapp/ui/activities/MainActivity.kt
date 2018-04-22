package org.hedgehog.tasksplanningapp.ui.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*
import org.hedgehog.tasksplanningapp.R
import org.hedgehog.tasksplanningapp.ui.fragments.LoginFragment
import org.hedgehog.tasksplanningapp.utils.mAuth


class MainActivity : AppCompatActivity() {

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {

                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_dashboard -> {

                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_notifications -> {

                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        Log.i("ACTIVE USER IS", mAuth.currentUser!!.email)
        supportFragmentManager.beginTransaction().replace(R.id.main_frame_layout, LoginFragment()).commit()
//        database.reference.child("Tasks").push().setValue(Task(0, "", "",
//                Date().time, Date().time, null, Statuses.ACTIVE, null))
    }

}