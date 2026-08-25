package androidx.compose.material3;

import androidx.compose.animation.core.AnimationSpec;
import androidx.compose.animation.core.SuspendAnimationKt;
import androidx.compose.foundation.gestures.DraggableKt;
import androidx.compose.foundation.gestures.Orientation;
import androidx.compose.foundation.interaction.MutableInteractionSource;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.ui.Modifier;
import f7.j;
import ka.k0;
import ka.l0;
import ka.m0;
import kotlin.Metadata;
import kotlin.collections.a0;
import kotlin.jvm.internal.h0;
import na.k;
import r7.l;
import r7.p;
import r7.q;
import r7.r;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000b\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0007\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a>\u0010\b\u001a\b\u0012\u0004\u0012\u00028\u00000\u0007\"\b\b\u0000\u0010\u0001*\u00020\u00002\u001d\u0010\u0006\u001a\u0019\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u0003\u0012\u0004\u0012\u00020\u00040\u0002¢\u0006\u0002\b\u0005H\u0001¢\u0006\u0004\b\b\u0010\t\u001aO\u0010\u0014\u001a\u00020\n\"\u0004\b\u0000\u0010\u0001*\u00020\n2\f\u0010\f\u001a\b\u0012\u0004\u0012\u00028\u00000\u000b2\u0006\u0010\u000e\u001a\u00020\r2\b\b\u0002\u0010\u0010\u001a\u00020\u000f2\b\b\u0002\u0010\u0011\u001a\u00020\u000f2\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u0012H\u0001¢\u0006\u0004\b\u0014\u0010\u0015\u001a(\u0010\u0017\u001a\u00020\u0004\"\u0004\b\u0000\u0010\u0001*\b\u0012\u0004\u0012\u00028\u00000\u000b2\u0006\u0010\u0016\u001a\u00028\u0000H\u0081@¢\u0006\u0004\b\u0017\u0010\u0018\u001a2\u0010\u001b\u001a\u00020\u0004\"\u0004\b\u0000\u0010\u0001*\b\u0012\u0004\u0012\u00028\u00000\u000b2\u0006\u0010\u0016\u001a\u00028\u00002\b\b\u0002\u0010\u001a\u001a\u00020\u0019H\u0081@¢\u0006\u0004\b\u001b\u0010\u001c\u001aH\u0010#\u001a\u00020\u0004\"\u0004\b\u0000\u0010\u001d2\f\u0010\u001f\u001a\b\u0012\u0004\u0012\u00028\u00000\u001e2\"\u0010\"\u001a\u001e\b\u0001\u0012\u0004\u0012\u00028\u0000\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040!\u0012\u0006\u0012\u0004\u0018\u00010\u00000 H\u0082@¢\u0006\u0004\b#\u0010$\u001a\u001b\u0010&\u001a\b\u0012\u0004\u0012\u00028\u00000%\"\u0004\b\u0000\u0010\u0001H\u0002¢\u0006\u0004\b&\u0010'¨\u0006("}, d2 = {"", "T", "Lkotlin/Function1;", "Landroidx/compose/material3/DraggableAnchorsConfig;", "Lx6/t0;", "Lx6/n;", "builder", "Landroidx/compose/material3/DraggableAnchors;", "DraggableAnchors", "(Lr7/l;)Landroidx/compose/material3/DraggableAnchors;", "Landroidx/compose/ui/Modifier;", "Landroidx/compose/material3/AnchoredDraggableState;", "state", "Landroidx/compose/foundation/gestures/Orientation;", "orientation", "", "enabled", "reverseDirection", "Landroidx/compose/foundation/interaction/MutableInteractionSource;", "interactionSource", "anchoredDraggable", "(Landroidx/compose/ui/Modifier;Landroidx/compose/material3/AnchoredDraggableState;Landroidx/compose/foundation/gestures/Orientation;ZZLandroidx/compose/foundation/interaction/MutableInteractionSource;)Landroidx/compose/ui/Modifier;", "targetValue", "snapTo", "(Landroidx/compose/material3/AnchoredDraggableState;Ljava/lang/Object;Ld7/d;)Ljava/lang/Object;", "", "velocity", "animateTo", "(Landroidx/compose/material3/AnchoredDraggableState;Ljava/lang/Object;FLd7/d;)Ljava/lang/Object;", "I", "Lkotlin/Function0;", "inputs", "Lkotlin/Function2;", "Ld7/d;", "block", "restartable", "(Lr7/a;Lr7/p;Ld7/d;)Ljava/lang/Object;", "Landroidx/compose/material3/MapDraggableAnchors;", "emptyDraggableAnchors", "()Landroidx/compose/material3/MapDraggableAnchors;", "material3_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class AnchoredDraggableKt {

    /* JADX INFO: renamed from: androidx.compose.material3.AnchoredDraggableKt$anchoredDraggable$1, reason: invalid class name */
    @Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0005\u001a\u00020\u0004\"\u0004\b\u0000\u0010\u0000*\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0002H\u008a@¢\u0006\u0004\b\u0005\u0010\u0006"}, d2 = {"T", "Lka/k0;", "", "velocity", "Lx6/t0;", "<anonymous>", "(Lka/k0;F)V"}, k = 3, mv = {1, 8, 0})
    @f7.e(c = "androidx.compose.material3.AnchoredDraggableKt$anchoredDraggable$1", f = "AnchoredDraggable.kt", l = {}, m = "invokeSuspend")
    public static final class AnonymousClass1 extends j implements q<k0, Float, d7.d<? super t0>, Object> {
        final /* synthetic */ AnchoredDraggableState<T> $state;
        /* synthetic */ float F$0;
        private /* synthetic */ Object L$0;
        int label;

        /* JADX INFO: renamed from: androidx.compose.material3.AnchoredDraggableKt$anchoredDraggable$1$1, reason: invalid class name and collision with other inner class name */
        @Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0003\u001a\u00020\u0002\"\u0004\b\u0000\u0010\u0000*\u00020\u0001H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"T", "Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {1, 8, 0})
        @f7.e(c = "androidx.compose.material3.AnchoredDraggableKt$anchoredDraggable$1$1", f = "AnchoredDraggable.kt", l = {176}, m = "invokeSuspend")
        public static final class C00811 extends j implements p<k0, d7.d<? super t0>, Object> {
            final /* synthetic */ AnchoredDraggableState<T> $state;
            final /* synthetic */ float $velocity;
            int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public C00811(AnchoredDraggableState<T> anchoredDraggableState, float f10, d7.d<? super C00811> dVar) {
                super(2, dVar);
                this.$state = anchoredDraggableState;
                this.$velocity = f10;
            }

            @Override // f7.a
            public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
                return new C00811(this.$state, this.$velocity, dVar);
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
            @Override // f7.a
            public final Object invokeSuspend(Object obj) {
                int i10 = this.label;
                if (i10 == 0) {
                    k2.c.G(obj);
                    AnchoredDraggableState<T> anchoredDraggableState = this.$state;
                    float f10 = this.$velocity;
                    this.label = 1;
                    Object obj2 = anchoredDraggableState.settle(f10, this);
                    e7.a aVar = e7.a.f15033i;
                    if (obj2 == aVar) {
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
                return ((C00811) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(AnchoredDraggableState<T> anchoredDraggableState, d7.d<? super AnonymousClass1> dVar) {
            super(3, dVar);
            this.$state = anchoredDraggableState;
        }

        @Override // r7.q
        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2, Object obj3) {
            return invoke((k0) obj, ((Number) obj2).floatValue(), (d7.d<? super t0>) obj3);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            k2.c.G(obj);
            m0.p((k0) this.L$0, null, 0, new C00811(this.$state, this.F$0, null), 3);
            return t0.f22605a;
        }

        public final Object invoke(k0 k0Var, float f10, d7.d<? super t0> dVar) {
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.$state, dVar);
            anonymousClass1.L$0 = k0Var;
            anonymousClass1.F$0 = f10;
            return anonymousClass1.invokeSuspend(t0.f22605a);
        }
    }

    /* JADX INFO: Add missing generic type declarations: [T] */
    /* JADX INFO: renamed from: androidx.compose.material3.AnchoredDraggableKt$animateTo$2, reason: invalid class name */
    @Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0010\u0006\u001a\u00020\u0005\"\u0004\b\u0000\u0010\u0000*\u00020\u00012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u00022\u0006\u0010\u0004\u001a\u00028\u0000H\u008a@"}, d2 = {"T", "Landroidx/compose/material3/AnchoredDragScope;", "Landroidx/compose/material3/DraggableAnchors;", "anchors", "latestTarget", "Lx6/t0;", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    @f7.e(c = "androidx.compose.material3.AnchoredDraggableKt$animateTo$2", f = "AnchoredDraggable.kt", l = {696}, m = "invokeSuspend")
    public static final class AnonymousClass2<T> extends j implements r<AnchoredDragScope, DraggableAnchors<T>, T, d7.d<? super t0>, Object> {
        final /* synthetic */ AnchoredDraggableState<T> $this_animateTo;
        final /* synthetic */ float $velocity;
        private /* synthetic */ Object L$0;
        /* synthetic */ Object L$1;
        /* synthetic */ Object L$2;
        int label;

        /* JADX INFO: renamed from: androidx.compose.material3.AnchoredDraggableKt$animateTo$2$1, reason: invalid class name */
        @Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0007\u001a\u00020\u0004\"\u0004\b\u0000\u0010\u00002\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0001H\n¢\u0006\u0004\b\u0005\u0010\u0006"}, d2 = {"T", "", "value", "velocity", "Lx6/t0;", "invoke", "(FF)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
        public static final class AnonymousClass1 extends kotlin.jvm.internal.r implements p<Float, Float, t0> {
            final /* synthetic */ AnchoredDragScope $$this$anchoredDrag;
            final /* synthetic */ h0 $prev;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public AnonymousClass1(AnchoredDragScope anchoredDragScope, h0 h0Var) {
                super(2);
                this.$$this$anchoredDrag = anchoredDragScope;
                this.$prev = h0Var;
            }

            @Override // r7.p
            public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                invoke(((Number) obj).floatValue(), ((Number) obj2).floatValue());
                return t0.f22605a;
            }

            public final void invoke(float f10, float f11) {
                this.$$this$anchoredDrag.dragTo(f10, f11);
                this.$prev.f19742i = f10;
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass2(AnchoredDraggableState<T> anchoredDraggableState, float f10, d7.d<? super AnonymousClass2> dVar) {
            super(4, dVar);
            this.$this_animateTo = anchoredDraggableState;
            this.$velocity = f10;
        }

        @Override // r7.r
        public final Object invoke(AnchoredDragScope anchoredDragScope, DraggableAnchors<T> draggableAnchors, T t2, d7.d<? super t0> dVar) {
            AnonymousClass2 anonymousClass2 = new AnonymousClass2(this.$this_animateTo, this.$velocity, dVar);
            anonymousClass2.L$0 = anchoredDragScope;
            anonymousClass2.L$1 = draggableAnchors;
            anonymousClass2.L$2 = t2;
            return anonymousClass2.invokeSuspend(t0.f22605a);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            int i10 = this.label;
            if (i10 == 0) {
                k2.c.G(obj);
                AnchoredDragScope anchoredDragScope = (AnchoredDragScope) this.L$0;
                float fPositionOf = ((DraggableAnchors) this.L$1).positionOf(this.L$2);
                if (!Float.isNaN(fPositionOf)) {
                    h0 h0Var = new h0();
                    float offset = Float.isNaN(this.$this_animateTo.getOffset()) ? 0.0f : this.$this_animateTo.getOffset();
                    h0Var.f19742i = offset;
                    float f10 = this.$velocity;
                    AnimationSpec<Float> animationSpec = this.$this_animateTo.getAnimationSpec();
                    AnonymousClass1 anonymousClass1 = new AnonymousClass1(anchoredDragScope, h0Var);
                    this.L$0 = null;
                    this.L$1 = null;
                    this.label = 1;
                    Object objAnimate = SuspendAnimationKt.animate(offset, fPositionOf, f10, animationSpec, anonymousClass1, this);
                    e7.a aVar = e7.a.f15033i;
                    if (objAnimate == aVar) {
                        return aVar;
                    }
                }
            } else {
                if (i10 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                k2.c.G(obj);
            }
            return t0.f22605a;
        }
    }

    /* JADX INFO: renamed from: androidx.compose.material3.AnchoredDraggableKt$restartable$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    @f7.e(c = "androidx.compose.material3.AnchoredDraggableKt", f = "AnchoredDraggable.kt", l = {732}, m = "restartable")
    public static final class C04491<I> extends f7.c {
        int label;
        /* synthetic */ Object result;

        public C04491(d7.d<? super C04491> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return AnchoredDraggableKt.restartable(null, null, this);
        }
    }

    /* JADX INFO: renamed from: androidx.compose.material3.AnchoredDraggableKt$restartable$2, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0003\u001a\u00020\u0002\"\u0004\b\u0000\u0010\u0000*\u00020\u0001H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"I", "Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {1, 8, 0})
    @f7.e(c = "androidx.compose.material3.AnchoredDraggableKt$restartable$2", f = "AnchoredDraggable.kt", l = {735}, m = "invokeSuspend")
    public static final class C04502 extends j implements p<k0, d7.d<? super t0>, Object> {
        final /* synthetic */ p<I, d7.d<? super t0>, Object> $block;
        final /* synthetic */ r7.a<I> $inputs;
        private /* synthetic */ Object L$0;
        int label;

        /* JADX INFO: renamed from: androidx.compose.material3.AnchoredDraggableKt$restartable$2$1, reason: invalid class name */
        @Metadata(d1 = {"\u0000\f\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0005\u001a\u00020\u0002\"\u0004\b\u0000\u0010\u00002\u0006\u0010\u0001\u001a\u00028\u0000H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"I", "latestInputs", "Lx6/t0;", "emit", "(Ljava/lang/Object;Ld7/d;)Ljava/lang/Object;", "<anonymous>"}, k = 3, mv = {1, 8, 0})
        public static final class AnonymousClass1<T> implements k {
            final /* synthetic */ k0 $$this$coroutineScope;
            final /* synthetic */ p<I, d7.d<? super t0>, Object> $block;
            final /* synthetic */ kotlin.jvm.internal.k0 $previousDrag;

            /* JADX INFO: renamed from: androidx.compose.material3.AnchoredDraggableKt$restartable$2$1$2, reason: invalid class name and collision with other inner class name */
            @Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0003\u001a\u00020\u0002\"\u0004\b\u0000\u0010\u0000*\u00020\u0001H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"I", "Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {1, 8, 0})
            @f7.e(c = "androidx.compose.material3.AnchoredDraggableKt$restartable$2$1$2", f = "AnchoredDraggable.kt", l = {741}, m = "invokeSuspend")
            public static final class C00822 extends j implements p<k0, d7.d<? super t0>, Object> {
                final /* synthetic */ k0 $$this$coroutineScope;
                final /* synthetic */ p<I, d7.d<? super t0>, Object> $block;
                final /* synthetic */ I $latestInputs;
                int label;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                /* JADX WARN: Multi-variable type inference failed */
                public C00822(p<? super I, ? super d7.d<? super t0>, ? extends Object> pVar, I i10, k0 k0Var, d7.d<? super C00822> dVar) {
                    super(2, dVar);
                    this.$block = pVar;
                    this.$latestInputs = i10;
                    this.$$this$coroutineScope = k0Var;
                }

                @Override // f7.a
                public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
                    return new C00822(this.$block, this.$latestInputs, this.$$this$coroutineScope, dVar);
                }

                /* JADX WARN: Type inference fix 'apply assigned field type' failed
                java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
                	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
                	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
                	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
                	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
                	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
                	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
                	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
                 */
                @Override // f7.a
                public final Object invokeSuspend(Object obj) {
                    int i10 = this.label;
                    if (i10 == 0) {
                        k2.c.G(obj);
                        p<I, d7.d<? super t0>, Object> pVar = this.$block;
                        I i11 = this.$latestInputs;
                        this.label = 1;
                        Object objInvoke = pVar.invoke(i11, this);
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
                    l0.b(this.$$this$coroutineScope, new AnchoredDragFinishedSignal());
                    return t0.f22605a;
                }

                @Override // r7.p
                public final Object invoke(k0 k0Var, d7.d<? super t0> dVar) {
                    return ((C00822) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
                }
            }

            /* JADX WARN: Multi-variable type inference failed */
            public AnonymousClass1(kotlin.jvm.internal.k0 k0Var, k0 k0Var2, p<? super I, ? super d7.d<? super t0>, ? extends Object> pVar) {
                this.$previousDrag = k0Var;
                this.$$this$coroutineScope = k0Var2;
                this.$block = pVar;
            }

            /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
            @Override // na.k
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public final java.lang.Object emit(I r6, d7.d<? super x6.t0> r7) {
                /*
                    r5 = this;
                    boolean r0 = r7 instanceof androidx.compose.material3.AnchoredDraggableKt$restartable$2$1$emit$1
                    if (r0 == 0) goto L13
                    r0 = r7
                    androidx.compose.material3.AnchoredDraggableKt$restartable$2$1$emit$1 r0 = (androidx.compose.material3.AnchoredDraggableKt$restartable$2$1$emit$1) r0
                    int r1 = r0.label
                    r2 = -2147483648(0xffffffff80000000, float:-0.0)
                    r3 = r1 & r2
                    if (r3 == 0) goto L13
                    int r1 = r1 - r2
                    r0.label = r1
                    goto L18
                L13:
                    androidx.compose.material3.AnchoredDraggableKt$restartable$2$1$emit$1 r0 = new androidx.compose.material3.AnchoredDraggableKt$restartable$2$1$emit$1
                    r0.<init>(r5, r7)
                L18:
                    java.lang.Object r7 = r0.result
                    int r1 = r0.label
                    r2 = 1
                    if (r1 == 0) goto L37
                    if (r1 != r2) goto L2f
                    java.lang.Object r6 = r0.L$2
                    ka.v1 r6 = (ka.v1) r6
                    java.lang.Object r6 = r0.L$1
                    java.lang.Object r0 = r0.L$0
                    androidx.compose.material3.AnchoredDraggableKt$restartable$2$1 r0 = (androidx.compose.material3.AnchoredDraggableKt.C04502.AnonymousClass1) r0
                    k2.c.G(r7)
                    goto L5c
                L2f:
                    java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
                    java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
                    r6.<init>(r7)
                    throw r6
                L37:
                    k2.c.G(r7)
                    kotlin.jvm.internal.k0 r7 = r5.$previousDrag
                    java.lang.Object r7 = r7.f19746i
                    ka.v1 r7 = (ka.v1) r7
                    if (r7 == 0) goto L5b
                    androidx.compose.material3.AnchoredDragFinishedSignal r1 = new androidx.compose.material3.AnchoredDragFinishedSignal
                    r1.<init>()
                    r7.cancel(r1)
                    r0.L$0 = r5
                    r0.L$1 = r6
                    r0.L$2 = r7
                    r0.label = r2
                    java.lang.Object r7 = r7.join(r0)
                    e7.a r0 = e7.a.f15033i
                    if (r7 != r0) goto L5b
                    return r0
                L5b:
                    r0 = r5
                L5c:
                    kotlin.jvm.internal.k0 r7 = r0.$previousDrag
                    ka.k0 r1 = r0.$$this$coroutineScope
                    androidx.compose.material3.AnchoredDraggableKt$restartable$2$1$2 r3 = new androidx.compose.material3.AnchoredDraggableKt$restartable$2$1$2
                    r7.p<I, d7.d<? super x6.t0>, java.lang.Object> r0 = r0.$block
                    r4 = 0
                    r3.<init>(r0, r6, r1, r4)
                    r6 = 4
                    ka.o2 r6 = ka.m0.p(r1, r4, r6, r3, r2)
                    r7.f19746i = r6
                    x6.t0 r6 = x6.t0.f22605a
                    return r6
                */
                throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material3.AnchoredDraggableKt.C04502.AnonymousClass1.emit(java.lang.Object, d7.d):java.lang.Object");
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public C04502(r7.a<? extends I> aVar, p<? super I, ? super d7.d<? super t0>, ? extends Object> pVar, d7.d<? super C04502> dVar) {
            super(2, dVar);
            this.$inputs = aVar;
            this.$block = pVar;
        }

        @Override // f7.a
        public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
            C04502 c04502 = new C04502(this.$inputs, this.$block, dVar);
            c04502.L$0 = obj;
            return c04502;
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            int i10 = this.label;
            if (i10 == 0) {
                k2.c.G(obj);
                k0 k0Var = (k0) this.L$0;
                kotlin.jvm.internal.k0 k0Var2 = new kotlin.jvm.internal.k0();
                na.j jVarSnapshotFlow = SnapshotStateKt.snapshotFlow(this.$inputs);
                AnonymousClass1 anonymousClass1 = new AnonymousClass1(k0Var2, k0Var, this.$block);
                this.label = 1;
                Object objCollect = jVarSnapshotFlow.collect(anonymousClass1, this);
                e7.a aVar = e7.a.f15033i;
                if (objCollect == aVar) {
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
            return ((C04502) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
        }
    }

    /* JADX INFO: Add missing generic type declarations: [T] */
    /* JADX INFO: renamed from: androidx.compose.material3.AnchoredDraggableKt$snapTo$2, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0010\u0006\u001a\u00020\u0005\"\u0004\b\u0000\u0010\u0000*\u00020\u00012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u00022\u0006\u0010\u0004\u001a\u00028\u0000H\u008a@"}, d2 = {"T", "Landroidx/compose/material3/AnchoredDragScope;", "Landroidx/compose/material3/DraggableAnchors;", "anchors", "latestTarget", "Lx6/t0;", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    @f7.e(c = "androidx.compose.material3.AnchoredDraggableKt$snapTo$2", f = "AnchoredDraggable.kt", l = {}, m = "invokeSuspend")
    public static final class C04512<T> extends j implements r<AnchoredDragScope, DraggableAnchors<T>, T, d7.d<? super t0>, Object> {
        private /* synthetic */ Object L$0;
        /* synthetic */ Object L$1;
        /* synthetic */ Object L$2;
        int label;

        public C04512(d7.d<? super C04512> dVar) {
            super(4, dVar);
        }

        @Override // r7.r
        public final Object invoke(AnchoredDragScope anchoredDragScope, DraggableAnchors<T> draggableAnchors, T t2, d7.d<? super t0> dVar) {
            C04512 c04512 = new C04512(dVar);
            c04512.L$0 = anchoredDragScope;
            c04512.L$1 = draggableAnchors;
            c04512.L$2 = t2;
            return c04512.invokeSuspend(t0.f22605a);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            k2.c.G(obj);
            AnchoredDragScope anchoredDragScope = (AnchoredDragScope) this.L$0;
            float fPositionOf = ((DraggableAnchors) this.L$1).positionOf(this.L$2);
            if (!Float.isNaN(fPositionOf)) {
                a.a(anchoredDragScope, fPositionOf, 0.0f, 2, null);
            }
            return t0.f22605a;
        }
    }

    public static final <T> DraggableAnchors<T> DraggableAnchors(l<? super DraggableAnchorsConfig<T>, t0> lVar) {
        DraggableAnchorsConfig draggableAnchorsConfig = new DraggableAnchorsConfig();
        lVar.invoke(draggableAnchorsConfig);
        return new MapDraggableAnchors(draggableAnchorsConfig.getAnchors$material3_release());
    }

    public static final <T> Modifier anchoredDraggable(Modifier modifier, AnchoredDraggableState<T> anchoredDraggableState, Orientation orientation, boolean z, boolean z5, MutableInteractionSource mutableInteractionSource) {
        return DraggableKt.draggable$default(modifier, anchoredDraggableState.getDraggableState(), orientation, z, mutableInteractionSource, anchoredDraggableState.isAnimationRunning(), null, new AnonymousClass1(anchoredDraggableState, null), z5, 32, null);
    }

    public static /* synthetic */ Modifier anchoredDraggable$default(Modifier modifier, AnchoredDraggableState anchoredDraggableState, Orientation orientation, boolean z, boolean z5, MutableInteractionSource mutableInteractionSource, int i10, Object obj) {
        if ((i10 & 4) != 0) {
            z = true;
        }
        boolean z10 = z;
        if ((i10 & 8) != 0) {
            z5 = false;
        }
        boolean z11 = z5;
        if ((i10 & 16) != 0) {
            mutableInteractionSource = null;
        }
        return anchoredDraggable(modifier, anchoredDraggableState, orientation, z10, z11, mutableInteractionSource);
    }

    public static final <T> Object animateTo(AnchoredDraggableState<T> anchoredDraggableState, T t2, float f10, d7.d<? super t0> dVar) {
        Object objAnchoredDrag$default = AnchoredDraggableState.anchoredDrag$default(anchoredDraggableState, t2, null, new AnonymousClass2(anchoredDraggableState, f10, null), dVar, 2, null);
        return objAnchoredDrag$default == e7.a.f15033i ? objAnchoredDrag$default : t0.f22605a;
    }

    public static /* synthetic */ Object animateTo$default(AnchoredDraggableState anchoredDraggableState, Object obj, float f10, d7.d dVar, int i10, Object obj2) {
        if ((i10 & 2) != 0) {
            f10 = anchoredDraggableState.getLastVelocity();
        }
        return animateTo(anchoredDraggableState, obj, f10, dVar);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final <T> MapDraggableAnchors<T> emptyDraggableAnchors() {
        return new MapDraggableAnchors<>(a0.f19683i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final <I> java.lang.Object restartable(r7.a<? extends I> r4, r7.p<? super I, ? super d7.d<? super x6.t0>, ? extends java.lang.Object> r5, d7.d<? super x6.t0> r6) {
        /*
            boolean r0 = r6 instanceof androidx.compose.material3.AnchoredDraggableKt.C04491
            if (r0 == 0) goto L13
            r0 = r6
            androidx.compose.material3.AnchoredDraggableKt$restartable$1 r0 = (androidx.compose.material3.AnchoredDraggableKt.C04491) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            androidx.compose.material3.AnchoredDraggableKt$restartable$1 r0 = new androidx.compose.material3.AnchoredDraggableKt$restartable$1
            r0.<init>(r6)
        L18:
            java.lang.Object r6 = r0.result
            int r1 = r0.label
            r2 = 1
            if (r1 == 0) goto L2d
            if (r1 != r2) goto L25
            k2.c.G(r6)     // Catch: androidx.compose.material3.AnchoredDragFinishedSignal -> L41
            goto L41
        L25:
            java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
            java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
            r4.<init>(r5)
            throw r4
        L2d:
            k2.c.G(r6)
            androidx.compose.material3.AnchoredDraggableKt$restartable$2 r6 = new androidx.compose.material3.AnchoredDraggableKt$restartable$2     // Catch: androidx.compose.material3.AnchoredDragFinishedSignal -> L41
            r1 = 0
            r6.<init>(r4, r5, r1)     // Catch: androidx.compose.material3.AnchoredDragFinishedSignal -> L41
            r0.label = r2     // Catch: androidx.compose.material3.AnchoredDragFinishedSignal -> L41
            java.lang.Object r4 = ka.l0.c(r6, r0)     // Catch: androidx.compose.material3.AnchoredDragFinishedSignal -> L41
            e7.a r5 = e7.a.f15033i
            if (r4 != r5) goto L41
            return r5
        L41:
            x6.t0 r4 = x6.t0.f22605a
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material3.AnchoredDraggableKt.restartable(r7.a, r7.p, d7.d):java.lang.Object");
    }

    public static final <T> Object snapTo(AnchoredDraggableState<T> anchoredDraggableState, T t2, d7.d<? super t0> dVar) {
        Object objAnchoredDrag$default = AnchoredDraggableState.anchoredDrag$default(anchoredDraggableState, t2, null, new C04512(null), dVar, 2, null);
        return objAnchoredDrag$default == e7.a.f15033i ? objAnchoredDrag$default : t0.f22605a;
    }
}
