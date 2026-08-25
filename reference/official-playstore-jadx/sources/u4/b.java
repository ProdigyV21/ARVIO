package u4;

import android.content.Intent;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.x;
import i4.s0;

/* JADX INFO: loaded from: classes4.dex */
public final class b extends t3.a implements x {
    public static final Parcelable.Creator<b> CREATOR = new s0(9);

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final int f22048i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final int f22049l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final Intent f22050m;

    public b(int i10, int i11, Intent intent) {
        this.f22048i = i10;
        this.f22049l = i11;
        this.f22050m = intent;
    }

    @Override // com.google.android.gms.common.api.x
    public final Status getStatus() {
        return this.f22049l == 0 ? Status.f12670o : Status.f12674s;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i10) {
        int iM0 = a.a.m0(20293, parcel);
        a.a.l0(parcel, 1, 4);
        parcel.writeInt(this.f22048i);
        a.a.l0(parcel, 2, 4);
        parcel.writeInt(this.f22049l);
        a.a.f0(parcel, 3, this.f22050m, i10, false);
        a.a.n0(iM0, parcel);
    }
}
