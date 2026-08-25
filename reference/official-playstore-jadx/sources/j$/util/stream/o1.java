package j$.util.stream;

import java.util.function.Predicate;

/* JADX INFO: loaded from: classes2.dex */
public final class o1 extends s1 {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final /* synthetic */ t1 f18812c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final /* synthetic */ Predicate f18813d;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public o1(t1 t1Var, Predicate predicate) {
        super(t1Var);
        this.f18812c = t1Var;
        this.f18813d = predicate;
    }

    @Override // java.util.function.Consumer
    public final void accept(Object obj) {
        if (this.f18865a) {
            return;
        }
        boolean zTest = this.f18813d.test(obj);
        t1 t1Var = this.f18812c;
        if (zTest == t1Var.f18880a) {
            this.f18865a = true;
            this.f18866b = t1Var.f18881b;
        }
    }
}
