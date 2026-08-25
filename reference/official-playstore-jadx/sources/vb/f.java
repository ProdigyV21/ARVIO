package vb;

import androidx.media3.exoplayer.analytics.AnalyticsListener;
import com.google.android.gms.common.api.internal.g0;
import gb.a0;
import gb.k0;
import gb.p0;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import kotlin.collections.s;
import kotlin.text.u;
import r7.l;
import x6.x;
import x8.n;
import x8.o;
import x8.p;
import xb.h0;
import xb.i0;
import y8.c0;

/* JADX INFO: loaded from: classes5.dex */
public final class f implements gb.j, s9.e {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final Object f22276i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final Object f22277l;

    public /* synthetic */ f(Object obj, Object obj2) {
        this.f22276i = obj;
        this.f22277l = obj2;
    }

    public void a(String str, l lVar) {
        LinkedHashMap linkedHashMap = ((o) this.f22277l).f22669a;
        n nVar = new n(this, str);
        lVar.invoke(nVar);
        String str2 = (String) this.f22276i;
        ArrayList arrayList = nVar.f22667a;
        ArrayList arrayList2 = new ArrayList(s.U(arrayList, 10));
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            arrayList2.add((String) ((x) it.next()).f22608i);
        }
        String strI = (String) nVar.f22668b.f22608i;
        StringBuilder sb2 = new StringBuilder();
        sb2.append(str);
        sb2.append('(');
        sb2.append(kotlin.collections.x.u0(arrayList2, "", null, null, c0.f22918i, 30));
        sb2.append(')');
        if (strI.length() > 1) {
            strI = androidx.compose.material3.d.i(';', "L", strI);
        }
        sb2.append(strI);
        String strM = androidx.compose.foundation.c.m('.', str2, sb2.toString());
        p pVar = (p) nVar.f22668b.f22609l;
        ArrayList arrayList3 = new ArrayList(s.U(arrayList, 10));
        Iterator it2 = arrayList.iterator();
        while (it2.hasNext()) {
            arrayList3.add((p) ((x) it2.next()).f22609l);
        }
        linkedHashMap.put(strM, new x8.k(pVar, arrayList3));
    }

    @Override // s9.e
    public s9.d c(f9.b bVar) {
        l8.c cVar = (l8.c) this.f22276i;
        y8.p pVar = (y8.p) this.f22277l;
        pVar.c().f21708c.getClass();
        e9.f fVar = e9.f.f15073g;
        kb.d dVarB = cVar.b(bVar);
        l8.e eVar = dVarB != null ? (l8.e) dVarB.f19673l : null;
        if (eVar == null) {
            return null;
        }
        m8.c.a(eVar.f20063a).equals(bVar);
        return pVar.f(eVar);
    }

    @Override // gb.j
    public void onFailure(gb.i iVar, IOException iOException) {
        ((g) this.f22276i).i(iOException, null);
    }

    @Override // gb.j
    public void onResponse(gb.i iVar, p0 p0Var) {
        g0 g0Var = p0Var.f15792w;
        boolean z = true;
        try {
            ((g) this.f22276i).h(p0Var, g0Var);
            lb.i iVar2 = (lb.i) g0Var.f12734b;
            if (iVar2.f20120u) {
                throw new IllegalStateException("Check failed.");
            }
            iVar2.f20120u = true;
            iVar2.f20115p.j();
            lb.k kVarE = ((mb.d) g0Var.f12737e).e();
            Socket socket = kVarE.f20127d;
            i0 i0Var = kVarE.f20131h;
            h0 h0Var = kVarE.f20132i;
            int i10 = 0;
            socket.setSoTimeout(0);
            kVarE.l();
            io.sentry.android.replay.util.e eVar = new io.sentry.android.replay.util.e(i0Var, h0Var, g0Var);
            a0 a0Var = p0Var.f15785p;
            int size = a0Var.size();
            int i11 = 0;
            boolean z5 = false;
            boolean z10 = false;
            boolean z11 = false;
            boolean z12 = false;
            Integer numR = null;
            Integer numR2 = null;
            while (i11 < size) {
                if (u.L(a0Var.b(i11), "Sec-WebSocket-Extensions", z)) {
                    String strE = a0Var.e(i11);
                    int i12 = i10;
                    while (i12 < strE.length()) {
                        boolean z13 = z;
                        int iH = ib.c.h(strE, ',', i12, i10, 4);
                        int iG = ib.c.g(strE, i12, iH, ';');
                        String strZ = ib.c.z(i12, iG, strE);
                        int i13 = iG + 1;
                        if (strZ.equalsIgnoreCase("permessage-deflate")) {
                            if (z5) {
                                z12 = z13;
                            }
                            while (true) {
                                i12 = i13;
                                while (i12 < iH) {
                                    int iG2 = ib.c.g(strE, i12, iH, ';');
                                    int iG3 = ib.c.g(strE, i12, iG2, '=');
                                    String strZ2 = ib.c.z(i12, iG3, strE);
                                    String strT0 = iG3 < iG2 ? kotlin.text.o.t0(ib.c.z(iG3 + 1, iG2, strE)) : null;
                                    i13 = iG2 + 1;
                                    if (strZ2.equalsIgnoreCase("client_max_window_bits")) {
                                        if (numR != null) {
                                            z12 = z13;
                                        }
                                        numR = strT0 != null ? u.R(strT0) : null;
                                        if (numR != null) {
                                            break;
                                        }
                                        i12 = i13;
                                        z12 = z13;
                                    } else if (strZ2.equalsIgnoreCase("client_no_context_takeover")) {
                                        if (z10) {
                                            z12 = z13;
                                        }
                                        if (strT0 != null) {
                                            z12 = z13;
                                        }
                                        i12 = i13;
                                        z10 = z13;
                                    } else {
                                        if (strZ2.equalsIgnoreCase("server_max_window_bits")) {
                                            if (numR2 != null) {
                                                z12 = z13;
                                            }
                                            numR2 = strT0 != null ? u.R(strT0) : null;
                                            if (numR2 != null) {
                                                break;
                                            }
                                        } else if (strZ2.equalsIgnoreCase("server_no_context_takeover")) {
                                            if (z11) {
                                                z12 = z13;
                                            }
                                            if (strT0 != null) {
                                                z12 = z13;
                                            }
                                            i12 = i13;
                                            z11 = z13;
                                        }
                                        i12 = i13;
                                        z12 = z13;
                                    }
                                }
                            }
                            z = z13;
                            z5 = z;
                        } else {
                            i12 = i13;
                            z = z13;
                            z12 = z;
                        }
                        i10 = 0;
                    }
                }
                i11++;
                z = z;
                i10 = 0;
            }
            boolean z14 = z;
            ((g) this.f22276i).f22282d = new h(z5, numR, z10, numR2, z11, z12);
            if (z12 || numR != null || (numR2 != null && !new x7.i(8, 15, z14 ? 1 : 0).d(numR2.intValue()))) {
                g gVar = (g) this.f22276i;
                synchronized (gVar) {
                    gVar.f22292o.clear();
                    gVar.e(AnalyticsListener.EVENT_AUDIO_POSITION_ADVANCING, "unexpected Sec-WebSocket-Extensions in response header");
                }
            }
            try {
                ((g) this.f22276i).j(ib.c.f16275h + " WebSocket " + ((k0) this.f22277l).f15730a.h(), eVar);
                g gVar2 = (g) this.f22276i;
                gVar2.f22279a.onOpen(gVar2, p0Var);
                ((g) this.f22276i).k();
            } catch (Exception e5) {
                ((g) this.f22276i).i(e5, null);
            }
        } catch (IOException e6) {
            ((g) this.f22276i).i(e6, p0Var);
            ib.c.d(p0Var);
            if (g0Var != null) {
                g0Var.n(true, true, null);
            }
        }
    }

    public f(o oVar, String str) {
        this.f22277l = oVar;
        this.f22276i = str;
    }
}
