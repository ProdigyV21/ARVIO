package android.content;

import a0.c;
import android.content.ComponentCallbacks2;
import android.content.Context;
import android.content.res.Configuration;
import coil.RealImageLoader;
import coil.network.EmptyNetworkObserver;
import coil.network.NetworkObserver;
import coil.network.NetworkObserverKt;
import java.lang.ref.WeakReference;
import java.util.concurrent.atomic.AtomicBoolean;
import kotlin.Metadata;
import r7.l;
import x6.t0;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0000\u0018\u0000 02\u00020\u00012\u00020\u0002:\u00010B\u001f\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\b\u001a\u00020\u0007¢\u0006\u0004\b\t\u0010\nJ$\u0010\u000e\u001a\u00020\f2\u0012\u0010\r\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\f0\u000bH\u0082\b¢\u0006\u0004\b\u000e\u0010\u000fJ\r\u0010\u0010\u001a\u00020\f¢\u0006\u0004\b\u0010\u0010\u0011J\u0017\u0010\u0014\u001a\u00020\f2\u0006\u0010\u0013\u001a\u00020\u0012H\u0016¢\u0006\u0004\b\u0014\u0010\u0015J\u0017\u0010\u0018\u001a\u00020\f2\u0006\u0010\u0017\u001a\u00020\u0016H\u0016¢\u0006\u0004\b\u0018\u0010\u0019J\u000f\u0010\u001a\u001a\u00020\fH\u0016¢\u0006\u0004\b\u001a\u0010\u0011J\u0017\u0010\u001c\u001a\u00020\f2\u0006\u0010\u001b\u001a\u00020\u0007H\u0016¢\u0006\u0004\b\u001c\u0010\u001dJ\r\u0010\u001e\u001a\u00020\f¢\u0006\u0004\b\u001e\u0010\u0011R\u0014\u0010\u0006\u001a\u00020\u00058\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0006\u0010\u001fR.\u0010\u0004\u001a\u0010\u0012\f\u0012\n !*\u0004\u0018\u00010\u00030\u00030 8\u0000X\u0081\u0004¢\u0006\u0012\n\u0004\b\u0004\u0010\"\u0012\u0004\b%\u0010\u0011\u001a\u0004\b#\u0010$R\u0014\u0010'\u001a\u00020&8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b'\u0010(R\u0016\u0010)\u001a\u00020\u00078\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b)\u0010*R\u0014\u0010,\u001a\u00020+8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b,\u0010-R\u0011\u0010\u001b\u001a\u00020\u00078F¢\u0006\u0006\u001a\u0004\b\u001b\u0010.R\u0011\u0010/\u001a\u00020\u00078F¢\u0006\u0006\u001a\u0004\b/\u0010.¨\u00061"}, d2 = {"Lcoil/util/SystemCallbacks;", "Landroid/content/ComponentCallbacks2;", "Lcoil/network/NetworkObserver$Listener;", "Lcoil/RealImageLoader;", "imageLoader", "Landroid/content/Context;", "context", "", "isNetworkObserverEnabled", "<init>", "(Lcoil/RealImageLoader;Landroid/content/Context;Z)V", "Lkotlin/Function1;", "Lx6/t0;", "block", "withImageLoader", "(Lr7/l;)V", "register", "()V", "Landroid/content/res/Configuration;", "newConfig", "onConfigurationChanged", "(Landroid/content/res/Configuration;)V", "", "level", "onTrimMemory", "(I)V", "onLowMemory", "isOnline", "onConnectivityChange", "(Z)V", "shutdown", "Landroid/content/Context;", "Ljava/lang/ref/WeakReference;", "kotlin.jvm.PlatformType", "Ljava/lang/ref/WeakReference;", "getImageLoader$coil_base_release", "()Ljava/lang/ref/WeakReference;", "getImageLoader$coil_base_release$annotations", "Lcoil/network/NetworkObserver;", "networkObserver", "Lcoil/network/NetworkObserver;", "_isOnline", "Z", "Ljava/util/concurrent/atomic/AtomicBoolean;", "_isShutdown", "Ljava/util/concurrent/atomic/AtomicBoolean;", "()Z", "isShutdown", "Companion", "coil-base_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final class SystemCallbacks implements ComponentCallbacks2, NetworkObserver.Listener {
    private static final String OFFLINE = "OFFLINE";
    private static final String ONLINE = "ONLINE";
    private static final String TAG = "NetworkObserver";
    private volatile boolean _isOnline;
    private final AtomicBoolean _isShutdown;
    private final Context context;
    private final WeakReference<RealImageLoader> imageLoader;
    private final NetworkObserver networkObserver;

    public SystemCallbacks(RealImageLoader realImageLoader, Context context, boolean z) {
        this.context = context;
        this.imageLoader = new WeakReference<>(realImageLoader);
        NetworkObserver NetworkObserver = z ? NetworkObserverKt.NetworkObserver(context, this, realImageLoader.getLogger()) : new EmptyNetworkObserver();
        this.networkObserver = NetworkObserver;
        this._isOnline = NetworkObserver.isOnline();
        this._isShutdown = new AtomicBoolean(false);
    }

    public static /* synthetic */ void getImageLoader$coil_base_release$annotations() {
    }

    private final void withImageLoader(l<? super RealImageLoader, t0> block) {
        t0 t0Var;
        RealImageLoader realImageLoader = this.imageLoader.get();
        if (realImageLoader != null) {
            block.invoke(realImageLoader);
            t0Var = t0.f22605a;
        } else {
            t0Var = null;
        }
        if (t0Var == null) {
            shutdown();
        }
    }

    public final WeakReference<RealImageLoader> getImageLoader$coil_base_release() {
        return this.imageLoader;
    }

    /* JADX INFO: renamed from: isOnline, reason: from getter */
    public final boolean get_isOnline() {
        return this._isOnline;
    }

    public final boolean isShutdown() {
        return this._isShutdown.get();
    }

    @Override // android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration newConfig) {
        if (this.imageLoader.get() == null) {
            shutdown();
        }
    }

    @Override // coil.network.NetworkObserver.Listener
    public void onConnectivityChange(boolean isOnline) {
        RealImageLoader realImageLoader = this.imageLoader.get();
        t0 t0Var = null;
        if (realImageLoader != null) {
            Logger logger = realImageLoader.getLogger();
            if (logger != null && logger.getLevel() <= 4) {
                logger.log(TAG, 4, isOnline ? ONLINE : OFFLINE, null);
            }
            this._isOnline = isOnline;
            t0Var = t0.f22605a;
        }
        if (t0Var == null) {
            shutdown();
        }
    }

    @Override // android.content.ComponentCallbacks
    public void onLowMemory() {
        onTrimMemory(80);
    }

    @Override // android.content.ComponentCallbacks2
    public void onTrimMemory(int level) {
        RealImageLoader realImageLoader = this.imageLoader.get();
        t0 t0Var = null;
        if (realImageLoader != null) {
            Logger logger = realImageLoader.getLogger();
            if (logger != null && logger.getLevel() <= 2) {
                logger.log(TAG, 2, c.i(level, "trimMemory, level="), null);
            }
            realImageLoader.onTrimMemory$coil_base_release(level);
            t0Var = t0.f22605a;
        }
        if (t0Var == null) {
            shutdown();
        }
    }

    public final void register() {
        this.context.registerComponentCallbacks(this);
    }

    public final void shutdown() {
        if (this._isShutdown.getAndSet(true)) {
            return;
        }
        this.context.unregisterComponentCallbacks(this);
        this.networkObserver.shutdown();
    }
}
