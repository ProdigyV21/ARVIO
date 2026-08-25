package com.google.common.collect;

import java.io.Serializable;

/* JADX INFO: loaded from: classes4.dex */
public final class d3 extends g3 implements Serializable {

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public static final d3 f13967l = new d3(0);

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public static final d3 f13968m = new d3(1);

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f13969i;

    public /* synthetic */ d3(int i10) {
        this.f13969i = i10;
    }

    @Override // com.google.common.collect.g3
    public final g3 a() {
        switch (this.f13969i) {
            case 0:
                return f13968m;
            default:
                return f13967l;
        }
    }

    @Override // java.util.Comparator
    public final int compare(Object obj, Object obj2) {
        switch (this.f13969i) {
            case 0:
                Comparable comparable = (Comparable) obj;
                Comparable comparable2 = (Comparable) obj2;
                comparable.getClass();
                comparable2.getClass();
                return comparable.compareTo(comparable2);
            default:
                Comparable comparable3 = (Comparable) obj;
                Comparable comparable4 = (Comparable) obj2;
                comparable3.getClass();
                if (comparable3 == comparable4) {
                    return 0;
                }
                return comparable4.compareTo(comparable3);
        }
    }

    public final String toString() {
        switch (this.f13969i) {
            case 0:
                return "Ordering.natural()";
            default:
                return "Ordering.natural().reverse()";
        }
    }
}
