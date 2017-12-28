package io.matchmore.ticketing.extensions

import android.app.Activity
import com.afollestad.materialdialogs.MaterialDialog
import io.matchmore.ticketing.R

fun Activity.showProgressDialog() =
        MaterialDialog.Builder(this)
                .content(R.string.please_wait)
                .progress(true, 0)
                .show()

fun Activity.showErrorDialog(throwable: Throwable): MaterialDialog {
    throwable.printStackTrace()
    return MaterialDialog.Builder(this)
            .neutralText(android.R.string.ok)
            .onNeutral({ dialog, _ -> dialog.dismiss() })
            .content(throwable.localizedMessage)
            .show()
}