package i4;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;

/* JADX INFO: loaded from: classes4.dex */
public final class f0 extends t3.a {
    public static final Parcelable.Creator<f0> CREATOR = new k0(17);

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final List f16109i;

    public f0(ArrayList arrayList) {
        this.f16109i = arrayList;
    }

    public final JSONArray d() {
        try {
            JSONArray jSONArray = new JSONArray();
            List list = this.f16109i;
            if (list != null) {
                for (int i10 = 0; i10 < list.size(); i10++) {
                    g0 g0Var = (g0) list.get(i10);
                    JSONArray jSONArray2 = new JSONArray();
                    jSONArray2.put((int) g0Var.f16117m);
                    jSONArray2.put((int) g0Var.f16116l);
                    jSONArray2.put((int) g0Var.f16117m);
                    jSONArray.put(i10, jSONArray2);
                }
            }
            return jSONArray;
        } catch (JSONException e5) {
            throw new RuntimeException("Error encoding UvmEntries to JSON object", e5);
        }
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof f0)) {
            return false;
        }
        List list = ((f0) obj).f16109i;
        List list2 = this.f16109i;
        if (list2 == null && list == null) {
            return true;
        }
        return list2 != null && list != null && list2.containsAll(list) && list.containsAll(list2);
    }

    public final int hashCode() {
        List list = this.f16109i;
        return Arrays.hashCode(new Object[]{list == null ? null : new HashSet(list)});
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i10) {
        int iM0 = a.a.m0(20293, parcel);
        a.a.k0(parcel, 1, this.f16109i, false);
        a.a.n0(iM0, parcel);
    }
}
