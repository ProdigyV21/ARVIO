package xb;

import java.io.IOException;

/* JADX INFO: loaded from: classes5.dex */
public abstract class v implements o0 {
    private final o0 delegate;

    public v(o0 o0Var) {
        this.delegate = o0Var;
    }

    @x6.e
    /* JADX INFO: renamed from: -deprecated_delegate, reason: not valid java name */
    public final o0 m7047deprecated_delegate() {
        return this.delegate;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.delegate.close();
    }

    public final o0 delegate() {
        return this.delegate;
    }

    @Override // xb.o0
    public long read(j jVar, long j10) {
        return this.delegate.read(jVar, j10);
    }

    @Override // xb.o0
    public q0 timeout() {
        return this.delegate.timeout();
    }

    public String toString() {
        return getClass().getSimpleName() + '(' + this.delegate + ')';
    }
}
