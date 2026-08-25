package com.arflix.tv.ui.screens.tv;

import androidx.compose.material3.MenuKt;
import androidx.credentials.exceptions.publickeycredential.DomExceptionUtils;
import com.arflix.tv.data.model.IptvChannel;
import com.arflix.tv.data.model.IptvSnapshot;
import com.arflix.tv.data.repository.IptvConfig;
import com.arflix.tv.data.repository.IptvPlaylistEntry;
import com.google.android.gms.cast.MediaError;
import com.google.common.util.concurrent.r0;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000^\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\t\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0006\u001a\u0016\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00010\u00152\u0006\u0010\u0016\u001a\u00020\u0017H\u0002\u001a(\u0010\u0018\u001a\u0012\u0012\u0004\u0012\u00020\u00010\u0019j\b\u0012\u0004\u0012\u00020\u0001`\u001a2\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u001b\u001a\u00020\u0003H\u0002\u001a\u0010\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u0003H\u0002\u001a\u0016\u0010\u001f\u001a\u00020\u00032\f\u0010 \u001a\b\u0012\u0004\u0012\u00020!0\u0015H\u0002\u001a\u0010\u0010\"\u001a\u00020\u00172\u0006\u0010\u0016\u001a\u00020\u0017H\u0002\u001a\u0016\u0010#\u001a\b\u0012\u0004\u0012\u00020\u00010\u00152\u0006\u0010$\u001a\u00020%H\u0002\u001a8\u0010&\u001a\u0014\u0012\u0004\u0012\u00020\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u00020!0\u00150'2\u0006\u0010$\u001a\u00020%2\u0006\u0010(\u001a\u00020\u00012\f\u0010)\u001a\b\u0012\u0004\u0012\u00020\u00010\u0015H\u0002\u001a\f\u0010*\u001a\u00020\u001d*\u00020\u0001H\u0002\u001a\f\u0010+\u001a\u00020\u001d*\u00020\u0001H\u0002\u001a$\u0010,\u001a\b\u0012\u0004\u0012\u00020!0\u00152\f\u0010-\u001a\b\u0012\u0004\u0012\u00020!0\u00152\u0006\u0010.\u001a\u00020\u0001H\u0002\u001a\u0010\u0010/\u001a\u00020\u001d2\u0006\u00100\u001a\u000201H\u0002\u001a\f\u00102\u001a\u00020\u001d*\u000201H\u0002\u001a\u0010\u00103\u001a\u00020\u001d2\u0006\u00104\u001a\u00020\u0001H\u0002\u001a\u0010\u00105\u001a\u00020\u001d2\u0006\u00104\u001a\u00020\u0001H\u0002\u001a\f\u00106\u001a\u00020\u0001*\u000201H\u0000\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0080T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0002\u001a\u00020\u0003X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0004\u001a\u00020\u0003X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0005\u001a\u00020\u0003X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0006\u001a\u00020\u0003X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0007\u001a\u00020\u0003X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\b\u001a\u00020\u0003X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\t\u001a\u00020\u0003X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\n\u001a\u00020\u0003X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u000b\u001a\u00020\u0003X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\f\u001a\u00020\rX\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u000e\u001a\u00020\rX\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u000f\u001a\u00020\rX\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0010\u001a\u00020\u0011X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0012\u001a\u00020\rX\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0013\u001a\u00020\rX\u0082T¢\u0006\u0002\n\u0000¨\u00067"}, d2 = {"FAVORITES_GROUP_NAME", "", "EpgLoadingStateLimit", "", "EpgAttemptedStateLimit", "LargeIptvListChannelCount", "StandardPriorityEpgLimit", "LargeListPriorityCacheLimit", "LargeListFocusedNetworkEpgLimit", "LargeListFavoriteEpgPasses", "LargeListFavoritePrefetchLookahead", "RichCatchupRecentTarget", "CatchupHistoryWindowMs", "", "RichCatchupRefreshThrottleMs", "CurrentChannelEpgRefreshThrottleMs", "LargeListCompleteGuideCoverageTarget", "", "PlaybackEpgBackfillResumeDelayMs", "LargeListCompleteEpgBackfillStartupDelayMs", "buildStartupWarmGroups", "", "state", "Lcom/arflix/tv/ui/screens/tv/TvUiState;", "buildPriorityEpgChannelIds", "Ljava/util/LinkedHashSet;", "Lkotlin/collections/LinkedHashSet;", "maxChannels", "isLargeIptvList", "", "channelCount", "guideCapableChannelCount", "channels", "Lcom/arflix/tv/data/model/IptvChannel;", "setPreparedContent", "buildPreparedGroups", "snapshot", "Lcom/arflix/tv/data/model/IptvSnapshot;", "buildPreparedChannelsByGroup", "", "query", "groups", "isNetherlandsGroup", "isPriorityStartupGroup", "filterTvChannels", "source", "trimmedQuery", "hasNetworkEpgSource", "config", "Lcom/arflix/tv/data/repository/IptvConfig;", "hasConfiguredEpgSource", "looksLikeXtream", "url", "looksLikeStalkerStreamCommand", "syncSignature", "app_playRelease"}, k = 2, mv = {2, 3, 0}, xi = 48)
public final class TvViewModelKt {
    private static final long CatchupHistoryWindowMs = 172800000;
    private static final long CurrentChannelEpgRefreshThrottleMs = 12000;
    private static final int EpgAttemptedStateLimit = 2400;
    private static final int EpgLoadingStateLimit = 800;
    public static final String FAVORITES_GROUP_NAME = "My Favorites";
    private static final int LargeIptvListChannelCount = 10000;
    private static final long LargeListCompleteEpgBackfillStartupDelayMs = 180000;
    private static final float LargeListCompleteGuideCoverageTarget = 0.75f;
    private static final int LargeListFavoriteEpgPasses = 12;
    private static final int LargeListFavoritePrefetchLookahead = 28;
    private static final int LargeListFocusedNetworkEpgLimit = 24;
    private static final int LargeListPriorityCacheLimit = 360;
    private static final long PlaybackEpgBackfillResumeDelayMs = 90000;
    private static final int RichCatchupRecentTarget = 6;
    private static final long RichCatchupRefreshThrottleMs = 45000;
    private static final int StandardPriorityEpgLimit = 3200;

    private static final Map<String, List<IptvChannel>> buildPreparedChannelsByGroup(IptvSnapshot iptvSnapshot, String str, List<String> list) {
        if (list.isEmpty()) {
            return kotlin.collections.a0.f19683i;
        }
        String lowerCase = kotlin.text.o.L0(str).toString().toLowerCase(Locale.ROOT);
        HashSet hashSetA1 = kotlin.collections.x.a1(iptvSnapshot.getFavoriteChannels());
        z6.f fVar = new z6.f(list.size());
        for (String str2 : list) {
            boolean zA = kotlin.jvm.internal.p.a(str2, FAVORITES_GROUP_NAME);
            List<IptvChannel> listW0 = kotlin.collections.z.f19728i;
            if (!zA) {
                List<IptvChannel> list2 = iptvSnapshot.getGrouped().get(str2);
                if (list2 != null) {
                    listW0 = list2;
                }
            } else if (!hashSetA1.isEmpty()) {
                ab.k kVarH1 = kotlin.collections.x.h1(iptvSnapshot.getFavoriteChannels());
                int iQ0 = kotlin.collections.i0.q0(kotlin.collections.s.U(kVarH1, 10));
                if (iQ0 < 16) {
                    iQ0 = 16;
                }
                final LinkedHashMap linkedHashMap = new LinkedHashMap(iQ0);
                Iterator it = kVarH1.iterator();
                while (true) {
                    ga.d dVar = (ga.d) it;
                    if (!dVar.f15570m.hasNext()) {
                        break;
                    }
                    kotlin.collections.d0 d0Var = (kotlin.collections.d0) dVar.next();
                    linkedHashMap.put((String) d0Var.f19689b, Integer.valueOf(d0Var.f19688a));
                }
                List<IptvChannel> channels = iptvSnapshot.getChannels();
                ArrayList arrayList = new ArrayList();
                for (Object obj : channels) {
                    if (hashSetA1.contains(((IptvChannel) obj).getId())) {
                        arrayList.add(obj);
                    }
                }
                listW0 = kotlin.collections.x.W0(arrayList, new Comparator() { // from class: com.arflix.tv.ui.screens.tv.TvViewModelKt$buildPreparedChannelsByGroup$lambda$0$0$$inlined$sortedBy$1
                    /* JADX WARN: Multi-variable type inference failed */
                    @Override // java.util.Comparator
                    public final int compare(T t2, T t10) {
                        Integer num = (Integer) linkedHashMap.get(((IptvChannel) t2).getId());
                        Integer numValueOf = Integer.valueOf(num != null ? num.intValue() : Integer.MAX_VALUE);
                        Integer num2 = (Integer) linkedHashMap.get(((IptvChannel) t10).getId());
                        return r0.e(numValueOf, Integer.valueOf(num2 != null ? num2.intValue() : Integer.MAX_VALUE));
                    }
                });
            }
            fVar.put(str2, filterTvChannels(listW0, lowerCase));
        }
        return fVar.c();
    }

    private static final List<String> buildPreparedGroups(IptvSnapshot iptvSnapshot) {
        List<String> listI0;
        List listC1 = kotlin.collections.x.c1(iptvSnapshot.getGrouped().keySet());
        HashSet hashSetA1 = kotlin.collections.x.a1(iptvSnapshot.getHiddenGroups());
        ArrayList arrayList = new ArrayList();
        for (Object obj : listC1) {
            if (!hashSetA1.contains((String) obj)) {
                arrayList.add(obj);
            }
        }
        List<String> favoriteGroups = iptvSnapshot.getFavoriteGroups();
        ArrayList arrayList2 = new ArrayList();
        for (Object obj2 : favoriteGroups) {
            if (arrayList.contains((String) obj2)) {
                arrayList2.add(obj2);
            }
        }
        ArrayList arrayList3 = new ArrayList();
        for (Object obj3 : arrayList) {
            if (!iptvSnapshot.getFavoriteGroups().contains((String) obj3)) {
                arrayList3.add(obj3);
            }
        }
        if (iptvSnapshot.getGroupOrder().isEmpty()) {
            listI0 = kotlin.collections.x.I0(arrayList2, arrayList3);
        } else {
            ab.k kVarH1 = kotlin.collections.x.h1(iptvSnapshot.getGroupOrder());
            int iQ0 = kotlin.collections.i0.q0(kotlin.collections.s.U(kVarH1, 10));
            if (iQ0 < 16) {
                iQ0 = 16;
            }
            final LinkedHashMap linkedHashMap = new LinkedHashMap(iQ0);
            Iterator it = kVarH1.iterator();
            while (true) {
                ga.d dVar = (ga.d) it;
                if (!dVar.f15570m.hasNext()) {
                    break;
                }
                kotlin.collections.d0 d0Var = (kotlin.collections.d0) dVar.next();
                linkedHashMap.put((String) d0Var.f19689b, Integer.valueOf(d0Var.f19688a));
            }
            listI0 = kotlin.collections.x.W0(kotlin.collections.x.I0(arrayList2, arrayList3), new Comparator() { // from class: com.arflix.tv.ui.screens.tv.TvViewModelKt$buildPreparedGroups$$inlined$sortedBy$1
                /* JADX WARN: Multi-variable type inference failed */
                @Override // java.util.Comparator
                public final int compare(T t2, T t10) {
                    Integer num = (Integer) linkedHashMap.get((String) t2);
                    Integer numValueOf = Integer.valueOf(num != null ? num.intValue() : Integer.MAX_VALUE);
                    Integer num2 = (Integer) linkedHashMap.get((String) t10);
                    return r0.e(numValueOf, Integer.valueOf(num2 != null ? num2.intValue() : Integer.MAX_VALUE));
                }
            });
        }
        HashSet hashSetA12 = kotlin.collections.x.a1(iptvSnapshot.getFavoriteChannels());
        List<IptvChannel> channels = iptvSnapshot.getChannels();
        if (channels == null || !channels.isEmpty()) {
            Iterator<T> it2 = channels.iterator();
            while (it2.hasNext()) {
                if (hashSetA12.contains(((IptvChannel) it2.next()).getId())) {
                    return kotlin.collections.x.I0(Collections.singletonList(FAVORITES_GROUP_NAME), listI0);
                }
            }
        }
        return listI0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final LinkedHashSet<String> buildPriorityEpgChannelIds(TvUiState tvUiState, int i10) {
        if (i10 <= 0 || tvUiState.getChannelsByGroup().isEmpty()) {
            return new LinkedHashSet<>();
        }
        List<String> listBuildStartupWarmGroups = buildStartupWarmGroups(tvUiState);
        LinkedHashSet<String> linkedHashSet = new LinkedHashSet<>(i10);
        int i11 = 0;
        for (Object obj : listBuildStartupWarmGroups) {
            int i12 = i11 + 1;
            if (i11 < 0) {
                t7.a.Q();
                throw null;
            }
            String str = (String) obj;
            if (linkedHashSet.size() < i10) {
                int i13 = kotlin.jvm.internal.p.a(str, FAVORITES_GROUP_NAME) ? 520 : i11 == 0 ? MediaError.DetailedErrorCode.DASH_MANIFEST_UNKNOWN : isPriorityStartupGroup(str) ? 280 : MenuKt.InTransitionDuration;
                List<IptvChannel> list = tvUiState.getChannelsByGroup().get(str);
                if (list == null) {
                    list = kotlin.collections.z.f19728i;
                }
                for (IptvChannel iptvChannel : ga.r.S(new ga.p(list, 3), i13)) {
                    if (linkedHashSet.size() < i10) {
                        linkedHashSet.add(iptvChannel.getId());
                    }
                }
            }
            i11 = i12;
        }
        if (linkedHashSet.isEmpty()) {
            Iterator it = ga.r.S(new ga.p(tvUiState.getSnapshot().getChannels(), 3), i10).iterator();
            while (it.hasNext()) {
                linkedHashSet.add(((IptvChannel) it.next()).getId());
            }
        }
        return linkedHashSet;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:25:0x0052  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.util.List<java.lang.String> buildStartupWarmGroups(com.arflix.tv.ui.screens.tv.TvUiState r10) {
        /*
            Method dump skipped, instruction units count: 276
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.screens.tv.TvViewModelKt.buildStartupWarmGroups(com.arflix.tv.ui.screens.tv.TvUiState):java.util.List");
    }

    private static final List<IptvChannel> filterTvChannels(List<IptvChannel> list, String str) {
        if (kotlin.text.o.h0(str)) {
            return list;
        }
        ArrayList arrayList = new ArrayList();
        for (IptvChannel iptvChannel : list) {
            String name = iptvChannel.getName();
            Locale locale = Locale.ROOT;
            String lowerCase = name.toLowerCase(locale);
            String lowerCase2 = iptvChannel.getGroup().toLowerCase(locale);
            int i10 = 0;
            if (kotlin.text.u.P(lowerCase, str, false)) {
                i10 = 100;
            } else if (kotlin.text.o.T(lowerCase, str, false)) {
                i10 = 80;
            } else if (kotlin.text.u.P(lowerCase2, str, false)) {
                i10 = 60;
            } else if (kotlin.text.o.T(lowerCase2, str, false)) {
                i10 = 45;
            }
            x6.x xVar = i10 > 0 ? new x6.x(iptvChannel, Integer.valueOf(i10)) : null;
            if (xVar != null) {
                arrayList.add(xVar);
            }
        }
        List listW0 = kotlin.collections.x.W0(arrayList, new Comparator() { // from class: com.arflix.tv.ui.screens.tv.TvViewModelKt$filterTvChannels$$inlined$sortedByDescending$1
            /* JADX WARN: Multi-variable type inference failed */
            @Override // java.util.Comparator
            public final int compare(T t2, T t10) {
                return r0.e((Integer) ((x6.x) t10).f22609l, (Integer) ((x6.x) t2).f22609l);
            }
        });
        ArrayList arrayList2 = new ArrayList(kotlin.collections.s.U(listW0, 10));
        Iterator it = listW0.iterator();
        while (it.hasNext()) {
            arrayList2.add((IptvChannel) ((x6.x) it.next()).f22608i);
        }
        return arrayList2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final int guideCapableChannelCount(List<IptvChannel> list) {
        String tvgName;
        int i10 = 0;
        if (list == null || !list.isEmpty()) {
            for (IptvChannel iptvChannel : list) {
                String epgId = iptvChannel.getEpgId();
                if ((epgId != null && !kotlin.text.o.h0(epgId)) || ((tvgName = iptvChannel.getTvgName()) != null && !kotlin.text.o.h0(tvgName))) {
                    i10++;
                    if (i10 < 0) {
                        t7.a.P();
                        throw null;
                    }
                }
            }
        }
        Integer numValueOf = i10 > 0 ? Integer.valueOf(i10) : null;
        return numValueOf != null ? numValueOf.intValue() : list.size();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean hasConfiguredEpgSource(IptvConfig iptvConfig) {
        if (!kotlin.text.o.h0(iptvConfig.getEpgUrl()) || !kotlin.text.o.h0(iptvConfig.getStalkerPortalUrl()) || !kotlin.text.o.h0(iptvConfig.getM3uUrl()) || looksLikeXtream(iptvConfig.getM3uUrl())) {
            return true;
        }
        List<IptvPlaylistEntry> playlists = iptvConfig.getPlaylists();
        if (playlists != null && playlists.isEmpty()) {
            return false;
        }
        for (IptvPlaylistEntry iptvPlaylistEntry : playlists) {
            if (iptvPlaylistEntry.getEnabled()) {
                if (!kotlin.text.o.h0(iptvPlaylistEntry.getEpgUrl())) {
                    return true;
                }
                List<String> epgUrls = iptvPlaylistEntry.getEpgUrls();
                if (epgUrls == null || !epgUrls.isEmpty()) {
                    Iterator<T> it = epgUrls.iterator();
                    while (it.hasNext()) {
                        if (!kotlin.text.o.h0((String) it.next())) {
                            return true;
                        }
                    }
                }
                if (!kotlin.text.o.h0(iptvPlaylistEntry.getM3uUrl()) || looksLikeXtream(iptvPlaylistEntry.getM3uUrl()) || looksLikeXtream(iptvPlaylistEntry.getEpgUrl())) {
                    return true;
                }
            }
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean hasNetworkEpgSource(IptvConfig iptvConfig) {
        if (!kotlin.text.o.h0(iptvConfig.getEpgUrl()) || !kotlin.text.o.h0(iptvConfig.getStalkerPortalUrl()) || !kotlin.text.o.h0(iptvConfig.getM3uUrl()) || looksLikeXtream(iptvConfig.getM3uUrl())) {
            return true;
        }
        List<IptvPlaylistEntry> playlists = iptvConfig.getPlaylists();
        if (playlists != null && playlists.isEmpty()) {
            return false;
        }
        for (IptvPlaylistEntry iptvPlaylistEntry : playlists) {
            if (iptvPlaylistEntry.getEnabled()) {
                if (!kotlin.text.o.h0(iptvPlaylistEntry.getEpgUrl())) {
                    return true;
                }
                List<String> epgUrls = iptvPlaylistEntry.getEpgUrls();
                if (epgUrls == null || !epgUrls.isEmpty()) {
                    Iterator<T> it = epgUrls.iterator();
                    while (it.hasNext()) {
                        if (!kotlin.text.o.h0((String) it.next())) {
                            return true;
                        }
                    }
                }
                if (!kotlin.text.o.h0(iptvPlaylistEntry.getM3uUrl()) || looksLikeXtream(iptvPlaylistEntry.getM3uUrl()) || looksLikeXtream(iptvPlaylistEntry.getEpgUrl())) {
                    return true;
                }
            }
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean isLargeIptvList(int i10) {
        return i10 > 10000;
    }

    private static final boolean isNetherlandsGroup(String str) {
        List listH = TvVMRegexes.INSTANCE.getNON_ALPHA_NUM().h(str.toLowerCase(Locale.ROOT));
        ArrayList arrayList = new ArrayList();
        for (Object obj : listH) {
            if (!kotlin.text.o.h0((String) obj)) {
                arrayList.add(obj);
            }
        }
        Set setG1 = kotlin.collections.x.g1(arrayList);
        return setG1.contains("netherlands") || setG1.contains("nederland") || setG1.contains("nl");
    }

    private static final boolean isPriorityStartupGroup(String str) {
        return isNetherlandsGroup(str) || kotlin.text.o.T(str.toLowerCase(Locale.ROOT), "4k", false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean looksLikeStalkerStreamCommand(String str) {
        String string = kotlin.text.o.L0(str).toString();
        return kotlin.text.u.P(string, "ffmpeg", true) || (kotlin.text.u.P(string, DomExceptionUtils.SEPARATOR, false) && !kotlin.text.u.P(string, "//", false)) || kotlin.text.u.P(string, "cmd=", true) || (kotlin.text.o.T(string, "type=itv", true) && kotlin.text.o.T(string, "create_link", true));
    }

    private static final boolean looksLikeXtream(String str) {
        return kotlin.text.o.T(str, "player_api.php", true) || kotlin.text.o.T(str, "get.php", true) || kotlin.text.o.T(str, "xmltv.php", true);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final TvUiState setPreparedContent(TvUiState tvUiState) {
        List<String> listBuildPreparedGroups = buildPreparedGroups(tvUiState.getSnapshot());
        Map<String, List<IptvChannel>> mapBuildPreparedChannelsByGroup = buildPreparedChannelsByGroup(tvUiState.getSnapshot(), tvUiState.getQuery(), listBuildPreparedGroups);
        List<IptvChannel> channels = tvUiState.getSnapshot().getChannels();
        int iQ0 = kotlin.collections.i0.q0(kotlin.collections.s.U(channels, 10));
        if (iQ0 < 16) {
            iQ0 = 16;
        }
        LinkedHashMap linkedHashMap = new LinkedHashMap(iQ0);
        for (Object obj : channels) {
            linkedHashMap.put(((IptvChannel) obj).getId(), obj);
        }
        return TvUiState.copy$default(tvUiState, false, null, null, 0, null, null, linkedHashMap, listBuildPreparedGroups, mapBuildPreparedChannelsByGroup, null, false, false, false, null, null, null, false, 130623, null);
    }

    public static final String syncSignature(IptvConfig iptvConfig) {
        return kotlin.collections.x.u0(t7.a.E(iptvConfig.getM3uUrl(), iptvConfig.getEpgUrl(), iptvConfig.getStalkerPortalUrl(), iptvConfig.getStalkerMacAddress(), kotlin.collections.x.u0(iptvConfig.getPlaylists(), "|", null, null, new j0(23), 30)), "||", null, null, null, 62);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final CharSequence syncSignature$lambda$0(IptvPlaylistEntry iptvPlaylistEntry) {
        return kotlin.collections.x.u0(t7.a.E(iptvPlaylistEntry.getId(), iptvPlaylistEntry.getName(), iptvPlaylistEntry.getM3uUrl(), iptvPlaylistEntry.getEpgUrl(), kotlin.collections.x.u0(iptvPlaylistEntry.getEpgUrls(), ",", null, null, null, 62), String.valueOf(iptvPlaylistEntry.getEnabled())), "~", null, null, null, 62);
    }
}
