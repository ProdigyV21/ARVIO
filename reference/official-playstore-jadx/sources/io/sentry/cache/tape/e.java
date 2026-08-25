package io.sentry.cache.tape;

import java.io.IOException;
import java.nio.channels.FileChannel;
import java.util.Iterator;

/* JADX INFO: loaded from: classes5.dex */
public final class e extends g {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final j f17166i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final c f17167l = new c();

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final f f17168m;

    public e(j jVar, f fVar) {
        this.f17166i = jVar;
        this.f17168m = fVar;
    }

    @Override // io.sentry.cache.tape.g
    public final void G(int i10) {
        this.f17166i.N0(i10);
    }

    @Override // io.sentry.cache.tape.g
    public final void clear() throws IOException {
        this.f17166i.clear();
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() throws IOException {
        this.f17166i.close();
    }

    @Override // java.lang.Iterable
    public final Iterator iterator() {
        j jVar = this.f17166i;
        jVar.getClass();
        return new d(this, new i(jVar));
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // io.sentry.cache.tape.g
    public final void k(Object obj) throws IOException {
        long j10;
        char c10;
        long j11;
        long j12;
        long j13;
        long j14;
        c cVar = this.f17167l;
        cVar.reset();
        this.f17168m.e(obj, cVar);
        byte[] bArrI = cVar.i();
        int size = cVar.size();
        j jVar = this.f17166i;
        jVar.getClass();
        byte[] bArr = jVar.f17183q;
        if (bArrI == null) {
            throw new NullPointerException("data == null");
        }
        if (size < 0 || size > bArrI.length) {
            throw new IndexOutOfBoundsException();
        }
        if (jVar.f17186t) {
            throw new IllegalStateException("closed");
        }
        int i10 = jVar.f17185s;
        if (i10 != -1 && jVar.f17180n == i10) {
            jVar.N0(1);
        }
        long j15 = ((long) size) + 4;
        long j16 = jVar.f17179m;
        long jR0 = 32;
        if (jVar.f17180n == 0) {
            c10 = 1;
            j10 = 4;
            j11 = 32;
        } else {
            h hVar = jVar.f17182p;
            long j17 = hVar.f17170a;
            int i11 = hVar.f17171b;
            j10 = 4;
            long j18 = jVar.f17181o.f17170a;
            if (j17 >= j18) {
                j11 = (j17 - j18) + 4 + ((long) i11) + 32;
                c10 = 1;
            } else {
                c10 = 1;
                j11 = (((j17 + 4) + ((long) i11)) + j16) - j18;
            }
        }
        long j19 = j16 - j11;
        if (j19 < j15) {
            do {
                j19 += j16;
                j16 <<= c10;
            } while (j19 < j15);
            jVar.f17177i.setLength(j16);
            jVar.f17177i.getChannel().force(c10);
            h hVar2 = jVar.f17182p;
            long jR02 = jVar.R0(hVar2.f17170a + j10 + ((long) hVar2.f17171b));
            if (jR02 <= jVar.f17181o.f17170a) {
                FileChannel channel = jVar.f17177i.getChannel();
                channel.position(jVar.f17179m);
                j12 = jR02 - 32;
                if (channel.transferTo(32L, j12, channel) != j12) {
                    throw new AssertionError("Copied insufficient number of bytes!");
                }
            } else {
                j12 = 0;
            }
            long j20 = jVar.f17182p.f17170a;
            long j21 = jVar.f17181o.f17170a;
            if (j20 < j21) {
                j14 = 0;
                long j22 = (jVar.f17179m + j20) - 32;
                j13 = j16;
                jVar.S0(jVar.f17180n, j13, j21, j22);
                jVar.f17182p = new h(j22, jVar.f17182p.f17171b);
            } else {
                j13 = j16;
                j14 = 0;
                jVar.S0(jVar.f17180n, j13, j21, j20);
            }
            jVar.f17179m = j13;
            long j23 = 32;
            long j24 = j12;
            while (j24 > j14) {
                int iMin = (int) Math.min(j24, 4096);
                jVar.Q0(j.f17176u, iMin, j23);
                long j25 = iMin;
                j24 -= j25;
                j23 += j25;
            }
        }
        boolean z = jVar.f17180n == 0;
        if (!z) {
            h hVar3 = jVar.f17182p;
            jR0 = jVar.R0(hVar3.f17170a + j10 + ((long) hVar3.f17171b));
        }
        long j26 = jR0;
        h hVar4 = new h(j26, size);
        j.T0(bArr, 0, size);
        jVar.Q0(bArr, 4, j26);
        jVar.Q0(bArrI, size, j26 + j10);
        jVar.S0(jVar.f17180n + 1, jVar.f17179m, z ? j26 : jVar.f17181o.f17170a, j26);
        jVar.f17182p = hVar4;
        jVar.f17180n++;
        jVar.f17184r++;
        if (z) {
            jVar.f17181o = hVar4;
        }
    }

    @Override // io.sentry.cache.tape.g
    public final int size() {
        return this.f17166i.f17180n;
    }

    public final String toString() {
        return "FileObjectQueue{queueFile=" + this.f17166i + '}';
    }
}
