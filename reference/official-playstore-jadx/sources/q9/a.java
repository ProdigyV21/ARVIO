package q9;

import androidx.appcompat.app.o0;
import g8.l;
import j8.p;
import v9.w;

/* JADX INFO: loaded from: classes5.dex */
public final class a extends o0 implements d {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ int f21355m = 1;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final f9.f f21356n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final l f21357o;

    /* JADX WARN: Multi-variable type inference failed */
    public a(g8.a aVar, w wVar, f9.f fVar, f fVar2) {
        super(wVar, fVar2);
        this.f21357o = (p) aVar;
        this.f21356n = fVar;
    }

    @Override // q9.d
    public final f9.f a() {
        switch (this.f21355m) {
        }
        return this.f21356n;
    }

    public final String toString() {
        switch (this.f21355m) {
            case 0:
                return getType() + ": Ctx { " + ((g8.f) this.f21357o) + " }";
            default:
                return "Cxt { " + ((p) this.f21357o) + " }";
        }
    }

    public a(g8.f fVar, w wVar, f9.f fVar2) {
        super(wVar, null);
        this.f21357o = fVar;
        this.f21356n = fVar2;
    }
}
