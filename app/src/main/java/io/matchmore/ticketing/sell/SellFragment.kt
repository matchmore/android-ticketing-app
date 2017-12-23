package io.matchmore.ticketing.sell

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.*
import io.matchmore.ticketing.R

class SellFragment: Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        activity.setTitle(R.string.sell)
        setHasOptionsMenu(true)
        return inflater.inflate(R.layout.sell_fragment, container, false)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.add_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.add -> startActivity(Intent(context, AddSellActivity::class.java))
        }
        return true
    }

}