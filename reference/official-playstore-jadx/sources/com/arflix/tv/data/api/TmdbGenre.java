package com.arflix.tv.data.api;

import androidx.media3.extractor.text.ttml.TtmlNode;
import io.ktor.http.ContentDisposition;
import kotlin.Metadata;
import kotlin.jvm.internal.p;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0087\b\u0018\u00002\u00020\u0001B\u001b\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\t\u0010\r\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0014\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0012\u001a\u00020\u0003HÖ\u0081\u0004J\n\u0010\u0013\u001a\u00020\u0005HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0014"}, d2 = {"Lcom/arflix/tv/data/api/TmdbGenre;", "", TtmlNode.ATTR_ID, "", ContentDisposition.Parameters.Name, "", "<init>", "(ILjava/lang/String;)V", "getId", "()I", "getName", "()Ljava/lang/String;", "component1", "component2", "copy", "equals", "", "other", "hashCode", "toString", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final /* data */ class TmdbGenre {
    public static final int $stable = 0;
    private final int id;
    private final String name;

    /* JADX WARN: Multi-variable type inference failed */
    public TmdbGenre() {
        this(0, null, 3, 0 == true ? 1 : 0);
    }

    public static /* synthetic */ TmdbGenre copy$default(TmdbGenre tmdbGenre, int i10, String str, int i11, Object obj) {
        if ((i11 & 1) != 0) {
            i10 = tmdbGenre.id;
        }
        if ((i11 & 2) != 0) {
            str = tmdbGenre.name;
        }
        return tmdbGenre.copy(i10, str);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final int getId() {
        return this.id;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getName() {
        return this.name;
    }

    public final TmdbGenre copy(int id, String name) {
        return new TmdbGenre(id, name);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof TmdbGenre)) {
            return false;
        }
        TmdbGenre tmdbGenre = (TmdbGenre) other;
        return this.id == tmdbGenre.id && p.a(this.name, tmdbGenre.name);
    }

    public final int getId() {
        return this.id;
    }

    public final String getName() {
        return this.name;
    }

    public int hashCode() {
        return this.name.hashCode() + (this.id * 31);
    }

    public String toString() {
        return "TmdbGenre(id=" + this.id + ", name=" + this.name + ")";
    }

    public TmdbGenre(int i10, String str) {
        this.id = i10;
        this.name = str;
    }

    public /* synthetic */ TmdbGenre(int i10, String str, int i11, kotlin.jvm.internal.h hVar) {
        this((i11 & 1) != 0 ? 0 : i10, (i11 & 2) != 0 ? "" : str);
    }
}
