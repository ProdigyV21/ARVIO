package com.arflix.tv.music

import android.content.Context
import android.telephony.TelephonyManager
import java.util.Locale
import java.util.TimeZone

/**
 * The country the user is in, for the Music home's charts, without any permission or network
 * call: the mobile network's country, then the SIM's, then the time zone, then the system region.
 * A TV or tablet has no SIM, and a TV often keeps an "en-US" region it was never set to, so the
 * time zone (which the user does set) comes before the region.
 */
object DeviceCountry {

    fun detect(context: Context): String? {
        val telephony = runCatching { context.getSystemService(TelephonyManager::class.java) }.getOrNull()
        return pick(
            network = runCatching { telephony?.networkCountryIso }.getOrNull(),
            sim = runCatching { telephony?.simCountryIso }.getOrNull(),
            timeZone = TimeZone.getDefault().id,
            region = Locale.getDefault().country,
        )
    }

    internal fun pick(network: String?, sim: String?, timeZone: String?, region: String?): String? =
        listOf(network, sim, timeZone?.let { ZONES[it] }, region)
            .firstOrNull { it != null && it.length == 2 && it.all(Char::isLetter) }
            ?.uppercase()

    /** Time zones to their country; zones a country shares with others are left out. */
    internal val ZONES: Map<String, String> = mapOf(
        "Europe/Amsterdam" to "NL", "Europe/Brussels" to "BE", "America/Paramaribo" to "SR", "America/Curacao" to "CW",
        "America/Aruba" to "AW", "Europe/Berlin" to "DE", "Europe/Vienna" to "AT", "Europe/Zurich" to "CH",
        "Europe/Paris" to "FR", "Europe/Madrid" to "ES", "Atlantic/Canary" to "ES", "Europe/Rome" to "IT",
        "Europe/Lisbon" to "PT", "Europe/London" to "GB", "Europe/Dublin" to "IE", "Europe/Luxembourg" to "LU",
        "Europe/Copenhagen" to "DK", "Europe/Oslo" to "NO", "Europe/Stockholm" to "SE", "Europe/Helsinki" to "FI",
        "Europe/Warsaw" to "PL", "Europe/Prague" to "CZ", "Europe/Budapest" to "HU", "Europe/Bucharest" to "RO",
        "Europe/Sofia" to "BG", "Europe/Athens" to "GR", "Europe/Istanbul" to "TR", "Europe/Moscow" to "RU",
        "Europe/Kiev" to "UA", "Europe/Kyiv" to "UA", "Europe/Belgrade" to "RS", "Europe/Zagreb" to "HR",
        "America/New_York" to "US", "America/Chicago" to "US", "America/Denver" to "US", "America/Los_Angeles" to "US",
        "America/Phoenix" to "US", "America/Anchorage" to "US", "Pacific/Honolulu" to "US", "America/Detroit" to "US",
        "America/Toronto" to "CA", "America/Vancouver" to "CA", "America/Edmonton" to "CA", "America/Winnipeg" to "CA",
        "America/Halifax" to "CA", "America/Montreal" to "CA", "America/Mexico_City" to "MX", "America/Monterrey" to "MX",
        "America/Tijuana" to "MX", "America/Cancun" to "MX", "America/Bogota" to "CO", "America/Lima" to "PE",
        "America/Santiago" to "CL", "America/Argentina/Buenos_Aires" to "AR", "America/Buenos_Aires" to "AR",
        "America/Sao_Paulo" to "BR", "America/Caracas" to "VE", "America/Guayaquil" to "EC", "America/Montevideo" to "UY",
        "America/Port_of_Spain" to "TT", "America/Jamaica" to "JM", "America/Santo_Domingo" to "DO", "America/Puerto_Rico" to "PR",
        "Asia/Kolkata" to "IN", "Asia/Calcutta" to "IN", "Asia/Karachi" to "PK", "Asia/Dhaka" to "BD", "Asia/Tokyo" to "JP",
        "Asia/Seoul" to "KR", "Asia/Taipei" to "TW", "Asia/Hong_Kong" to "HK", "Asia/Shanghai" to "CN", "Asia/Singapore" to "SG",
        "Asia/Kuala_Lumpur" to "MY", "Asia/Jakarta" to "ID", "Asia/Manila" to "PH", "Asia/Bangkok" to "TH",
        "Asia/Ho_Chi_Minh" to "VN", "Asia/Saigon" to "VN", "Asia/Dubai" to "AE", "Asia/Riyadh" to "SA", "Asia/Qatar" to "QA",
        "Asia/Kuwait" to "KW", "Asia/Jerusalem" to "IL", "Asia/Tehran" to "IR", "Asia/Baghdad" to "IQ", "Asia/Beirut" to "LB",
        "Asia/Amman" to "JO", "Africa/Cairo" to "EG", "Africa/Casablanca" to "MA", "Africa/Algiers" to "DZ", "Africa/Tunis" to "TN",
        "Africa/Lagos" to "NG", "Africa/Accra" to "GH", "Africa/Nairobi" to "KE", "Africa/Johannesburg" to "ZA",
        "Australia/Sydney" to "AU", "Australia/Melbourne" to "AU", "Australia/Brisbane" to "AU", "Australia/Perth" to "AU",
        "Australia/Adelaide" to "AU", "Pacific/Auckland" to "NZ",
    )
}
