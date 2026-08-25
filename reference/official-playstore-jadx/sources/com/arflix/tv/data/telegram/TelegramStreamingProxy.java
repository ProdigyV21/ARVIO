package com.arflix.tv.data.telegram;

import android.content.Context;
import android.util.Log;
import androidx.media3.extractor.text.ttml.TtmlNode;
import androidx.media3.extractor.ts.TsExtractor;
import d7.h;
import dagger.hilt.android.qualifiers.ApplicationContext;
import f7.j;
import io.ktor.server.application.Application;
import io.ktor.server.cio.CIO;
import io.ktor.server.engine.ApplicationEngine;
import io.ktor.server.engine.EmbeddedServerKt;
import io.ktor.server.routing.Routing;
import io.ktor.server.routing.RoutingBuilderKt;
import io.ktor.server.routing.RoutingKt;
import java.io.IOException;
import java.net.ServerSocket;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CancellationException;
import javax.inject.Inject;
import javax.inject.Singleton;
import ka.k0;
import ka.l0;
import ka.m0;
import ka.v1;
import ka.x0;
import kotlin.Metadata;
import kotlin.jvm.internal.p;
import kotlin.text.o;
import kotlin.text.u;
import org.drinkless.tdlib.TdApi;
import x6.t0;
import x6.x;

/* JADX INFO: loaded from: classes3.dex */
@Singleton
@Metadata(d1 = {"\u0000f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\u0012\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010%\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0007\u0018\u0000 72\u00020\u0001:\u000278B\u001b\b\u0007\u0012\b\b\u0001\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u0006\u0010\u0007J\u0018\u0010\u000b\u001a\u00020\n2\u0006\u0010\t\u001a\u00020\bH\u0082@¢\u0006\u0004\b\u000b\u0010\fJ\u0018\u0010\r\u001a\u00020\n2\u0006\u0010\t\u001a\u00020\bH\u0082@¢\u0006\u0004\b\r\u0010\fJ\u0018\u0010\u000e\u001a\u00020\n2\u0006\u0010\t\u001a\u00020\bH\u0082@¢\u0006\u0004\b\u000e\u0010\fJ<\u0010\u0016\u001a\u0004\u0018\u00010\u00152\u0006\u0010\t\u001a\u00020\b2\b\u0010\u0010\u001a\u0004\u0018\u00010\u000f2\u0006\u0010\u0012\u001a\u00020\u00112\u0006\u0010\u0013\u001a\u00020\b2\u0006\u0010\u0014\u001a\u00020\u0011H\u0082@¢\u0006\u0004\b\u0016\u0010\u0017J(\u0010\u0019\u001a\u0012\u0012\u0006\u0012\u0004\u0018\u00010\u000f\u0012\u0004\u0012\u00020\u0011\u0018\u00010\u00182\u0006\u0010\t\u001a\u00020\bH\u0082@¢\u0006\u0004\b\u0019\u0010\fJ)\u0010\u001b\u001a\u0012\u0012\u0006\u0012\u0004\u0018\u00010\u0011\u0012\u0006\u0012\u0004\u0018\u00010\u00110\u00182\b\u0010\u001a\u001a\u0004\u0018\u00010\u000fH\u0002¢\u0006\u0004\b\u001b\u0010\u001cJ\u000f\u0010\u001d\u001a\u00020\bH\u0002¢\u0006\u0004\b\u001d\u0010\u001eJ\r\u0010\u001f\u001a\u00020\n¢\u0006\u0004\b\u001f\u0010 J\r\u0010!\u001a\u00020\n¢\u0006\u0004\b!\u0010 J\u0015\u0010\"\u001a\u00020\u000f2\u0006\u0010\t\u001a\u00020\b¢\u0006\u0004\b\"\u0010#R\u0014\u0010\u0003\u001a\u00020\u00028\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0003\u0010$R\u0014\u0010\u0005\u001a\u00020\u00048\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0005\u0010%R\u0014\u0010'\u001a\u00020&8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b'\u0010(R\u0016\u0010)\u001a\u00020\b8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b)\u0010*R\u0018\u0010,\u001a\u0004\u0018\u00010+8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b,\u0010-R\u0018\u0010.\u001a\u0004\u0018\u00010\b8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b.\u0010/R\u0014\u00101\u001a\u0002008\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b1\u00102R \u00105\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u000204038\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b5\u00106¨\u00069"}, d2 = {"Lcom/arflix/tv/data/telegram/TelegramStreamingProxy;", "", "Landroid/content/Context;", "context", "Lcom/arflix/tv/data/telegram/TelegramClient;", "client", "<init>", "(Landroid/content/Context;Lcom/arflix/tv/data/telegram/TelegramClient;)V", "", "fileId", "Lx6/t0;", "deleteFile", "(ILd7/d;)Ljava/lang/Object;", "beginStreamRequest", "endStreamRequest", "", "localPath", "", "offset", "limit", "totalSize", "", "downloadChunk", "(ILjava/lang/String;JIJLd7/d;)Ljava/lang/Object;", "Lx6/x;", "getFileInfo", "header", "parseRange", "(Ljava/lang/String;)Lx6/x;", "findFreePort", "()I", TtmlNode.START, "()V", "stop", "getUrl", "(I)Ljava/lang/String;", "Landroid/content/Context;", "Lcom/arflix/tv/data/telegram/TelegramClient;", "Lka/k0;", "scope", "Lka/k0;", "port", "I", "Lio/ktor/server/engine/ApplicationEngine;", "server", "Lio/ktor/server/engine/ApplicationEngine;", "lastStreamedFileId", "Ljava/lang/Integer;", "Lua/a;", "requestStateMutex", "Lua/a;", "", "Lcom/arflix/tv/data/telegram/TelegramStreamingProxy$StreamRequestState;", "requestStates", "Ljava/util/Map;", "Companion", "StreamRequestState", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class TelegramStreamingProxy {
    private static final long CACHE_CLEANUP_GRACE_MS = 30000;
    private static final int CHUNK_SIZE = 2097152;
    private static final int DOWNLOAD_PRIORITY = 32;
    private static final long DOWNLOAD_TIMEOUT_MS = 30000;
    private static final long FILE_DELETE_TIMEOUT_MS = 5000;
    private static final long POLL_INTERVAL_MS = 100;
    private static final String TAG = "TelegramProxy";
    private final TelegramClient client;
    private final Context context;
    private volatile Integer lastStreamedFileId;
    private int port;
    private ApplicationEngine server;
    public static final int $stable = 8;
    private final k0 scope = l0.a(h.a(m0.d(), x0.f19655d));
    private final ua.a requestStateMutex = ua.e.a();
    private final Map<Integer, StreamRequestState> requestStates = new LinkedHashMap();

    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u000b\b\u0082\b\u0018\u00002\u00020\u0001B\u001d\u0012\b\b\u0002\u0010\u0003\u001a\u00020\u0002\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0004¢\u0006\u0004\b\u0006\u0010\u0007J\u0010\u0010\b\u001a\u00020\u0002HÆ\u0003¢\u0006\u0004\b\b\u0010\tJ\u0012\u0010\n\u001a\u0004\u0018\u00010\u0004HÆ\u0003¢\u0006\u0004\b\n\u0010\u000bJ&\u0010\f\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0004HÆ\u0001¢\u0006\u0004\b\f\u0010\rJ\u0010\u0010\u000f\u001a\u00020\u000eHÖ\u0001¢\u0006\u0004\b\u000f\u0010\u0010J\u0010\u0010\u0011\u001a\u00020\u0002HÖ\u0001¢\u0006\u0004\b\u0011\u0010\tJ\u001a\u0010\u0014\u001a\u00020\u00132\b\u0010\u0012\u001a\u0004\u0018\u00010\u0001HÖ\u0003¢\u0006\u0004\b\u0014\u0010\u0015R\"\u0010\u0003\u001a\u00020\u00028\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0003\u0010\u0016\u001a\u0004\b\u0017\u0010\t\"\u0004\b\u0018\u0010\u0019R$\u0010\u0005\u001a\u0004\u0018\u00010\u00048\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0005\u0010\u001a\u001a\u0004\b\u001b\u0010\u000b\"\u0004\b\u001c\u0010\u001d¨\u0006\u001e"}, d2 = {"Lcom/arflix/tv/data/telegram/TelegramStreamingProxy$StreamRequestState;", "", "", "activeRequests", "Lka/v1;", "cleanupJob", "<init>", "(ILka/v1;)V", "component1", "()I", "component2", "()Lka/v1;", "copy", "(ILka/v1;)Lcom/arflix/tv/data/telegram/TelegramStreamingProxy$StreamRequestState;", "", "toString", "()Ljava/lang/String;", "hashCode", "other", "", "equals", "(Ljava/lang/Object;)Z", "I", "getActiveRequests", "setActiveRequests", "(I)V", "Lka/v1;", "getCleanupJob", "setCleanupJob", "(Lka/v1;)V", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final /* data */ class StreamRequestState {
        private int activeRequests;
        private v1 cleanupJob;

        /* JADX WARN: Multi-variable type inference failed */
        public StreamRequestState() {
            this(0, null, 3, 0 == true ? 1 : 0);
        }

        public static /* synthetic */ StreamRequestState copy$default(StreamRequestState streamRequestState, int i10, v1 v1Var, int i11, Object obj) {
            if ((i11 & 1) != 0) {
                i10 = streamRequestState.activeRequests;
            }
            if ((i11 & 2) != 0) {
                v1Var = streamRequestState.cleanupJob;
            }
            return streamRequestState.copy(i10, v1Var);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final int getActiveRequests() {
            return this.activeRequests;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final v1 getCleanupJob() {
            return this.cleanupJob;
        }

        public final StreamRequestState copy(int activeRequests, v1 cleanupJob) {
            return new StreamRequestState(activeRequests, cleanupJob);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof StreamRequestState)) {
                return false;
            }
            StreamRequestState streamRequestState = (StreamRequestState) other;
            return this.activeRequests == streamRequestState.activeRequests && p.a(this.cleanupJob, streamRequestState.cleanupJob);
        }

        public final int getActiveRequests() {
            return this.activeRequests;
        }

        public final v1 getCleanupJob() {
            return this.cleanupJob;
        }

        public int hashCode() {
            int i10 = this.activeRequests * 31;
            v1 v1Var = this.cleanupJob;
            return i10 + (v1Var == null ? 0 : v1Var.hashCode());
        }

        public final void setActiveRequests(int i10) {
            this.activeRequests = i10;
        }

        public final void setCleanupJob(v1 v1Var) {
            this.cleanupJob = v1Var;
        }

        public String toString() {
            return "StreamRequestState(activeRequests=" + this.activeRequests + ", cleanupJob=" + this.cleanupJob + ")";
        }

        public StreamRequestState(int i10, v1 v1Var) {
            this.activeRequests = i10;
            this.cleanupJob = v1Var;
        }

        public /* synthetic */ StreamRequestState(int i10, v1 v1Var, int i11, kotlin.jvm.internal.h hVar) {
            this((i11 & 1) != 0 ? 0 : i10, (i11 & 2) != 0 ? null : v1Var);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.data.telegram.TelegramStreamingProxy$beginStreamRequest$1, reason: invalid class name */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @f7.e(c = "com.arflix.tv.data.telegram.TelegramStreamingProxy", f = "TelegramStreamingProxy.kt", l = {323}, m = "beginStreamRequest", v = 2)
    public static final class AnonymousClass1 extends f7.c {
        int I$0;
        int I$1;
        Object L$0;
        int label;
        /* synthetic */ Object result;

        public AnonymousClass1(d7.d<? super AnonymousClass1> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return TelegramStreamingProxy.this.beginStreamRequest(0, this);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.data.telegram.TelegramStreamingProxy$deleteFile$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @f7.e(c = "com.arflix.tv.data.telegram.TelegramStreamingProxy", f = "TelegramStreamingProxy.kt", l = {174, TsExtractor.TS_PACKET_SIZE}, m = "deleteFile", v = 2)
    public static final class C13001 extends f7.c {
        int I$0;
        int label;
        /* synthetic */ Object result;

        public C13001(d7.d<? super C13001> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return TelegramStreamingProxy.this.deleteFile(0, this);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.data.telegram.TelegramStreamingProxy$downloadChunk$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @f7.e(c = "com.arflix.tv.data.telegram.TelegramStreamingProxy", f = "TelegramStreamingProxy.kt", l = {TsExtractor.TS_STREAM_TYPE_AIT, 268, 284}, m = "downloadChunk", v = 2)
    public static final class C13011 extends f7.c {
        int I$0;
        int I$1;
        long J$0;
        long J$1;
        long J$2;
        long J$3;
        Object L$0;
        Object L$1;
        int label;
        /* synthetic */ Object result;

        public C13011(d7.d<? super C13011> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return TelegramStreamingProxy.this.downloadChunk(0, null, 0L, 0, 0L, this);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.data.telegram.TelegramStreamingProxy$downloadChunk$2, reason: invalid class name */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lorg/drinkless/tdlib/TdApi$Object;", "<anonymous>", "(Lka/k0;)Lorg/drinkless/tdlib/TdApi$Object;"}, k = 3, mv = {2, 3, 0})
    @f7.e(c = "com.arflix.tv.data.telegram.TelegramStreamingProxy$downloadChunk$2", f = "TelegramStreamingProxy.kt", l = {258}, m = "invokeSuspend", v = 2)
    public static final class AnonymousClass2 extends j implements r7.p<k0, d7.d<? super TdApi.Object>, Object> {
        final /* synthetic */ int $fileId;
        final /* synthetic */ long $offset;
        final /* synthetic */ long $prefetchSize;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass2(int i10, long j10, long j11, d7.d<? super AnonymousClass2> dVar) {
            super(2, dVar);
            this.$fileId = i10;
            this.$offset = j10;
            this.$prefetchSize = j11;
        }

        @Override // f7.a
        public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
            return TelegramStreamingProxy.this.new AnonymousClass2(this.$fileId, this.$offset, this.$prefetchSize, dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            int i10 = this.label;
            if (i10 != 0) {
                if (i10 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                k2.c.G(obj);
                return obj;
            }
            k2.c.G(obj);
            TelegramClient telegramClient = TelegramStreamingProxy.this.client;
            TdApi.DownloadFile downloadFile = new TdApi.DownloadFile();
            int i11 = this.$fileId;
            long j10 = this.$offset;
            long j11 = this.$prefetchSize;
            downloadFile.fileId = i11;
            downloadFile.priority = 32;
            downloadFile.offset = j10;
            downloadFile.limit = j11;
            downloadFile.synchronous = false;
            this.label = 1;
            Object objSendRequest$default = TelegramClient.sendRequest$default(telegramClient, downloadFile, 0L, this, 2, null);
            e7.a aVar = e7.a.f15033i;
            return objSendRequest$default == aVar ? aVar : objSendRequest$default;
        }

        @Override // r7.p
        public final Object invoke(k0 k0Var, d7.d<? super TdApi.Object> dVar) {
            return ((AnonymousClass2) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.data.telegram.TelegramStreamingProxy$endStreamRequest$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @f7.e(c = "com.arflix.tv.data.telegram.TelegramStreamingProxy", f = "TelegramStreamingProxy.kt", l = {323}, m = "endStreamRequest", v = 2)
    public static final class C13021 extends f7.c {
        int I$0;
        int I$1;
        Object L$0;
        int label;
        /* synthetic */ Object result;

        public C13021(d7.d<? super C13021> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return TelegramStreamingProxy.this.endStreamRequest(0, this);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.data.telegram.TelegramStreamingProxy$getFileInfo$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @f7.e(c = "com.arflix.tv.data.telegram.TelegramStreamingProxy", f = "TelegramStreamingProxy.kt", l = {292}, m = "getFileInfo", v = 2)
    public static final class C13031 extends f7.c {
        int I$0;
        int label;
        /* synthetic */ Object result;

        public C13031(d7.d<? super C13031> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return TelegramStreamingProxy.this.getFileInfo(0, this);
        }
    }

    @Inject
    public TelegramStreamingProxy(@ApplicationContext Context context, TelegramClient telegramClient) {
        this.context = context;
        this.client = telegramClient;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object beginStreamRequest(int r8, d7.d<? super x6.t0> r9) {
        /*
            r7 = this;
            boolean r0 = r9 instanceof com.arflix.tv.data.telegram.TelegramStreamingProxy.AnonymousClass1
            if (r0 == 0) goto L13
            r0 = r9
            com.arflix.tv.data.telegram.TelegramStreamingProxy$beginStreamRequest$1 r0 = (com.arflix.tv.data.telegram.TelegramStreamingProxy.AnonymousClass1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            com.arflix.tv.data.telegram.TelegramStreamingProxy$beginStreamRequest$1 r0 = new com.arflix.tv.data.telegram.TelegramStreamingProxy$beginStreamRequest$1
            r0.<init>(r9)
        L18:
            java.lang.Object r9 = r0.result
            e7.a r1 = e7.a.f15033i
            int r2 = r0.label
            r3 = 0
            r4 = 1
            if (r2 == 0) goto L36
            if (r2 != r4) goto L2e
            int r8 = r0.I$0
            java.lang.Object r0 = r0.L$0
            ua.a r0 = (ua.a) r0
            k2.c.G(r9)
            goto L4b
        L2e:
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
            java.lang.String r9 = "call to 'resume' before 'invoke' with coroutine"
            r8.<init>(r9)
            throw r8
        L36:
            k2.c.G(r9)
            ua.a r9 = r7.requestStateMutex
            r0.L$0 = r9
            r0.I$0 = r8
            r0.I$1 = r3
            r0.label = r4
            java.lang.Object r0 = r9.c(r0)
            if (r0 != r1) goto L4a
            return r1
        L4a:
            r0 = r9
        L4b:
            r9 = 0
            java.util.Map<java.lang.Integer, com.arflix.tv.data.telegram.TelegramStreamingProxy$StreamRequestState> r1 = r7.requestStates     // Catch: java.lang.Throwable -> L63
            java.lang.Integer r2 = new java.lang.Integer     // Catch: java.lang.Throwable -> L63
            r2.<init>(r8)     // Catch: java.lang.Throwable -> L63
            java.lang.Object r5 = r1.get(r2)     // Catch: java.lang.Throwable -> L63
            if (r5 != 0) goto L65
            com.arflix.tv.data.telegram.TelegramStreamingProxy$StreamRequestState r5 = new com.arflix.tv.data.telegram.TelegramStreamingProxy$StreamRequestState     // Catch: java.lang.Throwable -> L63
            r6 = 3
            r5.<init>(r3, r9, r6, r9)     // Catch: java.lang.Throwable -> L63
            r1.put(r2, r5)     // Catch: java.lang.Throwable -> L63
            goto L65
        L63:
            r8 = move-exception
            goto L88
        L65:
            com.arflix.tv.data.telegram.TelegramStreamingProxy$StreamRequestState r5 = (com.arflix.tv.data.telegram.TelegramStreamingProxy.StreamRequestState) r5     // Catch: java.lang.Throwable -> L63
            ka.v1 r1 = r5.getCleanupJob()     // Catch: java.lang.Throwable -> L63
            if (r1 == 0) goto L70
            r1.cancel(r9)     // Catch: java.lang.Throwable -> L63
        L70:
            r5.setCleanupJob(r9)     // Catch: java.lang.Throwable -> L63
            int r1 = r5.getActiveRequests()     // Catch: java.lang.Throwable -> L63
            int r1 = r1 + r4
            r5.setActiveRequests(r1)     // Catch: java.lang.Throwable -> L63
            java.lang.Integer r1 = new java.lang.Integer     // Catch: java.lang.Throwable -> L63
            r1.<init>(r8)     // Catch: java.lang.Throwable -> L63
            r7.lastStreamedFileId = r1     // Catch: java.lang.Throwable -> L63
            r0.b(r9)
            x6.t0 r8 = x6.t0.f22605a
            return r8
        L88:
            r0.b(r9)
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.telegram.TelegramStreamingProxy.beginStreamRequest(int, d7.d):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code restructure failed: missing block: B:30:0x0060, code lost:
    
        if (r12.sendRequest(r2, 5000, r1) == r8) goto L35;
     */
    /* JADX WARN: Code restructure failed: missing block: B:34:0x0085, code lost:
    
        if (r12.sendRequest(r2, 5000, r1) == r8) goto L35;
     */
    /* JADX WARN: Code restructure failed: missing block: B:35:0x0087, code lost:
    
        return r8;
     */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0015  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object deleteFile(int r11, d7.d<? super x6.t0> r12) {
        /*
            r10 = this;
            java.lang.String r0 = "Deleted cached file "
            boolean r1 = r12 instanceof com.arflix.tv.data.telegram.TelegramStreamingProxy.C13001
            if (r1 == 0) goto L15
            r1 = r12
            com.arflix.tv.data.telegram.TelegramStreamingProxy$deleteFile$1 r1 = (com.arflix.tv.data.telegram.TelegramStreamingProxy.C13001) r1
            int r2 = r1.label
            r3 = -2147483648(0xffffffff80000000, float:-0.0)
            r4 = r2 & r3
            if (r4 == 0) goto L15
            int r2 = r2 - r3
            r1.label = r2
            goto L1a
        L15:
            com.arflix.tv.data.telegram.TelegramStreamingProxy$deleteFile$1 r1 = new com.arflix.tv.data.telegram.TelegramStreamingProxy$deleteFile$1
            r1.<init>(r12)
        L1a:
            java.lang.Object r12 = r1.result
            int r2 = r1.label
            r3 = 5000(0x1388, double:2.4703E-320)
            r5 = 2
            r6 = 1
            java.lang.String r7 = "TelegramProxy"
            e7.a r8 = e7.a.f15033i
            if (r2 == 0) goto L49
            if (r2 == r6) goto L3f
            if (r2 != r5) goto L37
            int r11 = r1.I$0
            k2.c.G(r12)     // Catch: java.lang.Exception -> L32 java.util.concurrent.CancellationException -> L34
            goto L88
        L32:
            r12 = move-exception
            goto L98
        L34:
            r11 = move-exception
            goto Lac
        L37:
            java.lang.IllegalStateException r11 = new java.lang.IllegalStateException
            java.lang.String r12 = "call to 'resume' before 'invoke' with coroutine"
            r11.<init>(r12)
            throw r11
        L3f:
            int r11 = r1.I$0
            k2.c.G(r12)     // Catch: java.lang.Exception -> L45 java.util.concurrent.CancellationException -> L47
            goto L74
        L45:
            r12 = move-exception
            goto L63
        L47:
            r11 = move-exception
            goto Lad
        L49:
            k2.c.G(r12)
            com.arflix.tv.data.telegram.TelegramClient r12 = r10.client     // Catch: java.lang.Exception -> L45 java.util.concurrent.CancellationException -> L47
            org.drinkless.tdlib.TdApi$CancelDownloadFile r2 = new org.drinkless.tdlib.TdApi$CancelDownloadFile     // Catch: java.lang.Exception -> L45 java.util.concurrent.CancellationException -> L47
            r2.<init>()     // Catch: java.lang.Exception -> L45 java.util.concurrent.CancellationException -> L47
            r2.fileId = r11     // Catch: java.lang.Exception -> L45 java.util.concurrent.CancellationException -> L47
            r9 = 0
            r2.onlyIfPending = r9     // Catch: java.lang.Exception -> L45 java.util.concurrent.CancellationException -> L47
            r1.I$0 = r11     // Catch: java.lang.Exception -> L45 java.util.concurrent.CancellationException -> L47
            r1.label = r6     // Catch: java.lang.Exception -> L45 java.util.concurrent.CancellationException -> L47
            java.lang.Object r12 = r12.sendRequest(r2, r3, r1)     // Catch: java.lang.Exception -> L45 java.util.concurrent.CancellationException -> L47
            if (r12 != r8) goto L74
            goto L87
        L63:
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            java.lang.String r6 = "Failed to cancel cached file "
            r2.<init>(r6)
            r2.append(r11)
            java.lang.String r2 = r2.toString()
            android.util.Log.w(r7, r2, r12)
        L74:
            com.arflix.tv.data.telegram.TelegramClient r12 = r10.client     // Catch: java.lang.Exception -> L32 java.util.concurrent.CancellationException -> L34
            org.drinkless.tdlib.TdApi$DeleteFile r2 = new org.drinkless.tdlib.TdApi$DeleteFile     // Catch: java.lang.Exception -> L32 java.util.concurrent.CancellationException -> L34
            r2.<init>()     // Catch: java.lang.Exception -> L32 java.util.concurrent.CancellationException -> L34
            r2.fileId = r11     // Catch: java.lang.Exception -> L32 java.util.concurrent.CancellationException -> L34
            r1.I$0 = r11     // Catch: java.lang.Exception -> L32 java.util.concurrent.CancellationException -> L34
            r1.label = r5     // Catch: java.lang.Exception -> L32 java.util.concurrent.CancellationException -> L34
            java.lang.Object r12 = r12.sendRequest(r2, r3, r1)     // Catch: java.lang.Exception -> L32 java.util.concurrent.CancellationException -> L34
            if (r12 != r8) goto L88
        L87:
            return r8
        L88:
            java.lang.StringBuilder r12 = new java.lang.StringBuilder     // Catch: java.lang.Exception -> L32 java.util.concurrent.CancellationException -> L34
            r12.<init>(r0)     // Catch: java.lang.Exception -> L32 java.util.concurrent.CancellationException -> L34
            r12.append(r11)     // Catch: java.lang.Exception -> L32 java.util.concurrent.CancellationException -> L34
            java.lang.String r12 = r12.toString()     // Catch: java.lang.Exception -> L32 java.util.concurrent.CancellationException -> L34
            android.util.Log.d(r7, r12)     // Catch: java.lang.Exception -> L32 java.util.concurrent.CancellationException -> L34
            goto La9
        L98:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r1 = "Failed to delete cached file "
            r0.<init>(r1)
            r0.append(r11)
            java.lang.String r11 = r0.toString()
            android.util.Log.w(r7, r11, r12)
        La9:
            x6.t0 r11 = x6.t0.f22605a
            return r11
        Lac:
            throw r11
        Lad:
            throw r11
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.telegram.TelegramStreamingProxy.deleteFile(int, d7.d):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code restructure failed: missing block: B:39:0x016a, code lost:
    
        if (r0 == r14) goto L40;
     */
    /* JADX WARN: Removed duplicated region for block: B:37:0x012b A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:38:0x012c  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x001a  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object downloadChunk(int r21, java.lang.String r22, long r23, int r25, long r26, d7.d<? super byte[]> r28) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 391
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.telegram.TelegramStreamingProxy.downloadChunk(int, java.lang.String, long, int, long, d7.d):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object endStreamRequest(int r6, d7.d<? super x6.t0> r7) {
        /*
            r5 = this;
            boolean r0 = r7 instanceof com.arflix.tv.data.telegram.TelegramStreamingProxy.C13021
            if (r0 == 0) goto L13
            r0 = r7
            com.arflix.tv.data.telegram.TelegramStreamingProxy$endStreamRequest$1 r0 = (com.arflix.tv.data.telegram.TelegramStreamingProxy.C13021) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            com.arflix.tv.data.telegram.TelegramStreamingProxy$endStreamRequest$1 r0 = new com.arflix.tv.data.telegram.TelegramStreamingProxy$endStreamRequest$1
            r0.<init>(r7)
        L18:
            java.lang.Object r7 = r0.result
            int r1 = r0.label
            r2 = 0
            r3 = 1
            if (r1 == 0) goto L34
            if (r1 != r3) goto L2c
            int r6 = r0.I$0
            java.lang.Object r0 = r0.L$0
            ua.a r0 = (ua.a) r0
            k2.c.G(r7)
            goto L4b
        L2c:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r7)
            throw r6
        L34:
            k2.c.G(r7)
            ua.a r7 = r5.requestStateMutex
            r0.L$0 = r7
            r0.I$0 = r6
            r0.I$1 = r2
            r0.label = r3
            java.lang.Object r0 = r7.c(r0)
            e7.a r1 = e7.a.f15033i
            if (r0 != r1) goto L4a
            return r1
        L4a:
            r0 = r7
        L4b:
            r7 = 0
            java.util.Map<java.lang.Integer, com.arflix.tv.data.telegram.TelegramStreamingProxy$StreamRequestState> r1 = r5.requestStates     // Catch: java.lang.Throwable -> L7d
            java.lang.Integer r4 = new java.lang.Integer     // Catch: java.lang.Throwable -> L7d
            r4.<init>(r6)     // Catch: java.lang.Throwable -> L7d
            java.lang.Object r1 = r1.get(r4)     // Catch: java.lang.Throwable -> L7d
            com.arflix.tv.data.telegram.TelegramStreamingProxy$StreamRequestState r1 = (com.arflix.tv.data.telegram.TelegramStreamingProxy.StreamRequestState) r1     // Catch: java.lang.Throwable -> L7d
            if (r1 != 0) goto L5c
            goto L7f
        L5c:
            int r4 = r1.getActiveRequests()     // Catch: java.lang.Throwable -> L7d
            int r4 = r4 - r3
            if (r4 >= 0) goto L64
            r4 = r2
        L64:
            r1.setActiveRequests(r4)     // Catch: java.lang.Throwable -> L7d
            int r3 = r1.getActiveRequests()     // Catch: java.lang.Throwable -> L7d
            if (r3 != 0) goto L7f
            ka.k0 r3 = r5.scope     // Catch: java.lang.Throwable -> L7d
            com.arflix.tv.data.telegram.TelegramStreamingProxy$endStreamRequest$2$1 r4 = new com.arflix.tv.data.telegram.TelegramStreamingProxy$endStreamRequest$2$1     // Catch: java.lang.Throwable -> L7d
            r4.<init>(r5, r6, r7)     // Catch: java.lang.Throwable -> L7d
            r6 = 3
            ka.o2 r6 = ka.m0.p(r3, r7, r2, r4, r6)     // Catch: java.lang.Throwable -> L7d
            r1.setCleanupJob(r6)     // Catch: java.lang.Throwable -> L7d
            goto L7f
        L7d:
            r6 = move-exception
            goto L85
        L7f:
            r0.b(r7)
            x6.t0 r6 = x6.t0.f22605a
            return r6
        L85:
            r0.b(r7)
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.telegram.TelegramStreamingProxy.endStreamRequest(int, d7.d):java.lang.Object");
    }

    private final int findFreePort() throws IOException {
        ServerSocket serverSocket = new ServerSocket(0);
        try {
            int localPort = serverSocket.getLocalPort();
            serverSocket.close();
            return localPort;
        } finally {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0014  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object getFileInfo(int r9, d7.d<? super x6.x> r10) {
        /*
            r8 = this;
            boolean r0 = r10 instanceof com.arflix.tv.data.telegram.TelegramStreamingProxy.C13031
            if (r0 == 0) goto L14
            r0 = r10
            com.arflix.tv.data.telegram.TelegramStreamingProxy$getFileInfo$1 r0 = (com.arflix.tv.data.telegram.TelegramStreamingProxy.C13031) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L14
            int r1 = r1 - r2
            r0.label = r1
        L12:
            r5 = r0
            goto L1a
        L14:
            com.arflix.tv.data.telegram.TelegramStreamingProxy$getFileInfo$1 r0 = new com.arflix.tv.data.telegram.TelegramStreamingProxy$getFileInfo$1
            r0.<init>(r10)
            goto L12
        L1a:
            java.lang.Object r10 = r5.result
            int r0 = r5.label
            r1 = 1
            if (r0 == 0) goto L2f
            if (r0 != r1) goto L27
            k2.c.G(r10)
            goto L4b
        L27:
            java.lang.IllegalStateException r9 = new java.lang.IllegalStateException
            java.lang.String r10 = "call to 'resume' before 'invoke' with coroutine"
            r9.<init>(r10)
            throw r9
        L2f:
            k2.c.G(r10)
            r10 = r1
            com.arflix.tv.data.telegram.TelegramClient r1 = r8.client
            org.drinkless.tdlib.TdApi$GetFile r2 = new org.drinkless.tdlib.TdApi$GetFile
            r2.<init>(r9)
            r5.I$0 = r9
            r5.label = r10
            r3 = 0
            r6 = 2
            r7 = 0
            java.lang.Object r10 = com.arflix.tv.data.telegram.TelegramClient.sendRequest$default(r1, r2, r3, r5, r6, r7)
            e7.a r9 = e7.a.f15033i
            if (r10 != r9) goto L4b
            return r9
        L4b:
            boolean r9 = r10 instanceof org.drinkless.tdlib.TdApi.File
            r0 = 0
            if (r9 == 0) goto L53
            org.drinkless.tdlib.TdApi$File r10 = (org.drinkless.tdlib.TdApi.File) r10
            goto L54
        L53:
            r10 = r0
        L54:
            if (r10 != 0) goto L57
            return r0
        L57:
            long r1 = r10.size
            java.lang.Long r9 = new java.lang.Long
            r9.<init>(r1)
            long r1 = r9.longValue()
            r3 = 0
            int r1 = (r1 > r3 ? 1 : (r1 == r3 ? 0 : -1))
            if (r1 <= 0) goto L69
            goto L6a
        L69:
            r9 = r0
        L6a:
            if (r9 == 0) goto L71
            long r1 = r9.longValue()
            goto L73
        L71:
            long r1 = r10.expectedSize
        L73:
            org.drinkless.tdlib.TdApi$LocalFile r9 = r10.local
            if (r9 == 0) goto L82
            java.lang.String r9 = r9.path
            if (r9 == 0) goto L82
            boolean r10 = kotlin.text.o.h0(r9)
            if (r10 != 0) goto L82
            r0 = r9
        L82:
            x6.x r9 = new x6.x
            java.lang.Long r10 = new java.lang.Long
            r10.<init>(r1)
            r9.<init>(r0, r10)
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.telegram.TelegramStreamingProxy.getFileInfo(int, d7.d):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final x parseRange(String header) throws Exception {
        if (header == null) {
            return new x(null, null);
        }
        try {
            List listY0 = o.y0(o.r0(header, "bytes="), new String[]{"-"}, 0, 6);
            String str = (String) kotlin.collections.x.p0(0, listY0);
            Long lS = str != null ? u.S(str) : null;
            String str2 = (String) kotlin.collections.x.p0(1, listY0);
            return new x(lS, str2 != null ? u.S(str2) : null);
        } catch (Exception e5) {
            if (e5 instanceof CancellationException) {
                throw e5;
            }
            return new x(null, null);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 start$lambda$0(TelegramStreamingProxy telegramStreamingProxy, Application application) {
        RoutingKt.routing(application, new e(telegramStreamingProxy, 1));
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 start$lambda$0$0(TelegramStreamingProxy telegramStreamingProxy, Routing routing) {
        RoutingBuilderKt.get(routing, "/file/{fileId}", new TelegramStreamingProxy$start$1$1$1(telegramStreamingProxy, null));
        return t0.f22605a;
    }

    public final String getUrl(int fileId) {
        String strN = androidx.compose.foundation.c.n(this.port, fileId, "http://localhost:", "/file/");
        Log.d(TAG, "Generated stream URL: " + strN);
        return strN;
    }

    public final void start() {
        if (this.server != null) {
            return;
        }
        int iFindFreePort = findFreePort();
        this.port = iFindFreePort;
        ApplicationEngine applicationEngineEmbeddedServer$default = EmbeddedServerKt.embeddedServer$default(CIO.INSTANCE, iFindFreePort, null, null, null, new e(this, 0), 28, null);
        this.server = applicationEngineEmbeddedServer$default;
        applicationEngineEmbeddedServer$default.start(false);
        Log.d(TAG, "Streaming proxy started on port " + this.port);
    }

    public final void stop() {
        Integer num = this.lastStreamedFileId;
        if (num != null) {
            m0.p(this.scope, null, 0, new TelegramStreamingProxy$stop$1$1(this, num.intValue(), null), 3);
        }
        this.lastStreamedFileId = null;
        ApplicationEngine applicationEngine = this.server;
        if (applicationEngine != null) {
            applicationEngine.stop(0L, 0L);
        }
        this.server = null;
        Log.d(TAG, "Streaming proxy stopped");
    }
}
