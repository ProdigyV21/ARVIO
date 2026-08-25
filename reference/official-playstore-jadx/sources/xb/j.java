package xb;

import androidx.media3.common.C;
import androidx.media3.exoplayer.MediaPeriodQueue;
import androidx.media3.exoplayer.audio.SilenceSkippingAudioProcessor;
import androidx.media3.extractor.ts.PsExtractor;
import java.io.EOFException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.channels.ByteChannel;
import java.nio.charset.Charset;

/* JADX INFO: loaded from: classes5.dex */
public final class j implements l, k, Cloneable, ByteChannel {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public j0 f22758i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public long f22759l;

    @Override // xb.l
    public final long A(byte b10, long j10, long j11) {
        j0 j0Var;
        long j12 = j10;
        long j13 = j11;
        long j14 = 0;
        if (0 > j12 || j12 > j13) {
            StringBuilder sb2 = new StringBuilder("size=");
            sb2.append(this.f22759l);
            androidx.compose.foundation.c.z(sb2, " fromIndex=", j12, " toIndex=");
            sb2.append(j13);
            throw new IllegalArgumentException(sb2.toString().toString());
        }
        long j15 = this.f22759l;
        if (j13 > j15) {
            j13 = j15;
        }
        long j16 = -1;
        if (j12 == j13 || (j0Var = this.f22758i) == null) {
            return -1L;
        }
        if (j15 - j12 < j12) {
            while (j15 > j12) {
                j0Var = j0Var.f22766g;
                j15 -= (long) (j0Var.f22762c - j0Var.f22761b);
            }
            while (j15 < j13) {
                byte[] bArr = j0Var.f22760a;
                long j17 = j16;
                int iMin = (int) Math.min(j0Var.f22762c, (((long) j0Var.f22761b) + j13) - j15);
                for (int i10 = (int) ((((long) j0Var.f22761b) + j12) - j15); i10 < iMin; i10++) {
                    if (bArr[i10] == b10) {
                        return ((long) (i10 - j0Var.f22761b)) + j15;
                    }
                }
                j15 += (long) (j0Var.f22762c - j0Var.f22761b);
                j0Var = j0Var.f22765f;
                j16 = j17;
                j12 = j15;
            }
            return j16;
        }
        while (true) {
            long j18 = ((long) (j0Var.f22762c - j0Var.f22761b)) + j14;
            if (j18 > j12) {
                break;
            }
            j0Var = j0Var.f22765f;
            j14 = j18;
        }
        while (j14 < j13) {
            byte[] bArr2 = j0Var.f22760a;
            int iMin2 = (int) Math.min(j0Var.f22762c, (((long) j0Var.f22761b) + j13) - j14);
            for (int i11 = (int) ((((long) j0Var.f22761b) + j12) - j14); i11 < iMin2; i11++) {
                if (bArr2[i11] == b10) {
                    return ((long) (i11 - j0Var.f22761b)) + j14;
                }
            }
            j14 += (long) (j0Var.f22762c - j0Var.f22761b);
            j0Var = j0Var.f22765f;
            j12 = j14;
        }
        return -1L;
    }

    @Override // xb.l
    public final String B(long j10) throws EOFException {
        if (j10 < 0) {
            throw new IllegalArgumentException(androidx.compose.foundation.c.q(j10, "limit < 0: ").toString());
        }
        long j11 = j10 != Long.MAX_VALUE ? j10 + 1 : Long.MAX_VALUE;
        long jA = A((byte) 10, 0L, j11);
        if (jA != -1) {
            return yb.a.a(this, jA);
        }
        if (j11 < this.f22759l && x(j11 - 1) == 13 && x(j11) == 10) {
            return yb.a.a(this, j11);
        }
        j jVar = new j();
        l(jVar, 0L, Math.min(32, this.f22759l));
        throw new EOFException("\\n not found: limit=" + Math.min(this.f22759l, j10) + " content=" + jVar.c0(jVar.f22759l).i() + (char) 8230);
    }

    @Override // xb.l
    public final long D(k kVar) {
        long j10 = this.f22759l;
        if (j10 > 0) {
            kVar.write(this, j10);
        }
        return j10;
    }

    public final j0 D0(int i10) {
        if (i10 < 1 || i10 > 8192) {
            throw new IllegalArgumentException("unexpected capacity");
        }
        j0 j0Var = this.f22758i;
        if (j0Var == null) {
            j0 j0VarB = k0.b();
            this.f22758i = j0VarB;
            j0VarB.f22766g = j0VarB;
            j0VarB.f22765f = j0VarB;
            return j0VarB;
        }
        j0 j0Var2 = j0Var.f22766g;
        if (j0Var2.f22762c + i10 <= 8192 && j0Var2.f22764e) {
            return j0Var2;
        }
        j0 j0VarB2 = k0.b();
        j0Var2.b(j0VarB2);
        return j0VarB2;
    }

    @Override // xb.k
    public final /* bridge */ /* synthetic */ k H(String str) {
        S0(str);
        return this;
    }

    public final void J0(m mVar) {
        mVar.u(this, mVar.f());
    }

    /* JADX WARN: Removed duplicated region for block: B:32:0x0078  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x0082  */
    /* JADX WARN: Removed duplicated region for block: B:35:0x0086  */
    /* JADX WARN: Removed duplicated region for block: B:43:0x008a A[EDGE_INSN: B:43:0x008a->B:37:0x008a BREAK  A[LOOP:0: B:5:0x000b->B:45:?], SYNTHETIC] */
    @Override // xb.l
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final long L0() throws java.io.EOFException {
        /*
            r14 = this;
            long r0 = r14.f22759l
            r2 = 0
            int r0 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r0 == 0) goto L91
            r0 = 0
            r1 = r0
            r4 = r2
        Lb:
            xb.j0 r6 = r14.f22758i
            byte[] r7 = r6.f22760a
            int r8 = r6.f22761b
            int r9 = r6.f22762c
        L13:
            if (r8 >= r9) goto L76
            r10 = r7[r8]
            r11 = 48
            if (r10 < r11) goto L22
            r11 = 57
            if (r10 > r11) goto L22
            int r11 = r10 + (-48)
            goto L37
        L22:
            r11 = 97
            if (r10 < r11) goto L2d
            r11 = 102(0x66, float:1.43E-43)
            if (r10 > r11) goto L2d
            int r11 = r10 + (-87)
            goto L37
        L2d:
            r11 = 65
            if (r10 < r11) goto L62
            r11 = 70
            if (r10 > r11) goto L62
            int r11 = r10 + (-55)
        L37:
            r12 = -1152921504606846976(0xf000000000000000, double:-3.105036184601418E231)
            long r12 = r12 & r4
            int r12 = (r12 > r2 ? 1 : (r12 == r2 ? 0 : -1))
            if (r12 != 0) goto L47
            r10 = 4
            long r4 = r4 << r10
            long r10 = (long) r11
            long r4 = r4 | r10
            int r8 = r8 + 1
            int r0 = r0 + 1
            goto L13
        L47:
            xb.j r0 = new xb.j
            r0.<init>()
            r0.O0(r4)
            r0.M0(r10)
            java.lang.NumberFormatException r1 = new java.lang.NumberFormatException
            java.lang.String r0 = r0.t0()
            java.lang.String r2 = "Number too large: "
            java.lang.String r0 = r2.concat(r0)
            r1.<init>(r0)
            throw r1
        L62:
            if (r0 == 0) goto L66
            r1 = 1
            goto L76
        L66:
            java.lang.NumberFormatException r0 = new java.lang.NumberFormatException
            java.lang.String r1 = xb.b.h(r10)
            java.lang.String r2 = "Expected leading [0-9a-fA-F] character but was 0x"
            java.lang.String r1 = r2.concat(r1)
            r0.<init>(r1)
            throw r0
        L76:
            if (r8 != r9) goto L82
            xb.j0 r7 = r6.a()
            r14.f22758i = r7
            xb.k0.a(r6)
            goto L84
        L82:
            r6.f22761b = r8
        L84:
            if (r1 != 0) goto L8a
            xb.j0 r6 = r14.f22758i
            if (r6 != 0) goto Lb
        L8a:
            long r1 = r14.f22759l
            long r6 = (long) r0
            long r1 = r1 - r6
            r14.f22759l = r1
            return r4
        L91:
            java.io.EOFException r0 = new java.io.EOFException
            r0.<init>()
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: xb.j.L0():long");
    }

    public final void M0(int i10) {
        j0 j0VarD0 = D0(1);
        byte[] bArr = j0VarD0.f22760a;
        int i11 = j0VarD0.f22762c;
        j0VarD0.f22762c = i11 + 1;
        bArr[i11] = (byte) i10;
        this.f22759l++;
    }

    public final void N0(long j10) {
        if (j10 == 0) {
            M0(48);
            return;
        }
        boolean z = false;
        int i10 = 1;
        if (j10 < 0) {
            j10 = -j10;
            if (j10 < 0) {
                R0(0, 20, "-9223372036854775808");
                return;
            }
            z = true;
        }
        if (j10 >= 100000000) {
            i10 = j10 < MediaPeriodQueue.INITIAL_RENDERER_POSITION_OFFSET_US ? j10 < 10000000000L ? j10 < C.NANOS_PER_SECOND ? 9 : 10 : j10 < 100000000000L ? 11 : 12 : j10 < 1000000000000000L ? j10 < 10000000000000L ? 13 : j10 < 100000000000000L ? 14 : 15 : j10 < 100000000000000000L ? j10 < 10000000000000000L ? 16 : 17 : j10 < 1000000000000000000L ? 18 : 19;
        } else if (j10 >= 10000) {
            i10 = j10 < 1000000 ? j10 < SilenceSkippingAudioProcessor.DEFAULT_MINIMUM_SILENCE_DURATION_US ? 5 : 6 : j10 < 10000000 ? 7 : 8;
        } else if (j10 >= 100) {
            i10 = j10 < 1000 ? 3 : 4;
        } else if (j10 >= 10) {
            i10 = 2;
        }
        if (z) {
            i10++;
        }
        j0 j0VarD0 = D0(i10);
        byte[] bArr = j0VarD0.f22760a;
        int i11 = j0VarD0.f22762c + i10;
        while (j10 != 0) {
            long j11 = 10;
            i11--;
            bArr[i11] = yb.a.f23006a[(int) (j10 % j11)];
            j10 /= j11;
        }
        if (z) {
            bArr[i11 - 1] = 45;
        }
        j0VarD0.f22762c += i10;
        this.f22759l += (long) i10;
    }

    public final long O(long j10, m mVar) {
        long j11 = 0;
        if (j10 < 0) {
            throw new IllegalArgumentException(androidx.compose.foundation.c.q(j10, "fromIndex < 0: ").toString());
        }
        j0 j0Var = this.f22758i;
        if (j0Var == null) {
            return -1L;
        }
        long j12 = this.f22759l;
        if (j12 - j10 < j10) {
            while (j12 > j10) {
                j0Var = j0Var.f22766g;
                j12 -= (long) (j0Var.f22762c - j0Var.f22761b);
            }
            if (mVar.f() == 2) {
                byte bM = mVar.m(0);
                byte bM2 = mVar.m(1);
                while (j12 < this.f22759l) {
                    byte[] bArr = j0Var.f22760a;
                    int i10 = j0Var.f22762c;
                    for (int i11 = (int) ((((long) j0Var.f22761b) + j10) - j12); i11 < i10; i11++) {
                        byte b10 = bArr[i11];
                        if (b10 == bM || b10 == bM2) {
                            return ((long) (i11 - j0Var.f22761b)) + j12;
                        }
                    }
                    j12 += (long) (j0Var.f22762c - j0Var.f22761b);
                    j0Var = j0Var.f22765f;
                    j10 = j12;
                }
            } else {
                byte[] bArrL = mVar.l();
                while (j12 < this.f22759l) {
                    byte[] bArr2 = j0Var.f22760a;
                    int i12 = j0Var.f22762c;
                    for (int i13 = (int) ((((long) j0Var.f22761b) + j10) - j12); i13 < i12; i13++) {
                        byte b11 = bArr2[i13];
                        for (byte b12 : bArrL) {
                            if (b11 == b12) {
                                return ((long) (i13 - j0Var.f22761b)) + j12;
                            }
                        }
                    }
                    j12 += (long) (j0Var.f22762c - j0Var.f22761b);
                    j0Var = j0Var.f22765f;
                    j10 = j12;
                }
            }
            return -1L;
        }
        while (true) {
            long j13 = ((long) (j0Var.f22762c - j0Var.f22761b)) + j11;
            if (j13 > j10) {
                break;
            }
            j0Var = j0Var.f22765f;
            j11 = j13;
        }
        if (mVar.f() == 2) {
            byte bM3 = mVar.m(0);
            byte bM4 = mVar.m(1);
            while (j11 < this.f22759l) {
                byte[] bArr3 = j0Var.f22760a;
                int i14 = j0Var.f22762c;
                for (int i15 = (int) ((((long) j0Var.f22761b) + j10) - j11); i15 < i14; i15++) {
                    byte b13 = bArr3[i15];
                    if (b13 == bM3 || b13 == bM4) {
                        return ((long) (i15 - j0Var.f22761b)) + j11;
                    }
                }
                j11 += (long) (j0Var.f22762c - j0Var.f22761b);
                j0Var = j0Var.f22765f;
                j10 = j11;
            }
        } else {
            byte[] bArrL2 = mVar.l();
            while (j11 < this.f22759l) {
                byte[] bArr4 = j0Var.f22760a;
                int i16 = j0Var.f22762c;
                for (int i17 = (int) ((((long) j0Var.f22761b) + j10) - j11); i17 < i16; i17++) {
                    byte b14 = bArr4[i17];
                    for (byte b15 : bArrL2) {
                        if (b14 == b15) {
                            return ((long) (i17 - j0Var.f22761b)) + j11;
                        }
                    }
                }
                j11 += (long) (j0Var.f22762c - j0Var.f22761b);
                j0Var = j0Var.f22765f;
                j10 = j11;
            }
        }
        return -1L;
    }

    public final void O0(long j10) {
        if (j10 == 0) {
            M0(48);
            return;
        }
        long j11 = (j10 >>> 1) | j10;
        long j12 = j11 | (j11 >>> 2);
        long j13 = j12 | (j12 >>> 4);
        long j14 = j13 | (j13 >>> 8);
        long j15 = j14 | (j14 >>> 16);
        long j16 = j15 | (j15 >>> 32);
        long j17 = j16 - ((j16 >>> 1) & 6148914691236517205L);
        long j18 = ((j17 >>> 2) & 3689348814741910323L) + (j17 & 3689348814741910323L);
        long j19 = ((j18 >>> 4) + j18) & 1085102592571150095L;
        long j20 = j19 + (j19 >>> 8);
        long j21 = j20 + (j20 >>> 16);
        int i10 = (int) ((((j21 & 63) + ((j21 >>> 32) & 63)) + ((long) 3)) / ((long) 4));
        j0 j0VarD0 = D0(i10);
        byte[] bArr = j0VarD0.f22760a;
        int i11 = j0VarD0.f22762c;
        for (int i12 = (i11 + i10) - 1; i12 >= i11; i12--) {
            bArr[i12] = yb.a.f23006a[(int) (15 & j10)];
            j10 >>>= 4;
        }
        j0VarD0.f22762c += i10;
        this.f22759l += (long) i10;
    }

    public final void P0(int i10) {
        j0 j0VarD0 = D0(4);
        byte[] bArr = j0VarD0.f22760a;
        int i11 = j0VarD0.f22762c;
        bArr[i11] = (byte) ((i10 >>> 24) & 255);
        bArr[i11 + 1] = (byte) ((i10 >>> 16) & 255);
        bArr[i11 + 2] = (byte) ((i10 >>> 8) & 255);
        bArr[i11 + 3] = (byte) (i10 & 255);
        j0VarD0.f22762c = i11 + 4;
        this.f22759l += 4;
    }

    @Override // xb.l
    public final String Q() {
        return B(Long.MAX_VALUE);
    }

    public final void Q0(int i10) {
        j0 j0VarD0 = D0(2);
        byte[] bArr = j0VarD0.f22760a;
        int i11 = j0VarD0.f22762c;
        bArr[i11] = (byte) ((i10 >>> 8) & 255);
        bArr[i11 + 1] = (byte) (i10 & 255);
        j0VarD0.f22762c = i11 + 2;
        this.f22759l += 2;
    }

    public final g R(g gVar) {
        byte[] bArr = yb.a.f23006a;
        if (gVar == b.f22715a) {
            gVar = new g();
        }
        if (gVar.f22735i != null) {
            throw new IllegalStateException("already attached to a buffer");
        }
        gVar.f22735i = this;
        gVar.f22736l = true;
        return gVar;
    }

    public final void R0(int i10, int i11, String str) {
        char cCharAt;
        if (i10 < 0) {
            throw new IllegalArgumentException(a0.c.i(i10, "beginIndex < 0: ").toString());
        }
        if (i11 < i10) {
            throw new IllegalArgumentException(androidx.compose.foundation.c.n(i11, i10, "endIndex < beginIndex: ", " < ").toString());
        }
        if (i11 > str.length()) {
            StringBuilder sbS = a0.c.s(i11, "endIndex > string.length: ", " > ");
            sbS.append(str.length());
            throw new IllegalArgumentException(sbS.toString().toString());
        }
        while (i10 < i11) {
            char cCharAt2 = str.charAt(i10);
            if (cCharAt2 < 128) {
                j0 j0VarD0 = D0(1);
                byte[] bArr = j0VarD0.f22760a;
                int i12 = j0VarD0.f22762c - i10;
                int iMin = Math.min(i11, 8192 - i12);
                int i13 = i10 + 1;
                bArr[i10 + i12] = (byte) cCharAt2;
                while (true) {
                    i10 = i13;
                    if (i10 >= iMin || (cCharAt = str.charAt(i10)) >= 128) {
                        break;
                    }
                    i13 = i10 + 1;
                    bArr[i10 + i12] = (byte) cCharAt;
                }
                int i14 = j0VarD0.f22762c;
                int i15 = (i12 + i10) - i14;
                j0VarD0.f22762c = i14 + i15;
                this.f22759l += (long) i15;
            } else {
                if (cCharAt2 < 2048) {
                    j0 j0VarD02 = D0(2);
                    byte[] bArr2 = j0VarD02.f22760a;
                    int i16 = j0VarD02.f22762c;
                    bArr2[i16] = (byte) ((cCharAt2 >> 6) | PsExtractor.AUDIO_STREAM);
                    bArr2[i16 + 1] = (byte) ((cCharAt2 & '?') | 128);
                    j0VarD02.f22762c = i16 + 2;
                    this.f22759l += 2;
                } else if (cCharAt2 < 55296 || cCharAt2 > 57343) {
                    j0 j0VarD03 = D0(3);
                    byte[] bArr3 = j0VarD03.f22760a;
                    int i17 = j0VarD03.f22762c;
                    bArr3[i17] = (byte) ((cCharAt2 >> '\f') | 224);
                    bArr3[i17 + 1] = (byte) ((63 & (cCharAt2 >> 6)) | 128);
                    bArr3[i17 + 2] = (byte) ((cCharAt2 & '?') | 128);
                    j0VarD03.f22762c = i17 + 3;
                    this.f22759l += 3;
                } else {
                    int i18 = i10 + 1;
                    char cCharAt3 = i18 < i11 ? str.charAt(i18) : (char) 0;
                    if (cCharAt2 > 56319 || 56320 > cCharAt3 || cCharAt3 >= 57344) {
                        M0(63);
                        i10 = i18;
                    } else {
                        int i19 = (((cCharAt2 & 1023) << 10) | (cCharAt3 & 1023)) + 65536;
                        j0 j0VarD04 = D0(4);
                        byte[] bArr4 = j0VarD04.f22760a;
                        int i20 = j0VarD04.f22762c;
                        bArr4[i20] = (byte) ((i19 >> 18) | PsExtractor.VIDEO_STREAM_MASK);
                        bArr4[i20 + 1] = (byte) (((i19 >> 12) & 63) | 128);
                        bArr4[i20 + 2] = (byte) (((i19 >> 6) & 63) | 128);
                        bArr4[i20 + 3] = (byte) ((i19 & 63) | 128);
                        j0VarD04.f22762c = i20 + 4;
                        this.f22759l += 4;
                        i10 += 2;
                    }
                }
                i10++;
            }
        }
    }

    @Override // xb.l
    public final void S(j jVar, long j10) throws EOFException {
        long j11 = this.f22759l;
        if (j11 >= j10) {
            jVar.write(this, j10);
        } else {
            jVar.write(this, j11);
            throw new EOFException();
        }
    }

    public final void S0(String str) {
        R0(0, str.length(), str);
    }

    public final byte[] T(long j10) throws EOFException {
        if (j10 < 0 || j10 > 2147483647L) {
            throw new IllegalArgumentException(androidx.compose.foundation.c.q(j10, "byteCount: ").toString());
        }
        if (this.f22759l < j10) {
            throw new EOFException();
        }
        byte[] bArr = new byte[(int) j10];
        readFully(bArr);
        return bArr;
    }

    public final void T0(int i10) {
        String strI;
        if (i10 < 128) {
            M0(i10);
            return;
        }
        if (i10 < 2048) {
            j0 j0VarD0 = D0(2);
            byte[] bArr = j0VarD0.f22760a;
            int i11 = j0VarD0.f22762c;
            bArr[i11] = (byte) ((i10 >> 6) | PsExtractor.AUDIO_STREAM);
            bArr[i11 + 1] = (byte) ((i10 & 63) | 128);
            j0VarD0.f22762c = i11 + 2;
            this.f22759l += 2;
            return;
        }
        if (55296 <= i10 && i10 < 57344) {
            M0(63);
            return;
        }
        if (i10 < 65536) {
            j0 j0VarD02 = D0(3);
            byte[] bArr2 = j0VarD02.f22760a;
            int i12 = j0VarD02.f22762c;
            bArr2[i12] = (byte) ((i10 >> 12) | 224);
            bArr2[i12 + 1] = (byte) (((i10 >> 6) & 63) | 128);
            bArr2[i12 + 2] = (byte) ((i10 & 63) | 128);
            j0VarD02.f22762c = i12 + 3;
            this.f22759l += 3;
            return;
        }
        if (i10 <= 1114111) {
            j0 j0VarD03 = D0(4);
            byte[] bArr3 = j0VarD03.f22760a;
            int i13 = j0VarD03.f22762c;
            bArr3[i13] = (byte) ((i10 >> 18) | PsExtractor.VIDEO_STREAM_MASK);
            bArr3[i13 + 1] = (byte) (((i10 >> 12) & 63) | 128);
            bArr3[i13 + 2] = (byte) (((i10 >> 6) & 63) | 128);
            bArr3[i13 + 3] = (byte) ((i10 & 63) | 128);
            j0VarD03.f22762c = i13 + 4;
            this.f22759l += 4;
            return;
        }
        StringBuilder sb2 = new StringBuilder("Unexpected code point: 0x");
        if (i10 != 0) {
            char[] cArr = yb.b.f23007a;
            char[] cArr2 = {cArr[(i10 >> 28) & 15], cArr[(i10 >> 24) & 15], cArr[(i10 >> 20) & 15], cArr[(i10 >> 16) & 15], cArr[(i10 >> 12) & 15], cArr[(i10 >> 8) & 15], cArr[(i10 >> 4) & 15], cArr[i10 & 15]};
            int i14 = 0;
            while (i14 < 8 && cArr2[i14] == '0') {
                i14++;
            }
            strI = kotlin.text.u.I(cArr2, i14, 8);
        } else {
            strI = "0";
        }
        sb2.append(strI);
        throw new IllegalArgumentException(sb2.toString());
    }

    @Override // xb.k
    public final /* bridge */ /* synthetic */ k U(m mVar) {
        J0(mVar);
        return this;
    }

    /* JADX WARN: Code restructure failed: missing block: B:19:0x0040, code lost:
    
        r1 = new xb.j();
        r1.N0(r8);
        r1.M0(r15);
     */
    /* JADX WARN: Code restructure failed: missing block: B:20:0x004b, code lost:
    
        if (r2 != false) goto L22;
     */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x004d, code lost:
    
        r1.readByte();
     */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x005f, code lost:
    
        throw new java.lang.NumberFormatException("Number too large: ".concat(r1.t0()));
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final long V() {
        /*
            Method dump skipped, instruction units count: 213
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: xb.j.V():long");
    }

    @Override // xb.l
    public final void X(long j10) throws EOFException {
        if (this.f22759l < j10) {
            throw new EOFException();
        }
    }

    @Override // xb.k
    public final /* bridge */ /* synthetic */ k Y(long j10) {
        N0(j10);
        return this;
    }

    public final short b0() throws EOFException {
        short s10 = readShort();
        return (short) (((s10 & 255) << 8) | ((65280 & s10) >>> 8));
    }

    @Override // xb.l
    public final m c0(long j10) throws EOFException {
        if (j10 < 0 || j10 > 2147483647L) {
            throw new IllegalArgumentException(androidx.compose.foundation.c.q(j10, "byteCount: ").toString());
        }
        if (this.f22759l < j10) {
            throw new EOFException();
        }
        if (j10 < 4096) {
            return new m(T(j10));
        }
        m mVarU0 = u0((int) j10);
        skip(j10);
        return mVarU0;
    }

    public final Object clone() {
        j jVar = new j();
        if (this.f22759l != 0) {
            j0 j0Var = this.f22758i;
            j0 j0VarC = j0Var.c();
            jVar.f22758i = j0VarC;
            j0VarC.f22766g = j0VarC;
            j0VarC.f22765f = j0VarC;
            for (j0 j0Var2 = j0Var.f22765f; j0Var2 != j0Var; j0Var2 = j0Var2.f22765f) {
                j0VarC.f22766g.b(j0Var2.c());
            }
            jVar.f22759l = this.f22759l;
        }
        return jVar;
    }

    @Override // xb.l
    public final int d0(e0 e0Var) {
        int iB = yb.a.b(this, e0Var, false);
        if (iB == -1) {
            return -1;
        }
        skip(e0Var.f22731i[iB].f());
        return iB;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof j)) {
            return false;
        }
        long j10 = this.f22759l;
        j jVar = (j) obj;
        if (j10 != jVar.f22759l) {
            return false;
        }
        if (j10 == 0) {
            return true;
        }
        j0 j0Var = this.f22758i;
        j0 j0Var2 = jVar.f22758i;
        int i10 = j0Var.f22761b;
        int i11 = j0Var2.f22761b;
        long j11 = 0;
        while (j11 < this.f22759l) {
            long jMin = Math.min(j0Var.f22762c - i10, j0Var2.f22762c - i11);
            long j12 = 0;
            while (j12 < jMin) {
                int i12 = i10 + 1;
                int i13 = i11 + 1;
                if (j0Var.f22760a[i10] != j0Var2.f22760a[i11]) {
                    return false;
                }
                j12++;
                i10 = i12;
                i11 = i13;
            }
            if (i10 == j0Var.f22762c) {
                j0Var = j0Var.f22765f;
                i10 = j0Var.f22761b;
            }
            if (i11 == j0Var2.f22762c) {
                j0Var2 = j0Var2.f22765f;
                i11 = j0Var2.f22761b;
            }
            j11 += jMin;
        }
        return true;
    }

    public final int hashCode() {
        j0 j0Var = this.f22758i;
        if (j0Var == null) {
            return 0;
        }
        int i10 = 1;
        do {
            int i11 = j0Var.f22762c;
            for (int i12 = j0Var.f22761b; i12 < i11; i12++) {
                i10 = (i10 * 31) + j0Var.f22760a[i12];
            }
            j0Var = j0Var.f22765f;
        } while (j0Var != this.f22758i);
        return i10;
    }

    public final void i() {
        skip(this.f22759l);
    }

    @Override // xb.l
    public final byte[] i0() {
        return T(this.f22759l);
    }

    @Override // xb.l
    public final InputStream inputStream() {
        return new h(this, 0);
    }

    @Override // java.nio.channels.Channel
    public final boolean isOpen() {
        return true;
    }

    public final long j() {
        long j10 = this.f22759l;
        if (j10 == 0) {
            return 0L;
        }
        j0 j0Var = this.f22758i.f22766g;
        int i10 = j0Var.f22762c;
        return (i10 >= 8192 || !j0Var.f22764e) ? j10 : j10 - ((long) (i10 - j0Var.f22761b));
    }

    public final void l(j jVar, long j10, long j11) {
        long j12 = j10;
        b.c(this.f22759l, j12, j11);
        if (j11 == 0) {
            return;
        }
        jVar.f22759l += j11;
        j0 j0Var = this.f22758i;
        while (true) {
            long j13 = j0Var.f22762c - j0Var.f22761b;
            if (j12 < j13) {
                break;
            }
            j12 -= j13;
            j0Var = j0Var.f22765f;
        }
        j0 j0Var2 = j0Var;
        long j14 = j11;
        while (j14 > 0) {
            j0 j0VarC = j0Var2.c();
            int i10 = j0VarC.f22761b + ((int) j12);
            j0VarC.f22761b = i10;
            j0VarC.f22762c = Math.min(i10 + ((int) j14), j0VarC.f22762c);
            j0 j0Var3 = jVar.f22758i;
            if (j0Var3 == null) {
                j0VarC.f22766g = j0VarC;
                j0VarC.f22765f = j0VarC;
                jVar.f22758i = j0VarC;
            } else {
                j0Var3.f22766g.b(j0VarC);
            }
            j14 -= (long) (j0VarC.f22762c - j0VarC.f22761b);
            j0Var2 = j0Var2.f22765f;
            j12 = 0;
        }
    }

    public final boolean o() {
        return this.f22759l == 0;
    }

    @Override // xb.k
    public final /* bridge */ /* synthetic */ k p0(int i10, byte[] bArr) {
        write(bArr, 0, i10);
        return this;
    }

    @Override // xb.l
    public final i0 peek() {
        return new i0(new g0(this));
    }

    public final String q0(long j10, Charset charset) {
        if (j10 < 0 || j10 > 2147483647L) {
            throw new IllegalArgumentException(androidx.compose.foundation.c.q(j10, "byteCount: ").toString());
        }
        if (this.f22759l < j10) {
            throw new EOFException();
        }
        if (j10 == 0) {
            return "";
        }
        j0 j0Var = this.f22758i;
        int i10 = j0Var.f22761b;
        if (((long) i10) + j10 > j0Var.f22762c) {
            return new String(T(j10), charset);
        }
        int i11 = (int) j10;
        String str = new String(j0Var.f22760a, i10, i11, charset);
        int i12 = j0Var.f22761b + i11;
        j0Var.f22761b = i12;
        this.f22759l -= j10;
        if (i12 == j0Var.f22762c) {
            this.f22758i = j0Var.a();
            k0.a(j0Var);
        }
        return str;
    }

    @Override // xb.l
    public final String r0(Charset charset) {
        return q0(this.f22759l, charset);
    }

    @Override // xb.o0
    public final long read(j jVar, long j10) {
        if (j10 < 0) {
            throw new IllegalArgumentException(androidx.compose.foundation.c.q(j10, "byteCount < 0: ").toString());
        }
        long j11 = this.f22759l;
        if (j11 == 0) {
            return -1L;
        }
        if (j10 > j11) {
            j10 = j11;
        }
        jVar.write(this, j10);
        return j10;
    }

    @Override // xb.l
    public final byte readByte() {
        long j10 = this.f22759l;
        if (j10 == 0) {
            throw new EOFException();
        }
        j0 j0Var = this.f22758i;
        int i10 = j0Var.f22761b;
        int i11 = j0Var.f22762c;
        int i12 = i10 + 1;
        byte b10 = j0Var.f22760a[i10];
        this.f22759l = j10 - 1;
        if (i12 != i11) {
            j0Var.f22761b = i12;
            return b10;
        }
        this.f22758i = j0Var.a();
        k0.a(j0Var);
        return b10;
    }

    @Override // xb.l
    public final void readFully(byte[] bArr) throws EOFException {
        int i10 = 0;
        while (i10 < bArr.length) {
            int i11 = read(bArr, i10, bArr.length - i10);
            if (i11 == -1) {
                throw new EOFException();
            }
            i10 += i11;
        }
    }

    @Override // xb.l
    public final int readInt() throws EOFException {
        long j10 = this.f22759l;
        if (j10 < 4) {
            throw new EOFException();
        }
        j0 j0Var = this.f22758i;
        int i10 = j0Var.f22761b;
        int i11 = j0Var.f22762c;
        if (i11 - i10 < 4) {
            return ((readByte() & 255) << 24) | ((readByte() & 255) << 16) | ((readByte() & 255) << 8) | (readByte() & 255);
        }
        byte[] bArr = j0Var.f22760a;
        int i12 = i10 + 3;
        int i13 = ((bArr[i10 + 1] & 255) << 16) | ((bArr[i10] & 255) << 24) | ((bArr[i10 + 2] & 255) << 8);
        int i14 = i10 + 4;
        int i15 = (bArr[i12] & 255) | i13;
        this.f22759l = j10 - 4;
        if (i14 != i11) {
            j0Var.f22761b = i14;
            return i15;
        }
        this.f22758i = j0Var.a();
        k0.a(j0Var);
        return i15;
    }

    @Override // xb.l
    public final long readLong() throws EOFException {
        long j10 = this.f22759l;
        if (j10 < 8) {
            throw new EOFException();
        }
        j0 j0Var = this.f22758i;
        int i10 = j0Var.f22761b;
        int i11 = j0Var.f22762c;
        if (i11 - i10 < 8) {
            return ((((long) readInt()) & 4294967295L) << 32) | (4294967295L & ((long) readInt()));
        }
        byte[] bArr = j0Var.f22760a;
        int i12 = i10 + 7;
        long j11 = ((((long) bArr[i10 + 1]) & 255) << 48) | ((((long) bArr[i10]) & 255) << 56) | ((((long) bArr[i10 + 2]) & 255) << 40) | ((((long) bArr[i10 + 3]) & 255) << 32) | ((((long) bArr[i10 + 4]) & 255) << 24) | ((((long) bArr[i10 + 5]) & 255) << 16) | ((((long) bArr[i10 + 6]) & 255) << 8);
        int i13 = i10 + 8;
        long j12 = j11 | (((long) bArr[i12]) & 255);
        this.f22759l = j10 - 8;
        if (i13 != i11) {
            j0Var.f22761b = i13;
            return j12;
        }
        this.f22758i = j0Var.a();
        k0.a(j0Var);
        return j12;
    }

    @Override // xb.l
    public final short readShort() throws EOFException {
        long j10 = this.f22759l;
        if (j10 < 2) {
            throw new EOFException();
        }
        j0 j0Var = this.f22758i;
        int i10 = j0Var.f22761b;
        int i11 = j0Var.f22762c;
        if (i11 - i10 < 2) {
            return (short) (((readByte() & 255) << 8) | (readByte() & 255));
        }
        byte[] bArr = j0Var.f22760a;
        int i12 = i10 + 1;
        int i13 = (bArr[i10] & 255) << 8;
        int i14 = i10 + 2;
        int i15 = (bArr[i12] & 255) | i13;
        this.f22759l = j10 - 2;
        if (i14 == i11) {
            this.f22758i = j0Var.a();
            k0.a(j0Var);
        } else {
            j0Var.f22761b = i14;
        }
        return (short) i15;
    }

    @Override // xb.l
    public final boolean request(long j10) {
        return this.f22759l >= j10;
    }

    @Override // xb.l
    public final boolean s0(long j10, m mVar) {
        int iF = mVar.f();
        if (j10 >= 0 && iF >= 0 && this.f22759l - j10 >= iF && mVar.f() >= iF) {
            for (int i10 = 0; i10 < iF; i10++) {
                if (x(((long) i10) + j10) == mVar.m(i10)) {
                }
            }
            return true;
        }
        return false;
    }

    @Override // xb.l
    public final void skip(long j10) {
        while (j10 > 0) {
            j0 j0Var = this.f22758i;
            if (j0Var == null) {
                throw new EOFException();
            }
            int iMin = (int) Math.min(j10, j0Var.f22762c - j0Var.f22761b);
            long j11 = iMin;
            this.f22759l -= j11;
            j10 -= j11;
            int i10 = j0Var.f22761b + iMin;
            j0Var.f22761b = i10;
            if (i10 == j0Var.f22762c) {
                this.f22758i = j0Var.a();
                k0.a(j0Var);
            }
        }
    }

    public final String t0() {
        return q0(this.f22759l, kotlin.text.a.f19924a);
    }

    @Override // xb.o0
    public final q0 timeout() {
        return q0.f22791d;
    }

    public final String toString() {
        long j10 = this.f22759l;
        if (j10 <= 2147483647L) {
            return u0((int) j10).toString();
        }
        throw new IllegalStateException(("size > Int.MAX_VALUE: " + this.f22759l).toString());
    }

    public final m u0(int i10) {
        if (i10 == 0) {
            return m.f22772n;
        }
        b.c(this.f22759l, 0L, i10);
        j0 j0Var = this.f22758i;
        int i11 = 0;
        int i12 = 0;
        int i13 = 0;
        while (i12 < i10) {
            int i14 = j0Var.f22762c;
            int i15 = j0Var.f22761b;
            if (i14 == i15) {
                throw new AssertionError("s.limit == s.pos");
            }
            i12 += i14 - i15;
            i13++;
            j0Var = j0Var.f22765f;
        }
        byte[][] bArr = new byte[i13][];
        int[] iArr = new int[i13 * 2];
        j0 j0Var2 = this.f22758i;
        int i16 = 0;
        while (i11 < i10) {
            bArr[i16] = j0Var2.f22760a;
            i11 += j0Var2.f22762c - j0Var2.f22761b;
            iArr[i16] = Math.min(i11, i10);
            iArr[i16 + i13] = j0Var2.f22761b;
            j0Var2.f22763d = true;
            i16++;
            j0Var2 = j0Var2.f22765f;
        }
        return new l0(bArr, iArr);
    }

    @Override // xb.k
    public final /* bridge */ /* synthetic */ k v0(long j10) {
        O0(j10);
        return this;
    }

    @Override // xb.m0
    public final void write(j jVar, long j10) {
        j0 j0VarB;
        if (jVar == this) {
            throw new IllegalArgumentException("source == this");
        }
        b.c(jVar.f22759l, 0L, j10);
        while (j10 > 0) {
            j0 j0Var = jVar.f22758i;
            int i10 = j0Var.f22762c - j0Var.f22761b;
            if (j10 < i10) {
                j0 j0Var2 = this.f22758i;
                j0 j0Var3 = j0Var2 != null ? j0Var2.f22766g : null;
                if (j0Var3 != null && j0Var3.f22764e) {
                    if ((((long) j0Var3.f22762c) + j10) - ((long) (j0Var3.f22763d ? 0 : j0Var3.f22761b)) <= 8192) {
                        j0Var.d(j0Var3, (int) j10);
                        jVar.f22759l -= j10;
                        this.f22759l += j10;
                        return;
                    }
                }
                int i11 = (int) j10;
                if (i11 <= 0 || i11 > i10) {
                    throw new IllegalArgumentException("byteCount out of range");
                }
                if (i11 >= 1024) {
                    j0VarB = j0Var.c();
                } else {
                    j0VarB = k0.b();
                    byte[] bArr = j0Var.f22760a;
                    byte[] bArr2 = j0VarB.f22760a;
                    int i12 = j0Var.f22761b;
                    kotlin.collections.r.P(bArr, i12, i12 + i11, bArr2);
                }
                j0VarB.f22762c = j0VarB.f22761b + i11;
                j0Var.f22761b += i11;
                j0Var.f22766g.b(j0VarB);
                jVar.f22758i = j0VarB;
            }
            j0 j0Var4 = jVar.f22758i;
            long j11 = j0Var4.f22762c - j0Var4.f22761b;
            jVar.f22758i = j0Var4.a();
            j0 j0Var5 = this.f22758i;
            if (j0Var5 == null) {
                this.f22758i = j0Var4;
                j0Var4.f22766g = j0Var4;
                j0Var4.f22765f = j0Var4;
            } else {
                j0Var5.f22766g.b(j0Var4);
                j0 j0Var6 = j0Var4.f22766g;
                if (j0Var6 == j0Var4) {
                    throw new IllegalStateException("cannot compact");
                }
                if (j0Var6.f22764e) {
                    int i13 = j0Var4.f22762c - j0Var4.f22761b;
                    if (i13 <= (8192 - j0Var6.f22762c) + (j0Var6.f22763d ? 0 : j0Var6.f22761b)) {
                        j0Var4.d(j0Var6, i13);
                        j0Var4.a();
                        k0.a(j0Var4);
                    }
                }
            }
            jVar.f22759l -= j11;
            this.f22759l += j11;
            j10 -= j11;
        }
    }

    @Override // xb.k
    public final /* bridge */ /* synthetic */ k writeByte(int i10) {
        M0(i10);
        return this;
    }

    @Override // xb.k
    public final /* bridge */ /* synthetic */ k writeInt(int i10) {
        P0(i10);
        return this;
    }

    @Override // xb.k
    public final /* bridge */ /* synthetic */ k writeShort(int i10) {
        Q0(i10);
        return this;
    }

    public final byte x(long j10) {
        b.c(this.f22759l, j10, 1L);
        j0 j0Var = this.f22758i;
        j0Var.getClass();
        long j11 = this.f22759l;
        if (j11 - j10 < j10) {
            while (j11 > j10) {
                j0Var = j0Var.f22766g;
                j11 -= (long) (j0Var.f22762c - j0Var.f22761b);
            }
            return j0Var.f22760a[(int) ((((long) j0Var.f22761b) + j10) - j11)];
        }
        long j12 = 0;
        while (true) {
            int i10 = j0Var.f22762c;
            int i11 = j0Var.f22761b;
            long j13 = ((long) (i10 - i11)) + j12;
            if (j13 > j10) {
                return j0Var.f22760a[(int) ((((long) i11) + j10) - j12)];
            }
            j0Var = j0Var.f22765f;
            j12 = j13;
        }
    }

    @Override // xb.l
    public final m x0() {
        return c0(this.f22759l);
    }

    @Override // xb.k
    public final long z0(o0 o0Var) {
        long j10 = 0;
        while (true) {
            long j11 = o0Var.read(this, 8192L);
            if (j11 == -1) {
                return j10;
            }
            j10 += j11;
        }
    }

    @Override // java.nio.channels.ReadableByteChannel
    public final int read(ByteBuffer byteBuffer) {
        j0 j0Var = this.f22758i;
        if (j0Var == null) {
            return -1;
        }
        int iMin = Math.min(byteBuffer.remaining(), j0Var.f22762c - j0Var.f22761b);
        byteBuffer.put(j0Var.f22760a, j0Var.f22761b, iMin);
        int i10 = j0Var.f22761b + iMin;
        j0Var.f22761b = i10;
        this.f22759l -= (long) iMin;
        if (i10 == j0Var.f22762c) {
            this.f22758i = j0Var.a();
            k0.a(j0Var);
        }
        return iMin;
    }

    public final int read(byte[] bArr, int i10, int i11) {
        b.c(bArr.length, i10, i11);
        j0 j0Var = this.f22758i;
        if (j0Var == null) {
            return -1;
        }
        int iMin = Math.min(i11, j0Var.f22762c - j0Var.f22761b);
        byte[] bArr2 = j0Var.f22760a;
        int i12 = j0Var.f22761b;
        kotlin.collections.r.L(bArr2, i10, i12, bArr, i12 + iMin);
        int i13 = j0Var.f22761b + iMin;
        j0Var.f22761b = i13;
        this.f22759l -= (long) iMin;
        if (i13 == j0Var.f22762c) {
            this.f22758i = j0Var.a();
            k0.a(j0Var);
        }
        return iMin;
    }

    @Override // java.nio.channels.WritableByteChannel
    public final int write(ByteBuffer byteBuffer) {
        int iRemaining = byteBuffer.remaining();
        int i10 = iRemaining;
        while (i10 > 0) {
            j0 j0VarD0 = D0(1);
            int iMin = Math.min(i10, 8192 - j0VarD0.f22762c);
            byteBuffer.get(j0VarD0.f22760a, j0VarD0.f22762c, iMin);
            i10 -= iMin;
            j0VarD0.f22762c += iMin;
        }
        this.f22759l += (long) iRemaining;
        return iRemaining;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable, java.nio.channels.Channel, xb.m0
    public final void close() {
    }

    @Override // xb.k, xb.m0, java.io.Flushable
    public final void flush() {
    }

    @Override // xb.l
    public final j h() {
        return this;
    }

    @Override // xb.k
    public final k q() {
        return this;
    }

    @Override // xb.k
    public final k write(byte[] bArr) {
        write(bArr, 0, bArr.length);
        return this;
    }

    public final void write(byte[] bArr, int i10, int i11) {
        long j10 = i11;
        b.c(bArr.length, i10, j10);
        int i12 = i11 + i10;
        while (i10 < i12) {
            j0 j0VarD0 = D0(1);
            int iMin = Math.min(i12 - i10, 8192 - j0VarD0.f22762c);
            int i13 = i10 + iMin;
            kotlin.collections.r.L(bArr, j0VarD0.f22762c, i10, j0VarD0.f22760a, i13);
            j0VarD0.f22762c += iMin;
            i10 = i13;
        }
        this.f22759l += j10;
    }
}
