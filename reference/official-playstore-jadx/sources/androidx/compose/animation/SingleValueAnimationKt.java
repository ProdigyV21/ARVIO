package androidx.compose.animation;

import androidx.compose.animation.core.Animatable;
import androidx.compose.animation.core.AnimateAsStateKt;
import androidx.compose.animation.core.AnimationSpec;
import androidx.compose.animation.core.AnimationSpecKt;
import androidx.compose.animation.core.AnimationVector4D;
import androidx.compose.animation.core.SpringSpec;
import androidx.compose.animation.core.TwoWayConverter;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.State;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.colorspace.ColorSpace;
import kotlin.Metadata;
import r7.l;
import x6.e;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\u001aR\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00000\t2\u0006\u0010\u0001\u001a\u00020\u00002\u000e\b\u0002\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00000\u00022\b\b\u0002\u0010\u0005\u001a\u00020\u00042\u0016\b\u0002\u0010\b\u001a\u0010\u0012\u0004\u0012\u00020\u0000\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u0006H\u0007ø\u0001\u0000¢\u0006\u0004\b\n\u0010\u000b\u001aH\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00000\t2\u0006\u0010\u0001\u001a\u00020\u00002\u000e\b\u0002\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00000\u00022\u0016\b\u0002\u0010\b\u001a\u0010\u0012\u0004\u0012\u00020\u0000\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u0006H\u0007ø\u0001\u0000¢\u0006\u0004\b\r\u0010\u000e\u001a$\u0010\u0014\u001a\u000e\u0012\u0004\u0012\u00020\u0000\u0012\u0004\u0012\u00020\u00110\u00102\u0006\u0010\u000f\u001a\u00020\u0000ø\u0001\u0000¢\u0006\u0004\b\u0012\u0010\u0013\"\u001a\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00000\u00158\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0016\u0010\u0017\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006\u0018"}, d2 = {"Landroidx/compose/ui/graphics/Color;", "targetValue", "Landroidx/compose/animation/core/AnimationSpec;", "animationSpec", "", "label", "Lkotlin/Function1;", "Lx6/t0;", "finishedListener", "Landroidx/compose/runtime/State;", "animateColorAsState-euL9pac", "(JLandroidx/compose/animation/core/AnimationSpec;Ljava/lang/String;Lr7/l;Landroidx/compose/runtime/Composer;II)Landroidx/compose/runtime/State;", "animateColorAsState", "animateColorAsState-KTwxG1Y", "(JLandroidx/compose/animation/core/AnimationSpec;Lr7/l;Landroidx/compose/runtime/Composer;II)Landroidx/compose/runtime/State;", "initialValue", "Landroidx/compose/animation/core/Animatable;", "Landroidx/compose/animation/core/AnimationVector4D;", "Animatable-8_81llA", "(J)Landroidx/compose/animation/core/Animatable;", "Animatable", "Landroidx/compose/animation/core/SpringSpec;", "colorDefaultSpring", "Landroidx/compose/animation/core/SpringSpec;", "animation_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class SingleValueAnimationKt {
    private static final SpringSpec<Color> colorDefaultSpring = AnimationSpecKt.spring$default(0.0f, 0.0f, null, 7, null);

    /* JADX INFO: renamed from: Animatable-8_81llA, reason: not valid java name */
    public static final Animatable<Color, AnimationVector4D> m67Animatable8_81llA(long j10) {
        return new Animatable<>(Color.m3462boximpl(j10), (TwoWayConverter) ColorVectorConverterKt.getVectorConverter(Color.INSTANCE).invoke(Color.m3476getColorSpaceimpl(j10)), null, null, 12, null);
    }

    @e
    /* JADX INFO: renamed from: animateColorAsState-KTwxG1Y, reason: not valid java name */
    public static final /* synthetic */ State m68animateColorAsStateKTwxG1Y(long j10, AnimationSpec animationSpec, l lVar, Composer composer, int i10, int i11) {
        composer.startReplaceableGroup(-1942442407);
        if ((i11 & 2) != 0) {
            animationSpec = colorDefaultSpring;
        }
        AnimationSpec animationSpec2 = animationSpec;
        if ((i11 & 4) != 0) {
            lVar = null;
        }
        l lVar2 = lVar;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1942442407, i10, -1, "androidx.compose.animation.animateColorAsState (SingleValueAnimation.kt:79)");
        }
        State<Color> stateM69animateColorAsStateeuL9pac = m69animateColorAsStateeuL9pac(j10, animationSpec2, null, lVar2, composer, (i10 & 14) | 64 | ((i10 << 3) & 7168), 4);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        composer.endReplaceableGroup();
        return stateM69animateColorAsStateeuL9pac;
    }

    /* JADX INFO: renamed from: animateColorAsState-euL9pac, reason: not valid java name */
    public static final State<Color> m69animateColorAsStateeuL9pac(long j10, AnimationSpec<Color> animationSpec, String str, l<? super Color, t0> lVar, Composer composer, int i10, int i11) {
        composer.startReplaceableGroup(-451899108);
        AnimationSpec<Color> animationSpec2 = (i11 & 2) != 0 ? colorDefaultSpring : animationSpec;
        String str2 = (i11 & 4) != 0 ? "ColorAnimation" : str;
        l<? super Color, t0> lVar2 = (i11 & 8) != 0 ? null : lVar;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-451899108, i10, -1, "androidx.compose.animation.animateColorAsState (SingleValueAnimation.kt:61)");
        }
        ColorSpace colorSpaceM3476getColorSpaceimpl = Color.m3476getColorSpaceimpl(j10);
        composer.startReplaceableGroup(1157296644);
        boolean zChanged = composer.changed(colorSpaceM3476getColorSpaceimpl);
        Object objRememberedValue = composer.rememberedValue();
        if (zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
            objRememberedValue = (TwoWayConverter) ColorVectorConverterKt.getVectorConverter(Color.INSTANCE).invoke(Color.m3476getColorSpaceimpl(j10));
            composer.updateRememberedValue(objRememberedValue);
        }
        composer.endReplaceableGroup();
        int i12 = i10 << 6;
        State<Color> stateAnimateValueAsState = AnimateAsStateKt.animateValueAsState(Color.m3462boximpl(j10), (TwoWayConverter) objRememberedValue, animationSpec2, null, str2, lVar2, composer, (i10 & 14) | 576 | (57344 & i12) | (i12 & 458752), 8);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        composer.endReplaceableGroup();
        return stateAnimateValueAsState;
    }
}
