package io.matchmore.ticketing

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

class BuyFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        activity.setTitle(R.string.buy)
        return inflater.inflate(R.layout.buy_fragment, container, false)
    }

}