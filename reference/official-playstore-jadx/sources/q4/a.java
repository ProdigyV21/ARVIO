package q4;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.d;
import k4.c;

/* JADX INFO: loaded from: classes4.dex */
public abstract class a extends Binder implements IInterface {
    @Override // android.os.Binder
    public boolean onTransact(int i10, Parcel parcel, Parcel parcel2, int i11) throws RemoteException {
        if (i10 <= 16777215) {
            parcel.enforceInterface(getInterfaceDescriptor());
        } else if (super.onTransact(i10, parcel, parcel2, i11)) {
            return true;
        }
        c cVar = (c) this;
        if (i10 == 1) {
            cVar.init(d.f0(parcel.readStrongBinder()));
            parcel2.writeNoException();
            return true;
        }
        if (i10 == 2) {
            String string = parcel.readString();
            int i12 = b.f21328a;
            boolean booleanFlagValue = cVar.getBooleanFlagValue(string, parcel.readInt() != 0, parcel.readInt());
            parcel2.writeNoException();
            parcel2.writeInt(booleanFlagValue ? 1 : 0);
            return true;
        }
        if (i10 == 3) {
            int intFlagValue = cVar.getIntFlagValue(parcel.readString(), parcel.readInt(), parcel.readInt());
            parcel2.writeNoException();
            parcel2.writeInt(intFlagValue);
            return true;
        }
        if (i10 == 4) {
            long longFlagValue = cVar.getLongFlagValue(parcel.readString(), parcel.readLong(), parcel.readInt());
            parcel2.writeNoException();
            parcel2.writeLong(longFlagValue);
            return true;
        }
        if (i10 != 5) {
            return false;
        }
        String stringFlagValue = cVar.getStringFlagValue(parcel.readString(), parcel.readString(), parcel.readInt());
        parcel2.writeNoException();
        parcel2.writeString(stringFlagValue);
        return true;
    }

    @Override // android.os.IInterface
    public IBinder asBinder() {
        return this;
    }
}
