package com.google.android.gms.internal.cast;

import androidx.compose.ui.graphics.colorspace.ColorSpace;
import androidx.media3.container.MdtaMetadataEntry;
import androidx.media3.container.NalUnitUtil;
import androidx.media3.extractor.AacUtil;
import androidx.media3.extractor.flac.FlacConstants;
import androidx.media3.extractor.ts.TsExtractor;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import sun.misc.Unsafe;

/* JADX INFO: loaded from: classes4.dex */
public final class lb implements rb {

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final int[] f13426g = new int[0];

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public static final Unsafe f13427h = h.l();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final int[] f13428a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Object[] f13429b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final ib f13430c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final int[] f13431d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final int f13432e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final k5 f13433f;

    public lb(int[] iArr, Object[] objArr, ib ibVar, int[] iArr2, int i10, k5 k5Var, k5 k5Var2) {
        this.f13428a = iArr;
        this.f13429b = objArr;
        this.f13431d = iArr2;
        this.f13432e = i10;
        this.f13433f = k5Var;
        this.f13430c = ibVar;
    }

    /* JADX WARN: Removed duplicated region for block: B:124:0x0243  */
    /* JADX WARN: Removed duplicated region for block: B:126:0x0249  */
    /* JADX WARN: Removed duplicated region for block: B:129:0x0261  */
    /* JADX WARN: Removed duplicated region for block: B:130:0x0264  */
    /* JADX WARN: Removed duplicated region for block: B:168:0x0329  */
    /* JADX WARN: Removed duplicated region for block: B:184:0x037b  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static com.google.android.gms.internal.cast.lb k(com.google.android.gms.internal.cast.fb r30, com.google.android.gms.internal.cast.k5 r31, com.google.android.gms.internal.cast.k5 r32) {
        /*
            Method dump skipped, instruction units count: 999
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.cast.lb.k(com.google.android.gms.internal.cast.fb, com.google.android.gms.internal.cast.k5, com.google.android.gms.internal.cast.k5):com.google.android.gms.internal.cast.lb");
    }

    public static Field l(Class cls, String str) {
        try {
            return cls.getDeclaredField(str);
        } catch (NoSuchFieldException e5) {
            Field[] declaredFields = cls.getDeclaredFields();
            for (Field field : declaredFields) {
                if (str.equals(field.getName())) {
                    return field;
                }
            }
            String name = cls.getName();
            String string = Arrays.toString(declaredFields);
            StringBuilder sb2 = new StringBuilder(String.valueOf(str).length() + 11 + name.length() + 29 + String.valueOf(string).length());
            y.a.i(sb2, "Field ", str, " for ", name);
            throw new RuntimeException(a0.c.p(sb2, " not found. Known fields are ", string), e5);
        }
    }

    public static int q(int i10) {
        return (i10 >>> 20) & 255;
    }

    public static boolean r(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj instanceof pa) {
            return ((pa) obj).l();
        }
        return true;
    }

    public static int s(long j10, Object obj) {
        return ((Integer) h.j(j10, obj)).intValue();
    }

    public static long t(long j10, Object obj) {
        return ((Long) h.j(j10, obj)).longValue();
    }

    @Override // com.google.android.gms.internal.cast.rb
    public final boolean a(pa paVar, pa paVar2) {
        boolean zA;
        int i10 = 0;
        while (true) {
            int[] iArr = this.f13428a;
            if (i10 < iArr.length) {
                int iP = p(i10);
                long j10 = iP & 1048575;
                switch (q(iP)) {
                    case 0:
                        if (u(paVar, paVar2, i10)) {
                            g gVar = h.f13268c;
                            if (Double.doubleToLongBits(gVar.f(j10, paVar)) == Double.doubleToLongBits(gVar.f(j10, paVar2))) {
                                continue;
                                i10 += 3;
                            }
                        }
                        break;
                    case 1:
                        if (u(paVar, paVar2, i10)) {
                            g gVar2 = h.f13268c;
                            if (Float.floatToIntBits(gVar2.d(j10, paVar)) == Float.floatToIntBits(gVar2.d(j10, paVar2))) {
                                continue;
                                i10 += 3;
                            }
                        }
                        break;
                    case 2:
                        if (u(paVar, paVar2, i10) && h.h(j10, paVar) == h.h(j10, paVar2)) {
                            continue;
                            i10 += 3;
                        }
                        break;
                    case 3:
                        if (u(paVar, paVar2, i10) && h.h(j10, paVar) == h.h(j10, paVar2)) {
                            continue;
                            i10 += 3;
                        }
                        break;
                    case 4:
                        if (u(paVar, paVar2, i10) && h.f(j10, paVar) == h.f(j10, paVar2)) {
                            continue;
                            i10 += 3;
                        }
                        break;
                    case 5:
                        if (u(paVar, paVar2, i10) && h.h(j10, paVar) == h.h(j10, paVar2)) {
                            continue;
                            i10 += 3;
                        }
                        break;
                    case 6:
                        if (u(paVar, paVar2, i10) && h.f(j10, paVar) == h.f(j10, paVar2)) {
                            continue;
                            i10 += 3;
                        }
                        break;
                    case 7:
                        if (u(paVar, paVar2, i10)) {
                            g gVar3 = h.f13268c;
                            if (gVar3.b(j10, paVar) == gVar3.b(j10, paVar2)) {
                                continue;
                                i10 += 3;
                            }
                        }
                        break;
                    case 8:
                        if (u(paVar, paVar2, i10) && tb.a(h.j(j10, paVar), h.j(j10, paVar2))) {
                            continue;
                            i10 += 3;
                        }
                        break;
                    case 9:
                        if (u(paVar, paVar2, i10) && tb.a(h.j(j10, paVar), h.j(j10, paVar2))) {
                            continue;
                            i10 += 3;
                        }
                        break;
                    case 10:
                        if (u(paVar, paVar2, i10) && tb.a(h.j(j10, paVar), h.j(j10, paVar2))) {
                            continue;
                            i10 += 3;
                        }
                        break;
                    case 11:
                        if (u(paVar, paVar2, i10) && h.f(j10, paVar) == h.f(j10, paVar2)) {
                            continue;
                            i10 += 3;
                        }
                        break;
                    case 12:
                        if (u(paVar, paVar2, i10) && h.f(j10, paVar) == h.f(j10, paVar2)) {
                            continue;
                            i10 += 3;
                        }
                        break;
                    case 13:
                        if (u(paVar, paVar2, i10) && h.f(j10, paVar) == h.f(j10, paVar2)) {
                            continue;
                            i10 += 3;
                        }
                        break;
                    case 14:
                        if (u(paVar, paVar2, i10) && h.h(j10, paVar) == h.h(j10, paVar2)) {
                            continue;
                            i10 += 3;
                        }
                        break;
                    case 15:
                        if (u(paVar, paVar2, i10) && h.f(j10, paVar) == h.f(j10, paVar2)) {
                            continue;
                            i10 += 3;
                        }
                        break;
                    case 16:
                        if (u(paVar, paVar2, i10) && h.h(j10, paVar) == h.h(j10, paVar2)) {
                            continue;
                            i10 += 3;
                        }
                        break;
                    case 17:
                        if (u(paVar, paVar2, i10) && tb.a(h.j(j10, paVar), h.j(j10, paVar2))) {
                            continue;
                            i10 += 3;
                        }
                        break;
                    case 18:
                    case 19:
                    case 20:
                    case 21:
                    case 22:
                    case 23:
                    case 24:
                    case 25:
                    case 26:
                    case 27:
                    case 28:
                    case 29:
                    case 30:
                    case 31:
                    case 32:
                    case 33:
                    case 34:
                    case 35:
                    case TsExtractor.TS_STREAM_TYPE_H265 /* 36 */:
                    case 37:
                    case FlacConstants.STREAM_INFO_BLOCK_SIZE /* 38 */:
                    case NalUnitUtil.H265_NAL_UNIT_TYPE_PREFIX_SEI /* 39 */:
                    case 40:
                    case 41:
                    case AacUtil.AUDIO_OBJECT_TYPE_AAC_XHE /* 42 */:
                    case 43:
                    case 44:
                    case TsExtractor.TS_STREAM_TYPE_MHAS /* 45 */:
                    case 46:
                    case 47:
                    case 48:
                    case 49:
                        zA = tb.a(h.j(j10, paVar), h.j(j10, paVar2));
                        break;
                    case 50:
                        zA = tb.a(h.j(j10, paVar), h.j(j10, paVar2));
                        break;
                    case 51:
                    case 52:
                    case 53:
                    case 54:
                    case 55:
                    case 56:
                    case 57:
                    case 58:
                    case 59:
                    case 60:
                    case 61:
                    case 62:
                    case ColorSpace.MaxId /* 63 */:
                    case 64:
                    case 65:
                    case 66:
                    case MdtaMetadataEntry.TYPE_INDICATOR_INT32 /* 67 */:
                    case 68:
                        long j11 = iArr[i10 + 2] & 1048575;
                        if (h.f(j11, paVar) == h.f(j11, paVar2) && tb.a(h.j(j10, paVar), h.j(j10, paVar2))) {
                            continue;
                            i10 += 3;
                        }
                        break;
                    default:
                        i10 += 3;
                        break;
                }
                if (zA) {
                    i10 += 3;
                }
            } else if (paVar.zzc.equals(paVar2.zzc)) {
                return true;
            }
        }
        return false;
    }

    /* JADX WARN: Removed duplicated region for block: B:137:0x03a9  */
    /* JADX WARN: Removed duplicated region for block: B:190:0x04ee  */
    @Override // com.google.android.gms.internal.cast.rb
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final int b(com.google.android.gms.internal.cast.ba r18) {
        /*
            Method dump skipped, instruction units count: 1980
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.cast.lb.b(com.google.android.gms.internal.cast.ba):int");
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Removed duplicated region for block: B:9:0x0022  */
    @Override // com.google.android.gms.internal.cast.rb
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void c(java.lang.Object r13, java.lang.Object r14) {
        /*
            Method dump skipped, instruction units count: 638
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.cast.lb.c(java.lang.Object, java.lang.Object):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:44:0x00db A[PHI: r1
      0x00db: PHI (r1v35 int) = (r1v11 int), (r1v36 int) binds: [B:85:0x01ea, B:43:0x00d9] A[DONT_GENERATE, DONT_INLINE]] */
    @Override // com.google.android.gms.internal.cast.rb
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final int d(com.google.android.gms.internal.cast.pa r11) {
        /*
            Method dump skipped, instruction units count: 728
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.cast.lb.d(com.google.android.gms.internal.cast.pa):int");
    }

    @Override // com.google.android.gms.internal.cast.rb
    public final void e(Object obj, ja jaVar) throws zzxo {
        int i10;
        int i11;
        lb lbVar = this;
        ia iaVar = (ia) jaVar.f13365l;
        Unsafe unsafe = f13427h;
        int i12 = 1048575;
        int i13 = 1048575;
        int i14 = 0;
        int i15 = 0;
        while (true) {
            int[] iArr = lbVar.f13428a;
            if (i14 >= iArr.length) {
                c cVar = ((pa) obj).zzc;
                return;
            }
            int iP = lbVar.p(i14);
            int iQ = q(iP);
            int i16 = iArr[i14];
            if (iQ <= 17) {
                int i17 = iArr[i14 + 2];
                int i18 = i17 & i12;
                if (i18 != i13) {
                    i15 = i18 == i12 ? 0 : unsafe.getInt(obj, i18);
                    i13 = i18;
                }
                i10 = 1 << (i17 >>> 20);
            } else {
                i10 = 0;
            }
            long j10 = iP & i12;
            switch (iQ) {
                case 0:
                    if (lbVar.v(i14, obj, i13, i15, i10)) {
                        jaVar.i(i16, h.f13268c.f(j10, obj));
                    }
                    break;
                case 1:
                    if (lbVar.v(i14, obj, i13, i15, i10)) {
                        jaVar.h(h.f13268c.d(j10, obj), i16);
                    }
                    break;
                case 2:
                    if (lbVar.v(i14, obj, i13, i15, i10)) {
                        jaVar.f(i16, unsafe.getLong(obj, j10));
                    }
                    break;
                case 3:
                    if (lbVar.v(i14, obj, i13, i15, i10)) {
                        jaVar.k(i16, unsafe.getLong(obj, j10));
                    }
                    break;
                case 4:
                    if (lbVar.v(i14, obj, i13, i15, i10)) {
                        jaVar.l(i16, unsafe.getInt(obj, j10));
                    }
                    break;
                case 5:
                    if (lbVar.v(i14, obj, i13, i15, i10)) {
                        jaVar.m(i16, unsafe.getLong(obj, j10));
                    }
                    break;
                case 6:
                    if (lbVar.v(i14, obj, i13, i15, i10)) {
                        jaVar.n(i16, unsafe.getInt(obj, j10));
                    }
                    break;
                case 7:
                    if (lbVar.v(i14, obj, i13, i15, i10)) {
                        jaVar.o(i16, h.f13268c.b(j10, obj));
                    }
                    break;
                case 8:
                    if (lbVar.v(i14, obj, i13, i15, i10)) {
                        Object object = unsafe.getObject(obj, j10);
                        if (object instanceof String) {
                            iaVar.G(i16, (String) object);
                        } else {
                            jaVar.p(i16, (ha) object);
                        }
                    }
                    break;
                case 9:
                    if (lbVar.v(i14, obj, i13, i15, i10)) {
                        jaVar.t(i16, unsafe.getObject(obj, j10), lbVar.o(i14));
                    }
                    break;
                case 10:
                    if (lbVar.v(i14, obj, i13, i15, i10)) {
                        jaVar.p(i16, (ha) unsafe.getObject(obj, j10));
                    }
                    break;
                case 11:
                    if (lbVar.v(i14, obj, i13, i15, i10)) {
                        jaVar.q(i16, unsafe.getInt(obj, j10));
                    }
                    break;
                case 12:
                    if (lbVar.v(i14, obj, i13, i15, i10)) {
                        jaVar.j(i16, unsafe.getInt(obj, j10));
                    }
                    break;
                case 13:
                    if (lbVar.v(i14, obj, i13, i15, i10)) {
                        jaVar.e(i16, unsafe.getInt(obj, j10));
                    }
                    break;
                case 14:
                    if (lbVar.v(i14, obj, i13, i15, i10)) {
                        jaVar.g(i16, unsafe.getLong(obj, j10));
                    }
                    break;
                case 15:
                    if (lbVar.v(i14, obj, i13, i15, i10)) {
                        jaVar.r(i16, unsafe.getInt(obj, j10));
                    }
                    break;
                case 16:
                    if (lbVar.v(i14, obj, i13, i15, i10)) {
                        jaVar.s(i16, unsafe.getLong(obj, j10));
                    }
                    break;
                case 17:
                    if (lbVar.v(i14, obj, i13, i15, i10)) {
                        jaVar.u(i16, unsafe.getObject(obj, j10), lbVar.o(i14));
                    }
                    break;
                case 18:
                    int i19 = iArr[i14];
                    List list = (List) unsafe.getObject(obj, j10);
                    k5 k5Var = tb.f13618a;
                    if (list != null && !list.isEmpty()) {
                        for (int i20 = 0; i20 < list.size(); i20++) {
                            iaVar.F(i19, Double.doubleToRawLongBits(((Double) list.get(i20)).doubleValue()));
                        }
                    }
                    break;
                case 19:
                    int i21 = iArr[i14];
                    List list2 = (List) unsafe.getObject(obj, j10);
                    k5 k5Var2 = tb.f13618a;
                    if (list2 != null && !list2.isEmpty()) {
                        if (list2 instanceof na) {
                            na naVar = (na) list2;
                            for (int i22 = 0; i22 < naVar.f13495m; i22++) {
                                naVar.b(i22);
                                iaVar.D(i21, Float.floatToRawIntBits(naVar.f13494l[i22]));
                            }
                        } else {
                            for (int i23 = 0; i23 < list2.size(); i23++) {
                                iaVar.D(i21, Float.floatToRawIntBits(((Float) list2.get(i23)).floatValue()));
                            }
                        }
                    }
                    break;
                case 20:
                    int i24 = iArr[i14];
                    List list3 = (List) unsafe.getObject(obj, j10);
                    k5 k5Var3 = tb.f13618a;
                    if (list3 != null && !list3.isEmpty()) {
                        if (list3 instanceof cb) {
                            cb cbVar = (cb) list3;
                            for (int i25 = 0; i25 < cbVar.f13225m; i25++) {
                                iaVar.E(i24, cbVar.b(i25));
                            }
                        } else {
                            for (int i26 = 0; i26 < list3.size(); i26++) {
                                iaVar.E(i24, ((Long) list3.get(i26)).longValue());
                            }
                        }
                    }
                    break;
                case 21:
                    int i27 = iArr[i14];
                    List list4 = (List) unsafe.getObject(obj, j10);
                    k5 k5Var4 = tb.f13618a;
                    if (list4 != null && !list4.isEmpty()) {
                        if (list4 instanceof cb) {
                            cb cbVar2 = (cb) list4;
                            for (int i28 = 0; i28 < cbVar2.f13225m; i28++) {
                                iaVar.E(i27, cbVar2.b(i28));
                            }
                        } else {
                            for (int i29 = 0; i29 < list4.size(); i29++) {
                                iaVar.E(i27, ((Long) list4.get(i29)).longValue());
                            }
                        }
                    }
                    break;
                case 22:
                    int i30 = iArr[i14];
                    List list5 = (List) unsafe.getObject(obj, j10);
                    k5 k5Var5 = tb.f13618a;
                    if (list5 != null && !list5.isEmpty()) {
                        if (list5 instanceof qa) {
                            qa qaVar = (qa) list5;
                            for (int i31 = 0; i31 < qaVar.f13565m; i31++) {
                                iaVar.u(i30, qaVar.b(i31));
                            }
                        } else {
                            for (int i32 = 0; i32 < list5.size(); i32++) {
                                iaVar.u(i30, ((Integer) list5.get(i32)).intValue());
                            }
                        }
                    }
                    break;
                case 23:
                    int i33 = iArr[i14];
                    List list6 = (List) unsafe.getObject(obj, j10);
                    k5 k5Var6 = tb.f13618a;
                    if (list6 != null && !list6.isEmpty()) {
                        if (list6 instanceof cb) {
                            cb cbVar3 = (cb) list6;
                            for (int i34 = 0; i34 < cbVar3.f13225m; i34++) {
                                iaVar.F(i33, cbVar3.b(i34));
                            }
                        } else {
                            for (int i35 = 0; i35 < list6.size(); i35++) {
                                iaVar.F(i33, ((Long) list6.get(i35)).longValue());
                            }
                        }
                    }
                    break;
                case 24:
                    int i36 = iArr[i14];
                    List list7 = (List) unsafe.getObject(obj, j10);
                    k5 k5Var7 = tb.f13618a;
                    if (list7 != null && !list7.isEmpty()) {
                        if (list7 instanceof qa) {
                            qa qaVar2 = (qa) list7;
                            for (int i37 = 0; i37 < qaVar2.f13565m; i37++) {
                                iaVar.D(i36, qaVar2.b(i37));
                            }
                        } else {
                            for (int i38 = 0; i38 < list7.size(); i38++) {
                                iaVar.D(i36, ((Integer) list7.get(i38)).intValue());
                            }
                        }
                    }
                    break;
                case 25:
                    int i39 = iArr[i14];
                    List list8 = (List) unsafe.getObject(obj, j10);
                    k5 k5Var8 = tb.f13618a;
                    if (list8 != null && !list8.isEmpty()) {
                        for (int i40 = 0; i40 < list8.size(); i40++) {
                            byte bBooleanValue = ((Boolean) list8.get(i40)).booleanValue();
                            iaVar.J(i39 << 3);
                            int i41 = iaVar.f13330p;
                            try {
                                i11 = i41 + 1;
                            } catch (IndexOutOfBoundsException e5) {
                                e = e5;
                            }
                            try {
                                iaVar.f13328n[i41] = bBooleanValue;
                                iaVar.f13330p = i11;
                            } catch (IndexOutOfBoundsException e6) {
                                e = e6;
                                i41 = i11;
                                throw new zzxo(i41, iaVar.f13329o, 1, e);
                            }
                        }
                    }
                    break;
                case 26:
                    int i42 = iArr[i14];
                    List list9 = (List) unsafe.getObject(obj, j10);
                    k5 k5Var9 = tb.f13618a;
                    if (list9 != null && !list9.isEmpty()) {
                        jaVar.c(i42, list9);
                    }
                    break;
                case 27:
                    int i43 = iArr[i14];
                    List list10 = (List) unsafe.getObject(obj, j10);
                    rb rbVarO = lbVar.o(i14);
                    k5 k5Var10 = tb.f13618a;
                    if (list10 != null && !list10.isEmpty()) {
                        for (int i44 = 0; i44 < list10.size(); i44++) {
                            jaVar.t(i43, list10.get(i44), rbVarO);
                        }
                    }
                    break;
                case 28:
                    int i45 = iArr[i14];
                    List list11 = (List) unsafe.getObject(obj, j10);
                    k5 k5Var11 = tb.f13618a;
                    if (list11 != null && !list11.isEmpty()) {
                        jaVar.d(i45, list11);
                    }
                    break;
                case 29:
                    int i46 = iArr[i14];
                    List list12 = (List) unsafe.getObject(obj, j10);
                    k5 k5Var12 = tb.f13618a;
                    if (list12 != null && !list12.isEmpty()) {
                        if (list12 instanceof qa) {
                            qa qaVar3 = (qa) list12;
                            for (int i47 = 0; i47 < qaVar3.f13565m; i47++) {
                                iaVar.C(i46, qaVar3.b(i47));
                            }
                        } else {
                            for (int i48 = 0; i48 < list12.size(); i48++) {
                                iaVar.C(i46, ((Integer) list12.get(i48)).intValue());
                            }
                        }
                    }
                    break;
                case 30:
                    int i49 = iArr[i14];
                    List list13 = (List) unsafe.getObject(obj, j10);
                    k5 k5Var13 = tb.f13618a;
                    if (list13 != null && !list13.isEmpty()) {
                        if (list13 instanceof qa) {
                            qa qaVar4 = (qa) list13;
                            for (int i50 = 0; i50 < qaVar4.f13565m; i50++) {
                                iaVar.u(i49, qaVar4.b(i50));
                            }
                        } else {
                            for (int i51 = 0; i51 < list13.size(); i51++) {
                                iaVar.u(i49, ((Integer) list13.get(i51)).intValue());
                            }
                        }
                    }
                    break;
                case 31:
                    int i52 = iArr[i14];
                    List list14 = (List) unsafe.getObject(obj, j10);
                    k5 k5Var14 = tb.f13618a;
                    if (list14 != null && !list14.isEmpty()) {
                        if (list14 instanceof qa) {
                            qa qaVar5 = (qa) list14;
                            for (int i53 = 0; i53 < qaVar5.f13565m; i53++) {
                                iaVar.D(i52, qaVar5.b(i53));
                            }
                        } else {
                            for (int i54 = 0; i54 < list14.size(); i54++) {
                                iaVar.D(i52, ((Integer) list14.get(i54)).intValue());
                            }
                        }
                    }
                    break;
                case 32:
                    int i55 = iArr[i14];
                    List list15 = (List) unsafe.getObject(obj, j10);
                    k5 k5Var15 = tb.f13618a;
                    if (list15 != null && !list15.isEmpty()) {
                        if (list15 instanceof cb) {
                            cb cbVar4 = (cb) list15;
                            for (int i56 = 0; i56 < cbVar4.f13225m; i56++) {
                                iaVar.F(i55, cbVar4.b(i56));
                            }
                        } else {
                            for (int i57 = 0; i57 < list15.size(); i57++) {
                                iaVar.F(i55, ((Long) list15.get(i57)).longValue());
                            }
                        }
                    }
                    break;
                case 33:
                    int i58 = iArr[i14];
                    List list16 = (List) unsafe.getObject(obj, j10);
                    k5 k5Var16 = tb.f13618a;
                    if (list16 != null && !list16.isEmpty()) {
                        if (list16 instanceof qa) {
                            qa qaVar6 = (qa) list16;
                            for (int i59 = 0; i59 < qaVar6.f13565m; i59++) {
                                int iB = qaVar6.b(i59);
                                iaVar.C(i58, (iB >> 31) ^ (iB + iB));
                            }
                        } else {
                            for (int i60 = 0; i60 < list16.size(); i60++) {
                                int iIntValue = ((Integer) list16.get(i60)).intValue();
                                iaVar.C(i58, (iIntValue >> 31) ^ (iIntValue + iIntValue));
                            }
                        }
                    }
                    break;
                case 34:
                    int i61 = iArr[i14];
                    List list17 = (List) unsafe.getObject(obj, j10);
                    k5 k5Var17 = tb.f13618a;
                    if (list17 != null && !list17.isEmpty()) {
                        if (list17 instanceof cb) {
                            cb cbVar5 = (cb) list17;
                            for (int i62 = 0; i62 < cbVar5.f13225m; i62++) {
                                long jB = cbVar5.b(i62);
                                iaVar.E(i61, (jB >> 63) ^ (jB + jB));
                            }
                        } else {
                            for (int i63 = 0; i63 < list17.size(); i63++) {
                                long jLongValue = ((Long) list17.get(i63)).longValue();
                                iaVar.E(i61, (jLongValue >> 63) ^ (jLongValue + jLongValue));
                            }
                        }
                    }
                    break;
                case 35:
                    int i64 = iArr[i14];
                    List list18 = (List) unsafe.getObject(obj, j10);
                    k5 k5Var18 = tb.f13618a;
                    if (list18 != null && !list18.isEmpty()) {
                        iaVar.r(i64, 2);
                        int i65 = 0;
                        for (int i66 = 0; i66 < list18.size(); i66++) {
                            ((Double) list18.get(i66)).getClass();
                            i65 += 8;
                        }
                        iaVar.J(i65);
                        for (int i67 = 0; i67 < list18.size(); i67++) {
                            iaVar.M(Double.doubleToRawLongBits(((Double) list18.get(i67)).doubleValue()));
                        }
                    }
                    break;
                case TsExtractor.TS_STREAM_TYPE_H265 /* 36 */:
                    int i68 = iArr[i14];
                    List list19 = (List) unsafe.getObject(obj, j10);
                    k5 k5Var19 = tb.f13618a;
                    if (list19 != null && !list19.isEmpty()) {
                        if (list19 instanceof na) {
                            na naVar2 = (na) list19;
                            iaVar.r(i68, 2);
                            int i69 = 0;
                            for (int i70 = 0; i70 < naVar2.f13495m; i70++) {
                                naVar2.b(i70);
                                float f10 = naVar2.f13494l[i70];
                                i69 += 4;
                            }
                            iaVar.J(i69);
                            for (int i71 = 0; i71 < naVar2.f13495m; i71++) {
                                naVar2.b(i71);
                                iaVar.K(Float.floatToRawIntBits(naVar2.f13494l[i71]));
                            }
                        } else {
                            iaVar.r(i68, 2);
                            int i72 = 0;
                            for (int i73 = 0; i73 < list19.size(); i73++) {
                                ((Float) list19.get(i73)).getClass();
                                i72 += 4;
                            }
                            iaVar.J(i72);
                            for (int i74 = 0; i74 < list19.size(); i74++) {
                                iaVar.K(Float.floatToRawIntBits(((Float) list19.get(i74)).floatValue()));
                            }
                        }
                    }
                    break;
                case 37:
                    int i75 = iArr[i14];
                    List list20 = (List) unsafe.getObject(obj, j10);
                    k5 k5Var20 = tb.f13618a;
                    if (list20 != null && !list20.isEmpty()) {
                        if (list20 instanceof cb) {
                            cb cbVar6 = (cb) list20;
                            iaVar.r(i75, 2);
                            int iP2 = 0;
                            for (int i76 = 0; i76 < cbVar6.f13225m; i76++) {
                                iP2 += ia.P(cbVar6.b(i76));
                            }
                            iaVar.J(iP2);
                            for (int i77 = 0; i77 < cbVar6.f13225m; i77++) {
                                iaVar.L(cbVar6.b(i77));
                            }
                        } else {
                            iaVar.r(i75, 2);
                            int iP3 = 0;
                            for (int i78 = 0; i78 < list20.size(); i78++) {
                                iP3 += ia.P(((Long) list20.get(i78)).longValue());
                            }
                            iaVar.J(iP3);
                            for (int i79 = 0; i79 < list20.size(); i79++) {
                                iaVar.L(((Long) list20.get(i79)).longValue());
                            }
                        }
                    }
                    break;
                case FlacConstants.STREAM_INFO_BLOCK_SIZE /* 38 */:
                    int i80 = iArr[i14];
                    List list21 = (List) unsafe.getObject(obj, j10);
                    k5 k5Var21 = tb.f13618a;
                    if (list21 != null && !list21.isEmpty()) {
                        if (list21 instanceof cb) {
                            cb cbVar7 = (cb) list21;
                            iaVar.r(i80, 2);
                            int iP4 = 0;
                            for (int i81 = 0; i81 < cbVar7.f13225m; i81++) {
                                iP4 += ia.P(cbVar7.b(i81));
                            }
                            iaVar.J(iP4);
                            for (int i82 = 0; i82 < cbVar7.f13225m; i82++) {
                                iaVar.L(cbVar7.b(i82));
                            }
                        } else {
                            iaVar.r(i80, 2);
                            int iP5 = 0;
                            for (int i83 = 0; i83 < list21.size(); i83++) {
                                iP5 += ia.P(((Long) list21.get(i83)).longValue());
                            }
                            iaVar.J(iP5);
                            for (int i84 = 0; i84 < list21.size(); i84++) {
                                iaVar.L(((Long) list21.get(i84)).longValue());
                            }
                        }
                    }
                    break;
                case NalUnitUtil.H265_NAL_UNIT_TYPE_PREFIX_SEI /* 39 */:
                    int i85 = iArr[i14];
                    List list22 = (List) unsafe.getObject(obj, j10);
                    k5 k5Var22 = tb.f13618a;
                    if (list22 != null && !list22.isEmpty()) {
                        if (list22 instanceof qa) {
                            qa qaVar7 = (qa) list22;
                            iaVar.r(i85, 2);
                            int iP6 = 0;
                            for (int i86 = 0; i86 < qaVar7.f13565m; i86++) {
                                iP6 += ia.P(qaVar7.b(i86));
                            }
                            iaVar.J(iP6);
                            for (int i87 = 0; i87 < qaVar7.f13565m; i87++) {
                                iaVar.I(qaVar7.b(i87));
                            }
                        } else {
                            iaVar.r(i85, 2);
                            int iP7 = 0;
                            for (int i88 = 0; i88 < list22.size(); i88++) {
                                iP7 += ia.P(((Integer) list22.get(i88)).intValue());
                            }
                            iaVar.J(iP7);
                            for (int i89 = 0; i89 < list22.size(); i89++) {
                                iaVar.I(((Integer) list22.get(i89)).intValue());
                            }
                        }
                    }
                    break;
                case 40:
                    int i90 = iArr[i14];
                    List list23 = (List) unsafe.getObject(obj, j10);
                    k5 k5Var23 = tb.f13618a;
                    if (list23 != null && !list23.isEmpty()) {
                        if (list23 instanceof cb) {
                            cb cbVar8 = (cb) list23;
                            iaVar.r(i90, 2);
                            int i91 = 0;
                            for (int i92 = 0; i92 < cbVar8.f13225m; i92++) {
                                cbVar8.b(i92);
                                i91 += 8;
                            }
                            iaVar.J(i91);
                            for (int i93 = 0; i93 < cbVar8.f13225m; i93++) {
                                iaVar.M(cbVar8.b(i93));
                            }
                        } else {
                            iaVar.r(i90, 2);
                            int i94 = 0;
                            for (int i95 = 0; i95 < list23.size(); i95++) {
                                ((Long) list23.get(i95)).getClass();
                                i94 += 8;
                            }
                            iaVar.J(i94);
                            for (int i96 = 0; i96 < list23.size(); i96++) {
                                iaVar.M(((Long) list23.get(i96)).longValue());
                            }
                        }
                    }
                    break;
                case 41:
                    int i97 = iArr[i14];
                    List list24 = (List) unsafe.getObject(obj, j10);
                    k5 k5Var24 = tb.f13618a;
                    if (list24 != null && !list24.isEmpty()) {
                        if (list24 instanceof qa) {
                            qa qaVar8 = (qa) list24;
                            iaVar.r(i97, 2);
                            int i98 = 0;
                            for (int i99 = 0; i99 < qaVar8.f13565m; i99++) {
                                qaVar8.b(i99);
                                i98 += 4;
                            }
                            iaVar.J(i98);
                            for (int i100 = 0; i100 < qaVar8.f13565m; i100++) {
                                iaVar.K(qaVar8.b(i100));
                            }
                        } else {
                            iaVar.r(i97, 2);
                            int i101 = 0;
                            for (int i102 = 0; i102 < list24.size(); i102++) {
                                ((Integer) list24.get(i102)).getClass();
                                i101 += 4;
                            }
                            iaVar.J(i101);
                            for (int i103 = 0; i103 < list24.size(); i103++) {
                                iaVar.K(((Integer) list24.get(i103)).intValue());
                            }
                        }
                    }
                    break;
                case AacUtil.AUDIO_OBJECT_TYPE_AAC_XHE /* 42 */:
                    int i104 = iArr[i14];
                    List list25 = (List) unsafe.getObject(obj, j10);
                    k5 k5Var25 = tb.f13618a;
                    if (list25 != null && !list25.isEmpty()) {
                        iaVar.r(i104, 2);
                        int i105 = 0;
                        for (int i106 = 0; i106 < list25.size(); i106++) {
                            ((Boolean) list25.get(i106)).getClass();
                            i105++;
                        }
                        iaVar.J(i105);
                        for (int i107 = 0; i107 < list25.size(); i107++) {
                            byte bBooleanValue2 = ((Boolean) list25.get(i107)).booleanValue();
                            int i108 = iaVar.f13330p;
                            try {
                                int i109 = i108 + 1;
                                try {
                                    iaVar.f13328n[i108] = bBooleanValue2;
                                    iaVar.f13330p = i109;
                                } catch (IndexOutOfBoundsException e10) {
                                    e = e10;
                                    i108 = i109;
                                    throw new zzxo(i108, iaVar.f13329o, 1, e);
                                }
                            } catch (IndexOutOfBoundsException e11) {
                                e = e11;
                            }
                        }
                    }
                    break;
                case 43:
                    int i110 = iArr[i14];
                    List list26 = (List) unsafe.getObject(obj, j10);
                    k5 k5Var26 = tb.f13618a;
                    if (list26 != null && !list26.isEmpty()) {
                        if (list26 instanceof qa) {
                            qa qaVar9 = (qa) list26;
                            iaVar.r(i110, 2);
                            int iO = 0;
                            for (int i111 = 0; i111 < qaVar9.f13565m; i111++) {
                                iO += ia.O(qaVar9.b(i111));
                            }
                            iaVar.J(iO);
                            for (int i112 = 0; i112 < qaVar9.f13565m; i112++) {
                                iaVar.J(qaVar9.b(i112));
                            }
                        } else {
                            iaVar.r(i110, 2);
                            int iO2 = 0;
                            for (int i113 = 0; i113 < list26.size(); i113++) {
                                iO2 += ia.O(((Integer) list26.get(i113)).intValue());
                            }
                            iaVar.J(iO2);
                            for (int i114 = 0; i114 < list26.size(); i114++) {
                                iaVar.J(((Integer) list26.get(i114)).intValue());
                            }
                        }
                    }
                    break;
                case 44:
                    int i115 = iArr[i14];
                    List list27 = (List) unsafe.getObject(obj, j10);
                    k5 k5Var27 = tb.f13618a;
                    if (list27 != null && !list27.isEmpty()) {
                        if (list27 instanceof qa) {
                            qa qaVar10 = (qa) list27;
                            iaVar.r(i115, 2);
                            int iP8 = 0;
                            for (int i116 = 0; i116 < qaVar10.f13565m; i116++) {
                                iP8 += ia.P(qaVar10.b(i116));
                            }
                            iaVar.J(iP8);
                            for (int i117 = 0; i117 < qaVar10.f13565m; i117++) {
                                iaVar.I(qaVar10.b(i117));
                            }
                        } else {
                            iaVar.r(i115, 2);
                            int iP9 = 0;
                            for (int i118 = 0; i118 < list27.size(); i118++) {
                                iP9 += ia.P(((Integer) list27.get(i118)).intValue());
                            }
                            iaVar.J(iP9);
                            for (int i119 = 0; i119 < list27.size(); i119++) {
                                iaVar.I(((Integer) list27.get(i119)).intValue());
                            }
                        }
                    }
                    break;
                case TsExtractor.TS_STREAM_TYPE_MHAS /* 45 */:
                    int i120 = iArr[i14];
                    List list28 = (List) unsafe.getObject(obj, j10);
                    k5 k5Var28 = tb.f13618a;
                    if (list28 != null && !list28.isEmpty()) {
                        if (list28 instanceof qa) {
                            qa qaVar11 = (qa) list28;
                            iaVar.r(i120, 2);
                            int i121 = 0;
                            for (int i122 = 0; i122 < qaVar11.f13565m; i122++) {
                                qaVar11.b(i122);
                                i121 += 4;
                            }
                            iaVar.J(i121);
                            for (int i123 = 0; i123 < qaVar11.f13565m; i123++) {
                                iaVar.K(qaVar11.b(i123));
                            }
                        } else {
                            iaVar.r(i120, 2);
                            int i124 = 0;
                            for (int i125 = 0; i125 < list28.size(); i125++) {
                                ((Integer) list28.get(i125)).getClass();
                                i124 += 4;
                            }
                            iaVar.J(i124);
                            for (int i126 = 0; i126 < list28.size(); i126++) {
                                iaVar.K(((Integer) list28.get(i126)).intValue());
                            }
                        }
                    }
                    break;
                case 46:
                    int i127 = iArr[i14];
                    List list29 = (List) unsafe.getObject(obj, j10);
                    k5 k5Var29 = tb.f13618a;
                    if (list29 != null && !list29.isEmpty()) {
                        if (list29 instanceof cb) {
                            cb cbVar9 = (cb) list29;
                            iaVar.r(i127, 2);
                            int i128 = 0;
                            for (int i129 = 0; i129 < cbVar9.f13225m; i129++) {
                                cbVar9.b(i129);
                                i128 += 8;
                            }
                            iaVar.J(i128);
                            for (int i130 = 0; i130 < cbVar9.f13225m; i130++) {
                                iaVar.M(cbVar9.b(i130));
                            }
                        } else {
                            iaVar.r(i127, 2);
                            int i131 = 0;
                            for (int i132 = 0; i132 < list29.size(); i132++) {
                                ((Long) list29.get(i132)).getClass();
                                i131 += 8;
                            }
                            iaVar.J(i131);
                            for (int i133 = 0; i133 < list29.size(); i133++) {
                                iaVar.M(((Long) list29.get(i133)).longValue());
                            }
                        }
                    }
                    break;
                case 47:
                    int i134 = iArr[i14];
                    List list30 = (List) unsafe.getObject(obj, j10);
                    k5 k5Var30 = tb.f13618a;
                    if (list30 != null && !list30.isEmpty()) {
                        if (list30 instanceof qa) {
                            qa qaVar12 = (qa) list30;
                            iaVar.r(i134, 2);
                            int iO3 = 0;
                            for (int i135 = 0; i135 < qaVar12.f13565m; i135++) {
                                int iB2 = qaVar12.b(i135);
                                iO3 += ia.O((iB2 >> 31) ^ (iB2 + iB2));
                            }
                            iaVar.J(iO3);
                            for (int i136 = 0; i136 < qaVar12.f13565m; i136++) {
                                int iB3 = qaVar12.b(i136);
                                iaVar.J((iB3 >> 31) ^ (iB3 + iB3));
                            }
                        } else {
                            iaVar.r(i134, 2);
                            int iO4 = 0;
                            for (int i137 = 0; i137 < list30.size(); i137++) {
                                int iIntValue2 = ((Integer) list30.get(i137)).intValue();
                                iO4 += ia.O((iIntValue2 >> 31) ^ (iIntValue2 + iIntValue2));
                            }
                            iaVar.J(iO4);
                            for (int i138 = 0; i138 < list30.size(); i138++) {
                                int iIntValue3 = ((Integer) list30.get(i138)).intValue();
                                iaVar.J((iIntValue3 >> 31) ^ (iIntValue3 + iIntValue3));
                            }
                        }
                    }
                    break;
                case 48:
                    int i139 = iArr[i14];
                    List list31 = (List) unsafe.getObject(obj, j10);
                    k5 k5Var31 = tb.f13618a;
                    if (list31 != null && !list31.isEmpty()) {
                        if (list31 instanceof cb) {
                            cb cbVar10 = (cb) list31;
                            iaVar.r(i139, 2);
                            int iP10 = 0;
                            for (int i140 = 0; i140 < cbVar10.f13225m; i140++) {
                                long jB2 = cbVar10.b(i140);
                                iP10 += ia.P((jB2 >> 63) ^ (jB2 + jB2));
                            }
                            iaVar.J(iP10);
                            for (int i141 = 0; i141 < cbVar10.f13225m; i141++) {
                                long jB3 = cbVar10.b(i141);
                                iaVar.L((jB3 >> 63) ^ (jB3 + jB3));
                            }
                        } else {
                            iaVar.r(i139, 2);
                            int iP11 = 0;
                            for (int i142 = 0; i142 < list31.size(); i142++) {
                                long jLongValue2 = ((Long) list31.get(i142)).longValue();
                                iP11 += ia.P((jLongValue2 >> 63) ^ (jLongValue2 + jLongValue2));
                            }
                            iaVar.J(iP11);
                            for (int i143 = 0; i143 < list31.size(); i143++) {
                                long jLongValue3 = ((Long) list31.get(i143)).longValue();
                                iaVar.L((jLongValue3 >> 63) ^ (jLongValue3 + jLongValue3));
                            }
                        }
                    }
                    break;
                case 49:
                    int i144 = iArr[i14];
                    List list32 = (List) unsafe.getObject(obj, j10);
                    rb rbVarO2 = lbVar.o(i14);
                    k5 k5Var32 = tb.f13618a;
                    if (list32 != null && !list32.isEmpty()) {
                        for (int i145 = 0; i145 < list32.size(); i145++) {
                            jaVar.u(i144, list32.get(i145), rbVarO2);
                        }
                    }
                    break;
                case 50:
                    if (unsafe.getObject(obj, j10) != null) {
                        int i146 = i14 / 3;
                        lbVar.f13429b[i146 + i146].getClass();
                        throw new ClassCastException();
                    }
                    break;
                    break;
                case 51:
                    if (lbVar.j(i16, i14, obj)) {
                        jaVar.i(i16, ((Double) h.j(j10, obj)).doubleValue());
                    }
                    break;
                case 52:
                    if (lbVar.j(i16, i14, obj)) {
                        jaVar.h(((Float) h.j(j10, obj)).floatValue(), i16);
                    }
                    break;
                case 53:
                    if (lbVar.j(i16, i14, obj)) {
                        jaVar.f(i16, t(j10, obj));
                    }
                    break;
                case 54:
                    if (lbVar.j(i16, i14, obj)) {
                        jaVar.k(i16, t(j10, obj));
                    }
                    break;
                case 55:
                    if (lbVar.j(i16, i14, obj)) {
                        jaVar.l(i16, s(j10, obj));
                    }
                    break;
                case 56:
                    if (lbVar.j(i16, i14, obj)) {
                        jaVar.m(i16, t(j10, obj));
                    }
                    break;
                case 57:
                    if (lbVar.j(i16, i14, obj)) {
                        jaVar.n(i16, s(j10, obj));
                    }
                    break;
                case 58:
                    if (lbVar.j(i16, i14, obj)) {
                        jaVar.o(i16, ((Boolean) h.j(j10, obj)).booleanValue());
                    }
                    break;
                case 59:
                    if (lbVar.j(i16, i14, obj)) {
                        Object object2 = unsafe.getObject(obj, j10);
                        if (object2 instanceof String) {
                            iaVar.G(i16, (String) object2);
                        } else {
                            jaVar.p(i16, (ha) object2);
                        }
                    }
                    break;
                case 60:
                    if (lbVar.j(i16, i14, obj)) {
                        jaVar.t(i16, unsafe.getObject(obj, j10), lbVar.o(i14));
                    }
                    break;
                case 61:
                    if (lbVar.j(i16, i14, obj)) {
                        jaVar.p(i16, (ha) unsafe.getObject(obj, j10));
                    }
                    break;
                case 62:
                    if (lbVar.j(i16, i14, obj)) {
                        jaVar.q(i16, s(j10, obj));
                    }
                    break;
                case ColorSpace.MaxId /* 63 */:
                    if (lbVar.j(i16, i14, obj)) {
                        jaVar.j(i16, s(j10, obj));
                    }
                    break;
                case 64:
                    if (lbVar.j(i16, i14, obj)) {
                        jaVar.e(i16, s(j10, obj));
                    }
                    break;
                case 65:
                    if (lbVar.j(i16, i14, obj)) {
                        jaVar.g(i16, t(j10, obj));
                    }
                    break;
                case 66:
                    if (lbVar.j(i16, i14, obj)) {
                        jaVar.r(i16, s(j10, obj));
                    }
                    break;
                case MdtaMetadataEntry.TYPE_INDICATOR_INT32 /* 67 */:
                    if (lbVar.j(i16, i14, obj)) {
                        jaVar.s(i16, t(j10, obj));
                    }
                    break;
                case 68:
                    if (lbVar.j(i16, i14, obj)) {
                        jaVar.u(i16, unsafe.getObject(obj, j10), lbVar.o(i14));
                    }
                    break;
            }
            i14 += 3;
            i12 = 1048575;
            lbVar = this;
        }
    }

    @Override // com.google.android.gms.internal.cast.rb
    public final boolean f(Object obj) {
        int i10;
        int i11;
        int i12;
        int i13 = 0;
        int i14 = 0;
        int i15 = 1048575;
        while (i14 < this.f13432e) {
            int i16 = this.f13431d[i14];
            int[] iArr = this.f13428a;
            int i17 = iArr[i16];
            int iP = p(i16);
            int i18 = iArr[i16 + 2];
            int i19 = i18 & 1048575;
            int i20 = 1 << (i18 >>> 20);
            if (i19 != i15) {
                if (i19 != 1048575) {
                    i13 = f13427h.getInt(obj, i19);
                }
                i11 = i16;
                i12 = i13;
                i10 = i19;
            } else {
                int i21 = i13;
                i10 = i15;
                i11 = i16;
                i12 = i21;
            }
            if ((268435456 & iP) == 0 || v(i11, obj, i10, i12, i20)) {
                int iQ = q(iP);
                if (iQ != 9 && iQ != 17) {
                    if (iQ != 27) {
                        if (iQ == 60 || iQ == 68) {
                            if (!j(i17, i11, obj) || o(i11).f(h.j(iP & 1048575, obj))) {
                            }
                        } else if (iQ != 49) {
                            if (iQ == 50) {
                                h.j(iP & 1048575, obj).getClass();
                                throw new ClassCastException();
                            }
                        }
                        i14++;
                        i15 = i10;
                        i13 = i12;
                    }
                    List list = (List) h.j(iP & 1048575, obj);
                    if (list.isEmpty()) {
                        continue;
                    } else {
                        rb rbVarO = o(i11);
                        for (int i22 = 0; i22 < list.size(); i22++) {
                            if (rbVarO.f(list.get(i22))) {
                            }
                        }
                    }
                    i14++;
                    i15 = i10;
                    i13 = i12;
                } else if (!v(i11, obj, i10, i12, i20) || o(i11).f(h.j(iP & 1048575, obj))) {
                    i14++;
                    i15 = i10;
                    i13 = i12;
                }
            }
            return false;
        }
        return true;
    }

    /* JADX WARN: Code restructure failed: missing block: B:43:0x007d, code lost:
    
        continue;
     */
    /* JADX WARN: Removed duplicated region for block: B:28:0x006a  */
    @Override // com.google.android.gms.internal.cast.rb
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void g(java.lang.Object r8) {
        /*
            Method dump skipped, instruction units count: 216
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.cast.lb.g(java.lang.Object):void");
    }

    public final boolean h(int i10, Object obj) {
        int i11 = this.f13428a[i10 + 2];
        long j10 = i11 & 1048575;
        if (j10 == 1048575) {
            int iP = p(i10);
            long j11 = iP & 1048575;
            switch (q(iP)) {
                case 0:
                    if (Double.doubleToRawLongBits(h.f13268c.f(j11, obj)) == 0) {
                        return false;
                    }
                    break;
                case 1:
                    if (Float.floatToRawIntBits(h.f13268c.d(j11, obj)) == 0) {
                        return false;
                    }
                    break;
                case 2:
                    if (h.h(j11, obj) == 0) {
                        return false;
                    }
                    break;
                case 3:
                    if (h.h(j11, obj) == 0) {
                        return false;
                    }
                    break;
                case 4:
                    if (h.f(j11, obj) == 0) {
                        return false;
                    }
                    break;
                case 5:
                    if (h.h(j11, obj) == 0) {
                        return false;
                    }
                    break;
                case 6:
                    if (h.f(j11, obj) == 0) {
                        return false;
                    }
                    break;
                case 7:
                    return h.f13268c.b(j11, obj);
                case 8:
                    Object objJ = h.j(j11, obj);
                    if (objJ instanceof String) {
                        if (((String) objJ).isEmpty()) {
                            return false;
                        }
                    } else {
                        if (!(objJ instanceof ha)) {
                            throw new IllegalArgumentException();
                        }
                        if (ha.f13297m.equals(objJ)) {
                            return false;
                        }
                    }
                case 9:
                    if (h.j(j11, obj) == null) {
                        return false;
                    }
                    break;
                case 10:
                    if (ha.f13297m.equals(h.j(j11, obj))) {
                        return false;
                    }
                    break;
                case 11:
                    if (h.f(j11, obj) == 0) {
                        return false;
                    }
                    break;
                case 12:
                    if (h.f(j11, obj) == 0) {
                        return false;
                    }
                    break;
                case 13:
                    if (h.f(j11, obj) == 0) {
                        return false;
                    }
                    break;
                case 14:
                    if (h.h(j11, obj) == 0) {
                        return false;
                    }
                    break;
                case 15:
                    if (h.f(j11, obj) == 0) {
                        return false;
                    }
                    break;
                case 16:
                    if (h.h(j11, obj) == 0) {
                        return false;
                    }
                    break;
                case 17:
                    if (h.j(j11, obj) == null) {
                        return false;
                    }
                    break;
                default:
                    throw new IllegalArgumentException();
            }
        } else if (((1 << (i11 >>> 20)) & h.f(j10, obj)) == 0) {
            return false;
        }
        return true;
    }

    public final void i(int i10, Object obj) {
        int i11 = this.f13428a[i10 + 2];
        long j10 = 1048575 & i11;
        if (j10 == 1048575) {
            return;
        }
        h.g((1 << (i11 >>> 20)) | h.f(j10, obj), j10, obj);
    }

    public final boolean j(int i10, int i11, Object obj) {
        return h.f((long) (this.f13428a[i11 + 2] & 1048575), obj) == i10;
    }

    public final void m(int i10, Object obj, Object obj2) {
        if (h(i10, obj2)) {
            int iP = p(i10) & 1048575;
            Unsafe unsafe = f13427h;
            long j10 = iP;
            Object object = unsafe.getObject(obj2, j10);
            if (object == null) {
                int i11 = this.f13428a[i10];
                String string = obj2.toString();
                StringBuilder sb2 = new StringBuilder(String.valueOf(i11).length() + 38 + string.length());
                sb2.append("Source subfield ");
                sb2.append(i11);
                sb2.append(" is present but null: ");
                sb2.append(string);
                throw new IllegalStateException(sb2.toString());
            }
            rb rbVarO = o(i10);
            if (!h(i10, obj)) {
                if (r(object)) {
                    Object objZza = rbVarO.zza();
                    rbVarO.c(objZza, object);
                    unsafe.putObject(obj, j10, objZza);
                } else {
                    unsafe.putObject(obj, j10, object);
                }
                i(i10, obj);
                return;
            }
            Object object2 = unsafe.getObject(obj, j10);
            if (!r(object2)) {
                Object objZza2 = rbVarO.zza();
                rbVarO.c(objZza2, object2);
                unsafe.putObject(obj, j10, objZza2);
                object2 = objZza2;
            }
            rbVarO.c(object2, object);
        }
    }

    public final void n(int i10, Object obj, Object obj2) {
        int[] iArr = this.f13428a;
        int i11 = iArr[i10];
        if (j(i11, i10, obj2)) {
            int iP = p(i10) & 1048575;
            Unsafe unsafe = f13427h;
            long j10 = iP;
            Object object = unsafe.getObject(obj2, j10);
            if (object == null) {
                int i12 = iArr[i10];
                String string = obj2.toString();
                StringBuilder sb2 = new StringBuilder(String.valueOf(i12).length() + 38 + string.length());
                sb2.append("Source subfield ");
                sb2.append(i12);
                sb2.append(" is present but null: ");
                sb2.append(string);
                throw new IllegalStateException(sb2.toString());
            }
            rb rbVarO = o(i10);
            if (!j(i11, i10, obj)) {
                if (r(object)) {
                    Object objZza = rbVarO.zza();
                    rbVarO.c(objZza, object);
                    unsafe.putObject(obj, j10, objZza);
                } else {
                    unsafe.putObject(obj, j10, object);
                }
                h.g(i11, iArr[i10 + 2] & 1048575, obj);
                return;
            }
            Object object2 = unsafe.getObject(obj, j10);
            if (!r(object2)) {
                Object objZza2 = rbVarO.zza();
                rbVarO.c(objZza2, object2);
                unsafe.putObject(obj, j10, objZza2);
                object2 = objZza2;
            }
            rbVarO.c(object2, object);
        }
    }

    public final rb o(int i10) {
        int i11 = i10 / 3;
        int i12 = i11 + i11;
        Object[] objArr = this.f13429b;
        rb rbVar = (rb) objArr[i12];
        if (rbVar != null) {
            return rbVar;
        }
        rb rbVarA = ob.f13531c.a((Class) objArr[i12 + 1]);
        objArr[i12] = rbVarA;
        return rbVarA;
    }

    public final int p(int i10) {
        return this.f13428a[i10 + 1];
    }

    public final boolean u(pa paVar, pa paVar2, int i10) {
        return h(i10, paVar) == h(i10, paVar2);
    }

    public final boolean v(int i10, Object obj, int i11, int i12, int i13) {
        return i11 == 1048575 ? h(i10, obj) : (i12 & i13) != 0;
    }

    @Override // com.google.android.gms.internal.cast.rb
    public final Object zza() {
        return (pa) ((pa) this.f13430c).k(4, null);
    }
}
