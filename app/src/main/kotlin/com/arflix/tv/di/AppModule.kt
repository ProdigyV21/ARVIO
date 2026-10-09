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
    private val simklRateLimiter = com.arflix.tv.network.sharedSimklRateLimiter

    @Provides
    @Singleton
    @JvmStatic
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpProvider.client
    }

    @Provides
    @Singleton
    @JvmStatic
    fun provideTmdbApi(okHttpClient: OkHttpClient, @dagger.hilt.android.qualifiers.ApplicationContext context: android.content.Context): TmdbApi {
        val tmdbClient = okHttpClient.newBuilder()
            .addInterceptor { chain ->
                val original = chain.request()
                val originalHttpUrl = original.url

                val langPrefs = context.getSharedPreferences("app_locale", android.content.Context.MODE_PRIVATE)
                val lang = langPrefs.getString("locale_tag", "en-US") ?: "en-US"

                // Only inject if it's not the default English. Map "iw" to "he".
                //
                // An explicit `language` from the call site wins. setQueryParameter used to
                // overwrite it, which silently broke every deliberate request for a specific
                // language: the English fallback in getTrailerKey re-sent the user's language and
                // returned the same empty result (TMDB's /videos `language` filters the video
                // records, so a Hebrew user saw no trailers at all), and fetchTitles' explicit
                // language="en" came back localized.
                val urlBuilder = originalHttpUrl.newBuilder()
                if (lang != "en-US" && originalHttpUrl.queryParameter("language") == null) {
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
    @JvmStatic
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
    @Named("traktAuth")
    fun provideTraktAuthApi(okHttpClient: OkHttpClient): TraktApi {
        // Device activation must not queue behind Home's metadata/sync requests.
        val client = okHttpClient.newBuilder()
            .dispatcher(okhttp3.Dispatcher())
            .callTimeout(20, java.util.concurrent.TimeUnit.SECONDS)
            .build()
        return Retrofit.Builder()
            .baseUrl(Constants.TRAKT_API_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(TraktApi::class.java)
    }

    @Provides
    @Singleton
    @JvmStatic
    fun provideMdbListApi(okHttpClient: OkHttpClient): com.arflix.tv.data.api.MdbListApi {
        return Retrofit.Builder()
            .baseUrl(Constants.MDBLIST_API_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(com.arflix.tv.data.api.MdbListApi::class.java)
    }

    @Provides
    @Singleton
    @JvmStatic
    fun provideSimklApi(
        okHttpClient: OkHttpClient,
        simklV2: com.arflix.tv.data.repository.simkl.SimklListsRepository,
        @dagger.hilt.android.qualifiers.ApplicationContext context: android.content.Context
    ): com.arflix.tv.data.api.SimklApi {
        val simklClient = okHttpClient.newBuilder()
            .addInterceptor { chain ->
                val original = chain.request()
                val token = original.header("Authorization")?.removePrefix("Bearer ")
                if (token?.startsWith("simkl_at_") != true) return@addInterceptor chain.proceed(original)
                val profile = simklV2.profileId()
                val connection = simklV2.connectionId()
                val ownsToken = simklV2.ownsAccessToken(token)
                val response = chain.proceed(original)
                if (response.code != 401 || !ownsToken) return@addInterceptor response
                response.close()
                val refreshed = kotlinx.coroutines.runBlocking { simklV2.refreshRejectedToken(token, profile, connection) }
                chain.proceed(original.newBuilder().header("Authorization", "Bearer $refreshed").build())
            }
            .addInterceptor { chain ->
                val original = chain.request()

                val originalUrl = original.url
                val urlBuilder = originalUrl.newBuilder()
                val rawVersion = com.arflix.tv.BuildConfig.VERSION_NAME
                val cleanVersion = rawVersion.substringBefore("-")

                val clientId = if (original.header("Authorization")?.startsWith("Bearer simkl_at_") == true)
                    Constants.SIMKL_V2_CLIENT_ID else Constants.SIMKL_CLIENT_ID
                if (clientId.isNotBlank()) {
                    urlBuilder.setQueryParameter("client_id", clientId)
                }
                urlBuilder.setQueryParameter("app-name", "arvio")
                urlBuilder.setQueryParameter("app-version", cleanVersion)

                val requestBuilder = original.newBuilder()
                    .url(urlBuilder.build())
                    .header("User-Agent", OkHttpProvider.getAppUserAgent(context))

                if (clientId.isNotBlank()) {
                    requestBuilder.header("simkl-api-key", clientId)
                }

                if (original.method.equals("POST", ignoreCase = true) && original.header("Content-Type") == null) {
                    requestBuilder.header("Content-Type", "application/json")
                }


                val response = chain.proceed(requestBuilder.build())
                if (response.code == 412) {
                    com.arflix.tv.util.AppLogger.e("SimklApi", "HTTP 412 Precondition Failed / client_id_failed from Simkl. Check API key.")
                }
                response
            }
            .addInterceptor(simklRateLimiter)
            .build()

        return Retrofit.Builder()
            .baseUrl(Constants.SIMKL_BASE_URL)
            .client(simklClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(com.arflix.tv.data.api.SimklApi::class.java)
    }

    @Provides
    @Singleton
    @JvmStatic
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
    @JvmStatic
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
    @JvmStatic
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
    @JvmStatic
    fun provideIntroDbApi(@Named("introDb") retrofit: Retrofit): IntroDbApi {
        return retrofit.create(IntroDbApi::class.java)
    }

    @Provides
    @Singleton
    @JvmStatic
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
    @JvmStatic
    fun provideAniSkipApi(@Named("aniSkip") retrofit: Retrofit): AniSkipApi {
        return retrofit.create(AniSkipApi::class.java)
    }

    @Provides
    @Singleton
    @JvmStatic
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
    @JvmStatic
    fun provideArmApi(@Named("arm") retrofit: Retrofit): ArmApi {
        return retrofit.create(ArmApi::class.java)
    }

    @Provides
    @Singleton
    @JvmStatic
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
    @JvmStatic
    fun provideJikanApi(@Named("jikan") retrofit: Retrofit): com.arflix.tv.data.api.JikanApi {
        return retrofit.create(com.arflix.tv.data.api.JikanApi::class.java)
    }

    @Provides
    @Singleton
    @JvmStatic
    fun provideMoshi(): com.squareup.moshi.Moshi {
        return com.squareup.moshi.Moshi.Builder()
            .add(com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory())
            .build()
    }
}
