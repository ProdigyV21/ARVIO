package com.arflix.tv.data.model;

import androidx.compose.foundation.c;
import androidx.fragment.app.a2;
import androidx.media3.extractor.text.ttml.TtmlNode;
import java.io.Serializable;
import kotlin.Metadata;
import kotlin.jvm.internal.h;
import kotlin.jvm.internal.p;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\t\n\u0002\b\u0012\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B9\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007\u0012\b\b\u0002\u0010\b\u001a\u00020\t¢\u0006\u0004\b\n\u0010\u000bJ\t\u0010\u0014\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0015\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0016\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0017\u001a\u00020\u0007HÆ\u0003J\t\u0010\u0018\u001a\u00020\tHÆ\u0003J;\u0010\u0019\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\tHÆ\u0001J\u0014\u0010\u001a\u001a\u00020\u00072\b\u0010\u001b\u001a\u0004\u0018\u00010\u001cHÖ\u0083\u0004J\n\u0010\u001d\u001a\u00020\u001eHÖ\u0081\u0004J\n\u0010\u001f\u001a\u00020\u0003HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\rR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\rR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013¨\u0006 "}, d2 = {"Lcom/arflix/tv/data/model/QualityFilterConfig;", "Ljava/io/Serializable;", TtmlNode.ATTR_ID, "", "deviceName", "regexPattern", "enabled", "", "createdAt", "", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ZJ)V", "getId", "()Ljava/lang/String;", "getDeviceName", "getRegexPattern", "getEnabled", "()Z", "getCreatedAt", "()J", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "other", "", "hashCode", "", "toString", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final /* data */ class QualityFilterConfig implements Serializable {
    public static final int $stable = 0;
    private final long createdAt;
    private final String deviceName;
    private final boolean enabled;
    private final String id;
    private final String regexPattern;

    public QualityFilterConfig() {
        this(null, null, null, false, 0L, 31, null);
    }

    public static /* synthetic */ QualityFilterConfig copy$default(QualityFilterConfig qualityFilterConfig, String str, String str2, String str3, boolean z, long j10, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            str = qualityFilterConfig.id;
        }
        if ((i10 & 2) != 0) {
            str2 = qualityFilterConfig.deviceName;
        }
        if ((i10 & 4) != 0) {
            str3 = qualityFilterConfig.regexPattern;
        }
        if ((i10 & 8) != 0) {
            z = qualityFilterConfig.enabled;
        }
        if ((i10 & 16) != 0) {
            j10 = qualityFilterConfig.createdAt;
        }
        long j11 = j10;
        return qualityFilterConfig.copy(str, str2, str3, z, j11);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getId() {
        return this.id;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getDeviceName() {
        return this.deviceName;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final String getRegexPattern() {
        return this.regexPattern;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final boolean getEnabled() {
        return this.enabled;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final long getCreatedAt() {
        return this.createdAt;
    }

    public final QualityFilterConfig copy(String id, String deviceName, String regexPattern, boolean enabled, long createdAt) {
        return new QualityFilterConfig(id, deviceName, regexPattern, enabled, createdAt);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof QualityFilterConfig)) {
            return false;
        }
        QualityFilterConfig qualityFilterConfig = (QualityFilterConfig) other;
        return p.a(this.id, qualityFilterConfig.id) && p.a(this.deviceName, qualityFilterConfig.deviceName) && p.a(this.regexPattern, qualityFilterConfig.regexPattern) && this.enabled == qualityFilterConfig.enabled && this.createdAt == qualityFilterConfig.createdAt;
    }

    public final long getCreatedAt() {
        return this.createdAt;
    }

    public final String getDeviceName() {
        return this.deviceName;
    }

    public final boolean getEnabled() {
        return this.enabled;
    }

    public final String getId() {
        return this.id;
    }

    public final String getRegexPattern() {
        return this.regexPattern;
    }

    public int hashCode() {
        int iC = c.c(c.c(this.id.hashCode() * 31, 31, this.deviceName), 31, this.regexPattern);
        int i10 = this.enabled ? 1231 : 1237;
        long j10 = this.createdAt;
        return ((iC + i10) * 31) + ((int) (j10 ^ (j10 >>> 32)));
    }

    public String toString() {
        String str = this.id;
        String str2 = this.deviceName;
        String str3 = this.regexPattern;
        boolean z = this.enabled;
        long j10 = this.createdAt;
        StringBuilder sbR = a2.r("QualityFilterConfig(id=", str, ", deviceName=", str2, ", regexPattern=");
        sbR.append(str3);
        sbR.append(", enabled=");
        sbR.append(z);
        sbR.append(", createdAt=");
        return a0.c.j(j10, ")", sbR);
    }

    public QualityFilterConfig(String str, String str2, String str3, boolean z, long j10) {
        this.id = str;
        this.deviceName = str2;
        this.regexPattern = str3;
        this.enabled = z;
        this.createdAt = j10;
    }

    public /* synthetic */ QualityFilterConfig(String str, String str2, String str3, boolean z, long j10, int i10, h hVar) {
        this((i10 & 1) != 0 ? "" : str, (i10 & 2) != 0 ? "" : str2, (i10 & 4) != 0 ? "" : str3, (i10 & 8) != 0 ? true : z, (i10 & 16) != 0 ? System.currentTimeMillis() : j10);
    }
}
