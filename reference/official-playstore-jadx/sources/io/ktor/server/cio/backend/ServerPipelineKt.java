package io.ktor.server.cio.backend;

import androidx.compose.runtime.ComposerKt;
import androidx.media3.container.MdtaMetadataEntry;
import androidx.media3.extractor.ts.TsExtractor;
import d7.d;
import e7.a;
import f7.c;
import f7.e;
import f7.j;
import io.ktor.http.ContentDisposition;
import io.ktor.http.HttpProtocolVersion;
import io.ktor.http.HttpStatusCode;
import io.ktor.http.cio.ConnectionOptions;
import io.ktor.http.cio.Request;
import io.ktor.http.cio.RequestResponseBuilder;
import io.ktor.server.cio.PipelineKt;
import io.ktor.server.cio.internal.WeakTimeoutQueue;
import io.ktor.util.InternalAPI;
import io.ktor.utils.io.ByteChannel;
import io.ktor.utils.io.ByteReadChannel;
import io.ktor.utils.io.ByteWriteChannelKt;
import io.ktor.utils.io.core.ByteReadPacket;
import ka.k0;
import ka.m0;
import ka.r;
import ka.v1;
import kotlin.Metadata;
import r7.p;
import r7.q;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000v\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\u001ad\u0010\u0011\u001a\u00020\u0010*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u0004\u001a\u00020\u00032<\u0010\u000f\u001a8\b\u0001\u0012\u0004\u0012\u00020\u0006\u0012\u0013\u0012\u00110\u0007¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\n\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u000b\u0012\u0006\u0012\u0004\u0018\u00010\r0\u0005¢\u0006\u0002\b\u000eH\u0007ø\u0001\u0000¢\u0006\u0004\b\u0011\u0010\u0012\u001a!\u0010\u0016\u001a\u00020\f2\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00140\u0013H\u0082@ø\u0001\u0000¢\u0006\u0004\b\u0016\u0010\u0017\u001a1\u0010\u001a\u001a\u00020\f2\f\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u00140\u00182\u0006\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0002\u001a\u00020\u0001H\u0082@ø\u0001\u0000¢\u0006\u0004\b\u001a\u0010\u001b\u001a!\u0010!\u001a\u00020 2\u0006\u0010\u001d\u001a\u00020\u001c2\b\u0010\u001f\u001a\u0004\u0018\u00010\u001eH\u0000¢\u0006\u0004\b!\u0010\"\u001ak\u0010'\u001a#\b\u0001\u0012\u0004\u0012\u00028\u0000\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00010\u000b\u0012\u0006\u0012\u0004\u0018\u00010\r0%¢\u0006\u0002\b\u000e\"\u0004\b\u0000\u0010#\"\u0004\b\u0001\u0010$2)\b\b\u0010&\u001a#\b\u0001\u0012\u0004\u0012\u00028\u0000\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00010\u000b\u0012\u0006\u0012\u0004\u0018\u00010\r0%¢\u0006\u0002\b\u000eH\u0082\bø\u0001\u0000¢\u0006\u0004\b'\u0010(\"\u0014\u0010*\u001a\u00020)8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b*\u0010+\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006,"}, d2 = {"Lka/k0;", "Lio/ktor/server/cio/backend/ServerIncomingConnection;", "connection", "Lio/ktor/server/cio/internal/WeakTimeoutQueue;", "timeout", "Lkotlin/Function3;", "Lio/ktor/server/cio/backend/ServerRequestScope;", "Lio/ktor/http/cio/Request;", "Lx6/y;", ContentDisposition.Parameters.Name, "request", "Ld7/d;", "Lx6/t0;", "", "Lx6/n;", "handler", "Lka/v1;", "startServerConnectionPipeline", "(Lka/k0;Lio/ktor/server/cio/backend/ServerIncomingConnection;Lio/ktor/server/cio/internal/WeakTimeoutQueue;Lr7/q;)Lka/v1;", "Lma/j;", "Lio/ktor/utils/io/ByteReadChannel;", "actorChannel", "respondBadRequest", "(Lma/j;Ld7/d;)Ljava/lang/Object;", "Lma/w;", "channel", "pipelineWriterLoop", "(Lma/w;Lio/ktor/server/cio/internal/WeakTimeoutQueue;Lio/ktor/server/cio/backend/ServerIncomingConnection;Ld7/d;)Ljava/lang/Object;", "Lio/ktor/http/HttpProtocolVersion;", "version", "Lio/ktor/http/cio/ConnectionOptions;", "connectionOptions", "", "isLastHttpRequest", "(Lio/ktor/http/HttpProtocolVersion;Lio/ktor/http/cio/ConnectionOptions;)Z", "S", "R", "Lkotlin/Function2;", "block", "suspendLambda", "(Lr7/p;)Lr7/p;", "Lio/ktor/utils/io/core/ByteReadPacket;", "BadRequestPacket", "Lio/ktor/utils/io/core/ByteReadPacket;", "ktor-server-cio"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class ServerPipelineKt {
    private static final ByteReadPacket BadRequestPacket;

    /* JADX INFO: renamed from: io.ktor.server.cio.backend.ServerPipelineKt$pipelineWriterLoop$1, reason: invalid class name */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    @e(c = "io.ktor.server.cio.backend.ServerPipelineKt", f = "ServerPipeline.kt", l = {205, ComposerKt.reuseKey}, m = "pipelineWriterLoop")
    public static final class AnonymousClass1 extends c {
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        int label;
        /* synthetic */ Object result;

        public AnonymousClass1(d<? super AnonymousClass1> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return ServerPipelineKt.pipelineWriterLoop(null, null, null, this);
        }
    }

    /* JADX INFO: renamed from: io.ktor.server.cio.backend.ServerPipelineKt$respondBadRequest$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    @e(c = "io.ktor.server.cio.backend.ServerPipelineKt", f = "ServerPipeline.kt", l = {TsExtractor.TS_PACKET_SIZE}, m = "respondBadRequest")
    public static final class C18701 extends c {
        Object L$0;
        Object L$1;
        int label;
        /* synthetic */ Object result;

        public C18701(d<? super C18701> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return ServerPipelineKt.respondBadRequest(null, this);
        }
    }

    /* JADX INFO: renamed from: io.ktor.server.cio.backend.ServerPipelineKt$startServerConnectionPipeline$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {1, 8, 0})
    @e(c = "io.ktor.server.cio.backend.ServerPipelineKt$startServerConnectionPipeline$1", f = "ServerPipeline.kt", l = {58, 60, MdtaMetadataEntry.TYPE_INDICATOR_INT32, 84, 112, 147, 149, 158, 168}, m = "invokeSuspend")
    public static final class C18711 extends j implements p<k0, d<? super t0>, Object> {
        final /* synthetic */ ServerIncomingConnection $connection;
        final /* synthetic */ q<ServerRequestScope, Request, d<? super t0>, Object> $handler;
        final /* synthetic */ WeakTimeoutQueue $timeout;
        long J$0;
        private /* synthetic */ Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        Object L$5;
        Object L$6;
        Object L$7;
        Object L$8;
        boolean Z$0;
        int label;

        /* JADX INFO: renamed from: io.ktor.server.cio.backend.ServerPipelineKt$startServerConnectionPipeline$1$1, reason: invalid class name and collision with other inner class name */
        @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {1, 8, 0})
        @e(c = "io.ktor.server.cio.backend.ServerPipelineKt$startServerConnectionPipeline$1$1", f = "ServerPipeline.kt", l = {TsExtractor.TS_STREAM_TYPE_MHAS}, m = "invokeSuspend")
        public static final class C02351 extends j implements p<k0, d<? super t0>, Object> {
            final /* synthetic */ ma.j<ByteReadChannel> $actorChannel;
            final /* synthetic */ ServerIncomingConnection $connection;
            final /* synthetic */ WeakTimeoutQueue $timeout;
            int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public C02351(ma.j<ByteReadChannel> jVar, WeakTimeoutQueue weakTimeoutQueue, ServerIncomingConnection serverIncomingConnection, d<? super C02351> dVar) {
                super(2, dVar);
                this.$actorChannel = jVar;
                this.$timeout = weakTimeoutQueue;
                this.$connection = serverIncomingConnection;
            }

            @Override // f7.a
            public final d<t0> create(Object obj, d<?> dVar) {
                return new C02351(this.$actorChannel, this.$timeout, this.$connection, dVar);
            }

            @Override // f7.a
            public final Object invokeSuspend(Object obj) {
                int i10 = this.label;
                try {
                    if (i10 == 0) {
                        k2.c.G(obj);
                        ma.j<ByteReadChannel> jVar = this.$actorChannel;
                        WeakTimeoutQueue weakTimeoutQueue = this.$timeout;
                        ServerIncomingConnection serverIncomingConnection = this.$connection;
                        this.label = 1;
                        Object objPipelineWriterLoop = ServerPipelineKt.pipelineWriterLoop(jVar, weakTimeoutQueue, serverIncomingConnection, this);
                        a aVar = a.f15033i;
                        if (objPipelineWriterLoop == aVar) {
                            return aVar;
                        }
                    } else {
                        if (i10 != 1) {
                            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                        }
                        k2.c.G(obj);
                    }
                } finally {
                    try {
                    } finally {
                    }
                }
                return t0.f22605a;
            }

            @Override // r7.p
            public final Object invoke(k0 k0Var, d<? super t0> dVar) {
                return ((C02351) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
            }
        }

        /* JADX INFO: renamed from: io.ktor.server.cio.backend.ServerPipelineKt$startServerConnectionPipeline$1$2, reason: invalid class name */
        @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {1, 8, 0})
        @e(c = "io.ktor.server.cio.backend.ServerPipelineKt$startServerConnectionPipeline$1$2", f = "ServerPipeline.kt", l = {TsExtractor.TS_STREAM_TYPE_DTS_HD}, m = "invokeSuspend")
        public static final class AnonymousClass2 extends j implements p<k0, d<? super t0>, Object> {
            final /* synthetic */ ServerIncomingConnection $connection;
            final /* synthetic */ q<ServerRequestScope, Request, d<? super t0>, Object> $handler;
            final /* synthetic */ Request $request;
            final /* synthetic */ ByteReadChannel $requestBody;
            final /* synthetic */ ByteChannel $response;
            final /* synthetic */ r<Boolean> $upgraded;
            private /* synthetic */ Object L$0;
            int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            public AnonymousClass2(ByteReadChannel byteReadChannel, ByteChannel byteChannel, ServerIncomingConnection serverIncomingConnection, r<Boolean> rVar, q<? super ServerRequestScope, ? super Request, ? super d<? super t0>, ? extends Object> qVar, Request request, d<? super AnonymousClass2> dVar) {
                super(2, dVar);
                this.$requestBody = byteReadChannel;
                this.$response = byteChannel;
                this.$connection = serverIncomingConnection;
                this.$upgraded = rVar;
                this.$handler = qVar;
                this.$request = request;
            }

            @Override // f7.a
            public final d<t0> create(Object obj, d<?> dVar) {
                AnonymousClass2 anonymousClass2 = new AnonymousClass2(this.$requestBody, this.$response, this.$connection, this.$upgraded, this.$handler, this.$request, dVar);
                anonymousClass2.L$0 = obj;
                return anonymousClass2;
            }

            @Override // f7.a
            public final Object invokeSuspend(Object obj) {
                r<Boolean> rVar;
                r<Boolean> rVar2;
                int i10 = this.label;
                try {
                    if (i10 == 0) {
                        k2.c.G(obj);
                        ServerRequestScope serverRequestScope = new ServerRequestScope(((k0) this.L$0).getCoroutineContext(), this.$requestBody, this.$response, this.$connection.getRemoteAddress(), this.$connection.getLocalAddress(), this.$upgraded);
                        q<ServerRequestScope, Request, d<? super t0>, Object> qVar = this.$handler;
                        Request request = this.$request;
                        this.label = 1;
                        Object objInvoke = qVar.invoke(serverRequestScope, request, this);
                        a aVar = a.f15033i;
                        if (objInvoke == aVar) {
                            return aVar;
                        }
                    } else {
                        if (i10 != 1) {
                            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                        }
                        k2.c.G(obj);
                    }
                } catch (Throwable th) {
                    try {
                        this.$response.close(th);
                        r<Boolean> rVar3 = this.$upgraded;
                        if (rVar3 != null) {
                            rVar3.i(th);
                        }
                        ByteWriteChannelKt.close(this.$response);
                        rVar2 = this.$upgraded;
                        if (rVar2 != null) {
                        }
                    } finally {
                        ByteWriteChannelKt.close(this.$response);
                        rVar = this.$upgraded;
                        if (rVar != null) {
                            rVar.R(Boolean.FALSE);
                        }
                        throw th;
                    }
                }
                if (rVar != null) {
                    rVar2.R(Boolean.FALSE);
                }
                return t0.f22605a;
            }

            @Override // r7.p
            public final Object invoke(k0 k0Var, d<? super t0> dVar) {
                return ((AnonymousClass2) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public C18711(ServerIncomingConnection serverIncomingConnection, WeakTimeoutQueue weakTimeoutQueue, q<? super ServerRequestScope, ? super Request, ? super d<? super t0>, ? extends Object> qVar, d<? super C18711> dVar) {
            super(2, dVar);
            this.$connection = serverIncomingConnection;
            this.$timeout = weakTimeoutQueue;
            this.$handler = qVar;
        }

        @Override // f7.a
        public final d<t0> create(Object obj, d<?> dVar) {
            C18711 c18711 = new C18711(this.$connection, this.$timeout, this.$handler, dVar);
            c18711.L$0 = obj;
            return c18711;
        }

        /* JADX WARN: Code restructure failed: missing block: B:176:0x045f, code lost:
        
            if (r0 == r13) goto L204;
         */
        /* JADX WARN: Code restructure failed: missing block: B:181:0x047e, code lost:
        
            if (r0 == r13) goto L204;
         */
        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Not initialized variable reg: 2, insn: 0x03c5: MOVE (r1 I:??[OBJECT, ARRAY]) = (r2 I:??[OBJECT, ARRAY]) (LINE:966), block:B:141:0x03c5 */
        /* JADX WARN: Not initialized variable reg: 3, insn: 0x03c6: MOVE (r0 I:??[OBJECT, ARRAY]) = (r3 I:??[OBJECT, ARRAY]) (LINE:967), block:B:141:0x03c5 */
        /* JADX WARN: Path cross not found for [B:81:0x025a, B:84:0x0266], limit reached: 227 */
        /* JADX WARN: Path cross not found for [B:86:0x0269, B:87:0x026b], limit reached: 227 */
        /* JADX WARN: Removed duplicated region for block: B:102:0x02de A[Catch: all -> 0x00a9, IOException -> 0x00ad, TryCatch #4 {all -> 0x00a9, blocks: (B:100:0x02d6, B:102:0x02de, B:108:0x0311, B:110:0x0315, B:115:0x032f, B:24:0x009c), top: B:198:0x009c }] */
        /* JADX WARN: Removed duplicated region for block: B:107:0x030f  */
        /* JADX WARN: Removed duplicated region for block: B:113:0x0322  */
        /* JADX WARN: Removed duplicated region for block: B:135:0x03b4  */
        /* JADX WARN: Removed duplicated region for block: B:136:0x03b6  */
        /* JADX WARN: Removed duplicated region for block: B:151:0x03e3  */
        /* JADX WARN: Removed duplicated region for block: B:216:0x032f A[EXC_TOP_SPLITTER, SYNTHETIC] */
        /* JADX WARN: Removed duplicated region for block: B:60:0x01a2 A[Catch: all -> 0x0435, CancellationException -> 0x043a, IOException -> 0x043d, TooLongLineException -> 0x0440, PHI: r0 r1 r2 r3 r4 r5 r7 r8 r13
          0x01a2: PHI (r0v72 d7.j) = (r0v19 d7.j), (r0v76 d7.j) binds: [B:47:0x012f, B:58:0x019c] A[DONT_GENERATE, DONT_INLINE]
          0x01a2: PHI (r1v53 ka.k0) = (r1v18 ka.k0), (r1v72 ka.k0) binds: [B:47:0x012f, B:58:0x019c] A[DONT_GENERATE, DONT_INLINE]
          0x01a2: PHI (r2v37 ma.j) = (r2v8 ma.j), (r2v39 ma.j) binds: [B:47:0x012f, B:58:0x019c] A[DONT_GENERATE, DONT_INLINE]
          0x01a2: PHI (r3v28 java.lang.Object) = (r3v4 java.lang.Object), (r3v32 java.lang.Object) binds: [B:47:0x012f, B:58:0x019c] A[DONT_GENERATE, DONT_INLINE]
          0x01a2: PHI (r4v16 io.ktor.server.cio.backend.ServerPipelineKt$startServerConnectionPipeline$1) = 
          (r4v0 io.ktor.server.cio.backend.ServerPipelineKt$startServerConnectionPipeline$1)
          (r4v18 io.ktor.server.cio.backend.ServerPipelineKt$startServerConnectionPipeline$1)
         binds: [B:47:0x012f, B:58:0x019c] A[DONT_GENERATE, DONT_INLINE]
          0x01a2: PHI (r5v19 int) = (r5v2 int), (r5v22 int) binds: [B:47:0x012f, B:58:0x019c] A[DONT_GENERATE, DONT_INLINE]
          0x01a2: PHI (r7v28 e7.a) = (r7v0 e7.a), (r7v34 e7.a) binds: [B:47:0x012f, B:58:0x019c] A[DONT_GENERATE, DONT_INLINE]
          0x01a2: PHI (r8v4 java.lang.String) = (r8v0 java.lang.String), (r8v5 java.lang.String) binds: [B:47:0x012f, B:58:0x019c] A[DONT_GENERATE, DONT_INLINE]
          0x01a2: PHI (r13v25 boolean) = (r13v35 boolean), (r13v37 boolean) binds: [B:47:0x012f, B:58:0x019c] A[DONT_GENERATE, DONT_INLINE], TRY_LEAVE, TryCatch #27 {TooLongLineException -> 0x0440, IOException -> 0x043d, CancellationException -> 0x043a, all -> 0x0435, blocks: (B:57:0x017c, B:60:0x01a2), top: B:230:0x017c }] */
        /* JADX WARN: Removed duplicated region for block: B:64:0x01ab  */
        /* JADX WARN: Removed duplicated region for block: B:72:0x021e A[Catch: all -> 0x0241, TryCatch #24 {all -> 0x0241, blocks: (B:70:0x0201, B:72:0x021e, B:79:0x024a, B:81:0x025a, B:75:0x0239, B:76:0x0240), top: B:214:0x0201 }] */
        /* JADX WARN: Removed duplicated region for block: B:78:0x0247  */
        /* JADX WARN: Removed duplicated region for block: B:92:0x0280 A[Catch: all -> 0x0070, IOException -> 0x0075, TryCatch #0 {IOException -> 0x0075, blocks: (B:88:0x026e, B:92:0x0280, B:94:0x0289, B:96:0x02a8, B:90:0x0279, B:17:0x006b, B:40:0x010a, B:43:0x0119), top: B:197:0x000a }] */
        /* JADX WARN: Removed duplicated region for block: B:93:0x0287  */
        /* JADX WARN: Removed duplicated region for block: B:96:0x02a8 A[Catch: all -> 0x0070, IOException -> 0x0075, TRY_LEAVE, TryCatch #0 {IOException -> 0x0075, blocks: (B:88:0x026e, B:92:0x0280, B:94:0x0289, B:96:0x02a8, B:90:0x0279, B:17:0x006b, B:40:0x010a, B:43:0x0119), top: B:197:0x000a }] */
        /* JADX WARN: Type inference failed for: r0v0, types: [int] */
        /* JADX WARN: Type inference failed for: r0v100 */
        /* JADX WARN: Type inference failed for: r0v101 */
        /* JADX WARN: Type inference failed for: r0v102 */
        /* JADX WARN: Type inference failed for: r0v2 */
        /* JADX WARN: Type inference failed for: r0v3 */
        /* JADX WARN: Type inference failed for: r0v36 */
        /* JADX WARN: Type inference failed for: r0v48 */
        /* JADX WARN: Type inference failed for: r0v50 */
        /* JADX WARN: Type inference failed for: r0v57 */
        /* JADX WARN: Type inference failed for: r0v6 */
        /* JADX WARN: Type inference failed for: r0v62 */
        /* JADX WARN: Type inference failed for: r0v63 */
        /* JADX WARN: Type inference failed for: r0v8 */
        /* JADX WARN: Type inference failed for: r0v86 */
        /* JADX WARN: Type inference failed for: r0v9, types: [ka.k0] */
        /* JADX WARN: Type inference failed for: r13v21, types: [int] */
        /* JADX WARN: Type inference failed for: r13v36 */
        /* JADX WARN: Type inference failed for: r13v38 */
        /* JADX WARN: Type inference failed for: r2v28 */
        /* JADX WARN: Type inference failed for: r2v29 */
        /* JADX WARN: Type inference failed for: r2v34 */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:122:0x0361 -> B:226:0x0366). Please report as a decompilation issue!!! */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:147:0x03d8 -> B:125:0x036e). Please report as a decompilation issue!!! */
        @Override // f7.a
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r34) throws java.lang.Throwable {
            /*
                Method dump skipped, instruction units count: 1202
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: io.ktor.server.cio.backend.ServerPipelineKt.C18711.invokeSuspend(java.lang.Object):java.lang.Object");
        }

        @Override // r7.p
        public final Object invoke(k0 k0Var, d<? super t0> dVar) {
            return ((C18711) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
        }
    }

    static {
        RequestResponseBuilder requestResponseBuilder = new RequestResponseBuilder();
        requestResponseBuilder.responseLine("HTTP/1.0", HttpStatusCode.INSTANCE.getBadRequest().getValue(), "Bad Request");
        requestResponseBuilder.headerLine("Connection", "close");
        requestResponseBuilder.emptyLine();
        BadRequestPacket = requestResponseBuilder.build();
    }

    public static final boolean isLastHttpRequest(HttpProtocolVersion httpProtocolVersion, ConnectionOptions connectionOptions) {
        if (connectionOptions == null && httpProtocolVersion.equals(HttpProtocolVersion.INSTANCE.getHTTP_1_0())) {
            return true;
        }
        return connectionOptions == null ? !httpProtocolVersion.equals(HttpProtocolVersion.INSTANCE.getHTTP_1_1()) : !connectionOptions.getKeepAlive() && connectionOptions.getClose();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Can't wrap try/catch for region: R(7:12|(3:48|13|14)|44|34|22|(3:25|26|(2:28|29)(3:46|30|(5:33|44|34|22|(0))))|32) */
    /* JADX WARN: Code restructure failed: missing block: B:36:0x00a7, code lost:
    
        r1 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:37:0x00a8, code lost:
    
        r7 = r0;
        r0 = r11;
        r11 = r1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:42:0x00b9, code lost:
    
        ((io.ktor.utils.io.ByteWriteChannel) r8).close(r11);
     */
    /* JADX WARN: Code restructure failed: missing block: B:43:0x00be, code lost:
    
        r11 = r0;
        r0 = r1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:50:0x0065, code lost:
    
        r11 = r0;
        r0 = r1;
     */
    /* JADX WARN: Removed duplicated region for block: B:25:0x0076  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x0080  */
    /* JADX WARN: Removed duplicated region for block: B:42:0x00b9  */
    /* JADX WARN: Removed duplicated region for block: B:43:0x00be  */
    /* JADX WARN: Removed duplicated region for block: B:46:0x0083 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:33:0x0099 -> B:44:0x009f). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.Object pipelineWriterLoop(ma.w<? extends io.ktor.utils.io.ByteReadChannel> r8, io.ktor.server.cio.internal.WeakTimeoutQueue r9, io.ktor.server.cio.backend.ServerIncomingConnection r10, d7.d<? super x6.t0> r11) {
        /*
            boolean r0 = r11 instanceof io.ktor.server.cio.backend.ServerPipelineKt.AnonymousClass1
            if (r0 == 0) goto L13
            r0 = r11
            io.ktor.server.cio.backend.ServerPipelineKt$pipelineWriterLoop$1 r0 = (io.ktor.server.cio.backend.ServerPipelineKt.AnonymousClass1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            io.ktor.server.cio.backend.ServerPipelineKt$pipelineWriterLoop$1 r0 = new io.ktor.server.cio.backend.ServerPipelineKt$pipelineWriterLoop$1
            r0.<init>(r11)
        L18:
            java.lang.Object r11 = r0.result
            int r1 = r0.label
            r2 = 0
            r3 = 2
            r4 = 1
            e7.a r5 = e7.a.f15033i
            if (r1 == 0) goto L5d
            if (r1 == r4) goto L4d
            if (r1 != r3) goto L45
            java.lang.Object r8 = r0.L$3
            io.ktor.utils.io.ByteReadChannel r8 = (io.ktor.utils.io.ByteReadChannel) r8
            java.lang.Object r9 = r0.L$2
            r7.p r9 = (r7.p) r9
            java.lang.Object r10 = r0.L$1
            io.ktor.server.cio.backend.ServerIncomingConnection r10 = (io.ktor.server.cio.backend.ServerIncomingConnection) r10
            java.lang.Object r1 = r0.L$0
            io.ktor.server.cio.internal.WeakTimeoutQueue r1 = (io.ktor.server.cio.internal.WeakTimeoutQueue) r1
            k2.c.G(r11)     // Catch: java.lang.Throwable -> L3e
            r11 = r9
            r9 = r1
            goto L9f
        L3e:
            r11 = move-exception
            r7 = r0
            r0 = r9
            r9 = r1
        L42:
            r1 = r7
            goto Lb5
        L45:
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
            java.lang.String r9 = "call to 'resume' before 'invoke' with coroutine"
            r8.<init>(r9)
            throw r8
        L4d:
            java.lang.Object r8 = r0.L$2
            r7.p r8 = (r7.p) r8
            java.lang.Object r9 = r0.L$1
            io.ktor.server.cio.backend.ServerIncomingConnection r9 = (io.ktor.server.cio.backend.ServerIncomingConnection) r9
            java.lang.Object r10 = r0.L$0
            io.ktor.server.cio.internal.WeakTimeoutQueue r10 = (io.ktor.server.cio.internal.WeakTimeoutQueue) r10
            k2.c.G(r11)
            goto L7c
        L5d:
            k2.c.G(r11)
            io.ktor.server.cio.backend.ServerPipelineKt$pipelineWriterLoop$receiveChildOrNull$1 r11 = new io.ktor.server.cio.backend.ServerPipelineKt$pipelineWriterLoop$receiveChildOrNull$1
            r11.<init>(r8, r2)
        L65:
            r0.L$0 = r9
            r0.L$1 = r10
            r0.L$2 = r11
            r0.L$3 = r2
            r0.label = r4
            java.lang.Object r8 = r9.withTimeout(r11, r0)
            if (r8 != r5) goto L76
            goto L98
        L76:
            r7 = r11
            r11 = r8
            r8 = r7
            r7 = r10
            r10 = r9
            r9 = r7
        L7c:
            io.ktor.utils.io.ByteReadChannel r11 = (io.ktor.utils.io.ByteReadChannel) r11
            if (r11 != 0) goto L83
            x6.t0 r8 = x6.t0.f22605a
            return r8
        L83:
            io.ktor.utils.io.ByteWriteChannel r1 = r9.getOutput()     // Catch: java.lang.Throwable -> Lac
            r0.L$0 = r10     // Catch: java.lang.Throwable -> Lac
            r0.L$1 = r9     // Catch: java.lang.Throwable -> Lac
            r0.L$2 = r8     // Catch: java.lang.Throwable -> Lac
            r0.L$3 = r11     // Catch: java.lang.Throwable -> Lac
            r0.label = r3     // Catch: java.lang.Throwable -> Lac
            r6 = 0
            java.lang.Object r1 = io.ktor.utils.io.ByteReadChannelJVMKt.joinTo(r11, r1, r6, r0)     // Catch: java.lang.Throwable -> Lac
            if (r1 != r5) goto L99
        L98:
            return r5
        L99:
            r7 = r11
            r11 = r8
            r8 = r7
            r7 = r10
            r10 = r9
            r9 = r7
        L9f:
            io.ktor.utils.io.ByteWriteChannel r1 = r10.getOutput()     // Catch: java.lang.Throwable -> La7
            r1.flush()     // Catch: java.lang.Throwable -> La7
            goto L65
        La7:
            r1 = move-exception
            r7 = r0
            r0 = r11
            r11 = r1
            goto L42
        Lac:
            r1 = move-exception
            r7 = r0
            r0 = r8
            r8 = r11
            r11 = r1
            r1 = r7
            r7 = r10
            r10 = r9
            r9 = r7
        Lb5:
            boolean r6 = r8 instanceof io.ktor.utils.io.ByteWriteChannel
            if (r6 == 0) goto Lbe
            io.ktor.utils.io.ByteWriteChannel r8 = (io.ktor.utils.io.ByteWriteChannel) r8
            r8.close(r11)
        Lbe:
            r11 = r0
            r0 = r1
            goto L65
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.server.cio.backend.ServerPipelineKt.pipelineWriterLoop(ma.w, io.ktor.server.cio.internal.WeakTimeoutQueue, io.ktor.server.cio.backend.ServerIncomingConnection, d7.d):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.Object respondBadRequest(ma.j<io.ktor.utils.io.ByteReadChannel> r4, d7.d<? super x6.t0> r5) {
        /*
            boolean r0 = r5 instanceof io.ktor.server.cio.backend.ServerPipelineKt.C18701
            if (r0 == 0) goto L13
            r0 = r5
            io.ktor.server.cio.backend.ServerPipelineKt$respondBadRequest$1 r0 = (io.ktor.server.cio.backend.ServerPipelineKt.C18701) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            io.ktor.server.cio.backend.ServerPipelineKt$respondBadRequest$1 r0 = new io.ktor.server.cio.backend.ServerPipelineKt$respondBadRequest$1
            r0.<init>(r5)
        L18:
            java.lang.Object r5 = r0.result
            int r1 = r0.label
            r2 = 0
            r3 = 1
            if (r1 == 0) goto L36
            if (r1 != r3) goto L2e
            java.lang.Object r4 = r0.L$1
            io.ktor.utils.io.ByteChannel r4 = (io.ktor.utils.io.ByteChannel) r4
            java.lang.Object r0 = r0.L$0
            ma.j r0 = (ma.j) r0
            k2.c.G(r5)
            goto L5d
        L2e:
            java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
            java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
            r4.<init>(r5)
            throw r4
        L36:
            k2.c.G(r5)
            r5 = 0
            io.ktor.utils.io.ByteChannel r5 = io.ktor.utils.io.ByteChannelKt.ByteChannel$default(r5, r3, r2)
            java.lang.Object r1 = r4.mo6685trySendJP2dKIU(r5)
            boolean r1 = r1 instanceof ma.n
            if (r1 != 0) goto L61
            io.ktor.utils.io.core.ByteReadPacket r1 = io.ktor.server.cio.backend.ServerPipelineKt.BadRequestPacket
            io.ktor.utils.io.core.ByteReadPacket r1 = r1.copy()
            r0.L$0 = r4
            r0.L$1 = r5
            r0.label = r3
            java.lang.Object r0 = r5.writePacket(r1, r0)
            e7.a r1 = e7.a.f15033i
            if (r0 != r1) goto L5b
            return r1
        L5b:
            r0 = r4
            r4 = r5
        L5d:
            io.ktor.utils.io.ByteWriteChannelKt.close(r4)
            r4 = r0
        L61:
            r4.close(r2)
            x6.t0 r4 = x6.t0.f22605a
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.server.cio.backend.ServerPipelineKt.respondBadRequest(ma.j, d7.d):java.lang.Object");
    }

    @InternalAPI
    public static final v1 startServerConnectionPipeline(k0 k0Var, ServerIncomingConnection serverIncomingConnection, WeakTimeoutQueue weakTimeoutQueue, q<? super ServerRequestScope, ? super Request, ? super d<? super t0>, ? extends Object> qVar) {
        return m0.p(k0Var, PipelineKt.getHttpPipelineCoroutine(), 0, new C18711(serverIncomingConnection, weakTimeoutQueue, qVar, null), 2);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private static final <S, R> p<S, d<? super R>, Object> suspendLambda(p<? super S, ? super d<? super R>, ? extends Object> pVar) {
        return pVar;
    }
}
