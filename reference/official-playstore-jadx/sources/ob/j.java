package ob;

import java.io.IOException;

/* JADX INFO: loaded from: classes5.dex */
public final class j extends kb.a {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ o f20906e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final /* synthetic */ int f20907f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final /* synthetic */ xb.j f20908g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final /* synthetic */ int f20909h;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public j(String str, o oVar, int i10, xb.j jVar, int i11, boolean z) {
        super(str, true);
        this.f20906e = oVar;
        this.f20907f = i10;
        this.f20908g = jVar;
        this.f20909h = i11;
    }

    @Override // kb.a
    public final long a() {
        try {
            io.sentry.util.l lVar = this.f20906e.f20932u;
            xb.j jVar = this.f20908g;
            int i10 = this.f20909h;
            lVar.getClass();
            jVar.skip(i10);
            this.f20906e.H.y(this.f20907f, 9);
            synchronized (this.f20906e) {
                this.f20906e.J.remove(Integer.valueOf(this.f20907f));
            }
            return -1L;
        } catch (IOException unused) {
            return -1L;
        }
    }
}
