package androidx.compose.animation;

import androidx.compose.animation.core.AnimationSpecKt;
import androidx.compose.animation.core.FiniteAnimationSpec;
import androidx.compose.animation.core.InfiniteRepeatableSpec;
import androidx.compose.animation.core.InfiniteTransition;
import androidx.compose.animation.core.InfiniteTransitionKt;
import androidx.compose.animation.core.SpringSpec;
import androidx.compose.animation.core.Transition;
import androidx.compose.animation.core.TwoWayConverter;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.State;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.colorspace.ColorSpace;
import io.ktor.http.ContentDisposition;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.q;
import x6.e;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000F\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\u001a\u0084\u0001\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00050\u000f\"\u0004\b\u0000\u0010\u0000*\b\u0012\u0004\u0012\u00028\u00000\u00012*\b\n\u0010\b\u001a$\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u0003\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00040\u0002¢\u0006\u0002\b\u0006¢\u0006\u0002\b\u00072\b\b\u0002\u0010\n\u001a\u00020\t2&\u0010\u000e\u001a\"\u0012\u0013\u0012\u00118\u0000¢\u0006\f\b\u000b\u0012\b\b\f\u0012\u0004\b\b(\r\u0012\u0004\u0012\u00020\u00050\u0002¢\u0006\u0002\b\u0006H\u0087\b¢\u0006\u0004\b\u0010\u0010\u0011\u001aD\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00050\u000f*\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00052\u0006\u0010\u0014\u001a\u00020\u00052\f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00050\u00152\b\b\u0002\u0010\n\u001a\u00020\tH\u0007ø\u0001\u0000¢\u0006\u0004\b\u0017\u0010\u0018\u001a:\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00050\u000f*\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00052\u0006\u0010\u0014\u001a\u00020\u00052\f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00050\u0015H\u0007ø\u0001\u0000¢\u0006\u0004\b\u0019\u0010\u001a\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006\u001b"}, d2 = {"S", "Landroidx/compose/animation/core/Transition;", "Lkotlin/Function1;", "Landroidx/compose/animation/core/Transition$Segment;", "Landroidx/compose/animation/core/FiniteAnimationSpec;", "Landroidx/compose/ui/graphics/Color;", "Landroidx/compose/runtime/Composable;", "Lx6/n;", "transitionSpec", "", "label", "Lx6/y;", ContentDisposition.Parameters.Name, "state", "targetValueByState", "Landroidx/compose/runtime/State;", "animateColor", "(Landroidx/compose/animation/core/Transition;Lr7/q;Ljava/lang/String;Lr7/q;Landroidx/compose/runtime/Composer;II)Landroidx/compose/runtime/State;", "Landroidx/compose/animation/core/InfiniteTransition;", "initialValue", "targetValue", "Landroidx/compose/animation/core/InfiniteRepeatableSpec;", "animationSpec", "animateColor-DTcfvLk", "(Landroidx/compose/animation/core/InfiniteTransition;JJLandroidx/compose/animation/core/InfiniteRepeatableSpec;Ljava/lang/String;Landroidx/compose/runtime/Composer;II)Landroidx/compose/runtime/State;", "animateColor-RIQooxk", "(Landroidx/compose/animation/core/InfiniteTransition;JJLandroidx/compose/animation/core/InfiniteRepeatableSpec;Landroidx/compose/runtime/Composer;I)Landroidx/compose/runtime/State;", "animation_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class TransitionKt {

    /* JADX INFO: Add missing generic type declarations: [S] */
    /* JADX INFO: renamed from: androidx.compose.animation.TransitionKt$animateColor$1, reason: invalid class name */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 176)
    public static final class AnonymousClass1<S> extends r implements q<Transition.Segment<S>, Composer, Integer, SpringSpec<Color>> {
        public static final AnonymousClass1 INSTANCE = new AnonymousClass1();

        public AnonymousClass1() {
            super(3);
        }

        public final SpringSpec<Color> invoke(Transition.Segment<S> segment, Composer composer, int i10) {
            composer.startReplaceableGroup(-1457805428);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1457805428, i10, -1, "androidx.compose.animation.animateColor.<anonymous> (Transition.kt:64)");
            }
            SpringSpec<Color> springSpecSpring$default = AnimationSpecKt.spring$default(0.0f, 0.0f, null, 7, null);
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

    public static final <S> State<Color> animateColor(Transition<S> transition, q<? super Transition.Segment<S>, ? super Composer, ? super Integer, ? extends FiniteAnimationSpec<Color>> qVar, String str, q<? super S, ? super Composer, ? super Integer, Color> qVar2, Composer composer, int i10, int i11) {
        composer.startReplaceableGroup(-1939694975);
        if ((i11 & 1) != 0) {
            qVar = AnonymousClass1.INSTANCE;
        }
        if ((i11 & 2) != 0) {
            str = "ColorAnimation";
        }
        String str2 = str;
        ColorSpace colorSpaceM3476getColorSpaceimpl = Color.m3476getColorSpaceimpl(((Color) qVar2.invoke(transition.getTargetState(), composer, Integer.valueOf((i10 >> 6) & 112))).m3482unboximpl());
        composer.startReplaceableGroup(1157296644);
        boolean zChanged = composer.changed(colorSpaceM3476getColorSpaceimpl);
        Object objRememberedValue = composer.rememberedValue();
        if (zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
            objRememberedValue = (TwoWayConverter) ColorVectorConverterKt.getVectorConverter(Color.INSTANCE).invoke(colorSpaceM3476getColorSpaceimpl);
            composer.updateRememberedValue(objRememberedValue);
        }
        composer.endReplaceableGroup();
        int i12 = (i10 & 14) | 64;
        int i13 = i10 << 3;
        int i14 = i12 | (i13 & 896) | (i13 & 7168) | (i13 & 57344);
        composer.startReplaceableGroup(-142660079);
        int i15 = (i14 >> 9) & 112;
        State<Color> stateCreateTransitionAnimation = androidx.compose.animation.core.TransitionKt.createTransitionAnimation(transition, qVar2.invoke(transition.getCurrentState(), composer, Integer.valueOf(i15)), qVar2.invoke(transition.getTargetState(), composer, Integer.valueOf(i15)), (FiniteAnimationSpec) qVar.invoke(transition.getSegment(), composer, Integer.valueOf((i14 >> 3) & 112)), (TwoWayConverter) objRememberedValue, str2, composer, (i14 & 14) | 32768 | ((i14 << 6) & 458752));
        composer.endReplaceableGroup();
        composer.endReplaceableGroup();
        return stateCreateTransitionAnimation;
    }

    /* JADX INFO: renamed from: animateColor-DTcfvLk, reason: not valid java name */
    public static final State<Color> m79animateColorDTcfvLk(InfiniteTransition infiniteTransition, long j10, long j11, InfiniteRepeatableSpec<Color> infiniteRepeatableSpec, String str, Composer composer, int i10, int i11) {
        composer.startReplaceableGroup(1901963533);
        if ((i11 & 8) != 0) {
            str = "ColorAnimation";
        }
        String str2 = str;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1901963533, i10, -1, "androidx.compose.animation.animateColor (Transition.kt:101)");
        }
        composer.startReplaceableGroup(-492369756);
        Object objRememberedValue = composer.rememberedValue();
        if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
            objRememberedValue = (TwoWayConverter) ColorVectorConverterKt.getVectorConverter(Color.INSTANCE).invoke(Color.m3476getColorSpaceimpl(j11));
            composer.updateRememberedValue(objRememberedValue);
        }
        composer.endReplaceableGroup();
        int i12 = i10 << 3;
        State<Color> stateAnimateValue = InfiniteTransitionKt.animateValue(infiniteTransition, Color.m3462boximpl(j10), Color.m3462boximpl(j11), (TwoWayConverter) objRememberedValue, infiniteRepeatableSpec, str2, composer, InfiniteTransition.$stable | 4096 | (i10 & 14) | (i10 & 112) | (i10 & 896) | (InfiniteRepeatableSpec.$stable << 12) | (57344 & i12) | (i12 & 458752), 0);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        composer.endReplaceableGroup();
        return stateAnimateValue;
    }

    @e
    /* JADX INFO: renamed from: animateColor-RIQooxk, reason: not valid java name */
    public static final /* synthetic */ State m80animateColorRIQooxk(InfiniteTransition infiniteTransition, long j10, long j11, InfiniteRepeatableSpec infiniteRepeatableSpec, Composer composer, int i10) {
        composer.startReplaceableGroup(1400583834);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1400583834, i10, -1, "androidx.compose.animation.animateColor (Transition.kt:117)");
        }
        State<Color> stateM79animateColorDTcfvLk = m79animateColorDTcfvLk(infiniteTransition, j10, j11, infiniteRepeatableSpec, "ColorAnimation", composer, InfiniteTransition.$stable | 24576 | (i10 & 14) | (i10 & 112) | (i10 & 896) | (InfiniteRepeatableSpec.$stable << 9) | (i10 & 7168), 0);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        composer.endReplaceableGroup();
        return stateM79animateColorDTcfvLk;
    }
}
