package io.matchmore.ticketing.sell

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.design.widget.TextInputLayout
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.gms.maps.*
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import io.matchmore.sdk.MatchMore
import io.matchmore.sdk.api.models.Publication
import io.matchmore.ticketing.Contract
import io.matchmore.ticketing.R
import kotlinx.android.synthetic.main.fragment_add_mobile.*

class AddMobileSellFragment : Fragment(), OnMapReadyCallback {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_add_mobile, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        val mapFragment = SupportMapFragment.newInstance(GoogleMapOptions().apply { liteMode(true) })
        childFragmentManager.beginTransaction().replace(R.id.map, mapFragment).commit()
        mapFragment.getMapAsync(this)
        addButton.setOnClickListener { if (validate()) add() }
    }

    private fun add() {
        val publication = Publication(
                "ticketstosale",
                rangeView.editText!!.text.toString().toDouble(),
                durationView.editText!!.text.toString().toDouble()
        )
        publication.properties.apply {
            put(Contract.PROPERTY_CONCERT, concertView.editText!!.text.toString())
            put(Contract.PROPERTY_PRICE, priceView.editText!!.text.toString().toDouble().toString())
            put(Contract.PROPERTY_DEVICE_TYPE, "mobile")
        }
        MatchMore.instance.createPublication(publication, { _ -> activity.finish() }, Throwable::printStackTrace)
    }

    @SuppressLint("MissingPermission")
    override fun onMapReady(map: GoogleMap) {
        MatchMore.instance.locationManager.lastLocation?.let {
            val latLng = LatLng(it.latitude!!, it.longitude!!)
            map.moveCamera(CameraUpdateFactory.newCameraPosition(CameraPosition.builder().zoom(10f).target(latLng).build()))
            map.isMyLocationEnabled = true
        }
    }

    private fun validate(): Boolean {
        if (validateEditText(concertView)) return false
        if (validateEditText(priceView)) return false
        if (validateEditText(rangeView)) return false
        if (validateEditText(durationView)) return false
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