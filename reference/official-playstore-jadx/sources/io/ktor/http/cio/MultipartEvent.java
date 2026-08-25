package io.ktor.http.cio;

import androidx.media3.extractor.text.ttml.TtmlNode;
import d7.k;
import io.ktor.utils.io.ByteReadChannel;
import io.ktor.utils.io.core.ByteReadPacket;
import ka.m0;
import ka.p0;
import kotlin.Metadata;
import kotlin.jvm.internal.h;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0003\u0006\u0007\bB\t\b\u0004¢\u0006\u0004\b\u0002\u0010\u0003J\u000f\u0010\u0005\u001a\u00020\u0004H&¢\u0006\u0004\b\u0005\u0010\u0003\u0082\u0001\u0003\t\n\u000b¨\u0006\f"}, d2 = {"Lio/ktor/http/cio/MultipartEvent;", "", "<init>", "()V", "Lx6/t0;", "release", "Epilogue", "MultipartPart", "Preamble", "Lio/ktor/http/cio/MultipartEvent$Epilogue;", "Lio/ktor/http/cio/MultipartEvent$MultipartPart;", "Lio/ktor/http/cio/MultipartEvent$Preamble;", "ktor-http-cio"}, k = 1, mv = {1, 8, 0}, xi = 48)
public abstract class MultipartEvent {

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\u000f\u0010\u0007\u001a\u00020\u0006H\u0016¢\u0006\u0004\b\u0007\u0010\bR\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\t\u001a\u0004\b\n\u0010\u000b¨\u0006\f"}, d2 = {"Lio/ktor/http/cio/MultipartEvent$Epilogue;", "Lio/ktor/http/cio/MultipartEvent;", "Lio/ktor/utils/io/core/ByteReadPacket;", TtmlNode.TAG_BODY, "<init>", "(Lio/ktor/utils/io/core/ByteReadPacket;)V", "Lx6/t0;", "release", "()V", "Lio/ktor/utils/io/core/ByteReadPacket;", "getBody", "()Lio/ktor/utils/io/core/ByteReadPacket;", "ktor-http-cio"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class Epilogue extends MultipartEvent {
        private final ByteReadPacket body;

        public Epilogue(ByteReadPacket byteReadPacket) {
            super(null);
            this.body = byteReadPacket;
        }

        public final ByteReadPacket getBody() {
            return this.body;
        }

        @Override // io.ktor.http.cio.MultipartEvent
        public void release() {
            this.body.release();
        }
    }

    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\t\u0018\u00002\u00020\u0001B\u001d\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u0002\u0012\u0006\u0010\u0006\u001a\u00020\u0005¢\u0006\u0004\b\u0007\u0010\bJ\u000f\u0010\n\u001a\u00020\tH\u0016¢\u0006\u0004\b\n\u0010\u000bR\u001d\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u00028\u0006¢\u0006\f\n\u0004\b\u0004\u0010\f\u001a\u0004\b\r\u0010\u000eR\u0017\u0010\u0006\u001a\u00020\u00058\u0006¢\u0006\f\n\u0004\b\u0006\u0010\u000f\u001a\u0004\b\u0010\u0010\u0011¨\u0006\u0012"}, d2 = {"Lio/ktor/http/cio/MultipartEvent$MultipartPart;", "Lio/ktor/http/cio/MultipartEvent;", "Lka/p0;", "Lio/ktor/http/cio/HttpHeadersMap;", "headers", "Lio/ktor/utils/io/ByteReadChannel;", TtmlNode.TAG_BODY, "<init>", "(Lka/p0;Lio/ktor/utils/io/ByteReadChannel;)V", "Lx6/t0;", "release", "()V", "Lka/p0;", "getHeaders", "()Lka/p0;", "Lio/ktor/utils/io/ByteReadChannel;", "getBody", "()Lio/ktor/utils/io/ByteReadChannel;", "ktor-http-cio"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class MultipartPart extends MultipartEvent {
        private final ByteReadChannel body;
        private final p0<HttpHeadersMap> headers;

        public MultipartPart(p0<HttpHeadersMap> p0Var, ByteReadChannel byteReadChannel) {
            super(null);
            this.headers = p0Var;
            this.body = byteReadChannel;
        }

        public final ByteReadChannel getBody() {
            return this.body;
        }

        public final p0<HttpHeadersMap> getHeaders() {
            return this.headers;
        }

        @Override // io.ktor.http.cio.MultipartEvent
        public void release() throws Throwable {
            this.headers.invokeOnCompletion(new MultipartEvent$MultipartPart$release$1(this));
            m0.s(k.f14688i, new MultipartEvent$MultipartPart$release$2(this, null));
        }
    }

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\u000f\u0010\u0007\u001a\u00020\u0006H\u0016¢\u0006\u0004\b\u0007\u0010\bR\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\t\u001a\u0004\b\n\u0010\u000b¨\u0006\f"}, d2 = {"Lio/ktor/http/cio/MultipartEvent$Preamble;", "Lio/ktor/http/cio/MultipartEvent;", "Lio/ktor/utils/io/core/ByteReadPacket;", TtmlNode.TAG_BODY, "<init>", "(Lio/ktor/utils/io/core/ByteReadPacket;)V", "Lx6/t0;", "release", "()V", "Lio/ktor/utils/io/core/ByteReadPacket;", "getBody", "()Lio/ktor/utils/io/core/ByteReadPacket;", "ktor-http-cio"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class Preamble extends MultipartEvent {
        private final ByteReadPacket body;

        public Preamble(ByteReadPacket byteReadPacket) {
            super(null);
            this.body = byteReadPacket;
        }

        public final ByteReadPacket getBody() {
            return this.body;
        }

        @Override // io.ktor.http.cio.MultipartEvent
        public void release() {
            this.body.release();
        }
    }

    public /* synthetic */ MultipartEvent(h hVar) {
        this();
    }

    public abstract void release();

    private MultipartEvent() {
    }
}
