package io.ktor.utils.io.core;

import io.ktor.http.auth.HttpAuthHeader;
import java.nio.charset.Charset;
import kotlin.Metadata;
import kotlin.text.a;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u00000\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0019\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a:\u0010\t\u001a\u00020\b2\u0006\u0010\u0001\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\b\b\u0002\u0010\u0004\u001a\u00020\u00022\f\b\u0002\u0010\u0007\u001a\u00060\u0005j\u0002`\u0006H\u0086\b¢\u0006\u0004\b\t\u0010\n\u001a#\u0010\u000f\u001a\u00020\u000e*\u00020\b2\u0006\u0010\f\u001a\u00020\u000b2\u0006\u0010\r\u001a\u00020\u0002H\u0000¢\u0006\u0004\b\u000f\u0010\u0010¨\u0006\u0011"}, d2 = {"", "bytes", "", "offset", "length", "Ljava/nio/charset/Charset;", "Lio/ktor/utils/io/charsets/Charset;", HttpAuthHeader.Parameters.Charset, "", "String", "([BIILjava/nio/charset/Charset;)Ljava/lang/String;", "", "dst", "dstOffset", "Lx6/t0;", "getCharsInternal", "(Ljava/lang/String;[CI)V", "ktor-io"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class StringsJVMKt {
    public static final String String(byte[] bArr, int i10, int i11, Charset charset) {
        return new String(bArr, i10, i11, charset);
    }

    public static /* synthetic */ String String$default(byte[] bArr, int i10, int i11, Charset charset, int i12, Object obj) {
        if ((i12 & 2) != 0) {
            i10 = 0;
        }
        if ((i12 & 4) != 0) {
            i11 = bArr.length;
        }
        if ((i12 & 8) != 0) {
            charset = a.f19924a;
        }
        return new String(bArr, i10, i11, charset);
    }

    public static final void getCharsInternal(String str, char[] cArr, int i10) {
        str.getChars(0, str.length(), cArr, i10);
    }
}
