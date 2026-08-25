package androidx.compose.animation.core;

import androidx.compose.runtime.MonotonicFrameClockKt;
import f7.j;
import ka.k0;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.l;
import r7.p;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0003\u001a\u00020\u0002\"\u0004\b\u0000\u0010\u0000*\u00020\u0001H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"S", "Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {1, 8, 0})
@f7.e(c = "androidx.compose.animation.core.Transition$animateTo$1$1", f = "Transition.kt", l = {649}, m = "invokeSuspend")
public final class Transition$animateTo$1$1 extends j implements p<k0, d7.d<? super t0>, Object> {
    private /* synthetic */ Object L$0;
    int label;
    final /* synthetic */ Transition<S> this$0;

    /* JADX INFO: renamed from: androidx.compose.animation.core.Transition$animateTo$1$1$1, reason: invalid class name */
    @Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0006\u001a\u00020\u0003\"\u0004\b\u0000\u0010\u00002\u0006\u0010\u0002\u001a\u00020\u0001H\n¢\u0006\u0004\b\u0004\u0010\u0005"}, d2 = {"S", "", "it", "Lx6/t0;", "invoke", "(J)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class AnonymousClass1 extends r implements l<Long, t0> {
        final /* synthetic */ float $durationScale;
        final /* synthetic */ Transition<S> this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(Transition<S> transition, float f10) {
            super(1);
            this.this$0 = transition;
            this.$durationScale = f10;
        }

        @Override // r7.l
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            invoke(((Number) obj).longValue());
            return t0.f22605a;
        }

        /* JADX WARN: Type inference fix 'apply assigned field type' failed
        java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$PrimitiveArg
        	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
        	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
        	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
         */
        public final void invoke(long j10) {
            if (this.this$0.isSeeking()) {
                return;
            }
            this.this$0.onFrame$animation_core_release(j10, this.$durationScale);
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public Transition$animateTo$1$1(Transition<S> transition, d7.d<? super Transition$animateTo$1$1> dVar) {
        super(2, dVar);
        this.this$0 = transition;
    }

    @Override // f7.a
    public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
        Transition$animateTo$1$1 transition$animateTo$1$1 = new Transition$animateTo$1$1(this.this$0, dVar);
        transition$animateTo$1$1.L$0 = obj;
        return transition$animateTo$1$1;
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
        k0 k0Var;
        Object objWithFrameNanos;
        e7.a aVar;
        int i10 = this.label;
        if (i10 == 0) {
            k2.c.G(obj);
            k0Var = (k0) this.L$0;
        } else {
            if (i10 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            k0Var = (k0) this.L$0;
            k2.c.G(obj);
        }
        do {
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.this$0, SuspendAnimationKt.getDurationScale(k0Var.getCoroutineContext()));
            this.L$0 = k0Var;
            this.label = 1;
            objWithFrameNanos = MonotonicFrameClockKt.withFrameNanos(anonymousClass1, this);
            aVar = e7.a.f15033i;
        } while (objWithFrameNanos != aVar);
        return aVar;
    }

    @Override // r7.p
    public final Object invoke(k0 k0Var, d7.d<? super t0> dVar) {
        return ((Transition$animateTo$1$1) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
    }
}
