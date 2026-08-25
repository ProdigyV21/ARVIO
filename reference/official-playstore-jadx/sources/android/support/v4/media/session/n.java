package android.support.v4.media.session;

import android.app.PendingIntent;
import android.net.Uri;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.SystemClock;
import android.support.v4.media.MediaDescriptionCompat;
import android.support.v4.media.MediaMetadataCompat;
import android.support.v4.media.RatingCompat;
import android.text.TextUtils;
import android.view.KeyEvent;
import androidx.media3.container.NalUnitUtil;
import androidx.media3.extractor.AacUtil;
import androidx.media3.extractor.flac.FlacConstants;
import androidx.media3.extractor.ts.TsExtractor;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: loaded from: classes.dex */
public final class n extends Binder implements d {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final /* synthetic */ int f884d = 0;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final AtomicReference f885c;

    public n(o oVar) {
        attachInterface(this, "android.support.v4.media.session.IMediaSession");
        this.f885c = new AtomicReference(oVar);
    }

    @Override // android.support.v4.media.session.d
    public final void E(b bVar) {
        o oVar = (o) this.f885c.get();
        if (oVar == null) {
            return;
        }
        oVar.f890e.register(bVar, new androidx.media.d("android.media.session.MediaController", Binder.getCallingPid(), Binder.getCallingUid()));
        synchronized (oVar.f889d) {
        }
    }

    @Override // android.support.v4.media.session.d
    public final void G(RatingCompat ratingCompat) {
        throw new AssertionError();
    }

    @Override // android.support.v4.media.session.d
    public final void I(MediaDescriptionCompat mediaDescriptionCompat) {
        throw new AssertionError();
    }

    @Override // android.support.v4.media.session.d
    public final boolean J() {
        throw new AssertionError();
    }

    @Override // android.support.v4.media.session.d
    public final void K(MediaDescriptionCompat mediaDescriptionCompat) {
        throw new AssertionError();
    }

    @Override // android.support.v4.media.session.d
    public final void M(String str, Bundle bundle) {
        throw new AssertionError();
    }

    @Override // android.support.v4.media.session.d
    public final void P(b bVar) {
        o oVar = (o) this.f885c.get();
        if (oVar == null) {
            return;
        }
        oVar.f890e.unregister(bVar);
        Binder.getCallingPid();
        Binder.getCallingUid();
        synchronized (oVar.f889d) {
        }
    }

    @Override // android.support.v4.media.session.d
    public final void T() {
        throw new AssertionError();
    }

    @Override // android.support.v4.media.session.d
    public final void V(boolean z) {
        throw new AssertionError();
    }

    @Override // android.support.v4.media.session.d
    public final ParcelableVolumeInfo a0() {
        throw new AssertionError();
    }

    @Override // android.support.v4.media.session.d
    public final void adjustVolume(int i10, int i11) {
        throw new AssertionError();
    }

    @Override // android.support.v4.media.session.d
    public final String b() {
        throw new AssertionError();
    }

    @Override // android.support.v4.media.session.d
    public final void e(String str, Bundle bundle) {
        throw new AssertionError();
    }

    @Override // android.support.v4.media.session.d
    public final void g(Uri uri, Bundle bundle) {
        throw new AssertionError();
    }

    @Override // android.support.v4.media.session.d
    public final Bundle getExtras() {
        throw new AssertionError();
    }

    @Override // android.support.v4.media.session.d
    public final long getFlags() {
        throw new AssertionError();
    }

    @Override // android.support.v4.media.session.d
    public final MediaMetadataCompat getMetadata() {
        throw new AssertionError();
    }

    @Override // android.support.v4.media.session.d
    public final String getPackageName() {
        throw new AssertionError();
    }

    @Override // android.support.v4.media.session.d
    public final PlaybackStateCompat getPlaybackState() {
        o oVar = (o) this.f885c.get();
        if (oVar == null) {
            return null;
        }
        PlaybackStateCompat playbackStateCompat = oVar.f891f;
        MediaMetadataCompat mediaMetadataCompat = oVar.f892g;
        if (playbackStateCompat == null) {
            return playbackStateCompat;
        }
        float f10 = playbackStateCompat.f861n;
        long j10 = playbackStateCompat.f865r;
        int i10 = playbackStateCompat.f858i;
        long j11 = playbackStateCompat.f859l;
        long j12 = -1;
        if (j11 == -1) {
            return playbackStateCompat;
        }
        if ((i10 != 3 && i10 != 4 && i10 != 5) || j10 <= 0) {
            return playbackStateCompat;
        }
        long jElapsedRealtime = SystemClock.elapsedRealtime();
        long j13 = ((long) (f10 * (jElapsedRealtime - j10))) + j11;
        if (mediaMetadataCompat != null) {
            Bundle bundle = mediaMetadataCompat.f839i;
            if (bundle.containsKey(androidx.media3.session.legacy.MediaMetadataCompat.METADATA_KEY_DURATION)) {
                j12 = bundle.getLong(androidx.media3.session.legacy.MediaMetadataCompat.METADATA_KEY_DURATION, 0L);
            }
        }
        long j14 = (j12 < 0 || j13 <= j12) ? j13 < 0 ? 0L : j13 : j12;
        ArrayList arrayList = new ArrayList();
        long j15 = playbackStateCompat.f860m;
        long j16 = playbackStateCompat.f862o;
        int i11 = playbackStateCompat.f863p;
        CharSequence charSequence = playbackStateCompat.f864q;
        ArrayList arrayList2 = playbackStateCompat.f866s;
        if (arrayList2 != null) {
            arrayList.addAll(arrayList2);
        }
        return new PlaybackStateCompat(playbackStateCompat.f858i, j14, j15, playbackStateCompat.f861n, j16, i11, charSequence, jElapsedRealtime, arrayList, playbackStateCompat.f867t, playbackStateCompat.f868u);
    }

    @Override // android.support.v4.media.session.d
    public final CharSequence getQueueTitle() {
        throw new AssertionError();
    }

    @Override // android.support.v4.media.session.d
    public final void getRatingType() {
    }

    @Override // android.support.v4.media.session.d
    public final int getRepeatMode() {
        return ((o) this.f885c.get()) != null ? 0 : -1;
    }

    @Override // android.support.v4.media.session.d
    public final Bundle getSessionInfo() {
        ((o) this.f885c.get()).getClass();
        return null;
    }

    @Override // android.support.v4.media.session.d
    public final int getShuffleMode() {
        return ((o) this.f885c.get()) != null ? 0 : -1;
    }

    @Override // android.support.v4.media.session.d
    public final PendingIntent i() {
        throw new AssertionError();
    }

    @Override // android.support.v4.media.session.d
    public final void isCaptioningEnabled() {
    }

    @Override // android.support.v4.media.session.d
    public final void l(String str, Bundle bundle) {
        throw new AssertionError();
    }

    @Override // android.support.v4.media.session.d
    public final void m(String str, Bundle bundle) {
        throw new AssertionError();
    }

    @Override // android.support.v4.media.session.d
    public final void n(String str, Bundle bundle) {
        throw new AssertionError();
    }

    @Override // android.support.v4.media.session.d
    public final void next() {
        throw new AssertionError();
    }

    @Override // android.support.v4.media.session.d
    public final void o(Uri uri, Bundle bundle) {
        throw new AssertionError();
    }

    @Override // android.os.Binder
    public final boolean onTransact(int i10, Parcel parcel, Parcel parcel2, int i11) {
        if (i10 >= 1 && i10 <= 16777215) {
            parcel.enforceInterface("android.support.v4.media.session.IMediaSession");
        }
        if (i10 == 1598968902) {
            parcel2.writeString("android.support.v4.media.session.IMediaSession");
            return true;
        }
        b bVar = null;
        b bVar2 = null;
        switch (i10) {
            case 1:
                w(parcel.readString(), (Bundle) androidx.work.impl.t.a(parcel, Bundle.CREATOR), (MediaSessionCompat$ResultReceiverWrapper) androidx.work.impl.t.a(parcel, MediaSessionCompat$ResultReceiverWrapper.CREATOR));
                parcel2.writeNoException();
                return true;
            case 2:
                boolean zR = r((KeyEvent) androidx.work.impl.t.a(parcel, KeyEvent.CREATOR));
                parcel2.writeNoException();
                parcel2.writeInt(zR ? 1 : 0);
                return true;
            case 3:
                IBinder strongBinder = parcel.readStrongBinder();
                if (strongBinder != null) {
                    IInterface iInterfaceQueryLocalInterface = strongBinder.queryLocalInterface("android.support.v4.media.session.IMediaControllerCallback");
                    if (iInterfaceQueryLocalInterface == null || !(iInterfaceQueryLocalInterface instanceof b)) {
                        a aVar = new a();
                        aVar.f874c = strongBinder;
                        bVar = aVar;
                    } else {
                        bVar = (b) iInterfaceQueryLocalInterface;
                    }
                }
                E(bVar);
                parcel2.writeNoException();
                return true;
            case 4:
                IBinder strongBinder2 = parcel.readStrongBinder();
                if (strongBinder2 != null) {
                    IInterface iInterfaceQueryLocalInterface2 = strongBinder2.queryLocalInterface("android.support.v4.media.session.IMediaControllerCallback");
                    if (iInterfaceQueryLocalInterface2 == null || !(iInterfaceQueryLocalInterface2 instanceof b)) {
                        a aVar2 = new a();
                        aVar2.f874c = strongBinder2;
                        bVar2 = aVar2;
                    } else {
                        bVar2 = (b) iInterfaceQueryLocalInterface2;
                    }
                }
                P(bVar2);
                parcel2.writeNoException();
                return true;
            case 5:
                boolean zJ = J();
                parcel2.writeNoException();
                parcel2.writeInt(zJ ? 1 : 0);
                return true;
            case 6:
                String packageName = getPackageName();
                parcel2.writeNoException();
                parcel2.writeString(packageName);
                return true;
            case 7:
                String strB = b();
                parcel2.writeNoException();
                parcel2.writeString(strB);
                return true;
            case 8:
                PendingIntent pendingIntentI = i();
                parcel2.writeNoException();
                androidx.work.impl.t.D(parcel2, pendingIntentI);
                return true;
            case 9:
                long flags = getFlags();
                parcel2.writeNoException();
                parcel2.writeLong(flags);
                return true;
            case 10:
                ParcelableVolumeInfo parcelableVolumeInfoA0 = a0();
                parcel2.writeNoException();
                androidx.work.impl.t.D(parcel2, parcelableVolumeInfoA0);
                return true;
            case 11:
                int i12 = parcel.readInt();
                int i13 = parcel.readInt();
                parcel.readString();
                adjustVolume(i12, i13);
                parcel2.writeNoException();
                return true;
            case 12:
                int i14 = parcel.readInt();
                int i15 = parcel.readInt();
                parcel.readString();
                setVolumeTo(i14, i15);
                parcel2.writeNoException();
                return true;
            case 13:
                play();
                parcel2.writeNoException();
                return true;
            case 14:
                m(parcel.readString(), (Bundle) androidx.work.impl.t.a(parcel, Bundle.CREATOR));
                parcel2.writeNoException();
                return true;
            case 15:
                n(parcel.readString(), (Bundle) androidx.work.impl.t.a(parcel, Bundle.CREATOR));
                parcel2.writeNoException();
                return true;
            case 16:
                o((Uri) androidx.work.impl.t.a(parcel, Uri.CREATOR), (Bundle) androidx.work.impl.t.a(parcel, Bundle.CREATOR));
                parcel2.writeNoException();
                return true;
            case 17:
                z(parcel.readLong());
                parcel2.writeNoException();
                return true;
            case 18:
                pause();
                parcel2.writeNoException();
                return true;
            case 19:
                stop();
                parcel2.writeNoException();
                return true;
            case 20:
                next();
                parcel2.writeNoException();
                return true;
            case 21:
                previous();
                parcel2.writeNoException();
                return true;
            case 22:
                T();
                parcel2.writeNoException();
                return true;
            case 23:
                rewind();
                parcel2.writeNoException();
                return true;
            case 24:
                seekTo(parcel.readLong());
                parcel2.writeNoException();
                return true;
            case 25:
                G((RatingCompat) androidx.work.impl.t.a(parcel, RatingCompat.CREATOR));
                parcel2.writeNoException();
                return true;
            case 26:
                e(parcel.readString(), (Bundle) androidx.work.impl.t.a(parcel, Bundle.CREATOR));
                parcel2.writeNoException();
                return true;
            case 27:
                MediaMetadataCompat metadata = getMetadata();
                parcel2.writeNoException();
                androidx.work.impl.t.D(parcel2, metadata);
                return true;
            case 28:
                PlaybackStateCompat playbackState = getPlaybackState();
                parcel2.writeNoException();
                androidx.work.impl.t.D(parcel2, playbackState);
                return true;
            case 29:
                parcel2.writeNoException();
                parcel2.writeInt(-1);
                return true;
            case 30:
                CharSequence queueTitle = getQueueTitle();
                parcel2.writeNoException();
                if (queueTitle == null) {
                    parcel2.writeInt(0);
                    return true;
                }
                parcel2.writeInt(1);
                TextUtils.writeToParcel(queueTitle, parcel2, 1);
                return true;
            case 31:
                Bundle extras = getExtras();
                parcel2.writeNoException();
                androidx.work.impl.t.D(parcel2, extras);
                return true;
            case 32:
                getRatingType();
                parcel2.writeNoException();
                parcel2.writeInt(0);
                return true;
            case 33:
                prepare();
                parcel2.writeNoException();
                return true;
            case 34:
                l(parcel.readString(), (Bundle) androidx.work.impl.t.a(parcel, Bundle.CREATOR));
                parcel2.writeNoException();
                return true;
            case 35:
                M(parcel.readString(), (Bundle) androidx.work.impl.t.a(parcel, Bundle.CREATOR));
                parcel2.writeNoException();
                return true;
            case TsExtractor.TS_STREAM_TYPE_H265 /* 36 */:
                g((Uri) androidx.work.impl.t.a(parcel, Uri.CREATOR), (Bundle) androidx.work.impl.t.a(parcel, Bundle.CREATOR));
                parcel2.writeNoException();
                return true;
            case 37:
                int repeatMode = getRepeatMode();
                parcel2.writeNoException();
                parcel2.writeInt(repeatMode);
                return true;
            case FlacConstants.STREAM_INFO_BLOCK_SIZE /* 38 */:
                parcel2.writeNoException();
                parcel2.writeInt(0);
                return true;
            case NalUnitUtil.H265_NAL_UNIT_TYPE_PREFIX_SEI /* 39 */:
                setRepeatMode(parcel.readInt());
                parcel2.writeNoException();
                return true;
            case 40:
                parcel.readInt();
                parcel2.writeNoException();
                return true;
            case 41:
                K((MediaDescriptionCompat) androidx.work.impl.t.a(parcel, MediaDescriptionCompat.CREATOR));
                parcel2.writeNoException();
                return true;
            case AacUtil.AUDIO_OBJECT_TYPE_AAC_XHE /* 42 */:
                u((MediaDescriptionCompat) androidx.work.impl.t.a(parcel, MediaDescriptionCompat.CREATOR), parcel.readInt());
                parcel2.writeNoException();
                return true;
            case 43:
                I((MediaDescriptionCompat) androidx.work.impl.t.a(parcel, MediaDescriptionCompat.CREATOR));
                parcel2.writeNoException();
                return true;
            case 44:
                v(parcel.readInt());
                parcel2.writeNoException();
                return true;
            case TsExtractor.TS_STREAM_TYPE_MHAS /* 45 */:
                isCaptioningEnabled();
                parcel2.writeNoException();
                parcel2.writeInt(0);
                return true;
            case 46:
                V(parcel.readInt() != 0);
                parcel2.writeNoException();
                return true;
            case 47:
                int shuffleMode = getShuffleMode();
                parcel2.writeNoException();
                parcel2.writeInt(shuffleMode);
                return true;
            case 48:
                setShuffleMode(parcel.readInt());
                parcel2.writeNoException();
                return true;
            case 49:
                setPlaybackSpeed(parcel.readFloat());
                parcel2.writeNoException();
                return true;
            case 50:
                Bundle sessionInfo = getSessionInfo();
                parcel2.writeNoException();
                androidx.work.impl.t.D(parcel2, sessionInfo);
                return true;
            case 51:
                t((RatingCompat) androidx.work.impl.t.a(parcel, RatingCompat.CREATOR), (Bundle) androidx.work.impl.t.a(parcel, Bundle.CREATOR));
                parcel2.writeNoException();
                return true;
            default:
                return super.onTransact(i10, parcel, parcel2, i11);
        }
    }

    @Override // android.support.v4.media.session.d
    public final void pause() {
        throw new AssertionError();
    }

    @Override // android.support.v4.media.session.d
    public final void play() {
        throw new AssertionError();
    }

    @Override // android.support.v4.media.session.d
    public final void prepare() {
        throw new AssertionError();
    }

    @Override // android.support.v4.media.session.d
    public final void previous() {
        throw new AssertionError();
    }

    @Override // android.support.v4.media.session.d
    public final boolean r(KeyEvent keyEvent) {
        throw new AssertionError();
    }

    @Override // android.support.v4.media.session.d
    public final void rewind() {
        throw new AssertionError();
    }

    @Override // android.support.v4.media.session.d
    public final void seekTo(long j10) {
        throw new AssertionError();
    }

    @Override // android.support.v4.media.session.d
    public final void setPlaybackSpeed(float f10) {
        throw new AssertionError();
    }

    @Override // android.support.v4.media.session.d
    public final void setRepeatMode(int i10) {
        throw new AssertionError();
    }

    @Override // android.support.v4.media.session.d
    public final void setShuffleMode(int i10) {
        throw new AssertionError();
    }

    @Override // android.support.v4.media.session.d
    public final void setVolumeTo(int i10, int i11) {
        throw new AssertionError();
    }

    @Override // android.support.v4.media.session.d
    public final void stop() {
        throw new AssertionError();
    }

    @Override // android.support.v4.media.session.d
    public final void t(RatingCompat ratingCompat, Bundle bundle) {
        throw new AssertionError();
    }

    @Override // android.support.v4.media.session.d
    public final void u(MediaDescriptionCompat mediaDescriptionCompat, int i10) {
        throw new AssertionError();
    }

    @Override // android.support.v4.media.session.d
    public final void v(int i10) {
        throw new AssertionError();
    }

    @Override // android.support.v4.media.session.d
    public final void w(String str, Bundle bundle, MediaSessionCompat$ResultReceiverWrapper mediaSessionCompat$ResultReceiverWrapper) {
        throw new AssertionError();
    }

    @Override // android.support.v4.media.session.d
    public final void z(long j10) {
        throw new AssertionError();
    }

    @Override // android.os.IInterface
    public final IBinder asBinder() {
        return this;
    }

    @Override // android.support.v4.media.session.d
    public final void getQueue() {
    }
}
