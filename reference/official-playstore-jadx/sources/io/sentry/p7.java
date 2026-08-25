package io.sentry;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.Serializable;
import java.util.logging.Logger;

/* JADX INFO: loaded from: classes4.dex */
public class p7 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public boolean f17518a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public Object f17519b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final Serializable f17520c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public Object f17521d;

    public p7() {
        this.f17519b = null;
        this.f17520c = d4.AUTO;
        this.f17518a = false;
        this.f17521d = "manual";
    }

    public void a() {
        jb.h hVar = (jb.h) this.f17521d;
        synchronized (hVar) {
            try {
                if (this.f17518a) {
                    throw new IllegalStateException("Check failed.");
                }
                if (kotlin.jvm.internal.p.a(((jb.e) this.f17519b).f19425g, this)) {
                    hVar.j(this, false);
                }
                this.f17518a = true;
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public void b() {
        jb.h hVar = (jb.h) this.f17521d;
        synchronized (hVar) {
            try {
                if (this.f17518a) {
                    throw new IllegalStateException("Check failed.");
                }
                if (kotlin.jvm.internal.p.a(((jb.e) this.f17519b).f19425g, this)) {
                    hVar.j(this, true);
                }
                this.f17518a = true;
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public void c() {
        jb.e eVar = (jb.e) this.f17519b;
        if (kotlin.jvm.internal.p.a(eVar.f19425g, this)) {
            jb.h hVar = (jb.h) this.f17521d;
            if (hVar.f19444t) {
                hVar.j(this, false);
            } else {
                eVar.f19424f = true;
            }
        }
    }

    public int[] d() {
        synchronized (this) {
            try {
                if (!this.f17518a) {
                    return null;
                }
                long[] jArr = (long[]) this.f17519b;
                int length = jArr.length;
                int i10 = 0;
                int i11 = 0;
                while (i10 < length) {
                    int i12 = i11 + 1;
                    int i13 = 1;
                    boolean z = jArr[i10] > 0;
                    boolean[] zArr = (boolean[]) this.f17520c;
                    if (z != zArr[i11]) {
                        int[] iArr = (int[]) this.f17521d;
                        if (!z) {
                            i13 = 2;
                        }
                        iArr[i11] = i13;
                    } else {
                        ((int[]) this.f17521d)[i11] = 0;
                    }
                    zArr[i11] = z;
                    i10++;
                    i11 = i12;
                }
                this.f17518a = false;
                return (int[]) ((int[]) this.f17521d).clone();
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public xb.m0 e(int i10) {
        xb.c cVarE;
        jb.h hVar = (jb.h) this.f17521d;
        synchronized (hVar) {
            try {
                if (this.f17518a) {
                    throw new IllegalStateException("Check failed.");
                }
                if (!kotlin.jvm.internal.p.a(((jb.e) this.f17519b).f19425g, this)) {
                    return new xb.f();
                }
                jb.e eVar = (jb.e) this.f17519b;
                if (!eVar.f19423e) {
                    ((boolean[]) this.f17520c)[i10] = true;
                }
                File file = (File) eVar.f19422d.get(i10);
                try {
                    try {
                        Logger logger = xb.d0.f22723a;
                        cVarE = xb.b.e(new FileOutputStream(file, false));
                    } catch (FileNotFoundException unused) {
                        return new xb.f();
                    }
                } catch (FileNotFoundException unused2) {
                    file.getParentFile().mkdirs();
                    Logger logger2 = xb.d0.f22723a;
                    cVarE = xb.b.e(new FileOutputStream(file, false));
                }
                return new jb.i(cVarE, new androidx.navigation.p(hVar, this, 10));
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    /* JADX WARN: Type inference failed for: r0v1, types: [boolean[], java.io.Serializable] */
    public p7(int i10) {
        this.f17519b = new long[i10];
        this.f17520c = new boolean[i10];
        this.f17521d = new int[i10];
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v3, types: [java.io.Serializable] */
    /* JADX WARN: Type inference failed for: r1v4 */
    /* JADX WARN: Type inference failed for: r1v5 */
    public p7(jb.h hVar, jb.e eVar) {
        ?? r1;
        this.f17521d = hVar;
        this.f17519b = eVar;
        if (eVar.f19423e) {
            r1 = 0;
        } else {
            hVar.getClass();
            r1 = new boolean[2];
        }
        this.f17520c = r1;
    }
}
