package com.arflix.tv.data.api;

import com.google.gson.annotations.SerializedName;
import kotlin.Metadata;
import kotlin.jvm.internal.p;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0019\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0087\b\u0018\u00002\u00020\u0001BW\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\n\u0012\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\f¢\u0006\u0004\b\r\u0010\u000eJ\t\u0010\u001c\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001d\u001a\u00020\u0003HÆ\u0003J\u0010\u0010\u001e\u001a\u0004\u0018\u00010\u0006HÆ\u0003¢\u0006\u0002\u0010\u0013J\u0010\u0010\u001f\u001a\u0004\u0018\u00010\u0006HÆ\u0003¢\u0006\u0002\u0010\u0013J\u0010\u0010 \u001a\u0004\u0018\u00010\u0006HÆ\u0003¢\u0006\u0002\u0010\u0013J\u0010\u0010!\u001a\u0004\u0018\u00010\nHÆ\u0003¢\u0006\u0002\u0010\u0018J\u000b\u0010\"\u001a\u0004\u0018\u00010\fHÆ\u0003J^\u0010#\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\n2\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\fHÆ\u0001¢\u0006\u0002\u0010$J\u0014\u0010%\u001a\u00020&2\b\u0010'\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010(\u001a\u00020\nHÖ\u0081\u0004J\n\u0010)\u001a\u00020\fHÖ\u0081\u0004R\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0016\u0010\u0004\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0010R\u001a\u0010\u0005\u001a\u0004\u0018\u00010\u00068\u0006X\u0087\u0004¢\u0006\n\n\u0002\u0010\u0014\u001a\u0004\b\u0012\u0010\u0013R\u001a\u0010\u0007\u001a\u0004\u0018\u00010\u00068\u0006X\u0087\u0004¢\u0006\n\n\u0002\u0010\u0014\u001a\u0004\b\u0015\u0010\u0013R\u001a\u0010\b\u001a\u0004\u0018\u00010\u00068\u0006X\u0087\u0004¢\u0006\n\n\u0002\u0010\u0014\u001a\u0004\b\u0016\u0010\u0013R\u001a\u0010\t\u001a\u0004\u0018\u00010\n8\u0006X\u0087\u0004¢\u0006\n\n\u0002\u0010\u0019\u001a\u0004\b\u0017\u0010\u0018R\u0018\u0010\u000b\u001a\u0004\u0018\u00010\f8\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u001b¨\u0006*"}, d2 = {"Lcom/arflix/tv/data/api/IntroDbSegment;", "", "startMs", "", "endMs", "startSec", "", "endSec", "confidence", "submissionCount", "", "updatedAt", "", "<init>", "(JJLjava/lang/Double;Ljava/lang/Double;Ljava/lang/Double;Ljava/lang/Integer;Ljava/lang/String;)V", "getStartMs", "()J", "getEndMs", "getStartSec", "()Ljava/lang/Double;", "Ljava/lang/Double;", "getEndSec", "getConfidence", "getSubmissionCount", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getUpdatedAt", "()Ljava/lang/String;", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "copy", "(JJLjava/lang/Double;Ljava/lang/Double;Ljava/lang/Double;Ljava/lang/Integer;Ljava/lang/String;)Lcom/arflix/tv/data/api/IntroDbSegment;", "equals", "", "other", "hashCode", "toString", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final /* data */ class IntroDbSegment {
    public static final int $stable = 0;

    @SerializedName("confidence")
    private final Double confidence;

    @SerializedName("end_ms")
    private final long endMs;

    @SerializedName("end_sec")
    private final Double endSec;

    @SerializedName("start_ms")
    private final long startMs;

    @SerializedName("start_sec")
    private final Double startSec;

    @SerializedName("submission_count")
    private final Integer submissionCount;

    @SerializedName("updated_at")
    private final String updatedAt;

    public IntroDbSegment() {
        this(0L, 0L, null, null, null, null, null, 127, null);
    }

    public static /* synthetic */ IntroDbSegment copy$default(IntroDbSegment introDbSegment, long j10, long j11, Double d4, Double d10, Double d11, Integer num, String str, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            j10 = introDbSegment.startMs;
        }
        long j12 = j10;
        if ((i10 & 2) != 0) {
            j11 = introDbSegment.endMs;
        }
        long j13 = j11;
        if ((i10 & 4) != 0) {
            d4 = introDbSegment.startSec;
        }
        return introDbSegment.copy(j12, j13, d4, (i10 & 8) != 0 ? introDbSegment.endSec : d10, (i10 & 16) != 0 ? introDbSegment.confidence : d11, (i10 & 32) != 0 ? introDbSegment.submissionCount : num, (i10 & 64) != 0 ? introDbSegment.updatedAt : str);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final long getStartMs() {
        return this.startMs;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final long getEndMs() {
        return this.endMs;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final Double getStartSec() {
        return this.startSec;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final Double getEndSec() {
        return this.endSec;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final Double getConfidence() {
        return this.confidence;
    }

    /* JADX INFO: renamed from: component6, reason: from getter */
    public final Integer getSubmissionCount() {
        return this.submissionCount;
    }

    /* JADX INFO: renamed from: component7, reason: from getter */
    public final String getUpdatedAt() {
        return this.updatedAt;
    }

    public final IntroDbSegment copy(long startMs, long endMs, Double startSec, Double endSec, Double confidence, Integer submissionCount, String updatedAt) {
        return new IntroDbSegment(startMs, endMs, startSec, endSec, confidence, submissionCount, updatedAt);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof IntroDbSegment)) {
            return false;
        }
        IntroDbSegment introDbSegment = (IntroDbSegment) other;
        return this.startMs == introDbSegment.startMs && this.endMs == introDbSegment.endMs && p.a(this.startSec, introDbSegment.startSec) && p.a(this.endSec, introDbSegment.endSec) && p.a(this.confidence, introDbSegment.confidence) && p.a(this.submissionCount, introDbSegment.submissionCount) && p.a(this.updatedAt, introDbSegment.updatedAt);
    }

    public final Double getConfidence() {
        return this.confidence;
    }

    public final long getEndMs() {
        return this.endMs;
    }

    public final Double getEndSec() {
        return this.endSec;
    }

    public final long getStartMs() {
        return this.startMs;
    }

    public final Double getStartSec() {
        return this.startSec;
    }

    public final Integer getSubmissionCount() {
        return this.submissionCount;
    }

    public final String getUpdatedAt() {
        return this.updatedAt;
    }

    public int hashCode() {
        long j10 = this.startMs;
        long j11 = this.endMs;
        int i10 = ((((int) (j10 ^ (j10 >>> 32))) * 31) + ((int) ((j11 >>> 32) ^ j11))) * 31;
        Double d4 = this.startSec;
        int iHashCode = (i10 + (d4 == null ? 0 : d4.hashCode())) * 31;
        Double d10 = this.endSec;
        int iHashCode2 = (iHashCode + (d10 == null ? 0 : d10.hashCode())) * 31;
        Double d11 = this.confidence;
        int iHashCode3 = (iHashCode2 + (d11 == null ? 0 : d11.hashCode())) * 31;
        Integer num = this.submissionCount;
        int iHashCode4 = (iHashCode3 + (num == null ? 0 : num.hashCode())) * 31;
        String str = this.updatedAt;
        return iHashCode4 + (str != null ? str.hashCode() : 0);
    }

    public String toString() {
        long j10 = this.startMs;
        long j11 = this.endMs;
        Double d4 = this.startSec;
        Double d10 = this.endSec;
        Double d11 = this.confidence;
        Integer num = this.submissionCount;
        String str = this.updatedAt;
        StringBuilder sbR = androidx.compose.material3.d.r(j10, "IntroDbSegment(startMs=", ", endMs=");
        sbR.append(j11);
        sbR.append(", startSec=");
        sbR.append(d4);
        sbR.append(", endSec=");
        sbR.append(d10);
        sbR.append(", confidence=");
        sbR.append(d11);
        sbR.append(", submissionCount=");
        sbR.append(num);
        sbR.append(", updatedAt=");
        sbR.append(str);
        sbR.append(")");
        return sbR.toString();
    }

    public IntroDbSegment(long j10, long j11, Double d4, Double d10, Double d11, Integer num, String str) {
        this.startMs = j10;
        this.endMs = j11;
        this.startSec = d4;
        this.endSec = d10;
        this.confidence = d11;
        this.submissionCount = num;
        this.updatedAt = str;
    }

    public /* synthetic */ IntroDbSegment(long j10, long j11, Double d4, Double d10, Double d11, Integer num, String str, int i10, kotlin.jvm.internal.h hVar) {
        this((i10 & 1) != 0 ? 0L : j10, (i10 & 2) != 0 ? 0L : j11, (i10 & 4) != 0 ? null : d4, (i10 & 8) != 0 ? null : d10, (i10 & 16) != 0 ? null : d11, (i10 & 32) != 0 ? null : num, (i10 & 64) != 0 ? null : str);
    }
}
