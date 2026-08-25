package com.arflix.tv.data.api;

import androidx.fragment.app.a2;
import androidx.media3.extractor.text.ttml.TtmlNode;
import com.google.gson.annotations.SerializedName;
import io.ktor.http.ContentDisposition;
import kotlin.Metadata;
import kotlin.jvm.internal.p;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0010\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0087\b\u0018\u00002\u00020\u0001B5\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0005¢\u0006\u0004\b\b\u0010\tJ\t\u0010\u0010\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\u0011\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010\u0012\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010\u0013\u001a\u0004\u0018\u00010\u0005HÆ\u0003J7\u0010\u0014\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0005HÆ\u0001J\u0014\u0010\u0015\u001a\u00020\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0018\u001a\u00020\u0003HÖ\u0081\u0004J\n\u0010\u0019\u001a\u00020\u0005HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0018\u0010\u0006\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\rR\u0018\u0010\u0007\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\r¨\u0006\u001a"}, d2 = {"Lcom/arflix/tv/data/api/TmdbCollectionRef;", "", TtmlNode.ATTR_ID, "", ContentDisposition.Parameters.Name, "", "posterPath", "backdropPath", "<init>", "(ILjava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getId", "()I", "getName", "()Ljava/lang/String;", "getPosterPath", "getBackdropPath", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "hashCode", "toString", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final /* data */ class TmdbCollectionRef {
    public static final int $stable = 0;

    @SerializedName("backdrop_path")
    private final String backdropPath;
    private final int id;
    private final String name;

    @SerializedName("poster_path")
    private final String posterPath;

    public TmdbCollectionRef() {
        this(0, null, null, null, 15, null);
    }

    public static /* synthetic */ TmdbCollectionRef copy$default(TmdbCollectionRef tmdbCollectionRef, int i10, String str, String str2, String str3, int i11, Object obj) {
        if ((i11 & 1) != 0) {
            i10 = tmdbCollectionRef.id;
        }
        if ((i11 & 2) != 0) {
            str = tmdbCollectionRef.name;
        }
        if ((i11 & 4) != 0) {
            str2 = tmdbCollectionRef.posterPath;
        }
        if ((i11 & 8) != 0) {
            str3 = tmdbCollectionRef.backdropPath;
        }
        return tmdbCollectionRef.copy(i10, str, str2, str3);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final int getId() {
        return this.id;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getName() {
        return this.name;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final String getPosterPath() {
        return this.posterPath;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final String getBackdropPath() {
        return this.backdropPath;
    }

    public final TmdbCollectionRef copy(int id, String name, String posterPath, String backdropPath) {
        return new TmdbCollectionRef(id, name, posterPath, backdropPath);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof TmdbCollectionRef)) {
            return false;
        }
        TmdbCollectionRef tmdbCollectionRef = (TmdbCollectionRef) other;
        return this.id == tmdbCollectionRef.id && p.a(this.name, tmdbCollectionRef.name) && p.a(this.posterPath, tmdbCollectionRef.posterPath) && p.a(this.backdropPath, tmdbCollectionRef.backdropPath);
    }

    public final String getBackdropPath() {
        return this.backdropPath;
    }

    public final int getId() {
        return this.id;
    }

    public final String getName() {
        return this.name;
    }

    public final String getPosterPath() {
        return this.posterPath;
    }

    public int hashCode() {
        int i10 = this.id * 31;
        String str = this.name;
        int iHashCode = (i10 + (str == null ? 0 : str.hashCode())) * 31;
        String str2 = this.posterPath;
        int iHashCode2 = (iHashCode + (str2 == null ? 0 : str2.hashCode())) * 31;
        String str3 = this.backdropPath;
        return iHashCode2 + (str3 != null ? str3.hashCode() : 0);
    }

    public String toString() {
        int i10 = this.id;
        String str = this.name;
        return a2.n(a2.o("TmdbCollectionRef(id=", i10, ", name=", str, ", posterPath="), this.posterPath, ", backdropPath=", this.backdropPath, ")");
    }

    public TmdbCollectionRef(int i10, String str, String str2, String str3) {
        this.id = i10;
        this.name = str;
        this.posterPath = str2;
        this.backdropPath = str3;
    }

    public /* synthetic */ TmdbCollectionRef(int i10, String str, String str2, String str3, int i11, kotlin.jvm.internal.h hVar) {
        this((i11 & 1) != 0 ? 0 : i10, (i11 & 2) != 0 ? null : str, (i11 & 4) != 0 ? null : str2, (i11 & 8) != 0 ? null : str3);
    }
}
