package io.ktor.server.plugins;

import kotlin.Metadata;
import kotlin.reflect.m;
import r7.a;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\b\b\u0002\u0018\u0000*\b\b\u0000\u0010\u0002*\u00020\u00012\u00020\u0001B\u0015\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u0003¢\u0006\u0004\b\u0005\u0010\u0006J$\u0010\t\u001a\u00028\u00002\u0006\u0010\u0007\u001a\u00020\u00012\n\u0010\u0004\u001a\u0006\u0012\u0002\b\u00030\bH\u0086\u0002¢\u0006\u0004\b\t\u0010\nJ,\u0010\r\u001a\u00020\f2\u0006\u0010\u0007\u001a\u00020\u00012\n\u0010\u0004\u001a\u0006\u0012\u0002\b\u00030\b2\u0006\u0010\u000b\u001a\u00028\u0000H\u0086\u0002¢\u0006\u0004\b\r\u0010\u000eR\u001d\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u00038\u0006¢\u0006\f\n\u0004\b\u0004\u0010\u000f\u001a\u0004\b\u0010\u0010\u0011R\u0018\u0010\u0012\u001a\u0004\u0018\u00018\u00008\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u0012\u0010\u0013¨\u0006\u0014"}, d2 = {"Lio/ktor/server/plugins/AssignableWithDelegate;", "", "T", "Lkotlin/Function0;", "property", "<init>", "(Lr7/a;)V", "thisRef", "Lkotlin/reflect/m;", "getValue", "(Ljava/lang/Object;Lkotlin/reflect/m;)Ljava/lang/Object;", "value", "Lx6/t0;", "setValue", "(Ljava/lang/Object;Lkotlin/reflect/m;Ljava/lang/Object;)V", "Lr7/a;", "getProperty", "()Lr7/a;", "assigned", "Ljava/lang/Object;", "ktor-server-core"}, k = 1, mv = {1, 8, 0}, xi = 48)
final class AssignableWithDelegate<T> {
    private T assigned;
    private final a<T> property;

    /* JADX WARN: Multi-variable type inference failed */
    public AssignableWithDelegate(a<? extends T> aVar) {
        this.property = aVar;
    }

    public final a<T> getProperty() {
        return this.property;
    }

    public final T getValue(Object thisRef, m<?> property) {
        T t2 = this.assigned;
        return t2 == null ? (T) this.property.invoke() : t2;
    }

    public final void setValue(Object thisRef, m<?> property, T value) {
        this.assigned = value;
    }
}
