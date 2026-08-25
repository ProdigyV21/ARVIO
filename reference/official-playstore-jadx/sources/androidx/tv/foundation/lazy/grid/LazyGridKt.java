package androidx.tv.foundation.lazy.grid;

import androidx.compose.foundation.gestures.Orientation;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.MutableIntState;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.saveable.Saver;
import androidx.compose.runtime.snapshots.Snapshot;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.platform.CompositionLocalsKt;
import androidx.compose.ui.unit.LayoutDirection;
import androidx.media3.exoplayer.RendererCapabilities;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000r\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u001a\u0095\u0001\u0010\u001a\u001a\u00020\u00182\b\b\u0002\u0010\u0001\u001a\u00020\u00002\u0006\u0010\u0003\u001a\u00020\u00022\u001d\u0010\t\u001a\u0019\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0004¢\u0006\u0002\b\b2\b\b\u0002\u0010\u000b\u001a\u00020\n2\b\b\u0002\u0010\r\u001a\u00020\f2\u0006\u0010\u000e\u001a\u00020\f2\u0006\u0010\u000f\u001a\u00020\f2\u0006\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u0013\u001a\u00020\u00122\u0006\u0010\u0015\u001a\u00020\u00142\u0017\u0010\u0019\u001a\u0013\u0012\u0004\u0012\u00020\u0017\u0012\u0004\u0012\u00020\u00180\u0016¢\u0006\u0002\b\bH\u0001¢\u0006\u0004\b\u001a\u0010\u001b\u001a%\u0010\u001f\u001a\u00020\u00182\f\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u001d0\u001c2\u0006\u0010\u0003\u001a\u00020\u0002H\u0003¢\u0006\u0004\b\u001f\u0010 \u001a\u008b\u0001\u0010#\u001a\u0019\u0012\u0004\u0012\u00020!\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\"0\u0004¢\u0006\u0002\b\b2\f\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u001d0\u001c2\u0006\u0010\u0003\u001a\u00020\u00022\u001d\u0010\t\u001a\u0019\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0004¢\u0006\u0002\b\b2\u0006\u0010\u000b\u001a\u00020\n2\u0006\u0010\r\u001a\u00020\f2\u0006\u0010\u000e\u001a\u00020\f2\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u00122\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u0010H\u0003¢\u0006\u0004\b#\u0010$\u001a+\u0010'\u001a\u00020\u0000*\u00020\u00002\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\r\u001a\u00020\f2\u0006\u0010&\u001a\u00020%H\u0001¢\u0006\u0004\b'\u0010(¨\u0006)"}, d2 = {"Landroidx/compose/ui/Modifier;", "modifier", "Landroidx/tv/foundation/lazy/grid/b1;", "state", "Lkotlin/Function2;", "Landroidx/compose/ui/unit/Density;", "Landroidx/compose/ui/unit/Constraints;", "Landroidx/tv/foundation/lazy/grid/g0;", "Lx6/n;", "slots", "Landroidx/compose/foundation/layout/PaddingValues;", "contentPadding", "", "reverseLayout", "isVertical", "userScrollEnabled", "Landroidx/compose/foundation/layout/Arrangement$Vertical;", "verticalArrangement", "Landroidx/compose/foundation/layout/Arrangement$Horizontal;", "horizontalArrangement", "Lh2/b;", "pivotOffsets", "Lkotlin/Function1;", "Landroidx/tv/foundation/lazy/grid/v0;", "Lx6/t0;", "content", "LazyGrid", "(Landroidx/compose/ui/Modifier;Landroidx/tv/foundation/lazy/grid/b1;Lr7/p;Landroidx/compose/foundation/layout/PaddingValues;ZZZLandroidx/compose/foundation/layout/Arrangement$Vertical;Landroidx/compose/foundation/layout/Arrangement$Horizontal;Lh2/b;Lr7/l;Landroidx/compose/runtime/Composer;III)V", "Lkotlin/Function0;", "Landroidx/tv/foundation/lazy/grid/p;", "itemProviderLambda", "ScrollPositionUpdater", "(Lr7/a;Landroidx/tv/foundation/lazy/grid/b1;Landroidx/compose/runtime/Composer;I)V", "Landroidx/compose/foundation/lazy/layout/LazyLayoutMeasureScope;", "Landroidx/compose/ui/layout/MeasureResult;", "rememberLazyGridMeasurePolicy", "(Lr7/a;Landroidx/tv/foundation/lazy/grid/b1;Lr7/p;Landroidx/compose/foundation/layout/PaddingValues;ZZLandroidx/compose/foundation/layout/Arrangement$Horizontal;Landroidx/compose/foundation/layout/Arrangement$Vertical;Landroidx/compose/runtime/Composer;II)Lr7/p;", "Landroidx/compose/foundation/gestures/Orientation;", "orientation", "lazyGridBeyondBoundsModifier", "(Landroidx/compose/ui/Modifier;Landroidx/tv/foundation/lazy/grid/b1;ZLandroidx/compose/foundation/gestures/Orientation;Landroidx/compose/runtime/Composer;I)Landroidx/compose/ui/Modifier;", "tv-foundation_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class LazyGridKt {
    /* JADX WARN: Removed duplicated region for block: B:101:0x0118  */
    /* JADX WARN: Removed duplicated region for block: B:103:0x011d  */
    /* JADX WARN: Removed duplicated region for block: B:112:0x0134  */
    /* JADX WARN: Removed duplicated region for block: B:113:0x0137  */
    /* JADX WARN: Removed duplicated region for block: B:123:0x0155  */
    /* JADX WARN: Removed duplicated region for block: B:129:0x016d  */
    /* JADX WARN: Removed duplicated region for block: B:130:0x016f  */
    /* JADX WARN: Removed duplicated region for block: B:131:0x0172  */
    /* JADX WARN: Removed duplicated region for block: B:134:0x0176  */
    /* JADX WARN: Removed duplicated region for block: B:135:0x0180  */
    /* JADX WARN: Removed duplicated region for block: B:138:0x0184  */
    /* JADX WARN: Removed duplicated region for block: B:141:0x018c  */
    /* JADX WARN: Removed duplicated region for block: B:144:0x01e2  */
    /* JADX WARN: Removed duplicated region for block: B:145:0x01e5  */
    /* JADX WARN: Removed duplicated region for block: B:148:0x024e  */
    /* JADX WARN: Removed duplicated region for block: B:152:0x025c  */
    /* JADX WARN: Removed duplicated region for block: B:154:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:36:0x0067  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x006c  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x0082  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x0087  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x00a0  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x00a3  */
    /* JADX WARN: Removed duplicated region for block: B:68:0x00ba  */
    /* JADX WARN: Removed duplicated region for block: B:70:0x00c1  */
    /* JADX WARN: Removed duplicated region for block: B:79:0x00dc  */
    /* JADX WARN: Removed duplicated region for block: B:81:0x00e1  */
    /* JADX WARN: Removed duplicated region for block: B:90:0x00fa  */
    /* JADX WARN: Removed duplicated region for block: B:92:0x00ff  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void LazyGrid(androidx.compose.ui.Modifier r21, androidx.tv.foundation.lazy.grid.b1 r22, r7.p<? super androidx.compose.ui.unit.Density, ? super androidx.compose.ui.unit.Constraints, androidx.tv.foundation.lazy.grid.g0> r23, androidx.compose.foundation.layout.PaddingValues r24, boolean r25, boolean r26, boolean r27, androidx.compose.foundation.layout.Arrangement.Vertical r28, androidx.compose.foundation.layout.Arrangement.Horizontal r29, h2.b r30, r7.l<? super androidx.tv.foundation.lazy.grid.v0, x6.t0> r31, androidx.compose.runtime.Composer r32, int r33, int r34, int r35) {
        /*
            Method dump skipped, instruction units count: 632
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.tv.foundation.lazy.grid.LazyGridKt.LazyGrid(androidx.compose.ui.Modifier, androidx.tv.foundation.lazy.grid.b1, r7.p, androidx.compose.foundation.layout.PaddingValues, boolean, boolean, boolean, androidx.compose.foundation.layout.Arrangement$Vertical, androidx.compose.foundation.layout.Arrangement$Horizontal, h2.b, r7.l, androidx.compose.runtime.Composer, int, int, int):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void ScrollPositionUpdater(r7.a<? extends p> aVar, b1 b1Var, Composer composer, int i10) {
        int i11;
        Composer composerStartRestartGroup = composer.startRestartGroup(1939048366);
        int i12 = 2;
        if ((i10 & 6) == 0) {
            i11 = (composerStartRestartGroup.changedInstance(aVar) ? 4 : 2) | i10;
        } else {
            i11 = i10;
        }
        if ((i10 & 48) == 0) {
            i11 |= composerStartRestartGroup.changed(b1Var) ? 32 : 16;
        }
        if ((i11 & 19) == 18 && composerStartRestartGroup.getSkipping()) {
            composerStartRestartGroup.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1939048366, i11, -1, "androidx.tv.foundation.lazy.grid.ScrollPositionUpdater (LazyGrid.kt:142)");
            }
            p pVar = (p) aVar.invoke();
            if (pVar.getItemCount() > 0) {
                Saver saver = b1.x;
                Snapshot snapshotCreateNonObservableSnapshot = Snapshot.INSTANCE.createNonObservableSnapshot();
                try {
                    Snapshot snapshotMakeCurrent = snapshotCreateNonObservableSnapshot.makeCurrent();
                    try {
                        int intValue = ((MutableIntState) b1Var.f5153a.f5232b).getIntValue();
                        snapshotCreateNonObservableSnapshot.dispose();
                        b1Var.b(pVar, intValue);
                    } finally {
                        snapshotCreateNonObservableSnapshot.restoreCurrent(snapshotMakeCurrent);
                    }
                } catch (Throwable th) {
                    snapshotCreateNonObservableSnapshot.dispose();
                    throw th;
                }
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new androidx.navigation.compose.n(i10, i12, aVar, b1Var));
        }
    }

    public static final Modifier lazyGridBeyondBoundsModifier(Modifier modifier, b1 b1Var, boolean z, Orientation orientation, Composer composer, int i10) {
        composer.startReplaceableGroup(-793044332);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-793044332, i10, -1, "androidx.tv.foundation.lazy.grid.lazyGridBeyondBoundsModifier (LazyGrid.kt:400)");
        }
        LayoutDirection layoutDirection = (LayoutDirection) composer.consume(CompositionLocalsKt.getLocalLayoutDirection());
        composer.startReplaceableGroup(-1567214677);
        int i11 = (i10 & 112) ^ 48;
        boolean z5 = (i11 > 32 && composer.changed(b1Var)) || (i10 & 48) == 32;
        Object objRememberedValue = composer.rememberedValue();
        if (z5 || objRememberedValue == Composer.INSTANCE.getEmpty()) {
            objRememberedValue = new d(b1Var);
            composer.updateRememberedValue(objRememberedValue);
        }
        d dVar = (d) objRememberedValue;
        composer.endReplaceableGroup();
        composer.startReplaceableGroup(-1567211784);
        boolean zChanged = ((i11 > 32 && composer.changed(b1Var)) || (i10 & 48) == 32) | composer.changed(dVar) | ((((i10 & 896) ^ RendererCapabilities.DECODER_SUPPORT_MASK) > 256 && composer.changed(z)) || (i10 & RendererCapabilities.DECODER_SUPPORT_MASK) == 256) | composer.changed(layoutDirection) | ((((i10 & 7168) ^ 3072) > 2048 && composer.changed(orientation)) || (i10 & 3072) == 2048);
        Object objRememberedValue2 = composer.rememberedValue();
        if (zChanged || objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
            Object gVar = new androidx.tv.foundation.lazy.list.g(dVar, b1Var.f5170s, z, layoutDirection, orientation);
            composer.updateRememberedValue(gVar);
            objRememberedValue2 = gVar;
        }
        composer.endReplaceableGroup();
        Modifier modifierThen = modifier.then((androidx.tv.foundation.lazy.list.g) objRememberedValue2);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        composer.endReplaceableGroup();
        return modifierThen;
    }

    /* JADX WARN: Removed duplicated region for block: B:53:0x00aa A[PHI: r3
      0x00aa: PHI (r3v24 boolean) = (r3v22 boolean), (r3v25 boolean) binds: [B:52:0x00a8, B:48:0x00a2] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:74:0x00ea  */
    /* JADX WARN: Removed duplicated region for block: B:77:0x0100  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static final r7.p<androidx.compose.foundation.lazy.layout.LazyLayoutMeasureScope, androidx.compose.ui.unit.Constraints, androidx.compose.ui.layout.MeasureResult> rememberLazyGridMeasurePolicy(r7.a<? extends androidx.tv.foundation.lazy.grid.p> r15, androidx.tv.foundation.lazy.grid.b1 r16, r7.p<? super androidx.compose.ui.unit.Density, ? super androidx.compose.ui.unit.Constraints, androidx.tv.foundation.lazy.grid.g0> r17, androidx.compose.foundation.layout.PaddingValues r18, boolean r19, boolean r20, androidx.compose.foundation.layout.Arrangement.Horizontal r21, androidx.compose.foundation.layout.Arrangement.Vertical r22, androidx.compose.runtime.Composer r23, int r24, int r25) {
        /*
            Method dump skipped, instruction units count: 263
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.tv.foundation.lazy.grid.LazyGridKt.rememberLazyGridMeasurePolicy(r7.a, androidx.tv.foundation.lazy.grid.b1, r7.p, androidx.compose.foundation.layout.PaddingValues, boolean, boolean, androidx.compose.foundation.layout.Arrangement$Horizontal, androidx.compose.foundation.layout.Arrangement$Vertical, androidx.compose.runtime.Composer, int, int):r7.p");
    }
}
