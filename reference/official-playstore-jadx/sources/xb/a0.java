package xb;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/* JADX INFO: loaded from: classes5.dex */
public class a0 extends s {
    public static ArrayList a(f0 f0Var, boolean z) throws IOException {
        File file = f0Var.toFile();
        String[] list = file.list();
        if (list == null) {
            if (!z) {
                return null;
            }
            if (file.exists()) {
                throw new IOException(w.a.b(f0Var, "failed to list "));
            }
            throw new FileNotFoundException(w.a.b(f0Var, "no such file: "));
        }
        ArrayList arrayList = new ArrayList();
        for (String str : list) {
            arrayList.add(f0Var.i(str));
        }
        kotlin.collections.w.W(arrayList);
        return arrayList;
    }

    @Override // xb.s
    public m0 appendingSink(f0 f0Var, boolean z) throws IOException {
        if (!z || exists(f0Var)) {
            File file = f0Var.toFile();
            Logger logger = d0.f22723a;
            return b.e(new FileOutputStream(file, true));
        }
        throw new IOException(f0Var + " doesn't exist.");
    }

    @Override // xb.s
    public void atomicMove(f0 f0Var, f0 f0Var2) throws IOException {
        if (f0Var.toFile().renameTo(f0Var2.toFile())) {
            return;
        }
        throw new IOException("failed to move " + f0Var + " to " + f0Var2);
    }

    @Override // xb.s
    public f0 canonicalize(f0 f0Var) throws IOException {
        File canonicalFile = f0Var.toFile().getCanonicalFile();
        if (!canonicalFile.exists()) {
            throw new FileNotFoundException("no such file");
        }
        String str = f0.f22733l;
        return v1.i.h(canonicalFile);
    }

    @Override // xb.s
    public void createDirectory(f0 f0Var, boolean z) throws IOException {
        if (f0Var.toFile().mkdir()) {
            return;
        }
        q qVarMetadataOrNull = metadataOrNull(f0Var);
        if (qVarMetadataOrNull == null || !qVarMetadataOrNull.f22784b) {
            throw new IOException(w.a.b(f0Var, "failed to create directory: "));
        }
        if (z) {
            throw new IOException(f0Var + " already exists.");
        }
    }

    @Override // xb.s
    public void createSymlink(f0 f0Var, f0 f0Var2) throws IOException {
        throw new IOException("unsupported");
    }

    @Override // xb.s
    public void delete(f0 f0Var, boolean z) throws IOException {
        if (Thread.interrupted()) {
            throw new InterruptedIOException("interrupted");
        }
        File file = f0Var.toFile();
        if (file.delete()) {
            return;
        }
        if (file.exists()) {
            throw new IOException(w.a.b(f0Var, "failed to delete "));
        }
        if (z) {
            throw new FileNotFoundException(w.a.b(f0Var, "no such file: "));
        }
    }

    @Override // xb.s
    public List list(f0 f0Var) {
        return a(f0Var, true);
    }

    @Override // xb.s
    public List listOrNull(f0 f0Var) {
        return a(f0Var, false);
    }

    @Override // xb.s
    public q metadataOrNull(f0 f0Var) {
        File file = f0Var.toFile();
        boolean zIsFile = file.isFile();
        boolean zIsDirectory = file.isDirectory();
        long jLastModified = file.lastModified();
        long length = file.length();
        if (zIsFile || zIsDirectory || jLastModified != 0 || length != 0 || file.exists()) {
            return new q(zIsFile, zIsDirectory, null, Long.valueOf(length), null, Long.valueOf(jLastModified), null);
        }
        return null;
    }

    @Override // xb.s
    public p openReadOnly(f0 f0Var) {
        return new z(new RandomAccessFile(f0Var.toFile(), "r"), 0);
    }

    @Override // xb.s
    public p openReadWrite(f0 f0Var, boolean z, boolean z5) throws IOException {
        if (z && z5) {
            throw new IllegalArgumentException("Cannot require mustCreate and mustExist at the same time.");
        }
        if (z && exists(f0Var)) {
            throw new IOException(f0Var + " already exists.");
        }
        if (!z5 || exists(f0Var)) {
            return new z(new RandomAccessFile(f0Var.toFile(), "rw"), 0);
        }
        throw new IOException(f0Var + " doesn't exist.");
    }

    @Override // xb.s
    public m0 sink(f0 f0Var, boolean z) throws IOException {
        if (!z || !exists(f0Var)) {
            File file = f0Var.toFile();
            Logger logger = d0.f22723a;
            return b.e(new FileOutputStream(file, false));
        }
        throw new IOException(f0Var + " already exists.");
    }

    @Override // xb.s
    public o0 source(f0 f0Var) {
        File file = f0Var.toFile();
        Logger logger = d0.f22723a;
        return new d(new FileInputStream(file), q0.f22791d, 1);
    }

    public String toString() {
        return "JvmSystemFileSystem";
    }
}
