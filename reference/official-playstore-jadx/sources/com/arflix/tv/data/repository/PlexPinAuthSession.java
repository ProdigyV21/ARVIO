package com.arflix.tv.data.repository;

import androidx.fragment.app.a2;
import androidx.media3.extractor.text.ttml.TtmlNode;
import com.google.android.gms.cast.MediaError;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0018\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0087\b\u0018\u00002\u00020\u0001BW\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0007\u001a\u00020\b\u0012\b\b\u0002\u0010\t\u001a\u00020\b\u0012\b\b\u0002\u0010\n\u001a\u00020\u000b\u0012\b\b\u0002\u0010\f\u001a\u00020\u0003¢\u0006\u0004\b\r\u0010\u000eJ\t\u0010\u001a\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001b\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001c\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001d\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001e\u001a\u00020\bHÆ\u0003J\t\u0010\u001f\u001a\u00020\bHÆ\u0003J\t\u0010 \u001a\u00020\u000bHÆ\u0003J\t\u0010!\u001a\u00020\u0003HÆ\u0003JY\u0010\"\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\b2\b\b\u0002\u0010\n\u001a\u00020\u000b2\b\b\u0002\u0010\f\u001a\u00020\u0003HÆ\u0001J\u0014\u0010#\u001a\u00020$2\b\u0010%\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010&\u001a\u00020\bHÖ\u0081\u0004J\n\u0010'\u001a\u00020\u0003HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0010R\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0010R\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0010R\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0011\u0010\t\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0015R\u0011\u0010\n\u001a\u00020\u000b¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u0011\u0010\f\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0010¨\u0006("}, d2 = {"Lcom/arflix/tv/data/repository/PlexPinAuthSession;", "", TtmlNode.ATTR_ID, "", "secret", "code", "verificationUrl", "expiresIn", "", "interval", "serverKind", "Lcom/arflix/tv/data/repository/HomeServerKind;", "serverUrl", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;IILcom/arflix/tv/data/repository/HomeServerKind;Ljava/lang/String;)V", "getId", "()Ljava/lang/String;", "getSecret", "getCode", "getVerificationUrl", "getExpiresIn", "()I", "getInterval", "getServerKind", "()Lcom/arflix/tv/data/repository/HomeServerKind;", "getServerUrl", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "copy", "equals", "", "other", "hashCode", "toString", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final /* data */ class PlexPinAuthSession {
    public static final int $stable = 0;
    private final String code;
    private final int expiresIn;
    private final String id;
    private final int interval;
    private final String secret;
    private final HomeServerKind serverKind;
    private final String serverUrl;
    private final String verificationUrl;

    public PlexPinAuthSession() {
        this(null, null, null, null, 0, 0, null, null, 255, null);
    }

    public static /* synthetic */ PlexPinAuthSession copy$default(PlexPinAuthSession plexPinAuthSession, String str, String str2, String str3, String str4, int i10, int i11, HomeServerKind homeServerKind, String str5, int i12, Object obj) {
        if ((i12 & 1) != 0) {
            str = plexPinAuthSession.id;
        }
        if ((i12 & 2) != 0) {
            str2 = plexPinAuthSession.secret;
        }
        if ((i12 & 4) != 0) {
            str3 = plexPinAuthSession.code;
        }
        if ((i12 & 8) != 0) {
            str4 = plexPinAuthSession.verificationUrl;
        }
        if ((i12 & 16) != 0) {
            i10 = plexPinAuthSession.expiresIn;
        }
        if ((i12 & 32) != 0) {
            i11 = plexPinAuthSession.interval;
        }
        if ((i12 & 64) != 0) {
            homeServerKind = plexPinAuthSession.serverKind;
        }
        if ((i12 & 128) != 0) {
            str5 = plexPinAuthSession.serverUrl;
        }
        HomeServerKind homeServerKind2 = homeServerKind;
        String str6 = str5;
        int i13 = i10;
        int i14 = i11;
        return plexPinAuthSession.copy(str, str2, str3, str4, i13, i14, homeServerKind2, str6);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getId() {
        return this.id;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getSecret() {
        return this.secret;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final String getCode() {
        return this.code;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final String getVerificationUrl() {
        return this.verificationUrl;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final int getExpiresIn() {
        return this.expiresIn;
    }

    /* JADX INFO: renamed from: component6, reason: from getter */
    public final int getInterval() {
        return this.interval;
    }

    /* JADX INFO: renamed from: component7, reason: from getter */
    public final HomeServerKind getServerKind() {
        return this.serverKind;
    }

    /* JADX INFO: renamed from: component8, reason: from getter */
    public final String getServerUrl() {
        return this.serverUrl;
    }

    public final PlexPinAuthSession copy(String id, String secret, String code, String verificationUrl, int expiresIn, int interval, HomeServerKind serverKind, String serverUrl) {
        return new PlexPinAuthSession(id, secret, code, verificationUrl, expiresIn, interval, serverKind, serverUrl);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof PlexPinAuthSession)) {
            return false;
        }
        PlexPinAuthSession plexPinAuthSession = (PlexPinAuthSession) other;
        return kotlin.jvm.internal.p.a(this.id, plexPinAuthSession.id) && kotlin.jvm.internal.p.a(this.secret, plexPinAuthSession.secret) && kotlin.jvm.internal.p.a(this.code, plexPinAuthSession.code) && kotlin.jvm.internal.p.a(this.verificationUrl, plexPinAuthSession.verificationUrl) && this.expiresIn == plexPinAuthSession.expiresIn && this.interval == plexPinAuthSession.interval && this.serverKind == plexPinAuthSession.serverKind && kotlin.jvm.internal.p.a(this.serverUrl, plexPinAuthSession.serverUrl);
    }

    public final String getCode() {
        return this.code;
    }

    public final int getExpiresIn() {
        return this.expiresIn;
    }

    public final String getId() {
        return this.id;
    }

    public final int getInterval() {
        return this.interval;
    }

    public final String getSecret() {
        return this.secret;
    }

    public final HomeServerKind getServerKind() {
        return this.serverKind;
    }

    public final String getServerUrl() {
        return this.serverUrl;
    }

    public final String getVerificationUrl() {
        return this.verificationUrl;
    }

    public int hashCode() {
        return this.serverUrl.hashCode() + ((this.serverKind.hashCode() + ((((androidx.compose.foundation.c.c(androidx.compose.foundation.c.c(androidx.compose.foundation.c.c(this.id.hashCode() * 31, 31, this.secret), 31, this.code), 31, this.verificationUrl) + this.expiresIn) * 31) + this.interval) * 31)) * 31);
    }

    public String toString() {
        String str = this.id;
        String str2 = this.secret;
        String str3 = this.code;
        String str4 = this.verificationUrl;
        int i10 = this.expiresIn;
        int i11 = this.interval;
        HomeServerKind homeServerKind = this.serverKind;
        String str5 = this.serverUrl;
        StringBuilder sbR = a2.r("PlexPinAuthSession(id=", str, ", secret=", str2, ", code=");
        y.a.i(sbR, str3, ", verificationUrl=", str4, ", expiresIn=");
        a2.y(sbR, i10, ", interval=", i11, ", serverKind=");
        sbR.append(homeServerKind);
        sbR.append(", serverUrl=");
        sbR.append(str5);
        sbR.append(")");
        return sbR.toString();
    }

    public PlexPinAuthSession(String str, String str2, String str3, String str4, int i10, int i11, HomeServerKind homeServerKind, String str5) {
        this.id = str;
        this.secret = str2;
        this.code = str3;
        this.verificationUrl = str4;
        this.expiresIn = i10;
        this.interval = i11;
        this.serverKind = homeServerKind;
        this.serverUrl = str5;
    }

    public /* synthetic */ PlexPinAuthSession(String str, String str2, String str3, String str4, int i10, int i11, HomeServerKind homeServerKind, String str5, int i12, kotlin.jvm.internal.h hVar) {
        this((i12 & 1) != 0 ? "" : str, (i12 & 2) != 0 ? "" : str2, (i12 & 4) != 0 ? "" : str3, (i12 & 8) != 0 ? "" : str4, (i12 & 16) != 0 ? MediaError.DetailedErrorCode.TEXT_UNKNOWN : i10, (i12 & 32) != 0 ? 5 : i11, (i12 & 64) != 0 ? HomeServerKind.PLEX : homeServerKind, (i12 & 128) != 0 ? "" : str5);
    }
}
