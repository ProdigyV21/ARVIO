package com.arflix.tv.ui.screens.tv.live;

import androidx.compose.foundation.BackgroundKt;
import androidx.compose.foundation.layout.BoxScopeInstance;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalMap;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.SkippableUpdater;
import androidx.compose.runtime.State;
import androidx.compose.runtime.Updater;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.focus.FocusRequester;
import androidx.compose.ui.focus.FocusState;
import androidx.compose.ui.graphics.GraphicsLayerScope;
import androidx.compose.ui.layout.LayoutKt;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.text.TextStyle;
import androidx.compose.ui.unit.Dp;
import androidx.compose.ui.unit.TextUnitKt;
import androidx.media3.exoplayer.RendererCapabilities;
import androidx.media3.extractor.WavUtil;
import androidx.tv.material3.TextKt;
import com.arflix.tv.data.model.IptvProgram;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0007\n\u0000\u001aÑ\u0001\u0010\u001b\u001a\u00020\r2\u0006\u0010\u0001\u001a\u00020\u00002\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\u00062\b\b\u0002\u0010\n\u001a\u00020\u00062\b\b\u0002\u0010\u000b\u001a\u00020\u00062\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\r0\f2\u000e\b\u0002\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\r0\f2\u000e\b\u0002\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00060\f2\u000e\b\u0002\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00060\f2\u000e\b\u0002\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00060\f2\u000e\b\u0002\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00060\f2\b\b\u0002\u0010\u0014\u001a\u00020\u00042\n\b\u0002\u0010\u0016\u001a\u0004\u0018\u00010\u00152\b\b\u0002\u0010\u0018\u001a\u00020\u0017H\u0007¢\u0006\u0004\b\u0019\u0010\u001a\u001a'\u0010#\u001a\u00020\r2\u0006\u0010\u001d\u001a\u00020\u001c2\u0006\u0010\u001f\u001a\u00020\u001e2\u0006\u0010 \u001a\u00020\u001eH\u0007¢\u0006\u0004\b!\u0010\"¨\u0006'²\u0006\u000e\u0010$\u001a\u00020\u00068\n@\nX\u008a\u008e\u0002²\u0006\f\u0010%\u001a\u00020\u00048\nX\u008a\u0084\u0002²\u0006\f\u0010%\u001a\u00020&8\nX\u008a\u0084\u0002²\u0006\f\u0010%\u001a\u00020&8\nX\u008a\u0084\u0002"}, d2 = {"Lcom/arflix/tv/data/model/IptvProgram;", "program", "", "clockTickMillis", "Landroidx/compose/ui/unit/Dp;", "width", "", "isNow", "isPast", "isFocusTarget", "focusable", "isCatchupSupported", "Lkotlin/Function0;", "Lx6/t0;", "onClick", "onFocused", "onMoveLeft", "onMoveRight", "onMoveUp", "onMoveDown", "rowHeight", "Landroidx/compose/ui/focus/FocusRequester;", "focusRequester", "Landroidx/compose/ui/Modifier;", "modifier", "ProgramCell-dOl9XNk", "(Lcom/arflix/tv/data/model/IptvProgram;JFZZZZZLr7/a;Lr7/a;Lr7/a;Lr7/a;Lr7/a;Lr7/a;FLandroidx/compose/ui/focus/FocusRequester;Landroidx/compose/ui/Modifier;Landroidx/compose/runtime/Composer;III)V", "ProgramCell", "", "label", "Landroidx/compose/ui/graphics/Color;", "fg", "bg", "Badge-1wkBAMs", "(Ljava/lang/String;JJLandroidx/compose/runtime/Composer;I)V", "Badge", "focused", "animated", "", "app_playRelease"}, k = 2, mv = {2, 3, 0}, xi = 48)
public final class ProgramCellKt {
    /* JADX INFO: renamed from: Badge-1wkBAMs, reason: not valid java name */
    public static final void m6446Badge1wkBAMs(String str, long j10, long j11, Composer composer, int i10) {
        int i11;
        Composer composer2;
        Composer composerStartRestartGroup = composer.startRestartGroup(-571149264);
        if ((i10 & 6) == 0) {
            i11 = (composerStartRestartGroup.changed(str) ? 4 : 2) | i10;
        } else {
            i11 = i10;
        }
        if ((i10 & 48) == 0) {
            i11 |= composerStartRestartGroup.changed(j10) ? 32 : 16;
        }
        if ((i10 & RendererCapabilities.DECODER_SUPPORT_MASK) == 0) {
            i11 |= composerStartRestartGroup.changed(j11) ? 256 : 128;
        }
        if ((i11 & 147) == 146 && composerStartRestartGroup.getSkipping()) {
            composerStartRestartGroup.skipToGroupEnd();
            composer2 = composerStartRestartGroup;
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-571149264, i11, -1, "com.arflix.tv.ui.screens.tv.live.Badge (ProgramCell.kt:261)");
            }
            Modifier modifierH = androidx.fragment.app.a2.h(1, BackgroundKt.m179backgroundbw27NRU$default(com.arflix.tv.data.repository.g.i(3, Modifier.INSTANCE), j11, null, 2, null), Dp.m5678constructorimpl(5), composerStartRestartGroup, 733328855);
            MeasurePolicy measurePolicyD = a0.c.d(Alignment.INSTANCE, false, composerStartRestartGroup, 0, -1323940314);
            int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
            CompositionLocalMap currentCompositionLocalMap = composerStartRestartGroup.getCurrentCompositionLocalMap();
            ComposeUiNode.Companion companion = ComposeUiNode.INSTANCE;
            r7.a<ComposeUiNode> constructor = companion.getConstructor();
            r7.q<SkippableUpdater<ComposeUiNode>, Composer, Integer, x6.t0> qVarModifierMaterializerOf = LayoutKt.modifierMaterializerOf(modifierH);
            if (composerStartRestartGroup.getApplier() == null) {
                ComposablesKt.invalidApplier();
            }
            composerStartRestartGroup.startReusableNode();
            if (composerStartRestartGroup.getInserting()) {
                composerStartRestartGroup.createNode(constructor);
            } else {
                composerStartRestartGroup.useNode();
            }
            Composer composerM2991constructorimpl = Updater.m2991constructorimpl(composerStartRestartGroup);
            r7.p pVarU = a0.c.u(companion, composerM2991constructorimpl, measurePolicyD, composerM2991constructorimpl, currentCompositionLocalMap);
            if (composerM2991constructorimpl.getInserting() || !kotlin.jvm.internal.p.a(composerM2991constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                a0.c.w(currentCompositeKeyHash, composerM2991constructorimpl, currentCompositeKeyHash, pVarU);
            }
            a0.c.x(0, qVarModifierMaterializerOf, SkippableUpdater.m2980boximpl(SkippableUpdater.m2981constructorimpl(composerStartRestartGroup)), composerStartRestartGroup, 2058660585);
            BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
            composer2 = composerStartRestartGroup;
            TextKt.m6020Text4IGK_g(str, null, 0L, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, TextStyle.m5205copyp1EtxEg$default(LiveType.INSTANCE.getBadge(), j10, TextUnitKt.getSp(9), null, null, null, null, null, 0L, null, null, null, 0L, null, null, null, 0, 0, 0L, null, null, null, 0, 0, null, 16777212, null), composer2, i11 & 14, 0, WavUtil.TYPE_WAVE_FORMAT_EXTENSIBLE);
            if (androidx.compose.foundation.c.D(composer2)) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new com.arflix.tv.ui.screens.settings.z1(str, j10, j11, i10, 2));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 Badge_1wkBAMs$lambda$1(String str, long j10, long j11, int i10, Composer composer, int i11) {
        m6446Badge1wkBAMs(str, j10, j11, composer, RecomposeScopeImplKt.updateChangedFlags(i10 | 1));
        return x6.t0.f22605a;
    }

    /* JADX WARN: Removed duplicated region for block: B:121:0x0182  */
    /* JADX WARN: Removed duplicated region for block: B:128:0x0196  */
    /* JADX WARN: Removed duplicated region for block: B:131:0x01a3  */
    /* JADX WARN: Removed duplicated region for block: B:132:0x01a8  */
    /* JADX WARN: Removed duplicated region for block: B:141:0x01bf  */
    /* JADX WARN: Removed duplicated region for block: B:142:0x01c4  */
    /* JADX WARN: Removed duplicated region for block: B:156:0x0207  */
    /* JADX WARN: Removed duplicated region for block: B:165:0x0239  */
    /* JADX WARN: Removed duplicated region for block: B:166:0x023b  */
    /* JADX WARN: Removed duplicated region for block: B:168:0x023e  */
    /* JADX WARN: Removed duplicated region for block: B:170:0x0241  */
    /* JADX WARN: Removed duplicated region for block: B:174:0x0259  */
    /* JADX WARN: Removed duplicated region for block: B:176:0x025d  */
    /* JADX WARN: Removed duplicated region for block: B:180:0x0277  */
    /* JADX WARN: Removed duplicated region for block: B:182:0x027b  */
    /* JADX WARN: Removed duplicated region for block: B:187:0x0297  */
    /* JADX WARN: Removed duplicated region for block: B:189:0x029d  */
    /* JADX WARN: Removed duplicated region for block: B:193:0x02b9  */
    /* JADX WARN: Removed duplicated region for block: B:195:0x02bf  */
    /* JADX WARN: Removed duplicated region for block: B:199:0x02d8  */
    /* JADX WARN: Removed duplicated region for block: B:202:0x02de  */
    /* JADX WARN: Removed duplicated region for block: B:203:0x02e7  */
    /* JADX WARN: Removed duplicated region for block: B:205:0x02eb  */
    /* JADX WARN: Removed duplicated region for block: B:206:0x02ee  */
    /* JADX WARN: Removed duplicated region for block: B:208:0x02f2  */
    /* JADX WARN: Removed duplicated region for block: B:209:0x0307  */
    /* JADX WARN: Removed duplicated region for block: B:212:0x0322  */
    /* JADX WARN: Removed duplicated region for block: B:215:0x0346  */
    /* JADX WARN: Removed duplicated region for block: B:216:0x0355  */
    /* JADX WARN: Removed duplicated region for block: B:219:0x035b  */
    /* JADX WARN: Removed duplicated region for block: B:220:0x0362  */
    /* JADX WARN: Removed duplicated region for block: B:223:0x036e  */
    /* JADX WARN: Removed duplicated region for block: B:226:0x037a  */
    /* JADX WARN: Removed duplicated region for block: B:228:0x0384  */
    /* JADX WARN: Removed duplicated region for block: B:232:0x03b4  */
    /* JADX WARN: Removed duplicated region for block: B:237:0x0400  */
    /* JADX WARN: Removed duplicated region for block: B:244:0x0421  */
    /* JADX WARN: Removed duplicated region for block: B:250:0x0464  */
    /* JADX WARN: Removed duplicated region for block: B:252:0x0472  */
    /* JADX WARN: Removed duplicated region for block: B:261:0x04b9  */
    /* JADX WARN: Removed duplicated region for block: B:270:0x04f6  */
    /* JADX WARN: Removed duplicated region for block: B:275:0x050f  */
    /* JADX WARN: Removed duplicated region for block: B:278:0x0517  */
    /* JADX WARN: Removed duplicated region for block: B:288:0x0549  */
    /* JADX WARN: Removed duplicated region for block: B:291:0x058b  */
    /* JADX WARN: Removed duplicated region for block: B:294:0x059a  */
    /* JADX WARN: Removed duplicated region for block: B:322:0x0614  */
    /* JADX WARN: Removed duplicated region for block: B:328:0x063e  */
    /* JADX WARN: Removed duplicated region for block: B:330:0x064c  */
    /* JADX WARN: Removed duplicated region for block: B:331:0x064e  */
    /* JADX WARN: Removed duplicated region for block: B:336:0x065b  */
    /* JADX WARN: Removed duplicated region for block: B:341:0x06ab  */
    /* JADX WARN: Removed duplicated region for block: B:344:0x06b7  */
    /* JADX WARN: Removed duplicated region for block: B:345:0x06bb  */
    /* JADX WARN: Removed duplicated region for block: B:348:0x06cc  */
    /* JADX WARN: Removed duplicated region for block: B:351:0x06dd  */
    /* JADX WARN: Removed duplicated region for block: B:355:0x06ed  */
    /* JADX WARN: Removed duplicated region for block: B:356:0x0774  */
    /* JADX WARN: Removed duplicated region for block: B:360:0x07b5  */
    /* JADX WARN: Removed duplicated region for block: B:363:0x07c1  */
    /* JADX WARN: Removed duplicated region for block: B:364:0x07c5  */
    /* JADX WARN: Removed duplicated region for block: B:367:0x07d6  */
    /* JADX WARN: Removed duplicated region for block: B:371:0x07eb  */
    /* JADX WARN: Removed duplicated region for block: B:376:0x0821  */
    /* JADX WARN: Removed duplicated region for block: B:379:0x082d  */
    /* JADX WARN: Removed duplicated region for block: B:380:0x0831  */
    /* JADX WARN: Removed duplicated region for block: B:383:0x0842  */
    /* JADX WARN: Removed duplicated region for block: B:387:0x0855  */
    /* JADX WARN: Removed duplicated region for block: B:390:0x0862  */
    /* JADX WARN: Removed duplicated region for block: B:391:0x089b  */
    /* JADX WARN: Removed duplicated region for block: B:411:0x0a8f  */
    /* JADX WARN: Removed duplicated region for block: B:415:0x0ac7  */
    /* JADX WARN: Removed duplicated region for block: B:418:0x0ad3  */
    /* JADX WARN: Removed duplicated region for block: B:419:0x0ad7  */
    /* JADX WARN: Removed duplicated region for block: B:426:0x0afb  */
    /* JADX WARN: Removed duplicated region for block: B:429:0x0b87  */
    /* JADX WARN: Removed duplicated region for block: B:432:0x0b8d  */
    /* JADX WARN: Removed duplicated region for block: B:433:0x0c10  */
    /* JADX WARN: Removed duplicated region for block: B:436:0x0c43  */
    /* JADX WARN: Removed duplicated region for block: B:440:0x0c60  */
    /* JADX WARN: Removed duplicated region for block: B:442:? A[RETURN, SYNTHETIC] */
    /* JADX INFO: renamed from: ProgramCell-dOl9XNk, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void m6447ProgramCelldOl9XNk(final com.arflix.tv.data.model.IptvProgram r109, final long r110, final float r112, final boolean r113, final boolean r114, final boolean r115, boolean r116, boolean r117, final r7.a<x6.t0> r118, r7.a<x6.t0> r119, r7.a<java.lang.Boolean> r120, r7.a<java.lang.Boolean> r121, r7.a<java.lang.Boolean> r122, r7.a<java.lang.Boolean> r123, float r124, androidx.compose.ui.focus.FocusRequester r125, androidx.compose.ui.Modifier r126, androidx.compose.runtime.Composer r127, final int r128, final int r129, final int r130) {
        /*
            Method dump skipped, instruction units count: 3200
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.screens.tv.live.ProgramCellKt.m6447ProgramCelldOl9XNk(com.arflix.tv.data.model.IptvProgram, long, float, boolean, boolean, boolean, boolean, boolean, r7.a, r7.a, r7.a, r7.a, r7.a, r7.a, float, androidx.compose.ui.focus.FocusRequester, androidx.compose.ui.Modifier, androidx.compose.runtime.Composer, int, int, int):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean ProgramCell_dOl9XNk$lambda$1$0() {
        return false;
    }

    private static final float ProgramCell_dOl9XNk$lambda$10(State<Float> state) {
        return state.getValue().floatValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 ProgramCell_dOl9XNk$lambda$11$0(float f10, GraphicsLayerScope graphicsLayerScope) {
        graphicsLayerScope.setScaleX(f10);
        graphicsLayerScope.setScaleY(f10);
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 ProgramCell_dOl9XNk$lambda$12$0(r7.a aVar, MutableState mutableState, FocusState focusState) {
        ProgramCell_dOl9XNk$lambda$7(mutableState, focusState.getHasFocus());
        if (focusState.getHasFocus()) {
            aVar.invoke();
        }
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 ProgramCell_dOl9XNk$lambda$16(IptvProgram iptvProgram, long j10, float f10, boolean z, boolean z5, boolean z10, boolean z11, boolean z12, r7.a aVar, r7.a aVar2, r7.a aVar3, r7.a aVar4, r7.a aVar5, r7.a aVar6, float f11, FocusRequester focusRequester, Modifier modifier, int i10, int i11, int i12, Composer composer, int i13) {
        m6447ProgramCelldOl9XNk(iptvProgram, j10, f10, z, z5, z10, z11, z12, aVar, aVar2, aVar3, aVar4, aVar5, aVar6, f11, focusRequester, modifier, composer, RecomposeScopeImplKt.updateChangedFlags(i10 | 1), RecomposeScopeImplKt.updateChangedFlags(i11), i12);
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean ProgramCell_dOl9XNk$lambda$2$0() {
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean ProgramCell_dOl9XNk$lambda$3$0() {
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean ProgramCell_dOl9XNk$lambda$4$0() {
        return false;
    }

    private static final boolean ProgramCell_dOl9XNk$lambda$6(MutableState<Boolean> mutableState) {
        return mutableState.getValue().booleanValue();
    }

    private static final void ProgramCell_dOl9XNk$lambda$7(MutableState<Boolean> mutableState, boolean z) {
        mutableState.setValue(Boolean.valueOf(z));
    }

    private static final float ProgramCell_dOl9XNk$lambda$8(State<Dp> state) {
        return state.getValue().m5692unboximpl();
    }

    private static final float ProgramCell_dOl9XNk$lambda$9(State<Float> state) {
        return state.getValue().floatValue();
    }
}
