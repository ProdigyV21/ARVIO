package androidx.compose.foundation;

import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.SnapshotStateKt__SnapshotStateKt;
import d7.d;
import f7.e;
import f7.j;
import ka.k;
import ka.k0;
import kotlin.Metadata;
import r7.l;
import r7.p;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0003\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0002\u0012\u0006\u0010\u0006\u001a\u00020\u0005¢\u0006\u0004\b\u0007\u0010\bJ\u0018\u0010\f\u001a\u00020\u000b2\u0006\u0010\n\u001a\u00020\tH\u0096@¢\u0006\u0004\b\f\u0010\rJ\u000f\u0010\u000e\u001a\u00020\u000bH\u0016¢\u0006\u0004\b\u000e\u0010\u000fJ\u000f\u0010\u0010\u001a\u00020\u000bH\u0016¢\u0006\u0004\b\u0010\u0010\u000fR\u001a\u0010\u0004\u001a\u00020\u00028\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\u0004\u0010\u0011\u001a\u0004\b\u0004\u0010\u0012R\u0014\u0010\u0006\u001a\u00020\u00058\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0006\u0010\u0013R+\u0010\u0017\u001a\u00020\u00022\u0006\u0010\u0014\u001a\u00020\u00028V@VX\u0096\u008e\u0002¢\u0006\u0012\n\u0004\b\u0015\u0010\u0016\u001a\u0004\b\u0017\u0010\u0012\"\u0004\b\u0018\u0010\u0019R\u001e\u0010\u001b\u001a\n\u0012\u0004\u0012\u00020\u000b\u0018\u00010\u001a8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u001b\u0010\u001c¨\u0006\u001d"}, d2 = {"Landroidx/compose/foundation/BasicTooltipStateImpl;", "Landroidx/compose/foundation/BasicTooltipState;", "", "initialIsVisible", "isPersistent", "Landroidx/compose/foundation/MutatorMutex;", "mutatorMutex", "<init>", "(ZZLandroidx/compose/foundation/MutatorMutex;)V", "Landroidx/compose/foundation/MutatePriority;", "mutatePriority", "Lx6/t0;", "show", "(Landroidx/compose/foundation/MutatePriority;Ld7/d;)Ljava/lang/Object;", "dismiss", "()V", "onDispose", "Z", "()Z", "Landroidx/compose/foundation/MutatorMutex;", "<set-?>", "isVisible$delegate", "Landroidx/compose/runtime/MutableState;", "isVisible", "setVisible", "(Z)V", "Lka/k;", "job", "Lka/k;", "foundation_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
final class BasicTooltipStateImpl implements BasicTooltipState {
    private final boolean isPersistent;

    /* JADX INFO: renamed from: isVisible$delegate, reason: from kotlin metadata */
    private final MutableState isVisible;
    private k<? super t0> job;
    private final MutatorMutex mutatorMutex;

    /* JADX INFO: renamed from: androidx.compose.foundation.BasicTooltipStateImpl$show$2, reason: invalid class name */
    @Metadata(d1 = {"\u0000\b\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0001\u001a\u00020\u0000H\u008a@¢\u0006\u0004\b\u0001\u0010\u0002"}, d2 = {"Lx6/t0;", "<anonymous>", "()V"}, k = 3, mv = {1, 8, 0})
    @e(c = "androidx.compose.foundation.BasicTooltipStateImpl$show$2", f = "BasicTooltip.kt", l = {156, 158}, m = "invokeSuspend")
    public static final class AnonymousClass2 extends j implements l<d<? super t0>, Object> {
        final /* synthetic */ l<d<? super t0>, Object> $cancellableShow;
        int label;

        /* JADX INFO: renamed from: androidx.compose.foundation.BasicTooltipStateImpl$show$2$1, reason: invalid class name */
        @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {1, 8, 0})
        @e(c = "androidx.compose.foundation.BasicTooltipStateImpl$show$2$1", f = "BasicTooltip.kt", l = {159}, m = "invokeSuspend")
        public static final class AnonymousClass1 extends j implements p<k0, d<? super t0>, Object> {
            final /* synthetic */ l<d<? super t0>, Object> $cancellableShow;
            int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            public AnonymousClass1(l<? super d<? super t0>, ? extends Object> lVar, d<? super AnonymousClass1> dVar) {
                super(2, dVar);
                this.$cancellableShow = lVar;
            }

            @Override // f7.a
            public final d<t0> create(Object obj, d<?> dVar) {
                return new AnonymousClass1(this.$cancellableShow, dVar);
            }

            @Override // f7.a
            public final Object invokeSuspend(Object obj) {
                int i10 = this.label;
                if (i10 == 0) {
                    k2.c.G(obj);
                    l<d<? super t0>, Object> lVar = this.$cancellableShow;
                    this.label = 1;
                    Object objInvoke = lVar.invoke(this);
                    e7.a aVar = e7.a.f15033i;
                    if (objInvoke == aVar) {
                        return aVar;
                    }
                } else {
                    if (i10 != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    k2.c.G(obj);
                }
                return t0.f22605a;
            }

            @Override // r7.p
            public final Object invoke(k0 k0Var, d<? super t0> dVar) {
                return ((AnonymousClass1) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public AnonymousClass2(l<? super d<? super t0>, ? extends Object> lVar, d<? super AnonymousClass2> dVar) {
            super(1, dVar);
            this.$cancellableShow = lVar;
        }

        @Override // f7.a
        public final d<t0> create(d<?> dVar) {
            return BasicTooltipStateImpl.this.new AnonymousClass2(this.$cancellableShow, dVar);
        }

        @Override // r7.l
        public final Object invoke(d<? super t0> dVar) {
            return ((AnonymousClass2) create(dVar)).invokeSuspend(t0.f22605a);
        }

        /* JADX WARN: Code restructure failed: missing block: B:20:0x0041, code lost:
        
            if (ka.m0.z(1500, r6, r5) == r0) goto L21;
         */
        @Override // f7.a
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r6) {
            /*
                r5 = this;
                int r0 = r5.label
                r1 = 0
                r2 = 2
                r3 = 1
                if (r0 == 0) goto L19
                if (r0 == r3) goto Lb
                if (r0 != r2) goto L11
            Lb:
                k2.c.G(r6)     // Catch: java.lang.Throwable -> Lf
                goto L44
            Lf:
                r6 = move-exception
                goto L4c
            L11:
                java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
                java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
                r6.<init>(r0)
                throw r6
            L19:
                k2.c.G(r6)
                androidx.compose.foundation.BasicTooltipStateImpl r6 = androidx.compose.foundation.BasicTooltipStateImpl.this     // Catch: java.lang.Throwable -> Lf
                boolean r6 = r6.getIsPersistent()     // Catch: java.lang.Throwable -> Lf
                e7.a r0 = e7.a.f15033i
                if (r6 == 0) goto L31
                r7.l<d7.d<? super x6.t0>, java.lang.Object> r6 = r5.$cancellableShow     // Catch: java.lang.Throwable -> Lf
                r5.label = r3     // Catch: java.lang.Throwable -> Lf
                java.lang.Object r6 = r6.invoke(r5)     // Catch: java.lang.Throwable -> Lf
                if (r6 != r0) goto L44
                goto L43
            L31:
                androidx.compose.foundation.BasicTooltipStateImpl$show$2$1 r6 = new androidx.compose.foundation.BasicTooltipStateImpl$show$2$1     // Catch: java.lang.Throwable -> Lf
                r7.l<d7.d<? super x6.t0>, java.lang.Object> r3 = r5.$cancellableShow     // Catch: java.lang.Throwable -> Lf
                r4 = 0
                r6.<init>(r3, r4)     // Catch: java.lang.Throwable -> Lf
                r5.label = r2     // Catch: java.lang.Throwable -> Lf
                r2 = 1500(0x5dc, double:7.41E-321)
                java.lang.Object r6 = ka.m0.z(r2, r6, r5)     // Catch: java.lang.Throwable -> Lf
                if (r6 != r0) goto L44
            L43:
                return r0
            L44:
                androidx.compose.foundation.BasicTooltipStateImpl r6 = androidx.compose.foundation.BasicTooltipStateImpl.this
                r6.setVisible(r1)
                x6.t0 r6 = x6.t0.f22605a
                return r6
            L4c:
                androidx.compose.foundation.BasicTooltipStateImpl r0 = androidx.compose.foundation.BasicTooltipStateImpl.this
                r0.setVisible(r1)
                throw r6
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.BasicTooltipStateImpl.AnonymousClass2.invokeSuspend(java.lang.Object):java.lang.Object");
        }
    }

    public BasicTooltipStateImpl(boolean z, boolean z5, MutatorMutex mutatorMutex) {
        this.isPersistent = z5;
        this.mutatorMutex = mutatorMutex;
        this.isVisible = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(Boolean.valueOf(z), null, 2, null);
    }

    @Override // androidx.compose.foundation.BasicTooltipState
    public void dismiss() {
        setVisible(false);
    }

    @Override // androidx.compose.foundation.BasicTooltipState
    /* JADX INFO: renamed from: isPersistent, reason: from getter */
    public boolean getIsPersistent() {
        return this.isPersistent;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // androidx.compose.foundation.BasicTooltipState
    public boolean isVisible() {
        return ((Boolean) this.isVisible.getValue()).booleanValue();
    }

    @Override // androidx.compose.foundation.BasicTooltipState
    public void onDispose() {
        k<? super t0> kVar = this.job;
        if (kVar != null) {
            kVar.cancel(null);
        }
    }

    public void setVisible(boolean z) {
        this.isVisible.setValue(Boolean.valueOf(z));
    }

    @Override // androidx.compose.foundation.BasicTooltipState
    public Object show(MutatePriority mutatePriority, d<? super t0> dVar) {
        Object objMutate = this.mutatorMutex.mutate(mutatePriority, new AnonymousClass2(new BasicTooltipStateImpl$show$cancellableShow$1(this, null), null), dVar);
        return objMutate == e7.a.f15033i ? objMutate : t0.f22605a;
    }
}
