package com.arflix.tv.data.api;

import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.p;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0087\b\u0018\u00002\u00020\u0001B!\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0010\b\u0002\u0010\u0004\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0005¢\u0006\u0004\b\u0007\u0010\bJ\t\u0010\r\u001a\u00020\u0003HÆ\u0003J\u0011\u0010\u000e\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0005HÆ\u0003J%\u0010\u000f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\u0010\b\u0002\u0010\u0004\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0005HÆ\u0001J\u0014\u0010\u0010\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0013\u001a\u00020\u0003HÖ\u0081\u0004J\n\u0010\u0014\u001a\u00020\u0015HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0019\u0010\u0004\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f¨\u0006\u0016"}, d2 = {"Lcom/arflix/tv/data/api/TraktSeasonNumber;", "", "number", "", "episodes", "", "Lcom/arflix/tv/data/api/TraktEpisodeNumber;", "<init>", "(ILjava/util/List;)V", "getNumber", "()I", "getEpisodes", "()Ljava/util/List;", "component1", "component2", "copy", "equals", "", "other", "hashCode", "toString", "", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final /* data */ class TraktSeasonNumber {
    public static final int $stable = 0;
    private final List<TraktEpisodeNumber> episodes;
    private final int number;

    public TraktSeasonNumber(int i10, List<TraktEpisodeNumber> list) {
        this.number = i10;
        this.episodes = list;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ TraktSeasonNumber copy$default(TraktSeasonNumber traktSeasonNumber, int i10, List list, int i11, Object obj) {
        if ((i11 & 1) != 0) {
            i10 = traktSeasonNumber.number;
        }
        if ((i11 & 2) != 0) {
            list = traktSeasonNumber.episodes;
        }
        return traktSeasonNumber.copy(i10, list);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final int getNumber() {
        return this.number;
    }

    public final List<TraktEpisodeNumber> component2() {
        return this.episodes;
    }

    public final TraktSeasonNumber copy(int number, List<TraktEpisodeNumber> episodes) {
        return new TraktSeasonNumber(number, episodes);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof TraktSeasonNumber)) {
            return false;
        }
        TraktSeasonNumber traktSeasonNumber = (TraktSeasonNumber) other;
        return this.number == traktSeasonNumber.number && p.a(this.episodes, traktSeasonNumber.episodes);
    }

    public final List<TraktEpisodeNumber> getEpisodes() {
        return this.episodes;
    }

    public final int getNumber() {
        return this.number;
    }

    public int hashCode() {
        int i10 = this.number * 31;
        List<TraktEpisodeNumber> list = this.episodes;
        return i10 + (list == null ? 0 : list.hashCode());
    }

    public String toString() {
        return "TraktSeasonNumber(number=" + this.number + ", episodes=" + this.episodes + ")";
    }

    public /* synthetic */ TraktSeasonNumber(int i10, List list, int i11, kotlin.jvm.internal.h hVar) {
        this(i10, (i11 & 2) != 0 ? null : list);
    }
}
