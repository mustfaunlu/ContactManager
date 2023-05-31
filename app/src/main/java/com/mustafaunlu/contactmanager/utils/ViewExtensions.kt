package com.mustafaunlu.contactmanager.utils

import android.text.Editable
import android.view.View
import android.widget.EditText
import androidx.annotation.StringRes
import androidx.appcompat.widget.AppCompatEditText
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import com.google.android.material.snackbar.Snackbar
import com.mustafaunlu.contactmanager.ui.contract.AbstractTextWatcher
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.onStart

fun AppCompatEditText.observeTextChanges(): Flow<String> {
    return callbackFlow {
        val textWatcher = object : AbstractTextWatcher() {
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                super.onTextChanged(s, start, before, count)
                trySend(s.toString())
            }

            override fun afterTextChanged(s: Editable?) {
                super.afterTextChanged(s)
                trySend(s.toString())
            }
        }
        addTextChangedListener(textWatcher)

        awaitClose {
            removeTextChangedListener(textWatcher)
        }
    }.onStart {
        emit(text.toString() ?: "")
    }
}

fun View.visible() {
    visibility = View.VISIBLE
}

fun View.gone() {
    visibility = View.GONE
}

fun EditText.enable() {
    this.isEnabled = true
    this.isFocusableInTouchMode = true
    this.isFocusable = true
    this.setBackgroundColor(Color.LightGray.toArgb())
    this.requestFocus()
}

fun View.showSnackbar(@StringRes messageResId: Int, duration: Int = Snackbar.LENGTH_SHORT) {
    Snackbar.make(this, context.getString(messageResId), duration).show()
}
