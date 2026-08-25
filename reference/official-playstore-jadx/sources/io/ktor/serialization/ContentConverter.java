package io.ktor.serialization;

import d7.d;
import io.ktor.http.ContentType;
import io.ktor.http.auth.HttpAuthHeader;
import io.ktor.http.content.OutgoingContent;
import io.ktor.util.reflect.TypeInfo;
import io.ktor.utils.io.ByteReadChannel;
import java.nio.charset.Charset;
import kotlin.Metadata;
import x6.e;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\bf\u0018\u00002\u00020\u0001J9\u0010\u000b\u001a\u0004\u0018\u00010\n2\u0006\u0010\u0003\u001a\u00020\u00022\n\u0010\u0006\u001a\u00060\u0004j\u0002`\u00052\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010\t\u001a\u00020\u0001H\u0097@ø\u0001\u0000¢\u0006\u0004\b\u000b\u0010\fJ;\u0010\r\u001a\u0004\u0018\u00010\n2\u0006\u0010\u0003\u001a\u00020\u00022\n\u0010\u0006\u001a\u00060\u0004j\u0002`\u00052\u0006\u0010\b\u001a\u00020\u00072\b\u0010\t\u001a\u0004\u0018\u00010\u0001H\u0096@ø\u0001\u0000¢\u0006\u0004\b\r\u0010\fJ1\u0010\u0010\u001a\u0004\u0018\u00010\u00012\n\u0010\u0006\u001a\u00060\u0004j\u0002`\u00052\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010\u000f\u001a\u00020\u000eH¦@ø\u0001\u0000¢\u0006\u0004\b\u0010\u0010\u0011\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0012"}, d2 = {"Lio/ktor/serialization/ContentConverter;", "", "Lio/ktor/http/ContentType;", "contentType", "Ljava/nio/charset/Charset;", "Lio/ktor/utils/io/charsets/Charset;", HttpAuthHeader.Parameters.Charset, "Lio/ktor/util/reflect/TypeInfo;", "typeInfo", "value", "Lio/ktor/http/content/OutgoingContent;", "serialize", "(Lio/ktor/http/ContentType;Ljava/nio/charset/Charset;Lio/ktor/util/reflect/TypeInfo;Ljava/lang/Object;Ld7/d;)Ljava/lang/Object;", "serializeNullable", "Lio/ktor/utils/io/ByteReadChannel;", "content", "deserialize", "(Ljava/nio/charset/Charset;Lio/ktor/util/reflect/TypeInfo;Lio/ktor/utils/io/ByteReadChannel;Ld7/d;)Ljava/lang/Object;", "ktor-serialization"}, k = 1, mv = {1, 8, 0}, xi = 48)
public interface ContentConverter {

    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class DefaultImpls {
        @e
        public static Object serialize(ContentConverter contentConverter, ContentType contentType, Charset charset, TypeInfo typeInfo, Object obj, d<? super OutgoingContent> dVar) {
            return contentConverter.serializeNullable(contentType, charset, typeInfo, obj, dVar);
        }

        public static Object serializeNullable(ContentConverter contentConverter, ContentType contentType, Charset charset, TypeInfo typeInfo, Object obj, d<? super OutgoingContent> dVar) {
            return contentConverter.serialize(contentType, charset, typeInfo, obj, dVar);
        }
    }

    Object deserialize(Charset charset, TypeInfo typeInfo, ByteReadChannel byteReadChannel, d<Object> dVar);

    @e
    Object serialize(ContentType contentType, Charset charset, TypeInfo typeInfo, Object obj, d<? super OutgoingContent> dVar);

    Object serializeNullable(ContentType contentType, Charset charset, TypeInfo typeInfo, Object obj, d<? super OutgoingContent> dVar);
}
