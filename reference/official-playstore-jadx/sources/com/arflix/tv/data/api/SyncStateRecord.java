package com.arflix.tv.data.api;

import androidx.fragment.app.a2;
import com.google.gson.annotations.SerializedName;
import kotlin.Metadata;
import kotlin.jvm.internal.p;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b#\b\u0087\b\u0018\u00002\u00020\u0001B\u0081\u0001\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0003\u0012\b\b\u0002\u0010\t\u001a\u00020\n\u0012\b\b\u0002\u0010\u000b\u001a\u00020\n\u0012\b\b\u0002\u0010\f\u001a\u00020\r\u0012\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u0010\u0010\u0011J\t\u0010 \u001a\u00020\u0003HÆ\u0003J\u000b\u0010!\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\"\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010#\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010$\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010%\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\t\u0010&\u001a\u00020\nHÆ\u0003J\t\u0010'\u001a\u00020\nHÆ\u0003J\t\u0010(\u001a\u00020\rHÆ\u0003J\u000b\u0010)\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010*\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0085\u0001\u0010+\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\t\u001a\u00020\n2\b\b\u0002\u0010\u000b\u001a\u00020\n2\b\b\u0002\u0010\f\u001a\u00020\r2\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0014\u0010,\u001a\u00020\r2\b\u0010-\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010.\u001a\u00020\nHÖ\u0081\u0004J\n\u0010/\u001a\u00020\u0003HÖ\u0081\u0004R\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0018\u0010\u0004\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0013R\u0018\u0010\u0005\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0013R\u0018\u0010\u0006\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0013R\u0018\u0010\u0007\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0013R\u0018\u0010\b\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0013R\u0016\u0010\t\u001a\u00020\n8\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001aR\u0016\u0010\u000b\u001a\u00020\n8\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u001aR\u0016\u0010\f\u001a\u00020\r8\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001dR\u0018\u0010\u000e\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u0013R\u0018\u0010\u000f\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010\u0013¨\u00060"}, d2 = {"Lcom/arflix/tv/data/api/SyncStateRecord;", "", "userId", "", "profileId", "lastSyncAt", "lastFullSyncAt", "lastTraktActivities", "lastTraktActivitiesJson", "moviesSynced", "", "episodesSynced", "syncInProgress", "", "lastError", "updatedAt", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;IIZLjava/lang/String;Ljava/lang/String;)V", "getUserId", "()Ljava/lang/String;", "getProfileId", "getLastSyncAt", "getLastFullSyncAt", "getLastTraktActivities", "getLastTraktActivitiesJson", "getMoviesSynced", "()I", "getEpisodesSynced", "getSyncInProgress", "()Z", "getLastError", "getUpdatedAt", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "component10", "component11", "copy", "equals", "other", "hashCode", "toString", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final /* data */ class SyncStateRecord {
    public static final int $stable = 0;

    @SerializedName("episodes_synced")
    private final int episodesSynced;

    @SerializedName("last_error")
    private final String lastError;

    @SerializedName("last_full_sync_at")
    private final String lastFullSyncAt;

    @SerializedName("last_sync_at")
    private final String lastSyncAt;

    @SerializedName("last_trakt_activities")
    private final String lastTraktActivities;

    @SerializedName("last_trakt_activities_json")
    private final String lastTraktActivitiesJson;

    @SerializedName("movies_synced")
    private final int moviesSynced;

    @SerializedName("profile_id")
    private final String profileId;

    @SerializedName("sync_in_progress")
    private final boolean syncInProgress;

    @SerializedName("updated_at")
    private final String updatedAt;

    @SerializedName("user_id")
    private final String userId;

    public SyncStateRecord(String str, String str2, String str3, String str4, String str5, String str6, int i10, int i11, boolean z, String str7, String str8) {
        this.userId = str;
        this.profileId = str2;
        this.lastSyncAt = str3;
        this.lastFullSyncAt = str4;
        this.lastTraktActivities = str5;
        this.lastTraktActivitiesJson = str6;
        this.moviesSynced = i10;
        this.episodesSynced = i11;
        this.syncInProgress = z;
        this.lastError = str7;
        this.updatedAt = str8;
    }

    public static /* synthetic */ SyncStateRecord copy$default(SyncStateRecord syncStateRecord, String str, String str2, String str3, String str4, String str5, String str6, int i10, int i11, boolean z, String str7, String str8, int i12, Object obj) {
        if ((i12 & 1) != 0) {
            str = syncStateRecord.userId;
        }
        if ((i12 & 2) != 0) {
            str2 = syncStateRecord.profileId;
        }
        if ((i12 & 4) != 0) {
            str3 = syncStateRecord.lastSyncAt;
        }
        if ((i12 & 8) != 0) {
            str4 = syncStateRecord.lastFullSyncAt;
        }
        if ((i12 & 16) != 0) {
            str5 = syncStateRecord.lastTraktActivities;
        }
        if ((i12 & 32) != 0) {
            str6 = syncStateRecord.lastTraktActivitiesJson;
        }
        if ((i12 & 64) != 0) {
            i10 = syncStateRecord.moviesSynced;
        }
        if ((i12 & 128) != 0) {
            i11 = syncStateRecord.episodesSynced;
        }
        if ((i12 & 256) != 0) {
            z = syncStateRecord.syncInProgress;
        }
        if ((i12 & 512) != 0) {
            str7 = syncStateRecord.lastError;
        }
        if ((i12 & 1024) != 0) {
            str8 = syncStateRecord.updatedAt;
        }
        String str9 = str7;
        String str10 = str8;
        int i13 = i11;
        boolean z5 = z;
        String str11 = str6;
        int i14 = i10;
        String str12 = str5;
        String str13 = str3;
        return syncStateRecord.copy(str, str2, str13, str4, str12, str11, i14, i13, z5, str9, str10);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getUserId() {
        return this.userId;
    }

    /* JADX INFO: renamed from: component10, reason: from getter */
    public final String getLastError() {
        return this.lastError;
    }

    /* JADX INFO: renamed from: component11, reason: from getter */
    public final String getUpdatedAt() {
        return this.updatedAt;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getProfileId() {
        return this.profileId;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final String getLastSyncAt() {
        return this.lastSyncAt;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final String getLastFullSyncAt() {
        return this.lastFullSyncAt;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final String getLastTraktActivities() {
        return this.lastTraktActivities;
    }

    /* JADX INFO: renamed from: component6, reason: from getter */
    public final String getLastTraktActivitiesJson() {
        return this.lastTraktActivitiesJson;
    }

    /* JADX INFO: renamed from: component7, reason: from getter */
    public final int getMoviesSynced() {
        return this.moviesSynced;
    }

    /* JADX INFO: renamed from: component8, reason: from getter */
    public final int getEpisodesSynced() {
        return this.episodesSynced;
    }

    /* JADX INFO: renamed from: component9, reason: from getter */
    public final boolean getSyncInProgress() {
        return this.syncInProgress;
    }

    public final SyncStateRecord copy(String userId, String profileId, String lastSyncAt, String lastFullSyncAt, String lastTraktActivities, String lastTraktActivitiesJson, int moviesSynced, int episodesSynced, boolean syncInProgress, String lastError, String updatedAt) {
        return new SyncStateRecord(userId, profileId, lastSyncAt, lastFullSyncAt, lastTraktActivities, lastTraktActivitiesJson, moviesSynced, episodesSynced, syncInProgress, lastError, updatedAt);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof SyncStateRecord)) {
            return false;
        }
        SyncStateRecord syncStateRecord = (SyncStateRecord) other;
        return p.a(this.userId, syncStateRecord.userId) && p.a(this.profileId, syncStateRecord.profileId) && p.a(this.lastSyncAt, syncStateRecord.lastSyncAt) && p.a(this.lastFullSyncAt, syncStateRecord.lastFullSyncAt) && p.a(this.lastTraktActivities, syncStateRecord.lastTraktActivities) && p.a(this.lastTraktActivitiesJson, syncStateRecord.lastTraktActivitiesJson) && this.moviesSynced == syncStateRecord.moviesSynced && this.episodesSynced == syncStateRecord.episodesSynced && this.syncInProgress == syncStateRecord.syncInProgress && p.a(this.lastError, syncStateRecord.lastError) && p.a(this.updatedAt, syncStateRecord.updatedAt);
    }

    public final int getEpisodesSynced() {
        return this.episodesSynced;
    }

    public final String getLastError() {
        return this.lastError;
    }

    public final String getLastFullSyncAt() {
        return this.lastFullSyncAt;
    }

    public final String getLastSyncAt() {
        return this.lastSyncAt;
    }

    public final String getLastTraktActivities() {
        return this.lastTraktActivities;
    }

    public final String getLastTraktActivitiesJson() {
        return this.lastTraktActivitiesJson;
    }

    public final int getMoviesSynced() {
        return this.moviesSynced;
    }

    public final String getProfileId() {
        return this.profileId;
    }

    public final boolean getSyncInProgress() {
        return this.syncInProgress;
    }

    public final String getUpdatedAt() {
        return this.updatedAt;
    }

    public final String getUserId() {
        return this.userId;
    }

    public int hashCode() {
        int iHashCode = this.userId.hashCode() * 31;
        String str = this.profileId;
        int iHashCode2 = (iHashCode + (str == null ? 0 : str.hashCode())) * 31;
        String str2 = this.lastSyncAt;
        int iHashCode3 = (iHashCode2 + (str2 == null ? 0 : str2.hashCode())) * 31;
        String str3 = this.lastFullSyncAt;
        int iHashCode4 = (iHashCode3 + (str3 == null ? 0 : str3.hashCode())) * 31;
        String str4 = this.lastTraktActivities;
        int iHashCode5 = (iHashCode4 + (str4 == null ? 0 : str4.hashCode())) * 31;
        String str5 = this.lastTraktActivitiesJson;
        int iHashCode6 = (((((((iHashCode5 + (str5 == null ? 0 : str5.hashCode())) * 31) + this.moviesSynced) * 31) + this.episodesSynced) * 31) + (this.syncInProgress ? 1231 : 1237)) * 31;
        String str6 = this.lastError;
        int iHashCode7 = (iHashCode6 + (str6 == null ? 0 : str6.hashCode())) * 31;
        String str7 = this.updatedAt;
        return iHashCode7 + (str7 != null ? str7.hashCode() : 0);
    }

    public String toString() {
        String str = this.userId;
        String str2 = this.profileId;
        String str3 = this.lastSyncAt;
        String str4 = this.lastFullSyncAt;
        String str5 = this.lastTraktActivities;
        String str6 = this.lastTraktActivitiesJson;
        int i10 = this.moviesSynced;
        int i11 = this.episodesSynced;
        boolean z = this.syncInProgress;
        String str7 = this.lastError;
        String str8 = this.updatedAt;
        StringBuilder sbR = a2.r("SyncStateRecord(userId=", str, ", profileId=", str2, ", lastSyncAt=");
        y.a.i(sbR, str3, ", lastFullSyncAt=", str4, ", lastTraktActivities=");
        y.a.i(sbR, str5, ", lastTraktActivitiesJson=", str6, ", moviesSynced=");
        a2.y(sbR, i10, ", episodesSynced=", i11, ", syncInProgress=");
        a2.B(sbR, z, ", lastError=", str7, ", updatedAt=");
        return a0.c.p(sbR, str8, ")");
    }

    public /* synthetic */ SyncStateRecord(String str, String str2, String str3, String str4, String str5, String str6, int i10, int i11, boolean z, String str7, String str8, int i12, kotlin.jvm.internal.h hVar) {
        this(str, (i12 & 2) != 0 ? null : str2, (i12 & 4) != 0 ? null : str3, (i12 & 8) != 0 ? null : str4, (i12 & 16) != 0 ? null : str5, (i12 & 32) != 0 ? null : str6, (i12 & 64) != 0 ? 0 : i10, (i12 & 128) != 0 ? 0 : i11, (i12 & 256) != 0 ? false : z, (i12 & 512) != 0 ? null : str7, (i12 & 1024) != 0 ? null : str8);
    }
}
