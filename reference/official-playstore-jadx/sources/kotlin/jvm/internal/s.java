package kotlin.jvm.internal;

import kotlin.reflect.i;
import kotlin.reflect.n;

/* JADX INFO: loaded from: classes5.dex */
public abstract class s extends w implements kotlin.reflect.i {
    @Override // kotlin.jvm.internal.e
    public final kotlin.reflect.c computeReflected() {
        return l0.f19747a.d(this);
    }

    @Override // kotlin.reflect.n
    public final Object getDelegate() {
        return ((kotlin.reflect.i) getReflected()).getDelegate();
    }

    @Override // r7.a
    public final Object invoke() {
        return ((t) this).get();
    }

    @Override // kotlin.reflect.m
    public final n.a getGetter() {
        return ((kotlin.reflect.i) getReflected()).getGetter();
    }

    @Override // kotlin.reflect.h
    public final i.a getSetter() {
        return ((kotlin.reflect.i) getReflected()).getSetter();
    }
}
