package coil.disk;

import java.io.IOException;
import kotlin.Metadata;
import r7.l;
import x6.t0;
import xb.j;
import xb.m0;
import xb.u;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0003\b\u0000\u0018\u00002\u00020\u0001B'\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0016\u0010\b\u001a\u0012\u0012\b\u0012\u00060\u0005j\u0002`\u0006\u0012\u0004\u0012\u00020\u00070\u0004¢\u0006\u0004\b\t\u0010\nJ\u001f\u0010\u000f\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\u000b2\u0006\u0010\u000e\u001a\u00020\rH\u0016¢\u0006\u0004\b\u000f\u0010\u0010J\u000f\u0010\u0011\u001a\u00020\u0007H\u0016¢\u0006\u0004\b\u0011\u0010\u0012J\u000f\u0010\u0013\u001a\u00020\u0007H\u0016¢\u0006\u0004\b\u0013\u0010\u0012R$\u0010\b\u001a\u0012\u0012\b\u0012\u00060\u0005j\u0002`\u0006\u0012\u0004\u0012\u00020\u00070\u00048\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\b\u0010\u0014R\u0016\u0010\u0016\u001a\u00020\u00158\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u0016\u0010\u0017¨\u0006\u0018"}, d2 = {"Lcoil/disk/FaultHidingSink;", "Lxb/u;", "Lxb/m0;", "delegate", "Lkotlin/Function1;", "Ljava/io/IOException;", "Lokio/IOException;", "Lx6/t0;", "onException", "<init>", "(Lxb/m0;Lr7/l;)V", "Lxb/j;", "source", "", "byteCount", "write", "(Lxb/j;J)V", "flush", "()V", "close", "Lr7/l;", "", "hasErrors", "Z", "coil-base_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final class FaultHidingSink extends u {
    private boolean hasErrors;
    private final l<IOException, t0> onException;

    /* JADX WARN: Multi-variable type inference failed */
    public FaultHidingSink(m0 m0Var, l<? super IOException, t0> lVar) {
        super(m0Var);
        this.onException = lVar;
    }

    @Override // xb.u, xb.m0, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        try {
            super.close();
        } catch (IOException e5) {
            this.hasErrors = true;
            this.onException.invoke(e5);
        }
    }

    @Override // xb.u, xb.m0, java.io.Flushable
    public void flush() {
        try {
            super.flush();
        } catch (IOException e5) {
            this.hasErrors = true;
            this.onException.invoke(e5);
        }
    }

    @Override // xb.u, xb.m0
    public void write(j source, long byteCount) {
        if (this.hasErrors) {
            source.skip(byteCount);
            return;
        }
        try {
            super.write(source, byteCount);
        } catch (IOException e5) {
            this.hasErrors = true;
            this.onException.invoke(e5);
        }
    }
}
