package androidx.tv.material3;

import io.ktor.http.ContentDisposition;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000N\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\u001a°\u0001\u0010\u001a\u001a\u00020\b2\u0006\u0010\u0001\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\b\b\u0002\u0010\u0005\u001a\u00020\u00042\b\b\u0002\u0010\u0006\u001a\u00020\u00042\u0013\b\u0002\u0010\n\u001a\r\u0012\u0004\u0012\u00020\b0\u0007¢\u0006\u0002\b\t2C\b\u0002\u0010\u0013\u001a=\u0012\u0019\u0012\u0017\u0012\u0004\u0012\u00020\r0\f¢\u0006\f\b\u000e\u0012\b\b\u000f\u0012\u0004\b\b(\u0010\u0012\u0013\u0012\u00110\u0011¢\u0006\f\b\u000e\u0012\b\b\u000f\u0012\u0004\b\b(\u0012\u0012\u0004\u0012\u00020\b0\u000b¢\u0006\u0002\b\t2\u001c\u0010\u0017\u001a\u0018\u0012\u0004\u0012\u00020\u0015\u0012\u0004\u0012\u00020\b0\u0014¢\u0006\u0002\b\t¢\u0006\u0002\b\u0016H\u0007ø\u0001\u0000¢\u0006\u0004\b\u0018\u0010\u0019\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006\u001b²\u0006\u000e\u0010\u0012\u001a\u00020\u00118\n@\nX\u008a\u008e\u0002"}, d2 = {"", "selectedTabIndex", "Landroidx/compose/ui/Modifier;", "modifier", "Landroidx/compose/ui/graphics/Color;", "containerColor", "contentColor", "Lkotlin/Function0;", "Lx6/t0;", "Landroidx/compose/runtime/Composable;", "separator", "Lkotlin/Function2;", "", "Landroidx/compose/ui/unit/DpRect;", "Lx6/y;", ContentDisposition.Parameters.Name, "tabPositions", "", "doesTabRowHaveFocus", "indicator", "Lkotlin/Function1;", "Landroidx/tv/material3/l4;", "Lx6/n;", "tabs", "TabRow-pAZo6Ak", "(ILandroidx/compose/ui/Modifier;JJLr7/p;Lr7/r;Lr7/q;Landroidx/compose/runtime/Composer;II)V", "TabRow", "tv-material_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class TabRowKt {
    /* JADX WARN: Removed duplicated region for block: B:101:0x0123  */
    /* JADX WARN: Removed duplicated region for block: B:104:0x013c  */
    /* JADX WARN: Removed duplicated region for block: B:107:0x015c  */
    /* JADX WARN: Removed duplicated region for block: B:110:0x019c  */
    /* JADX WARN: Removed duplicated region for block: B:113:0x01f5  */
    /* JADX WARN: Removed duplicated region for block: B:117:0x0207  */
    /* JADX WARN: Removed duplicated region for block: B:119:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:26:0x0045  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x004a  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x0060  */
    /* JADX WARN: Removed duplicated region for block: B:43:0x0073  */
    /* JADX WARN: Removed duplicated region for block: B:46:0x0079  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x007e  */
    /* JADX WARN: Removed duplicated region for block: B:57:0x0097  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x009c  */
    /* JADX WARN: Removed duplicated region for block: B:67:0x00b5  */
    /* JADX WARN: Removed duplicated region for block: B:68:0x00b8  */
    /* JADX WARN: Removed duplicated region for block: B:77:0x00d3  */
    /* JADX WARN: Removed duplicated region for block: B:81:0x00e7  */
    /* JADX WARN: Removed duplicated region for block: B:91:0x0106  */
    /* JADX WARN: Removed duplicated region for block: B:92:0x0108  */
    /* JADX WARN: Removed duplicated region for block: B:94:0x010d  */
    /* JADX WARN: Removed duplicated region for block: B:97:0x0113  */
    /* JADX WARN: Removed duplicated region for block: B:99:0x011e  */
    /* JADX INFO: renamed from: TabRow-pAZo6Ak, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void m6019TabRowpAZo6Ak(int r25, androidx.compose.ui.Modifier r26, long r27, long r29, r7.p<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, x6.t0> r31, r7.r<? super java.util.List<androidx.compose.ui.unit.DpRect>, ? super java.lang.Boolean, ? super androidx.compose.runtime.Composer, ? super java.lang.Integer, x6.t0> r32, r7.q<? super androidx.tv.material3.l4, ? super androidx.compose.runtime.Composer, ? super java.lang.Integer, x6.t0> r33, androidx.compose.runtime.Composer r34, int r35, int r36) {
        /*
            Method dump skipped, instruction units count: 532
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.tv.material3.TabRowKt.m6019TabRowpAZo6Ak(int, androidx.compose.ui.Modifier, long, long, r7.p, r7.r, r7.q, androidx.compose.runtime.Composer, int, int):void");
    }
}
