package androidx.media3.session;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.pm.ServiceInfo;
import android.media.session.MediaSession;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Parcelable;
import android.os.ResultReceiver;
import android.text.TextUtils;
import androidx.media3.common.MediaLibraryInfo;
import androidx.media3.common.util.Util;
import androidx.media3.session.legacy.LegacyParcelableUtil;
import androidx.media3.session.legacy.MediaBrowserServiceCompat;
import androidx.media3.session.legacy.MediaControllerCompat;
import androidx.media3.session.legacy.MediaSessionCompat;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public final class SessionToken {
    private static final String FIELD_IMPL;
    private static final String FIELD_IMPL_TYPE;
    private static final int IMPL_TYPE_BASE = 0;
    private static final int IMPL_TYPE_LEGACY = 1;
    public static final int PLATFORM_SESSION_VERSION = 0;
    static final int TYPE_BROWSER_SERVICE_LEGACY = 101;
    public static final int TYPE_LIBRARY_SERVICE = 2;
    public static final int TYPE_SESSION = 0;
    static final int TYPE_SESSION_LEGACY = 100;
    public static final int TYPE_SESSION_SERVICE = 1;
    public static final int UNKNOWN_INTERFACE_VERSION = 0;
    public static final int UNKNOWN_SESSION_VERSION = 1000000;
    private static final long WAIT_TIME_MS_FOR_SESSION3_TOKEN = 500;
    private final SessionTokenImpl impl;

    public interface SessionTokenImpl {
        Object getBinder();

        ComponentName getComponentName();

        Bundle getExtras();

        int getInterfaceVersion();

        int getLibraryVersion();

        String getPackageName();

        MediaSession.Token getPlatformToken();

        String getServiceName();

        int getType();

        int getUid();

        boolean isLegacySession();

        Bundle toBundle();
    }

    @Target({ElementType.TYPE_USE})
    @Documented
    @Retention(RetentionPolicy.SOURCE)
    public @interface TokenType {
    }

    static {
        MediaLibraryInfo.registerModule("media3.session");
        FIELD_IMPL_TYPE = Util.intToStringMaxRadix(0);
        FIELD_IMPL = Util.intToStringMaxRadix(1);
    }

    public SessionToken(Context context, ComponentName componentName) {
        int i10;
        ac.b.p(context, "context must not be null");
        ac.b.p(componentName, "serviceComponent must not be null");
        PackageManager packageManager = context.getPackageManager();
        int uid = getUid(packageManager, componentName.getPackageName());
        if (isInterfaceDeclared(packageManager, MediaLibraryService.SERVICE_INTERFACE, componentName)) {
            i10 = 2;
        } else if (isInterfaceDeclared(packageManager, MediaSessionService.SERVICE_INTERFACE, componentName)) {
            i10 = 1;
        } else {
            if (!isInterfaceDeclared(packageManager, MediaBrowserServiceCompat.SERVICE_INTERFACE, componentName)) {
                throw new IllegalArgumentException("Failed to resolve SessionToken for " + componentName + ". Manifest doesn't declare one of either MediaSessionService, MediaLibraryService, MediaBrowserService or MediaBrowserServiceCompat. Use service's full name.");
            }
            i10 = 101;
        }
        if (i10 != 101) {
            this.impl = new SessionTokenImplBase(componentName, uid, i10);
        } else {
            this.impl = new SessionTokenImplLegacy(componentName, uid);
        }
    }

    private static MediaSessionCompat.Token createCompatToken(Parcelable parcelable) {
        return parcelable instanceof MediaSession.Token ? MediaSessionCompat.Token.fromToken((MediaSession.Token) parcelable) : (MediaSessionCompat.Token) LegacyParcelableUtil.convert(parcelable, MediaSessionCompat.Token.CREATOR);
    }

    public static com.google.common.util.concurrent.d1<SessionToken> createSessionToken(Context context, MediaSession.Token token) {
        return createSessionToken(context, MediaSessionCompat.Token.fromToken(token));
    }

    public static SessionToken fromBundle(Bundle bundle) {
        return new SessionToken(bundle, (MediaSession.Token) null);
    }

    public static com.google.common.collect.w1 getAllServiceTokens(Context context) {
        ServiceInfo serviceInfo;
        PackageManager packageManager = context.getPackageManager();
        ArrayList<ResolveInfo> arrayList = new ArrayList();
        List<ResolveInfo> listQueryIntentServices = packageManager.queryIntentServices(new Intent(MediaLibraryService.SERVICE_INTERFACE), 128);
        if (listQueryIntentServices != null) {
            arrayList.addAll(listQueryIntentServices);
        }
        List<ResolveInfo> listQueryIntentServices2 = packageManager.queryIntentServices(new Intent(MediaSessionService.SERVICE_INTERFACE), 128);
        if (listQueryIntentServices2 != null) {
            arrayList.addAll(listQueryIntentServices2);
        }
        List<ResolveInfo> listQueryIntentServices3 = packageManager.queryIntentServices(new Intent(MediaBrowserServiceCompat.SERVICE_INTERFACE), 128);
        if (listQueryIntentServices3 != null) {
            arrayList.addAll(listQueryIntentServices3);
        }
        int i10 = com.google.common.collect.w1.f14132m;
        com.google.common.collect.v1 v1Var = new com.google.common.collect.v1(4);
        for (ResolveInfo resolveInfo : arrayList) {
            if (resolveInfo != null && (serviceInfo = resolveInfo.serviceInfo) != null) {
                v1Var.c(new SessionToken(context, new ComponentName(serviceInfo.packageName, serviceInfo.name)));
            }
        }
        return v1Var.f();
    }

    private static boolean isInterfaceDeclared(PackageManager packageManager, String str, ComponentName componentName) {
        ServiceInfo serviceInfo;
        Intent intent = new Intent(str);
        intent.setPackage(componentName.getPackageName());
        List<ResolveInfo> listQueryIntentServices = packageManager.queryIntentServices(intent, 128);
        if (listQueryIntentServices != null) {
            for (int i10 = 0; i10 < listQueryIntentServices.size(); i10++) {
                ResolveInfo resolveInfo = listQueryIntentServices.get(i10);
                if (resolveInfo != null && (serviceInfo = resolveInfo.serviceInfo) != null && TextUtils.equals(serviceInfo.name, componentName.getClassName())) {
                    return true;
                }
            }
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$createSessionToken$0(Context context, String str, MediaSessionCompat.Token token, MediaControllerCompat mediaControllerCompat, com.google.common.util.concurrent.q1 q1Var) {
        q1Var.set(new SessionToken(token, str, getUid(context.getPackageManager(), str), mediaControllerCompat.getSessionInfo()));
    }

    public boolean equals(Object obj) {
        if (obj instanceof SessionToken) {
            return this.impl.equals(((SessionToken) obj).impl);
        }
        return false;
    }

    public Object getBinder() {
        return this.impl.getBinder();
    }

    public ComponentName getComponentName() {
        return this.impl.getComponentName();
    }

    public Bundle getExtras() {
        return this.impl.getExtras();
    }

    public int getInterfaceVersion() {
        return this.impl.getInterfaceVersion();
    }

    public String getPackageName() {
        return this.impl.getPackageName();
    }

    public MediaSession.Token getPlatformToken() {
        return this.impl.getPlatformToken();
    }

    public String getServiceName() {
        return this.impl.getServiceName();
    }

    public int getSessionVersion() {
        return this.impl.getLibraryVersion();
    }

    public int getType() {
        return this.impl.getType();
    }

    public int getUid() {
        return this.impl.getUid();
    }

    public int hashCode() {
        return this.impl.hashCode();
    }

    public boolean isLegacySession() {
        return this.impl.isLegacySession();
    }

    public Bundle toBundle() {
        Bundle bundle = new Bundle();
        if (this.impl instanceof SessionTokenImplBase) {
            bundle.putInt(FIELD_IMPL_TYPE, 0);
        } else {
            bundle.putInt(FIELD_IMPL_TYPE, 1);
        }
        bundle.putBundle(FIELD_IMPL, this.impl.toBundle());
        return bundle;
    }

    public String toString() {
        return this.impl.toString();
    }

    public static com.google.common.util.concurrent.d1<SessionToken> createSessionToken(Context context, Parcelable parcelable) {
        return createSessionToken(context, createCompatToken(parcelable));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static SessionToken fromBundle(Bundle bundle, MediaSession.Token token) {
        return new SessionToken(bundle, token);
    }

    private static int getUid(PackageManager packageManager, String str) {
        try {
            return packageManager.getApplicationInfo(str, 0).uid;
        } catch (PackageManager.NameNotFoundException unused) {
            return -1;
        }
    }

    public static com.google.common.util.concurrent.d1<SessionToken> createSessionToken(Context context, MediaSession.Token token, Looper looper) {
        return createSessionToken(context, MediaSessionCompat.Token.fromToken(token), looper);
    }

    public static com.google.common.util.concurrent.d1<SessionToken> createSessionToken(Context context, Parcelable parcelable, Looper looper) {
        return createSessionToken(context, createCompatToken(parcelable), looper);
    }

    private static com.google.common.util.concurrent.d1<SessionToken> createSessionToken(Context context, MediaSessionCompat.Token token) {
        HandlerThread handlerThread = new HandlerThread("SessionTokenThread");
        handlerThread.start();
        com.google.common.util.concurrent.d1<SessionToken> d1VarCreateSessionToken = createSessionToken(context, token, handlerThread.getLooper());
        d1VarCreateSessionToken.addListener(new j3(handlerThread, 3), com.google.common.util.concurrent.s0.f14233i);
        return d1VarCreateSessionToken;
    }

    private static com.google.common.util.concurrent.d1<SessionToken> createSessionToken(Context context, final MediaSessionCompat.Token token, Looper looper) {
        ac.b.p(context, "context must not be null");
        ac.b.p(token, "compatToken must not be null");
        final com.google.common.util.concurrent.q1 q1Var = new com.google.common.util.concurrent.q1();
        MediaControllerCompat mediaControllerCompat = new MediaControllerCompat(context, token);
        String packageName = mediaControllerCompat.getPackageName();
        packageName.getClass();
        final Handler handler = new Handler(looper);
        final a aVar = new a(context, packageName, token, mediaControllerCompat, q1Var, 4);
        handler.postDelayed(aVar, 500L);
        mediaControllerCompat.sendCommand("androidx.media3.session.SESSION_COMMAND_REQUEST_SESSION3_TOKEN", null, new ResultReceiver(handler) { // from class: androidx.media3.session.SessionToken.1
            @Override // android.os.ResultReceiver
            public void onReceiveResult(int i10, Bundle bundle) {
                handler.removeCallbacksAndMessages(null);
                try {
                    q1Var.set(SessionToken.fromBundle(bundle, token.getToken()));
                } catch (RuntimeException unused) {
                    aVar.run();
                }
            }
        });
        return q1Var;
    }

    public SessionToken(int i10, int i11, int i12, int i13, String str, IMediaSession iMediaSession, Bundle bundle, MediaSession.Token token) {
        this.impl = new SessionTokenImplBase(i10, i11, i12, i13, str, iMediaSession, bundle, token);
    }

    private SessionToken(MediaSessionCompat.Token token, String str, int i10, Bundle bundle) {
        this.impl = new SessionTokenImplLegacy(token, str, i10, bundle);
    }

    private SessionToken(Bundle bundle, MediaSession.Token token) {
        String str = FIELD_IMPL_TYPE;
        ac.b.k(bundle.containsKey(str), "Impl type needs to be set.");
        int i10 = bundle.getInt(str);
        Bundle bundle2 = bundle.getBundle(FIELD_IMPL);
        bundle2.getClass();
        if (i10 == 0) {
            this.impl = SessionTokenImplBase.fromBundle(bundle2, token);
        } else {
            this.impl = SessionTokenImplLegacy.fromBundle(bundle2);
        }
    }
}
