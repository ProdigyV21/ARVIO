package w5;

/* JADX INFO: loaded from: classes4.dex */
public final class e implements com.google.firebase.encoders.d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final e f22412a = new e();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final com.google.firebase.encoders.c f22413b = com.google.firebase.encoders.c.a("performance");

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final com.google.firebase.encoders.c f22414c = com.google.firebase.encoders.c.a("crashlytics");

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final com.google.firebase.encoders.c f22415d = com.google.firebase.encoders.c.a("sessionSamplingRate");

    @Override // com.google.firebase.encoders.d
    public final void encode(Object obj, Object obj2) {
        j jVar = (j) obj;
        com.google.firebase.encoders.e eVar = (com.google.firebase.encoders.e) obj2;
        eVar.f(f22413b, jVar.f22460a);
        eVar.f(f22414c, jVar.f22461b);
        eVar.d(f22415d, jVar.f22462c);
    }
}
