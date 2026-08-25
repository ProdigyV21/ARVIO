package com.arflix.tv.data.api;

import com.google.gson.annotations.SerializedName;
import io.ktor.http.ContentDisposition;
import kotlin.Metadata;
import kotlin.jvm.internal.p;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0002\b\u000f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B+\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u0007\u0010\bJ\u000b\u0010\u000f\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0010\u0010\u0010\u001a\u0004\u0018\u00010\u0005HÆ\u0003¢\u0006\u0002\u0010\fJ\u000b\u0010\u0011\u001a\u0004\u0018\u00010\u0003HÆ\u0003J2\u0010\u0012\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0003HÆ\u0001¢\u0006\u0002\u0010\u0013J\u0014\u0010\u0014\u001a\u00020\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0017\u001a\u00020\u0018HÖ\u0081\u0004J\n\u0010\u0019\u001a\u00020\u0003HÖ\u0081\u0004R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u001a\u0010\u0004\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\n\n\u0002\u0010\r\u001a\u0004\b\u000b\u0010\fR\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\n¨\u0006\u001a"}, d2 = {"Lcom/arflix/tv/data/api/MdbUser;", "", "username", "", "userId", "", ContentDisposition.Parameters.Name, "<init>", "(Ljava/lang/String;Ljava/lang/Long;Ljava/lang/String;)V", "getUsername", "()Ljava/lang/String;", "getUserId", "()Ljava/lang/Long;", "Ljava/lang/Long;", "getName", "component1", "component2", "component3", "copy", "(Ljava/lang/String;Ljava/lang/Long;Ljava/lang/String;)Lcom/arflix/tv/data/api/MdbUser;", "equals", "", "other", "hashCode", "", "toString", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final /* data */ class MdbUser {
    public static final int $stable = 0;
    private final String name;

    @SerializedName("user_id")
    private final Long userId;
    private final String username;

    public MdbUser() {
        this(null, null, null, 7, null);
    }

    public static /* synthetic */ MdbUser copy$default(MdbUser mdbUser, String str, Long l10, String str2, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            str = mdbUser.username;
        }
        if ((i10 & 2) != 0) {
            l10 = mdbUser.userId;
        }
        if ((i10 & 4) != 0) {
            str2 = mdbUser.name;
        }
        return mdbUser.copy(str, l10, str2);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getUsername() {
        return this.username;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final Long getUserId() {
        return this.userId;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final String getName() {
        return this.name;
    }

    public final MdbUser copy(String username, Long userId, String name) {
        return new MdbUser(username, userId, name);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof MdbUser)) {
            return false;
        }
        MdbUser mdbUser = (MdbUser) other;
        return p.a(this.username, mdbUser.username) && p.a(this.userId, mdbUser.userId) && p.a(this.name, mdbUser.name);
    }

    public final String getName() {
        return this.name;
    }

    public final Long getUserId() {
        return this.userId;
    }

    public final String getUsername() {
        return this.username;
    }

    public int hashCode() {
        String str = this.username;
        int iHashCode = (str == null ? 0 : str.hashCode()) * 31;
        Long l10 = this.userId;
        int iHashCode2 = (iHashCode + (l10 == null ? 0 : l10.hashCode())) * 31;
        String str2 = this.name;
        return iHashCode2 + (str2 != null ? str2.hashCode() : 0);
    }

    public String toString() {
        String str = this.username;
        Long l10 = this.userId;
        String str2 = this.name;
        StringBuilder sb2 = new StringBuilder("MdbUser(username=");
        sb2.append(str);
        sb2.append(", userId=");
        sb2.append(l10);
        sb2.append(", name=");
        return a0.c.p(sb2, str2, ")");
    }

    public MdbUser(String str, Long l10, String str2) {
        this.username = str;
        this.userId = l10;
        this.name = str2;
    }

    public /* synthetic */ MdbUser(String str, Long l10, String str2, int i10, kotlin.jvm.internal.h hVar) {
        this((i10 & 1) != 0 ? null : str, (i10 & 2) != 0 ? null : l10, (i10 & 4) != 0 ? null : str2);
    }
}
