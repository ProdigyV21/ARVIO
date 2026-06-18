package com.arflix.tv.di

import com.arflix.tv.data.api.TmdbApi
import com.arflix.tv.data.repository.MediaRepository
import com.arflix.tv.data.repository.ProfileManager
import com.arflix.tv.data.repository.ProfileRepository
import com.arflix.tv.data.repository.StreamRepository
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@EntryPoint
@InstallIn(SingletonComponent::class)
interface RepositoryAccessEntryPoint {
    fun streamRepository(): StreamRepository
    fun mediaRepository(): MediaRepository
    fun profileRepository(): ProfileRepository
    fun profileManager(): ProfileManager
    fun tmdbApi(): TmdbApi
}
