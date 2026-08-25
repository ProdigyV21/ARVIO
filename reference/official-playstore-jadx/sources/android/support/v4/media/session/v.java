package android.support.v4.media.session;

import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.ResolveInfo;
import android.media.MediaMetadata;
import android.media.session.MediaSession;
import android.os.BadParcelableException;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Parcel;
import android.support.v4.media.MediaMetadataCompat;
import android.text.TextUtils;
import android.util.Log;
import android.util.TypedValue;
import androidx.mediarouter.media.t0;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class v {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static int f896d;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final o f897a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final io.sentry.internal.debugmeta.c f898b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final ArrayList f899c = new ArrayList();

    public v(Context context, ComponentName componentName, PendingIntent pendingIntent) {
        if (context == null) {
            throw new IllegalArgumentException("context must not be null");
        }
        if (TextUtils.isEmpty("CastMediaSession")) {
            throw new IllegalArgumentException("tag must not be null or empty");
        }
        if (componentName == null) {
            int i10 = t0.f4484a;
            Intent intent = new Intent("android.intent.action.MEDIA_BUTTON");
            intent.setPackage(context.getPackageName());
            List<ResolveInfo> listQueryBroadcastReceivers = context.getPackageManager().queryBroadcastReceivers(intent, 0);
            if (listQueryBroadcastReceivers.size() == 1) {
                ActivityInfo activityInfo = listQueryBroadcastReceivers.get(0).activityInfo;
                componentName = new ComponentName(activityInfo.packageName, activityInfo.name);
            } else {
                if (listQueryBroadcastReceivers.size() > 1) {
                    Log.w("MediaButtonReceiver", "More than one BroadcastReceiver that handles android.intent.action.MEDIA_BUTTON was found, returning null.");
                }
                componentName = null;
            }
            if (componentName == null) {
                Log.w("MediaSessionCompat", "Couldn't find a unique registered media button receiver in the given context.");
            }
        }
        if (componentName != null && pendingIntent == null) {
            Intent intent2 = new Intent("android.intent.action.MEDIA_BUTTON");
            intent2.setComponent(componentName);
            pendingIntent = PendingIntent.getBroadcast(context, 0, intent2, Build.VERSION.SDK_INT >= 31 ? 33554432 : 0);
        }
        int i11 = Build.VERSION.SDK_INT;
        if (i11 >= 29) {
            this.f897a = new r(context);
        } else if (i11 >= 28) {
            this.f897a = new p(context);
        } else {
            this.f897a = new o(context);
        }
        c(new i(), new Handler(Looper.myLooper() != null ? Looper.myLooper() : Looper.getMainLooper()));
        this.f897a.f886a.setMediaButtonReceiver(pendingIntent);
        this.f898b = new io.sentry.internal.debugmeta.c(context, this);
        if (f896d == 0) {
            f896d = (int) (TypedValue.applyDimension(1, 320.0f, context.getResources().getDisplayMetrics()) + 0.5f);
        }
    }

    public static void a(Bundle bundle) {
        if (bundle != null) {
            bundle.setClassLoader(v.class.getClassLoader());
        }
    }

    public static Bundle e(Bundle bundle) {
        if (bundle == null) {
            return null;
        }
        a(bundle);
        try {
            bundle.isEmpty();
            return bundle;
        } catch (BadParcelableException unused) {
            Log.e("MediaSessionCompat", "Could not unparcel the data.");
            return null;
        }
    }

    public final void b(boolean z) {
        this.f897a.f886a.setActive(z);
        Iterator it = this.f899c.iterator();
        while (it.hasNext()) {
            ((s) it.next()).a();
        }
    }

    public final void c(l lVar, Handler handler) {
        o oVar = this.f897a;
        if (lVar == null) {
            oVar.c(null, null);
            return;
        }
        if (handler == null) {
            handler = new Handler();
        }
        oVar.c(lVar, handler);
    }

    public final void d(MediaMetadataCompat mediaMetadataCompat) {
        o oVar = this.f897a;
        oVar.f892g = mediaMetadataCompat;
        MediaSession mediaSession = oVar.f886a;
        if (mediaMetadataCompat.f840l == null) {
            Parcel parcelObtain = Parcel.obtain();
            mediaMetadataCompat.writeToParcel(parcelObtain, 0);
            parcelObtain.setDataPosition(0);
            mediaMetadataCompat.f840l = (MediaMetadata) MediaMetadata.CREATOR.createFromParcel(parcelObtain);
            parcelObtain.recycle();
        }
        mediaSession.setMetadata(mediaMetadataCompat.f840l);
    }
}
