package kotlin.jvm.internal;

import kotlin.reflect.n;

/* JADX INFO: loaded from: classes5.dex */
public abstract class y extends e0 implements kotlin.reflect.n {
    @Override // kotlin.jvm.internal.e
    public kotlin.reflect.c computeReflected() {
        return l0.f19747a.f(this);
    }

    @Override // kotlin.reflect.n
    public Object getDelegate() {
        return ((kotlin.reflect.n) getReflected()).getDelegate();
    }

    @Override // r7.a
    public Object invoke() {
        return get();
    }

    @Override // kotlin.reflect.m
    public n.a getGetter() {
        return ((kotlin.reflect.n) getReflected()).getGetter();
    }
}
