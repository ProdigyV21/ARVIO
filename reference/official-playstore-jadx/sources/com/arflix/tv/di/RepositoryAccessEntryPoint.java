package com.arflix.tv.di;

import com.arflix.tv.data.api.TmdbApi;
import com.arflix.tv.data.repository.CloudSyncInvalidationBus;
import com.arflix.tv.data.repository.MediaRepository;
import com.arflix.tv.data.repository.ProfileManager;
import com.arflix.tv.data.repository.ProfileRepository;
import com.arflix.tv.data.repository.StreamRepository;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bg\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&J\b\u0010\u0004\u001a\u00020\u0005H&J\b\u0010\u0006\u001a\u00020\u0007H&J\b\u0010\b\u001a\u00020\tH&J\b\u0010\n\u001a\u00020\u000bH&J\b\u0010\f\u001a\u00020\rH&¨\u0006\u000eÀ\u0006\u0003"}, d2 = {"Lcom/arflix/tv/di/RepositoryAccessEntryPoint;", "", "streamRepository", "Lcom/arflix/tv/data/repository/StreamRepository;", "mediaRepository", "Lcom/arflix/tv/data/repository/MediaRepository;", "profileRepository", "Lcom/arflix/tv/data/repository/ProfileRepository;", "profileManager", "Lcom/arflix/tv/data/repository/ProfileManager;", "cloudSyncInvalidationBus", "Lcom/arflix/tv/data/repository/CloudSyncInvalidationBus;", "tmdbApi", "Lcom/arflix/tv/data/api/TmdbApi;", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public interface RepositoryAccessEntryPoint {
    CloudSyncInvalidationBus cloudSyncInvalidationBus();

    MediaRepository mediaRepository();

    ProfileManager profileManager();

    ProfileRepository profileRepository();

    StreamRepository streamRepository();

    TmdbApi tmdbApi();
}
