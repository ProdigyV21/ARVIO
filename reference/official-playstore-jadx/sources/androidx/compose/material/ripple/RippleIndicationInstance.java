package androidx.compose.material.ripple;

import androidx.compose.foundation.IndicationInstance;
import androidx.compose.foundation.interaction.Interaction;
import androidx.compose.foundation.interaction.PressInteraction;
import androidx.compose.runtime.State;
import androidx.compose.ui.graphics.drawscope.DrawScope;
import androidx.media3.extractor.text.ttml.TtmlNode;
import ka.k0;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\b \u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004¢\u0006\u0004\b\u0007\u0010\bJ\u001f\u0010\u000e\u001a\u00020\r2\u0006\u0010\n\u001a\u00020\t2\u0006\u0010\f\u001a\u00020\u000bH&¢\u0006\u0004\b\u000e\u0010\u000fJ\u0017\u0010\u0010\u001a\u00020\r2\u0006\u0010\n\u001a\u00020\tH&¢\u0006\u0004\b\u0010\u0010\u0011J\u001f\u0010\u0015\u001a\u00020\r2\u0006\u0010\n\u001a\u00020\u00122\u0006\u0010\f\u001a\u00020\u000bH\u0000¢\u0006\u0004\b\u0013\u0010\u0014J$\u0010\u001d\u001a\u00020\r*\u00020\u00162\u0006\u0010\u0018\u001a\u00020\u00172\u0006\u0010\u001a\u001a\u00020\u0019ø\u0001\u0000¢\u0006\u0004\b\u001b\u0010\u001cR\u0014\u0010\u001f\u001a\u00020\u001e8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u001f\u0010 \u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006!"}, d2 = {"Landroidx/compose/material/ripple/RippleIndicationInstance;", "Landroidx/compose/foundation/IndicationInstance;", "", "bounded", "Landroidx/compose/runtime/State;", "Landroidx/compose/material/ripple/RippleAlpha;", "rippleAlpha", "<init>", "(ZLandroidx/compose/runtime/State;)V", "Landroidx/compose/foundation/interaction/PressInteraction$Press;", "interaction", "Lka/k0;", "scope", "Lx6/t0;", "addRipple", "(Landroidx/compose/foundation/interaction/PressInteraction$Press;Lka/k0;)V", "removeRipple", "(Landroidx/compose/foundation/interaction/PressInteraction$Press;)V", "Landroidx/compose/foundation/interaction/Interaction;", "updateStateLayer$material_ripple_release", "(Landroidx/compose/foundation/interaction/Interaction;Lka/k0;)V", "updateStateLayer", "Landroidx/compose/ui/graphics/drawscope/DrawScope;", "Landroidx/compose/ui/unit/Dp;", "radius", "Landroidx/compose/ui/graphics/Color;", TtmlNode.ATTR_TTS_COLOR, "drawStateLayer-H2RKhps", "(Landroidx/compose/ui/graphics/drawscope/DrawScope;FJ)V", "drawStateLayer", "Landroidx/compose/material/ripple/StateLayer;", "stateLayer", "Landroidx/compose/material/ripple/StateLayer;", "material-ripple_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public abstract class RippleIndicationInstance implements IndicationInstance {
    public static final int $stable = 8;
    private final StateLayer stateLayer;

    public RippleIndicationInstance(boolean z, State<RippleAlpha> state) {
        this.stateLayer = new StateLayer(z, state);
    }

    public abstract void addRipple(PressInteraction.Press interaction, k0 scope);

    /* JADX INFO: renamed from: drawStateLayer-H2RKhps, reason: not valid java name */
    public final void m1207drawStateLayerH2RKhps(DrawScope drawScope, float f10, long j10) {
        this.stateLayer.m1211drawStateLayerH2RKhps(drawScope, f10, j10);
    }

    public abstract void removeRipple(PressInteraction.Press interaction);

    public final void updateStateLayer$material_ripple_release(Interaction interaction, k0 scope) {
        this.stateLayer.handleInteraction(interaction, scope);
    }
}
