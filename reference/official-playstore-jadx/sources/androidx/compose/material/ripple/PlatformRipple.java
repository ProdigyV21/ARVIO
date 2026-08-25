package androidx.compose.material.ripple;

import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import androidx.compose.foundation.interaction.InteractionSource;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.State;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.platform.AndroidCompositionLocals_androidKt;
import androidx.media3.extractor.text.ttml.TtmlNode;
import kotlin.Metadata;
import kotlin.jvm.internal.h;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0001\u0018\u00002\u00020\u0001B%\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006¢\u0006\u0004\b\t\u0010\nJ\u000f\u0010\f\u001a\u00020\u000bH\u0003¢\u0006\u0004\b\f\u0010\rJF\u0010\u0015\u001a\u00020\u00122\u0006\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u00042\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00100\u0006H\u0017ø\u0001\u0000¢\u0006\u0004\b\u0013\u0010\u0014\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006\u0016"}, d2 = {"Landroidx/compose/material/ripple/PlatformRipple;", "Landroidx/compose/material/ripple/Ripple;", "", "bounded", "Landroidx/compose/ui/unit/Dp;", "radius", "Landroidx/compose/runtime/State;", "Landroidx/compose/ui/graphics/Color;", TtmlNode.ATTR_TTS_COLOR, "<init>", "(ZFLandroidx/compose/runtime/State;Lkotlin/jvm/internal/h;)V", "Landroid/view/ViewGroup;", "findNearestViewGroup", "(Landroidx/compose/runtime/Composer;I)Landroid/view/ViewGroup;", "Landroidx/compose/foundation/interaction/InteractionSource;", "interactionSource", "Landroidx/compose/material/ripple/RippleAlpha;", "rippleAlpha", "Landroidx/compose/material/ripple/RippleIndicationInstance;", "rememberUpdatedRippleInstance-942rkJo", "(Landroidx/compose/foundation/interaction/InteractionSource;ZFLandroidx/compose/runtime/State;Landroidx/compose/runtime/State;Landroidx/compose/runtime/Composer;I)Landroidx/compose/material/ripple/RippleIndicationInstance;", "rememberUpdatedRippleInstance", "material-ripple_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class PlatformRipple extends Ripple {
    public static final int $stable = 0;

    public /* synthetic */ PlatformRipple(boolean z, float f10, State state, h hVar) {
        this(z, f10, state);
    }

    private final ViewGroup findNearestViewGroup(Composer composer, int i10) {
        composer.startReplaceableGroup(-1737891121);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1737891121, i10, -1, "androidx.compose.material.ripple.PlatformRipple.findNearestViewGroup (Ripple.android.kt:86)");
        }
        Object objConsume = composer.consume(AndroidCompositionLocals_androidKt.getLocalView());
        while (!(objConsume instanceof ViewGroup)) {
            ViewParent parent = ((View) objConsume).getParent();
            if (!(parent instanceof View)) {
                throw new IllegalArgumentException(("Couldn't find a valid parent for " + objConsume + ". Are you overriding LocalView and providing a View that is not attached to the view hierarchy?").toString());
            }
            objConsume = parent;
        }
        ViewGroup viewGroup = (ViewGroup) objConsume;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        composer.endReplaceableGroup();
        return viewGroup;
    }

    @Override // androidx.compose.material.ripple.Ripple
    /* JADX INFO: renamed from: rememberUpdatedRippleInstance-942rkJo */
    public RippleIndicationInstance mo1199rememberUpdatedRippleInstance942rkJo(InteractionSource interactionSource, boolean z, float f10, State<Color> state, State<RippleAlpha> state2, Composer composer, int i10) {
        composer.startReplaceableGroup(331259447);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(331259447, i10, -1, "androidx.compose.material.ripple.PlatformRipple.rememberUpdatedRippleInstance (Ripple.android.kt:63)");
        }
        ViewGroup viewGroupFindNearestViewGroup = findNearestViewGroup(composer, (i10 >> 15) & 14);
        composer.startReplaceableGroup(1643267293);
        if (viewGroupFindNearestViewGroup.isInEditMode()) {
            composer.startReplaceableGroup(511388516);
            boolean zChanged = composer.changed(interactionSource) | composer.changed(this);
            Object objRememberedValue = composer.rememberedValue();
            if (zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                CommonRippleIndicationInstance commonRippleIndicationInstance = new CommonRippleIndicationInstance(z, f10, state, state2, null);
                composer.updateRememberedValue(commonRippleIndicationInstance);
                objRememberedValue = commonRippleIndicationInstance;
            }
            composer.endReplaceableGroup();
            CommonRippleIndicationInstance commonRippleIndicationInstance2 = (CommonRippleIndicationInstance) objRememberedValue;
            composer.endReplaceableGroup();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            composer.endReplaceableGroup();
            return commonRippleIndicationInstance2;
        }
        composer.endReplaceableGroup();
        composer.startReplaceableGroup(1618982084);
        boolean zChanged2 = composer.changed(interactionSource) | composer.changed(this) | composer.changed(viewGroupFindNearestViewGroup);
        Object objRememberedValue2 = composer.rememberedValue();
        if (zChanged2 || objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
            AndroidRippleIndicationInstance androidRippleIndicationInstance = new AndroidRippleIndicationInstance(z, f10, state, state2, viewGroupFindNearestViewGroup, null);
            composer.updateRememberedValue(androidRippleIndicationInstance);
            objRememberedValue2 = androidRippleIndicationInstance;
        }
        composer.endReplaceableGroup();
        AndroidRippleIndicationInstance androidRippleIndicationInstance2 = (AndroidRippleIndicationInstance) objRememberedValue2;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        composer.endReplaceableGroup();
        return androidRippleIndicationInstance2;
    }

    private PlatformRipple(boolean z, float f10, State<Color> state) {
        super(z, f10, state, null);
    }
}
