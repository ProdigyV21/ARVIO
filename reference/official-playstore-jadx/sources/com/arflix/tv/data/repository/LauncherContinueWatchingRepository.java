package com.arflix.tv.data.repository;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteException;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.tv.TvContract;
import android.net.Uri;
import android.os.Build;
import android.util.Log;
import androidx.fragment.app.a2;
import androidx.media3.container.MdtaMetadataEntry;
import androidx.media3.extractor.metadata.dvbsi.AppInfoTableDecoder;
import com.arflix.tv.MainActivity;
import com.arflix.tv.R;
import com.arflix.tv.data.model.MediaType;
import com.arflix.tv.data.repository.sync.RemoteSyncManager;
import com.arflix.tv.util.AppLogger;
import com.google.android.gms.cast.MediaError;
import com.google.android.gms.cast.MediaTrack;
import dagger.hilt.android.qualifiers.ApplicationContext;
import io.ktor.http.LinkHeader;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Singleton;
import ka.x0;
import kotlin.Metadata;
import x6.t0;

/* JADX INFO: loaded from: classes3.dex */
@Singleton
@Metadata(d1 = {"\u0000p\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\n\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0015\b\u0007\u0018\u0000 H2\u00020\u0001:\u0001HB;\b\u0007\u0012\b\b\u0001\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u0012\u0006\u0010\t\u001a\u00020\b\u0012\u0006\u0010\u000b\u001a\u00020\n\u0012\u0006\u0010\r\u001a\u00020\f¢\u0006\u0004\b\u000e\u0010\u000fJ\u000f\u0010\u0011\u001a\u00020\u0010H\u0002¢\u0006\u0004\b\u0011\u0010\u0012J\u0016\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00140\u0013H\u0082@¢\u0006\u0004\b\u0015\u0010\u0016J\u001d\u0010\u0019\u001a\u00020\u00182\f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00140\u0013H\u0003¢\u0006\u0004\b\u0019\u0010\u001aJ\u0011\u0010\u001c\u001a\u0004\u0018\u00010\u001bH\u0003¢\u0006\u0004\b\u001c\u0010\u001dJ'\u0010\"\u001a\u00020\u00182\u0006\u0010\u001e\u001a\u00020\u001b2\u0006\u0010\u001f\u001a\u00020\u00142\u0006\u0010!\u001a\u00020 H\u0003¢\u0006\u0004\b\"\u0010#J\u001f\u0010$\u001a\u00020\u00182\u0006\u0010\u001f\u001a\u00020\u00142\u0006\u0010!\u001a\u00020 H\u0003¢\u0006\u0004\b$\u0010%J\u0017\u0010&\u001a\u00020\u00182\u0006\u0010\u001e\u001a\u00020\u001bH\u0003¢\u0006\u0004\b&\u0010'J\u000f\u0010(\u001a\u00020\u0018H\u0003¢\u0006\u0004\b(\u0010)J\u0011\u0010*\u001a\u0004\u0018\u00010\u001bH\u0003¢\u0006\u0004\b*\u0010\u001dJ%\u0010/\u001a\u00020\u00182\u0006\u0010,\u001a\u00020+2\f\u0010.\u001a\b\u0012\u0004\u0012\u00020\u00180-H\u0002¢\u0006\u0004\b/\u00100J3\u0010/\u001a\u00028\u0000\"\u0004\b\u0000\u001012\u0006\u0010,\u001a\u00020+2\u0006\u00102\u001a\u00028\u00002\f\u0010.\u001a\b\u0012\u0004\u0012\u00028\u00000-H\u0002¢\u0006\u0004\b/\u00103J\u0017\u00105\u001a\u0002042\u0006\u0010\u001f\u001a\u00020\u0014H\u0002¢\u0006\u0004\b5\u00106J\u0013\u00107\u001a\u00020 *\u00020\u0014H\u0002¢\u0006\u0004\b7\u00108J\u0013\u00109\u001a\u00020+*\u00020\u0014H\u0002¢\u0006\u0004\b9\u0010:J\u0013\u0010;\u001a\u00020+*\u00020\u0014H\u0002¢\u0006\u0004\b;\u0010:J\u0013\u0010<\u001a\u00020+*\u00020\u0014H\u0002¢\u0006\u0004\b<\u0010:J\u0017\u0010>\u001a\u00020+2\u0006\u0010=\u001a\u00020\u001bH\u0002¢\u0006\u0004\b>\u0010?J\u0010\u0010@\u001a\u00020\u0018H\u0086@¢\u0006\u0004\b@\u0010\u0016J\u0010\u0010A\u001a\u00020\u0018H\u0086@¢\u0006\u0004\bA\u0010\u0016R\u0014\u0010\u0003\u001a\u00020\u00028\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0003\u0010BR\u0014\u0010\u0005\u001a\u00020\u00048\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0005\u0010CR\u0014\u0010\u0007\u001a\u00020\u00068\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0007\u0010DR\u0014\u0010\t\u001a\u00020\b8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\t\u0010ER\u0014\u0010\u000b\u001a\u00020\n8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u000b\u0010FR\u0014\u0010\r\u001a\u00020\f8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\r\u0010G¨\u0006I"}, d2 = {"Lcom/arflix/tv/data/repository/LauncherContinueWatchingRepository;", "", "Landroid/content/Context;", "context", "Lcom/arflix/tv/data/repository/ProfileManager;", "profileManager", "Lcom/arflix/tv/data/repository/TraktRepository;", "traktRepository", "Lcom/arflix/tv/data/repository/sync/RemoteSyncManager;", "remoteSyncManager", "Lcom/arflix/tv/data/repository/WatchHistoryRepository;", "watchHistoryRepository", "Lcom/arflix/tv/data/repository/StreamRepository;", "streamRepository", "<init>", "(Landroid/content/Context;Lcom/arflix/tv/data/repository/ProfileManager;Lcom/arflix/tv/data/repository/TraktRepository;Lcom/arflix/tv/data/repository/sync/RemoteSyncManager;Lcom/arflix/tv/data/repository/WatchHistoryRepository;Lcom/arflix/tv/data/repository/StreamRepository;)V", "", "supportsLauncherPublishing", "()Z", "", "Lcom/arflix/tv/data/repository/ContinueWatchingItem;", "loadPublisherItems", "(Ld7/d;)Ljava/lang/Object;", "items", "Lx6/t0;", "syncPublishedRows", "(Ljava/util/List;)V", "", "ensurePreviewChannel", "()Ljava/lang/Long;", "channelId", "item", "", "index", "insertPreviewProgram", "(JLcom/arflix/tv/data/repository/ContinueWatchingItem;I)V", "insertWatchNextProgram", "(Lcom/arflix/tv/data/repository/ContinueWatchingItem;I)V", "deletePreviewPrograms", "(J)V", "deleteWatchNextPrograms", "()V", "findExistingChannelId", "", "action", "Lkotlin/Function0;", "block", "runTvProviderCall", "(Ljava/lang/String;Lr7/a;)V", "T", "fallback", "(Ljava/lang/String;Ljava/lang/Object;Lr7/a;)Ljava/lang/Object;", "Landroid/content/Intent;", "buildLaunchIntent", "(Lcom/arflix/tv/data/repository/ContinueWatchingItem;)Landroid/content/Intent;", "toPreviewType", "(Lcom/arflix/tv/data/repository/ContinueWatchingItem;)I", "previewProgramId", "(Lcom/arflix/tv/data/repository/ContinueWatchingItem;)Ljava/lang/String;", "watchNextProgramId", "buildSubtitle", "totalSeconds", "formatResumeClock", "(J)Ljava/lang/String;", "refreshForCurrentProfile", "clearPublishedPrograms", "Landroid/content/Context;", "Lcom/arflix/tv/data/repository/ProfileManager;", "Lcom/arflix/tv/data/repository/TraktRepository;", "Lcom/arflix/tv/data/repository/sync/RemoteSyncManager;", "Lcom/arflix/tv/data/repository/WatchHistoryRepository;", "Lcom/arflix/tv/data/repository/StreamRepository;", "Companion", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class LauncherContinueWatchingRepository {
    private static final String CHANNEL_INTERNAL_ID = "arvio_continue_watching_channel";
    private static final String PREVIEW_PROGRAM_PREFIX = "arvio_continue_preview";
    private static final String TAG = "LauncherCW";
    private static final String URI_HOST = "continue";
    private static final String URI_SCHEME = "arvio";
    private static final String WATCH_NEXT_PROGRAM_PREFIX = "arvio_continue_watchnext";
    private final Context context;
    private final ProfileManager profileManager;
    private final RemoteSyncManager remoteSyncManager;
    private final StreamRepository streamRepository;
    private final TraktRepository traktRepository;
    private final WatchHistoryRepository watchHistoryRepository;
    public static final int $stable = 8;

    /* JADX INFO: renamed from: com.arflix.tv.data.repository.LauncherContinueWatchingRepository$clearPublishedPrograms$2, reason: invalid class name */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
    @f7.e(c = "com.arflix.tv.data.repository.LauncherContinueWatchingRepository$clearPublishedPrograms$2", f = "LauncherContinueWatchingRepository.kt", l = {}, m = "invokeSuspend", v = 2)
    public static final class AnonymousClass2 extends f7.j implements r7.p<ka.k0, d7.d<? super t0>, Object> {
        int label;

        public AnonymousClass2(d7.d<? super AnonymousClass2> dVar) {
            super(2, dVar);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final t0 invokeSuspend$lambda$0(LauncherContinueWatchingRepository launcherContinueWatchingRepository) {
            Long lFindExistingChannelId = launcherContinueWatchingRepository.findExistingChannelId();
            if (lFindExistingChannelId != null) {
                launcherContinueWatchingRepository.deletePreviewPrograms(lFindExistingChannelId.longValue());
            }
            launcherContinueWatchingRepository.deleteWatchNextPrograms();
            return t0.f22605a;
        }

        @Override // f7.a
        public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
            return LauncherContinueWatchingRepository.this.new AnonymousClass2(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            k2.c.G(obj);
            LauncherContinueWatchingRepository launcherContinueWatchingRepository = LauncherContinueWatchingRepository.this;
            launcherContinueWatchingRepository.runTvProviderCall("clear launcher continue watching", new a0(launcherContinueWatchingRepository, 0));
            return t0.f22605a;
        }

        @Override // r7.p
        public final Object invoke(ka.k0 k0Var, d7.d<? super t0> dVar) {
            return ((AnonymousClass2) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.data.repository.LauncherContinueWatchingRepository$loadPublisherItems$1, reason: invalid class name */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @f7.e(c = "com.arflix.tv.data.repository.LauncherContinueWatchingRepository", f = "LauncherContinueWatchingRepository.kt", l = {101, MediaError.DetailedErrorCode.MEDIA_DECODE, AppInfoTableDecoder.APPLICATION_INFORMATION_TABLE_ID}, m = "loadPublisherItems", v = 2)
    public static final class AnonymousClass1 extends f7.c {
        int I$0;
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        int label;
        /* synthetic */ Object result;

        public AnonymousClass1(d7.d<? super AnonymousClass1> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return LauncherContinueWatchingRepository.this.loadPublisherItems(this);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.data.repository.LauncherContinueWatchingRepository$refreshForCurrentProfile$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @f7.e(c = "com.arflix.tv.data.repository.LauncherContinueWatchingRepository", f = "LauncherContinueWatchingRepository.kt", l = {62, 66, MdtaMetadataEntry.TYPE_INDICATOR_INT32}, m = "refreshForCurrentProfile", v = 2)
    public static final class C08661 extends f7.c {
        Object L$0;
        Object L$1;
        int label;
        /* synthetic */ Object result;

        public C08661(d7.d<? super C08661> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return LauncherContinueWatchingRepository.this.refreshForCurrentProfile(this);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.data.repository.LauncherContinueWatchingRepository$refreshForCurrentProfile$2, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
    @f7.e(c = "com.arflix.tv.data.repository.LauncherContinueWatchingRepository$refreshForCurrentProfile$2", f = "LauncherContinueWatchingRepository.kt", l = {}, m = "invokeSuspend", v = 2)
    public static final class C08672 extends f7.j implements r7.p<ka.k0, d7.d<? super t0>, Object> {
        final /* synthetic */ List<ContinueWatchingItem> $items;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C08672(List<ContinueWatchingItem> list, d7.d<? super C08672> dVar) {
            super(2, dVar);
            this.$items = list;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final t0 invokeSuspend$lambda$0(LauncherContinueWatchingRepository launcherContinueWatchingRepository, List list) {
            launcherContinueWatchingRepository.syncPublishedRows(list);
            return t0.f22605a;
        }

        @Override // f7.a
        public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
            return LauncherContinueWatchingRepository.this.new C08672(this.$items, dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            k2.c.G(obj);
            LauncherContinueWatchingRepository launcherContinueWatchingRepository = LauncherContinueWatchingRepository.this;
            launcherContinueWatchingRepository.runTvProviderCall("refresh launcher continue watching", new y(launcherContinueWatchingRepository, this.$items, 2));
            return t0.f22605a;
        }

        @Override // r7.p
        public final Object invoke(ka.k0 k0Var, d7.d<? super t0> dVar) {
            return ((C08672) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
        }
    }

    @Inject
    public LauncherContinueWatchingRepository(@ApplicationContext Context context, ProfileManager profileManager, TraktRepository traktRepository, RemoteSyncManager remoteSyncManager, WatchHistoryRepository watchHistoryRepository, StreamRepository streamRepository) {
        this.context = context;
        this.profileManager = profileManager;
        this.traktRepository = traktRepository;
        this.remoteSyncManager = remoteSyncManager;
        this.watchHistoryRepository = watchHistoryRepository;
        this.streamRepository = streamRepository;
    }

    private final Intent buildLaunchIntent(ContinueWatchingItem item) {
        MediaType mediaType = item.getMediaType();
        int id = item.getId();
        Integer season = item.getSeason();
        Integer episode = item.getEpisode();
        Long lValueOf = Long.valueOf(item.getResumePositionSeconds());
        if (lValueOf.longValue() <= 0) {
            lValueOf = null;
        }
        LauncherContinueWatchingRequest launcherContinueWatchingRequest = new LauncherContinueWatchingRequest(mediaType, id, season, episode, lValueOf != null ? Long.valueOf(lValueOf.longValue() * 1000) : null);
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.setClass(this.context, MainActivity.class);
        intent.setData(LauncherContinueWatchingRepositoryKt.toUri(launcherContinueWatchingRequest));
        intent.addFlags(335544320);
        return intent;
    }

    private final String buildSubtitle(ContinueWatchingItem continueWatchingItem) {
        String string = (continueWatchingItem.getMediaType() != MediaType.TV || continueWatchingItem.getSeason() == null || continueWatchingItem.getEpisode() == null) ? this.context.getString(R.string.continue_label) : this.context.getString(R.string.continue_season_episode, continueWatchingItem.getSeason(), continueWatchingItem.getEpisode());
        Long lValueOf = Long.valueOf(continueWatchingItem.getResumePositionSeconds());
        if (lValueOf.longValue() <= 0) {
            lValueOf = null;
        }
        String resumeClock = lValueOf != null ? formatResumeClock(lValueOf.longValue()) : null;
        if (resumeClock != null && !kotlin.text.o.h0(resumeClock)) {
            return this.context.getString(R.string.launcher_continue_from, string, resumeClock);
        }
        String episodeTitle = continueWatchingItem.getEpisodeTitle();
        return (episodeTitle == null || kotlin.text.o.h0(episodeTitle)) ? string : this.context.getString(R.string.launcher_continue_dash, string, continueWatchingItem.getEpisodeTitle());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void deletePreviewPrograms(final long channelId) {
        final String[] strArr = {"_id", "channel_id"};
        runTvProviderCall("delete preview rows", new r7.a() { // from class: com.arflix.tv.data.repository.z
            @Override // r7.a
            public final Object invoke() {
                return LauncherContinueWatchingRepository.deletePreviewPrograms$lambda$0(this.f7719i, strArr, channelId);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 deletePreviewPrograms$lambda$0(LauncherContinueWatchingRepository launcherContinueWatchingRepository, String[] strArr, long j10) throws IOException {
        Cursor cursorQuery = launcherContinueWatchingRepository.context.getContentResolver().query(androidx.tvprovider.media.tv.t.f6611a, strArr, null, null, null);
        if (cursorQuery != null) {
            try {
                int columnIndexOrThrow = cursorQuery.getColumnIndexOrThrow("_id");
                int columnIndexOrThrow2 = cursorQuery.getColumnIndexOrThrow("channel_id");
                while (cursorQuery.moveToNext()) {
                    if (cursorQuery.getLong(columnIndexOrThrow2) == j10) {
                        launcherContinueWatchingRepository.context.getContentResolver().delete(ContentUris.withAppendedId(androidx.tvprovider.media.tv.t.f6611a, cursorQuery.getLong(columnIndexOrThrow)), null, null);
                    }
                }
                cursorQuery.close();
            } finally {
            }
        }
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void deleteWatchNextPrograms() {
        runTvProviderCall("delete watch-next rows", new y(this, new String[]{"_id", "internal_provider_id"}, 0));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 deleteWatchNextPrograms$lambda$0(LauncherContinueWatchingRepository launcherContinueWatchingRepository, String[] strArr) throws IOException {
        Cursor cursorQuery = launcherContinueWatchingRepository.context.getContentResolver().query(androidx.tvprovider.media.tv.w.f6612a, strArr, null, null, null);
        if (cursorQuery != null) {
            try {
                int columnIndexOrThrow = cursorQuery.getColumnIndexOrThrow("_id");
                int columnIndexOrThrow2 = cursorQuery.getColumnIndexOrThrow("internal_provider_id");
                while (cursorQuery.moveToNext()) {
                    String string = cursorQuery.getString(columnIndexOrThrow2);
                    if (string == null) {
                        string = "";
                    }
                    if (kotlin.text.u.P(string, WATCH_NEXT_PROGRAM_PREFIX, false)) {
                        launcherContinueWatchingRepository.context.getContentResolver().delete(ContentUris.withAppendedId(androidx.tvprovider.media.tv.w.f6612a, cursorQuery.getLong(columnIndexOrThrow)), null, null);
                    }
                }
                cursorQuery.close();
            } finally {
            }
        }
        return t0.f22605a;
    }

    private final Long ensurePreviewChannel() {
        Long lFindExistingChannelId = findExistingChannelId();
        if (lFindExistingChannelId != null) {
            return Long.valueOf(lFindExistingChannelId.longValue());
        }
        ContentValues contentValues = new ContentValues();
        contentValues.put(LinkHeader.Parameters.Type, "TYPE_PREVIEW");
        contentValues.put("display_name", this.context.getString(R.string.continue_watching));
        contentValues.put(MediaTrack.ROLE_DESCRIPTION, "Resume watching in Arvio");
        contentValues.put("internal_provider_id", CHANNEL_INTERNAL_ID);
        ContentResolver contentResolver = this.context.getContentResolver();
        Uri uri = androidx.tvprovider.media.tv.r.f6610a;
        ContentValues contentValues2 = new ContentValues(contentValues);
        int i10 = Build.VERSION.SDK_INT;
        if (i10 < 26) {
            contentValues2.remove("internal_provider_id");
            contentValues2.remove("transient");
            contentValues2.remove("configuration_display_order");
            contentValues2.remove("system_channel_key");
        }
        if (i10 < 30) {
            contentValues2.remove("global_content_id");
        }
        contentValues2.remove("browsable");
        contentValues2.remove("locked");
        contentValues2.remove("system_approved");
        Uri uriInsert = contentResolver.insert(uri, contentValues2);
        if (uriInsert == null) {
            return null;
        }
        long id = ContentUris.parseId(uriInsert);
        Bitmap bitmapDecodeResource = BitmapFactory.decodeResource(this.context.getResources(), R.mipmap.ic_launcher);
        if (bitmapDecodeResource != null) {
            try {
                Context context = this.context;
                try {
                    OutputStream outputStreamOpenOutputStream = context.getContentResolver().openOutputStream(TvContract.buildChannelLogoUri(id));
                    try {
                        bitmapDecodeResource.compress(Bitmap.CompressFormat.PNG, 100, outputStreamOpenOutputStream);
                        outputStreamOpenOutputStream.flush();
                        outputStreamOpenOutputStream.close();
                    } catch (Throwable th) {
                        if (outputStreamOpenOutputStream != null) {
                            try {
                                outputStreamOpenOutputStream.close();
                            } catch (Throwable th2) {
                                th.addSuppressed(th2);
                            }
                        }
                        throw th;
                    }
                } catch (SQLiteException | IOException e5) {
                    Log.i("ChannelLogoUtils", "Failed to store the logo to the system content provider.\n", e5);
                }
            } catch (Throwable unused) {
            }
        }
        try {
            e4.b.d(this.context, id);
        } catch (Throwable unused2) {
        }
        return Long.valueOf(id);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Long findExistingChannelId() {
        return (Long) runTvProviderCall("find existing preview channel", null, new y(this, new String[]{"_id", "internal_provider_id"}, 1));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Long findExistingChannelId$lambda$0(LauncherContinueWatchingRepository launcherContinueWatchingRepository, String[] strArr) throws IOException {
        Cursor cursorQuery = launcherContinueWatchingRepository.context.getContentResolver().query(androidx.tvprovider.media.tv.r.f6610a, strArr, null, null, null);
        if (cursorQuery == null) {
            return null;
        }
        try {
            int columnIndexOrThrow = cursorQuery.getColumnIndexOrThrow("_id");
            int columnIndexOrThrow2 = cursorQuery.getColumnIndexOrThrow("internal_provider_id");
            while (cursorQuery.moveToNext()) {
                if (kotlin.jvm.internal.p.a(cursorQuery.getString(columnIndexOrThrow2), CHANNEL_INTERNAL_ID)) {
                    Long lValueOf = Long.valueOf(cursorQuery.getLong(columnIndexOrThrow));
                    cursorQuery.close();
                    return lValueOf;
                }
            }
            cursorQuery.close();
            return null;
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                xc.d.L(cursorQuery, th);
                throw th2;
            }
        }
    }

    private final String formatResumeClock(long totalSeconds) {
        if (totalSeconds < 0) {
            totalSeconds = 0;
        }
        long j10 = totalSeconds / 3600;
        long j11 = (totalSeconds % 3600) / 60;
        long j12 = totalSeconds % 60;
        return j10 > 0 ? String.format("%d:%02d:%02d", Arrays.copyOf(new Object[]{Long.valueOf(j10), Long.valueOf(j11), Long.valueOf(j12)}, 3)) : String.format("%02d:%02d", Arrays.copyOf(new Object[]{Long.valueOf(j11), Long.valueOf(j12)}, 2));
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x006f  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x0096  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private final void insertPreviewProgram(long r4, com.arflix.tv.data.repository.ContinueWatchingItem r6, int r7) {
        /*
            Method dump skipped, instruction units count: 244
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.repository.LauncherContinueWatchingRepository.insertPreviewProgram(long, com.arflix.tv.data.repository.ContinueWatchingItem, int):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x0080  */
    /* JADX WARN: Removed duplicated region for block: B:30:0x00a7  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private final void insertWatchNextProgram(com.arflix.tv.data.repository.ContinueWatchingItem r9, int r10) {
        /*
            Method dump skipped, instruction units count: 305
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.repository.LauncherContinueWatchingRepository.insertWatchNextProgram(com.arflix.tv.data.repository.ContinueWatchingItem, int):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:38:0x009d  */
    /* JADX WARN: Removed duplicated region for block: B:42:0x00af  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x00eb  */
    /* JADX WARN: Removed duplicated region for block: B:62:0x0119  */
    /* JADX WARN: Removed duplicated region for block: B:66:0x013a  */
    /* JADX WARN: Removed duplicated region for block: B:78:0x01cf  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0017  */
    /* JADX WARN: Removed duplicated region for block: B:84:0x0212  */
    /* JADX WARN: Removed duplicated region for block: B:98:0x00f0 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Type inference failed for: r12v0, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r12v2 */
    /* JADX WARN: Type inference failed for: r17v1, types: [java.util.List] */
    /* JADX WARN: Type inference failed for: r19v0 */
    /* JADX WARN: Type inference failed for: r19v1, types: [java.util.List] */
    /* JADX WARN: Type inference failed for: r19v2 */
    /* JADX WARN: Type inference failed for: r2v12 */
    /* JADX WARN: Type inference failed for: r2v15, types: [java.util.List] */
    /* JADX WARN: Type inference failed for: r2v18 */
    /* JADX WARN: Type inference failed for: r2v19 */
    /* JADX WARN: Type inference failed for: r2v20 */
    /* JADX WARN: Type inference failed for: r2v3 */
    /* JADX WARN: Type inference failed for: r2v4 */
    /* JADX WARN: Type inference failed for: r2v5 */
    /* JADX WARN: Type inference failed for: r2v6 */
    /* JADX WARN: Type inference failed for: r2v7 */
    /* JADX WARN: Type inference failed for: r3v0, types: [int] */
    /* JADX WARN: Type inference failed for: r3v1 */
    /* JADX WARN: Type inference failed for: r3v17 */
    /* JADX WARN: Type inference failed for: r3v2 */
    /* JADX WARN: Type inference failed for: r3v30 */
    /* JADX WARN: Type inference failed for: r3v31 */
    /* JADX WARN: Type inference failed for: r3v32 */
    /* JADX WARN: Type inference failed for: r3v33 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object loadPublisherItems(d7.d<? super java.util.List<com.arflix.tv.data.repository.ContinueWatchingItem>> r52) {
        /*
            Method dump skipped, instruction units count: 615
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.repository.LauncherContinueWatchingRepository.loadPublisherItems(d7.d):java.lang.Object");
    }

    private final String previewProgramId(ContinueWatchingItem continueWatchingItem) {
        String strName = continueWatchingItem.getMediaType().name();
        int id = continueWatchingItem.getId();
        Integer season = continueWatchingItem.getSeason();
        int iIntValue = season != null ? season.intValue() : -1;
        Integer episode = continueWatchingItem.getEpisode();
        int iIntValue2 = episode != null ? episode.intValue() : -1;
        StringBuilder sbQ = a2.q("arvio_continue_preview:", strName, ":", id, ":");
        sbQ.append(iIntValue);
        sbQ.append(":");
        sbQ.append(iIntValue2);
        return sbQ.toString();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void runTvProviderCall(String action, r7.a<t0> block) {
        Object c0Var;
        try {
            c0Var = block.invoke();
        } catch (Throwable th) {
            c0Var = new x6.c0(th);
        }
        Throwable thA = x6.d0.a(c0Var);
        if (thA != null) {
            AppLogger.INSTANCE.w(TAG, "Skipping launcher publish action: " + action, thA);
        }
    }

    private final boolean supportsLauncherPublishing() {
        Object c0Var;
        PackageManager packageManager = this.context.getPackageManager();
        if (!packageManager.hasSystemFeature("android.software.leanback") && !packageManager.hasSystemFeature("android.hardware.type.television")) {
            return false;
        }
        try {
            c0Var = Boolean.valueOf(this.context.getPackageManager().resolveContentProvider("android.media.tv", 0) != null);
        } catch (Throwable th) {
            c0Var = new x6.c0(th);
        }
        Object obj = Boolean.FALSE;
        if (c0Var instanceof x6.c0) {
            c0Var = obj;
        }
        return ((Boolean) c0Var).booleanValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void syncPublishedRows(List<ContinueWatchingItem> items) {
        Long lEnsurePreviewChannel = ensurePreviewChannel();
        if (lEnsurePreviewChannel != null) {
            long jLongValue = lEnsurePreviewChannel.longValue();
            deletePreviewPrograms(jLongValue);
            deleteWatchNextPrograms();
            if (items.isEmpty()) {
                return;
            }
            int i10 = 0;
            for (Object obj : items) {
                int i11 = i10 + 1;
                if (i10 < 0) {
                    t7.a.Q();
                    throw null;
                }
                ContinueWatchingItem continueWatchingItem = (ContinueWatchingItem) obj;
                insertPreviewProgram(jLongValue, continueWatchingItem, i10);
                insertWatchNextProgram(continueWatchingItem, i10);
                i10 = i11;
            }
        }
    }

    private final int toPreviewType(ContinueWatchingItem continueWatchingItem) {
        return continueWatchingItem.getMediaType() == MediaType.TV ? 3 : 0;
    }

    private final String watchNextProgramId(ContinueWatchingItem continueWatchingItem) {
        String strName = continueWatchingItem.getMediaType().name();
        int id = continueWatchingItem.getId();
        Integer season = continueWatchingItem.getSeason();
        int iIntValue = season != null ? season.intValue() : -1;
        Integer episode = continueWatchingItem.getEpisode();
        int iIntValue2 = episode != null ? episode.intValue() : -1;
        StringBuilder sbQ = a2.q("arvio_continue_watchnext:", strName, ":", id, ":");
        sbQ.append(iIntValue);
        sbQ.append(":");
        sbQ.append(iIntValue2);
        return sbQ.toString();
    }

    public final Object clearPublishedPrograms(d7.d<? super t0> dVar) {
        Object objY;
        return (Build.VERSION.SDK_INT >= 26 && supportsLauncherPublishing() && (objY = ka.m0.y(x0.f19655d, new AnonymousClass2(null), dVar)) == e7.a.f15033i) ? objY : t0.f22605a;
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object refreshForCurrentProfile(d7.d<? super x6.t0> r9) {
        /*
            r8 = this;
            boolean r0 = r9 instanceof com.arflix.tv.data.repository.LauncherContinueWatchingRepository.C08661
            if (r0 == 0) goto L13
            r0 = r9
            com.arflix.tv.data.repository.LauncherContinueWatchingRepository$refreshForCurrentProfile$1 r0 = (com.arflix.tv.data.repository.LauncherContinueWatchingRepository.C08661) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            com.arflix.tv.data.repository.LauncherContinueWatchingRepository$refreshForCurrentProfile$1 r0 = new com.arflix.tv.data.repository.LauncherContinueWatchingRepository$refreshForCurrentProfile$1
            r0.<init>(r9)
        L18:
            java.lang.Object r9 = r0.result
            int r1 = r0.label
            r2 = 3
            r3 = 2
            r4 = 1
            x6.t0 r5 = x6.t0.f22605a
            r6 = 0
            e7.a r7 = e7.a.f15033i
            if (r1 == 0) goto L48
            if (r1 == r4) goto L30
            if (r1 == r3) goto L40
            if (r1 != r2) goto L38
            java.lang.Object r1 = r0.L$1
            java.util.List r1 = (java.util.List) r1
        L30:
            java.lang.Object r0 = r0.L$0
            java.lang.String r0 = (java.lang.String) r0
            k2.c.G(r9)
            return r5
        L38:
            java.lang.IllegalStateException r9 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r9.<init>(r0)
            throw r9
        L40:
            java.lang.Object r1 = r0.L$0
            java.lang.String r1 = (java.lang.String) r1
            k2.c.G(r9)
            goto L8b
        L48:
            k2.c.G(r9)
            int r9 = android.os.Build.VERSION.SDK_INT
            r1 = 26
            if (r9 < r1) goto La1
            boolean r9 = r8.supportsLauncherPublishing()
            if (r9 != 0) goto L58
            goto La1
        L58:
            com.arflix.tv.data.repository.ProfileManager r9 = r8.profileManager     // Catch: java.lang.Throwable -> L5f
            java.lang.String r9 = r9.getProfileIdSync()     // Catch: java.lang.Throwable -> L5f
            goto L66
        L5f:
            r9 = move-exception
            x6.c0 r1 = new x6.c0
            r1.<init>(r9)
            r9 = r1
        L66:
            boolean r1 = r9 instanceof x6.c0
            if (r1 == 0) goto L6d
            java.lang.String r9 = ""
        L6d:
            java.lang.String r9 = (java.lang.String) r9
            boolean r9 = kotlin.text.o.h0(r9)
            if (r9 == 0) goto L80
            r0.L$0 = r6
            r0.label = r4
            java.lang.Object r9 = r8.clearPublishedPrograms(r0)
            if (r9 != r7) goto La1
            goto La0
        L80:
            r0.L$0 = r6
            r0.label = r3
            java.lang.Object r9 = r8.loadPublisherItems(r0)
            if (r9 != r7) goto L8b
            goto La0
        L8b:
            java.util.List r9 = (java.util.List) r9
            ra.c r1 = ka.x0.f19655d
            com.arflix.tv.data.repository.LauncherContinueWatchingRepository$refreshForCurrentProfile$2 r3 = new com.arflix.tv.data.repository.LauncherContinueWatchingRepository$refreshForCurrentProfile$2
            r3.<init>(r9, r6)
            r0.L$0 = r6
            r0.L$1 = r6
            r0.label = r2
            java.lang.Object r9 = ka.m0.y(r1, r3, r0)
            if (r9 != r7) goto La1
        La0:
            return r7
        La1:
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.repository.LauncherContinueWatchingRepository.refreshForCurrentProfile(d7.d):java.lang.Object");
    }

    private final <T> T runTvProviderCall(String action, T fallback, r7.a<? extends T> block) {
        Object c0Var;
        try {
            c0Var = block.invoke();
        } catch (Throwable th) {
            c0Var = new x6.c0(th);
        }
        Throwable thA = x6.d0.a(c0Var);
        if (thA != null) {
            AppLogger.INSTANCE.w(TAG, "Skipping launcher publish action: " + action, thA);
        }
        return c0Var instanceof x6.c0 ? fallback : (T) c0Var;
    }
}
