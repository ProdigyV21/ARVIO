package kotlin.jvm.internal;

import kotlin.reflect.p;

/* JADX INFO: loaded from: classes5.dex */
public abstract class c0 extends e0 implements kotlin.reflect.p {
    @Override // kotlin.jvm.internal.e
    public final kotlin.reflect.c computeReflected() {
        return l0.f19747a.h(this);
    }

    @Override // r7.p
    public final Object invoke(Object obj, Object obj2) {
        return ((d0) this).getGetter().call(obj, obj2);
    }

    @Override // kotlin.reflect.m
    public final p.a getGetter() {
        return ((kotlin.reflect.p) getReflected()).getGetter();
    }
}
