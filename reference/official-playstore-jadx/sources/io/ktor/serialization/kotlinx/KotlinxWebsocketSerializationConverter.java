package io.ktor.serialization.kotlinx;

import d7.d;
import io.ktor.http.auth.HttpAuthHeader;
import io.ktor.serialization.WebsocketContentConverter;
import io.ktor.serialization.WebsocketConverterNotFoundException;
import io.ktor.serialization.WebsocketDeserializeException;
import io.ktor.util.reflect.TypeInfo;
import io.ktor.websocket.Frame;
import io.ktor.websocket.FrameCommonKt;
import java.lang.reflect.InvocationTargetException;
import java.nio.charset.Charset;
import kotlin.Metadata;
import kotlinx.serialization.SerializationException;
import ya.a;
import ya.h;
import ya.o;
import ya.x;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005J-\u0010\u000b\u001a\u00020\n2\n\u0010\u0007\u001a\u0006\u0012\u0002\b\u00030\u00062\u0006\u0010\u0003\u001a\u00020\u00022\b\u0010\t\u001a\u0004\u0018\u00010\bH\u0002¢\u0006\u0004\b\u000b\u0010\fJ1\u0010\u0012\u001a\u00020\n2\n\u0010\u000f\u001a\u00060\rj\u0002`\u000e2\u0006\u0010\u0011\u001a\u00020\u00102\b\u0010\t\u001a\u0004\u0018\u00010\bH\u0096@ø\u0001\u0000¢\u0006\u0004\b\u0012\u0010\u0013J1\u0010\u0015\u001a\u0004\u0018\u00010\b2\n\u0010\u000f\u001a\u00060\rj\u0002`\u000e2\u0006\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u0014\u001a\u00020\nH\u0096@ø\u0001\u0000¢\u0006\u0004\b\u0015\u0010\u0016J\u0017\u0010\u0019\u001a\u00020\u00182\u0006\u0010\u0017\u001a\u00020\nH\u0016¢\u0006\u0004\b\u0019\u0010\u001aR\u0014\u0010\u0003\u001a\u00020\u00028\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0003\u0010\u001b\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u001c"}, d2 = {"Lio/ktor/serialization/kotlinx/KotlinxWebsocketSerializationConverter;", "Lio/ktor/serialization/WebsocketContentConverter;", "Lya/o;", "format", "<init>", "(Lya/o;)V", "Lya/h;", "serializer", "", "value", "Lio/ktor/websocket/Frame;", "serializeContent", "(Lya/h;Lya/o;Ljava/lang/Object;)Lio/ktor/websocket/Frame;", "Ljava/nio/charset/Charset;", "Lio/ktor/utils/io/charsets/Charset;", HttpAuthHeader.Parameters.Charset, "Lio/ktor/util/reflect/TypeInfo;", "typeInfo", "serializeNullable", "(Ljava/nio/charset/Charset;Lio/ktor/util/reflect/TypeInfo;Ljava/lang/Object;Ld7/d;)Ljava/lang/Object;", "content", "deserialize", "(Ljava/nio/charset/Charset;Lio/ktor/util/reflect/TypeInfo;Lio/ktor/websocket/Frame;Ld7/d;)Ljava/lang/Object;", "frame", "", "isApplicable", "(Lio/ktor/websocket/Frame;)Z", "Lya/o;", "ktor-serialization-kotlinx"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class KotlinxWebsocketSerializationConverter implements WebsocketContentConverter {
    private final o format;

    public KotlinxWebsocketSerializationConverter(o oVar) {
        this.format = oVar;
        if ((oVar instanceof a) || (oVar instanceof x)) {
            return;
        }
        throw new IllegalArgumentException(("Only binary and string formats are supported, " + oVar + " is not supported.").toString());
    }

    private final Frame serializeContent(h<?> serializer, o format, Object value) {
        if (format instanceof x) {
            return new Frame.Text(((x) format).b(serializer, value));
        }
        if (format instanceof a) {
            return new Frame.Binary(true, ((a) format).e());
        }
        throw new IllegalStateException(("Unsupported format " + format).toString());
    }

    @Override // io.ktor.serialization.WebsocketContentConverter
    public Object deserialize(Charset charset, TypeInfo typeInfo, Frame frame, d<Object> dVar) throws IllegalAccessException, WebsocketDeserializeException, InvocationTargetException, WebsocketConverterNotFoundException {
        if (!isApplicable(frame)) {
            throw new WebsocketConverterNotFoundException("Unsupported frame " + frame.getFrameType().name(), null, 2, null);
        }
        h<?> hVarSerializerForTypeInfo = SerializerLookupKt.serializerForTypeInfo(this.format.a(), typeInfo);
        o oVar = this.format;
        if (oVar instanceof x) {
            if (frame instanceof Frame.Text) {
                return ((x) oVar).c(FrameCommonKt.readText((Frame.Text) frame), hVarSerializerForTypeInfo);
            }
            throw new WebsocketDeserializeException("Unsupported format " + this.format + " for " + frame.getFrameType().name(), null, frame, 2, null);
        }
        if (!(oVar instanceof a)) {
            throw new IllegalStateException(("Unsupported format " + this.format).toString());
        }
        if (frame instanceof Frame.Binary) {
            FrameCommonKt.readBytes(frame);
            return ((a) oVar).d();
        }
        throw new WebsocketDeserializeException("Unsupported format " + this.format + " for " + frame.getFrameType().name(), null, frame, 2, null);
    }

    @Override // io.ktor.serialization.WebsocketContentConverter
    public boolean isApplicable(Frame frame) {
        return (frame instanceof Frame.Text) || (frame instanceof Frame.Binary);
    }

    @Override // io.ktor.serialization.WebsocketContentConverter
    public Object serialize(Charset charset, TypeInfo typeInfo, Object obj, d<? super Frame> dVar) {
        return WebsocketContentConverter.DefaultImpls.serialize(this, charset, typeInfo, obj, dVar);
    }

    @Override // io.ktor.serialization.WebsocketContentConverter
    public Object serializeNullable(Charset charset, TypeInfo typeInfo, Object obj, d<? super Frame> dVar) throws IllegalAccessException, InvocationTargetException {
        h<?> hVarGuessSerializer;
        try {
            hVarGuessSerializer = SerializerLookupKt.serializerForTypeInfo(this.format.a(), typeInfo);
        } catch (SerializationException unused) {
            hVarGuessSerializer = SerializerLookupKt.guessSerializer(obj, this.format.a());
        }
        return serializeContent(hVarGuessSerializer, this.format, obj);
    }
}
