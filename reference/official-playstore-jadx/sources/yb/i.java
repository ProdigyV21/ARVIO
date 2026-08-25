package yb;

import androidx.credentials.exceptions.publickeycredential.DomExceptionUtils;
import j8.t0;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import x6.i0;
import x6.x;
import xb.f0;
import xb.m0;
import xb.o0;
import xb.p;
import xb.q;
import xb.s;

/* JADX INFO: loaded from: classes5.dex */
public final class i extends s {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final f0 f23038d;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final ClassLoader f23039a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final s f23040b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final i0 f23041c;

    static {
        String str = f0.f22733l;
        f23038d = v1.i.i(DomExceptionUtils.SEPARATOR);
    }

    public i(ClassLoader classLoader) {
        s sVar = s.SYSTEM;
        this.f23039a = classLoader;
        this.f23040b = sVar;
        this.f23041c = new i0(new t0(this, 16));
    }

    public static String a(f0 f0Var) {
        f0 f0Var2 = f23038d;
        f0Var2.getClass();
        return f.b(f0Var2, f0Var, true).f(f0Var2).f22734i.t();
    }

    @Override // xb.s
    public final m0 appendingSink(f0 f0Var, boolean z) throws IOException {
        throw new IOException(this + " is read-only");
    }

    @Override // xb.s
    public final void atomicMove(f0 f0Var, f0 f0Var2) throws IOException {
        throw new IOException(this + " is read-only");
    }

    @Override // xb.s
    public final f0 canonicalize(f0 f0Var) {
        f0 f0Var2 = f23038d;
        f0Var2.getClass();
        return f.b(f0Var2, f0Var, true);
    }

    @Override // xb.s
    public final void createDirectory(f0 f0Var, boolean z) throws IOException {
        throw new IOException(this + " is read-only");
    }

    @Override // xb.s
    public final void createSymlink(f0 f0Var, f0 f0Var2) throws IOException {
        throw new IOException(this + " is read-only");
    }

    @Override // xb.s
    public final void delete(f0 f0Var, boolean z) throws IOException {
        throw new IOException(this + " is read-only");
    }

    @Override // xb.s
    public final List list(f0 f0Var) throws FileNotFoundException {
        String strA = a(f0Var);
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        boolean z = false;
        for (x xVar : (List) this.f23041c.getValue()) {
            s sVar = (s) xVar.f22608i;
            f0 f0Var2 = (f0) xVar.f22609l;
            try {
                List list = sVar.list(f0Var2.i(strA));
                ArrayList arrayList = new ArrayList();
                for (Object obj : list) {
                    if (v1.i.c((f0) obj)) {
                        arrayList.add(obj);
                    }
                }
                ArrayList arrayList2 = new ArrayList(kotlin.collections.s.U(arrayList, 10));
                Iterator it = arrayList.iterator();
                while (it.hasNext()) {
                    arrayList2.add(v1.i.m((f0) it.next(), f0Var2));
                }
                kotlin.collections.x.b0(linkedHashSet, arrayList2);
                z = true;
            } catch (IOException unused) {
            }
        }
        if (z) {
            return kotlin.collections.x.c1(linkedHashSet);
        }
        throw new FileNotFoundException(w.a.b(f0Var, "file not found: "));
    }

    @Override // xb.s
    public final List listOrNull(f0 f0Var) {
        String strA = a(f0Var);
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        Iterator it = ((List) this.f23041c.getValue()).iterator();
        boolean z = false;
        while (true) {
            ArrayList arrayList = null;
            if (!it.hasNext()) {
                break;
            }
            x xVar = (x) it.next();
            s sVar = (s) xVar.f22608i;
            f0 f0Var2 = (f0) xVar.f22609l;
            List listListOrNull = sVar.listOrNull(f0Var2.i(strA));
            if (listListOrNull != null) {
                ArrayList arrayList2 = new ArrayList();
                for (Object obj : listListOrNull) {
                    if (v1.i.c((f0) obj)) {
                        arrayList2.add(obj);
                    }
                }
                ArrayList arrayList3 = new ArrayList(kotlin.collections.s.U(arrayList2, 10));
                Iterator it2 = arrayList2.iterator();
                while (it2.hasNext()) {
                    arrayList3.add(v1.i.m((f0) it2.next(), f0Var2));
                }
                arrayList = arrayList3;
            }
            if (arrayList != null) {
                kotlin.collections.x.b0(linkedHashSet, arrayList);
                z = true;
            }
        }
        if (z) {
            return kotlin.collections.x.c1(linkedHashSet);
        }
        return null;
    }

    @Override // xb.s
    public final q metadataOrNull(f0 f0Var) {
        if (!v1.i.c(f0Var)) {
            return null;
        }
        String strA = a(f0Var);
        for (x xVar : (List) this.f23041c.getValue()) {
            q qVarMetadataOrNull = ((s) xVar.f22608i).metadataOrNull(((f0) xVar.f22609l).i(strA));
            if (qVarMetadataOrNull != null) {
                return qVarMetadataOrNull;
            }
        }
        return null;
    }

    @Override // xb.s
    public final p openReadOnly(f0 f0Var) throws FileNotFoundException {
        if (!v1.i.c(f0Var)) {
            throw new FileNotFoundException(w.a.b(f0Var, "file not found: "));
        }
        String strA = a(f0Var);
        for (x xVar : (List) this.f23041c.getValue()) {
            try {
                return ((s) xVar.f22608i).openReadOnly(((f0) xVar.f22609l).i(strA));
            } catch (FileNotFoundException unused) {
            }
        }
        throw new FileNotFoundException(w.a.b(f0Var, "file not found: "));
    }

    @Override // xb.s
    public final p openReadWrite(f0 f0Var, boolean z, boolean z5) throws IOException {
        throw new IOException("resources are not writable");
    }

    @Override // xb.s
    public final m0 sink(f0 f0Var, boolean z) throws IOException {
        throw new IOException(this + " is read-only");
    }

    @Override // xb.s
    public final o0 source(f0 f0Var) throws FileNotFoundException {
        if (!v1.i.c(f0Var)) {
            throw new FileNotFoundException(w.a.b(f0Var, "file not found: "));
        }
        f0 f0Var2 = f23038d;
        f0Var2.getClass();
        InputStream resourceAsStream = this.f23039a.getResourceAsStream(f.b(f0Var2, f0Var, false).f(f0Var2).f22734i.t());
        if (resourceAsStream != null) {
            return xb.b.g(resourceAsStream);
        }
        throw new FileNotFoundException(w.a.b(f0Var, "file not found: "));
    }
}
