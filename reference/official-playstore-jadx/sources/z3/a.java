package z3;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.SparseArray;
import i4.s0;
import java.util.ArrayList;
import java.util.HashMap;

/* JADX INFO: loaded from: classes4.dex */
public final class a extends t3.a implements com.google.android.gms.common.server.response.b {
    public static final Parcelable.Creator<a> CREATOR = new s0(13);

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final int f23168i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final HashMap f23169l = new HashMap();

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final SparseArray f23170m = new SparseArray();

    public a(int i10, ArrayList arrayList) {
        this.f23168i = i10;
        int size = arrayList.size();
        for (int i11 = 0; i11 < size; i11++) {
            c cVar = (c) arrayList.get(i11);
            String str = cVar.f23174l;
            int i12 = cVar.f23175m;
            this.f23169l.put(str, Integer.valueOf(i12));
            this.f23170m.put(i12, str);
        }
    }

    @Override // com.google.android.gms.common.server.response.b
    public final /* bridge */ /* synthetic */ Integer a(Object obj) {
        HashMap map = this.f23169l;
        Integer num = (Integer) map.get((String) obj);
        return num == null ? (Integer) map.get("gms_unknown") : num;
    }

    @Override // com.google.android.gms.common.server.response.b
    public final /* bridge */ /* synthetic */ String b(Object obj) {
        String str = (String) this.f23170m.get(((Integer) obj).intValue());
        return (str == null && this.f23169l.containsKey("gms_unknown")) ? "gms_unknown" : str;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i10) {
        int iM0 = a.a.m0(20293, parcel);
        a.a.l0(parcel, 1, 4);
        parcel.writeInt(this.f23168i);
        ArrayList arrayList = new ArrayList();
        HashMap map = this.f23169l;
        for (String str : map.keySet()) {
            arrayList.add(new c(str, ((Integer) map.get(str)).intValue()));
        }
        a.a.k0(parcel, 2, arrayList, false);
        a.a.n0(iM0, parcel);
    }
}
