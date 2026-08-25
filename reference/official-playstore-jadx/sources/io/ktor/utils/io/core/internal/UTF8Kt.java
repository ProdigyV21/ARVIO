package io.ktor.utils.io.core.internal;

import androidx.media3.exoplayer.analytics.AnalyticsListener;
import androidx.media3.extractor.ts.PsExtractor;
import d7.d;
import f7.c;
import f7.e;
import io.ktor.http.ContentDisposition;
import io.ktor.network.sockets.DatagramKt;
import io.ktor.utils.io.core.Buffer;
import java.io.EOFException;
import java.nio.ByteBuffer;
import kotlin.KotlinNothingValueException;
import kotlin.Metadata;
import r7.l;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\f\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0001\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0010\r\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b'\u001a+\u0010\u0005\u001a\u00020\u0003*\u00020\u00002\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001H\u0080\bø\u0001\u0000¢\u0006\u0004\b\u0005\u0010\u0006\u001aa\u0010\u0013\u001a\u00020\u00032\n\u0010\t\u001a\u00060\u0007j\u0002`\b2\u0006\u0010\u000b\u001a\u00020\n2$\u0010\u0010\u001a \b\u0001\u0012\u0004\u0012\u00020\n\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u00010\u000e0\r\u0012\u0006\u0012\u0004\u0018\u00010\u000f0\f2\u0012\u0010\u0012\u001a\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u00110\u0001H\u0080@ø\u0001\u0001¢\u0006\u0004\b\u0013\u0010\u0014\u001a\u0017\u0010\u0017\u001a\u00020\u00162\u0006\u0010\u0015\u001a\u00020\nH\u0002¢\u0006\u0004\b\u0017\u0010\u0018\u001a\u0017\u0010\u001a\u001a\u00020\n2\u0006\u0010\u0019\u001a\u00020\nH\u0000¢\u0006\u0004\b\u001a\u0010\u001b\u001a+\u0010\u001c\u001a\u00020\n*\u00020\u00002\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001H\u0080\bø\u0001\u0000¢\u0006\u0004\b\u001c\u0010\u001d\u001aA\u0010(\u001a\u00020%*\u00020\u001e2\u0006\u0010 \u001a\u00020\u001f2\u0006\u0010!\u001a\u00020\n2\u0006\u0010\"\u001a\u00020\n2\u0006\u0010#\u001a\u00020\n2\u0006\u0010$\u001a\u00020\nH\u0000ø\u0001\u0002ø\u0001\u0001¢\u0006\u0004\b&\u0010'\u001aQ\u0010/\u001a\u00020%*\u00020\u001e2\u0006\u0010 \u001a\u00020\u001f2\u0006\u0010)\u001a\u00020\n2\u0006\u0010*\u001a\u00020\n2\u0006\u0010!\u001a\u00020\n2\u0006\u0010+\u001a\u00020\n2\u0006\u0010,\u001a\u00020\n2\u0006\u0010#\u001a\u00020\nH\u0002ø\u0001\u0002ø\u0001\u0001¢\u0006\u0004\b-\u0010.\u001aQ\u00101\u001a\u00020%*\u00020\u001e2\u0006\u0010 \u001a\u00020\u001f2\u0006\u0010)\u001a\u00020\n2\u0006\u0010*\u001a\u00020\n2\u0006\u0010!\u001a\u00020\n2\u0006\u0010+\u001a\u00020\n2\u0006\u0010,\u001a\u00020\n2\u0006\u0010#\u001a\u00020\nH\u0002ø\u0001\u0002ø\u0001\u0001¢\u0006\u0004\b0\u0010.\u001a\u0018\u00103\u001a\u00020\n2\u0006\u00102\u001a\u00020\nH\u0082\b¢\u0006\u0004\b3\u0010\u001b\u001a*\u00107\u001a\u00020\n*\u00020\u001e2\u0006\u00104\u001a\u00020\n2\u0006\u00102\u001a\u00020\nH\u0080\bø\u0001\u0002ø\u0001\u0001¢\u0006\u0004\b5\u00106\u001a\u0017\u00109\u001a\u00020\u00162\u0006\u00108\u001a\u00020\nH\u0001¢\u0006\u0004\b9\u0010\u0018\u001a\u0017\u0010;\u001a\u00020\u00162\u0006\u0010:\u001a\u00020\nH\u0001¢\u0006\u0004\b;\u0010\u0018\u001a\u0017\u0010=\u001a\u00020\u00032\u0006\u0010<\u001a\u00020\nH\u0001¢\u0006\u0004\b=\u0010>\u001a\u0017\u0010@\u001a\u00020\u00032\u0006\u0010?\u001a\u00020\nH\u0001¢\u0006\u0004\b@\u0010>\u001a\u0017\u0010A\u001a\u00020\n2\u0006\u0010<\u001a\u00020\nH\u0001¢\u0006\u0004\bA\u0010\u001b\u001a\u0017\u0010B\u001a\u00020\n2\u0006\u0010<\u001a\u00020\nH\u0001¢\u0006\u0004\bB\u0010\u001b\u001a\u001f\u0010?\u001a\u00020\n2\u0006\u0010C\u001a\u00020\u00022\u0006\u0010D\u001a\u00020\u0002H\u0000¢\u0006\u0004\b?\u0010E\"\u0014\u0010F\u001a\u00020\n8\u0002X\u0082T¢\u0006\u0006\n\u0004\bF\u0010G\"\u0014\u0010H\u001a\u00020\n8\u0002X\u0082T¢\u0006\u0006\n\u0004\bH\u0010G\"\u0014\u0010I\u001a\u00020\n8\u0002X\u0082T¢\u0006\u0006\n\u0004\bI\u0010G\"\u0014\u0010J\u001a\u00020\n8\u0002X\u0082T¢\u0006\u0006\n\u0004\bJ\u0010G\"\u0014\u0010K\u001a\u00020\n8\u0002X\u0082T¢\u0006\u0006\n\u0004\bK\u0010G\u0082\u0002\u0012\n\u0005\b\u009920\u0001\n\u0002\b\u0019\n\u0005\b¡\u001e0\u0001¨\u0006L"}, d2 = {"Lio/ktor/utils/io/core/Buffer;", "Lkotlin/Function1;", "", "", "consumer", "decodeASCII", "(Lio/ktor/utils/io/core/Buffer;Lr7/l;)Z", "Ljava/lang/Appendable;", "Lkotlin/text/Appendable;", "out", "", "limit", "Lkotlin/Function2;", "Ld7/d;", "Lio/ktor/utils/io/core/Input;", "", "nextChunk", "Lx6/t0;", "afterRead", "decodeUTF8LineLoopSuspend", "(Ljava/lang/Appendable;ILr7/p;Lr7/l;Ld7/d;)Ljava/lang/Object;", ContentDisposition.Parameters.Size, "", "prematureEndOfStreamUtf", "(I)Ljava/lang/Void;", "firstByte", "byteCountUtf8", "(I)I", "decodeUTF8", "(Lio/ktor/utils/io/core/Buffer;Lr7/l;)I", "Lio/ktor/utils/io/bits/Memory;", "", "text", "from", "to", "dstOffset", "dstLimit", "Lio/ktor/utils/io/core/internal/EncodeResult;", "encodeUTF8-lBXzO7A", "(Ljava/nio/ByteBuffer;Ljava/lang/CharSequence;IIII)I", "encodeUTF8", "index1", "lastCharIndex", "resultPosition1", "resultLimit", "encodeUTF8Stage1-Vm9B2pQ", "(Ljava/nio/ByteBuffer;Ljava/lang/CharSequence;IIIIII)I", "encodeUTF8Stage1", "encodeUTF8Stage2-Vm9B2pQ", "encodeUTF8Stage2", "v", "charactersSize", "offset", "putUtf8Char-62zg_DM", "(Ljava/nio/ByteBuffer;II)I", "putUtf8Char", "byteCount", "malformedByteCount", "value", "malformedCodePoint", "cp", "isBmpCodePoint", "(I)Z", "codePoint", "isValidCodePoint", "lowSurrogate", "highSurrogate", "high", "low", "(CC)I", "MaxCodePoint", "I", "MinLowSurrogate", "MinHighSurrogate", "MinSupplementary", "HighSurrogateMagic", "ktor-io"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class UTF8Kt {
    private static final int HighSurrogateMagic = 55232;
    private static final int MaxCodePoint = 1114111;
    private static final int MinHighSurrogate = 55296;
    private static final int MinLowSurrogate = 56320;
    private static final int MinSupplementary = 65536;

    /* JADX INFO: renamed from: io.ktor.utils.io.core.internal.UTF8Kt$decodeUTF8LineLoopSuspend$1, reason: invalid class name */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    @e(c = "io.ktor.utils.io.core.internal.UTF8Kt", f = "UTF8.kt", l = {37}, m = "decodeUTF8LineLoopSuspend")
    public static final class AnonymousClass1 extends c {
        int I$0;
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        Object L$5;
        Object L$6;
        int label;
        /* synthetic */ Object result;

        public AnonymousClass1(d<? super AnonymousClass1> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return UTF8Kt.decodeUTF8LineLoopSuspend(null, 0, null, null, this);
        }
    }

    public static final int byteCountUtf8(int i10) {
        int i11 = 0;
        int i12 = 128;
        for (int i13 = 1; i13 < 7 && (i10 & i12) != 0; i13++) {
            i10 &= ~i12;
            i12 >>= 1;
            i11++;
        }
        return i11;
    }

    private static final int charactersSize(int i10) {
        if (1 <= i10 && i10 < 128) {
            return 1;
        }
        if (128 <= i10 && i10 < 2048) {
            return 2;
        }
        if (2048 <= i10 && i10 < 65536) {
            return 3;
        }
        if (65536 <= i10 && i10 < 1114112) {
            return 4;
        }
        malformedCodePoint(i10);
        throw new KotlinNothingValueException();
    }

    public static final int codePoint(char c10, char c11) {
        return ((c10 - HighSurrogateMagic) << 10) | (c11 - MinLowSurrogate);
    }

    public static final boolean decodeASCII(Buffer buffer, l<? super Character, Boolean> lVar) {
        ByteBuffer memory = buffer.getMemory();
        int readPosition = buffer.getReadPosition();
        int writePosition = buffer.getWritePosition();
        for (int i10 = readPosition; i10 < writePosition; i10++) {
            byte b10 = memory.get(i10);
            int i11 = b10 & 255;
            if ((b10 & 128) == 128 || !((Boolean) lVar.invoke(Character.valueOf((char) i11))).booleanValue()) {
                buffer.discardExact(i10 - readPosition);
                return false;
            }
        }
        buffer.discardExact(writePosition - readPosition);
        return true;
    }

    public static final int decodeUTF8(Buffer buffer, l<? super Character, Boolean> lVar) throws MalformedUTF8InputException {
        ByteBuffer memory = buffer.getMemory();
        int readPosition = buffer.getReadPosition();
        int writePosition = buffer.getWritePosition();
        int i10 = 0;
        int i11 = 0;
        int i12 = 0;
        for (int i13 = readPosition; i13 < writePosition; i13++) {
            byte b10 = memory.get(i13);
            int i14 = b10 & 255;
            if ((b10 & 128) != 0) {
                if (i10 == 0) {
                    int i15 = 128;
                    i11 = i14;
                    for (int i16 = 1; i16 < 7 && (i11 & i15) != 0; i16++) {
                        i11 &= ~i15;
                        i15 >>= 1;
                        i10++;
                    }
                    int i17 = i10 - 1;
                    if (i10 > writePosition - i13) {
                        buffer.discardExact(i13 - readPosition);
                        return i10;
                    }
                    i12 = i10;
                    i10 = i17;
                } else {
                    i11 = (i11 << 6) | (b10 & 127);
                    i10--;
                    if (i10 != 0) {
                        continue;
                    } else {
                        if (!isBmpCodePoint(i11)) {
                            if (!isValidCodePoint(i11)) {
                                malformedCodePoint(i11);
                                throw new KotlinNothingValueException();
                            }
                            if (!((Boolean) lVar.invoke(Character.valueOf((char) highSurrogate(i11)))).booleanValue() || !((Boolean) lVar.invoke(Character.valueOf((char) lowSurrogate(i11)))).booleanValue()) {
                                buffer.discardExact(((i13 - readPosition) - i12) + 1);
                                return -1;
                            }
                        } else if (!((Boolean) lVar.invoke(Character.valueOf((char) i11))).booleanValue()) {
                            buffer.discardExact(((i13 - readPosition) - i12) + 1);
                            return -1;
                        }
                        i11 = 0;
                    }
                }
            } else {
                if (i10 != 0) {
                    malformedByteCount(i10);
                    throw new KotlinNothingValueException();
                }
                if (!((Boolean) lVar.invoke(Character.valueOf((char) i14))).booleanValue()) {
                    buffer.discardExact(i13 - readPosition);
                    return -1;
                }
            }
        }
        buffer.discardExact(writePosition - readPosition);
        return 0;
    }

    /* JADX WARN: Code restructure failed: missing block: B:114:0x0217, code lost:
    
        r7.f19738i = true;
        r24 = r13;
     */
    /* JADX WARN: Code restructure failed: missing block: B:115:0x021b, code lost:
    
        r13 = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:125:0x0238, code lost:
    
        r4 = 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:134:0x0253, code lost:
    
        r4 = 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:135:0x0254, code lost:
    
        r7.f19738i = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:136:0x0256, code lost:
    
        r16 = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:144:0x026e, code lost:
    
        r13 = r16;
     */
    /* JADX WARN: Code restructure failed: missing block: B:44:0x0114, code lost:
    
        r7.f19738i = true;
        r20 = r6;
     */
    /* JADX WARN: Code restructure failed: missing block: B:45:0x0118, code lost:
    
        r4 = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:57:0x0138, code lost:
    
        r15.discardExact(r30 - r28);
     */
    /* JADX WARN: Code restructure failed: missing block: B:79:0x019d, code lost:
    
        r4 = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:89:0x01b9, code lost:
    
        r4 = 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:90:0x01ba, code lost:
    
        r7.f19738i = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:91:0x01bc, code lost:
    
        r0 = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:99:0x01d2, code lost:
    
        r15.discardExact(((r30 - r28) - r19) + r4);
        r4 = r0;
     */
    /* JADX WARN: Path cross not found for [B:214:0x0212, B:119:0x0227], limit reached: 248 */
    /* JADX WARN: Removed duplicated region for block: B:132:0x024f A[Catch: all -> 0x0123, TRY_ENTER, TRY_LEAVE, TryCatch #1 {all -> 0x0123, blocks: (B:41:0x010f, B:87:0x01b5, B:132:0x024f), top: B:216:0x010f }] */
    /* JADX WARN: Removed duplicated region for block: B:138:0x025d  */
    /* JADX WARN: Removed duplicated region for block: B:200:0x0372  */
    /* JADX WARN: Removed duplicated region for block: B:212:0x0389  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x00a8 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:22:0x00a9  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x00b5  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0015  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:22:0x00a9 -> B:23:0x00ae). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.Object decodeUTF8LineLoopSuspend(java.lang.Appendable r27, int r28, r7.p<? super java.lang.Integer, ? super d7.d<? super io.ktor.utils.io.core.Input>, ? extends java.lang.Object> r29, r7.l<? super java.lang.Integer, x6.t0> r30, d7.d<? super java.lang.Boolean> r31) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 914
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.utils.io.core.internal.UTF8Kt.decodeUTF8LineLoopSuspend(java.lang.Appendable, int, r7.p, r7.l, d7.d):java.lang.Object");
    }

    /* JADX INFO: renamed from: encodeUTF8-lBXzO7A, reason: not valid java name */
    public static final int m7007encodeUTF8lBXzO7A(ByteBuffer byteBuffer, CharSequence charSequence, int i10, int i11, int i12, int i13) {
        int iMin = Math.min(i11, i10 + DatagramKt.MAX_DATAGRAM_SIZE);
        int i14 = i13 > 65535 ? 65535 : i13;
        int i15 = i10;
        int i16 = i12;
        while (i16 < i14 && i15 < iMin) {
            int i17 = i15 + 1;
            char cCharAt = charSequence.charAt(i15);
            int i18 = cCharAt & 65535;
            if ((cCharAt & 65408) != 0) {
                return m7008encodeUTF8Stage1Vm9B2pQ(byteBuffer, charSequence, i15, iMin, i10, i16, i14, i12);
            }
            byteBuffer.put(i16, (byte) i18);
            i15 = i17;
            i16++;
        }
        return EncodeResult.m6999constructorimpl((short) (i15 - i10), (short) (i16 - i12));
    }

    /* JADX INFO: renamed from: encodeUTF8Stage1-Vm9B2pQ, reason: not valid java name */
    private static final int m7008encodeUTF8Stage1Vm9B2pQ(ByteBuffer byteBuffer, CharSequence charSequence, int i10, int i11, int i12, int i13, int i14, int i15) {
        int iCodePoint;
        int i16;
        int i17 = i14 - 3;
        while (i17 - i13 > 0 && i10 < i11) {
            int i18 = i10 + 1;
            char cCharAt = charSequence.charAt(i10);
            if (!Character.isHighSurrogate(cCharAt)) {
                i10 = i18;
                iCodePoint = cCharAt;
            } else if (i18 == i11 || !Character.isLowSurrogate(charSequence.charAt(i18))) {
                i10 = i18;
                iCodePoint = 63;
            } else {
                i10 += 2;
                iCodePoint = codePoint(cCharAt, charSequence.charAt(i18));
            }
            if (iCodePoint >= 0 && iCodePoint < 128) {
                byteBuffer.put(i13, (byte) iCodePoint);
                i16 = 1;
            } else if (128 <= iCodePoint && iCodePoint < 2048) {
                byteBuffer.put(i13, (byte) (((iCodePoint >> 6) & 31) | PsExtractor.AUDIO_STREAM));
                byteBuffer.put(i13 + 1, (byte) (128 | (iCodePoint & 63)));
                i16 = 2;
            } else if (2048 <= iCodePoint && iCodePoint < 65536) {
                byteBuffer.put(i13, (byte) (((iCodePoint >> 12) & 15) | 224));
                byteBuffer.put(i13 + 1, (byte) ((63 & (iCodePoint >> 6)) | 128));
                byteBuffer.put(i13 + 2, (byte) (128 | (iCodePoint & 63)));
                i16 = 3;
            } else {
                if (65536 > iCodePoint || iCodePoint >= 1114112) {
                    malformedCodePoint(iCodePoint);
                    throw new KotlinNothingValueException();
                }
                byteBuffer.put(i13, (byte) (((iCodePoint >> 18) & 7) | PsExtractor.VIDEO_STREAM_MASK));
                byteBuffer.put(i13 + 1, (byte) (((iCodePoint >> 12) & 63) | 128));
                byteBuffer.put(i13 + 2, (byte) ((63 & (iCodePoint >> 6)) | 128));
                byteBuffer.put(i13 + 3, (byte) (128 | (iCodePoint & 63)));
                i16 = 4;
            }
            i13 += i16;
        }
        return i13 == i17 ? m7009encodeUTF8Stage2Vm9B2pQ(byteBuffer, charSequence, i10, i11, i12, i13, i14, i15) : EncodeResult.m6999constructorimpl((short) (i10 - i12), (short) (i13 - i15));
    }

    /* JADX WARN: Code restructure failed: missing block: B:46:0x00e2, code lost:
    
        malformedCodePoint(r7);
     */
    /* JADX WARN: Code restructure failed: missing block: B:47:0x00ea, code lost:
    
        throw new kotlin.KotlinNothingValueException();
     */
    /* JADX WARN: Code restructure failed: missing block: B:49:0x00f5, code lost:
    
        return io.ktor.utils.io.core.internal.EncodeResult.m6999constructorimpl((short) (r3 - r20), (short) (r4 - r23));
     */
    /* JADX INFO: renamed from: encodeUTF8Stage2-Vm9B2pQ, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static final int m7009encodeUTF8Stage2Vm9B2pQ(java.nio.ByteBuffer r16, java.lang.CharSequence r17, int r18, int r19, int r20, int r21, int r22, int r23) {
        /*
            Method dump skipped, instruction units count: 246
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.utils.io.core.internal.UTF8Kt.m7009encodeUTF8Stage2Vm9B2pQ(java.nio.ByteBuffer, java.lang.CharSequence, int, int, int, int, int, int):int");
    }

    public static final int highSurrogate(int i10) {
        return (i10 >>> 10) + HighSurrogateMagic;
    }

    public static final boolean isBmpCodePoint(int i10) {
        return (i10 >>> 16) == 0;
    }

    public static final boolean isValidCodePoint(int i10) {
        return i10 <= MaxCodePoint;
    }

    public static final int lowSurrogate(int i10) {
        return (i10 & AnalyticsListener.EVENT_DRM_KEYS_LOADED) + MinLowSurrogate;
    }

    public static final Void malformedByteCount(int i10) throws MalformedUTF8InputException {
        throw new MalformedUTF8InputException(androidx.compose.foundation.c.o(i10, "Expected ", " more character bytes"));
    }

    public static final Void malformedCodePoint(int i10) {
        throw new IllegalArgumentException(androidx.compose.foundation.c.o(i10, "Malformed code-point ", " found"));
    }

    private static final Void prematureEndOfStreamUtf(int i10) throws EOFException {
        throw new EOFException(androidx.compose.foundation.c.o(i10, "Premature end of stream: expected ", " bytes to decode UTF-8 char"));
    }

    /* JADX INFO: renamed from: putUtf8Char-62zg_DM, reason: not valid java name */
    public static final int m7010putUtf8Char62zg_DM(ByteBuffer byteBuffer, int i10, int i11) {
        if (i11 >= 0 && i11 < 128) {
            byteBuffer.put(i10, (byte) i11);
            return 1;
        }
        if (128 <= i11 && i11 < 2048) {
            byteBuffer.put(i10, (byte) (((i11 >> 6) & 31) | PsExtractor.AUDIO_STREAM));
            byteBuffer.put(i10 + 1, (byte) ((i11 & 63) | 128));
            return 2;
        }
        if (2048 <= i11 && i11 < 65536) {
            byteBuffer.put(i10, (byte) (((i11 >> 12) & 15) | 224));
            byteBuffer.put(i10 + 1, (byte) (((i11 >> 6) & 63) | 128));
            byteBuffer.put(i10 + 2, (byte) ((i11 & 63) | 128));
            return 3;
        }
        if (65536 > i11 || i11 >= 1114112) {
            malformedCodePoint(i11);
            throw new KotlinNothingValueException();
        }
        byteBuffer.put(i10, (byte) (((i11 >> 18) & 7) | PsExtractor.VIDEO_STREAM_MASK));
        byteBuffer.put(i10 + 1, (byte) (((i11 >> 12) & 63) | 128));
        byteBuffer.put(i10 + 2, (byte) (((i11 >> 6) & 63) | 128));
        byteBuffer.put(i10 + 3, (byte) ((i11 & 63) | 128));
        return 4;
    }
}
