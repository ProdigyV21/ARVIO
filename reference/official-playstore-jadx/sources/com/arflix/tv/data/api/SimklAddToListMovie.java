package com.arflix.tv.data.api;

import com.google.gson.annotations.SerializedName;
import kotlin.Metadata;
import kotlin.jvm.internal.p;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B\u0019\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\t\u0010\r\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0014\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0012\u001a\u00020\u0013HÖ\u0081\u0004J\n\u0010\u0014\u001a\u00020\u0003HÖ\u0081\u0004R\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0016\u0010\u0004\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0015"}, d2 = {"Lcom/arflix/tv/data/api/SimklAddToListMovie;", "", "to", "", "ids", "Lcom/arflix/tv/data/api/SimklIds;", "<init>", "(Ljava/lang/String;Lcom/arflix/tv/data/api/SimklIds;)V", "getTo", "()Ljava/lang/String;", "getIds", "()Lcom/arflix/tv/data/api/SimklIds;", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final /* data */ class SimklAddToListMovie {
    public static final int $stable = 0;

    @SerializedName("ids")
    private final SimklIds ids;

    @SerializedName("to")
    private final String to;

    public SimklAddToListMovie(String str, SimklIds simklIds) {
        this.to = str;
        this.ids = simklIds;
    }

    public static /* synthetic */ SimklAddToListMovie copy$default(SimklAddToListMovie simklAddToListMovie, String str, SimklIds simklIds, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            str = simklAddToListMovie.to;
        }
        if ((i10 & 2) != 0) {
            simklIds = simklAddToListMovie.ids;
        }
        return simklAddToListMovie.copy(str, simklIds);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getTo() {
        return this.to;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final SimklIds getIds() {
        return this.ids;
    }

    public final SimklAddToListMovie copy(String to, SimklIds ids) {
        return new SimklAddToListMovie(to, ids);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof SimklAddToListMovie)) {
            return false;
        }
        SimklAddToListMovie simklAddToListMovie = (SimklAddToListMovie) other;
        return p.a(this.to, simklAddToListMovie.to) && p.a(this.ids, simklAddToListMovie.ids);
    }

    public final SimklIds getIds() {
        return this.ids;
    }

    public final String getTo() {
        return this.to;
    }

    public int hashCode() {
        return this.ids.hashCode() + (this.to.hashCode() * 31);
    }

    public String toString() {
        return "SimklAddToListMovie(to=" + this.to + ", ids=" + this.ids + ")";
    }

    public /* synthetic */ SimklAddToListMovie(String str, SimklIds simklIds, int i10, kotlin.jvm.internal.h hVar) {
        this((i10 & 1) != 0 ? "plantowatch" : str, simklIds);
    }
}
