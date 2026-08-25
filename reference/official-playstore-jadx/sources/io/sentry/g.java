package io.sentry;

import java.util.Iterator;
import java.util.NoSuchElementException;

/* JADX INFO: loaded from: classes4.dex */
public final class g implements Iterator {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public int f17288i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public int f17289l = -1;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public boolean f17290m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ h f17291n;

    public g(h hVar) {
        this.f17291n = hVar;
        this.f17288i = hVar.f17309l;
        this.f17290m = hVar.f17311n;
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        return this.f17290m || this.f17288i != this.f17291n.f17310m;
    }

    @Override // java.util.Iterator
    public final Object next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        this.f17290m = false;
        int i10 = this.f17288i;
        this.f17289l = i10;
        int i11 = i10 + 1;
        h hVar = this.f17291n;
        this.f17288i = i11 < hVar.f17312o ? i11 : 0;
        return hVar.f17308i[i10];
    }

    @Override // java.util.Iterator
    public final void remove() {
        int i10;
        h hVar = this.f17291n;
        int i11 = hVar.f17312o;
        Object[] objArr = hVar.f17308i;
        int i12 = this.f17289l;
        if (i12 == -1) {
            throw new IllegalStateException();
        }
        int i13 = hVar.f17309l;
        if (i12 == i13) {
            hVar.remove();
            this.f17289l = -1;
            return;
        }
        int i14 = i12 + 1;
        if (i13 >= i12 || i14 >= (i10 = hVar.f17310m)) {
            while (i14 != hVar.f17310m) {
                if (i14 >= i11) {
                    objArr[i14 - 1] = objArr[0];
                } else {
                    int i15 = i14 - 1;
                    if (i15 < 0) {
                        i15 = i11 - 1;
                    }
                    objArr[i15] = objArr[i14];
                    i14++;
                    if (i14 >= i11) {
                    }
                }
                i14 = 0;
            }
        } else {
            System.arraycopy(objArr, i14, objArr, i12, i10 - i14);
        }
        this.f17289l = -1;
        int i16 = hVar.f17310m - 1;
        if (i16 < 0) {
            i16 = i11 - 1;
        }
        hVar.f17310m = i16;
        objArr[i16] = null;
        hVar.f17311n = false;
        int i17 = this.f17288i - 1;
        if (i17 < 0) {
            i17 = i11 - 1;
        }
        this.f17288i = i17;
    }
}
