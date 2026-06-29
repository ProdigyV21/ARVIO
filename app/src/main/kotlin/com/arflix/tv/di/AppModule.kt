package com.arflix.tv.di

import android.content.Context
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
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
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
    fun provideTmdbApi(okHttpClient: OkHttpClient, @dagger.hilt.android.qualifiers.ApplicationContext context: android.content.Context): TmdbApi {
        val tmdbClient = okHttpClient.newBuilder()
            .addInterceptor { chain ->
                val original = chain.request()
                val originalHttpUrl = original.url

                val langPrefs = context.getSharedPreferences("app_locale", android.content.Context.MODE_PRIVATE)
                val lang = langPrefs.getString("locale_tag", "en-US") ?: "en-US"

                // Only inject if it's not the default English. Map "iw" to "he".
                val urlBuilder = originalHttpUrl.newBuilder()
                if (lang != "en-US") {
                    val tmdbLang = lang.replace("iw", "he").replace('_', '-')
                    urlBuilder.setQueryParameter("language", tmdbLang)
                }

                val requestBuilder = original.newBuilder().url(urlBuilder.build())
                chain.proceed(requestBuilder.build())
            }
            .build()

        return Retrofit.Builder()
            .baseUrl(Constants.TMDB_BASE_URL)
            .client(tmdbClient)
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
        // Supabase API client without disk cache to prevent OkHttp from returning
        // cached responses for POST/upsert operations (which silently drops writes)
        val noCacheClient = okHttpClient.newBuilder()
            .cache(null)
            .build()
        return Retrofit.Builder()
            .baseUrl(Constants.SUPABASE_URL + "/")
            .client(noCacheClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(SupabaseApi::class.java)
    }

    @Provides
    @Singleton
    fun provideStreamApi(okHttpClient: OkHttpClient): StreamApi {
        // Base URL doesn't matter for dynamic URLs
        return Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/")
            .client(okHttpClient)
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

    @Provides
    @Singleton
    @Named("jikan")
    fun provideJikanRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://api.jikan.moe/v4/")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideJikanApi(@Named("jikan") retrofit: Retrofit): com.arflix.tv.data.api.JikanApi {
        return retrofit.create(com.arflix.tv.data.api.JikanApi::class.java)
    }
    @Provides
    @Singleton
    fun provideMoshi(): com.squareup.moshi.Moshi {
        return com.squareup.moshi.Moshi.Builder()
            .add(com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory())
            .build()
    }
}
