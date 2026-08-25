package com.arflix.tv.ui.skin;

import androidx.compose.animation.core.CubicBezierEasing;
import androidx.compose.material3.MenuKt;
import androidx.compose.ui.graphics.ColorKt;
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
import androidx.compose.ui.unit.Dp;
import androidx.compose.ui.unit.TextUnitKt;
import com.arflix.tv.ui.theme.FontsKt;
import kotlin.Metadata;
import kotlin.jvm.internal.h;
import kotlin.jvm.internal.p;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0016\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0087\b\u0018\u0000 *2\u00020\u0001:\u0001*B7\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\r¢\u0006\u0004\b\u000e\u0010\u000fJ\t\u0010\u001c\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001d\u001a\u00020\u0005HÆ\u0003J\t\u0010\u001e\u001a\u00020\u0007HÆ\u0003J\t\u0010\u001f\u001a\u00020\tHÆ\u0003J\t\u0010 \u001a\u00020\u000bHÆ\u0003J\t\u0010!\u001a\u00020\rHÆ\u0003JE\u0010\"\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\u000b2\b\b\u0002\u0010\f\u001a\u00020\rHÆ\u0001J\u0014\u0010#\u001a\u00020$2\b\u0010%\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010&\u001a\u00020'HÖ\u0081\u0004J\n\u0010(\u001a\u00020)HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u0011\u0010\n\u001a\u00020\u000b¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019R\u0011\u0010\f\u001a\u00020\r¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u001b¨\u0006+"}, d2 = {"Lcom/arflix/tv/ui/skin/ArvioSkinTokens;", "", "colors", "Lcom/arflix/tv/ui/skin/ArvioColorTokens;", "spacing", "Lcom/arflix/tv/ui/skin/ArvioSpacingTokens;", "radius", "Lcom/arflix/tv/ui/skin/ArvioRadiusTokens;", "typography", "Lcom/arflix/tv/ui/skin/ArvioTypographyTokens;", "motion", "Lcom/arflix/tv/ui/skin/ArvioMotionTokens;", "focus", "Lcom/arflix/tv/ui/skin/ArvioFocusTokens;", "<init>", "(Lcom/arflix/tv/ui/skin/ArvioColorTokens;Lcom/arflix/tv/ui/skin/ArvioSpacingTokens;Lcom/arflix/tv/ui/skin/ArvioRadiusTokens;Lcom/arflix/tv/ui/skin/ArvioTypographyTokens;Lcom/arflix/tv/ui/skin/ArvioMotionTokens;Lcom/arflix/tv/ui/skin/ArvioFocusTokens;)V", "getColors", "()Lcom/arflix/tv/ui/skin/ArvioColorTokens;", "getSpacing", "()Lcom/arflix/tv/ui/skin/ArvioSpacingTokens;", "getRadius", "()Lcom/arflix/tv/ui/skin/ArvioRadiusTokens;", "getTypography", "()Lcom/arflix/tv/ui/skin/ArvioTypographyTokens;", "getMotion", "()Lcom/arflix/tv/ui/skin/ArvioMotionTokens;", "getFocus", "()Lcom/arflix/tv/ui/skin/ArvioFocusTokens;", "component1", "component2", "component3", "component4", "component5", "component6", "copy", "equals", "", "other", "hashCode", "", "toString", "", "Companion", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final /* data */ class ArvioSkinTokens {
    public static final int $stable = 0;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final ArvioColorTokens colors;
    private final ArvioFocusTokens focus;
    private final ArvioMotionTokens motion;
    private final ArvioRadiusTokens radius;
    private final ArvioSpacingTokens spacing;
    private final ArvioTypographyTokens typography;

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0006\u0010\u0004\u001a\u00020\u0005¨\u0006\u0006"}, d2 = {"Lcom/arflix/tv/ui/skin/ArvioSkinTokens$Companion;", "", "<init>", "()V", "defaults", "Lcom/arflix/tv/ui/skin/ArvioSkinTokens;", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(h hVar) {
            this();
        }

        /* JADX WARN: Multi-variable type inference failed */
        public final ArvioSkinTokens defaults() {
            CubicBezierEasing cubicBezierEasing = new CubicBezierEasing(0.0f, 0.0f, 0.2f, 1.0f);
            ArvioColorTokens arvioColorTokens = new ArvioColorTokens(ColorKt.Color(4278190080L), ColorKt.Color(4279045389L), ColorKt.Color(4279900698L), ColorKt.Color(4293783021L), ColorKt.Color(3018714605L), ColorKt.Color(4293783021L), ColorKt.Color(4294967295L), ColorKt.Color(4294967295L), ColorKt.Color(4294967295L), ColorKt.Color(4278245813L), ColorKt.Color(4283215696L), ColorKt.Color(4285887861L), null);
            float f10 = 8;
            float f11 = 12;
            float f12 = 16;
            ArvioSpacingTokens arvioSpacingTokens = new ArvioSpacingTokens(Dp.m5678constructorimpl(4), Dp.m5678constructorimpl(f10), Dp.m5678constructorimpl(f11), Dp.m5678constructorimpl(f12), Dp.m5678constructorimpl(24), Dp.m5678constructorimpl(32), Dp.m5678constructorimpl(48), Dp.m5678constructorimpl(64), null);
            ArvioRadiusTokens arvioRadiusTokens = new ArvioRadiusTokens(Dp.m5678constructorimpl(f10), Dp.m5678constructorimpl(f11), Dp.m5678constructorimpl(f12), null);
            FontFamily interFontFamily = FontsKt.getInterFontFamily();
            FontWeight.Companion companion = FontWeight.INSTANCE;
            long j10 = 0;
            int i10 = 0;
            int i11 = 0;
            TextStyle textStyle = new TextStyle(j10, TextUnitKt.getSp(50), companion.getBlack(), (FontStyle) null, (FontSynthesis) null, interFontFamily, (String) null, TextUnitKt.getSp(-0.5d), (BaselineShift) null, (TextGeometricTransform) null, (LocaleList) null, 0L, (TextDecoration) null, (Shadow) null, (DrawStyle) null, 0, i10, TextUnitKt.getSp(56), (TextIndent) null, (PlatformTextStyle) null, (LineHeightStyle) null, 0, i11, (TextMotion) null, 16645977, (h) null);
            long j11 = 0;
            int i12 = 0;
            int i13 = 0;
            TextStyle textStyle2 = new TextStyle(j11, TextUnitKt.getSp(20), companion.getBold(), (FontStyle) (0 == true ? 1 : 0), (FontSynthesis) null, FontsKt.getInterFontFamily(), (String) null, TextUnitKt.getSp(0.4d), (BaselineShift) (0 == true ? 1 : 0), (TextGeometricTransform) (0 == true ? 1 : 0), (LocaleList) null, 0L, (TextDecoration) (0 == true ? 1 : 0), (Shadow) (0 == true ? 1 : 0), (DrawStyle) null, i10, i12, TextUnitKt.getSp(26), (TextIndent) (0 == true ? 1 : 0), (PlatformTextStyle) (0 == true ? 1 : 0), (LineHeightStyle) null, i11, i13, (TextMotion) null, 16645977, (h) null);
            FontFamily interFontFamily2 = FontsKt.getInterFontFamily();
            int i14 = 0;
            int i15 = 0;
            Object[] objArr = 0 == true ? 1 : 0;
            Object[] objArr2 = 0 == true ? 1 : 0;
            Object[] objArr3 = 0 == true ? 1 : 0;
            TextStyle textStyle3 = new TextStyle(0L, TextUnitKt.getSp(15), companion.getSemiBold(), (FontStyle) (0 == true ? 1 : 0), (FontSynthesis) null, interFontFamily2, (String) null, TextUnitKt.getSp(0), (BaselineShift) objArr, (TextGeometricTransform) (0 == true ? 1 : 0), (LocaleList) null, 0L, (TextDecoration) objArr2, (Shadow) (0 == true ? 1 : 0), (DrawStyle) null, i12, i14, TextUnitKt.getSp(20), (TextIndent) objArr3, (PlatformTextStyle) (0 == true ? 1 : 0), (LineHeightStyle) null, i13, i15, (TextMotion) null, 16645977, (h) null);
            FontFamily interFontFamily3 = FontsKt.getInterFontFamily();
            int i16 = 0;
            int i17 = 0;
            Object[] objArr4 = 0 == true ? 1 : 0;
            Object[] objArr5 = 0 == true ? 1 : 0;
            Object[] objArr6 = 0 == true ? 1 : 0;
            TextStyle textStyle4 = new TextStyle(0L, TextUnitKt.getSp(14), companion.getNormal(), (FontStyle) (0 == true ? 1 : 0), (FontSynthesis) null, interFontFamily3, (String) null, TextUnitKt.getSp(0), (BaselineShift) objArr4, (TextGeometricTransform) (0 == true ? 1 : 0), (LocaleList) null, 0L, (TextDecoration) objArr5, (Shadow) (0 == true ? 1 : 0), (DrawStyle) null, i14, i16, TextUnitKt.getSp(20), (TextIndent) objArr6, (PlatformTextStyle) (0 == true ? 1 : 0), (LineHeightStyle) null, i15, i17, (TextMotion) null, 16645977, (h) null);
            FontFamily interFontFamily4 = FontsKt.getInterFontFamily();
            int i18 = 0;
            int i19 = 0;
            Object[] objArr7 = 0 == true ? 1 : 0;
            Object[] objArr8 = 0 == true ? 1 : 0;
            Object[] objArr9 = 0 == true ? 1 : 0;
            TextStyle textStyle5 = new TextStyle(0L, TextUnitKt.getSp(11), companion.getMedium(), (FontStyle) (0 == true ? 1 : 0), (FontSynthesis) null, interFontFamily4, (String) null, TextUnitKt.getSp(0.3d), (BaselineShift) objArr7, (TextGeometricTransform) (0 == true ? 1 : 0), (LocaleList) null, 0L, (TextDecoration) objArr8, (Shadow) (0 == true ? 1 : 0), (DrawStyle) null, i16, i18, TextUnitKt.getSp(14), (TextIndent) objArr9, (PlatformTextStyle) (0 == true ? 1 : 0), (LineHeightStyle) null, i17, i19, (TextMotion) null, 16645977, (h) null);
            FontFamily interFontFamily5 = FontsKt.getInterFontFamily();
            int i20 = 0;
            int i21 = 0;
            Object[] objArr10 = 0 == true ? 1 : 0;
            Object[] objArr11 = 0 == true ? 1 : 0;
            Object[] objArr12 = 0 == true ? 1 : 0;
            TextStyle textStyle6 = new TextStyle(0L, TextUnitKt.getSp(9), companion.getBold(), (FontStyle) (0 == true ? 1 : 0), (FontSynthesis) null, interFontFamily5, (String) null, TextUnitKt.getSp(0.4d), (BaselineShift) objArr10, (TextGeometricTransform) (0 == true ? 1 : 0), (LocaleList) null, 0L, (TextDecoration) objArr11, (Shadow) (0 == true ? 1 : 0), (DrawStyle) null, i18, i20, TextUnitKt.getSp(12), (TextIndent) objArr12, (PlatformTextStyle) (0 == true ? 1 : 0), (LineHeightStyle) null, i19, i21, (TextMotion) null, 16645977, (h) null);
            FontFamily interFontFamily6 = FontsKt.getInterFontFamily();
            return new ArvioSkinTokens(arvioColorTokens, arvioSpacingTokens, arvioRadiusTokens, new ArvioTypographyTokens(textStyle, textStyle2, textStyle3, textStyle4, textStyle5, textStyle6, new TextStyle(0L, TextUnitKt.getSp(14), companion.getBold(), (FontStyle) (0 == true ? 1 : 0), null, interFontFamily6, null, TextUnitKt.getSp(0.4d), (BaselineShift) (0 == true ? 1 : 0), (TextGeometricTransform) (0 == true ? 1 : 0), null, 0L, (TextDecoration) (0 == true ? 1 : 0), (Shadow) (0 == true ? 1 : 0), null, i20, 0, TextUnitKt.getSp(20), (TextIndent) (0 == true ? 1 : 0), (PlatformTextStyle) (0 == true ? 1 : 0), null, i21, 0, null, 16645977, (h) null)), new ArvioMotionTokens(MenuKt.InTransitionDuration, cubicBezierEasing, 150, 200), new ArvioFocusTokens(1.05f, 0.97f, MenuKt.InTransitionDuration, cubicBezierEasing, Dp.m5678constructorimpl(3), Dp.m5678constructorimpl(0), 0.0f, Dp.m5678constructorimpl(f10), null));
        }

        private Companion() {
        }
    }

    public ArvioSkinTokens(ArvioColorTokens arvioColorTokens, ArvioSpacingTokens arvioSpacingTokens, ArvioRadiusTokens arvioRadiusTokens, ArvioTypographyTokens arvioTypographyTokens, ArvioMotionTokens arvioMotionTokens, ArvioFocusTokens arvioFocusTokens) {
        this.colors = arvioColorTokens;
        this.spacing = arvioSpacingTokens;
        this.radius = arvioRadiusTokens;
        this.typography = arvioTypographyTokens;
        this.motion = arvioMotionTokens;
        this.focus = arvioFocusTokens;
    }

    public static /* synthetic */ ArvioSkinTokens copy$default(ArvioSkinTokens arvioSkinTokens, ArvioColorTokens arvioColorTokens, ArvioSpacingTokens arvioSpacingTokens, ArvioRadiusTokens arvioRadiusTokens, ArvioTypographyTokens arvioTypographyTokens, ArvioMotionTokens arvioMotionTokens, ArvioFocusTokens arvioFocusTokens, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            arvioColorTokens = arvioSkinTokens.colors;
        }
        if ((i10 & 2) != 0) {
            arvioSpacingTokens = arvioSkinTokens.spacing;
        }
        if ((i10 & 4) != 0) {
            arvioRadiusTokens = arvioSkinTokens.radius;
        }
        if ((i10 & 8) != 0) {
            arvioTypographyTokens = arvioSkinTokens.typography;
        }
        if ((i10 & 16) != 0) {
            arvioMotionTokens = arvioSkinTokens.motion;
        }
        if ((i10 & 32) != 0) {
            arvioFocusTokens = arvioSkinTokens.focus;
        }
        ArvioMotionTokens arvioMotionTokens2 = arvioMotionTokens;
        ArvioFocusTokens arvioFocusTokens2 = arvioFocusTokens;
        return arvioSkinTokens.copy(arvioColorTokens, arvioSpacingTokens, arvioRadiusTokens, arvioTypographyTokens, arvioMotionTokens2, arvioFocusTokens2);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final ArvioColorTokens getColors() {
        return this.colors;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final ArvioSpacingTokens getSpacing() {
        return this.spacing;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final ArvioRadiusTokens getRadius() {
        return this.radius;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final ArvioTypographyTokens getTypography() {
        return this.typography;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final ArvioMotionTokens getMotion() {
        return this.motion;
    }

    /* JADX INFO: renamed from: component6, reason: from getter */
    public final ArvioFocusTokens getFocus() {
        return this.focus;
    }

    public final ArvioSkinTokens copy(ArvioColorTokens colors, ArvioSpacingTokens spacing, ArvioRadiusTokens radius, ArvioTypographyTokens typography, ArvioMotionTokens motion, ArvioFocusTokens focus) {
        return new ArvioSkinTokens(colors, spacing, radius, typography, motion, focus);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof ArvioSkinTokens)) {
            return false;
        }
        ArvioSkinTokens arvioSkinTokens = (ArvioSkinTokens) other;
        return p.a(this.colors, arvioSkinTokens.colors) && p.a(this.spacing, arvioSkinTokens.spacing) && p.a(this.radius, arvioSkinTokens.radius) && p.a(this.typography, arvioSkinTokens.typography) && p.a(this.motion, arvioSkinTokens.motion) && p.a(this.focus, arvioSkinTokens.focus);
    }

    public final ArvioColorTokens getColors() {
        return this.colors;
    }

    public final ArvioFocusTokens getFocus() {
        return this.focus;
    }

    public final ArvioMotionTokens getMotion() {
        return this.motion;
    }

    public final ArvioRadiusTokens getRadius() {
        return this.radius;
    }

    public final ArvioSpacingTokens getSpacing() {
        return this.spacing;
    }

    public final ArvioTypographyTokens getTypography() {
        return this.typography;
    }

    public int hashCode() {
        return this.focus.hashCode() + ((this.motion.hashCode() + ((this.typography.hashCode() + ((this.radius.hashCode() + ((this.spacing.hashCode() + (this.colors.hashCode() * 31)) * 31)) * 31)) * 31)) * 31);
    }

    public String toString() {
        return "ArvioSkinTokens(colors=" + this.colors + ", spacing=" + this.spacing + ", radius=" + this.radius + ", typography=" + this.typography + ", motion=" + this.motion + ", focus=" + this.focus + ")";
    }
}
