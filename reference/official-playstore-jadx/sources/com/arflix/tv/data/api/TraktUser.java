package com.arflix.tv.data.api;

import com.google.gson.annotations.SerializedName;
import io.ktor.client.utils.CacheControl;
import io.ktor.http.ContentDisposition;
import kotlin.Metadata;
import kotlin.jvm.internal.p;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0018\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B?\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\b\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\t\u001a\u0004\u0018\u00010\n¢\u0006\u0004\b\u000b\u0010\fJ\t\u0010\u0018\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0019\u001a\u00020\u0005HÆ\u0003J\u000b\u0010\u001a\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0010\u0010\u001b\u001a\u0004\u0018\u00010\u0005HÆ\u0003¢\u0006\u0002\u0010\u0013J\u0010\u0010\u001c\u001a\u0004\u0018\u00010\u0005HÆ\u0003¢\u0006\u0002\u0010\u0013J\u000b\u0010\u001d\u001a\u0004\u0018\u00010\nHÆ\u0003JR\u0010\u001e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\nHÆ\u0001¢\u0006\u0002\u0010\u001fJ\u0014\u0010 \u001a\u00020\u00052\b\u0010!\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\"\u001a\u00020#HÖ\u0081\u0004J\n\u0010$\u001a\u00020\u0003HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u000eR\u0015\u0010\u0007\u001a\u0004\u0018\u00010\u0005¢\u0006\n\n\u0002\u0010\u0014\u001a\u0004\b\u0012\u0010\u0013R\u001a\u0010\b\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\n\n\u0002\u0010\u0014\u001a\u0004\b\u0015\u0010\u0013R\u0013\u0010\t\u001a\u0004\u0018\u00010\n¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017¨\u0006%"}, d2 = {"Lcom/arflix/tv/data/api/TraktUser;", "", "username", "", CacheControl.PRIVATE, "", ContentDisposition.Parameters.Name, "vip", "vipEp", "ids", "Lcom/arflix/tv/data/api/TraktUserIds;", "<init>", "(Ljava/lang/String;ZLjava/lang/String;Ljava/lang/Boolean;Ljava/lang/Boolean;Lcom/arflix/tv/data/api/TraktUserIds;)V", "getUsername", "()Ljava/lang/String;", "getPrivate", "()Z", "getName", "getVip", "()Ljava/lang/Boolean;", "Ljava/lang/Boolean;", "getVipEp", "getIds", "()Lcom/arflix/tv/data/api/TraktUserIds;", "component1", "component2", "component3", "component4", "component5", "component6", "copy", "(Ljava/lang/String;ZLjava/lang/String;Ljava/lang/Boolean;Ljava/lang/Boolean;Lcom/arflix/tv/data/api/TraktUserIds;)Lcom/arflix/tv/data/api/TraktUser;", "equals", "other", "hashCode", "", "toString", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final /* data */ class TraktUser {
    public static final int $stable = 0;
    private final TraktUserIds ids;
    private final String name;
    private final boolean private;
    private final String username;
    private final Boolean vip;

    @SerializedName("vip_ep")
    private final Boolean vipEp;

    public TraktUser(String str, boolean z, String str2, Boolean bool, Boolean bool2, TraktUserIds traktUserIds) {
        this.username = str;
        this.private = z;
        this.name = str2;
        this.vip = bool;
        this.vipEp = bool2;
        this.ids = traktUserIds;
    }

    public static /* synthetic */ TraktUser copy$default(TraktUser traktUser, String str, boolean z, String str2, Boolean bool, Boolean bool2, TraktUserIds traktUserIds, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            str = traktUser.username;
        }
        if ((i10 & 2) != 0) {
            z = traktUser.private;
        }
        if ((i10 & 4) != 0) {
            str2 = traktUser.name;
        }
        if ((i10 & 8) != 0) {
            bool = traktUser.vip;
        }
        if ((i10 & 16) != 0) {
            bool2 = traktUser.vipEp;
        }
        if ((i10 & 32) != 0) {
            traktUserIds = traktUser.ids;
        }
        Boolean bool3 = bool2;
        TraktUserIds traktUserIds2 = traktUserIds;
        return traktUser.copy(str, z, str2, bool, bool3, traktUserIds2);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getUsername() {
        return this.username;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final boolean getPrivate() {
        return this.private;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final String getName() {
        return this.name;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final Boolean getVip() {
        return this.vip;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final Boolean getVipEp() {
        return this.vipEp;
    }

    /* JADX INFO: renamed from: component6, reason: from getter */
    public final TraktUserIds getIds() {
        return this.ids;
    }

    public final TraktUser copy(String username, boolean z, String name, Boolean vip, Boolean vipEp, TraktUserIds ids) {
        return new TraktUser(username, z, name, vip, vipEp, ids);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof TraktUser)) {
            return false;
        }
        TraktUser traktUser = (TraktUser) other;
        return p.a(this.username, traktUser.username) && this.private == traktUser.private && p.a(this.name, traktUser.name) && p.a(this.vip, traktUser.vip) && p.a(this.vipEp, traktUser.vipEp) && p.a(this.ids, traktUser.ids);
    }

    public final TraktUserIds getIds() {
        return this.ids;
    }

    public final String getName() {
        return this.name;
    }

    public final boolean getPrivate() {
        return this.private;
    }

    public final String getUsername() {
        return this.username;
    }

    public final Boolean getVip() {
        return this.vip;
    }

    public final Boolean getVipEp() {
        return this.vipEp;
    }

    public int hashCode() {
        int iHashCode = ((this.username.hashCode() * 31) + (this.private ? 1231 : 1237)) * 31;
        String str = this.name;
        int iHashCode2 = (iHashCode + (str == null ? 0 : str.hashCode())) * 31;
        Boolean bool = this.vip;
        int iHashCode3 = (iHashCode2 + (bool == null ? 0 : bool.hashCode())) * 31;
        Boolean bool2 = this.vipEp;
        int iHashCode4 = (iHashCode3 + (bool2 == null ? 0 : bool2.hashCode())) * 31;
        TraktUserIds traktUserIds = this.ids;
        return iHashCode4 + (traktUserIds != null ? traktUserIds.hashCode() : 0);
    }

    public String toString() {
        return "TraktUser(username=" + this.username + ", private=" + this.private + ", name=" + this.name + ", vip=" + this.vip + ", vipEp=" + this.vipEp + ", ids=" + this.ids + ")";
    }
}
