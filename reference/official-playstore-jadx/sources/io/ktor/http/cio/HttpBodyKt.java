package io.ktor.http.cio;

import d7.d;
import e7.a;
import io.ktor.http.HttpMethod;
import io.ktor.http.HttpProtocolVersion;
import io.ktor.http.cio.internals.CharsKt;
import io.ktor.utils.io.ByteReadChannel;
import io.ktor.utils.io.ByteReadChannelJVMKt;
import io.ktor.utils.io.ByteWriteChannel;
import java.util.Iterator;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.jvm.internal.p;
import kotlin.text.o;
import org.jsoup.helper.HttpConnection;
import x6.e;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\r\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\u001a)\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0001\u001a\u00020\u00002\b\u0010\u0003\u001a\u0004\u0018\u00010\u00022\b\u0010\u0005\u001a\u0004\u0018\u00010\u0004¢\u0006\u0004\b\u0007\u0010\b\u001a\u0015\u0010\u0007\u001a\u00020\u00062\u0006\u0010\n\u001a\u00020\t¢\u0006\u0004\b\u0007\u0010\u000b\u001a;\u0010\u0010\u001a\u00020\u00062\u0006\u0010\u0001\u001a\u00020\u00002\u0006\u0010\r\u001a\u00020\f2\b\u0010\u000e\u001a\u0004\u0018\u00010\u00022\b\u0010\u0005\u001a\u0004\u0018\u00010\u00042\b\u0010\u000f\u001a\u0004\u0018\u00010\u0002¢\u0006\u0004\b\u0010\u0010\u0011\u001a\u0015\u0010\u0010\u001a\u00020\u00062\u0006\u0010\n\u001a\u00020\t¢\u0006\u0004\b\u0010\u0010\u000b\u001aI\u0010\u0019\u001a\u00020\u00182\b\u0010\u0013\u001a\u0004\u0018\u00010\u00122\u0006\u0010\r\u001a\u00020\f2\b\u0010\u000e\u001a\u0004\u0018\u00010\u00022\b\u0010\u0005\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0015\u001a\u00020\u00142\u0006\u0010\u0017\u001a\u00020\u0016H\u0086@ø\u0001\u0000¢\u0006\u0004\b\u0019\u0010\u001a\u001a?\u0010\u0019\u001a\u00020\u00182\u0006\u0010\r\u001a\u00020\f2\b\u0010\u000e\u001a\u0004\u0018\u00010\u00022\b\u0010\u0005\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0015\u001a\u00020\u00142\u0006\u0010\u0017\u001a\u00020\u0016H\u0087@ø\u0001\u0000¢\u0006\u0004\b\u0019\u0010\u001b\u001a+\u0010\u0019\u001a\u00020\u00182\u0006\u0010\u001d\u001a\u00020\u001c2\u0006\u0010\u0015\u001a\u00020\u00142\u0006\u0010\u0017\u001a\u00020\u0016H\u0086@ø\u0001\u0000¢\u0006\u0004\b\u0019\u0010\u001e\u001a\u0017\u0010\u001f\u001a\u00020\u00062\u0006\u0010\u000e\u001a\u00020\u0002H\u0002¢\u0006\u0004\b\u001f\u0010 \u0082\u0002\u0004\n\u0002\b\u0019¨\u0006!"}, d2 = {"Lio/ktor/http/HttpMethod;", "method", "", "upgrade", "Lio/ktor/http/cio/ConnectionOptions;", "connectionOptions", "", "expectHttpUpgrade", "(Lio/ktor/http/HttpMethod;Ljava/lang/CharSequence;Lio/ktor/http/cio/ConnectionOptions;)Z", "Lio/ktor/http/cio/Request;", "request", "(Lio/ktor/http/cio/Request;)Z", "", "contentLength", "transferEncoding", "contentType", "expectHttpBody", "(Lio/ktor/http/HttpMethod;JLjava/lang/CharSequence;Lio/ktor/http/cio/ConnectionOptions;Ljava/lang/CharSequence;)Z", "Lio/ktor/http/HttpProtocolVersion;", "version", "Lio/ktor/utils/io/ByteReadChannel;", "input", "Lio/ktor/utils/io/ByteWriteChannel;", "out", "Lx6/t0;", "parseHttpBody", "(Lio/ktor/http/HttpProtocolVersion;JLjava/lang/CharSequence;Lio/ktor/http/cio/ConnectionOptions;Lio/ktor/utils/io/ByteReadChannel;Lio/ktor/utils/io/ByteWriteChannel;Ld7/d;)Ljava/lang/Object;", "(JLjava/lang/CharSequence;Lio/ktor/http/cio/ConnectionOptions;Lio/ktor/utils/io/ByteReadChannel;Lio/ktor/utils/io/ByteWriteChannel;Ld7/d;)Ljava/lang/Object;", "Lio/ktor/http/cio/HttpHeadersMap;", "headers", "(Lio/ktor/http/cio/HttpHeadersMap;Lio/ktor/utils/io/ByteReadChannel;Lio/ktor/utils/io/ByteWriteChannel;Ld7/d;)Ljava/lang/Object;", "isTransferEncodingChunked", "(Ljava/lang/CharSequence;)Z", "ktor-http-cio"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class HttpBodyKt {
    public static final boolean expectHttpBody(HttpMethod httpMethod, long j10, CharSequence charSequence, ConnectionOptions connectionOptions, CharSequence charSequence2) {
        if (charSequence != null) {
            isTransferEncodingChunked(charSequence);
            return true;
        }
        if (j10 != -1) {
            return j10 > 0;
        }
        if (charSequence2 != null) {
            return true;
        }
        HttpMethod.Companion companion = HttpMethod.INSTANCE;
        return (httpMethod.equals(companion.getGet()) || httpMethod.equals(companion.getHead()) || httpMethod.equals(companion.getOptions()) || connectionOptions == null || !connectionOptions.getClose()) ? false : true;
    }

    public static final boolean expectHttpUpgrade(HttpMethod httpMethod, CharSequence charSequence, ConnectionOptions connectionOptions) {
        return httpMethod.equals(HttpMethod.INSTANCE.getGet()) && charSequence != null && connectionOptions != null && connectionOptions.getUpgrade();
    }

    private static final boolean isTransferEncodingChunked(CharSequence charSequence) {
        if (CharsKt.equalsLowerCase$default(charSequence, 0, 0, "chunked", 3, null)) {
            return true;
        }
        boolean z = false;
        if (CharsKt.equalsLowerCase$default(charSequence, 0, 0, "identity", 3, null)) {
            return false;
        }
        Iterator it = o.y0(charSequence, new String[]{","}, 0, 6).iterator();
        while (it.hasNext()) {
            String lowerCase = o.L0((String) it.next()).toString().toLowerCase(Locale.ROOT);
            if (lowerCase.equals("chunked")) {
                if (z) {
                    throw new IllegalArgumentException("Double-chunked TE is not supported: " + ((Object) charSequence));
                }
                z = true;
            } else if (!lowerCase.equals("identity")) {
                throw new IllegalArgumentException("Unsupported transfer encoding ".concat(lowerCase));
            }
        }
        return z;
    }

    public static final Object parseHttpBody(HttpProtocolVersion httpProtocolVersion, long j10, CharSequence charSequence, ConnectionOptions connectionOptions, ByteReadChannel byteReadChannel, ByteWriteChannel byteWriteChannel, d<? super t0> dVar) {
        a aVar = a.f15033i;
        t0 t0Var = t0.f22605a;
        if (charSequence != null && isTransferEncodingChunked(charSequence)) {
            Object objDecodeChunked = ChunkedTransferEncodingKt.decodeChunked(byteReadChannel, byteWriteChannel, dVar);
            return objDecodeChunked == aVar ? objDecodeChunked : t0Var;
        }
        if (j10 != -1) {
            Object objCopyTo = ByteReadChannelJVMKt.copyTo(byteReadChannel, byteWriteChannel, j10, dVar);
            return objCopyTo == aVar ? objCopyTo : t0Var;
        }
        if ((connectionOptions == null || !connectionOptions.getClose()) && !(connectionOptions == null && p.a(httpProtocolVersion, HttpProtocolVersion.INSTANCE.getHTTP_1_0()))) {
            byteWriteChannel.close(new IllegalStateException("Failed to parse request body: request body length should be specified,\nchunked transfer encoding should be used or\nkeep-alive should be disabled (connection: close)"));
            return t0Var;
        }
        Object objCopyTo2 = ByteReadChannelJVMKt.copyTo(byteReadChannel, byteWriteChannel, Long.MAX_VALUE, dVar);
        return objCopyTo2 == aVar ? objCopyTo2 : t0Var;
    }

    public static final boolean expectHttpUpgrade(Request request) {
        return expectHttpUpgrade(request.getMethod(), request.getHeaders().get("Upgrade"), ConnectionOptions.INSTANCE.parse(request.getHeaders().get("Connection")));
    }

    public static final boolean expectHttpBody(Request request) {
        HttpMethod method = request.getMethod();
        CharSequence charSequence = request.getHeaders().get("Content-Length");
        return expectHttpBody(method, charSequence != null ? CharsKt.parseDecLong(charSequence) : -1L, request.getHeaders().get("Transfer-Encoding"), ConnectionOptions.INSTANCE.parse(request.getHeaders().get("Connection")), request.getHeaders().get(HttpConnection.CONTENT_TYPE));
    }

    @e
    public static final Object parseHttpBody(long j10, CharSequence charSequence, ConnectionOptions connectionOptions, ByteReadChannel byteReadChannel, ByteWriteChannel byteWriteChannel, d<? super t0> dVar) {
        Object httpBody = parseHttpBody(null, j10, charSequence, connectionOptions, byteReadChannel, byteWriteChannel, dVar);
        return httpBody == a.f15033i ? httpBody : t0.f22605a;
    }

    public static final Object parseHttpBody(HttpHeadersMap httpHeadersMap, ByteReadChannel byteReadChannel, ByteWriteChannel byteWriteChannel, d<? super t0> dVar) {
        CharSequence charSequence = httpHeadersMap.get("Content-Length");
        Object httpBody = parseHttpBody(null, charSequence != null ? CharsKt.parseDecLong(charSequence) : -1L, httpHeadersMap.get("Transfer-Encoding"), ConnectionOptions.INSTANCE.parse(httpHeadersMap.get("Connection")), byteReadChannel, byteWriteChannel, dVar);
        return httpBody == a.f15033i ? httpBody : t0.f22605a;
    }
}
