package i4;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Base64;
import com.google.android.gms.internal.fido.w0;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes4.dex */
public final class m0 extends t3.a {
    public static final Parcelable.Creator<m0> CREATOR = new k0(2);

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public static final byte[] f16138l = "WebAuthn PRF\u0000".getBytes(StandardCharsets.UTF_8);

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final byte[][] f16139i;

    public m0(byte[][] bArr) {
        com.google.android.gms.common.internal.t.b(bArr != null);
        com.google.android.gms.common.internal.t.b(1 == ((bArr.length & 1) ^ 1));
        int i10 = 0;
        while (i10 < bArr.length) {
            com.google.android.gms.common.internal.t.b(i10 == 0 || bArr[i10] != null);
            int i11 = i10 + 1;
            com.google.android.gms.common.internal.t.b(bArr[i11] != null);
            int length = bArr[i11].length;
            com.google.android.gms.common.internal.t.b(length == 32 || length == 64);
            i10 += 2;
        }
        this.f16139i = bArr;
    }

    public static m0 d(JSONObject jSONObject, boolean z) {
        ArrayList arrayList = new ArrayList();
        try {
            if (jSONObject.has("eval")) {
                arrayList.add(null);
                if (z) {
                    arrayList.add(g(jSONObject.getJSONObject("eval")));
                } else {
                    arrayList.add(h(jSONObject.getJSONObject("eval")));
                }
            }
            if (jSONObject.has("evalByCredential")) {
                JSONObject jSONObject2 = jSONObject.getJSONObject("evalByCredential");
                Iterator<String> itKeys = jSONObject2.keys();
                while (itKeys.hasNext()) {
                    String next = itKeys.next();
                    arrayList.add(c4.d.a(next));
                    if (z) {
                        arrayList.add(g(jSONObject2.getJSONObject(next)));
                    } else {
                        arrayList.add(h(jSONObject2.getJSONObject(next)));
                    }
                }
            }
            return new m0((byte[][]) arrayList.toArray(new byte[0][]));
        } catch (IllegalArgumentException unused) {
            throw new JSONException("invalid base64url value");
        }
    }

    public static JSONObject e(byte[] bArr) throws JSONException {
        JSONObject jSONObject = new JSONObject();
        if (bArr.length == 32) {
            jSONObject.put("first", Base64.encodeToString(bArr, 11));
            return jSONObject;
        }
        jSONObject.put("first", Base64.encodeToString(bArr, 0, 32, 11));
        jSONObject.put("second", Base64.encodeToString(bArr, 32, 32, 11));
        return jSONObject;
    }

    public static byte[] f(byte[] bArr) {
        androidx.appcompat.widget.a aVar;
        com.google.android.gms.internal.fido.p0 p0Var;
        int i10 = com.google.android.gms.internal.fido.u0.f13825a;
        w0 w0Var = com.google.android.gms.internal.fido.t0.f13821a;
        int i11 = w0Var.f13834p;
        MessageDigest messageDigest = w0Var.f13833o;
        if (w0Var.f13835q) {
            try {
                aVar = new androidx.appcompat.widget.a((MessageDigest) messageDigest.clone(), i11);
            } catch (CloneNotSupportedException unused) {
                try {
                    aVar = new androidx.appcompat.widget.a(MessageDigest.getInstance(messageDigest.getAlgorithm()), i11);
                } catch (NoSuchAlgorithmException e5) {
                    throw new AssertionError(e5);
                }
            }
        } else {
            aVar = new androidx.appcompat.widget.a(MessageDigest.getInstance(messageDigest.getAlgorithm()), i11);
        }
        MessageDigest messageDigest2 = (MessageDigest) aVar.f1501c;
        byte[] bArr2 = f16138l;
        bArr2.getClass();
        int length = bArr2.length;
        if (aVar.f1500b) {
            throw new IllegalStateException("Cannot re-use a Hasher after calling hash() on it");
        }
        messageDigest2.update(bArr2, 0, length);
        bArr.getClass();
        int length2 = bArr.length;
        if (aVar.f1500b) {
            throw new IllegalStateException("Cannot re-use a Hasher after calling hash() on it");
        }
        messageDigest2.update(bArr, 0, length2);
        if (aVar.f1500b) {
            throw new IllegalStateException("Cannot re-use a Hasher after calling hash() on it");
        }
        aVar.f1500b = true;
        int i12 = aVar.f1499a;
        if (i12 == messageDigest2.getDigestLength()) {
            byte[] bArrDigest = messageDigest2.digest();
            char[] cArr = com.google.android.gms.internal.fido.q0.f13809i;
            p0Var = new com.google.android.gms.internal.fido.p0(bArrDigest);
        } else {
            byte[] bArrCopyOf = Arrays.copyOf(messageDigest2.digest(), i12);
            char[] cArr2 = com.google.android.gms.internal.fido.q0.f13809i;
            p0Var = new com.google.android.gms.internal.fido.p0(bArrCopyOf);
        }
        return (byte[]) p0Var.f13806l.clone();
    }

    public static byte[] g(JSONObject jSONObject) throws JSONException {
        byte[] bArrA = c4.d.a(jSONObject.getString("first"));
        if (bArrA.length != 32) {
            throw new JSONException("hashed PRF value with wrong length");
        }
        if (!jSONObject.has("second")) {
            return bArrA;
        }
        byte[] bArrA2 = c4.d.a(jSONObject.getString("second"));
        if (bArrA2.length == 32) {
            return com.google.android.gms.internal.fido.b.j(bArrA, bArrA2);
        }
        throw new JSONException("hashed PRF value with wrong length");
    }

    public static byte[] h(JSONObject jSONObject) {
        byte[] bArrF = f(c4.d.a(jSONObject.getString("first")));
        return !jSONObject.has("second") ? bArrF : com.google.android.gms.internal.fido.b.j(bArrF, f(c4.d.a(jSONObject.getString("second"))));
    }

    public final boolean equals(Object obj) {
        if (obj instanceof m0) {
            return Arrays.deepEquals(this.f16139i, ((m0) obj).f16139i);
        }
        return false;
    }

    public final int hashCode() {
        int iHashCode = 0;
        for (byte[] bArr : this.f16139i) {
            if (bArr != null) {
                iHashCode ^= Arrays.hashCode(new Object[]{bArr});
            }
        }
        return iHashCode;
    }

    public final String toString() {
        byte[][] bArr = this.f16139i;
        try {
            JSONObject jSONObject = new JSONObject();
            JSONObject jSONObject2 = null;
            for (int i10 = 0; i10 < bArr.length; i10 += 2) {
                if (bArr[i10] == null) {
                    jSONObject.put("eval", e(bArr[i10 + 1]));
                } else {
                    if (jSONObject2 == null) {
                        jSONObject2 = new JSONObject();
                        jSONObject.put("evalByCredential", jSONObject2);
                    }
                    jSONObject2.put(c4.d.b(bArr[i10]), e(bArr[i10 + 1]));
                }
            }
            return "PrfExtension{" + jSONObject.toString() + "}";
        } catch (JSONException e5) {
            return a0.c.l("PrfExtension{Exception:", e5.getMessage(), "}");
        }
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i10) {
        int iM0 = a.a.m0(20293, parcel);
        byte[][] bArr = this.f16139i;
        if (bArr != null) {
            int iM02 = a.a.m0(1, parcel);
            parcel.writeInt(bArr.length);
            for (byte[] bArr2 : bArr) {
                parcel.writeByteArray(bArr2);
            }
            a.a.n0(iM02, parcel);
        }
        a.a.n0(iM0, parcel);
    }
}
