package io.matchmore.ticketing.sell

import android.annotation.SuppressLint
import android.os.Bundle
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
import io.matchmore.ticketing.extensions.*
import kotlinx.android.synthetic.main.fragment_add_mobile.*

class AddMobileSellFragment : Fragment(), OnMapReadyCallback {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_add_mobile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val mapFragment = SupportMapFragment.newInstance(GoogleMapOptions().apply { liteMode(true) })
        childFragmentManager.beginTransaction().replace(R.id.map, mapFragment).commit()
        mapFragment.getMapAsync(this)
        radiusView.initValues(R.string.radius_km, 100)
        durationView.initValues(R.string.duration_days, 30)
        priceView.initValues(R.string.max_price, 1000)
        addButton.setOnClickListener { if (concertView.validate()) add() }
    }

    private fun add() {
        val publication = Publication(
                "ticketstosale",
                radiusView.value.kmToM(),
                durationView.value.daysToSec()
        )
        publication.properties.apply {
            put(Contract.PROPERTY_CONCERT, concertView.editText!!.text.toString())
            put(Contract.PROPERTY_PRICE, priceView.value.toDouble())
            put(Contract.PROPERTY_DEVICE_TYPE, Contract.DEVICE_TYPE_MOBILE)
            put(Contract.PROPERTY_IMAGE, imageView.editText!!.text.toString())
        }
        val dialog = activity!!.showProgressDialog()
        MatchMore.instance.createPublication(publication,
                { _ ->
                    dialog.dismiss()
                    activity?.finish()
                },
                {
                    dialog.dismiss()
                    activity?.showErrorDialog(it)
                })
    }

    @SuppressLint("MissingPermission")
    override fun onMapReady(map: GoogleMap) {
        MatchMore.instance.locationManager.lastLocation?.let {
            val latLng = LatLng(it.latitude!!, it.longitude!!)
            map.moveCamera(CameraUpdateFactory.newCameraPosition(CameraPosition.builder().zoom(10f).target(latLng).build()))
            map.isMyLocationEnabled = true
        }
    }
}