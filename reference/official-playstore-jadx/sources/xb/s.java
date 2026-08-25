package xb;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.FileSystem;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public abstract class s {
    public static final r Companion = new r();
    public static final s RESOURCES;
    public static final s SYSTEM;
    public static final f0 SYSTEM_TEMPORARY_DIRECTORY;

    /* JADX INFO: renamed from: -write$default, reason: not valid java name */
    public static Object m7043write$default(s sVar, f0 f0Var, boolean z, r7.l lVar, int i10, Object obj) throws Throwable {
        Object objInvoke;
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: write");
        }
        if ((i10 & 2) != 0) {
            z = false;
        }
        h0 h0Var = new h0(sVar.sink(f0Var, z));
        Throwable th = null;
        try {
            objInvoke = lVar.invoke(h0Var);
            try {
                h0Var.close();
            } catch (Throwable th2) {
                th = th2;
            }
        } catch (Throwable th3) {
            try {
                h0Var.close();
            } catch (Throwable th4) {
                com.google.common.util.concurrent.r0.a(th3, th4);
            }
            objInvoke = null;
            th = th3;
        }
        if (th == null) {
            return objInvoke;
        }
        throw th;
    }

    static {
        s a0Var;
        try {
            Class.forName("java.nio.file.Files");
            a0Var = new c0();
        } catch (ClassNotFoundException unused) {
            a0Var = new a0();
        }
        SYSTEM = a0Var;
        String str = f0.f22733l;
        SYSTEM_TEMPORARY_DIRECTORY = v1.i.i(System.getProperty("java.io.tmpdir"));
        RESOURCES = new yb.i(yb.i.class.getClassLoader());
    }

    public static /* synthetic */ m0 appendingSink$default(s sVar, f0 f0Var, boolean z, int i10, Object obj) throws IOException {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: appendingSink");
        }
        if ((i10 & 2) != 0) {
            z = false;
        }
        return sVar.appendingSink(f0Var, z);
    }

    public static /* synthetic */ void createDirectories$default(s sVar, f0 f0Var, boolean z, int i10, Object obj) throws IOException {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: createDirectories");
        }
        if ((i10 & 2) != 0) {
            z = false;
        }
        sVar.createDirectories(f0Var, z);
    }

    public static /* synthetic */ void createDirectory$default(s sVar, f0 f0Var, boolean z, int i10, Object obj) throws IOException {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: createDirectory");
        }
        if ((i10 & 2) != 0) {
            z = false;
        }
        sVar.createDirectory(f0Var, z);
    }

    public static /* synthetic */ void delete$default(s sVar, f0 f0Var, boolean z, int i10, Object obj) throws IOException {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: delete");
        }
        if ((i10 & 2) != 0) {
            z = false;
        }
        sVar.delete(f0Var, z);
    }

    public static /* synthetic */ void deleteRecursively$default(s sVar, f0 f0Var, boolean z, int i10, Object obj) throws IOException {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: deleteRecursively");
        }
        if ((i10 & 2) != 0) {
            z = false;
        }
        sVar.deleteRecursively(f0Var, z);
    }

    @q7.n
    public static final s get(FileSystem fileSystem) {
        Companion.getClass();
        return new b0(fileSystem);
    }

    public static /* synthetic */ ga.m listRecursively$default(s sVar, f0 f0Var, boolean z, int i10, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: listRecursively");
        }
        if ((i10 & 2) != 0) {
            z = false;
        }
        return sVar.listRecursively(f0Var, z);
    }

    public static /* synthetic */ p openReadWrite$default(s sVar, f0 f0Var, boolean z, boolean z5, int i10, Object obj) throws IOException {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: openReadWrite");
        }
        if ((i10 & 2) != 0) {
            z = false;
        }
        if ((i10 & 4) != 0) {
            z5 = false;
        }
        return sVar.openReadWrite(f0Var, z, z5);
    }

    public static /* synthetic */ m0 sink$default(s sVar, f0 f0Var, boolean z, int i10, Object obj) throws IOException {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: sink");
        }
        if ((i10 & 2) != 0) {
            z = false;
        }
        return sVar.sink(f0Var, z);
    }

    /* JADX INFO: renamed from: -read, reason: not valid java name */
    public final <T> T m7044read(f0 f0Var, r7.l<? super l, ? extends T> lVar) throws Throwable {
        T t2;
        i0 i0Var = new i0(source(f0Var));
        Throwable th = null;
        try {
            t2 = (T) lVar.invoke(i0Var);
            try {
                i0Var.close();
            } catch (Throwable th2) {
                th = th2;
            }
        } catch (Throwable th3) {
            try {
                i0Var.close();
            } catch (Throwable th4) {
                com.google.common.util.concurrent.r0.a(th3, th4);
            }
            t2 = null;
            th = th3;
        }
        if (th == null) {
            return t2;
        }
        throw th;
    }

    /* JADX INFO: renamed from: -write, reason: not valid java name */
    public final <T> T m7045write(f0 f0Var, boolean z, r7.l<? super k, ? extends T> lVar) throws Throwable {
        T t2;
        h0 h0Var = new h0(sink(f0Var, z));
        Throwable th = null;
        try {
            t2 = (T) lVar.invoke(h0Var);
            try {
                h0Var.close();
            } catch (Throwable th2) {
                th = th2;
            }
        } catch (Throwable th3) {
            try {
                h0Var.close();
            } catch (Throwable th4) {
                com.google.common.util.concurrent.r0.a(th3, th4);
            }
            t2 = null;
            th = th3;
        }
        if (th == null) {
            return t2;
        }
        throw th;
    }

    public final m0 appendingSink(f0 f0Var) throws IOException {
        return appendingSink(f0Var, false);
    }

    public abstract m0 appendingSink(f0 f0Var, boolean z);

    public abstract void atomicMove(f0 f0Var, f0 f0Var2);

    public abstract f0 canonicalize(f0 f0Var);

    public void copy(f0 f0Var, f0 f0Var2) throws Throwable {
        Throwable th;
        o0 o0VarSource = source(f0Var);
        try {
            h0 h0Var = new h0(sink(f0Var2));
            th = null;
            try {
                h0Var.z0(o0VarSource);
                try {
                    h0Var.close();
                    th = null;
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (Throwable th3) {
                try {
                    h0Var.close();
                } catch (Throwable th4) {
                    com.google.common.util.concurrent.r0.a(th3, th4);
                }
                th = th3;
            }
            if (th != null) {
                throw th;
            }
            if (o0VarSource != null) {
                try {
                    o0VarSource.close();
                } catch (Throwable th5) {
                    th = th5;
                }
            }
        } catch (Throwable th6) {
            th = th6;
            if (o0VarSource != null) {
                try {
                    o0VarSource.close();
                } catch (Throwable th7) {
                    com.google.common.util.concurrent.r0.a(th, th7);
                }
            }
        }
        if (th != null) {
            throw th;
        }
    }

    public final void createDirectories(f0 f0Var, boolean z) throws IOException {
        kotlin.collections.p pVar = new kotlin.collections.p();
        for (f0 f0VarD = f0Var; f0VarD != null && !exists(f0VarD); f0VarD = f0VarD.d()) {
            pVar.addFirst(f0VarD);
        }
        if (z && pVar.isEmpty()) {
            throw new IOException(f0Var + " already exists.");
        }
        Iterator<E> it = pVar.iterator();
        while (it.hasNext()) {
            createDirectory((f0) it.next());
        }
    }

    public final void createDirectory(f0 f0Var) throws IOException {
        createDirectory(f0Var, false);
    }

    public abstract void createDirectory(f0 f0Var, boolean z);

    public abstract void createSymlink(f0 f0Var, f0 f0Var2);

    public final void delete(f0 f0Var) throws IOException {
        delete(f0Var, false);
    }

    public abstract void delete(f0 f0Var, boolean z);

    public void deleteRecursively(f0 f0Var, boolean z) throws IOException {
        ga.n nVarN = f4.f.n(new yb.d(this, f0Var, null));
        while (nVarN.hasNext()) {
            delete((f0) nVarN.next(), z && !nVarN.hasNext());
        }
    }

    public final boolean exists(f0 f0Var) throws IOException {
        return metadataOrNull(f0Var) != null;
    }

    public abstract List list(f0 f0Var);

    public abstract List listOrNull(f0 f0Var);

    public final ga.m<f0> listRecursively(f0 f0Var) {
        return listRecursively(f0Var, false);
    }

    public final q metadata(f0 f0Var) throws IOException {
        q qVarMetadataOrNull = metadataOrNull(f0Var);
        if (qVarMetadataOrNull != null) {
            return qVarMetadataOrNull;
        }
        throw new FileNotFoundException(w.a.b(f0Var, "no such file: "));
    }

    public abstract q metadataOrNull(f0 f0Var);

    public abstract p openReadOnly(f0 f0Var);

    public final p openReadWrite(f0 f0Var) throws IOException {
        return openReadWrite(f0Var, false, false);
    }

    public abstract p openReadWrite(f0 f0Var, boolean z, boolean z5);

    public final m0 sink(f0 f0Var) throws IOException {
        return sink(f0Var, false);
    }

    public abstract m0 sink(f0 f0Var, boolean z);

    public abstract o0 source(f0 f0Var);

    public ga.m listRecursively(f0 f0Var, boolean z) {
        return new ga.p(new yb.e(f0Var, this, z, null));
    }

    public final void deleteRecursively(f0 f0Var) throws IOException {
        deleteRecursively(f0Var, false);
    }

    public final void createDirectories(f0 f0Var) throws IOException {
        createDirectories(f0Var, false);
    }
}
