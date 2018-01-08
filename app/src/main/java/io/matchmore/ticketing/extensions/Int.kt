package io.matchmore.ticketing.extensions

fun Int.kmToM() = (this * 1000).toDouble()
fun Int.daysToSec() = (this * 60 * 24).toDouble()