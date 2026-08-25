package com.arflix.tv.data.model;

import androidx.compose.foundation.c;
import androidx.compose.material3.d;
import androidx.fragment.app.a2;
import androidx.media3.exoplayer.analytics.AnalyticsListener;
import j$.time.Instant;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.a0;
import kotlin.collections.z;
import kotlin.jvm.internal.h;
import kotlin.jvm.internal.p;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\b\u0015\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0011\b\u0087\b\u0018\u00002\u00020\u0001B©\u0001\u0012\u000e\b\u0002\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u0002\u0012\u001a\b\u0002\u0010\u0007\u001a\u0014\u0012\u0004\u0012\u00020\u0006\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\u00020\u0005\u0012\u0014\b\u0002\u0010\t\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\b0\u0005\u0012\u000e\b\u0002\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00060\u0002\u0012\u000e\b\u0002\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00060\u0002\u0012\u000e\b\u0002\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00060\u0002\u0012\u000e\b\u0002\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00060\u0002\u0012\b\b\u0002\u0010\u000e\u001a\u00020\u0006\u0012\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u0006\u0012\b\b\u0002\u0010\u0011\u001a\u00020\u0010¢\u0006\u0004\b\u0012\u0010\u0013J\u0016\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00030\u0002HÆ\u0003¢\u0006\u0004\b\u0014\u0010\u0015J\"\u0010\u0016\u001a\u0014\u0012\u0004\u0012\u00020\u0006\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\u00020\u0005HÆ\u0003¢\u0006\u0004\b\u0016\u0010\u0017J\u001c\u0010\u0018\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\b0\u0005HÆ\u0003¢\u0006\u0004\b\u0018\u0010\u0017J\u0016\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u00060\u0002HÆ\u0003¢\u0006\u0004\b\u0019\u0010\u0015J\u0016\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u00060\u0002HÆ\u0003¢\u0006\u0004\b\u001a\u0010\u0015J\u0016\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u00060\u0002HÆ\u0003¢\u0006\u0004\b\u001b\u0010\u0015J\u0016\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u00060\u0002HÆ\u0003¢\u0006\u0004\b\u001c\u0010\u0015J\u0010\u0010\u001d\u001a\u00020\u0006HÆ\u0003¢\u0006\u0004\b\u001d\u0010\u001eJ\u0012\u0010\u001f\u001a\u0004\u0018\u00010\u0006HÆ\u0003¢\u0006\u0004\b\u001f\u0010\u001eJ\u0010\u0010 \u001a\u00020\u0010HÆ\u0003¢\u0006\u0004\b \u0010!J²\u0001\u0010\"\u001a\u00020\u00002\u000e\b\u0002\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u00022\u001a\b\u0002\u0010\u0007\u001a\u0014\u0012\u0004\u0012\u00020\u0006\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\u00020\u00052\u0014\b\u0002\u0010\t\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\b0\u00052\u000e\b\u0002\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00060\u00022\u000e\b\u0002\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00060\u00022\u000e\b\u0002\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00060\u00022\u000e\b\u0002\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00060\u00022\b\b\u0002\u0010\u000e\u001a\u00020\u00062\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u00062\b\b\u0002\u0010\u0011\u001a\u00020\u0010HÆ\u0001¢\u0006\u0004\b\"\u0010#J\u0010\u0010$\u001a\u00020\u0006HÖ\u0001¢\u0006\u0004\b$\u0010\u001eJ\u0010\u0010&\u001a\u00020%HÖ\u0001¢\u0006\u0004\b&\u0010'J\u001a\u0010*\u001a\u00020)2\b\u0010(\u001a\u0004\u0018\u00010\u0001HÖ\u0003¢\u0006\u0004\b*\u0010+R\u001d\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u00028\u0006¢\u0006\f\n\u0004\b\u0004\u0010,\u001a\u0004\b-\u0010\u0015R)\u0010\u0007\u001a\u0014\u0012\u0004\u0012\u00020\u0006\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\u00020\u00058\u0006¢\u0006\f\n\u0004\b\u0007\u0010.\u001a\u0004\b/\u0010\u0017R#\u0010\t\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\b0\u00058\u0006¢\u0006\f\n\u0004\b\t\u0010.\u001a\u0004\b0\u0010\u0017R\u001d\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00060\u00028\u0006¢\u0006\f\n\u0004\b\n\u0010,\u001a\u0004\b1\u0010\u0015R\u001d\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00060\u00028\u0006¢\u0006\f\n\u0004\b\u000b\u0010,\u001a\u0004\b2\u0010\u0015R\u001d\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00060\u00028\u0006¢\u0006\f\n\u0004\b\f\u0010,\u001a\u0004\b3\u0010\u0015R\u001d\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00060\u00028\u0006¢\u0006\f\n\u0004\b\r\u0010,\u001a\u0004\b4\u0010\u0015R\u0017\u0010\u000e\u001a\u00020\u00068\u0006¢\u0006\f\n\u0004\b\u000e\u00105\u001a\u0004\b6\u0010\u001eR\u0019\u0010\u000f\u001a\u0004\u0018\u00010\u00068\u0006¢\u0006\f\n\u0004\b\u000f\u00105\u001a\u0004\b7\u0010\u001eR\u0017\u0010\u0011\u001a\u00020\u00108\u0006¢\u0006\f\n\u0004\b\u0011\u00108\u001a\u0004\b9\u0010!¨\u0006:"}, d2 = {"Lcom/arflix/tv/data/model/IptvSnapshot;", "", "", "Lcom/arflix/tv/data/model/IptvChannel;", "channels", "", "", "grouped", "Lcom/arflix/tv/data/model/IptvNowNext;", "nowNext", "favoriteGroups", "favoriteChannels", "hiddenGroups", "groupOrder", "sortOrder", "epgWarning", "j$/time/Instant", "loadedAt", "<init>", "(Ljava/util/List;Ljava/util/Map;Ljava/util/Map;Ljava/util/List;Ljava/util/List;Ljava/util/List;Ljava/util/List;Ljava/lang/String;Ljava/lang/String;Lj$/time/Instant;)V", "component1", "()Ljava/util/List;", "component2", "()Ljava/util/Map;", "component3", "component4", "component5", "component6", "component7", "component8", "()Ljava/lang/String;", "component9", "component10", "()Lj$/time/Instant;", "copy", "(Ljava/util/List;Ljava/util/Map;Ljava/util/Map;Ljava/util/List;Ljava/util/List;Ljava/util/List;Ljava/util/List;Ljava/lang/String;Ljava/lang/String;Lj$/time/Instant;)Lcom/arflix/tv/data/model/IptvSnapshot;", "toString", "", "hashCode", "()I", "other", "", "equals", "(Ljava/lang/Object;)Z", "Ljava/util/List;", "getChannels", "Ljava/util/Map;", "getGrouped", "getNowNext", "getFavoriteGroups", "getFavoriteChannels", "getHiddenGroups", "getGroupOrder", "Ljava/lang/String;", "getSortOrder", "getEpgWarning", "Lj$/time/Instant;", "getLoadedAt", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final /* data */ class IptvSnapshot {
    public static final int $stable = 0;
    private final List<IptvChannel> channels;
    private final String epgWarning;
    private final List<String> favoriteChannels;
    private final List<String> favoriteGroups;
    private final List<String> groupOrder;
    private final Map<String, List<IptvChannel>> grouped;
    private final List<String> hiddenGroups;
    private final Instant loadedAt;
    private final Map<String, IptvNowNext> nowNext;
    private final String sortOrder;

    public IptvSnapshot() {
        this(null, null, null, null, null, null, null, null, null, null, AnalyticsListener.EVENT_DRM_KEYS_LOADED, null);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ IptvSnapshot copy$default(IptvSnapshot iptvSnapshot, List list, Map map, Map map2, List list2, List list3, List list4, List list5, String str, String str2, Instant instant, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            list = iptvSnapshot.channels;
        }
        if ((i10 & 2) != 0) {
            map = iptvSnapshot.grouped;
        }
        if ((i10 & 4) != 0) {
            map2 = iptvSnapshot.nowNext;
        }
        if ((i10 & 8) != 0) {
            list2 = iptvSnapshot.favoriteGroups;
        }
        if ((i10 & 16) != 0) {
            list3 = iptvSnapshot.favoriteChannels;
        }
        if ((i10 & 32) != 0) {
            list4 = iptvSnapshot.hiddenGroups;
        }
        if ((i10 & 64) != 0) {
            list5 = iptvSnapshot.groupOrder;
        }
        if ((i10 & 128) != 0) {
            str = iptvSnapshot.sortOrder;
        }
        if ((i10 & 256) != 0) {
            str2 = iptvSnapshot.epgWarning;
        }
        if ((i10 & 512) != 0) {
            instant = iptvSnapshot.loadedAt;
        }
        String str3 = str2;
        Instant instant2 = instant;
        List list6 = list5;
        String str4 = str;
        List list7 = list3;
        List list8 = list4;
        return iptvSnapshot.copy(list, map, map2, list2, list7, list8, list6, str4, str3, instant2);
    }

    public final List<IptvChannel> component1() {
        return this.channels;
    }

    /* JADX INFO: renamed from: component10, reason: from getter */
    public final Instant getLoadedAt() {
        return this.loadedAt;
    }

    public final Map<String, List<IptvChannel>> component2() {
        return this.grouped;
    }

    public final Map<String, IptvNowNext> component3() {
        return this.nowNext;
    }

    public final List<String> component4() {
        return this.favoriteGroups;
    }

    public final List<String> component5() {
        return this.favoriteChannels;
    }

    public final List<String> component6() {
        return this.hiddenGroups;
    }

    public final List<String> component7() {
        return this.groupOrder;
    }

    /* JADX INFO: renamed from: component8, reason: from getter */
    public final String getSortOrder() {
        return this.sortOrder;
    }

    /* JADX INFO: renamed from: component9, reason: from getter */
    public final String getEpgWarning() {
        return this.epgWarning;
    }

    public final IptvSnapshot copy(List<IptvChannel> channels, Map<String, ? extends List<IptvChannel>> grouped, Map<String, IptvNowNext> nowNext, List<String> favoriteGroups, List<String> favoriteChannels, List<String> hiddenGroups, List<String> groupOrder, String sortOrder, String epgWarning, Instant loadedAt) {
        return new IptvSnapshot(channels, grouped, nowNext, favoriteGroups, favoriteChannels, hiddenGroups, groupOrder, sortOrder, epgWarning, loadedAt);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof IptvSnapshot)) {
            return false;
        }
        IptvSnapshot iptvSnapshot = (IptvSnapshot) other;
        return p.a(this.channels, iptvSnapshot.channels) && p.a(this.grouped, iptvSnapshot.grouped) && p.a(this.nowNext, iptvSnapshot.nowNext) && p.a(this.favoriteGroups, iptvSnapshot.favoriteGroups) && p.a(this.favoriteChannels, iptvSnapshot.favoriteChannels) && p.a(this.hiddenGroups, iptvSnapshot.hiddenGroups) && p.a(this.groupOrder, iptvSnapshot.groupOrder) && p.a(this.sortOrder, iptvSnapshot.sortOrder) && p.a(this.epgWarning, iptvSnapshot.epgWarning) && p.a(this.loadedAt, iptvSnapshot.loadedAt);
    }

    public final List<IptvChannel> getChannels() {
        return this.channels;
    }

    public final String getEpgWarning() {
        return this.epgWarning;
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

    public final Map<String, List<IptvChannel>> getGrouped() {
        return this.grouped;
    }

    public final List<String> getHiddenGroups() {
        return this.hiddenGroups;
    }

    public final Instant getLoadedAt() {
        return this.loadedAt;
    }

    public final Map<String, IptvNowNext> getNowNext() {
        return this.nowNext;
    }

    public final String getSortOrder() {
        return this.sortOrder;
    }

    public int hashCode() {
        int iC = c.c(d.e(this.groupOrder, d.e(this.hiddenGroups, d.e(this.favoriteChannels, d.e(this.favoriteGroups, a2.g(this.nowNext, a2.g(this.grouped, this.channels.hashCode() * 31, 31), 31), 31), 31), 31), 31), 31, this.sortOrder);
        String str = this.epgWarning;
        return this.loadedAt.hashCode() + ((iC + (str == null ? 0 : str.hashCode())) * 31);
    }

    public String toString() {
        List<IptvChannel> list = this.channels;
        Map<String, List<IptvChannel>> map = this.grouped;
        Map<String, IptvNowNext> map2 = this.nowNext;
        List<String> list2 = this.favoriteGroups;
        List<String> list3 = this.favoriteChannels;
        List<String> list4 = this.hiddenGroups;
        List<String> list5 = this.groupOrder;
        String str = this.sortOrder;
        String str2 = this.epgWarning;
        Instant instant = this.loadedAt;
        StringBuilder sb2 = new StringBuilder("IptvSnapshot(channels=");
        sb2.append(list);
        sb2.append(", grouped=");
        sb2.append(map);
        sb2.append(", nowNext=");
        sb2.append(map2);
        sb2.append(", favoriteGroups=");
        sb2.append(list2);
        sb2.append(", favoriteChannels=");
        a2.A(sb2, list3, ", hiddenGroups=", list4, ", groupOrder=");
        sb2.append(list5);
        sb2.append(", sortOrder=");
        sb2.append(str);
        sb2.append(", epgWarning=");
        sb2.append(str2);
        sb2.append(", loadedAt=");
        sb2.append(instant);
        sb2.append(")");
        return sb2.toString();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public IptvSnapshot(List<IptvChannel> list, Map<String, ? extends List<IptvChannel>> map, Map<String, IptvNowNext> map2, List<String> list2, List<String> list3, List<String> list4, List<String> list5, String str, String str2, Instant instant) {
        this.channels = list;
        this.grouped = map;
        this.nowNext = map2;
        this.favoriteGroups = list2;
        this.favoriteChannels = list3;
        this.hiddenGroups = list4;
        this.groupOrder = list5;
        this.sortOrder = str;
        this.epgWarning = str2;
        this.loadedAt = instant;
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public /* synthetic */ IptvSnapshot(List list, Map map, Map map2, List list2, List list3, List list4, List list5, String str, String str2, Instant instant, int i10, h hVar) {
        int i11 = i10 & 1;
        z zVar = z.f19728i;
        list = i11 != 0 ? zVar : list;
        int i12 = i10 & 2;
        a0 a0Var = a0.f19683i;
        this(list, i12 != 0 ? a0Var : map, (i10 & 4) != 0 ? a0Var : map2, (i10 & 8) != 0 ? zVar : list2, (i10 & 16) != 0 ? zVar : list3, (i10 & 32) != 0 ? zVar : list4, (i10 & 64) != 0 ? zVar : list5, (i10 & 128) != 0 ? "provider" : str, (i10 & 256) != 0 ? null : str2, (i10 & 512) != 0 ? Instant.now() : instant);
    }
}
