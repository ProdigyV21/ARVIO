package com.arflix.tv.data.api;

import com.google.gson.annotations.SerializedName;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0006\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0087\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\t\u0010\n\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\u001d\u0010\f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0003HÆ\u0001J\u0014\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0010\u001a\u00020\u0011HÖ\u0081\u0004J\n\u0010\u0012\u001a\u00020\u0013HÖ\u0081\u0004R\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0016\u0010\u0004\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\b¨\u0006\u0014"}, d2 = {"Lcom/arflix/tv/data/api/AniSkipInterval;", "", "startTime", "", "endTime", "<init>", "(DD)V", "getStartTime", "()D", "getEndTime", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final /* data */ class AniSkipInterval {
    public static final int $stable = 0;

    @SerializedName("endTime")
    private final double endTime;

    @SerializedName("startTime")
    private final double startTime;

    public AniSkipInterval(double d4, double d10) {
        this.startTime = d4;
        this.endTime = d10;
    }

    public static /* synthetic */ AniSkipInterval copy$default(AniSkipInterval aniSkipInterval, double d4, double d10, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            d4 = aniSkipInterval.startTime;
        }
        if ((i10 & 2) != 0) {
            d10 = aniSkipInterval.endTime;
        }
        return aniSkipInterval.copy(d4, d10);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final double getStartTime() {
        return this.startTime;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final double getEndTime() {
        return this.endTime;
    }

    public final AniSkipInterval copy(double startTime, double endTime) {
        return new AniSkipInterval(startTime, endTime);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof AniSkipInterval)) {
            return false;
        }
        AniSkipInterval aniSkipInterval = (AniSkipInterval) other;
        return Double.compare(this.startTime, aniSkipInterval.startTime) == 0 && Double.compare(this.endTime, aniSkipInterval.endTime) == 0;
    }

    public final double getEndTime() {
        return this.endTime;
    }

    public final double getStartTime() {
        return this.startTime;
    }

    public int hashCode() {
        long jDoubleToLongBits = Double.doubleToLongBits(this.startTime);
        int i10 = ((int) (jDoubleToLongBits ^ (jDoubleToLongBits >>> 32))) * 31;
        long jDoubleToLongBits2 = Double.doubleToLongBits(this.endTime);
        return i10 + ((int) ((jDoubleToLongBits2 >>> 32) ^ jDoubleToLongBits2));
    }

    public String toString() {
        return "AniSkipInterval(startTime=" + this.startTime + ", endTime=" + this.endTime + ")";
    }
}
