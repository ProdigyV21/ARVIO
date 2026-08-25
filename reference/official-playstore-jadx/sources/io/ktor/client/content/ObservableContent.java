package io.ktor.client.content;

import d7.d;
import d7.j;
import io.ktor.client.call.UnsupportedContentTypeException;
import io.ktor.client.utils.ByteChannelUtilsKt;
import io.ktor.http.ContentDisposition;
import io.ktor.http.ContentType;
import io.ktor.http.Headers;
import io.ktor.http.HttpStatusCode;
import io.ktor.http.content.OutgoingContent;
import io.ktor.util.AttributeKey;
import io.ktor.utils.io.ByteChannelCtorKt;
import io.ktor.utils.io.ByteReadChannel;
import io.ktor.utils.io.CoroutinesKt;
import io.ktor.utils.io.WriterScope;
import ka.k0;
import ka.n1;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import r7.p;
import r7.q;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\\\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0000\u0018\u00002\u00020\u0001Bb\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012F\u0010\u000f\u001aB\b\u0001\u0012\u0013\u0012\u00110\u0007¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\n\u0012\u0013\u0012\u00110\u0007¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\u000b\u0012\n\u0012\b\u0012\u0004\u0012\u00020\r0\f\u0012\u0006\u0012\u0004\u0018\u00010\u000e0\u0006ø\u0001\u0000¢\u0006\u0004\b\u0010\u0010\u0011J)\u0010\u0015\u001a\u0004\u0018\u00018\u0000\"\b\b\u0000\u0010\u0012*\u00020\u000e2\f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00028\u00000\u0013H\u0016¢\u0006\u0004\b\u0015\u0010\u0016J1\u0010\u0018\u001a\u00020\r\"\b\b\u0000\u0010\u0012*\u00020\u000e2\f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00028\u00000\u00132\b\u0010\u0017\u001a\u0004\u0018\u00018\u0000H\u0016¢\u0006\u0004\b\u0018\u0010\u0019J\u000f\u0010\u001b\u001a\u00020\u001aH\u0016¢\u0006\u0004\b\u001b\u0010\u001cR\u0014\u0010\u0003\u001a\u00020\u00028\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0003\u0010\u001dR\u0014\u0010\u0005\u001a\u00020\u00048\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0005\u0010\u001eRW\u0010\u000f\u001aB\b\u0001\u0012\u0013\u0012\u00110\u0007¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\n\u0012\u0013\u0012\u00110\u0007¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\u000b\u0012\n\u0012\b\u0012\u0004\u0012\u00020\r0\f\u0012\u0006\u0012\u0004\u0018\u00010\u000e0\u00068\u0002X\u0082\u0004ø\u0001\u0000¢\u0006\u0006\n\u0004\b\u000f\u0010\u001fR\u001a\u0010 \u001a\u00020\u001a8\u0002X\u0082\u0004¢\u0006\f\n\u0004\b \u0010!\u0012\u0004\b\"\u0010#R\u0016\u0010'\u001a\u0004\u0018\u00010$8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b%\u0010&R\u0016\u0010\u000b\u001a\u0004\u0018\u00010\u00078VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b(\u0010)R\u0016\u0010-\u001a\u0004\u0018\u00010*8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b+\u0010,R\u0014\u00101\u001a\u00020.8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b/\u00100\u0082\u0002\u0004\n\u0002\b\u0019¨\u00062"}, d2 = {"Lio/ktor/client/content/ObservableContent;", "Lio/ktor/http/content/OutgoingContent$ReadChannelContent;", "Lio/ktor/http/content/OutgoingContent;", "delegate", "Ld7/j;", "callContext", "Lkotlin/Function3;", "", "Lx6/y;", ContentDisposition.Parameters.Name, "bytesSentTotal", "contentLength", "Ld7/d;", "Lx6/t0;", "", "listener", "<init>", "(Lio/ktor/http/content/OutgoingContent;Ld7/j;Lr7/q;)V", "T", "Lio/ktor/util/AttributeKey;", "key", "getProperty", "(Lio/ktor/util/AttributeKey;)Ljava/lang/Object;", "value", "setProperty", "(Lio/ktor/util/AttributeKey;Ljava/lang/Object;)V", "Lio/ktor/utils/io/ByteReadChannel;", "readFrom", "()Lio/ktor/utils/io/ByteReadChannel;", "Lio/ktor/http/content/OutgoingContent;", "Ld7/j;", "Lr7/q;", "content", "Lio/ktor/utils/io/ByteReadChannel;", "getContent$annotations", "()V", "Lio/ktor/http/ContentType;", "getContentType", "()Lio/ktor/http/ContentType;", "contentType", "getContentLength", "()Ljava/lang/Long;", "Lio/ktor/http/HttpStatusCode;", "getStatus", "()Lio/ktor/http/HttpStatusCode;", "status", "Lio/ktor/http/Headers;", "getHeaders", "()Lio/ktor/http/Headers;", "headers", "ktor-client-core"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class ObservableContent extends OutgoingContent.ReadChannelContent {
    private final j callContext;
    private final ByteReadChannel content;
    private final OutgoingContent delegate;
    private final q<Long, Long, d<? super t0>, Object> listener;

    /* JADX WARN: Multi-variable type inference failed */
    public ObservableContent(OutgoingContent outgoingContent, j jVar, q<? super Long, ? super Long, ? super d<? super t0>, ? extends Object> qVar) {
        ByteReadChannel channel;
        this.delegate = outgoingContent;
        this.callContext = jVar;
        this.listener = qVar;
        if (outgoingContent instanceof OutgoingContent.ByteArrayContent) {
            channel = ByteChannelCtorKt.ByteReadChannel(((OutgoingContent.ByteArrayContent) outgoingContent).getBytes());
        } else {
            if (outgoingContent instanceof OutgoingContent.ProtocolUpgrade) {
                throw new UnsupportedContentTypeException(outgoingContent);
            }
            if (outgoingContent instanceof OutgoingContent.NoContent) {
                channel = ByteReadChannel.INSTANCE.getEmpty();
            } else if (outgoingContent instanceof OutgoingContent.ReadChannelContent) {
                channel = ((OutgoingContent.ReadChannelContent) outgoingContent).readFrom();
            } else {
                if (!(outgoingContent instanceof OutgoingContent.WriteChannelContent)) {
                    throw new NoWhenBranchMatchedException();
                }
                channel = CoroutinesKt.writer((k0) n1.f19624i, jVar, true, (p<? super WriterScope, ? super d<? super t0>, ? extends Object>) new ObservableContent$content$1(this, null)).getChannel();
            }
        }
        this.content = channel;
    }

    private static /* synthetic */ void getContent$annotations() {
    }

    @Override // io.ktor.http.content.OutgoingContent
    public Long getContentLength() {
        return this.delegate.getContentLength();
    }

    @Override // io.ktor.http.content.OutgoingContent
    public ContentType getContentType() {
        return this.delegate.getContentType();
    }

    @Override // io.ktor.http.content.OutgoingContent
    public Headers getHeaders() {
        return this.delegate.getHeaders();
    }

    @Override // io.ktor.http.content.OutgoingContent
    public <T> T getProperty(AttributeKey<T> key) {
        return (T) this.delegate.getProperty(key);
    }

    @Override // io.ktor.http.content.OutgoingContent
    /* JADX INFO: renamed from: getStatus */
    public HttpStatusCode getValue() {
        return this.delegate.getValue();
    }

    @Override // io.ktor.http.content.OutgoingContent.ReadChannelContent
    public ByteReadChannel readFrom() {
        return ByteChannelUtilsKt.observable(this.content, this.callContext, getContentLength(), this.listener);
    }

    @Override // io.ktor.http.content.OutgoingContent
    public <T> void setProperty(AttributeKey<T> key, T value) {
        this.delegate.setProperty(key, value);
    }
}
