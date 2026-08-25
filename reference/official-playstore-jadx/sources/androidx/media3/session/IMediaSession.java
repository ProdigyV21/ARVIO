package androidx.media3.session;

import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import android.view.Surface;
import androidx.media3.session.IMediaController;

/* JADX INFO: loaded from: classes3.dex */
public interface IMediaSession extends IInterface {
    public static final String DESCRIPTOR = "androidx.media3.session.IMediaSession";

    public static class Default implements IMediaSession {
        @Override // androidx.media3.session.IMediaSession
        public void addMediaItem(IMediaController iMediaController, int i10, Bundle bundle) throws RemoteException {
        }

        @Override // androidx.media3.session.IMediaSession
        public void addMediaItemWithIndex(IMediaController iMediaController, int i10, int i11, Bundle bundle) throws RemoteException {
        }

        @Override // androidx.media3.session.IMediaSession
        public void addMediaItems(IMediaController iMediaController, int i10, IBinder iBinder) throws RemoteException {
        }

        @Override // androidx.media3.session.IMediaSession
        public void addMediaItemsWithIndex(IMediaController iMediaController, int i10, int i11, IBinder iBinder) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // androidx.media3.session.IMediaSession
        public void clearMediaItems(IMediaController iMediaController, int i10) throws RemoteException {
        }

        @Override // androidx.media3.session.IMediaSession
        public void connect(IMediaController iMediaController, int i10, Bundle bundle) throws RemoteException {
        }

        @Override // androidx.media3.session.IMediaSession
        public void decreaseDeviceVolume(IMediaController iMediaController, int i10) throws RemoteException {
        }

        @Override // androidx.media3.session.IMediaSession
        public void decreaseDeviceVolumeWithFlags(IMediaController iMediaController, int i10, int i11) throws RemoteException {
        }

        @Override // androidx.media3.session.IMediaSession
        public void flushCommandQueue(IMediaController iMediaController) throws RemoteException {
        }

        @Override // androidx.media3.session.IMediaSession
        public void getChildren(IMediaController iMediaController, int i10, String str, int i11, int i12, Bundle bundle) throws RemoteException {
        }

        @Override // androidx.media3.session.IMediaSession
        public void getItem(IMediaController iMediaController, int i10, String str) throws RemoteException {
        }

        @Override // androidx.media3.session.IMediaSession
        public void getLibraryRoot(IMediaController iMediaController, int i10, Bundle bundle) throws RemoteException {
        }

        @Override // androidx.media3.session.IMediaSession
        public void getSearchResult(IMediaController iMediaController, int i10, String str, int i11, int i12, Bundle bundle) throws RemoteException {
        }

        @Override // androidx.media3.session.IMediaSession
        public void increaseDeviceVolume(IMediaController iMediaController, int i10) throws RemoteException {
        }

        @Override // androidx.media3.session.IMediaSession
        public void increaseDeviceVolumeWithFlags(IMediaController iMediaController, int i10, int i11) throws RemoteException {
        }

        @Override // androidx.media3.session.IMediaSession
        public void moveMediaItem(IMediaController iMediaController, int i10, int i11, int i12) throws RemoteException {
        }

        @Override // androidx.media3.session.IMediaSession
        public void moveMediaItems(IMediaController iMediaController, int i10, int i11, int i12, int i13) throws RemoteException {
        }

        @Override // androidx.media3.session.IMediaSession
        public void mute(IMediaController iMediaController, int i10) throws RemoteException {
        }

        @Override // androidx.media3.session.IMediaSession
        public void onControllerResult(IMediaController iMediaController, int i10, Bundle bundle) throws RemoteException {
        }

        @Override // androidx.media3.session.IMediaSession
        public void onCustomCommand(IMediaController iMediaController, int i10, Bundle bundle, Bundle bundle2) throws RemoteException {
        }

        @Override // androidx.media3.session.IMediaSession
        public void onCustomCommandWithProgressUpdate(IMediaController iMediaController, int i10, Bundle bundle, Bundle bundle2, boolean z) throws RemoteException {
        }

        @Override // androidx.media3.session.IMediaSession
        public void onSurfaceSizeChanged(IMediaController iMediaController, int i10, int i11, int i12) throws RemoteException {
        }

        @Override // androidx.media3.session.IMediaSession
        public void pause(IMediaController iMediaController, int i10) throws RemoteException {
        }

        @Override // androidx.media3.session.IMediaSession
        public void play(IMediaController iMediaController, int i10) throws RemoteException {
        }

        @Override // androidx.media3.session.IMediaSession
        public void prepare(IMediaController iMediaController, int i10) throws RemoteException {
        }

        @Override // androidx.media3.session.IMediaSession
        public void release(IMediaController iMediaController, int i10) throws RemoteException {
        }

        @Override // androidx.media3.session.IMediaSession
        public void removeMediaItem(IMediaController iMediaController, int i10, int i11) throws RemoteException {
        }

        @Override // androidx.media3.session.IMediaSession
        public void removeMediaItems(IMediaController iMediaController, int i10, int i11, int i12) throws RemoteException {
        }

        @Override // androidx.media3.session.IMediaSession
        public void replaceMediaItem(IMediaController iMediaController, int i10, int i11, Bundle bundle) throws RemoteException {
        }

        @Override // androidx.media3.session.IMediaSession
        public void replaceMediaItems(IMediaController iMediaController, int i10, int i11, int i12, IBinder iBinder) throws RemoteException {
        }

        @Override // androidx.media3.session.IMediaSession
        public void search(IMediaController iMediaController, int i10, String str, Bundle bundle) throws RemoteException {
        }

        @Override // androidx.media3.session.IMediaSession
        public void seekBack(IMediaController iMediaController, int i10) throws RemoteException {
        }

        @Override // androidx.media3.session.IMediaSession
        public void seekForward(IMediaController iMediaController, int i10) throws RemoteException {
        }

        @Override // androidx.media3.session.IMediaSession
        public void seekTo(IMediaController iMediaController, int i10, long j10) throws RemoteException {
        }

        @Override // androidx.media3.session.IMediaSession
        public void seekToDefaultPosition(IMediaController iMediaController, int i10) throws RemoteException {
        }

        @Override // androidx.media3.session.IMediaSession
        public void seekToDefaultPositionWithMediaItemIndex(IMediaController iMediaController, int i10, int i11) throws RemoteException {
        }

        @Override // androidx.media3.session.IMediaSession
        public void seekToNext(IMediaController iMediaController, int i10) throws RemoteException {
        }

        @Override // androidx.media3.session.IMediaSession
        public void seekToNextMediaItem(IMediaController iMediaController, int i10) throws RemoteException {
        }

        @Override // androidx.media3.session.IMediaSession
        public void seekToPrevious(IMediaController iMediaController, int i10) throws RemoteException {
        }

        @Override // androidx.media3.session.IMediaSession
        public void seekToPreviousMediaItem(IMediaController iMediaController, int i10) throws RemoteException {
        }

        @Override // androidx.media3.session.IMediaSession
        public void seekToWithMediaItemIndex(IMediaController iMediaController, int i10, int i11, long j10) throws RemoteException {
        }

        @Override // androidx.media3.session.IMediaSession
        public void setAudioAttributes(IMediaController iMediaController, int i10, Bundle bundle, boolean z) throws RemoteException {
        }

        @Override // androidx.media3.session.IMediaSession
        public void setDeviceMuted(IMediaController iMediaController, int i10, boolean z) throws RemoteException {
        }

        @Override // androidx.media3.session.IMediaSession
        public void setDeviceMutedWithFlags(IMediaController iMediaController, int i10, boolean z, int i11) throws RemoteException {
        }

        @Override // androidx.media3.session.IMediaSession
        public void setDeviceVolume(IMediaController iMediaController, int i10, int i11) throws RemoteException {
        }

        @Override // androidx.media3.session.IMediaSession
        public void setDeviceVolumeWithFlags(IMediaController iMediaController, int i10, int i11, int i12) throws RemoteException {
        }

        @Override // androidx.media3.session.IMediaSession
        public void setMediaItem(IMediaController iMediaController, int i10, Bundle bundle) throws RemoteException {
        }

        @Override // androidx.media3.session.IMediaSession
        public void setMediaItemWithResetPosition(IMediaController iMediaController, int i10, Bundle bundle, boolean z) throws RemoteException {
        }

        @Override // androidx.media3.session.IMediaSession
        public void setMediaItemWithStartPosition(IMediaController iMediaController, int i10, Bundle bundle, long j10) throws RemoteException {
        }

        @Override // androidx.media3.session.IMediaSession
        public void setMediaItems(IMediaController iMediaController, int i10, IBinder iBinder) throws RemoteException {
        }

        @Override // androidx.media3.session.IMediaSession
        public void setMediaItemsWithResetPosition(IMediaController iMediaController, int i10, IBinder iBinder, boolean z) throws RemoteException {
        }

        @Override // androidx.media3.session.IMediaSession
        public void setMediaItemsWithStartIndex(IMediaController iMediaController, int i10, IBinder iBinder, int i11, long j10) throws RemoteException {
        }

        @Override // androidx.media3.session.IMediaSession
        public void setPlayWhenReady(IMediaController iMediaController, int i10, boolean z) throws RemoteException {
        }

        @Override // androidx.media3.session.IMediaSession
        public void setPlaybackParameters(IMediaController iMediaController, int i10, Bundle bundle) throws RemoteException {
        }

        @Override // androidx.media3.session.IMediaSession
        public void setPlaybackSpeed(IMediaController iMediaController, int i10, float f10) throws RemoteException {
        }

        @Override // androidx.media3.session.IMediaSession
        public void setPlaylistMetadata(IMediaController iMediaController, int i10, Bundle bundle) throws RemoteException {
        }

        @Override // androidx.media3.session.IMediaSession
        public void setRating(IMediaController iMediaController, int i10, Bundle bundle) throws RemoteException {
        }

        @Override // androidx.media3.session.IMediaSession
        public void setRatingWithMediaId(IMediaController iMediaController, int i10, String str, Bundle bundle) throws RemoteException {
        }

        @Override // androidx.media3.session.IMediaSession
        public void setRepeatMode(IMediaController iMediaController, int i10, int i11) throws RemoteException {
        }

        @Override // androidx.media3.session.IMediaSession
        public void setShuffleModeEnabled(IMediaController iMediaController, int i10, boolean z) throws RemoteException {
        }

        @Override // androidx.media3.session.IMediaSession
        public void setTrackSelectionParameters(IMediaController iMediaController, int i10, Bundle bundle) throws RemoteException {
        }

        @Override // androidx.media3.session.IMediaSession
        public void setVideoSurface(IMediaController iMediaController, int i10, Surface surface) throws RemoteException {
        }

        @Override // androidx.media3.session.IMediaSession
        public void setVideoSurfaceWithSize(IMediaController iMediaController, int i10, Surface surface, int i11, int i12) throws RemoteException {
        }

        @Override // androidx.media3.session.IMediaSession
        public void setVolume(IMediaController iMediaController, int i10, float f10) throws RemoteException {
        }

        @Override // androidx.media3.session.IMediaSession
        public void stop(IMediaController iMediaController, int i10) throws RemoteException {
        }

        @Override // androidx.media3.session.IMediaSession
        public void subscribe(IMediaController iMediaController, int i10, String str, Bundle bundle) throws RemoteException {
        }

        @Override // androidx.media3.session.IMediaSession
        public void unmute(IMediaController iMediaController, int i10) throws RemoteException {
        }

        @Override // androidx.media3.session.IMediaSession
        public void unsubscribe(IMediaController iMediaController, int i10, String str) throws RemoteException {
        }
    }

    public static abstract class Stub extends Binder implements IMediaSession {
        static final int TRANSACTION_addMediaItem = 3029;
        static final int TRANSACTION_addMediaItemWithIndex = 3030;
        static final int TRANSACTION_addMediaItems = 3031;
        static final int TRANSACTION_addMediaItemsWithIndex = 3032;
        static final int TRANSACTION_clearMediaItems = 3021;
        static final int TRANSACTION_connect = 3015;
        static final int TRANSACTION_decreaseDeviceVolume = 3005;
        static final int TRANSACTION_decreaseDeviceVolumeWithFlags = 3053;
        static final int TRANSACTION_flushCommandQueue = 3045;
        static final int TRANSACTION_getChildren = 4003;
        static final int TRANSACTION_getItem = 4002;
        static final int TRANSACTION_getLibraryRoot = 4001;
        static final int TRANSACTION_getSearchResult = 4005;
        static final int TRANSACTION_increaseDeviceVolume = 3004;
        static final int TRANSACTION_increaseDeviceVolumeWithFlags = 3052;
        static final int TRANSACTION_moveMediaItem = 3022;
        static final int TRANSACTION_moveMediaItems = 3023;
        static final int TRANSACTION_mute = 3058;
        static final int TRANSACTION_onControllerResult = 3014;
        static final int TRANSACTION_onCustomCommand = 3016;
        static final int TRANSACTION_onCustomCommandWithProgressUpdate = 3060;
        static final int TRANSACTION_onSurfaceSizeChanged = 3062;
        static final int TRANSACTION_pause = 3025;
        static final int TRANSACTION_play = 3024;
        static final int TRANSACTION_prepare = 3026;
        static final int TRANSACTION_release = 3035;
        static final int TRANSACTION_removeMediaItem = 3019;
        static final int TRANSACTION_removeMediaItems = 3020;
        static final int TRANSACTION_replaceMediaItem = 3055;
        static final int TRANSACTION_replaceMediaItems = 3056;
        static final int TRANSACTION_search = 4004;
        static final int TRANSACTION_seekBack = 3040;
        static final int TRANSACTION_seekForward = 3041;
        static final int TRANSACTION_seekTo = 3038;
        static final int TRANSACTION_seekToDefaultPosition = 3036;
        static final int TRANSACTION_seekToDefaultPositionWithMediaItemIndex = 3037;
        static final int TRANSACTION_seekToNext = 3047;
        static final int TRANSACTION_seekToNextMediaItem = 3043;
        static final int TRANSACTION_seekToPrevious = 3046;
        static final int TRANSACTION_seekToPreviousMediaItem = 3042;
        static final int TRANSACTION_seekToWithMediaItemIndex = 3039;
        static final int TRANSACTION_setAudioAttributes = 3057;
        static final int TRANSACTION_setDeviceMuted = 3006;
        static final int TRANSACTION_setDeviceMutedWithFlags = 3054;
        static final int TRANSACTION_setDeviceVolume = 3003;
        static final int TRANSACTION_setDeviceVolumeWithFlags = 3051;
        static final int TRANSACTION_setMediaItem = 3007;
        static final int TRANSACTION_setMediaItemWithResetPosition = 3009;
        static final int TRANSACTION_setMediaItemWithStartPosition = 3008;
        static final int TRANSACTION_setMediaItems = 3010;
        static final int TRANSACTION_setMediaItemsWithResetPosition = 3011;
        static final int TRANSACTION_setMediaItemsWithStartIndex = 3012;
        static final int TRANSACTION_setPlayWhenReady = 3013;
        static final int TRANSACTION_setPlaybackParameters = 3027;
        static final int TRANSACTION_setPlaybackSpeed = 3028;
        static final int TRANSACTION_setPlaylistMetadata = 3033;
        static final int TRANSACTION_setRating = 3050;
        static final int TRANSACTION_setRatingWithMediaId = 3049;
        static final int TRANSACTION_setRepeatMode = 3017;
        static final int TRANSACTION_setShuffleModeEnabled = 3018;
        static final int TRANSACTION_setTrackSelectionParameters = 3048;
        static final int TRANSACTION_setVideoSurface = 3044;
        static final int TRANSACTION_setVideoSurfaceWithSize = 3061;
        static final int TRANSACTION_setVolume = 3002;
        static final int TRANSACTION_stop = 3034;
        static final int TRANSACTION_subscribe = 4006;
        static final int TRANSACTION_unmute = 3059;
        static final int TRANSACTION_unsubscribe = 4007;

        public static class Proxy implements IMediaSession {
            private IBinder mRemote;

            public Proxy(IBinder iBinder) {
                this.mRemote = iBinder;
            }

            @Override // androidx.media3.session.IMediaSession
            public void addMediaItem(IMediaController iMediaController, int i10, Bundle bundle) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(IMediaSession.DESCRIPTOR);
                    parcelObtain.writeStrongInterface(iMediaController);
                    parcelObtain.writeInt(i10);
                    _Parcel.writeTypedObject(parcelObtain, bundle, 0);
                    this.mRemote.transact(Stub.TRANSACTION_addMediaItem, parcelObtain, null, 1);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // androidx.media3.session.IMediaSession
            public void addMediaItemWithIndex(IMediaController iMediaController, int i10, int i11, Bundle bundle) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(IMediaSession.DESCRIPTOR);
                    parcelObtain.writeStrongInterface(iMediaController);
                    parcelObtain.writeInt(i10);
                    parcelObtain.writeInt(i11);
                    _Parcel.writeTypedObject(parcelObtain, bundle, 0);
                    this.mRemote.transact(Stub.TRANSACTION_addMediaItemWithIndex, parcelObtain, null, 1);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // androidx.media3.session.IMediaSession
            public void addMediaItems(IMediaController iMediaController, int i10, IBinder iBinder) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(IMediaSession.DESCRIPTOR);
                    parcelObtain.writeStrongInterface(iMediaController);
                    parcelObtain.writeInt(i10);
                    parcelObtain.writeStrongBinder(iBinder);
                    this.mRemote.transact(Stub.TRANSACTION_addMediaItems, parcelObtain, null, 1);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // androidx.media3.session.IMediaSession
            public void addMediaItemsWithIndex(IMediaController iMediaController, int i10, int i11, IBinder iBinder) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(IMediaSession.DESCRIPTOR);
                    parcelObtain.writeStrongInterface(iMediaController);
                    parcelObtain.writeInt(i10);
                    parcelObtain.writeInt(i11);
                    parcelObtain.writeStrongBinder(iBinder);
                    this.mRemote.transact(Stub.TRANSACTION_addMediaItemsWithIndex, parcelObtain, null, 1);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            @Override // androidx.media3.session.IMediaSession
            public void clearMediaItems(IMediaController iMediaController, int i10) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(IMediaSession.DESCRIPTOR);
                    parcelObtain.writeStrongInterface(iMediaController);
                    parcelObtain.writeInt(i10);
                    this.mRemote.transact(Stub.TRANSACTION_clearMediaItems, parcelObtain, null, 1);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // androidx.media3.session.IMediaSession
            public void connect(IMediaController iMediaController, int i10, Bundle bundle) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(IMediaSession.DESCRIPTOR);
                    parcelObtain.writeStrongInterface(iMediaController);
                    parcelObtain.writeInt(i10);
                    _Parcel.writeTypedObject(parcelObtain, bundle, 0);
                    this.mRemote.transact(Stub.TRANSACTION_connect, parcelObtain, null, 1);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // androidx.media3.session.IMediaSession
            public void decreaseDeviceVolume(IMediaController iMediaController, int i10) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(IMediaSession.DESCRIPTOR);
                    parcelObtain.writeStrongInterface(iMediaController);
                    parcelObtain.writeInt(i10);
                    this.mRemote.transact(3005, parcelObtain, null, 1);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // androidx.media3.session.IMediaSession
            public void decreaseDeviceVolumeWithFlags(IMediaController iMediaController, int i10, int i11) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(IMediaSession.DESCRIPTOR);
                    parcelObtain.writeStrongInterface(iMediaController);
                    parcelObtain.writeInt(i10);
                    parcelObtain.writeInt(i11);
                    this.mRemote.transact(Stub.TRANSACTION_decreaseDeviceVolumeWithFlags, parcelObtain, null, 1);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // androidx.media3.session.IMediaSession
            public void flushCommandQueue(IMediaController iMediaController) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(IMediaSession.DESCRIPTOR);
                    parcelObtain.writeStrongInterface(iMediaController);
                    this.mRemote.transact(Stub.TRANSACTION_flushCommandQueue, parcelObtain, null, 1);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // androidx.media3.session.IMediaSession
            public void getChildren(IMediaController iMediaController, int i10, String str, int i11, int i12, Bundle bundle) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(IMediaSession.DESCRIPTOR);
                    parcelObtain.writeStrongInterface(iMediaController);
                    parcelObtain.writeInt(i10);
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(i11);
                    parcelObtain.writeInt(i12);
                    _Parcel.writeTypedObject(parcelObtain, bundle, 0);
                    this.mRemote.transact(4003, parcelObtain, null, 1);
                } finally {
                    parcelObtain.recycle();
                }
            }

            public String getInterfaceDescriptor() {
                return IMediaSession.DESCRIPTOR;
            }

            @Override // androidx.media3.session.IMediaSession
            public void getItem(IMediaController iMediaController, int i10, String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(IMediaSession.DESCRIPTOR);
                    parcelObtain.writeStrongInterface(iMediaController);
                    parcelObtain.writeInt(i10);
                    parcelObtain.writeString(str);
                    this.mRemote.transact(4002, parcelObtain, null, 1);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // androidx.media3.session.IMediaSession
            public void getLibraryRoot(IMediaController iMediaController, int i10, Bundle bundle) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(IMediaSession.DESCRIPTOR);
                    parcelObtain.writeStrongInterface(iMediaController);
                    parcelObtain.writeInt(i10);
                    _Parcel.writeTypedObject(parcelObtain, bundle, 0);
                    this.mRemote.transact(4001, parcelObtain, null, 1);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // androidx.media3.session.IMediaSession
            public void getSearchResult(IMediaController iMediaController, int i10, String str, int i11, int i12, Bundle bundle) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(IMediaSession.DESCRIPTOR);
                    parcelObtain.writeStrongInterface(iMediaController);
                    parcelObtain.writeInt(i10);
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(i11);
                    parcelObtain.writeInt(i12);
                    _Parcel.writeTypedObject(parcelObtain, bundle, 0);
                    this.mRemote.transact(4005, parcelObtain, null, 1);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // androidx.media3.session.IMediaSession
            public void increaseDeviceVolume(IMediaController iMediaController, int i10) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(IMediaSession.DESCRIPTOR);
                    parcelObtain.writeStrongInterface(iMediaController);
                    parcelObtain.writeInt(i10);
                    this.mRemote.transact(3004, parcelObtain, null, 1);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // androidx.media3.session.IMediaSession
            public void increaseDeviceVolumeWithFlags(IMediaController iMediaController, int i10, int i11) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(IMediaSession.DESCRIPTOR);
                    parcelObtain.writeStrongInterface(iMediaController);
                    parcelObtain.writeInt(i10);
                    parcelObtain.writeInt(i11);
                    this.mRemote.transact(Stub.TRANSACTION_increaseDeviceVolumeWithFlags, parcelObtain, null, 1);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // androidx.media3.session.IMediaSession
            public void moveMediaItem(IMediaController iMediaController, int i10, int i11, int i12) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(IMediaSession.DESCRIPTOR);
                    parcelObtain.writeStrongInterface(iMediaController);
                    parcelObtain.writeInt(i10);
                    parcelObtain.writeInt(i11);
                    parcelObtain.writeInt(i12);
                    this.mRemote.transact(Stub.TRANSACTION_moveMediaItem, parcelObtain, null, 1);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // androidx.media3.session.IMediaSession
            public void moveMediaItems(IMediaController iMediaController, int i10, int i11, int i12, int i13) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(IMediaSession.DESCRIPTOR);
                    parcelObtain.writeStrongInterface(iMediaController);
                    parcelObtain.writeInt(i10);
                    parcelObtain.writeInt(i11);
                    parcelObtain.writeInt(i12);
                    parcelObtain.writeInt(i13);
                    this.mRemote.transact(Stub.TRANSACTION_moveMediaItems, parcelObtain, null, 1);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // androidx.media3.session.IMediaSession
            public void mute(IMediaController iMediaController, int i10) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(IMediaSession.DESCRIPTOR);
                    parcelObtain.writeStrongInterface(iMediaController);
                    parcelObtain.writeInt(i10);
                    this.mRemote.transact(Stub.TRANSACTION_mute, parcelObtain, null, 1);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // androidx.media3.session.IMediaSession
            public void onControllerResult(IMediaController iMediaController, int i10, Bundle bundle) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(IMediaSession.DESCRIPTOR);
                    parcelObtain.writeStrongInterface(iMediaController);
                    parcelObtain.writeInt(i10);
                    _Parcel.writeTypedObject(parcelObtain, bundle, 0);
                    this.mRemote.transact(Stub.TRANSACTION_onControllerResult, parcelObtain, null, 1);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // androidx.media3.session.IMediaSession
            public void onCustomCommand(IMediaController iMediaController, int i10, Bundle bundle, Bundle bundle2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(IMediaSession.DESCRIPTOR);
                    parcelObtain.writeStrongInterface(iMediaController);
                    parcelObtain.writeInt(i10);
                    _Parcel.writeTypedObject(parcelObtain, bundle, 0);
                    _Parcel.writeTypedObject(parcelObtain, bundle2, 0);
                    this.mRemote.transact(Stub.TRANSACTION_onCustomCommand, parcelObtain, null, 1);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // androidx.media3.session.IMediaSession
            public void onCustomCommandWithProgressUpdate(IMediaController iMediaController, int i10, Bundle bundle, Bundle bundle2, boolean z) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(IMediaSession.DESCRIPTOR);
                    parcelObtain.writeStrongInterface(iMediaController);
                    parcelObtain.writeInt(i10);
                    _Parcel.writeTypedObject(parcelObtain, bundle, 0);
                    _Parcel.writeTypedObject(parcelObtain, bundle2, 0);
                    parcelObtain.writeInt(z ? 1 : 0);
                    this.mRemote.transact(Stub.TRANSACTION_onCustomCommandWithProgressUpdate, parcelObtain, null, 1);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // androidx.media3.session.IMediaSession
            public void onSurfaceSizeChanged(IMediaController iMediaController, int i10, int i11, int i12) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(IMediaSession.DESCRIPTOR);
                    parcelObtain.writeStrongInterface(iMediaController);
                    parcelObtain.writeInt(i10);
                    parcelObtain.writeInt(i11);
                    parcelObtain.writeInt(i12);
                    this.mRemote.transact(Stub.TRANSACTION_onSurfaceSizeChanged, parcelObtain, null, 1);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // androidx.media3.session.IMediaSession
            public void pause(IMediaController iMediaController, int i10) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(IMediaSession.DESCRIPTOR);
                    parcelObtain.writeStrongInterface(iMediaController);
                    parcelObtain.writeInt(i10);
                    this.mRemote.transact(Stub.TRANSACTION_pause, parcelObtain, null, 1);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // androidx.media3.session.IMediaSession
            public void play(IMediaController iMediaController, int i10) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(IMediaSession.DESCRIPTOR);
                    parcelObtain.writeStrongInterface(iMediaController);
                    parcelObtain.writeInt(i10);
                    this.mRemote.transact(Stub.TRANSACTION_play, parcelObtain, null, 1);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // androidx.media3.session.IMediaSession
            public void prepare(IMediaController iMediaController, int i10) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(IMediaSession.DESCRIPTOR);
                    parcelObtain.writeStrongInterface(iMediaController);
                    parcelObtain.writeInt(i10);
                    this.mRemote.transact(Stub.TRANSACTION_prepare, parcelObtain, null, 1);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // androidx.media3.session.IMediaSession
            public void release(IMediaController iMediaController, int i10) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(IMediaSession.DESCRIPTOR);
                    parcelObtain.writeStrongInterface(iMediaController);
                    parcelObtain.writeInt(i10);
                    this.mRemote.transact(Stub.TRANSACTION_release, parcelObtain, null, 1);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // androidx.media3.session.IMediaSession
            public void removeMediaItem(IMediaController iMediaController, int i10, int i11) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(IMediaSession.DESCRIPTOR);
                    parcelObtain.writeStrongInterface(iMediaController);
                    parcelObtain.writeInt(i10);
                    parcelObtain.writeInt(i11);
                    this.mRemote.transact(Stub.TRANSACTION_removeMediaItem, parcelObtain, null, 1);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // androidx.media3.session.IMediaSession
            public void removeMediaItems(IMediaController iMediaController, int i10, int i11, int i12) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(IMediaSession.DESCRIPTOR);
                    parcelObtain.writeStrongInterface(iMediaController);
                    parcelObtain.writeInt(i10);
                    parcelObtain.writeInt(i11);
                    parcelObtain.writeInt(i12);
                    this.mRemote.transact(Stub.TRANSACTION_removeMediaItems, parcelObtain, null, 1);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // androidx.media3.session.IMediaSession
            public void replaceMediaItem(IMediaController iMediaController, int i10, int i11, Bundle bundle) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(IMediaSession.DESCRIPTOR);
                    parcelObtain.writeStrongInterface(iMediaController);
                    parcelObtain.writeInt(i10);
                    parcelObtain.writeInt(i11);
                    _Parcel.writeTypedObject(parcelObtain, bundle, 0);
                    this.mRemote.transact(Stub.TRANSACTION_replaceMediaItem, parcelObtain, null, 1);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // androidx.media3.session.IMediaSession
            public void replaceMediaItems(IMediaController iMediaController, int i10, int i11, int i12, IBinder iBinder) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(IMediaSession.DESCRIPTOR);
                    parcelObtain.writeStrongInterface(iMediaController);
                    parcelObtain.writeInt(i10);
                    parcelObtain.writeInt(i11);
                    parcelObtain.writeInt(i12);
                    parcelObtain.writeStrongBinder(iBinder);
                    this.mRemote.transact(Stub.TRANSACTION_replaceMediaItems, parcelObtain, null, 1);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // androidx.media3.session.IMediaSession
            public void search(IMediaController iMediaController, int i10, String str, Bundle bundle) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(IMediaSession.DESCRIPTOR);
                    parcelObtain.writeStrongInterface(iMediaController);
                    parcelObtain.writeInt(i10);
                    parcelObtain.writeString(str);
                    _Parcel.writeTypedObject(parcelObtain, bundle, 0);
                    this.mRemote.transact(4004, parcelObtain, null, 1);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // androidx.media3.session.IMediaSession
            public void seekBack(IMediaController iMediaController, int i10) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(IMediaSession.DESCRIPTOR);
                    parcelObtain.writeStrongInterface(iMediaController);
                    parcelObtain.writeInt(i10);
                    this.mRemote.transact(Stub.TRANSACTION_seekBack, parcelObtain, null, 1);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // androidx.media3.session.IMediaSession
            public void seekForward(IMediaController iMediaController, int i10) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(IMediaSession.DESCRIPTOR);
                    parcelObtain.writeStrongInterface(iMediaController);
                    parcelObtain.writeInt(i10);
                    this.mRemote.transact(Stub.TRANSACTION_seekForward, parcelObtain, null, 1);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // androidx.media3.session.IMediaSession
            public void seekTo(IMediaController iMediaController, int i10, long j10) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(IMediaSession.DESCRIPTOR);
                    parcelObtain.writeStrongInterface(iMediaController);
                    parcelObtain.writeInt(i10);
                    parcelObtain.writeLong(j10);
                    this.mRemote.transact(Stub.TRANSACTION_seekTo, parcelObtain, null, 1);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // androidx.media3.session.IMediaSession
            public void seekToDefaultPosition(IMediaController iMediaController, int i10) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(IMediaSession.DESCRIPTOR);
                    parcelObtain.writeStrongInterface(iMediaController);
                    parcelObtain.writeInt(i10);
                    this.mRemote.transact(Stub.TRANSACTION_seekToDefaultPosition, parcelObtain, null, 1);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // androidx.media3.session.IMediaSession
            public void seekToDefaultPositionWithMediaItemIndex(IMediaController iMediaController, int i10, int i11) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(IMediaSession.DESCRIPTOR);
                    parcelObtain.writeStrongInterface(iMediaController);
                    parcelObtain.writeInt(i10);
                    parcelObtain.writeInt(i11);
                    this.mRemote.transact(Stub.TRANSACTION_seekToDefaultPositionWithMediaItemIndex, parcelObtain, null, 1);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // androidx.media3.session.IMediaSession
            public void seekToNext(IMediaController iMediaController, int i10) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(IMediaSession.DESCRIPTOR);
                    parcelObtain.writeStrongInterface(iMediaController);
                    parcelObtain.writeInt(i10);
                    this.mRemote.transact(Stub.TRANSACTION_seekToNext, parcelObtain, null, 1);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // androidx.media3.session.IMediaSession
            public void seekToNextMediaItem(IMediaController iMediaController, int i10) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(IMediaSession.DESCRIPTOR);
                    parcelObtain.writeStrongInterface(iMediaController);
                    parcelObtain.writeInt(i10);
                    this.mRemote.transact(Stub.TRANSACTION_seekToNextMediaItem, parcelObtain, null, 1);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // androidx.media3.session.IMediaSession
            public void seekToPrevious(IMediaController iMediaController, int i10) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(IMediaSession.DESCRIPTOR);
                    parcelObtain.writeStrongInterface(iMediaController);
                    parcelObtain.writeInt(i10);
                    this.mRemote.transact(Stub.TRANSACTION_seekToPrevious, parcelObtain, null, 1);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // androidx.media3.session.IMediaSession
            public void seekToPreviousMediaItem(IMediaController iMediaController, int i10) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(IMediaSession.DESCRIPTOR);
                    parcelObtain.writeStrongInterface(iMediaController);
                    parcelObtain.writeInt(i10);
                    this.mRemote.transact(Stub.TRANSACTION_seekToPreviousMediaItem, parcelObtain, null, 1);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // androidx.media3.session.IMediaSession
            public void seekToWithMediaItemIndex(IMediaController iMediaController, int i10, int i11, long j10) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(IMediaSession.DESCRIPTOR);
                    parcelObtain.writeStrongInterface(iMediaController);
                    parcelObtain.writeInt(i10);
                    parcelObtain.writeInt(i11);
                    parcelObtain.writeLong(j10);
                    this.mRemote.transact(Stub.TRANSACTION_seekToWithMediaItemIndex, parcelObtain, null, 1);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // androidx.media3.session.IMediaSession
            public void setAudioAttributes(IMediaController iMediaController, int i10, Bundle bundle, boolean z) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(IMediaSession.DESCRIPTOR);
                    parcelObtain.writeStrongInterface(iMediaController);
                    parcelObtain.writeInt(i10);
                    _Parcel.writeTypedObject(parcelObtain, bundle, 0);
                    parcelObtain.writeInt(z ? 1 : 0);
                    this.mRemote.transact(Stub.TRANSACTION_setAudioAttributes, parcelObtain, null, 1);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // androidx.media3.session.IMediaSession
            public void setDeviceMuted(IMediaController iMediaController, int i10, boolean z) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(IMediaSession.DESCRIPTOR);
                    parcelObtain.writeStrongInterface(iMediaController);
                    parcelObtain.writeInt(i10);
                    parcelObtain.writeInt(z ? 1 : 0);
                    this.mRemote.transact(3006, parcelObtain, null, 1);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // androidx.media3.session.IMediaSession
            public void setDeviceMutedWithFlags(IMediaController iMediaController, int i10, boolean z, int i11) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(IMediaSession.DESCRIPTOR);
                    parcelObtain.writeStrongInterface(iMediaController);
                    parcelObtain.writeInt(i10);
                    parcelObtain.writeInt(z ? 1 : 0);
                    parcelObtain.writeInt(i11);
                    this.mRemote.transact(Stub.TRANSACTION_setDeviceMutedWithFlags, parcelObtain, null, 1);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // androidx.media3.session.IMediaSession
            public void setDeviceVolume(IMediaController iMediaController, int i10, int i11) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(IMediaSession.DESCRIPTOR);
                    parcelObtain.writeStrongInterface(iMediaController);
                    parcelObtain.writeInt(i10);
                    parcelObtain.writeInt(i11);
                    this.mRemote.transact(3003, parcelObtain, null, 1);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // androidx.media3.session.IMediaSession
            public void setDeviceVolumeWithFlags(IMediaController iMediaController, int i10, int i11, int i12) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(IMediaSession.DESCRIPTOR);
                    parcelObtain.writeStrongInterface(iMediaController);
                    parcelObtain.writeInt(i10);
                    parcelObtain.writeInt(i11);
                    parcelObtain.writeInt(i12);
                    this.mRemote.transact(Stub.TRANSACTION_setDeviceVolumeWithFlags, parcelObtain, null, 1);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // androidx.media3.session.IMediaSession
            public void setMediaItem(IMediaController iMediaController, int i10, Bundle bundle) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(IMediaSession.DESCRIPTOR);
                    parcelObtain.writeStrongInterface(iMediaController);
                    parcelObtain.writeInt(i10);
                    _Parcel.writeTypedObject(parcelObtain, bundle, 0);
                    this.mRemote.transact(Stub.TRANSACTION_setMediaItem, parcelObtain, null, 1);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // androidx.media3.session.IMediaSession
            public void setMediaItemWithResetPosition(IMediaController iMediaController, int i10, Bundle bundle, boolean z) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(IMediaSession.DESCRIPTOR);
                    parcelObtain.writeStrongInterface(iMediaController);
                    parcelObtain.writeInt(i10);
                    _Parcel.writeTypedObject(parcelObtain, bundle, 0);
                    parcelObtain.writeInt(z ? 1 : 0);
                    this.mRemote.transact(Stub.TRANSACTION_setMediaItemWithResetPosition, parcelObtain, null, 1);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // androidx.media3.session.IMediaSession
            public void setMediaItemWithStartPosition(IMediaController iMediaController, int i10, Bundle bundle, long j10) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(IMediaSession.DESCRIPTOR);
                    parcelObtain.writeStrongInterface(iMediaController);
                    parcelObtain.writeInt(i10);
                    _Parcel.writeTypedObject(parcelObtain, bundle, 0);
                    parcelObtain.writeLong(j10);
                    this.mRemote.transact(Stub.TRANSACTION_setMediaItemWithStartPosition, parcelObtain, null, 1);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // androidx.media3.session.IMediaSession
            public void setMediaItems(IMediaController iMediaController, int i10, IBinder iBinder) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(IMediaSession.DESCRIPTOR);
                    parcelObtain.writeStrongInterface(iMediaController);
                    parcelObtain.writeInt(i10);
                    parcelObtain.writeStrongBinder(iBinder);
                    this.mRemote.transact(Stub.TRANSACTION_setMediaItems, parcelObtain, null, 1);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // androidx.media3.session.IMediaSession
            public void setMediaItemsWithResetPosition(IMediaController iMediaController, int i10, IBinder iBinder, boolean z) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(IMediaSession.DESCRIPTOR);
                    parcelObtain.writeStrongInterface(iMediaController);
                    parcelObtain.writeInt(i10);
                    parcelObtain.writeStrongBinder(iBinder);
                    parcelObtain.writeInt(z ? 1 : 0);
                    this.mRemote.transact(Stub.TRANSACTION_setMediaItemsWithResetPosition, parcelObtain, null, 1);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // androidx.media3.session.IMediaSession
            public void setMediaItemsWithStartIndex(IMediaController iMediaController, int i10, IBinder iBinder, int i11, long j10) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(IMediaSession.DESCRIPTOR);
                    parcelObtain.writeStrongInterface(iMediaController);
                    parcelObtain.writeInt(i10);
                    parcelObtain.writeStrongBinder(iBinder);
                    parcelObtain.writeInt(i11);
                    parcelObtain.writeLong(j10);
                    this.mRemote.transact(Stub.TRANSACTION_setMediaItemsWithStartIndex, parcelObtain, null, 1);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // androidx.media3.session.IMediaSession
            public void setPlayWhenReady(IMediaController iMediaController, int i10, boolean z) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(IMediaSession.DESCRIPTOR);
                    parcelObtain.writeStrongInterface(iMediaController);
                    parcelObtain.writeInt(i10);
                    parcelObtain.writeInt(z ? 1 : 0);
                    this.mRemote.transact(Stub.TRANSACTION_setPlayWhenReady, parcelObtain, null, 1);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // androidx.media3.session.IMediaSession
            public void setPlaybackParameters(IMediaController iMediaController, int i10, Bundle bundle) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(IMediaSession.DESCRIPTOR);
                    parcelObtain.writeStrongInterface(iMediaController);
                    parcelObtain.writeInt(i10);
                    _Parcel.writeTypedObject(parcelObtain, bundle, 0);
                    this.mRemote.transact(Stub.TRANSACTION_setPlaybackParameters, parcelObtain, null, 1);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // androidx.media3.session.IMediaSession
            public void setPlaybackSpeed(IMediaController iMediaController, int i10, float f10) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(IMediaSession.DESCRIPTOR);
                    parcelObtain.writeStrongInterface(iMediaController);
                    parcelObtain.writeInt(i10);
                    parcelObtain.writeFloat(f10);
                    this.mRemote.transact(Stub.TRANSACTION_setPlaybackSpeed, parcelObtain, null, 1);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // androidx.media3.session.IMediaSession
            public void setPlaylistMetadata(IMediaController iMediaController, int i10, Bundle bundle) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(IMediaSession.DESCRIPTOR);
                    parcelObtain.writeStrongInterface(iMediaController);
                    parcelObtain.writeInt(i10);
                    _Parcel.writeTypedObject(parcelObtain, bundle, 0);
                    this.mRemote.transact(Stub.TRANSACTION_setPlaylistMetadata, parcelObtain, null, 1);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // androidx.media3.session.IMediaSession
            public void setRating(IMediaController iMediaController, int i10, Bundle bundle) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(IMediaSession.DESCRIPTOR);
                    parcelObtain.writeStrongInterface(iMediaController);
                    parcelObtain.writeInt(i10);
                    _Parcel.writeTypedObject(parcelObtain, bundle, 0);
                    this.mRemote.transact(Stub.TRANSACTION_setRating, parcelObtain, null, 1);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // androidx.media3.session.IMediaSession
            public void setRatingWithMediaId(IMediaController iMediaController, int i10, String str, Bundle bundle) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(IMediaSession.DESCRIPTOR);
                    parcelObtain.writeStrongInterface(iMediaController);
                    parcelObtain.writeInt(i10);
                    parcelObtain.writeString(str);
                    _Parcel.writeTypedObject(parcelObtain, bundle, 0);
                    this.mRemote.transact(Stub.TRANSACTION_setRatingWithMediaId, parcelObtain, null, 1);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // androidx.media3.session.IMediaSession
            public void setRepeatMode(IMediaController iMediaController, int i10, int i11) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(IMediaSession.DESCRIPTOR);
                    parcelObtain.writeStrongInterface(iMediaController);
                    parcelObtain.writeInt(i10);
                    parcelObtain.writeInt(i11);
                    this.mRemote.transact(Stub.TRANSACTION_setRepeatMode, parcelObtain, null, 1);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // androidx.media3.session.IMediaSession
            public void setShuffleModeEnabled(IMediaController iMediaController, int i10, boolean z) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(IMediaSession.DESCRIPTOR);
                    parcelObtain.writeStrongInterface(iMediaController);
                    parcelObtain.writeInt(i10);
                    parcelObtain.writeInt(z ? 1 : 0);
                    this.mRemote.transact(Stub.TRANSACTION_setShuffleModeEnabled, parcelObtain, null, 1);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // androidx.media3.session.IMediaSession
            public void setTrackSelectionParameters(IMediaController iMediaController, int i10, Bundle bundle) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(IMediaSession.DESCRIPTOR);
                    parcelObtain.writeStrongInterface(iMediaController);
                    parcelObtain.writeInt(i10);
                    _Parcel.writeTypedObject(parcelObtain, bundle, 0);
                    this.mRemote.transact(Stub.TRANSACTION_setTrackSelectionParameters, parcelObtain, null, 1);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // androidx.media3.session.IMediaSession
            public void setVideoSurface(IMediaController iMediaController, int i10, Surface surface) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(IMediaSession.DESCRIPTOR);
                    parcelObtain.writeStrongInterface(iMediaController);
                    parcelObtain.writeInt(i10);
                    _Parcel.writeTypedObject(parcelObtain, surface, 0);
                    this.mRemote.transact(Stub.TRANSACTION_setVideoSurface, parcelObtain, null, 1);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // androidx.media3.session.IMediaSession
            public void setVideoSurfaceWithSize(IMediaController iMediaController, int i10, Surface surface, int i11, int i12) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(IMediaSession.DESCRIPTOR);
                    parcelObtain.writeStrongInterface(iMediaController);
                    parcelObtain.writeInt(i10);
                    _Parcel.writeTypedObject(parcelObtain, surface, 0);
                    parcelObtain.writeInt(i11);
                    parcelObtain.writeInt(i12);
                    this.mRemote.transact(Stub.TRANSACTION_setVideoSurfaceWithSize, parcelObtain, null, 1);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // androidx.media3.session.IMediaSession
            public void setVolume(IMediaController iMediaController, int i10, float f10) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(IMediaSession.DESCRIPTOR);
                    parcelObtain.writeStrongInterface(iMediaController);
                    parcelObtain.writeInt(i10);
                    parcelObtain.writeFloat(f10);
                    this.mRemote.transact(3002, parcelObtain, null, 1);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // androidx.media3.session.IMediaSession
            public void stop(IMediaController iMediaController, int i10) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(IMediaSession.DESCRIPTOR);
                    parcelObtain.writeStrongInterface(iMediaController);
                    parcelObtain.writeInt(i10);
                    this.mRemote.transact(Stub.TRANSACTION_stop, parcelObtain, null, 1);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // androidx.media3.session.IMediaSession
            public void subscribe(IMediaController iMediaController, int i10, String str, Bundle bundle) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(IMediaSession.DESCRIPTOR);
                    parcelObtain.writeStrongInterface(iMediaController);
                    parcelObtain.writeInt(i10);
                    parcelObtain.writeString(str);
                    _Parcel.writeTypedObject(parcelObtain, bundle, 0);
                    this.mRemote.transact(4006, parcelObtain, null, 1);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // androidx.media3.session.IMediaSession
            public void unmute(IMediaController iMediaController, int i10) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(IMediaSession.DESCRIPTOR);
                    parcelObtain.writeStrongInterface(iMediaController);
                    parcelObtain.writeInt(i10);
                    this.mRemote.transact(Stub.TRANSACTION_unmute, parcelObtain, null, 1);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // androidx.media3.session.IMediaSession
            public void unsubscribe(IMediaController iMediaController, int i10, String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(IMediaSession.DESCRIPTOR);
                    parcelObtain.writeStrongInterface(iMediaController);
                    parcelObtain.writeInt(i10);
                    parcelObtain.writeString(str);
                    this.mRemote.transact(Stub.TRANSACTION_unsubscribe, parcelObtain, null, 1);
                } finally {
                    parcelObtain.recycle();
                }
            }
        }

        public Stub() {
            attachInterface(this, IMediaSession.DESCRIPTOR);
        }

        public static IMediaSession asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(IMediaSession.DESCRIPTOR);
            return (iInterfaceQueryLocalInterface == null || !(iInterfaceQueryLocalInterface instanceof IMediaSession)) ? new Proxy(iBinder) : (IMediaSession) iInterfaceQueryLocalInterface;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int i10, Parcel parcel, Parcel parcel2, int i11) throws RemoteException {
            if (i10 >= 1 && i10 <= 16777215) {
                parcel.enforceInterface(IMediaSession.DESCRIPTOR);
            }
            if (i10 == 1598968902) {
                parcel2.writeString(IMediaSession.DESCRIPTOR);
                return true;
            }
            switch (i10) {
                case 3002:
                    setVolume(IMediaController.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt(), parcel.readFloat());
                    return true;
                case 3003:
                    setDeviceVolume(IMediaController.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt(), parcel.readInt());
                    return true;
                case 3004:
                    increaseDeviceVolume(IMediaController.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt());
                    return true;
                case 3005:
                    decreaseDeviceVolume(IMediaController.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt());
                    return true;
                case 3006:
                    setDeviceMuted(IMediaController.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt(), parcel.readInt() != 0);
                    return true;
                case TRANSACTION_setMediaItem /* 3007 */:
                    setMediaItem(IMediaController.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt(), (Bundle) _Parcel.readTypedObject(parcel, Bundle.CREATOR));
                    return true;
                case TRANSACTION_setMediaItemWithStartPosition /* 3008 */:
                    setMediaItemWithStartPosition(IMediaController.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt(), (Bundle) _Parcel.readTypedObject(parcel, Bundle.CREATOR), parcel.readLong());
                    return true;
                case TRANSACTION_setMediaItemWithResetPosition /* 3009 */:
                    setMediaItemWithResetPosition(IMediaController.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt(), (Bundle) _Parcel.readTypedObject(parcel, Bundle.CREATOR), parcel.readInt() != 0);
                    return true;
                case TRANSACTION_setMediaItems /* 3010 */:
                    setMediaItems(IMediaController.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt(), parcel.readStrongBinder());
                    return true;
                case TRANSACTION_setMediaItemsWithResetPosition /* 3011 */:
                    setMediaItemsWithResetPosition(IMediaController.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt(), parcel.readStrongBinder(), parcel.readInt() != 0);
                    return true;
                case TRANSACTION_setMediaItemsWithStartIndex /* 3012 */:
                    setMediaItemsWithStartIndex(IMediaController.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt(), parcel.readStrongBinder(), parcel.readInt(), parcel.readLong());
                    return true;
                case TRANSACTION_setPlayWhenReady /* 3013 */:
                    setPlayWhenReady(IMediaController.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt(), parcel.readInt() != 0);
                    return true;
                case TRANSACTION_onControllerResult /* 3014 */:
                    onControllerResult(IMediaController.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt(), (Bundle) _Parcel.readTypedObject(parcel, Bundle.CREATOR));
                    return true;
                case TRANSACTION_connect /* 3015 */:
                    connect(IMediaController.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt(), (Bundle) _Parcel.readTypedObject(parcel, Bundle.CREATOR));
                    return true;
                case TRANSACTION_onCustomCommand /* 3016 */:
                    IMediaController iMediaControllerAsInterface = IMediaController.Stub.asInterface(parcel.readStrongBinder());
                    int i12 = parcel.readInt();
                    Parcelable.Creator creator = Bundle.CREATOR;
                    onCustomCommand(iMediaControllerAsInterface, i12, (Bundle) _Parcel.readTypedObject(parcel, creator), (Bundle) _Parcel.readTypedObject(parcel, creator));
                    return true;
                case TRANSACTION_setRepeatMode /* 3017 */:
                    setRepeatMode(IMediaController.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt(), parcel.readInt());
                    return true;
                case TRANSACTION_setShuffleModeEnabled /* 3018 */:
                    setShuffleModeEnabled(IMediaController.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt(), parcel.readInt() != 0);
                    return true;
                case TRANSACTION_removeMediaItem /* 3019 */:
                    removeMediaItem(IMediaController.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt(), parcel.readInt());
                    return true;
                case TRANSACTION_removeMediaItems /* 3020 */:
                    removeMediaItems(IMediaController.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt(), parcel.readInt(), parcel.readInt());
                    return true;
                case TRANSACTION_clearMediaItems /* 3021 */:
                    clearMediaItems(IMediaController.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt());
                    return true;
                case TRANSACTION_moveMediaItem /* 3022 */:
                    moveMediaItem(IMediaController.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt(), parcel.readInt(), parcel.readInt());
                    return true;
                case TRANSACTION_moveMediaItems /* 3023 */:
                    moveMediaItems(IMediaController.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt(), parcel.readInt(), parcel.readInt(), parcel.readInt());
                    return true;
                case TRANSACTION_play /* 3024 */:
                    play(IMediaController.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt());
                    return true;
                case TRANSACTION_pause /* 3025 */:
                    pause(IMediaController.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt());
                    return true;
                case TRANSACTION_prepare /* 3026 */:
                    prepare(IMediaController.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt());
                    return true;
                case TRANSACTION_setPlaybackParameters /* 3027 */:
                    setPlaybackParameters(IMediaController.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt(), (Bundle) _Parcel.readTypedObject(parcel, Bundle.CREATOR));
                    return true;
                case TRANSACTION_setPlaybackSpeed /* 3028 */:
                    setPlaybackSpeed(IMediaController.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt(), parcel.readFloat());
                    return true;
                case TRANSACTION_addMediaItem /* 3029 */:
                    addMediaItem(IMediaController.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt(), (Bundle) _Parcel.readTypedObject(parcel, Bundle.CREATOR));
                    return true;
                case TRANSACTION_addMediaItemWithIndex /* 3030 */:
                    addMediaItemWithIndex(IMediaController.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt(), parcel.readInt(), (Bundle) _Parcel.readTypedObject(parcel, Bundle.CREATOR));
                    return true;
                case TRANSACTION_addMediaItems /* 3031 */:
                    addMediaItems(IMediaController.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt(), parcel.readStrongBinder());
                    return true;
                case TRANSACTION_addMediaItemsWithIndex /* 3032 */:
                    addMediaItemsWithIndex(IMediaController.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt(), parcel.readInt(), parcel.readStrongBinder());
                    return true;
                case TRANSACTION_setPlaylistMetadata /* 3033 */:
                    setPlaylistMetadata(IMediaController.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt(), (Bundle) _Parcel.readTypedObject(parcel, Bundle.CREATOR));
                    return true;
                case TRANSACTION_stop /* 3034 */:
                    stop(IMediaController.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt());
                    return true;
                case TRANSACTION_release /* 3035 */:
                    release(IMediaController.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt());
                    return true;
                case TRANSACTION_seekToDefaultPosition /* 3036 */:
                    seekToDefaultPosition(IMediaController.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt());
                    return true;
                case TRANSACTION_seekToDefaultPositionWithMediaItemIndex /* 3037 */:
                    seekToDefaultPositionWithMediaItemIndex(IMediaController.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt(), parcel.readInt());
                    return true;
                case TRANSACTION_seekTo /* 3038 */:
                    seekTo(IMediaController.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt(), parcel.readLong());
                    return true;
                case TRANSACTION_seekToWithMediaItemIndex /* 3039 */:
                    seekToWithMediaItemIndex(IMediaController.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt(), parcel.readInt(), parcel.readLong());
                    return true;
                case TRANSACTION_seekBack /* 3040 */:
                    seekBack(IMediaController.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt());
                    return true;
                case TRANSACTION_seekForward /* 3041 */:
                    seekForward(IMediaController.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt());
                    return true;
                case TRANSACTION_seekToPreviousMediaItem /* 3042 */:
                    seekToPreviousMediaItem(IMediaController.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt());
                    return true;
                case TRANSACTION_seekToNextMediaItem /* 3043 */:
                    seekToNextMediaItem(IMediaController.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt());
                    return true;
                case TRANSACTION_setVideoSurface /* 3044 */:
                    setVideoSurface(IMediaController.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt(), (Surface) _Parcel.readTypedObject(parcel, Surface.CREATOR));
                    return true;
                case TRANSACTION_flushCommandQueue /* 3045 */:
                    flushCommandQueue(IMediaController.Stub.asInterface(parcel.readStrongBinder()));
                    return true;
                case TRANSACTION_seekToPrevious /* 3046 */:
                    seekToPrevious(IMediaController.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt());
                    return true;
                case TRANSACTION_seekToNext /* 3047 */:
                    seekToNext(IMediaController.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt());
                    return true;
                case TRANSACTION_setTrackSelectionParameters /* 3048 */:
                    setTrackSelectionParameters(IMediaController.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt(), (Bundle) _Parcel.readTypedObject(parcel, Bundle.CREATOR));
                    return true;
                case TRANSACTION_setRatingWithMediaId /* 3049 */:
                    setRatingWithMediaId(IMediaController.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt(), parcel.readString(), (Bundle) _Parcel.readTypedObject(parcel, Bundle.CREATOR));
                    return true;
                case TRANSACTION_setRating /* 3050 */:
                    setRating(IMediaController.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt(), (Bundle) _Parcel.readTypedObject(parcel, Bundle.CREATOR));
                    return true;
                case TRANSACTION_setDeviceVolumeWithFlags /* 3051 */:
                    setDeviceVolumeWithFlags(IMediaController.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt(), parcel.readInt(), parcel.readInt());
                    return true;
                case TRANSACTION_increaseDeviceVolumeWithFlags /* 3052 */:
                    increaseDeviceVolumeWithFlags(IMediaController.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt(), parcel.readInt());
                    return true;
                case TRANSACTION_decreaseDeviceVolumeWithFlags /* 3053 */:
                    decreaseDeviceVolumeWithFlags(IMediaController.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt(), parcel.readInt());
                    return true;
                case TRANSACTION_setDeviceMutedWithFlags /* 3054 */:
                    setDeviceMutedWithFlags(IMediaController.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt(), parcel.readInt() != 0, parcel.readInt());
                    return true;
                case TRANSACTION_replaceMediaItem /* 3055 */:
                    replaceMediaItem(IMediaController.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt(), parcel.readInt(), (Bundle) _Parcel.readTypedObject(parcel, Bundle.CREATOR));
                    return true;
                case TRANSACTION_replaceMediaItems /* 3056 */:
                    replaceMediaItems(IMediaController.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt(), parcel.readInt(), parcel.readInt(), parcel.readStrongBinder());
                    return true;
                case TRANSACTION_setAudioAttributes /* 3057 */:
                    setAudioAttributes(IMediaController.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt(), (Bundle) _Parcel.readTypedObject(parcel, Bundle.CREATOR), parcel.readInt() != 0);
                    return true;
                case TRANSACTION_mute /* 3058 */:
                    mute(IMediaController.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt());
                    return true;
                case TRANSACTION_unmute /* 3059 */:
                    unmute(IMediaController.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt());
                    return true;
                case TRANSACTION_onCustomCommandWithProgressUpdate /* 3060 */:
                    IMediaController iMediaControllerAsInterface2 = IMediaController.Stub.asInterface(parcel.readStrongBinder());
                    int i13 = parcel.readInt();
                    Parcelable.Creator creator2 = Bundle.CREATOR;
                    onCustomCommandWithProgressUpdate(iMediaControllerAsInterface2, i13, (Bundle) _Parcel.readTypedObject(parcel, creator2), (Bundle) _Parcel.readTypedObject(parcel, creator2), parcel.readInt() != 0);
                    return true;
                case TRANSACTION_setVideoSurfaceWithSize /* 3061 */:
                    setVideoSurfaceWithSize(IMediaController.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt(), (Surface) _Parcel.readTypedObject(parcel, Surface.CREATOR), parcel.readInt(), parcel.readInt());
                    return true;
                case TRANSACTION_onSurfaceSizeChanged /* 3062 */:
                    onSurfaceSizeChanged(IMediaController.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt(), parcel.readInt(), parcel.readInt());
                    return true;
                default:
                    switch (i10) {
                        case 4001:
                            getLibraryRoot(IMediaController.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt(), (Bundle) _Parcel.readTypedObject(parcel, Bundle.CREATOR));
                            return true;
                        case 4002:
                            getItem(IMediaController.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt(), parcel.readString());
                            return true;
                        case 4003:
                            getChildren(IMediaController.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt(), parcel.readString(), parcel.readInt(), parcel.readInt(), (Bundle) _Parcel.readTypedObject(parcel, Bundle.CREATOR));
                            return true;
                        case 4004:
                            search(IMediaController.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt(), parcel.readString(), (Bundle) _Parcel.readTypedObject(parcel, Bundle.CREATOR));
                            return true;
                        case 4005:
                            getSearchResult(IMediaController.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt(), parcel.readString(), parcel.readInt(), parcel.readInt(), (Bundle) _Parcel.readTypedObject(parcel, Bundle.CREATOR));
                            return true;
                        case 4006:
                            subscribe(IMediaController.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt(), parcel.readString(), (Bundle) _Parcel.readTypedObject(parcel, Bundle.CREATOR));
                            return true;
                        case TRANSACTION_unsubscribe /* 4007 */:
                            unsubscribe(IMediaController.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt(), parcel.readString());
                            return true;
                        default:
                            return super.onTransact(i10, parcel, parcel2, i11);
                    }
            }
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

    void addMediaItem(IMediaController iMediaController, int i10, Bundle bundle) throws RemoteException;

    void addMediaItemWithIndex(IMediaController iMediaController, int i10, int i11, Bundle bundle) throws RemoteException;

    void addMediaItems(IMediaController iMediaController, int i10, IBinder iBinder) throws RemoteException;

    void addMediaItemsWithIndex(IMediaController iMediaController, int i10, int i11, IBinder iBinder) throws RemoteException;

    void clearMediaItems(IMediaController iMediaController, int i10) throws RemoteException;

    void connect(IMediaController iMediaController, int i10, Bundle bundle) throws RemoteException;

    void decreaseDeviceVolume(IMediaController iMediaController, int i10) throws RemoteException;

    void decreaseDeviceVolumeWithFlags(IMediaController iMediaController, int i10, int i11) throws RemoteException;

    void flushCommandQueue(IMediaController iMediaController) throws RemoteException;

    void getChildren(IMediaController iMediaController, int i10, String str, int i11, int i12, Bundle bundle) throws RemoteException;

    void getItem(IMediaController iMediaController, int i10, String str) throws RemoteException;

    void getLibraryRoot(IMediaController iMediaController, int i10, Bundle bundle) throws RemoteException;

    void getSearchResult(IMediaController iMediaController, int i10, String str, int i11, int i12, Bundle bundle) throws RemoteException;

    void increaseDeviceVolume(IMediaController iMediaController, int i10) throws RemoteException;

    void increaseDeviceVolumeWithFlags(IMediaController iMediaController, int i10, int i11) throws RemoteException;

    void moveMediaItem(IMediaController iMediaController, int i10, int i11, int i12) throws RemoteException;

    void moveMediaItems(IMediaController iMediaController, int i10, int i11, int i12, int i13) throws RemoteException;

    void mute(IMediaController iMediaController, int i10) throws RemoteException;

    void onControllerResult(IMediaController iMediaController, int i10, Bundle bundle) throws RemoteException;

    void onCustomCommand(IMediaController iMediaController, int i10, Bundle bundle, Bundle bundle2) throws RemoteException;

    void onCustomCommandWithProgressUpdate(IMediaController iMediaController, int i10, Bundle bundle, Bundle bundle2, boolean z) throws RemoteException;

    void onSurfaceSizeChanged(IMediaController iMediaController, int i10, int i11, int i12) throws RemoteException;

    void pause(IMediaController iMediaController, int i10) throws RemoteException;

    void play(IMediaController iMediaController, int i10) throws RemoteException;

    void prepare(IMediaController iMediaController, int i10) throws RemoteException;

    void release(IMediaController iMediaController, int i10) throws RemoteException;

    void removeMediaItem(IMediaController iMediaController, int i10, int i11) throws RemoteException;

    void removeMediaItems(IMediaController iMediaController, int i10, int i11, int i12) throws RemoteException;

    void replaceMediaItem(IMediaController iMediaController, int i10, int i11, Bundle bundle) throws RemoteException;

    void replaceMediaItems(IMediaController iMediaController, int i10, int i11, int i12, IBinder iBinder) throws RemoteException;

    void search(IMediaController iMediaController, int i10, String str, Bundle bundle) throws RemoteException;

    void seekBack(IMediaController iMediaController, int i10) throws RemoteException;

    void seekForward(IMediaController iMediaController, int i10) throws RemoteException;

    void seekTo(IMediaController iMediaController, int i10, long j10) throws RemoteException;

    void seekToDefaultPosition(IMediaController iMediaController, int i10) throws RemoteException;

    void seekToDefaultPositionWithMediaItemIndex(IMediaController iMediaController, int i10, int i11) throws RemoteException;

    void seekToNext(IMediaController iMediaController, int i10) throws RemoteException;

    void seekToNextMediaItem(IMediaController iMediaController, int i10) throws RemoteException;

    void seekToPrevious(IMediaController iMediaController, int i10) throws RemoteException;

    void seekToPreviousMediaItem(IMediaController iMediaController, int i10) throws RemoteException;

    void seekToWithMediaItemIndex(IMediaController iMediaController, int i10, int i11, long j10) throws RemoteException;

    void setAudioAttributes(IMediaController iMediaController, int i10, Bundle bundle, boolean z) throws RemoteException;

    void setDeviceMuted(IMediaController iMediaController, int i10, boolean z) throws RemoteException;

    void setDeviceMutedWithFlags(IMediaController iMediaController, int i10, boolean z, int i11) throws RemoteException;

    void setDeviceVolume(IMediaController iMediaController, int i10, int i11) throws RemoteException;

    void setDeviceVolumeWithFlags(IMediaController iMediaController, int i10, int i11, int i12) throws RemoteException;

    void setMediaItem(IMediaController iMediaController, int i10, Bundle bundle) throws RemoteException;

    void setMediaItemWithResetPosition(IMediaController iMediaController, int i10, Bundle bundle, boolean z) throws RemoteException;

    void setMediaItemWithStartPosition(IMediaController iMediaController, int i10, Bundle bundle, long j10) throws RemoteException;

    void setMediaItems(IMediaController iMediaController, int i10, IBinder iBinder) throws RemoteException;

    void setMediaItemsWithResetPosition(IMediaController iMediaController, int i10, IBinder iBinder, boolean z) throws RemoteException;

    void setMediaItemsWithStartIndex(IMediaController iMediaController, int i10, IBinder iBinder, int i11, long j10) throws RemoteException;

    void setPlayWhenReady(IMediaController iMediaController, int i10, boolean z) throws RemoteException;

    void setPlaybackParameters(IMediaController iMediaController, int i10, Bundle bundle) throws RemoteException;

    void setPlaybackSpeed(IMediaController iMediaController, int i10, float f10) throws RemoteException;

    void setPlaylistMetadata(IMediaController iMediaController, int i10, Bundle bundle) throws RemoteException;

    void setRating(IMediaController iMediaController, int i10, Bundle bundle) throws RemoteException;

    void setRatingWithMediaId(IMediaController iMediaController, int i10, String str, Bundle bundle) throws RemoteException;

    void setRepeatMode(IMediaController iMediaController, int i10, int i11) throws RemoteException;

    void setShuffleModeEnabled(IMediaController iMediaController, int i10, boolean z) throws RemoteException;

    void setTrackSelectionParameters(IMediaController iMediaController, int i10, Bundle bundle) throws RemoteException;

    void setVideoSurface(IMediaController iMediaController, int i10, Surface surface) throws RemoteException;

    void setVideoSurfaceWithSize(IMediaController iMediaController, int i10, Surface surface, int i11, int i12) throws RemoteException;

    void setVolume(IMediaController iMediaController, int i10, float f10) throws RemoteException;

    void stop(IMediaController iMediaController, int i10) throws RemoteException;

    void subscribe(IMediaController iMediaController, int i10, String str, Bundle bundle) throws RemoteException;

    void unmute(IMediaController iMediaController, int i10) throws RemoteException;

    void unsubscribe(IMediaController iMediaController, int i10, String str) throws RemoteException;
}
