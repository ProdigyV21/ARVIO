package io.ktor.http;

import androidx.media3.extractor.text.ttml.TtmlNode;
import io.ktor.http.auth.HttpAuthHeader;
import io.ktor.util.date.GMTDateParser;
import io.ktor.utils.io.charsets.CharsetJVMKt;
import io.ktor.utils.io.charsets.EncodingKt;
import io.ktor.utils.io.core.ByteReadPacket;
import io.ktor.utils.io.core.internal.ChunkBuffer;
import io.ktor.utils.io.core.internal.UnsafeKt;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.q0;
import kotlin.collections.r;
import kotlin.collections.s;
import kotlin.collections.x;
import kotlin.jvm.internal.p;
import r7.l;
import t7.a;
import x6.t0;
import x7.c;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000T\n\u0002\u0010\u000e\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\"\n\u0002\u0010\f\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0010\r\n\u0002\b\u0003\n\u0002\u0010\u0005\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010 \n\u0002\b\b\u001a3\u0010\u0007\u001a\u00020\u0000*\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00012\b\b\u0002\u0010\u0003\u001a\u00020\u00012\f\b\u0002\u0010\u0006\u001a\u00060\u0004j\u0002`\u0005¢\u0006\u0004\b\u0007\u0010\b\u001a\u0011\u0010\t\u001a\u00020\u0000*\u00020\u0000¢\u0006\u0004\b\t\u0010\n\u001a\u0011\u0010\u000b\u001a\u00020\u0000*\u00020\u0000¢\u0006\u0004\b\u000b\u0010\n\u001a\u001b\u0010\t\u001a\u00020\u0000*\u00020\u00002\u0006\u0010\f\u001a\u00020\u0001H\u0000¢\u0006\u0004\b\t\u0010\r\u001a\u0011\u0010\u000e\u001a\u00020\u0000*\u00020\u0000¢\u0006\u0004\b\u000e\u0010\n\u001a\u001b\u0010\u000f\u001a\u00020\u0000*\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u0001¢\u0006\u0004\b\u000f\u0010\r\u001a!\u0010\u0013\u001a\u00020\u0000*\u00020\u00002\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00110\u0010H\u0000¢\u0006\u0004\b\u0013\u0010\u0014\u001a\u0013\u0010\u0015\u001a\u00020\u0000*\u00020\u0000H\u0000¢\u0006\u0004\b\u0015\u0010\n\u001a=\u0010\u001a\u001a\u00020\u0000*\u00020\u00002\b\b\u0002\u0010\u0017\u001a\u00020\u00162\b\b\u0002\u0010\u0018\u001a\u00020\u00162\b\b\u0002\u0010\u0019\u001a\u00020\u00012\f\b\u0002\u0010\u0006\u001a\u00060\u0004j\u0002`\u0005¢\u0006\u0004\b\u001a\u0010\u001b\u001a3\u0010\u001c\u001a\u00020\u0000*\u00020\u00002\b\b\u0002\u0010\u0017\u001a\u00020\u00162\b\b\u0002\u0010\u0018\u001a\u00020\u00162\f\b\u0002\u0010\u0006\u001a\u00060\u0004j\u0002`\u0005¢\u0006\u0004\b\u001c\u0010\u001d\u001a7\u0010\u001e\u001a\u00020\u0000*\u00020\u00002\u0006\u0010\u0017\u001a\u00020\u00162\u0006\u0010\u0018\u001a\u00020\u00162\u0006\u0010\u0019\u001a\u00020\u00012\n\u0010\u0006\u001a\u00060\u0004j\u0002`\u0005H\u0002¢\u0006\u0004\b\u001e\u0010\u001b\u001a?\u0010!\u001a\u00020\u0000*\u00020\u001f2\u0006\u0010\u0017\u001a\u00020\u00162\u0006\u0010\u0018\u001a\u00020\u00162\u0006\u0010 \u001a\u00020\u00162\u0006\u0010\u0019\u001a\u00020\u00012\n\u0010\u0006\u001a\u00060\u0004j\u0002`\u0005H\u0002¢\u0006\u0004\b!\u0010\"\u001a\u0013\u0010\u0013\u001a\u00020\u0000*\u00020#H\u0002¢\u0006\u0004\b\u0013\u0010$\u001a\u0017\u0010&\u001a\u00020\u00162\u0006\u0010%\u001a\u00020\u0011H\u0002¢\u0006\u0004\b&\u0010'\u001a\u0017\u0010)\u001a\u00020\u00112\u0006\u0010(\u001a\u00020\u0016H\u0002¢\u0006\u0004\b)\u0010*\u001a'\u0010/\u001a\u00020-*\u00020+2\u0012\u0010.\u001a\u000e\u0012\u0004\u0012\u00020#\u0012\u0004\u0012\u00020-0,H\u0002¢\u0006\u0004\b/\u00100\"\u001a\u00101\u001a\b\u0012\u0004\u0012\u00020#0\u00108\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b1\u00102\"\u001a\u00103\u001a\b\u0012\u0004\u0012\u00020\u00110\u00108\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b3\u00102\"\u001a\u00104\u001a\b\u0012\u0004\u0012\u00020\u00110\u00108\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b4\u00102\"\u001a\u00106\u001a\b\u0012\u0004\u0012\u00020#058\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b6\u00107\"\u001a\u00108\u001a\b\u0012\u0004\u0012\u00020\u00110\u00108\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b8\u00102\" \u00109\u001a\b\u0012\u0004\u0012\u00020\u00110\u00108\u0000X\u0080\u0004¢\u0006\f\n\u0004\b9\u00102\u001a\u0004\b:\u0010;\"\u001a\u0010<\u001a\b\u0012\u0004\u0012\u00020#058\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b<\u00107¨\u0006="}, d2 = {"", "", "encodeFull", "spaceToPlus", "Ljava/nio/charset/Charset;", "Lio/ktor/utils/io/charsets/Charset;", HttpAuthHeader.Parameters.Charset, "encodeURLQueryComponent", "(Ljava/lang/String;ZZLjava/nio/charset/Charset;)Ljava/lang/String;", "encodeURLPath", "(Ljava/lang/String;)Ljava/lang/String;", "encodeURLPathPart", "encodeSlash", "(Ljava/lang/String;Z)Ljava/lang/String;", "encodeOAuth", "encodeURLParameter", "", "", "allowedSet", "percentEncode", "(Ljava/lang/String;Ljava/util/Set;)Ljava/lang/String;", "encodeURLParameterValue", "", TtmlNode.START, TtmlNode.END, "plusIsSpace", "decodeURLQueryComponent", "(Ljava/lang/String;IIZLjava/nio/charset/Charset;)Ljava/lang/String;", "decodeURLPart", "(Ljava/lang/String;IILjava/nio/charset/Charset;)Ljava/lang/String;", "decodeScan", "", "prefixEnd", "decodeImpl", "(Ljava/lang/CharSequence;IIIZLjava/nio/charset/Charset;)Ljava/lang/String;", "", "(B)Ljava/lang/String;", "c2", "charToHexDigit", "(C)I", "digit", "hexDigitToChar", "(I)C", "Lio/ktor/utils/io/core/ByteReadPacket;", "Lkotlin/Function1;", "Lx6/t0;", "block", "forEach", "(Lio/ktor/utils/io/core/ByteReadPacket;Lr7/l;)V", "URL_ALPHABET", "Ljava/util/Set;", "URL_ALPHABET_CHARS", "HEX_ALPHABET", "", "URL_PROTOCOL_PART", "Ljava/util/List;", "VALID_PATH_PART", "ATTRIBUTE_CHARACTERS", "getATTRIBUTE_CHARACTERS", "()Ljava/util/Set;", "SPECIAL_SYMBOLS", "ktor-http"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class CodecsKt {
    private static final Set<Character> ATTRIBUTE_CHARACTERS;
    private static final Set<Character> HEX_ALPHABET;
    private static final List<Byte> SPECIAL_SYMBOLS;
    private static final Set<Byte> URL_ALPHABET;
    private static final Set<Character> URL_ALPHABET_CHARS;
    private static final List<Byte> URL_PROTOCOL_PART;
    private static final Set<Character> VALID_PATH_PART;

    static {
        Character chValueOf = Character.valueOf(GMTDateParser.ANY);
        ArrayList arrayListI0 = x.I0(x.F0(new c('a', GMTDateParser.ZONE), new c('A', 'Z')), new c('0', '9'));
        ArrayList arrayList = new ArrayList(s.U(arrayListI0, 10));
        Iterator it = arrayListI0.iterator();
        while (it.hasNext()) {
            arrayList.add(Byte.valueOf((byte) ((Character) it.next()).charValue()));
        }
        URL_ALPHABET = x.g1(arrayList);
        URL_ALPHABET_CHARS = x.g1(x.I0(x.F0(new c('a', GMTDateParser.ZONE), new c('A', 'Z')), new c('0', '9')));
        HEX_ALPHABET = x.g1(x.I0(x.F0(new c('a', 'f'), new c('A', 'F')), new c('0', '9')));
        Set setP0 = r.p0(new Character[]{':', '/', '?', '#', '[', ']', '@', '!', '$', '&', '\'', '(', ')', chValueOf, ',', ';', '=', '-', '.', '_', '~', '+'});
        ArrayList arrayList2 = new ArrayList(s.U(setP0, 10));
        Iterator it2 = setP0.iterator();
        while (it2.hasNext()) {
            arrayList2.add(Byte.valueOf((byte) ((Character) it2.next()).charValue()));
        }
        URL_PROTOCOL_PART = arrayList2;
        VALID_PATH_PART = r.p0(new Character[]{':', '@', '!', '$', '&', '\'', '(', ')', chValueOf, '+', ',', ';', '=', '-', '.', '_', '~'});
        ATTRIBUTE_CHARACTERS = q0.b0(URL_ALPHABET_CHARS, r.p0(new Character[]{'!', '#', '$', '&', '+', '-', '.', '^', '_', '`', '|', '~'}));
        List listE = a.E('-', '.', '_', '~');
        ArrayList arrayList3 = new ArrayList(s.U(listE, 10));
        Iterator it3 = listE.iterator();
        while (it3.hasNext()) {
            arrayList3.add(Byte.valueOf((byte) ((Character) it3.next()).charValue()));
        }
        SPECIAL_SYMBOLS = arrayList3;
    }

    private static final int charToHexDigit(char c10) {
        if ('0' <= c10 && c10 < ':') {
            return c10 - '0';
        }
        if ('A' <= c10 && c10 < 'G') {
            return c10 - '7';
        }
        if ('a' > c10 || c10 >= 'g') {
            return -1;
        }
        return c10 - 'W';
    }

    private static final String decodeImpl(CharSequence charSequence, int i10, int i11, int i12, boolean z, Charset charset) throws URLDecodeException {
        int i13 = i11 - i10;
        if (i13 > 255) {
            i13 /= 3;
        }
        StringBuilder sb2 = new StringBuilder(i13);
        if (i12 > i10) {
            sb2.append(charSequence, i10, i12);
        }
        byte[] bArr = null;
        while (i12 < i11) {
            char cCharAt = charSequence.charAt(i12);
            if (z && cCharAt == '+') {
                sb2.append(' ');
            } else if (cCharAt == '%') {
                if (bArr == null) {
                    bArr = new byte[(i11 - i12) / 3];
                }
                int i14 = 0;
                while (i12 < i11 && charSequence.charAt(i12) == '%') {
                    int i15 = i12 + 2;
                    if (i15 >= i11) {
                        StringBuilder sb3 = new StringBuilder("Incomplete trailing HEX escape: ");
                        sb3.append(charSequence.subSequence(i12, charSequence.length()).toString());
                        sb3.append(", in ");
                        sb3.append((Object) charSequence);
                        throw new URLDecodeException(androidx.compose.foundation.c.p(i12, " at ", sb3));
                    }
                    int i16 = i12 + 1;
                    int iCharToHexDigit = charToHexDigit(charSequence.charAt(i16));
                    int iCharToHexDigit2 = charToHexDigit(charSequence.charAt(i15));
                    if (iCharToHexDigit == -1 || iCharToHexDigit2 == -1) {
                        throw new URLDecodeException("Wrong HEX escape: %" + charSequence.charAt(i16) + charSequence.charAt(i15) + ", in " + ((Object) charSequence) + ", at " + i12);
                    }
                    bArr[i14] = (byte) ((iCharToHexDigit * 16) + iCharToHexDigit2);
                    i12 += 3;
                    i14++;
                }
                sb2.append(new String(bArr, 0, i14, charset));
            } else {
                sb2.append(cCharAt);
            }
            i12++;
        }
        return sb2.toString();
    }

    private static final String decodeScan(String str, int i10, int i11, boolean z, Charset charset) {
        for (int i12 = i10; i12 < i11; i12++) {
            char cCharAt = str.charAt(i12);
            if (cCharAt == '%' || (z && cCharAt == '+')) {
                return decodeImpl(str, i10, i11, i12, z, charset);
            }
        }
        return (i10 == 0 && i11 == str.length()) ? str.toString() : str.substring(i10, i11);
    }

    public static final String decodeURLPart(String str, int i10, int i11, Charset charset) {
        return decodeScan(str, i10, i11, false, charset);
    }

    public static /* synthetic */ String decodeURLPart$default(String str, int i10, int i11, Charset charset, int i12, Object obj) {
        if ((i12 & 1) != 0) {
            i10 = 0;
        }
        if ((i12 & 2) != 0) {
            i11 = str.length();
        }
        if ((i12 & 4) != 0) {
            charset = kotlin.text.a.f19924a;
        }
        return decodeURLPart(str, i10, i11, charset);
    }

    public static final String decodeURLQueryComponent(String str, int i10, int i11, boolean z, Charset charset) {
        return decodeScan(str, i10, i11, z, charset);
    }

    public static /* synthetic */ String decodeURLQueryComponent$default(String str, int i10, int i11, boolean z, Charset charset, int i12, Object obj) {
        if ((i12 & 1) != 0) {
            i10 = 0;
        }
        if ((i12 & 2) != 0) {
            i11 = str.length();
        }
        if ((i12 & 4) != 0) {
            z = false;
        }
        if ((i12 & 8) != 0) {
            charset = kotlin.text.a.f19924a;
        }
        return decodeURLQueryComponent(str, i10, i11, z, charset);
    }

    public static final String encodeOAuth(String str) {
        return encodeURLParameter$default(str, false, 1, null);
    }

    public static final String encodeURLParameter(String str, boolean z) throws Throwable {
        StringBuilder sb2 = new StringBuilder();
        forEach(EncodingKt.encode$default(kotlin.text.a.f19924a.newEncoder(), str, 0, 0, 6, null), new CodecsKt$encodeURLParameter$1$1(sb2, z));
        return sb2.toString();
    }

    public static /* synthetic */ String encodeURLParameter$default(String str, boolean z, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            z = false;
        }
        return encodeURLParameter(str, z);
    }

    public static final String encodeURLParameterValue(String str) {
        return encodeURLParameter(str, true);
    }

    public static final String encodeURLPath(String str) {
        return encodeURLPath(str, false);
    }

    public static final String encodeURLPathPart(String str) {
        return encodeURLPath(str, true);
    }

    public static final String encodeURLQueryComponent(String str, boolean z, boolean z5, Charset charset) throws Throwable {
        StringBuilder sb2 = new StringBuilder();
        forEach(EncodingKt.encode$default(charset.newEncoder(), str, 0, 0, 6, null), new CodecsKt$encodeURLQueryComponent$1$1(z5, sb2, z));
        return sb2.toString();
    }

    public static /* synthetic */ String encodeURLQueryComponent$default(String str, boolean z, boolean z5, Charset charset, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            z = false;
        }
        if ((i10 & 2) != 0) {
            z5 = false;
        }
        if ((i10 & 4) != 0) {
            charset = kotlin.text.a.f19924a;
        }
        return encodeURLQueryComponent(str, z, z5, charset);
    }

    private static final void forEach(ByteReadPacket byteReadPacket, l<? super Byte, t0> lVar) throws Throwable {
        boolean z = true;
        ChunkBuffer chunkBufferPrepareReadFirstHead = UnsafeKt.prepareReadFirstHead(byteReadPacket, 1);
        if (chunkBufferPrepareReadFirstHead == null) {
            return;
        }
        while (true) {
            try {
                if (chunkBufferPrepareReadFirstHead.getWritePosition() > chunkBufferPrepareReadFirstHead.getReadPosition()) {
                    lVar.invoke(Byte.valueOf(chunkBufferPrepareReadFirstHead.readByte()));
                } else {
                    try {
                        chunkBufferPrepareReadFirstHead = UnsafeKt.prepareReadNextHead(byteReadPacket, chunkBufferPrepareReadFirstHead);
                        if (chunkBufferPrepareReadFirstHead == null) {
                            return;
                        }
                    } catch (Throwable th) {
                        th = th;
                        z = false;
                        if (z) {
                            UnsafeKt.completeReadHead(byteReadPacket, chunkBufferPrepareReadFirstHead);
                        }
                        throw th;
                    }
                }
            } catch (Throwable th2) {
                th = th2;
            }
        }
    }

    public static final Set<Character> getATTRIBUTE_CHARACTERS() {
        return ATTRIBUTE_CHARACTERS;
    }

    private static final char hexDigitToChar(int i10) {
        return (char) ((i10 < 0 || i10 >= 10) ? ((char) (i10 + 65)) - '\n' : i10 + 48);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final String percentEncode(byte b10) {
        return new String(new char[]{'%', hexDigitToChar((b10 & 255) >> 4), hexDigitToChar(b10 & 15)});
    }

    public static final String encodeURLPath(String str, boolean z) throws Throwable {
        int i10;
        StringBuilder sb2 = new StringBuilder();
        Charset charset = kotlin.text.a.f19924a;
        int i11 = 0;
        while (i11 < str.length()) {
            char cCharAt = str.charAt(i11);
            if ((!z && cCharAt == '/') || URL_ALPHABET_CHARS.contains(Character.valueOf(cCharAt)) || VALID_PATH_PART.contains(Character.valueOf(cCharAt))) {
                sb2.append(cCharAt);
                i11++;
            } else {
                if (cCharAt == '%' && (i10 = i11 + 2) < str.length()) {
                    Set<Character> set = HEX_ALPHABET;
                    int i12 = i11 + 1;
                    if (set.contains(Character.valueOf(str.charAt(i12))) && set.contains(Character.valueOf(str.charAt(i10)))) {
                        sb2.append(cCharAt);
                        sb2.append(str.charAt(i12));
                        sb2.append(str.charAt(i10));
                        i11 += 3;
                    }
                }
                int i13 = ((55296 > cCharAt || cCharAt >= 57344) ? 1 : 2) + i11;
                forEach(EncodingKt.encode(charset.newEncoder(), str, i11, i13), new CodecsKt$encodeURLPath$1$1(sb2));
                i11 = i13;
            }
        }
        return sb2.toString();
    }

    public static final String percentEncode(String str, Set<Character> set) {
        byte[] bArrEncodeToByteArray;
        int i10 = 0;
        for (int i11 = 0; i11 < str.length(); i11++) {
            if (!set.contains(Character.valueOf(str.charAt(i11)))) {
                i10++;
            }
        }
        if (i10 == 0) {
            return str;
        }
        Charset charset = kotlin.text.a.f19924a;
        if (p.a(charset, charset)) {
            bArrEncodeToByteArray = str.getBytes(charset);
        } else {
            bArrEncodeToByteArray = CharsetJVMKt.encodeToByteArray(charset.newEncoder(), str, 0, str.length());
        }
        int length = str.length() - i10;
        char[] cArr = new char[((bArrEncodeToByteArray.length - length) * 3) + length];
        int i12 = 0;
        for (byte b10 : bArrEncodeToByteArray) {
            char c10 = (char) b10;
            if (set.contains(Character.valueOf(c10))) {
                cArr[i12] = c10;
                i12++;
            } else {
                cArr[i12] = '%';
                int i13 = i12 + 2;
                cArr[i12 + 1] = hexDigitToChar((b10 & 255) >> 4);
                i12 += 3;
                cArr[i13] = hexDigitToChar(b10 & 15);
            }
        }
        return new String(cArr);
    }
}
