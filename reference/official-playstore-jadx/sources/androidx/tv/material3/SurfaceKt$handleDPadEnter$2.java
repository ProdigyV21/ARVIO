package androidx.tv.material3;

import androidx.compose.foundation.interaction.MutableInteractionSource;
import androidx.compose.foundation.interaction.PressInteraction;
import androidx.compose.foundation.interaction.PressInteractionKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionScopedCoroutineScopeCanceller;
import androidx.compose.runtime.EffectsKt;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.SnapshotStateKt__SnapshotStateKt;
import androidx.compose.runtime.State;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.focus.FocusChangedModifierKt;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.input.key.KeyInputModifierKt;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0001H\u000b¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"<anonymous>", "Landroidx/compose/ui/Modifier;", "invoke", "(Landroidx/compose/ui/Modifier;Landroidx/compose/runtime/Composer;I)Landroidx/compose/ui/Modifier;"}, k = 3, mv = {1, 8, 0}, xi = 48)
final class SurfaceKt$handleDPadEnter$2 extends kotlin.jvm.internal.r implements r7.q<Modifier, Composer, Integer, Modifier> {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ boolean f5737i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ MutableInteractionSource f5738l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ r7.a f5739m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ r7.a f5740n;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SurfaceKt$handleDPadEnter$2(boolean z, MutableInteractionSource mutableInteractionSource, r7.a aVar, r7.a aVar2) {
        super(3);
        this.f5737i = z;
        this.f5738l = mutableInteractionSource;
        this.f5739m = aVar;
        this.f5740n = aVar2;
    }

    @Override // r7.q
    public final /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2, Object obj3) {
        return invoke((Modifier) obj, (Composer) obj2, ((Number) obj3).intValue());
    }

    public final Modifier invoke(Modifier modifier, Composer composer, int i10) {
        composer.startReplaceableGroup(-631124006);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-631124006, i10, -1, "androidx.tv.material3.handleDPadEnter.<anonymous> (Surface.kt:518)");
        }
        if (!this.f5737i) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            composer.endReplaceableGroup();
            return modifier;
        }
        Object objG = a0.c.g(773894976, composer, -492369756);
        Composer.Companion companion = Composer.INSTANCE;
        if (objG == companion.getEmpty()) {
            objG = com.arflix.tv.data.repository.g.h(EffectsKt.createCompositionCoroutineScope(d7.k.f14688i, composer), composer);
        }
        composer.endReplaceableGroup();
        ka.k0 coroutineScope = ((CompositionScopedCoroutineScopeCanceller) objG).getCoroutineScope();
        composer.endReplaceableGroup();
        composer.startReplaceableGroup(-1139414937);
        Object objRememberedValue = composer.rememberedValue();
        if (objRememberedValue == companion.getEmpty()) {
            objRememberedValue = new PressInteraction.Press(Offset.INSTANCE.m3252getZeroF1C5BW0(), null);
            composer.updateRememberedValue(objRememberedValue);
        }
        PressInteraction.Press press = (PressInteraction.Press) objRememberedValue;
        Object objL = androidx.compose.foundation.c.l(composer, -1139412519);
        if (objL == companion.getEmpty()) {
            objL = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(Boolean.FALSE, null, 2, null);
            composer.updateRememberedValue(objL);
        }
        MutableState mutableState = (MutableState) objL;
        composer.endReplaceableGroup();
        MutableInteractionSource mutableInteractionSource = this.f5738l;
        State<Boolean> stateCollectIsPressedAsState = PressInteractionKt.collectIsPressedAsState(mutableInteractionSource, composer, 0);
        composer.startReplaceableGroup(-1139408057);
        boolean zChanged = composer.changed(stateCollectIsPressedAsState) | composer.changedInstance(coroutineScope) | composer.changed(mutableInteractionSource) | composer.changedInstance(press);
        Object objRememberedValue2 = composer.rememberedValue();
        if (zChanged || objRememberedValue2 == companion.getEmpty()) {
            objRememberedValue2 = new n3(coroutineScope, stateCollectIsPressedAsState, mutableInteractionSource, press);
            composer.updateRememberedValue(objRememberedValue2);
        }
        composer.endReplaceableGroup();
        Modifier modifierOnFocusChanged = FocusChangedModifierKt.onFocusChanged(modifier, (r7.l) objRememberedValue2);
        composer.startReplaceableGroup(-1139398023);
        boolean zChangedInstance = composer.changedInstance(coroutineScope) | composer.changed(mutableInteractionSource) | composer.changedInstance(press) | composer.changed(this.f5739m) | composer.changed(this.f5740n);
        Object objRememberedValue3 = composer.rememberedValue();
        if (zChangedInstance || objRememberedValue3 == companion.getEmpty()) {
            objRememberedValue3 = new r3(coroutineScope, this.f5739m, this.f5740n, this.f5738l, press, mutableState);
            composer.updateRememberedValue(objRememberedValue3);
        }
        composer.endReplaceableGroup();
        Modifier modifierOnKeyEvent = KeyInputModifierKt.onKeyEvent(modifierOnFocusChanged, (r7.l) objRememberedValue3);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        composer.endReplaceableGroup();
        return modifierOnKeyEvent;
    }
}
