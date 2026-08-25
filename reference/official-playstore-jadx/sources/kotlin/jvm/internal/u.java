package kotlin.jvm.internal;

import kotlin.reflect.j;
import kotlin.reflect.o;

/* JADX INFO: loaded from: classes5.dex */
public abstract class u extends w implements kotlin.reflect.j {
    @Override // kotlin.jvm.internal.e
    public kotlin.reflect.c computeReflected() {
        return l0.f19747a.e(this);
    }

    @Override // kotlin.reflect.o
    public Object getDelegate(Object obj) {
        return ((kotlin.reflect.j) getReflected()).getDelegate(obj);
    }

    @Override // r7.l
    public Object invoke(Object obj) {
        return get(obj);
    }

    @Override // kotlin.reflect.m
    public o.a getGetter() {
        return ((kotlin.reflect.j) getReflected()).getGetter();
    }

    @Override // kotlin.reflect.h
    public j.a getSetter() {
        return ((kotlin.reflect.j) getReflected()).getSetter();
    }
}
