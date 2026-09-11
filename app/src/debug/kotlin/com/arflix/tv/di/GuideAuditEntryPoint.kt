package com.arflix.tv.di

import com.arflix.tv.data.repository.IptvRepository
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@EntryPoint
@InstallIn(SingletonComponent::class)
interface GuideAuditEntryPoint {
    fun iptvRepository(): IptvRepository
    fun sportsRepository(): com.arflix.tv.data.repository.SportsRepository
}
