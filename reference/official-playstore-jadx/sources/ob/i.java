package ob;

import java.io.IOException;

/* JADX INFO: loaded from: classes5.dex */
public final class i extends kb.a {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ int f20902e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final /* synthetic */ o f20903f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final /* synthetic */ int f20904g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final /* synthetic */ int f20905h;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ i(String str, o oVar, int i10, int i11, int i12) {
        super(str, true);
        this.f20902e = i12;
        this.f20903f = oVar;
        this.f20904g = i10;
        this.f20905h = i11;
    }

    @Override // kb.a
    public final long a() {
        switch (this.f20902e) {
            case 0:
                o oVar = this.f20903f;
                try {
                    oVar.H.x(this.f20904g, this.f20905h, true);
                } catch (IOException e5) {
                    oVar.i(2, 2, e5);
                    return -1L;
                }
                break;
            default:
                o oVar2 = this.f20903f;
                try {
                    oVar2.H.y(this.f20904g, this.f20905h);
                } catch (IOException e6) {
                    oVar2.i(2, 2, e6);
                    return -1L;
                }
                break;
        }
        return -1L;
    }
}
