package com.arflix.tv.ui.screens.tv;

import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0016\n\u0002\u0010\b\n\u0002\b\u0002\b\u0082\b\u0018\u00002\u00020\u0001B;\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\b\b\u0002\u0010\t\u001a\u00020\b\u0012\b\b\u0002\u0010\n\u001a\u00020\b¢\u0006\u0004\b\u000b\u0010\fJ\t\u0010\u0015\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0016\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0017\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0018\u001a\u00020\bHÆ\u0003J\t\u0010\u0019\u001a\u00020\bHÆ\u0003J\t\u0010\u001a\u001a\u00020\bHÆ\u0003JE\u0010\u001b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\b2\b\b\u0002\u0010\n\u001a\u00020\bHÆ\u0001J\u0014\u0010\u001c\u001a\u00020\b2\b\u0010\u001d\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u001e\u001a\u00020\u001fHÖ\u0081\u0004J\n\u0010 \u001a\u00020\u0003HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0010R\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\u0012R\u0011\u0010\t\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\u0012R\u0011\u0010\n\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u0012R\u0011\u0010\u0013\u001a\u00020\u00058F¢\u0006\u0006\u001a\u0004\b\u0014\u0010\u0010¨\u0006!"}, d2 = {"Lcom/arflix/tv/ui/screens/tv/ProgramSegment;", "", "label", "", "startRatio", "", "endRatio", "isNow", "", "isFiller", "isPast", "<init>", "(Ljava/lang/String;FFZZZ)V", "getLabel", "()Ljava/lang/String;", "getStartRatio", "()F", "getEndRatio", "()Z", "widthRatio", "getWidthRatio", "component1", "component2", "component3", "component4", "component5", "component6", "copy", "equals", "other", "hashCode", "", "toString", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
final /* data */ class ProgramSegment {
    private final float endRatio;
    private final boolean isFiller;
    private final boolean isNow;
    private final boolean isPast;
    private final String label;
    private final float startRatio;

    public ProgramSegment(String str, float f10, float f11, boolean z, boolean z5, boolean z10) {
        this.label = str;
        this.startRatio = f10;
        this.endRatio = f11;
        this.isNow = z;
        this.isFiller = z5;
        this.isPast = z10;
    }

    public static /* synthetic */ ProgramSegment copy$default(ProgramSegment programSegment, String str, float f10, float f11, boolean z, boolean z5, boolean z10, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            str = programSegment.label;
        }
        if ((i10 & 2) != 0) {
            f10 = programSegment.startRatio;
        }
        if ((i10 & 4) != 0) {
            f11 = programSegment.endRatio;
        }
        if ((i10 & 8) != 0) {
            z = programSegment.isNow;
        }
        if ((i10 & 16) != 0) {
            z5 = programSegment.isFiller;
        }
        if ((i10 & 32) != 0) {
            z10 = programSegment.isPast;
        }
        boolean z11 = z5;
        boolean z12 = z10;
        return programSegment.copy(str, f10, f11, z, z11, z12);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getLabel() {
        return this.label;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final float getStartRatio() {
        return this.startRatio;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final float getEndRatio() {
        return this.endRatio;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final boolean getIsNow() {
        return this.isNow;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final boolean getIsFiller() {
        return this.isFiller;
    }

    /* JADX INFO: renamed from: component6, reason: from getter */
    public final boolean getIsPast() {
        return this.isPast;
    }

    public final ProgramSegment copy(String label, float startRatio, float endRatio, boolean isNow, boolean isFiller, boolean isPast) {
        return new ProgramSegment(label, startRatio, endRatio, isNow, isFiller, isPast);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof ProgramSegment)) {
            return false;
        }
        ProgramSegment programSegment = (ProgramSegment) other;
        return kotlin.jvm.internal.p.a(this.label, programSegment.label) && Float.compare(this.startRatio, programSegment.startRatio) == 0 && Float.compare(this.endRatio, programSegment.endRatio) == 0 && this.isNow == programSegment.isNow && this.isFiller == programSegment.isFiller && this.isPast == programSegment.isPast;
    }

    public final float getEndRatio() {
        return this.endRatio;
    }

    public final String getLabel() {
        return this.label;
    }

    public final float getStartRatio() {
        return this.startRatio;
    }

    public final float getWidthRatio() {
        float f10 = this.endRatio - this.startRatio;
        if (f10 < 0.0f) {
            return 0.0f;
        }
        return f10;
    }

    public int hashCode() {
        return ((((a0.c.b(this.endRatio, a0.c.b(this.startRatio, this.label.hashCode() * 31, 31), 31) + (this.isNow ? 1231 : 1237)) * 31) + (this.isFiller ? 1231 : 1237)) * 31) + (this.isPast ? 1231 : 1237);
    }

    public final boolean isFiller() {
        return this.isFiller;
    }

    public final boolean isNow() {
        return this.isNow;
    }

    public final boolean isPast() {
        return this.isPast;
    }

    public String toString() {
        return "ProgramSegment(label=" + this.label + ", startRatio=" + this.startRatio + ", endRatio=" + this.endRatio + ", isNow=" + this.isNow + ", isFiller=" + this.isFiller + ", isPast=" + this.isPast + ")";
    }

    public /* synthetic */ ProgramSegment(String str, float f10, float f11, boolean z, boolean z5, boolean z10, int i10, kotlin.jvm.internal.h hVar) {
        this(str, f10, f11, z, (i10 & 16) != 0 ? false : z5, (i10 & 32) != 0 ? false : z10);
    }
}
