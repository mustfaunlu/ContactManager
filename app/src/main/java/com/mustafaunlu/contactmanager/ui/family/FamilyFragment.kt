package com.mustafaunlu.contactmanager.ui.family

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.mustafaunlu.contactmanager.R
import com.mustafaunlu.contactmanager.common.ResponseStateHandle
import com.mustafaunlu.contactmanager.databinding.FragmentFamilyBinding
import com.mustafaunlu.contactmanager.utils.showSnackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FamilyFragment : Fragment() {

    private var _binding: FragmentFamilyBinding? = null
    private val binding get() = _binding!!
    private val adapter by lazy { FamilyFragmentAdapter(::onItemClicked) }
    private val viewModel: FamilyViewModel by viewModels()

    private fun onItemClicked(contactId: Int) {
        val action = FamilyFragmentDirections.actionNavFamilyToDetailFragment(contactId)
        findNavController().navigate(action)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentFamilyBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.familyContacts.observe(viewLifecycleOwner) {
            when (it) {
                is ResponseStateHandle.Success -> {
                    adapter.updateItems(it.result)
                    binding.rvFamily.adapter = adapter
                }
                is ResponseStateHandle.Error -> binding.root.showSnackbar(R.string.unexpected_error)
                is ResponseStateHandle.Loading -> Unit
            }
        }
    }

    override fun onStart() {
        super.onStart()
        viewModel.getFamilyContacts("Family")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
