package com.arflix.tv.data.api;

import androidx.fragment.app.a2;
import io.ktor.http.ContentDisposition;
import kotlin.Metadata;
import kotlin.jvm.internal.p;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B+\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\u0004\b\u0007\u0010\bJ\u000b\u0010\u000e\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u000f\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u0010\u001a\u0004\u0018\u00010\u0006HÆ\u0003J-\u0010\u0011\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006HÆ\u0001J\u0014\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0015\u001a\u00020\u0016HÖ\u0081\u0004J\n\u0010\u0017\u001a\u00020\u0003HÖ\u0081\u0004R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\nR\u0013\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\r¨\u0006\u0018"}, d2 = {"Lcom/arflix/tv/data/api/TraktSearchListUser;", "", "username", "", ContentDisposition.Parameters.Name, "ids", "Lcom/arflix/tv/data/api/TraktSearchListUserIds;", "<init>", "(Ljava/lang/String;Ljava/lang/String;Lcom/arflix/tv/data/api/TraktSearchListUserIds;)V", "getUsername", "()Ljava/lang/String;", "getName", "getIds", "()Lcom/arflix/tv/data/api/TraktSearchListUserIds;", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "", "toString", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final /* data */ class TraktSearchListUser {
    public static final int $stable = 0;
    private final TraktSearchListUserIds ids;
    private final String name;
    private final String username;

    public TraktSearchListUser() {
        this(null, null, null, 7, null);
    }

    public static /* synthetic */ TraktSearchListUser copy$default(TraktSearchListUser traktSearchListUser, String str, String str2, TraktSearchListUserIds traktSearchListUserIds, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            str = traktSearchListUser.username;
        }
        if ((i10 & 2) != 0) {
            str2 = traktSearchListUser.name;
        }
        if ((i10 & 4) != 0) {
            traktSearchListUserIds = traktSearchListUser.ids;
        }
        return traktSearchListUser.copy(str, str2, traktSearchListUserIds);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getUsername() {
        return this.username;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getName() {
        return this.name;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final TraktSearchListUserIds getIds() {
        return this.ids;
    }

    public final TraktSearchListUser copy(String username, String name, TraktSearchListUserIds ids) {
        return new TraktSearchListUser(username, name, ids);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof TraktSearchListUser)) {
            return false;
        }
        TraktSearchListUser traktSearchListUser = (TraktSearchListUser) other;
        return p.a(this.username, traktSearchListUser.username) && p.a(this.name, traktSearchListUser.name) && p.a(this.ids, traktSearchListUser.ids);
    }

    public final TraktSearchListUserIds getIds() {
        return this.ids;
    }

    public final String getName() {
        return this.name;
    }

    public final String getUsername() {
        return this.username;
    }

    public int hashCode() {
        String str = this.username;
        int iHashCode = (str == null ? 0 : str.hashCode()) * 31;
        String str2 = this.name;
        int iHashCode2 = (iHashCode + (str2 == null ? 0 : str2.hashCode())) * 31;
        TraktSearchListUserIds traktSearchListUserIds = this.ids;
        return iHashCode2 + (traktSearchListUserIds != null ? traktSearchListUserIds.hashCode() : 0);
    }

    public String toString() {
        String str = this.username;
        String str2 = this.name;
        TraktSearchListUserIds traktSearchListUserIds = this.ids;
        StringBuilder sbR = a2.r("TraktSearchListUser(username=", str, ", name=", str2, ", ids=");
        sbR.append(traktSearchListUserIds);
        sbR.append(")");
        return sbR.toString();
    }

    public TraktSearchListUser(String str, String str2, TraktSearchListUserIds traktSearchListUserIds) {
        this.username = str;
        this.name = str2;
        this.ids = traktSearchListUserIds;
    }

    public /* synthetic */ TraktSearchListUser(String str, String str2, TraktSearchListUserIds traktSearchListUserIds, int i10, kotlin.jvm.internal.h hVar) {
        this((i10 & 1) != 0 ? null : str, (i10 & 2) != 0 ? null : str2, (i10 & 4) != 0 ? null : traktSearchListUserIds);
    }
}
