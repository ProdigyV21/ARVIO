package com.arflix.tv.data.repository;

import kotlin.Metadata;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u000f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B/\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0007\u001a\u00020\b¢\u0006\u0004\b\t\u0010\nJ\t\u0010\u0012\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\u0013\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\t\u0010\u0014\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0015\u001a\u00020\bHÆ\u0003J3\u0010\u0016\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\bHÆ\u0001J\u0014\u0010\u0017\u001a\u00020\u00182\b\u0010\u0019\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u001a\u001a\u00020\u001bHÖ\u0081\u0004J\n\u0010\u001c\u001a\u00020\u0005HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u000eR\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011¨\u0006\u001d"}, d2 = {"Lcom/arflix/tv/data/repository/CloudSyncInvalidation;", "", "scope", "Lcom/arflix/tv/data/repository/CloudSyncScope;", "profileId", "", "reason", "changedAt", "", "<init>", "(Lcom/arflix/tv/data/repository/CloudSyncScope;Ljava/lang/String;Ljava/lang/String;J)V", "getScope", "()Lcom/arflix/tv/data/repository/CloudSyncScope;", "getProfileId", "()Ljava/lang/String;", "getReason", "getChangedAt", "()J", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "hashCode", "", "toString", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final /* data */ class CloudSyncInvalidation {
    public static final int $stable = 0;
    private final long changedAt;
    private final String profileId;
    private final String reason;
    private final CloudSyncScope scope;

    public CloudSyncInvalidation(CloudSyncScope cloudSyncScope, String str, String str2, long j10) {
        this.scope = cloudSyncScope;
        this.profileId = str;
        this.reason = str2;
        this.changedAt = j10;
    }

    public static /* synthetic */ CloudSyncInvalidation copy$default(CloudSyncInvalidation cloudSyncInvalidation, CloudSyncScope cloudSyncScope, String str, String str2, long j10, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            cloudSyncScope = cloudSyncInvalidation.scope;
        }
        if ((i10 & 2) != 0) {
            str = cloudSyncInvalidation.profileId;
        }
        if ((i10 & 4) != 0) {
            str2 = cloudSyncInvalidation.reason;
        }
        if ((i10 & 8) != 0) {
            j10 = cloudSyncInvalidation.changedAt;
        }
        String str3 = str2;
        return cloudSyncInvalidation.copy(cloudSyncScope, str, str3, j10);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final CloudSyncScope getScope() {
        return this.scope;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getProfileId() {
        return this.profileId;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final String getReason() {
        return this.reason;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final long getChangedAt() {
        return this.changedAt;
    }

    public final CloudSyncInvalidation copy(CloudSyncScope scope, String profileId, String reason, long changedAt) {
        return new CloudSyncInvalidation(scope, profileId, reason, changedAt);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof CloudSyncInvalidation)) {
            return false;
        }
        CloudSyncInvalidation cloudSyncInvalidation = (CloudSyncInvalidation) other;
        return this.scope == cloudSyncInvalidation.scope && kotlin.jvm.internal.p.a(this.profileId, cloudSyncInvalidation.profileId) && kotlin.jvm.internal.p.a(this.reason, cloudSyncInvalidation.reason) && this.changedAt == cloudSyncInvalidation.changedAt;
    }

    public final long getChangedAt() {
        return this.changedAt;
    }

    public final String getProfileId() {
        return this.profileId;
    }

    public final String getReason() {
        return this.reason;
    }

    public final CloudSyncScope getScope() {
        return this.scope;
    }

    public int hashCode() {
        int iHashCode = this.scope.hashCode() * 31;
        String str = this.profileId;
        int iC = androidx.compose.foundation.c.c((iHashCode + (str == null ? 0 : str.hashCode())) * 31, 31, this.reason);
        long j10 = this.changedAt;
        return iC + ((int) (j10 ^ (j10 >>> 32)));
    }

    public String toString() {
        return "CloudSyncInvalidation(scope=" + this.scope + ", profileId=" + this.profileId + ", reason=" + this.reason + ", changedAt=" + this.changedAt + ")";
    }

    public /* synthetic */ CloudSyncInvalidation(CloudSyncScope cloudSyncScope, String str, String str2, long j10, int i10, kotlin.jvm.internal.h hVar) {
        this(cloudSyncScope, (i10 & 2) != 0 ? null : str, (i10 & 4) != 0 ? "" : str2, (i10 & 8) != 0 ? System.currentTimeMillis() : j10);
    }
}
