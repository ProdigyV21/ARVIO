package com.arflix.tv.ui.screens.tv;

import androidx.fragment.app.a2;
import androidx.media3.exoplayer.analytics.AnalyticsListener;
import com.arflix.tv.data.model.IptvChannel;
import com.arflix.tv.data.model.IptvSnapshot;
import com.arflix.tv.data.repository.IptvConfig;
import com.arflix.tv.data.repository.IptvPlaylistEntry;
import com.arflix.tv.data.repository.IptvTvSessionState;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\"\n\u0002\b7\b\u0087\b\u0018\u00002\u00020\u0001Bå\u0001\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0005\u0012\b\b\u0002\u0010\u0007\u001a\u00020\b\u0012\b\b\u0002\u0010\t\u001a\u00020\n\u0012\b\b\u0002\u0010\u000b\u001a\u00020\f\u0012\u0014\b\u0002\u0010\r\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u000f0\u000e\u0012\u000e\b\u0002\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00050\u0011\u0012\u001a\b\u0002\u0010\u0012\u001a\u0014\u0012\u0004\u0012\u00020\u0005\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000f0\u00110\u000e\u0012\b\b\u0002\u0010\u0013\u001a\u00020\u0014\u0012\b\b\u0002\u0010\u0015\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0016\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0017\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0018\u001a\u00020\u0005\u0012\u000e\b\u0002\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u00050\u001a\u0012\u000e\b\u0002\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u00050\u001a\u0012\b\b\u0002\u0010\u001c\u001a\u00020\u0003¢\u0006\u0004\b\u001d\u0010\u001eJ\t\u0010;\u001a\u00020\u0003HÆ\u0003J\u000b\u0010<\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010=\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\t\u0010>\u001a\u00020\bHÆ\u0003J\t\u0010?\u001a\u00020\nHÆ\u0003J\t\u0010@\u001a\u00020\fHÆ\u0003J\u0015\u0010A\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u000f0\u000eHÆ\u0003J\u000f\u0010B\u001a\b\u0012\u0004\u0012\u00020\u00050\u0011HÆ\u0003J\u001b\u0010C\u001a\u0014\u0012\u0004\u0012\u00020\u0005\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000f0\u00110\u000eHÆ\u0003J\t\u0010D\u001a\u00020\u0014HÆ\u0003J\t\u0010E\u001a\u00020\u0003HÆ\u0003J\t\u0010F\u001a\u00020\u0003HÆ\u0003J\t\u0010G\u001a\u00020\u0003HÆ\u0003J\t\u0010H\u001a\u00020\u0005HÆ\u0003J\u000f\u0010I\u001a\b\u0012\u0004\u0012\u00020\u00050\u001aHÆ\u0003J\u000f\u0010J\u001a\b\u0012\u0004\u0012\u00020\u00050\u001aHÆ\u0003J\t\u0010K\u001a\u00020\u0003HÆ\u0003Jç\u0001\u0010L\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00052\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\n2\b\b\u0002\u0010\u000b\u001a\u00020\f2\u0014\b\u0002\u0010\r\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u000f0\u000e2\u000e\b\u0002\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00050\u00112\u001a\b\u0002\u0010\u0012\u001a\u0014\u0012\u0004\u0012\u00020\u0005\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000f0\u00110\u000e2\b\b\u0002\u0010\u0013\u001a\u00020\u00142\b\b\u0002\u0010\u0015\u001a\u00020\u00032\b\b\u0002\u0010\u0016\u001a\u00020\u00032\b\b\u0002\u0010\u0017\u001a\u00020\u00032\b\b\u0002\u0010\u0018\u001a\u00020\u00052\u000e\b\u0002\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u00050\u001a2\u000e\b\u0002\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u00050\u001a2\b\b\u0002\u0010\u001c\u001a\u00020\u0003HÆ\u0001J\u0014\u0010M\u001a\u00020\u00032\b\u0010N\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010O\u001a\u00020\bHÖ\u0081\u0004J\n\u0010P\u001a\u00020\u0005HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0002\u0010\u001fR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b \u0010!R\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010!R\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b#\u0010$R\u0011\u0010\t\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b%\u0010&R\u0011\u0010\u000b\u001a\u00020\f¢\u0006\b\n\u0000\u001a\u0004\b'\u0010(R\u001d\u0010\r\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u000f0\u000e¢\u0006\b\n\u0000\u001a\u0004\b)\u0010*R\u0017\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00050\u0011¢\u0006\b\n\u0000\u001a\u0004\b+\u0010,R#\u0010\u0012\u001a\u0014\u0012\u0004\u0012\u00020\u0005\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000f0\u00110\u000e¢\u0006\b\n\u0000\u001a\u0004\b-\u0010*R\u0011\u0010\u0013\u001a\u00020\u0014¢\u0006\b\n\u0000\u001a\u0004\b.\u0010/R\u0011\u0010\u0015\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b0\u0010\u001fR\u0011\u0010\u0016\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b1\u0010\u001fR\u0011\u0010\u0017\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b2\u0010\u001fR\u0011\u0010\u0018\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b3\u0010!R\u0017\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u00050\u001a¢\u0006\b\n\u0000\u001a\u0004\b4\u00105R\u0017\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u00050\u001a¢\u0006\b\n\u0000\u001a\u0004\b6\u00105R\u0011\u0010\u001c\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b7\u0010\u001fR\u0011\u00108\u001a\u00020\u00038F¢\u0006\u0006\u001a\u0004\b8\u0010\u001fR\u0011\u00109\u001a\u00020\u00038F¢\u0006\u0006\u001a\u0004\b:\u0010\u001f¨\u0006Q"}, d2 = {"Lcom/arflix/tv/ui/screens/tv/TvUiState;", "", "isLoading", "", "error", "", "loadingMessage", "loadingPercent", "", "config", "Lcom/arflix/tv/data/repository/IptvConfig;", "snapshot", "Lcom/arflix/tv/data/model/IptvSnapshot;", "channelLookup", "", "Lcom/arflix/tv/data/model/IptvChannel;", "groups", "", "channelsByGroup", "tvSession", "Lcom/arflix/tv/data/repository/IptvTvSessionState;", "iptvPreferencesLoaded", "tvSessionLoaded", "favoritesOnly", "query", "epgLoadingChannelIds", "", "epgAttemptedChannelIds", "epgBackfillInProgress", "<init>", "(ZLjava/lang/String;Ljava/lang/String;ILcom/arflix/tv/data/repository/IptvConfig;Lcom/arflix/tv/data/model/IptvSnapshot;Ljava/util/Map;Ljava/util/List;Ljava/util/Map;Lcom/arflix/tv/data/repository/IptvTvSessionState;ZZZLjava/lang/String;Ljava/util/Set;Ljava/util/Set;Z)V", "()Z", "getError", "()Ljava/lang/String;", "getLoadingMessage", "getLoadingPercent", "()I", "getConfig", "()Lcom/arflix/tv/data/repository/IptvConfig;", "getSnapshot", "()Lcom/arflix/tv/data/model/IptvSnapshot;", "getChannelLookup", "()Ljava/util/Map;", "getGroups", "()Ljava/util/List;", "getChannelsByGroup", "getTvSession", "()Lcom/arflix/tv/data/repository/IptvTvSessionState;", "getIptvPreferencesLoaded", "getTvSessionLoaded", "getFavoritesOnly", "getQuery", "getEpgLoadingChannelIds", "()Ljava/util/Set;", "getEpgAttemptedChannelIds", "getEpgBackfillInProgress", "isConfigured", "hasPotentialGuideSource", "getHasPotentialGuideSource", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "component10", "component11", "component12", "component13", "component14", "component15", "component16", "component17", "copy", "equals", "other", "hashCode", "toString", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final /* data */ class TvUiState {
    public static final int $stable = 0;
    private final Map<String, IptvChannel> channelLookup;
    private final Map<String, List<IptvChannel>> channelsByGroup;
    private final IptvConfig config;
    private final Set<String> epgAttemptedChannelIds;
    private final boolean epgBackfillInProgress;
    private final Set<String> epgLoadingChannelIds;
    private final String error;
    private final boolean favoritesOnly;
    private final List<String> groups;
    private final boolean iptvPreferencesLoaded;
    private final boolean isLoading;
    private final String loadingMessage;
    private final int loadingPercent;
    private final String query;
    private final IptvSnapshot snapshot;
    private final IptvTvSessionState tvSession;
    private final boolean tvSessionLoaded;

    public TvUiState() {
        this(false, null, null, 0, null, null, null, null, null, null, false, false, false, null, null, null, false, 131071, null);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ TvUiState copy$default(TvUiState tvUiState, boolean z, String str, String str2, int i10, IptvConfig iptvConfig, IptvSnapshot iptvSnapshot, Map map, List list, Map map2, IptvTvSessionState iptvTvSessionState, boolean z5, boolean z10, boolean z11, String str3, Set set, Set set2, boolean z12, int i11, Object obj) {
        boolean z13;
        Set set3;
        boolean z14;
        TvUiState tvUiState2;
        Set set4;
        String str4;
        String str5;
        int i12;
        IptvConfig iptvConfig2;
        IptvSnapshot iptvSnapshot2;
        Map map3;
        List list2;
        Map map4;
        IptvTvSessionState iptvTvSessionState2;
        boolean z15;
        boolean z16;
        boolean z17;
        String str6;
        boolean z18 = (i11 & 1) != 0 ? tvUiState.isLoading : z;
        String str7 = (i11 & 2) != 0 ? tvUiState.error : str;
        String str8 = (i11 & 4) != 0 ? tvUiState.loadingMessage : str2;
        int i13 = (i11 & 8) != 0 ? tvUiState.loadingPercent : i10;
        IptvConfig iptvConfig3 = (i11 & 16) != 0 ? tvUiState.config : iptvConfig;
        IptvSnapshot iptvSnapshot3 = (i11 & 32) != 0 ? tvUiState.snapshot : iptvSnapshot;
        Map map5 = (i11 & 64) != 0 ? tvUiState.channelLookup : map;
        List list3 = (i11 & 128) != 0 ? tvUiState.groups : list;
        Map map6 = (i11 & 256) != 0 ? tvUiState.channelsByGroup : map2;
        IptvTvSessionState iptvTvSessionState3 = (i11 & 512) != 0 ? tvUiState.tvSession : iptvTvSessionState;
        boolean z19 = (i11 & 1024) != 0 ? tvUiState.iptvPreferencesLoaded : z5;
        boolean z20 = (i11 & 2048) != 0 ? tvUiState.tvSessionLoaded : z10;
        boolean z21 = (i11 & 4096) != 0 ? tvUiState.favoritesOnly : z11;
        String str9 = (i11 & 8192) != 0 ? tvUiState.query : str3;
        boolean z22 = z18;
        Set set5 = (i11 & 16384) != 0 ? tvUiState.epgLoadingChannelIds : set;
        Set set6 = (i11 & 32768) != 0 ? tvUiState.epgAttemptedChannelIds : set2;
        if ((i11 & 65536) != 0) {
            set3 = set6;
            z13 = tvUiState.epgBackfillInProgress;
            set4 = set5;
            str4 = str7;
            str5 = str8;
            i12 = i13;
            iptvConfig2 = iptvConfig3;
            iptvSnapshot2 = iptvSnapshot3;
            map3 = map5;
            list2 = list3;
            map4 = map6;
            iptvTvSessionState2 = iptvTvSessionState3;
            z15 = z19;
            z16 = z20;
            z17 = z21;
            str6 = str9;
            z14 = z22;
            tvUiState2 = tvUiState;
        } else {
            z13 = z12;
            set3 = set6;
            z14 = z22;
            tvUiState2 = tvUiState;
            set4 = set5;
            str4 = str7;
            str5 = str8;
            i12 = i13;
            iptvConfig2 = iptvConfig3;
            iptvSnapshot2 = iptvSnapshot3;
            map3 = map5;
            list2 = list3;
            map4 = map6;
            iptvTvSessionState2 = iptvTvSessionState3;
            z15 = z19;
            z16 = z20;
            z17 = z21;
            str6 = str9;
        }
        return tvUiState2.copy(z14, str4, str5, i12, iptvConfig2, iptvSnapshot2, map3, list2, map4, iptvTvSessionState2, z15, z16, z17, str6, set4, set3, z13);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final boolean getIsLoading() {
        return this.isLoading;
    }

    /* JADX INFO: renamed from: component10, reason: from getter */
    public final IptvTvSessionState getTvSession() {
        return this.tvSession;
    }

    /* JADX INFO: renamed from: component11, reason: from getter */
    public final boolean getIptvPreferencesLoaded() {
        return this.iptvPreferencesLoaded;
    }

    /* JADX INFO: renamed from: component12, reason: from getter */
    public final boolean getTvSessionLoaded() {
        return this.tvSessionLoaded;
    }

    /* JADX INFO: renamed from: component13, reason: from getter */
    public final boolean getFavoritesOnly() {
        return this.favoritesOnly;
    }

    /* JADX INFO: renamed from: component14, reason: from getter */
    public final String getQuery() {
        return this.query;
    }

    public final Set<String> component15() {
        return this.epgLoadingChannelIds;
    }

    public final Set<String> component16() {
        return this.epgAttemptedChannelIds;
    }

    /* JADX INFO: renamed from: component17, reason: from getter */
    public final boolean getEpgBackfillInProgress() {
        return this.epgBackfillInProgress;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getError() {
        return this.error;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final String getLoadingMessage() {
        return this.loadingMessage;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final int getLoadingPercent() {
        return this.loadingPercent;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final IptvConfig getConfig() {
        return this.config;
    }

    /* JADX INFO: renamed from: component6, reason: from getter */
    public final IptvSnapshot getSnapshot() {
        return this.snapshot;
    }

    public final Map<String, IptvChannel> component7() {
        return this.channelLookup;
    }

    public final List<String> component8() {
        return this.groups;
    }

    public final Map<String, List<IptvChannel>> component9() {
        return this.channelsByGroup;
    }

    public final TvUiState copy(boolean isLoading, String error, String loadingMessage, int loadingPercent, IptvConfig config, IptvSnapshot snapshot, Map<String, IptvChannel> channelLookup, List<String> groups, Map<String, ? extends List<IptvChannel>> channelsByGroup, IptvTvSessionState tvSession, boolean iptvPreferencesLoaded, boolean tvSessionLoaded, boolean favoritesOnly, String query, Set<String> epgLoadingChannelIds, Set<String> epgAttemptedChannelIds, boolean epgBackfillInProgress) {
        return new TvUiState(isLoading, error, loadingMessage, loadingPercent, config, snapshot, channelLookup, groups, channelsByGroup, tvSession, iptvPreferencesLoaded, tvSessionLoaded, favoritesOnly, query, epgLoadingChannelIds, epgAttemptedChannelIds, epgBackfillInProgress);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof TvUiState)) {
            return false;
        }
        TvUiState tvUiState = (TvUiState) other;
        return this.isLoading == tvUiState.isLoading && kotlin.jvm.internal.p.a(this.error, tvUiState.error) && kotlin.jvm.internal.p.a(this.loadingMessage, tvUiState.loadingMessage) && this.loadingPercent == tvUiState.loadingPercent && kotlin.jvm.internal.p.a(this.config, tvUiState.config) && kotlin.jvm.internal.p.a(this.snapshot, tvUiState.snapshot) && kotlin.jvm.internal.p.a(this.channelLookup, tvUiState.channelLookup) && kotlin.jvm.internal.p.a(this.groups, tvUiState.groups) && kotlin.jvm.internal.p.a(this.channelsByGroup, tvUiState.channelsByGroup) && kotlin.jvm.internal.p.a(this.tvSession, tvUiState.tvSession) && this.iptvPreferencesLoaded == tvUiState.iptvPreferencesLoaded && this.tvSessionLoaded == tvUiState.tvSessionLoaded && this.favoritesOnly == tvUiState.favoritesOnly && kotlin.jvm.internal.p.a(this.query, tvUiState.query) && kotlin.jvm.internal.p.a(this.epgLoadingChannelIds, tvUiState.epgLoadingChannelIds) && kotlin.jvm.internal.p.a(this.epgAttemptedChannelIds, tvUiState.epgAttemptedChannelIds) && this.epgBackfillInProgress == tvUiState.epgBackfillInProgress;
    }

    public final Map<String, IptvChannel> getChannelLookup() {
        return this.channelLookup;
    }

    public final Map<String, List<IptvChannel>> getChannelsByGroup() {
        return this.channelsByGroup;
    }

    public final IptvConfig getConfig() {
        return this.config;
    }

    public final Set<String> getEpgAttemptedChannelIds() {
        return this.epgAttemptedChannelIds;
    }

    public final boolean getEpgBackfillInProgress() {
        return this.epgBackfillInProgress;
    }

    public final Set<String> getEpgLoadingChannelIds() {
        return this.epgLoadingChannelIds;
    }

    public final String getError() {
        return this.error;
    }

    public final boolean getFavoritesOnly() {
        return this.favoritesOnly;
    }

    public final List<String> getGroups() {
        return this.groups;
    }

    public final boolean getHasPotentialGuideSource() {
        return TvViewModelKt.hasConfiguredEpgSource(this.config);
    }

    public final boolean getIptvPreferencesLoaded() {
        return this.iptvPreferencesLoaded;
    }

    public final String getLoadingMessage() {
        return this.loadingMessage;
    }

    public final int getLoadingPercent() {
        return this.loadingPercent;
    }

    public final String getQuery() {
        return this.query;
    }

    public final IptvSnapshot getSnapshot() {
        return this.snapshot;
    }

    public final IptvTvSessionState getTvSession() {
        return this.tvSession;
    }

    public final boolean getTvSessionLoaded() {
        return this.tvSessionLoaded;
    }

    public int hashCode() {
        int i10 = (this.isLoading ? 1231 : 1237) * 31;
        String str = this.error;
        int iHashCode = (i10 + (str == null ? 0 : str.hashCode())) * 31;
        String str2 = this.loadingMessage;
        return ((this.epgAttemptedChannelIds.hashCode() + ((this.epgLoadingChannelIds.hashCode() + androidx.compose.foundation.c.c((((((((this.tvSession.hashCode() + a2.g(this.channelsByGroup, androidx.compose.material3.d.e(this.groups, a2.g(this.channelLookup, (this.snapshot.hashCode() + ((this.config.hashCode() + ((((iHashCode + (str2 != null ? str2.hashCode() : 0)) * 31) + this.loadingPercent) * 31)) * 31)) * 31, 31), 31), 31)) * 31) + (this.iptvPreferencesLoaded ? 1231 : 1237)) * 31) + (this.tvSessionLoaded ? 1231 : 1237)) * 31) + (this.favoritesOnly ? 1231 : 1237)) * 31, 31, this.query)) * 31)) * 31) + (this.epgBackfillInProgress ? 1231 : 1237);
    }

    public final boolean isConfigured() {
        if (!kotlin.text.o.h0(this.config.getM3uUrl()) || !kotlin.text.o.h0(this.config.getStalkerPortalUrl())) {
            return true;
        }
        List<IptvPlaylistEntry> playlists = this.config.getPlaylists();
        if (playlists != null && playlists.isEmpty()) {
            return false;
        }
        for (IptvPlaylistEntry iptvPlaylistEntry : playlists) {
            if (iptvPlaylistEntry.getEnabled() && !kotlin.text.o.h0(iptvPlaylistEntry.getM3uUrl())) {
                return true;
            }
        }
        return false;
    }

    public final boolean isLoading() {
        return this.isLoading;
    }

    public String toString() {
        boolean z = this.isLoading;
        String str = this.error;
        String str2 = this.loadingMessage;
        int i10 = this.loadingPercent;
        IptvConfig iptvConfig = this.config;
        IptvSnapshot iptvSnapshot = this.snapshot;
        Map<String, IptvChannel> map = this.channelLookup;
        List<String> list = this.groups;
        Map<String, List<IptvChannel>> map2 = this.channelsByGroup;
        IptvTvSessionState iptvTvSessionState = this.tvSession;
        boolean z5 = this.iptvPreferencesLoaded;
        boolean z10 = this.tvSessionLoaded;
        boolean z11 = this.favoritesOnly;
        String str3 = this.query;
        Set<String> set = this.epgLoadingChannelIds;
        Set<String> set2 = this.epgAttemptedChannelIds;
        boolean z12 = this.epgBackfillInProgress;
        StringBuilder sb2 = new StringBuilder("TvUiState(isLoading=");
        sb2.append(z);
        sb2.append(", error=");
        sb2.append(str);
        sb2.append(", loadingMessage=");
        androidx.compose.material3.d.A(sb2, str2, ", loadingPercent=", i10, ", config=");
        sb2.append(iptvConfig);
        sb2.append(", snapshot=");
        sb2.append(iptvSnapshot);
        sb2.append(", channelLookup=");
        sb2.append(map);
        sb2.append(", groups=");
        sb2.append(list);
        sb2.append(", channelsByGroup=");
        sb2.append(map2);
        sb2.append(", tvSession=");
        sb2.append(iptvTvSessionState);
        sb2.append(", iptvPreferencesLoaded=");
        v.f.h(sb2, z5, ", tvSessionLoaded=", z10, ", favoritesOnly=");
        a2.B(sb2, z11, ", query=", str3, ", epgLoadingChannelIds=");
        sb2.append(set);
        sb2.append(", epgAttemptedChannelIds=");
        sb2.append(set2);
        sb2.append(", epgBackfillInProgress=");
        return a0.c.m(")", z12, sb2);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public TvUiState(boolean z, String str, String str2, int i10, IptvConfig iptvConfig, IptvSnapshot iptvSnapshot, Map<String, IptvChannel> map, List<String> list, Map<String, ? extends List<IptvChannel>> map2, IptvTvSessionState iptvTvSessionState, boolean z5, boolean z10, boolean z11, String str3, Set<String> set, Set<String> set2, boolean z12) {
        this.isLoading = z;
        this.error = str;
        this.loadingMessage = str2;
        this.loadingPercent = i10;
        this.config = iptvConfig;
        this.snapshot = iptvSnapshot;
        this.channelLookup = map;
        this.groups = list;
        this.channelsByGroup = map2;
        this.tvSession = iptvTvSessionState;
        this.iptvPreferencesLoaded = z5;
        this.tvSessionLoaded = z10;
        this.favoritesOnly = z11;
        this.query = str3;
        this.epgLoadingChannelIds = set;
        this.epgAttemptedChannelIds = set2;
        this.epgBackfillInProgress = z12;
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public /* synthetic */ TvUiState(boolean z, String str, String str2, int i10, IptvConfig iptvConfig, IptvSnapshot iptvSnapshot, Map map, List list, Map map2, IptvTvSessionState iptvTvSessionState, boolean z5, boolean z10, boolean z11, String str3, Set set, Set set2, boolean z12, int i11, kotlin.jvm.internal.h hVar) {
        boolean z13 = (i11 & 1) != 0 ? false : z;
        String str4 = (i11 & 2) != 0 ? null : str;
        String str5 = (i11 & 4) == 0 ? str2 : null;
        int i12 = (i11 & 8) != 0 ? 0 : i10;
        IptvConfig iptvConfig2 = (i11 & 16) != 0 ? new IptvConfig(null, null, null, null, null, null, 63, null) : iptvConfig;
        IptvSnapshot iptvSnapshot2 = (i11 & 32) != 0 ? new IptvSnapshot(null, null, null, null, null, null, null, null, null, null, AnalyticsListener.EVENT_DRM_KEYS_LOADED, null) : iptvSnapshot;
        int i13 = i11 & 64;
        Map map3 = kotlin.collections.a0.f19683i;
        Map map4 = i13 != 0 ? map3 : map;
        List list2 = (i11 & 128) != 0 ? kotlin.collections.z.f19728i : list;
        map3 = (i11 & 256) == 0 ? map2 : map3;
        IptvTvSessionState iptvTvSessionState2 = (i11 & 512) != 0 ? new IptvTvSessionState(null, null, null, 0L, null, 31, null) : iptvTvSessionState;
        boolean z14 = (i11 & 1024) != 0 ? false : z5;
        boolean z15 = (i11 & 2048) != 0 ? false : z10;
        boolean z16 = (i11 & 4096) != 0 ? false : z11;
        String str6 = (i11 & 8192) != 0 ? "" : str3;
        int i14 = i11 & 16384;
        Set set3 = kotlin.collections.b0.f19686i;
        this(z13, str4, str5, i12, iptvConfig2, iptvSnapshot2, map4, list2, map3, iptvTvSessionState2, z14, z15, z16, str6, i14 != 0 ? set3 : set, (i11 & 32768) == 0 ? set2 : set3, (i11 & 65536) != 0 ? false : z12);
    }
}
