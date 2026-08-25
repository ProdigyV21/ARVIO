package com.arflix.tv.data.api;

import androidx.fragment.app.a2;
import com.google.gson.annotations.SerializedName;
import kotlin.Metadata;
import kotlin.jvm.internal.p;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\r\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003¢\u0006\u0004\b\u0006\u0010\u0007J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\t\u0010\r\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000e\u001a\u00020\u0003HÆ\u0003J'\u0010\u000f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0003HÆ\u0001J\u0014\u0010\u0010\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0013\u001a\u00020\u0014HÖ\u0081\u0004J\n\u0010\u0015\u001a\u00020\u0003HÖ\u0081\u0004R\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0016\u0010\u0004\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\tR\u0016\u0010\u0005\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\t¨\u0006\u0016"}, d2 = {"Lcom/arflix/tv/data/api/TokenPollRequest;", "", "code", "", "clientId", "clientSecret", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getCode", "()Ljava/lang/String;", "getClientId", "getClientSecret", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "", "toString", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final /* data */ class TokenPollRequest {
    public static final int $stable = 0;

    @SerializedName("client_id")
    private final String clientId;

    @SerializedName("client_secret")
    private final String clientSecret;

    @SerializedName("code")
    private final String code;

    public TokenPollRequest(String str, String str2, String str3) {
        this.code = str;
        this.clientId = str2;
        this.clientSecret = str3;
    }

    public static /* synthetic */ TokenPollRequest copy$default(TokenPollRequest tokenPollRequest, String str, String str2, String str3, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            str = tokenPollRequest.code;
        }
        if ((i10 & 2) != 0) {
            str2 = tokenPollRequest.clientId;
        }
        if ((i10 & 4) != 0) {
            str3 = tokenPollRequest.clientSecret;
        }
        return tokenPollRequest.copy(str, str2, str3);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getCode() {
        return this.code;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getClientId() {
        return this.clientId;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final String getClientSecret() {
        return this.clientSecret;
    }

    public final TokenPollRequest copy(String code, String clientId, String clientSecret) {
        return new TokenPollRequest(code, clientId, clientSecret);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof TokenPollRequest)) {
            return false;
        }
        TokenPollRequest tokenPollRequest = (TokenPollRequest) other;
        return p.a(this.code, tokenPollRequest.code) && p.a(this.clientId, tokenPollRequest.clientId) && p.a(this.clientSecret, tokenPollRequest.clientSecret);
    }

    public final String getClientId() {
        return this.clientId;
    }

    public final String getClientSecret() {
        return this.clientSecret;
    }

    public final String getCode() {
        return this.code;
    }

    public int hashCode() {
        return this.clientSecret.hashCode() + androidx.compose.foundation.c.c(this.code.hashCode() * 31, 31, this.clientId);
    }

    public String toString() {
        String str = this.code;
        String str2 = this.clientId;
        return a0.c.p(a2.r("TokenPollRequest(code=", str, ", clientId=", str2, ", clientSecret="), this.clientSecret, ")");
    }
}
