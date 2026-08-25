package com.google.android.gms.internal.auth;

import j$.util.concurrent.ConcurrentHashMap;
import java.nio.charset.Charset;

/* JADX INFO: loaded from: classes4.dex */
public final class f3 {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final f3 f13029c = new f3();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final ConcurrentHashMap f13031b = new ConcurrentHashMap();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final p2 f13030a = new p2();

    public final i3 a(Class cls) {
        i3 i3VarN;
        Class cls2;
        Charset charset = i2.f13047a;
        if (cls == null) {
            throw new NullPointerException("messageType");
        }
        ConcurrentHashMap concurrentHashMap = this.f13031b;
        i3 i3Var = (i3) concurrentHashMap.get(cls);
        if (i3Var != null) {
            return i3Var;
        }
        p2 p2Var = this.f13030a;
        p2Var.getClass();
        Class cls3 = k3.f13055a;
        if (!d2.class.isAssignableFrom(cls) && (cls2 = k3.f13055a) != null && !cls2.isAssignableFrom(cls)) {
            throw new IllegalArgumentException("Message classes must extend GeneratedMessage or GeneratedMessageLite");
        }
        t2 t2VarA = ((o2) p2Var.f13091l).a(cls);
        if (t2VarA.zzb()) {
            if (d2.class.isAssignableFrom(cls)) {
                i3VarN = new b3(k3.f13057c, a2.f12981a, t2VarA.zza());
            } else {
                m3 m3Var = k3.f13056b;
                z1 z1Var = a2.f12982b;
                if (z1Var == null) {
                    throw new IllegalStateException("Protobuf runtime is not correctly loaded.");
                }
                i3VarN = new b3(m3Var, z1Var, t2VarA.zza());
            }
        } else if (d2.class.isAssignableFrom(cls)) {
            if (t2VarA.zzc() - 1 != 1) {
                int i10 = d3.f13019a;
                m2 m2Var = n2.f13080b;
                m3 m3Var2 = k3.f13057c;
                z1 z1Var2 = a2.f12981a;
                int i11 = s2.f13111a;
                i3VarN = a3.n(t2VarA, m2Var, m3Var2);
            } else {
                int i12 = d3.f13019a;
                m2 m2Var2 = n2.f13080b;
                m3 m3Var3 = k3.f13057c;
                int i13 = s2.f13111a;
                i3VarN = a3.n(t2VarA, m2Var2, m3Var3);
            }
        } else if (t2VarA.zzc() - 1 != 1) {
            int i14 = d3.f13019a;
            l2 l2Var = n2.f13079a;
            m3 m3Var4 = k3.f13056b;
            if (a2.f12982b == null) {
                throw new IllegalStateException("Protobuf runtime is not correctly loaded.");
            }
            int i15 = s2.f13111a;
            i3VarN = a3.n(t2VarA, l2Var, m3Var4);
        } else {
            int i16 = d3.f13019a;
            l2 l2Var2 = n2.f13079a;
            m3 m3Var5 = k3.f13056b;
            int i17 = s2.f13111a;
            i3VarN = a3.n(t2VarA, l2Var2, m3Var5);
        }
        i3 i3Var2 = (i3) concurrentHashMap.putIfAbsent(cls, i3VarN);
        return i3Var2 == null ? i3VarN : i3Var2;
    }
}
