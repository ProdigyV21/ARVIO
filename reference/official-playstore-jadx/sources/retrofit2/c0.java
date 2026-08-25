package retrofit2;

import j$.util.Objects;

/* JADX INFO: loaded from: classes5.dex */
public final class c0 extends b1 {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ int f21483b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final String f21484c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final a f21485d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final boolean f21486e;

    public c0(String str, boolean z, int i10) {
        this.f21483b = i10;
        switch (i10) {
            case 1:
                a aVar = a.f21467l;
                Objects.requireNonNull(str, "name == null");
                this.f21484c = str;
                this.f21485d = aVar;
                this.f21486e = z;
                break;
            default:
                a aVar2 = a.f21467l;
                Objects.requireNonNull(str, "name == null");
                this.f21484c = str;
                this.f21485d = aVar2;
                this.f21486e = z;
                break;
        }
    }

    @Override // retrofit2.b1
    public final void a(o0 o0Var, Object obj) {
        switch (this.f21483b) {
            case 0:
                if (obj != null) {
                    this.f21485d.getClass();
                    String string = obj.toString();
                    if (string != null) {
                        o0Var.a(this.f21484c, string, this.f21486e);
                        break;
                    }
                }
                break;
            default:
                if (obj != null) {
                    this.f21485d.getClass();
                    String string2 = obj.toString();
                    if (string2 != null) {
                        o0Var.d(this.f21484c, string2, this.f21486e);
                        break;
                    }
                }
                break;
        }
    }
}
