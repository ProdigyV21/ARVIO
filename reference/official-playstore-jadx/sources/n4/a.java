package n4;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.api.Status;

/* JADX INFO: loaded from: classes4.dex */
public final class a extends d implements i {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final /* synthetic */ int f20484d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ com.google.android.gms.tasks.i f20485e;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public a(int i10, com.google.android.gms.tasks.i iVar) {
        super("com.google.android.gms.auth.api.phone.internal.ISmsRetrieverResultCallback", 0);
        this.f20484d = i10;
        this.f20485e = iVar;
    }

    @Override // n4.d
    public final boolean a(int i10, Parcel parcel) {
        if (i10 != 1) {
            return false;
        }
        Parcelable.Creator<Status> creator = Status.CREATOR;
        int i11 = e.f20492a;
        Status statusCreateFromParcel = parcel.readInt() == 0 ? null : creator.createFromParcel(parcel);
        e.a(parcel);
        x(statusCreateFromParcel);
        return true;
    }

    @Override // n4.i
    public final void x(Status status) {
        switch (this.f20484d) {
            case 0:
                qb.d.S(status, null, this.f20485e);
                break;
            default:
                qb.d.S(status, null, this.f20485e);
                break;
        }
    }
}
