package com.mustafaunlu.contactmanager.domain.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "contact")
data class ContactEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    val name: String? = null,
    val surname: String? = null,
    val group: String,
    val phone: String,
    val email: String,
    val address: String,
)
