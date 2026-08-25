package androidx.compose.material3;

import androidx.compose.animation.core.AnimationScope;
import androidx.compose.animation.core.AnimationSpec;
import androidx.compose.animation.core.AnimationStateKt;
import androidx.compose.animation.core.AnimationVector1D;
import androidx.compose.animation.core.DecayAnimationSpec;
import androidx.compose.foundation.gestures.FlingBehavior;
import androidx.compose.foundation.gestures.Orientation;
import androidx.compose.foundation.gestures.ScrollScope;
import androidx.compose.foundation.lazy.LazyListItemInfo;
import androidx.compose.foundation.lazy.LazyListLayoutInfo;
import androidx.compose.foundation.lazy.LazyListState;
import androidx.compose.ui.MotionDurationScale;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.Dp;
import androidx.compose.ui.unit.IntSize;
import androidx.media3.extractor.ts.TsExtractor;
import com.google.android.gms.cast.MediaError;
import d7.j;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.h0;
import kotlin.jvm.internal.r;
import r7.l;
import r7.p;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0089\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000f\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\n*\u0001A\b\u0001\u0018\u00002\u00020\u0001B3\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004\u0012\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00050\u0007\u0012\u0006\u0010\n\u001a\u00020\t¢\u0006\u0004\b\u000b\u0010\fJ\u001c\u0010\u000f\u001a\u00020\u0005*\u00020\r2\u0006\u0010\u000e\u001a\u00020\u0005H\u0096@¢\u0006\u0004\b\u000f\u0010\u0010J\u001a\u0010\u0014\u001a\u00020\u00132\b\u0010\u0012\u001a\u0004\u0018\u00010\u0011H\u0096\u0002¢\u0006\u0004\b\u0014\u0010\u0015J\u000f\u0010\u0017\u001a\u00020\u0016H\u0016¢\u0006\u0004\b\u0017\u0010\u0018J(\u0010\u001b\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u001a0\u0019*\u00020\r2\u0006\u0010\u000e\u001a\u00020\u0005H\u0082@¢\u0006\u0004\b\u001b\u0010\u0010J(\u0010\u001d\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u001a0\u0019*\u00020\r2\u0006\u0010\u001c\u001a\u00020\u0005H\u0082@¢\u0006\u0004\b\u001d\u0010\u0010J(\u0010\u001e\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u001a0\u0019*\u00020\r2\u0006\u0010\u000e\u001a\u00020\u0005H\u0082@¢\u0006\u0004\b\u001e\u0010\u0010J0\u0010 \u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u001a0\u0019*\u00020\r2\u0006\u0010\u001f\u001a\u00020\u00052\u0006\u0010\u000e\u001a\u00020\u0005H\u0082@¢\u0006\u0004\b \u0010!J*\u0010%\u001a\u00028\u0000\"\u000e\b\u0000\u0010#*\b\u0012\u0004\u0012\u00028\u00000\"*\b\u0012\u0004\u0012\u00028\u00000$H\u0082\u0002¢\u0006\u0004\b%\u0010&J*\u0010'\u001a\u00028\u0000\"\u000e\b\u0000\u0010#*\b\u0012\u0004\u0012\u00028\u00000\"*\b\u0012\u0004\u0012\u00028\u00000$H\u0082\u0002¢\u0006\u0004\b'\u0010&J\u001f\u0010(\u001a\u00020\u00052\u0006\u0010\u001c\u001a\u00020\u00052\u0006\u0010\u0003\u001a\u00020\u0002H\u0002¢\u0006\u0004\b(\u0010)JJ\u0010-\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u001a0\u0019*\u00020\r2\u0006\u0010*\u001a\u00020\u00052\u0012\u0010,\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u001a0+2\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004H\u0082@¢\u0006\u0004\b-\u0010.JR\u00100\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u001a0\u0019*\u00020\r2\u0006\u0010*\u001a\u00020\u00052\u0006\u0010/\u001a\u00020\u00052\u0012\u0010,\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u001a0+2\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00050\u0007H\u0082@¢\u0006\u0004\b0\u00101J\u001b\u00103\u001a\u00020\u0005*\u00020\u00052\u0006\u00102\u001a\u00020\u0005H\u0002¢\u0006\u0004\b3\u00104J\u001f\u00109\u001a\u00020\u00052\u0006\u00106\u001a\u0002052\u0006\u00108\u001a\u000207H\u0002¢\u0006\u0004\b9\u0010:R\u0014\u0010\u0003\u001a\u00020\u00028\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0003\u0010;R\u001a\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u00048\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0006\u0010<R\u001a\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00050\u00078\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\b\u0010=R\u0014\u0010\n\u001a\u00020\t8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\n\u0010>R\u0014\u0010?\u001a\u00020\u00058\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b?\u0010@R\u0016\u0010B\u001a\u00020A8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bB\u0010CR\u0014\u0010D\u001a\u00020\u00058\u0002X\u0082D¢\u0006\u0006\n\u0004\bD\u0010@R\u001a\u0010F\u001a\u00020E8\u0002X\u0082\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\u0006\n\u0004\bF\u0010@R\u001a\u0010J\u001a\b\u0012\u0004\u0012\u0002070G8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\bH\u0010IR\u0014\u0010M\u001a\u00020\u00058BX\u0082\u0004¢\u0006\u0006\u001a\u0004\bK\u0010LR\u0018\u0010P\u001a\u00020\u0016*\u0002058BX\u0082\u0004¢\u0006\u0006\u001a\u0004\bN\u0010O\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u0006Q"}, d2 = {"Landroidx/compose/material3/SnapFlingBehavior;", "Landroidx/compose/foundation/gestures/FlingBehavior;", "Landroidx/compose/foundation/lazy/LazyListState;", "lazyListState", "Landroidx/compose/animation/core/DecayAnimationSpec;", "", "decayAnimationSpec", "Landroidx/compose/animation/core/AnimationSpec;", "snapAnimationSpec", "Landroidx/compose/ui/unit/Density;", "density", "<init>", "(Landroidx/compose/foundation/lazy/LazyListState;Landroidx/compose/animation/core/DecayAnimationSpec;Landroidx/compose/animation/core/AnimationSpec;Landroidx/compose/ui/unit/Density;)V", "Landroidx/compose/foundation/gestures/ScrollScope;", "initialVelocity", "performFling", "(Landroidx/compose/foundation/gestures/ScrollScope;FLd7/d;)Ljava/lang/Object;", "", "other", "", "equals", "(Ljava/lang/Object;)Z", "", "hashCode", "()I", "Landroidx/compose/material3/AnimationResult;", "Landroidx/compose/animation/core/AnimationVector1D;", "fling", "velocity", "shortSnap", "longSnap", "initialTargetOffset", "runApproach", "(Landroidx/compose/foundation/gestures/ScrollScope;FFLd7/d;)Ljava/lang/Object;", "", "T", "Lx7/e;", "component1", "(Lx7/e;)Ljava/lang/Comparable;", "component2", "findClosestOffset", "(FLandroidx/compose/foundation/lazy/LazyListState;)F", "targetOffset", "Landroidx/compose/animation/core/AnimationState;", "animationState", "animateDecay", "(Landroidx/compose/foundation/gestures/ScrollScope;FLandroidx/compose/animation/core/AnimationState;Landroidx/compose/animation/core/DecayAnimationSpec;Ld7/d;)Ljava/lang/Object;", "cancelOffset", "animateSnap", "(Landroidx/compose/foundation/gestures/ScrollScope;FFLandroidx/compose/animation/core/AnimationState;Landroidx/compose/animation/core/AnimationSpec;Ld7/d;)Ljava/lang/Object;", "target", "coerceToTarget", "(FF)F", "Landroidx/compose/foundation/lazy/LazyListLayoutInfo;", "layoutInfo", "Landroidx/compose/foundation/lazy/LazyListItemInfo;", "item", "calculateDistanceToDesiredSnapPosition", "(Landroidx/compose/foundation/lazy/LazyListLayoutInfo;Landroidx/compose/foundation/lazy/LazyListItemInfo;)F", "Landroidx/compose/foundation/lazy/LazyListState;", "Landroidx/compose/animation/core/DecayAnimationSpec;", "Landroidx/compose/animation/core/AnimationSpec;", "Landroidx/compose/ui/unit/Density;", "velocityThreshold", "F", "androidx/compose/material3/SnapFlingBehavior$motionScaleDuration$1", "motionScaleDuration", "Landroidx/compose/material3/SnapFlingBehavior$motionScaleDuration$1;", "DefaultScrollMotionDurationScaleFactor", "Landroidx/compose/ui/unit/Dp;", "MinFlingVelocityDp", "", "getVisibleItemsInfo", "()Ljava/util/List;", "visibleItemsInfo", "getItemSize", "()F", "itemSize", "getSingleAxisViewportSize", "(Landroidx/compose/foundation/lazy/LazyListLayoutInfo;)I", "singleAxisViewportSize", "material3_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class SnapFlingBehavior implements FlingBehavior {
    public static final int $stable = 0;
    private final DecayAnimationSpec<Float> decayAnimationSpec;
    private final Density density;
    private final LazyListState lazyListState;
    private final AnimationSpec<Float> snapAnimationSpec;
    private final float velocityThreshold;
    private SnapFlingBehavior$motionScaleDuration$1 motionScaleDuration = new MotionDurationScale() { // from class: androidx.compose.material3.SnapFlingBehavior$motionScaleDuration$1
        @Override // androidx.compose.ui.MotionDurationScale, d7.j
        public <R> R fold(R r4, p<? super R, ? super j.a, ? extends R> pVar) {
            return (R) MotionDurationScale.DefaultImpls.fold(this, r4, pVar);
        }

        @Override // androidx.compose.ui.MotionDurationScale, d7.j
        public <E extends j.a> E get(j.b<E> bVar) {
            return (E) MotionDurationScale.DefaultImpls.get(this, bVar);
        }

        @Override // androidx.compose.ui.MotionDurationScale, d7.j.a
        public final /* synthetic */ j.b getKey() {
            return androidx.compose.ui.c.a(this);
        }

        @Override // androidx.compose.ui.MotionDurationScale
        public float getScaleFactor() {
            return this.this$0.DefaultScrollMotionDurationScaleFactor;
        }

        @Override // androidx.compose.ui.MotionDurationScale, d7.j
        public j minusKey(j.b<?> bVar) {
            return MotionDurationScale.DefaultImpls.minusKey(this, bVar);
        }

        @Override // androidx.compose.ui.MotionDurationScale, d7.j
        public j plus(j jVar) {
            return MotionDurationScale.DefaultImpls.plus(this, jVar);
        }
    };
    private final float DefaultScrollMotionDurationScaleFactor = 1.0f;
    private final float MinFlingVelocityDp = Dp.m5678constructorimpl(MediaError.DetailedErrorCode.MANIFEST_UNKNOWN);

    /* JADX INFO: renamed from: androidx.compose.material3.SnapFlingBehavior$animateDecay$1, reason: invalid class name */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    @f7.e(c = "androidx.compose.material3.SnapFlingBehavior", f = "SnapFlingBehavior.kt", l = {261}, m = "animateDecay")
    public static final class AnonymousClass1 extends f7.c {
        float F$0;
        Object L$0;
        Object L$1;
        int label;
        /* synthetic */ Object result;

        public AnonymousClass1(d7.d<? super AnonymousClass1> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return SnapFlingBehavior.this.animateDecay(null, 0.0f, null, null, this);
        }
    }

    /* JADX INFO: renamed from: androidx.compose.material3.SnapFlingBehavior$animateDecay$2, reason: invalid class name */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0007\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0006\u001a\u00020\u0003*\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00020\u0000H\n¢\u0006\u0004\b\u0004\u0010\u0005"}, d2 = {"Landroidx/compose/animation/core/AnimationScope;", "", "Landroidx/compose/animation/core/AnimationVector1D;", "Lx6/t0;", "invoke", "(Landroidx/compose/animation/core/AnimationScope;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class AnonymousClass2 extends r implements l<AnimationScope<Float, AnimationVector1D>, t0> {
        final /* synthetic */ h0 $previousValue;
        final /* synthetic */ float $targetOffset;
        final /* synthetic */ ScrollScope $this_animateDecay;
        final /* synthetic */ SnapFlingBehavior this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass2(float f10, SnapFlingBehavior snapFlingBehavior, h0 h0Var, ScrollScope scrollScope) {
            super(1);
            this.$targetOffset = f10;
            this.this$0 = snapFlingBehavior;
            this.$previousValue = h0Var;
            this.$this_animateDecay = scrollScope;
        }

        @Override // r7.l
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            invoke((AnimationScope<Float, AnimationVector1D>) obj);
            return t0.f22605a;
        }

        public final void invoke(AnimationScope<Float, AnimationVector1D> animationScope) {
            if (Math.abs(animationScope.getValue().floatValue()) >= Math.abs(this.$targetOffset)) {
                SnapFlingBehavior.animateDecay$consumeDelta(animationScope, this.$this_animateDecay, this.this$0.coerceToTarget(animationScope.getValue().floatValue(), this.$targetOffset) - this.$previousValue.f19742i);
                animationScope.cancelAnimation();
            } else {
                SnapFlingBehavior.animateDecay$consumeDelta(animationScope, this.$this_animateDecay, animationScope.getValue().floatValue() - this.$previousValue.f19742i);
                this.$previousValue.f19742i = animationScope.getValue().floatValue();
            }
        }
    }

    /* JADX INFO: renamed from: androidx.compose.material3.SnapFlingBehavior$animateSnap$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    @f7.e(c = "androidx.compose.material3.SnapFlingBehavior", f = "SnapFlingBehavior.kt", l = {299}, m = "animateSnap")
    public static final class C05511 extends f7.c {
        float F$0;
        float F$1;
        Object L$0;
        Object L$1;
        Object L$2;
        int label;
        /* synthetic */ Object result;

        public C05511(d7.d<? super C05511> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return SnapFlingBehavior.this.animateSnap(null, 0.0f, 0.0f, null, null, this);
        }
    }

    /* JADX INFO: renamed from: androidx.compose.material3.SnapFlingBehavior$animateSnap$2, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0007\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0006\u001a\u00020\u0003*\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00020\u0000H\n¢\u0006\u0004\b\u0004\u0010\u0005"}, d2 = {"Landroidx/compose/animation/core/AnimationScope;", "", "Landroidx/compose/animation/core/AnimationVector1D;", "Lx6/t0;", "invoke", "(Landroidx/compose/animation/core/AnimationScope;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class C05522 extends r implements l<AnimationScope<Float, AnimationVector1D>, t0> {
        final /* synthetic */ float $cancelOffset;
        final /* synthetic */ h0 $consumedUpToNow;
        final /* synthetic */ ScrollScope $this_animateSnap;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C05522(float f10, h0 h0Var, ScrollScope scrollScope) {
            super(1);
            this.$cancelOffset = f10;
            this.$consumedUpToNow = h0Var;
            this.$this_animateSnap = scrollScope;
        }

        @Override // r7.l
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            invoke((AnimationScope<Float, AnimationVector1D>) obj);
            return t0.f22605a;
        }

        public final void invoke(AnimationScope<Float, AnimationVector1D> animationScope) {
            float fCoerceToTarget = SnapFlingBehavior.this.coerceToTarget(animationScope.getValue().floatValue(), this.$cancelOffset);
            float f10 = fCoerceToTarget - this.$consumedUpToNow.f19742i;
            float fScrollBy = this.$this_animateSnap.scrollBy(f10);
            if (Math.abs(f10 - fScrollBy) > 0.5f || fCoerceToTarget != animationScope.getValue().floatValue()) {
                animationScope.cancelAnimation();
            }
            this.$consumedUpToNow.f19742i += fScrollBy;
        }
    }

    /* JADX INFO: renamed from: androidx.compose.material3.SnapFlingBehavior$fling$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    @f7.e(c = "androidx.compose.material3.SnapFlingBehavior", f = "SnapFlingBehavior.kt", l = {95}, m = "fling")
    public static final class C05531 extends f7.c {
        int label;
        /* synthetic */ Object result;

        public C05531(d7.d<? super C05531> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return SnapFlingBehavior.this.fling(null, 0.0f, this);
        }
    }

    /* JADX INFO: renamed from: androidx.compose.material3.SnapFlingBehavior$longSnap$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    @f7.e(c = "androidx.compose.material3.SnapFlingBehavior", f = "SnapFlingBehavior.kt", l = {TsExtractor.TS_STREAM_TYPE_E_AC3, 140}, m = "longSnap")
    public static final class C05541 extends f7.c {
        Object L$0;
        Object L$1;
        int label;
        /* synthetic */ Object result;

        public C05541(d7.d<? super C05541> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return SnapFlingBehavior.this.longSnap(null, 0.0f, this);
        }
    }

    /* JADX INFO: renamed from: androidx.compose.material3.SnapFlingBehavior$performFling$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    @f7.e(c = "androidx.compose.material3.SnapFlingBehavior", f = "SnapFlingBehavior.kt", l = {83}, m = "performFling")
    public static final class C05551 extends f7.c {
        int label;
        /* synthetic */ Object result;

        public C05551(d7.d<? super C05551> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return SnapFlingBehavior.this.performFling(null, 0.0f, this);
        }
    }

    /* JADX INFO: renamed from: androidx.compose.material3.SnapFlingBehavior$runApproach$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    @f7.e(c = "androidx.compose.material3.SnapFlingBehavior", f = "SnapFlingBehavior.kt", l = {154}, m = "runApproach")
    public static final class C05561 extends f7.c {
        Object L$0;
        int label;
        /* synthetic */ Object result;

        public C05561(d7.d<? super C05561> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return SnapFlingBehavior.this.runApproach(null, 0.0f, 0.0f, this);
        }
    }

    /* JADX WARN: Type inference failed for: r1v3, types: [androidx.compose.material3.SnapFlingBehavior$motionScaleDuration$1] */
    public SnapFlingBehavior(LazyListState lazyListState, DecayAnimationSpec<Float> decayAnimationSpec, AnimationSpec<Float> animationSpec, Density density) {
        this.lazyListState = lazyListState;
        this.decayAnimationSpec = decayAnimationSpec;
        this.snapAnimationSpec = animationSpec;
        this.density = density;
        this.velocityThreshold = density.mo285toPx0680j_4(this.MinFlingVelocityDp);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object animateDecay(androidx.compose.foundation.gestures.ScrollScope r5, float r6, androidx.compose.animation.core.AnimationState<java.lang.Float, androidx.compose.animation.core.AnimationVector1D> r7, androidx.compose.animation.core.DecayAnimationSpec<java.lang.Float> r8, d7.d<? super androidx.compose.material3.AnimationResult<java.lang.Float, androidx.compose.animation.core.AnimationVector1D>> r9) {
        /*
            r4 = this;
            boolean r0 = r9 instanceof androidx.compose.material3.SnapFlingBehavior.AnonymousClass1
            if (r0 == 0) goto L13
            r0 = r9
            androidx.compose.material3.SnapFlingBehavior$animateDecay$1 r0 = (androidx.compose.material3.SnapFlingBehavior.AnonymousClass1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            androidx.compose.material3.SnapFlingBehavior$animateDecay$1 r0 = new androidx.compose.material3.SnapFlingBehavior$animateDecay$1
            r0.<init>(r9)
        L18:
            java.lang.Object r9 = r0.result
            int r1 = r0.label
            r2 = 1
            if (r1 == 0) goto L37
            if (r1 != r2) goto L2f
            float r6 = r0.F$0
            java.lang.Object r5 = r0.L$1
            kotlin.jvm.internal.h0 r5 = (kotlin.jvm.internal.h0) r5
            java.lang.Object r7 = r0.L$0
            androidx.compose.animation.core.AnimationState r7 = (androidx.compose.animation.core.AnimationState) r7
            k2.c.G(r9)
            goto L69
        L2f:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r6)
            throw r5
        L37:
            k2.c.G(r9)
            kotlin.jvm.internal.h0 r9 = new kotlin.jvm.internal.h0
            r9.<init>()
            java.lang.Object r1 = r7.getVelocity()
            java.lang.Number r1 = (java.lang.Number) r1
            float r1 = r1.floatValue()
            r3 = 0
            int r1 = (r1 > r3 ? 1 : (r1 == r3 ? 0 : -1))
            if (r1 != 0) goto L50
            r1 = r2
            goto L51
        L50:
            r1 = 0
        L51:
            r1 = r1 ^ r2
            androidx.compose.material3.SnapFlingBehavior$animateDecay$2 r3 = new androidx.compose.material3.SnapFlingBehavior$animateDecay$2
            r3.<init>(r6, r4, r9, r5)
            r0.L$0 = r7
            r0.L$1 = r9
            r0.F$0 = r6
            r0.label = r2
            java.lang.Object r5 = androidx.compose.animation.core.SuspendAnimationKt.animateDecay(r7, r8, r1, r3, r0)
            e7.a r8 = e7.a.f15033i
            if (r5 != r8) goto L68
            return r8
        L68:
            r5 = r9
        L69:
            androidx.compose.material3.AnimationResult r8 = new androidx.compose.material3.AnimationResult
            float r5 = r5.f19742i
            float r6 = r6 - r5
            java.lang.Float r5 = new java.lang.Float
            r5.<init>(r6)
            r8.<init>(r5, r7)
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material3.SnapFlingBehavior.animateDecay(androidx.compose.foundation.gestures.ScrollScope, float, androidx.compose.animation.core.AnimationState, androidx.compose.animation.core.DecayAnimationSpec, d7.d):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void animateDecay$consumeDelta(AnimationScope<Float, AnimationVector1D> animationScope, ScrollScope scrollScope, float f10) {
        if (Math.abs(f10 - scrollScope.scrollBy(f10)) > 0.5f) {
            animationScope.cancelAnimation();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0016  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object animateSnap(androidx.compose.foundation.gestures.ScrollScope r12, float r13, float r14, androidx.compose.animation.core.AnimationState<java.lang.Float, androidx.compose.animation.core.AnimationVector1D> r15, androidx.compose.animation.core.AnimationSpec<java.lang.Float> r16, d7.d<? super androidx.compose.material3.AnimationResult<java.lang.Float, androidx.compose.animation.core.AnimationVector1D>> r17) {
        /*
            r11 = this;
            r0 = r17
            boolean r1 = r0 instanceof androidx.compose.material3.SnapFlingBehavior.C05511
            if (r1 == 0) goto L16
            r1 = r0
            androidx.compose.material3.SnapFlingBehavior$animateSnap$1 r1 = (androidx.compose.material3.SnapFlingBehavior.C05511) r1
            int r2 = r1.label
            r3 = -2147483648(0xffffffff80000000, float:-0.0)
            r4 = r2 & r3
            if (r4 == 0) goto L16
            int r2 = r2 - r3
            r1.label = r2
        L14:
            r7 = r1
            goto L1c
        L16:
            androidx.compose.material3.SnapFlingBehavior$animateSnap$1 r1 = new androidx.compose.material3.SnapFlingBehavior$animateSnap$1
            r1.<init>(r0)
            goto L14
        L1c:
            java.lang.Object r0 = r7.result
            int r1 = r7.label
            r2 = 1
            if (r1 == 0) goto L42
            if (r1 != r2) goto L3a
            float r12 = r7.F$1
            float r13 = r7.F$0
            java.lang.Object r1 = r7.L$2
            kotlin.jvm.internal.h0 r1 = (kotlin.jvm.internal.h0) r1
            java.lang.Object r2 = r7.L$1
            androidx.compose.animation.core.AnimationState r2 = (androidx.compose.animation.core.AnimationState) r2
            java.lang.Object r3 = r7.L$0
            androidx.compose.material3.SnapFlingBehavior r3 = (androidx.compose.material3.SnapFlingBehavior) r3
            k2.c.G(r0)
            r0 = r2
            goto L8d
        L3a:
            java.lang.IllegalStateException r12 = new java.lang.IllegalStateException
            java.lang.String r13 = "call to 'resume' before 'invoke' with coroutine"
            r12.<init>(r13)
            throw r12
        L42:
            k2.c.G(r0)
            kotlin.jvm.internal.h0 r1 = new kotlin.jvm.internal.h0
            r1.<init>()
            java.lang.Object r0 = r15.getVelocity()
            java.lang.Number r0 = (java.lang.Number) r0
            float r0 = r0.floatValue()
            java.lang.Float r3 = new java.lang.Float
            r3.<init>(r13)
            java.lang.Object r4 = r15.getVelocity()
            java.lang.Number r4 = (java.lang.Number) r4
            float r4 = r4.floatValue()
            r5 = 0
            int r4 = (r4 > r5 ? 1 : (r4 == r5 ? 0 : -1))
            if (r4 != 0) goto L6a
            r4 = r2
            goto L6b
        L6a:
            r4 = 0
        L6b:
            r5 = r4 ^ 1
            androidx.compose.material3.SnapFlingBehavior$animateSnap$2 r6 = new androidx.compose.material3.SnapFlingBehavior$animateSnap$2
            r6.<init>(r14, r1, r12)
            r7.L$0 = r11
            r7.L$1 = r15
            r7.L$2 = r1
            r7.F$0 = r13
            r7.F$1 = r0
            r7.label = r2
            r2 = r15
            r4 = r16
            java.lang.Object r12 = androidx.compose.animation.core.SuspendAnimationKt.animateTo(r2, r3, r4, r5, r6, r7)
            e7.a r2 = e7.a.f15033i
            if (r12 != r2) goto L8a
            return r2
        L8a:
            r3 = r11
            r12 = r0
            r0 = r15
        L8d:
            java.lang.Object r2 = r0.getVelocity()
            java.lang.Number r2 = (java.lang.Number) r2
            float r2 = r2.floatValue()
            float r2 = r3.coerceToTarget(r2, r12)
            androidx.compose.material3.AnimationResult r12 = new androidx.compose.material3.AnimationResult
            float r1 = r1.f19742i
            float r13 = r13 - r1
            java.lang.Float r10 = new java.lang.Float
            r10.<init>(r13)
            r8 = 29
            r9 = 0
            r1 = 0
            r3 = 0
            r5 = 0
            r7 = 0
            androidx.compose.animation.core.AnimationState r13 = androidx.compose.animation.core.AnimationStateKt.copy$default(r0, r1, r2, r3, r5, r7, r8, r9)
            r12.<init>(r10, r13)
            return r12
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material3.SnapFlingBehavior.animateSnap(androidx.compose.foundation.gestures.ScrollScope, float, float, androidx.compose.animation.core.AnimationState, androidx.compose.animation.core.AnimationSpec, d7.d):java.lang.Object");
    }

    private final float calculateDistanceToDesiredSnapPosition(LazyListLayoutInfo layoutInfo, LazyListItemInfo item) {
        float f10 = 2;
        return item.getOffset() - ((((getSingleAxisViewportSize(layoutInfo) - layoutInfo.getBeforeContentPadding()) - layoutInfo.getAfterContentPadding()) / f10) - (item.getSize() / f10));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final float coerceToTarget(float f10, float f11) {
        if (f11 == 0.0f) {
            return 0.0f;
        }
        return (f11 <= 0.0f ? f10 >= f11 : f10 <= f11) ? f10 : f11;
    }

    private final <T extends Comparable<? super T>> T component1(x7.e<T> eVar) {
        return (T) eVar.getStart();
    }

    private final <T extends Comparable<? super T>> T component2(x7.e<T> eVar) {
        return (T) eVar.b();
    }

    /* JADX WARN: Removed duplicated region for block: B:9:0x0034  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private final float findClosestOffset(float r4, androidx.compose.foundation.lazy.LazyListState r5) {
        /*
            r3 = this;
            x7.e r5 = findClosestOffset$calculateSnappingOffsetBounds(r5, r3)
            java.lang.Comparable r0 = r3.component1(r5)
            java.lang.Number r0 = (java.lang.Number) r0
            float r0 = r0.floatValue()
            java.lang.Comparable r5 = r3.component2(r5)
            java.lang.Number r5 = (java.lang.Number) r5
            float r5 = r5.floatValue()
            float r4 = java.lang.Math.signum(r4)
            r1 = 0
            int r2 = (r4 > r1 ? 1 : (r4 == r1 ? 0 : -1))
            if (r2 != 0) goto L2e
            float r4 = java.lang.Math.abs(r5)
            float r2 = java.lang.Math.abs(r0)
            int r4 = (r4 > r2 ? 1 : (r4 == r2 ? 0 : -1))
            if (r4 > 0) goto L3e
            goto L34
        L2e:
            r2 = 1065353216(0x3f800000, float:1.0)
            int r2 = (r4 > r2 ? 1 : (r4 == r2 ? 0 : -1))
            if (r2 != 0) goto L36
        L34:
            r0 = r5
            goto L3e
        L36:
            r5 = -1082130432(0xffffffffbf800000, float:-1.0)
            int r4 = (r4 > r5 ? 1 : (r4 == r5 ? 0 : -1))
            if (r4 != 0) goto L3d
            goto L3e
        L3d:
            r0 = r1
        L3e:
            boolean r4 = findClosestOffset$isValidDistance(r0)
            if (r4 == 0) goto L45
            return r0
        L45:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material3.SnapFlingBehavior.findClosestOffset(float, androidx.compose.foundation.lazy.LazyListState):float");
    }

    private static final x7.e<Float> findClosestOffset$calculateSnappingOffsetBounds(LazyListState lazyListState, SnapFlingBehavior snapFlingBehavior) {
        LazyListLayoutInfo layoutInfo = lazyListState.getLayoutInfo();
        List<LazyListItemInfo> visibleItemsInfo = layoutInfo.getVisibleItemsInfo();
        int size = visibleItemsInfo.size();
        float f10 = Float.NEGATIVE_INFINITY;
        float f11 = Float.POSITIVE_INFINITY;
        for (int i10 = 0; i10 < size; i10++) {
            float fCalculateDistanceToDesiredSnapPosition = snapFlingBehavior.calculateDistanceToDesiredSnapPosition(layoutInfo, visibleItemsInfo.get(i10));
            if (fCalculateDistanceToDesiredSnapPosition <= 0.0f && fCalculateDistanceToDesiredSnapPosition > f10) {
                f10 = fCalculateDistanceToDesiredSnapPosition;
            }
            if (fCalculateDistanceToDesiredSnapPosition >= 0.0f && fCalculateDistanceToDesiredSnapPosition < f11) {
                f11 = fCalculateDistanceToDesiredSnapPosition;
            }
        }
        return new x7.d(f10, f11);
    }

    private static final boolean findClosestOffset$isValidDistance(float f10) {
        return (f10 == Float.POSITIVE_INFINITY || f10 == Float.NEGATIVE_INFINITY) ? false : true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object fling(androidx.compose.foundation.gestures.ScrollScope r5, float r6, d7.d<? super androidx.compose.material3.AnimationResult<java.lang.Float, androidx.compose.animation.core.AnimationVector1D>> r7) throws java.lang.Throwable {
        /*
            r4 = this;
            boolean r0 = r7 instanceof androidx.compose.material3.SnapFlingBehavior.C05531
            if (r0 == 0) goto L13
            r0 = r7
            androidx.compose.material3.SnapFlingBehavior$fling$1 r0 = (androidx.compose.material3.SnapFlingBehavior.C05531) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            androidx.compose.material3.SnapFlingBehavior$fling$1 r0 = new androidx.compose.material3.SnapFlingBehavior$fling$1
            r0.<init>(r7)
        L18:
            java.lang.Object r7 = r0.result
            int r1 = r0.label
            r2 = 1
            if (r1 == 0) goto L2d
            if (r1 != r2) goto L25
            k2.c.G(r7)
            goto L43
        L25:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r6)
            throw r5
        L2d:
            k2.c.G(r7)
            androidx.compose.material3.SnapFlingBehavior$motionScaleDuration$1 r7 = r4.motionScaleDuration
            androidx.compose.material3.SnapFlingBehavior$fling$result$1 r1 = new androidx.compose.material3.SnapFlingBehavior$fling$result$1
            r3 = 0
            r1.<init>(r6, r4, r5, r3)
            r0.label = r2
            java.lang.Object r7 = ka.m0.y(r7, r1, r0)
            e7.a r5 = e7.a.f15033i
            if (r7 != r5) goto L43
            return r5
        L43:
            androidx.compose.material3.AnimationResult r7 = (androidx.compose.material3.AnimationResult) r7
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material3.SnapFlingBehavior.fling(androidx.compose.foundation.gestures.ScrollScope, float, d7.d):java.lang.Object");
    }

    private final float getItemSize() {
        if (getVisibleItemsInfo().isEmpty()) {
            return 0.0f;
        }
        List<LazyListItemInfo> visibleItemsInfo = getVisibleItemsInfo();
        int size = visibleItemsInfo.size();
        int size2 = 0;
        for (int i10 = 0; i10 < size; i10++) {
            size2 += visibleItemsInfo.get(i10).getSize();
        }
        return size2 / getVisibleItemsInfo().size();
    }

    private final int getSingleAxisViewportSize(LazyListLayoutInfo lazyListLayoutInfo) {
        return lazyListLayoutInfo.getOrientation() == Orientation.Vertical ? IntSize.m5843getHeightimpl(lazyListLayoutInfo.mo634getViewportSizeYbymL2g()) : IntSize.m5844getWidthimpl(lazyListLayoutInfo.mo634getViewportSizeYbymL2g());
    }

    private final List<LazyListItemInfo> getVisibleItemsInfo() {
        return this.lazyListState.getLayoutInfo().getVisibleItemsInfo();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:8:0x001c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object longSnap(androidx.compose.foundation.gestures.ScrollScope r24, float r25, d7.d<? super androidx.compose.material3.AnimationResult<java.lang.Float, androidx.compose.animation.core.AnimationVector1D>> r26) {
        /*
            r23 = this;
            r0 = r23
            r1 = r24
            r2 = r25
            r3 = r26
            boolean r4 = r3 instanceof androidx.compose.material3.SnapFlingBehavior.C05541
            if (r4 == 0) goto L1c
            r4 = r3
            androidx.compose.material3.SnapFlingBehavior$longSnap$1 r4 = (androidx.compose.material3.SnapFlingBehavior.C05541) r4
            int r5 = r4.label
            r6 = -2147483648(0xffffffff80000000, float:-0.0)
            r7 = r5 & r6
            if (r7 == 0) goto L1c
            int r5 = r5 - r6
            r4.label = r5
        L1a:
            r11 = r4
            goto L22
        L1c:
            androidx.compose.material3.SnapFlingBehavior$longSnap$1 r4 = new androidx.compose.material3.SnapFlingBehavior$longSnap$1
            r4.<init>(r3)
            goto L1a
        L22:
            java.lang.Object r3 = r11.result
            int r4 = r11.label
            r5 = 2
            r6 = 1
            e7.a r12 = e7.a.f15033i
            if (r4 == 0) goto L49
            if (r4 == r6) goto L3c
            if (r4 != r5) goto L34
            k2.c.G(r3)
            return r3
        L34:
            java.lang.IllegalStateException r1 = new java.lang.IllegalStateException
            java.lang.String r2 = "call to 'resume' before 'invoke' with coroutine"
            r1.<init>(r2)
            throw r1
        L3c:
            java.lang.Object r1 = r11.L$1
            androidx.compose.foundation.gestures.ScrollScope r1 = (androidx.compose.foundation.gestures.ScrollScope) r1
            java.lang.Object r2 = r11.L$0
            androidx.compose.material3.SnapFlingBehavior r2 = (androidx.compose.material3.SnapFlingBehavior) r2
            k2.c.G(r3)
        L47:
            r6 = r1
            goto L7a
        L49:
            k2.c.G(r3)
            androidx.compose.animation.core.DecayAnimationSpec<java.lang.Float> r3 = r0.decayAnimationSpec
            r4 = 0
            float r3 = androidx.compose.animation.core.DecayAnimationSpecKt.calculateTargetValue(r3, r4, r2)
            float r3 = java.lang.Math.abs(r3)
            float r7 = r0.getItemSize()
            float r3 = r3 - r7
            int r7 = (r3 > r4 ? 1 : (r3 == r4 ? 0 : -1))
            if (r7 >= 0) goto L61
            r3 = r4
        L61:
            int r4 = (r3 > r4 ? 1 : (r3 == r4 ? 0 : -1))
            if (r4 != 0) goto L66
            goto L6b
        L66:
            float r4 = java.lang.Math.signum(r2)
            float r3 = r3 * r4
        L6b:
            r11.L$0 = r0
            r11.L$1 = r1
            r11.label = r6
            java.lang.Object r3 = r0.runApproach(r1, r3, r2, r11)
            if (r3 != r12) goto L78
            goto Lab
        L78:
            r2 = r0
            goto L47
        L7a:
            androidx.compose.material3.AnimationResult r3 = (androidx.compose.material3.AnimationResult) r3
            java.lang.Object r1 = r3.component1()
            java.lang.Number r1 = (java.lang.Number) r1
            float r7 = r1.floatValue()
            androidx.compose.animation.core.AnimationState r13 = r3.component2()
            r21 = 30
            r22 = 0
            r14 = 0
            r15 = 0
            r16 = 0
            r18 = 0
            r20 = 0
            androidx.compose.animation.core.AnimationState r9 = androidx.compose.animation.core.AnimationStateKt.copy$default(r13, r14, r15, r16, r18, r20, r21, r22)
            androidx.compose.animation.core.AnimationSpec<java.lang.Float> r10 = r2.snapAnimationSpec
            r1 = 0
            r11.L$0 = r1
            r11.L$1 = r1
            r11.label = r5
            r8 = r7
            r5 = r2
            java.lang.Object r1 = r5.animateSnap(r6, r7, r8, r9, r10, r11)
            if (r1 != r12) goto Lac
        Lab:
            return r12
        Lac:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material3.SnapFlingBehavior.longSnap(androidx.compose.foundation.gestures.ScrollScope, float, d7.d):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0016  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object runApproach(androidx.compose.foundation.gestures.ScrollScope r16, float r17, float r18, d7.d<? super androidx.compose.material3.AnimationResult<java.lang.Float, androidx.compose.animation.core.AnimationVector1D>> r19) {
        /*
            r15 = this;
            r1 = r19
            boolean r2 = r1 instanceof androidx.compose.material3.SnapFlingBehavior.C05561
            if (r2 == 0) goto L16
            r2 = r1
            androidx.compose.material3.SnapFlingBehavior$runApproach$1 r2 = (androidx.compose.material3.SnapFlingBehavior.C05561) r2
            int r3 = r2.label
            r4 = -2147483648(0xffffffff80000000, float:-0.0)
            r5 = r3 & r4
            if (r5 == 0) goto L16
            int r3 = r3 - r4
            r2.label = r3
        L14:
            r5 = r2
            goto L1c
        L16:
            androidx.compose.material3.SnapFlingBehavior$runApproach$1 r2 = new androidx.compose.material3.SnapFlingBehavior$runApproach$1
            r2.<init>(r1)
            goto L14
        L1c:
            java.lang.Object r1 = r5.result
            int r2 = r5.label
            r3 = 1
            if (r2 == 0) goto L36
            if (r2 != r3) goto L2e
            java.lang.Object r2 = r5.L$0
            androidx.compose.material3.SnapFlingBehavior r2 = (androidx.compose.material3.SnapFlingBehavior) r2
            k2.c.G(r1)
            r0 = r2
            goto L5e
        L2e:
            java.lang.IllegalStateException r1 = new java.lang.IllegalStateException
            java.lang.String r2 = "call to 'resume' before 'invoke' with coroutine"
            r1.<init>(r2)
            throw r1
        L36:
            k2.c.G(r1)
            r13 = 28
            r14 = 0
            r6 = 0
            r8 = 0
            r10 = 0
            r12 = 0
            r7 = r18
            androidx.compose.animation.core.AnimationState r1 = androidx.compose.animation.core.AnimationStateKt.AnimationState$default(r6, r7, r8, r10, r12, r13, r14)
            androidx.compose.animation.core.DecayAnimationSpec<java.lang.Float> r4 = r15.decayAnimationSpec
            r5.L$0 = r15
            r5.label = r3
            r0 = r15
            r2 = r17
            r3 = r1
            r1 = r16
            java.lang.Object r1 = r0.animateDecay(r1, r2, r3, r4, r5)
            e7.a r0 = e7.a.f15033i
            if (r1 != r0) goto L5d
            return r0
        L5d:
            r0 = r15
        L5e:
            androidx.compose.material3.AnimationResult r1 = (androidx.compose.material3.AnimationResult) r1
            androidx.compose.animation.core.AnimationState r1 = r1.component2()
            java.lang.Object r2 = r1.getVelocity()
            java.lang.Number r2 = (java.lang.Number) r2
            float r2 = r2.floatValue()
            androidx.compose.foundation.lazy.LazyListState r3 = r0.lazyListState
            float r0 = r0.findClosestOffset(r2, r3)
            androidx.compose.material3.AnimationResult r2 = new androidx.compose.material3.AnimationResult
            java.lang.Float r3 = new java.lang.Float
            r3.<init>(r0)
            r2.<init>(r3, r1)
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material3.SnapFlingBehavior.runApproach(androidx.compose.foundation.gestures.ScrollScope, float, float, d7.d):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Object shortSnap(ScrollScope scrollScope, float f10, d7.d<? super AnimationResult<Float, AnimationVector1D>> dVar) {
        float fFindClosestOffset = findClosestOffset(0.0f, this.lazyListState);
        return animateSnap(scrollScope, fFindClosestOffset, fFindClosestOffset, AnimationStateKt.AnimationState$default(0.0f, f10, 0L, 0L, false, 28, null), this.snapAnimationSpec, dVar);
    }

    public boolean equals(Object other) {
        if (other instanceof SnapFlingBehavior) {
            SnapFlingBehavior snapFlingBehavior = (SnapFlingBehavior) other;
            if (kotlin.jvm.internal.p.a(snapFlingBehavior.snapAnimationSpec, this.snapAnimationSpec) && kotlin.jvm.internal.p.a(snapFlingBehavior.decayAnimationSpec, this.decayAnimationSpec) && kotlin.jvm.internal.p.a(snapFlingBehavior.lazyListState, this.lazyListState) && kotlin.jvm.internal.p.a(snapFlingBehavior.density, this.density)) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        return this.density.hashCode() + ((this.lazyListState.hashCode() + ((this.decayAnimationSpec.hashCode() + (this.snapAnimationSpec.hashCode() * 31)) * 31)) * 31);
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    @Override // androidx.compose.foundation.gestures.FlingBehavior
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.Object performFling(androidx.compose.foundation.gestures.ScrollScope r5, float r6, d7.d<? super java.lang.Float> r7) throws java.lang.Throwable {
        /*
            r4 = this;
            boolean r0 = r7 instanceof androidx.compose.material3.SnapFlingBehavior.C05551
            if (r0 == 0) goto L13
            r0 = r7
            androidx.compose.material3.SnapFlingBehavior$performFling$1 r0 = (androidx.compose.material3.SnapFlingBehavior.C05551) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            androidx.compose.material3.SnapFlingBehavior$performFling$1 r0 = new androidx.compose.material3.SnapFlingBehavior$performFling$1
            r0.<init>(r7)
        L18:
            java.lang.Object r7 = r0.result
            int r1 = r0.label
            r2 = 1
            if (r1 == 0) goto L2d
            if (r1 != r2) goto L25
            k2.c.G(r7)
            goto L3b
        L25:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r6)
            throw r5
        L2d:
            k2.c.G(r7)
            r0.label = r2
            java.lang.Object r7 = r4.fling(r5, r6, r0)
            e7.a r5 = e7.a.f15033i
            if (r7 != r5) goto L3b
            return r5
        L3b:
            androidx.compose.material3.AnimationResult r7 = (androidx.compose.material3.AnimationResult) r7
            java.lang.Object r5 = r7.component1()
            java.lang.Number r5 = (java.lang.Number) r5
            float r5 = r5.floatValue()
            androidx.compose.animation.core.AnimationState r6 = r7.component2()
            r7 = 0
            int r5 = (r5 > r7 ? 1 : (r5 == r7 ? 0 : -1))
            if (r5 != 0) goto L51
            goto L5b
        L51:
            java.lang.Object r5 = r6.getVelocity()
            java.lang.Number r5 = (java.lang.Number) r5
            float r7 = r5.floatValue()
        L5b:
            java.lang.Float r5 = new java.lang.Float
            r5.<init>(r7)
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material3.SnapFlingBehavior.performFling(androidx.compose.foundation.gestures.ScrollScope, float, d7.d):java.lang.Object");
    }
}
