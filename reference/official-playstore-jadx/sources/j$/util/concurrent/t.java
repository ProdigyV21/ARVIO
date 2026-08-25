package j$.util.concurrent;

import j$.util.Spliterator;
import j$.util.function.BiFunction$CC;
import j$.util.function.Consumer$CC;
import j$.util.stream.e8;
import j$.util.stream.h7;
import j$.util.stream.o1;
import j$.util.stream.s1;
import j$.util.stream.t1;
import j$.util.stream.u1;
import j$.util.stream.v3;
import j$.util.stream.y6;
import j$.util.stream.z6;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/* JADX INFO: loaded from: classes2.dex */
public final /* synthetic */ class t implements BiConsumer, BiFunction, Consumer, Supplier, e8 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f18514a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Object f18515b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final Object f18516c;

    public /* synthetic */ t(int i10, Object obj, Object obj2) {
        this.f18514a = i10;
        this.f18515b = obj;
        this.f18516c = obj2;
    }

    public /* synthetic */ t(BiFunction biFunction, Function function) {
        this.f18514a = 2;
        this.f18516c = biFunction;
        this.f18515b = function;
    }

    public /* synthetic */ BiConsumer andThen(BiConsumer biConsumer) {
        switch (this.f18514a) {
        }
        return j$.com.android.tools.r8.a.b(this, biConsumer);
    }

    public /* synthetic */ BiFunction andThen(Function function) {
        return BiFunction$CC.$default$andThen(this, function);
    }

    public /* synthetic */ Consumer andThen(Consumer consumer) {
        switch (this.f18514a) {
        }
        return Consumer$CC.$default$andThen(this, consumer);
    }

    @Override // java.util.function.BiFunction
    public Object apply(Object obj, Object obj2) {
        return ((Function) this.f18515b).apply(((BiFunction) this.f18516c).apply(obj, obj2));
    }

    @Override // java.util.function.BiConsumer
    public void accept(Object obj, Object obj2) {
        switch (this.f18514a) {
            case 0:
                ConcurrentMap concurrentMap = (ConcurrentMap) this.f18515b;
                BiFunction biFunction = (BiFunction) this.f18516c;
                while (!concurrentMap.replace(obj, obj2, biFunction.apply(obj, obj2)) && (obj2 = concurrentMap.get(obj)) != null) {
                }
                break;
            default:
                BiConsumer biConsumer = (BiConsumer) this.f18515b;
                BiConsumer biConsumer2 = (BiConsumer) this.f18516c;
                biConsumer.accept(obj, obj2);
                biConsumer2.accept(obj, obj2);
                break;
        }
    }

    @Override // java.util.function.Supplier
    public Object get() {
        return new o1((t1) this.f18515b, (Predicate) this.f18516c);
    }

    public t(z6 z6Var, t1 t1Var, Supplier supplier) {
        this.f18514a = 6;
        this.f18515b = t1Var;
        this.f18516c = supplier;
    }

    @Override // j$.util.stream.e8
    public int t() {
        return y6.f18977u | y6.f18974r;
    }

    @Override // j$.util.stream.e8
    public Object e(j$.util.stream.a aVar, Spliterator spliterator) {
        s1 s1Var = (s1) ((Supplier) this.f18516c).get();
        aVar.E0(spliterator, s1Var);
        return Boolean.valueOf(s1Var.f18866b);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // j$.util.stream.e8
    public Object h(v3 v3Var, Spliterator spliterator) {
        return (Boolean) new u1(this, (j$.util.stream.a) v3Var, spliterator).invoke();
    }

    @Override // java.util.function.Consumer
    /* JADX INFO: renamed from: accept */
    public void n(Object obj) {
        switch (this.f18514a) {
            case 3:
                Consumer consumer = (Consumer) this.f18515b;
                Consumer consumer2 = (Consumer) this.f18516c;
                consumer.n(obj);
                consumer2.n(obj);
                break;
            case 4:
                AtomicBoolean atomicBoolean = (AtomicBoolean) this.f18515b;
                ConcurrentHashMap concurrentHashMap = (ConcurrentHashMap) this.f18516c;
                if (obj != null) {
                    concurrentHashMap.putIfAbsent(obj, Boolean.TRUE);
                } else {
                    atomicBoolean.set(true);
                }
                break;
            case 5:
            case 6:
            default:
                h7 h7Var = (h7) this.f18515b;
                Consumer consumer3 = (Consumer) this.f18516c;
                if (h7Var.f18715b.putIfAbsent(obj != null ? obj : h7.f18713d, Boolean.TRUE) == null) {
                    consumer3.n(obj);
                }
                break;
            case 7:
                ((BiConsumer) this.f18515b).accept(this.f18516c, obj);
                break;
        }
    }
}
