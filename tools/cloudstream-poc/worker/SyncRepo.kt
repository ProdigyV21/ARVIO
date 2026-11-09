package com.lagradost.cloudstream3.syncproviders

import com.lagradost.cloudstream3.syncproviders.providers.AniListApi

/** This isolated source resolver has no Cloudstream tracking account. Never impersonate a signed-in user. */
open class SyncAPI
class AuthUser
class SyncRepo(val api: SyncAPI) {
    fun authUser(): AuthUser? = null
}
class AccountManager {
    companion object { val aniListApi = AniListApi() }
}
