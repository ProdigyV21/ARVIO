package com.arflix.tv.di

import android.content.Context
import androidx.room.Room
import androidx.work.WorkManager
import com.arflix.tv.data.db.ArflixDatabase
import com.arflix.tv.data.db.DownloadDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): ArflixDatabase =
        Room.databaseBuilder(context, ArflixDatabase::class.java, ArflixDatabase.DATABASE_NAME)
            .fallbackToDestructiveMigration()
            .build()

    @Provides
    fun provideDownloadDao(db: ArflixDatabase): DownloadDao = db.downloadDao()

    @Provides
    @Singleton
    fun provideWorkManager(@ApplicationContext context: Context): WorkManager =
        WorkManager.getInstance(context)
}
