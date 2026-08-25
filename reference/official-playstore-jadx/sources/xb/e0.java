package xb;

import java.util.RandomAccess;

/* JADX INFO: loaded from: classes5.dex */
public final class e0 extends kotlin.collections.f implements RandomAccess {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final m[] f22731i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final int[] f22732l;

    public e0(m[] mVarArr, int[] iArr) {
        this.f22731i = mVarArr;
        this.f22732l = iArr;
    }

    @Override // kotlin.collections.a, java.util.Collection, java.util.List
    public final /* bridge */ boolean contains(Object obj) {
        if (obj instanceof m) {
            return super.contains((m) obj);
        }
        return false;
    }

    @Override // kotlin.collections.f, java.util.List
    public final Object get(int i10) {
        return this.f22731i[i10];
    }

    @Override // kotlin.collections.f, kotlin.collections.a
    /* JADX INFO: renamed from: getSize */
    public final int get_size() {
        return this.f22731i.length;
    }

    @Override // kotlin.collections.f, java.util.List
    public final /* bridge */ int indexOf(Object obj) {
        if (obj instanceof m) {
            return super.indexOf((m) obj);
        }
        return -1;
    }

    @Override // kotlin.collections.f, java.util.List
    public final /* bridge */ int lastIndexOf(Object obj) {
        if (obj instanceof m) {
            return super.lastIndexOf((m) obj);
        }
        return -1;
    }
}
