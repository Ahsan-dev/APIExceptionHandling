package com.ahsan.testapiinterrupt.ui.info

import android.graphics.PorterDuff
import android.os.Bundle
import android.view.*
import androidx.core.graphics.BlendModeColorFilterCompat
import androidx.core.graphics.BlendModeCompat
import androidx.core.view.MenuProvider
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.navigation.fragment.findNavController
import com.ahsan.testapiinterrupt.R
import com.ahsan.testapiinterrupt.databinding.FragmentInfoBinding
import com.ahsan.testapiinterrupt.helper.ApiResponse
import com.google.android.material.snackbar.Snackbar


class InfoFragment : Fragment() , MenuProvider{

    private lateinit var tokenViewModel: TokenViewModel
    private lateinit var tokenFactory: TokenViewModelFactory
    private lateinit var infoViewModel: InfoViewModel
    private lateinit var infoViewModelFactory: InfoViewModelFactory
    private lateinit var binding: FragmentInfoBinding
    private var token: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(layoutInflater,R.layout.fragment_info, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity?.addMenuProvider(this,viewLifecycleOwner,Lifecycle.State.RESUMED)
        setupViewModel()
        setupUI(view)
        setupObserver(view)
    }

    private fun setupViewModel(){
        tokenFactory = TokenViewModelFactory()
        tokenViewModel = ViewModelProvider(this,tokenFactory).get(TokenViewModel::class.java)
        infoViewModelFactory = InfoViewModelFactory()
        infoViewModel = ViewModelProvider(this, infoViewModelFactory).get(InfoViewModel::class.java)
    }

    private fun setupUI(view: View){
        binding.infoName.text = ""

    }

    private fun setupObserver(view: View){
        infoViewModel.infoResponse.observe(viewLifecycleOwner){
            when(it){
                is ApiResponse.Failure -> Snackbar.make(view, "Error ${it.errorMessage}",Snackbar.LENGTH_SHORT).show()
                is ApiResponse.Loading -> Snackbar.make(view, "Loading...", Snackbar.LENGTH_SHORT).show()
                is ApiResponse.Success -> {
                    binding.infoName.text = it.data.name
                }
            }
        }

        tokenViewModel.token.observe(viewLifecycleOwner){ token ->
            this.token = token
            if(token == null){
                findNavController().navigate(R.id.action_infoFragment_to_loginFragment)
            }
        }
    }

    override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
        menuInflater.inflate(R.menu.home_menu,menu)
        for (i in 0 until menu.size()) {
            val drawable = menu.getItem(i).icon
            if (drawable != null) {
                drawable.mutate()
                drawable.setColorFilter(BlendModeColorFilterCompat.createBlendModeColorFilterCompat(android.R.color.white, BlendModeCompat.SRC_ATOP))
            }
        }
    }

    override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
        if(menuItem.itemId==R.id.home_menu_logout){
            tokenViewModel.deleteToken()
            return true
        }
        return false
    }

}