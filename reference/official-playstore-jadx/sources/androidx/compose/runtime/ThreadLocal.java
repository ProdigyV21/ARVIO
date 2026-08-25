package androidx.compose.runtime;

import kotlin.Metadata;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0000\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u00028\u00000\u0002B\u0015\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\u000f\u0010\u0007\u001a\u00028\u0000H\u0016¢\u0006\u0004\b\u0007\u0010\bJ\u0017\u0010\u000b\u001a\u00020\n2\u0006\u0010\t\u001a\u00028\u0000H\u0016¢\u0006\u0004\b\u000b\u0010\fJ\u0011\u0010\u0004\u001a\u0004\u0018\u00018\u0000H\u0014¢\u0006\u0004\b\u0004\u0010\bJ\u000f\u0010\r\u001a\u00020\nH\u0016¢\u0006\u0004\b\r\u0010\u000eR\u001a\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u00038\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0004\u0010\u000f¨\u0006\u0010"}, d2 = {"Landroidx/compose/runtime/ThreadLocal;", "T", "Ljava/lang/ThreadLocal;", "Lkotlin/Function0;", "initialValue", "<init>", "(Lr7/a;)V", "get", "()Ljava/lang/Object;", "value", "Lx6/t0;", "set", "(Ljava/lang/Object;)V", "remove", "()V", "Lr7/a;", "runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class ThreadLocal<T> extends java.lang.ThreadLocal<T> {
    public static final int $stable = 0;
    private final r7.a<T> initialValue;

    /* JADX WARN: Multi-variable type inference failed */
    public ThreadLocal(r7.a<? extends T> aVar) {
        this.initialValue = aVar;
    }

    @Override // java.lang.ThreadLocal
    public T get() {
        return (T) super.get();
    }

    @Override // java.lang.ThreadLocal
    public T initialValue() {
        return (T) this.initialValue.invoke();
    }

    @Override // java.lang.ThreadLocal
    public void remove() {
        super.remove();
    }

    @Override // java.lang.ThreadLocal
    public void set(T value) {
        super.set(value);
    }
}
