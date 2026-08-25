package y1;

import android.os.IBinder;
import android.os.Parcel;

/* JADX INFO: loaded from: classes3.dex */
public final class s implements t {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public IBinder f22860c;

    @Override // android.os.IInterface
    public final IBinder asBinder() {
        return this.f22860c;
    }

    @Override // y1.t
    public final void j(String[] strArr) {
        Parcel parcelObtain = Parcel.obtain();
        try {
            parcelObtain.writeInterfaceToken("androidx.room.IMultiInstanceInvalidationCallback");
            parcelObtain.writeStringArray(strArr);
            this.f22860c.transact(1, parcelObtain, null, 1);
        } finally {
            parcelObtain.recycle();
        }
    }
}
