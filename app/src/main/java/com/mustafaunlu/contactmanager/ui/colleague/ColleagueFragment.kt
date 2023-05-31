package com.mustafaunlu.contactmanager.ui.colleague

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.mustafaunlu.contactmanager.R
import com.mustafaunlu.contactmanager.common.ResponseStateHandle
import com.mustafaunlu.contactmanager.databinding.FragmentColleagueBinding
import com.mustafaunlu.contactmanager.utils.showSnackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ColleagueFragment : Fragment() {

    lateinit var binding: FragmentColleagueBinding
    private val adapter by lazy { ColleagueFragmentAdapter(::onItemClicked) }
    private val viewModel: ColleagueViewModel by viewModels()

    private fun onItemClicked(contactId: Int) {
        val action = ColleagueFragmentDirections.actionNavWorkToDetailFragment(contactId)
        findNavController().navigate(action)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentColleagueBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.colleagueContacts.observe(viewLifecycleOwner) {
            when (it) {
                is ResponseStateHandle.Success -> {
                    adapter.updateItems(it.result)
                    binding.rvColleague.adapter = adapter
                }
                is ResponseStateHandle.Error -> binding.root.showSnackbar(R.string.unexpected_error)
                is ResponseStateHandle.Loading -> Unit
            }
        }
    }

    override fun onStart() {
        super.onStart()
        viewModel.getColleagueContact("Colleague")
    }
}
