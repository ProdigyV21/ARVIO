package com.arflix.tv.data.api;

import kotlin.Metadata;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0087\b\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\t\u0010\b\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\t\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0014\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\r\u001a\u00020\u0003HÖ\u0081\u0004J\n\u0010\u000e\u001a\u00020\u000fHÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0010"}, d2 = {"Lcom/arflix/tv/data/api/TraktEpisodeNumber;", "", "number", "", "<init>", "(I)V", "getNumber", "()I", "component1", "copy", "equals", "", "other", "hashCode", "toString", "", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final /* data */ class TraktEpisodeNumber {
    public static final int $stable = 0;
    private final int number;

    public TraktEpisodeNumber(int i10) {
        this.number = i10;
    }

    public static /* synthetic */ TraktEpisodeNumber copy$default(TraktEpisodeNumber traktEpisodeNumber, int i10, int i11, Object obj) {
        if ((i11 & 1) != 0) {
            i10 = traktEpisodeNumber.number;
        }
        return traktEpisodeNumber.copy(i10);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final int getNumber() {
        return this.number;
    }

    public final TraktEpisodeNumber copy(int number) {
        return new TraktEpisodeNumber(number);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        return (other instanceof TraktEpisodeNumber) && this.number == ((TraktEpisodeNumber) other).number;
    }

    public final int getNumber() {
        return this.number;
    }

    public int hashCode() {
        return this.number;
    }

    public String toString() {
        return androidx.compose.foundation.c.o(this.number, "TraktEpisodeNumber(number=", ")");
    }
}
