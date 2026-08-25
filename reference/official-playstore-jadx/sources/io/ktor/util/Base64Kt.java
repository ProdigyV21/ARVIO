package io.ktor.util;

import io.ktor.utils.io.core.BytePacketBuilder;
import io.ktor.utils.io.core.ByteReadPacket;
import io.ktor.utils.io.core.Input;
import io.ktor.utils.io.core.InputArraysKt;
import io.ktor.utils.io.core.StringsKt;
import java.nio.charset.Charset;
import kotlin.Metadata;
import kotlin.text.a;
import kotlin.text.o;
import kotlin.text.u;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u00000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0015\n\u0000\n\u0002\u0010\u0005\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\f\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0012\n\u0002\b\u0005\u001a\n\u0010\n\u001a\u00020\u000b*\u00020\f\u001a\n\u0010\n\u001a\u00020\r*\u00020\u0001\u001a\n\u0010\u000e\u001a\u00020\u0001*\u00020\u0001\u001a\n\u0010\u000f\u001a\u00020\u0001*\u00020\f\u001a\n\u0010\u000f\u001a\u00020\u0001*\u00020\r\u001a\n\u0010\u000f\u001a\u00020\u0001*\u00020\u0001\u001a\r\u0010\u0010\u001a\u00020\u0005*\u00020\u0005H\u0080\b\u001a\r\u0010\u0011\u001a\u00020\t*\u00020\u0007H\u0080\b\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000\"\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0006\u001a\u00020\u0007X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\b\u001a\u00020\tX\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0012"}, d2 = {"BASE64_ALPHABET", "", "BASE64_INVERSE_ALPHABET", "", "BASE64_MASK", "", "BASE64_MASK_INT", "", "BASE64_PAD", "", "decodeBase64Bytes", "Lio/ktor/utils/io/core/Input;", "Lio/ktor/utils/io/core/ByteReadPacket;", "", "decodeBase64String", "encodeBase64", "fromBase64", "toBase64", "ktor-utils"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class Base64Kt {
    private static final String BASE64_ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/";
    private static final int[] BASE64_INVERSE_ALPHABET;
    private static final byte BASE64_MASK = 63;
    private static final int BASE64_MASK_INT = 63;
    private static final char BASE64_PAD = '=';

    static {
        int[] iArr = new int[256];
        for (int i10 = 0; i10 < 256; i10++) {
            iArr[i10] = o.e0(BASE64_ALPHABET, (char) i10, 0, false, 6);
        }
        BASE64_INVERSE_ALPHABET = iArr;
    }

    public static final byte[] decodeBase64Bytes(String str) {
        String strSubstring;
        BytePacketBuilder bytePacketBuilder = new BytePacketBuilder(null, 1, null);
        try {
            int iB0 = o.b0(str);
            while (true) {
                if (-1 >= iB0) {
                    strSubstring = "";
                    break;
                }
                if (str.charAt(iB0) != '=') {
                    strSubstring = str.substring(0, iB0 + 1);
                    break;
                }
                iB0--;
            }
            StringsKt.writeText$default(bytePacketBuilder, strSubstring, 0, 0, (Charset) null, 14, (Object) null);
            return StringsKt.readBytes(decodeBase64Bytes(bytePacketBuilder.build()));
        } catch (Throwable th) {
            bytePacketBuilder.release();
            throw th;
        }
    }

    public static final String decodeBase64String(String str) {
        byte[] bArrDecodeBase64Bytes = decodeBase64Bytes(str);
        return new String(bArrDecodeBase64Bytes, 0, bArrDecodeBase64Bytes.length, a.f19924a);
    }

    public static final String encodeBase64(byte[] bArr) {
        int i10;
        int i11;
        int i12 = 3;
        char[] cArr = new char[((bArr.length * 8) / 6) + 3];
        int i13 = 0;
        int i14 = 0;
        while (true) {
            int i15 = i13 + 3;
            if (i15 > bArr.length) {
                break;
            }
            int i16 = (bArr[i13 + 2] & 255) | ((bArr[i13] & 255) << 16) | ((bArr[i13 + 1] & 255) << 8);
            int i17 = 3;
            while (-1 < i17) {
                cArr[i14] = BASE64_ALPHABET.charAt((i16 >> (i17 * 6)) & 63);
                i17--;
                i14++;
            }
            i13 = i15;
        }
        int length = bArr.length - i13;
        if (length == 0) {
            return u.I(cArr, 0, i14);
        }
        if (length == 1) {
            i10 = (bArr[i13] & 255) << 16;
        } else {
            i10 = ((bArr[i13 + 1] & 255) << 8) | ((bArr[i13] & 255) << 16);
        }
        int i18 = ((3 - length) * 8) / 6;
        if (i18 <= 3) {
            while (true) {
                i11 = i14 + 1;
                cArr[i14] = BASE64_ALPHABET.charAt((i10 >> (i12 * 6)) & 63);
                if (i12 == i18) {
                    break;
                }
                i12--;
                i14 = i11;
            }
            i14 = i11;
        }
        int i19 = 0;
        while (i19 < i18) {
            cArr[i14] = BASE64_PAD;
            i19++;
            i14++;
        }
        return u.I(cArr, 0, i14);
    }

    public static final byte fromBase64(byte b10) {
        return (byte) (((byte) BASE64_INVERSE_ALPHABET[b10 & 255]) & BASE64_MASK);
    }

    public static final char toBase64(int i10) {
        return BASE64_ALPHABET.charAt(i10);
    }

    public static final Input decodeBase64Bytes(ByteReadPacket byteReadPacket) {
        int i10;
        BytePacketBuilder bytePacketBuilder = new BytePacketBuilder(null, 1, null);
        try {
            byte[] bArr = new byte[4];
            while (byteReadPacket.getRemaining() > 0) {
                ByteReadPacket byteReadPacket2 = byteReadPacket;
                int available$default = InputArraysKt.readAvailable$default((Input) byteReadPacket2, bArr, 0, 0, 6, (Object) null);
                int i11 = 0;
                int i12 = 0;
                int i13 = 0;
                while (i11 < 4) {
                    i12 |= ((byte) (((byte) BASE64_INVERSE_ALPHABET[bArr[i11] & 255]) & BASE64_MASK)) << ((3 - i13) * 6);
                    i11++;
                    i13++;
                }
                int i14 = 4 - available$default;
                if (i14 <= 2) {
                    while (true) {
                        bytePacketBuilder.writeByte((byte) ((i12 >> (i10 * 8)) & 255));
                        i10 = i10 != i14 ? i10 - 1 : 2;
                    }
                }
                byteReadPacket = byteReadPacket2;
            }
            return bytePacketBuilder.build();
        } catch (Throwable th) {
            bytePacketBuilder.release();
            throw th;
        }
    }

    public static final String encodeBase64(ByteReadPacket byteReadPacket) {
        return encodeBase64(StringsKt.readBytes$default(byteReadPacket, 0, 1, null));
    }

    public static final String encodeBase64(String str) {
        BytePacketBuilder bytePacketBuilder = new BytePacketBuilder(null, 1, null);
        try {
            StringsKt.writeText$default(bytePacketBuilder, str, 0, 0, (Charset) null, 14, (Object) null);
            return encodeBase64(bytePacketBuilder.build());
        } catch (Throwable th) {
            bytePacketBuilder.release();
            throw th;
        }
    }
}
