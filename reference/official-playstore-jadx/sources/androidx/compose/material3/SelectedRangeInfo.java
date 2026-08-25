package androidx.compose.material3;

import androidx.compose.ui.unit.IntOffsetKt;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u000e\b\u0000\u0018\u0000 \u00122\u00020\u0001:\u0001\u0012B'\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0002\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\u0005¢\u0006\u0004\b\b\u0010\tR\u001d\u0010\u0003\u001a\u00020\u00028\u0006ø\u0001\u0000ø\u0001\u0001¢\u0006\f\n\u0004\b\u0003\u0010\n\u001a\u0004\b\u000b\u0010\fR\u001d\u0010\u0004\u001a\u00020\u00028\u0006ø\u0001\u0000ø\u0001\u0001¢\u0006\f\n\u0004\b\u0004\u0010\n\u001a\u0004\b\r\u0010\fR\u0017\u0010\u0006\u001a\u00020\u00058\u0006¢\u0006\f\n\u0004\b\u0006\u0010\u000e\u001a\u0004\b\u000f\u0010\u0010R\u0017\u0010\u0007\u001a\u00020\u00058\u0006¢\u0006\f\n\u0004\b\u0007\u0010\u000e\u001a\u0004\b\u0011\u0010\u0010\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u0006\u0013"}, d2 = {"Landroidx/compose/material3/SelectedRangeInfo;", "", "Landroidx/compose/ui/unit/IntOffset;", "gridStartCoordinates", "gridEndCoordinates", "", "firstIsSelectionStart", "lastIsSelectionEnd", "<init>", "(JJZZLkotlin/jvm/internal/h;)V", "J", "getGridStartCoordinates-nOcc-ac", "()J", "getGridEndCoordinates-nOcc-ac", "Z", "getFirstIsSelectionStart", "()Z", "getLastIsSelectionEnd", "Companion", "material3_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class SelectedRangeInfo {
    public static final int $stable = 0;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final boolean firstIsSelectionStart;
    private final long gridEndCoordinates;
    private final long gridStartCoordinates;
    private final boolean lastIsSelectionEnd;

    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J \u0010\u0003\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\b¨\u0006\n"}, d2 = {"Landroidx/compose/material3/SelectedRangeInfo$Companion;", "", "()V", "calculateRangeInfo", "Landroidx/compose/material3/SelectedRangeInfo;", "month", "Landroidx/compose/material3/CalendarMonth;", "startDate", "Landroidx/compose/material3/CalendarDate;", "endDate", "material3_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(kotlin.jvm.internal.h hVar) {
            this();
        }

        public final SelectedRangeInfo calculateRangeInfo(CalendarMonth month, CalendarDate startDate, CalendarDate endDate) {
            if (startDate.getUtcTimeMillis() > month.getEndUtcTimeMillis() || endDate.getUtcTimeMillis() < month.getStartUtcTimeMillis()) {
                return null;
            }
            boolean z = startDate.getUtcTimeMillis() >= month.getStartUtcTimeMillis();
            boolean z5 = endDate.getUtcTimeMillis() <= month.getEndUtcTimeMillis();
            int dayOfMonth = z ? (startDate.getDayOfMonth() + month.getDaysFromStartOfWeekToFirstOfMonth()) - 1 : month.getDaysFromStartOfWeekToFirstOfMonth();
            int dayOfMonth2 = z5 ? (endDate.getDayOfMonth() + month.getDaysFromStartOfWeekToFirstOfMonth()) - 1 : (month.getNumberOfDays() + month.getDaysFromStartOfWeekToFirstOfMonth()) - 1;
            return new SelectedRangeInfo(IntOffsetKt.IntOffset(dayOfMonth % 7, dayOfMonth / 7), IntOffsetKt.IntOffset(dayOfMonth2 % 7, dayOfMonth2 / 7), z, z5, null);
        }

        private Companion() {
        }
    }

    public /* synthetic */ SelectedRangeInfo(long j10, long j11, boolean z, boolean z5, kotlin.jvm.internal.h hVar) {
        this(j10, j11, z, z5);
    }

    public final boolean getFirstIsSelectionStart() {
        return this.firstIsSelectionStart;
    }

    /* JADX INFO: renamed from: getGridEndCoordinates-nOcc-ac, reason: not valid java name and from getter */
    public final long getGridEndCoordinates() {
        return this.gridEndCoordinates;
    }

    /* JADX INFO: renamed from: getGridStartCoordinates-nOcc-ac, reason: not valid java name and from getter */
    public final long getGridStartCoordinates() {
        return this.gridStartCoordinates;
    }

    public final boolean getLastIsSelectionEnd() {
        return this.lastIsSelectionEnd;
    }

    private SelectedRangeInfo(long j10, long j11, boolean z, boolean z5) {
        this.gridStartCoordinates = j10;
        this.gridEndCoordinates = j11;
        this.firstIsSelectionStart = z;
        this.lastIsSelectionEnd = z5;
    }
}
