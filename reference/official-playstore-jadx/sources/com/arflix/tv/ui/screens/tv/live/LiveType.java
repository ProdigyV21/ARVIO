package com.arflix.tv.ui.screens.tv.live;

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
import com.arflix.tv.ui.theme.FontsKt;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0013\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\b\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\u0007R\u0011\u0010\n\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\u0007R\u0011\u0010\f\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u0007R\u0011\u0010\u000e\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0007R\u0011\u0010\u0010\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0007R\u0011\u0010\u0012\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0007R\u0011\u0010\u0014\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0007R\u0011\u0010\u0016\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0007¨\u0006\u0018"}, d2 = {"Lcom/arflix/tv/ui/screens/tv/live/LiveType;", "", "<init>", "()V", "ChannelName", "Landroidx/compose/ui/text/TextStyle;", "getChannelName", "()Landroidx/compose/ui/text/TextStyle;", "ProgramTitle", "getProgramTitle", "CellTitle", "getCellTitle", "BodySynopsis", "getBodySynopsis", "CatLabel", "getCatLabel", "SectionTag", "getSectionTag", "Badge", "getBadge", "TimeMono", "getTimeMono", "NumberMono", "getNumberMono", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class LiveType {
    public static final int $stable = 0;
    private static final TextStyle Badge;
    private static final TextStyle BodySynopsis;
    private static final TextStyle CatLabel;
    private static final TextStyle CellTitle;
    private static final TextStyle ChannelName;
    public static final LiveType INSTANCE = new LiveType();
    private static final TextStyle NumberMono;
    private static final TextStyle ProgramTitle;
    private static final TextStyle SectionTag;
    private static final TextStyle TimeMono;

    /* JADX WARN: Multi-variable type inference failed */
    static {
        FontFamily interFontFamily = FontsKt.getInterFontFamily();
        long sp = TextUnitKt.getSp(11);
        FontWeight.Companion companion = FontWeight.INSTANCE;
        int i10 = 16645977;
        kotlin.jvm.internal.h hVar = null;
        long j10 = 0;
        FontStyle fontStyle = null;
        FontSynthesis fontSynthesis = null;
        String str = null;
        BaselineShift baselineShift = null;
        TextGeometricTransform textGeometricTransform = null;
        LocaleList localeList = null;
        long j11 = 0;
        TextDecoration textDecoration = null;
        Shadow shadow = null;
        DrawStyle drawStyle = null;
        int i11 = 0;
        int i12 = 0;
        TextIndent textIndent = null;
        PlatformTextStyle platformTextStyle = null;
        LineHeightStyle lineHeightStyle = null;
        int i13 = 0;
        int i14 = 0;
        TextMotion textMotion = null;
        ChannelName = new TextStyle(j10, sp, companion.getW600(), fontStyle, fontSynthesis, interFontFamily, str, TextUnitKt.getSp(0), baselineShift, textGeometricTransform, localeList, j11, textDecoration, shadow, drawStyle, i11, i12, TextUnitKt.getSp(14), textIndent, platformTextStyle, lineHeightStyle, i13, i14, textMotion, i10, hVar);
        ProgramTitle = new TextStyle(0L, TextUnitKt.getSp(10), companion.getW500(), null, null, FontsKt.getInterFontFamily(), (String) null, TextUnitKt.getSp(0), (BaselineShift) null, (TextGeometricTransform) null, (LocaleList) null, 0L, (TextDecoration) null, (Shadow) null, (DrawStyle) null, 0, 0, TextUnitKt.getSp(13), (TextIndent) null, (PlatformTextStyle) null, (LineHeightStyle) null, 0, 0, (TextMotion) null, 16645977, (kotlin.jvm.internal.h) null);
        CellTitle = new TextStyle(j10, TextUnitKt.getSp(9), companion.getW500(), fontStyle, fontSynthesis, FontsKt.getInterFontFamily(), str, TextUnitKt.getSp(0), baselineShift, textGeometricTransform, localeList, j11, textDecoration, shadow, drawStyle, i11, i12, TextUnitKt.getSp(12), textIndent, platformTextStyle, lineHeightStyle, i13, i14, textMotion, i10, hVar);
        int i15 = 0;
        int i16 = 0;
        Object[] objArr = 0 == true ? 1 : 0;
        BodySynopsis = new TextStyle(0L, TextUnitKt.getSp(8), companion.getW400(), null, (FontSynthesis) (0 == true ? 1 : 0), FontsKt.getInterFontFamily(), (String) null, TextUnitKt.getSp(0), (BaselineShift) (0 == true ? 1 : 0), (TextGeometricTransform) null, (LocaleList) null, 0L, (TextDecoration) (0 == true ? 1 : 0), (Shadow) null, (DrawStyle) null, 0, i15, TextUnitKt.getSp(11), (TextIndent) (0 == true ? 1 : 0), (PlatformTextStyle) null, (LineHeightStyle) null, 0, i16, (TextMotion) objArr, 16645977, (kotlin.jvm.internal.h) null);
        int i17 = 0;
        int i18 = 0;
        CatLabel = new TextStyle(0L, TextUnitKt.getSp(9), companion.getW500(), (FontStyle) (0 == true ? 1 : 0), null, FontsKt.getInterFontFamily(), (String) null, TextUnitKt.getSp(0), (BaselineShift) (0 == true ? 1 : 0), (TextGeometricTransform) (0 == true ? 1 : 0), (LocaleList) null, 0L, (TextDecoration) (0 == true ? 1 : 0), (Shadow) (0 == true ? 1 : 0), (DrawStyle) null, i15, i17, TextUnitKt.getSp(12), (TextIndent) (0 == true ? 1 : 0), (PlatformTextStyle) (0 == true ? 1 : 0), (LineHeightStyle) null, i16, i18, (TextMotion) null, 16645977, (kotlin.jvm.internal.h) null);
        int i19 = 0;
        int i20 = 0;
        Object[] objArr2 = 0 == true ? 1 : 0;
        Object[] objArr3 = 0 == true ? 1 : 0;
        Object[] objArr4 = 0 == true ? 1 : 0;
        SectionTag = new TextStyle(0L, TextUnitKt.getSp(8), companion.getW600(), (FontStyle) (0 == true ? 1 : 0), null, FontsKt.getInterFontFamily(), (String) null, TextUnitKt.getSp(0), (BaselineShift) objArr2, (TextGeometricTransform) (0 == true ? 1 : 0), (LocaleList) null, 0L, (TextDecoration) objArr3, (Shadow) (0 == true ? 1 : 0), (DrawStyle) null, i17, i19, TextUnitKt.getSp(11), (TextIndent) objArr4, (PlatformTextStyle) (0 == true ? 1 : 0), (LineHeightStyle) null, i18, i20, (TextMotion) null, 16645977, (kotlin.jvm.internal.h) null);
        int i21 = 0;
        int i22 = 0;
        Object[] objArr5 = 0 == true ? 1 : 0;
        Object[] objArr6 = 0 == true ? 1 : 0;
        Object[] objArr7 = 0 == true ? 1 : 0;
        Badge = new TextStyle(0L, TextUnitKt.getSp(8), companion.getW600(), (FontStyle) (0 == true ? 1 : 0), null, FontsKt.getInterFontFamily(), (String) null, TextUnitKt.getSp(0), (BaselineShift) objArr5, (TextGeometricTransform) (0 == true ? 1 : 0), (LocaleList) null, 0L, (TextDecoration) objArr6, (Shadow) (0 == true ? 1 : 0), (DrawStyle) null, i19, i21, TextUnitKt.getSp(11), (TextIndent) objArr7, (PlatformTextStyle) (0 == true ? 1 : 0), (LineHeightStyle) null, i20, i22, (TextMotion) null, 16645977, (kotlin.jvm.internal.h) null);
        int i23 = 0;
        int i24 = 0;
        Object[] objArr8 = 0 == true ? 1 : 0;
        Object[] objArr9 = 0 == true ? 1 : 0;
        Object[] objArr10 = 0 == true ? 1 : 0;
        TimeMono = new TextStyle(0L, TextUnitKt.getSp(8), companion.getW500(), (FontStyle) (0 == true ? 1 : 0), null, FontsKt.getInterFontFamily(), (String) null, TextUnitKt.getSp(0), (BaselineShift) objArr8, (TextGeometricTransform) (0 == true ? 1 : 0), (LocaleList) null, 0L, (TextDecoration) objArr9, (Shadow) (0 == true ? 1 : 0), (DrawStyle) null, i21, i23, TextUnitKt.getSp(11), (TextIndent) objArr10, (PlatformTextStyle) (0 == true ? 1 : 0), (LineHeightStyle) null, i22, i24, (TextMotion) null, 16645977, (kotlin.jvm.internal.h) null);
        Object[] objArr11 = 0 == true ? 1 : 0;
        Object[] objArr12 = 0 == true ? 1 : 0;
        Object[] objArr13 = 0 == true ? 1 : 0;
        NumberMono = new TextStyle(0L, TextUnitKt.getSp(8), companion.getW500(), (FontStyle) (0 == true ? 1 : 0), null, FontsKt.getInterFontFamily(), (String) null, TextUnitKt.getSp(0), (BaselineShift) objArr11, (TextGeometricTransform) (0 == true ? 1 : 0), (LocaleList) null, 0L, (TextDecoration) objArr12, (Shadow) (0 == true ? 1 : 0), (DrawStyle) null, i23, 0, TextUnitKt.getSp(11), (TextIndent) objArr13, (PlatformTextStyle) (0 == true ? 1 : 0), (LineHeightStyle) null, i24, 0, (TextMotion) null, 16645977, (kotlin.jvm.internal.h) null);
    }

    private LiveType() {
    }

    public final TextStyle getBadge() {
        return Badge;
    }

    public final TextStyle getBodySynopsis() {
        return BodySynopsis;
    }

    public final TextStyle getCatLabel() {
        return CatLabel;
    }

    public final TextStyle getCellTitle() {
        return CellTitle;
    }

    public final TextStyle getChannelName() {
        return ChannelName;
    }

    public final TextStyle getNumberMono() {
        return NumberMono;
    }

    public final TextStyle getProgramTitle() {
        return ProgramTitle;
    }

    public final TextStyle getSectionTag() {
        return SectionTag;
    }

    public final TextStyle getTimeMono() {
        return TimeMono;
    }
}
