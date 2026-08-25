package com.arflix.tv.data.repository;

import com.arflix.tv.data.api.TraktIds;
import com.arflix.tv.data.api.TraktWatchedMovie;
import com.arflix.tv.data.api.TraktWatchedShow;
import io.ktor.http.LinkHeader;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000>\n\u0000\n\u0002\u0010!\n\u0000\n\u0002\u0010#\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\u001aS\u0010\u000b\u001a\u00020\n\"\u0004\b\u0000\u0010\u00002\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00028\u00000\u00012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00028\u00000\u00062\u0012\u0010\t\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\u00040\bH\u0000¢\u0006\u0004\b\u000b\u0010\f\u001a\u0017\u0010\u000f\u001a\u00020\u00042\u0006\u0010\u000e\u001a\u00020\rH\u0000¢\u0006\u0004\b\u000f\u0010\u0010\u001a\u0017\u0010\u0012\u001a\u00020\u00042\u0006\u0010\u000e\u001a\u00020\u0011H\u0000¢\u0006\u0004\b\u0012\u0010\u0013\u001a'\u0010\u0018\u001a\u00020\u00042\u0006\u0010\u0014\u001a\u00020\u00042\u0006\u0010\u0016\u001a\u00020\u00152\u0006\u0010\u0017\u001a\u00020\u0004H\u0002¢\u0006\u0004\b\u0018\u0010\u0019\u001a\u0015\u0010\u001a\u001a\u00020\u0004*\u0004\u0018\u00010\nH\u0002¢\u0006\u0004\b\u001a\u0010\u001b¨\u0006\u001c"}, d2 = {"T", "", "target", "", "", "seen", "", "pageItems", "Lkotlin/Function1;", "identity", "", "appendUniqueTraktPage", "(Ljava/util/List;Ljava/util/Set;Ljava/util/List;Lr7/l;)I", "Lcom/arflix/tv/data/api/TraktWatchedMovie;", "item", "watchedMovieIdentity", "(Lcom/arflix/tv/data/api/TraktWatchedMovie;)Ljava/lang/String;", "Lcom/arflix/tv/data/api/TraktWatchedShow;", "watchedShowIdentity", "(Lcom/arflix/tv/data/api/TraktWatchedShow;)Ljava/lang/String;", LinkHeader.Parameters.Type, "Lcom/arflix/tv/data/api/TraktIds;", "ids", "fallback", "traktMediaIdentity", "(Ljava/lang/String;Lcom/arflix/tv/data/api/TraktIds;Ljava/lang/String;)Ljava/lang/String;", "orEmptyYear", "(Ljava/lang/Integer;)Ljava/lang/String;", "app_playRelease"}, k = 2, mv = {2, 3, 0}, xi = 48)
public final class TraktPaginationKt {
    /* JADX WARN: Multi-variable type inference failed */
    public static final <T> int appendUniqueTraktPage(List<T> list, Set<String> set, List<? extends T> list2, r7.l<? super T, String> lVar) {
        int i10 = 0;
        for (T t2 : list2) {
            if (set.add(lVar.invoke(t2))) {
                list.add(t2);
                i10++;
            }
        }
        return i10;
    }

    private static final String orEmptyYear(Integer num) {
        String strValueOf = num != null ? String.valueOf(num.intValue()) : null;
        return strValueOf == null ? "" : strValueOf;
    }

    private static final String traktMediaIdentity(String str, TraktIds traktIds, String str2) {
        if (traktIds.getTrakt() != null) {
            return str + ":trakt:" + traktIds.getTrakt();
        }
        if (traktIds.getTmdb() != null) {
            return str + ":tmdb:" + traktIds.getTmdb();
        }
        if (traktIds.getTvdb() != null) {
            return str + ":tvdb:" + traktIds.getTvdb();
        }
        String imdb = traktIds.getImdb();
        if (imdb != null && !kotlin.text.o.h0(imdb)) {
            return androidx.compose.foundation.c.t(str, ":imdb:", traktIds.getImdb());
        }
        String slug = traktIds.getSlug();
        return (slug == null || kotlin.text.o.h0(slug)) ? androidx.compose.foundation.c.t(str, ":fallback:", str2) : androidx.compose.foundation.c.t(str, ":slug:", traktIds.getSlug());
    }

    public static final String watchedMovieIdentity(TraktWatchedMovie traktWatchedMovie) {
        return traktMediaIdentity("movie", traktWatchedMovie.getMovie().getIds(), androidx.compose.foundation.c.t(traktWatchedMovie.getMovie().getTitle(), ":", orEmptyYear(traktWatchedMovie.getMovie().getYear())));
    }

    public static final String watchedShowIdentity(TraktWatchedShow traktWatchedShow) {
        return traktMediaIdentity("show", traktWatchedShow.getShow().getIds(), androidx.compose.foundation.c.t(traktWatchedShow.getShow().getTitle(), ":", orEmptyYear(traktWatchedShow.getShow().getYear())));
    }
}
