package android.support.v4.media.session;

import android.os.Binder;
import android.os.IBinder;
import android.os.Parcel;
import java.lang.ref.WeakReference;

/* JADX INFO: loaded from: classes.dex */
public final class f extends Binder implements b {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final WeakReference f876c;

    public f() {
        attachInterface(this, "android.support.v4.media.session.IMediaControllerCallback");
        this.f876c = new WeakReference(null);
    }

    @Override // android.support.v4.media.session.b
    public final void c0(PlaybackStateCompat playbackStateCompat) {
        if (this.f876c.get() != null) {
            throw new ClassCastException();
        }
    }

    @Override // android.os.Binder
    public final boolean onTransact(int i10, Parcel parcel, Parcel parcel2, int i11) {
        if (i10 >= 1 && i10 <= 16777215) {
            parcel.enforceInterface("android.support.v4.media.session.IMediaControllerCallback");
        }
        if (i10 == 1598968902) {
            parcel2.writeString("android.support.v4.media.session.IMediaControllerCallback");
            return true;
        }
        switch (i10) {
            case 1:
                parcel.readString();
                if (this.f876c.get() != null) {
                    throw new ClassCastException();
                }
                return true;
            case 2:
                throw new AssertionError();
            case 3:
                c0((PlaybackStateCompat) ac.b.a(parcel, PlaybackStateCompat.CREATOR));
                return true;
            case 4:
                throw new AssertionError();
            case 5:
                parcel.createTypedArrayList(MediaSessionCompat$QueueItem.CREATOR);
                throw new AssertionError();
            case 6:
                throw new AssertionError();
            case 7:
                throw new AssertionError();
            case 8:
                throw new AssertionError();
            case 9:
                parcel.readInt();
                if (this.f876c.get() != null) {
                    throw new ClassCastException();
                }
                return true;
            case 10:
                parcel.readInt();
                return true;
            case 11:
                parcel.readInt();
                if (this.f876c.get() != null) {
                    throw new ClassCastException();
                }
                return true;
            case 12:
                parcel.readInt();
                if (this.f876c.get() != null) {
                    throw new ClassCastException();
                }
                return true;
            case 13:
                if (this.f876c.get() != null) {
                    throw new ClassCastException();
                }
                return true;
            default:
                return super.onTransact(i10, parcel, parcel2, i11);
        }
    }

    @Override // android.os.IInterface
    public final IBinder asBinder() {
        return this;
    }
}
