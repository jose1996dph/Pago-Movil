package com.example.pagomovil.views

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pagomovil.R
import com.example.pagomovil.models.Contact


/**
 * A fragment representing a list of Items.
 */
class ContactsFragment(
    private val contacts: List<Contact>,
    private val onSelectListener: (Contact) -> Unit,
    private val onDeleteListener: (Contact) -> Unit
) : DialogFragment() {
    private var columnCount = 1


    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState)
        dialog.window!!.requestFeature(Window.FEATURE_NO_TITLE)

        return dialog
    }

    override fun onStart() {
        super.onStart()
        val dialog = dialog
        if (dialog != null) {
            dialog.window!!.setLayout(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_contacts_list, container, false)

        val recyclerViewContracts = view.findViewById<RecyclerView>(R.id.rcContacts)

        // Set the adapter
        setContacts(recyclerViewContracts)

        return view
    }

    private fun setContacts(recyclerViewContracts: RecyclerView) {
        val handlerOnSelectListener = {c: Contact ->  onSelectListener(c); this.dismiss() }
        val handlerOnDeleteListener = {c: Contact ->  onDeleteListener(c); this.dismiss() }

        with(recyclerViewContracts) {
            layoutManager = when {
                columnCount <= 1 -> LinearLayoutManager(context)
                else -> GridLayoutManager(context, columnCount)
            }
            adapter = ContactRecyclerViewAdapter(contacts,  handlerOnSelectListener, handlerOnDeleteListener)
        }
    }

    companion object {
        const val TAG = "ContactsDialog"
    }
}