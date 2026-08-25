package com.arflix.tv.data.api;

import androidx.fragment.app.a2;
import com.google.gson.annotations.SerializedName;
import kotlin.Metadata;
import kotlin.jvm.internal.p;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0010\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0087\b\u0018\u00002\u00020\u0001B1\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0005\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0003¢\u0006\u0004\b\b\u0010\tJ\t\u0010\u0010\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0011\u001a\u00020\u0005HÆ\u0003J\u000b\u0010\u0012\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\t\u0010\u0013\u001a\u00020\u0003HÆ\u0003J3\u0010\u0014\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00052\b\b\u0002\u0010\u0007\u001a\u00020\u0003HÆ\u0001J\u0014\u0010\u0015\u001a\u00020\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0018\u001a\u00020\u0003HÖ\u0081\u0004J\n\u0010\u0019\u001a\u00020\u0005HÖ\u0081\u0004R\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0016\u0010\u0004\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0018\u0010\u0006\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\rR\u0016\u0010\u0007\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u000b¨\u0006\u001a"}, d2 = {"Lcom/arflix/tv/data/api/TmdbWatchProvider;", "", "providerId", "", "providerName", "", "logoPath", "displayPriority", "<init>", "(ILjava/lang/String;Ljava/lang/String;I)V", "getProviderId", "()I", "getProviderName", "()Ljava/lang/String;", "getLogoPath", "getDisplayPriority", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "hashCode", "toString", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final /* data */ class TmdbWatchProvider {
    public static final int $stable = 0;

    @SerializedName("display_priority")
    private final int displayPriority;

    @SerializedName("logo_path")
    private final String logoPath;

    @SerializedName("provider_id")
    private final int providerId;

    @SerializedName("provider_name")
    private final String providerName;

    public TmdbWatchProvider() {
        this(0, null, null, 0, 15, null);
    }

    public static /* synthetic */ TmdbWatchProvider copy$default(TmdbWatchProvider tmdbWatchProvider, int i10, String str, String str2, int i11, int i12, Object obj) {
        if ((i12 & 1) != 0) {
            i10 = tmdbWatchProvider.providerId;
        }
        if ((i12 & 2) != 0) {
            str = tmdbWatchProvider.providerName;
        }
        if ((i12 & 4) != 0) {
            str2 = tmdbWatchProvider.logoPath;
        }
        if ((i12 & 8) != 0) {
            i11 = tmdbWatchProvider.displayPriority;
        }
        return tmdbWatchProvider.copy(i10, str, str2, i11);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final int getProviderId() {
        return this.providerId;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getProviderName() {
        return this.providerName;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final String getLogoPath() {
        return this.logoPath;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final int getDisplayPriority() {
        return this.displayPriority;
    }

    public final TmdbWatchProvider copy(int providerId, String providerName, String logoPath, int displayPriority) {
        return new TmdbWatchProvider(providerId, providerName, logoPath, displayPriority);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof TmdbWatchProvider)) {
            return false;
        }
        TmdbWatchProvider tmdbWatchProvider = (TmdbWatchProvider) other;
        return this.providerId == tmdbWatchProvider.providerId && p.a(this.providerName, tmdbWatchProvider.providerName) && p.a(this.logoPath, tmdbWatchProvider.logoPath) && this.displayPriority == tmdbWatchProvider.displayPriority;
    }

    public final int getDisplayPriority() {
        return this.displayPriority;
    }

    public final String getLogoPath() {
        return this.logoPath;
    }

    public final int getProviderId() {
        return this.providerId;
    }

    public final String getProviderName() {
        return this.providerName;
    }

    public int hashCode() {
        int iC = androidx.compose.foundation.c.c(this.providerId * 31, 31, this.providerName);
        String str = this.logoPath;
        return ((iC + (str == null ? 0 : str.hashCode())) * 31) + this.displayPriority;
    }

    public String toString() {
        int i10 = this.providerId;
        String str = this.providerName;
        String str2 = this.logoPath;
        int i11 = this.displayPriority;
        StringBuilder sbO = a2.o("TmdbWatchProvider(providerId=", i10, ", providerName=", str, ", logoPath=");
        sbO.append(str2);
        sbO.append(", displayPriority=");
        sbO.append(i11);
        sbO.append(")");
        return sbO.toString();
    }

    public TmdbWatchProvider(int i10, String str, String str2, int i11) {
        this.providerId = i10;
        this.providerName = str;
        this.logoPath = str2;
        this.displayPriority = i11;
    }

    public /* synthetic */ TmdbWatchProvider(int i10, String str, String str2, int i11, int i12, kotlin.jvm.internal.h hVar) {
        this((i12 & 1) != 0 ? 0 : i10, (i12 & 2) != 0 ? "" : str, (i12 & 4) != 0 ? null : str2, (i12 & 8) != 0 ? 0 : i11);
    }
}
