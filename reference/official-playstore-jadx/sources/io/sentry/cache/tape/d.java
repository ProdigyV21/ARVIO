package io.sentry.cache.tape;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Stack;
import kotlin.reflect.jvm.internal.impl.protobuf.b0;
import kotlin.reflect.jvm.internal.impl.protobuf.h0;

/* JADX INFO: loaded from: classes5.dex */
public final class d implements Iterator {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f17163i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final Object f17164l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public Iterable f17165m;

    public d(e eVar, i iVar) {
        this.f17163i = 0;
        this.f17165m = eVar;
        this.f17164l = iVar;
    }

    public b0 a() {
        b0 b0Var;
        Stack stack = (Stack) this.f17164l;
        b0 b0Var2 = (b0) this.f17165m;
        if (b0Var2 == null) {
            throw new NoSuchElementException();
        }
        while (true) {
            if (!stack.isEmpty()) {
                kotlin.reflect.jvm.internal.impl.protobuf.g gVar = ((h0) stack.pop()).f19779n;
                while (gVar instanceof h0) {
                    h0 h0Var = (h0) gVar;
                    stack.push(h0Var);
                    gVar = h0Var.f19778m;
                }
                b0 b0Var3 = (b0) gVar;
                if (b0Var3.f19764l.length != 0) {
                    b0Var = b0Var3;
                    break;
                }
            } else {
                b0Var = null;
                break;
            }
        }
        this.f17165m = b0Var;
        return b0Var2;
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        switch (this.f17163i) {
            case 0:
                return ((i) this.f17164l).hasNext();
            default:
                return ((b0) this.f17165m) != null;
        }
    }

    @Override // java.util.Iterator
    public final Object next() {
        switch (this.f17163i) {
            case 0:
                return ((e) this.f17165m).f17168m.f((byte[]) ((i) this.f17164l).next());
            default:
                return a();
        }
    }

    @Override // java.util.Iterator
    public final void remove() {
        switch (this.f17163i) {
            case 0:
                ((i) this.f17164l).remove();
                return;
            default:
                throw new UnsupportedOperationException();
        }
    }

    public d(kotlin.reflect.jvm.internal.impl.protobuf.g gVar) {
        this.f17163i = 1;
        this.f17164l = new Stack();
        while (gVar instanceof h0) {
            h0 h0Var = (h0) gVar;
            ((Stack) this.f17164l).push(h0Var);
            gVar = h0Var.f19778m;
        }
        this.f17165m = (b0) gVar;
    }
}
