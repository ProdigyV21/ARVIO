package androidx.compose.material3;

import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalKt;
import androidx.compose.runtime.ProvidableCompositionLocal;
import androidx.compose.runtime.ProvidedValue;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.ui.text.TextStyle;
import androidx.media3.extractor.text.ttml.TtmlNode;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.p;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0088\u0001\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\u001aÒ\u0001\u0010#\u001a\u00020\u001d2\u0006\u0010\u0001\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\b\b\u0002\u0010\u0005\u001a\u00020\u00042\b\b\u0002\u0010\u0007\u001a\u00020\u00062\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\b2\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\n2\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\f2\b\b\u0002\u0010\u000e\u001a\u00020\u00062\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u000f2\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u00112\b\b\u0002\u0010\u0013\u001a\u00020\u00062\b\b\u0002\u0010\u0015\u001a\u00020\u00142\b\b\u0002\u0010\u0017\u001a\u00020\u00162\b\b\u0002\u0010\u0019\u001a\u00020\u00182\b\b\u0002\u0010\u001a\u001a\u00020\u00182\u0016\b\u0002\u0010\u001e\u001a\u0010\u0012\u0004\u0012\u00020\u001c\u0012\u0004\u0012\u00020\u001d\u0018\u00010\u001b2\b\b\u0002\u0010 \u001a\u00020\u001fH\u0007ø\u0001\u0000¢\u0006\u0004\b!\u0010\"\u001aÆ\u0001\u0010#\u001a\u00020\u001d2\u0006\u0010\u0001\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\b\b\u0002\u0010\u0005\u001a\u00020\u00042\b\b\u0002\u0010\u0007\u001a\u00020\u00062\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\b2\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\n2\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\f2\b\b\u0002\u0010\u000e\u001a\u00020\u00062\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u000f2\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u00112\b\b\u0002\u0010\u0013\u001a\u00020\u00062\b\b\u0002\u0010\u0015\u001a\u00020\u00142\b\b\u0002\u0010\u0017\u001a\u00020\u00162\b\b\u0002\u0010\u0019\u001a\u00020\u00182\u0014\b\u0002\u0010\u001e\u001a\u000e\u0012\u0004\u0012\u00020\u001c\u0012\u0004\u0012\u00020\u001d0\u001b2\b\b\u0002\u0010 \u001a\u00020\u001fH\u0007ø\u0001\u0000¢\u0006\u0004\b$\u0010%\u001aæ\u0001\u0010#\u001a\u00020\u001d2\u0006\u0010\u0001\u001a\u00020&2\b\b\u0002\u0010\u0003\u001a\u00020\u00022\b\b\u0002\u0010\u0005\u001a\u00020\u00042\b\b\u0002\u0010\u0007\u001a\u00020\u00062\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\b2\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\n2\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\f2\b\b\u0002\u0010\u000e\u001a\u00020\u00062\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u000f2\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u00112\b\b\u0002\u0010\u0013\u001a\u00020\u00062\b\b\u0002\u0010\u0015\u001a\u00020\u00142\b\b\u0002\u0010\u0017\u001a\u00020\u00162\b\b\u0002\u0010\u0019\u001a\u00020\u00182\b\b\u0002\u0010\u001a\u001a\u00020\u00182\u0014\b\u0002\u0010)\u001a\u000e\u0012\u0004\u0012\u00020\u0000\u0012\u0004\u0012\u00020(0'2\u0014\b\u0002\u0010\u001e\u001a\u000e\u0012\u0004\u0012\u00020\u001c\u0012\u0004\u0012\u00020\u001d0\u001b2\b\b\u0002\u0010 \u001a\u00020\u001fH\u0007ø\u0001\u0000¢\u0006\u0004\b*\u0010+\u001aÜ\u0001\u0010#\u001a\u00020\u001d2\u0006\u0010\u0001\u001a\u00020&2\b\b\u0002\u0010\u0003\u001a\u00020\u00022\b\b\u0002\u0010\u0005\u001a\u00020\u00042\b\b\u0002\u0010\u0007\u001a\u00020\u00062\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\b2\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\n2\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\f2\b\b\u0002\u0010\u000e\u001a\u00020\u00062\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u000f2\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u00112\b\b\u0002\u0010\u0013\u001a\u00020\u00062\b\b\u0002\u0010\u0015\u001a\u00020\u00142\b\b\u0002\u0010\u0017\u001a\u00020\u00162\b\b\u0002\u0010\u0019\u001a\u00020\u00182\u0014\b\u0002\u0010)\u001a\u000e\u0012\u0004\u0012\u00020\u0000\u0012\u0004\u0012\u00020(0'2\u0014\b\u0002\u0010\u001e\u001a\u000e\u0012\u0004\u0012\u00020\u001c\u0012\u0004\u0012\u00020\u001d0\u001b2\b\b\u0002\u0010 \u001a\u00020\u001fH\u0007ø\u0001\u0000¢\u0006\u0004\b!\u0010,\u001a*\u00101\u001a\u00020\u001d2\u0006\u0010-\u001a\u00020\u001f2\u0011\u00100\u001a\r\u0012\u0004\u0012\u00020\u001d0.¢\u0006\u0002\b/H\u0007¢\u0006\u0004\b1\u00102\"\u001d\u00104\u001a\b\u0012\u0004\u0012\u00020\u001f038\u0006¢\u0006\f\n\u0004\b4\u00105\u001a\u0004\b6\u00107\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u00068"}, d2 = {"", "text", "Landroidx/compose/ui/Modifier;", "modifier", "Landroidx/compose/ui/graphics/Color;", TtmlNode.ATTR_TTS_COLOR, "Landroidx/compose/ui/unit/TextUnit;", TtmlNode.ATTR_TTS_FONT_SIZE, "Landroidx/compose/ui/text/font/FontStyle;", TtmlNode.ATTR_TTS_FONT_STYLE, "Landroidx/compose/ui/text/font/FontWeight;", TtmlNode.ATTR_TTS_FONT_WEIGHT, "Landroidx/compose/ui/text/font/FontFamily;", TtmlNode.ATTR_TTS_FONT_FAMILY, "letterSpacing", "Landroidx/compose/ui/text/style/TextDecoration;", TtmlNode.ATTR_TTS_TEXT_DECORATION, "Landroidx/compose/ui/text/style/TextAlign;", TtmlNode.ATTR_TTS_TEXT_ALIGN, "lineHeight", "Landroidx/compose/ui/text/style/TextOverflow;", "overflow", "", "softWrap", "", "maxLines", "minLines", "Lkotlin/Function1;", "Landroidx/compose/ui/text/TextLayoutResult;", "Lx6/t0;", "onTextLayout", "Landroidx/compose/ui/text/TextStyle;", "style", "Text--4IGK_g", "(Ljava/lang/String;Landroidx/compose/ui/Modifier;JJLandroidx/compose/ui/text/font/FontStyle;Landroidx/compose/ui/text/font/FontWeight;Landroidx/compose/ui/text/font/FontFamily;JLandroidx/compose/ui/text/style/TextDecoration;Landroidx/compose/ui/text/style/TextAlign;JIZIILr7/l;Landroidx/compose/ui/text/TextStyle;Landroidx/compose/runtime/Composer;III)V", "Text", "Text-fLXpl1I", "(Ljava/lang/String;Landroidx/compose/ui/Modifier;JJLandroidx/compose/ui/text/font/FontStyle;Landroidx/compose/ui/text/font/FontWeight;Landroidx/compose/ui/text/font/FontFamily;JLandroidx/compose/ui/text/style/TextDecoration;Landroidx/compose/ui/text/style/TextAlign;JIZILr7/l;Landroidx/compose/ui/text/TextStyle;Landroidx/compose/runtime/Composer;III)V", "Landroidx/compose/ui/text/AnnotatedString;", "", "Landroidx/compose/foundation/text/InlineTextContent;", "inlineContent", "Text-IbK3jfQ", "(Landroidx/compose/ui/text/AnnotatedString;Landroidx/compose/ui/Modifier;JJLandroidx/compose/ui/text/font/FontStyle;Landroidx/compose/ui/text/font/FontWeight;Landroidx/compose/ui/text/font/FontFamily;JLandroidx/compose/ui/text/style/TextDecoration;Landroidx/compose/ui/text/style/TextAlign;JIZIILjava/util/Map;Lr7/l;Landroidx/compose/ui/text/TextStyle;Landroidx/compose/runtime/Composer;III)V", "(Landroidx/compose/ui/text/AnnotatedString;Landroidx/compose/ui/Modifier;JJLandroidx/compose/ui/text/font/FontStyle;Landroidx/compose/ui/text/font/FontWeight;Landroidx/compose/ui/text/font/FontFamily;JLandroidx/compose/ui/text/style/TextDecoration;Landroidx/compose/ui/text/style/TextAlign;JIZILjava/util/Map;Lr7/l;Landroidx/compose/ui/text/TextStyle;Landroidx/compose/runtime/Composer;III)V", "value", "Lkotlin/Function0;", "Landroidx/compose/runtime/Composable;", "content", "ProvideTextStyle", "(Landroidx/compose/ui/text/TextStyle;Lr7/p;Landroidx/compose/runtime/Composer;I)V", "Landroidx/compose/runtime/ProvidableCompositionLocal;", "LocalTextStyle", "Landroidx/compose/runtime/ProvidableCompositionLocal;", "getLocalTextStyle", "()Landroidx/compose/runtime/ProvidableCompositionLocal;", "material3_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class TextKt {
    private static final ProvidableCompositionLocal<TextStyle> LocalTextStyle = CompositionLocalKt.compositionLocalOf(SnapshotStateKt.structuralEqualityPolicy(), TextKt$LocalTextStyle$1.INSTANCE);

    /* JADX INFO: renamed from: androidx.compose.material3.TextKt$ProvideTextStyle$1, reason: invalid class name */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class AnonymousClass1 extends r implements p<Composer, Integer, t0> {
        final /* synthetic */ int $$changed;
        final /* synthetic */ p<Composer, Integer, t0> $content;
        final /* synthetic */ TextStyle $value;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public AnonymousClass1(TextStyle textStyle, p<? super Composer, ? super Integer, t0> pVar, int i10) {
            super(2);
            this.$value = textStyle;
            this.$content = pVar;
            this.$$changed = i10;
        }

        @Override // r7.p
        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
            invoke((Composer) obj, ((Number) obj2).intValue());
            return t0.f22605a;
        }

        public final void invoke(Composer composer, int i10) {
            TextKt.ProvideTextStyle(this.$value, this.$content, composer, RecomposeScopeImplKt.updateChangedFlags(this.$$changed | 1));
        }
    }

    public static final void ProvideTextStyle(TextStyle textStyle, p<? super Composer, ? super Integer, t0> pVar, Composer composer, int i10) {
        int i11;
        Composer composerStartRestartGroup = composer.startRestartGroup(-460300127);
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
                ComposerKt.traceEventStart(-460300127, i11, -1, "androidx.compose.material3.ProvideTextStyle (Text.kt:349)");
            }
            ProvidableCompositionLocal<TextStyle> providableCompositionLocal = LocalTextStyle;
            CompositionLocalKt.CompositionLocalProvider(providableCompositionLocal.provides(((TextStyle) composerStartRestartGroup.consume(providableCompositionLocal)).merge(textStyle)), pVar, composerStartRestartGroup, (i11 & 112) | ProvidedValue.$stable);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new AnonymousClass1(textStyle, pVar, i10));
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:100:0x0133  */
    /* JADX WARN: Removed duplicated region for block: B:102:0x013a  */
    /* JADX WARN: Removed duplicated region for block: B:112:0x015b  */
    /* JADX WARN: Removed duplicated region for block: B:113:0x0160  */
    /* JADX WARN: Removed duplicated region for block: B:123:0x017c  */
    /* JADX WARN: Removed duplicated region for block: B:125:0x0183  */
    /* JADX WARN: Removed duplicated region for block: B:135:0x01a2  */
    /* JADX WARN: Removed duplicated region for block: B:137:0x01a7  */
    /* JADX WARN: Removed duplicated region for block: B:146:0x01c0  */
    /* JADX WARN: Removed duplicated region for block: B:147:0x01c3  */
    /* JADX WARN: Removed duplicated region for block: B:156:0x01e2  */
    /* JADX WARN: Removed duplicated region for block: B:158:0x01e7  */
    /* JADX WARN: Removed duplicated region for block: B:166:0x01fe  */
    /* JADX WARN: Removed duplicated region for block: B:167:0x0203  */
    /* JADX WARN: Removed duplicated region for block: B:176:0x0218  */
    /* JADX WARN: Removed duplicated region for block: B:183:0x022c  */
    /* JADX WARN: Removed duplicated region for block: B:186:0x0238  */
    /* JADX WARN: Removed duplicated region for block: B:192:0x0269  */
    /* JADX WARN: Removed duplicated region for block: B:201:0x02a0  */
    /* JADX WARN: Removed duplicated region for block: B:202:0x02a2  */
    /* JADX WARN: Removed duplicated region for block: B:203:0x02a5  */
    /* JADX WARN: Removed duplicated region for block: B:205:0x02a9  */
    /* JADX WARN: Removed duplicated region for block: B:207:0x02b1  */
    /* JADX WARN: Removed duplicated region for block: B:210:0x02ba  */
    /* JADX WARN: Removed duplicated region for block: B:212:0x02bd  */
    /* JADX WARN: Removed duplicated region for block: B:214:0x02c0  */
    /* JADX WARN: Removed duplicated region for block: B:216:0x02c3  */
    /* JADX WARN: Removed duplicated region for block: B:217:0x02ca  */
    /* JADX WARN: Removed duplicated region for block: B:219:0x02ce  */
    /* JADX WARN: Removed duplicated region for block: B:220:0x02d0  */
    /* JADX WARN: Removed duplicated region for block: B:222:0x02d4  */
    /* JADX WARN: Removed duplicated region for block: B:223:0x02d6  */
    /* JADX WARN: Removed duplicated region for block: B:225:0x02da  */
    /* JADX WARN: Removed duplicated region for block: B:226:0x02e1  */
    /* JADX WARN: Removed duplicated region for block: B:228:0x02e5  */
    /* JADX WARN: Removed duplicated region for block: B:229:0x02ec  */
    /* JADX WARN: Removed duplicated region for block: B:232:0x02f2  */
    /* JADX WARN: Removed duplicated region for block: B:234:0x02f6  */
    /* JADX WARN: Removed duplicated region for block: B:235:0x02fa  */
    /* JADX WARN: Removed duplicated region for block: B:238:0x02ff  */
    /* JADX WARN: Removed duplicated region for block: B:241:0x0304  */
    /* JADX WARN: Removed duplicated region for block: B:244:0x030a  */
    /* JADX WARN: Removed duplicated region for block: B:246:0x0328  */
    /* JADX WARN: Removed duplicated region for block: B:249:0x0334  */
    /* JADX WARN: Removed duplicated region for block: B:252:0x034c  */
    /* JADX WARN: Removed duplicated region for block: B:253:0x034f  */
    /* JADX WARN: Removed duplicated region for block: B:260:0x037a  */
    /* JADX WARN: Removed duplicated region for block: B:262:0x0381  */
    /* JADX WARN: Removed duplicated region for block: B:265:0x03ef  */
    /* JADX WARN: Removed duplicated region for block: B:269:0x0412  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x004c  */
    /* JADX WARN: Removed duplicated region for block: B:271:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:27:0x0052  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x0071  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x0076  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x0091  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x0096  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x00b3  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x00b8  */
    /* JADX WARN: Removed duplicated region for block: B:68:0x00d1  */
    /* JADX WARN: Removed duplicated region for block: B:69:0x00d6  */
    /* JADX WARN: Removed duplicated region for block: B:78:0x00ef  */
    /* JADX WARN: Removed duplicated region for block: B:80:0x00f6  */
    /* JADX WARN: Removed duplicated region for block: B:89:0x0111  */
    /* JADX WARN: Removed duplicated region for block: B:91:0x0118  */
    /* JADX INFO: renamed from: Text--4IGK_g, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void m2153Text4IGK_g(java.lang.String r67, androidx.compose.ui.Modifier r68, long r69, long r71, androidx.compose.ui.text.font.FontStyle r73, androidx.compose.ui.text.font.FontWeight r74, androidx.compose.ui.text.font.FontFamily r75, long r76, androidx.compose.ui.text.style.TextDecoration r78, androidx.compose.ui.text.style.TextAlign r79, long r80, int r82, boolean r83, int r84, int r85, r7.l<? super androidx.compose.ui.text.TextLayoutResult, x6.t0> r86, androidx.compose.ui.text.TextStyle r87, androidx.compose.runtime.Composer r88, int r89, int r90, int r91) {
        /*
            Method dump skipped, instruction units count: 1064
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material3.TextKt.m2153Text4IGK_g(java.lang.String, androidx.compose.ui.Modifier, long, long, androidx.compose.ui.text.font.FontStyle, androidx.compose.ui.text.font.FontWeight, androidx.compose.ui.text.font.FontFamily, long, androidx.compose.ui.text.style.TextDecoration, androidx.compose.ui.text.style.TextAlign, long, int, boolean, int, int, r7.l, androidx.compose.ui.text.TextStyle, androidx.compose.runtime.Composer, int, int, int):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:108:0x0145  */
    /* JADX WARN: Removed duplicated region for block: B:109:0x014a  */
    /* JADX WARN: Removed duplicated region for block: B:119:0x0166  */
    /* JADX WARN: Removed duplicated region for block: B:121:0x016d  */
    /* JADX WARN: Removed duplicated region for block: B:131:0x018c  */
    /* JADX WARN: Removed duplicated region for block: B:133:0x0191  */
    /* JADX WARN: Removed duplicated region for block: B:142:0x01aa  */
    /* JADX WARN: Removed duplicated region for block: B:143:0x01ad  */
    /* JADX WARN: Removed duplicated region for block: B:152:0x01cc  */
    /* JADX WARN: Removed duplicated region for block: B:154:0x01d3  */
    /* JADX WARN: Removed duplicated region for block: B:162:0x01ec  */
    /* JADX WARN: Removed duplicated region for block: B:163:0x01f1  */
    /* JADX WARN: Removed duplicated region for block: B:172:0x0208  */
    /* JADX WARN: Removed duplicated region for block: B:173:0x020d  */
    /* JADX WARN: Removed duplicated region for block: B:182:0x0224  */
    /* JADX WARN: Removed duplicated region for block: B:189:0x0238  */
    /* JADX WARN: Removed duplicated region for block: B:192:0x0244  */
    /* JADX WARN: Removed duplicated region for block: B:198:0x0279  */
    /* JADX WARN: Removed duplicated region for block: B:207:0x02b7  */
    /* JADX WARN: Removed duplicated region for block: B:208:0x02b9  */
    /* JADX WARN: Removed duplicated region for block: B:209:0x02bc  */
    /* JADX WARN: Removed duplicated region for block: B:211:0x02c0  */
    /* JADX WARN: Removed duplicated region for block: B:212:0x02c7  */
    /* JADX WARN: Removed duplicated region for block: B:214:0x02cb  */
    /* JADX WARN: Removed duplicated region for block: B:217:0x02d4  */
    /* JADX WARN: Removed duplicated region for block: B:219:0x02d7  */
    /* JADX WARN: Removed duplicated region for block: B:221:0x02da  */
    /* JADX WARN: Removed duplicated region for block: B:223:0x02dd  */
    /* JADX WARN: Removed duplicated region for block: B:224:0x02e4  */
    /* JADX WARN: Removed duplicated region for block: B:226:0x02e8  */
    /* JADX WARN: Removed duplicated region for block: B:227:0x02ea  */
    /* JADX WARN: Removed duplicated region for block: B:230:0x02ef  */
    /* JADX WARN: Removed duplicated region for block: B:232:0x02f3  */
    /* JADX WARN: Removed duplicated region for block: B:233:0x02fa  */
    /* JADX WARN: Removed duplicated region for block: B:235:0x02fe  */
    /* JADX WARN: Removed duplicated region for block: B:236:0x0305  */
    /* JADX WARN: Removed duplicated region for block: B:239:0x030a  */
    /* JADX WARN: Removed duplicated region for block: B:241:0x030d  */
    /* JADX WARN: Removed duplicated region for block: B:242:0x0311  */
    /* JADX WARN: Removed duplicated region for block: B:245:0x0316  */
    /* JADX WARN: Removed duplicated region for block: B:247:0x031a  */
    /* JADX WARN: Removed duplicated region for block: B:248:0x031d  */
    /* JADX WARN: Removed duplicated region for block: B:250:0x0321  */
    /* JADX WARN: Removed duplicated region for block: B:251:0x0324  */
    /* JADX WARN: Removed duplicated region for block: B:254:0x032c  */
    /* JADX WARN: Removed duplicated region for block: B:255:0x034e  */
    /* JADX WARN: Removed duplicated region for block: B:258:0x0369  */
    /* JADX WARN: Removed duplicated region for block: B:261:0x0381  */
    /* JADX WARN: Removed duplicated region for block: B:262:0x0384  */
    /* JADX WARN: Removed duplicated region for block: B:269:0x03af  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x004c  */
    /* JADX WARN: Removed duplicated region for block: B:271:0x03b6  */
    /* JADX WARN: Removed duplicated region for block: B:274:0x042c  */
    /* JADX WARN: Removed duplicated region for block: B:278:0x0452  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x0051  */
    /* JADX WARN: Removed duplicated region for block: B:280:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:36:0x006c  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x0071  */
    /* JADX WARN: Removed duplicated region for block: B:46:0x008c  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x0091  */
    /* JADX WARN: Removed duplicated region for block: B:57:0x00ae  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x00b3  */
    /* JADX WARN: Removed duplicated region for block: B:67:0x00cc  */
    /* JADX WARN: Removed duplicated region for block: B:68:0x00d1  */
    /* JADX WARN: Removed duplicated region for block: B:77:0x00ea  */
    /* JADX WARN: Removed duplicated region for block: B:78:0x00ef  */
    /* JADX WARN: Removed duplicated region for block: B:87:0x0107  */
    /* JADX WARN: Removed duplicated region for block: B:88:0x010c  */
    /* JADX WARN: Removed duplicated region for block: B:97:0x0125  */
    /* JADX WARN: Removed duplicated region for block: B:99:0x012c  */
    /* JADX INFO: renamed from: Text-IbK3jfQ, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void m2154TextIbK3jfQ(androidx.compose.ui.text.AnnotatedString r60, androidx.compose.ui.Modifier r61, long r62, long r64, androidx.compose.ui.text.font.FontStyle r66, androidx.compose.ui.text.font.FontWeight r67, androidx.compose.ui.text.font.FontFamily r68, long r69, androidx.compose.ui.text.style.TextDecoration r71, androidx.compose.ui.text.style.TextAlign r72, long r73, int r75, boolean r76, int r77, int r78, java.util.Map<java.lang.String, androidx.compose.foundation.text.InlineTextContent> r79, r7.l<? super androidx.compose.ui.text.TextLayoutResult, x6.t0> r80, androidx.compose.ui.text.TextStyle r81, androidx.compose.runtime.Composer r82, int r83, int r84, int r85) {
        /*
            Method dump skipped, instruction units count: 1133
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material3.TextKt.m2154TextIbK3jfQ(androidx.compose.ui.text.AnnotatedString, androidx.compose.ui.Modifier, long, long, androidx.compose.ui.text.font.FontStyle, androidx.compose.ui.text.font.FontWeight, androidx.compose.ui.text.font.FontFamily, long, androidx.compose.ui.text.style.TextDecoration, androidx.compose.ui.text.style.TextAlign, long, int, boolean, int, int, java.util.Map, r7.l, androidx.compose.ui.text.TextStyle, androidx.compose.runtime.Composer, int, int, int):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:100:0x0133  */
    /* JADX WARN: Removed duplicated region for block: B:102:0x013a  */
    /* JADX WARN: Removed duplicated region for block: B:112:0x015b  */
    /* JADX WARN: Removed duplicated region for block: B:113:0x0160  */
    /* JADX WARN: Removed duplicated region for block: B:123:0x017c  */
    /* JADX WARN: Removed duplicated region for block: B:125:0x0183  */
    /* JADX WARN: Removed duplicated region for block: B:135:0x01a2  */
    /* JADX WARN: Removed duplicated region for block: B:137:0x01a7  */
    /* JADX WARN: Removed duplicated region for block: B:146:0x01c0  */
    /* JADX WARN: Removed duplicated region for block: B:147:0x01c3  */
    /* JADX WARN: Removed duplicated region for block: B:156:0x01e2  */
    /* JADX WARN: Removed duplicated region for block: B:158:0x01e7  */
    /* JADX WARN: Removed duplicated region for block: B:166:0x01fe  */
    /* JADX WARN: Removed duplicated region for block: B:172:0x020f  */
    /* JADX WARN: Removed duplicated region for block: B:175:0x021b  */
    /* JADX WARN: Removed duplicated region for block: B:181:0x024b  */
    /* JADX WARN: Removed duplicated region for block: B:190:0x027e  */
    /* JADX WARN: Removed duplicated region for block: B:191:0x0280  */
    /* JADX WARN: Removed duplicated region for block: B:192:0x0283  */
    /* JADX WARN: Removed duplicated region for block: B:194:0x0287  */
    /* JADX WARN: Removed duplicated region for block: B:196:0x028f  */
    /* JADX WARN: Removed duplicated region for block: B:199:0x0298  */
    /* JADX WARN: Removed duplicated region for block: B:201:0x029b  */
    /* JADX WARN: Removed duplicated region for block: B:203:0x029e  */
    /* JADX WARN: Removed duplicated region for block: B:205:0x02a1  */
    /* JADX WARN: Removed duplicated region for block: B:206:0x02a8  */
    /* JADX WARN: Removed duplicated region for block: B:208:0x02ac  */
    /* JADX WARN: Removed duplicated region for block: B:209:0x02ae  */
    /* JADX WARN: Removed duplicated region for block: B:212:0x02b3  */
    /* JADX WARN: Removed duplicated region for block: B:214:0x02b7  */
    /* JADX WARN: Removed duplicated region for block: B:215:0x02be  */
    /* JADX WARN: Removed duplicated region for block: B:217:0x02c2  */
    /* JADX WARN: Removed duplicated region for block: B:218:0x02c9  */
    /* JADX WARN: Removed duplicated region for block: B:220:0x02cd  */
    /* JADX WARN: Removed duplicated region for block: B:222:0x02d0  */
    /* JADX WARN: Removed duplicated region for block: B:223:0x02d4  */
    /* JADX WARN: Removed duplicated region for block: B:225:0x02d8  */
    /* JADX WARN: Removed duplicated region for block: B:226:0x02db  */
    /* JADX WARN: Removed duplicated region for block: B:229:0x02e3  */
    /* JADX WARN: Removed duplicated region for block: B:231:0x030d  */
    /* JADX WARN: Removed duplicated region for block: B:234:0x0320  */
    /* JADX WARN: Removed duplicated region for block: B:237:0x0356  */
    /* JADX WARN: Removed duplicated region for block: B:241:0x0375  */
    /* JADX WARN: Removed duplicated region for block: B:243:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:26:0x004c  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x0052  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x0071  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x0076  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x0091  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x0096  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x00b3  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x00b8  */
    /* JADX WARN: Removed duplicated region for block: B:68:0x00d1  */
    /* JADX WARN: Removed duplicated region for block: B:69:0x00d6  */
    /* JADX WARN: Removed duplicated region for block: B:78:0x00ef  */
    /* JADX WARN: Removed duplicated region for block: B:80:0x00f6  */
    /* JADX WARN: Removed duplicated region for block: B:89:0x0111  */
    /* JADX WARN: Removed duplicated region for block: B:91:0x0118  */
    @x6.e
    /* JADX INFO: renamed from: Text-fLXpl1I, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final /* synthetic */ void m2155TextfLXpl1I(java.lang.String r42, androidx.compose.ui.Modifier r43, long r44, long r46, androidx.compose.ui.text.font.FontStyle r48, androidx.compose.ui.text.font.FontWeight r49, androidx.compose.ui.text.font.FontFamily r50, long r51, androidx.compose.ui.text.style.TextDecoration r53, androidx.compose.ui.text.style.TextAlign r54, long r55, int r57, boolean r58, int r59, r7.l r60, androidx.compose.ui.text.TextStyle r61, androidx.compose.runtime.Composer r62, int r63, int r64, int r65) {
        /*
            Method dump skipped, instruction units count: 907
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material3.TextKt.m2155TextfLXpl1I(java.lang.String, androidx.compose.ui.Modifier, long, long, androidx.compose.ui.text.font.FontStyle, androidx.compose.ui.text.font.FontWeight, androidx.compose.ui.text.font.FontFamily, long, androidx.compose.ui.text.style.TextDecoration, androidx.compose.ui.text.style.TextAlign, long, int, boolean, int, r7.l, androidx.compose.ui.text.TextStyle, androidx.compose.runtime.Composer, int, int, int):void");
    }

    public static final ProvidableCompositionLocal<TextStyle> getLocalTextStyle() {
        return LocalTextStyle;
    }

    /* JADX WARN: Removed duplicated region for block: B:100:0x0133  */
    /* JADX WARN: Removed duplicated region for block: B:102:0x013a  */
    /* JADX WARN: Removed duplicated region for block: B:112:0x015b  */
    /* JADX WARN: Removed duplicated region for block: B:113:0x0160  */
    /* JADX WARN: Removed duplicated region for block: B:123:0x017c  */
    /* JADX WARN: Removed duplicated region for block: B:125:0x0183  */
    /* JADX WARN: Removed duplicated region for block: B:135:0x01a2  */
    /* JADX WARN: Removed duplicated region for block: B:137:0x01a7  */
    /* JADX WARN: Removed duplicated region for block: B:146:0x01c0  */
    /* JADX WARN: Removed duplicated region for block: B:147:0x01c3  */
    /* JADX WARN: Removed duplicated region for block: B:156:0x01e2  */
    /* JADX WARN: Removed duplicated region for block: B:158:0x01e7  */
    /* JADX WARN: Removed duplicated region for block: B:166:0x01fe  */
    /* JADX WARN: Removed duplicated region for block: B:167:0x0203  */
    /* JADX WARN: Removed duplicated region for block: B:176:0x0218  */
    /* JADX WARN: Removed duplicated region for block: B:183:0x022c  */
    /* JADX WARN: Removed duplicated region for block: B:186:0x0238  */
    /* JADX WARN: Removed duplicated region for block: B:192:0x026a  */
    /* JADX WARN: Removed duplicated region for block: B:201:0x029f  */
    /* JADX WARN: Removed duplicated region for block: B:202:0x02a1  */
    /* JADX WARN: Removed duplicated region for block: B:203:0x02a4  */
    /* JADX WARN: Removed duplicated region for block: B:205:0x02a8  */
    /* JADX WARN: Removed duplicated region for block: B:207:0x02b0  */
    /* JADX WARN: Removed duplicated region for block: B:210:0x02b9  */
    /* JADX WARN: Removed duplicated region for block: B:212:0x02bc  */
    /* JADX WARN: Removed duplicated region for block: B:214:0x02bf  */
    /* JADX WARN: Removed duplicated region for block: B:216:0x02c2  */
    /* JADX WARN: Removed duplicated region for block: B:217:0x02c9  */
    /* JADX WARN: Removed duplicated region for block: B:219:0x02cd  */
    /* JADX WARN: Removed duplicated region for block: B:220:0x02cf  */
    /* JADX WARN: Removed duplicated region for block: B:223:0x02d4  */
    /* JADX WARN: Removed duplicated region for block: B:225:0x02d8  */
    /* JADX WARN: Removed duplicated region for block: B:226:0x02df  */
    /* JADX WARN: Removed duplicated region for block: B:228:0x02e3  */
    /* JADX WARN: Removed duplicated region for block: B:229:0x02ea  */
    /* JADX WARN: Removed duplicated region for block: B:231:0x02ee  */
    /* JADX WARN: Removed duplicated region for block: B:233:0x02f1  */
    /* JADX WARN: Removed duplicated region for block: B:234:0x02f5  */
    /* JADX WARN: Removed duplicated region for block: B:236:0x02f9  */
    /* JADX WARN: Removed duplicated region for block: B:237:0x02fc  */
    /* JADX WARN: Removed duplicated region for block: B:239:0x0300  */
    /* JADX WARN: Removed duplicated region for block: B:240:0x0303  */
    /* JADX WARN: Removed duplicated region for block: B:243:0x030b  */
    /* JADX WARN: Removed duplicated region for block: B:245:0x0339  */
    /* JADX WARN: Removed duplicated region for block: B:248:0x0345  */
    /* JADX WARN: Removed duplicated region for block: B:251:0x037f  */
    /* JADX WARN: Removed duplicated region for block: B:255:0x03a0  */
    /* JADX WARN: Removed duplicated region for block: B:257:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:26:0x004c  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x0052  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x0071  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x0076  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x0091  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x0096  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x00b3  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x00b8  */
    /* JADX WARN: Removed duplicated region for block: B:68:0x00d1  */
    /* JADX WARN: Removed duplicated region for block: B:69:0x00d6  */
    /* JADX WARN: Removed duplicated region for block: B:78:0x00ef  */
    /* JADX WARN: Removed duplicated region for block: B:80:0x00f6  */
    /* JADX WARN: Removed duplicated region for block: B:89:0x0111  */
    /* JADX WARN: Removed duplicated region for block: B:91:0x0118  */
    @x6.e
    /* JADX INFO: renamed from: Text--4IGK_g, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final /* synthetic */ void m2152Text4IGK_g(androidx.compose.ui.text.AnnotatedString r44, androidx.compose.ui.Modifier r45, long r46, long r48, androidx.compose.ui.text.font.FontStyle r50, androidx.compose.ui.text.font.FontWeight r51, androidx.compose.ui.text.font.FontFamily r52, long r53, androidx.compose.ui.text.style.TextDecoration r55, androidx.compose.ui.text.style.TextAlign r56, long r57, int r59, boolean r60, int r61, java.util.Map r62, r7.l r63, androidx.compose.ui.text.TextStyle r64, androidx.compose.runtime.Composer r65, int r66, int r67, int r68) {
        /*
            Method dump skipped, instruction units count: 950
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material3.TextKt.m2152Text4IGK_g(androidx.compose.ui.text.AnnotatedString, androidx.compose.ui.Modifier, long, long, androidx.compose.ui.text.font.FontStyle, androidx.compose.ui.text.font.FontWeight, androidx.compose.ui.text.font.FontFamily, long, androidx.compose.ui.text.style.TextDecoration, androidx.compose.ui.text.style.TextAlign, long, int, boolean, int, java.util.Map, r7.l, androidx.compose.ui.text.TextStyle, androidx.compose.runtime.Composer, int, int, int):void");
    }
}
