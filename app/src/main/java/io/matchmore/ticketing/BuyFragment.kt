package io.matchmore.ticketing

import android.graphics.Color
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration
import io.matchmore.sdk.MatchMore
import io.matchmore.sdk.api.models.Match
import io.matchmore.sdk.monitoring.MatchMonitorListener
import kotlinx.android.synthetic.main.fragment_buy.*
import net.idik.lib.slimadapter.SlimAdapter

class BuyFragment : Fragment() {

    private val adapter = SlimAdapter.create()

    private val matchListener: MatchMonitorListener = { _, _ -> adapter.updateData(MatchMore.instance.matches.toList()) }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        activity?.setTitle(R.string.buy)
        return inflater.inflate(R.layout.fragment_buy, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        matchesList.layoutManager = LinearLayoutManager(context)
        matchesList.addItemDecoration(HorizontalDividerItemDecoration.Builder(context)
                .color(Color.TRANSPARENT)
                .sizeResId(R.dimen.divider).build())
        adapter.register<Match>(R.layout.item_publication, { data, injector ->
            data.publication?.let {
                injector.text(R.id.concertView, it.properties[Contract.PROPERTY_CONCERT].toString())
                        .text(R.id.priceView, it.properties[Contract.PROPERTY_PRICE].toString())
                        .text(R.id.deviceTypeView, it.properties[Contract.PROPERTY_DEVICE_TYPE].toString())
            }
        }).attachTo(matchesList)
        adapter.updateData(MatchMore.instance.matches.toList())
        MatchMore.instance.matchMonitor.addOnMatchListener(matchListener)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        MatchMore.instance.matchMonitor.removeOnMatchListener(matchListener)
    }
}