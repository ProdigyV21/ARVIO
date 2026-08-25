package com.arflix.tv.data.api;

import androidx.fragment.app.a2;
import androidx.media3.extractor.text.ttml.TtmlNode;
import com.google.gson.annotations.SerializedName;
import io.ktor.http.ContentDisposition;
import kotlin.Metadata;
import kotlin.jvm.internal.p;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0013\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0087\b\u0018\u00002\u00020\u0001B=\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0005\u0012\b\b\u0002\u0010\b\u001a\u00020\u0003¢\u0006\u0004\b\t\u0010\nJ\t\u0010\u0012\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0013\u001a\u00020\u0005HÆ\u0003J\u000b\u0010\u0014\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010\u0015\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\t\u0010\u0016\u001a\u00020\u0003HÆ\u0003J?\u0010\u0017\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00052\b\b\u0002\u0010\b\u001a\u00020\u0003HÆ\u0001J\u0014\u0010\u0018\u001a\u00020\u00192\b\u0010\u001a\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u001b\u001a\u00020\u0003HÖ\u0081\u0004J\n\u0010\u001c\u001a\u00020\u0005HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u000eR\u0018\u0010\u0007\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000eR\u0011\u0010\b\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\f¨\u0006\u001d"}, d2 = {"Lcom/arflix/tv/data/api/TmdbCastMember;", "", TtmlNode.ATTR_ID, "", ContentDisposition.Parameters.Name, "", "character", "profilePath", "order", "<init>", "(ILjava/lang/String;Ljava/lang/String;Ljava/lang/String;I)V", "getId", "()I", "getName", "()Ljava/lang/String;", "getCharacter", "getProfilePath", "getOrder", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "", "other", "hashCode", "toString", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final /* data */ class TmdbCastMember {
    public static final int $stable = 0;
    private final String character;
    private final int id;
    private final String name;
    private final int order;

    @SerializedName("profile_path")
    private final String profilePath;

    public TmdbCastMember() {
        this(0, null, null, null, 0, 31, null);
    }

    public static /* synthetic */ TmdbCastMember copy$default(TmdbCastMember tmdbCastMember, int i10, String str, String str2, String str3, int i11, int i12, Object obj) {
        if ((i12 & 1) != 0) {
            i10 = tmdbCastMember.id;
        }
        if ((i12 & 2) != 0) {
            str = tmdbCastMember.name;
        }
        if ((i12 & 4) != 0) {
            str2 = tmdbCastMember.character;
        }
        if ((i12 & 8) != 0) {
            str3 = tmdbCastMember.profilePath;
        }
        if ((i12 & 16) != 0) {
            i11 = tmdbCastMember.order;
        }
        int i13 = i11;
        String str4 = str2;
        return tmdbCastMember.copy(i10, str, str4, str3, i13);
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
    public final String getCharacter() {
        return this.character;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final String getProfilePath() {
        return this.profilePath;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final int getOrder() {
        return this.order;
    }

    public final TmdbCastMember copy(int id, String name, String character, String profilePath, int order) {
        return new TmdbCastMember(id, name, character, profilePath, order);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof TmdbCastMember)) {
            return false;
        }
        TmdbCastMember tmdbCastMember = (TmdbCastMember) other;
        return this.id == tmdbCastMember.id && p.a(this.name, tmdbCastMember.name) && p.a(this.character, tmdbCastMember.character) && p.a(this.profilePath, tmdbCastMember.profilePath) && this.order == tmdbCastMember.order;
    }

    public final String getCharacter() {
        return this.character;
    }

    public final int getId() {
        return this.id;
    }

    public final String getName() {
        return this.name;
    }

    public final int getOrder() {
        return this.order;
    }

    public final String getProfilePath() {
        return this.profilePath;
    }

    public int hashCode() {
        int iC = androidx.compose.foundation.c.c(this.id * 31, 31, this.name);
        String str = this.character;
        int iHashCode = (iC + (str == null ? 0 : str.hashCode())) * 31;
        String str2 = this.profilePath;
        return ((iHashCode + (str2 != null ? str2.hashCode() : 0)) * 31) + this.order;
    }

    public String toString() {
        int i10 = this.id;
        String str = this.name;
        String str2 = this.character;
        String str3 = this.profilePath;
        int i11 = this.order;
        StringBuilder sbO = a2.o("TmdbCastMember(id=", i10, ", name=", str, ", character=");
        y.a.i(sbO, str2, ", profilePath=", str3, ", order=");
        return androidx.compose.material3.d.j(i11, ")", sbO);
    }

    public TmdbCastMember(int i10, String str, String str2, String str3, int i11) {
        this.id = i10;
        this.name = str;
        this.character = str2;
        this.profilePath = str3;
        this.order = i11;
    }

    public /* synthetic */ TmdbCastMember(int i10, String str, String str2, String str3, int i11, int i12, kotlin.jvm.internal.h hVar) {
        this((i12 & 1) != 0 ? 0 : i10, (i12 & 2) != 0 ? "" : str, (i12 & 4) != 0 ? null : str2, (i12 & 8) != 0 ? null : str3, (i12 & 16) != 0 ? 0 : i11);
    }
}
