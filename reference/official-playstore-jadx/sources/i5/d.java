package i5;

import com.google.firebase.encoders.g;
import java.util.Date;
import java.util.HashMap;

/* JADX INFO: loaded from: classes4.dex */
public final class d implements h5.b {

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final b f16219f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final b f16220g;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final HashMap f16222a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final HashMap f16223b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final a f16224c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public boolean f16225d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final a f16218e = new a(0);

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public static final c f16221h = new c();

    /* JADX WARN: Type inference failed for: r0v1, types: [i5.b] */
    /* JADX WARN: Type inference failed for: r0v2, types: [i5.b] */
    static {
        final int i10 = 0;
        f16219f = new com.google.firebase.encoders.f() { // from class: i5.b
            @Override // com.google.firebase.encoders.f
            public final void encode(Object obj, Object obj2) {
                switch (i10) {
                    case 0:
                        ((g) obj2).e((String) obj);
                        break;
                    default:
                        ((g) obj2).g(((Boolean) obj).booleanValue());
                        break;
                }
            }
        };
        final int i11 = 1;
        f16220g = new com.google.firebase.encoders.f() { // from class: i5.b
            @Override // com.google.firebase.encoders.f
            public final void encode(Object obj, Object obj2) {
                switch (i11) {
                    case 0:
                        ((g) obj2).e((String) obj);
                        break;
                    default:
                        ((g) obj2).g(((Boolean) obj).booleanValue());
                        break;
                }
            }
        };
    }

    public d() {
        HashMap map = new HashMap();
        this.f16222a = map;
        HashMap map2 = new HashMap();
        this.f16223b = map2;
        this.f16224c = f16218e;
        this.f16225d = false;
        map2.put(String.class, f16219f);
        map.remove(String.class);
        map2.put(Boolean.class, f16220g);
        map.remove(Boolean.class);
        map2.put(Date.class, f16221h);
        map.remove(Date.class);
    }

    @Override // h5.b
    public final h5.b a(Class cls, com.google.firebase.encoders.d dVar) {
        this.f16222a.put(cls, dVar);
        this.f16223b.remove(cls);
        return this;
    }
}
