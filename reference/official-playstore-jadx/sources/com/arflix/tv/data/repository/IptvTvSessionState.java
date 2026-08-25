package com.arflix.tv.data.repository;

import androidx.fragment.app.a2;
import java.util.List;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\t\n\u0000\n\u0002\u0010 \n\u0002\b\u0011\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B?\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007\u0012\u000e\b\u0002\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00030\t¢\u0006\u0004\b\n\u0010\u000bJ\t\u0010\u0014\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0015\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0016\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0017\u001a\u00020\u0007HÆ\u0003J\u000f\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00030\tHÆ\u0003JA\u0010\u0019\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00072\u000e\b\u0002\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00030\tHÆ\u0001J\u0014\u0010\u001a\u001a\u00020\u001b2\b\u0010\u001c\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u001d\u001a\u00020\u001eHÖ\u0081\u0004J\n\u0010\u001f\u001a\u00020\u0003HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\rR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\rR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0017\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00030\t¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013¨\u0006 "}, d2 = {"Lcom/arflix/tv/data/repository/IptvTvSessionState;", "", "lastChannelId", "", "lastGroupName", "lastFocusedZone", "lastOpenedAt", "", "recentChannelIds", "", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;JLjava/util/List;)V", "getLastChannelId", "()Ljava/lang/String;", "getLastGroupName", "getLastFocusedZone", "getLastOpenedAt", "()J", "getRecentChannelIds", "()Ljava/util/List;", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "", "other", "hashCode", "", "toString", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final /* data */ class IptvTvSessionState {
    public static final int $stable = 0;
    private final String lastChannelId;
    private final String lastFocusedZone;
    private final String lastGroupName;
    private final long lastOpenedAt;
    private final List<String> recentChannelIds;

    public IptvTvSessionState() {
        this(null, null, null, 0L, null, 31, null);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ IptvTvSessionState copy$default(IptvTvSessionState iptvTvSessionState, String str, String str2, String str3, long j10, List list, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            str = iptvTvSessionState.lastChannelId;
        }
        if ((i10 & 2) != 0) {
            str2 = iptvTvSessionState.lastGroupName;
        }
        if ((i10 & 4) != 0) {
            str3 = iptvTvSessionState.lastFocusedZone;
        }
        if ((i10 & 8) != 0) {
            j10 = iptvTvSessionState.lastOpenedAt;
        }
        if ((i10 & 16) != 0) {
            list = iptvTvSessionState.recentChannelIds;
        }
        List list2 = list;
        String str4 = str3;
        return iptvTvSessionState.copy(str, str2, str4, j10, list2);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getLastChannelId() {
        return this.lastChannelId;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getLastGroupName() {
        return this.lastGroupName;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final String getLastFocusedZone() {
        return this.lastFocusedZone;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final long getLastOpenedAt() {
        return this.lastOpenedAt;
    }

    public final List<String> component5() {
        return this.recentChannelIds;
    }

    public final IptvTvSessionState copy(String lastChannelId, String lastGroupName, String lastFocusedZone, long lastOpenedAt, List<String> recentChannelIds) {
        return new IptvTvSessionState(lastChannelId, lastGroupName, lastFocusedZone, lastOpenedAt, recentChannelIds);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof IptvTvSessionState)) {
            return false;
        }
        IptvTvSessionState iptvTvSessionState = (IptvTvSessionState) other;
        return kotlin.jvm.internal.p.a(this.lastChannelId, iptvTvSessionState.lastChannelId) && kotlin.jvm.internal.p.a(this.lastGroupName, iptvTvSessionState.lastGroupName) && kotlin.jvm.internal.p.a(this.lastFocusedZone, iptvTvSessionState.lastFocusedZone) && this.lastOpenedAt == iptvTvSessionState.lastOpenedAt && kotlin.jvm.internal.p.a(this.recentChannelIds, iptvTvSessionState.recentChannelIds);
    }

    public final String getLastChannelId() {
        return this.lastChannelId;
    }

    public final String getLastFocusedZone() {
        return this.lastFocusedZone;
    }

    public final String getLastGroupName() {
        return this.lastGroupName;
    }

    public final long getLastOpenedAt() {
        return this.lastOpenedAt;
    }

    public final List<String> getRecentChannelIds() {
        return this.recentChannelIds;
    }

    public int hashCode() {
        int iC = androidx.compose.foundation.c.c(androidx.compose.foundation.c.c(this.lastChannelId.hashCode() * 31, 31, this.lastGroupName), 31, this.lastFocusedZone);
        long j10 = this.lastOpenedAt;
        return this.recentChannelIds.hashCode() + ((iC + ((int) (j10 ^ (j10 >>> 32)))) * 31);
    }

    public String toString() {
        String str = this.lastChannelId;
        String str2 = this.lastGroupName;
        String str3 = this.lastFocusedZone;
        long j10 = this.lastOpenedAt;
        List<String> list = this.recentChannelIds;
        StringBuilder sbR = a2.r("IptvTvSessionState(lastChannelId=", str, ", lastGroupName=", str2, ", lastFocusedZone=");
        sbR.append(str3);
        sbR.append(", lastOpenedAt=");
        sbR.append(j10);
        sbR.append(", recentChannelIds=");
        sbR.append(list);
        sbR.append(")");
        return sbR.toString();
    }

    public IptvTvSessionState(String str, String str2, String str3, long j10, List<String> list) {
        this.lastChannelId = str;
        this.lastGroupName = str2;
        this.lastFocusedZone = str3;
        this.lastOpenedAt = j10;
        this.recentChannelIds = list;
    }

    public /* synthetic */ IptvTvSessionState(String str, String str2, String str3, long j10, List list, int i10, kotlin.jvm.internal.h hVar) {
        this((i10 & 1) != 0 ? "" : str, (i10 & 2) != 0 ? "" : str2, (i10 & 4) != 0 ? "GUIDE" : str3, (i10 & 8) != 0 ? 0L : j10, (i10 & 16) != 0 ? kotlin.collections.z.f19728i : list);
    }
}
