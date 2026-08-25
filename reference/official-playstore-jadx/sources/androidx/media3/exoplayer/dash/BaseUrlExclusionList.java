package androidx.media3.exoplayer.dash;

import android.os.SystemClock;
import android.util.Pair;
import androidx.media3.common.util.Util;
import androidx.media3.exoplayer.dash.manifest.BaseUrl;
import com.google.common.collect.a0;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;

/* JADX INFO: loaded from: classes3.dex */
public final class BaseUrlExclusionList {
    private final Map<Integer, Long> excludedPriorities;
    private final Map<String, Long> excludedServiceLocations;
    private final Random random;
    private final Map<List<Pair<String, Integer>>, BaseUrl> selectionsTaken;

    public BaseUrlExclusionList() {
        this(new Random());
    }

    private static <T> void addExclusion(T t2, long j10, Map<T, Long> map) {
        if (map.containsKey(t2)) {
            j10 = Math.max(j10, ((Long) Util.castNonNull(map.get(t2))).longValue());
        }
        map.put(t2, Long.valueOf(j10));
    }

    private List<BaseUrl> applyExclusions(List<BaseUrl> list) {
        long jElapsedRealtime = SystemClock.elapsedRealtime();
        removeExpiredExclusions(jElapsedRealtime, this.excludedServiceLocations);
        removeExpiredExclusions(jElapsedRealtime, this.excludedPriorities);
        ArrayList arrayList = new ArrayList();
        for (int i10 = 0; i10 < list.size(); i10++) {
            BaseUrl baseUrl = list.get(i10);
            if (!this.excludedServiceLocations.containsKey(baseUrl.serviceLocation) && !this.excludedPriorities.containsKey(Integer.valueOf(baseUrl.priority))) {
                arrayList.add(baseUrl);
            }
        }
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static int compareBaseUrl(BaseUrl baseUrl, BaseUrl baseUrl2) {
        int iCompare = Integer.compare(baseUrl.priority, baseUrl2.priority);
        return iCompare != 0 ? iCompare : baseUrl.serviceLocation.compareTo(baseUrl2.serviceLocation);
    }

    public static int getPriorityCount(List<BaseUrl> list) {
        HashSet hashSet = new HashSet();
        for (int i10 = 0; i10 < list.size(); i10++) {
            hashSet.add(Integer.valueOf(list.get(i10).priority));
        }
        return hashSet.size();
    }

    private static <T> void removeExpiredExclusions(long j10, Map<T, Long> map) {
        ArrayList arrayList = new ArrayList();
        for (Map.Entry<T, Long> entry : map.entrySet()) {
            if (entry.getValue().longValue() <= j10) {
                arrayList.add(entry.getKey());
            }
        }
        for (int i10 = 0; i10 < arrayList.size(); i10++) {
            map.remove(arrayList.get(i10));
        }
    }

    private BaseUrl selectWeighted(List<BaseUrl> list) {
        int i10 = 0;
        for (int i11 = 0; i11 < list.size(); i11++) {
            i10 += list.get(i11).weight;
        }
        int iNextInt = this.random.nextInt(i10);
        int i12 = 0;
        for (int i13 = 0; i13 < list.size(); i13++) {
            BaseUrl baseUrl = list.get(i13);
            i12 += baseUrl.weight;
            if (iNextInt < i12) {
                return baseUrl;
            }
        }
        return (BaseUrl) a0.j(list);
    }

    public void exclude(BaseUrl baseUrl, long j10) {
        long jElapsedRealtime = SystemClock.elapsedRealtime() + j10;
        addExclusion(baseUrl.serviceLocation, jElapsedRealtime, this.excludedServiceLocations);
        int i10 = baseUrl.priority;
        if (i10 != Integer.MIN_VALUE) {
            addExclusion(Integer.valueOf(i10), jElapsedRealtime, this.excludedPriorities);
        }
    }

    public int getPriorityCountAfterExclusion(List<BaseUrl> list) {
        HashSet hashSet = new HashSet();
        List<BaseUrl> listApplyExclusions = applyExclusions(list);
        for (int i10 = 0; i10 < listApplyExclusions.size(); i10++) {
            hashSet.add(Integer.valueOf(listApplyExclusions.get(i10).priority));
        }
        return hashSet.size();
    }

    public void reset() {
        this.excludedServiceLocations.clear();
        this.excludedPriorities.clear();
        this.selectionsTaken.clear();
    }

    public BaseUrl selectBaseUrl(List<BaseUrl> list) {
        List<BaseUrl> listApplyExclusions = applyExclusions(list);
        if (listApplyExclusions.size() < 2) {
            return (BaseUrl) a0.i(null, listApplyExclusions);
        }
        Collections.sort(listApplyExclusions, new a());
        ArrayList arrayList = new ArrayList();
        int i10 = listApplyExclusions.get(0).priority;
        int i11 = 0;
        while (true) {
            if (i11 >= listApplyExclusions.size()) {
                break;
            }
            BaseUrl baseUrl = listApplyExclusions.get(i11);
            if (i10 == baseUrl.priority) {
                arrayList.add(new Pair(baseUrl.serviceLocation, Integer.valueOf(baseUrl.weight)));
                i11++;
            } else if (arrayList.size() == 1) {
                return listApplyExclusions.get(0);
            }
        }
        BaseUrl baseUrl2 = this.selectionsTaken.get(arrayList);
        if (baseUrl2 != null) {
            return baseUrl2;
        }
        BaseUrl baseUrlSelectWeighted = selectWeighted(listApplyExclusions.subList(0, arrayList.size()));
        this.selectionsTaken.put(arrayList, baseUrlSelectWeighted);
        return baseUrlSelectWeighted;
    }

    public BaseUrlExclusionList(Random random) {
        this.selectionsTaken = new HashMap();
        this.random = random;
        this.excludedServiceLocations = new HashMap();
        this.excludedPriorities = new HashMap();
    }
}
