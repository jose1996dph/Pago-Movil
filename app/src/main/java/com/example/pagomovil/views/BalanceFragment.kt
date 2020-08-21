package com.example.pagomovil.views

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.pagomovil.R
import com.example.pagomovil.viewModels.BalanceViewModel
import com.example.pagomovil.viewModels.MainViewModel
import com.example.pagomovil.viewModels.PayViewModel
import java.lang.Exception

class BalanceFragment : Fragment() {

    private var viewModel: BalanceViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return setupBinding(container)

        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_balance, container, false)
    }

    private fun setupBinding( container: ViewGroup?) : View? {
        var fragmentPaymentBinding: com.example.pagomovil.databinding.FragmentBalanceBinding
                = DataBindingUtil.inflate(layoutInflater, R.layout.fragment_balance, container,false)

        this.viewModel = ViewModelProvider(this).get(BalanceViewModel::class.java)

        fragmentPaymentBinding.viewModel = this.viewModel
        return  fragmentPaymentBinding.root
    }
}