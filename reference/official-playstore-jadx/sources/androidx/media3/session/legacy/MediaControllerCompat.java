package androidx.media3.session.legacy;

import a0.c;
import ac.b;
import android.app.PendingIntent;
import android.content.Context;
import android.media.MediaMetadata;
import android.media.Rating;
import android.media.session.MediaController;
import android.media.session.MediaSession;
import android.media.session.PlaybackState;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.RemoteException;
import android.os.ResultReceiver;
import android.text.TextUtils;
import android.view.KeyEvent;
import androidx.media3.common.AudioAttributes;
import androidx.media3.common.util.Log;
import androidx.media3.session.legacy.IMediaControllerCallback;
import androidx.media3.session.legacy.IMediaSession;
import androidx.media3.session.legacy.MediaSessionCompat;
import androidx.media3.session.legacy.PlaybackStateCompat;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/* JADX INFO: loaded from: classes3.dex */
public final class MediaControllerCompat {
    public static final String COMMAND_ADD_QUEUE_ITEM = "android.support.v4.media.session.command.ADD_QUEUE_ITEM";
    public static final String COMMAND_ADD_QUEUE_ITEM_AT = "android.support.v4.media.session.command.ADD_QUEUE_ITEM_AT";
    public static final String COMMAND_ARGUMENT_INDEX = "android.support.v4.media.session.command.ARGUMENT_INDEX";
    public static final String COMMAND_ARGUMENT_MEDIA_DESCRIPTION = "android.support.v4.media.session.command.ARGUMENT_MEDIA_DESCRIPTION";
    public static final String COMMAND_GET_EXTRA_BINDER = "android.support.v4.media.session.command.GET_EXTRA_BINDER";
    public static final String COMMAND_REMOVE_QUEUE_ITEM = "android.support.v4.media.session.command.REMOVE_QUEUE_ITEM";
    public static final String COMMAND_REMOVE_QUEUE_ITEM_AT = "android.support.v4.media.session.command.REMOVE_QUEUE_ITEM_AT";
    static final String TAG = "MediaControllerCompat";
    private final MediaControllerImpl impl;
    private final Set<Callback> registeredCallbacks;
    private final MediaSessionCompat.Token token;

    public static abstract class Callback implements IBinder.DeathRecipient {
        final MediaController.Callback callbackFwk = new MediaControllerCallback(this);
        MessageHandler handler;
        IMediaControllerCallback iControllerCallback;

        public static class CallbackStub extends IMediaControllerCallback.Stub {
            private final WeakReference<Callback> callback;

            public CallbackStub(Callback callback) {
                this.callback = new WeakReference<>(callback);
            }

            @Override // androidx.media3.session.legacy.IMediaControllerCallback
            public void onCaptioningEnabledChanged(boolean z) {
                Callback callback = this.callback.get();
                if (callback != null) {
                    callback.postToHandler(11, Boolean.valueOf(z), null);
                }
            }

            @Override // androidx.media3.session.legacy.IMediaControllerCallback
            public void onPlaybackStateChanged(PlaybackStateCompat playbackStateCompat) {
                Callback callback = this.callback.get();
                if (callback != null) {
                    callback.postToHandler(2, playbackStateCompat, null);
                }
            }

            @Override // androidx.media3.session.legacy.IMediaControllerCallback
            public void onRepeatModeChanged(int i10) {
                Callback callback = this.callback.get();
                if (callback != null) {
                    callback.postToHandler(9, Integer.valueOf(i10), null);
                }
            }

            @Override // androidx.media3.session.legacy.IMediaControllerCallback
            public void onSessionReady() {
                Callback callback = this.callback.get();
                if (callback != null) {
                    callback.postToHandler(13, null, null);
                }
            }

            @Override // androidx.media3.session.legacy.IMediaControllerCallback
            public void onShuffleModeChanged(int i10) {
                Callback callback = this.callback.get();
                if (callback != null) {
                    callback.postToHandler(12, Integer.valueOf(i10), null);
                }
            }
        }

        public static class MediaControllerCallback extends MediaController.Callback {
            private final WeakReference<Callback> callback;

            public MediaControllerCallback(Callback callback) {
                this.callback = new WeakReference<>(callback);
            }

            @Override // android.media.session.MediaController.Callback
            public void onAudioInfoChanged(MediaController.PlaybackInfo playbackInfo) {
                Callback callback = this.callback.get();
                if (callback == null || playbackInfo == null) {
                    return;
                }
                int playbackType = playbackInfo.getPlaybackType();
                String volumeControlId = Build.VERSION.SDK_INT >= 30 ? playbackInfo.getVolumeControlId() : null;
                boolean z = true;
                if (playbackType == 1 && volumeControlId != null) {
                    z = false;
                }
                b.j(z);
                callback.onAudioInfoChanged(new PlaybackInfo(playbackType, AudioAttributes.fromPlatformAudioAttributes(playbackInfo.getAudioAttributes()), playbackInfo.getVolumeControl(), playbackInfo.getMaxVolume(), playbackInfo.getCurrentVolume(), volumeControlId));
            }

            @Override // android.media.session.MediaController.Callback
            public void onExtrasChanged(Bundle bundle) {
                MediaSessionCompat.ensureClassLoader(bundle);
                Callback callback = this.callback.get();
                if (callback != null) {
                    callback.onExtrasChanged(bundle);
                }
            }

            @Override // android.media.session.MediaController.Callback
            public void onMetadataChanged(MediaMetadata mediaMetadata) {
                Callback callback = this.callback.get();
                if (callback != null) {
                    callback.onMetadataChanged(MediaMetadataCompat.fromMediaMetadata(mediaMetadata));
                }
            }

            @Override // android.media.session.MediaController.Callback
            public void onPlaybackStateChanged(PlaybackState playbackState) {
                Callback callback = this.callback.get();
                if (callback == null || callback.iControllerCallback != null) {
                    return;
                }
                callback.onPlaybackStateChanged(PlaybackStateCompat.fromPlaybackState(playbackState));
            }

            @Override // android.media.session.MediaController.Callback
            public void onQueueChanged(List<MediaSession.QueueItem> list) {
                Callback callback = this.callback.get();
                if (callback != null) {
                    callback.onQueueChanged(MediaSessionCompat.QueueItem.fromQueueItemList(list));
                }
            }

            @Override // android.media.session.MediaController.Callback
            public void onQueueTitleChanged(CharSequence charSequence) {
                Callback callback = this.callback.get();
                if (callback != null) {
                    callback.onQueueTitleChanged(charSequence);
                }
            }

            @Override // android.media.session.MediaController.Callback
            public void onSessionDestroyed() {
                Callback callback = this.callback.get();
                if (callback != null) {
                    callback.onSessionDestroyed();
                }
            }

            @Override // android.media.session.MediaController.Callback
            public void onSessionEvent(String str, Bundle bundle) {
                MediaSessionCompat.ensureClassLoader(bundle);
                Callback callback = this.callback.get();
                if (callback != null) {
                    callback.onSessionEvent(str, bundle);
                }
            }
        }

        public class MessageHandler extends Handler {
            private static final int MSG_DESTROYED = 8;
            private static final int MSG_SESSION_READY = 13;
            private static final int MSG_UPDATE_CAPTIONING_ENABLED = 11;
            private static final int MSG_UPDATE_PLAYBACK_STATE = 2;
            private static final int MSG_UPDATE_REPEAT_MODE = 9;
            private static final int MSG_UPDATE_SHUFFLE_MODE = 12;
            boolean registered;

            public MessageHandler(Looper looper) {
                super(looper);
                this.registered = false;
            }

            @Override // android.os.Handler
            public void handleMessage(Message message) {
                if (this.registered) {
                    int i10 = message.what;
                    if (i10 == 2) {
                        Callback.this.onPlaybackStateChanged((PlaybackStateCompat) message.obj);
                        return;
                    }
                    if (i10 == 8) {
                        Callback.this.onSessionDestroyed();
                        return;
                    }
                    if (i10 == 9) {
                        Callback.this.onRepeatModeChanged(((Integer) message.obj).intValue());
                        return;
                    }
                    switch (i10) {
                        case 11:
                            Callback.this.onCaptioningEnabledChanged(((Boolean) message.obj).booleanValue());
                            break;
                        case 12:
                            Callback.this.onShuffleModeChanged(((Integer) message.obj).intValue());
                            break;
                        case 13:
                            Callback.this.onSessionReady();
                            break;
                    }
                }
            }
        }

        @Override // android.os.IBinder.DeathRecipient
        public void binderDied() {
            postToHandler(8, null, null);
        }

        public void onAudioInfoChanged(PlaybackInfo playbackInfo) {
        }

        public void onCaptioningEnabledChanged(boolean z) {
        }

        public void onExtrasChanged(Bundle bundle) {
        }

        public void onMetadataChanged(MediaMetadataCompat mediaMetadataCompat) {
        }

        public void onPlaybackStateChanged(PlaybackStateCompat playbackStateCompat) {
        }

        public void onQueueChanged(List<MediaSessionCompat.QueueItem> list) {
        }

        public void onQueueTitleChanged(CharSequence charSequence) {
        }

        public void onRepeatModeChanged(int i10) {
        }

        public void onSessionDestroyed() {
        }

        public void onSessionEvent(String str, Bundle bundle) {
        }

        public void onSessionReady() {
        }

        public void onShuffleModeChanged(int i10) {
        }

        public void postToHandler(int i10, Object obj, Bundle bundle) {
            MessageHandler messageHandler = this.handler;
            if (messageHandler != null) {
                Message messageObtainMessage = messageHandler.obtainMessage(i10, obj);
                if (bundle != null) {
                    messageObtainMessage.setData(bundle);
                }
                messageObtainMessage.sendToTarget();
            }
        }

        public void setHandler(Handler handler) {
            if (handler != null) {
                MessageHandler messageHandler = new MessageHandler(handler.getLooper());
                this.handler = messageHandler;
                messageHandler.registered = true;
            } else {
                MessageHandler messageHandler2 = this.handler;
                if (messageHandler2 != null) {
                    messageHandler2.registered = false;
                    messageHandler2.removeCallbacksAndMessages(null);
                    this.handler = null;
                }
            }
        }
    }

    public interface MediaControllerImpl {
        void addQueueItem(MediaDescriptionCompat mediaDescriptionCompat);

        void addQueueItem(MediaDescriptionCompat mediaDescriptionCompat, int i10);

        void adjustVolume(int i10, int i11);

        boolean dispatchMediaButtonEvent(KeyEvent keyEvent);

        Bundle getExtras();

        long getFlags();

        Object getMediaController();

        MediaMetadataCompat getMetadata();

        String getPackageName();

        PlaybackInfo getPlaybackInfo();

        PlaybackStateCompat getPlaybackState();

        List<MediaSessionCompat.QueueItem> getQueue();

        CharSequence getQueueTitle();

        int getRatingType();

        int getRepeatMode();

        PendingIntent getSessionActivity();

        Bundle getSessionInfo();

        int getShuffleMode();

        TransportControls getTransportControls();

        boolean isCaptioningEnabled();

        boolean isSessionReady();

        void registerCallback(Callback callback, Handler handler);

        void removeQueueItem(MediaDescriptionCompat mediaDescriptionCompat);

        void sendCommand(String str, Bundle bundle, ResultReceiver resultReceiver);

        void setVolumeTo(int i10, int i11);

        void unregisterCallback(Callback callback);
    }

    public static class MediaControllerImplApi29 extends MediaControllerImplApi23 {
        public MediaControllerImplApi29(Context context, MediaSessionCompat.Token token) {
            super(context, token);
        }

        @Override // androidx.media3.session.legacy.MediaControllerCompat.MediaControllerImplApi23, androidx.media3.session.legacy.MediaControllerCompat.MediaControllerImpl
        public Bundle getSessionInfo() {
            if (this.sessionInfo != null) {
                return new Bundle(this.sessionInfo);
            }
            Bundle sessionInfo = this.controllerFwk.getSessionInfo();
            this.sessionInfo = sessionInfo;
            Bundle bundleUnparcelWithClassLoader = MediaSessionCompat.unparcelWithClassLoader(sessionInfo);
            this.sessionInfo = bundleUnparcelWithClassLoader;
            return bundleUnparcelWithClassLoader == null ? Bundle.EMPTY : new Bundle(this.sessionInfo);
        }
    }

    public static final class PlaybackInfo {
        public static final int PLAYBACK_TYPE_LOCAL = 1;
        public static final int PLAYBACK_TYPE_REMOTE = 2;
        private final AudioAttributes audioAttributes;
        private final int currentVolume;
        private final int maxVolume;
        private final int playbackType;
        private final int volumeControl;
        private final String volumeControlId;

        public PlaybackInfo(int i10, AudioAttributes audioAttributes, int i11, int i12, int i13, String str) {
            this.playbackType = i10;
            this.audioAttributes = audioAttributes;
            this.volumeControl = i11;
            this.maxVolume = i12;
            this.currentVolume = i13;
            this.volumeControlId = str;
        }

        public AudioAttributes getAudioAttributes() {
            return this.audioAttributes;
        }

        public int getCurrentVolume() {
            return this.currentVolume;
        }

        public int getMaxVolume() {
            return this.maxVolume;
        }

        public int getPlaybackType() {
            return this.playbackType;
        }

        public int getVolumeControl() {
            return this.volumeControl;
        }

        public String getVolumeControlId() {
            return this.volumeControlId;
        }
    }

    public static abstract class TransportControls {

        @Deprecated
        public static final String EXTRA_LEGACY_STREAM_TYPE = "android.media.session.extra.LEGACY_STREAM_TYPE";

        public abstract void fastForward();

        public abstract void pause();

        public abstract void play();

        public abstract void playFromMediaId(String str, Bundle bundle);

        public abstract void playFromSearch(String str, Bundle bundle);

        public abstract void playFromUri(Uri uri, Bundle bundle);

        public abstract void prepare();

        public abstract void prepareFromMediaId(String str, Bundle bundle);

        public abstract void prepareFromSearch(String str, Bundle bundle);

        public abstract void prepareFromUri(Uri uri, Bundle bundle);

        public abstract void rewind();

        public abstract void seekTo(long j10);

        public abstract void sendCustomAction(PlaybackStateCompat.CustomAction customAction, Bundle bundle);

        public abstract void sendCustomAction(String str, Bundle bundle);

        public void setPlaybackSpeed(float f10) {
        }

        public abstract void setRating(RatingCompat ratingCompat);

        public abstract void setRating(RatingCompat ratingCompat, Bundle bundle);

        public abstract void setRepeatMode(int i10);

        public abstract void setShuffleMode(int i10);

        public abstract void skipToNext();

        public abstract void skipToPrevious();

        public abstract void skipToQueueItem(long j10);

        public abstract void stop();
    }

    public static class TransportControlsApi23 extends TransportControls {
        protected final MediaController.TransportControls controlsFwk;

        public TransportControlsApi23(MediaController.TransportControls transportControls) {
            this.controlsFwk = transportControls;
        }

        @Override // androidx.media3.session.legacy.MediaControllerCompat.TransportControls
        public void fastForward() {
            this.controlsFwk.fastForward();
        }

        @Override // androidx.media3.session.legacy.MediaControllerCompat.TransportControls
        public void pause() {
            this.controlsFwk.pause();
        }

        @Override // androidx.media3.session.legacy.MediaControllerCompat.TransportControls
        public void play() {
            this.controlsFwk.play();
        }

        @Override // androidx.media3.session.legacy.MediaControllerCompat.TransportControls
        public void playFromMediaId(String str, Bundle bundle) {
            this.controlsFwk.playFromMediaId(str, bundle);
        }

        @Override // androidx.media3.session.legacy.MediaControllerCompat.TransportControls
        public void playFromSearch(String str, Bundle bundle) {
            this.controlsFwk.playFromSearch(str, bundle);
        }

        @Override // androidx.media3.session.legacy.MediaControllerCompat.TransportControls
        public void playFromUri(Uri uri, Bundle bundle) {
            this.controlsFwk.playFromUri(uri, bundle);
        }

        @Override // androidx.media3.session.legacy.MediaControllerCompat.TransportControls
        public void prepare() {
            sendCustomAction(MediaSessionCompat.ACTION_PREPARE, (Bundle) null);
        }

        @Override // androidx.media3.session.legacy.MediaControllerCompat.TransportControls
        public void prepareFromMediaId(String str, Bundle bundle) {
            Bundle bundle2 = new Bundle();
            bundle2.putString(MediaSessionCompat.ACTION_ARGUMENT_MEDIA_ID, str);
            bundle2.putBundle(MediaSessionCompat.ACTION_ARGUMENT_EXTRAS, bundle);
            sendCustomAction(MediaSessionCompat.ACTION_PREPARE_FROM_MEDIA_ID, bundle2);
        }

        @Override // androidx.media3.session.legacy.MediaControllerCompat.TransportControls
        public void prepareFromSearch(String str, Bundle bundle) {
            Bundle bundle2 = new Bundle();
            bundle2.putString(MediaSessionCompat.ACTION_ARGUMENT_QUERY, str);
            bundle2.putBundle(MediaSessionCompat.ACTION_ARGUMENT_EXTRAS, bundle);
            sendCustomAction(MediaSessionCompat.ACTION_PREPARE_FROM_SEARCH, bundle2);
        }

        @Override // androidx.media3.session.legacy.MediaControllerCompat.TransportControls
        public void prepareFromUri(Uri uri, Bundle bundle) {
            Bundle bundle2 = new Bundle();
            bundle2.putParcelable(MediaSessionCompat.ACTION_ARGUMENT_URI, uri);
            bundle2.putBundle(MediaSessionCompat.ACTION_ARGUMENT_EXTRAS, bundle);
            sendCustomAction(MediaSessionCompat.ACTION_PREPARE_FROM_URI, bundle2);
        }

        @Override // androidx.media3.session.legacy.MediaControllerCompat.TransportControls
        public void rewind() {
            this.controlsFwk.rewind();
        }

        @Override // androidx.media3.session.legacy.MediaControllerCompat.TransportControls
        public void seekTo(long j10) {
            this.controlsFwk.seekTo(j10);
        }

        @Override // androidx.media3.session.legacy.MediaControllerCompat.TransportControls
        public void sendCustomAction(PlaybackStateCompat.CustomAction customAction, Bundle bundle) {
            MediaControllerCompat.validateCustomAction(customAction.getAction(), bundle);
            this.controlsFwk.sendCustomAction(customAction.getAction(), bundle);
        }

        @Override // androidx.media3.session.legacy.MediaControllerCompat.TransportControls
        public void setPlaybackSpeed(float f10) {
            if (f10 == 0.0f) {
                throw new IllegalArgumentException("speed must not be zero");
            }
            Bundle bundle = new Bundle();
            bundle.putFloat(MediaSessionCompat.ACTION_ARGUMENT_PLAYBACK_SPEED, f10);
            sendCustomAction(MediaSessionCompat.ACTION_SET_PLAYBACK_SPEED, bundle);
        }

        @Override // androidx.media3.session.legacy.MediaControllerCompat.TransportControls
        public void setRating(RatingCompat ratingCompat) {
            this.controlsFwk.setRating((Rating) ratingCompat.getRating());
        }

        @Override // androidx.media3.session.legacy.MediaControllerCompat.TransportControls
        public void setRepeatMode(int i10) {
            Bundle bundle = new Bundle();
            bundle.putInt(MediaSessionCompat.ACTION_ARGUMENT_REPEAT_MODE, i10);
            sendCustomAction(MediaSessionCompat.ACTION_SET_REPEAT_MODE, bundle);
        }

        @Override // androidx.media3.session.legacy.MediaControllerCompat.TransportControls
        public void setShuffleMode(int i10) {
            Bundle bundle = new Bundle();
            bundle.putInt(MediaSessionCompat.ACTION_ARGUMENT_SHUFFLE_MODE, i10);
            sendCustomAction(MediaSessionCompat.ACTION_SET_SHUFFLE_MODE, bundle);
        }

        @Override // androidx.media3.session.legacy.MediaControllerCompat.TransportControls
        public void skipToNext() {
            this.controlsFwk.skipToNext();
        }

        @Override // androidx.media3.session.legacy.MediaControllerCompat.TransportControls
        public void skipToPrevious() {
            this.controlsFwk.skipToPrevious();
        }

        @Override // androidx.media3.session.legacy.MediaControllerCompat.TransportControls
        public void skipToQueueItem(long j10) {
            this.controlsFwk.skipToQueueItem(j10);
        }

        @Override // androidx.media3.session.legacy.MediaControllerCompat.TransportControls
        public void stop() {
            this.controlsFwk.stop();
        }

        @Override // androidx.media3.session.legacy.MediaControllerCompat.TransportControls
        public void setRating(RatingCompat ratingCompat, Bundle bundle) {
            Bundle bundle2 = new Bundle();
            bundle2.putParcelable(MediaSessionCompat.ACTION_ARGUMENT_RATING, LegacyParcelableUtil.convert(ratingCompat, android.support.v4.media.RatingCompat.CREATOR));
            bundle2.putBundle(MediaSessionCompat.ACTION_ARGUMENT_EXTRAS, bundle);
            sendCustomAction(MediaSessionCompat.ACTION_SET_RATING, bundle2);
        }

        @Override // androidx.media3.session.legacy.MediaControllerCompat.TransportControls
        public void sendCustomAction(String str, Bundle bundle) {
            MediaControllerCompat.validateCustomAction(str, bundle);
            this.controlsFwk.sendCustomAction(str, bundle);
        }
    }

    public static class TransportControlsApi24 extends TransportControlsApi23 {
        public TransportControlsApi24(MediaController.TransportControls transportControls) {
            super(transportControls);
        }

        @Override // androidx.media3.session.legacy.MediaControllerCompat.TransportControlsApi23, androidx.media3.session.legacy.MediaControllerCompat.TransportControls
        public void prepare() {
            this.controlsFwk.prepare();
        }

        @Override // androidx.media3.session.legacy.MediaControllerCompat.TransportControlsApi23, androidx.media3.session.legacy.MediaControllerCompat.TransportControls
        public void prepareFromMediaId(String str, Bundle bundle) {
            this.controlsFwk.prepareFromMediaId(str, bundle);
        }

        @Override // androidx.media3.session.legacy.MediaControllerCompat.TransportControlsApi23, androidx.media3.session.legacy.MediaControllerCompat.TransportControls
        public void prepareFromSearch(String str, Bundle bundle) {
            this.controlsFwk.prepareFromSearch(str, bundle);
        }

        @Override // androidx.media3.session.legacy.MediaControllerCompat.TransportControlsApi23, androidx.media3.session.legacy.MediaControllerCompat.TransportControls
        public void prepareFromUri(Uri uri, Bundle bundle) {
            this.controlsFwk.prepareFromUri(uri, bundle);
        }
    }

    public static class TransportControlsApi29 extends TransportControlsApi24 {
        public TransportControlsApi29(MediaController.TransportControls transportControls) {
            super(transportControls);
        }

        @Override // androidx.media3.session.legacy.MediaControllerCompat.TransportControlsApi23, androidx.media3.session.legacy.MediaControllerCompat.TransportControls
        public void setPlaybackSpeed(float f10) {
            if (f10 == 0.0f) {
                throw new IllegalArgumentException("speed must not be zero");
            }
            this.controlsFwk.setPlaybackSpeed(f10);
        }
    }

    public MediaControllerCompat(Context context, MediaSessionCompat mediaSessionCompat) {
        this(context, mediaSessionCompat.getSessionToken());
    }

    public static void validateCustomAction(String str, Bundle bundle) {
        if (str == null) {
            return;
        }
        if (str.equals(MediaSessionCompat.ACTION_FOLLOW) || str.equals(MediaSessionCompat.ACTION_UNFOLLOW)) {
            if (bundle == null || !bundle.containsKey(MediaSessionCompat.ARGUMENT_MEDIA_ATTRIBUTE)) {
                throw new IllegalArgumentException(c.l("An extra field android.support.v4.media.session.ARGUMENT_MEDIA_ATTRIBUTE is required for this action ", str, "."));
            }
        }
    }

    public void addQueueItem(MediaDescriptionCompat mediaDescriptionCompat) {
        this.impl.addQueueItem(mediaDescriptionCompat);
    }

    public void adjustVolume(int i10, int i11) {
        this.impl.adjustVolume(i10, i11);
    }

    public boolean dispatchMediaButtonEvent(KeyEvent keyEvent) {
        if (keyEvent != null) {
            return this.impl.dispatchMediaButtonEvent(keyEvent);
        }
        throw new IllegalArgumentException("KeyEvent may not be null");
    }

    public Bundle getExtras() {
        return this.impl.getExtras();
    }

    public long getFlags() {
        return this.impl.getFlags();
    }

    public Object getMediaController() {
        return this.impl.getMediaController();
    }

    public MediaMetadataCompat getMetadata() {
        return this.impl.getMetadata();
    }

    public String getPackageName() {
        return this.impl.getPackageName();
    }

    public PlaybackInfo getPlaybackInfo() {
        return this.impl.getPlaybackInfo();
    }

    public PlaybackStateCompat getPlaybackState() {
        return this.impl.getPlaybackState();
    }

    public List<MediaSessionCompat.QueueItem> getQueue() {
        return this.impl.getQueue();
    }

    public CharSequence getQueueTitle() {
        return this.impl.getQueueTitle();
    }

    public int getRatingType() {
        return this.impl.getRatingType();
    }

    public int getRepeatMode() {
        return this.impl.getRepeatMode();
    }

    public PendingIntent getSessionActivity() {
        return this.impl.getSessionActivity();
    }

    public Bundle getSessionInfo() {
        return this.impl.getSessionInfo();
    }

    public MediaSessionCompat.Token getSessionToken() {
        return this.token;
    }

    public int getShuffleMode() {
        return this.impl.getShuffleMode();
    }

    public TransportControls getTransportControls() {
        return this.impl.getTransportControls();
    }

    public boolean isCaptioningEnabled() {
        return this.impl.isCaptioningEnabled();
    }

    public boolean isSessionReady() {
        return this.impl.isSessionReady();
    }

    public void registerCallback(Callback callback, Handler handler) {
        if (!this.registeredCallbacks.add(callback)) {
            Log.w(TAG, "the callback has already been registered");
            return;
        }
        if (handler == null) {
            handler = new Handler();
        }
        callback.setHandler(handler);
        this.impl.registerCallback(callback, handler);
    }

    public void removeQueueItem(MediaDescriptionCompat mediaDescriptionCompat) {
        this.impl.removeQueueItem(mediaDescriptionCompat);
    }

    @Deprecated
    public void removeQueueItemAt(int i10) {
        MediaSessionCompat.QueueItem queueItem;
        List<MediaSessionCompat.QueueItem> queue = getQueue();
        if (queue == null || i10 < 0 || i10 >= queue.size() || (queueItem = queue.get(i10)) == null) {
            return;
        }
        removeQueueItem(queueItem.getDescription());
    }

    public void sendCommand(String str, Bundle bundle, ResultReceiver resultReceiver) {
        if (TextUtils.isEmpty(str)) {
            throw new IllegalArgumentException("command must neither be null nor empty");
        }
        this.impl.sendCommand(str, bundle, resultReceiver);
    }

    public void setVolumeTo(int i10, int i11) {
        this.impl.setVolumeTo(i10, i11);
    }

    public void unregisterCallback(Callback callback) {
        if (!this.registeredCallbacks.remove(callback)) {
            Log.w(TAG, "the callback has never been registered");
            return;
        }
        try {
            this.impl.unregisterCallback(callback);
        } finally {
            callback.setHandler(null);
        }
    }

    public MediaControllerCompat(Context context, MediaSessionCompat.Token token) {
        this.registeredCallbacks = Collections.synchronizedSet(new HashSet());
        this.token = token;
        if (Build.VERSION.SDK_INT >= 29) {
            this.impl = new MediaControllerImplApi29(context, token);
        } else {
            this.impl = new MediaControllerImplApi23(context, token);
        }
    }

    public void addQueueItem(MediaDescriptionCompat mediaDescriptionCompat, int i10) {
        this.impl.addQueueItem(mediaDescriptionCompat, i10);
    }

    public static class MediaControllerImplApi23 implements MediaControllerImpl {
        protected final MediaController controllerFwk;
        protected Bundle sessionInfo;
        final MediaSessionCompat.Token sessionToken;
        final Object lock = new Object();
        private final List<Callback> pendingCallbacks = new ArrayList();
        private final HashMap<Callback, Callback.CallbackStub> callbackMap = new HashMap<>();

        public static class ExtraBinderRequestResultReceiver extends ResultReceiver {
            private final WeakReference<MediaControllerImplApi23> mediaControllerImpl;

            public ExtraBinderRequestResultReceiver(MediaControllerImplApi23 mediaControllerImplApi23) {
                super(null);
                this.mediaControllerImpl = new WeakReference<>(mediaControllerImplApi23);
            }

            @Override // android.os.ResultReceiver
            public void onReceiveResult(int i10, Bundle bundle) {
                MediaControllerImplApi23 mediaControllerImplApi23 = this.mediaControllerImpl.get();
                if (mediaControllerImplApi23 == null || bundle == null) {
                    return;
                }
                synchronized (mediaControllerImplApi23.lock) {
                    mediaControllerImplApi23.sessionToken.setExtraBinder(IMediaSession.Stub.asInterface(bundle.getBinder(MediaSessionCompat.KEY_EXTRA_BINDER)));
                    mediaControllerImplApi23.sessionToken.setSession2Token(k2.c.s(bundle));
                    mediaControllerImplApi23.processPendingCallbacksLocked();
                }
            }
        }

        public MediaControllerImplApi23(Context context, MediaSessionCompat.Token token) {
            this.sessionToken = token;
            this.controllerFwk = new MediaController(context, token.getToken());
            if (token.getExtraBinder() == null) {
                requestExtraBinder();
            }
        }

        private void requestExtraBinder() {
            sendCommand(MediaControllerCompat.COMMAND_GET_EXTRA_BINDER, null, new ExtraBinderRequestResultReceiver(this));
        }

        @Override // androidx.media3.session.legacy.MediaControllerCompat.MediaControllerImpl
        public void addQueueItem(MediaDescriptionCompat mediaDescriptionCompat) {
            if ((getFlags() & 4) == 0) {
                throw new UnsupportedOperationException("This session doesn't support queue management operations");
            }
            Bundle bundle = new Bundle();
            bundle.putParcelable(MediaControllerCompat.COMMAND_ARGUMENT_MEDIA_DESCRIPTION, LegacyParcelableUtil.convert(mediaDescriptionCompat, android.support.v4.media.MediaDescriptionCompat.CREATOR));
            sendCommand(MediaControllerCompat.COMMAND_ADD_QUEUE_ITEM, bundle, null);
        }

        @Override // androidx.media3.session.legacy.MediaControllerCompat.MediaControllerImpl
        public void adjustVolume(int i10, int i11) {
            this.controllerFwk.adjustVolume(i10, i11);
        }

        @Override // androidx.media3.session.legacy.MediaControllerCompat.MediaControllerImpl
        public boolean dispatchMediaButtonEvent(KeyEvent keyEvent) {
            return this.controllerFwk.dispatchMediaButtonEvent(keyEvent);
        }

        @Override // androidx.media3.session.legacy.MediaControllerCompat.MediaControllerImpl
        public Bundle getExtras() {
            return this.controllerFwk.getExtras();
        }

        @Override // androidx.media3.session.legacy.MediaControllerCompat.MediaControllerImpl
        public long getFlags() {
            return this.controllerFwk.getFlags();
        }

        @Override // androidx.media3.session.legacy.MediaControllerCompat.MediaControllerImpl
        public Object getMediaController() {
            return this.controllerFwk;
        }

        @Override // androidx.media3.session.legacy.MediaControllerCompat.MediaControllerImpl
        public MediaMetadataCompat getMetadata() {
            MediaMetadata metadata = this.controllerFwk.getMetadata();
            if (metadata != null) {
                return MediaMetadataCompat.fromMediaMetadata(metadata);
            }
            return null;
        }

        @Override // androidx.media3.session.legacy.MediaControllerCompat.MediaControllerImpl
        public String getPackageName() {
            return this.controllerFwk.getPackageName();
        }

        @Override // androidx.media3.session.legacy.MediaControllerCompat.MediaControllerImpl
        public PlaybackInfo getPlaybackInfo() {
            MediaController.PlaybackInfo playbackInfo = this.controllerFwk.getPlaybackInfo();
            if (playbackInfo != null) {
                return new PlaybackInfo(playbackInfo.getPlaybackType(), AudioAttributes.fromPlatformAudioAttributes(playbackInfo.getAudioAttributes()), playbackInfo.getVolumeControl(), playbackInfo.getMaxVolume(), playbackInfo.getCurrentVolume(), Build.VERSION.SDK_INT >= 30 ? playbackInfo.getVolumeControlId() : null);
            }
            return null;
        }

        @Override // androidx.media3.session.legacy.MediaControllerCompat.MediaControllerImpl
        public PlaybackStateCompat getPlaybackState() {
            IMediaSession extraBinder = this.sessionToken.getExtraBinder();
            if (extraBinder != null) {
                try {
                    return extraBinder.getPlaybackState();
                } catch (RemoteException | SecurityException e5) {
                    Log.e(MediaControllerCompat.TAG, "Dead object in getPlaybackState.", e5);
                }
            }
            PlaybackState playbackState = this.controllerFwk.getPlaybackState();
            if (playbackState != null) {
                return PlaybackStateCompat.fromPlaybackState(playbackState);
            }
            return null;
        }

        @Override // androidx.media3.session.legacy.MediaControllerCompat.MediaControllerImpl
        public List<MediaSessionCompat.QueueItem> getQueue() {
            List<MediaSession.QueueItem> queue = this.controllerFwk.getQueue();
            if (queue != null) {
                return MediaSessionCompat.QueueItem.fromQueueItemList(queue);
            }
            return null;
        }

        @Override // androidx.media3.session.legacy.MediaControllerCompat.MediaControllerImpl
        public CharSequence getQueueTitle() {
            return this.controllerFwk.getQueueTitle();
        }

        @Override // androidx.media3.session.legacy.MediaControllerCompat.MediaControllerImpl
        public int getRatingType() {
            return this.controllerFwk.getRatingType();
        }

        @Override // androidx.media3.session.legacy.MediaControllerCompat.MediaControllerImpl
        public int getRepeatMode() {
            IMediaSession extraBinder = this.sessionToken.getExtraBinder();
            if (extraBinder == null) {
                return -1;
            }
            try {
                return extraBinder.getRepeatMode();
            } catch (RemoteException | SecurityException e5) {
                Log.e(MediaControllerCompat.TAG, "Dead object in getRepeatMode.", e5);
                return -1;
            }
        }

        @Override // androidx.media3.session.legacy.MediaControllerCompat.MediaControllerImpl
        public PendingIntent getSessionActivity() {
            return this.controllerFwk.getSessionActivity();
        }

        @Override // androidx.media3.session.legacy.MediaControllerCompat.MediaControllerImpl
        public Bundle getSessionInfo() {
            if (this.sessionInfo != null) {
                return new Bundle(this.sessionInfo);
            }
            IMediaSession extraBinder = this.sessionToken.getExtraBinder();
            if (extraBinder != null) {
                try {
                    this.sessionInfo = extraBinder.getSessionInfo();
                } catch (RemoteException | SecurityException e5) {
                    Log.e(MediaControllerCompat.TAG, "Dead object in getSessionInfo.", e5);
                    this.sessionInfo = Bundle.EMPTY;
                }
            }
            Bundle bundleUnparcelWithClassLoader = MediaSessionCompat.unparcelWithClassLoader(this.sessionInfo);
            this.sessionInfo = bundleUnparcelWithClassLoader;
            return bundleUnparcelWithClassLoader == null ? Bundle.EMPTY : new Bundle(this.sessionInfo);
        }

        @Override // androidx.media3.session.legacy.MediaControllerCompat.MediaControllerImpl
        public int getShuffleMode() {
            IMediaSession extraBinder = this.sessionToken.getExtraBinder();
            if (extraBinder == null) {
                return -1;
            }
            try {
                return extraBinder.getShuffleMode();
            } catch (RemoteException | SecurityException e5) {
                Log.e(MediaControllerCompat.TAG, "Dead object in getShuffleMode.", e5);
                return -1;
            }
        }

        @Override // androidx.media3.session.legacy.MediaControllerCompat.MediaControllerImpl
        public TransportControls getTransportControls() {
            MediaController.TransportControls transportControls = this.controllerFwk.getTransportControls();
            int i10 = Build.VERSION.SDK_INT;
            return i10 >= 29 ? new TransportControlsApi29(transportControls) : i10 >= 24 ? new TransportControlsApi24(transportControls) : new TransportControlsApi23(transportControls);
        }

        @Override // androidx.media3.session.legacy.MediaControllerCompat.MediaControllerImpl
        public boolean isCaptioningEnabled() {
            IMediaSession extraBinder = this.sessionToken.getExtraBinder();
            if (extraBinder == null) {
                return false;
            }
            try {
                return extraBinder.isCaptioningEnabled();
            } catch (RemoteException | SecurityException e5) {
                Log.e(MediaControllerCompat.TAG, "Dead object in isCaptioningEnabled.", e5);
                return false;
            }
        }

        @Override // androidx.media3.session.legacy.MediaControllerCompat.MediaControllerImpl
        public boolean isSessionReady() {
            return this.sessionToken.getExtraBinder() != null;
        }

        public void processPendingCallbacksLocked() {
            IMediaSession extraBinder = this.sessionToken.getExtraBinder();
            if (extraBinder == null) {
                return;
            }
            for (Callback callback : this.pendingCallbacks) {
                Callback.CallbackStub callbackStub = new Callback.CallbackStub(callback);
                this.callbackMap.put(callback, callbackStub);
                callback.iControllerCallback = callbackStub;
                try {
                    extraBinder.registerCallbackListener(callbackStub);
                    callback.postToHandler(13, null, null);
                } catch (RemoteException | SecurityException e5) {
                    Log.e(MediaControllerCompat.TAG, "Dead object in registerCallback.", e5);
                }
            }
            this.pendingCallbacks.clear();
        }

        @Override // androidx.media3.session.legacy.MediaControllerCompat.MediaControllerImpl
        public final void registerCallback(Callback callback, Handler handler) {
            MediaController mediaController = this.controllerFwk;
            MediaController.Callback callback2 = callback.callbackFwk;
            callback2.getClass();
            mediaController.registerCallback(callback2, handler);
            synchronized (this.lock) {
                IMediaSession extraBinder = this.sessionToken.getExtraBinder();
                if (extraBinder != null) {
                    Callback.CallbackStub callbackStub = new Callback.CallbackStub(callback);
                    this.callbackMap.put(callback, callbackStub);
                    callback.iControllerCallback = callbackStub;
                    try {
                        extraBinder.registerCallbackListener(callbackStub);
                        callback.postToHandler(13, null, null);
                    } catch (RemoteException | SecurityException e5) {
                        Log.e(MediaControllerCompat.TAG, "Dead object in registerCallback.", e5);
                    }
                } else {
                    callback.iControllerCallback = null;
                    this.pendingCallbacks.add(callback);
                }
            }
        }

        @Override // androidx.media3.session.legacy.MediaControllerCompat.MediaControllerImpl
        public void removeQueueItem(MediaDescriptionCompat mediaDescriptionCompat) {
            if ((getFlags() & 4) == 0) {
                throw new UnsupportedOperationException("This session doesn't support queue management operations");
            }
            Bundle bundle = new Bundle();
            bundle.putParcelable(MediaControllerCompat.COMMAND_ARGUMENT_MEDIA_DESCRIPTION, LegacyParcelableUtil.convert(mediaDescriptionCompat, android.support.v4.media.MediaDescriptionCompat.CREATOR));
            sendCommand(MediaControllerCompat.COMMAND_REMOVE_QUEUE_ITEM, bundle, null);
        }

        @Override // androidx.media3.session.legacy.MediaControllerCompat.MediaControllerImpl
        public void sendCommand(String str, Bundle bundle, ResultReceiver resultReceiver) {
            this.controllerFwk.sendCommand(str, bundle, resultReceiver);
        }

        @Override // androidx.media3.session.legacy.MediaControllerCompat.MediaControllerImpl
        public void setVolumeTo(int i10, int i11) {
            this.controllerFwk.setVolumeTo(i10, i11);
        }

        @Override // androidx.media3.session.legacy.MediaControllerCompat.MediaControllerImpl
        public final void unregisterCallback(Callback callback) {
            MediaController mediaController = this.controllerFwk;
            MediaController.Callback callback2 = callback.callbackFwk;
            callback2.getClass();
            mediaController.unregisterCallback(callback2);
            synchronized (this.lock) {
                IMediaSession extraBinder = this.sessionToken.getExtraBinder();
                if (extraBinder != null) {
                    try {
                        Callback.CallbackStub callbackStubRemove = this.callbackMap.remove(callback);
                        if (callbackStubRemove != null) {
                            callback.iControllerCallback = null;
                            extraBinder.unregisterCallbackListener(callbackStubRemove);
                        }
                    } catch (RemoteException | SecurityException e5) {
                        Log.e(MediaControllerCompat.TAG, "Dead object in unregisterCallback.", e5);
                    }
                } else {
                    this.pendingCallbacks.remove(callback);
                }
            }
        }

        @Override // androidx.media3.session.legacy.MediaControllerCompat.MediaControllerImpl
        public void addQueueItem(MediaDescriptionCompat mediaDescriptionCompat, int i10) {
            if ((getFlags() & 4) != 0) {
                Bundle bundle = new Bundle();
                bundle.putParcelable(MediaControllerCompat.COMMAND_ARGUMENT_MEDIA_DESCRIPTION, LegacyParcelableUtil.convert(mediaDescriptionCompat, android.support.v4.media.MediaDescriptionCompat.CREATOR));
                bundle.putInt(MediaControllerCompat.COMMAND_ARGUMENT_INDEX, i10);
                sendCommand(MediaControllerCompat.COMMAND_ADD_QUEUE_ITEM_AT, bundle, null);
                return;
            }
            throw new UnsupportedOperationException("This session doesn't support queue management operations");
        }
    }
}
