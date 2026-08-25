package com.google.android.gms.internal.cast;

import j$.util.concurrent.ConcurrentHashMap;
import java.nio.charset.Charset;

/* JADX INFO: loaded from: classes4.dex */
public final class ob {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final ob f13531c = new ob();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final ConcurrentHashMap f13533b = new ConcurrentHashMap();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final ja f13532a = new ja();

    public final rb a(Class cls) {
        Charset charset = ya.f13698a;
        if (cls == null) {
            throw new NullPointerException("messageType");
        }
        ConcurrentHashMap concurrentHashMap = this.f13533b;
        rb mbVar = (rb) concurrentHashMap.get(cls);
        if (mbVar == null) {
            ja jaVar = this.f13532a;
            jaVar.getClass();
            k5 k5Var = tb.f13618a;
            if (!pa.class.isAssignableFrom(cls)) {
                int i10 = da.f13235a;
            }
            fb fbVarB = ((ja) jaVar.f13365l).b(cls);
            if (fbVarB.zza()) {
                int i11 = da.f13235a;
                k5 k5Var2 = tb.f13618a;
                k5 k5Var3 = ka.f13390a;
                mbVar = new mb(k5Var2, fbVarB.zzb());
            } else {
                int i12 = da.f13235a;
                int i13 = nb.f13496a;
                int i14 = bb.f13197a;
                k5 k5Var4 = tb.f13618a;
                k5 k5Var5 = fbVarB.zzc() + (-1) != 1 ? ka.f13390a : null;
                int i15 = eb.f13245a;
                mbVar = lb.k(fbVarB, k5Var4, k5Var5);
            }
            rb rbVar = (rb) concurrentHashMap.putIfAbsent(cls, mbVar);
            if (rbVar != null) {
                return rbVar;
            }
        }
        return mbVar;
    }
}
