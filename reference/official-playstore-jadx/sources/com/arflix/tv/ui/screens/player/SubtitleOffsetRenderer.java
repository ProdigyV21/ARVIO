package com.arflix.tv.ui.screens.player;

import androidx.media3.common.Format;
import androidx.media3.common.Timeline;
import androidx.media3.common.text.Cue;
import androidx.media3.common.text.CueGroup;
import androidx.media3.common.util.Clock;
import androidx.media3.exoplayer.ExoPlaybackException;
import androidx.media3.exoplayer.MediaClock;
import androidx.media3.exoplayer.Renderer;
import androidx.media3.exoplayer.RendererCapabilities;
import androidx.media3.exoplayer.RendererConfiguration;
import androidx.media3.exoplayer.analytics.PlayerId;
import androidx.media3.exoplayer.audio.SilenceSkippingAudioProcessor;
import androidx.media3.exoplayer.dash.DashMediaSource;
import androidx.media3.exoplayer.source.MediaSource;
import androidx.media3.exoplayer.source.SampleStream;
import androidx.media3.extractor.text.ttml.TtmlNode;
import io.ktor.http.ContentDisposition;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CancellationException;
import java.util.concurrent.atomic.AtomicLong;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000Ê\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0011\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0000\n\u0002\b\u000b\n\u0002\u0010#\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0003\u0018\u0000 \u0082\u00012\u00020\u0001:\u0002\u0082\u0001B)\u0012\u0006\u0010\u0002\u001a\u00020\u0001\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\b\b\u0002\u0010\b\u001a\u00020\u0007¢\u0006\u0004\b\t\u0010\nJ\u001f\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\f\u001a\u00020\u000b2\u0006\u0010\r\u001a\u00020\u000bH\u0016¢\u0006\u0004\b\u000f\u0010\u0010J\r\u0010\u0011\u001a\u00020\u000e¢\u0006\u0004\b\u0011\u0010\u0012J\u0013\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00140\u0013¢\u0006\u0004\b\u0015\u0010\u0016J'\u0010\u001a\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\u000b0\u00190\u00132\u0006\u0010\u0018\u001a\u00020\u0017¢\u0006\u0004\b\u001a\u0010\u001bJ\u0015\u0010\u001d\u001a\u00070\u0014¢\u0006\u0002\b\u001cH\u0096\u0001¢\u0006\u0004\b\u001d\u0010\u001eJ\u0010\u0010\u001f\u001a\u00020\u0017H\u0096\u0001¢\u0006\u0004\b\u001f\u0010 J\u0015\u0010\"\u001a\u00070!¢\u0006\u0002\b\u001cH\u0096\u0001¢\u0006\u0004\b\"\u0010#J2\u0010)\u001a\u00020\u000e2\u0006\u0010$\u001a\u00020\u00172\u000b\u0010&\u001a\u00070%¢\u0006\u0002\b\u001c2\u000b\u0010(\u001a\u00070'¢\u0006\u0002\b\u001cH\u0096\u0001¢\u0006\u0004\b)\u0010*J\u0017\u0010,\u001a\t\u0018\u00010+¢\u0006\u0002\b\u001cH\u0097\u0001¢\u0006\u0004\b,\u0010-J\u0010\u0010.\u001a\u00020\u0017H\u0096\u0001¢\u0006\u0004\b.\u0010 J\u008f\u0001\u0010<\u001a\u00020\u000e2\u000b\u0010$\u001a\u00070/¢\u0006\u0002\b\u001c2.\u0010&\u001a*\u0012\t\u0012\u000701¢\u0006\u0002\b\u001c 2*\u0014\u0012\u000b\b\u0001\u0012\u000701¢\u0006\u0002\b\u001c00¢\u0006\u0002\b\u001c00¢\u0006\u0002\b\u001c2\u000b\u0010(\u001a\u000703¢\u0006\u0002\b\u001c2\u0006\u00104\u001a\u00020\u000b2\u0006\u00106\u001a\u0002052\u0006\u00107\u001a\u0002052\u0006\u00108\u001a\u00020\u000b2\u0006\u00109\u001a\u00020\u000b2\u000b\u0010;\u001a\u00070:¢\u0006\u0002\b\u001cH\u0096\u0001¢\u0006\u0004\b<\u0010=J\u0010\u0010>\u001a\u00020\u000eH\u0096\u0001¢\u0006\u0004\b>\u0010\u0012Jj\u0010?\u001a\u00020\u000e2.\u0010$\u001a*\u0012\t\u0012\u000701¢\u0006\u0002\b\u001c 2*\u0014\u0012\u000b\b\u0001\u0012\u000701¢\u0006\u0002\b\u001c00¢\u0006\u0002\b\u001c00¢\u0006\u0002\b\u001c2\u000b\u0010&\u001a\u000703¢\u0006\u0002\b\u001c2\u0006\u0010(\u001a\u00020\u000b2\u0006\u00104\u001a\u00020\u000b2\u000b\u00106\u001a\u00070:¢\u0006\u0002\b\u001cH\u0096\u0001¢\u0006\u0004\b?\u0010@J\u0017\u0010A\u001a\t\u0018\u000103¢\u0006\u0002\b\u001cH\u0097\u0001¢\u0006\u0004\bA\u0010BJ\u0010\u0010C\u001a\u000205H\u0096\u0001¢\u0006\u0004\bC\u0010DJ\u0010\u0010E\u001a\u00020\u000bH\u0096\u0001¢\u0006\u0004\bE\u0010FJ\u0010\u0010G\u001a\u00020\u000eH\u0096\u0001¢\u0006\u0004\bG\u0010\u0012J\u0010\u0010H\u001a\u000205H\u0096\u0001¢\u0006\u0004\bH\u0010DJ\u0010\u0010I\u001a\u00020\u000eH\u0096\u0001¢\u0006\u0004\bI\u0010\u0012J \u0010J\u001a\u00020\u000e2\u0006\u0010$\u001a\u00020\u000b2\u0006\u0010&\u001a\u000205H\u0096\u0001¢\u0006\u0004\bJ\u0010KJ\u001d\u0010M\u001a\u00020\u000e2\u000b\u0010$\u001a\u00070L¢\u0006\u0002\b\u001cH\u0096\u0001¢\u0006\u0004\bM\u0010NJ\u0010\u0010O\u001a\u000205H\u0096\u0001¢\u0006\u0004\bO\u0010DJ\u0010\u0010P\u001a\u000205H\u0096\u0001¢\u0006\u0004\bP\u0010DJ\u0010\u0010Q\u001a\u00020\u000eH\u0096\u0001¢\u0006\u0004\bQ\u0010\u0012J\u0010\u0010R\u001a\u00020\u000eH\u0096\u0001¢\u0006\u0004\bR\u0010\u0012J\u0010\u0010S\u001a\u00020\u000eH\u0096\u0001¢\u0006\u0004\bS\u0010\u0012J)\u0010U\u001a\u00020\u000e2\u0006\u0010$\u001a\u00020\u00172\u000f\b\u0001\u0010&\u001a\t\u0018\u00010T¢\u0006\u0002\b\u001cH\u0096\u0001¢\u0006\u0004\bU\u0010VJ\u000f\u0010W\u001a\u00020\u000eH\u0002¢\u0006\u0004\bW\u0010\u0012J\u001d\u0010Y\u001a\u00020\u000e2\f\u0010X\u001a\b\u0012\u0004\u0012\u00020\u00140\u0013H\u0002¢\u0006\u0004\bY\u0010ZJ\u000f\u0010[\u001a\u00020\u000bH\u0002¢\u0006\u0004\b[\u0010FJ%\u0010]\u001a\u0010\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\u000b\u0018\u00010\u00192\u0006\u0010\\\u001a\u00020TH\u0002¢\u0006\u0004\b]\u0010^J-\u0010b\u001a\u00020\u00172\u0006\u0010_\u001a\u00020T2\f\u0010X\u001a\b\u0012\u0004\u0012\u00020\u00140`2\u0006\u0010a\u001a\u000205H\u0002¢\u0006\u0004\bb\u0010cJ/\u0010d\u001a\u0002052\b\u0010\\\u001a\u0004\u0018\u00010T2\f\u0010X\u001a\b\u0012\u0004\u0012\u00020\u00140`2\u0006\u0010a\u001a\u000205H\u0002¢\u0006\u0004\bd\u0010eJ%\u0010h\u001a\u00020\u00142\f\u0010g\u001a\b\u0012\u0004\u0012\u00020f0\u00132\u0006\u0010a\u001a\u000205H\u0002¢\u0006\u0004\bh\u0010iJ\u0017\u0010k\u001a\u00020\u00142\u0006\u0010j\u001a\u00020\u0014H\u0002¢\u0006\u0004\bk\u0010lJ%\u0010q\u001a\u0004\u0018\u00010p2\n\u0010n\u001a\u0006\u0012\u0002\b\u00030m2\u0006\u0010o\u001a\u00020\u0014H\u0002¢\u0006\u0004\bq\u0010rR\u0014\u0010\u0002\u001a\u00020\u00018\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0002\u0010sR\u0014\u0010\u0004\u001a\u00020\u00038\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0004\u0010tR\u0014\u0010\u0006\u001a\u00020\u00058\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0006\u0010uR\u0014\u0010\b\u001a\u00020\u00078\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\b\u0010vR\u0016\u0010w\u001a\u00020\u000b8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bw\u0010xR\u0016\u0010y\u001a\u00020\u000b8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\by\u0010xR\u0016\u0010z\u001a\u00020\u000b8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bz\u0010xR\u0016\u0010{\u001a\u0002058\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b{\u0010|R\u0016\u0010}\u001a\u00020\u000b8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b}\u0010xR\u0016\u0010~\u001a\u00020\u000b8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b~\u0010xR\u001b\u0010\u0080\u0001\u001a\u0004\u0018\u00010\u007f8\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\b\u0080\u0001\u0010\u0081\u0001¨\u0006\u0083\u0001"}, d2 = {"Lcom/arflix/tv/ui/screens/player/SubtitleOffsetRenderer;", "Landroidx/media3/exoplayer/Renderer;", "baseRenderer", "Lcom/arflix/tv/ui/screens/player/SubtitleTranslationManager;", "translationManager", "Lka/k0;", "translationScope", "Ljava/util/concurrent/atomic/AtomicLong;", "syncOffsetUs", "<init>", "(Landroidx/media3/exoplayer/Renderer;Lcom/arflix/tv/ui/screens/player/SubtitleTranslationManager;Lka/k0;Ljava/util/concurrent/atomic/AtomicLong;)V", "", "positionUs", "elapsedRealtimeUs", "Lx6/t0;", "render", "(JJ)V", "triggerPreTranslation", "()V", "", "", "extractAllCueTexts", "()Ljava/util/List;", "", "maxCount", "Lx6/x;", "extractBufferedIntervals", "(I)Ljava/util/List;", "Lkotlin/jvm/internal/EnhancedNullability;", "getName", "()Ljava/lang/String;", "getTrackType", "()I", "Landroidx/media3/exoplayer/RendererCapabilities;", "getCapabilities", "()Landroidx/media3/exoplayer/RendererCapabilities;", "p0", "Landroidx/media3/exoplayer/analytics/PlayerId;", "p1", "Landroidx/media3/common/util/Clock;", "p2", "init", "(ILandroidx/media3/exoplayer/analytics/PlayerId;Landroidx/media3/common/util/Clock;)V", "Landroidx/media3/exoplayer/MediaClock;", "getMediaClock", "()Landroidx/media3/exoplayer/MediaClock;", "getState", "Landroidx/media3/exoplayer/RendererConfiguration;", "", "Landroidx/media3/common/Format;", "kotlin.jvm.PlatformType", "Landroidx/media3/exoplayer/source/SampleStream;", "p3", "", "p4", "p5", "p6", "p7", "Landroidx/media3/exoplayer/source/MediaSource$MediaPeriodId;", "p8", "enable", "(Landroidx/media3/exoplayer/RendererConfiguration;[Landroidx/media3/common/Format;Landroidx/media3/exoplayer/source/SampleStream;JZZJJLandroidx/media3/exoplayer/source/MediaSource$MediaPeriodId;)V", TtmlNode.START, "replaceStream", "([Landroidx/media3/common/Format;Landroidx/media3/exoplayer/source/SampleStream;JJLandroidx/media3/exoplayer/source/MediaSource$MediaPeriodId;)V", "getStream", "()Landroidx/media3/exoplayer/source/SampleStream;", "hasReadStreamToEnd", "()Z", "getReadingPositionUs", "()J", "setCurrentStreamFinal", "isCurrentStreamFinal", "maybeThrowStreamError", "resetPosition", "(JZ)V", "Landroidx/media3/common/Timeline;", "setTimeline", "(Landroidx/media3/common/Timeline;)V", "isReady", "isEnded", "stop", "disable", "reset", "", "handleMessage", "(ILjava/lang/Object;)V", "tryPeriodicLookahead", "texts", "launchPreTranslation", "(Ljava/util/List;)V", "readStreamOffsetUs", "obj", "intervalFromCueWrapper", "(Ljava/lang/Object;)Lx6/x;", "v", "", "removeHI", "extractFromCollectionOrMap", "(Ljava/lang/Object;Ljava/util/Set;Z)I", "extractCueGroupTexts", "(Ljava/lang/Object;Ljava/util/Set;Z)Z", "Landroidx/media3/common/text/Cue;", "cues", "joinCues", "(Ljava/util/List;Z)Ljava/lang/String;", "text", "stripHI", "(Ljava/lang/String;)Ljava/lang/String;", "Ljava/lang/Class;", "startClass", ContentDisposition.Parameters.Name, "Ljava/lang/reflect/Field;", "findField", "(Ljava/lang/Class;Ljava/lang/String;)Ljava/lang/reflect/Field;", "Landroidx/media3/exoplayer/Renderer;", "Lcom/arflix/tv/ui/screens/player/SubtitleTranslationManager;", "Lka/k0;", "Ljava/util/concurrent/atomic/AtomicLong;", "preTranslatedUpToUs", "J", "currentPositionUs", "lastLookaheadMs", "pendingSeek", "Z", "lastSeekRetryMs", "lastRenderPositionUs", "Lka/v1;", "lookaheadJob", "Lka/v1;", "Companion", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
final class SubtitleOffsetRenderer implements Renderer {
    private static final long PREFETCH_TRIGGER_US = 30000000;
    private static final int WINDOW_CUES = 80;
    private static final long WINDOW_US = 120000000;
    private final Renderer baseRenderer;
    private long currentPositionUs;
    private volatile long lastLookaheadMs;
    private long lastRenderPositionUs;
    private long lastSeekRetryMs;
    private ka.v1 lookaheadJob;
    private volatile boolean pendingSeek;
    private volatile long preTranslatedUpToUs;
    private final AtomicLong syncOffsetUs;
    private final SubtitleTranslationManager translationManager;
    private final ka.k0 translationScope;

    /* JADX INFO: renamed from: com.arflix.tv.ui.screens.player.SubtitleOffsetRenderer$launchPreTranslation$1, reason: invalid class name */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
    @f7.e(c = "com.arflix.tv.ui.screens.player.SubtitleOffsetRenderer$launchPreTranslation$1", f = "AiSubtitleRenderersFactory.kt", l = {406}, m = "invokeSuspend", v = 2)
    public static final class AnonymousClass1 extends f7.j implements r7.p<ka.k0, d7.d<? super x6.t0>, Object> {
        final /* synthetic */ List<String> $texts;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(List<String> list, d7.d<? super AnonymousClass1> dVar) {
            super(2, dVar);
            this.$texts = list;
        }

        @Override // f7.a
        public final d7.d<x6.t0> create(Object obj, d7.d<?> dVar) {
            return SubtitleOffsetRenderer.this.new AnonymousClass1(this.$texts, dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            int i10 = this.label;
            if (i10 == 0) {
                k2.c.G(obj);
                SubtitleTranslationManager subtitleTranslationManager = SubtitleOffsetRenderer.this.translationManager;
                List<String> list = this.$texts;
                this.label = 1;
                Object objPreTranslateWindow = subtitleTranslationManager.preTranslateWindow(list, this);
                e7.a aVar = e7.a.f15033i;
                if (objPreTranslateWindow == aVar) {
                    return aVar;
                }
            } else {
                if (i10 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                k2.c.G(obj);
            }
            SubtitleOffsetRenderer.this.lastLookaheadMs = System.currentTimeMillis() - 2000;
            return x6.t0.f22605a;
        }

        @Override // r7.p
        public final Object invoke(ka.k0 k0Var, d7.d<? super x6.t0> dVar) {
            return ((AnonymousClass1) create(k0Var, dVar)).invokeSuspend(x6.t0.f22605a);
        }
    }

    public SubtitleOffsetRenderer(Renderer renderer, SubtitleTranslationManager subtitleTranslationManager, ka.k0 k0Var, AtomicLong atomicLong) {
        this.baseRenderer = renderer;
        this.translationManager = subtitleTranslationManager;
        this.translationScope = k0Var;
        this.syncOffsetUs = atomicLong;
        this.preTranslatedUpToUs = Long.MIN_VALUE;
        this.lastRenderPositionUs = Long.MIN_VALUE;
    }

    private static final void extractAllCueTexts$extractFromSubtitleField(SubtitleOffsetRenderer subtitleOffsetRenderer, boolean z, Set<String> set, String str) {
        Object obj;
        try {
            Field fieldFindField = subtitleOffsetRenderer.findField(subtitleOffsetRenderer.baseRenderer.getClass(), str);
            if (fieldFindField != null && (obj = fieldFindField.get(subtitleOffsetRenderer.baseRenderer)) != null) {
                Method method = obj.getClass().getMethod("getEventTimeCount", null);
                Method method2 = obj.getClass().getMethod("getEventTime", Integer.TYPE);
                Method method3 = obj.getClass().getMethod("getCues", Long.TYPE);
                int iIntValue = ((Integer) method.invoke(obj, null)).intValue();
                for (int i10 = 0; i10 < iIntValue; i10++) {
                    Object objInvoke = method3.invoke(obj, (Long) method2.invoke(obj, Integer.valueOf(i10)));
                    List<Cue> list = objInvoke instanceof List ? (List) objInvoke : null;
                    if (list != null) {
                        String strJoinCues = subtitleOffsetRenderer.joinCues(list, z);
                        if (!kotlin.text.o.h0(strJoinCues)) {
                            set.add(strJoinCues);
                        }
                    }
                }
            }
        } catch (Exception unused) {
        }
    }

    private final boolean extractCueGroupTexts(Object obj, Set<String> texts, boolean removeHI) {
        if (obj == null) {
            return false;
        }
        if (obj instanceof CueGroup) {
            String strJoinCues = joinCues(((CueGroup) obj).cues, removeHI);
            if (!kotlin.text.o.h0(strJoinCues)) {
                texts.add(strJoinCues);
            }
            return true;
        }
        if (obj instanceof List) {
            ArrayList arrayList = new ArrayList();
            for (Object obj2 : (Iterable) obj) {
                if (obj2 instanceof Cue) {
                    arrayList.add(obj2);
                }
            }
            String strJoinCues2 = joinCues(arrayList, removeHI);
            if (!kotlin.text.o.h0(strJoinCues2)) {
                texts.add(strJoinCues2);
            }
            return !((Collection) obj).isEmpty();
        }
        try {
            Field fieldFindField = findField(obj.getClass(), "cues");
            Object obj3 = fieldFindField != null ? fieldFindField.get(obj) : null;
            if (obj3 instanceof List) {
                ArrayList arrayList2 = new ArrayList();
                for (Object obj4 : (Iterable) obj3) {
                    if (obj4 instanceof Cue) {
                        arrayList2.add(obj4);
                    }
                }
                String strJoinCues3 = joinCues(arrayList2, removeHI);
                if (!kotlin.text.o.h0(strJoinCues3)) {
                    texts.add(strJoinCues3);
                }
                return !((Collection) obj3).isEmpty();
            }
        } catch (Exception unused) {
        }
        return false;
    }

    private final int extractFromCollectionOrMap(Object v2, Set<String> texts, boolean removeHI) {
        int i10 = 0;
        if (v2 instanceof Map) {
            Iterator it = ((Map) v2).values().iterator();
            while (it.hasNext()) {
                if (extractCueGroupTexts(it.next(), texts, removeHI)) {
                    i10++;
                }
            }
            return i10;
        }
        if (v2 instanceof Collection) {
            Iterator it2 = ((Iterable) v2).iterator();
            while (it2.hasNext()) {
                if (extractCueGroupTexts(it2.next(), texts, removeHI)) {
                    i10++;
                }
            }
        }
        return i10;
    }

    private final Field findField(Class<?> startClass, String name) {
        while (startClass != null && !startClass.equals(Object.class)) {
            try {
                Field declaredField = startClass.getDeclaredField(name);
                declaredField.setAccessible(true);
                return declaredField;
            } catch (NoSuchFieldException unused) {
                startClass = startClass.getSuperclass();
            }
        }
        return null;
    }

    private final x6.x intervalFromCueWrapper(Object obj) {
        Collection collection;
        ArrayList arrayList;
        Object c0Var;
        long jLongValue;
        Object c0Var2;
        String string;
        boolean z = obj instanceof CueGroup;
        if (z) {
            collection = ((CueGroup) obj).cues;
        } else {
            if (obj instanceof List) {
                arrayList = new ArrayList();
                for (Object obj2 : (Iterable) obj) {
                    if (obj2 instanceof Cue) {
                        arrayList.add(obj2);
                    }
                }
            } else {
                Field fieldFindField = findField(obj.getClass(), "cues");
                Object obj3 = fieldFindField != null ? fieldFindField.get(obj) : null;
                List list = obj3 instanceof List ? (List) obj3 : null;
                if (list != null) {
                    arrayList = new ArrayList();
                    for (Object obj4 : list) {
                        if (obj4 instanceof Cue) {
                            arrayList.add(obj4);
                        }
                    }
                } else {
                    collection = null;
                }
            }
            collection = arrayList;
        }
        if (collection == null || collection.isEmpty() || collection.isEmpty()) {
            return null;
        }
        Iterator it = collection.iterator();
        while (it.hasNext()) {
            CharSequence charSequence = ((Cue) it.next()).text;
            String string2 = (charSequence == null || (string = charSequence.toString()) == null) ? null : kotlin.text.o.L0(string).toString();
            if (string2 != null && !kotlin.text.o.h0(string2)) {
                long jLongValue2 = SilenceSkippingAudioProcessor.DEFAULT_MAX_SILENCE_TO_KEEP_DURATION_US;
                if (z) {
                    jLongValue = ((CueGroup) obj).presentationTimeUs;
                } else {
                    try {
                        Field fieldFindField2 = findField(obj.getClass(), "startTimeUs");
                        c0Var = fieldFindField2 != null ? Long.valueOf(fieldFindField2.getLong(obj)) : null;
                    } catch (Throwable th) {
                        c0Var = new x6.c0(th);
                    }
                    if (c0Var instanceof x6.c0) {
                        c0Var = null;
                    }
                    Long l10 = (Long) c0Var;
                    if (l10 == null) {
                        return null;
                    }
                    jLongValue = l10.longValue();
                    try {
                        Field fieldFindField3 = findField(obj.getClass(), "durationUs");
                        c0Var2 = fieldFindField3 != null ? Long.valueOf(fieldFindField3.getLong(obj)) : null;
                    } catch (Throwable th2) {
                        c0Var2 = new x6.c0(th2);
                    }
                    if (c0Var2 instanceof x6.c0) {
                        c0Var2 = null;
                    }
                    Long l11 = (Long) c0Var2;
                    if (l11 != null) {
                        if (l11.longValue() <= 0) {
                            l11 = null;
                        }
                        if (l11 != null) {
                            jLongValue2 = l11.longValue();
                        }
                    }
                }
                long j10 = jLongValue2 + jLongValue;
                if (j10 <= jLongValue) {
                    return null;
                }
                return new x6.x(Long.valueOf(jLongValue / 1000), Long.valueOf(j10 / 1000));
            }
        }
        return null;
    }

    private final String joinCues(List<Cue> cues, boolean removeHI) {
        String string;
        ArrayList arrayList = new ArrayList();
        Iterator<T> it = cues.iterator();
        while (it.hasNext()) {
            CharSequence charSequence = ((Cue) it.next()).text;
            String string2 = (charSequence == null || (string = charSequence.toString()) == null) ? null : kotlin.text.o.L0(string).toString();
            if (string2 != null) {
                arrayList.add(string2);
            }
        }
        ArrayList arrayList2 = new ArrayList();
        for (Object obj : arrayList) {
            if (!kotlin.text.o.h0((String) obj)) {
                arrayList2.add(obj);
            }
        }
        String strU0 = kotlin.collections.x.u0(arrayList2, "\n", null, null, null, 62);
        return removeHI ? stripHI(strU0) : strU0;
    }

    private final void launchPreTranslation(List<String> texts) {
        this.lookaheadJob = ka.m0.p(this.translationScope, null, 0, new AnonymousClass1(texts, null), 3);
    }

    private final long readStreamOffsetUs() {
        Object c0Var;
        Iterator it = t7.a.E("streamOffsetUs", "outputStreamOffsetUs").iterator();
        while (it.hasNext()) {
            try {
                Field fieldFindField = findField(this.baseRenderer.getClass(), (String) it.next());
                c0Var = fieldFindField != null ? Long.valueOf(fieldFindField.getLong(this.baseRenderer)) : null;
            } catch (Throwable th) {
                c0Var = new x6.c0(th);
            }
            Long l10 = (Long) (c0Var instanceof x6.c0 ? null : c0Var);
            if (l10 != null && l10.longValue() > 0) {
                return l10.longValue();
            }
        }
        return 0L;
    }

    private final String stripHI(String text) {
        AiSubtitleRegexes aiSubtitleRegexes = AiSubtitleRegexes.INSTANCE;
        return com.arflix.tv.data.repository.g.o(aiSubtitleRegexes.getMUSIC_REGEX(), aiSubtitleRegexes.getBRACKET_REGEX().f("", text), "");
    }

    private final void tryPeriodicLookahead() {
        if (this.translationManager.getIsEnabled()) {
            long jCurrentTimeMillis = System.currentTimeMillis();
            boolean z = this.pendingSeek;
            if ((z || jCurrentTimeMillis - this.lastLookaheadMs >= 5000) && jCurrentTimeMillis - this.lastSeekRetryMs >= 300) {
                this.lastSeekRetryMs = jCurrentTimeMillis;
                List<String> listExtractAllCueTexts = extractAllCueTexts();
                if (listExtractAllCueTexts.isEmpty()) {
                    return;
                }
                this.lastLookaheadMs = jCurrentTimeMillis;
                if (z) {
                    this.pendingSeek = false;
                }
                ArrayList arrayList = new ArrayList();
                for (Object obj : listExtractAllCueTexts) {
                    if (this.translationManager.getCached((String) obj) == null) {
                        arrayList.add(obj);
                    }
                }
                List<String> listX0 = kotlin.collections.x.X0(arrayList, 80);
                if (listX0.isEmpty()) {
                    return;
                }
                launchPreTranslation(listX0);
            }
        }
    }

    @Override // androidx.media3.exoplayer.Renderer
    public void disable() {
        this.baseRenderer.disable();
    }

    @Override // androidx.media3.exoplayer.Renderer
    public void enable(RendererConfiguration p02, Format[] p12, SampleStream p22, long p32, boolean p42, boolean p52, long p62, long p72, MediaSource.MediaPeriodId p82) throws ExoPlaybackException {
        this.baseRenderer.enable(p02, p12, p22, p32, p42, p52, p62, p72, p82);
    }

    @Override // androidx.media3.exoplayer.Renderer
    public final /* synthetic */ void enableMayRenderStartOfStream() {
        androidx.media3.exoplayer.s0.a(this);
    }

    /* JADX WARN: Code restructure failed: missing block: B:20:0x005e, code lost:
    
        r3 = r2.getClass();
     */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x0062, code lost:
    
        if (r3 == null) goto L57;
     */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x006a, code lost:
    
        if (r3.equals(java.lang.Object.class) != false) goto L58;
     */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x006c, code lost:
    
        r4 = r3.getDeclaredFields();
        r5 = r4.length;
        r6 = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x0072, code lost:
    
        if (r6 >= r5) goto L59;
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x0074, code lost:
    
        r7 = r4[r6];
     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x0077, code lost:
    
        r7.setAccessible(true);
        r7 = r7.get(r2);
     */
    /* JADX WARN: Code restructure failed: missing block: B:29:0x007e, code lost:
    
        if (r7 != null) goto L31;
     */
    /* JADX WARN: Code restructure failed: missing block: B:31:0x0081, code lost:
    
        extractFromCollectionOrMap(r7, r1, r0);
     */
    /* JADX WARN: Code restructure failed: missing block: B:33:0x0087, code lost:
    
        r3 = r3.getSuperclass();
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.util.List<java.lang.String> extractAllCueTexts() {
        /*
            r9 = this;
            com.arflix.tv.ui.screens.player.SubtitleTranslationManager r0 = r9.translationManager
            boolean r0 = r0.getRemoveHearingImpaired()
            java.util.LinkedHashSet r1 = new java.util.LinkedHashSet
            r1.<init>()
            androidx.media3.exoplayer.Renderer r2 = r9.baseRenderer     // Catch: java.lang.Exception -> L8c
            java.lang.Class r2 = r2.getClass()     // Catch: java.lang.Exception -> L8c
            java.lang.String r3 = "cuesResolver"
            java.lang.reflect.Field r2 = r9.findField(r2, r3)     // Catch: java.lang.Exception -> L8c
            if (r2 == 0) goto L20
            androidx.media3.exoplayer.Renderer r3 = r9.baseRenderer     // Catch: java.lang.Exception -> L8c
            java.lang.Object r2 = r2.get(r3)     // Catch: java.lang.Exception -> L8c
            goto L21
        L20:
            r2 = 0
        L21:
            if (r2 == 0) goto L8c
            java.lang.String r3 = "cuesWithTimingList"
            java.lang.String r4 = "cueGroupsByStartTime"
            java.lang.String r5 = "cueGroups"
            java.lang.String r6 = "cueGroupList"
            java.lang.String r7 = "groups"
            java.lang.String[] r3 = new java.lang.String[]{r3, r4, r5, r6, r7}     // Catch: java.lang.Exception -> L8c
            java.util.List r3 = t7.a.E(r3)     // Catch: java.lang.Exception -> L8c
            java.util.Iterator r3 = r3.iterator()     // Catch: java.lang.Exception -> L8c
        L39:
            boolean r4 = r3.hasNext()     // Catch: java.lang.Exception -> L8c
            if (r4 == 0) goto L5e
            java.lang.Object r4 = r3.next()     // Catch: java.lang.Exception -> L8c
            java.lang.String r4 = (java.lang.String) r4     // Catch: java.lang.Exception -> L8c
            java.lang.Class r5 = r2.getClass()     // Catch: java.lang.Exception -> L8c
            java.lang.reflect.Field r4 = r9.findField(r5, r4)     // Catch: java.lang.Exception -> L8c
            if (r4 != 0) goto L50
            goto L39
        L50:
            java.lang.Object r4 = r4.get(r2)     // Catch: java.lang.Exception -> L8c
            if (r4 != 0) goto L57
            goto L39
        L57:
            int r4 = r9.extractFromCollectionOrMap(r4, r1, r0)     // Catch: java.lang.Exception -> L8c
            if (r4 <= 0) goto L39
            goto L8c
        L5e:
            java.lang.Class r3 = r2.getClass()     // Catch: java.lang.Exception -> L8c
        L62:
            if (r3 == 0) goto L8c
            java.lang.Class<java.lang.Object> r4 = java.lang.Object.class
            boolean r4 = r3.equals(r4)     // Catch: java.lang.Exception -> L8c
            if (r4 != 0) goto L8c
            java.lang.reflect.Field[] r4 = r3.getDeclaredFields()     // Catch: java.lang.Exception -> L8c
            int r5 = r4.length     // Catch: java.lang.Exception -> L8c
            r6 = 0
        L72:
            if (r6 >= r5) goto L87
            r7 = r4[r6]     // Catch: java.lang.Exception -> L8c
            r8 = 1
            r7.setAccessible(r8)     // Catch: java.lang.Exception -> L84
            java.lang.Object r7 = r7.get(r2)     // Catch: java.lang.Exception -> L84
            if (r7 != 0) goto L81
            goto L84
        L81:
            r9.extractFromCollectionOrMap(r7, r1, r0)     // Catch: java.lang.Exception -> L84
        L84:
            int r6 = r6 + 1
            goto L72
        L87:
            java.lang.Class r3 = r3.getSuperclass()     // Catch: java.lang.Exception -> L8c
            goto L62
        L8c:
            boolean r2 = r1.isEmpty()
            if (r2 != 0) goto L97
            java.util.List r0 = kotlin.collections.x.c1(r1)
            return r0
        L97:
            java.lang.String r2 = "subtitle"
            extractAllCueTexts$extractFromSubtitleField(r9, r0, r1, r2)
            java.lang.String r2 = "nextSubtitle"
            extractAllCueTexts$extractFromSubtitleField(r9, r0, r1, r2)
            java.util.List r0 = kotlin.collections.x.c1(r1)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.screens.player.SubtitleOffsetRenderer.extractAllCueTexts():java.util.List");
    }

    public final List<x6.x> extractBufferedIntervals(int maxCount) {
        Collection collectionValues;
        x6.x xVarIntervalFromCueWrapper;
        Object obj;
        Collection collectionValues2;
        x6.x xVarIntervalFromCueWrapper2;
        ArrayList arrayList = new ArrayList();
        long streamOffsetUs = readStreamOffsetUs() / 1000;
        try {
            Field fieldFindField = findField(this.baseRenderer.getClass(), "cuesResolver");
            Object obj2 = fieldFindField != null ? fieldFindField.get(this.baseRenderer) : null;
            if (obj2 != null) {
                Iterator it = t7.a.E("cuesWithTimingList", "cuesWithTimings", "cueGroupsByStartTime", "cueGroups", "cueGroupList", "groups").iterator();
                while (it.hasNext()) {
                    Field fieldFindField2 = findField(obj2.getClass(), (String) it.next());
                    if (fieldFindField2 != null && (obj = fieldFindField2.get(obj2)) != null) {
                        if (obj instanceof Map) {
                            collectionValues2 = ((Map) obj).values();
                        } else if (obj instanceof Collection) {
                            collectionValues2 = (Collection) obj;
                        } else {
                            continue;
                        }
                        for (Object obj3 : collectionValues2) {
                            if (obj3 != null && (xVarIntervalFromCueWrapper2 = intervalFromCueWrapper(obj3)) != null) {
                                arrayList.add(new x6.x(Long.valueOf(((Number) xVarIntervalFromCueWrapper2.f22608i).longValue() - streamOffsetUs), Long.valueOf(((Number) xVarIntervalFromCueWrapper2.f22609l).longValue() - streamOffsetUs)));
                            }
                        }
                        if (!arrayList.isEmpty()) {
                            break;
                        }
                    }
                }
                if (arrayList.isEmpty()) {
                    loop2: for (Class<?> superclass = obj2.getClass(); superclass != null; superclass = superclass.getSuperclass()) {
                        if (superclass.equals(Object.class)) {
                            break;
                        }
                        for (Field field : superclass.getDeclaredFields()) {
                            try {
                                field.setAccessible(true);
                                Object obj4 = field.get(obj2);
                                if (obj4 != null) {
                                    if (obj4 instanceof Map) {
                                        collectionValues = ((Map) obj4).values();
                                    } else if (obj4 instanceof Collection) {
                                        collectionValues = (Collection) obj4;
                                    } else {
                                        continue;
                                    }
                                    for (Object obj5 : collectionValues) {
                                        if (obj5 != null && (xVarIntervalFromCueWrapper = intervalFromCueWrapper(obj5)) != null) {
                                            arrayList.add(new x6.x(Long.valueOf(((Number) xVarIntervalFromCueWrapper.f22608i).longValue() - streamOffsetUs), Long.valueOf(((Number) xVarIntervalFromCueWrapper.f22609l).longValue() - streamOffsetUs)));
                                        }
                                    }
                                    if (!arrayList.isEmpty()) {
                                        break loop2;
                                    }
                                }
                            } catch (Exception unused) {
                            }
                        }
                    }
                }
            }
        } catch (Exception unused2) {
        }
        ArrayList arrayList2 = new ArrayList();
        for (Object obj6 : arrayList) {
            if (((Number) ((x6.x) obj6).f22608i).longValue() >= 0) {
                arrayList2.add(obj6);
            }
        }
        return kotlin.collections.x.X0(kotlin.collections.x.W0(kotlin.collections.x.g0(arrayList2), new Comparator() { // from class: com.arflix.tv.ui.screens.player.SubtitleOffsetRenderer$extractBufferedIntervals$$inlined$sortedBy$1
            /* JADX WARN: Multi-variable type inference failed */
            @Override // java.util.Comparator
            public final int compare(T t2, T t10) {
                return com.google.common.util.concurrent.r0.e((Long) ((x6.x) t2).f22608i, (Long) ((x6.x) t10).f22608i);
            }
        }), maxCount);
    }

    @Override // androidx.media3.exoplayer.Renderer
    public RendererCapabilities getCapabilities() {
        return this.baseRenderer.getCapabilities();
    }

    @Override // androidx.media3.exoplayer.Renderer
    public final /* synthetic */ long getDurationToProgressUs(long j10, long j11) {
        return androidx.media3.exoplayer.s0.b(this, j10, j11);
    }

    @Override // androidx.media3.exoplayer.Renderer
    public MediaClock getMediaClock() {
        return this.baseRenderer.getMediaClock();
    }

    @Override // androidx.media3.exoplayer.Renderer
    public String getName() {
        return this.baseRenderer.getName();
    }

    @Override // androidx.media3.exoplayer.Renderer
    public long getReadingPositionUs() {
        return this.baseRenderer.getReadingPositionUs();
    }

    @Override // androidx.media3.exoplayer.Renderer
    public int getState() {
        return this.baseRenderer.getState();
    }

    @Override // androidx.media3.exoplayer.Renderer
    public SampleStream getStream() {
        return this.baseRenderer.getStream();
    }

    @Override // androidx.media3.exoplayer.Renderer, androidx.media3.exoplayer.RendererCapabilities
    public int getTrackType() {
        return this.baseRenderer.getTrackType();
    }

    @Override // androidx.media3.exoplayer.PlayerMessage.Target
    public void handleMessage(int p02, Object p12) throws ExoPlaybackException {
        this.baseRenderer.handleMessage(p02, p12);
    }

    @Override // androidx.media3.exoplayer.Renderer
    public boolean hasReadStreamToEnd() {
        return this.baseRenderer.hasReadStreamToEnd();
    }

    @Override // androidx.media3.exoplayer.Renderer
    public void init(int p02, PlayerId p12, Clock p22) {
        this.baseRenderer.init(p02, p12, p22);
    }

    @Override // androidx.media3.exoplayer.Renderer
    public boolean isCurrentStreamFinal() {
        return this.baseRenderer.isCurrentStreamFinal();
    }

    @Override // androidx.media3.exoplayer.Renderer
    public boolean isEnded() {
        return this.baseRenderer.isEnded();
    }

    @Override // androidx.media3.exoplayer.Renderer
    public boolean isReady() {
        return this.baseRenderer.isReady();
    }

    @Override // androidx.media3.exoplayer.Renderer
    public void maybeThrowStreamError() throws IOException {
        this.baseRenderer.maybeThrowStreamError();
    }

    @Override // androidx.media3.exoplayer.Renderer
    public final /* synthetic */ void release() {
        androidx.media3.exoplayer.s0.c(this);
    }

    @Override // androidx.media3.exoplayer.Renderer
    public void render(long positionUs, long elapsedRealtimeUs) throws ExoPlaybackException {
        long j10;
        this.currentPositionUs = positionUs;
        long j11 = this.lastRenderPositionUs;
        long j12 = this.syncOffsetUs.get();
        if (j12 != 0) {
            j10 = positionUs - j12;
            if (j10 < 0) {
                j10 = 0;
            }
        } else {
            j10 = positionUs;
        }
        this.baseRenderer.render(j10, elapsedRealtimeUs);
        if (j11 != Long.MIN_VALUE && Math.abs(positionUs - j11) > DashMediaSource.MIN_LIVE_DEFAULT_START_POSITION_US) {
            this.preTranslatedUpToUs = positionUs;
            this.lastLookaheadMs = 0L;
            this.lastSeekRetryMs = 0L;
            this.pendingSeek = true;
            ka.v1 v1Var = this.lookaheadJob;
            if (v1Var != null) {
                v1Var.cancel((CancellationException) null);
            }
            this.lookaheadJob = null;
        }
        this.lastRenderPositionUs = positionUs;
        tryPeriodicLookahead();
    }

    @Override // androidx.media3.exoplayer.Renderer
    public void replaceStream(Format[] p02, SampleStream p12, long p22, long p32, MediaSource.MediaPeriodId p42) throws ExoPlaybackException {
        this.baseRenderer.replaceStream(p02, p12, p22, p32, p42);
    }

    @Override // androidx.media3.exoplayer.Renderer
    public void reset() {
        this.baseRenderer.reset();
    }

    @Override // androidx.media3.exoplayer.Renderer
    public void resetPosition(long p02, boolean p12) throws ExoPlaybackException {
        this.baseRenderer.resetPosition(p02, p12);
    }

    @Override // androidx.media3.exoplayer.Renderer
    public void setCurrentStreamFinal() {
        this.baseRenderer.setCurrentStreamFinal();
    }

    @Override // androidx.media3.exoplayer.Renderer
    public final /* synthetic */ void setPlaybackSpeed(float f10, float f11) {
        androidx.media3.exoplayer.s0.d(this, f10, f11);
    }

    @Override // androidx.media3.exoplayer.Renderer
    public void setTimeline(Timeline p02) {
        this.baseRenderer.setTimeline(p02);
    }

    @Override // androidx.media3.exoplayer.Renderer
    public void start() throws ExoPlaybackException {
        this.baseRenderer.start();
    }

    @Override // androidx.media3.exoplayer.Renderer
    public void stop() {
        this.baseRenderer.stop();
    }

    @Override // androidx.media3.exoplayer.Renderer
    public final /* synthetic */ boolean supportsResetPositionWithoutKeyFrameReset(long j10) {
        return androidx.media3.exoplayer.s0.e(this, j10);
    }

    public final void triggerPreTranslation() {
        if (this.translationManager.getIsEnabled()) {
            if (this.preTranslatedUpToUs == Long.MIN_VALUE || this.preTranslatedUpToUs <= this.currentPositionUs + PREFETCH_TRIGGER_US) {
                List<String> listExtractAllCueTexts = extractAllCueTexts();
                if (listExtractAllCueTexts.isEmpty()) {
                    return;
                }
                ArrayList arrayList = new ArrayList();
                for (Object obj : listExtractAllCueTexts) {
                    if (this.translationManager.getCached((String) obj) == null) {
                        arrayList.add(obj);
                    }
                }
                List<String> listX0 = kotlin.collections.x.X0(arrayList, 80);
                if (listX0.isEmpty()) {
                    return;
                }
                this.preTranslatedUpToUs = this.currentPositionUs + WINDOW_US;
                this.lastLookaheadMs = System.currentTimeMillis();
                launchPreTranslation(listX0);
            }
        }
    }

    public /* synthetic */ SubtitleOffsetRenderer(Renderer renderer, SubtitleTranslationManager subtitleTranslationManager, ka.k0 k0Var, AtomicLong atomicLong, int i10, kotlin.jvm.internal.h hVar) {
        this(renderer, subtitleTranslationManager, k0Var, (i10 & 8) != 0 ? new AtomicLong(0L) : atomicLong);
    }
}
