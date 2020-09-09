package com.example.pagomovil.views

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.pagomovil.R
import com.example.pagomovil.viewModels.ServicesViewModel

class ServicesFragment : Fragment() {

    private lateinit var viewModel: ServicesViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val fragmentServicesBinding: com.example.pagomovil.databinding.FragmentServicesBinding
                = DataBindingUtil.inflate(layoutInflater, R.layout.fragment_services, container, false)

        this.viewModel = ViewModelProvider(this).get(ServicesViewModel::class.java)

        fragmentServicesBinding.viewModel = this.viewModel

        setupSpinners(fragmentServicesBinding.root)

        // Inflate the layout for this fragment
        return fragmentServicesBinding.root
    }

    private fun setupSpinners(view: View){
        view.findViewById<Spinner>(R.id.sService)?.apply {
            val services : List<String> = viewModel.services.value ?: emptyList<String>()
            val adapterService : ArrayAdapter<String> = ArrayAdapter(view.context, android.R.layout.simple_spinner_dropdown_item, services)
            adapterService.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            adapter = adapterService
        }
    }
}