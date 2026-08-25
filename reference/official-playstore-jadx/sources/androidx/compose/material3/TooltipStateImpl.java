package androidx.compose.material3;

import androidx.compose.animation.core.MutableTransitionState;
import androidx.compose.foundation.MutatePriority;
import androidx.compose.foundation.MutatorMutex;
import f7.j;
import ka.k;
import ka.k0;
import kotlin.Metadata;
import r7.l;
import r7.p;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0003\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0002\u0012\u0006\u0010\u0006\u001a\u00020\u0005¢\u0006\u0004\b\u0007\u0010\bJ\u0018\u0010\f\u001a\u00020\u000b2\u0006\u0010\n\u001a\u00020\tH\u0096@¢\u0006\u0004\b\f\u0010\rJ\u000f\u0010\u000e\u001a\u00020\u000bH\u0016¢\u0006\u0004\b\u000e\u0010\u000fJ\u000f\u0010\u0010\u001a\u00020\u000bH\u0016¢\u0006\u0004\b\u0010\u0010\u000fR\u001a\u0010\u0004\u001a\u00020\u00028\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\u0004\u0010\u0011\u001a\u0004\b\u0004\u0010\u0012R\u0014\u0010\u0006\u001a\u00020\u00058\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0006\u0010\u0013R \u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00020\u00148\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\u0015\u0010\u0016\u001a\u0004\b\u0017\u0010\u0018R\u001e\u0010\u001a\u001a\n\u0012\u0004\u0012\u00020\u000b\u0018\u00010\u00198\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u001a\u0010\u001bR\u0014\u0010\u001c\u001a\u00020\u00028VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u001c\u0010\u0012¨\u0006\u001d"}, d2 = {"Landroidx/compose/material3/TooltipStateImpl;", "Landroidx/compose/material3/TooltipState;", "", "initialIsVisible", "isPersistent", "Landroidx/compose/foundation/MutatorMutex;", "mutatorMutex", "<init>", "(ZZLandroidx/compose/foundation/MutatorMutex;)V", "Landroidx/compose/foundation/MutatePriority;", "mutatePriority", "Lx6/t0;", "show", "(Landroidx/compose/foundation/MutatePriority;Ld7/d;)Ljava/lang/Object;", "dismiss", "()V", "onDispose", "Z", "()Z", "Landroidx/compose/foundation/MutatorMutex;", "Landroidx/compose/animation/core/MutableTransitionState;", "transition", "Landroidx/compose/animation/core/MutableTransitionState;", "getTransition", "()Landroidx/compose/animation/core/MutableTransitionState;", "Lka/k;", "job", "Lka/k;", "isVisible", "material3_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
final class TooltipStateImpl implements TooltipState {
    private final boolean isPersistent;
    private k<? super t0> job;
    private final MutatorMutex mutatorMutex;
    private final MutableTransitionState<Boolean> transition;

    /* JADX INFO: renamed from: androidx.compose.material3.TooltipStateImpl$show$2, reason: invalid class name */
    @Metadata(d1 = {"\u0000\b\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0001\u001a\u00020\u0000H\u008a@¢\u0006\u0004\b\u0001\u0010\u0002"}, d2 = {"Lx6/t0;", "<anonymous>", "()V"}, k = 3, mv = {1, 8, 0})
    @f7.e(c = "androidx.compose.material3.TooltipStateImpl$show$2", f = "Tooltip.kt", l = {590, 592}, m = "invokeSuspend")
    public static final class AnonymousClass2 extends j implements l<d7.d<? super t0>, Object> {
        final /* synthetic */ l<d7.d<? super t0>, Object> $cancellableShow;
        int label;

        /* JADX INFO: renamed from: androidx.compose.material3.TooltipStateImpl$show$2$1, reason: invalid class name */
        @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {1, 8, 0})
        @f7.e(c = "androidx.compose.material3.TooltipStateImpl$show$2$1", f = "Tooltip.kt", l = {593}, m = "invokeSuspend")
        public static final class AnonymousClass1 extends j implements p<k0, d7.d<? super t0>, Object> {
            final /* synthetic */ l<d7.d<? super t0>, Object> $cancellableShow;
            int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            public AnonymousClass1(l<? super d7.d<? super t0>, ? extends Object> lVar, d7.d<? super AnonymousClass1> dVar) {
                super(2, dVar);
                this.$cancellableShow = lVar;
            }

            @Override // f7.a
            public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
                return new AnonymousClass1(this.$cancellableShow, dVar);
            }

            @Override // f7.a
            public final Object invokeSuspend(Object obj) {
                int i10 = this.label;
                if (i10 == 0) {
                    k2.c.G(obj);
                    l<d7.d<? super t0>, Object> lVar = this.$cancellableShow;
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
            public final Object invoke(k0 k0Var, d7.d<? super t0> dVar) {
                return ((AnonymousClass1) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public AnonymousClass2(l<? super d7.d<? super t0>, ? extends Object> lVar, d7.d<? super AnonymousClass2> dVar) {
            super(1, dVar);
            this.$cancellableShow = lVar;
        }

        @Override // f7.a
        public final d7.d<t0> create(d7.d<?> dVar) {
            return TooltipStateImpl.this.new AnonymousClass2(this.$cancellableShow, dVar);
        }

        @Override // r7.l
        public final Object invoke(d7.d<? super t0> dVar) {
            return ((AnonymousClass2) create(dVar)).invokeSuspend(t0.f22605a);
        }

        /* JADX WARN: Code restructure failed: missing block: B:20:0x0040, code lost:
        
            if (ka.m0.z(1500, r5, r4) == r0) goto L21;
         */
        @Override // f7.a
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r5) {
            /*
                r4 = this;
                int r0 = r4.label
                r1 = 2
                r2 = 1
                if (r0 == 0) goto L18
                if (r0 == r2) goto La
                if (r0 != r1) goto L10
            La:
                k2.c.G(r5)     // Catch: java.lang.Throwable -> Le
                goto L43
            Le:
                r5 = move-exception
                goto L4b
            L10:
                java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
                java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
                r5.<init>(r0)
                throw r5
            L18:
                k2.c.G(r5)
                androidx.compose.material3.TooltipStateImpl r5 = androidx.compose.material3.TooltipStateImpl.this     // Catch: java.lang.Throwable -> Le
                boolean r5 = r5.getIsPersistent()     // Catch: java.lang.Throwable -> Le
                e7.a r0 = e7.a.f15033i
                if (r5 == 0) goto L30
                r7.l<d7.d<? super x6.t0>, java.lang.Object> r5 = r4.$cancellableShow     // Catch: java.lang.Throwable -> Le
                r4.label = r2     // Catch: java.lang.Throwable -> Le
                java.lang.Object r5 = r5.invoke(r4)     // Catch: java.lang.Throwable -> Le
                if (r5 != r0) goto L43
                goto L42
            L30:
                androidx.compose.material3.TooltipStateImpl$show$2$1 r5 = new androidx.compose.material3.TooltipStateImpl$show$2$1     // Catch: java.lang.Throwable -> Le
                r7.l<d7.d<? super x6.t0>, java.lang.Object> r2 = r4.$cancellableShow     // Catch: java.lang.Throwable -> Le
                r3 = 0
                r5.<init>(r2, r3)     // Catch: java.lang.Throwable -> Le
                r4.label = r1     // Catch: java.lang.Throwable -> Le
                r1 = 1500(0x5dc, double:7.41E-321)
                java.lang.Object r5 = ka.m0.z(r1, r5, r4)     // Catch: java.lang.Throwable -> Le
                if (r5 != r0) goto L43
            L42:
                return r0
            L43:
                androidx.compose.material3.TooltipStateImpl r5 = androidx.compose.material3.TooltipStateImpl.this
                r5.dismiss()
                x6.t0 r5 = x6.t0.f22605a
                return r5
            L4b:
                androidx.compose.material3.TooltipStateImpl r0 = androidx.compose.material3.TooltipStateImpl.this
                r0.dismiss()
                throw r5
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material3.TooltipStateImpl.AnonymousClass2.invokeSuspend(java.lang.Object):java.lang.Object");
        }
    }

    public TooltipStateImpl(boolean z, boolean z5, MutatorMutex mutatorMutex) {
        this.isPersistent = z5;
        this.mutatorMutex = mutatorMutex;
        this.transition = new MutableTransitionState<>(Boolean.valueOf(z));
    }

    @Override // androidx.compose.material3.BasicTooltipState
    public void dismiss() {
        getTransition().setTargetState(Boolean.FALSE);
    }

    @Override // androidx.compose.material3.TooltipState
    public MutableTransitionState<Boolean> getTransition() {
        return this.transition;
    }

    @Override // androidx.compose.material3.BasicTooltipState
    /* JADX INFO: renamed from: isPersistent, reason: from getter */
    public boolean getIsPersistent() {
        return this.isPersistent;
    }

    @Override // androidx.compose.material3.BasicTooltipState
    /* JADX INFO: renamed from: isVisible */
    public boolean getIsVisible() {
        return getTransition().getCurrentState().booleanValue() || getTransition().getTargetState().booleanValue();
    }

    @Override // androidx.compose.material3.BasicTooltipState
    public void onDispose() {
        k<? super t0> kVar = this.job;
        if (kVar != null) {
            kVar.cancel(null);
        }
    }

    @Override // androidx.compose.material3.BasicTooltipState
    public Object show(MutatePriority mutatePriority, d7.d<? super t0> dVar) {
        Object objMutate = this.mutatorMutex.mutate(mutatePriority, new AnonymousClass2(new TooltipStateImpl$show$cancellableShow$1(this, null), null), dVar);
        return objMutate == e7.a.f15033i ? objMutate : t0.f22605a;
    }
}
