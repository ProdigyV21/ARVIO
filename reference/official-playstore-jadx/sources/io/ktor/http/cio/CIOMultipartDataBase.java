package io.ktor.http.cio;

import androidx.media3.container.MdtaMetadataEntry;
import androidx.media3.container.NalUnitUtil;
import androidx.media3.extractor.AacUtil;
import androidx.media3.extractor.ts.TsExtractor;
import d7.d;
import d7.j;
import f7.c;
import f7.e;
import io.ktor.http.cio.MultipartEvent;
import io.ktor.http.content.MultiPartData;
import io.ktor.http.content.PartData;
import io.ktor.util.InternalAPI;
import io.ktor.utils.io.ByteReadChannel;
import io.ktor.utils.io.core.Input;
import java.io.File;
import java.nio.ByteBuffer;
import ka.k0;
import kotlin.Metadata;
import kotlin.jvm.internal.f0;
import kotlin.jvm.internal.h;
import kotlin.jvm.internal.r;
import ma.w;
import r7.a;
import x6.i0;
import x6.s;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@InternalAPI
@Metadata(d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\r\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0007\u0018\u00002\u00020\u00012\u00020\u0002B=\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\b\u001a\u00020\u0007\u0012\b\u0010\n\u001a\u0004\u0018\u00010\t\u0012\b\b\u0002\u0010\f\u001a\u00020\u000b\u0012\b\b\u0002\u0010\r\u001a\u00020\u000b¢\u0006\u0004\b\u000e\u0010\u000fJ\u0015\u0010\u0011\u001a\u0004\u0018\u00010\u0010H\u0082@ø\u0001\u0000¢\u0006\u0004\b\u0011\u0010\u0012J\u001d\u0010\u0015\u001a\u0004\u0018\u00010\u00102\u0006\u0010\u0014\u001a\u00020\u0013H\u0082@ø\u0001\u0000¢\u0006\u0004\b\u0015\u0010\u0016J\u001b\u0010\u0019\u001a\u00020\u00102\u0006\u0010\u0018\u001a\u00020\u0017H\u0082@ø\u0001\u0000¢\u0006\u0004\b\u0019\u0010\u001aJ'\u0010\u001f\u001a\u00020\u00102\u0006\u0010\u0018\u001a\u00020\u00172\u0006\u0010\u001c\u001a\u00020\u001b2\u0006\u0010\u001e\u001a\u00020\u001dH\u0002¢\u0006\u0004\b\u001f\u0010 J+\u0010!\u001a\u00020\u00102\u0006\u0010\u0018\u001a\u00020\u00172\u0006\u0010\u001c\u001a\u00020\u001b2\u0006\u0010\u001e\u001a\u00020\u001dH\u0082@ø\u0001\u0000¢\u0006\u0004\b!\u0010\"J\u0015\u0010#\u001a\u0004\u0018\u00010\u0010H\u0096@ø\u0001\u0000¢\u0006\u0004\b#\u0010\u0012R\u001a\u0010\u0004\u001a\u00020\u00038\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\u0004\u0010$\u001a\u0004\b%\u0010&R\u0014\u0010\f\u001a\u00020\u000b8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\f\u0010'R\u0014\u0010\r\u001a\u00020\u000b8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\r\u0010'R\u001a\u0010)\u001a\b\u0012\u0004\u0012\u00020\u00130(8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b)\u0010*\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006+"}, d2 = {"Lio/ktor/http/cio/CIOMultipartDataBase;", "Lio/ktor/http/content/MultiPartData;", "Lka/k0;", "Ld7/j;", "coroutineContext", "Lio/ktor/utils/io/ByteReadChannel;", "channel", "", "contentType", "", "contentLength", "", "formFieldLimit", "inMemoryFileUploadLimit", "<init>", "(Ld7/j;Lio/ktor/utils/io/ByteReadChannel;Ljava/lang/CharSequence;Ljava/lang/Long;II)V", "Lio/ktor/http/content/PartData;", "readPartSuspend", "(Ld7/d;)Ljava/lang/Object;", "Lio/ktor/http/cio/MultipartEvent;", "event", "eventToData", "(Lio/ktor/http/cio/MultipartEvent;Ld7/d;)Ljava/lang/Object;", "Lio/ktor/http/cio/MultipartEvent$MultipartPart;", "part", "partToData", "(Lio/ktor/http/cio/MultipartEvent$MultipartPart;Ld7/d;)Ljava/lang/Object;", "Lio/ktor/http/cio/HttpHeadersMap;", "headers", "Ljava/nio/ByteBuffer;", "buffer", "withoutTempFile", "(Lio/ktor/http/cio/MultipartEvent$MultipartPart;Lio/ktor/http/cio/HttpHeadersMap;Ljava/nio/ByteBuffer;)Lio/ktor/http/content/PartData;", "withTempFile", "(Lio/ktor/http/cio/MultipartEvent$MultipartPart;Lio/ktor/http/cio/HttpHeadersMap;Ljava/nio/ByteBuffer;Ld7/d;)Ljava/lang/Object;", "readPart", "Ld7/j;", "getCoroutineContext", "()Ld7/j;", "I", "Lma/w;", "events", "Lma/w;", "ktor-http-cio"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class CIOMultipartDataBase implements MultiPartData, k0 {
    private final j coroutineContext;
    private final w<MultipartEvent> events;
    private final int formFieldLimit;
    private final int inMemoryFileUploadLimit;

    /* JADX INFO: renamed from: io.ktor.http.cio.CIOMultipartDataBase$eventToData$1, reason: invalid class name */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    @e(c = "io.ktor.http.cio.CIOMultipartDataBase", f = "CIOMultipartDataBase.kt", l = {59}, m = "eventToData")
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
            return CIOMultipartDataBase.this.eventToData(null, this);
        }
    }

    /* JADX INFO: renamed from: io.ktor.http.cio.CIOMultipartDataBase$partToData$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    @e(c = "io.ktor.http.cio.CIOMultipartDataBase", f = "CIOMultipartDataBase.kt", l = {72, MdtaMetadataEntry.TYPE_INDICATOR_UNSIGNED_INT64, TsExtractor.TS_STREAM_TYPE_DVBSUBS, 92, 107}, m = "partToData")
    public static final class C18131 extends c {
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        int label;
        /* synthetic */ Object result;

        public C18131(d<? super C18131> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return CIOMultipartDataBase.this.partToData(null, this);
        }
    }

    /* JADX INFO: renamed from: io.ktor.http.cio.CIOMultipartDataBase$partToData$2, reason: invalid class name */
    @Metadata(d1 = {"\u0000\b\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0003\u001a\u00020\u0000H\n¢\u0006\u0004\b\u0001\u0010\u0002"}, d2 = {"Lx6/t0;", "invoke", "()V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class AnonymousClass2 extends r implements a<t0> {
        final /* synthetic */ MultipartEvent.MultipartPart $part;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass2(MultipartEvent.MultipartPart multipartPart) {
            super(0);
            this.$part = multipartPart;
        }

        @Override // r7.a
        public /* bridge */ /* synthetic */ Object invoke() throws Throwable {
            m6676invoke();
            return t0.f22605a;
        }

        /* JADX INFO: renamed from: invoke, reason: collision with other method in class */
        public final void m6676invoke() throws Throwable {
            this.$part.release();
        }
    }

    /* JADX INFO: renamed from: io.ktor.http.cio.CIOMultipartDataBase$partToData$3, reason: invalid class name */
    @Metadata(d1 = {"\u0000\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "Lio/ktor/utils/io/core/Input;", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class AnonymousClass3 extends r implements a<Input> {
        final /* synthetic */ Input $input;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass3(Input input) {
            super(0);
            this.$input = input;
        }

        @Override // r7.a
        public final Input invoke() {
            return this.$input;
        }
    }

    /* JADX INFO: renamed from: io.ktor.http.cio.CIOMultipartDataBase$partToData$4, reason: invalid class name */
    @Metadata(d1 = {"\u0000\b\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0003\u001a\u00020\u0000H\n¢\u0006\u0004\b\u0001\u0010\u0002"}, d2 = {"Lx6/t0;", "invoke", "()V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class AnonymousClass4 extends r implements a<t0> {
        final /* synthetic */ MultipartEvent.MultipartPart $part;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass4(MultipartEvent.MultipartPart multipartPart) {
            super(0);
            this.$part = multipartPart;
        }

        @Override // r7.a
        public /* bridge */ /* synthetic */ Object invoke() throws Throwable {
            m6677invoke();
            return t0.f22605a;
        }

        /* JADX INFO: renamed from: invoke, reason: collision with other method in class */
        public final void m6677invoke() throws Throwable {
            this.$part.release();
        }
    }

    /* JADX INFO: renamed from: io.ktor.http.cio.CIOMultipartDataBase$readPart$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    @e(c = "io.ktor.http.cio.CIOMultipartDataBase", f = "CIOMultipartDataBase.kt", l = {NalUnitUtil.H265_NAL_UNIT_TYPE_PREFIX_SEI, AacUtil.AUDIO_OBJECT_TYPE_AAC_XHE}, m = "readPart")
    public static final class C18141 extends c {
        Object L$0;
        int label;
        /* synthetic */ Object result;

        public C18141(d<? super C18141> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return CIOMultipartDataBase.this.readPart(this);
        }
    }

    /* JADX INFO: renamed from: io.ktor.http.cio.CIOMultipartDataBase$readPartSuspend$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    @e(c = "io.ktor.http.cio.CIOMultipartDataBase", f = "CIOMultipartDataBase.kt", l = {48, 49}, m = "readPartSuspend")
    public static final class C18151 extends c {
        Object L$0;
        int label;
        /* synthetic */ Object result;

        public C18151(d<? super C18151> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return CIOMultipartDataBase.this.readPartSuspend(this);
        }
    }

    /* JADX INFO: renamed from: io.ktor.http.cio.CIOMultipartDataBase$withTempFile$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    @e(c = "io.ktor.http.cio.CIOMultipartDataBase", f = "CIOMultipartDataBase.kt", l = {151}, m = "withTempFile")
    public static final class C18161 extends c {
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        Object L$5;
        Object L$6;
        int label;
        /* synthetic */ Object result;

        public C18161(d<? super C18161> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return CIOMultipartDataBase.this.withTempFile(null, null, null, this);
        }
    }

    /* JADX INFO: renamed from: io.ktor.http.cio.CIOMultipartDataBase$withTempFile$3, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "Lio/ktor/utils/io/core/Input;", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class C18173 extends r implements a<Input> {
        final /* synthetic */ s<Input> $lazyInput;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public C18173(s<? extends Input> sVar) {
            super(0);
            this.$lazyInput = sVar;
        }

        @Override // r7.a
        public final Input invoke() {
            return (Input) this.$lazyInput.getValue();
        }
    }

    /* JADX INFO: renamed from: io.ktor.http.cio.CIOMultipartDataBase$withTempFile$4, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\b\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0003\u001a\u00020\u0000H\n¢\u0006\u0004\b\u0001\u0010\u0002"}, d2 = {"Lx6/t0;", "invoke", "()V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class C18184 extends r implements a<t0> {
        final /* synthetic */ f0 $closed;
        final /* synthetic */ s<Input> $lazyInput;
        final /* synthetic */ MultipartEvent.MultipartPart $part;
        final /* synthetic */ File $tmp;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public C18184(f0 f0Var, s<? extends Input> sVar, MultipartEvent.MultipartPart multipartPart, File file) {
            super(0);
            this.$closed = f0Var;
            this.$lazyInput = sVar;
            this.$part = multipartPart;
            this.$tmp = file;
        }

        @Override // r7.a
        public /* bridge */ /* synthetic */ Object invoke() throws Throwable {
            m6678invoke();
            return t0.f22605a;
        }

        /* JADX INFO: renamed from: invoke, reason: collision with other method in class */
        public final void m6678invoke() throws Throwable {
            this.$closed.f19738i = true;
            if (this.$lazyInput.isInitialized()) {
                ((Input) this.$lazyInput.getValue()).close();
            }
            this.$part.release();
            this.$tmp.delete();
        }
    }

    /* JADX INFO: renamed from: io.ktor.http.cio.CIOMultipartDataBase$withoutTempFile$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "Lio/ktor/utils/io/core/Input;", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class C18191 extends r implements a<Input> {
        final /* synthetic */ s<MultipartInput> $lazyInput;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C18191(s<MultipartInput> sVar) {
            super(0);
            this.$lazyInput = sVar;
        }

        @Override // r7.a
        public final Input invoke() {
            return (Input) this.$lazyInput.getValue();
        }
    }

    /* JADX INFO: renamed from: io.ktor.http.cio.CIOMultipartDataBase$withoutTempFile$2, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\b\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0003\u001a\u00020\u0000H\n¢\u0006\u0004\b\u0001\u0010\u0002"}, d2 = {"Lx6/t0;", "invoke", "()V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class C18202 extends r implements a<t0> {
        final /* synthetic */ f0 $closed;
        final /* synthetic */ s<MultipartInput> $lazyInput;
        final /* synthetic */ MultipartEvent.MultipartPart $part;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C18202(f0 f0Var, s<MultipartInput> sVar, MultipartEvent.MultipartPart multipartPart) {
            super(0);
            this.$closed = f0Var;
            this.$lazyInput = sVar;
            this.$part = multipartPart;
        }

        @Override // r7.a
        public /* bridge */ /* synthetic */ Object invoke() throws Throwable {
            m6679invoke();
            return t0.f22605a;
        }

        /* JADX INFO: renamed from: invoke, reason: collision with other method in class */
        public final void m6679invoke() throws Throwable {
            this.$closed.f19738i = true;
            if (this.$lazyInput.isInitialized()) {
                ((MultipartInput) this.$lazyInput.getValue()).close();
            }
            this.$part.release();
        }
    }

    public CIOMultipartDataBase(j jVar, ByteReadChannel byteReadChannel, CharSequence charSequence, Long l10, int i10, int i11) {
        this.coroutineContext = jVar;
        this.formFieldLimit = i10;
        this.inMemoryFileUploadLimit = i11;
        this.events = MultipartKt.parseMultipart(this, byteReadChannel, charSequence, l10);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object eventToData(io.ktor.http.cio.MultipartEvent r5, d7.d<? super io.ktor.http.content.PartData> r6) {
        /*
            r4 = this;
            boolean r0 = r6 instanceof io.ktor.http.cio.CIOMultipartDataBase.AnonymousClass1
            if (r0 == 0) goto L13
            r0 = r6
            io.ktor.http.cio.CIOMultipartDataBase$eventToData$1 r0 = (io.ktor.http.cio.CIOMultipartDataBase.AnonymousClass1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            io.ktor.http.cio.CIOMultipartDataBase$eventToData$1 r0 = new io.ktor.http.cio.CIOMultipartDataBase$eventToData$1
            r0.<init>(r6)
        L18:
            java.lang.Object r6 = r0.result
            int r1 = r0.label
            r2 = 1
            if (r1 == 0) goto L33
            if (r1 != r2) goto L2b
            java.lang.Object r5 = r0.L$0
            io.ktor.http.cio.MultipartEvent r5 = (io.ktor.http.cio.MultipartEvent) r5
            k2.c.G(r6)     // Catch: java.lang.Throwable -> L29
            goto L4a
        L29:
            r6 = move-exception
            goto L52
        L2b:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r6)
            throw r5
        L33:
            k2.c.G(r6)
            boolean r6 = r5 instanceof io.ktor.http.cio.MultipartEvent.MultipartPart     // Catch: java.lang.Throwable -> L29
            if (r6 == 0) goto L4d
            r6 = r5
            io.ktor.http.cio.MultipartEvent$MultipartPart r6 = (io.ktor.http.cio.MultipartEvent.MultipartPart) r6     // Catch: java.lang.Throwable -> L29
            r0.L$0 = r5     // Catch: java.lang.Throwable -> L29
            r0.label = r2     // Catch: java.lang.Throwable -> L29
            java.lang.Object r6 = r4.partToData(r6, r0)     // Catch: java.lang.Throwable -> L29
            e7.a r0 = e7.a.f15033i
            if (r6 != r0) goto L4a
            return r0
        L4a:
            io.ktor.http.content.PartData r6 = (io.ktor.http.content.PartData) r6     // Catch: java.lang.Throwable -> L29
            return r6
        L4d:
            r5.release()     // Catch: java.lang.Throwable -> L29
            r5 = 0
            return r5
        L52:
            r5.release()
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.http.cio.CIOMultipartDataBase.eventToData(io.ktor.http.cio.MultipartEvent, d7.d):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:52:0x0115  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x0135  */
    /* JADX WARN: Removed duplicated region for block: B:60:0x0139  */
    /* JADX WARN: Removed duplicated region for block: B:61:0x013b A[PHI: r1 r4 r5 r6 r14
      0x013b: PHI (r1v7 io.ktor.http.cio.HttpHeadersMap) = (r1v6 io.ktor.http.cio.HttpHeadersMap), (r1v8 io.ktor.http.cio.HttpHeadersMap) binds: [B:51:0x0113, B:59:0x0137] A[DONT_GENERATE, DONT_INLINE]
      0x013b: PHI (r4v6 io.ktor.http.cio.MultipartEvent$MultipartPart) = (r4v4 io.ktor.http.cio.MultipartEvent$MultipartPart), (r4v8 io.ktor.http.cio.MultipartEvent$MultipartPart) binds: [B:51:0x0113, B:59:0x0137] A[DONT_GENERATE, DONT_INLINE]
      0x013b: PHI (r5v5 io.ktor.http.cio.CIOMultipartDataBase) = (r5v4 io.ktor.http.cio.CIOMultipartDataBase), (r5v8 io.ktor.http.cio.CIOMultipartDataBase) binds: [B:51:0x0113, B:59:0x0137] A[DONT_GENERATE, DONT_INLINE]
      0x013b: PHI (r6v1 boolean) = (r6v0 boolean), (r6v2 boolean) binds: [B:51:0x0113, B:59:0x0137] A[DONT_GENERATE, DONT_INLINE]
      0x013b: PHI (r14v7 java.nio.ByteBuffer) = (r14v6 java.nio.ByteBuffer), (r14v13 java.nio.ByteBuffer) binds: [B:51:0x0113, B:59:0x0137] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:63:0x0140  */
    /* JADX WARN: Removed duplicated region for block: B:65:0x016a  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object partToData(io.ktor.http.cio.MultipartEvent.MultipartPart r14, d7.d<? super io.ktor.http.content.PartData> r15) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 400
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.http.cio.CIOMultipartDataBase.partToData(io.ktor.http.cio.MultipartEvent$MultipartPart, d7.d):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x005c, code lost:
    
        if (r7 != r4) goto L28;
     */
    /* JADX WARN: Removed duplicated region for block: B:23:0x004e  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x004f  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:26:0x005c -> B:28:0x005f). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object readPartSuspend(d7.d<? super io.ktor.http.content.PartData> r7) {
        /*
            r6 = this;
            boolean r0 = r7 instanceof io.ktor.http.cio.CIOMultipartDataBase.C18151
            if (r0 == 0) goto L13
            r0 = r7
            io.ktor.http.cio.CIOMultipartDataBase$readPartSuspend$1 r0 = (io.ktor.http.cio.CIOMultipartDataBase.C18151) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            io.ktor.http.cio.CIOMultipartDataBase$readPartSuspend$1 r0 = new io.ktor.http.cio.CIOMultipartDataBase$readPartSuspend$1
            r0.<init>(r7)
        L18:
            java.lang.Object r7 = r0.result
            int r1 = r0.label
            r2 = 2
            r3 = 1
            e7.a r4 = e7.a.f15033i
            if (r1 == 0) goto L3e
            if (r1 == r3) goto L36
            if (r1 != r2) goto L2e
            java.lang.Object r1 = r0.L$0
            io.ktor.http.cio.CIOMultipartDataBase r1 = (io.ktor.http.cio.CIOMultipartDataBase) r1
            k2.c.G(r7)     // Catch: kotlinx.coroutines.channels.ClosedReceiveChannelException -> L66
            goto L5f
        L2e:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r7.<init>(r0)
            throw r7
        L36:
            java.lang.Object r1 = r0.L$0
            io.ktor.http.cio.CIOMultipartDataBase r1 = (io.ktor.http.cio.CIOMultipartDataBase) r1
            k2.c.G(r7)     // Catch: kotlinx.coroutines.channels.ClosedReceiveChannelException -> L66
            goto L52
        L3e:
            k2.c.G(r7)
            r7 = r6
        L42:
            ma.w<io.ktor.http.cio.MultipartEvent> r1 = r7.events     // Catch: kotlinx.coroutines.channels.ClosedReceiveChannelException -> L66
            r0.L$0 = r7     // Catch: kotlinx.coroutines.channels.ClosedReceiveChannelException -> L66
            r0.label = r3     // Catch: kotlinx.coroutines.channels.ClosedReceiveChannelException -> L66
            java.lang.Object r1 = r1.receive(r0)     // Catch: kotlinx.coroutines.channels.ClosedReceiveChannelException -> L66
            if (r1 != r4) goto L4f
            goto L5e
        L4f:
            r5 = r1
            r1 = r7
            r7 = r5
        L52:
            io.ktor.http.cio.MultipartEvent r7 = (io.ktor.http.cio.MultipartEvent) r7     // Catch: kotlinx.coroutines.channels.ClosedReceiveChannelException -> L66
            r0.L$0 = r1     // Catch: kotlinx.coroutines.channels.ClosedReceiveChannelException -> L66
            r0.label = r2     // Catch: kotlinx.coroutines.channels.ClosedReceiveChannelException -> L66
            java.lang.Object r7 = r1.eventToData(r7, r0)     // Catch: kotlinx.coroutines.channels.ClosedReceiveChannelException -> L66
            if (r7 != r4) goto L5f
        L5e:
            return r4
        L5f:
            io.ktor.http.content.PartData r7 = (io.ktor.http.content.PartData) r7     // Catch: kotlinx.coroutines.channels.ClosedReceiveChannelException -> L66
            if (r7 == 0) goto L64
            return r7
        L64:
            r7 = r1
            goto L42
        L66:
            r7 = 0
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.http.cio.CIOMultipartDataBase.readPartSuspend(d7.d):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:26:0x0079 A[Catch: all -> 0x007d, LOOP:0: B:82:0x0073->B:26:0x0079, LOOP_END, TryCatch #9 {all -> 0x007d, blocks: (B:24:0x0073, B:26:0x0079, B:29:0x0083), top: B:82:0x0073 }] */
    /* JADX WARN: Removed duplicated region for block: B:32:0x00a2 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:33:0x00a3  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x00b1 A[Catch: all -> 0x00b8, TRY_LEAVE, TryCatch #4 {all -> 0x00b8, blocks: (B:34:0x00a8, B:36:0x00b1), top: B:72:0x00a8 }] */
    /* JADX WARN: Removed duplicated region for block: B:40:0x00bd  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /* JADX WARN: Type inference failed for: r0v10 */
    /* JADX WARN: Type inference failed for: r0v11 */
    /* JADX WARN: Type inference failed for: r0v3 */
    /* JADX WARN: Type inference failed for: r0v4, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r0v5, types: [java.io.Closeable] */
    /* JADX WARN: Type inference failed for: r0v7 */
    /* JADX WARN: Type inference failed for: r11v1 */
    /* JADX WARN: Type inference failed for: r11v10, types: [java.io.Closeable] */
    /* JADX WARN: Type inference failed for: r11v3 */
    /* JADX WARN: Type inference failed for: r11v5 */
    /* JADX WARN: Type inference failed for: r11v7 */
    /* JADX WARN: Type inference failed for: r12v14 */
    /* JADX WARN: Type inference failed for: r12v3, types: [java.io.FileOutputStream] */
    /* JADX WARN: Type inference failed for: r12v5, types: [java.io.Closeable] */
    /* JADX WARN: Type inference failed for: r12v7 */
    /* JADX WARN: Type inference failed for: r1v0, types: [int] */
    /* JADX WARN: Type inference failed for: r1v1 */
    /* JADX WARN: Type inference failed for: r1v11 */
    /* JADX WARN: Type inference failed for: r1v12 */
    /* JADX WARN: Type inference failed for: r1v13 */
    /* JADX WARN: Type inference failed for: r1v16, types: [java.io.File] */
    /* JADX WARN: Type inference failed for: r1v2, types: [java.io.File] */
    /* JADX WARN: Type inference failed for: r1v4, types: [java.io.File] */
    /* JADX WARN: Type inference failed for: r1v6 */
    /* JADX WARN: Type inference failed for: r1v7 */
    /* JADX WARN: Type inference failed for: r1v9 */
    /* JADX WARN: Type inference failed for: r4v1 */
    /* JADX WARN: Type inference failed for: r4v2, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r4v5 */
    /* JADX WARN: Type inference failed for: r5v3 */
    /* JADX WARN: Type inference failed for: r5v4, types: [java.io.File] */
    /* JADX WARN: Type inference failed for: r5v7 */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:33:0x00a3 -> B:72:0x00a8). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object withTempFile(io.ktor.http.cio.MultipartEvent.MultipartPart r9, io.ktor.http.cio.HttpHeadersMap r10, java.nio.ByteBuffer r11, d7.d<? super io.ktor.http.content.PartData> r12) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 263
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.http.cio.CIOMultipartDataBase.withTempFile(io.ktor.http.cio.MultipartEvent$MultipartPart, io.ktor.http.cio.HttpHeadersMap, java.nio.ByteBuffer, d7.d):java.lang.Object");
    }

    private final PartData withoutTempFile(MultipartEvent.MultipartPart part, HttpHeadersMap headers, ByteBuffer buffer) {
        f0 f0Var = new f0();
        i0 i0Var = new i0(new CIOMultipartDataBase$withoutTempFile$lazyInput$1(f0Var, buffer, part));
        return new PartData.FileItem(new C18191(i0Var), new C18202(f0Var, i0Var, part), new CIOHeaders(headers));
    }

    @Override // ka.k0
    public j getCoroutineContext() {
        return this.coroutineContext;
    }

    /* JADX WARN: Code restructure failed: missing block: B:25:0x0061, code lost:
    
        if (r6 == r4) goto L26;
     */
    /* JADX WARN: Removed duplicated region for block: B:20:0x004c  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x0059  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:25:0x0061 -> B:27:0x0064). Please report as a decompilation issue!!! */
    @Override // io.ktor.http.content.MultiPartData
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.Object readPart(d7.d<? super io.ktor.http.content.PartData> r6) {
        /*
            r5 = this;
            boolean r0 = r6 instanceof io.ktor.http.cio.CIOMultipartDataBase.C18141
            if (r0 == 0) goto L13
            r0 = r6
            io.ktor.http.cio.CIOMultipartDataBase$readPart$1 r0 = (io.ktor.http.cio.CIOMultipartDataBase.C18141) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            io.ktor.http.cio.CIOMultipartDataBase$readPart$1 r0 = new io.ktor.http.cio.CIOMultipartDataBase$readPart$1
            r0.<init>(r6)
        L18:
            java.lang.Object r6 = r0.result
            int r1 = r0.label
            r2 = 2
            r3 = 1
            if (r1 == 0) goto L38
            if (r1 == r3) goto L30
            if (r1 != r2) goto L28
            k2.c.G(r6)
            return r6
        L28:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r0)
            throw r6
        L30:
            java.lang.Object r1 = r0.L$0
            io.ktor.http.cio.CIOMultipartDataBase r1 = (io.ktor.http.cio.CIOMultipartDataBase) r1
            k2.c.G(r6)
            goto L64
        L38:
            k2.c.G(r6)
            r1 = r5
        L3c:
            ma.w<io.ktor.http.cio.MultipartEvent> r6 = r1.events
            java.lang.Object r6 = r6.b()
            java.lang.Object r6 = ma.o.a(r6)
            io.ktor.http.cio.MultipartEvent r6 = (io.ktor.http.cio.MultipartEvent) r6
            e7.a r4 = e7.a.f15033i
            if (r6 != 0) goto L59
            r6 = 0
            r0.L$0 = r6
            r0.label = r2
            java.lang.Object r6 = r1.readPartSuspend(r0)
            if (r6 != r4) goto L58
            goto L63
        L58:
            return r6
        L59:
            r0.L$0 = r1
            r0.label = r3
            java.lang.Object r6 = r1.eventToData(r6, r0)
            if (r6 != r4) goto L64
        L63:
            return r4
        L64:
            io.ktor.http.content.PartData r6 = (io.ktor.http.content.PartData) r6
            if (r6 == 0) goto L3c
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.http.cio.CIOMultipartDataBase.readPart(d7.d):java.lang.Object");
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public /* synthetic */ CIOMultipartDataBase(j jVar, ByteReadChannel byteReadChannel, CharSequence charSequence, Long l10, int i10, int i11, int i12, h hVar) {
        int i13 = (i12 & 16) != 0 ? 65536 : i10;
        this(jVar, byteReadChannel, charSequence, l10, i13, (i12 & 32) != 0 ? i13 : i11);
    }
}
