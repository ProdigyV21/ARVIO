package eb;

import java.util.LinkedHashMap;
import kotlin.NoWhenBranchMatchedException;

/* JADX INFO: loaded from: classes5.dex */
public final class z extends u {

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public String f15196h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public boolean f15197i;

    @Override // eb.u
    public final db.m I() {
        return new db.b0((LinkedHashMap) this.f15182g);
    }

    @Override // eb.u
    public final void L(db.m mVar, String str) {
        if (!this.f15197i) {
            LinkedHashMap linkedHashMap = (LinkedHashMap) this.f15182g;
            String str2 = this.f15196h;
            if (str2 == null) {
                kotlin.jvm.internal.p.i("tag");
                throw null;
            }
            linkedHashMap.put(str2, mVar);
            this.f15197i = true;
            return;
        }
        if (mVar instanceof db.g0) {
            this.f15196h = ((db.g0) mVar).b();
            this.f15197i = false;
        } else {
            if (mVar instanceof db.b0) {
                throw r.b(db.e0.f14891b);
            }
            if (!(mVar instanceof db.d)) {
                throw new NoWhenBranchMatchedException();
            }
            throw r.b(db.f.f14893b);
        }
    }
}
