package xb;

import androidx.credentials.exceptions.publickeycredential.DomExceptionUtils;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.zip.Inflater;

/* JADX INFO: loaded from: classes5.dex */
public final class r0 extends s {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final f0 f22795d;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final f0 f22796a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final s f22797b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final LinkedHashMap f22798c;

    static {
        String str = f0.f22733l;
        f22795d = v1.i.i(DomExceptionUtils.SEPARATOR);
    }

    public r0(f0 f0Var, s sVar, LinkedHashMap linkedHashMap) {
        this.f22796a = f0Var;
        this.f22797b = sVar;
        this.f22798c = linkedHashMap;
    }

    public final List a(f0 f0Var, boolean z) throws IOException {
        f0 f0Var2 = f22795d;
        f0Var2.getClass();
        yb.j jVar = (yb.j) this.f22798c.get(yb.f.b(f0Var2, f0Var, true));
        if (jVar != null) {
            return kotlin.collections.x.c1(jVar.f23049h);
        }
        if (z) {
            throw new IOException(w.a.b(f0Var, "not a directory: "));
        }
        return null;
    }

    @Override // xb.s
    public final m0 appendingSink(f0 f0Var, boolean z) throws IOException {
        throw new IOException("zip file systems are read-only");
    }

    @Override // xb.s
    public final void atomicMove(f0 f0Var, f0 f0Var2) throws IOException {
        throw new IOException("zip file systems are read-only");
    }

    @Override // xb.s
    public final f0 canonicalize(f0 f0Var) throws FileNotFoundException {
        f0 f0Var2 = f22795d;
        f0Var2.getClass();
        f0 f0VarB = yb.f.b(f0Var2, f0Var, true);
        if (this.f22798c.containsKey(f0VarB)) {
            return f0VarB;
        }
        throw new FileNotFoundException(String.valueOf(f0Var));
    }

    @Override // xb.s
    public final void createDirectory(f0 f0Var, boolean z) throws IOException {
        throw new IOException("zip file systems are read-only");
    }

    @Override // xb.s
    public final void createSymlink(f0 f0Var, f0 f0Var2) throws IOException {
        throw new IOException("zip file systems are read-only");
    }

    @Override // xb.s
    public final void delete(f0 f0Var, boolean z) throws IOException {
        throw new IOException("zip file systems are read-only");
    }

    @Override // xb.s
    public final List list(f0 f0Var) {
        return a(f0Var, true);
    }

    @Override // xb.s
    public final List listOrNull(f0 f0Var) {
        return a(f0Var, false);
    }

    @Override // xb.s
    public final q metadataOrNull(f0 f0Var) throws Throwable {
        q qVarG;
        Throwable th;
        f0 f0Var2 = f22795d;
        f0Var2.getClass();
        yb.j jVar = (yb.j) this.f22798c.get(yb.f.b(f0Var2, f0Var, true));
        Throwable th2 = null;
        if (jVar == null) {
            return null;
        }
        long j10 = jVar.f23048g;
        boolean z = jVar.f23043b;
        q qVar = new q(!z, z, null, z ? null : Long.valueOf(jVar.f23045d), null, jVar.f23047f, null);
        if (j10 == -1) {
            return qVar;
        }
        p pVarOpenReadOnly = this.f22797b.openReadOnly(this.f22796a);
        try {
            i0 i0Var = new i0(pVarOpenReadOnly.l(j10));
            try {
                qVarG = yb.b.g(i0Var, qVar);
                try {
                    i0Var.close();
                    th = null;
                } catch (Throwable th3) {
                    th = th3;
                }
            } catch (Throwable th4) {
                try {
                    i0Var.close();
                } catch (Throwable th5) {
                    com.google.common.util.concurrent.r0.a(th4, th5);
                }
                th = th4;
                qVarG = null;
            }
            if (th != null) {
                throw th;
            }
            try {
                pVarOpenReadOnly.close();
            } catch (Throwable th6) {
                th2 = th6;
            }
        } catch (Throwable th7) {
            if (pVarOpenReadOnly != null) {
                try {
                    pVarOpenReadOnly.close();
                } catch (Throwable th8) {
                    com.google.common.util.concurrent.r0.a(th7, th8);
                }
            }
            qVarG = null;
            th2 = th7;
        }
        if (th2 == null) {
            return qVarG;
        }
        throw th2;
    }

    @Override // xb.s
    public final p openReadOnly(f0 f0Var) {
        throw new UnsupportedOperationException("not implemented yet!");
    }

    @Override // xb.s
    public final p openReadWrite(f0 f0Var, boolean z, boolean z5) throws IOException {
        throw new IOException("zip entries are not writable");
    }

    @Override // xb.s
    public final m0 sink(f0 f0Var, boolean z) throws IOException {
        throw new IOException("zip file systems are read-only");
    }

    @Override // xb.s
    public final o0 source(f0 f0Var) throws Throwable {
        i0 i0Var;
        Throwable th;
        f0 f0Var2 = f22795d;
        f0Var2.getClass();
        yb.j jVar = (yb.j) this.f22798c.get(yb.f.b(f0Var2, f0Var, true));
        if (jVar == null) {
            throw new FileNotFoundException(w.a.b(f0Var, "no such file: "));
        }
        long j10 = jVar.f23045d;
        p pVarOpenReadOnly = this.f22797b.openReadOnly(this.f22796a);
        try {
            i0Var = new i0(pVarOpenReadOnly.l(jVar.f23048g));
            try {
                pVarOpenReadOnly.close();
                th = null;
            } catch (Throwable th2) {
                th = th2;
            }
        } catch (Throwable th3) {
            if (pVarOpenReadOnly != null) {
                try {
                    pVarOpenReadOnly.close();
                } catch (Throwable th4) {
                    com.google.common.util.concurrent.r0.a(th3, th4);
                }
            }
            i0Var = null;
            th = th3;
        }
        if (th != null) {
            throw th;
        }
        yb.b.g(i0Var, null);
        return jVar.f23046e == 0 ? new yb.h(i0Var, j10, true) : new yb.h(new y(new yb.h(i0Var, jVar.f23044c, true), new Inflater(true)), j10, false);
    }
}
