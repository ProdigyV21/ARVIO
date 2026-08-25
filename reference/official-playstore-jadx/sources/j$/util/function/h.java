package j$.util.function;

import java.util.function.Predicate;

/* JADX INFO: loaded from: classes2.dex */
public final /* synthetic */ class h implements Predicate {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f18553a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ Predicate f18554b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final /* synthetic */ Predicate f18555c;

    public /* synthetic */ h(Predicate predicate, Predicate predicate2, int i10) {
        this.f18553a = i10;
        this.f18554b = predicate;
        this.f18555c = predicate2;
    }

    public final /* synthetic */ Predicate and(Predicate predicate) {
        switch (this.f18553a) {
        }
        return Predicate$CC.$default$and(this, predicate);
    }

    public final /* synthetic */ Predicate negate() {
        switch (this.f18553a) {
        }
        return Predicate$CC.$default$negate(this);
    }

    public final /* synthetic */ Predicate or(Predicate predicate) {
        switch (this.f18553a) {
        }
        return Predicate$CC.$default$or(this, predicate);
    }

    @Override // java.util.function.Predicate
    public final boolean test(Object obj) {
        switch (this.f18553a) {
            case 0:
                return this.f18554b.test(obj) && this.f18555c.test(obj);
            default:
                return this.f18554b.test(obj) || this.f18555c.test(obj);
        }
    }
}
