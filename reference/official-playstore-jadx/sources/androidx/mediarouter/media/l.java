package androidx.mediarouter.media;

import android.content.Context;
import android.media.MediaRoute2Info;
import android.media.MediaRouter2;
import android.media.MediaRouter2$RouteCallback;
import android.media.RouteDiscoveryPreference;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Messenger;
import android.text.TextUtils;
import android.util.ArrayMap;
import android.util.ArraySet;
import android.util.Log;
import androidx.credentials.exceptions.publickeycredential.DomExceptionUtils;
import com.arvio.tv.R;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes3.dex */
public final class l extends w {
    public ArrayList A;
    public final ArrayMap B;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public final MediaRouter2 f4429s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public final b f4430t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public final ArrayMap f4431u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public final MediaRouter2$RouteCallback f4432v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public final k f4433w;
    public final g x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public final androidx.media3.common.util.d f4434y;
    public boolean z;

    static {
        Log.isLoggable("MR2Provider", 3);
    }

    public l(Context context, b bVar) {
        super(context, null);
        this.f4431u = new ArrayMap();
        this.f4433w = new k(this);
        this.x = new g(this);
        this.A = new ArrayList();
        this.B = new ArrayMap();
        this.f4429s = MediaRouter2.getInstance(context);
        this.f4430t = bVar;
        this.f4434y = new androidx.media3.common.util.d(new Handler(Looper.getMainLooper()));
        if (Build.VERSION.SDK_INT >= 34) {
            this.f4432v = new j(this, 1);
        } else {
            this.f4432v = new j(this, 0);
        }
    }

    public static Messenger h(MediaRouter2.RoutingController routingController) {
        Bundle controlHints = routingController.getControlHints();
        if (controlHints == null) {
            return null;
        }
        return (Messenger) controlHints.getParcelable("androidx.mediarouter.media.KEY_MESSENGER");
    }

    public static String i(u uVar) {
        MediaRouter2.RoutingController routingController;
        if ((uVar instanceof h) && (routingController = ((h) uVar).f4369g) != null) {
            return routingController.getId();
        }
        return null;
    }

    @Override // androidx.mediarouter.media.w
    public final s a(String str, v vVar) {
        Iterator it = this.f4431u.entrySet().iterator();
        while (it.hasNext()) {
            h hVar = (h) ((Map.Entry) it.next()).getValue();
            if (TextUtils.equals(str, hVar.f4368f)) {
                return hVar;
            }
        }
        return null;
    }

    @Override // androidx.mediarouter.media.w
    public final u b(String str) {
        return new i((String) this.B.get(str), null);
    }

    @Override // androidx.mediarouter.media.w
    public final u d(String str, String str2) {
        String str3 = (String) this.B.get(str);
        for (h hVar : this.f4431u.values()) {
            if (TextUtils.equals(str2, hVar.k())) {
                return new i(str3, hVar);
            }
        }
        Log.w("MR2Provider", "Could not find the matching GroupRouteController. routeId=" + str + ", routeGroupId=" + str2);
        return new i(str3, null);
    }

    @Override // androidx.mediarouter.media.w
    public final void e(n nVar) {
        ArrayList<String> arrayList;
        z zVar;
        RouteDiscoveryPreference routeDiscoveryPreferenceBuild;
        int i10 = k0.f4425c == null ? 0 : k0.c().C;
        MediaRouter2$RouteCallback mediaRouter2$RouteCallback = this.f4432v;
        g gVar = this.x;
        k kVar = this.f4433w;
        if (i10 <= 0) {
            this.f4429s.unregisterRouteCallback(mediaRouter2$RouteCallback);
            this.f4429s.unregisterTransferCallback(kVar);
            this.f4429s.unregisterControllerCallback(gVar);
            return;
        }
        o0 o0Var = k0.c().f4346v;
        boolean z = o0Var == null ? false : o0Var.f4468c;
        if (nVar == null) {
            nVar = new n(z.f4516c, false);
        }
        nVar.a();
        ArrayList<String> arrayListC = nVar.f4455b.c();
        if (!z) {
            arrayListC.remove("android.media.intent.category.LIVE_AUDIO");
        } else if (!arrayListC.contains("android.media.intent.category.LIVE_AUDIO")) {
            arrayListC.add("android.media.intent.category.LIVE_AUDIO");
        }
        if (arrayListC.isEmpty()) {
            arrayList = null;
        } else {
            arrayList = null;
            for (String str : arrayListC) {
                if (str == null) {
                    throw new IllegalArgumentException("category must not be null");
                }
                if (arrayList == null) {
                    arrayList = new ArrayList<>();
                }
                if (!arrayList.contains(str)) {
                    arrayList.add(str);
                }
            }
        }
        if (arrayList == null) {
            zVar = z.f4516c;
        } else {
            Bundle bundle = new Bundle();
            bundle.putStringArrayList("controlCategories", arrayList);
            zVar = new z(bundle, arrayList);
        }
        boolean zB = nVar.b();
        if (zVar == null) {
            throw new IllegalArgumentException("selector must not be null");
        }
        Bundle bundle2 = new Bundle();
        bundle2.putBundle("selector", zVar.f4517a);
        bundle2.putBoolean("activeScan", zB);
        MediaRouter2 mediaRouter2 = this.f4429s;
        zVar.a();
        if (zVar.f4518b.contains(null)) {
            routeDiscoveryPreferenceBuild = new RouteDiscoveryPreference.Builder(new ArrayList(), false).build();
        } else {
            boolean z5 = bundle2.getBoolean("activeScan");
            ArrayList arrayList2 = new ArrayList();
            for (String str2 : zVar.c()) {
                str2.getClass();
                switch (str2) {
                    case "android.media.intent.category.REMOTE_PLAYBACK":
                        str2 = "android.media.route.feature.REMOTE_PLAYBACK";
                        break;
                    case "android.media.intent.category.LIVE_AUDIO":
                        str2 = "android.media.route.feature.LIVE_AUDIO";
                        break;
                    case "android.media.intent.category.LIVE_VIDEO":
                        str2 = "android.media.route.feature.LIVE_VIDEO";
                        break;
                    case "android.media.intent.category.REMOTE_AUDIO_PLAYBACK":
                        str2 = "android.media.route.feature.REMOTE_AUDIO_PLAYBACK";
                        break;
                    case "android.media.intent.category.REMOTE_VIDEO_PLAYBACK":
                        str2 = "android.media.route.feature.REMOTE_VIDEO_PLAYBACK";
                        break;
                }
                arrayList2.add(str2);
            }
            routeDiscoveryPreferenceBuild = new RouteDiscoveryPreference.Builder(arrayList2, z5).build();
        }
        androidx.media3.common.util.d dVar = this.f4434y;
        mediaRouter2.registerRouteCallback(dVar, mediaRouter2$RouteCallback, routeDiscoveryPreferenceBuild);
        this.f4429s.registerTransferCallback(dVar, kVar);
        this.f4429s.registerControllerCallback(dVar, gVar);
    }

    public final void j() {
        ArrayList arrayList = new ArrayList();
        ArraySet arraySet = new ArraySet();
        Iterator<MediaRoute2Info> it = this.f4429s.getRoutes().iterator();
        while (it.hasNext()) {
            MediaRoute2Info mediaRoute2InfoA = androidx.media3.exoplayer.w.a(it.next());
            if (mediaRoute2InfoA != null && !arraySet.contains(mediaRoute2InfoA) && !mediaRoute2InfoA.isSystemRoute()) {
                if (this.z) {
                    if (!mediaRoute2InfoA.getId().startsWith(this.f4493i.getPackageName() + DomExceptionUtils.SEPARATOR)) {
                    }
                }
                arraySet.add(mediaRoute2InfoA);
                arrayList.add(mediaRoute2InfoA);
            }
        }
        if (arrayList.equals(this.A)) {
            return;
        }
        this.A = arrayList;
        ArrayMap arrayMap = this.B;
        arrayMap.clear();
        Iterator it2 = this.A.iterator();
        while (it2.hasNext()) {
            MediaRoute2Info mediaRoute2InfoA2 = androidx.media3.exoplayer.w.a(it2.next());
            Bundle extras = mediaRoute2InfoA2.getExtras();
            if (extras == null || extras.getString("androidx.mediarouter.media.KEY_ORIGINAL_ROUTE_ID") == null) {
                Log.w("MR2Provider", "Cannot find the original route Id. route=" + mediaRoute2InfoA2);
            } else {
                arrayMap.put(mediaRoute2InfoA2.getId(), extras.getString("androidx.mediarouter.media.KEY_ORIGINAL_ROUTE_ID"));
            }
        }
        ArrayList<m> arrayList2 = new ArrayList();
        Iterator it3 = this.A.iterator();
        while (it3.hasNext()) {
            m mVarB = j0.b(androidx.media3.exoplayer.w.a(it3.next()));
            if (mVarB != null) {
                arrayList2.add(mVarB);
            }
        }
        ArrayList arrayList3 = new ArrayList();
        if (!arrayList2.isEmpty()) {
            for (m mVar : arrayList2) {
                if (mVar == null) {
                    throw new IllegalArgumentException("route must not be null");
                }
                if (arrayList3.contains(mVar)) {
                    throw new IllegalArgumentException("route descriptor already added");
                }
                arrayList3.add(mVar);
            }
        }
        f(new x(arrayList3, true));
    }

    public final void k(MediaRouter2.RoutingController routingController) {
        androidx.emoji2.text.e0 e0Var;
        h hVar = (h) this.f4431u.get(routingController);
        if (hVar == null) {
            Log.w("MR2Provider", "setDynamicRouteDescriptors: No matching routeController found. routingController=" + routingController);
            return;
        }
        List<MediaRoute2Info> selectedRoutes = routingController.getSelectedRoutes();
        if (selectedRoutes.isEmpty()) {
            Log.w("MR2Provider", "setDynamicRouteDescriptors: No selected routes. This may happen when the selected routes become invalid.routingController=" + routingController);
            return;
        }
        ArrayList<String> arrayListA = j0.a(selectedRoutes);
        m mVarB = j0.b(androidx.media3.exoplayer.w.a(selectedRoutes.get(0)));
        Bundle controlHints = routingController.getControlHints();
        String string = this.f4493i.getString(R.string.mr_dialog_default_group_name);
        m mVar = null;
        if (controlHints != null) {
            try {
                String string2 = controlHints.getString("androidx.mediarouter.media.KEY_SESSION_NAME");
                if (!TextUtils.isEmpty(string2)) {
                    string = string2;
                }
                Bundle bundle = controlHints.getBundle("androidx.mediarouter.media.KEY_GROUP_ROUTE");
                if (bundle != null) {
                    mVar = new m(bundle);
                }
            } catch (Exception e5) {
                Log.w("MR2Provider", "Exception while unparceling control hints.", e5);
            }
        }
        if (mVar == null) {
            e0Var = new androidx.emoji2.text.e0(routingController.getId(), string);
            Bundle bundle2 = (Bundle) e0Var.f2600l;
            bundle2.putInt("connectionState", 2);
            bundle2.putInt("playbackType", 1);
        } else {
            e0Var = new androidx.emoji2.text.e0(mVar);
        }
        Bundle bundle3 = (Bundle) e0Var.f2600l;
        ArrayList arrayList = (ArrayList) e0Var.f2601m;
        bundle3.putInt("volume", routingController.getVolume());
        bundle3.putInt("volumeMax", routingController.getVolumeMax());
        bundle3.putInt("volumeHandling", routingController.getVolumeHandling());
        ((ArrayList) e0Var.f2602n).clear();
        e0Var.o(mVarB.b());
        arrayList.clear();
        if (!arrayListA.isEmpty()) {
            for (String str : arrayListA) {
                if (TextUtils.isEmpty(str)) {
                    throw new IllegalArgumentException("groupMemberId must not be empty");
                }
                if (!arrayList.contains(str)) {
                    arrayList.add(str);
                }
            }
        }
        m mVarP = e0Var.p();
        ArrayList arrayListA2 = j0.a(routingController.getSelectableRoutes());
        ArrayList arrayListA3 = j0.a(routingController.getDeselectableRoutes());
        x xVar = this.f4499q;
        if (xVar == null) {
            Log.w("MR2Provider", "setDynamicRouteDescriptors: providerDescriptor is not set.");
            return;
        }
        ArrayList arrayList2 = new ArrayList();
        List<m> list = xVar.f4506b;
        if (!list.isEmpty()) {
            for (m mVar2 : list) {
                String strD = mVar2.d();
                int i10 = arrayListA.contains(strD) ? 3 : 1;
                arrayListA2.contains(strD);
                arrayListA3.contains(strD);
                arrayList2.add(new q(mVar2, i10));
            }
        }
        hVar.f4376o = mVarP;
        hVar.j(mVarP, arrayList2);
    }

    public final void l(String str) {
        MediaRoute2Info mediaRoute2Info = null;
        if (str != null) {
            Iterator it = this.A.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                MediaRoute2Info mediaRoute2InfoA = androidx.media3.exoplayer.w.a(it.next());
                if (TextUtils.equals(mediaRoute2InfoA.getId(), str)) {
                    mediaRoute2Info = mediaRoute2InfoA;
                    break;
                }
            }
        }
        if (mediaRoute2Info == null) {
            androidx.compose.material3.d.y("transferTo: Specified route not found. routeId=", str, "MR2Provider");
        } else {
            this.f4429s.transferTo(mediaRoute2Info);
        }
    }
}
