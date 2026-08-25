package ba;

import java.util.Arrays;
import java.util.Iterator;

/* JADX INFO: loaded from: classes5.dex */
public final class c extends a {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public Object[] f7272i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public int f7273l;

    @Override // ba.a
    public final int a() {
        return this.f7273l;
    }

    @Override // ba.a
    public final void b(int i10, v9.h hVar) {
        Object[] objArr = this.f7272i;
        if (objArr.length <= i10) {
            int length = objArr.length;
            do {
                length *= 2;
            } while (length <= i10);
            this.f7272i = Arrays.copyOf(this.f7272i, length);
        }
        Object[] objArr2 = this.f7272i;
        if (objArr2[i10] == null) {
            this.f7273l++;
        }
        objArr2[i10] = hVar;
    }

    @Override // ba.a
    public final Object get(int i10) {
        return kotlin.collections.r.c0(i10, this.f7272i);
    }

    @Override // ba.a, java.lang.Iterable
    public final Iterator iterator() {
        return new b(this);
    }
}
