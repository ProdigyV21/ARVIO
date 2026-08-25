package jb;

import coil.disk.DiskLruCache;
import io.sentry.p7;
import io.sentry.util.l;
import java.io.Closeable;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.Flushable;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.logging.Logger;
import kotlin.jvm.internal.p;
import kotlin.text.m;
import kotlin.text.o;
import kotlin.text.u;
import qb.n;
import xb.d0;
import xb.h0;
import xb.i0;
import xb.q0;

/* JADX INFO: loaded from: classes5.dex */
public final class h implements Closeable, Flushable {
    public static final m B = new m("[a-z0-9_-]{1,120}");
    public static final String C = "CLEAN";
    public static final String D = "DIRTY";
    public static final String E = "REMOVE";
    public static final String F = "READ";

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final File f19435i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final File f19436l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final File f19437m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final File f19438n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public long f19439o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public h0 f19440p;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public int f19442r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public boolean f19443s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public boolean f19444t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public boolean f19445u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public boolean f19446v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public boolean f19447w;
    public boolean x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public long f19448y;
    public final kb.b z;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final LinkedHashMap f19441q = new LinkedHashMap(0, 0.75f, true);
    public final g A = new g(this, a0.c.p(new StringBuilder(), ib.c.f16275h, " Cache"), 0);

    public h(File file, kb.e eVar) {
        this.f19435i = file;
        this.z = eVar.e();
        this.f19436l = new File(file, DiskLruCache.JOURNAL_FILE);
        this.f19437m = new File(file, DiskLruCache.JOURNAL_FILE_TMP);
        this.f19438n = new File(file, DiskLruCache.JOURNAL_FILE_BACKUP);
    }

    public static void b0(String str) {
        if (!B.e(str)) {
            throw new IllegalArgumentException(androidx.compose.material3.d.i('\"', "keys must match regex [a-z0-9_-]{1,120}: \"", str).toString());
        }
    }

    public final void G() throws IOException {
        xb.c cVarE;
        Logger logger = d0.f22723a;
        File file = this.f19436l;
        i0 i0Var = new i0(new xb.d(new FileInputStream(file), q0.f22791d, 1));
        try {
            String strB = i0Var.B(Long.MAX_VALUE);
            String strB2 = i0Var.B(Long.MAX_VALUE);
            String strB3 = i0Var.B(Long.MAX_VALUE);
            String strB4 = i0Var.B(Long.MAX_VALUE);
            String strB5 = i0Var.B(Long.MAX_VALUE);
            if (!DiskLruCache.MAGIC.equals(strB) || !"1".equals(strB2) || !p.a(String.valueOf(201105), strB3) || !p.a(String.valueOf(2), strB4) || strB5.length() > 0) {
                throw new IOException("unexpected journal header: [" + strB + ", " + strB2 + ", " + strB4 + ", " + strB5 + ']');
            }
            int i10 = 0;
            while (true) {
                try {
                    O(i0Var.B(Long.MAX_VALUE));
                    i10++;
                } catch (EOFException unused) {
                    this.f19442r = i10 - this.f19441q.size();
                    if (i0Var.i()) {
                        try {
                            Logger logger2 = d0.f22723a;
                            cVarE = xb.b.e(new FileOutputStream(file, true));
                        } catch (FileNotFoundException unused2) {
                            file.getParentFile().mkdirs();
                            Logger logger3 = d0.f22723a;
                            cVarE = xb.b.e(new FileOutputStream(file, true));
                        }
                        this.f19440p = new h0(new i(cVarE, new ab.h(this, 21)));
                    } else {
                        R();
                    }
                    i0Var.close();
                    return;
                }
            }
        } finally {
        }
    }

    public final void O(String str) throws IOException {
        String strSubstring;
        int iE0 = o.e0(str, ' ', 0, false, 6);
        if (iE0 == -1) {
            throw new IOException(androidx.compose.material3.d.C("unexpected journal line: ", str));
        }
        int i10 = iE0 + 1;
        int iE02 = o.e0(str, ' ', i10, false, 4);
        LinkedHashMap linkedHashMap = this.f19441q;
        if (iE02 == -1) {
            strSubstring = str.substring(i10);
            String str2 = E;
            if (iE0 == str2.length() && u.P(str, str2, false)) {
                linkedHashMap.remove(strSubstring);
                return;
            }
        } else {
            strSubstring = str.substring(i10, iE02);
        }
        e eVar = (e) linkedHashMap.get(strSubstring);
        if (eVar == null) {
            eVar = new e(this, strSubstring);
            linkedHashMap.put(strSubstring, eVar);
        }
        if (iE02 != -1) {
            String str3 = C;
            if (iE0 == str3.length() && u.P(str, str3, false)) {
                List listX0 = o.x0(str.substring(iE02 + 1), new char[]{' '}, 6);
                eVar.f19423e = true;
                eVar.f19425g = null;
                int size = listX0.size();
                eVar.f19428j.getClass();
                if (size != 2) {
                    throw new IOException("unexpected journal line: " + listX0);
                }
                try {
                    int size2 = listX0.size();
                    for (int i11 = 0; i11 < size2; i11++) {
                        eVar.f19420b[i11] = Long.parseLong((String) listX0.get(i11));
                    }
                    return;
                } catch (NumberFormatException unused) {
                    throw new IOException("unexpected journal line: " + listX0);
                }
            }
        }
        if (iE02 == -1) {
            String str4 = D;
            if (iE0 == str4.length() && u.P(str, str4, false)) {
                eVar.f19425g = new p7(this, eVar);
                return;
            }
        }
        if (iE02 == -1) {
            String str5 = F;
            if (iE0 == str5.length() && u.P(str, str5, false)) {
                return;
            }
        }
        throw new IOException(androidx.compose.material3.d.C("unexpected journal line: ", str));
    }

    public final synchronized void R() {
        xb.c cVarE;
        xb.c cVarE2;
        try {
            h0 h0Var = this.f19440p;
            if (h0Var != null) {
                h0Var.close();
            }
            File file = this.f19437m;
            try {
                Logger logger = d0.f22723a;
                cVarE = xb.b.e(new FileOutputStream(file, false));
            } catch (FileNotFoundException unused) {
                file.getParentFile().mkdirs();
                Logger logger2 = d0.f22723a;
                cVarE = xb.b.e(new FileOutputStream(file, false));
            }
            h0 h0Var2 = new h0(cVarE);
            try {
                h0Var2.H(DiskLruCache.MAGIC);
                h0Var2.writeByte(10);
                h0Var2.H("1");
                h0Var2.writeByte(10);
                h0Var2.Y(201105);
                h0Var2.writeByte(10);
                h0Var2.Y(2);
                h0Var2.writeByte(10);
                h0Var2.writeByte(10);
                for (e eVar : this.f19441q.values()) {
                    if (eVar.f19425g != null) {
                        h0Var2.H(D);
                        h0Var2.writeByte(32);
                        h0Var2.H(eVar.f19419a);
                        h0Var2.writeByte(10);
                    } else {
                        h0Var2.H(C);
                        h0Var2.writeByte(32);
                        h0Var2.H(eVar.f19419a);
                        for (long j10 : eVar.f19420b) {
                            h0Var2.writeByte(32);
                            h0Var2.Y(j10);
                        }
                        h0Var2.writeByte(10);
                    }
                }
                h0Var2.close();
                l lVar = pb.a.f21302j;
                if (this.f19436l.exists()) {
                    lVar.r(this.f19436l, this.f19438n);
                }
                lVar.r(this.f19437m, this.f19436l);
                lVar.i(this.f19438n);
                File file2 = this.f19436l;
                try {
                    Logger logger3 = d0.f22723a;
                    cVarE2 = xb.b.e(new FileOutputStream(file2, true));
                } catch (FileNotFoundException unused2) {
                    file2.getParentFile().mkdirs();
                    Logger logger4 = d0.f22723a;
                    cVarE2 = xb.b.e(new FileOutputStream(file2, true));
                }
                this.f19440p = new h0(new i(cVarE2, new ab.h(this, 21)));
                this.f19443s = false;
                this.x = false;
            } finally {
            }
        } catch (Throwable th) {
            throw th;
        }
    }

    public final void T(e eVar) {
        h0 h0Var;
        String str = eVar.f19419a;
        if (!this.f19444t) {
            if (eVar.f19426h > 0 && (h0Var = this.f19440p) != null) {
                h0Var.H(D);
                h0Var.writeByte(32);
                h0Var.H(str);
                h0Var.writeByte(10);
                h0Var.flush();
            }
            if (eVar.f19426h > 0 || eVar.f19425g != null) {
                eVar.f19424f = true;
                return;
            }
        }
        p7 p7Var = eVar.f19425g;
        if (p7Var != null) {
            p7Var.c();
        }
        for (int i10 = 0; i10 < 2; i10++) {
            File file = (File) eVar.f19421c.get(i10);
            if (!file.delete() && file.exists()) {
                throw new IOException("failed to delete " + file);
            }
            long j10 = this.f19439o;
            long[] jArr = eVar.f19420b;
            this.f19439o = j10 - jArr[i10];
            jArr[i10] = 0;
        }
        this.f19442r++;
        h0 h0Var2 = this.f19440p;
        if (h0Var2 != null) {
            h0Var2.H(E);
            h0Var2.writeByte(32);
            h0Var2.H(str);
            h0Var2.writeByte(10);
        }
        this.f19441q.remove(str);
        if (x()) {
            this.z.c(this.A, 0L);
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:9:0x0023, code lost:
    
        T(r1);
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void V() {
        /*
            r4 = this;
        L0:
            long r0 = r4.f19439o
            r2 = 52428800(0x3200000, double:2.5903269E-316)
            int r0 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r0 <= 0) goto L28
            java.util.LinkedHashMap r0 = r4.f19441q
            java.util.Collection r0 = r0.values()
            java.util.Iterator r0 = r0.iterator()
        L13:
            boolean r1 = r0.hasNext()
            if (r1 == 0) goto L27
            java.lang.Object r1 = r0.next()
            jb.e r1 = (jb.e) r1
            boolean r2 = r1.f19424f
            if (r2 != 0) goto L13
            r4.T(r1)
            goto L0
        L27:
            return
        L28:
            r0 = 0
            r4.f19447w = r0
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: jb.h.V():void");
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final synchronized void close() {
        try {
            if (this.f19445u && !this.f19446v) {
                for (e eVar : (e[]) this.f19441q.values().toArray(new e[0])) {
                    p7 p7Var = eVar.f19425g;
                    if (p7Var != null) {
                        p7Var.c();
                    }
                }
                V();
                this.f19440p.close();
                this.f19440p = null;
                this.f19446v = true;
                return;
            }
            this.f19446v = true;
        } catch (Throwable th) {
            throw th;
        }
    }

    @Override // java.io.Flushable
    public final synchronized void flush() {
        if (this.f19445u) {
            i();
            V();
            this.f19440p.flush();
        }
    }

    public final synchronized void i() {
        if (this.f19446v) {
            throw new IllegalStateException("cache is closed");
        }
    }

    public final synchronized void j(p7 p7Var, boolean z) {
        e eVar = (e) p7Var.f17519b;
        if (!p.a(eVar.f19425g, p7Var)) {
            throw new IllegalStateException("Check failed.");
        }
        if (z && !eVar.f19423e) {
            for (int i10 = 0; i10 < 2; i10++) {
                if (!((boolean[]) p7Var.f17520c)[i10]) {
                    p7Var.a();
                    throw new IllegalStateException("Newly created entry didn't create value for index " + i10);
                }
                if (!((File) eVar.f19422d.get(i10)).exists()) {
                    p7Var.a();
                    return;
                }
            }
        }
        for (int i11 = 0; i11 < 2; i11++) {
            File file = (File) eVar.f19422d.get(i11);
            if (z && !eVar.f19424f) {
                l lVar = pb.a.f21302j;
                if (file.exists()) {
                    File file2 = (File) eVar.f19421c.get(i11);
                    lVar.r(file, file2);
                    long j10 = eVar.f19420b[i11];
                    long length = file2.length();
                    eVar.f19420b[i11] = length;
                    this.f19439o = (this.f19439o - j10) + length;
                }
            } else if (!file.delete() && file.exists()) {
                throw new IOException("failed to delete " + file);
            }
        }
        eVar.f19425g = null;
        if (eVar.f19424f) {
            T(eVar);
            return;
        }
        this.f19442r++;
        h0 h0Var = this.f19440p;
        if (eVar.f19423e || z) {
            eVar.f19423e = true;
            h0Var.H(C);
            h0Var.writeByte(32);
            h0Var.H(eVar.f19419a);
            for (long j11 : eVar.f19420b) {
                h0Var.writeByte(32);
                h0Var.Y(j11);
            }
            h0Var.writeByte(10);
            if (z) {
                long j12 = this.f19448y;
                this.f19448y = 1 + j12;
                eVar.f19427i = j12;
            }
        } else {
            this.f19441q.remove(eVar.f19419a);
            h0Var.H(E);
            h0Var.writeByte(32);
            h0Var.H(eVar.f19419a);
            h0Var.writeByte(10);
        }
        h0Var.flush();
        if (this.f19439o > 52428800 || x()) {
            this.z.c(this.A, 0L);
        }
    }

    public final synchronized p7 k(long j10, String str) {
        o();
        i();
        b0(str);
        e eVar = (e) this.f19441q.get(str);
        if (j10 != -1 && (eVar == null || eVar.f19427i != j10)) {
            return null;
        }
        if ((eVar != null ? eVar.f19425g : null) != null) {
            return null;
        }
        if (eVar != null && eVar.f19426h != 0) {
            return null;
        }
        if (!this.f19447w && !this.x) {
            h0 h0Var = this.f19440p;
            h0Var.H(D);
            h0Var.writeByte(32);
            h0Var.H(str);
            h0Var.writeByte(10);
            h0Var.flush();
            if (this.f19443s) {
                return null;
            }
            if (eVar == null) {
                eVar = new e(this, str);
                this.f19441q.put(str, eVar);
            }
            p7 p7Var = new p7(this, eVar);
            eVar.f19425g = p7Var;
            return p7Var;
        }
        this.z.c(this.A, 0L);
        return null;
    }

    public final synchronized f l(String str) {
        o();
        i();
        b0(str);
        e eVar = (e) this.f19441q.get(str);
        if (eVar == null) {
            return null;
        }
        f fVarA = eVar.a();
        if (fVarA == null) {
            return null;
        }
        this.f19442r++;
        h0 h0Var = this.f19440p;
        h0Var.H(F);
        h0Var.writeByte(32);
        h0Var.H(str);
        h0Var.writeByte(10);
        if (x()) {
            this.z.c(this.A, 0L);
        }
        return fVarA;
    }

    public final synchronized void o() {
        xb.c cVarE;
        boolean z;
        try {
            byte[] bArr = ib.c.f16268a;
            if (this.f19445u) {
                return;
            }
            l lVar = pb.a.f21302j;
            if (this.f19438n.exists()) {
                if (this.f19436l.exists()) {
                    lVar.i(this.f19438n);
                } else {
                    lVar.r(this.f19438n, this.f19436l);
                }
            }
            File file = this.f19438n;
            lVar.getClass();
            try {
                Logger logger = d0.f22723a;
                cVarE = xb.b.e(new FileOutputStream(file, false));
            } catch (FileNotFoundException unused) {
                file.getParentFile().mkdirs();
                Logger logger2 = d0.f22723a;
                cVarE = xb.b.e(new FileOutputStream(file, false));
            }
            try {
                try {
                    lVar.i(file);
                    cVarE.close();
                    z = true;
                } catch (IOException unused2) {
                    cVarE.close();
                    lVar.i(file);
                    z = false;
                }
                this.f19444t = z;
                if (this.f19436l.exists()) {
                    try {
                        G();
                        y();
                        this.f19445u = true;
                        return;
                    } catch (IOException e5) {
                        n nVar = n.f21385a;
                        n nVar2 = n.f21385a;
                        String str = "DiskLruCache " + this.f19435i + " is corrupt: " + e5.getMessage() + ", removing";
                        nVar2.getClass();
                        n.i(str, e5, 5);
                        try {
                            close();
                            pb.a.f21302j.j(this.f19435i);
                            this.f19446v = false;
                            R();
                            this.f19445u = true;
                        } catch (Throwable th) {
                            this.f19446v = false;
                            throw th;
                        }
                    }
                }
                R();
                this.f19445u = true;
            } finally {
            }
        } catch (Throwable th2) {
            throw th2;
        }
    }

    public final boolean x() {
        int i10 = this.f19442r;
        return i10 >= 2000 && i10 >= this.f19441q.size();
    }

    public final void y() throws IOException {
        File file = this.f19437m;
        l lVar = pb.a.f21302j;
        lVar.i(file);
        Iterator it = this.f19441q.values().iterator();
        while (it.hasNext()) {
            e eVar = (e) it.next();
            int i10 = 0;
            if (eVar.f19425g == null) {
                while (i10 < 2) {
                    this.f19439o += eVar.f19420b[i10];
                    i10++;
                }
            } else {
                eVar.f19425g = null;
                while (i10 < 2) {
                    lVar.i((File) eVar.f19421c.get(i10));
                    lVar.i((File) eVar.f19422d.get(i10));
                    i10++;
                }
                it.remove();
            }
        }
    }
}
