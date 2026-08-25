package io.sentry.cache.tape;

import java.io.IOException;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

/* JADX INFO: loaded from: classes5.dex */
public final class i implements Iterator {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public int f17172i = 0;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public long f17173l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public int f17174m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ j f17175n;

    public i(j jVar) {
        this.f17175n = jVar;
        this.f17173l = jVar.f17181o.f17170a;
        this.f17174m = jVar.f17184r;
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        j jVar = this.f17175n;
        if (jVar.f17186t) {
            throw new IllegalStateException("closed");
        }
        if (jVar.f17184r == this.f17174m) {
            return this.f17172i != jVar.f17180n;
        }
        throw new ConcurrentModificationException();
    }

    @Override // java.util.Iterator
    public final Object next() throws IOException {
        byte[] bArr = j.f17176u;
        j jVar = this.f17175n;
        if (jVar.f17186t) {
            throw new IllegalStateException("closed");
        }
        if (jVar.f17184r != this.f17174m) {
            throw new ConcurrentModificationException();
        }
        int i10 = jVar.f17180n;
        if (i10 == 0) {
            throw new NoSuchElementException();
        }
        if (this.f17172i >= i10) {
            throw new NoSuchElementException();
        }
        try {
            h hVarY = jVar.y(this.f17173l);
            int i11 = hVarY.f17171b;
            long j10 = hVarY.f17170a;
            byte[] bArr2 = new byte[i11];
            long j11 = j10 + 4;
            long jR0 = jVar.R0(j11);
            this.f17173l = jR0;
            if (!jVar.P0(bArr2, i11, jR0)) {
                this.f17172i = jVar.f17180n;
                return bArr;
            }
            this.f17173l = jVar.R0(j11 + ((long) i11));
            this.f17172i++;
            return bArr2;
        } catch (IOException e5) {
            throw e5;
        } catch (OutOfMemoryError unused) {
            jVar.O0();
            this.f17172i = jVar.f17180n;
            return bArr;
        }
    }

    @Override // java.util.Iterator
    public final void remove() {
        j jVar = this.f17175n;
        if (jVar.f17184r != this.f17174m) {
            throw new ConcurrentModificationException();
        }
        if (jVar.f17180n == 0) {
            throw new NoSuchElementException();
        }
        if (this.f17172i != 1) {
            throw new UnsupportedOperationException("Removal is only permitted from the head.");
        }
        jVar.N0(1);
        this.f17174m = jVar.f17184r;
        this.f17172i--;
    }
}
