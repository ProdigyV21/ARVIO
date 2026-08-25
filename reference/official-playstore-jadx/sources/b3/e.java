package b3;

/* JADX INFO: loaded from: classes4.dex */
public final class e implements com.google.firebase.encoders.d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final e f7143a = new e();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final com.google.firebase.encoders.c f7144b = com.google.firebase.encoders.c.a("eventTimeMs");

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final com.google.firebase.encoders.c f7145c = com.google.firebase.encoders.c.a("eventCode");

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final com.google.firebase.encoders.c f7146d = com.google.firebase.encoders.c.a("eventUptimeMs");

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final com.google.firebase.encoders.c f7147e = com.google.firebase.encoders.c.a("sourceExtension");

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final com.google.firebase.encoders.c f7148f = com.google.firebase.encoders.c.a("sourceExtensionJsonProto3");

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final com.google.firebase.encoders.c f7149g = com.google.firebase.encoders.c.a("timezoneOffsetSeconds");

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public static final com.google.firebase.encoders.c f7150h = com.google.firebase.encoders.c.a("networkConnectionInfo");

    @Override // com.google.firebase.encoders.d
    public final void encode(Object obj, Object obj2) {
        com.google.firebase.encoders.e eVar = (com.google.firebase.encoders.e) obj2;
        l lVar = (l) ((s) obj);
        eVar.b(f7144b, lVar.f7176a);
        eVar.f(f7145c, lVar.f7177b);
        eVar.b(f7146d, lVar.f7178c);
        eVar.f(f7147e, lVar.f7179d);
        eVar.f(f7148f, lVar.f7180e);
        eVar.b(f7149g, lVar.f7181f);
        eVar.f(f7150h, lVar.f7182g);
    }
}
