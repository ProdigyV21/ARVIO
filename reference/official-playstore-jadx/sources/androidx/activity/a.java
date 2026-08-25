package androidx.activity;

import android.media.MediaRoute2Info;
import android.media.MediaRouter2;
import android.media.RouteListingPreference;
import android.window.BackEvent;
import androidx.mediarouter.media.u1;
import androidx.mediarouter.media.v1;
import java.util.ArrayList;
import java.util.Set;

/* JADX INFO: loaded from: classes.dex */
public abstract class a {
    public static Set a(MediaRoute2Info mediaRoute2Info) {
        return mediaRoute2Info.getDeduplicationIds();
    }

    public static int b(MediaRoute2Info mediaRoute2Info) {
        return mediaRoute2Info.getType();
    }

    public static float c(BackEvent backEvent) {
        return backEvent.getProgress();
    }

    public static void d(MediaRouter2 mediaRouter2, RouteListingPreference routeListingPreference) {
        mediaRouter2.setRouteListingPreference(routeListingPreference);
    }

    public static int e(BackEvent backEvent) {
        return backEvent.getSwipeEdge();
    }

    public static RouteListingPreference f(v1 v1Var) {
        ArrayList arrayList = new ArrayList();
        for (u1 u1Var : v1Var.f4491a) {
            arrayList.add(new RouteListingPreference.Item.Builder(u1Var.f4486a).setFlags(0).setSubText(0).setCustomSubtextMessage(null).setSelectionBehavior(u1Var.f4487b).build());
        }
        return new RouteListingPreference.Builder().setItems(arrayList).setLinkedItemComponentName(null).setUseSystemOrdering(v1Var.f4492b).build();
    }

    public static float g(BackEvent backEvent) {
        return backEvent.getTouchX();
    }

    public static float h(BackEvent backEvent) {
        return backEvent.getTouchY();
    }
}
