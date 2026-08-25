package androidx.media3.session;

import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.graphics.Rect;
import android.graphics.SurfaceTexture;
import android.media.session.MediaSession;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.Process;
import android.os.RemoteException;
import android.os.SystemClock;
import android.util.SparseArray;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.TextureView;
import androidx.media3.common.AdPlaybackState;
import androidx.media3.common.AudioAttributes;
import androidx.media3.common.BundleListRetriever;
import androidx.media3.common.C;
import androidx.media3.common.DeviceInfo;
import androidx.media3.common.FlagSet;
import androidx.media3.common.MediaItem;
import androidx.media3.common.MediaMetadata;
import androidx.media3.common.PlaybackException;
import androidx.media3.common.PlaybackParameters;
import androidx.media3.common.Player;
import androidx.media3.common.Rating;
import androidx.media3.common.Timeline;
import androidx.media3.common.TrackSelectionParameters;
import androidx.media3.common.Tracks;
import androidx.media3.common.VideoSize;
import androidx.media3.common.text.CueGroup;
import androidx.media3.common.util.BundleCollectionUtil;
import androidx.media3.common.util.Clock;
import androidx.media3.common.util.ListenerSet;
import androidx.media3.common.util.Log;
import androidx.media3.common.util.Size;
import androidx.media3.common.util.Util;
import androidx.media3.session.IMediaSession;
import androidx.media3.session.IMediaSessionService;
import androidx.media3.session.MediaController;
import androidx.media3.session.PlayerInfo;
import androidx.media3.session.SequencedFutureManager;
import androidx.media3.session.legacy.MediaBrowserCompat;
import com.google.common.collect.o3;
import com.google.common.collect.t3;
import j$.util.Objects;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

/* JADX INFO: loaded from: classes3.dex */
class MediaControllerImplBase implements MediaController.MediaControllerImpl {
    private static final long RELEASE_TIMEOUT_MS = 30000;
    public static final String TAG = "MCImplBase";
    private com.google.common.collect.l1 commandButtonsForMediaItemsMap;
    private SessionToken connectedToken;
    private final Bundle connectionHints;
    private final Context context;
    protected final MediaControllerStub controllerStub;
    private long currentPositionMs;
    private com.google.common.collect.h1 customLayoutOriginal;
    private final IBinder.DeathRecipient deathRecipient;
    private final Handler fallbackPlaybackInfoUpdateHandler;
    private final FlushCommandQueueHandler flushCommandQueueHandler;
    private IMediaSession iSession;
    private final MediaController instance;
    private Player.Commands intersectedPlayerCommands;
    private long lastSetPlayWhenReadyCalledTimeMs;
    private final ListenerSet<Player.Listener> listeners;
    private com.google.common.collect.h1 mediaButtonPreferencesOriginal;
    private final SparseArray<MediaController.ProgressListener> pendingCustomActionProgressListeners;
    private final t.f pendingMaskingSequencedFutureNumbers;
    private PlayerInfo pendingPlayerInfo;
    private android.media.session.MediaController platformController;
    private Player.Commands playerCommandsFromPlayer;
    private Player.Commands playerCommandsFromSession;
    private boolean released;
    private com.google.common.collect.h1 resolvedCustomLayout;
    private com.google.common.collect.h1 resolvedMediaButtonPreferences;
    protected final SequencedFutureManager sequencedFutureManager;
    private SessionServiceConnection serviceConnection;
    private PendingIntent sessionActivity;
    private Bundle sessionExtras;
    private final SurfaceCallback surfaceCallback;
    private final SessionToken token;
    private Surface videoSurface;
    private SurfaceHolder videoSurfaceHolder;
    private TextureView videoTextureView;
    private PlayerInfo playerInfo = PlayerInfo.DEFAULT;
    private Size surfaceSize = Size.UNKNOWN;
    private SessionCommands sessionCommands = SessionCommands.EMPTY;

    public class FlushCommandQueueHandler {
        private static final int MSG_FLUSH_COMMAND_QUEUE = 1;
        private final Handler handler;

        public FlushCommandQueueHandler(Looper looper) {
            this.handler = new Handler(looper, new a1(this, 0));
        }

        private void flushCommandQueue() {
            try {
                MediaControllerImplBase.this.iSession.flushCommandQueue(MediaControllerImplBase.this.controllerStub);
            } catch (RemoteException unused) {
                Log.w(MediaControllerImplBase.TAG, "Error in sending flushCommandQueue");
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean handleMessage(Message message) {
            if (message.what == 1) {
                flushCommandQueue();
            }
            return true;
        }

        public void release() {
            if (this.handler.hasMessages(1)) {
                flushCommandQueue();
            }
            this.handler.removeCallbacksAndMessages(null);
        }

        public void sendFlushCommandQueueMessage() {
            if (MediaControllerImplBase.this.iSession == null || this.handler.hasMessages(1)) {
                return;
            }
            this.handler.sendEmptyMessage(1);
        }
    }

    public static final class PeriodInfo {
        private final int index;
        private final long periodPositionUs;

        public PeriodInfo(int i10, long j10) {
            this.index = i10;
            this.periodPositionUs = j10;
        }

        public static /* synthetic */ int access$100(PeriodInfo periodInfo) {
            return periodInfo.index;
        }

        public static /* synthetic */ long access$200(PeriodInfo periodInfo) {
            return periodInfo.periodPositionUs;
        }
    }

    public interface RemoteSessionTask {
        void run(IMediaSession iMediaSession, int i10) throws RemoteException;
    }

    public class SessionServiceConnection implements ServiceConnection {
        private final Bundle connectionHints;

        public SessionServiceConnection(Bundle bundle) {
            this.connectionHints = bundle;
        }

        @Override // android.content.ServiceConnection
        public void onBindingDied(ComponentName componentName) {
            MediaController mediaControllerImplBase = MediaControllerImplBase.this.getInstance();
            MediaController mediaControllerImplBase2 = MediaControllerImplBase.this.getInstance();
            Objects.requireNonNull(mediaControllerImplBase2);
            mediaControllerImplBase.runOnApplicationLooper(new j3(mediaControllerImplBase2, 1));
        }

        @Override // android.content.ServiceConnection
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            MediaController mediaControllerImplBase;
            j3 j3Var;
            try {
                try {
                    if (MediaControllerImplBase.this.token.getPackageName().equals(componentName.getPackageName())) {
                        IMediaSessionService iMediaSessionServiceAsInterface = IMediaSessionService.Stub.asInterface(iBinder);
                        if (iMediaSessionServiceAsInterface != null) {
                            iMediaSessionServiceAsInterface.connect(MediaControllerImplBase.this.controllerStub, new ConnectionRequest(MediaControllerImplBase.this.getContext().getPackageName(), Process.myPid(), this.connectionHints, MediaControllerImplBase.this.instance.getMaxCommandsForMediaItems()).toBundle());
                            return;
                        } else {
                            Log.e(MediaControllerImplBase.TAG, "Service interface is missing.");
                            mediaControllerImplBase = MediaControllerImplBase.this.getInstance();
                            MediaController mediaControllerImplBase2 = MediaControllerImplBase.this.getInstance();
                            Objects.requireNonNull(mediaControllerImplBase2);
                            j3Var = new j3(mediaControllerImplBase2, 1);
                        }
                    } else {
                        Log.e(MediaControllerImplBase.TAG, "Expected connection to " + MediaControllerImplBase.this.token.getPackageName() + " but is connected to " + componentName);
                        mediaControllerImplBase = MediaControllerImplBase.this.getInstance();
                        MediaController mediaControllerImplBase3 = MediaControllerImplBase.this.getInstance();
                        Objects.requireNonNull(mediaControllerImplBase3);
                        j3Var = new j3(mediaControllerImplBase3, 1);
                    }
                } catch (RemoteException unused) {
                    Log.w(MediaControllerImplBase.TAG, "Service " + componentName + " has died prematurely");
                    mediaControllerImplBase = MediaControllerImplBase.this.getInstance();
                    MediaController mediaControllerImplBase4 = MediaControllerImplBase.this.getInstance();
                    Objects.requireNonNull(mediaControllerImplBase4);
                    j3Var = new j3(mediaControllerImplBase4, 1);
                }
                mediaControllerImplBase.runOnApplicationLooper(j3Var);
            } catch (Throwable th) {
                MediaController mediaControllerImplBase5 = MediaControllerImplBase.this.getInstance();
                MediaController mediaControllerImplBase6 = MediaControllerImplBase.this.getInstance();
                Objects.requireNonNull(mediaControllerImplBase6);
                mediaControllerImplBase5.runOnApplicationLooper(new j3(mediaControllerImplBase6, 1));
                throw th;
            }
        }

        @Override // android.content.ServiceConnection
        public void onServiceDisconnected(ComponentName componentName) {
            MediaController mediaControllerImplBase = MediaControllerImplBase.this.getInstance();
            MediaController mediaControllerImplBase2 = MediaControllerImplBase.this.getInstance();
            Objects.requireNonNull(mediaControllerImplBase2);
            mediaControllerImplBase.runOnApplicationLooper(new j3(mediaControllerImplBase2, 1));
        }
    }

    public class SurfaceCallback implements SurfaceHolder.Callback, TextureView.SurfaceTextureListener {
        private SurfaceCallback() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void lambda$onSurfaceTextureSizeChanged$1(int i10, int i11, IMediaSession iMediaSession, int i12) throws RemoteException {
            iMediaSession.onSurfaceSizeChanged(MediaControllerImplBase.this.controllerStub, i12, i10, i11);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void lambda$surfaceChanged$0(int i10, int i11, IMediaSession iMediaSession, int i12) throws RemoteException {
            iMediaSession.onSurfaceSizeChanged(MediaControllerImplBase.this.controllerStub, i12, i10, i11);
        }

        @Override // android.view.TextureView.SurfaceTextureListener
        public void onSurfaceTextureAvailable(SurfaceTexture surfaceTexture, int i10, int i11) {
            if (MediaControllerImplBase.this.videoTextureView == null || MediaControllerImplBase.this.videoTextureView.getSurfaceTexture() != surfaceTexture) {
                return;
            }
            MediaControllerImplBase.this.videoSurface = new Surface(surfaceTexture);
            MediaControllerImplBase mediaControllerImplBase = MediaControllerImplBase.this;
            mediaControllerImplBase.setVideoSurfaceWithSize(mediaControllerImplBase.videoSurface, i10, i11);
            MediaControllerImplBase.this.onSurfaceSizeChanged(i10, i11);
        }

        @Override // android.view.TextureView.SurfaceTextureListener
        public boolean onSurfaceTextureDestroyed(SurfaceTexture surfaceTexture) {
            if (MediaControllerImplBase.this.videoTextureView != null && MediaControllerImplBase.this.videoTextureView.getSurfaceTexture() == surfaceTexture) {
                MediaControllerImplBase.this.videoSurface = null;
                MediaControllerImplBase.this.setVideoSurfaceWithSize(null, 0, 0);
                MediaControllerImplBase.this.onSurfaceSizeChanged(0, 0);
            }
            return true;
        }

        @Override // android.view.TextureView.SurfaceTextureListener
        public void onSurfaceTextureSizeChanged(SurfaceTexture surfaceTexture, int i10, int i11) {
            if (MediaControllerImplBase.this.videoTextureView != null && MediaControllerImplBase.this.videoTextureView.getSurfaceTexture() == surfaceTexture && MediaControllerImplBase.this.isConnected()) {
                SessionToken sessionToken = MediaControllerImplBase.this.connectedToken;
                sessionToken.getClass();
                if (sessionToken.getInterfaceVersion() >= 8) {
                    MediaControllerImplBase.this.dispatchRemoteSessionTaskWithPlayerCommandAndWaitForFuture(new b1(this, i10, i11, 1));
                }
                MediaControllerImplBase.this.onSurfaceSizeChanged(i10, i11);
            }
        }

        @Override // android.view.TextureView.SurfaceTextureListener
        public void onSurfaceTextureUpdated(SurfaceTexture surfaceTexture) {
        }

        @Override // android.view.SurfaceHolder.Callback
        public void surfaceChanged(SurfaceHolder surfaceHolder, int i10, int i11, int i12) {
            if (MediaControllerImplBase.this.videoSurfaceHolder == surfaceHolder && MediaControllerImplBase.this.isConnected()) {
                SessionToken sessionToken = MediaControllerImplBase.this.connectedToken;
                sessionToken.getClass();
                if (sessionToken.getInterfaceVersion() >= 8) {
                    MediaControllerImplBase.this.dispatchRemoteSessionTaskWithPlayerCommandAndWaitForFuture(new b1(this, i11, i12, 0));
                }
                MediaControllerImplBase.this.onSurfaceSizeChanged(i11, i12);
            }
        }

        @Override // android.view.SurfaceHolder.Callback
        public void surfaceCreated(SurfaceHolder surfaceHolder) {
            if (MediaControllerImplBase.this.videoSurfaceHolder != surfaceHolder) {
                return;
            }
            MediaControllerImplBase.this.videoSurface = surfaceHolder.getSurface();
            Rect surfaceFrame = surfaceHolder.getSurfaceFrame();
            MediaControllerImplBase mediaControllerImplBase = MediaControllerImplBase.this;
            mediaControllerImplBase.setVideoSurfaceWithSize(mediaControllerImplBase.videoSurface, surfaceFrame.width(), surfaceFrame.height());
            MediaControllerImplBase.this.onSurfaceSizeChanged(surfaceFrame.width(), surfaceFrame.height());
        }

        @Override // android.view.SurfaceHolder.Callback
        public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
            if (MediaControllerImplBase.this.videoSurfaceHolder != surfaceHolder) {
                return;
            }
            MediaControllerImplBase.this.videoSurface = null;
            MediaControllerImplBase.this.setVideoSurfaceWithSize(null, 0, 0);
            MediaControllerImplBase.this.onSurfaceSizeChanged(0, 0);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public MediaControllerImplBase(Context context, MediaController mediaController, SessionToken sessionToken, Bundle bundle, Looper looper) {
        o3 o3Var = o3.f14078o;
        this.customLayoutOriginal = o3Var;
        this.mediaButtonPreferencesOriginal = o3Var;
        this.resolvedMediaButtonPreferences = o3Var;
        this.resolvedCustomLayout = o3Var;
        this.commandButtonsForMediaItemsMap = t3.f14114q;
        Player.Commands commands = Player.Commands.EMPTY;
        this.playerCommandsFromSession = commands;
        this.playerCommandsFromPlayer = commands;
        this.intersectedPlayerCommands = createIntersectedCommandsEnsuringCommandReleaseAvailable(commands, commands);
        this.listeners = new ListenerSet<>(looper, Clock.DEFAULT, new q(this, 11));
        this.fallbackPlaybackInfoUpdateHandler = new Handler(looper);
        this.instance = mediaController;
        ac.b.p(context, "context must not be null");
        ac.b.p(sessionToken, "token must not be null");
        this.context = context;
        this.sequencedFutureManager = new SequencedFutureManager();
        this.controllerStub = new MediaControllerStub(this);
        this.pendingMaskingSequencedFutureNumbers = new t.f(0);
        this.token = sessionToken;
        this.connectionHints = bundle;
        this.deathRecipient = new IBinder.DeathRecipient() { // from class: androidx.media3.session.v0
            @Override // android.os.IBinder.DeathRecipient
            public final void binderDied() {
                this.f4174a.lambda$new$1();
            }
        };
        this.surfaceCallback = new SurfaceCallback();
        this.sessionExtras = Bundle.EMPTY;
        this.serviceConnection = sessionToken.getType() != 0 ? new SessionServiceConnection(bundle) : null;
        this.flushCommandQueueHandler = new FlushCommandQueueHandler(looper);
        this.currentPositionMs = C.TIME_UNSET;
        this.lastSetPlayWhenReadyCalledTimeMs = C.TIME_UNSET;
        this.pendingCustomActionProgressListeners = new SparseArray<>();
    }

    private void addMediaItemsInternal(int i10, List<MediaItem> list) {
        if (list.isEmpty()) {
            return;
        }
        if (this.playerInfo.timeline.isEmpty()) {
            setMediaItemsInternal(list, -1, C.TIME_UNSET, false);
        } else {
            updatePlayerInfo(maskPlayerInfoForAddedItems(this.playerInfo, Math.min(i10, this.playerInfo.timeline.getWindowCount()), list, getCurrentPosition(), getContentPosition()), 0, null, null, this.playerInfo.timeline.isEmpty() ? 3 : null);
        }
    }

    private void clearSurfacesAndCallbacks() {
        TextureView textureView = this.videoTextureView;
        if (textureView != null) {
            textureView.setSurfaceTextureListener(null);
            this.videoTextureView = null;
        }
        SurfaceHolder surfaceHolder = this.videoSurfaceHolder;
        if (surfaceHolder != null) {
            surfaceHolder.removeCallback(this.surfaceCallback);
            this.videoSurfaceHolder = null;
        }
        if (this.videoSurface != null) {
            this.videoSurface = null;
        }
    }

    private static int convertRepeatModeForNavigation(int i10) {
        if (i10 == 1) {
            return 0;
        }
        return i10;
    }

    private static Player.Commands createIntersectedCommandsEnsuringCommandReleaseAvailable(Player.Commands commands, Player.Commands commands2) {
        Player.Commands commandsIntersect = MediaUtils.intersect(commands, commands2);
        return commandsIntersect.contains(32) ? commandsIntersect : commandsIntersect.buildUpon().add(32).build();
    }

    private static Timeline createMaskingTimeline(List<Timeline.Window> list, List<Timeline.Period> list2) {
        com.google.common.collect.e1 e1Var = new com.google.common.collect.e1(4);
        e1Var.d(list);
        o3 o3VarF = e1Var.f();
        com.google.common.collect.e1 e1Var2 = new com.google.common.collect.e1(4);
        e1Var2.d(list2);
        return new Timeline.RemotableTimeline(o3VarF, e1Var2.f(), MediaUtils.generateUnshuffledIndices(list.size()));
    }

    private static Timeline.Period createNewPeriod(int i10) {
        return new Timeline.Period().set(null, null, i10, C.TIME_UNSET, 0L, AdPlaybackState.NONE, true);
    }

    private static Timeline.Window createNewWindow(MediaItem mediaItem) {
        return new Timeline.Window().set(0, mediaItem, null, 0L, 0L, 0L, true, false, null, 0L, C.TIME_UNSET, -1, -1, 0L);
    }

    private com.google.common.util.concurrent.d1<SessionResult> dispatchRemoteSessionTask(IMediaSession iMediaSession, RemoteSessionTask remoteSessionTask, boolean z) {
        if (iMediaSession == null) {
            return xc.d.s0(new SessionResult(-4));
        }
        notifyPlatformControllerAboutMedia3ChangeRequest();
        SequencedFutureManager.SequencedFuture sequencedFutureCreateSequencedFuture = this.sequencedFutureManager.createSequencedFuture(new SessionResult(1));
        int sequenceNumber = sequencedFutureCreateSequencedFuture.getSequenceNumber();
        if (z) {
            if (this.pendingMaskingSequencedFutureNumbers.isEmpty()) {
                this.pendingPlayerInfo = this.playerInfo;
            }
            this.pendingMaskingSequencedFutureNumbers.add(Integer.valueOf(sequenceNumber));
        }
        try {
            remoteSessionTask.run(iMediaSession, sequenceNumber);
            return sequencedFutureCreateSequencedFuture;
        } catch (RemoteException e5) {
            Log.w(TAG, "Cannot connect to the service or the session is gone", e5);
            this.pendingMaskingSequencedFutureNumbers.remove(Integer.valueOf(sequenceNumber));
            this.sequencedFutureManager.setFutureResult(sequenceNumber, new SessionResult(-100));
            return sequencedFutureCreateSequencedFuture;
        }
    }

    private void dispatchRemoteSessionTaskWithPlayerCommand(RemoteSessionTask remoteSessionTask) {
        this.flushCommandQueueHandler.sendFlushCommandQueueMessage();
        dispatchRemoteSessionTask(this.iSession, remoteSessionTask, true);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void dispatchRemoteSessionTaskWithPlayerCommandAndWaitForFuture(RemoteSessionTask remoteSessionTask) {
        this.flushCommandQueueHandler.sendFlushCommandQueueMessage();
        com.google.common.util.concurrent.d1<SessionResult> d1VarDispatchRemoteSessionTask = dispatchRemoteSessionTask(this.iSession, remoteSessionTask, true);
        try {
            LegacyConversions.getFutureResult(d1VarDispatchRemoteSessionTask, C.DEFAULT_MAX_SEEK_TO_PREVIOUS_POSITION_MS);
        } catch (ExecutionException e5) {
            throw new IllegalStateException(e5);
        } catch (TimeoutException e6) {
            if (d1VarDispatchRemoteSessionTask instanceof SequencedFutureManager.SequencedFuture) {
                int sequenceNumber = ((SequencedFutureManager.SequencedFuture) d1VarDispatchRemoteSessionTask).getSequenceNumber();
                this.pendingMaskingSequencedFutureNumbers.remove(Integer.valueOf(sequenceNumber));
                this.sequencedFutureManager.setFutureResult(sequenceNumber, new SessionResult(-1));
            }
            Log.w(TAG, "Synchronous command takes too long on the session side.", e6);
        }
    }

    private com.google.common.util.concurrent.d1<SessionResult> dispatchRemoteSessionTaskWithSessionCommand(int i10, RemoteSessionTask remoteSessionTask) {
        return dispatchRemoteSessionTaskWithSessionCommandInternal(i10, null, remoteSessionTask);
    }

    private com.google.common.util.concurrent.d1<SessionResult> dispatchRemoteSessionTaskWithSessionCommandInternal(int i10, SessionCommand sessionCommand, RemoteSessionTask remoteSessionTask) {
        return dispatchRemoteSessionTask(sessionCommand != null ? getSessionInterfaceWithSessionCommandIfAble(sessionCommand) : getSessionInterfaceWithSessionCommandIfAble(i10), remoteSessionTask, false);
    }

    private static int getCurrentMediaItemIndexInternal(PlayerInfo playerInfo) {
        return playerInfo.sessionPositionInfo.positionInfo.mediaItemIndex;
    }

    private static int getNewPeriodIndexWithoutRemovedPeriods(Timeline timeline, int i10, int i11, int i12) {
        if (i10 == -1) {
            return i10;
        }
        while (i11 < i12) {
            Timeline.Window window = new Timeline.Window();
            timeline.getWindow(i11, window);
            i10 -= (window.lastPeriodIndex - window.firstPeriodIndex) + 1;
            i11++;
        }
        return i10;
    }

    private PeriodInfo getPeriodInfo(Timeline timeline, int i10, long j10) {
        if (timeline.isEmpty()) {
            return null;
        }
        Timeline.Window window = new Timeline.Window();
        Timeline.Period period = new Timeline.Period();
        if (i10 == -1 || i10 >= timeline.getWindowCount()) {
            i10 = timeline.getFirstWindowIndex(getShuffleModeEnabled());
            j10 = timeline.getWindow(i10, window).getDefaultPositionMs();
        }
        return getPeriodInfo(timeline, window, period, i10, Util.msToUs(j10));
    }

    private static Timeline.Period getPeriodWithNewWindowIndex(Timeline timeline, int i10, int i11) {
        Timeline.Period period = new Timeline.Period();
        timeline.getPeriod(i10, period);
        period.windowIndex = i11;
        return period;
    }

    private boolean isPlayerCommandAvailable(int i10) {
        if (this.intersectedPlayerCommands.contains(i10)) {
            return true;
        }
        androidx.fragment.app.a2.s(i10, "Controller isn't allowed to call command= ", TAG);
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$addMediaItem$31(MediaItem mediaItem, IMediaSession iMediaSession, int i10) throws RemoteException {
        iMediaSession.addMediaItem(this.controllerStub, i10, mediaItem.toBundleIncludeLocalConfiguration());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$addMediaItem$32(int i10, MediaItem mediaItem, IMediaSession iMediaSession, int i11) throws RemoteException {
        iMediaSession.addMediaItemWithIndex(this.controllerStub, i11, i10, mediaItem.toBundleIncludeLocalConfiguration());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$addMediaItems$33(List list, IMediaSession iMediaSession, int i10) throws RemoteException {
        iMediaSession.addMediaItems(this.controllerStub, i10, new BundleListRetriever(BundleCollectionUtil.toBundleList(list, new f(3))));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$addMediaItems$34(int i10, List list, IMediaSession iMediaSession, int i11) throws RemoteException {
        iMediaSession.addMediaItemsWithIndex(this.controllerStub, i11, i10, new BundleListRetriever(BundleCollectionUtil.toBundleList(list, new f(3))));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$clearMediaItems$37(IMediaSession iMediaSession, int i10) throws RemoteException {
        iMediaSession.clearMediaItems(this.controllerStub, i10);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$decreaseDeviceVolume$64(IMediaSession iMediaSession, int i10) throws RemoteException {
        iMediaSession.decreaseDeviceVolume(this.controllerStub, i10);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$decreaseDeviceVolume$65(int i10, Player.Listener listener) {
        listener.onDeviceVolumeChanged(i10, this.playerInfo.deviceMuted);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$decreaseDeviceVolume$66(int i10, IMediaSession iMediaSession, int i11) throws RemoteException {
        iMediaSession.decreaseDeviceVolumeWithFlags(this.controllerStub, i11, i10);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$decreaseDeviceVolume$67(int i10, Player.Listener listener) {
        listener.onDeviceVolumeChanged(i10, this.playerInfo.deviceMuted);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$increaseDeviceVolume$60(IMediaSession iMediaSession, int i10) throws RemoteException {
        iMediaSession.increaseDeviceVolume(this.controllerStub, i10);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$increaseDeviceVolume$61(int i10, Player.Listener listener) {
        listener.onDeviceVolumeChanged(i10, this.playerInfo.deviceMuted);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$increaseDeviceVolume$62(int i10, IMediaSession iMediaSession, int i11) throws RemoteException {
        iMediaSession.increaseDeviceVolumeWithFlags(this.controllerStub, i11, i10);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$increaseDeviceVolume$63(int i10, Player.Listener listener) {
        listener.onDeviceVolumeChanged(i10, this.playerInfo.deviceMuted);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$moveMediaItem$38(int i10, int i11, IMediaSession iMediaSession, int i12) throws RemoteException {
        iMediaSession.moveMediaItem(this.controllerStub, i12, i10, i11);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$moveMediaItems$39(int i10, int i11, int i12, IMediaSession iMediaSession, int i13) throws RemoteException {
        iMediaSession.moveMediaItems(this.controllerStub, i13, i10, i11, i12);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void lambda$mute$52(float f10, IMediaSession iMediaSession, int i10) throws RemoteException {
        SessionToken sessionToken = this.connectedToken;
        sessionToken.getClass();
        if (sessionToken.getInterfaceVersion() >= 6) {
            iMediaSession.mute(this.controllerStub, i10);
        } else {
            iMediaSession.setVolume(this.controllerStub, i10, f10);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$new$0(Player.Listener listener, FlagSet flagSet) {
        listener.onEvents(getInstance(), new Player.Events(flagSet));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$new$1() {
        MediaController mediaControllerImplBase = getInstance();
        MediaController mediaControllerImplBase2 = getInstance();
        Objects.requireNonNull(mediaControllerImplBase2);
        mediaControllerImplBase.runOnApplicationLooper(new j3(mediaControllerImplBase2, 1));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$notifyPlayerInfoListenersWithReasons$100(PlayerInfo playerInfo, Player.Listener listener) {
        listener.onDeviceVolumeChanged(playerInfo.deviceVolume, playerInfo.deviceMuted);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$notifyPlayerInfoListenersWithReasons$101(PlayerInfo playerInfo, Player.Listener listener) {
        listener.onVideoSizeChanged(playerInfo.videoSize);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$notifyPlayerInfoListenersWithReasons$102(PlayerInfo playerInfo, Player.Listener listener) {
        listener.onSeekBackIncrementChanged(playerInfo.seekBackIncrementMs);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$notifyPlayerInfoListenersWithReasons$103(PlayerInfo playerInfo, Player.Listener listener) {
        listener.onSeekForwardIncrementChanged(playerInfo.seekForwardIncrementMs);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$notifyPlayerInfoListenersWithReasons$104(PlayerInfo playerInfo, Player.Listener listener) {
        listener.onMaxSeekToPreviousPositionChanged(playerInfo.maxSeekToPreviousPositionMs);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$notifyPlayerInfoListenersWithReasons$105(PlayerInfo playerInfo, Player.Listener listener) {
        listener.onTrackSelectionParametersChanged(playerInfo.trackSelectionParameters);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$notifyPlayerInfoListenersWithReasons$78(PlayerInfo playerInfo, Integer num, Player.Listener listener) {
        listener.onTimelineChanged(playerInfo.timeline, num.intValue());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$notifyPlayerInfoListenersWithReasons$79(PlayerInfo playerInfo, Integer num, Player.Listener listener) {
        listener.onPositionDiscontinuity(playerInfo.oldPositionInfo, playerInfo.newPositionInfo, num.intValue());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$notifyPlayerInfoListenersWithReasons$80(MediaItem mediaItem, Integer num, Player.Listener listener) {
        listener.onMediaItemTransition(mediaItem, num.intValue());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$notifyPlayerInfoListenersWithReasons$83(PlayerInfo playerInfo, Player.Listener listener) {
        listener.onTracksChanged(playerInfo.currentTracks);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$notifyPlayerInfoListenersWithReasons$84(PlayerInfo playerInfo, Player.Listener listener) {
        listener.onMediaMetadataChanged(playerInfo.mediaMetadata);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$notifyPlayerInfoListenersWithReasons$85(PlayerInfo playerInfo, Player.Listener listener) {
        listener.onIsLoadingChanged(playerInfo.isLoading);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$notifyPlayerInfoListenersWithReasons$86(PlayerInfo playerInfo, Player.Listener listener) {
        listener.onPlaybackStateChanged(playerInfo.playbackState);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$notifyPlayerInfoListenersWithReasons$87(PlayerInfo playerInfo, Integer num, Player.Listener listener) {
        listener.onPlayWhenReadyChanged(playerInfo.playWhenReady, num.intValue());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$notifyPlayerInfoListenersWithReasons$88(PlayerInfo playerInfo, Player.Listener listener) {
        listener.onPlaybackSuppressionReasonChanged(playerInfo.playbackSuppressionReason);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$notifyPlayerInfoListenersWithReasons$89(PlayerInfo playerInfo, Player.Listener listener) {
        listener.onIsPlayingChanged(playerInfo.isPlaying);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$notifyPlayerInfoListenersWithReasons$90(PlayerInfo playerInfo, Player.Listener listener) {
        listener.onPlaybackParametersChanged(playerInfo.playbackParameters);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$notifyPlayerInfoListenersWithReasons$91(PlayerInfo playerInfo, Player.Listener listener) {
        listener.onRepeatModeChanged(playerInfo.repeatMode);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$notifyPlayerInfoListenersWithReasons$92(PlayerInfo playerInfo, Player.Listener listener) {
        listener.onShuffleModeEnabledChanged(playerInfo.shuffleModeEnabled);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$notifyPlayerInfoListenersWithReasons$93(PlayerInfo playerInfo, Player.Listener listener) {
        listener.onPlaylistMetadataChanged(playerInfo.playlistMetadata);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$notifyPlayerInfoListenersWithReasons$94(PlayerInfo playerInfo, Player.Listener listener) {
        listener.onVolumeChanged(playerInfo.volume);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$notifyPlayerInfoListenersWithReasons$95(PlayerInfo playerInfo, Player.Listener listener) {
        listener.onAudioAttributesChanged(playerInfo.audioAttributes);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$notifyPlayerInfoListenersWithReasons$96(PlayerInfo playerInfo, Player.Listener listener) {
        listener.onAudioSessionIdChanged(playerInfo.audioSessionId);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$notifyPlayerInfoListenersWithReasons$97(PlayerInfo playerInfo, Player.Listener listener) {
        listener.onCues(playerInfo.cueGroup.cues);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$notifyPlayerInfoListenersWithReasons$98(PlayerInfo playerInfo, Player.Listener listener) {
        listener.onCues(playerInfo.cueGroup);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$notifyPlayerInfoListenersWithReasons$99(PlayerInfo playerInfo, Player.Listener listener) {
        listener.onDeviceInfoChanged(playerInfo.deviceInfo);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$onAvailableCommandsChangedFromPlayer$114(Player.Listener listener) {
        listener.onAvailableCommandsChanged(this.intersectedPlayerCommands);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$onAvailableCommandsChangedFromPlayer$115(MediaController.Listener listener) {
        listener.onCustomLayoutChanged(getInstance(), this.resolvedCustomLayout);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$onAvailableCommandsChangedFromPlayer$116(MediaController.Listener listener) {
        listener.onMediaButtonPreferencesChanged(getInstance(), this.resolvedMediaButtonPreferences);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$onAvailableCommandsChangedFromSession$110(Player.Listener listener) {
        listener.onAvailableCommandsChanged(this.intersectedPlayerCommands);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$onAvailableCommandsChangedFromSession$111(SessionCommands sessionCommands, MediaController.Listener listener) {
        listener.onAvailableSessionCommandsChanged(getInstance(), sessionCommands);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$onAvailableCommandsChangedFromSession$112(MediaController.Listener listener) {
        listener.onCustomLayoutChanged(getInstance(), this.resolvedCustomLayout);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$onAvailableCommandsChangedFromSession$113(MediaController.Listener listener) {
        listener.onMediaButtonPreferencesChanged(getInstance(), this.resolvedMediaButtonPreferences);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$onCustomCommand$109(SessionCommand sessionCommand, Bundle bundle, int i10, MediaController.Listener listener) {
        com.google.common.util.concurrent.d1<SessionResult> d1VarOnCustomCommand = listener.onCustomCommand(getInstance(), sessionCommand, bundle);
        ac.b.p(d1VarOnCustomCommand, "ControllerCallback#onCustomCommand() must not return null");
        sendControllerResultWhenReady(i10, d1VarOnCustomCommand);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$onError$121(SessionError sessionError, MediaController.Listener listener) {
        listener.onError(getInstance(), sessionError);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$onExtrasChanged$119(Bundle bundle, boolean z, boolean z5, MediaController.Listener listener) {
        listener.onExtrasChanged(getInstance(), bundle);
        if (z) {
            listener.onCustomLayoutChanged(getInstance(), this.resolvedCustomLayout);
        }
        if (z5) {
            listener.onMediaButtonPreferencesChanged(getInstance(), this.resolvedMediaButtonPreferences);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$onSetCustomLayout$117(boolean z, boolean z5, int i10, MediaController.Listener listener) {
        com.google.common.util.concurrent.d1<SessionResult> d1VarOnSetCustomLayout = listener.onSetCustomLayout(getInstance(), this.resolvedCustomLayout);
        ac.b.p(d1VarOnSetCustomLayout, "MediaController.Listener#onSetCustomLayout() must not return null");
        if (z) {
            listener.onCustomLayoutChanged(getInstance(), this.resolvedCustomLayout);
        }
        if (z5) {
            listener.onMediaButtonPreferencesChanged(getInstance(), this.resolvedMediaButtonPreferences);
        }
        sendControllerResultWhenReady(i10, d1VarOnSetCustomLayout);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$onSetMediaButtonPreferences$118(boolean z, boolean z5, int i10, MediaController.Listener listener) {
        com.google.common.util.concurrent.d1<SessionResult> d1VarOnSetCustomLayout = listener.onSetCustomLayout(getInstance(), this.resolvedCustomLayout);
        ac.b.p(d1VarOnSetCustomLayout, "MediaController.Listener#onSetCustomLayout() must not return null");
        if (z) {
            listener.onCustomLayoutChanged(getInstance(), this.resolvedCustomLayout);
        }
        if (z5) {
            listener.onMediaButtonPreferencesChanged(getInstance(), this.resolvedMediaButtonPreferences);
        }
        sendControllerResultWhenReady(i10, d1VarOnSetCustomLayout);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$onSetSessionActivity$120(PendingIntent pendingIntent, MediaController.Listener listener) {
        listener.onSessionActivityChanged(getInstance(), pendingIntent);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$pause$6(IMediaSession iMediaSession, int i10) throws RemoteException {
        iMediaSession.pause(this.controllerStub, i10);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$play$5(IMediaSession iMediaSession, int i10) throws RemoteException {
        iMediaSession.play(this.controllerStub, i10);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$prepare$7(IMediaSession iMediaSession, int i10) throws RemoteException {
        iMediaSession.prepare(this.controllerStub, i10);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$release$4() {
        SessionServiceConnection sessionServiceConnection = this.serviceConnection;
        if (sessionServiceConnection != null) {
            this.context.unbindService(sessionServiceConnection);
            this.serviceConnection = null;
        }
        this.controllerStub.destroy();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$removeMediaItem$35(int i10, IMediaSession iMediaSession, int i11) throws RemoteException {
        iMediaSession.removeMediaItem(this.controllerStub, i11, i10);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$removeMediaItems$36(int i10, int i11, IMediaSession iMediaSession, int i12) throws RemoteException {
        iMediaSession.removeMediaItems(this.controllerStub, i12, i10, i11);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void lambda$replaceMediaItem$40(int i10, MediaItem mediaItem, IMediaSession iMediaSession, int i11) throws RemoteException {
        SessionToken sessionToken = this.connectedToken;
        sessionToken.getClass();
        if (sessionToken.getInterfaceVersion() >= 2) {
            iMediaSession.replaceMediaItem(this.controllerStub, i11, i10, mediaItem.toBundleIncludeLocalConfiguration());
        } else {
            iMediaSession.addMediaItemWithIndex(this.controllerStub, i11, i10 + 1, mediaItem.toBundleIncludeLocalConfiguration());
            iMediaSession.removeMediaItem(this.controllerStub, i11, i10);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void lambda$replaceMediaItems$41(List list, int i10, int i11, IMediaSession iMediaSession, int i12) throws RemoteException {
        BundleListRetriever bundleListRetriever = new BundleListRetriever(BundleCollectionUtil.toBundleList(list, new f(3)));
        SessionToken sessionToken = this.connectedToken;
        sessionToken.getClass();
        if (sessionToken.getInterfaceVersion() >= 2) {
            iMediaSession.replaceMediaItems(this.controllerStub, i12, i10, i11, bundleListRetriever);
        } else {
            iMediaSession.addMediaItemsWithIndex(this.controllerStub, i12, i11, bundleListRetriever);
            iMediaSession.removeMediaItems(this.controllerStub, i12, i10, i11);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$seekBack$12(IMediaSession iMediaSession, int i10) throws RemoteException {
        iMediaSession.seekBack(this.controllerStub, i10);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$seekForward$13(IMediaSession iMediaSession, int i10) throws RemoteException {
        iMediaSession.seekForward(this.controllerStub, i10);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$seekTo$10(long j10, IMediaSession iMediaSession, int i10) throws RemoteException {
        iMediaSession.seekTo(this.controllerStub, i10, j10);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$seekTo$11(int i10, long j10, IMediaSession iMediaSession, int i11) throws RemoteException {
        iMediaSession.seekToWithMediaItemIndex(this.controllerStub, i11, i10, j10);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$seekToDefaultPosition$8(IMediaSession iMediaSession, int i10) throws RemoteException {
        iMediaSession.seekToDefaultPosition(this.controllerStub, i10);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$seekToDefaultPosition$9(int i10, IMediaSession iMediaSession, int i11) throws RemoteException {
        iMediaSession.seekToDefaultPositionWithMediaItemIndex(this.controllerStub, i11, i10);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$seekToNext$45(IMediaSession iMediaSession, int i10) throws RemoteException {
        iMediaSession.seekToNext(this.controllerStub, i10);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$seekToNextMediaItem$43(IMediaSession iMediaSession, int i10) throws RemoteException {
        iMediaSession.seekToNextMediaItem(this.controllerStub, i10);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$seekToPrevious$44(IMediaSession iMediaSession, int i10) throws RemoteException {
        iMediaSession.seekToPrevious(this.controllerStub, i10);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$seekToPreviousMediaItem$42(IMediaSession iMediaSession, int i10) throws RemoteException {
        iMediaSession.seekToPreviousMediaItem(this.controllerStub, i10);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    public /* synthetic */ void lambda$sendControllerResultWhenReady$108(com.google.common.util.concurrent.d1 d1Var, int i10) {
        SessionResult sessionResult;
        try {
            sessionResult = (SessionResult) d1Var.get();
            ac.b.p(sessionResult, "SessionResult must not be null");
        } catch (InterruptedException e5) {
            e = e5;
            Log.w(TAG, "Session operation failed", e);
            sessionResult = new SessionResult(-1);
        } catch (CancellationException e6) {
            Log.w(TAG, "Session operation cancelled", e6);
            sessionResult = new SessionResult(1);
        } catch (ExecutionException e10) {
            e = e10;
            Log.w(TAG, "Session operation failed", e);
            sessionResult = new SessionResult(-1);
        }
        sendControllerResult(i10, sessionResult);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$sendCustomCommand$21(SessionCommand sessionCommand, Bundle bundle, IMediaSession iMediaSession, int i10) throws RemoteException {
        iMediaSession.onCustomCommand(this.controllerStub, i10, sessionCommand.toBundle(), bundle);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$sendCustomCommand$22(MediaController.ProgressListener progressListener, SessionCommand sessionCommand, Bundle bundle, IMediaSession iMediaSession, int i10) throws RemoteException {
        if (progressListener != null) {
            this.pendingCustomActionProgressListeners.put(i10, progressListener);
        }
        iMediaSession.onCustomCommandWithProgressUpdate(this.controllerStub, i10, sessionCommand.toBundle(), bundle, progressListener != null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$setAudioAttributes$72(AudioAttributes audioAttributes, boolean z, IMediaSession iMediaSession, int i10) throws RemoteException {
        iMediaSession.setAudioAttributes(this.controllerStub, i10, audioAttributes.toBundle(), z);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$setDeviceMuted$68(boolean z, IMediaSession iMediaSession, int i10) throws RemoteException {
        iMediaSession.setDeviceMuted(this.controllerStub, i10, z);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$setDeviceMuted$69(boolean z, Player.Listener listener) {
        listener.onDeviceVolumeChanged(this.playerInfo.deviceVolume, z);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$setDeviceMuted$70(boolean z, int i10, IMediaSession iMediaSession, int i11) throws RemoteException {
        iMediaSession.setDeviceMutedWithFlags(this.controllerStub, i11, z, i10);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$setDeviceMuted$71(boolean z, Player.Listener listener) {
        listener.onDeviceVolumeChanged(this.playerInfo.deviceVolume, z);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$setDeviceVolume$56(int i10, IMediaSession iMediaSession, int i11) throws RemoteException {
        iMediaSession.setDeviceVolume(this.controllerStub, i11, i10);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$setDeviceVolume$57(int i10, Player.Listener listener) {
        listener.onDeviceVolumeChanged(i10, this.playerInfo.deviceMuted);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$setDeviceVolume$58(int i10, int i11, IMediaSession iMediaSession, int i12) throws RemoteException {
        iMediaSession.setDeviceVolumeWithFlags(this.controllerStub, i12, i10, i11);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$setDeviceVolume$59(int i10, Player.Listener listener) {
        listener.onDeviceVolumeChanged(i10, this.playerInfo.deviceMuted);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$setFutureResult$106() {
        PlayerInfo playerInfo = this.pendingPlayerInfo;
        if (playerInfo != null) {
            onPlayerInfoChanged(playerInfo, PlayerInfo.BundlingExclusions.NONE);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$setFutureResult$107(int i10) {
        this.pendingMaskingSequencedFutureNumbers.remove(Integer.valueOf(i10));
        this.pendingCustomActionProgressListeners.delete(i10);
        SessionToken sessionToken = this.connectedToken;
        if (sessionToken == null || sessionToken.getInterfaceVersion() >= 5 || !this.pendingMaskingSequencedFutureNumbers.isEmpty()) {
            return;
        }
        this.fallbackPlaybackInfoUpdateHandler.postDelayed(new m0(this, 0), 500L);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$setMediaItem$23(MediaItem mediaItem, IMediaSession iMediaSession, int i10) throws RemoteException {
        iMediaSession.setMediaItem(this.controllerStub, i10, mediaItem.toBundleIncludeLocalConfiguration());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$setMediaItem$24(MediaItem mediaItem, long j10, IMediaSession iMediaSession, int i10) throws RemoteException {
        iMediaSession.setMediaItemWithStartPosition(this.controllerStub, i10, mediaItem.toBundleIncludeLocalConfiguration(), j10);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$setMediaItem$25(MediaItem mediaItem, boolean z, IMediaSession iMediaSession, int i10) throws RemoteException {
        iMediaSession.setMediaItemWithResetPosition(this.controllerStub, i10, mediaItem.toBundleIncludeLocalConfiguration(), z);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$setMediaItems$26(List list, IMediaSession iMediaSession, int i10) throws RemoteException {
        iMediaSession.setMediaItems(this.controllerStub, i10, new BundleListRetriever(BundleCollectionUtil.toBundleList(list, new f(3))));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$setMediaItems$27(List list, boolean z, IMediaSession iMediaSession, int i10) throws RemoteException {
        iMediaSession.setMediaItemsWithResetPosition(this.controllerStub, i10, new BundleListRetriever(BundleCollectionUtil.toBundleList(list, new f(3))), z);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$setMediaItems$28(List list, int i10, long j10, IMediaSession iMediaSession, int i11) throws RemoteException {
        iMediaSession.setMediaItemsWithStartIndex(this.controllerStub, i11, new BundleListRetriever(BundleCollectionUtil.toBundleList(list, new f(3))), i10, j10);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$setPlayWhenReady$14(boolean z, IMediaSession iMediaSession, int i10) throws RemoteException {
        iMediaSession.setPlayWhenReady(this.controllerStub, i10, z);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$setPlaybackParameters$15(PlaybackParameters playbackParameters, IMediaSession iMediaSession, int i10) throws RemoteException {
        iMediaSession.setPlaybackParameters(this.controllerStub, i10, playbackParameters.toBundle());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$setPlaybackSpeed$17(float f10, IMediaSession iMediaSession, int i10) throws RemoteException {
        iMediaSession.setPlaybackSpeed(this.controllerStub, i10, f10);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$setPlaylistMetadata$29(MediaMetadata mediaMetadata, IMediaSession iMediaSession, int i10) throws RemoteException {
        iMediaSession.setPlaylistMetadata(this.controllerStub, i10, mediaMetadata.toBundle());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$setRating$19(String str, Rating rating, IMediaSession iMediaSession, int i10) throws RemoteException {
        iMediaSession.setRatingWithMediaId(this.controllerStub, i10, str, rating.toBundle());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$setRating$20(Rating rating, IMediaSession iMediaSession, int i10) throws RemoteException {
        iMediaSession.setRating(this.controllerStub, i10, rating.toBundle());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$setRepeatMode$46(int i10, IMediaSession iMediaSession, int i11) throws RemoteException {
        iMediaSession.setRepeatMode(this.controllerStub, i11, i10);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$setShuffleModeEnabled$48(boolean z, IMediaSession iMediaSession, int i10) throws RemoteException {
        iMediaSession.setShuffleModeEnabled(this.controllerStub, i10, z);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$setTrackSelectionParameters$76(TrackSelectionParameters trackSelectionParameters, IMediaSession iMediaSession, int i10) throws RemoteException {
        iMediaSession.setTrackSelectionParameters(this.controllerStub, i10, trackSelectionParameters.toBundle());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$setVideoSurfaceWithSize$74(Surface surface, int i10, int i11, IMediaSession iMediaSession, int i12) throws RemoteException {
        iMediaSession.setVideoSurfaceWithSize(this.controllerStub, i12, surface, i10, i11);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$setVideoSurfaceWithSize$75(Surface surface, IMediaSession iMediaSession, int i10) throws RemoteException {
        iMediaSession.setVideoSurface(this.controllerStub, i10, surface);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$setVolume$50(float f10, IMediaSession iMediaSession, int i10) throws RemoteException {
        iMediaSession.setVolume(this.controllerStub, i10, f10);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$stop$2(IMediaSession iMediaSession, int i10) throws RemoteException {
        iMediaSession.stop(this.controllerStub, i10);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void lambda$unmute$54(float f10, IMediaSession iMediaSession, int i10) throws RemoteException {
        SessionToken sessionToken = this.connectedToken;
        sessionToken.getClass();
        if (sessionToken.getInterfaceVersion() >= 6) {
            iMediaSession.unmute(this.controllerStub, i10);
        } else {
            iMediaSession.setVolume(this.controllerStub, i10, f10);
        }
    }

    private static PlayerInfo maskPlayerInfoForAddedItems(PlayerInfo playerInfo, int i10, List<MediaItem> list, long j10, long j11) {
        int size;
        Timeline timeline = playerInfo.timeline;
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        int size2 = 0;
        for (int i11 = 0; i11 < timeline.getWindowCount(); i11++) {
            arrayList.add(timeline.getWindow(i11, new Timeline.Window()));
        }
        for (int i12 = 0; i12 < list.size(); i12++) {
            arrayList.add(i12 + i10, createNewWindow(list.get(i12)));
        }
        rebuildPeriods(timeline, arrayList, arrayList2);
        Timeline timelineCreateMaskingTimeline = createMaskingTimeline(arrayList, arrayList2);
        if (playerInfo.timeline.isEmpty()) {
            size = 0;
        } else {
            int i13 = playerInfo.sessionPositionInfo.positionInfo.mediaItemIndex;
            size2 = i13 >= i10 ? list.size() + i13 : i13;
            int i14 = playerInfo.sessionPositionInfo.positionInfo.periodIndex;
            size = i14 >= i10 ? list.size() + i14 : i14;
        }
        return maskTimelineAndPositionInfo(playerInfo, timelineCreateMaskingTimeline, size2, size, j10, j11, 5);
    }

    /* JADX WARN: Removed duplicated region for block: B:33:0x0094  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x0106  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static androidx.media3.session.PlayerInfo maskPlayerInfoForRemovedItems(androidx.media3.session.PlayerInfo r34, int r35, int r36, boolean r37, long r38, long r40) {
        /*
            Method dump skipped, instruction units count: 295
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.media3.session.MediaControllerImplBase.maskPlayerInfoForRemovedItems(androidx.media3.session.PlayerInfo, int, int, boolean, long, long):androidx.media3.session.PlayerInfo");
    }

    private PlayerInfo maskPositionInfo(PlayerInfo playerInfo, Timeline timeline, PeriodInfo periodInfo) {
        int i10 = playerInfo.sessionPositionInfo.positionInfo.periodIndex;
        int i11 = periodInfo.index;
        Timeline.Period period = new Timeline.Period();
        timeline.getPeriod(i10, period);
        Timeline.Period period2 = new Timeline.Period();
        timeline.getPeriod(i11, period2);
        boolean z = i10 != i11;
        long j10 = periodInfo.periodPositionUs;
        long jMsToUs = Util.msToUs(getCurrentPosition()) - period.getPositionInWindowUs();
        if (!z && j10 == jMsToUs) {
            return playerInfo;
        }
        ac.b.s(playerInfo.sessionPositionInfo.positionInfo.adGroupIndex == -1);
        Player.PositionInfo positionInfo = new Player.PositionInfo(null, period.windowIndex, playerInfo.sessionPositionInfo.positionInfo.mediaItem, null, i10, Util.usToMs(period.positionInWindowUs + jMsToUs), Util.usToMs(period.positionInWindowUs + jMsToUs), -1, -1);
        timeline.getPeriod(i11, period2);
        Timeline.Window window = new Timeline.Window();
        timeline.getWindow(period2.windowIndex, window);
        long jUsToMs = Util.usToMs(period2.positionInWindowUs + j10);
        Player.PositionInfo positionInfo2 = new Player.PositionInfo(null, period2.windowIndex, window.mediaItem, null, i11, jUsToMs, jUsToMs, -1, -1);
        PlayerInfo playerInfoCopyWithPositionInfos = playerInfo.copyWithPositionInfos(positionInfo, positionInfo2, 1);
        if (z || j10 < jMsToUs) {
            return playerInfoCopyWithPositionInfos.copyWithSessionPositionInfo(new SessionPositionInfo(positionInfo2, false, SystemClock.elapsedRealtime(), window.getDurationMs(), jUsToMs, MediaUtils.calculateBufferedPercentage(jUsToMs, window.getDurationMs()), 0L, C.TIME_UNSET, C.TIME_UNSET, jUsToMs));
        }
        long jMax = Math.max(0L, Util.msToUs(playerInfoCopyWithPositionInfos.sessionPositionInfo.totalBufferedDurationMs) - (j10 - jMsToUs));
        long jUsToMs2 = Util.usToMs(period2.positionInWindowUs + j10 + jMax);
        return playerInfoCopyWithPositionInfos.copyWithSessionPositionInfo(new SessionPositionInfo(positionInfo2, false, SystemClock.elapsedRealtime(), window.getDurationMs(), jUsToMs2, MediaUtils.calculateBufferedPercentage(jUsToMs2, window.getDurationMs()), Util.usToMs(jMax), C.TIME_UNSET, C.TIME_UNSET, jUsToMs2));
    }

    private static PlayerInfo maskTimelineAndPositionInfo(PlayerInfo playerInfo, Timeline timeline, int i10, int i11, long j10, long j11, int i12) {
        MediaItem mediaItem = timeline.getWindow(i10, new Timeline.Window()).mediaItem;
        Player.PositionInfo positionInfo = playerInfo.sessionPositionInfo.positionInfo;
        Player.PositionInfo positionInfo2 = new Player.PositionInfo(null, i10, mediaItem, null, i11, j10, j11, positionInfo.adGroupIndex, positionInfo.adIndexInAdGroup);
        boolean z = playerInfo.sessionPositionInfo.isPlayingAd;
        long jElapsedRealtime = SystemClock.elapsedRealtime();
        SessionPositionInfo sessionPositionInfo = playerInfo.sessionPositionInfo;
        return maskTimelineAndPositionInfo(playerInfo, timeline, positionInfo2, new SessionPositionInfo(positionInfo2, z, jElapsedRealtime, sessionPositionInfo.durationMs, sessionPositionInfo.bufferedPositionMs, sessionPositionInfo.bufferedPercentage, sessionPositionInfo.totalBufferedDurationMs, sessionPositionInfo.currentLiveOffsetMs, sessionPositionInfo.contentDurationMs, sessionPositionInfo.contentBufferedPositionMs), i12);
    }

    private void moveMediaItemsInternal(int i10, int i11, int i12) {
        int i13;
        int i14;
        Timeline timeline = this.playerInfo.timeline;
        int windowCount = timeline.getWindowCount();
        int iMin = Math.min(i11, windowCount);
        int i15 = iMin - i10;
        int iMin2 = Math.min(i12, windowCount - i15);
        if (i10 >= windowCount || i10 == iMin || i10 == iMin2) {
            return;
        }
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        for (int i16 = 0; i16 < windowCount; i16++) {
            arrayList.add(timeline.getWindow(i16, new Timeline.Window()));
        }
        Util.moveItems(arrayList, i10, iMin, iMin2);
        rebuildPeriods(timeline, arrayList, arrayList2);
        Timeline timelineCreateMaskingTimeline = createMaskingTimeline(arrayList, arrayList2);
        if (timelineCreateMaskingTimeline.isEmpty()) {
            return;
        }
        int currentMediaItemIndex = getCurrentMediaItemIndex();
        if (currentMediaItemIndex >= i10 && currentMediaItemIndex < iMin) {
            i14 = (currentMediaItemIndex - i10) + iMin2;
        } else if (iMin <= currentMediaItemIndex && iMin2 > currentMediaItemIndex) {
            i14 = currentMediaItemIndex - i15;
        } else {
            if (iMin <= currentMediaItemIndex || iMin2 > currentMediaItemIndex) {
                i13 = currentMediaItemIndex;
                Timeline.Window window = new Timeline.Window();
                updatePlayerInfo(maskTimelineAndPositionInfo(this.playerInfo, timelineCreateMaskingTimeline, i13, timelineCreateMaskingTimeline.getWindow(i13, window).firstPeriodIndex + (this.playerInfo.sessionPositionInfo.positionInfo.periodIndex - timeline.getWindow(currentMediaItemIndex, window).firstPeriodIndex), getCurrentPosition(), getContentPosition(), 5), 0, null, null, null);
            }
            i14 = currentMediaItemIndex + i15;
        }
        i13 = i14;
        Timeline.Window window2 = new Timeline.Window();
        updatePlayerInfo(maskTimelineAndPositionInfo(this.playerInfo, timelineCreateMaskingTimeline, i13, timelineCreateMaskingTimeline.getWindow(i13, window2).firstPeriodIndex + (this.playerInfo.sessionPositionInfo.positionInfo.periodIndex - timeline.getWindow(currentMediaItemIndex, window2).firstPeriodIndex), getCurrentPosition(), getContentPosition(), 5), 0, null, null, null);
    }

    private void notifyPlayerInfoListenersWithReasons(PlayerInfo playerInfo, final PlayerInfo playerInfo2, final Integer num, final Integer num2, final Integer num3, Integer num4) {
        if (num != null) {
            final int i10 = 0;
            this.listeners.queueEvent(0, new ListenerSet.Event() { // from class: androidx.media3.session.k0
                @Override // androidx.media3.common.util.ListenerSet.Event
                public final void invoke(Object obj) {
                    switch (i10) {
                        case 0:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$78(playerInfo2, num, (Player.Listener) obj);
                            break;
                        case 1:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$79(playerInfo2, num, (Player.Listener) obj);
                            break;
                        default:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$87(playerInfo2, num, (Player.Listener) obj);
                            break;
                    }
                }
            });
        }
        if (num3 != null) {
            final int i11 = 1;
            this.listeners.queueEvent(11, new ListenerSet.Event() { // from class: androidx.media3.session.k0
                @Override // androidx.media3.common.util.ListenerSet.Event
                public final void invoke(Object obj) {
                    switch (i11) {
                        case 0:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$78(playerInfo2, num3, (Player.Listener) obj);
                            break;
                        case 1:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$79(playerInfo2, num3, (Player.Listener) obj);
                            break;
                        default:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$87(playerInfo2, num3, (Player.Listener) obj);
                            break;
                    }
                }
            });
        }
        MediaItem currentMediaItem = playerInfo2.getCurrentMediaItem();
        if (num4 != null) {
            this.listeners.queueEvent(1, new k(currentMediaItem, num4, 6));
        }
        PlaybackException playbackException = playerInfo.playerError;
        PlaybackException playbackException2 = playerInfo2.playerError;
        if (playbackException != playbackException2 && (playbackException == null || !playbackException.errorInfoEquals(playbackException2))) {
            this.listeners.queueEvent(10, new n0(0, playbackException2));
            if (playbackException2 != null) {
                this.listeners.queueEvent(10, new n0(1, playbackException2));
            }
        }
        if (!playerInfo.currentTracks.equals(playerInfo2.currentTracks)) {
            final int i12 = 18;
            this.listeners.queueEvent(2, new ListenerSet.Event() { // from class: androidx.media3.session.l0
                @Override // androidx.media3.common.util.ListenerSet.Event
                public final void invoke(Object obj) {
                    switch (i12) {
                        case 0:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$88(playerInfo2, (Player.Listener) obj);
                            break;
                        case 1:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$89(playerInfo2, (Player.Listener) obj);
                            break;
                        case 2:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$90(playerInfo2, (Player.Listener) obj);
                            break;
                        case 3:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$91(playerInfo2, (Player.Listener) obj);
                            break;
                        case 4:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$92(playerInfo2, (Player.Listener) obj);
                            break;
                        case 5:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$93(playerInfo2, (Player.Listener) obj);
                            break;
                        case 6:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$94(playerInfo2, (Player.Listener) obj);
                            break;
                        case 7:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$95(playerInfo2, (Player.Listener) obj);
                            break;
                        case 8:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$96(playerInfo2, (Player.Listener) obj);
                            break;
                        case 9:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$97(playerInfo2, (Player.Listener) obj);
                            break;
                        case 10:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$98(playerInfo2, (Player.Listener) obj);
                            break;
                        case 11:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$99(playerInfo2, (Player.Listener) obj);
                            break;
                        case 12:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$100(playerInfo2, (Player.Listener) obj);
                            break;
                        case 13:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$101(playerInfo2, (Player.Listener) obj);
                            break;
                        case 14:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$102(playerInfo2, (Player.Listener) obj);
                            break;
                        case 15:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$103(playerInfo2, (Player.Listener) obj);
                            break;
                        case 16:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$104(playerInfo2, (Player.Listener) obj);
                            break;
                        case 17:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$105(playerInfo2, (Player.Listener) obj);
                            break;
                        case 18:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$83(playerInfo2, (Player.Listener) obj);
                            break;
                        case 19:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$84(playerInfo2, (Player.Listener) obj);
                            break;
                        case 20:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$85(playerInfo2, (Player.Listener) obj);
                            break;
                        default:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$86(playerInfo2, (Player.Listener) obj);
                            break;
                    }
                }
            });
        }
        if (!playerInfo.mediaMetadata.equals(playerInfo2.mediaMetadata)) {
            final int i13 = 19;
            this.listeners.queueEvent(14, new ListenerSet.Event() { // from class: androidx.media3.session.l0
                @Override // androidx.media3.common.util.ListenerSet.Event
                public final void invoke(Object obj) {
                    switch (i13) {
                        case 0:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$88(playerInfo2, (Player.Listener) obj);
                            break;
                        case 1:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$89(playerInfo2, (Player.Listener) obj);
                            break;
                        case 2:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$90(playerInfo2, (Player.Listener) obj);
                            break;
                        case 3:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$91(playerInfo2, (Player.Listener) obj);
                            break;
                        case 4:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$92(playerInfo2, (Player.Listener) obj);
                            break;
                        case 5:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$93(playerInfo2, (Player.Listener) obj);
                            break;
                        case 6:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$94(playerInfo2, (Player.Listener) obj);
                            break;
                        case 7:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$95(playerInfo2, (Player.Listener) obj);
                            break;
                        case 8:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$96(playerInfo2, (Player.Listener) obj);
                            break;
                        case 9:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$97(playerInfo2, (Player.Listener) obj);
                            break;
                        case 10:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$98(playerInfo2, (Player.Listener) obj);
                            break;
                        case 11:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$99(playerInfo2, (Player.Listener) obj);
                            break;
                        case 12:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$100(playerInfo2, (Player.Listener) obj);
                            break;
                        case 13:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$101(playerInfo2, (Player.Listener) obj);
                            break;
                        case 14:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$102(playerInfo2, (Player.Listener) obj);
                            break;
                        case 15:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$103(playerInfo2, (Player.Listener) obj);
                            break;
                        case 16:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$104(playerInfo2, (Player.Listener) obj);
                            break;
                        case 17:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$105(playerInfo2, (Player.Listener) obj);
                            break;
                        case 18:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$83(playerInfo2, (Player.Listener) obj);
                            break;
                        case 19:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$84(playerInfo2, (Player.Listener) obj);
                            break;
                        case 20:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$85(playerInfo2, (Player.Listener) obj);
                            break;
                        default:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$86(playerInfo2, (Player.Listener) obj);
                            break;
                    }
                }
            });
        }
        if (playerInfo.isLoading != playerInfo2.isLoading) {
            final int i14 = 20;
            this.listeners.queueEvent(3, new ListenerSet.Event() { // from class: androidx.media3.session.l0
                @Override // androidx.media3.common.util.ListenerSet.Event
                public final void invoke(Object obj) {
                    switch (i14) {
                        case 0:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$88(playerInfo2, (Player.Listener) obj);
                            break;
                        case 1:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$89(playerInfo2, (Player.Listener) obj);
                            break;
                        case 2:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$90(playerInfo2, (Player.Listener) obj);
                            break;
                        case 3:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$91(playerInfo2, (Player.Listener) obj);
                            break;
                        case 4:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$92(playerInfo2, (Player.Listener) obj);
                            break;
                        case 5:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$93(playerInfo2, (Player.Listener) obj);
                            break;
                        case 6:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$94(playerInfo2, (Player.Listener) obj);
                            break;
                        case 7:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$95(playerInfo2, (Player.Listener) obj);
                            break;
                        case 8:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$96(playerInfo2, (Player.Listener) obj);
                            break;
                        case 9:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$97(playerInfo2, (Player.Listener) obj);
                            break;
                        case 10:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$98(playerInfo2, (Player.Listener) obj);
                            break;
                        case 11:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$99(playerInfo2, (Player.Listener) obj);
                            break;
                        case 12:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$100(playerInfo2, (Player.Listener) obj);
                            break;
                        case 13:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$101(playerInfo2, (Player.Listener) obj);
                            break;
                        case 14:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$102(playerInfo2, (Player.Listener) obj);
                            break;
                        case 15:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$103(playerInfo2, (Player.Listener) obj);
                            break;
                        case 16:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$104(playerInfo2, (Player.Listener) obj);
                            break;
                        case 17:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$105(playerInfo2, (Player.Listener) obj);
                            break;
                        case 18:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$83(playerInfo2, (Player.Listener) obj);
                            break;
                        case 19:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$84(playerInfo2, (Player.Listener) obj);
                            break;
                        case 20:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$85(playerInfo2, (Player.Listener) obj);
                            break;
                        default:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$86(playerInfo2, (Player.Listener) obj);
                            break;
                    }
                }
            });
        }
        if (playerInfo.playbackState != playerInfo2.playbackState) {
            final int i15 = 21;
            this.listeners.queueEvent(4, new ListenerSet.Event() { // from class: androidx.media3.session.l0
                @Override // androidx.media3.common.util.ListenerSet.Event
                public final void invoke(Object obj) {
                    switch (i15) {
                        case 0:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$88(playerInfo2, (Player.Listener) obj);
                            break;
                        case 1:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$89(playerInfo2, (Player.Listener) obj);
                            break;
                        case 2:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$90(playerInfo2, (Player.Listener) obj);
                            break;
                        case 3:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$91(playerInfo2, (Player.Listener) obj);
                            break;
                        case 4:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$92(playerInfo2, (Player.Listener) obj);
                            break;
                        case 5:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$93(playerInfo2, (Player.Listener) obj);
                            break;
                        case 6:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$94(playerInfo2, (Player.Listener) obj);
                            break;
                        case 7:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$95(playerInfo2, (Player.Listener) obj);
                            break;
                        case 8:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$96(playerInfo2, (Player.Listener) obj);
                            break;
                        case 9:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$97(playerInfo2, (Player.Listener) obj);
                            break;
                        case 10:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$98(playerInfo2, (Player.Listener) obj);
                            break;
                        case 11:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$99(playerInfo2, (Player.Listener) obj);
                            break;
                        case 12:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$100(playerInfo2, (Player.Listener) obj);
                            break;
                        case 13:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$101(playerInfo2, (Player.Listener) obj);
                            break;
                        case 14:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$102(playerInfo2, (Player.Listener) obj);
                            break;
                        case 15:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$103(playerInfo2, (Player.Listener) obj);
                            break;
                        case 16:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$104(playerInfo2, (Player.Listener) obj);
                            break;
                        case 17:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$105(playerInfo2, (Player.Listener) obj);
                            break;
                        case 18:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$83(playerInfo2, (Player.Listener) obj);
                            break;
                        case 19:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$84(playerInfo2, (Player.Listener) obj);
                            break;
                        case 20:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$85(playerInfo2, (Player.Listener) obj);
                            break;
                        default:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$86(playerInfo2, (Player.Listener) obj);
                            break;
                    }
                }
            });
        }
        if (num2 != null) {
            final int i16 = 2;
            this.listeners.queueEvent(5, new ListenerSet.Event() { // from class: androidx.media3.session.k0
                @Override // androidx.media3.common.util.ListenerSet.Event
                public final void invoke(Object obj) {
                    switch (i16) {
                        case 0:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$78(playerInfo2, num2, (Player.Listener) obj);
                            break;
                        case 1:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$79(playerInfo2, num2, (Player.Listener) obj);
                            break;
                        default:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$87(playerInfo2, num2, (Player.Listener) obj);
                            break;
                    }
                }
            });
        }
        if (playerInfo.playbackSuppressionReason != playerInfo2.playbackSuppressionReason) {
            final int i17 = 0;
            this.listeners.queueEvent(6, new ListenerSet.Event() { // from class: androidx.media3.session.l0
                @Override // androidx.media3.common.util.ListenerSet.Event
                public final void invoke(Object obj) {
                    switch (i17) {
                        case 0:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$88(playerInfo2, (Player.Listener) obj);
                            break;
                        case 1:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$89(playerInfo2, (Player.Listener) obj);
                            break;
                        case 2:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$90(playerInfo2, (Player.Listener) obj);
                            break;
                        case 3:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$91(playerInfo2, (Player.Listener) obj);
                            break;
                        case 4:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$92(playerInfo2, (Player.Listener) obj);
                            break;
                        case 5:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$93(playerInfo2, (Player.Listener) obj);
                            break;
                        case 6:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$94(playerInfo2, (Player.Listener) obj);
                            break;
                        case 7:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$95(playerInfo2, (Player.Listener) obj);
                            break;
                        case 8:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$96(playerInfo2, (Player.Listener) obj);
                            break;
                        case 9:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$97(playerInfo2, (Player.Listener) obj);
                            break;
                        case 10:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$98(playerInfo2, (Player.Listener) obj);
                            break;
                        case 11:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$99(playerInfo2, (Player.Listener) obj);
                            break;
                        case 12:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$100(playerInfo2, (Player.Listener) obj);
                            break;
                        case 13:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$101(playerInfo2, (Player.Listener) obj);
                            break;
                        case 14:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$102(playerInfo2, (Player.Listener) obj);
                            break;
                        case 15:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$103(playerInfo2, (Player.Listener) obj);
                            break;
                        case 16:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$104(playerInfo2, (Player.Listener) obj);
                            break;
                        case 17:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$105(playerInfo2, (Player.Listener) obj);
                            break;
                        case 18:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$83(playerInfo2, (Player.Listener) obj);
                            break;
                        case 19:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$84(playerInfo2, (Player.Listener) obj);
                            break;
                        case 20:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$85(playerInfo2, (Player.Listener) obj);
                            break;
                        default:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$86(playerInfo2, (Player.Listener) obj);
                            break;
                    }
                }
            });
        }
        if (playerInfo.isPlaying != playerInfo2.isPlaying) {
            final int i18 = 1;
            this.listeners.queueEvent(7, new ListenerSet.Event() { // from class: androidx.media3.session.l0
                @Override // androidx.media3.common.util.ListenerSet.Event
                public final void invoke(Object obj) {
                    switch (i18) {
                        case 0:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$88(playerInfo2, (Player.Listener) obj);
                            break;
                        case 1:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$89(playerInfo2, (Player.Listener) obj);
                            break;
                        case 2:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$90(playerInfo2, (Player.Listener) obj);
                            break;
                        case 3:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$91(playerInfo2, (Player.Listener) obj);
                            break;
                        case 4:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$92(playerInfo2, (Player.Listener) obj);
                            break;
                        case 5:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$93(playerInfo2, (Player.Listener) obj);
                            break;
                        case 6:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$94(playerInfo2, (Player.Listener) obj);
                            break;
                        case 7:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$95(playerInfo2, (Player.Listener) obj);
                            break;
                        case 8:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$96(playerInfo2, (Player.Listener) obj);
                            break;
                        case 9:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$97(playerInfo2, (Player.Listener) obj);
                            break;
                        case 10:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$98(playerInfo2, (Player.Listener) obj);
                            break;
                        case 11:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$99(playerInfo2, (Player.Listener) obj);
                            break;
                        case 12:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$100(playerInfo2, (Player.Listener) obj);
                            break;
                        case 13:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$101(playerInfo2, (Player.Listener) obj);
                            break;
                        case 14:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$102(playerInfo2, (Player.Listener) obj);
                            break;
                        case 15:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$103(playerInfo2, (Player.Listener) obj);
                            break;
                        case 16:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$104(playerInfo2, (Player.Listener) obj);
                            break;
                        case 17:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$105(playerInfo2, (Player.Listener) obj);
                            break;
                        case 18:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$83(playerInfo2, (Player.Listener) obj);
                            break;
                        case 19:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$84(playerInfo2, (Player.Listener) obj);
                            break;
                        case 20:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$85(playerInfo2, (Player.Listener) obj);
                            break;
                        default:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$86(playerInfo2, (Player.Listener) obj);
                            break;
                    }
                }
            });
        }
        if (!playerInfo.playbackParameters.equals(playerInfo2.playbackParameters)) {
            final int i19 = 2;
            this.listeners.queueEvent(12, new ListenerSet.Event() { // from class: androidx.media3.session.l0
                @Override // androidx.media3.common.util.ListenerSet.Event
                public final void invoke(Object obj) {
                    switch (i19) {
                        case 0:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$88(playerInfo2, (Player.Listener) obj);
                            break;
                        case 1:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$89(playerInfo2, (Player.Listener) obj);
                            break;
                        case 2:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$90(playerInfo2, (Player.Listener) obj);
                            break;
                        case 3:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$91(playerInfo2, (Player.Listener) obj);
                            break;
                        case 4:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$92(playerInfo2, (Player.Listener) obj);
                            break;
                        case 5:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$93(playerInfo2, (Player.Listener) obj);
                            break;
                        case 6:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$94(playerInfo2, (Player.Listener) obj);
                            break;
                        case 7:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$95(playerInfo2, (Player.Listener) obj);
                            break;
                        case 8:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$96(playerInfo2, (Player.Listener) obj);
                            break;
                        case 9:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$97(playerInfo2, (Player.Listener) obj);
                            break;
                        case 10:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$98(playerInfo2, (Player.Listener) obj);
                            break;
                        case 11:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$99(playerInfo2, (Player.Listener) obj);
                            break;
                        case 12:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$100(playerInfo2, (Player.Listener) obj);
                            break;
                        case 13:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$101(playerInfo2, (Player.Listener) obj);
                            break;
                        case 14:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$102(playerInfo2, (Player.Listener) obj);
                            break;
                        case 15:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$103(playerInfo2, (Player.Listener) obj);
                            break;
                        case 16:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$104(playerInfo2, (Player.Listener) obj);
                            break;
                        case 17:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$105(playerInfo2, (Player.Listener) obj);
                            break;
                        case 18:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$83(playerInfo2, (Player.Listener) obj);
                            break;
                        case 19:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$84(playerInfo2, (Player.Listener) obj);
                            break;
                        case 20:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$85(playerInfo2, (Player.Listener) obj);
                            break;
                        default:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$86(playerInfo2, (Player.Listener) obj);
                            break;
                    }
                }
            });
        }
        if (playerInfo.repeatMode != playerInfo2.repeatMode) {
            final int i20 = 3;
            this.listeners.queueEvent(8, new ListenerSet.Event() { // from class: androidx.media3.session.l0
                @Override // androidx.media3.common.util.ListenerSet.Event
                public final void invoke(Object obj) {
                    switch (i20) {
                        case 0:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$88(playerInfo2, (Player.Listener) obj);
                            break;
                        case 1:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$89(playerInfo2, (Player.Listener) obj);
                            break;
                        case 2:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$90(playerInfo2, (Player.Listener) obj);
                            break;
                        case 3:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$91(playerInfo2, (Player.Listener) obj);
                            break;
                        case 4:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$92(playerInfo2, (Player.Listener) obj);
                            break;
                        case 5:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$93(playerInfo2, (Player.Listener) obj);
                            break;
                        case 6:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$94(playerInfo2, (Player.Listener) obj);
                            break;
                        case 7:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$95(playerInfo2, (Player.Listener) obj);
                            break;
                        case 8:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$96(playerInfo2, (Player.Listener) obj);
                            break;
                        case 9:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$97(playerInfo2, (Player.Listener) obj);
                            break;
                        case 10:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$98(playerInfo2, (Player.Listener) obj);
                            break;
                        case 11:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$99(playerInfo2, (Player.Listener) obj);
                            break;
                        case 12:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$100(playerInfo2, (Player.Listener) obj);
                            break;
                        case 13:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$101(playerInfo2, (Player.Listener) obj);
                            break;
                        case 14:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$102(playerInfo2, (Player.Listener) obj);
                            break;
                        case 15:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$103(playerInfo2, (Player.Listener) obj);
                            break;
                        case 16:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$104(playerInfo2, (Player.Listener) obj);
                            break;
                        case 17:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$105(playerInfo2, (Player.Listener) obj);
                            break;
                        case 18:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$83(playerInfo2, (Player.Listener) obj);
                            break;
                        case 19:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$84(playerInfo2, (Player.Listener) obj);
                            break;
                        case 20:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$85(playerInfo2, (Player.Listener) obj);
                            break;
                        default:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$86(playerInfo2, (Player.Listener) obj);
                            break;
                    }
                }
            });
        }
        if (playerInfo.shuffleModeEnabled != playerInfo2.shuffleModeEnabled) {
            final int i21 = 4;
            this.listeners.queueEvent(9, new ListenerSet.Event() { // from class: androidx.media3.session.l0
                @Override // androidx.media3.common.util.ListenerSet.Event
                public final void invoke(Object obj) {
                    switch (i21) {
                        case 0:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$88(playerInfo2, (Player.Listener) obj);
                            break;
                        case 1:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$89(playerInfo2, (Player.Listener) obj);
                            break;
                        case 2:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$90(playerInfo2, (Player.Listener) obj);
                            break;
                        case 3:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$91(playerInfo2, (Player.Listener) obj);
                            break;
                        case 4:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$92(playerInfo2, (Player.Listener) obj);
                            break;
                        case 5:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$93(playerInfo2, (Player.Listener) obj);
                            break;
                        case 6:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$94(playerInfo2, (Player.Listener) obj);
                            break;
                        case 7:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$95(playerInfo2, (Player.Listener) obj);
                            break;
                        case 8:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$96(playerInfo2, (Player.Listener) obj);
                            break;
                        case 9:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$97(playerInfo2, (Player.Listener) obj);
                            break;
                        case 10:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$98(playerInfo2, (Player.Listener) obj);
                            break;
                        case 11:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$99(playerInfo2, (Player.Listener) obj);
                            break;
                        case 12:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$100(playerInfo2, (Player.Listener) obj);
                            break;
                        case 13:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$101(playerInfo2, (Player.Listener) obj);
                            break;
                        case 14:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$102(playerInfo2, (Player.Listener) obj);
                            break;
                        case 15:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$103(playerInfo2, (Player.Listener) obj);
                            break;
                        case 16:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$104(playerInfo2, (Player.Listener) obj);
                            break;
                        case 17:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$105(playerInfo2, (Player.Listener) obj);
                            break;
                        case 18:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$83(playerInfo2, (Player.Listener) obj);
                            break;
                        case 19:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$84(playerInfo2, (Player.Listener) obj);
                            break;
                        case 20:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$85(playerInfo2, (Player.Listener) obj);
                            break;
                        default:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$86(playerInfo2, (Player.Listener) obj);
                            break;
                    }
                }
            });
        }
        if (!playerInfo.playlistMetadata.equals(playerInfo2.playlistMetadata)) {
            final int i22 = 5;
            this.listeners.queueEvent(15, new ListenerSet.Event() { // from class: androidx.media3.session.l0
                @Override // androidx.media3.common.util.ListenerSet.Event
                public final void invoke(Object obj) {
                    switch (i22) {
                        case 0:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$88(playerInfo2, (Player.Listener) obj);
                            break;
                        case 1:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$89(playerInfo2, (Player.Listener) obj);
                            break;
                        case 2:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$90(playerInfo2, (Player.Listener) obj);
                            break;
                        case 3:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$91(playerInfo2, (Player.Listener) obj);
                            break;
                        case 4:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$92(playerInfo2, (Player.Listener) obj);
                            break;
                        case 5:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$93(playerInfo2, (Player.Listener) obj);
                            break;
                        case 6:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$94(playerInfo2, (Player.Listener) obj);
                            break;
                        case 7:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$95(playerInfo2, (Player.Listener) obj);
                            break;
                        case 8:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$96(playerInfo2, (Player.Listener) obj);
                            break;
                        case 9:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$97(playerInfo2, (Player.Listener) obj);
                            break;
                        case 10:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$98(playerInfo2, (Player.Listener) obj);
                            break;
                        case 11:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$99(playerInfo2, (Player.Listener) obj);
                            break;
                        case 12:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$100(playerInfo2, (Player.Listener) obj);
                            break;
                        case 13:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$101(playerInfo2, (Player.Listener) obj);
                            break;
                        case 14:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$102(playerInfo2, (Player.Listener) obj);
                            break;
                        case 15:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$103(playerInfo2, (Player.Listener) obj);
                            break;
                        case 16:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$104(playerInfo2, (Player.Listener) obj);
                            break;
                        case 17:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$105(playerInfo2, (Player.Listener) obj);
                            break;
                        case 18:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$83(playerInfo2, (Player.Listener) obj);
                            break;
                        case 19:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$84(playerInfo2, (Player.Listener) obj);
                            break;
                        case 20:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$85(playerInfo2, (Player.Listener) obj);
                            break;
                        default:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$86(playerInfo2, (Player.Listener) obj);
                            break;
                    }
                }
            });
        }
        if (playerInfo.volume != playerInfo2.volume) {
            final int i23 = 6;
            this.listeners.queueEvent(22, new ListenerSet.Event() { // from class: androidx.media3.session.l0
                @Override // androidx.media3.common.util.ListenerSet.Event
                public final void invoke(Object obj) {
                    switch (i23) {
                        case 0:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$88(playerInfo2, (Player.Listener) obj);
                            break;
                        case 1:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$89(playerInfo2, (Player.Listener) obj);
                            break;
                        case 2:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$90(playerInfo2, (Player.Listener) obj);
                            break;
                        case 3:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$91(playerInfo2, (Player.Listener) obj);
                            break;
                        case 4:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$92(playerInfo2, (Player.Listener) obj);
                            break;
                        case 5:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$93(playerInfo2, (Player.Listener) obj);
                            break;
                        case 6:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$94(playerInfo2, (Player.Listener) obj);
                            break;
                        case 7:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$95(playerInfo2, (Player.Listener) obj);
                            break;
                        case 8:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$96(playerInfo2, (Player.Listener) obj);
                            break;
                        case 9:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$97(playerInfo2, (Player.Listener) obj);
                            break;
                        case 10:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$98(playerInfo2, (Player.Listener) obj);
                            break;
                        case 11:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$99(playerInfo2, (Player.Listener) obj);
                            break;
                        case 12:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$100(playerInfo2, (Player.Listener) obj);
                            break;
                        case 13:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$101(playerInfo2, (Player.Listener) obj);
                            break;
                        case 14:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$102(playerInfo2, (Player.Listener) obj);
                            break;
                        case 15:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$103(playerInfo2, (Player.Listener) obj);
                            break;
                        case 16:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$104(playerInfo2, (Player.Listener) obj);
                            break;
                        case 17:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$105(playerInfo2, (Player.Listener) obj);
                            break;
                        case 18:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$83(playerInfo2, (Player.Listener) obj);
                            break;
                        case 19:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$84(playerInfo2, (Player.Listener) obj);
                            break;
                        case 20:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$85(playerInfo2, (Player.Listener) obj);
                            break;
                        default:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$86(playerInfo2, (Player.Listener) obj);
                            break;
                    }
                }
            });
        }
        if (!playerInfo.audioAttributes.equals(playerInfo2.audioAttributes)) {
            final int i24 = 7;
            this.listeners.queueEvent(20, new ListenerSet.Event() { // from class: androidx.media3.session.l0
                @Override // androidx.media3.common.util.ListenerSet.Event
                public final void invoke(Object obj) {
                    switch (i24) {
                        case 0:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$88(playerInfo2, (Player.Listener) obj);
                            break;
                        case 1:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$89(playerInfo2, (Player.Listener) obj);
                            break;
                        case 2:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$90(playerInfo2, (Player.Listener) obj);
                            break;
                        case 3:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$91(playerInfo2, (Player.Listener) obj);
                            break;
                        case 4:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$92(playerInfo2, (Player.Listener) obj);
                            break;
                        case 5:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$93(playerInfo2, (Player.Listener) obj);
                            break;
                        case 6:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$94(playerInfo2, (Player.Listener) obj);
                            break;
                        case 7:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$95(playerInfo2, (Player.Listener) obj);
                            break;
                        case 8:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$96(playerInfo2, (Player.Listener) obj);
                            break;
                        case 9:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$97(playerInfo2, (Player.Listener) obj);
                            break;
                        case 10:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$98(playerInfo2, (Player.Listener) obj);
                            break;
                        case 11:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$99(playerInfo2, (Player.Listener) obj);
                            break;
                        case 12:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$100(playerInfo2, (Player.Listener) obj);
                            break;
                        case 13:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$101(playerInfo2, (Player.Listener) obj);
                            break;
                        case 14:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$102(playerInfo2, (Player.Listener) obj);
                            break;
                        case 15:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$103(playerInfo2, (Player.Listener) obj);
                            break;
                        case 16:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$104(playerInfo2, (Player.Listener) obj);
                            break;
                        case 17:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$105(playerInfo2, (Player.Listener) obj);
                            break;
                        case 18:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$83(playerInfo2, (Player.Listener) obj);
                            break;
                        case 19:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$84(playerInfo2, (Player.Listener) obj);
                            break;
                        case 20:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$85(playerInfo2, (Player.Listener) obj);
                            break;
                        default:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$86(playerInfo2, (Player.Listener) obj);
                            break;
                    }
                }
            });
        }
        if (playerInfo.audioSessionId != playerInfo2.audioSessionId) {
            final int i25 = 8;
            this.listeners.queueEvent(21, new ListenerSet.Event() { // from class: androidx.media3.session.l0
                @Override // androidx.media3.common.util.ListenerSet.Event
                public final void invoke(Object obj) {
                    switch (i25) {
                        case 0:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$88(playerInfo2, (Player.Listener) obj);
                            break;
                        case 1:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$89(playerInfo2, (Player.Listener) obj);
                            break;
                        case 2:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$90(playerInfo2, (Player.Listener) obj);
                            break;
                        case 3:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$91(playerInfo2, (Player.Listener) obj);
                            break;
                        case 4:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$92(playerInfo2, (Player.Listener) obj);
                            break;
                        case 5:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$93(playerInfo2, (Player.Listener) obj);
                            break;
                        case 6:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$94(playerInfo2, (Player.Listener) obj);
                            break;
                        case 7:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$95(playerInfo2, (Player.Listener) obj);
                            break;
                        case 8:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$96(playerInfo2, (Player.Listener) obj);
                            break;
                        case 9:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$97(playerInfo2, (Player.Listener) obj);
                            break;
                        case 10:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$98(playerInfo2, (Player.Listener) obj);
                            break;
                        case 11:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$99(playerInfo2, (Player.Listener) obj);
                            break;
                        case 12:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$100(playerInfo2, (Player.Listener) obj);
                            break;
                        case 13:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$101(playerInfo2, (Player.Listener) obj);
                            break;
                        case 14:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$102(playerInfo2, (Player.Listener) obj);
                            break;
                        case 15:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$103(playerInfo2, (Player.Listener) obj);
                            break;
                        case 16:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$104(playerInfo2, (Player.Listener) obj);
                            break;
                        case 17:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$105(playerInfo2, (Player.Listener) obj);
                            break;
                        case 18:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$83(playerInfo2, (Player.Listener) obj);
                            break;
                        case 19:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$84(playerInfo2, (Player.Listener) obj);
                            break;
                        case 20:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$85(playerInfo2, (Player.Listener) obj);
                            break;
                        default:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$86(playerInfo2, (Player.Listener) obj);
                            break;
                    }
                }
            });
        }
        if (!playerInfo.cueGroup.cues.equals(playerInfo2.cueGroup.cues)) {
            final int i26 = 9;
            this.listeners.queueEvent(27, new ListenerSet.Event() { // from class: androidx.media3.session.l0
                @Override // androidx.media3.common.util.ListenerSet.Event
                public final void invoke(Object obj) {
                    switch (i26) {
                        case 0:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$88(playerInfo2, (Player.Listener) obj);
                            break;
                        case 1:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$89(playerInfo2, (Player.Listener) obj);
                            break;
                        case 2:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$90(playerInfo2, (Player.Listener) obj);
                            break;
                        case 3:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$91(playerInfo2, (Player.Listener) obj);
                            break;
                        case 4:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$92(playerInfo2, (Player.Listener) obj);
                            break;
                        case 5:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$93(playerInfo2, (Player.Listener) obj);
                            break;
                        case 6:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$94(playerInfo2, (Player.Listener) obj);
                            break;
                        case 7:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$95(playerInfo2, (Player.Listener) obj);
                            break;
                        case 8:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$96(playerInfo2, (Player.Listener) obj);
                            break;
                        case 9:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$97(playerInfo2, (Player.Listener) obj);
                            break;
                        case 10:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$98(playerInfo2, (Player.Listener) obj);
                            break;
                        case 11:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$99(playerInfo2, (Player.Listener) obj);
                            break;
                        case 12:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$100(playerInfo2, (Player.Listener) obj);
                            break;
                        case 13:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$101(playerInfo2, (Player.Listener) obj);
                            break;
                        case 14:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$102(playerInfo2, (Player.Listener) obj);
                            break;
                        case 15:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$103(playerInfo2, (Player.Listener) obj);
                            break;
                        case 16:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$104(playerInfo2, (Player.Listener) obj);
                            break;
                        case 17:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$105(playerInfo2, (Player.Listener) obj);
                            break;
                        case 18:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$83(playerInfo2, (Player.Listener) obj);
                            break;
                        case 19:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$84(playerInfo2, (Player.Listener) obj);
                            break;
                        case 20:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$85(playerInfo2, (Player.Listener) obj);
                            break;
                        default:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$86(playerInfo2, (Player.Listener) obj);
                            break;
                    }
                }
            });
            final int i27 = 10;
            this.listeners.queueEvent(27, new ListenerSet.Event() { // from class: androidx.media3.session.l0
                @Override // androidx.media3.common.util.ListenerSet.Event
                public final void invoke(Object obj) {
                    switch (i27) {
                        case 0:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$88(playerInfo2, (Player.Listener) obj);
                            break;
                        case 1:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$89(playerInfo2, (Player.Listener) obj);
                            break;
                        case 2:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$90(playerInfo2, (Player.Listener) obj);
                            break;
                        case 3:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$91(playerInfo2, (Player.Listener) obj);
                            break;
                        case 4:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$92(playerInfo2, (Player.Listener) obj);
                            break;
                        case 5:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$93(playerInfo2, (Player.Listener) obj);
                            break;
                        case 6:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$94(playerInfo2, (Player.Listener) obj);
                            break;
                        case 7:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$95(playerInfo2, (Player.Listener) obj);
                            break;
                        case 8:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$96(playerInfo2, (Player.Listener) obj);
                            break;
                        case 9:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$97(playerInfo2, (Player.Listener) obj);
                            break;
                        case 10:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$98(playerInfo2, (Player.Listener) obj);
                            break;
                        case 11:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$99(playerInfo2, (Player.Listener) obj);
                            break;
                        case 12:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$100(playerInfo2, (Player.Listener) obj);
                            break;
                        case 13:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$101(playerInfo2, (Player.Listener) obj);
                            break;
                        case 14:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$102(playerInfo2, (Player.Listener) obj);
                            break;
                        case 15:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$103(playerInfo2, (Player.Listener) obj);
                            break;
                        case 16:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$104(playerInfo2, (Player.Listener) obj);
                            break;
                        case 17:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$105(playerInfo2, (Player.Listener) obj);
                            break;
                        case 18:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$83(playerInfo2, (Player.Listener) obj);
                            break;
                        case 19:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$84(playerInfo2, (Player.Listener) obj);
                            break;
                        case 20:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$85(playerInfo2, (Player.Listener) obj);
                            break;
                        default:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$86(playerInfo2, (Player.Listener) obj);
                            break;
                    }
                }
            });
        }
        if (!playerInfo.deviceInfo.equals(playerInfo2.deviceInfo)) {
            final int i28 = 11;
            this.listeners.queueEvent(29, new ListenerSet.Event() { // from class: androidx.media3.session.l0
                @Override // androidx.media3.common.util.ListenerSet.Event
                public final void invoke(Object obj) {
                    switch (i28) {
                        case 0:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$88(playerInfo2, (Player.Listener) obj);
                            break;
                        case 1:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$89(playerInfo2, (Player.Listener) obj);
                            break;
                        case 2:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$90(playerInfo2, (Player.Listener) obj);
                            break;
                        case 3:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$91(playerInfo2, (Player.Listener) obj);
                            break;
                        case 4:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$92(playerInfo2, (Player.Listener) obj);
                            break;
                        case 5:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$93(playerInfo2, (Player.Listener) obj);
                            break;
                        case 6:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$94(playerInfo2, (Player.Listener) obj);
                            break;
                        case 7:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$95(playerInfo2, (Player.Listener) obj);
                            break;
                        case 8:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$96(playerInfo2, (Player.Listener) obj);
                            break;
                        case 9:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$97(playerInfo2, (Player.Listener) obj);
                            break;
                        case 10:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$98(playerInfo2, (Player.Listener) obj);
                            break;
                        case 11:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$99(playerInfo2, (Player.Listener) obj);
                            break;
                        case 12:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$100(playerInfo2, (Player.Listener) obj);
                            break;
                        case 13:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$101(playerInfo2, (Player.Listener) obj);
                            break;
                        case 14:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$102(playerInfo2, (Player.Listener) obj);
                            break;
                        case 15:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$103(playerInfo2, (Player.Listener) obj);
                            break;
                        case 16:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$104(playerInfo2, (Player.Listener) obj);
                            break;
                        case 17:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$105(playerInfo2, (Player.Listener) obj);
                            break;
                        case 18:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$83(playerInfo2, (Player.Listener) obj);
                            break;
                        case 19:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$84(playerInfo2, (Player.Listener) obj);
                            break;
                        case 20:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$85(playerInfo2, (Player.Listener) obj);
                            break;
                        default:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$86(playerInfo2, (Player.Listener) obj);
                            break;
                    }
                }
            });
        }
        if (playerInfo.deviceVolume != playerInfo2.deviceVolume || playerInfo.deviceMuted != playerInfo2.deviceMuted) {
            final int i29 = 12;
            this.listeners.queueEvent(30, new ListenerSet.Event() { // from class: androidx.media3.session.l0
                @Override // androidx.media3.common.util.ListenerSet.Event
                public final void invoke(Object obj) {
                    switch (i29) {
                        case 0:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$88(playerInfo2, (Player.Listener) obj);
                            break;
                        case 1:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$89(playerInfo2, (Player.Listener) obj);
                            break;
                        case 2:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$90(playerInfo2, (Player.Listener) obj);
                            break;
                        case 3:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$91(playerInfo2, (Player.Listener) obj);
                            break;
                        case 4:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$92(playerInfo2, (Player.Listener) obj);
                            break;
                        case 5:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$93(playerInfo2, (Player.Listener) obj);
                            break;
                        case 6:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$94(playerInfo2, (Player.Listener) obj);
                            break;
                        case 7:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$95(playerInfo2, (Player.Listener) obj);
                            break;
                        case 8:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$96(playerInfo2, (Player.Listener) obj);
                            break;
                        case 9:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$97(playerInfo2, (Player.Listener) obj);
                            break;
                        case 10:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$98(playerInfo2, (Player.Listener) obj);
                            break;
                        case 11:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$99(playerInfo2, (Player.Listener) obj);
                            break;
                        case 12:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$100(playerInfo2, (Player.Listener) obj);
                            break;
                        case 13:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$101(playerInfo2, (Player.Listener) obj);
                            break;
                        case 14:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$102(playerInfo2, (Player.Listener) obj);
                            break;
                        case 15:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$103(playerInfo2, (Player.Listener) obj);
                            break;
                        case 16:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$104(playerInfo2, (Player.Listener) obj);
                            break;
                        case 17:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$105(playerInfo2, (Player.Listener) obj);
                            break;
                        case 18:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$83(playerInfo2, (Player.Listener) obj);
                            break;
                        case 19:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$84(playerInfo2, (Player.Listener) obj);
                            break;
                        case 20:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$85(playerInfo2, (Player.Listener) obj);
                            break;
                        default:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$86(playerInfo2, (Player.Listener) obj);
                            break;
                    }
                }
            });
        }
        if (!playerInfo.videoSize.equals(playerInfo2.videoSize)) {
            final int i30 = 13;
            this.listeners.queueEvent(25, new ListenerSet.Event() { // from class: androidx.media3.session.l0
                @Override // androidx.media3.common.util.ListenerSet.Event
                public final void invoke(Object obj) {
                    switch (i30) {
                        case 0:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$88(playerInfo2, (Player.Listener) obj);
                            break;
                        case 1:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$89(playerInfo2, (Player.Listener) obj);
                            break;
                        case 2:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$90(playerInfo2, (Player.Listener) obj);
                            break;
                        case 3:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$91(playerInfo2, (Player.Listener) obj);
                            break;
                        case 4:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$92(playerInfo2, (Player.Listener) obj);
                            break;
                        case 5:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$93(playerInfo2, (Player.Listener) obj);
                            break;
                        case 6:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$94(playerInfo2, (Player.Listener) obj);
                            break;
                        case 7:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$95(playerInfo2, (Player.Listener) obj);
                            break;
                        case 8:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$96(playerInfo2, (Player.Listener) obj);
                            break;
                        case 9:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$97(playerInfo2, (Player.Listener) obj);
                            break;
                        case 10:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$98(playerInfo2, (Player.Listener) obj);
                            break;
                        case 11:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$99(playerInfo2, (Player.Listener) obj);
                            break;
                        case 12:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$100(playerInfo2, (Player.Listener) obj);
                            break;
                        case 13:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$101(playerInfo2, (Player.Listener) obj);
                            break;
                        case 14:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$102(playerInfo2, (Player.Listener) obj);
                            break;
                        case 15:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$103(playerInfo2, (Player.Listener) obj);
                            break;
                        case 16:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$104(playerInfo2, (Player.Listener) obj);
                            break;
                        case 17:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$105(playerInfo2, (Player.Listener) obj);
                            break;
                        case 18:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$83(playerInfo2, (Player.Listener) obj);
                            break;
                        case 19:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$84(playerInfo2, (Player.Listener) obj);
                            break;
                        case 20:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$85(playerInfo2, (Player.Listener) obj);
                            break;
                        default:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$86(playerInfo2, (Player.Listener) obj);
                            break;
                    }
                }
            });
        }
        if (playerInfo.seekBackIncrementMs != playerInfo2.seekBackIncrementMs) {
            final int i31 = 14;
            this.listeners.queueEvent(16, new ListenerSet.Event() { // from class: androidx.media3.session.l0
                @Override // androidx.media3.common.util.ListenerSet.Event
                public final void invoke(Object obj) {
                    switch (i31) {
                        case 0:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$88(playerInfo2, (Player.Listener) obj);
                            break;
                        case 1:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$89(playerInfo2, (Player.Listener) obj);
                            break;
                        case 2:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$90(playerInfo2, (Player.Listener) obj);
                            break;
                        case 3:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$91(playerInfo2, (Player.Listener) obj);
                            break;
                        case 4:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$92(playerInfo2, (Player.Listener) obj);
                            break;
                        case 5:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$93(playerInfo2, (Player.Listener) obj);
                            break;
                        case 6:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$94(playerInfo2, (Player.Listener) obj);
                            break;
                        case 7:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$95(playerInfo2, (Player.Listener) obj);
                            break;
                        case 8:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$96(playerInfo2, (Player.Listener) obj);
                            break;
                        case 9:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$97(playerInfo2, (Player.Listener) obj);
                            break;
                        case 10:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$98(playerInfo2, (Player.Listener) obj);
                            break;
                        case 11:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$99(playerInfo2, (Player.Listener) obj);
                            break;
                        case 12:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$100(playerInfo2, (Player.Listener) obj);
                            break;
                        case 13:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$101(playerInfo2, (Player.Listener) obj);
                            break;
                        case 14:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$102(playerInfo2, (Player.Listener) obj);
                            break;
                        case 15:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$103(playerInfo2, (Player.Listener) obj);
                            break;
                        case 16:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$104(playerInfo2, (Player.Listener) obj);
                            break;
                        case 17:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$105(playerInfo2, (Player.Listener) obj);
                            break;
                        case 18:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$83(playerInfo2, (Player.Listener) obj);
                            break;
                        case 19:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$84(playerInfo2, (Player.Listener) obj);
                            break;
                        case 20:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$85(playerInfo2, (Player.Listener) obj);
                            break;
                        default:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$86(playerInfo2, (Player.Listener) obj);
                            break;
                    }
                }
            });
        }
        if (playerInfo.seekForwardIncrementMs != playerInfo2.seekForwardIncrementMs) {
            final int i32 = 15;
            this.listeners.queueEvent(17, new ListenerSet.Event() { // from class: androidx.media3.session.l0
                @Override // androidx.media3.common.util.ListenerSet.Event
                public final void invoke(Object obj) {
                    switch (i32) {
                        case 0:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$88(playerInfo2, (Player.Listener) obj);
                            break;
                        case 1:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$89(playerInfo2, (Player.Listener) obj);
                            break;
                        case 2:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$90(playerInfo2, (Player.Listener) obj);
                            break;
                        case 3:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$91(playerInfo2, (Player.Listener) obj);
                            break;
                        case 4:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$92(playerInfo2, (Player.Listener) obj);
                            break;
                        case 5:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$93(playerInfo2, (Player.Listener) obj);
                            break;
                        case 6:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$94(playerInfo2, (Player.Listener) obj);
                            break;
                        case 7:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$95(playerInfo2, (Player.Listener) obj);
                            break;
                        case 8:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$96(playerInfo2, (Player.Listener) obj);
                            break;
                        case 9:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$97(playerInfo2, (Player.Listener) obj);
                            break;
                        case 10:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$98(playerInfo2, (Player.Listener) obj);
                            break;
                        case 11:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$99(playerInfo2, (Player.Listener) obj);
                            break;
                        case 12:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$100(playerInfo2, (Player.Listener) obj);
                            break;
                        case 13:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$101(playerInfo2, (Player.Listener) obj);
                            break;
                        case 14:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$102(playerInfo2, (Player.Listener) obj);
                            break;
                        case 15:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$103(playerInfo2, (Player.Listener) obj);
                            break;
                        case 16:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$104(playerInfo2, (Player.Listener) obj);
                            break;
                        case 17:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$105(playerInfo2, (Player.Listener) obj);
                            break;
                        case 18:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$83(playerInfo2, (Player.Listener) obj);
                            break;
                        case 19:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$84(playerInfo2, (Player.Listener) obj);
                            break;
                        case 20:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$85(playerInfo2, (Player.Listener) obj);
                            break;
                        default:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$86(playerInfo2, (Player.Listener) obj);
                            break;
                    }
                }
            });
        }
        if (playerInfo.maxSeekToPreviousPositionMs != playerInfo2.maxSeekToPreviousPositionMs) {
            final int i33 = 16;
            this.listeners.queueEvent(18, new ListenerSet.Event() { // from class: androidx.media3.session.l0
                @Override // androidx.media3.common.util.ListenerSet.Event
                public final void invoke(Object obj) {
                    switch (i33) {
                        case 0:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$88(playerInfo2, (Player.Listener) obj);
                            break;
                        case 1:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$89(playerInfo2, (Player.Listener) obj);
                            break;
                        case 2:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$90(playerInfo2, (Player.Listener) obj);
                            break;
                        case 3:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$91(playerInfo2, (Player.Listener) obj);
                            break;
                        case 4:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$92(playerInfo2, (Player.Listener) obj);
                            break;
                        case 5:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$93(playerInfo2, (Player.Listener) obj);
                            break;
                        case 6:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$94(playerInfo2, (Player.Listener) obj);
                            break;
                        case 7:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$95(playerInfo2, (Player.Listener) obj);
                            break;
                        case 8:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$96(playerInfo2, (Player.Listener) obj);
                            break;
                        case 9:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$97(playerInfo2, (Player.Listener) obj);
                            break;
                        case 10:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$98(playerInfo2, (Player.Listener) obj);
                            break;
                        case 11:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$99(playerInfo2, (Player.Listener) obj);
                            break;
                        case 12:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$100(playerInfo2, (Player.Listener) obj);
                            break;
                        case 13:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$101(playerInfo2, (Player.Listener) obj);
                            break;
                        case 14:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$102(playerInfo2, (Player.Listener) obj);
                            break;
                        case 15:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$103(playerInfo2, (Player.Listener) obj);
                            break;
                        case 16:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$104(playerInfo2, (Player.Listener) obj);
                            break;
                        case 17:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$105(playerInfo2, (Player.Listener) obj);
                            break;
                        case 18:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$83(playerInfo2, (Player.Listener) obj);
                            break;
                        case 19:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$84(playerInfo2, (Player.Listener) obj);
                            break;
                        case 20:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$85(playerInfo2, (Player.Listener) obj);
                            break;
                        default:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$86(playerInfo2, (Player.Listener) obj);
                            break;
                    }
                }
            });
        }
        if (!playerInfo.trackSelectionParameters.equals(playerInfo2.trackSelectionParameters)) {
            final int i34 = 17;
            this.listeners.queueEvent(19, new ListenerSet.Event() { // from class: androidx.media3.session.l0
                @Override // androidx.media3.common.util.ListenerSet.Event
                public final void invoke(Object obj) {
                    switch (i34) {
                        case 0:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$88(playerInfo2, (Player.Listener) obj);
                            break;
                        case 1:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$89(playerInfo2, (Player.Listener) obj);
                            break;
                        case 2:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$90(playerInfo2, (Player.Listener) obj);
                            break;
                        case 3:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$91(playerInfo2, (Player.Listener) obj);
                            break;
                        case 4:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$92(playerInfo2, (Player.Listener) obj);
                            break;
                        case 5:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$93(playerInfo2, (Player.Listener) obj);
                            break;
                        case 6:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$94(playerInfo2, (Player.Listener) obj);
                            break;
                        case 7:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$95(playerInfo2, (Player.Listener) obj);
                            break;
                        case 8:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$96(playerInfo2, (Player.Listener) obj);
                            break;
                        case 9:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$97(playerInfo2, (Player.Listener) obj);
                            break;
                        case 10:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$98(playerInfo2, (Player.Listener) obj);
                            break;
                        case 11:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$99(playerInfo2, (Player.Listener) obj);
                            break;
                        case 12:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$100(playerInfo2, (Player.Listener) obj);
                            break;
                        case 13:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$101(playerInfo2, (Player.Listener) obj);
                            break;
                        case 14:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$102(playerInfo2, (Player.Listener) obj);
                            break;
                        case 15:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$103(playerInfo2, (Player.Listener) obj);
                            break;
                        case 16:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$104(playerInfo2, (Player.Listener) obj);
                            break;
                        case 17:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$105(playerInfo2, (Player.Listener) obj);
                            break;
                        case 18:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$83(playerInfo2, (Player.Listener) obj);
                            break;
                        case 19:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$84(playerInfo2, (Player.Listener) obj);
                            break;
                        case 20:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$85(playerInfo2, (Player.Listener) obj);
                            break;
                        default:
                            MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$86(playerInfo2, (Player.Listener) obj);
                            break;
                    }
                }
            });
        }
        this.listeners.flushEvents();
    }

    private static void rebuildPeriods(Timeline timeline, List<Timeline.Window> list, List<Timeline.Period> list2) {
        for (int i10 = 0; i10 < list.size(); i10++) {
            Timeline.Window window = list.get(i10);
            int i11 = window.firstPeriodIndex;
            int i12 = window.lastPeriodIndex;
            if (i11 == -1 || i12 == -1) {
                window.firstPeriodIndex = list2.size();
                window.lastPeriodIndex = list2.size();
                list2.add(createNewPeriod(i10));
            } else {
                window.firstPeriodIndex = list2.size();
                window.lastPeriodIndex = (i12 - i11) + list2.size();
                while (i11 <= i12) {
                    list2.add(getPeriodWithNewWindowIndex(timeline, i11, i10));
                    i11++;
                }
            }
        }
    }

    private void removeMediaItemsInternal(int i10, int i11) {
        int windowCount = this.playerInfo.timeline.getWindowCount();
        int iMin = Math.min(i11, windowCount);
        if (i10 >= windowCount || i10 == iMin || windowCount == 0) {
            return;
        }
        boolean z = getCurrentMediaItemIndex() >= i10 && getCurrentMediaItemIndex() < iMin;
        PlayerInfo playerInfoMaskPlayerInfoForRemovedItems = maskPlayerInfoForRemovedItems(this.playerInfo, i10, iMin, false, getCurrentPosition(), getContentPosition());
        int i12 = this.playerInfo.sessionPositionInfo.positionInfo.mediaItemIndex;
        updatePlayerInfo(playerInfoMaskPlayerInfoForRemovedItems, 0, null, z ? 4 : null, i12 >= i10 && i12 < iMin ? 3 : null);
    }

    private void replaceMediaItemsInternal(int i10, int i11, List<MediaItem> list) {
        int windowCount = this.playerInfo.timeline.getWindowCount();
        if (i10 > windowCount) {
            return;
        }
        if (this.playerInfo.timeline.isEmpty()) {
            setMediaItemsInternal(list, -1, C.TIME_UNSET, false);
            return;
        }
        int iMin = Math.min(i11, windowCount);
        PlayerInfo playerInfoMaskPlayerInfoForRemovedItems = maskPlayerInfoForRemovedItems(maskPlayerInfoForAddedItems(this.playerInfo, iMin, list, getCurrentPosition(), getContentPosition()), i10, iMin, true, getCurrentPosition(), getContentPosition());
        int i12 = this.playerInfo.sessionPositionInfo.positionInfo.mediaItemIndex;
        boolean z = i12 >= i10 && i12 < iMin;
        updatePlayerInfo(playerInfoMaskPlayerInfoForRemovedItems, 0, null, z ? 4 : null, z ? 3 : null);
    }

    private boolean requestConnectToService() {
        int i10 = Build.VERSION.SDK_INT >= 29 ? 4097 : 1;
        Intent intent = new Intent(MediaSessionService.SERVICE_INTERFACE);
        intent.setClassName(this.token.getPackageName(), this.token.getServiceName());
        try {
            if (this.context.bindService(intent, this.serviceConnection, i10)) {
                return true;
            }
            Log.w(TAG, "bind to " + this.token + " failed");
            return false;
        } catch (SecurityException e5) {
            Log.w(TAG, "bind to " + this.token + " not allowed", e5);
            return false;
        }
    }

    private boolean requestConnectToSession(Bundle bundle) {
        Object binder = this.token.getBinder();
        binder.getClass();
        try {
            IMediaSession.Stub.asInterface((IBinder) binder).connect(this.controllerStub, this.sequencedFutureManager.obtainNextSequenceNumber(), new ConnectionRequest(this.context.getPackageName(), Process.myPid(), bundle, this.instance.getMaxCommandsForMediaItems()).toBundle());
            return true;
        } catch (RemoteException e5) {
            Log.w(TAG, "Failed to call connection request.", e5);
            return false;
        }
    }

    private static com.google.common.collect.h1 resolveCustomLayout(List<CommandButton> list, List<CommandButton> list2, Bundle bundle, SessionCommands sessionCommands, Player.Commands commands) {
        if (!list2.isEmpty()) {
            return CommandButton.copyWithUnavailableButtonsDisabled(list2, sessionCommands, commands);
        }
        boolean z = false;
        boolean z5 = (bundle.getBoolean("android.media.playback.ALWAYS_RESERVE_SPACE_FOR.ACTION_SKIP_TO_PREVIOUS") || commands.containsAny(6, 7)) ? false : true;
        if (!bundle.getBoolean("android.media.playback.ALWAYS_RESERVE_SPACE_FOR.ACTION_SKIP_TO_NEXT") && !commands.containsAny(8, 9)) {
            z = true;
        }
        return CommandButton.getCustomLayoutFromMediaButtonPreferences(list, z5, z);
    }

    private static com.google.common.collect.h1 resolveMediaButtonPreferences(List<CommandButton> list, List<CommandButton> list2, SessionCommands sessionCommands, Player.Commands commands, Bundle bundle) {
        if (list.isEmpty()) {
            list = CommandButton.getMediaButtonPreferencesFromCustomLayout(list2, commands, bundle);
        }
        return CommandButton.copyWithUnavailableButtonsDisabled(list, sessionCommands, commands);
    }

    private static int resolveSubsequentMediaItemIndex(int i10, boolean z, int i11, Timeline timeline, int i12, int i13) {
        int windowCount = timeline.getWindowCount();
        for (int i14 = 0; i14 < windowCount && (i11 = timeline.getNextWindowIndex(i11, i10, z)) != -1; i14++) {
            if (i11 < i12 || i11 >= i13) {
                return i11;
            }
        }
        return -1;
    }

    private void seekToInternal(int i10, long j10) {
        int i11;
        int i12;
        PlayerInfo playerInfoMaskPositionInfo;
        Timeline timeline = this.playerInfo.timeline;
        if ((timeline.isEmpty() || i10 < timeline.getWindowCount()) && !isPlayingAd()) {
            int i13 = getPlaybackState() == 1 ? 1 : 2;
            PlayerInfo playerInfo = this.playerInfo;
            PlayerInfo playerInfoCopyWithPlaybackState = playerInfo.copyWithPlaybackState(i13, playerInfo.playerError);
            PeriodInfo periodInfo = getPeriodInfo(timeline, i10, j10);
            if (periodInfo == null) {
                i11 = 1;
                i12 = 2;
                Player.PositionInfo positionInfo = new Player.PositionInfo(null, i10, null, null, i10, j10 == C.TIME_UNSET ? 0L : j10, j10 == C.TIME_UNSET ? 0L : j10, -1, -1);
                PlayerInfo playerInfo2 = this.playerInfo;
                Timeline timeline2 = playerInfo2.timeline;
                boolean z = this.playerInfo.sessionPositionInfo.isPlayingAd;
                long jElapsedRealtime = SystemClock.elapsedRealtime();
                SessionPositionInfo sessionPositionInfo = this.playerInfo.sessionPositionInfo;
                playerInfoMaskPositionInfo = maskTimelineAndPositionInfo(playerInfo2, timeline2, positionInfo, new SessionPositionInfo(positionInfo, z, jElapsedRealtime, sessionPositionInfo.durationMs, j10 == C.TIME_UNSET ? 0L : j10, 0, 0L, sessionPositionInfo.currentLiveOffsetMs, sessionPositionInfo.contentDurationMs, j10 == C.TIME_UNSET ? 0L : j10), 1);
            } else {
                i11 = 1;
                i12 = 2;
                playerInfoMaskPositionInfo = maskPositionInfo(playerInfoCopyWithPlaybackState, timeline, periodInfo);
            }
            int i14 = (this.playerInfo.timeline.isEmpty() || playerInfoMaskPositionInfo.sessionPositionInfo.positionInfo.mediaItemIndex == this.playerInfo.sessionPositionInfo.positionInfo.mediaItemIndex) ? 0 : i11;
            if (i14 == 0 && playerInfoMaskPositionInfo.sessionPositionInfo.positionInfo.positionMs == this.playerInfo.sessionPositionInfo.positionInfo.positionMs) {
                return;
            }
            updatePlayerInfo(playerInfoMaskPositionInfo, null, null, Integer.valueOf(i11), i14 != 0 ? Integer.valueOf(i12) : null);
        }
    }

    private void seekToInternalByOffset(long j10) {
        long currentPosition = getCurrentPosition() + j10;
        long duration = getDuration();
        if (duration != C.TIME_UNSET) {
            currentPosition = Math.min(currentPosition, duration);
        }
        seekToInternal(getCurrentMediaItemIndex(), Math.max(currentPosition, 0L));
    }

    private void sendControllerResult(int i10, SessionResult sessionResult) {
        IMediaSession iMediaSession = this.iSession;
        if (iMediaSession == null) {
            return;
        }
        try {
            iMediaSession.onControllerResult(this.controllerStub, i10, sessionResult.toBundle());
        } catch (RemoteException unused) {
            Log.w(TAG, "Error in sending");
        }
    }

    private void sendControllerResultWhenReady(final int i10, final com.google.common.util.concurrent.d1<SessionResult> d1Var) {
        d1Var.addListener(new Runnable() { // from class: androidx.media3.session.u0
            @Override // java.lang.Runnable
            public final void run() {
                this.f4162i.lambda$sendControllerResultWhenReady$108(d1Var, i10);
            }
        }, com.google.common.util.concurrent.s0.f14233i);
    }

    /*  JADX ERROR: JadxRuntimeException in pass: ConstructorVisitor
        jadx.core.utils.exceptions.JadxRuntimeException: Can't remove SSA var: r26v0 ??, still in use, count: 1, list:
          (r26v0 ?? I:androidx.media3.common.Player$PositionInfo) from 0x013b: CONSTRUCTOR (r25v0 ?? I:androidx.media3.session.SessionPositionInfo) = 
          (r26v0 ?? I:androidx.media3.common.Player$PositionInfo)
          (r27v0 ?? I:boolean)
          (r28v0 ?? I:long)
          (r30v0 ?? I:long)
          (r32v0 ?? I:long)
          (r34v0 ?? I:int)
          (r35v0 ?? I:long)
          (r37v0 ?? I:long)
          (r39v0 ?? I:long)
          (r41v0 ?? I:long)
         A[MD:(androidx.media3.common.Player$PositionInfo, boolean, long, long, long, int, long, long, long, long):void (m)] (LINE:316) call: androidx.media3.session.SessionPositionInfo.<init>(androidx.media3.common.Player$PositionInfo, boolean, long, long, long, int, long, long, long, long):void type: CONSTRUCTOR
        	at jadx.core.utils.InsnRemover.removeSsaVar(InsnRemover.java:162)
        	at jadx.core.utils.InsnRemover.unbindResult(InsnRemover.java:127)
        	at jadx.core.utils.InsnRemover.lambda$unbindInsns$1(InsnRemover.java:99)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        	at jadx.core.utils.InsnRemover.unbindInsns(InsnRemover.java:98)
        	at jadx.core.utils.InsnRemover.perform(InsnRemover.java:73)
        	at jadx.core.dex.visitors.ConstructorVisitor.replaceInvoke(ConstructorVisitor.java:59)
        	at jadx.core.dex.visitors.ConstructorVisitor.visit(ConstructorVisitor.java:42)
        */
    private void setMediaItemsInternal(
    /*  JADX ERROR: JadxRuntimeException in pass: ConstructorVisitor
        jadx.core.utils.exceptions.JadxRuntimeException: Can't remove SSA var: r26v0 ??, still in use, count: 1, list:
          (r26v0 ?? I:androidx.media3.common.Player$PositionInfo) from 0x013b: CONSTRUCTOR (r25v0 ?? I:androidx.media3.session.SessionPositionInfo) = 
          (r26v0 ?? I:androidx.media3.common.Player$PositionInfo)
          (r27v0 ?? I:boolean)
          (r28v0 ?? I:long)
          (r30v0 ?? I:long)
          (r32v0 ?? I:long)
          (r34v0 ?? I:int)
          (r35v0 ?? I:long)
          (r37v0 ?? I:long)
          (r39v0 ?? I:long)
          (r41v0 ?? I:long)
         A[MD:(androidx.media3.common.Player$PositionInfo, boolean, long, long, long, int, long, long, long, long):void (m)] (LINE:316) call: androidx.media3.session.SessionPositionInfo.<init>(androidx.media3.common.Player$PositionInfo, boolean, long, long, long, int, long, long, long, long):void type: CONSTRUCTOR
        	at jadx.core.utils.InsnRemover.removeSsaVar(InsnRemover.java:162)
        	at jadx.core.utils.InsnRemover.unbindResult(InsnRemover.java:127)
        	at jadx.core.utils.InsnRemover.lambda$unbindInsns$1(InsnRemover.java:99)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        	at jadx.core.utils.InsnRemover.unbindInsns(InsnRemover.java:98)
        	at jadx.core.utils.InsnRemover.perform(InsnRemover.java:73)
        	at jadx.core.dex.visitors.ConstructorVisitor.replaceInvoke(ConstructorVisitor.java:59)
        */
    /*  JADX ERROR: Method generation error
        jadx.core.utils.exceptions.JadxRuntimeException: Code variable not set in r44v0 ??
        	at jadx.core.dex.instructions.args.SSAVar.getCodeVar(SSAVar.java:236)
        	at jadx.core.codegen.MethodGen.addMethodArguments(MethodGen.java:224)
        	at jadx.core.codegen.MethodGen.addDefinition(MethodGen.java:169)
        	at jadx.core.codegen.ClassGen.addMethodCode(ClassGen.java:407)
        	at jadx.core.codegen.ClassGen.addMethod(ClassGen.java:337)
        	at jadx.core.codegen.ClassGen.lambda$addInnerClsAndMethods$3(ClassGen.java:303)
        	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.accept(ForEachOps.java:184)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        	at java.base/java.util.stream.SortedOps$RefSortingSink.end(SortedOps.java:395)
        	at java.base/java.util.stream.Sink$ChainedReference.end(Sink.java:261)
        */

    /* JADX INFO: Access modifiers changed from: private */
    public void setVideoSurfaceWithSize(Surface surface, int i10, int i11) {
        if (isConnected()) {
            SessionToken sessionToken = this.connectedToken;
            sessionToken.getClass();
            if (sessionToken.getInterfaceVersion() >= 8) {
                dispatchRemoteSessionTaskWithPlayerCommandAndWaitForFuture(new h0(this, surface, i10, i11, 0));
            } else {
                dispatchRemoteSessionTaskWithPlayerCommandAndWaitForFuture(new k(this, surface, 4));
            }
        }
    }

    private void updatePlayerInfo(PlayerInfo playerInfo, Integer num, Integer num2, Integer num3, Integer num4) {
        PlayerInfo playerInfo2 = this.playerInfo;
        this.playerInfo = playerInfo;
        notifyPlayerInfoListenersWithReasons(playerInfo2, playerInfo, num, num2, num3, num4);
    }

    private void updateSessionPositionInfoIfNeeded(SessionPositionInfo sessionPositionInfo) {
        if (this.pendingMaskingSequencedFutureNumbers.isEmpty()) {
            SessionPositionInfo sessionPositionInfo2 = this.playerInfo.sessionPositionInfo;
            if (sessionPositionInfo2.eventTimeMs >= sessionPositionInfo.eventTimeMs || !MediaUtils.areSessionPositionInfosInSamePeriodOrAd(sessionPositionInfo, sessionPositionInfo2)) {
                return;
            }
            this.playerInfo = this.playerInfo.copyWithSessionPositionInfo(sessionPositionInfo);
        }
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public void addListener(Player.Listener listener) {
        this.listeners.add(listener);
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public void addMediaItem(MediaItem mediaItem) {
        if (isPlayerCommandAvailable(20)) {
            dispatchRemoteSessionTaskWithPlayerCommand(new j0(this, mediaItem, 1));
            addMediaItemsInternal(getCurrentTimeline().getWindowCount(), Collections.singletonList(mediaItem));
        }
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public void addMediaItems(List<MediaItem> list) {
        if (isPlayerCommandAvailable(20)) {
            dispatchRemoteSessionTaskWithPlayerCommand(new e0(0, list, this));
            addMediaItemsInternal(getCurrentTimeline().getWindowCount(), list);
        }
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public void clearMediaItems() {
        if (isPlayerCommandAvailable(20)) {
            dispatchRemoteSessionTaskWithPlayerCommand(new q(this, 10));
            removeMediaItemsInternal(0, Integer.MAX_VALUE);
        }
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public void clearVideoSurface() {
        if (isPlayerCommandAvailable(27)) {
            clearSurfacesAndCallbacks();
            setVideoSurfaceWithSize(null, 0, 0);
            onSurfaceSizeChanged(0, 0);
        }
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public void clearVideoSurfaceHolder(SurfaceHolder surfaceHolder) {
        if (isPlayerCommandAvailable(27) && surfaceHolder != null && this.videoSurfaceHolder == surfaceHolder) {
            clearVideoSurface();
        }
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public void clearVideoSurfaceView(SurfaceView surfaceView) {
        if (isPlayerCommandAvailable(27)) {
            clearVideoSurfaceHolder(surfaceView == null ? null : surfaceView.getHolder());
        }
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public void clearVideoTextureView(TextureView textureView) {
        if (isPlayerCommandAvailable(27) && textureView != null && this.videoTextureView == textureView) {
            clearVideoSurface();
        }
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public void connect() {
        boolean zRequestConnectToService;
        if (this.token.getType() == 0) {
            this.serviceConnection = null;
            zRequestConnectToService = requestConnectToSession(this.connectionHints);
        } else {
            this.serviceConnection = new SessionServiceConnection(this.connectionHints);
            zRequestConnectToService = requestConnectToService();
        }
        if (zRequestConnectToService) {
            return;
        }
        MediaController mediaControllerImplBase = getInstance();
        MediaController mediaControllerImplBase2 = getInstance();
        Objects.requireNonNull(mediaControllerImplBase2);
        mediaControllerImplBase.runOnApplicationLooper(new j3(mediaControllerImplBase2, 1));
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    @Deprecated
    public void decreaseDeviceVolume() {
        if (isPlayerCommandAvailable(26)) {
            dispatchRemoteSessionTaskWithPlayerCommand(new q(this, 5));
            int i10 = this.playerInfo.deviceVolume - 1;
            if (i10 >= getDeviceInfo().minVolume) {
                PlayerInfo playerInfo = this.playerInfo;
                this.playerInfo = playerInfo.copyWithDeviceVolume(i10, playerInfo.deviceMuted);
                this.listeners.queueEvent(30, new p(i10, 4, this));
                this.listeners.flushEvents();
            }
        }
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public AudioAttributes getAudioAttributes() {
        return this.playerInfo.audioAttributes;
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public int getAudioSessionId() {
        return this.playerInfo.audioSessionId;
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public Player.Commands getAvailableCommands() {
        return this.intersectedPlayerCommands;
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public SessionCommands getAvailableSessionCommands() {
        return this.sessionCommands;
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public IMediaController getBinder() {
        return this.controllerStub;
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public MediaBrowserCompat getBrowserCompat() {
        return null;
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public int getBufferedPercentage() {
        return this.playerInfo.sessionPositionInfo.bufferedPercentage;
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public long getBufferedPosition() {
        return this.playerInfo.sessionPositionInfo.bufferedPositionMs;
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public com.google.common.collect.h1 getCommandButtonsForMediaItem(MediaItem mediaItem) {
        SessionCommand sessionCommand;
        com.google.common.collect.h1 h1Var = mediaItem.mediaMetadata.supportedCommands;
        SessionCommands availableSessionCommands = getAvailableSessionCommands();
        com.google.common.collect.a0.c(4, "initialCapacity");
        Object[] objArrCopyOf = new Object[4];
        int i10 = 0;
        for (int i11 = 0; i11 < h1Var.size(); i11++) {
            CommandButton commandButton = (CommandButton) this.commandButtonsForMediaItemsMap.get(h1Var.get(i11));
            if (commandButton != null && (sessionCommand = commandButton.sessionCommand) != null && availableSessionCommands.contains(sessionCommand)) {
                int i12 = i10 + 1;
                int iB = com.google.common.collect.b1.b(objArrCopyOf.length, i12);
                if (iB > objArrCopyOf.length) {
                    objArrCopyOf = Arrays.copyOf(objArrCopyOf, iB);
                }
                objArrCopyOf[i10] = commandButton;
                i10 = i12;
            }
        }
        return com.google.common.collect.h1.h(i10, objArrCopyOf);
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public SessionToken getConnectedToken() {
        return this.connectedToken;
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public Bundle getConnectionHints() {
        return this.connectionHints;
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public long getContentBufferedPosition() {
        return this.playerInfo.sessionPositionInfo.contentBufferedPositionMs;
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public long getContentDuration() {
        return this.playerInfo.sessionPositionInfo.contentDurationMs;
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public long getContentPosition() {
        SessionPositionInfo sessionPositionInfo = this.playerInfo.sessionPositionInfo;
        return !sessionPositionInfo.isPlayingAd ? getCurrentPosition() : sessionPositionInfo.positionInfo.contentPositionMs;
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public Context getContext() {
        return this.context;
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public int getCurrentAdGroupIndex() {
        return this.playerInfo.sessionPositionInfo.positionInfo.adGroupIndex;
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public int getCurrentAdIndexInAdGroup() {
        return this.playerInfo.sessionPositionInfo.positionInfo.adIndexInAdGroup;
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public CueGroup getCurrentCues() {
        return this.playerInfo.cueGroup;
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public long getCurrentLiveOffset() {
        return this.playerInfo.sessionPositionInfo.currentLiveOffsetMs;
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public int getCurrentMediaItemIndex() {
        return getCurrentMediaItemIndexInternal(this.playerInfo);
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public int getCurrentPeriodIndex() {
        return this.playerInfo.sessionPositionInfo.positionInfo.periodIndex;
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public long getCurrentPosition() {
        long updatedCurrentPositionMs = MediaUtils.getUpdatedCurrentPositionMs(this.playerInfo, this.currentPositionMs, this.lastSetPlayWhenReadyCalledTimeMs, getInstance().getTimeDiffMs());
        this.currentPositionMs = updatedCurrentPositionMs;
        return updatedCurrentPositionMs;
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public Timeline getCurrentTimeline() {
        return this.playerInfo.timeline;
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public Tracks getCurrentTracks() {
        return this.playerInfo.currentTracks;
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public com.google.common.collect.h1 getCustomLayout() {
        return this.resolvedCustomLayout;
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public DeviceInfo getDeviceInfo() {
        return this.playerInfo.deviceInfo;
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public int getDeviceVolume() {
        return this.playerInfo.deviceVolume;
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public long getDuration() {
        return this.playerInfo.sessionPositionInfo.durationMs;
    }

    public MediaController getInstance() {
        return this.instance;
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public long getMaxSeekToPreviousPosition() {
        return this.playerInfo.maxSeekToPreviousPositionMs;
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public com.google.common.collect.h1 getMediaButtonPreferences() {
        return this.resolvedMediaButtonPreferences;
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public MediaMetadata getMediaMetadata() {
        return this.playerInfo.mediaMetadata;
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public int getNextMediaItemIndex() {
        if (this.playerInfo.timeline.isEmpty()) {
            return -1;
        }
        return this.playerInfo.timeline.getNextWindowIndex(getCurrentMediaItemIndex(), convertRepeatModeForNavigation(this.playerInfo.repeatMode), this.playerInfo.shuffleModeEnabled);
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public boolean getPlayWhenReady() {
        return this.playerInfo.playWhenReady;
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public PlaybackParameters getPlaybackParameters() {
        return this.playerInfo.playbackParameters;
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public int getPlaybackState() {
        return this.playerInfo.playbackState;
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public int getPlaybackSuppressionReason() {
        return this.playerInfo.playbackSuppressionReason;
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public PlaybackException getPlayerError() {
        return this.playerInfo.playerError;
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public MediaMetadata getPlaylistMetadata() {
        return this.playerInfo.playlistMetadata;
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public int getPreviousMediaItemIndex() {
        if (this.playerInfo.timeline.isEmpty()) {
            return -1;
        }
        return this.playerInfo.timeline.getPreviousWindowIndex(getCurrentMediaItemIndex(), convertRepeatModeForNavigation(this.playerInfo.repeatMode), this.playerInfo.shuffleModeEnabled);
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public int getRepeatMode() {
        return this.playerInfo.repeatMode;
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public long getSeekBackIncrement() {
        return this.playerInfo.seekBackIncrementMs;
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public long getSeekForwardIncrement() {
        return this.playerInfo.seekForwardIncrementMs;
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public PendingIntent getSessionActivity() {
        return this.sessionActivity;
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public Bundle getSessionExtras() {
        return this.sessionExtras;
    }

    public IMediaSession getSessionInterfaceWithSessionCommandIfAble(int i10) {
        ac.b.j(i10 != 0);
        if (this.sessionCommands.contains(i10)) {
            return this.iSession;
        }
        androidx.fragment.app.a2.s(i10, "Controller isn't allowed to call command, commandCode=", TAG);
        return null;
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public boolean getShuffleModeEnabled() {
        return this.playerInfo.shuffleModeEnabled;
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public Size getSurfaceSize() {
        return this.surfaceSize;
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public long getTotalBufferedDuration() {
        return this.playerInfo.sessionPositionInfo.totalBufferedDurationMs;
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public TrackSelectionParameters getTrackSelectionParameters() {
        return this.playerInfo.trackSelectionParameters;
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public VideoSize getVideoSize() {
        return this.playerInfo.videoSize;
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public float getVolume() {
        return this.playerInfo.volume;
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public boolean hasNextMediaItem() {
        return getNextMediaItemIndex() != -1;
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public boolean hasPreviousMediaItem() {
        return getPreviousMediaItemIndex() != -1;
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    @Deprecated
    public void increaseDeviceVolume() {
        if (isPlayerCommandAvailable(26)) {
            dispatchRemoteSessionTaskWithPlayerCommand(new q(this, 14));
            int i10 = this.playerInfo.deviceVolume + 1;
            int i11 = getDeviceInfo().maxVolume;
            if (i11 == 0 || i10 <= i11) {
                PlayerInfo playerInfo = this.playerInfo;
                this.playerInfo = playerInfo.copyWithDeviceVolume(i10, playerInfo.deviceMuted);
                this.listeners.queueEvent(30, new p(i10, 9, this));
                this.listeners.flushEvents();
            }
        }
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public boolean isConnected() {
        return this.iSession != null;
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public boolean isDeviceMuted() {
        return this.playerInfo.deviceMuted;
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public boolean isLoading() {
        return this.playerInfo.isLoading;
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public boolean isPlaying() {
        return this.playerInfo.isPlaying;
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public boolean isPlayingAd() {
        return this.playerInfo.sessionPositionInfo.isPlayingAd;
    }

    public boolean isReleased() {
        return this.released;
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public void moveMediaItem(int i10, int i11) {
        if (isPlayerCommandAvailable(20)) {
            ac.b.j(i10 >= 0 && i11 >= 0);
            dispatchRemoteSessionTaskWithPlayerCommand(new x(this, i10, i11, 0));
            moveMediaItemsInternal(i10, i10 + 1, i11);
        }
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public void moveMediaItems(final int i10, final int i11, final int i12) {
        if (isPlayerCommandAvailable(20)) {
            ac.b.j(i10 >= 0 && i10 <= i11 && i12 >= 0);
            dispatchRemoteSessionTaskWithPlayerCommand(new RemoteSessionTask() { // from class: androidx.media3.session.q0
                @Override // androidx.media3.session.MediaControllerImplBase.RemoteSessionTask
                public final void run(IMediaSession iMediaSession, int i13) throws RemoteException {
                    this.f4113i.lambda$moveMediaItems$39(i10, i11, i12, iMediaSession, i13);
                }
            });
            moveMediaItemsInternal(i10, i11, i12);
        }
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public void mute() {
        if (isPlayerCommandAvailable(24)) {
            dispatchRemoteSessionTaskWithPlayerCommand(new q(this, 3));
            PlayerInfo playerInfo = this.playerInfo;
            if (playerInfo.volume != 0.0f) {
                this.playerInfo = playerInfo.copyWithVolume(0.0f);
                this.listeners.queueEvent(22, new v(0));
                this.listeners.flushEvents();
            }
        }
    }

    public void notifyPeriodicSessionPositionInfoChanged(SessionPositionInfo sessionPositionInfo) {
        if (isConnected()) {
            updateSessionPositionInfoIfNeeded(sessionPositionInfo);
        }
    }

    public void notifyPlatformControllerAboutMedia3ChangeRequest() {
        android.media.session.MediaController mediaController;
        if (Build.VERSION.SDK_INT < 31 || (mediaController = this.platformController) == null) {
            return;
        }
        mediaController.getTransportControls().sendCustomAction("androidx.media3.session.SESSION_COMMAND_MEDIA3_PLAY_REQUEST", (Bundle) null);
    }

    public void onAvailableCommandsChangedFromPlayer(Player.Commands commands) {
        boolean z;
        boolean z5;
        if (isConnected() && !Objects.equals(this.playerCommandsFromPlayer, commands)) {
            this.playerCommandsFromPlayer = commands;
            Player.Commands commands2 = this.intersectedPlayerCommands;
            Player.Commands commandsCreateIntersectedCommandsEnsuringCommandReleaseAvailable = createIntersectedCommandsEnsuringCommandReleaseAvailable(this.playerCommandsFromSession, commands);
            this.intersectedPlayerCommands = commandsCreateIntersectedCommandsEnsuringCommandReleaseAvailable;
            if (Objects.equals(commandsCreateIntersectedCommandsEnsuringCommandReleaseAvailable, commands2)) {
                z = false;
                z5 = false;
            } else {
                com.google.common.collect.h1 h1Var = this.resolvedMediaButtonPreferences;
                com.google.common.collect.h1 h1Var2 = this.resolvedCustomLayout;
                com.google.common.collect.h1 h1VarResolveMediaButtonPreferences = resolveMediaButtonPreferences(this.mediaButtonPreferencesOriginal, this.customLayoutOriginal, this.sessionCommands, this.intersectedPlayerCommands, this.sessionExtras);
                this.resolvedMediaButtonPreferences = h1VarResolveMediaButtonPreferences;
                this.resolvedCustomLayout = resolveCustomLayout(h1VarResolveMediaButtonPreferences, this.customLayoutOriginal, this.sessionExtras, this.sessionCommands, this.intersectedPlayerCommands);
                z = !this.resolvedMediaButtonPreferences.equals(h1Var);
                z5 = !this.resolvedCustomLayout.equals(h1Var2);
                this.listeners.sendEvent(13, new q(this, 0));
            }
            if (z5) {
                getInstance().notifyControllerListener(new q(this, 1));
            }
            if (z) {
                getInstance().notifyControllerListener(new q(this, 2));
            }
        }
    }

    public void onAvailableCommandsChangedFromSession(SessionCommands sessionCommands, Player.Commands commands) {
        boolean z;
        boolean z5;
        if (isConnected()) {
            boolean zEquals = Objects.equals(this.playerCommandsFromSession, commands);
            boolean zEquals2 = Objects.equals(this.sessionCommands, sessionCommands);
            if (zEquals && zEquals2) {
                return;
            }
            this.sessionCommands = sessionCommands;
            boolean z10 = false;
            if (zEquals) {
                z = false;
            } else {
                this.playerCommandsFromSession = commands;
                Player.Commands commands2 = this.intersectedPlayerCommands;
                Player.Commands commandsCreateIntersectedCommandsEnsuringCommandReleaseAvailable = createIntersectedCommandsEnsuringCommandReleaseAvailable(commands, this.playerCommandsFromPlayer);
                this.intersectedPlayerCommands = commandsCreateIntersectedCommandsEnsuringCommandReleaseAvailable;
                z = !Objects.equals(commandsCreateIntersectedCommandsEnsuringCommandReleaseAvailable, commands2);
            }
            if (!zEquals2 || z) {
                com.google.common.collect.h1 h1Var = this.resolvedMediaButtonPreferences;
                com.google.common.collect.h1 h1Var2 = this.resolvedCustomLayout;
                com.google.common.collect.h1 h1VarResolveMediaButtonPreferences = resolveMediaButtonPreferences(this.mediaButtonPreferencesOriginal, this.customLayoutOriginal, sessionCommands, this.intersectedPlayerCommands, this.sessionExtras);
                this.resolvedMediaButtonPreferences = h1VarResolveMediaButtonPreferences;
                this.resolvedCustomLayout = resolveCustomLayout(h1VarResolveMediaButtonPreferences, this.customLayoutOriginal, this.sessionExtras, sessionCommands, this.intersectedPlayerCommands);
                z5 = !this.resolvedMediaButtonPreferences.equals(h1Var);
                z10 = !this.resolvedCustomLayout.equals(h1Var2);
            } else {
                z5 = false;
            }
            if (z) {
                this.listeners.sendEvent(13, new q(this, 19));
            }
            if (!zEquals2) {
                getInstance().notifyControllerListener(new k(this, sessionCommands, 9));
            }
            if (z10) {
                getInstance().notifyControllerListener(new q(this, 20));
            }
            if (z5) {
                getInstance().notifyControllerListener(new q(this, 21));
            }
        }
    }

    public void onConnected(ConnectionState connectionState) {
        if (this.iSession != null) {
            Log.e(TAG, "Cannot be notified about the connection result many times. Probably a bug or malicious app.");
            getInstance().release();
            return;
        }
        this.iSession = connectionState.sessionBinder;
        this.sessionActivity = connectionState.sessionActivity;
        this.sessionCommands = connectionState.sessionCommands;
        Player.Commands commands = connectionState.playerCommandsFromSession;
        this.playerCommandsFromSession = commands;
        Player.Commands commands2 = connectionState.playerCommandsFromPlayer;
        this.playerCommandsFromPlayer = commands2;
        Player.Commands commandsCreateIntersectedCommandsEnsuringCommandReleaseAvailable = createIntersectedCommandsEnsuringCommandReleaseAvailable(commands, commands2);
        this.intersectedPlayerCommands = commandsCreateIntersectedCommandsEnsuringCommandReleaseAvailable;
        com.google.common.collect.h1 h1Var = connectionState.customLayout;
        this.customLayoutOriginal = h1Var;
        com.google.common.collect.h1 h1Var2 = connectionState.mediaButtonPreferences;
        this.mediaButtonPreferencesOriginal = h1Var2;
        com.google.common.collect.h1 h1VarResolveMediaButtonPreferences = resolveMediaButtonPreferences(h1Var2, h1Var, this.sessionCommands, commandsCreateIntersectedCommandsEnsuringCommandReleaseAvailable, connectionState.sessionExtras);
        this.resolvedMediaButtonPreferences = h1VarResolveMediaButtonPreferences;
        this.resolvedCustomLayout = resolveCustomLayout(h1VarResolveMediaButtonPreferences, this.customLayoutOriginal, connectionState.sessionExtras, this.sessionCommands, this.intersectedPlayerCommands);
        com.google.common.collect.k1 k1Var = new com.google.common.collect.k1(4);
        for (int i10 = 0; i10 < connectionState.commandButtonsForMediaItems.size(); i10++) {
            CommandButton commandButton = (CommandButton) connectionState.commandButtonsForMediaItems.get(i10);
            SessionCommand sessionCommand = commandButton.sessionCommand;
            if (sessionCommand != null && sessionCommand.commandCode == 0) {
                k1Var.c(sessionCommand.customAction, commandButton);
            }
        }
        this.commandButtonsForMediaItemsMap = k1Var.a(true);
        this.playerInfo = connectionState.playerInfo;
        MediaSession.Token platformToken = connectionState.platformToken;
        if (platformToken == null) {
            platformToken = this.token.getPlatformToken();
        }
        MediaSession.Token token = platformToken;
        if (token != null) {
            this.platformController = new android.media.session.MediaController(this.context, token);
        }
        try {
            connectionState.sessionBinder.asBinder().linkToDeath(this.deathRecipient, 0);
            this.connectedToken = new SessionToken(this.token.getUid(), 0, connectionState.libraryVersion, connectionState.sessionInterfaceVersion, this.token.getPackageName(), connectionState.sessionBinder, connectionState.tokenExtras, token);
            this.sessionExtras = connectionState.sessionExtras;
            getInstance().notifyAccepted();
        } catch (RemoteException unused) {
            getInstance().release();
        }
    }

    public void onCustomCommand(int i10, SessionCommand sessionCommand, Bundle bundle) {
        if (isConnected()) {
            getInstance().notifyControllerListener(new w0(i10, sessionCommand, bundle, this));
        }
    }

    public void onCustomCommandProgressUpdate(int i10, SessionCommand sessionCommand, Bundle bundle, Bundle bundle2) {
        MediaController.ProgressListener progressListener;
        if (isConnected() && (progressListener = this.pendingCustomActionProgressListeners.get(i10)) != null) {
            progressListener.onProgress(getInstance(), sessionCommand, bundle, bundle2);
        }
    }

    public void onError(int i10, SessionError sessionError) {
        if (isConnected()) {
            getInstance().notifyControllerListener(new k(this, sessionError, 8));
        }
    }

    public void onExtrasChanged(Bundle bundle) {
        if (isConnected()) {
            com.google.common.collect.h1 h1Var = this.resolvedMediaButtonPreferences;
            com.google.common.collect.h1 h1Var2 = this.resolvedCustomLayout;
            this.sessionExtras = bundle;
            com.google.common.collect.h1 h1VarResolveMediaButtonPreferences = resolveMediaButtonPreferences(this.mediaButtonPreferencesOriginal, this.customLayoutOriginal, this.sessionCommands, this.intersectedPlayerCommands, bundle);
            this.resolvedMediaButtonPreferences = h1VarResolveMediaButtonPreferences;
            this.resolvedCustomLayout = resolveCustomLayout(h1VarResolveMediaButtonPreferences, this.customLayoutOriginal, this.sessionExtras, this.sessionCommands, this.intersectedPlayerCommands);
            getInstance().notifyControllerListener(new o0(this, !this.resolvedCustomLayout.equals(h1Var2), bundle, !this.resolvedMediaButtonPreferences.equals(h1Var)));
        }
    }

    public void onPlayerInfoChanged(PlayerInfo playerInfo, PlayerInfo.BundlingExclusions bundlingExclusions) {
        if (isConnected()) {
            SessionToken sessionToken = this.connectedToken;
            sessionToken.getClass();
            boolean z = sessionToken.getInterfaceVersion() < 6;
            PlayerInfo playerInfo2 = this.pendingPlayerInfo;
            if (playerInfo2 != null) {
                this.pendingPlayerInfo = MediaUtils.mergePlayerInfo(playerInfo2, playerInfo, bundlingExclusions, this.intersectedPlayerCommands, z);
                if (!this.pendingMaskingSequencedFutureNumbers.isEmpty()) {
                    return;
                }
                playerInfo = this.pendingPlayerInfo;
                bundlingExclusions = PlayerInfo.BundlingExclusions.NONE;
                this.pendingPlayerInfo = null;
            }
            PlayerInfo playerInfo3 = this.playerInfo;
            PlayerInfo playerInfoMergePlayerInfo = MediaUtils.mergePlayerInfo(playerInfo3, playerInfo, bundlingExclusions, this.intersectedPlayerCommands, z);
            this.playerInfo = playerInfoMergePlayerInfo;
            Integer numValueOf = (playerInfo3.oldPositionInfo.equals(playerInfo.oldPositionInfo) && playerInfo3.newPositionInfo.equals(playerInfo.newPositionInfo)) ? null : Integer.valueOf(playerInfoMergePlayerInfo.discontinuityReason);
            Integer numValueOf2 = !Objects.equals(playerInfo3.getCurrentMediaItem(), playerInfoMergePlayerInfo.getCurrentMediaItem()) ? Integer.valueOf(playerInfoMergePlayerInfo.mediaItemTransitionReason) : null;
            Integer numValueOf3 = !playerInfo3.timeline.equals(playerInfoMergePlayerInfo.timeline) ? Integer.valueOf(playerInfoMergePlayerInfo.timelineChangeReason) : null;
            int i10 = playerInfo3.playWhenReadyChangeReason;
            int i11 = playerInfoMergePlayerInfo.playWhenReadyChangeReason;
            notifyPlayerInfoListenersWithReasons(playerInfo3, playerInfoMergePlayerInfo, numValueOf3, (i10 == i11 && playerInfo3.playWhenReady == playerInfoMergePlayerInfo.playWhenReady) ? null : Integer.valueOf(i11), numValueOf, numValueOf2);
        }
    }

    public void onRenderedFirstFrame() {
        this.listeners.sendEvent(26, new androidx.credentials.d(24));
    }

    public void onSetCustomLayout(int i10, List<CommandButton> list) {
        if (isConnected()) {
            com.google.common.collect.h1 h1Var = this.resolvedMediaButtonPreferences;
            com.google.common.collect.h1 h1Var2 = this.resolvedCustomLayout;
            this.customLayoutOriginal = com.google.common.collect.h1.n(list);
            com.google.common.collect.h1 h1VarResolveMediaButtonPreferences = resolveMediaButtonPreferences(this.mediaButtonPreferencesOriginal, list, this.sessionCommands, this.intersectedPlayerCommands, this.sessionExtras);
            this.resolvedMediaButtonPreferences = h1VarResolveMediaButtonPreferences;
            this.resolvedCustomLayout = resolveCustomLayout(h1VarResolveMediaButtonPreferences, list, this.sessionExtras, this.sessionCommands, this.intersectedPlayerCommands);
            getInstance().notifyControllerListener(new s0(this, !this.resolvedCustomLayout.equals(h1Var2), !this.resolvedMediaButtonPreferences.equals(h1Var), i10, 1));
        }
    }

    public void onSetMediaButtonPreferences(int i10, List<CommandButton> list) {
        if (isConnected()) {
            com.google.common.collect.h1 h1Var = this.resolvedMediaButtonPreferences;
            com.google.common.collect.h1 h1Var2 = this.resolvedCustomLayout;
            this.mediaButtonPreferencesOriginal = com.google.common.collect.h1.n(list);
            com.google.common.collect.h1 h1VarResolveMediaButtonPreferences = resolveMediaButtonPreferences(list, this.customLayoutOriginal, this.sessionCommands, this.intersectedPlayerCommands, this.sessionExtras);
            this.resolvedMediaButtonPreferences = h1VarResolveMediaButtonPreferences;
            this.resolvedCustomLayout = resolveCustomLayout(h1VarResolveMediaButtonPreferences, this.customLayoutOriginal, this.sessionExtras, this.sessionCommands, this.intersectedPlayerCommands);
            getInstance().notifyControllerListener(new s0(this, !this.resolvedCustomLayout.equals(h1Var2), !this.resolvedMediaButtonPreferences.equals(h1Var), i10, 0));
        }
    }

    public void onSetSessionActivity(int i10, PendingIntent pendingIntent) {
        if (!isConnected() || Objects.equals(this.sessionActivity, pendingIntent)) {
            return;
        }
        this.sessionActivity = pendingIntent;
        getInstance().notifyControllerListener(new k(this, pendingIntent, 7));
    }

    public void onSurfaceSizeChanged(int i10, int i11) {
        if (this.surfaceSize.getWidth() == i10 && this.surfaceSize.getHeight() == i11) {
            return;
        }
        this.surfaceSize = new Size(i10, i11);
        this.listeners.sendEvent(24, new u(i10, i11, 0));
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public void pause() {
        if (isPlayerCommandAvailable(1)) {
            dispatchRemoteSessionTaskWithPlayerCommand(new q(this, 13));
            setPlayWhenReady(false, 1);
        }
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public void play() {
        if (!isPlayerCommandAvailable(1)) {
            Log.w(TAG, "Calling play() omitted due to COMMAND_PLAY_PAUSE not being available. If this play command has started the service for instance for playback resumption, this may prevent the service from being started into the foreground.");
        } else {
            dispatchRemoteSessionTaskWithPlayerCommand(new q(this, 16));
            setPlayWhenReady(true, 1);
        }
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public void prepare() {
        if (isPlayerCommandAvailable(2)) {
            dispatchRemoteSessionTaskWithPlayerCommand(new q(this, 18));
            PlayerInfo playerInfo = this.playerInfo;
            if (playerInfo.playbackState == 1) {
                updatePlayerInfo(playerInfo.copyWithPlaybackState(playerInfo.timeline.isEmpty() ? 4 : 2, null), null, null, null, null);
            }
        }
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public void release() {
        IMediaSession iMediaSession = this.iSession;
        if (this.released) {
            return;
        }
        this.released = true;
        this.connectedToken = null;
        this.fallbackPlaybackInfoUpdateHandler.removeCallbacksAndMessages(null);
        clearSurfacesAndCallbacks();
        this.flushCommandQueueHandler.release();
        this.iSession = null;
        if (iMediaSession != null) {
            int iObtainNextSequenceNumber = this.sequencedFutureManager.obtainNextSequenceNumber();
            try {
                iMediaSession.asBinder().unlinkToDeath(this.deathRecipient, 0);
                iMediaSession.release(this.controllerStub, iObtainNextSequenceNumber);
            } catch (RemoteException unused) {
            }
        }
        this.listeners.release();
        this.sequencedFutureManager.lazyRelease(30000L, new m0(this, 1));
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public void removeListener(Player.Listener listener) {
        this.listeners.remove(listener);
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public void removeMediaItem(int i10) {
        if (isPlayerCommandAvailable(20)) {
            ac.b.j(i10 >= 0);
            dispatchRemoteSessionTaskWithPlayerCommand(new p(i10, 8, this));
            removeMediaItemsInternal(i10, i10 + 1);
        }
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public void removeMediaItems(int i10, int i11) {
        if (isPlayerCommandAvailable(20)) {
            ac.b.j(i10 >= 0 && i11 >= i10);
            dispatchRemoteSessionTaskWithPlayerCommand(new x(this, i10, i11, 1));
            removeMediaItemsInternal(i10, i11);
        }
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public void replaceMediaItem(int i10, MediaItem mediaItem) {
        if (isPlayerCommandAvailable(20)) {
            ac.b.j(i10 >= 0);
            dispatchRemoteSessionTaskWithPlayerCommand(new d0(this, i10, mediaItem, 0));
            replaceMediaItemsInternal(i10, i10 + 1, com.google.common.collect.h1.s(mediaItem));
        }
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public void replaceMediaItems(int i10, int i11, List<MediaItem> list) {
        if (isPlayerCommandAvailable(20)) {
            ac.b.j(i10 >= 0 && i10 <= i11);
            dispatchRemoteSessionTaskWithPlayerCommand(new h0(this, list, i10, i11, 1));
            replaceMediaItemsInternal(i10, i11, list);
        }
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public void seekBack() {
        if (isPlayerCommandAvailable(11)) {
            dispatchRemoteSessionTaskWithPlayerCommand(new q(this, 12));
            seekToInternalByOffset(-getSeekBackIncrement());
        }
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public void seekForward() {
        if (isPlayerCommandAvailable(12)) {
            dispatchRemoteSessionTaskWithPlayerCommand(new q(this, 6));
            seekToInternalByOffset(getSeekForwardIncrement());
        }
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public void seekTo(long j10) {
        if (isPlayerCommandAvailable(5)) {
            dispatchRemoteSessionTaskWithPlayerCommand(new g0(this, j10));
            seekToInternal(getCurrentMediaItemIndex(), j10);
        }
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public void seekToDefaultPosition() {
        if (isPlayerCommandAvailable(4)) {
            dispatchRemoteSessionTaskWithPlayerCommand(new q(this, 7));
            seekToInternal(getCurrentMediaItemIndex(), C.TIME_UNSET);
        }
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public void seekToNext() {
        if (isPlayerCommandAvailable(9)) {
            dispatchRemoteSessionTaskWithPlayerCommand(new q(this, 15));
            Timeline currentTimeline = getCurrentTimeline();
            if (currentTimeline.isEmpty() || isPlayingAd()) {
                return;
            }
            if (hasNextMediaItem()) {
                seekToInternal(getNextMediaItemIndex(), C.TIME_UNSET);
                return;
            }
            Timeline.Window window = currentTimeline.getWindow(getCurrentMediaItemIndex(), new Timeline.Window());
            if (window.isDynamic && window.isLive()) {
                seekToInternal(getCurrentMediaItemIndex(), C.TIME_UNSET);
            }
        }
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public void seekToNextMediaItem() {
        if (isPlayerCommandAvailable(8)) {
            dispatchRemoteSessionTaskWithPlayerCommand(new q(this, 4));
            if (getNextMediaItemIndex() != -1) {
                seekToInternal(getNextMediaItemIndex(), C.TIME_UNSET);
            }
        }
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public void seekToPrevious() {
        if (isPlayerCommandAvailable(7)) {
            dispatchRemoteSessionTaskWithPlayerCommand(new q(this, 9));
            Timeline currentTimeline = getCurrentTimeline();
            if (currentTimeline.isEmpty() || isPlayingAd()) {
                return;
            }
            boolean zHasPreviousMediaItem = hasPreviousMediaItem();
            Timeline.Window window = currentTimeline.getWindow(getCurrentMediaItemIndex(), new Timeline.Window());
            if (window.isDynamic && window.isLive()) {
                if (zHasPreviousMediaItem) {
                    seekToInternal(getPreviousMediaItemIndex(), C.TIME_UNSET);
                }
            } else if (!zHasPreviousMediaItem || getCurrentPosition() > getMaxSeekToPreviousPosition()) {
                seekToInternal(getCurrentMediaItemIndex(), 0L);
            } else {
                seekToInternal(getPreviousMediaItemIndex(), C.TIME_UNSET);
            }
        }
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public void seekToPreviousMediaItem() {
        if (isPlayerCommandAvailable(6)) {
            dispatchRemoteSessionTaskWithPlayerCommand(new q(this, 8));
            if (getPreviousMediaItemIndex() != -1) {
                seekToInternal(getPreviousMediaItemIndex(), C.TIME_UNSET);
            }
        }
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public com.google.common.util.concurrent.d1<SessionResult> sendCustomCommand(SessionCommand sessionCommand, Bundle bundle) {
        SessionToken sessionToken = this.connectedToken;
        sessionToken.getClass();
        return sessionToken.getInterfaceVersion() >= 7 ? sendCustomCommand(sessionCommand, bundle, null) : dispatchRemoteSessionTaskWithSessionCommand(sessionCommand, new j(this, sessionCommand, bundle, 3));
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public void setAudioAttributes(AudioAttributes audioAttributes, boolean z) {
        if (isPlayerCommandAvailable(35)) {
            dispatchRemoteSessionTaskWithPlayerCommand(new a0(this, audioAttributes, z, 1));
            if (this.playerInfo.audioAttributes.equals(audioAttributes)) {
                return;
            }
            this.playerInfo = this.playerInfo.copyWithAudioAttributes(audioAttributes);
            this.listeners.queueEvent(20, new c0(audioAttributes));
            this.listeners.flushEvents();
        }
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    @Deprecated
    public void setDeviceMuted(boolean z) {
        if (isPlayerCommandAvailable(26)) {
            dispatchRemoteSessionTaskWithPlayerCommand(new w(this, z, 3));
            PlayerInfo playerInfo = this.playerInfo;
            if (playerInfo.deviceMuted != z) {
                this.playerInfo = playerInfo.copyWithDeviceVolume(playerInfo.deviceVolume, z);
                this.listeners.queueEvent(30, new w(this, z, 4));
                this.listeners.flushEvents();
            }
        }
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    @Deprecated
    public void setDeviceVolume(int i10) {
        if (isPlayerCommandAvailable(25)) {
            dispatchRemoteSessionTaskWithPlayerCommand(new p(i10, 2, this));
            DeviceInfo deviceInfo = getDeviceInfo();
            PlayerInfo playerInfo = this.playerInfo;
            if (playerInfo.deviceVolume == i10 || deviceInfo.minVolume > i10) {
                return;
            }
            int i11 = deviceInfo.maxVolume;
            if (i11 == 0 || i10 <= i11) {
                this.playerInfo = playerInfo.copyWithDeviceVolume(i10, playerInfo.deviceMuted);
                this.listeners.queueEvent(30, new p(i10, 3, this));
                this.listeners.flushEvents();
            }
        }
    }

    public <T> void setFutureResult(final int i10, T t2) {
        this.sequencedFutureManager.setFutureResult(i10, t2);
        getInstance().runOnApplicationLooper(new Runnable() { // from class: androidx.media3.session.y0
            @Override // java.lang.Runnable
            public final void run() {
                this.f4212i.lambda$setFutureResult$107(i10);
            }
        });
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public void setMediaItem(MediaItem mediaItem) {
        if (isPlayerCommandAvailable(31)) {
            dispatchRemoteSessionTaskWithPlayerCommand(new j0(this, mediaItem, 0));
            setMediaItemsInternal(Collections.singletonList(mediaItem), -1, C.TIME_UNSET, true);
        }
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public void setMediaItems(List<MediaItem> list) {
        if (isPlayerCommandAvailable(20)) {
            dispatchRemoteSessionTaskWithPlayerCommand(new e0(1, list, this));
            setMediaItemsInternal(list, -1, C.TIME_UNSET, true);
        }
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public void setPlayWhenReady(boolean z) {
        if (isPlayerCommandAvailable(1)) {
            dispatchRemoteSessionTaskWithPlayerCommand(new w(this, z, 0));
            setPlayWhenReady(z, 1);
        } else if (z) {
            Log.w(TAG, "Calling play() omitted due to COMMAND_PLAY_PAUSE not being available. If this play command has started the service for instance for playback resumption, this may prevent the service from being started into the foreground.");
        }
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public void setPlaybackParameters(PlaybackParameters playbackParameters) {
        if (isPlayerCommandAvailable(13)) {
            dispatchRemoteSessionTaskWithPlayerCommand(new k(this, playbackParameters, 3));
            if (this.playerInfo.playbackParameters.equals(playbackParameters)) {
                return;
            }
            this.playerInfo = this.playerInfo.copyWithPlaybackParameters(playbackParameters);
            this.listeners.queueEvent(12, new f0(0, playbackParameters));
            this.listeners.flushEvents();
        }
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public void setPlaybackSpeed(float f10) {
        if (isPlayerCommandAvailable(13)) {
            dispatchRemoteSessionTaskWithPlayerCommand(new s(this, f10, 1));
            PlaybackParameters playbackParameters = this.playerInfo.playbackParameters;
            if (playbackParameters.speed != f10) {
                PlaybackParameters playbackParametersWithSpeed = playbackParameters.withSpeed(f10);
                this.playerInfo = this.playerInfo.copyWithPlaybackParameters(playbackParametersWithSpeed);
                this.listeners.queueEvent(12, new f0(1, playbackParametersWithSpeed));
                this.listeners.flushEvents();
            }
        }
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public void setPlaylistMetadata(MediaMetadata mediaMetadata) {
        if (isPlayerCommandAvailable(19)) {
            dispatchRemoteSessionTaskWithPlayerCommand(new k(this, mediaMetadata, 5));
            if (this.playerInfo.playlistMetadata.equals(mediaMetadata)) {
                return;
            }
            this.playerInfo = this.playerInfo.copyWithPlaylistMetadata(mediaMetadata);
            this.listeners.queueEvent(15, new i0(0, mediaMetadata));
            this.listeners.flushEvents();
        }
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public com.google.common.util.concurrent.d1<SessionResult> setRating(String str, Rating rating) {
        return dispatchRemoteSessionTaskWithSessionCommand(SessionCommand.COMMAND_CODE_SESSION_SET_RATING, new j(this, str, rating, 2));
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public void setRepeatMode(int i10) {
        if (isPlayerCommandAvailable(15)) {
            dispatchRemoteSessionTaskWithPlayerCommand(new p(i10, 1, this));
            PlayerInfo playerInfo = this.playerInfo;
            if (playerInfo.repeatMode != i10) {
                this.playerInfo = playerInfo.copyWithRepeatMode(i10);
                this.listeners.queueEvent(8, new z(i10, 0));
                this.listeners.flushEvents();
            }
        }
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public void setShuffleModeEnabled(boolean z) {
        if (isPlayerCommandAvailable(14)) {
            dispatchRemoteSessionTaskWithPlayerCommand(new w(this, z, 2));
            PlayerInfo playerInfo = this.playerInfo;
            if (playerInfo.shuffleModeEnabled != z) {
                this.playerInfo = playerInfo.copyWithShuffleModeEnabled(z);
                this.listeners.queueEvent(9, new r0(z, 0));
                this.listeners.flushEvents();
            }
        }
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public void setTrackSelectionParameters(TrackSelectionParameters trackSelectionParameters) {
        if (isPlayerCommandAvailable(29)) {
            dispatchRemoteSessionTaskWithPlayerCommand(new k(this, trackSelectionParameters, 1));
            PlayerInfo playerInfo = this.playerInfo;
            if (trackSelectionParameters != playerInfo.trackSelectionParameters) {
                this.playerInfo = playerInfo.copyWithTrackSelectionParameters(trackSelectionParameters);
                this.listeners.queueEvent(19, new b0(trackSelectionParameters));
                this.listeners.flushEvents();
            }
        }
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public void setVideoSurface(Surface surface) {
        if (isPlayerCommandAvailable(27)) {
            clearSurfacesAndCallbacks();
            this.videoSurface = surface;
            int i10 = surface == null ? 0 : -1;
            setVideoSurfaceWithSize(surface, i10, i10);
            onSurfaceSizeChanged(i10, i10);
        }
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public void setVideoSurfaceHolder(SurfaceHolder surfaceHolder) {
        if (isPlayerCommandAvailable(27)) {
            if (surfaceHolder == null) {
                clearVideoSurface();
                return;
            }
            if (this.videoSurfaceHolder == surfaceHolder) {
                return;
            }
            clearSurfacesAndCallbacks();
            this.videoSurfaceHolder = surfaceHolder;
            surfaceHolder.addCallback(this.surfaceCallback);
            Surface surface = surfaceHolder.getSurface();
            if (surface == null || !surface.isValid()) {
                this.videoSurface = null;
                setVideoSurfaceWithSize(null, 0, 0);
                onSurfaceSizeChanged(0, 0);
            } else {
                this.videoSurface = surface;
                Rect surfaceFrame = surfaceHolder.getSurfaceFrame();
                setVideoSurfaceWithSize(surface, surfaceFrame.width(), surfaceFrame.height());
                onSurfaceSizeChanged(surfaceFrame.width(), surfaceFrame.height());
            }
        }
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public void setVideoSurfaceView(SurfaceView surfaceView) {
        if (isPlayerCommandAvailable(27)) {
            setVideoSurfaceHolder(surfaceView == null ? null : surfaceView.getHolder());
        }
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public void setVideoTextureView(TextureView textureView) {
        if (isPlayerCommandAvailable(27)) {
            if (textureView == null) {
                clearVideoSurface();
                return;
            }
            if (this.videoTextureView == textureView) {
                return;
            }
            clearSurfacesAndCallbacks();
            this.videoTextureView = textureView;
            textureView.setSurfaceTextureListener(this.surfaceCallback);
            SurfaceTexture surfaceTexture = textureView.getSurfaceTexture();
            if (surfaceTexture == null) {
                setVideoSurfaceWithSize(null, 0, 0);
                onSurfaceSizeChanged(0, 0);
            } else {
                Surface surface = new Surface(surfaceTexture);
                this.videoSurface = surface;
                setVideoSurfaceWithSize(surface, textureView.getWidth(), textureView.getHeight());
                onSurfaceSizeChanged(textureView.getWidth(), textureView.getHeight());
            }
        }
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public void setVolume(float f10) {
        if (isPlayerCommandAvailable(24)) {
            dispatchRemoteSessionTaskWithPlayerCommand(new s(this, f10, 2));
            PlayerInfo playerInfo = this.playerInfo;
            if (playerInfo.volume != f10) {
                this.playerInfo = playerInfo.copyWithVolume(f10);
                this.listeners.queueEvent(22, new t(f10, 1));
                this.listeners.flushEvents();
            }
        }
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public void stop() {
        if (isPlayerCommandAvailable(3)) {
            dispatchRemoteSessionTaskWithPlayerCommand(new q(this, 17));
            PlayerInfo playerInfo = this.playerInfo;
            SessionPositionInfo sessionPositionInfo = this.playerInfo.sessionPositionInfo;
            Player.PositionInfo positionInfo = sessionPositionInfo.positionInfo;
            boolean z = sessionPositionInfo.isPlayingAd;
            long jElapsedRealtime = SystemClock.elapsedRealtime();
            SessionPositionInfo sessionPositionInfo2 = this.playerInfo.sessionPositionInfo;
            long j10 = sessionPositionInfo2.durationMs;
            long j11 = sessionPositionInfo2.positionInfo.positionMs;
            int iCalculateBufferedPercentage = MediaUtils.calculateBufferedPercentage(j11, j10);
            SessionPositionInfo sessionPositionInfo3 = this.playerInfo.sessionPositionInfo;
            PlayerInfo playerInfoCopyWithSessionPositionInfo = playerInfo.copyWithSessionPositionInfo(new SessionPositionInfo(positionInfo, z, jElapsedRealtime, j10, j11, iCalculateBufferedPercentage, 0L, sessionPositionInfo3.currentLiveOffsetMs, sessionPositionInfo3.contentDurationMs, sessionPositionInfo3.positionInfo.positionMs));
            this.playerInfo = playerInfoCopyWithSessionPositionInfo;
            if (playerInfoCopyWithSessionPositionInfo.playbackState != 1) {
                this.playerInfo = playerInfoCopyWithSessionPositionInfo.copyWithPlaybackState(1, playerInfoCopyWithSessionPositionInfo.playerError);
                this.listeners.queueEvent(4, new v(1));
                this.listeners.flushEvents();
            }
        }
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public void unmute() {
        if (isPlayerCommandAvailable(24)) {
            float f10 = this.playerInfo.unmuteVolume;
            dispatchRemoteSessionTaskWithPlayerCommand(new s(this, f10, 0));
            PlayerInfo playerInfo = this.playerInfo;
            float f11 = playerInfo.volume;
            if (f11 == playerInfo.unmuteVolume || f11 != 0.0f) {
                return;
            }
            this.playerInfo = playerInfo.copyWithVolume(f10);
            this.listeners.queueEvent(22, new t(f10, 0));
            this.listeners.flushEvents();
        }
    }

    private com.google.common.util.concurrent.d1<SessionResult> dispatchRemoteSessionTaskWithSessionCommand(SessionCommand sessionCommand, RemoteSessionTask remoteSessionTask) {
        return dispatchRemoteSessionTaskWithSessionCommandInternal(0, sessionCommand, remoteSessionTask);
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public com.google.common.util.concurrent.d1<SessionResult> setRating(Rating rating) {
        return dispatchRemoteSessionTaskWithSessionCommand(SessionCommand.COMMAND_CODE_SESSION_SET_RATING, new k(this, rating, 2));
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public void addMediaItems(int i10, List<MediaItem> list) {
        if (isPlayerCommandAvailable(20)) {
            ac.b.j(i10 >= 0);
            dispatchRemoteSessionTaskWithPlayerCommand(new x0(this, i10, list));
            addMediaItemsInternal(i10, list);
        }
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public void seekTo(int i10, long j10) {
        if (isPlayerCommandAvailable(10)) {
            ac.b.j(i10 >= 0);
            dispatchRemoteSessionTaskWithPlayerCommand(new z0(i10, j10, this));
            seekToInternal(i10, j10);
        }
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public void seekToDefaultPosition(int i10) {
        if (isPlayerCommandAvailable(10)) {
            ac.b.j(i10 >= 0);
            dispatchRemoteSessionTaskWithPlayerCommand(new p(i10, 0, this));
            seekToInternal(i10, C.TIME_UNSET);
        }
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public void setMediaItems(List<MediaItem> list, boolean z) {
        if (isPlayerCommandAvailable(20)) {
            dispatchRemoteSessionTaskWithPlayerCommand(new a0(this, list, z, 2));
            setMediaItemsInternal(list, -1, C.TIME_UNSET, z);
        }
    }

    private void setPlayWhenReady(boolean z, int i10) {
        int playbackSuppressionReason = getPlaybackSuppressionReason();
        if (playbackSuppressionReason == 1) {
            playbackSuppressionReason = 0;
        }
        PlayerInfo playerInfo = this.playerInfo;
        if (playerInfo.playWhenReady == z && playerInfo.playbackSuppressionReason == playbackSuppressionReason) {
            return;
        }
        this.currentPositionMs = MediaUtils.getUpdatedCurrentPositionMs(playerInfo, this.currentPositionMs, this.lastSetPlayWhenReadyCalledTimeMs, getInstance().getTimeDiffMs());
        this.lastSetPlayWhenReadyCalledTimeMs = SystemClock.elapsedRealtime();
        updatePlayerInfo(this.playerInfo.copyWithPlayWhenReady(z, i10, playbackSuppressionReason), null, Integer.valueOf(i10), null, null);
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public void addMediaItem(int i10, MediaItem mediaItem) {
        if (isPlayerCommandAvailable(20)) {
            ac.b.j(i10 >= 0);
            dispatchRemoteSessionTaskWithPlayerCommand(new d0(this, i10, mediaItem, 1));
            addMediaItemsInternal(i10, Collections.singletonList(mediaItem));
        }
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public void clearVideoSurface(Surface surface) {
        if (isPlayerCommandAvailable(27) && surface != null && this.videoSurface == surface) {
            clearVideoSurface();
        }
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public void setMediaItem(final MediaItem mediaItem, final long j10) {
        if (isPlayerCommandAvailable(31)) {
            dispatchRemoteSessionTaskWithPlayerCommand(new RemoteSessionTask() { // from class: androidx.media3.session.t0
                @Override // androidx.media3.session.MediaControllerImplBase.RemoteSessionTask
                public final void run(IMediaSession iMediaSession, int i10) throws RemoteException {
                    this.f4154i.lambda$setMediaItem$24(mediaItem, j10, iMediaSession, i10);
                }
            });
            setMediaItemsInternal(Collections.singletonList(mediaItem), -1, j10, false);
        }
    }

    private static PlayerInfo maskTimelineAndPositionInfo(PlayerInfo playerInfo, Timeline timeline, Player.PositionInfo positionInfo, SessionPositionInfo sessionPositionInfo, int i10) {
        return new PlayerInfo.Builder(playerInfo).setTimeline(timeline).setOldPositionInfo(playerInfo.sessionPositionInfo.positionInfo).setNewPositionInfo(positionInfo).setSessionPositionInfo(sessionPositionInfo).setDiscontinuityReason(i10).build();
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public com.google.common.util.concurrent.d1<SessionResult> sendCustomCommand(SessionCommand sessionCommand, Bundle bundle, MediaController.ProgressListener progressListener) {
        SessionToken sessionToken = this.connectedToken;
        sessionToken.getClass();
        if (sessionToken.getInterfaceVersion() < 7) {
            return sendCustomCommand(sessionCommand, bundle);
        }
        return dispatchRemoteSessionTaskWithSessionCommand(sessionCommand, new l(this, progressListener, sessionCommand, bundle));
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public void setDeviceMuted(final boolean z, final int i10) {
        if (isPlayerCommandAvailable(34)) {
            dispatchRemoteSessionTaskWithPlayerCommand(new RemoteSessionTask() { // from class: androidx.media3.session.p0
                @Override // androidx.media3.session.MediaControllerImplBase.RemoteSessionTask
                public final void run(IMediaSession iMediaSession, int i11) throws RemoteException {
                    this.f4103i.lambda$setDeviceMuted$70(z, i10, iMediaSession, i11);
                }
            });
            PlayerInfo playerInfo = this.playerInfo;
            if (playerInfo.deviceMuted != z) {
                this.playerInfo = playerInfo.copyWithDeviceVolume(playerInfo.deviceVolume, z);
                this.listeners.queueEvent(30, new w(this, z, 1));
                this.listeners.flushEvents();
            }
        }
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public void setMediaItems(final List<MediaItem> list, final int i10, final long j10) {
        if (isPlayerCommandAvailable(20)) {
            dispatchRemoteSessionTaskWithPlayerCommand(new RemoteSessionTask() { // from class: androidx.media3.session.y
                @Override // androidx.media3.session.MediaControllerImplBase.RemoteSessionTask
                public final void run(IMediaSession iMediaSession, int i11) throws RemoteException {
                    this.f4208i.lambda$setMediaItems$28(list, i10, j10, iMediaSession, i11);
                }
            });
            setMediaItemsInternal(list, i10, j10, false);
        }
    }

    private static PeriodInfo getPeriodInfo(Timeline timeline, Timeline.Window window, Timeline.Period period, int i10, long j10) {
        ac.b.n(i10, timeline.getWindowCount());
        timeline.getWindow(i10, window);
        if (j10 == C.TIME_UNSET) {
            j10 = window.getDefaultPositionUs();
            if (j10 == C.TIME_UNSET) {
                return null;
            }
        }
        int i11 = window.firstPeriodIndex;
        timeline.getPeriod(i11, period);
        while (i11 < window.lastPeriodIndex && period.positionInWindowUs != j10) {
            int i12 = i11 + 1;
            if (timeline.getPeriod(i12, period).positionInWindowUs > j10) {
                break;
            }
            i11 = i12;
        }
        timeline.getPeriod(i11, period);
        return new PeriodInfo(i11, j10 - period.positionInWindowUs);
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public void decreaseDeviceVolume(int i10) {
        if (isPlayerCommandAvailable(34)) {
            dispatchRemoteSessionTaskWithPlayerCommand(new p(i10, 5, this));
            int i11 = this.playerInfo.deviceVolume - 1;
            if (i11 >= getDeviceInfo().minVolume) {
                PlayerInfo playerInfo = this.playerInfo;
                this.playerInfo = playerInfo.copyWithDeviceVolume(i11, playerInfo.deviceMuted);
                this.listeners.queueEvent(30, new p(i11, 6, this));
                this.listeners.flushEvents();
            }
        }
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public void increaseDeviceVolume(int i10) {
        if (isPlayerCommandAvailable(34)) {
            dispatchRemoteSessionTaskWithPlayerCommand(new p(i10, 10, this));
            int i11 = this.playerInfo.deviceVolume + 1;
            int i12 = getDeviceInfo().maxVolume;
            if (i12 == 0 || i11 <= i12) {
                PlayerInfo playerInfo = this.playerInfo;
                this.playerInfo = playerInfo.copyWithDeviceVolume(i11, playerInfo.deviceMuted);
                this.listeners.queueEvent(30, new p(i11, 11, this));
                this.listeners.flushEvents();
            }
        }
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public void setDeviceVolume(int i10, int i11) {
        if (isPlayerCommandAvailable(33)) {
            dispatchRemoteSessionTaskWithPlayerCommand(new x(this, i10, i11, 2));
            DeviceInfo deviceInfo = getDeviceInfo();
            PlayerInfo playerInfo = this.playerInfo;
            if (playerInfo.deviceVolume == i10 || deviceInfo.minVolume > i10) {
                return;
            }
            int i12 = deviceInfo.maxVolume;
            if (i12 == 0 || i10 <= i12) {
                this.playerInfo = playerInfo.copyWithDeviceVolume(i10, playerInfo.deviceMuted);
                this.listeners.queueEvent(30, new p(i10, 7, this));
                this.listeners.flushEvents();
            }
        }
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public void setMediaItem(MediaItem mediaItem, boolean z) {
        if (isPlayerCommandAvailable(31)) {
            dispatchRemoteSessionTaskWithPlayerCommand(new a0(this, mediaItem, z, 0));
            setMediaItemsInternal(Collections.singletonList(mediaItem), -1, C.TIME_UNSET, z);
        }
    }

    public IMediaSession getSessionInterfaceWithSessionCommandIfAble(SessionCommand sessionCommand) {
        ac.b.j(sessionCommand.commandCode == 0);
        if (!this.sessionCommands.contains(sessionCommand) && !CommandButton.isPredefinedCustomCommandButtonCode(sessionCommand.customAction)) {
            Log.w(TAG, "Controller isn't allowed to call custom session command:" + sessionCommand.customAction);
            return null;
        }
        return this.iSession;
    }
}
