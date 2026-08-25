package b3;

import java.util.Arrays;

/* JADX INFO: loaded from: classes4.dex */
public final class l extends s {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final long f7176a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Integer f7177b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final long f7178c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final byte[] f7179d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final String f7180e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final long f7181f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final w f7182g;

    public l(long j10, Integer num, long j11, byte[] bArr, String str, long j12, w wVar) {
        this.f7176a = j10;
        this.f7177b = num;
        this.f7178c = j11;
        this.f7179d = bArr;
        this.f7180e = str;
        this.f7181f = j12;
        this.f7182g = wVar;
    }

    public final boolean equals(Object obj) {
        Integer num;
        String str;
        w wVar;
        if (obj == this) {
            return true;
        }
        if (obj instanceof s) {
            s sVar = (s) obj;
            l lVar = (l) sVar;
            w wVar2 = lVar.f7182g;
            String str2 = lVar.f7180e;
            Integer num2 = lVar.f7177b;
            if (this.f7176a == lVar.f7176a && ((num = this.f7177b) != null ? num.equals(num2) : num2 == null) && this.f7178c == lVar.f7178c) {
                if (Arrays.equals(this.f7179d, sVar instanceof l ? ((l) sVar).f7179d : lVar.f7179d) && ((str = this.f7180e) != null ? str.equals(str2) : str2 == null) && this.f7181f == lVar.f7181f && ((wVar = this.f7182g) != null ? wVar.equals(wVar2) : wVar2 == null)) {
                    return true;
                }
            }
        }
        return false;
    }

    public final int hashCode() {
        long j10 = this.f7176a;
        int i10 = (((int) (j10 ^ (j10 >>> 32))) ^ 1000003) * 1000003;
        Integer num = this.f7177b;
        int iHashCode = (i10 ^ (num == null ? 0 : num.hashCode())) * 1000003;
        long j11 = this.f7178c;
        int iHashCode2 = (((iHashCode ^ ((int) (j11 ^ (j11 >>> 32)))) * 1000003) ^ Arrays.hashCode(this.f7179d)) * 1000003;
        String str = this.f7180e;
        int iHashCode3 = (iHashCode2 ^ (str == null ? 0 : str.hashCode())) * 1000003;
        long j12 = this.f7181f;
        int i11 = (iHashCode3 ^ ((int) (j12 ^ (j12 >>> 32)))) * 1000003;
        w wVar = this.f7182g;
        return i11 ^ (wVar != null ? wVar.hashCode() : 0);
    }

    public final String toString() {
        return "LogEvent{eventTimeMs=" + this.f7176a + ", eventCode=" + this.f7177b + ", eventUptimeMs=" + this.f7178c + ", sourceExtension=" + Arrays.toString(this.f7179d) + ", sourceExtensionJsonProto3=" + this.f7180e + ", timezoneOffsetSeconds=" + this.f7181f + ", networkConnectionInfo=" + this.f7182g + "}";
    }
}
