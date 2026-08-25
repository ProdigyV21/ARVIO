package androidx.compose.runtime;

import kotlin.Metadata;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0001\n\u0002\b\n\b\u0002\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u00028\u00000\u00022\b\u0012\u0004\u0012\u00028\u00000\u0003B\u001d\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0005¢\u0006\u0004\b\u0007\u0010\bJ\u0010\u0010\t\u001a\u00028\u0000H\u0096\u0003¢\u0006\u0004\b\t\u0010\nJ\u001c\u0010\r\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\f0\u000bH\u0096\u0003¢\u0006\u0004\b\r\u0010\u000eJ\u001e\u0010\u0012\u001a\u00020\u00112\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\f0\u000fH\u0096@¢\u0006\u0004\b\u0012\u0010\u0013R\u001a\u0010\u0006\u001a\u00020\u00058\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\u0006\u0010\u0014\u001a\u0004\b\u0015\u0010\u0016R\u001c\u0010\u001a\u001a\u00028\u00008\u0016@\u0016X\u0096\u000f¢\u0006\f\u001a\u0004\b\u0017\u0010\n\"\u0004\b\u0018\u0010\u0019¨\u0006\u001b"}, d2 = {"Landroidx/compose/runtime/ProduceStateScopeImpl;", "T", "Landroidx/compose/runtime/ProduceStateScope;", "Landroidx/compose/runtime/MutableState;", "state", "Ld7/j;", "coroutineContext", "<init>", "(Landroidx/compose/runtime/MutableState;Ld7/j;)V", "component1", "()Ljava/lang/Object;", "Lkotlin/Function1;", "Lx6/t0;", "component2", "()Lr7/l;", "Lkotlin/Function0;", "onDispose", "", "awaitDispose", "(Lr7/a;Ld7/d;)Ljava/lang/Object;", "Ld7/j;", "getCoroutineContext", "()Ld7/j;", "getValue", "setValue", "(Ljava/lang/Object;)V", "value", "runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
final class ProduceStateScopeImpl<T> implements ProduceStateScope<T>, MutableState<T> {
    private final /* synthetic */ MutableState<T> $$delegate_0;
    private final d7.j coroutineContext;

    /* JADX INFO: renamed from: androidx.compose.runtime.ProduceStateScopeImpl$awaitDispose$1, reason: invalid class name */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    @f7.e(c = "androidx.compose.runtime.ProduceStateScopeImpl", f = "ProduceState.kt", l = {224}, m = "awaitDispose")
    public static final class AnonymousClass1 extends f7.c {
        Object L$0;
        int label;
        /* synthetic */ Object result;
        final /* synthetic */ ProduceStateScopeImpl<T> this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(ProduceStateScopeImpl<T> produceStateScopeImpl, d7.d<? super AnonymousClass1> dVar) {
            super(dVar);
            this.this$0 = produceStateScopeImpl;
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return this.this$0.awaitDispose(null, this);
        }
    }

    public ProduceStateScopeImpl(MutableState<T> mutableState, d7.j jVar) {
        this.coroutineContext = jVar;
        this.$$delegate_0 = mutableState;
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    @Override // androidx.compose.runtime.ProduceStateScope
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.Object awaitDispose(r7.a<x6.t0> r5, d7.d<?> r6) {
        /*
            r4 = this;
            boolean r0 = r6 instanceof androidx.compose.runtime.ProduceStateScopeImpl.AnonymousClass1
            if (r0 == 0) goto L13
            r0 = r6
            androidx.compose.runtime.ProduceStateScopeImpl$awaitDispose$1 r0 = (androidx.compose.runtime.ProduceStateScopeImpl.AnonymousClass1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            androidx.compose.runtime.ProduceStateScopeImpl$awaitDispose$1 r0 = new androidx.compose.runtime.ProduceStateScopeImpl$awaitDispose$1
            r0.<init>(r4, r6)
        L18:
            java.lang.Object r6 = r0.result
            int r1 = r0.label
            r2 = 1
            if (r1 == 0) goto L33
            if (r1 == r2) goto L29
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r6)
            throw r5
        L29:
            java.lang.Object r5 = r0.L$0
            r7.a r5 = (r7.a) r5
            k2.c.G(r6)     // Catch: java.lang.Throwable -> L31
            goto L4f
        L31:
            r6 = move-exception
            goto L55
        L33:
            k2.c.G(r6)
            r0.L$0 = r5     // Catch: java.lang.Throwable -> L31
            r0.label = r2     // Catch: java.lang.Throwable -> L31
            ka.l r6 = new ka.l     // Catch: java.lang.Throwable -> L31
            d7.d r0 = t7.a.A(r0)     // Catch: java.lang.Throwable -> L31
            r6.<init>(r2, r0)     // Catch: java.lang.Throwable -> L31
            r6.s()     // Catch: java.lang.Throwable -> L31
            java.lang.Object r6 = r6.r()     // Catch: java.lang.Throwable -> L31
            e7.a r0 = e7.a.f15033i
            if (r6 != r0) goto L4f
            return r0
        L4f:
            kotlin.KotlinNothingValueException r6 = new kotlin.KotlinNothingValueException     // Catch: java.lang.Throwable -> L31
            r6.<init>()     // Catch: java.lang.Throwable -> L31
            throw r6     // Catch: java.lang.Throwable -> L31
        L55:
            r5.invoke()
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.runtime.ProduceStateScopeImpl.awaitDispose(r7.a, d7.d):java.lang.Object");
    }

    @Override // androidx.compose.runtime.MutableState
    public T component1() {
        return this.$$delegate_0.component1();
    }

    @Override // androidx.compose.runtime.MutableState
    public r7.l<T, t0> component2() {
        return this.$$delegate_0.component2();
    }

    @Override // androidx.compose.runtime.ProduceStateScope, ka.k0
    public d7.j getCoroutineContext() {
        return this.coroutineContext;
    }

    @Override // androidx.compose.runtime.MutableState, androidx.compose.runtime.State
    public T getValue() {
        return this.$$delegate_0.getValue();
    }

    @Override // androidx.compose.runtime.MutableState
    public void setValue(T t2) {
        this.$$delegate_0.setValue(t2);
    }
}
