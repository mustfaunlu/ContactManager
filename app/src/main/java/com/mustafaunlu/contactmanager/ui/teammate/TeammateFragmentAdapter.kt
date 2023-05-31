package com.mustafaunlu.contactmanager.ui.teammate

import android.view.LayoutInflater
import android.view.ViewGroup
import com.mustafaunlu.contactmanager.R
import com.mustafaunlu.contactmanager.databinding.HomeContactItemBinding
import com.mustafaunlu.contactmanager.domain.entity.ContactEntity
import com.mustafaunlu.contactmanager.ui.base.BaseRecyclerViewAdapter
import com.mustafaunlu.contactmanager.ui.base.BaseViewHolder

class TeammateFragmentAdapter(private val onItemClicked: (Int) -> Unit) : BaseRecyclerViewAdapter<ContactEntity, TeammateFragmentViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeammateFragmentViewHolder {
        return TeammateFragmentViewHolder.create(parent, onItemClicked)
    }
}

class TeammateFragmentViewHolder(private val binding: HomeContactItemBinding, private val onItemClicked: (Int) -> Unit) : BaseViewHolder<ContactEntity>(binding.root) {

    override fun onBind(data: ContactEntity) {
        binding.apply {
            nameSurnameTxt.text = "${data.name} ${data.surname}"
            groupNameTxt.text = data.group
            telAdressTxt.text = data.phone
            when (data.group) {
                "Family" -> contactImage.setImageResource(R.drawable.family_img)
                "Friends" -> contactImage.setImageResource(R.drawable.friends_img)
                "Colleague" -> contactImage.setImageResource(R.drawable.colleague_img)
                "Teammate" -> contactImage.setImageResource(R.drawable.teammate_img)
            }
        }

        binding.root.setOnClickListener {
            onItemClicked(data.id!!)
        }
    }

    companion object {
        fun create(parent: ViewGroup, onItemClicked: (Int) -> Unit): TeammateFragmentViewHolder {
            val binding = HomeContactItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return TeammateFragmentViewHolder(binding, onItemClicked)
        }
    }
}
