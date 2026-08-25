package androidx.tv.material3;

import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.saveable.RememberSaveableKt;
import androidx.compose.runtime.saveable.Saver;
import io.ktor.http.ContentDisposition;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u001ad\u0010\u000f\u001a\u00020\u00032\"\u0010\u0006\u001a\u001e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0000¢\u0006\u0002\b\u0004¢\u0006\u0002\b\u00052\b\b\u0002\u0010\b\u001a\u00020\u00072\b\b\u0002\u0010\n\u001a\u00020\t2\b\b\u0002\u0010\f\u001a\u00020\u000b2\u0011\u0010\u000e\u001a\r\u0012\u0004\u0012\u00020\u00030\r¢\u0006\u0002\b\u0004H\u0007¢\u0006\u0004\b\u000f\u0010\u0010\u001aZ\u0010\u0011\u001a\u00020\u00032\"\u0010\u0006\u001a\u001e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0000¢\u0006\u0002\b\u0004¢\u0006\u0002\b\u00052\b\b\u0002\u0010\b\u001a\u00020\u00072\b\b\u0002\u0010\n\u001a\u00020\t2\u0011\u0010\u000e\u001a\r\u0012\u0004\u0012\u00020\u00030\r¢\u0006\u0002\b\u0004H\u0007¢\u0006\u0004\b\u0011\u0010\u0012\u001a\u0017\u0010\u0014\u001a\u00020\t2\u0006\u0010\u0013\u001a\u00020\u0002H\u0007¢\u0006\u0004\b\u0014\u0010\u0015\u001a\u0083\u0001\u0010\u001b\u001a\u00020\u00032\b\b\u0002\u0010\b\u001a\u00020\u00072\b\b\u0002\u0010\n\u001a\u00020\t2:\b\u0002\u0010\u001a\u001a4\u0012\u0013\u0012\u00110\u0016¢\u0006\f\b\u0017\u0012\b\b\u0018\u0012\u0004\b\b(\u0013\u0012\u0013\u0012\u00110\u0016¢\u0006\f\b\u0017\u0012\b\b\u0018\u0012\u0004\b\b(\u0019\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u00002\"\u0010\u000e\u001a\u001e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0000¢\u0006\u0002\b\u0004¢\u0006\u0002\b\u0005H\u0003¢\u0006\u0004\b\u001b\u0010\u001c¨\u0006!²\u0006\u000e\u0010\u001e\u001a\u00020\u001d8\n@\nX\u008a\u008e\u0002²\u0006\u0010\u0010 \u001a\u0004\u0018\u00010\u001f8\n@\nX\u008a\u008e\u0002"}, d2 = {"Lkotlin/Function2;", "Landroidx/tv/material3/o2;", "Landroidx/tv/material3/q1;", "Lx6/t0;", "Landroidx/compose/runtime/Composable;", "Lx6/n;", "drawerContent", "Landroidx/compose/ui/Modifier;", "modifier", "Landroidx/tv/material3/p1;", "drawerState", "Landroidx/compose/ui/graphics/Brush;", "scrimBrush", "Lkotlin/Function0;", "content", "ModalNavigationDrawer", "(Lr7/r;Landroidx/compose/ui/Modifier;Landroidx/tv/material3/p1;Landroidx/compose/ui/graphics/Brush;Lr7/p;Landroidx/compose/runtime/Composer;II)V", "NavigationDrawer", "(Lr7/r;Landroidx/compose/ui/Modifier;Landroidx/tv/material3/p1;Lr7/p;Landroidx/compose/runtime/Composer;II)V", "initialValue", "rememberDrawerState", "(Landroidx/tv/material3/q1;Landroidx/compose/runtime/Composer;I)Landroidx/tv/material3/p1;", "Landroidx/compose/ui/unit/IntSize;", "Lx6/y;", ContentDisposition.Parameters.Name, "targetValue", "sizeAnimationFinishedListener", "DrawerSheet", "(Landroidx/compose/ui/Modifier;Landroidx/tv/material3/p1;Lr7/p;Lr7/r;Landroidx/compose/runtime/Composer;II)V", "", "initializationComplete", "Landroidx/compose/ui/focus/FocusState;", "focusState", "tv-material_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class NavigationDrawerKt {
    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:102:0x01cd  */
    /* JADX WARN: Removed duplicated region for block: B:103:0x01d1  */
    /* JADX WARN: Removed duplicated region for block: B:108:0x01f0  */
    /* JADX WARN: Removed duplicated region for block: B:111:0x0213  */
    /* JADX WARN: Removed duplicated region for block: B:112:0x0215  */
    /* JADX WARN: Removed duplicated region for block: B:115:0x023d  */
    /* JADX WARN: Removed duplicated region for block: B:119:0x0248  */
    /* JADX WARN: Removed duplicated region for block: B:121:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:26:0x004c  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x0051  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x0067  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x006a  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x0080  */
    /* JADX WARN: Removed duplicated region for block: B:51:0x008e  */
    /* JADX WARN: Removed duplicated region for block: B:52:0x0090  */
    /* JADX WARN: Removed duplicated region for block: B:53:0x0093  */
    /* JADX WARN: Removed duplicated region for block: B:55:0x0096  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x00b9  */
    /* JADX WARN: Removed duplicated region for block: B:62:0x00bd  */
    /* JADX WARN: Removed duplicated region for block: B:63:0x00bf  */
    /* JADX WARN: Removed duplicated region for block: B:66:0x00c6  */
    /* JADX WARN: Removed duplicated region for block: B:69:0x00de  */
    /* JADX WARN: Removed duplicated region for block: B:72:0x00f8  */
    /* JADX WARN: Removed duplicated region for block: B:75:0x010f  */
    /* JADX WARN: Removed duplicated region for block: B:78:0x0127  */
    /* JADX WARN: Removed duplicated region for block: B:79:0x012a  */
    /* JADX WARN: Removed duplicated region for block: B:86:0x013e  */
    /* JADX WARN: Removed duplicated region for block: B:89:0x0174  */
    /* JADX WARN: Removed duplicated region for block: B:90:0x0176  */
    /* JADX WARN: Removed duplicated region for block: B:95:0x0183  */
    /* JADX WARN: Removed duplicated region for block: B:99:0x01c1  */
    /* JADX WARN: Type inference failed for: r12v11 */
    /* JADX WARN: Type inference failed for: r12v12, types: [boolean] */
    /* JADX WARN: Type inference failed for: r12v13 */
    /* JADX WARN: Type inference failed for: r12v15 */
    /* JADX WARN: Type inference failed for: r12v8 */
    /* JADX WARN: Type inference failed for: r12v9 */
    /* JADX WARN: Type inference failed for: r9v1 */
    /* JADX WARN: Type inference failed for: r9v2, types: [boolean, int] */
    /* JADX WARN: Type inference failed for: r9v4 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void DrawerSheet(androidx.compose.ui.Modifier r19, androidx.tv.material3.p1 r20, r7.p<? super androidx.compose.ui.unit.IntSize, ? super androidx.compose.ui.unit.IntSize, x6.t0> r21, r7.r<? super androidx.tv.material3.o2, ? super androidx.tv.material3.q1, ? super androidx.compose.runtime.Composer, ? super java.lang.Integer, x6.t0> r22, androidx.compose.runtime.Composer r23, int r24, int r25) {
        /*
            Method dump skipped, instruction units count: 597
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.tv.material3.NavigationDrawerKt.DrawerSheet(androidx.compose.ui.Modifier, androidx.tv.material3.p1, r7.p, r7.r, androidx.compose.runtime.Composer, int, int):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:102:0x0170  */
    /* JADX WARN: Removed duplicated region for block: B:104:0x0176  */
    /* JADX WARN: Removed duplicated region for block: B:108:0x01bb  */
    /* JADX WARN: Removed duplicated region for block: B:111:0x01c7  */
    /* JADX WARN: Removed duplicated region for block: B:112:0x01cb  */
    /* JADX WARN: Removed duplicated region for block: B:117:0x01ea  */
    /* JADX WARN: Removed duplicated region for block: B:128:0x0228  */
    /* JADX WARN: Removed duplicated region for block: B:130:0x022e  */
    /* JADX WARN: Removed duplicated region for block: B:133:0x0268  */
    /* JADX WARN: Removed duplicated region for block: B:149:0x02bb  */
    /* JADX WARN: Removed duplicated region for block: B:153:0x02c8  */
    /* JADX WARN: Removed duplicated region for block: B:155:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:26:0x004a  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x005e  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x0064  */
    /* JADX WARN: Removed duplicated region for block: B:45:0x0079  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x007f  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x0082  */
    /* JADX WARN: Removed duplicated region for block: B:62:0x00a7  */
    /* JADX WARN: Removed duplicated region for block: B:75:0x00cb  */
    /* JADX WARN: Removed duplicated region for block: B:76:0x00cd  */
    /* JADX WARN: Removed duplicated region for block: B:77:0x00d0  */
    /* JADX WARN: Removed duplicated region for block: B:80:0x00d5  */
    /* JADX WARN: Removed duplicated region for block: B:81:0x00de  */
    /* JADX WARN: Removed duplicated region for block: B:84:0x00e3  */
    /* JADX WARN: Removed duplicated region for block: B:85:0x0109  */
    /* JADX WARN: Removed duplicated region for block: B:88:0x0114  */
    /* JADX WARN: Removed duplicated region for block: B:91:0x0136  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void ModalNavigationDrawer(r7.r<? super androidx.tv.material3.o2, ? super androidx.tv.material3.q1, ? super androidx.compose.runtime.Composer, ? super java.lang.Integer, x6.t0> r24, androidx.compose.ui.Modifier r25, androidx.tv.material3.p1 r26, androidx.compose.ui.graphics.Brush r27, r7.p<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, x6.t0> r28, androidx.compose.runtime.Composer r29, int r30, int r31) {
        /*
            Method dump skipped, instruction units count: 727
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.tv.material3.NavigationDrawerKt.ModalNavigationDrawer(r7.r, androidx.compose.ui.Modifier, androidx.tv.material3.p1, androidx.compose.ui.graphics.Brush, r7.p, androidx.compose.runtime.Composer, int, int):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:26:0x0047  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x005c  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x0062  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x0065  */
    /* JADX WARN: Removed duplicated region for block: B:51:0x0089  */
    /* JADX WARN: Removed duplicated region for block: B:61:0x00a3  */
    /* JADX WARN: Removed duplicated region for block: B:62:0x00a5  */
    /* JADX WARN: Removed duplicated region for block: B:63:0x00a8  */
    /* JADX WARN: Removed duplicated region for block: B:66:0x00ad  */
    /* JADX WARN: Removed duplicated region for block: B:69:0x00c0  */
    /* JADX WARN: Removed duplicated region for block: B:73:0x00f7  */
    /* JADX WARN: Removed duplicated region for block: B:76:0x0103  */
    /* JADX WARN: Removed duplicated region for block: B:77:0x0107  */
    /* JADX WARN: Removed duplicated region for block: B:80:0x0118  */
    /* JADX WARN: Removed duplicated region for block: B:82:0x0126  */
    /* JADX WARN: Removed duplicated region for block: B:85:0x0164  */
    /* JADX WARN: Removed duplicated region for block: B:89:0x016e  */
    /* JADX WARN: Removed duplicated region for block: B:91:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void NavigationDrawer(r7.r<? super androidx.tv.material3.o2, ? super androidx.tv.material3.q1, ? super androidx.compose.runtime.Composer, ? super java.lang.Integer, x6.t0> r16, androidx.compose.ui.Modifier r17, androidx.tv.material3.p1 r18, r7.p<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, x6.t0> r19, androidx.compose.runtime.Composer r20, int r21, int r22) {
        /*
            Method dump skipped, instruction units count: 379
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.tv.material3.NavigationDrawerKt.NavigationDrawer(r7.r, androidx.compose.ui.Modifier, androidx.tv.material3.p1, r7.p, androidx.compose.runtime.Composer, int, int):void");
    }

    public static final p1 rememberDrawerState(q1 q1Var, Composer composer, int i10) {
        composer.startReplaceableGroup(-1159665049);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1159665049, i10, -1, "androidx.tv.material3.rememberDrawerState (NavigationDrawer.kt:199)");
        }
        Object[] objArr = new Object[0];
        Saver saver = p1.f6292b;
        composer.startReplaceableGroup(2099473959);
        boolean z = (((i10 & 14) ^ 6) > 4 && composer.changed(q1Var)) || (i10 & 6) == 4;
        Object objRememberedValue = composer.rememberedValue();
        if (z || objRememberedValue == Composer.INSTANCE.getEmpty()) {
            objRememberedValue = new a8.g0(q1Var, 11);
            composer.updateRememberedValue(objRememberedValue);
        }
        composer.endReplaceableGroup();
        p1 p1Var = (p1) RememberSaveableKt.m3084rememberSaveable(objArr, saver, (String) null, (r7.a) objRememberedValue, composer, 0, 4);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        composer.endReplaceableGroup();
        return p1Var;
    }
}
