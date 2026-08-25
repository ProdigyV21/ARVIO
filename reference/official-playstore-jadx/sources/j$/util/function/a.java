package j$.util.function;

import java.util.Comparator;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.function.Function;

/* JADX INFO: loaded from: classes2.dex */
public final /* synthetic */ class a implements BinaryOperator {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f18536a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ Comparator f18537b;

    public /* synthetic */ a(Comparator comparator, int i10) {
        this.f18536a = i10;
        this.f18537b = comparator;
    }

    public final /* synthetic */ BiFunction andThen(Function function) {
        switch (this.f18536a) {
        }
        return BiFunction$CC.$default$andThen(this, function);
    }

    @Override // java.util.function.BiFunction
    public final Object apply(Object obj, Object obj2) {
        switch (this.f18536a) {
            case 0:
                if (this.f18537b.compare(obj, obj2) < 0) {
                    break;
                }
                break;
            default:
                if (this.f18537b.compare(obj, obj2) > 0) {
                    break;
                }
                break;
        }
        return obj2;
    }
}
