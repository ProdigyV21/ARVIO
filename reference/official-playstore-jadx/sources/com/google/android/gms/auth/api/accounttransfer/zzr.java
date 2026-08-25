package com.google.android.gms.auth.api.accounttransfer;

import android.os.Bundle;
import com.google.android.gms.common.api.d;
import com.google.android.gms.common.internal.t;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/* JADX INFO: loaded from: classes4.dex */
public final class zzr implements d.e {
    public static final zzr zza;
    private final Bundle zzb;

    static {
        Bundle bundle = new Bundle();
        if (!bundle.containsKey("accountTypes")) {
            bundle.putStringArrayList("accountTypes", new ArrayList<>(0));
        }
        zza = new zzr(bundle, null);
    }

    public /* synthetic */ zzr(Bundle bundle, zzq zzqVar) {
        this.zzb = bundle;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzr)) {
            return false;
        }
        Bundle bundleZza = zza();
        Bundle bundleZza2 = ((zzr) obj).zza();
        if (bundleZza.size() != bundleZza2.size()) {
            return false;
        }
        for (String str : bundleZza.keySet()) {
            if (!bundleZza2.containsKey(str) || !t.l(bundleZza.get(str), bundleZza2.get(str))) {
                return false;
            }
        }
        return true;
    }

    public final int hashCode() {
        Bundle bundleZza = zza();
        int size = bundleZza.size();
        ArrayList arrayList = new ArrayList(size + size);
        ArrayList arrayList2 = new ArrayList(bundleZza.keySet());
        Collections.sort(arrayList2);
        int size2 = arrayList2.size();
        for (int i10 = 0; i10 < size2; i10++) {
            String str = (String) arrayList2.get(i10);
            arrayList.add(str);
            arrayList.add(bundleZza.get(str));
        }
        return Arrays.hashCode(new Object[]{arrayList});
    }

    public final Bundle zza() {
        return new Bundle(this.zzb);
    }
}
