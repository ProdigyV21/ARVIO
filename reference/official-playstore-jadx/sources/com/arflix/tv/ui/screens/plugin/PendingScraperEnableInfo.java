package com.arflix.tv.ui.screens.plugin;

import androidx.fragment.app.a2;
import kotlin.Metadata;
import kotlin.jvm.internal.p;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\t\u0010\n\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\u001d\u0010\f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0003HÆ\u0001J\u0014\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0010\u001a\u00020\u0011HÖ\u0081\u0004J\n\u0010\u0012\u001a\u00020\u0003HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\b¨\u0006\u0013"}, d2 = {"Lcom/arflix/tv/ui/screens/plugin/PendingScraperEnableInfo;", "", "scraperId", "", "scraperName", "<init>", "(Ljava/lang/String;Ljava/lang/String;)V", "getScraperId", "()Ljava/lang/String;", "getScraperName", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final /* data */ class PendingScraperEnableInfo {
    public static final int $stable = 0;
    private final String scraperId;
    private final String scraperName;

    public PendingScraperEnableInfo(String str, String str2) {
        this.scraperId = str;
        this.scraperName = str2;
    }

    public static /* synthetic */ PendingScraperEnableInfo copy$default(PendingScraperEnableInfo pendingScraperEnableInfo, String str, String str2, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            str = pendingScraperEnableInfo.scraperId;
        }
        if ((i10 & 2) != 0) {
            str2 = pendingScraperEnableInfo.scraperName;
        }
        return pendingScraperEnableInfo.copy(str, str2);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getScraperId() {
        return this.scraperId;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getScraperName() {
        return this.scraperName;
    }

    public final PendingScraperEnableInfo copy(String scraperId, String scraperName) {
        return new PendingScraperEnableInfo(scraperId, scraperName);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof PendingScraperEnableInfo)) {
            return false;
        }
        PendingScraperEnableInfo pendingScraperEnableInfo = (PendingScraperEnableInfo) other;
        return p.a(this.scraperId, pendingScraperEnableInfo.scraperId) && p.a(this.scraperName, pendingScraperEnableInfo.scraperName);
    }

    public final String getScraperId() {
        return this.scraperId;
    }

    public final String getScraperName() {
        return this.scraperName;
    }

    public int hashCode() {
        return this.scraperName.hashCode() + (this.scraperId.hashCode() * 31);
    }

    public String toString() {
        return a2.m("PendingScraperEnableInfo(scraperId=", this.scraperId, ", scraperName=", this.scraperName, ")");
    }
}
