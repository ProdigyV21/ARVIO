package j$.util;

import java.io.Serializable;
import java.util.function.Function;

/* JADX INFO: loaded from: classes2.dex */
public final /* synthetic */ class d implements java.util.Comparator, Serializable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f18529a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ java.util.Comparator f18530b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final /* synthetic */ Object f18531c;

    public /* synthetic */ d(java.util.Comparator comparator, Object obj, int i10) {
        this.f18529a = i10;
        this.f18530b = comparator;
        this.f18531c = obj;
    }

    @Override // java.util.Comparator
    public final int compare(Object obj, Object obj2) {
        switch (this.f18529a) {
            case 0:
                java.util.Comparator comparator = this.f18530b;
                java.util.Comparator comparator2 = (java.util.Comparator) this.f18531c;
                int iCompare = comparator.compare(obj, obj2);
                return iCompare != 0 ? iCompare : comparator2.compare(obj, obj2);
            default:
                java.util.Comparator comparator3 = this.f18530b;
                Function function = (Function) this.f18531c;
                return comparator3.compare(function.apply(obj), function.apply(obj2));
        }
    }
}
