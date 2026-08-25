package androidx.media3.session;

import android.app.PendingIntent;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public interface IMediaController extends IInterface {
    public static final String DESCRIPTOR = "androidx.media3.session.IMediaController";

    public static class Default implements IMediaController {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // androidx.media3.session.IMediaController
        public void onAvailableCommandsChangedFromPlayer(int i10, Bundle bundle) throws RemoteException {
        }

        @Override // androidx.media3.session.IMediaController
        public void onAvailableCommandsChangedFromSession(int i10, Bundle bundle, Bundle bundle2) throws RemoteException {
        }

        @Override // androidx.media3.session.IMediaController
        public void onChildrenChanged(int i10, String str, int i11, Bundle bundle) throws RemoteException {
        }

        @Override // androidx.media3.session.IMediaController
        public void onConnected(int i10, Bundle bundle) throws RemoteException {
        }

        @Override // androidx.media3.session.IMediaController
        public void onCustomCommand(int i10, Bundle bundle, Bundle bundle2) throws RemoteException {
        }

        @Override // androidx.media3.session.IMediaController
        public void onCustomCommandProgressUpdate(int i10, Bundle bundle, Bundle bundle2, Bundle bundle3) throws RemoteException {
        }

        @Override // androidx.media3.session.IMediaController
        public void onDisconnected(int i10) throws RemoteException {
        }

        @Override // androidx.media3.session.IMediaController
        public void onError(int i10, Bundle bundle) throws RemoteException {
        }

        @Override // androidx.media3.session.IMediaController
        public void onExtrasChanged(int i10, Bundle bundle) throws RemoteException {
        }

        @Override // androidx.media3.session.IMediaController
        public void onLibraryResult(int i10, Bundle bundle) throws RemoteException {
        }

        @Override // androidx.media3.session.IMediaController
        public void onPeriodicSessionPositionInfoChanged(int i10, Bundle bundle) throws RemoteException {
        }

        @Override // androidx.media3.session.IMediaController
        public void onPlayerInfoChanged(int i10, Bundle bundle, boolean z) throws RemoteException {
        }

        @Override // androidx.media3.session.IMediaController
        public void onPlayerInfoChangedWithExclusions(int i10, Bundle bundle, Bundle bundle2) throws RemoteException {
        }

        @Override // androidx.media3.session.IMediaController
        public void onRenderedFirstFrame(int i10) throws RemoteException {
        }

        @Override // androidx.media3.session.IMediaController
        public void onSearchResultChanged(int i10, String str, int i11, Bundle bundle) throws RemoteException {
        }

        @Override // androidx.media3.session.IMediaController
        public void onSessionActivityChanged(int i10, PendingIntent pendingIntent) throws RemoteException {
        }

        @Override // androidx.media3.session.IMediaController
        public void onSessionResult(int i10, Bundle bundle) throws RemoteException {
        }

        @Override // androidx.media3.session.IMediaController
        public void onSetCustomLayout(int i10, List<Bundle> list) throws RemoteException {
        }

        @Override // androidx.media3.session.IMediaController
        public void onSetMediaButtonPreferences(int i10, List<Bundle> list) throws RemoteException {
        }

        @Override // androidx.media3.session.IMediaController
        public void onSurfaceSizeChanged(int i10, int i11, int i12) throws RemoteException {
        }
    }

    public static abstract class Stub extends Binder implements IMediaController {
        static final int TRANSACTION_onAvailableCommandsChangedFromPlayer = 3009;
        static final int TRANSACTION_onAvailableCommandsChangedFromSession = 3010;
        static final int TRANSACTION_onChildrenChanged = 4001;
        static final int TRANSACTION_onConnected = 3001;
        static final int TRANSACTION_onCustomCommand = 3005;
        static final int TRANSACTION_onCustomCommandProgressUpdate = 3017;
        static final int TRANSACTION_onDisconnected = 3006;
        static final int TRANSACTION_onError = 3015;
        static final int TRANSACTION_onExtrasChanged = 3012;
        static final int TRANSACTION_onLibraryResult = 3003;
        static final int TRANSACTION_onPeriodicSessionPositionInfoChanged = 3008;
        static final int TRANSACTION_onPlayerInfoChanged = 3007;
        static final int TRANSACTION_onPlayerInfoChangedWithExclusions = 3013;
        static final int TRANSACTION_onRenderedFirstFrame = 3011;
        static final int TRANSACTION_onSearchResultChanged = 4002;
        static final int TRANSACTION_onSessionActivityChanged = 3014;
        static final int TRANSACTION_onSessionResult = 3002;
        static final int TRANSACTION_onSetCustomLayout = 3004;
        static final int TRANSACTION_onSetMediaButtonPreferences = 3016;
        static final int TRANSACTION_onSurfaceSizeChanged = 3018;

        public static class Proxy implements IMediaController {
            private IBinder mRemote;

            public Proxy(IBinder iBinder) {
                this.mRemote = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return IMediaController.DESCRIPTOR;
            }

            @Override // androidx.media3.session.IMediaController
            public void onAvailableCommandsChangedFromPlayer(int i10, Bundle bundle) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(IMediaController.DESCRIPTOR);
                    parcelObtain.writeInt(i10);
                    _Parcel.writeTypedObject(parcelObtain, bundle, 0);
                    this.mRemote.transact(Stub.TRANSACTION_onAvailableCommandsChangedFromPlayer, parcelObtain, null, 1);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // androidx.media3.session.IMediaController
            public void onAvailableCommandsChangedFromSession(int i10, Bundle bundle, Bundle bundle2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(IMediaController.DESCRIPTOR);
                    parcelObtain.writeInt(i10);
                    _Parcel.writeTypedObject(parcelObtain, bundle, 0);
                    _Parcel.writeTypedObject(parcelObtain, bundle2, 0);
                    this.mRemote.transact(Stub.TRANSACTION_onAvailableCommandsChangedFromSession, parcelObtain, null, 1);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // androidx.media3.session.IMediaController
            public void onChildrenChanged(int i10, String str, int i11, Bundle bundle) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(IMediaController.DESCRIPTOR);
                    parcelObtain.writeInt(i10);
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(i11);
                    _Parcel.writeTypedObject(parcelObtain, bundle, 0);
                    this.mRemote.transact(4001, parcelObtain, null, 1);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // androidx.media3.session.IMediaController
            public void onConnected(int i10, Bundle bundle) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(IMediaController.DESCRIPTOR);
                    parcelObtain.writeInt(i10);
                    _Parcel.writeTypedObject(parcelObtain, bundle, 0);
                    this.mRemote.transact(3001, parcelObtain, null, 1);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // androidx.media3.session.IMediaController
            public void onCustomCommand(int i10, Bundle bundle, Bundle bundle2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(IMediaController.DESCRIPTOR);
                    parcelObtain.writeInt(i10);
                    _Parcel.writeTypedObject(parcelObtain, bundle, 0);
                    _Parcel.writeTypedObject(parcelObtain, bundle2, 0);
                    this.mRemote.transact(3005, parcelObtain, null, 1);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // androidx.media3.session.IMediaController
            public void onCustomCommandProgressUpdate(int i10, Bundle bundle, Bundle bundle2, Bundle bundle3) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(IMediaController.DESCRIPTOR);
                    parcelObtain.writeInt(i10);
                    _Parcel.writeTypedObject(parcelObtain, bundle, 0);
                    _Parcel.writeTypedObject(parcelObtain, bundle2, 0);
                    _Parcel.writeTypedObject(parcelObtain, bundle3, 0);
                    this.mRemote.transact(Stub.TRANSACTION_onCustomCommandProgressUpdate, parcelObtain, null, 1);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // androidx.media3.session.IMediaController
            public void onDisconnected(int i10) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(IMediaController.DESCRIPTOR);
                    parcelObtain.writeInt(i10);
                    this.mRemote.transact(3006, parcelObtain, null, 1);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // androidx.media3.session.IMediaController
            public void onError(int i10, Bundle bundle) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(IMediaController.DESCRIPTOR);
                    parcelObtain.writeInt(i10);
                    _Parcel.writeTypedObject(parcelObtain, bundle, 0);
                    this.mRemote.transact(Stub.TRANSACTION_onError, parcelObtain, null, 1);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // androidx.media3.session.IMediaController
            public void onExtrasChanged(int i10, Bundle bundle) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(IMediaController.DESCRIPTOR);
                    parcelObtain.writeInt(i10);
                    _Parcel.writeTypedObject(parcelObtain, bundle, 0);
                    this.mRemote.transact(Stub.TRANSACTION_onExtrasChanged, parcelObtain, null, 1);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // androidx.media3.session.IMediaController
            public void onLibraryResult(int i10, Bundle bundle) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(IMediaController.DESCRIPTOR);
                    parcelObtain.writeInt(i10);
                    _Parcel.writeTypedObject(parcelObtain, bundle, 0);
                    this.mRemote.transact(3003, parcelObtain, null, 1);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // androidx.media3.session.IMediaController
            public void onPeriodicSessionPositionInfoChanged(int i10, Bundle bundle) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(IMediaController.DESCRIPTOR);
                    parcelObtain.writeInt(i10);
                    _Parcel.writeTypedObject(parcelObtain, bundle, 0);
                    this.mRemote.transact(Stub.TRANSACTION_onPeriodicSessionPositionInfoChanged, parcelObtain, null, 1);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // androidx.media3.session.IMediaController
            public void onPlayerInfoChanged(int i10, Bundle bundle, boolean z) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(IMediaController.DESCRIPTOR);
                    parcelObtain.writeInt(i10);
                    _Parcel.writeTypedObject(parcelObtain, bundle, 0);
                    parcelObtain.writeInt(z ? 1 : 0);
                    this.mRemote.transact(Stub.TRANSACTION_onPlayerInfoChanged, parcelObtain, null, 1);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // androidx.media3.session.IMediaController
            public void onPlayerInfoChangedWithExclusions(int i10, Bundle bundle, Bundle bundle2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(IMediaController.DESCRIPTOR);
                    parcelObtain.writeInt(i10);
                    _Parcel.writeTypedObject(parcelObtain, bundle, 0);
                    _Parcel.writeTypedObject(parcelObtain, bundle2, 0);
                    this.mRemote.transact(Stub.TRANSACTION_onPlayerInfoChangedWithExclusions, parcelObtain, null, 1);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // androidx.media3.session.IMediaController
            public void onRenderedFirstFrame(int i10) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(IMediaController.DESCRIPTOR);
                    parcelObtain.writeInt(i10);
                    this.mRemote.transact(Stub.TRANSACTION_onRenderedFirstFrame, parcelObtain, null, 1);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // androidx.media3.session.IMediaController
            public void onSearchResultChanged(int i10, String str, int i11, Bundle bundle) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(IMediaController.DESCRIPTOR);
                    parcelObtain.writeInt(i10);
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(i11);
                    _Parcel.writeTypedObject(parcelObtain, bundle, 0);
                    this.mRemote.transact(4002, parcelObtain, null, 1);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // androidx.media3.session.IMediaController
            public void onSessionActivityChanged(int i10, PendingIntent pendingIntent) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(IMediaController.DESCRIPTOR);
                    parcelObtain.writeInt(i10);
                    _Parcel.writeTypedObject(parcelObtain, pendingIntent, 0);
                    this.mRemote.transact(Stub.TRANSACTION_onSessionActivityChanged, parcelObtain, null, 1);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // androidx.media3.session.IMediaController
            public void onSessionResult(int i10, Bundle bundle) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(IMediaController.DESCRIPTOR);
                    parcelObtain.writeInt(i10);
                    _Parcel.writeTypedObject(parcelObtain, bundle, 0);
                    this.mRemote.transact(3002, parcelObtain, null, 1);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // androidx.media3.session.IMediaController
            public void onSetCustomLayout(int i10, List<Bundle> list) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(IMediaController.DESCRIPTOR);
                    parcelObtain.writeInt(i10);
                    _Parcel.writeTypedList(parcelObtain, list, 0);
                    this.mRemote.transact(3004, parcelObtain, null, 1);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // androidx.media3.session.IMediaController
            public void onSetMediaButtonPreferences(int i10, List<Bundle> list) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(IMediaController.DESCRIPTOR);
                    parcelObtain.writeInt(i10);
                    _Parcel.writeTypedList(parcelObtain, list, 0);
                    this.mRemote.transact(Stub.TRANSACTION_onSetMediaButtonPreferences, parcelObtain, null, 1);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // androidx.media3.session.IMediaController
            public void onSurfaceSizeChanged(int i10, int i11, int i12) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(IMediaController.DESCRIPTOR);
                    parcelObtain.writeInt(i10);
                    parcelObtain.writeInt(i11);
                    parcelObtain.writeInt(i12);
                    this.mRemote.transact(Stub.TRANSACTION_onSurfaceSizeChanged, parcelObtain, null, 1);
                } finally {
                    parcelObtain.recycle();
                }
            }
        }

        public Stub() {
            attachInterface(this, IMediaController.DESCRIPTOR);
        }

        public static IMediaController asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(IMediaController.DESCRIPTOR);
            return (iInterfaceQueryLocalInterface == null || !(iInterfaceQueryLocalInterface instanceof IMediaController)) ? new Proxy(iBinder) : (IMediaController) iInterfaceQueryLocalInterface;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int i10, Parcel parcel, Parcel parcel2, int i11) throws RemoteException {
            if (i10 >= 1 && i10 <= 16777215) {
                parcel.enforceInterface(IMediaController.DESCRIPTOR);
            }
            if (i10 == 1598968902) {
                parcel2.writeString(IMediaController.DESCRIPTOR);
                return true;
            }
            if (i10 == 4001) {
                onChildrenChanged(parcel.readInt(), parcel.readString(), parcel.readInt(), (Bundle) _Parcel.readTypedObject(parcel, Bundle.CREATOR));
            } else if (i10 != 4002) {
                switch (i10) {
                    case 3001:
                        onConnected(parcel.readInt(), (Bundle) _Parcel.readTypedObject(parcel, Bundle.CREATOR));
                        break;
                    case 3002:
                        onSessionResult(parcel.readInt(), (Bundle) _Parcel.readTypedObject(parcel, Bundle.CREATOR));
                        break;
                    case 3003:
                        onLibraryResult(parcel.readInt(), (Bundle) _Parcel.readTypedObject(parcel, Bundle.CREATOR));
                        break;
                    case 3004:
                        onSetCustomLayout(parcel.readInt(), parcel.createTypedArrayList(Bundle.CREATOR));
                        break;
                    case 3005:
                        int i12 = parcel.readInt();
                        Parcelable.Creator creator = Bundle.CREATOR;
                        onCustomCommand(i12, (Bundle) _Parcel.readTypedObject(parcel, creator), (Bundle) _Parcel.readTypedObject(parcel, creator));
                        break;
                    case 3006:
                        onDisconnected(parcel.readInt());
                        break;
                    case TRANSACTION_onPlayerInfoChanged /* 3007 */:
                        onPlayerInfoChanged(parcel.readInt(), (Bundle) _Parcel.readTypedObject(parcel, Bundle.CREATOR), parcel.readInt() != 0);
                        break;
                    case TRANSACTION_onPeriodicSessionPositionInfoChanged /* 3008 */:
                        onPeriodicSessionPositionInfoChanged(parcel.readInt(), (Bundle) _Parcel.readTypedObject(parcel, Bundle.CREATOR));
                        break;
                    case TRANSACTION_onAvailableCommandsChangedFromPlayer /* 3009 */:
                        onAvailableCommandsChangedFromPlayer(parcel.readInt(), (Bundle) _Parcel.readTypedObject(parcel, Bundle.CREATOR));
                        break;
                    case TRANSACTION_onAvailableCommandsChangedFromSession /* 3010 */:
                        int i13 = parcel.readInt();
                        Parcelable.Creator creator2 = Bundle.CREATOR;
                        onAvailableCommandsChangedFromSession(i13, (Bundle) _Parcel.readTypedObject(parcel, creator2), (Bundle) _Parcel.readTypedObject(parcel, creator2));
                        break;
                    case TRANSACTION_onRenderedFirstFrame /* 3011 */:
                        onRenderedFirstFrame(parcel.readInt());
                        break;
                    case TRANSACTION_onExtrasChanged /* 3012 */:
                        onExtrasChanged(parcel.readInt(), (Bundle) _Parcel.readTypedObject(parcel, Bundle.CREATOR));
                        break;
                    case TRANSACTION_onPlayerInfoChangedWithExclusions /* 3013 */:
                        int i14 = parcel.readInt();
                        Parcelable.Creator creator3 = Bundle.CREATOR;
                        onPlayerInfoChangedWithExclusions(i14, (Bundle) _Parcel.readTypedObject(parcel, creator3), (Bundle) _Parcel.readTypedObject(parcel, creator3));
                        break;
                    case TRANSACTION_onSessionActivityChanged /* 3014 */:
                        onSessionActivityChanged(parcel.readInt(), (PendingIntent) _Parcel.readTypedObject(parcel, PendingIntent.CREATOR));
                        break;
                    case TRANSACTION_onError /* 3015 */:
                        onError(parcel.readInt(), (Bundle) _Parcel.readTypedObject(parcel, Bundle.CREATOR));
                        break;
                    case TRANSACTION_onSetMediaButtonPreferences /* 3016 */:
                        onSetMediaButtonPreferences(parcel.readInt(), parcel.createTypedArrayList(Bundle.CREATOR));
                        break;
                    case TRANSACTION_onCustomCommandProgressUpdate /* 3017 */:
                        int i15 = parcel.readInt();
                        Parcelable.Creator creator4 = Bundle.CREATOR;
                        onCustomCommandProgressUpdate(i15, (Bundle) _Parcel.readTypedObject(parcel, creator4), (Bundle) _Parcel.readTypedObject(parcel, creator4), (Bundle) _Parcel.readTypedObject(parcel, creator4));
                        break;
                    case TRANSACTION_onSurfaceSizeChanged /* 3018 */:
                        onSurfaceSizeChanged(parcel.readInt(), parcel.readInt(), parcel.readInt());
                        break;
                    default:
                        return super.onTransact(i10, parcel, parcel2, i11);
                }
            } else {
                onSearchResultChanged(parcel.readInt(), parcel.readString(), parcel.readInt(), (Bundle) _Parcel.readTypedObject(parcel, Bundle.CREATOR));
            }
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
        public static <T extends Parcelable> void writeTypedList(Parcel parcel, List<T> list, int i10) {
            if (list == null) {
                parcel.writeInt(-1);
                return;
            }
            int size = list.size();
            parcel.writeInt(size);
            for (int i11 = 0; i11 < size; i11++) {
                writeTypedObject(parcel, list.get(i11), i10);
            }
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

    void onAvailableCommandsChangedFromPlayer(int i10, Bundle bundle) throws RemoteException;

    void onAvailableCommandsChangedFromSession(int i10, Bundle bundle, Bundle bundle2) throws RemoteException;

    void onChildrenChanged(int i10, String str, int i11, Bundle bundle) throws RemoteException;

    void onConnected(int i10, Bundle bundle) throws RemoteException;

    void onCustomCommand(int i10, Bundle bundle, Bundle bundle2) throws RemoteException;

    void onCustomCommandProgressUpdate(int i10, Bundle bundle, Bundle bundle2, Bundle bundle3) throws RemoteException;

    void onDisconnected(int i10) throws RemoteException;

    void onError(int i10, Bundle bundle) throws RemoteException;

    void onExtrasChanged(int i10, Bundle bundle) throws RemoteException;

    void onLibraryResult(int i10, Bundle bundle) throws RemoteException;

    void onPeriodicSessionPositionInfoChanged(int i10, Bundle bundle) throws RemoteException;

    void onPlayerInfoChanged(int i10, Bundle bundle, boolean z) throws RemoteException;

    void onPlayerInfoChangedWithExclusions(int i10, Bundle bundle, Bundle bundle2) throws RemoteException;

    void onRenderedFirstFrame(int i10) throws RemoteException;

    void onSearchResultChanged(int i10, String str, int i11, Bundle bundle) throws RemoteException;

    void onSessionActivityChanged(int i10, PendingIntent pendingIntent) throws RemoteException;

    void onSessionResult(int i10, Bundle bundle) throws RemoteException;

    void onSetCustomLayout(int i10, List<Bundle> list) throws RemoteException;

    void onSetMediaButtonPreferences(int i10, List<Bundle> list) throws RemoteException;

    void onSurfaceSizeChanged(int i10, int i11, int i12) throws RemoteException;
}
