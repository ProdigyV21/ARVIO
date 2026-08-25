package gb;

import java.security.cert.Certificate;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public final class y {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final u0 f15832a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final m f15833b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final List f15834c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final x6.i0 f15835d;

    public y(u0 u0Var, m mVar, List list, r7.a aVar) {
        this.f15832a = u0Var;
        this.f15833b = mVar;
        this.f15834c = list;
        this.f15835d = new x6.i0(new a8.g0(aVar));
    }

    public final List a() {
        return (List) this.f15835d.getValue();
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof y)) {
            return false;
        }
        y yVar = (y) obj;
        return yVar.f15832a == this.f15832a && kotlin.jvm.internal.p.a(yVar.f15833b, this.f15833b) && kotlin.jvm.internal.p.a(yVar.a(), a()) && kotlin.jvm.internal.p.a(yVar.f15834c, this.f15834c);
    }

    public final int hashCode() {
        return this.f15834c.hashCode() + ((a().hashCode() + ((this.f15833b.hashCode() + ((this.f15832a.hashCode() + 527) * 31)) * 31)) * 31);
    }

    public final String toString() {
        List<Certificate> listA = a();
        ArrayList arrayList = new ArrayList(kotlin.collections.s.U(listA, 10));
        for (Certificate certificate : listA) {
            arrayList.add(certificate instanceof X509Certificate ? ((X509Certificate) certificate).getSubjectDN().toString() : certificate.getType());
        }
        String string = arrayList.toString();
        StringBuilder sb2 = new StringBuilder("Handshake{tlsVersion=");
        sb2.append(this.f15832a);
        sb2.append(" cipherSuite=");
        sb2.append(this.f15833b);
        sb2.append(" peerCertificates=");
        sb2.append(string);
        sb2.append(" localCertificates=");
        List<Certificate> list = this.f15834c;
        ArrayList arrayList2 = new ArrayList(kotlin.collections.s.U(list, 10));
        for (Certificate certificate2 : list) {
            arrayList2.add(certificate2 instanceof X509Certificate ? ((X509Certificate) certificate2).getSubjectDN().toString() : certificate2.getType());
        }
        sb2.append(arrayList2);
        sb2.append('}');
        return sb2.toString();
    }
}
