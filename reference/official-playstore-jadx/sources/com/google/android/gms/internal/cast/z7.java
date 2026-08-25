package com.google.android.gms.internal.cast;

import j$.util.concurrent.ConcurrentHashMap;
import java.io.IOException;

/* JADX INFO: loaded from: classes4.dex */
public final class z7 implements ta, z2.d {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public static z7 f13718i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public static final z7 f13719l = new z7();

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public static final z7 f13720m = new z7();

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public static final z7 f13721n = new z7();

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public static final z7 f13722o = new z7();

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public static final /* synthetic */ z7 f13723p = new z7();

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public static final z7 f13724q = new z7();

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public static final z7 f13725r = new z7();

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public static final z7 f13726s = new z7();

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public static final z7 f13727t = new z7();

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public static final z7 f13728u = new z7();

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public static final z7 f13729v = new z7();

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public static final z7 f13730w = new z7();
    public static final z7 x = new z7();

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public static final z7 f13731y = new z7();
    public static final z7 z = new z7();
    public static final z7 A = new z7();
    public static final z7 B = new z7();
    public static final z7 C = new z7();
    public static final z7 D = new z7();
    public static final z7 E = new z7();
    public static final z7 F = new z7();
    public static final z7 G = new z7();
    public static final z7 H = new z7();
    public static final z7 I = new z7();
    public static final z7 J = new z7();
    public static final z7 K = new z7();
    public static final z7 L = new z7();
    public static final z7 M = new z7();
    public static final z7 N = new z7();

    public static synchronized void a() {
        if (f13718i == null) {
            z7 z7Var = new z7();
            new ConcurrentHashMap();
            f13718i = z7Var;
        }
    }

    @Override // z2.d
    public Object apply(Object obj) {
        ba baVar = (ba) obj;
        baVar.getClass();
        try {
            pa paVar = (pa) baVar;
            int iG = paVar.g();
            byte[] bArr = new byte[iG];
            ia iaVar = new ia(bArr, iG);
            rb rbVarA = ob.f13531c.a(paVar.getClass());
            ja jaVar = iaVar.f13327m;
            if (jaVar == null) {
                jaVar = new ja(iaVar);
            }
            rbVarA.e(paVar, jaVar);
            if (iG - iaVar.f13330p == 0) {
                return bArr;
            }
            throw new IllegalStateException("Did not write as much data as expected.");
        } catch (IOException e5) {
            String name = baVar.getClass().getName();
            throw new RuntimeException(androidx.compose.material3.d.q(new StringBuilder(name.length() + 72), "Serializing ", name, " to a byte array threw an IOException (should never happen)."), e5);
        }
    }
}
