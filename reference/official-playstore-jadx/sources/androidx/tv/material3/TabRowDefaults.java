package androidx.tv.material3;

import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.foundation.layout.SpacerKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.unit.Dp;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0005\bÇ\u0002\u0018\u00002\u00020\u0001J\u000f\u0010\u0003\u001a\u00020\u0002H\u0007¢\u0006\u0004\b\u0003\u0010\u0004J\u0015\u0010\b\u001a\u00020\u0005H\u0007ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u0006\u0010\u0007J@\u0010\u0013\u001a\u00020\u00022\u0006\u0010\n\u001a\u00020\t2\u0006\u0010\f\u001a\u00020\u000b2\b\b\u0002\u0010\u000e\u001a\u00020\r2\b\b\u0002\u0010\u000f\u001a\u00020\u00052\b\b\u0002\u0010\u0010\u001a\u00020\u0005H\u0007ø\u0001\u0001¢\u0006\u0004\b\u0011\u0010\u0012J@\u0010\u0015\u001a\u00020\u00022\u0006\u0010\n\u001a\u00020\t2\u0006\u0010\f\u001a\u00020\u000b2\b\b\u0002\u0010\u000e\u001a\u00020\r2\b\b\u0002\u0010\u000f\u001a\u00020\u00052\b\b\u0002\u0010\u0010\u001a\u00020\u0005H\u0007ø\u0001\u0001¢\u0006\u0004\b\u0014\u0010\u0012\u0082\u0002\u000b\n\u0002\b!\n\u0005\b¡\u001e0\u0001¨\u0006\u001b²\u0006\f\u0010\u0017\u001a\u00020\u00168\nX\u008a\u0084\u0002²\u0006\f\u0010\u0018\u001a\u00020\u00168\nX\u008a\u0084\u0002²\u0006\f\u0010\u0019\u001a\u00020\u00058\nX\u008a\u0084\u0002²\u0006\f\u0010\u0017\u001a\u00020\u00168\nX\u008a\u0084\u0002²\u0006\f\u0010\u0018\u001a\u00020\u00168\nX\u008a\u0084\u0002²\u0006\f\u0010\u001a\u001a\u00020\u00058\nX\u008a\u0084\u0002"}, d2 = {"Landroidx/tv/material3/TabRowDefaults;", "", "Lx6/t0;", "TabSeparator", "(Landroidx/compose/runtime/Composer;I)V", "Landroidx/compose/ui/graphics/Color;", "contentColor-WaAFU9c", "(Landroidx/compose/runtime/Composer;I)J", "contentColor", "Landroidx/compose/ui/unit/DpRect;", "currentTabPosition", "", "doesTabRowHaveFocus", "Landroidx/compose/ui/Modifier;", "modifier", "activeColor", "inactiveColor", "PillIndicator-jA1GFJw", "(Landroidx/compose/ui/unit/DpRect;ZLandroidx/compose/ui/Modifier;JJLandroidx/compose/runtime/Composer;II)V", "PillIndicator", "UnderlinedIndicator-jA1GFJw", "UnderlinedIndicator", "Landroidx/compose/ui/unit/Dp;", "width", "leftOffset", "pillColor", "underlineColor", "tv-material_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class TabRowDefaults {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final TabRowDefaults f5765a = new TabRowDefaults();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final long f5766b = Color.INSTANCE.m3507getTransparent0d7_KjU();

    /* JADX WARN: Removed duplicated region for block: B:103:0x01e7  */
    /* JADX WARN: Removed duplicated region for block: B:105:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:36:0x0062  */
    /* JADX WARN: Removed duplicated region for block: B:44:0x0077  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x007d  */
    /* JADX WARN: Removed duplicated region for block: B:55:0x0092  */
    /* JADX WARN: Removed duplicated region for block: B:62:0x00a9  */
    /* JADX WARN: Removed duplicated region for block: B:75:0x00cd  */
    /* JADX WARN: Removed duplicated region for block: B:76:0x00cf  */
    /* JADX WARN: Removed duplicated region for block: B:77:0x00d2  */
    /* JADX WARN: Removed duplicated region for block: B:80:0x00da  */
    /* JADX WARN: Removed duplicated region for block: B:81:0x00e5  */
    /* JADX WARN: Removed duplicated region for block: B:84:0x00ea  */
    /* JADX WARN: Removed duplicated region for block: B:87:0x010e  */
    /* JADX WARN: Removed duplicated region for block: B:90:0x014b  */
    /* JADX WARN: Removed duplicated region for block: B:91:0x014d  */
    /* JADX WARN: Removed duplicated region for block: B:96:0x0194  */
    /* JADX WARN: Removed duplicated region for block: B:99:0x01da  */
    /* JADX INFO: renamed from: PillIndicator-jA1GFJw, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void m6016PillIndicatorjA1GFJw(androidx.compose.ui.unit.DpRect r25, boolean r26, androidx.compose.ui.Modifier r27, long r28, long r30, androidx.compose.runtime.Composer r32, int r33, int r34) {
        /*
            Method dump skipped, instruction units count: 502
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.tv.material3.TabRowDefaults.m6016PillIndicatorjA1GFJw(androidx.compose.ui.unit.DpRect, boolean, androidx.compose.ui.Modifier, long, long, androidx.compose.runtime.Composer, int, int):void");
    }

    public final void TabSeparator(Composer composer, int i10) {
        Composer composerStartRestartGroup = composer.startRestartGroup(-562414269);
        if ((i10 & 1) == 0 && composerStartRestartGroup.getSkipping()) {
            composerStartRestartGroup.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-562414269, i10, -1, "androidx.tv.material3.TabRowDefaults.TabSeparator (TabRow.kt:189)");
            }
            SpacerKt.Spacer(SizeKt.m583width3ABfNKs(Modifier.INSTANCE, Dp.m5678constructorimpl(8)), composerStartRestartGroup, 6);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new androidx.navigation.compose.j(this, i10, 2));
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:100:0x016a  */
    /* JADX WARN: Removed duplicated region for block: B:105:0x01a3  */
    /* JADX WARN: Removed duplicated region for block: B:108:0x01eb  */
    /* JADX WARN: Removed duplicated region for block: B:112:0x01f8  */
    /* JADX WARN: Removed duplicated region for block: B:114:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:36:0x0062  */
    /* JADX WARN: Removed duplicated region for block: B:44:0x0077  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x007d  */
    /* JADX WARN: Removed duplicated region for block: B:55:0x0092  */
    /* JADX WARN: Removed duplicated region for block: B:62:0x00a9  */
    /* JADX WARN: Removed duplicated region for block: B:75:0x00cd  */
    /* JADX WARN: Removed duplicated region for block: B:76:0x00cf  */
    /* JADX WARN: Removed duplicated region for block: B:77:0x00d2  */
    /* JADX WARN: Removed duplicated region for block: B:80:0x00da  */
    /* JADX WARN: Removed duplicated region for block: B:81:0x00e5  */
    /* JADX WARN: Removed duplicated region for block: B:84:0x00ea  */
    /* JADX WARN: Removed duplicated region for block: B:87:0x00fd  */
    /* JADX WARN: Removed duplicated region for block: B:90:0x0111  */
    /* JADX WARN: Removed duplicated region for block: B:91:0x011f  */
    /* JADX WARN: Removed duplicated region for block: B:94:0x012e  */
    /* JADX WARN: Removed duplicated region for block: B:96:0x0134  */
    /* JADX WARN: Removed duplicated region for block: B:99:0x0168  */
    /* JADX INFO: renamed from: UnderlinedIndicator-jA1GFJw, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void m6017UnderlinedIndicatorjA1GFJw(androidx.compose.ui.unit.DpRect r22, boolean r23, androidx.compose.ui.Modifier r24, long r25, long r27, androidx.compose.runtime.Composer r29, int r30, int r31) {
        /*
            Method dump skipped, instruction units count: 519
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.tv.material3.TabRowDefaults.m6017UnderlinedIndicatorjA1GFJw(androidx.compose.ui.unit.DpRect, boolean, androidx.compose.ui.Modifier, long, long, androidx.compose.runtime.Composer, int, int):void");
    }

    /* JADX INFO: renamed from: contentColor-WaAFU9c, reason: not valid java name */
    public final long m6018contentColorWaAFU9c(Composer composer, int i10) {
        composer.startReplaceableGroup(-817149238);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-817149238, i10, -1, "androidx.tv.material3.TabRowDefaults.contentColor (TabRow.kt:194)");
        }
        long jN = MaterialTheme.f5670a.getColorScheme(composer, 6).n();
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        composer.endReplaceableGroup();
        return jN;
    }
}
