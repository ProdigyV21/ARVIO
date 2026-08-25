package androidx.media3.common.util;

import android.os.Bundle;
import android.util.SparseArray;
import com.google.common.base.m;
import com.google.common.collect.e1;
import com.google.common.collect.h1;
import com.google.common.collect.l1;
import com.google.common.collect.t3;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes3.dex */
public final class BundleCollectionUtil {
    private BundleCollectionUtil() {
    }

    public static HashMap<String, String> bundleToStringHashMap(Bundle bundle) {
        HashMap<String, String> map = new HashMap<>();
        if (bundle != Bundle.EMPTY) {
            for (String str : bundle.keySet()) {
                String string = bundle.getString(str);
                if (string != null) {
                    map.put(str, string);
                }
            }
        }
        return map;
    }

    public static l1 bundleToStringImmutableMap(Bundle bundle) {
        return bundle == Bundle.EMPTY ? t3.f14114q : l1.b(bundleToStringHashMap(bundle));
    }

    public static void ensureClassLoader(Bundle bundle) {
        if (bundle != null) {
            bundle.setClassLoader((ClassLoader) Util.castNonNull(BundleCollectionUtil.class.getClassLoader()));
        }
    }

    public static <T> h1 fromBundleList(m<Bundle, T> mVar, List<Bundle> list) {
        e1 e1VarK = h1.k();
        for (int i10 = 0; i10 < list.size(); i10++) {
            Bundle bundle = list.get(i10);
            bundle.getClass();
            e1VarK.c(mVar.apply(bundle));
        }
        return e1VarK.f();
    }

    public static <T> SparseArray<T> fromBundleSparseArray(m<Bundle, T> mVar, SparseArray<Bundle> sparseArray) {
        SparseArray<T> sparseArray2 = new SparseArray<>(sparseArray.size());
        for (int i10 = 0; i10 < sparseArray.size(); i10++) {
            sparseArray2.put(sparseArray.keyAt(i10), mVar.apply(sparseArray.valueAt(i10)));
        }
        return sparseArray2;
    }

    public static Bundle getBundleWithDefault(Bundle bundle, String str, Bundle bundle2) {
        Bundle bundle3 = bundle.getBundle(str);
        return bundle3 != null ? bundle3 : bundle2;
    }

    public static ArrayList<Integer> getIntegerArrayListWithDefault(Bundle bundle, String str, ArrayList<Integer> arrayList) {
        ArrayList<Integer> integerArrayList = bundle.getIntegerArrayList(str);
        return integerArrayList != null ? integerArrayList : arrayList;
    }

    public static Bundle stringMapToBundle(Map<String, String> map) {
        Bundle bundle = new Bundle();
        for (Map.Entry<String, String> entry : map.entrySet()) {
            bundle.putString(entry.getKey(), entry.getValue());
        }
        return bundle;
    }

    public static <T> ArrayList<Bundle> toBundleArrayList(Collection<T> collection, m<T, Bundle> mVar) {
        ArrayList<Bundle> arrayList = new ArrayList<>(collection.size());
        Iterator<T> it = collection.iterator();
        while (it.hasNext()) {
            arrayList.add(mVar.apply(it.next()));
        }
        return arrayList;
    }

    public static <T> h1 toBundleList(List<T> list, m<T, Bundle> mVar) {
        e1 e1VarK = h1.k();
        for (int i10 = 0; i10 < list.size(); i10++) {
            e1VarK.c(mVar.apply(list.get(i10)));
        }
        return e1VarK.f();
    }

    public static <T> SparseArray<Bundle> toBundleSparseArray(SparseArray<T> sparseArray, m<T, Bundle> mVar) {
        SparseArray<Bundle> sparseArray2 = new SparseArray<>(sparseArray.size());
        for (int i10 = 0; i10 < sparseArray.size(); i10++) {
            sparseArray2.put(sparseArray.keyAt(i10), mVar.apply(sparseArray.valueAt(i10)));
        }
        return sparseArray2;
    }
}
