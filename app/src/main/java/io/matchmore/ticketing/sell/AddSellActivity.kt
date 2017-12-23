package io.matchmore.ticketing.sell

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import io.matchmore.ticketing.R

class AddSellActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_sell_activity)
        setTitle(R.string.sell_your_ticket)
    }

}