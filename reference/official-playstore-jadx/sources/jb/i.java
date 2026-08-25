package jb;

import java.io.IOException;
import kotlin.jvm.internal.r;
import r7.l;
import xb.j;
import xb.m0;
import xb.u;

/* JADX INFO: loaded from: classes5.dex */
public final class i extends u {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final r f19449i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public boolean f19450l;

    /* JADX WARN: Multi-variable type inference failed */
    public i(m0 m0Var, l lVar) {
        super(m0Var);
        this.f19449i = (r) lVar;
    }

    /* JADX WARN: Type inference failed for: r1v1, types: [kotlin.jvm.internal.r, r7.l] */
    @Override // xb.u, xb.m0, java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
        if (this.f19450l) {
            return;
        }
        try {
            super.close();
        } catch (IOException e5) {
            this.f19450l = true;
            this.f19449i.invoke(e5);
        }
    }

    /* JADX WARN: Type inference failed for: r1v1, types: [kotlin.jvm.internal.r, r7.l] */
    @Override // xb.u, xb.m0, java.io.Flushable
    public final void flush() {
        if (this.f19450l) {
            return;
        }
        try {
            super.flush();
        } catch (IOException e5) {
            this.f19450l = true;
            this.f19449i.invoke(e5);
        }
    }

    /* JADX WARN: Type inference failed for: r3v2, types: [kotlin.jvm.internal.r, r7.l] */
    @Override // xb.u, xb.m0
    public final void write(j jVar, long j10) {
        if (this.f19450l) {
            jVar.skip(j10);
            return;
        }
        try {
            super.write(jVar, j10);
        } catch (IOException e5) {
            this.f19450l = true;
            this.f19449i.invoke(e5);
        }
    }
}
