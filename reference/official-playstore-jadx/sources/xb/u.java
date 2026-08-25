package xb;

/* JADX INFO: loaded from: classes5.dex */
public abstract class u implements m0 {
    private final m0 delegate;

    public u(m0 m0Var) {
        this.delegate = m0Var;
    }

    @x6.e
    /* JADX INFO: renamed from: -deprecated_delegate, reason: not valid java name */
    public final m0 m7046deprecated_delegate() {
        return this.delegate;
    }

    @Override // xb.m0, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        this.delegate.close();
    }

    public final m0 delegate() {
        return this.delegate;
    }

    @Override // xb.m0, java.io.Flushable
    public void flush() {
        this.delegate.flush();
    }

    @Override // xb.m0
    public q0 timeout() {
        return this.delegate.timeout();
    }

    public String toString() {
        return getClass().getSimpleName() + '(' + this.delegate + ')';
    }

    @Override // xb.m0
    public void write(j jVar, long j10) {
        this.delegate.write(jVar, j10);
    }
}
