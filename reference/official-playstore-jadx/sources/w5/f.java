package w5;

/* JADX INFO: loaded from: classes4.dex */
public final class f implements com.google.firebase.encoders.d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final f f22420a = new f();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final com.google.firebase.encoders.c f22421b = com.google.firebase.encoders.c.a("processName");

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final com.google.firebase.encoders.c f22422c = com.google.firebase.encoders.c.a("pid");

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final com.google.firebase.encoders.c f22423d = com.google.firebase.encoders.c.a("importance");

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final com.google.firebase.encoders.c f22424e = com.google.firebase.encoders.c.a("defaultProcess");

    @Override // com.google.firebase.encoders.d
    public final void encode(Object obj, Object obj2) {
        q qVar = (q) obj;
        com.google.firebase.encoders.e eVar = (com.google.firebase.encoders.e) obj2;
        eVar.f(f22421b, qVar.f22480a);
        eVar.c(f22422c, qVar.f22481b);
        eVar.c(f22423d, qVar.f22482c);
        eVar.a(f22424e, qVar.f22483d);
    }
}
