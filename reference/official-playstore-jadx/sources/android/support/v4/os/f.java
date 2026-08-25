package android.support.v4.os;

import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;

/* JADX INFO: loaded from: classes.dex */
public class f implements Parcelable {
    public static final Parcelable.Creator<f> CREATOR = new android.support.v4.media.d(4);
    final Handler mHandler;
    final boolean mLocal;
    c mReceiver;

    public f(Handler handler) {
        this.mLocal = true;
        this.mHandler = handler;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public void onReceiveResult(int i10, Bundle bundle) {
    }

    public void send(int i10, Bundle bundle) {
        if (this.mLocal) {
            Handler handler = this.mHandler;
            if (handler != null) {
                handler.post(new e(this, i10, bundle, 0));
                return;
            } else {
                onReceiveResult(i10, bundle);
                return;
            }
        }
        c cVar = this.mReceiver;
        if (cVar != null) {
            try {
                cVar.A(i10, bundle);
            } catch (RemoteException unused) {
            }
        }
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i10) {
        synchronized (this) {
            try {
                if (this.mReceiver == null) {
                    this.mReceiver = new d(this);
                }
                parcel.writeStrongBinder(this.mReceiver.asBinder());
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public f(Parcel parcel) {
        this.mLocal = false;
        c cVar = null;
        this.mHandler = null;
        IBinder strongBinder = parcel.readStrongBinder();
        int i10 = d.f909d;
        if (strongBinder != null) {
            IInterface iInterfaceQueryLocalInterface = strongBinder.queryLocalInterface(c.f908a);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof c)) {
                cVar = (c) iInterfaceQueryLocalInterface;
            } else {
                a aVar = new a();
                aVar.f907c = strongBinder;
                cVar = aVar;
            }
        }
        this.mReceiver = cVar;
    }
}
