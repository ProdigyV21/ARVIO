package com.arflix.tv.data.repository;

import io.ktor.http.LinkHeader;
import java.util.Locale;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\bÁ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J;\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00052\b\u0010\u0007\u001a\u0004\u0018\u00010\b2\b\u0010\t\u001a\u0004\u0018\u00010\u00052\b\u0010\n\u001a\u0004\u0018\u00010\b2\b\u0010\u000b\u001a\u0004\u0018\u00010\b¢\u0006\u0002\u0010\f¨\u0006\r"}, d2 = {"Lcom/arflix/tv/data/repository/HomeServerSourceCacheKey;", "", "<init>", "()V", "contentIdentity", "", LinkHeader.Parameters.Title, "year", "", "imdbId", "tmdbId", "tvdbId", "(Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/Integer;)Ljava/lang/String;", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class HomeServerSourceCacheKey {
    public static final int $stable = 0;
    public static final HomeServerSourceCacheKey INSTANCE = new HomeServerSourceCacheKey();

    private HomeServerSourceCacheKey() {
    }

    public final String contentIdentity(String title, Integer year, String imdbId, Integer tmdbId, Integer tvdbId) {
        String string;
        if (imdbId != null && (string = kotlin.text.o.L0(imdbId).toString()) != null) {
            String lowerCase = string.toLowerCase(Locale.US);
            if (kotlin.text.o.h0(lowerCase)) {
                lowerCase = null;
            }
            if (lowerCase != null) {
                return "imdb:".concat(lowerCase);
            }
        }
        if (tmdbId != null) {
            if (tmdbId.intValue() <= 0) {
                tmdbId = null;
            }
            if (tmdbId != null) {
                return a0.c.i(tmdbId.intValue(), "tmdb:");
            }
        }
        if (tvdbId != null) {
            if (tvdbId.intValue() <= 0) {
                tvdbId = null;
            }
            if (tvdbId != null) {
                return a0.c.i(tvdbId.intValue(), "tvdb:");
            }
        }
        String strNormalizeTitle = HomeServerMatcher.INSTANCE.normalizeTitle(title);
        String strValueOf = year != null ? String.valueOf(year.intValue()) : null;
        if (strValueOf == null) {
            strValueOf = "";
        }
        return androidx.compose.material3.d.n("title:", strNormalizeTitle, ":", strValueOf);
    }
}
