package androidx.compose.ui.text.platform.extensions;

import android.graphics.Typeface;
import android.os.Build;
import android.text.Spannable;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.BackgroundColorSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.LeadingMarginSpan;
import android.text.style.LocaleSpan;
import android.text.style.MetricAffectingSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.ScaleXSpan;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.graphics.Brush;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.ColorKt;
import androidx.compose.ui.graphics.ShaderBrush;
import androidx.compose.ui.graphics.Shadow;
import androidx.compose.ui.graphics.SolidColor;
import androidx.compose.ui.graphics.drawscope.DrawStyle;
import androidx.compose.ui.text.AnnotatedString;
import androidx.compose.ui.text.AnnotatedStringKt;
import androidx.compose.ui.text.PlatformSpanStyle;
import androidx.compose.ui.text.SpanStyle;
import androidx.compose.ui.text.TextStyle;
import androidx.compose.ui.text.android.style.BaselineShiftSpan;
import androidx.compose.ui.text.android.style.FontFeatureSpan;
import androidx.compose.ui.text.android.style.LetterSpacingSpanEm;
import androidx.compose.ui.text.android.style.LetterSpacingSpanPx;
import androidx.compose.ui.text.android.style.LineHeightSpan;
import androidx.compose.ui.text.android.style.LineHeightStyleSpan;
import androidx.compose.ui.text.android.style.ShadowSpan;
import androidx.compose.ui.text.android.style.SkewXSpan;
import androidx.compose.ui.text.android.style.TextDecorationSpan;
import androidx.compose.ui.text.android.style.TypefaceSpan;
import androidx.compose.ui.text.font.FontFamily;
import androidx.compose.ui.text.font.FontStyle;
import androidx.compose.ui.text.font.FontSynthesis;
import androidx.compose.ui.text.font.FontWeight;
import androidx.compose.ui.text.intl.Locale;
import androidx.compose.ui.text.intl.LocaleList;
import androidx.compose.ui.text.platform.style.DrawStyleSpan;
import androidx.compose.ui.text.platform.style.ShaderBrushSpan;
import androidx.compose.ui.text.style.BaselineShift;
import androidx.compose.ui.text.style.LineHeightStyle;
import androidx.compose.ui.text.style.TextDecoration;
import androidx.compose.ui.text.style.TextGeometricTransform;
import androidx.compose.ui.text.style.TextIndent;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.TextUnit;
import androidx.compose.ui.unit.TextUnitKt;
import androidx.compose.ui.unit.TextUnitType;
import androidx.media3.extractor.text.ttml.TtmlNode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.h;
import kotlin.jvm.internal.r;
import kotlin.text.o;
import r7.q;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000Ì\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\r\u001a+\u0010\u0007\u001a\u00020\u0006*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u0003H\u0000¢\u0006\u0004\b\u0007\u0010\b\u001a-\u0010\u000f\u001a\u00020\u0006*\u00020\u00002\b\u0010\n\u001a\u0004\u0018\u00010\t2\u0006\u0010\f\u001a\u00020\u000b2\u0006\u0010\u000e\u001a\u00020\rH\u0000¢\u0006\u0004\b\u000f\u0010\u0010\u001a6\u0010\u0017\u001a\u00020\u0006*\u00020\u00002\u0006\u0010\u0012\u001a\u00020\u00112\u0006\u0010\f\u001a\u00020\u000b2\u0006\u0010\u000e\u001a\u00020\r2\u0006\u0010\u0014\u001a\u00020\u0013H\u0000ø\u0001\u0000¢\u0006\u0004\b\u0015\u0010\u0016\u001a.\u0010\u0017\u001a\u00020\u0006*\u00020\u00002\u0006\u0010\u0012\u001a\u00020\u00112\u0006\u0010\f\u001a\u00020\u000b2\u0006\u0010\u000e\u001a\u00020\rH\u0000ø\u0001\u0000¢\u0006\u0004\b\u0018\u0010\u0019\u001a*\u0010\u001c\u001a\u00020\u000b2\u0006\u0010\u0012\u001a\u00020\u00112\u0006\u0010\f\u001a\u00020\u000b2\u0006\u0010\u000e\u001a\u00020\rH\u0002ø\u0001\u0000¢\u0006\u0004\b\u001a\u0010\u001b\u001a\u0017\u0010\u001e\u001a\u00020\u001d2\u0006\u0010\u000e\u001a\u00020\rH\u0002¢\u0006\u0004\b\u001e\u0010\u001f\u001a_\u0010-\u001a\u00020\u0006*\u00020\u00002\u0006\u0010!\u001a\u00020 2\u0012\u0010%\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020$0#0\"2\u0006\u0010\u000e\u001a\u00020\r2&\u0010,\u001a\"\u0012\u0006\u0012\u0004\u0018\u00010'\u0012\u0004\u0012\u00020(\u0012\u0004\u0012\u00020)\u0012\u0004\u0012\u00020*\u0012\u0004\u0012\u00020+0&H\u0000¢\u0006\u0004\b-\u0010.\u001a)\u00100\u001a\u00020\u0006*\u00020\u00002\f\u0010/\u001a\b\u0012\u0004\u0012\u00020$0#2\u0006\u0010\u000e\u001a\u00020\rH\u0002¢\u0006\u0004\b0\u00101\u001aW\u00102\u001a\u00020\u0006*\u00020\u00002\u0006\u0010!\u001a\u00020 2\u0012\u0010%\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020$0#0\"2&\u0010,\u001a\"\u0012\u0006\u0012\u0004\u0018\u00010'\u0012\u0004\u0012\u00020(\u0012\u0004\u0012\u00020)\u0012\u0004\u0012\u00020*\u0012\u0004\u0012\u00020+0&H\u0002¢\u0006\u0004\b2\u00103\u001aM\u00107\u001a\u00020\u00062\b\u00104\u001a\u0004\u0018\u00010$2\u0012\u0010%\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020$0#0\"2\u001e\u00106\u001a\u001a\u0012\u0004\u0012\u00020$\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u000605H\u0000¢\u0006\u0004\b7\u00108\u001a$\u0010=\u001a\u0004\u0018\u00010:2\u0006\u00109\u001a\u00020\u00112\u0006\u0010\u000e\u001a\u00020\rH\u0002ø\u0001\u0000¢\u0006\u0004\b;\u0010<\u001a-\u0010@\u001a\u00020\u0006*\u00020\u00002\b\u0010?\u001a\u0004\u0018\u00010>2\u0006\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u0003H\u0002¢\u0006\u0004\b@\u0010A\u001a-\u0010D\u001a\u00020\u0006*\u00020\u00002\b\u0010C\u001a\u0004\u0018\u00010B2\u0006\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u0003H\u0002¢\u0006\u0004\bD\u0010E\u001a.\u0010J\u001a\u00020\u0006*\u00020\u00002\u0006\u0010G\u001a\u00020F2\u0006\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u0003H\u0000ø\u0001\u0000¢\u0006\u0004\bH\u0010I\u001a-\u0010M\u001a\u00020\u0006*\u00020\u00002\b\u0010L\u001a\u0004\u0018\u00010K2\u0006\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u0003H\u0000¢\u0006\u0004\bM\u0010N\u001a-\u0010Q\u001a\u00020\u0006*\u00020\u00002\b\u0010P\u001a\u0004\u0018\u00010O2\u0006\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u0003H\u0002¢\u0006\u0004\bQ\u0010R\u001a-\u0010U\u001a\u00020\u0006*\u00020\u00002\b\u0010T\u001a\u0004\u0018\u00010S2\u0006\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u0003H\u0002¢\u0006\u0004\bU\u0010V\u001a6\u0010Z\u001a\u00020\u0006*\u00020\u00002\u0006\u0010W\u001a\u00020\u00112\u0006\u0010\u000e\u001a\u00020\r2\u0006\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u0003H\u0000ø\u0001\u0000¢\u0006\u0004\bX\u0010Y\u001a-\u0010]\u001a\u00020\u0006*\u00020\u00002\b\u0010\\\u001a\u0004\u0018\u00010[2\u0006\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u0003H\u0000¢\u0006\u0004\b]\u0010^\u001a.\u0010`\u001a\u00020\u0006*\u00020\u00002\u0006\u0010G\u001a\u00020F2\u0006\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u0003H\u0000ø\u0001\u0000¢\u0006\u0004\b_\u0010I\u001a0\u0010e\u001a\u00020\u0006*\u00020\u00002\b\u0010b\u001a\u0004\u0018\u00010a2\u0006\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u0003H\u0002ø\u0001\u0000¢\u0006\u0004\bc\u0010d\u001a5\u0010i\u001a\u00020\u0006*\u00020\u00002\b\u0010g\u001a\u0004\u0018\u00010f2\u0006\u0010h\u001a\u00020\u000b2\u0006\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u0003H\u0002¢\u0006\u0004\bi\u0010j\u001a\u0013\u0010k\u001a\u00020\u001d*\u00020 H\u0002¢\u0006\u0004\bk\u0010l\u001a\u001d\u0010n\u001a\u00020$*\u0004\u0018\u00010$2\u0006\u0010m\u001a\u00020$H\u0002¢\u0006\u0004\bn\u0010o\"\u0018\u0010r\u001a\u00020\u001d*\u00020$8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\bp\u0010q\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006s"}, d2 = {"Landroid/text/Spannable;", "", TtmlNode.TAG_SPAN, "", TtmlNode.START, TtmlNode.END, "Lx6/t0;", "setSpan", "(Landroid/text/Spannable;Ljava/lang/Object;II)V", "Landroidx/compose/ui/text/style/TextIndent;", "textIndent", "", "contextFontSize", "Landroidx/compose/ui/unit/Density;", "density", "setTextIndent", "(Landroid/text/Spannable;Landroidx/compose/ui/text/style/TextIndent;FLandroidx/compose/ui/unit/Density;)V", "Landroidx/compose/ui/unit/TextUnit;", "lineHeight", "Landroidx/compose/ui/text/style/LineHeightStyle;", "lineHeightStyle", "setLineHeight-KmRG4DE", "(Landroid/text/Spannable;JFLandroidx/compose/ui/unit/Density;Landroidx/compose/ui/text/style/LineHeightStyle;)V", "setLineHeight", "setLineHeight-r9BaKPg", "(Landroid/text/Spannable;JFLandroidx/compose/ui/unit/Density;)V", "resolveLineHeightInPx-o2QH7mI", "(JFLandroidx/compose/ui/unit/Density;)F", "resolveLineHeightInPx", "", "isNonLinearFontScalingActive", "(Landroidx/compose/ui/unit/Density;)Z", "Landroidx/compose/ui/text/TextStyle;", "contextTextStyle", "", "Landroidx/compose/ui/text/AnnotatedString$Range;", "Landroidx/compose/ui/text/SpanStyle;", "spanStyles", "Lkotlin/Function4;", "Landroidx/compose/ui/text/font/FontFamily;", "Landroidx/compose/ui/text/font/FontWeight;", "Landroidx/compose/ui/text/font/FontStyle;", "Landroidx/compose/ui/text/font/FontSynthesis;", "Landroid/graphics/Typeface;", "resolveTypeface", "setSpanStyles", "(Landroid/text/Spannable;Landroidx/compose/ui/text/TextStyle;Ljava/util/List;Landroidx/compose/ui/unit/Density;Lr7/r;)V", "spanStyleRange", "setSpanStyle", "(Landroid/text/Spannable;Landroidx/compose/ui/text/AnnotatedString$Range;Landroidx/compose/ui/unit/Density;)V", "setFontAttributes", "(Landroid/text/Spannable;Landroidx/compose/ui/text/TextStyle;Ljava/util/List;Lr7/r;)V", "contextFontSpanStyle", "Lkotlin/Function3;", "block", "flattenFontStylesAndApply", "(Landroidx/compose/ui/text/SpanStyle;Ljava/util/List;Lr7/q;)V", "letterSpacing", "Landroid/text/style/MetricAffectingSpan;", "createLetterSpacingSpan-eAf_CNQ", "(JLandroidx/compose/ui/unit/Density;)Landroid/text/style/MetricAffectingSpan;", "createLetterSpacingSpan", "Landroidx/compose/ui/graphics/Shadow;", "shadow", "setShadow", "(Landroid/text/Spannable;Landroidx/compose/ui/graphics/Shadow;II)V", "Landroidx/compose/ui/graphics/drawscope/DrawStyle;", "drawStyle", "setDrawStyle", "(Landroid/text/Spannable;Landroidx/compose/ui/graphics/drawscope/DrawStyle;II)V", "Landroidx/compose/ui/graphics/Color;", TtmlNode.ATTR_TTS_COLOR, "setBackground-RPmYEkk", "(Landroid/text/Spannable;JII)V", "setBackground", "Landroidx/compose/ui/text/intl/LocaleList;", "localeList", "setLocaleList", "(Landroid/text/Spannable;Landroidx/compose/ui/text/intl/LocaleList;II)V", "Landroidx/compose/ui/text/style/TextGeometricTransform;", "textGeometricTransform", "setGeometricTransform", "(Landroid/text/Spannable;Landroidx/compose/ui/text/style/TextGeometricTransform;II)V", "", "fontFeatureSettings", "setFontFeatureSettings", "(Landroid/text/Spannable;Ljava/lang/String;II)V", TtmlNode.ATTR_TTS_FONT_SIZE, "setFontSize-KmRG4DE", "(Landroid/text/Spannable;JLandroidx/compose/ui/unit/Density;II)V", "setFontSize", "Landroidx/compose/ui/text/style/TextDecoration;", TtmlNode.ATTR_TTS_TEXT_DECORATION, "setTextDecoration", "(Landroid/text/Spannable;Landroidx/compose/ui/text/style/TextDecoration;II)V", "setColor-RPmYEkk", "setColor", "Landroidx/compose/ui/text/style/BaselineShift;", "baselineShift", "setBaselineShift-0ocSgnM", "(Landroid/text/Spannable;Landroidx/compose/ui/text/style/BaselineShift;II)V", "setBaselineShift", "Landroidx/compose/ui/graphics/Brush;", "brush", "alpha", "setBrush", "(Landroid/text/Spannable;Landroidx/compose/ui/graphics/Brush;FII)V", "hasFontAttributes", "(Landroidx/compose/ui/text/TextStyle;)Z", "spanStyle", "merge", "(Landroidx/compose/ui/text/SpanStyle;Landroidx/compose/ui/text/SpanStyle;)Landroidx/compose/ui/text/SpanStyle;", "getNeedsLetterSpacingSpan", "(Landroidx/compose/ui/text/SpanStyle;)Z", "needsLetterSpacingSpan", "ui-text_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class SpannableExtensions_androidKt {

    /* JADX INFO: renamed from: androidx.compose.ui.text.platform.extensions.SpannableExtensions_androidKt$setFontAttributes$1, reason: invalid class name */
    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\b\u001a\u00020\u00052\u0006\u0010\u0001\u001a\u00020\u00002\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0004\u001a\u00020\u0002H\n¢\u0006\u0004\b\u0006\u0010\u0007"}, d2 = {"Landroidx/compose/ui/text/SpanStyle;", "spanStyle", "", TtmlNode.START, TtmlNode.END, "Lx6/t0;", "invoke", "(Landroidx/compose/ui/text/SpanStyle;II)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class AnonymousClass1 extends r implements q<SpanStyle, Integer, Integer, t0> {
        final /* synthetic */ r7.r<FontFamily, FontWeight, FontStyle, FontSynthesis, Typeface> $resolveTypeface;
        final /* synthetic */ Spannable $this_setFontAttributes;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public AnonymousClass1(Spannable spannable, r7.r<? super FontFamily, ? super FontWeight, ? super FontStyle, ? super FontSynthesis, ? extends Typeface> rVar) {
            super(3);
            this.$this_setFontAttributes = spannable;
            this.$resolveTypeface = rVar;
        }

        @Override // r7.q
        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2, Object obj3) {
            invoke((SpanStyle) obj, ((Number) obj2).intValue(), ((Number) obj3).intValue());
            return t0.f22605a;
        }

        public final void invoke(SpanStyle spanStyle, int i10, int i11) {
            Spannable spannable = this.$this_setFontAttributes;
            r7.r<FontFamily, FontWeight, FontStyle, FontSynthesis, Typeface> rVar = this.$resolveTypeface;
            FontFamily fontFamily = spanStyle.getFontFamily();
            FontWeight fontWeight = spanStyle.getFontWeight();
            if (fontWeight == null) {
                fontWeight = FontWeight.INSTANCE.getNormal();
            }
            FontStyle fontStyle = spanStyle.getFontStyle();
            FontStyle fontStyleM5299boximpl = FontStyle.m5299boximpl(fontStyle != null ? fontStyle.m5305unboximpl() : FontStyle.INSTANCE.m5309getNormal_LCdwA());
            FontSynthesis fontSynthesis = spanStyle.getFontSynthesis();
            spannable.setSpan(new TypefaceSpan((Typeface) rVar.invoke(fontFamily, fontWeight, fontStyleM5299boximpl, FontSynthesis.m5310boximpl(fontSynthesis != null ? fontSynthesis.getValue() : FontSynthesis.INSTANCE.m5319getAllGVVA2EU()))), i10, i11, 33);
        }
    }

    /* JADX INFO: renamed from: createLetterSpacingSpan-eAf_CNQ, reason: not valid java name */
    private static final MetricAffectingSpan m5453createLetterSpacingSpaneAf_CNQ(long j10, Density density) {
        long jM5864getTypeUIouoOA = TextUnit.m5864getTypeUIouoOA(j10);
        TextUnitType.Companion companion = TextUnitType.INSTANCE;
        if (TextUnitType.m5893equalsimpl0(jM5864getTypeUIouoOA, companion.m5898getSpUIouoOA())) {
            return new LetterSpacingSpanPx(density.mo284toPxR2X_6o(j10));
        }
        if (TextUnitType.m5893equalsimpl0(jM5864getTypeUIouoOA, companion.m5897getEmUIouoOA())) {
            return new LetterSpacingSpanEm(TextUnit.m5865getValueimpl(j10));
        }
        return null;
    }

    public static final void flattenFontStylesAndApply(SpanStyle spanStyle, List<AnnotatedString.Range<SpanStyle>> list, q<? super SpanStyle, ? super Integer, ? super Integer, t0> qVar) {
        if (list.size() <= 1) {
            if (list.isEmpty()) {
                return;
            }
            qVar.invoke(merge(spanStyle, list.get(0).getItem()), Integer.valueOf(list.get(0).getStart()), Integer.valueOf(list.get(0).getEnd()));
            return;
        }
        int size = list.size();
        int i10 = size * 2;
        Integer[] numArr = new Integer[i10];
        for (int i11 = 0; i11 < i10; i11++) {
            numArr[i11] = 0;
        }
        int size2 = list.size();
        for (int i12 = 0; i12 < size2; i12++) {
            AnnotatedString.Range<SpanStyle> range = list.get(i12);
            numArr[i12] = Integer.valueOf(range.getStart());
            numArr[i12 + size] = Integer.valueOf(range.getEnd());
        }
        Integer[] numArr2 = numArr;
        if (numArr2.length > 1) {
            Arrays.sort(numArr2);
        }
        int iIntValue = ((Number) kotlin.collections.r.Y(numArr)).intValue();
        for (int i13 = 0; i13 < i10; i13++) {
            Integer num = numArr[i13];
            int iIntValue2 = num.intValue();
            if (iIntValue2 != iIntValue) {
                int size3 = list.size();
                SpanStyle spanStyleMerge = spanStyle;
                for (int i14 = 0; i14 < size3; i14++) {
                    AnnotatedString.Range<SpanStyle> range2 = list.get(i14);
                    if (range2.getStart() != range2.getEnd() && AnnotatedStringKt.intersect(iIntValue, iIntValue2, range2.getStart(), range2.getEnd())) {
                        spanStyleMerge = merge(spanStyleMerge, range2.getItem());
                    }
                }
                if (spanStyleMerge != null) {
                    qVar.invoke(spanStyleMerge, Integer.valueOf(iIntValue), num);
                }
                iIntValue = iIntValue2;
            }
        }
    }

    private static final boolean getNeedsLetterSpacingSpan(SpanStyle spanStyle) {
        long jM5864getTypeUIouoOA = TextUnit.m5864getTypeUIouoOA(spanStyle.getLetterSpacing());
        TextUnitType.Companion companion = TextUnitType.INSTANCE;
        return TextUnitType.m5893equalsimpl0(jM5864getTypeUIouoOA, companion.m5898getSpUIouoOA()) || TextUnitType.m5893equalsimpl0(TextUnit.m5864getTypeUIouoOA(spanStyle.getLetterSpacing()), companion.m5897getEmUIouoOA());
    }

    private static final boolean hasFontAttributes(TextStyle textStyle) {
        return TextPaintExtensions_androidKt.hasFontAttributes(textStyle.toSpanStyle()) || textStyle.m5225getFontSynthesisZQGJjVo() != null;
    }

    private static final boolean isNonLinearFontScalingActive(Density density) {
        return ((double) density.getFontScale()) > 1.05d;
    }

    private static final SpanStyle merge(SpanStyle spanStyle, SpanStyle spanStyle2) {
        return spanStyle == null ? spanStyle2 : spanStyle.merge(spanStyle2);
    }

    /* JADX INFO: renamed from: resolveLineHeightInPx-o2QH7mI, reason: not valid java name */
    private static final float m5454resolveLineHeightInPxo2QH7mI(long j10, float f10, Density density) {
        float fM5865getValueimpl;
        long jM5864getTypeUIouoOA = TextUnit.m5864getTypeUIouoOA(j10);
        TextUnitType.Companion companion = TextUnitType.INSTANCE;
        if (TextUnitType.m5893equalsimpl0(jM5864getTypeUIouoOA, companion.m5898getSpUIouoOA())) {
            if (!isNonLinearFontScalingActive(density)) {
                return density.mo284toPxR2X_6o(j10);
            }
            fM5865getValueimpl = TextUnit.m5865getValueimpl(j10) / TextUnit.m5865getValueimpl(density.mo288toSpkPz2Gy4(f10));
        } else {
            if (!TextUnitType.m5893equalsimpl0(jM5864getTypeUIouoOA, companion.m5897getEmUIouoOA())) {
                return Float.NaN;
            }
            fM5865getValueimpl = TextUnit.m5865getValueimpl(j10);
        }
        return fM5865getValueimpl * f10;
    }

    /* JADX INFO: renamed from: setBackground-RPmYEkk, reason: not valid java name */
    public static final void m5455setBackgroundRPmYEkk(Spannable spannable, long j10, int i10, int i11) {
        if (j10 != Color.INSTANCE.m3508getUnspecified0d7_KjU()) {
            setSpan(spannable, new BackgroundColorSpan(ColorKt.m3526toArgb8_81llA(j10)), i10, i11);
        }
    }

    /* JADX INFO: renamed from: setBaselineShift-0ocSgnM, reason: not valid java name */
    private static final void m5456setBaselineShift0ocSgnM(Spannable spannable, BaselineShift baselineShift, int i10, int i11) {
        if (baselineShift != null) {
            setSpan(spannable, new BaselineShiftSpan(baselineShift.m5472unboximpl()), i10, i11);
        }
    }

    private static final void setBrush(Spannable spannable, Brush brush, float f10, int i10, int i11) {
        if (brush != null) {
            if (brush instanceof SolidColor) {
                m5457setColorRPmYEkk(spannable, ((SolidColor) brush).getValue(), i10, i11);
            } else if (brush instanceof ShaderBrush) {
                setSpan(spannable, new ShaderBrushSpan((ShaderBrush) brush, f10), i10, i11);
            }
        }
    }

    /* JADX INFO: renamed from: setColor-RPmYEkk, reason: not valid java name */
    public static final void m5457setColorRPmYEkk(Spannable spannable, long j10, int i10, int i11) {
        if (j10 != Color.INSTANCE.m3508getUnspecified0d7_KjU()) {
            setSpan(spannable, new ForegroundColorSpan(ColorKt.m3526toArgb8_81llA(j10)), i10, i11);
        }
    }

    private static final void setDrawStyle(Spannable spannable, DrawStyle drawStyle, int i10, int i11) {
        if (drawStyle != null) {
            setSpan(spannable, new DrawStyleSpan(drawStyle), i10, i11);
        }
    }

    private static final void setFontAttributes(Spannable spannable, TextStyle textStyle, List<AnnotatedString.Range<SpanStyle>> list, r7.r<? super FontFamily, ? super FontWeight, ? super FontStyle, ? super FontSynthesis, ? extends Typeface> rVar) {
        ArrayList arrayList = new ArrayList(list.size());
        int size = list.size();
        for (int i10 = 0; i10 < size; i10++) {
            AnnotatedString.Range<SpanStyle> range = list.get(i10);
            AnnotatedString.Range<SpanStyle> range2 = range;
            if (TextPaintExtensions_androidKt.hasFontAttributes(range2.getItem()) || range2.getItem().getFontSynthesis() != null) {
                arrayList.add(range);
            }
        }
        flattenFontStylesAndApply(hasFontAttributes(textStyle) ? new SpanStyle(0L, 0L, textStyle.getFontWeight(), textStyle.m5224getFontStyle4Lr2A7w(), textStyle.m5225getFontSynthesisZQGJjVo(), textStyle.getFontFamily(), (String) null, 0L, (BaselineShift) null, (TextGeometricTransform) null, (LocaleList) null, 0L, (TextDecoration) null, (Shadow) null, (PlatformSpanStyle) null, (DrawStyle) null, 65475, (h) null) : null, arrayList, new AnonymousClass1(spannable, rVar));
    }

    private static final void setFontFeatureSettings(Spannable spannable, String str, int i10, int i11) {
        if (str != null) {
            setSpan(spannable, new FontFeatureSpan(str), i10, i11);
        }
    }

    /* JADX INFO: renamed from: setFontSize-KmRG4DE, reason: not valid java name */
    public static final void m5458setFontSizeKmRG4DE(Spannable spannable, long j10, Density density, int i10, int i11) {
        long jM5864getTypeUIouoOA = TextUnit.m5864getTypeUIouoOA(j10);
        TextUnitType.Companion companion = TextUnitType.INSTANCE;
        if (TextUnitType.m5893equalsimpl0(jM5864getTypeUIouoOA, companion.m5898getSpUIouoOA())) {
            setSpan(spannable, new AbsoluteSizeSpan(t7.a.M(density.mo284toPxR2X_6o(j10)), false), i10, i11);
        } else if (TextUnitType.m5893equalsimpl0(jM5864getTypeUIouoOA, companion.m5897getEmUIouoOA())) {
            setSpan(spannable, new RelativeSizeSpan(TextUnit.m5865getValueimpl(j10)), i10, i11);
        }
    }

    private static final void setGeometricTransform(Spannable spannable, TextGeometricTransform textGeometricTransform, int i10, int i11) {
        if (textGeometricTransform != null) {
            setSpan(spannable, new ScaleXSpan(textGeometricTransform.getScaleX()), i10, i11);
            setSpan(spannable, new SkewXSpan(textGeometricTransform.getSkewX()), i10, i11);
        }
    }

    /* JADX INFO: renamed from: setLineHeight-KmRG4DE, reason: not valid java name */
    public static final void m5459setLineHeightKmRG4DE(Spannable spannable, long j10, float f10, Density density, LineHeightStyle lineHeightStyle) {
        float fM5454resolveLineHeightInPxo2QH7mI = m5454resolveLineHeightInPxo2QH7mI(j10, f10, density);
        if (Float.isNaN(fM5454resolveLineHeightInPxo2QH7mI)) {
            return;
        }
        setSpan(spannable, new LineHeightStyleSpan(fM5454resolveLineHeightInPxo2QH7mI, 0, (spannable.length() == 0 || o.i0(spannable) == '\n') ? spannable.length() + 1 : spannable.length(), LineHeightStyle.Trim.m5567isTrimFirstLineTopimpl$ui_text_release(lineHeightStyle.getTrim()), LineHeightStyle.Trim.m5568isTrimLastLineBottomimpl$ui_text_release(lineHeightStyle.getTrim()), lineHeightStyle.getAlignment()), 0, spannable.length());
    }

    /* JADX INFO: renamed from: setLineHeight-r9BaKPg, reason: not valid java name */
    public static final void m5460setLineHeightr9BaKPg(Spannable spannable, long j10, float f10, Density density) {
        float fM5454resolveLineHeightInPxo2QH7mI = m5454resolveLineHeightInPxo2QH7mI(j10, f10, density);
        if (Float.isNaN(fM5454resolveLineHeightInPxo2QH7mI)) {
            return;
        }
        setSpan(spannable, new LineHeightSpan(fM5454resolveLineHeightInPxo2QH7mI), 0, spannable.length());
    }

    public static final void setLocaleList(Spannable spannable, LocaleList localeList, int i10, int i11) {
        Object localeSpan;
        if (localeList != null) {
            if (Build.VERSION.SDK_INT >= 24) {
                localeSpan = LocaleListHelperMethods.INSTANCE.localeSpan(localeList);
            } else {
                localeSpan = new LocaleSpan(LocaleExtensions_androidKt.toJavaLocale(localeList.isEmpty() ? Locale.INSTANCE.getCurrent() : localeList.get(0)));
            }
            setSpan(spannable, localeSpan, i10, i11);
        }
    }

    private static final void setShadow(Spannable spannable, Shadow shadow, int i10, int i11) {
        if (shadow != null) {
            setSpan(spannable, new ShadowSpan(ColorKt.m3526toArgb8_81llA(shadow.getColor()), Offset.m3236getXimpl(shadow.getOffset()), Offset.m3237getYimpl(shadow.getOffset()), TextPaintExtensions_androidKt.correctBlurRadius(shadow.getBlurRadius())), i10, i11);
        }
    }

    public static final void setSpan(Spannable spannable, Object obj, int i10, int i11) {
        spannable.setSpan(obj, i10, i11, 33);
    }

    private static final void setSpanStyle(Spannable spannable, AnnotatedString.Range<SpanStyle> range, Density density) {
        int start = range.getStart();
        int end = range.getEnd();
        SpanStyle item = range.getItem();
        m5456setBaselineShift0ocSgnM(spannable, item.getBaselineShift(), start, end);
        m5457setColorRPmYEkk(spannable, item.m5151getColor0d7_KjU(), start, end);
        setBrush(spannable, item.getBrush(), item.getAlpha(), start, end);
        setTextDecoration(spannable, item.getBackground(), start, end);
        m5458setFontSizeKmRG4DE(spannable, item.getFontSize(), density, start, end);
        setFontFeatureSettings(spannable, item.getFontFeatureSettings(), start, end);
        setGeometricTransform(spannable, item.getTextGeometricTransform(), start, end);
        setLocaleList(spannable, item.getLocaleList(), start, end);
        m5455setBackgroundRPmYEkk(spannable, item.getBackground(), start, end);
        setShadow(spannable, item.getShadow(), start, end);
        setDrawStyle(spannable, item.getDrawStyle(), start, end);
    }

    public static final void setSpanStyles(Spannable spannable, TextStyle textStyle, List<AnnotatedString.Range<SpanStyle>> list, Density density, r7.r<? super FontFamily, ? super FontWeight, ? super FontStyle, ? super FontSynthesis, ? extends Typeface> rVar) {
        MetricAffectingSpan metricAffectingSpanM5453createLetterSpacingSpaneAf_CNQ;
        setFontAttributes(spannable, textStyle, list, rVar);
        int size = list.size();
        boolean z = false;
        for (int i10 = 0; i10 < size; i10++) {
            AnnotatedString.Range<SpanStyle> range = list.get(i10);
            int start = range.getStart();
            int end = range.getEnd();
            if (start >= 0 && start < spannable.length() && end > start && end <= spannable.length()) {
                setSpanStyle(spannable, range, density);
                if (getNeedsLetterSpacingSpan(range.getItem())) {
                    z = true;
                }
            }
        }
        if (z) {
            int size2 = list.size();
            for (int i11 = 0; i11 < size2; i11++) {
                AnnotatedString.Range<SpanStyle> range2 = list.get(i11);
                int start2 = range2.getStart();
                int end2 = range2.getEnd();
                SpanStyle item = range2.getItem();
                if (start2 >= 0 && start2 < spannable.length() && end2 > start2 && end2 <= spannable.length() && (metricAffectingSpanM5453createLetterSpacingSpaneAf_CNQ = m5453createLetterSpacingSpaneAf_CNQ(item.getLetterSpacing(), density)) != null) {
                    setSpan(spannable, metricAffectingSpanM5453createLetterSpacingSpaneAf_CNQ, start2, end2);
                }
            }
        }
    }

    public static final void setTextDecoration(Spannable spannable, TextDecoration textDecoration, int i10, int i11) {
        if (textDecoration != null) {
            TextDecoration.Companion companion = TextDecoration.INSTANCE;
            setSpan(spannable, new TextDecorationSpan(textDecoration.contains(companion.getUnderline()), textDecoration.contains(companion.getLineThrough())), i10, i11);
        }
    }

    public static final void setTextIndent(Spannable spannable, TextIndent textIndent, float f10, Density density) {
        if (textIndent != null) {
            if ((TextUnit.m5862equalsimpl0(textIndent.getFirstLine(), TextUnitKt.getSp(0)) && TextUnit.m5862equalsimpl0(textIndent.getRestLine(), TextUnitKt.getSp(0))) || TextUnitKt.m5883isUnspecifiedR2X_6o(textIndent.getFirstLine()) || TextUnitKt.m5883isUnspecifiedR2X_6o(textIndent.getRestLine())) {
                return;
            }
            long jM5864getTypeUIouoOA = TextUnit.m5864getTypeUIouoOA(textIndent.getFirstLine());
            TextUnitType.Companion companion = TextUnitType.INSTANCE;
            float fM5865getValueimpl = 0.0f;
            float fMo284toPxR2X_6o = TextUnitType.m5893equalsimpl0(jM5864getTypeUIouoOA, companion.m5898getSpUIouoOA()) ? density.mo284toPxR2X_6o(textIndent.getFirstLine()) : TextUnitType.m5893equalsimpl0(jM5864getTypeUIouoOA, companion.m5897getEmUIouoOA()) ? TextUnit.m5865getValueimpl(textIndent.getFirstLine()) * f10 : 0.0f;
            long jM5864getTypeUIouoOA2 = TextUnit.m5864getTypeUIouoOA(textIndent.getRestLine());
            if (TextUnitType.m5893equalsimpl0(jM5864getTypeUIouoOA2, companion.m5898getSpUIouoOA())) {
                fM5865getValueimpl = density.mo284toPxR2X_6o(textIndent.getRestLine());
            } else if (TextUnitType.m5893equalsimpl0(jM5864getTypeUIouoOA2, companion.m5897getEmUIouoOA())) {
                fM5865getValueimpl = TextUnit.m5865getValueimpl(textIndent.getRestLine()) * f10;
            }
            setSpan(spannable, new LeadingMarginSpan.Standard((int) Math.ceil(fMo284toPxR2X_6o), (int) Math.ceil(fM5865getValueimpl)), 0, spannable.length());
        }
    }
}
