package n4;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.api.Status;

/* JADX INFO: loaded from: classes4.dex */
public final class j extends d implements f {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final /* synthetic */ com.google.android.gms.tasks.i f20494d;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public j(com.google.android.gms.tasks.i iVar) {
        super("com.google.android.gms.auth.api.phone.internal.IAutofillPermissionStateCallback", 0);
        this.f20494d = iVar;
    }

    @Override // n4.d
    public final boolean a(int i10, Parcel parcel) {
        if (i10 != 1) {
            return false;
        }
        Parcelable.Creator<Status> creator = Status.CREATOR;
        int i11 = e.f20492a;
        Status statusCreateFromParcel = parcel.readInt() == 0 ? null : creator.createFromParcel(parcel);
        int i12 = parcel.readInt();
        e.a(parcel);
        qb.d.S(statusCreateFromParcel, Integer.valueOf(i12), this.f20494d);
        return true;
    }
}
