package androidx.compose.ui.input.pointer;

import androidx.compose.material3.d;
import androidx.compose.ui.geometry.Offset;
import androidx.media3.extractor.text.ttml.TtmlNode;
import io.ktor.http.LinkHeader;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.h;
import kotlin.jvm.internal.p;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u001e\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0015\b\u0080\b\u0018\u00002\u00020\u0001Bm\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u0012\u0006\u0010\b\u001a\u00020\u0006\u0012\u0006\u0010\n\u001a\u00020\t\u0012\u0006\u0010\f\u001a\u00020\u000b\u0012\u0006\u0010\u000e\u001a\u00020\r\u0012\b\b\u0002\u0010\u000f\u001a\u00020\t\u0012\u000e\b\u0002\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00110\u0010\u0012\b\b\u0002\u0010\u0013\u001a\u00020\u0006\u0012\b\b\u0002\u0010\u0014\u001a\u00020\u0006¢\u0006\u0004\b\u0015\u0010\u0016J\u0016\u0010\u0019\u001a\u00020\u0002HÆ\u0003ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u0017\u0010\u0018J\u0010\u0010\u001a\u001a\u00020\u0004HÆ\u0003¢\u0006\u0004\b\u001a\u0010\u0018J\u0016\u0010\u001c\u001a\u00020\u0006HÆ\u0003ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u001b\u0010\u0018J\u0016\u0010\u001e\u001a\u00020\u0006HÆ\u0003ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u001d\u0010\u0018J\u0010\u0010\u001f\u001a\u00020\tHÆ\u0003¢\u0006\u0004\b\u001f\u0010 J\u0010\u0010!\u001a\u00020\u000bHÆ\u0003¢\u0006\u0004\b!\u0010\"J\u0016\u0010%\u001a\u00020\rHÆ\u0003ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b#\u0010$J\u0010\u0010&\u001a\u00020\tHÆ\u0003¢\u0006\u0004\b&\u0010 J\u0016\u0010'\u001a\b\u0012\u0004\u0012\u00020\u00110\u0010HÆ\u0003¢\u0006\u0004\b'\u0010(J\u0016\u0010*\u001a\u00020\u0006HÆ\u0003ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b)\u0010\u0018J\u0016\u0010,\u001a\u00020\u0006HÆ\u0003ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b+\u0010\u0018J\u0087\u0001\u0010/\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\b\b\u0002\u0010\u0005\u001a\u00020\u00042\b\b\u0002\u0010\u0007\u001a\u00020\u00062\b\b\u0002\u0010\b\u001a\u00020\u00062\b\b\u0002\u0010\n\u001a\u00020\t2\b\b\u0002\u0010\f\u001a\u00020\u000b2\b\b\u0002\u0010\u000e\u001a\u00020\r2\b\b\u0002\u0010\u000f\u001a\u00020\t2\u000e\b\u0002\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00110\u00102\b\b\u0002\u0010\u0013\u001a\u00020\u00062\b\b\u0002\u0010\u0014\u001a\u00020\u0006HÆ\u0001ø\u0001\u0001¢\u0006\u0004\b-\u0010.J\u0010\u00101\u001a\u000200HÖ\u0001¢\u0006\u0004\b1\u00102J\u0010\u00104\u001a\u000203HÖ\u0001¢\u0006\u0004\b4\u0010$J\u001a\u00106\u001a\u00020\t2\b\u00105\u001a\u0004\u0018\u00010\u0001HÖ\u0003¢\u0006\u0004\b6\u00107R\u001d\u0010\u0003\u001a\u00020\u00028\u0006ø\u0001\u0001ø\u0001\u0000¢\u0006\f\n\u0004\b\u0003\u00108\u001a\u0004\b9\u0010\u0018R\u0017\u0010\u0005\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b\u0005\u00108\u001a\u0004\b:\u0010\u0018R\u001d\u0010\u0007\u001a\u00020\u00068\u0006ø\u0001\u0001ø\u0001\u0000¢\u0006\f\n\u0004\b\u0007\u00108\u001a\u0004\b;\u0010\u0018R\u001d\u0010\b\u001a\u00020\u00068\u0006ø\u0001\u0001ø\u0001\u0000¢\u0006\f\n\u0004\b\b\u00108\u001a\u0004\b<\u0010\u0018R\u0017\u0010\n\u001a\u00020\t8\u0006¢\u0006\f\n\u0004\b\n\u0010=\u001a\u0004\b>\u0010 R\u0017\u0010\f\u001a\u00020\u000b8\u0006¢\u0006\f\n\u0004\b\f\u0010?\u001a\u0004\b@\u0010\"R\u001d\u0010\u000e\u001a\u00020\r8\u0006ø\u0001\u0001ø\u0001\u0000¢\u0006\f\n\u0004\b\u000e\u0010A\u001a\u0004\bB\u0010$R\u0017\u0010\u000f\u001a\u00020\t8\u0006¢\u0006\f\n\u0004\b\u000f\u0010=\u001a\u0004\bC\u0010 R\u001d\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00110\u00108\u0006¢\u0006\f\n\u0004\b\u0012\u0010D\u001a\u0004\bE\u0010(R\u001d\u0010\u0013\u001a\u00020\u00068\u0006ø\u0001\u0001ø\u0001\u0000¢\u0006\f\n\u0004\b\u0013\u00108\u001a\u0004\bF\u0010\u0018R\u001d\u0010\u0014\u001a\u00020\u00068\u0006ø\u0001\u0001ø\u0001\u0000¢\u0006\f\n\u0004\b\u0014\u00108\u001a\u0004\bG\u0010\u0018\u0082\u0002\u000b\n\u0002\b!\n\u0005\b¡\u001e0\u0001¨\u0006H"}, d2 = {"Landroidx/compose/ui/input/pointer/PointerInputEventData;", "", "Landroidx/compose/ui/input/pointer/PointerId;", TtmlNode.ATTR_ID, "", "uptime", "Landroidx/compose/ui/geometry/Offset;", "positionOnScreen", "position", "", "down", "", "pressure", "Landroidx/compose/ui/input/pointer/PointerType;", LinkHeader.Parameters.Type, "issuesEnterExit", "", "Landroidx/compose/ui/input/pointer/HistoricalChange;", "historical", "scrollDelta", "originalEventPosition", "<init>", "(JJJJZFIZLjava/util/List;JJLkotlin/jvm/internal/h;)V", "component1-J3iCeTQ", "()J", "component1", "component2", "component3-F1C5BW0", "component3", "component4-F1C5BW0", "component4", "component5", "()Z", "component6", "()F", "component7-T8wyACA", "()I", "component7", "component8", "component9", "()Ljava/util/List;", "component10-F1C5BW0", "component10", "component11-F1C5BW0", "component11", "copy-rc8HELY", "(JJJJZFIZLjava/util/List;JJ)Landroidx/compose/ui/input/pointer/PointerInputEventData;", "copy", "", "toString", "()Ljava/lang/String;", "", "hashCode", "other", "equals", "(Ljava/lang/Object;)Z", "J", "getId-J3iCeTQ", "getUptime", "getPositionOnScreen-F1C5BW0", "getPosition-F1C5BW0", "Z", "getDown", "F", "getPressure", "I", "getType-T8wyACA", "getIssuesEnterExit", "Ljava/util/List;", "getHistorical", "getScrollDelta-F1C5BW0", "getOriginalEventPosition-F1C5BW0", "ui_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final /* data */ class PointerInputEventData {
    public static final int $stable = 8;
    private final boolean down;
    private final List<HistoricalChange> historical;
    private final long id;
    private final boolean issuesEnterExit;
    private final long originalEventPosition;
    private final long position;
    private final long positionOnScreen;
    private final float pressure;
    private final long scrollDelta;
    private final int type;
    private final long uptime;

    public /* synthetic */ PointerInputEventData(long j10, long j11, long j12, long j13, boolean z, float f10, int i10, boolean z5, List list, long j14, long j15, h hVar) {
        this(j10, j11, j12, j13, z, f10, i10, z5, list, j14, j15);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX INFO: renamed from: copy-rc8HELY$default, reason: not valid java name */
    public static /* synthetic */ PointerInputEventData m4525copyrc8HELY$default(PointerInputEventData pointerInputEventData, long j10, long j11, long j12, long j13, boolean z, float f10, int i10, boolean z5, List list, long j14, long j15, int i11, Object obj) {
        long j16;
        long j17;
        long j18;
        long j19;
        long j20 = (i11 & 1) != 0 ? pointerInputEventData.id : j10;
        long j21 = (i11 & 2) != 0 ? pointerInputEventData.uptime : j11;
        long j22 = (i11 & 4) != 0 ? pointerInputEventData.positionOnScreen : j12;
        long j23 = (i11 & 8) != 0 ? pointerInputEventData.position : j13;
        boolean z10 = (i11 & 16) != 0 ? pointerInputEventData.down : z;
        float f11 = (i11 & 32) != 0 ? pointerInputEventData.pressure : f10;
        int i12 = (i11 & 64) != 0 ? pointerInputEventData.type : i10;
        boolean z11 = (i11 & 128) != 0 ? pointerInputEventData.issuesEnterExit : z5;
        List list2 = (i11 & 256) != 0 ? pointerInputEventData.historical : list;
        if ((i11 & 512) != 0) {
            j16 = j20;
            j17 = pointerInputEventData.scrollDelta;
        } else {
            j16 = j20;
            j17 = j14;
        }
        if ((i11 & 1024) != 0) {
            j19 = j17;
            j18 = pointerInputEventData.originalEventPosition;
        } else {
            j18 = j15;
            j19 = j17;
        }
        return pointerInputEventData.m4532copyrc8HELY(j16, j21, j22, j23, z10, f11, i12, z11, list2, j19, j18);
    }

    /* JADX INFO: renamed from: component1-J3iCeTQ, reason: not valid java name and from getter */
    public final long getId() {
        return this.id;
    }

    /* JADX INFO: renamed from: component10-F1C5BW0, reason: not valid java name and from getter */
    public final long getScrollDelta() {
        return this.scrollDelta;
    }

    /* JADX INFO: renamed from: component11-F1C5BW0, reason: not valid java name and from getter */
    public final long getOriginalEventPosition() {
        return this.originalEventPosition;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final long getUptime() {
        return this.uptime;
    }

    /* JADX INFO: renamed from: component3-F1C5BW0, reason: not valid java name and from getter */
    public final long getPositionOnScreen() {
        return this.positionOnScreen;
    }

    /* JADX INFO: renamed from: component4-F1C5BW0, reason: not valid java name and from getter */
    public final long getPosition() {
        return this.position;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final boolean getDown() {
        return this.down;
    }

    /* JADX INFO: renamed from: component6, reason: from getter */
    public final float getPressure() {
        return this.pressure;
    }

    /* JADX INFO: renamed from: component7-T8wyACA, reason: not valid java name and from getter */
    public final int getType() {
        return this.type;
    }

    /* JADX INFO: renamed from: component8, reason: from getter */
    public final boolean getIssuesEnterExit() {
        return this.issuesEnterExit;
    }

    public final List<HistoricalChange> component9() {
        return this.historical;
    }

    /* JADX INFO: renamed from: copy-rc8HELY, reason: not valid java name */
    public final PointerInputEventData m4532copyrc8HELY(long id, long uptime, long positionOnScreen, long position, boolean down, float pressure, int type, boolean issuesEnterExit, List<HistoricalChange> historical, long scrollDelta, long originalEventPosition) {
        return new PointerInputEventData(id, uptime, positionOnScreen, position, down, pressure, type, issuesEnterExit, historical, scrollDelta, originalEventPosition, null);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof PointerInputEventData)) {
            return false;
        }
        PointerInputEventData pointerInputEventData = (PointerInputEventData) other;
        return PointerId.m4500equalsimpl0(this.id, pointerInputEventData.id) && this.uptime == pointerInputEventData.uptime && Offset.m3233equalsimpl0(this.positionOnScreen, pointerInputEventData.positionOnScreen) && Offset.m3233equalsimpl0(this.position, pointerInputEventData.position) && this.down == pointerInputEventData.down && Float.compare(this.pressure, pointerInputEventData.pressure) == 0 && PointerType.m4577equalsimpl0(this.type, pointerInputEventData.type) && this.issuesEnterExit == pointerInputEventData.issuesEnterExit && p.a(this.historical, pointerInputEventData.historical) && Offset.m3233equalsimpl0(this.scrollDelta, pointerInputEventData.scrollDelta) && Offset.m3233equalsimpl0(this.originalEventPosition, pointerInputEventData.originalEventPosition);
    }

    public final boolean getDown() {
        return this.down;
    }

    public final List<HistoricalChange> getHistorical() {
        return this.historical;
    }

    /* JADX INFO: renamed from: getId-J3iCeTQ, reason: not valid java name */
    public final long m4533getIdJ3iCeTQ() {
        return this.id;
    }

    public final boolean getIssuesEnterExit() {
        return this.issuesEnterExit;
    }

    /* JADX INFO: renamed from: getOriginalEventPosition-F1C5BW0, reason: not valid java name */
    public final long m4534getOriginalEventPositionF1C5BW0() {
        return this.originalEventPosition;
    }

    /* JADX INFO: renamed from: getPosition-F1C5BW0, reason: not valid java name */
    public final long m4535getPositionF1C5BW0() {
        return this.position;
    }

    /* JADX INFO: renamed from: getPositionOnScreen-F1C5BW0, reason: not valid java name */
    public final long m4536getPositionOnScreenF1C5BW0() {
        return this.positionOnScreen;
    }

    public final float getPressure() {
        return this.pressure;
    }

    /* JADX INFO: renamed from: getScrollDelta-F1C5BW0, reason: not valid java name */
    public final long m4537getScrollDeltaF1C5BW0() {
        return this.scrollDelta;
    }

    /* JADX INFO: renamed from: getType-T8wyACA, reason: not valid java name */
    public final int m4538getTypeT8wyACA() {
        return this.type;
    }

    public final long getUptime() {
        return this.uptime;
    }

    public int hashCode() {
        int iM4501hashCodeimpl = PointerId.m4501hashCodeimpl(this.id) * 31;
        long j10 = this.uptime;
        return Offset.m3238hashCodeimpl(this.originalEventPosition) + ((Offset.m3238hashCodeimpl(this.scrollDelta) + d.e(this.historical, (((PointerType.m4578hashCodeimpl(this.type) + a0.c.b(this.pressure, (((Offset.m3238hashCodeimpl(this.position) + ((Offset.m3238hashCodeimpl(this.positionOnScreen) + ((iM4501hashCodeimpl + ((int) (j10 ^ (j10 >>> 32)))) * 31)) * 31)) * 31) + (this.down ? 1231 : 1237)) * 31, 31)) * 31) + (this.issuesEnterExit ? 1231 : 1237)) * 31, 31)) * 31);
    }

    public String toString() {
        return "PointerInputEventData(id=" + ((Object) PointerId.m4502toStringimpl(this.id)) + ", uptime=" + this.uptime + ", positionOnScreen=" + ((Object) Offset.m3244toStringimpl(this.positionOnScreen)) + ", position=" + ((Object) Offset.m3244toStringimpl(this.position)) + ", down=" + this.down + ", pressure=" + this.pressure + ", type=" + ((Object) PointerType.m4579toStringimpl(this.type)) + ", issuesEnterExit=" + this.issuesEnterExit + ", historical=" + this.historical + ", scrollDelta=" + ((Object) Offset.m3244toStringimpl(this.scrollDelta)) + ", originalEventPosition=" + ((Object) Offset.m3244toStringimpl(this.originalEventPosition)) + ')';
    }

    private PointerInputEventData(long j10, long j11, long j12, long j13, boolean z, float f10, int i10, boolean z5, List<HistoricalChange> list, long j14, long j15) {
        this.id = j10;
        this.uptime = j11;
        this.positionOnScreen = j12;
        this.position = j13;
        this.down = z;
        this.pressure = f10;
        this.type = i10;
        this.issuesEnterExit = z5;
        this.historical = list;
        this.scrollDelta = j14;
        this.originalEventPosition = j15;
    }

    public /* synthetic */ PointerInputEventData(long j10, long j11, long j12, long j13, boolean z, float f10, int i10, boolean z5, List list, long j14, long j15, int i11, h hVar) {
        this(j10, j11, j12, j13, z, f10, i10, (i11 & 128) != 0 ? false : z5, (i11 & 256) != 0 ? new ArrayList() : list, (i11 & 512) != 0 ? Offset.INSTANCE.m3252getZeroF1C5BW0() : j14, (i11 & 1024) != 0 ? Offset.INSTANCE.m3252getZeroF1C5BW0() : j15, null);
    }
}
