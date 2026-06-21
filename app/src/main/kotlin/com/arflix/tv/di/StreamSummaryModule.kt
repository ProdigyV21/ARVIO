package com.arflix.tv.di

import com.arflix.tv.data.repository.StreamRepositorySummaryResolver
import com.arflix.tv.data.repository.StreamSummaryResolver
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class StreamSummaryModule {
    @Binds
    @Singleton
    abstract fun bindStreamSummaryResolver(
        resolver: StreamRepositorySummaryResolver
    ): StreamSummaryResolver
}
