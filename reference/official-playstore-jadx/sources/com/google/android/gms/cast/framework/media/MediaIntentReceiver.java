package com.google.android.gms.cast.framework.media;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import com.google.android.gms.cast.framework.CastContext;
import com.google.android.gms.cast.framework.CastSession;
import com.google.android.gms.cast.framework.Session;
import com.google.android.gms.cast.framework.SessionManager;
import com.google.android.gms.cast.internal.Logger;
import com.google.android.gms.common.internal.t;

/* JADX INFO: loaded from: classes4.dex */
public class MediaIntentReceiver extends BroadcastReceiver {
    public static final String ACTION_DISCONNECT = "com.google.android.gms.cast.framework.action.DISCONNECT";
    public static final String ACTION_FORWARD = "com.google.android.gms.cast.framework.action.FORWARD";
    public static final String ACTION_REWIND = "com.google.android.gms.cast.framework.action.REWIND";
    public static final String ACTION_SKIP_NEXT = "com.google.android.gms.cast.framework.action.SKIP_NEXT";
    public static final String ACTION_SKIP_PREV = "com.google.android.gms.cast.framework.action.SKIP_PREV";
    public static final String ACTION_STOP_CASTING = "com.google.android.gms.cast.framework.action.STOP_CASTING";
    public static final String ACTION_TOGGLE_PLAYBACK = "com.google.android.gms.cast.framework.action.TOGGLE_PLAYBACK";
    public static final String EXTRA_SKIP_STEP_MS = "googlecast-extra_skip_step_ms";
    private static final String TAG = "MediaIntentReceiver";
    private static final Logger log = new Logger(TAG);

    private static RemoteMediaClient getRemoteMediaClient(CastSession castSession) {
        if (castSession == null || !castSession.isConnected()) {
            return null;
        }
        return castSession.getRemoteMediaClient();
    }

    private void seek(CastSession castSession, long j10) {
        RemoteMediaClient remoteMediaClient;
        if (j10 == 0 || (remoteMediaClient = getRemoteMediaClient(castSession)) == null || remoteMediaClient.isLiveStream() || remoteMediaClient.isPlayingAd()) {
            return;
        }
        remoteMediaClient.seek(remoteMediaClient.getApproximateStreamPosition() + j10);
    }

    private void togglePlayback(CastSession castSession) {
        RemoteMediaClient remoteMediaClient = getRemoteMediaClient(castSession);
        if (remoteMediaClient == null) {
            return;
        }
        remoteMediaClient.togglePlayback();
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        SessionManager sessionManager;
        Session currentSession;
        String action = intent.getAction();
        log.d("onReceive action: %s", action);
        if (action == null || (currentSession = (sessionManager = CastContext.getSharedInstance(context).getSessionManager()).getCurrentSession()) == null) {
            return;
        }
        switch (action.hashCode()) {
            case -1699820260:
                if (action.equals(ACTION_REWIND)) {
                    onReceiveActionRewind(currentSession, intent.getLongExtra(EXTRA_SKIP_STEP_MS, 0L));
                    return;
                }
                break;
            case -945151566:
                if (action.equals(ACTION_SKIP_NEXT)) {
                    onReceiveActionSkipNext(currentSession);
                    return;
                }
                break;
            case -945080078:
                if (action.equals(ACTION_SKIP_PREV)) {
                    onReceiveActionSkipPrev(currentSession);
                    return;
                }
                break;
            case -668151673:
                if (action.equals(ACTION_STOP_CASTING)) {
                    sessionManager.endCurrentSession(true);
                    return;
                }
                break;
            case -124479363:
                if (action.equals(ACTION_DISCONNECT)) {
                    sessionManager.endCurrentSession(false);
                    return;
                }
                break;
            case 235550565:
                if (action.equals(ACTION_TOGGLE_PLAYBACK)) {
                    onReceiveActionTogglePlayback(currentSession);
                    return;
                }
                break;
            case 1362116196:
                if (action.equals(ACTION_FORWARD)) {
                    onReceiveActionForward(currentSession, intent.getLongExtra(EXTRA_SKIP_STEP_MS, 0L));
                    return;
                }
                break;
            case 1997055314:
                if (action.equals("android.intent.action.MEDIA_BUTTON")) {
                    onReceiveActionMediaButton(currentSession, intent);
                    return;
                }
                break;
        }
        onReceiveOtherAction(context, action, intent);
    }

    public void onReceiveActionForward(Session session, long j10) {
        if (session instanceof CastSession) {
            seek((CastSession) session, j10);
        }
    }

    public void onReceiveActionMediaButton(Session session, Intent intent) {
        if ((session instanceof CastSession) && intent.hasExtra("android.intent.extra.KEY_EVENT")) {
            Bundle extras = intent.getExtras();
            t.i(extras);
            KeyEvent keyEvent = (KeyEvent) extras.get("android.intent.extra.KEY_EVENT");
            if (keyEvent != null && keyEvent.getAction() == 0 && keyEvent.getKeyCode() == 85) {
                togglePlayback((CastSession) session);
            }
        }
    }

    public void onReceiveActionRewind(Session session, long j10) {
        if (session instanceof CastSession) {
            seek((CastSession) session, -j10);
        }
    }

    public void onReceiveActionSkipNext(Session session) {
        RemoteMediaClient remoteMediaClient;
        if (!(session instanceof CastSession) || (remoteMediaClient = getRemoteMediaClient((CastSession) session)) == null || remoteMediaClient.isPlayingAd()) {
            return;
        }
        remoteMediaClient.queueNext(null);
    }

    public void onReceiveActionSkipPrev(Session session) {
        RemoteMediaClient remoteMediaClient;
        if (!(session instanceof CastSession) || (remoteMediaClient = getRemoteMediaClient((CastSession) session)) == null || remoteMediaClient.isPlayingAd()) {
            return;
        }
        remoteMediaClient.queuePrev(null);
    }

    public void onReceiveActionTogglePlayback(Session session) {
        if (session instanceof CastSession) {
            togglePlayback((CastSession) session);
        }
    }

    public void onReceiveOtherAction(Context context, String str, Intent intent) {
    }

    @Deprecated
    public void onReceiveOtherAction(String str, Intent intent) {
        onReceiveOtherAction(null, str, intent);
    }
}
