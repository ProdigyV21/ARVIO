package androidx.compose.foundation.lazy.layout;

import androidx.compose.foundation.lazy.layout.IntervalList;
import androidx.compose.foundation.lazy.layout.LazyLayoutIntervalContent.Interval;
import io.ktor.http.ContentDisposition;
import io.ktor.http.LinkHeader;
import kotlin.Metadata;
import r7.l;
import r7.p;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0007\b'\u0018\u0000*\b\b\u0000\u0010\u0002*\u00020\u00012\u00020\u0003:\u0001\u0002B\u0007¢\u0006\u0004\b\u0004\u0010\u0005J\u0015\u0010\b\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\u0006¢\u0006\u0004\b\b\u0010\tJ\u0017\u0010\n\u001a\u0004\u0018\u00010\u00032\u0006\u0010\u0007\u001a\u00020\u0006¢\u0006\u0004\b\n\u0010\tJV\u0010\u0013\u001a\u00028\u0001\"\u0004\b\u0001\u0010\u000b2\u0006\u0010\f\u001a\u00020\u000626\u0010\u0012\u001a2\u0012\u0013\u0012\u00110\u0006¢\u0006\f\b\u000e\u0012\b\b\u000f\u0012\u0004\b\b(\u0010\u0012\u0013\u0012\u00118\u0000¢\u0006\f\b\u000e\u0012\b\b\u000f\u0012\u0004\b\b(\u0011\u0012\u0004\u0012\u00028\u00010\rH\u0086\b¢\u0006\u0004\b\u0013\u0010\u0014R\u001a\u0010\u0018\u001a\b\u0012\u0004\u0012\u00028\u00000\u00158&X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0016\u0010\u0017R\u0011\u0010\u001b\u001a\u00020\u00068F¢\u0006\u0006\u001a\u0004\b\u0019\u0010\u001a¨\u0006\u001c"}, d2 = {"Landroidx/compose/foundation/lazy/layout/LazyLayoutIntervalContent;", "Landroidx/compose/foundation/lazy/layout/LazyLayoutIntervalContent$Interval;", "Interval", "", "<init>", "()V", "", "index", "getKey", "(I)Ljava/lang/Object;", "getContentType", "T", "globalIndex", "Lkotlin/Function2;", "Lx6/y;", ContentDisposition.Parameters.Name, "localIntervalIndex", "content", "block", "withInterval", "(ILr7/p;)Ljava/lang/Object;", "Landroidx/compose/foundation/lazy/layout/IntervalList;", "getIntervals", "()Landroidx/compose/foundation/lazy/layout/IntervalList;", "intervals", "getItemCount", "()I", "itemCount", "foundation_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public abstract class LazyLayoutIntervalContent<Interval extends Interval> {
    public static final int $stable = 0;

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\b\b\bg\u0018\u00002\u00020\u0001R1\u0010\t\u001a\u001f\u0012\u0013\u0012\u00110\u0003¢\u0006\f\b\u0004\u0012\b\b\u0005\u0012\u0004\b\b(\u0006\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u00028VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0007\u0010\bR1\u0010\u000b\u001a\u001f\u0012\u0013\u0012\u00110\u0003¢\u0006\f\b\u0004\u0012\b\b\u0005\u0012\u0004\b\b(\u0006\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u00028VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\n\u0010\bø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\fÀ\u0006\u0001"}, d2 = {"Landroidx/compose/foundation/lazy/layout/LazyLayoutIntervalContent$Interval;", "", "Lkotlin/Function1;", "", "Lx6/y;", ContentDisposition.Parameters.Name, "index", "getKey", "()Lr7/l;", "key", "getType", LinkHeader.Parameters.Type, "foundation_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public interface Interval {

        /* JADX INFO: renamed from: androidx.compose.foundation.lazy.layout.LazyLayoutIntervalContent$Interval$-CC, reason: invalid class name */
        public abstract /* synthetic */ class CC {
            public static l a(Interval interval) {
                return null;
            }

            public static l b(Interval interval) {
                return LazyLayoutIntervalContent$Interval$type$1.INSTANCE;
            }
        }

        l<Integer, Object> getKey();

        l<Integer, Object> getType();
    }

    public final Object getContentType(int index) {
        IntervalList.Interval<Interval> interval = getIntervals().get(index);
        return interval.getValue().getType().invoke(Integer.valueOf(index - interval.getStartIndex()));
    }

    public abstract IntervalList<Interval> getIntervals();

    public final int getItemCount() {
        return getIntervals().getSize();
    }

    public final Object getKey(int index) {
        Object objInvoke;
        IntervalList.Interval<Interval> interval = getIntervals().get(index);
        int startIndex = index - interval.getStartIndex();
        l<Integer, Object> key = interval.getValue().getKey();
        return (key == null || (objInvoke = key.invoke(Integer.valueOf(startIndex))) == null) ? Lazy_androidKt.getDefaultLazyLayoutKey(index) : objInvoke;
    }

    public final <T> T withInterval(int globalIndex, p<? super Integer, ? super Interval, ? extends T> block) {
        IntervalList.Interval<Interval> interval = getIntervals().get(globalIndex);
        return (T) block.invoke(Integer.valueOf(globalIndex - interval.getStartIndex()), interval.getValue());
    }
}
