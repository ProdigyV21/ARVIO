package f8;

import java.util.Collections;
import java.util.List;
import kotlin.jvm.internal.r;

/* JADX INFO: loaded from: classes5.dex */
public final class k extends r implements r7.a {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f15329i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ n f15330l;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ k(n nVar, int i10) {
        super(0);
        this.f15329i = i10;
        this.f15330l = nVar;
    }

    @Override // r7.a
    public final Object invoke() {
        switch (this.f15329i) {
            case 0:
                return this.f15330l.f15335a.j().e();
            default:
                List listSingletonList = Collections.singletonList(h8.e.a(this.f15330l.f15335a.j(), "This member is not fully supported by Kotlin compiler, so it may be absent or have different signature in next major version", null, 6));
                return listSingletonList.isEmpty() ? h8.g.f15863a : new h8.i(listSingletonList, 0);
        }
    }
}
