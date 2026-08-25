package gb;

import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public final class x extends kotlin.jvm.internal.r implements r7.a {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f15830i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ List f15831l;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ x(List list, int i10) {
        super(0);
        this.f15830i = i10;
        this.f15831l = list;
    }

    @Override // r7.a
    public final Object invoke() {
        switch (this.f15830i) {
        }
        return ((kotlin.reflect.q) this.f15831l.get(0)).b();
    }
}
