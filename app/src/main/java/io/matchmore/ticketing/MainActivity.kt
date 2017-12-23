package io.matchmore.ticketing

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.util.Log
import io.matchmore.sdk.MatchMore
import io.matchmore.ticketing.find.FindFragment
import io.matchmore.ticketing.sell.SellFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        prepareNavigation(savedInstanceState)
        prepareMatchMore()
    }

    private fun prepareMatchMore() {
        MatchMore.instance.startUsingMainDevice(
                { device ->
                    Log.i(TAG, "start using device ${device.name}")
                    MatchMore.instance.matchMonitor.addOnMatchListener { matches, _ ->
                        Log.i(TAG, "Matches found: ${matches.size}")
                    }
                    MatchMore.instance.matchMonitor.startPollingMatches()
                }, Throwable::printStackTrace)
    }

    private fun prepareNavigation(savedInstanceState: Bundle?) {
        navigation.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.buy -> openFragment(BuyFragment())
                R.id.find -> openFragment(FindFragment())
                R.id.sell -> openFragment(SellFragment())
            }
            true
        }
        if (savedInstanceState == null) {
            navigation.selectedItemId = R.id.find
            openFragment(FindFragment())
        }
    }

    private fun openFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().replace(R.id.container, fragment).commitNow()
    }

    companion object {
        private const val TAG = "MatchMore"
    }
}
