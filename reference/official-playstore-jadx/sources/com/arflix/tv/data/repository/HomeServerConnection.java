package com.arflix.tv.data.repository;

import java.util.List;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b&\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B\u008f\u0001\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0005\u0012\b\b\u0002\u0010\b\u001a\u00020\u0005\u0012\b\b\u0002\u0010\t\u001a\u00020\n\u0012\b\b\u0002\u0010\u000b\u001a\u00020\u0005\u0012\b\b\u0002\u0010\f\u001a\u00020\u0005\u0012\b\b\u0002\u0010\r\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u000e\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u000f\u001a\u00020\u0005\u0012\u000e\b\u0002\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00120\u0011\u0012\b\b\u0002\u0010\u0013\u001a\u00020\u0014¢\u0006\u0004\b\u0015\u0010\u0016J\t\u0010*\u001a\u00020\u0003HÆ\u0003J\t\u0010+\u001a\u00020\u0005HÆ\u0003J\t\u0010,\u001a\u00020\u0005HÆ\u0003J\t\u0010-\u001a\u00020\u0005HÆ\u0003J\t\u0010.\u001a\u00020\u0005HÆ\u0003J\t\u0010/\u001a\u00020\nHÆ\u0003J\t\u00100\u001a\u00020\u0005HÆ\u0003J\t\u00101\u001a\u00020\u0005HÆ\u0003J\t\u00102\u001a\u00020\u0005HÆ\u0003J\t\u00103\u001a\u00020\u0005HÆ\u0003J\t\u00104\u001a\u00020\u0005HÆ\u0003J\u000f\u00105\u001a\b\u0012\u0004\u0012\u00020\u00120\u0011HÆ\u0003J\t\u00106\u001a\u00020\u0014HÆ\u0003J\u0091\u0001\u00107\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\u00052\b\b\u0002\u0010\b\u001a\u00020\u00052\b\b\u0002\u0010\t\u001a\u00020\n2\b\b\u0002\u0010\u000b\u001a\u00020\u00052\b\b\u0002\u0010\f\u001a\u00020\u00052\b\b\u0002\u0010\r\u001a\u00020\u00052\b\b\u0002\u0010\u000e\u001a\u00020\u00052\b\b\u0002\u0010\u000f\u001a\u00020\u00052\u000e\b\u0002\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00120\u00112\b\b\u0002\u0010\u0013\u001a\u00020\u0014HÆ\u0001J\u0014\u00108\u001a\u00020\u00032\b\u00109\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010:\u001a\u00020;HÖ\u0081\u0004J\n\u0010<\u001a\u00020\u0005HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001aR\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u001aR\u0011\u0010\u0007\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001aR\u0011\u0010\b\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001aR\u0011\u0010\t\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u001fR\u0011\u0010\u000b\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b \u0010\u001aR\u0011\u0010\f\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b!\u0010\u001aR\u0011\u0010\r\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010\u001aR\u0011\u0010\u000e\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b#\u0010\u001aR\u0011\u0010\u000f\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b$\u0010\u001aR\u0017\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00120\u0011¢\u0006\b\n\u0000\u001a\u0004\b%\u0010&R\u0011\u0010\u0013\u001a\u00020\u0014¢\u0006\b\n\u0000\u001a\u0004\b'\u0010(R\u0011\u0010)\u001a\u00020\u00038F¢\u0006\u0006\u001a\u0004\b)\u0010\u0018¨\u0006="}, d2 = {"Lcom/arflix/tv/data/repository/HomeServerConnection;", "", "enabled", "", "connectionId", "", "serverUrl", "displayName", "serverName", "serverKind", "Lcom/arflix/tv/data/repository/HomeServerKind;", "serverId", "userId", "userName", "accessToken", "accountToken", "collections", "", "Lcom/arflix/tv/data/repository/HomeServerCollection;", "lastConnectedAt", "", "<init>", "(ZLjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lcom/arflix/tv/data/repository/HomeServerKind;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/List;J)V", "getEnabled", "()Z", "getConnectionId", "()Ljava/lang/String;", "getServerUrl", "getDisplayName", "getServerName", "getServerKind", "()Lcom/arflix/tv/data/repository/HomeServerKind;", "getServerId", "getUserId", "getUserName", "getAccessToken", "getAccountToken", "getCollections", "()Ljava/util/List;", "getLastConnectedAt", "()J", "isUsable", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "component10", "component11", "component12", "component13", "copy", "equals", "other", "hashCode", "", "toString", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final /* data */ class HomeServerConnection {
    public static final int $stable = 0;
    private final String accessToken;
    private final String accountToken;
    private final List<HomeServerCollection> collections;
    private final String connectionId;
    private final String displayName;
    private final boolean enabled;
    private final long lastConnectedAt;
    private final String serverId;
    private final HomeServerKind serverKind;
    private final String serverName;
    private final String serverUrl;
    private final String userId;
    private final String userName;

    public HomeServerConnection() {
        this(false, null, null, null, null, null, null, null, null, null, null, null, 0L, 8191, null);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final boolean getEnabled() {
        return this.enabled;
    }

    /* JADX INFO: renamed from: component10, reason: from getter */
    public final String getAccessToken() {
        return this.accessToken;
    }

    /* JADX INFO: renamed from: component11, reason: from getter */
    public final String getAccountToken() {
        return this.accountToken;
    }

    public final List<HomeServerCollection> component12() {
        return this.collections;
    }

    /* JADX INFO: renamed from: component13, reason: from getter */
    public final long getLastConnectedAt() {
        return this.lastConnectedAt;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getConnectionId() {
        return this.connectionId;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final String getServerUrl() {
        return this.serverUrl;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final String getDisplayName() {
        return this.displayName;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final String getServerName() {
        return this.serverName;
    }

    /* JADX INFO: renamed from: component6, reason: from getter */
    public final HomeServerKind getServerKind() {
        return this.serverKind;
    }

    /* JADX INFO: renamed from: component7, reason: from getter */
    public final String getServerId() {
        return this.serverId;
    }

    /* JADX INFO: renamed from: component8, reason: from getter */
    public final String getUserId() {
        return this.userId;
    }

    /* JADX INFO: renamed from: component9, reason: from getter */
    public final String getUserName() {
        return this.userName;
    }

    public final HomeServerConnection copy(boolean enabled, String connectionId, String serverUrl, String displayName, String serverName, HomeServerKind serverKind, String serverId, String userId, String userName, String accessToken, String accountToken, List<HomeServerCollection> collections, long lastConnectedAt) {
        return new HomeServerConnection(enabled, connectionId, serverUrl, displayName, serverName, serverKind, serverId, userId, userName, accessToken, accountToken, collections, lastConnectedAt);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof HomeServerConnection)) {
            return false;
        }
        HomeServerConnection homeServerConnection = (HomeServerConnection) other;
        return this.enabled == homeServerConnection.enabled && kotlin.jvm.internal.p.a(this.connectionId, homeServerConnection.connectionId) && kotlin.jvm.internal.p.a(this.serverUrl, homeServerConnection.serverUrl) && kotlin.jvm.internal.p.a(this.displayName, homeServerConnection.displayName) && kotlin.jvm.internal.p.a(this.serverName, homeServerConnection.serverName) && this.serverKind == homeServerConnection.serverKind && kotlin.jvm.internal.p.a(this.serverId, homeServerConnection.serverId) && kotlin.jvm.internal.p.a(this.userId, homeServerConnection.userId) && kotlin.jvm.internal.p.a(this.userName, homeServerConnection.userName) && kotlin.jvm.internal.p.a(this.accessToken, homeServerConnection.accessToken) && kotlin.jvm.internal.p.a(this.accountToken, homeServerConnection.accountToken) && kotlin.jvm.internal.p.a(this.collections, homeServerConnection.collections) && this.lastConnectedAt == homeServerConnection.lastConnectedAt;
    }

    public final String getAccessToken() {
        return this.accessToken;
    }

    public final String getAccountToken() {
        return this.accountToken;
    }

    public final List<HomeServerCollection> getCollections() {
        return this.collections;
    }

    public final String getConnectionId() {
        return this.connectionId;
    }

    public final String getDisplayName() {
        return this.displayName;
    }

    public final boolean getEnabled() {
        return this.enabled;
    }

    public final long getLastConnectedAt() {
        return this.lastConnectedAt;
    }

    public final String getServerId() {
        return this.serverId;
    }

    public final HomeServerKind getServerKind() {
        return this.serverKind;
    }

    public final String getServerName() {
        return this.serverName;
    }

    public final String getServerUrl() {
        return this.serverUrl;
    }

    public final String getUserId() {
        return this.userId;
    }

    public final String getUserName() {
        return this.userName;
    }

    public int hashCode() {
        int iE = androidx.compose.material3.d.e(this.collections, androidx.compose.foundation.c.c(androidx.compose.foundation.c.c(androidx.compose.foundation.c.c(androidx.compose.foundation.c.c(androidx.compose.foundation.c.c((this.serverKind.hashCode() + androidx.compose.foundation.c.c(androidx.compose.foundation.c.c(androidx.compose.foundation.c.c(androidx.compose.foundation.c.c((this.enabled ? 1231 : 1237) * 31, 31, this.connectionId), 31, this.serverUrl), 31, this.displayName), 31, this.serverName)) * 31, 31, this.serverId), 31, this.userId), 31, this.userName), 31, this.accessToken), 31, this.accountToken), 31);
        long j10 = this.lastConnectedAt;
        return iE + ((int) (j10 ^ (j10 >>> 32)));
    }

    public final boolean isUsable() {
        if (!this.enabled || kotlin.text.o.h0(this.serverUrl) || kotlin.text.o.h0(this.accessToken)) {
            return false;
        }
        return this.serverKind == HomeServerKind.PLEX || !kotlin.text.o.h0(this.userId);
    }

    public String toString() {
        boolean z = this.enabled;
        String str = this.connectionId;
        String str2 = this.serverUrl;
        String str3 = this.displayName;
        String str4 = this.serverName;
        HomeServerKind homeServerKind = this.serverKind;
        String str5 = this.serverId;
        String str6 = this.userId;
        String str7 = this.userName;
        String str8 = this.accessToken;
        String str9 = this.accountToken;
        List<HomeServerCollection> list = this.collections;
        long j10 = this.lastConnectedAt;
        StringBuilder sb2 = new StringBuilder("HomeServerConnection(enabled=");
        sb2.append(z);
        sb2.append(", connectionId=");
        sb2.append(str);
        sb2.append(", serverUrl=");
        y.a.i(sb2, str2, ", displayName=", str3, ", serverName=");
        sb2.append(str4);
        sb2.append(", serverKind=");
        sb2.append(homeServerKind);
        sb2.append(", serverId=");
        y.a.i(sb2, str5, ", userId=", str6, ", userName=");
        y.a.i(sb2, str7, ", accessToken=", str8, ", accountToken=");
        sb2.append(str9);
        sb2.append(", collections=");
        sb2.append(list);
        sb2.append(", lastConnectedAt=");
        return a0.c.j(j10, ")", sb2);
    }

    public HomeServerConnection(boolean z, String str, String str2, String str3, String str4, HomeServerKind homeServerKind, String str5, String str6, String str7, String str8, String str9, List<HomeServerCollection> list, long j10) {
        this.enabled = z;
        this.connectionId = str;
        this.serverUrl = str2;
        this.displayName = str3;
        this.serverName = str4;
        this.serverKind = homeServerKind;
        this.serverId = str5;
        this.userId = str6;
        this.userName = str7;
        this.accessToken = str8;
        this.accountToken = str9;
        this.collections = list;
        this.lastConnectedAt = j10;
    }

    public /* synthetic */ HomeServerConnection(boolean z, String str, String str2, String str3, String str4, HomeServerKind homeServerKind, String str5, String str6, String str7, String str8, String str9, List list, long j10, int i10, kotlin.jvm.internal.h hVar) {
        this((i10 & 1) != 0 ? true : z, (i10 & 2) != 0 ? "" : str, (i10 & 4) != 0 ? "" : str2, (i10 & 8) != 0 ? "" : str3, (i10 & 16) != 0 ? "" : str4, (i10 & 32) != 0 ? HomeServerKind.UNKNOWN : homeServerKind, (i10 & 64) != 0 ? "" : str5, (i10 & 128) != 0 ? "" : str6, (i10 & 256) != 0 ? "" : str7, (i10 & 512) != 0 ? "" : str8, (i10 & 1024) == 0 ? str9 : "", (i10 & 2048) != 0 ? kotlin.collections.z.f19728i : list, (i10 & 4096) != 0 ? 0L : j10);
    }
}
