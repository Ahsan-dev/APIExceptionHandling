package com.ahsan.testapiinterrupt.ui.login

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.core.view.MenuProvider
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.ahsan.testapiinterrupt.R
import com.ahsan.testapiinterrupt.databinding.FragmentLoginBinding
import com.ahsan.testapiinterrupt.helper.ApiResponse
import com.ahsan.testapiinterrupt.helper.CoroutineErrorHandler
import com.ahsan.testapiinterrupt.models.request.LoginReq
import com.ahsan.testapiinterrupt.ui.info.TokenViewModel
import com.ahsan.testapiinterrupt.ui.info.TokenViewModelFactory
import com.google.android.material.snackbar.Snackbar
import org.koin.core.KoinComponent

class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding
    private lateinit var loginViewModel: LoginViewModel
    private lateinit var tokenViewModel: TokenViewModel
    private lateinit var loginFactory: LoginViewModelFactory
    private lateinit var tokenFactory: TokenViewModelFactory


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(layoutInflater,R.layout.fragment_login, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupViewModel()
        setupUI(view)
        setupObserver(view)


    }

    private fun setupUI(view:View){
        binding.loginbtn.setOnClickListener{
            loginViewModel.login(
                LoginReq(binding.emailEdt.text.toString(),binding.passwordEdt.text.toString()),
                object : CoroutineErrorHandler{
                    override fun onError(message: String) {
                        Toast.makeText(view.context,"Error $message",Toast.LENGTH_LONG).show()
                    }
                }
            )
        }
    }

    private fun setupObserver(view: View){
        loginViewModel.loginResponse.observe(viewLifecycleOwner){
            when(it){
                is ApiResponse.Failure -> Snackbar.make(view,"Error ${it.errorMessage}",Snackbar.LENGTH_SHORT).show()
                is ApiResponse.Loading -> Snackbar.make(view,"Loading...",Snackbar.LENGTH_SHORT).show()
                is ApiResponse.Success-> {
                    tokenViewModel.saveToken(it.data.token)
                }
            }
        }

        tokenViewModel.token.observe(viewLifecycleOwner){ token ->
            Log.d("Token","Token: $token")
            if(token != null){
                findNavController().navigate(R.id.action_loginFragment_to_infoFragment)
            }
        }
    }

    private fun setupViewModel(){
        loginFactory = LoginViewModelFactory()
        loginViewModel = ViewModelProvider(this,loginFactory).get(LoginViewModel::class.java)
        tokenFactory = TokenViewModelFactory()
        tokenViewModel = ViewModelProvider(this,tokenFactory).get(TokenViewModel::class.java)
        binding.viewmodel = loginViewModel
        binding.lifecycleOwner = viewLifecycleOwner
    }



}