package kotlin.jvm.internal;

import kotlin.reflect.o;

/* JADX INFO: loaded from: classes5.dex */
public abstract class a0 extends e0 implements kotlin.reflect.o {
    @Override // kotlin.jvm.internal.e
    public kotlin.reflect.c computeReflected() {
        return l0.f19747a.g(this);
    }

    @Override // kotlin.reflect.o
    public Object getDelegate(Object obj) {
        return ((kotlin.reflect.o) getReflected()).getDelegate(obj);
    }

    @Override // r7.l
    public Object invoke(Object obj) {
        return get(obj);
    }

    @Override // kotlin.reflect.m
    public o.a getGetter() {
        return ((kotlin.reflect.o) getReflected()).getGetter();
    }
}
