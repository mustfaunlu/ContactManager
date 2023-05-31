package com.mustafaunlu.contactmanager.ui.family

import android.view.LayoutInflater
import android.view.ViewGroup
import com.mustafaunlu.contactmanager.R
import com.mustafaunlu.contactmanager.databinding.HomeContactItemBinding
import com.mustafaunlu.contactmanager.domain.entity.ContactEntity
import com.mustafaunlu.contactmanager.ui.base.BaseRecyclerViewAdapter
import com.mustafaunlu.contactmanager.ui.base.BaseViewHolder

class FamilyFragmentAdapter(private val onItemClicked: (Int) -> Unit) : BaseRecyclerViewAdapter<ContactEntity, FamilyFragmentViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FamilyFragmentViewHolder {
        return FamilyFragmentViewHolder.create(parent, onItemClicked)
    }
}

class FamilyFragmentViewHolder(private val binding: HomeContactItemBinding, private val onItemClicked: (Int) -> Unit) : BaseViewHolder<ContactEntity>(binding.root) {

    override fun onBind(data: ContactEntity) {
        binding.apply {
            nameSurnameTxt.text = "${data.name} ${data.surname}"
            groupNameTxt.text = data.group
            telAdressTxt.text = data.phone
            contactImage.setImageResource(R.drawable.family_img)
        }

        binding.root.setOnClickListener {
            onItemClicked(data.id!!)
        }
    }

    companion object {
        fun create(parent: ViewGroup, onItemClicked: (Int) -> Unit): FamilyFragmentViewHolder {
            val binding = HomeContactItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return FamilyFragmentViewHolder(binding, onItemClicked)
        }
    }
}
