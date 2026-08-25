package androidx.tv.foundation.lazy.list;

import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.MutableIntState;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.saveable.Saver;
import androidx.compose.runtime.snapshots.Snapshot;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000r\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a\u009a\u0001\u0010\u001b\u001a\u00020\u00182\u0006\u0010\u0001\u001a\u00020\u00002\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\u00062\b\b\u0002\u0010\u000b\u001a\u00020\n2\u0006\u0010\r\u001a\u00020\f2\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u000e2\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u00102\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u00122\n\b\u0002\u0010\u0015\u001a\u0004\u0018\u00010\u00142\u0017\u0010\u001a\u001a\u0013\u0012\u0004\u0012\u00020\u0017\u0012\u0004\u0012\u00020\u00180\u0016¢\u0006\u0002\b\u0019H\u0001¢\u0006\u0004\b\u001b\u0010\u001c\u001a%\u0010 \u001a\u00020\u00182\f\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\u001e0\u001d2\u0006\u0010\u0003\u001a\u00020\u0002H\u0003¢\u0006\u0004\b \u0010!\u001a\u008c\u0001\u0010&\u001a\u0019\u0012\u0004\u0012\u00020#\u0012\u0004\u0012\u00020$\u0012\u0004\u0012\u00020%0\"¢\u0006\u0002\b\u00192\f\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\u001e0\u001d2\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\u00062\u0006\u0010\u000b\u001a\u00020\n2\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u000e2\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u00122\n\b\u0002\u0010\u0015\u001a\u0004\u0018\u00010\u00142\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u0010H\u0003¢\u0006\u0004\b&\u0010'¨\u0006("}, d2 = {"Landroidx/compose/ui/Modifier;", "modifier", "Landroidx/tv/foundation/lazy/list/m0;", "state", "Landroidx/compose/foundation/layout/PaddingValues;", "contentPadding", "", "reverseLayout", "isVertical", "userScrollEnabled", "", "beyondBoundsItemCount", "Lh2/b;", "pivotOffsets", "Landroidx/compose/ui/Alignment$Horizontal;", "horizontalAlignment", "Landroidx/compose/foundation/layout/Arrangement$Vertical;", "verticalArrangement", "Landroidx/compose/ui/Alignment$Vertical;", "verticalAlignment", "Landroidx/compose/foundation/layout/Arrangement$Horizontal;", "horizontalArrangement", "Lkotlin/Function1;", "Landroidx/tv/foundation/lazy/list/g0;", "Lx6/t0;", "Lx6/n;", "content", "LazyList", "(Landroidx/compose/ui/Modifier;Landroidx/tv/foundation/lazy/list/m0;Landroidx/compose/foundation/layout/PaddingValues;ZZZILh2/b;Landroidx/compose/ui/Alignment$Horizontal;Landroidx/compose/foundation/layout/Arrangement$Vertical;Landroidx/compose/ui/Alignment$Vertical;Landroidx/compose/foundation/layout/Arrangement$Horizontal;Lr7/l;Landroidx/compose/runtime/Composer;III)V", "Lkotlin/Function0;", "Landroidx/tv/foundation/lazy/list/l;", "itemProviderLambda", "ScrollPositionUpdater", "(Lr7/a;Landroidx/tv/foundation/lazy/list/m0;Landroidx/compose/runtime/Composer;I)V", "Lkotlin/Function2;", "Landroidx/compose/foundation/lazy/layout/LazyLayoutMeasureScope;", "Landroidx/compose/ui/unit/Constraints;", "Landroidx/compose/ui/layout/MeasureResult;", "rememberLazyListMeasurePolicy", "(Lr7/a;Landroidx/tv/foundation/lazy/list/m0;Landroidx/compose/foundation/layout/PaddingValues;ZZILandroidx/compose/ui/Alignment$Horizontal;Landroidx/compose/ui/Alignment$Vertical;Landroidx/compose/foundation/layout/Arrangement$Horizontal;Landroidx/compose/foundation/layout/Arrangement$Vertical;Landroidx/compose/runtime/Composer;II)Lr7/p;", "tv-foundation_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class LazyListKt {
    /* JADX WARN: Removed duplicated region for block: B:100:0x011a  */
    /* JADX WARN: Removed duplicated region for block: B:109:0x0133  */
    /* JADX WARN: Removed duplicated region for block: B:110:0x013a  */
    /* JADX WARN: Removed duplicated region for block: B:120:0x015a  */
    /* JADX WARN: Removed duplicated region for block: B:122:0x0161  */
    /* JADX WARN: Removed duplicated region for block: B:132:0x0180  */
    /* JADX WARN: Removed duplicated region for block: B:133:0x0183  */
    /* JADX WARN: Removed duplicated region for block: B:141:0x019a  */
    /* JADX WARN: Removed duplicated region for block: B:147:0x01b6  */
    /* JADX WARN: Removed duplicated region for block: B:148:0x01b8  */
    /* JADX WARN: Removed duplicated region for block: B:149:0x01bb  */
    /* JADX WARN: Removed duplicated region for block: B:152:0x01bf  */
    /* JADX WARN: Removed duplicated region for block: B:153:0x01c1  */
    /* JADX WARN: Removed duplicated region for block: B:155:0x01c5  */
    /* JADX WARN: Removed duplicated region for block: B:156:0x01c7  */
    /* JADX WARN: Removed duplicated region for block: B:158:0x01cb  */
    /* JADX WARN: Removed duplicated region for block: B:159:0x01ce  */
    /* JADX WARN: Removed duplicated region for block: B:162:0x01d3  */
    /* JADX WARN: Removed duplicated region for block: B:165:0x01dd  */
    /* JADX WARN: Removed duplicated region for block: B:168:0x020e  */
    /* JADX WARN: Removed duplicated region for block: B:171:0x027d  */
    /* JADX WARN: Removed duplicated region for block: B:172:0x0280  */
    /* JADX WARN: Removed duplicated region for block: B:175:0x02f1  */
    /* JADX WARN: Removed duplicated region for block: B:179:0x0305  */
    /* JADX WARN: Removed duplicated region for block: B:181:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:36:0x006b  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x006e  */
    /* JADX WARN: Removed duplicated region for block: B:46:0x0082  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x0085  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x009b  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x00a0  */
    /* JADX WARN: Removed duplicated region for block: B:67:0x00b9  */
    /* JADX WARN: Removed duplicated region for block: B:68:0x00be  */
    /* JADX WARN: Removed duplicated region for block: B:77:0x00d7  */
    /* JADX WARN: Removed duplicated region for block: B:79:0x00dc  */
    /* JADX WARN: Removed duplicated region for block: B:88:0x00f5  */
    /* JADX WARN: Removed duplicated region for block: B:89:0x00fa  */
    /* JADX WARN: Removed duplicated region for block: B:98:0x0113  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void LazyList(androidx.compose.ui.Modifier r28, androidx.tv.foundation.lazy.list.m0 r29, androidx.compose.foundation.layout.PaddingValues r30, boolean r31, boolean r32, boolean r33, int r34, h2.b r35, androidx.compose.ui.Alignment.Horizontal r36, androidx.compose.foundation.layout.Arrangement.Vertical r37, androidx.compose.ui.Alignment.Vertical r38, androidx.compose.foundation.layout.Arrangement.Horizontal r39, r7.l<? super androidx.tv.foundation.lazy.list.g0, x6.t0> r40, androidx.compose.runtime.Composer r41, int r42, int r43, int r44) {
        /*
            Method dump skipped, instruction units count: 805
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.tv.foundation.lazy.list.LazyListKt.LazyList(androidx.compose.ui.Modifier, androidx.tv.foundation.lazy.list.m0, androidx.compose.foundation.layout.PaddingValues, boolean, boolean, boolean, int, h2.b, androidx.compose.ui.Alignment$Horizontal, androidx.compose.foundation.layout.Arrangement$Vertical, androidx.compose.ui.Alignment$Vertical, androidx.compose.foundation.layout.Arrangement$Horizontal, r7.l, androidx.compose.runtime.Composer, int, int, int):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void ScrollPositionUpdater(r7.a<? extends l> aVar, m0 m0Var, Composer composer, int i10) {
        int i11;
        int index;
        Composer composerStartRestartGroup = composer.startRestartGroup(797498966);
        if ((i10 & 6) == 0) {
            i11 = (composerStartRestartGroup.changedInstance(aVar) ? 4 : 2) | i10;
        } else {
            i11 = i10;
        }
        if ((i10 & 48) == 0) {
            i11 |= composerStartRestartGroup.changed(m0Var) ? 32 : 16;
        }
        if ((i11 & 19) == 18 && composerStartRestartGroup.getSkipping()) {
            composerStartRestartGroup.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(797498966, i11, -1, "androidx.tv.foundation.lazy.list.ScrollPositionUpdater (LazyList.kt:152)");
            }
            l lVar = (l) aVar.invoke();
            if (lVar.getItemCount() > 0) {
                Saver saver = m0.z;
                Snapshot snapshotCreateNonObservableSnapshot = Snapshot.INSTANCE.createNonObservableSnapshot();
                try {
                    Snapshot snapshotMakeCurrent = snapshotCreateNonObservableSnapshot.makeCurrent();
                    try {
                        int intValue = ((MutableIntState) m0Var.f5429c.f5232b).getIntValue();
                        snapshotCreateNonObservableSnapshot.dispose();
                        androidx.tv.foundation.lazy.grid.f0 f0Var = m0Var.f5429c;
                        Object obj = f0Var.f5235e;
                        if (obj == null || ((intValue < lVar.getItemCount() && obj.equals(lVar.getKey(intValue))) || (index = lVar.getIndex(obj)) == -1)) {
                            index = intValue;
                        }
                        if (intValue != index) {
                            ((MutableIntState) f0Var.f5232b).setIntValue(index);
                            ((i2.l) f0Var.f5236f).a(intValue);
                        }
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
            scopeUpdateScopeEndRestartGroup.updateScope(new androidx.navigation.compose.n(i10, 3, aVar, m0Var));
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:53:0x00a1 A[PHI: r3
      0x00a1: PHI (r3v18 boolean) = (r3v16 boolean), (r3v19 boolean) binds: [B:52:0x009f, B:48:0x0098] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:90:0x0111  */
    /* JADX WARN: Removed duplicated region for block: B:93:0x012c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static final r7.p<androidx.compose.foundation.lazy.layout.LazyLayoutMeasureScope, androidx.compose.ui.unit.Constraints, androidx.compose.ui.layout.MeasureResult> rememberLazyListMeasurePolicy(r7.a<? extends androidx.tv.foundation.lazy.list.l> r17, androidx.tv.foundation.lazy.list.m0 r18, androidx.compose.foundation.layout.PaddingValues r19, boolean r20, boolean r21, int r22, androidx.compose.ui.Alignment.Horizontal r23, androidx.compose.ui.Alignment.Vertical r24, androidx.compose.foundation.layout.Arrangement.Horizontal r25, androidx.compose.foundation.layout.Arrangement.Vertical r26, androidx.compose.runtime.Composer r27, int r28, int r29) {
        /*
            Method dump skipped, instruction units count: 307
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.tv.foundation.lazy.list.LazyListKt.rememberLazyListMeasurePolicy(r7.a, androidx.tv.foundation.lazy.list.m0, androidx.compose.foundation.layout.PaddingValues, boolean, boolean, int, androidx.compose.ui.Alignment$Horizontal, androidx.compose.ui.Alignment$Vertical, androidx.compose.foundation.layout.Arrangement$Horizontal, androidx.compose.foundation.layout.Arrangement$Vertical, androidx.compose.runtime.Composer, int, int):r7.p");
    }
}
