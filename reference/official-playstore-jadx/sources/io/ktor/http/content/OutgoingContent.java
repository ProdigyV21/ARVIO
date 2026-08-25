package io.ktor.http.content;

import d7.d;
import d7.j;
import io.ktor.http.ContentType;
import io.ktor.http.Headers;
import io.ktor.http.HttpStatusCode;
import io.ktor.util.AttributeKey;
import io.ktor.util.Attributes;
import io.ktor.util.AttributesJvmKt;
import io.ktor.utils.io.ByteReadChannel;
import io.ktor.utils.io.ByteWriteChannel;
import io.ktor.utils.io.CoroutinesKt;
import io.ktor.utils.io.WriterScope;
import ka.k0;
import ka.n1;
import ka.v1;
import ka.x0;
import kotlin.Metadata;
import kotlin.jvm.internal.h;
import r7.p;
import x6.t0;
import x7.l;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0005!\"#$%B\t\b\u0004¢\u0006\u0004\b\u0002\u0010\u0003J)\u0010\u0007\u001a\u0004\u0018\u00018\u0000\"\b\b\u0000\u0010\u0004*\u00020\u00012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00028\u00000\u0005H\u0016¢\u0006\u0004\b\u0007\u0010\bJ1\u0010\u000b\u001a\u00020\n\"\b\b\u0000\u0010\u0004*\u00020\u00012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00028\u00000\u00052\b\u0010\t\u001a\u0004\u0018\u00018\u0000H\u0016¢\u0006\u0004\b\u000b\u0010\fJ\u0011\u0010\u000e\u001a\u0004\u0018\u00010\rH\u0016¢\u0006\u0004\b\u000e\u0010\u000fR\u0018\u0010\u0011\u001a\u0004\u0018\u00010\u00108\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u0011\u0010\u0012R\u0016\u0010\u0016\u001a\u0004\u0018\u00010\u00138VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0014\u0010\u0015R\u0016\u0010\u001a\u001a\u0004\u0018\u00010\u00178VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0018\u0010\u0019R\u0016\u0010\u001e\u001a\u0004\u0018\u00010\u001b8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u001c\u0010\u001dR\u0014\u0010 \u001a\u00020\r8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u001f\u0010\u000f\u0082\u0001\u0005&'()*¨\u0006+"}, d2 = {"Lio/ktor/http/content/OutgoingContent;", "", "<init>", "()V", "T", "Lio/ktor/util/AttributeKey;", "key", "getProperty", "(Lio/ktor/util/AttributeKey;)Ljava/lang/Object;", "value", "Lx6/t0;", "setProperty", "(Lio/ktor/util/AttributeKey;Ljava/lang/Object;)V", "Lio/ktor/http/Headers;", "trailers", "()Lio/ktor/http/Headers;", "Lio/ktor/util/Attributes;", "extensionProperties", "Lio/ktor/util/Attributes;", "Lio/ktor/http/ContentType;", "getContentType", "()Lio/ktor/http/ContentType;", "contentType", "", "getContentLength", "()Ljava/lang/Long;", "contentLength", "Lio/ktor/http/HttpStatusCode;", "getStatus", "()Lio/ktor/http/HttpStatusCode;", "status", "getHeaders", "headers", "ByteArrayContent", "NoContent", "ProtocolUpgrade", "ReadChannelContent", "WriteChannelContent", "Lio/ktor/http/content/OutgoingContent$ByteArrayContent;", "Lio/ktor/http/content/OutgoingContent$NoContent;", "Lio/ktor/http/content/OutgoingContent$ProtocolUpgrade;", "Lio/ktor/http/content/OutgoingContent$ReadChannelContent;", "Lio/ktor/http/content/OutgoingContent$WriteChannelContent;", "ktor-http"}, k = 1, mv = {1, 8, 0}, xi = 48)
public abstract class OutgoingContent {
    private Attributes extensionProperties;

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0012\n\u0000\b&\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H&¨\u0006\u0005"}, d2 = {"Lio/ktor/http/content/OutgoingContent$ByteArrayContent;", "Lio/ktor/http/content/OutgoingContent;", "()V", "bytes", "", "ktor-http"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static abstract class ByteArrayContent extends OutgoingContent {
        public ByteArrayContent() {
            super(null);
        }

        public abstract byte[] bytes();
    }

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b&\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Lio/ktor/http/content/OutgoingContent$NoContent;", "Lio/ktor/http/content/OutgoingContent;", "()V", "ktor-http"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static abstract class NoContent extends OutgoingContent {
        public NoContent() {
            super(null);
        }
    }

    @Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\b&\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J3\u0010\f\u001a\u00020\u000b2\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\bH¦@ø\u0001\u0000¢\u0006\u0004\b\f\u0010\rR\u0011\u0010\u0011\u001a\u00020\u000e8F¢\u0006\u0006\u001a\u0004\b\u000f\u0010\u0010\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0012"}, d2 = {"Lio/ktor/http/content/OutgoingContent$ProtocolUpgrade;", "Lio/ktor/http/content/OutgoingContent;", "<init>", "()V", "Lio/ktor/utils/io/ByteReadChannel;", "input", "Lio/ktor/utils/io/ByteWriteChannel;", "output", "Ld7/j;", "engineContext", "userContext", "Lka/v1;", "upgrade", "(Lio/ktor/utils/io/ByteReadChannel;Lio/ktor/utils/io/ByteWriteChannel;Ld7/j;Ld7/j;Ld7/d;)Ljava/lang/Object;", "Lio/ktor/http/HttpStatusCode;", "getStatus", "()Lio/ktor/http/HttpStatusCode;", "status", "ktor-http"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static abstract class ProtocolUpgrade extends OutgoingContent {
        public ProtocolUpgrade() {
            super(null);
        }

        @Override // io.ktor.http.content.OutgoingContent
        public final HttpStatusCode getStatus() {
            return HttpStatusCode.INSTANCE.getSwitchingProtocols();
        }

        public abstract Object upgrade(ByteReadChannel byteReadChannel, ByteWriteChannel byteWriteChannel, j jVar, j jVar2, d<? super v1> dVar);
    }

    @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b&\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u000f\u0010\u0005\u001a\u00020\u0004H&¢\u0006\u0004\b\u0005\u0010\u0006J\u0017\u0010\u0005\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\u0007H\u0016¢\u0006\u0004\b\u0005\u0010\t¨\u0006\n"}, d2 = {"Lio/ktor/http/content/OutgoingContent$ReadChannelContent;", "Lio/ktor/http/content/OutgoingContent;", "<init>", "()V", "Lio/ktor/utils/io/ByteReadChannel;", "readFrom", "()Lio/ktor/utils/io/ByteReadChannel;", "Lx7/l;", "range", "(Lx7/l;)Lio/ktor/utils/io/ByteReadChannel;", "ktor-http"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static abstract class ReadChannelContent extends OutgoingContent {
        public ReadChannelContent() {
            super(null);
        }

        public abstract ByteReadChannel readFrom();

        public ByteReadChannel readFrom(l range) {
            if (range.isEmpty()) {
                return ByteReadChannel.INSTANCE.getEmpty();
            }
            return CoroutinesKt.writer((k0) n1.f19624i, (j) x0.f19654c, true, (p<? super WriterScope, ? super d<? super t0>, ? extends Object>) new OutgoingContent$ReadChannelContent$readFrom$1(this, range, null)).getChannel();
        }
    }

    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b&\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u001b\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0004H¦@ø\u0001\u0000¢\u0006\u0004\b\u0007\u0010\b\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\t"}, d2 = {"Lio/ktor/http/content/OutgoingContent$WriteChannelContent;", "Lio/ktor/http/content/OutgoingContent;", "<init>", "()V", "Lio/ktor/utils/io/ByteWriteChannel;", "channel", "Lx6/t0;", "writeTo", "(Lio/ktor/utils/io/ByteWriteChannel;Ld7/d;)Ljava/lang/Object;", "ktor-http"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static abstract class WriteChannelContent extends OutgoingContent {
        public WriteChannelContent() {
            super(null);
        }

        public abstract Object writeTo(ByteWriteChannel byteWriteChannel, d<? super t0> dVar);
    }

    public /* synthetic */ OutgoingContent(h hVar) {
        this();
    }

    public Long getContentLength() {
        return null;
    }

    public ContentType getContentType() {
        return null;
    }

    public Headers getHeaders() {
        return Headers.INSTANCE.getEmpty();
    }

    public <T> T getProperty(AttributeKey<T> key) {
        Attributes attributes = this.extensionProperties;
        if (attributes != null) {
            return (T) attributes.getOrNull(key);
        }
        return null;
    }

    public HttpStatusCode getStatus() {
        return null;
    }

    public <T> void setProperty(AttributeKey<T> key, T value) {
        if (value == null && this.extensionProperties == null) {
            return;
        }
        if (value == null) {
            Attributes attributes = this.extensionProperties;
            if (attributes != null) {
                attributes.remove(key);
                return;
            }
            return;
        }
        Attributes attributesAttributes$default = this.extensionProperties;
        if (attributesAttributes$default == null) {
            attributesAttributes$default = AttributesJvmKt.Attributes$default(false, 1, null);
        }
        this.extensionProperties = attributesAttributes$default;
        attributesAttributes$default.put(key, value);
    }

    public Headers trailers() {
        return null;
    }

    private OutgoingContent() {
    }
}
