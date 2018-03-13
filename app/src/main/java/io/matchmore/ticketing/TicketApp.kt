package io.matchmore.ticketing

import android.app.Application
import io.matchmore.sdk.MatchMore
import io.matchmore.sdk.MatchMoreConfig

class TicketApp : Application() {
    override fun onCreate() {
        super.onCreate()
        MatchMore.config(this, getString(R.string.api_key),true)
    }

}