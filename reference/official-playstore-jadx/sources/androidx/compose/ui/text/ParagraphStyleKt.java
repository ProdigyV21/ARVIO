package androidx.compose.ui.text;

import androidx.compose.ui.text.style.Hyphens;
import androidx.compose.ui.text.style.LineBreak;
import androidx.compose.ui.text.style.LineHeightStyle;
import androidx.compose.ui.text.style.TextAlign;
import androidx.compose.ui.text.style.TextDirection;
import androidx.compose.ui.text.style.TextIndent;
import androidx.compose.ui.text.style.TextIndentKt;
import androidx.compose.ui.text.style.TextMotion;
import androidx.compose.ui.unit.LayoutDirection;
import androidx.compose.ui.unit.TextUnit;
import androidx.compose.ui.unit.TextUnitKt;
import androidx.media3.extractor.text.ttml.TtmlNode;
import kotlin.Metadata;
import kotlin.jvm.internal.h;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000X\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\u001a \u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\bH\u0007\u001a&\u0010\t\u001a\u0004\u0018\u00010\n2\b\u0010\u0005\u001a\u0004\u0018\u00010\n2\b\u0010\u0006\u001a\u0004\u0018\u00010\n2\u0006\u0010\u0007\u001a\u00020\bH\u0002\u001a\u0018\u0010\u000b\u001a\u00020\u00042\u0006\u0010\f\u001a\u00020\u00042\u0006\u0010\r\u001a\u00020\u000eH\u0000\u001af\u0010\u000f\u001a\u00020\u0004*\u00020\u00042\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00012\b\u0010\u0015\u001a\u0004\u0018\u00010\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\n2\b\u0010\u0018\u001a\u0004\u0018\u00010\u00192\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u001d2\b\u0010\u001e\u001a\u0004\u0018\u00010\u001fH\u0000ø\u0001\u0000¢\u0006\u0004\b \u0010!\u001a\u0018\u0010\"\u001a\u0004\u0018\u00010\n*\u00020\u00042\b\u0010#\u001a\u0004\u0018\u00010\nH\u0002\"\u0010\u0010\u0000\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0002\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006$"}, d2 = {"DefaultLineHeight", "Landroidx/compose/ui/unit/TextUnit;", "J", "lerp", "Landroidx/compose/ui/text/ParagraphStyle;", TtmlNode.START, "stop", "fraction", "", "lerpPlatformStyle", "Landroidx/compose/ui/text/PlatformParagraphStyle;", "resolveParagraphStyleDefaults", "style", "direction", "Landroidx/compose/ui/unit/LayoutDirection;", "fastMerge", TtmlNode.ATTR_TTS_TEXT_ALIGN, "Landroidx/compose/ui/text/style/TextAlign;", "textDirection", "Landroidx/compose/ui/text/style/TextDirection;", "lineHeight", "textIndent", "Landroidx/compose/ui/text/style/TextIndent;", "platformStyle", "lineHeightStyle", "Landroidx/compose/ui/text/style/LineHeightStyle;", "lineBreak", "Landroidx/compose/ui/text/style/LineBreak;", "hyphens", "Landroidx/compose/ui/text/style/Hyphens;", "textMotion", "Landroidx/compose/ui/text/style/TextMotion;", "fastMerge-j5T8yCg", "(Landroidx/compose/ui/text/ParagraphStyle;IIJLandroidx/compose/ui/text/style/TextIndent;Landroidx/compose/ui/text/PlatformParagraphStyle;Landroidx/compose/ui/text/style/LineHeightStyle;IILandroidx/compose/ui/text/style/TextMotion;)Landroidx/compose/ui/text/ParagraphStyle;", "mergePlatformStyle", "other", "ui-text_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class ParagraphStyleKt {
    private static final long DefaultLineHeight = TextUnit.INSTANCE.m5876getUnspecifiedXSAIIZE();

    /* JADX INFO: renamed from: fastMerge-j5T8yCg, reason: not valid java name */
    public static final ParagraphStyle m5110fastMergej5T8yCg(ParagraphStyle paragraphStyle, int i10, int i11, long j10, TextIndent textIndent, PlatformParagraphStyle platformParagraphStyle, LineHeightStyle lineHeightStyle, int i12, int i13, TextMotion textMotion) {
        long j11;
        int textAlign = i10;
        TextIndent textIndent2 = textIndent;
        TextAlign.Companion companion = TextAlign.INSTANCE;
        if (TextAlign.m5578equalsimpl0(textAlign, companion.m5588getUnspecifiede0LSkKk()) || TextAlign.m5578equalsimpl0(textAlign, paragraphStyle.getTextAlign())) {
            if (TextUnitKt.m5883isUnspecifiedR2X_6o(j10)) {
                j11 = j10;
            } else {
                j11 = j10;
                if (TextUnit.m5862equalsimpl0(j11, paragraphStyle.getLineHeight())) {
                }
            }
            if ((textIndent2 == null || textIndent2.equals(paragraphStyle.getTextIndent())) && ((TextDirection.m5592equalsimpl0(i11, TextDirection.INSTANCE.m5601getUnspecifieds_7Xco()) || TextDirection.m5592equalsimpl0(i11, paragraphStyle.getTextDirection())) && ((platformParagraphStyle == null || platformParagraphStyle.equals(paragraphStyle.getPlatformStyle())) && ((lineHeightStyle == null || lineHeightStyle.equals(paragraphStyle.getLineHeightStyle())) && ((LineBreak.m5501equalsimpl0(i12, LineBreak.INSTANCE.m5515getUnspecifiedrAG3T2k()) || LineBreak.m5501equalsimpl0(i12, paragraphStyle.getLineBreak())) && ((Hyphens.m5488equalsimpl0(i13, Hyphens.INSTANCE.m5494getUnspecifiedvmbZdU8()) || Hyphens.m5488equalsimpl0(i13, paragraphStyle.getHyphens())) && (textMotion == null || textMotion.equals(paragraphStyle.getTextMotion())))))))) {
                return paragraphStyle;
            }
        } else {
            j11 = j10;
        }
        long lineHeight = TextUnitKt.m5883isUnspecifiedR2X_6o(j11) ? paragraphStyle.getLineHeight() : j11;
        if (textIndent2 == null) {
            textIndent2 = paragraphStyle.getTextIndent();
        }
        TextIndent textIndent3 = textIndent2;
        if (TextAlign.m5578equalsimpl0(textAlign, companion.m5588getUnspecifiede0LSkKk())) {
            textAlign = paragraphStyle.getTextAlign();
        }
        return new ParagraphStyle(textAlign, !TextDirection.m5592equalsimpl0(i11, TextDirection.INSTANCE.m5601getUnspecifieds_7Xco()) ? i11 : paragraphStyle.getTextDirection(), lineHeight, textIndent3, mergePlatformStyle(paragraphStyle, platformParagraphStyle), lineHeightStyle == null ? paragraphStyle.getLineHeightStyle() : lineHeightStyle, !LineBreak.m5501equalsimpl0(i12, LineBreak.INSTANCE.m5515getUnspecifiedrAG3T2k()) ? i12 : paragraphStyle.getLineBreak(), !Hyphens.m5488equalsimpl0(i13, Hyphens.INSTANCE.m5494getUnspecifiedvmbZdU8()) ? i13 : paragraphStyle.getHyphens(), textMotion == null ? paragraphStyle.getTextMotion() : textMotion, (h) null);
    }

    public static final ParagraphStyle lerp(ParagraphStyle paragraphStyle, ParagraphStyle paragraphStyle2, float f10) {
        int value = ((TextAlign) SpanStyleKt.lerpDiscrete(TextAlign.m5575boximpl(paragraphStyle.getTextAlign()), TextAlign.m5575boximpl(paragraphStyle2.getTextAlign()), f10)).getValue();
        int value2 = ((TextDirection) SpanStyleKt.lerpDiscrete(TextDirection.m5589boximpl(paragraphStyle.getTextDirection()), TextDirection.m5589boximpl(paragraphStyle2.getTextDirection()), f10)).getValue();
        long jM5157lerpTextUnitInheritableC3pnCVY = SpanStyleKt.m5157lerpTextUnitInheritableC3pnCVY(paragraphStyle.getLineHeight(), paragraphStyle2.getLineHeight(), f10);
        TextIndent textIndent = paragraphStyle.getTextIndent();
        if (textIndent == null) {
            textIndent = TextIndent.INSTANCE.getNone();
        }
        TextIndent textIndent2 = paragraphStyle2.getTextIndent();
        if (textIndent2 == null) {
            textIndent2 = TextIndent.INSTANCE.getNone();
        }
        return new ParagraphStyle(value, value2, jM5157lerpTextUnitInheritableC3pnCVY, TextIndentKt.lerp(textIndent, textIndent2, f10), lerpPlatformStyle(paragraphStyle.getPlatformStyle(), paragraphStyle2.getPlatformStyle(), f10), (LineHeightStyle) SpanStyleKt.lerpDiscrete(paragraphStyle.getLineHeightStyle(), paragraphStyle2.getLineHeightStyle(), f10), ((LineBreak) SpanStyleKt.lerpDiscrete(LineBreak.m5495boximpl(paragraphStyle.getLineBreak()), LineBreak.m5495boximpl(paragraphStyle2.getLineBreak()), f10)).getMask(), ((Hyphens) SpanStyleKt.lerpDiscrete(Hyphens.m5485boximpl(paragraphStyle.getHyphens()), Hyphens.m5485boximpl(paragraphStyle2.getHyphens()), f10)).getValue(), (TextMotion) SpanStyleKt.lerpDiscrete(paragraphStyle.getTextMotion(), paragraphStyle2.getTextMotion(), f10), (h) null);
    }

    private static final PlatformParagraphStyle lerpPlatformStyle(PlatformParagraphStyle platformParagraphStyle, PlatformParagraphStyle platformParagraphStyle2, float f10) {
        if (platformParagraphStyle == null && platformParagraphStyle2 == null) {
            return null;
        }
        if (platformParagraphStyle == null) {
            platformParagraphStyle = PlatformParagraphStyle.INSTANCE.getDefault();
        }
        if (platformParagraphStyle2 == null) {
            platformParagraphStyle2 = PlatformParagraphStyle.INSTANCE.getDefault();
        }
        return AndroidTextStyle_androidKt.lerp(platformParagraphStyle, platformParagraphStyle2, f10);
    }

    private static final PlatformParagraphStyle mergePlatformStyle(ParagraphStyle paragraphStyle, PlatformParagraphStyle platformParagraphStyle) {
        return paragraphStyle.getPlatformStyle() == null ? platformParagraphStyle : platformParagraphStyle == null ? paragraphStyle.getPlatformStyle() : paragraphStyle.getPlatformStyle().merge(platformParagraphStyle);
    }

    public static final ParagraphStyle resolveParagraphStyleDefaults(ParagraphStyle paragraphStyle, LayoutDirection layoutDirection) {
        int textAlign = paragraphStyle.getTextAlign();
        TextAlign.Companion companion = TextAlign.INSTANCE;
        int iM5587getStarte0LSkKk = TextAlign.m5578equalsimpl0(textAlign, companion.m5588getUnspecifiede0LSkKk()) ? companion.m5587getStarte0LSkKk() : paragraphStyle.getTextAlign();
        int iM5238resolveTextDirectionIhaHGbI = TextStyleKt.m5238resolveTextDirectionIhaHGbI(layoutDirection, paragraphStyle.getTextDirection());
        long lineHeight = TextUnitKt.m5883isUnspecifiedR2X_6o(paragraphStyle.getLineHeight()) ? DefaultLineHeight : paragraphStyle.getLineHeight();
        TextIndent textIndent = paragraphStyle.getTextIndent();
        if (textIndent == null) {
            textIndent = TextIndent.INSTANCE.getNone();
        }
        TextIndent textIndent2 = textIndent;
        PlatformParagraphStyle platformStyle = paragraphStyle.getPlatformStyle();
        LineHeightStyle lineHeightStyle = paragraphStyle.getLineHeightStyle();
        int lineBreak = paragraphStyle.getLineBreak();
        LineBreak.Companion companion2 = LineBreak.INSTANCE;
        int iM5514getSimplerAG3T2k = LineBreak.m5501equalsimpl0(lineBreak, companion2.m5515getUnspecifiedrAG3T2k()) ? companion2.m5514getSimplerAG3T2k() : paragraphStyle.getLineBreak();
        int hyphens = paragraphStyle.getHyphens();
        Hyphens.Companion companion3 = Hyphens.INSTANCE;
        int iM5493getNonevmbZdU8 = Hyphens.m5488equalsimpl0(hyphens, companion3.m5494getUnspecifiedvmbZdU8()) ? companion3.m5493getNonevmbZdU8() : paragraphStyle.getHyphens();
        TextMotion textMotion = paragraphStyle.getTextMotion();
        if (textMotion == null) {
            textMotion = TextMotion.INSTANCE.getStatic();
        }
        return new ParagraphStyle(iM5587getStarte0LSkKk, iM5238resolveTextDirectionIhaHGbI, lineHeight, textIndent2, platformStyle, lineHeightStyle, iM5514getSimplerAG3T2k, iM5493getNonevmbZdU8, textMotion, (h) null);
    }
}
