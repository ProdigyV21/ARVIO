package io.ktor.http.cio.internals;

import androidx.media3.exoplayer.upstream.CmcdData;
import androidx.media3.extractor.metadata.dvbsi.AppInfoTableDecoder;
import androidx.media3.extractor.text.ttml.TtmlNode;
import d7.d;
import f7.c;
import f7.e;
import io.ktor.http.HttpMethod;
import kotlin.KotlinNothingValueException;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000V\n\u0002\u0010\r\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0001\n\u0002\b\u0006\n\u0002\u0010\f\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0016\n\u0002\b\u0002\n\u0002\u0010\u0012\n\u0002\b\u0005\u001a'\u0010\u0004\u001a\u00020\u0001*\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00012\b\b\u0002\u0010\u0003\u001a\u00020\u0001H\u0000¢\u0006\u0004\b\u0004\u0010\u0005\u001a/\u0010\b\u001a\u00020\u0007*\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00012\b\b\u0002\u0010\u0003\u001a\u00020\u00012\u0006\u0010\u0006\u001a\u00020\u0000H\u0000¢\u0006\u0004\b\b\u0010\t\u001a\u0014\u0010\n\u001a\u00020\u0001*\u00020\u0001H\u0082\b¢\u0006\u0004\b\n\u0010\u000b\u001a\u0013\u0010\r\u001a\u00020\f*\u00020\u0000H\u0000¢\u0006\u0004\b\r\u0010\u000e\u001a\u0011\u0010\u000f\u001a\u00020\f*\u00020\u0000¢\u0006\u0004\b\u000f\u0010\u000e\u001a\u0013\u0010\u0010\u001a\u00020\f*\u00020\u0000H\u0002¢\u0006\u0004\b\u0010\u0010\u000e\u001a\u001f\u0010\u0014\u001a\u00020\u0013*\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0001H\u0080@ø\u0001\u0000¢\u0006\u0004\b\u0014\u0010\u0015\u001a\u001f\u0010\u0019\u001a\u00020\u00182\u0006\u0010\u0016\u001a\u00020\u00002\u0006\u0010\u0017\u001a\u00020\u0001H\u0002¢\u0006\u0004\b\u0019\u0010\u001a\u001a\u001f\u0010\u001c\u001a\u00020\u00132\u0006\u0010\u001b\u001a\u00020\u00002\u0006\u0010\u0017\u001a\u00020\u0001H\u0002¢\u0006\u0004\b\u001c\u0010\u001d\u001a\u0017\u0010\u001c\u001a\u00020\u00132\u0006\u0010\u001b\u001a\u00020\u0000H\u0002¢\u0006\u0004\b\u001c\u0010\u001e\"\u0014\u0010 \u001a\u00020\u001f8\u0000X\u0080T¢\u0006\u0006\n\u0004\b \u0010!\" \u0010$\u001a\b\u0012\u0004\u0012\u00020#0\"8\u0000X\u0080\u0004¢\u0006\f\n\u0004\b$\u0010%\u001a\u0004\b&\u0010'\"\u0014\u0010)\u001a\u00020(8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b)\u0010*\"\u001a\u0010,\u001a\u00020+8\u0000X\u0080\u0004¢\u0006\f\n\u0004\b,\u0010-\u001a\u0004\b.\u0010/\u0082\u0002\u0004\n\u0002\b\u0019¨\u00060"}, d2 = {"", "", TtmlNode.START, TtmlNode.END, "hashCodeLowerCase", "(Ljava/lang/CharSequence;II)I", "other", "", "equalsLowerCase", "(Ljava/lang/CharSequence;IILjava/lang/CharSequence;)Z", "toLowerCase", "(I)I", "", "parseHexLong", "(Ljava/lang/CharSequence;)J", "parseDecLong", "parseDecLongWithCheck", "Lio/ktor/utils/io/ByteWriteChannel;", "value", "Lx6/t0;", "writeIntHex", "(Lio/ktor/utils/io/ByteWriteChannel;ILd7/d;)Ljava/lang/Object;", CmcdData.STREAMING_FORMAT_SS, "idx", "", "hexNumberFormatException", "(Ljava/lang/CharSequence;I)Ljava/lang/Void;", "cs", "numberFormatException", "(Ljava/lang/CharSequence;I)V", "(Ljava/lang/CharSequence;)V", "", "HTAB", "C", "Lio/ktor/http/cio/internals/AsciiCharTree;", "Lio/ktor/http/HttpMethod;", "DefaultHttpMethods", "Lio/ktor/http/cio/internals/AsciiCharTree;", "getDefaultHttpMethods", "()Lio/ktor/http/cio/internals/AsciiCharTree;", "", "HexTable", "[J", "", "HexLetterTable", "[B", "getHexLetterTable", "()[B", "ktor-http-cio"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class CharsKt {
    private static final AsciiCharTree<HttpMethod> DefaultHttpMethods = AsciiCharTree.INSTANCE.build(HttpMethod.INSTANCE.getDefaultMethods(), CharsKt$DefaultHttpMethods$1.INSTANCE, CharsKt$DefaultHttpMethods$2.INSTANCE);
    public static final char HTAB = '\t';
    private static final byte[] HexLetterTable;
    private static final long[] HexTable;

    /* JADX INFO: renamed from: io.ktor.http.cio.internals.CharsKt$writeIntHex$1, reason: invalid class name */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    @e(c = "io.ktor.http.cio.internals.CharsKt", f = "Chars.kt", l = {108, AppInfoTableDecoder.APPLICATION_INFORMATION_TABLE_ID}, m = "writeIntHex")
    public static final class AnonymousClass1 extends c {
        int I$0;
        int I$1;
        Object L$0;
        Object L$1;
        int label;
        /* synthetic */ Object result;

        public AnonymousClass1(d<? super AnonymousClass1> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return CharsKt.writeIntHex(null, 0, this);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x004c A[PHI: r7
      0x004c: PHI (r7v2 long) = (r7v1 long), (r7v0 long) binds: [B:18:0x005a, B:13:0x004a] A[DONT_GENERATE, DONT_INLINE]] */
    static {
        /*
            io.ktor.http.cio.internals.AsciiCharTree$Companion r0 = io.ktor.http.cio.internals.AsciiCharTree.INSTANCE
            io.ktor.http.HttpMethod$Companion r1 = io.ktor.http.HttpMethod.INSTANCE
            java.util.List r1 = r1.getDefaultMethods()
            io.ktor.http.cio.internals.CharsKt$DefaultHttpMethods$1 r2 = io.ktor.http.cio.internals.CharsKt$DefaultHttpMethods$1.INSTANCE
            io.ktor.http.cio.internals.CharsKt$DefaultHttpMethods$2 r3 = io.ktor.http.cio.internals.CharsKt$DefaultHttpMethods$2.INSTANCE
            io.ktor.http.cio.internals.AsciiCharTree r0 = r0.build(r1, r2, r3)
            io.ktor.http.cio.internals.CharsKt.DefaultHttpMethods = r0
            x7.i r0 = new x7.i
            r1 = 0
            r2 = 255(0xff, float:3.57E-43)
            r3 = 1
            r0.<init>(r1, r2, r3)
            java.util.ArrayList r2 = new java.util.ArrayList
            r4 = 10
            int r5 = kotlin.collections.s.U(r0, r4)
            r2.<init>(r5)
            x7.h r0 = r0.iterator()
        L2a:
            boolean r5 = r0.f22624m
            if (r5 == 0) goto L67
            int r5 = r0.nextInt()
            r6 = 48
            if (r6 > r5) goto L3f
            r6 = 58
            if (r5 >= r6) goto L3f
            long r5 = (long) r5
            r7 = 48
            long r5 = r5 - r7
            goto L5f
        L3f:
            long r5 = (long) r5
            r7 = 97
            int r9 = (r5 > r7 ? 1 : (r5 == r7 ? 0 : -1))
            if (r9 < 0) goto L50
            r9 = 102(0x66, double:5.04E-322)
            int r9 = (r5 > r9 ? 1 : (r5 == r9 ? 0 : -1))
            if (r9 > 0) goto L50
        L4c:
            long r5 = r5 - r7
            long r7 = (long) r4
            long r5 = r5 + r7
            goto L5f
        L50:
            r7 = 65
            int r9 = (r5 > r7 ? 1 : (r5 == r7 ? 0 : -1))
            if (r9 < 0) goto L5d
            r9 = 70
            int r9 = (r5 > r9 ? 1 : (r5 == r9 ? 0 : -1))
            if (r9 > 0) goto L5d
            goto L4c
        L5d:
            r5 = -1
        L5f:
            java.lang.Long r5 = java.lang.Long.valueOf(r5)
            r2.add(r5)
            goto L2a
        L67:
            long[] r0 = kotlin.collections.x.d1(r2)
            io.ktor.http.cio.internals.CharsKt.HexTable = r0
            x7.i r0 = new x7.i
            r2 = 15
            r0.<init>(r1, r2, r3)
            java.util.ArrayList r2 = new java.util.ArrayList
            int r3 = kotlin.collections.s.U(r0, r4)
            r2.<init>(r3)
            x7.h r0 = r0.iterator()
        L81:
            boolean r3 = r0.f22624m
            if (r3 == 0) goto L9d
            int r3 = r0.nextInt()
            if (r3 >= r4) goto L8f
            int r3 = r3 + 48
        L8d:
            byte r3 = (byte) r3
            goto L95
        L8f:
            int r3 = r3 + 97
            char r3 = (char) r3
            int r3 = r3 - r4
            char r3 = (char) r3
            goto L8d
        L95:
            java.lang.Byte r3 = java.lang.Byte.valueOf(r3)
            r2.add(r3)
            goto L81
        L9d:
            int r0 = r2.size()
            byte[] r0 = new byte[r0]
            java.util.Iterator r2 = r2.iterator()
        La7:
            boolean r3 = r2.hasNext()
            if (r3 == 0) goto Lbd
            java.lang.Object r3 = r2.next()
            java.lang.Number r3 = (java.lang.Number) r3
            byte r3 = r3.byteValue()
            int r4 = r1 + 1
            r0[r1] = r3
            r1 = r4
            goto La7
        Lbd:
            io.ktor.http.cio.internals.CharsKt.HexLetterTable = r0
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.http.cio.internals.CharsKt.<clinit>():void");
    }

    public static final boolean equalsLowerCase(CharSequence charSequence, int i10, int i11, CharSequence charSequence2) {
        if (i11 - i10 != charSequence2.length()) {
            return false;
        }
        for (int i12 = i10; i12 < i11; i12++) {
            int iCharAt = charSequence.charAt(i12);
            if (65 <= iCharAt && iCharAt < 91) {
                iCharAt += 32;
            }
            int iCharAt2 = charSequence2.charAt(i12 - i10);
            if (65 <= iCharAt2 && iCharAt2 < 91) {
                iCharAt2 += 32;
            }
            if (iCharAt != iCharAt2) {
                return false;
            }
        }
        return true;
    }

    public static /* synthetic */ boolean equalsLowerCase$default(CharSequence charSequence, int i10, int i11, CharSequence charSequence2, int i12, Object obj) {
        if ((i12 & 1) != 0) {
            i10 = 0;
        }
        if ((i12 & 2) != 0) {
            i11 = charSequence.length();
        }
        return equalsLowerCase(charSequence, i10, i11, charSequence2);
    }

    public static final AsciiCharTree<HttpMethod> getDefaultHttpMethods() {
        return DefaultHttpMethods;
    }

    public static final byte[] getHexLetterTable() {
        return HexLetterTable;
    }

    public static final int hashCodeLowerCase(CharSequence charSequence, int i10, int i11) {
        int i12 = 0;
        while (i10 < i11) {
            int iCharAt = charSequence.charAt(i10);
            if (65 <= iCharAt && iCharAt < 91) {
                iCharAt += 32;
            }
            i12 = (i12 * 31) + iCharAt;
            i10++;
        }
        return i12;
    }

    public static /* synthetic */ int hashCodeLowerCase$default(CharSequence charSequence, int i10, int i11, int i12, Object obj) {
        if ((i12 & 1) != 0) {
            i10 = 0;
        }
        if ((i12 & 2) != 0) {
            i11 = charSequence.length();
        }
        return hashCodeLowerCase(charSequence, i10, i11);
    }

    private static final Void hexNumberFormatException(CharSequence charSequence, int i10) {
        throw new NumberFormatException("Invalid HEX number: " + ((Object) charSequence) + ", wrong digit: " + charSequence.charAt(i10));
    }

    private static final void numberFormatException(CharSequence charSequence, int i10) {
        throw new NumberFormatException("Invalid number: " + ((Object) charSequence) + ", wrong digit: " + charSequence.charAt(i10) + " at position " + i10);
    }

    public static final long parseDecLong(CharSequence charSequence) {
        int length = charSequence.length();
        if (length > 19) {
            numberFormatException(charSequence);
        }
        if (length == 19) {
            return parseDecLongWithCheck(charSequence);
        }
        long j10 = 0;
        for (int i10 = 0; i10 < length; i10++) {
            long jCharAt = ((long) charSequence.charAt(i10)) - 48;
            if (jCharAt < 0 || jCharAt > 9) {
                numberFormatException(charSequence, i10);
            }
            j10 = (j10 << 3) + (j10 << 1) + jCharAt;
        }
        return j10;
    }

    private static final long parseDecLongWithCheck(CharSequence charSequence) {
        int length = charSequence.length();
        long j10 = 0;
        for (int i10 = 0; i10 < length; i10++) {
            long jCharAt = ((long) charSequence.charAt(i10)) - 48;
            if (jCharAt < 0 || jCharAt > 9) {
                numberFormatException(charSequence, i10);
            }
            j10 = (j10 << 3) + (j10 << 1) + jCharAt;
            if (j10 < 0) {
                numberFormatException(charSequence);
            }
        }
        return j10;
    }

    public static final long parseHexLong(CharSequence charSequence) {
        long[] jArr = HexTable;
        int length = charSequence.length();
        long j10 = 0;
        for (int i10 = 0; i10 < length; i10++) {
            int iCharAt = charSequence.charAt(i10) & 65535;
            long j11 = iCharAt < 255 ? jArr[iCharAt] : -1L;
            if (j11 == -1) {
                hexNumberFormatException(charSequence, i10);
                throw new KotlinNothingValueException();
            }
            j10 = (j10 << 4) | j11;
        }
        return j10;
    }

    private static final int toLowerCase(int i10) {
        return (65 > i10 || i10 >= 91) ? i10 : i10 + 32;
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.Object writeIntHex(io.ktor.utils.io.ByteWriteChannel r7, int r8, d7.d<? super x6.t0> r9) {
        /*
            boolean r0 = r9 instanceof io.ktor.http.cio.internals.CharsKt.AnonymousClass1
            if (r0 == 0) goto L13
            r0 = r9
            io.ktor.http.cio.internals.CharsKt$writeIntHex$1 r0 = (io.ktor.http.cio.internals.CharsKt.AnonymousClass1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            io.ktor.http.cio.internals.CharsKt$writeIntHex$1 r0 = new io.ktor.http.cio.internals.CharsKt$writeIntHex$1
            r0.<init>(r9)
        L18:
            java.lang.Object r9 = r0.result
            int r1 = r0.label
            r2 = 8
            r3 = 2
            r4 = 1
            e7.a r5 = e7.a.f15033i
            if (r1 == 0) goto L43
            if (r1 == r4) goto L31
            if (r1 != r3) goto L29
            goto L31
        L29:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            java.lang.String r8 = "call to 'resume' before 'invoke' with coroutine"
            r7.<init>(r8)
            throw r7
        L31:
            int r7 = r0.I$1
            int r8 = r0.I$0
            java.lang.Object r1 = r0.L$1
            byte[] r1 = (byte[]) r1
            java.lang.Object r4 = r0.L$0
            io.ktor.utils.io.ByteWriteChannel r4 = (io.ktor.utils.io.ByteWriteChannel) r4
            k2.c.G(r9)
            r6 = r7
            r7 = r4
            goto L6a
        L43:
            k2.c.G(r9)
            if (r8 <= 0) goto L8a
            byte[] r1 = io.ktor.http.cio.internals.CharsKt.HexLetterTable
            r9 = 0
        L4b:
            int r6 = r9 + 1
            if (r9 >= r2) goto L6a
            int r9 = r8 >>> 28
            int r8 = r8 << 4
            if (r9 == 0) goto L68
            r9 = r1[r9]
            r0.L$0 = r7
            r0.L$1 = r1
            r0.I$0 = r8
            r0.I$1 = r6
            r0.label = r4
            java.lang.Object r9 = r7.writeByte(r9, r0)
            if (r9 != r5) goto L6a
            goto L84
        L68:
            r9 = r6
            goto L4b
        L6a:
            int r9 = r6 + 1
            if (r6 >= r2) goto L87
            int r4 = r8 >>> 28
            int r8 = r8 << 4
            r4 = r1[r4]
            r0.L$0 = r7
            r0.L$1 = r1
            r0.I$0 = r8
            r0.I$1 = r9
            r0.label = r3
            java.lang.Object r4 = r7.writeByte(r4, r0)
            if (r4 != r5) goto L85
        L84:
            return r5
        L85:
            r6 = r9
            goto L6a
        L87:
            x6.t0 r7 = x6.t0.f22605a
            return r7
        L8a:
            java.lang.IllegalArgumentException r7 = new java.lang.IllegalArgumentException
            java.lang.String r8 = "Does only work for positive numbers"
            r7.<init>(r8)
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.http.cio.internals.CharsKt.writeIntHex(io.ktor.utils.io.ByteWriteChannel, int, d7.d):java.lang.Object");
    }

    private static final void numberFormatException(CharSequence charSequence) {
        throw new NumberFormatException("Invalid number " + ((Object) charSequence) + ": too large for Long type");
    }
}
