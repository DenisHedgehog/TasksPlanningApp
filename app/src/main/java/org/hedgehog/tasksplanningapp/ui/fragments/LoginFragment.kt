package org.hedgehog.tasksplanningapp.ui.fragments


import android.app.AlertDialog
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.alert_dialog_registration.view.*
import kotlinx.android.synthetic.main.fragment_login.view.*
import kotlinx.coroutines.experimental.async
import kotlinx.coroutines.experimental.runBlocking

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
        val register = view.register

        loginButton.setOnClickListener {
            when {
                login.length() == 0 -> toast(context!!, getString(R.string.login_error))
                password.length() == 0 -> toast(context!!, getString(R.string.password_error))
                else -> {
                    mAuth.signInWithEmailAndPassword(login.text.toString(), password.text.toString())
                            .addOnCompleteListener {
                                if(it.isSuccessful) {
                                    toast(context!!, getString(R.string.login_success))
//                                    navigation.visibility = View.INVISIBLE
//                                    navigation.selectedItemId = R.id.tasks_menu
                                } else {
                                    toast(context!!, getString(R.string.login_denied))
                                }
                            }
                }
            }
        }

        register.setOnClickListener {
            showRegisterDialog()
        }

        return view
    }

    private fun registerUser(email: String, password: String) = runBlocking {
        val reg = async {
            mAuth.createUserWithEmailAndPassword(email, password)
        }.await()
        reg.addOnCompleteListener {
            if (!reg.isSuccessful) {
                when {
                    reg.exception is FirebaseAuthUserCollisionException -> toastWithContext(getString(R.string.email_already_exist))
                }
            } else {
                toastWithContext(getString(R.string.register_succesful))
            }
        }
    }

    private fun toastWithContext(message: String) {
        toast(context!!, message)
    }

    private fun showRegisterDialog() {
        val view = layoutInflater.inflate(R.layout.alert_dialog_registration, null)
        val email = view.registration_email
        val pass = view.registration_password
        val repPass = view.registration_repeat_password
        val dialog = AlertDialog.Builder(context)
                .setView(view)
                .setTitle(R.string.registration)
                .setPositiveButton(R.string.register, null)
                .setNegativeButton(R.string.cancel, null)
                .create()

        dialog.setOnShowListener {
            val button = dialog.getButton(AlertDialog.BUTTON_POSITIVE)
            button.setOnClickListener {
                when {
                    email.length() < 1 -> toast(context!!, getString(R.string.email_error))
                    pass.length() < 1 -> toast(context!!, getString(R.string.password_error))
                    repPass.length() < 1 -> toast(context!!, getString(R.string.repeat_password_error))
                    pass.text.toString() != repPass.text.toString() -> toast(context!!, getString(R.string.pass_doesnt_match))
                    else -> {
                        registerUser(email.text.toString(), pass.text.toString())
//                        if (registerUser(email.text.toString(), pass.text.toString())) {
//                            toast(context!!, getString(R.string.register_succesful))
//                            dialog.dismiss()
//                        } else {
//                            toast(context!!, getString(R.string.unsuccesful_register))
//                        }
                    }
                }
            }
        }

        dialog.show()
//        builder.setPositiveButton(R.string.register, { d, _ ->
//            when {
//                email.length() < 1 -> toast(context!!, getString(R.string.email_error))
//                pass.length() < 1 -> toast(context!!, getString(R.string.password_error))
//                repPass.length() < 1 -> toast(context!!, getString(R.string.repeat_password_error))
//                pass.text.toString() != repPass.text.toString() -> toast(context!!, getString(R.string.pass_doesnt_match))
//                else -> {
//                    if (registerUser(email.text.toString(), pass.text.toString())) {
//                        toast(context!!, getString(R.string.register_succesful))
//                        d.dismiss()
//                    } else {
//                        toast(context!!, getString(R.string.unsuccesful_register))
//                    }
//                }
//            }
//        })
//
//        builder.setNegativeButton(R.string.cancel, { d, _ ->
//            d.dismiss()
//        })
//        builder.create()
//
//        builder.show()

    }

}