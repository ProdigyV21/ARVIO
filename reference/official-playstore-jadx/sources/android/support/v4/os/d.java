package android.support.v4.os;

import android.os.Binder;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Parcel;

/* JADX INFO: loaded from: classes.dex */
public final class d extends Binder implements c {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final /* synthetic */ int f909d = 0;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final /* synthetic */ f f910c;

    public d(f fVar) {
        this.f910c = fVar;
        attachInterface(this, c.f908a);
    }

    @Override // android.support.v4.os.c
    public final void A(int i10, Bundle bundle) {
        f fVar = this.f910c;
        Handler handler = fVar.mHandler;
        if (handler != null) {
            handler.post(new e(fVar, i10, bundle, 0));
        } else {
            fVar.onReceiveResult(i10, bundle);
        }
    }

    @Override // android.os.Binder
    public final boolean onTransact(int i10, Parcel parcel, Parcel parcel2, int i11) {
        String str = c.f908a;
        if (i10 >= 1 && i10 <= 16777215) {
            parcel.enforceInterface(str);
        }
        if (i10 == 1598968902) {
            parcel2.writeString(str);
            return true;
        }
        if (i10 != 1) {
            return super.onTransact(i10, parcel, parcel2, i11);
        }
        A(parcel.readInt(), (Bundle) (parcel.readInt() != 0 ? Bundle.CREATOR.createFromParcel(parcel) : null));
        return true;
    }

    @Override // android.os.IInterface
    public final IBinder asBinder() {
        return this;
    }
}
