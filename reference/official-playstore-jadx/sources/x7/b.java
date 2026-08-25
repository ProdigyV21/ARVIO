package x7;

import java.util.Iterator;
import java.util.NoSuchElementException;
import kotlin.jvm.internal.p;

/* JADX INFO: loaded from: classes5.dex */
public final class b implements Iterator, s7.a {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final int f22613i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final int f22614l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public boolean f22615m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public int f22616n;

    public b(char c10, char c11, int i10) {
        this.f22613i = i10;
        this.f22614l = c11;
        boolean z = false;
        if (i10 <= 0 ? p.c(c10, c11) >= 0 : p.c(c10, c11) <= 0) {
            z = true;
        }
        this.f22615m = z;
        this.f22616n = z ? c10 : c11;
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        return this.f22615m;
    }

    @Override // java.util.Iterator
    public final Object next() {
        int i10 = this.f22616n;
        if (i10 != this.f22614l) {
            this.f22616n = this.f22613i + i10;
        } else {
            if (!this.f22615m) {
                throw new NoSuchElementException();
            }
            this.f22615m = false;
        }
        return Character.valueOf((char) i10);
    }

    @Override // java.util.Iterator
    public final void remove() {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }
}
