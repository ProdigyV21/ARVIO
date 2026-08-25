package androidx.compose.ui.input.pointer;

import androidx.compose.ui.geometry.Offset;
import kotlin.Metadata;
import kotlin.jvm.internal.h;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\t\b\u0007\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u0006\u0010\u0007B!\b\u0010\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\b\u001a\u00020\u0004¢\u0006\u0004\b\u0006\u0010\tJ\u000f\u0010\u000b\u001a\u00020\nH\u0016¢\u0006\u0004\b\u000b\u0010\fR\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\r\u001a\u0004\b\u000e\u0010\u000fR\u001d\u0010\u0005\u001a\u00020\u00048\u0006ø\u0001\u0000ø\u0001\u0001¢\u0006\f\n\u0004\b\u0005\u0010\r\u001a\u0004\b\u0010\u0010\u000fR*\u0010\b\u001a\u00020\u00042\u0006\u0010\u0011\u001a\u00020\u00048\u0000@BX\u0080\u000eø\u0001\u0000ø\u0001\u0001¢\u0006\f\n\u0004\b\b\u0010\r\u001a\u0004\b\u0012\u0010\u000f\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u0006\u0013"}, d2 = {"Landroidx/compose/ui/input/pointer/HistoricalChange;", "", "", "uptimeMillis", "Landroidx/compose/ui/geometry/Offset;", "position", "<init>", "(JJLkotlin/jvm/internal/h;)V", "originalEventPosition", "(JJJLkotlin/jvm/internal/h;)V", "", "toString", "()Ljava/lang/String;", "J", "getUptimeMillis", "()J", "getPosition-F1C5BW0", "<set-?>", "getOriginalEventPosition-F1C5BW0$ui_release", "ui_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class HistoricalChange {
    public static final int $stable = 0;
    private long originalEventPosition;
    private final long position;
    private final long uptimeMillis;

    public /* synthetic */ HistoricalChange(long j10, long j11, long j12, h hVar) {
        this(j10, j11, j12);
    }

    /* JADX INFO: renamed from: getOriginalEventPosition-F1C5BW0$ui_release, reason: not valid java name and from getter */
    public final long getOriginalEventPosition() {
        return this.originalEventPosition;
    }

    /* JADX INFO: renamed from: getPosition-F1C5BW0, reason: not valid java name and from getter */
    public final long getPosition() {
        return this.position;
    }

    public final long getUptimeMillis() {
        return this.uptimeMillis;
    }

    public String toString() {
        return "HistoricalChange(uptimeMillis=" + this.uptimeMillis + ", position=" + ((Object) Offset.m3244toStringimpl(this.position)) + ')';
    }

    public /* synthetic */ HistoricalChange(long j10, long j11, h hVar) {
        this(j10, j11);
    }

    private HistoricalChange(long j10, long j11) {
        this.uptimeMillis = j10;
        this.position = j11;
        this.originalEventPosition = Offset.INSTANCE.m3252getZeroF1C5BW0();
    }

    private HistoricalChange(long j10, long j11, long j12) {
        this(j10, j11, (h) null);
        this.originalEventPosition = j12;
    }
}
