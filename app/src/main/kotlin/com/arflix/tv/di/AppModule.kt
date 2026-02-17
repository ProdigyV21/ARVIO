package com.arflix.tv.di

import android.content.Context
import coil.Coil
import coil.ImageLoader
import com.arflix.tv.data.api.AniSkipApi
import com.arflix.tv.data.api.ArmApi
import com.arflix.tv.data.api.IntroDbApi
import com.arflix.tv.data.api.StreamApi
import com.arflix.tv.data.api.SupabaseApi
import com.arflix.tv.data.api.TmdbApi
import com.arflix.tv.data.api.TraktApi
import com.arflix.tv.network.OkHttpProvider
import com.arflix.tv.util.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpProvider.client
    }

    @Provides
    @Singleton
    fun provideImageLoader(
        @ApplicationContext context: Context
    ): ImageLoader {
        return Coil.imageLoader(context)
    }
    
    @Provides
    @Singleton
    fun provideTmdbApi(okHttpClient: OkHttpClient): TmdbApi {
        return Retrofit.Builder()
            .baseUrl(Constants.TMDB_BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(TmdbApi::class.java)
    }
    
    @Provides
    @Singleton
    fun provideTraktApi(okHttpClient: OkHttpClient): TraktApi {
        return Retrofit.Builder()
            .baseUrl(Constants.TRAKT_API_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(TraktApi::class.java)
    }
    
    @Provides
    @Singleton
    fun provideSupabaseApi(okHttpClient: OkHttpClient): SupabaseApi {
        return Retrofit.Builder()
            .baseUrl(Constants.SUPABASE_URL + "/")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(SupabaseApi::class.java)
    }
    
    @Provides
    @Singleton
    fun provideStreamApi(okHttpClient: OkHttpClient): StreamApi {
        val streamClient = okHttpClient.newBuilder()
            .connectTimeout(8, TimeUnit.SECONDS)
            .readTimeout(8, TimeUnit.SECONDS)
            .writeTimeout(8, TimeUnit.SECONDS)
            .callTimeout(9, TimeUnit.SECONDS)
            .retryOnConnectionFailure(false)
            .build()

        // Base URL doesn't matter for dynamic URLs
        return Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/")
            .client(streamClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(StreamApi::class.java)
    }

    // Skip intro providers (IntroDB + AniSkip + ARM).

    @Provides
    @Singleton
    @Named("introDb")
    fun provideIntroDbRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://api.introdb.app/")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideIntroDbApi(@Named("introDb") retrofit: Retrofit): IntroDbApi {
        return retrofit.create(IntroDbApi::class.java)
    }

    @Provides
    @Singleton
    @Named("aniSkip")
    fun provideAniSkipRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://api.aniskip.com/v2/")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideAniSkipApi(@Named("aniSkip") retrofit: Retrofit): AniSkipApi {
        return retrofit.create(AniSkipApi::class.java)
    }

    @Provides
    @Singleton
    @Named("arm")
    fun provideArmRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://arm.haglund.dev/api/v2/")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideArmApi(@Named("arm") retrofit: Retrofit): ArmApi {
        return retrofit.create(ArmApi::class.java)
    }
}
