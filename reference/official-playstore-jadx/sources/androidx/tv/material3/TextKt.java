package androidx.tv.material3;

import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalKt;
import androidx.compose.runtime.ProvidableCompositionLocal;
import androidx.compose.runtime.ProvidedValue;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.ui.text.TextStyle;
import androidx.media3.extractor.text.ttml.TtmlNode;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\u0080\u0001\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u001aÐ\u0001\u0010#\u001a\u00020\u001d2\u0006\u0010\u0001\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\b\b\u0002\u0010\u0005\u001a\u00020\u00042\b\b\u0002\u0010\u0007\u001a\u00020\u00062\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\b2\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\n2\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\f2\b\b\u0002\u0010\u000e\u001a\u00020\u00062\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u000f2\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u00112\b\b\u0002\u0010\u0013\u001a\u00020\u00062\b\b\u0002\u0010\u0015\u001a\u00020\u00142\b\b\u0002\u0010\u0017\u001a\u00020\u00162\b\b\u0002\u0010\u0019\u001a\u00020\u00182\b\b\u0002\u0010\u001a\u001a\u00020\u00182\u0014\b\u0002\u0010\u001e\u001a\u000e\u0012\u0004\u0012\u00020\u001c\u0012\u0004\u0012\u00020\u001d0\u001b2\b\b\u0002\u0010 \u001a\u00020\u001fH\u0007ø\u0001\u0000¢\u0006\u0004\b!\u0010\"\u001aæ\u0001\u0010#\u001a\u00020\u001d2\u0006\u0010\u0001\u001a\u00020$2\b\b\u0002\u0010\u0003\u001a\u00020\u00022\b\b\u0002\u0010\u0005\u001a\u00020\u00042\b\b\u0002\u0010\u0007\u001a\u00020\u00062\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\b2\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\n2\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\f2\b\b\u0002\u0010\u000e\u001a\u00020\u00062\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u000f2\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u00112\b\b\u0002\u0010\u0013\u001a\u00020\u00062\b\b\u0002\u0010\u0015\u001a\u00020\u00142\b\b\u0002\u0010\u0017\u001a\u00020\u00162\b\b\u0002\u0010\u0019\u001a\u00020\u00182\b\b\u0002\u0010\u001a\u001a\u00020\u00182\u0014\b\u0002\u0010'\u001a\u000e\u0012\u0004\u0012\u00020\u0000\u0012\u0004\u0012\u00020&0%2\u0014\b\u0002\u0010\u001e\u001a\u000e\u0012\u0004\u0012\u00020\u001c\u0012\u0004\u0012\u00020\u001d0\u001b2\b\b\u0002\u0010 \u001a\u00020\u001fH\u0007ø\u0001\u0000¢\u0006\u0004\b(\u0010)\u001a*\u0010.\u001a\u00020\u001d2\u0006\u0010*\u001a\u00020\u001f2\u0011\u0010-\u001a\r\u0012\u0004\u0012\u00020\u001d0+¢\u0006\u0002\b,H\u0007¢\u0006\u0004\b.\u0010/\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u00060"}, d2 = {"", "text", "Landroidx/compose/ui/Modifier;", "modifier", "Landroidx/compose/ui/graphics/Color;", TtmlNode.ATTR_TTS_COLOR, "Landroidx/compose/ui/unit/TextUnit;", TtmlNode.ATTR_TTS_FONT_SIZE, "Landroidx/compose/ui/text/font/FontStyle;", TtmlNode.ATTR_TTS_FONT_STYLE, "Landroidx/compose/ui/text/font/FontWeight;", TtmlNode.ATTR_TTS_FONT_WEIGHT, "Landroidx/compose/ui/text/font/FontFamily;", TtmlNode.ATTR_TTS_FONT_FAMILY, "letterSpacing", "Landroidx/compose/ui/text/style/TextDecoration;", TtmlNode.ATTR_TTS_TEXT_DECORATION, "Landroidx/compose/ui/text/style/TextAlign;", TtmlNode.ATTR_TTS_TEXT_ALIGN, "lineHeight", "Landroidx/compose/ui/text/style/TextOverflow;", "overflow", "", "softWrap", "", "maxLines", "minLines", "Lkotlin/Function1;", "Landroidx/compose/ui/text/TextLayoutResult;", "Lx6/t0;", "onTextLayout", "Landroidx/compose/ui/text/TextStyle;", "style", "Text--4IGK_g", "(Ljava/lang/String;Landroidx/compose/ui/Modifier;JJLandroidx/compose/ui/text/font/FontStyle;Landroidx/compose/ui/text/font/FontWeight;Landroidx/compose/ui/text/font/FontFamily;JLandroidx/compose/ui/text/style/TextDecoration;Landroidx/compose/ui/text/style/TextAlign;JIZIILr7/l;Landroidx/compose/ui/text/TextStyle;Landroidx/compose/runtime/Composer;III)V", "Text", "Landroidx/compose/ui/text/AnnotatedString;", "", "Landroidx/compose/foundation/text/InlineTextContent;", "inlineContent", "Text-IbK3jfQ", "(Landroidx/compose/ui/text/AnnotatedString;Landroidx/compose/ui/Modifier;JJLandroidx/compose/ui/text/font/FontStyle;Landroidx/compose/ui/text/font/FontWeight;Landroidx/compose/ui/text/font/FontFamily;JLandroidx/compose/ui/text/style/TextDecoration;Landroidx/compose/ui/text/style/TextAlign;JIZIILjava/util/Map;Lr7/l;Landroidx/compose/ui/text/TextStyle;Landroidx/compose/runtime/Composer;III)V", "value", "Lkotlin/Function0;", "Landroidx/compose/runtime/Composable;", "content", "ProvideTextStyle", "(Landroidx/compose/ui/text/TextStyle;Lr7/p;Landroidx/compose/runtime/Composer;I)V", "tv-material_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class TextKt {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final ProvidableCompositionLocal f5779a = CompositionLocalKt.compositionLocalOf(SnapshotStateKt.structuralEqualityPolicy(), i1.f6095p);

    public static final void ProvideTextStyle(TextStyle textStyle, r7.p<? super Composer, ? super Integer, x6.t0> pVar, Composer composer, int i10) {
        int i11;
        Composer composerStartRestartGroup = composer.startRestartGroup(1119446463);
        if ((i10 & 6) == 0) {
            i11 = (composerStartRestartGroup.changed(textStyle) ? 4 : 2) | i10;
        } else {
            i11 = i10;
        }
        if ((i10 & 48) == 0) {
            i11 |= composerStartRestartGroup.changedInstance(pVar) ? 32 : 16;
        }
        if ((i11 & 19) == 18 && composerStartRestartGroup.getSkipping()) {
            composerStartRestartGroup.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1119446463, i11, -1, "androidx.tv.material3.ProvideTextStyle (Text.kt:252)");
            }
            ProvidableCompositionLocal providableCompositionLocal = f5779a;
            CompositionLocalKt.CompositionLocalProvider((ProvidedValue<?>) providableCompositionLocal.provides(((TextStyle) composerStartRestartGroup.consume(providableCompositionLocal)).merge(textStyle)), pVar, composerStartRestartGroup, (i11 & 112) | ProvidedValue.$stable);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new androidx.navigation.compose.n(i10, 4, textStyle, pVar));
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:100:0x0137  */
    /* JADX WARN: Removed duplicated region for block: B:102:0x013e  */
    /* JADX WARN: Removed duplicated region for block: B:112:0x015f  */
    /* JADX WARN: Removed duplicated region for block: B:113:0x0164  */
    /* JADX WARN: Removed duplicated region for block: B:123:0x0180  */
    /* JADX WARN: Removed duplicated region for block: B:125:0x0187  */
    /* JADX WARN: Removed duplicated region for block: B:135:0x01a6  */
    /* JADX WARN: Removed duplicated region for block: B:137:0x01ab  */
    /* JADX WARN: Removed duplicated region for block: B:146:0x01c4  */
    /* JADX WARN: Removed duplicated region for block: B:147:0x01c7  */
    /* JADX WARN: Removed duplicated region for block: B:156:0x01e6  */
    /* JADX WARN: Removed duplicated region for block: B:158:0x01eb  */
    /* JADX WARN: Removed duplicated region for block: B:166:0x0202  */
    /* JADX WARN: Removed duplicated region for block: B:167:0x0207  */
    /* JADX WARN: Removed duplicated region for block: B:176:0x021c  */
    /* JADX WARN: Removed duplicated region for block: B:182:0x022d  */
    /* JADX WARN: Removed duplicated region for block: B:185:0x0239  */
    /* JADX WARN: Removed duplicated region for block: B:191:0x026a  */
    /* JADX WARN: Removed duplicated region for block: B:200:0x02a1  */
    /* JADX WARN: Removed duplicated region for block: B:201:0x02a3  */
    /* JADX WARN: Removed duplicated region for block: B:202:0x02a6  */
    /* JADX WARN: Removed duplicated region for block: B:204:0x02aa  */
    /* JADX WARN: Removed duplicated region for block: B:206:0x02b2  */
    /* JADX WARN: Removed duplicated region for block: B:209:0x02bb  */
    /* JADX WARN: Removed duplicated region for block: B:211:0x02be  */
    /* JADX WARN: Removed duplicated region for block: B:213:0x02c1  */
    /* JADX WARN: Removed duplicated region for block: B:215:0x02c4  */
    /* JADX WARN: Removed duplicated region for block: B:216:0x02cb  */
    /* JADX WARN: Removed duplicated region for block: B:218:0x02cf  */
    /* JADX WARN: Removed duplicated region for block: B:219:0x02d1  */
    /* JADX WARN: Removed duplicated region for block: B:222:0x02d6  */
    /* JADX WARN: Removed duplicated region for block: B:224:0x02da  */
    /* JADX WARN: Removed duplicated region for block: B:225:0x02e1  */
    /* JADX WARN: Removed duplicated region for block: B:227:0x02e5  */
    /* JADX WARN: Removed duplicated region for block: B:228:0x02ec  */
    /* JADX WARN: Removed duplicated region for block: B:231:0x02f2  */
    /* JADX WARN: Removed duplicated region for block: B:233:0x02f6  */
    /* JADX WARN: Removed duplicated region for block: B:234:0x02fa  */
    /* JADX WARN: Removed duplicated region for block: B:237:0x02ff  */
    /* JADX WARN: Removed duplicated region for block: B:239:0x0303  */
    /* JADX WARN: Removed duplicated region for block: B:240:0x0306  */
    /* JADX WARN: Removed duplicated region for block: B:243:0x030c  */
    /* JADX WARN: Removed duplicated region for block: B:245:0x0329  */
    /* JADX WARN: Removed duplicated region for block: B:248:0x0335  */
    /* JADX WARN: Removed duplicated region for block: B:251:0x034d  */
    /* JADX WARN: Removed duplicated region for block: B:252:0x0350  */
    /* JADX WARN: Removed duplicated region for block: B:259:0x0379  */
    /* JADX WARN: Removed duplicated region for block: B:261:0x0380  */
    /* JADX WARN: Removed duplicated region for block: B:264:0x03ee  */
    /* JADX WARN: Removed duplicated region for block: B:268:0x0412  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x004c  */
    /* JADX WARN: Removed duplicated region for block: B:270:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:27:0x0052  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x0071  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x0076  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x0091  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x0096  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x00b3  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x00b8  */
    /* JADX WARN: Removed duplicated region for block: B:68:0x00d5  */
    /* JADX WARN: Removed duplicated region for block: B:69:0x00da  */
    /* JADX WARN: Removed duplicated region for block: B:78:0x00f3  */
    /* JADX WARN: Removed duplicated region for block: B:80:0x00fa  */
    /* JADX WARN: Removed duplicated region for block: B:89:0x0115  */
    /* JADX WARN: Removed duplicated region for block: B:91:0x011c  */
    /* JADX INFO: renamed from: Text--4IGK_g, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void m6020Text4IGK_g(java.lang.String r69, androidx.compose.ui.Modifier r70, long r71, long r73, androidx.compose.ui.text.font.FontStyle r75, androidx.compose.ui.text.font.FontWeight r76, androidx.compose.ui.text.font.FontFamily r77, long r78, androidx.compose.ui.text.style.TextDecoration r80, androidx.compose.ui.text.style.TextAlign r81, long r82, int r84, boolean r85, int r86, int r87, r7.l<? super androidx.compose.ui.text.TextLayoutResult, x6.t0> r88, androidx.compose.ui.text.TextStyle r89, androidx.compose.runtime.Composer r90, int r91, int r92, int r93) {
        /*
            Method dump skipped, instruction units count: 1064
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.tv.material3.TextKt.m6020Text4IGK_g(java.lang.String, androidx.compose.ui.Modifier, long, long, androidx.compose.ui.text.font.FontStyle, androidx.compose.ui.text.font.FontWeight, androidx.compose.ui.text.font.FontFamily, long, androidx.compose.ui.text.style.TextDecoration, androidx.compose.ui.text.style.TextAlign, long, int, boolean, int, int, r7.l, androidx.compose.ui.text.TextStyle, androidx.compose.runtime.Composer, int, int, int):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:100:0x013b  */
    /* JADX WARN: Removed duplicated region for block: B:102:0x0142  */
    /* JADX WARN: Removed duplicated region for block: B:112:0x0163  */
    /* JADX WARN: Removed duplicated region for block: B:113:0x0168  */
    /* JADX WARN: Removed duplicated region for block: B:123:0x0184  */
    /* JADX WARN: Removed duplicated region for block: B:125:0x018b  */
    /* JADX WARN: Removed duplicated region for block: B:135:0x01aa  */
    /* JADX WARN: Removed duplicated region for block: B:137:0x01af  */
    /* JADX WARN: Removed duplicated region for block: B:146:0x01c8  */
    /* JADX WARN: Removed duplicated region for block: B:147:0x01cb  */
    /* JADX WARN: Removed duplicated region for block: B:156:0x01ea  */
    /* JADX WARN: Removed duplicated region for block: B:158:0x01ef  */
    /* JADX WARN: Removed duplicated region for block: B:166:0x0206  */
    /* JADX WARN: Removed duplicated region for block: B:167:0x020b  */
    /* JADX WARN: Removed duplicated region for block: B:176:0x0222  */
    /* JADX WARN: Removed duplicated region for block: B:177:0x0227  */
    /* JADX WARN: Removed duplicated region for block: B:185:0x023b  */
    /* JADX WARN: Removed duplicated region for block: B:191:0x024c  */
    /* JADX WARN: Removed duplicated region for block: B:194:0x0258  */
    /* JADX WARN: Removed duplicated region for block: B:200:0x028b  */
    /* JADX WARN: Removed duplicated region for block: B:209:0x02c4  */
    /* JADX WARN: Removed duplicated region for block: B:210:0x02c6  */
    /* JADX WARN: Removed duplicated region for block: B:211:0x02c9  */
    /* JADX WARN: Removed duplicated region for block: B:213:0x02cd  */
    /* JADX WARN: Removed duplicated region for block: B:215:0x02d5  */
    /* JADX WARN: Removed duplicated region for block: B:218:0x02de  */
    /* JADX WARN: Removed duplicated region for block: B:220:0x02e1  */
    /* JADX WARN: Removed duplicated region for block: B:222:0x02e4  */
    /* JADX WARN: Removed duplicated region for block: B:224:0x02e7  */
    /* JADX WARN: Removed duplicated region for block: B:225:0x02ee  */
    /* JADX WARN: Removed duplicated region for block: B:227:0x02f2  */
    /* JADX WARN: Removed duplicated region for block: B:228:0x02f4  */
    /* JADX WARN: Removed duplicated region for block: B:231:0x02f9  */
    /* JADX WARN: Removed duplicated region for block: B:233:0x02fd  */
    /* JADX WARN: Removed duplicated region for block: B:234:0x0304  */
    /* JADX WARN: Removed duplicated region for block: B:236:0x0308  */
    /* JADX WARN: Removed duplicated region for block: B:237:0x030f  */
    /* JADX WARN: Removed duplicated region for block: B:240:0x0315  */
    /* JADX WARN: Removed duplicated region for block: B:242:0x0319  */
    /* JADX WARN: Removed duplicated region for block: B:243:0x031d  */
    /* JADX WARN: Removed duplicated region for block: B:246:0x0322  */
    /* JADX WARN: Removed duplicated region for block: B:248:0x0326  */
    /* JADX WARN: Removed duplicated region for block: B:249:0x0329  */
    /* JADX WARN: Removed duplicated region for block: B:251:0x032d  */
    /* JADX WARN: Removed duplicated region for block: B:252:0x0330  */
    /* JADX WARN: Removed duplicated region for block: B:255:0x0336  */
    /* JADX WARN: Removed duplicated region for block: B:257:0x0353  */
    /* JADX WARN: Removed duplicated region for block: B:260:0x035f  */
    /* JADX WARN: Removed duplicated region for block: B:263:0x0377  */
    /* JADX WARN: Removed duplicated region for block: B:264:0x037a  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x004c  */
    /* JADX WARN: Removed duplicated region for block: B:271:0x03a3  */
    /* JADX WARN: Removed duplicated region for block: B:273:0x03aa  */
    /* JADX WARN: Removed duplicated region for block: B:276:0x041e  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x0052  */
    /* JADX WARN: Removed duplicated region for block: B:280:0x0444  */
    /* JADX WARN: Removed duplicated region for block: B:282:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:37:0x0071  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x0076  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x0091  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x0096  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x00b3  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x00b8  */
    /* JADX WARN: Removed duplicated region for block: B:68:0x00d5  */
    /* JADX WARN: Removed duplicated region for block: B:69:0x00da  */
    /* JADX WARN: Removed duplicated region for block: B:78:0x00f7  */
    /* JADX WARN: Removed duplicated region for block: B:80:0x00fe  */
    /* JADX WARN: Removed duplicated region for block: B:89:0x0119  */
    /* JADX WARN: Removed duplicated region for block: B:91:0x0120  */
    /* JADX INFO: renamed from: Text-IbK3jfQ, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void m6021TextIbK3jfQ(androidx.compose.ui.text.AnnotatedString r69, androidx.compose.ui.Modifier r70, long r71, long r73, androidx.compose.ui.text.font.FontStyle r75, androidx.compose.ui.text.font.FontWeight r76, androidx.compose.ui.text.font.FontFamily r77, long r78, androidx.compose.ui.text.style.TextDecoration r80, androidx.compose.ui.text.style.TextAlign r81, long r82, int r84, boolean r85, int r86, int r87, java.util.Map<java.lang.String, androidx.compose.foundation.text.InlineTextContent> r88, r7.l<? super androidx.compose.ui.text.TextLayoutResult, x6.t0> r89, androidx.compose.ui.text.TextStyle r90, androidx.compose.runtime.Composer r91, int r92, int r93, int r94) {
        /*
            Method dump skipped, instruction units count: 1114
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.tv.material3.TextKt.m6021TextIbK3jfQ(androidx.compose.ui.text.AnnotatedString, androidx.compose.ui.Modifier, long, long, androidx.compose.ui.text.font.FontStyle, androidx.compose.ui.text.font.FontWeight, androidx.compose.ui.text.font.FontFamily, long, androidx.compose.ui.text.style.TextDecoration, androidx.compose.ui.text.style.TextAlign, long, int, boolean, int, int, java.util.Map, r7.l, androidx.compose.ui.text.TextStyle, androidx.compose.runtime.Composer, int, int, int):void");
    }
}
