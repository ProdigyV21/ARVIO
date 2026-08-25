package xb;

import java.io.Serializable;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

/* JADX INFO: loaded from: classes5.dex */
public class m implements Serializable, Comparable {

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public static final m f22772n = new m(new byte[0]);

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final byte[] f22773i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public transient int f22774l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public transient String f22775m;

    public m(byte[] bArr) {
        this.f22773i = bArr;
    }

    public static int k(m mVar, m mVar2) {
        mVar.getClass();
        return mVar.j(0, mVar2.l());
    }

    public static /* synthetic */ m r(m mVar, int i10, int i11, int i12) {
        if ((i12 & 1) != 0) {
            i10 = 0;
        }
        if ((i12 & 2) != 0) {
            i11 = -1234567890;
        }
        return mVar.q(i10, i11);
    }

    @Override // java.lang.Comparable
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public final int compareTo(m mVar) {
        int iF = f();
        int iF2 = mVar.f();
        int iMin = Math.min(iF, iF2);
        for (int i10 = 0; i10 < iMin; i10++) {
            int iM = m(i10) & 255;
            int iM2 = mVar.m(i10) & 255;
            if (iM != iM2) {
                return iM < iM2 ? -1 : 1;
            }
        }
        if (iF == iF2) {
            return 0;
        }
        return iF < iF2 ? -1 : 1;
    }

    public m d(String str) throws NoSuchAlgorithmException {
        MessageDigest messageDigest = MessageDigest.getInstance(str);
        messageDigest.update(this.f22773i, 0, f());
        return new m(messageDigest.digest());
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof m) {
            m mVar = (m) obj;
            int iF = mVar.f();
            byte[] bArr = this.f22773i;
            if (iF == bArr.length && mVar.o(0, 0, bArr.length, bArr)) {
                return true;
            }
        }
        return false;
    }

    public int f() {
        return this.f22773i.length;
    }

    public int hashCode() {
        int i10 = this.f22774l;
        if (i10 != 0) {
            return i10;
        }
        int iHashCode = Arrays.hashCode(this.f22773i);
        this.f22774l = iHashCode;
        return iHashCode;
    }

    public String i() {
        byte[] bArr = this.f22773i;
        char[] cArr = new char[bArr.length * 2];
        int i10 = 0;
        for (byte b10 : bArr) {
            int i11 = i10 + 1;
            char[] cArr2 = yb.b.f23007a;
            cArr[i10] = cArr2[(b10 >> 4) & 15];
            i10 += 2;
            cArr[i11] = cArr2[b10 & 15];
        }
        return new String(cArr);
    }

    public int j(int i10, byte[] bArr) {
        byte[] bArr2 = this.f22773i;
        int length = bArr2.length - bArr.length;
        int iMax = Math.max(i10, 0);
        if (iMax > length) {
            return -1;
        }
        while (!b.a(bArr2, iMax, 0, bArr, bArr.length)) {
            if (iMax == length) {
                return -1;
            }
            iMax++;
        }
        return iMax;
    }

    public byte[] l() {
        return this.f22773i;
    }

    public byte m(int i10) {
        return this.f22773i[i10];
    }

    public int n(byte[] bArr) {
        int iF = f();
        byte[] bArr2 = this.f22773i;
        for (int iMin = Math.min(iF, bArr2.length - bArr.length); -1 < iMin; iMin--) {
            if (b.a(bArr2, iMin, 0, bArr, bArr.length)) {
                return iMin;
            }
        }
        return -1;
    }

    public boolean o(int i10, int i11, int i12, byte[] bArr) {
        if (i10 < 0) {
            return false;
        }
        byte[] bArr2 = this.f22773i;
        return i10 <= bArr2.length - i12 && i11 >= 0 && i11 <= bArr.length - i12 && b.a(bArr2, i10, i11, bArr, i12);
    }

    public boolean p(int i10, m mVar, int i11) {
        return mVar.o(0, i10, i11, this.f22773i);
    }

    public m q(int i10, int i11) {
        if (i11 == -1234567890) {
            i11 = f();
        }
        if (i10 < 0) {
            throw new IllegalArgumentException("beginIndex < 0");
        }
        byte[] bArr = this.f22773i;
        if (i11 > bArr.length) {
            throw new IllegalArgumentException(a0.c.o(new StringBuilder("endIndex > length("), bArr.length, ')').toString());
        }
        if (i11 - i10 < 0) {
            throw new IllegalArgumentException("endIndex < beginIndex");
        }
        if (i10 == 0 && i11 == bArr.length) {
            return this;
        }
        qb.l.j(i11, bArr.length);
        return new m(Arrays.copyOfRange(bArr, i10, i11));
    }

    public m s() {
        int i10 = 0;
        while (true) {
            byte[] bArr = this.f22773i;
            if (i10 >= bArr.length) {
                return this;
            }
            byte b10 = bArr[i10];
            if (b10 >= 65 && b10 <= 90) {
                byte[] bArrCopyOf = Arrays.copyOf(bArr, bArr.length);
                bArrCopyOf[i10] = (byte) (b10 + 32);
                for (int i11 = i10 + 1; i11 < bArrCopyOf.length; i11++) {
                    byte b11 = bArrCopyOf[i11];
                    if (b11 >= 65 && b11 <= 90) {
                        bArrCopyOf[i11] = (byte) (b11 + 32);
                    }
                }
                return new m(bArrCopyOf);
            }
            i10++;
        }
    }

    public final String t() {
        String str = this.f22775m;
        if (str != null) {
            return str;
        }
        String str2 = new String(l(), kotlin.text.a.f19924a);
        this.f22775m = str2;
        return str2;
    }

    public String toString() {
        m mVar;
        byte b10;
        int i10;
        byte[] bArr = this.f22773i;
        if (bArr.length == 0) {
            return "[size=0]";
        }
        int length = bArr.length;
        int i11 = 0;
        int i12 = 0;
        int i13 = 0;
        loop0: while (true) {
            if (i11 >= length) {
                break;
            }
            byte b11 = bArr[i11];
            if (b11 >= 0) {
                int i14 = i13 + 1;
                if (i13 == 64) {
                    break;
                }
                if ((b11 != 10 && b11 != 13 && ((b11 >= 0 && b11 < 32) || (127 <= b11 && b11 < 160))) || b11 == 65533) {
                    break;
                }
                i12 += b11 < 65536 ? 1 : 2;
                i11++;
                while (true) {
                    i13 = i14;
                    if (i11 < length && (b10 = bArr[i11]) >= 0) {
                        i11++;
                        i14 = i13 + 1;
                        if (i13 == 64) {
                            break loop0;
                        }
                        if ((b10 != 10 && b10 != 13 && ((b10 >= 0 && b10 < 32) || (127 <= b10 && b10 < 160))) || b10 == 65533) {
                            break loop0;
                        }
                        i12 += b10 < 65536 ? 1 : 2;
                    } else {
                        break;
                    }
                }
            } else if ((b11 >> 5) == -2) {
                int i15 = i11 + 1;
                if (length > i15) {
                    byte b12 = bArr[i15];
                    if ((b12 & 192) == 128) {
                        int i16 = (b12 ^ 3968) ^ (b11 << 6);
                        if (i16 >= 128) {
                            i10 = i13 + 1;
                            if (i13 == 64) {
                                break;
                            }
                            if ((i16 != 10 && i16 != 13 && ((i16 >= 0 && i16 < 32) || (127 <= i16 && i16 < 160))) || i16 == 65533) {
                                break;
                            }
                            i12 += i16 < 65536 ? 1 : 2;
                            i11 += 2;
                            i13 = i10;
                        } else if (i13 != 64) {
                            break;
                        }
                    } else if (i13 != 64) {
                        break;
                    }
                } else if (i13 != 64) {
                    break;
                }
            } else if ((b11 >> 4) == -2) {
                int i17 = i11 + 2;
                if (length > i17) {
                    byte b13 = bArr[i11 + 1];
                    if ((b13 & 192) == 128) {
                        byte b14 = bArr[i17];
                        if ((b14 & 192) == 128) {
                            int i18 = ((b14 ^ (-123008)) ^ (b13 << 6)) ^ (b11 << 12);
                            if (i18 < 2048) {
                                if (i13 != 64) {
                                    break;
                                }
                            } else if (55296 > i18 || i18 >= 57344) {
                                i10 = i13 + 1;
                                if (i13 == 64) {
                                    break;
                                }
                                if ((i18 != 10 && i18 != 13 && ((i18 >= 0 && i18 < 32) || (127 <= i18 && i18 < 160))) || i18 == 65533) {
                                    break;
                                }
                                i12 += i18 < 65536 ? 1 : 2;
                                i11 += 3;
                                i13 = i10;
                            } else if (i13 != 64) {
                                break;
                            }
                        } else if (i13 != 64) {
                            break;
                        }
                    } else if (i13 != 64) {
                        break;
                    }
                } else if (i13 != 64) {
                    break;
                }
            } else if ((b11 >> 3) == -2) {
                int i19 = i11 + 3;
                if (length > i19) {
                    byte b15 = bArr[i11 + 1];
                    if ((b15 & 192) == 128) {
                        byte b16 = bArr[i11 + 2];
                        if ((b16 & 192) == 128) {
                            byte b17 = bArr[i19];
                            if ((b17 & 192) == 128) {
                                int i20 = (((b17 ^ 3678080) ^ (b16 << 6)) ^ (b15 << 12)) ^ (b11 << 18);
                                if (i20 > 1114111) {
                                    if (i13 != 64) {
                                        break;
                                    }
                                } else if (55296 > i20 || i20 >= 57344) {
                                    if (i20 >= 65536) {
                                        i10 = i13 + 1;
                                        if (i13 == 64) {
                                            break;
                                        }
                                        if ((i20 != 10 && i20 != 13 && ((i20 >= 0 && i20 < 32) || (127 <= i20 && i20 < 160))) || i20 == 65533) {
                                            break;
                                        }
                                        i12 += i20 < 65536 ? 1 : 2;
                                        i11 += 4;
                                        i13 = i10;
                                    } else if (i13 != 64) {
                                        break;
                                    }
                                } else if (i13 != 64) {
                                    break;
                                }
                            } else if (i13 != 64) {
                                break;
                            }
                        } else if (i13 != 64) {
                            break;
                        }
                    } else if (i13 != 64) {
                        break;
                    }
                } else if (i13 != 64) {
                    break;
                }
            } else if (i13 != 64) {
                break;
            }
        }
        i12 = -1;
        if (i12 != -1) {
            String strT = t();
            String strO = kotlin.text.u.O(kotlin.text.u.O(kotlin.text.u.O(strT.substring(0, i12), "\\", "\\\\", false), "\n", "\\n", false), "\r", "\\r", false);
            if (i12 >= strT.length()) {
                return androidx.compose.material3.d.i(']', "[text=", strO);
            }
            return "[size=" + bArr.length + " text=" + strO + "…]";
        }
        if (bArr.length <= 64) {
            return "[hex=" + i() + ']';
        }
        StringBuilder sb2 = new StringBuilder("[size=");
        sb2.append(bArr.length);
        sb2.append(" hex=");
        if (64 > bArr.length) {
            throw new IllegalArgumentException(a0.c.o(new StringBuilder("endIndex > length("), bArr.length, ')').toString());
        }
        if (64 == bArr.length) {
            mVar = this;
        } else {
            qb.l.j(64, bArr.length);
            mVar = new m(Arrays.copyOfRange(bArr, 0, 64));
        }
        sb2.append(mVar.i());
        sb2.append("…]");
        return sb2.toString();
    }

    public void u(j jVar, int i10) {
        jVar.write(this.f22773i, 0, i10);
    }
}
