package com.arflix.tv.data.api;

import androidx.fragment.app.a2;
import androidx.media3.extractor.text.ttml.TtmlNode;
import io.ktor.http.ContentDisposition;
import io.ktor.http.LinkHeader;
import kotlin.Metadata;
import kotlin.jvm.internal.p;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0014\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001BC\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0003\u0012\b\b\u0002\u0010\b\u001a\u00020\t¢\u0006\u0004\b\n\u0010\u000bJ\t\u0010\u0014\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0015\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0016\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0017\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0018\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0019\u001a\u00020\tHÆ\u0003JE\u0010\u001a\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\u00032\b\b\u0002\u0010\b\u001a\u00020\tHÆ\u0001J\u0014\u0010\u001b\u001a\u00020\t2\b\u0010\u001c\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u001d\u001a\u00020\u001eHÖ\u0081\u0004J\n\u0010\u001f\u001a\u00020\u0003HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\rR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\rR\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\rR\u0011\u0010\u0007\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\rR\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013¨\u0006 "}, d2 = {"Lcom/arflix/tv/data/api/TmdbVideo;", "", TtmlNode.ATTR_ID, "", "key", ContentDisposition.Parameters.Name, "site", LinkHeader.Parameters.Type, "official", "", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Z)V", "getId", "()Ljava/lang/String;", "getKey", "getName", "getSite", "getType", "getOfficial", "()Z", "component1", "component2", "component3", "component4", "component5", "component6", "copy", "equals", "other", "hashCode", "", "toString", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final /* data */ class TmdbVideo {
    public static final int $stable = 0;
    private final String id;
    private final String key;
    private final String name;
    private final boolean official;
    private final String site;
    private final String type;

    public TmdbVideo() {
        this(null, null, null, null, null, false, 63, null);
    }

    public static /* synthetic */ TmdbVideo copy$default(TmdbVideo tmdbVideo, String str, String str2, String str3, String str4, String str5, boolean z, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            str = tmdbVideo.id;
        }
        if ((i10 & 2) != 0) {
            str2 = tmdbVideo.key;
        }
        if ((i10 & 4) != 0) {
            str3 = tmdbVideo.name;
        }
        if ((i10 & 8) != 0) {
            str4 = tmdbVideo.site;
        }
        if ((i10 & 16) != 0) {
            str5 = tmdbVideo.type;
        }
        if ((i10 & 32) != 0) {
            z = tmdbVideo.official;
        }
        String str6 = str5;
        boolean z5 = z;
        return tmdbVideo.copy(str, str2, str3, str4, str6, z5);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getId() {
        return this.id;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getKey() {
        return this.key;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final String getName() {
        return this.name;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final String getSite() {
        return this.site;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final String getType() {
        return this.type;
    }

    /* JADX INFO: renamed from: component6, reason: from getter */
    public final boolean getOfficial() {
        return this.official;
    }

    public final TmdbVideo copy(String id, String key, String name, String site, String type, boolean official) {
        return new TmdbVideo(id, key, name, site, type, official);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof TmdbVideo)) {
            return false;
        }
        TmdbVideo tmdbVideo = (TmdbVideo) other;
        return p.a(this.id, tmdbVideo.id) && p.a(this.key, tmdbVideo.key) && p.a(this.name, tmdbVideo.name) && p.a(this.site, tmdbVideo.site) && p.a(this.type, tmdbVideo.type) && this.official == tmdbVideo.official;
    }

    public final String getId() {
        return this.id;
    }

    public final String getKey() {
        return this.key;
    }

    public final String getName() {
        return this.name;
    }

    public final boolean getOfficial() {
        return this.official;
    }

    public final String getSite() {
        return this.site;
    }

    public final String getType() {
        return this.type;
    }

    public int hashCode() {
        return androidx.compose.foundation.c.c(androidx.compose.foundation.c.c(androidx.compose.foundation.c.c(androidx.compose.foundation.c.c(this.id.hashCode() * 31, 31, this.key), 31, this.name), 31, this.site), 31, this.type) + (this.official ? 1231 : 1237);
    }

    public String toString() {
        String str = this.id;
        String str2 = this.key;
        String str3 = this.name;
        String str4 = this.site;
        String str5 = this.type;
        boolean z = this.official;
        StringBuilder sbR = a2.r("TmdbVideo(id=", str, ", key=", str2, ", name=");
        y.a.i(sbR, str3, ", site=", str4, ", type=");
        sbR.append(str5);
        sbR.append(", official=");
        sbR.append(z);
        sbR.append(")");
        return sbR.toString();
    }

    public TmdbVideo(String str, String str2, String str3, String str4, String str5, boolean z) {
        this.id = str;
        this.key = str2;
        this.name = str3;
        this.site = str4;
        this.type = str5;
        this.official = z;
    }

    public /* synthetic */ TmdbVideo(String str, String str2, String str3, String str4, String str5, boolean z, int i10, kotlin.jvm.internal.h hVar) {
        this((i10 & 1) != 0 ? "" : str, (i10 & 2) != 0 ? "" : str2, (i10 & 4) != 0 ? "" : str3, (i10 & 8) != 0 ? "" : str4, (i10 & 16) != 0 ? "" : str5, (i10 & 32) != 0 ? false : z);
    }
}
