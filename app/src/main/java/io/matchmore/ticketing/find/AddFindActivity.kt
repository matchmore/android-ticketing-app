package io.matchmore.ticketing.find

import android.os.Bundle
import android.support.design.widget.TextInputLayout
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import io.matchmore.sdk.MatchMore
import io.matchmore.sdk.api.models.Subscription
import io.matchmore.ticketing.Contract
import io.matchmore.ticketing.R
import io.matchmore.ticketing.extensions.showErrorDialog
import io.matchmore.ticketing.extensions.showProgressDialog
import kotlinx.android.synthetic.main.activity_add_find.*

class AddFindActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_find)
        setTitle(R.string.add_wanted_ticket)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        radiusView.initValues(R.string.radius_km, 100)
        durationView.initValues(R.string.duration_days, 30)
        maxPriceView.initValues(R.string.max_price, 1000)
        addButton.setOnClickListener { if (validate()) add() }
    }

    private fun add() {
        val concert = concertView.editText!!.text.toString()
        val maxPrice = maxPriceView.value.toDouble()
        val subscription = Subscription(
                "ticketstosale",
                (radiusView.value * 1000).toDouble(),
                durationView.value.toDouble() * 60 * 24,
                "${Contract.PROPERTY_CONCERT}='$concert' and ${Contract.PROPERTY_PRICE} <= $maxPrice"
        )
        subscription.pushers = mutableListOf("${MatchMore.instance.main?.deviceToken}")
        val dialog = showProgressDialog()
        MatchMore.instance.createSubscription(subscription, { _ ->
            dialog.dismiss()
            finish()
        }, {
            dialog.dismiss()
            showErrorDialog(it)
        })
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) finish()
        return super.onOptionsItemSelected(item)
    }

    private fun validate(): Boolean {
        if (validateEditText(concertView)) return false
        return true
    }

    private fun validateEditText(textInputLayout: TextInputLayout): Boolean {
        if (textInputLayout.editText!!.text.isEmpty()) {
            textInputLayout.error = getString(R.string.cant_be_empty)
            return true
        }
        return false
    }

}