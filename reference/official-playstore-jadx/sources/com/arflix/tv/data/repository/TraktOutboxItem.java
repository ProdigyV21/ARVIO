package com.arflix.tv.data.repository;

import androidx.fragment.app.a2;
import androidx.media3.extractor.text.ttml.TtmlNode;
import j$.time.Instant;
import java.util.UUID;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\t\n\u0002\b$\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0087\b\u0018\u00002\u00020\u0001B\u007f\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\b\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\b\u0012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\b\u0012\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\b\u0012\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\b\u0012\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u000e\u0012\b\b\u0002\u0010\u000f\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0010\u001a\u00020\b¢\u0006\u0004\b\u0011\u0010\u0012J\t\u0010%\u001a\u00020\u0003HÆ\u0003J\t\u0010&\u001a\u00020\u0005HÆ\u0003J\t\u0010'\u001a\u00020\u0003HÆ\u0003J\u0010\u0010(\u001a\u0004\u0018\u00010\bHÆ\u0003¢\u0006\u0002\u0010\u0019J\u0010\u0010)\u001a\u0004\u0018\u00010\bHÆ\u0003¢\u0006\u0002\u0010\u0019J\u0010\u0010*\u001a\u0004\u0018\u00010\bHÆ\u0003¢\u0006\u0002\u0010\u0019J\u0010\u0010+\u001a\u0004\u0018\u00010\bHÆ\u0003¢\u0006\u0002\u0010\u0019J\u0010\u0010,\u001a\u0004\u0018\u00010\bHÆ\u0003¢\u0006\u0002\u0010\u0019J\u0010\u0010-\u001a\u0004\u0018\u00010\u000eHÆ\u0003¢\u0006\u0002\u0010 J\t\u0010.\u001a\u00020\u0003HÆ\u0003J\t\u0010/\u001a\u00020\bHÆ\u0003J\u0088\u0001\u00100\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00032\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\b2\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\b2\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\b2\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\b2\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\b2\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u000e2\b\b\u0002\u0010\u000f\u001a\u00020\u00032\b\b\u0002\u0010\u0010\u001a\u00020\bHÆ\u0001¢\u0006\u0002\u00101J\u0014\u00102\u001a\u0002032\b\u00104\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u00105\u001a\u00020\bHÖ\u0081\u0004J\n\u00106\u001a\u00020\u0003HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0014R\u0015\u0010\u0007\u001a\u0004\u0018\u00010\b¢\u0006\n\n\u0002\u0010\u001a\u001a\u0004\b\u0018\u0010\u0019R\u0015\u0010\t\u001a\u0004\u0018\u00010\b¢\u0006\n\n\u0002\u0010\u001a\u001a\u0004\b\u001b\u0010\u0019R\u0015\u0010\n\u001a\u0004\u0018\u00010\b¢\u0006\n\n\u0002\u0010\u001a\u001a\u0004\b\u001c\u0010\u0019R\u0015\u0010\u000b\u001a\u0004\u0018\u00010\b¢\u0006\n\n\u0002\u0010\u001a\u001a\u0004\b\u001d\u0010\u0019R\u0015\u0010\f\u001a\u0004\u0018\u00010\b¢\u0006\n\n\u0002\u0010\u001a\u001a\u0004\b\u001e\u0010\u0019R\u0015\u0010\r\u001a\u0004\u0018\u00010\u000e¢\u0006\n\n\u0002\u0010!\u001a\u0004\b\u001f\u0010 R\u0011\u0010\u000f\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010\u0014R\u0011\u0010\u0010\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b#\u0010$¨\u00067"}, d2 = {"Lcom/arflix/tv/data/repository/TraktOutboxItem;", "", TtmlNode.ATTR_ID, "", "action", "Lcom/arflix/tv/data/repository/TraktOutboxAction;", "provider", "tmdbId", "", "showTraktId", "traktEpisodeId", "season", "episode", "playbackId", "", "createdAt", "attempts", "<init>", "(Ljava/lang/String;Lcom/arflix/tv/data/repository/TraktOutboxAction;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Long;Ljava/lang/String;I)V", "getId", "()Ljava/lang/String;", "getAction", "()Lcom/arflix/tv/data/repository/TraktOutboxAction;", "getProvider", "getTmdbId", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getShowTraktId", "getTraktEpisodeId", "getSeason", "getEpisode", "getPlaybackId", "()Ljava/lang/Long;", "Ljava/lang/Long;", "getCreatedAt", "getAttempts", "()I", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "component10", "component11", "copy", "(Ljava/lang/String;Lcom/arflix/tv/data/repository/TraktOutboxAction;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Long;Ljava/lang/String;I)Lcom/arflix/tv/data/repository/TraktOutboxItem;", "equals", "", "other", "hashCode", "toString", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final /* data */ class TraktOutboxItem {
    public static final int $stable = 0;
    private final TraktOutboxAction action;
    private final int attempts;
    private final String createdAt;
    private final Integer episode;
    private final String id;
    private final Long playbackId;
    private final String provider;
    private final Integer season;
    private final Integer showTraktId;
    private final Integer tmdbId;
    private final Integer traktEpisodeId;

    public TraktOutboxItem(String str, TraktOutboxAction traktOutboxAction, String str2, Integer num, Integer num2, Integer num3, Integer num4, Integer num5, Long l10, String str3, int i10) {
        this.id = str;
        this.action = traktOutboxAction;
        this.provider = str2;
        this.tmdbId = num;
        this.showTraktId = num2;
        this.traktEpisodeId = num3;
        this.season = num4;
        this.episode = num5;
        this.playbackId = l10;
        this.createdAt = str3;
        this.attempts = i10;
    }

    public static /* synthetic */ TraktOutboxItem copy$default(TraktOutboxItem traktOutboxItem, String str, TraktOutboxAction traktOutboxAction, String str2, Integer num, Integer num2, Integer num3, Integer num4, Integer num5, Long l10, String str3, int i10, int i11, Object obj) {
        if ((i11 & 1) != 0) {
            str = traktOutboxItem.id;
        }
        if ((i11 & 2) != 0) {
            traktOutboxAction = traktOutboxItem.action;
        }
        if ((i11 & 4) != 0) {
            str2 = traktOutboxItem.provider;
        }
        if ((i11 & 8) != 0) {
            num = traktOutboxItem.tmdbId;
        }
        if ((i11 & 16) != 0) {
            num2 = traktOutboxItem.showTraktId;
        }
        if ((i11 & 32) != 0) {
            num3 = traktOutboxItem.traktEpisodeId;
        }
        if ((i11 & 64) != 0) {
            num4 = traktOutboxItem.season;
        }
        if ((i11 & 128) != 0) {
            num5 = traktOutboxItem.episode;
        }
        if ((i11 & 256) != 0) {
            l10 = traktOutboxItem.playbackId;
        }
        if ((i11 & 512) != 0) {
            str3 = traktOutboxItem.createdAt;
        }
        if ((i11 & 1024) != 0) {
            i10 = traktOutboxItem.attempts;
        }
        String str4 = str3;
        int i12 = i10;
        Integer num6 = num5;
        Long l11 = l10;
        Integer num7 = num3;
        Integer num8 = num4;
        Integer num9 = num2;
        String str5 = str2;
        return traktOutboxItem.copy(str, traktOutboxAction, str5, num, num9, num7, num8, num6, l11, str4, i12);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getId() {
        return this.id;
    }

    /* JADX INFO: renamed from: component10, reason: from getter */
    public final String getCreatedAt() {
        return this.createdAt;
    }

    /* JADX INFO: renamed from: component11, reason: from getter */
    public final int getAttempts() {
        return this.attempts;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final TraktOutboxAction getAction() {
        return this.action;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final String getProvider() {
        return this.provider;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final Integer getTmdbId() {
        return this.tmdbId;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final Integer getShowTraktId() {
        return this.showTraktId;
    }

    /* JADX INFO: renamed from: component6, reason: from getter */
    public final Integer getTraktEpisodeId() {
        return this.traktEpisodeId;
    }

    /* JADX INFO: renamed from: component7, reason: from getter */
    public final Integer getSeason() {
        return this.season;
    }

    /* JADX INFO: renamed from: component8, reason: from getter */
    public final Integer getEpisode() {
        return this.episode;
    }

    /* JADX INFO: renamed from: component9, reason: from getter */
    public final Long getPlaybackId() {
        return this.playbackId;
    }

    public final TraktOutboxItem copy(String id, TraktOutboxAction action, String provider, Integer tmdbId, Integer showTraktId, Integer traktEpisodeId, Integer season, Integer episode, Long playbackId, String createdAt, int attempts) {
        return new TraktOutboxItem(id, action, provider, tmdbId, showTraktId, traktEpisodeId, season, episode, playbackId, createdAt, attempts);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof TraktOutboxItem)) {
            return false;
        }
        TraktOutboxItem traktOutboxItem = (TraktOutboxItem) other;
        return kotlin.jvm.internal.p.a(this.id, traktOutboxItem.id) && this.action == traktOutboxItem.action && kotlin.jvm.internal.p.a(this.provider, traktOutboxItem.provider) && kotlin.jvm.internal.p.a(this.tmdbId, traktOutboxItem.tmdbId) && kotlin.jvm.internal.p.a(this.showTraktId, traktOutboxItem.showTraktId) && kotlin.jvm.internal.p.a(this.traktEpisodeId, traktOutboxItem.traktEpisodeId) && kotlin.jvm.internal.p.a(this.season, traktOutboxItem.season) && kotlin.jvm.internal.p.a(this.episode, traktOutboxItem.episode) && kotlin.jvm.internal.p.a(this.playbackId, traktOutboxItem.playbackId) && kotlin.jvm.internal.p.a(this.createdAt, traktOutboxItem.createdAt) && this.attempts == traktOutboxItem.attempts;
    }

    public final TraktOutboxAction getAction() {
        return this.action;
    }

    public final int getAttempts() {
        return this.attempts;
    }

    public final String getCreatedAt() {
        return this.createdAt;
    }

    public final Integer getEpisode() {
        return this.episode;
    }

    public final String getId() {
        return this.id;
    }

    public final Long getPlaybackId() {
        return this.playbackId;
    }

    public final String getProvider() {
        return this.provider;
    }

    public final Integer getSeason() {
        return this.season;
    }

    public final Integer getShowTraktId() {
        return this.showTraktId;
    }

    public final Integer getTmdbId() {
        return this.tmdbId;
    }

    public final Integer getTraktEpisodeId() {
        return this.traktEpisodeId;
    }

    public int hashCode() {
        int iC = androidx.compose.foundation.c.c((this.action.hashCode() + (this.id.hashCode() * 31)) * 31, 31, this.provider);
        Integer num = this.tmdbId;
        int iHashCode = (iC + (num == null ? 0 : num.hashCode())) * 31;
        Integer num2 = this.showTraktId;
        int iHashCode2 = (iHashCode + (num2 == null ? 0 : num2.hashCode())) * 31;
        Integer num3 = this.traktEpisodeId;
        int iHashCode3 = (iHashCode2 + (num3 == null ? 0 : num3.hashCode())) * 31;
        Integer num4 = this.season;
        int iHashCode4 = (iHashCode3 + (num4 == null ? 0 : num4.hashCode())) * 31;
        Integer num5 = this.episode;
        int iHashCode5 = (iHashCode4 + (num5 == null ? 0 : num5.hashCode())) * 31;
        Long l10 = this.playbackId;
        return androidx.compose.foundation.c.c((iHashCode5 + (l10 != null ? l10.hashCode() : 0)) * 31, 31, this.createdAt) + this.attempts;
    }

    public String toString() {
        String str = this.id;
        TraktOutboxAction traktOutboxAction = this.action;
        String str2 = this.provider;
        Integer num = this.tmdbId;
        Integer num2 = this.showTraktId;
        Integer num3 = this.traktEpisodeId;
        Integer num4 = this.season;
        Integer num5 = this.episode;
        Long l10 = this.playbackId;
        String str3 = this.createdAt;
        int i10 = this.attempts;
        StringBuilder sb2 = new StringBuilder("TraktOutboxItem(id=");
        sb2.append(str);
        sb2.append(", action=");
        sb2.append(traktOutboxAction);
        sb2.append(", provider=");
        sb2.append(str2);
        sb2.append(", tmdbId=");
        sb2.append(num);
        sb2.append(", showTraktId=");
        a2.z(sb2, num2, ", traktEpisodeId=", num3, ", season=");
        a2.z(sb2, num4, ", episode=", num5, ", playbackId=");
        sb2.append(l10);
        sb2.append(", createdAt=");
        sb2.append(str3);
        sb2.append(", attempts=");
        return androidx.compose.material3.d.j(i10, ")", sb2);
    }

    public /* synthetic */ TraktOutboxItem(String str, TraktOutboxAction traktOutboxAction, String str2, Integer num, Integer num2, Integer num3, Integer num4, Integer num5, Long l10, String str3, int i10, int i11, kotlin.jvm.internal.h hVar) {
        this((i11 & 1) != 0 ? UUID.randomUUID().toString() : str, traktOutboxAction, (i11 & 4) != 0 ? "trakt" : str2, (i11 & 8) != 0 ? null : num, (i11 & 16) != 0 ? null : num2, (i11 & 32) != 0 ? null : num3, (i11 & 64) != 0 ? null : num4, (i11 & 128) != 0 ? null : num5, (i11 & 256) != 0 ? null : l10, (i11 & 512) != 0 ? Instant.now().toString() : str3, (i11 & 1024) != 0 ? 0 : i10);
    }
}
