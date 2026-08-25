package com.google.android.gms.internal.cast;

import android.os.RemoteException;
import com.google.android.gms.cast.CastDevice;
import com.google.android.gms.cast.framework.SessionManager;
import com.google.android.gms.cast.internal.Logger;
import java.util.ArrayList;
import java.util.Collections;

/* JADX INFO: loaded from: classes4.dex */
public final class p0 extends androidx.mediarouter.media.a0 {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final Logger f13534d = new Logger("MediaRouterCallback");

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final l0 f13535a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final x0 f13536b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final c1 f13537c;

    public p0(l0 l0Var, x0 x0Var, c1 c1Var) {
        com.google.android.gms.common.internal.t.i(l0Var);
        this.f13535a = l0Var;
        this.f13536b = x0Var;
        this.f13537c = c1Var;
    }

    public final void a(androidx.mediarouter.media.k0 k0Var) {
        c1 c1Var = this.f13537c;
        if (c1Var != null && c1Var.f13213f && c1Var.f13208a.zzh()) {
            SessionManager sessionManager = c1Var.f13214g;
            if ((sessionManager != null ? sessionManager.getCurrentCastSession() : null) == null) {
                k0Var.getClass();
                androidx.mediarouter.media.k0.f(null);
                return;
            }
            ArrayList arrayList = new ArrayList();
            k0Var.getClass();
            androidx.mediarouter.media.k0.b();
            for (androidx.mediarouter.media.i0 i0Var : androidx.mediarouter.media.k0.c().f4335j) {
                if (CastDevice.getFromBundle(i0Var.f4403s) != null) {
                    arrayList.add(new androidx.mediarouter.media.u1(new androidx.mediarouter.media.q1(i0Var.f4388c)));
                }
            }
            c1.f13207j.d("updateRouteListingPreference with %d available routes", Integer.valueOf(arrayList.size()));
            androidx.mediarouter.media.x xVar = new androidx.mediarouter.media.x();
            xVar.f4506b = Collections.EMPTY_LIST;
            xVar.f4507c = true;
            xVar.f4506b = Collections.unmodifiableList(new ArrayList(arrayList));
            androidx.mediarouter.media.k0.f(new androidx.mediarouter.media.v1(xVar));
        }
    }

    @Override // androidx.mediarouter.media.a0
    public final void onRouteAdded(androidx.mediarouter.media.k0 k0Var, androidx.mediarouter.media.i0 i0Var) {
        try {
            this.f13535a.W(i0Var.f4403s, i0Var.f4388c);
        } catch (RemoteException e5) {
            f13534d.d(e5, "Unable to call %s on %s.", "onRouteAdded", l0.class.getSimpleName());
        }
        a(k0Var);
    }

    @Override // androidx.mediarouter.media.a0
    public final void onRouteChanged(androidx.mediarouter.media.k0 k0Var, androidx.mediarouter.media.i0 i0Var) {
        i0Var.getClass();
        androidx.mediarouter.media.k0.b();
        if (androidx.mediarouter.media.k0.c().g() == i0Var) {
            try {
                this.f13535a.X(i0Var.f4403s, i0Var.f4388c);
            } catch (RemoteException e5) {
                f13534d.d(e5, "Unable to call %s on %s.", "onRouteChanged", l0.class.getSimpleName());
            }
            a(k0Var);
        }
    }

    @Override // androidx.mediarouter.media.a0
    public final void onRouteConnected(androidx.mediarouter.media.k0 k0Var, androidx.mediarouter.media.i0 i0Var, androidx.mediarouter.media.i0 i0Var2) {
        int i10 = i0Var.f4396l;
        String str = i0Var.f4388c;
        Logger logger = f13534d;
        if (i10 != 1) {
            logger.i("ignore onRouteConnected for non-remote connected routeId: %s", str);
            return;
        }
        logger.i("onRouteConnected with connectedRouteId = %s", str);
        this.f13536b.f13679i = true;
        try {
            l0 l0Var = this.f13535a;
            if (l0Var.zze() >= 251600000) {
                l0Var.C(i0Var2.f4388c, str, i0Var.f4403s);
            } else {
                l0Var.L(i0Var2.f4388c, str, i0Var.f4403s);
            }
        } catch (RemoteException e5) {
            logger.d(e5, "Unable to call %s on %s.", "onRouteConnected", l0.class.getSimpleName());
        }
    }

    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$ArrayArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    @Override // androidx.mediarouter.media.a0
    public final void onRouteDisconnected(androidx.mediarouter.media.k0 k0Var, androidx.mediarouter.media.i0 i0Var, androidx.mediarouter.media.i0 i0Var2, int i10) {
        Logger logger = f13534d;
        if (i0Var != null) {
            String str = i0Var.f4388c;
            if (i0Var.f4396l == 1) {
                logger.i("onRouteDisconnected with disconnectedRouteId = %s, requestedRouteId = %s, reason = %d", str, i0Var2.f4388c, Integer.valueOf(i10));
                this.f13536b.f13679i = false;
                try {
                    l0 l0Var = this.f13535a;
                    if (l0Var.zze() >= 251600000) {
                        l0Var.O(i0Var2.f4388c, str, i0Var.f4403s, i10);
                        return;
                    } else {
                        l0Var.U(i10, str, i0Var.f4403s);
                        return;
                    }
                } catch (RemoteException e5) {
                    logger.d(e5, "Unable to call %s on %s.", "onRouteDisconnected", l0.class.getSimpleName());
                    return;
                }
            }
        }
        logger.i("ignore onRouteDisconnected for invalid or non-remote disconnected route", new Object[0]);
    }

    @Override // androidx.mediarouter.media.a0
    public final void onRouteRemoved(androidx.mediarouter.media.k0 k0Var, androidx.mediarouter.media.i0 i0Var) {
        try {
            this.f13535a.b0(i0Var.f4403s, i0Var.f4388c);
        } catch (RemoteException e5) {
            f13534d.d(e5, "Unable to call %s on %s.", "onRouteRemoved", l0.class.getSimpleName());
        }
        a(k0Var);
    }

    @Override // androidx.mediarouter.media.a0
    public final void onRouteSelected(androidx.mediarouter.media.k0 k0Var, androidx.mediarouter.media.i0 i0Var, int i10, androidx.mediarouter.media.i0 i0Var2) {
        int i11 = i0Var.f4396l;
        String str = i0Var.f4388c;
        Logger logger = f13534d;
        if (i11 != 1) {
            logger.i("ignore onRouteSelected for non-remote selected routeId: %s", str);
            return;
        }
        logger.i("onRouteSelected with reason = %d, routeId = %s", Integer.valueOf(i10), str);
        try {
            l0 l0Var = this.f13535a;
            if (l0Var.zze() >= 220400000) {
                l0Var.L(i0Var2.f4388c, str, i0Var.f4403s);
            } else {
                l0Var.e0(i0Var.f4403s, i0Var2.f4388c);
            }
        } catch (RemoteException e5) {
            logger.d(e5, "Unable to call %s on %s.", "onRouteSelected", l0.class.getSimpleName());
        }
        a(k0Var);
    }

    @Override // androidx.mediarouter.media.a0
    public final void onRouteUnselected(androidx.mediarouter.media.k0 k0Var, androidx.mediarouter.media.i0 i0Var, int i10) {
        int i11 = i0Var.f4396l;
        String str = i0Var.f4388c;
        Logger logger = f13534d;
        if (i11 != 1) {
            logger.i("ignore onRouteUnselected for non-remote routeId: %s", str);
            return;
        }
        logger.i("onRouteUnselected with reason = %d, routeId = %s", Integer.valueOf(i10), str);
        try {
            this.f13535a.U(i10, str, i0Var.f4403s);
        } catch (RemoteException e5) {
            logger.d(e5, "Unable to call %s on %s.", "onRouteUnselected", l0.class.getSimpleName());
        }
        a(k0Var);
    }
}
