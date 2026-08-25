package com.google.android.gms.internal.cast;

import android.content.Context;
import android.os.Bundle;
import android.os.PowerManager;
import com.google.android.gms.cast.CastDevice;
import com.google.android.gms.cast.CastMediaControlIntent;
import com.google.android.gms.cast.framework.CastOptions;
import com.google.android.gms.cast.internal.Logger;
import j$.util.concurrent.ConcurrentHashMap;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Set;

/* JADX INFO: loaded from: classes4.dex */
public final class l2 {

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public static l2 f13395l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public static final Logger f13396m = new Logger("RemoteConnectionManager");

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final CastOptions f13397a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final c0 f13398b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final y0 f13399c;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final ConcurrentHashMap f13402f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final ConcurrentHashMap f13403g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final PowerManager f13404h;
    public boolean k;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final Object f13405i = new Object();

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final Object f13406j = new Object();

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final Set f13401e = Collections.newSetFromMap(new ConcurrentHashMap());

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final androidx.mediarouter.app.d f13400d = new androidx.mediarouter.app.d(this, 1);

    public l2(Context context, CastOptions castOptions, c0 c0Var, y0 y0Var) {
        this.f13397a = castOptions;
        this.f13399c = y0Var;
        r0 r0Var = new r0(this, 1);
        this.f13398b = c0Var;
        c0Var.f13205c.add(r0Var);
        this.f13402f = new ConcurrentHashMap();
        this.f13403g = new ConcurrentHashMap();
        this.f13404h = (PowerManager) context.getSystemService("power");
        new androidx.appcompat.app.n0(this, 4);
    }

    public final /* synthetic */ void a() {
        synchronized (this.f13406j) {
            try {
                Iterator it = this.f13402f.values().iterator();
                if (it.hasNext()) {
                    if (it.next() != null) {
                        throw new ClassCastException();
                    }
                    this.f13398b.getClass();
                    PowerManager powerManager = this.f13404h;
                    if (powerManager != null) {
                        powerManager.isInteractive();
                    }
                    throw null;
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public final /* synthetic */ void b() {
        synchronized (this.f13406j) {
            try {
                Iterator it = this.f13402f.values().iterator();
                if (it.hasNext()) {
                    if (it.next() != null) {
                        throw new ClassCastException();
                    }
                    throw null;
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public final void c() {
        androidx.mediarouter.media.z zVar;
        boolean zIsEmpty = this.f13401e.isEmpty();
        boolean zA = this.f13398b.a();
        androidx.mediarouter.app.d dVar = this.f13400d;
        y0 y0Var = this.f13399c;
        Logger logger = f13396m;
        if (zA || zIsEmpty) {
            if (this.k) {
                this.k = false;
                logger.d("Stopping RemoteConnectionManager discovery.", new Object[0]);
                if (((androidx.mediarouter.media.k0) y0Var.f13692b) == null) {
                    y0Var.f13692b = androidx.mediarouter.media.k0.d((Context) y0Var.f13691a);
                }
                androidx.mediarouter.media.k0 k0Var = (androidx.mediarouter.media.k0) y0Var.f13692b;
                if (k0Var != null) {
                    k0Var.e(dVar);
                    return;
                }
                return;
            }
            return;
        }
        if (this.k) {
            return;
        }
        logger.d("Starting RemoteConnectionManager discovery.", new Object[0]);
        androidx.mediarouter.media.k0 k0Var2 = (androidx.mediarouter.media.k0) y0Var.f13692b;
        Context context = (Context) y0Var.f13691a;
        if (k0Var2 == null) {
            y0Var.f13692b = androidx.mediarouter.media.k0.d(context);
        }
        androidx.mediarouter.media.k0 k0Var3 = (androidx.mediarouter.media.k0) y0Var.f13692b;
        if (k0Var3 != null) {
            k0Var3.e(dVar);
        }
        String receiverApplicationId = this.f13397a.getReceiverApplicationId();
        if (receiverApplicationId.isEmpty()) {
            logger.d("Failed to create MediaRouteSelector. No target receiver app ID has been set.", new Object[0]);
            zVar = null;
        } else {
            String strCategoryForCast = CastMediaControlIntent.categoryForCast(receiverApplicationId);
            if (strCategoryForCast == null) {
                throw new IllegalArgumentException("category must not be null");
            }
            ArrayList<String> arrayList = new ArrayList<>();
            if (!arrayList.contains(strCategoryForCast)) {
                arrayList.add(strCategoryForCast);
            }
            Bundle bundle = new Bundle();
            bundle.putStringArrayList("controlCategories", arrayList);
            zVar = new androidx.mediarouter.media.z(bundle, arrayList);
        }
        if (zVar == null) {
            logger.d("Skipping starting discovery. No target receiver app ID has been set.", new Object[0]);
            return;
        }
        this.k = true;
        logger.d("Adding mediaRouter callback for control category ".concat(String.valueOf(zVar.c())), new Object[0]);
        if (((androidx.mediarouter.media.k0) y0Var.f13692b) == null) {
            y0Var.f13692b = androidx.mediarouter.media.k0.d(context);
        }
        ((androidx.mediarouter.media.k0) y0Var.f13692b).a(zVar, dVar, 4);
    }

    public final /* synthetic */ void d(Bundle bundle) {
        CastDevice fromBundle;
        if (bundle == null || (fromBundle = CastDevice.getFromBundle(bundle)) == null) {
            return;
        }
        String string = bundle.getString("com.google.android.gms.cast.EXTRA_RUNNING_RECEIVER_APP_ID");
        ConcurrentHashMap concurrentHashMap = this.f13402f;
        if (concurrentHashMap.containsKey(fromBundle.getDeviceId()) && concurrentHashMap.get(fromBundle.getDeviceId()) != null) {
            throw new ClassCastException();
        }
        String receiverApplicationId = this.f13397a.getReceiverApplicationId();
        if (string == null || receiverApplicationId.isEmpty() || !string.equals(receiverApplicationId)) {
            e(fromBundle);
            return;
        }
        ConcurrentHashMap concurrentHashMap2 = this.f13403g;
        if (concurrentHashMap2.containsKey(fromBundle.getDeviceId())) {
        } else {
            concurrentHashMap2.put(fromBundle.getDeviceId(), new k2());
        }
        Set set = this.f13401e;
        if (set.isEmpty()) {
            return;
        }
        synchronized (this.f13405i) {
            try {
                Iterator it = set.iterator();
                if (it.hasNext()) {
                    if (it.next() != null) {
                        throw new ClassCastException();
                    }
                    throw null;
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public final void e(CastDevice castDevice) {
        if (((k2) this.f13403g.remove(castDevice.getDeviceId())) != null) {
            synchronized (this.f13405i) {
                try {
                    Iterator it = this.f13401e.iterator();
                    if (it.hasNext()) {
                        if (it.next() != null) {
                            throw new ClassCastException();
                        }
                        throw null;
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
        }
    }
}
