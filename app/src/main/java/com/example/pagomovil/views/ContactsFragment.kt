package com.example.pagomovil.views

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.pagomovil.R
import com.example.pagomovil.utilis.app
import com.example.pagomovil.utilis.getViewModel
import com.example.pagomovil.viewModels.PayViewModel
import com.example.pagomovil.viewModels.ViewModelComponent
import com.example.pagomovil.viewModels.ViewModelModule
import com.example.pagomovil.views.placeholder.PlaceholderContent

/**
 * A fragment representing a list of Items.
 */
class ContactsFragment : Fragment() {

    private lateinit var viewModelComponent: ViewModelComponent

    private val viewModel: PayViewModel by lazy {
        getViewModel { viewModelComponent.payViewModel }
    }

    private var columnCount = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModelComponent = requireContext().app.component.inject(ViewModelModule())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_contacts_list, container, false)

        // Set the adapter
        if (view is RecyclerView) {
            with(view) {
                layoutManager = when {
                    columnCount <= 1 -> LinearLayoutManager(context)
                    else -> GridLayoutManager(context, columnCount)
                }
                adapter = ContactRecyclerViewAdapter(viewModel.contacts.value!!,{
                    viewModel.selectContact(it)
                }, {
                    viewModel.deleteContact(it)
                })
            }
        }
        return view
    }

    companion object {

        // TODO: Customize parameter argument names
        const val ARG_COLUMN_COUNT = "column-count"

        // TODO: Customize parameter initialization
        @JvmStatic
        fun newInstance(columnCount: Int) =
            ContactsFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_COLUMN_COUNT, columnCount)
                }
            }
    }
}