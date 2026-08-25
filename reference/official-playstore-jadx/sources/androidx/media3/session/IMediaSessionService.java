package androidx.media3.session;

import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import androidx.media3.session.IMediaController;

/* JADX INFO: loaded from: classes3.dex */
public interface IMediaSessionService extends IInterface {
    public static final String DESCRIPTOR = "androidx.media3.session.IMediaSessionService";

    public static class Default implements IMediaSessionService {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // androidx.media3.session.IMediaSessionService
        public void connect(IMediaController iMediaController, Bundle bundle) throws RemoteException {
        }
    }

    public static abstract class Stub extends Binder implements IMediaSessionService {
        static final int TRANSACTION_connect = 3001;

        public static class Proxy implements IMediaSessionService {
            private IBinder mRemote;

            public Proxy(IBinder iBinder) {
                this.mRemote = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            @Override // androidx.media3.session.IMediaSessionService
            public void connect(IMediaController iMediaController, Bundle bundle) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(IMediaSessionService.DESCRIPTOR);
                    parcelObtain.writeStrongInterface(iMediaController);
                    _Parcel.writeTypedObject(parcelObtain, bundle, 0);
                    this.mRemote.transact(3001, parcelObtain, null, 1);
                } finally {
                    parcelObtain.recycle();
                }
            }

            public String getInterfaceDescriptor() {
                return IMediaSessionService.DESCRIPTOR;
            }
        }

        public Stub() {
            attachInterface(this, IMediaSessionService.DESCRIPTOR);
        }

        public static IMediaSessionService asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(IMediaSessionService.DESCRIPTOR);
            return (iInterfaceQueryLocalInterface == null || !(iInterfaceQueryLocalInterface instanceof IMediaSessionService)) ? new Proxy(iBinder) : (IMediaSessionService) iInterfaceQueryLocalInterface;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int i10, Parcel parcel, Parcel parcel2, int i11) throws RemoteException {
            if (i10 >= 1 && i10 <= 16777215) {
                parcel.enforceInterface(IMediaSessionService.DESCRIPTOR);
            }
            if (i10 == 1598968902) {
                parcel2.writeString(IMediaSessionService.DESCRIPTOR);
                return true;
            }
            if (i10 != 3001) {
                return super.onTransact(i10, parcel, parcel2, i11);
            }
            connect(IMediaController.Stub.asInterface(parcel.readStrongBinder()), (Bundle) _Parcel.readTypedObject(parcel, Bundle.CREATOR));
            return true;
        }
    }

    public static class _Parcel {
        /* JADX INFO: Access modifiers changed from: private */
        public static <T> T readTypedObject(Parcel parcel, Parcelable.Creator<T> creator) {
            if (parcel.readInt() != 0) {
                return creator.createFromParcel(parcel);
            }
            return null;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static <T extends Parcelable> void writeTypedObject(Parcel parcel, T t2, int i10) {
            if (t2 == null) {
                parcel.writeInt(0);
            } else {
                parcel.writeInt(1);
                t2.writeToParcel(parcel, i10);
            }
        }
    }

    void connect(IMediaController iMediaController, Bundle bundle) throws RemoteException;
}
