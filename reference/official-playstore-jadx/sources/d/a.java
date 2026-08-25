package d;

import android.app.Notification;
import android.os.IBinder;
import android.os.Parcel;

/* JADX INFO: loaded from: classes.dex */
public final class a implements c {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public IBinder f14653c;

    @Override // d.c
    public final void Z(String str, int i10, Notification notification) {
        Parcel parcelObtain = Parcel.obtain();
        try {
            parcelObtain.writeInterfaceToken(c.f14655b);
            parcelObtain.writeString(str);
            parcelObtain.writeInt(i10);
            parcelObtain.writeString(null);
            if (notification != null) {
                parcelObtain.writeInt(1);
                notification.writeToParcel(parcelObtain, 0);
            } else {
                parcelObtain.writeInt(0);
            }
            this.f14653c.transact(1, parcelObtain, null, 1);
        } finally {
            parcelObtain.recycle();
        }
    }

    @Override // android.os.IInterface
    public final IBinder asBinder() {
        return this.f14653c;
    }
}
