package com.arflix.tv.ui.screens.tv.live;

import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u000e\n\u0002\u0010\b\n\u0002\b\u0002\b\u0082\b\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tJ\t\u0010\u000f\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0010\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0011\u001a\u00020\u0007HÆ\u0003J'\u0010\u0012\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0007HÆ\u0001J\u0014\u0010\u0013\u001a\u00020\u00072\b\u0010\u0014\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0015\u001a\u00020\u0016HÖ\u0081\u0004J\n\u0010\u0017\u001a\u00020\u0005HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u000e¨\u0006\u0018"}, d2 = {"Lcom/arflix/tv/ui/screens/tv/live/TimeSlot;", "", "millis", "", "label", "", "isNow", "", "<init>", "(JLjava/lang/String;Z)V", "getMillis", "()J", "getLabel", "()Ljava/lang/String;", "()Z", "component1", "component2", "component3", "copy", "equals", "other", "hashCode", "", "toString", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
final /* data */ class TimeSlot {
    private final boolean isNow;
    private final String label;
    private final long millis;

    public TimeSlot(long j10, String str, boolean z) {
        this.millis = j10;
        this.label = str;
        this.isNow = z;
    }

    public static /* synthetic */ TimeSlot copy$default(TimeSlot timeSlot, long j10, String str, boolean z, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            j10 = timeSlot.millis;
        }
        if ((i10 & 2) != 0) {
            str = timeSlot.label;
        }
        if ((i10 & 4) != 0) {
            z = timeSlot.isNow;
        }
        return timeSlot.copy(j10, str, z);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final long getMillis() {
        return this.millis;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getLabel() {
        return this.label;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final boolean getIsNow() {
        return this.isNow;
    }

    public final TimeSlot copy(long millis, String label, boolean isNow) {
        return new TimeSlot(millis, label, isNow);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof TimeSlot)) {
            return false;
        }
        TimeSlot timeSlot = (TimeSlot) other;
        return this.millis == timeSlot.millis && kotlin.jvm.internal.p.a(this.label, timeSlot.label) && this.isNow == timeSlot.isNow;
    }

    public final String getLabel() {
        return this.label;
    }

    public final long getMillis() {
        return this.millis;
    }

    public int hashCode() {
        long j10 = this.millis;
        return androidx.compose.foundation.c.c(((int) (j10 ^ (j10 >>> 32))) * 31, 31, this.label) + (this.isNow ? 1231 : 1237);
    }

    public final boolean isNow() {
        return this.isNow;
    }

    public String toString() {
        return "TimeSlot(millis=" + this.millis + ", label=" + this.label + ", isNow=" + this.isNow + ")";
    }
}
