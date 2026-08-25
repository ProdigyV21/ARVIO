package com.arflix.tv.data.api;

import androidx.fragment.app.a2;
import com.google.gson.annotations.SerializedName;
import io.ktor.http.ContentDisposition;
import kotlin.Metadata;
import kotlin.jvm.internal.p;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\u0010\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B3\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\u0004\b\b\u0010\tJ\t\u0010\u0011\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0012\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\u0013\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0010\u0010\u0014\u001a\u0004\u0018\u00010\u0007HÆ\u0003¢\u0006\u0002\u0010\u000fJ:\u0010\u0015\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007HÆ\u0001¢\u0006\u0002\u0010\u0016J\u0014\u0010\u0017\u001a\u00020\u00182\b\u0010\u0019\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u001a\u001a\u00020\u001bHÖ\u0081\u0004J\n\u0010\u001c\u001a\u00020\u0003HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u000bR\u0018\u0010\u0005\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000bR\u0015\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\n\n\u0002\u0010\u0010\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u001d"}, d2 = {"Lcom/arflix/tv/data/api/TmdbAuthorDetails;", "", ContentDisposition.Parameters.Name, "", "username", "avatarPath", "rating", "", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Float;)V", "getName", "()Ljava/lang/String;", "getUsername", "getAvatarPath", "getRating", "()Ljava/lang/Float;", "Ljava/lang/Float;", "component1", "component2", "component3", "component4", "copy", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Float;)Lcom/arflix/tv/data/api/TmdbAuthorDetails;", "equals", "", "other", "hashCode", "", "toString", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final /* data */ class TmdbAuthorDetails {
    public static final int $stable = 0;

    @SerializedName("avatar_path")
    private final String avatarPath;
    private final String name;
    private final Float rating;
    private final String username;

    public TmdbAuthorDetails() {
        this(null, null, null, null, 15, null);
    }

    public static /* synthetic */ TmdbAuthorDetails copy$default(TmdbAuthorDetails tmdbAuthorDetails, String str, String str2, String str3, Float f10, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            str = tmdbAuthorDetails.name;
        }
        if ((i10 & 2) != 0) {
            str2 = tmdbAuthorDetails.username;
        }
        if ((i10 & 4) != 0) {
            str3 = tmdbAuthorDetails.avatarPath;
        }
        if ((i10 & 8) != 0) {
            f10 = tmdbAuthorDetails.rating;
        }
        return tmdbAuthorDetails.copy(str, str2, str3, f10);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getName() {
        return this.name;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getUsername() {
        return this.username;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final String getAvatarPath() {
        return this.avatarPath;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final Float getRating() {
        return this.rating;
    }

    public final TmdbAuthorDetails copy(String name, String username, String avatarPath, Float rating) {
        return new TmdbAuthorDetails(name, username, avatarPath, rating);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof TmdbAuthorDetails)) {
            return false;
        }
        TmdbAuthorDetails tmdbAuthorDetails = (TmdbAuthorDetails) other;
        return p.a(this.name, tmdbAuthorDetails.name) && p.a(this.username, tmdbAuthorDetails.username) && p.a(this.avatarPath, tmdbAuthorDetails.avatarPath) && p.a(this.rating, tmdbAuthorDetails.rating);
    }

    public final String getAvatarPath() {
        return this.avatarPath;
    }

    public final String getName() {
        return this.name;
    }

    public final Float getRating() {
        return this.rating;
    }

    public final String getUsername() {
        return this.username;
    }

    public int hashCode() {
        int iC = androidx.compose.foundation.c.c(this.name.hashCode() * 31, 31, this.username);
        String str = this.avatarPath;
        int iHashCode = (iC + (str == null ? 0 : str.hashCode())) * 31;
        Float f10 = this.rating;
        return iHashCode + (f10 != null ? f10.hashCode() : 0);
    }

    public String toString() {
        String str = this.name;
        String str2 = this.username;
        String str3 = this.avatarPath;
        Float f10 = this.rating;
        StringBuilder sbR = a2.r("TmdbAuthorDetails(name=", str, ", username=", str2, ", avatarPath=");
        sbR.append(str3);
        sbR.append(", rating=");
        sbR.append(f10);
        sbR.append(")");
        return sbR.toString();
    }

    public TmdbAuthorDetails(String str, String str2, String str3, Float f10) {
        this.name = str;
        this.username = str2;
        this.avatarPath = str3;
        this.rating = f10;
    }

    public /* synthetic */ TmdbAuthorDetails(String str, String str2, String str3, Float f10, int i10, kotlin.jvm.internal.h hVar) {
        this((i10 & 1) != 0 ? "" : str, (i10 & 2) != 0 ? "" : str2, (i10 & 4) != 0 ? null : str3, (i10 & 8) != 0 ? null : f10);
    }
}
