package ob;

import java.io.IOException;

/* JADX INFO: loaded from: classes5.dex */
public final class n extends kb.a {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ o f20919e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final /* synthetic */ int f20920f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final /* synthetic */ long f20921g;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public n(String str, o oVar, int i10, long j10) {
        super(str, true);
        this.f20919e = oVar;
        this.f20920f = i10;
        this.f20921g = j10;
    }

    @Override // kb.a
    public final long a() {
        o oVar = this.f20919e;
        try {
            oVar.H.O(this.f20920f, this.f20921g);
            return -1L;
        } catch (IOException e5) {
            oVar.i(2, 2, e5);
            return -1L;
        }
    }
}
