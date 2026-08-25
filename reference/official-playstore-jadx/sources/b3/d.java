package b3;

/* JADX INFO: loaded from: classes4.dex */
public final class d implements com.google.firebase.encoders.d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final d f7140a = new d();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final com.google.firebase.encoders.c f7141b = com.google.firebase.encoders.c.a("clientType");

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final com.google.firebase.encoders.c f7142c = com.google.firebase.encoders.c.a("androidClientInfo");

    @Override // com.google.firebase.encoders.d
    public final void encode(Object obj, Object obj2) {
        com.google.firebase.encoders.e eVar = (com.google.firebase.encoders.e) obj2;
        k kVar = (k) ((r) obj);
        kVar.getClass();
        eVar.f(f7141b, q.f7192i);
        eVar.f(f7142c, kVar.f7175a);
    }
}
