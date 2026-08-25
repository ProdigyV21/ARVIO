package io.ktor.serialization;

import d7.d;
import io.ktor.http.auth.HttpAuthHeader;
import io.ktor.util.reflect.TypeInfo;
import io.ktor.websocket.Frame;
import java.nio.charset.Charset;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001J/\u0010\t\u001a\u00020\b2\n\u0010\u0004\u001a\u00060\u0002j\u0002`\u00032\u0006\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\u0001H\u0096@ø\u0001\u0000¢\u0006\u0004\b\t\u0010\nJ1\u0010\u000b\u001a\u00020\b2\n\u0010\u0004\u001a\u00060\u0002j\u0002`\u00032\u0006\u0010\u0006\u001a\u00020\u00052\b\u0010\u0007\u001a\u0004\u0018\u00010\u0001H\u0096@ø\u0001\u0000¢\u0006\u0004\b\u000b\u0010\nJ1\u0010\r\u001a\u0004\u0018\u00010\u00012\n\u0010\u0004\u001a\u00060\u0002j\u0002`\u00032\u0006\u0010\u0006\u001a\u00020\u00052\u0006\u0010\f\u001a\u00020\bH¦@ø\u0001\u0000¢\u0006\u0004\b\r\u0010\u000eJ\u0017\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u000f\u001a\u00020\bH&¢\u0006\u0004\b\u0011\u0010\u0012\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0013"}, d2 = {"Lio/ktor/serialization/WebsocketContentConverter;", "", "Ljava/nio/charset/Charset;", "Lio/ktor/utils/io/charsets/Charset;", HttpAuthHeader.Parameters.Charset, "Lio/ktor/util/reflect/TypeInfo;", "typeInfo", "value", "Lio/ktor/websocket/Frame;", "serialize", "(Ljava/nio/charset/Charset;Lio/ktor/util/reflect/TypeInfo;Ljava/lang/Object;Ld7/d;)Ljava/lang/Object;", "serializeNullable", "content", "deserialize", "(Ljava/nio/charset/Charset;Lio/ktor/util/reflect/TypeInfo;Lio/ktor/websocket/Frame;Ld7/d;)Ljava/lang/Object;", "frame", "", "isApplicable", "(Lio/ktor/websocket/Frame;)Z", "ktor-serialization"}, k = 1, mv = {1, 8, 0}, xi = 48)
public interface WebsocketContentConverter {

    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class DefaultImpls {
        public static Object serialize(WebsocketContentConverter websocketContentConverter, Charset charset, TypeInfo typeInfo, Object obj, d<? super Frame> dVar) {
            return websocketContentConverter.serializeNullable(charset, typeInfo, obj, dVar);
        }

        public static Object serializeNullable(WebsocketContentConverter websocketContentConverter, Charset charset, TypeInfo typeInfo, Object obj, d<? super Frame> dVar) {
            return websocketContentConverter.serialize(charset, typeInfo, obj, dVar);
        }
    }

    Object deserialize(Charset charset, TypeInfo typeInfo, Frame frame, d<Object> dVar);

    boolean isApplicable(Frame frame);

    Object serialize(Charset charset, TypeInfo typeInfo, Object obj, d<? super Frame> dVar);

    Object serializeNullable(Charset charset, TypeInfo typeInfo, Object obj, d<? super Frame> dVar);
}
