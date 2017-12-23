package io.matchmore.ticketing.find

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.*
import io.matchmore.ticketing.R

class FindFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        activity.setTitle(R.string.wanted_tickets)
        setHasOptionsMenu(true)
        return inflater.inflate(R.layout.find_fragment, container, false)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.add_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.add -> startActivity(Intent(context, AddFindActivity::class.java))
        }
        return true
    }
}