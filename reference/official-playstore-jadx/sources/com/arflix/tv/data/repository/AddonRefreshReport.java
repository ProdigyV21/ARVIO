package com.arflix.tv.data.repository;

import kotlin.Metadata;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0087\b\u0018\u00002\u00020\u0001B\u001b\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\t\u0010\n\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\u001d\u0010\f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0003HÆ\u0001J\u0014\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0010\u001a\u00020\u0003HÖ\u0081\u0004J\n\u0010\u0011\u001a\u00020\u0012HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\b¨\u0006\u0013"}, d2 = {"Lcom/arflix/tv/data/repository/AddonRefreshReport;", "", "refreshed", "", "failed", "<init>", "(II)V", "getRefreshed", "()I", "getFailed", "component1", "component2", "copy", "equals", "", "other", "hashCode", "toString", "", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final /* data */ class AddonRefreshReport {
    public static final int $stable = 0;
    private final int failed;
    private final int refreshed;

    /* JADX WARN: Illegal instructions before constructor call */
    public AddonRefreshReport() {
        int i10 = 0;
        this(i10, i10, 3, null);
    }

    public static /* synthetic */ AddonRefreshReport copy$default(AddonRefreshReport addonRefreshReport, int i10, int i11, int i12, Object obj) {
        if ((i12 & 1) != 0) {
            i10 = addonRefreshReport.refreshed;
        }
        if ((i12 & 2) != 0) {
            i11 = addonRefreshReport.failed;
        }
        return addonRefreshReport.copy(i10, i11);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final int getRefreshed() {
        return this.refreshed;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final int getFailed() {
        return this.failed;
    }

    public final AddonRefreshReport copy(int refreshed, int failed) {
        return new AddonRefreshReport(refreshed, failed);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof AddonRefreshReport)) {
            return false;
        }
        AddonRefreshReport addonRefreshReport = (AddonRefreshReport) other;
        return this.refreshed == addonRefreshReport.refreshed && this.failed == addonRefreshReport.failed;
    }

    public final int getFailed() {
        return this.failed;
    }

    public final int getRefreshed() {
        return this.refreshed;
    }

    public int hashCode() {
        return (this.refreshed * 31) + this.failed;
    }

    public String toString() {
        return androidx.compose.foundation.c.s("AddonRefreshReport(refreshed=", this.refreshed, ", failed=", this.failed, ")");
    }

    public AddonRefreshReport(int i10, int i11) {
        this.refreshed = i10;
        this.failed = i11;
    }

    public /* synthetic */ AddonRefreshReport(int i10, int i11, int i12, kotlin.jvm.internal.h hVar) {
        this((i12 & 1) != 0 ? 0 : i10, (i12 & 2) != 0 ? 0 : i11);
    }
}
