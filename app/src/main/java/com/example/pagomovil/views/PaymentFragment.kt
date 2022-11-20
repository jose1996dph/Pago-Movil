package com.example.pagomovil.views

import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.appcompat.widget.Toolbar
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.lifecycle.Lifecycle
import com.example.pagomovil.R
import com.example.pagomovil.utilis.app
import com.example.pagomovil.utilis.getViewModel
import com.example.pagomovil.viewModels.PayViewModel
import com.example.pagomovil.databinding.FragmentPaymentBinding
import com.example.pagomovil.viewModels.ViewModelComponent
import com.example.pagomovil.viewModels.ViewModelModule
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.toList

class PaymentFragment : Fragment() {

    private lateinit var viewModelComponent: ViewModelComponent

    private val viewModel: PayViewModel by lazy {
        getViewModel { viewModelComponent.payViewModel }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupMenu()
    }

    private fun setupMenu() {
        (requireActivity() as MenuHost).addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.payment_menu, menu)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                return when (menuItem.itemId){
                    R.id.navContacts -> {
                        showContacts()
                        true
                    }
                    else -> false
                }
            }
        }, viewLifecycleOwner, Lifecycle.State.RESUMED)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModelComponent = requireContext().app.component.inject(ViewModelModule())
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setupActivity()
        // Inflate the layout for this fragment
        return setupBinding(container)
    }

    private fun showContacts(){
        val contacts = viewModel.contacts.value!!
        val adapter = ArrayAdapter<String>(requireContext(), R.layout.support_simple_spinner_dropdown_item)
        for (contact in contacts){
            adapter.add(contact.name)
        }

        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle(R.string.contacts)
            .setAdapter(adapter
            ) { _, which ->
                viewModel.selectContact(contacts[which])
            }
        builder.create()
        builder.show()
    }

    private fun setupActivity(){
        val title = requireActivity().findViewById<Toolbar>(R.id.toolBar)
        title.title = getString(R.string.transfers)
    }

    private fun setupBinding(container: ViewGroup?) : View? {
        val fragmentPaymentBinding: FragmentPaymentBinding =
            DataBindingUtil.inflate(
                layoutInflater,
                R.layout.fragment_payment,
                container,
                false
        )

        fragmentPaymentBinding.viewModel = this.viewModel

        setupBindingElement(fragmentPaymentBinding.root)

        return fragmentPaymentBinding.root
    }

    private fun setupBindingElement(view: View){
        viewModel.contacts.observe(viewLifecycleOwner){ contacts ->
            for (contact in contacts){
                Log.d("Contacts", contact.name);
            }
        }

        val sBanks =  view.findViewById<Spinner>(R.id.sBanks)?.apply {
            val banks : List<String> = viewModel.getBanksName()
            val adapterBank : ArrayAdapter<String> = ArrayAdapter(view.context, android.R.layout.simple_spinner_dropdown_item, banks)
            adapterBank.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            adapter = adapterBank
        }
        viewModel.bankPosition.observe(viewLifecycleOwner) {
            sBanks!!.setSelection(it)
        }

        val sOperators = view.findViewById<Spinner>(R.id.sOperators)?.apply {
            val operators : List<String> = viewModel.operators.value ?: emptyList<String>()
            val adapterOperator : ArrayAdapter<String> = ArrayAdapter(view.context, android.R.layout.simple_spinner_dropdown_item, operators)
            adapterOperator.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            adapter = adapterOperator
        }
        viewModel.operatorPosition.observe(viewLifecycleOwner) {
            sOperators!!.setSelection(it)
        }

        val dni = view.findViewById<EditText>(R.id.dni);
        viewModel.dni.observe(viewLifecycleOwner) {
            if (it != dni.text.toString()) {
                dni?.setText(it)
            }
        }

        val phone = view.findViewById<EditText>(R.id.phone);
        viewModel.phone.observe(viewLifecycleOwner) {
            if (it != phone.text.toString()) {
                phone?.setText(it)
            }
        }

        val mount = view.findViewById<EditText>(R.id.mount);
        viewModel.mount.observe(viewLifecycleOwner) {
            if (it != mount.text.toString()) {
                mount?.setText(it)
            }
        }

        val name = view.findViewById<EditText>(R.id.name)
        viewModel.name.observe(viewLifecycleOwner
        ) {
            if (it != name.text.toString()) {
                name.setText(it)
            }
        }

        val saveContact = view.findViewById<CheckBox>(R.id.saveContact)
        val saveContactText = view.findViewById<TextView>(R.id.tvName)
        viewModel.saveContact.observe(viewLifecycleOwner) {
            if (saveContact.isChecked != it) {
                saveContact.isChecked = it
            }
            name.visibility = if (it == true) View.VISIBLE else View.GONE
            saveContactText.visibility = if (it == true) View.VISIBLE else View.GONE
        }
    }
}

