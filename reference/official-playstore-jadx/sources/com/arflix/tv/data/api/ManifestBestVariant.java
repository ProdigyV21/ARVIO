package com.arflix.tv.data.api;

import androidx.fragment.app.a2;
import kotlin.Metadata;
import kotlin.jvm.internal.p;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u000f\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0082\b\u0018\u00002\u00020\u0001B'\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0004\b\t\u0010\nJ\t\u0010\u0012\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0013\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0014\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0015\u001a\u00020\bHÆ\u0003J1\u0010\u0016\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\bHÆ\u0001J\u0014\u0010\u0017\u001a\u00020\u00182\b\u0010\u0019\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u001a\u001a\u00020\u0005HÖ\u0081\u0004J\n\u0010\u001b\u001a\u00020\u0003HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u000eR\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011¨\u0006\u001c"}, d2 = {"Lcom/arflix/tv/data/api/ManifestBestVariant;", "", "url", "", "width", "", "height", "bandwidth", "", "<init>", "(Ljava/lang/String;IIJ)V", "getUrl", "()Ljava/lang/String;", "getWidth", "()I", "getHeight", "getBandwidth", "()J", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "hashCode", "toString", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
final /* data */ class ManifestBestVariant {
    private final long bandwidth;
    private final int height;
    private final String url;
    private final int width;

    public ManifestBestVariant(String str, int i10, int i11, long j10) {
        this.url = str;
        this.width = i10;
        this.height = i11;
        this.bandwidth = j10;
    }

    public static /* synthetic */ ManifestBestVariant copy$default(ManifestBestVariant manifestBestVariant, String str, int i10, int i11, long j10, int i12, Object obj) {
        if ((i12 & 1) != 0) {
            str = manifestBestVariant.url;
        }
        if ((i12 & 2) != 0) {
            i10 = manifestBestVariant.width;
        }
        if ((i12 & 4) != 0) {
            i11 = manifestBestVariant.height;
        }
        if ((i12 & 8) != 0) {
            j10 = manifestBestVariant.bandwidth;
        }
        int i13 = i11;
        return manifestBestVariant.copy(str, i10, i13, j10);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getUrl() {
        return this.url;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final int getWidth() {
        return this.width;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final int getHeight() {
        return this.height;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final long getBandwidth() {
        return this.bandwidth;
    }

    public final ManifestBestVariant copy(String url, int width, int height, long bandwidth) {
        return new ManifestBestVariant(url, width, height, bandwidth);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof ManifestBestVariant)) {
            return false;
        }
        ManifestBestVariant manifestBestVariant = (ManifestBestVariant) other;
        return p.a(this.url, manifestBestVariant.url) && this.width == manifestBestVariant.width && this.height == manifestBestVariant.height && this.bandwidth == manifestBestVariant.bandwidth;
    }

    public final long getBandwidth() {
        return this.bandwidth;
    }

    public final int getHeight() {
        return this.height;
    }

    public final String getUrl() {
        return this.url;
    }

    public final int getWidth() {
        return this.width;
    }

    public int hashCode() {
        int iHashCode = ((((this.url.hashCode() * 31) + this.width) * 31) + this.height) * 31;
        long j10 = this.bandwidth;
        return iHashCode + ((int) (j10 ^ (j10 >>> 32)));
    }

    public String toString() {
        String str = this.url;
        int i10 = this.width;
        int i11 = this.height;
        long j10 = this.bandwidth;
        StringBuilder sbQ = a2.q("ManifestBestVariant(url=", str, ", width=", i10, ", height=");
        sbQ.append(i11);
        sbQ.append(", bandwidth=");
        sbQ.append(j10);
        sbQ.append(")");
        return sbQ.toString();
    }
}
