package g8;

import java.util.ArrayList;
import java.util.Map;

/* JADX INFO: loaded from: classes5.dex */
public final class c0 extends c1 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final ArrayList f15495a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Map f15496b;

    public c0(ArrayList arrayList) {
        this.f15495a = arrayList;
        Map mapA0 = kotlin.collections.h0.A0(arrayList);
        if (mapA0.size() != arrayList.size()) {
            throw new IllegalArgumentException("Some properties have the same names");
        }
        this.f15496b = mapA0;
    }

    @Override // g8.c1
    public final boolean a(f9.f fVar) {
        return this.f15496b.containsKey(fVar);
    }

    public final String toString() {
        return "MultiFieldValueClassRepresentation(underlyingPropertyNamesToTypes=" + this.f15495a + ')';
    }
}
