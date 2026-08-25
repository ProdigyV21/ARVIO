package com.arflix.tv.data.repository;

import androidx.fragment.app.a2;
import androidx.media3.exoplayer.analytics.AnalyticsListener;
import java.util.List;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u001c\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0087\b\u0018\u00002\u00020\u0001B\u0089\u0001\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003\u0012\u000e\b\u0002\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00030\u0006\u0012\u000e\b\u0002\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00030\u0006\u0012\u000e\b\u0002\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00030\u0006\u0012\u000e\b\u0002\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00030\u0006\u0012\b\b\u0002\u0010\n\u001a\u00020\u000b\u0012\b\b\u0002\u0010\f\u001a\u00020\u0003\u0012\u000e\b\u0002\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000e0\u0006\u0012\b\b\u0002\u0010\u000f\u001a\u00020\u0010¢\u0006\u0004\b\u0011\u0010\u0012J\t\u0010!\u001a\u00020\u0003HÆ\u0003J\t\u0010\"\u001a\u00020\u0003HÆ\u0003J\u000f\u0010#\u001a\b\u0012\u0004\u0012\u00020\u00030\u0006HÆ\u0003J\u000f\u0010$\u001a\b\u0012\u0004\u0012\u00020\u00030\u0006HÆ\u0003J\u000f\u0010%\u001a\b\u0012\u0004\u0012\u00020\u00030\u0006HÆ\u0003J\u000f\u0010&\u001a\b\u0012\u0004\u0012\u00020\u00030\u0006HÆ\u0003J\t\u0010'\u001a\u00020\u000bHÆ\u0003J\t\u0010(\u001a\u00020\u0003HÆ\u0003J\u000f\u0010)\u001a\b\u0012\u0004\u0012\u00020\u000e0\u0006HÆ\u0003J\t\u0010*\u001a\u00020\u0010HÆ\u0003J\u008b\u0001\u0010+\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\u000e\b\u0002\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00030\u00062\u000e\b\u0002\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00030\u00062\u000e\b\u0002\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00030\u00062\u000e\b\u0002\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00030\u00062\b\b\u0002\u0010\n\u001a\u00020\u000b2\b\b\u0002\u0010\f\u001a\u00020\u00032\u000e\b\u0002\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000e0\u00062\b\b\u0002\u0010\u000f\u001a\u00020\u0010HÆ\u0001J\u0014\u0010,\u001a\u00020-2\b\u0010.\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010/\u001a\u00020\u000bHÖ\u0081\u0004J\n\u00100\u001a\u00020\u0003HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0014R\u0017\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00030\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u0017\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00030\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0017R\u0017\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00030\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0017R\u0017\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00030\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0017R\u0011\u0010\n\u001a\u00020\u000b¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u001cR\u0011\u0010\f\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u0014R\u0017\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000e0\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u0017R\u0011\u0010\u000f\u001a\u00020\u0010¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010 ¨\u00061"}, d2 = {"Lcom/arflix/tv/data/repository/IptvCloudProfileState;", "", "m3uUrl", "", "epgUrl", "favoriteGroups", "", "favoriteChannels", "hiddenGroups", "groupOrder", "groupOrderSchema", "", "sortOrder", "playlists", "Lcom/arflix/tv/data/repository/IptvPlaylistEntry;", "tvSession", "Lcom/arflix/tv/data/repository/IptvTvSessionState;", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/util/List;Ljava/util/List;Ljava/util/List;Ljava/util/List;ILjava/lang/String;Ljava/util/List;Lcom/arflix/tv/data/repository/IptvTvSessionState;)V", "getM3uUrl", "()Ljava/lang/String;", "getEpgUrl", "getFavoriteGroups", "()Ljava/util/List;", "getFavoriteChannels", "getHiddenGroups", "getGroupOrder", "getGroupOrderSchema", "()I", "getSortOrder", "getPlaylists", "getTvSession", "()Lcom/arflix/tv/data/repository/IptvTvSessionState;", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "component10", "copy", "equals", "", "other", "hashCode", "toString", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final /* data */ class IptvCloudProfileState {
    public static final int $stable = 0;
    private final String epgUrl;
    private final List<String> favoriteChannels;
    private final List<String> favoriteGroups;
    private final List<String> groupOrder;
    private final int groupOrderSchema;
    private final List<String> hiddenGroups;
    private final String m3uUrl;
    private final List<IptvPlaylistEntry> playlists;
    private final String sortOrder;
    private final IptvTvSessionState tvSession;

    public IptvCloudProfileState() {
        this(null, null, null, null, null, null, 0, null, null, null, AnalyticsListener.EVENT_DRM_KEYS_LOADED, null);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ IptvCloudProfileState copy$default(IptvCloudProfileState iptvCloudProfileState, String str, String str2, List list, List list2, List list3, List list4, int i10, String str3, List list5, IptvTvSessionState iptvTvSessionState, int i11, Object obj) {
        if ((i11 & 1) != 0) {
            str = iptvCloudProfileState.m3uUrl;
        }
        if ((i11 & 2) != 0) {
            str2 = iptvCloudProfileState.epgUrl;
        }
        if ((i11 & 4) != 0) {
            list = iptvCloudProfileState.favoriteGroups;
        }
        if ((i11 & 8) != 0) {
            list2 = iptvCloudProfileState.favoriteChannels;
        }
        if ((i11 & 16) != 0) {
            list3 = iptvCloudProfileState.hiddenGroups;
        }
        if ((i11 & 32) != 0) {
            list4 = iptvCloudProfileState.groupOrder;
        }
        if ((i11 & 64) != 0) {
            i10 = iptvCloudProfileState.groupOrderSchema;
        }
        if ((i11 & 128) != 0) {
            str3 = iptvCloudProfileState.sortOrder;
        }
        if ((i11 & 256) != 0) {
            list5 = iptvCloudProfileState.playlists;
        }
        if ((i11 & 512) != 0) {
            iptvTvSessionState = iptvCloudProfileState.tvSession;
        }
        List list6 = list5;
        IptvTvSessionState iptvTvSessionState2 = iptvTvSessionState;
        int i12 = i10;
        String str4 = str3;
        List list7 = list3;
        List list8 = list4;
        return iptvCloudProfileState.copy(str, str2, list, list2, list7, list8, i12, str4, list6, iptvTvSessionState2);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getM3uUrl() {
        return this.m3uUrl;
    }

    /* JADX INFO: renamed from: component10, reason: from getter */
    public final IptvTvSessionState getTvSession() {
        return this.tvSession;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getEpgUrl() {
        return this.epgUrl;
    }

    public final List<String> component3() {
        return this.favoriteGroups;
    }

    public final List<String> component4() {
        return this.favoriteChannels;
    }

    public final List<String> component5() {
        return this.hiddenGroups;
    }

    public final List<String> component6() {
        return this.groupOrder;
    }

    /* JADX INFO: renamed from: component7, reason: from getter */
    public final int getGroupOrderSchema() {
        return this.groupOrderSchema;
    }

    /* JADX INFO: renamed from: component8, reason: from getter */
    public final String getSortOrder() {
        return this.sortOrder;
    }

    public final List<IptvPlaylistEntry> component9() {
        return this.playlists;
    }

    public final IptvCloudProfileState copy(String m3uUrl, String epgUrl, List<String> favoriteGroups, List<String> favoriteChannels, List<String> hiddenGroups, List<String> groupOrder, int groupOrderSchema, String sortOrder, List<IptvPlaylistEntry> playlists, IptvTvSessionState tvSession) {
        return new IptvCloudProfileState(m3uUrl, epgUrl, favoriteGroups, favoriteChannels, hiddenGroups, groupOrder, groupOrderSchema, sortOrder, playlists, tvSession);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof IptvCloudProfileState)) {
            return false;
        }
        IptvCloudProfileState iptvCloudProfileState = (IptvCloudProfileState) other;
        return kotlin.jvm.internal.p.a(this.m3uUrl, iptvCloudProfileState.m3uUrl) && kotlin.jvm.internal.p.a(this.epgUrl, iptvCloudProfileState.epgUrl) && kotlin.jvm.internal.p.a(this.favoriteGroups, iptvCloudProfileState.favoriteGroups) && kotlin.jvm.internal.p.a(this.favoriteChannels, iptvCloudProfileState.favoriteChannels) && kotlin.jvm.internal.p.a(this.hiddenGroups, iptvCloudProfileState.hiddenGroups) && kotlin.jvm.internal.p.a(this.groupOrder, iptvCloudProfileState.groupOrder) && this.groupOrderSchema == iptvCloudProfileState.groupOrderSchema && kotlin.jvm.internal.p.a(this.sortOrder, iptvCloudProfileState.sortOrder) && kotlin.jvm.internal.p.a(this.playlists, iptvCloudProfileState.playlists) && kotlin.jvm.internal.p.a(this.tvSession, iptvCloudProfileState.tvSession);
    }

    public final String getEpgUrl() {
        return this.epgUrl;
    }

    public final List<String> getFavoriteChannels() {
        return this.favoriteChannels;
    }

    public final List<String> getFavoriteGroups() {
        return this.favoriteGroups;
    }

    public final List<String> getGroupOrder() {
        return this.groupOrder;
    }

    public final int getGroupOrderSchema() {
        return this.groupOrderSchema;
    }

    public final List<String> getHiddenGroups() {
        return this.hiddenGroups;
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

    public final IptvTvSessionState getTvSession() {
        return this.tvSession;
    }

    public int hashCode() {
        return this.tvSession.hashCode() + androidx.compose.material3.d.e(this.playlists, androidx.compose.foundation.c.c((androidx.compose.material3.d.e(this.groupOrder, androidx.compose.material3.d.e(this.hiddenGroups, androidx.compose.material3.d.e(this.favoriteChannels, androidx.compose.material3.d.e(this.favoriteGroups, androidx.compose.foundation.c.c(this.m3uUrl.hashCode() * 31, 31, this.epgUrl), 31), 31), 31), 31) + this.groupOrderSchema) * 31, 31, this.sortOrder), 31);
    }

    public String toString() {
        String str = this.m3uUrl;
        String str2 = this.epgUrl;
        List<String> list = this.favoriteGroups;
        List<String> list2 = this.favoriteChannels;
        List<String> list3 = this.hiddenGroups;
        List<String> list4 = this.groupOrder;
        int i10 = this.groupOrderSchema;
        String str3 = this.sortOrder;
        List<IptvPlaylistEntry> list5 = this.playlists;
        IptvTvSessionState iptvTvSessionState = this.tvSession;
        StringBuilder sbR = a2.r("IptvCloudProfileState(m3uUrl=", str, ", epgUrl=", str2, ", favoriteGroups=");
        a2.A(sbR, list, ", favoriteChannels=", list2, ", hiddenGroups=");
        a2.A(sbR, list3, ", groupOrder=", list4, ", groupOrderSchema=");
        sbR.append(i10);
        sbR.append(", sortOrder=");
        sbR.append(str3);
        sbR.append(", playlists=");
        sbR.append(list5);
        sbR.append(", tvSession=");
        sbR.append(iptvTvSessionState);
        sbR.append(")");
        return sbR.toString();
    }

    public IptvCloudProfileState(String str, String str2, List<String> list, List<String> list2, List<String> list3, List<String> list4, int i10, String str3, List<IptvPlaylistEntry> list5, IptvTvSessionState iptvTvSessionState) {
        this.m3uUrl = str;
        this.epgUrl = str2;
        this.favoriteGroups = list;
        this.favoriteChannels = list2;
        this.hiddenGroups = list3;
        this.groupOrder = list4;
        this.groupOrderSchema = i10;
        this.sortOrder = str3;
        this.playlists = list5;
        this.tvSession = iptvTvSessionState;
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public /* synthetic */ IptvCloudProfileState(String str, String str2, List list, List list2, List list3, List list4, int i10, String str3, List list5, IptvTvSessionState iptvTvSessionState, int i11, kotlin.jvm.internal.h hVar) {
        String str4 = (i11 & 1) != 0 ? "" : str;
        String str5 = (i11 & 2) == 0 ? str2 : "";
        int i12 = i11 & 4;
        List list6 = kotlin.collections.z.f19728i;
        this(str4, str5, i12 != 0 ? list6 : list, (i11 & 8) != 0 ? list6 : list2, (i11 & 16) != 0 ? list6 : list3, (i11 & 32) != 0 ? list6 : list4, (i11 & 64) != 0 ? 0 : i10, (i11 & 128) != 0 ? "provider" : str3, (i11 & 256) == 0 ? list5 : list6, (i11 & 512) != 0 ? new IptvTvSessionState(null, null, null, 0L, null, 31, null) : iptvTvSessionState);
    }
}
