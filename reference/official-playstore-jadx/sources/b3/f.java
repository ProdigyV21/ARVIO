package b3;

/* JADX INFO: loaded from: classes4.dex */
public final class f implements com.google.firebase.encoders.d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final f f7151a = new f();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final com.google.firebase.encoders.c f7152b = com.google.firebase.encoders.c.a("requestTimeMs");

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final com.google.firebase.encoders.c f7153c = com.google.firebase.encoders.c.a("requestUptimeMs");

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final com.google.firebase.encoders.c f7154d = com.google.firebase.encoders.c.a("clientInfo");

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final com.google.firebase.encoders.c f7155e = com.google.firebase.encoders.c.a("logSource");

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final com.google.firebase.encoders.c f7156f = com.google.firebase.encoders.c.a("logSourceName");

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final com.google.firebase.encoders.c f7157g = com.google.firebase.encoders.c.a("logEvent");

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public static final com.google.firebase.encoders.c f7158h = com.google.firebase.encoders.c.a("qosTier");

    @Override // com.google.firebase.encoders.d
    public final void encode(Object obj, Object obj2) {
        com.google.firebase.encoders.e eVar = (com.google.firebase.encoders.e) obj2;
        m mVar = (m) ((t) obj);
        eVar.b(f7152b, mVar.f7183a);
        eVar.b(f7153c, mVar.f7184b);
        eVar.f(f7154d, mVar.f7185c);
        eVar.f(f7155e, mVar.f7186d);
        eVar.f(f7156f, mVar.f7187e);
        eVar.f(f7157g, mVar.f7188f);
        eVar.f(f7158h, x.f7198i);
    }
}
