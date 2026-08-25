package com.arflix.tv.data.repository;

import kotlin.Metadata;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0013\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B?\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0005¢\u0006\u0004\b\t\u0010\nJ\t\u0010\u0012\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\u0013\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010\u0014\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010\u0015\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010\u0016\u001a\u0004\u0018\u00010\u0005HÆ\u0003JC\u0010\u0017\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0005HÆ\u0001J\u0014\u0010\u0018\u001a\u00020\u00192\b\u0010\u001a\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u001b\u001a\u00020\u001cHÖ\u0081\u0004J\n\u0010\u001d\u001a\u00020\u0005HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u000eR\u0013\u0010\u0007\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000eR\u0013\u0010\b\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u000e¨\u0006\u001e"}, d2 = {"Lcom/arflix/tv/data/repository/TvDeviceAuthStatus;", "", "status", "Lcom/arflix/tv/data/repository/TvDeviceAuthStatusType;", "accessToken", "", "refreshToken", "email", "message", "<init>", "(Lcom/arflix/tv/data/repository/TvDeviceAuthStatusType;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getStatus", "()Lcom/arflix/tv/data/repository/TvDeviceAuthStatusType;", "getAccessToken", "()Ljava/lang/String;", "getRefreshToken", "getEmail", "getMessage", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "", "other", "hashCode", "", "toString", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final /* data */ class TvDeviceAuthStatus {
    public static final int $stable = 0;
    private final String accessToken;
    private final String email;
    private final String message;
    private final String refreshToken;
    private final TvDeviceAuthStatusType status;

    public TvDeviceAuthStatus(TvDeviceAuthStatusType tvDeviceAuthStatusType, String str, String str2, String str3, String str4) {
        this.status = tvDeviceAuthStatusType;
        this.accessToken = str;
        this.refreshToken = str2;
        this.email = str3;
        this.message = str4;
    }

    public static /* synthetic */ TvDeviceAuthStatus copy$default(TvDeviceAuthStatus tvDeviceAuthStatus, TvDeviceAuthStatusType tvDeviceAuthStatusType, String str, String str2, String str3, String str4, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            tvDeviceAuthStatusType = tvDeviceAuthStatus.status;
        }
        if ((i10 & 2) != 0) {
            str = tvDeviceAuthStatus.accessToken;
        }
        if ((i10 & 4) != 0) {
            str2 = tvDeviceAuthStatus.refreshToken;
        }
        if ((i10 & 8) != 0) {
            str3 = tvDeviceAuthStatus.email;
        }
        if ((i10 & 16) != 0) {
            str4 = tvDeviceAuthStatus.message;
        }
        String str5 = str4;
        String str6 = str2;
        return tvDeviceAuthStatus.copy(tvDeviceAuthStatusType, str, str6, str3, str5);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final TvDeviceAuthStatusType getStatus() {
        return this.status;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getAccessToken() {
        return this.accessToken;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final String getRefreshToken() {
        return this.refreshToken;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final String getEmail() {
        return this.email;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final String getMessage() {
        return this.message;
    }

    public final TvDeviceAuthStatus copy(TvDeviceAuthStatusType status, String accessToken, String refreshToken, String email, String message) {
        return new TvDeviceAuthStatus(status, accessToken, refreshToken, email, message);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof TvDeviceAuthStatus)) {
            return false;
        }
        TvDeviceAuthStatus tvDeviceAuthStatus = (TvDeviceAuthStatus) other;
        return this.status == tvDeviceAuthStatus.status && kotlin.jvm.internal.p.a(this.accessToken, tvDeviceAuthStatus.accessToken) && kotlin.jvm.internal.p.a(this.refreshToken, tvDeviceAuthStatus.refreshToken) && kotlin.jvm.internal.p.a(this.email, tvDeviceAuthStatus.email) && kotlin.jvm.internal.p.a(this.message, tvDeviceAuthStatus.message);
    }

    public final String getAccessToken() {
        return this.accessToken;
    }

    public final String getEmail() {
        return this.email;
    }

    public final String getMessage() {
        return this.message;
    }

    public final String getRefreshToken() {
        return this.refreshToken;
    }

    public final TvDeviceAuthStatusType getStatus() {
        return this.status;
    }

    public int hashCode() {
        int iHashCode = this.status.hashCode() * 31;
        String str = this.accessToken;
        int iHashCode2 = (iHashCode + (str == null ? 0 : str.hashCode())) * 31;
        String str2 = this.refreshToken;
        int iHashCode3 = (iHashCode2 + (str2 == null ? 0 : str2.hashCode())) * 31;
        String str3 = this.email;
        int iHashCode4 = (iHashCode3 + (str3 == null ? 0 : str3.hashCode())) * 31;
        String str4 = this.message;
        return iHashCode4 + (str4 != null ? str4.hashCode() : 0);
    }

    public String toString() {
        TvDeviceAuthStatusType tvDeviceAuthStatusType = this.status;
        String str = this.accessToken;
        String str2 = this.refreshToken;
        String str3 = this.email;
        String str4 = this.message;
        StringBuilder sb2 = new StringBuilder("TvDeviceAuthStatus(status=");
        sb2.append(tvDeviceAuthStatusType);
        sb2.append(", accessToken=");
        sb2.append(str);
        sb2.append(", refreshToken=");
        y.a.i(sb2, str2, ", email=", str3, ", message=");
        return a0.c.p(sb2, str4, ")");
    }

    public /* synthetic */ TvDeviceAuthStatus(TvDeviceAuthStatusType tvDeviceAuthStatusType, String str, String str2, String str3, String str4, int i10, kotlin.jvm.internal.h hVar) {
        this(tvDeviceAuthStatusType, (i10 & 2) != 0 ? null : str, (i10 & 4) != 0 ? null : str2, (i10 & 8) != 0 ? null : str3, (i10 & 16) != 0 ? null : str4);
    }
}
