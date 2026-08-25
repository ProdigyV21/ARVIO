package a3;

import android.content.Context;
import android.net.ConnectivityManager;
import android.util.Log;
import androidx.appcompat.widget.u4;
import b3.h;
import b3.i;
import b3.k;
import b3.l;
import b3.m;
import b3.o;
import b3.u;
import b3.v;
import b3.x;
import c3.j;
import com.google.android.datatransport.runtime.n;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes4.dex */
public final class e implements j {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final a8.e f119a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final ConnectivityManager f120b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final Context f121c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final URL f122d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final m3.a f123e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final m3.a f124f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final int f125g;

    public e(Context context, m3.a aVar, m3.a aVar2) {
        i5.d dVar = new i5.d();
        h.f7162a.configure(dVar);
        dVar.f16225d = true;
        this.f119a = new a8.e(dVar, 22);
        this.f121c = context;
        this.f120b = (ConnectivityManager) context.getSystemService("connectivity");
        this.f122d = c(a.f105c);
        this.f123e = aVar2;
        this.f124f = aVar;
        this.f125g = 130000;
    }

    public static URL c(String str) {
        try {
            return new URL(str);
        } catch (MalformedURLException e5) {
            throw new IllegalArgumentException(androidx.compose.material3.d.C("Invalid url: ", str), e5);
        }
    }

    @Override // c3.j
    public final c3.b a(c3.a aVar) {
        String str;
        d dVarC;
        Integer numValueOf;
        String str2;
        Iterator it;
        k kVar;
        u4 u4Var;
        HashMap map = new HashMap();
        for (com.google.android.datatransport.runtime.h hVar : aVar.f7385a) {
            String str3 = hVar.f12583a;
            if (map.containsKey(str3)) {
                ((List) map.get(str3)).add(hVar);
            } else {
                ArrayList arrayList = new ArrayList();
                arrayList.add(hVar);
                map.put(str3, arrayList);
            }
        }
        ArrayList arrayList2 = new ArrayList();
        Iterator it2 = map.entrySet().iterator();
        while (true) {
            int i10 = 0;
            if (!it2.hasNext()) {
                b3.j jVar = new b3.j(arrayList2);
                byte[] bArr = aVar.f7386b;
                URL urlC = this.f122d;
                if (bArr != null) {
                    try {
                        a aVarB = a.b(bArr);
                        str = aVarB.f110b;
                        if (str == null) {
                            str = null;
                        }
                        String str4 = aVarB.f109a;
                        if (str4 != null) {
                            urlC = c(str4);
                        }
                    } catch (IllegalArgumentException unused) {
                        return new c3.b(3, -1L);
                    }
                } else {
                    str = null;
                }
                try {
                    c cVar = new c(urlC, jVar, str);
                    b bVar = new b(this, i10);
                    int i11 = 5;
                    do {
                        dVarC = bVar.c(cVar);
                        URL url = (URL) dVarC.f118c;
                        if (url != null) {
                            ac.b.x("CctTransportBackend", url, "Following redirect to: %s");
                            cVar = new c(url, cVar.f114b, cVar.f115c);
                        } else {
                            cVar = null;
                        }
                        if (cVar == null) {
                            break;
                        }
                        i11--;
                    } while (i11 >= 1);
                    int i12 = dVarC.f116a;
                    if (i12 == 200) {
                        return new c3.b(1, dVarC.f117b);
                    }
                    if (i12 < 500 && i12 != 404) {
                        return i12 == 400 ? new c3.b(4, -1L) : new c3.b(3, -1L);
                    }
                    return new c3.b(2, -1L);
                } catch (IOException e5) {
                    ac.b.B("CctTransportBackend", "Could not make request to the backend", e5);
                    return new c3.b(2, -1L);
                }
            }
            Map.Entry entry = (Map.Entry) it2.next();
            com.google.android.datatransport.runtime.h hVar2 = (com.google.android.datatransport.runtime.h) ((List) entry.getValue()).get(0);
            x xVar = x.f7198i;
            long jA = this.f124f.a();
            long jA2 = this.f123e.a();
            k kVar2 = new k(new i(hVar2.a("model"), hVar2.a("hardware"), hVar2.a("device"), hVar2.a("product"), hVar2.a("os-uild"), hVar2.a("manufacturer"), hVar2.a("fingerprint"), hVar2.a("locale"), hVar2.a("country"), hVar2.a("mcc_mnc"), hVar2.a("application_build"), Integer.valueOf(hVar2.b("sdk-version"))));
            try {
                str2 = null;
                numValueOf = Integer.valueOf(Integer.parseInt((String) entry.getKey()));
            } catch (NumberFormatException unused2) {
                numValueOf = null;
                str2 = (String) entry.getKey();
            }
            ArrayList arrayList3 = new ArrayList();
            Iterator it3 = ((List) entry.getValue()).iterator();
            while (it3.hasNext()) {
                com.google.android.datatransport.runtime.h hVar3 = (com.google.android.datatransport.runtime.h) it3.next();
                n nVar = hVar3.f12585c;
                z2.b bVar2 = nVar.f12600a;
                byte[] bArr2 = nVar.f12601b;
                Iterator it4 = it2;
                if (bVar2.equals(new z2.b("proto"))) {
                    u4Var = new u4();
                    u4Var.f1776d = bArr2;
                } else if (bVar2.equals(new z2.b("json"))) {
                    String str5 = new String(bArr2, Charset.forName("UTF-8"));
                    u4 u4Var2 = new u4();
                    u4Var2.f1777e = str5;
                    u4Var = u4Var2;
                } else {
                    it = it3;
                    kVar = kVar2;
                    String strI = ac.b.I("CctTransportBackend");
                    if (Log.isLoggable(strI, 5)) {
                        Log.w(strI, "Received event of unsupported encoding " + bVar2 + ". Skipping...");
                    }
                    kVar2 = kVar;
                    it3 = it;
                    it2 = it4;
                }
                it = it3;
                kVar = kVar2;
                u4Var.f1773a = Long.valueOf(hVar3.f12586d);
                u4Var.f1775c = Long.valueOf(hVar3.f12587e);
                String str6 = (String) hVar3.f12588f.get("tz-offset");
                u4Var.f1778f = Long.valueOf(str6 == null ? 0L : Long.valueOf(str6).longValue());
                u4Var.f1779g = new o((v) v.f7196i.get(hVar3.b("net-type")), (u) u.f7194i.get(hVar3.b("mobile-subtype")));
                Integer num = hVar3.f12584b;
                if (num != null) {
                    u4Var.f1774b = num;
                }
                String strM = ((Long) u4Var.f1773a) == null ? " eventTimeMs" : "";
                if (((Long) u4Var.f1775c) == null) {
                    strM = strM.concat(" eventUptimeMs");
                }
                if (((Long) u4Var.f1778f) == null) {
                    strM = androidx.compose.material3.d.m(strM, " timezoneOffsetSeconds");
                }
                if (!strM.isEmpty()) {
                    throw new IllegalStateException("Missing required properties:".concat(strM));
                }
                arrayList3.add(new l(((Long) u4Var.f1773a).longValue(), (Integer) u4Var.f1774b, ((Long) u4Var.f1775c).longValue(), (byte[]) u4Var.f1776d, (String) u4Var.f1777e, ((Long) u4Var.f1778f).longValue(), (o) u4Var.f1779g));
                kVar2 = kVar;
                it3 = it;
                it2 = it4;
            }
            arrayList2.add(new m(jA, jA2, kVar2, numValueOf, str2, arrayList3));
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:23:0x00b0  */
    /* JADX WARN: Removed duplicated region for block: B:30:0x010b  */
    @Override // c3.j
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final com.google.android.datatransport.runtime.h b(com.google.android.datatransport.runtime.h r7) {
        /*
            Method dump skipped, instruction units count: 291
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: a3.e.b(com.google.android.datatransport.runtime.h):com.google.android.datatransport.runtime.h");
    }
}
