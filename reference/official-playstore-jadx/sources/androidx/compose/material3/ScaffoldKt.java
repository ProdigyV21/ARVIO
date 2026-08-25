package androidx.compose.material3;

import androidx.compose.foundation.layout.PaddingValues;
import androidx.compose.foundation.layout.WindowInsets;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalKt;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.ProvidableCompositionLocal;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.SnapshotStateKt__SnapshotStateKt;
import androidx.compose.ui.layout.SubcomposeLayoutKt;
import androidx.compose.ui.unit.Dp;
import androidx.media3.exoplayer.RendererCapabilities;
import androidx.media3.exoplayer.source.ProgressiveMediaSource;
import kotlin.Metadata;
import r7.p;
import r7.q;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0010\u000b\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a±\u0001\u0010\u0015\u001a\u00020\u00032\b\b\u0002\u0010\u0001\u001a\u00020\u00002\u0013\b\u0002\u0010\u0005\u001a\r\u0012\u0004\u0012\u00020\u00030\u0002¢\u0006\u0002\b\u00042\u0013\b\u0002\u0010\u0006\u001a\r\u0012\u0004\u0012\u00020\u00030\u0002¢\u0006\u0002\b\u00042\u0013\b\u0002\u0010\u0007\u001a\r\u0012\u0004\u0012\u00020\u00030\u0002¢\u0006\u0002\b\u00042\u0013\b\u0002\u0010\b\u001a\r\u0012\u0004\u0012\u00020\u00030\u0002¢\u0006\u0002\b\u00042\b\b\u0002\u0010\n\u001a\u00020\t2\b\b\u0002\u0010\f\u001a\u00020\u000b2\b\b\u0002\u0010\r\u001a\u00020\u000b2\b\b\u0002\u0010\u000f\u001a\u00020\u000e2\u0017\u0010\u0012\u001a\u0013\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\u00030\u0010¢\u0006\u0002\b\u0004H\u0007ø\u0001\u0000¢\u0006\u0004\b\u0013\u0010\u0014\u001a\u0087\u0001\u0010\u001b\u001a\u00020\u00032\u0006\u0010\u0016\u001a\u00020\t2\u0011\u0010\u0005\u001a\r\u0012\u0004\u0012\u00020\u00030\u0002¢\u0006\u0002\b\u00042\u0017\u0010\u0012\u001a\u0013\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\u00030\u0010¢\u0006\u0002\b\u00042\u0011\u0010\u0017\u001a\r\u0012\u0004\u0012\u00020\u00030\u0002¢\u0006\u0002\b\u00042\u0011\u0010\u0018\u001a\r\u0012\u0004\u0012\u00020\u00030\u0002¢\u0006\u0002\b\u00042\u0006\u0010\u000f\u001a\u00020\u000e2\u0011\u0010\u0006\u001a\r\u0012\u0004\u0012\u00020\u00030\u0002¢\u0006\u0002\b\u0004H\u0003ø\u0001\u0000¢\u0006\u0004\b\u0019\u0010\u001a\u001a\u0087\u0001\u0010\u001d\u001a\u00020\u00032\u0006\u0010\u0016\u001a\u00020\t2\u0011\u0010\u0005\u001a\r\u0012\u0004\u0012\u00020\u00030\u0002¢\u0006\u0002\b\u00042\u0017\u0010\u0012\u001a\u0013\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\u00030\u0010¢\u0006\u0002\b\u00042\u0011\u0010\u0017\u001a\r\u0012\u0004\u0012\u00020\u00030\u0002¢\u0006\u0002\b\u00042\u0011\u0010\u0018\u001a\r\u0012\u0004\u0012\u00020\u00030\u0002¢\u0006\u0002\b\u00042\u0006\u0010\u000f\u001a\u00020\u000e2\u0011\u0010\u0006\u001a\r\u0012\u0004\u0012\u00020\u00030\u0002¢\u0006\u0002\b\u0004H\u0003ø\u0001\u0000¢\u0006\u0004\b\u001c\u0010\u001a\u001a\u0087\u0001\u0010\u001f\u001a\u00020\u00032\u0006\u0010\u0016\u001a\u00020\t2\u0011\u0010\u0005\u001a\r\u0012\u0004\u0012\u00020\u00030\u0002¢\u0006\u0002\b\u00042\u0017\u0010\u0012\u001a\u0013\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\u00030\u0010¢\u0006\u0002\b\u00042\u0011\u0010\u0017\u001a\r\u0012\u0004\u0012\u00020\u00030\u0002¢\u0006\u0002\b\u00042\u0011\u0010\u0018\u001a\r\u0012\u0004\u0012\u00020\u00030\u0002¢\u0006\u0002\b\u00042\u0006\u0010\u000f\u001a\u00020\u000e2\u0011\u0010\u0006\u001a\r\u0012\u0004\u0012\u00020\u00030\u0002¢\u0006\u0002\b\u0004H\u0003ø\u0001\u0000¢\u0006\u0004\b\u001e\u0010\u001a\"1\u0010*\u001a\u00020 2\u0006\u0010!\u001a\u00020 8G@GX\u0087\u008e\u0002¢\u0006\u0018\n\u0004\b\"\u0010#\u0012\u0004\b(\u0010)\u001a\u0004\b$\u0010%\"\u0004\b&\u0010'\"\"\u0010-\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010,0+8\u0000X\u0080\u0004¢\u0006\f\n\u0004\b-\u0010.\u001a\u0004\b/\u00100\"\u0014\u00102\u001a\u0002018\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b2\u00103\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u00064"}, d2 = {"Landroidx/compose/ui/Modifier;", "modifier", "Lkotlin/Function0;", "Lx6/t0;", "Landroidx/compose/runtime/Composable;", "topBar", "bottomBar", "snackbarHost", "floatingActionButton", "Landroidx/compose/material3/FabPosition;", "floatingActionButtonPosition", "Landroidx/compose/ui/graphics/Color;", "containerColor", "contentColor", "Landroidx/compose/foundation/layout/WindowInsets;", "contentWindowInsets", "Lkotlin/Function1;", "Landroidx/compose/foundation/layout/PaddingValues;", "content", "Scaffold-TvnljyQ", "(Landroidx/compose/ui/Modifier;Lr7/p;Lr7/p;Lr7/p;Lr7/p;IJJLandroidx/compose/foundation/layout/WindowInsets;Lr7/q;Landroidx/compose/runtime/Composer;II)V", "Scaffold", "fabPosition", "snackbar", "fab", "ScaffoldLayout-FMILGgc", "(ILr7/p;Lr7/q;Lr7/p;Lr7/p;Landroidx/compose/foundation/layout/WindowInsets;Lr7/p;Landroidx/compose/runtime/Composer;I)V", "ScaffoldLayout", "ScaffoldLayoutWithMeasureFix-FMILGgc", "ScaffoldLayoutWithMeasureFix", "LegacyScaffoldLayout-FMILGgc", "LegacyScaffoldLayout", "", "<set-?>", "ScaffoldSubcomposeInMeasureFix$delegate", "Landroidx/compose/runtime/MutableState;", "getScaffoldSubcomposeInMeasureFix", "()Z", "setScaffoldSubcomposeInMeasureFix", "(Z)V", "getScaffoldSubcomposeInMeasureFix$annotations", "()V", "ScaffoldSubcomposeInMeasureFix", "Landroidx/compose/runtime/ProvidableCompositionLocal;", "Landroidx/compose/material3/FabPlacement;", "LocalFabPlacement", "Landroidx/compose/runtime/ProvidableCompositionLocal;", "getLocalFabPlacement", "()Landroidx/compose/runtime/ProvidableCompositionLocal;", "Landroidx/compose/ui/unit/Dp;", "FabSpacing", "F", "material3_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class ScaffoldKt {
    private static final MutableState ScaffoldSubcomposeInMeasureFix$delegate = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(Boolean.TRUE, null, 2, null);
    private static final ProvidableCompositionLocal<FabPlacement> LocalFabPlacement = CompositionLocalKt.staticCompositionLocalOf(ScaffoldKt$LocalFabPlacement$1.INSTANCE);
    private static final float FabSpacing = Dp.m5678constructorimpl(16);

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: LegacyScaffoldLayout-FMILGgc, reason: not valid java name */
    public static final void m1797LegacyScaffoldLayoutFMILGgc(int i10, p<? super Composer, ? super Integer, t0> pVar, q<? super PaddingValues, ? super Composer, ? super Integer, t0> qVar, p<? super Composer, ? super Integer, t0> pVar2, p<? super Composer, ? super Integer, t0> pVar3, WindowInsets windowInsets, p<? super Composer, ? super Integer, t0> pVar4, Composer composer, int i11) {
        int i12;
        q<? super PaddingValues, ? super Composer, ? super Integer, t0> qVar2;
        p<? super Composer, ? super Integer, t0> pVar5;
        WindowInsets windowInsets2;
        int i13;
        Composer composerStartRestartGroup = composer.startRestartGroup(1307205667);
        if ((i11 & 6) == 0) {
            i12 = (composerStartRestartGroup.changed(i10) ? 4 : 2) | i11;
        } else {
            i12 = i11;
        }
        if ((i11 & 48) == 0) {
            i12 |= composerStartRestartGroup.changedInstance(pVar) ? 32 : 16;
        }
        if ((i11 & RendererCapabilities.DECODER_SUPPORT_MASK) == 0) {
            qVar2 = qVar;
            i12 |= composerStartRestartGroup.changedInstance(qVar2) ? 256 : 128;
        } else {
            qVar2 = qVar;
        }
        if ((i11 & 3072) == 0) {
            i12 |= composerStartRestartGroup.changedInstance(pVar2) ? 2048 : 1024;
        }
        if ((i11 & 24576) == 0) {
            pVar5 = pVar3;
            i12 |= composerStartRestartGroup.changedInstance(pVar5) ? 16384 : 8192;
        } else {
            pVar5 = pVar3;
        }
        if ((196608 & i11) == 0) {
            windowInsets2 = windowInsets;
            i12 |= composerStartRestartGroup.changed(windowInsets2) ? 131072 : 65536;
        } else {
            windowInsets2 = windowInsets;
        }
        if ((i11 & 1572864) == 0) {
            i12 |= composerStartRestartGroup.changedInstance(pVar4) ? ProgressiveMediaSource.DEFAULT_LOADING_CHECK_INTERVAL_BYTES : 524288;
        }
        if ((i12 & 599187) == 599186 && composerStartRestartGroup.getSkipping()) {
            composerStartRestartGroup.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1307205667, i12, -1, "androidx.compose.material3.LegacyScaffoldLayout (Scaffold.kt:347)");
            }
            composerStartRestartGroup.startReplaceableGroup(1646578117);
            boolean z = ((i12 & 112) == 32) | ((i12 & 7168) == 2048) | ((458752 & i12) == 131072) | ((57344 & i12) == 16384) | ((i12 & 14) == 4) | ((3670016 & i12) == 1048576) | ((i12 & 896) == 256);
            Object objRememberedValue = composerStartRestartGroup.rememberedValue();
            if (z || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                i13 = 1;
                ScaffoldKt$LegacyScaffoldLayout$1$1 scaffoldKt$LegacyScaffoldLayout$1$1 = new ScaffoldKt$LegacyScaffoldLayout$1$1(pVar, pVar2, pVar5, i10, windowInsets2, pVar4, qVar2);
                composerStartRestartGroup.updateRememberedValue(scaffoldKt$LegacyScaffoldLayout$1$1);
                objRememberedValue = scaffoldKt$LegacyScaffoldLayout$1$1;
            } else {
                i13 = 1;
            }
            composerStartRestartGroup.endReplaceableGroup();
            SubcomposeLayoutKt.SubcomposeLayout(null, (p) objRememberedValue, composerStartRestartGroup, 0, i13);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new ScaffoldKt$LegacyScaffoldLayout$2(i10, pVar, qVar, pVar2, pVar3, windowInsets, pVar4, i11));
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:100:0x0116  */
    /* JADX WARN: Removed duplicated region for block: B:102:0x011b  */
    /* JADX WARN: Removed duplicated region for block: B:111:0x0138  */
    /* JADX WARN: Removed duplicated region for block: B:115:0x0154  */
    /* JADX WARN: Removed duplicated region for block: B:130:0x0190  */
    /* JADX WARN: Removed duplicated region for block: B:131:0x0192  */
    /* JADX WARN: Removed duplicated region for block: B:132:0x0195  */
    /* JADX WARN: Removed duplicated region for block: B:134:0x0199  */
    /* JADX WARN: Removed duplicated region for block: B:135:0x01a0  */
    /* JADX WARN: Removed duplicated region for block: B:137:0x01a3  */
    /* JADX WARN: Removed duplicated region for block: B:138:0x01aa  */
    /* JADX WARN: Removed duplicated region for block: B:140:0x01ad  */
    /* JADX WARN: Removed duplicated region for block: B:141:0x01b4  */
    /* JADX WARN: Removed duplicated region for block: B:143:0x01b7  */
    /* JADX WARN: Removed duplicated region for block: B:144:0x01be  */
    /* JADX WARN: Removed duplicated region for block: B:146:0x01c1  */
    /* JADX WARN: Removed duplicated region for block: B:147:0x01c8  */
    /* JADX WARN: Removed duplicated region for block: B:150:0x01cf  */
    /* JADX WARN: Removed duplicated region for block: B:151:0x01e1  */
    /* JADX WARN: Removed duplicated region for block: B:154:0x01ea  */
    /* JADX WARN: Removed duplicated region for block: B:155:0x01f5  */
    /* JADX WARN: Removed duplicated region for block: B:158:0x01fb  */
    /* JADX WARN: Removed duplicated region for block: B:160:0x0207  */
    /* JADX WARN: Removed duplicated region for block: B:163:0x0213  */
    /* JADX WARN: Removed duplicated region for block: B:164:0x021f  */
    /* JADX WARN: Removed duplicated region for block: B:175:0x0243  */
    /* JADX WARN: Removed duplicated region for block: B:177:0x024b  */
    /* JADX WARN: Removed duplicated region for block: B:188:0x027c  */
    /* JADX WARN: Removed duplicated region for block: B:190:0x0284  */
    /* JADX WARN: Removed duplicated region for block: B:193:0x02d4  */
    /* JADX WARN: Removed duplicated region for block: B:197:0x02e7  */
    /* JADX WARN: Removed duplicated region for block: B:199:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:26:0x004a  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x004f  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x0065  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x006a  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x0080  */
    /* JADX WARN: Removed duplicated region for block: B:50:0x0085  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x009d  */
    /* JADX WARN: Removed duplicated region for block: B:60:0x00a2  */
    /* JADX WARN: Removed duplicated region for block: B:69:0x00bb  */
    /* JADX WARN: Removed duplicated region for block: B:76:0x00d1  */
    /* JADX WARN: Removed duplicated region for block: B:79:0x00db  */
    /* JADX WARN: Removed duplicated region for block: B:86:0x00ee  */
    /* JADX WARN: Removed duplicated region for block: B:89:0x00f8  */
    /* JADX WARN: Removed duplicated region for block: B:97:0x010e  */
    /* JADX INFO: renamed from: Scaffold-TvnljyQ, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void m1798ScaffoldTvnljyQ(androidx.compose.ui.Modifier r28, r7.p<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, x6.t0> r29, r7.p<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, x6.t0> r30, r7.p<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, x6.t0> r31, r7.p<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, x6.t0> r32, int r33, long r34, long r36, androidx.compose.foundation.layout.WindowInsets r38, r7.q<? super androidx.compose.foundation.layout.PaddingValues, ? super androidx.compose.runtime.Composer, ? super java.lang.Integer, x6.t0> r39, androidx.compose.runtime.Composer r40, int r41, int r42) {
        /*
            Method dump skipped, instruction units count: 754
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material3.ScaffoldKt.m1798ScaffoldTvnljyQ(androidx.compose.ui.Modifier, r7.p, r7.p, r7.p, r7.p, int, long, long, androidx.compose.foundation.layout.WindowInsets, r7.q, androidx.compose.runtime.Composer, int, int):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: ScaffoldLayout-FMILGgc, reason: not valid java name */
    public static final void m1799ScaffoldLayoutFMILGgc(int i10, p<? super Composer, ? super Integer, t0> pVar, q<? super PaddingValues, ? super Composer, ? super Integer, t0> qVar, p<? super Composer, ? super Integer, t0> pVar2, p<? super Composer, ? super Integer, t0> pVar3, WindowInsets windowInsets, p<? super Composer, ? super Integer, t0> pVar4, Composer composer, int i11) {
        int i12;
        Composer composer2;
        Composer composerStartRestartGroup = composer.startRestartGroup(-975511942);
        if ((i11 & 6) == 0) {
            i12 = (composerStartRestartGroup.changed(i10) ? 4 : 2) | i11;
        } else {
            i12 = i11;
        }
        if ((i11 & 48) == 0) {
            i12 |= composerStartRestartGroup.changedInstance(pVar) ? 32 : 16;
        }
        if ((i11 & RendererCapabilities.DECODER_SUPPORT_MASK) == 0) {
            i12 |= composerStartRestartGroup.changedInstance(qVar) ? 256 : 128;
        }
        if ((i11 & 3072) == 0) {
            i12 |= composerStartRestartGroup.changedInstance(pVar2) ? 2048 : 1024;
        }
        if ((i11 & 24576) == 0) {
            i12 |= composerStartRestartGroup.changedInstance(pVar3) ? 16384 : 8192;
        }
        if ((196608 & i11) == 0) {
            i12 |= composerStartRestartGroup.changed(windowInsets) ? 131072 : 65536;
        }
        if ((1572864 & i11) == 0) {
            i12 |= composerStartRestartGroup.changedInstance(pVar4) ? ProgressiveMediaSource.DEFAULT_LOADING_CHECK_INTERVAL_BYTES : 524288;
        }
        if ((599187 & i12) == 599186 && composerStartRestartGroup.getSkipping()) {
            composerStartRestartGroup.skipToGroupEnd();
            composer2 = composerStartRestartGroup;
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-975511942, i12, -1, "androidx.compose.material3.ScaffoldLayout (Scaffold.kt:141)");
            }
            if (getScaffoldSubcomposeInMeasureFix()) {
                composerStartRestartGroup.startReplaceableGroup(-915303637);
                composer2 = composerStartRestartGroup;
                m1800ScaffoldLayoutWithMeasureFixFMILGgc(i10, pVar, qVar, pVar2, pVar3, windowInsets, pVar4, composer2, i12 & 4194302);
                composer2.endReplaceableGroup();
            } else {
                composerStartRestartGroup.startReplaceableGroup(-915303332);
                composer2 = composerStartRestartGroup;
                m1797LegacyScaffoldLayoutFMILGgc(i10, pVar, qVar, pVar2, pVar3, windowInsets, pVar4, composer2, i12 & 4194302);
                composer2.endReplaceableGroup();
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new ScaffoldKt$ScaffoldLayout$1(i10, pVar, qVar, pVar2, pVar3, windowInsets, pVar4, i11));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: ScaffoldLayoutWithMeasureFix-FMILGgc, reason: not valid java name */
    public static final void m1800ScaffoldLayoutWithMeasureFixFMILGgc(int i10, p<? super Composer, ? super Integer, t0> pVar, q<? super PaddingValues, ? super Composer, ? super Integer, t0> qVar, p<? super Composer, ? super Integer, t0> pVar2, p<? super Composer, ? super Integer, t0> pVar3, WindowInsets windowInsets, p<? super Composer, ? super Integer, t0> pVar4, Composer composer, int i11) {
        int i12;
        q<? super PaddingValues, ? super Composer, ? super Integer, t0> qVar2;
        p<? super Composer, ? super Integer, t0> pVar5;
        WindowInsets windowInsets2;
        int i13;
        Composer composerStartRestartGroup = composer.startRestartGroup(-2037614249);
        if ((i11 & 6) == 0) {
            i12 = (composerStartRestartGroup.changed(i10) ? 4 : 2) | i11;
        } else {
            i12 = i11;
        }
        if ((i11 & 48) == 0) {
            i12 |= composerStartRestartGroup.changedInstance(pVar) ? 32 : 16;
        }
        if ((i11 & RendererCapabilities.DECODER_SUPPORT_MASK) == 0) {
            qVar2 = qVar;
            i12 |= composerStartRestartGroup.changedInstance(qVar2) ? 256 : 128;
        } else {
            qVar2 = qVar;
        }
        if ((i11 & 3072) == 0) {
            i12 |= composerStartRestartGroup.changedInstance(pVar2) ? 2048 : 1024;
        }
        if ((i11 & 24576) == 0) {
            pVar5 = pVar3;
            i12 |= composerStartRestartGroup.changedInstance(pVar5) ? 16384 : 8192;
        } else {
            pVar5 = pVar3;
        }
        if ((196608 & i11) == 0) {
            windowInsets2 = windowInsets;
            i12 |= composerStartRestartGroup.changed(windowInsets2) ? 131072 : 65536;
        } else {
            windowInsets2 = windowInsets;
        }
        if ((i11 & 1572864) == 0) {
            i12 |= composerStartRestartGroup.changedInstance(pVar4) ? ProgressiveMediaSource.DEFAULT_LOADING_CHECK_INTERVAL_BYTES : 524288;
        }
        if ((i12 & 599187) == 599186 && composerStartRestartGroup.getSkipping()) {
            composerStartRestartGroup.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-2037614249, i12, -1, "androidx.compose.material3.ScaffoldLayoutWithMeasureFix (Scaffold.kt:177)");
            }
            composerStartRestartGroup.startReplaceableGroup(-273325894);
            boolean z = ((i12 & 112) == 32) | ((i12 & 7168) == 2048) | ((458752 & i12) == 131072) | ((57344 & i12) == 16384) | ((i12 & 14) == 4) | ((3670016 & i12) == 1048576) | ((i12 & 896) == 256);
            Object objRememberedValue = composerStartRestartGroup.rememberedValue();
            if (z || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                i13 = 1;
                ScaffoldKt$ScaffoldLayoutWithMeasureFix$1$1 scaffoldKt$ScaffoldLayoutWithMeasureFix$1$1 = new ScaffoldKt$ScaffoldLayoutWithMeasureFix$1$1(pVar, pVar2, pVar5, i10, windowInsets2, pVar4, qVar2);
                composerStartRestartGroup.updateRememberedValue(scaffoldKt$ScaffoldLayoutWithMeasureFix$1$1);
                objRememberedValue = scaffoldKt$ScaffoldLayoutWithMeasureFix$1$1;
            } else {
                i13 = 1;
            }
            composerStartRestartGroup.endReplaceableGroup();
            SubcomposeLayoutKt.SubcomposeLayout(null, (p) objRememberedValue, composerStartRestartGroup, 0, i13);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new ScaffoldKt$ScaffoldLayoutWithMeasureFix$2(i10, pVar, qVar, pVar2, pVar3, windowInsets, pVar4, i11));
        }
    }

    public static final ProvidableCompositionLocal<FabPlacement> getLocalFabPlacement() {
        return LocalFabPlacement;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static final boolean getScaffoldSubcomposeInMeasureFix() {
        return ((Boolean) ScaffoldSubcomposeInMeasureFix$delegate.getValue()).booleanValue();
    }

    public static /* synthetic */ void getScaffoldSubcomposeInMeasureFix$annotations() {
    }

    public static final void setScaffoldSubcomposeInMeasureFix(boolean z) {
        ScaffoldSubcomposeInMeasureFix$delegate.setValue(Boolean.valueOf(z));
    }
}
