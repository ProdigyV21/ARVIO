package com.arflix.tv.ui.screens.tv.live;

import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0082\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\u000e\u0010\n\u001a\u00020\u00032\u0006\u0010\u000b\u001a\u00020\u0003J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\t\u0010\r\u001a\u00020\u0003HÆ\u0003J\u001d\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0003HÆ\u0001J\u0014\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0012\u001a\u00020\u0003HÖ\u0081\u0004J\n\u0010\u0013\u001a\u00020\u0014HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\b¨\u0006\u0015"}, d2 = {"Lcom/arflix/tv/ui/screens/tv/live/ProgramFocusTarget;", "", "startMin", "", "endMin", "<init>", "(II)V", "getStartMin", "()I", "getEndMin", "distanceTo", "anchorStartMin", "component1", "component2", "copy", "equals", "", "other", "hashCode", "toString", "", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
final /* data */ class ProgramFocusTarget {
    private final int endMin;
    private final int startMin;

    public ProgramFocusTarget(int i10, int i11) {
        this.startMin = i10;
        this.endMin = i11;
    }

    public static /* synthetic */ ProgramFocusTarget copy$default(ProgramFocusTarget programFocusTarget, int i10, int i11, int i12, Object obj) {
        if ((i12 & 1) != 0) {
            i10 = programFocusTarget.startMin;
        }
        if ((i12 & 2) != 0) {
            i11 = programFocusTarget.endMin;
        }
        return programFocusTarget.copy(i10, i11);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final int getStartMin() {
        return this.startMin;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final int getEndMin() {
        return this.endMin;
    }

    public final ProgramFocusTarget copy(int startMin, int endMin) {
        return new ProgramFocusTarget(startMin, endMin);
    }

    public final int distanceTo(int anchorStartMin) {
        int i10 = this.startMin;
        if (anchorStartMin < i10) {
            return i10 - anchorStartMin;
        }
        int i11 = this.endMin;
        if (anchorStartMin > i11) {
            return anchorStartMin - i11;
        }
        return 0;
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof ProgramFocusTarget)) {
            return false;
        }
        ProgramFocusTarget programFocusTarget = (ProgramFocusTarget) other;
        return this.startMin == programFocusTarget.startMin && this.endMin == programFocusTarget.endMin;
    }

    public final int getEndMin() {
        return this.endMin;
    }

    public final int getStartMin() {
        return this.startMin;
    }

    public int hashCode() {
        return (this.startMin * 31) + this.endMin;
    }

    public String toString() {
        return androidx.compose.foundation.c.s("ProgramFocusTarget(startMin=", this.startMin, ", endMin=", this.endMin, ")");
    }
}
