package androidx.mediarouter.media;

import android.content.ComponentName;
import android.content.Context;
import android.content.IntentFilter;
import android.media.MediaRouter;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Display;
import androidx.fragment.app.a2;
import com.arvio.tv.R;
import com.google.android.gms.cast.CredentialsData;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Locale;

/* JADX INFO: loaded from: classes3.dex */
public class y0 extends a1 implements p0, r0 {
    public static final ArrayList C;
    public static final ArrayList D;
    public final ArrayList A;
    public final ArrayList B;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public final f f4510s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public final MediaRouter f4511t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public final q0 f4512u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public final s0 f4513v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public final MediaRouter.RouteCategory f4514w;
    public int x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public boolean f4515y;
    public boolean z;

    static {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addCategory("android.media.intent.category.LIVE_AUDIO");
        ArrayList arrayList = new ArrayList();
        C = arrayList;
        arrayList.add(intentFilter);
        IntentFilter intentFilter2 = new IntentFilter();
        intentFilter2.addCategory("android.media.intent.category.LIVE_VIDEO");
        ArrayList arrayList2 = new ArrayList();
        D = arrayList2;
        arrayList2.add(intentFilter2);
    }

    public y0(Context context, f fVar) {
        super(context, new t(new ComponentName(CredentialsData.CREDENTIALS_TYPE_ANDROID, a1.class.getName())));
        this.A = new ArrayList();
        this.B = new ArrayList();
        this.f4510s = fVar;
        MediaRouter mediaRouter = (MediaRouter) context.getSystemService("media_router");
        this.f4511t = mediaRouter;
        this.f4512u = new q0(this);
        this.f4513v = new s0(this);
        this.f4514w = mediaRouter.createRouteCategory((CharSequence) context.getResources().getString(R.string.mr_user_route_category_name), false);
        s();
    }

    public static x0 m(MediaRouter.RouteInfo routeInfo) {
        Object tag = routeInfo.getTag();
        if (tag instanceof x0) {
            return (x0) tag;
        }
        return null;
    }

    public static void t(x0 x0Var) {
        MediaRouter.UserRouteInfo userRouteInfo = x0Var.f4509b;
        i0 i0Var = x0Var.f4508a;
        userRouteInfo.setName(i0Var.f4389d);
        userRouteInfo.setPlaybackType(i0Var.f4396l);
        userRouteInfo.setPlaybackStream(i0Var.f4397m);
        userRouteInfo.setVolume(i0Var.f4400p);
        userRouteInfo.setVolumeMax(i0Var.f4401q);
        userRouteInfo.setVolumeHandling(i0Var.b());
        userRouteInfo.setDescription(i0Var.f4390e);
    }

    @Override // androidx.mediarouter.media.w
    public final u b(String str) {
        int iJ = j(str);
        if (iJ >= 0) {
            return new v0(((w0) this.A.get(iJ)).f4501a);
        }
        return null;
    }

    @Override // androidx.mediarouter.media.w
    public final void e(n nVar) {
        boolean zB;
        int i10 = 0;
        if (nVar != null) {
            nVar.a();
            ArrayList arrayListC = nVar.f4455b.c();
            int size = arrayListC.size();
            int i11 = 0;
            while (i10 < size) {
                String str = (String) arrayListC.get(i10);
                i11 = str.equals("android.media.intent.category.LIVE_AUDIO") ? i11 | 1 : str.equals("android.media.intent.category.LIVE_VIDEO") ? i11 | 2 : i11 | 8388608;
                i10++;
            }
            zB = nVar.b();
            i10 = i11;
        } else {
            zB = false;
        }
        if (this.x == i10 && this.f4515y == zB) {
            return;
        }
        this.x = i10;
        this.f4515y = zB;
        s();
    }

    public final boolean h(MediaRouter.RouteInfo routeInfo) {
        String strJ;
        if (m(routeInfo) != null || i(routeInfo) >= 0) {
            return false;
        }
        String str = this.f4511t.getDefaultRoute() == routeInfo ? "DEFAULT_ROUTE" : String.format(Locale.US, "ROUTE_%08x", Integer.valueOf(l(routeInfo).hashCode()));
        if (j(str) >= 0) {
            int i10 = 2;
            while (true) {
                Locale locale = Locale.US;
                strJ = a2.j(i10, str, "_");
                if (j(strJ) < 0) {
                    break;
                }
                i10++;
            }
            str = strJ;
        }
        w0 w0Var = new w0(routeInfo, str);
        androidx.emoji2.text.e0 e0Var = new androidx.emoji2.text.e0(str, l(routeInfo));
        n(w0Var, e0Var);
        w0Var.f4503c = e0Var.p();
        this.A.add(w0Var);
        return true;
    }

    public final int i(MediaRouter.RouteInfo routeInfo) {
        ArrayList arrayList = this.A;
        int size = arrayList.size();
        for (int i10 = 0; i10 < size; i10++) {
            if (((w0) arrayList.get(i10)).f4501a == routeInfo) {
                return i10;
            }
        }
        return -1;
    }

    public final int j(String str) {
        ArrayList arrayList = this.A;
        int size = arrayList.size();
        for (int i10 = 0; i10 < size; i10++) {
            if (((w0) arrayList.get(i10)).f4502b.equals(str)) {
                return i10;
            }
        }
        return -1;
    }

    public final int k(i0 i0Var) {
        ArrayList arrayList = this.B;
        int size = arrayList.size();
        for (int i10 = 0; i10 < size; i10++) {
            if (((x0) arrayList.get(i10)).f4508a == i0Var) {
                return i10;
            }
        }
        return -1;
    }

    public final String l(MediaRouter.RouteInfo routeInfo) {
        Context context = this.f4493i;
        CharSequence name = routeInfo.getName(context);
        if (!TextUtils.isEmpty(name)) {
            return name.toString();
        }
        if ((routeInfo.getSupportedTypes() & 8388608) != 0) {
            return "";
        }
        int deviceType = Build.VERSION.SDK_INT >= 24 ? routeInfo.getDeviceType() : 0;
        return context.getString(deviceType != 1 ? deviceType != 2 ? deviceType != 3 ? R.string.mr_route_name_unknown : R.string.mr_route_name_bluetooth : R.string.mr_route_name_speaker : R.string.mr_route_name_tv);
    }

    public void n(w0 w0Var, androidx.emoji2.text.e0 e0Var) {
        Bundle bundle = (Bundle) e0Var.f2600l;
        MediaRouter.RouteInfo routeInfo = w0Var.f4501a;
        int supportedTypes = routeInfo.getSupportedTypes();
        if ((supportedTypes & 1) != 0) {
            e0Var.o(C);
        }
        if ((supportedTypes & 2) != 0) {
            e0Var.o(D);
        }
        bundle.putInt("playbackType", routeInfo.getPlaybackType());
        bundle.putInt("playbackStream", routeInfo.getPlaybackStream());
        bundle.putInt("volume", routeInfo.getVolume());
        bundle.putInt("volumeMax", routeInfo.getVolumeMax());
        bundle.putInt("volumeHandling", routeInfo.getVolumeHandling());
        bundle.putBoolean("isSystemRoute", (8388608 & supportedTypes) == 0);
        if (!routeInfo.isEnabled()) {
            bundle.putBoolean("enabled", false);
        }
        if (routeInfo.isConnecting()) {
            bundle.putInt("connectionState", 1);
        }
        Display presentationDisplay = routeInfo.getPresentationDisplay();
        if (presentationDisplay != null) {
            bundle.putInt("presentationDisplayId", presentationDisplay.getDisplayId());
        }
        CharSequence description = routeInfo.getDescription();
        if (description != null) {
            bundle.putString("status", description.toString());
        }
    }

    public final void o(i0 i0Var) {
        w wVarA = i0Var.a();
        MediaRouter mediaRouter = this.f4511t;
        if (wVarA == this) {
            int i10 = i(mediaRouter.getSelectedRoute(8388611));
            if (i10 < 0 || !((w0) this.A.get(i10)).f4502b.equals(i0Var.f4387b)) {
                return;
            }
            i0Var.g(false);
            return;
        }
        MediaRouter.UserRouteInfo userRouteInfoCreateUserRoute = mediaRouter.createUserRoute(this.f4514w);
        x0 x0Var = new x0(i0Var, userRouteInfoCreateUserRoute);
        userRouteInfoCreateUserRoute.setTag(x0Var);
        userRouteInfoCreateUserRoute.setVolumeCallback(this.f4513v);
        t(x0Var);
        this.B.add(x0Var);
        mediaRouter.addUserRoute(userRouteInfoCreateUserRoute);
    }

    public final void p(i0 i0Var) {
        int iK;
        if (i0Var.a() == this || (iK = k(i0Var)) < 0) {
            return;
        }
        MediaRouter.UserRouteInfo userRouteInfo = ((x0) this.B.remove(iK)).f4509b;
        userRouteInfo.setTag(null);
        userRouteInfo.setVolumeCallback(null);
        try {
            this.f4511t.removeUserRoute(userRouteInfo);
        } catch (IllegalArgumentException e5) {
            Log.w("AxSysMediaRouteProvider", "Failed to remove user route", e5);
        }
    }

    public final void q(i0 i0Var) {
        i0Var.getClass();
        k0.b();
        if (k0.c().g() == i0Var) {
            w wVarA = i0Var.a();
            MediaRouter mediaRouter = this.f4511t;
            if (wVarA != this) {
                int iK = k(i0Var);
                if (iK >= 0) {
                    mediaRouter.selectRoute(8388611, ((x0) this.B.get(iK)).f4509b);
                    return;
                }
                return;
            }
            int iJ = j(i0Var.f4387b);
            if (iJ >= 0) {
                mediaRouter.selectRoute(8388611, ((w0) this.A.get(iJ)).f4501a);
            }
        }
    }

    public final void r() {
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = this.A;
        int size = arrayList2.size();
        for (int i10 = 0; i10 < size; i10++) {
            m mVar = ((w0) arrayList2.get(i10)).f4503c;
            if (mVar == null) {
                throw new IllegalArgumentException("route must not be null");
            }
            if (arrayList.contains(mVar)) {
                throw new IllegalArgumentException("route descriptor already added");
            }
            arrayList.add(mVar);
        }
        f(new x(arrayList, false));
    }

    public final void s() {
        boolean z = this.z;
        q0 q0Var = this.f4512u;
        MediaRouter mediaRouter = this.f4511t;
        if (z) {
            mediaRouter.removeCallback(q0Var);
        }
        this.z = true;
        mediaRouter.addCallback(this.x, q0Var, (this.f4515y ? 1 : 0) | 2);
        int routeCount = mediaRouter.getRouteCount();
        ArrayList arrayList = new ArrayList(routeCount);
        boolean zH = false;
        for (int i10 = 0; i10 < routeCount; i10++) {
            arrayList.add(mediaRouter.getRouteAt(i10));
        }
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            zH |= h((MediaRouter.RouteInfo) it.next());
        }
        if (zH) {
            r();
        }
    }
}
