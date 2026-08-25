package androidx.tv.material3;

import androidx.compose.foundation.layout.BoxScope;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalKt;
import androidx.compose.runtime.ProvidableCompositionLocal;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.ColorKt;
import androidx.compose.ui.graphics.Shape;
import androidx.compose.ui.unit.Dp;
import androidx.media3.exoplayer.RendererCapabilities;
import androidx.media3.extractor.text.ttml.TtmlNode;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\u0090\u0001\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\r\u001al\u0010\u0014\u001a\u00020\u000e2\b\b\u0002\u0010\u0001\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\b\b\u0002\u0010\u0005\u001a\u00020\u00042\b\b\u0002\u0010\u0007\u001a\u00020\u00062\b\b\u0002\u0010\t\u001a\u00020\b2\b\b\u0002\u0010\u000b\u001a\u00020\n2\u001c\u0010\u0011\u001a\u0018\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\u000e0\f¢\u0006\u0002\b\u000f¢\u0006\u0002\b\u0010H\u0007ø\u0001\u0000¢\u0006\u0004\b\u0012\u0010\u0013\u001a¬\u0001\u0010\u0014\u001a\u00020\u000e2\f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u000e0\u00152\b\b\u0002\u0010\u0001\u001a\u00020\u00002\u0010\b\u0002\u0010\u0017\u001a\n\u0012\u0004\u0012\u00020\u000e\u0018\u00010\u00152\b\b\u0002\u0010\u0019\u001a\u00020\u00182\b\b\u0002\u0010\u0003\u001a\u00020\u00022\b\b\u0002\u0010\u0005\u001a\u00020\u001a2\b\b\u0002\u0010\u0007\u001a\u00020\u001b2\b\b\u0002\u0010\u001d\u001a\u00020\u001c2\b\b\u0002\u0010\t\u001a\u00020\u001e2\b\b\u0002\u0010\u000b\u001a\u00020\u001f2\n\b\u0002\u0010!\u001a\u0004\u0018\u00010 2\u001c\u0010\u0011\u001a\u0018\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\u000e0\f¢\u0006\u0002\b\u000f¢\u0006\u0002\b\u0010H\u0007ø\u0001\u0000¢\u0006\u0004\b\"\u0010#\u001a´\u0001\u0010\u0014\u001a\u00020\u000e2\u0006\u0010$\u001a\u00020\u00182\f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u000e0\u00152\b\b\u0002\u0010\u0001\u001a\u00020\u00002\b\b\u0002\u0010\u0019\u001a\u00020\u00182\u0010\b\u0002\u0010\u0017\u001a\n\u0012\u0004\u0012\u00020\u000e\u0018\u00010\u00152\b\b\u0002\u0010\u0003\u001a\u00020\u00022\b\b\u0002\u0010\u0005\u001a\u00020%2\b\b\u0002\u0010\u0007\u001a\u00020&2\b\b\u0002\u0010\u001d\u001a\u00020'2\b\b\u0002\u0010\t\u001a\u00020(2\b\b\u0002\u0010\u000b\u001a\u00020)2\n\b\u0002\u0010!\u001a\u0004\u0018\u00010 2\u001c\u0010\u0011\u001a\u0018\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\u000e0\f¢\u0006\u0002\b\u000f¢\u0006\u0002\b\u0010H\u0007ø\u0001\u0000¢\u0006\u0004\b*\u0010+\u001a\u008c\u0001\u00102\u001a\u00020\u000e2\u0006\u0010\u0001\u001a\u00020\u00002\u0006\u0010$\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u00182\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010-\u001a\u00020,2\u0006\u0010.\u001a\u00020,2\u0006\u0010\u001d\u001a\u00020/2\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\u000b\u001a\u00020\n2\u0006\u0010\u0003\u001a\u00020\u00022\n\b\u0002\u0010!\u001a\u0004\u0018\u00010 2\u001c\u0010\u0011\u001a\u0018\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\u000e0\f¢\u0006\u0002\b\u000f¢\u0006\u0002\b\u0010H\u0003ø\u0001\u0000¢\u0006\u0004\b0\u00101\u001a\"\u00106\u001a\u00020,2\u0006\u0010-\u001a\u00020,2\u0006\u00103\u001a\u00020\u0002H\u0001ø\u0001\u0000¢\u0006\u0004\b4\u00105\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006<²\u0006\f\u00107\u001a\u00020\u00188\nX\u008a\u0084\u0002²\u0006\f\u00108\u001a\u00020\u00188\nX\u008a\u0084\u0002²\u0006\f\u00107\u001a\u00020\u00188\nX\u008a\u0084\u0002²\u0006\f\u00108\u001a\u00020\u00188\nX\u008a\u0084\u0002²\u0006\f\u00107\u001a\u00020\u00188\nX\u008a\u0084\u0002²\u0006\f\u00108\u001a\u00020\u00188\nX\u008a\u0084\u0002²\u0006\f\u00109\u001a\u00020/8\nX\u008a\u0084\u0002²\u0006\u000e\u0010:\u001a\u00020\u00188\n@\nX\u008a\u008e\u0002²\u0006\f\u0010;\u001a\u00020\u00188\nX\u008a\u0084\u0002"}, d2 = {"Landroidx/compose/ui/Modifier;", "modifier", "Landroidx/compose/ui/unit/Dp;", "tonalElevation", "Landroidx/compose/ui/graphics/Shape;", "shape", "Landroidx/tv/material3/f3;", "colors", "Landroidx/tv/material3/d;", "border", "Landroidx/tv/material3/s1;", "glow", "Lkotlin/Function1;", "Landroidx/compose/foundation/layout/BoxScope;", "Lx6/t0;", "Landroidx/compose/runtime/Composable;", "Lx6/n;", "content", "Surface-jfnsLPA", "(Landroidx/compose/ui/Modifier;FLandroidx/compose/ui/graphics/Shape;Landroidx/tv/material3/f3;Landroidx/tv/material3/d;Landroidx/tv/material3/s1;Lr7/q;Landroidx/compose/runtime/Composer;II)V", "Surface", "Lkotlin/Function0;", "onClick", "onLongClick", "", "enabled", "Landroidx/tv/material3/g1;", "Landroidx/tv/material3/d1;", "Landroidx/tv/material3/f1;", "scale", "Landroidx/tv/material3/c1;", "Landroidx/tv/material3/e1;", "Landroidx/compose/foundation/interaction/MutableInteractionSource;", "interactionSource", "Surface-05tvjtU", "(Lr7/a;Landroidx/compose/ui/Modifier;Lr7/a;ZFLandroidx/tv/material3/g1;Landroidx/tv/material3/d1;Landroidx/tv/material3/f1;Landroidx/tv/material3/c1;Landroidx/tv/material3/e1;Landroidx/compose/foundation/interaction/MutableInteractionSource;Lr7/q;Landroidx/compose/runtime/Composer;III)V", "selected", "Landroidx/tv/material3/b3;", "Landroidx/tv/material3/y2;", "Landroidx/tv/material3/a3;", "Landroidx/tv/material3/x2;", "Landroidx/tv/material3/z2;", "Surface-xYaah8o", "(ZLr7/a;Landroidx/compose/ui/Modifier;ZLr7/a;FLandroidx/tv/material3/b3;Landroidx/tv/material3/y2;Landroidx/tv/material3/a3;Landroidx/tv/material3/x2;Landroidx/tv/material3/z2;Landroidx/compose/foundation/interaction/MutableInteractionSource;Lr7/q;Landroidx/compose/runtime/Composer;III)V", "Landroidx/compose/ui/graphics/Color;", TtmlNode.ATTR_TTS_COLOR, "contentColor", "", "SurfaceImpl-vf0GYmI", "(Landroidx/compose/ui/Modifier;ZZLandroidx/compose/ui/graphics/Shape;JJFLandroidx/tv/material3/d;Landroidx/tv/material3/s1;FLandroidx/compose/foundation/interaction/MutableInteractionSource;Lr7/q;Landroidx/compose/runtime/Composer;III)V", "SurfaceImpl", "elevation", "surfaceColorAtElevation-CLU3JFs", "(JFLandroidx/compose/runtime/Composer;I)J", "surfaceColorAtElevation", "focused", "pressed", "zIndex", "isLongClick", "isPressed", "tv-material_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class SurfaceKt {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final ProvidableCompositionLocal f5724a = CompositionLocalKt.compositionLocalOf$default(null, i1.f6093n, 1, null);

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final int[] f5725b = {23, 66, 160};

    /* JADX WARN: Removed duplicated region for block: B:101:0x010e  */
    /* JADX WARN: Removed duplicated region for block: B:110:0x0126  */
    /* JADX WARN: Removed duplicated region for block: B:113:0x012d  */
    /* JADX WARN: Removed duplicated region for block: B:114:0x0132  */
    /* JADX WARN: Removed duplicated region for block: B:124:0x014c  */
    /* JADX WARN: Removed duplicated region for block: B:127:0x0153  */
    /* JADX WARN: Removed duplicated region for block: B:136:0x0171  */
    /* JADX WARN: Removed duplicated region for block: B:142:0x0195  */
    /* JADX WARN: Removed duplicated region for block: B:164:0x01e8  */
    /* JADX WARN: Removed duplicated region for block: B:165:0x01ea  */
    /* JADX WARN: Removed duplicated region for block: B:166:0x01ed  */
    /* JADX WARN: Removed duplicated region for block: B:168:0x01f0  */
    /* JADX WARN: Removed duplicated region for block: B:170:0x01f3  */
    /* JADX WARN: Removed duplicated region for block: B:172:0x01f6  */
    /* JADX WARN: Removed duplicated region for block: B:173:0x01fd  */
    /* JADX WARN: Removed duplicated region for block: B:176:0x0205  */
    /* JADX WARN: Removed duplicated region for block: B:178:0x0227  */
    /* JADX WARN: Removed duplicated region for block: B:181:0x0237  */
    /* JADX WARN: Removed duplicated region for block: B:182:0x0258  */
    /* JADX WARN: Removed duplicated region for block: B:185:0x0260  */
    /* JADX WARN: Removed duplicated region for block: B:187:0x026f  */
    /* JADX WARN: Removed duplicated region for block: B:190:0x0276  */
    /* JADX WARN: Removed duplicated region for block: B:191:0x0288  */
    /* JADX WARN: Removed duplicated region for block: B:194:0x028e  */
    /* JADX WARN: Removed duplicated region for block: B:195:0x029a  */
    /* JADX WARN: Removed duplicated region for block: B:198:0x02a2  */
    /* JADX WARN: Removed duplicated region for block: B:199:0x02b1  */
    /* JADX WARN: Removed duplicated region for block: B:202:0x02ca  */
    /* JADX WARN: Removed duplicated region for block: B:205:0x02da  */
    /* JADX WARN: Removed duplicated region for block: B:209:0x02f9  */
    /* JADX WARN: Removed duplicated region for block: B:212:0x030c  */
    /* JADX WARN: Removed duplicated region for block: B:214:0x032a  */
    /* JADX WARN: Removed duplicated region for block: B:220:0x0370  */
    /* JADX WARN: Removed duplicated region for block: B:223:0x0377  */
    /* JADX WARN: Removed duplicated region for block: B:226:0x037e  */
    /* JADX WARN: Removed duplicated region for block: B:227:0x0380  */
    /* JADX WARN: Removed duplicated region for block: B:228:0x0383  */
    /* JADX WARN: Removed duplicated region for block: B:234:0x03a3  */
    /* JADX WARN: Removed duplicated region for block: B:237:0x03aa  */
    /* JADX WARN: Removed duplicated region for block: B:238:0x03ac  */
    /* JADX WARN: Removed duplicated region for block: B:239:0x03af  */
    /* JADX WARN: Removed duplicated region for block: B:245:0x03cf  */
    /* JADX WARN: Removed duplicated region for block: B:248:0x03d6  */
    /* JADX WARN: Removed duplicated region for block: B:249:0x03d8  */
    /* JADX WARN: Removed duplicated region for block: B:250:0x03db  */
    /* JADX WARN: Removed duplicated region for block: B:256:0x03fe  */
    /* JADX WARN: Removed duplicated region for block: B:259:0x0405  */
    /* JADX WARN: Removed duplicated region for block: B:267:0x042d A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:26:0x0045  */
    /* JADX WARN: Removed duplicated region for block: B:270:0x0434  */
    /* JADX WARN: Removed duplicated region for block: B:273:0x043b  */
    /* JADX WARN: Removed duplicated region for block: B:276:0x0442  */
    /* JADX WARN: Removed duplicated region for block: B:277:0x0444  */
    /* JADX WARN: Removed duplicated region for block: B:278:0x0447  */
    /* JADX WARN: Removed duplicated region for block: B:281:0x0460  */
    /* JADX WARN: Removed duplicated region for block: B:287:0x046f  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x004a  */
    /* JADX WARN: Removed duplicated region for block: B:290:0x049a  */
    /* JADX WARN: Removed duplicated region for block: B:294:0x04b3  */
    /* JADX WARN: Removed duplicated region for block: B:296:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:37:0x0060  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x0065  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x007c  */
    /* JADX WARN: Removed duplicated region for block: B:50:0x0081  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x009a  */
    /* JADX WARN: Removed duplicated region for block: B:66:0x00ae  */
    /* JADX WARN: Removed duplicated region for block: B:69:0x00b6  */
    /* JADX WARN: Removed duplicated region for block: B:76:0x00ca  */
    /* JADX WARN: Removed duplicated region for block: B:79:0x00d2  */
    /* JADX WARN: Removed duplicated region for block: B:87:0x00e8  */
    /* JADX WARN: Removed duplicated region for block: B:90:0x00f0  */
    /* JADX WARN: Removed duplicated region for block: B:98:0x0106  */
    /* JADX INFO: renamed from: Surface-05tvjtU, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void m6007Surface05tvjtU(r7.a<x6.t0> r42, androidx.compose.ui.Modifier r43, r7.a<x6.t0> r44, boolean r45, float r46, androidx.tv.material3.g1 r47, androidx.tv.material3.d1 r48, androidx.tv.material3.f1 r49, androidx.tv.material3.c1 r50, androidx.tv.material3.e1 r51, androidx.compose.foundation.interaction.MutableInteractionSource r52, r7.q<? super androidx.compose.foundation.layout.BoxScope, ? super androidx.compose.runtime.Composer, ? super java.lang.Integer, x6.t0> r53, androidx.compose.runtime.Composer r54, int r55, int r56, int r57) {
        /*
            Method dump skipped, instruction units count: 1221
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.tv.material3.SurfaceKt.m6007Surface05tvjtU(r7.a, androidx.compose.ui.Modifier, r7.a, boolean, float, androidx.tv.material3.g1, androidx.tv.material3.d1, androidx.tv.material3.f1, androidx.tv.material3.c1, androidx.tv.material3.e1, androidx.compose.foundation.interaction.MutableInteractionSource, r7.q, androidx.compose.runtime.Composer, int, int, int):void");
    }

    /* JADX INFO: renamed from: Surface-jfnsLPA, reason: not valid java name */
    public static final void m6008SurfacejfnsLPA(Modifier modifier, float f10, Shape shape, f3 f3Var, d dVar, s1 s1Var, r7.q<? super BoxScope, ? super Composer, ? super Integer, x6.t0> qVar, Composer composer, int i10, int i11) {
        d dVar2;
        s1 s1Var2;
        composer.startReplaceableGroup(178297762);
        Modifier modifier2 = (i11 & 1) != 0 ? Modifier.INSTANCE : modifier;
        float fM5678constructorimpl = (i11 & 2) != 0 ? Dp.m5678constructorimpl(0) : f10;
        Shape shape2 = (i11 & 4) != 0 ? SurfaceDefaults.f5717a.getShape(composer, 6) : shape;
        f3 f3VarM6006colorsdgg9oW8 = (i11 & 8) != 0 ? SurfaceDefaults.f5717a.m6006colorsdgg9oW8(0L, 0L, composer, RendererCapabilities.DECODER_SUPPORT_MASK, 3) : f3Var;
        if ((i11 & 16) != 0) {
            SurfaceDefaults surfaceDefaults = SurfaceDefaults.f5717a;
            dVar2 = SurfaceDefaults.f5718b;
        } else {
            dVar2 = dVar;
        }
        if ((i11 & 32) != 0) {
            SurfaceDefaults surfaceDefaults2 = SurfaceDefaults.f5717a;
            s1Var2 = SurfaceDefaults.f5719c;
        } else {
            s1Var2 = s1Var;
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(178297762, i10, -1, "androidx.tv.material3.Surface (Surface.kt:85)");
        }
        int i12 = i10 << 9;
        m6010SurfaceImplvf0GYmI(modifier2, false, true, shape2, f3VarM6006colorsdgg9oW8.f5981a, f3VarM6006colorsdgg9oW8.f5982b, 1.0f, dVar2, s1Var2, fM5678constructorimpl, null, qVar, composer, (i10 & 14) | 1573296 | ((i10 << 3) & 7168) | (29360128 & i12) | (i12 & 234881024) | ((i10 << 24) & 1879048192), (i10 >> 15) & 112, 1024);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        composer.endReplaceableGroup();
    }

    /* JADX WARN: Removed duplicated region for block: B:100:0x0111  */
    /* JADX WARN: Removed duplicated region for block: B:109:0x0129  */
    /* JADX WARN: Removed duplicated region for block: B:112:0x0130  */
    /* JADX WARN: Removed duplicated region for block: B:120:0x0146  */
    /* JADX WARN: Removed duplicated region for block: B:123:0x014e  */
    /* JADX WARN: Removed duplicated region for block: B:124:0x0157  */
    /* JADX WARN: Removed duplicated region for block: B:135:0x0178  */
    /* JADX WARN: Removed duplicated region for block: B:137:0x017d  */
    /* JADX WARN: Removed duplicated region for block: B:146:0x019a  */
    /* JADX WARN: Removed duplicated region for block: B:152:0x01ba  */
    /* JADX WARN: Removed duplicated region for block: B:173:0x0205  */
    /* JADX WARN: Removed duplicated region for block: B:174:0x0207  */
    /* JADX WARN: Removed duplicated region for block: B:175:0x020a  */
    /* JADX WARN: Removed duplicated region for block: B:177:0x020d  */
    /* JADX WARN: Removed duplicated region for block: B:178:0x0210  */
    /* JADX WARN: Removed duplicated region for block: B:180:0x0214  */
    /* JADX WARN: Removed duplicated region for block: B:181:0x0217  */
    /* JADX WARN: Removed duplicated region for block: B:183:0x021b  */
    /* JADX WARN: Removed duplicated region for block: B:184:0x0220  */
    /* JADX WARN: Removed duplicated region for block: B:187:0x0228  */
    /* JADX WARN: Removed duplicated region for block: B:189:0x0248  */
    /* JADX WARN: Removed duplicated region for block: B:192:0x0253  */
    /* JADX WARN: Removed duplicated region for block: B:193:0x0280  */
    /* JADX WARN: Removed duplicated region for block: B:196:0x0288  */
    /* JADX WARN: Removed duplicated region for block: B:197:0x0293  */
    /* JADX WARN: Removed duplicated region for block: B:200:0x0299  */
    /* JADX WARN: Removed duplicated region for block: B:201:0x02b7  */
    /* JADX WARN: Removed duplicated region for block: B:204:0x02be  */
    /* JADX WARN: Removed duplicated region for block: B:205:0x02dd  */
    /* JADX WARN: Removed duplicated region for block: B:207:0x02e1  */
    /* JADX WARN: Removed duplicated region for block: B:208:0x02f2  */
    /* JADX WARN: Removed duplicated region for block: B:211:0x030b  */
    /* JADX WARN: Removed duplicated region for block: B:214:0x031b  */
    /* JADX WARN: Removed duplicated region for block: B:218:0x033a  */
    /* JADX WARN: Removed duplicated region for block: B:221:0x034e  */
    /* JADX WARN: Removed duplicated region for block: B:223:0x036e  */
    /* JADX WARN: Removed duplicated region for block: B:229:0x03b7  */
    /* JADX WARN: Removed duplicated region for block: B:233:0x03c0  */
    /* JADX WARN: Removed duplicated region for block: B:236:0x03c7  */
    /* JADX WARN: Removed duplicated region for block: B:239:0x03ce  */
    /* JADX WARN: Removed duplicated region for block: B:242:0x03d5  */
    /* JADX WARN: Removed duplicated region for block: B:243:0x03d7  */
    /* JADX WARN: Removed duplicated region for block: B:244:0x03da  */
    /* JADX WARN: Removed duplicated region for block: B:260:0x0414  */
    /* JADX WARN: Removed duplicated region for block: B:265:0x0421  */
    /* JADX WARN: Removed duplicated region for block: B:268:0x0428  */
    /* JADX WARN: Removed duplicated region for block: B:271:0x042f  */
    /* JADX WARN: Removed duplicated region for block: B:274:0x0436  */
    /* JADX WARN: Removed duplicated region for block: B:275:0x0438  */
    /* JADX WARN: Removed duplicated region for block: B:276:0x043b  */
    /* JADX WARN: Removed duplicated region for block: B:282:0x045c  */
    /* JADX WARN: Removed duplicated region for block: B:287:0x0467  */
    /* JADX WARN: Removed duplicated region for block: B:290:0x046e  */
    /* JADX WARN: Removed duplicated region for block: B:293:0x0475  */
    /* JADX WARN: Removed duplicated region for block: B:296:0x047c  */
    /* JADX WARN: Removed duplicated region for block: B:297:0x047e  */
    /* JADX WARN: Removed duplicated region for block: B:298:0x0481  */
    /* JADX WARN: Removed duplicated region for block: B:304:0x04a3  */
    /* JADX WARN: Removed duplicated region for block: B:309:0x04ae  */
    /* JADX WARN: Removed duplicated region for block: B:312:0x04b6  */
    /* JADX WARN: Removed duplicated region for block: B:315:0x04be  */
    /* JADX WARN: Removed duplicated region for block: B:319:0x04c7  */
    /* JADX WARN: Removed duplicated region for block: B:320:0x04cb  */
    /* JADX WARN: Removed duplicated region for block: B:336:0x0505  */
    /* JADX WARN: Removed duplicated region for block: B:340:0x050e  */
    /* JADX WARN: Removed duplicated region for block: B:343:0x0515  */
    /* JADX WARN: Removed duplicated region for block: B:346:0x051c  */
    /* JADX WARN: Removed duplicated region for block: B:349:0x0523  */
    /* JADX WARN: Removed duplicated region for block: B:350:0x0525  */
    /* JADX WARN: Removed duplicated region for block: B:351:0x0528  */
    /* JADX WARN: Removed duplicated region for block: B:367:0x055e  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x0061  */
    /* JADX WARN: Removed duplicated region for block: B:371:0x0567  */
    /* JADX WARN: Removed duplicated region for block: B:374:0x056e  */
    /* JADX WARN: Removed duplicated region for block: B:377:0x0575  */
    /* JADX WARN: Removed duplicated region for block: B:380:0x057c  */
    /* JADX WARN: Removed duplicated region for block: B:381:0x057e  */
    /* JADX WARN: Removed duplicated region for block: B:382:0x0581  */
    /* JADX WARN: Removed duplicated region for block: B:385:0x05be  */
    /* JADX WARN: Removed duplicated region for block: B:389:0x05d9  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x0066  */
    /* JADX WARN: Removed duplicated region for block: B:391:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:47:0x007d  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x0082  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x009b  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x00a0  */
    /* JADX WARN: Removed duplicated region for block: B:68:0x00b9  */
    /* JADX WARN: Removed duplicated region for block: B:75:0x00cd  */
    /* JADX WARN: Removed duplicated region for block: B:78:0x00d5  */
    /* JADX WARN: Removed duplicated region for block: B:86:0x00eb  */
    /* JADX WARN: Removed duplicated region for block: B:89:0x00f3  */
    /* JADX WARN: Removed duplicated region for block: B:97:0x0109  */
    /* JADX INFO: renamed from: Surface-xYaah8o, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void m6009SurfacexYaah8o(boolean r48, r7.a<x6.t0> r49, androidx.compose.ui.Modifier r50, boolean r51, r7.a<x6.t0> r52, float r53, androidx.tv.material3.b3 r54, androidx.tv.material3.y2 r55, androidx.tv.material3.a3 r56, androidx.tv.material3.x2 r57, androidx.tv.material3.z2 r58, androidx.compose.foundation.interaction.MutableInteractionSource r59, r7.q<? super androidx.compose.foundation.layout.BoxScope, ? super androidx.compose.runtime.Composer, ? super java.lang.Integer, x6.t0> r60, androidx.compose.runtime.Composer r61, int r62, int r63, int r64) {
        /*
            Method dump skipped, instruction units count: 1523
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.tv.material3.SurfaceKt.m6009SurfacexYaah8o(boolean, r7.a, androidx.compose.ui.Modifier, boolean, r7.a, float, androidx.tv.material3.b3, androidx.tv.material3.y2, androidx.tv.material3.a3, androidx.tv.material3.x2, androidx.tv.material3.z2, androidx.compose.foundation.interaction.MutableInteractionSource, r7.q, androidx.compose.runtime.Composer, int, int, int):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:100:0x0114  */
    /* JADX WARN: Removed duplicated region for block: B:101:0x0117  */
    /* JADX WARN: Removed duplicated region for block: B:110:0x012b  */
    /* JADX WARN: Removed duplicated region for block: B:111:0x0132  */
    /* JADX WARN: Removed duplicated region for block: B:121:0x014c  */
    /* JADX WARN: Removed duplicated region for block: B:123:0x0151  */
    /* JADX WARN: Removed duplicated region for block: B:133:0x0172  */
    /* JADX WARN: Removed duplicated region for block: B:139:0x0186  */
    /* JADX WARN: Removed duplicated region for block: B:140:0x0188  */
    /* JADX WARN: Removed duplicated region for block: B:141:0x018a  */
    /* JADX WARN: Removed duplicated region for block: B:144:0x0191  */
    /* JADX WARN: Removed duplicated region for block: B:147:0x01a1  */
    /* JADX WARN: Removed duplicated region for block: B:151:0x01c0  */
    /* JADX WARN: Removed duplicated region for block: B:163:0x01f5  */
    /* JADX WARN: Removed duplicated region for block: B:164:0x01f8  */
    /* JADX WARN: Removed duplicated region for block: B:167:0x0252  */
    /* JADX WARN: Removed duplicated region for block: B:171:0x025c  */
    /* JADX WARN: Removed duplicated region for block: B:173:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:46:0x007e  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x0084  */
    /* JADX WARN: Removed duplicated region for block: B:57:0x009d  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x00a2  */
    /* JADX WARN: Removed duplicated region for block: B:67:0x00ba  */
    /* JADX WARN: Removed duplicated region for block: B:69:0x00bf  */
    /* JADX WARN: Removed duplicated region for block: B:78:0x00d8  */
    /* JADX WARN: Removed duplicated region for block: B:80:0x00dd  */
    /* JADX WARN: Removed duplicated region for block: B:89:0x00f6  */
    /* JADX WARN: Removed duplicated region for block: B:91:0x00fb  */
    /* JADX INFO: renamed from: SurfaceImpl-vf0GYmI, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void m6010SurfaceImplvf0GYmI(androidx.compose.ui.Modifier r24, boolean r25, boolean r26, androidx.compose.ui.graphics.Shape r27, long r28, long r30, float r32, androidx.tv.material3.d r33, androidx.tv.material3.s1 r34, float r35, androidx.compose.foundation.interaction.MutableInteractionSource r36, r7.q<? super androidx.compose.foundation.layout.BoxScope, ? super androidx.compose.runtime.Composer, ? super java.lang.Integer, x6.t0> r37, androidx.compose.runtime.Composer r38, int r39, int r40, int r41) {
        /*
            Method dump skipped, instruction units count: 646
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.tv.material3.SurfaceKt.m6010SurfaceImplvf0GYmI(androidx.compose.ui.Modifier, boolean, boolean, androidx.compose.ui.graphics.Shape, long, long, float, androidx.tv.material3.d, androidx.tv.material3.s1, float, androidx.compose.foundation.interaction.MutableInteractionSource, r7.q, androidx.compose.runtime.Composer, int, int, int):void");
    }

    /* JADX INFO: renamed from: surfaceColorAtElevation-CLU3JFs, reason: not valid java name */
    public static final long m6011surfaceColorAtElevationCLU3JFs(long j10, float f10, Composer composer, int i10) {
        composer.startReplaceableGroup(-2040884110);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-2040884110, i10, -1, "androidx.tv.material3.surfaceColorAtElevation (Surface.kt:571)");
        }
        MaterialTheme materialTheme = MaterialTheme.f5670a;
        if (Color.m3473equalsimpl0(j10, materialTheme.getColorScheme(composer, 6).w())) {
            h1 colorScheme = materialTheme.getColorScheme(composer, 6);
            ProvidableCompositionLocal providableCompositionLocal = ColorSchemeKt.f5609a;
            if (Dp.m5683equalsimpl0(f10, Dp.m5678constructorimpl(0))) {
                j10 = colorScheme.w();
            } else {
                j10 = ColorKt.m3517compositeOverOWjLjI(Color.m3471copywmQWz5c$default(colorScheme.x(), ((((float) Math.log(f10 + 1)) * 4.5f) + 2.0f) / 100.0f, 0.0f, 0.0f, 0.0f, 14, null), colorScheme.w());
            }
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        composer.endReplaceableGroup();
        return j10;
    }
}
