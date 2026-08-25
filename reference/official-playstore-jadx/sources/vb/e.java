package vb;

import java.io.IOException;

/* JADX INFO: loaded from: classes5.dex */
public final class e extends kb.a {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ int f22274e = 1;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final /* synthetic */ g f22275f;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public e(String str, g gVar) {
        super(str, true);
        this.f22275f = gVar;
    }

    @Override // kb.a
    public final long a() {
        switch (this.f22274e) {
            case 0:
                g gVar = this.f22275f;
                try {
                    if (gVar.m()) {
                    }
                } catch (IOException e5) {
                    gVar.i(e5, null);
                    break;
                }
                break;
            default:
                this.f22275f.f22285g.cancel();
                break;
        }
        return -1L;
        return -1L;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public e(g gVar) {
        super(a0.c.p(new StringBuilder(), gVar.f22289l, " writer"), true);
        this.f22275f = gVar;
    }
}
