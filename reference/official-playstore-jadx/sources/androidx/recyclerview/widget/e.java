package androidx.recyclerview.widget;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public final class e implements fi.iki.elonen.a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f4913a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public long f4914b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public Object f4915c;

    public e(xb.l lVar) {
        this.f4913a = 2;
        this.f4915c = lVar;
        this.f4914b = 262144L;
    }

    @Override // fi.iki.elonen.a
    public void a(fi.iki.elonen.b bVar) {
        ((List) this.f4915c).remove(bVar);
    }

    @Override // fi.iki.elonen.a
    public void b() {
        for (fi.iki.elonen.b bVar : new ArrayList((List) this.f4915c)) {
            fi.iki.elonen.t.b(bVar.f15402i);
            fi.iki.elonen.t.b(bVar.f15403l);
        }
    }

    @Override // fi.iki.elonen.a
    public void c(fi.iki.elonen.b bVar) {
        this.f4914b++;
        Thread thread = new Thread(bVar);
        thread.setDaemon(true);
        thread.setName("NanoHttpd Request Processor (#" + this.f4914b + ")");
        ((List) this.f4915c).add(bVar);
        thread.start();
    }

    public void d(int i10) {
        if (i10 < 64) {
            this.f4914b &= ~(1 << i10);
            return;
        }
        e eVar = (e) this.f4915c;
        if (eVar != null) {
            eVar.d(i10 - 64);
        }
    }

    public int e(int i10) {
        e eVar = (e) this.f4915c;
        if (eVar == null) {
            return i10 >= 64 ? Long.bitCount(this.f4914b) : Long.bitCount(this.f4914b & ((1 << i10) - 1));
        }
        if (i10 < 64) {
            return Long.bitCount(this.f4914b & ((1 << i10) - 1));
        }
        return Long.bitCount(this.f4914b) + eVar.e(i10 - 64);
    }

    public void f() {
        if (((e) this.f4915c) == null) {
            this.f4915c = new e(0);
        }
    }

    public boolean g(int i10) {
        if (i10 < 64) {
            return (this.f4914b & (1 << i10)) != 0;
        }
        f();
        return ((e) this.f4915c).g(i10 - 64);
    }

    public void h(int i10, boolean z) {
        if (i10 >= 64) {
            f();
            ((e) this.f4915c).h(i10 - 64, z);
            return;
        }
        long j10 = this.f4914b;
        boolean z5 = (Long.MIN_VALUE & j10) != 0;
        long j11 = (1 << i10) - 1;
        this.f4914b = ((j10 & (~j11)) << 1) | (j10 & j11);
        if (z) {
            k(i10);
        } else {
            d(i10);
        }
        if (z5 || ((e) this.f4915c) != null) {
            f();
            ((e) this.f4915c).h(0, z5);
        }
    }

    public boolean i(int i10) {
        if (i10 >= 64) {
            f();
            return ((e) this.f4915c).i(i10 - 64);
        }
        long j10 = 1 << i10;
        long j11 = this.f4914b;
        boolean z = (j11 & j10) != 0;
        long j12 = j11 & (~j10);
        this.f4914b = j12;
        long j13 = j10 - 1;
        this.f4914b = (j12 & j13) | Long.rotateRight((~j13) & j12, 1);
        e eVar = (e) this.f4915c;
        if (eVar != null) {
            if (eVar.g(0)) {
                k(63);
            }
            ((e) this.f4915c).i(0);
        }
        return z;
    }

    public void j() {
        this.f4914b = 0L;
        e eVar = (e) this.f4915c;
        if (eVar != null) {
            eVar.j();
        }
    }

    public void k(int i10) {
        if (i10 < 64) {
            this.f4914b |= 1 << i10;
        } else {
            f();
            ((e) this.f4915c).k(i10 - 64);
        }
    }

    public String toString() {
        switch (this.f4913a) {
            case 0:
                if (((e) this.f4915c) == null) {
                    return Long.toBinaryString(this.f4914b);
                }
                return ((e) this.f4915c).toString() + "xx" + Long.toBinaryString(this.f4914b);
            default:
                return super.toString();
        }
    }

    public e(int i10) {
        this.f4913a = i10;
        switch (i10) {
            case 1:
                this.f4915c = Collections.synchronizedList(new ArrayList());
                break;
            default:
                this.f4914b = 0L;
                break;
        }
    }
}
