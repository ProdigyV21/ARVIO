package w5;

import android.os.Build;

/* JADX INFO: loaded from: classes4.dex */
public final class d implements com.google.firebase.encoders.d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final d f22394a = new d();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final com.google.firebase.encoders.c f22395b = com.google.firebase.encoders.c.a("appId");

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final com.google.firebase.encoders.c f22396c = com.google.firebase.encoders.c.a("deviceModel");

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final com.google.firebase.encoders.c f22397d = com.google.firebase.encoders.c.a("sessionSdkVersion");

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final com.google.firebase.encoders.c f22398e = com.google.firebase.encoders.c.a("osVersion");

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final com.google.firebase.encoders.c f22399f = com.google.firebase.encoders.c.a("logEnvironment");

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final com.google.firebase.encoders.c f22400g = com.google.firebase.encoders.c.a("androidAppInfo");

    @Override // com.google.firebase.encoders.d
    public final void encode(Object obj, Object obj2) {
        b bVar = (b) obj;
        com.google.firebase.encoders.e eVar = (com.google.firebase.encoders.e) obj2;
        eVar.f(f22395b, bVar.f22382a);
        eVar.f(f22396c, Build.MODEL);
        eVar.f(f22397d, "1.2.0");
        eVar.f(f22398e, Build.VERSION.RELEASE);
        eVar.f(f22399f, p.LOG_ENVIRONMENT_PROD);
        eVar.f(f22400g, bVar.f22383b);
    }
}
