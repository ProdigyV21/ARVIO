package com.arflix.tv.updater;

import androidx.compose.foundation.c;
import androidx.fragment.app.a2;
import io.ktor.http.LinkHeader;
import kotlin.Metadata;
import kotlin.jvm.internal.p;
import y.a;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\t\n\u0002\b\u0016\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001BC\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0007\u001a\u00020\u0003\u0012\u0006\u0010\b\u001a\u00020\u0003\u0012\b\u0010\t\u001a\u0004\u0018\u00010\n¢\u0006\u0004\b\u000b\u0010\fJ\t\u0010\u0017\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0018\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0019\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\u001a\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\t\u0010\u001b\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001c\u001a\u00020\u0003HÆ\u0003J\u0010\u0010\u001d\u001a\u0004\u0018\u00010\nHÆ\u0003¢\u0006\u0002\u0010\u0015JX\u0010\u001e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u0007\u001a\u00020\u00032\b\b\u0002\u0010\b\u001a\u00020\u00032\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\nHÆ\u0001¢\u0006\u0002\u0010\u001fJ\u0014\u0010 \u001a\u00020!2\b\u0010\"\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010#\u001a\u00020$HÖ\u0081\u0004J\n\u0010%\u001a\u00020\u0003HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u000eR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000eR\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u000eR\u0011\u0010\u0007\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u000eR\u0011\u0010\b\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u000eR\u0015\u0010\t\u001a\u0004\u0018\u00010\n¢\u0006\n\n\u0002\u0010\u0016\u001a\u0004\b\u0014\u0010\u0015¨\u0006&"}, d2 = {"Lcom/arflix/tv/updater/AppUpdate;", "", "tag", "", LinkHeader.Parameters.Title, "notes", "releaseUrl", "assetName", "assetUrl", "assetSizeBytes", "", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Long;)V", "getTag", "()Ljava/lang/String;", "getTitle", "getNotes", "getReleaseUrl", "getAssetName", "getAssetUrl", "getAssetSizeBytes", "()Ljava/lang/Long;", "Ljava/lang/Long;", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "copy", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Long;)Lcom/arflix/tv/updater/AppUpdate;", "equals", "", "other", "hashCode", "", "toString", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final /* data */ class AppUpdate {
    public static final int $stable = 0;
    private final String assetName;
    private final Long assetSizeBytes;
    private final String assetUrl;
    private final String notes;
    private final String releaseUrl;
    private final String tag;
    private final String title;

    public AppUpdate(String str, String str2, String str3, String str4, String str5, String str6, Long l10) {
        this.tag = str;
        this.title = str2;
        this.notes = str3;
        this.releaseUrl = str4;
        this.assetName = str5;
        this.assetUrl = str6;
        this.assetSizeBytes = l10;
    }

    public static /* synthetic */ AppUpdate copy$default(AppUpdate appUpdate, String str, String str2, String str3, String str4, String str5, String str6, Long l10, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            str = appUpdate.tag;
        }
        if ((i10 & 2) != 0) {
            str2 = appUpdate.title;
        }
        if ((i10 & 4) != 0) {
            str3 = appUpdate.notes;
        }
        if ((i10 & 8) != 0) {
            str4 = appUpdate.releaseUrl;
        }
        if ((i10 & 16) != 0) {
            str5 = appUpdate.assetName;
        }
        if ((i10 & 32) != 0) {
            str6 = appUpdate.assetUrl;
        }
        if ((i10 & 64) != 0) {
            l10 = appUpdate.assetSizeBytes;
        }
        String str7 = str6;
        Long l11 = l10;
        String str8 = str5;
        String str9 = str3;
        return appUpdate.copy(str, str2, str9, str4, str8, str7, l11);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getTag() {
        return this.tag;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getTitle() {
        return this.title;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final String getNotes() {
        return this.notes;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final String getReleaseUrl() {
        return this.releaseUrl;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final String getAssetName() {
        return this.assetName;
    }

    /* JADX INFO: renamed from: component6, reason: from getter */
    public final String getAssetUrl() {
        return this.assetUrl;
    }

    /* JADX INFO: renamed from: component7, reason: from getter */
    public final Long getAssetSizeBytes() {
        return this.assetSizeBytes;
    }

    public final AppUpdate copy(String tag, String title, String notes, String releaseUrl, String assetName, String assetUrl, Long assetSizeBytes) {
        return new AppUpdate(tag, title, notes, releaseUrl, assetName, assetUrl, assetSizeBytes);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof AppUpdate)) {
            return false;
        }
        AppUpdate appUpdate = (AppUpdate) other;
        return p.a(this.tag, appUpdate.tag) && p.a(this.title, appUpdate.title) && p.a(this.notes, appUpdate.notes) && p.a(this.releaseUrl, appUpdate.releaseUrl) && p.a(this.assetName, appUpdate.assetName) && p.a(this.assetUrl, appUpdate.assetUrl) && p.a(this.assetSizeBytes, appUpdate.assetSizeBytes);
    }

    public final String getAssetName() {
        return this.assetName;
    }

    public final Long getAssetSizeBytes() {
        return this.assetSizeBytes;
    }

    public final String getAssetUrl() {
        return this.assetUrl;
    }

    public final String getNotes() {
        return this.notes;
    }

    public final String getReleaseUrl() {
        return this.releaseUrl;
    }

    public final String getTag() {
        return this.tag;
    }

    public final String getTitle() {
        return this.title;
    }

    public int hashCode() {
        int iC = c.c(c.c(this.tag.hashCode() * 31, 31, this.title), 31, this.notes);
        String str = this.releaseUrl;
        int iC2 = c.c(c.c((iC + (str == null ? 0 : str.hashCode())) * 31, 31, this.assetName), 31, this.assetUrl);
        Long l10 = this.assetSizeBytes;
        return iC2 + (l10 != null ? l10.hashCode() : 0);
    }

    public String toString() {
        String str = this.tag;
        String str2 = this.title;
        String str3 = this.notes;
        String str4 = this.releaseUrl;
        String str5 = this.assetName;
        String str6 = this.assetUrl;
        Long l10 = this.assetSizeBytes;
        StringBuilder sbR = a2.r("AppUpdate(tag=", str, ", title=", str2, ", notes=");
        a.i(sbR, str3, ", releaseUrl=", str4, ", assetName=");
        a.i(sbR, str5, ", assetUrl=", str6, ", assetSizeBytes=");
        sbR.append(l10);
        sbR.append(")");
        return sbR.toString();
    }
}
