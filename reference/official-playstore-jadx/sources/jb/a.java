package jb;

import androidx.tv.foundation.lazy.grid.f0;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import xb.h0;
import xb.j;
import xb.l;
import xb.o0;
import xb.q0;

/* JADX INFO: loaded from: classes5.dex */
public final class a implements o0 {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public boolean f19411i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ l f19412l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ f0 f19413m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ h0 f19414n;

    public a(l lVar, f0 f0Var, h0 h0Var) {
        this.f19412l = lVar;
        this.f19413m = f0Var;
        this.f19414n = h0Var;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() throws IOException {
        boolean zU;
        if (!this.f19411i) {
            TimeUnit timeUnit = TimeUnit.MILLISECONDS;
            try {
                zU = ib.c.u(this, 100);
            } catch (IOException unused) {
                zU = false;
            }
            if (!zU) {
                this.f19411i = true;
                this.f19413m.a();
            }
        }
        this.f19412l.close();
    }

    @Override // xb.o0
    public final long read(j jVar, long j10) throws Throwable {
        try {
            long j11 = this.f19412l.read(jVar, j10);
            h0 h0Var = this.f19414n;
            if (j11 != -1) {
                jVar.l(h0Var.f22751l, jVar.f22759l - j11, j11);
                h0Var.i();
                return j11;
            }
            if (!this.f19411i) {
                this.f19411i = true;
                h0Var.close();
            }
            return -1L;
        } catch (IOException e5) {
            if (this.f19411i) {
                throw e5;
            }
            this.f19411i = true;
            this.f19413m.a();
            throw e5;
        }
    }

    @Override // xb.o0
    public final q0 timeout() {
        return this.f19412l.timeout();
    }
}
