package com.arflix.tv.ui.theme;

import androidx.compose.ui.graphics.Shadow;
import androidx.compose.ui.graphics.drawscope.DrawStyle;
import androidx.compose.ui.text.PlatformTextStyle;
import androidx.compose.ui.text.TextStyle;
import androidx.compose.ui.text.font.FontFamily;
import androidx.compose.ui.text.font.FontStyle;
import androidx.compose.ui.text.font.FontSynthesis;
import androidx.compose.ui.text.font.FontWeight;
import androidx.compose.ui.text.intl.LocaleList;
import androidx.compose.ui.text.style.BaselineShift;
import androidx.compose.ui.text.style.LineHeightStyle;
import androidx.compose.ui.text.style.TextDecoration;
import androidx.compose.ui.text.style.TextGeometricTransform;
import androidx.compose.ui.text.style.TextIndent;
import androidx.compose.ui.text.style.TextMotion;
import androidx.compose.ui.unit.TextUnitKt;
import androidx.media3.extractor.text.ttml.TtmlNode;
import com.google.android.gms.cast.MediaTrack;
import kotlin.Metadata;
import kotlin.jvm.internal.h;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0015\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\b\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\u0007R\u0011\u0010\n\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\u0007R\u0011\u0010\f\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u0007R\u0011\u0010\u000e\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0007R\u0011\u0010\u0010\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0007R\u0011\u0010\u0012\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0007R\u0011\u0010\u0014\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0007R\u0011\u0010\u0016\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0007R\u0011\u0010\u0018\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0007¨\u0006\u001a"}, d2 = {"Lcom/arflix/tv/ui/theme/ArflixTypography;", "", "<init>", "()V", "heroTitle", "Landroidx/compose/ui/text/TextStyle;", "getHeroTitle", "()Landroidx/compose/ui/text/TextStyle;", "sectionTitle", "getSectionTitle", "cardTitle", "getCardTitle", TtmlNode.TAG_BODY, "getBody", "bodyLarge", "getBodyLarge", MediaTrack.ROLE_CAPTION, "getCaption", "label", "getLabel", "button", "getButton", "clock", "getClock", "badge", "getBadge", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class ArflixTypography {
    public static final int $stable = 0;
    public static final ArflixTypography INSTANCE = new ArflixTypography();
    private static final TextStyle badge;
    private static final TextStyle body;
    private static final TextStyle bodyLarge;
    private static final TextStyle button;
    private static final TextStyle caption;
    private static final TextStyle cardTitle;
    private static final TextStyle clock;
    private static final TextStyle heroTitle;
    private static final TextStyle label;
    private static final TextStyle sectionTitle;

    static {
        FontFamily interFontFamily = FontsKt.getInterFontFamily();
        FontWeight.Companion companion = FontWeight.INSTANCE;
        heroTitle = new TextStyle(0L, TextUnitKt.getSp(48), companion.getBlack(), (FontStyle) null, (FontSynthesis) null, interFontFamily, (String) null, TextUnitKt.getSp(-1), (BaselineShift) null, (TextGeometricTransform) null, (LocaleList) null, 0L, (TextDecoration) null, (Shadow) null, (DrawStyle) null, 0, 0, TextUnitKt.getSp(52), (TextIndent) null, (PlatformTextStyle) null, (LineHeightStyle) null, 0, 0, (TextMotion) null, 16645977, (h) null);
        sectionTitle = new TextStyle(0L, TextUnitKt.getSp(20), companion.getBold(), (FontStyle) null, (FontSynthesis) null, FontsKt.getInterFontFamily(), (String) null, TextUnitKt.getSp(0.5d), (BaselineShift) null, (TextGeometricTransform) null, (LocaleList) null, 0L, (TextDecoration) null, (Shadow) null, (DrawStyle) null, 0, 0, TextUnitKt.getSp(26), (TextIndent) null, (PlatformTextStyle) null, (LineHeightStyle) null, 0, 0, (TextMotion) null, 16645977, (h) null);
        cardTitle = new TextStyle(0L, TextUnitKt.getSp(15), companion.getSemiBold(), (FontStyle) null, (FontSynthesis) null, FontsKt.getInterFontFamily(), (String) null, TextUnitKt.getSp(0), (BaselineShift) null, (TextGeometricTransform) null, (LocaleList) null, 0L, (TextDecoration) null, (Shadow) null, (DrawStyle) null, 0, 0, TextUnitKt.getSp(20), (TextIndent) null, (PlatformTextStyle) null, (LineHeightStyle) null, 0, 0, (TextMotion) null, 16645977, (h) null);
        body = new TextStyle(0L, TextUnitKt.getSp(14), companion.getNormal(), (FontStyle) null, (FontSynthesis) null, FontsKt.getInterFontFamily(), (String) null, TextUnitKt.getSp(0), (BaselineShift) null, (TextGeometricTransform) null, (LocaleList) null, 0L, (TextDecoration) null, (Shadow) null, (DrawStyle) null, 0, 0, TextUnitKt.getSp(20), (TextIndent) null, (PlatformTextStyle) null, (LineHeightStyle) null, 0, 0, (TextMotion) null, 16645977, (h) null);
        bodyLarge = new TextStyle(0L, TextUnitKt.getSp(18), companion.getMedium(), (FontStyle) null, (FontSynthesis) null, FontsKt.getInterFontFamily(), (String) null, TextUnitKt.getSp(0), (BaselineShift) null, (TextGeometricTransform) null, (LocaleList) null, 0L, (TextDecoration) null, (Shadow) null, (DrawStyle) null, 0, 0, TextUnitKt.getSp(24), (TextIndent) null, (PlatformTextStyle) null, (LineHeightStyle) null, 0, 0, (TextMotion) null, 16645977, (h) null);
        caption = new TextStyle(0L, TextUnitKt.getSp(11), companion.getMedium(), (FontStyle) null, (FontSynthesis) null, FontsKt.getInterFontFamily(), (String) null, TextUnitKt.getSp(0.4d), (BaselineShift) null, (TextGeometricTransform) null, (LocaleList) null, 0L, (TextDecoration) null, (Shadow) null, (DrawStyle) null, 0, 0, TextUnitKt.getSp(14), (TextIndent) null, (PlatformTextStyle) null, (LineHeightStyle) null, 0, 0, (TextMotion) null, 16645977, (h) null);
        label = new TextStyle(0L, TextUnitKt.getSp(12), companion.getBold(), (FontStyle) null, (FontSynthesis) null, FontsKt.getInterFontFamily(), (String) null, TextUnitKt.getSp(0.4d), (BaselineShift) null, (TextGeometricTransform) null, (LocaleList) null, 0L, (TextDecoration) null, (Shadow) null, (DrawStyle) null, 0, 0, TextUnitKt.getSp(16), (TextIndent) null, (PlatformTextStyle) null, (LineHeightStyle) null, 0, 0, (TextMotion) null, 16645977, (h) null);
        button = new TextStyle(0L, TextUnitKt.getSp(14), companion.getBold(), (FontStyle) null, (FontSynthesis) null, FontsKt.getInterFontFamily(), (String) null, TextUnitKt.getSp(0.5d), (BaselineShift) null, (TextGeometricTransform) null, (LocaleList) null, 0L, (TextDecoration) null, (Shadow) null, (DrawStyle) null, 0, 0, TextUnitKt.getSp(20), (TextIndent) null, (PlatformTextStyle) null, (LineHeightStyle) null, 0, 0, (TextMotion) null, 16645977, (h) null);
        clock = new TextStyle(0L, TextUnitKt.getSp(24), companion.getBold(), (FontStyle) null, (FontSynthesis) null, FontsKt.getInterFontFamily(), (String) null, TextUnitKt.getSp(0), (BaselineShift) null, (TextGeometricTransform) null, (LocaleList) null, 0L, (TextDecoration) null, (Shadow) null, (DrawStyle) null, 0, 0, TextUnitKt.getSp(30), (TextIndent) null, (PlatformTextStyle) null, (LineHeightStyle) null, 0, 0, (TextMotion) null, 16645977, (h) null);
        badge = new TextStyle(0L, TextUnitKt.getSp(9), companion.getBold(), (FontStyle) null, (FontSynthesis) null, FontsKt.getInterFontFamily(), (String) null, TextUnitKt.getSp(0.5d), (BaselineShift) null, (TextGeometricTransform) null, (LocaleList) null, 0L, (TextDecoration) null, (Shadow) null, (DrawStyle) null, 0, 0, TextUnitKt.getSp(12), (TextIndent) null, (PlatformTextStyle) null, (LineHeightStyle) null, 0, 0, (TextMotion) null, 16645977, (h) null);
    }

    private ArflixTypography() {
    }

    public final TextStyle getBadge() {
        return badge;
    }

    public final TextStyle getBody() {
        return body;
    }

    public final TextStyle getBodyLarge() {
        return bodyLarge;
    }

    public final TextStyle getButton() {
        return button;
    }

    public final TextStyle getCaption() {
        return caption;
    }

    public final TextStyle getCardTitle() {
        return cardTitle;
    }

    public final TextStyle getClock() {
        return clock;
    }

    public final TextStyle getHeroTitle() {
        return heroTitle;
    }

    public final TextStyle getLabel() {
        return label;
    }

    public final TextStyle getSectionTitle() {
        return sectionTitle;
    }
}
