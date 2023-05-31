package com.mustafaunlu.contactmanager.ui.friends

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.mustafaunlu.contactmanager.R
import com.mustafaunlu.contactmanager.common.ResponseStateHandle
import com.mustafaunlu.contactmanager.databinding.FragmentFriendsBinding
import com.mustafaunlu.contactmanager.utils.showSnackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FriendsFragment : Fragment() {

    private var _binding: FragmentFriendsBinding? = null
    private val binding get() = _binding!!

    private val viewModel: FriendsViewModel by viewModels()

    private val adapter by lazy { FriendsFragmentAdapter(::onItemClicked) }

    private fun onItemClicked(contactId: Int) {
        val action = FriendsFragmentDirections.actionNavFriendsToDetailFragment(contactId)
        findNavController().navigate(action)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentFriendsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.friendsContacts.observe(viewLifecycleOwner) {
            when (it) {
                is ResponseStateHandle.Success -> {
                    adapter.updateItems(it.result)
                    binding.rvFriends.adapter = adapter
                }
                is ResponseStateHandle.Error -> binding.root.showSnackbar(R.string.unexpected_error)
                is ResponseStateHandle.Loading -> Unit
            }
        }
    }

    override fun onStart() {
        super.onStart()
        viewModel.getFriendsContacts("Friends")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
