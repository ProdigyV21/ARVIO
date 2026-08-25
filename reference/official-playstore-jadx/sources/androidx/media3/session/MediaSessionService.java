package androidx.media3.session;

import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.net.Uri;
import android.os.Binder;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import androidx.media3.common.MediaLibraryInfo;
import androidx.media3.common.util.Log;
import androidx.media3.common.util.Util;
import androidx.media3.session.DefaultMediaNotificationProvider;
import androidx.media3.session.IMediaSessionService;
import androidx.media3.session.MediaNotification;
import androidx.media3.session.MediaSession;
import androidx.media3.session.MediaSessionStub;
import androidx.media3.session.legacy.MediaBrowserServiceCompat;
import androidx.media3.session.legacy.MediaSessionManager;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* JADX INFO: loaded from: classes3.dex */
public abstract class MediaSessionService extends Service {
    public static final long DEFAULT_FOREGROUND_SERVICE_TIMEOUT_MS = 600000;
    public static final String SERVICE_INTERFACE = "androidx.media3.session.MediaSessionService";
    public static final int SHOW_NOTIFICATION_FOR_IDLE_PLAYER_AFTER_STOP_OR_ERROR = 3;
    public static final int SHOW_NOTIFICATION_FOR_IDLE_PLAYER_ALWAYS = 1;
    public static final int SHOW_NOTIFICATION_FOR_IDLE_PLAYER_NEVER = 2;
    private static final String TAG = "MSessionService";
    private DefaultActionFactory actionFactory;
    private Listener listener;
    private MediaNotificationManager mediaNotificationManager;
    private MediaSessionServiceStub stub;
    private final Object lock = new Object();
    private final Handler mainHandler = new Handler(Looper.getMainLooper());
    private final Map<String, MediaSession> sessions = new t.e(0);
    private boolean defaultMethodCalled = false;

    public static final class Api31 {
        private Api31() {
        }

        public static boolean instanceOfForegroundServiceStartNotAllowedException(IllegalStateException illegalStateException) {
            return v.b(illegalStateException);
        }
    }

    public interface Listener {
        void onForegroundServiceStartNotAllowedException();
    }

    public final class MediaSessionListener implements MediaSession.Listener {
        private MediaSessionListener() {
        }

        @Override // androidx.media3.session.MediaSession.Listener
        public void onNotificationRefreshRequired(MediaSession mediaSession) {
            MediaSessionService.this.onUpdateNotificationInternal(mediaSession, false);
        }

        @Override // androidx.media3.session.MediaSession.Listener
        public boolean onPlayRequested(MediaSession mediaSession) {
            int i10 = Build.VERSION.SDK_INT;
            if (i10 < 31 || i10 >= 33 || MediaSessionService.this.getMediaNotificationManager().isStartedInForeground()) {
                return true;
            }
            return MediaSessionService.this.onUpdateNotificationInternal(mediaSession, true);
        }
    }

    public static final class MediaSessionServiceStub extends IMediaSessionService.Stub {
        private final Handler handler;
        private final Set<IMediaController> pendingControllers = Collections.synchronizedSet(new HashSet());
        private final WeakReference<MediaSessionService> serviceReference;

        public MediaSessionServiceStub(MediaSessionService mediaSessionService) {
            this.serviceReference = new WeakReference<>(mediaSessionService);
            this.handler = new Handler(mediaSessionService.getApplicationContext().getMainLooper());
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void lambda$connect$0(IMediaController iMediaController, MediaSessionManager.RemoteUserInfo remoteUserInfo, ConnectionRequest connectionRequest, boolean z) {
            this.pendingControllers.remove(iMediaController);
            try {
                try {
                    MediaSessionService mediaSessionService = this.serviceReference.get();
                    if (mediaSessionService == null) {
                        SessionUtil.disconnectIMediaController(iMediaController);
                        return;
                    }
                    int i10 = connectionRequest.libraryVersion;
                    int i11 = connectionRequest.controllerInterfaceVersion;
                    MediaSession.ControllerInfo controllerInfo = new MediaSession.ControllerInfo(remoteUserInfo, i10, i11, z, new MediaSessionStub.Controller2Cb(iMediaController, i11), connectionRequest.connectionHints, connectionRequest.maxCommandsForMediaItems, true);
                    MediaSession mediaSessionOnGetSession = mediaSessionService.onGetSession(controllerInfo);
                    if (mediaSessionOnGetSession == null) {
                        SessionUtil.disconnectIMediaController(iMediaController);
                        return;
                    } else {
                        mediaSessionService.addSession(mediaSessionOnGetSession);
                        mediaSessionOnGetSession.handleControllerConnectionFromService(iMediaController, controllerInfo);
                        return;
                    }
                } catch (Exception e5) {
                    Log.w(MediaSessionService.TAG, "Failed to add a session to session service", e5);
                    SessionUtil.disconnectIMediaController(iMediaController);
                    return;
                }
            } catch (Throwable th) {
                SessionUtil.disconnectIMediaController(iMediaController);
                throw th;
            }
            SessionUtil.disconnectIMediaController(iMediaController);
            throw th;
        }

        @Override // androidx.media3.session.IMediaSessionService
        public void connect(IMediaController iMediaController, Bundle bundle) {
            if (iMediaController == null || bundle == null) {
                SessionUtil.disconnectIMediaController(iMediaController);
                return;
            }
            try {
                ConnectionRequest connectionRequestFromBundle = ConnectionRequest.fromBundle(bundle);
                MediaSessionService mediaSessionService = this.serviceReference.get();
                if (mediaSessionService == null) {
                    SessionUtil.disconnectIMediaController(iMediaController);
                    return;
                }
                int callingPid = Binder.getCallingPid();
                int callingUid = Binder.getCallingUid();
                long jClearCallingIdentity = Binder.clearCallingIdentity();
                if (callingPid == 0) {
                    callingPid = connectionRequestFromBundle.pid;
                }
                if (SessionUtil.checkPackageValidity(mediaSessionService, connectionRequestFromBundle.packageName, callingUid) == 0) {
                    MediaSessionManager.RemoteUserInfo remoteUserInfo = new MediaSessionManager.RemoteUserInfo(connectionRequestFromBundle.packageName, callingPid, callingUid);
                    boolean zIsTrustedForMediaControl = MediaSessionManager.getSessionManager(mediaSessionService.getApplicationContext()).isTrustedForMediaControl(remoteUserInfo);
                    this.pendingControllers.add(iMediaController);
                    try {
                        this.handler.post(new z1(2, this, iMediaController, remoteUserInfo, connectionRequestFromBundle, zIsTrustedForMediaControl));
                        return;
                    } finally {
                        Binder.restoreCallingIdentity(jClearCallingIdentity);
                    }
                }
                Log.w(MediaSessionService.TAG, "Ignoring connection from invalid package name " + connectionRequestFromBundle.packageName + " (uid=" + callingUid + ")");
                SessionUtil.disconnectIMediaController(iMediaController);
            } catch (RuntimeException e5) {
                Log.w(MediaSessionService.TAG, "Ignoring malformed Bundle for ConnectionRequest", e5);
                SessionUtil.disconnectIMediaController(iMediaController);
            }
        }

        public void release() {
            this.serviceReference.clear();
            this.handler.removeCallbacksAndMessages(null);
            Iterator<IMediaController> it = this.pendingControllers.iterator();
            while (it.hasNext()) {
                SessionUtil.disconnectIMediaController(it.next());
            }
            this.pendingControllers.clear();
        }
    }

    @Target({ElementType.TYPE_USE})
    @Documented
    @Retention(RetentionPolicy.SOURCE)
    public @interface ShowNotificationForIdlePlayerMode {
    }

    private static MediaSession.ControllerInfo createFallbackMediaButtonCaller(Intent intent) {
        ComponentName component = intent.getComponent();
        return new MediaSession.ControllerInfo(new MediaSessionManager.RemoteUserInfo(component != null ? component.getPackageName() : SERVICE_INTERFACE, -1, -1), MediaLibraryInfo.VERSION_INT, 8, false, null, Bundle.EMPTY, 0, false);
    }

    private DefaultActionFactory getActionFactory() {
        if (this.actionFactory == null) {
            this.actionFactory = new DefaultActionFactory(this);
        }
        return this.actionFactory;
    }

    private Listener getListener() {
        Listener listener;
        synchronized (this.lock) {
            listener = this.listener;
        }
        return listener;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public MediaNotificationManager getMediaNotificationManager() {
        return getMediaNotificationManager(null);
    }

    private boolean isAnySessionPlaying() {
        List<MediaSession> sessions = getSessions();
        for (int i10 = 0; i10 < sessions.size(); i10++) {
            if (sessions.get(i10).getPlayer().isPlaying()) {
                return true;
            }
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$addSession$0(MediaSession mediaSession) {
        getMediaNotificationManager().addSession(mediaSession);
        mediaSession.setListener(new MediaSessionListener());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$onForegroundServiceStartNotAllowedException$4() {
        Listener listener = getListener();
        if (listener != null) {
            listener.onForegroundServiceStartNotAllowedException();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$onStartCommand$2(MediaSessionImpl mediaSessionImpl, Intent intent) {
        MediaSession.ControllerInfo mediaNotificationControllerInfo = mediaSessionImpl.getMediaNotificationControllerInfo();
        if (mediaNotificationControllerInfo == null) {
            mediaNotificationControllerInfo = createFallbackMediaButtonCaller(intent);
        }
        if (mediaSessionImpl.onMediaButtonEvent(mediaNotificationControllerInfo, intent)) {
            return;
        }
        Log.d(TAG, "Ignored unrecognized media button intent.");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$removeSession$1(MediaSession mediaSession) {
        getMediaNotificationManager().removeSession(mediaSession);
        mediaSession.clearListener();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$setMediaNotificationProvider$3(MediaNotification.Provider provider) {
        getMediaNotificationManager(provider).setMediaNotificationProvider(provider);
    }

    private void onForegroundServiceStartNotAllowedException() {
        this.mainHandler.post(new j3(this, 2));
    }

    public final void addSession(MediaSession mediaSession) {
        MediaSession mediaSession2;
        ac.b.p(mediaSession, "session must not be null");
        boolean z = true;
        ac.b.k(!mediaSession.isReleased(), "session is already released");
        synchronized (this.lock) {
            mediaSession2 = this.sessions.get(mediaSession.getId());
            if (mediaSession2 != null && mediaSession2 != mediaSession) {
                z = false;
            }
            ac.b.k(z, "Session ID should be unique");
            this.sessions.put(mediaSession.getId(), mediaSession);
        }
        if (mediaSession2 == null) {
            Util.postOrRun(this.mainHandler, new s2(this, mediaSession, 1));
        }
    }

    public final void clearListener() {
        synchronized (this.lock) {
            this.listener = null;
        }
    }

    public IBinder getServiceBinder() {
        MediaSessionServiceStub mediaSessionServiceStub = this.stub;
        mediaSessionServiceStub.getClass();
        return mediaSessionServiceStub.asBinder();
    }

    public final List<MediaSession> getSessions() {
        ArrayList arrayList;
        synchronized (this.lock) {
            arrayList = new ArrayList(this.sessions.values());
        }
        return arrayList;
    }

    public final boolean isPlaybackOngoing() {
        return getMediaNotificationManager().isStartedInForeground();
    }

    public final boolean isSessionAdded(MediaSession mediaSession) {
        boolean zContainsKey;
        synchronized (this.lock) {
            zContainsKey = this.sessions.containsKey(mediaSession.getId());
        }
        return zContainsKey;
    }

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        String action;
        MediaSession mediaSessionOnGetSession;
        if (intent == null || (action = intent.getAction()) == null) {
            return null;
        }
        if (action.equals(SERVICE_INTERFACE)) {
            return getServiceBinder();
        }
        if (!action.equals(MediaBrowserServiceCompat.SERVICE_INTERFACE) || (mediaSessionOnGetSession = onGetSession(MediaSession.ControllerInfo.createLegacyControllerInfo())) == null) {
            return null;
        }
        addSession(mediaSessionOnGetSession);
        return mediaSessionOnGetSession.getLegacyBrowserServiceBinder();
    }

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        this.stub = new MediaSessionServiceStub(this);
    }

    @Override // android.app.Service
    public void onDestroy() {
        super.onDestroy();
        MediaNotificationManager mediaNotificationManager = this.mediaNotificationManager;
        if (mediaNotificationManager != null) {
            mediaNotificationManager.disableUserEngagedTimeout();
        }
        MediaSessionServiceStub mediaSessionServiceStub = this.stub;
        if (mediaSessionServiceStub != null) {
            mediaSessionServiceStub.release();
            this.stub = null;
        }
    }

    public abstract MediaSession onGetSession(MediaSession.ControllerInfo controllerInfo);

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int i10, int i11) {
        String customAction;
        if (intent != null) {
            DefaultActionFactory actionFactory = getActionFactory();
            Uri data = intent.getData();
            MediaSession session = data != null ? MediaSession.getSession(data) : null;
            if (actionFactory.isMediaAction(intent)) {
                if (session == null) {
                    session = onGetSession(MediaSession.ControllerInfo.createLegacyControllerInfo());
                    if (session != null) {
                        addSession(session);
                    }
                }
                MediaSessionImpl impl = session.getImpl();
                impl.getApplicationHandler().post(new g(impl, intent, 11));
                return 1;
            }
            if (session != null && actionFactory.isCustomAction(intent) && (customAction = actionFactory.getCustomAction(intent)) != null) {
                getMediaNotificationManager().onCustomAction(session, customAction, actionFactory.getCustomActionExtras(intent));
            }
        }
        return 1;
    }

    @Override // android.app.Service
    public void onTaskRemoved(Intent intent) {
        if (isPlaybackOngoing() && isAnySessionPlaying()) {
            return;
        }
        pauseAllPlayersAndStopSelf();
    }

    @Deprecated
    public void onUpdateNotification(MediaSession mediaSession) {
        this.defaultMethodCalled = true;
    }

    public boolean onUpdateNotificationInternal(MediaSession mediaSession, boolean z) {
        try {
            onUpdateNotification(mediaSession, getMediaNotificationManager().shouldRunInForeground(z));
            return true;
        } catch (IllegalStateException e5) {
            if (Build.VERSION.SDK_INT < 31 || !Api31.instanceOfForegroundServiceStartNotAllowedException(e5)) {
                throw e5;
            }
            Log.e(TAG, "Failed to start foreground", e5);
            onForegroundServiceStartNotAllowedException();
            return false;
        }
    }

    public final void pauseAllPlayersAndStopSelf() {
        getMediaNotificationManager().disableUserEngagedTimeout();
        List<MediaSession> sessions = getSessions();
        for (int i10 = 0; i10 < sessions.size(); i10++) {
            sessions.get(i10).getPlayer().setPlayWhenReady(false);
        }
        stopSelf();
    }

    public final void removeSession(MediaSession mediaSession) {
        ac.b.p(mediaSession, "session must not be null");
        synchronized (this.lock) {
            ac.b.k(this.sessions.containsKey(mediaSession.getId()), "session not found");
            this.sessions.remove(mediaSession.getId());
        }
        Util.postOrRun(this.mainHandler, new s2(this, mediaSession, 0));
    }

    public final void setForegroundServiceTimeoutMs(long j10) {
        getMediaNotificationManager().setUserEngagedTimeoutMs(Util.constrainValue(j10, 0L, DEFAULT_FOREGROUND_SERVICE_TIMEOUT_MS));
    }

    public final void setListener(Listener listener) {
        synchronized (this.lock) {
            this.listener = listener;
        }
    }

    public final void setMediaNotificationProvider(MediaNotification.Provider provider) {
        provider.getClass();
        Util.postOrRun(this.mainHandler, new g(this, provider, 15));
    }

    public final void setShowNotificationForIdlePlayer(int i10) {
        getMediaNotificationManager().setShowNotificationForIdlePlayer(i10);
    }

    public final void triggerNotificationUpdate() {
        List<MediaSession> sessions = getSessions();
        for (int i10 = 0; i10 < sessions.size(); i10++) {
            onUpdateNotificationInternal(sessions.get(i10), false);
        }
    }

    private MediaNotificationManager getMediaNotificationManager(MediaNotification.Provider provider) {
        if (this.mediaNotificationManager == null) {
            if (provider == null) {
                ac.b.p(getBaseContext(), "Accessing service context before onCreate()");
                provider = new DefaultMediaNotificationProvider.Builder(getApplicationContext()).build();
            }
            this.mediaNotificationManager = new MediaNotificationManager(this, provider, getActionFactory());
        }
        return this.mediaNotificationManager;
    }

    public void onUpdateNotification(MediaSession mediaSession, boolean z) {
        onUpdateNotification(mediaSession);
        if (this.defaultMethodCalled) {
            getMediaNotificationManager().updateNotification(mediaSession, z);
        }
    }
}
