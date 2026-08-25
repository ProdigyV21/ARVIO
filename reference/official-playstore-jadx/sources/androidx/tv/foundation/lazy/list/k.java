package androidx.tv.foundation.lazy.list;

import androidx.compose.ui.unit.IntOffset;
import androidx.compose.ui.unit.IntOffsetKt;
import androidx.media3.extractor.ts.PsExtractor;
import gb.t0;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.SocketAddress;
import java.net.SocketException;
import java.net.URI;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.NoSuchElementException;

/* JADX INFO: loaded from: classes3.dex */
public final class k {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f5407a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f5408b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final ArrayList f5409c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public Object f5410d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public Object f5411e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public Object f5412f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public Object f5413g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public Object f5414h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public Object f5415i;

    public k(int i10) {
        this.f5407a = i10;
        switch (i10) {
            case 1:
                this.f5412f = "";
                this.f5413g = "";
                this.f5408b = -1;
                ArrayList arrayList = new ArrayList();
                this.f5409c = arrayList;
                arrayList.add("");
                break;
            default:
                this.f5411e = new LinkedHashSet();
                this.f5412f = i2.j.f16053a;
                int i11 = t.k0.f21841a;
                this.f5413g = new t.d0();
                this.f5409c = new ArrayList();
                this.f5410d = new ArrayList();
                this.f5414h = new ArrayList();
                this.f5415i = new ArrayList();
                break;
        }
    }

    public static void g(x xVar, int i10) {
        int[] iArr = xVar.f5520q;
        long jIntOffset = IntOffsetKt.IntOffset(iArr[0], iArr[1]);
        if (xVar.f5507c) {
            IntOffset.m5798copyiSbpLlY$default(jIntOffset, 0, i10, 1, null);
        } else {
            IntOffset.m5798copyiSbpLlY$default(jIntOffset, i10, 0, 2, null);
        }
        int size = xVar.f5506b.size();
        for (int i11 = 0; i11 < size; i11++) {
            xVar.a(i11);
        }
    }

    public static void l(x xVar) {
        int size = xVar.f5506b.size();
        for (int i10 = 0; i10 < size; i10++) {
            xVar.a(i10);
        }
    }

    public void a(String str, String str2) {
        if (((ArrayList) this.f5410d) == null) {
            this.f5410d = new ArrayList();
        }
        ((ArrayList) this.f5410d).add(gb.u.c(0, 0, 211, str, " \"'<>#&=", true));
        ((ArrayList) this.f5410d).add(str2 != null ? gb.u.c(0, 0, 211, str2, " \"'<>#&=", true) : null);
    }

    public void b(String str) {
        j(0, str.length(), str, false, false);
    }

    public void c(String str, String str2) {
        if (((ArrayList) this.f5410d) == null) {
            this.f5410d = new ArrayList();
        }
        ((ArrayList) this.f5410d).add(gb.u.c(0, 0, 219, str, " !\"#$&'(),/:;<=>?@[]\\^`{|}~", false));
        ((ArrayList) this.f5410d).add(str2 != null ? gb.u.c(0, 0, 219, str2, " !\"#$&'(),/:;<=>?@[]\\^`{|}~", false) : null);
    }

    public gb.b0 d() {
        ArrayList arrayList;
        String str = (String) this.f5411e;
        if (str == null) {
            throw new IllegalStateException("scheme == null");
        }
        String strG = gb.u.g((String) this.f5412f, 0, 0, 7);
        String strG2 = gb.u.g((String) this.f5413g, 0, 0, 7);
        String str2 = (String) this.f5414h;
        if (str2 == null) {
            throw new IllegalStateException("host == null");
        }
        int iE = e();
        ArrayList arrayList2 = this.f5409c;
        ArrayList arrayList3 = new ArrayList(kotlin.collections.s.U(arrayList2, 10));
        Iterator it = arrayList2.iterator();
        while (it.hasNext()) {
            arrayList3.add(gb.u.g((String) it.next(), 0, 0, 7));
        }
        ArrayList<String> arrayList4 = (ArrayList) this.f5410d;
        if (arrayList4 != null) {
            ArrayList arrayList5 = new ArrayList(kotlin.collections.s.U(arrayList4, 10));
            for (String str3 : arrayList4) {
                arrayList5.add(str3 != null ? gb.u.g(str3, 0, 0, 3) : null);
            }
            arrayList = arrayList5;
        } else {
            arrayList = null;
        }
        String str4 = (String) this.f5415i;
        return new gb.b0(str, strG, strG2, str2, iE, arrayList3, arrayList, str4 != null ? gb.u.g(str4, 0, 0, 7) : null, toString());
    }

    public int e() {
        int i10 = this.f5408b;
        if (i10 != -1) {
            return i10;
        }
        String str = (String) this.f5411e;
        if (str.equals("http")) {
            return 80;
        }
        return str.equals("https") ? 443 : -1;
    }

    public boolean f() {
        return this.f5408b < ((List) this.f5414h).size() || !this.f5409c.isEmpty();
    }

    /* JADX WARN: Type inference failed for: r1v23, types: [java.lang.Object, java.util.List] */
    public androidx.core.provider.j h() throws SocketException, UnknownHostException {
        String hostName;
        int port;
        List listSingletonList;
        boolean zContains;
        if (!f()) {
            throw new NoSuchElementException();
        }
        ArrayList arrayList = new ArrayList();
        while (this.f5408b < ((List) this.f5414h).size()) {
            gb.a aVar = (gb.a) this.f5411e;
            if (this.f5408b >= ((List) this.f5414h).size()) {
                throw new SocketException("No route to " + aVar.f15612h.f15620d + "; exhausted proxy configurations: " + ((List) this.f5414h));
            }
            List list = (List) this.f5414h;
            int i10 = this.f5408b;
            this.f5408b = i10 + 1;
            Proxy proxy = (Proxy) list.get(i10);
            gb.u uVar = (gb.u) this.f5410d;
            ArrayList arrayList2 = new ArrayList();
            this.f5415i = arrayList2;
            if (proxy.type() == Proxy.Type.DIRECT || proxy.type() == Proxy.Type.SOCKS) {
                gb.b0 b0Var = aVar.f15612h;
                hostName = b0Var.f15620d;
                port = b0Var.f15621e;
            } else {
                SocketAddress socketAddressAddress = proxy.address();
                if (!(socketAddressAddress instanceof InetSocketAddress)) {
                    throw new IllegalArgumentException(("Proxy.address() is not an InetSocketAddress: " + socketAddressAddress.getClass()).toString());
                }
                InetSocketAddress inetSocketAddress = (InetSocketAddress) socketAddressAddress;
                InetAddress address = inetSocketAddress.getAddress();
                hostName = address == null ? inetSocketAddress.getHostName() : address.getHostAddress();
                port = inetSocketAddress.getPort();
            }
            if (1 > port || port >= 65536) {
                throw new SocketException("No route to " + hostName + ':' + port + "; port is out of range");
            }
            if (proxy.type() == Proxy.Type.SOCKS) {
                arrayList2.add(InetSocketAddress.createUnresolved(hostName, port));
            } else {
                if (ib.c.f16274g.e(hostName)) {
                    listSingletonList = Collections.singletonList(InetAddress.getByName(hostName));
                } else {
                    uVar.getClass();
                    List listLookup = aVar.f15605a.lookup(hostName);
                    if (listLookup.isEmpty()) {
                        throw new UnknownHostException(aVar.f15605a + " returned no addresses for " + hostName);
                    }
                    listSingletonList = listLookup;
                }
                Iterator it = listSingletonList.iterator();
                while (it.hasNext()) {
                    arrayList2.add(new InetSocketAddress((InetAddress) it.next(), port));
                }
            }
            Iterator it2 = this.f5415i.iterator();
            while (it2.hasNext()) {
                t0 t0Var = new t0((gb.a) this.f5411e, proxy, (InetSocketAddress) it2.next());
                kb.d dVar = (kb.d) this.f5412f;
                synchronized (dVar) {
                    zContains = ((LinkedHashSet) dVar.f19673l).contains(t0Var);
                }
                if (zContains) {
                    this.f5409c.add(t0Var);
                } else {
                    arrayList.add(t0Var);
                }
            }
            if (!arrayList.isEmpty()) {
                break;
            }
        }
        if (arrayList.isEmpty()) {
            kotlin.collections.x.b0(arrayList, this.f5409c);
            this.f5409c.clear();
        }
        return new androidx.core.provider.j(arrayList);
    }

    /* JADX WARN: Removed duplicated region for block: B:145:0x02aa  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x0079  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void i(gb.b0 r22, java.lang.String r23) {
        /*
            Method dump skipped, instruction units count: 770
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.tv.foundation.lazy.list.k.i(gb.b0, java.lang.String):void");
    }

    public void j(int i10, int i11, String str, boolean z, boolean z5) {
        String strC = gb.u.c(i10, i11, PsExtractor.VIDEO_STREAM_MASK, str, " \"<>^`{}|/\\?#", z5);
        if (strC.equals(".") || strC.equalsIgnoreCase("%2e")) {
            return;
        }
        boolean zEquals = strC.equals("..");
        ArrayList arrayList = this.f5409c;
        if (zEquals || strC.equalsIgnoreCase("%2e.") || strC.equalsIgnoreCase(".%2e") || strC.equalsIgnoreCase("%2e%2e")) {
            if (((String) arrayList.remove(arrayList.size() - 1)).length() != 0 || arrayList.isEmpty()) {
                arrayList.add("");
                return;
            } else {
                arrayList.set(arrayList.size() - 1, "");
                return;
            }
        }
        if (((CharSequence) a0.c.h(1, arrayList)).length() == 0) {
            arrayList.set(arrayList.size() - 1, strC);
        } else {
            arrayList.add(strC);
        }
        if (z) {
            arrayList.add("");
        }
    }

    public void k(int i10, int i11, String str) {
        if (i10 == i11) {
            return;
        }
        char cCharAt = str.charAt(i10);
        ArrayList arrayList = this.f5409c;
        if (cCharAt == '/' || cCharAt == '\\') {
            arrayList.clear();
            arrayList.add("");
            i10++;
        } else {
            arrayList.set(arrayList.size() - 1, "");
        }
        int i12 = i10;
        while (i12 < i11) {
            int iF = ib.c.f(i12, i11, str, "/\\");
            boolean z = iF < i11;
            String str2 = str;
            j(i12, iF, str2, z, true);
            i12 = z ? iF + 1 : iF;
            str = str2;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:38:0x00ab  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.String toString() {
        /*
            Method dump skipped, instruction units count: 250
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.tv.foundation.lazy.list.k.toString():java.lang.String");
    }

    public k(gb.a aVar, kb.d dVar, gb.i iVar, gb.u uVar) {
        List listL;
        this.f5407a = 2;
        this.f5411e = aVar;
        this.f5412f = dVar;
        this.f5413g = iVar;
        this.f5410d = uVar;
        kotlin.collections.z zVar = kotlin.collections.z.f19728i;
        this.f5414h = zVar;
        this.f5415i = zVar;
        this.f5409c = new ArrayList();
        URI uriI = aVar.f15612h.i();
        if (uriI.getHost() == null) {
            listL = ib.c.l(Proxy.NO_PROXY);
        } else {
            List<Proxy> listSelect = aVar.f15611g.select(uriI);
            if (listSelect != null && !listSelect.isEmpty()) {
                listL = ib.c.x(listSelect);
            } else {
                listL = ib.c.l(Proxy.NO_PROXY);
            }
        }
        this.f5414h = listL;
        this.f5408b = 0;
    }
}
