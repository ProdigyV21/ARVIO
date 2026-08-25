package androidx.mediarouter.media;

import android.os.Handler;
import android.os.Message;
import androidx.media3.extractor.ts.TsExtractor;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;

/* JADX INFO: loaded from: classes3.dex */
public final class c extends Handler {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final ArrayList f4306a = new ArrayList();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final ArrayList f4307b = new ArrayList();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final /* synthetic */ f f4308c;

    public c(f fVar) {
        this.f4308c = fVar;
    }

    public static void a(b0 b0Var, int i10, Object obj, int i11) {
        i0 i0Var;
        i0 i0Var2;
        k0 k0Var = b0Var.f4299a;
        a0 a0Var = b0Var.f4300b;
        int i12 = 65280 & i10;
        if (i12 != 256) {
            if (i12 != 512) {
                if (i12 == 768 && i10 == 769) {
                    a0Var.onRouterParamsChanged(k0Var, (o0) obj);
                    return;
                }
                return;
            }
            g0 g0Var = (g0) obj;
            switch (i10) {
                case 513:
                    a0Var.onProviderAdded(k0Var, g0Var);
                    return;
                case 514:
                    a0Var.onProviderRemoved(k0Var, g0Var);
                    return;
                case 515:
                    a0Var.onProviderChanged(k0Var, g0Var);
                    return;
                default:
                    return;
            }
        }
        if (i10 == 264 || i10 == 262) {
            e eVar = (e) obj;
            i0 i0Var3 = eVar.f4323b;
            i0Var = eVar.f4322a;
            i0Var2 = i0Var3;
        } else {
            if (i10 == 265 || i10 == 266) {
                obj.getClass();
                throw new ClassCastException();
            }
            i0Var2 = (i0) obj;
            i0Var = null;
        }
        if (i0Var2 != null) {
            boolean zC = true;
            if ((b0Var.f4302d & 2) == 0 && !i0Var2.e(b0Var.f4301c)) {
                o0 o0Var = k0.c().f4346v;
                zC = ((o0Var == null ? false : o0Var.f4468c) && i0Var2.c() && i10 == 262 && i11 == 3 && i0Var != null) ? true ^ i0Var.c() : false;
            }
            if (zC) {
                switch (i10) {
                    case TsExtractor.TS_STREAM_TYPE_AIT /* 257 */:
                        a0Var.onRouteAdded(k0Var, i0Var2);
                        return;
                    case 258:
                        a0Var.onRouteRemoved(k0Var, i0Var2);
                        return;
                    case 259:
                        a0Var.onRouteChanged(k0Var, i0Var2);
                        return;
                    case 260:
                        a0Var.onRouteVolumeChanged(k0Var, i0Var2);
                        return;
                    case 261:
                        a0Var.onRoutePresentationDisplayChanged(k0Var, i0Var2);
                        return;
                    case 262:
                        a0Var.onRouteSelected(k0Var, i0Var2, i11, i0Var2);
                        return;
                    case 263:
                        a0Var.onRouteUnselected(k0Var, i0Var2, i11);
                        return;
                    case 264:
                        a0Var.onRouteSelected(k0Var, i0Var2, i11, i0Var);
                        return;
                    case 265:
                        a0Var.onRouteConnected(k0Var, i0Var, i0Var2);
                        return;
                    case 266:
                        a0Var.onRouteDisconnected(k0Var, i0Var, i0Var2, i11);
                        return;
                    default:
                        return;
                }
            }
        }
    }

    public final void b(int i10, Object obj) {
        obtainMessage(i10, obj).sendToTarget();
    }

    @Override // android.os.Handler
    public final void handleMessage(Message message) {
        int iK;
        ArrayList arrayList = this.f4306a;
        f fVar = this.f4308c;
        ArrayList arrayList2 = fVar.f4334i;
        y0 y0Var = fVar.f4344t;
        int i10 = message.what;
        Object obj = message.obj;
        int i11 = message.arg1;
        if (i10 == 259 && fVar.g().f4388c.equals(((i0) obj).f4388c)) {
            fVar.r(true);
        }
        ArrayList arrayList3 = this.f4307b;
        if (i10 == 262) {
            e eVar = (e) obj;
            i0 i0Var = eVar.f4323b;
            if (eVar.f4324c) {
                y0Var.q(i0Var);
            }
            if (fVar.f4347w != null && i0Var.c()) {
                Iterator it = arrayList3.iterator();
                while (it.hasNext()) {
                    y0Var.p((i0) it.next());
                }
                arrayList3.clear();
            }
        } else if (i10 != 264) {
            switch (i10) {
                case TsExtractor.TS_STREAM_TYPE_AIT /* 257 */:
                    y0Var.o((i0) obj);
                    break;
                case 258:
                    y0Var.p((i0) obj);
                    break;
                case 259:
                    i0 i0Var2 = (i0) obj;
                    y0Var.getClass();
                    if (i0Var2.a() != y0Var && (iK = y0Var.k(i0Var2)) >= 0) {
                        y0.t((x0) y0Var.B.get(iK));
                    }
                    break;
            }
        } else {
            e eVar2 = (e) obj;
            i0 i0Var3 = eVar2.f4323b;
            arrayList3.add(i0Var3);
            y0Var.o(i0Var3);
            if (eVar2.f4324c) {
                y0Var.q(i0Var3);
            }
        }
        try {
            int size = arrayList2.size();
            while (true) {
                size--;
                if (size < 0) {
                    Iterator it2 = arrayList.iterator();
                    while (it2.hasNext()) {
                        a((b0) it2.next(), i10, obj, i11);
                    }
                    arrayList.clear();
                    return;
                }
                k0 k0Var = (k0) ((WeakReference) arrayList2.get(size)).get();
                if (k0Var == null) {
                    arrayList2.remove(size);
                } else {
                    arrayList.addAll(k0Var.f4427b);
                }
            }
        } catch (Throwable th) {
            arrayList.clear();
            throw th;
        }
    }
}
