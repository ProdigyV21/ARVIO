package com.arflix.tv.data.api;

import androidx.fragment.app.a2;
import kotlin.Metadata;
import kotlin.jvm.internal.p;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\t\n\u0002\b\u0013\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0082\b\u0018\u00002\u00020\u0001B7\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\u0003\u0012\u0006\u0010\b\u001a\u00020\u0005\u0012\u0006\u0010\t\u001a\u00020\n¢\u0006\u0004\b\u000b\u0010\fJ\t\u0010\u0016\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0017\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0018\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0019\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001a\u001a\u00020\u0005HÆ\u0003J\t\u0010\u001b\u001a\u00020\nHÆ\u0003JE\u0010\u001c\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\u00032\b\b\u0002\u0010\b\u001a\u00020\u00052\b\b\u0002\u0010\t\u001a\u00020\nHÆ\u0001J\u0014\u0010\u001d\u001a\u00020\u001e2\b\u0010\u001f\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010 \u001a\u00020\u0005HÖ\u0081\u0004J\n\u0010!\u001a\u00020\u0003HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u000eR\u0011\u0010\u0007\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u000eR\u0011\u0010\b\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0010R\u0011\u0010\t\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015¨\u0006\""}, d2 = {"Lcom/arflix/tv/data/api/ManifestCandidate;", "", "client", "", "priority", "", "manifestUrl", "selectedVariantUrl", "height", "bandwidth", "", "<init>", "(Ljava/lang/String;ILjava/lang/String;Ljava/lang/String;IJ)V", "getClient", "()Ljava/lang/String;", "getPriority", "()I", "getManifestUrl", "getSelectedVariantUrl", "getHeight", "getBandwidth", "()J", "component1", "component2", "component3", "component4", "component5", "component6", "copy", "equals", "", "other", "hashCode", "toString", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
final /* data */ class ManifestCandidate {
    private final long bandwidth;
    private final String client;
    private final int height;
    private final String manifestUrl;
    private final int priority;
    private final String selectedVariantUrl;

    public ManifestCandidate(String str, int i10, String str2, String str3, int i11, long j10) {
        this.client = str;
        this.priority = i10;
        this.manifestUrl = str2;
        this.selectedVariantUrl = str3;
        this.height = i11;
        this.bandwidth = j10;
    }

    public static /* synthetic */ ManifestCandidate copy$default(ManifestCandidate manifestCandidate, String str, int i10, String str2, String str3, int i11, long j10, int i12, Object obj) {
        if ((i12 & 1) != 0) {
            str = manifestCandidate.client;
        }
        if ((i12 & 2) != 0) {
            i10 = manifestCandidate.priority;
        }
        if ((i12 & 4) != 0) {
            str2 = manifestCandidate.manifestUrl;
        }
        if ((i12 & 8) != 0) {
            str3 = manifestCandidate.selectedVariantUrl;
        }
        if ((i12 & 16) != 0) {
            i11 = manifestCandidate.height;
        }
        if ((i12 & 32) != 0) {
            j10 = manifestCandidate.bandwidth;
        }
        long j11 = j10;
        int i13 = i11;
        String str4 = str2;
        return manifestCandidate.copy(str, i10, str4, str3, i13, j11);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getClient() {
        return this.client;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final int getPriority() {
        return this.priority;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final String getManifestUrl() {
        return this.manifestUrl;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final String getSelectedVariantUrl() {
        return this.selectedVariantUrl;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final int getHeight() {
        return this.height;
    }

    /* JADX INFO: renamed from: component6, reason: from getter */
    public final long getBandwidth() {
        return this.bandwidth;
    }

    public final ManifestCandidate copy(String client, int priority, String manifestUrl, String selectedVariantUrl, int height, long bandwidth) {
        return new ManifestCandidate(client, priority, manifestUrl, selectedVariantUrl, height, bandwidth);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof ManifestCandidate)) {
            return false;
        }
        ManifestCandidate manifestCandidate = (ManifestCandidate) other;
        return p.a(this.client, manifestCandidate.client) && this.priority == manifestCandidate.priority && p.a(this.manifestUrl, manifestCandidate.manifestUrl) && p.a(this.selectedVariantUrl, manifestCandidate.selectedVariantUrl) && this.height == manifestCandidate.height && this.bandwidth == manifestCandidate.bandwidth;
    }

    public final long getBandwidth() {
        return this.bandwidth;
    }

    public final String getClient() {
        return this.client;
    }

    public final int getHeight() {
        return this.height;
    }

    public final String getManifestUrl() {
        return this.manifestUrl;
    }

    public final int getPriority() {
        return this.priority;
    }

    public final String getSelectedVariantUrl() {
        return this.selectedVariantUrl;
    }

    public int hashCode() {
        int iC = (androidx.compose.foundation.c.c(androidx.compose.foundation.c.c(((this.client.hashCode() * 31) + this.priority) * 31, 31, this.manifestUrl), 31, this.selectedVariantUrl) + this.height) * 31;
        long j10 = this.bandwidth;
        return iC + ((int) (j10 ^ (j10 >>> 32)));
    }

    public String toString() {
        String str = this.client;
        int i10 = this.priority;
        String str2 = this.manifestUrl;
        String str3 = this.selectedVariantUrl;
        int i11 = this.height;
        long j10 = this.bandwidth;
        StringBuilder sbQ = a2.q("ManifestCandidate(client=", str, ", priority=", i10, ", manifestUrl=");
        y.a.i(sbQ, str2, ", selectedVariantUrl=", str3, ", height=");
        sbQ.append(i11);
        sbQ.append(", bandwidth=");
        sbQ.append(j10);
        sbQ.append(")");
        return sbQ.toString();
    }
}
