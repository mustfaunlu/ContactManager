package com.mustafaunlu.contactmanager.ui.detail

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.mustafaunlu.contactmanager.R
import com.mustafaunlu.contactmanager.common.ResponseStateHandle
import com.mustafaunlu.contactmanager.databinding.FragmentDetailBinding
import com.mustafaunlu.contactmanager.domain.entity.ContactEntity
import com.mustafaunlu.contactmanager.utils.enable
import com.mustafaunlu.contactmanager.utils.gone
import com.mustafaunlu.contactmanager.utils.showConfirmationDialog
import com.mustafaunlu.contactmanager.utils.showSnackbar
import com.mustafaunlu.contactmanager.utils.visible
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment : Fragment() {
    private val args: DetailFragmentArgs by navArgs()
    private lateinit var binding: FragmentDetailBinding
    private val viewModel: DetailViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentDetailBinding.inflate(inflater, container, false)
        viewModel.getSingleContact(args.contactId)
        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupObservers()
    }

    @SuppressLint("SetTextI18n")
    private fun setupObservers() {
        viewModel.contact.observe(viewLifecycleOwner) { contact ->
            when (contact) {
                is ResponseStateHandle.Success -> {
                    binding.apply {
                        progressBar.gone()
                        contactNameSurname.setText("${contact.result.name} ${contact.result.surname}")
                        contactGroup.setText(contact.result.group)
                        contactPhone.setText(contact.result.phone)
                        contactAddress.setText(contact.result.address)
                        contactMail.setText(contact.result.email)
                        setContactImage(contact.result.group)
                    }

                    binding.btnDelete.setOnClickListener {
                        showConfirmationDialog(getString(R.string.dialog_warn_txt)) {
                            viewModel.deleteContact(contact.result)
                            binding.root.showSnackbar(R.string.contact_deleted)
                            findNavController().popBackStack()
                        }
                    }

                    binding.btnUpdate.setOnClickListener {
                        enableContactFieldsForUpdate()
                        setupContactGroupSpinner()
                    }

                    binding.apply {
                        btnSave.setOnClickListener {
                            updateContact(contact.result)
                        }
                    }
                }
                is ResponseStateHandle.Error -> binding.root.showSnackbar(R.string.unexpected_error)
                is ResponseStateHandle.Loading -> binding.progressBar.visible()
            }
        }
    }

    private fun updateContact(result: ContactEntity) {
        binding.apply {
            val name = contactNameSurname.text?.toString()?.substringBefore(" ") ?: ""
            val surname = contactNameSurname.text?.toString()?.substringAfter(" ") ?: ""
            val group = contactGroupSpinner.selectedItem.toString()
            val phone = contactPhone.text.toString()
            val address = contactAddress.text.toString()
            val email = contactMail.text.toString()

            if (name.isBlank() || surname.isBlank() || group.isBlank() || phone.isBlank() || address.isBlank() || email.isBlank()) {
                binding.root.showSnackbar(R.string.dont_empty_fields)
            } else {
                viewModel.updateContact(result.copy(name = name, surname = surname, group = group, phone = phone, address = address, email = email))
                binding.root.showSnackbar(R.string.contact_updated)
                findNavController().popBackStack()
            }
        }
    }

    private fun setupContactGroupSpinner() {
        ArrayAdapter.createFromResource(
            requireContext(),
            R.array.groups,
            android.R.layout.simple_spinner_item,
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            binding.contactGroupSpinner.adapter = adapter
        }
    }

    private fun enableContactFieldsForUpdate() {
        binding.apply {
            contactGroup.gone()
            contactGroupSpinner.visible()
            contactPhone.enable()
            contactAddress.enable()
            contactMail.enable()
            contactNameSurname.enable()
            btnUpdate.gone()
            btnSave.visible()
        }
    }

    private fun setContactImage(group: String) {
        val imageResId: Int = when (group) {
            getString(R.string.menu_family) -> R.drawable.family_img
            getString(R.string.menu_friends) -> R.drawable.friends_img
            getString(R.string.menu_work) -> R.drawable.colleague_img
            getString(R.string.menu_teammate) -> R.drawable.teammate_img
            else -> R.drawable.ic_launcher_foreground
        }
        binding.contactImg.setImageResource(imageResId)
    }
}


