package com.arflix.tv.data.api;

import androidx.fragment.app.a2;
import androidx.media3.extractor.text.ttml.TtmlNode;
import com.google.gson.annotations.SerializedName;
import io.ktor.http.ContentDisposition;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.z;
import kotlin.jvm.internal.p;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0013\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0087\b\u0018\u00002\u00020\u0001BO\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0005\u0012\u000e\b\u0002\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\n¢\u0006\u0004\b\f\u0010\rJ\t\u0010\u0017\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0018\u001a\u00020\u0005HÆ\u0003J\u000b\u0010\u0019\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010\u001a\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010\u001b\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000f\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u000b0\nHÆ\u0003JQ\u0010\u001d\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00052\u000e\b\u0002\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\nHÆ\u0001J\u0014\u0010\u001e\u001a\u00020\u001f2\b\u0010 \u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010!\u001a\u00020\u0003HÖ\u0081\u0004J\n\u0010\"\u001a\u00020\u0005HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0011R\u0018\u0010\u0007\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0011R\u0018\u0010\b\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0011R\u0017\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\n¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016¨\u0006#"}, d2 = {"Lcom/arflix/tv/data/api/TmdbCollectionResponse;", "", TtmlNode.ATTR_ID, "", ContentDisposition.Parameters.Name, "", "overview", "posterPath", "backdropPath", "parts", "", "Lcom/arflix/tv/data/api/TmdbMediaItem;", "<init>", "(ILjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/List;)V", "getId", "()I", "getName", "()Ljava/lang/String;", "getOverview", "getPosterPath", "getBackdropPath", "getParts", "()Ljava/util/List;", "component1", "component2", "component3", "component4", "component5", "component6", "copy", "equals", "", "other", "hashCode", "toString", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final /* data */ class TmdbCollectionResponse {
    public static final int $stable = 8;

    @SerializedName("backdrop_path")
    private final String backdropPath;
    private final int id;
    private final String name;
    private final String overview;
    private final List<TmdbMediaItem> parts;

    @SerializedName("poster_path")
    private final String posterPath;

    public TmdbCollectionResponse() {
        this(0, null, null, null, null, null, 63, null);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ TmdbCollectionResponse copy$default(TmdbCollectionResponse tmdbCollectionResponse, int i10, String str, String str2, String str3, String str4, List list, int i11, Object obj) {
        if ((i11 & 1) != 0) {
            i10 = tmdbCollectionResponse.id;
        }
        if ((i11 & 2) != 0) {
            str = tmdbCollectionResponse.name;
        }
        if ((i11 & 4) != 0) {
            str2 = tmdbCollectionResponse.overview;
        }
        if ((i11 & 8) != 0) {
            str3 = tmdbCollectionResponse.posterPath;
        }
        if ((i11 & 16) != 0) {
            str4 = tmdbCollectionResponse.backdropPath;
        }
        if ((i11 & 32) != 0) {
            list = tmdbCollectionResponse.parts;
        }
        String str5 = str4;
        List list2 = list;
        return tmdbCollectionResponse.copy(i10, str, str2, str3, str5, list2);
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
    public final String getOverview() {
        return this.overview;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final String getPosterPath() {
        return this.posterPath;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final String getBackdropPath() {
        return this.backdropPath;
    }

    public final List<TmdbMediaItem> component6() {
        return this.parts;
    }

    public final TmdbCollectionResponse copy(int id, String name, String overview, String posterPath, String backdropPath, List<TmdbMediaItem> parts) {
        return new TmdbCollectionResponse(id, name, overview, posterPath, backdropPath, parts);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof TmdbCollectionResponse)) {
            return false;
        }
        TmdbCollectionResponse tmdbCollectionResponse = (TmdbCollectionResponse) other;
        return this.id == tmdbCollectionResponse.id && p.a(this.name, tmdbCollectionResponse.name) && p.a(this.overview, tmdbCollectionResponse.overview) && p.a(this.posterPath, tmdbCollectionResponse.posterPath) && p.a(this.backdropPath, tmdbCollectionResponse.backdropPath) && p.a(this.parts, tmdbCollectionResponse.parts);
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

    public final String getOverview() {
        return this.overview;
    }

    public final List<TmdbMediaItem> getParts() {
        return this.parts;
    }

    public final String getPosterPath() {
        return this.posterPath;
    }

    public int hashCode() {
        int iC = androidx.compose.foundation.c.c(this.id * 31, 31, this.name);
        String str = this.overview;
        int iHashCode = (iC + (str == null ? 0 : str.hashCode())) * 31;
        String str2 = this.posterPath;
        int iHashCode2 = (iHashCode + (str2 == null ? 0 : str2.hashCode())) * 31;
        String str3 = this.backdropPath;
        return this.parts.hashCode() + ((iHashCode2 + (str3 != null ? str3.hashCode() : 0)) * 31);
    }

    public String toString() {
        int i10 = this.id;
        String str = this.name;
        String str2 = this.overview;
        String str3 = this.posterPath;
        String str4 = this.backdropPath;
        List<TmdbMediaItem> list = this.parts;
        StringBuilder sbO = a2.o("TmdbCollectionResponse(id=", i10, ", name=", str, ", overview=");
        y.a.i(sbO, str2, ", posterPath=", str3, ", backdropPath=");
        sbO.append(str4);
        sbO.append(", parts=");
        sbO.append(list);
        sbO.append(")");
        return sbO.toString();
    }

    public TmdbCollectionResponse(int i10, String str, String str2, String str3, String str4, List<TmdbMediaItem> list) {
        this.id = i10;
        this.name = str;
        this.overview = str2;
        this.posterPath = str3;
        this.backdropPath = str4;
        this.parts = list;
    }

    public /* synthetic */ TmdbCollectionResponse(int i10, String str, String str2, String str3, String str4, List list, int i11, kotlin.jvm.internal.h hVar) {
        this((i11 & 1) != 0 ? 0 : i10, (i11 & 2) != 0 ? "" : str, (i11 & 4) != 0 ? null : str2, (i11 & 8) != 0 ? null : str3, (i11 & 16) != 0 ? null : str4, (i11 & 32) != 0 ? z.f19728i : list);
    }
}
