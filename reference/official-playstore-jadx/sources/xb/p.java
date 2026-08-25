package xb;

import java.io.Closeable;
import java.util.concurrent.locks.ReentrantLock;

/* JADX INFO: loaded from: classes5.dex */
public abstract class p implements Closeable {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public boolean f22780i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public int f22781l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final ReentrantLock f22782m = new ReentrantLock();

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
        ReentrantLock reentrantLock = this.f22782m;
        reentrantLock.lock();
        try {
            if (this.f22780i) {
                return;
            }
            this.f22780i = true;
            if (this.f22781l != 0) {
                return;
            }
            reentrantLock.unlock();
            i();
        } finally {
            reentrantLock.unlock();
        }
    }

    public abstract void i();

    public abstract int j(long j10, byte[] bArr, int i10, int i11);

    public abstract long k();

    public final o l(long j10) {
        ReentrantLock reentrantLock = this.f22782m;
        reentrantLock.lock();
        try {
            if (this.f22780i) {
                throw new IllegalStateException("closed");
            }
            this.f22781l++;
            reentrantLock.unlock();
            return new o(this, j10);
        } catch (Throwable th) {
            reentrantLock.unlock();
            throw th;
        }
    }

    public final long size() {
        ReentrantLock reentrantLock = this.f22782m;
        reentrantLock.lock();
        try {
            if (this.f22780i) {
                throw new IllegalStateException("closed");
            }
            reentrantLock.unlock();
            return k();
        } catch (Throwable th) {
            reentrantLock.unlock();
            throw th;
        }
    }
}
