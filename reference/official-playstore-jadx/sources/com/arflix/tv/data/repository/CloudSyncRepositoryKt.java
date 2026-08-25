package com.arflix.tv.data.repository;

import com.arflix.tv.data.model.Addon;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\u001aQ\u0010\t\u001a\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00010\u0000\u0012\u0004\u0012\u00020\b0\u00072\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00010\u00002\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00010\u00002\b\b\u0002\u0010\u0005\u001a\u00020\u00042\b\b\u0002\u0010\u0006\u001a\u00020\u0004H\u0000¢\u0006\u0004\b\t\u0010\n¨\u0006\u000b"}, d2 = {"", "Lcom/arflix/tv/data/model/Addon;", "cloudAddons", "localAddons", "", "cloudAddonsUpdatedAt", "localAddonsUpdatedAt", "Lx6/x;", "", "reconcileAddonsWithCloud", "(Ljava/util/List;Ljava/util/List;JJ)Lx6/x;", "app_playRelease"}, k = 2, mv = {2, 3, 0}, xi = 48)
public final class CloudSyncRepositoryKt {
    public static final x6.x reconcileAddonsWithCloud(List<Addon> list, List<Addon> list2, long j10, long j11) {
        ArrayList<Addon> arrayList = new ArrayList();
        for (Object obj : list) {
            if (!kotlin.text.o.h0(kotlin.text.o.L0(((Addon) obj).getId()).toString())) {
                arrayList.add(obj);
            }
        }
        if (arrayList.isEmpty()) {
            if (j10 <= j11) {
                return new x6.x(list2, Boolean.FALSE);
            }
            return new x6.x(kotlin.collections.z.f19728i, Boolean.FALSE);
        }
        if (j11 > j10) {
            return new x6.x(list2, Boolean.FALSE);
        }
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (Addon addon : arrayList) {
            linkedHashMap.put(kotlin.text.o.L0(addon.getId()).toString(), addon);
        }
        return new x6.x(kotlin.collections.x.c1(linkedHashMap.values()), Boolean.FALSE);
    }

    public static /* synthetic */ x6.x reconcileAddonsWithCloud$default(List list, List list2, long j10, long j11, int i10, Object obj) {
        if ((i10 & 4) != 0) {
            j10 = 0;
        }
        if ((i10 & 8) != 0) {
            j11 = 0;
        }
        return reconcileAddonsWithCloud(list, list2, j10, j11);
    }
}
