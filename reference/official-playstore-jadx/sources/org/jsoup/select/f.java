package org.jsoup.select;

import java.util.IdentityHashMap;
import java.util.function.Supplier;
import org.jsoup.select.StructuralEvaluator;

/* JADX INFO: loaded from: classes5.dex */
public final /* synthetic */ class f extends ThreadLocal {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f21019a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ Supplier f21020b;

    public /* synthetic */ f(Supplier supplier, int i10) {
        this.f21019a = i10;
        this.f21020b = supplier;
    }

    @Override // java.lang.ThreadLocal
    public final Object initialValue() {
        switch (this.f21019a) {
            case 0:
                ((e) this.f21020b).getClass();
                return StructuralEvaluator.Has.lambda$static$0();
            default:
                ((e) this.f21020b).getClass();
                return new IdentityHashMap();
        }
    }
}
