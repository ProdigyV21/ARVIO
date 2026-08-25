package com.arflix.tv.data.repository;

import androidx.fragment.app.a2;
import androidx.media3.exoplayer.source.ProgressiveMediaSource;
import androidx.media3.extractor.text.ttml.TtmlNode;
import androidx.media3.session.MediaUtils;
import cb.b1;
import cb.l1;
import cb.r1;
import io.ktor.http.LinkHeader;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\t\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b#\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\"\b\u0087\b\u0018\u0000 l2\u00020\u0001:\u0002mlB\u0089\u0002\u0012\n\b\u0002\u0010\u0003\u001a\u0004\u0018\u00010\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0002\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0002\u0012\u0006\u0010\u0006\u001a\u00020\u0002\u0012\u0006\u0010\b\u001a\u00020\u0007\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u0007\u0012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u0007\u0012\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u0007\u0012\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u0007\u0012\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u0007\u0012\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u0002\u0012\b\b\u0002\u0010\u0011\u001a\u00020\u0010\u0012\b\b\u0002\u0010\u0013\u001a\u00020\u0012\u0012\b\b\u0002\u0010\u0014\u001a\u00020\u0012\u0012\n\b\u0002\u0010\u0015\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0002\u0010\u0016\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0002\u0010\u0017\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0002\u0010\u0018\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0002\u0010\u0019\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0002\u0010\u001a\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0002\u0010\u001b\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0002\u0010\u001c\u001a\u0004\u0018\u00010\u0002¢\u0006\u0004\b\u001d\u0010\u001eBù\u0001\b\u0010\u0012\u0006\u0010\u001f\u001a\u00020\u0007\u0012\b\u0010\u0003\u001a\u0004\u0018\u00010\u0002\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0002\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0002\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0002\u0012\u0006\u0010\b\u001a\u00020\u0007\u0012\b\u0010\t\u001a\u0004\u0018\u00010\u0007\u0012\b\u0010\n\u001a\u0004\u0018\u00010\u0007\u0012\b\u0010\u000b\u001a\u0004\u0018\u00010\u0007\u0012\b\u0010\f\u001a\u0004\u0018\u00010\u0007\u0012\b\u0010\r\u001a\u0004\u0018\u00010\u0007\u0012\b\u0010\u000e\u001a\u0004\u0018\u00010\u0002\u0012\b\u0010\u000f\u001a\u0004\u0018\u00010\u0002\u0012\u0006\u0010\u0011\u001a\u00020\u0010\u0012\u0006\u0010\u0013\u001a\u00020\u0012\u0012\u0006\u0010\u0014\u001a\u00020\u0012\u0012\b\u0010\u0015\u001a\u0004\u0018\u00010\u0002\u0012\b\u0010\u0016\u001a\u0004\u0018\u00010\u0002\u0012\b\u0010\u0017\u001a\u0004\u0018\u00010\u0002\u0012\b\u0010\u0018\u001a\u0004\u0018\u00010\u0002\u0012\b\u0010\u0019\u001a\u0004\u0018\u00010\u0002\u0012\b\u0010\u001a\u001a\u0004\u0018\u00010\u0002\u0012\b\u0010\u001b\u001a\u0004\u0018\u00010\u0002\u0012\b\u0010\u001c\u001a\u0004\u0018\u00010\u0002\u0012\b\u0010!\u001a\u0004\u0018\u00010 ¢\u0006\u0004\b\u001d\u0010\"J\u0012\u0010#\u001a\u0004\u0018\u00010\u0002HÆ\u0003¢\u0006\u0004\b#\u0010$J\u0010\u0010%\u001a\u00020\u0002HÆ\u0003¢\u0006\u0004\b%\u0010$J\u0012\u0010&\u001a\u0004\u0018\u00010\u0002HÆ\u0003¢\u0006\u0004\b&\u0010$J\u0010\u0010'\u001a\u00020\u0002HÆ\u0003¢\u0006\u0004\b'\u0010$J\u0010\u0010(\u001a\u00020\u0007HÆ\u0003¢\u0006\u0004\b(\u0010)J\u0012\u0010*\u001a\u0004\u0018\u00010\u0007HÆ\u0003¢\u0006\u0004\b*\u0010+J\u0012\u0010,\u001a\u0004\u0018\u00010\u0007HÆ\u0003¢\u0006\u0004\b,\u0010+J\u0012\u0010-\u001a\u0004\u0018\u00010\u0007HÆ\u0003¢\u0006\u0004\b-\u0010+J\u0012\u0010.\u001a\u0004\u0018\u00010\u0007HÆ\u0003¢\u0006\u0004\b.\u0010+J\u0012\u0010/\u001a\u0004\u0018\u00010\u0007HÆ\u0003¢\u0006\u0004\b/\u0010+J\u0012\u00100\u001a\u0004\u0018\u00010\u0002HÆ\u0003¢\u0006\u0004\b0\u0010$J\u0012\u00101\u001a\u0004\u0018\u00010\u0002HÆ\u0003¢\u0006\u0004\b1\u0010$J\u0010\u00102\u001a\u00020\u0010HÆ\u0003¢\u0006\u0004\b2\u00103J\u0010\u00104\u001a\u00020\u0012HÆ\u0003¢\u0006\u0004\b4\u00105J\u0010\u00106\u001a\u00020\u0012HÆ\u0003¢\u0006\u0004\b6\u00105J\u0012\u00107\u001a\u0004\u0018\u00010\u0002HÆ\u0003¢\u0006\u0004\b7\u0010$J\u0012\u00108\u001a\u0004\u0018\u00010\u0002HÆ\u0003¢\u0006\u0004\b8\u0010$J\u0012\u00109\u001a\u0004\u0018\u00010\u0002HÆ\u0003¢\u0006\u0004\b9\u0010$J\u0012\u0010:\u001a\u0004\u0018\u00010\u0002HÆ\u0003¢\u0006\u0004\b:\u0010$J\u0012\u0010;\u001a\u0004\u0018\u00010\u0002HÆ\u0003¢\u0006\u0004\b;\u0010$J\u0012\u0010<\u001a\u0004\u0018\u00010\u0002HÆ\u0003¢\u0006\u0004\b<\u0010$J\u0012\u0010=\u001a\u0004\u0018\u00010\u0002HÆ\u0003¢\u0006\u0004\b=\u0010$J\u0012\u0010>\u001a\u0004\u0018\u00010\u0002HÆ\u0003¢\u0006\u0004\b>\u0010$J\u0098\u0002\u0010?\u001a\u00020\u00002\n\b\u0002\u0010\u0003\u001a\u0004\u0018\u00010\u00022\b\b\u0002\u0010\u0004\u001a\u00020\u00022\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00022\b\b\u0002\u0010\u0006\u001a\u00020\u00022\b\b\u0002\u0010\b\u001a\u00020\u00072\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u00022\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u00022\b\b\u0002\u0010\u0011\u001a\u00020\u00102\b\b\u0002\u0010\u0013\u001a\u00020\u00122\b\b\u0002\u0010\u0014\u001a\u00020\u00122\n\b\u0002\u0010\u0015\u001a\u0004\u0018\u00010\u00022\n\b\u0002\u0010\u0016\u001a\u0004\u0018\u00010\u00022\n\b\u0002\u0010\u0017\u001a\u0004\u0018\u00010\u00022\n\b\u0002\u0010\u0018\u001a\u0004\u0018\u00010\u00022\n\b\u0002\u0010\u0019\u001a\u0004\u0018\u00010\u00022\n\b\u0002\u0010\u001a\u001a\u0004\u0018\u00010\u00022\n\b\u0002\u0010\u001b\u001a\u0004\u0018\u00010\u00022\n\b\u0002\u0010\u001c\u001a\u0004\u0018\u00010\u0002HÆ\u0001¢\u0006\u0004\b?\u0010@J\u0010\u0010A\u001a\u00020\u0002HÖ\u0001¢\u0006\u0004\bA\u0010$J\u0010\u0010B\u001a\u00020\u0007HÖ\u0001¢\u0006\u0004\bB\u0010)J\u001a\u0010E\u001a\u00020D2\b\u0010C\u001a\u0004\u0018\u00010\u0001HÖ\u0003¢\u0006\u0004\bE\u0010FJ'\u0010O\u001a\u00020L2\u0006\u0010G\u001a\u00020\u00002\u0006\u0010I\u001a\u00020H2\u0006\u0010K\u001a\u00020JH\u0001¢\u0006\u0004\bM\u0010NR\u0019\u0010\u0003\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010P\u001a\u0004\bQ\u0010$R\u0017\u0010\u0004\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0004\u0010P\u001a\u0004\bR\u0010$R\u0019\u0010\u0005\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\u0005\u0010P\u001a\u0004\bS\u0010$R\u0017\u0010\u0006\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0006\u0010P\u001a\u0004\bT\u0010$R\u0017\u0010\b\u001a\u00020\u00078\u0006¢\u0006\f\n\u0004\b\b\u0010U\u001a\u0004\bV\u0010)R\u0019\u0010\t\u001a\u0004\u0018\u00010\u00078\u0006¢\u0006\f\n\u0004\b\t\u0010W\u001a\u0004\bX\u0010+R\u0019\u0010\n\u001a\u0004\u0018\u00010\u00078\u0006¢\u0006\f\n\u0004\b\n\u0010W\u001a\u0004\bY\u0010+R\u0019\u0010\u000b\u001a\u0004\u0018\u00010\u00078\u0006¢\u0006\f\n\u0004\b\u000b\u0010W\u001a\u0004\bZ\u0010+R\u0019\u0010\f\u001a\u0004\u0018\u00010\u00078\u0006¢\u0006\f\n\u0004\b\f\u0010W\u001a\u0004\b[\u0010+R\u0019\u0010\r\u001a\u0004\u0018\u00010\u00078\u0006¢\u0006\f\n\u0004\b\r\u0010W\u001a\u0004\b\\\u0010+R\u0019\u0010\u000e\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\u000e\u0010P\u001a\u0004\b]\u0010$R\u0019\u0010\u000f\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\u000f\u0010P\u001a\u0004\b^\u0010$R\u0017\u0010\u0011\u001a\u00020\u00108\u0006¢\u0006\f\n\u0004\b\u0011\u0010_\u001a\u0004\b`\u00103R\u0017\u0010\u0013\u001a\u00020\u00128\u0006¢\u0006\f\n\u0004\b\u0013\u0010a\u001a\u0004\bb\u00105R\u0017\u0010\u0014\u001a\u00020\u00128\u0006¢\u0006\f\n\u0004\b\u0014\u0010a\u001a\u0004\bc\u00105R\u0019\u0010\u0015\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\u0015\u0010P\u001a\u0004\bd\u0010$R\u0019\u0010\u0016\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\u0016\u0010P\u001a\u0004\be\u0010$R\u0019\u0010\u0017\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\u0017\u0010P\u001a\u0004\bf\u0010$R\u0019\u0010\u0018\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\u0018\u0010P\u001a\u0004\bg\u0010$R\u0019\u0010\u0019\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\u0019\u0010P\u001a\u0004\bh\u0010$R\u0019\u0010\u001a\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\u001a\u0010P\u001a\u0004\bi\u0010$R\u0019\u0010\u001b\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\u001b\u0010P\u001a\u0004\bj\u0010$R\u0019\u0010\u001c\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\u001c\u0010P\u001a\u0004\bk\u0010$¨\u0006n"}, d2 = {"Lcom/arflix/tv/data/repository/WatchHistoryEntry;", "", "", TtmlNode.ATTR_ID, "user_id", "profile_id", "media_type", "", "show_tmdb_id", "show_trakt_id", "season", "episode", "trakt_episode_id", "tmdb_episode_id", LinkHeader.Parameters.Title, "episode_title", "", "progress", "", "duration_seconds", "position_seconds", "paused_at", "updated_at", "source", "backdrop_path", "poster_path", "stream_key", "stream_addon_id", "stream_title", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;FJJLjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "seen0", "Lcb/l1;", "serializationConstructorMarker", "(ILjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;FJJLjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lcb/l1;)V", "component1", "()Ljava/lang/String;", "component2", "component3", "component4", "component5", "()I", "component6", "()Ljava/lang/Integer;", "component7", "component8", "component9", "component10", "component11", "component12", "component13", "()F", "component14", "()J", "component15", "component16", "component17", "component18", "component19", "component20", "component21", "component22", "component23", "copy", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;FJJLjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Lcom/arflix/tv/data/repository/WatchHistoryEntry;", "toString", "hashCode", "other", "", "equals", "(Ljava/lang/Object;)Z", "self", "Lbb/c;", "output", "Lab/g;", "serialDesc", "Lx6/t0;", "write$Self$app_playRelease", "(Lcom/arflix/tv/data/repository/WatchHistoryEntry;Lbb/c;Lab/g;)V", "write$Self", "Ljava/lang/String;", "getId", "getUser_id", "getProfile_id", "getMedia_type", "I", "getShow_tmdb_id", "Ljava/lang/Integer;", "getShow_trakt_id", "getSeason", "getEpisode", "getTrakt_episode_id", "getTmdb_episode_id", "getTitle", "getEpisode_title", "F", "getProgress", "J", "getDuration_seconds", "getPosition_seconds", "getPaused_at", "getUpdated_at", "getSource", "getBackdrop_path", "getPoster_path", "getStream_key", "getStream_addon_id", "getStream_title", "Companion", "$serializer", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
@ya.r
public final /* data */ class WatchHistoryEntry {
    public static final int $stable = 0;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final String backdrop_path;
    private final long duration_seconds;
    private final Integer episode;
    private final String episode_title;
    private final String id;
    private final String media_type;
    private final String paused_at;
    private final long position_seconds;
    private final String poster_path;
    private final String profile_id;
    private final float progress;
    private final Integer season;
    private final int show_tmdb_id;
    private final Integer show_trakt_id;
    private final String source;
    private final String stream_addon_id;
    private final String stream_key;
    private final String stream_title;
    private final String title;
    private final Integer tmdb_episode_id;
    private final Integer trakt_episode_id;
    private final String updated_at;
    private final String user_id;

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0013\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004¢\u0006\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Lcom/arflix/tv/data/repository/WatchHistoryEntry$Companion;", "", "<init>", "()V", "Lya/h;", "Lcom/arflix/tv/data/repository/WatchHistoryEntry;", "serializer", "()Lya/h;", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(kotlin.jvm.internal.h hVar) {
            this();
        }

        public final ya.h<WatchHistoryEntry> serializer() {
            return WatchHistoryEntry$$serializer.INSTANCE;
        }

        private Companion() {
        }
    }

    public /* synthetic */ WatchHistoryEntry(int i10, String str, String str2, String str3, String str4, int i11, Integer num, Integer num2, Integer num3, Integer num4, Integer num5, String str5, String str6, float f10, long j10, long j11, String str7, String str8, String str9, String str10, String str11, String str12, String str13, String str14, l1 l1Var) {
        if (26 != (i10 & 26)) {
            b1.h(i10, 26, WatchHistoryEntry$$serializer.INSTANCE.getDescriptor());
            throw null;
        }
        if ((i10 & 1) == 0) {
            this.id = null;
        } else {
            this.id = str;
        }
        this.user_id = str2;
        if ((i10 & 4) == 0) {
            this.profile_id = null;
        } else {
            this.profile_id = str3;
        }
        this.media_type = str4;
        this.show_tmdb_id = i11;
        if ((i10 & 32) == 0) {
            this.show_trakt_id = null;
        } else {
            this.show_trakt_id = num;
        }
        if ((i10 & 64) == 0) {
            this.season = null;
        } else {
            this.season = num2;
        }
        if ((i10 & 128) == 0) {
            this.episode = null;
        } else {
            this.episode = num3;
        }
        if ((i10 & 256) == 0) {
            this.trakt_episode_id = null;
        } else {
            this.trakt_episode_id = num4;
        }
        if ((i10 & 512) == 0) {
            this.tmdb_episode_id = null;
        } else {
            this.tmdb_episode_id = num5;
        }
        if ((i10 & 1024) == 0) {
            this.title = null;
        } else {
            this.title = str5;
        }
        if ((i10 & 2048) == 0) {
            this.episode_title = null;
        } else {
            this.episode_title = str6;
        }
        this.progress = (i10 & 4096) == 0 ? 0.0f : f10;
        if ((i10 & 8192) == 0) {
            this.duration_seconds = 0L;
        } else {
            this.duration_seconds = j10;
        }
        if ((i10 & 16384) == 0) {
            this.position_seconds = 0L;
        } else {
            this.position_seconds = j11;
        }
        if ((32768 & i10) == 0) {
            this.paused_at = null;
        } else {
            this.paused_at = str7;
        }
        if ((65536 & i10) == 0) {
            this.updated_at = null;
        } else {
            this.updated_at = str8;
        }
        if ((131072 & i10) == 0) {
            this.source = null;
        } else {
            this.source = str9;
        }
        if ((262144 & i10) == 0) {
            this.backdrop_path = null;
        } else {
            this.backdrop_path = str10;
        }
        if ((524288 & i10) == 0) {
            this.poster_path = null;
        } else {
            this.poster_path = str11;
        }
        if ((1048576 & i10) == 0) {
            this.stream_key = null;
        } else {
            this.stream_key = str12;
        }
        if ((2097152 & i10) == 0) {
            this.stream_addon_id = null;
        } else {
            this.stream_addon_id = str13;
        }
        if ((i10 & 4194304) == 0) {
            this.stream_title = null;
        } else {
            this.stream_title = str14;
        }
    }

    public static /* synthetic */ WatchHistoryEntry copy$default(WatchHistoryEntry watchHistoryEntry, String str, String str2, String str3, String str4, int i10, Integer num, Integer num2, Integer num3, Integer num4, Integer num5, String str5, String str6, float f10, long j10, long j11, String str7, String str8, String str9, String str10, String str11, String str12, String str13, String str14, int i11, Object obj) {
        String str15;
        String str16;
        String str17 = (i11 & 1) != 0 ? watchHistoryEntry.id : str;
        String str18 = (i11 & 2) != 0 ? watchHistoryEntry.user_id : str2;
        String str19 = (i11 & 4) != 0 ? watchHistoryEntry.profile_id : str3;
        String str20 = (i11 & 8) != 0 ? watchHistoryEntry.media_type : str4;
        int i12 = (i11 & 16) != 0 ? watchHistoryEntry.show_tmdb_id : i10;
        Integer num6 = (i11 & 32) != 0 ? watchHistoryEntry.show_trakt_id : num;
        Integer num7 = (i11 & 64) != 0 ? watchHistoryEntry.season : num2;
        Integer num8 = (i11 & 128) != 0 ? watchHistoryEntry.episode : num3;
        Integer num9 = (i11 & 256) != 0 ? watchHistoryEntry.trakt_episode_id : num4;
        Integer num10 = (i11 & 512) != 0 ? watchHistoryEntry.tmdb_episode_id : num5;
        String str21 = (i11 & 1024) != 0 ? watchHistoryEntry.title : str5;
        String str22 = (i11 & 2048) != 0 ? watchHistoryEntry.episode_title : str6;
        float f11 = (i11 & 4096) != 0 ? watchHistoryEntry.progress : f10;
        String str23 = str17;
        String str24 = str18;
        long j12 = (i11 & 8192) != 0 ? watchHistoryEntry.duration_seconds : j10;
        long j13 = (i11 & 16384) != 0 ? watchHistoryEntry.position_seconds : j11;
        String str25 = (i11 & 32768) != 0 ? watchHistoryEntry.paused_at : str7;
        String str26 = (i11 & 65536) != 0 ? watchHistoryEntry.updated_at : str8;
        long j14 = j13;
        String str27 = (i11 & 131072) != 0 ? watchHistoryEntry.source : str9;
        String str28 = (i11 & MediaUtils.TRANSACTION_SIZE_LIMIT_IN_BYTES) != 0 ? watchHistoryEntry.backdrop_path : str10;
        String str29 = str27;
        String str30 = (i11 & 524288) != 0 ? watchHistoryEntry.poster_path : str11;
        String str31 = (i11 & ProgressiveMediaSource.DEFAULT_LOADING_CHECK_INTERVAL_BYTES) != 0 ? watchHistoryEntry.stream_key : str12;
        String str32 = (i11 & 2097152) != 0 ? watchHistoryEntry.stream_addon_id : str13;
        if ((i11 & 4194304) != 0) {
            str16 = str32;
            str15 = watchHistoryEntry.stream_title;
        } else {
            str15 = str14;
            str16 = str32;
        }
        return watchHistoryEntry.copy(str23, str24, str19, str20, i12, num6, num7, num8, num9, num10, str21, str22, f11, j12, j14, str25, str26, str29, str28, str30, str31, str16, str15);
    }

    @q7.n
    public static final /* synthetic */ void write$Self$app_playRelease(WatchHistoryEntry self, bb.c output, ab.g serialDesc) {
        if (output.C() || self.id != null) {
            output.m(serialDesc, 0, r1.f7553a, self.id);
        }
        output.x(serialDesc, 1, self.user_id);
        if (output.C() || self.profile_id != null) {
            output.m(serialDesc, 2, r1.f7553a, self.profile_id);
        }
        output.x(serialDesc, 3, self.media_type);
        output.r(4, self.show_tmdb_id, serialDesc);
        if (output.C() || self.show_trakt_id != null) {
            output.m(serialDesc, 5, cb.l0.f7529a, self.show_trakt_id);
        }
        if (output.C() || self.season != null) {
            output.m(serialDesc, 6, cb.l0.f7529a, self.season);
        }
        if (output.C() || self.episode != null) {
            output.m(serialDesc, 7, cb.l0.f7529a, self.episode);
        }
        if (output.C() || self.trakt_episode_id != null) {
            output.m(serialDesc, 8, cb.l0.f7529a, self.trakt_episode_id);
        }
        if (output.C() || self.tmdb_episode_id != null) {
            output.m(serialDesc, 9, cb.l0.f7529a, self.tmdb_episode_id);
        }
        if (output.C() || self.title != null) {
            output.m(serialDesc, 10, r1.f7553a, self.title);
        }
        if (output.C() || self.episode_title != null) {
            output.m(serialDesc, 11, r1.f7553a, self.episode_title);
        }
        if (output.C() || Float.compare(self.progress, 0.0f) != 0) {
            output.B(serialDesc, 12, self.progress);
        }
        if (output.C() || self.duration_seconds != 0) {
            output.g(serialDesc, 13, self.duration_seconds);
        }
        if (output.C() || self.position_seconds != 0) {
            output.g(serialDesc, 14, self.position_seconds);
        }
        if (output.C() || self.paused_at != null) {
            output.m(serialDesc, 15, r1.f7553a, self.paused_at);
        }
        if (output.C() || self.updated_at != null) {
            output.m(serialDesc, 16, r1.f7553a, self.updated_at);
        }
        if (output.C() || self.source != null) {
            output.m(serialDesc, 17, r1.f7553a, self.source);
        }
        if (output.C() || self.backdrop_path != null) {
            output.m(serialDesc, 18, r1.f7553a, self.backdrop_path);
        }
        if (output.C() || self.poster_path != null) {
            output.m(serialDesc, 19, r1.f7553a, self.poster_path);
        }
        if (output.C() || self.stream_key != null) {
            output.m(serialDesc, 20, r1.f7553a, self.stream_key);
        }
        if (output.C() || self.stream_addon_id != null) {
            output.m(serialDesc, 21, r1.f7553a, self.stream_addon_id);
        }
        if (!output.C() && self.stream_title == null) {
            return;
        }
        output.m(serialDesc, 22, r1.f7553a, self.stream_title);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getId() {
        return this.id;
    }

    /* JADX INFO: renamed from: component10, reason: from getter */
    public final Integer getTmdb_episode_id() {
        return this.tmdb_episode_id;
    }

    /* JADX INFO: renamed from: component11, reason: from getter */
    public final String getTitle() {
        return this.title;
    }

    /* JADX INFO: renamed from: component12, reason: from getter */
    public final String getEpisode_title() {
        return this.episode_title;
    }

    /* JADX INFO: renamed from: component13, reason: from getter */
    public final float getProgress() {
        return this.progress;
    }

    /* JADX INFO: renamed from: component14, reason: from getter */
    public final long getDuration_seconds() {
        return this.duration_seconds;
    }

    /* JADX INFO: renamed from: component15, reason: from getter */
    public final long getPosition_seconds() {
        return this.position_seconds;
    }

    /* JADX INFO: renamed from: component16, reason: from getter */
    public final String getPaused_at() {
        return this.paused_at;
    }

    /* JADX INFO: renamed from: component17, reason: from getter */
    public final String getUpdated_at() {
        return this.updated_at;
    }

    /* JADX INFO: renamed from: component18, reason: from getter */
    public final String getSource() {
        return this.source;
    }

    /* JADX INFO: renamed from: component19, reason: from getter */
    public final String getBackdrop_path() {
        return this.backdrop_path;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getUser_id() {
        return this.user_id;
    }

    /* JADX INFO: renamed from: component20, reason: from getter */
    public final String getPoster_path() {
        return this.poster_path;
    }

    /* JADX INFO: renamed from: component21, reason: from getter */
    public final String getStream_key() {
        return this.stream_key;
    }

    /* JADX INFO: renamed from: component22, reason: from getter */
    public final String getStream_addon_id() {
        return this.stream_addon_id;
    }

    /* JADX INFO: renamed from: component23, reason: from getter */
    public final String getStream_title() {
        return this.stream_title;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final String getProfile_id() {
        return this.profile_id;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final String getMedia_type() {
        return this.media_type;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final int getShow_tmdb_id() {
        return this.show_tmdb_id;
    }

    /* JADX INFO: renamed from: component6, reason: from getter */
    public final Integer getShow_trakt_id() {
        return this.show_trakt_id;
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
    public final Integer getTrakt_episode_id() {
        return this.trakt_episode_id;
    }

    public final WatchHistoryEntry copy(String id, String user_id, String profile_id, String media_type, int show_tmdb_id, Integer show_trakt_id, Integer season, Integer episode, Integer trakt_episode_id, Integer tmdb_episode_id, String title, String episode_title, float progress, long duration_seconds, long position_seconds, String paused_at, String updated_at, String source, String backdrop_path, String poster_path, String stream_key, String stream_addon_id, String stream_title) {
        return new WatchHistoryEntry(id, user_id, profile_id, media_type, show_tmdb_id, show_trakt_id, season, episode, trakt_episode_id, tmdb_episode_id, title, episode_title, progress, duration_seconds, position_seconds, paused_at, updated_at, source, backdrop_path, poster_path, stream_key, stream_addon_id, stream_title);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof WatchHistoryEntry)) {
            return false;
        }
        WatchHistoryEntry watchHistoryEntry = (WatchHistoryEntry) other;
        return kotlin.jvm.internal.p.a(this.id, watchHistoryEntry.id) && kotlin.jvm.internal.p.a(this.user_id, watchHistoryEntry.user_id) && kotlin.jvm.internal.p.a(this.profile_id, watchHistoryEntry.profile_id) && kotlin.jvm.internal.p.a(this.media_type, watchHistoryEntry.media_type) && this.show_tmdb_id == watchHistoryEntry.show_tmdb_id && kotlin.jvm.internal.p.a(this.show_trakt_id, watchHistoryEntry.show_trakt_id) && kotlin.jvm.internal.p.a(this.season, watchHistoryEntry.season) && kotlin.jvm.internal.p.a(this.episode, watchHistoryEntry.episode) && kotlin.jvm.internal.p.a(this.trakt_episode_id, watchHistoryEntry.trakt_episode_id) && kotlin.jvm.internal.p.a(this.tmdb_episode_id, watchHistoryEntry.tmdb_episode_id) && kotlin.jvm.internal.p.a(this.title, watchHistoryEntry.title) && kotlin.jvm.internal.p.a(this.episode_title, watchHistoryEntry.episode_title) && Float.compare(this.progress, watchHistoryEntry.progress) == 0 && this.duration_seconds == watchHistoryEntry.duration_seconds && this.position_seconds == watchHistoryEntry.position_seconds && kotlin.jvm.internal.p.a(this.paused_at, watchHistoryEntry.paused_at) && kotlin.jvm.internal.p.a(this.updated_at, watchHistoryEntry.updated_at) && kotlin.jvm.internal.p.a(this.source, watchHistoryEntry.source) && kotlin.jvm.internal.p.a(this.backdrop_path, watchHistoryEntry.backdrop_path) && kotlin.jvm.internal.p.a(this.poster_path, watchHistoryEntry.poster_path) && kotlin.jvm.internal.p.a(this.stream_key, watchHistoryEntry.stream_key) && kotlin.jvm.internal.p.a(this.stream_addon_id, watchHistoryEntry.stream_addon_id) && kotlin.jvm.internal.p.a(this.stream_title, watchHistoryEntry.stream_title);
    }

    public final String getBackdrop_path() {
        return this.backdrop_path;
    }

    public final long getDuration_seconds() {
        return this.duration_seconds;
    }

    public final Integer getEpisode() {
        return this.episode;
    }

    public final String getEpisode_title() {
        return this.episode_title;
    }

    public final String getId() {
        return this.id;
    }

    public final String getMedia_type() {
        return this.media_type;
    }

    public final String getPaused_at() {
        return this.paused_at;
    }

    public final long getPosition_seconds() {
        return this.position_seconds;
    }

    public final String getPoster_path() {
        return this.poster_path;
    }

    public final String getProfile_id() {
        return this.profile_id;
    }

    public final float getProgress() {
        return this.progress;
    }

    public final Integer getSeason() {
        return this.season;
    }

    public final int getShow_tmdb_id() {
        return this.show_tmdb_id;
    }

    public final Integer getShow_trakt_id() {
        return this.show_trakt_id;
    }

    public final String getSource() {
        return this.source;
    }

    public final String getStream_addon_id() {
        return this.stream_addon_id;
    }

    public final String getStream_key() {
        return this.stream_key;
    }

    public final String getStream_title() {
        return this.stream_title;
    }

    public final String getTitle() {
        return this.title;
    }

    public final Integer getTmdb_episode_id() {
        return this.tmdb_episode_id;
    }

    public final Integer getTrakt_episode_id() {
        return this.trakt_episode_id;
    }

    public final String getUpdated_at() {
        return this.updated_at;
    }

    public final String getUser_id() {
        return this.user_id;
    }

    public int hashCode() {
        String str = this.id;
        int iC = androidx.compose.foundation.c.c((str == null ? 0 : str.hashCode()) * 31, 31, this.user_id);
        String str2 = this.profile_id;
        int iC2 = (androidx.compose.foundation.c.c((iC + (str2 == null ? 0 : str2.hashCode())) * 31, 31, this.media_type) + this.show_tmdb_id) * 31;
        Integer num = this.show_trakt_id;
        int iHashCode = (iC2 + (num == null ? 0 : num.hashCode())) * 31;
        Integer num2 = this.season;
        int iHashCode2 = (iHashCode + (num2 == null ? 0 : num2.hashCode())) * 31;
        Integer num3 = this.episode;
        int iHashCode3 = (iHashCode2 + (num3 == null ? 0 : num3.hashCode())) * 31;
        Integer num4 = this.trakt_episode_id;
        int iHashCode4 = (iHashCode3 + (num4 == null ? 0 : num4.hashCode())) * 31;
        Integer num5 = this.tmdb_episode_id;
        int iHashCode5 = (iHashCode4 + (num5 == null ? 0 : num5.hashCode())) * 31;
        String str3 = this.title;
        int iHashCode6 = (iHashCode5 + (str3 == null ? 0 : str3.hashCode())) * 31;
        String str4 = this.episode_title;
        int iB = a0.c.b(this.progress, (iHashCode6 + (str4 == null ? 0 : str4.hashCode())) * 31, 31);
        long j10 = this.duration_seconds;
        int i10 = (iB + ((int) (j10 ^ (j10 >>> 32)))) * 31;
        long j11 = this.position_seconds;
        int i11 = (i10 + ((int) (j11 ^ (j11 >>> 32)))) * 31;
        String str5 = this.paused_at;
        int iHashCode7 = (i11 + (str5 == null ? 0 : str5.hashCode())) * 31;
        String str6 = this.updated_at;
        int iHashCode8 = (iHashCode7 + (str6 == null ? 0 : str6.hashCode())) * 31;
        String str7 = this.source;
        int iHashCode9 = (iHashCode8 + (str7 == null ? 0 : str7.hashCode())) * 31;
        String str8 = this.backdrop_path;
        int iHashCode10 = (iHashCode9 + (str8 == null ? 0 : str8.hashCode())) * 31;
        String str9 = this.poster_path;
        int iHashCode11 = (iHashCode10 + (str9 == null ? 0 : str9.hashCode())) * 31;
        String str10 = this.stream_key;
        int iHashCode12 = (iHashCode11 + (str10 == null ? 0 : str10.hashCode())) * 31;
        String str11 = this.stream_addon_id;
        int iHashCode13 = (iHashCode12 + (str11 == null ? 0 : str11.hashCode())) * 31;
        String str12 = this.stream_title;
        return iHashCode13 + (str12 != null ? str12.hashCode() : 0);
    }

    public String toString() {
        String str = this.id;
        String str2 = this.user_id;
        String str3 = this.profile_id;
        String str4 = this.media_type;
        int i10 = this.show_tmdb_id;
        Integer num = this.show_trakt_id;
        Integer num2 = this.season;
        Integer num3 = this.episode;
        Integer num4 = this.trakt_episode_id;
        Integer num5 = this.tmdb_episode_id;
        String str5 = this.title;
        String str6 = this.episode_title;
        float f10 = this.progress;
        long j10 = this.duration_seconds;
        long j11 = this.position_seconds;
        String str7 = this.paused_at;
        String str8 = this.updated_at;
        String str9 = this.source;
        String str10 = this.backdrop_path;
        String str11 = this.poster_path;
        String str12 = this.stream_key;
        String str13 = this.stream_addon_id;
        String str14 = this.stream_title;
        StringBuilder sbR = a2.r("WatchHistoryEntry(id=", str, ", user_id=", str2, ", profile_id=");
        y.a.i(sbR, str3, ", media_type=", str4, ", show_tmdb_id=");
        sbR.append(i10);
        sbR.append(", show_trakt_id=");
        sbR.append(num);
        sbR.append(", season=");
        a2.z(sbR, num2, ", episode=", num3, ", trakt_episode_id=");
        a2.z(sbR, num4, ", tmdb_episode_id=", num5, ", title=");
        y.a.i(sbR, str5, ", episode_title=", str6, ", progress=");
        sbR.append(f10);
        sbR.append(", duration_seconds=");
        sbR.append(j10);
        androidx.compose.foundation.c.z(sbR, ", position_seconds=", j11, ", paused_at=");
        y.a.i(sbR, str7, ", updated_at=", str8, ", source=");
        y.a.i(sbR, str9, ", backdrop_path=", str10, ", poster_path=");
        y.a.i(sbR, str11, ", stream_key=", str12, ", stream_addon_id=");
        return a2.n(sbR, str13, ", stream_title=", str14, ")");
    }

    public WatchHistoryEntry(String str, String str2, String str3, String str4, int i10, Integer num, Integer num2, Integer num3, Integer num4, Integer num5, String str5, String str6, float f10, long j10, long j11, String str7, String str8, String str9, String str10, String str11, String str12, String str13, String str14) {
        this.id = str;
        this.user_id = str2;
        this.profile_id = str3;
        this.media_type = str4;
        this.show_tmdb_id = i10;
        this.show_trakt_id = num;
        this.season = num2;
        this.episode = num3;
        this.trakt_episode_id = num4;
        this.tmdb_episode_id = num5;
        this.title = str5;
        this.episode_title = str6;
        this.progress = f10;
        this.duration_seconds = j10;
        this.position_seconds = j11;
        this.paused_at = str7;
        this.updated_at = str8;
        this.source = str9;
        this.backdrop_path = str10;
        this.poster_path = str11;
        this.stream_key = str12;
        this.stream_addon_id = str13;
        this.stream_title = str14;
    }

    public /* synthetic */ WatchHistoryEntry(String str, String str2, String str3, String str4, int i10, Integer num, Integer num2, Integer num3, Integer num4, Integer num5, String str5, String str6, float f10, long j10, long j11, String str7, String str8, String str9, String str10, String str11, String str12, String str13, String str14, int i11, kotlin.jvm.internal.h hVar) {
        this((i11 & 1) != 0 ? null : str, str2, (i11 & 4) != 0 ? null : str3, str4, i10, (i11 & 32) != 0 ? null : num, (i11 & 64) != 0 ? null : num2, (i11 & 128) != 0 ? null : num3, (i11 & 256) != 0 ? null : num4, (i11 & 512) != 0 ? null : num5, (i11 & 1024) != 0 ? null : str5, (i11 & 2048) != 0 ? null : str6, (i11 & 4096) != 0 ? 0.0f : f10, (i11 & 8192) != 0 ? 0L : j10, (i11 & 16384) != 0 ? 0L : j11, (32768 & i11) != 0 ? null : str7, (65536 & i11) != 0 ? null : str8, (131072 & i11) != 0 ? null : str9, (262144 & i11) != 0 ? null : str10, (524288 & i11) != 0 ? null : str11, (1048576 & i11) != 0 ? null : str12, (2097152 & i11) != 0 ? null : str13, (i11 & 4194304) != 0 ? null : str14);
    }
}
