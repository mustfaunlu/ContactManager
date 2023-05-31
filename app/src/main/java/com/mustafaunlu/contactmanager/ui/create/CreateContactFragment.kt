package com.mustafaunlu.contactmanager.ui.create

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.mustafaunlu.contactmanager.R
import com.mustafaunlu.contactmanager.databinding.FragmentCreateContactBinding
import com.mustafaunlu.contactmanager.domain.entity.ContactEntity
import com.mustafaunlu.contactmanager.utils.showSnackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CreateContactFragment : Fragment() {

    private lateinit var binding: FragmentCreateContactBinding
    private val viewModel: CreateContactViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentCreateContactBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupDropdownMenu()
        setupButtonClickListener()
    }

    private fun setupDropdownMenu() {
        val items = listOf(
            getString(R.string.menu_family),
            getString(R.string.menu_friends),
            getString(R.string.menu_teammate),
            getString(R.string.menu_work),
        )
        val adapter = ArrayAdapter(requireContext(), R.layout.dropdown_list_item, items)
        (binding.dropdownMenu.editText as? AutoCompleteTextView)?.setAdapter(adapter)
    }

    private fun setupButtonClickListener() {
        binding.buttonSave.setOnClickListener {
            val contactName = binding.nameEdtTxt.text.toString()
            val contactSurname = binding.surnameEdtTxt.text.toString()
            val contactPhone = binding.phoneEdtTxt.text.toString()
            val contactEmail = binding.mailEdtTxt.text.toString()
            val contactAddress = binding.adressEdtTxt.text.toString()
            val contactGroup = binding.dropdownMenu.editText?.text.toString()

            if (contactName.isBlank() || contactSurname.isBlank() || contactPhone.isBlank() || contactEmail.isBlank() || contactAddress.isBlank() || contactGroup.isBlank()) {
                binding.root.showSnackbar(R.string.dont_empty_fields)
                return@setOnClickListener
            } else {
                val newContact = ContactEntity(
                    name = contactName,
                    surname = contactSurname,
                    phone = contactPhone,
                    email = contactEmail,
                    address = contactAddress,
                    group = contactGroup,
                )
                viewModel.insertContact(newContact)
                binding.root.showSnackbar(R.string.contact_added_successfully)
                findNavController().popBackStack()
            }
        }
    }
}
