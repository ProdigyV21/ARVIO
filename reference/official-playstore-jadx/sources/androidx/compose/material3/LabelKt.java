package androidx.compose.material3;

import androidx.compose.foundation.interaction.MutableInteractionSource;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.EffectsKt;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.layout.LayoutCoordinates;
import androidx.media3.exoplayer.RendererCapabilities;
import ka.k0;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.p;
import r7.q;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a^\u0010\u000e\u001a\u00020\u00022\u001c\u0010\u0005\u001a\u0018\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00020\u0000¢\u0006\u0002\b\u0003¢\u0006\u0002\b\u00042\b\b\u0002\u0010\u0007\u001a\u00020\u00062\b\b\u0002\u0010\t\u001a\u00020\b2\b\b\u0002\u0010\u000b\u001a\u00020\n2\u0011\u0010\r\u001a\r\u0012\u0004\u0012\u00020\u00020\f¢\u0006\u0002\b\u0003H\u0007¢\u0006\u0004\b\u000e\u0010\u000f\u001a'\u0010\u0013\u001a\u00020\u00022\u0006\u0010\u0010\u001a\u00020\n2\u0006\u0010\u0012\u001a\u00020\u00112\u0006\u0010\t\u001a\u00020\bH\u0003¢\u0006\u0004\b\u0013\u0010\u0014¨\u0006\u0017²\u0006\u0010\u0010\u0016\u001a\u0004\u0018\u00010\u00158\n@\nX\u008a\u008e\u0002"}, d2 = {"Lkotlin/Function1;", "Landroidx/compose/material3/CaretScope;", "Lx6/t0;", "Landroidx/compose/runtime/Composable;", "Lx6/n;", "label", "Landroidx/compose/ui/Modifier;", "modifier", "Landroidx/compose/foundation/interaction/MutableInteractionSource;", "interactionSource", "", "isPersistent", "Lkotlin/Function0;", "content", TextFieldImplKt.LabelId, "(Lr7/q;Landroidx/compose/ui/Modifier;Landroidx/compose/foundation/interaction/MutableInteractionSource;ZLr7/p;Landroidx/compose/runtime/Composer;II)V", "enabled", "Landroidx/compose/material3/BasicTooltipState;", "state", "HandleInteractions", "(ZLandroidx/compose/material3/BasicTooltipState;Landroidx/compose/foundation/interaction/MutableInteractionSource;Landroidx/compose/runtime/Composer;I)V", "Landroidx/compose/ui/layout/LayoutCoordinates;", "anchorBounds", "material3_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class LabelKt {

    /* JADX INFO: renamed from: androidx.compose.material3.LabelKt$HandleInteractions$2, reason: invalid class name */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class AnonymousClass2 extends r implements p<Composer, Integer, t0> {
        final /* synthetic */ int $$changed;
        final /* synthetic */ boolean $enabled;
        final /* synthetic */ MutableInteractionSource $interactionSource;
        final /* synthetic */ BasicTooltipState $state;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass2(boolean z, BasicTooltipState basicTooltipState, MutableInteractionSource mutableInteractionSource, int i10) {
            super(2);
            this.$enabled = z;
            this.$state = basicTooltipState;
            this.$interactionSource = mutableInteractionSource;
            this.$$changed = i10;
        }

        @Override // r7.p
        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
            invoke((Composer) obj, ((Number) obj2).intValue());
            return t0.f22605a;
        }

        public final void invoke(Composer composer, int i10) {
            LabelKt.HandleInteractions(this.$enabled, this.$state, this.$interactionSource, composer, RecomposeScopeImplKt.updateChangedFlags(this.$$changed | 1));
        }
    }

    /* JADX INFO: renamed from: androidx.compose.material3.LabelKt$Label$2, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\b\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0003\u001a\u00020\u0000H\u000b¢\u0006\u0004\b\u0001\u0010\u0002"}, d2 = {"Lx6/t0;", "invoke", "(Landroidx/compose/runtime/Composer;I)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class C05252 extends r implements p<Composer, Integer, t0> {
        final /* synthetic */ q<CaretScope, Composer, Integer, t0> $label;
        final /* synthetic */ LabelKt$Label$scope$1$1 $scope;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public C05252(q<? super CaretScope, ? super Composer, ? super Integer, t0> qVar, LabelKt$Label$scope$1$1 labelKt$Label$scope$1$1) {
            super(2);
            this.$label = qVar;
            this.$scope = labelKt$Label$scope$1$1;
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
                ComposerKt.traceEventStart(784196780, i10, -1, "androidx.compose.material3.Label.<anonymous> (Label.kt:97)");
            }
            this.$label.invoke(this.$scope, composer, 6);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
    }

    /* JADX INFO: renamed from: androidx.compose.material3.LabelKt$Label$3, reason: invalid class name */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class AnonymousClass3 extends r implements p<Composer, Integer, t0> {
        final /* synthetic */ int $$changed;
        final /* synthetic */ int $$default;
        final /* synthetic */ p<Composer, Integer, t0> $content;
        final /* synthetic */ MutableInteractionSource $interactionSource;
        final /* synthetic */ boolean $isPersistent;
        final /* synthetic */ q<CaretScope, Composer, Integer, t0> $label;
        final /* synthetic */ Modifier $modifier;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public AnonymousClass3(q<? super CaretScope, ? super Composer, ? super Integer, t0> qVar, Modifier modifier, MutableInteractionSource mutableInteractionSource, boolean z, p<? super Composer, ? super Integer, t0> pVar, int i10, int i11) {
            super(2);
            this.$label = qVar;
            this.$modifier = modifier;
            this.$interactionSource = mutableInteractionSource;
            this.$isPersistent = z;
            this.$content = pVar;
            this.$$changed = i10;
            this.$$default = i11;
        }

        @Override // r7.p
        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
            invoke((Composer) obj, ((Number) obj2).intValue());
            return t0.f22605a;
        }

        public final void invoke(Composer composer, int i10) {
            LabelKt.Label(this.$label, this.$modifier, this.$interactionSource, this.$isPersistent, this.$content, composer, RecomposeScopeImplKt.updateChangedFlags(this.$$changed | 1), this.$$default);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void HandleInteractions(boolean z, BasicTooltipState basicTooltipState, MutableInteractionSource mutableInteractionSource, Composer composer, int i10) {
        int i11;
        Composer composerStartRestartGroup = composer.startRestartGroup(-1479255681);
        if ((i10 & 6) == 0) {
            i11 = (composerStartRestartGroup.changed(z) ? 4 : 2) | i10;
        } else {
            i11 = i10;
        }
        if ((i10 & 48) == 0) {
            i11 |= composerStartRestartGroup.changed(basicTooltipState) ? 32 : 16;
        }
        if ((i10 & RendererCapabilities.DECODER_SUPPORT_MASK) == 0) {
            i11 |= composerStartRestartGroup.changed(mutableInteractionSource) ? 256 : 128;
        }
        if ((i11 & 147) == 146 && composerStartRestartGroup.getSkipping()) {
            composerStartRestartGroup.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1479255681, i11, -1, "androidx.compose.material3.HandleInteractions (Label.kt:117)");
            }
            if (z) {
                composerStartRestartGroup.startReplaceableGroup(-404204353);
                boolean z5 = ((i11 & 896) == 256) | ((i11 & 112) == 32);
                Object objRememberedValue = composerStartRestartGroup.rememberedValue();
                if (z5 || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                    objRememberedValue = new LabelKt$HandleInteractions$1$1(mutableInteractionSource, basicTooltipState, null);
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                }
                composerStartRestartGroup.endReplaceableGroup();
                EffectsKt.LaunchedEffect(mutableInteractionSource, (p<? super k0, ? super d7.d<? super t0>, ? extends Object>) objRememberedValue, composerStartRestartGroup, (i11 >> 6) & 14);
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new AnonymousClass2(z, basicTooltipState, mutableInteractionSource, i10));
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:100:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:26:0x0046  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x004b  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x0061  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x0066  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x007c  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x007f  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x0095  */
    /* JADX WARN: Removed duplicated region for block: B:62:0x00a4  */
    /* JADX WARN: Removed duplicated region for block: B:63:0x00a6  */
    /* JADX WARN: Removed duplicated region for block: B:64:0x00a9  */
    /* JADX WARN: Removed duplicated region for block: B:66:0x00ac  */
    /* JADX WARN: Removed duplicated region for block: B:70:0x00cc  */
    /* JADX WARN: Removed duplicated region for block: B:73:0x00d0  */
    /* JADX WARN: Removed duplicated region for block: B:74:0x00d3  */
    /* JADX WARN: Removed duplicated region for block: B:77:0x00db  */
    /* JADX WARN: Removed duplicated region for block: B:80:0x00ee  */
    /* JADX WARN: Removed duplicated region for block: B:85:0x0110  */
    /* JADX WARN: Removed duplicated region for block: B:88:0x0139  */
    /* JADX WARN: Removed duplicated region for block: B:91:0x014f  */
    /* JADX WARN: Removed duplicated region for block: B:94:0x0198  */
    /* JADX WARN: Removed duplicated region for block: B:98:0x01a5  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void Label(r7.q<? super androidx.compose.material3.CaretScope, ? super androidx.compose.runtime.Composer, ? super java.lang.Integer, x6.t0> r18, androidx.compose.ui.Modifier r19, androidx.compose.foundation.interaction.MutableInteractionSource r20, boolean r21, r7.p<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, x6.t0> r22, androidx.compose.runtime.Composer r23, int r24, int r25) {
        /*
            Method dump skipped, instruction units count: 432
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material3.LabelKt.Label(r7.q, androidx.compose.ui.Modifier, androidx.compose.foundation.interaction.MutableInteractionSource, boolean, r7.p, androidx.compose.runtime.Composer, int, int):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final LayoutCoordinates Label$lambda$3(MutableState<LayoutCoordinates> mutableState) {
        return mutableState.getValue();
    }
}
