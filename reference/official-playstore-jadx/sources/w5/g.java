package w5;

/* JADX INFO: loaded from: classes4.dex */
public final class g implements com.google.firebase.encoders.d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final g f22432a = new g();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final com.google.firebase.encoders.c f22433b = com.google.firebase.encoders.c.a("eventType");

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final com.google.firebase.encoders.c f22434c = com.google.firebase.encoders.c.a("sessionData");

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final com.google.firebase.encoders.c f22435d = com.google.firebase.encoders.c.a("applicationInfo");

    @Override // com.google.firebase.encoders.d
    public final void encode(Object obj, Object obj2) {
        z zVar = (z) obj;
        com.google.firebase.encoders.e eVar = (com.google.firebase.encoders.e) obj2;
        zVar.getClass();
        eVar.f(f22433b, l.SESSION_START);
        eVar.f(f22434c, zVar.f22502a);
        eVar.f(f22435d, zVar.f22503b);
    }
}
