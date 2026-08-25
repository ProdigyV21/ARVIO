package io.sentry;

import com.google.android.gms.cast.CredentialsData;
import j$.util.concurrent.ConcurrentHashMap;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.Callable;

/* JADX INFO: loaded from: classes4.dex */
public final class t3 implements k2, i2 {
    public String A;
    public String B;
    public String C;
    public String D;
    public String E;
    public String F;
    public String G;
    public String H;
    public String I;
    public Date J;
    public final Map K;
    public ConcurrentHashMap M;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final File f17872i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final Callable f17873l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public int f17874m;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public String f17876o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public String f17877p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public String f17878q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public String f17879r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public String f17880s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public boolean f17881t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public String f17882u;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public String f17884w;
    public String x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public String f17885y;
    public final ArrayList z;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public List f17883v = new ArrayList();
    public String L = null;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public String f17875n = Locale.getDefault().toString();

    public t3(File file, Date date, ArrayList arrayList, String str, String str2, String str3, String str4, int i10, String str5, Callable callable, String str6, String str7, String str8, Boolean bool, String str9, String str10, String str11, String str12, String str13, Map map) {
        this.f17872i = file;
        this.J = date;
        this.f17882u = str5;
        this.f17873l = callable;
        this.f17874m = i10;
        this.f17876o = str6 == null ? "" : str6;
        this.f17877p = str7 == null ? "" : str7;
        this.f17880s = str8 != null ? str8 : "";
        this.f17881t = bool != null ? bool.booleanValue() : false;
        this.f17884w = str9 != null ? str9 : "0";
        this.f17878q = "";
        this.f17879r = CredentialsData.CREDENTIALS_TYPE_ANDROID;
        this.x = CredentialsData.CREDENTIALS_TYPE_ANDROID;
        this.f17885y = str10 != null ? str10 : "";
        this.z = arrayList;
        this.A = str.isEmpty() ? "unknown" : str;
        this.B = str4;
        this.C = "";
        this.D = str11 != null ? str11 : "";
        this.E = str2;
        this.F = str3;
        this.G = qb.d.B();
        this.H = str12 != null ? str12 : "production";
        this.I = str13;
        if (!str13.equals("normal") && !this.I.equals("timeout") && !this.I.equals("backgrounded")) {
            this.I = "normal";
        }
        this.K = map;
    }

    @Override // io.sentry.i2
    public final void serialize(l3 l3Var, ILogger iLogger) throws IOException {
        fi.iki.elonen.f fVar = (fi.iki.elonen.f) l3Var;
        fVar.j();
        fVar.S("android_api_level");
        fVar.b0(iLogger, Integer.valueOf(this.f17874m));
        fVar.S("device_locale");
        fVar.b0(iLogger, this.f17875n);
        fVar.S("device_manufacturer");
        fVar.value(this.f17876o);
        fVar.S("device_model");
        fVar.value(this.f17877p);
        fVar.S("device_os_build_number");
        fVar.value(this.f17878q);
        fVar.S("device_os_name");
        fVar.value(this.f17879r);
        fVar.S("device_os_version");
        fVar.value(this.f17880s);
        fVar.S("device_is_emulator");
        fVar.e0(this.f17881t);
        fVar.S("architecture");
        fVar.b0(iLogger, this.f17882u);
        fVar.S("device_cpu_frequencies");
        fVar.b0(iLogger, this.f17883v);
        fVar.S("device_physical_memory_bytes");
        fVar.value(this.f17884w);
        fVar.S("platform");
        fVar.value(this.x);
        fVar.S("build_id");
        fVar.value(this.f17885y);
        fVar.S("transaction_name");
        fVar.value(this.A);
        fVar.S("duration_ns");
        fVar.value(this.B);
        fVar.S("version_name");
        fVar.value(this.D);
        fVar.S("version_code");
        fVar.value(this.C);
        ArrayList arrayList = this.z;
        if (!arrayList.isEmpty()) {
            fVar.S("transactions");
            fVar.b0(iLogger, arrayList);
        }
        fVar.S("transaction_id");
        fVar.value(this.E);
        fVar.S("trace_id");
        fVar.value(this.F);
        fVar.S("profile_id");
        fVar.value(this.G);
        fVar.S("environment");
        fVar.value(this.H);
        fVar.S("truncation_reason");
        fVar.value(this.I);
        if (this.L != null) {
            fVar.S("sampled_profile");
            fVar.value(this.L);
        }
        String str = ((io.sentry.vendor.gson.stream.c) fVar.f15416i).f18022n;
        fVar.Y("");
        fVar.S("measurements");
        fVar.b0(iLogger, this.K);
        fVar.Y(str);
        fVar.S("timestamp");
        fVar.b0(iLogger, this.J);
        ConcurrentHashMap concurrentHashMap = this.M;
        if (concurrentHashMap != null) {
            for (K k : concurrentHashMap.keySet()) {
                g8.b.n(this.M, k, fVar, k, iLogger);
            }
        }
        fVar.K();
    }
}
