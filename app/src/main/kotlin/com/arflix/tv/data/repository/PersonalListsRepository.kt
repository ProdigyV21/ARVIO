package com.arflix.tv.data.repository

import com.arflix.tv.data.model.MediaType
import com.arflix.tv.data.repository.sync.MdbListConnection
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.first
import javax.inject.Inject
import javax.inject.Singleton

/**
 * The user's own lists on the connected tracking services, for "Add to list".
 *
 * Trakt and MDBList can both be connected; their lists are shown together, tagged with
 * the service they live on. Only lists that can take items are offered (MDBList's
 * dynamic, filter-based lists cannot).
 */
@Singleton
class PersonalListsRepository @Inject constructor(
    private val traktRepository: TraktRepository,
    private val mdbListRepository: MdbListRepository
) {
    enum class Provider(val label: String) { TRAKT("Trakt"), MDBLIST("MDBList") }

    data class UserList(val provider: Provider, val id: String, val title: String) {
        val key: String get() = "${provider.name}:$id"
    }

    /** A list and whether the title is already in it. */
    data class Choice(val list: UserList, val contains: Boolean)

    /** Whether any service with personal lists is connected for the active profile. */
    suspend fun hasProvider(): Boolean =
        traktRepository.isAuthenticated.first() || mdbListRepository.isConnected()

    /** Every list with the title's membership, loaded in parallel. */
    suspend fun choicesFor(mediaType: MediaType, tmdbId: Int): List<Choice> = coroutineScope {
        val trakt = async {
            if (!traktRepository.isAuthenticated.first()) return@async emptyList()
            traktRepository.getPersonalLists().map { list ->
                async {
                    val contains = traktRepository.getPersonalListItems(list.id)
                        .any { it.mediaType == mediaType && it.id == tmdbId }
                    Choice(UserList(Provider.TRAKT, list.id, list.title), contains)
                }
            }.awaitAll()
        }
        val mdb = async {
            if (!mdbListRepository.isConnected()) return@async emptyList()
            mdbListRepository.getUserLists().map { list ->
                async {
                    val contains = "${mediaType.name}:$tmdbId" in mdbListRepository.listItemKeys(list.id)
                    Choice(
                        UserList(Provider.MDBLIST, list.id.toString(), list.name ?: list.slug ?: "MDBList"),
                        contains
                    )
                }
            }.awaitAll()
        }
        trakt.await() + mdb.await()
    }

    /** An MDBList list for the Library; [path] is "user/slug", as in its mdblist.com URL. */
    data class MdbListLibraryList(val id: Long, val title: String, val path: String?)

    /** The active profile's MDBList connection, for screens that list its lists. */
    val mdbListConnection: Flow<MdbListConnection> get() = mdbListRepository.connection

    /**
     * Every MDBList list of the user, filter-based ones too, for the Library's "My lists".
     * Throws when MDBList cannot be read, so a failure is not shown as having no lists.
     */
    suspend fun mdbListLibraryLists(): List<MdbListLibraryList> =
        if (!mdbListRepository.isConnected()) emptyList()
        else mdbListRepository.libraryUserLists(includeDynamic = true).map { list ->
            MdbListLibraryList(
                id = list.id,
                title = list.name ?: list.slug ?: "MDBList",
                path = list.userName?.let { user -> list.slug?.let { "$user/$it" } }
            )
        }

    /** The titles of one MDBList list; throws when it cannot be read. */
    suspend fun mdbListItems(listId: Long) = mdbListRepository.libraryListItems(listId)

    private val _changes = MutableSharedFlow<UserList>(extraBufferCapacity = 8)

    /** Lists the app just edited, so screens showing them can reload at once. */
    val changes: SharedFlow<UserList> = _changes.asSharedFlow()

    /** Adds the title to [list] or removes it; true when the service accepted it. */
    suspend fun setMembership(list: UserList, mediaType: MediaType, tmdbId: Int, add: Boolean): Boolean {
        val ok = when (list.provider) {
            Provider.TRAKT -> traktRepository.modifyPersonalList(list.id, mediaType, tmdbId, add)
            Provider.MDBLIST -> list.id.toLongOrNull()
                ?.let { mdbListRepository.modifyList(it, mediaType, tmdbId, add) }
                ?: false
        }
        if (ok) {
            // The edited list must not be answered from a cached copy on its next load.
            bumpListFreshness()
            _changes.tryEmit(list)
        }
        return ok
    }
}
