package j$.util.stream;

import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.function.ObjDoubleConsumer;
import java.util.function.ObjIntConsumer;
import java.util.function.ObjLongConsumer;
import java.util.function.Supplier;

/* JADX INFO: loaded from: classes2.dex */
public final class a4 extends v3 {

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final /* synthetic */ int f18613h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ Object f18614i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final /* synthetic */ Object f18615j;
    public final /* synthetic */ Object k;

    public /* synthetic */ a4(z6 z6Var, Object obj, Object obj2, Object obj3, int i10) {
        this.f18613h = i10;
        this.f18615j = obj;
        this.k = obj2;
        this.f18614i = obj3;
    }

    @Override // j$.util.stream.v3
    public final q4 D0() {
        switch (this.f18613h) {
            case 0:
                return new x3((Supplier) this.f18614i, (ObjLongConsumer) this.k, (p) this.f18615j);
            case 1:
                return new d4((Supplier) this.f18614i, (ObjDoubleConsumer) this.k, (p) this.f18615j);
            case 2:
                return new f4(this.f18614i, (BiFunction) this.k, (BinaryOperator) this.f18615j);
            case 3:
                return new j4((Supplier) this.f18614i, (BiConsumer) this.k, (BiConsumer) this.f18615j);
            default:
                return new n4((Supplier) this.f18614i, (ObjIntConsumer) this.k, (p) this.f18615j);
        }
    }
}
