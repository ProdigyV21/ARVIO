package ob;

import java.io.IOException;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public final class k extends kb.a {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ int f20910e = 1;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final /* synthetic */ o f20911f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final /* synthetic */ int f20912g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final /* synthetic */ List f20913h;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public k(String str, o oVar, int i10, List list) {
        super(str, true);
        this.f20911f = oVar;
        this.f20912g = i10;
        this.f20913h = list;
    }

    private final long b() {
        this.f20911f.f20932u.getClass();
        try {
            this.f20911f.H.y(this.f20912g, 9);
            synchronized (this.f20911f) {
                this.f20911f.J.remove(Integer.valueOf(this.f20912g));
            }
            return -1L;
        } catch (IOException unused) {
            return -1L;
        }
    }

    @Override // kb.a
    public final long a() {
        switch (this.f20910e) {
            case 0:
                return b();
            default:
                this.f20911f.f20932u.getClass();
                try {
                    this.f20911f.H.y(this.f20912g, 9);
                    synchronized (this.f20911f) {
                        this.f20911f.J.remove(Integer.valueOf(this.f20912g));
                    }
                    return -1L;
                } catch (IOException unused) {
                    return -1L;
                }
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public k(String str, o oVar, int i10, List list, boolean z) {
        super(str, true);
        this.f20911f = oVar;
        this.f20912g = i10;
        this.f20913h = list;
    }
}
