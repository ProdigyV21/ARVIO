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
import androidx.compose.ui.focus.FocusState;
import androidx.compose.ui.graphics.GraphicsLayerScope;
import androidx.compose.ui.layout.LayoutKt;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.text.TextStyle;
import androidx.compose.ui.unit.Dp;
import androidx.media3.extractor.WavUtil;
import androidx.tv.material3.TextKt;
import com.arflix.tv.data.model.IptvNowNext;
import java.util.Locale;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u0007\n\u0002\b\u0002\u001aç\u0001\u0010\u001d\u001a\u00020\u000b2\u0006\u0010\u0001\u001a\u00020\u00002\u0006\u0010\u0003\u001a\u00020\u00022\b\u0010\u0005\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\u00062\b\b\u0002\u0010\t\u001a\u00020\u00062\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000b0\n2\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000b0\n2\u000e\b\u0002\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u000b0\n2\u000e\b\u0002\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00060\n2\u000e\b\u0002\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00060\n2\u000e\b\u0002\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00060\n2\u000e\b\u0002\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u000b0\n2\b\b\u0002\u0010\u0014\u001a\u00020\u00132\u000e\b\u0002\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u000b0\n2\b\b\u0002\u0010\u0017\u001a\u00020\u00162\b\b\u0002\u0010\u0018\u001a\u00020\u00062\b\b\u0002\u0010\u001a\u001a\u00020\u0019H\u0007¢\u0006\u0004\b\u001b\u0010\u001c\u001a\u0017\u0010 \u001a\u00020\u000b2\u0006\u0010\u001f\u001a\u00020\u001eH\u0003¢\u0006\u0004\b \u0010!¨\u0006&²\u0006\u000e\u0010\"\u001a\u00020\u00068\n@\nX\u008a\u008e\u0002²\u0006\f\u0010#\u001a\u00020\u00168\nX\u008a\u0084\u0002²\u0006\f\u0010%\u001a\u00020$8\nX\u008a\u0084\u0002"}, d2 = {"Lcom/arflix/tv/ui/screens/tv/live/EnrichedChannel;", "channel", "", "clockTickMillis", "Lcom/arflix/tv/data/model/IptvNowNext;", "nowNext", "", "isActive", "isFavorite", "stripe", "Lkotlin/Function0;", "Lx6/t0;", "onClick", "onFavoriteToggle", "onMoveLeft", "onMoveRight", "onMoveUp", "onMoveDown", "onFocused", "", "variantCount", "onOpenVariants", "Landroidx/compose/ui/unit/Dp;", "rowHeight", "forceFocused", "Landroidx/compose/ui/Modifier;", "modifier", "ChannelRow-ZSrRDQQ", "(Lcom/arflix/tv/ui/screens/tv/live/EnrichedChannel;JLcom/arflix/tv/data/model/IptvNowNext;ZZZLr7/a;Lr7/a;Lr7/a;Lr7/a;Lr7/a;Lr7/a;Lr7/a;ILr7/a;FZLandroidx/compose/ui/Modifier;Landroidx/compose/runtime/Composer;III)V", "ChannelRow", "", "text", "SmallPillBadge", "(Ljava/lang/String;Landroidx/compose/runtime/Composer;I)V", "focused", "animatedBorderWidth", "", "animatedScale", "app_playRelease"}, k = 2, mv = {2, 3, 0}, xi = 48)
public final class ChannelRowKt {
    /* JADX WARN: Removed duplicated region for block: B:140:0x01ca  */
    /* JADX WARN: Removed duplicated region for block: B:147:0x01de  */
    /* JADX WARN: Removed duplicated region for block: B:150:0x01e4  */
    /* JADX WARN: Removed duplicated region for block: B:151:0x01e9  */
    /* JADX WARN: Removed duplicated region for block: B:159:0x01fd  */
    /* JADX WARN: Removed duplicated region for block: B:160:0x0202  */
    /* JADX WARN: Removed duplicated region for block: B:174:0x0248  */
    /* JADX WARN: Removed duplicated region for block: B:183:0x027a  */
    /* JADX WARN: Removed duplicated region for block: B:184:0x027c  */
    /* JADX WARN: Removed duplicated region for block: B:185:0x027e  */
    /* JADX WARN: Removed duplicated region for block: B:187:0x0282  */
    /* JADX WARN: Removed duplicated region for block: B:192:0x029b  */
    /* JADX WARN: Removed duplicated region for block: B:196:0x02b5  */
    /* JADX WARN: Removed duplicated region for block: B:198:0x02b9  */
    /* JADX WARN: Removed duplicated region for block: B:202:0x02d3  */
    /* JADX WARN: Removed duplicated region for block: B:204:0x02d7  */
    /* JADX WARN: Removed duplicated region for block: B:208:0x02f3  */
    /* JADX WARN: Removed duplicated region for block: B:210:0x02f9  */
    /* JADX WARN: Removed duplicated region for block: B:215:0x0317  */
    /* JADX WARN: Removed duplicated region for block: B:217:0x031f  */
    /* JADX WARN: Removed duplicated region for block: B:218:0x0322  */
    /* JADX WARN: Removed duplicated region for block: B:220:0x0326  */
    /* JADX WARN: Removed duplicated region for block: B:224:0x0340  */
    /* JADX WARN: Removed duplicated region for block: B:227:0x0346  */
    /* JADX WARN: Removed duplicated region for block: B:228:0x034f  */
    /* JADX WARN: Removed duplicated region for block: B:230:0x0353  */
    /* JADX WARN: Removed duplicated region for block: B:231:0x0356  */
    /* JADX WARN: Removed duplicated region for block: B:233:0x035a  */
    /* JADX WARN: Removed duplicated region for block: B:235:0x036b  */
    /* JADX WARN: Removed duplicated region for block: B:238:0x037d  */
    /* JADX WARN: Removed duplicated region for block: B:241:0x0396  */
    /* JADX WARN: Removed duplicated region for block: B:242:0x03a1  */
    /* JADX WARN: Removed duplicated region for block: B:248:0x03b0  */
    /* JADX WARN: Removed duplicated region for block: B:250:0x03b4  */
    /* JADX WARN: Removed duplicated region for block: B:251:0x03bb  */
    /* JADX WARN: Removed duplicated region for block: B:257:0x03d5  */
    /* JADX WARN: Removed duplicated region for block: B:258:0x03dc  */
    /* JADX WARN: Removed duplicated region for block: B:261:0x03e0  */
    /* JADX WARN: Removed duplicated region for block: B:262:0x03eb  */
    /* JADX WARN: Removed duplicated region for block: B:265:0x041b  */
    /* JADX WARN: Removed duplicated region for block: B:266:0x0421  */
    /* JADX WARN: Removed duplicated region for block: B:271:0x0467  */
    /* JADX WARN: Removed duplicated region for block: B:274:0x047c  */
    /* JADX WARN: Removed duplicated region for block: B:275:0x047e  */
    /* JADX WARN: Removed duplicated region for block: B:280:0x048b  */
    /* JADX WARN: Removed duplicated region for block: B:283:0x04a0  */
    /* JADX WARN: Removed duplicated region for block: B:284:0x04a7  */
    /* JADX WARN: Removed duplicated region for block: B:287:0x04c4  */
    /* JADX WARN: Removed duplicated region for block: B:290:0x04ea  */
    /* JADX WARN: Removed duplicated region for block: B:291:0x04ec  */
    /* JADX WARN: Removed duplicated region for block: B:294:0x04f5  */
    /* JADX WARN: Removed duplicated region for block: B:295:0x04f7  */
    /* JADX WARN: Removed duplicated region for block: B:298:0x04fe  */
    /* JADX WARN: Removed duplicated region for block: B:299:0x0500  */
    /* JADX WARN: Removed duplicated region for block: B:302:0x0508  */
    /* JADX WARN: Removed duplicated region for block: B:303:0x050a  */
    /* JADX WARN: Removed duplicated region for block: B:308:0x0518  */
    /* JADX WARN: Removed duplicated region for block: B:311:0x052c  */
    /* JADX WARN: Removed duplicated region for block: B:312:0x052e  */
    /* JADX WARN: Removed duplicated region for block: B:315:0x0538  */
    /* JADX WARN: Removed duplicated region for block: B:316:0x053a  */
    /* JADX WARN: Removed duplicated region for block: B:319:0x0544  */
    /* JADX WARN: Removed duplicated region for block: B:320:0x0546  */
    /* JADX WARN: Removed duplicated region for block: B:327:0x055c  */
    /* JADX WARN: Removed duplicated region for block: B:330:0x0597  */
    /* JADX WARN: Removed duplicated region for block: B:332:0x059b  */
    /* JADX WARN: Removed duplicated region for block: B:334:0x059f  */
    /* JADX WARN: Removed duplicated region for block: B:335:0x05a1  */
    /* JADX WARN: Removed duplicated region for block: B:338:0x05a7  */
    /* JADX WARN: Removed duplicated region for block: B:339:0x05a9  */
    /* JADX WARN: Removed duplicated region for block: B:344:0x05b7  */
    /* JADX WARN: Removed duplicated region for block: B:348:0x05fc  */
    /* JADX WARN: Removed duplicated region for block: B:351:0x0608  */
    /* JADX WARN: Removed duplicated region for block: B:352:0x060c  */
    /* JADX WARN: Removed duplicated region for block: B:355:0x061d  */
    /* JADX WARN: Removed duplicated region for block: B:358:0x062e  */
    /* JADX WARN: Removed duplicated region for block: B:362:0x0652  */
    /* JADX WARN: Removed duplicated region for block: B:364:0x065b  */
    /* JADX WARN: Removed duplicated region for block: B:368:0x06c4  */
    /* JADX WARN: Removed duplicated region for block: B:371:0x06d0  */
    /* JADX WARN: Removed duplicated region for block: B:372:0x06d4  */
    /* JADX WARN: Removed duplicated region for block: B:375:0x06e5  */
    /* JADX WARN: Removed duplicated region for block: B:379:0x06fa  */
    /* JADX WARN: Removed duplicated region for block: B:383:0x0717  */
    /* JADX WARN: Removed duplicated region for block: B:385:0x071e  */
    /* JADX WARN: Removed duplicated region for block: B:389:0x0813  */
    /* JADX WARN: Removed duplicated region for block: B:392:0x081f  */
    /* JADX WARN: Removed duplicated region for block: B:393:0x0823  */
    /* JADX WARN: Removed duplicated region for block: B:396:0x0836  */
    /* JADX WARN: Removed duplicated region for block: B:400:0x084b  */
    /* JADX WARN: Removed duplicated region for block: B:405:0x0881  */
    /* JADX WARN: Removed duplicated region for block: B:408:0x088d  */
    /* JADX WARN: Removed duplicated region for block: B:409:0x0891  */
    /* JADX WARN: Removed duplicated region for block: B:412:0x08a2  */
    /* JADX WARN: Removed duplicated region for block: B:416:0x08b5  */
    /* JADX WARN: Removed duplicated region for block: B:419:0x08c8  */
    /* JADX WARN: Removed duplicated region for block: B:421:0x08cf  */
    /* JADX WARN: Removed duplicated region for block: B:424:0x094a  */
    /* JADX WARN: Removed duplicated region for block: B:425:0x098b  */
    /* JADX WARN: Removed duplicated region for block: B:428:0x099b  */
    /* JADX WARN: Removed duplicated region for block: B:429:0x09fc  */
    /* JADX WARN: Removed duplicated region for block: B:432:0x0a1d  */
    /* JADX WARN: Removed duplicated region for block: B:433:0x0a1f  */
    /* JADX WARN: Removed duplicated region for block: B:438:0x0a2d  */
    /* JADX WARN: Removed duplicated region for block: B:441:0x0a38  */
    /* JADX WARN: Removed duplicated region for block: B:447:0x0a95  */
    /* JADX WARN: Removed duplicated region for block: B:451:0x0aed  */
    /* JADX WARN: Removed duplicated region for block: B:454:0x0af9  */
    /* JADX WARN: Removed duplicated region for block: B:455:0x0afd  */
    /* JADX WARN: Removed duplicated region for block: B:462:0x0b21  */
    /* JADX WARN: Removed duplicated region for block: B:465:0x0b2b  */
    /* JADX WARN: Removed duplicated region for block: B:466:0x0b4e  */
    /* JADX WARN: Removed duplicated region for block: B:469:0x0b70  */
    /* JADX WARN: Removed duplicated region for block: B:473:0x0b8f  */
    /* JADX WARN: Removed duplicated region for block: B:475:? A[RETURN, SYNTHETIC] */
    /* JADX INFO: renamed from: ChannelRow-ZSrRDQQ, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void m6368ChannelRowZSrRDQQ(final com.arflix.tv.ui.screens.tv.live.EnrichedChannel r120, final long r121, final com.arflix.tv.data.model.IptvNowNext r123, final boolean r124, final boolean r125, boolean r126, final r7.a<x6.t0> r127, r7.a<x6.t0> r128, r7.a<x6.t0> r129, r7.a<java.lang.Boolean> r130, r7.a<java.lang.Boolean> r131, r7.a<java.lang.Boolean> r132, r7.a<x6.t0> r133, int r134, r7.a<x6.t0> r135, float r136, boolean r137, androidx.compose.ui.Modifier r138, androidx.compose.runtime.Composer r139, final int r140, final int r141, final int r142) {
        /*
            Method dump skipped, instruction units count: 2991
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.screens.tv.live.ChannelRowKt.m6368ChannelRowZSrRDQQ(com.arflix.tv.ui.screens.tv.live.EnrichedChannel, long, com.arflix.tv.data.model.IptvNowNext, boolean, boolean, boolean, r7.a, r7.a, r7.a, r7.a, r7.a, r7.a, r7.a, int, r7.a, float, boolean, androidx.compose.ui.Modifier, androidx.compose.runtime.Composer, int, int, int):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean ChannelRow_ZSrRDQQ$lambda$1$0() {
        return false;
    }

    private static final float ChannelRow_ZSrRDQQ$lambda$10(State<Float> state) {
        return state.getValue().floatValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 ChannelRow_ZSrRDQQ$lambda$11$0(State state, GraphicsLayerScope graphicsLayerScope) {
        graphicsLayerScope.setScaleX(ChannelRow_ZSrRDQQ$lambda$10(state));
        graphicsLayerScope.setScaleY(ChannelRow_ZSrRDQQ$lambda$10(state));
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 ChannelRow_ZSrRDQQ$lambda$12$0(r7.a aVar, MutableState mutableState, FocusState focusState) {
        ChannelRow_ZSrRDQQ$lambda$8(mutableState, focusState.getHasFocus());
        if (focusState.getHasFocus()) {
            aVar.invoke();
        }
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 ChannelRow_ZSrRDQQ$lambda$14$0(int i10, r7.a aVar, r7.a aVar2) {
        if (i10 > 1) {
            aVar.invoke();
        } else {
            aVar2.invoke();
        }
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 ChannelRow_ZSrRDQQ$lambda$17(EnrichedChannel enrichedChannel, long j10, IptvNowNext iptvNowNext, boolean z, boolean z5, boolean z10, r7.a aVar, r7.a aVar2, r7.a aVar3, r7.a aVar4, r7.a aVar5, r7.a aVar6, r7.a aVar7, int i10, r7.a aVar8, float f10, boolean z11, Modifier modifier, int i11, int i12, int i13, Composer composer, int i14) {
        m6368ChannelRowZSrRDQQ(enrichedChannel, j10, iptvNowNext, z, z5, z10, aVar, aVar2, aVar3, aVar4, aVar5, aVar6, aVar7, i10, aVar8, f10, z11, modifier, composer, RecomposeScopeImplKt.updateChangedFlags(i11 | 1), RecomposeScopeImplKt.updateChangedFlags(i12), i13);
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean ChannelRow_ZSrRDQQ$lambda$2$0() {
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean ChannelRow_ZSrRDQQ$lambda$3$0() {
        return false;
    }

    private static final boolean ChannelRow_ZSrRDQQ$lambda$7(MutableState<Boolean> mutableState) {
        return mutableState.getValue().booleanValue();
    }

    private static final void ChannelRow_ZSrRDQQ$lambda$8(MutableState<Boolean> mutableState, boolean z) {
        mutableState.setValue(Boolean.valueOf(z));
    }

    private static final float ChannelRow_ZSrRDQQ$lambda$9(State<Dp> state) {
        return state.getValue().m5692unboximpl();
    }

    private static final void SmallPillBadge(String str, Composer composer, int i10) {
        int i11;
        Composer composer2;
        Composer composerStartRestartGroup = composer.startRestartGroup(-1229838997);
        if ((i10 & 6) == 0) {
            i11 = (composerStartRestartGroup.changed(str) ? 4 : 2) | i10;
        } else {
            i11 = i10;
        }
        if ((i11 & 3) == 2 && composerStartRestartGroup.getSkipping()) {
            composerStartRestartGroup.skipToGroupEnd();
            composer2 = composerStartRestartGroup;
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1229838997, i11, -1, "com.arflix.tv.ui.screens.tv.live.SmallPillBadge (ChannelRow.kt:245)");
            }
            Modifier modifierI = com.arflix.tv.data.repository.g.i(3, Modifier.INSTANCE);
            LiveColors liveColors = LiveColors.INSTANCE;
            Modifier modifierH = androidx.fragment.app.a2.h(2, BackgroundKt.m179backgroundbw27NRU$default(modifierI, liveColors.m6409getPanel0d7_KjU(), null, 2, null), Dp.m5678constructorimpl(6), composerStartRestartGroup, 733328855);
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
            TextKt.m6020Text4IGK_g(str.toUpperCase(Locale.ROOT), null, 0L, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, TextStyle.m5205copyp1EtxEg$default(LiveType.INSTANCE.getBadge(), liveColors.m6403getFgDim0d7_KjU(), 0L, null, null, null, null, null, 0L, null, null, null, 0L, null, null, null, 0, 0, 0L, null, null, null, 0, 0, null, 16777214, null), composer2, 0, 0, WavUtil.TYPE_WAVE_FORMAT_EXTENSIBLE);
            if (androidx.compose.foundation.c.D(composer2)) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new com.arflix.tv.ui.screens.collections.b(str, i10, 7));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 SmallPillBadge$lambda$1(String str, int i10, Composer composer, int i11) {
        SmallPillBadge(str, composer, RecomposeScopeImplKt.updateChangedFlags(i10 | 1));
        return x6.t0.f22605a;
    }
}
