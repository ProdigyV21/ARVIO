package androidx.datastore.preferences.protobuf;

import androidx.compose.ui.graphics.colorspace.ColorSpace;
import androidx.media3.container.MdtaMetadataEntry;
import androidx.media3.container.NalUnitUtil;
import androidx.media3.extractor.AacUtil;
import androidx.media3.extractor.flac.FlacConstants;
import androidx.media3.extractor.ts.TsExtractor;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;
import sun.misc.Unsafe;

/* JADX INFO: loaded from: classes3.dex */
public final class y1 implements o2 {

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public static final int[] f2550o = new int[0];

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public static final Unsafe f2551p = p3.i();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final int[] f2552a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Object[] f2553b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final int f2554c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final int f2555d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final v1 f2556e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final boolean f2557f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final boolean f2558g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final int[] f2559h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final int f2560i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final int f2561j;
    public final d2 k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final l1 f2562l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final f3 f2563m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final q1 f2564n;

    public y1(int[] iArr, Object[] objArr, int i10, int i11, v1 v1Var, boolean z, int[] iArr2, int i12, int i13, d2 d2Var, l1 l1Var, f3 f3Var, e0 e0Var, q1 q1Var) {
        this.f2552a = iArr;
        this.f2553b = objArr;
        this.f2554c = i10;
        this.f2555d = i11;
        this.f2557f = v1Var instanceof r0;
        this.f2558g = z;
        this.f2559h = iArr2;
        this.f2560i = i12;
        this.f2561j = i13;
        this.k = d2Var;
        this.f2562l = l1Var;
        this.f2563m = f3Var;
        this.f2556e = v1Var;
        this.f2564n = q1Var;
    }

    public static long A(long j10, Object obj) {
        return ((Long) p3.f2500d.i(j10, obj)).longValue();
    }

    public static Field C(Class cls, String str) {
        try {
            return cls.getDeclaredField(str);
        } catch (NoSuchFieldException unused) {
            Field[] declaredFields = cls.getDeclaredFields();
            for (Field field : declaredFields) {
                if (str.equals(field.getName())) {
                    return field;
                }
            }
            StringBuilder sbT = a0.c.t("Field ", str, " for ");
            sbT.append(cls.getName());
            sbT.append(" not found. Known fields are ");
            sbT.append(Arrays.toString(declaredFields));
            throw new RuntimeException(sbT.toString());
        }
    }

    public static int F(int i10) {
        return (i10 & 267386880) >>> 20;
    }

    public static void J(int i10, Object obj, a4 a4Var) {
        if (obj instanceof String) {
            a4Var.i(i10, (String) obj);
        } else {
            a4Var.H(i10, (m) obj);
        }
    }

    public static List s(long j10, Object obj) {
        return (List) p3.f2500d.i(j10, obj);
    }

    public static y1 w(t1 t1Var, d2 d2Var, l1 l1Var, f3 f3Var, e0 e0Var, q1 q1Var) {
        if (t1Var instanceof m2) {
            return x((m2) t1Var, d2Var, l1Var, f3Var, e0Var, q1Var);
        }
        throw new ClassCastException();
    }

    /* JADX WARN: Removed duplicated region for block: B:167:0x0371  */
    /* JADX WARN: Removed duplicated region for block: B:182:0x03bb  */
    /* JADX WARN: Removed duplicated region for block: B:188:0x03d6  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static androidx.datastore.preferences.protobuf.y1 x(androidx.datastore.preferences.protobuf.m2 r37, androidx.datastore.preferences.protobuf.d2 r38, androidx.datastore.preferences.protobuf.l1 r39, androidx.datastore.preferences.protobuf.f3 r40, androidx.datastore.preferences.protobuf.e0 r41, androidx.datastore.preferences.protobuf.q1 r42) {
        /*
            Method dump skipped, instruction units count: 1075
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.datastore.preferences.protobuf.y1.x(androidx.datastore.preferences.protobuf.m2, androidx.datastore.preferences.protobuf.d2, androidx.datastore.preferences.protobuf.l1, androidx.datastore.preferences.protobuf.f3, androidx.datastore.preferences.protobuf.e0, androidx.datastore.preferences.protobuf.q1):androidx.datastore.preferences.protobuf.y1");
    }

    public static long y(int i10) {
        return i10 & 1048575;
    }

    public static int z(long j10, Object obj) {
        return ((Integer) p3.f2500d.i(j10, obj)).intValue();
    }

    public final void B(Object obj, int i10, n2 n2Var) {
        if ((536870912 & i10) != 0) {
            p3.o(obj, n2Var.M(), i10 & 1048575);
        } else if (this.f2557f) {
            p3.o(obj, n2Var.D(), i10 & 1048575);
        } else {
            p3.o(obj, n2Var.p(), i10 & 1048575);
        }
    }

    public final void D(int i10, Object obj) {
        if (this.f2558g) {
            return;
        }
        int i11 = this.f2552a[i10 + 2];
        long j10 = i11 & 1048575;
        p3.m(p3.f2500d.g(j10, obj) | (1 << (i11 >>> 20)), j10, obj);
    }

    public final void E(int i10, int i11, Object obj) {
        p3.m(i10, this.f2552a[i11 + 2] & 1048575, obj);
    }

    public final int G(int i10) {
        return this.f2552a[i10 + 1];
    }

    public final void H(Object obj, a4 a4Var) {
        int i10;
        int i11;
        int i12;
        int i13;
        boolean z;
        int[] iArr = this.f2552a;
        int length = iArr.length;
        Unsafe unsafe = f2551p;
        int i14 = -1;
        int i15 = 0;
        for (int i16 = 0; i16 < length; i16 = i13 + 3) {
            int iG = G(i16);
            int i17 = iArr[i16];
            int iF = F(iG);
            if (this.f2558g || iF > 17) {
                i10 = i16;
                i11 = 1048575;
                i12 = 0;
            } else {
                int i18 = iArr[i16 + 2];
                i11 = 1048575;
                int i19 = i18 & 1048575;
                i10 = i16;
                if (i19 != i14) {
                    i15 = unsafe.getInt(obj, i19);
                    i14 = i19;
                }
                i12 = 1 << (i18 >>> 20);
            }
            long j10 = iG & i11;
            switch (iF) {
                case 0:
                    i13 = i10;
                    if ((i12 & i15) != 0) {
                        a4Var.w(i17, p3.f2500d.e(j10, obj));
                    }
                    break;
                case 1:
                    i13 = i10;
                    if ((i12 & i15) != 0) {
                        a4Var.B(p3.f2500d.f(j10, obj), i17);
                    }
                    break;
                case 2:
                    i13 = i10;
                    if ((i12 & i15) != 0) {
                        a4Var.D(i17, unsafe.getLong(obj, j10));
                    }
                    break;
                case 3:
                    i13 = i10;
                    if ((i12 & i15) != 0) {
                        a4Var.k(i17, unsafe.getLong(obj, j10));
                    }
                    break;
                case 4:
                    i13 = i10;
                    if ((i12 & i15) != 0) {
                        a4Var.m(i17, unsafe.getInt(obj, j10));
                    }
                    break;
                case 5:
                    i13 = i10;
                    if ((i12 & i15) != 0) {
                        a4Var.y(i17, unsafe.getLong(obj, j10));
                    }
                    break;
                case 6:
                    i13 = i10;
                    if ((i12 & i15) != 0) {
                        a4Var.d(i17, unsafe.getInt(obj, j10));
                    }
                    break;
                case 7:
                    i13 = i10;
                    if ((i12 & i15) != 0) {
                        a4Var.E(i17, p3.f2500d.c(j10, obj));
                    }
                    break;
                case 8:
                    i13 = i10;
                    if ((i12 & i15) != 0) {
                        J(i17, unsafe.getObject(obj, j10), a4Var);
                    }
                    break;
                case 9:
                    i13 = i10;
                    if ((i12 & i15) != 0) {
                        a4Var.p(i17, unsafe.getObject(obj, j10), n(i13));
                    }
                    break;
                case 10:
                    i13 = i10;
                    if ((i12 & i15) != 0) {
                        a4Var.H(i17, (m) unsafe.getObject(obj, j10));
                    }
                    break;
                case 11:
                    i13 = i10;
                    if ((i12 & i15) != 0) {
                        a4Var.u(i17, unsafe.getInt(obj, j10));
                    }
                    break;
                case 12:
                    i13 = i10;
                    if ((i12 & i15) != 0) {
                        a4Var.J(i17, unsafe.getInt(obj, j10));
                    }
                    break;
                case 13:
                    i13 = i10;
                    if ((i12 & i15) != 0) {
                        a4Var.F(i17, unsafe.getInt(obj, j10));
                    }
                    break;
                case 14:
                    i13 = i10;
                    if ((i12 & i15) != 0) {
                        a4Var.n(i17, unsafe.getLong(obj, j10));
                    }
                    break;
                case 15:
                    i13 = i10;
                    if ((i12 & i15) != 0) {
                        a4Var.L(i17, unsafe.getInt(obj, j10));
                    }
                    break;
                case 16:
                    i13 = i10;
                    if ((i12 & i15) != 0) {
                        a4Var.q(i17, unsafe.getLong(obj, j10));
                    }
                    break;
                case 17:
                    i13 = i10;
                    if ((i12 & i15) != 0) {
                        a4Var.r(i17, unsafe.getObject(obj, j10), n(i13));
                    }
                    break;
                case 18:
                    i13 = i10;
                    q2.B(iArr[i13], (List) unsafe.getObject(obj, j10), a4Var, false);
                    break;
                case 19:
                    i13 = i10;
                    q2.F(iArr[i13], (List) unsafe.getObject(obj, j10), a4Var, false);
                    break;
                case 20:
                    i13 = i10;
                    q2.H(iArr[i13], (List) unsafe.getObject(obj, j10), a4Var, false);
                    break;
                case 21:
                    i13 = i10;
                    q2.N(iArr[i13], (List) unsafe.getObject(obj, j10), a4Var, false);
                    break;
                case 22:
                    i13 = i10;
                    q2.G(iArr[i13], (List) unsafe.getObject(obj, j10), a4Var, false);
                    break;
                case 23:
                    i13 = i10;
                    q2.E(iArr[i13], (List) unsafe.getObject(obj, j10), a4Var, false);
                    break;
                case 24:
                    i13 = i10;
                    q2.D(iArr[i13], (List) unsafe.getObject(obj, j10), a4Var, false);
                    break;
                case 25:
                    i13 = i10;
                    q2.A(iArr[i13], (List) unsafe.getObject(obj, j10), a4Var, false);
                    break;
                case 26:
                    i13 = i10;
                    int i20 = iArr[i13];
                    List list = (List) unsafe.getObject(obj, j10);
                    Class cls = q2.f2507a;
                    if (list != null && !list.isEmpty()) {
                        a4Var.g(i20, list);
                    }
                    break;
                case 27:
                    i13 = i10;
                    int i21 = iArr[i13];
                    List list2 = (List) unsafe.getObject(obj, j10);
                    o2 o2VarN = n(i13);
                    Class cls2 = q2.f2507a;
                    if (list2 != null && !list2.isEmpty()) {
                        a4Var.z(i21, list2, o2VarN);
                    }
                    break;
                case 28:
                    i13 = i10;
                    int i22 = iArr[i13];
                    List list3 = (List) unsafe.getObject(obj, j10);
                    Class cls3 = q2.f2507a;
                    if (list3 != null && !list3.isEmpty()) {
                        a4Var.M(i22, list3);
                    }
                    break;
                case 29:
                    i13 = i10;
                    z = false;
                    q2.M(iArr[i13], (List) unsafe.getObject(obj, j10), a4Var, false);
                    break;
                case 30:
                    i13 = i10;
                    z = false;
                    q2.C(iArr[i13], (List) unsafe.getObject(obj, j10), a4Var, false);
                    break;
                case 31:
                    i13 = i10;
                    z = false;
                    q2.I(iArr[i13], (List) unsafe.getObject(obj, j10), a4Var, false);
                    break;
                case 32:
                    i13 = i10;
                    z = false;
                    q2.J(iArr[i13], (List) unsafe.getObject(obj, j10), a4Var, false);
                    break;
                case 33:
                    i13 = i10;
                    z = false;
                    q2.K(iArr[i13], (List) unsafe.getObject(obj, j10), a4Var, false);
                    break;
                case 34:
                    i13 = i10;
                    z = false;
                    q2.L(iArr[i13], (List) unsafe.getObject(obj, j10), a4Var, false);
                    break;
                case 35:
                    i13 = i10;
                    q2.B(iArr[i13], (List) unsafe.getObject(obj, j10), a4Var, true);
                    break;
                case TsExtractor.TS_STREAM_TYPE_H265 /* 36 */:
                    i13 = i10;
                    q2.F(iArr[i13], (List) unsafe.getObject(obj, j10), a4Var, true);
                    break;
                case 37:
                    i13 = i10;
                    q2.H(iArr[i13], (List) unsafe.getObject(obj, j10), a4Var, true);
                    break;
                case FlacConstants.STREAM_INFO_BLOCK_SIZE /* 38 */:
                    i13 = i10;
                    q2.N(iArr[i13], (List) unsafe.getObject(obj, j10), a4Var, true);
                    break;
                case NalUnitUtil.H265_NAL_UNIT_TYPE_PREFIX_SEI /* 39 */:
                    i13 = i10;
                    q2.G(iArr[i13], (List) unsafe.getObject(obj, j10), a4Var, true);
                    break;
                case 40:
                    i13 = i10;
                    q2.E(iArr[i13], (List) unsafe.getObject(obj, j10), a4Var, true);
                    break;
                case 41:
                    i13 = i10;
                    q2.D(iArr[i13], (List) unsafe.getObject(obj, j10), a4Var, true);
                    break;
                case AacUtil.AUDIO_OBJECT_TYPE_AAC_XHE /* 42 */:
                    i13 = i10;
                    q2.A(iArr[i13], (List) unsafe.getObject(obj, j10), a4Var, true);
                    break;
                case 43:
                    i13 = i10;
                    q2.M(iArr[i13], (List) unsafe.getObject(obj, j10), a4Var, true);
                    break;
                case 44:
                    i13 = i10;
                    q2.C(iArr[i13], (List) unsafe.getObject(obj, j10), a4Var, true);
                    break;
                case TsExtractor.TS_STREAM_TYPE_MHAS /* 45 */:
                    i13 = i10;
                    q2.I(iArr[i13], (List) unsafe.getObject(obj, j10), a4Var, true);
                    break;
                case 46:
                    i13 = i10;
                    q2.J(iArr[i13], (List) unsafe.getObject(obj, j10), a4Var, true);
                    break;
                case 47:
                    i13 = i10;
                    q2.K(iArr[i13], (List) unsafe.getObject(obj, j10), a4Var, true);
                    break;
                case 48:
                    i13 = i10;
                    q2.L(iArr[i13], (List) unsafe.getObject(obj, j10), a4Var, true);
                    break;
                case 49:
                    i13 = i10;
                    int i23 = iArr[i13];
                    List list4 = (List) unsafe.getObject(obj, j10);
                    o2 o2VarN2 = n(i13);
                    Class cls4 = q2.f2507a;
                    if (list4 != null && !list4.isEmpty()) {
                        a4Var.h(i23, list4, o2VarN2);
                    }
                    break;
                case 50:
                    i13 = i10;
                    I(a4Var, i17, unsafe.getObject(obj, j10), i13);
                    break;
                case 51:
                    i13 = i10;
                    if (r(i17, i13, obj)) {
                        a4Var.w(i17, ((Double) p3.f2500d.i(j10, obj)).doubleValue());
                    }
                    break;
                case 52:
                    i13 = i10;
                    if (r(i17, i13, obj)) {
                        a4Var.B(((Float) p3.f2500d.i(j10, obj)).floatValue(), i17);
                    }
                    break;
                case 53:
                    i13 = i10;
                    if (r(i17, i13, obj)) {
                        a4Var.D(i17, A(j10, obj));
                    }
                    break;
                case 54:
                    i13 = i10;
                    if (r(i17, i13, obj)) {
                        a4Var.k(i17, A(j10, obj));
                    }
                    break;
                case 55:
                    i13 = i10;
                    if (r(i17, i13, obj)) {
                        a4Var.m(i17, z(j10, obj));
                    }
                    break;
                case 56:
                    i13 = i10;
                    if (r(i17, i13, obj)) {
                        a4Var.y(i17, A(j10, obj));
                    }
                    break;
                case 57:
                    i13 = i10;
                    if (r(i17, i13, obj)) {
                        a4Var.d(i17, z(j10, obj));
                    }
                    break;
                case 58:
                    i13 = i10;
                    if (r(i17, i13, obj)) {
                        a4Var.E(i17, ((Boolean) p3.f2500d.i(j10, obj)).booleanValue());
                    }
                    break;
                case 59:
                    i13 = i10;
                    if (r(i17, i13, obj)) {
                        J(i17, unsafe.getObject(obj, j10), a4Var);
                    }
                    break;
                case 60:
                    i13 = i10;
                    if (r(i17, i13, obj)) {
                        a4Var.p(i17, unsafe.getObject(obj, j10), n(i13));
                    }
                    break;
                case 61:
                    i13 = i10;
                    if (r(i17, i13, obj)) {
                        a4Var.H(i17, (m) unsafe.getObject(obj, j10));
                    }
                    break;
                case 62:
                    i13 = i10;
                    if (r(i17, i13, obj)) {
                        a4Var.u(i17, z(j10, obj));
                    }
                    break;
                case ColorSpace.MaxId /* 63 */:
                    i13 = i10;
                    if (r(i17, i13, obj)) {
                        a4Var.J(i17, z(j10, obj));
                    }
                    break;
                case 64:
                    i13 = i10;
                    if (r(i17, i13, obj)) {
                        a4Var.F(i17, z(j10, obj));
                    }
                    break;
                case 65:
                    i13 = i10;
                    if (r(i17, i13, obj)) {
                        a4Var.n(i17, A(j10, obj));
                    }
                    break;
                case 66:
                    i13 = i10;
                    if (r(i17, i13, obj)) {
                        a4Var.L(i17, z(j10, obj));
                    }
                    break;
                case MdtaMetadataEntry.TYPE_INDICATOR_INT32 /* 67 */:
                    i13 = i10;
                    if (r(i17, i13, obj)) {
                        a4Var.q(i17, A(j10, obj));
                    }
                    break;
                case 68:
                    i13 = i10;
                    if (r(i17, i13, obj)) {
                        a4Var.r(i17, unsafe.getObject(obj, j10), n(i13));
                    }
                    break;
                default:
                    i13 = i10;
                    break;
            }
        }
        ((h3) this.f2563m).getClass();
        ((r0) obj).unknownFields.d(a4Var);
    }

    public final void I(a4 a4Var, int i10, Object obj, int i11) {
        if (obj != null) {
            Object objM = m(i11);
            q1 q1Var = this.f2564n;
            a4Var.b(i10, q1Var.b(objM), q1Var.d(obj));
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:224:?, code lost:
    
        return;
     */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x0061, code lost:
    
        ((androidx.datastore.preferences.protobuf.r0) r2).unknownFields = (androidx.datastore.preferences.protobuf.g3) r11;
     */
    /* JADX WARN: Removed duplicated region for block: B:170:0x0634 A[Catch: all -> 0x0251, TryCatch #1 {all -> 0x0251, blocks: (B:88:0x0244, B:168:0x062f, B:170:0x0634, B:172:0x063b, B:174:0x0642, B:91:0x0254, B:92:0x0263, B:93:0x0272, B:94:0x0281, B:95:0x0290, B:96:0x02a6, B:97:0x02b5, B:98:0x02c4, B:99:0x02d3, B:100:0x02e2, B:101:0x02f1, B:102:0x0301, B:103:0x0311, B:104:0x0321, B:105:0x0331, B:106:0x0341, B:107:0x0351, B:108:0x0361, B:109:0x0371, B:110:0x0389, B:111:0x0399, B:112:0x03a9, B:113:0x03c0, B:115:0x03cd, B:116:0x03d9, B:117:0x03e5, B:118:0x03f5, B:119:0x0405, B:120:0x0415, B:121:0x0425, B:122:0x0435, B:123:0x0445, B:124:0x0455, B:125:0x0465, B:127:0x046e, B:128:0x048d, B:129:0x04a1, B:130:0x04b4, B:131:0x04c7, B:132:0x04da, B:133:0x04ed, B:135:0x04fa, B:138:0x0501, B:139:0x0507, B:140:0x0513, B:141:0x0526, B:142:0x0539, B:144:0x0542, B:145:0x0561, B:146:0x0575, B:147:0x0580, B:148:0x0595, B:149:0x05a8, B:150:0x05bb, B:151:0x05ce, B:152:0x05e1, B:153:0x05f4, B:154:0x0608, B:160:0x0620), top: B:192:0x0244 }] */
    /* JADX WARN: Removed duplicated region for block: B:202:0x0093 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:205:0x004a A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:207:0x0648 A[SYNTHETIC] */
    @Override // androidx.datastore.preferences.protobuf.o2
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void a(java.lang.Object r18, androidx.datastore.preferences.protobuf.n2 r19, androidx.datastore.preferences.protobuf.d0 r20) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 1806
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.datastore.preferences.protobuf.y1.a(java.lang.Object, androidx.datastore.preferences.protobuf.n2, androidx.datastore.preferences.protobuf.d0):void");
    }

    @Override // androidx.datastore.preferences.protobuf.o2
    public final void b(Object obj) {
        int[] iArr;
        int i10;
        int i11 = this.f2560i;
        while (true) {
            iArr = this.f2559h;
            i10 = this.f2561j;
            if (i11 >= i10) {
                break;
            }
            long jG = G(iArr[i11]) & 1048575;
            Object objI = p3.f2500d.i(jG, obj);
            if (objI != null) {
                p3.o(obj, this.f2564n.e(objI), jG);
            }
            i11++;
        }
        int length = iArr.length;
        while (i10 < length) {
            this.f2562l.a(iArr[i10], obj);
            i10++;
        }
        ((h3) this.f2563m).getClass();
        ((r0) obj).unknownFields.f2444e = false;
    }

    @Override // androidx.datastore.preferences.protobuf.o2
    public final int c(c cVar) {
        return this.f2558g ? p(cVar) : o(cVar);
    }

    /* JADX WARN: Removed duplicated region for block: B:21:0x0049  */
    @Override // androidx.datastore.preferences.protobuf.o2
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final boolean d(java.lang.Object r15) {
        /*
            Method dump skipped, instruction units count: 297
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.datastore.preferences.protobuf.y1.d(java.lang.Object):boolean");
    }

    @Override // androidx.datastore.preferences.protobuf.o2
    public final Object e() {
        return this.k.a(this.f2556e);
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Removed duplicated region for block: B:9:0x001f  */
    @Override // androidx.datastore.preferences.protobuf.o2
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void f(androidx.datastore.preferences.protobuf.r0 r11, androidx.datastore.preferences.protobuf.r0 r12) {
        /*
            Method dump skipped, instruction units count: 588
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.datastore.preferences.protobuf.y1.f(androidx.datastore.preferences.protobuf.r0, androidx.datastore.preferences.protobuf.r0):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:42:0x00e1 A[PHI: r3
      0x00e1: PHI (r3v32 int) = (r3v10 int), (r3v33 int) binds: [B:83:0x0216, B:41:0x00df] A[DONT_GENERATE, DONT_INLINE]] */
    @Override // androidx.datastore.preferences.protobuf.o2
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final int g(androidx.datastore.preferences.protobuf.r0 r12) {
        /*
            Method dump skipped, instruction units count: 796
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.datastore.preferences.protobuf.y1.g(androidx.datastore.preferences.protobuf.r0):int");
    }

    @Override // androidx.datastore.preferences.protobuf.o2
    public final void h(Object obj, a4 a4Var) {
        a4Var.getClass();
        if (!this.f2558g) {
            H(obj, a4Var);
            return;
        }
        int[] iArr = this.f2552a;
        int length = iArr.length;
        for (int i10 = 0; i10 < length; i10 += 3) {
            int iG = G(i10);
            int i11 = iArr[i10];
            switch (F(iG)) {
                case 0:
                    if (q(i10, obj)) {
                        a4Var.w(i11, p3.f2500d.e(iG & 1048575, obj));
                    }
                    break;
                case 1:
                    if (q(i10, obj)) {
                        a4Var.B(p3.f2500d.f(iG & 1048575, obj), i11);
                    }
                    break;
                case 2:
                    if (q(i10, obj)) {
                        a4Var.D(i11, p3.f2500d.h(iG & 1048575, obj));
                    }
                    break;
                case 3:
                    if (q(i10, obj)) {
                        a4Var.k(i11, p3.f2500d.h(iG & 1048575, obj));
                    }
                    break;
                case 4:
                    if (q(i10, obj)) {
                        a4Var.m(i11, p3.f2500d.g(iG & 1048575, obj));
                    }
                    break;
                case 5:
                    if (q(i10, obj)) {
                        a4Var.y(i11, p3.f2500d.h(iG & 1048575, obj));
                    }
                    break;
                case 6:
                    if (q(i10, obj)) {
                        a4Var.d(i11, p3.f2500d.g(iG & 1048575, obj));
                    }
                    break;
                case 7:
                    if (q(i10, obj)) {
                        a4Var.E(i11, p3.f2500d.c(iG & 1048575, obj));
                    }
                    break;
                case 8:
                    if (q(i10, obj)) {
                        J(i11, p3.f2500d.i(iG & 1048575, obj), a4Var);
                    }
                    break;
                case 9:
                    if (q(i10, obj)) {
                        a4Var.p(i11, p3.f2500d.i(iG & 1048575, obj), n(i10));
                    }
                    break;
                case 10:
                    if (q(i10, obj)) {
                        a4Var.H(i11, (m) p3.f2500d.i(iG & 1048575, obj));
                    }
                    break;
                case 11:
                    if (q(i10, obj)) {
                        a4Var.u(i11, p3.f2500d.g(iG & 1048575, obj));
                    }
                    break;
                case 12:
                    if (q(i10, obj)) {
                        a4Var.J(i11, p3.f2500d.g(iG & 1048575, obj));
                    }
                    break;
                case 13:
                    if (q(i10, obj)) {
                        a4Var.F(i11, p3.f2500d.g(iG & 1048575, obj));
                    }
                    break;
                case 14:
                    if (q(i10, obj)) {
                        a4Var.n(i11, p3.f2500d.h(iG & 1048575, obj));
                    }
                    break;
                case 15:
                    if (q(i10, obj)) {
                        a4Var.L(i11, p3.f2500d.g(iG & 1048575, obj));
                    }
                    break;
                case 16:
                    if (q(i10, obj)) {
                        a4Var.q(i11, p3.f2500d.h(iG & 1048575, obj));
                    }
                    break;
                case 17:
                    if (q(i10, obj)) {
                        a4Var.r(i11, p3.f2500d.i(iG & 1048575, obj), n(i10));
                    }
                    break;
                case 18:
                    q2.B(iArr[i10], (List) p3.f2500d.i(iG & 1048575, obj), a4Var, false);
                    break;
                case 19:
                    q2.F(iArr[i10], (List) p3.f2500d.i(iG & 1048575, obj), a4Var, false);
                    break;
                case 20:
                    q2.H(iArr[i10], (List) p3.f2500d.i(iG & 1048575, obj), a4Var, false);
                    break;
                case 21:
                    q2.N(iArr[i10], (List) p3.f2500d.i(iG & 1048575, obj), a4Var, false);
                    break;
                case 22:
                    q2.G(iArr[i10], (List) p3.f2500d.i(iG & 1048575, obj), a4Var, false);
                    break;
                case 23:
                    q2.E(iArr[i10], (List) p3.f2500d.i(iG & 1048575, obj), a4Var, false);
                    break;
                case 24:
                    q2.D(iArr[i10], (List) p3.f2500d.i(iG & 1048575, obj), a4Var, false);
                    break;
                case 25:
                    q2.A(iArr[i10], (List) p3.f2500d.i(iG & 1048575, obj), a4Var, false);
                    break;
                case 26:
                    int i12 = iArr[i10];
                    List list = (List) p3.f2500d.i(iG & 1048575, obj);
                    Class cls = q2.f2507a;
                    if (list != null && !list.isEmpty()) {
                        a4Var.g(i12, list);
                    }
                    break;
                case 27:
                    int i13 = iArr[i10];
                    List list2 = (List) p3.f2500d.i(iG & 1048575, obj);
                    o2 o2VarN = n(i10);
                    Class cls2 = q2.f2507a;
                    if (list2 != null && !list2.isEmpty()) {
                        a4Var.z(i13, list2, o2VarN);
                    }
                    break;
                case 28:
                    int i14 = iArr[i10];
                    List list3 = (List) p3.f2500d.i(iG & 1048575, obj);
                    Class cls3 = q2.f2507a;
                    if (list3 != null && !list3.isEmpty()) {
                        a4Var.M(i14, list3);
                    }
                    break;
                case 29:
                    q2.M(iArr[i10], (List) p3.f2500d.i(iG & 1048575, obj), a4Var, false);
                    break;
                case 30:
                    q2.C(iArr[i10], (List) p3.f2500d.i(iG & 1048575, obj), a4Var, false);
                    break;
                case 31:
                    q2.I(iArr[i10], (List) p3.f2500d.i(iG & 1048575, obj), a4Var, false);
                    break;
                case 32:
                    q2.J(iArr[i10], (List) p3.f2500d.i(iG & 1048575, obj), a4Var, false);
                    break;
                case 33:
                    q2.K(iArr[i10], (List) p3.f2500d.i(iG & 1048575, obj), a4Var, false);
                    break;
                case 34:
                    q2.L(iArr[i10], (List) p3.f2500d.i(iG & 1048575, obj), a4Var, false);
                    break;
                case 35:
                    q2.B(iArr[i10], (List) p3.f2500d.i(iG & 1048575, obj), a4Var, true);
                    break;
                case TsExtractor.TS_STREAM_TYPE_H265 /* 36 */:
                    q2.F(iArr[i10], (List) p3.f2500d.i(iG & 1048575, obj), a4Var, true);
                    break;
                case 37:
                    q2.H(iArr[i10], (List) p3.f2500d.i(iG & 1048575, obj), a4Var, true);
                    break;
                case FlacConstants.STREAM_INFO_BLOCK_SIZE /* 38 */:
                    q2.N(iArr[i10], (List) p3.f2500d.i(iG & 1048575, obj), a4Var, true);
                    break;
                case NalUnitUtil.H265_NAL_UNIT_TYPE_PREFIX_SEI /* 39 */:
                    q2.G(iArr[i10], (List) p3.f2500d.i(iG & 1048575, obj), a4Var, true);
                    break;
                case 40:
                    q2.E(iArr[i10], (List) p3.f2500d.i(iG & 1048575, obj), a4Var, true);
                    break;
                case 41:
                    q2.D(iArr[i10], (List) p3.f2500d.i(iG & 1048575, obj), a4Var, true);
                    break;
                case AacUtil.AUDIO_OBJECT_TYPE_AAC_XHE /* 42 */:
                    q2.A(iArr[i10], (List) p3.f2500d.i(iG & 1048575, obj), a4Var, true);
                    break;
                case 43:
                    q2.M(iArr[i10], (List) p3.f2500d.i(iG & 1048575, obj), a4Var, true);
                    break;
                case 44:
                    q2.C(iArr[i10], (List) p3.f2500d.i(iG & 1048575, obj), a4Var, true);
                    break;
                case TsExtractor.TS_STREAM_TYPE_MHAS /* 45 */:
                    q2.I(iArr[i10], (List) p3.f2500d.i(iG & 1048575, obj), a4Var, true);
                    break;
                case 46:
                    q2.J(iArr[i10], (List) p3.f2500d.i(iG & 1048575, obj), a4Var, true);
                    break;
                case 47:
                    q2.K(iArr[i10], (List) p3.f2500d.i(iG & 1048575, obj), a4Var, true);
                    break;
                case 48:
                    q2.L(iArr[i10], (List) p3.f2500d.i(iG & 1048575, obj), a4Var, true);
                    break;
                case 49:
                    int i15 = iArr[i10];
                    List list4 = (List) p3.f2500d.i(iG & 1048575, obj);
                    o2 o2VarN2 = n(i10);
                    Class cls4 = q2.f2507a;
                    if (list4 != null && !list4.isEmpty()) {
                        a4Var.h(i15, list4, o2VarN2);
                    }
                    break;
                case 50:
                    I(a4Var, i11, p3.f2500d.i(iG & 1048575, obj), i10);
                    break;
                case 51:
                    if (r(i11, i10, obj)) {
                        a4Var.w(i11, ((Double) p3.f2500d.i(iG & 1048575, obj)).doubleValue());
                    }
                    break;
                case 52:
                    if (r(i11, i10, obj)) {
                        a4Var.B(((Float) p3.f2500d.i(iG & 1048575, obj)).floatValue(), i11);
                    }
                    break;
                case 53:
                    if (r(i11, i10, obj)) {
                        a4Var.D(i11, A(iG & 1048575, obj));
                    }
                    break;
                case 54:
                    if (r(i11, i10, obj)) {
                        a4Var.k(i11, A(iG & 1048575, obj));
                    }
                    break;
                case 55:
                    if (r(i11, i10, obj)) {
                        a4Var.m(i11, z(iG & 1048575, obj));
                    }
                    break;
                case 56:
                    if (r(i11, i10, obj)) {
                        a4Var.y(i11, A(iG & 1048575, obj));
                    }
                    break;
                case 57:
                    if (r(i11, i10, obj)) {
                        a4Var.d(i11, z(iG & 1048575, obj));
                    }
                    break;
                case 58:
                    if (r(i11, i10, obj)) {
                        a4Var.E(i11, ((Boolean) p3.f2500d.i(iG & 1048575, obj)).booleanValue());
                    }
                    break;
                case 59:
                    if (r(i11, i10, obj)) {
                        J(i11, p3.f2500d.i(iG & 1048575, obj), a4Var);
                    }
                    break;
                case 60:
                    if (r(i11, i10, obj)) {
                        a4Var.p(i11, p3.f2500d.i(iG & 1048575, obj), n(i10));
                    }
                    break;
                case 61:
                    if (r(i11, i10, obj)) {
                        a4Var.H(i11, (m) p3.f2500d.i(iG & 1048575, obj));
                    }
                    break;
                case 62:
                    if (r(i11, i10, obj)) {
                        a4Var.u(i11, z(iG & 1048575, obj));
                    }
                    break;
                case ColorSpace.MaxId /* 63 */:
                    if (r(i11, i10, obj)) {
                        a4Var.J(i11, z(iG & 1048575, obj));
                    }
                    break;
                case 64:
                    if (r(i11, i10, obj)) {
                        a4Var.F(i11, z(iG & 1048575, obj));
                    }
                    break;
                case 65:
                    if (r(i11, i10, obj)) {
                        a4Var.n(i11, A(iG & 1048575, obj));
                    }
                    break;
                case 66:
                    if (r(i11, i10, obj)) {
                        a4Var.L(i11, z(iG & 1048575, obj));
                    }
                    break;
                case MdtaMetadataEntry.TYPE_INDICATOR_INT32 /* 67 */:
                    if (r(i11, i10, obj)) {
                        a4Var.q(i11, A(iG & 1048575, obj));
                    }
                    break;
                case 68:
                    if (r(i11, i10, obj)) {
                        a4Var.r(i11, p3.f2500d.i(iG & 1048575, obj), n(i10));
                    }
                    break;
            }
        }
        ((h3) this.f2563m).getClass();
        ((r0) obj).unknownFields.d(a4Var);
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x003d  */
    @Override // androidx.datastore.preferences.protobuf.o2
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final boolean i(androidx.datastore.preferences.protobuf.r0 r12, androidx.datastore.preferences.protobuf.r0 r13) {
        /*
            Method dump skipped, instruction units count: 666
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.datastore.preferences.protobuf.y1.i(androidx.datastore.preferences.protobuf.r0, androidx.datastore.preferences.protobuf.r0):boolean");
    }

    public final boolean j(r0 r0Var, Object obj, int i10) {
        return q(i10, r0Var) == q(i10, obj);
    }

    public final Object k(Object obj, int i10, Object obj2, f3 f3Var) {
        y0 y0VarL;
        int i11 = this.f2552a[i10];
        Object objI = p3.f2500d.i(G(i10) & 1048575, obj);
        if (objI == null || (y0VarL = l(i10)) == null) {
            return obj2;
        }
        q1 q1Var = this.f2564n;
        p1 p1VarC = q1Var.c(objI);
        androidx.appcompat.app.i1 i1VarB = q1Var.b(m(i10));
        Iterator it = p1VarC.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            ((Integer) entry.getValue()).getClass();
            if (!y0VarL.a()) {
                if (obj2 == null) {
                    ((h3) f3Var).getClass();
                    obj2 = g3.b();
                }
                int iA = o1.a(i1VarB, entry.getKey(), entry.getValue());
                byte[] bArr = new byte[iA];
                Logger logger = u.f2533d;
                s sVar = new s(bArr, iA);
                try {
                    Object key = entry.getKey();
                    Object value = entry.getValue();
                    k0.e(sVar, (y3) i1VarB.f1059l, 1, key);
                    k0.e(sVar, (y3) i1VarB.f1060m, 2, value);
                    if (iA - sVar.f2525h != 0) {
                        throw new IllegalStateException("Did not write as much data as expected.");
                    }
                    m mVar = new m(bArr);
                    ((h3) f3Var).getClass();
                    ((g3) obj2).c((i11 << 3) | 2, mVar);
                    it.remove();
                } catch (IOException e5) {
                    throw new RuntimeException(e5);
                }
            }
        }
        return obj2;
    }

    public final y0 l(int i10) {
        return (y0) this.f2553b[((i10 / 3) * 2) + 1];
    }

    public final Object m(int i10) {
        return this.f2553b[(i10 / 3) * 2];
    }

    public final o2 n(int i10) {
        int i11 = (i10 / 3) * 2;
        Object[] objArr = this.f2553b;
        o2 o2Var = (o2) objArr[i11];
        if (o2Var != null) {
            return o2Var;
        }
        o2 o2VarA = j2.f2452c.a((Class) objArr[i11 + 1]);
        objArr[i11] = o2VarA;
        return o2VarA;
    }

    public final int o(Object obj) {
        int i10;
        int iT;
        int iW;
        int iT2;
        int iR;
        int iP;
        int iT3;
        int iS;
        int iB;
        int iT4;
        int iQ;
        Unsafe unsafe = f2551p;
        int i11 = -1;
        int i12 = 0;
        int iA = 0;
        int i13 = 0;
        while (true) {
            int[] iArr = this.f2552a;
            if (i12 >= iArr.length) {
                ((h3) this.f2563m).getClass();
                return ((r0) obj).unknownFields.a() + iA;
            }
            int iG = G(i12);
            int i14 = iArr[i12];
            int iF = F(iG);
            if (iF <= 17) {
                int i15 = iArr[i12 + 2];
                int i16 = i15 & 1048575;
                i10 = 1 << (i15 >>> 20);
                if (i16 != i11) {
                    i13 = unsafe.getInt(obj, i16);
                    i11 = i16;
                }
            } else {
                i10 = 0;
            }
            long j10 = iG & 1048575;
            switch (iF) {
                case 0:
                    if ((i10 & i13) != 0) {
                        iA = androidx.compose.material3.d.a(i14, 8, iA);
                    }
                    break;
                case 1:
                    if ((i13 & i10) != 0) {
                        iA = androidx.compose.material3.d.a(i14, 4, iA);
                    }
                    break;
                case 2:
                    if ((i13 & i10) != 0) {
                        long j11 = unsafe.getLong(obj, j10);
                        iT = u.t(i14);
                        iW = u.w(j11);
                        iT4 = iW + iT;
                        iA += iT4;
                    }
                    break;
                case 3:
                    if ((i13 & i10) != 0) {
                        long j12 = unsafe.getLong(obj, j10);
                        iT = u.t(i14);
                        iW = u.w(j12);
                        iT4 = iW + iT;
                        iA += iT4;
                    }
                    break;
                case 4:
                    if ((i13 & i10) != 0) {
                        int i17 = unsafe.getInt(obj, j10);
                        iT2 = u.t(i14);
                        iR = u.r(i17);
                        iP = iR + iT2;
                        iA += iP;
                    }
                    break;
                case 5:
                    if ((i13 & i10) != 0) {
                        iP = u.p(i14);
                        iA += iP;
                    }
                    break;
                case 6:
                    if ((i13 & i10) != 0) {
                        iP = u.o(i14);
                        iA += iP;
                    }
                    break;
                case 7:
                    if ((i13 & i10) != 0) {
                        iA = androidx.compose.material3.d.a(i14, 1, iA);
                    }
                    break;
                case 8:
                    if ((i13 & i10) != 0) {
                        Object object = unsafe.getObject(obj, j10);
                        if (object instanceof m) {
                            int iT5 = u.t(i14);
                            int size = ((m) object).size();
                            iB = androidx.compose.material3.d.b(size, size, iT5, iA);
                            iA = iB;
                        } else {
                            iT3 = u.t(i14);
                            iS = u.s((String) object);
                            iB = iS + iT3 + iA;
                            iA = iB;
                        }
                    }
                    break;
                case 9:
                    if ((i13 & i10) != 0) {
                        Object object2 = unsafe.getObject(obj, j10);
                        o2 o2VarN = n(i12);
                        Class cls = q2.f2507a;
                        int iT6 = u.t(i14);
                        int iG2 = ((c) ((v1) object2)).g(o2VarN);
                        iA = androidx.compose.material3.d.b(iG2, iG2, iT6, iA);
                    }
                    break;
                case 10:
                    if ((i13 & i10) != 0) {
                        iP = u.m(i14, (m) unsafe.getObject(obj, j10));
                        iA += iP;
                    }
                    break;
                case 11:
                    if ((i13 & i10) != 0) {
                        iP = u.u(i14, unsafe.getInt(obj, j10));
                        iA += iP;
                    }
                    break;
                case 12:
                    if ((i13 & i10) != 0) {
                        int i18 = unsafe.getInt(obj, j10);
                        iT2 = u.t(i14);
                        iR = u.r(i18);
                        iP = iR + iT2;
                        iA += iP;
                    }
                    break;
                case 13:
                    if ((i13 & i10) != 0) {
                        iA = androidx.compose.material3.d.a(i14, 4, iA);
                    }
                    break;
                case 14:
                    if ((i10 & i13) != 0) {
                        iA = androidx.compose.material3.d.a(i14, 8, iA);
                    }
                    break;
                case 15:
                    if ((i13 & i10) != 0) {
                        int i19 = unsafe.getInt(obj, j10);
                        iT2 = u.t(i14);
                        iR = u.v((i19 >> 31) ^ (i19 << 1));
                        iP = iR + iT2;
                        iA += iP;
                    }
                    break;
                case 16:
                    if ((i13 & i10) != 0) {
                        long j13 = unsafe.getLong(obj, j10);
                        iT = u.t(i14);
                        iW = u.w((j13 >> 63) ^ (j13 << 1));
                        iT4 = iW + iT;
                        iA += iT4;
                    }
                    break;
                case 17:
                    if ((i13 & i10) != 0) {
                        iP = u.q(i14, (v1) unsafe.getObject(obj, j10), n(i12));
                        iA += iP;
                    }
                    break;
                case 18:
                    iP = q2.f(i14, (List) unsafe.getObject(obj, j10));
                    iA += iP;
                    break;
                case 19:
                    iP = q2.d(i14, (List) unsafe.getObject(obj, j10));
                    iA += iP;
                    break;
                case 20:
                    iP = q2.j(i14, (List) unsafe.getObject(obj, j10));
                    iA += iP;
                    break;
                case 21:
                    iP = q2.t(i14, (List) unsafe.getObject(obj, j10));
                    iA += iP;
                    break;
                case 22:
                    iP = q2.h(i14, (List) unsafe.getObject(obj, j10));
                    iA += iP;
                    break;
                case 23:
                    iP = q2.f(i14, (List) unsafe.getObject(obj, j10));
                    iA += iP;
                    break;
                case 24:
                    iP = q2.d(i14, (List) unsafe.getObject(obj, j10));
                    iA += iP;
                    break;
                case 25:
                    List list = (List) unsafe.getObject(obj, j10);
                    Class cls2 = q2.f2507a;
                    int size2 = list.size();
                    iT4 = size2 == 0 ? 0 : (u.t(i14) + 1) * size2;
                    iA += iT4;
                    break;
                case 26:
                    iP = q2.q(i14, (List) unsafe.getObject(obj, j10));
                    iA += iP;
                    break;
                case 27:
                    iP = q2.l(i14, (List) unsafe.getObject(obj, j10), n(i12));
                    iA += iP;
                    break;
                case 28:
                    iP = q2.a(i14, (List) unsafe.getObject(obj, j10));
                    iA += iP;
                    break;
                case 29:
                    iP = q2.r(i14, (List) unsafe.getObject(obj, j10));
                    iA += iP;
                    break;
                case 30:
                    iP = q2.b(i14, (List) unsafe.getObject(obj, j10));
                    iA += iP;
                    break;
                case 31:
                    iP = q2.d(i14, (List) unsafe.getObject(obj, j10));
                    iA += iP;
                    break;
                case 32:
                    iP = q2.f(i14, (List) unsafe.getObject(obj, j10));
                    iA += iP;
                    break;
                case 33:
                    iP = q2.m(i14, (List) unsafe.getObject(obj, j10));
                    iA += iP;
                    break;
                case 34:
                    iP = q2.o(i14, (List) unsafe.getObject(obj, j10));
                    iA += iP;
                    break;
                case 35:
                    int iG3 = q2.g((List) unsafe.getObject(obj, j10));
                    if (iG3 > 0) {
                        iA = androidx.compose.material3.d.b(iG3, u.t(i14), iG3, iA);
                    }
                    break;
                case TsExtractor.TS_STREAM_TYPE_H265 /* 36 */:
                    int iE = q2.e((List) unsafe.getObject(obj, j10));
                    if (iE > 0) {
                        iA = androidx.compose.material3.d.b(iE, u.t(i14), iE, iA);
                    }
                    break;
                case 37:
                    int iK = q2.k((List) unsafe.getObject(obj, j10));
                    if (iK > 0) {
                        iA = androidx.compose.material3.d.b(iK, u.t(i14), iK, iA);
                    }
                    break;
                case FlacConstants.STREAM_INFO_BLOCK_SIZE /* 38 */:
                    int iU = q2.u((List) unsafe.getObject(obj, j10));
                    if (iU > 0) {
                        iA = androidx.compose.material3.d.b(iU, u.t(i14), iU, iA);
                    }
                    break;
                case NalUnitUtil.H265_NAL_UNIT_TYPE_PREFIX_SEI /* 39 */:
                    int i20 = q2.i((List) unsafe.getObject(obj, j10));
                    if (i20 > 0) {
                        iA = androidx.compose.material3.d.b(i20, u.t(i14), i20, iA);
                    }
                    break;
                case 40:
                    int iG4 = q2.g((List) unsafe.getObject(obj, j10));
                    if (iG4 > 0) {
                        iA = androidx.compose.material3.d.b(iG4, u.t(i14), iG4, iA);
                    }
                    break;
                case 41:
                    int iE2 = q2.e((List) unsafe.getObject(obj, j10));
                    if (iE2 > 0) {
                        iA = androidx.compose.material3.d.b(iE2, u.t(i14), iE2, iA);
                    }
                    break;
                case AacUtil.AUDIO_OBJECT_TYPE_AAC_XHE /* 42 */:
                    List list2 = (List) unsafe.getObject(obj, j10);
                    Class cls3 = q2.f2507a;
                    int size3 = list2.size();
                    if (size3 > 0) {
                        iA = androidx.compose.material3.d.b(size3, u.t(i14), size3, iA);
                    }
                    break;
                case 43:
                    int iS2 = q2.s((List) unsafe.getObject(obj, j10));
                    if (iS2 > 0) {
                        iA = androidx.compose.material3.d.b(iS2, u.t(i14), iS2, iA);
                    }
                    break;
                case 44:
                    int iC = q2.c((List) unsafe.getObject(obj, j10));
                    if (iC > 0) {
                        iA = androidx.compose.material3.d.b(iC, u.t(i14), iC, iA);
                    }
                    break;
                case TsExtractor.TS_STREAM_TYPE_MHAS /* 45 */:
                    int iE3 = q2.e((List) unsafe.getObject(obj, j10));
                    if (iE3 > 0) {
                        iA = androidx.compose.material3.d.b(iE3, u.t(i14), iE3, iA);
                    }
                    break;
                case 46:
                    int iG5 = q2.g((List) unsafe.getObject(obj, j10));
                    if (iG5 > 0) {
                        iA = androidx.compose.material3.d.b(iG5, u.t(i14), iG5, iA);
                    }
                    break;
                case 47:
                    int iN = q2.n((List) unsafe.getObject(obj, j10));
                    if (iN > 0) {
                        iA = androidx.compose.material3.d.b(iN, u.t(i14), iN, iA);
                    }
                    break;
                case 48:
                    int iP2 = q2.p((List) unsafe.getObject(obj, j10));
                    if (iP2 > 0) {
                        iA = androidx.compose.material3.d.b(iP2, u.t(i14), iP2, iA);
                    }
                    break;
                case 49:
                    List list3 = (List) unsafe.getObject(obj, j10);
                    o2 o2VarN2 = n(i12);
                    Class cls4 = q2.f2507a;
                    int size4 = list3.size();
                    if (size4 == 0) {
                        iQ = 0;
                    } else {
                        iQ = 0;
                        for (int i21 = 0; i21 < size4; i21++) {
                            iQ += u.q(i14, (v1) list3.get(i21), o2VarN2);
                        }
                    }
                    iA += iQ;
                    break;
                case 50:
                    iA += this.f2564n.g(i14, unsafe.getObject(obj, j10), m(i12));
                    break;
                case 51:
                    if (r(i14, i12, obj)) {
                        iA = androidx.compose.material3.d.a(i14, 8, iA);
                    }
                    break;
                case 52:
                    if (r(i14, i12, obj)) {
                        iA = androidx.compose.material3.d.a(i14, 4, iA);
                    }
                    break;
                case 53:
                    if (r(i14, i12, obj)) {
                        long jA = A(j10, obj);
                        iT = u.t(i14);
                        iW = u.w(jA);
                        iT4 = iW + iT;
                        iA += iT4;
                    }
                    break;
                case 54:
                    if (r(i14, i12, obj)) {
                        long jA2 = A(j10, obj);
                        iT = u.t(i14);
                        iW = u.w(jA2);
                        iT4 = iW + iT;
                        iA += iT4;
                    }
                    break;
                case 55:
                    if (r(i14, i12, obj)) {
                        int iZ = z(j10, obj);
                        iT2 = u.t(i14);
                        iR = u.r(iZ);
                        iP = iR + iT2;
                        iA += iP;
                    }
                    break;
                case 56:
                    if (r(i14, i12, obj)) {
                        iP = u.p(i14);
                        iA += iP;
                    }
                    break;
                case 57:
                    if (r(i14, i12, obj)) {
                        iP = u.o(i14);
                        iA += iP;
                    }
                    break;
                case 58:
                    if (r(i14, i12, obj)) {
                        iA = androidx.compose.material3.d.a(i14, 1, iA);
                    }
                    break;
                case 59:
                    if (r(i14, i12, obj)) {
                        Object object3 = unsafe.getObject(obj, j10);
                        if (object3 instanceof m) {
                            int iT7 = u.t(i14);
                            int size5 = ((m) object3).size();
                            iB = androidx.compose.material3.d.b(size5, size5, iT7, iA);
                            iA = iB;
                        } else {
                            iT3 = u.t(i14);
                            iS = u.s((String) object3);
                            iB = iS + iT3 + iA;
                            iA = iB;
                        }
                    }
                    break;
                case 60:
                    if (r(i14, i12, obj)) {
                        Object object4 = unsafe.getObject(obj, j10);
                        o2 o2VarN3 = n(i12);
                        Class cls5 = q2.f2507a;
                        int iT8 = u.t(i14);
                        int iG6 = ((c) ((v1) object4)).g(o2VarN3);
                        iA = androidx.compose.material3.d.b(iG6, iG6, iT8, iA);
                    }
                    break;
                case 61:
                    if (r(i14, i12, obj)) {
                        iP = u.m(i14, (m) unsafe.getObject(obj, j10));
                        iA += iP;
                    }
                    break;
                case 62:
                    if (r(i14, i12, obj)) {
                        iP = u.u(i14, z(j10, obj));
                        iA += iP;
                    }
                    break;
                case ColorSpace.MaxId /* 63 */:
                    if (r(i14, i12, obj)) {
                        int iZ2 = z(j10, obj);
                        iT2 = u.t(i14);
                        iR = u.r(iZ2);
                        iP = iR + iT2;
                        iA += iP;
                    }
                    break;
                case 64:
                    if (r(i14, i12, obj)) {
                        iA = androidx.compose.material3.d.a(i14, 4, iA);
                    }
                    break;
                case 65:
                    if (r(i14, i12, obj)) {
                        iA = androidx.compose.material3.d.a(i14, 8, iA);
                    }
                    break;
                case 66:
                    if (r(i14, i12, obj)) {
                        int iZ3 = z(j10, obj);
                        iT2 = u.t(i14);
                        iR = u.v((iZ3 >> 31) ^ (iZ3 << 1));
                        iP = iR + iT2;
                        iA += iP;
                    }
                    break;
                case MdtaMetadataEntry.TYPE_INDICATOR_INT32 /* 67 */:
                    if (r(i14, i12, obj)) {
                        long jA3 = A(j10, obj);
                        iT = u.t(i14);
                        iW = u.w((jA3 >> 63) ^ (jA3 << 1));
                        iT4 = iW + iT;
                        iA += iT4;
                    }
                    break;
                case 68:
                    if (r(i14, i12, obj)) {
                        iP = u.q(i14, (v1) unsafe.getObject(obj, j10), n(i12));
                        iA += iP;
                    }
                    break;
            }
            i12 += 3;
        }
    }

    public final int p(Object obj) {
        int iT;
        int iW;
        int iT2;
        int iR;
        int iP;
        int iT3;
        int iS;
        int iT4;
        int iW2;
        int iQ;
        Unsafe unsafe = f2551p;
        int i10 = 0;
        int iA = 0;
        while (true) {
            int[] iArr = this.f2552a;
            if (i10 >= iArr.length) {
                ((h3) this.f2563m).getClass();
                return ((r0) obj).unknownFields.a() + iA;
            }
            int iG = G(i10);
            int iF = F(iG);
            int i11 = iArr[i10];
            long j10 = iG & 1048575;
            if (iF >= l0.f2462l.f2467i && iF <= l0.f2463m.f2467i) {
                int i12 = iArr[i10 + 2];
            }
            switch (iF) {
                case 0:
                    if (q(i10, obj)) {
                        iA = androidx.compose.material3.d.a(i11, 8, iA);
                    }
                    break;
                case 1:
                    if (q(i10, obj)) {
                        iA = androidx.compose.material3.d.a(i11, 4, iA);
                    }
                    break;
                case 2:
                    if (q(i10, obj)) {
                        long jH = p3.f2500d.h(j10, obj);
                        iT = u.t(i11);
                        iW = u.w(jH);
                        iP = iW + iT;
                        iA += iP;
                    }
                    break;
                case 3:
                    if (q(i10, obj)) {
                        long jH2 = p3.f2500d.h(j10, obj);
                        iT = u.t(i11);
                        iW = u.w(jH2);
                        iP = iW + iT;
                        iA += iP;
                    }
                    break;
                case 4:
                    if (q(i10, obj)) {
                        int iG2 = p3.f2500d.g(j10, obj);
                        iT2 = u.t(i11);
                        iR = u.r(iG2);
                        iP = iR + iT2;
                        iA += iP;
                    }
                    break;
                case 5:
                    if (q(i10, obj)) {
                        iP = u.p(i11);
                        iA += iP;
                    }
                    break;
                case 6:
                    if (q(i10, obj)) {
                        iP = u.o(i11);
                        iA += iP;
                    }
                    break;
                case 7:
                    if (q(i10, obj)) {
                        iA = androidx.compose.material3.d.a(i11, 1, iA);
                    }
                    break;
                case 8:
                    if (q(i10, obj)) {
                        Object objI = p3.f2500d.i(j10, obj);
                        if (objI instanceof m) {
                            int iT5 = u.t(i11);
                            int size = ((m) objI).size();
                            iA = androidx.compose.material3.d.b(size, size, iT5, iA);
                        } else {
                            iT3 = u.t(i11);
                            iS = u.s((String) objI);
                            iA = iS + iT3 + iA;
                        }
                    }
                    break;
                case 9:
                    if (q(i10, obj)) {
                        Object objI2 = p3.f2500d.i(j10, obj);
                        o2 o2VarN = n(i10);
                        Class cls = q2.f2507a;
                        int iT6 = u.t(i11);
                        int iG3 = ((c) ((v1) objI2)).g(o2VarN);
                        iA = androidx.compose.material3.d.b(iG3, iG3, iT6, iA);
                    }
                    break;
                case 10:
                    if (q(i10, obj)) {
                        iP = u.m(i11, (m) p3.f2500d.i(j10, obj));
                        iA += iP;
                    }
                    break;
                case 11:
                    if (q(i10, obj)) {
                        iP = u.u(i11, p3.f2500d.g(j10, obj));
                        iA += iP;
                    }
                    break;
                case 12:
                    if (q(i10, obj)) {
                        int iG4 = p3.f2500d.g(j10, obj);
                        iT2 = u.t(i11);
                        iR = u.r(iG4);
                        iP = iR + iT2;
                        iA += iP;
                    }
                    break;
                case 13:
                    if (q(i10, obj)) {
                        iA = androidx.compose.material3.d.a(i11, 4, iA);
                    }
                    break;
                case 14:
                    if (q(i10, obj)) {
                        iA = androidx.compose.material3.d.a(i11, 8, iA);
                    }
                    break;
                case 15:
                    if (q(i10, obj)) {
                        int iG5 = p3.f2500d.g(j10, obj);
                        iT2 = u.t(i11);
                        iR = u.v((iG5 >> 31) ^ (iG5 << 1));
                        iP = iR + iT2;
                        iA += iP;
                    }
                    break;
                case 16:
                    if (q(i10, obj)) {
                        long jH3 = p3.f2500d.h(j10, obj);
                        iT4 = u.t(i11);
                        iW2 = u.w((jH3 >> 63) ^ (jH3 << 1));
                        iP = iW2 + iT4;
                        iA += iP;
                    }
                    break;
                case 17:
                    if (q(i10, obj)) {
                        iP = u.q(i11, (v1) p3.f2500d.i(j10, obj), n(i10));
                        iA += iP;
                    }
                    break;
                case 18:
                    iP = q2.f(i11, s(j10, obj));
                    iA += iP;
                    break;
                case 19:
                    iP = q2.d(i11, s(j10, obj));
                    iA += iP;
                    break;
                case 20:
                    iP = q2.j(i11, s(j10, obj));
                    iA += iP;
                    break;
                case 21:
                    iP = q2.t(i11, s(j10, obj));
                    iA += iP;
                    break;
                case 22:
                    iP = q2.h(i11, s(j10, obj));
                    iA += iP;
                    break;
                case 23:
                    iP = q2.f(i11, s(j10, obj));
                    iA += iP;
                    break;
                case 24:
                    iP = q2.d(i11, s(j10, obj));
                    iA += iP;
                    break;
                case 25:
                    List listS = s(j10, obj);
                    Class cls2 = q2.f2507a;
                    int size2 = listS.size();
                    iA += size2 == 0 ? 0 : (u.t(i11) + 1) * size2;
                    break;
                case 26:
                    iP = q2.q(i11, s(j10, obj));
                    iA += iP;
                    break;
                case 27:
                    iP = q2.l(i11, s(j10, obj), n(i10));
                    iA += iP;
                    break;
                case 28:
                    iP = q2.a(i11, s(j10, obj));
                    iA += iP;
                    break;
                case 29:
                    iP = q2.r(i11, s(j10, obj));
                    iA += iP;
                    break;
                case 30:
                    iP = q2.b(i11, s(j10, obj));
                    iA += iP;
                    break;
                case 31:
                    iP = q2.d(i11, s(j10, obj));
                    iA += iP;
                    break;
                case 32:
                    iP = q2.f(i11, s(j10, obj));
                    iA += iP;
                    break;
                case 33:
                    iP = q2.m(i11, s(j10, obj));
                    iA += iP;
                    break;
                case 34:
                    iP = q2.o(i11, s(j10, obj));
                    iA += iP;
                    break;
                case 35:
                    int iG6 = q2.g((List) unsafe.getObject(obj, j10));
                    if (iG6 > 0) {
                        iA = androidx.compose.material3.d.b(iG6, u.t(i11), iG6, iA);
                    }
                    break;
                case TsExtractor.TS_STREAM_TYPE_H265 /* 36 */:
                    int iE = q2.e((List) unsafe.getObject(obj, j10));
                    if (iE > 0) {
                        iA = androidx.compose.material3.d.b(iE, u.t(i11), iE, iA);
                    }
                    break;
                case 37:
                    int iK = q2.k((List) unsafe.getObject(obj, j10));
                    if (iK > 0) {
                        iA = androidx.compose.material3.d.b(iK, u.t(i11), iK, iA);
                    }
                    break;
                case FlacConstants.STREAM_INFO_BLOCK_SIZE /* 38 */:
                    int iU = q2.u((List) unsafe.getObject(obj, j10));
                    if (iU > 0) {
                        iA = androidx.compose.material3.d.b(iU, u.t(i11), iU, iA);
                    }
                    break;
                case NalUnitUtil.H265_NAL_UNIT_TYPE_PREFIX_SEI /* 39 */:
                    int i13 = q2.i((List) unsafe.getObject(obj, j10));
                    if (i13 > 0) {
                        iA = androidx.compose.material3.d.b(i13, u.t(i11), i13, iA);
                    }
                    break;
                case 40:
                    int iG7 = q2.g((List) unsafe.getObject(obj, j10));
                    if (iG7 > 0) {
                        iA = androidx.compose.material3.d.b(iG7, u.t(i11), iG7, iA);
                    }
                    break;
                case 41:
                    int iE2 = q2.e((List) unsafe.getObject(obj, j10));
                    if (iE2 > 0) {
                        iA = androidx.compose.material3.d.b(iE2, u.t(i11), iE2, iA);
                    }
                    break;
                case AacUtil.AUDIO_OBJECT_TYPE_AAC_XHE /* 42 */:
                    List list = (List) unsafe.getObject(obj, j10);
                    Class cls3 = q2.f2507a;
                    int size3 = list.size();
                    if (size3 > 0) {
                        iA = androidx.compose.material3.d.b(size3, u.t(i11), size3, iA);
                    }
                    break;
                case 43:
                    int iS2 = q2.s((List) unsafe.getObject(obj, j10));
                    if (iS2 > 0) {
                        iA = androidx.compose.material3.d.b(iS2, u.t(i11), iS2, iA);
                    }
                    break;
                case 44:
                    int iC = q2.c((List) unsafe.getObject(obj, j10));
                    if (iC > 0) {
                        iA = androidx.compose.material3.d.b(iC, u.t(i11), iC, iA);
                    }
                    break;
                case TsExtractor.TS_STREAM_TYPE_MHAS /* 45 */:
                    int iE3 = q2.e((List) unsafe.getObject(obj, j10));
                    if (iE3 > 0) {
                        iA = androidx.compose.material3.d.b(iE3, u.t(i11), iE3, iA);
                    }
                    break;
                case 46:
                    int iG8 = q2.g((List) unsafe.getObject(obj, j10));
                    if (iG8 > 0) {
                        iA = androidx.compose.material3.d.b(iG8, u.t(i11), iG8, iA);
                    }
                    break;
                case 47:
                    int iN = q2.n((List) unsafe.getObject(obj, j10));
                    if (iN > 0) {
                        iA = androidx.compose.material3.d.b(iN, u.t(i11), iN, iA);
                    }
                    break;
                case 48:
                    int iP2 = q2.p((List) unsafe.getObject(obj, j10));
                    if (iP2 > 0) {
                        iA = androidx.compose.material3.d.b(iP2, u.t(i11), iP2, iA);
                    }
                    break;
                case 49:
                    List listS2 = s(j10, obj);
                    o2 o2VarN2 = n(i10);
                    Class cls4 = q2.f2507a;
                    int size4 = listS2.size();
                    if (size4 == 0) {
                        iQ = 0;
                    } else {
                        iQ = 0;
                        for (int i14 = 0; i14 < size4; i14++) {
                            iQ += u.q(i11, (v1) listS2.get(i14), o2VarN2);
                        }
                    }
                    iA += iQ;
                    break;
                case 50:
                    iA += this.f2564n.g(i11, p3.f2500d.i(j10, obj), m(i10));
                    break;
                case 51:
                    if (r(i11, i10, obj)) {
                        iA = androidx.compose.material3.d.a(i11, 8, iA);
                    }
                    break;
                case 52:
                    if (r(i11, i10, obj)) {
                        iA = androidx.compose.material3.d.a(i11, 4, iA);
                    }
                    break;
                case 53:
                    if (r(i11, i10, obj)) {
                        long jA = A(j10, obj);
                        iT = u.t(i11);
                        iW = u.w(jA);
                        iP = iW + iT;
                        iA += iP;
                    }
                    break;
                case 54:
                    if (r(i11, i10, obj)) {
                        long jA2 = A(j10, obj);
                        iT = u.t(i11);
                        iW = u.w(jA2);
                        iP = iW + iT;
                        iA += iP;
                    }
                    break;
                case 55:
                    if (r(i11, i10, obj)) {
                        int iZ = z(j10, obj);
                        iT2 = u.t(i11);
                        iR = u.r(iZ);
                        iP = iR + iT2;
                        iA += iP;
                    }
                    break;
                case 56:
                    if (r(i11, i10, obj)) {
                        iP = u.p(i11);
                        iA += iP;
                    }
                    break;
                case 57:
                    if (r(i11, i10, obj)) {
                        iP = u.o(i11);
                        iA += iP;
                    }
                    break;
                case 58:
                    if (r(i11, i10, obj)) {
                        iA = androidx.compose.material3.d.a(i11, 1, iA);
                    }
                    break;
                case 59:
                    if (r(i11, i10, obj)) {
                        Object objI3 = p3.f2500d.i(j10, obj);
                        if (objI3 instanceof m) {
                            int iT7 = u.t(i11);
                            int size5 = ((m) objI3).size();
                            iA = androidx.compose.material3.d.b(size5, size5, iT7, iA);
                        } else {
                            iT3 = u.t(i11);
                            iS = u.s((String) objI3);
                            iA = iS + iT3 + iA;
                        }
                    }
                    break;
                case 60:
                    if (r(i11, i10, obj)) {
                        Object objI4 = p3.f2500d.i(j10, obj);
                        o2 o2VarN3 = n(i10);
                        Class cls5 = q2.f2507a;
                        int iT8 = u.t(i11);
                        int iG9 = ((c) ((v1) objI4)).g(o2VarN3);
                        iA = androidx.compose.material3.d.b(iG9, iG9, iT8, iA);
                    }
                    break;
                case 61:
                    if (r(i11, i10, obj)) {
                        iP = u.m(i11, (m) p3.f2500d.i(j10, obj));
                        iA += iP;
                    }
                    break;
                case 62:
                    if (r(i11, i10, obj)) {
                        iP = u.u(i11, z(j10, obj));
                        iA += iP;
                    }
                    break;
                case ColorSpace.MaxId /* 63 */:
                    if (r(i11, i10, obj)) {
                        int iZ2 = z(j10, obj);
                        iT2 = u.t(i11);
                        iR = u.r(iZ2);
                        iP = iR + iT2;
                        iA += iP;
                    }
                    break;
                case 64:
                    if (r(i11, i10, obj)) {
                        iA = androidx.compose.material3.d.a(i11, 4, iA);
                    }
                    break;
                case 65:
                    if (r(i11, i10, obj)) {
                        iA = androidx.compose.material3.d.a(i11, 8, iA);
                    }
                    break;
                case 66:
                    if (r(i11, i10, obj)) {
                        int iZ3 = z(j10, obj);
                        iT2 = u.t(i11);
                        iR = u.v((iZ3 >> 31) ^ (iZ3 << 1));
                        iP = iR + iT2;
                        iA += iP;
                    }
                    break;
                case MdtaMetadataEntry.TYPE_INDICATOR_INT32 /* 67 */:
                    if (r(i11, i10, obj)) {
                        long jA3 = A(j10, obj);
                        iT4 = u.t(i11);
                        iW2 = u.w((jA3 >> 63) ^ (jA3 << 1));
                        iP = iW2 + iT4;
                        iA += iP;
                    }
                    break;
                case 68:
                    if (r(i11, i10, obj)) {
                        iP = u.q(i11, (v1) p3.f2500d.i(j10, obj), n(i10));
                        iA += iP;
                    }
                    break;
            }
            i10 += 3;
        }
    }

    public final boolean q(int i10, Object obj) {
        if (this.f2558g) {
            int iG = G(i10);
            long j10 = iG & 1048575;
            switch (F(iG)) {
                case 0:
                    if (p3.f2500d.e(j10, obj) == 0.0d) {
                        return false;
                    }
                    break;
                case 1:
                    if (p3.f2500d.f(j10, obj) == 0.0f) {
                        return false;
                    }
                    break;
                case 2:
                    if (p3.f2500d.h(j10, obj) == 0) {
                        return false;
                    }
                    break;
                case 3:
                    if (p3.f2500d.h(j10, obj) == 0) {
                        return false;
                    }
                    break;
                case 4:
                    if (p3.f2500d.g(j10, obj) == 0) {
                        return false;
                    }
                    break;
                case 5:
                    if (p3.f2500d.h(j10, obj) == 0) {
                        return false;
                    }
                    break;
                case 6:
                    if (p3.f2500d.g(j10, obj) == 0) {
                        return false;
                    }
                    break;
                case 7:
                    return p3.f2500d.c(j10, obj);
                case 8:
                    Object objI = p3.f2500d.i(j10, obj);
                    if (objI instanceof String) {
                        return !((String) objI).isEmpty();
                    }
                    if (objI instanceof m) {
                        return !m.f2470m.equals(objI);
                    }
                    throw new IllegalArgumentException();
                case 9:
                    if (p3.f2500d.i(j10, obj) == null) {
                        return false;
                    }
                    break;
                case 10:
                    return !m.f2470m.equals(p3.f2500d.i(j10, obj));
                case 11:
                    if (p3.f2500d.g(j10, obj) == 0) {
                        return false;
                    }
                    break;
                case 12:
                    if (p3.f2500d.g(j10, obj) == 0) {
                        return false;
                    }
                    break;
                case 13:
                    if (p3.f2500d.g(j10, obj) == 0) {
                        return false;
                    }
                    break;
                case 14:
                    if (p3.f2500d.h(j10, obj) == 0) {
                        return false;
                    }
                    break;
                case 15:
                    if (p3.f2500d.g(j10, obj) == 0) {
                        return false;
                    }
                    break;
                case 16:
                    if (p3.f2500d.h(j10, obj) == 0) {
                        return false;
                    }
                    break;
                case 17:
                    if (p3.f2500d.i(j10, obj) == null) {
                        return false;
                    }
                    break;
                default:
                    throw new IllegalArgumentException();
            }
        } else {
            if ((p3.f2500d.g(r6 & 1048575, obj) & (1 << (this.f2552a[i10 + 2] >>> 20))) == 0) {
                return false;
            }
        }
        return true;
    }

    public final boolean r(int i10, int i11, Object obj) {
        return p3.f2500d.g((long) (this.f2552a[i11 + 2] & 1048575), obj) == i10;
    }

    public final void t(Object obj, int i10, Object obj2, d0 d0Var, n2 n2Var) {
        long jG = G(i10) & 1048575;
        Object objI = p3.f2500d.i(jG, obj);
        q1 q1Var = this.f2564n;
        if (objI == null) {
            objI = q1Var.f();
            p3.o(obj, objI, jG);
        } else if (q1Var.h(objI)) {
            p1 p1VarF = q1Var.f();
            q1Var.a(p1VarF, objI);
            p3.o(obj, p1VarF, jG);
            objI = p1VarF;
        }
        n2Var.t(q1Var.c(objI), q1Var.b(obj2), d0Var);
    }

    public final void u(int i10, Object obj, Object obj2) {
        long jG = G(i10) & 1048575;
        if (q(i10, obj2)) {
            o3 o3Var = p3.f2500d;
            Object objI = o3Var.i(jG, obj);
            Object objI2 = o3Var.i(jG, obj2);
            if (objI != null && objI2 != null) {
                p3.o(obj, f1.c(objI, objI2), jG);
                D(i10, obj);
            } else if (objI2 != null) {
                p3.o(obj, objI2, jG);
                D(i10, obj);
            }
        }
    }

    public final void v(int i10, Object obj, Object obj2) {
        int iG = G(i10);
        int i11 = this.f2552a[i10];
        long j10 = iG & 1048575;
        if (r(i11, i10, obj2)) {
            o3 o3Var = p3.f2500d;
            Object objI = o3Var.i(j10, obj);
            Object objI2 = o3Var.i(j10, obj2);
            if (objI != null && objI2 != null) {
                p3.o(obj, f1.c(objI, objI2), j10);
                E(i11, i10, obj);
            } else if (objI2 != null) {
                p3.o(obj, objI2, j10);
                E(i11, i10, obj);
            }
        }
    }
}
