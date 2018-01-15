package io.matchmore.ticketing.sell

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import io.matchmore.sdk.MatchMore
import io.matchmore.sdk.api.models.Publication
import io.matchmore.ticketing.Contract
import io.matchmore.ticketing.R
import io.matchmore.ticketing.extensions.daysToSec
import io.matchmore.ticketing.extensions.showErrorDialog
import io.matchmore.ticketing.extensions.showProgressDialog
import io.matchmore.ticketing.extensions.validate
import kotlinx.android.synthetic.main.fragment_add_beacon.*

class AddBeaconSellFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_add_beacon, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        durationView.initValues(R.string.duration_days, 30)
        priceView.initValues(R.string.max_price, 1000)
        addButton.setOnClickListener { if (concertView.validate()) add() }
    }

    override fun onResume() {
        super.onResume()
        val beaconsId = MatchMore.instance.knownBeacons.findAll().map { it.id }
        beaconList.adapter = ArrayAdapter(context, android.R.layout.simple_spinner_item, beaconsId).apply {
            setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        }
    }

    private fun add() {
        val publication = Publication(
                "ticketstosale",
                0.0,
                durationView.value.daysToSec()
        )
        publication.properties.apply {
            put(Contract.PROPERTY_CONCERT, concertView.editText!!.text.toString())
            put(Contract.PROPERTY_PRICE, priceView.value.toDouble())
            put(Contract.PROPERTY_DEVICE_TYPE, Contract.DEVICE_TYPE_BEACON)
            put(Contract.PROPERTY_IMAGE, imageView.editText!!.text.toString())
        }
        val dialog = activity!!.showProgressDialog()
        MatchMore.instance.createPublication(publication, beaconList.selectedItem.toString(),
                { _ ->
                    dialog.dismiss()
                    activity?.finish()
                },
                {
                    dialog.dismiss()
                    activity?.showErrorDialog(it)
                })
    }
}