package com.google.android.gms.internal.cast;

import android.content.Context;
import android.os.Bundle;
import android.os.Looper;
import com.google.android.gms.cast.CastMediaControlIntent;
import com.google.android.gms.cast.internal.Logger;
import j$.util.DesugarCollections;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

/* JADX INFO: loaded from: classes4.dex */
public final class t0 extends androidx.mediarouter.media.a0 {

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final Logger f13604f = new Logger("MRDiscoveryCallback");

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final y0 f13609e;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final Map f13607c = DesugarCollections.synchronizedMap(new HashMap());

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final LinkedHashSet f13608d = new LinkedHashSet();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Set f13606b = Collections.synchronizedSet(new LinkedHashSet());

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final r0 f13605a = new r0(this, 0);

    public t0(Context context) {
        this.f13609e = new y0(context);
    }

    /* JADX WARN: Code restructure failed: missing block: B:43:0x0196, code lost:
    
        r0 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:50:0x019d, code lost:
    
        throw r0;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void a(androidx.mediarouter.media.i0 r17, boolean r18) {
        /*
            Method dump skipped, instruction units count: 417
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.cast.t0.a(androidx.mediarouter.media.i0, boolean):void");
    }

    public final void b() {
        int size = this.f13608d.size();
        StringBuilder sb2 = new StringBuilder(String.valueOf(size).length() + 33);
        sb2.append("Starting RouteDiscovery with ");
        sb2.append(size);
        sb2.append(" IDs");
        Logger logger = f13604f;
        logger.d(sb2.toString(), new Object[0]);
        logger.d("appIdToRouteInfo has these appId route keys: ".concat(String.valueOf(this.f13607c.keySet())), new Object[0]);
        if (Looper.myLooper() == Looper.getMainLooper()) {
            c();
        } else {
            new androidx.loader.content.j(Looper.getMainLooper(), 2).post(new s0(this, 0));
        }
    }

    public final void c() {
        y0 y0Var = this.f13609e;
        if (((androidx.mediarouter.media.k0) y0Var.f13692b) == null) {
            y0Var.f13692b = androidx.mediarouter.media.k0.d((Context) y0Var.f13691a);
        }
        androidx.mediarouter.media.k0 k0Var = (androidx.mediarouter.media.k0) y0Var.f13692b;
        if (k0Var != null) {
            k0Var.e(this);
        }
        LinkedHashSet<String> linkedHashSet = this.f13608d;
        synchronized (linkedHashSet) {
            try {
                for (String str : linkedHashSet) {
                    String strCategoryForCast = CastMediaControlIntent.categoryForCast(str);
                    if (strCategoryForCast == null) {
                        throw new IllegalArgumentException("category must not be null");
                    }
                    ArrayList<String> arrayList = new ArrayList<>();
                    if (!arrayList.contains(strCategoryForCast)) {
                        arrayList.add(strCategoryForCast);
                    }
                    Bundle bundle = new Bundle();
                    bundle.putStringArrayList("controlCategories", arrayList);
                    androidx.mediarouter.media.z zVar = new androidx.mediarouter.media.z(bundle, arrayList);
                    Map map = this.f13607c;
                    if (((q0) map.get(str)) == null) {
                        map.put(str, new q0(zVar));
                    }
                    Logger logger = f13604f;
                    String strCategoryForCast2 = CastMediaControlIntent.categoryForCast(str);
                    StringBuilder sb2 = new StringBuilder(strCategoryForCast2.length() + 49);
                    sb2.append("Adding mediaRouter callback for control category ");
                    sb2.append(strCategoryForCast2);
                    logger.d(sb2.toString(), new Object[0]);
                    if (((androidx.mediarouter.media.k0) y0Var.f13692b) == null) {
                        y0Var.f13692b = androidx.mediarouter.media.k0.d((Context) y0Var.f13691a);
                    }
                    ((androidx.mediarouter.media.k0) y0Var.f13692b).a(zVar, this, 4);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        Map map2 = this.f13607c;
        f13604f.d("appIdToRouteInfo has these appId route keys: ".concat(String.valueOf(map2.keySet())), new Object[0]);
    }

    @Override // androidx.mediarouter.media.a0
    public final void onRouteAdded(androidx.mediarouter.media.k0 k0Var, androidx.mediarouter.media.i0 i0Var) {
        f13604f.d("MediaRouterDiscoveryCallback.onRouteAdded.", new Object[0]);
        a(i0Var, true);
    }

    @Override // androidx.mediarouter.media.a0
    public final void onRouteChanged(androidx.mediarouter.media.k0 k0Var, androidx.mediarouter.media.i0 i0Var) {
        f13604f.d("MediaRouterDiscoveryCallback.onRouteChanged.", new Object[0]);
        a(i0Var, true);
    }

    @Override // androidx.mediarouter.media.a0
    public final void onRouteRemoved(androidx.mediarouter.media.k0 k0Var, androidx.mediarouter.media.i0 i0Var) {
        f13604f.d("MediaRouterDiscoveryCallback.onRouteRemoved.", new Object[0]);
        a(i0Var, false);
    }
}
