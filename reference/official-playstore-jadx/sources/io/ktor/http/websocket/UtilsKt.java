package io.ktor.http.websocket;

import io.ktor.util.Base64Kt;
import io.ktor.util.CryptoKt;
import io.ktor.utils.io.charsets.CharsetJVMKt;
import java.nio.charset.Charset;
import kotlin.Metadata;
import kotlin.jvm.internal.p;
import kotlin.text.a;
import kotlin.text.o;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\u001a\u000e\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0001\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0004"}, d2 = {"WEBSOCKET_SERVER_ACCEPT_TAIL", "", "websocketServerAccept", "nonce", "ktor-http"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class UtilsKt {
    private static final String WEBSOCKET_SERVER_ACCEPT_TAIL = "258EAFA5-E914-47DA-95CA-C5AB0DC85B11";

    public static final String websocketServerAccept(String str) {
        String str2 = o.L0(str).toString() + WEBSOCKET_SERVER_ACCEPT_TAIL;
        Charset charset = a.f19926c;
        Charset charset2 = a.f19924a;
        return Base64Kt.encodeBase64(CryptoKt.sha1(p.a(charset, charset2) ? str2.getBytes(charset2) : CharsetJVMKt.encodeToByteArray(charset.newEncoder(), str2, 0, str2.length())));
    }
}
