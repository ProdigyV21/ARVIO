package androidx.compose.animation.core;

import androidx.compose.animation.core.Transition;
import androidx.compose.animation.core.Transition.DeferredAnimation;
import androidx.compose.animation.core.Transition.TransitionAnimationState;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.DisposableEffectResult;
import androidx.compose.runtime.DisposableEffectScope;
import androidx.compose.runtime.EffectsKt;
import androidx.compose.runtime.State;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.geometry.Size;
import androidx.compose.ui.unit.Dp;
import androidx.compose.ui.unit.IntOffset;
import androidx.compose.ui.unit.IntOffsetKt;
import androidx.compose.ui.unit.IntSize;
import androidx.compose.ui.unit.IntSizeKt;
import io.ktor.http.ContentDisposition;
import kotlin.Metadata;
import kotlin.jvm.internal.i;
import kotlin.jvm.internal.n;
import kotlin.jvm.internal.r;
import r7.l;
import r7.q;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0096\u0001\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u001a/\u0010\u0005\u001a\b\u0012\u0004\u0012\u00028\u00000\u0004\"\u0004\b\u0000\u0010\u00002\u0006\u0010\u0001\u001a\u00028\u00002\n\b\u0002\u0010\u0003\u001a\u0004\u0018\u00010\u0002H\u0007¢\u0006\u0004\b\u0005\u0010\u0006\u001a5\u0010\t\u001a\b\u0012\u0004\u0012\u00028\u00000\u0004\"\u0004\b\u0000\u0010\u00002\f\u0010\b\u001a\b\u0012\u0004\u0012\u00028\u00000\u00072\n\b\u0002\u0010\u0003\u001a\u0004\u0018\u00010\u0002H\u0007¢\u0006\u0004\b\t\u0010\n\u001a5\u0010\u0005\u001a\b\u0012\u0004\u0012\u00028\u00000\u0004\"\u0004\b\u0000\u0010\u00002\f\u0010\b\u001a\b\u0012\u0004\u0012\u00028\u00000\u000b2\n\b\u0002\u0010\u0003\u001a\u0004\u0018\u00010\u0002H\u0007¢\u0006\u0004\b\u0005\u0010\f\u001ac\u0010\u0013\u001a\u0018\u0012\u0004\u0012\u00028\u0001\u0012\u0004\u0012\u00028\u00020\u0012R\b\u0012\u0004\u0012\u00028\u00000\u0004\"\u0004\b\u0000\u0010\r\"\u0004\b\u0001\u0010\u0000\"\b\b\u0002\u0010\u000f*\u00020\u000e*\b\u0012\u0004\u0012\u00028\u00000\u00042\u0012\u0010\u0011\u001a\u000e\u0012\u0004\u0012\u00028\u0001\u0012\u0004\u0012\u00028\u00020\u00102\b\b\u0002\u0010\u0003\u001a\u00020\u0002H\u0007¢\u0006\u0004\b\u0013\u0010\u0014\u001a^\u0010\u001b\u001a\b\u0012\u0004\u0012\u00028\u00010\u0004\"\u0004\b\u0000\u0010\r\"\u0004\b\u0001\u0010\u0000*\b\u0012\u0004\u0012\u00028\u00000\u00042\b\b\u0002\u0010\u0003\u001a\u00020\u00022&\u0010\u001a\u001a\"\u0012\u0013\u0012\u00118\u0000¢\u0006\f\b\u0016\u0012\b\b\u0017\u0012\u0004\b\b(\u0018\u0012\u0004\u0012\u00028\u00010\u0015¢\u0006\u0002\b\u0019H\u0087\b¢\u0006\u0004\b\u001b\u0010\u001c\u001aC\u0010\u001f\u001a\b\u0012\u0004\u0012\u00028\u00010\u0004\"\u0004\b\u0000\u0010\r\"\u0004\b\u0001\u0010\u0000*\b\u0012\u0004\u0012\u00028\u00000\u00042\u0006\u0010\u001d\u001a\u00028\u00012\u0006\u0010\u0001\u001a\u00028\u00012\u0006\u0010\u001e\u001a\u00020\u0002H\u0001¢\u0006\u0004\b\u001f\u0010 \u001a¨\u0001\u0010(\u001a\b\u0012\u0004\u0012\u00028\u00010'\"\u0004\b\u0000\u0010\r\"\u0004\b\u0001\u0010\u0000\"\b\b\u0002\u0010\u000f*\u00020\u000e*\b\u0012\u0004\u0012\u00028\u00000\u00042\u0012\u0010\u0011\u001a\u000e\u0012\u0004\u0012\u00028\u0001\u0012\u0004\u0012\u00028\u00020\u00102*\b\n\u0010$\u001a$\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000!\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00010\"0\u0015¢\u0006\u0002\b\u0019¢\u0006\u0002\b#2\b\b\u0002\u0010\u0003\u001a\u00020\u00022&\u0010&\u001a\"\u0012\u0013\u0012\u00118\u0000¢\u0006\f\b\u0016\u0012\b\b\u0017\u0012\u0004\b\b(%\u0012\u0004\u0012\u00028\u00010\u0015¢\u0006\u0002\b\u0019H\u0087\b¢\u0006\u0004\b(\u0010)\u001ao\u0010-\u001a\b\u0012\u0004\u0012\u00028\u00010'\"\u0004\b\u0000\u0010\r\"\u0004\b\u0001\u0010\u0000\"\b\b\u0002\u0010\u000f*\u00020\u000e*\b\u0012\u0004\u0012\u00028\u00000\u00042\u0006\u0010*\u001a\u00028\u00012\u0006\u0010+\u001a\u00028\u00012\f\u0010,\u001a\b\u0012\u0004\u0012\u00028\u00010\"2\u0012\u0010\u0011\u001a\u000e\u0012\u0004\u0012\u00028\u0001\u0012\u0004\u0012\u00028\u00020\u00102\u0006\u0010\u0003\u001a\u00020\u0002H\u0001¢\u0006\u0004\b-\u0010.\u001a\u0084\u0001\u00100\u001a\b\u0012\u0004\u0012\u00020/0'\"\u0004\b\u0000\u0010\r*\b\u0012\u0004\u0012\u00028\u00000\u00042*\b\n\u0010$\u001a$\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000!\u0012\n\u0012\b\u0012\u0004\u0012\u00020/0\"0\u0015¢\u0006\u0002\b\u0019¢\u0006\u0002\b#2\b\b\u0002\u0010\u0003\u001a\u00020\u00022&\u0010&\u001a\"\u0012\u0013\u0012\u00118\u0000¢\u0006\f\b\u0016\u0012\b\b\u0017\u0012\u0004\b\b(%\u0012\u0004\u0012\u00020/0\u0015¢\u0006\u0002\b\u0019H\u0087\b¢\u0006\u0004\b0\u00101\u001a\u0084\u0001\u00103\u001a\b\u0012\u0004\u0012\u0002020'\"\u0004\b\u0000\u0010\r*\b\u0012\u0004\u0012\u00028\u00000\u00042*\b\n\u0010$\u001a$\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000!\u0012\n\u0012\b\u0012\u0004\u0012\u0002020\"0\u0015¢\u0006\u0002\b\u0019¢\u0006\u0002\b#2\b\b\u0002\u0010\u0003\u001a\u00020\u00022&\u0010&\u001a\"\u0012\u0013\u0012\u00118\u0000¢\u0006\f\b\u0016\u0012\b\b\u0017\u0012\u0004\b\b(%\u0012\u0004\u0012\u0002020\u0015¢\u0006\u0002\b\u0019H\u0087\b¢\u0006\u0004\b3\u00101\u001a\u0084\u0001\u00105\u001a\b\u0012\u0004\u0012\u0002040'\"\u0004\b\u0000\u0010\r*\b\u0012\u0004\u0012\u00028\u00000\u00042*\b\n\u0010$\u001a$\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000!\u0012\n\u0012\b\u0012\u0004\u0012\u0002040\"0\u0015¢\u0006\u0002\b\u0019¢\u0006\u0002\b#2\b\b\u0002\u0010\u0003\u001a\u00020\u00022&\u0010&\u001a\"\u0012\u0013\u0012\u00118\u0000¢\u0006\f\b\u0016\u0012\b\b\u0017\u0012\u0004\b\b(%\u0012\u0004\u0012\u0002040\u0015¢\u0006\u0002\b\u0019H\u0087\b¢\u0006\u0004\b5\u00101\u001a\u0084\u0001\u00107\u001a\b\u0012\u0004\u0012\u0002060'\"\u0004\b\u0000\u0010\r*\b\u0012\u0004\u0012\u00028\u00000\u00042*\b\n\u0010$\u001a$\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000!\u0012\n\u0012\b\u0012\u0004\u0012\u0002060\"0\u0015¢\u0006\u0002\b\u0019¢\u0006\u0002\b#2\b\b\u0002\u0010\u0003\u001a\u00020\u00022&\u0010&\u001a\"\u0012\u0013\u0012\u00118\u0000¢\u0006\f\b\u0016\u0012\b\b\u0017\u0012\u0004\b\b(%\u0012\u0004\u0012\u0002060\u0015¢\u0006\u0002\b\u0019H\u0087\b¢\u0006\u0004\b7\u00101\u001a\u0084\u0001\u00109\u001a\b\u0012\u0004\u0012\u0002080'\"\u0004\b\u0000\u0010\r*\b\u0012\u0004\u0012\u00028\u00000\u00042*\b\n\u0010$\u001a$\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000!\u0012\n\u0012\b\u0012\u0004\u0012\u0002080\"0\u0015¢\u0006\u0002\b\u0019¢\u0006\u0002\b#2\b\b\u0002\u0010\u0003\u001a\u00020\u00022&\u0010&\u001a\"\u0012\u0013\u0012\u00118\u0000¢\u0006\f\b\u0016\u0012\b\b\u0017\u0012\u0004\b\b(%\u0012\u0004\u0012\u0002080\u0015¢\u0006\u0002\b\u0019H\u0087\b¢\u0006\u0004\b9\u00101\u001a\u0084\u0001\u0010;\u001a\b\u0012\u0004\u0012\u00020:0'\"\u0004\b\u0000\u0010\r*\b\u0012\u0004\u0012\u00028\u00000\u00042*\b\n\u0010$\u001a$\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000!\u0012\n\u0012\b\u0012\u0004\u0012\u00020:0\"0\u0015¢\u0006\u0002\b\u0019¢\u0006\u0002\b#2\b\b\u0002\u0010\u0003\u001a\u00020\u00022&\u0010&\u001a\"\u0012\u0013\u0012\u00118\u0000¢\u0006\f\b\u0016\u0012\b\b\u0017\u0012\u0004\b\b(%\u0012\u0004\u0012\u00020:0\u0015¢\u0006\u0002\b\u0019H\u0087\b¢\u0006\u0004\b;\u00101\u001a\u0084\u0001\u0010=\u001a\b\u0012\u0004\u0012\u00020<0'\"\u0004\b\u0000\u0010\r*\b\u0012\u0004\u0012\u00028\u00000\u00042*\b\n\u0010$\u001a$\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000!\u0012\n\u0012\b\u0012\u0004\u0012\u00020<0\"0\u0015¢\u0006\u0002\b\u0019¢\u0006\u0002\b#2\b\b\u0002\u0010\u0003\u001a\u00020\u00022&\u0010&\u001a\"\u0012\u0013\u0012\u00118\u0000¢\u0006\f\b\u0016\u0012\b\b\u0017\u0012\u0004\b\b(%\u0012\u0004\u0012\u00020<0\u0015¢\u0006\u0002\b\u0019H\u0087\b¢\u0006\u0004\b=\u00101\u001a\u0084\u0001\u0010?\u001a\b\u0012\u0004\u0012\u00020>0'\"\u0004\b\u0000\u0010\r*\b\u0012\u0004\u0012\u00028\u00000\u00042*\b\n\u0010$\u001a$\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000!\u0012\n\u0012\b\u0012\u0004\u0012\u00020>0\"0\u0015¢\u0006\u0002\b\u0019¢\u0006\u0002\b#2\b\b\u0002\u0010\u0003\u001a\u00020\u00022&\u0010&\u001a\"\u0012\u0013\u0012\u00118\u0000¢\u0006\f\b\u0016\u0012\b\b\u0017\u0012\u0004\b\b(%\u0012\u0004\u0012\u00020>0\u0015¢\u0006\u0002\b\u0019H\u0087\b¢\u0006\u0004\b?\u00101\"\u0014\u0010@\u001a\u00020:8\u0000X\u0080T¢\u0006\u0006\n\u0004\b@\u0010A¨\u0006B"}, d2 = {"T", "targetState", "", "label", "Landroidx/compose/animation/core/Transition;", "updateTransition", "(Ljava/lang/Object;Ljava/lang/String;Landroidx/compose/runtime/Composer;II)Landroidx/compose/animation/core/Transition;", "Landroidx/compose/animation/core/TransitionState;", "transitionState", "rememberTransition", "(Landroidx/compose/animation/core/TransitionState;Ljava/lang/String;Landroidx/compose/runtime/Composer;II)Landroidx/compose/animation/core/Transition;", "Landroidx/compose/animation/core/MutableTransitionState;", "(Landroidx/compose/animation/core/MutableTransitionState;Ljava/lang/String;Landroidx/compose/runtime/Composer;II)Landroidx/compose/animation/core/Transition;", "S", "Landroidx/compose/animation/core/AnimationVector;", "V", "Landroidx/compose/animation/core/TwoWayConverter;", "typeConverter", "Landroidx/compose/animation/core/Transition$DeferredAnimation;", "createDeferredAnimation", "(Landroidx/compose/animation/core/Transition;Landroidx/compose/animation/core/TwoWayConverter;Ljava/lang/String;Landroidx/compose/runtime/Composer;II)Landroidx/compose/animation/core/Transition$DeferredAnimation;", "Lkotlin/Function1;", "Lx6/y;", ContentDisposition.Parameters.Name, "parentState", "Landroidx/compose/runtime/Composable;", "transformToChildState", "createChildTransition", "(Landroidx/compose/animation/core/Transition;Ljava/lang/String;Lr7/q;Landroidx/compose/runtime/Composer;II)Landroidx/compose/animation/core/Transition;", "initialState", "childLabel", "createChildTransitionInternal", "(Landroidx/compose/animation/core/Transition;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/String;Landroidx/compose/runtime/Composer;I)Landroidx/compose/animation/core/Transition;", "Landroidx/compose/animation/core/Transition$Segment;", "Landroidx/compose/animation/core/FiniteAnimationSpec;", "Lx6/n;", "transitionSpec", "state", "targetValueByState", "Landroidx/compose/runtime/State;", "animateValue", "(Landroidx/compose/animation/core/Transition;Landroidx/compose/animation/core/TwoWayConverter;Lr7/q;Ljava/lang/String;Lr7/q;Landroidx/compose/runtime/Composer;II)Landroidx/compose/runtime/State;", "initialValue", "targetValue", "animationSpec", "createTransitionAnimation", "(Landroidx/compose/animation/core/Transition;Ljava/lang/Object;Ljava/lang/Object;Landroidx/compose/animation/core/FiniteAnimationSpec;Landroidx/compose/animation/core/TwoWayConverter;Ljava/lang/String;Landroidx/compose/runtime/Composer;I)Landroidx/compose/runtime/State;", "", "animateFloat", "(Landroidx/compose/animation/core/Transition;Lr7/q;Ljava/lang/String;Lr7/q;Landroidx/compose/runtime/Composer;II)Landroidx/compose/runtime/State;", "Landroidx/compose/ui/unit/Dp;", "animateDp", "Landroidx/compose/ui/geometry/Offset;", "animateOffset", "Landroidx/compose/ui/geometry/Size;", "animateSize", "Landroidx/compose/ui/unit/IntOffset;", "animateIntOffset", "", "animateInt", "Landroidx/compose/ui/unit/IntSize;", "animateIntSize", "Landroidx/compose/ui/geometry/Rect;", "animateRect", "AnimationDebugDurationScale", "I", "animation-core_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class TransitionKt {
    public static final int AnimationDebugDurationScale = 1;

    /* JADX INFO: Add missing generic type declarations: [S] */
    /* JADX INFO: renamed from: androidx.compose.animation.core.TransitionKt$animateDp$1, reason: invalid class name */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 176)
    public static final class AnonymousClass1<S> extends r implements q<Transition.Segment<S>, Composer, Integer, SpringSpec<Dp>> {
        public static final AnonymousClass1 INSTANCE = new AnonymousClass1();

        public AnonymousClass1() {
            super(3);
        }

        @Override // r7.q
        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2, Object obj3) {
            return invoke((Transition.Segment) obj, (Composer) obj2, ((Number) obj3).intValue());
        }

        public final SpringSpec<Dp> invoke(Transition.Segment<S> segment, Composer composer, int i10) {
            composer.startReplaceableGroup(-575880366);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-575880366, i10, -1, "androidx.compose.animation.core.animateDp.<anonymous> (Transition.kt:1191)");
            }
            SpringSpec<Dp> springSpecSpring$default = AnimationSpecKt.spring$default(0.0f, 0.0f, Dp.m5676boximpl(VisibilityThresholdsKt.getVisibilityThreshold(Dp.INSTANCE)), 3, null);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            composer.endReplaceableGroup();
            return springSpecSpring$default;
        }
    }

    /* JADX INFO: Add missing generic type declarations: [S] */
    /* JADX INFO: renamed from: androidx.compose.animation.core.TransitionKt$animateFloat$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 176)
    public static final class C02761<S> extends r implements q<Transition.Segment<S>, Composer, Integer, SpringSpec<Float>> {
        public static final C02761 INSTANCE = new C02761();

        public C02761() {
            super(3);
        }

        public final SpringSpec<Float> invoke(Transition.Segment<S> segment, Composer composer, int i10) {
            composer.startReplaceableGroup(-522164544);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-522164544, i10, -1, "androidx.compose.animation.core.animateFloat.<anonymous> (Transition.kt:1161)");
            }
            SpringSpec<Float> springSpecSpring$default = AnimationSpecKt.spring$default(0.0f, 0.0f, null, 7, null);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            composer.endReplaceableGroup();
            return springSpecSpring$default;
        }

        @Override // r7.q
        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2, Object obj3) {
            return invoke((Transition.Segment) obj, (Composer) obj2, ((Number) obj3).intValue());
        }
    }

    /* JADX INFO: Add missing generic type declarations: [S] */
    /* JADX INFO: renamed from: androidx.compose.animation.core.TransitionKt$animateInt$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 176)
    public static final class C02771<S> extends r implements q<Transition.Segment<S>, Composer, Integer, SpringSpec<Integer>> {
        public static final C02771 INSTANCE = new C02771();

        public C02771() {
            super(3);
        }

        @Override // r7.q
        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2, Object obj3) {
            return invoke((Transition.Segment) obj, (Composer) obj2, ((Number) obj3).intValue());
        }

        public final SpringSpec<Integer> invoke(Transition.Segment<S> segment, Composer composer, int i10) {
            composer.startReplaceableGroup(-785273069);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-785273069, i10, -1, "androidx.compose.animation.core.animateInt.<anonymous> (Transition.kt:1315)");
            }
            SpringSpec<Integer> springSpecSpring$default = AnimationSpecKt.spring$default(0.0f, 0.0f, 1, 3, null);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            composer.endReplaceableGroup();
            return springSpecSpring$default;
        }
    }

    /* JADX INFO: Add missing generic type declarations: [S] */
    /* JADX INFO: renamed from: androidx.compose.animation.core.TransitionKt$animateIntOffset$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 176)
    public static final class C02781<S> extends r implements q<Transition.Segment<S>, Composer, Integer, SpringSpec<IntOffset>> {
        public static final C02781 INSTANCE = new C02781();

        public C02781() {
            super(3);
        }

        public final SpringSpec<IntOffset> invoke(Transition.Segment<S> segment, Composer composer, int i10) {
            composer.startReplaceableGroup(-1953479610);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1953479610, i10, -1, "androidx.compose.animation.core.animateIntOffset.<anonymous> (Transition.kt:1285)");
            }
            SpringSpec<IntOffset> springSpecSpring$default = AnimationSpecKt.spring$default(0.0f, 0.0f, IntOffset.m5793boximpl(IntOffsetKt.IntOffset(1, 1)), 3, null);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            composer.endReplaceableGroup();
            return springSpecSpring$default;
        }

        @Override // r7.q
        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2, Object obj3) {
            return invoke((Transition.Segment) obj, (Composer) obj2, ((Number) obj3).intValue());
        }
    }

    /* JADX INFO: Add missing generic type declarations: [S] */
    /* JADX INFO: renamed from: androidx.compose.animation.core.TransitionKt$animateIntSize$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 176)
    public static final class C02791<S> extends r implements q<Transition.Segment<S>, Composer, Integer, SpringSpec<IntSize>> {
        public static final C02791 INSTANCE = new C02791();

        public C02791() {
            super(3);
        }

        public final SpringSpec<IntSize> invoke(Transition.Segment<S> segment, Composer composer, int i10) {
            composer.startReplaceableGroup(967893300);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(967893300, i10, -1, "androidx.compose.animation.core.animateIntSize.<anonymous> (Transition.kt:1346)");
            }
            SpringSpec<IntSize> springSpecSpring$default = AnimationSpecKt.spring$default(0.0f, 0.0f, IntSize.m5836boximpl(IntSizeKt.IntSize(1, 1)), 3, null);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            composer.endReplaceableGroup();
            return springSpecSpring$default;
        }

        @Override // r7.q
        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2, Object obj3) {
            return invoke((Transition.Segment) obj, (Composer) obj2, ((Number) obj3).intValue());
        }
    }

    /* JADX INFO: Add missing generic type declarations: [S] */
    /* JADX INFO: renamed from: androidx.compose.animation.core.TransitionKt$animateOffset$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 176)
    public static final class C02801<S> extends r implements q<Transition.Segment<S>, Composer, Integer, SpringSpec<Offset>> {
        public static final C02801 INSTANCE = new C02801();

        public C02801() {
            super(3);
        }

        @Override // r7.q
        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2, Object obj3) {
            return invoke((Transition.Segment) obj, (Composer) obj2, ((Number) obj3).intValue());
        }

        public final SpringSpec<Offset> invoke(Transition.Segment<S> segment, Composer composer, int i10) {
            composer.startReplaceableGroup(1623385561);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1623385561, i10, -1, "androidx.compose.animation.core.animateOffset.<anonymous> (Transition.kt:1222)");
            }
            SpringSpec<Offset> springSpecSpring$default = AnimationSpecKt.spring$default(0.0f, 0.0f, Offset.m3225boximpl(VisibilityThresholdsKt.getVisibilityThreshold(Offset.INSTANCE)), 3, null);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            composer.endReplaceableGroup();
            return springSpecSpring$default;
        }
    }

    /* JADX INFO: Add missing generic type declarations: [S] */
    /* JADX INFO: renamed from: androidx.compose.animation.core.TransitionKt$animateRect$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 176)
    public static final class C02811<S> extends r implements q<Transition.Segment<S>, Composer, Integer, SpringSpec<Rect>> {
        public static final C02811 INSTANCE = new C02811();

        public C02811() {
            super(3);
        }

        public final SpringSpec<Rect> invoke(Transition.Segment<S> segment, Composer composer, int i10) {
            composer.startReplaceableGroup(691336298);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(691336298, i10, -1, "androidx.compose.animation.core.animateRect.<anonymous> (Transition.kt:1376)");
            }
            SpringSpec<Rect> springSpecSpring$default = AnimationSpecKt.spring$default(0.0f, 0.0f, VisibilityThresholdsKt.getVisibilityThreshold(Rect.INSTANCE), 3, null);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            composer.endReplaceableGroup();
            return springSpecSpring$default;
        }

        @Override // r7.q
        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2, Object obj3) {
            return invoke((Transition.Segment) obj, (Composer) obj2, ((Number) obj3).intValue());
        }
    }

    /* JADX INFO: Add missing generic type declarations: [S] */
    /* JADX INFO: renamed from: androidx.compose.animation.core.TransitionKt$animateSize$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 176)
    public static final class C02821<S> extends r implements q<Transition.Segment<S>, Composer, Integer, SpringSpec<Size>> {
        public static final C02821 INSTANCE = new C02821();

        public C02821() {
            super(3);
        }

        @Override // r7.q
        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2, Object obj3) {
            return invoke((Transition.Segment) obj, (Composer) obj2, ((Number) obj3).intValue());
        }

        public final SpringSpec<Size> invoke(Transition.Segment<S> segment, Composer composer, int i10) {
            composer.startReplaceableGroup(-1607152761);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1607152761, i10, -1, "androidx.compose.animation.core.animateSize.<anonymous> (Transition.kt:1253)");
            }
            SpringSpec<Size> springSpecSpring$default = AnimationSpecKt.spring$default(0.0f, 0.0f, Size.m3293boximpl(VisibilityThresholdsKt.getVisibilityThreshold(Size.INSTANCE)), 3, null);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            composer.endReplaceableGroup();
            return springSpecSpring$default;
        }
    }

    /* JADX INFO: Add missing generic type declarations: [S, T] */
    /* JADX INFO: renamed from: androidx.compose.animation.core.TransitionKt$animateValue$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 176)
    public static final class C02831<S, T> extends r implements q<Transition.Segment<S>, Composer, Integer, SpringSpec<T>> {
        public static final C02831 INSTANCE = new C02831();

        public C02831() {
            super(3);
        }

        public final SpringSpec<T> invoke(Transition.Segment<S> segment, Composer composer, int i10) {
            composer.startReplaceableGroup(-895531546);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-895531546, i10, -1, "androidx.compose.animation.core.animateValue.<anonymous> (Transition.kt:1077)");
            }
            SpringSpec<T> springSpecSpring$default = AnimationSpecKt.spring$default(0.0f, 0.0f, null, 7, null);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            composer.endReplaceableGroup();
            return springSpecSpring$default;
        }

        @Override // r7.q
        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2, Object obj3) {
            return invoke((Transition.Segment) obj, (Composer) obj2, ((Number) obj3).intValue());
        }
    }

    /* JADX INFO: renamed from: androidx.compose.animation.core.TransitionKt$createDeferredAnimation$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003\"\b\b\u0002\u0010\u0004*\u00020\u0005*\u00020\u0006H\n¢\u0006\u0002\b\u0007"}, d2 = {"<anonymous>", "Landroidx/compose/runtime/DisposableEffectResult;", "S", "T", "V", "Landroidx/compose/animation/core/AnimationVector;", "Landroidx/compose/runtime/DisposableEffectScope;", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class C02841 extends r implements l<DisposableEffectScope, DisposableEffectResult> {
        final /* synthetic */ Transition<S>.DeferredAnimation<T, V> $lazyAnim;
        final /* synthetic */ Transition<S> $this_createDeferredAnimation;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C02841(Transition<S> transition, Transition<S>.DeferredAnimation<T, V> deferredAnimation) {
            super(1);
            this.$this_createDeferredAnimation = transition;
            this.$lazyAnim = deferredAnimation;
        }

        @Override // r7.l
        public final DisposableEffectResult invoke(DisposableEffectScope disposableEffectScope) {
            final Transition<S> transition = this.$this_createDeferredAnimation;
            final Transition<S>.DeferredAnimation<T, V> deferredAnimation = this.$lazyAnim;
            return new DisposableEffectResult() { // from class: androidx.compose.animation.core.TransitionKt$createDeferredAnimation$1$invoke$$inlined$onDispose$1
                @Override // androidx.compose.runtime.DisposableEffectResult
                public void dispose() {
                    transition.removeAnimation$animation_core_release(deferredAnimation);
                }
            };
        }
    }

    public static final <S> State<Dp> animateDp(Transition<S> transition, q<? super Transition.Segment<S>, ? super Composer, ? super Integer, ? extends FiniteAnimationSpec<Dp>> qVar, String str, q<? super S, ? super Composer, ? super Integer, Dp> qVar2, Composer composer, int i10, int i11) {
        composer.startReplaceableGroup(184732935);
        if ((i11 & 1) != 0) {
            qVar = AnonymousClass1.INSTANCE;
        }
        if ((i11 & 2) != 0) {
            str = "DpAnimation";
        }
        String str2 = str;
        TwoWayConverter<Dp, AnimationVector1D> vectorConverter = VectorConvertersKt.getVectorConverter(Dp.INSTANCE);
        int i12 = i10 & 14;
        int i13 = i10 << 3;
        int i14 = i12 | (i13 & 896) | (i13 & 7168) | (i13 & 57344);
        composer.startReplaceableGroup(-142660079);
        int i15 = (i14 >> 9) & 112;
        State<Dp> stateCreateTransitionAnimation = createTransitionAnimation(transition, qVar2.invoke(transition.getCurrentState(), composer, Integer.valueOf(i15)), qVar2.invoke(transition.getTargetState(), composer, Integer.valueOf(i15)), (FiniteAnimationSpec) qVar.invoke(transition.getSegment(), composer, Integer.valueOf((i14 >> 3) & 112)), vectorConverter, str2, composer, (i14 & 14) | ((i14 << 6) & 458752));
        composer.endReplaceableGroup();
        composer.endReplaceableGroup();
        return stateCreateTransitionAnimation;
    }

    public static final <S> State<Float> animateFloat(Transition<S> transition, q<? super Transition.Segment<S>, ? super Composer, ? super Integer, ? extends FiniteAnimationSpec<Float>> qVar, String str, q<? super S, ? super Composer, ? super Integer, Float> qVar2, Composer composer, int i10, int i11) {
        composer.startReplaceableGroup(-1338768149);
        if ((i11 & 1) != 0) {
            qVar = C02761.INSTANCE;
        }
        if ((i11 & 2) != 0) {
            str = "FloatAnimation";
        }
        String str2 = str;
        TwoWayConverter<Float, AnimationVector1D> vectorConverter = VectorConvertersKt.getVectorConverter(i.f19743a);
        int i12 = i10 & 14;
        int i13 = i10 << 3;
        int i14 = i12 | (i13 & 896) | (i13 & 7168) | (i13 & 57344);
        composer.startReplaceableGroup(-142660079);
        int i15 = (i14 >> 9) & 112;
        State<Float> stateCreateTransitionAnimation = createTransitionAnimation(transition, qVar2.invoke(transition.getCurrentState(), composer, Integer.valueOf(i15)), qVar2.invoke(transition.getTargetState(), composer, Integer.valueOf(i15)), (FiniteAnimationSpec) qVar.invoke(transition.getSegment(), composer, Integer.valueOf((i14 >> 3) & 112)), vectorConverter, str2, composer, (i14 & 14) | ((i14 << 6) & 458752));
        composer.endReplaceableGroup();
        composer.endReplaceableGroup();
        return stateCreateTransitionAnimation;
    }

    public static final <S> State<Integer> animateInt(Transition<S> transition, q<? super Transition.Segment<S>, ? super Composer, ? super Integer, ? extends FiniteAnimationSpec<Integer>> qVar, String str, q<? super S, ? super Composer, ? super Integer, Integer> qVar2, Composer composer, int i10, int i11) {
        composer.startReplaceableGroup(1318902782);
        if ((i11 & 1) != 0) {
            qVar = C02771.INSTANCE;
        }
        if ((i11 & 2) != 0) {
            str = "IntAnimation";
        }
        String str2 = str;
        TwoWayConverter<Integer, AnimationVector1D> vectorConverter = VectorConvertersKt.getVectorConverter(n.f19749a);
        int i12 = i10 & 14;
        int i13 = i10 << 3;
        int i14 = i12 | (i13 & 896) | (i13 & 7168) | (i13 & 57344);
        composer.startReplaceableGroup(-142660079);
        int i15 = (i14 >> 9) & 112;
        State<Integer> stateCreateTransitionAnimation = createTransitionAnimation(transition, qVar2.invoke(transition.getCurrentState(), composer, Integer.valueOf(i15)), qVar2.invoke(transition.getTargetState(), composer, Integer.valueOf(i15)), (FiniteAnimationSpec) qVar.invoke(transition.getSegment(), composer, Integer.valueOf((i14 >> 3) & 112)), vectorConverter, str2, composer, (i14 & 14) | ((i14 << 6) & 458752));
        composer.endReplaceableGroup();
        composer.endReplaceableGroup();
        return stateCreateTransitionAnimation;
    }

    public static final <S> State<IntOffset> animateIntOffset(Transition<S> transition, q<? super Transition.Segment<S>, ? super Composer, ? super Integer, ? extends FiniteAnimationSpec<IntOffset>> qVar, String str, q<? super S, ? super Composer, ? super Integer, IntOffset> qVar2, Composer composer, int i10, int i11) {
        composer.startReplaceableGroup(776131825);
        if ((i11 & 1) != 0) {
            qVar = C02781.INSTANCE;
        }
        if ((i11 & 2) != 0) {
            str = "IntOffsetAnimation";
        }
        String str2 = str;
        TwoWayConverter<IntOffset, AnimationVector2D> vectorConverter = VectorConvertersKt.getVectorConverter(IntOffset.INSTANCE);
        int i12 = i10 & 14;
        int i13 = i10 << 3;
        int i14 = i12 | (i13 & 896) | (i13 & 7168) | (i13 & 57344);
        composer.startReplaceableGroup(-142660079);
        int i15 = (i14 >> 9) & 112;
        State<IntOffset> stateCreateTransitionAnimation = createTransitionAnimation(transition, qVar2.invoke(transition.getCurrentState(), composer, Integer.valueOf(i15)), qVar2.invoke(transition.getTargetState(), composer, Integer.valueOf(i15)), (FiniteAnimationSpec) qVar.invoke(transition.getSegment(), composer, Integer.valueOf((i14 >> 3) & 112)), vectorConverter, str2, composer, (i14 & 14) | ((i14 << 6) & 458752));
        composer.endReplaceableGroup();
        composer.endReplaceableGroup();
        return stateCreateTransitionAnimation;
    }

    public static final <S> State<IntSize> animateIntSize(Transition<S> transition, q<? super Transition.Segment<S>, ? super Composer, ? super Integer, ? extends FiniteAnimationSpec<IntSize>> qVar, String str, q<? super S, ? super Composer, ? super Integer, IntSize> qVar2, Composer composer, int i10, int i11) {
        composer.startReplaceableGroup(-2104123233);
        if ((i11 & 1) != 0) {
            qVar = C02791.INSTANCE;
        }
        if ((i11 & 2) != 0) {
            str = "IntSizeAnimation";
        }
        String str2 = str;
        TwoWayConverter<IntSize, AnimationVector2D> vectorConverter = VectorConvertersKt.getVectorConverter(IntSize.INSTANCE);
        int i12 = i10 & 14;
        int i13 = i10 << 3;
        int i14 = i12 | (i13 & 896) | (i13 & 7168) | (i13 & 57344);
        composer.startReplaceableGroup(-142660079);
        int i15 = (i14 >> 9) & 112;
        State<IntSize> stateCreateTransitionAnimation = createTransitionAnimation(transition, qVar2.invoke(transition.getCurrentState(), composer, Integer.valueOf(i15)), qVar2.invoke(transition.getTargetState(), composer, Integer.valueOf(i15)), (FiniteAnimationSpec) qVar.invoke(transition.getSegment(), composer, Integer.valueOf((i14 >> 3) & 112)), vectorConverter, str2, composer, (i14 & 14) | ((i14 << 6) & 458752));
        composer.endReplaceableGroup();
        composer.endReplaceableGroup();
        return stateCreateTransitionAnimation;
    }

    public static final <S> State<Offset> animateOffset(Transition<S> transition, q<? super Transition.Segment<S>, ? super Composer, ? super Integer, ? extends FiniteAnimationSpec<Offset>> qVar, String str, q<? super S, ? super Composer, ? super Integer, Offset> qVar2, Composer composer, int i10, int i11) {
        composer.startReplaceableGroup(2078477582);
        if ((i11 & 1) != 0) {
            qVar = C02801.INSTANCE;
        }
        if ((i11 & 2) != 0) {
            str = "OffsetAnimation";
        }
        String str2 = str;
        TwoWayConverter<Offset, AnimationVector2D> vectorConverter = VectorConvertersKt.getVectorConverter(Offset.INSTANCE);
        int i12 = i10 & 14;
        int i13 = i10 << 3;
        int i14 = i12 | (i13 & 896) | (i13 & 7168) | (i13 & 57344);
        composer.startReplaceableGroup(-142660079);
        int i15 = (i14 >> 9) & 112;
        State<Offset> stateCreateTransitionAnimation = createTransitionAnimation(transition, qVar2.invoke(transition.getCurrentState(), composer, Integer.valueOf(i15)), qVar2.invoke(transition.getTargetState(), composer, Integer.valueOf(i15)), (FiniteAnimationSpec) qVar.invoke(transition.getSegment(), composer, Integer.valueOf((i14 >> 3) & 112)), vectorConverter, str2, composer, (i14 & 14) | ((i14 << 6) & 458752));
        composer.endReplaceableGroup();
        composer.endReplaceableGroup();
        return stateCreateTransitionAnimation;
    }

    public static final <S> State<Rect> animateRect(Transition<S> transition, q<? super Transition.Segment<S>, ? super Composer, ? super Integer, ? extends FiniteAnimationSpec<Rect>> qVar, String str, q<? super S, ? super Composer, ? super Integer, Rect> qVar2, Composer composer, int i10, int i11) {
        composer.startReplaceableGroup(1496278239);
        if ((i11 & 1) != 0) {
            qVar = C02811.INSTANCE;
        }
        if ((i11 & 2) != 0) {
            str = "RectAnimation";
        }
        String str2 = str;
        TwoWayConverter<Rect, AnimationVector4D> vectorConverter = VectorConvertersKt.getVectorConverter(Rect.INSTANCE);
        int i12 = i10 & 14;
        int i13 = i10 << 3;
        int i14 = i12 | (i13 & 896) | (i13 & 7168) | (i13 & 57344);
        composer.startReplaceableGroup(-142660079);
        int i15 = (i14 >> 9) & 112;
        State<Rect> stateCreateTransitionAnimation = createTransitionAnimation(transition, qVar2.invoke(transition.getCurrentState(), composer, Integer.valueOf(i15)), qVar2.invoke(transition.getTargetState(), composer, Integer.valueOf(i15)), (FiniteAnimationSpec) qVar.invoke(transition.getSegment(), composer, Integer.valueOf((i14 >> 3) & 112)), vectorConverter, str2, composer, (i14 & 14) | ((i14 << 6) & 458752));
        composer.endReplaceableGroup();
        composer.endReplaceableGroup();
        return stateCreateTransitionAnimation;
    }

    public static final <S> State<Size> animateSize(Transition<S> transition, q<? super Transition.Segment<S>, ? super Composer, ? super Integer, ? extends FiniteAnimationSpec<Size>> qVar, String str, q<? super S, ? super Composer, ? super Integer, Size> qVar2, Composer composer, int i10, int i11) {
        composer.startReplaceableGroup(-802210820);
        if ((i11 & 1) != 0) {
            qVar = C02821.INSTANCE;
        }
        if ((i11 & 2) != 0) {
            str = "SizeAnimation";
        }
        String str2 = str;
        TwoWayConverter<Size, AnimationVector2D> vectorConverter = VectorConvertersKt.getVectorConverter(Size.INSTANCE);
        int i12 = i10 & 14;
        int i13 = i10 << 3;
        int i14 = i12 | (i13 & 896) | (i13 & 7168) | (i13 & 57344);
        composer.startReplaceableGroup(-142660079);
        int i15 = (i14 >> 9) & 112;
        State<Size> stateCreateTransitionAnimation = createTransitionAnimation(transition, qVar2.invoke(transition.getCurrentState(), composer, Integer.valueOf(i15)), qVar2.invoke(transition.getTargetState(), composer, Integer.valueOf(i15)), (FiniteAnimationSpec) qVar.invoke(transition.getSegment(), composer, Integer.valueOf((i14 >> 3) & 112)), vectorConverter, str2, composer, (i14 & 14) | ((i14 << 6) & 458752));
        composer.endReplaceableGroup();
        composer.endReplaceableGroup();
        return stateCreateTransitionAnimation;
    }

    public static final <S, T, V extends AnimationVector> State<T> animateValue(Transition<S> transition, TwoWayConverter<T, V> twoWayConverter, q<? super Transition.Segment<S>, ? super Composer, ? super Integer, ? extends FiniteAnimationSpec<T>> qVar, String str, q<? super S, ? super Composer, ? super Integer, ? extends T> qVar2, Composer composer, int i10, int i11) {
        composer.startReplaceableGroup(-142660079);
        if ((i11 & 2) != 0) {
            qVar = C02831.INSTANCE;
        }
        if ((i11 & 4) != 0) {
            str = "ValueAnimation";
        }
        int i12 = (i10 >> 9) & 112;
        State<T> stateCreateTransitionAnimation = createTransitionAnimation(transition, qVar2.invoke(transition.getCurrentState(), composer, Integer.valueOf(i12)), qVar2.invoke(transition.getTargetState(), composer, Integer.valueOf(i12)), (FiniteAnimationSpec) qVar.invoke(transition.getSegment(), composer, Integer.valueOf((i10 >> 3) & 112)), twoWayConverter, str, composer, (i10 & 14) | (57344 & (i10 << 9)) | ((i10 << 6) & 458752));
        composer.endReplaceableGroup();
        return stateCreateTransitionAnimation;
    }

    public static final <S, T> Transition<T> createChildTransition(Transition<S> transition, String str, q<? super S, ? super Composer, ? super Integer, ? extends T> qVar, Composer composer, int i10, int i11) {
        composer.startReplaceableGroup(1215497572);
        if ((i11 & 1) != 0) {
            str = "ChildTransition";
        }
        String str2 = str;
        int i12 = i10 & 14;
        composer.startReplaceableGroup(1157296644);
        boolean zChanged = composer.changed(transition);
        Object objRememberedValue = composer.rememberedValue();
        if (zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
            objRememberedValue = transition.getCurrentState();
            composer.updateRememberedValue(objRememberedValue);
        }
        composer.endReplaceableGroup();
        if (transition.isSeeking()) {
            objRememberedValue = transition.getCurrentState();
        }
        int i13 = (i10 >> 3) & 112;
        Transition<T> transitionCreateChildTransitionInternal = createChildTransitionInternal(transition, qVar.invoke(objRememberedValue, composer, Integer.valueOf(i13)), qVar.invoke(transition.getTargetState(), composer, Integer.valueOf(i13)), str2, composer, i12 | ((i10 << 6) & 7168));
        composer.endReplaceableGroup();
        return transitionCreateChildTransitionInternal;
    }

    public static final <S, T> Transition<T> createChildTransitionInternal(Transition<S> transition, T t2, T t10, String str, Composer composer, int i10) {
        composer.startReplaceableGroup(-198307638);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-198307638, i10, -1, "androidx.compose.animation.core.createChildTransitionInternal (Transition.kt:1022)");
        }
        composer.startReplaceableGroup(1157296644);
        boolean zChanged = composer.changed(transition);
        Object objRememberedValue = composer.rememberedValue();
        if (zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
            objRememberedValue = new Transition(new MutableTransitionState(t2), transition.getLabel() + " > " + str);
            composer.updateRememberedValue(objRememberedValue);
        }
        composer.endReplaceableGroup();
        Transition<T> transition2 = (Transition) objRememberedValue;
        composer.startReplaceableGroup(1951131101);
        boolean zChanged2 = composer.changed(transition) | composer.changed(transition2);
        Object objRememberedValue2 = composer.rememberedValue();
        if (zChanged2 || objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
            objRememberedValue2 = new TransitionKt$createChildTransitionInternal$1$1(transition, transition2);
            composer.updateRememberedValue(objRememberedValue2);
        }
        composer.endReplaceableGroup();
        EffectsKt.DisposableEffect(transition2, (l<? super DisposableEffectScope, ? extends DisposableEffectResult>) objRememberedValue2, composer, 0);
        if (transition.isSeeking()) {
            transition2.seek(t2, t10, transition.getLastSeekedTimeNanos());
        } else {
            transition2.updateTarget$animation_core_release(t10, composer, ((i10 >> 3) & 8) | ((i10 >> 6) & 14));
            transition2.setSeeking$animation_core_release(false);
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        composer.endReplaceableGroup();
        return transition2;
    }

    public static final <S, T, V extends AnimationVector> Transition<S>.DeferredAnimation<T, V> createDeferredAnimation(Transition<S> transition, TwoWayConverter<T, V> twoWayConverter, String str, Composer composer, int i10, int i11) {
        composer.startReplaceableGroup(-1714122528);
        if ((i11 & 2) != 0) {
            str = "DeferredAnimation";
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1714122528, i10, -1, "androidx.compose.animation.core.createDeferredAnimation (Transition.kt:976)");
        }
        composer.startReplaceableGroup(1157296644);
        boolean zChanged = composer.changed(transition);
        Object objRememberedValue = composer.rememberedValue();
        if (zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
            objRememberedValue = transition.new DeferredAnimation(twoWayConverter, str);
            composer.updateRememberedValue(objRememberedValue);
        }
        composer.endReplaceableGroup();
        Transition<S>.DeferredAnimation<T, V> deferredAnimation = (Transition.DeferredAnimation) objRememberedValue;
        EffectsKt.DisposableEffect(deferredAnimation, new C02841(transition, deferredAnimation), composer, 0);
        if (transition.isSeeking()) {
            deferredAnimation.setupSeeking$animation_core_release();
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        composer.endReplaceableGroup();
        return deferredAnimation;
    }

    public static final <S, T, V extends AnimationVector> State<T> createTransitionAnimation(Transition<S> transition, T t2, T t10, FiniteAnimationSpec<T> finiteAnimationSpec, TwoWayConverter<T, V> twoWayConverter, String str, Composer composer, int i10) {
        Transition<S> transition2;
        T t11;
        composer.startReplaceableGroup(-304821198);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-304821198, i10, -1, "androidx.compose.animation.core.createTransitionAnimation (Transition.kt:1097)");
        }
        composer.startReplaceableGroup(1157296644);
        boolean zChanged = composer.changed(transition);
        Object objRememberedValue = composer.rememberedValue();
        if (zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
            transition2 = transition;
            t11 = t2;
            Object transitionAnimationState = transition2.new TransitionAnimationState(t11, AnimationStateKt.createZeroVectorFrom(twoWayConverter, t10), twoWayConverter, str);
            composer.updateRememberedValue(transitionAnimationState);
            objRememberedValue = transitionAnimationState;
        } else {
            transition2 = transition;
            t11 = t2;
        }
        composer.endReplaceableGroup();
        Transition.TransitionAnimationState transitionAnimationState2 = (Transition.TransitionAnimationState) objRememberedValue;
        if (transition2.isSeeking()) {
            transitionAnimationState2.updateInitialAndTargetValue$animation_core_release(t11, t10, finiteAnimationSpec);
        } else {
            transitionAnimationState2.updateTargetValue$animation_core_release(t10, finiteAnimationSpec);
        }
        composer.startReplaceableGroup(1951134899);
        boolean zChanged2 = composer.changed(transition2) | composer.changed(transitionAnimationState2);
        Object objRememberedValue2 = composer.rememberedValue();
        if (zChanged2 || objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
            objRememberedValue2 = new TransitionKt$createTransitionAnimation$1$1(transition2, transitionAnimationState2);
            composer.updateRememberedValue(objRememberedValue2);
        }
        composer.endReplaceableGroup();
        EffectsKt.DisposableEffect(transitionAnimationState2, (l<? super DisposableEffectScope, ? extends DisposableEffectResult>) objRememberedValue2, composer, 0);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        composer.endReplaceableGroup();
        return transitionAnimationState2;
    }

    public static final <T> Transition<T> rememberTransition(TransitionState<T> transitionState, String str, Composer composer, int i10, int i11) {
        composer.startReplaceableGroup(1643203617);
        if ((i11 & 2) != 0) {
            str = null;
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1643203617, i10, -1, "androidx.compose.animation.core.rememberTransition (Transition.kt:316)");
        }
        composer.startReplaceableGroup(1157296644);
        boolean zChanged = composer.changed(transitionState);
        Object objRememberedValue = composer.rememberedValue();
        if (zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
            objRememberedValue = new Transition((TransitionState) transitionState, str);
            composer.updateRememberedValue(objRememberedValue);
        }
        composer.endReplaceableGroup();
        Transition<T> transition = (Transition) objRememberedValue;
        transition.animateTo$animation_core_release(transitionState.getTargetState(), composer, 0);
        composer.startReplaceableGroup(1951103416);
        boolean zChanged2 = composer.changed(transition);
        Object objRememberedValue2 = composer.rememberedValue();
        if (zChanged2 || objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
            objRememberedValue2 = new TransitionKt$rememberTransition$1$1(transition);
            composer.updateRememberedValue(objRememberedValue2);
        }
        composer.endReplaceableGroup();
        EffectsKt.DisposableEffect(transition, (l<? super DisposableEffectScope, ? extends DisposableEffectResult>) objRememberedValue2, composer, 0);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        composer.endReplaceableGroup();
        return transition;
    }

    public static final <T> Transition<T> updateTransition(T t2, String str, Composer composer, int i10, int i11) {
        composer.startReplaceableGroup(2029166765);
        if ((i11 & 2) != 0) {
            str = null;
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(2029166765, i10, -1, "androidx.compose.animation.core.updateTransition (Transition.kt:73)");
        }
        composer.startReplaceableGroup(-492369756);
        Object objRememberedValue = composer.rememberedValue();
        Composer.Companion companion = Composer.INSTANCE;
        if (objRememberedValue == companion.getEmpty()) {
            objRememberedValue = new Transition(t2, str);
            composer.updateRememberedValue(objRememberedValue);
        }
        composer.endReplaceableGroup();
        Transition<T> transition = (Transition) objRememberedValue;
        transition.animateTo$animation_core_release(t2, composer, (i10 & 8) | 48 | (i10 & 14));
        composer.startReplaceableGroup(1951093734);
        boolean zChanged = composer.changed(transition);
        Object objRememberedValue2 = composer.rememberedValue();
        if (zChanged || objRememberedValue2 == companion.getEmpty()) {
            objRememberedValue2 = new TransitionKt$updateTransition$1$1(transition);
            composer.updateRememberedValue(objRememberedValue2);
        }
        composer.endReplaceableGroup();
        EffectsKt.DisposableEffect(transition, (l<? super DisposableEffectScope, ? extends DisposableEffectResult>) objRememberedValue2, composer, 6);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        composer.endReplaceableGroup();
        return transition;
    }

    public static final <T> Transition<T> updateTransition(MutableTransitionState<T> mutableTransitionState, String str, Composer composer, int i10, int i11) {
        composer.startReplaceableGroup(882913843);
        if ((i11 & 2) != 0) {
            str = null;
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(882913843, i10, -1, "androidx.compose.animation.core.updateTransition (Transition.kt:355)");
        }
        Transition<T> transitionRememberTransition = rememberTransition(mutableTransitionState, str, composer, i10 & 126, 0);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        composer.endReplaceableGroup();
        return transitionRememberTransition;
    }
}
