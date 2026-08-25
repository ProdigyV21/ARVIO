package com.arflix.tv.data.api;

import com.google.gson.annotations.SerializedName;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.p;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0087\b\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\u0004\b\u0007\u0010\bJ\t\u0010\r\u001a\u00020\u0003HÆ\u0003J\u000f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005HÆ\u0003J#\u0010\u000f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\u000e\b\u0002\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005HÆ\u0001J\u0014\u0010\u0010\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0013\u001a\u00020\u0003HÖ\u0081\u0004J\n\u0010\u0014\u001a\u00020\u0015HÖ\u0081\u0004R\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u001c\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u00058\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f¨\u0006\u0016"}, d2 = {"Lcom/arflix/tv/data/api/SimklSeasonRef;", "", "number", "", "episodes", "", "Lcom/arflix/tv/data/api/SimklEpisodeRef;", "<init>", "(ILjava/util/List;)V", "getNumber", "()I", "getEpisodes", "()Ljava/util/List;", "component1", "component2", "copy", "equals", "", "other", "hashCode", "toString", "", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final /* data */ class SimklSeasonRef {
    public static final int $stable = 0;

    @SerializedName("episodes")
    private final List<SimklEpisodeRef> episodes;

    @SerializedName("number")
    private final int number;

    public SimklSeasonRef(int i10, List<SimklEpisodeRef> list) {
        this.number = i10;
        this.episodes = list;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ SimklSeasonRef copy$default(SimklSeasonRef simklSeasonRef, int i10, List list, int i11, Object obj) {
        if ((i11 & 1) != 0) {
            i10 = simklSeasonRef.number;
        }
        if ((i11 & 2) != 0) {
            list = simklSeasonRef.episodes;
        }
        return simklSeasonRef.copy(i10, list);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final int getNumber() {
        return this.number;
    }

    public final List<SimklEpisodeRef> component2() {
        return this.episodes;
    }

    public final SimklSeasonRef copy(int number, List<SimklEpisodeRef> episodes) {
        return new SimklSeasonRef(number, episodes);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof SimklSeasonRef)) {
            return false;
        }
        SimklSeasonRef simklSeasonRef = (SimklSeasonRef) other;
        return this.number == simklSeasonRef.number && p.a(this.episodes, simklSeasonRef.episodes);
    }

    public final List<SimklEpisodeRef> getEpisodes() {
        return this.episodes;
    }

    public final int getNumber() {
        return this.number;
    }

    public int hashCode() {
        return this.episodes.hashCode() + (this.number * 31);
    }

    public String toString() {
        return "SimklSeasonRef(number=" + this.number + ", episodes=" + this.episodes + ")";
    }
}
