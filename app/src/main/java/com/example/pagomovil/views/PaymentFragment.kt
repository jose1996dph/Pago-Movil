package com.example.pagomovil.views

import android.database.DataSetObserver
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.pagomovil.R
import com.example.pagomovil.viewModels.PayViewModel

class PaymentFragment : Fragment() {

    private lateinit var viewModel: PayViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return setupBinding(container) //inflater.inflate(R.layout.fragment_payment, container, false)
    }

    private fun setupBinding(container: ViewGroup?) : View? {
        var fragmentPaymentBinding: com.example.pagomovil.databinding.FragmentPaymentBinding
                = DataBindingUtil.inflate(layoutInflater, R.layout.fragment_payment, container, false)

        this.viewModel = ViewModelProvider(this).get(PayViewModel::class.java)

        fragmentPaymentBinding.viewModel = this.viewModel

        setupSpinners(fragmentPaymentBinding.root)

        return  fragmentPaymentBinding.root
    }

    private fun setupSpinners(view: View){
        view.findViewById<Spinner>(R.id.sBanks)?.apply {
            val banks : List<String> = viewModel.getBanksName()
            val adapterBank : ArrayAdapter<String> = ArrayAdapter(view.context, android.R.layout.simple_spinner_dropdown_item, banks)
            adapterBank.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            adapter = adapterBank
        }

        view.findViewById<Spinner>(R.id.sOperators)?.apply {
            val operators : List<String> = viewModel.operators.value?: emptyList<String>()
            val adapterOpe : ArrayAdapter<String> = ArrayAdapter<String>(view.context, android.R.layout.simple_spinner_dropdown_item, operators)
            adapterOpe.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            adapter = adapterOpe
        }
    }
}