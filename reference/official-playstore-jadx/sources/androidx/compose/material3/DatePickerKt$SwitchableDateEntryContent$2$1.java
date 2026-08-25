package androidx.compose.material3;

import androidx.compose.animation.AnimatedContentKt;
import androidx.compose.animation.AnimatedContentTransitionScope;
import androidx.compose.animation.ContentTransform;
import androidx.compose.animation.EnterExitTransitionKt;
import androidx.compose.animation.core.AnimationSpecKt;
import androidx.compose.animation.core.FiniteAnimationSpec;
import androidx.compose.material3.tokens.MotionTokens;
import androidx.compose.ui.unit.IntSize;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.l;
import r7.p;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001*\b\u0012\u0004\u0012\u00020\u00030\u0002H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "Landroidx/compose/animation/ContentTransform;", "Landroidx/compose/animation/AnimatedContentTransitionScope;", "Landroidx/compose/material3/DisplayMode;", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
public final class DatePickerKt$SwitchableDateEntryContent$2$1 extends r implements l<AnimatedContentTransitionScope<DisplayMode>, ContentTransform> {
    final /* synthetic */ int $parallaxTarget;

    /* JADX INFO: renamed from: androidx.compose.material3.DatePickerKt$SwitchableDateEntryContent$2$1$1, reason: invalid class name */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0001H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "", "height", "invoke", "(I)Ljava/lang/Integer;"}, k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class AnonymousClass1 extends r implements l<Integer, Integer> {
        public static final AnonymousClass1 INSTANCE = new AnonymousClass1();

        public AnonymousClass1() {
            super(1);
        }

        public final Integer invoke(int i10) {
            return Integer.valueOf(i10);
        }

        @Override // r7.l
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            return invoke(((Number) obj).intValue());
        }
    }

    /* JADX INFO: renamed from: androidx.compose.material3.DatePickerKt$SwitchableDateEntryContent$2$1$2, reason: invalid class name */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0001H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "", "<anonymous parameter 0>", "invoke", "(I)Ljava/lang/Integer;"}, k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class AnonymousClass2 extends r implements l<Integer, Integer> {
        final /* synthetic */ int $parallaxTarget;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass2(int i10) {
            super(1);
            this.$parallaxTarget = i10;
        }

        public final Integer invoke(int i10) {
            return Integer.valueOf(this.$parallaxTarget);
        }

        @Override // r7.l
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            return invoke(((Number) obj).intValue());
        }
    }

    /* JADX INFO: renamed from: androidx.compose.material3.DatePickerKt$SwitchableDateEntryContent$2$1$3, reason: invalid class name */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0001H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "", "<anonymous parameter 0>", "invoke", "(I)Ljava/lang/Integer;"}, k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class AnonymousClass3 extends r implements l<Integer, Integer> {
        final /* synthetic */ int $parallaxTarget;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass3(int i10) {
            super(1);
            this.$parallaxTarget = i10;
        }

        @Override // r7.l
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            return invoke(((Number) obj).intValue());
        }

        public final Integer invoke(int i10) {
            return Integer.valueOf(this.$parallaxTarget);
        }
    }

    /* JADX INFO: renamed from: androidx.compose.material3.DatePickerKt$SwitchableDateEntryContent$2$1$4, reason: invalid class name */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0001H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "", "fullHeight", "invoke", "(I)Ljava/lang/Integer;"}, k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class AnonymousClass4 extends r implements l<Integer, Integer> {
        public static final AnonymousClass4 INSTANCE = new AnonymousClass4();

        public AnonymousClass4() {
            super(1);
        }

        public final Integer invoke(int i10) {
            return Integer.valueOf(i10);
        }

        @Override // r7.l
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            return invoke(((Number) obj).intValue());
        }
    }

    /* JADX INFO: renamed from: androidx.compose.material3.DatePickerKt$SwitchableDateEntryContent$2$1$5, reason: invalid class name */
    @Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0004\u001a\u00020\u0002H\n¢\u0006\u0004\b\u0005\u0010\u0006"}, d2 = {"<anonymous>", "Landroidx/compose/animation/core/FiniteAnimationSpec;", "Landroidx/compose/ui/unit/IntSize;", "<anonymous parameter 0>", "<anonymous parameter 1>", "invoke-TemP2vQ", "(JJ)Landroidx/compose/animation/core/FiniteAnimationSpec;"}, k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class AnonymousClass5 extends r implements p<IntSize, IntSize, FiniteAnimationSpec<IntSize>> {
        public static final AnonymousClass5 INSTANCE = new AnonymousClass5();

        public AnonymousClass5() {
            super(2);
        }

        @Override // r7.p
        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
            return m1495invokeTemP2vQ(((IntSize) obj).getPackedValue(), ((IntSize) obj2).getPackedValue());
        }

        /* JADX INFO: renamed from: invoke-TemP2vQ, reason: not valid java name */
        public final FiniteAnimationSpec<IntSize> m1495invokeTemP2vQ(long j10, long j11) {
            return AnimationSpecKt.tween$default(500, 0, MotionTokens.INSTANCE.getEasingEmphasizedDecelerateCubicBezier(), 2, null);
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DatePickerKt$SwitchableDateEntryContent$2$1(int i10) {
        super(1);
        this.$parallaxTarget = i10;
    }

    @Override // r7.l
    public final ContentTransform invoke(AnimatedContentTransitionScope<DisplayMode> animatedContentTransitionScope) {
        return animatedContentTransitionScope.using(DisplayMode.m1526equalsimpl0(animatedContentTransitionScope.getTargetState().getValue(), DisplayMode.INSTANCE.m1530getInputjFl4v0()) ? AnimatedContentKt.togetherWith(EnterExitTransitionKt.slideInVertically$default(null, AnonymousClass1.INSTANCE, 1, null).plus(EnterExitTransitionKt.fadeIn$default(AnimationSpecKt.tween$default(100, 100, null, 4, null), 0.0f, 2, null)), EnterExitTransitionKt.fadeOut$default(AnimationSpecKt.tween$default(100, 0, null, 6, null), 0.0f, 2, null).plus(EnterExitTransitionKt.slideOutVertically$default(null, new AnonymousClass2(this.$parallaxTarget), 1, null))) : AnimatedContentKt.togetherWith(EnterExitTransitionKt.slideInVertically(AnimationSpecKt.tween$default(0, 50, null, 5, null), new AnonymousClass3(this.$parallaxTarget)).plus(EnterExitTransitionKt.fadeIn$default(AnimationSpecKt.tween$default(100, 100, null, 4, null), 0.0f, 2, null)), EnterExitTransitionKt.slideOutVertically$default(null, AnonymousClass4.INSTANCE, 1, null).plus(EnterExitTransitionKt.fadeOut$default(AnimationSpecKt.tween$default(100, 0, null, 6, null), 0.0f, 2, null))), AnimatedContentKt.SizeTransform(true, AnonymousClass5.INSTANCE));
    }
}
