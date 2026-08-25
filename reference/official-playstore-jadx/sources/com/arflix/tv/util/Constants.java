package com.arflix.tv.util;

import androidx.compose.material3.d;
import com.arflix.tv.BuildConfig;
import kotlin.Metadata;
import kotlin.text.o;
import kotlin.text.u;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b;\n\u0002\u0010\b\n\u0002\b\b\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u00108\u001a\u00020\u00052\u0006\u00109\u001a\u00020\u0005H\u0002R\u0011\u0010\u0004\u001a\u00020\u00058F¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\b\u001a\u00020\u00058F¢\u0006\u0006\u001a\u0004\b\t\u0010\u0007R\u0011\u0010\n\u001a\u00020\u00058F¢\u0006\u0006\u001a\u0004\b\u000b\u0010\u0007R\u0011\u0010\f\u001a\u00020\u00058F¢\u0006\u0006\u001a\u0004\b\r\u0010\u0007R\u0011\u0010\u000e\u001a\u00020\u000f8F¢\u0006\u0006\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\u0012\u001a\u00020\u00058F¢\u0006\u0006\u001a\u0004\b\u0013\u0010\u0007R\u0011\u0010\u0014\u001a\u00020\u00058F¢\u0006\u0006\u001a\u0004\b\u0015\u0010\u0007R\u0011\u0010\u0016\u001a\u00020\u00058F¢\u0006\u0006\u001a\u0004\b\u0017\u0010\u0007R\u0011\u0010\u0018\u001a\u00020\u00058F¢\u0006\u0006\u001a\u0004\b\u0019\u0010\u0007R\u0011\u0010\u001a\u001a\u00020\u00058F¢\u0006\u0006\u001a\u0004\b\u001b\u0010\u0007R\u0011\u0010\u001c\u001a\u00020\u00058F¢\u0006\u0006\u001a\u0004\b\u001d\u0010\u0007R\u0011\u0010\u001e\u001a\u00020\u00058F¢\u0006\u0006\u001a\u0004\b\u001f\u0010\u0007R\u0011\u0010 \u001a\u00020\u00058F¢\u0006\u0006\u001a\u0004\b!\u0010\u0007R\u0011\u0010\"\u001a\u00020\u00058F¢\u0006\u0006\u001a\u0004\b#\u0010\u0007R\u0011\u0010$\u001a\u00020\u00058F¢\u0006\u0006\u001a\u0004\b%\u0010\u0007R\u0011\u0010&\u001a\u00020\u00058F¢\u0006\u0006\u001a\u0004\b'\u0010\u0007R\u0011\u0010(\u001a\u00020\u00058F¢\u0006\u0006\u001a\u0004\b)\u0010\u0007R\u0011\u0010*\u001a\u00020\u00058F¢\u0006\u0006\u001a\u0004\b+\u0010\u0007R\u0011\u0010,\u001a\u00020\u00058F¢\u0006\u0006\u001a\u0004\b-\u0010\u0007R\u0011\u0010.\u001a\u00020\u00058F¢\u0006\u0006\u001a\u0004\b/\u0010\u0007R\u0011\u00100\u001a\u00020\u00058F¢\u0006\u0006\u001a\u0004\b1\u0010\u0007R\u0011\u00102\u001a\u00020\u00058F¢\u0006\u0006\u001a\u0004\b3\u0010\u0007R\u000e\u00104\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u00105\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u00106\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u00107\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u0011\u0010:\u001a\u00020\u00058F¢\u0006\u0006\u001a\u0004\b;\u0010\u0007R\u0011\u0010<\u001a\u00020\u00058F¢\u0006\u0006\u001a\u0004\b=\u0010\u0007R\u0011\u0010>\u001a\u00020\u00058F¢\u0006\u0006\u001a\u0004\b?\u0010\u0007R\u0011\u0010@\u001a\u00020\u00058F¢\u0006\u0006\u001a\u0004\bA\u0010\u0007R\u000e\u0010B\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010C\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010D\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010E\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010F\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010G\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u0011\u0010H\u001a\u00020\u00058F¢\u0006\u0006\u001a\u0004\bI\u0010\u0007R\u000e\u0010J\u001a\u00020KX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010L\u001a\u00020KX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010M\u001a\u00020KX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010N\u001a\u00020KX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010O\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010P\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010Q\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010R\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000¨\u0006S"}, d2 = {"Lcom/arflix/tv/util/Constants;", "", "<init>", "()V", "SUPABASE_URL", "", "getSUPABASE_URL", "()Ljava/lang/String;", "SUPABASE_ANON_KEY", "getSUPABASE_ANON_KEY", "APP_ANON_KEY", "getAPP_ANON_KEY", "NETLIFY_BACKEND_URL", "getNETLIFY_BACKEND_URL", "USE_NETLIFY_CLOUD_SYNC", "", "getUSE_NETLIFY_CLOUD_SYNC", "()Z", "TMDB_PROXY_URL", "getTMDB_PROXY_URL", "SIMKL_PROXY_URL", "getSIMKL_PROXY_URL", "TV_AUTH_START_URL", "getTV_AUTH_START_URL", "TV_AUTH_STATUS_URL", "getTV_AUTH_STATUS_URL", "TV_AUTH_POLL_URL", "getTV_AUTH_POLL_URL", "TV_AUTH_COMPLETE_URL", "getTV_AUTH_COMPLETE_URL", "DISCORD_AUTH_START_URL", "getDISCORD_AUTH_START_URL", "DISCORD_AUTH_STATUS_URL", "getDISCORD_AUTH_STATUS_URL", "AUTH_LOGIN_URL", "getAUTH_LOGIN_URL", "AUTH_REFRESH_URL", "getAUTH_REFRESH_URL", "AUTH_PASSWORD_START_URL", "getAUTH_PASSWORD_START_URL", "CLOUD_AUTH_EMAIL_URL", "getCLOUD_AUTH_EMAIL_URL", "NETLIFY_ACCOUNT_SYNC_PULL_URL", "getNETLIFY_ACCOUNT_SYNC_PULL_URL", "NETLIFY_ACCOUNT_SYNC_PUSH_URL", "getNETLIFY_ACCOUNT_SYNC_PUSH_URL", "NETLIFY_ACCOUNT_SYNC_CURSOR_URL", "getNETLIFY_ACCOUNT_SYNC_CURSOR_URL", "NETLIFY_ACCOUNT_SYNC_DELTA_URL", "getNETLIFY_ACCOUNT_SYNC_DELTA_URL", "APP_USAGE_EVENT_URL", "getAPP_USAGE_EVENT_URL", "TMDB_BASE_URL", "TRAKT_API_URL", "SIMKL_BASE_URL", "MDBLIST_API_URL", "usableSecret", "value", "TMDB_API_KEY", "getTMDB_API_KEY", "TRAKT_CLIENT_ID", "getTRAKT_CLIENT_ID", "TRAKT_CLIENT_SECRET", "getTRAKT_CLIENT_SECRET", "SIMKL_CLIENT_ID", "getSIMKL_CLIENT_ID", "IMAGE_BASE", "IMAGE_BASE_LARGE", "BACKDROP_BASE", "BACKDROP_BASE_LARGE", "LOGO_BASE", "LOGO_BASE_LARGE", "GOOGLE_WEB_CLIENT_ID", "getGOOGLE_WEB_CLIENT_ID", "WATCHED_THRESHOLD", "", "MIN_PROGRESS_THRESHOLD", "MAX_PROGRESS_ENTRIES", "MAX_CONTINUE_WATCHING", "PREFS_NAME", "PREF_DEFAULT_SUBTITLE", "PREF_AUTO_PLAY_NEXT", "PREF_TRAKT_TOKEN", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class Constants {
    public static final int $stable = 0;
    public static final String BACKDROP_BASE = "https://image.tmdb.org/t/p/w1280";
    public static final String BACKDROP_BASE_LARGE = "https://image.tmdb.org/t/p/original";
    public static final String IMAGE_BASE = "https://image.tmdb.org/t/p/w780";
    public static final String IMAGE_BASE_LARGE = "https://image.tmdb.org/t/p/w1280";
    public static final Constants INSTANCE = new Constants();
    public static final String LOGO_BASE = "https://image.tmdb.org/t/p/w500";
    public static final String LOGO_BASE_LARGE = "https://image.tmdb.org/t/p/original";
    public static final int MAX_CONTINUE_WATCHING = 50;
    public static final int MAX_PROGRESS_ENTRIES = 50;
    public static final String MDBLIST_API_URL = "https://api.mdblist.com/";
    public static final int MIN_PROGRESS_THRESHOLD = 3;
    public static final String PREFS_NAME = "arflix_prefs";
    public static final String PREF_AUTO_PLAY_NEXT = "auto_play_next";
    public static final String PREF_DEFAULT_SUBTITLE = "default_subtitle";
    public static final String PREF_TRAKT_TOKEN = "trakt_token";
    public static final String SIMKL_BASE_URL = "https://api.simkl.com/";
    public static final String TMDB_BASE_URL = "https://api.themoviedb.org/3/";
    public static final String TRAKT_API_URL = "https://api.trakt.tv/";
    public static final int WATCHED_THRESHOLD = 90;

    private Constants() {
    }

    private final String usableSecret(String value) {
        if (o.h0(value) || u.P(value, "your-", true)) {
            value = null;
        }
        return value == null ? "" : value;
    }

    public final String getAPP_ANON_KEY() {
        return "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6InpyZHd2b3J0Y2Zub3lrbHR6dXFmIiwicm9sZSI6ImFub24iLCJpYXQiOjE3NjY3NDU4NzMsImV4cCI6MjA4MjMyMTg3M30.YfKZbSwxGs6_xMd6jkDtn1PKkfuyOHo9qVhUvFRddGU";
    }

    public final String getAPP_USAGE_EVENT_URL() {
        return d.m(getNETLIFY_BACKEND_URL(), "/app-usage-event");
    }

    public final String getAUTH_LOGIN_URL() {
        return d.m(getNETLIFY_BACKEND_URL(), "/auth-login");
    }

    public final String getAUTH_PASSWORD_START_URL() {
        return d.m(getNETLIFY_BACKEND_URL(), "/auth-password-start");
    }

    public final String getAUTH_REFRESH_URL() {
        return d.m(getNETLIFY_BACKEND_URL(), "/auth-refresh");
    }

    public final String getCLOUD_AUTH_EMAIL_URL() {
        return d.m(getNETLIFY_BACKEND_URL(), "/cloud-auth-email");
    }

    public final String getDISCORD_AUTH_START_URL() {
        return d.m(getNETLIFY_BACKEND_URL(), "/discord-auth-start");
    }

    public final String getDISCORD_AUTH_STATUS_URL() {
        return d.m(getNETLIFY_BACKEND_URL(), "/discord-auth-status");
    }

    public final String getGOOGLE_WEB_CLIENT_ID() {
        return BuildConfig.GOOGLE_WEB_CLIENT_ID;
    }

    public final String getNETLIFY_ACCOUNT_SYNC_CURSOR_URL() {
        return d.m(getNETLIFY_BACKEND_URL(), "/account-sync-cursor");
    }

    public final String getNETLIFY_ACCOUNT_SYNC_DELTA_URL() {
        return d.m(getNETLIFY_BACKEND_URL(), "/account-sync-delta");
    }

    public final String getNETLIFY_ACCOUNT_SYNC_PULL_URL() {
        return d.m(getNETLIFY_BACKEND_URL(), "/account-sync-pull");
    }

    public final String getNETLIFY_ACCOUNT_SYNC_PUSH_URL() {
        return d.m(getNETLIFY_BACKEND_URL(), "/account-sync-push");
    }

    public final String getNETLIFY_BACKEND_URL() {
        return o.O0(o.L0(BuildConfig.NETLIFY_BACKEND_URL).toString(), '/');
    }

    public final String getSIMKL_CLIENT_ID() {
        return usableSecret(BuildConfig.SIMKL_CLIENT_ID);
    }

    public final String getSIMKL_PROXY_URL() {
        return d.m(getNETLIFY_BACKEND_URL(), "/simkl-proxy");
    }

    public final String getSUPABASE_ANON_KEY() {
        return "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6InpyZHd2b3J0Y2Zub3lrbHR6dXFmIiwicm9sZSI6ImFub24iLCJpYXQiOjE3NjY3NDU4NzMsImV4cCI6MjA4MjMyMTg3M30.YfKZbSwxGs6_xMd6jkDtn1PKkfuyOHo9qVhUvFRddGU";
    }

    public final String getSUPABASE_URL() {
        return BuildConfig.SUPABASE_URL;
    }

    public final String getTMDB_API_KEY() {
        return usableSecret(BuildConfig.TMDB_API_KEY);
    }

    public final String getTMDB_PROXY_URL() {
        return d.m(getNETLIFY_BACKEND_URL(), "/tmdb-proxy");
    }

    public final String getTRAKT_CLIENT_ID() {
        return usableSecret(BuildConfig.TRAKT_CLIENT_ID);
    }

    public final String getTRAKT_CLIENT_SECRET() {
        return usableSecret(BuildConfig.TRAKT_CLIENT_SECRET);
    }

    public final String getTV_AUTH_COMPLETE_URL() {
        return d.m(getNETLIFY_BACKEND_URL(), "/tv-auth-complete");
    }

    public final String getTV_AUTH_POLL_URL() {
        return d.m(getNETLIFY_BACKEND_URL(), "/tv-auth-poll");
    }

    public final String getTV_AUTH_START_URL() {
        return d.m(getNETLIFY_BACKEND_URL(), "/tv-auth-start");
    }

    public final String getTV_AUTH_STATUS_URL() {
        return d.m(getNETLIFY_BACKEND_URL(), "/tv-auth-status");
    }

    public final boolean getUSE_NETLIFY_CLOUD_SYNC() {
        return BuildConfig.ENABLE_NETLIFY_CLOUD_SYNC.booleanValue() && (u.P(getNETLIFY_BACKEND_URL(), "https://", false) || u.P(getNETLIFY_BACKEND_URL(), "http://", false));
    }
}
