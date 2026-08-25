package j$.util.function;

import java.util.function.Function;

/* JADX INFO: loaded from: classes2.dex */
public final /* synthetic */ class d implements Function {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f18543a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ Function f18544b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final /* synthetic */ Function f18545c;

    public /* synthetic */ d(Function function, Function function2, int i10) {
        this.f18543a = i10;
        this.f18544b = function;
        this.f18545c = function2;
    }

    @Override // java.util.function.Function
    /* JADX INFO: renamed from: andThen */
    public final /* synthetic */ Function mo7027andThen(Function function) {
        switch (this.f18543a) {
        }
        return Function$CC.$default$andThen(this, function);
    }

    public final /* synthetic */ Function compose(Function function) {
        switch (this.f18543a) {
        }
        return Function$CC.$default$compose(this, function);
    }

    @Override // java.util.function.Function
    public final Object apply(Object obj) {
        switch (this.f18543a) {
            case 0:
                return this.f18545c.apply(this.f18544b.apply(obj));
            default:
                return this.f18544b.apply(this.f18545c.apply(obj));
        }
    }
}
