package o5;

import h.f0;

/* JADX INFO: loaded from: classes4.dex */
public final class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final String f20782a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final int f20783b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final String f20784c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final String f20785d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final long f20786e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final long f20787f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final String f20788g;

    public b(String str, int i10, String str2, String str3, long j10, long j11, String str4) {
        this.f20782a = str;
        this.f20783b = i10;
        this.f20784c = str2;
        this.f20785d = str3;
        this.f20786e = j10;
        this.f20787f = j11;
        this.f20788g = str4;
    }

    public final a a() {
        a aVar = new a();
        aVar.f20775b = this.f20782a;
        aVar.f20776c = this.f20783b;
        aVar.f20777d = this.f20784c;
        aVar.f20778e = this.f20785d;
        aVar.f20780g = Long.valueOf(this.f20786e);
        aVar.f20781h = Long.valueOf(this.f20787f);
        aVar.f20779f = this.f20788g;
        return aVar;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof b)) {
            return false;
        }
        b bVar = (b) obj;
        String str = bVar.f20788g;
        String str2 = bVar.f20785d;
        String str3 = bVar.f20784c;
        String str4 = bVar.f20782a;
        String str5 = this.f20782a;
        if (str5 == null) {
            if (str4 != null) {
                return false;
            }
        } else if (!str5.equals(str4)) {
            return false;
        }
        if (!f0.b(this.f20783b, bVar.f20783b)) {
            return false;
        }
        String str6 = this.f20784c;
        if (str6 == null) {
            if (str3 != null) {
                return false;
            }
        } else if (!str6.equals(str3)) {
            return false;
        }
        String str7 = this.f20785d;
        if (str7 == null) {
            if (str2 != null) {
                return false;
            }
        } else if (!str7.equals(str2)) {
            return false;
        }
        if (this.f20786e != bVar.f20786e || this.f20787f != bVar.f20787f) {
            return false;
        }
        String str8 = this.f20788g;
        return str8 == null ? str == null : str8.equals(str);
    }

    public final int hashCode() {
        String str = this.f20782a;
        int iHashCode = ((((str == null ? 0 : str.hashCode()) ^ 1000003) * 1000003) ^ f0.c(this.f20783b)) * 1000003;
        String str2 = this.f20784c;
        int iHashCode2 = (iHashCode ^ (str2 == null ? 0 : str2.hashCode())) * 1000003;
        String str3 = this.f20785d;
        int iHashCode3 = (iHashCode2 ^ (str3 == null ? 0 : str3.hashCode())) * 1000003;
        long j10 = this.f20786e;
        int i10 = (iHashCode3 ^ ((int) (j10 ^ (j10 >>> 32)))) * 1000003;
        long j11 = this.f20787f;
        int i11 = (i10 ^ ((int) (j11 ^ (j11 >>> 32)))) * 1000003;
        String str4 = this.f20788g;
        return (str4 != null ? str4.hashCode() : 0) ^ i11;
    }

    public final String toString() {
        StringBuilder sb2 = new StringBuilder("PersistedInstallationEntry{firebaseInstallationId=");
        sb2.append(this.f20782a);
        sb2.append(", registrationStatus=");
        int i10 = this.f20783b;
        sb2.append(i10 != 1 ? i10 != 2 ? i10 != 3 ? i10 != 4 ? i10 != 5 ? "null" : "REGISTER_ERROR" : "REGISTERED" : "UNREGISTERED" : "NOT_GENERATED" : "ATTEMPT_MIGRATION");
        sb2.append(", authToken=");
        sb2.append(this.f20784c);
        sb2.append(", refreshToken=");
        sb2.append(this.f20785d);
        sb2.append(", expiresInSecs=");
        sb2.append(this.f20786e);
        sb2.append(", tokenCreationEpochInSecs=");
        sb2.append(this.f20787f);
        sb2.append(", fisError=");
        return a0.c.p(sb2, this.f20788g, "}");
    }
}
