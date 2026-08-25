package w5;

import android.os.Build;

/* JADX INFO: loaded from: classes4.dex */
public final class c implements com.google.firebase.encoders.d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final c f22384a = new c();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final com.google.firebase.encoders.c f22385b = com.google.firebase.encoders.c.a("packageName");

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final com.google.firebase.encoders.c f22386c = com.google.firebase.encoders.c.a("versionName");

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final com.google.firebase.encoders.c f22387d = com.google.firebase.encoders.c.a("appBuildVersion");

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final com.google.firebase.encoders.c f22388e = com.google.firebase.encoders.c.a("deviceManufacturer");

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final com.google.firebase.encoders.c f22389f = com.google.firebase.encoders.c.a("currentProcessDetails");

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final com.google.firebase.encoders.c f22390g = com.google.firebase.encoders.c.a("appProcessDetails");

    @Override // com.google.firebase.encoders.d
    public final void encode(Object obj, Object obj2) {
        a aVar = (a) obj;
        com.google.firebase.encoders.e eVar = (com.google.firebase.encoders.e) obj2;
        eVar.f(f22385b, aVar.f22375a);
        eVar.f(f22386c, aVar.f22376b);
        eVar.f(f22387d, aVar.f22377c);
        eVar.f(f22388e, Build.MANUFACTURER);
        eVar.f(f22389f, aVar.f22378d);
        eVar.f(f22390g, aVar.f22379e);
    }
}
