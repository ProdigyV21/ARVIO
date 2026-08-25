package androidx.lifecycle;

import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcelable;
import android.util.Size;
import android.util.SizeF;
import android.util.SparseArray;
import com.google.firebase.crashlytics.internal.metadata.UserMetadata;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

/* JADX INFO: loaded from: classes3.dex */
public final class w0 {

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final Class[] f3303f = {Boolean.TYPE, boolean[].class, Double.TYPE, double[].class, Integer.TYPE, int[].class, Long.TYPE, long[].class, String.class, String[].class, Binder.class, Bundle.class, Byte.TYPE, byte[].class, Character.TYPE, char[].class, CharSequence.class, CharSequence[].class, ArrayList.class, Float.TYPE, float[].class, Parcelable.class, Parcelable[].class, Serializable.class, Short.TYPE, short[].class, SparseArray.class, Size.class, SizeF.class};

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final LinkedHashMap f3304a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final LinkedHashMap f3305b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final LinkedHashMap f3306c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final LinkedHashMap f3307d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final b2.d f3308e;

    public w0(HashMap map) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        this.f3304a = linkedHashMap;
        this.f3305b = new LinkedHashMap();
        this.f3306c = new LinkedHashMap();
        this.f3307d = new LinkedHashMap();
        this.f3308e = new androidx.activity.g(this, 1);
        linkedHashMap.putAll(map);
    }

    public static Bundle a(w0 w0Var) {
        LinkedHashMap linkedHashMap = w0Var.f3304a;
        for (Map.Entry entry : kotlin.collections.h0.B0(w0Var.f3305b).entrySet()) {
            w0Var.b((String) entry.getKey(), ((b2.d) entry.getValue()).a());
        }
        Set<String> setKeySet = linkedHashMap.keySet();
        ArrayList arrayList = new ArrayList(setKeySet.size());
        ArrayList arrayList2 = new ArrayList(arrayList.size());
        for (String str : setKeySet) {
            arrayList.add(str);
            arrayList2.add(linkedHashMap.get(str));
        }
        x6.x[] xVarArr = {new x6.x(UserMetadata.KEYDATA_FILENAME, arrayList), new x6.x("values", arrayList2)};
        Bundle bundle = new Bundle(2);
        for (int i10 = 0; i10 < 2; i10++) {
            x6.x xVar = xVarArr[i10];
            String str2 = (String) xVar.f22608i;
            Object obj = xVar.f22609l;
            if (obj == null) {
                bundle.putString(str2, null);
            } else if (obj instanceof Boolean) {
                bundle.putBoolean(str2, ((Boolean) obj).booleanValue());
            } else if (obj instanceof Byte) {
                bundle.putByte(str2, ((Number) obj).byteValue());
            } else if (obj instanceof Character) {
                bundle.putChar(str2, ((Character) obj).charValue());
            } else if (obj instanceof Double) {
                bundle.putDouble(str2, ((Number) obj).doubleValue());
            } else if (obj instanceof Float) {
                bundle.putFloat(str2, ((Number) obj).floatValue());
            } else if (obj instanceof Integer) {
                bundle.putInt(str2, ((Number) obj).intValue());
            } else if (obj instanceof Long) {
                bundle.putLong(str2, ((Number) obj).longValue());
            } else if (obj instanceof Short) {
                bundle.putShort(str2, ((Number) obj).shortValue());
            } else if (obj instanceof Bundle) {
                bundle.putBundle(str2, (Bundle) obj);
            } else if (obj instanceof CharSequence) {
                bundle.putCharSequence(str2, (CharSequence) obj);
            } else if (obj instanceof Parcelable) {
                bundle.putParcelable(str2, (Parcelable) obj);
            } else if (obj instanceof boolean[]) {
                bundle.putBooleanArray(str2, (boolean[]) obj);
            } else if (obj instanceof byte[]) {
                bundle.putByteArray(str2, (byte[]) obj);
            } else if (obj instanceof char[]) {
                bundle.putCharArray(str2, (char[]) obj);
            } else if (obj instanceof double[]) {
                bundle.putDoubleArray(str2, (double[]) obj);
            } else if (obj instanceof float[]) {
                bundle.putFloatArray(str2, (float[]) obj);
            } else if (obj instanceof int[]) {
                bundle.putIntArray(str2, (int[]) obj);
            } else if (obj instanceof long[]) {
                bundle.putLongArray(str2, (long[]) obj);
            } else if (obj instanceof short[]) {
                bundle.putShortArray(str2, (short[]) obj);
            } else if (obj instanceof Object[]) {
                Class<?> componentType = obj.getClass().getComponentType();
                if (Parcelable.class.isAssignableFrom(componentType)) {
                    bundle.putParcelableArray(str2, (Parcelable[]) obj);
                } else if (String.class.isAssignableFrom(componentType)) {
                    bundle.putStringArray(str2, (String[]) obj);
                } else if (CharSequence.class.isAssignableFrom(componentType)) {
                    bundle.putCharSequenceArray(str2, (CharSequence[]) obj);
                } else {
                    if (!Serializable.class.isAssignableFrom(componentType)) {
                        throw new IllegalArgumentException("Illegal value array type " + componentType.getCanonicalName() + " for key \"" + str2 + '\"');
                    }
                    bundle.putSerializable(str2, (Serializable) obj);
                }
            } else if (obj instanceof Serializable) {
                bundle.putSerializable(str2, (Serializable) obj);
            } else if (obj instanceof IBinder) {
                bundle.putBinder(str2, (IBinder) obj);
            } else if (obj instanceof Size) {
                androidx.core.os.d.a(bundle, str2, (Size) obj);
            } else {
                if (!(obj instanceof SizeF)) {
                    throw new IllegalArgumentException("Illegal value type " + obj.getClass().getCanonicalName() + " for key \"" + str2 + '\"');
                }
                androidx.core.os.d.b(bundle, str2, (SizeF) obj);
            }
        }
        return bundle;
    }

    public final void b(String str, Object obj) {
        if (obj != null) {
            for (int i10 = 0; i10 < 29; i10++) {
                if (!f3303f[i10].isInstance(obj)) {
                }
            }
            throw new IllegalArgumentException("Can't put value with type " + obj.getClass() + " into saved state");
        }
        Object obj2 = this.f3306c.get(str);
        j0 j0Var = obj2 instanceof j0 ? (j0) obj2 : null;
        if (j0Var != null) {
            j0Var.i(obj);
        } else {
            this.f3304a.put(str, obj);
        }
        na.q0 q0Var = (na.q0) this.f3307d.get(str);
        if (q0Var == null) {
            return;
        }
        q0Var.setValue(obj);
    }

    public w0() {
        this.f3304a = new LinkedHashMap();
        this.f3305b = new LinkedHashMap();
        this.f3306c = new LinkedHashMap();
        this.f3307d = new LinkedHashMap();
        this.f3308e = new androidx.activity.g(this, 1);
    }
}
