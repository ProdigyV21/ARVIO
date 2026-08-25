package retrofit2;

import j$.util.Objects;

/* JADX INFO: loaded from: classes5.dex */
public final class e0 extends b1 {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final String f21496b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final a f21497c;

    public e0(String str) {
        a aVar = a.f21467l;
        Objects.requireNonNull(str, "name == null");
        this.f21496b = str;
        this.f21497c = aVar;
    }

    @Override // retrofit2.b1
    public final void a(o0 o0Var, Object obj) {
        if (obj == null) {
            return;
        }
        this.f21497c.getClass();
        String string = obj.toString();
        if (string == null) {
            return;
        }
        o0Var.b(this.f21496b, string);
    }
}
