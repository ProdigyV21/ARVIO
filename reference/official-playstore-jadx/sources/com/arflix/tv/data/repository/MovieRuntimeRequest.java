package com.arflix.tv.data.repository;

import androidx.fragment.app.a2;
import io.ktor.http.LinkHeader;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u000e\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0087\b\u0018\u00002\u00020\u0001B!\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\u0004\b\u0007\u0010\bJ\t\u0010\u000f\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0010\u001a\u00020\u0003HÆ\u0003J\u0010\u0010\u0011\u001a\u0004\u0018\u00010\u0006HÆ\u0003¢\u0006\u0002\u0010\rJ.\u0010\u0012\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006HÆ\u0001¢\u0006\u0002\u0010\u0013J\u0014\u0010\u0014\u001a\u00020\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0017\u001a\u00020\u0006HÖ\u0081\u0004J\n\u0010\u0018\u001a\u00020\u0003HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\nR\u0015\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\n\n\u0002\u0010\u000e\u001a\u0004\b\f\u0010\r¨\u0006\u0019"}, d2 = {"Lcom/arflix/tv/data/repository/MovieRuntimeRequest;", "", "imdbId", "", LinkHeader.Parameters.Title, "year", "", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;)V", "getImdbId", "()Ljava/lang/String;", "getTitle", "getYear", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "component1", "component2", "component3", "copy", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;)Lcom/arflix/tv/data/repository/MovieRuntimeRequest;", "equals", "", "other", "hashCode", "toString", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final /* data */ class MovieRuntimeRequest {
    public static final int $stable = 0;
    private final String imdbId;
    private final String title;
    private final Integer year;

    public MovieRuntimeRequest(String str, String str2, Integer num) {
        this.imdbId = str;
        this.title = str2;
        this.year = num;
    }

    public static /* synthetic */ MovieRuntimeRequest copy$default(MovieRuntimeRequest movieRuntimeRequest, String str, String str2, Integer num, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            str = movieRuntimeRequest.imdbId;
        }
        if ((i10 & 2) != 0) {
            str2 = movieRuntimeRequest.title;
        }
        if ((i10 & 4) != 0) {
            num = movieRuntimeRequest.year;
        }
        return movieRuntimeRequest.copy(str, str2, num);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getImdbId() {
        return this.imdbId;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getTitle() {
        return this.title;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final Integer getYear() {
        return this.year;
    }

    public final MovieRuntimeRequest copy(String imdbId, String title, Integer year) {
        return new MovieRuntimeRequest(imdbId, title, year);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof MovieRuntimeRequest)) {
            return false;
        }
        MovieRuntimeRequest movieRuntimeRequest = (MovieRuntimeRequest) other;
        return kotlin.jvm.internal.p.a(this.imdbId, movieRuntimeRequest.imdbId) && kotlin.jvm.internal.p.a(this.title, movieRuntimeRequest.title) && kotlin.jvm.internal.p.a(this.year, movieRuntimeRequest.year);
    }

    public final String getImdbId() {
        return this.imdbId;
    }

    public final String getTitle() {
        return this.title;
    }

    public final Integer getYear() {
        return this.year;
    }

    public int hashCode() {
        int iC = androidx.compose.foundation.c.c(this.imdbId.hashCode() * 31, 31, this.title);
        Integer num = this.year;
        return iC + (num == null ? 0 : num.hashCode());
    }

    public String toString() {
        String str = this.imdbId;
        String str2 = this.title;
        Integer num = this.year;
        StringBuilder sbR = a2.r("MovieRuntimeRequest(imdbId=", str, ", title=", str2, ", year=");
        sbR.append(num);
        sbR.append(")");
        return sbR.toString();
    }
}
