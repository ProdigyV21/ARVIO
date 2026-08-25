package u4;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.x;
import i4.s0;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
public final class f extends t3.a implements x {
    public static final Parcelable.Creator<f> CREATOR = new s0(10);

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final List f22051i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final String f22052l;

    public f(String str, ArrayList arrayList) {
        this.f22051i = arrayList;
        this.f22052l = str;
    }

    @Override // com.google.android.gms.common.api.x
    public final Status getStatus() {
        return this.f22052l != null ? Status.f12670o : Status.f12674s;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i10) {
        int iM0 = a.a.m0(20293, parcel);
        a.a.i0(parcel, this.f22051i, 1);
        a.a.g0(parcel, 2, this.f22052l, false);
        a.a.n0(iM0, parcel);
    }
}
