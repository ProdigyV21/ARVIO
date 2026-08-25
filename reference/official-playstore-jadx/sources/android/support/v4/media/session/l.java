package android.support.v4.media.session;

import android.content.Intent;
import android.media.session.MediaSession;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.ResultReceiver;
import android.support.v4.media.MediaDescriptionCompat;
import android.support.v4.media.RatingCompat;
import android.view.KeyEvent;
import android.view.ViewConfiguration;
import java.lang.ref.WeakReference;

/* JADX INFO: loaded from: classes.dex */
public abstract class l {
    j mCallbackHandler;
    private boolean mMediaPlayPausePendingOnHandler;
    final Object mLock = new Object();
    final MediaSession.Callback mCallbackFwk = new k(this);
    WeakReference<m> mSessionImpl = new WeakReference<>(null);

    public void handleMediaPlayPauseIfPendingOnHandler(m mVar, Handler handler) {
        if (this.mMediaPlayPausePendingOnHandler) {
            this.mMediaPlayPausePendingOnHandler = false;
            handler.removeMessages(1);
            PlaybackStateCompat playbackState = mVar.getPlaybackState();
            long j10 = playbackState == null ? 0L : playbackState.f862o;
            boolean z = playbackState != null && playbackState.f858i == 3;
            boolean z5 = (516 & j10) != 0;
            boolean z10 = (j10 & 514) != 0;
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
        m mVar;
        j jVar;
        KeyEvent keyEvent;
        if (Build.VERSION.SDK_INT < 27) {
            synchronized (this.mLock) {
                mVar = this.mSessionImpl.get();
                jVar = this.mCallbackHandler;
            }
            if (mVar != null && jVar != null && (keyEvent = (KeyEvent) intent.getParcelableExtra("android.intent.extra.KEY_EVENT")) != null && keyEvent.getAction() == 0) {
                androidx.media.d currentControllerInfo = mVar.getCurrentControllerInfo();
                int keyCode = keyEvent.getKeyCode();
                if (keyCode != 79 && keyCode != 85) {
                    handleMediaPlayPauseIfPendingOnHandler(mVar, jVar);
                    return false;
                }
                if (keyEvent.getRepeatCount() != 0) {
                    handleMediaPlayPauseIfPendingOnHandler(mVar, jVar);
                    return true;
                }
                if (!this.mMediaPlayPausePendingOnHandler) {
                    this.mMediaPlayPausePendingOnHandler = true;
                    jVar.sendMessageDelayed(jVar.obtainMessage(1, currentControllerInfo), ViewConfiguration.getDoubleTapTimeout());
                    return true;
                }
                jVar.removeMessages(1);
                this.mMediaPlayPausePendingOnHandler = false;
                PlaybackStateCompat playbackState = mVar.getPlaybackState();
                if (((playbackState == null ? 0L : playbackState.f862o) & 32) != 0) {
                    onSkipToNext();
                }
                return true;
            }
        }
        return false;
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

    @Deprecated
    public void onRemoveQueueItemAt(int i10) {
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

    public void setSessionImpl(m mVar, Handler handler) {
        synchronized (this.mLock) {
            try {
                this.mSessionImpl = new WeakReference<>(mVar);
                j jVar = this.mCallbackHandler;
                j jVar2 = null;
                if (jVar != null) {
                    jVar.removeCallbacksAndMessages(null);
                }
                if (mVar != null && handler != null) {
                    jVar2 = new j(this, handler.getLooper());
                }
                this.mCallbackHandler = jVar2;
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
