package org.jsoup.select;

import java.util.IdentityHashMap;
import java.util.function.Supplier;
import org.jsoup.select.StructuralEvaluator;

/* JADX INFO: loaded from: classes5.dex */
public final /* synthetic */ class e implements Supplier {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f21018a;

    public /* synthetic */ e(int i10) {
        this.f21018a = i10;
    }

    @Override // java.util.function.Supplier
    public final Object get() {
        switch (this.f21018a) {
            case 0:
                return StructuralEvaluator.Has.lambda$static$0();
            case 1:
                return new Elements();
            default:
                return new IdentityHashMap();
        }
    }
}
