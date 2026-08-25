package com.google.android.gms.internal.cast;

import android.text.TextUtils;
import com.google.android.gms.cast.CastDevice;
import com.google.android.gms.cast.framework.CastSession;
import com.google.android.gms.cast.internal.Logger;
import com.google.android.gms.cast.internal.zzaa;
import j$.util.DesugarCollections;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes4.dex */
public final class b {

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public static final Logger f13170v = new Logger("SessionFlowSummary");

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public static final String f13171w = "22.2.0";
    public static long x = System.currentTimeMillis();

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final z4 f13177f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final String f13178g;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final long f13180i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public CastSession f13181j;
    public String k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public String f13182l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public androidx.appcompat.app.g1 f13183m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public String f13184n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public String f13185o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public String f13186p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public String f13187q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public String f13188r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public String f13189s;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public int f13191u;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final e4 f13172a = new e4(0);

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final List f13173b = Collections.synchronizedList(new ArrayList());

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final List f13174c = Collections.synchronizedList(new ArrayList());

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final List f13175d = Collections.synchronizedList(new ArrayList());

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final Map f13176e = DesugarCollections.synchronizedMap(new HashMap());

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public int f13190t = 0;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final long f13179h = System.currentTimeMillis();

    public b(z4 z4Var, String str) {
        this.f13177f = z4Var;
        this.f13178g = str;
        long j10 = x;
        x = 1 + j10;
        this.f13180i = j10;
    }

    public final void a(CastSession castSession) {
        if (castSession == null) {
            b(2);
            return;
        }
        CastDevice castDevice = castSession.getCastDevice();
        if (castDevice == null) {
            b(3);
            return;
        }
        this.f13181j = castSession;
        String str = this.f13182l;
        if (str != null) {
            if (TextUtils.equals(str, castDevice.zza())) {
                return;
            }
            b(5);
            return;
        }
        this.f13182l = castDevice.zza();
        this.f13184n = castDevice.getModelName();
        this.f13190t = castDevice.zzd();
        zzaa zzaaVarZzb = castDevice.zzb();
        if (zzaaVarZzb != null) {
            this.f13185o = zzaaVarZzb.zza();
            this.f13186p = zzaaVarZzb.zzb();
            this.f13187q = zzaaVarZzb.zzc();
            this.f13188r = zzaaVarZzb.zzd();
            this.f13189s = zzaaVarZzb.zze();
        }
        castSession.zzm();
    }

    public final void b(int i10) {
        Integer numValueOf = Integer.valueOf(i10 - 1);
        Map map = this.f13176e;
        s sVar = (s) map.get(numValueOf);
        if (sVar != null) {
            sVar.f13595d.incrementAndGet();
            sVar.f13593b = System.currentTimeMillis();
        } else {
            s sVar2 = new s(new androidx.leanback.widget.r(i10));
            sVar2.f13594c = this.f13179h;
            map.put(numValueOf, sVar2);
        }
    }
}
