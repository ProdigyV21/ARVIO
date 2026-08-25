package b3;

/* JADX INFO: loaded from: classes4.dex */
public final class b implements com.google.firebase.encoders.d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final b f7126a = new b();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final com.google.firebase.encoders.c f7127b = com.google.firebase.encoders.c.a("sdkVersion");

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final com.google.firebase.encoders.c f7128c = com.google.firebase.encoders.c.a("model");

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final com.google.firebase.encoders.c f7129d = com.google.firebase.encoders.c.a("hardware");

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final com.google.firebase.encoders.c f7130e = com.google.firebase.encoders.c.a("device");

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final com.google.firebase.encoders.c f7131f = com.google.firebase.encoders.c.a("product");

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final com.google.firebase.encoders.c f7132g = com.google.firebase.encoders.c.a("osBuild");

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public static final com.google.firebase.encoders.c f7133h = com.google.firebase.encoders.c.a("manufacturer");

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public static final com.google.firebase.encoders.c f7134i = com.google.firebase.encoders.c.a("fingerprint");

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public static final com.google.firebase.encoders.c f7135j = com.google.firebase.encoders.c.a("locale");
    public static final com.google.firebase.encoders.c k = com.google.firebase.encoders.c.a("country");

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public static final com.google.firebase.encoders.c f7136l = com.google.firebase.encoders.c.a("mccMnc");

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public static final com.google.firebase.encoders.c f7137m = com.google.firebase.encoders.c.a("applicationBuild");

    @Override // com.google.firebase.encoders.d
    public final void encode(Object obj, Object obj2) {
        com.google.firebase.encoders.e eVar = (com.google.firebase.encoders.e) obj2;
        i iVar = (i) ((a) obj);
        eVar.f(f7127b, iVar.f7163a);
        eVar.f(f7128c, iVar.f7164b);
        eVar.f(f7129d, iVar.f7165c);
        eVar.f(f7130e, iVar.f7166d);
        eVar.f(f7131f, iVar.f7167e);
        eVar.f(f7132g, iVar.f7168f);
        eVar.f(f7133h, iVar.f7169g);
        eVar.f(f7134i, iVar.f7170h);
        eVar.f(f7135j, iVar.f7171i);
        eVar.f(k, iVar.f7172j);
        eVar.f(f7136l, iVar.k);
        eVar.f(f7137m, iVar.f7173l);
    }
}
