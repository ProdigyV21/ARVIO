package io.sentry;

import androidx.media3.extractor.text.ttml.TtmlNode;
import com.google.android.gms.cast.CredentialsData;
import io.ktor.http.LinkHeader;
import j$.util.concurrent.ConcurrentHashMap;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/* JADX INFO: loaded from: classes4.dex */
public final class e implements u1 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f17228a;

    public /* synthetic */ e(int i10) {
        this.f17228a = i10;
    }

    public static l7 b(k3 k3Var, ILogger iLogger) {
        String strNextName;
        k3Var.beginObject();
        io.sentry.protocol.v vVar = null;
        o7 o7Var = null;
        String strNextString = null;
        ConcurrentHashMap concurrentHashMap = null;
        o7 o7Var2 = null;
        String strNextString2 = null;
        q7 q7Var = null;
        String strNextString3 = null;
        ConcurrentHashMap concurrentHashMapW = null;
        Map map = null;
        while (k3Var.peek() == io.sentry.vendor.gson.stream.b.NAME) {
            strNextName = k3Var.nextName();
            strNextName.getClass();
            switch (strNextName) {
                case "span_id":
                    o7Var = new o7(k3Var.nextString());
                    break;
                case "parent_span_id":
                    o7Var2 = (o7) k3Var.P(iLogger, new e(23));
                    break;
                case "description":
                    strNextString2 = k3Var.nextString();
                    break;
                case "origin":
                    strNextString3 = k3Var.nextString();
                    break;
                case "status":
                    q7Var = (q7) k3Var.P(iLogger, new e(24));
                    break;
                case "op":
                    strNextString = k3Var.nextString();
                    break;
                case "data":
                    map = (Map) k3Var.F0();
                    break;
                case "tags":
                    concurrentHashMapW = k2.c.w((Map) k3Var.F0());
                    break;
                case "trace_id":
                    vVar = new io.sentry.protocol.v(k3Var.nextString());
                    break;
                default:
                    if (concurrentHashMap == null) {
                        concurrentHashMap = new ConcurrentHashMap();
                    }
                    k3Var.t(iLogger, concurrentHashMap, strNextName);
                    break;
            }
        }
        if (vVar == null) {
            IllegalStateException illegalStateException = new IllegalStateException("Missing required field \"trace_id\"");
            iLogger.l(w5.ERROR, "Missing required field \"trace_id\"", illegalStateException);
            throw illegalStateException;
        }
        if (o7Var == null) {
            IllegalStateException illegalStateException2 = new IllegalStateException("Missing required field \"span_id\"");
            iLogger.l(w5.ERROR, "Missing required field \"span_id\"", illegalStateException2);
            throw illegalStateException2;
        }
        if (strNextString == null) {
            strNextString = "";
        }
        l7 l7Var = new l7(vVar, o7Var, strNextString, o7Var2);
        l7Var.f17440p = strNextString2;
        l7Var.f17441q = q7Var;
        l7Var.f17443s = strNextString3;
        if (concurrentHashMapW != null) {
            l7Var.f17442r = concurrentHashMapW;
        }
        if (map != null) {
            l7Var.f17444t = map;
        }
        l7Var.f17445u = concurrentHashMap;
        k3Var.endObject();
        return l7Var;
    }

    private final Object c(k3 k3Var, ILogger iLogger) {
        String strNextName;
        k3Var.beginObject();
        ConcurrentHashMap concurrentHashMap = null;
        io.sentry.protocol.v vVar = null;
        String strNextString = null;
        String strL0 = null;
        String strL02 = null;
        String strL03 = null;
        String strL04 = null;
        String strL05 = null;
        String strL06 = null;
        io.sentry.protocol.v vVar2 = null;
        String strL07 = null;
        while (k3Var.peek() == io.sentry.vendor.gson.stream.b.NAME) {
            strNextName = k3Var.nextName();
            strNextName.getClass();
            switch (strNextName) {
                case "replay_id":
                    vVar2 = new io.sentry.protocol.v(k3Var.nextString());
                    break;
                case "user_id":
                    strL03 = k3Var.l0();
                    break;
                case "environment":
                    strL02 = k3Var.l0();
                    break;
                case "sample_rand":
                    strL07 = k3Var.l0();
                    break;
                case "sample_rate":
                    strL05 = k3Var.l0();
                    break;
                case "release":
                    strL0 = k3Var.l0();
                    break;
                case "trace_id":
                    vVar = new io.sentry.protocol.v(k3Var.nextString());
                    break;
                case "sampled":
                    strL06 = k3Var.l0();
                    break;
                case "public_key":
                    strNextString = k3Var.nextString();
                    break;
                case "transaction":
                    strL04 = k3Var.l0();
                    break;
                default:
                    if (concurrentHashMap == null) {
                        concurrentHashMap = new ConcurrentHashMap();
                    }
                    k3Var.t(iLogger, concurrentHashMap, strNextName);
                    break;
            }
        }
        if (vVar == null) {
            throw e("trace_id", iLogger);
        }
        if (strNextString == null) {
            throw e("public_key", iLogger);
        }
        s7 s7Var = new s7(vVar, strNextString, strL0, strL02, strL03, strL04, strL05, strL06, vVar2, strL07);
        s7Var.f17867u = concurrentHashMap;
        k3Var.endObject();
        return s7Var;
    }

    public static IllegalStateException d(String str, ILogger iLogger) {
        String strL = a0.c.l("Missing required field \"", str, "\"");
        IllegalStateException illegalStateException = new IllegalStateException(strL);
        iLogger.l(w5.ERROR, strL, illegalStateException);
        return illegalStateException;
    }

    public static IllegalStateException e(String str, ILogger iLogger) {
        String strL = a0.c.l("Missing required field \"", str, "\"");
        IllegalStateException illegalStateException = new IllegalStateException(strL);
        iLogger.l(w5.ERROR, strL, illegalStateException);
        return illegalStateException;
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r52v0, types: [io.sentry.ILogger] */
    /* JADX WARN: Type inference failed for: r9v10 */
    /* JADX WARN: Type inference failed for: r9v11 */
    /* JADX WARN: Type inference failed for: r9v13 */
    /* JADX WARN: Type inference failed for: r9v14 */
    /* JADX WARN: Type inference failed for: r9v17 */
    /* JADX WARN: Type inference failed for: r9v18 */
    /* JADX WARN: Type inference failed for: r9v20 */
    /* JADX WARN: Type inference failed for: r9v21 */
    /* JADX WARN: Type inference failed for: r9v24 */
    /* JADX WARN: Type inference failed for: r9v25 */
    /* JADX WARN: Type inference failed for: r9v28 */
    /* JADX WARN: Type inference failed for: r9v29 */
    /* JADX WARN: Type inference failed for: r9v30 */
    /* JADX WARN: Type inference failed for: r9v34 */
    /* JADX WARN: Type inference failed for: r9v6 */
    /* JADX WARN: Type inference failed for: r9v7 */
    @Override // io.sentry.u1
    public final Object a(k3 k3Var, ILogger iLogger) {
        ?? r92;
        String strNextName;
        String strNextName2;
        String strNextName3;
        ArrayList arrayList;
        String strNextName4;
        String strNextName5;
        String strNextName6;
        int i10;
        String strNextName7;
        String strNextName8;
        String strNextName9;
        String strNextName10;
        String strNextName11;
        Integer num;
        String strNextName12;
        k3 k3Var2 = k3Var;
        int i11 = 17;
        int i12 = 11;
        switch (this.f17228a) {
            case 0:
                boolean z = true;
                char c10 = 2;
                String strL0 = null;
                k3Var2.beginObject();
                Date dateM = k2.c.m();
                ConcurrentHashMap concurrentHashMap = new ConcurrentHashMap();
                String strL02 = null;
                String strL03 = null;
                ConcurrentHashMap concurrentHashMap2 = null;
                String strL04 = null;
                w5 w5VarValueOf = null;
                Date date = dateM;
                while (k3Var2.peek() == io.sentry.vendor.gson.stream.b.NAME) {
                    String strNextName13 = k3Var2.nextName();
                    strNextName13.getClass();
                    switch (strNextName13.hashCode()) {
                        case -1008619738:
                            r92 = strNextName13.equals(TtmlNode.ATTR_TTS_ORIGIN) ? 0 : -1;
                            break;
                        case 3076010:
                            r92 = strNextName13.equals("data") ? z : -1;
                            break;
                        case 3575610:
                            r92 = strNextName13.equals(LinkHeader.Parameters.Type) ? c10 : -1;
                            break;
                        case 50511102:
                            r92 = strNextName13.equals("category") ? 3 : -1;
                            break;
                        case 55126294:
                            r92 = strNextName13.equals("timestamp") ? 4 : -1;
                            break;
                        case 102865796:
                            r92 = strNextName13.equals("level") ? 5 : -1;
                            break;
                        case 954925063:
                            r92 = strNextName13.equals("message") ? 6 : -1;
                            break;
                        default:
                            r92 = -1;
                            break;
                    }
                    switch (r92) {
                        case 0:
                            strL04 = k3Var.l0();
                            continue;
                            k3Var2 = k3Var;
                            z = true;
                            c10 = 2;
                            break;
                        case 1:
                            ConcurrentHashMap concurrentHashMapW = k2.c.w((Map) k3Var.F0());
                            if (concurrentHashMapW != null) {
                                concurrentHashMap = concurrentHashMapW;
                            } else {
                                continue;
                            }
                            k3Var2 = k3Var;
                            z = true;
                            c10 = 2;
                            break;
                        case 2:
                            strL02 = k3Var.l0();
                            continue;
                            k3Var2 = k3Var;
                            z = true;
                            c10 = 2;
                            break;
                        case 3:
                            strL03 = k3Var.l0();
                            continue;
                            k3Var2 = k3Var;
                            z = true;
                            c10 = 2;
                            break;
                        case 4:
                            Date dateI = k3Var.I(iLogger);
                            if (dateI != null) {
                                date = dateI;
                            } else {
                                continue;
                            }
                            k3Var2 = k3Var;
                            z = true;
                            c10 = 2;
                            break;
                        case 5:
                            try {
                                w5VarValueOf = w5.valueOf(k3Var2.nextString().toUpperCase(Locale.ROOT));
                            } catch (Exception e5) {
                                iLogger.k(w5.ERROR, e5, "Error when deserializing SentryLevel", new Object[0]);
                            }
                            break;
                        case 6:
                            strL0 = k3Var2.l0();
                            break;
                        default:
                            if (concurrentHashMap2 == null) {
                                concurrentHashMap2 = new ConcurrentHashMap();
                            }
                            k3Var2.t(iLogger, concurrentHashMap2, strNextName13);
                            break;
                    }
                    k3Var2 = k3Var;
                    z = true;
                    c10 = 2;
                }
                f fVar = new f(date);
                fVar.f17246n = strL0;
                fVar.f17247o = strL02;
                fVar.f17248p = concurrentHashMap;
                fVar.f17249q = strL03;
                fVar.f17250r = strL04;
                fVar.f17251s = w5VarValueOf;
                fVar.f17252t = concurrentHashMap2;
                k3Var.endObject();
                return fVar;
            case 1:
                ConcurrentHashMap concurrentHashMap3 = null;
                k3Var2.beginObject();
                io.sentry.protocol.v vVar = io.sentry.protocol.v.f17707l;
                q3 q3Var = new q3(vVar, vVar, null, new HashMap(), Double.valueOf(0.0d), CredentialsData.CREDENTIALS_TYPE_ANDROID, y6.empty());
                while (k3Var2.peek() == io.sentry.vendor.gson.stream.b.NAME) {
                    strNextName = k3Var2.nextName();
                    strNextName.getClass();
                    switch (strNextName) {
                        case "debug_meta":
                            io.sentry.protocol.d dVar = (io.sentry.protocol.d) k3Var2.P(iLogger, new io.sentry.clientreport.a(8));
                            if (dVar == null) {
                                break;
                            } else {
                                q3Var.f17748i = dVar;
                                break;
                            }
                            break;
                        case "measurements":
                            HashMap mapM0 = k3Var2.m0(iLogger, new io.sentry.clientreport.a(2));
                            if (mapM0 != null) {
                                q3Var.f17752o.putAll(mapM0);
                            }
                            break;
                        case "profile":
                            io.sentry.protocol.profiling.a aVar = (io.sentry.protocol.profiling.a) k3Var2.P(iLogger, new io.sentry.protocol.c0(5));
                            if (aVar != null) {
                                q3Var.f17760w = aVar;
                            }
                            break;
                        case "environment":
                            String strL05 = k3Var2.l0();
                            if (strL05 != null) {
                                q3Var.f17755r = strL05;
                            }
                            break;
                        case "timestamp":
                            Double dF = k3Var2.F();
                            if (dF != null) {
                                q3Var.f17757t = dF.doubleValue();
                            }
                            break;
                        case "profiler_id":
                            io.sentry.protocol.v vVar2 = (io.sentry.protocol.v) k3Var2.P(iLogger, new io.sentry.clientreport.a(23));
                            if (vVar2 != null) {
                                q3Var.f17749l = vVar2;
                            }
                            break;
                        case "version":
                            String strL06 = k3Var2.l0();
                            if (strL06 != null) {
                                q3Var.f17756s = strL06;
                            }
                            break;
                        case "release":
                            String strL07 = k3Var2.l0();
                            if (strL07 != null) {
                                q3Var.f17754q = strL07;
                            }
                            break;
                        case "client_sdk":
                            io.sentry.protocol.t tVar = (io.sentry.protocol.t) k3Var2.P(iLogger, new io.sentry.clientreport.a(21));
                            if (tVar != null) {
                                q3Var.f17751n = tVar;
                            }
                            break;
                        case "platform":
                            String strL08 = k3Var2.l0();
                            if (strL08 != null) {
                                q3Var.f17753p = strL08;
                            }
                            break;
                        case "sampled_profile":
                            String strL09 = k3Var2.l0();
                            if (strL09 != null) {
                                q3Var.f17759v = strL09;
                            }
                            break;
                        case "chunk_id":
                            io.sentry.protocol.v vVar3 = (io.sentry.protocol.v) k3Var2.P(iLogger, new io.sentry.clientreport.a(23));
                            if (vVar3 != null) {
                                q3Var.f17750m = vVar3;
                            }
                            break;
                        default:
                            if (concurrentHashMap3 == null) {
                                concurrentHashMap3 = new ConcurrentHashMap();
                            }
                            k3Var2.t(iLogger, concurrentHashMap3, strNextName);
                            break;
                    }
                }
                q3Var.x = concurrentHashMap3;
                k3Var2.endObject();
                return q3Var;
            case 2:
                ConcurrentHashMap concurrentHashMap4 = null;
                k3Var2.beginObject();
                r3 r3Var = new r3(io.sentry.protocol.v.f17707l);
                while (k3Var2.peek() == io.sentry.vendor.gson.stream.b.NAME) {
                    String strNextName14 = k3Var2.nextName();
                    strNextName14.getClass();
                    if (strNextName14.equals("profiler_id")) {
                        io.sentry.protocol.v vVar4 = (io.sentry.protocol.v) k3Var2.P(iLogger, new io.sentry.clientreport.a(23));
                        if (vVar4 != null) {
                            r3Var.f17775i = vVar4;
                        }
                    } else {
                        if (concurrentHashMap4 == null) {
                            concurrentHashMap4 = new ConcurrentHashMap();
                        }
                        k3Var2.t(iLogger, concurrentHashMap4, strNextName14);
                    }
                }
                r3Var.f17776l = concurrentHashMap4;
                k3Var2.endObject();
                return r3Var;
            case 3:
                k3Var2.beginObject();
                File file = new File("dummy");
                Date dateM2 = k2.c.m();
                ArrayList arrayList2 = new ArrayList();
                io.sentry.protocol.v vVar5 = io.sentry.protocol.v.f17707l;
                ConcurrentHashMap concurrentHashMap5 = null;
                t3 t3Var = new t3(file, dateM2, arrayList2, "", vVar5.toString(), new l7(vVar5, o7.f17504l, "op", null).f17435i.toString(), "0", 0, "", new k0(2), null, null, null, null, null, null, null, null, "normal", new HashMap());
                while (k3Var2.peek() == io.sentry.vendor.gson.stream.b.NAME) {
                    strNextName2 = k3Var2.nextName();
                    strNextName2.getClass();
                    switch (strNextName2) {
                        case "device_manufacturer":
                            String strL010 = k3Var2.l0();
                            if (strL010 == null) {
                                break;
                            } else {
                                t3Var.f17876o = strL010;
                                break;
                            }
                            break;
                        case "android_api_level":
                            Integer numF0 = k3Var2.f0();
                            if (numF0 == null) {
                                break;
                            } else {
                                t3Var.f17874m = numF0.intValue();
                                break;
                            }
                            break;
                        case "build_id":
                            String strL011 = k3Var2.l0();
                            if (strL011 == null) {
                                break;
                            } else {
                                t3Var.f17885y = strL011;
                                break;
                            }
                            break;
                        case "device_locale":
                            String strL012 = k3Var2.l0();
                            if (strL012 == null) {
                                break;
                            } else {
                                t3Var.f17875n = strL012;
                                break;
                            }
                            break;
                        case "profile_id":
                            String strL013 = k3Var2.l0();
                            if (strL013 == null) {
                                break;
                            } else {
                                t3Var.G = strL013;
                                break;
                            }
                            break;
                        case "device_os_build_number":
                            String strL014 = k3Var2.l0();
                            if (strL014 == null) {
                                break;
                            } else {
                                t3Var.f17878q = strL014;
                                break;
                            }
                            break;
                        case "device_model":
                            String strL015 = k3Var2.l0();
                            if (strL015 == null) {
                                break;
                            } else {
                                t3Var.f17877p = strL015;
                                break;
                            }
                            break;
                        case "device_is_emulator":
                            Boolean boolL = k3Var2.L();
                            if (boolL == null) {
                                break;
                            } else {
                                t3Var.f17881t = boolL.booleanValue();
                                break;
                            }
                            break;
                        case "duration_ns":
                            String strL016 = k3Var2.l0();
                            if (strL016 == null) {
                                break;
                            } else {
                                t3Var.B = strL016;
                                break;
                            }
                            break;
                        case "measurements":
                            HashMap mapM02 = k3Var2.m0(iLogger, new io.sentry.clientreport.a(2));
                            if (mapM02 == null) {
                                break;
                            } else {
                                t3Var.K.putAll(mapM02);
                                break;
                            }
                            break;
                        case "device_physical_memory_bytes":
                            String strL017 = k3Var2.l0();
                            if (strL017 == null) {
                                break;
                            } else {
                                t3Var.f17884w = strL017;
                                break;
                            }
                            break;
                        case "device_cpu_frequencies":
                            List list = (List) k3Var2.F0();
                            if (list == null) {
                                break;
                            } else {
                                t3Var.f17883v = list;
                                break;
                            }
                            break;
                        case "version_code":
                            String strL018 = k3Var2.l0();
                            if (strL018 == null) {
                                break;
                            } else {
                                t3Var.C = strL018;
                                break;
                            }
                            break;
                        case "version_name":
                            String strL019 = k3Var2.l0();
                            if (strL019 == null) {
                                break;
                            } else {
                                t3Var.D = strL019;
                                break;
                            }
                            break;
                        case "environment":
                            String strL020 = k3Var2.l0();
                            if (strL020 == null) {
                                break;
                            } else {
                                t3Var.H = strL020;
                                break;
                            }
                            break;
                        case "timestamp":
                            Date dateI2 = k3Var.I(iLogger);
                            if (dateI2 == null) {
                                break;
                            } else {
                                t3Var.J = dateI2;
                                break;
                            }
                            break;
                        case "transaction_name":
                            String strL021 = k3Var2.l0();
                            if (strL021 == null) {
                                break;
                            } else {
                                t3Var.A = strL021;
                                break;
                            }
                            break;
                        case "device_os_name":
                            String strL022 = k3Var2.l0();
                            if (strL022 == null) {
                                break;
                            } else {
                                t3Var.f17879r = strL022;
                                break;
                            }
                            break;
                        case "architecture":
                            String strL023 = k3Var2.l0();
                            if (strL023 == null) {
                                break;
                            } else {
                                t3Var.f17882u = strL023;
                                break;
                            }
                            break;
                        case "transaction_id":
                            String strL024 = k3Var2.l0();
                            if (strL024 == null) {
                                break;
                            } else {
                                t3Var.E = strL024;
                                break;
                            }
                            break;
                        case "device_os_version":
                            String strL025 = k3Var2.l0();
                            if (strL025 == null) {
                                break;
                            } else {
                                t3Var.f17880s = strL025;
                                break;
                            }
                            break;
                        case "truncation_reason":
                            String strL026 = k3Var2.l0();
                            if (strL026 == null) {
                                break;
                            } else {
                                t3Var.I = strL026;
                                break;
                            }
                            break;
                        case "trace_id":
                            String strL027 = k3Var2.l0();
                            if (strL027 == null) {
                                break;
                            } else {
                                t3Var.F = strL027;
                                break;
                            }
                            break;
                        case "platform":
                            String strL028 = k3Var2.l0();
                            if (strL028 == null) {
                                break;
                            } else {
                                t3Var.x = strL028;
                                break;
                            }
                            break;
                        case "sampled_profile":
                            String strL029 = k3Var2.l0();
                            if (strL029 == null) {
                                break;
                            } else {
                                t3Var.L = strL029;
                                break;
                            }
                            break;
                        case "transactions":
                            ArrayList arrayListI0 = k3Var2.I0(iLogger, new e(4));
                            if (arrayListI0 == null) {
                                break;
                            } else {
                                t3Var.z.addAll(arrayListI0);
                                break;
                            }
                            break;
                        default:
                            if (concurrentHashMap5 == null) {
                                concurrentHashMap5 = new ConcurrentHashMap();
                            }
                            k3Var2.t(iLogger, concurrentHashMap5, strNextName2);
                            break;
                    }
                }
                t3Var.M = concurrentHashMap5;
                k3Var2.endObject();
                return t3Var;
            case 4:
                k3Var2.beginObject();
                u3 u3Var = new u3(h3.f17316a, 0L, 0L);
                ConcurrentHashMap concurrentHashMap6 = null;
                while (k3Var2.peek() == io.sentry.vendor.gson.stream.b.NAME) {
                    strNextName3 = k3Var2.nextName();
                    strNextName3.getClass();
                    switch (strNextName3) {
                        case "relative_start_ns":
                            Long lH0 = k3Var2.h0();
                            if (lH0 == null) {
                                break;
                            } else {
                                u3Var.f17946n = lH0;
                                break;
                            }
                            break;
                        case "relative_end_ns":
                            Long lH02 = k3Var2.h0();
                            if (lH02 == null) {
                                break;
                            } else {
                                u3Var.f17947o = lH02;
                                break;
                            }
                            break;
                        case "id":
                            String strL030 = k3Var2.l0();
                            if (strL030 == null) {
                                break;
                            } else {
                                u3Var.f17943i = strL030;
                                break;
                            }
                            break;
                        case "name":
                            String strL031 = k3Var2.l0();
                            if (strL031 == null) {
                                break;
                            } else {
                                u3Var.f17945m = strL031;
                                break;
                            }
                            break;
                        case "trace_id":
                            String strL032 = k3Var2.l0();
                            if (strL032 == null) {
                                break;
                            } else {
                                u3Var.f17944l = strL032;
                                break;
                            }
                            break;
                        case "relative_cpu_end_ms":
                            Long lH03 = k3Var2.h0();
                            if (lH03 == null) {
                                break;
                            } else {
                                u3Var.f17949q = lH03;
                                break;
                            }
                            break;
                        case "relative_cpu_start_ms":
                            Long lH04 = k3Var2.h0();
                            if (lH04 == null) {
                                break;
                            } else {
                                u3Var.f17948p = lH04;
                                break;
                            }
                            break;
                        default:
                            if (concurrentHashMap6 == null) {
                                concurrentHashMap6 = new ConcurrentHashMap();
                            }
                            k3Var2.t(iLogger, concurrentHashMap6, strNextName3);
                            break;
                    }
                }
                u3Var.f17950r = concurrentHashMap6;
                k3Var2.endObject();
                return u3Var;
            case 5:
                y3 y3Var = new y3();
                k3Var2.beginObject();
                Integer numF02 = null;
                HashMap map = null;
                while (k3Var2.peek() == io.sentry.vendor.gson.stream.b.NAME) {
                    String strNextName15 = k3Var2.nextName();
                    strNextName15.getClass();
                    if (strNextName15.equals("segment_id")) {
                        numF02 = k3Var2.f0();
                    } else {
                        if (map == null) {
                            map = new HashMap();
                        }
                        k3Var2.t(iLogger, map, strNextName15);
                    }
                }
                k3Var2.endObject();
                k3Var2.setLenient(true);
                List list2 = (List) k3Var2.F0();
                k3Var2.setLenient(false);
                if (list2 != null) {
                    arrayList = new ArrayList(list2.size());
                    for (Object obj : list2) {
                        if (obj instanceof Map) {
                            Map map2 = (Map) obj;
                            io.sentry.util.m mVar = new io.sentry.util.m(map2);
                            for (Map.Entry entry : map2.entrySet()) {
                                String str = (String) entry.getKey();
                                Object value = entry.getValue();
                                if (str.equals(LinkHeader.Parameters.Type)) {
                                    io.sentry.rrweb.c cVar = io.sentry.rrweb.c.values()[((Integer) value).intValue()];
                                    int i13 = x3.f18053b[cVar.ordinal()];
                                    if (i13 == 1) {
                                        Map map3 = (Map) map2.get("data");
                                        if (map3 == null) {
                                            map3 = Collections.EMPTY_MAP;
                                        }
                                        Integer num2 = (Integer) map3.get("source");
                                        if (num2 != null) {
                                            io.sentry.rrweb.d dVar2 = io.sentry.rrweb.d.values()[num2.intValue()];
                                            int i14 = x3.f18052a[dVar2.ordinal()];
                                            if (i14 == 1) {
                                                arrayList.add(io.sentry.protocol.c0.c(mVar, iLogger));
                                            } else if (i14 != 2) {
                                                iLogger.q(w5.DEBUG, "Unsupported rrweb incremental snapshot type %s", dVar2);
                                            } else {
                                                arrayList.add(io.sentry.protocol.c0.d(mVar, iLogger));
                                            }
                                        }
                                    } else if (i13 == 2) {
                                        arrayList.add(io.sentry.protocol.c0.e(mVar, iLogger));
                                    } else if (i13 == 3) {
                                        Map map4 = (Map) map2.get("data");
                                        if (map4 == null) {
                                            map4 = Collections.EMPTY_MAP;
                                        }
                                        String str2 = (String) map4.get("tag");
                                        if (str2 != null) {
                                            switch (str2) {
                                                case "performanceSpan":
                                                    arrayList.add(io.sentry.protocol.c0.f(mVar, iLogger));
                                                    break;
                                                case "video":
                                                    arrayList.add(io.sentry.protocol.c0.g(mVar, iLogger));
                                                    break;
                                                case "breadcrumb":
                                                    arrayList.add(io.sentry.protocol.c0.b(mVar, iLogger));
                                                    break;
                                                default:
                                                    iLogger.q(w5.DEBUG, "Unsupported rrweb event type %s", cVar);
                                                    break;
                                            }
                                        }
                                    } else {
                                        iLogger.q(w5.DEBUG, "Unsupported rrweb event type %s", cVar);
                                    }
                                }
                            }
                        }
                    }
                } else {
                    arrayList = null;
                }
                y3Var.f18063i = numF02;
                y3Var.f18064l = arrayList;
                y3Var.f18065m = map;
                return y3Var;
            case 6:
                k3Var2.beginObject();
                t4 t4Var = new t4();
                t4Var.f17888m = false;
                t4Var.f17889n = null;
                t4Var.f17886i = false;
                t4Var.f17887l = null;
                t4Var.f17894s = false;
                t4Var.f17890o = null;
                t4Var.f17891p = false;
                t4Var.f17892q = false;
                t4Var.f17897v = s3.MANUAL;
                t4Var.f17893r = 0;
                t4Var.f17895t = true;
                t4Var.f17896u = false;
                ConcurrentHashMap concurrentHashMap7 = null;
                while (k3Var2.peek() == io.sentry.vendor.gson.stream.b.NAME) {
                    strNextName4 = k3Var2.nextName();
                    strNextName4.getClass();
                    switch (strNextName4) {
                        case "is_enable_app_start_profiling":
                            Boolean boolL2 = k3Var2.L();
                            if (boolL2 == null) {
                                break;
                            } else {
                                t4Var.f17895t = boolL2.booleanValue();
                                break;
                            }
                            break;
                        case "trace_sampled":
                            Boolean boolL3 = k3Var2.L();
                            if (boolL3 == null) {
                                break;
                            } else {
                                t4Var.f17888m = boolL3.booleanValue();
                                break;
                            }
                            break;
                        case "profiling_traces_dir_path":
                            String strL033 = k3Var2.l0();
                            if (strL033 == null) {
                                break;
                            } else {
                                t4Var.f17890o = strL033;
                                break;
                            }
                            break;
                        case "is_continuous_profiling_enabled":
                            Boolean boolL4 = k3Var2.L();
                            if (boolL4 == null) {
                                break;
                            } else {
                                t4Var.f17892q = boolL4.booleanValue();
                                break;
                            }
                            break;
                        case "is_profiling_enabled":
                            Boolean boolL5 = k3Var2.L();
                            if (boolL5 == null) {
                                break;
                            } else {
                                t4Var.f17891p = boolL5.booleanValue();
                                break;
                            }
                            break;
                        case "is_start_profiler_on_app_start":
                            Boolean boolL6 = k3Var2.L();
                            if (boolL6 == null) {
                                break;
                            } else {
                                t4Var.f17896u = boolL6.booleanValue();
                                break;
                            }
                            break;
                        case "profile_sampled":
                            Boolean boolL7 = k3Var2.L();
                            if (boolL7 == null) {
                                break;
                            } else {
                                t4Var.f17886i = boolL7.booleanValue();
                                break;
                            }
                            break;
                        case "profile_lifecycle":
                            String strL034 = k3Var2.l0();
                            if (strL034 == null) {
                                break;
                            } else {
                                try {
                                    t4Var.f17897v = s3.valueOf(strL034);
                                } catch (IllegalArgumentException unused) {
                                    iLogger.q(w5.ERROR, "Error when deserializing ProfileLifecycle: ".concat(strL034), new Object[0]);
                                }
                                break;
                            }
                            break;
                        case "continuous_profile_sampled":
                            Boolean boolL8 = k3Var2.L();
                            if (boolL8 == null) {
                                break;
                            } else {
                                t4Var.f17894s = boolL8.booleanValue();
                                break;
                            }
                            break;
                        case "profiling_traces_hz":
                            Integer numF03 = k3Var2.f0();
                            if (numF03 == null) {
                                break;
                            } else {
                                t4Var.f17893r = numF03.intValue();
                                break;
                            }
                            break;
                        case "trace_sample_rate":
                            Double dF2 = k3Var2.F();
                            if (dF2 == null) {
                                break;
                            } else {
                                t4Var.f17889n = dF2;
                                break;
                            }
                            break;
                        case "profile_sample_rate":
                            Double dF3 = k3Var2.F();
                            if (dF3 == null) {
                                break;
                            } else {
                                t4Var.f17887l = dF3;
                                break;
                            }
                            break;
                        default:
                            if (concurrentHashMap7 == null) {
                                concurrentHashMap7 = new ConcurrentHashMap();
                            }
                            k3Var2.t(iLogger, concurrentHashMap7, strNextName4);
                            break;
                    }
                }
                t4Var.f17898w = concurrentHashMap7;
                k3Var2.endObject();
                return t4Var;
            case 7:
                k3Var2.beginObject();
                io.sentry.protocol.t tVar2 = null;
                s7 s7Var = null;
                Date dateI3 = null;
                HashMap map5 = null;
                io.sentry.protocol.v vVar6 = null;
                while (k3Var2.peek() == io.sentry.vendor.gson.stream.b.NAME) {
                    strNextName5 = k3Var2.nextName();
                    strNextName5.getClass();
                    switch (strNextName5) {
                        case "sdk":
                            tVar2 = (io.sentry.protocol.t) k3Var2.P(iLogger, new io.sentry.clientreport.a(21));
                            break;
                        case "trace":
                            s7Var = (s7) k3Var2.P(iLogger, new e(25));
                            break;
                        case "event_id":
                            vVar6 = (io.sentry.protocol.v) k3Var2.P(iLogger, new io.sentry.clientreport.a(23));
                            break;
                        case "sent_at":
                            dateI3 = k3Var.I(iLogger);
                            break;
                        default:
                            if (map5 == null) {
                                map5 = new HashMap();
                            }
                            k3Var2.t(iLogger, map5, strNextName5);
                            break;
                    }
                }
                b5 b5Var = new b5(vVar6, tVar2, s7Var);
                b5Var.f17103n = dateI3;
                b5Var.f17104o = map5;
                k3Var2.endObject();
                return b5Var;
            case 8:
                k3Var2.beginObject();
                HashMap map6 = null;
                v5 v5Var = null;
                int iNextInt = 0;
                String strL035 = null;
                String strL036 = null;
                String strL037 = null;
                String strL038 = null;
                Integer numF04 = null;
                while (k3Var2.peek() == io.sentry.vendor.gson.stream.b.NAME) {
                    strNextName6 = k3Var2.nextName();
                    strNextName6.getClass();
                    switch (strNextName6) {
                        case "item_count":
                            numF04 = k3Var2.f0();
                            continue;
                            break;
                        case "length":
                            iNextInt = k3Var2.nextInt();
                            continue;
                            break;
                        case "filename":
                            strL036 = k3Var2.l0();
                            continue;
                            break;
                        case "attachment_type":
                            strL037 = k3Var2.l0();
                            continue;
                            break;
                        case "type":
                            v5Var = (v5) k3Var2.P(iLogger, new e(10));
                            continue;
                            break;
                        case "content_type":
                            strL035 = k3Var2.l0();
                            break;
                        case "platform":
                            strL038 = k3Var2.l0();
                            break;
                        default:
                            if (map6 == null) {
                                map6 = new HashMap();
                            }
                            k3Var2.t(iLogger, map6, strNextName6);
                            break;
                    }
                }
                if (v5Var == null) {
                    IllegalStateException illegalStateException = new IllegalStateException("Missing required field \"type\"");
                    iLogger.l(w5.ERROR, "Missing required field \"type\"", illegalStateException);
                    throw illegalStateException;
                }
                i5 i5Var = new i5(v5Var, iNextInt, strL035, strL036, strL037, strL038, numF04);
                i5Var.f17360s = map6;
                k3Var2.endObject();
                return i5Var;
            case 9:
                int i15 = 0;
                k3Var2.beginObject();
                j5 j5Var = new j5();
                ConcurrentHashMap concurrentHashMap8 = null;
                while (k3Var2.peek() == io.sentry.vendor.gson.stream.b.NAME) {
                    String strNextName16 = k3Var2.nextName();
                    strNextName16.getClass();
                    switch (strNextName16.hashCode()) {
                        case -1375934236:
                            i10 = strNextName16.equals("fingerprint") ? i15 : -1;
                            break;
                        case -1337936983:
                            i10 = strNextName16.equals("threads") ? 1 : -1;
                            break;
                        case -1097337456:
                            i10 = strNextName16.equals("logger") ? 2 : -1;
                            break;
                        case 55126294:
                            i10 = strNextName16.equals("timestamp") ? 3 : -1;
                            break;
                        case 102865796:
                            i10 = strNextName16.equals("level") ? 4 : -1;
                            break;
                        case 954925063:
                            i10 = strNextName16.equals("message") ? 5 : -1;
                            break;
                        case 1227433863:
                            i10 = strNextName16.equals("modules") ? 6 : -1;
                            break;
                        case 1481625679:
                            i10 = strNextName16.equals("exception") ? 7 : -1;
                            break;
                        case 2141246174:
                            i10 = strNextName16.equals("transaction") ? 8 : -1;
                            break;
                        default:
                            i10 = -1;
                            break;
                    }
                    switch (i10) {
                        case 0:
                            List list3 = (List) k3Var2.F0();
                            if (list3 != null) {
                                j5Var.G = list3;
                            }
                            break;
                        case 1:
                            k3Var2.beginObject();
                            k3Var2.nextName();
                            j5Var.C = new f2(k3Var2.I0(iLogger, new io.sentry.protocol.c0(i15)));
                            k3Var2.endObject();
                            break;
                        case 2:
                            j5Var.B = k3Var2.l0();
                            break;
                        case 3:
                            Date dateI4 = k3Var.I(iLogger);
                            if (dateI4 != null) {
                                j5Var.z = dateI4;
                            }
                            break;
                        case 4:
                            j5Var.E = (w5) k3Var2.P(iLogger, new e(i12));
                            break;
                        case 5:
                            j5Var.A = (io.sentry.protocol.n) k3Var2.P(iLogger, new io.sentry.clientreport.a(i11));
                            break;
                        case 6:
                            j5Var.I = k2.c.w((Map) k3Var2.F0());
                            break;
                        case 7:
                            k3Var2.beginObject();
                            k3Var2.nextName();
                            j5Var.D = new f2(k3Var2.I0(iLogger, new io.sentry.clientreport.a(22)));
                            k3Var2.endObject();
                            break;
                        case 8:
                            j5Var.F = k3Var2.l0();
                            break;
                        default:
                            if (!kotlin.reflect.b0.n(j5Var, strNextName16, k3Var2, iLogger)) {
                                if (concurrentHashMap8 == null) {
                                    concurrentHashMap8 = new ConcurrentHashMap();
                                }
                                k3Var2.t(iLogger, concurrentHashMap8, strNextName16);
                            }
                            break;
                    }
                    i15 = 0;
                }
                j5Var.H = concurrentHashMap8;
                k3Var2.endObject();
                return j5Var;
            case 10:
                return v5.valueOfLabel(k3Var2.nextString().toLowerCase(Locale.ROOT));
            case 11:
                return w5.valueOf(k3Var2.nextString().toUpperCase(Locale.ROOT));
            case 12:
                x5 x5Var = new x5();
                k3Var2.beginObject();
                ConcurrentHashMap concurrentHashMap9 = null;
                while (k3Var2.peek() == io.sentry.vendor.gson.stream.b.NAME) {
                    strNextName7 = k3Var2.nextName();
                    strNextName7.getClass();
                    switch (strNextName7) {
                        case "package_name":
                            x5Var.f18056m = k3Var2.l0();
                            break;
                        case "thread_id":
                            x5Var.f18058o = k3Var2.h0();
                            break;
                        case "address":
                            x5Var.f18055l = k3Var2.l0();
                            break;
                        case "class_name":
                            x5Var.f18057n = k3Var2.l0();
                            break;
                        case "type":
                            x5Var.f18054i = k3Var2.nextInt();
                            break;
                        default:
                            if (concurrentHashMap9 == null) {
                                concurrentHashMap9 = new ConcurrentHashMap();
                            }
                            k3Var2.t(iLogger, concurrentHashMap9, strNextName7);
                            break;
                    }
                }
                x5Var.f18059p = concurrentHashMap9;
                k3Var2.endObject();
                return x5Var;
            case 13:
                k3Var2.beginObject();
                Double dF4 = null;
                String strL039 = null;
                HashMap map7 = null;
                a6 a6Var = null;
                HashMap mapM03 = null;
                Integer numF05 = null;
                o7 o7Var = null;
                io.sentry.protocol.v vVar7 = null;
                while (k3Var2.peek() == io.sentry.vendor.gson.stream.b.NAME) {
                    strNextName8 = k3Var2.nextName();
                    strNextName8.getClass();
                    switch (strNextName8) {
                        case "span_id":
                            o7Var = (o7) k3Var2.P(iLogger, new e(23));
                            break;
                        case "severity_number":
                            numF05 = k3Var2.f0();
                            break;
                        case "body":
                            strL039 = k3Var2.l0();
                            break;
                        case "timestamp":
                            dF4 = k3Var2.F();
                            break;
                        case "level":
                            a6Var = (a6) k3Var2.P(iLogger, new e(16));
                            break;
                        case "attributes":
                            mapM03 = k3Var2.m0(iLogger, new e(14));
                            break;
                        case "trace_id":
                            vVar7 = (io.sentry.protocol.v) k3Var2.P(iLogger, new io.sentry.clientreport.a(23));
                            break;
                        default:
                            if (map7 == null) {
                                map7 = new HashMap();
                            }
                            k3Var2.t(iLogger, map7, strNextName8);
                            break;
                    }
                }
                k3Var2.endObject();
                if (vVar7 == null) {
                    IllegalStateException illegalStateException2 = new IllegalStateException("Missing required field \"trace_id\"");
                    iLogger.l(w5.ERROR, "Missing required field \"trace_id\"", illegalStateException2);
                    throw illegalStateException2;
                }
                if (dF4 == null) {
                    IllegalStateException illegalStateException3 = new IllegalStateException("Missing required field \"timestamp\"");
                    iLogger.l(w5.ERROR, "Missing required field \"timestamp\"", illegalStateException3);
                    throw illegalStateException3;
                }
                if (strL039 == null) {
                    IllegalStateException illegalStateException4 = new IllegalStateException("Missing required field \"body\"");
                    iLogger.l(w5.ERROR, "Missing required field \"body\"", illegalStateException4);
                    throw illegalStateException4;
                }
                if (a6Var == null) {
                    IllegalStateException illegalStateException5 = new IllegalStateException("Missing required field \"level\"");
                    iLogger.l(w5.ERROR, "Missing required field \"level\"", illegalStateException5);
                    throw illegalStateException5;
                }
                y5 y5Var = new y5();
                y5Var.f18069i = vVar7;
                y5Var.f18071m = dF4;
                y5Var.f18072n = strL039;
                y5Var.f18073o = a6Var;
                y5Var.f18075q = mapM03;
                y5Var.f18074p = numF05;
                y5Var.f18070l = o7Var;
                y5Var.f18076r = map7;
                return y5Var;
            case 14:
                k3Var2.beginObject();
                Object objF0 = null;
                HashMap map8 = null;
                String strL040 = null;
                while (k3Var2.peek() == io.sentry.vendor.gson.stream.b.NAME) {
                    String strNextName17 = k3Var2.nextName();
                    strNextName17.getClass();
                    if (strNextName17.equals(LinkHeader.Parameters.Type)) {
                        strL040 = k3Var2.l0();
                    } else if (strNextName17.equals("value")) {
                        objF0 = k3Var2.F0();
                    } else {
                        if (map8 == null) {
                            map8 = new HashMap();
                        }
                        k3Var2.t(iLogger, map8, strNextName17);
                    }
                }
                k3Var2.endObject();
                if (strL040 == null) {
                    IllegalStateException illegalStateException6 = new IllegalStateException("Missing required field \"type\"");
                    iLogger.l(w5.ERROR, "Missing required field \"type\"", illegalStateException6);
                    throw illegalStateException6;
                }
                io.sentry.protocol.l lVar = new io.sentry.protocol.l();
                lVar.f17635l = strL040;
                if (objF0 == null || !strL040.equals("string")) {
                    lVar.f17636m = objF0;
                } else {
                    lVar.f17636m = objF0.toString();
                }
                lVar.f17637n = map8;
                return lVar;
            case 15:
                k3Var2.beginObject();
                HashMap map9 = null;
                ArrayList arrayListI02 = null;
                while (k3Var2.peek() == io.sentry.vendor.gson.stream.b.NAME) {
                    String strNextName18 = k3Var2.nextName();
                    strNextName18.getClass();
                    if (strNextName18.equals("items")) {
                        arrayListI02 = k3Var2.I0(iLogger, new e(13));
                    } else {
                        if (map9 == null) {
                            map9 = new HashMap();
                        }
                        k3Var2.t(iLogger, map9, strNextName18);
                    }
                }
                k3Var2.endObject();
                if (arrayListI02 != null) {
                    z5 z5Var = new z5(arrayListI02);
                    z5Var.f18080l = map9;
                    return z5Var;
                }
                IllegalStateException illegalStateException7 = new IllegalStateException("Missing required field \"items\"");
                iLogger.l(w5.ERROR, "Missing required field \"items\"", illegalStateException7);
                throw illegalStateException7;
            case 16:
                return a6.valueOf(k3Var2.nextString().toUpperCase(Locale.ROOT));
            case 17:
                k3Var2.beginObject();
                Double dF5 = null;
                String strL041 = null;
                HashMap map10 = null;
                String strL042 = null;
                Double dF6 = null;
                HashMap mapM04 = null;
                o7 o7Var2 = null;
                String strL043 = null;
                io.sentry.protocol.v vVar8 = null;
                while (true) {
                    HashMap map11 = map10;
                    if (k3Var2.peek() != io.sentry.vendor.gson.stream.b.NAME) {
                        k3Var2.endObject();
                        if (vVar8 == null) {
                            IllegalStateException illegalStateException8 = new IllegalStateException("Missing required field \"trace_id\"");
                            iLogger.l(w5.ERROR, "Missing required field \"trace_id\"", illegalStateException8);
                            throw illegalStateException8;
                        }
                        if (dF5 == null) {
                            IllegalStateException illegalStateException9 = new IllegalStateException("Missing required field \"timestamp\"");
                            iLogger.l(w5.ERROR, "Missing required field \"timestamp\"", illegalStateException9);
                            throw illegalStateException9;
                        }
                        if (strL041 == null) {
                            IllegalStateException illegalStateException10 = new IllegalStateException("Missing required field \"type\"");
                            iLogger.l(w5.ERROR, "Missing required field \"type\"", illegalStateException10);
                            throw illegalStateException10;
                        }
                        if (strL042 == null) {
                            IllegalStateException illegalStateException11 = new IllegalStateException("Missing required field \"name\"");
                            iLogger.l(w5.ERROR, "Missing required field \"name\"", illegalStateException11);
                            throw illegalStateException11;
                        }
                        if (dF6 == null) {
                            IllegalStateException illegalStateException12 = new IllegalStateException("Missing required field \"value\"");
                            iLogger.l(w5.ERROR, "Missing required field \"value\"", illegalStateException12);
                            throw illegalStateException12;
                        }
                        c6 c6Var = new c6();
                        c6Var.f17135i = vVar8;
                        c6Var.f17137m = dF5;
                        c6Var.f17138n = strL042;
                        c6Var.f17140p = strL041;
                        c6Var.f17141q = dF6;
                        c6Var.f17142r = mapM04;
                        c6Var.f17136l = o7Var2;
                        c6Var.f17139o = strL043;
                        c6Var.f17143s = map11;
                        return c6Var;
                    }
                    strNextName9 = k3Var2.nextName();
                    strNextName9.getClass();
                    switch (strNextName9) {
                        case "span_id":
                            o7Var2 = (o7) k3Var2.P(iLogger, new e(23));
                            break;
                        case "name":
                            strL042 = k3Var2.l0();
                            break;
                        case "type":
                            strL041 = k3Var2.l0();
                            break;
                        case "unit":
                            strL043 = k3Var2.l0();
                            break;
                        case "timestamp":
                            dF5 = k3Var2.F();
                            break;
                        case "value":
                            dF6 = k3Var2.F();
                            break;
                        case "attributes":
                            mapM04 = k3Var2.m0(iLogger, new e(14));
                            break;
                        case "trace_id":
                            vVar8 = (io.sentry.protocol.v) k3Var2.P(iLogger, new io.sentry.clientreport.a(23));
                            break;
                        default:
                            HashMap map12 = map11 == null ? new HashMap() : map11;
                            k3Var2.t(iLogger, map12, strNextName9);
                            map10 = map12;
                            continue;
                            break;
                    }
                    map10 = map11;
                }
                break;
            case 18:
                k3Var2.beginObject();
                HashMap map13 = null;
                ArrayList arrayListI03 = null;
                while (k3Var2.peek() == io.sentry.vendor.gson.stream.b.NAME) {
                    String strNextName19 = k3Var2.nextName();
                    strNextName19.getClass();
                    if (strNextName19.equals("items")) {
                        arrayListI03 = k3Var2.I0(iLogger, new e(i11));
                    } else {
                        if (map13 == null) {
                            map13 = new HashMap();
                        }
                        k3Var2.t(iLogger, map13, strNextName19);
                    }
                }
                k3Var2.endObject();
                if (arrayListI03 != null) {
                    d6 d6Var = new d6(arrayListI03);
                    d6Var.f17215l = map13;
                    return d6Var;
                }
                IllegalStateException illegalStateException13 = new IllegalStateException("Missing required field \"items\"");
                iLogger.l(w5.ERROR, "Missing required field \"items\"", illegalStateException13);
                throw illegalStateException13;
            case 19:
                a7 a7Var = new a7();
                k3Var2.beginObject();
                z6 z6Var = null;
                Date dateI5 = null;
                HashMap map14 = null;
                io.sentry.protocol.v vVar9 = null;
                Date dateI6 = null;
                List list4 = null;
                List list5 = null;
                List list6 = null;
                String strL044 = null;
                Integer numF06 = null;
                while (k3Var2.peek() == io.sentry.vendor.gson.stream.b.NAME) {
                    strNextName10 = k3Var2.nextName();
                    strNextName10.getClass();
                    switch (strNextName10) {
                        case "replay_id":
                            vVar9 = (io.sentry.protocol.v) k3Var2.P(iLogger, new io.sentry.clientreport.a(23));
                            break;
                        case "replay_start_timestamp":
                            dateI6 = k3Var.I(iLogger);
                            break;
                        case "type":
                            strL044 = k3Var2.l0();
                            break;
                        case "urls":
                            list4 = (List) k3Var2.F0();
                            break;
                        case "timestamp":
                            dateI5 = k3Var.I(iLogger);
                            break;
                        case "error_ids":
                            list5 = (List) k3Var2.F0();
                            break;
                        case "trace_ids":
                            list6 = (List) k3Var2.F0();
                            break;
                        case "replay_type":
                            z6Var = (z6) k3Var2.P(iLogger, new e(20));
                            break;
                        case "segment_id":
                            numF06 = k3Var2.f0();
                            break;
                        default:
                            if (!kotlin.reflect.b0.n(a7Var, strNextName10, k3Var2, iLogger)) {
                                if (map14 == null) {
                                    map14 = new HashMap();
                                }
                                k3Var2.t(iLogger, map14, strNextName10);
                                break;
                            } else {
                                break;
                            }
                            break;
                    }
                }
                k3Var2.endObject();
                if (strL044 != null) {
                    a7Var.A = strL044;
                }
                if (z6Var != null) {
                    a7Var.B = z6Var;
                }
                if (numF06 != null) {
                    a7Var.D = numF06.intValue();
                }
                if (dateI5 != null) {
                    a7Var.E = dateI5;
                }
                a7Var.C = vVar9;
                a7Var.F = dateI6;
                a7Var.G = list4;
                a7Var.H = list5;
                a7Var.I = list6;
                a7Var.J = map14;
                return a7Var;
            case 20:
                return z6.valueOf(k3Var2.nextString().toUpperCase(Locale.ROOT));
            case 21:
                k3Var2.beginObject();
                Integer numF07 = null;
                i7 i7VarValueOf = null;
                ConcurrentHashMap concurrentHashMap10 = null;
                String strL045 = null;
                String str3 = null;
                Long lH05 = null;
                Boolean boolL9 = null;
                String strL046 = null;
                String strL047 = null;
                String strL048 = null;
                Date dateI7 = null;
                Double dF7 = null;
                String strL049 = null;
                String strL050 = null;
                Date dateI8 = null;
                while (k3Var2.peek() == io.sentry.vendor.gson.stream.b.NAME) {
                    strNextName11 = k3Var2.nextName();
                    strNextName11.getClass();
                    switch (strNextName11) {
                        case "duration":
                            num = numF07;
                            dF7 = k3Var2.F();
                            break;
                        case "started":
                            num = numF07;
                            dateI7 = k3Var.I(iLogger);
                            break;
                        case "errors":
                            numF07 = k3Var2.f0();
                            continue;
                            break;
                        case "status":
                            num = numF07;
                            String strA = io.sentry.util.s.a(k3Var2.l0());
                            if (strA != null) {
                                i7VarValueOf = i7.valueOf(strA);
                                break;
                            }
                            break;
                        case "did":
                            num = numF07;
                            strL045 = k3Var2.l0();
                            break;
                        case "seq":
                            num = numF07;
                            lH05 = k3Var2.h0();
                            break;
                        case "sid":
                            String strL051 = k3Var2.l0();
                            if (strL051 != null) {
                                num = numF07;
                                if (strL051.length() == 36 || strL051.length() == 32) {
                                    str3 = strL051;
                                }
                                break;
                            } else {
                                num = numF07;
                            }
                            iLogger.q(w5.ERROR, "%s sid is not valid.", strL051);
                            break;
                        case "init":
                            boolL9 = k3Var2.L();
                            continue;
                            break;
                        case "timestamp":
                            dateI8 = k3Var.I(iLogger);
                            continue;
                            break;
                        case "attrs":
                            k3Var2.beginObject();
                            while (k3Var2.peek() == io.sentry.vendor.gson.stream.b.NAME) {
                                String strNextName20 = k3Var2.nextName();
                                strNextName20.getClass();
                                switch (strNextName20) {
                                    case "environment":
                                        strL050 = k3Var2.l0();
                                        break;
                                    case "release":
                                        strL047 = k3Var2.l0();
                                        break;
                                    case "ip_address":
                                        strL046 = k3Var2.l0();
                                        break;
                                    case "user_agent":
                                        strL049 = k3Var2.l0();
                                        break;
                                    default:
                                        k3Var2.skipValue();
                                        break;
                                }
                            }
                            k3Var2.endObject();
                            continue;
                            break;
                        case "abnormal_mechanism":
                            strL048 = k3Var2.l0();
                            continue;
                            break;
                        default:
                            if (concurrentHashMap10 == null) {
                                concurrentHashMap10 = new ConcurrentHashMap();
                            }
                            k3Var2.t(iLogger, concurrentHashMap10, strNextName11);
                            continue;
                            break;
                    }
                    numF07 = num;
                }
                Integer num3 = numF07;
                if (i7VarValueOf == null) {
                    throw d("status", iLogger);
                }
                if (dateI7 == null) {
                    throw d("started", iLogger);
                }
                if (num3 == null) {
                    throw d("errors", iLogger);
                }
                if (strL047 == null) {
                    throw d("release", iLogger);
                }
                j7 j7Var = new j7(i7VarValueOf, dateI7, dateI8, num3.intValue(), strL045, str3, boolL9, lH05, dF7, strL046, strL049, strL050, strL047, strL048);
                j7Var.z = concurrentHashMap10;
                k3Var2.endObject();
                return j7Var;
            case 22:
                return b(k3Var, iLogger);
            case 23:
                return new o7(k3Var2.nextString());
            case 24:
                return q7.valueOf(k3Var2.nextString().toUpperCase(Locale.ROOT));
            case 25:
                return c(k3Var, iLogger);
            default:
                k3Var2.beginObject();
                io.sentry.protocol.v vVar10 = null;
                String strL052 = null;
                String strL053 = null;
                String strL054 = null;
                HashMap map15 = null;
                while (k3Var2.peek() == io.sentry.vendor.gson.stream.b.NAME) {
                    strNextName12 = k3Var2.nextName();
                    strNextName12.getClass();
                    switch (strNextName12) {
                        case "comments":
                            strL054 = k3Var2.l0();
                            break;
                        case "name":
                            strL052 = k3Var2.l0();
                            break;
                        case "email":
                            strL053 = k3Var2.l0();
                            break;
                        case "event_id":
                            vVar10 = new io.sentry.protocol.v(k3Var2.nextString());
                            break;
                        default:
                            if (map15 == null) {
                                map15 = new HashMap();
                            }
                            k3Var2.t(iLogger, map15, strNextName12);
                            break;
                    }
                }
                k3Var2.endObject();
                if (vVar10 != null) {
                    z7 z7Var = new z7(vVar10, strL052, strL053, strL054);
                    z7Var.f18085o = map15;
                    return z7Var;
                }
                IllegalStateException illegalStateException14 = new IllegalStateException("Missing required field \"event_id\"");
                iLogger.l(w5.ERROR, "Missing required field \"event_id\"", illegalStateException14);
                throw illegalStateException14;
        }
    }
}
