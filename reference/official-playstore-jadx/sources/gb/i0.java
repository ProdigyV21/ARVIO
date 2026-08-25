package gb;

/* JADX INFO: loaded from: classes5.dex */
public enum i0 {
    HTTP_1_0("http/1.0"),
    HTTP_1_1("http/1.1"),
    SPDY_3("spdy/3.1"),
    HTTP_2("h2"),
    H2_PRIOR_KNOWLEDGE("h2_prior_knowledge"),
    QUIC("quic");


    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final String f15721i;

    i0(String str) {
        this.f15721i = str;
    }

    @Override // java.lang.Enum
    public final String toString() {
        return this.f15721i;
    }
}
