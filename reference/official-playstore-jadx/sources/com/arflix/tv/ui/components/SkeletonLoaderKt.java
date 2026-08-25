package com.arflix.tv.ui.components;

import androidx.compose.foundation.layout.Arrangement;
import androidx.compose.foundation.layout.AspectRatioKt;
import androidx.compose.foundation.layout.ColumnScopeInstance;
import androidx.compose.foundation.layout.RowScopeInstance;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.foundation.lazy.LazyItemScope;
import androidx.compose.foundation.lazy.LazyListScope;
import androidx.compose.foundation.shape.RoundedCornerShape;
import androidx.compose.foundation.shape.RoundedCornerShapeKt;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalMap;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.SkippableUpdater;
import androidx.compose.runtime.Updater;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.Brush;
import androidx.compose.ui.layout.LayoutKt;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.unit.Dp;
import androidx.media3.extractor.ts.TsExtractor;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u000b\u001a\u000f\u0010\u0001\u001a\u00020\u0000H\u0007¢\u0006\u0004\b\u0001\u0010\u0002\u001a#\u0010\b\u001a\u00020\u00072\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u0005H\u0007¢\u0006\u0004\b\b\u0010\t\u001a#\u0010\u000e\u001a\u00020\u00072\b\b\u0002\u0010\u000b\u001a\u00020\n2\b\b\u0002\u0010\u0004\u001a\u00020\u0003H\u0007¢\u0006\u0004\b\f\u0010\r\u001a#\u0010\u0010\u001a\u00020\u00072\b\b\u0002\u0010\u000b\u001a\u00020\n2\b\b\u0002\u0010\u0004\u001a\u00020\u0003H\u0007¢\u0006\u0004\b\u000f\u0010\r\u001a\u0019\u0010\u0011\u001a\u00020\u00072\b\b\u0002\u0010\u0004\u001a\u00020\u0003H\u0007¢\u0006\u0004\b\u0011\u0010\u0012\u001a\u0019\u0010\u0013\u001a\u00020\u00072\b\b\u0002\u0010\u0004\u001a\u00020\u0003H\u0007¢\u0006\u0004\b\u0013\u0010\u0012\u001a7\u0010\u001a\u001a\u00020\u00072\b\b\u0002\u0010\u0015\u001a\u00020\u00142\b\b\u0002\u0010\u0017\u001a\u00020\u00162\b\b\u0002\u0010\u0019\u001a\u00020\u00182\b\b\u0002\u0010\u0004\u001a\u00020\u0003H\u0007¢\u0006\u0004\b\u001a\u0010\u001b\u001a\u0019\u0010\u001c\u001a\u00020\u00072\b\b\u0002\u0010\u0004\u001a\u00020\u0003H\u0007¢\u0006\u0004\b\u001c\u0010\u0012\u001a-\u0010\u001e\u001a\u00020\u00072\b\b\u0002\u0010\u001d\u001a\u00020\u00182\b\b\u0002\u0010\u0019\u001a\u00020\u00182\b\b\u0002\u0010\u0004\u001a\u00020\u0003H\u0007¢\u0006\u0004\b\u001e\u0010\u001f\u001a#\u0010!\u001a\u00020\u00072\b\b\u0002\u0010 \u001a\u00020\u00142\b\b\u0002\u0010\u0004\u001a\u00020\u0003H\u0007¢\u0006\u0004\b!\u0010\"¨\u0006#"}, d2 = {"Landroidx/compose/ui/graphics/Brush;", "shimmerBrush", "(Landroidx/compose/runtime/Composer;I)Landroidx/compose/ui/graphics/Brush;", "Landroidx/compose/ui/Modifier;", "modifier", "Landroidx/compose/foundation/shape/RoundedCornerShape;", "shape", "Lx6/t0;", "SkeletonBox", "(Landroidx/compose/ui/Modifier;Landroidx/compose/foundation/shape/RoundedCornerShape;Landroidx/compose/runtime/Composer;II)V", "Landroidx/compose/ui/unit/Dp;", "width", "SkeletonPosterCard--orJrPs", "(FLandroidx/compose/ui/Modifier;Landroidx/compose/runtime/Composer;II)V", "SkeletonPosterCard", "SkeletonMediaCard--orJrPs", "SkeletonMediaCard", "SkeletonCastCard", "(Landroidx/compose/ui/Modifier;Landroidx/compose/runtime/Composer;II)V", "SkeletonEpisodeCard", "", "cardCount", "Lcom/arflix/tv/ui/components/SkeletonCardType;", "cardType", "", "isMobile", "SkeletonCategoryRow", "(ILcom/arflix/tv/ui/components/SkeletonCardType;ZLandroidx/compose/ui/Modifier;Landroidx/compose/runtime/Composer;II)V", "SkeletonDetailsHero", "isTV", "SkeletonDetailsPage", "(ZZLandroidx/compose/ui/Modifier;Landroidx/compose/runtime/Composer;II)V", "rowCount", "SkeletonHomePage", "(ILandroidx/compose/ui/Modifier;Landroidx/compose/runtime/Composer;II)V", "app_playRelease"}, k = 2, mv = {2, 3, 0}, xi = 48)
public final class SkeletonLoaderKt {

    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[SkeletonCardType.values().length];
            try {
                iArr[SkeletonCardType.POSTER.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[SkeletonCardType.MEDIA.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[SkeletonCardType.EPISODE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                iArr[SkeletonCardType.CAST.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:44:0x007a  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x009a  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void SkeletonBox(androidx.compose.ui.Modifier r10, androidx.compose.foundation.shape.RoundedCornerShape r11, androidx.compose.runtime.Composer r12, int r13, int r14) {
        /*
            r0 = -437686219(0xffffffffe5e97035, float:-1.3779766E23)
            androidx.compose.runtime.Composer r12 = r12.startRestartGroup(r0)
            r1 = r14 & 1
            if (r1 == 0) goto Le
            r2 = r13 | 6
            goto L1e
        Le:
            r2 = r13 & 6
            if (r2 != 0) goto L1d
            boolean r2 = r12.changed(r10)
            if (r2 == 0) goto L1a
            r2 = 4
            goto L1b
        L1a:
            r2 = 2
        L1b:
            r2 = r2 | r13
            goto L1e
        L1d:
            r2 = r13
        L1e:
            r3 = r13 & 48
            if (r3 != 0) goto L32
            r3 = r14 & 2
            if (r3 != 0) goto L2f
            boolean r3 = r12.changed(r11)
            if (r3 == 0) goto L2f
            r3 = 32
            goto L31
        L2f:
            r3 = 16
        L31:
            r2 = r2 | r3
        L32:
            r3 = r2 & 19
            r4 = 18
            if (r3 != r4) goto L45
            boolean r3 = r12.getSkipping()
            if (r3 != 0) goto L3f
            goto L45
        L3f:
            r12.skipToGroupEnd()
        L42:
            r2 = r10
            r3 = r11
            goto L9e
        L45:
            r12.startDefaults()
            r3 = r13 & 1
            if (r3 == 0) goto L5d
            boolean r3 = r12.getDefaultsInvalid()
            if (r3 == 0) goto L53
            goto L5d
        L53:
            r12.skipToGroupEnd()
            r1 = r14 & 2
            if (r1 == 0) goto L71
        L5a:
            r2 = r2 & (-113(0xffffffffffffff8f, float:NaN))
            goto L71
        L5d:
            if (r1 == 0) goto L61
            androidx.compose.ui.Modifier$Companion r10 = androidx.compose.ui.Modifier.INSTANCE
        L61:
            r1 = r14 & 2
            if (r1 == 0) goto L71
            r11 = 8
            float r11 = (float) r11
            float r11 = androidx.compose.ui.unit.Dp.m5678constructorimpl(r11)
            androidx.compose.foundation.shape.RoundedCornerShape r11 = androidx.compose.foundation.shape.RoundedCornerShapeKt.m798RoundedCornerShape0680j_4(r11)
            goto L5a
        L71:
            r12.endDefaults()
            boolean r1 = androidx.compose.runtime.ComposerKt.isTraceInProgress()
            if (r1 == 0) goto L80
            r1 = -1
            java.lang.String r3 = "com.arflix.tv.ui.components.SkeletonBox (SkeletonLoader.kt:83)"
            androidx.compose.runtime.ComposerKt.traceEventStart(r0, r2, r1, r3)
        L80:
            androidx.compose.ui.Modifier r4 = androidx.compose.ui.draw.ClipKt.clip(r10, r11)
            r0 = 0
            androidx.compose.ui.graphics.Brush r5 = shimmerBrush(r12, r0)
            r8 = 6
            r9 = 0
            r6 = 0
            r7 = 0
            androidx.compose.ui.Modifier r1 = androidx.compose.foundation.BackgroundKt.background$default(r4, r5, r6, r7, r8, r9)
            androidx.compose.foundation.layout.BoxKt.Box(r1, r12, r0)
            boolean r0 = androidx.compose.runtime.ComposerKt.isTraceInProgress()
            if (r0 == 0) goto L42
            androidx.compose.runtime.ComposerKt.traceEventEnd()
            goto L42
        L9e:
            androidx.compose.runtime.ScopeUpdateScope r10 = r12.endRestartGroup()
            if (r10 == 0) goto Laf
            com.arflix.tv.ui.components.p1 r1 = new com.arflix.tv.ui.components.p1
            r6 = 1
            r4 = r13
            r5 = r14
            r1.<init>(r2, r3, r4, r5, r6)
            r10.updateScope(r1)
        Laf:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.components.SkeletonLoaderKt.SkeletonBox(androidx.compose.ui.Modifier, androidx.compose.foundation.shape.RoundedCornerShape, androidx.compose.runtime.Composer, int, int):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 SkeletonBox$lambda$0(Modifier modifier, RoundedCornerShape roundedCornerShape, int i10, int i11, Composer composer, int i12) {
        SkeletonBox(modifier, roundedCornerShape, composer, RecomposeScopeImplKt.updateChangedFlags(i10 | 1), i11);
        return x6.t0.f22605a;
    }

    public static final void SkeletonCastCard(Modifier modifier, Composer composer, int i10, int i11) {
        int i12;
        Composer composerStartRestartGroup = composer.startRestartGroup(1817166350);
        int i13 = i11 & 1;
        if (i13 != 0) {
            i12 = i10 | 6;
        } else if ((i10 & 6) == 0) {
            i12 = (composerStartRestartGroup.changed(modifier) ? 4 : 2) | i10;
        } else {
            i12 = i10;
        }
        if ((i12 & 3) == 2 && composerStartRestartGroup.getSkipping()) {
            composerStartRestartGroup.skipToGroupEnd();
        } else {
            if (i13 != 0) {
                modifier = Modifier.INSTANCE;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1817166350, i12, -1, "com.arflix.tv.ui.components.SkeletonCastCard (SkeletonLoader.kt:159)");
            }
            Modifier modifierM583width3ABfNKs = SizeKt.m583width3ABfNKs(modifier, Dp.m5678constructorimpl(100));
            Alignment.Horizontal centerHorizontally = Alignment.INSTANCE.getCenterHorizontally();
            composerStartRestartGroup.startReplaceableGroup(-483455358);
            MeasurePolicy measurePolicyF = androidx.compose.material3.d.f(Arrangement.INSTANCE, centerHorizontally, composerStartRestartGroup, 48, -1323940314);
            int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
            CompositionLocalMap currentCompositionLocalMap = composerStartRestartGroup.getCurrentCompositionLocalMap();
            ComposeUiNode.Companion companion = ComposeUiNode.INSTANCE;
            r7.a<ComposeUiNode> constructor = companion.getConstructor();
            r7.q<SkippableUpdater<ComposeUiNode>, Composer, Integer, x6.t0> qVarModifierMaterializerOf = LayoutKt.modifierMaterializerOf(modifierM583width3ABfNKs);
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
            r7.p pVarU = a0.c.u(companion, composerM2991constructorimpl, measurePolicyF, composerM2991constructorimpl, currentCompositionLocalMap);
            if (composerM2991constructorimpl.getInserting() || !kotlin.jvm.internal.p.a(composerM2991constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                a0.c.w(currentCompositeKeyHash, composerM2991constructorimpl, currentCompositeKeyHash, pVarU);
            }
            a0.c.x(0, qVarModifierMaterializerOf, SkippableUpdater.m2980boximpl(SkippableUpdater.m2981constructorimpl(composerStartRestartGroup)), composerStartRestartGroup, 2058660585);
            ColumnScopeInstance columnScopeInstance = ColumnScopeInstance.INSTANCE;
            Modifier.Companion companion2 = Modifier.INSTANCE;
            SkeletonBox(SizeKt.m578size3ABfNKs(companion2, Dp.m5678constructorimpl(80)), RoundedCornerShapeKt.m798RoundedCornerShape0680j_4(Dp.m5678constructorimpl(40)), composerStartRestartGroup, 6, 0);
            com.arflix.tv.data.repository.g.p(8, companion2, composerStartRestartGroup, 6);
            float f10 = 4;
            SkeletonBox(SizeKt.m564height3ABfNKs(SizeKt.m583width3ABfNKs(companion2, Dp.m5678constructorimpl(70)), Dp.m5678constructorimpl(12)), RoundedCornerShapeKt.m798RoundedCornerShape0680j_4(Dp.m5678constructorimpl(f10)), composerStartRestartGroup, 6, 0);
            com.arflix.tv.data.repository.g.p(f10, companion2, composerStartRestartGroup, 6);
            SkeletonBox(SizeKt.m564height3ABfNKs(SizeKt.m583width3ABfNKs(companion2, Dp.m5678constructorimpl(50)), Dp.m5678constructorimpl(10)), RoundedCornerShapeKt.m798RoundedCornerShape0680j_4(Dp.m5678constructorimpl(f10)), composerStartRestartGroup, 6, 0);
            composerStartRestartGroup.endReplaceableGroup();
            composerStartRestartGroup.endNode();
            composerStartRestartGroup.endReplaceableGroup();
            composerStartRestartGroup.endReplaceableGroup();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new n1(modifier, i10, i11, 1));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 SkeletonCastCard$lambda$1(Modifier modifier, int i10, int i11, Composer composer, int i12) {
        SkeletonCastCard(modifier, composer, RecomposeScopeImplKt.updateChangedFlags(i10 | 1), i11);
        return x6.t0.f22605a;
    }

    /* JADX WARN: Removed duplicated region for block: B:104:0x01d4  */
    /* JADX WARN: Removed duplicated region for block: B:119:0x0234  */
    /* JADX WARN: Removed duplicated region for block: B:123:0x0243  */
    /* JADX WARN: Removed duplicated region for block: B:125:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:39:0x006a  */
    /* JADX WARN: Removed duplicated region for block: B:41:0x006f  */
    /* JADX WARN: Removed duplicated region for block: B:50:0x0087  */
    /* JADX WARN: Removed duplicated region for block: B:54:0x0098  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x009b  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x009e  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x00a1  */
    /* JADX WARN: Removed duplicated region for block: B:62:0x00a6  */
    /* JADX WARN: Removed duplicated region for block: B:63:0x00a9  */
    /* JADX WARN: Removed duplicated region for block: B:65:0x00ad  */
    /* JADX WARN: Removed duplicated region for block: B:66:0x00b2  */
    /* JADX WARN: Removed duplicated region for block: B:69:0x00ba  */
    /* JADX WARN: Removed duplicated region for block: B:73:0x00ed  */
    /* JADX WARN: Removed duplicated region for block: B:76:0x00f9  */
    /* JADX WARN: Removed duplicated region for block: B:77:0x00fd  */
    /* JADX WARN: Removed duplicated region for block: B:82:0x011c  */
    /* JADX WARN: Removed duplicated region for block: B:85:0x0149  */
    /* JADX WARN: Removed duplicated region for block: B:87:0x0151  */
    /* JADX WARN: Removed duplicated region for block: B:90:0x0176  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void SkeletonCategoryRow(int r27, com.arflix.tv.ui.components.SkeletonCardType r28, boolean r29, androidx.compose.ui.Modifier r30, androidx.compose.runtime.Composer r31, int r32, int r33) {
        /*
            Method dump skipped, instruction units count: 590
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.components.SkeletonLoaderKt.SkeletonCategoryRow(int, com.arflix.tv.ui.components.SkeletonCardType, boolean, androidx.compose.ui.Modifier, androidx.compose.runtime.Composer, int, int):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 SkeletonCategoryRow$lambda$0$0$0(int i10, SkeletonCardType skeletonCardType, LazyListScope lazyListScope) {
        LazyListScope.CC.k(lazyListScope, i10, null, null, ComposableLambdaKt.composableLambdaInstance(-1087295765, true, new i2(skeletonCardType, 0)), 6, null);
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 SkeletonCategoryRow$lambda$0$0$0$0(SkeletonCardType skeletonCardType, LazyItemScope lazyItemScope, int i10, Composer composer, int i11) {
        if ((i11 & TsExtractor.TS_STREAM_TYPE_AC3) == 128 && composer.getSkipping()) {
            composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1087295765, i11, -1, "com.arflix.tv.ui.components.SkeletonCategoryRow.<anonymous>.<anonymous>.<anonymous>.<anonymous> (SkeletonLoader.kt:243)");
            }
            int i12 = WhenMappings.$EnumSwitchMapping$0[skeletonCardType.ordinal()];
            if (i12 == 1) {
                composer.startReplaceGroup(-1214174305);
                m6186SkeletonPosterCardorJrPs(0.0f, null, composer, 0, 3);
                composer.endReplaceGroup();
            } else if (i12 == 2) {
                composer.startReplaceGroup(-1214172034);
                m6185SkeletonMediaCardorJrPs(0.0f, null, composer, 0, 3);
                composer.endReplaceGroup();
            } else if (i12 == 3) {
                composer.startReplaceGroup(-1214169728);
                SkeletonEpisodeCard(null, composer, 0, 1);
                composer.endReplaceGroup();
            } else {
                if (i12 != 4) {
                    composer.startReplaceGroup(-1214176210);
                    composer.endReplaceGroup();
                    throw new NoWhenBranchMatchedException();
                }
                composer.startReplaceGroup(-1214167459);
                SkeletonCastCard(null, composer, 0, 1);
                composer.endReplaceGroup();
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 SkeletonCategoryRow$lambda$0$1$0(int i10, SkeletonCardType skeletonCardType, androidx.tv.foundation.lazy.list.g0 g0Var) {
        g0Var.items(i10, null, androidx.tv.foundation.lazy.list.f0.f5387l, ComposableLambdaKt.composableLambdaInstance(-2049692012, true, new i2(skeletonCardType, 1)));
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 SkeletonCategoryRow$lambda$0$1$0$0(SkeletonCardType skeletonCardType, androidx.tv.foundation.lazy.list.c0 c0Var, int i10, Composer composer, int i11) {
        if ((i11 & TsExtractor.TS_STREAM_TYPE_AC3) == 128 && composer.getSkipping()) {
            composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-2049692012, i11, -1, "com.arflix.tv.ui.components.SkeletonCategoryRow.<anonymous>.<anonymous>.<anonymous>.<anonymous> (SkeletonLoader.kt:257)");
            }
            int i12 = WhenMappings.$EnumSwitchMapping$0[skeletonCardType.ordinal()];
            if (i12 == 1) {
                composer.startReplaceGroup(1635968264);
                m6186SkeletonPosterCardorJrPs(0.0f, null, composer, 0, 3);
                composer.endReplaceGroup();
            } else if (i12 == 2) {
                composer.startReplaceGroup(1635970535);
                m6185SkeletonMediaCardorJrPs(0.0f, null, composer, 0, 3);
                composer.endReplaceGroup();
            } else if (i12 == 3) {
                composer.startReplaceGroup(1635972841);
                SkeletonEpisodeCard(null, composer, 0, 1);
                composer.endReplaceGroup();
            } else {
                if (i12 != 4) {
                    composer.startReplaceGroup(1635966359);
                    composer.endReplaceGroup();
                    throw new NoWhenBranchMatchedException();
                }
                composer.startReplaceGroup(1635975110);
                SkeletonCastCard(null, composer, 0, 1);
                composer.endReplaceGroup();
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 SkeletonCategoryRow$lambda$1(int i10, SkeletonCardType skeletonCardType, boolean z, Modifier modifier, int i11, int i12, Composer composer, int i13) {
        SkeletonCategoryRow(i10, skeletonCardType, z, modifier, composer, RecomposeScopeImplKt.updateChangedFlags(i11 | 1), i12);
        return x6.t0.f22605a;
    }

    public static final void SkeletonDetailsHero(Modifier modifier, Composer composer, int i10, int i11) {
        Modifier modifier2;
        int i12;
        Modifier modifier3;
        Composer composerStartRestartGroup = composer.startRestartGroup(-407104489);
        int i13 = i11 & 1;
        if (i13 != 0) {
            i12 = i10 | 6;
            modifier2 = modifier;
        } else if ((i10 & 6) == 0) {
            modifier2 = modifier;
            i12 = (composerStartRestartGroup.changed(modifier2) ? 4 : 2) | i10;
        } else {
            modifier2 = modifier;
            i12 = i10;
        }
        if ((i12 & 3) == 2 && composerStartRestartGroup.getSkipping()) {
            composerStartRestartGroup.skipToGroupEnd();
            modifier3 = modifier2;
        } else {
            modifier3 = i13 != 0 ? Modifier.INSTANCE : modifier2;
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-407104489, i12, -1, "com.arflix.tv.ui.components.SkeletonDetailsHero (SkeletonLoader.kt:275)");
            }
            float f10 = 24;
            Modifier modifierM = com.arflix.tv.data.repository.g.m(f10, modifier3, composerStartRestartGroup, -483455358);
            Arrangement arrangement = Arrangement.INSTANCE;
            Arrangement.Vertical top = arrangement.getTop();
            Alignment.Companion companion = Alignment.INSTANCE;
            MeasurePolicy measurePolicyK = androidx.compose.foundation.c.k(companion, top, composerStartRestartGroup, 0, -1323940314);
            int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
            CompositionLocalMap currentCompositionLocalMap = composerStartRestartGroup.getCurrentCompositionLocalMap();
            ComposeUiNode.Companion companion2 = ComposeUiNode.INSTANCE;
            r7.a<ComposeUiNode> constructor = companion2.getConstructor();
            r7.q<SkippableUpdater<ComposeUiNode>, Composer, Integer, x6.t0> qVarModifierMaterializerOf = LayoutKt.modifierMaterializerOf(modifierM);
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
            r7.p pVarU = a0.c.u(companion2, composerM2991constructorimpl, measurePolicyK, composerM2991constructorimpl, currentCompositionLocalMap);
            if (composerM2991constructorimpl.getInserting() || !kotlin.jvm.internal.p.a(composerM2991constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                a0.c.w(currentCompositeKeyHash, composerM2991constructorimpl, currentCompositeKeyHash, pVarU);
            }
            a0.c.y(composerStartRestartGroup, qVarModifierMaterializerOf, composerStartRestartGroup, 0, 2058660585);
            ColumnScopeInstance columnScopeInstance = ColumnScopeInstance.INSTANCE;
            Modifier.Companion companion3 = Modifier.INSTANCE;
            float f11 = 60;
            SkeletonBox(SizeKt.m564height3ABfNKs(SizeKt.m583width3ABfNKs(companion3, Dp.m5678constructorimpl(300)), Dp.m5678constructorimpl(f11)), null, composerStartRestartGroup, 6, 2);
            float f12 = 16;
            com.arflix.tv.data.repository.g.p(f12, companion3, composerStartRestartGroup, 6);
            MeasurePolicy measurePolicyC = androidx.compose.foundation.c.C(companion, androidx.compose.foundation.c.g(10, arrangement, composerStartRestartGroup, 693286680), composerStartRestartGroup, 6, -1323940314);
            int currentCompositeKeyHash2 = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
            CompositionLocalMap currentCompositionLocalMap2 = composerStartRestartGroup.getCurrentCompositionLocalMap();
            r7.a<ComposeUiNode> constructor2 = companion2.getConstructor();
            r7.q<SkippableUpdater<ComposeUiNode>, Composer, Integer, x6.t0> qVarModifierMaterializerOf2 = LayoutKt.modifierMaterializerOf(companion3);
            if (composerStartRestartGroup.getApplier() == null) {
                ComposablesKt.invalidApplier();
            }
            composerStartRestartGroup.startReusableNode();
            if (composerStartRestartGroup.getInserting()) {
                composerStartRestartGroup.createNode(constructor2);
            } else {
                composerStartRestartGroup.useNode();
            }
            Composer composerM2991constructorimpl2 = Updater.m2991constructorimpl(composerStartRestartGroup);
            r7.p pVarU2 = a0.c.u(companion2, composerM2991constructorimpl2, measurePolicyC, composerM2991constructorimpl2, currentCompositionLocalMap2);
            if (composerM2991constructorimpl2.getInserting() || !kotlin.jvm.internal.p.a(composerM2991constructorimpl2.rememberedValue(), Integer.valueOf(currentCompositeKeyHash2))) {
                a0.c.w(currentCompositeKeyHash2, composerM2991constructorimpl2, currentCompositeKeyHash2, pVarU2);
            }
            a0.c.y(composerStartRestartGroup, qVarModifierMaterializerOf2, composerStartRestartGroup, 0, 2058660585);
            RowScopeInstance rowScopeInstance = RowScopeInstance.INSTANCE;
            composerStartRestartGroup.startReplaceGroup(1992252784);
            for (int i14 = 0; i14 < 4; i14++) {
                SkeletonBox(SizeKt.m564height3ABfNKs(SizeKt.m583width3ABfNKs(Modifier.INSTANCE, Dp.m5678constructorimpl(80)), Dp.m5678constructorimpl(28)), RoundedCornerShapeKt.m798RoundedCornerShape0680j_4(Dp.m5678constructorimpl(6)), composerStartRestartGroup, 6, 0);
            }
            composerStartRestartGroup.endReplaceGroup();
            composerStartRestartGroup.endReplaceableGroup();
            composerStartRestartGroup.endNode();
            composerStartRestartGroup.endReplaceableGroup();
            composerStartRestartGroup.endReplaceableGroup();
            Modifier.Companion companion4 = Modifier.INSTANCE;
            com.arflix.tv.data.repository.g.p(f12, companion4, composerStartRestartGroup, 6);
            SkeletonBox(SizeKt.m564height3ABfNKs(SizeKt.m583width3ABfNKs(companion4, Dp.m5678constructorimpl(500)), Dp.m5678constructorimpl(f11)), RoundedCornerShapeKt.m798RoundedCornerShape0680j_4(Dp.m5678constructorimpl(4)), composerStartRestartGroup, 6, 0);
            com.arflix.tv.data.repository.g.p(f10, companion4, composerStartRestartGroup, 6);
            MeasurePolicy measurePolicyC2 = androidx.compose.foundation.c.C(Alignment.INSTANCE, androidx.compose.foundation.c.g(12, Arrangement.INSTANCE, composerStartRestartGroup, 693286680), composerStartRestartGroup, 6, -1323940314);
            int currentCompositeKeyHash3 = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
            CompositionLocalMap currentCompositionLocalMap3 = composerStartRestartGroup.getCurrentCompositionLocalMap();
            ComposeUiNode.Companion companion5 = ComposeUiNode.INSTANCE;
            r7.a<ComposeUiNode> constructor3 = companion5.getConstructor();
            r7.q<SkippableUpdater<ComposeUiNode>, Composer, Integer, x6.t0> qVarModifierMaterializerOf3 = LayoutKt.modifierMaterializerOf(companion4);
            if (composerStartRestartGroup.getApplier() == null) {
                ComposablesKt.invalidApplier();
            }
            composerStartRestartGroup.startReusableNode();
            if (composerStartRestartGroup.getInserting()) {
                composerStartRestartGroup.createNode(constructor3);
            } else {
                composerStartRestartGroup.useNode();
            }
            Composer composerM2991constructorimpl3 = Updater.m2991constructorimpl(composerStartRestartGroup);
            r7.p pVarU3 = a0.c.u(companion5, composerM2991constructorimpl3, measurePolicyC2, composerM2991constructorimpl3, currentCompositionLocalMap3);
            if (composerM2991constructorimpl3.getInserting() || !kotlin.jvm.internal.p.a(composerM2991constructorimpl3.rememberedValue(), Integer.valueOf(currentCompositeKeyHash3))) {
                a0.c.w(currentCompositeKeyHash3, composerM2991constructorimpl3, currentCompositeKeyHash3, pVarU3);
            }
            a0.c.y(composerStartRestartGroup, qVarModifierMaterializerOf3, composerStartRestartGroup, 0, 2058660585);
            RowScopeInstance rowScopeInstance2 = RowScopeInstance.INSTANCE;
            composerStartRestartGroup.startReplaceGroup(616086938);
            for (int i15 = 0; i15 < 5; i15++) {
                SkeletonBox(SizeKt.m564height3ABfNKs(SizeKt.m583width3ABfNKs(Modifier.INSTANCE, Dp.m5678constructorimpl(100)), Dp.m5678constructorimpl(40)), RoundedCornerShapeKt.m798RoundedCornerShape0680j_4(Dp.m5678constructorimpl(8)), composerStartRestartGroup, 6, 0);
            }
            composerStartRestartGroup.endReplaceGroup();
            composerStartRestartGroup.endReplaceableGroup();
            composerStartRestartGroup.endNode();
            composerStartRestartGroup.endReplaceableGroup();
            composerStartRestartGroup.endReplaceableGroup();
            composerStartRestartGroup.endReplaceableGroup();
            composerStartRestartGroup.endNode();
            composerStartRestartGroup.endReplaceableGroup();
            composerStartRestartGroup.endReplaceableGroup();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new n1(modifier3, i10, i11, 2));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 SkeletonDetailsHero$lambda$1(Modifier modifier, int i10, int i11, Composer composer, int i12) {
        SkeletonDetailsHero(modifier, composer, RecomposeScopeImplKt.updateChangedFlags(i10 | 1), i11);
        return x6.t0.f22605a;
    }

    /* JADX WARN: Removed duplicated region for block: B:145:0x0483  */
    /* JADX WARN: Removed duplicated region for block: B:148:0x048f  */
    /* JADX WARN: Removed duplicated region for block: B:149:0x0493  */
    /* JADX WARN: Removed duplicated region for block: B:156:0x04b7  */
    /* JADX WARN: Removed duplicated region for block: B:159:0x04ca A[LOOP:1: B:158:0x04c8->B:159:0x04ca, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:163:0x0531  */
    /* JADX WARN: Removed duplicated region for block: B:166:0x053d  */
    /* JADX WARN: Removed duplicated region for block: B:167:0x0541  */
    /* JADX WARN: Removed duplicated region for block: B:170:0x0552  */
    /* JADX WARN: Removed duplicated region for block: B:174:0x0567  */
    /* JADX WARN: Removed duplicated region for block: B:178:0x05d2  */
    /* JADX WARN: Removed duplicated region for block: B:197:0x0687  */
    /* JADX WARN: Removed duplicated region for block: B:200:0x069b  */
    /* JADX WARN: Removed duplicated region for block: B:201:0x06b1  */
    /* JADX WARN: Removed duplicated region for block: B:203:0x06cb  */
    /* JADX WARN: Removed duplicated region for block: B:225:0x079a  */
    /* JADX WARN: Removed duplicated region for block: B:229:0x07a7  */
    /* JADX WARN: Removed duplicated region for block: B:234:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:41:0x0079  */
    /* JADX WARN: Removed duplicated region for block: B:50:0x0094  */
    /* JADX WARN: Removed duplicated region for block: B:51:0x0096  */
    /* JADX WARN: Removed duplicated region for block: B:54:0x009b  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x00ae  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x00bc  */
    /* JADX WARN: Removed duplicated region for block: B:62:0x00cc  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void SkeletonDetailsPage(boolean r34, boolean r35, androidx.compose.ui.Modifier r36, androidx.compose.runtime.Composer r37, int r38, int r39) {
        /*
            Method dump skipped, instruction units count: 1972
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.components.SkeletonLoaderKt.SkeletonDetailsPage(boolean, boolean, androidx.compose.ui.Modifier, androidx.compose.runtime.Composer, int, int):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 SkeletonDetailsPage$lambda$2(boolean z, boolean z5, Modifier modifier, int i10, int i11, Composer composer, int i12) {
        SkeletonDetailsPage(z, z5, modifier, composer, RecomposeScopeImplKt.updateChangedFlags(i10 | 1), i11);
        return x6.t0.f22605a;
    }

    public static final void SkeletonEpisodeCard(Modifier modifier, Composer composer, int i10, int i11) {
        int i12;
        Composer composerStartRestartGroup = composer.startRestartGroup(784937768);
        int i13 = i11 & 1;
        if (i13 != 0) {
            i12 = i10 | 6;
        } else if ((i10 & 6) == 0) {
            i12 = (composerStartRestartGroup.changed(modifier) ? 4 : 2) | i10;
        } else {
            i12 = i10;
        }
        if ((i12 & 3) == 2 && composerStartRestartGroup.getSkipping()) {
            composerStartRestartGroup.skipToGroupEnd();
        } else {
            if (i13 != 0) {
                modifier = Modifier.INSTANCE;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(784937768, i12, -1, "com.arflix.tv.ui.components.SkeletonEpisodeCard (SkeletonLoader.kt:191)");
            }
            Modifier modifierM583width3ABfNKs = SizeKt.m583width3ABfNKs(modifier, Dp.m5678constructorimpl(220));
            composerStartRestartGroup.startReplaceableGroup(-483455358);
            MeasurePolicy measurePolicyK = androidx.compose.foundation.c.k(Alignment.INSTANCE, Arrangement.INSTANCE.getTop(), composerStartRestartGroup, 0, -1323940314);
            int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
            CompositionLocalMap currentCompositionLocalMap = composerStartRestartGroup.getCurrentCompositionLocalMap();
            ComposeUiNode.Companion companion = ComposeUiNode.INSTANCE;
            r7.a<ComposeUiNode> constructor = companion.getConstructor();
            r7.q<SkippableUpdater<ComposeUiNode>, Composer, Integer, x6.t0> qVarModifierMaterializerOf = LayoutKt.modifierMaterializerOf(modifierM583width3ABfNKs);
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
            r7.p pVarU = a0.c.u(companion, composerM2991constructorimpl, measurePolicyK, composerM2991constructorimpl, currentCompositionLocalMap);
            if (composerM2991constructorimpl.getInserting() || !kotlin.jvm.internal.p.a(composerM2991constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                a0.c.w(currentCompositeKeyHash, composerM2991constructorimpl, currentCompositeKeyHash, pVarU);
            }
            a0.c.x(0, qVarModifierMaterializerOf, SkippableUpdater.m2980boximpl(SkippableUpdater.m2981constructorimpl(composerStartRestartGroup)), composerStartRestartGroup, 2058660585);
            ColumnScopeInstance columnScopeInstance = ColumnScopeInstance.INSTANCE;
            Modifier.Companion companion2 = Modifier.INSTANCE;
            SkeletonBox(AspectRatioKt.aspectRatio$default(SizeKt.fillMaxWidth$default(companion2, 0.0f, 1, null), 1.7777778f, false, 2, null), null, composerStartRestartGroup, 6, 2);
            com.arflix.tv.data.repository.g.p(8, companion2, composerStartRestartGroup, 6);
            float f10 = 4;
            SkeletonBox(SizeKt.m564height3ABfNKs(SizeKt.fillMaxWidth$default(companion2, 0.0f, 1, null), Dp.m5678constructorimpl(14)), RoundedCornerShapeKt.m798RoundedCornerShape0680j_4(Dp.m5678constructorimpl(f10)), composerStartRestartGroup, 6, 0);
            com.arflix.tv.data.repository.g.p(f10, companion2, composerStartRestartGroup, 6);
            SkeletonBox(SizeKt.m564height3ABfNKs(SizeKt.m583width3ABfNKs(companion2, Dp.m5678constructorimpl(80)), Dp.m5678constructorimpl(10)), RoundedCornerShapeKt.m798RoundedCornerShape0680j_4(Dp.m5678constructorimpl(f10)), composerStartRestartGroup, 6, 0);
            composerStartRestartGroup.endReplaceableGroup();
            composerStartRestartGroup.endNode();
            composerStartRestartGroup.endReplaceableGroup();
            composerStartRestartGroup.endReplaceableGroup();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new n1(modifier, i10, i11, 3));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 SkeletonEpisodeCard$lambda$1(Modifier modifier, int i10, int i11, Composer composer, int i12) {
        SkeletonEpisodeCard(modifier, composer, RecomposeScopeImplKt.updateChangedFlags(i10 | 1), i11);
        return x6.t0.f22605a;
    }

    /* JADX WARN: Removed duplicated region for block: B:30:0x005a  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x005c  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x005e  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x0061  */
    /* JADX WARN: Removed duplicated region for block: B:35:0x0065  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x006c  */
    /* JADX WARN: Removed duplicated region for block: B:42:0x00b5  */
    /* JADX WARN: Removed duplicated region for block: B:45:0x00c1  */
    /* JADX WARN: Removed duplicated region for block: B:46:0x00c5  */
    /* JADX WARN: Removed duplicated region for block: B:51:0x00e4  */
    /* JADX WARN: Removed duplicated region for block: B:54:0x0100  */
    /* JADX WARN: Removed duplicated region for block: B:61:0x012a  */
    /* JADX WARN: Removed duplicated region for block: B:65:0x0135  */
    /* JADX WARN: Removed duplicated region for block: B:68:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void SkeletonHomePage(int r17, androidx.compose.ui.Modifier r18, androidx.compose.runtime.Composer r19, int r20, int r21) {
        /*
            Method dump skipped, instruction units count: 324
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.components.SkeletonLoaderKt.SkeletonHomePage(int, androidx.compose.ui.Modifier, androidx.compose.runtime.Composer, int, int):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 SkeletonHomePage$lambda$1(int i10, Modifier modifier, int i11, int i12, Composer composer, int i13) {
        SkeletonHomePage(i10, modifier, composer, RecomposeScopeImplKt.updateChangedFlags(i11 | 1), i12);
        return x6.t0.f22605a;
    }

    /* JADX INFO: renamed from: SkeletonMediaCard--orJrPs, reason: not valid java name */
    public static final void m6185SkeletonMediaCardorJrPs(float f10, Modifier modifier, Composer composer, int i10, int i11) {
        int i12;
        Composer composerStartRestartGroup = composer.startRestartGroup(-264613407);
        int i13 = i11 & 1;
        if (i13 != 0) {
            i12 = i10 | 6;
        } else if ((i10 & 6) == 0) {
            i12 = (composerStartRestartGroup.changed(f10) ? 4 : 2) | i10;
        } else {
            i12 = i10;
        }
        int i14 = i11 & 2;
        if (i14 != 0) {
            i12 |= 48;
        } else if ((i10 & 48) == 0) {
            i12 |= composerStartRestartGroup.changed(modifier) ? 32 : 16;
        }
        if ((i12 & 19) == 18 && composerStartRestartGroup.getSkipping()) {
            composerStartRestartGroup.skipToGroupEnd();
        } else {
            if (i13 != 0) {
                f10 = Dp.m5678constructorimpl(220);
            }
            if (i14 != 0) {
                modifier = Modifier.INSTANCE;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-264613407, i12, -1, "com.arflix.tv.ui.components.SkeletonMediaCard (SkeletonLoader.kt:129)");
            }
            Modifier modifierM583width3ABfNKs = SizeKt.m583width3ABfNKs(modifier, f10);
            composerStartRestartGroup.startReplaceableGroup(-483455358);
            MeasurePolicy measurePolicyK = androidx.compose.foundation.c.k(Alignment.INSTANCE, Arrangement.INSTANCE.getTop(), composerStartRestartGroup, 0, -1323940314);
            int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
            CompositionLocalMap currentCompositionLocalMap = composerStartRestartGroup.getCurrentCompositionLocalMap();
            ComposeUiNode.Companion companion = ComposeUiNode.INSTANCE;
            r7.a<ComposeUiNode> constructor = companion.getConstructor();
            r7.q<SkippableUpdater<ComposeUiNode>, Composer, Integer, x6.t0> qVarModifierMaterializerOf = LayoutKt.modifierMaterializerOf(modifierM583width3ABfNKs);
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
            r7.p pVarU = a0.c.u(companion, composerM2991constructorimpl, measurePolicyK, composerM2991constructorimpl, currentCompositionLocalMap);
            if (composerM2991constructorimpl.getInserting() || !kotlin.jvm.internal.p.a(composerM2991constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                a0.c.w(currentCompositeKeyHash, composerM2991constructorimpl, currentCompositeKeyHash, pVarU);
            }
            a0.c.x(0, qVarModifierMaterializerOf, SkippableUpdater.m2980boximpl(SkippableUpdater.m2981constructorimpl(composerStartRestartGroup)), composerStartRestartGroup, 2058660585);
            ColumnScopeInstance columnScopeInstance = ColumnScopeInstance.INSTANCE;
            Modifier.Companion companion2 = Modifier.INSTANCE;
            SkeletonBox(AspectRatioKt.aspectRatio$default(SizeKt.fillMaxWidth$default(companion2, 0.0f, 1, null), 1.7777778f, false, 2, null), null, composerStartRestartGroup, 6, 2);
            com.arflix.tv.data.repository.g.p(8, companion2, composerStartRestartGroup, 6);
            float f11 = 4;
            SkeletonBox(SizeKt.m564height3ABfNKs(SizeKt.fillMaxWidth$default(companion2, 0.0f, 1, null), Dp.m5678constructorimpl(14)), RoundedCornerShapeKt.m798RoundedCornerShape0680j_4(Dp.m5678constructorimpl(f11)), composerStartRestartGroup, 6, 0);
            com.arflix.tv.data.repository.g.p(f11, companion2, composerStartRestartGroup, 6);
            SkeletonBox(SizeKt.m564height3ABfNKs(SizeKt.m583width3ABfNKs(companion2, Dp.m5678constructorimpl(60)), Dp.m5678constructorimpl(10)), RoundedCornerShapeKt.m798RoundedCornerShape0680j_4(Dp.m5678constructorimpl(f11)), composerStartRestartGroup, 6, 0);
            composerStartRestartGroup.endReplaceableGroup();
            composerStartRestartGroup.endNode();
            composerStartRestartGroup.endReplaceableGroup();
            composerStartRestartGroup.endReplaceableGroup();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        float f12 = f10;
        Modifier modifier2 = modifier;
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new s1(f12, modifier2, i10, i11, 1));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 SkeletonMediaCard__orJrPs$lambda$1(float f10, Modifier modifier, int i10, int i11, Composer composer, int i12) {
        m6185SkeletonMediaCardorJrPs(f10, modifier, composer, RecomposeScopeImplKt.updateChangedFlags(i10 | 1), i11);
        return x6.t0.f22605a;
    }

    /* JADX INFO: renamed from: SkeletonPosterCard--orJrPs, reason: not valid java name */
    public static final void m6186SkeletonPosterCardorJrPs(float f10, Modifier modifier, Composer composer, int i10, int i11) {
        int i12;
        Composer composerStartRestartGroup = composer.startRestartGroup(635654624);
        int i13 = i11 & 1;
        if (i13 != 0) {
            i12 = i10 | 6;
        } else if ((i10 & 6) == 0) {
            i12 = (composerStartRestartGroup.changed(f10) ? 4 : 2) | i10;
        } else {
            i12 = i10;
        }
        int i14 = i11 & 2;
        if (i14 != 0) {
            i12 |= 48;
        } else if ((i10 & 48) == 0) {
            i12 |= composerStartRestartGroup.changed(modifier) ? 32 : 16;
        }
        if ((i12 & 19) == 18 && composerStartRestartGroup.getSkipping()) {
            composerStartRestartGroup.skipToGroupEnd();
        } else {
            if (i13 != 0) {
                f10 = Dp.m5678constructorimpl(140);
            }
            if (i14 != 0) {
                modifier = Modifier.INSTANCE;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(635654624, i12, -1, "com.arflix.tv.ui.components.SkeletonPosterCard (SkeletonLoader.kt:98)");
            }
            Modifier modifierM583width3ABfNKs = SizeKt.m583width3ABfNKs(modifier, f10);
            composerStartRestartGroup.startReplaceableGroup(-483455358);
            MeasurePolicy measurePolicyK = androidx.compose.foundation.c.k(Alignment.INSTANCE, Arrangement.INSTANCE.getTop(), composerStartRestartGroup, 0, -1323940314);
            int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
            CompositionLocalMap currentCompositionLocalMap = composerStartRestartGroup.getCurrentCompositionLocalMap();
            ComposeUiNode.Companion companion = ComposeUiNode.INSTANCE;
            r7.a<ComposeUiNode> constructor = companion.getConstructor();
            r7.q<SkippableUpdater<ComposeUiNode>, Composer, Integer, x6.t0> qVarModifierMaterializerOf = LayoutKt.modifierMaterializerOf(modifierM583width3ABfNKs);
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
            r7.p pVarU = a0.c.u(companion, composerM2991constructorimpl, measurePolicyK, composerM2991constructorimpl, currentCompositionLocalMap);
            if (composerM2991constructorimpl.getInserting() || !kotlin.jvm.internal.p.a(composerM2991constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                a0.c.w(currentCompositeKeyHash, composerM2991constructorimpl, currentCompositeKeyHash, pVarU);
            }
            a0.c.x(0, qVarModifierMaterializerOf, SkippableUpdater.m2980boximpl(SkippableUpdater.m2981constructorimpl(composerStartRestartGroup)), composerStartRestartGroup, 2058660585);
            ColumnScopeInstance columnScopeInstance = ColumnScopeInstance.INSTANCE;
            Modifier.Companion companion2 = Modifier.INSTANCE;
            SkeletonBox(AspectRatioKt.aspectRatio$default(SizeKt.fillMaxWidth$default(companion2, 0.0f, 1, null), 0.6666667f, false, 2, null), null, composerStartRestartGroup, 6, 2);
            com.arflix.tv.data.repository.g.p(8, companion2, composerStartRestartGroup, 6);
            float f11 = 4;
            SkeletonBox(SizeKt.m564height3ABfNKs(SizeKt.fillMaxWidth$default(companion2, 0.0f, 1, null), Dp.m5678constructorimpl(16)), RoundedCornerShapeKt.m798RoundedCornerShape0680j_4(Dp.m5678constructorimpl(f11)), composerStartRestartGroup, 6, 0);
            com.arflix.tv.data.repository.g.p(f11, companion2, composerStartRestartGroup, 6);
            SkeletonBox(SizeKt.m564height3ABfNKs(SizeKt.m583width3ABfNKs(companion2, Dp.m5678constructorimpl(80)), Dp.m5678constructorimpl(12)), RoundedCornerShapeKt.m798RoundedCornerShape0680j_4(Dp.m5678constructorimpl(f11)), composerStartRestartGroup, 6, 0);
            composerStartRestartGroup.endReplaceableGroup();
            composerStartRestartGroup.endNode();
            composerStartRestartGroup.endReplaceableGroup();
            composerStartRestartGroup.endReplaceableGroup();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        float f12 = f10;
        Modifier modifier2 = modifier;
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new s1(f12, modifier2, i10, i11, 2));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 SkeletonPosterCard__orJrPs$lambda$1(float f10, Modifier modifier, int i10, int i11, Composer composer, int i12) {
        m6186SkeletonPosterCardorJrPs(f10, modifier, composer, RecomposeScopeImplKt.updateChangedFlags(i10 | 1), i11);
        return x6.t0.f22605a;
    }

    public static final Brush shimmerBrush(Composer composer, int i10) {
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1271198380, i10, -1, "com.arflix.tv.ui.components.shimmerBrush (SkeletonLoader.kt:74)");
        }
        Brush shimmerBrush = ShimmerState.INSTANCE.getShimmerBrush(composer, 0);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return shimmerBrush;
    }
}
