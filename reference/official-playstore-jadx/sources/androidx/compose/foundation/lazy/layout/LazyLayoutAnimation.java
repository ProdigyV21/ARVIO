package androidx.compose.foundation.lazy.layout;

import androidx.compose.animation.core.Animatable;
import androidx.compose.animation.core.AnimationVector1D;
import androidx.compose.animation.core.AnimationVector2D;
import androidx.compose.animation.core.FiniteAnimationSpec;
import androidx.compose.animation.core.VectorConvertersKt;
import androidx.compose.runtime.MutableFloatState;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.PrimitiveSnapshotStateKt;
import androidx.compose.runtime.SnapshotStateKt__SnapshotStateKt;
import androidx.compose.ui.graphics.GraphicsLayerScope;
import androidx.compose.ui.unit.IntOffset;
import androidx.compose.ui.unit.IntOffsetKt;
import f7.e;
import f7.j;
import ka.k0;
import ka.m0;
import kotlin.Metadata;
import kotlin.jvm.internal.h;
import kotlin.jvm.internal.i;
import kotlin.jvm.internal.r;
import r7.l;
import r7.p;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\u0010\u0007\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u000f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\t\b\u0000\u0018\u0000 I2\u00020\u0001:\u0001IB\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\r\u0010\u0007\u001a\u00020\u0006¢\u0006\u0004\b\u0007\u0010\bJ\u0018\u0010\r\u001a\u00020\u00062\u0006\u0010\n\u001a\u00020\tø\u0001\u0000¢\u0006\u0004\b\u000b\u0010\fJ\r\u0010\u000e\u001a\u00020\u0006¢\u0006\u0004\b\u000e\u0010\bJ\r\u0010\u000f\u001a\u00020\u0006¢\u0006\u0004\b\u000f\u0010\bR\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\u0010\u001a\u0004\b\u0011\u0010\u0012R*\u0010\u0015\u001a\n\u0012\u0004\u0012\u00020\u0014\u0018\u00010\u00138\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0015\u0010\u0016\u001a\u0004\b\u0017\u0010\u0018\"\u0004\b\u0019\u0010\u001aR*\u0010\u001b\u001a\n\u0012\u0004\u0012\u00020\t\u0018\u00010\u00138\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u001b\u0010\u0016\u001a\u0004\b\u001c\u0010\u0018\"\u0004\b\u001d\u0010\u001aR+\u0010\"\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020\u001e8F@BX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\b \u0010!\u001a\u0004\b\"\u0010#\"\u0004\b$\u0010%R+\u0010'\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020\u001e8F@BX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\b&\u0010!\u001a\u0004\b'\u0010#\"\u0004\b(\u0010%R(\u0010)\u001a\u00020\t8\u0006@\u0006X\u0086\u000eø\u0001\u0000ø\u0001\u0001¢\u0006\u0012\n\u0004\b)\u0010*\u001a\u0004\b+\u0010,\"\u0004\b-\u0010\fR \u00100\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020/0.8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b0\u00101R \u00103\u001a\u000e\u0012\u0004\u0012\u00020\u0014\u0012\u0004\u0012\u0002020.8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b3\u00101R1\u00107\u001a\u00020\t2\u0006\u0010\u001f\u001a\u00020\t8F@BX\u0086\u008e\u0002ø\u0001\u0000ø\u0001\u0001¢\u0006\u0012\n\u0004\b4\u0010!\u001a\u0004\b5\u0010,\"\u0004\b6\u0010\fR+\u0010>\u001a\u00020\u00142\u0006\u0010\u001f\u001a\u00020\u00148F@BX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\b8\u00109\u001a\u0004\b:\u0010;\"\u0004\b<\u0010=R(\u0010B\u001a\u0013\u0012\u0004\u0012\u00020@\u0012\u0004\u0012\u00020\u00060?¢\u0006\u0002\bA8\u0006¢\u0006\f\n\u0004\bB\u0010C\u001a\u0004\bD\u0010ER(\u0010F\u001a\u00020\t8\u0006@\u0006X\u0086\u000eø\u0001\u0000ø\u0001\u0001¢\u0006\u0012\n\u0004\bF\u0010*\u001a\u0004\bG\u0010,\"\u0004\bH\u0010\f\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u0006J"}, d2 = {"Landroidx/compose/foundation/lazy/layout/LazyLayoutAnimation;", "", "Lka/k0;", "coroutineScope", "<init>", "(Lka/k0;)V", "Lx6/t0;", "cancelPlacementAnimation", "()V", "Landroidx/compose/ui/unit/IntOffset;", "delta", "animatePlacementDelta--gyyYBs", "(J)V", "animatePlacementDelta", "animateAppearance", "stopAnimations", "Lka/k0;", "getCoroutineScope", "()Lka/k0;", "Landroidx/compose/animation/core/FiniteAnimationSpec;", "", "appearanceSpec", "Landroidx/compose/animation/core/FiniteAnimationSpec;", "getAppearanceSpec", "()Landroidx/compose/animation/core/FiniteAnimationSpec;", "setAppearanceSpec", "(Landroidx/compose/animation/core/FiniteAnimationSpec;)V", "placementSpec", "getPlacementSpec", "setPlacementSpec", "", "<set-?>", "isPlacementAnimationInProgress$delegate", "Landroidx/compose/runtime/MutableState;", "isPlacementAnimationInProgress", "()Z", "setPlacementAnimationInProgress", "(Z)V", "isAppearanceAnimationInProgress$delegate", "isAppearanceAnimationInProgress", "setAppearanceAnimationInProgress", "rawOffset", "J", "getRawOffset-nOcc-ac", "()J", "setRawOffset--gyyYBs", "Landroidx/compose/animation/core/Animatable;", "Landroidx/compose/animation/core/AnimationVector2D;", "placementDeltaAnimation", "Landroidx/compose/animation/core/Animatable;", "Landroidx/compose/animation/core/AnimationVector1D;", "visibilityAnimation", "placementDelta$delegate", "getPlacementDelta-nOcc-ac", "setPlacementDelta--gyyYBs", "placementDelta", "visibility$delegate", "Landroidx/compose/runtime/MutableFloatState;", "getVisibility", "()F", "setVisibility", "(F)V", "visibility", "Lkotlin/Function1;", "Landroidx/compose/ui/graphics/GraphicsLayerScope;", "Lx6/n;", "layerBlock", "Lr7/l;", "getLayerBlock", "()Lr7/l;", "lookaheadOffset", "getLookaheadOffset-nOcc-ac", "setLookaheadOffset--gyyYBs", "Companion", "foundation_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class LazyLayoutAnimation {
    private FiniteAnimationSpec<Float> appearanceSpec;
    private final k0 coroutineScope;

    /* JADX INFO: renamed from: isAppearanceAnimationInProgress$delegate, reason: from kotlin metadata */
    private final MutableState isAppearanceAnimationInProgress;

    /* JADX INFO: renamed from: isPlacementAnimationInProgress$delegate, reason: from kotlin metadata */
    private final MutableState isPlacementAnimationInProgress;
    private final l<GraphicsLayerScope, t0> layerBlock;
    private long lookaheadOffset;

    /* JADX INFO: renamed from: placementDelta$delegate, reason: from kotlin metadata */
    private final MutableState placementDelta;
    private final Animatable<IntOffset, AnimationVector2D> placementDeltaAnimation;
    private FiniteAnimationSpec<IntOffset> placementSpec;
    private long rawOffset;

    /* JADX INFO: renamed from: visibility$delegate, reason: from kotlin metadata */
    private final MutableFloatState visibility;
    private final Animatable<Float, AnimationVector1D> visibilityAnimation;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    public static final int $stable = 8;
    private static final long NotInitialized = IntOffsetKt.IntOffset(Integer.MAX_VALUE, Integer.MAX_VALUE);

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0019\u0010\u0003\u001a\u00020\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\n\n\u0002\u0010\u0007\u001a\u0004\b\u0005\u0010\u0006\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u0006\b"}, d2 = {"Landroidx/compose/foundation/lazy/layout/LazyLayoutAnimation$Companion;", "", "()V", "NotInitialized", "Landroidx/compose/ui/unit/IntOffset;", "getNotInitialized-nOcc-ac", "()J", "J", "foundation_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(h hVar) {
            this();
        }

        /* JADX INFO: renamed from: getNotInitialized-nOcc-ac, reason: not valid java name */
        public final long m681getNotInitializednOccac() {
            return LazyLayoutAnimation.NotInitialized;
        }

        private Companion() {
        }
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.lazy.layout.LazyLayoutAnimation$animateAppearance$1, reason: invalid class name */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {1, 8, 0})
    @e(c = "androidx.compose.foundation.lazy.layout.LazyLayoutAnimation$animateAppearance$1", f = "LazyLayoutAnimation.kt", l = {155, 156}, m = "invokeSuspend")
    public static final class AnonymousClass1 extends j implements p<k0, d7.d<? super t0>, Object> {
        final /* synthetic */ FiniteAnimationSpec<Float> $spec;
        int label;

        /* JADX INFO: renamed from: androidx.compose.foundation.lazy.layout.LazyLayoutAnimation$animateAppearance$1$1, reason: invalid class name and collision with other inner class name */
        @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0007\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0006\u001a\u00020\u0003*\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00020\u0000H\n¢\u0006\u0004\b\u0004\u0010\u0005"}, d2 = {"Landroidx/compose/animation/core/Animatable;", "", "Landroidx/compose/animation/core/AnimationVector1D;", "Lx6/t0;", "invoke", "(Landroidx/compose/animation/core/Animatable;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
        public static final class C00331 extends r implements l<Animatable<Float, AnimationVector1D>, t0> {
            final /* synthetic */ LazyLayoutAnimation this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public C00331(LazyLayoutAnimation lazyLayoutAnimation) {
                super(1);
                this.this$0 = lazyLayoutAnimation;
            }

            @Override // r7.l
            public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                invoke((Animatable<Float, AnimationVector1D>) obj);
                return t0.f22605a;
            }

            public final void invoke(Animatable<Float, AnimationVector1D> animatable) {
                this.this$0.setVisibility(animatable.getValue().floatValue());
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(FiniteAnimationSpec<Float> finiteAnimationSpec, d7.d<? super AnonymousClass1> dVar) {
            super(2, dVar);
            this.$spec = finiteAnimationSpec;
        }

        @Override // f7.a
        public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
            return LazyLayoutAnimation.this.new AnonymousClass1(this.$spec, dVar);
        }

        /* JADX WARN: Code restructure failed: missing block: B:21:0x005b, code lost:
        
            if (androidx.compose.animation.core.Animatable.animateTo$default(r5, r6, r7, null, r9, r10, 4, null) == r4) goto L22;
         */
        @Override // f7.a
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r14) throws java.lang.Throwable {
            /*
                r13 = this;
                int r0 = r13.label
                r1 = 0
                r2 = 2
                r3 = 1
                e7.a r4 = e7.a.f15033i
                if (r0 == 0) goto L22
                if (r0 == r3) goto L1e
                if (r0 != r2) goto L16
                k2.c.G(r14)     // Catch: java.lang.Throwable -> L12
                r10 = r13
                goto L5e
            L12:
                r0 = move-exception
                r14 = r0
                r10 = r13
                goto L6c
            L16:
                java.lang.IllegalStateException r14 = new java.lang.IllegalStateException
                java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
                r14.<init>(r0)
                throw r14
            L1e:
                k2.c.G(r14)     // Catch: java.lang.Throwable -> L12
                goto L3b
            L22:
                k2.c.G(r14)
                androidx.compose.foundation.lazy.layout.LazyLayoutAnimation r14 = androidx.compose.foundation.lazy.layout.LazyLayoutAnimation.this     // Catch: java.lang.Throwable -> L69
                androidx.compose.animation.core.Animatable r14 = androidx.compose.foundation.lazy.layout.LazyLayoutAnimation.access$getVisibilityAnimation$p(r14)     // Catch: java.lang.Throwable -> L69
                java.lang.Float r0 = new java.lang.Float     // Catch: java.lang.Throwable -> L69
                r5 = 0
                r0.<init>(r5)     // Catch: java.lang.Throwable -> L69
                r13.label = r3     // Catch: java.lang.Throwable -> L69
                java.lang.Object r14 = r14.snapTo(r0, r13)     // Catch: java.lang.Throwable -> L69
                if (r14 != r4) goto L3b
                r10 = r13
                goto L5d
            L3b:
                androidx.compose.foundation.lazy.layout.LazyLayoutAnimation r14 = androidx.compose.foundation.lazy.layout.LazyLayoutAnimation.this     // Catch: java.lang.Throwable -> L69
                androidx.compose.animation.core.Animatable r5 = androidx.compose.foundation.lazy.layout.LazyLayoutAnimation.access$getVisibilityAnimation$p(r14)     // Catch: java.lang.Throwable -> L69
                java.lang.Float r6 = new java.lang.Float     // Catch: java.lang.Throwable -> L69
                r14 = 1065353216(0x3f800000, float:1.0)
                r6.<init>(r14)     // Catch: java.lang.Throwable -> L69
                androidx.compose.animation.core.FiniteAnimationSpec<java.lang.Float> r7 = r13.$spec     // Catch: java.lang.Throwable -> L69
                androidx.compose.foundation.lazy.layout.LazyLayoutAnimation$animateAppearance$1$1 r9 = new androidx.compose.foundation.lazy.layout.LazyLayoutAnimation$animateAppearance$1$1     // Catch: java.lang.Throwable -> L69
                androidx.compose.foundation.lazy.layout.LazyLayoutAnimation r14 = androidx.compose.foundation.lazy.layout.LazyLayoutAnimation.this     // Catch: java.lang.Throwable -> L69
                r9.<init>(r14)     // Catch: java.lang.Throwable -> L69
                r13.label = r2     // Catch: java.lang.Throwable -> L69
                r8 = 0
                r11 = 4
                r12 = 0
                r10 = r13
                java.lang.Object r14 = androidx.compose.animation.core.Animatable.animateTo$default(r5, r6, r7, r8, r9, r10, r11, r12)     // Catch: java.lang.Throwable -> L66
                if (r14 != r4) goto L5e
            L5d:
                return r4
            L5e:
                androidx.compose.foundation.lazy.layout.LazyLayoutAnimation r14 = androidx.compose.foundation.lazy.layout.LazyLayoutAnimation.this
                androidx.compose.foundation.lazy.layout.LazyLayoutAnimation.access$setAppearanceAnimationInProgress(r14, r1)
                x6.t0 r14 = x6.t0.f22605a
                return r14
            L66:
                r0 = move-exception
            L67:
                r14 = r0
                goto L6c
            L69:
                r0 = move-exception
                r10 = r13
                goto L67
            L6c:
                androidx.compose.foundation.lazy.layout.LazyLayoutAnimation r0 = androidx.compose.foundation.lazy.layout.LazyLayoutAnimation.this
                androidx.compose.foundation.lazy.layout.LazyLayoutAnimation.access$setAppearanceAnimationInProgress(r0, r1)
                throw r14
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.lazy.layout.LazyLayoutAnimation.AnonymousClass1.invokeSuspend(java.lang.Object):java.lang.Object");
        }

        @Override // r7.p
        public final Object invoke(k0 k0Var, d7.d<? super t0> dVar) {
            return ((AnonymousClass1) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
        }
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.lazy.layout.LazyLayoutAnimation$cancelPlacementAnimation$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {1, 8, 0})
    @e(c = "androidx.compose.foundation.lazy.layout.LazyLayoutAnimation$cancelPlacementAnimation$1", f = "LazyLayoutAnimation.kt", l = {92}, m = "invokeSuspend")
    public static final class C03671 extends j implements p<k0, d7.d<? super t0>, Object> {
        int label;

        public C03671(d7.d<? super C03671> dVar) {
            super(2, dVar);
        }

        @Override // f7.a
        public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
            return LazyLayoutAnimation.this.new C03671(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            int i10 = this.label;
            if (i10 == 0) {
                k2.c.G(obj);
                Animatable animatable = LazyLayoutAnimation.this.placementDeltaAnimation;
                IntOffset intOffsetM5793boximpl = IntOffset.m5793boximpl(IntOffset.INSTANCE.m5812getZeronOccac());
                this.label = 1;
                Object objSnapTo = animatable.snapTo(intOffsetM5793boximpl, this);
                e7.a aVar = e7.a.f15033i;
                if (objSnapTo == aVar) {
                    return aVar;
                }
            } else {
                if (i10 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                k2.c.G(obj);
            }
            LazyLayoutAnimation.this.m674setPlacementDeltagyyYBs(IntOffset.INSTANCE.m5812getZeronOccac());
            LazyLayoutAnimation.this.setPlacementAnimationInProgress(false);
            return t0.f22605a;
        }

        @Override // r7.p
        public final Object invoke(k0 k0Var, d7.d<? super t0> dVar) {
            return ((C03671) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
        }
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.lazy.layout.LazyLayoutAnimation$stopAnimations$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {1, 8, 0})
    @e(c = "androidx.compose.foundation.lazy.layout.LazyLayoutAnimation$stopAnimations$1", f = "LazyLayoutAnimation.kt", l = {169}, m = "invokeSuspend")
    public static final class C03681 extends j implements p<k0, d7.d<? super t0>, Object> {
        int label;

        public C03681(d7.d<? super C03681> dVar) {
            super(2, dVar);
        }

        @Override // f7.a
        public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
            return LazyLayoutAnimation.this.new C03681(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            int i10 = this.label;
            if (i10 == 0) {
                k2.c.G(obj);
                Animatable animatable = LazyLayoutAnimation.this.placementDeltaAnimation;
                this.label = 1;
                Object objStop = animatable.stop(this);
                e7.a aVar = e7.a.f15033i;
                if (objStop == aVar) {
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
            return ((C03681) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
        }
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.lazy.layout.LazyLayoutAnimation$stopAnimations$2, reason: invalid class name */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {1, 8, 0})
    @e(c = "androidx.compose.foundation.lazy.layout.LazyLayoutAnimation$stopAnimations$2", f = "LazyLayoutAnimation.kt", l = {175}, m = "invokeSuspend")
    public static final class AnonymousClass2 extends j implements p<k0, d7.d<? super t0>, Object> {
        int label;

        public AnonymousClass2(d7.d<? super AnonymousClass2> dVar) {
            super(2, dVar);
        }

        @Override // f7.a
        public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
            return LazyLayoutAnimation.this.new AnonymousClass2(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            int i10 = this.label;
            if (i10 == 0) {
                k2.c.G(obj);
                Animatable animatable = LazyLayoutAnimation.this.visibilityAnimation;
                this.label = 1;
                Object objStop = animatable.stop(this);
                e7.a aVar = e7.a.f15033i;
                if (objStop == aVar) {
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

    public LazyLayoutAnimation(k0 k0Var) {
        this.coroutineScope = k0Var;
        Boolean bool = Boolean.FALSE;
        this.isPlacementAnimationInProgress = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(bool, null, 2, null);
        this.isAppearanceAnimationInProgress = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(bool, null, 2, null);
        long j10 = NotInitialized;
        this.rawOffset = j10;
        IntOffset.Companion companion = IntOffset.INSTANCE;
        String str = null;
        this.placementDeltaAnimation = new Animatable<>(IntOffset.m5793boximpl(companion.m5812getZeronOccac()), VectorConvertersKt.getVectorConverter(companion), null, str, 12, null);
        this.visibilityAnimation = new Animatable<>(Float.valueOf(1.0f), VectorConvertersKt.getVectorConverter(i.f19743a), str, null, 12, null);
        this.placementDelta = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(IntOffset.m5793boximpl(companion.m5812getZeronOccac()), null, 2, null);
        this.visibility = PrimitiveSnapshotStateKt.mutableFloatStateOf(1.0f);
        this.layerBlock = new LazyLayoutAnimation$layerBlock$1(this);
        this.lookaheadOffset = j10;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setAppearanceAnimationInProgress(boolean z) {
        this.isAppearanceAnimationInProgress.setValue(Boolean.valueOf(z));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setPlacementAnimationInProgress(boolean z) {
        this.isPlacementAnimationInProgress.setValue(Boolean.valueOf(z));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: setPlacementDelta--gyyYBs, reason: not valid java name */
    public final void m674setPlacementDeltagyyYBs(long j10) {
        this.placementDelta.setValue(IntOffset.m5793boximpl(j10));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setVisibility(float f10) {
        this.visibility.setFloatValue(f10);
    }

    public final void animateAppearance() {
        FiniteAnimationSpec<Float> finiteAnimationSpec = this.appearanceSpec;
        if (isAppearanceAnimationInProgress() || finiteAnimationSpec == null) {
            return;
        }
        setAppearanceAnimationInProgress(true);
        setVisibility(0.0f);
        m0.p(this.coroutineScope, null, 0, new AnonymousClass1(finiteAnimationSpec, null), 3);
    }

    /* JADX INFO: renamed from: animatePlacementDelta--gyyYBs, reason: not valid java name */
    public final void m675animatePlacementDeltagyyYBs(long delta) {
        FiniteAnimationSpec<IntOffset> finiteAnimationSpec = this.placementSpec;
        if (finiteAnimationSpec == null) {
            return;
        }
        long jM677getPlacementDeltanOccac = m677getPlacementDeltanOccac();
        long jIntOffset = IntOffsetKt.IntOffset(IntOffset.m5802getXimpl(jM677getPlacementDeltanOccac) - IntOffset.m5802getXimpl(delta), IntOffset.m5803getYimpl(jM677getPlacementDeltanOccac) - IntOffset.m5803getYimpl(delta));
        m674setPlacementDeltagyyYBs(jIntOffset);
        setPlacementAnimationInProgress(true);
        m0.p(this.coroutineScope, null, 0, new LazyLayoutAnimation$animatePlacementDelta$1(this, finiteAnimationSpec, jIntOffset, null), 3);
    }

    public final void cancelPlacementAnimation() {
        if (isPlacementAnimationInProgress()) {
            m0.p(this.coroutineScope, null, 0, new C03671(null), 3);
        }
    }

    public final FiniteAnimationSpec<Float> getAppearanceSpec() {
        return this.appearanceSpec;
    }

    public final k0 getCoroutineScope() {
        return this.coroutineScope;
    }

    public final l<GraphicsLayerScope, t0> getLayerBlock() {
        return this.layerBlock;
    }

    /* JADX INFO: renamed from: getLookaheadOffset-nOcc-ac, reason: not valid java name and from getter */
    public final long getLookaheadOffset() {
        return this.lookaheadOffset;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX INFO: renamed from: getPlacementDelta-nOcc-ac, reason: not valid java name */
    public final long m677getPlacementDeltanOccac() {
        return ((IntOffset) this.placementDelta.getValue()).getPackedValue();
    }

    public final FiniteAnimationSpec<IntOffset> getPlacementSpec() {
        return this.placementSpec;
    }

    /* JADX INFO: renamed from: getRawOffset-nOcc-ac, reason: not valid java name and from getter */
    public final long getRawOffset() {
        return this.rawOffset;
    }

    public final float getVisibility() {
        return this.visibility.getFloatValue();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final boolean isAppearanceAnimationInProgress() {
        return ((Boolean) this.isAppearanceAnimationInProgress.getValue()).booleanValue();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final boolean isPlacementAnimationInProgress() {
        return ((Boolean) this.isPlacementAnimationInProgress.getValue()).booleanValue();
    }

    public final void setAppearanceSpec(FiniteAnimationSpec<Float> finiteAnimationSpec) {
        this.appearanceSpec = finiteAnimationSpec;
    }

    /* JADX INFO: renamed from: setLookaheadOffset--gyyYBs, reason: not valid java name */
    public final void m679setLookaheadOffsetgyyYBs(long j10) {
        this.lookaheadOffset = j10;
    }

    public final void setPlacementSpec(FiniteAnimationSpec<IntOffset> finiteAnimationSpec) {
        this.placementSpec = finiteAnimationSpec;
    }

    /* JADX INFO: renamed from: setRawOffset--gyyYBs, reason: not valid java name */
    public final void m680setRawOffsetgyyYBs(long j10) {
        this.rawOffset = j10;
    }

    public final void stopAnimations() {
        if (isPlacementAnimationInProgress()) {
            setPlacementAnimationInProgress(false);
            m0.p(this.coroutineScope, null, 0, new C03681(null), 3);
        }
        if (isAppearanceAnimationInProgress()) {
            setAppearanceAnimationInProgress(false);
            m0.p(this.coroutineScope, null, 0, new AnonymousClass2(null), 3);
        }
        m674setPlacementDeltagyyYBs(IntOffset.INSTANCE.m5812getZeronOccac());
        this.rawOffset = NotInitialized;
        setVisibility(1.0f);
    }
}
