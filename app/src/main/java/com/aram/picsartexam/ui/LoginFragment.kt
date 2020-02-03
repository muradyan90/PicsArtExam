package com.aram.picsartexam.ui


import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.aram.picsartexam.ExamApplication

import com.aram.picsartexam.R
import com.aram.picsartexam.databinding.FragmentLoginBinding
import com.aram.picsartexam.viewmodel.LoginViewModel
import com.google.gson.Gson
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.KoinComponent

/**
 * A simple [Fragment] subclass.
 */
class LoginFragment : Fragment(), KoinComponent {

    private lateinit var binding: FragmentLoginBinding
    private var sharedPreferances: SharedPreferences? = null
    private val viewModel: LoginViewModel by viewModel()
    private var userName = "dududu9@gmail.com"
    private var password = "dududu"


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false)
        binding.vieeModel = viewModel
        binding.lifecycleOwner = this

        sharedPreferances = activity?.getSharedPreferences("login_info",MODE_PRIVATE)

        val isLogged = sharedPreferances?.getBoolean("isLogged",false)
        val user =  sharedPreferances?.getString("user","")

        val gson = Gson()
        if(isLogged != null && isLogged){
            findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToItemsFragment(gson.fromJson(user,User::class.java)))
        }
        login()

        return binding.root
    }


    private fun login() {
        binding.loginButton.setOnClickListener {
            binding.loginErrorTv.visibility = View.GONE


            //ToDO user login code didnn work


            viewModel.user.observe(this, Observer {
                val status = it.status


                if (status != null && status == ("success")) {

                    var gson = Gson()
                    sharedPreferances?.edit()?.apply {
                        putBoolean("isLogged",true)
                        putString("user",gson.toJson(it))
                        apply()
                    }

                    this.findNavController().navigate(
                        LoginFragmentDirections.actionLoginFragmentToItemsFragment(
                            it
                        )
                    )
                } else {
                    binding.loginErrorTv.text = "No such user go to registration"
                    binding.loginErrorTv.visibility = View.VISIBLE                }
            })
            if (ExamApplication.isNetworkConnected()) {
                viewModel.loginUser(userName, password)
            } else {
                binding.loginErrorTv.text = "No internet connection try later"
                binding.loginErrorTv.visibility = View.VISIBLE
            }
            if (userName.trim().isEmpty()) {
                binding.loginUserName.error = "empty username"
                return@setOnClickListener
            }

            if (password.trim().isEmpty()) {
                binding.loginUserName.error = "empty password"
                return@setOnClickListener
            }




//            findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToItemsFragment(
//                User(
//                    id="ddf",
//                    name = "aram",
//                    photo = "dfdmf",
//                    username = "csdcns",
//                    defoultPhoto = true,
//                    email = "@mail",
//                    status = "dfd"
//                )
//            ))
        }
    }
}
