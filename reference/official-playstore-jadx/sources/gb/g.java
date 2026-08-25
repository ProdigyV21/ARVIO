package gb;

import java.io.Closeable;
import java.io.File;
import java.io.Flushable;
import java.security.NoSuchAlgorithmException;

/* JADX INFO: loaded from: classes5.dex */
public final class g implements Closeable, Flushable {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final jb.h f15662i;

    public g(File file) {
        this.f15662i = new jb.h(file, kb.e.f19674h);
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
        this.f15662i.close();
    }

    @Override // java.io.Flushable
    public final void flush() {
        this.f15662i.flush();
    }

    public final void i(k0 k0Var) throws NoSuchAlgorithmException {
        jb.h hVar = this.f15662i;
        String strV = k2.c.v(k0Var.f15730a);
        synchronized (hVar) {
            hVar.o();
            hVar.i();
            jb.h.b0(strV);
            jb.e eVar = (jb.e) hVar.f19441q.get(strV);
            if (eVar == null) {
                return;
            }
            hVar.T(eVar);
            if (hVar.f19439o <= 52428800) {
                hVar.f19447w = false;
            }
        }
    }

    public final synchronized void j() {
    }
}
