package io.sentry.clientreport;

import io.ktor.http.ContentDisposition;
import io.sentry.ILogger;
import io.sentry.k3;
import io.sentry.protocol.f0;
import io.sentry.protocol.h;
import io.sentry.protocol.i;
import io.sentry.protocol.k;
import io.sentry.protocol.o;
import io.sentry.protocol.r;
import io.sentry.protocol.v;
import io.sentry.protocol.x;
import io.sentry.r3;
import io.sentry.u1;
import io.sentry.w5;
import j$.util.concurrent.ConcurrentHashMap;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes5.dex */
public final class a implements u1 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f17187a;

    public /* synthetic */ a(int i10) {
        this.f17187a = i10;
    }

    public static io.sentry.protocol.a b(k3 k3Var, ILogger iLogger) {
        String strNextName;
        k3Var.beginObject();
        io.sentry.protocol.a aVar = new io.sentry.protocol.a();
        ConcurrentHashMap concurrentHashMap = null;
        while (k3Var.peek() == io.sentry.vendor.gson.stream.b.NAME) {
            strNextName = k3Var.nextName();
            strNextName.getClass();
            switch (strNextName) {
                case "split_names":
                    List list = (List) k3Var.F0();
                    if (list == null) {
                        break;
                    } else {
                        aVar.f17541w = list;
                        break;
                    }
                    break;
                case "device_app_hash":
                    aVar.f17531m = k3Var.l0();
                    break;
                case "start_type":
                    aVar.f17538t = k3Var.l0();
                    break;
                case "view_names":
                    List list2 = (List) k3Var.F0();
                    if (list2 == null) {
                        break;
                    } else {
                        aVar.f17537s = list2;
                        break;
                    }
                    break;
                case "app_version":
                    aVar.f17534p = k3Var.l0();
                    break;
                case "in_foreground":
                    aVar.f17539u = k3Var.L();
                    break;
                case "build_type":
                    aVar.f17532n = k3Var.l0();
                    break;
                case "app_identifier":
                    aVar.f17529i = k3Var.l0();
                    break;
                case "app_start_time":
                    aVar.f17530l = k3Var.I(iLogger);
                    break;
                case "permissions":
                    aVar.f17536r = k2.c.w((Map) k3Var.F0());
                    break;
                case "app_name":
                    aVar.f17533o = k3Var.l0();
                    break;
                case "app_build":
                    aVar.f17535q = k3Var.l0();
                    break;
                case "is_split_apks":
                    aVar.f17540v = k3Var.L();
                    break;
                default:
                    if (concurrentHashMap == null) {
                        concurrentHashMap = new ConcurrentHashMap();
                    }
                    k3Var.t(iLogger, concurrentHashMap, strNextName);
                    break;
            }
        }
        aVar.x = concurrentHashMap;
        k3Var.endObject();
        return aVar;
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    public static io.sentry.protocol.c c(k3 k3Var, ILogger iLogger) {
        byte b10;
        String strNextName;
        String strNextName2;
        io.sentry.protocol.c cVar = new io.sentry.protocol.c();
        k3Var.beginObject();
        while (k3Var.peek() == io.sentry.vendor.gson.stream.b.NAME) {
            String strNextName3 = k3Var.nextName();
            strNextName3.getClass();
            int i10 = 11;
            switch (strNextName3.hashCode()) {
                case -1335157162:
                    b10 = !strNextName3.equals("device") ? (byte) -1 : (byte) 0;
                    break;
                case -895679987:
                    b10 = !strNextName3.equals("spring") ? (byte) -1 : (byte) 1;
                    break;
                case -340323263:
                    b10 = !strNextName3.equals("response") ? (byte) -1 : (byte) 2;
                    break;
                case -309425751:
                    b10 = !strNextName3.equals("profile") ? (byte) -1 : (byte) 3;
                    break;
                case -191501435:
                    b10 = !strNextName3.equals("feedback") ? (byte) -1 : (byte) 4;
                    break;
                case 3556:
                    b10 = !strNextName3.equals("os") ? (byte) -1 : (byte) 5;
                    break;
                case 96801:
                    b10 = !strNextName3.equals("app") ? (byte) -1 : (byte) 6;
                    break;
                case 102572:
                    b10 = !strNextName3.equals("gpu") ? (byte) -1 : (byte) 7;
                    break;
                case 97513095:
                    b10 = !strNextName3.equals("flags") ? (byte) -1 : (byte) 8;
                    break;
                case 110620997:
                    b10 = !strNextName3.equals("trace") ? (byte) -1 : (byte) 9;
                    break;
                case 150940456:
                    b10 = !strNextName3.equals("browser") ? (byte) -1 : (byte) 10;
                    break;
                case 1550962648:
                    b10 = !strNextName3.equals("runtime") ? (byte) -1 : (byte) 11;
                    break;
                default:
                    b10 = -1;
                    break;
            }
            ArrayList arrayList = null;
            switch (b10) {
                case 0:
                    cVar.o(d(k3Var, iLogger));
                    break;
                case 1:
                    k3Var.beginObject();
                    f0 f0Var = new f0();
                    ConcurrentHashMap concurrentHashMap = null;
                    while (k3Var.peek() == io.sentry.vendor.gson.stream.b.NAME) {
                        String strNextName4 = k3Var.nextName();
                        strNextName4.getClass();
                        if (strNextName4.equals("active_profiles")) {
                            List list = (List) k3Var.F0();
                            if (list != null) {
                                String[] strArr = new String[list.size()];
                                list.toArray(strArr);
                                f0Var.f17581i = strArr;
                            }
                        } else {
                            if (concurrentHashMap == null) {
                                concurrentHashMap = new ConcurrentHashMap();
                            }
                            k3Var.t(iLogger, concurrentHashMap, strNextName4);
                        }
                    }
                    f0Var.f17582l = concurrentHashMap;
                    k3Var.endObject();
                    cVar.u(f0Var);
                    break;
                case 2:
                    k3Var.beginObject();
                    r rVar = new r();
                    ConcurrentHashMap concurrentHashMap2 = null;
                    while (k3Var.peek() == io.sentry.vendor.gson.stream.b.NAME) {
                        strNextName = k3Var.nextName();
                        strNextName.getClass();
                        switch (strNextName) {
                            case "status_code":
                                rVar.f17686m = k3Var.f0();
                                break;
                            case "data":
                                rVar.f17688o = k3Var.F0();
                                break;
                            case "headers":
                                Map map = (Map) k3Var.F0();
                                if (map != null) {
                                    rVar.f17685l = k2.c.w(map);
                                    break;
                                } else {
                                    break;
                                }
                                break;
                            case "cookies":
                                rVar.f17684i = k3Var.l0();
                                break;
                            case "body_size":
                                rVar.f17687n = k3Var.h0();
                                break;
                            default:
                                if (concurrentHashMap2 == null) {
                                    concurrentHashMap2 = new ConcurrentHashMap();
                                }
                                k3Var.t(iLogger, concurrentHashMap2, strNextName);
                                break;
                        }
                    }
                    rVar.f17689p = concurrentHashMap2;
                    k3Var.endObject();
                    cVar.s(rVar);
                    break;
                case 3:
                    k3Var.beginObject();
                    r3 r3Var = new r3(v.f17707l);
                    ConcurrentHashMap concurrentHashMap3 = null;
                    while (k3Var.peek() == io.sentry.vendor.gson.stream.b.NAME) {
                        String strNextName5 = k3Var.nextName();
                        strNextName5.getClass();
                        if (strNextName5.equals("profiler_id")) {
                            v vVar = (v) k3Var.P(iLogger, new a(23));
                            if (vVar != null) {
                                r3Var.f17775i = vVar;
                            }
                        } else {
                            if (concurrentHashMap3 == null) {
                                concurrentHashMap3 = new ConcurrentHashMap();
                            }
                            k3Var.t(iLogger, concurrentHashMap3, strNextName5);
                        }
                    }
                    r3Var.f17776l = concurrentHashMap3;
                    k3Var.endObject();
                    cVar.k("profile", r3Var);
                    break;
                case 4:
                    cVar.k("feedback", e(k3Var, iLogger));
                    break;
                case 5:
                    cVar.r(g(k3Var, iLogger));
                    break;
                case 6:
                    cVar.m(b(k3Var, iLogger));
                    break;
                case 7:
                    cVar.q(f(k3Var, iLogger));
                    break;
                case 8:
                    k3Var.beginObject();
                    ConcurrentHashMap concurrentHashMap4 = null;
                    while (k3Var.peek() == io.sentry.vendor.gson.stream.b.NAME) {
                        String strNextName6 = k3Var.nextName();
                        strNextName6.getClass();
                        if (strNextName6.equals("values")) {
                            arrayList = k3Var.I0(iLogger, new a(i10));
                        } else {
                            if (concurrentHashMap4 == null) {
                                concurrentHashMap4 = new ConcurrentHashMap();
                            }
                            k3Var.t(iLogger, concurrentHashMap4, strNextName6);
                        }
                    }
                    if (arrayList == null) {
                        arrayList = new ArrayList();
                    }
                    h hVar = new h(arrayList);
                    hVar.f17589l = concurrentHashMap4;
                    k3Var.endObject();
                    cVar.p(hVar);
                    break;
                case 9:
                    cVar.v(io.sentry.e.b(k3Var, iLogger));
                    break;
                case 10:
                    k3Var.beginObject();
                    io.sentry.protocol.b bVar = new io.sentry.protocol.b();
                    ConcurrentHashMap concurrentHashMap5 = null;
                    while (k3Var.peek() == io.sentry.vendor.gson.stream.b.NAME) {
                        String strNextName7 = k3Var.nextName();
                        strNextName7.getClass();
                        if (strNextName7.equals(ContentDisposition.Parameters.Name)) {
                            bVar.f17542i = k3Var.l0();
                        } else if (strNextName7.equals("version")) {
                            bVar.f17543l = k3Var.l0();
                        } else {
                            if (concurrentHashMap5 == null) {
                                concurrentHashMap5 = new ConcurrentHashMap();
                            }
                            k3Var.t(iLogger, concurrentHashMap5, strNextName7);
                        }
                    }
                    bVar.f17544m = concurrentHashMap5;
                    k3Var.endObject();
                    cVar.n(bVar);
                    break;
                case 11:
                    k3Var.beginObject();
                    x xVar = new x();
                    ConcurrentHashMap concurrentHashMap6 = null;
                    while (k3Var.peek() == io.sentry.vendor.gson.stream.b.NAME) {
                        strNextName2 = k3Var.nextName();
                        strNextName2.getClass();
                        switch (strNextName2) {
                            case "raw_description":
                                xVar.f17714m = k3Var.l0();
                                break;
                            case "name":
                                xVar.f17712i = k3Var.l0();
                                break;
                            case "version":
                                xVar.f17713l = k3Var.l0();
                                break;
                            default:
                                if (concurrentHashMap6 == null) {
                                    concurrentHashMap6 = new ConcurrentHashMap();
                                }
                                k3Var.t(iLogger, concurrentHashMap6, strNextName2);
                                break;
                        }
                    }
                    xVar.f17715n = concurrentHashMap6;
                    k3Var.endObject();
                    cVar.t(xVar);
                    break;
                default:
                    Object objF0 = k3Var.F0();
                    if (objF0 != null) {
                        cVar.k(strNextName3, objF0);
                    }
                    break;
            }
        }
        k3Var.endObject();
        return cVar;
    }

    public static io.sentry.protocol.f d(k3 k3Var, ILogger iLogger) {
        String strNextName;
        int i10;
        k3Var.beginObject();
        io.sentry.protocol.f fVar = new io.sentry.protocol.f();
        ConcurrentHashMap concurrentHashMap = null;
        while (k3Var.peek() == io.sentry.vendor.gson.stream.b.NAME) {
            strNextName = k3Var.nextName();
            strNextName.getClass();
            i10 = 10;
            switch (strNextName) {
                case "timezone":
                    fVar.J = k3Var.v(iLogger);
                    break;
                case "boot_time":
                    if (k3Var.peek() != io.sentry.vendor.gson.stream.b.STRING) {
                        break;
                    } else {
                        fVar.I = k3Var.I(iLogger);
                        break;
                    }
                    break;
                case "simulator":
                    fVar.f17578v = k3Var.L();
                    break;
                case "manufacturer":
                    fVar.f17568l = k3Var.l0();
                    break;
                case "processor_count":
                    fVar.O = k3Var.f0();
                    break;
                case "orientation":
                    fVar.f17577u = (io.sentry.protocol.e) k3Var.P(iLogger, new a(i10));
                    break;
                case "battery_temperature":
                    fVar.N = k3Var.B0();
                    break;
                case "family":
                    fVar.f17570n = k3Var.l0();
                    break;
                case "locale":
                    fVar.L = k3Var.l0();
                    break;
                case "online":
                    fVar.f17576t = k3Var.L();
                    break;
                case "battery_level":
                    fVar.f17574r = k3Var.B0();
                    break;
                case "model_id":
                    fVar.f17572p = k3Var.l0();
                    break;
                case "screen_density":
                    fVar.G = k3Var.B0();
                    break;
                case "screen_dpi":
                    fVar.H = k3Var.f0();
                    break;
                case "free_memory":
                    fVar.x = k3Var.h0();
                    break;
                case "id":
                    fVar.K = k3Var.l0();
                    break;
                case "name":
                    fVar.f17567i = k3Var.l0();
                    break;
                case "low_memory":
                    fVar.z = k3Var.L();
                    break;
                case "archs":
                    List list = (List) k3Var.F0();
                    if (list == null) {
                        break;
                    } else {
                        String[] strArr = new String[list.size()];
                        list.toArray(strArr);
                        fVar.f17573q = strArr;
                        break;
                    }
                    break;
                case "brand":
                    fVar.f17569m = k3Var.l0();
                    break;
                case "model":
                    fVar.f17571o = k3Var.l0();
                    break;
                case "cpu_description":
                    fVar.Q = k3Var.l0();
                    break;
                case "processor_frequency":
                    fVar.P = k3Var.F();
                    break;
                case "connection_type":
                    fVar.M = k3Var.l0();
                    break;
                case "chipset":
                    fVar.R = k3Var.l0();
                    break;
                case "screen_width_pixels":
                    fVar.E = k3Var.f0();
                    break;
                case "external_storage_size":
                    fVar.C = k3Var.h0();
                    break;
                case "storage_size":
                    fVar.A = k3Var.h0();
                    break;
                case "usable_memory":
                    fVar.f17580y = k3Var.h0();
                    break;
                case "memory_size":
                    fVar.f17579w = k3Var.h0();
                    break;
                case "charging":
                    fVar.f17575s = k3Var.L();
                    break;
                case "external_free_storage":
                    fVar.D = k3Var.h0();
                    break;
                case "free_storage":
                    fVar.B = k3Var.h0();
                    break;
                case "screen_height_pixels":
                    fVar.F = k3Var.f0();
                    break;
                default:
                    if (concurrentHashMap == null) {
                        concurrentHashMap = new ConcurrentHashMap();
                    }
                    k3Var.t(iLogger, concurrentHashMap, strNextName);
                    break;
            }
        }
        fVar.S = concurrentHashMap;
        k3Var.endObject();
        return fVar;
    }

    public static i e(k3 k3Var, ILogger iLogger) {
        String strNextName;
        k3Var.beginObject();
        String strL0 = null;
        String strL02 = null;
        String strL03 = null;
        v vVar = null;
        v vVar2 = null;
        String strL04 = null;
        HashMap map = null;
        while (k3Var.peek() == io.sentry.vendor.gson.stream.b.NAME) {
            strNextName = k3Var.nextName();
            strNextName.getClass();
            switch (strNextName) {
                case "associated_event_id":
                    vVar = new v(k3Var.nextString());
                    break;
                case "replay_id":
                    vVar2 = new v(k3Var.nextString());
                    break;
                case "url":
                    strL04 = k3Var.l0();
                    break;
                case "name":
                    strL03 = k3Var.l0();
                    break;
                case "contact_email":
                    strL02 = k3Var.l0();
                    break;
                case "message":
                    strL0 = k3Var.l0();
                    break;
                default:
                    if (map == null) {
                        map = new HashMap();
                    }
                    k3Var.t(iLogger, map, strNextName);
                    break;
            }
        }
        k3Var.endObject();
        if (strL0 == null) {
            IllegalStateException illegalStateException = new IllegalStateException("Missing required field \"message\"");
            iLogger.l(w5.ERROR, "Missing required field \"message\"", illegalStateException);
            throw illegalStateException;
        }
        i iVar = new i(strL0);
        iVar.f17591l = strL02;
        iVar.f17592m = strL03;
        iVar.f17593n = vVar;
        iVar.f17594o = vVar2;
        iVar.f17595p = strL04;
        iVar.f17596q = map;
        return iVar;
    }

    public static k f(k3 k3Var, ILogger iLogger) {
        String strNextName;
        k3Var.beginObject();
        k kVar = new k();
        ConcurrentHashMap concurrentHashMap = null;
        while (k3Var.peek() == io.sentry.vendor.gson.stream.b.NAME) {
            strNextName = k3Var.nextName();
            strNextName.getClass();
            switch (strNextName) {
                case "npot_support":
                    kVar.f17620s = k3Var.l0();
                    break;
                case "vendor_id":
                    kVar.f17614m = k3Var.l0();
                    break;
                case "multi_threaded_rendering":
                    kVar.f17618q = k3Var.L();
                    break;
                case "id":
                    kVar.f17613l = k3Var.f0();
                    break;
                case "name":
                    kVar.f17612i = k3Var.l0();
                    break;
                case "vendor_name":
                    kVar.f17615n = k3Var.l0();
                    break;
                case "version":
                    kVar.f17619r = k3Var.l0();
                    break;
                case "api_type":
                    kVar.f17617p = k3Var.l0();
                    break;
                case "memory_size":
                    kVar.f17616o = k3Var.f0();
                    break;
                default:
                    if (concurrentHashMap == null) {
                        concurrentHashMap = new ConcurrentHashMap();
                    }
                    k3Var.t(iLogger, concurrentHashMap, strNextName);
                    break;
            }
        }
        kVar.f17621t = concurrentHashMap;
        k3Var.endObject();
        return kVar;
    }

    public static o g(k3 k3Var, ILogger iLogger) {
        String strNextName;
        k3Var.beginObject();
        o oVar = new o();
        ConcurrentHashMap concurrentHashMap = null;
        while (k3Var.peek() == io.sentry.vendor.gson.stream.b.NAME) {
            strNextName = k3Var.nextName();
            strNextName.getClass();
            switch (strNextName) {
                case "rooted":
                    oVar.f17658p = k3Var.L();
                    break;
                case "raw_description":
                    oVar.f17655m = k3Var.l0();
                    break;
                case "name":
                    oVar.f17653i = k3Var.l0();
                    break;
                case "build":
                    oVar.f17656n = k3Var.l0();
                    break;
                case "version":
                    oVar.f17654l = k3Var.l0();
                    break;
                case "kernel_version":
                    oVar.f17657o = k3Var.l0();
                    break;
                default:
                    if (concurrentHashMap == null) {
                        concurrentHashMap = new ConcurrentHashMap();
                    }
                    k3Var.t(iLogger, concurrentHashMap, strNextName);
                    break;
            }
        }
        oVar.f17659q = concurrentHashMap;
        k3Var.endObject();
        return oVar;
    }

    public static IllegalStateException h(String str, ILogger iLogger) {
        String strL = a0.c.l("Missing required field \"", str, "\"");
        IllegalStateException illegalStateException = new IllegalStateException(strL);
        iLogger.l(w5.ERROR, strL, illegalStateException);
        return illegalStateException;
    }

    public static IllegalStateException i(String str, ILogger iLogger) {
        String strL = a0.c.l("Missing required field \"", str, "\"");
        IllegalStateException illegalStateException = new IllegalStateException(strL);
        iLogger.l(w5.ERROR, strL, illegalStateException);
        return illegalStateException;
    }

    public static IllegalStateException j(String str, ILogger iLogger) {
        String strL = a0.c.l("Missing required field \"", str, "\"");
        IllegalStateException illegalStateException = new IllegalStateException(strL);
        iLogger.l(w5.ERROR, strL, illegalStateException);
        return illegalStateException;
    }

    /*  JADX ERROR: NullPointerException in pass: SwitchBreakVisitor
        java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.SSAVar.use(jadx.core.dex.instructions.args.RegisterArg)" because "ssaVar" is null
        	at jadx.core.dex.nodes.InsnNode.rebindArgs(InsnNode.java:506)
        	at jadx.core.dex.nodes.InsnNode.rebindArgs(InsnNode.java:509)
        */
    @Override // io.sentry.u1
    public final java.lang.Object a(io.sentry.k3 r36, io.sentry.ILogger r37) {
        /*
            Method dump skipped, instruction units count: 4500
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: io.sentry.clientreport.a.a(io.sentry.k3, io.sentry.ILogger):java.lang.Object");
    }
}
