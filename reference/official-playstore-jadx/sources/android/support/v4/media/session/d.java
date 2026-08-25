package android.support.v4.media.session;

import android.app.PendingIntent;
import android.net.Uri;
import android.os.Bundle;
import android.os.IInterface;
import android.support.v4.media.MediaDescriptionCompat;
import android.support.v4.media.MediaMetadataCompat;
import android.support.v4.media.RatingCompat;
import android.view.KeyEvent;

/* JADX INFO: loaded from: classes.dex */
public interface d extends IInterface {
    void E(b bVar);

    void G(RatingCompat ratingCompat);

    void I(MediaDescriptionCompat mediaDescriptionCompat);

    boolean J();

    void K(MediaDescriptionCompat mediaDescriptionCompat);

    void M(String str, Bundle bundle);

    void P(b bVar);

    void T();

    void V(boolean z);

    ParcelableVolumeInfo a0();

    void adjustVolume(int i10, int i11);

    String b();

    void e(String str, Bundle bundle);

    void g(Uri uri, Bundle bundle);

    Bundle getExtras();

    long getFlags();

    MediaMetadataCompat getMetadata();

    String getPackageName();

    PlaybackStateCompat getPlaybackState();

    void getQueue();

    CharSequence getQueueTitle();

    void getRatingType();

    int getRepeatMode();

    Bundle getSessionInfo();

    int getShuffleMode();

    PendingIntent i();

    void isCaptioningEnabled();

    void l(String str, Bundle bundle);

    void m(String str, Bundle bundle);

    void n(String str, Bundle bundle);

    void next();

    void o(Uri uri, Bundle bundle);

    void pause();

    void play();

    void prepare();

    void previous();

    boolean r(KeyEvent keyEvent);

    void rewind();

    void seekTo(long j10);

    void setPlaybackSpeed(float f10);

    void setRepeatMode(int i10);

    void setShuffleMode(int i10);

    void setVolumeTo(int i10, int i11);

    void stop();

    void t(RatingCompat ratingCompat, Bundle bundle);

    void u(MediaDescriptionCompat mediaDescriptionCompat, int i10);

    void v(int i10);

    void w(String str, Bundle bundle, MediaSessionCompat$ResultReceiverWrapper mediaSessionCompat$ResultReceiverWrapper);

    void z(long j10);
}
