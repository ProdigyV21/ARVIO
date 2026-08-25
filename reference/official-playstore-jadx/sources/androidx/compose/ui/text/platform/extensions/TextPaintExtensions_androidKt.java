package androidx.compose.ui.text.platform.extensions;

import android.graphics.Typeface;
import android.os.Build;
import androidx.compose.ui.geometry.Size;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.Shadow;
import androidx.compose.ui.graphics.drawscope.DrawStyle;
import androidx.compose.ui.text.PlatformSpanStyle;
import androidx.compose.ui.text.SpanStyle;
import androidx.compose.ui.text.font.FontFamily;
import androidx.compose.ui.text.font.FontStyle;
import androidx.compose.ui.text.font.FontSynthesis;
import androidx.compose.ui.text.font.FontWeight;
import androidx.compose.ui.text.intl.Locale;
import androidx.compose.ui.text.intl.LocaleList;
import androidx.compose.ui.text.platform.AndroidTextPaint;
import androidx.compose.ui.text.style.BaselineShift;
import androidx.compose.ui.text.style.TextDecoration;
import androidx.compose.ui.text.style.TextGeometricTransform;
import androidx.compose.ui.text.style.TextMotion;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.TextUnit;
import androidx.compose.ui.unit.TextUnitType;
import kotlin.Metadata;
import kotlin.jvm.internal.h;
import kotlin.jvm.internal.p;
import r7.r;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\\\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0007\n\u0002\b\u0004\u001aW\u0010\u000e\u001a\u0004\u0018\u00010\u0001*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u00012&\u0010\t\u001a\"\u0012\u0006\u0012\u0004\u0018\u00010\u0004\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b0\u00032\u0006\u0010\u000b\u001a\u00020\n2\b\b\u0002\u0010\r\u001a\u00020\fH\u0000¢\u0006\u0004\b\u000e\u0010\u000f\u001a6\u0010\u0018\u001a\u0004\u0018\u00010\u00012\u0006\u0010\u0011\u001a\u00020\u00102\u0006\u0010\r\u001a\u00020\f2\u0006\u0010\u0013\u001a\u00020\u00122\b\u0010\u0015\u001a\u0004\u0018\u00010\u0014H\u0002ø\u0001\u0000¢\u0006\u0004\b\u0016\u0010\u0017\u001a\u001d\u0010\u001c\u001a\u00020\u001b*\u00020\u00002\b\u0010\u001a\u001a\u0004\u0018\u00010\u0019H\u0000¢\u0006\u0004\b\u001c\u0010\u001d\u001a\u0013\u0010\u001e\u001a\u00020\f*\u00020\u0001H\u0000¢\u0006\u0004\b\u001e\u0010\u001f\u001a\u0017\u0010\"\u001a\u00020 2\u0006\u0010!\u001a\u00020 H\u0000¢\u0006\u0004\b\"\u0010#\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006$"}, d2 = {"Landroidx/compose/ui/text/platform/AndroidTextPaint;", "Landroidx/compose/ui/text/SpanStyle;", "style", "Lkotlin/Function4;", "Landroidx/compose/ui/text/font/FontFamily;", "Landroidx/compose/ui/text/font/FontWeight;", "Landroidx/compose/ui/text/font/FontStyle;", "Landroidx/compose/ui/text/font/FontSynthesis;", "Landroid/graphics/Typeface;", "resolveTypeface", "Landroidx/compose/ui/unit/Density;", "density", "", "requiresLetterSpacing", "applySpanStyle", "(Landroidx/compose/ui/text/platform/AndroidTextPaint;Landroidx/compose/ui/text/SpanStyle;Lr7/r;Landroidx/compose/ui/unit/Density;Z)Landroidx/compose/ui/text/SpanStyle;", "Landroidx/compose/ui/unit/TextUnit;", "letterSpacing", "Landroidx/compose/ui/graphics/Color;", "background", "Landroidx/compose/ui/text/style/BaselineShift;", "baselineShift", "generateFallbackSpanStyle-62GTOB8", "(JZJLandroidx/compose/ui/text/style/BaselineShift;)Landroidx/compose/ui/text/SpanStyle;", "generateFallbackSpanStyle", "Landroidx/compose/ui/text/style/TextMotion;", "textMotion", "Lx6/t0;", "setTextMotion", "(Landroidx/compose/ui/text/platform/AndroidTextPaint;Landroidx/compose/ui/text/style/TextMotion;)V", "hasFontAttributes", "(Landroidx/compose/ui/text/SpanStyle;)Z", "", "blurRadius", "correctBlurRadius", "(F)F", "ui-text_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class TextPaintExtensions_androidKt {
    public static final SpanStyle applySpanStyle(AndroidTextPaint androidTextPaint, SpanStyle spanStyle, r<? super FontFamily, ? super FontWeight, ? super FontStyle, ? super FontSynthesis, ? extends Typeface> rVar, Density density, boolean z) {
        long jM5864getTypeUIouoOA = TextUnit.m5864getTypeUIouoOA(spanStyle.getFontSize());
        TextUnitType.Companion companion = TextUnitType.INSTANCE;
        if (TextUnitType.m5893equalsimpl0(jM5864getTypeUIouoOA, companion.m5898getSpUIouoOA())) {
            androidTextPaint.setTextSize(density.mo284toPxR2X_6o(spanStyle.getFontSize()));
        } else if (TextUnitType.m5893equalsimpl0(jM5864getTypeUIouoOA, companion.m5897getEmUIouoOA())) {
            androidTextPaint.setTextSize(TextUnit.m5865getValueimpl(spanStyle.getFontSize()) * androidTextPaint.getTextSize());
        }
        if (hasFontAttributes(spanStyle)) {
            FontFamily fontFamily = spanStyle.getFontFamily();
            FontWeight fontWeight = spanStyle.getFontWeight();
            if (fontWeight == null) {
                fontWeight = FontWeight.INSTANCE.getNormal();
            }
            FontStyle fontStyle = spanStyle.getFontStyle();
            FontStyle fontStyleM5299boximpl = FontStyle.m5299boximpl(fontStyle != null ? fontStyle.m5305unboximpl() : FontStyle.INSTANCE.m5309getNormal_LCdwA());
            FontSynthesis fontSynthesis = spanStyle.getFontSynthesis();
            androidTextPaint.setTypeface((Typeface) rVar.invoke(fontFamily, fontWeight, fontStyleM5299boximpl, FontSynthesis.m5310boximpl(fontSynthesis != null ? fontSynthesis.getValue() : FontSynthesis.INSTANCE.m5319getAllGVVA2EU())));
        }
        if (spanStyle.getLocaleList() != null && !p.a(spanStyle.getLocaleList(), LocaleList.INSTANCE.getCurrent())) {
            if (Build.VERSION.SDK_INT >= 24) {
                LocaleListHelperMethods.INSTANCE.setTextLocales(androidTextPaint, spanStyle.getLocaleList());
            } else {
                androidTextPaint.setTextLocale(LocaleExtensions_androidKt.toJavaLocale(spanStyle.getLocaleList().isEmpty() ? Locale.INSTANCE.getCurrent() : spanStyle.getLocaleList().get(0)));
            }
        }
        if (spanStyle.getFontFeatureSettings() != null && !p.a(spanStyle.getFontFeatureSettings(), "")) {
            androidTextPaint.setFontFeatureSettings(spanStyle.getFontFeatureSettings());
        }
        if (spanStyle.getTextGeometricTransform() != null && !p.a(spanStyle.getTextGeometricTransform(), TextGeometricTransform.INSTANCE.getNone$ui_text_release())) {
            androidTextPaint.setTextScaleX(spanStyle.getTextGeometricTransform().getScaleX() * androidTextPaint.getTextScaleX());
            androidTextPaint.setTextSkewX(spanStyle.getTextGeometricTransform().getSkewX() + androidTextPaint.getTextSkewX());
        }
        androidTextPaint.m5447setColor8_81llA(spanStyle.m5151getColor0d7_KjU());
        androidTextPaint.m5446setBrush12SF9DM(spanStyle.getBrush(), Size.INSTANCE.m3313getUnspecifiedNHjbRc(), spanStyle.getAlpha());
        androidTextPaint.setShadow(spanStyle.getShadow());
        androidTextPaint.setTextDecoration(spanStyle.getBackground());
        androidTextPaint.setDrawStyle(spanStyle.getDrawStyle());
        if (TextUnitType.m5893equalsimpl0(TextUnit.m5864getTypeUIouoOA(spanStyle.getLetterSpacing()), companion.m5898getSpUIouoOA()) && TextUnit.m5865getValueimpl(spanStyle.getLetterSpacing()) != 0.0f) {
            float textScaleX = androidTextPaint.getTextScaleX() * androidTextPaint.getTextSize();
            float fMo284toPxR2X_6o = density.mo284toPxR2X_6o(spanStyle.getLetterSpacing());
            if (textScaleX != 0.0f) {
                androidTextPaint.setLetterSpacing(fMo284toPxR2X_6o / textScaleX);
            }
        } else if (TextUnitType.m5893equalsimpl0(TextUnit.m5864getTypeUIouoOA(spanStyle.getLetterSpacing()), companion.m5897getEmUIouoOA())) {
            androidTextPaint.setLetterSpacing(TextUnit.m5865getValueimpl(spanStyle.getLetterSpacing()));
        }
        return m5461generateFallbackSpanStyle62GTOB8(spanStyle.getLetterSpacing(), z, spanStyle.getBackground(), spanStyle.getBaselineShift());
    }

    public static /* synthetic */ SpanStyle applySpanStyle$default(AndroidTextPaint androidTextPaint, SpanStyle spanStyle, r rVar, Density density, boolean z, int i10, Object obj) {
        if ((i10 & 8) != 0) {
            z = false;
        }
        return applySpanStyle(androidTextPaint, spanStyle, rVar, density, z);
    }

    public static final float correctBlurRadius(float f10) {
        if (f10 == 0.0f) {
            return Float.MIN_VALUE;
        }
        return f10;
    }

    /* JADX INFO: renamed from: generateFallbackSpanStyle-62GTOB8, reason: not valid java name */
    private static final SpanStyle m5461generateFallbackSpanStyle62GTOB8(long j10, boolean z, long j11, BaselineShift baselineShift) {
        long jM3508getUnspecified0d7_KjU = j11;
        boolean z5 = false;
        boolean z10 = z && TextUnitType.m5893equalsimpl0(TextUnit.m5864getTypeUIouoOA(j10), TextUnitType.INSTANCE.m5898getSpUIouoOA()) && TextUnit.m5865getValueimpl(j10) != 0.0f;
        Color.Companion companion = Color.INSTANCE;
        boolean z11 = (Color.m3473equalsimpl0(jM3508getUnspecified0d7_KjU, companion.m3508getUnspecified0d7_KjU()) || Color.m3473equalsimpl0(jM3508getUnspecified0d7_KjU, companion.m3507getTransparent0d7_KjU())) ? false : true;
        if (baselineShift != null) {
            if (!BaselineShift.m5469equalsimpl0(baselineShift.m5472unboximpl(), BaselineShift.INSTANCE.m5476getNoney9eOQZs())) {
                z5 = true;
            }
        }
        if (!z10 && !z11 && !z5) {
            return null;
        }
        long jM5876getUnspecifiedXSAIIZE = z10 ? j10 : TextUnit.INSTANCE.m5876getUnspecifiedXSAIIZE();
        if (!z11) {
            jM3508getUnspecified0d7_KjU = companion.m3508getUnspecified0d7_KjU();
        }
        return new SpanStyle(0L, 0L, (FontWeight) null, (FontStyle) null, (FontSynthesis) null, (FontFamily) null, (String) null, jM5876getUnspecifiedXSAIIZE, z5 ? baselineShift : null, (TextGeometricTransform) null, (LocaleList) null, jM3508getUnspecified0d7_KjU, (TextDecoration) null, (Shadow) null, (PlatformSpanStyle) null, (DrawStyle) null, 63103, (h) null);
    }

    public static final boolean hasFontAttributes(SpanStyle spanStyle) {
        return (spanStyle.getFontFamily() == null && spanStyle.getFontStyle() == null && spanStyle.getFontWeight() == null) ? false : true;
    }

    public static final void setTextMotion(AndroidTextPaint androidTextPaint, TextMotion textMotion) {
        if (textMotion == null) {
            textMotion = TextMotion.INSTANCE.getStatic();
        }
        androidTextPaint.setFlags(textMotion.getSubpixelTextPositioning() ? androidTextPaint.getFlags() | 128 : androidTextPaint.getFlags() & (-129));
        int linearity = textMotion.getLinearity();
        TextMotion.Linearity.Companion companion = TextMotion.Linearity.INSTANCE;
        if (TextMotion.Linearity.m5614equalsimpl0(linearity, companion.m5619getLinear4e0Vf04())) {
            androidTextPaint.setFlags(androidTextPaint.getFlags() | 64);
            androidTextPaint.setHinting(0);
        } else if (TextMotion.Linearity.m5614equalsimpl0(linearity, companion.m5618getFontHinting4e0Vf04())) {
            androidTextPaint.getFlags();
            androidTextPaint.setHinting(1);
        } else if (!TextMotion.Linearity.m5614equalsimpl0(linearity, companion.m5620getNone4e0Vf04())) {
            androidTextPaint.getFlags();
        } else {
            androidTextPaint.getFlags();
            androidTextPaint.setHinting(0);
        }
    }
}
