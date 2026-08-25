package ga;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import kotlin.collections.w;

/* JADX INFO: loaded from: classes5.dex */
public final class l implements m {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f15590a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Object f15591b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final Object f15592c;

    public /* synthetic */ l(Object obj, Object obj2, int i10) {
        this.f15590a = i10;
        this.f15591b = obj;
        this.f15592c = obj2;
    }

    @Override // ga.m
    public final Iterator iterator() {
        switch (this.f15590a) {
            case 0:
                return new k(this);
            case 1:
                List listV = r.V((m) this.f15591b);
                w.X((Comparator) this.f15592c, listV);
                return ((ArrayList) listV).iterator();
            default:
                return new k(this, (byte) 0);
        }
    }
}
