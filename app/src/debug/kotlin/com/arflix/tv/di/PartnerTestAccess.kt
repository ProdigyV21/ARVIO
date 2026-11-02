package com.arflix.tv.di

import com.arflix.tv.data.repository.*
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@EntryPoint
@InstallIn(SingletonComponent::class)
interface PartnerTestAccess {
    fun auth(): AuthRepository
    fun partner(): PartnerDeliveryRepository
    fun catalogs(): CatalogRepository
    fun iptv(): IptvRepository
    fun media(): MediaRepository
    fun profiles(): ProfileManager
    fun preferences(): PartnerPreferences
    fun streams(): StreamRepository
}
