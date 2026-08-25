package a8;

import kotlin.reflect.m;

/* JADX INFO: loaded from: classes5.dex */
public abstract class l1 extends u implements kotlin.reflect.g, m.a {
    @Override // a8.u
    public final boolean A() {
        return C().A();
    }

    public abstract g8.o0 B();

    public abstract r1 C();

    @Override // kotlin.reflect.g
    public final boolean isExternal() {
        return B().isExternal();
    }

    @Override // kotlin.reflect.g
    public final boolean isInfix() {
        return B().isInfix();
    }

    @Override // kotlin.reflect.g
    public final boolean isInline() {
        return B().isInline();
    }

    @Override // kotlin.reflect.g
    public final boolean isOperator() {
        return B().isOperator();
    }

    @Override // kotlin.reflect.c, kotlin.reflect.g
    public final boolean isSuspend() {
        return B().isSuspend();
    }

    @Override // a8.u
    public final j0 v() {
        return C().f254q;
    }

    @Override // a8.u
    public final b8.e w() {
        return null;
    }
}
