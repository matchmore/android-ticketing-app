package io.matchmore.ticketing

import android.app.Application
import io.matchmore.sdk.Matchmore

class TicketApp : Application() {
    override fun onCreate() {
        super.onCreate()
        Matchmore.config(this, getString(R.string.api_key),true)
    }

}