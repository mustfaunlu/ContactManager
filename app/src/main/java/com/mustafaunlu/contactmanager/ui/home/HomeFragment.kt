package com.mustafaunlu.contactmanager.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.mustafaunlu.contactmanager.R
import com.mustafaunlu.contactmanager.common.ResponseStateHandle
import com.mustafaunlu.contactmanager.databinding.FragmentHomeBinding
import com.mustafaunlu.contactmanager.utils.gone
import com.mustafaunlu.contactmanager.utils.observeTextChanges
import com.mustafaunlu.contactmanager.utils.showSnackbar
import com.mustafaunlu.contactmanager.utils.visible
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private val viewModel: HomeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeSearchViewTextChanges()
        observeContacts()
    }

    private fun observeContacts() {
        viewModel.lastTenContacts.observe(viewLifecycleOwner) {
            binding.contactRecyclerView.adapter = HomeContactAdapter(::onItemClicked).apply {
                when (it) {
                    is ResponseStateHandle.Success -> {
                        binding.progressBar.gone()
                        submitList(it.result)
                    }
                    is ResponseStateHandle.Error -> Snackbar.make(binding.root, getString(R.string.unexpected_error), Snackbar.LENGTH_LONG).show()
                    is ResponseStateHandle.Loading -> binding.progressBar.visible()
                }
            }
        }
        viewModel.searchContact.observe(viewLifecycleOwner) {
            binding.contactRecyclerView.adapter = HomeContactAdapter(::onItemClicked).apply {
                when (it) {
                    is ResponseStateHandle.Success -> {
                        binding.progressBar.gone()
                        submitList(it.result)
                    }
                    is ResponseStateHandle.Error -> binding.root.showSnackbar(R.string.unexpected_error)
                    is ResponseStateHandle.Loading -> {
                        binding.progressBar.visible()
                    }
                }
            }
        }
    }

    private fun observeSearchViewTextChanges() {
        binding.searchEditText.observeTextChanges()
            .debounce(300L)
            .distinctUntilChanged()
            .onEach {
                if (it.isBlank()) {
                    viewModel.getLastTenContacts()
                } else {
                    viewModel.getSearchResults(it)
                }
            }.launchIn(lifecycleScope)
    }

    private fun onItemClicked(contactId: Int) {
        val action = HomeFragmentDirections.actionNavHomeToDetailFragment(contactId)
        findNavController().navigate(action)
    }
}
