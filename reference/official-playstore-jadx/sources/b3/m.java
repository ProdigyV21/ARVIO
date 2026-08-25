package b3;

import java.util.ArrayList;

/* JADX INFO: loaded from: classes4.dex */
public final class m extends t {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final long f7183a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final long f7184b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final k f7185c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final Integer f7186d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final String f7187e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final ArrayList f7188f;

    public m(long j10, long j11, k kVar, Integer num, String str, ArrayList arrayList) {
        x xVar = x.f7198i;
        this.f7183a = j10;
        this.f7184b = j11;
        this.f7185c = kVar;
        this.f7186d = num;
        this.f7187e = str;
        this.f7188f = arrayList;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof t)) {
            return false;
        }
        m mVar = (m) ((t) obj);
        Object obj2 = x.f7198i;
        ArrayList arrayList = mVar.f7188f;
        String str = mVar.f7187e;
        Integer num = mVar.f7186d;
        k kVar = mVar.f7185c;
        if (this.f7183a != mVar.f7183a || this.f7184b != mVar.f7184b || !this.f7185c.equals(kVar)) {
            return false;
        }
        Integer num2 = this.f7186d;
        if (num2 == null) {
            if (num != null) {
                return false;
            }
        } else if (!num2.equals(num)) {
            return false;
        }
        String str2 = this.f7187e;
        if (str2 == null) {
            if (str != null) {
                return false;
            }
        } else if (!str2.equals(str)) {
            return false;
        }
        return this.f7188f.equals(arrayList) && obj2.equals(obj2);
    }

    public final int hashCode() {
        long j10 = this.f7183a;
        long j11 = this.f7184b;
        int iHashCode = (((((((int) (j10 ^ (j10 >>> 32))) ^ 1000003) * 1000003) ^ ((int) (j11 ^ (j11 >>> 32)))) * 1000003) ^ this.f7185c.hashCode()) * 1000003;
        Integer num = this.f7186d;
        int iHashCode2 = (iHashCode ^ (num == null ? 0 : num.hashCode())) * 1000003;
        String str = this.f7187e;
        return ((((iHashCode2 ^ (str != null ? str.hashCode() : 0)) * 1000003) ^ this.f7188f.hashCode()) * 1000003) ^ x.f7198i.hashCode();
    }

    public final String toString() {
        return "LogRequest{requestTimeMs=" + this.f7183a + ", requestUptimeMs=" + this.f7184b + ", clientInfo=" + this.f7185c + ", logSource=" + this.f7186d + ", logSourceName=" + this.f7187e + ", logEvents=" + this.f7188f + ", qosTier=" + x.f7198i + "}";
    }
}
