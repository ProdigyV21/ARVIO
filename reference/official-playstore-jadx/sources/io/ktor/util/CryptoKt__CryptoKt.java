package io.ktor.util;

import androidx.media3.exoplayer.upstream.CmcdData;
import com.google.common.util.concurrent.r0;
import d7.d;
import io.ktor.http.ContentDisposition;
import io.ktor.http.auth.HttpAuthHeader;
import io.ktor.utils.io.charsets.CharsetJVMKt;
import io.ktor.utils.io.core.BytePacketBuilder;
import io.ktor.utils.io.core.StringsKt;
import java.nio.charset.Charset;
import kotlin.Metadata;
import kotlin.jvm.internal.p;
import kotlin.text.a;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u00002\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0019\n\u0002\b\u0005\u001a\u0015\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u0000¢\u0006\u0004\b\u0003\u0010\u0004\u001a\u0015\u0010\u0003\u001a\u00020\u00002\u0006\u0010\u0005\u001a\u00020\u0002¢\u0006\u0004\b\u0003\u0010\u0006\u001a\u0015\u0010\t\u001a\u00020\u00002\u0006\u0010\b\u001a\u00020\u0007¢\u0006\u0004\b\t\u0010\n\u001a\u001f\u0010\f\u001a\u00020\u0000*\u00020\u000b2\u0006\u0010\u0001\u001a\u00020\u0000H\u0087@ø\u0001\u0000¢\u0006\u0004\b\f\u0010\r\u001a-\u0010\f\u001a\u00020\u0000*\u00020\u000b2\u0006\u0010\u000e\u001a\u00020\u00022\f\b\u0002\u0010\u0011\u001a\u00060\u000fj\u0002`\u0010H\u0087@ø\u0001\u0000¢\u0006\u0004\b\f\u0010\u0012\"\u0014\u0010\u0014\u001a\u00020\u00138\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0014\u0010\u0015\"\u0014\u0010\u0016\u001a\u00020\u00078\u0000X\u0080T¢\u0006\u0006\n\u0004\b\u0016\u0010\u0017\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0018"}, d2 = {"", "bytes", "", "hex", "([B)Ljava/lang/String;", CmcdData.STREAMING_FORMAT_SS, "(Ljava/lang/String;)[B", "", ContentDisposition.Parameters.Size, "generateNonce", "(I)[B", "Lio/ktor/util/Digest;", "build", "(Lio/ktor/util/Digest;[BLd7/d;)Ljava/lang/Object;", "string", "Ljava/nio/charset/Charset;", "Lio/ktor/utils/io/charsets/Charset;", HttpAuthHeader.Parameters.Charset, "(Lio/ktor/util/Digest;Ljava/lang/String;Ljava/nio/charset/Charset;Ld7/d;)Ljava/lang/Object;", "", "digits", "[C", "NONCE_SIZE_IN_BYTES", "I", "ktor-utils"}, k = 5, mv = {1, 8, 0}, xi = 48, xs = "io/ktor/util/CryptoKt")
final /* synthetic */ class CryptoKt__CryptoKt {
    private static final char[] digits = CharsetKt.toCharArray("0123456789abcdef");

    @InternalAPI
    public static final Object build(Digest digest, byte[] bArr, d<? super byte[]> dVar) {
        digest.plusAssign(bArr);
        return digest.build(dVar);
    }

    public static /* synthetic */ Object build$default(Digest digest, String str, Charset charset, d dVar, int i10, Object obj) {
        if ((i10 & 2) != 0) {
            charset = a.f19924a;
        }
        return CryptoKt.build(digest, str, charset, dVar);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static final byte[] generateNonce(int i10) {
        BytePacketBuilder bytePacketBuilder = new BytePacketBuilder(null, 1, 0 == true ? 1 : 0);
        while (bytePacketBuilder.getSize() < i10) {
            try {
                StringsKt.writeText$default(bytePacketBuilder, CryptoKt.generateNonce(), 0, 0, (Charset) null, 14, (Object) null);
            } catch (Throwable th) {
                bytePacketBuilder.release();
                throw th;
            }
        }
        return StringsKt.readBytes(bytePacketBuilder.build(), i10);
    }

    public static final String hex(byte[] bArr) {
        char[] cArr = new char[bArr.length * 2];
        char[] cArr2 = digits;
        int i10 = 0;
        for (byte b10 : bArr) {
            int i11 = i10 + 1;
            cArr[i10] = cArr2[(b10 & 255) >> 4];
            i10 += 2;
            cArr[i11] = cArr2[b10 & 15];
        }
        return new String(cArr);
    }

    @InternalAPI
    public static final Object build(Digest digest, String str, Charset charset, d<? super byte[]> dVar) {
        byte[] bArrEncodeToByteArray;
        Charset charset2 = a.f19924a;
        if (p.a(charset, charset2)) {
            bArrEncodeToByteArray = str.getBytes(charset2);
        } else {
            bArrEncodeToByteArray = CharsetJVMKt.encodeToByteArray(charset.newEncoder(), str, 0, str.length());
        }
        digest.plusAssign(bArrEncodeToByteArray);
        return digest.build(dVar);
    }

    public static final byte[] hex(String str) {
        int length = str.length() / 2;
        byte[] bArr = new byte[length];
        for (int i10 = 0; i10 < length; i10++) {
            int i11 = i10 * 2;
            String strValueOf = String.valueOf(str.charAt(i11));
            r0.c(16);
            int i12 = Integer.parseInt(strValueOf, 16) << 4;
            String strValueOf2 = String.valueOf(str.charAt(i11 + 1));
            r0.c(16);
            bArr[i10] = (byte) (Integer.parseInt(strValueOf2, 16) | i12);
        }
        return bArr;
    }
}
