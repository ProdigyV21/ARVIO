package com.arflix.tv.data.api;

import androidx.fragment.app.a2;
import com.google.gson.annotations.SerializedName;
import kotlin.Metadata;
import kotlin.jvm.internal.p;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0002\b\u0012\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0087\b\u0018\u00002\u00020\u0001B/\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\u0003¢\u0006\u0004\b\n\u0010\u000bJ\t\u0010\u0014\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0015\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0016\u001a\u00020\u0006HÆ\u0003J\t\u0010\u0017\u001a\u00020\bHÆ\u0003J\t\u0010\u0018\u001a\u00020\u0003HÆ\u0003J;\u0010\u0019\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\u0003HÆ\u0001J\u0014\u0010\u001a\u001a\u00020\u001b2\b\u0010\u001c\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u001d\u001a\u00020\u0006HÖ\u0081\u0004J\n\u0010\u001e\u001a\u00020\u0003HÖ\u0081\u0004R\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0016\u0010\u0004\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\rR\u0016\u0010\u0005\u001a\u00020\u00068\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0016\u0010\u0007\u001a\u00020\b8\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0016\u0010\t\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\r¨\u0006\u001f"}, d2 = {"Lcom/arflix/tv/data/api/TraktToken;", "", "accessToken", "", "refreshToken", "expiresIn", "", "createdAt", "", "tokenType", "<init>", "(Ljava/lang/String;Ljava/lang/String;IJLjava/lang/String;)V", "getAccessToken", "()Ljava/lang/String;", "getRefreshToken", "getExpiresIn", "()I", "getCreatedAt", "()J", "getTokenType", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "", "other", "hashCode", "toString", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final /* data */ class TraktToken {
    public static final int $stable = 0;

    @SerializedName("access_token")
    private final String accessToken;

    @SerializedName("created_at")
    private final long createdAt;

    @SerializedName("expires_in")
    private final int expiresIn;

    @SerializedName("refresh_token")
    private final String refreshToken;

    @SerializedName("token_type")
    private final String tokenType;

    public TraktToken(String str, String str2, int i10, long j10, String str3) {
        this.accessToken = str;
        this.refreshToken = str2;
        this.expiresIn = i10;
        this.createdAt = j10;
        this.tokenType = str3;
    }

    public static /* synthetic */ TraktToken copy$default(TraktToken traktToken, String str, String str2, int i10, long j10, String str3, int i11, Object obj) {
        if ((i11 & 1) != 0) {
            str = traktToken.accessToken;
        }
        if ((i11 & 2) != 0) {
            str2 = traktToken.refreshToken;
        }
        if ((i11 & 4) != 0) {
            i10 = traktToken.expiresIn;
        }
        if ((i11 & 8) != 0) {
            j10 = traktToken.createdAt;
        }
        if ((i11 & 16) != 0) {
            str3 = traktToken.tokenType;
        }
        String str4 = str3;
        int i12 = i10;
        return traktToken.copy(str, str2, i12, j10, str4);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getAccessToken() {
        return this.accessToken;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getRefreshToken() {
        return this.refreshToken;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final int getExpiresIn() {
        return this.expiresIn;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final long getCreatedAt() {
        return this.createdAt;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final String getTokenType() {
        return this.tokenType;
    }

    public final TraktToken copy(String accessToken, String refreshToken, int expiresIn, long createdAt, String tokenType) {
        return new TraktToken(accessToken, refreshToken, expiresIn, createdAt, tokenType);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof TraktToken)) {
            return false;
        }
        TraktToken traktToken = (TraktToken) other;
        return p.a(this.accessToken, traktToken.accessToken) && p.a(this.refreshToken, traktToken.refreshToken) && this.expiresIn == traktToken.expiresIn && this.createdAt == traktToken.createdAt && p.a(this.tokenType, traktToken.tokenType);
    }

    public final String getAccessToken() {
        return this.accessToken;
    }

    public final long getCreatedAt() {
        return this.createdAt;
    }

    public final int getExpiresIn() {
        return this.expiresIn;
    }

    public final String getRefreshToken() {
        return this.refreshToken;
    }

    public final String getTokenType() {
        return this.tokenType;
    }

    public int hashCode() {
        int iC = (androidx.compose.foundation.c.c(this.accessToken.hashCode() * 31, 31, this.refreshToken) + this.expiresIn) * 31;
        long j10 = this.createdAt;
        return this.tokenType.hashCode() + ((iC + ((int) (j10 ^ (j10 >>> 32)))) * 31);
    }

    public String toString() {
        String str = this.accessToken;
        String str2 = this.refreshToken;
        int i10 = this.expiresIn;
        long j10 = this.createdAt;
        String str3 = this.tokenType;
        StringBuilder sbR = a2.r("TraktToken(accessToken=", str, ", refreshToken=", str2, ", expiresIn=");
        sbR.append(i10);
        sbR.append(", createdAt=");
        sbR.append(j10);
        return androidx.compose.material3.d.q(sbR, ", tokenType=", str3, ")");
    }
}
