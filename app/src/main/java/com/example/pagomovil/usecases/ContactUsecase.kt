package com.example.pagomovil.usecases

import com.example.pagomovil.data.repository.ContactRepository
import com.example.pagomovil.models.Contact

class ContactUsecase (
        private val contactRepository: ContactRepository
) {
    fun getAllContacts() = contactRepository.getAllContacts()
    fun deleteContact(contact: Contact) = contactRepository.deleteContact(contact)
}