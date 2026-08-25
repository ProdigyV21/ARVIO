package androidx.compose.foundation;

import androidx.compose.foundation.interaction.InteractionSource;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalKt;
import androidx.compose.runtime.ProvidableCompositionLocal;
import androidx.compose.ui.ComposedModifierKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.platform.InspectableValueKt;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.q;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\u001c\u0010\u0005\u001a\u00020\u0006*\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\b\u0010\u0005\u001a\u0004\u0018\u00010\u0002\"\u0017\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001¢\u0006\b\n\u0000\u001a\u0004\b\u0003\u0010\u0004¨\u0006\t"}, d2 = {"LocalIndication", "Landroidx/compose/runtime/ProvidableCompositionLocal;", "Landroidx/compose/foundation/Indication;", "getLocalIndication", "()Landroidx/compose/runtime/ProvidableCompositionLocal;", "indication", "Landroidx/compose/ui/Modifier;", "interactionSource", "Landroidx/compose/foundation/interaction/InteractionSource;", "foundation_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class IndicationKt {
    private static final ProvidableCompositionLocal<Indication> LocalIndication = CompositionLocalKt.staticCompositionLocalOf(IndicationKt$LocalIndication$1.INSTANCE);

    /* JADX INFO: renamed from: androidx.compose.foundation.IndicationKt$indication$2, reason: invalid class name */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0001H\u000b¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"<anonymous>", "Landroidx/compose/ui/Modifier;", "invoke", "(Landroidx/compose/ui/Modifier;Landroidx/compose/runtime/Composer;I)Landroidx/compose/ui/Modifier;"}, k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class AnonymousClass2 extends r implements q<Modifier, Composer, Integer, Modifier> {
        final /* synthetic */ Indication $indication;
        final /* synthetic */ InteractionSource $interactionSource;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass2(Indication indication, InteractionSource interactionSource) {
            super(3);
            this.$indication = indication;
            this.$interactionSource = interactionSource;
        }

        @Override // r7.q
        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2, Object obj3) {
            return invoke((Modifier) obj, (Composer) obj2, ((Number) obj3).intValue());
        }

        public final Modifier invoke(Modifier modifier, Composer composer, int i10) {
            composer.startReplaceableGroup(-353972293);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-353972293, i10, -1, "androidx.compose.foundation.indication.<anonymous> (Indication.kt:108)");
            }
            Indication indication = this.$indication;
            if (indication == null) {
                indication = NoIndication.INSTANCE;
            }
            IndicationInstance indicationInstanceRememberUpdatedInstance = indication.rememberUpdatedInstance(this.$interactionSource, composer, 0);
            composer.startReplaceableGroup(1157296644);
            boolean zChanged = composer.changed(indicationInstanceRememberUpdatedInstance);
            Object objRememberedValue = composer.rememberedValue();
            if (zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                objRememberedValue = new IndicationModifier(indicationInstanceRememberUpdatedInstance);
                composer.updateRememberedValue(objRememberedValue);
            }
            composer.endReplaceableGroup();
            IndicationModifier indicationModifier = (IndicationModifier) objRememberedValue;
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            composer.endReplaceableGroup();
            return indicationModifier;
        }
    }

    public static final ProvidableCompositionLocal<Indication> getLocalIndication() {
        return LocalIndication;
    }

    public static final Modifier indication(Modifier modifier, InteractionSource interactionSource, Indication indication) {
        return ComposedModifierKt.composed(modifier, InspectableValueKt.isDebugInspectorInfoEnabled() ? new IndicationKt$indication$$inlined$debugInspectorInfo$1(indication, interactionSource) : InspectableValueKt.getNoInspectorInfo(), new AnonymousClass2(indication, interactionSource));
    }
}
