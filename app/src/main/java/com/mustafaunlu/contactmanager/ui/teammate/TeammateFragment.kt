package com.mustafaunlu.contactmanager.ui.teammate

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.mustafaunlu.contactmanager.R
import com.mustafaunlu.contactmanager.common.ResponseStateHandle
import com.mustafaunlu.contactmanager.databinding.FragmentTeammateBinding
import com.mustafaunlu.contactmanager.utils.showSnackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TeammateFragment : Fragment() {

    private var _binding: FragmentTeammateBinding? = null
    private val binding get() = _binding!!

    private val viewModel: TeammateViewModel by viewModels()

    private val adapter by lazy { TeammateFragmentAdapter(::onItemClicked) }

    private fun onItemClicked(contactId: Int) {
        val action = TeammateFragmentDirections.actionNavTeammateToDetailFragment(contactId)
        findNavController().navigate(action)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentTeammateBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.teammateContacts.observe(viewLifecycleOwner) {
            when (it) {
                is ResponseStateHandle.Success -> {
                    adapter.updateItems(it.result)
                    binding.rvTeammate.adapter = adapter
                }
                is ResponseStateHandle.Error -> binding.root.showSnackbar(R.string.unexpected_error)
                is ResponseStateHandle.Loading -> Unit
            }
        }
    }

    override fun onStart() {
        super.onStart()
        viewModel.getTeammateContacts("Teammate")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
