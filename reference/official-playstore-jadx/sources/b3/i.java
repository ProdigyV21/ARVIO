package b3;

/* JADX INFO: loaded from: classes4.dex */
public final class i extends a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Integer f7163a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final String f7164b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final String f7165c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final String f7166d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final String f7167e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final String f7168f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final String f7169g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final String f7170h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final String f7171i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final String f7172j;
    public final String k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final String f7173l;

    public i(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, String str10, String str11, Integer num) {
        this.f7163a = num;
        this.f7164b = str;
        this.f7165c = str2;
        this.f7166d = str3;
        this.f7167e = str4;
        this.f7168f = str5;
        this.f7169g = str6;
        this.f7170h = str7;
        this.f7171i = str8;
        this.f7172j = str9;
        this.k = str10;
        this.f7173l = str11;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof a) {
            a aVar = (a) obj;
            Integer num = this.f7163a;
            if (num != null ? num.equals(((i) aVar).f7163a) : ((i) aVar).f7163a == null) {
                String str = this.f7164b;
                if (str != null ? str.equals(((i) aVar).f7164b) : ((i) aVar).f7164b == null) {
                    String str2 = this.f7165c;
                    if (str2 != null ? str2.equals(((i) aVar).f7165c) : ((i) aVar).f7165c == null) {
                        String str3 = this.f7166d;
                        if (str3 != null ? str3.equals(((i) aVar).f7166d) : ((i) aVar).f7166d == null) {
                            String str4 = this.f7167e;
                            if (str4 != null ? str4.equals(((i) aVar).f7167e) : ((i) aVar).f7167e == null) {
                                String str5 = this.f7168f;
                                if (str5 != null ? str5.equals(((i) aVar).f7168f) : ((i) aVar).f7168f == null) {
                                    String str6 = this.f7169g;
                                    if (str6 != null ? str6.equals(((i) aVar).f7169g) : ((i) aVar).f7169g == null) {
                                        String str7 = this.f7170h;
                                        if (str7 != null ? str7.equals(((i) aVar).f7170h) : ((i) aVar).f7170h == null) {
                                            String str8 = this.f7171i;
                                            if (str8 != null ? str8.equals(((i) aVar).f7171i) : ((i) aVar).f7171i == null) {
                                                String str9 = this.f7172j;
                                                if (str9 != null ? str9.equals(((i) aVar).f7172j) : ((i) aVar).f7172j == null) {
                                                    String str10 = this.k;
                                                    if (str10 != null ? str10.equals(((i) aVar).k) : ((i) aVar).k == null) {
                                                        String str11 = this.f7173l;
                                                        if (str11 != null ? str11.equals(((i) aVar).f7173l) : ((i) aVar).f7173l == null) {
                                                            return true;
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

    public final int hashCode() {
        Integer num = this.f7163a;
        int iHashCode = ((num == null ? 0 : num.hashCode()) ^ 1000003) * 1000003;
        String str = this.f7164b;
        int iHashCode2 = (iHashCode ^ (str == null ? 0 : str.hashCode())) * 1000003;
        String str2 = this.f7165c;
        int iHashCode3 = (iHashCode2 ^ (str2 == null ? 0 : str2.hashCode())) * 1000003;
        String str3 = this.f7166d;
        int iHashCode4 = (iHashCode3 ^ (str3 == null ? 0 : str3.hashCode())) * 1000003;
        String str4 = this.f7167e;
        int iHashCode5 = (iHashCode4 ^ (str4 == null ? 0 : str4.hashCode())) * 1000003;
        String str5 = this.f7168f;
        int iHashCode6 = (iHashCode5 ^ (str5 == null ? 0 : str5.hashCode())) * 1000003;
        String str6 = this.f7169g;
        int iHashCode7 = (iHashCode6 ^ (str6 == null ? 0 : str6.hashCode())) * 1000003;
        String str7 = this.f7170h;
        int iHashCode8 = (iHashCode7 ^ (str7 == null ? 0 : str7.hashCode())) * 1000003;
        String str8 = this.f7171i;
        int iHashCode9 = (iHashCode8 ^ (str8 == null ? 0 : str8.hashCode())) * 1000003;
        String str9 = this.f7172j;
        int iHashCode10 = (iHashCode9 ^ (str9 == null ? 0 : str9.hashCode())) * 1000003;
        String str10 = this.k;
        int iHashCode11 = (iHashCode10 ^ (str10 == null ? 0 : str10.hashCode())) * 1000003;
        String str11 = this.f7173l;
        return (str11 != null ? str11.hashCode() : 0) ^ iHashCode11;
    }

    public final String toString() {
        StringBuilder sb2 = new StringBuilder("AndroidClientInfo{sdkVersion=");
        sb2.append(this.f7163a);
        sb2.append(", model=");
        sb2.append(this.f7164b);
        sb2.append(", hardware=");
        sb2.append(this.f7165c);
        sb2.append(", device=");
        sb2.append(this.f7166d);
        sb2.append(", product=");
        sb2.append(this.f7167e);
        sb2.append(", osBuild=");
        sb2.append(this.f7168f);
        sb2.append(", manufacturer=");
        sb2.append(this.f7169g);
        sb2.append(", fingerprint=");
        sb2.append(this.f7170h);
        sb2.append(", locale=");
        sb2.append(this.f7171i);
        sb2.append(", country=");
        sb2.append(this.f7172j);
        sb2.append(", mccMnc=");
        sb2.append(this.k);
        sb2.append(", applicationBuild=");
        return a0.c.p(sb2, this.f7173l, "}");
    }
}
