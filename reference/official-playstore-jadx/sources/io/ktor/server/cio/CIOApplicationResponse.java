package io.ktor.server.cio;

import androidx.compose.ui.graphics.colorspace.ColorSpace;
import d7.d;
import d7.j;
import e7.a;
import f7.c;
import f7.e;
import io.ktor.http.HeadersBuilder;
import io.ktor.http.HttpStatusCode;
import io.ktor.server.engine.BaseApplicationResponse;
import io.ktor.server.response.ResponseHeaders;
import io.ktor.utils.io.ByteReadChannel;
import io.ktor.utils.io.ByteReadChannelKt;
import io.ktor.utils.io.ByteWriteChannel;
import io.ktor.utils.io.ByteWriteChannelKt;
import java.util.List;
import ka.k0;
import ka.r;
import ka.v1;
import kotlin.Metadata;
import kotlin.collections.x;
import kotlin.collections.z;
import r7.l;
import r7.p;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000v\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0012\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0000\u0018\u00002\u00020\u0001B?\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u0012\u0006\u0010\t\u001a\u00020\b\u0012\u0006\u0010\n\u001a\u00020\b\u0012\u000e\u0010\r\u001a\n\u0012\u0004\u0012\u00020\f\u0018\u00010\u000b¢\u0006\u0004\b\u000e\u0010\u000fJ\u001b\u0010\u0012\u001a\u00020\u00112\u0006\u0010\u0010\u001a\u00020\fH\u0082@ø\u0001\u0000¢\u0006\u0004\b\u0012\u0010\u0013J\u0013\u0010\u0014\u001a\u00020\u0004H\u0082@ø\u0001\u0000¢\u0006\u0004\b\u0014\u0010\u0015J\u0013\u0010\u0016\u001a\u00020\u0004H\u0094@ø\u0001\u0000¢\u0006\u0004\b\u0016\u0010\u0015J\u001b\u0010\u0019\u001a\u00020\u00112\u0006\u0010\u0018\u001a\u00020\u0017H\u0094@ø\u0001\u0000¢\u0006\u0004\b\u0019\u0010\u001aJ\u001b\u0010\u001d\u001a\u00020\u00112\u0006\u0010\u001c\u001a\u00020\u001bH\u0094@ø\u0001\u0000¢\u0006\u0004\b\u001d\u0010\u001eJ\u001b\u0010!\u001a\u00020\u00112\u0006\u0010 \u001a\u00020\u001fH\u0094@ø\u0001\u0000¢\u0006\u0004\b!\u0010\"J\u001b\u0010$\u001a\u00020\u00112\u0006\u0010 \u001a\u00020#H\u0094@ø\u0001\u0000¢\u0006\u0004\b$\u0010%J\u0017\u0010(\u001a\u00020\u00112\u0006\u0010'\u001a\u00020&H\u0014¢\u0006\u0004\b(\u0010)R\u0014\u0010\u0005\u001a\u00020\u00048\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0005\u0010*R\u0014\u0010\u0007\u001a\u00020\u00068\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0007\u0010+R\u0014\u0010\t\u001a\u00020\b8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\t\u0010,R\u0014\u0010\n\u001a\u00020\b8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\n\u0010,R\u001c\u0010\r\u001a\n\u0012\u0004\u0012\u00020\f\u0018\u00010\u000b8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\r\u0010-R\u0016\u0010'\u001a\u00020&8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b'\u0010.R\u0014\u00100\u001a\u00020/8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b0\u00101R\u0018\u00102\u001a\u0004\u0018\u00010\u00048\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b2\u0010*R\u0018\u00104\u001a\u0004\u0018\u0001038\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b4\u00105R\u001a\u00107\u001a\u0002068\u0016X\u0096\u0004¢\u0006\f\n\u0004\b7\u00108\u001a\u0004\b9\u0010:\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006;"}, d2 = {"Lio/ktor/server/cio/CIOApplicationResponse;", "Lio/ktor/server/engine/BaseApplicationResponse;", "Lio/ktor/server/cio/CIOApplicationCall;", "call", "Lio/ktor/utils/io/ByteWriteChannel;", "output", "Lio/ktor/utils/io/ByteReadChannel;", "input", "Ld7/j;", "engineDispatcher", "userDispatcher", "Lka/r;", "", "upgraded", "<init>", "(Lio/ktor/server/cio/CIOApplicationCall;Lio/ktor/utils/io/ByteWriteChannel;Lio/ktor/utils/io/ByteReadChannel;Ld7/j;Ld7/j;Lka/r;)V", "contentReady", "Lx6/t0;", "sendResponseMessage", "(ZLd7/d;)Ljava/lang/Object;", "preparedBodyChannel", "(Ld7/d;)Ljava/lang/Object;", "responseChannel", "Lio/ktor/http/content/OutgoingContent$ProtocolUpgrade;", "upgrade", "respondUpgrade", "(Lio/ktor/http/content/OutgoingContent$ProtocolUpgrade;Ld7/d;)Ljava/lang/Object;", "", "bytes", "respondFromBytes", "([BLd7/d;)Ljava/lang/Object;", "Lio/ktor/http/content/OutgoingContent$NoContent;", "content", "respondNoContent", "(Lio/ktor/http/content/OutgoingContent$NoContent;Ld7/d;)Ljava/lang/Object;", "Lio/ktor/http/content/OutgoingContent;", "respondOutgoingContent", "(Lio/ktor/http/content/OutgoingContent;Ld7/d;)Ljava/lang/Object;", "Lio/ktor/http/HttpStatusCode;", "statusCode", "setStatus", "(Lio/ktor/http/HttpStatusCode;)V", "Lio/ktor/utils/io/ByteWriteChannel;", "Lio/ktor/utils/io/ByteReadChannel;", "Ld7/j;", "Lka/r;", "Lio/ktor/http/HttpStatusCode;", "Lio/ktor/http/HeadersBuilder;", "headersBuilder", "Lio/ktor/http/HeadersBuilder;", "chunkedChannel", "Lka/v1;", "chunkedJob", "Lka/v1;", "Lio/ktor/server/response/ResponseHeaders;", "headers", "Lio/ktor/server/response/ResponseHeaders;", "getHeaders", "()Lio/ktor/server/response/ResponseHeaders;", "ktor-server-cio"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class CIOApplicationResponse extends BaseApplicationResponse {
    private ByteWriteChannel chunkedChannel;
    private v1 chunkedJob;
    private final j engineDispatcher;
    private final ResponseHeaders headers;
    private final HeadersBuilder headersBuilder;
    private final ByteReadChannel input;
    private final ByteWriteChannel output;
    private HttpStatusCode statusCode;
    private final r<Boolean> upgraded;
    private final j userDispatcher;

    /* JADX INFO: renamed from: io.ktor.server.cio.CIOApplicationResponse$preparedBodyChannel$1, reason: invalid class name */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    @e(c = "io.ktor.server.cio.CIOApplicationResponse", f = "CIOApplicationResponse.kt", l = {119}, m = "preparedBodyChannel")
    public static final class AnonymousClass1 extends c {
        Object L$0;
        int label;
        /* synthetic */ Object result;

        public AnonymousClass1(d<? super AnonymousClass1> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return CIOApplicationResponse.this.preparedBodyChannel(this);
        }
    }

    /* JADX INFO: renamed from: io.ktor.server.cio.CIOApplicationResponse$respondFromBytes$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    @e(c = "io.ktor.server.cio.CIOApplicationResponse", f = "CIOApplicationResponse.kt", l = {ColorSpace.MaxId, 64, 65}, m = "respondFromBytes")
    public static final class C18631 extends c {
        Object L$0;
        Object L$1;
        int label;
        /* synthetic */ Object result;

        public C18631(d<? super C18631> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return CIOApplicationResponse.this.respondFromBytes(null, this);
        }
    }

    /* JADX INFO: renamed from: io.ktor.server.cio.CIOApplicationResponse$respondFromBytes$2, reason: invalid class name */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {1, 8, 0})
    @e(c = "io.ktor.server.cio.CIOApplicationResponse$respondFromBytes$2", f = "CIOApplicationResponse.kt", l = {66}, m = "invokeSuspend")
    public static final class AnonymousClass2 extends f7.j implements p<k0, d<? super t0>, Object> {
        final /* synthetic */ byte[] $bytes;
        final /* synthetic */ ByteWriteChannel $channel;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass2(ByteWriteChannel byteWriteChannel, byte[] bArr, d<? super AnonymousClass2> dVar) {
            super(2, dVar);
            this.$channel = byteWriteChannel;
            this.$bytes = bArr;
        }

        @Override // f7.a
        public final d<t0> create(Object obj, d<?> dVar) {
            return new AnonymousClass2(this.$channel, this.$bytes, dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            int i10 = this.label;
            if (i10 == 0) {
                k2.c.G(obj);
                ByteWriteChannel byteWriteChannel = this.$channel;
                byte[] bArr = this.$bytes;
                this.label = 1;
                Object objWriteFully = ByteWriteChannelKt.writeFully(byteWriteChannel, bArr, this);
                a aVar = a.f15033i;
                if (objWriteFully == aVar) {
                    return aVar;
                }
            } else {
                if (i10 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                k2.c.G(obj);
            }
            ByteWriteChannelKt.close(this.$channel);
            return t0.f22605a;
        }

        @Override // r7.p
        public final Object invoke(k0 k0Var, d<? super t0> dVar) {
            return ((AnonymousClass2) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
        }
    }

    /* JADX INFO: renamed from: io.ktor.server.cio.CIOApplicationResponse$respondNoContent$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    @e(c = "io.ktor.server.cio.CIOApplicationResponse", f = "CIOApplicationResponse.kt", l = {72}, m = "respondNoContent")
    public static final class C18641 extends c {
        Object L$0;
        int label;
        /* synthetic */ Object result;

        public C18641(d<? super C18641> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return CIOApplicationResponse.this.respondNoContent(null, this);
        }
    }

    /* JADX INFO: renamed from: io.ktor.server.cio.CIOApplicationResponse$respondOutgoingContent$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    @e(c = "io.ktor.server.cio.CIOApplicationResponse", f = "CIOApplicationResponse.kt", l = {86, 88}, m = "respondOutgoingContent")
    public static final class C18651 extends c {
        Object L$0;
        int label;
        /* synthetic */ Object result;

        public C18651(d<? super C18651> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return CIOApplicationResponse.this.respondOutgoingContent(null, this);
        }
    }

    /* JADX INFO: renamed from: io.ktor.server.cio.CIOApplicationResponse$respondUpgrade$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    @e(c = "io.ktor.server.cio.CIOApplicationResponse", f = "CIOApplicationResponse.kt", l = {55, 57, 59}, m = "respondUpgrade")
    public static final class C18661 extends c {
        Object L$0;
        Object L$1;
        int label;
        /* synthetic */ Object result;

        public C18661(d<? super C18661> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return CIOApplicationResponse.this.respondUpgrade(null, this);
        }
    }

    /* JADX INFO: renamed from: io.ktor.server.cio.CIOApplicationResponse$respondUpgrade$2, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\u000e\n\u0002\u0010\u0003\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0005\u001a\u00020\u00022\b\u0010\u0001\u001a\u0004\u0018\u00010\u0000H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"", "it", "Lx6/t0;", "invoke", "(Ljava/lang/Throwable;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class C18672 extends kotlin.jvm.internal.r implements l<Throwable, t0> {
        public C18672() {
            super(1);
        }

        @Override // r7.l
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            invoke((Throwable) obj);
            return t0.f22605a;
        }

        public final void invoke(Throwable th) {
            ByteWriteChannelKt.close(CIOApplicationResponse.this.output);
            ByteReadChannelKt.cancel(CIOApplicationResponse.this.input);
        }
    }

    /* JADX INFO: renamed from: io.ktor.server.cio.CIOApplicationResponse$responseChannel$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    @e(c = "io.ktor.server.cio.CIOApplicationResponse", f = "CIOApplicationResponse.kt", l = {50, 51}, m = "responseChannel")
    public static final class C18681 extends c {
        Object L$0;
        int label;
        /* synthetic */ Object result;

        public C18681(d<? super C18681> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return CIOApplicationResponse.this.responseChannel(this);
        }
    }

    /* JADX INFO: renamed from: io.ktor.server.cio.CIOApplicationResponse$sendResponseMessage$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    @e(c = "io.ktor.server.cio.CIOApplicationResponse", f = "CIOApplicationResponse.kt", l = {105}, m = "sendResponseMessage")
    public static final class C18691 extends c {
        Object L$0;
        Object L$1;
        boolean Z$0;
        int label;
        /* synthetic */ Object result;

        public C18691(d<? super C18691> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return CIOApplicationResponse.this.sendResponseMessage(false, this);
        }
    }

    public CIOApplicationResponse(CIOApplicationCall cIOApplicationCall, ByteWriteChannel byteWriteChannel, ByteReadChannel byteReadChannel, j jVar, j jVar2, r<Boolean> rVar) {
        super(cIOApplicationCall);
        this.output = byteWriteChannel;
        this.input = byteReadChannel;
        this.engineDispatcher = jVar;
        this.userDispatcher = jVar2;
        this.upgraded = rVar;
        this.statusCode = HttpStatusCode.INSTANCE.getOK();
        this.headersBuilder = new HeadersBuilder(0, 1, null);
        this.headers = new ResponseHeaders() { // from class: io.ktor.server.cio.CIOApplicationResponse$headers$1
            @Override // io.ktor.server.response.ResponseHeaders
            public void engineAppendHeader(String name, String value) {
                this.this$0.headersBuilder.append(name, value);
            }

            @Override // io.ktor.server.response.ResponseHeaders
            public List<String> getEngineHeaderNames() {
                return x.c1(this.this$0.headersBuilder.names());
            }

            @Override // io.ktor.server.response.ResponseHeaders
            public List<String> getEngineHeaderValues(String name) {
                List<String> all = this.this$0.headersBuilder.getAll(name);
                return all == null ? z.f19728i : all;
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object preparedBodyChannel(d7.d<? super io.ktor.utils.io.ByteWriteChannel> r5) {
        /*
            r4 = this;
            boolean r0 = r5 instanceof io.ktor.server.cio.CIOApplicationResponse.AnonymousClass1
            if (r0 == 0) goto L13
            r0 = r5
            io.ktor.server.cio.CIOApplicationResponse$preparedBodyChannel$1 r0 = (io.ktor.server.cio.CIOApplicationResponse.AnonymousClass1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            io.ktor.server.cio.CIOApplicationResponse$preparedBodyChannel$1 r0 = new io.ktor.server.cio.CIOApplicationResponse$preparedBodyChannel$1
            r0.<init>(r5)
        L18:
            java.lang.Object r5 = r0.result
            int r1 = r0.label
            r2 = 1
            if (r1 == 0) goto L31
            if (r1 != r2) goto L29
            java.lang.Object r0 = r0.L$0
            io.ktor.server.cio.CIOApplicationResponse r0 = (io.ktor.server.cio.CIOApplicationResponse) r0
            k2.c.G(r5)
            goto L5f
        L29:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r0)
            throw r5
        L31:
            k2.c.G(r5)
            io.ktor.server.response.ResponseHeaders r5 = r4.getHeaders()
            io.ktor.http.HttpHeaders r1 = io.ktor.http.HttpHeaders.INSTANCE
            java.lang.String r1 = r1.getTransferEncoding()
            java.lang.String r5 = r5.get(r1)
            java.lang.String r1 = "chunked"
            boolean r5 = kotlin.jvm.internal.p.a(r5, r1)
            if (r5 != 0) goto L4d
            io.ktor.utils.io.ByteWriteChannel r5 = r4.output
            return r5
        L4d:
            io.ktor.utils.io.ByteWriteChannel r5 = r4.output
            ka.v2 r1 = ka.x0.f19654c
            r0.L$0 = r4
            r0.label = r2
            java.lang.Object r5 = io.ktor.http.cio.ChunkedTransferEncodingKt.encodeChunked(r5, r1, r0)
            e7.a r0 = e7.a.f15033i
            if (r5 != r0) goto L5e
            return r0
        L5e:
            r0 = r4
        L5f:
            io.ktor.utils.io.ReaderJob r5 = (io.ktor.utils.io.ReaderJob) r5
            io.ktor.utils.io.ByteWriteChannel r1 = r5.getChannel()
            r0.chunkedChannel = r1
            r0.chunkedJob = r5
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.server.cio.CIOApplicationResponse.preparedBodyChannel(d7.d):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /* JADX WARN: Type inference failed for: r1v0, types: [int] */
    /* JADX WARN: Type inference failed for: r1v3, types: [io.ktor.http.cio.RequestResponseBuilder] */
    /* JADX WARN: Type inference failed for: r1v8 */
    /* JADX WARN: Type inference failed for: r1v9 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object sendResponseMessage(boolean r7, d7.d<? super x6.t0> r8) {
        /*
            r6 = this;
            boolean r0 = r8 instanceof io.ktor.server.cio.CIOApplicationResponse.C18691
            if (r0 == 0) goto L13
            r0 = r8
            io.ktor.server.cio.CIOApplicationResponse$sendResponseMessage$1 r0 = (io.ktor.server.cio.CIOApplicationResponse.C18691) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            io.ktor.server.cio.CIOApplicationResponse$sendResponseMessage$1 r0 = new io.ktor.server.cio.CIOApplicationResponse$sendResponseMessage$1
            r0.<init>(r8)
        L18:
            java.lang.Object r8 = r0.result
            int r1 = r0.label
            r2 = 1
            if (r1 == 0) goto L3a
            if (r1 != r2) goto L32
            boolean r7 = r0.Z$0
            java.lang.Object r1 = r0.L$1
            io.ktor.http.cio.RequestResponseBuilder r1 = (io.ktor.http.cio.RequestResponseBuilder) r1
            java.lang.Object r0 = r0.L$0
            io.ktor.server.cio.CIOApplicationResponse r0 = (io.ktor.server.cio.CIOApplicationResponse) r0
            k2.c.G(r8)     // Catch: java.lang.Throwable -> L2f
            goto L9e
        L2f:
            r7 = move-exception
            goto Lab
        L32:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            java.lang.String r8 = "call to 'resume' before 'invoke' with coroutine"
            r7.<init>(r8)
            throw r7
        L3a:
            k2.c.G(r8)
            io.ktor.http.cio.RequestResponseBuilder r1 = new io.ktor.http.cio.RequestResponseBuilder
            r1.<init>()
            java.lang.String r8 = "HTTP/1.1"
            io.ktor.http.HttpStatusCode r3 = r6.statusCode     // Catch: java.lang.Throwable -> L2f
            int r3 = r3.getValue()     // Catch: java.lang.Throwable -> L2f
            io.ktor.http.HttpStatusCode r4 = r6.statusCode     // Catch: java.lang.Throwable -> L2f
            java.lang.String r4 = r4.getDescription()     // Catch: java.lang.Throwable -> L2f
            r1.responseLine(r8, r3, r4)     // Catch: java.lang.Throwable -> L2f
            io.ktor.http.HeadersBuilder r8 = r6.headersBuilder     // Catch: java.lang.Throwable -> L2f
            java.util.Set r8 = r8.names()     // Catch: java.lang.Throwable -> L2f
            java.util.Iterator r8 = r8.iterator()     // Catch: java.lang.Throwable -> L2f
        L5d:
            boolean r3 = r8.hasNext()     // Catch: java.lang.Throwable -> L2f
            if (r3 == 0) goto L83
            java.lang.Object r3 = r8.next()     // Catch: java.lang.Throwable -> L2f
            java.lang.String r3 = (java.lang.String) r3     // Catch: java.lang.Throwable -> L2f
            io.ktor.http.HeadersBuilder r4 = r6.headersBuilder     // Catch: java.lang.Throwable -> L2f
            java.util.List r4 = r4.getAll(r3)     // Catch: java.lang.Throwable -> L2f
            java.util.Iterator r4 = r4.iterator()     // Catch: java.lang.Throwable -> L2f
        L73:
            boolean r5 = r4.hasNext()     // Catch: java.lang.Throwable -> L2f
            if (r5 == 0) goto L5d
            java.lang.Object r5 = r4.next()     // Catch: java.lang.Throwable -> L2f
            java.lang.String r5 = (java.lang.String) r5     // Catch: java.lang.Throwable -> L2f
            r1.headerLine(r3, r5)     // Catch: java.lang.Throwable -> L2f
            goto L73
        L83:
            r1.emptyLine()     // Catch: java.lang.Throwable -> L2f
            io.ktor.utils.io.ByteWriteChannel r8 = r6.output     // Catch: java.lang.Throwable -> L2f
            io.ktor.utils.io.core.ByteReadPacket r3 = r1.build()     // Catch: java.lang.Throwable -> L2f
            r0.L$0 = r6     // Catch: java.lang.Throwable -> L2f
            r0.L$1 = r1     // Catch: java.lang.Throwable -> L2f
            r0.Z$0 = r7     // Catch: java.lang.Throwable -> L2f
            r0.label = r2     // Catch: java.lang.Throwable -> L2f
            java.lang.Object r8 = r8.writePacket(r3, r0)     // Catch: java.lang.Throwable -> L2f
            e7.a r0 = e7.a.f15033i
            if (r8 != r0) goto L9d
            return r0
        L9d:
            r0 = r6
        L9e:
            if (r7 != 0) goto La5
            io.ktor.utils.io.ByteWriteChannel r7 = r0.output     // Catch: java.lang.Throwable -> L2f
            r7.flush()     // Catch: java.lang.Throwable -> L2f
        La5:
            r1.release()
            x6.t0 r7 = x6.t0.f22605a
            return r7
        Lab:
            r1.release()
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.server.cio.CIOApplicationResponse.sendResponseMessage(boolean, d7.d):java.lang.Object");
    }

    @Override // io.ktor.server.response.ApplicationResponse
    public ResponseHeaders getHeaders() {
        return this.headers;
    }

    /* JADX WARN: Code restructure failed: missing block: B:26:0x0079, code lost:
    
        if (ka.m0.y(r1, r3, r0) != r6) goto L28;
     */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    @Override // io.ktor.server.engine.BaseApplicationResponse
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.Object respondFromBytes(byte[] r8, d7.d<? super x6.t0> r9) {
        /*
            r7 = this;
            boolean r0 = r9 instanceof io.ktor.server.cio.CIOApplicationResponse.C18631
            if (r0 == 0) goto L13
            r0 = r9
            io.ktor.server.cio.CIOApplicationResponse$respondFromBytes$1 r0 = (io.ktor.server.cio.CIOApplicationResponse.C18631) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            io.ktor.server.cio.CIOApplicationResponse$respondFromBytes$1 r0 = new io.ktor.server.cio.CIOApplicationResponse$respondFromBytes$1
            r0.<init>(r9)
        L18:
            java.lang.Object r9 = r0.result
            int r1 = r0.label
            r2 = 3
            r3 = 2
            r4 = 0
            r5 = 1
            e7.a r6 = e7.a.f15033i
            if (r1 == 0) goto L4a
            if (r1 == r5) goto L3e
            if (r1 == r3) goto L36
            if (r1 != r2) goto L2e
            k2.c.G(r9)
            goto L7c
        L2e:
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
            java.lang.String r9 = "call to 'resume' before 'invoke' with coroutine"
            r8.<init>(r9)
            throw r8
        L36:
            java.lang.Object r8 = r0.L$0
            byte[] r8 = (byte[]) r8
            k2.c.G(r9)
            goto L68
        L3e:
            java.lang.Object r8 = r0.L$1
            byte[] r8 = (byte[]) r8
            java.lang.Object r1 = r0.L$0
            io.ktor.server.cio.CIOApplicationResponse r1 = (io.ktor.server.cio.CIOApplicationResponse) r1
            k2.c.G(r9)
            goto L5b
        L4a:
            k2.c.G(r9)
            r0.L$0 = r7
            r0.L$1 = r8
            r0.label = r5
            java.lang.Object r9 = r7.sendResponseMessage(r5, r0)
            if (r9 != r6) goto L5a
            goto L7b
        L5a:
            r1 = r7
        L5b:
            r0.L$0 = r8
            r0.L$1 = r4
            r0.label = r3
            java.lang.Object r9 = r1.preparedBodyChannel(r0)
            if (r9 != r6) goto L68
            goto L7b
        L68:
            io.ktor.utils.io.ByteWriteChannel r9 = (io.ktor.utils.io.ByteWriteChannel) r9
            ka.v2 r1 = ka.x0.f19654c
            io.ktor.server.cio.CIOApplicationResponse$respondFromBytes$2 r3 = new io.ktor.server.cio.CIOApplicationResponse$respondFromBytes$2
            r3.<init>(r9, r8, r4)
            r0.L$0 = r4
            r0.label = r2
            java.lang.Object r8 = ka.m0.y(r1, r3, r0)
            if (r8 != r6) goto L7c
        L7b:
            return r6
        L7c:
            x6.t0 r8 = x6.t0.f22605a
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.server.cio.CIOApplicationResponse.respondFromBytes(byte[], d7.d):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    @Override // io.ktor.server.engine.BaseApplicationResponse
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.Object respondNoContent(io.ktor.http.content.OutgoingContent.NoContent r4, d7.d<? super x6.t0> r5) {
        /*
            r3 = this;
            boolean r4 = r5 instanceof io.ktor.server.cio.CIOApplicationResponse.C18641
            if (r4 == 0) goto L13
            r4 = r5
            io.ktor.server.cio.CIOApplicationResponse$respondNoContent$1 r4 = (io.ktor.server.cio.CIOApplicationResponse.C18641) r4
            int r0 = r4.label
            r1 = -2147483648(0xffffffff80000000, float:-0.0)
            r2 = r0 & r1
            if (r2 == 0) goto L13
            int r0 = r0 - r1
            r4.label = r0
            goto L18
        L13:
            io.ktor.server.cio.CIOApplicationResponse$respondNoContent$1 r4 = new io.ktor.server.cio.CIOApplicationResponse$respondNoContent$1
            r4.<init>(r5)
        L18:
            java.lang.Object r5 = r4.result
            int r0 = r4.label
            r1 = 1
            if (r0 == 0) goto L31
            if (r0 != r1) goto L29
            java.lang.Object r4 = r4.L$0
            io.ktor.server.cio.CIOApplicationResponse r4 = (io.ktor.server.cio.CIOApplicationResponse) r4
            k2.c.G(r5)
            goto L42
        L29:
            java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
            java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
            r4.<init>(r5)
            throw r4
        L31:
            k2.c.G(r5)
            r4.L$0 = r3
            r4.label = r1
            java.lang.Object r4 = r3.sendResponseMessage(r1, r4)
            e7.a r5 = e7.a.f15033i
            if (r4 != r5) goto L41
            return r5
        L41:
            r4 = r3
        L42:
            io.ktor.utils.io.ByteWriteChannel r4 = r4.output
            io.ktor.utils.io.ByteWriteChannelKt.close(r4)
            x6.t0 r4 = x6.t0.f22605a
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.server.cio.CIOApplicationResponse.respondNoContent(io.ktor.http.content.OutgoingContent$NoContent, d7.d):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    @Override // io.ktor.server.engine.BaseApplicationResponse
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.Object respondOutgoingContent(io.ktor.http.content.OutgoingContent r7, d7.d<? super x6.t0> r8) {
        /*
            r6 = this;
            boolean r0 = r8 instanceof io.ktor.server.cio.CIOApplicationResponse.C18651
            if (r0 == 0) goto L13
            r0 = r8
            io.ktor.server.cio.CIOApplicationResponse$respondOutgoingContent$1 r0 = (io.ktor.server.cio.CIOApplicationResponse.C18651) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            io.ktor.server.cio.CIOApplicationResponse$respondOutgoingContent$1 r0 = new io.ktor.server.cio.CIOApplicationResponse$respondOutgoingContent$1
            r0.<init>(r8)
        L18:
            java.lang.Object r8 = r0.result
            int r1 = r0.label
            x6.t0 r2 = x6.t0.f22605a
            r3 = 2
            r4 = 1
            e7.a r5 = e7.a.f15033i
            if (r1 == 0) goto L3c
            if (r1 == r4) goto L34
            if (r1 != r3) goto L2c
            k2.c.G(r8)
            goto L81
        L2c:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            java.lang.String r8 = "call to 'resume' before 'invoke' with coroutine"
            r7.<init>(r8)
            throw r7
        L34:
            java.lang.Object r7 = r0.L$0
            io.ktor.server.cio.CIOApplicationResponse r7 = (io.ktor.server.cio.CIOApplicationResponse) r7
            k2.c.G(r8)
            goto L6a
        L3c:
            k2.c.G(r8)
            boolean r8 = r7 instanceof io.ktor.http.content.OutgoingContent.ProtocolUpgrade
            if (r8 == 0) goto L55
            ka.r<java.lang.Boolean> r8 = r6.upgraded
            if (r8 == 0) goto L4d
            java.lang.Boolean r1 = java.lang.Boolean.TRUE
            r8.R(r1)
            goto L5e
        L4d:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            java.lang.String r8 = "Unable to perform upgrade as it is not requested by the client: request should have Upgrade and Connection headers filled properly"
            r7.<init>(r8)
            throw r7
        L55:
            ka.r<java.lang.Boolean> r8 = r6.upgraded
            if (r8 == 0) goto L5e
            java.lang.Boolean r1 = java.lang.Boolean.FALSE
            r8.R(r1)
        L5e:
            r0.L$0 = r6
            r0.label = r4
            java.lang.Object r7 = super.respondOutgoingContent(r7, r0)
            if (r7 != r5) goto L69
            goto L80
        L69:
            r7 = r6
        L6a:
            io.ktor.utils.io.ByteWriteChannel r8 = r7.chunkedChannel
            if (r8 == 0) goto L71
            io.ktor.utils.io.ByteWriteChannelKt.close(r8)
        L71:
            ka.v1 r7 = r7.chunkedJob
            if (r7 == 0) goto L81
            r8 = 0
            r0.L$0 = r8
            r0.label = r3
            java.lang.Object r7 = r7.join(r0)
            if (r7 != r5) goto L81
        L80:
            return r5
        L81:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.server.cio.CIOApplicationResponse.respondOutgoingContent(io.ktor.http.content.OutgoingContent, d7.d):java.lang.Object");
    }

    /* JADX WARN: Code restructure failed: missing block: B:28:0x0087, code lost:
    
        if (r12.join(r6) != r9) goto L30;
     */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0014  */
    @Override // io.ktor.server.engine.BaseApplicationResponse
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.Object respondUpgrade(io.ktor.http.content.OutgoingContent.ProtocolUpgrade r11, d7.d<? super x6.t0> r12) {
        /*
            r10 = this;
            boolean r0 = r12 instanceof io.ktor.server.cio.CIOApplicationResponse.C18661
            if (r0 == 0) goto L14
            r0 = r12
            io.ktor.server.cio.CIOApplicationResponse$respondUpgrade$1 r0 = (io.ktor.server.cio.CIOApplicationResponse.C18661) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L14
            int r1 = r1 - r2
            r0.label = r1
        L12:
            r6 = r0
            goto L1a
        L14:
            io.ktor.server.cio.CIOApplicationResponse$respondUpgrade$1 r0 = new io.ktor.server.cio.CIOApplicationResponse$respondUpgrade$1
            r0.<init>(r12)
            goto L12
        L1a:
            java.lang.Object r12 = r6.result
            int r0 = r6.label
            r7 = 0
            r8 = 3
            r1 = 2
            r2 = 1
            e7.a r9 = e7.a.f15033i
            if (r0 == 0) goto L4c
            if (r0 == r2) goto L40
            if (r0 == r1) goto L38
            if (r0 != r8) goto L30
            k2.c.G(r12)
            goto L8a
        L30:
            java.lang.IllegalStateException r11 = new java.lang.IllegalStateException
            java.lang.String r12 = "call to 'resume' before 'invoke' with coroutine"
            r11.<init>(r12)
            throw r11
        L38:
            java.lang.Object r11 = r6.L$0
            io.ktor.server.cio.CIOApplicationResponse r11 = (io.ktor.server.cio.CIOApplicationResponse) r11
            k2.c.G(r12)
            goto L75
        L40:
            java.lang.Object r11 = r6.L$1
            io.ktor.http.content.OutgoingContent$ProtocolUpgrade r11 = (io.ktor.http.content.OutgoingContent.ProtocolUpgrade) r11
            java.lang.Object r0 = r6.L$0
            io.ktor.server.cio.CIOApplicationResponse r0 = (io.ktor.server.cio.CIOApplicationResponse) r0
            k2.c.G(r12)
            goto L5e
        L4c:
            k2.c.G(r12)
            r6.L$0 = r10
            r6.L$1 = r11
            r6.label = r2
            r12 = 0
            java.lang.Object r12 = r10.sendResponseMessage(r12, r6)
            if (r12 != r9) goto L5d
            goto L89
        L5d:
            r0 = r10
        L5e:
            io.ktor.utils.io.ByteReadChannel r2 = r0.input
            io.ktor.utils.io.ByteWriteChannel r3 = r0.output
            d7.j r4 = r0.engineDispatcher
            d7.j r5 = r0.userDispatcher
            r6.L$0 = r0
            r6.L$1 = r7
            r6.label = r1
            r1 = r11
            java.lang.Object r12 = r1.upgrade(r2, r3, r4, r5, r6)
            if (r12 != r9) goto L74
            goto L89
        L74:
            r11 = r0
        L75:
            ka.v1 r12 = (ka.v1) r12
            io.ktor.server.cio.CIOApplicationResponse$respondUpgrade$2 r0 = new io.ktor.server.cio.CIOApplicationResponse$respondUpgrade$2
            r0.<init>()
            r12.invokeOnCompletion(r0)
            r6.L$0 = r7
            r6.label = r8
            java.lang.Object r11 = r12.join(r6)
            if (r11 != r9) goto L8a
        L89:
            return r9
        L8a:
            x6.t0 r11 = x6.t0.f22605a
            return r11
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.server.cio.CIOApplicationResponse.respondUpgrade(io.ktor.http.content.OutgoingContent$ProtocolUpgrade, d7.d):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    @Override // io.ktor.server.engine.BaseApplicationResponse
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.Object responseChannel(d7.d<? super io.ktor.utils.io.ByteWriteChannel> r6) {
        /*
            r5 = this;
            boolean r0 = r6 instanceof io.ktor.server.cio.CIOApplicationResponse.C18681
            if (r0 == 0) goto L13
            r0 = r6
            io.ktor.server.cio.CIOApplicationResponse$responseChannel$1 r0 = (io.ktor.server.cio.CIOApplicationResponse.C18681) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            io.ktor.server.cio.CIOApplicationResponse$responseChannel$1 r0 = new io.ktor.server.cio.CIOApplicationResponse$responseChannel$1
            r0.<init>(r6)
        L18:
            java.lang.Object r6 = r0.result
            int r1 = r0.label
            r2 = 2
            r3 = 1
            e7.a r4 = e7.a.f15033i
            if (r1 == 0) goto L3a
            if (r1 == r3) goto L32
            if (r1 != r2) goto L2a
            k2.c.G(r6)
            return r6
        L2a:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r0)
            throw r6
        L32:
            java.lang.Object r1 = r0.L$0
            io.ktor.server.cio.CIOApplicationResponse r1 = (io.ktor.server.cio.CIOApplicationResponse) r1
            k2.c.G(r6)
            goto L4a
        L3a:
            k2.c.G(r6)
            r0.L$0 = r5
            r0.label = r3
            r6 = 0
            java.lang.Object r6 = r5.sendResponseMessage(r6, r0)
            if (r6 != r4) goto L49
            goto L55
        L49:
            r1 = r5
        L4a:
            r6 = 0
            r0.L$0 = r6
            r0.label = r2
            java.lang.Object r6 = r1.preparedBodyChannel(r0)
            if (r6 != r4) goto L56
        L55:
            return r4
        L56:
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.server.cio.CIOApplicationResponse.responseChannel(d7.d):java.lang.Object");
    }

    @Override // io.ktor.server.engine.BaseApplicationResponse
    public void setStatus(HttpStatusCode statusCode) {
        this.statusCode = statusCode;
    }
}
