package v9;

import java.util.ArrayList;
import java.util.Map;

/* JADX INFO: loaded from: classes5.dex */
public final class h0 extends p0 {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final /* synthetic */ int f22213c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final /* synthetic */ Object f22214d;

    public /* synthetic */ h0(Object obj, int i10) {
        this.f22213c = i10;
        this.f22214d = obj;
    }

    @Override // v9.v0
    public boolean a() {
        switch (this.f22213c) {
            case 1:
                return false;
            default:
                return super.a();
        }
    }

    @Override // v9.v0
    public boolean e() {
        switch (this.f22213c) {
            case 1:
                return ((Map) this.f22214d).isEmpty();
            default:
                return super.e();
        }
    }

    @Override // v9.p0
    public final r0 g(o0 o0Var) {
        switch (this.f22213c) {
            case 0:
                if (((ArrayList) this.f22214d).contains(o0Var)) {
                    return b1.j((g8.b1) o0Var.e());
                }
                return null;
            default:
                return (r0) ((Map) this.f22214d).get(o0Var);
        }
    }
}
