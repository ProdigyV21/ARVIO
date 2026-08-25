package com.arflix.tv.ui.skin;

import androidx.compose.animation.core.Easing;
import androidx.compose.ui.unit.Dp;
import kotlin.Metadata;
import kotlin.jvm.internal.h;
import kotlin.jvm.internal.p;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0017\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u000e\b\u0087\b\u0018\u00002\u00020\u0001BG\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0002\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\b\u001a\u00020\u0007\u0012\u0006\u0010\n\u001a\u00020\t\u0012\u0006\u0010\u000b\u001a\u00020\t\u0012\u0006\u0010\f\u001a\u00020\u0002\u0012\u0006\u0010\r\u001a\u00020\t¢\u0006\u0004\b\u000e\u0010\u000fJ\u0010\u0010\u0010\u001a\u00020\u0002HÆ\u0003¢\u0006\u0004\b\u0010\u0010\u0011J\u0010\u0010\u0012\u001a\u00020\u0002HÆ\u0003¢\u0006\u0004\b\u0012\u0010\u0011J\u0010\u0010\u0013\u001a\u00020\u0005HÆ\u0003¢\u0006\u0004\b\u0013\u0010\u0014J\u0010\u0010\u0015\u001a\u00020\u0007HÆ\u0003¢\u0006\u0004\b\u0015\u0010\u0016J\u0010\u0010\u0018\u001a\u00020\tHÆ\u0003¢\u0006\u0004\b\u0017\u0010\u0011J\u0010\u0010\u001a\u001a\u00020\tHÆ\u0003¢\u0006\u0004\b\u0019\u0010\u0011J\u0010\u0010\u001b\u001a\u00020\u0002HÆ\u0003¢\u0006\u0004\b\u001b\u0010\u0011J\u0010\u0010\u001d\u001a\u00020\tHÆ\u0003¢\u0006\u0004\b\u001c\u0010\u0011J`\u0010 \u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\b\b\u0002\u0010\u0004\u001a\u00020\u00022\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\b\u001a\u00020\u00072\b\b\u0002\u0010\n\u001a\u00020\t2\b\b\u0002\u0010\u000b\u001a\u00020\t2\b\b\u0002\u0010\f\u001a\u00020\u00022\b\b\u0002\u0010\r\u001a\u00020\tHÆ\u0001¢\u0006\u0004\b\u001e\u0010\u001fJ\u0010\u0010\"\u001a\u00020!HÖ\u0001¢\u0006\u0004\b\"\u0010#J\u0010\u0010$\u001a\u00020\u0005HÖ\u0001¢\u0006\u0004\b$\u0010\u0014J\u001a\u0010'\u001a\u00020&2\b\u0010%\u001a\u0004\u0018\u00010\u0001HÖ\u0003¢\u0006\u0004\b'\u0010(R\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010)\u001a\u0004\b*\u0010\u0011R\u0017\u0010\u0004\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0004\u0010)\u001a\u0004\b+\u0010\u0011R\u0017\u0010\u0006\u001a\u00020\u00058\u0006¢\u0006\f\n\u0004\b\u0006\u0010,\u001a\u0004\b-\u0010\u0014R\u0017\u0010\b\u001a\u00020\u00078\u0006¢\u0006\f\n\u0004\b\b\u0010.\u001a\u0004\b/\u0010\u0016R\u0017\u0010\n\u001a\u00020\t8\u0006¢\u0006\f\n\u0004\b\n\u0010)\u001a\u0004\b0\u0010\u0011R\u0017\u0010\u000b\u001a\u00020\t8\u0006¢\u0006\f\n\u0004\b\u000b\u0010)\u001a\u0004\b1\u0010\u0011R\u0017\u0010\f\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\f\u0010)\u001a\u0004\b2\u0010\u0011R\u0017\u0010\r\u001a\u00020\t8\u0006¢\u0006\f\n\u0004\b\r\u0010)\u001a\u0004\b3\u0010\u0011¨\u00064"}, d2 = {"Lcom/arflix/tv/ui/skin/ArvioFocusTokens;", "", "", "scaleFocused", "scalePressed", "", "durationMillis", "Landroidx/compose/animation/core/Easing;", "easing", "Landroidx/compose/ui/unit/Dp;", "outlineWidth", "glowWidth", "glowAlpha", "translationZFocused", "<init>", "(FFILandroidx/compose/animation/core/Easing;FFFFLkotlin/jvm/internal/h;)V", "component1", "()F", "component2", "component3", "()I", "component4", "()Landroidx/compose/animation/core/Easing;", "component5-D9Ej5fM", "component5", "component6-D9Ej5fM", "component6", "component7", "component8-D9Ej5fM", "component8", "copy-eE3J2Qk", "(FFILandroidx/compose/animation/core/Easing;FFFF)Lcom/arflix/tv/ui/skin/ArvioFocusTokens;", "copy", "", "toString", "()Ljava/lang/String;", "hashCode", "other", "", "equals", "(Ljava/lang/Object;)Z", "F", "getScaleFocused", "getScalePressed", "I", "getDurationMillis", "Landroidx/compose/animation/core/Easing;", "getEasing", "getOutlineWidth-D9Ej5fM", "getGlowWidth-D9Ej5fM", "getGlowAlpha", "getTranslationZFocused-D9Ej5fM", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final /* data */ class ArvioFocusTokens {
    public static final int $stable = 0;
    private final int durationMillis;
    private final Easing easing;
    private final float glowAlpha;
    private final float glowWidth;
    private final float outlineWidth;
    private final float scaleFocused;
    private final float scalePressed;
    private final float translationZFocused;

    public /* synthetic */ ArvioFocusTokens(float f10, float f11, int i10, Easing easing, float f12, float f13, float f14, float f15, h hVar) {
        this(f10, f11, i10, easing, f12, f13, f14, f15);
    }

    /* JADX INFO: renamed from: copy-eE3J2Qk$default, reason: not valid java name */
    public static /* synthetic */ ArvioFocusTokens m6503copyeE3J2Qk$default(ArvioFocusTokens arvioFocusTokens, float f10, float f11, int i10, Easing easing, float f12, float f13, float f14, float f15, int i11, Object obj) {
        if ((i11 & 1) != 0) {
            f10 = arvioFocusTokens.scaleFocused;
        }
        if ((i11 & 2) != 0) {
            f11 = arvioFocusTokens.scalePressed;
        }
        if ((i11 & 4) != 0) {
            i10 = arvioFocusTokens.durationMillis;
        }
        if ((i11 & 8) != 0) {
            easing = arvioFocusTokens.easing;
        }
        if ((i11 & 16) != 0) {
            f12 = arvioFocusTokens.outlineWidth;
        }
        if ((i11 & 32) != 0) {
            f13 = arvioFocusTokens.glowWidth;
        }
        if ((i11 & 64) != 0) {
            f14 = arvioFocusTokens.glowAlpha;
        }
        if ((i11 & 128) != 0) {
            f15 = arvioFocusTokens.translationZFocused;
        }
        float f16 = f14;
        float f17 = f15;
        float f18 = f12;
        float f19 = f13;
        return arvioFocusTokens.m6507copyeE3J2Qk(f10, f11, i10, easing, f18, f19, f16, f17);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final float getScaleFocused() {
        return this.scaleFocused;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final float getScalePressed() {
        return this.scalePressed;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final int getDurationMillis() {
        return this.durationMillis;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final Easing getEasing() {
        return this.easing;
    }

    /* JADX INFO: renamed from: component5-D9Ej5fM, reason: not valid java name and from getter */
    public final float getOutlineWidth() {
        return this.outlineWidth;
    }

    /* JADX INFO: renamed from: component6-D9Ej5fM, reason: not valid java name and from getter */
    public final float getGlowWidth() {
        return this.glowWidth;
    }

    /* JADX INFO: renamed from: component7, reason: from getter */
    public final float getGlowAlpha() {
        return this.glowAlpha;
    }

    /* JADX INFO: renamed from: component8-D9Ej5fM, reason: not valid java name and from getter */
    public final float getTranslationZFocused() {
        return this.translationZFocused;
    }

    /* JADX INFO: renamed from: copy-eE3J2Qk, reason: not valid java name */
    public final ArvioFocusTokens m6507copyeE3J2Qk(float scaleFocused, float scalePressed, int durationMillis, Easing easing, float outlineWidth, float glowWidth, float glowAlpha, float translationZFocused) {
        return new ArvioFocusTokens(scaleFocused, scalePressed, durationMillis, easing, outlineWidth, glowWidth, glowAlpha, translationZFocused, null);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof ArvioFocusTokens)) {
            return false;
        }
        ArvioFocusTokens arvioFocusTokens = (ArvioFocusTokens) other;
        return Float.compare(this.scaleFocused, arvioFocusTokens.scaleFocused) == 0 && Float.compare(this.scalePressed, arvioFocusTokens.scalePressed) == 0 && this.durationMillis == arvioFocusTokens.durationMillis && p.a(this.easing, arvioFocusTokens.easing) && Dp.m5683equalsimpl0(this.outlineWidth, arvioFocusTokens.outlineWidth) && Dp.m5683equalsimpl0(this.glowWidth, arvioFocusTokens.glowWidth) && Float.compare(this.glowAlpha, arvioFocusTokens.glowAlpha) == 0 && Dp.m5683equalsimpl0(this.translationZFocused, arvioFocusTokens.translationZFocused);
    }

    public final int getDurationMillis() {
        return this.durationMillis;
    }

    public final Easing getEasing() {
        return this.easing;
    }

    public final float getGlowAlpha() {
        return this.glowAlpha;
    }

    /* JADX INFO: renamed from: getGlowWidth-D9Ej5fM, reason: not valid java name */
    public final float m6508getGlowWidthD9Ej5fM() {
        return this.glowWidth;
    }

    /* JADX INFO: renamed from: getOutlineWidth-D9Ej5fM, reason: not valid java name */
    public final float m6509getOutlineWidthD9Ej5fM() {
        return this.outlineWidth;
    }

    public final float getScaleFocused() {
        return this.scaleFocused;
    }

    public final float getScalePressed() {
        return this.scalePressed;
    }

    /* JADX INFO: renamed from: getTranslationZFocused-D9Ej5fM, reason: not valid java name */
    public final float m6510getTranslationZFocusedD9Ej5fM() {
        return this.translationZFocused;
    }

    public int hashCode() {
        return Dp.m5684hashCodeimpl(this.translationZFocused) + a0.c.b(this.glowAlpha, a0.c.C(this.glowWidth, a0.c.C(this.outlineWidth, (this.easing.hashCode() + ((a0.c.b(this.scalePressed, Float.floatToIntBits(this.scaleFocused) * 31, 31) + this.durationMillis) * 31)) * 31, 31), 31), 31);
    }

    public String toString() {
        float f10 = this.scaleFocused;
        float f11 = this.scalePressed;
        int i10 = this.durationMillis;
        Easing easing = this.easing;
        String strM5689toStringimpl = Dp.m5689toStringimpl(this.outlineWidth);
        String strM5689toStringimpl2 = Dp.m5689toStringimpl(this.glowWidth);
        float f12 = this.glowAlpha;
        String strM5689toStringimpl3 = Dp.m5689toStringimpl(this.translationZFocused);
        StringBuilder sb2 = new StringBuilder("ArvioFocusTokens(scaleFocused=");
        sb2.append(f10);
        sb2.append(", scalePressed=");
        sb2.append(f11);
        sb2.append(", durationMillis=");
        sb2.append(i10);
        sb2.append(", easing=");
        sb2.append(easing);
        sb2.append(", outlineWidth=");
        y.a.i(sb2, strM5689toStringimpl, ", glowWidth=", strM5689toStringimpl2, ", glowAlpha=");
        sb2.append(f12);
        sb2.append(", translationZFocused=");
        sb2.append(strM5689toStringimpl3);
        sb2.append(")");
        return sb2.toString();
    }

    private ArvioFocusTokens(float f10, float f11, int i10, Easing easing, float f12, float f13, float f14, float f15) {
        this.scaleFocused = f10;
        this.scalePressed = f11;
        this.durationMillis = i10;
        this.easing = easing;
        this.outlineWidth = f12;
        this.glowWidth = f13;
        this.glowAlpha = f14;
        this.translationZFocused = f15;
    }
}
