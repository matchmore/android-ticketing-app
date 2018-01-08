package io.matchmore.ticketing.extensions

import android.support.design.widget.TextInputLayout
import io.matchmore.ticketing.R

fun TextInputLayout.validate(): Boolean {
    if (editText!!.text.isEmpty()) {
        error = context.getString(R.string.cant_be_empty)
        return false
    }
    return true
}