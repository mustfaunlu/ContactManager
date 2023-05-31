package com.mustafaunlu.contactmanager.ui.home

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.mustafaunlu.contactmanager.R
import com.mustafaunlu.contactmanager.databinding.HomeContactItemBinding
import com.mustafaunlu.contactmanager.domain.entity.ContactEntity

class HomeContactAdapter(private val onItemClicked: (Int) -> Unit) : ListAdapter<ContactEntity, HomeContactAdapter.HomeContactViewHolder>(HomeContactDiffCallback()) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): HomeContactAdapter.HomeContactViewHolder {
        val binding = HomeContactItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HomeContactViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HomeContactAdapter.HomeContactViewHolder, position: Int) {
        holder.bind(currentList[position])
    }

    inner class HomeContactViewHolder(private val binding: HomeContactItemBinding) : RecyclerView.ViewHolder(binding.root) {

        @SuppressLint("SetTextI18n")
        fun bind(contactEntity: ContactEntity) {
            binding.apply {
                nameSurnameTxt.text = "${contactEntity.name} ${contactEntity.surname}"
                groupNameTxt.text = contactEntity.group
                telAdressTxt.text = contactEntity.phone
                when (contactEntity.group) {
                    "Family" -> contactImage.setImageResource(R.drawable.family_img)
                    "Friends" -> contactImage.setImageResource(R.drawable.friends_img)
                    "Colleague" -> contactImage.setImageResource(R.drawable.colleague_img)
                    "Teammate" -> contactImage.setImageResource(R.drawable.teammate_img)
                }
            }

            binding.root.setOnClickListener {
                onItemClicked(contactEntity.id!!)
            }
        }
    }
}

class HomeContactDiffCallback : DiffUtil.ItemCallback<ContactEntity>() {
    override fun areItemsTheSame(oldItem: ContactEntity, newItem: ContactEntity): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: ContactEntity, newItem: ContactEntity): Boolean {
        return oldItem == newItem
    }
}
