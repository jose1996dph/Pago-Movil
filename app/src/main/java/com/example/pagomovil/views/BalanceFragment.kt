package com.example.pagomovil.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.pagomovil.R
import com.example.pagomovil.utilis.app
import com.example.pagomovil.utilis.getViewModel
import com.example.pagomovil.viewModels.BalanceViewModel
import com.example.pagomovil.viewModels.ViewModelComponent
import com.example.pagomovil.viewModels.ViewModelModule

class BalanceFragment : Fragment() {

    private lateinit var viewModelComponent: ViewModelComponent

    private val viewModel: BalanceViewModel by lazy {
        getViewModel { viewModelComponent.balanceViewModel }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModelComponent = requireContext().app.component.inject(ViewModelModule())
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        setupActivity()
        return setupBinding(container)
    }

    private fun setupActivity(){
        //val title = requireActivity().findViewById<Toolbar>(R.id.toolBar)
        //title.title = getString(R.string.consulta)
        val title = requireActivity().findViewById<Toolbar>(R.id.toolBar) ?: return
        title.title = getString(R.string.consulta)
    }

    private fun setupBinding( container: ViewGroup?) : View? {
        val fragmentPaymentBinding: com.example.pagomovil.databinding.FragmentBalanceBinding
                = DataBindingUtil.inflate(layoutInflater, R.layout.fragment_balance, container,false)

        fragmentPaymentBinding.viewModel = this.viewModel
        return  fragmentPaymentBinding.root
    }
}