package com.arflix.tv.data.api;

import androidx.fragment.app.a2;
import com.google.gson.annotations.SerializedName;
import kotlin.Metadata;
import kotlin.jvm.internal.p;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0011\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0087\b\u0018\u00002\u00020\u0001B/\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\u0007¢\u0006\u0004\b\t\u0010\nJ\t\u0010\u0012\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0013\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0014\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0015\u001a\u00020\u0007HÆ\u0003J\t\u0010\u0016\u001a\u00020\u0007HÆ\u0003J;\u0010\u0017\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\u0007HÆ\u0001J\u0014\u0010\u0018\u001a\u00020\u00192\b\u0010\u001a\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u001b\u001a\u00020\u0007HÖ\u0081\u0004J\n\u0010\u001c\u001a\u00020\u0003HÖ\u0081\u0004R\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0016\u0010\u0004\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\fR\u0016\u0010\u0005\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\fR\u0016\u0010\u0006\u001a\u00020\u00078\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\b\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0010¨\u0006\u001d"}, d2 = {"Lcom/arflix/tv/data/api/TraktDeviceCode;", "", "deviceCode", "", "userCode", "verificationUrl", "expiresIn", "", "interval", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;II)V", "getDeviceCode", "()Ljava/lang/String;", "getUserCode", "getVerificationUrl", "getExpiresIn", "()I", "getInterval", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "", "other", "hashCode", "toString", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final /* data */ class TraktDeviceCode {
    public static final int $stable = 0;

    @SerializedName("device_code")
    private final String deviceCode;

    @SerializedName("expires_in")
    private final int expiresIn;
    private final int interval;

    @SerializedName("user_code")
    private final String userCode;

    @SerializedName("verification_url")
    private final String verificationUrl;

    public TraktDeviceCode(String str, String str2, String str3, int i10, int i11) {
        this.deviceCode = str;
        this.userCode = str2;
        this.verificationUrl = str3;
        this.expiresIn = i10;
        this.interval = i11;
    }

    public static /* synthetic */ TraktDeviceCode copy$default(TraktDeviceCode traktDeviceCode, String str, String str2, String str3, int i10, int i11, int i12, Object obj) {
        if ((i12 & 1) != 0) {
            str = traktDeviceCode.deviceCode;
        }
        if ((i12 & 2) != 0) {
            str2 = traktDeviceCode.userCode;
        }
        if ((i12 & 4) != 0) {
            str3 = traktDeviceCode.verificationUrl;
        }
        if ((i12 & 8) != 0) {
            i10 = traktDeviceCode.expiresIn;
        }
        if ((i12 & 16) != 0) {
            i11 = traktDeviceCode.interval;
        }
        int i13 = i11;
        String str4 = str3;
        return traktDeviceCode.copy(str, str2, str4, i10, i13);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getDeviceCode() {
        return this.deviceCode;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getUserCode() {
        return this.userCode;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final String getVerificationUrl() {
        return this.verificationUrl;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final int getExpiresIn() {
        return this.expiresIn;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final int getInterval() {
        return this.interval;
    }

    public final TraktDeviceCode copy(String deviceCode, String userCode, String verificationUrl, int expiresIn, int interval) {
        return new TraktDeviceCode(deviceCode, userCode, verificationUrl, expiresIn, interval);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof TraktDeviceCode)) {
            return false;
        }
        TraktDeviceCode traktDeviceCode = (TraktDeviceCode) other;
        return p.a(this.deviceCode, traktDeviceCode.deviceCode) && p.a(this.userCode, traktDeviceCode.userCode) && p.a(this.verificationUrl, traktDeviceCode.verificationUrl) && this.expiresIn == traktDeviceCode.expiresIn && this.interval == traktDeviceCode.interval;
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
        return ((androidx.compose.foundation.c.c(androidx.compose.foundation.c.c(this.deviceCode.hashCode() * 31, 31, this.userCode), 31, this.verificationUrl) + this.expiresIn) * 31) + this.interval;
    }

    public String toString() {
        String str = this.deviceCode;
        String str2 = this.userCode;
        String str3 = this.verificationUrl;
        int i10 = this.expiresIn;
        int i11 = this.interval;
        StringBuilder sbR = a2.r("TraktDeviceCode(deviceCode=", str, ", userCode=", str2, ", verificationUrl=");
        androidx.compose.material3.d.A(sbR, str3, ", expiresIn=", i10, ", interval=");
        return androidx.compose.material3.d.j(i11, ")", sbR);
    }
}
