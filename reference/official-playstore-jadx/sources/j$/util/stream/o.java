package j$.util.stream;

import j$.util.Optional;
import j$.util.function.Predicate$CC;
import java.util.function.BiConsumer;
import java.util.function.DoubleBinaryOperator;
import java.util.function.DoubleFunction;
import java.util.function.IntBinaryOperator;
import java.util.function.IntFunction;
import java.util.function.ObjDoubleConsumer;
import java.util.function.ObjIntConsumer;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.function.ToDoubleFunction;
import java.util.function.ToIntFunction;

/* JADX INFO: loaded from: classes2.dex */
public final /* synthetic */ class o implements ObjDoubleConsumer, BiConsumer, DoubleFunction, ToDoubleFunction, IntFunction, DoubleBinaryOperator, Supplier, Predicate, ToIntFunction, IntBinaryOperator, ObjIntConsumer {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f18810a;

    public /* synthetic */ Predicate and(Predicate predicate) {
        switch (this.f18810a) {
        }
        return Predicate$CC.$default$and(this, predicate);
    }

    public /* synthetic */ BiConsumer andThen(BiConsumer biConsumer) {
        switch (this.f18810a) {
            case 1:
                break;
            case 3:
                break;
            case 24:
                break;
        }
        return j$.com.android.tools.r8.a.b(this, biConsumer);
    }

    @Override // java.util.function.DoubleFunction
    public Object apply(double d4) {
        return Double.valueOf(d4);
    }

    @Override // java.util.function.DoubleBinaryOperator
    public double applyAsDouble(double d4, double d10) {
        return Math.max(d4, d10);
    }

    @Override // java.util.function.IntBinaryOperator
    public int applyAsInt(int i10, int i11) {
        switch (this.f18810a) {
            case 22:
                return Math.min(i10, i11);
            case 25:
                return i10 + i11;
            default:
                return Math.max(i10, i11);
        }
    }

    public /* synthetic */ Predicate negate() {
        switch (this.f18810a) {
        }
        return Predicate$CC.$default$negate(this);
    }

    public /* synthetic */ Predicate or(Predicate predicate) {
        switch (this.f18810a) {
        }
        return Predicate$CC.$default$or(this, predicate);
    }

    @Override // java.util.function.Predicate
    public boolean test(Object obj) {
        switch (this.f18810a) {
            case 10:
                return ((j$.util.l) obj).f18569a;
            case 11:
            case 13:
            default:
                return ((Optional) obj).isPresent();
            case 12:
                return ((j$.util.m) obj).f18573a;
            case 14:
                return ((j$.util.n) obj).f18577a;
        }
    }

    @Override // java.util.function.ToDoubleFunction
    public double applyAsDouble(Object obj) {
        return ((Double) obj).doubleValue();
    }

    @Override // java.util.function.ObjDoubleConsumer
    public void accept(Object obj, double d4) {
        switch (this.f18810a) {
            case 0:
                double[] dArr = (double[]) obj;
                dArr[2] = dArr[2] + 1.0d;
                Collectors.a(dArr, d4);
                dArr[3] = dArr[3] + d4;
                break;
            case 1:
            default:
                double[] dArr2 = (double[]) obj;
                Collectors.a(dArr2, d4);
                dArr2[2] = dArr2[2] + d4;
                break;
            case 2:
                ((j$.util.h) obj).accept(d4);
                break;
        }
    }

    @Override // java.util.function.ToIntFunction
    public int applyAsInt(Object obj) {
        return ((Integer) obj).intValue();
    }

    @Override // java.util.function.Supplier
    public Object get() {
        switch (this.f18810a) {
            case 8:
                return new double[3];
            case 11:
                return new f0();
            case 13:
                return new g0();
            case 15:
                return new h0();
            case 17:
                return new i0();
            default:
                return new long[2];
        }
    }

    @Override // java.util.function.ObjIntConsumer
    public void accept(Object obj, int i10) {
        switch (this.f18810a) {
            case 23:
                ((j$.util.i) obj).accept(i10);
                break;
            default:
                long[] jArr = (long[]) obj;
                jArr[0] = jArr[0] + 1;
                jArr[1] = jArr[1] + ((long) i10);
                break;
        }
    }

    @Override // java.util.function.BiConsumer
    public void accept(Object obj, Object obj2) {
        switch (this.f18810a) {
            case 1:
                double[] dArr = (double[]) obj;
                double[] dArr2 = (double[]) obj2;
                Collectors.a(dArr, dArr2[0]);
                Collectors.a(dArr, dArr2[1]);
                dArr[2] = dArr[2] + dArr2[2];
                dArr[3] = dArr[3] + dArr2[3];
                break;
            case 3:
                ((j$.util.h) obj).a((j$.util.h) obj2);
                break;
            case 24:
                ((j$.util.i) obj).a((j$.util.i) obj2);
                break;
            default:
                long[] jArr = (long[]) obj;
                long[] jArr2 = (long[]) obj2;
                jArr[0] = jArr[0] + jArr2[0];
                jArr[1] = jArr[1] + jArr2[1];
                break;
        }
    }

    @Override // java.util.function.IntFunction
    public Object apply(int i10) {
        switch (this.f18810a) {
            case 6:
                return new Double[i10];
            case 18:
                return new Object[i10];
            case 19:
                return new Integer[i10];
            default:
                return Integer.valueOf(i10);
        }
    }
}
