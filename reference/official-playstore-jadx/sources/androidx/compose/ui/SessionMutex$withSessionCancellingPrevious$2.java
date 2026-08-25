package androidx.compose.ui;

import androidx.compose.ui.SessionMutex;
import d7.d;
import f7.e;
import f7.j;
import java.util.concurrent.atomic.AtomicReference;
import ka.k0;
import kotlin.Metadata;
import r7.l;
import r7.p;
import x6.t0;

/* JADX INFO: Add missing generic type declarations: [R] */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\n\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0010\u0003\u001a\u00028\u0000\"\u0004\b\u0000\u0010\u0000\"\u0004\b\u0001\u0010\u0001*\u00020\u0002H\u008a@"}, d2 = {"R", "T", "Lka/k0;", "<anonymous>"}, k = 3, mv = {1, 8, 0})
@e(c = "androidx.compose.ui.SessionMutex$withSessionCancellingPrevious$2", f = "SessionMutex.kt", l = {66, 68}, m = "invokeSuspend")
public final class SessionMutex$withSessionCancellingPrevious$2<R> extends j implements p<k0, d<? super R>, Object> {
    final /* synthetic */ AtomicReference<SessionMutex.Session<T>> $arg0;
    final /* synthetic */ p<T, d<? super R>, Object> $session;
    final /* synthetic */ l<k0, T> $sessionInitializer;
    private /* synthetic */ Object L$0;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public SessionMutex$withSessionCancellingPrevious$2(l<? super k0, ? extends T> lVar, AtomicReference<SessionMutex.Session<T>> atomicReference, p<? super T, ? super d<? super R>, ? extends Object> pVar, d<? super SessionMutex$withSessionCancellingPrevious$2> dVar) {
        super(2, dVar);
        this.$sessionInitializer = lVar;
        this.$arg0 = atomicReference;
        this.$session = pVar;
    }

    @Override // f7.a
    public final d<t0> create(Object obj, d<?> dVar) {
        SessionMutex$withSessionCancellingPrevious$2 sessionMutex$withSessionCancellingPrevious$2 = new SessionMutex$withSessionCancellingPrevious$2(this.$sessionInitializer, this.$arg0, this.$session, dVar);
        sessionMutex$withSessionCancellingPrevious$2.L$0 = obj;
        return sessionMutex$withSessionCancellingPrevious$2;
    }

    /* JADX WARN: Code restructure failed: missing block: B:22:0x006b, code lost:
    
        if (r8 == r4) goto L23;
     */
    /* JADX WARN: Finally extract failed */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v0, types: [int] */
    /* JADX WARN: Type inference failed for: r0v1, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r0v14 */
    /* JADX WARN: Type inference failed for: r0v15 */
    /* JADX WARN: Type inference failed for: r0v4 */
    /* JADX WARN: Type inference failed for: r2v2, types: [java.lang.Object] */
    /* JADX WARN: Type inference incomplete: some casts might be missing */
    @Override // f7.a
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r8) {
        /*
            r7 = this;
            int r0 = r7.label
            r1 = 0
            r2 = 2
            r3 = 1
            e7.a r4 = e7.a.f15033i
            if (r0 == 0) goto L28
            if (r0 == r3) goto L20
            if (r0 != r2) goto L18
            java.lang.Object r0 = r7.L$0
            androidx.compose.ui.SessionMutex$Session r0 = (androidx.compose.ui.SessionMutex.Session) r0
            k2.c.G(r8)     // Catch: java.lang.Throwable -> L16
        L14:
            r2 = r0
            goto L6e
        L16:
            r8 = move-exception
            goto L7e
        L18:
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r8.<init>(r0)
            throw r8
        L20:
            java.lang.Object r0 = r7.L$0
            androidx.compose.ui.SessionMutex$Session r0 = (androidx.compose.ui.SessionMutex.Session) r0
            k2.c.G(r8)
            goto L5d
        L28:
            k2.c.G(r8)
            java.lang.Object r8 = r7.L$0
            ka.k0 r8 = (ka.k0) r8
            androidx.compose.ui.SessionMutex$Session r0 = new androidx.compose.ui.SessionMutex$Session
            d7.j r5 = r8.getCoroutineContext()
            ka.v1 r5 = ka.m0.k(r5)
            r7.l<ka.k0, T> r6 = r7.$sessionInitializer
            java.lang.Object r8 = r6.invoke(r8)
            r0.<init>(r5, r8)
            java.util.concurrent.atomic.AtomicReference<androidx.compose.ui.SessionMutex$Session<T>> r8 = r7.$arg0
            java.lang.Object r8 = r8.getAndSet(r0)
            androidx.compose.ui.SessionMutex$Session r8 = (androidx.compose.ui.SessionMutex.Session) r8
            if (r8 == 0) goto L5d
            ka.v1 r8 = r8.getJob()
            if (r8 == 0) goto L5d
            r7.L$0 = r0
            r7.label = r3
            java.lang.Object r8 = ka.m0.h(r8, r7)
            if (r8 != r4) goto L5d
            goto L6d
        L5d:
            r7.p<T, d7.d<? super R>, java.lang.Object> r8 = r7.$session     // Catch: java.lang.Throwable -> L16
            java.lang.Object r3 = r0.getValue()     // Catch: java.lang.Throwable -> L16
            r7.L$0 = r0     // Catch: java.lang.Throwable -> L16
            r7.label = r2     // Catch: java.lang.Throwable -> L16
            java.lang.Object r8 = r8.invoke(r3, r7)     // Catch: java.lang.Throwable -> L16
            if (r8 != r4) goto L14
        L6d:
            return r4
        L6e:
            java.util.concurrent.atomic.AtomicReference<androidx.compose.ui.SessionMutex$Session<T>> r3 = r7.$arg0
        L70:
            boolean r0 = r3.compareAndSet(r2, r1)
            if (r0 == 0) goto L77
            goto L7d
        L77:
            java.lang.Object r0 = r3.get()
            if (r0 == r2) goto L70
        L7d:
            return r8
        L7e:
            java.util.concurrent.atomic.AtomicReference<androidx.compose.ui.SessionMutex$Session<T>> r2 = r7.$arg0
        L80:
            boolean r3 = r2.compareAndSet(r0, r1)
            if (r3 != 0) goto L8d
            java.lang.Object r3 = r2.get()
            if (r3 != r0) goto L8d
            goto L80
        L8d:
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.ui.SessionMutex$withSessionCancellingPrevious$2.invokeSuspend(java.lang.Object):java.lang.Object");
    }

    @Override // r7.p
    public final Object invoke(k0 k0Var, d<? super R> dVar) {
        return ((SessionMutex$withSessionCancellingPrevious$2) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
    }
}
