package com.example.pagomovil.views

import androidx.recyclerview.widget.RecyclerView
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import com.example.pagomovil.R
import com.example.pagomovil.databinding.ItemContactBinding
import com.example.pagomovil.models.Contact
import com.example.pagomovil.utilis.bindingInflate

import com.example.pagomovil.views.placeholder.PlaceholderContent.PlaceholderItem

/**
 * [RecyclerView.Adapter] that can display a [PlaceholderItem].
 * TODO: Replace the implementation with code for your data type.
 */
class ContactRecyclerViewAdapter(
    private val contactList: List<Contact>,
    private val onSelectListener: (Contact) -> Unit,
    private val onDeleteListener: (Contact) -> Unit
) : RecyclerView.Adapter<ContactRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            parent.bindingInflate(R.layout.item_contact, false),
            onSelectListener,
            onDeleteListener
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(contactList[position])
    }

    override fun getItemCount(): Int = contactList.size

    inner class ViewHolder(
        private val binding: ItemContactBinding,
        private val onSelectListener: (Contact) -> Unit,
        private val onDeleteListener: (Contact) -> Unit
    ): RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Contact){
            binding.contact = item
            val btnDelete = itemView.findViewById<Button>(R.id.delete)
            btnDelete.setOnClickListener { onDeleteListener(item) }
            itemView.setOnClickListener { onSelectListener(item) }
        }
    }
}
