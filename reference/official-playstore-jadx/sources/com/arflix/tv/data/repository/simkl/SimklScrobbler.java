package com.arflix.tv.data.repository.simkl;

import androidx.compose.foundation.c;
import androidx.compose.ui.graphics.colorspace.ColorSpace;
import androidx.media3.container.MdtaMetadataEntry;
import androidx.media3.extractor.text.ttml.TtmlNode;
import androidx.media3.extractor.ts.TsExtractor;
import com.arflix.tv.data.api.SimklApi;
import com.arflix.tv.data.api.SimklEpisodeRef;
import com.arflix.tv.data.api.SimklIds;
import com.arflix.tv.data.api.SimklMovieRef;
import com.arflix.tv.data.api.SimklScrobbleBody;
import com.arflix.tv.data.api.SimklShowRef;
import com.arflix.tv.data.model.MediaType;
import com.arflix.tv.util.Constants;
import d7.d;
import d7.h;
import g7.b;
import javax.inject.Inject;
import javax.inject.Singleton;
import ka.k0;
import ka.l0;
import ka.m0;
import ka.v1;
import ka.x0;
import kotlin.Metadata;
import kotlin.jvm.internal.p;
import ua.e;

/* JADX INFO: loaded from: classes3.dex */
@Singleton
@Metadata(d1 = {"\u0000x\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0002\b\u0007\b\u0007\u0018\u0000 >2\u00020\u0001:\u0003?@>B\u0019\b\u0007\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u0006\u0010\u0007J\u0017\u0010\n\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\bH\u0002¢\u0006\u0004\b\n\u0010\u000bJ\u0018\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\r\u001a\u00020\fH\u0082@¢\u0006\u0004\b\u000f\u0010\u0010J\u0018\u0010\u0011\u001a\u00020\u000e2\u0006\u0010\r\u001a\u00020\fH\u0082@¢\u0006\u0004\b\u0011\u0010\u0010JC\u0010\u001b\u001a\u00020\u001a2\u0006\u0010\u0013\u001a\u00020\u00122\u0006\u0010\u0015\u001a\u00020\u00142\u0006\u0010\t\u001a\u00020\b2\b\u0010\u0016\u001a\u0004\u0018\u00010\u00142\b\u0010\u0017\u001a\u0004\u0018\u00010\u00142\u0006\u0010\u0019\u001a\u00020\u0018H\u0002¢\u0006\u0004\b\u001b\u0010\u001cJJ\u0010\u001d\u001a\u00020\u000e2\u0006\u0010\u0013\u001a\u00020\u00122\u0006\u0010\u0015\u001a\u00020\u00142\u0006\u0010\t\u001a\u00020\b2\n\b\u0002\u0010\u0016\u001a\u0004\u0018\u00010\u00142\n\b\u0002\u0010\u0017\u001a\u0004\u0018\u00010\u00142\b\b\u0002\u0010\u0019\u001a\u00020\u0018H\u0086@¢\u0006\u0004\b\u001d\u0010\u001eJJ\u0010\u001f\u001a\u00020\u000e2\u0006\u0010\u0013\u001a\u00020\u00122\u0006\u0010\u0015\u001a\u00020\u00142\u0006\u0010\t\u001a\u00020\b2\n\b\u0002\u0010\u0016\u001a\u0004\u0018\u00010\u00142\n\b\u0002\u0010\u0017\u001a\u0004\u0018\u00010\u00142\b\b\u0002\u0010\u0019\u001a\u00020\u0018H\u0086@¢\u0006\u0004\b\u001f\u0010\u001eJJ\u0010 \u001a\u00020\u000e2\u0006\u0010\u0013\u001a\u00020\u00122\u0006\u0010\u0015\u001a\u00020\u00142\u0006\u0010\t\u001a\u00020\b2\n\b\u0002\u0010\u0016\u001a\u0004\u0018\u00010\u00142\n\b\u0002\u0010\u0017\u001a\u0004\u0018\u00010\u00142\b\b\u0002\u0010\u0019\u001a\u00020\u0018H\u0086@¢\u0006\u0004\b \u0010\u001eR\u0014\u0010\u0003\u001a\u00020\u00028\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0003\u0010!R\u0014\u0010\u0005\u001a\u00020\u00048\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0005\u0010\"R\u0014\u0010$\u001a\u00020#8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b$\u0010%R\u0014\u0010'\u001a\u00020&8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b'\u0010(R\u0016\u0010)\u001a\u00020\u00188\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b)\u0010*R\u0016\u0010,\u001a\u00020+8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b,\u0010-R\u0018\u0010.\u001a\u0004\u0018\u00010\f8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b.\u0010/R\u0018\u00101\u001a\u0004\u0018\u0001008\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b1\u00102R(\u00104\u001a\b\u0012\u0004\u0012\u00020+038\u0000@\u0000X\u0080\u000e¢\u0006\u0012\n\u0004\b4\u00105\u001a\u0004\b6\u00107\"\u0004\b8\u00109R\u0014\u0010=\u001a\u00020:8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b;\u0010<¨\u0006A"}, d2 = {"Lcom/arflix/tv/data/repository/simkl/SimklScrobbler;", "", "Lcom/arflix/tv/data/api/SimklApi;", "simklApi", "Lcom/arflix/tv/data/repository/simkl/SimklAuthManager;", "authManager", "<init>", "(Lcom/arflix/tv/data/api/SimklApi;Lcom/arflix/tv/data/repository/simkl/SimklAuthManager;)V", "", "progress", "normalizeProgress", "(F)F", "Lcom/arflix/tv/data/repository/simkl/SimklScrobbler$Command;", "command", "Lx6/t0;", "submit", "(Lcom/arflix/tv/data/repository/simkl/SimklScrobbler$Command;Ld7/d;)Ljava/lang/Object;", "execute", "Lcom/arflix/tv/data/model/MediaType;", "mediaType", "", "tmdbId", "season", "episode", "", "isAnime", "Lcom/arflix/tv/data/api/SimklScrobbleBody;", "buildScrobbleBody", "(Lcom/arflix/tv/data/model/MediaType;IFLjava/lang/Integer;Ljava/lang/Integer;Z)Lcom/arflix/tv/data/api/SimklScrobbleBody;", "scrobbleStart", "(Lcom/arflix/tv/data/model/MediaType;IFLjava/lang/Integer;Ljava/lang/Integer;ZLd7/d;)Ljava/lang/Object;", "scrobblePause", "scrobbleStop", "Lcom/arflix/tv/data/api/SimklApi;", "Lcom/arflix/tv/data/repository/simkl/SimklAuthManager;", "Lka/k0;", "queueScope", "Lka/k0;", "Lua/a;", "queueMutex", "Lua/a;", "hasWritten", "Z", "", "lastWriteAt", "J", "pendingCommand", "Lcom/arflix/tv/data/repository/simkl/SimklScrobbler$Command;", "Lka/v1;", "pendingJob", "Lka/v1;", "Lkotlin/Function0;", "elapsedRealtimeMs", "Lr7/a;", "getElapsedRealtimeMs$app_playRelease", "()Lr7/a;", "setElapsedRealtimeMs$app_playRelease", "(Lr7/a;)V", "", "getClientId", "()Ljava/lang/String;", "clientId", "Companion", "Action", "Command", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class SimklScrobbler {

    @Deprecated
    public static final long WRITE_LOCK_MS = 20500;
    private final SimklAuthManager authManager;
    private boolean hasWritten;
    private long lastWriteAt;
    private Command pendingCommand;
    private v1 pendingJob;
    private final SimklApi simklApi;
    private static final Companion Companion = new Companion(null);
    public static final int $stable = 8;
    private final k0 queueScope = l0.a(h.a(m0.d(), x0.f19655d));
    private final ua.a queueMutex = e.a();
    private r7.a<Long> elapsedRealtimeMs = new a(0);

    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0006\b\u0082\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006¨\u0006\u0007"}, d2 = {"Lcom/arflix/tv/data/repository/simkl/SimklScrobbler$Action;", "", "<init>", "(Ljava/lang/String;I)V", "START", "PAUSE", "STOP", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class Action {
        private static final /* synthetic */ g7.a $ENTRIES;
        private static final /* synthetic */ Action[] $VALUES;
        public static final Action START = new Action("START", 0);
        public static final Action PAUSE = new Action("PAUSE", 1);
        public static final Action STOP = new Action("STOP", 2);

        private static final /* synthetic */ Action[] $values() {
            return new Action[]{START, PAUSE, STOP};
        }

        static {
            Action[] actionArr$values = $values();
            $VALUES = actionArr$values;
            $ENTRIES = new b(actionArr$values);
        }

        private Action(String str, int i10) {
        }

        public static g7.a<Action> getEntries() {
            return $ENTRIES;
        }

        public static Action valueOf(String str) {
            return (Action) Enum.valueOf(Action.class, str);
        }

        public static Action[] values() {
            return (Action[]) $VALUES.clone();
        }
    }

    @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0010\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0082\b\u0018\u00002\u00020\u0001B'\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0004\b\n\u0010\u000bJ\t\u0010\u0014\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0015\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0016\u001a\u00020\u0007HÆ\u0003J\t\u0010\u0017\u001a\u00020\tHÆ\u0003J1\u0010\u0018\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\tHÆ\u0001J\u0014\u0010\u0019\u001a\u00020\u001a2\b\u0010\u001b\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u001c\u001a\u00020\tHÖ\u0081\u0004J\n\u0010\u001d\u001a\u00020\u0005HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013¨\u0006\u001e"}, d2 = {"Lcom/arflix/tv/data/repository/simkl/SimklScrobbler$Command;", "", "action", "Lcom/arflix/tv/data/repository/simkl/SimklScrobbler$Action;", "authHeader", "", TtmlNode.TAG_BODY, "Lcom/arflix/tv/data/api/SimklScrobbleBody;", "tmdbId", "", "<init>", "(Lcom/arflix/tv/data/repository/simkl/SimklScrobbler$Action;Ljava/lang/String;Lcom/arflix/tv/data/api/SimklScrobbleBody;I)V", "getAction", "()Lcom/arflix/tv/data/repository/simkl/SimklScrobbler$Action;", "getAuthHeader", "()Ljava/lang/String;", "getBody", "()Lcom/arflix/tv/data/api/SimklScrobbleBody;", "getTmdbId", "()I", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "hashCode", "toString", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final /* data */ class Command {
        private final Action action;
        private final String authHeader;
        private final SimklScrobbleBody body;
        private final int tmdbId;

        public Command(Action action, String str, SimklScrobbleBody simklScrobbleBody, int i10) {
            this.action = action;
            this.authHeader = str;
            this.body = simklScrobbleBody;
            this.tmdbId = i10;
        }

        public static /* synthetic */ Command copy$default(Command command, Action action, String str, SimklScrobbleBody simklScrobbleBody, int i10, int i11, Object obj) {
            if ((i11 & 1) != 0) {
                action = command.action;
            }
            if ((i11 & 2) != 0) {
                str = command.authHeader;
            }
            if ((i11 & 4) != 0) {
                simklScrobbleBody = command.body;
            }
            if ((i11 & 8) != 0) {
                i10 = command.tmdbId;
            }
            return command.copy(action, str, simklScrobbleBody, i10);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final Action getAction() {
            return this.action;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final String getAuthHeader() {
            return this.authHeader;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final SimklScrobbleBody getBody() {
            return this.body;
        }

        /* JADX INFO: renamed from: component4, reason: from getter */
        public final int getTmdbId() {
            return this.tmdbId;
        }

        public final Command copy(Action action, String authHeader, SimklScrobbleBody body, int tmdbId) {
            return new Command(action, authHeader, body, tmdbId);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Command)) {
                return false;
            }
            Command command = (Command) other;
            return this.action == command.action && p.a(this.authHeader, command.authHeader) && p.a(this.body, command.body) && this.tmdbId == command.tmdbId;
        }

        public final Action getAction() {
            return this.action;
        }

        public final String getAuthHeader() {
            return this.authHeader;
        }

        public final SimklScrobbleBody getBody() {
            return this.body;
        }

        public final int getTmdbId() {
            return this.tmdbId;
        }

        public int hashCode() {
            return ((this.body.hashCode() + c.c(this.action.hashCode() * 31, 31, this.authHeader)) * 31) + this.tmdbId;
        }

        public String toString() {
            return "Command(action=" + this.action + ", authHeader=" + this.authHeader + ", body=" + this.body + ", tmdbId=" + this.tmdbId + ")";
        }
    }

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\t\n\u0000\b\u0082\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u0006"}, d2 = {"Lcom/arflix/tv/data/repository/simkl/SimklScrobbler$Companion;", "", "<init>", "()V", "WRITE_LOCK_MS", "", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(kotlin.jvm.internal.h hVar) {
            this();
        }

        private Companion() {
        }
    }

    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[Action.values().length];
            try {
                iArr[Action.START.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[Action.PAUSE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[Action.STOP.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.data.repository.simkl.SimklScrobbler$execute$1, reason: invalid class name */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @f7.e(c = "com.arflix.tv.data.repository.simkl.SimklScrobbler", f = "SimklScrobbler.kt", l = {133, TsExtractor.TS_STREAM_TYPE_DTS_HD, 137, TsExtractor.TS_STREAM_TYPE_DTS}, m = "execute", v = 2)
    public static final class AnonymousClass1 extends f7.c {
        Object L$0;
        Object L$1;
        int label;
        /* synthetic */ Object result;

        public AnonymousClass1(d<? super AnonymousClass1> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return SimklScrobbler.this.execute(null, this);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.data.repository.simkl.SimklScrobbler$scrobblePause$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @f7.e(c = "com.arflix.tv.data.repository.simkl.SimklScrobbler", f = "SimklScrobbler.kt", l = {MdtaMetadataEntry.TYPE_INDICATOR_UNSIGNED_INT64, 82}, m = "scrobblePause", v = 2)
    public static final class C12451 extends f7.c {
        float F$0;
        int I$0;
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        Object L$5;
        boolean Z$0;
        int label;
        /* synthetic */ Object result;

        public C12451(d<? super C12451> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return SimklScrobbler.this.scrobblePause(null, 0, 0.0f, null, null, false, this);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.data.repository.simkl.SimklScrobbler$scrobbleStart$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @f7.e(c = "com.arflix.tv.data.repository.simkl.SimklScrobbler", f = "SimklScrobbler.kt", l = {ColorSpace.MaxId, MdtaMetadataEntry.TYPE_INDICATOR_INT32}, m = "scrobbleStart", v = 2)
    public static final class C12461 extends f7.c {
        float F$0;
        int I$0;
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        Object L$5;
        boolean Z$0;
        int label;
        /* synthetic */ Object result;

        public C12461(d<? super C12461> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return SimklScrobbler.this.scrobbleStart(null, 0, 0.0f, null, null, false, this);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.data.repository.simkl.SimklScrobbler$scrobbleStop$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @f7.e(c = "com.arflix.tv.data.repository.simkl.SimklScrobbler", f = "SimklScrobbler.kt", l = {93, 97}, m = "scrobbleStop", v = 2)
    public static final class C12471 extends f7.c {
        float F$0;
        int I$0;
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        Object L$5;
        boolean Z$0;
        int label;
        /* synthetic */ Object result;

        public C12471(d<? super C12471> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return SimklScrobbler.this.scrobbleStop(null, 0, 0.0f, null, null, false, this);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.data.repository.simkl.SimklScrobbler$submit$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @f7.e(c = "com.arflix.tv.data.repository.simkl.SimklScrobbler", f = "SimklScrobbler.kt", l = {187, 128}, m = "submit", v = 2)
    public static final class C12481 extends f7.c {
        int I$0;
        Object L$0;
        Object L$1;
        Object L$2;
        int label;
        /* synthetic */ Object result;

        public C12481(d<? super C12481> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return SimklScrobbler.this.submit(null, this);
        }
    }

    @Inject
    public SimklScrobbler(SimklApi simklApi, SimklAuthManager simklAuthManager) {
        this.simklApi = simklApi;
        this.authManager = simklAuthManager;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final SimklScrobbleBody buildScrobbleBody(MediaType mediaType, int tmdbId, float progress, Integer season, Integer episode, boolean isAnime) {
        float fNormalizeProgress = normalizeProgress(progress);
        if (mediaType == MediaType.MOVIE) {
            return new SimklScrobbleBody(new SimklMovieRef(0 == true ? 1 : 0, null, new SimklIds(null, Integer.valueOf(tmdbId), null, null, 13, null), 0 == true ? 1 : 0, null, 27, null), null, null, null, fNormalizeProgress, 14, 0 == true ? 1 : 0);
        }
        SimklShowRef simklShowRef = new SimklShowRef(null, 0 == true ? 1 : 0, new SimklIds(null, Integer.valueOf(tmdbId), null, null, 13, null), 0 == true ? 1 : 0, 0 == true ? 1 : 0, null, 59, null);
        return new SimklScrobbleBody(null, !isAnime ? simklShowRef : null, isAnime ? simklShowRef : null, (season == null || episode == null) ? null : new SimklEpisodeRef(season, episode, null, 4, null), fNormalizeProgress, 1, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code restructure failed: missing block: B:55:0x00fd, code lost:
    
        if (r13 == r8) goto L56;
     */
    /* JADX WARN: Removed duplicated region for block: B:60:0x010a A[Catch: Exception -> 0x003d, TRY_LEAVE, TryCatch #0 {Exception -> 0x003d, blocks: (B:15:0x0038, B:47:0x00be, B:58:0x0102, B:60:0x010a, B:22:0x0051, B:53:0x00e2, B:25:0x005e, B:57:0x0100, B:28:0x0067, B:34:0x007c, B:37:0x0081, B:40:0x0090, B:44:0x00a3, B:48:0x00c1, B:49:0x00c6, B:50:0x00c7, B:54:0x00e5, B:31:0x006e), top: B:64:0x0026 }] */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0015  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object execute(com.arflix.tv.data.repository.simkl.SimklScrobbler.Command r12, d7.d<? super x6.t0> r13) {
        /*
            Method dump skipped, instruction units count: 370
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.repository.simkl.SimklScrobbler.execute(com.arflix.tv.data.repository.simkl.SimklScrobbler$Command, d7.d):java.lang.Object");
    }

    private final String getClientId() {
        return Constants.INSTANCE.getSIMKL_CLIENT_ID();
    }

    private final float normalizeProgress(float progress) {
        return (0.0f > progress || progress > 1.0f) ? qb.d.m(progress, 0.0f, 100.0f) : progress * 100.0f;
    }

    public static /* synthetic */ Object scrobblePause$default(SimklScrobbler simklScrobbler, MediaType mediaType, int i10, float f10, Integer num, Integer num2, boolean z, d dVar, int i11, Object obj) {
        if ((i11 & 8) != 0) {
            num = null;
        }
        if ((i11 & 16) != 0) {
            num2 = null;
        }
        if ((i11 & 32) != 0) {
            z = false;
        }
        return simklScrobbler.scrobblePause(mediaType, i10, f10, num, num2, z, dVar);
    }

    public static /* synthetic */ Object scrobbleStart$default(SimklScrobbler simklScrobbler, MediaType mediaType, int i10, float f10, Integer num, Integer num2, boolean z, d dVar, int i11, Object obj) {
        if ((i11 & 8) != 0) {
            num = null;
        }
        if ((i11 & 16) != 0) {
            num2 = null;
        }
        if ((i11 & 32) != 0) {
            z = false;
        }
        return simklScrobbler.scrobbleStart(mediaType, i10, f10, num, num2, z, dVar);
    }

    public static /* synthetic */ Object scrobbleStop$default(SimklScrobbler simklScrobbler, MediaType mediaType, int i10, float f10, Integer num, Integer num2, boolean z, d dVar, int i11, Object obj) {
        if ((i11 & 8) != 0) {
            num = null;
        }
        if ((i11 & 16) != 0) {
            num2 = null;
        }
        if ((i11 & 32) != 0) {
            z = false;
        }
        return simklScrobbler.scrobbleStop(mediaType, i10, f10, num, num2, z, dVar);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code restructure failed: missing block: B:37:0x00d4, code lost:
    
        if (execute(r0, r1) == r6) goto L38;
     */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0015  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object submit(com.arflix.tv.data.repository.simkl.SimklScrobbler.Command r15, d7.d<? super x6.t0> r16) {
        /*
            Method dump skipped, instruction units count: 222
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.repository.simkl.SimklScrobbler.submit(com.arflix.tv.data.repository.simkl.SimklScrobbler$Command, d7.d):java.lang.Object");
    }

    public final r7.a<Long> getElapsedRealtimeMs$app_playRelease() {
        return this.elapsedRealtimeMs;
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0015  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object scrobblePause(com.arflix.tv.data.model.MediaType r12, int r13, float r14, java.lang.Integer r15, java.lang.Integer r16, boolean r17, d7.d<? super x6.t0> r18) {
        /*
            Method dump skipped, instruction units count: 202
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.repository.simkl.SimklScrobbler.scrobblePause(com.arflix.tv.data.model.MediaType, int, float, java.lang.Integer, java.lang.Integer, boolean, d7.d):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0015  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object scrobbleStart(com.arflix.tv.data.model.MediaType r12, int r13, float r14, java.lang.Integer r15, java.lang.Integer r16, boolean r17, d7.d<? super x6.t0> r18) {
        /*
            Method dump skipped, instruction units count: 202
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.repository.simkl.SimklScrobbler.scrobbleStart(com.arflix.tv.data.model.MediaType, int, float, java.lang.Integer, java.lang.Integer, boolean, d7.d):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0015  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object scrobbleStop(com.arflix.tv.data.model.MediaType r12, int r13, float r14, java.lang.Integer r15, java.lang.Integer r16, boolean r17, d7.d<? super x6.t0> r18) {
        /*
            Method dump skipped, instruction units count: 202
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.repository.simkl.SimklScrobbler.scrobbleStop(com.arflix.tv.data.model.MediaType, int, float, java.lang.Integer, java.lang.Integer, boolean, d7.d):java.lang.Object");
    }

    public final void setElapsedRealtimeMs$app_playRelease(r7.a<Long> aVar) {
        this.elapsedRealtimeMs = aVar;
    }
}
