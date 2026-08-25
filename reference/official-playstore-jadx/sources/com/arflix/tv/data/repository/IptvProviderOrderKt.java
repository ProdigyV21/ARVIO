package com.arflix.tv.data.repository;

import com.arflix.tv.data.model.IptvChannel;
import com.arflix.tv.data.model.PlaylistGroupKey;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.q0;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\"\n\u0002\b\u0007\u001a=\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00040\u00002\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00010\u00002\u0018\u0010\u0005\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00040\u00030\u0000H\u0000¢\u0006\u0004\b\u0006\u0010\u0007\u001a1\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00010\u000b2\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\b0\u00002\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\b0\u0000H\u0000¢\u0006\u0004\b\f\u0010\r\u001a1\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00010\u00002\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00010\u00002\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00010\u000bH\u0000¢\u0006\u0004\b\u0010\u0010\u0011¨\u0006\u0012"}, d2 = {"", "", "categoryIdsInProviderOrder", "Lx6/x;", "Lcom/arflix/tv/data/model/IptvChannel;", "categorizedChannels", "orderXtreamChannelsByProviderCategories", "(Ljava/util/List;Ljava/util/List;)Ljava/util/List;", "Lcom/arflix/tv/data/repository/IptvPlaylistEntry;", "previous", "current", "", "changedPlaylistSourceIds", "(Ljava/util/List;Ljava/util/List;)Ljava/util/Set;", "savedOrder", "changedPlaylistIds", "retainGroupOrderForUnchangedSources", "(Ljava/util/List;Ljava/util/Set;)Ljava/util/List;", "app_playRelease"}, k = 2, mv = {2, 3, 0}, xi = 48)
public final class IptvProviderOrderKt {
    public static final Set<String> changedPlaylistSourceIds(List<IptvPlaylistEntry> list, List<IptvPlaylistEntry> list2) {
        int iQ0 = kotlin.collections.i0.q0(kotlin.collections.s.U(list, 10));
        if (iQ0 < 16) {
            iQ0 = 16;
        }
        LinkedHashMap linkedHashMap = new LinkedHashMap(iQ0);
        for (Object obj : list) {
            linkedHashMap.put(kotlin.text.o.L0(((IptvPlaylistEntry) obj).getId()).toString(), obj);
        }
        int iQ02 = kotlin.collections.i0.q0(kotlin.collections.s.U(list2, 10));
        LinkedHashMap linkedHashMap2 = new LinkedHashMap(iQ02 >= 16 ? iQ02 : 16);
        for (Object obj2 : list2) {
            linkedHashMap2.put(kotlin.text.o.L0(((IptvPlaylistEntry) obj2).getId()).toString(), obj2);
        }
        return ga.r.W(ga.r.I(ga.r.I(new ga.p(q0.b0(linkedHashMap.keySet(), linkedHashMap2.keySet()), 3), new h(8)), new a(linkedHashMap, linkedHashMap2, 2)));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean changedPlaylistSourceIds$lambda$2(String str) {
        return !kotlin.text.o.h0(str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean changedPlaylistSourceIds$lambda$3(Map map, Map map2, String str) {
        String m3uUrl;
        String m3uUrl2;
        IptvPlaylistEntry iptvPlaylistEntry = (IptvPlaylistEntry) map.get(str);
        String string = null;
        String string2 = (iptvPlaylistEntry == null || (m3uUrl2 = iptvPlaylistEntry.getM3uUrl()) == null) ? null : kotlin.text.o.L0(m3uUrl2).toString();
        IptvPlaylistEntry iptvPlaylistEntry2 = (IptvPlaylistEntry) map2.get(str);
        if (iptvPlaylistEntry2 != null && (m3uUrl = iptvPlaylistEntry2.getM3uUrl()) != null) {
            string = kotlin.text.o.L0(m3uUrl).toString();
        }
        return !kotlin.jvm.internal.p.a(string2, string);
    }

    public static final List<IptvChannel> orderXtreamChannelsByProviderCategories(List<String> list, List<x6.x> list2) {
        if (list2.isEmpty()) {
            return kotlin.collections.z.f19728i;
        }
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (x6.x xVar : list2) {
            String str = (String) xVar.f22608i;
            IptvChannel iptvChannel = (IptvChannel) xVar.f22609l;
            String string = kotlin.text.o.L0(str).toString();
            Object objT = linkedHashMap.get(string);
            if (objT == null) {
                objT = androidx.compose.material3.d.t(linkedHashMap, string);
            }
            ((List) objT).add(iptvChannel);
        }
        ArrayList arrayList = new ArrayList(list2.size());
        Iterator it = ga.r.G(ga.r.I(new ga.t(new ga.p(list, 3), new h(6)), new h(7))).iterator();
        while (true) {
            kotlin.collections.b bVar = (kotlin.collections.b) it;
            if (!bVar.hasNext()) {
                break;
            }
            List list3 = (List) linkedHashMap.remove((String) bVar.next());
            if (list3 != null) {
                arrayList.addAll(list3);
            }
        }
        Iterator it2 = linkedHashMap.values().iterator();
        while (it2.hasNext()) {
            arrayList.addAll((Collection) it2.next());
        }
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final String orderXtreamChannelsByProviderCategories$lambda$1(String str) {
        return kotlin.text.o.L0(str).toString();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean orderXtreamChannelsByProviderCategories$lambda$2(String str) {
        return !kotlin.text.o.h0(str);
    }

    public static final List<String> retainGroupOrderForUnchangedSources(List<String> list, Set<String> set) {
        if (list.isEmpty() || set.isEmpty()) {
            return list;
        }
        ArrayList arrayList = new ArrayList();
        for (Object obj : list) {
            if (!set.contains(PlaylistGroupKey.m6072getPlaylistIdimpl(PlaylistGroupKey.m6068constructorimpl(kotlin.text.o.L0((String) obj).toString())))) {
                arrayList.add(obj);
            }
        }
        return arrayList;
    }
}
