package androidx.media3.session.legacy;

import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.media.AudioAttributes;
import android.media.Rating;
import android.media.VolumeProvider;
import android.media.session.MediaSession;
import android.net.Uri;
import android.os.BadParcelableException;
import android.os.Binder;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteCallbackList;
import android.os.RemoteException;
import android.os.ResultReceiver;
import android.os.SystemClock;
import android.support.v4.media.session.q;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.ViewConfiguration;
import androidx.media3.common.util.Log;
import androidx.media3.session.legacy.IMediaSession;
import androidx.media3.session.legacy.MediaSessionManager;
import androidx.media3.session.legacy.PlaybackStateCompat;
import java.lang.ref.WeakReference;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import k2.c;
import k2.f;

/* JADX INFO: loaded from: classes3.dex */
public class MediaSessionCompat {
    public static final String ACTION_ARGUMENT_CAPTIONING_ENABLED = "android.support.v4.media.session.action.ARGUMENT_CAPTIONING_ENABLED";
    public static final String ACTION_ARGUMENT_EXTRAS = "android.support.v4.media.session.action.ARGUMENT_EXTRAS";
    public static final String ACTION_ARGUMENT_MEDIA_ID = "android.support.v4.media.session.action.ARGUMENT_MEDIA_ID";
    public static final String ACTION_ARGUMENT_PLAYBACK_SPEED = "android.support.v4.media.session.action.ARGUMENT_PLAYBACK_SPEED";
    public static final String ACTION_ARGUMENT_QUERY = "android.support.v4.media.session.action.ARGUMENT_QUERY";
    public static final String ACTION_ARGUMENT_RATING = "android.support.v4.media.session.action.ARGUMENT_RATING";
    public static final String ACTION_ARGUMENT_REPEAT_MODE = "android.support.v4.media.session.action.ARGUMENT_REPEAT_MODE";
    public static final String ACTION_ARGUMENT_SHUFFLE_MODE = "android.support.v4.media.session.action.ARGUMENT_SHUFFLE_MODE";
    public static final String ACTION_ARGUMENT_URI = "android.support.v4.media.session.action.ARGUMENT_URI";
    public static final String ACTION_FLAG_AS_INAPPROPRIATE = "android.support.v4.media.session.action.FLAG_AS_INAPPROPRIATE";
    public static final String ACTION_FOLLOW = "android.support.v4.media.session.action.FOLLOW";
    public static final String ACTION_PLAY_FROM_URI = "android.support.v4.media.session.action.PLAY_FROM_URI";
    public static final String ACTION_PREPARE = "android.support.v4.media.session.action.PREPARE";
    public static final String ACTION_PREPARE_FROM_MEDIA_ID = "android.support.v4.media.session.action.PREPARE_FROM_MEDIA_ID";
    public static final String ACTION_PREPARE_FROM_SEARCH = "android.support.v4.media.session.action.PREPARE_FROM_SEARCH";
    public static final String ACTION_PREPARE_FROM_URI = "android.support.v4.media.session.action.PREPARE_FROM_URI";
    public static final String ACTION_SET_CAPTIONING_ENABLED = "android.support.v4.media.session.action.SET_CAPTIONING_ENABLED";
    public static final String ACTION_SET_PLAYBACK_SPEED = "android.support.v4.media.session.action.SET_PLAYBACK_SPEED";
    public static final String ACTION_SET_RATING = "android.support.v4.media.session.action.SET_RATING";
    public static final String ACTION_SET_REPEAT_MODE = "android.support.v4.media.session.action.SET_REPEAT_MODE";
    public static final String ACTION_SET_SHUFFLE_MODE = "android.support.v4.media.session.action.SET_SHUFFLE_MODE";
    public static final String ACTION_SKIP_AD = "android.support.v4.media.session.action.SKIP_AD";
    public static final String ACTION_UNFOLLOW = "android.support.v4.media.session.action.UNFOLLOW";
    public static final String ARGUMENT_MEDIA_ATTRIBUTE = "android.support.v4.media.session.ARGUMENT_MEDIA_ATTRIBUTE";
    public static final String ARGUMENT_MEDIA_ATTRIBUTE_VALUE = "android.support.v4.media.session.ARGUMENT_MEDIA_ATTRIBUTE_VALUE";

    @Deprecated
    public static final int FLAG_HANDLES_MEDIA_BUTTONS = 1;
    public static final int FLAG_HANDLES_QUEUE_COMMANDS = 4;

    @Deprecated
    public static final int FLAG_HANDLES_TRANSPORT_CONTROLS = 2;
    public static final String KEY_EXTRA_BINDER = "android.support.v4.media.session.EXTRA_BINDER";
    public static final String KEY_SESSION2_TOKEN = "android.support.v4.media.session.SESSION_TOKEN2";
    public static final String KEY_TOKEN = "android.support.v4.media.session.TOKEN";
    static final String TAG = "MediaSessionCompat";
    private final MediaControllerCompat controller;
    private final MediaSessionImpl impl;

    public static abstract class Callback {
        CallbackHandler callbackHandler;
        private boolean mediaPlayPausePendingOnHandler;
        final Object lock = new Object();
        final MediaSession.Callback callbackFwk = new MediaSessionCallback();
        WeakReference<MediaSessionImpl> sessionImpl = new WeakReference<>(null);

        public class CallbackHandler extends Handler {
            private static final int MSG_MEDIA_PLAY_PAUSE_KEY_DOUBLE_TAP_TIMEOUT = 1;

            public CallbackHandler(Looper looper) {
                super(looper);
            }

            @Override // android.os.Handler
            public void handleMessage(Message message) {
                MediaSessionImpl mediaSessionImpl;
                Callback callback;
                CallbackHandler callbackHandler;
                if (message.what == 1) {
                    synchronized (Callback.this.lock) {
                        mediaSessionImpl = Callback.this.sessionImpl.get();
                        callback = Callback.this;
                        callbackHandler = callback.callbackHandler;
                    }
                    if (mediaSessionImpl == null || callback != mediaSessionImpl.getCallback() || callbackHandler == null) {
                        return;
                    }
                    mediaSessionImpl.setCurrentControllerInfo((MediaSessionManager.RemoteUserInfo) message.obj);
                    Callback.this.handleMediaPlayPauseIfPendingOnHandler(mediaSessionImpl, callbackHandler);
                    mediaSessionImpl.setCurrentControllerInfo(null);
                }
            }
        }

        public class MediaSessionCallback extends MediaSession.Callback {
            public MediaSessionCallback() {
            }

            private void clearCurrentControllerInfo(MediaSessionImpl mediaSessionImpl) {
                mediaSessionImpl.setCurrentControllerInfo(null);
            }

            private MediaSessionImplApi23 getSessionImplIfCallbackIsSet() {
                MediaSessionImplApi23 mediaSessionImplApi23;
                synchronized (Callback.this.lock) {
                    mediaSessionImplApi23 = (MediaSessionImplApi23) Callback.this.sessionImpl.get();
                }
                if (mediaSessionImplApi23 == null || Callback.this != mediaSessionImplApi23.getCallback()) {
                    return null;
                }
                return mediaSessionImplApi23;
            }

            private void setCurrentControllerInfo(MediaSessionImpl mediaSessionImpl) {
                if (Build.VERSION.SDK_INT >= 28) {
                    return;
                }
                String callingPackage = mediaSessionImpl.getCallingPackage();
                if (TextUtils.isEmpty(callingPackage)) {
                    callingPackage = "android.media.session.MediaController";
                }
                mediaSessionImpl.setCurrentControllerInfo(new MediaSessionManager.RemoteUserInfo(callingPackage, -1, -1));
            }

            @Override // android.media.session.MediaSession.Callback
            public void onCommand(String str, Bundle bundle, ResultReceiver resultReceiver) {
                MediaSessionImplApi23 sessionImplIfCallbackIsSet = getSessionImplIfCallbackIsSet();
                if (sessionImplIfCallbackIsSet == null) {
                    return;
                }
                MediaSessionCompat.ensureClassLoader(bundle);
                setCurrentControllerInfo(sessionImplIfCallbackIsSet);
                try {
                    QueueItem queueItem = null;
                    IBinder iBinderAsBinder = null;
                    queueItem = null;
                    if (str.equals(MediaControllerCompat.COMMAND_GET_EXTRA_BINDER)) {
                        if (resultReceiver != null) {
                            Bundle bundle2 = new Bundle();
                            Token sessionToken = sessionImplIfCallbackIsSet.getSessionToken();
                            IMediaSession extraBinder = sessionToken.getExtraBinder();
                            if (extraBinder != null) {
                                iBinderAsBinder = extraBinder.asBinder();
                            }
                            bundle2.putBinder(MediaSessionCompat.KEY_EXTRA_BINDER, iBinderAsBinder);
                            c.y(bundle2, sessionToken.getSession2Token());
                            resultReceiver.send(0, bundle2);
                        }
                    } else if (str.equals(MediaControllerCompat.COMMAND_ADD_QUEUE_ITEM)) {
                        if (bundle != null) {
                            Callback.this.onAddQueueItem((MediaDescriptionCompat) LegacyParcelableUtil.convert(bundle.getParcelable(MediaControllerCompat.COMMAND_ARGUMENT_MEDIA_DESCRIPTION), MediaDescriptionCompat.CREATOR));
                        }
                    } else if (str.equals(MediaControllerCompat.COMMAND_ADD_QUEUE_ITEM_AT)) {
                        if (bundle != null) {
                            Callback.this.onAddQueueItem((MediaDescriptionCompat) LegacyParcelableUtil.convert(bundle.getParcelable(MediaControllerCompat.COMMAND_ARGUMENT_MEDIA_DESCRIPTION), MediaDescriptionCompat.CREATOR), bundle.getInt(MediaControllerCompat.COMMAND_ARGUMENT_INDEX));
                        }
                    } else if (str.equals(MediaControllerCompat.COMMAND_REMOVE_QUEUE_ITEM)) {
                        if (bundle != null) {
                            Callback.this.onRemoveQueueItem((MediaDescriptionCompat) LegacyParcelableUtil.convert(bundle.getParcelable(MediaControllerCompat.COMMAND_ARGUMENT_MEDIA_DESCRIPTION), MediaDescriptionCompat.CREATOR));
                        }
                    } else if (str.equals(MediaControllerCompat.COMMAND_REMOVE_QUEUE_ITEM_AT)) {
                        List<QueueItem> list = sessionImplIfCallbackIsSet.queue;
                        if (list != null && bundle != null) {
                            int i10 = bundle.getInt(MediaControllerCompat.COMMAND_ARGUMENT_INDEX, -1);
                            if (i10 >= 0 && i10 < list.size()) {
                                queueItem = list.get(i10);
                            }
                            if (queueItem != null) {
                                Callback.this.onRemoveQueueItem(queueItem.getDescription());
                            }
                        }
                    } else {
                        Callback.this.onCommand(str, bundle, resultReceiver);
                    }
                } catch (BadParcelableException unused) {
                    Log.e(MediaSessionCompat.TAG, "Could not unparcel the extra data.");
                }
                clearCurrentControllerInfo(sessionImplIfCallbackIsSet);
            }

            @Override // android.media.session.MediaSession.Callback
            public void onCustomAction(String str, Bundle bundle) {
                MediaSessionImplApi23 sessionImplIfCallbackIsSet = getSessionImplIfCallbackIsSet();
                if (sessionImplIfCallbackIsSet == null) {
                    return;
                }
                MediaSessionCompat.ensureClassLoader(bundle);
                setCurrentControllerInfo(sessionImplIfCallbackIsSet);
                try {
                    if (str.equals(MediaSessionCompat.ACTION_PLAY_FROM_URI)) {
                        if (bundle != null) {
                            Uri uri = (Uri) bundle.getParcelable(MediaSessionCompat.ACTION_ARGUMENT_URI);
                            Bundle bundle2 = bundle.getBundle(MediaSessionCompat.ACTION_ARGUMENT_EXTRAS);
                            MediaSessionCompat.ensureClassLoader(bundle2);
                            Callback.this.onPlayFromUri(uri, bundle2);
                        }
                    } else if (str.equals(MediaSessionCompat.ACTION_PREPARE)) {
                        Callback.this.onPrepare();
                    } else if (str.equals(MediaSessionCompat.ACTION_PREPARE_FROM_MEDIA_ID)) {
                        if (bundle != null) {
                            String string = bundle.getString(MediaSessionCompat.ACTION_ARGUMENT_MEDIA_ID);
                            Bundle bundle3 = bundle.getBundle(MediaSessionCompat.ACTION_ARGUMENT_EXTRAS);
                            MediaSessionCompat.ensureClassLoader(bundle3);
                            Callback.this.onPrepareFromMediaId(string, bundle3);
                        }
                    } else if (str.equals(MediaSessionCompat.ACTION_PREPARE_FROM_SEARCH)) {
                        if (bundle != null) {
                            String string2 = bundle.getString(MediaSessionCompat.ACTION_ARGUMENT_QUERY);
                            Bundle bundle4 = bundle.getBundle(MediaSessionCompat.ACTION_ARGUMENT_EXTRAS);
                            MediaSessionCompat.ensureClassLoader(bundle4);
                            Callback.this.onPrepareFromSearch(string2, bundle4);
                        }
                    } else if (str.equals(MediaSessionCompat.ACTION_PREPARE_FROM_URI)) {
                        if (bundle != null) {
                            Uri uri2 = (Uri) bundle.getParcelable(MediaSessionCompat.ACTION_ARGUMENT_URI);
                            Bundle bundle5 = bundle.getBundle(MediaSessionCompat.ACTION_ARGUMENT_EXTRAS);
                            MediaSessionCompat.ensureClassLoader(bundle5);
                            Callback.this.onPrepareFromUri(uri2, bundle5);
                        }
                    } else if (str.equals(MediaSessionCompat.ACTION_SET_CAPTIONING_ENABLED)) {
                        if (bundle != null) {
                            Callback.this.onSetCaptioningEnabled(bundle.getBoolean(MediaSessionCompat.ACTION_ARGUMENT_CAPTIONING_ENABLED));
                        }
                    } else if (str.equals(MediaSessionCompat.ACTION_SET_REPEAT_MODE)) {
                        if (bundle != null) {
                            Callback.this.onSetRepeatMode(bundle.getInt(MediaSessionCompat.ACTION_ARGUMENT_REPEAT_MODE));
                        }
                    } else if (str.equals(MediaSessionCompat.ACTION_SET_SHUFFLE_MODE)) {
                        if (bundle != null) {
                            Callback.this.onSetShuffleMode(bundle.getInt(MediaSessionCompat.ACTION_ARGUMENT_SHUFFLE_MODE));
                        }
                    } else if (str.equals(MediaSessionCompat.ACTION_SET_RATING)) {
                        if (bundle != null) {
                            RatingCompat ratingCompat = (RatingCompat) LegacyParcelableUtil.convert(bundle.getParcelable(MediaSessionCompat.ACTION_ARGUMENT_RATING), RatingCompat.CREATOR);
                            Bundle bundle6 = bundle.getBundle(MediaSessionCompat.ACTION_ARGUMENT_EXTRAS);
                            MediaSessionCompat.ensureClassLoader(bundle6);
                            Callback.this.onSetRating(ratingCompat, bundle6);
                        }
                    } else if (!str.equals(MediaSessionCompat.ACTION_SET_PLAYBACK_SPEED)) {
                        Callback.this.onCustomAction(str, bundle);
                    } else if (bundle != null) {
                        Callback.this.onSetPlaybackSpeed(bundle.getFloat(MediaSessionCompat.ACTION_ARGUMENT_PLAYBACK_SPEED, 1.0f));
                    }
                } catch (BadParcelableException unused) {
                    Log.e(MediaSessionCompat.TAG, "Could not unparcel the data.");
                }
                clearCurrentControllerInfo(sessionImplIfCallbackIsSet);
            }

            @Override // android.media.session.MediaSession.Callback
            public void onFastForward() {
                MediaSessionImplApi23 sessionImplIfCallbackIsSet = getSessionImplIfCallbackIsSet();
                if (sessionImplIfCallbackIsSet == null) {
                    return;
                }
                setCurrentControllerInfo(sessionImplIfCallbackIsSet);
                Callback.this.onFastForward();
                clearCurrentControllerInfo(sessionImplIfCallbackIsSet);
            }

            @Override // android.media.session.MediaSession.Callback
            public boolean onMediaButtonEvent(Intent intent) {
                MediaSessionImplApi23 sessionImplIfCallbackIsSet = getSessionImplIfCallbackIsSet();
                if (sessionImplIfCallbackIsSet == null) {
                    return false;
                }
                setCurrentControllerInfo(sessionImplIfCallbackIsSet);
                boolean zOnMediaButtonEvent = Callback.this.onMediaButtonEvent(intent);
                clearCurrentControllerInfo(sessionImplIfCallbackIsSet);
                return zOnMediaButtonEvent || super.onMediaButtonEvent(intent);
            }

            @Override // android.media.session.MediaSession.Callback
            public void onPause() {
                MediaSessionImplApi23 sessionImplIfCallbackIsSet = getSessionImplIfCallbackIsSet();
                if (sessionImplIfCallbackIsSet == null) {
                    return;
                }
                setCurrentControllerInfo(sessionImplIfCallbackIsSet);
                Callback.this.onPause();
                clearCurrentControllerInfo(sessionImplIfCallbackIsSet);
            }

            @Override // android.media.session.MediaSession.Callback
            public void onPlay() {
                MediaSessionImplApi23 sessionImplIfCallbackIsSet = getSessionImplIfCallbackIsSet();
                if (sessionImplIfCallbackIsSet == null) {
                    return;
                }
                setCurrentControllerInfo(sessionImplIfCallbackIsSet);
                Callback.this.onPlay();
                clearCurrentControllerInfo(sessionImplIfCallbackIsSet);
            }

            @Override // android.media.session.MediaSession.Callback
            public void onPlayFromMediaId(String str, Bundle bundle) {
                MediaSessionImplApi23 sessionImplIfCallbackIsSet = getSessionImplIfCallbackIsSet();
                if (sessionImplIfCallbackIsSet == null) {
                    return;
                }
                MediaSessionCompat.ensureClassLoader(bundle);
                setCurrentControllerInfo(sessionImplIfCallbackIsSet);
                Callback.this.onPlayFromMediaId(str, bundle);
                clearCurrentControllerInfo(sessionImplIfCallbackIsSet);
            }

            @Override // android.media.session.MediaSession.Callback
            public void onPlayFromSearch(String str, Bundle bundle) {
                MediaSessionImplApi23 sessionImplIfCallbackIsSet = getSessionImplIfCallbackIsSet();
                if (sessionImplIfCallbackIsSet == null) {
                    return;
                }
                MediaSessionCompat.ensureClassLoader(bundle);
                setCurrentControllerInfo(sessionImplIfCallbackIsSet);
                Callback.this.onPlayFromSearch(str, bundle);
                clearCurrentControllerInfo(sessionImplIfCallbackIsSet);
            }

            @Override // android.media.session.MediaSession.Callback
            public void onPlayFromUri(Uri uri, Bundle bundle) {
                MediaSessionImplApi23 sessionImplIfCallbackIsSet = getSessionImplIfCallbackIsSet();
                if (sessionImplIfCallbackIsSet == null) {
                    return;
                }
                MediaSessionCompat.ensureClassLoader(bundle);
                setCurrentControllerInfo(sessionImplIfCallbackIsSet);
                Callback.this.onPlayFromUri(uri, bundle);
                clearCurrentControllerInfo(sessionImplIfCallbackIsSet);
            }

            @Override // android.media.session.MediaSession.Callback
            public void onPrepare() {
                MediaSessionImplApi23 sessionImplIfCallbackIsSet = getSessionImplIfCallbackIsSet();
                if (sessionImplIfCallbackIsSet == null) {
                    return;
                }
                setCurrentControllerInfo(sessionImplIfCallbackIsSet);
                Callback.this.onPrepare();
                clearCurrentControllerInfo(sessionImplIfCallbackIsSet);
            }

            @Override // android.media.session.MediaSession.Callback
            public void onPrepareFromMediaId(String str, Bundle bundle) {
                MediaSessionImplApi23 sessionImplIfCallbackIsSet = getSessionImplIfCallbackIsSet();
                if (sessionImplIfCallbackIsSet == null) {
                    return;
                }
                MediaSessionCompat.ensureClassLoader(bundle);
                setCurrentControllerInfo(sessionImplIfCallbackIsSet);
                Callback.this.onPrepareFromMediaId(str, bundle);
                clearCurrentControllerInfo(sessionImplIfCallbackIsSet);
            }

            @Override // android.media.session.MediaSession.Callback
            public void onPrepareFromSearch(String str, Bundle bundle) {
                MediaSessionImplApi23 sessionImplIfCallbackIsSet = getSessionImplIfCallbackIsSet();
                if (sessionImplIfCallbackIsSet == null) {
                    return;
                }
                MediaSessionCompat.ensureClassLoader(bundle);
                setCurrentControllerInfo(sessionImplIfCallbackIsSet);
                Callback.this.onPrepareFromSearch(str, bundle);
                clearCurrentControllerInfo(sessionImplIfCallbackIsSet);
            }

            @Override // android.media.session.MediaSession.Callback
            public void onPrepareFromUri(Uri uri, Bundle bundle) {
                MediaSessionImplApi23 sessionImplIfCallbackIsSet = getSessionImplIfCallbackIsSet();
                if (sessionImplIfCallbackIsSet == null) {
                    return;
                }
                MediaSessionCompat.ensureClassLoader(bundle);
                setCurrentControllerInfo(sessionImplIfCallbackIsSet);
                Callback.this.onPrepareFromUri(uri, bundle);
                clearCurrentControllerInfo(sessionImplIfCallbackIsSet);
            }

            @Override // android.media.session.MediaSession.Callback
            public void onRewind() {
                MediaSessionImplApi23 sessionImplIfCallbackIsSet = getSessionImplIfCallbackIsSet();
                if (sessionImplIfCallbackIsSet == null) {
                    return;
                }
                setCurrentControllerInfo(sessionImplIfCallbackIsSet);
                Callback.this.onRewind();
                clearCurrentControllerInfo(sessionImplIfCallbackIsSet);
            }

            @Override // android.media.session.MediaSession.Callback
            public void onSeekTo(long j10) {
                MediaSessionImplApi23 sessionImplIfCallbackIsSet = getSessionImplIfCallbackIsSet();
                if (sessionImplIfCallbackIsSet == null) {
                    return;
                }
                setCurrentControllerInfo(sessionImplIfCallbackIsSet);
                Callback.this.onSeekTo(j10);
                clearCurrentControllerInfo(sessionImplIfCallbackIsSet);
            }

            @Override // android.media.session.MediaSession.Callback
            public void onSetPlaybackSpeed(float f10) {
                MediaSessionImplApi23 sessionImplIfCallbackIsSet = getSessionImplIfCallbackIsSet();
                if (sessionImplIfCallbackIsSet == null) {
                    return;
                }
                setCurrentControllerInfo(sessionImplIfCallbackIsSet);
                Callback.this.onSetPlaybackSpeed(f10);
                clearCurrentControllerInfo(sessionImplIfCallbackIsSet);
            }

            @Override // android.media.session.MediaSession.Callback
            public void onSetRating(Rating rating) {
                MediaSessionImplApi23 sessionImplIfCallbackIsSet = getSessionImplIfCallbackIsSet();
                if (sessionImplIfCallbackIsSet == null) {
                    return;
                }
                setCurrentControllerInfo(sessionImplIfCallbackIsSet);
                Callback.this.onSetRating(RatingCompat.fromRating(rating));
                clearCurrentControllerInfo(sessionImplIfCallbackIsSet);
            }

            @Override // android.media.session.MediaSession.Callback
            public void onSkipToNext() {
                MediaSessionImplApi23 sessionImplIfCallbackIsSet = getSessionImplIfCallbackIsSet();
                if (sessionImplIfCallbackIsSet == null) {
                    return;
                }
                setCurrentControllerInfo(sessionImplIfCallbackIsSet);
                Callback.this.onSkipToNext();
                clearCurrentControllerInfo(sessionImplIfCallbackIsSet);
            }

            @Override // android.media.session.MediaSession.Callback
            public void onSkipToPrevious() {
                MediaSessionImplApi23 sessionImplIfCallbackIsSet = getSessionImplIfCallbackIsSet();
                if (sessionImplIfCallbackIsSet == null) {
                    return;
                }
                setCurrentControllerInfo(sessionImplIfCallbackIsSet);
                Callback.this.onSkipToPrevious();
                clearCurrentControllerInfo(sessionImplIfCallbackIsSet);
            }

            @Override // android.media.session.MediaSession.Callback
            public void onSkipToQueueItem(long j10) {
                MediaSessionImplApi23 sessionImplIfCallbackIsSet = getSessionImplIfCallbackIsSet();
                if (sessionImplIfCallbackIsSet == null) {
                    return;
                }
                setCurrentControllerInfo(sessionImplIfCallbackIsSet);
                Callback.this.onSkipToQueueItem(j10);
                clearCurrentControllerInfo(sessionImplIfCallbackIsSet);
            }

            @Override // android.media.session.MediaSession.Callback
            public void onStop() {
                MediaSessionImplApi23 sessionImplIfCallbackIsSet = getSessionImplIfCallbackIsSet();
                if (sessionImplIfCallbackIsSet == null) {
                    return;
                }
                setCurrentControllerInfo(sessionImplIfCallbackIsSet);
                Callback.this.onStop();
                clearCurrentControllerInfo(sessionImplIfCallbackIsSet);
            }
        }

        public void handleMediaPlayPauseIfPendingOnHandler(MediaSessionImpl mediaSessionImpl, Handler handler) {
            if (this.mediaPlayPausePendingOnHandler) {
                this.mediaPlayPausePendingOnHandler = false;
                handler.removeMessages(1);
                PlaybackStateCompat playbackState = mediaSessionImpl.getPlaybackState();
                long actions = playbackState == null ? 0L : playbackState.getActions();
                boolean z = playbackState != null && playbackState.getState() == 3;
                boolean z5 = (516 & actions) != 0;
                boolean z10 = (actions & 514) != 0;
                if (z && z10) {
                    onPause();
                } else {
                    if (z || !z5) {
                        return;
                    }
                    onPlay();
                }
            }
        }

        public void onAddQueueItem(MediaDescriptionCompat mediaDescriptionCompat) {
        }

        public void onCommand(String str, Bundle bundle, ResultReceiver resultReceiver) {
        }

        public void onCustomAction(String str, Bundle bundle) {
        }

        public void onFastForward() {
        }

        public boolean onMediaButtonEvent(Intent intent) {
            MediaSessionImpl mediaSessionImpl;
            CallbackHandler callbackHandler;
            KeyEvent keyEvent;
            if (Build.VERSION.SDK_INT >= 27) {
                return false;
            }
            synchronized (this.lock) {
                mediaSessionImpl = this.sessionImpl.get();
                callbackHandler = this.callbackHandler;
            }
            if (mediaSessionImpl == null || callbackHandler == null || (keyEvent = (KeyEvent) intent.getParcelableExtra("android.intent.extra.KEY_EVENT")) == null || keyEvent.getAction() != 0) {
                return false;
            }
            MediaSessionManager.RemoteUserInfo currentControllerInfo = mediaSessionImpl.getCurrentControllerInfo();
            int keyCode = keyEvent.getKeyCode();
            if (keyCode != 79 && keyCode != 85) {
                handleMediaPlayPauseIfPendingOnHandler(mediaSessionImpl, callbackHandler);
                return false;
            }
            if (keyEvent.getRepeatCount() != 0) {
                handleMediaPlayPauseIfPendingOnHandler(mediaSessionImpl, callbackHandler);
            } else if (this.mediaPlayPausePendingOnHandler) {
                callbackHandler.removeMessages(1);
                this.mediaPlayPausePendingOnHandler = false;
                PlaybackStateCompat playbackState = mediaSessionImpl.getPlaybackState();
                if (((playbackState == null ? 0L : playbackState.getActions()) & 32) != 0) {
                    onSkipToNext();
                }
            } else {
                this.mediaPlayPausePendingOnHandler = true;
                callbackHandler.sendMessageDelayed(callbackHandler.obtainMessage(1, currentControllerInfo), ViewConfiguration.getDoubleTapTimeout());
            }
            return true;
        }

        public void onPause() {
        }

        public void onPlay() {
        }

        public void onPlayFromMediaId(String str, Bundle bundle) {
        }

        public void onPlayFromSearch(String str, Bundle bundle) {
        }

        public void onPlayFromUri(Uri uri, Bundle bundle) {
        }

        public void onPrepare() {
        }

        public void onPrepareFromMediaId(String str, Bundle bundle) {
        }

        public void onPrepareFromSearch(String str, Bundle bundle) {
        }

        public void onPrepareFromUri(Uri uri, Bundle bundle) {
        }

        public void onRemoveQueueItem(MediaDescriptionCompat mediaDescriptionCompat) {
        }

        public void onRewind() {
        }

        public void onSeekTo(long j10) {
        }

        public void onSetCaptioningEnabled(boolean z) {
        }

        public void onSetPlaybackSpeed(float f10) {
        }

        public void onSetRating(RatingCompat ratingCompat) {
        }

        public void onSetRepeatMode(int i10) {
        }

        public void onSetShuffleMode(int i10) {
        }

        public void onSkipToNext() {
        }

        public void onSkipToPrevious() {
        }

        public void onSkipToQueueItem(long j10) {
        }

        public void onStop() {
        }

        public void setSessionImpl(MediaSessionImpl mediaSessionImpl, Handler handler) {
            synchronized (this.lock) {
                try {
                    this.sessionImpl = new WeakReference<>(mediaSessionImpl);
                    CallbackHandler callbackHandler = this.callbackHandler;
                    CallbackHandler callbackHandler2 = null;
                    if (callbackHandler != null) {
                        callbackHandler.removeCallbacksAndMessages(null);
                    }
                    if (handler != null) {
                        callbackHandler2 = new CallbackHandler(handler.getLooper());
                    }
                    this.callbackHandler = callbackHandler2;
                } catch (Throwable th) {
                    throw th;
                }
            }
        }

        public void onAddQueueItem(MediaDescriptionCompat mediaDescriptionCompat, int i10) {
        }

        public void onSetRating(RatingCompat ratingCompat, Bundle bundle) {
        }
    }

    public interface MediaSessionImpl {
        Callback getCallback();

        String getCallingPackage();

        MediaSessionManager.RemoteUserInfo getCurrentControllerInfo();

        Object getMediaSession();

        PlaybackStateCompat getPlaybackState();

        Token getSessionToken();

        boolean isActive();

        void release();

        void sendSessionEvent(String str, Bundle bundle);

        void setActive(boolean z);

        void setCallback(Callback callback, Handler handler);

        void setCurrentControllerInfo(MediaSessionManager.RemoteUserInfo remoteUserInfo);

        void setExtras(Bundle bundle);

        void setFlags(int i10);

        void setMediaButtonReceiver(PendingIntent pendingIntent);

        void setMetadata(MediaMetadataCompat mediaMetadataCompat);

        void setPlaybackState(PlaybackStateCompat playbackStateCompat);

        void setPlaybackToLocal(int i10);

        void setPlaybackToRemote(VolumeProviderCompat volumeProviderCompat);

        void setQueue(List<QueueItem> list);

        void setQueueTitle(CharSequence charSequence);

        void setRatingType(int i10);

        void setRepeatMode(int i10);

        void setSessionActivity(PendingIntent pendingIntent);

        void setShuffleMode(int i10);
    }

    public static class MediaSessionImplApi23 implements MediaSessionImpl {
        Callback callback;
        boolean captioningEnabled;
        final ExtraSession extraSession;
        MediaMetadataCompat metadata;
        PlaybackStateCompat playbackState;
        List<QueueItem> queue;
        RegistrationCallbackHandler registrationCallbackHandler;
        MediaSessionManager.RemoteUserInfo remoteUserInfo;
        int repeatMode;
        final MediaSession sessionFwk;
        Bundle sessionInfo;
        int shuffleMode;
        final Token token;
        final Object lock = new Object();
        boolean destroyed = false;
        final RemoteCallbackList<IMediaControllerCallback> extraControllerCallbacks = new RemoteCallbackList<>();

        public static class ExtraSession extends IMediaSession.Stub {
            private final WeakReference<MediaSessionImplApi23> mediaSessionImplRef;

            public ExtraSession(MediaSessionImplApi23 mediaSessionImplApi23) {
                this.mediaSessionImplRef = new WeakReference<>(mediaSessionImplApi23);
            }

            @Override // androidx.media3.session.legacy.IMediaSession
            public PlaybackStateCompat getPlaybackState() {
                MediaSessionImplApi23 mediaSessionImplApi23 = this.mediaSessionImplRef.get();
                if (mediaSessionImplApi23 != null) {
                    return MediaSessionCompat.getStateWithUpdatedPosition(mediaSessionImplApi23.playbackState, mediaSessionImplApi23.metadata);
                }
                return null;
            }

            @Override // androidx.media3.session.legacy.IMediaSession
            public int getRepeatMode() {
                MediaSessionImplApi23 mediaSessionImplApi23 = this.mediaSessionImplRef.get();
                if (mediaSessionImplApi23 != null) {
                    return mediaSessionImplApi23.repeatMode;
                }
                return -1;
            }

            @Override // androidx.media3.session.legacy.IMediaSession
            public Bundle getSessionInfo() {
                MediaSessionImplApi23 mediaSessionImplApi23 = this.mediaSessionImplRef.get();
                if (mediaSessionImplApi23 == null || mediaSessionImplApi23.sessionInfo == null) {
                    return null;
                }
                return new Bundle(mediaSessionImplApi23.sessionInfo);
            }

            @Override // androidx.media3.session.legacy.IMediaSession
            public int getShuffleMode() {
                MediaSessionImplApi23 mediaSessionImplApi23 = this.mediaSessionImplRef.get();
                if (mediaSessionImplApi23 != null) {
                    return mediaSessionImplApi23.shuffleMode;
                }
                return -1;
            }

            @Override // androidx.media3.session.legacy.IMediaSession
            public boolean isCaptioningEnabled() {
                MediaSessionImplApi23 mediaSessionImplApi23 = this.mediaSessionImplRef.get();
                return mediaSessionImplApi23 != null && mediaSessionImplApi23.captioningEnabled;
            }

            @Override // androidx.media3.session.legacy.IMediaSession
            public void registerCallbackListener(IMediaControllerCallback iMediaControllerCallback) {
                MediaSessionImplApi23 mediaSessionImplApi23 = this.mediaSessionImplRef.get();
                if (mediaSessionImplApi23 == null || iMediaControllerCallback == null) {
                    return;
                }
                int callingPid = Binder.getCallingPid();
                int callingUid = Binder.getCallingUid();
                mediaSessionImplApi23.extraControllerCallbacks.register(iMediaControllerCallback, new MediaSessionManager.RemoteUserInfo("android.media.session.MediaController", callingPid, callingUid));
                synchronized (mediaSessionImplApi23.lock) {
                    try {
                        RegistrationCallbackHandler registrationCallbackHandler = mediaSessionImplApi23.registrationCallbackHandler;
                        if (registrationCallbackHandler != null) {
                            registrationCallbackHandler.postCallbackRegistered(callingPid, callingUid);
                        }
                    } catch (Throwable th) {
                        throw th;
                    }
                }
            }

            public void release() {
                this.mediaSessionImplRef.clear();
            }

            @Override // androidx.media3.session.legacy.IMediaSession
            public void unregisterCallbackListener(IMediaControllerCallback iMediaControllerCallback) {
                MediaSessionImplApi23 mediaSessionImplApi23 = this.mediaSessionImplRef.get();
                if (mediaSessionImplApi23 == null || iMediaControllerCallback == null) {
                    return;
                }
                mediaSessionImplApi23.extraControllerCallbacks.unregister(iMediaControllerCallback);
                int callingPid = Binder.getCallingPid();
                int callingUid = Binder.getCallingUid();
                synchronized (mediaSessionImplApi23.lock) {
                    try {
                        RegistrationCallbackHandler registrationCallbackHandler = mediaSessionImplApi23.registrationCallbackHandler;
                        if (registrationCallbackHandler != null) {
                            registrationCallbackHandler.postCallbackUnregistered(callingPid, callingUid);
                        }
                    } catch (Throwable th) {
                        throw th;
                    }
                }
            }
        }

        public MediaSessionImplApi23(Context context, String str, Bundle bundle) {
            MediaSession mediaSessionCreateFwkMediaSession = createFwkMediaSession(context, str, bundle);
            this.sessionFwk = mediaSessionCreateFwkMediaSession;
            ExtraSession extraSession = new ExtraSession(this);
            this.extraSession = extraSession;
            this.token = new Token(mediaSessionCreateFwkMediaSession.getSessionToken(), extraSession);
            this.sessionInfo = bundle;
            setFlags(3);
        }

        public MediaSession createFwkMediaSession(Context context, String str, Bundle bundle) {
            return new MediaSession(context, str);
        }

        @Override // androidx.media3.session.legacy.MediaSessionCompat.MediaSessionImpl
        public Callback getCallback() {
            Callback callback;
            synchronized (this.lock) {
                callback = this.callback;
            }
            return callback;
        }

        @Override // androidx.media3.session.legacy.MediaSessionCompat.MediaSessionImpl
        public String getCallingPackage() {
            if (Build.VERSION.SDK_INT < 24) {
                return null;
            }
            try {
                return (String) this.sessionFwk.getClass().getMethod("getCallingPackage", null).invoke(this.sessionFwk, null);
            } catch (Exception e5) {
                Log.e(MediaSessionCompat.TAG, "Cannot execute MediaSession.getCallingPackage()", e5);
                return null;
            }
        }

        @Override // androidx.media3.session.legacy.MediaSessionCompat.MediaSessionImpl
        public MediaSessionManager.RemoteUserInfo getCurrentControllerInfo() {
            MediaSessionManager.RemoteUserInfo remoteUserInfo;
            synchronized (this.lock) {
                remoteUserInfo = this.remoteUserInfo;
            }
            return remoteUserInfo;
        }

        @Override // androidx.media3.session.legacy.MediaSessionCompat.MediaSessionImpl
        public Object getMediaSession() {
            return this.sessionFwk;
        }

        @Override // androidx.media3.session.legacy.MediaSessionCompat.MediaSessionImpl
        public PlaybackStateCompat getPlaybackState() {
            return this.playbackState;
        }

        @Override // androidx.media3.session.legacy.MediaSessionCompat.MediaSessionImpl
        public Token getSessionToken() {
            return this.token;
        }

        @Override // androidx.media3.session.legacy.MediaSessionCompat.MediaSessionImpl
        public boolean isActive() {
            return this.sessionFwk.isActive();
        }

        @Override // androidx.media3.session.legacy.MediaSessionCompat.MediaSessionImpl
        public void release() {
            this.destroyed = true;
            this.extraControllerCallbacks.kill();
            if (Build.VERSION.SDK_INT == 27) {
                try {
                    Field declaredField = this.sessionFwk.getClass().getDeclaredField("mCallback");
                    declaredField.setAccessible(true);
                    Handler handler = (Handler) declaredField.get(this.sessionFwk);
                    if (handler != null) {
                        handler.removeCallbacksAndMessages(null);
                    }
                } catch (Exception e5) {
                    Log.w(MediaSessionCompat.TAG, "Exception happened while accessing MediaSession.mCallback.", e5);
                }
            }
            this.sessionFwk.setCallback(null);
            this.extraSession.release();
            this.sessionFwk.release();
        }

        @Override // androidx.media3.session.legacy.MediaSessionCompat.MediaSessionImpl
        public void sendSessionEvent(String str, Bundle bundle) {
            this.sessionFwk.sendSessionEvent(str, bundle);
        }

        @Override // androidx.media3.session.legacy.MediaSessionCompat.MediaSessionImpl
        public void setActive(boolean z) {
            this.sessionFwk.setActive(z);
        }

        @Override // androidx.media3.session.legacy.MediaSessionCompat.MediaSessionImpl
        public void setCallback(Callback callback, Handler handler) {
            synchronized (this.lock) {
                try {
                    this.callback = callback;
                    this.sessionFwk.setCallback(callback == null ? null : callback.callbackFwk, handler);
                    if (callback != null) {
                        callback.setSessionImpl(this, handler);
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
        }

        @Override // androidx.media3.session.legacy.MediaSessionCompat.MediaSessionImpl
        public void setCurrentControllerInfo(MediaSessionManager.RemoteUserInfo remoteUserInfo) {
            synchronized (this.lock) {
                this.remoteUserInfo = remoteUserInfo;
            }
        }

        @Override // androidx.media3.session.legacy.MediaSessionCompat.MediaSessionImpl
        public void setExtras(Bundle bundle) {
            this.sessionFwk.setExtras(bundle);
        }

        @Override // androidx.media3.session.legacy.MediaSessionCompat.MediaSessionImpl
        public void setFlags(int i10) {
            this.sessionFwk.setFlags(i10 | 3);
        }

        @Override // androidx.media3.session.legacy.MediaSessionCompat.MediaSessionImpl
        public void setMediaButtonReceiver(PendingIntent pendingIntent) {
            this.sessionFwk.setMediaButtonReceiver(pendingIntent);
        }

        @Override // androidx.media3.session.legacy.MediaSessionCompat.MediaSessionImpl
        public void setMetadata(MediaMetadataCompat mediaMetadataCompat) {
            this.metadata = mediaMetadataCompat;
            this.sessionFwk.setMetadata(mediaMetadataCompat == null ? null : mediaMetadataCompat.getMediaMetadata());
        }

        @Override // androidx.media3.session.legacy.MediaSessionCompat.MediaSessionImpl
        public void setPlaybackState(PlaybackStateCompat playbackStateCompat) {
            this.playbackState = playbackStateCompat;
            synchronized (this.lock) {
                for (int iBeginBroadcast = this.extraControllerCallbacks.beginBroadcast() - 1; iBeginBroadcast >= 0; iBeginBroadcast--) {
                    try {
                        ((IMediaControllerCallback) this.extraControllerCallbacks.getBroadcastItem(iBeginBroadcast)).onPlaybackStateChanged(playbackStateCompat);
                    } catch (RemoteException | SecurityException e5) {
                        Log.e(MediaSessionCompat.TAG, "Dead object in setPlaybackState.", e5);
                    }
                }
                this.extraControllerCallbacks.finishBroadcast();
            }
            this.sessionFwk.setPlaybackState(playbackStateCompat.getPlaybackState());
        }

        @Override // androidx.media3.session.legacy.MediaSessionCompat.MediaSessionImpl
        public void setPlaybackToLocal(int i10) {
            AudioAttributes.Builder builder = new AudioAttributes.Builder();
            builder.setLegacyStreamType(i10);
            this.sessionFwk.setPlaybackToLocal(builder.build());
        }

        @Override // androidx.media3.session.legacy.MediaSessionCompat.MediaSessionImpl
        public void setPlaybackToRemote(VolumeProviderCompat volumeProviderCompat) {
            this.sessionFwk.setPlaybackToRemote((VolumeProvider) volumeProviderCompat.getVolumeProvider());
        }

        @Override // androidx.media3.session.legacy.MediaSessionCompat.MediaSessionImpl
        public void setQueue(List<QueueItem> list) {
            this.queue = list;
            if (list == null) {
                this.sessionFwk.setQueue(null);
                return;
            }
            ArrayList arrayList = new ArrayList(list.size());
            Iterator<QueueItem> it = list.iterator();
            while (it.hasNext()) {
                arrayList.add(it.next().getQueueItem());
            }
            this.sessionFwk.setQueue(arrayList);
        }

        @Override // androidx.media3.session.legacy.MediaSessionCompat.MediaSessionImpl
        public void setQueueTitle(CharSequence charSequence) {
            this.sessionFwk.setQueueTitle(charSequence);
        }

        @Override // androidx.media3.session.legacy.MediaSessionCompat.MediaSessionImpl
        public void setRatingType(int i10) {
            this.sessionFwk.setRatingType(i10);
        }

        @Override // androidx.media3.session.legacy.MediaSessionCompat.MediaSessionImpl
        public void setRepeatMode(int i10) {
            if (this.repeatMode != i10) {
                this.repeatMode = i10;
                synchronized (this.lock) {
                    for (int iBeginBroadcast = this.extraControllerCallbacks.beginBroadcast() - 1; iBeginBroadcast >= 0; iBeginBroadcast--) {
                        try {
                            ((IMediaControllerCallback) this.extraControllerCallbacks.getBroadcastItem(iBeginBroadcast)).onRepeatModeChanged(i10);
                        } catch (RemoteException | SecurityException e5) {
                            Log.e(MediaSessionCompat.TAG, "Dead object in setRepeatMode.", e5);
                        }
                    }
                    this.extraControllerCallbacks.finishBroadcast();
                }
            }
        }

        @Override // androidx.media3.session.legacy.MediaSessionCompat.MediaSessionImpl
        public void setSessionActivity(PendingIntent pendingIntent) {
            this.sessionFwk.setSessionActivity(pendingIntent);
        }

        @Override // androidx.media3.session.legacy.MediaSessionCompat.MediaSessionImpl
        public void setShuffleMode(int i10) {
            if (this.shuffleMode != i10) {
                this.shuffleMode = i10;
                synchronized (this.lock) {
                    for (int iBeginBroadcast = this.extraControllerCallbacks.beginBroadcast() - 1; iBeginBroadcast >= 0; iBeginBroadcast--) {
                        try {
                            ((IMediaControllerCallback) this.extraControllerCallbacks.getBroadcastItem(iBeginBroadcast)).onShuffleModeChanged(i10);
                        } catch (RemoteException | SecurityException e5) {
                            Log.e(MediaSessionCompat.TAG, "Dead object in setShuffleMode.", e5);
                        }
                    }
                    this.extraControllerCallbacks.finishBroadcast();
                }
            }
        }
    }

    public static class MediaSessionImplApi28 extends MediaSessionImplApi23 {
        public MediaSessionImplApi28(Context context, String str, Bundle bundle) {
            super(context, str, bundle);
        }

        @Override // androidx.media3.session.legacy.MediaSessionCompat.MediaSessionImplApi23, androidx.media3.session.legacy.MediaSessionCompat.MediaSessionImpl
        public final MediaSessionManager.RemoteUserInfo getCurrentControllerInfo() {
            return new MediaSessionManager.RemoteUserInfo(this.sessionFwk.getCurrentControllerInfo());
        }

        @Override // androidx.media3.session.legacy.MediaSessionCompat.MediaSessionImplApi23, androidx.media3.session.legacy.MediaSessionCompat.MediaSessionImpl
        public void setCurrentControllerInfo(MediaSessionManager.RemoteUserInfo remoteUserInfo) {
        }
    }

    public static class MediaSessionImplApi29 extends MediaSessionImplApi28 {
        public MediaSessionImplApi29(Context context, String str, Bundle bundle) {
            super(context, str, bundle);
        }

        @Override // androidx.media3.session.legacy.MediaSessionCompat.MediaSessionImplApi23
        public MediaSession createFwkMediaSession(Context context, String str, Bundle bundle) {
            return q.d(context, str, bundle);
        }
    }

    public static final class QueueItem implements Parcelable {
        public static final Parcelable.Creator<QueueItem> CREATOR = new Parcelable.Creator<QueueItem>() { // from class: androidx.media3.session.legacy.MediaSessionCompat.QueueItem.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public QueueItem createFromParcel(Parcel parcel) {
                return new QueueItem(parcel);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public QueueItem[] newArray(int i10) {
                return new QueueItem[i10];
            }
        };
        public static final int UNKNOWN_ID = -1;
        private final MediaDescriptionCompat description;
        private final long id;
        private MediaSession.QueueItem itemFwk;

        public QueueItem(MediaDescriptionCompat mediaDescriptionCompat, long j10) {
            this(null, mediaDescriptionCompat, j10);
        }

        public static QueueItem fromQueueItem(MediaSession.QueueItem queueItem) {
            return new QueueItem(queueItem, MediaDescriptionCompat.fromMediaDescription(queueItem.getDescription()), queueItem.getQueueId());
        }

        public static List<QueueItem> fromQueueItemList(List<MediaSession.QueueItem> list) {
            if (list == null) {
                return null;
            }
            ArrayList arrayList = new ArrayList(list.size());
            Iterator<MediaSession.QueueItem> it = list.iterator();
            while (it.hasNext()) {
                arrayList.add(fromQueueItem(it.next()));
            }
            return arrayList;
        }

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        public MediaDescriptionCompat getDescription() {
            return this.description;
        }

        public long getQueueId() {
            return this.id;
        }

        public MediaSession.QueueItem getQueueItem() {
            MediaSession.QueueItem queueItem = this.itemFwk;
            if (queueItem != null) {
                return queueItem;
            }
            MediaSession.QueueItem queueItem2 = new MediaSession.QueueItem(this.description.getMediaDescription(), this.id);
            this.itemFwk = queueItem2;
            return queueItem2;
        }

        public String toString() {
            StringBuilder sb2 = new StringBuilder("MediaSession.QueueItem { Description=");
            sb2.append(this.description);
            sb2.append(", Id=");
            return a0.c.j(this.id, " }", sb2);
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i10) {
            this.description.writeToParcel(parcel, i10);
            parcel.writeLong(this.id);
        }

        private QueueItem(MediaSession.QueueItem queueItem, MediaDescriptionCompat mediaDescriptionCompat, long j10) {
            if (j10 == -1) {
                throw new IllegalArgumentException("Id cannot be QueueItem.UNKNOWN_ID");
            }
            this.description = mediaDescriptionCompat;
            this.id = j10;
            this.itemFwk = queueItem;
        }

        public QueueItem(Parcel parcel) {
            this.description = MediaDescriptionCompat.CREATOR.createFromParcel(parcel);
            this.id = parcel.readLong();
        }
    }

    public interface RegistrationCallback {
        void onCallbackRegistered(int i10, int i11);

        void onCallbackUnregistered(int i10, int i11);
    }

    public static final class RegistrationCallbackHandler extends Handler {
        private static final int MSG_CALLBACK_REGISTERED = 1001;
        private static final int MSG_CALLBACK_UNREGISTERED = 1002;
        private final RegistrationCallback callback;

        public RegistrationCallbackHandler(Looper looper, RegistrationCallback registrationCallback) {
            super(looper);
            this.callback = registrationCallback;
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            super.handleMessage(message);
            int i10 = message.what;
            if (i10 == 1001) {
                this.callback.onCallbackRegistered(message.arg1, message.arg2);
            } else {
                if (i10 != 1002) {
                    return;
                }
                this.callback.onCallbackUnregistered(message.arg1, message.arg2);
            }
        }

        public void postCallbackRegistered(int i10, int i11) {
            obtainMessage(1001, i10, i11).sendToTarget();
        }

        public void postCallbackUnregistered(int i10, int i11) {
            obtainMessage(1002, i10, i11).sendToTarget();
        }
    }

    public static final class ResultReceiverWrapper implements Parcelable {
        public static final Parcelable.Creator<ResultReceiverWrapper> CREATOR = new Parcelable.Creator<ResultReceiverWrapper>() { // from class: androidx.media3.session.legacy.MediaSessionCompat.ResultReceiverWrapper.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public ResultReceiverWrapper createFromParcel(Parcel parcel) {
                return new ResultReceiverWrapper(parcel);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public ResultReceiverWrapper[] newArray(int i10) {
                return new ResultReceiverWrapper[i10];
            }
        };
        ResultReceiver resultReceiver;

        public ResultReceiverWrapper(Parcel parcel) {
            this.resultReceiver = (ResultReceiver) ResultReceiver.CREATOR.createFromParcel(parcel);
        }

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i10) {
            this.resultReceiver.writeToParcel(parcel, i10);
        }
    }

    public static final class Token implements Parcelable {
        public static final Parcelable.Creator<Token> CREATOR = new Parcelable.Creator<Token>() { // from class: androidx.media3.session.legacy.MediaSessionCompat.Token.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public Token createFromParcel(Parcel parcel) {
                MediaSession.Token token = (MediaSession.Token) parcel.readParcelable(null);
                token.getClass();
                return new Token(token);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public Token[] newArray(int i10) {
                return new Token[i10];
            }
        };
        private IMediaSession extraBinder;
        private final MediaSession.Token inner;
        private final Object lock;
        private f session2Token;

        public Token(MediaSession.Token token) {
            this(token, null);
        }

        public static Token fromBundle(Bundle bundle) {
            if (bundle == null) {
                return null;
            }
            MediaSessionCompat.ensureClassLoader(bundle);
            IMediaSession iMediaSessionAsInterface = IMediaSession.Stub.asInterface(bundle.getBinder(MediaSessionCompat.KEY_EXTRA_BINDER));
            f fVarS = c.s(bundle);
            Token token = (Token) LegacyParcelableUtil.convert(bundle.getParcelable(MediaSessionCompat.KEY_TOKEN), CREATOR);
            if (token == null) {
                return null;
            }
            return new Token(token.inner, iMediaSessionAsInterface, fVarS);
        }

        public static Token fromToken(MediaSession.Token token) {
            return fromToken(token, null);
        }

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj instanceof Token) {
                return this.inner.equals(((Token) obj).inner);
            }
            return false;
        }

        public IMediaSession getExtraBinder() {
            IMediaSession iMediaSession;
            synchronized (this.lock) {
                iMediaSession = this.extraBinder;
            }
            return iMediaSession;
        }

        public f getSession2Token() {
            f fVar;
            synchronized (this.lock) {
                fVar = this.session2Token;
            }
            return fVar;
        }

        public MediaSession.Token getToken() {
            return this.inner;
        }

        public int hashCode() {
            return this.inner.hashCode();
        }

        public void setExtraBinder(IMediaSession iMediaSession) {
            synchronized (this.lock) {
                this.extraBinder = iMediaSession;
            }
        }

        public void setSession2Token(f fVar) {
            synchronized (this.lock) {
                this.session2Token = fVar;
            }
        }

        public Bundle toBundle() {
            Bundle bundle = new Bundle();
            bundle.putParcelable(MediaSessionCompat.KEY_TOKEN, LegacyParcelableUtil.convert(this, android.support.v4.media.session.MediaSessionCompat$Token.CREATOR));
            synchronized (this.lock) {
                try {
                    IMediaSession iMediaSession = this.extraBinder;
                    if (iMediaSession != null) {
                        bundle.putBinder(MediaSessionCompat.KEY_EXTRA_BINDER, iMediaSession.asBinder());
                    }
                    f fVar = this.session2Token;
                    if (fVar != null) {
                        c.y(bundle, fVar);
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
            return bundle;
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i10) {
            parcel.writeParcelable(this.inner, i10);
        }

        public Token(MediaSession.Token token, IMediaSession iMediaSession) {
            this(token, iMediaSession, null);
        }

        public static Token fromToken(MediaSession.Token token, IMediaSession iMediaSession) {
            return new Token(token, iMediaSession);
        }

        public Token(MediaSession.Token token, IMediaSession iMediaSession, f fVar) {
            this.lock = new Object();
            this.inner = token;
            this.extraBinder = iMediaSession;
            this.session2Token = fVar;
        }
    }

    public MediaSessionCompat(Context context, String str, ComponentName componentName, PendingIntent pendingIntent, Bundle bundle) {
        if (TextUtils.isEmpty(str)) {
            throw new IllegalArgumentException("tag must not be null or empty");
        }
        if (componentName == null && (componentName = MediaButtonReceiver.getMediaButtonReceiverComponent(context)) == null) {
            Log.i(TAG, "Couldn't find a unique registered media button receiver in the given context.");
        }
        if (componentName != null && pendingIntent == null) {
            Intent intent = new Intent("android.intent.action.MEDIA_BUTTON");
            intent.setComponent(componentName);
            pendingIntent = PendingIntent.getBroadcast(context, 0, intent, Build.VERSION.SDK_INT >= 31 ? 33554432 : 0);
        }
        int i10 = Build.VERSION.SDK_INT;
        if (i10 >= 29) {
            this.impl = new MediaSessionImplApi29(context, str, bundle);
        } else if (i10 >= 28) {
            this.impl = new MediaSessionImplApi28(context, str, bundle);
        } else {
            this.impl = new MediaSessionImplApi23(context, str, bundle);
        }
        Looper looperMyLooper = Looper.myLooper();
        setCallback(new Callback() { // from class: androidx.media3.session.legacy.MediaSessionCompat.1
        }, new Handler(looperMyLooper == null ? Looper.getMainLooper() : looperMyLooper));
        this.impl.setMediaButtonReceiver(pendingIntent);
        this.controller = new MediaControllerCompat(context, this);
    }

    public static void ensureClassLoader(Bundle bundle) {
        if (bundle != null) {
            ClassLoader classLoader = MediaSessionCompat.class.getClassLoader();
            classLoader.getClass();
            bundle.setClassLoader(classLoader);
        }
    }

    public static PlaybackStateCompat getStateWithUpdatedPosition(PlaybackStateCompat playbackStateCompat, MediaMetadataCompat mediaMetadataCompat) {
        if (playbackStateCompat == null) {
            return playbackStateCompat;
        }
        long j10 = -1;
        if (playbackStateCompat.getPosition() == -1) {
            return playbackStateCompat;
        }
        if (playbackStateCompat.getState() != 3 && playbackStateCompat.getState() != 4 && playbackStateCompat.getState() != 5) {
            return playbackStateCompat;
        }
        if (playbackStateCompat.getLastPositionUpdateTime() <= 0) {
            return playbackStateCompat;
        }
        long jElapsedRealtime = SystemClock.elapsedRealtime();
        long position = playbackStateCompat.getPosition() + ((long) (playbackStateCompat.getPlaybackSpeed() * (jElapsedRealtime - r0)));
        if (mediaMetadataCompat != null && mediaMetadataCompat.containsKey(MediaMetadataCompat.METADATA_KEY_DURATION)) {
            j10 = mediaMetadataCompat.getLong(MediaMetadataCompat.METADATA_KEY_DURATION);
        }
        return new PlaybackStateCompat.Builder(playbackStateCompat).setState(playbackStateCompat.getState(), (j10 < 0 || position <= j10) ? position < 0 ? 0L : position : j10, playbackStateCompat.getPlaybackSpeed(), jElapsedRealtime).build();
    }

    public static Bundle unparcelWithClassLoader(Bundle bundle) {
        if (bundle == null) {
            return null;
        }
        ensureClassLoader(bundle);
        try {
            bundle.isEmpty();
            return bundle;
        } catch (BadParcelableException unused) {
            Log.e(TAG, "Could not unparcel the data.");
            return null;
        }
    }

    public MediaControllerCompat getController() {
        return this.controller;
    }

    public final MediaSessionManager.RemoteUserInfo getCurrentControllerInfo() {
        return this.impl.getCurrentControllerInfo();
    }

    public Object getMediaSession() {
        return this.impl.getMediaSession();
    }

    public Token getSessionToken() {
        return this.impl.getSessionToken();
    }

    public boolean isActive() {
        return this.impl.isActive();
    }

    public void release() {
        this.impl.release();
    }

    public void sendSessionEvent(String str, Bundle bundle) {
        if (TextUtils.isEmpty(str)) {
            throw new IllegalArgumentException("event cannot be null or empty");
        }
        this.impl.sendSessionEvent(str, bundle);
    }

    public void setActive(boolean z) {
        this.impl.setActive(z);
    }

    public void setCallback(Callback callback, Handler handler) {
        this.impl.setCallback(callback, handler);
    }

    public void setExtras(Bundle bundle) {
        this.impl.setExtras(bundle);
    }

    public void setFlags(int i10) {
        this.impl.setFlags(i10);
    }

    public void setMediaButtonReceiver(PendingIntent pendingIntent) {
        this.impl.setMediaButtonReceiver(pendingIntent);
    }

    public void setMetadata(MediaMetadataCompat mediaMetadataCompat) {
        this.impl.setMetadata(mediaMetadataCompat);
    }

    public void setPlaybackState(PlaybackStateCompat playbackStateCompat) {
        this.impl.setPlaybackState(playbackStateCompat);
    }

    public void setPlaybackToLocal(int i10) {
        this.impl.setPlaybackToLocal(i10);
    }

    public void setPlaybackToRemote(VolumeProviderCompat volumeProviderCompat) {
        this.impl.setPlaybackToRemote(volumeProviderCompat);
    }

    public void setQueue(List<QueueItem> list) {
        if (list != null) {
            HashSet hashSet = new HashSet();
            for (QueueItem queueItem : list) {
                if (hashSet.contains(Long.valueOf(queueItem.getQueueId()))) {
                    Log.e(TAG, "Found duplicate queue id: " + queueItem.getQueueId(), new IllegalArgumentException("id of each queue item should be unique"));
                }
                hashSet.add(Long.valueOf(queueItem.getQueueId()));
            }
        }
        this.impl.setQueue(list);
    }

    public void setQueueTitle(CharSequence charSequence) {
        this.impl.setQueueTitle(charSequence);
    }

    public void setRatingType(int i10) {
        this.impl.setRatingType(i10);
    }

    public void setRepeatMode(int i10) {
        this.impl.setRepeatMode(i10);
    }

    public void setSessionActivity(PendingIntent pendingIntent) {
        this.impl.setSessionActivity(pendingIntent);
    }

    public void setShuffleMode(int i10) {
        this.impl.setShuffleMode(i10);
    }
}
