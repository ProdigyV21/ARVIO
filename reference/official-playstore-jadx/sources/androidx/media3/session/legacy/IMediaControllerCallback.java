package androidx.media3.session.legacy;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* JADX INFO: loaded from: classes3.dex */
public interface IMediaControllerCallback extends IInterface {

    public static abstract class Stub extends Binder implements IMediaControllerCallback {
        private static final String DESCRIPTOR = "android.support.v4.media.session.IMediaControllerCallback";
        static final int TRANSACTION_onCaptioningEnabledChanged = 11;
        static final int TRANSACTION_onPlaybackStateChanged = 3;
        static final int TRANSACTION_onRepeatModeChanged = 9;
        static final int TRANSACTION_onSessionReady = 13;
        static final int TRANSACTION_onShuffleModeChanged = 12;

        public static class Proxy implements IMediaControllerCallback {
            public static IMediaControllerCallback defaultImpl;
            private IBinder remote;

            public Proxy(IBinder iBinder) {
                this.remote = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.remote;
            }

            public String getInterfaceDescriptor() {
                return Stub.DESCRIPTOR;
            }

            @Override // androidx.media3.session.legacy.IMediaControllerCallback
            public void onCaptioningEnabledChanged(boolean z) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(z ? 1 : 0);
                    if (this.remote.transact(11, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    IMediaControllerCallback defaultImpl2 = Stub.getDefaultImpl();
                    defaultImpl2.getClass();
                    defaultImpl2.onCaptioningEnabledChanged(z);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // androidx.media3.session.legacy.IMediaControllerCallback
            public void onPlaybackStateChanged(PlaybackStateCompat playbackStateCompat) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (playbackStateCompat != null) {
                        parcelObtain.writeInt(1);
                        playbackStateCompat.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (this.remote.transact(3, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        parcelObtain.recycle();
                        return;
                    }
                    IMediaControllerCallback defaultImpl2 = Stub.getDefaultImpl();
                    defaultImpl2.getClass();
                    defaultImpl2.onPlaybackStateChanged(playbackStateCompat);
                    parcelObtain.recycle();
                } catch (Throwable th) {
                    parcelObtain.recycle();
                    throw th;
                }
            }

            @Override // androidx.media3.session.legacy.IMediaControllerCallback
            public void onRepeatModeChanged(int i10) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i10);
                    if (this.remote.transact(9, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    IMediaControllerCallback defaultImpl2 = Stub.getDefaultImpl();
                    defaultImpl2.getClass();
                    defaultImpl2.onRepeatModeChanged(i10);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // androidx.media3.session.legacy.IMediaControllerCallback
            public void onSessionReady() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (this.remote.transact(13, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    IMediaControllerCallback defaultImpl2 = Stub.getDefaultImpl();
                    defaultImpl2.getClass();
                    defaultImpl2.onSessionReady();
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // androidx.media3.session.legacy.IMediaControllerCallback
            public void onShuffleModeChanged(int i10) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i10);
                    if (this.remote.transact(12, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    IMediaControllerCallback defaultImpl2 = Stub.getDefaultImpl();
                    defaultImpl2.getClass();
                    defaultImpl2.onShuffleModeChanged(i10);
                } finally {
                    parcelObtain.recycle();
                }
            }
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IMediaControllerCallback asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            return (iInterfaceQueryLocalInterface == null || !(iInterfaceQueryLocalInterface instanceof IMediaControllerCallback)) ? new Proxy(iBinder) : (IMediaControllerCallback) iInterfaceQueryLocalInterface;
        }

        public static IMediaControllerCallback getDefaultImpl() {
            return Proxy.defaultImpl;
        }

        public static boolean setDefaultImpl(IMediaControllerCallback iMediaControllerCallback) {
            if (Proxy.defaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iMediaControllerCallback == null) {
                return false;
            }
            Proxy.defaultImpl = iMediaControllerCallback;
            return true;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int i10, Parcel parcel, Parcel parcel2, int i11) throws RemoteException {
            if (i10 == 3) {
                parcel.enforceInterface(DESCRIPTOR);
                onPlaybackStateChanged(parcel.readInt() != 0 ? PlaybackStateCompat.CREATOR.createFromParcel(parcel) : null);
                return true;
            }
            if (i10 == 9) {
                parcel.enforceInterface(DESCRIPTOR);
                onRepeatModeChanged(parcel.readInt());
                return true;
            }
            if (i10 == 1598968902) {
                parcel2.getClass();
                parcel2.writeString(DESCRIPTOR);
                return true;
            }
            switch (i10) {
                case 11:
                    parcel.enforceInterface(DESCRIPTOR);
                    onCaptioningEnabledChanged(parcel.readInt() != 0);
                    return true;
                case 12:
                    parcel.enforceInterface(DESCRIPTOR);
                    onShuffleModeChanged(parcel.readInt());
                    return true;
                case 13:
                    parcel.enforceInterface(DESCRIPTOR);
                    onSessionReady();
                    return true;
                default:
                    return super.onTransact(i10, parcel, parcel2, i11);
            }
        }
    }

    void onCaptioningEnabledChanged(boolean z) throws RemoteException;

    void onPlaybackStateChanged(PlaybackStateCompat playbackStateCompat) throws RemoteException;

    void onRepeatModeChanged(int i10) throws RemoteException;

    void onSessionReady() throws RemoteException;

    void onShuffleModeChanged(int i10) throws RemoteException;
}
