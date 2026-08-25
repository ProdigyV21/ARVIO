package androidx.compose.material3;

import androidx.compose.foundation.BackgroundKt;
import androidx.compose.foundation.layout.BoxKt;
import androidx.compose.material3.tokens.NavigationBarTokens;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.State;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.GraphicsLayerModifierKt;
import androidx.compose.ui.layout.LayoutIdKt;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.l;
import r7.p;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\b\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0003\u001a\u00020\u0000H\u000b¢\u0006\u0004\b\u0001\u0010\u0002"}, d2 = {"Lx6/t0;", "invoke", "(Landroidx/compose/runtime/Composer;I)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
public final class NavigationBarKt$NavigationBarItem$3$indicator$1 extends r implements p<Composer, Integer, t0> {
    final /* synthetic */ State<Float> $animationProgress;
    final /* synthetic */ NavigationBarItemColors $colors;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public NavigationBarKt$NavigationBarItem$3$indicator$1(State<Float> state, NavigationBarItemColors navigationBarItemColors) {
        super(2);
        this.$animationProgress = state;
        this.$colors = navigationBarItemColors;
    }

    @Override // r7.p
    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
        invoke((Composer) obj, ((Number) obj2).intValue());
        return t0.f22605a;
    }

    public final void invoke(Composer composer, int i10) {
        if ((i10 & 3) == 2 && composer.getSkipping()) {
            composer.skipToGroupEnd();
            return;
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-474426875, i10, -1, "androidx.compose.material3.NavigationBarItem.<anonymous>.<anonymous> (NavigationBar.kt:250)");
        }
        Modifier modifierLayoutId = LayoutIdKt.layoutId(Modifier.INSTANCE, "indicator");
        composer.startReplaceableGroup(1844203561);
        boolean zChanged = composer.changed(this.$animationProgress);
        State<Float> state = this.$animationProgress;
        Object objRememberedValue = composer.rememberedValue();
        if (zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
            objRememberedValue = new NavigationBarKt$NavigationBarItem$3$indicator$1$1$1(state);
            composer.updateRememberedValue(objRememberedValue);
        }
        composer.endReplaceableGroup();
        BoxKt.Box(BackgroundKt.m178backgroundbw27NRU(GraphicsLayerModifierKt.graphicsLayer(modifierLayoutId, (l) objRememberedValue), this.$colors.getSelectedIndicatorColor(), ShapesKt.getValue(NavigationBarTokens.INSTANCE.getActiveIndicatorShape(), composer, 6)), composer, 0);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
    }
}
