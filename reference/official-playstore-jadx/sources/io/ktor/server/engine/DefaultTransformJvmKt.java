package io.ktor.server.engine;

import ac.b;
import d7.d;
import io.ktor.http.HttpHeaders;
import io.ktor.http.auth.HttpAuthHeader;
import io.ktor.http.cio.CIOMultipartDataBase;
import io.ktor.http.content.MultiPartData;
import io.ktor.server.application.ApplicationCall;
import io.ktor.server.application.ApplicationCallKt;
import io.ktor.server.request.ApplicationRequest;
import io.ktor.server.request.ApplicationRequestPropertiesKt;
import io.ktor.util.pipeline.PipelineContext;
import io.ktor.utils.io.ByteReadChannel;
import io.ktor.utils.io.core.ByteReadPacket;
import io.ktor.utils.io.jvm.javaio.BlockingKt;
import io.ktor.utils.io.streams.StreamsKt;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import ka.x0;
import kotlin.Metadata;
import kotlin.jvm.internal.l0;
import kotlin.jvm.internal.m0;
import kotlin.jvm.internal.p;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a-\u0010\u0004\u001a\u0004\u0018\u00010\u0001*\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00020\u00002\u0006\u0010\u0003\u001a\u00020\u0001H\u0080@ø\u0001\u0000¢\u0006\u0004\b\u0004\u0010\u0005\u001a%\u0010\t\u001a\u00020\b*\f\u0012\u0002\b\u0003\u0012\u0004\u0012\u00020\u00020\u00002\u0006\u0010\u0007\u001a\u00020\u0006H\u0000¢\u0006\u0004\b\t\u0010\n\u001a\u001f\u0010\u0010\u001a\u00020\u000f*\u00020\u000b2\n\u0010\u000e\u001a\u00060\fj\u0002`\rH\u0000¢\u0006\u0004\b\u0010\u0010\u0011\u001a\u0017\u0010\u0014\u001a\u00020\u00132\u0006\u0010\u0012\u001a\u00020\u0006H\u0002¢\u0006\u0004\b\u0014\u0010\u0015\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0016"}, d2 = {"Lio/ktor/util/pipeline/PipelineContext;", "", "Lio/ktor/server/application/ApplicationCall;", "query", "defaultPlatformTransformations", "(Lio/ktor/util/pipeline/PipelineContext;Ljava/lang/Object;Ld7/d;)Ljava/lang/Object;", "Lio/ktor/utils/io/ByteReadChannel;", "rc", "Lio/ktor/http/content/MultiPartData;", "multiPartData", "(Lio/ktor/util/pipeline/PipelineContext;Lio/ktor/utils/io/ByteReadChannel;)Lio/ktor/http/content/MultiPartData;", "Lio/ktor/utils/io/core/ByteReadPacket;", "Ljava/nio/charset/Charset;", "Lio/ktor/utils/io/charsets/Charset;", HttpAuthHeader.Parameters.Charset, "", "readTextWithCustomCharset", "(Lio/ktor/utils/io/core/ByteReadPacket;Ljava/nio/charset/Charset;)Ljava/lang/String;", "channel", "Ljava/io/InputStream;", "receiveGuardedInputStream", "(Lio/ktor/utils/io/ByteReadChannel;)Ljava/io/InputStream;", "ktor-server-host-common"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class DefaultTransformJvmKt {
    public static final Object defaultPlatformTransformations(PipelineContext<Object, ApplicationCall> pipelineContext, Object obj, d<Object> dVar) {
        ByteReadChannel byteReadChannel = obj instanceof ByteReadChannel ? (ByteReadChannel) obj : null;
        if (byteReadChannel != null) {
            kotlin.reflect.d<?> type = ApplicationCallKt.getReceiveType(pipelineContext.getContext()).getType();
            m0 m0Var = l0.f19747a;
            if (p.a(type, m0Var.b(InputStream.class))) {
                return receiveGuardedInputStream(byteReadChannel);
            }
            if (p.a(type, m0Var.b(MultiPartData.class))) {
                return multiPartData(pipelineContext, byteReadChannel);
            }
        }
        return null;
    }

    public static final MultiPartData multiPartData(PipelineContext<?, ApplicationCall> pipelineContext, ByteReadChannel byteReadChannel) {
        ApplicationRequest request = pipelineContext.getContext().getRequest();
        HttpHeaders httpHeaders = HttpHeaders.INSTANCE;
        String strHeader = ApplicationRequestPropertiesKt.header(request, httpHeaders.getContentType());
        if (strHeader == null) {
            throw new IllegalStateException("Content-Type header is required for multipart processing");
        }
        String strHeader2 = ApplicationRequestPropertiesKt.header(pipelineContext.getContext().getRequest(), httpHeaders.getContentLength());
        return new CIOMultipartDataBase(pipelineContext.getCoroutineContext().plus(x0.f19654c), byteReadChannel, strHeader, strHeader2 != null ? Long.valueOf(Long.parseLong(strHeader2)) : null, 0, 0, 48, null);
    }

    public static final String readTextWithCustomCharset(ByteReadPacket byteReadPacket, Charset charset) {
        return b.U(new InputStreamReader(StreamsKt.inputStream(byteReadPacket), charset));
    }

    private static final InputStream receiveGuardedInputStream(ByteReadChannel byteReadChannel) {
        return BlockingKt.toInputStream$default(byteReadChannel, null, 1, null);
    }
}
