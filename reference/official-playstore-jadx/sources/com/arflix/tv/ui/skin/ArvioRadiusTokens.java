package com.arflix.tv.ui.skin;

import androidx.compose.ui.unit.Dp;
import androidx.fragment.app.a2;
import kotlin.Metadata;
import kotlin.jvm.internal.h;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0007\b\u0087\b\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0002¢\u0006\u0004\b\u0006\u0010\u0007J\u0010\u0010\n\u001a\u00020\u0002HÆ\u0003¢\u0006\u0004\b\b\u0010\tJ\u0010\u0010\f\u001a\u00020\u0002HÆ\u0003¢\u0006\u0004\b\u000b\u0010\tJ\u0010\u0010\u000e\u001a\u00020\u0002HÆ\u0003¢\u0006\u0004\b\r\u0010\tJ.\u0010\u0011\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\b\b\u0002\u0010\u0004\u001a\u00020\u00022\b\b\u0002\u0010\u0005\u001a\u00020\u0002HÆ\u0001¢\u0006\u0004\b\u000f\u0010\u0010J\u0010\u0010\u0013\u001a\u00020\u0012HÖ\u0001¢\u0006\u0004\b\u0013\u0010\u0014J\u0010\u0010\u0016\u001a\u00020\u0015HÖ\u0001¢\u0006\u0004\b\u0016\u0010\u0017J\u001a\u0010\u001a\u001a\u00020\u00192\b\u0010\u0018\u001a\u0004\u0018\u00010\u0001HÖ\u0003¢\u0006\u0004\b\u001a\u0010\u001bR\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\u001c\u001a\u0004\b\u001d\u0010\tR\u0017\u0010\u0004\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0004\u0010\u001c\u001a\u0004\b\u001e\u0010\tR\u0017\u0010\u0005\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0005\u0010\u001c\u001a\u0004\b\u001f\u0010\t¨\u0006 "}, d2 = {"Lcom/arflix/tv/ui/skin/ArvioRadiusTokens;", "", "Landroidx/compose/ui/unit/Dp;", "sm", "md", "lg", "<init>", "(FFFLkotlin/jvm/internal/h;)V", "component1-D9Ej5fM", "()F", "component1", "component2-D9Ej5fM", "component2", "component3-D9Ej5fM", "component3", "copy-2z7ARbQ", "(FFF)Lcom/arflix/tv/ui/skin/ArvioRadiusTokens;", "copy", "", "toString", "()Ljava/lang/String;", "", "hashCode", "()I", "other", "", "equals", "(Ljava/lang/Object;)Z", "F", "getSm-D9Ej5fM", "getMd-D9Ej5fM", "getLg-D9Ej5fM", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final /* data */ class ArvioRadiusTokens {
    public static final int $stable = 0;
    private final float lg;
    private final float md;
    private final float sm;

    public /* synthetic */ ArvioRadiusTokens(float f10, float f11, float f12, h hVar) {
        this(f10, f11, f12);
    }

    /* JADX INFO: renamed from: copy-2z7ARbQ$default, reason: not valid java name */
    public static /* synthetic */ ArvioRadiusTokens m6511copy2z7ARbQ$default(ArvioRadiusTokens arvioRadiusTokens, float f10, float f11, float f12, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            f10 = arvioRadiusTokens.sm;
        }
        if ((i10 & 2) != 0) {
            f11 = arvioRadiusTokens.md;
        }
        if ((i10 & 4) != 0) {
            f12 = arvioRadiusTokens.lg;
        }
        return arvioRadiusTokens.m6515copy2z7ARbQ(f10, f11, f12);
    }

    /* JADX INFO: renamed from: component1-D9Ej5fM, reason: not valid java name and from getter */
    public final float getSm() {
        return this.sm;
    }

    /* JADX INFO: renamed from: component2-D9Ej5fM, reason: not valid java name and from getter */
    public final float getMd() {
        return this.md;
    }

    /* JADX INFO: renamed from: component3-D9Ej5fM, reason: not valid java name and from getter */
    public final float getLg() {
        return this.lg;
    }

    /* JADX INFO: renamed from: copy-2z7ARbQ, reason: not valid java name */
    public final ArvioRadiusTokens m6515copy2z7ARbQ(float sm, float md, float lg) {
        return new ArvioRadiusTokens(sm, md, lg, null);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof ArvioRadiusTokens)) {
            return false;
        }
        ArvioRadiusTokens arvioRadiusTokens = (ArvioRadiusTokens) other;
        return Dp.m5683equalsimpl0(this.sm, arvioRadiusTokens.sm) && Dp.m5683equalsimpl0(this.md, arvioRadiusTokens.md) && Dp.m5683equalsimpl0(this.lg, arvioRadiusTokens.lg);
    }

    /* JADX INFO: renamed from: getLg-D9Ej5fM, reason: not valid java name */
    public final float m6516getLgD9Ej5fM() {
        return this.lg;
    }

    /* JADX INFO: renamed from: getMd-D9Ej5fM, reason: not valid java name */
    public final float m6517getMdD9Ej5fM() {
        return this.md;
    }

    /* JADX INFO: renamed from: getSm-D9Ej5fM, reason: not valid java name */
    public final float m6518getSmD9Ej5fM() {
        return this.sm;
    }

    public int hashCode() {
        return Dp.m5684hashCodeimpl(this.lg) + a0.c.C(this.md, Dp.m5684hashCodeimpl(this.sm) * 31, 31);
    }

    public String toString() {
        String strM5689toStringimpl = Dp.m5689toStringimpl(this.sm);
        String strM5689toStringimpl2 = Dp.m5689toStringimpl(this.md);
        return a0.c.p(a2.r("ArvioRadiusTokens(sm=", strM5689toStringimpl, ", md=", strM5689toStringimpl2, ", lg="), Dp.m5689toStringimpl(this.lg), ")");
    }

    private ArvioRadiusTokens(float f10, float f11, float f12) {
        this.sm = f10;
        this.md = f11;
        this.lg = f12;
    }
}
