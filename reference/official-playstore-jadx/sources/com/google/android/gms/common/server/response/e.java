package com.google.android.gms.common.server.response;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Base64;
import android.util.SparseArray;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader$ParseException;
import com.google.android.gms.common.internal.t;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/* JADX INFO: loaded from: classes4.dex */
public final class e extends d {
    public static final Parcelable.Creator<e> CREATOR = new android.support.v4.media.d(24);

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final int f12958i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final Parcel f12959l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final int f12960m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final j f12961n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final String f12962o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public int f12963p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public int f12964q;

    public e(int i10, Parcel parcel, j jVar) {
        this.f12958i = i10;
        t.i(parcel);
        this.f12959l = parcel;
        this.f12960m = 2;
        this.f12961n = jVar;
        this.f12962o = jVar == null ? null : jVar.f12973m;
        this.f12963p = 2;
    }

    public static void e(StringBuilder sb2, Map map, Parcel parcel) {
        BigInteger bigInteger;
        Parcel parcelObtain;
        BigInteger[] bigIntegerArr;
        float[] fArrCreateFloatArray;
        double[] dArrCreateDoubleArray;
        BigDecimal[] bigDecimalArr;
        boolean[] zArrCreateBooleanArray;
        Parcel[] parcelArr;
        BigInteger bigInteger2;
        SparseArray sparseArray = new SparseArray();
        for (Map.Entry entry : map.entrySet()) {
            sparseArray.put(((a) entry.getValue()).f12953q, entry);
        }
        sb2.append('{');
        int iL0 = xc.d.L0(parcel);
        boolean z = false;
        while (parcel.dataPosition() < iL0) {
            int i10 = parcel.readInt();
            Map.Entry entry2 = (Map.Entry) sparseArray.get((char) i10);
            if (entry2 != null) {
                if (z) {
                    sb2.append(",");
                }
                String str = (String) entry2.getKey();
                a aVar = (a) entry2.getValue();
                sb2.append("\"");
                sb2.append(str);
                sb2.append("\":");
                b bVar = aVar.f12957u;
                String str2 = aVar.f12955s;
                int i11 = aVar.f12950n;
                if (bVar != null) {
                    switch (i11) {
                        case 0:
                            g(sb2, aVar, c.zaD(aVar, Integer.valueOf(xc.d.z0(i10, parcel))));
                            break;
                        case 1:
                            int iC0 = xc.d.C0(i10, parcel);
                            int iDataPosition = parcel.dataPosition();
                            if (iC0 == 0) {
                                bigInteger2 = null;
                            } else {
                                byte[] bArrCreateByteArray = parcel.createByteArray();
                                parcel.setDataPosition(iDataPosition + iC0);
                                bigInteger2 = new BigInteger(bArrCreateByteArray);
                            }
                            g(sb2, aVar, c.zaD(aVar, bigInteger2));
                            break;
                        case 2:
                            g(sb2, aVar, c.zaD(aVar, Long.valueOf(xc.d.B0(i10, parcel))));
                            break;
                        case 3:
                            g(sb2, aVar, c.zaD(aVar, Float.valueOf(xc.d.x0(i10, parcel))));
                            break;
                        case 4:
                            g(sb2, aVar, c.zaD(aVar, Double.valueOf(xc.d.w0(i10, parcel))));
                            break;
                        case 5:
                            g(sb2, aVar, c.zaD(aVar, xc.d.O(i10, parcel)));
                            break;
                        case 6:
                            g(sb2, aVar, c.zaD(aVar, Boolean.valueOf(xc.d.u0(i10, parcel))));
                            break;
                        case 7:
                            g(sb2, aVar, c.zaD(aVar, xc.d.V(i10, parcel)));
                            break;
                        case 8:
                        case 9:
                            g(sb2, aVar, c.zaD(aVar, xc.d.Q(i10, parcel)));
                            break;
                        case 10:
                            Bundle bundleP = xc.d.P(i10, parcel);
                            HashMap map2 = new HashMap();
                            for (String str3 : bundleP.keySet()) {
                                String string = bundleP.getString(str3);
                                t.i(string);
                                map2.put(str3, string);
                            }
                            g(sb2, aVar, c.zaD(aVar, map2));
                            break;
                        case 11:
                            throw new IllegalArgumentException("Method does not accept concrete type.");
                        default:
                            throw new IllegalArgumentException(androidx.compose.foundation.c.p(i11, "Unknown field out type = ", new StringBuilder(String.valueOf(i11).length() + 25)));
                    }
                } else if (aVar.f12951o) {
                    sb2.append("[");
                    switch (i11) {
                        case 0:
                            int[] iArrR = xc.d.R(i10, parcel);
                            int length = iArrR.length;
                            for (int i12 = 0; i12 < length; i12++) {
                                if (i12 != 0) {
                                    sb2.append(",");
                                }
                                sb2.append(iArrR[i12]);
                            }
                            break;
                        case 1:
                            int iC02 = xc.d.C0(i10, parcel);
                            int iDataPosition2 = parcel.dataPosition();
                            if (iC02 == 0) {
                                bigIntegerArr = null;
                            } else {
                                int i13 = parcel.readInt();
                                bigIntegerArr = new BigInteger[i13];
                                for (int i14 = 0; i14 < i13; i14++) {
                                    bigIntegerArr[i14] = new BigInteger(parcel.createByteArray());
                                }
                                parcel.setDataPosition(iDataPosition2 + iC02);
                            }
                            int length2 = bigIntegerArr.length;
                            for (int i15 = 0; i15 < length2; i15++) {
                                if (i15 != 0) {
                                    sb2.append(",");
                                }
                                sb2.append(bigIntegerArr[i15]);
                            }
                            break;
                        case 2:
                            long[] jArrS = xc.d.S(i10, parcel);
                            int length3 = jArrS.length;
                            for (int i16 = 0; i16 < length3; i16++) {
                                if (i16 != 0) {
                                    sb2.append(",");
                                }
                                sb2.append(jArrS[i16]);
                            }
                            break;
                        case 3:
                            int iC03 = xc.d.C0(i10, parcel);
                            int iDataPosition3 = parcel.dataPosition();
                            if (iC03 == 0) {
                                fArrCreateFloatArray = null;
                            } else {
                                fArrCreateFloatArray = parcel.createFloatArray();
                                parcel.setDataPosition(iDataPosition3 + iC03);
                            }
                            int length4 = fArrCreateFloatArray.length;
                            for (int i17 = 0; i17 < length4; i17++) {
                                if (i17 != 0) {
                                    sb2.append(",");
                                }
                                sb2.append(fArrCreateFloatArray[i17]);
                            }
                            break;
                        case 4:
                            int iC04 = xc.d.C0(i10, parcel);
                            int iDataPosition4 = parcel.dataPosition();
                            if (iC04 == 0) {
                                dArrCreateDoubleArray = null;
                            } else {
                                dArrCreateDoubleArray = parcel.createDoubleArray();
                                parcel.setDataPosition(iDataPosition4 + iC04);
                            }
                            int length5 = dArrCreateDoubleArray.length;
                            for (int i18 = 0; i18 < length5; i18++) {
                                if (i18 != 0) {
                                    sb2.append(",");
                                }
                                sb2.append(dArrCreateDoubleArray[i18]);
                            }
                            break;
                        case 5:
                            int iC05 = xc.d.C0(i10, parcel);
                            int iDataPosition5 = parcel.dataPosition();
                            if (iC05 == 0) {
                                bigDecimalArr = null;
                            } else {
                                int i19 = parcel.readInt();
                                bigDecimalArr = new BigDecimal[i19];
                                for (int i20 = 0; i20 < i19; i20++) {
                                    bigDecimalArr[i20] = new BigDecimal(new BigInteger(parcel.createByteArray()), parcel.readInt());
                                }
                                parcel.setDataPosition(iDataPosition5 + iC05);
                            }
                            int length6 = bigDecimalArr.length;
                            for (int i21 = 0; i21 < length6; i21++) {
                                if (i21 != 0) {
                                    sb2.append(",");
                                }
                                sb2.append(bigDecimalArr[i21]);
                            }
                            break;
                        case 6:
                            int iC06 = xc.d.C0(i10, parcel);
                            int iDataPosition6 = parcel.dataPosition();
                            if (iC06 == 0) {
                                zArrCreateBooleanArray = null;
                            } else {
                                zArrCreateBooleanArray = parcel.createBooleanArray();
                                parcel.setDataPosition(iDataPosition6 + iC06);
                            }
                            int length7 = zArrCreateBooleanArray.length;
                            for (int i22 = 0; i22 < length7; i22++) {
                                if (i22 != 0) {
                                    sb2.append(",");
                                }
                                sb2.append(zArrCreateBooleanArray[i22]);
                            }
                            break;
                        case 7:
                            String[] strArrW = xc.d.W(i10, parcel);
                            int length8 = strArrW.length;
                            for (int i23 = 0; i23 < length8; i23++) {
                                if (i23 != 0) {
                                    sb2.append(",");
                                }
                                sb2.append("\"");
                                sb2.append(strArrW[i23]);
                                sb2.append("\"");
                            }
                            break;
                        case 8:
                        case 9:
                        case 10:
                            throw new UnsupportedOperationException("List of type BASE64, BASE64_URL_SAFE, or STRING_MAP is not supported");
                        case 11:
                            int iC07 = xc.d.C0(i10, parcel);
                            int iDataPosition7 = parcel.dataPosition();
                            if (iC07 == 0) {
                                parcelArr = null;
                            } else {
                                int i24 = parcel.readInt();
                                Parcel[] parcelArr2 = new Parcel[i24];
                                for (int i25 = 0; i25 < i24; i25++) {
                                    int i26 = parcel.readInt();
                                    if (i26 != 0) {
                                        int iDataPosition8 = parcel.dataPosition();
                                        Parcel parcelObtain2 = Parcel.obtain();
                                        parcelObtain2.appendFrom(parcel, iDataPosition8, i26);
                                        parcelArr2[i25] = parcelObtain2;
                                        parcel.setDataPosition(iDataPosition8 + i26);
                                    } else {
                                        parcelArr2[i25] = null;
                                    }
                                }
                                parcel.setDataPosition(iDataPosition7 + iC07);
                                parcelArr = parcelArr2;
                            }
                            int length9 = parcelArr.length;
                            for (int i27 = 0; i27 < length9; i27++) {
                                if (i27 > 0) {
                                    sb2.append(",");
                                }
                                parcelArr[i27].setDataPosition(0);
                                t.i(str2);
                                t.i(aVar.f12956t);
                                Map map3 = (Map) aVar.f12956t.f12972l.get(str2);
                                t.i(map3);
                                e(sb2, map3, parcelArr[i27]);
                            }
                            break;
                        default:
                            throw new IllegalStateException("Unknown field type out.");
                    }
                    sb2.append("]");
                } else {
                    switch (i11) {
                        case 0:
                            sb2.append(xc.d.z0(i10, parcel));
                            break;
                        case 1:
                            int iC08 = xc.d.C0(i10, parcel);
                            int iDataPosition9 = parcel.dataPosition();
                            if (iC08 == 0) {
                                bigInteger = null;
                            } else {
                                byte[] bArrCreateByteArray2 = parcel.createByteArray();
                                parcel.setDataPosition(iDataPosition9 + iC08);
                                bigInteger = new BigInteger(bArrCreateByteArray2);
                            }
                            sb2.append(bigInteger);
                            break;
                        case 2:
                            sb2.append(xc.d.B0(i10, parcel));
                            break;
                        case 3:
                            sb2.append(xc.d.x0(i10, parcel));
                            break;
                        case 4:
                            sb2.append(xc.d.w0(i10, parcel));
                            break;
                        case 5:
                            sb2.append(xc.d.O(i10, parcel));
                            break;
                        case 6:
                            sb2.append(xc.d.u0(i10, parcel));
                            break;
                        case 7:
                            String strV = xc.d.V(i10, parcel);
                            sb2.append("\"");
                            sb2.append(c4.e.b(strV));
                            sb2.append("\"");
                            break;
                        case 8:
                            byte[] bArrQ = xc.d.Q(i10, parcel);
                            sb2.append("\"");
                            sb2.append(bArrQ == null ? null : Base64.encodeToString(bArrQ, 0));
                            sb2.append("\"");
                            break;
                        case 9:
                            byte[] bArrQ2 = xc.d.Q(i10, parcel);
                            sb2.append("\"");
                            sb2.append(bArrQ2 == null ? null : Base64.encodeToString(bArrQ2, 10));
                            sb2.append("\"");
                            break;
                        case 10:
                            Bundle bundleP2 = xc.d.P(i10, parcel);
                            Set<String> setKeySet = bundleP2.keySet();
                            sb2.append("{");
                            boolean z5 = true;
                            for (String str4 : setKeySet) {
                                if (!z5) {
                                    sb2.append(",");
                                }
                                sb2.append("\"");
                                sb2.append(str4);
                                sb2.append("\":\"");
                                sb2.append(c4.e.b(bundleP2.getString(str4)));
                                sb2.append("\"");
                                z5 = false;
                            }
                            sb2.append("}");
                            break;
                        case 11:
                            int iC09 = xc.d.C0(i10, parcel);
                            int iDataPosition10 = parcel.dataPosition();
                            if (iC09 == 0) {
                                parcelObtain = null;
                            } else {
                                parcelObtain = Parcel.obtain();
                                parcelObtain.appendFrom(parcel, iDataPosition10, iC09);
                                parcel.setDataPosition(iDataPosition10 + iC09);
                            }
                            parcelObtain.setDataPosition(0);
                            t.i(str2);
                            t.i(aVar.f12956t);
                            Map map4 = (Map) aVar.f12956t.f12972l.get(str2);
                            t.i(map4);
                            e(sb2, map4, parcelObtain);
                            break;
                        default:
                            throw new IllegalStateException("Unknown field type out");
                    }
                }
                z = true;
            }
        }
        if (parcel.dataPosition() != iL0) {
            throw new SafeParcelReader$ParseException(androidx.compose.foundation.c.p(iL0, "Overread allowed size end=", new StringBuilder(String.valueOf(iL0).length() + 26)), parcel);
        }
        sb2.append('}');
    }

    public static final void f(StringBuilder sb2, int i10, Object obj) {
        switch (i10) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
                sb2.append(obj);
                return;
            case 7:
                sb2.append("\"");
                t.i(obj);
                sb2.append(c4.e.b(obj.toString()));
                sb2.append("\"");
                return;
            case 8:
                sb2.append("\"");
                byte[] bArr = (byte[]) obj;
                sb2.append(bArr != null ? Base64.encodeToString(bArr, 0) : null);
                sb2.append("\"");
                return;
            case 9:
                sb2.append("\"");
                byte[] bArr2 = (byte[]) obj;
                sb2.append(bArr2 != null ? Base64.encodeToString(bArr2, 10) : null);
                sb2.append("\"");
                return;
            case 10:
                t.i(obj);
                c4.d.e(sb2, (HashMap) obj);
                return;
            case 11:
                throw new IllegalArgumentException("Method does not accept concrete type.");
            default:
                throw new IllegalArgumentException(androidx.compose.foundation.c.p(i10, "Unknown type = ", new StringBuilder(String.valueOf(i10).length() + 15)));
        }
    }

    public static final void g(StringBuilder sb2, a aVar, Object obj) {
        boolean z = aVar.f12949m;
        int i10 = aVar.f12948l;
        if (!z) {
            f(sb2, i10, obj);
            return;
        }
        ArrayList arrayList = (ArrayList) obj;
        sb2.append("[");
        int size = arrayList.size();
        for (int i11 = 0; i11 < size; i11++) {
            if (i11 != 0) {
                sb2.append(",");
            }
            f(sb2, i10, arrayList.get(i11));
        }
        sb2.append("]");
    }

    @Override // com.google.android.gms.common.server.response.c
    public final void addConcreteTypeArrayInternal(a aVar, String str, ArrayList arrayList) {
        d(aVar);
        ArrayList arrayList2 = new ArrayList();
        t.i(arrayList);
        arrayList.size();
        int size = arrayList.size();
        for (int i10 = 0; i10 < size; i10++) {
            arrayList2.add(((e) ((c) arrayList.get(i10))).c());
        }
        int i11 = aVar.f12953q;
        Parcel parcel = this.f12959l;
        int iM0 = a.a.m0(i11, parcel);
        int size2 = arrayList2.size();
        parcel.writeInt(size2);
        for (int i12 = 0; i12 < size2; i12++) {
            Parcel parcel2 = (Parcel) arrayList2.get(i12);
            if (parcel2 != null) {
                parcel.writeInt(parcel2.dataSize());
                parcel.appendFrom(parcel2, 0, parcel2.dataSize());
            } else {
                parcel.writeInt(0);
            }
        }
        a.a.n0(iM0, parcel);
    }

    @Override // com.google.android.gms.common.server.response.c
    public final void addConcreteTypeInternal(a aVar, String str, c cVar) {
        d(aVar);
        Parcel parcelC = ((e) cVar).c();
        int i10 = aVar.f12953q;
        Parcel parcel = this.f12959l;
        if (parcelC == null) {
            a.a.l0(parcel, i10, 0);
            return;
        }
        int iM0 = a.a.m0(i10, parcel);
        parcel.appendFrom(parcelC, 0, parcelC.dataSize());
        a.a.n0(iM0, parcel);
    }

    public final Parcel c() {
        int i10 = this.f12963p;
        Parcel parcel = this.f12959l;
        if (i10 != 0) {
            if (i10 != 1) {
                return parcel;
            }
            a.a.n0(this.f12964q, parcel);
            this.f12963p = 2;
            return parcel;
        }
        int iM0 = a.a.m0(20293, parcel);
        this.f12964q = iM0;
        a.a.n0(iM0, parcel);
        this.f12963p = 2;
        return parcel;
    }

    public final void d(a aVar) {
        if (aVar.f12953q == -1) {
            throw new IllegalStateException("Field does not have a valid safe parcelable field id.");
        }
        Parcel parcel = this.f12959l;
        if (parcel == null) {
            throw new IllegalStateException("Internal Parcel object is null.");
        }
        int i10 = this.f12963p;
        if (i10 != 0) {
            if (i10 != 1) {
                throw new IllegalStateException("Attempted to parse JSON with a SafeParcelResponse object that is already filled with data.");
            }
        } else {
            this.f12964q = a.a.m0(20293, parcel);
            this.f12963p = 1;
        }
    }

    @Override // com.google.android.gms.common.server.response.c
    public final Map getFieldMappings() {
        j jVar = this.f12961n;
        if (jVar == null) {
            return null;
        }
        String str = this.f12962o;
        t.i(str);
        return (Map) jVar.f12972l.get(str);
    }

    @Override // com.google.android.gms.common.server.response.d, com.google.android.gms.common.server.response.c
    public final Object getValueObject(String str) {
        throw new UnsupportedOperationException("Converting to JSON does not require this method.");
    }

    @Override // com.google.android.gms.common.server.response.d, com.google.android.gms.common.server.response.c
    public final boolean isPrimitiveFieldSet(String str) {
        throw new UnsupportedOperationException("Converting to JSON does not require this method.");
    }

    @Override // com.google.android.gms.common.server.response.c
    public final void setBooleanInternal(a aVar, String str, boolean z) {
        d(aVar);
        int i10 = aVar.f12953q;
        Parcel parcel = this.f12959l;
        a.a.l0(parcel, i10, 4);
        parcel.writeInt(z ? 1 : 0);
    }

    @Override // com.google.android.gms.common.server.response.c
    public final void setDecodedBytesInternal(a aVar, String str, byte[] bArr) {
        d(aVar);
        a.a.a0(this.f12959l, aVar.f12953q, bArr, true);
    }

    @Override // com.google.android.gms.common.server.response.c
    public final void setIntegerInternal(a aVar, String str, int i10) {
        d(aVar);
        int i11 = aVar.f12953q;
        Parcel parcel = this.f12959l;
        a.a.l0(parcel, i11, 4);
        parcel.writeInt(i10);
    }

    @Override // com.google.android.gms.common.server.response.c
    public final void setLongInternal(a aVar, String str, long j10) {
        d(aVar);
        int i10 = aVar.f12953q;
        Parcel parcel = this.f12959l;
        a.a.l0(parcel, i10, 8);
        parcel.writeLong(j10);
    }

    @Override // com.google.android.gms.common.server.response.c
    public final void setStringInternal(a aVar, String str, String str2) {
        d(aVar);
        a.a.g0(this.f12959l, aVar.f12953q, str2, true);
    }

    @Override // com.google.android.gms.common.server.response.c
    public final void setStringMapInternal(a aVar, String str, Map map) {
        d(aVar);
        Bundle bundle = new Bundle();
        t.i(map);
        for (String str2 : map.keySet()) {
            bundle.putString(str2, (String) map.get(str2));
        }
        a.a.Z(this.f12959l, aVar.f12953q, bundle, true);
    }

    @Override // com.google.android.gms.common.server.response.c
    public final void setStringsInternal(a aVar, String str, ArrayList arrayList) {
        d(aVar);
        t.i(arrayList);
        int size = arrayList.size();
        String[] strArr = new String[size];
        for (int i10 = 0; i10 < size; i10++) {
            strArr[i10] = (String) arrayList.get(i10);
        }
        a.a.h0(this.f12959l, aVar.f12953q, strArr, true);
    }

    @Override // com.google.android.gms.common.server.response.c
    public final String toString() {
        j jVar = this.f12961n;
        t.j(jVar, "Cannot convert to JSON on client side.");
        Parcel parcelC = c();
        parcelC.setDataPosition(0);
        StringBuilder sb2 = new StringBuilder(100);
        String str = this.f12962o;
        t.i(str);
        Map map = (Map) jVar.f12972l.get(str);
        t.i(map);
        e(sb2, map, parcelC);
        return sb2.toString();
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i10) {
        int iM0 = a.a.m0(20293, parcel);
        a.a.l0(parcel, 1, 4);
        parcel.writeInt(this.f12958i);
        Parcel parcelC = c();
        if (parcelC != null) {
            int iM02 = a.a.m0(2, parcel);
            parcel.appendFrom(parcelC, 0, parcelC.dataSize());
            a.a.n0(iM02, parcel);
        }
        a.a.f0(parcel, 3, this.f12960m != 0 ? this.f12961n : null, i10, false);
        a.a.n0(iM0, parcel);
    }

    @Override // com.google.android.gms.common.server.response.c
    public final void zaA(a aVar, String str, BigDecimal bigDecimal) {
        d(aVar);
        int i10 = aVar.f12953q;
        Parcel parcel = this.f12959l;
        if (bigDecimal == null) {
            a.a.l0(parcel, i10, 0);
            return;
        }
        int iM0 = a.a.m0(i10, parcel);
        parcel.writeByteArray(bigDecimal.unscaledValue().toByteArray());
        parcel.writeInt(bigDecimal.scale());
        a.a.n0(iM0, parcel);
    }

    @Override // com.google.android.gms.common.server.response.c
    public final void zaB(a aVar, String str, ArrayList arrayList) {
        d(aVar);
        t.i(arrayList);
        int size = arrayList.size();
        BigDecimal[] bigDecimalArr = new BigDecimal[size];
        for (int i10 = 0; i10 < size; i10++) {
            bigDecimalArr[i10] = (BigDecimal) arrayList.get(i10);
        }
        int i11 = aVar.f12953q;
        Parcel parcel = this.f12959l;
        int iM0 = a.a.m0(i11, parcel);
        parcel.writeInt(size);
        for (int i12 = 0; i12 < size; i12++) {
            parcel.writeByteArray(bigDecimalArr[i12].unscaledValue().toByteArray());
            parcel.writeInt(bigDecimalArr[i12].scale());
        }
        a.a.n0(iM0, parcel);
    }

    @Override // com.google.android.gms.common.server.response.c
    public final void zaC(a aVar, String str, ArrayList arrayList) {
        d(aVar);
        t.i(arrayList);
        int size = arrayList.size();
        boolean[] zArr = new boolean[size];
        for (int i10 = 0; i10 < size; i10++) {
            zArr[i10] = ((Boolean) arrayList.get(i10)).booleanValue();
        }
        int i11 = aVar.f12953q;
        Parcel parcel = this.f12959l;
        int iM0 = a.a.m0(i11, parcel);
        parcel.writeBooleanArray(zArr);
        a.a.n0(iM0, parcel);
    }

    @Override // com.google.android.gms.common.server.response.c
    public final void zas(a aVar, String str, ArrayList arrayList) {
        d(aVar);
        t.i(arrayList);
        int size = arrayList.size();
        int[] iArr = new int[size];
        for (int i10 = 0; i10 < size; i10++) {
            iArr[i10] = ((Integer) arrayList.get(i10)).intValue();
        }
        a.a.c0(this.f12959l, aVar.f12953q, iArr, true);
    }

    @Override // com.google.android.gms.common.server.response.c
    public final void zat(a aVar, String str, BigInteger bigInteger) {
        d(aVar);
        int i10 = aVar.f12953q;
        Parcel parcel = this.f12959l;
        if (bigInteger == null) {
            a.a.l0(parcel, i10, 0);
            return;
        }
        int iM0 = a.a.m0(i10, parcel);
        parcel.writeByteArray(bigInteger.toByteArray());
        a.a.n0(iM0, parcel);
    }

    @Override // com.google.android.gms.common.server.response.c
    public final void zau(a aVar, String str, ArrayList arrayList) {
        d(aVar);
        t.i(arrayList);
        int size = arrayList.size();
        BigInteger[] bigIntegerArr = new BigInteger[size];
        for (int i10 = 0; i10 < size; i10++) {
            bigIntegerArr[i10] = (BigInteger) arrayList.get(i10);
        }
        int i11 = aVar.f12953q;
        Parcel parcel = this.f12959l;
        int iM0 = a.a.m0(i11, parcel);
        parcel.writeInt(size);
        for (int i12 = 0; i12 < size; i12++) {
            parcel.writeByteArray(bigIntegerArr[i12].toByteArray());
        }
        a.a.n0(iM0, parcel);
    }

    @Override // com.google.android.gms.common.server.response.c
    public final void zav(a aVar, String str, ArrayList arrayList) {
        d(aVar);
        t.i(arrayList);
        int size = arrayList.size();
        long[] jArr = new long[size];
        for (int i10 = 0; i10 < size; i10++) {
            jArr[i10] = ((Long) arrayList.get(i10)).longValue();
        }
        a.a.e0(this.f12959l, aVar.f12953q, jArr, true);
    }

    @Override // com.google.android.gms.common.server.response.c
    public final void zaw(a aVar, String str, float f10) {
        d(aVar);
        int i10 = aVar.f12953q;
        Parcel parcel = this.f12959l;
        a.a.l0(parcel, i10, 4);
        parcel.writeFloat(f10);
    }

    @Override // com.google.android.gms.common.server.response.c
    public final void zax(a aVar, String str, ArrayList arrayList) {
        d(aVar);
        t.i(arrayList);
        int size = arrayList.size();
        float[] fArr = new float[size];
        for (int i10 = 0; i10 < size; i10++) {
            fArr[i10] = ((Float) arrayList.get(i10)).floatValue();
        }
        int i11 = aVar.f12953q;
        Parcel parcel = this.f12959l;
        int iM0 = a.a.m0(i11, parcel);
        parcel.writeFloatArray(fArr);
        a.a.n0(iM0, parcel);
    }

    @Override // com.google.android.gms.common.server.response.c
    public final void zay(a aVar, String str, double d4) {
        d(aVar);
        int i10 = aVar.f12953q;
        Parcel parcel = this.f12959l;
        a.a.l0(parcel, i10, 8);
        parcel.writeDouble(d4);
    }

    @Override // com.google.android.gms.common.server.response.c
    public final void zaz(a aVar, String str, ArrayList arrayList) {
        d(aVar);
        t.i(arrayList);
        int size = arrayList.size();
        double[] dArr = new double[size];
        for (int i10 = 0; i10 < size; i10++) {
            dArr[i10] = ((Double) arrayList.get(i10)).doubleValue();
        }
        int i11 = aVar.f12953q;
        Parcel parcel = this.f12959l;
        int iM0 = a.a.m0(i11, parcel);
        parcel.writeDoubleArray(dArr);
        a.a.n0(iM0, parcel);
    }
}
