package io.sentry.protocol;

import io.ktor.http.ContentDisposition;
import io.ktor.http.LinkHeader;
import io.sentry.ILogger;
import io.sentry.k3;
import io.sentry.u1;
import io.sentry.w5;
import j$.util.concurrent.ConcurrentHashMap;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/* JADX INFO: loaded from: classes5.dex */
public final class c0 implements u1 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f17552a;

    public /* synthetic */ c0(int i10) {
        this.f17552a = i10;
    }

    public static io.sentry.rrweb.a b(k3 k3Var, ILogger iLogger) {
        String strNextName;
        k3Var.beginObject();
        io.sentry.rrweb.a aVar = new io.sentry.rrweb.a();
        HashMap map = null;
        while (k3Var.peek() == io.sentry.vendor.gson.stream.b.NAME) {
            String strNextName2 = k3Var.nextName();
            strNextName2.getClass();
            if (strNextName2.equals("data")) {
                k3Var.beginObject();
                ConcurrentHashMap concurrentHashMap = null;
                while (k3Var.peek() == io.sentry.vendor.gson.stream.b.NAME) {
                    String strNextName3 = k3Var.nextName();
                    strNextName3.getClass();
                    if (strNextName3.equals("payload")) {
                        k3Var.beginObject();
                        ConcurrentHashMap concurrentHashMap2 = null;
                        while (k3Var.peek() == io.sentry.vendor.gson.stream.b.NAME) {
                            strNextName = k3Var.nextName();
                            strNextName.getClass();
                            switch (strNextName) {
                                case "data":
                                    ConcurrentHashMap concurrentHashMapW = k2.c.w((Map) k3Var.F0());
                                    if (concurrentHashMapW == null) {
                                        break;
                                    } else {
                                        aVar.f17796s = concurrentHashMapW;
                                        break;
                                    }
                                    break;
                                case "type":
                                    aVar.f17792o = k3Var.l0();
                                    break;
                                case "category":
                                    aVar.f17793p = k3Var.l0();
                                    break;
                                case "timestamp":
                                    aVar.f17791n = k3Var.nextDouble();
                                    break;
                                case "level":
                                    try {
                                        aVar.f17795r = w5.valueOf(k3Var.nextString().toUpperCase(Locale.ROOT));
                                        break;
                                    } catch (Exception e5) {
                                        iLogger.k(w5.DEBUG, e5, "Error when deserializing SentryLevel", new Object[0]);
                                        break;
                                    }
                                    break;
                                case "message":
                                    aVar.f17794q = k3Var.l0();
                                    break;
                                default:
                                    if (concurrentHashMap2 == null) {
                                        concurrentHashMap2 = new ConcurrentHashMap();
                                    }
                                    k3Var.t(iLogger, concurrentHashMap2, strNextName);
                                    break;
                            }
                        }
                        aVar.f17798u = concurrentHashMap2;
                        k3Var.endObject();
                    } else if (strNextName3.equals("tag")) {
                        String strL0 = k3Var.l0();
                        if (strL0 == null) {
                            strL0 = "";
                        }
                        aVar.f17790m = strL0;
                    } else {
                        if (concurrentHashMap == null) {
                            concurrentHashMap = new ConcurrentHashMap();
                        }
                        k3Var.t(iLogger, concurrentHashMap, strNextName3);
                    }
                }
                aVar.f17799v = concurrentHashMap;
                k3Var.endObject();
            } else if (strNextName2.equals(LinkHeader.Parameters.Type)) {
                io.sentry.rrweb.c cVar = (io.sentry.rrweb.c) k3Var.P(iLogger, new c0(10));
                ac.b.V(cVar, "");
                aVar.f17800i = cVar;
            } else if (strNextName2.equals("timestamp")) {
                aVar.f17801l = k3Var.nextLong();
            } else {
                if (map == null) {
                    map = new HashMap();
                }
                k3Var.t(iLogger, map, strNextName2);
            }
        }
        aVar.f17797t = map;
        k3Var.endObject();
        return aVar;
    }

    public static io.sentry.rrweb.g c(k3 k3Var, ILogger iLogger) {
        String strNextName;
        k3Var.beginObject();
        io.sentry.rrweb.g gVar = new io.sentry.rrweb.g();
        HashMap map = null;
        while (k3Var.peek() == io.sentry.vendor.gson.stream.b.NAME) {
            String strNextName2 = k3Var.nextName();
            strNextName2.getClass();
            if (strNextName2.equals("data")) {
                k3Var.beginObject();
                HashMap map2 = null;
                while (k3Var.peek() == io.sentry.vendor.gson.stream.b.NAME) {
                    strNextName = k3Var.nextName();
                    strNextName.getClass();
                    switch (strNextName) {
                        case "x":
                            gVar.f17805p = k3Var.nextFloat();
                            break;
                        case "y":
                            gVar.f17806q = k3Var.nextFloat();
                            break;
                        case "id":
                            gVar.f17804o = k3Var.nextInt();
                            break;
                        case "type":
                            gVar.f17803n = (io.sentry.rrweb.f) k3Var.P(iLogger, new c0(13));
                            break;
                        case "pointerType":
                            gVar.f17807r = k3Var.nextInt();
                            break;
                        case "pointerId":
                            gVar.f17808s = k3Var.nextInt();
                            break;
                        default:
                            if (!strNextName.equals("source")) {
                                if (map2 == null) {
                                    map2 = new HashMap();
                                }
                                k3Var.t(iLogger, map2, strNextName);
                                break;
                            } else {
                                io.sentry.rrweb.d dVar = (io.sentry.rrweb.d) k3Var.P(iLogger, new c0(11));
                                ac.b.V(dVar, "");
                                gVar.f17802m = dVar;
                                break;
                            }
                            break;
                    }
                }
                gVar.f17810u = map2;
                k3Var.endObject();
            } else if (strNextName2.equals(LinkHeader.Parameters.Type)) {
                io.sentry.rrweb.c cVar = (io.sentry.rrweb.c) k3Var.P(iLogger, new c0(10));
                ac.b.V(cVar, "");
                gVar.f17800i = cVar;
            } else if (strNextName2.equals("timestamp")) {
                gVar.f17801l = k3Var.nextLong();
            } else {
                if (map == null) {
                    map = new HashMap();
                }
                k3Var.t(iLogger, map, strNextName2);
            }
        }
        gVar.f17809t = map;
        k3Var.endObject();
        return gVar;
    }

    public static io.sentry.rrweb.i d(k3 k3Var, ILogger iLogger) {
        k3Var.beginObject();
        io.sentry.rrweb.i iVar = new io.sentry.rrweb.i();
        HashMap map = null;
        while (k3Var.peek() == io.sentry.vendor.gson.stream.b.NAME) {
            String strNextName = k3Var.nextName();
            strNextName.getClass();
            if (strNextName.equals("data")) {
                k3Var.beginObject();
                HashMap map2 = null;
                while (k3Var.peek() == io.sentry.vendor.gson.stream.b.NAME) {
                    String strNextName2 = k3Var.nextName();
                    strNextName2.getClass();
                    if (strNextName2.equals("pointerId")) {
                        iVar.f17816n = k3Var.nextInt();
                    } else if (strNextName2.equals("positions")) {
                        iVar.f17817o = k3Var.I0(iLogger, new c0(15));
                    } else if (strNextName2.equals("source")) {
                        io.sentry.rrweb.d dVar = (io.sentry.rrweb.d) k3Var.P(iLogger, new c0(11));
                        ac.b.V(dVar, "");
                        iVar.f17802m = dVar;
                    } else {
                        if (map2 == null) {
                            map2 = new HashMap();
                        }
                        k3Var.t(iLogger, map2, strNextName2);
                    }
                }
                iVar.f17819q = map2;
                k3Var.endObject();
            } else if (strNextName.equals(LinkHeader.Parameters.Type)) {
                io.sentry.rrweb.c cVar = (io.sentry.rrweb.c) k3Var.P(iLogger, new c0(10));
                ac.b.V(cVar, "");
                iVar.f17800i = cVar;
            } else if (strNextName.equals("timestamp")) {
                iVar.f17801l = k3Var.nextLong();
            } else {
                if (map == null) {
                    map = new HashMap();
                }
                k3Var.t(iLogger, map, strNextName);
            }
        }
        iVar.f17818p = map;
        k3Var.endObject();
        return iVar;
    }

    public static io.sentry.rrweb.j e(k3 k3Var, ILogger iLogger) {
        String strNextName;
        k3Var.beginObject();
        io.sentry.rrweb.j jVar = new io.sentry.rrweb.j();
        HashMap map = null;
        while (k3Var.peek() == io.sentry.vendor.gson.stream.b.NAME) {
            String strNextName2 = k3Var.nextName();
            strNextName2.getClass();
            if (strNextName2.equals("data")) {
                k3Var.beginObject();
                AbstractMap concurrentHashMap = null;
                while (k3Var.peek() == io.sentry.vendor.gson.stream.b.NAME) {
                    strNextName = k3Var.nextName();
                    strNextName.getClass();
                    switch (strNextName) {
                        case "height":
                            Integer numF0 = k3Var.f0();
                            jVar.f17821n = numF0 != null ? numF0.intValue() : 0;
                            break;
                        case "href":
                            String strL0 = k3Var.l0();
                            if (strL0 == null) {
                                strL0 = "";
                            }
                            jVar.f17820m = strL0;
                            break;
                        case "width":
                            Integer numF02 = k3Var.f0();
                            jVar.f17822o = numF02 != null ? numF02.intValue() : 0;
                            break;
                        default:
                            if (concurrentHashMap == null) {
                                concurrentHashMap = new ConcurrentHashMap();
                            }
                            k3Var.t(iLogger, concurrentHashMap, strNextName);
                            break;
                    }
                }
                k3Var.endObject();
            } else if (strNextName2.equals(LinkHeader.Parameters.Type)) {
                io.sentry.rrweb.c cVar = (io.sentry.rrweb.c) k3Var.P(iLogger, new c0(10));
                ac.b.V(cVar, "");
                jVar.f17800i = cVar;
            } else if (strNextName2.equals("timestamp")) {
                jVar.f17801l = k3Var.nextLong();
            } else {
                if (map == null) {
                    map = new HashMap();
                }
                k3Var.t(iLogger, map, strNextName2);
            }
        }
        jVar.f17823p = map;
        k3Var.endObject();
        return jVar;
    }

    public static io.sentry.rrweb.l f(k3 k3Var, ILogger iLogger) {
        String strNextName;
        k3Var.beginObject();
        io.sentry.rrweb.l lVar = new io.sentry.rrweb.l();
        HashMap map = null;
        while (k3Var.peek() == io.sentry.vendor.gson.stream.b.NAME) {
            String strNextName2 = k3Var.nextName();
            strNextName2.getClass();
            if (strNextName2.equals("data")) {
                k3Var.beginObject();
                ConcurrentHashMap concurrentHashMap = null;
                while (k3Var.peek() == io.sentry.vendor.gson.stream.b.NAME) {
                    String strNextName3 = k3Var.nextName();
                    strNextName3.getClass();
                    if (strNextName3.equals("payload")) {
                        k3Var.beginObject();
                        ConcurrentHashMap concurrentHashMap2 = null;
                        while (k3Var.peek() == io.sentry.vendor.gson.stream.b.NAME) {
                            strNextName = k3Var.nextName();
                            strNextName.getClass();
                            switch (strNextName) {
                                case "description":
                                    lVar.f17828o = k3Var.l0();
                                    break;
                                case "endTimestamp":
                                    lVar.f17830q = k3Var.nextDouble();
                                    break;
                                case "startTimestamp":
                                    lVar.f17829p = k3Var.nextDouble();
                                    break;
                                case "op":
                                    lVar.f17827n = k3Var.l0();
                                    break;
                                case "data":
                                    ConcurrentHashMap concurrentHashMapW = k2.c.w((Map) k3Var.F0());
                                    if (concurrentHashMapW == null) {
                                        break;
                                    } else {
                                        lVar.f17831r = concurrentHashMapW;
                                        break;
                                    }
                                    break;
                                default:
                                    if (concurrentHashMap2 == null) {
                                        concurrentHashMap2 = new ConcurrentHashMap();
                                    }
                                    k3Var.t(iLogger, concurrentHashMap2, strNextName);
                                    break;
                            }
                        }
                        lVar.f17833t = concurrentHashMap2;
                        k3Var.endObject();
                    } else if (strNextName3.equals("tag")) {
                        String strL0 = k3Var.l0();
                        if (strL0 == null) {
                            strL0 = "";
                        }
                        lVar.f17826m = strL0;
                    } else {
                        if (concurrentHashMap == null) {
                            concurrentHashMap = new ConcurrentHashMap();
                        }
                        k3Var.t(iLogger, concurrentHashMap, strNextName3);
                    }
                }
                lVar.f17834u = concurrentHashMap;
                k3Var.endObject();
            } else if (strNextName2.equals(LinkHeader.Parameters.Type)) {
                io.sentry.rrweb.c cVar = (io.sentry.rrweb.c) k3Var.P(iLogger, new c0(10));
                ac.b.V(cVar, "");
                lVar.f17800i = cVar;
            } else if (strNextName2.equals("timestamp")) {
                lVar.f17801l = k3Var.nextLong();
            } else {
                if (map == null) {
                    map = new HashMap();
                }
                k3Var.t(iLogger, map, strNextName2);
            }
        }
        lVar.f17832s = map;
        k3Var.endObject();
        return lVar;
    }

    public static io.sentry.rrweb.m g(k3 k3Var, ILogger iLogger) {
        String strNextName;
        k3Var.beginObject();
        io.sentry.rrweb.m mVar = new io.sentry.rrweb.m();
        HashMap map = null;
        while (k3Var.peek() == io.sentry.vendor.gson.stream.b.NAME) {
            String strNextName2 = k3Var.nextName();
            strNextName2.getClass();
            int i10 = 10;
            if (strNextName2.equals("data")) {
                k3Var.beginObject();
                ConcurrentHashMap concurrentHashMap = null;
                while (k3Var.peek() == io.sentry.vendor.gson.stream.b.NAME) {
                    String strNextName3 = k3Var.nextName();
                    strNextName3.getClass();
                    if (strNextName3.equals("payload")) {
                        k3Var.beginObject();
                        ConcurrentHashMap concurrentHashMap2 = null;
                        while (k3Var.peek() == io.sentry.vendor.gson.stream.b.NAME) {
                            strNextName = k3Var.nextName();
                            strNextName.getClass();
                            switch (strNextName) {
                                case "duration":
                                    mVar.f17838p = k3Var.nextLong();
                                    break;
                                case "segmentId":
                                    mVar.f17836n = k3Var.nextInt();
                                    break;
                                case "height":
                                    Integer numF0 = k3Var.f0();
                                    mVar.f17841s = numF0 != null ? numF0.intValue() : 0;
                                    break;
                                case "container":
                                    String strL0 = k3Var.l0();
                                    if (strL0 == null) {
                                        strL0 = "";
                                    }
                                    mVar.f17840r = strL0;
                                    break;
                                case "frameCount":
                                    Integer numF02 = k3Var.f0();
                                    mVar.f17843u = numF02 != null ? numF02.intValue() : 0;
                                    break;
                                case "top":
                                    Integer numF03 = k3Var.f0();
                                    mVar.f17846y = numF03 != null ? numF03.intValue() : 0;
                                    break;
                                case "left":
                                    Integer numF04 = k3Var.f0();
                                    mVar.x = numF04 != null ? numF04.intValue() : 0;
                                    break;
                                case "size":
                                    Long lH0 = k3Var.h0();
                                    mVar.f17837o = lH0 == null ? 0L : lH0.longValue();
                                    break;
                                case "width":
                                    Integer numF05 = k3Var.f0();
                                    mVar.f17842t = numF05 != null ? numF05.intValue() : 0;
                                    break;
                                case "frameRate":
                                    Integer numF06 = k3Var.f0();
                                    mVar.f17845w = numF06 != null ? numF06.intValue() : 0;
                                    break;
                                case "encoding":
                                    String strL02 = k3Var.l0();
                                    if (strL02 == null) {
                                        strL02 = "";
                                    }
                                    mVar.f17839q = strL02;
                                    break;
                                case "frameRateType":
                                    String strL03 = k3Var.l0();
                                    if (strL03 == null) {
                                        strL03 = "";
                                    }
                                    mVar.f17844v = strL03;
                                    break;
                                default:
                                    if (concurrentHashMap2 == null) {
                                        concurrentHashMap2 = new ConcurrentHashMap();
                                    }
                                    k3Var.t(iLogger, concurrentHashMap2, strNextName);
                                    break;
                            }
                        }
                        mVar.A = concurrentHashMap2;
                        k3Var.endObject();
                    } else if (strNextName3.equals("tag")) {
                        String strL04 = k3Var.l0();
                        if (strL04 == null) {
                            strL04 = "";
                        }
                        mVar.f17835m = strL04;
                    } else {
                        if (concurrentHashMap == null) {
                            concurrentHashMap = new ConcurrentHashMap();
                        }
                        k3Var.t(iLogger, concurrentHashMap, strNextName3);
                    }
                }
                mVar.B = concurrentHashMap;
                k3Var.endObject();
            } else if (strNextName2.equals(LinkHeader.Parameters.Type)) {
                io.sentry.rrweb.c cVar = (io.sentry.rrweb.c) k3Var.P(iLogger, new c0(i10));
                ac.b.V(cVar, "");
                mVar.f17800i = cVar;
            } else if (strNextName2.equals("timestamp")) {
                mVar.f17801l = k3Var.nextLong();
            } else {
                if (map == null) {
                    map = new HashMap();
                }
                k3Var.t(iLogger, map, strNextName2);
            }
        }
        mVar.z = map;
        k3Var.endObject();
        return mVar;
    }

    @Override // io.sentry.u1
    public final Object a(k3 k3Var, ILogger iLogger) {
        String strNextName;
        String strNextName2;
        String strNextName3;
        String strNextName4;
        String strNextName5;
        String strNextName6;
        String strNextName7;
        String strNextName8;
        int i10 = 7;
        int i11 = 8;
        int i12 = 4;
        int i13 = 6;
        switch (this.f17552a) {
            case 0:
                d0 d0Var = new d0();
                k3Var.beginObject();
                ConcurrentHashMap concurrentHashMap = null;
                while (k3Var.peek() == io.sentry.vendor.gson.stream.b.NAME) {
                    strNextName = k3Var.nextName();
                    strNextName.getClass();
                    switch (strNextName) {
                        case "daemon":
                            d0Var.f17562q = k3Var.L();
                            break;
                        case "priority":
                            d0Var.f17557l = k3Var.f0();
                            break;
                        case "held_locks":
                            HashMap mapM0 = k3Var.m0(iLogger, new io.sentry.e(12));
                            if (mapM0 != null) {
                                d0Var.f17565t = new HashMap(mapM0);
                                break;
                            } else {
                                break;
                            }
                            break;
                        case "id":
                            d0Var.f17556i = k3Var.h0();
                            break;
                        case "main":
                            d0Var.f17563r = k3Var.L();
                            break;
                        case "name":
                            d0Var.f17558m = k3Var.l0();
                            break;
                        case "state":
                            d0Var.f17559n = k3Var.l0();
                            break;
                        case "crashed":
                            d0Var.f17560o = k3Var.L();
                            break;
                        case "current":
                            d0Var.f17561p = k3Var.L();
                            break;
                        case "stacktrace":
                            d0Var.f17564s = (b0) k3Var.P(iLogger, new io.sentry.clientreport.a(28));
                            break;
                        default:
                            if (concurrentHashMap == null) {
                                concurrentHashMap = new ConcurrentHashMap();
                            }
                            k3Var.t(iLogger, concurrentHashMap, strNextName);
                            break;
                    }
                }
                d0Var.f17566u = concurrentHashMap;
                k3Var.endObject();
                return d0Var;
            case 1:
                k3Var.beginObject();
                e0 e0Var = new e0(new ArrayList(), new HashMap(), new g0(h0.CUSTOM.apiName()));
                ConcurrentHashMap concurrentHashMap2 = null;
                while (k3Var.peek() == io.sentry.vendor.gson.stream.b.NAME) {
                    strNextName2 = k3Var.nextName();
                    strNextName2.getClass();
                    switch (strNextName2) {
                        case "start_timestamp":
                            try {
                                Double dF = k3Var.F();
                                if (dF != null) {
                                    e0Var.A = dF;
                                }
                                break;
                            } catch (NumberFormatException unused) {
                                if (k3Var.I(iLogger) != null) {
                                    e0Var.A = Double.valueOf(r8.getTime() / 1000.0d);
                                }
                                break;
                            }
                            break;
                        case "measurements":
                            HashMap mapM02 = k3Var.m0(iLogger, new io.sentry.clientreport.a(15));
                            if (mapM02 != null) {
                                e0Var.D.putAll(mapM02);
                                break;
                            } else {
                                break;
                            }
                            break;
                        case "type":
                            k3Var.nextString();
                            break;
                        case "timestamp":
                            try {
                                Double dF2 = k3Var.F();
                                if (dF2 != null) {
                                    e0Var.B = dF2;
                                }
                                break;
                            } catch (NumberFormatException unused2) {
                                if (k3Var.I(iLogger) != null) {
                                    e0Var.B = Double.valueOf(r8.getTime() / 1000.0d);
                                }
                                break;
                            }
                            break;
                        case "spans":
                            ArrayList arrayListI0 = k3Var.I0(iLogger, new io.sentry.clientreport.a(26));
                            if (arrayListI0 != null) {
                                e0Var.C.addAll(arrayListI0);
                                break;
                            } else {
                                break;
                            }
                            break;
                        case "transaction_info":
                            k3Var.beginObject();
                            String strL0 = null;
                            ConcurrentHashMap concurrentHashMap3 = null;
                            while (k3Var.peek() == io.sentry.vendor.gson.stream.b.NAME) {
                                String strNextName9 = k3Var.nextName();
                                strNextName9.getClass();
                                if (strNextName9.equals("source")) {
                                    strL0 = k3Var.l0();
                                } else {
                                    if (concurrentHashMap3 == null) {
                                        concurrentHashMap3 = new ConcurrentHashMap();
                                    }
                                    k3Var.t(iLogger, concurrentHashMap3, strNextName9);
                                }
                            }
                            g0 g0Var = new g0(strL0);
                            g0Var.f17587l = concurrentHashMap3;
                            k3Var.endObject();
                            e0Var.E = g0Var;
                            break;
                        case "transaction":
                            e0Var.z = k3Var.l0();
                            break;
                        default:
                            if (kotlin.reflect.b0.n(e0Var, strNextName2, k3Var, iLogger)) {
                                break;
                            } else {
                                if (concurrentHashMap2 == null) {
                                    concurrentHashMap2 = new ConcurrentHashMap();
                                }
                                k3Var.t(iLogger, concurrentHashMap2, strNextName2);
                                break;
                            }
                            break;
                    }
                }
                e0Var.F = concurrentHashMap2;
                k3Var.endObject();
                return e0Var;
            case 2:
                k3Var.beginObject();
                i0 i0Var = new i0();
                ConcurrentHashMap concurrentHashMap4 = null;
                while (k3Var.peek() == io.sentry.vendor.gson.stream.b.NAME) {
                    strNextName3 = k3Var.nextName();
                    strNextName3.getClass();
                    switch (strNextName3) {
                        case "username":
                            i0Var.f17599m = k3Var.l0();
                            break;
                        case "id":
                            i0Var.f17598l = k3Var.l0();
                            break;
                        case "geo":
                            k3Var.beginObject();
                            j jVar = new j();
                            ConcurrentHashMap concurrentHashMap5 = null;
                            while (k3Var.peek() == io.sentry.vendor.gson.stream.b.NAME) {
                                strNextName4 = k3Var.nextName();
                                strNextName4.getClass();
                                switch (strNextName4) {
                                    case "region":
                                        jVar.f17607m = k3Var.l0();
                                        break;
                                    case "city":
                                        jVar.f17605i = k3Var.l0();
                                        break;
                                    case "country_code":
                                        jVar.f17606l = k3Var.l0();
                                        break;
                                    default:
                                        if (concurrentHashMap5 == null) {
                                            concurrentHashMap5 = new ConcurrentHashMap();
                                        }
                                        k3Var.t(iLogger, concurrentHashMap5, strNextName4);
                                        break;
                                }
                            }
                            jVar.f17608n = concurrentHashMap5;
                            k3Var.endObject();
                            i0Var.f17602p = jVar;
                            break;
                        case "data":
                            i0Var.f17603q = k2.c.w((Map) k3Var.F0());
                            break;
                        case "name":
                            i0Var.f17601o = k3Var.l0();
                            break;
                        case "email":
                            i0Var.f17597i = k3Var.l0();
                            break;
                        case "ip_address":
                            i0Var.f17600n = k3Var.l0();
                            break;
                        default:
                            if (concurrentHashMap4 == null) {
                                concurrentHashMap4 = new ConcurrentHashMap();
                            }
                            k3Var.t(iLogger, concurrentHashMap4, strNextName3);
                            break;
                    }
                }
                i0Var.f17604r = concurrentHashMap4;
                k3Var.endObject();
                return i0Var;
            case 3:
                k3Var.beginObject();
                String strL02 = null;
                ArrayList arrayListI02 = null;
                HashMap map = null;
                while (k3Var.peek() == io.sentry.vendor.gson.stream.b.NAME) {
                    String strNextName10 = k3Var.nextName();
                    strNextName10.getClass();
                    if (strNextName10.equals("rendering_system")) {
                        strL02 = k3Var.l0();
                    } else if (strNextName10.equals("windows")) {
                        arrayListI02 = k3Var.I0(iLogger, new c0(i12));
                    } else {
                        if (map == null) {
                            map = new HashMap();
                        }
                        k3Var.t(iLogger, map, strNextName10);
                    }
                }
                k3Var.endObject();
                j0 j0Var = new j0(strL02, arrayListI02);
                j0Var.f17611m = map;
                return j0Var;
            case 4:
                k0 k0Var = new k0();
                k3Var.beginObject();
                HashMap map2 = null;
                while (k3Var.peek() == io.sentry.vendor.gson.stream.b.NAME) {
                    strNextName5 = k3Var.nextName();
                    strNextName5.getClass();
                    switch (strNextName5) {
                        case "rendering_system":
                            k0Var.f17622i = k3Var.l0();
                            break;
                        case "identifier":
                            k0Var.f17624m = k3Var.l0();
                            break;
                        case "height":
                            k0Var.f17627p = k3Var.F();
                            break;
                        case "x":
                            k0Var.f17628q = k3Var.F();
                            break;
                        case "y":
                            k0Var.f17629r = k3Var.F();
                            break;
                        case "tag":
                            k0Var.f17625n = k3Var.l0();
                            break;
                        case "type":
                            k0Var.f17623l = k3Var.l0();
                            break;
                        case "alpha":
                            k0Var.f17631t = k3Var.F();
                            break;
                        case "width":
                            k0Var.f17626o = k3Var.F();
                            break;
                        case "children":
                            k0Var.f17632u = k3Var.I0(iLogger, this);
                            break;
                        case "visibility":
                            k0Var.f17630s = k3Var.l0();
                            break;
                        default:
                            if (map2 == null) {
                                map2 = new HashMap();
                            }
                            k3Var.t(iLogger, map2, strNextName5);
                            break;
                    }
                }
                k3Var.endObject();
                k0Var.f17633v = map2;
                return k0Var;
            case 5:
                k3Var.beginObject();
                io.sentry.protocol.profiling.a aVar = new io.sentry.protocol.profiling.a();
                ConcurrentHashMap concurrentHashMap6 = null;
                while (k3Var.peek() == io.sentry.vendor.gson.stream.b.NAME) {
                    strNextName6 = k3Var.nextName();
                    strNextName6.getClass();
                    switch (strNextName6) {
                        case "frames":
                            ArrayList arrayListI03 = k3Var.I0(iLogger, new io.sentry.clientreport.a(27));
                            if (arrayListI03 != null) {
                                aVar.f17674m = arrayListI03;
                                break;
                            } else {
                                break;
                            }
                            break;
                        case "stacks":
                            List list = (List) k3Var.P(iLogger, new c0(i13));
                            if (list != null) {
                                aVar.f17673l = list;
                                break;
                            } else {
                                break;
                            }
                            break;
                        case "samples":
                            ArrayList arrayListI04 = k3Var.I0(iLogger, new c0(i10));
                            if (arrayListI04 != null) {
                                aVar.f17672i = arrayListI04;
                                break;
                            } else {
                                break;
                            }
                            break;
                        case "thread_metadata":
                            HashMap mapM03 = k3Var.m0(iLogger, new c0(i11));
                            if (mapM03 != null) {
                                aVar.f17675n = mapM03;
                                break;
                            } else {
                                break;
                            }
                            break;
                        default:
                            if (concurrentHashMap6 == null) {
                                concurrentHashMap6 = new ConcurrentHashMap();
                            }
                            k3Var.t(iLogger, concurrentHashMap6, strNextName6);
                            break;
                    }
                }
                aVar.f17676o = concurrentHashMap6;
                k3Var.endObject();
                return aVar;
            case 6:
                ArrayList arrayList = new ArrayList();
                k3Var.beginArray();
                while (k3Var.hasNext()) {
                    ArrayList arrayList2 = new ArrayList();
                    k3Var.beginArray();
                    while (k3Var.hasNext()) {
                        arrayList2.add(Integer.valueOf(k3Var.nextInt()));
                    }
                    k3Var.endArray();
                    arrayList.add(arrayList2);
                }
                k3Var.endArray();
                return arrayList;
            case 7:
                k3Var.beginObject();
                io.sentry.protocol.profiling.b bVar = new io.sentry.protocol.profiling.b();
                HashMap map3 = null;
                while (k3Var.peek() == io.sentry.vendor.gson.stream.b.NAME) {
                    strNextName7 = k3Var.nextName();
                    strNextName7.getClass();
                    switch (strNextName7) {
                        case "thread_id":
                            bVar.f17679m = k3Var.l0();
                            break;
                        case "timestamp":
                            bVar.f17677i = k3Var.nextDouble();
                            break;
                        case "stack_id":
                            bVar.f17678l = k3Var.nextInt();
                            break;
                        default:
                            if (map3 == null) {
                                map3 = new HashMap();
                            }
                            k3Var.t(iLogger, map3, strNextName7);
                            break;
                    }
                }
                bVar.f17680n = map3;
                k3Var.endObject();
                return bVar;
            case 8:
                k3Var.beginObject();
                io.sentry.protocol.profiling.c cVar = new io.sentry.protocol.profiling.c();
                HashMap map4 = null;
                while (k3Var.peek() == io.sentry.vendor.gson.stream.b.NAME) {
                    String strNextName11 = k3Var.nextName();
                    strNextName11.getClass();
                    if (strNextName11.equals("priority")) {
                        cVar.f17682l = k3Var.nextInt();
                    } else if (strNextName11.equals(ContentDisposition.Parameters.Name)) {
                        cVar.f17681i = k3Var.l0();
                    } else {
                        if (map4 == null) {
                            map4 = new HashMap();
                        }
                        k3Var.t(iLogger, map4, strNextName11);
                    }
                }
                cVar.f17683m = map4;
                k3Var.endObject();
                return cVar;
            case 9:
                return b(k3Var, iLogger);
            case 10:
                return io.sentry.rrweb.c.values()[k3Var.nextInt()];
            case 11:
                return io.sentry.rrweb.d.values()[k3Var.nextInt()];
            case 12:
                return c(k3Var, iLogger);
            case 13:
                return io.sentry.rrweb.f.values()[k3Var.nextInt()];
            case 14:
                return d(k3Var, iLogger);
            case 15:
                k3Var.beginObject();
                io.sentry.rrweb.h hVar = new io.sentry.rrweb.h();
                HashMap map5 = null;
                while (k3Var.peek() == io.sentry.vendor.gson.stream.b.NAME) {
                    strNextName8 = k3Var.nextName();
                    strNextName8.getClass();
                    switch (strNextName8) {
                        case "x":
                            hVar.f17812l = k3Var.nextFloat();
                            break;
                        case "y":
                            hVar.f17813m = k3Var.nextFloat();
                            break;
                        case "id":
                            hVar.f17811i = k3Var.nextInt();
                            break;
                        case "timeOffset":
                            hVar.f17814n = k3Var.nextLong();
                            break;
                        default:
                            if (map5 == null) {
                                map5 = new HashMap();
                            }
                            k3Var.t(iLogger, map5, strNextName8);
                            break;
                    }
                }
                hVar.f17815o = map5;
                k3Var.endObject();
                return hVar;
            case 16:
                return e(k3Var, iLogger);
            case 17:
                return f(k3Var, iLogger);
            default:
                return g(k3Var, iLogger);
        }
    }
}
