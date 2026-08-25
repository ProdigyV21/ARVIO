package j$.time;

import j$.time.chrono.Chronology;
import j$.time.format.DateTimeFormatterBuilder;
import j$.time.temporal.Temporal;
import j$.time.temporal.TemporalAccessor;
import j$.util.function.BiFunction$CC;
import j$.util.function.Function$CC;
import j$.util.stream.Collectors;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.function.DoubleBinaryOperator;
import java.util.function.Function;
import java.util.function.IntFunction;
import java.util.function.Supplier;

/* JADX INFO: loaded from: classes2.dex */
public final /* synthetic */ class c implements j$.time.temporal.l, IntFunction, Supplier, BiConsumer, BinaryOperator, Function, DoubleBinaryOperator {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f18133a;

    public /* synthetic */ c(int i10) {
        this.f18133a = i10;
    }

    public /* synthetic */ BiConsumer andThen(BiConsumer biConsumer) {
        switch (this.f18133a) {
            case 16:
                break;
            case 19:
                break;
            case 25:
                break;
            case 26:
                break;
        }
        return j$.com.android.tools.r8.a.b(this, biConsumer);
    }

    public /* synthetic */ BiFunction andThen(Function function) {
        switch (this.f18133a) {
        }
        return BiFunction$CC.$default$andThen(this, function);
    }

    @Override // java.util.function.Function
    /* JADX INFO: renamed from: andThen, reason: collision with other method in class */
    public /* synthetic */ Function mo7027andThen(Function function) {
        return Function$CC.$default$andThen(this, function);
    }

    @Override // java.util.function.Function
    public Object apply(Object obj) {
        Set set = Collectors.f18598a;
        return obj;
    }

    @Override // java.util.function.DoubleBinaryOperator
    public double applyAsDouble(double d4, double d10) {
        return Math.min(d4, d10);
    }

    public /* synthetic */ Function compose(Function function) {
        return Function$CC.$default$compose(this, function);
    }

    @Override // j$.time.temporal.l
    public Temporal r(Temporal temporal) {
        j$.time.temporal.a aVar = j$.time.temporal.a.DAY_OF_MONTH;
        return temporal.c(temporal.n(aVar).f18376d, aVar);
    }

    @Override // java.util.function.BiFunction
    public Object apply(Object obj, Object obj2) {
        switch (this.f18133a) {
            case 17:
                Collection collection = (Collection) obj;
                Set set = Collectors.f18598a;
                collection.addAll((Collection) obj2);
                return collection;
            default:
                List list = (List) obj;
                Set set2 = Collectors.f18598a;
                list.addAll((List) obj2);
                return list;
        }
    }

    @Override // java.util.function.BiConsumer
    public void accept(Object obj, Object obj2) {
        switch (this.f18133a) {
            case 16:
                ((Collection) obj).add(obj2);
                break;
            case 19:
                ((List) obj).add(obj2);
                break;
            case 25:
                ((LinkedHashSet) obj).add(obj2);
                break;
            case 26:
                ((LinkedHashSet) obj).addAll((LinkedHashSet) obj2);
                break;
            default:
                double[] dArr = (double[]) obj;
                double[] dArr2 = (double[]) obj2;
                Collectors.a(dArr, dArr2[0]);
                Collectors.a(dArr, dArr2[1]);
                dArr[2] = dArr[2] + dArr2[2];
                break;
        }
    }

    public Object h(TemporalAccessor temporalAccessor) {
        int i10 = this.f18133a;
        c cVar = j$.time.temporal.p.f18366a;
        switch (i10) {
            case 0:
                return Instant.V(temporalAccessor);
            case 1:
                return LocalDate.W(temporalAccessor);
            case 2:
                return LocalDateTime.V(temporalAccessor);
            case 3:
                return OffsetDateTime.U(temporalAccessor);
            case 4:
                return ZonedDateTime.U(temporalAccessor);
            case 5:
                c cVar2 = DateTimeFormatterBuilder.f18205h;
                ZoneId zoneId = (ZoneId) temporalAccessor.G(cVar);
                if (zoneId == null || (zoneId instanceof ZoneOffset)) {
                    return null;
                }
                return zoneId;
            case 6:
            default:
                j$.time.temporal.a aVar = j$.time.temporal.a.NANO_OF_DAY;
                if (temporalAccessor.e(aVar)) {
                    return LocalTime.Z(temporalAccessor.H(aVar));
                }
                return null;
            case 7:
                return (ZoneId) temporalAccessor.G(cVar);
            case 8:
                return (Chronology) temporalAccessor.G(j$.time.temporal.p.f18367b);
            case 9:
                return (j$.time.temporal.q) temporalAccessor.G(j$.time.temporal.p.f18368c);
            case 10:
                j$.time.temporal.a aVar2 = j$.time.temporal.a.OFFSET_SECONDS;
                if (temporalAccessor.e(aVar2)) {
                    return ZoneOffset.d0(temporalAccessor.l(aVar2));
                }
                return null;
            case 11:
                ZoneId zoneId2 = (ZoneId) temporalAccessor.G(cVar);
                return zoneId2 != null ? zoneId2 : (ZoneId) temporalAccessor.G(j$.time.temporal.p.f18369d);
            case 12:
                j$.time.temporal.a aVar3 = j$.time.temporal.a.EPOCH_DAY;
                if (temporalAccessor.e(aVar3)) {
                    return LocalDate.e0(temporalAccessor.H(aVar3));
                }
                return null;
        }
    }

    public String toString() {
        switch (this.f18133a) {
            case 7:
                return "ZoneId";
            case 8:
                return "Chronology";
            case 9:
                return "Precision";
            case 10:
                return "ZoneOffset";
            case 11:
                return "Zone";
            case 12:
                return "LocalDate";
            case 13:
                return "LocalTime";
            default:
                return super.toString();
        }
    }

    @Override // java.util.function.Supplier
    public Object get() {
        switch (this.f18133a) {
            case 15:
                return new j$.util.h();
            case 18:
                return new ArrayList();
            case 20:
                return new j$.util.i();
            case 21:
                return new j$.util.k();
            case 24:
                return new LinkedHashSet();
            default:
                return new double[4];
        }
    }

    @Override // java.util.function.IntFunction
    public Object apply(int i10) {
        return new Object[i10];
    }
}
