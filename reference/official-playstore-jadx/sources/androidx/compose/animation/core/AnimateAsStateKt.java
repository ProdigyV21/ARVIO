package androidx.compose.animation.core;

import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.EffectsKt;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.runtime.SnapshotStateKt__SnapshotStateKt;
import androidx.compose.runtime.State;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.geometry.Size;
import androidx.compose.ui.unit.Dp;
import androidx.compose.ui.unit.IntOffset;
import androidx.compose.ui.unit.IntSize;
import androidx.media3.exoplayer.analytics.AnalyticsListener;
import ka.k0;
import kotlin.Metadata;
import kotlin.jvm.internal.i;
import kotlin.jvm.internal.n;
import kotlin.jvm.internal.r;
import m2.f0;
import ma.j;
import r7.l;
import r7.p;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000t\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\b\f\u001aY\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00000\n2\u0006\u0010\u0001\u001a\u00020\u00002\u000e\b\u0002\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00000\u00022\b\b\u0002\u0010\u0004\u001a\u00020\u00002\b\b\u0002\u0010\u0006\u001a\u00020\u00052\u0016\b\u0002\u0010\t\u001a\u0010\u0012\u0004\u0012\u00020\u0000\u0012\u0004\u0012\u00020\b\u0018\u00010\u0007H\u0007¢\u0006\u0004\b\u000b\u0010\f\u001aR\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\r0\n2\u0006\u0010\u0001\u001a\u00020\r2\u000e\b\u0002\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\r0\u00022\b\b\u0002\u0010\u0006\u001a\u00020\u00052\u0016\b\u0002\u0010\t\u001a\u0010\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\b\u0018\u00010\u0007H\u0007ø\u0001\u0000¢\u0006\u0004\b\u000e\u0010\u000f\u001aR\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00110\n2\u0006\u0010\u0001\u001a\u00020\u00112\u000e\b\u0002\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00110\u00022\b\b\u0002\u0010\u0006\u001a\u00020\u00052\u0016\b\u0002\u0010\t\u001a\u0010\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\b\u0018\u00010\u0007H\u0007ø\u0001\u0000¢\u0006\u0004\b\u0012\u0010\u0013\u001aR\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00150\n2\u0006\u0010\u0001\u001a\u00020\u00152\u000e\b\u0002\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00150\u00022\b\b\u0002\u0010\u0006\u001a\u00020\u00052\u0016\b\u0002\u0010\t\u001a\u0010\u0012\u0004\u0012\u00020\u0015\u0012\u0004\u0012\u00020\b\u0018\u00010\u0007H\u0007ø\u0001\u0000¢\u0006\u0004\b\u0016\u0010\u0013\u001aO\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u00180\n2\u0006\u0010\u0001\u001a\u00020\u00182\u000e\b\u0002\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00180\u00022\b\b\u0002\u0010\u0006\u001a\u00020\u00052\u0016\b\u0002\u0010\t\u001a\u0010\u0012\u0004\u0012\u00020\u0018\u0012\u0004\u0012\u00020\b\u0018\u00010\u0007H\u0007¢\u0006\u0004\b\u0019\u0010\u001a\u001aO\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u001b0\n2\u0006\u0010\u0001\u001a\u00020\u001b2\u000e\b\u0002\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u001b0\u00022\b\b\u0002\u0010\u0006\u001a\u00020\u00052\u0016\b\u0002\u0010\t\u001a\u0010\u0012\u0004\u0012\u00020\u001b\u0012\u0004\u0012\u00020\b\u0018\u00010\u0007H\u0007¢\u0006\u0004\b\u001c\u0010\u001d\u001aR\u0010 \u001a\b\u0012\u0004\u0012\u00020\u001e0\n2\u0006\u0010\u0001\u001a\u00020\u001e2\u000e\b\u0002\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u001e0\u00022\b\b\u0002\u0010\u0006\u001a\u00020\u00052\u0016\b\u0002\u0010\t\u001a\u0010\u0012\u0004\u0012\u00020\u001e\u0012\u0004\u0012\u00020\b\u0018\u00010\u0007H\u0007ø\u0001\u0000¢\u0006\u0004\b\u001f\u0010\u0013\u001aR\u0010#\u001a\b\u0012\u0004\u0012\u00020!0\n2\u0006\u0010\u0001\u001a\u00020!2\u000e\b\u0002\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020!0\u00022\b\b\u0002\u0010\u0006\u001a\u00020\u00052\u0016\b\u0002\u0010\t\u001a\u0010\u0012\u0004\u0012\u00020!\u0012\u0004\u0012\u00020\b\u0018\u00010\u0007H\u0007ø\u0001\u0000¢\u0006\u0004\b\"\u0010\u0013\u001a\u007f\u0010)\u001a\b\u0012\u0004\u0012\u00028\u00000\n\"\u0004\b\u0000\u0010$\"\b\b\u0001\u0010&*\u00020%2\u0006\u0010\u0001\u001a\u00028\u00002\u0012\u0010(\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010'2\u000e\b\u0002\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u00022\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00018\u00002\b\b\u0002\u0010\u0006\u001a\u00020\u00052\u0016\b\u0002\u0010\t\u001a\u0010\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\b\u0018\u00010\u0007H\u0007¢\u0006\u0004\b)\u0010*\u001aO\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00000\n2\u0006\u0010\u0001\u001a\u00020\u00002\u000e\b\u0002\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00000\u00022\b\b\u0002\u0010\u0004\u001a\u00020\u00002\u0016\b\u0002\u0010\t\u001a\u0010\u0012\u0004\u0012\u00020\u0000\u0012\u0004\u0012\u00020\b\u0018\u00010\u0007H\u0007¢\u0006\u0004\b\u000b\u0010+\u001aH\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\r0\n2\u0006\u0010\u0001\u001a\u00020\r2\u000e\b\u0002\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\r0\u00022\u0016\b\u0002\u0010\t\u001a\u0010\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\b\u0018\u00010\u0007H\u0007ø\u0001\u0000¢\u0006\u0004\b,\u0010-\u001aH\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00110\n2\u0006\u0010\u0001\u001a\u00020\u00112\u000e\b\u0002\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00110\u00022\u0016\b\u0002\u0010\t\u001a\u0010\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\b\u0018\u00010\u0007H\u0007ø\u0001\u0000¢\u0006\u0004\b.\u0010/\u001aH\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00150\n2\u0006\u0010\u0001\u001a\u00020\u00152\u000e\b\u0002\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00150\u00022\u0016\b\u0002\u0010\t\u001a\u0010\u0012\u0004\u0012\u00020\u0015\u0012\u0004\u0012\u00020\b\u0018\u00010\u0007H\u0007ø\u0001\u0000¢\u0006\u0004\b0\u0010/\u001aE\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u00180\n2\u0006\u0010\u0001\u001a\u00020\u00182\u000e\b\u0002\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00180\u00022\u0016\b\u0002\u0010\t\u001a\u0010\u0012\u0004\u0012\u00020\u0018\u0012\u0004\u0012\u00020\b\u0018\u00010\u0007H\u0007¢\u0006\u0004\b\u0019\u00101\u001aE\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u001b0\n2\u0006\u0010\u0001\u001a\u00020\u001b2\u000e\b\u0002\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u001b0\u00022\u0016\b\u0002\u0010\t\u001a\u0010\u0012\u0004\u0012\u00020\u001b\u0012\u0004\u0012\u00020\b\u0018\u00010\u0007H\u0007¢\u0006\u0004\b\u001c\u00102\u001aH\u0010 \u001a\b\u0012\u0004\u0012\u00020\u001e0\n2\u0006\u0010\u0001\u001a\u00020\u001e2\u000e\b\u0002\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u001e0\u00022\u0016\b\u0002\u0010\t\u001a\u0010\u0012\u0004\u0012\u00020\u001e\u0012\u0004\u0012\u00020\b\u0018\u00010\u0007H\u0007ø\u0001\u0000¢\u0006\u0004\b3\u0010/\u001aH\u0010#\u001a\b\u0012\u0004\u0012\u00020!0\n2\u0006\u0010\u0001\u001a\u00020!2\u000e\b\u0002\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020!0\u00022\u0016\b\u0002\u0010\t\u001a\u0010\u0012\u0004\u0012\u00020!\u0012\u0004\u0012\u00020\b\u0018\u00010\u0007H\u0007ø\u0001\u0000¢\u0006\u0004\b4\u0010/\u001au\u0010)\u001a\b\u0012\u0004\u0012\u00028\u00000\n\"\u0004\b\u0000\u0010$\"\b\b\u0001\u0010&*\u00020%2\u0006\u0010\u0001\u001a\u00028\u00002\u0012\u0010(\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010'2\u000e\b\u0002\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u00022\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00018\u00002\u0016\b\u0002\u0010\t\u001a\u0010\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\b\u0018\u00010\u0007H\u0007¢\u0006\u0004\b)\u00105\"\u001a\u00107\u001a\b\u0012\u0004\u0012\u00020\u0000068\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b7\u00108\"\u001a\u00109\u001a\b\u0012\u0004\u0012\u00020\r068\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b9\u00108\"\u001a\u0010:\u001a\b\u0012\u0004\u0012\u00020\u0011068\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b:\u00108\"\u001a\u0010;\u001a\b\u0012\u0004\u0012\u00020\u0015068\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b;\u00108\"\u001a\u0010<\u001a\b\u0012\u0004\u0012\u00020\u0018068\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b<\u00108\"\u001a\u0010=\u001a\b\u0012\u0004\u0012\u00020\u001b068\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b=\u00108\"\u001a\u0010>\u001a\b\u0012\u0004\u0012\u00020\u001e068\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b>\u00108\"\u001a\u0010?\u001a\b\u0012\u0004\u0012\u00020!068\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b?\u00108\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006B²\u0006*\u0010@\u001a\u0010\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\b\u0018\u00010\u0007\"\u0004\b\u0000\u0010$\"\b\b\u0001\u0010&*\u00020%8\nX\u008a\u0084\u0002²\u0006\"\u0010A\u001a\b\u0012\u0004\u0012\u00028\u00000\u0002\"\u0004\b\u0000\u0010$\"\b\b\u0001\u0010&*\u00020%8\nX\u008a\u0084\u0002"}, d2 = {"", "targetValue", "Landroidx/compose/animation/core/AnimationSpec;", "animationSpec", "visibilityThreshold", "", "label", "Lkotlin/Function1;", "Lx6/t0;", "finishedListener", "Landroidx/compose/runtime/State;", "animateFloatAsState", "(FLandroidx/compose/animation/core/AnimationSpec;FLjava/lang/String;Lr7/l;Landroidx/compose/runtime/Composer;II)Landroidx/compose/runtime/State;", "Landroidx/compose/ui/unit/Dp;", "animateDpAsState-AjpBEmI", "(FLandroidx/compose/animation/core/AnimationSpec;Ljava/lang/String;Lr7/l;Landroidx/compose/runtime/Composer;II)Landroidx/compose/runtime/State;", "animateDpAsState", "Landroidx/compose/ui/geometry/Size;", "animateSizeAsState-YLp_XPw", "(JLandroidx/compose/animation/core/AnimationSpec;Ljava/lang/String;Lr7/l;Landroidx/compose/runtime/Composer;II)Landroidx/compose/runtime/State;", "animateSizeAsState", "Landroidx/compose/ui/geometry/Offset;", "animateOffsetAsState-7362WCg", "animateOffsetAsState", "Landroidx/compose/ui/geometry/Rect;", "animateRectAsState", "(Landroidx/compose/ui/geometry/Rect;Landroidx/compose/animation/core/AnimationSpec;Ljava/lang/String;Lr7/l;Landroidx/compose/runtime/Composer;II)Landroidx/compose/runtime/State;", "", "animateIntAsState", "(ILandroidx/compose/animation/core/AnimationSpec;Ljava/lang/String;Lr7/l;Landroidx/compose/runtime/Composer;II)Landroidx/compose/runtime/State;", "Landroidx/compose/ui/unit/IntOffset;", "animateIntOffsetAsState-HyPO7BM", "animateIntOffsetAsState", "Landroidx/compose/ui/unit/IntSize;", "animateIntSizeAsState-4goxYXU", "animateIntSizeAsState", "T", "Landroidx/compose/animation/core/AnimationVector;", "V", "Landroidx/compose/animation/core/TwoWayConverter;", "typeConverter", "animateValueAsState", "(Ljava/lang/Object;Landroidx/compose/animation/core/TwoWayConverter;Landroidx/compose/animation/core/AnimationSpec;Ljava/lang/Object;Ljava/lang/String;Lr7/l;Landroidx/compose/runtime/Composer;II)Landroidx/compose/runtime/State;", "(FLandroidx/compose/animation/core/AnimationSpec;FLr7/l;Landroidx/compose/runtime/Composer;II)Landroidx/compose/runtime/State;", "animateDpAsState-Kz89ssw", "(FLandroidx/compose/animation/core/AnimationSpec;Lr7/l;Landroidx/compose/runtime/Composer;II)Landroidx/compose/runtime/State;", "animateSizeAsState-LjSzlW0", "(JLandroidx/compose/animation/core/AnimationSpec;Lr7/l;Landroidx/compose/runtime/Composer;II)Landroidx/compose/runtime/State;", "animateOffsetAsState-N6fFfp4", "(Landroidx/compose/ui/geometry/Rect;Landroidx/compose/animation/core/AnimationSpec;Lr7/l;Landroidx/compose/runtime/Composer;II)Landroidx/compose/runtime/State;", "(ILandroidx/compose/animation/core/AnimationSpec;Lr7/l;Landroidx/compose/runtime/Composer;II)Landroidx/compose/runtime/State;", "animateIntOffsetAsState-8f6pmRE", "animateIntSizeAsState-zTRF_AQ", "(Ljava/lang/Object;Landroidx/compose/animation/core/TwoWayConverter;Landroidx/compose/animation/core/AnimationSpec;Ljava/lang/Object;Lr7/l;Landroidx/compose/runtime/Composer;II)Landroidx/compose/runtime/State;", "Landroidx/compose/animation/core/SpringSpec;", "defaultAnimation", "Landroidx/compose/animation/core/SpringSpec;", "dpDefaultSpring", "sizeDefaultSpring", "offsetDefaultSpring", "rectDefaultSpring", "intDefaultSpring", "intOffsetDefaultSpring", "intSizeDefaultSpring", "listener", "animSpec", "animation-core_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class AnimateAsStateKt {
    private static final SpringSpec<Float> defaultAnimation = AnimationSpecKt.spring$default(0.0f, 0.0f, null, 7, null);
    private static final SpringSpec<Dp> dpDefaultSpring = AnimationSpecKt.spring$default(0.0f, 0.0f, Dp.m5676boximpl(VisibilityThresholdsKt.getVisibilityThreshold(Dp.INSTANCE)), 3, null);
    private static final SpringSpec<Size> sizeDefaultSpring = AnimationSpecKt.spring$default(0.0f, 0.0f, Size.m3293boximpl(VisibilityThresholdsKt.getVisibilityThreshold(Size.INSTANCE)), 3, null);
    private static final SpringSpec<Offset> offsetDefaultSpring = AnimationSpecKt.spring$default(0.0f, 0.0f, Offset.m3225boximpl(VisibilityThresholdsKt.getVisibilityThreshold(Offset.INSTANCE)), 3, null);
    private static final SpringSpec<Rect> rectDefaultSpring = AnimationSpecKt.spring$default(0.0f, 0.0f, VisibilityThresholdsKt.getVisibilityThreshold(Rect.INSTANCE), 3, null);
    private static final SpringSpec<Integer> intDefaultSpring = AnimationSpecKt.spring$default(0.0f, 0.0f, Integer.valueOf(VisibilityThresholdsKt.getVisibilityThreshold(n.f19749a)), 3, null);
    private static final SpringSpec<IntOffset> intOffsetDefaultSpring = AnimationSpecKt.spring$default(0.0f, 0.0f, IntOffset.m5793boximpl(VisibilityThresholdsKt.getVisibilityThreshold(IntOffset.INSTANCE)), 3, null);
    private static final SpringSpec<IntSize> intSizeDefaultSpring = AnimationSpecKt.spring$default(0.0f, 0.0f, IntSize.m5836boximpl(VisibilityThresholdsKt.getVisibilityThreshold(IntSize.INSTANCE)), 3, null);

    /* JADX INFO: renamed from: androidx.compose.animation.core.AnimateAsStateKt$animateValueAsState$2, reason: invalid class name */
    @Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0006\u001a\u00020\u0003\"\u0004\b\u0000\u0010\u0000\"\b\b\u0001\u0010\u0002*\u00020\u0001H\n¢\u0006\u0004\b\u0004\u0010\u0005"}, d2 = {"T", "Landroidx/compose/animation/core/AnimationVector;", "V", "Lx6/t0;", "invoke", "()V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class AnonymousClass2 extends r implements r7.a<t0> {
        final /* synthetic */ j<T> $channel;
        final /* synthetic */ T $targetValue;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass2(j<T> jVar, T t2) {
            super(0);
            this.$channel = jVar;
            this.$targetValue = t2;
        }

        @Override // r7.a
        public /* bridge */ /* synthetic */ Object invoke() {
            m91invoke();
            return t0.f22605a;
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
        /* JADX INFO: renamed from: invoke, reason: collision with other method in class */
        public final void m91invoke() {
            this.$channel.mo6685trySendJP2dKIU(this.$targetValue);
        }
    }

    /* JADX INFO: renamed from: androidx.compose.animation.core.AnimateAsStateKt$animateValueAsState$3, reason: invalid class name */
    @Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0005\u001a\u00020\u0004\"\u0004\b\u0000\u0010\u0000\"\b\b\u0001\u0010\u0002*\u00020\u0001*\u00020\u0003H\u008a@¢\u0006\u0004\b\u0005\u0010\u0006"}, d2 = {"T", "Landroidx/compose/animation/core/AnimationVector;", "V", "Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {1, 8, 0})
    @f7.e(c = "androidx.compose.animation.core.AnimateAsStateKt$animateValueAsState$3", f = "AnimateAsState.kt", l = {419}, m = "invokeSuspend")
    public static final class AnonymousClass3 extends f7.j implements p<k0, d7.d<? super t0>, Object> {
        final /* synthetic */ State<AnimationSpec<T>> $animSpec$delegate;
        final /* synthetic */ Animatable<T, V> $animatable;
        final /* synthetic */ j<T> $channel;
        final /* synthetic */ State<l<T, t0>> $listener$delegate;
        private /* synthetic */ Object L$0;
        Object L$1;
        int label;

        /* JADX INFO: renamed from: androidx.compose.animation.core.AnimateAsStateKt$animateValueAsState$3$1, reason: invalid class name */
        @Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0005\u001a\u00020\u0004\"\u0004\b\u0000\u0010\u0000\"\b\b\u0001\u0010\u0002*\u00020\u0001*\u00020\u0003H\u008a@¢\u0006\u0004\b\u0005\u0010\u0006"}, d2 = {"T", "Landroidx/compose/animation/core/AnimationVector;", "V", "Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {1, 8, 0})
        @f7.e(c = "androidx.compose.animation.core.AnimateAsStateKt$animateValueAsState$3$1", f = "AnimateAsState.kt", l = {428}, m = "invokeSuspend")
        public static final class AnonymousClass1 extends f7.j implements p<k0, d7.d<? super t0>, Object> {
            final /* synthetic */ State<AnimationSpec<T>> $animSpec$delegate;
            final /* synthetic */ Animatable<T, V> $animatable;
            final /* synthetic */ State<l<T, t0>> $listener$delegate;
            final /* synthetic */ T $newTarget;
            int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            public AnonymousClass1(T t2, Animatable<T, V> animatable, State<? extends AnimationSpec<T>> state, State<? extends l<? super T, t0>> state2, d7.d<? super AnonymousClass1> dVar) {
                super(2, dVar);
                this.$newTarget = t2;
                this.$animatable = animatable;
                this.$animSpec$delegate = state;
                this.$listener$delegate = state2;
            }

            @Override // f7.a
            public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
                return new AnonymousClass1(this.$newTarget, this.$animatable, this.$animSpec$delegate, this.$listener$delegate, dVar);
            }

            @Override // f7.a
            public final Object invokeSuspend(Object obj) {
                AnonymousClass1 anonymousClass1;
                int i10 = this.label;
                if (i10 == 0) {
                    k2.c.G(obj);
                    if (kotlin.jvm.internal.p.a(this.$newTarget, this.$animatable.getTargetValue())) {
                        return t0.f22605a;
                    }
                    Animatable<T, V> animatable = this.$animatable;
                    T t2 = this.$newTarget;
                    AnimationSpec animationSpecAnimateValueAsState$lambda$6 = AnimateAsStateKt.animateValueAsState$lambda$6(this.$animSpec$delegate);
                    this.label = 1;
                    anonymousClass1 = this;
                    Object objAnimateTo$default = Animatable.animateTo$default(animatable, t2, animationSpecAnimateValueAsState$lambda$6, null, null, anonymousClass1, 12, null);
                    e7.a aVar = e7.a.f15033i;
                    if (objAnimateTo$default == aVar) {
                        return aVar;
                    }
                } else {
                    if (i10 != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    k2.c.G(obj);
                    anonymousClass1 = this;
                }
                l lVarAnimateValueAsState$lambda$4 = AnimateAsStateKt.animateValueAsState$lambda$4(anonymousClass1.$listener$delegate);
                if (lVarAnimateValueAsState$lambda$4 != null) {
                    lVarAnimateValueAsState$lambda$4.invoke(anonymousClass1.$animatable.getValue());
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
        public AnonymousClass3(j<T> jVar, Animatable<T, V> animatable, State<? extends AnimationSpec<T>> state, State<? extends l<? super T, t0>> state2, d7.d<? super AnonymousClass3> dVar) {
            super(2, dVar);
            this.$channel = jVar;
            this.$animatable = animatable;
            this.$animSpec$delegate = state;
            this.$listener$delegate = state2;
        }

        @Override // f7.a
        public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
            AnonymousClass3 anonymousClass3 = new AnonymousClass3(this.$channel, this.$animatable, this.$animSpec$delegate, this.$listener$delegate, dVar);
            anonymousClass3.L$0 = obj;
            return anonymousClass3;
        }

        /* JADX WARN: Removed duplicated region for block: B:11:0x0037 A[RETURN] */
        /* JADX WARN: Removed duplicated region for block: B:14:0x0040  */
        /* JADX WARN: Removed duplicated region for block: B:19:0x0066  */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:10:0x0035 -> B:12:0x0038). Please report as a decompilation issue!!! */
        @Override // f7.a
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r11) {
            /*
                r10 = this;
                int r0 = r10.label
                r1 = 1
                if (r0 == 0) goto L1b
                if (r0 != r1) goto L13
                java.lang.Object r0 = r10.L$1
                ma.l r0 = (ma.l) r0
                java.lang.Object r2 = r10.L$0
                ka.k0 r2 = (ka.k0) r2
                k2.c.G(r11)
                goto L38
            L13:
                java.lang.IllegalStateException r11 = new java.lang.IllegalStateException
                java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
                r11.<init>(r0)
                throw r11
            L1b:
                k2.c.G(r11)
                java.lang.Object r11 = r10.L$0
                ka.k0 r11 = (ka.k0) r11
                ma.j<T> r0 = r10.$channel
                ma.l r0 = r0.iterator()
                r2 = r11
            L29:
                r10.L$0 = r2
                r10.L$1 = r0
                r10.label = r1
                java.lang.Object r11 = r0.a(r10)
                e7.a r3 = e7.a.f15033i
                if (r11 != r3) goto L38
                return r3
            L38:
                java.lang.Boolean r11 = (java.lang.Boolean) r11
                boolean r11 = r11.booleanValue()
                if (r11 == 0) goto L66
                java.lang.Object r11 = r0.next()
                ma.j<T> r3 = r10.$channel
                java.lang.Object r3 = r3.b()
                java.lang.Object r3 = ma.o.a(r3)
                if (r3 != 0) goto L52
                r5 = r11
                goto L53
            L52:
                r5 = r3
            L53:
                androidx.compose.animation.core.AnimateAsStateKt$animateValueAsState$3$1 r4 = new androidx.compose.animation.core.AnimateAsStateKt$animateValueAsState$3$1
                androidx.compose.animation.core.Animatable<T, V> r6 = r10.$animatable
                androidx.compose.runtime.State<androidx.compose.animation.core.AnimationSpec<T>> r7 = r10.$animSpec$delegate
                androidx.compose.runtime.State<r7.l<T, x6.t0>> r8 = r10.$listener$delegate
                r9 = 0
                r4.<init>(r5, r6, r7, r8, r9)
                r11 = 3
                r3 = 0
                r5 = 0
                ka.m0.p(r2, r5, r3, r4, r11)
                goto L29
            L66:
                x6.t0 r11 = x6.t0.f22605a
                return r11
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.compose.animation.core.AnimateAsStateKt.AnonymousClass3.invokeSuspend(java.lang.Object):java.lang.Object");
        }

        @Override // r7.p
        public final Object invoke(k0 k0Var, d7.d<? super t0> dVar) {
            return ((AnonymousClass3) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
        }
    }

    /* JADX INFO: renamed from: animateDpAsState-AjpBEmI, reason: not valid java name */
    public static final State<Dp> m81animateDpAsStateAjpBEmI(float f10, AnimationSpec<Dp> animationSpec, String str, l<? super Dp, t0> lVar, Composer composer, int i10, int i11) {
        composer.startReplaceableGroup(-1407150062);
        if ((i11 & 2) != 0) {
            animationSpec = dpDefaultSpring;
        }
        AnimationSpec<Dp> animationSpec2 = animationSpec;
        if ((i11 & 4) != 0) {
            str = "DpAnimation";
        }
        String str2 = str;
        if ((i11 & 8) != 0) {
            lVar = null;
        }
        l<? super Dp, t0> lVar2 = lVar;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1407150062, i10, -1, "androidx.compose.animation.core.animateDpAsState (AnimateAsState.kt:113)");
        }
        int i12 = i10 << 6;
        State<Dp> stateAnimateValueAsState = animateValueAsState(Dp.m5676boximpl(f10), VectorConvertersKt.getVectorConverter(Dp.INSTANCE), animationSpec2, null, str2, lVar2, composer, (i10 & 14) | ((i10 << 3) & 896) | (57344 & i12) | (i12 & 458752), 8);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        composer.endReplaceableGroup();
        return stateAnimateValueAsState;
    }

    @x6.e
    /* JADX INFO: renamed from: animateDpAsState-Kz89ssw, reason: not valid java name */
    public static final /* synthetic */ State m82animateDpAsStateKz89ssw(float f10, AnimationSpec animationSpec, l lVar, Composer composer, int i10, int i11) {
        composer.startReplaceableGroup(704104481);
        if ((i11 & 2) != 0) {
            animationSpec = dpDefaultSpring;
        }
        AnimationSpec animationSpec2 = animationSpec;
        if ((i11 & 4) != 0) {
            lVar = null;
        }
        l lVar2 = lVar;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(704104481, i10, -1, "androidx.compose.animation.core.animateDpAsState (AnimateAsState.kt:462)");
        }
        State stateAnimateValueAsState = animateValueAsState(Dp.m5676boximpl(f10), VectorConvertersKt.getVectorConverter(Dp.INSTANCE), animationSpec2, null, null, lVar2, composer, (i10 & 14) | ((i10 << 3) & 896) | ((i10 << 9) & 458752), 24);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        composer.endReplaceableGroup();
        return stateAnimateValueAsState;
    }

    public static final State<Float> animateFloatAsState(float f10, AnimationSpec<Float> animationSpec, float f11, String str, l<? super Float, t0> lVar, Composer composer, int i10, int i11) {
        composer.startReplaceableGroup(668842840);
        if ((i11 & 2) != 0) {
            animationSpec = defaultAnimation;
        }
        if ((i11 & 4) != 0) {
            f11 = 0.01f;
        }
        if ((i11 & 8) != 0) {
            str = "FloatAnimation";
        }
        String str2 = str;
        l<? super Float, t0> lVar2 = (i11 & 16) != 0 ? null : lVar;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(668842840, i10, -1, "androidx.compose.animation.core.animateFloatAsState (AnimateAsState.kt:68)");
        }
        composer.startReplaceableGroup(841393662);
        if (animationSpec == defaultAnimation) {
            Float fValueOf = Float.valueOf(f11);
            composer.startReplaceableGroup(1157296644);
            boolean zChanged = composer.changed(fValueOf);
            Object objRememberedValue = composer.rememberedValue();
            if (zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                objRememberedValue = AnimationSpecKt.spring$default(0.0f, 0.0f, Float.valueOf(f11), 3, null);
                composer.updateRememberedValue(objRememberedValue);
            }
            composer.endReplaceableGroup();
            animationSpec = (AnimationSpec) objRememberedValue;
        }
        composer.endReplaceableGroup();
        Float fValueOf2 = Float.valueOf(f10);
        TwoWayConverter<Float, AnimationVector1D> vectorConverter = VectorConvertersKt.getVectorConverter(i.f19743a);
        Float fValueOf3 = Float.valueOf(f11);
        int i12 = i10 << 3;
        State<Float> stateAnimateValueAsState = animateValueAsState(fValueOf2, vectorConverter, animationSpec, fValueOf3, str2, lVar2, composer, (i10 & 14) | (i12 & 7168) | (57344 & i12) | (i12 & 458752), 0);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        composer.endReplaceableGroup();
        return stateAnimateValueAsState;
    }

    public static final State<Integer> animateIntAsState(int i10, AnimationSpec<Integer> animationSpec, String str, l<? super Integer, t0> lVar, Composer composer, int i11, int i12) {
        composer.startReplaceableGroup(428074472);
        if ((i12 & 2) != 0) {
            animationSpec = intDefaultSpring;
        }
        AnimationSpec<Integer> animationSpec2 = animationSpec;
        if ((i12 & 4) != 0) {
            str = "IntAnimation";
        }
        String str2 = str;
        if ((i12 & 8) != 0) {
            lVar = null;
        }
        l<? super Integer, t0> lVar2 = lVar;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(428074472, i11, -1, "androidx.compose.animation.core.animateIntAsState (AnimateAsState.kt:273)");
        }
        int i13 = i11 << 6;
        State<Integer> stateAnimateValueAsState = animateValueAsState(Integer.valueOf(i10), VectorConvertersKt.getVectorConverter(n.f19749a), animationSpec2, null, str2, lVar2, composer, (i11 & 14) | ((i11 << 3) & 896) | (57344 & i13) | (i13 & 458752), 8);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        composer.endReplaceableGroup();
        return stateAnimateValueAsState;
    }

    @x6.e
    /* JADX INFO: renamed from: animateIntOffsetAsState-8f6pmRE, reason: not valid java name */
    public static final /* synthetic */ State m83animateIntOffsetAsState8f6pmRE(long j10, AnimationSpec animationSpec, l lVar, Composer composer, int i10, int i11) {
        composer.startReplaceableGroup(1010307371);
        if ((i11 & 2) != 0) {
            animationSpec = intOffsetDefaultSpring;
        }
        AnimationSpec animationSpec2 = animationSpec;
        if ((i11 & 4) != 0) {
            lVar = null;
        }
        l lVar2 = lVar;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1010307371, i10, -1, "androidx.compose.animation.core.animateIntOffsetAsState (AnimateAsState.kt:543)");
        }
        State stateAnimateValueAsState = animateValueAsState(IntOffset.m5793boximpl(j10), VectorConvertersKt.getVectorConverter(IntOffset.INSTANCE), animationSpec2, null, null, lVar2, composer, (i10 & 14) | ((i10 << 3) & 896) | ((i10 << 9) & 458752), 24);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        composer.endReplaceableGroup();
        return stateAnimateValueAsState;
    }

    /* JADX INFO: renamed from: animateIntOffsetAsState-HyPO7BM, reason: not valid java name */
    public static final State<IntOffset> m84animateIntOffsetAsStateHyPO7BM(long j10, AnimationSpec<IntOffset> animationSpec, String str, l<? super IntOffset, t0> lVar, Composer composer, int i10, int i11) {
        composer.startReplaceableGroup(-696782904);
        if ((i11 & 2) != 0) {
            animationSpec = intOffsetDefaultSpring;
        }
        AnimationSpec<IntOffset> animationSpec2 = animationSpec;
        if ((i11 & 4) != 0) {
            str = "IntOffsetAnimation";
        }
        String str2 = str;
        if ((i11 & 8) != 0) {
            lVar = null;
        }
        l<? super IntOffset, t0> lVar2 = lVar;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-696782904, i10, -1, "androidx.compose.animation.core.animateIntOffsetAsState (AnimateAsState.kt:313)");
        }
        int i12 = i10 << 6;
        State<IntOffset> stateAnimateValueAsState = animateValueAsState(IntOffset.m5793boximpl(j10), VectorConvertersKt.getVectorConverter(IntOffset.INSTANCE), animationSpec2, null, str2, lVar2, composer, (i10 & 14) | ((i10 << 3) & 896) | (57344 & i12) | (i12 & 458752), 8);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        composer.endReplaceableGroup();
        return stateAnimateValueAsState;
    }

    /* JADX INFO: renamed from: animateIntSizeAsState-4goxYXU, reason: not valid java name */
    public static final State<IntSize> m85animateIntSizeAsState4goxYXU(long j10, AnimationSpec<IntSize> animationSpec, String str, l<? super IntSize, t0> lVar, Composer composer, int i10, int i11) {
        composer.startReplaceableGroup(582576328);
        if ((i11 & 2) != 0) {
            animationSpec = intSizeDefaultSpring;
        }
        AnimationSpec<IntSize> animationSpec2 = animationSpec;
        if ((i11 & 4) != 0) {
            str = "IntSizeAnimation";
        }
        String str2 = str;
        if ((i11 & 8) != 0) {
            lVar = null;
        }
        l<? super IntSize, t0> lVar2 = lVar;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(582576328, i10, -1, "androidx.compose.animation.core.animateIntSizeAsState (AnimateAsState.kt:351)");
        }
        int i12 = i10 << 6;
        State<IntSize> stateAnimateValueAsState = animateValueAsState(IntSize.m5836boximpl(j10), VectorConvertersKt.getVectorConverter(IntSize.INSTANCE), animationSpec2, null, str2, lVar2, composer, (i10 & 14) | ((i10 << 3) & 896) | (57344 & i12) | (i12 & 458752), 8);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        composer.endReplaceableGroup();
        return stateAnimateValueAsState;
    }

    @x6.e
    /* JADX INFO: renamed from: animateIntSizeAsState-zTRF_AQ, reason: not valid java name */
    public static final /* synthetic */ State m86animateIntSizeAsStatezTRF_AQ(long j10, AnimationSpec animationSpec, l lVar, Composer composer, int i10, int i11) {
        composer.startReplaceableGroup(-1749239765);
        if ((i11 & 2) != 0) {
            animationSpec = intSizeDefaultSpring;
        }
        AnimationSpec animationSpec2 = animationSpec;
        if ((i11 & 4) != 0) {
            lVar = null;
        }
        l lVar2 = lVar;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1749239765, i10, -1, "androidx.compose.animation.core.animateIntSizeAsState (AnimateAsState.kt:558)");
        }
        State stateAnimateValueAsState = animateValueAsState(IntSize.m5836boximpl(j10), VectorConvertersKt.getVectorConverter(IntSize.INSTANCE), animationSpec2, null, null, lVar2, composer, (i10 & 14) | ((i10 << 3) & 896) | ((i10 << 9) & 458752), 24);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        composer.endReplaceableGroup();
        return stateAnimateValueAsState;
    }

    /* JADX INFO: renamed from: animateOffsetAsState-7362WCg, reason: not valid java name */
    public static final State<Offset> m87animateOffsetAsState7362WCg(long j10, AnimationSpec<Offset> animationSpec, String str, l<? super Offset, t0> lVar, Composer composer, int i10, int i11) {
        composer.startReplaceableGroup(357896800);
        if ((i11 & 2) != 0) {
            animationSpec = offsetDefaultSpring;
        }
        AnimationSpec<Offset> animationSpec2 = animationSpec;
        if ((i11 & 4) != 0) {
            str = "OffsetAnimation";
        }
        String str2 = str;
        if ((i11 & 8) != 0) {
            lVar = null;
        }
        l<? super Offset, t0> lVar2 = lVar;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(357896800, i10, -1, "androidx.compose.animation.core.animateOffsetAsState (AnimateAsState.kt:194)");
        }
        int i12 = i10 << 6;
        State<Offset> stateAnimateValueAsState = animateValueAsState(Offset.m3225boximpl(j10), VectorConvertersKt.getVectorConverter(Offset.INSTANCE), animationSpec2, null, str2, lVar2, composer, (i10 & 14) | ((i10 << 3) & 896) | (57344 & i12) | (i12 & 458752), 8);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        composer.endReplaceableGroup();
        return stateAnimateValueAsState;
    }

    @x6.e
    /* JADX INFO: renamed from: animateOffsetAsState-N6fFfp4, reason: not valid java name */
    public static final /* synthetic */ State m88animateOffsetAsStateN6fFfp4(long j10, AnimationSpec animationSpec, l lVar, Composer composer, int i10, int i11) {
        composer.startReplaceableGroup(-456513133);
        if ((i11 & 2) != 0) {
            animationSpec = offsetDefaultSpring;
        }
        AnimationSpec animationSpec2 = animationSpec;
        if ((i11 & 4) != 0) {
            lVar = null;
        }
        l lVar2 = lVar;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-456513133, i10, -1, "androidx.compose.animation.core.animateOffsetAsState (AnimateAsState.kt:498)");
        }
        State stateAnimateValueAsState = animateValueAsState(Offset.m3225boximpl(j10), VectorConvertersKt.getVectorConverter(Offset.INSTANCE), animationSpec2, null, null, lVar2, composer, (i10 & 14) | ((i10 << 3) & 896) | ((i10 << 9) & 458752), 24);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        composer.endReplaceableGroup();
        return stateAnimateValueAsState;
    }

    public static final State<Rect> animateRectAsState(Rect rect, AnimationSpec<Rect> animationSpec, String str, l<? super Rect, t0> lVar, Composer composer, int i10, int i11) {
        composer.startReplaceableGroup(536062978);
        if ((i11 & 2) != 0) {
            animationSpec = rectDefaultSpring;
        }
        AnimationSpec<Rect> animationSpec2 = animationSpec;
        if ((i11 & 4) != 0) {
            str = "RectAnimation";
        }
        String str2 = str;
        if ((i11 & 8) != 0) {
            lVar = null;
        }
        l<? super Rect, t0> lVar2 = lVar;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(536062978, i10, -1, "androidx.compose.animation.core.animateRectAsState (AnimateAsState.kt:235)");
        }
        int i12 = i10 << 6;
        State<Rect> stateAnimateValueAsState = animateValueAsState(rect, VectorConvertersKt.getVectorConverter(Rect.INSTANCE), animationSpec2, null, str2, lVar2, composer, (i10 & 14) | ((i10 << 3) & 896) | (57344 & i12) | (i12 & 458752), 8);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        composer.endReplaceableGroup();
        return stateAnimateValueAsState;
    }

    @x6.e
    /* JADX INFO: renamed from: animateSizeAsState-LjSzlW0, reason: not valid java name */
    public static final /* synthetic */ State m89animateSizeAsStateLjSzlW0(long j10, AnimationSpec animationSpec, l lVar, Composer composer, int i10, int i11) {
        composer.startReplaceableGroup(875212471);
        if ((i11 & 2) != 0) {
            animationSpec = sizeDefaultSpring;
        }
        AnimationSpec animationSpec2 = animationSpec;
        if ((i11 & 4) != 0) {
            lVar = null;
        }
        l lVar2 = lVar;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(875212471, i10, -1, "androidx.compose.animation.core.animateSizeAsState (AnimateAsState.kt:480)");
        }
        State stateAnimateValueAsState = animateValueAsState(Size.m3293boximpl(j10), VectorConvertersKt.getVectorConverter(Size.INSTANCE), animationSpec2, null, null, lVar2, composer, (i10 & 14) | ((i10 << 3) & 896) | ((i10 << 9) & 458752), 24);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        composer.endReplaceableGroup();
        return stateAnimateValueAsState;
    }

    /* JADX INFO: renamed from: animateSizeAsState-YLp_XPw, reason: not valid java name */
    public static final State<Size> m90animateSizeAsStateYLp_XPw(long j10, AnimationSpec<Size> animationSpec, String str, l<? super Size, t0> lVar, Composer composer, int i10, int i11) {
        composer.startReplaceableGroup(1374633148);
        if ((i11 & 2) != 0) {
            animationSpec = sizeDefaultSpring;
        }
        AnimationSpec<Size> animationSpec2 = animationSpec;
        if ((i11 & 4) != 0) {
            str = "SizeAnimation";
        }
        String str2 = str;
        if ((i11 & 8) != 0) {
            lVar = null;
        }
        l<? super Size, t0> lVar2 = lVar;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1374633148, i10, -1, "androidx.compose.animation.core.animateSizeAsState (AnimateAsState.kt:154)");
        }
        int i12 = i10 << 6;
        State<Size> stateAnimateValueAsState = animateValueAsState(Size.m3293boximpl(j10), VectorConvertersKt.getVectorConverter(Size.INSTANCE), animationSpec2, null, str2, lVar2, composer, (i10 & 14) | ((i10 << 3) & 896) | (57344 & i12) | (i12 & 458752), 8);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        composer.endReplaceableGroup();
        return stateAnimateValueAsState;
    }

    public static final <T, V extends AnimationVector> State<T> animateValueAsState(T t2, TwoWayConverter<T, V> twoWayConverter, AnimationSpec<T> animationSpec, T t10, String str, l<? super T, t0> lVar, Composer composer, int i10, int i11) {
        AnimationSpec<T> animationSpecSpring;
        composer.startReplaceableGroup(-1994373980);
        if ((i11 & 4) != 0) {
            composer.startReplaceableGroup(-492369756);
            Object objRememberedValue = composer.rememberedValue();
            if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                objRememberedValue = AnimationSpecKt.spring$default(0.0f, 0.0f, null, 7, null);
                composer.updateRememberedValue(objRememberedValue);
            }
            composer.endReplaceableGroup();
            animationSpecSpring = (AnimationSpec) objRememberedValue;
        } else {
            animationSpecSpring = animationSpec;
        }
        T t11 = (i11 & 8) != 0 ? null : t10;
        String str2 = (i11 & 16) != 0 ? "ValueAnimation" : str;
        l<? super T, t0> lVar2 = (i11 & 32) != 0 ? null : lVar;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1994373980, i10, -1, "androidx.compose.animation.core.animateValueAsState (AnimateAsState.kt:397)");
        }
        composer.startReplaceableGroup(-492369756);
        Object objRememberedValue2 = composer.rememberedValue();
        Composer.Companion companion = Composer.INSTANCE;
        if (objRememberedValue2 == companion.getEmpty()) {
            objRememberedValue2 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(null, null, 2, null);
            composer.updateRememberedValue(objRememberedValue2);
        }
        composer.endReplaceableGroup();
        MutableState mutableState = (MutableState) objRememberedValue2;
        composer.startReplaceableGroup(-492369756);
        Object objRememberedValue3 = composer.rememberedValue();
        if (objRememberedValue3 == companion.getEmpty()) {
            objRememberedValue3 = new Animatable(t2, twoWayConverter, t11, str2);
            composer.updateRememberedValue(objRememberedValue3);
        }
        composer.endReplaceableGroup();
        Animatable animatable = (Animatable) objRememberedValue3;
        State stateRememberUpdatedState = SnapshotStateKt.rememberUpdatedState(lVar2, composer, (i10 >> 15) & 14);
        if (t11 != null && (animationSpecSpring instanceof SpringSpec)) {
            SpringSpec springSpec = (SpringSpec) animationSpecSpring;
            if (!kotlin.jvm.internal.p.a(springSpec.getVisibilityThreshold(), t11)) {
                animationSpecSpring = AnimationSpecKt.spring(springSpec.getDampingRatio(), springSpec.getStiffness(), t11);
            }
        }
        State stateRememberUpdatedState2 = SnapshotStateKt.rememberUpdatedState(animationSpecSpring, composer, 0);
        composer.startReplaceableGroup(-492369756);
        Object objRememberedValue4 = composer.rememberedValue();
        if (objRememberedValue4 == companion.getEmpty()) {
            objRememberedValue4 = f0.a(-1, 0, 6);
            composer.updateRememberedValue(objRememberedValue4);
        }
        composer.endReplaceableGroup();
        j jVar = (j) objRememberedValue4;
        EffectsKt.SideEffect(new AnonymousClass2(jVar, t2), composer, 0);
        EffectsKt.LaunchedEffect(jVar, new AnonymousClass3(jVar, animatable, stateRememberUpdatedState2, stateRememberUpdatedState, null), composer, 72);
        State<T> stateAsState = (State) mutableState.getValue();
        if (stateAsState == null) {
            stateAsState = animatable.asState();
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        composer.endReplaceableGroup();
        return stateAsState;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final <T> l<T, t0> animateValueAsState$lambda$4(State<? extends l<? super T, t0>> state) {
        return state.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final <T> AnimationSpec<T> animateValueAsState$lambda$6(State<? extends AnimationSpec<T>> state) {
        return state.getValue();
    }

    @x6.e
    public static final /* synthetic */ State animateRectAsState(Rect rect, AnimationSpec animationSpec, l lVar, Composer composer, int i10, int i11) {
        composer.startReplaceableGroup(-782613967);
        if ((i11 & 2) != 0) {
            animationSpec = rectDefaultSpring;
        }
        AnimationSpec animationSpec2 = animationSpec;
        if ((i11 & 4) != 0) {
            lVar = null;
        }
        l lVar2 = lVar;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-782613967, i10, -1, "androidx.compose.animation.core.animateRectAsState (AnimateAsState.kt:513)");
        }
        State stateAnimateValueAsState = animateValueAsState(rect, VectorConvertersKt.getVectorConverter(Rect.INSTANCE), animationSpec2, null, null, lVar2, composer, (i10 & 14) | ((i10 << 3) & 896) | ((i10 << 9) & 458752), 24);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        composer.endReplaceableGroup();
        return stateAnimateValueAsState;
    }

    @x6.e
    public static final /* synthetic */ State animateIntAsState(int i10, AnimationSpec animationSpec, l lVar, Composer composer, int i11, int i12) {
        composer.startReplaceableGroup(-842612981);
        if ((i12 & 2) != 0) {
            animationSpec = intDefaultSpring;
        }
        AnimationSpec animationSpec2 = animationSpec;
        if ((i12 & 4) != 0) {
            lVar = null;
        }
        l lVar2 = lVar;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-842612981, i11, -1, "androidx.compose.animation.core.animateIntAsState (AnimateAsState.kt:528)");
        }
        State stateAnimateValueAsState = animateValueAsState(Integer.valueOf(i10), VectorConvertersKt.getVectorConverter(n.f19749a), animationSpec2, null, null, lVar2, composer, (i11 & 14) | ((i11 << 3) & 896) | ((i11 << 9) & 458752), 24);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        composer.endReplaceableGroup();
        return stateAnimateValueAsState;
    }

    @x6.e
    public static final /* synthetic */ State animateFloatAsState(float f10, AnimationSpec animationSpec, float f11, l lVar, Composer composer, int i10, int i11) {
        composer.startReplaceableGroup(1091643291);
        if ((i11 & 2) != 0) {
            animationSpec = defaultAnimation;
        }
        AnimationSpec animationSpec2 = animationSpec;
        if ((i11 & 4) != 0) {
            f11 = 0.01f;
        }
        float f12 = f11;
        if ((i11 & 8) != 0) {
            lVar = null;
        }
        l lVar2 = lVar;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1091643291, i10, -1, "androidx.compose.animation.core.animateFloatAsState (AnimateAsState.kt:446)");
        }
        State<Float> stateAnimateFloatAsState = animateFloatAsState(f10, animationSpec2, f12, null, lVar2, composer, (i10 & AnalyticsListener.EVENT_DRM_SESSION_ACQUIRED) | ((i10 << 3) & 57344), 8);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        composer.endReplaceableGroup();
        return stateAnimateFloatAsState;
    }

    @x6.e
    public static final /* synthetic */ State animateValueAsState(Object obj, TwoWayConverter twoWayConverter, AnimationSpec animationSpec, Object obj2, l lVar, Composer composer, int i10, int i11) {
        AnimationSpec animationSpec2;
        composer.startReplaceableGroup(-846382129);
        if ((i11 & 4) != 0) {
            composer.startReplaceableGroup(-492369756);
            Object objRememberedValue = composer.rememberedValue();
            if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                objRememberedValue = AnimationSpecKt.spring$default(0.0f, 0.0f, null, 7, null);
                composer.updateRememberedValue(objRememberedValue);
            }
            composer.endReplaceableGroup();
            animationSpec2 = (AnimationSpec) objRememberedValue;
        } else {
            animationSpec2 = animationSpec;
        }
        Object obj3 = (i11 & 8) != 0 ? null : obj2;
        l lVar2 = (i11 & 16) != 0 ? null : lVar;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-846382129, i10, -1, "androidx.compose.animation.core.animateValueAsState (AnimateAsState.kt:575)");
        }
        int i12 = i10 & 8;
        State stateAnimateValueAsState = animateValueAsState(obj, twoWayConverter, animationSpec2, obj3, "ValueAnimation", lVar2, composer, (i12 << 9) | i12 | 24576 | (i10 & 14) | (i10 & 112) | (i10 & 896) | (i10 & 7168) | ((i10 << 3) & 458752), 0);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        composer.endReplaceableGroup();
        return stateAnimateValueAsState;
    }
}
