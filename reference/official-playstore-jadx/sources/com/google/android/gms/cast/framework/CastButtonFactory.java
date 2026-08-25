package com.google.android.gms.cast.framework;

import android.content.Context;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import androidx.compose.foundation.c;
import androidx.core.view.e;
import androidx.mediarouter.app.a;
import androidx.mediarouter.app.b;
import androidx.mediarouter.app.h;
import com.google.android.gms.cast.internal.Logger;
import com.google.android.gms.common.internal.t;
import com.google.android.gms.internal.cast.l6;
import com.google.android.gms.internal.cast.m5;
import com.google.android.gms.tasks.i;
import com.google.android.gms.tasks.j;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.Executor;
import qb.l;

/* JADX INFO: loaded from: classes4.dex */
public final class CastButtonFactory {
    public static final /* synthetic */ int zza = 0;
    private static final Logger zzb = new Logger("CastButtonFactory");
    private static final List zzc = new ArrayList();
    private static final Object zzd = new Object();
    private static final List zze = new ArrayList();
    private static final Object zzf = new Object();

    private CastButtonFactory() {
    }

    public static MenuItem setUpMediaRouteButton(Context context, Menu menu, int i10) {
        t.e("Must be called from the main thread.");
        t.i(menu);
        MenuItem menuItemFindItem = menu.findItem(i10);
        if (menuItemFindItem == null) {
            Locale locale = Locale.ROOT;
            throw new IllegalArgumentException(c.o(i10, "menu doesn't contain a menu item whose ID is ", "."));
        }
        try {
            zzf(context, menuItemFindItem, null);
            synchronized (zzd) {
                zzc.add(new WeakReference(menuItemFindItem));
            }
            l6.a(m5.CAST_DEFAULT_MEDIA_ROUTER_DIALOG);
            return menuItemFindItem;
        } catch (IllegalArgumentException e5) {
            Locale locale2 = Locale.ROOT;
            throw new IllegalArgumentException(c.o(i10, "menu item with ID ", " doesn't have a MediaRouteActionProvider."), e5);
        }
    }

    public static void zza(Context context) {
        synchronized (zzd) {
            Iterator it = zzc.iterator();
            while (it.hasNext()) {
                MenuItem menuItem = (MenuItem) ((WeakReference) it.next()).get();
                if (menuItem != null) {
                    try {
                        zzf(context, menuItem, null);
                    } catch (IllegalArgumentException e5) {
                        zzb.w("Unexpected exception when refreshing MediaRouteSelectors for Cast buttons", e5);
                    }
                }
            }
        }
        synchronized (zzf) {
            try {
                Iterator it2 = zze.iterator();
                while (it2.hasNext()) {
                    if (((WeakReference) it2.next()).get() != null) {
                        throw new ClassCastException();
                    }
                }
            } finally {
            }
        }
    }

    public static /* synthetic */ void zzb(h hVar, MenuItem menuItem) {
        synchronized (zzd) {
            zzc.add(new WeakReference(menuItem));
        }
        l6.a(m5.CAST_DEFAULT_MEDIA_ROUTER_DIALOG);
    }

    public static /* synthetic */ void zzc(b bVar, h hVar, Void r32) {
        synchronized (zzf) {
            zze.add(new WeakReference(bVar));
        }
        l6.a(m5.CAST_DEFAULT_MEDIA_ROUTER_DIALOG);
    }

    public static /* synthetic */ void zzd(a aVar, h hVar, i iVar, MenuItem menuItem, CastContext castContext) {
        zzh(castContext, aVar, null);
        iVar.b(menuItem);
    }

    public static /* synthetic */ void zze(b bVar, h hVar, i iVar, CastContext castContext) {
        zzi(castContext, bVar, null);
        iVar.b(null);
    }

    private static void zzf(Context context, MenuItem menuItem, h hVar) throws IllegalArgumentException {
        t.e("Must be called from the main thread.");
        zzj(menuItem);
        throw new IllegalArgumentException("cannot refreshButtonSelector with null mediaRouteActionProvider");
    }

    private static void zzg(Context context, b bVar, h hVar) {
        t.e("Must be called from the main thread.");
        zzi(CastContext.zza(context), bVar, null);
    }

    private static void zzh(CastContext castContext, a aVar, h hVar) {
        if (castContext != null && castContext.getMergedSelector() != null) {
            throw null;
        }
    }

    private static void zzi(CastContext castContext, b bVar, h hVar) {
        if (castContext != null && castContext.getMergedSelector() != null) {
            throw null;
        }
    }

    private static a zzj(MenuItem menuItem) {
        e eVarA;
        if (menuItem instanceof k0.b) {
            eVarA = ((k0.b) menuItem).a();
        } else {
            Log.w("MenuItemCompat", "getActionProvider: item does not implement SupportMenuItem; returning null");
            eVarA = null;
        }
        if (eVarA == null) {
            return null;
        }
        throw new ClassCastException();
    }

    public static void setUpMediaRouteButton(Context context, b bVar) {
        t.e("Must be called from the main thread.");
        l6.a(m5.CAST_DEFAULT_MEDIA_ROUTER_DIALOG);
    }

    public static com.google.android.gms.tasks.h setUpMediaRouteButton(Context context, Executor executor, Menu menu, int i10) {
        t.e("Must be called from the main thread.");
        t.i(menu);
        MenuItem menuItemFindItem = menu.findItem(i10);
        if (menuItemFindItem == null) {
            Locale locale = Locale.ROOT;
            return l.m(new IllegalArgumentException(c.o(i10, "menu doesn't contain a menu item whose ID is ", ".")));
        }
        try {
            t.e("Must be called from the main thread.");
            zzj(menuItemFindItem);
            com.google.android.gms.tasks.t tVarM = l.m(new IllegalArgumentException("cannot refreshButtonSelector with null mediaRouteActionProvider"));
            final h hVar = null;
            tVarM.c(j.f13856a, new com.google.android.gms.tasks.e(hVar) { // from class: com.google.android.gms.cast.framework.zze
                @Override // com.google.android.gms.tasks.e
                public final /* synthetic */ void onSuccess(Object obj) {
                    CastButtonFactory.zzb(null, (MenuItem) obj);
                }
            });
            return tVarM;
        } catch (IllegalArgumentException e5) {
            Locale locale2 = Locale.ROOT;
            return l.m(new IllegalArgumentException(c.o(i10, "menu item with ID ", " doesn't have a MediaRouteActionProvider."), e5));
        }
    }

    public static com.google.android.gms.tasks.h setUpMediaRouteButton(Context context, Executor executor, b bVar) {
        t.e("Must be called from the main thread.");
        l6.a(m5.CAST_DEFAULT_MEDIA_ROUTER_DIALOG);
        return l.n(null);
    }
}
