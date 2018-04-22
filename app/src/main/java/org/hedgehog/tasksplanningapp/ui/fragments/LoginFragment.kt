package org.hedgehog.tasksplanningapp.ui.fragments


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_login.view.*

import org.hedgehog.tasksplanningapp.R
import org.hedgehog.tasksplanningapp.utils.mAuth
import org.hedgehog.tasksplanningapp.utils.toast


class LoginFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_login, container, false)

        val login = view.login
        val password = view.password
        val loginButton = view.login_button

        loginButton.setOnClickListener {
            when {
                login.length() == 0 -> toast(context!!, getString(R.string.login_error))
                password.length() == 0 -> toast(context!!, getString(R.string.password_error))
                else -> {
                    mAuth.signInWithEmailAndPassword(login.text.toString(), password.text.toString())
                            .addOnCompleteListener {
                                if(it.isSuccessful) {
                                    toast(context!!, getString(R.string.login_success))
                                } else {
                                    toast(context!!, getString(R.string.login_denied))
                                }
                            }
                }
            }
        }

        return view
    }

}
