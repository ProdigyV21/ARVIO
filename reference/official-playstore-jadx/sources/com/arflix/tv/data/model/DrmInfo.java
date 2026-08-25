package com.arflix.tv.data.model;

import a0.c;
import androidx.fragment.app.a2;
import kotlin.Metadata;
import kotlin.jvm.internal.h;
import kotlin.jvm.internal.p;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\r\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B'\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u0006\u0010\u0007J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\r\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u000e\u001a\u0004\u0018\u00010\u0003HÆ\u0003J+\u0010\u000f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0014\u0010\u0010\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0013\u001a\u00020\u0014HÖ\u0081\u0004J\n\u0010\u0015\u001a\u00020\u0003HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\tR\u0013\u0010\u0005\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\t¨\u0006\u0016"}, d2 = {"Lcom/arflix/tv/data/model/DrmInfo;", "", "scheme", "", "licenseUrl", "licenseData", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getScheme", "()Ljava/lang/String;", "getLicenseUrl", "getLicenseData", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "", "toString", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final /* data */ class DrmInfo {
    public static final int $stable = 0;
    private final String licenseData;
    private final String licenseUrl;
    private final String scheme;

    public DrmInfo(String str, String str2, String str3) {
        this.scheme = str;
        this.licenseUrl = str2;
        this.licenseData = str3;
    }

    public static /* synthetic */ DrmInfo copy$default(DrmInfo drmInfo, String str, String str2, String str3, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            str = drmInfo.scheme;
        }
        if ((i10 & 2) != 0) {
            str2 = drmInfo.licenseUrl;
        }
        if ((i10 & 4) != 0) {
            str3 = drmInfo.licenseData;
        }
        return drmInfo.copy(str, str2, str3);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getScheme() {
        return this.scheme;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getLicenseUrl() {
        return this.licenseUrl;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final String getLicenseData() {
        return this.licenseData;
    }

    public final DrmInfo copy(String scheme, String licenseUrl, String licenseData) {
        return new DrmInfo(scheme, licenseUrl, licenseData);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof DrmInfo)) {
            return false;
        }
        DrmInfo drmInfo = (DrmInfo) other;
        return p.a(this.scheme, drmInfo.scheme) && p.a(this.licenseUrl, drmInfo.licenseUrl) && p.a(this.licenseData, drmInfo.licenseData);
    }

    public final String getLicenseData() {
        return this.licenseData;
    }

    public final String getLicenseUrl() {
        return this.licenseUrl;
    }

    public final String getScheme() {
        return this.scheme;
    }

    public int hashCode() {
        int iHashCode = this.scheme.hashCode() * 31;
        String str = this.licenseUrl;
        int iHashCode2 = (iHashCode + (str == null ? 0 : str.hashCode())) * 31;
        String str2 = this.licenseData;
        return iHashCode2 + (str2 != null ? str2.hashCode() : 0);
    }

    public String toString() {
        String str = this.scheme;
        String str2 = this.licenseUrl;
        return c.p(a2.r("DrmInfo(scheme=", str, ", licenseUrl=", str2, ", licenseData="), this.licenseData, ")");
    }

    public /* synthetic */ DrmInfo(String str, String str2, String str3, int i10, h hVar) {
        this(str, (i10 & 2) != 0 ? null : str2, (i10 & 4) != 0 ? null : str3);
    }
}
