package androidx.tv.material3;

import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000v\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u001aî\u0001\u0010\u001f\u001a\u00020\u0004*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u00012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u0011\u0010\u0007\u001a\r\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0002\b\u00062\b\b\u0002\u0010\t\u001a\u00020\b2\b\b\u0002\u0010\n\u001a\u00020\u00012\u0010\b\u0002\u0010\u000b\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u00032\u0015\b\u0002\u0010\f\u001a\u000f\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003¢\u0006\u0002\b\u00062\u0015\b\u0002\u0010\r\u001a\u000f\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003¢\u0006\u0002\b\u00062\b\b\u0002\u0010\u000f\u001a\u00020\u000e2\b\b\u0002\u0010\u0011\u001a\u00020\u00102\b\b\u0002\u0010\u0013\u001a\u00020\u00122\b\b\u0002\u0010\u0015\u001a\u00020\u00142\b\b\u0002\u0010\u0017\u001a\u00020\u00162\b\b\u0002\u0010\u0019\u001a\u00020\u00182\n\b\u0002\u0010\u001b\u001a\u0004\u0018\u00010\u001a2\u0011\u0010\u001c\u001a\r\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0002\b\u0006H\u0007ø\u0001\u0000¢\u0006\u0004\b\u001d\u0010\u001e\u001a\u0013\u0010!\u001a\u00020 *\u00020\u0010H\u0003¢\u0006\u0004\b!\u0010\"\u001a\u001b\u0010%\u001a\u00020$*\u00020\u00122\u0006\u0010#\u001a\u00020\u0001H\u0003¢\u0006\u0004\b%\u0010&\u001a\u0013\u0010(\u001a\u00020'*\u00020\u0014H\u0003¢\u0006\u0004\b(\u0010)\u001a\u0013\u0010+\u001a\u00020**\u00020\u0016H\u0003¢\u0006\u0004\b+\u0010,\u001a\u0013\u0010.\u001a\u00020-*\u00020\u0018H\u0003¢\u0006\u0004\b.\u0010/\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u00061²\u0006\f\u00100\u001a\u00020\u000e8\nX\u008a\u0084\u0002"}, d2 = {"Landroidx/tv/material3/o2;", "", "selected", "Lkotlin/Function0;", "Lx6/t0;", "onClick", "Landroidx/compose/runtime/Composable;", "leadingContent", "Landroidx/compose/ui/Modifier;", "modifier", "enabled", "onLongClick", "supportingContent", "trailingContent", "Landroidx/compose/ui/unit/Dp;", "tonalElevation", "Landroidx/tv/material3/l2;", "shape", "Landroidx/tv/material3/f2;", "colors", "Landroidx/tv/material3/k2;", "scale", "Landroidx/tv/material3/e2;", "border", "Landroidx/tv/material3/h2;", "glow", "Landroidx/compose/foundation/interaction/MutableInteractionSource;", "interactionSource", "content", "NavigationDrawerItem-eLwUrMk", "(Landroidx/tv/material3/o2;ZLr7/a;Lr7/p;Landroidx/compose/ui/Modifier;ZLr7/a;Lr7/p;Lr7/p;FLandroidx/tv/material3/l2;Landroidx/tv/material3/f2;Landroidx/tv/material3/k2;Landroidx/tv/material3/e2;Landroidx/tv/material3/h2;Landroidx/compose/foundation/interaction/MutableInteractionSource;Lr7/p;Landroidx/compose/runtime/Composer;III)V", "NavigationDrawerItem", "Landroidx/tv/material3/d2;", "toToggleableListItemShape", "(Landroidx/tv/material3/l2;Landroidx/compose/runtime/Composer;I)Landroidx/tv/material3/d2;", "doesNavigationDrawerHaveFocus", "Landroidx/tv/material3/x1;", "toToggleableListItemColors", "(Landroidx/tv/material3/f2;ZLandroidx/compose/runtime/Composer;I)Landroidx/tv/material3/x1;", "Landroidx/tv/material3/c2;", "toToggleableListItemScale", "(Landroidx/tv/material3/k2;Landroidx/compose/runtime/Composer;I)Landroidx/tv/material3/c2;", "Landroidx/tv/material3/w1;", "toToggleableListItemBorder", "(Landroidx/tv/material3/e2;Landroidx/compose/runtime/Composer;I)Landroidx/tv/material3/w1;", "Landroidx/tv/material3/y1;", "toToggleableListItemGlow", "(Landroidx/tv/material3/h2;Landroidx/compose/runtime/Composer;I)Landroidx/tv/material3/y1;", "animatedWidth", "tv-material_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class NavigationDrawerItemKt {
    /* JADX WARN: Removed duplicated region for block: B:101:0x0130  */
    /* JADX WARN: Removed duplicated region for block: B:103:0x0137  */
    /* JADX WARN: Removed duplicated region for block: B:112:0x0150  */
    /* JADX WARN: Removed duplicated region for block: B:120:0x0166  */
    /* JADX WARN: Removed duplicated region for block: B:123:0x016e  */
    /* JADX WARN: Removed duplicated region for block: B:132:0x0186  */
    /* JADX WARN: Removed duplicated region for block: B:135:0x018f  */
    /* JADX WARN: Removed duplicated region for block: B:136:0x0196  */
    /* JADX WARN: Removed duplicated region for block: B:146:0x01b2  */
    /* JADX WARN: Removed duplicated region for block: B:154:0x01c6  */
    /* JADX WARN: Removed duplicated region for block: B:157:0x01cc  */
    /* JADX WARN: Removed duplicated region for block: B:165:0x01e0  */
    /* JADX WARN: Removed duplicated region for block: B:168:0x01e8  */
    /* JADX WARN: Removed duplicated region for block: B:170:0x01ef  */
    /* JADX WARN: Removed duplicated region for block: B:178:0x0208  */
    /* JADX WARN: Removed duplicated region for block: B:179:0x020b  */
    /* JADX WARN: Removed duplicated region for block: B:187:0x0223  */
    /* JADX WARN: Removed duplicated region for block: B:193:0x024f  */
    /* JADX WARN: Removed duplicated region for block: B:211:0x029a  */
    /* JADX WARN: Removed duplicated region for block: B:212:0x029c  */
    /* JADX WARN: Removed duplicated region for block: B:213:0x029f  */
    /* JADX WARN: Removed duplicated region for block: B:215:0x02a2  */
    /* JADX WARN: Removed duplicated region for block: B:216:0x02a5  */
    /* JADX WARN: Removed duplicated region for block: B:218:0x02a9  */
    /* JADX WARN: Removed duplicated region for block: B:219:0x02ac  */
    /* JADX WARN: Removed duplicated region for block: B:221:0x02b0  */
    /* JADX WARN: Removed duplicated region for block: B:222:0x02b3  */
    /* JADX WARN: Removed duplicated region for block: B:224:0x02b7  */
    /* JADX WARN: Removed duplicated region for block: B:225:0x02ba  */
    /* JADX WARN: Removed duplicated region for block: B:227:0x02be  */
    /* JADX WARN: Removed duplicated region for block: B:228:0x02c3  */
    /* JADX WARN: Removed duplicated region for block: B:231:0x02c9  */
    /* JADX WARN: Removed duplicated region for block: B:233:0x02eb  */
    /* JADX WARN: Removed duplicated region for block: B:236:0x02f2  */
    /* JADX WARN: Removed duplicated region for block: B:237:0x035c  */
    /* JADX WARN: Removed duplicated region for block: B:239:0x0368  */
    /* JADX WARN: Removed duplicated region for block: B:240:0x036d  */
    /* JADX WARN: Removed duplicated region for block: B:243:0x0373  */
    /* JADX WARN: Removed duplicated region for block: B:244:0x038e  */
    /* JADX WARN: Removed duplicated region for block: B:247:0x0398  */
    /* JADX WARN: Removed duplicated region for block: B:248:0x03bb  */
    /* JADX WARN: Removed duplicated region for block: B:250:0x03bf  */
    /* JADX WARN: Removed duplicated region for block: B:252:0x03d7  */
    /* JADX WARN: Removed duplicated region for block: B:255:0x03e9  */
    /* JADX WARN: Removed duplicated region for block: B:256:0x03f4  */
    /* JADX WARN: Removed duplicated region for block: B:259:0x03fc  */
    /* JADX WARN: Removed duplicated region for block: B:260:0x03ff  */
    /* JADX WARN: Removed duplicated region for block: B:263:0x0421  */
    /* JADX WARN: Removed duplicated region for block: B:265:0x0428  */
    /* JADX WARN: Removed duplicated region for block: B:267:0x042d  */
    /* JADX WARN: Removed duplicated region for block: B:268:0x0441  */
    /* JADX WARN: Removed duplicated region for block: B:270:0x044c  */
    /* JADX WARN: Removed duplicated region for block: B:271:0x045b  */
    /* JADX WARN: Removed duplicated region for block: B:276:0x047a  */
    /* JADX WARN: Removed duplicated region for block: B:279:0x051f  */
    /* JADX WARN: Removed duplicated region for block: B:283:0x053b  */
    /* JADX WARN: Removed duplicated region for block: B:285:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:29:0x0057  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x005c  */
    /* JADX WARN: Removed duplicated region for block: B:40:0x0077  */
    /* JADX WARN: Removed duplicated region for block: B:41:0x007a  */
    /* JADX WARN: Removed duplicated region for block: B:50:0x0092  */
    /* JADX WARN: Removed duplicated region for block: B:52:0x0097  */
    /* JADX WARN: Removed duplicated region for block: B:61:0x00b4  */
    /* JADX WARN: Removed duplicated region for block: B:62:0x00b9  */
    /* JADX WARN: Removed duplicated region for block: B:71:0x00d6  */
    /* JADX WARN: Removed duplicated region for block: B:72:0x00db  */
    /* JADX WARN: Removed duplicated region for block: B:81:0x00f4  */
    /* JADX WARN: Removed duplicated region for block: B:82:0x00f9  */
    /* JADX WARN: Removed duplicated region for block: B:91:0x0112  */
    /* JADX WARN: Removed duplicated region for block: B:92:0x0117  */
    /* JADX INFO: renamed from: NavigationDrawerItem-eLwUrMk, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void m6000NavigationDrawerItemeLwUrMk(androidx.tv.material3.o2 r55, boolean r56, r7.a<x6.t0> r57, r7.p<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, x6.t0> r58, androidx.compose.ui.Modifier r59, boolean r60, r7.a<x6.t0> r61, r7.p<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, x6.t0> r62, r7.p<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, x6.t0> r63, float r64, androidx.tv.material3.l2 r65, androidx.tv.material3.f2 r66, androidx.tv.material3.k2 r67, androidx.tv.material3.e2 r68, androidx.tv.material3.h2 r69, androidx.compose.foundation.interaction.MutableInteractionSource r70, r7.p<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, x6.t0> r71, androidx.compose.runtime.Composer r72, int r73, int r74, int r75) {
        /*
            Method dump skipped, instruction units count: 1365
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.tv.material3.NavigationDrawerItemKt.m6000NavigationDrawerItemeLwUrMk(androidx.tv.material3.o2, boolean, r7.a, r7.p, androidx.compose.ui.Modifier, boolean, r7.a, r7.p, r7.p, float, androidx.tv.material3.l2, androidx.tv.material3.f2, androidx.tv.material3.k2, androidx.tv.material3.e2, androidx.tv.material3.h2, androidx.compose.foundation.interaction.MutableInteractionSource, r7.p, androidx.compose.runtime.Composer, int, int, int):void");
    }

    private static final w1 toToggleableListItemBorder(e2 e2Var, Composer composer, int i10) {
        composer.startReplaceableGroup(608350888);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(608350888, i10, -1, "androidx.tv.material3.toToggleableListItemBorder (NavigationDrawerItem.kt:215)");
        }
        w1 w1VarBorder = ListItemDefaults.f5640a.border(e2Var.f5934a, e2Var.f5935b, e2Var.f5936c, e2Var.f5937d, e2Var.f5938e, e2Var.f5939f, e2Var.f5940g, e2Var.f5941h, composer, 100663296, 0);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        composer.endReplaceableGroup();
        return w1VarBorder;
    }

    private static final x1 toToggleableListItemColors(f2 f2Var, boolean z, Composer composer, int i10) {
        long j10;
        long j11;
        composer.startReplaceableGroup(225557664);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(225557664, i10, -1, "androidx.tv.material3.toToggleableListItemColors (NavigationDrawerItem.kt:181)");
        }
        ListItemDefaults listItemDefaults = ListItemDefaults.f5640a;
        long j12 = f2Var.f5966a;
        long j13 = z ? f2Var.f5967b : f2Var.f5968c;
        long j14 = f2Var.f5969d;
        long j15 = f2Var.f5970e;
        long j16 = f2Var.f5971f;
        long j17 = f2Var.f5972g;
        long j18 = f2Var.f5973h;
        long j19 = f2Var.f5974i;
        long j20 = f2Var.f5975j;
        if (z) {
            j10 = j20;
            j11 = f2Var.k;
        } else {
            j10 = j20;
            j11 = f2Var.f5976l;
        }
        x1 x1VarM5994colorsu3YEpmA = listItemDefaults.m5994colorsu3YEpmA(j12, j13, j14, j15, j16, j17, j18, j19, j10, j11, f2Var.f5977m, f2Var.f5978n, f2Var.f5979o, f2Var.f5980p, composer, 0, 24576, 0);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        composer.endReplaceableGroup();
        return x1VarM5994colorsu3YEpmA;
    }

    private static final y1 toToggleableListItemGlow(h2 h2Var, Composer composer, int i10) {
        composer.startReplaceableGroup(2089307143);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(2089307143, i10, -1, "androidx.tv.material3.toToggleableListItemGlow (NavigationDrawerItem.kt:228)");
        }
        ListItemDefaults listItemDefaults = ListItemDefaults.f5640a;
        y1 y1Var = new y1(h2Var.f6046a, h2Var.f6047b, h2Var.f6048c, h2Var.f6049d, h2Var.f6050e, h2Var.f6051f);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        composer.endReplaceableGroup();
        return y1Var;
    }

    private static final c2 toToggleableListItemScale(k2 k2Var, Composer composer, int i10) {
        composer.startReplaceableGroup(801988534);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(801988534, i10, -1, "androidx.tv.material3.toToggleableListItemScale (NavigationDrawerItem.kt:202)");
        }
        ListItemDefaults listItemDefaults = ListItemDefaults.f5640a;
        k2Var.getClass();
        c2 c2Var = new c2(1.0f, 1.0f);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        composer.endReplaceableGroup();
        return c2Var;
    }

    private static final d2 toToggleableListItemShape(l2 l2Var, Composer composer, int i10) {
        composer.startReplaceableGroup(1585915629);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1585915629, i10, -1, "androidx.tv.material3.toToggleableListItemShape (NavigationDrawerItem.kt:166)");
        }
        d2 d2VarShape = ListItemDefaults.f5640a.shape(l2Var.f6188a, l2Var.f6189b, l2Var.f6190c, l2Var.f6191d, l2Var.f6192e, l2Var.f6193f, l2Var.f6194g, l2Var.f6195h, composer, 100663296, 0);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        composer.endReplaceableGroup();
        return d2VarShape;
    }
}
