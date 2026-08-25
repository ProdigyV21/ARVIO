package androidx.compose.foundation.gestures;

import androidx.compose.foundation.MutatePriority;
import androidx.compose.foundation.MutatorMutex;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.SnapshotStateKt__SnapshotStateKt;
import androidx.compose.ui.geometry.Offset;
import f7.j;
import io.ktor.http.ContentDisposition;
import ka.k0;
import ka.l0;
import kotlin.Metadata;
import r7.p;
import r7.q;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\b\u0002\u0018\u00002\u00020\u0001BT\u0012K\u0010\u000b\u001aG\u0012\u0013\u0012\u00110\u0003¢\u0006\f\b\u0004\u0012\b\b\u0005\u0012\u0004\b\b(\u0006\u0012\u0013\u0012\u00110\u0007¢\u0006\f\b\u0004\u0012\b\b\u0005\u0012\u0004\b\b(\b\u0012\u0013\u0012\u00110\u0003¢\u0006\f\b\u0004\u0012\b\b\u0005\u0012\u0004\b\b(\t\u0012\u0004\u0012\u00020\n0\u0002¢\u0006\u0004\b\f\u0010\rJA\u0010\u0016\u001a\u00020\n2\u0006\u0010\u000f\u001a\u00020\u000e2'\u0010\u0015\u001a#\b\u0001\u0012\u0004\u0012\u00020\u0011\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\u0012\u0012\u0006\u0012\u0004\u0018\u00010\u00130\u0010¢\u0006\u0002\b\u0014H\u0096@¢\u0006\u0004\b\u0016\u0010\u0017R\\\u0010\u000b\u001aG\u0012\u0013\u0012\u00110\u0003¢\u0006\f\b\u0004\u0012\b\b\u0005\u0012\u0004\b\b(\u0006\u0012\u0013\u0012\u00110\u0007¢\u0006\f\b\u0004\u0012\b\b\u0005\u0012\u0004\b\b(\b\u0012\u0013\u0012\u00110\u0003¢\u0006\f\b\u0004\u0012\b\b\u0005\u0012\u0004\b\b(\t\u0012\u0004\u0012\u00020\n0\u00028\u0006¢\u0006\f\n\u0004\b\u000b\u0010\u0018\u001a\u0004\b\u0019\u0010\u001aR\u0014\u0010\u001b\u001a\u00020\u00118\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u001b\u0010\u001cR\u0014\u0010\u001e\u001a\u00020\u001d8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u001e\u0010\u001fR\u001a\u0010\"\u001a\b\u0012\u0004\u0012\u00020!0 8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\"\u0010#R\u0014\u0010$\u001a\u00020!8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b$\u0010%¨\u0006&"}, d2 = {"Landroidx/compose/foundation/gestures/DefaultTransformableState;", "Landroidx/compose/foundation/gestures/TransformableState;", "Lkotlin/Function3;", "", "Lx6/y;", ContentDisposition.Parameters.Name, "zoomChange", "Landroidx/compose/ui/geometry/Offset;", "panChange", "rotationChange", "Lx6/t0;", "onTransformation", "<init>", "(Lr7/q;)V", "Landroidx/compose/foundation/MutatePriority;", "transformPriority", "Lkotlin/Function2;", "Landroidx/compose/foundation/gestures/TransformScope;", "Ld7/d;", "", "Lx6/n;", "block", "transform", "(Landroidx/compose/foundation/MutatePriority;Lr7/p;Ld7/d;)Ljava/lang/Object;", "Lr7/q;", "getOnTransformation", "()Lr7/q;", "transformScope", "Landroidx/compose/foundation/gestures/TransformScope;", "Landroidx/compose/foundation/MutatorMutex;", "transformMutex", "Landroidx/compose/foundation/MutatorMutex;", "Landroidx/compose/runtime/MutableState;", "", "isTransformingState", "Landroidx/compose/runtime/MutableState;", "isTransformInProgress", "()Z", "foundation_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
final class DefaultTransformableState implements TransformableState {
    private final q<Float, Offset, Float, t0> onTransformation;
    private final TransformScope transformScope = new TransformScope() { // from class: androidx.compose.foundation.gestures.DefaultTransformableState$transformScope$1
        @Override // androidx.compose.foundation.gestures.TransformScope
        /* JADX INFO: renamed from: transformBy-d-4ec7I, reason: not valid java name */
        public void mo302transformByd4ec7I(float zoomChange, long panChange, float rotationChange) {
            this.this$0.getOnTransformation().invoke(Float.valueOf(zoomChange), Offset.m3225boximpl(panChange), Float.valueOf(rotationChange));
        }
    };
    private final MutatorMutex transformMutex = new MutatorMutex();
    private final MutableState<Boolean> isTransformingState = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(Boolean.FALSE, null, 2, null);

    /* JADX INFO: renamed from: androidx.compose.foundation.gestures.DefaultTransformableState$transform$2, reason: invalid class name */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {1, 8, 0})
    @f7.e(c = "androidx.compose.foundation.gestures.DefaultTransformableState$transform$2", f = "TransformableState.kt", l = {249}, m = "invokeSuspend")
    public static final class AnonymousClass2 extends j implements p<k0, d7.d<? super t0>, Object> {
        final /* synthetic */ p<TransformScope, d7.d<? super t0>, Object> $block;
        final /* synthetic */ MutatePriority $transformPriority;
        int label;

        /* JADX INFO: renamed from: androidx.compose.foundation.gestures.DefaultTransformableState$transform$2$1, reason: invalid class name */
        @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Landroidx/compose/foundation/gestures/TransformScope;", "Lx6/t0;", "<anonymous>", "(Landroidx/compose/foundation/gestures/TransformScope;)V"}, k = 3, mv = {1, 8, 0})
        @f7.e(c = "androidx.compose.foundation.gestures.DefaultTransformableState$transform$2$1", f = "TransformableState.kt", l = {252}, m = "invokeSuspend")
        public static final class AnonymousClass1 extends j implements p<TransformScope, d7.d<? super t0>, Object> {
            final /* synthetic */ p<TransformScope, d7.d<? super t0>, Object> $block;
            private /* synthetic */ Object L$0;
            int label;
            final /* synthetic */ DefaultTransformableState this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            public AnonymousClass1(DefaultTransformableState defaultTransformableState, p<? super TransformScope, ? super d7.d<? super t0>, ? extends Object> pVar, d7.d<? super AnonymousClass1> dVar) {
                super(2, dVar);
                this.this$0 = defaultTransformableState;
                this.$block = pVar;
            }

            @Override // f7.a
            public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
                AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.this$0, this.$block, dVar);
                anonymousClass1.L$0 = obj;
                return anonymousClass1;
            }

            @Override // r7.p
            public final Object invoke(TransformScope transformScope, d7.d<? super t0> dVar) {
                return ((AnonymousClass1) create(transformScope, dVar)).invokeSuspend(t0.f22605a);
            }

            @Override // f7.a
            public final Object invokeSuspend(Object obj) {
                int i10 = this.label;
                try {
                    if (i10 == 0) {
                        k2.c.G(obj);
                        TransformScope transformScope = (TransformScope) this.L$0;
                        this.this$0.isTransformingState.setValue(Boolean.TRUE);
                        p<TransformScope, d7.d<? super t0>, Object> pVar = this.$block;
                        this.label = 1;
                        Object objInvoke = pVar.invoke(transformScope, this);
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
                    this.this$0.isTransformingState.setValue(Boolean.FALSE);
                    return t0.f22605a;
                } catch (Throwable th) {
                    this.this$0.isTransformingState.setValue(Boolean.FALSE);
                    throw th;
                }
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public AnonymousClass2(MutatePriority mutatePriority, p<? super TransformScope, ? super d7.d<? super t0>, ? extends Object> pVar, d7.d<? super AnonymousClass2> dVar) {
            super(2, dVar);
            this.$transformPriority = mutatePriority;
            this.$block = pVar;
        }

        @Override // f7.a
        public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
            return DefaultTransformableState.this.new AnonymousClass2(this.$transformPriority, this.$block, dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            int i10 = this.label;
            if (i10 == 0) {
                k2.c.G(obj);
                MutatorMutex mutatorMutex = DefaultTransformableState.this.transformMutex;
                TransformScope transformScope = DefaultTransformableState.this.transformScope;
                MutatePriority mutatePriority = this.$transformPriority;
                AnonymousClass1 anonymousClass1 = new AnonymousClass1(DefaultTransformableState.this, this.$block, null);
                this.label = 1;
                Object objMutateWith = mutatorMutex.mutateWith(transformScope, mutatePriority, anonymousClass1, this);
                e7.a aVar = e7.a.f15033i;
                if (objMutateWith == aVar) {
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
            return ((AnonymousClass2) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public DefaultTransformableState(q<? super Float, ? super Offset, ? super Float, t0> qVar) {
        this.onTransformation = qVar;
    }

    public final q<Float, Offset, Float, t0> getOnTransformation() {
        return this.onTransformation;
    }

    @Override // androidx.compose.foundation.gestures.TransformableState
    public boolean isTransformInProgress() {
        return this.isTransformingState.getValue().booleanValue();
    }

    @Override // androidx.compose.foundation.gestures.TransformableState
    public Object transform(MutatePriority mutatePriority, p<? super TransformScope, ? super d7.d<? super t0>, ? extends Object> pVar, d7.d<? super t0> dVar) {
        Object objC = l0.c(new AnonymousClass2(mutatePriority, pVar, null), dVar);
        return objC == e7.a.f15033i ? objC : t0.f22605a;
    }
}
