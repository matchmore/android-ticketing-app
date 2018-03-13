package io.matchmore.ticketing.sell

import android.content.Context
import android.os.Bundle
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import io.matchmore.ticketing.R
import kotlinx.android.synthetic.main.activity_add_sell.*

class AddSellActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_sell)
        setTitle(R.string.sell_your_ticket)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        pagerView.adapter = AddPagerAdapter(this, supportFragmentManager)
        tabLayout.setupWithViewPager(pagerView)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) finish()
        return super.onOptionsItemSelected(item)
    }
}

class AddPagerAdapter(private val context: Context, fm: FragmentManager) : FragmentPagerAdapter(fm) {
    override fun getItem(position: Int) =
            when (position) {
                0 -> AddMobileSellFragment()
                1 -> AddPinSellFragment()
                2 -> AddBeaconSellFragment()
                else -> throw IllegalStateException()
            }

    override fun getPageTitle(position: Int) =
            context.getString(
                    when (position) {
                        0 -> R.string.mobile
                        1 -> R.string.pin
                        2 -> R.string.ibeacon
                        else -> throw IllegalStateException()
                    }
            )

    override fun getCount() = 3
}