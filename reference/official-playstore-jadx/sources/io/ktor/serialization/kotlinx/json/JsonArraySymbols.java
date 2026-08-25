package io.ktor.serialization.kotlinx.json;

import io.ktor.http.auth.HttpAuthHeader;
import io.ktor.utils.io.charsets.CharsetJVMKt;
import java.nio.charset.Charset;
import kotlin.Metadata;
import kotlin.text.a;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0012\n\u0002\b\u0007\b\u0002\u0018\u00002\u00020\u0001B\u0011\u0012\n\u0010\u0002\u001a\u00060\u0003j\u0002`\u0004¢\u0006\u0002\u0010\u0005R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\n\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\tR\u0011\u0010\f\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\t¨\u0006\u000e"}, d2 = {"Lio/ktor/serialization/kotlinx/json/JsonArraySymbols;", "", HttpAuthHeader.Parameters.Charset, "Ljava/nio/charset/Charset;", "Lio/ktor/utils/io/charsets/Charset;", "(Ljava/nio/charset/Charset;)V", "beginArray", "", "getBeginArray", "()[B", "endArray", "getEndArray", "objectSeparator", "getObjectSeparator", "ktor-serialization-kotlinx-json"}, k = 1, mv = {1, 8, 0}, xi = 48)
final class JsonArraySymbols {
    private final byte[] beginArray;
    private final byte[] endArray;
    private final byte[] objectSeparator;

    public JsonArraySymbols(Charset charset) {
        Charset charset2 = a.f19924a;
        this.beginArray = charset.equals(charset2) ? "[".getBytes(charset2) : CharsetJVMKt.encodeToByteArray(charset.newEncoder(), "[", 0, 1);
        this.endArray = charset.equals(charset2) ? "]".getBytes(charset2) : CharsetJVMKt.encodeToByteArray(charset.newEncoder(), "]", 0, 1);
        this.objectSeparator = charset.equals(charset2) ? ",".getBytes(charset2) : CharsetJVMKt.encodeToByteArray(charset.newEncoder(), ",", 0, 1);
    }

    public final byte[] getBeginArray() {
        return this.beginArray;
    }

    public final byte[] getEndArray() {
        return this.endArray;
    }

    public final byte[] getObjectSeparator() {
        return this.objectSeparator;
    }
}
