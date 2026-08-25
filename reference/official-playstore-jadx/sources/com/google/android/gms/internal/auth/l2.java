package com.google.android.gms.internal.auth;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
public final class l2 extends n2 {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final Class f13064c = Collections.unmodifiableList(Collections.EMPTY_LIST).getClass();

    @Override // com.google.android.gms.internal.auth.n2
    public final void a(long j10, Object obj) {
        Object objUnmodifiableList;
        List list = (List) u3.d(j10, obj);
        if (list instanceof k2) {
            objUnmodifiableList = ((k2) list).zze();
        } else {
            if (f13064c.isAssignableFrom(list.getClass())) {
                return;
            }
            if ((list instanceof e3) && (list instanceof g2)) {
                g2 g2Var = (g2) list;
                if (g2Var.zzc()) {
                    g2Var.zzb();
                    return;
                }
                return;
            }
            objUnmodifiableList = Collections.unmodifiableList(list);
        }
        u3.j(obj, objUnmodifiableList, j10);
    }

    @Override // com.google.android.gms.internal.auth.n2
    public final void b(Object obj, Object obj2, long j10) {
        List list;
        List list2;
        List list3 = (List) u3.d(j10, obj2);
        int size = list3.size();
        List list4 = (List) u3.d(j10, obj);
        if (list4.isEmpty()) {
            List j2Var = list4 instanceof k2 ? new j2(size) : ((list4 instanceof e3) && (list4 instanceof g2)) ? ((g2) list4).zzd(size) : new ArrayList(size);
            u3.j(obj, j2Var, j10);
            list2 = j2Var;
        } else {
            if (f13064c.isAssignableFrom(list4.getClass())) {
                ArrayList arrayList = new ArrayList(list4.size() + size);
                arrayList.addAll(list4);
                u3.j(obj, arrayList, j10);
                list = arrayList;
            } else if (list4 instanceof p3) {
                j2 j2Var2 = new j2(list4.size() + size);
                j2Var2.addAll(j2Var2.f13051l.size(), (p3) list4);
                u3.j(obj, j2Var2, j10);
                list = j2Var2;
            } else {
                boolean z = list4 instanceof e3;
                list2 = list4;
                if (z) {
                    boolean z5 = list4 instanceof g2;
                    list2 = list4;
                    if (z5) {
                        g2 g2Var = (g2) list4;
                        list2 = list4;
                        if (!g2Var.zzc()) {
                            g2 g2VarZzd = g2Var.zzd(list4.size() + size);
                            u3.j(obj, g2VarZzd, j10);
                            list2 = g2VarZzd;
                        }
                    }
                }
            }
            list2 = list;
        }
        int size2 = list2.size();
        int size3 = list3.size();
        if (size2 > 0 && size3 > 0) {
            list2.addAll(list3);
        }
        if (size2 > 0) {
            list3 = list2;
        }
        u3.j(obj, list3, j10);
    }
}
