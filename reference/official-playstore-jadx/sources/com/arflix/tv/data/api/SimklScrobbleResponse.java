package com.arflix.tv.data.api;

import com.google.gson.annotations.SerializedName;
import kotlin.Metadata;
import kotlin.jvm.internal.p;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0007\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B\u001f\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u000b\u0010\r\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0010\u0010\u000e\u001a\u0004\u0018\u00010\u0005HÆ\u0003¢\u0006\u0002\u0010\u000bJ&\u0010\u000f\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005HÆ\u0001¢\u0006\u0002\u0010\u0010J\u0014\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0014\u001a\u00020\u0015HÖ\u0081\u0004J\n\u0010\u0016\u001a\u00020\u0003HÖ\u0081\u0004R\u0018\u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u001a\u0010\u0004\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\n\n\u0002\u0010\f\u001a\u0004\b\n\u0010\u000b¨\u0006\u0017"}, d2 = {"Lcom/arflix/tv/data/api/SimklScrobbleResponse;", "", "action", "", "progress", "", "<init>", "(Ljava/lang/String;Ljava/lang/Float;)V", "getAction", "()Ljava/lang/String;", "getProgress", "()Ljava/lang/Float;", "Ljava/lang/Float;", "component1", "component2", "copy", "(Ljava/lang/String;Ljava/lang/Float;)Lcom/arflix/tv/data/api/SimklScrobbleResponse;", "equals", "", "other", "hashCode", "", "toString", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final /* data */ class SimklScrobbleResponse {
    public static final int $stable = 0;

    @SerializedName("action")
    private final String action;

    @SerializedName("progress")
    private final Float progress;

    /* JADX WARN: Multi-variable type inference failed */
    public SimklScrobbleResponse() {
        this(null, 0 == true ? 1 : 0, 3, 0 == true ? 1 : 0);
    }

    public static /* synthetic */ SimklScrobbleResponse copy$default(SimklScrobbleResponse simklScrobbleResponse, String str, Float f10, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            str = simklScrobbleResponse.action;
        }
        if ((i10 & 2) != 0) {
            f10 = simklScrobbleResponse.progress;
        }
        return simklScrobbleResponse.copy(str, f10);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getAction() {
        return this.action;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final Float getProgress() {
        return this.progress;
    }

    public final SimklScrobbleResponse copy(String action, Float progress) {
        return new SimklScrobbleResponse(action, progress);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof SimklScrobbleResponse)) {
            return false;
        }
        SimklScrobbleResponse simklScrobbleResponse = (SimklScrobbleResponse) other;
        return p.a(this.action, simklScrobbleResponse.action) && p.a(this.progress, simklScrobbleResponse.progress);
    }

    public final String getAction() {
        return this.action;
    }

    public final Float getProgress() {
        return this.progress;
    }

    public int hashCode() {
        String str = this.action;
        int iHashCode = (str == null ? 0 : str.hashCode()) * 31;
        Float f10 = this.progress;
        return iHashCode + (f10 != null ? f10.hashCode() : 0);
    }

    public String toString() {
        return "SimklScrobbleResponse(action=" + this.action + ", progress=" + this.progress + ")";
    }

    public SimklScrobbleResponse(String str, Float f10) {
        this.action = str;
        this.progress = f10;
    }

    public /* synthetic */ SimklScrobbleResponse(String str, Float f10, int i10, kotlin.jvm.internal.h hVar) {
        this((i10 & 1) != 0 ? null : str, (i10 & 2) != 0 ? null : f10);
    }
}
