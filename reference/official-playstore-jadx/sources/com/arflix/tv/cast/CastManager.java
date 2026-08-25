package com.arflix.tv.cast;

import a0.c;
import a3.b;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import androidx.core.content.e;
import androidx.core.os.i;
import androidx.mediarouter.media.z;
import com.arflix.tv.BuildConfig;
import com.arflix.tv.cast.CastManager;
import com.google.android.gms.cast.CastDevice;
import com.google.android.gms.cast.CastMediaControlIntent;
import com.google.android.gms.cast.MediaInfo;
import com.google.android.gms.cast.MediaLoadRequestData;
import com.google.android.gms.cast.MediaMetadata;
import com.google.android.gms.cast.MediaSeekOptions;
import com.google.android.gms.cast.framework.CastContext;
import com.google.android.gms.cast.framework.CastSession;
import com.google.android.gms.cast.framework.SessionManager;
import com.google.android.gms.cast.framework.SessionManagerListener;
import com.google.android.gms.cast.framework.media.RemoteMediaClient;
import dagger.hilt.android.qualifiers.ApplicationContext;
import io.ktor.http.LinkHeader;
import java.util.ArrayList;
import java.util.concurrent.Executor;
import javax.inject.Inject;
import javax.inject.Singleton;
import kotlin.Metadata;
import kotlin.jvm.internal.h;
import kotlin.jvm.internal.p;
import na.h1;
import na.j1;
import na.q0;
import na.y0;
import x6.t0;

/* JADX INFO: loaded from: classes3.dex */
@Singleton
@Metadata(d1 = {"\u0000a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\t\n\u0002\b\u0011\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\b\u0005*\u00015\b\u0007\u0018\u00002\u00020\u0001:\u00018B\u0013\b\u0007\u0012\b\b\u0001\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\u0015\u0010\t\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\u0006¢\u0006\u0004\b\t\u0010\nJ7\u0010\u0012\u001a\u00020\b2\u0006\u0010\f\u001a\u00020\u000b2\u0006\u0010\r\u001a\u00020\u000b2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\u000f\u001a\u00020\u000b2\u0006\u0010\u0011\u001a\u00020\u0010¢\u0006\u0004\b\u0012\u0010\u0013J\r\u0010\u0014\u001a\u00020\b¢\u0006\u0004\b\u0014\u0010\u0015J\r\u0010\u0016\u001a\u00020\b¢\u0006\u0004\b\u0016\u0010\u0015J\u0015\u0010\u0017\u001a\u00020\b2\u0006\u0010\u0011\u001a\u00020\u0010¢\u0006\u0004\b\u0017\u0010\u0018J\u0017\u0010\u001a\u001a\u00020\b2\b\b\u0002\u0010\u0019\u001a\u00020\u0010¢\u0006\u0004\b\u001a\u0010\u0018J\u0017\u0010\u001b\u001a\u00020\b2\b\b\u0002\u0010\u0019\u001a\u00020\u0010¢\u0006\u0004\b\u001b\u0010\u0018J\r\u0010\u001c\u001a\u00020\u0010¢\u0006\u0004\b\u001c\u0010\u001dJ\r\u0010\u001e\u001a\u00020\u0010¢\u0006\u0004\b\u001e\u0010\u001dJ\r\u0010\u001f\u001a\u00020\u0006¢\u0006\u0004\b\u001f\u0010 J\r\u0010!\u001a\u00020\b¢\u0006\u0004\b!\u0010\u0015J\r\u0010#\u001a\u00020\"¢\u0006\u0004\b#\u0010$R\u0014\u0010\u0003\u001a\u00020\u00028\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0003\u0010%R\u001a\u0010(\u001a\b\u0012\u0004\u0012\u00020'0&8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b(\u0010)R\u001d\u0010+\u001a\b\u0012\u0004\u0012\u00020'0*8\u0006¢\u0006\f\n\u0004\b+\u0010,\u001a\u0004\b-\u0010.R\u0018\u00100\u001a\u0004\u0018\u00010/8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b0\u00101R\u0018\u00103\u001a\u0004\u0018\u0001028\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b3\u00104R\u0014\u00106\u001a\u0002058\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b6\u00107¨\u00069"}, d2 = {"Lcom/arflix/tv/cast/CastManager;", "", "Landroid/content/Context;", "context", "<init>", "(Landroid/content/Context;)V", "", "isMobile", "Lx6/t0;", "initialize", "(Z)V", "", "url", LinkHeader.Parameters.Title, "imageUrl", "mimeType", "", "positionMs", "loadMedia", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;J)V", BuildConfig.FLAVOR, "()V", "pause", "seekTo", "(J)V", "amountMs", "skipForward", "skipBack", "getApproximatePosition", "()J", "getApproximateDuration", "isRemotePlaying", "()Z", "disconnect", "Landroidx/mediarouter/media/z;", "getRouteSelector", "()Landroidx/mediarouter/media/z;", "Landroid/content/Context;", "Lna/q0;", "Lcom/arflix/tv/cast/CastManager$CastState;", "_castState", "Lna/q0;", "Lna/h1;", "castState", "Lna/h1;", "getCastState", "()Lna/h1;", "Lcom/google/android/gms/cast/framework/CastContext;", "castContext", "Lcom/google/android/gms/cast/framework/CastContext;", "Lcom/google/android/gms/cast/framework/CastSession;", "currentSession", "Lcom/google/android/gms/cast/framework/CastSession;", "com/arflix/tv/cast/CastManager$sessionListener$1", "sessionListener", "Lcom/arflix/tv/cast/CastManager$sessionListener$1;", "CastState", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class CastManager {
    public static final int $stable = 8;
    private final q0<CastState> _castState;
    private CastContext castContext;
    private final h1<CastState> castState;
    private final Context context;
    private CastSession currentSession;
    private final CastManager$sessionListener$1 sessionListener;

    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b7\u0018\u00002\u00020\u0001:\u0004\u0004\u0005\u0006\u0007B\t\b\u0004¢\u0006\u0004\b\u0002\u0010\u0003\u0082\u0001\u0004\b\t\n\u000b¨\u0006\f"}, d2 = {"Lcom/arflix/tv/cast/CastManager$CastState;", "", "<init>", "()V", "NotAvailable", "NotConnected", "Connecting", "Casting", "Lcom/arflix/tv/cast/CastManager$CastState$Casting;", "Lcom/arflix/tv/cast/CastManager$CastState$Connecting;", "Lcom/arflix/tv/cast/CastManager$CastState$NotAvailable;", "Lcom/arflix/tv/cast/CastManager$CastState$NotConnected;", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static abstract class CastState {
        public static final int $stable = 0;

        @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\t\u0010\b\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\t\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0014\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rHÖ\u0083\u0004J\n\u0010\u000e\u001a\u00020\u000fHÖ\u0081\u0004J\n\u0010\u0010\u001a\u00020\u0003HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0011"}, d2 = {"Lcom/arflix/tv/cast/CastManager$CastState$Casting;", "Lcom/arflix/tv/cast/CastManager$CastState;", "deviceName", "", "<init>", "(Ljava/lang/String;)V", "getDeviceName", "()Ljava/lang/String;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
        public static final /* data */ class Casting extends CastState {
            public static final int $stable = 0;
            private final String deviceName;

            public Casting(String str) {
                super(null);
                this.deviceName = str;
            }

            public static /* synthetic */ Casting copy$default(Casting casting, String str, int i10, Object obj) {
                if ((i10 & 1) != 0) {
                    str = casting.deviceName;
                }
                return casting.copy(str);
            }

            /* JADX INFO: renamed from: component1, reason: from getter */
            public final String getDeviceName() {
                return this.deviceName;
            }

            public final Casting copy(String deviceName) {
                return new Casting(deviceName);
            }

            public boolean equals(Object other) {
                if (this == other) {
                    return true;
                }
                return (other instanceof Casting) && p.a(this.deviceName, ((Casting) other).deviceName);
            }

            public final String getDeviceName() {
                return this.deviceName;
            }

            public int hashCode() {
                return this.deviceName.hashCode();
            }

            public String toString() {
                return c.l("Casting(deviceName=", this.deviceName, ")");
            }
        }

        @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\bÇ\n\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0014\u0010\u0004\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007HÖ\u0083\u0004J\n\u0010\b\u001a\u00020\tHÖ\u0081\u0004J\n\u0010\n\u001a\u00020\u000bHÖ\u0081\u0004¨\u0006\f"}, d2 = {"Lcom/arflix/tv/cast/CastManager$CastState$Connecting;", "Lcom/arflix/tv/cast/CastManager$CastState;", "<init>", "()V", "equals", "", "other", "", "hashCode", "", "toString", "", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
        public static final /* data */ class Connecting extends CastState {
            public static final int $stable = 0;
            public static final Connecting INSTANCE = new Connecting();

            private Connecting() {
                super(null);
            }

            public boolean equals(Object other) {
                return this == other || (other instanceof Connecting);
            }

            public int hashCode() {
                return 56640818;
            }

            public String toString() {
                return "Connecting";
            }
        }

        @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\bÇ\n\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0014\u0010\u0004\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007HÖ\u0083\u0004J\n\u0010\b\u001a\u00020\tHÖ\u0081\u0004J\n\u0010\n\u001a\u00020\u000bHÖ\u0081\u0004¨\u0006\f"}, d2 = {"Lcom/arflix/tv/cast/CastManager$CastState$NotAvailable;", "Lcom/arflix/tv/cast/CastManager$CastState;", "<init>", "()V", "equals", "", "other", "", "hashCode", "", "toString", "", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
        public static final /* data */ class NotAvailable extends CastState {
            public static final int $stable = 0;
            public static final NotAvailable INSTANCE = new NotAvailable();

            private NotAvailable() {
                super(null);
            }

            public boolean equals(Object other) {
                return this == other || (other instanceof NotAvailable);
            }

            public int hashCode() {
                return 339578160;
            }

            public String toString() {
                return "NotAvailable";
            }
        }

        @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\bÇ\n\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0014\u0010\u0004\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007HÖ\u0083\u0004J\n\u0010\b\u001a\u00020\tHÖ\u0081\u0004J\n\u0010\n\u001a\u00020\u000bHÖ\u0081\u0004¨\u0006\f"}, d2 = {"Lcom/arflix/tv/cast/CastManager$CastState$NotConnected;", "Lcom/arflix/tv/cast/CastManager$CastState;", "<init>", "()V", "equals", "", "other", "", "hashCode", "", "toString", "", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
        public static final /* data */ class NotConnected extends CastState {
            public static final int $stable = 0;
            public static final NotConnected INSTANCE = new NotConnected();

            private NotConnected() {
                super(null);
            }

            public boolean equals(Object other) {
                return this == other || (other instanceof NotConnected);
            }

            public int hashCode() {
                return 494269808;
            }

            public String toString() {
                return "NotConnected";
            }
        }

        public /* synthetic */ CastState(h hVar) {
            this();
        }

        private CastState() {
        }
    }

    /* JADX WARN: Type inference failed for: r1v4, types: [com.arflix.tv.cast.CastManager$sessionListener$1] */
    @Inject
    public CastManager(@ApplicationContext Context context) {
        this.context = context;
        j1 j1VarB = y0.b(CastState.NotConnected.INSTANCE);
        this._castState = j1VarB;
        this.castState = y0.e(j1VarB);
        this.sessionListener = new SessionManagerListener<CastSession>() { // from class: com.arflix.tv.cast.CastManager$sessionListener$1
            @Override // com.google.android.gms.cast.framework.SessionManagerListener
            public void onSessionEnding(CastSession session) {
            }

            @Override // com.google.android.gms.cast.framework.SessionManagerListener
            public void onSessionSuspended(CastSession session, int reason) {
            }

            @Override // com.google.android.gms.cast.framework.SessionManagerListener
            public void onSessionEnded(CastSession session, int error) {
                this.this$0.currentSession = null;
                this.this$0._castState.setValue(CastManager.CastState.NotConnected.INSTANCE);
            }

            @Override // com.google.android.gms.cast.framework.SessionManagerListener
            public void onSessionResumeFailed(CastSession session, int error) {
                this.this$0.currentSession = null;
                this.this$0._castState.setValue(CastManager.CastState.NotConnected.INSTANCE);
            }

            @Override // com.google.android.gms.cast.framework.SessionManagerListener
            public void onSessionResumed(CastSession session, boolean wasSuspended) {
                String friendlyName;
                this.this$0.currentSession = session;
                q0 q0Var = this.this$0._castState;
                CastDevice castDevice = session.getCastDevice();
                if (castDevice == null || (friendlyName = castDevice.getFriendlyName()) == null) {
                    friendlyName = "Chromecast";
                }
                q0Var.setValue(new CastManager.CastState.Casting(friendlyName));
            }

            @Override // com.google.android.gms.cast.framework.SessionManagerListener
            public void onSessionResuming(CastSession session, String sessionId) {
                this.this$0._castState.setValue(CastManager.CastState.Connecting.INSTANCE);
            }

            @Override // com.google.android.gms.cast.framework.SessionManagerListener
            public void onSessionStartFailed(CastSession session, int error) {
                this.this$0.currentSession = null;
                this.this$0._castState.setValue(CastManager.CastState.NotConnected.INSTANCE);
            }

            @Override // com.google.android.gms.cast.framework.SessionManagerListener
            public void onSessionStarted(CastSession session, String sessionId) {
                String friendlyName;
                this.this$0.currentSession = session;
                q0 q0Var = this.this$0._castState;
                CastDevice castDevice = session.getCastDevice();
                if (castDevice == null || (friendlyName = castDevice.getFriendlyName()) == null) {
                    friendlyName = "Chromecast";
                }
                q0Var.setValue(new CastManager.CastState.Casting(friendlyName));
            }

            @Override // com.google.android.gms.cast.framework.SessionManagerListener
            public void onSessionStarting(CastSession session) {
                this.this$0._castState.setValue(CastManager.CastState.Connecting.INSTANCE);
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 initialize$lambda$0(CastManager castManager, CastContext castContext) {
        String friendlyName;
        castManager.castContext = castContext;
        castContext.getSessionManager().addSessionManagerListener(castManager.sessionListener, CastSession.class);
        CastSession currentCastSession = castContext.getSessionManager().getCurrentCastSession();
        if (currentCastSession != null) {
            castManager.currentSession = currentCastSession;
            q0<CastState> q0Var = castManager._castState;
            CastDevice castDevice = currentCastSession.getCastDevice();
            if (castDevice == null || (friendlyName = castDevice.getFriendlyName()) == null) {
                friendlyName = "Chromecast";
            }
            q0Var.setValue(new CastState.Casting(friendlyName));
        }
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initialize$lambda$2(CastManager castManager, Exception exc) {
        castManager._castState.setValue(CastState.NotAvailable.INSTANCE);
    }

    public static /* synthetic */ void skipBack$default(CastManager castManager, long j10, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            j10 = 10000;
        }
        castManager.skipBack(j10);
    }

    public static /* synthetic */ void skipForward$default(CastManager castManager, long j10, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            j10 = 10000;
        }
        castManager.skipForward(j10);
    }

    public final void disconnect() {
        SessionManager sessionManager;
        CastContext castContext = this.castContext;
        if (castContext == null || (sessionManager = castContext.getSessionManager()) == null) {
            return;
        }
        sessionManager.endCurrentSession(true);
    }

    public final long getApproximateDuration() {
        RemoteMediaClient remoteMediaClient;
        MediaInfo mediaInfo;
        CastSession castSession = this.currentSession;
        if (castSession == null || (remoteMediaClient = castSession.getRemoteMediaClient()) == null || (mediaInfo = remoteMediaClient.getMediaInfo()) == null) {
            return 0L;
        }
        return mediaInfo.getStreamDuration();
    }

    public final long getApproximatePosition() {
        RemoteMediaClient remoteMediaClient;
        CastSession castSession = this.currentSession;
        if (castSession == null || (remoteMediaClient = castSession.getRemoteMediaClient()) == null) {
            return 0L;
        }
        return remoteMediaClient.getApproximateStreamPosition();
    }

    public final h1<CastState> getCastState() {
        return this.castState;
    }

    public final z getRouteSelector() {
        String strCategoryForCast = CastMediaControlIntent.categoryForCast(CastMediaControlIntent.DEFAULT_MEDIA_RECEIVER_APPLICATION_ID);
        if (strCategoryForCast == null) {
            throw new IllegalArgumentException("category must not be null");
        }
        ArrayList<String> arrayList = new ArrayList<>();
        if (!arrayList.contains(strCategoryForCast)) {
            arrayList.add(strCategoryForCast);
        }
        Bundle bundle = new Bundle();
        bundle.putStringArrayList("controlCategories", arrayList);
        return new z(bundle, arrayList);
    }

    public final void initialize(boolean isMobile) {
        if (!isMobile) {
            this._castState.setValue(CastState.NotAvailable.INSTANCE);
            return;
        }
        if (this.castContext != null) {
            return;
        }
        Context context = this.context;
        Executor executorA = Build.VERSION.SDK_INT >= 28 ? e.a(context) : new i(new Handler(context.getMainLooper()));
        try {
            com.google.android.gms.tasks.h sharedInstance = CastContext.getSharedInstance(this.context, executorA);
            sharedInstance.c(executorA, new b(new a(this, 0), 13));
            sharedInstance.b(executorA, new b(this, 14));
        } catch (Exception unused) {
            this._castState.setValue(CastState.NotAvailable.INSTANCE);
        }
    }

    public final boolean isRemotePlaying() {
        RemoteMediaClient remoteMediaClient;
        CastSession castSession = this.currentSession;
        return (castSession == null || (remoteMediaClient = castSession.getRemoteMediaClient()) == null || !remoteMediaClient.isPlaying()) ? false : true;
    }

    public final void loadMedia(String url, String title, String imageUrl, String mimeType, long positionMs) {
        RemoteMediaClient remoteMediaClient;
        CastSession castSession = this.currentSession;
        if (castSession == null || (remoteMediaClient = castSession.getRemoteMediaClient()) == null) {
            return;
        }
        MediaMetadata mediaMetadata = new MediaMetadata(1);
        mediaMetadata.putString(MediaMetadata.KEY_TITLE, title);
        remoteMediaClient.load(new MediaLoadRequestData.Builder().setMediaInfo(new MediaInfo.Builder(url).setStreamType(1).setContentType(mimeType).setMetadata(mediaMetadata).build()).setCurrentTime(positionMs).setAutoplay(Boolean.TRUE).build());
    }

    public final void pause() {
        RemoteMediaClient remoteMediaClient;
        CastSession castSession = this.currentSession;
        if (castSession == null || (remoteMediaClient = castSession.getRemoteMediaClient()) == null) {
            return;
        }
        remoteMediaClient.pause();
    }

    public final void play() {
        RemoteMediaClient remoteMediaClient;
        CastSession castSession = this.currentSession;
        if (castSession == null || (remoteMediaClient = castSession.getRemoteMediaClient()) == null) {
            return;
        }
        remoteMediaClient.play();
    }

    public final void seekTo(long positionMs) {
        RemoteMediaClient remoteMediaClient;
        CastSession castSession = this.currentSession;
        if (castSession == null || (remoteMediaClient = castSession.getRemoteMediaClient()) == null) {
            return;
        }
        remoteMediaClient.seek(new MediaSeekOptions.Builder().setPosition(positionMs).build());
    }

    public final void skipBack(long amountMs) {
        long approximatePosition = getApproximatePosition() - amountMs;
        if (approximatePosition < 0) {
            approximatePosition = 0;
        }
        seekTo(approximatePosition);
    }

    public final void skipForward(long amountMs) {
        seekTo(getApproximatePosition() + amountMs);
    }
}
