package w5;

/* JADX INFO: loaded from: classes4.dex */
public final class h implements com.google.firebase.encoders.d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final h f22437a = new h();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final com.google.firebase.encoders.c f22438b = com.google.firebase.encoders.c.a("sessionId");

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final com.google.firebase.encoders.c f22439c = com.google.firebase.encoders.c.a("firstSessionId");

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final com.google.firebase.encoders.c f22440d = com.google.firebase.encoders.c.a("sessionIndex");

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final com.google.firebase.encoders.c f22441e = com.google.firebase.encoders.c.a("eventTimestampUs");

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final com.google.firebase.encoders.c f22442f = com.google.firebase.encoders.c.a("dataCollectionStatus");

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final com.google.firebase.encoders.c f22443g = com.google.firebase.encoders.c.a("firebaseInstallationId");

    @Override // com.google.firebase.encoders.d
    public final void encode(Object obj, Object obj2) {
        i0 i0Var = (i0) obj;
        com.google.firebase.encoders.e eVar = (com.google.firebase.encoders.e) obj2;
        eVar.f(f22438b, i0Var.f22454a);
        eVar.f(f22439c, i0Var.f22455b);
        eVar.c(f22440d, i0Var.f22456c);
        eVar.b(f22441e, i0Var.f22457d);
        eVar.f(f22442f, i0Var.f22458e);
        eVar.f(f22443g, i0Var.f22459f);
    }
}
