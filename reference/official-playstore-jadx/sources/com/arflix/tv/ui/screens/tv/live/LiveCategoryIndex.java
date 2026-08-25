package com.arflix.tv.ui.screens.tv.live;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\"\n\u0002\b\n\n\u0002\u0010\u001e\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\b\u0087\b\u0018\u0000  2\u00020\u0001:\u0001 BE\u0012\u0018\u0010\u0002\u001a\u0014\u0012\u0004\u0012\u00020\u0004\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u00050\u0003\u0012\u0012\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00060\u0003\u0012\u000e\b\u0002\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00040\t¢\u0006\u0004\b\n\u0010\u000bJ0\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\u0006\u0010\u0012\u001a\u00020\u00042\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00040\u00142\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00040\u0014J\u001b\u0010\u0016\u001a\u0014\u0012\u0004\u0012\u00020\u0004\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u00050\u0003HÆ\u0003J\u0015\u0010\u0017\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00060\u0003HÆ\u0003J\u000f\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00040\tHÆ\u0003JK\u0010\u0019\u001a\u00020\u00002\u001a\b\u0002\u0010\u0002\u001a\u0014\u0012\u0004\u0012\u00020\u0004\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u00050\u00032\u0014\b\u0002\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00060\u00032\u000e\b\u0002\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00040\tHÆ\u0001J\u0014\u0010\u001a\u001a\u00020\u001b2\b\u0010\u001c\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u001d\u001a\u00020\u001eHÖ\u0081\u0004J\n\u0010\u001f\u001a\u00020\u0004HÖ\u0081\u0004R#\u0010\u0002\u001a\u0014\u0012\u0004\u0012\u00020\u0004\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u00050\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u001d\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00060\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\rR\u0017\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00040\t¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010¨\u0006!"}, d2 = {"Lcom/arflix/tv/ui/screens/tv/live/LiveCategoryIndex;", "", "byCategory", "", "", "", "Lcom/arflix/tv/ui/screens/tv/live/EnrichedChannel;", "byId", "hiddenIds", "", "<init>", "(Ljava/util/Map;Ljava/util/Map;Ljava/util/Set;)V", "getByCategory", "()Ljava/util/Map;", "getById", "getHiddenIds", "()Ljava/util/Set;", "channelsFor", "categoryId", "favorites", "", "recents", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "", "toString", "Companion", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final /* data */ class LiveCategoryIndex {
    public static final int $stable = 0;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final LiveCategoryIndex Empty;
    private final Map<String, List<EnrichedChannel>> byCategory;
    private final Map<String, EnrichedChannel> byId;
    private final Set<String> hiddenIds;

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Lcom/arflix/tv/ui/screens/tv/live/LiveCategoryIndex$Companion;", "", "<init>", "()V", "Empty", "Lcom/arflix/tv/ui/screens/tv/live/LiveCategoryIndex;", "getEmpty", "()Lcom/arflix/tv/ui/screens/tv/live/LiveCategoryIndex;", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(kotlin.jvm.internal.h hVar) {
            this();
        }

        public final LiveCategoryIndex getEmpty() {
            return LiveCategoryIndex.Empty;
        }

        private Companion() {
        }
    }

    static {
        kotlin.collections.a0 a0Var = kotlin.collections.a0.f19683i;
        Empty = new LiveCategoryIndex(a0Var, a0Var, null, 4, null);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public LiveCategoryIndex(Map<String, ? extends List<EnrichedChannel>> map, Map<String, EnrichedChannel> map2, Set<String> set) {
        this.byCategory = map;
        this.byId = map2;
        this.hiddenIds = set;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ LiveCategoryIndex copy$default(LiveCategoryIndex liveCategoryIndex, Map map, Map map2, Set set, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            map = liveCategoryIndex.byCategory;
        }
        if ((i10 & 2) != 0) {
            map2 = liveCategoryIndex.byId;
        }
        if ((i10 & 4) != 0) {
            set = liveCategoryIndex.hiddenIds;
        }
        return liveCategoryIndex.copy(map, map2, set);
    }

    public final List<EnrichedChannel> channelsFor(String categoryId, Collection<String> favorites, Collection<String> recents) {
        if (categoryId.equals("fav")) {
            Map<String, EnrichedChannel> map = this.byId;
            ArrayList arrayList = new ArrayList();
            Iterator<T> it = favorites.iterator();
            while (it.hasNext()) {
                EnrichedChannel enrichedChannel = map.get((String) it.next());
                if (enrichedChannel != null) {
                    arrayList.add(enrichedChannel);
                }
            }
            ArrayList arrayList2 = new ArrayList();
            for (Object obj : arrayList) {
                EnrichedChannel enrichedChannel2 = (EnrichedChannel) obj;
                if (!enrichedChannel2.isAdult() && !this.hiddenIds.contains(enrichedChannel2.getId())) {
                    arrayList2.add(obj);
                }
            }
            return arrayList2;
        }
        if (!categoryId.equals("recent")) {
            List<EnrichedChannel> list = this.byCategory.get(categoryId);
            return list == null ? kotlin.collections.z.f19728i : list;
        }
        kotlin.collections.n0 n0Var = new kotlin.collections.n0(kotlin.collections.x.c1(recents), 0);
        Map<String, EnrichedChannel> map2 = this.byId;
        ArrayList arrayList3 = new ArrayList();
        Iterator it2 = n0Var.iterator();
        while (true) {
            ListIterator listIterator = ((kotlin.collections.m0) it2).f19697i;
            if (!listIterator.hasPrevious()) {
                break;
            }
            EnrichedChannel enrichedChannel3 = map2.get((String) listIterator.previous());
            if (enrichedChannel3 != null) {
                arrayList3.add(enrichedChannel3);
            }
        }
        ArrayList arrayList4 = new ArrayList();
        for (Object obj2 : arrayList3) {
            EnrichedChannel enrichedChannel4 = (EnrichedChannel) obj2;
            if (!enrichedChannel4.isAdult() && !this.hiddenIds.contains(enrichedChannel4.getId())) {
                arrayList4.add(obj2);
            }
        }
        return arrayList4;
    }

    public final Map<String, List<EnrichedChannel>> component1() {
        return this.byCategory;
    }

    public final Map<String, EnrichedChannel> component2() {
        return this.byId;
    }

    public final Set<String> component3() {
        return this.hiddenIds;
    }

    public final LiveCategoryIndex copy(Map<String, ? extends List<EnrichedChannel>> byCategory, Map<String, EnrichedChannel> byId, Set<String> hiddenIds) {
        return new LiveCategoryIndex(byCategory, byId, hiddenIds);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof LiveCategoryIndex)) {
            return false;
        }
        LiveCategoryIndex liveCategoryIndex = (LiveCategoryIndex) other;
        return kotlin.jvm.internal.p.a(this.byCategory, liveCategoryIndex.byCategory) && kotlin.jvm.internal.p.a(this.byId, liveCategoryIndex.byId) && kotlin.jvm.internal.p.a(this.hiddenIds, liveCategoryIndex.hiddenIds);
    }

    public final Map<String, List<EnrichedChannel>> getByCategory() {
        return this.byCategory;
    }

    public final Map<String, EnrichedChannel> getById() {
        return this.byId;
    }

    public final Set<String> getHiddenIds() {
        return this.hiddenIds;
    }

    public int hashCode() {
        return this.hiddenIds.hashCode() + androidx.fragment.app.a2.g(this.byId, this.byCategory.hashCode() * 31, 31);
    }

    public String toString() {
        return "LiveCategoryIndex(byCategory=" + this.byCategory + ", byId=" + this.byId + ", hiddenIds=" + this.hiddenIds + ")";
    }

    public /* synthetic */ LiveCategoryIndex(Map map, Map map2, Set set, int i10, kotlin.jvm.internal.h hVar) {
        this(map, map2, (i10 & 4) != 0 ? kotlin.collections.b0.f19686i : set);
    }
}
