package androidx.navigation.compose;

import android.os.Bundle;
import android.os.Parcelable;
import androidx.navigation.a1;
import androidx.navigation.l0;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.collections.h0;

/* JADX INFO: loaded from: classes3.dex */
public final class r extends kotlin.jvm.internal.r implements r7.p {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public static final r f4600i = new r(2);

    @Override // r7.p
    public final Object invoke(Object obj, Object obj2) {
        Bundle bundle;
        l0 l0Var = (l0) obj2;
        LinkedHashMap linkedHashMap = l0Var.f4772n;
        LinkedHashMap linkedHashMap2 = l0Var.f4771m;
        kotlin.collections.p pVar = l0Var.f4766g;
        ArrayList<String> arrayList = new ArrayList<>();
        Bundle bundle2 = new Bundle();
        for (Map.Entry entry : h0.B0(l0Var.f4780v.f4529a).entrySet()) {
            ((a1) entry.getValue()).getClass();
        }
        if (arrayList.isEmpty()) {
            bundle = null;
        } else {
            bundle = new Bundle();
            bundle2.putStringArrayList("android-support-nav:controller:navigatorState:names", arrayList);
            bundle.putBundle("android-support-nav:controller:navigatorState", bundle2);
        }
        if (!pVar.isEmpty()) {
            if (bundle == null) {
                bundle = new Bundle();
            }
            Parcelable[] parcelableArr = new Parcelable[pVar.size()];
            Iterator<E> it = pVar.iterator();
            int i10 = 0;
            while (it.hasNext()) {
                parcelableArr[i10] = new androidx.navigation.j((androidx.navigation.i) it.next());
                i10++;
            }
            bundle.putParcelableArray("android-support-nav:controller:backStack", parcelableArr);
        }
        if (!linkedHashMap2.isEmpty()) {
            if (bundle == null) {
                bundle = new Bundle();
            }
            int[] iArr = new int[linkedHashMap2.size()];
            ArrayList<String> arrayList2 = new ArrayList<>();
            int i11 = 0;
            for (Map.Entry entry2 : linkedHashMap2.entrySet()) {
                int iIntValue = ((Number) entry2.getKey()).intValue();
                String str = (String) entry2.getValue();
                iArr[i11] = iIntValue;
                arrayList2.add(str);
                i11++;
            }
            bundle.putIntArray("android-support-nav:controller:backStackDestIds", iArr);
            bundle.putStringArrayList("android-support-nav:controller:backStackIds", arrayList2);
        }
        if (!linkedHashMap.isEmpty()) {
            if (bundle == null) {
                bundle = new Bundle();
            }
            ArrayList<String> arrayList3 = new ArrayList<>();
            for (Map.Entry entry3 : linkedHashMap.entrySet()) {
                String str2 = (String) entry3.getKey();
                kotlin.collections.p pVar2 = (kotlin.collections.p) entry3.getValue();
                arrayList3.add(str2);
                Parcelable[] parcelableArr2 = new Parcelable[pVar2.size()];
                int i12 = 0;
                for (Object obj3 : pVar2) {
                    int i13 = i12 + 1;
                    if (i12 < 0) {
                        t7.a.Q();
                        throw null;
                    }
                    parcelableArr2[i12] = (androidx.navigation.j) obj3;
                    i12 = i13;
                }
                bundle.putParcelableArray(androidx.compose.material3.d.C("android-support-nav:controller:backStackStates:", str2), parcelableArr2);
            }
            bundle.putStringArrayList("android-support-nav:controller:backStackStates", arrayList3);
        }
        if (l0Var.f4765f) {
            if (bundle == null) {
                bundle = new Bundle();
            }
            bundle.putBoolean("android-support-nav:controller:deepLinkHandled", l0Var.f4765f);
        }
        return bundle;
    }
}
