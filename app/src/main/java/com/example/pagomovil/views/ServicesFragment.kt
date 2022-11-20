package com.example.pagomovil.views

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil
import com.example.pagomovil.R
import com.example.pagomovil.utilis.app
import com.example.pagomovil.utilis.getViewModel
import com.example.pagomovil.viewModels.ServicesViewModel
import com.example.pagomovil.viewModels.ViewModelComponent
import com.example.pagomovil.viewModels.ViewModelModule

class ServicesFragment : Fragment() {

    private lateinit var viewModelComponent: ViewModelComponent

    private val viewModel: ServicesViewModel by lazy {
        getViewModel { viewModelComponent.servicesViewModel }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        viewModelComponent = requireContext().app.component.inject(ViewModelModule())
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        setupActivity()
        // Inflate the layout for this fragment
        return setupBinding(container)
    }

    private fun setupActivity(){
        val title = requireActivity().findViewById<Toolbar>(R.id.toolBar)
        title.title = getString(R.string.service)
    }

    private fun setupBinding(container:  ViewGroup?): View{
        val fragmentServicesBinding: com.example.pagomovil.databinding.FragmentServicesBinding
                = DataBindingUtil.inflate(layoutInflater, R.layout.fragment_services, container, false)

        fragmentServicesBinding.viewModel = this.viewModel

        setupSpinners(fragmentServicesBinding.root)

        return fragmentServicesBinding.root
    }

    private fun setupSpinners(view: View){
        view.findViewById<Spinner>(R.id.sService)?.apply {
            val services : List<String> = viewModel.services.value ?: emptyList<String>()
            val adapterService : ArrayAdapter<String> = ArrayAdapter(view.context, android.R.layout.simple_spinner_dropdown_item, services)
            adapterService.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            adapter = adapterService
        }

         val alias = view.findViewById<EditText>(R.id.alias);
        viewModel.alias.observe(viewLifecycleOwner, {
            if (it != alias?.text.toString()){
                alias?.setText(it)
            }
        })

        val mount = view.findViewById<EditText>(R.id.mount);
        viewModel.mount.observe(viewLifecycleOwner, {
            if (it != mount?.text.toString()){
                mount?.setText(it)
            }
        })

        val name = view.findViewById<EditText>(R.id.name)
        viewModel.name.observe(viewLifecycleOwner,  {
            if (it != name.text.toString()){
                name.setText(it)
            }
        })

        val saveContact = view.findViewById<CheckBox>(R.id.saveContact)
        viewModel.saveService.observe(viewLifecycleOwner,  {
            if (saveContact.isChecked != it){
                saveContact.isChecked = it
            }
            name.visibility = if(it == true) View.VISIBLE else View.GONE
        })
    }
}
