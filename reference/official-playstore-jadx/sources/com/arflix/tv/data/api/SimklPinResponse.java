package com.arflix.tv.data.api;

import androidx.fragment.app.a2;
import com.google.android.gms.cast.MediaError;
import com.google.gson.annotations.SerializedName;
import kotlin.Metadata;
import kotlin.jvm.internal.p;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0012\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0087\b\u0018\u00002\u00020\u0001B7\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0006\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0006\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\t\u0010\nJ\t\u0010\u0012\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0013\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0014\u001a\u00020\u0006HÆ\u0003J\t\u0010\u0015\u001a\u00020\u0006HÆ\u0003J\u000b\u0010\u0016\u001a\u0004\u0018\u00010\u0003HÆ\u0003J=\u0010\u0017\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\u00062\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0014\u0010\u0018\u001a\u00020\u00192\b\u0010\u001a\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u001b\u001a\u00020\u0006HÖ\u0081\u0004J\n\u0010\u001c\u001a\u00020\u0003HÖ\u0081\u0004R\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0016\u0010\u0004\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\fR\u0016\u0010\u0005\u001a\u00020\u00068\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0016\u0010\u0007\u001a\u00020\u00068\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000fR\u0018\u0010\b\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\f¨\u0006\u001d"}, d2 = {"Lcom/arflix/tv/data/api/SimklPinResponse;", "", "userCode", "", "verificationUrl", "expiresIn", "", "interval", "deviceCode", "<init>", "(Ljava/lang/String;Ljava/lang/String;IILjava/lang/String;)V", "getUserCode", "()Ljava/lang/String;", "getVerificationUrl", "getExpiresIn", "()I", "getInterval", "getDeviceCode", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "", "other", "hashCode", "toString", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final /* data */ class SimklPinResponse {
    public static final int $stable = 0;

    @SerializedName("device_code")
    private final String deviceCode;

    @SerializedName("expires_in")
    private final int expiresIn;

    @SerializedName("interval")
    private final int interval;

    @SerializedName("user_code")
    private final String userCode;

    @SerializedName("verification_url")
    private final String verificationUrl;

    public SimklPinResponse(String str, String str2, int i10, int i11, String str3) {
        this.userCode = str;
        this.verificationUrl = str2;
        this.expiresIn = i10;
        this.interval = i11;
        this.deviceCode = str3;
    }

    public static /* synthetic */ SimklPinResponse copy$default(SimklPinResponse simklPinResponse, String str, String str2, int i10, int i11, String str3, int i12, Object obj) {
        if ((i12 & 1) != 0) {
            str = simklPinResponse.userCode;
        }
        if ((i12 & 2) != 0) {
            str2 = simklPinResponse.verificationUrl;
        }
        if ((i12 & 4) != 0) {
            i10 = simklPinResponse.expiresIn;
        }
        if ((i12 & 8) != 0) {
            i11 = simklPinResponse.interval;
        }
        if ((i12 & 16) != 0) {
            str3 = simklPinResponse.deviceCode;
        }
        String str4 = str3;
        int i13 = i10;
        return simklPinResponse.copy(str, str2, i13, i11, str4);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getUserCode() {
        return this.userCode;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getVerificationUrl() {
        return this.verificationUrl;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final int getExpiresIn() {
        return this.expiresIn;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final int getInterval() {
        return this.interval;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final String getDeviceCode() {
        return this.deviceCode;
    }

    public final SimklPinResponse copy(String userCode, String verificationUrl, int expiresIn, int interval, String deviceCode) {
        return new SimklPinResponse(userCode, verificationUrl, expiresIn, interval, deviceCode);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof SimklPinResponse)) {
            return false;
        }
        SimklPinResponse simklPinResponse = (SimklPinResponse) other;
        return p.a(this.userCode, simklPinResponse.userCode) && p.a(this.verificationUrl, simklPinResponse.verificationUrl) && this.expiresIn == simklPinResponse.expiresIn && this.interval == simklPinResponse.interval && p.a(this.deviceCode, simklPinResponse.deviceCode);
    }

    public final String getDeviceCode() {
        return this.deviceCode;
    }

    public final int getExpiresIn() {
        return this.expiresIn;
    }

    public final int getInterval() {
        return this.interval;
    }

    public final String getUserCode() {
        return this.userCode;
    }

    public final String getVerificationUrl() {
        return this.verificationUrl;
    }

    public int hashCode() {
        int iC = (((androidx.compose.foundation.c.c(this.userCode.hashCode() * 31, 31, this.verificationUrl) + this.expiresIn) * 31) + this.interval) * 31;
        String str = this.deviceCode;
        return iC + (str == null ? 0 : str.hashCode());
    }

    public String toString() {
        String str = this.userCode;
        String str2 = this.verificationUrl;
        int i10 = this.expiresIn;
        int i11 = this.interval;
        String str3 = this.deviceCode;
        StringBuilder sbR = a2.r("SimklPinResponse(userCode=", str, ", verificationUrl=", str2, ", expiresIn=");
        a2.y(sbR, i10, ", interval=", i11, ", deviceCode=");
        return a0.c.p(sbR, str3, ")");
    }

    public /* synthetic */ SimklPinResponse(String str, String str2, int i10, int i11, String str3, int i12, kotlin.jvm.internal.h hVar) {
        this(str, str2, (i12 & 4) != 0 ? MediaError.DetailedErrorCode.TEXT_UNKNOWN : i10, (i12 & 8) != 0 ? 5 : i11, (i12 & 16) != 0 ? null : str3);
    }
}
