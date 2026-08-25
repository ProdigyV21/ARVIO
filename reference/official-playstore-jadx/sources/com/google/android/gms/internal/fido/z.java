package com.google.android.gms.internal.fido;

import java.io.Serializable;

/* JADX INFO: loaded from: classes4.dex */
public final class z extends a0 implements Serializable {

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public static final z f13850l = new z(0);

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public static final z f13851m = new z(1);

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f13852i;

    public /* synthetic */ z(int i10) {
        this.f13852i = i10;
    }

    @Override // com.google.android.gms.internal.fido.a0
    public final a0 a() {
        switch (this.f13852i) {
            case 0:
                return f13851m;
            default:
                return f13850l;
        }
    }

    @Override // java.util.Comparator
    public final /* bridge */ /* synthetic */ int compare(Object obj, Object obj2) {
        switch (this.f13852i) {
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
        switch (this.f13852i) {
            case 0:
                return "Ordering.natural()";
            default:
                return "Ordering.natural().reverse()";
        }
    }
}
