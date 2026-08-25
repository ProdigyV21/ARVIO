package androidx.media3.session.legacy;

import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import androidx.media3.session.legacy.IMediaControllerCallback;

/* JADX INFO: loaded from: classes3.dex */
public interface IMediaSession extends IInterface {

    public static abstract class Stub extends Binder implements IMediaSession {
        private static final String DESCRIPTOR = "android.support.v4.media.session.IMediaSession";
        static final int TRANSACTION_getPlaybackState = 28;
        static final int TRANSACTION_getRepeatMode = 37;
        static final int TRANSACTION_getSessionInfo = 50;
        static final int TRANSACTION_getShuffleMode = 47;
        static final int TRANSACTION_isCaptioningEnabled = 45;
        static final int TRANSACTION_registerCallbackListener = 3;
        static final int TRANSACTION_unregisterCallbackListener = 4;

        public static class Proxy implements IMediaSession {
            public static IMediaSession defaultImpl;
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

            @Override // androidx.media3.session.legacy.IMediaSession
            public PlaybackStateCompat getPlaybackState() throws RemoteException {
                PlaybackStateCompat playbackStateCompatCreateFromParcel;
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (this.remote.transact(28, parcelObtain, parcelObtain2, 0) || Stub.getDefaultImpl() == null) {
                        parcelObtain2.readException();
                        playbackStateCompatCreateFromParcel = parcelObtain2.readInt() != 0 ? PlaybackStateCompat.CREATOR.createFromParcel(parcelObtain2) : null;
                    } else {
                        IMediaSession defaultImpl2 = Stub.getDefaultImpl();
                        defaultImpl2.getClass();
                        playbackStateCompatCreateFromParcel = defaultImpl2.getPlaybackState();
                    }
                    return playbackStateCompatCreateFromParcel;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // androidx.media3.session.legacy.IMediaSession
            public int getRepeatMode() throws RemoteException {
                int repeatMode;
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (this.remote.transact(Stub.TRANSACTION_getRepeatMode, parcelObtain, parcelObtain2, 0) || Stub.getDefaultImpl() == null) {
                        parcelObtain2.readException();
                        repeatMode = parcelObtain2.readInt();
                    } else {
                        IMediaSession defaultImpl2 = Stub.getDefaultImpl();
                        defaultImpl2.getClass();
                        repeatMode = defaultImpl2.getRepeatMode();
                    }
                    return repeatMode;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // androidx.media3.session.legacy.IMediaSession
            public Bundle getSessionInfo() throws RemoteException {
                Bundle sessionInfo;
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (this.remote.transact(50, parcelObtain, parcelObtain2, 0) || Stub.getDefaultImpl() == null) {
                        parcelObtain2.readException();
                        sessionInfo = parcelObtain2.readInt() != 0 ? (Bundle) Bundle.CREATOR.createFromParcel(parcelObtain2) : null;
                    } else {
                        IMediaSession defaultImpl2 = Stub.getDefaultImpl();
                        defaultImpl2.getClass();
                        sessionInfo = defaultImpl2.getSessionInfo();
                    }
                    return sessionInfo;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // androidx.media3.session.legacy.IMediaSession
            public int getShuffleMode() throws RemoteException {
                int shuffleMode;
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (this.remote.transact(Stub.TRANSACTION_getShuffleMode, parcelObtain, parcelObtain2, 0) || Stub.getDefaultImpl() == null) {
                        parcelObtain2.readException();
                        shuffleMode = parcelObtain2.readInt();
                    } else {
                        IMediaSession defaultImpl2 = Stub.getDefaultImpl();
                        defaultImpl2.getClass();
                        shuffleMode = defaultImpl2.getShuffleMode();
                    }
                    return shuffleMode;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // androidx.media3.session.legacy.IMediaSession
            public boolean isCaptioningEnabled() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (this.remote.transact(45, parcelObtain, parcelObtain2, 0) || Stub.getDefaultImpl() == null) {
                        parcelObtain2.readException();
                        return parcelObtain2.readInt() != 0;
                    }
                    IMediaSession defaultImpl2 = Stub.getDefaultImpl();
                    defaultImpl2.getClass();
                    return defaultImpl2.isCaptioningEnabled();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // androidx.media3.session.legacy.IMediaSession
            public void registerCallbackListener(IMediaControllerCallback iMediaControllerCallback) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iMediaControllerCallback != null ? iMediaControllerCallback.asBinder() : null);
                    if (this.remote.transact(3, parcelObtain, parcelObtain2, 0) || Stub.getDefaultImpl() == null) {
                        parcelObtain2.readException();
                    } else {
                        IMediaSession defaultImpl2 = Stub.getDefaultImpl();
                        defaultImpl2.getClass();
                        defaultImpl2.registerCallbackListener(iMediaControllerCallback);
                    }
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                } catch (Throwable th) {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                    throw th;
                }
            }

            @Override // androidx.media3.session.legacy.IMediaSession
            public void unregisterCallbackListener(IMediaControllerCallback iMediaControllerCallback) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iMediaControllerCallback != null ? iMediaControllerCallback.asBinder() : null);
                    if (this.remote.transact(4, parcelObtain, parcelObtain2, 0) || Stub.getDefaultImpl() == null) {
                        parcelObtain2.readException();
                    } else {
                        IMediaSession defaultImpl2 = Stub.getDefaultImpl();
                        defaultImpl2.getClass();
                        defaultImpl2.unregisterCallbackListener(iMediaControllerCallback);
                    }
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                } catch (Throwable th) {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                    throw th;
                }
            }
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IMediaSession asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            return (iInterfaceQueryLocalInterface == null || !(iInterfaceQueryLocalInterface instanceof IMediaSession)) ? new Proxy(iBinder) : (IMediaSession) iInterfaceQueryLocalInterface;
        }

        public static IMediaSession getDefaultImpl() {
            return Proxy.defaultImpl;
        }

        public static boolean setDefaultImpl(IMediaSession iMediaSession) {
            if (Proxy.defaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iMediaSession == null) {
                return false;
            }
            Proxy.defaultImpl = iMediaSession;
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
                registerCallbackListener(IMediaControllerCallback.Stub.asInterface(parcel.readStrongBinder()));
                parcel2.getClass();
                parcel2.writeNoException();
                return true;
            }
            if (i10 == 4) {
                parcel.enforceInterface(DESCRIPTOR);
                unregisterCallbackListener(IMediaControllerCallback.Stub.asInterface(parcel.readStrongBinder()));
                parcel2.getClass();
                parcel2.writeNoException();
                return true;
            }
            if (i10 == 28) {
                parcel.enforceInterface(DESCRIPTOR);
                PlaybackStateCompat playbackState = getPlaybackState();
                parcel2.getClass();
                parcel2.writeNoException();
                if (playbackState != null) {
                    parcel2.writeInt(1);
                    playbackState.writeToParcel(parcel2, 1);
                } else {
                    parcel2.writeInt(0);
                }
                return true;
            }
            if (i10 == TRANSACTION_getRepeatMode) {
                parcel.enforceInterface(DESCRIPTOR);
                int repeatMode = getRepeatMode();
                parcel2.getClass();
                parcel2.writeNoException();
                parcel2.writeInt(repeatMode);
                return true;
            }
            if (i10 == 45) {
                parcel.enforceInterface(DESCRIPTOR);
                boolean zIsCaptioningEnabled = isCaptioningEnabled();
                parcel2.getClass();
                parcel2.writeNoException();
                parcel2.writeInt(zIsCaptioningEnabled ? 1 : 0);
                return true;
            }
            if (i10 == TRANSACTION_getShuffleMode) {
                parcel.enforceInterface(DESCRIPTOR);
                int shuffleMode = getShuffleMode();
                parcel2.getClass();
                parcel2.writeNoException();
                parcel2.writeInt(shuffleMode);
                return true;
            }
            if (i10 != 50) {
                if (i10 != 1598968902) {
                    return super.onTransact(i10, parcel, parcel2, i11);
                }
                parcel2.getClass();
                parcel2.writeString(DESCRIPTOR);
                return true;
            }
            parcel.enforceInterface(DESCRIPTOR);
            Bundle sessionInfo = getSessionInfo();
            parcel2.getClass();
            parcel2.writeNoException();
            if (sessionInfo != null) {
                parcel2.writeInt(1);
                sessionInfo.writeToParcel(parcel2, 1);
            } else {
                parcel2.writeInt(0);
            }
            return true;
        }
    }

    PlaybackStateCompat getPlaybackState() throws RemoteException;

    int getRepeatMode() throws RemoteException;

    Bundle getSessionInfo() throws RemoteException;

    int getShuffleMode() throws RemoteException;

    boolean isCaptioningEnabled() throws RemoteException;

    void registerCallbackListener(IMediaControllerCallback iMediaControllerCallback) throws RemoteException;

    void unregisterCallbackListener(IMediaControllerCallback iMediaControllerCallback) throws RemoteException;
}
