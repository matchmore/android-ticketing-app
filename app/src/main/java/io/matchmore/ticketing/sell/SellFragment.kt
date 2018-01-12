package io.matchmore.ticketing.sell

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.*
import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration
import io.matchmore.sdk.MatchMore
import io.matchmore.sdk.api.models.Publication
import io.matchmore.ticketing.Contract
import io.matchmore.ticketing.R
import kotlinx.android.synthetic.main.fragment_sell.*
import net.idik.lib.slimadapter.SlimAdapter

class SellFragment : Fragment() {

    private val adapter = SlimAdapter.create()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        activity?.setTitle(R.string.sell)
        setHasOptionsMenu(true)
        return inflater.inflate(R.layout.fragment_sell, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        publicationsList.layoutManager = LinearLayoutManager(context)
        publicationsList.addItemDecoration(HorizontalDividerItemDecoration.Builder(context)
                .color(Color.TRANSPARENT)
                .sizeResId(R.dimen.divider).build())
        adapter.register<Publication>(R.layout.item_publication, { data, injector ->
            injector.text(R.id.concertView, data.properties[Contract.PROPERTY_CONCERT].toString())
                    .text(R.id.priceView, data.properties[Contract.PROPERTY_PRICE].toString())
                    .text(R.id.deviceTypeView, data.properties[Contract.PROPERTY_DEVICE_TYPE].toString())
        }).attachTo(publicationsList)
    }

    override fun onResume() {
        super.onResume()
        adapter.updateData(MatchMore.instance.publications.findAll())
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.add_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.add -> startActivity(Intent(context, AddSellActivity::class.java))
        }
        return true
    }

}