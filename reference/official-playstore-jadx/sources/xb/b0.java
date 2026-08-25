package xb;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.nio.channels.FileChannel;
import java.nio.file.CopyOption;
import java.nio.file.DirectoryStream;
import java.nio.file.FileSystem;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.NoSuchFileException;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.nio.file.StandardOpenOption;
import java.nio.file.attribute.FileAttribute;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public final class b0 extends c0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final FileSystem f22716a;

    public b0(FileSystem fileSystem) {
        this.f22716a = fileSystem;
    }

    @Override // xb.a0, xb.s
    public final m0 appendingSink(f0 f0Var, boolean z) {
        z6.c cVarN = t7.a.n();
        cVarN.add(StandardOpenOption.APPEND);
        if (!z) {
            cVarN.add(StandardOpenOption.CREATE);
        }
        z6.c cVarE = t7.a.e(cVarN);
        Path pathE = e(f0Var);
        StandardOpenOption[] standardOpenOptionArr = (StandardOpenOption[]) cVarE.toArray(new StandardOpenOption[0]);
        OpenOption[] openOptionArr = (OpenOption[]) Arrays.copyOf(standardOpenOptionArr, standardOpenOptionArr.length);
        return b.e(Files.newOutputStream(pathE, (OpenOption[]) Arrays.copyOf(openOptionArr, openOptionArr.length)));
    }

    @Override // xb.c0, xb.a0, xb.s
    public final void atomicMove(f0 f0Var, f0 f0Var2) throws IOException {
        try {
            Files.move(e(f0Var), e(f0Var2), (CopyOption[]) Arrays.copyOf(new CopyOption[]{StandardCopyOption.ATOMIC_MOVE, StandardCopyOption.REPLACE_EXISTING}, 2));
        } catch (UnsupportedOperationException unused) {
            throw new IOException("atomic move not supported");
        } catch (NoSuchFileException e5) {
            throw new FileNotFoundException(e5.getMessage());
        }
    }

    @Override // xb.a0, xb.s
    public final f0 canonicalize(f0 f0Var) throws FileNotFoundException {
        try {
            String str = f0.f22733l;
            return v1.i.j(e(f0Var).toRealPath(new LinkOption[0]));
        } catch (NoSuchFileException unused) {
            throw new FileNotFoundException(w.a.b(f0Var, "no such file: "));
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x000d  */
    @Override // xb.a0, xb.s
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void createDirectory(xb.f0 r4, boolean r5) throws java.io.IOException {
        /*
            r3 = this;
            xb.q r0 = r3.metadataOrNull(r4)
            r1 = 0
            if (r0 == 0) goto Ld
            boolean r0 = r0.f22784b
            r2 = 1
            if (r0 != r2) goto Ld
            goto Le
        Ld:
            r2 = r1
        Le:
            if (r2 == 0) goto L2a
            if (r5 != 0) goto L13
            goto L2a
        L13:
            java.io.IOException r5 = new java.io.IOException
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            r0.append(r4)
            java.lang.String r4 = " already exists."
            r0.append(r4)
            java.lang.String r4 = r0.toString()
            r5.<init>(r4)
            throw r5
        L2a:
            java.nio.file.Path r5 = r3.e(r4)     // Catch: java.io.IOException -> L3a
            java.nio.file.attribute.FileAttribute[] r0 = new java.nio.file.attribute.FileAttribute[r1]     // Catch: java.io.IOException -> L3a
            java.lang.Object[] r0 = java.util.Arrays.copyOf(r0, r1)     // Catch: java.io.IOException -> L3a
            java.nio.file.attribute.FileAttribute[] r0 = (java.nio.file.attribute.FileAttribute[]) r0     // Catch: java.io.IOException -> L3a
            java.nio.file.Files.createDirectory(r5, r0)     // Catch: java.io.IOException -> L3a
            return
        L3a:
            r5 = move-exception
            if (r2 == 0) goto L3e
            return
        L3e:
            java.io.IOException r0 = new java.io.IOException
            java.lang.String r1 = "failed to create directory: "
            java.lang.String r4 = w.a.b(r4, r1)
            r0.<init>(r4, r5)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: xb.b0.createDirectory(xb.f0, boolean):void");
    }

    @Override // xb.c0, xb.a0, xb.s
    public final void createSymlink(f0 f0Var, f0 f0Var2) throws IOException {
        Files.createSymbolicLink(e(f0Var), e(f0Var2), (FileAttribute[]) Arrays.copyOf(new FileAttribute[0], 0));
    }

    public final ArrayList d(f0 f0Var, boolean z) throws IOException {
        Path pathE = e(f0Var);
        try {
            DirectoryStream<Path> directoryStreamNewDirectoryStream = Files.newDirectoryStream(pathE, "*");
            try {
                List<Path> listC1 = kotlin.collections.x.c1(directoryStreamNewDirectoryStream);
                directoryStreamNewDirectoryStream.close();
                ArrayList arrayList = new ArrayList();
                for (Path path : listC1) {
                    String str = f0.f22733l;
                    arrayList.add(v1.i.j(path));
                }
                kotlin.collections.w.W(arrayList);
                return arrayList;
            } finally {
            }
        } catch (Exception unused) {
            if (!z) {
                return null;
            }
            if (Files.exists(pathE, (LinkOption[]) Arrays.copyOf(new LinkOption[0], 0))) {
                throw new IOException(w.a.b(f0Var, "failed to list "));
            }
            throw new FileNotFoundException(w.a.b(f0Var, "no such file: "));
        }
    }

    @Override // xb.a0, xb.s
    public final void delete(f0 f0Var, boolean z) throws IOException {
        if (Thread.interrupted()) {
            throw new InterruptedIOException("interrupted");
        }
        Path pathE = e(f0Var);
        try {
            Files.delete(pathE);
        } catch (NoSuchFileException unused) {
            if (z) {
                throw new FileNotFoundException(w.a.b(f0Var, "no such file: "));
            }
        } catch (IOException unused2) {
            if (Files.exists(pathE, (LinkOption[]) Arrays.copyOf(new LinkOption[0], 0))) {
                throw new IOException(w.a.b(f0Var, "failed to delete "));
            }
        }
    }

    public final Path e(f0 f0Var) {
        return this.f22716a.getPath(f0Var.f22734i.t(), new String[0]);
    }

    @Override // xb.a0, xb.s
    public final List list(f0 f0Var) {
        return d(f0Var, true);
    }

    @Override // xb.a0, xb.s
    public final List listOrNull(f0 f0Var) {
        return d(f0Var, false);
    }

    @Override // xb.c0, xb.a0, xb.s
    public final q metadataOrNull(f0 f0Var) {
        return c0.b(e(f0Var));
    }

    @Override // xb.a0, xb.s
    public final p openReadOnly(f0 f0Var) throws FileNotFoundException {
        try {
            return new z(FileChannel.open(e(f0Var), StandardOpenOption.READ), 1);
        } catch (NoSuchFileException unused) {
            throw new FileNotFoundException(w.a.b(f0Var, "no such file: "));
        }
    }

    @Override // xb.a0, xb.s
    public final p openReadWrite(f0 f0Var, boolean z, boolean z5) throws FileNotFoundException {
        if (z && z5) {
            throw new IllegalArgumentException("Cannot require mustCreate and mustExist at the same time.");
        }
        z6.c cVarN = t7.a.n();
        cVarN.add(StandardOpenOption.READ);
        cVarN.add(StandardOpenOption.WRITE);
        if (z) {
            cVarN.add(StandardOpenOption.CREATE_NEW);
        } else if (!z5) {
            cVarN.add(StandardOpenOption.CREATE);
        }
        z6.c cVarE = t7.a.e(cVarN);
        try {
            Path pathE = e(f0Var);
            StandardOpenOption[] standardOpenOptionArr = (StandardOpenOption[]) cVarE.toArray(new StandardOpenOption[0]);
            return new z(FileChannel.open(pathE, (OpenOption[]) Arrays.copyOf(standardOpenOptionArr, standardOpenOptionArr.length)), 1);
        } catch (NoSuchFileException unused) {
            throw new FileNotFoundException(w.a.b(f0Var, "no such file: "));
        }
    }

    @Override // xb.a0, xb.s
    public final m0 sink(f0 f0Var, boolean z) throws FileNotFoundException {
        z6.c cVarN = t7.a.n();
        if (z) {
            cVarN.add(StandardOpenOption.CREATE_NEW);
        }
        z6.c cVarE = t7.a.e(cVarN);
        try {
            Path pathE = e(f0Var);
            StandardOpenOption[] standardOpenOptionArr = (StandardOpenOption[]) cVarE.toArray(new StandardOpenOption[0]);
            OpenOption[] openOptionArr = (OpenOption[]) Arrays.copyOf(standardOpenOptionArr, standardOpenOptionArr.length);
            return b.e(Files.newOutputStream(pathE, (OpenOption[]) Arrays.copyOf(openOptionArr, openOptionArr.length)));
        } catch (NoSuchFileException unused) {
            throw new FileNotFoundException(w.a.b(f0Var, "no such file: "));
        }
    }

    @Override // xb.a0, xb.s
    public final o0 source(f0 f0Var) throws FileNotFoundException {
        try {
            return b.g(Files.newInputStream(e(f0Var), (OpenOption[]) Arrays.copyOf(new OpenOption[0], 0)));
        } catch (NoSuchFileException unused) {
            throw new FileNotFoundException(w.a.b(f0Var, "no such file: "));
        }
    }

    @Override // xb.c0, xb.a0
    public final String toString() {
        return kotlin.jvm.internal.l0.f19747a.b(this.f22716a.getClass()).r();
    }
}
