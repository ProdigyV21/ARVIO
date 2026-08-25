package com.arflix.tv.data.repository;

import com.arflix.tv.data.model.MediaType;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\bv\u0018\u00002\u00020\u0001:\u0002\u0006\u0007R\u0012\u0010\u0002\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005\u0082\u0001\u0002\b\t¨\u0006\nÀ\u0006\u0003"}, d2 = {"Lcom/arflix/tv/data/repository/ContinueWatchingUpdate;", "", "profileId", "", "getProfileId", "()Ljava/lang/String;", "Upsert", "Remove", "Lcom/arflix/tv/data/repository/ContinueWatchingUpdate$Remove;", "Lcom/arflix/tv/data/repository/ContinueWatchingUpdate$Upsert;", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public interface ContinueWatchingUpdate {

    @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0016\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\b\u0087\b\u0018\u00002\u00020\u0001B5\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\b\u0010\b\u001a\u0004\u0018\u00010\u0007\u0012\b\u0010\t\u001a\u0004\u0018\u00010\u0007¢\u0006\u0004\b\n\u0010\u000bJ\t\u0010\u0016\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\u0017\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\t\u0010\u0018\u001a\u00020\u0007HÆ\u0003J\u0010\u0010\u0019\u001a\u0004\u0018\u00010\u0007HÆ\u0003¢\u0006\u0002\u0010\u0013J\u0010\u0010\u001a\u001a\u0004\u0018\u00010\u0007HÆ\u0003¢\u0006\u0002\u0010\u0013JF\u0010\u001b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u0007HÆ\u0001¢\u0006\u0002\u0010\u001cJ\u0014\u0010\u001d\u001a\u00020\u001e2\b\u0010\u001f\u001a\u0004\u0018\u00010 HÖ\u0083\u0004J\n\u0010!\u001a\u00020\u0007HÖ\u0081\u0004J\n\u0010\"\u001a\u00020\u0003HÖ\u0081\u0004R\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0015\u0010\b\u001a\u0004\u0018\u00010\u0007¢\u0006\n\n\u0002\u0010\u0014\u001a\u0004\b\u0012\u0010\u0013R\u0015\u0010\t\u001a\u0004\u0018\u00010\u0007¢\u0006\n\n\u0002\u0010\u0014\u001a\u0004\b\u0015\u0010\u0013¨\u0006#"}, d2 = {"Lcom/arflix/tv/data/repository/ContinueWatchingUpdate$Remove;", "Lcom/arflix/tv/data/repository/ContinueWatchingUpdate;", "profileId", "", "mediaType", "Lcom/arflix/tv/data/model/MediaType;", "tmdbId", "", "season", "episode", "<init>", "(Ljava/lang/String;Lcom/arflix/tv/data/model/MediaType;ILjava/lang/Integer;Ljava/lang/Integer;)V", "getProfileId", "()Ljava/lang/String;", "getMediaType", "()Lcom/arflix/tv/data/model/MediaType;", "getTmdbId", "()I", "getSeason", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getEpisode", "component1", "component2", "component3", "component4", "component5", "copy", "(Ljava/lang/String;Lcom/arflix/tv/data/model/MediaType;ILjava/lang/Integer;Ljava/lang/Integer;)Lcom/arflix/tv/data/repository/ContinueWatchingUpdate$Remove;", "equals", "", "other", "", "hashCode", "toString", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final /* data */ class Remove implements ContinueWatchingUpdate {
        public static final int $stable = 0;
        private final Integer episode;
        private final MediaType mediaType;
        private final String profileId;
        private final Integer season;
        private final int tmdbId;

        public Remove(String str, MediaType mediaType, int i10, Integer num, Integer num2) {
            this.profileId = str;
            this.mediaType = mediaType;
            this.tmdbId = i10;
            this.season = num;
            this.episode = num2;
        }

        public static /* synthetic */ Remove copy$default(Remove remove, String str, MediaType mediaType, int i10, Integer num, Integer num2, int i11, Object obj) {
            if ((i11 & 1) != 0) {
                str = remove.profileId;
            }
            if ((i11 & 2) != 0) {
                mediaType = remove.mediaType;
            }
            if ((i11 & 4) != 0) {
                i10 = remove.tmdbId;
            }
            if ((i11 & 8) != 0) {
                num = remove.season;
            }
            if ((i11 & 16) != 0) {
                num2 = remove.episode;
            }
            Integer num3 = num2;
            int i12 = i10;
            return remove.copy(str, mediaType, i12, num, num3);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getProfileId() {
            return this.profileId;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final MediaType getMediaType() {
            return this.mediaType;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final int getTmdbId() {
            return this.tmdbId;
        }

        /* JADX INFO: renamed from: component4, reason: from getter */
        public final Integer getSeason() {
            return this.season;
        }

        /* JADX INFO: renamed from: component5, reason: from getter */
        public final Integer getEpisode() {
            return this.episode;
        }

        public final Remove copy(String profileId, MediaType mediaType, int tmdbId, Integer season, Integer episode) {
            return new Remove(profileId, mediaType, tmdbId, season, episode);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Remove)) {
                return false;
            }
            Remove remove = (Remove) other;
            return kotlin.jvm.internal.p.a(this.profileId, remove.profileId) && this.mediaType == remove.mediaType && this.tmdbId == remove.tmdbId && kotlin.jvm.internal.p.a(this.season, remove.season) && kotlin.jvm.internal.p.a(this.episode, remove.episode);
        }

        public final Integer getEpisode() {
            return this.episode;
        }

        public final MediaType getMediaType() {
            return this.mediaType;
        }

        @Override // com.arflix.tv.data.repository.ContinueWatchingUpdate
        public String getProfileId() {
            return this.profileId;
        }

        public final Integer getSeason() {
            return this.season;
        }

        public final int getTmdbId() {
            return this.tmdbId;
        }

        public int hashCode() {
            int iHashCode = this.profileId.hashCode() * 31;
            MediaType mediaType = this.mediaType;
            int iHashCode2 = (((iHashCode + (mediaType == null ? 0 : mediaType.hashCode())) * 31) + this.tmdbId) * 31;
            Integer num = this.season;
            int iHashCode3 = (iHashCode2 + (num == null ? 0 : num.hashCode())) * 31;
            Integer num2 = this.episode;
            return iHashCode3 + (num2 != null ? num2.hashCode() : 0);
        }

        public String toString() {
            return "Remove(profileId=" + this.profileId + ", mediaType=" + this.mediaType + ", tmdbId=" + this.tmdbId + ", season=" + this.season + ", episode=" + this.episode + ")";
        }
    }

    @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\t\u0010\r\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0014\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012HÖ\u0083\u0004J\n\u0010\u0013\u001a\u00020\u0014HÖ\u0081\u0004J\n\u0010\u0015\u001a\u00020\u0003HÖ\u0081\u0004R\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0016"}, d2 = {"Lcom/arflix/tv/data/repository/ContinueWatchingUpdate$Upsert;", "Lcom/arflix/tv/data/repository/ContinueWatchingUpdate;", "profileId", "", "item", "Lcom/arflix/tv/data/repository/ContinueWatchingItem;", "<init>", "(Ljava/lang/String;Lcom/arflix/tv/data/repository/ContinueWatchingItem;)V", "getProfileId", "()Ljava/lang/String;", "getItem", "()Lcom/arflix/tv/data/repository/ContinueWatchingItem;", "component1", "component2", "copy", "equals", "", "other", "", "hashCode", "", "toString", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final /* data */ class Upsert implements ContinueWatchingUpdate {
        public static final int $stable = 0;
        private final ContinueWatchingItem item;
        private final String profileId;

        public Upsert(String str, ContinueWatchingItem continueWatchingItem) {
            this.profileId = str;
            this.item = continueWatchingItem;
        }

        public static /* synthetic */ Upsert copy$default(Upsert upsert, String str, ContinueWatchingItem continueWatchingItem, int i10, Object obj) {
            if ((i10 & 1) != 0) {
                str = upsert.profileId;
            }
            if ((i10 & 2) != 0) {
                continueWatchingItem = upsert.item;
            }
            return upsert.copy(str, continueWatchingItem);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getProfileId() {
            return this.profileId;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final ContinueWatchingItem getItem() {
            return this.item;
        }

        public final Upsert copy(String profileId, ContinueWatchingItem item) {
            return new Upsert(profileId, item);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Upsert)) {
                return false;
            }
            Upsert upsert = (Upsert) other;
            return kotlin.jvm.internal.p.a(this.profileId, upsert.profileId) && kotlin.jvm.internal.p.a(this.item, upsert.item);
        }

        public final ContinueWatchingItem getItem() {
            return this.item;
        }

        @Override // com.arflix.tv.data.repository.ContinueWatchingUpdate
        public String getProfileId() {
            return this.profileId;
        }

        public int hashCode() {
            return this.item.hashCode() + (this.profileId.hashCode() * 31);
        }

        public String toString() {
            return "Upsert(profileId=" + this.profileId + ", item=" + this.item + ")";
        }
    }

    String getProfileId();
}
