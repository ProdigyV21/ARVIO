package n4;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;

/* JADX INFO: loaded from: classes4.dex */
public abstract class d extends Binder implements IInterface {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final /* synthetic */ int f20491c;

    public d(String str, int i10) {
        this.f20491c = i10;
        switch (i10) {
            case 1:
                attachInterface(this, str);
                break;
            default:
                attachInterface(this, str);
                break;
        }
    }

    public abstract boolean a(int i10, Parcel parcel);

    @Override // android.os.IInterface
    public final IBinder asBinder() {
        int i10 = this.f20491c;
        return this;
    }

    public boolean c(int i10, Parcel parcel, Parcel parcel2) {
        return false;
    }

    @Override // android.os.Binder
    public final boolean onTransact(int i10, Parcel parcel, Parcel parcel2, int i11) {
        switch (this.f20491c) {
            case 0:
                if (i10 <= 16777215) {
                    parcel.enforceInterface(getInterfaceDescriptor());
                } else if (super.onTransact(i10, parcel, parcel2, i11)) {
                    return true;
                }
                return a(i10, parcel);
            default:
                if (i10 <= 16777215) {
                    parcel.enforceInterface(getInterfaceDescriptor());
                } else if (super.onTransact(i10, parcel, parcel2, i11)) {
                    return true;
                }
                return c(i10, parcel, parcel2);
        }
    }
}
