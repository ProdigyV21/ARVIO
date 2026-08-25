package com.arflix.tv.data.api;

import com.google.gson.annotations.SerializedName;
import kotlin.Metadata;
import kotlin.jvm.internal.p;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\r\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B#\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0005¢\u0006\u0004\b\u0007\u0010\bJ\t\u0010\u000e\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000f\u001a\u00020\u0005HÆ\u0003J\u000b\u0010\u0010\u001a\u0004\u0018\u00010\u0005HÆ\u0003J)\u0010\u0011\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0005HÆ\u0001J\u0014\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0015\u001a\u00020\u0016HÖ\u0081\u0004J\n\u0010\u0017\u001a\u00020\u0005HÖ\u0081\u0004R\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0016\u0010\u0004\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0018\u0010\u0006\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\f¨\u0006\u0018"}, d2 = {"Lcom/arflix/tv/data/api/AniSkipResult;", "", "interval", "Lcom/arflix/tv/data/api/AniSkipInterval;", "skipType", "", "skipId", "<init>", "(Lcom/arflix/tv/data/api/AniSkipInterval;Ljava/lang/String;Ljava/lang/String;)V", "getInterval", "()Lcom/arflix/tv/data/api/AniSkipInterval;", "getSkipType", "()Ljava/lang/String;", "getSkipId", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "", "toString", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final /* data */ class AniSkipResult {
    public static final int $stable = 0;

    @SerializedName("interval")
    private final AniSkipInterval interval;

    @SerializedName("skipId")
    private final String skipId;

    @SerializedName("skipType")
    private final String skipType;

    public AniSkipResult(AniSkipInterval aniSkipInterval, String str, String str2) {
        this.interval = aniSkipInterval;
        this.skipType = str;
        this.skipId = str2;
    }

    public static /* synthetic */ AniSkipResult copy$default(AniSkipResult aniSkipResult, AniSkipInterval aniSkipInterval, String str, String str2, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            aniSkipInterval = aniSkipResult.interval;
        }
        if ((i10 & 2) != 0) {
            str = aniSkipResult.skipType;
        }
        if ((i10 & 4) != 0) {
            str2 = aniSkipResult.skipId;
        }
        return aniSkipResult.copy(aniSkipInterval, str, str2);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final AniSkipInterval getInterval() {
        return this.interval;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getSkipType() {
        return this.skipType;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final String getSkipId() {
        return this.skipId;
    }

    public final AniSkipResult copy(AniSkipInterval interval, String skipType, String skipId) {
        return new AniSkipResult(interval, skipType, skipId);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof AniSkipResult)) {
            return false;
        }
        AniSkipResult aniSkipResult = (AniSkipResult) other;
        return p.a(this.interval, aniSkipResult.interval) && p.a(this.skipType, aniSkipResult.skipType) && p.a(this.skipId, aniSkipResult.skipId);
    }

    public final AniSkipInterval getInterval() {
        return this.interval;
    }

    public final String getSkipId() {
        return this.skipId;
    }

    public final String getSkipType() {
        return this.skipType;
    }

    public int hashCode() {
        int iC = androidx.compose.foundation.c.c(this.interval.hashCode() * 31, 31, this.skipType);
        String str = this.skipId;
        return iC + (str == null ? 0 : str.hashCode());
    }

    public String toString() {
        AniSkipInterval aniSkipInterval = this.interval;
        String str = this.skipType;
        String str2 = this.skipId;
        StringBuilder sb2 = new StringBuilder("AniSkipResult(interval=");
        sb2.append(aniSkipInterval);
        sb2.append(", skipType=");
        sb2.append(str);
        sb2.append(", skipId=");
        return a0.c.p(sb2, str2, ")");
    }

    public /* synthetic */ AniSkipResult(AniSkipInterval aniSkipInterval, String str, String str2, int i10, kotlin.jvm.internal.h hVar) {
        this(aniSkipInterval, str, (i10 & 4) != 0 ? null : str2);
    }
}
