package com.google.android.gms.common.server.response;

import android.util.Base64;
import com.google.android.gms.common.internal.t;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes4.dex */
public abstract class c {
    public static final void b(StringBuilder sb2, a aVar, Object obj) {
        int i10 = aVar.f12948l;
        if (i10 == 11) {
            Class cls = aVar.f12954r;
            t.i(cls);
            sb2.append(((c) cls.cast(obj)).toString());
        } else {
            if (i10 != 7) {
                sb2.append(obj);
                return;
            }
            sb2.append("\"");
            sb2.append(c4.e.b((String) obj));
            sb2.append("\"");
        }
    }

    public static final Object zaD(a aVar, Object obj) {
        b bVar = aVar.f12957u;
        return bVar != null ? bVar.b(obj) : obj;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void a(a aVar, Object obj) {
        int i10 = aVar.f12950n;
        b bVar = aVar.f12957u;
        t.i(bVar);
        Integer numA = bVar.a(obj);
        t.i(numA);
        String str = aVar.f12952p;
        switch (i10) {
            case 0:
                setIntegerInternal(aVar, str, numA.intValue());
                return;
            case 1:
                zat(aVar, str, (BigInteger) numA);
                return;
            case 2:
                setLongInternal(aVar, str, ((Long) numA).longValue());
                return;
            case 3:
            default:
                throw new IllegalStateException(androidx.compose.foundation.c.p(i10, "Unsupported type for conversion: ", new StringBuilder(String.valueOf(i10).length() + 33)));
            case 4:
                zay(aVar, str, ((Double) numA).doubleValue());
                return;
            case 5:
                zaA(aVar, str, (BigDecimal) numA);
                return;
            case 6:
                setBooleanInternal(aVar, str, ((Boolean) numA).booleanValue());
                return;
            case 7:
                setStringInternal(aVar, str, (String) numA);
                return;
            case 8:
            case 9:
                setDecodedBytesInternal(aVar, str, (byte[]) numA);
                return;
        }
    }

    public <T extends c> void addConcreteTypeArrayInternal(a aVar, String str, ArrayList<T> arrayList) {
        throw new UnsupportedOperationException("Concrete type array not supported");
    }

    public <T extends c> void addConcreteTypeInternal(a aVar, String str, T t2) {
        throw new UnsupportedOperationException("Concrete type not supported");
    }

    public abstract Map getFieldMappings();

    public Object getFieldValue(a aVar) {
        String str = aVar.f12952p;
        if (aVar.f12954r == null) {
            return getValueObject(str);
        }
        if (!(getValueObject(str) == null)) {
            throw new IllegalStateException(androidx.compose.material3.d.C("Concrete field shouldn't be value object: ", str));
        }
        try {
            char upperCase = Character.toUpperCase(str.charAt(0));
            String strSubstring = str.substring(1);
            StringBuilder sb2 = new StringBuilder(String.valueOf(upperCase).length() + 3 + String.valueOf(strSubstring).length());
            sb2.append("get");
            sb2.append(upperCase);
            sb2.append(strSubstring);
            return getClass().getMethod(sb2.toString(), null).invoke(this, null);
        } catch (Exception e5) {
            throw new RuntimeException(e5);
        }
    }

    public abstract Object getValueObject(String str);

    public boolean isFieldSet(a aVar) {
        if (aVar.f12950n != 11) {
            return isPrimitiveFieldSet(aVar.f12952p);
        }
        if (aVar.f12951o) {
            throw new UnsupportedOperationException("Concrete type arrays not supported");
        }
        throw new UnsupportedOperationException("Concrete types not supported");
    }

    public abstract boolean isPrimitiveFieldSet(String str);

    public void setBooleanInternal(a aVar, String str, boolean z) {
        throw new UnsupportedOperationException("Boolean not supported");
    }

    public void setDecodedBytesInternal(a aVar, String str, byte[] bArr) {
        throw new UnsupportedOperationException("byte[] not supported");
    }

    public void setIntegerInternal(a aVar, String str, int i10) {
        throw new UnsupportedOperationException("Integer not supported");
    }

    public void setLongInternal(a aVar, String str, long j10) {
        throw new UnsupportedOperationException("Long not supported");
    }

    public void setStringInternal(a aVar, String str, String str2) {
        throw new UnsupportedOperationException("String not supported");
    }

    public void setStringMapInternal(a aVar, String str, Map<String, String> map) {
        throw new UnsupportedOperationException("String map not supported");
    }

    public void setStringsInternal(a aVar, String str, ArrayList<String> arrayList) {
        throw new UnsupportedOperationException("String list not supported");
    }

    public String toString() {
        Map fieldMappings = getFieldMappings();
        StringBuilder sb2 = new StringBuilder(100);
        for (String str : fieldMappings.keySet()) {
            a aVar = (a) fieldMappings.get(str);
            if (isFieldSet(aVar)) {
                Object objZaD = zaD(aVar, getFieldValue(aVar));
                if (sb2.length() == 0) {
                    sb2.append("{");
                } else {
                    sb2.append(",");
                }
                sb2.append("\"");
                sb2.append(str);
                sb2.append("\":");
                if (objZaD != null) {
                    switch (aVar.f12950n) {
                        case 8:
                            sb2.append("\"");
                            sb2.append(Base64.encodeToString((byte[]) objZaD, 0));
                            sb2.append("\"");
                            break;
                        case 9:
                            sb2.append("\"");
                            sb2.append(Base64.encodeToString((byte[]) objZaD, 10));
                            sb2.append("\"");
                            break;
                        case 10:
                            c4.d.e(sb2, (HashMap) objZaD);
                            break;
                        default:
                            if (aVar.f12949m) {
                                ArrayList arrayList = (ArrayList) objZaD;
                                sb2.append("[");
                                int size = arrayList.size();
                                for (int i10 = 0; i10 < size; i10++) {
                                    if (i10 > 0) {
                                        sb2.append(",");
                                    }
                                    Object obj = arrayList.get(i10);
                                    if (obj != null) {
                                        b(sb2, aVar, obj);
                                    }
                                }
                                sb2.append("]");
                            } else {
                                b(sb2, aVar, objZaD);
                            }
                            break;
                    }
                } else {
                    sb2.append("null");
                }
            }
        }
        if (sb2.length() > 0) {
            sb2.append("}");
        } else {
            sb2.append("{}");
        }
        return sb2.toString();
    }

    public void zaA(a aVar, String str, BigDecimal bigDecimal) {
        throw new UnsupportedOperationException("BigDecimal not supported");
    }

    public void zaB(a aVar, String str, ArrayList arrayList) {
        throw new UnsupportedOperationException("BigDecimal list not supported");
    }

    public void zaC(a aVar, String str, ArrayList arrayList) {
        throw new UnsupportedOperationException("Boolean list not supported");
    }

    public final void zaa(a aVar, int i10) {
        if (aVar.f12957u != null) {
            a(aVar, Integer.valueOf(i10));
        } else {
            setIntegerInternal(aVar, aVar.f12952p, i10);
        }
    }

    public final void zab(a aVar, ArrayList arrayList) {
        if (aVar.f12957u != null) {
            a(aVar, arrayList);
        } else {
            zas(aVar, aVar.f12952p, arrayList);
        }
    }

    public final void zac(a aVar, BigInteger bigInteger) {
        if (aVar.f12957u != null) {
            a(aVar, bigInteger);
        } else {
            zat(aVar, aVar.f12952p, bigInteger);
        }
    }

    public final void zad(a aVar, ArrayList arrayList) {
        if (aVar.f12957u != null) {
            a(aVar, arrayList);
        } else {
            zau(aVar, aVar.f12952p, arrayList);
        }
    }

    public final void zae(a aVar, long j10) {
        if (aVar.f12957u != null) {
            a(aVar, Long.valueOf(j10));
        } else {
            setLongInternal(aVar, aVar.f12952p, j10);
        }
    }

    public final void zaf(a aVar, ArrayList arrayList) {
        if (aVar.f12957u != null) {
            a(aVar, arrayList);
        } else {
            zav(aVar, aVar.f12952p, arrayList);
        }
    }

    public final void zag(a aVar, float f10) {
        if (aVar.f12957u != null) {
            a(aVar, Float.valueOf(f10));
        } else {
            zaw(aVar, aVar.f12952p, f10);
        }
    }

    public final void zah(a aVar, ArrayList arrayList) {
        if (aVar.f12957u != null) {
            a(aVar, arrayList);
        } else {
            zax(aVar, aVar.f12952p, arrayList);
        }
    }

    public final void zai(a aVar, double d4) {
        if (aVar.f12957u != null) {
            a(aVar, Double.valueOf(d4));
        } else {
            zay(aVar, aVar.f12952p, d4);
        }
    }

    public final void zaj(a aVar, ArrayList arrayList) {
        if (aVar.f12957u != null) {
            a(aVar, arrayList);
        } else {
            zaz(aVar, aVar.f12952p, arrayList);
        }
    }

    public final void zak(a aVar, BigDecimal bigDecimal) {
        if (aVar.f12957u != null) {
            a(aVar, bigDecimal);
        } else {
            zaA(aVar, aVar.f12952p, bigDecimal);
        }
    }

    public final void zal(a aVar, ArrayList arrayList) {
        if (aVar.f12957u != null) {
            a(aVar, arrayList);
        } else {
            zaB(aVar, aVar.f12952p, arrayList);
        }
    }

    public final void zam(a aVar, boolean z) {
        if (aVar.f12957u != null) {
            a(aVar, Boolean.valueOf(z));
        } else {
            setBooleanInternal(aVar, aVar.f12952p, z);
        }
    }

    public final void zan(a aVar, ArrayList arrayList) {
        if (aVar.f12957u != null) {
            a(aVar, arrayList);
        } else {
            zaC(aVar, aVar.f12952p, arrayList);
        }
    }

    public final void zao(a aVar, String str) {
        if (aVar.f12957u != null) {
            a(aVar, str);
        } else {
            setStringInternal(aVar, aVar.f12952p, str);
        }
    }

    public final void zap(a aVar, ArrayList arrayList) {
        if (aVar.f12957u != null) {
            a(aVar, arrayList);
        } else {
            setStringsInternal(aVar, aVar.f12952p, arrayList);
        }
    }

    public final void zaq(a aVar, byte[] bArr) {
        if (aVar.f12957u != null) {
            a(aVar, bArr);
        } else {
            setDecodedBytesInternal(aVar, aVar.f12952p, bArr);
        }
    }

    public final void zar(a aVar, Map map) {
        if (aVar.f12957u != null) {
            a(aVar, map);
        } else {
            setStringMapInternal(aVar, aVar.f12952p, map);
        }
    }

    public void zas(a aVar, String str, ArrayList arrayList) {
        throw new UnsupportedOperationException("Integer list not supported");
    }

    public void zat(a aVar, String str, BigInteger bigInteger) {
        throw new UnsupportedOperationException("BigInteger not supported");
    }

    public void zau(a aVar, String str, ArrayList arrayList) {
        throw new UnsupportedOperationException("BigInteger list not supported");
    }

    public void zav(a aVar, String str, ArrayList arrayList) {
        throw new UnsupportedOperationException("Long list not supported");
    }

    public void zaw(a aVar, String str, float f10) {
        throw new UnsupportedOperationException("Float not supported");
    }

    public void zax(a aVar, String str, ArrayList arrayList) {
        throw new UnsupportedOperationException("Float list not supported");
    }

    public void zay(a aVar, String str, double d4) {
        throw new UnsupportedOperationException("Double not supported");
    }

    public void zaz(a aVar, String str, ArrayList arrayList) {
        throw new UnsupportedOperationException("Double list not supported");
    }
}
