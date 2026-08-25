package com.arflix.tv.data.api;

import androidx.fragment.app.a2;
import com.google.gson.annotations.SerializedName;
import kotlin.Metadata;
import kotlin.jvm.internal.p;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0010\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B)\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0003¢\u0006\u0004\b\u0007\u0010\bJ\t\u0010\u000e\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000f\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0010\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0011\u001a\u00020\u0003HÆ\u0003J1\u0010\u0012\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u0003HÆ\u0001J\u0014\u0010\u0013\u001a\u00020\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0016\u001a\u00020\u0017HÖ\u0081\u0004J\n\u0010\u0018\u001a\u00020\u0003HÖ\u0081\u0004R\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0016\u0010\u0004\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\nR\u0016\u0010\u0005\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\nR\u0016\u0010\u0006\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\n¨\u0006\u0019"}, d2 = {"Lcom/arflix/tv/data/api/RefreshTokenRequest;", "", "refreshToken", "", "clientId", "clientSecret", "grantType", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getRefreshToken", "()Ljava/lang/String;", "getClientId", "getClientSecret", "getGrantType", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "hashCode", "", "toString", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final /* data */ class RefreshTokenRequest {
    public static final int $stable = 0;

    @SerializedName("client_id")
    private final String clientId;

    @SerializedName("client_secret")
    private final String clientSecret;

    @SerializedName("grant_type")
    private final String grantType;

    @SerializedName("refresh_token")
    private final String refreshToken;

    public RefreshTokenRequest(String str, String str2, String str3, String str4) {
        this.refreshToken = str;
        this.clientId = str2;
        this.clientSecret = str3;
        this.grantType = str4;
    }

    public static /* synthetic */ RefreshTokenRequest copy$default(RefreshTokenRequest refreshTokenRequest, String str, String str2, String str3, String str4, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            str = refreshTokenRequest.refreshToken;
        }
        if ((i10 & 2) != 0) {
            str2 = refreshTokenRequest.clientId;
        }
        if ((i10 & 4) != 0) {
            str3 = refreshTokenRequest.clientSecret;
        }
        if ((i10 & 8) != 0) {
            str4 = refreshTokenRequest.grantType;
        }
        return refreshTokenRequest.copy(str, str2, str3, str4);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getRefreshToken() {
        return this.refreshToken;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getClientId() {
        return this.clientId;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final String getClientSecret() {
        return this.clientSecret;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final String getGrantType() {
        return this.grantType;
    }

    public final RefreshTokenRequest copy(String refreshToken, String clientId, String clientSecret, String grantType) {
        return new RefreshTokenRequest(refreshToken, clientId, clientSecret, grantType);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof RefreshTokenRequest)) {
            return false;
        }
        RefreshTokenRequest refreshTokenRequest = (RefreshTokenRequest) other;
        return p.a(this.refreshToken, refreshTokenRequest.refreshToken) && p.a(this.clientId, refreshTokenRequest.clientId) && p.a(this.clientSecret, refreshTokenRequest.clientSecret) && p.a(this.grantType, refreshTokenRequest.grantType);
    }

    public final String getClientId() {
        return this.clientId;
    }

    public final String getClientSecret() {
        return this.clientSecret;
    }

    public final String getGrantType() {
        return this.grantType;
    }

    public final String getRefreshToken() {
        return this.refreshToken;
    }

    public int hashCode() {
        return this.grantType.hashCode() + androidx.compose.foundation.c.c(androidx.compose.foundation.c.c(this.refreshToken.hashCode() * 31, 31, this.clientId), 31, this.clientSecret);
    }

    public String toString() {
        String str = this.refreshToken;
        String str2 = this.clientId;
        return a2.n(a2.r("RefreshTokenRequest(refreshToken=", str, ", clientId=", str2, ", clientSecret="), this.clientSecret, ", grantType=", this.grantType, ")");
    }

    public /* synthetic */ RefreshTokenRequest(String str, String str2, String str3, String str4, int i10, kotlin.jvm.internal.h hVar) {
        this(str, str2, str3, (i10 & 8) != 0 ? "refresh_token" : str4);
    }
}
