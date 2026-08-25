package com.arflix.tv.data.api;

import androidx.fragment.app.a2;
import androidx.media3.extractor.text.ttml.TtmlNode;
import io.ktor.http.ContentDisposition;
import io.ktor.http.LinkHeader;
import kotlin.Metadata;
import kotlin.jvm.internal.p;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0010\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0087\b\u0018\u00002\u00020\u0001B/\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007\u0012\b\b\u0002\u0010\b\u001a\u00020\u0007¢\u0006\u0004\b\t\u0010\nJ\t\u0010\u0012\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0013\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0014\u001a\u00020\u0007HÆ\u0003J\t\u0010\u0015\u001a\u00020\u0007HÆ\u0003J1\u0010\u0016\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\u0007HÆ\u0001J\u0014\u0010\u0017\u001a\u00020\u00182\b\u0010\u0019\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u001a\u001a\u00020\u0003HÖ\u0081\u0004J\n\u0010\u001b\u001a\u00020\u0007HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\b\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0010¨\u0006\u001c"}, d2 = {"Lcom/arflix/tv/data/api/TmdbFindItem;", "", TtmlNode.ATTR_ID, "", "popularity", "", LinkHeader.Parameters.Title, "", ContentDisposition.Parameters.Name, "<init>", "(IFLjava/lang/String;Ljava/lang/String;)V", "getId", "()I", "getPopularity", "()F", "getTitle", "()Ljava/lang/String;", "getName", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "hashCode", "toString", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final /* data */ class TmdbFindItem {
    public static final int $stable = 0;
    private final int id;
    private final String name;
    private final float popularity;
    private final String title;

    public TmdbFindItem() {
        this(0, 0.0f, null, null, 15, null);
    }

    public static /* synthetic */ TmdbFindItem copy$default(TmdbFindItem tmdbFindItem, int i10, float f10, String str, String str2, int i11, Object obj) {
        if ((i11 & 1) != 0) {
            i10 = tmdbFindItem.id;
        }
        if ((i11 & 2) != 0) {
            f10 = tmdbFindItem.popularity;
        }
        if ((i11 & 4) != 0) {
            str = tmdbFindItem.title;
        }
        if ((i11 & 8) != 0) {
            str2 = tmdbFindItem.name;
        }
        return tmdbFindItem.copy(i10, f10, str, str2);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final int getId() {
        return this.id;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final float getPopularity() {
        return this.popularity;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final String getTitle() {
        return this.title;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final String getName() {
        return this.name;
    }

    public final TmdbFindItem copy(int id, float popularity, String title, String name) {
        return new TmdbFindItem(id, popularity, title, name);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof TmdbFindItem)) {
            return false;
        }
        TmdbFindItem tmdbFindItem = (TmdbFindItem) other;
        return this.id == tmdbFindItem.id && Float.compare(this.popularity, tmdbFindItem.popularity) == 0 && p.a(this.title, tmdbFindItem.title) && p.a(this.name, tmdbFindItem.name);
    }

    public final int getId() {
        return this.id;
    }

    public final String getName() {
        return this.name;
    }

    public final float getPopularity() {
        return this.popularity;
    }

    public final String getTitle() {
        return this.title;
    }

    public int hashCode() {
        return this.name.hashCode() + androidx.compose.foundation.c.c(a0.c.b(this.popularity, this.id * 31, 31), 31, this.title);
    }

    public String toString() {
        int i10 = this.id;
        float f10 = this.popularity;
        String str = this.title;
        String str2 = this.name;
        StringBuilder sb2 = new StringBuilder("TmdbFindItem(id=");
        sb2.append(i10);
        sb2.append(", popularity=");
        sb2.append(f10);
        sb2.append(", title=");
        return a2.n(sb2, str, ", name=", str2, ")");
    }

    public TmdbFindItem(int i10, float f10, String str, String str2) {
        this.id = i10;
        this.popularity = f10;
        this.title = str;
        this.name = str2;
    }

    public /* synthetic */ TmdbFindItem(int i10, float f10, String str, String str2, int i11, kotlin.jvm.internal.h hVar) {
        this((i11 & 1) != 0 ? 0 : i10, (i11 & 2) != 0 ? 0.0f : f10, (i11 & 4) != 0 ? "" : str, (i11 & 8) != 0 ? "" : str2);
    }
}
