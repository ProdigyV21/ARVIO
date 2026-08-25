package p5;

import h.f0;

/* JADX INFO: loaded from: classes4.dex */
public final class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final String f21053a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final String f21054b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final String f21055c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final b f21056d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final int f21057e;

    public a(String str, String str2, String str3, b bVar, int i10) {
        this.f21053a = str;
        this.f21054b = str2;
        this.f21055c = str3;
        this.f21056d = bVar;
        this.f21057e = i10;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof a)) {
            return false;
        }
        a aVar = (a) obj;
        int i10 = aVar.f21057e;
        b bVar = aVar.f21056d;
        String str = aVar.f21055c;
        String str2 = aVar.f21054b;
        String str3 = aVar.f21053a;
        String str4 = this.f21053a;
        if (str4 == null) {
            if (str3 != null) {
                return false;
            }
        } else if (!str4.equals(str3)) {
            return false;
        }
        String str5 = this.f21054b;
        if (str5 == null) {
            if (str2 != null) {
                return false;
            }
        } else if (!str5.equals(str2)) {
            return false;
        }
        String str6 = this.f21055c;
        if (str6 == null) {
            if (str != null) {
                return false;
            }
        } else if (!str6.equals(str)) {
            return false;
        }
        b bVar2 = this.f21056d;
        if (bVar2 == null) {
            if (bVar != null) {
                return false;
            }
        } else if (!bVar2.equals(bVar)) {
            return false;
        }
        int i11 = this.f21057e;
        return i11 == 0 ? i10 == 0 : f0.b(i11, i10);
    }

    public final int hashCode() {
        String str = this.f21053a;
        int iHashCode = ((str == null ? 0 : str.hashCode()) ^ 1000003) * 1000003;
        String str2 = this.f21054b;
        int iHashCode2 = (iHashCode ^ (str2 == null ? 0 : str2.hashCode())) * 1000003;
        String str3 = this.f21055c;
        int iHashCode3 = (iHashCode2 ^ (str3 == null ? 0 : str3.hashCode())) * 1000003;
        b bVar = this.f21056d;
        int iHashCode4 = (iHashCode3 ^ (bVar == null ? 0 : bVar.hashCode())) * 1000003;
        int i10 = this.f21057e;
        return (i10 != 0 ? f0.c(i10) : 0) ^ iHashCode4;
    }

    public final String toString() {
        StringBuilder sb2 = new StringBuilder("InstallationResponse{uri=");
        sb2.append(this.f21053a);
        sb2.append(", fid=");
        sb2.append(this.f21054b);
        sb2.append(", refreshToken=");
        sb2.append(this.f21055c);
        sb2.append(", authToken=");
        sb2.append(this.f21056d);
        sb2.append(", responseCode=");
        int i10 = this.f21057e;
        sb2.append(i10 != 1 ? i10 != 2 ? "null" : "BAD_CONFIG" : "OK");
        sb2.append("}");
        return sb2.toString();
    }
}
