package com.arflix.tv.data.api;

import androidx.fragment.app.a2;
import com.google.gson.annotations.SerializedName;
import kotlin.Metadata;
import kotlin.jvm.internal.p;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0010\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B3\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\u0004\b\b\u0010\tJ\t\u0010\u0011\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\u0012\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u0013\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0010\u0010\u0014\u001a\u0004\u0018\u00010\u0007HÆ\u0003¢\u0006\u0002\u0010\u000fJ<\u0010\u0015\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007HÆ\u0001¢\u0006\u0002\u0010\u0016J\u0014\u0010\u0017\u001a\u00020\u00182\b\u0010\u0019\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u001a\u001a\u00020\u001bHÖ\u0081\u0004J\n\u0010\u001c\u001a\u00020\u0003HÖ\u0081\u0004R\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0018\u0010\u0004\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u000bR\u0018\u0010\u0005\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000bR\u001a\u0010\u0006\u001a\u0004\u0018\u00010\u00078\u0006X\u0087\u0004¢\u0006\n\n\u0002\u0010\u0010\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u001d"}, d2 = {"Lcom/arflix/tv/data/api/SimklPinPollResponse;", "", "result", "", "accessToken", "tokenType", "expiresIn", "", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Long;)V", "getResult", "()Ljava/lang/String;", "getAccessToken", "getTokenType", "getExpiresIn", "()Ljava/lang/Long;", "Ljava/lang/Long;", "component1", "component2", "component3", "component4", "copy", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Long;)Lcom/arflix/tv/data/api/SimklPinPollResponse;", "equals", "", "other", "hashCode", "", "toString", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final /* data */ class SimklPinPollResponse {
    public static final int $stable = 0;

    @SerializedName("access_token")
    private final String accessToken;

    @SerializedName("expires_in")
    private final Long expiresIn;

    @SerializedName("result")
    private final String result;

    @SerializedName("token_type")
    private final String tokenType;

    public SimklPinPollResponse(String str, String str2, String str3, Long l10) {
        this.result = str;
        this.accessToken = str2;
        this.tokenType = str3;
        this.expiresIn = l10;
    }

    public static /* synthetic */ SimklPinPollResponse copy$default(SimklPinPollResponse simklPinPollResponse, String str, String str2, String str3, Long l10, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            str = simklPinPollResponse.result;
        }
        if ((i10 & 2) != 0) {
            str2 = simklPinPollResponse.accessToken;
        }
        if ((i10 & 4) != 0) {
            str3 = simklPinPollResponse.tokenType;
        }
        if ((i10 & 8) != 0) {
            l10 = simklPinPollResponse.expiresIn;
        }
        return simklPinPollResponse.copy(str, str2, str3, l10);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getResult() {
        return this.result;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getAccessToken() {
        return this.accessToken;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final String getTokenType() {
        return this.tokenType;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final Long getExpiresIn() {
        return this.expiresIn;
    }

    public final SimklPinPollResponse copy(String result, String accessToken, String tokenType, Long expiresIn) {
        return new SimklPinPollResponse(result, accessToken, tokenType, expiresIn);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof SimklPinPollResponse)) {
            return false;
        }
        SimklPinPollResponse simklPinPollResponse = (SimklPinPollResponse) other;
        return p.a(this.result, simklPinPollResponse.result) && p.a(this.accessToken, simklPinPollResponse.accessToken) && p.a(this.tokenType, simklPinPollResponse.tokenType) && p.a(this.expiresIn, simklPinPollResponse.expiresIn);
    }

    public final String getAccessToken() {
        return this.accessToken;
    }

    public final Long getExpiresIn() {
        return this.expiresIn;
    }

    public final String getResult() {
        return this.result;
    }

    public final String getTokenType() {
        return this.tokenType;
    }

    public int hashCode() {
        int iHashCode = this.result.hashCode() * 31;
        String str = this.accessToken;
        int iHashCode2 = (iHashCode + (str == null ? 0 : str.hashCode())) * 31;
        String str2 = this.tokenType;
        int iHashCode3 = (iHashCode2 + (str2 == null ? 0 : str2.hashCode())) * 31;
        Long l10 = this.expiresIn;
        return iHashCode3 + (l10 != null ? l10.hashCode() : 0);
    }

    public String toString() {
        String str = this.result;
        String str2 = this.accessToken;
        String str3 = this.tokenType;
        Long l10 = this.expiresIn;
        StringBuilder sbR = a2.r("SimklPinPollResponse(result=", str, ", accessToken=", str2, ", tokenType=");
        sbR.append(str3);
        sbR.append(", expiresIn=");
        sbR.append(l10);
        sbR.append(")");
        return sbR.toString();
    }

    public /* synthetic */ SimklPinPollResponse(String str, String str2, String str3, Long l10, int i10, kotlin.jvm.internal.h hVar) {
        this(str, (i10 & 2) != 0 ? null : str2, (i10 & 4) != 0 ? null : str3, (i10 & 8) != 0 ? null : l10);
    }
}
