package io.ktor.http.content;

import androidx.media3.extractor.text.ttml.TtmlNode;
import d7.d;
import e7.a;
import f7.e;
import f7.j;
import io.ktor.http.ContentType;
import io.ktor.http.ContentTypesKt;
import io.ktor.http.HttpStatusCode;
import io.ktor.http.content.OutgoingContent;
import io.ktor.util.cio.OutputStreamAdaptersKt;
import io.ktor.utils.io.ByteWriteChannel;
import java.io.Closeable;
import java.io.Writer;
import java.nio.charset.Charset;
import k2.c;
import kotlin.Metadata;
import kotlin.jvm.internal.h;
import r7.l;
import r7.p;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u000e\u0018\u00002\u00020\u0001BS\u0012'\u0010\b\u001a#\b\u0001\u0012\u0004\u0012\u00020\u0003\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00060\u0002¢\u0006\u0002\b\u0007\u0012\u0006\u0010\n\u001a\u00020\t\u0012\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u000b\u0012\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\rø\u0001\u0000¢\u0006\u0004\b\u000f\u0010\u0010J\u001b\u0010\u0013\u001a\u00020\u00052\u0006\u0010\u0012\u001a\u00020\u0011H\u0096@ø\u0001\u0000¢\u0006\u0004\b\u0013\u0010\u0014R8\u0010\b\u001a#\b\u0001\u0012\u0004\u0012\u00020\u0003\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00060\u0002¢\u0006\u0002\b\u00078\u0002X\u0082\u0004ø\u0001\u0000¢\u0006\u0006\n\u0004\b\b\u0010\u0015R\u001a\u0010\n\u001a\u00020\t8\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\n\u0010\u0016\u001a\u0004\b\u0017\u0010\u0018R\u001c\u0010\f\u001a\u0004\u0018\u00010\u000b8\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\f\u0010\u0019\u001a\u0004\b\u001a\u0010\u001bR\u001c\u0010\u000e\u001a\u0004\u0018\u00010\r8\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\u000e\u0010\u001c\u001a\u0004\b\u001d\u0010\u001e\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u001f"}, d2 = {"Lio/ktor/http/content/WriterContent;", "Lio/ktor/http/content/OutgoingContent$WriteChannelContent;", "Lkotlin/Function2;", "Ljava/io/Writer;", "Ld7/d;", "Lx6/t0;", "", "Lx6/n;", TtmlNode.TAG_BODY, "Lio/ktor/http/ContentType;", "contentType", "Lio/ktor/http/HttpStatusCode;", "status", "", "contentLength", "<init>", "(Lr7/p;Lio/ktor/http/ContentType;Lio/ktor/http/HttpStatusCode;Ljava/lang/Long;)V", "Lio/ktor/utils/io/ByteWriteChannel;", "channel", "writeTo", "(Lio/ktor/utils/io/ByteWriteChannel;Ld7/d;)Ljava/lang/Object;", "Lr7/p;", "Lio/ktor/http/ContentType;", "getContentType", "()Lio/ktor/http/ContentType;", "Lio/ktor/http/HttpStatusCode;", "getStatus", "()Lio/ktor/http/HttpStatusCode;", "Ljava/lang/Long;", "getContentLength", "()Ljava/lang/Long;", "ktor-http"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class WriterContent extends OutgoingContent.WriteChannelContent {
    private final p<Writer, d<? super t0>, Object> body;
    private final Long contentLength;
    private final ContentType contentType;
    private final HttpStatusCode status;

    /* JADX INFO: renamed from: io.ktor.http.content.WriterContent$writeTo$2, reason: invalid class name */
    @Metadata(d1 = {"\u0000\b\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0001\u001a\u00020\u0000H\u008a@¢\u0006\u0004\b\u0001\u0010\u0002"}, d2 = {"Lx6/t0;", "<anonymous>", "()V"}, k = 3, mv = {1, 8, 0})
    @e(c = "io.ktor.http.content.WriterContent$writeTo$2", f = "WriterContent.kt", l = {26}, m = "invokeSuspend")
    public static final class AnonymousClass2 extends j implements l<d<? super t0>, Object> {
        final /* synthetic */ ByteWriteChannel $channel;
        final /* synthetic */ Charset $charset;
        Object L$0;
        int label;
        final /* synthetic */ WriterContent this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass2(ByteWriteChannel byteWriteChannel, Charset charset, WriterContent writerContent, d<? super AnonymousClass2> dVar) {
            super(1, dVar);
            this.$channel = byteWriteChannel;
            this.$charset = charset;
            this.this$0 = writerContent;
        }

        @Override // f7.a
        public final d<t0> create(d<?> dVar) {
            return new AnonymousClass2(this.$channel, this.$charset, this.this$0, dVar);
        }

        @Override // r7.l
        public final Object invoke(d<? super t0> dVar) {
            return ((AnonymousClass2) create(dVar)).invokeSuspend(t0.f22605a);
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r0v0, types: [int] */
        /* JADX WARN: Type inference failed for: r0v1, types: [java.io.Closeable] */
        /* JADX WARN: Type inference failed for: r0v4, types: [java.io.Closeable] */
        /* JADX WARN: Type inference failed for: r0v8 */
        /* JADX WARN: Type inference failed for: r0v9 */
        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            ?? r02 = this.label;
            try {
                if (r02 == 0) {
                    c.G(obj);
                    Writer writer = OutputStreamAdaptersKt.writer(this.$channel, this.$charset);
                    p pVar = this.this$0.body;
                    this.L$0 = writer;
                    this.label = 1;
                    Object objInvoke = pVar.invoke(writer, this);
                    a aVar = a.f15033i;
                    r02 = writer;
                    if (objInvoke == aVar) {
                        return aVar;
                    }
                } else {
                    if (r02 != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    Closeable closeable = (Closeable) this.L$0;
                    c.G(obj);
                    r02 = closeable;
                }
                xc.d.L(r02, null);
                return t0.f22605a;
            } finally {
            }
        }
    }

    public /* synthetic */ WriterContent(p pVar, ContentType contentType, HttpStatusCode httpStatusCode, Long l10, int i10, h hVar) {
        this(pVar, contentType, (i10 & 4) != 0 ? null : httpStatusCode, (i10 & 8) != 0 ? null : l10);
    }

    @Override // io.ktor.http.content.OutgoingContent
    public Long getContentLength() {
        return this.contentLength;
    }

    @Override // io.ktor.http.content.OutgoingContent
    public ContentType getContentType() {
        return this.contentType;
    }

    @Override // io.ktor.http.content.OutgoingContent
    public HttpStatusCode getStatus() {
        return this.status;
    }

    @Override // io.ktor.http.content.OutgoingContent.WriteChannelContent
    public Object writeTo(ByteWriteChannel byteWriteChannel, d<? super t0> dVar) throws Throwable {
        Charset charset = ContentTypesKt.charset(getContentType());
        if (charset == null) {
            charset = kotlin.text.a.f19924a;
        }
        Object objWithBlocking = BlockingBridgeKt.withBlocking(new AnonymousClass2(byteWriteChannel, charset, this, null), dVar);
        return objWithBlocking == a.f15033i ? objWithBlocking : t0.f22605a;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public WriterContent(p<? super Writer, ? super d<? super t0>, ? extends Object> pVar, ContentType contentType, HttpStatusCode httpStatusCode, Long l10) {
        this.body = pVar;
        this.contentType = contentType;
        this.status = httpStatusCode;
        this.contentLength = l10;
    }
}
