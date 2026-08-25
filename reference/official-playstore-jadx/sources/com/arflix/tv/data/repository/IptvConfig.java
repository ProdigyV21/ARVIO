package com.arflix.tv.data.repository;

import androidx.fragment.app.a2;
import java.util.List;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0015\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001BI\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003\u0012\u000e\b\u0002\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006\u0012\b\b\u0002\u0010\b\u001a\u00020\u0003\u0012\b\b\u0002\u0010\t\u001a\u00020\u0003\u0012\b\b\u0002\u0010\n\u001a\u00020\u0003¢\u0006\u0004\b\u000b\u0010\fJ\t\u0010\u0015\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0016\u001a\u00020\u0003HÆ\u0003J\u000f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006HÆ\u0003J\t\u0010\u0018\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0019\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001a\u001a\u00020\u0003HÆ\u0003JK\u0010\u001b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\u000e\b\u0002\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\b\b\u0002\u0010\b\u001a\u00020\u00032\b\b\u0002\u0010\t\u001a\u00020\u00032\b\b\u0002\u0010\n\u001a\u00020\u0003HÆ\u0001J\u0014\u0010\u001c\u001a\u00020\u001d2\b\u0010\u001e\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u001f\u001a\u00020 HÖ\u0081\u0004J\n\u0010!\u001a\u00020\u0003HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u000eR\u0017\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\b\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u000eR\u0011\u0010\t\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u000eR\u0011\u0010\n\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u000e¨\u0006\""}, d2 = {"Lcom/arflix/tv/data/repository/IptvConfig;", "", "m3uUrl", "", "epgUrl", "playlists", "", "Lcom/arflix/tv/data/repository/IptvPlaylistEntry;", "stalkerPortalUrl", "stalkerMacAddress", "sortOrder", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/util/List;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getM3uUrl", "()Ljava/lang/String;", "getEpgUrl", "getPlaylists", "()Ljava/util/List;", "getStalkerPortalUrl", "getStalkerMacAddress", "getSortOrder", "component1", "component2", "component3", "component4", "component5", "component6", "copy", "equals", "", "other", "hashCode", "", "toString", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final /* data */ class IptvConfig {
    public static final int $stable = 0;
    private final String epgUrl;
    private final String m3uUrl;
    private final List<IptvPlaylistEntry> playlists;
    private final String sortOrder;
    private final String stalkerMacAddress;
    private final String stalkerPortalUrl;

    public IptvConfig() {
        this(null, null, null, null, null, null, 63, null);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ IptvConfig copy$default(IptvConfig iptvConfig, String str, String str2, List list, String str3, String str4, String str5, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            str = iptvConfig.m3uUrl;
        }
        if ((i10 & 2) != 0) {
            str2 = iptvConfig.epgUrl;
        }
        if ((i10 & 4) != 0) {
            list = iptvConfig.playlists;
        }
        if ((i10 & 8) != 0) {
            str3 = iptvConfig.stalkerPortalUrl;
        }
        if ((i10 & 16) != 0) {
            str4 = iptvConfig.stalkerMacAddress;
        }
        if ((i10 & 32) != 0) {
            str5 = iptvConfig.sortOrder;
        }
        String str6 = str4;
        String str7 = str5;
        return iptvConfig.copy(str, str2, list, str3, str6, str7);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getM3uUrl() {
        return this.m3uUrl;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getEpgUrl() {
        return this.epgUrl;
    }

    public final List<IptvPlaylistEntry> component3() {
        return this.playlists;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final String getStalkerPortalUrl() {
        return this.stalkerPortalUrl;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final String getStalkerMacAddress() {
        return this.stalkerMacAddress;
    }

    /* JADX INFO: renamed from: component6, reason: from getter */
    public final String getSortOrder() {
        return this.sortOrder;
    }

    public final IptvConfig copy(String m3uUrl, String epgUrl, List<IptvPlaylistEntry> playlists, String stalkerPortalUrl, String stalkerMacAddress, String sortOrder) {
        return new IptvConfig(m3uUrl, epgUrl, playlists, stalkerPortalUrl, stalkerMacAddress, sortOrder);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof IptvConfig)) {
            return false;
        }
        IptvConfig iptvConfig = (IptvConfig) other;
        return kotlin.jvm.internal.p.a(this.m3uUrl, iptvConfig.m3uUrl) && kotlin.jvm.internal.p.a(this.epgUrl, iptvConfig.epgUrl) && kotlin.jvm.internal.p.a(this.playlists, iptvConfig.playlists) && kotlin.jvm.internal.p.a(this.stalkerPortalUrl, iptvConfig.stalkerPortalUrl) && kotlin.jvm.internal.p.a(this.stalkerMacAddress, iptvConfig.stalkerMacAddress) && kotlin.jvm.internal.p.a(this.sortOrder, iptvConfig.sortOrder);
    }

    public final String getEpgUrl() {
        return this.epgUrl;
    }

    public final String getM3uUrl() {
        return this.m3uUrl;
    }

    public final List<IptvPlaylistEntry> getPlaylists() {
        return this.playlists;
    }

    public final String getSortOrder() {
        return this.sortOrder;
    }

    public final String getStalkerMacAddress() {
        return this.stalkerMacAddress;
    }

    public final String getStalkerPortalUrl() {
        return this.stalkerPortalUrl;
    }

    public int hashCode() {
        return this.sortOrder.hashCode() + androidx.compose.foundation.c.c(androidx.compose.foundation.c.c(androidx.compose.material3.d.e(this.playlists, androidx.compose.foundation.c.c(this.m3uUrl.hashCode() * 31, 31, this.epgUrl), 31), 31, this.stalkerPortalUrl), 31, this.stalkerMacAddress);
    }

    public String toString() {
        String str = this.m3uUrl;
        String str2 = this.epgUrl;
        List<IptvPlaylistEntry> list = this.playlists;
        String str3 = this.stalkerPortalUrl;
        String str4 = this.stalkerMacAddress;
        String str5 = this.sortOrder;
        StringBuilder sbR = a2.r("IptvConfig(m3uUrl=", str, ", epgUrl=", str2, ", playlists=");
        sbR.append(list);
        sbR.append(", stalkerPortalUrl=");
        sbR.append(str3);
        sbR.append(", stalkerMacAddress=");
        return a2.n(sbR, str4, ", sortOrder=", str5, ")");
    }

    public IptvConfig(String str, String str2, List<IptvPlaylistEntry> list, String str3, String str4, String str5) {
        this.m3uUrl = str;
        this.epgUrl = str2;
        this.playlists = list;
        this.stalkerPortalUrl = str3;
        this.stalkerMacAddress = str4;
        this.sortOrder = str5;
    }

    public /* synthetic */ IptvConfig(String str, String str2, List list, String str3, String str4, String str5, int i10, kotlin.jvm.internal.h hVar) {
        this((i10 & 1) != 0 ? "" : str, (i10 & 2) != 0 ? "" : str2, (i10 & 4) != 0 ? kotlin.collections.z.f19728i : list, (i10 & 8) != 0 ? "" : str3, (i10 & 16) != 0 ? "" : str4, (i10 & 32) != 0 ? "provider" : str5);
    }
}
