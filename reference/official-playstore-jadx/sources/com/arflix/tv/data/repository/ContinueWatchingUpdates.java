package com.arflix.tv.data.repository;

import com.arflix.tv.data.model.MediaType;
import com.arflix.tv.data.repository.ContinueWatchingUpdate;
import java.util.concurrent.atomic.AtomicLong;
import javax.inject.Inject;
import javax.inject.Singleton;
import kotlin.Metadata;
import na.r0;
import na.u0;
import na.x0;
import na.y0;

/* JADX INFO: loaded from: classes3.dex */
@Singleton
@Metadata(d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\t\n\u0002\b\u0004\b\u0007\u0018\u00002\u00020\u0001B\t\b\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u001d\u0010\t\u001a\u00020\b2\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u0006¢\u0006\u0004\b\t\u0010\nJ;\u0010\u0011\u001a\u00020\b2\u0006\u0010\u0005\u001a\u00020\u00042\b\u0010\f\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\u000e\u001a\u00020\r2\b\u0010\u000f\u001a\u0004\u0018\u00010\r2\b\u0010\u0010\u001a\u0004\u0018\u00010\r¢\u0006\u0004\b\u0011\u0010\u0012R\u0014\u0010\u0014\u001a\u00020\u00138\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0014\u0010\u0015R\u001a\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00170\u00168\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0018\u0010\u0019R\u001d\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u00170\u001a8\u0006¢\u0006\f\n\u0004\b\u001b\u0010\u001c\u001a\u0004\b\u001d\u0010\u001eR\u0011\u0010\"\u001a\u00020\u001f8F¢\u0006\u0006\u001a\u0004\b \u0010!¨\u0006#"}, d2 = {"Lcom/arflix/tv/data/repository/ContinueWatchingUpdates;", "", "<init>", "()V", "", "profileId", "Lcom/arflix/tv/data/repository/ContinueWatchingItem;", "item", "Lx6/t0;", "upsert", "(Ljava/lang/String;Lcom/arflix/tv/data/repository/ContinueWatchingItem;)V", "Lcom/arflix/tv/data/model/MediaType;", "mediaType", "", "tmdbId", "season", "episode", "remove", "(Ljava/lang/String;Lcom/arflix/tv/data/model/MediaType;ILjava/lang/Integer;Ljava/lang/Integer;)V", "Ljava/util/concurrent/atomic/AtomicLong;", "revisionCounter", "Ljava/util/concurrent/atomic/AtomicLong;", "Lna/p0;", "Lcom/arflix/tv/data/repository/ContinueWatchingUpdate;", "_updates", "Lna/p0;", "Lna/u0;", "updates", "Lna/u0;", "getUpdates", "()Lna/u0;", "", "getRevision", "()J", "revision", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class ContinueWatchingUpdates {
    public static final int $stable = 8;
    private final na.p0<ContinueWatchingUpdate> _updates;
    private final AtomicLong revisionCounter = new AtomicLong(0);
    private final u0<ContinueWatchingUpdate> updates;

    @Inject
    public ContinueWatchingUpdates() {
        x0 x0VarA = y0.a(32, 1);
        this._updates = x0VarA;
        this.updates = new r0(x0VarA);
    }

    public final long getRevision() {
        return this.revisionCounter.get();
    }

    public final u0<ContinueWatchingUpdate> getUpdates() {
        return this.updates;
    }

    public final void remove(String profileId, MediaType mediaType, int tmdbId, Integer season, Integer episode) {
        this.revisionCounter.incrementAndGet();
        this._updates.b(new ContinueWatchingUpdate.Remove(profileId, mediaType, tmdbId, season, episode));
    }

    public final void upsert(String profileId, ContinueWatchingItem item) {
        this.revisionCounter.incrementAndGet();
        this._updates.b(new ContinueWatchingUpdate.Upsert(profileId, item));
    }
}
