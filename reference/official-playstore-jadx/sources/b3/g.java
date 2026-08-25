package b3;

/* JADX INFO: loaded from: classes4.dex */
public final class g implements com.google.firebase.encoders.d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final g f7159a = new g();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final com.google.firebase.encoders.c f7160b = com.google.firebase.encoders.c.a("networkType");

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final com.google.firebase.encoders.c f7161c = com.google.firebase.encoders.c.a("mobileSubtype");

    @Override // com.google.firebase.encoders.d
    public final void encode(Object obj, Object obj2) {
        com.google.firebase.encoders.e eVar = (com.google.firebase.encoders.e) obj2;
        o oVar = (o) ((w) obj);
        eVar.f(f7160b, oVar.f7190a);
        eVar.f(f7161c, oVar.f7191b);
    }
}
