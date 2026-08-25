package io.ktor.utils.io;

import io.ktor.http.auth.HttpAuthHeader;
import io.ktor.utils.io.charsets.CharsetJVMKt;
import java.nio.charset.Charset;
import kotlin.Metadata;
import kotlin.text.a;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000$\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u001a\u000e\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003\u001a\u0016\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005\u001a\u001c\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0006\u001a\u00020\u00072\f\b\u0002\u0010\b\u001a\u00060\tj\u0002`\n¨\u0006\u000b"}, d2 = {"ByteReadChannel", "Lio/ktor/utils/io/ByteReadChannel;", "content", "", "offset", "", "text", "", HttpAuthHeader.Parameters.Charset, "Ljava/nio/charset/Charset;", "Lio/ktor/utils/io/charsets/Charset;", "ktor-io"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class ByteChannelCtorKt {
    public static final ByteReadChannel ByteReadChannel(byte[] bArr) {
        return ByteChannelKt.ByteReadChannel(bArr, 0, bArr.length);
    }

    public static /* synthetic */ ByteReadChannel ByteReadChannel$default(String str, Charset charset, int i10, Object obj) {
        if ((i10 & 2) != 0) {
            charset = a.f19924a;
        }
        return ByteReadChannel(str, charset);
    }

    public static final ByteReadChannel ByteReadChannel(byte[] bArr, int i10) {
        return ByteChannelKt.ByteReadChannel(bArr, i10, bArr.length);
    }

    public static final ByteReadChannel ByteReadChannel(String str, Charset charset) {
        byte[] bArrEncodeToByteArray;
        Charset charset2 = a.f19924a;
        if (charset.equals(charset2)) {
            bArrEncodeToByteArray = str.getBytes(charset2);
        } else {
            bArrEncodeToByteArray = CharsetJVMKt.encodeToByteArray(charset.newEncoder(), str, 0, str.length());
        }
        return ByteReadChannel(bArrEncodeToByteArray);
    }
}
