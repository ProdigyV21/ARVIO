package com.arflix.tv.ui.screens.details;

import androidx.compose.foundation.BackgroundKt;
import androidx.compose.foundation.layout.Arrangement;
import androidx.compose.foundation.layout.BoxKt;
import androidx.compose.foundation.layout.BoxScope;
import androidx.compose.foundation.layout.BoxScopeInstance;
import androidx.compose.foundation.layout.ColumnKt;
import androidx.compose.foundation.layout.ColumnScopeInstance;
import androidx.compose.foundation.layout.RowKt;
import androidx.compose.foundation.layout.RowScopeInstance;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.foundation.layout.SpacerKt;
import androidx.compose.foundation.shape.RoundedCornerShapeKt;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalMap;
import androidx.compose.runtime.SkippableUpdater;
import androidx.compose.runtime.Updater;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.layout.LayoutKt;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.text.TextStyle;
import androidx.compose.ui.text.font.FontWeight;
import androidx.compose.ui.unit.Dp;
import androidx.compose.ui.unit.TextUnitKt;
import androidx.media3.extractor.ts.TsExtractor;
import androidx.tv.material3.TextKt;
import com.arflix.tv.data.model.Episode;
import com.arflix.tv.ui.skin.ArvioSkin;
import java.util.List;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
public final class ComposableSingletons$DetailsScreenKt {
    public static final ComposableSingletons$DetailsScreenKt INSTANCE = new ComposableSingletons$DetailsScreenKt();

    /* JADX INFO: renamed from: lambda$-101879240, reason: not valid java name */
    private static r7.r<BoxScope, Boolean, Composer, Integer, x6.t0> f61lambda$101879240 = ComposableLambdaKt.composableLambdaInstance(-101879240, false, new b());

    /* JADX INFO: renamed from: lambda$-1308153404, reason: not valid java name */
    private static r7.q<androidx.tv.foundation.lazy.list.c0, Composer, Integer, x6.t0> f62lambda$1308153404;

    /* JADX INFO: renamed from: lambda$-1478673585, reason: not valid java name */
    private static r7.q<androidx.tv.foundation.lazy.list.c0, Composer, Integer, x6.t0> f63lambda$1478673585;

    /* JADX INFO: renamed from: lambda$-2113069712, reason: not valid java name */
    private static r7.q<x6.x, Composer, Integer, x6.t0> f64lambda$2113069712;

    /* JADX INFO: renamed from: lambda$-462453458, reason: not valid java name */
    private static r7.q<androidx.tv.foundation.lazy.list.c0, Composer, Integer, x6.t0> f65lambda$462453458;
    private static r7.q<androidx.tv.foundation.lazy.list.c0, Composer, Integer, x6.t0> lambda$1800073584;
    private static r7.q<androidx.tv.foundation.lazy.list.c0, Composer, Integer, x6.t0> lambda$783853457;

    static {
        final int i10 = 0;
        lambda$783853457 = ComposableLambdaKt.composableLambdaInstance(783853457, false, new r7.q() { // from class: com.arflix.tv.ui.screens.details.a
            @Override // r7.q
            public final Object invoke(Object obj, Object obj2, Object obj3) {
                switch (i10) {
                    case 0:
                        return ComposableSingletons$DetailsScreenKt.lambda_783853457$lambda$0((androidx.tv.foundation.lazy.list.c0) obj, (Composer) obj2, ((Integer) obj3).intValue());
                    case 1:
                        return ComposableSingletons$DetailsScreenKt.lambda_1800073584$lambda$0((androidx.tv.foundation.lazy.list.c0) obj, (Composer) obj2, ((Integer) obj3).intValue());
                    case 2:
                        return ComposableSingletons$DetailsScreenKt.lambda__1478673585$lambda$0((androidx.tv.foundation.lazy.list.c0) obj, (Composer) obj2, ((Integer) obj3).intValue());
                    case 3:
                        return ComposableSingletons$DetailsScreenKt.lambda__462453458$lambda$0((androidx.tv.foundation.lazy.list.c0) obj, (Composer) obj2, ((Integer) obj3).intValue());
                    case 4:
                        return ComposableSingletons$DetailsScreenKt.lambda__1308153404$lambda$0((androidx.tv.foundation.lazy.list.c0) obj, (Composer) obj2, ((Integer) obj3).intValue());
                    default:
                        return ComposableSingletons$DetailsScreenKt.lambda__2113069712$lambda$0((x6.x) obj, (Composer) obj2, ((Integer) obj3).intValue());
                }
            }
        });
        final int i11 = 1;
        lambda$1800073584 = ComposableLambdaKt.composableLambdaInstance(1800073584, false, new r7.q() { // from class: com.arflix.tv.ui.screens.details.a
            @Override // r7.q
            public final Object invoke(Object obj, Object obj2, Object obj3) {
                switch (i11) {
                    case 0:
                        return ComposableSingletons$DetailsScreenKt.lambda_783853457$lambda$0((androidx.tv.foundation.lazy.list.c0) obj, (Composer) obj2, ((Integer) obj3).intValue());
                    case 1:
                        return ComposableSingletons$DetailsScreenKt.lambda_1800073584$lambda$0((androidx.tv.foundation.lazy.list.c0) obj, (Composer) obj2, ((Integer) obj3).intValue());
                    case 2:
                        return ComposableSingletons$DetailsScreenKt.lambda__1478673585$lambda$0((androidx.tv.foundation.lazy.list.c0) obj, (Composer) obj2, ((Integer) obj3).intValue());
                    case 3:
                        return ComposableSingletons$DetailsScreenKt.lambda__462453458$lambda$0((androidx.tv.foundation.lazy.list.c0) obj, (Composer) obj2, ((Integer) obj3).intValue());
                    case 4:
                        return ComposableSingletons$DetailsScreenKt.lambda__1308153404$lambda$0((androidx.tv.foundation.lazy.list.c0) obj, (Composer) obj2, ((Integer) obj3).intValue());
                    default:
                        return ComposableSingletons$DetailsScreenKt.lambda__2113069712$lambda$0((x6.x) obj, (Composer) obj2, ((Integer) obj3).intValue());
                }
            }
        });
        final int i12 = 2;
        f63lambda$1478673585 = ComposableLambdaKt.composableLambdaInstance(-1478673585, false, new r7.q() { // from class: com.arflix.tv.ui.screens.details.a
            @Override // r7.q
            public final Object invoke(Object obj, Object obj2, Object obj3) {
                switch (i12) {
                    case 0:
                        return ComposableSingletons$DetailsScreenKt.lambda_783853457$lambda$0((androidx.tv.foundation.lazy.list.c0) obj, (Composer) obj2, ((Integer) obj3).intValue());
                    case 1:
                        return ComposableSingletons$DetailsScreenKt.lambda_1800073584$lambda$0((androidx.tv.foundation.lazy.list.c0) obj, (Composer) obj2, ((Integer) obj3).intValue());
                    case 2:
                        return ComposableSingletons$DetailsScreenKt.lambda__1478673585$lambda$0((androidx.tv.foundation.lazy.list.c0) obj, (Composer) obj2, ((Integer) obj3).intValue());
                    case 3:
                        return ComposableSingletons$DetailsScreenKt.lambda__462453458$lambda$0((androidx.tv.foundation.lazy.list.c0) obj, (Composer) obj2, ((Integer) obj3).intValue());
                    case 4:
                        return ComposableSingletons$DetailsScreenKt.lambda__1308153404$lambda$0((androidx.tv.foundation.lazy.list.c0) obj, (Composer) obj2, ((Integer) obj3).intValue());
                    default:
                        return ComposableSingletons$DetailsScreenKt.lambda__2113069712$lambda$0((x6.x) obj, (Composer) obj2, ((Integer) obj3).intValue());
                }
            }
        });
        final int i13 = 3;
        f65lambda$462453458 = ComposableLambdaKt.composableLambdaInstance(-462453458, false, new r7.q() { // from class: com.arflix.tv.ui.screens.details.a
            @Override // r7.q
            public final Object invoke(Object obj, Object obj2, Object obj3) {
                switch (i13) {
                    case 0:
                        return ComposableSingletons$DetailsScreenKt.lambda_783853457$lambda$0((androidx.tv.foundation.lazy.list.c0) obj, (Composer) obj2, ((Integer) obj3).intValue());
                    case 1:
                        return ComposableSingletons$DetailsScreenKt.lambda_1800073584$lambda$0((androidx.tv.foundation.lazy.list.c0) obj, (Composer) obj2, ((Integer) obj3).intValue());
                    case 2:
                        return ComposableSingletons$DetailsScreenKt.lambda__1478673585$lambda$0((androidx.tv.foundation.lazy.list.c0) obj, (Composer) obj2, ((Integer) obj3).intValue());
                    case 3:
                        return ComposableSingletons$DetailsScreenKt.lambda__462453458$lambda$0((androidx.tv.foundation.lazy.list.c0) obj, (Composer) obj2, ((Integer) obj3).intValue());
                    case 4:
                        return ComposableSingletons$DetailsScreenKt.lambda__1308153404$lambda$0((androidx.tv.foundation.lazy.list.c0) obj, (Composer) obj2, ((Integer) obj3).intValue());
                    default:
                        return ComposableSingletons$DetailsScreenKt.lambda__2113069712$lambda$0((x6.x) obj, (Composer) obj2, ((Integer) obj3).intValue());
                }
            }
        });
        final int i14 = 4;
        f62lambda$1308153404 = ComposableLambdaKt.composableLambdaInstance(-1308153404, false, new r7.q() { // from class: com.arflix.tv.ui.screens.details.a
            @Override // r7.q
            public final Object invoke(Object obj, Object obj2, Object obj3) {
                switch (i14) {
                    case 0:
                        return ComposableSingletons$DetailsScreenKt.lambda_783853457$lambda$0((androidx.tv.foundation.lazy.list.c0) obj, (Composer) obj2, ((Integer) obj3).intValue());
                    case 1:
                        return ComposableSingletons$DetailsScreenKt.lambda_1800073584$lambda$0((androidx.tv.foundation.lazy.list.c0) obj, (Composer) obj2, ((Integer) obj3).intValue());
                    case 2:
                        return ComposableSingletons$DetailsScreenKt.lambda__1478673585$lambda$0((androidx.tv.foundation.lazy.list.c0) obj, (Composer) obj2, ((Integer) obj3).intValue());
                    case 3:
                        return ComposableSingletons$DetailsScreenKt.lambda__462453458$lambda$0((androidx.tv.foundation.lazy.list.c0) obj, (Composer) obj2, ((Integer) obj3).intValue());
                    case 4:
                        return ComposableSingletons$DetailsScreenKt.lambda__1308153404$lambda$0((androidx.tv.foundation.lazy.list.c0) obj, (Composer) obj2, ((Integer) obj3).intValue());
                    default:
                        return ComposableSingletons$DetailsScreenKt.lambda__2113069712$lambda$0((x6.x) obj, (Composer) obj2, ((Integer) obj3).intValue());
                }
            }
        });
        final int i15 = 5;
        f64lambda$2113069712 = ComposableLambdaKt.composableLambdaInstance(-2113069712, false, new r7.q() { // from class: com.arflix.tv.ui.screens.details.a
            @Override // r7.q
            public final Object invoke(Object obj, Object obj2, Object obj3) {
                switch (i15) {
                    case 0:
                        return ComposableSingletons$DetailsScreenKt.lambda_783853457$lambda$0((androidx.tv.foundation.lazy.list.c0) obj, (Composer) obj2, ((Integer) obj3).intValue());
                    case 1:
                        return ComposableSingletons$DetailsScreenKt.lambda_1800073584$lambda$0((androidx.tv.foundation.lazy.list.c0) obj, (Composer) obj2, ((Integer) obj3).intValue());
                    case 2:
                        return ComposableSingletons$DetailsScreenKt.lambda__1478673585$lambda$0((androidx.tv.foundation.lazy.list.c0) obj, (Composer) obj2, ((Integer) obj3).intValue());
                    case 3:
                        return ComposableSingletons$DetailsScreenKt.lambda__462453458$lambda$0((androidx.tv.foundation.lazy.list.c0) obj, (Composer) obj2, ((Integer) obj3).intValue());
                    case 4:
                        return ComposableSingletons$DetailsScreenKt.lambda__1308153404$lambda$0((androidx.tv.foundation.lazy.list.c0) obj, (Composer) obj2, ((Integer) obj3).intValue());
                    default:
                        return ComposableSingletons$DetailsScreenKt.lambda__2113069712$lambda$0((x6.x) obj, (Composer) obj2, ((Integer) obj3).intValue());
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 lambda_1800073584$lambda$0(androidx.tv.foundation.lazy.list.c0 c0Var, Composer composer, int i10) {
        if ((i10 & 17) == 16 && composer.getSkipping()) {
            composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1800073584, i10, -1, "com.arflix.tv.ui.screens.details.ComposableSingletons$DetailsScreenKt.lambda$1800073584.<anonymous> (DetailsScreen.kt:2457)");
            }
            SpacerKt.Spacer(SizeKt.m564height3ABfNKs(Modifier.INSTANCE, Dp.m5678constructorimpl(4)), composer, 6);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 lambda_783853457$lambda$0(androidx.tv.foundation.lazy.list.c0 c0Var, Composer composer, int i10) {
        if ((i10 & 17) == 16 && composer.getSkipping()) {
            composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(783853457, i10, -1, "com.arflix.tv.ui.screens.details.ComposableSingletons$DetailsScreenKt.lambda$783853457.<anonymous> (DetailsScreen.kt:2443)");
            }
            SpacerKt.Spacer(SizeKt.m564height3ABfNKs(Modifier.INSTANCE, Dp.m5678constructorimpl(4)), composer, 6);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 lambda__101879240$lambda$0(BoxScope boxScope, boolean z, Composer composer, int i10) {
        if ((i10 & TsExtractor.TS_STREAM_TYPE_AC3) == 128 && composer.getSkipping()) {
            composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-101879240, i10, -1, "com.arflix.tv.ui.screens.details.ComposableSingletons$DetailsScreenKt.lambda$-101879240.<anonymous> (DetailsScreen.kt:3285)");
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 lambda__1308153404$lambda$0(androidx.tv.foundation.lazy.list.c0 c0Var, Composer composer, int i10) {
        if ((i10 & 17) == 16 && composer.getSkipping()) {
            composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1308153404, i10, -1, "com.arflix.tv.ui.screens.details.ComposableSingletons$DetailsScreenKt.lambda$-1308153404.<anonymous> (DetailsScreen.kt:2502)");
            }
            SpacerKt.Spacer(SizeKt.m564height3ABfNKs(Modifier.INSTANCE, Dp.m5678constructorimpl(20)), composer, 6);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 lambda__1478673585$lambda$0(androidx.tv.foundation.lazy.list.c0 c0Var, Composer composer, int i10) {
        if ((i10 & 17) == 16 && composer.getSkipping()) {
            composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1478673585, i10, -1, "com.arflix.tv.ui.screens.details.ComposableSingletons$DetailsScreenKt.lambda$-1478673585.<anonymous> (DetailsScreen.kt:2471)");
            }
            SpacerKt.Spacer(SizeKt.m564height3ABfNKs(Modifier.INSTANCE, Dp.m5678constructorimpl(4)), composer, 6);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Type inference failed for: r2v0 */
    /* JADX WARN: Type inference failed for: r2v1, types: [boolean, int] */
    /* JADX WARN: Type inference failed for: r2v23 */
    public static final x6.t0 lambda__2113069712$lambda$0(x6.x xVar, Composer composer, int i10) {
        Composer composer2 = composer;
        ?? r22 = 0;
        Integer num = 0;
        int i11 = (i10 & 6) == 0 ? i10 | (composer2.changed(xVar) ? 4 : 2) : i10;
        if ((i11 & 19) == 18 && composer2.getSkipping()) {
            composer2.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-2113069712, i11, -1, "com.arflix.tv.ui.screens.details.ComposableSingletons$DetailsScreenKt.lambda$-2113069712.<anonymous> (DetailsScreen.kt:2768)");
            }
            List list = (List) xVar.f22608i;
            boolean zBooleanValue = ((Boolean) xVar.f22609l).booleanValue();
            Arrangement.HorizontalOrVertical horizontalOrVerticalM441spacedBy0680j_4 = Arrangement.INSTANCE.m441spacedBy0680j_4(Dp.m5678constructorimpl(7));
            Alignment.Vertical centerVertically = Alignment.INSTANCE.getCenterVertically();
            composer2.startReplaceableGroup(693286680);
            Modifier.Companion companion = Modifier.INSTANCE;
            int i12 = 54;
            MeasurePolicy measurePolicyRowMeasurePolicy = RowKt.rowMeasurePolicy(horizontalOrVerticalM441spacedBy0680j_4, centerVertically, composer2, 54);
            int i13 = -1323940314;
            composer2.startReplaceableGroup(-1323940314);
            int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composer2, 0);
            CompositionLocalMap currentCompositionLocalMap = composer2.getCurrentCompositionLocalMap();
            ComposeUiNode.Companion companion2 = ComposeUiNode.INSTANCE;
            r7.a<ComposeUiNode> constructor = companion2.getConstructor();
            r7.q<SkippableUpdater<ComposeUiNode>, Composer, Integer, x6.t0> qVarModifierMaterializerOf = LayoutKt.modifierMaterializerOf(companion);
            if (composer2.getApplier() == null) {
                ComposablesKt.invalidApplier();
            }
            composer2.startReusableNode();
            if (composer2.getInserting()) {
                composer2.createNode(constructor);
            } else {
                composer2.useNode();
            }
            Composer composerM2991constructorimpl = Updater.m2991constructorimpl(composer2);
            r7.p pVarU = a0.c.u(companion2, composerM2991constructorimpl, measurePolicyRowMeasurePolicy, composerM2991constructorimpl, currentCompositionLocalMap);
            if (composerM2991constructorimpl.getInserting() || !kotlin.jvm.internal.p.a(composerM2991constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                a0.c.w(currentCompositeKeyHash, composerM2991constructorimpl, currentCompositeKeyHash, pVarU);
            }
            int i14 = 2058660585;
            a0.c.y(composer2, qVarModifierMaterializerOf, composer2, num, 2058660585);
            RowScopeInstance rowScopeInstance = RowScopeInstance.INSTANCE;
            composer2.startReplaceGroup(443640095);
            int i15 = 0;
            for (Object obj : list) {
                int i16 = i15 + 1;
                if (i15 < 0) {
                    t7.a.Q();
                    throw null;
                }
                Episode episode = (Episode) obj;
                Float fH = kotlin.text.t.H(episode.getImdbRating());
                float fFloatValue = fH != null ? fH.floatValue() : 0.0f;
                long episodeRatingColor = DetailsScreenKt.getEpisodeRatingColor(fFloatValue);
                long episodeRatingTextColor = DetailsScreenKt.getEpisodeRatingTextColor(fFloatValue);
                String episodeRating = DetailsScreenKt.formatEpisodeRating(episode.getImdbRating());
                Modifier.Companion companion3 = Modifier.INSTANCE;
                float f10 = 64;
                Modifier modifierL = com.arflix.tv.data.repository.g.l(8, SizeKt.m580sizeVpY3zN4(companion3, Dp.m5678constructorimpl(f10), Dp.m5678constructorimpl(f10)), episodeRatingColor);
                Alignment.Companion companion4 = Alignment.INSTANCE;
                Alignment center = companion4.getCenter();
                composer2.startReplaceableGroup(733328855);
                MeasurePolicy measurePolicyRememberBoxMeasurePolicy = BoxKt.rememberBoxMeasurePolicy(center, r22, composer2, 6);
                composer2.startReplaceableGroup(i13);
                int currentCompositeKeyHash2 = ComposablesKt.getCurrentCompositeKeyHash(composer2, r22);
                CompositionLocalMap currentCompositionLocalMap2 = composer2.getCurrentCompositionLocalMap();
                ComposeUiNode.Companion companion5 = ComposeUiNode.INSTANCE;
                r7.a<ComposeUiNode> constructor2 = companion5.getConstructor();
                r7.q<SkippableUpdater<ComposeUiNode>, Composer, Integer, x6.t0> qVarModifierMaterializerOf2 = LayoutKt.modifierMaterializerOf(modifierL);
                if (composer2.getApplier() == null) {
                    ComposablesKt.invalidApplier();
                }
                composer2.startReusableNode();
                if (composer2.getInserting()) {
                    composer2.createNode(constructor2);
                } else {
                    composer2.useNode();
                }
                Composer composerM2991constructorimpl2 = Updater.m2991constructorimpl(composer2);
                r7.p pVarU2 = a0.c.u(companion5, composerM2991constructorimpl2, measurePolicyRememberBoxMeasurePolicy, composerM2991constructorimpl2, currentCompositionLocalMap2);
                if (composerM2991constructorimpl2.getInserting() || !kotlin.jvm.internal.p.a(composerM2991constructorimpl2.rememberedValue(), Integer.valueOf(currentCompositeKeyHash2))) {
                    a0.c.w(currentCompositeKeyHash2, composerM2991constructorimpl2, currentCompositeKeyHash2, pVarU2);
                }
                a0.c.y(composer2, qVarModifierMaterializerOf2, composer2, num, i14);
                BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
                Alignment.Horizontal centerHorizontally = companion4.getCenterHorizontally();
                Arrangement.HorizontalOrVertical center2 = Arrangement.INSTANCE.getCenter();
                composer2.startReplaceableGroup(-483455358);
                MeasurePolicy measurePolicyColumnMeasurePolicy = ColumnKt.columnMeasurePolicy(center2, centerHorizontally, composer2, i12);
                composer2.startReplaceableGroup(-1323940314);
                int currentCompositeKeyHash3 = ComposablesKt.getCurrentCompositeKeyHash(composer2, 0);
                CompositionLocalMap currentCompositionLocalMap3 = composer2.getCurrentCompositionLocalMap();
                r7.a<ComposeUiNode> constructor3 = companion5.getConstructor();
                r7.q<SkippableUpdater<ComposeUiNode>, Composer, Integer, x6.t0> qVarModifierMaterializerOf3 = LayoutKt.modifierMaterializerOf(companion3);
                if (composer2.getApplier() == null) {
                    ComposablesKt.invalidApplier();
                }
                composer2.startReusableNode();
                if (composer2.getInserting()) {
                    composer2.createNode(constructor3);
                } else {
                    composer2.useNode();
                }
                Composer composerM2991constructorimpl3 = Updater.m2991constructorimpl(composer2);
                r7.p pVarU3 = a0.c.u(companion5, composerM2991constructorimpl3, measurePolicyColumnMeasurePolicy, composerM2991constructorimpl3, currentCompositionLocalMap3);
                if (composerM2991constructorimpl3.getInserting() || !kotlin.jvm.internal.p.a(composerM2991constructorimpl3.rememberedValue(), Integer.valueOf(currentCompositeKeyHash3))) {
                    a0.c.w(currentCompositeKeyHash3, composerM2991constructorimpl3, currentCompositeKeyHash3, pVarU3);
                }
                a0.c.y(composer2, qVarModifierMaterializerOf3, composer2, num, i14);
                ColumnScopeInstance columnScopeInstance = ColumnScopeInstance.INSTANCE;
                String strI = a0.c.i(episode.getEpisodeNumber(), "E");
                long jM3471copywmQWz5c$default = Color.m3471copywmQWz5c$default(episodeRatingTextColor, 0.8f, 0.0f, 0.0f, 0.0f, 14, null);
                ArvioSkin arvioSkin = ArvioSkin.INSTANCE;
                TextStyle body = arvioSkin.getTypography(composer2, 6).getBody();
                FontWeight.Companion companion6 = FontWeight.INSTANCE;
                TextKt.m6020Text4IGK_g(strI, null, jM3471copywmQWz5c$default, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, TextStyle.m5205copyp1EtxEg$default(body, 0L, TextUnitKt.getSp(12), companion6.getNormal(), null, null, null, null, 0L, null, null, null, 0L, null, null, null, 0, 0, 0L, null, null, null, 0, 0, null, 16777209, null), composer, 0, 0, 65530);
                TextKt.m6020Text4IGK_g(episodeRating, null, episodeRatingTextColor, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, TextStyle.m5205copyp1EtxEg$default(arvioSkin.getTypography(composer, 6).getBody(), 0L, TextUnitKt.getSp(16), companion6.getBold(), null, null, null, null, 0L, null, null, null, 0L, null, null, null, 0, 0, 0L, null, null, null, 0, 0, null, 16777209, null), composer, 0, 0, 65530);
                composer2 = composer;
                com.arflix.tv.data.repository.g.w(composer2);
                i15 = i16;
                i12 = i12;
                num = num;
                i14 = 2058660585;
                r22 = 0;
                i13 = -1323940314;
            }
            Integer num2 = num;
            composer2.endReplaceGroup();
            if (zBooleanValue) {
                composer2.startReplaceGroup(869717514);
                Modifier.Companion companion7 = Modifier.INSTANCE;
                Modifier modifierM578size3ABfNKs = SizeKt.m578size3ABfNKs(companion7, Dp.m5678constructorimpl(24));
                Alignment center3 = Alignment.INSTANCE.getCenter();
                composer2.startReplaceableGroup(733328855);
                MeasurePolicy measurePolicyRememberBoxMeasurePolicy2 = BoxKt.rememberBoxMeasurePolicy(center3, false, composer2, 6);
                composer2.startReplaceableGroup(-1323940314);
                int currentCompositeKeyHash4 = ComposablesKt.getCurrentCompositeKeyHash(composer2, 0);
                CompositionLocalMap currentCompositionLocalMap4 = composer2.getCurrentCompositionLocalMap();
                ComposeUiNode.Companion companion8 = ComposeUiNode.INSTANCE;
                r7.a<ComposeUiNode> constructor4 = companion8.getConstructor();
                r7.q<SkippableUpdater<ComposeUiNode>, Composer, Integer, x6.t0> qVarModifierMaterializerOf4 = LayoutKt.modifierMaterializerOf(modifierM578size3ABfNKs);
                if (composer2.getApplier() == null) {
                    ComposablesKt.invalidApplier();
                }
                composer2.startReusableNode();
                if (composer2.getInserting()) {
                    composer2.createNode(constructor4);
                } else {
                    composer2.useNode();
                }
                Composer composerM2991constructorimpl4 = Updater.m2991constructorimpl(composer2);
                r7.p pVarU4 = a0.c.u(companion8, composerM2991constructorimpl4, measurePolicyRememberBoxMeasurePolicy2, composerM2991constructorimpl4, currentCompositionLocalMap4);
                if (composerM2991constructorimpl4.getInserting() || !kotlin.jvm.internal.p.a(composerM2991constructorimpl4.rememberedValue(), Integer.valueOf(currentCompositeKeyHash4))) {
                    a0.c.w(currentCompositeKeyHash4, composerM2991constructorimpl4, currentCompositeKeyHash4, pVarU4);
                }
                a0.c.y(composer2, qVarModifierMaterializerOf4, composer2, num2, 2058660585);
                BoxScopeInstance boxScopeInstance2 = BoxScopeInstance.INSTANCE;
                BoxKt.Box(BackgroundKt.m178backgroundbw27NRU(SizeKt.m578size3ABfNKs(companion7, Dp.m5678constructorimpl(6)), Color.INSTANCE.m3509getWhite0d7_KjU(), RoundedCornerShapeKt.getCircleShape()), composer2, 0);
                composer2.endReplaceableGroup();
                composer2.endNode();
                composer2.endReplaceableGroup();
                composer2.endReplaceableGroup();
                composer2.endReplaceGroup();
            } else {
                composer2.startReplaceGroup(870251830);
                composer2.endReplaceGroup();
            }
            if (androidx.compose.foundation.c.D(composer2)) {
                ComposerKt.traceEventEnd();
            }
        }
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 lambda__462453458$lambda$0(androidx.tv.foundation.lazy.list.c0 c0Var, Composer composer, int i10) {
        if ((i10 & 17) == 16 && composer.getSkipping()) {
            composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-462453458, i10, -1, "com.arflix.tv.ui.screens.details.ComposableSingletons$DetailsScreenKt.lambda$-462453458.<anonymous> (DetailsScreen.kt:2487)");
            }
            SpacerKt.Spacer(SizeKt.m564height3ABfNKs(Modifier.INSTANCE, Dp.m5678constructorimpl(4)), composer, 6);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return x6.t0.f22605a;
    }

    /* JADX INFO: renamed from: getLambda$-101879240$app_playRelease, reason: not valid java name */
    public final r7.r<BoxScope, Boolean, Composer, Integer, x6.t0> m6205getLambda$101879240$app_playRelease() {
        return f61lambda$101879240;
    }

    /* JADX INFO: renamed from: getLambda$-1308153404$app_playRelease, reason: not valid java name */
    public final r7.q<androidx.tv.foundation.lazy.list.c0, Composer, Integer, x6.t0> m6206getLambda$1308153404$app_playRelease() {
        return f62lambda$1308153404;
    }

    /* JADX INFO: renamed from: getLambda$-1478673585$app_playRelease, reason: not valid java name */
    public final r7.q<androidx.tv.foundation.lazy.list.c0, Composer, Integer, x6.t0> m6207getLambda$1478673585$app_playRelease() {
        return f63lambda$1478673585;
    }

    /* JADX INFO: renamed from: getLambda$-2113069712$app_playRelease, reason: not valid java name */
    public final r7.q<x6.x, Composer, Integer, x6.t0> m6208getLambda$2113069712$app_playRelease() {
        return f64lambda$2113069712;
    }

    /* JADX INFO: renamed from: getLambda$-462453458$app_playRelease, reason: not valid java name */
    public final r7.q<androidx.tv.foundation.lazy.list.c0, Composer, Integer, x6.t0> m6209getLambda$462453458$app_playRelease() {
        return f65lambda$462453458;
    }

    public final r7.q<androidx.tv.foundation.lazy.list.c0, Composer, Integer, x6.t0> getLambda$1800073584$app_playRelease() {
        return lambda$1800073584;
    }

    public final r7.q<androidx.tv.foundation.lazy.list.c0, Composer, Integer, x6.t0> getLambda$783853457$app_playRelease() {
        return lambda$783853457;
    }
}
