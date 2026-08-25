package android.support.v4.media.session;

import android.content.Intent;
import android.media.Rating;
import android.media.session.MediaSession;
import android.net.Uri;
import android.os.BadParcelableException;
import android.os.Build;
import android.os.Bundle;
import android.os.ResultReceiver;
import android.support.v4.media.MediaDescriptionCompat;
import android.support.v4.media.RatingCompat;
import android.text.TextUtils;
import android.util.Log;
import androidx.media3.session.legacy.MediaControllerCompat;
import androidx.media3.session.legacy.MediaSessionCompat;

/* JADX INFO: loaded from: classes.dex */
public final class k extends MediaSession.Callback {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ l f883a;

    public k(l lVar) {
        this.f883a = lVar;
    }

    public static void b(o oVar) {
        int i10 = Build.VERSION.SDK_INT;
        if (i10 >= 28) {
            return;
        }
        MediaSession mediaSession = oVar.f886a;
        String str = null;
        if (i10 >= 24) {
            try {
                str = (String) mediaSession.getClass().getMethod("getCallingPackage", null).invoke(mediaSession, null);
            } catch (Exception e5) {
                Log.e("MediaSessionCompat", "Cannot execute MediaSession.getCallingPackage()", e5);
            }
        }
        if (TextUtils.isEmpty(str)) {
            str = "android.media.session.MediaController";
        }
        oVar.a(new androidx.media.d(str, -1, -1));
    }

    public final o a() {
        o oVar;
        synchronized (this.f883a.mLock) {
            oVar = (o) this.f883a.mSessionImpl.get();
        }
        if (oVar == null || this.f883a != oVar.getCallback()) {
            return null;
        }
        return oVar;
    }

    @Override // android.media.session.MediaSession.Callback
    public final void onCommand(String str, Bundle bundle, ResultReceiver resultReceiver) {
        k2.f fVar;
        o oVarA = a();
        if (oVarA == null) {
            return;
        }
        v.a(bundle);
        b(oVarA);
        try {
            if (str.equals(MediaControllerCompat.COMMAND_GET_EXTRA_BINDER)) {
                Bundle bundle2 = new Bundle();
                MediaSessionCompat$Token mediaSessionCompat$Token = oVarA.f888c;
                d dVarA = mediaSessionCompat$Token.a();
                bundle2.putBinder(MediaSessionCompat.KEY_EXTRA_BINDER, dVarA == null ? null : dVarA.asBinder());
                synchronized (mediaSessionCompat$Token.f849i) {
                    fVar = mediaSessionCompat$Token.f852n;
                }
                k2.c.y(bundle2, fVar);
                resultReceiver.send(0, bundle2);
            } else if (str.equals(MediaControllerCompat.COMMAND_ADD_QUEUE_ITEM)) {
                this.f883a.onAddQueueItem((MediaDescriptionCompat) bundle.getParcelable(MediaControllerCompat.COMMAND_ARGUMENT_MEDIA_DESCRIPTION));
            } else if (str.equals(MediaControllerCompat.COMMAND_ADD_QUEUE_ITEM_AT)) {
                this.f883a.onAddQueueItem((MediaDescriptionCompat) bundle.getParcelable(MediaControllerCompat.COMMAND_ARGUMENT_MEDIA_DESCRIPTION), bundle.getInt(MediaControllerCompat.COMMAND_ARGUMENT_INDEX));
            } else if (str.equals(MediaControllerCompat.COMMAND_REMOVE_QUEUE_ITEM)) {
                this.f883a.onRemoveQueueItem((MediaDescriptionCompat) bundle.getParcelable(MediaControllerCompat.COMMAND_ARGUMENT_MEDIA_DESCRIPTION));
            } else if (!str.equals(MediaControllerCompat.COMMAND_REMOVE_QUEUE_ITEM_AT)) {
                this.f883a.onCommand(str, bundle, resultReceiver);
            }
        } catch (BadParcelableException unused) {
            Log.e("MediaSessionCompat", "Could not unparcel the extra data.");
        }
        oVarA.a(null);
    }

    @Override // android.media.session.MediaSession.Callback
    public final void onCustomAction(String str, Bundle bundle) {
        o oVarA = a();
        if (oVarA == null) {
            return;
        }
        v.a(bundle);
        b(oVarA);
        try {
            boolean zEquals = str.equals(MediaSessionCompat.ACTION_PLAY_FROM_URI);
            l lVar = this.f883a;
            if (zEquals) {
                Uri uri = (Uri) bundle.getParcelable(MediaSessionCompat.ACTION_ARGUMENT_URI);
                Bundle bundle2 = bundle.getBundle(MediaSessionCompat.ACTION_ARGUMENT_EXTRAS);
                v.a(bundle2);
                lVar.onPlayFromUri(uri, bundle2);
            } else if (str.equals(MediaSessionCompat.ACTION_PREPARE)) {
                lVar.onPrepare();
            } else if (str.equals(MediaSessionCompat.ACTION_PREPARE_FROM_MEDIA_ID)) {
                String string = bundle.getString(MediaSessionCompat.ACTION_ARGUMENT_MEDIA_ID);
                Bundle bundle3 = bundle.getBundle(MediaSessionCompat.ACTION_ARGUMENT_EXTRAS);
                v.a(bundle3);
                lVar.onPrepareFromMediaId(string, bundle3);
            } else if (str.equals(MediaSessionCompat.ACTION_PREPARE_FROM_SEARCH)) {
                String string2 = bundle.getString(MediaSessionCompat.ACTION_ARGUMENT_QUERY);
                Bundle bundle4 = bundle.getBundle(MediaSessionCompat.ACTION_ARGUMENT_EXTRAS);
                v.a(bundle4);
                lVar.onPrepareFromSearch(string2, bundle4);
            } else if (str.equals(MediaSessionCompat.ACTION_PREPARE_FROM_URI)) {
                Uri uri2 = (Uri) bundle.getParcelable(MediaSessionCompat.ACTION_ARGUMENT_URI);
                Bundle bundle5 = bundle.getBundle(MediaSessionCompat.ACTION_ARGUMENT_EXTRAS);
                v.a(bundle5);
                lVar.onPrepareFromUri(uri2, bundle5);
            } else if (str.equals(MediaSessionCompat.ACTION_SET_CAPTIONING_ENABLED)) {
                lVar.onSetCaptioningEnabled(bundle.getBoolean(MediaSessionCompat.ACTION_ARGUMENT_CAPTIONING_ENABLED));
            } else if (str.equals(MediaSessionCompat.ACTION_SET_REPEAT_MODE)) {
                lVar.onSetRepeatMode(bundle.getInt(MediaSessionCompat.ACTION_ARGUMENT_REPEAT_MODE));
            } else if (str.equals(MediaSessionCompat.ACTION_SET_SHUFFLE_MODE)) {
                lVar.onSetShuffleMode(bundle.getInt(MediaSessionCompat.ACTION_ARGUMENT_SHUFFLE_MODE));
            } else if (str.equals(MediaSessionCompat.ACTION_SET_RATING)) {
                RatingCompat ratingCompat = (RatingCompat) bundle.getParcelable(MediaSessionCompat.ACTION_ARGUMENT_RATING);
                Bundle bundle6 = bundle.getBundle(MediaSessionCompat.ACTION_ARGUMENT_EXTRAS);
                v.a(bundle6);
                lVar.onSetRating(ratingCompat, bundle6);
            } else if (str.equals(MediaSessionCompat.ACTION_SET_PLAYBACK_SPEED)) {
                lVar.onSetPlaybackSpeed(bundle.getFloat(MediaSessionCompat.ACTION_ARGUMENT_PLAYBACK_SPEED, 1.0f));
            } else {
                lVar.onCustomAction(str, bundle);
            }
        } catch (BadParcelableException unused) {
            Log.e("MediaSessionCompat", "Could not unparcel the data.");
        }
        oVarA.a(null);
    }

    @Override // android.media.session.MediaSession.Callback
    public final void onFastForward() {
        o oVarA = a();
        if (oVarA == null) {
            return;
        }
        b(oVarA);
        this.f883a.onFastForward();
        oVarA.a(null);
    }

    @Override // android.media.session.MediaSession.Callback
    public final boolean onMediaButtonEvent(Intent intent) {
        o oVarA = a();
        if (oVarA == null) {
            return false;
        }
        b(oVarA);
        boolean zOnMediaButtonEvent = this.f883a.onMediaButtonEvent(intent);
        oVarA.a(null);
        return zOnMediaButtonEvent || super.onMediaButtonEvent(intent);
    }

    @Override // android.media.session.MediaSession.Callback
    public final void onPause() {
        o oVarA = a();
        if (oVarA == null) {
            return;
        }
        b(oVarA);
        this.f883a.onPause();
        oVarA.a(null);
    }

    @Override // android.media.session.MediaSession.Callback
    public final void onPlay() {
        o oVarA = a();
        if (oVarA == null) {
            return;
        }
        b(oVarA);
        this.f883a.onPlay();
        oVarA.a(null);
    }

    @Override // android.media.session.MediaSession.Callback
    public final void onPlayFromMediaId(String str, Bundle bundle) {
        o oVarA = a();
        if (oVarA == null) {
            return;
        }
        v.a(bundle);
        b(oVarA);
        this.f883a.onPlayFromMediaId(str, bundle);
        oVarA.a(null);
    }

    @Override // android.media.session.MediaSession.Callback
    public final void onPlayFromSearch(String str, Bundle bundle) {
        o oVarA = a();
        if (oVarA == null) {
            return;
        }
        v.a(bundle);
        b(oVarA);
        this.f883a.onPlayFromSearch(str, bundle);
        oVarA.a(null);
    }

    @Override // android.media.session.MediaSession.Callback
    public final void onPlayFromUri(Uri uri, Bundle bundle) {
        o oVarA = a();
        if (oVarA == null) {
            return;
        }
        v.a(bundle);
        b(oVarA);
        this.f883a.onPlayFromUri(uri, bundle);
        oVarA.a(null);
    }

    @Override // android.media.session.MediaSession.Callback
    public final void onPrepare() {
        o oVarA = a();
        if (oVarA == null) {
            return;
        }
        b(oVarA);
        this.f883a.onPrepare();
        oVarA.a(null);
    }

    @Override // android.media.session.MediaSession.Callback
    public final void onPrepareFromMediaId(String str, Bundle bundle) {
        o oVarA = a();
        if (oVarA == null) {
            return;
        }
        v.a(bundle);
        b(oVarA);
        this.f883a.onPrepareFromMediaId(str, bundle);
        oVarA.a(null);
    }

    @Override // android.media.session.MediaSession.Callback
    public final void onPrepareFromSearch(String str, Bundle bundle) {
        o oVarA = a();
        if (oVarA == null) {
            return;
        }
        v.a(bundle);
        b(oVarA);
        this.f883a.onPrepareFromSearch(str, bundle);
        oVarA.a(null);
    }

    @Override // android.media.session.MediaSession.Callback
    public final void onPrepareFromUri(Uri uri, Bundle bundle) {
        o oVarA = a();
        if (oVarA == null) {
            return;
        }
        v.a(bundle);
        b(oVarA);
        this.f883a.onPrepareFromUri(uri, bundle);
        oVarA.a(null);
    }

    @Override // android.media.session.MediaSession.Callback
    public final void onRewind() {
        o oVarA = a();
        if (oVarA == null) {
            return;
        }
        b(oVarA);
        this.f883a.onRewind();
        oVarA.a(null);
    }

    @Override // android.media.session.MediaSession.Callback
    public final void onSeekTo(long j10) {
        o oVarA = a();
        if (oVarA == null) {
            return;
        }
        b(oVarA);
        this.f883a.onSeekTo(j10);
        oVarA.a(null);
    }

    @Override // android.media.session.MediaSession.Callback
    public final void onSetPlaybackSpeed(float f10) {
        o oVarA = a();
        if (oVarA == null) {
            return;
        }
        b(oVarA);
        this.f883a.onSetPlaybackSpeed(f10);
        oVarA.a(null);
    }

    @Override // android.media.session.MediaSession.Callback
    public final void onSetRating(Rating rating) {
        float f10;
        o oVarA = a();
        if (oVarA == null) {
            return;
        }
        b(oVarA);
        RatingCompat ratingCompat = null;
        if (rating != null) {
            int iB = android.support.v4.media.h.b(rating);
            if (android.support.v4.media.h.e(rating)) {
                switch (iB) {
                    case 1:
                        ratingCompat = new RatingCompat(1, android.support.v4.media.h.d(rating) ? 1.0f : 0.0f);
                        break;
                    case 2:
                        ratingCompat = new RatingCompat(2, android.support.v4.media.h.f(rating) ? 1.0f : 0.0f);
                        break;
                    case 3:
                    case 4:
                    case 5:
                        float fC = android.support.v4.media.h.c(rating);
                        if (iB == 3) {
                            f10 = 3.0f;
                        } else if (iB == 4) {
                            f10 = 4.0f;
                        } else if (iB != 5) {
                            Log.e("Rating", "Invalid rating style (" + iB + ") for a star rating");
                        } else {
                            f10 = 5.0f;
                        }
                        if (fC >= 0.0f && fC <= f10) {
                            ratingCompat = new RatingCompat(iB, fC);
                        } else {
                            Log.e("Rating", "Trying to set out of range star-based rating");
                        }
                        break;
                    case 6:
                        float fA = android.support.v4.media.h.a(rating);
                        if (fA >= 0.0f && fA <= 100.0f) {
                            ratingCompat = new RatingCompat(6, fA);
                        } else {
                            Log.e("Rating", "Invalid percentage-based rating value");
                        }
                        break;
                }
            } else {
                switch (iB) {
                    case 1:
                    case 2:
                    case 3:
                    case 4:
                    case 5:
                    case 6:
                        ratingCompat = new RatingCompat(iB, -1.0f);
                        break;
                }
            }
            ratingCompat.getClass();
        }
        this.f883a.onSetRating(ratingCompat);
        oVarA.a(null);
    }

    @Override // android.media.session.MediaSession.Callback
    public final void onSkipToNext() {
        o oVarA = a();
        if (oVarA == null) {
            return;
        }
        b(oVarA);
        this.f883a.onSkipToNext();
        oVarA.a(null);
    }

    @Override // android.media.session.MediaSession.Callback
    public final void onSkipToPrevious() {
        o oVarA = a();
        if (oVarA == null) {
            return;
        }
        b(oVarA);
        this.f883a.onSkipToPrevious();
        oVarA.a(null);
    }

    @Override // android.media.session.MediaSession.Callback
    public final void onSkipToQueueItem(long j10) {
        o oVarA = a();
        if (oVarA == null) {
            return;
        }
        b(oVarA);
        this.f883a.onSkipToQueueItem(j10);
        oVarA.a(null);
    }

    @Override // android.media.session.MediaSession.Callback
    public final void onStop() {
        o oVarA = a();
        if (oVarA == null) {
            return;
        }
        b(oVarA);
        this.f883a.onStop();
        oVarA.a(null);
    }
}
