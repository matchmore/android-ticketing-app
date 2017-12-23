package io.matchmore.ticketing.find

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import io.matchmore.ticketing.R

class AddFindActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_find_activity)
        setTitle(R.string.add_wanted_ticket)
    }

}