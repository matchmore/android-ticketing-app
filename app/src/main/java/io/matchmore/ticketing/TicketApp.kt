package io.matchmore.ticketing

import android.app.Application
import io.matchmore.sdk.MatchMore
import io.matchmore.sdk.MatchMoreConfig

class TicketApp : Application() {

    override fun onCreate() {
        super.onCreate()
        val config = MatchMoreConfig(this,
                getString(R.string.api_key),
                getString(R.string.world_id),
                debugLog = true)
        MatchMore.config(config)
    }

}