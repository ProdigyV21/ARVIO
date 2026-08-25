package lb;

import androidx.appcompat.widget.g4;
import gb.l;
import gb.m;
import gb.p;
import java.net.UnknownServiceException;
import java.util.Arrays;
import java.util.List;
import javax.net.ssl.SSLSocket;

/* JADX INFO: loaded from: classes5.dex */
public final class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final List f20080a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f20081b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public boolean f20082c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public boolean f20083d;

    public b(List list) {
        this.f20080a = list;
    }

    public final p a(SSLSocket sSLSocket) throws UnknownServiceException {
        p pVar;
        int i10;
        boolean z;
        int i11 = this.f20081b;
        List list = this.f20080a;
        int size = list.size();
        while (true) {
            if (i11 >= size) {
                pVar = null;
                break;
            }
            pVar = (p) list.get(i11);
            if (pVar.b(sSLSocket)) {
                this.f20081b = i11 + 1;
                break;
            }
            i11++;
        }
        if (pVar == null) {
            throw new UnknownServiceException("Unable to find acceptable protocols. isFallback=" + this.f20083d + ", modes=" + list + ", supported protocols=" + Arrays.toString(sSLSocket.getEnabledProtocols()));
        }
        int i12 = this.f20081b;
        int size2 = list.size();
        while (true) {
            i10 = 0;
            if (i12 >= size2) {
                z = false;
                break;
            }
            if (((p) list.get(i12)).b(sSLSocket)) {
                z = true;
                break;
            }
            i12++;
        }
        this.f20082c = z;
        boolean z5 = this.f20083d;
        String[] strArr = pVar.f15779d;
        String[] strArr2 = pVar.f15778c;
        String[] strArrP = strArr2 != null ? ib.c.p(sSLSocket.getEnabledCipherSuites(), strArr2, m.f15740c) : sSLSocket.getEnabledCipherSuites();
        String[] strArrP2 = strArr != null ? ib.c.p(sSLSocket.getEnabledProtocols(), strArr, a7.c.f130l) : sSLSocket.getEnabledProtocols();
        String[] supportedCipherSuites = sSLSocket.getSupportedCipherSuites();
        l lVar = m.f15740c;
        byte[] bArr = ib.c.f16268a;
        int length = supportedCipherSuites.length;
        while (true) {
            if (i10 >= length) {
                i10 = -1;
                break;
            }
            if (lVar.compare(supportedCipherSuites[i10], "TLS_FALLBACK_SCSV") == 0) {
                break;
            }
            i10++;
        }
        if (z5 && i10 != -1) {
            String str = supportedCipherSuites[i10];
            strArrP = (String[]) Arrays.copyOf(strArrP, strArrP.length + 1);
            strArrP[strArrP.length - 1] = str;
        }
        g4 g4Var = new g4();
        g4Var.f1589a = pVar.f15776a;
        g4Var.f1591c = strArr2;
        g4Var.f1592d = strArr;
        g4Var.f1590b = pVar.f15777b;
        g4Var.c((String[]) Arrays.copyOf(strArrP, strArrP.length));
        g4Var.e((String[]) Arrays.copyOf(strArrP2, strArrP2.length));
        p pVarA = g4Var.a();
        if (pVarA.c() != null) {
            sSLSocket.setEnabledProtocols(pVarA.f15779d);
        }
        if (pVarA.a() != null) {
            sSLSocket.setEnabledCipherSuites(pVarA.f15778c);
        }
        return pVar;
    }
}
