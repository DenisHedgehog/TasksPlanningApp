package org.hedgehog.tasksplanningapp.ui.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import org.hedgehog.tasksplanningapp.R
import org.hedgehog.tasksplanningapp.ui.fragments.LoginFragment
import org.hedgehog.tasksplanningapp.ui.fragments.TaskFragment
import org.hedgehog.tasksplanningapp.utils.mAuth


class MainActivity : AppCompatActivity() {

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.journal_menu -> {

                return@OnNavigationItemSelectedListener true
            }
            R.id.tasks_menu -> {
                supportFragmentManager.beginTransaction().replace(R.id.main_frame_layout, TaskFragment()).commit()
                return@OnNavigationItemSelectedListener true
            }
            R.id.profile_menu -> {
                mAuth.signOut()
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)

        if (mAuth.currentUser != null) {
            navigation.selectedItemId = R.id.tasks_menu
        } else {
            navigation.visibility = View.INVISIBLE
            supportFragmentManager.beginTransaction().replace(R.id.main_frame_layout, LoginFragment()).commit()
        }

//        database.reference.child("Tasks").push().setValue(Task(0, "", "",
//                Date().time, Date().time, null, Statuses.ACTIVE, null))
    }

}