package io.matchmore.ticketing.find

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.*
import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration
import io.matchmore.sdk.MatchMore
import io.matchmore.sdk.api.models.Subscription
import io.matchmore.ticketing.R
import kotlinx.android.synthetic.main.fragment_find.*
import net.idik.lib.slimadapter.SlimAdapter

class FindFragment : Fragment() {

    private val adapter = SlimAdapter.create()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        activity.setTitle(R.string.wanted_tickets)
        setHasOptionsMenu(true)
        return inflater.inflate(R.layout.fragment_find, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        subscriptionsList.layoutManager = LinearLayoutManager(context)
        subscriptionsList.addItemDecoration(HorizontalDividerItemDecoration.Builder(context)
                .color(Color.TRANSPARENT)
                .sizeResId(R.dimen.divider).build())
        adapter.register<Subscription>(R.layout.item_subscription, { data, injector ->
            injector.text(R.id.subscriptionId, data.id).text(R.id.selector, data.selector)
        }).attachTo(subscriptionsList)
    }

    override fun onResume() {
        super.onResume()
        adapter.updateData(MatchMore.instance.subscriptions.findAll())
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.add_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.add -> startActivity(Intent(context, AddFindActivity::class.java))
        }
        return true
    }
}