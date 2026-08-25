package com.arflix.tv.ui.screens.tv.live;

import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0082\b\u0018\u00002\u00020\u0001B\u0019\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\r\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u001f\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005HÆ\u0001J\u0014\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0012\u001a\u00020\u0013HÖ\u0081\u0004J\n\u0010\u0014\u001a\u00020\u0005HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0015"}, d2 = {"Lcom/arflix/tv/ui/screens/tv/live/SearchResult;", "", "channel", "Lcom/arflix/tv/ui/screens/tv/live/EnrichedChannel;", "matchText", "", "<init>", "(Lcom/arflix/tv/ui/screens/tv/live/EnrichedChannel;Ljava/lang/String;)V", "getChannel", "()Lcom/arflix/tv/ui/screens/tv/live/EnrichedChannel;", "getMatchText", "()Ljava/lang/String;", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
final /* data */ class SearchResult {
    private final EnrichedChannel channel;
    private final String matchText;

    public SearchResult(EnrichedChannel enrichedChannel, String str) {
        this.channel = enrichedChannel;
        this.matchText = str;
    }

    public static /* synthetic */ SearchResult copy$default(SearchResult searchResult, EnrichedChannel enrichedChannel, String str, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            enrichedChannel = searchResult.channel;
        }
        if ((i10 & 2) != 0) {
            str = searchResult.matchText;
        }
        return searchResult.copy(enrichedChannel, str);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final EnrichedChannel getChannel() {
        return this.channel;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getMatchText() {
        return this.matchText;
    }

    public final SearchResult copy(EnrichedChannel channel, String matchText) {
        return new SearchResult(channel, matchText);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof SearchResult)) {
            return false;
        }
        SearchResult searchResult = (SearchResult) other;
        return kotlin.jvm.internal.p.a(this.channel, searchResult.channel) && kotlin.jvm.internal.p.a(this.matchText, searchResult.matchText);
    }

    public final EnrichedChannel getChannel() {
        return this.channel;
    }

    public final String getMatchText() {
        return this.matchText;
    }

    public int hashCode() {
        int iHashCode = this.channel.hashCode() * 31;
        String str = this.matchText;
        return iHashCode + (str == null ? 0 : str.hashCode());
    }

    public String toString() {
        return "SearchResult(channel=" + this.channel + ", matchText=" + this.matchText + ")";
    }
}
