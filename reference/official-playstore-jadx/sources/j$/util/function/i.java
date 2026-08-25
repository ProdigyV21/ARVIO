package j$.util.function;

import j$.util.Spliterator;
import j$.util.stream.IntStream;
import j$.util.stream.Stream;
import j$.util.stream.b0;
import j$.util.stream.c0;
import j$.util.stream.c8;
import j$.util.stream.d0;
import j$.util.stream.j7;
import j$.util.stream.k1;
import j$.util.stream.l1;
import j$.util.stream.l5;
import j$.util.stream.l7;
import j$.util.stream.m1;
import j$.util.stream.n7;
import j$.util.stream.w6;
import j$.util.stream.x6;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.function.BooleanSupplier;
import java.util.function.Consumer;
import java.util.function.DoubleFunction;
import java.util.function.Function;
import java.util.function.LongFunction;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.DoubleStream;
import java.util.stream.LongStream;

/* JADX INFO: loaded from: classes2.dex */
public final /* synthetic */ class i implements Predicate, Supplier, DoubleFunction, Function, LongFunction, Consumer, BooleanSupplier {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f18556a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public Object f18557b;

    public /* synthetic */ i(int i10) {
        this.f18556a = i10;
    }

    public /* synthetic */ i(Object obj, int i10) {
        this.f18556a = i10;
        this.f18557b = obj;
    }

    @Override // java.util.function.Consumer
    /* JADX INFO: renamed from: accept */
    public void n(Object obj) {
        switch (this.f18556a) {
            case 6:
                ((l5) this.f18557b).n(obj);
                break;
            default:
                ((List) this.f18557b).add(obj);
                break;
        }
    }

    public /* synthetic */ Predicate and(Predicate predicate) {
        return Predicate$CC.$default$and(this, predicate);
    }

    public /* synthetic */ Consumer andThen(Consumer consumer) {
        switch (this.f18556a) {
        }
        return Consumer$CC.$default$andThen(this, consumer);
    }

    @Override // java.util.function.Function
    /* JADX INFO: renamed from: andThen */
    public /* synthetic */ Function mo7027andThen(Function function) {
        return Function$CC.$default$andThen(this, function);
    }

    public /* synthetic */ Function compose(Function function) {
        return Function$CC.$default$compose(this, function);
    }

    public /* synthetic */ Predicate negate() {
        return Predicate$CC.$default$negate(this);
    }

    public /* synthetic */ Predicate or(Predicate predicate) {
        return Predicate$CC.$default$or(this, predicate);
    }

    @Override // java.util.function.Predicate
    public boolean test(Object obj) {
        return !((Predicate) this.f18557b).test(obj);
    }

    @Override // java.util.function.Function
    public Object apply(Object obj) {
        Object objApply = ((Function) this.f18557b).apply(obj);
        if (objApply == null) {
            return null;
        }
        if (objApply instanceof Stream) {
            return Stream.Wrapper.convert((Stream) objApply);
        }
        if (objApply instanceof java.util.stream.Stream) {
            return w6.e((java.util.stream.Stream) objApply);
        }
        if (objApply instanceof IntStream) {
            return IntStream.Wrapper.convert((IntStream) objApply);
        }
        if (objApply instanceof java.util.stream.IntStream) {
            return IntStream.VivifiedWrapper.convert((java.util.stream.IntStream) objApply);
        }
        if (objApply instanceof d0) {
            return c0.e((d0) objApply);
        }
        if (objApply instanceof DoubleStream) {
            return b0.e((DoubleStream) objApply);
        }
        if (objApply instanceof m1) {
            return l1.e((m1) objApply);
        }
        if (objApply instanceof LongStream) {
            return k1.e((LongStream) objApply);
        }
        j$.util.f.a(objApply.getClass(), "java.util.stream.*Stream");
        throw null;
    }

    @Override // java.util.function.DoubleFunction
    public Object apply(double d4) {
        Object objApply = ((DoubleFunction) this.f18557b).apply(d4);
        if (objApply == null) {
            return null;
        }
        if (objApply instanceof d0) {
            return c0.e((d0) objApply);
        }
        if (objApply instanceof DoubleStream) {
            return b0.e((DoubleStream) objApply);
        }
        j$.util.f.a(objApply.getClass(), "java.util.stream.DoubleStream");
        throw null;
    }

    @Override // java.util.function.LongFunction
    public Object apply(long j10) {
        Object objApply = ((LongFunction) this.f18557b).apply(j10);
        if (objApply == null) {
            return null;
        }
        if (objApply instanceof m1) {
            return l1.e((m1) objApply);
        }
        if (objApply instanceof LongStream) {
            return k1.e((LongStream) objApply);
        }
        j$.util.f.a(objApply.getClass(), "java.util.stream.LongStream");
        throw null;
    }

    @Override // java.util.function.BooleanSupplier
    public boolean getAsBoolean() {
        switch (this.f18556a) {
            case 9:
                j7 j7Var = (j7) this.f18557b;
                return j7Var.f18620d.tryAdvance(j7Var.f18621e);
            case 10:
                l7 l7Var = (l7) this.f18557b;
                return l7Var.f18620d.tryAdvance(l7Var.f18621e);
            case 11:
                n7 n7Var = (n7) this.f18557b;
                return n7Var.f18620d.tryAdvance(n7Var.f18621e);
            default:
                c8 c8Var = (c8) this.f18557b;
                return c8Var.f18620d.tryAdvance(c8Var.f18621e);
        }
    }

    @Override // java.util.function.Supplier
    public Object get() {
        switch (this.f18556a) {
            case 1:
                return ((j$.util.stream.a) this.f18557b).Q0(0);
            default:
                return (Spliterator) this.f18557b;
        }
    }

    public void a(x6 x6Var) {
        ((EnumMap) ((Map) this.f18557b)).put(x6Var, 1);
    }
}
