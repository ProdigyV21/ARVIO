package xb;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/* JADX INFO: loaded from: classes5.dex */
public class e extends q0 {

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public static final ReentrantLock f22724h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public static final Condition f22725i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public static final long f22726j;
    public static final long k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public static e f22727l;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f22728e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public e f22729f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public long f22730g;

    static {
        ReentrantLock reentrantLock = new ReentrantLock();
        f22724h = reentrantLock;
        f22725i = reentrantLock.newCondition();
        long millis = TimeUnit.SECONDS.toMillis(60L);
        f22726j = millis;
        k = TimeUnit.MILLISECONDS.toNanos(millis);
    }

    public final void i() {
        long j10 = this.f22794c;
        boolean z = this.f22792a;
        if (j10 != 0 || z) {
            ReentrantLock reentrantLock = f22724h;
            reentrantLock.lock();
            try {
                if (this.f22728e != 0) {
                    throw new IllegalStateException("Unbalanced enter/exit");
                }
                this.f22728e = 1;
                v1.i.b(this, j10, z);
            } finally {
                reentrantLock.unlock();
            }
        }
    }

    public final boolean j() {
        ReentrantLock reentrantLock = f22724h;
        reentrantLock.lock();
        try {
            int i10 = this.f22728e;
            this.f22728e = 0;
            if (i10 != 1) {
                return i10 == 2;
            }
            e eVar = f22727l;
            while (eVar != null) {
                e eVar2 = eVar.f22729f;
                if (eVar2 == this) {
                    eVar.f22729f = this.f22729f;
                    this.f22729f = null;
                    return false;
                }
                eVar = eVar2;
            }
            throw new IllegalStateException("node was not found in the queue");
        } finally {
            reentrantLock.unlock();
        }
    }

    public void k() {
    }
}
