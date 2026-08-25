package androidx.compose.ui.input.pointer;

import androidx.compose.ui.geometry.Offset;
import androidx.media3.extractor.text.ttml.TtmlNode;
import io.ktor.http.LinkHeader;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.z;
import kotlin.jvm.internal.h;
import x6.e;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0013\n\u0002\u0010\u000e\n\u0002\b%\b\u0007\u0018\u00002\u00020\u0001Bc\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u0012\u0006\u0010\t\u001a\u00020\b\u0012\u0006\u0010\u000b\u001a\u00020\n\u0012\u0006\u0010\f\u001a\u00020\u0004\u0012\u0006\u0010\r\u001a\u00020\u0006\u0012\u0006\u0010\u000e\u001a\u00020\b\u0012\u0006\u0010\u000f\u001a\u00020\b\u0012\b\b\u0002\u0010\u0011\u001a\u00020\u0010\u0012\b\b\u0002\u0010\u0012\u001a\u00020\u0006¢\u0006\u0004\b\u0013\u0010\u0014B]\b\u0016\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u0012\u0006\u0010\t\u001a\u00020\b\u0012\u0006\u0010\f\u001a\u00020\u0004\u0012\u0006\u0010\r\u001a\u00020\u0006\u0012\u0006\u0010\u000e\u001a\u00020\b\u0012\u0006\u0010\u000f\u001a\u00020\b\u0012\b\b\u0002\u0010\u0011\u001a\u00020\u0010\u0012\b\b\u0002\u0010\u0012\u001a\u00020\u0006¢\u0006\u0004\b\u0013\u0010\u0015BS\b\u0017\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u0012\u0006\u0010\t\u001a\u00020\b\u0012\u0006\u0010\f\u001a\u00020\u0004\u0012\u0006\u0010\r\u001a\u00020\u0006\u0012\u0006\u0010\u000e\u001a\u00020\b\u0012\u0006\u0010\u0017\u001a\u00020\u0016\u0012\b\b\u0002\u0010\u0011\u001a\u00020\u0010¢\u0006\u0004\b\u0013\u0010\u0018Bw\b\u0010\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u0012\u0006\u0010\t\u001a\u00020\b\u0012\u0006\u0010\u000b\u001a\u00020\n\u0012\u0006\u0010\f\u001a\u00020\u0004\u0012\u0006\u0010\r\u001a\u00020\u0006\u0012\u0006\u0010\u000e\u001a\u00020\b\u0012\u0006\u0010\u000f\u001a\u00020\b\u0012\u0006\u0010\u0011\u001a\u00020\u0010\u0012\f\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u001a0\u0019\u0012\u0006\u0010\u0012\u001a\u00020\u0006\u0012\u0006\u0010\u001c\u001a\u00020\u0006¢\u0006\u0004\b\u0013\u0010\u001dJ\r\u0010\u001f\u001a\u00020\u001e¢\u0006\u0004\b\u001f\u0010 Jl\u0010'\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\b\b\u0002\u0010!\u001a\u00020\u00042\b\b\u0002\u0010\"\u001a\u00020\u00062\b\b\u0002\u0010#\u001a\u00020\b2\b\b\u0002\u0010$\u001a\u00020\u00042\b\b\u0002\u0010\r\u001a\u00020\u00062\b\b\u0002\u0010\u000e\u001a\u00020\b2\b\b\u0002\u0010\u0017\u001a\u00020\u00162\b\b\u0002\u0010\u0011\u001a\u00020\u0010H\u0007ø\u0001\u0000¢\u0006\u0004\b%\u0010&Jj\u0010'\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\b\b\u0002\u0010!\u001a\u00020\u00042\b\b\u0002\u0010\"\u001a\u00020\u00062\b\b\u0002\u0010#\u001a\u00020\b2\b\b\u0002\u0010$\u001a\u00020\u00042\b\b\u0002\u0010\r\u001a\u00020\u00062\b\b\u0002\u0010\u000e\u001a\u00020\b2\b\b\u0002\u0010\u0011\u001a\u00020\u00102\b\b\u0002\u0010\u0012\u001a\u00020\u0006ø\u0001\u0000¢\u0006\u0004\b(\u0010)Jt\u0010'\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\b\b\u0002\u0010!\u001a\u00020\u00042\b\b\u0002\u0010\"\u001a\u00020\u00062\b\b\u0002\u0010#\u001a\u00020\b2\b\b\u0002\u0010$\u001a\u00020\u00042\b\b\u0002\u0010\r\u001a\u00020\u00062\b\b\u0002\u0010\u000e\u001a\u00020\b2\u0006\u0010\u0017\u001a\u00020\u00162\b\b\u0002\u0010\u0011\u001a\u00020\u00102\b\b\u0002\u0010\u0012\u001a\u00020\u0006H\u0007ø\u0001\u0000¢\u0006\u0004\b*\u0010+Jt\u0010'\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\b\b\u0002\u0010!\u001a\u00020\u00042\b\b\u0002\u0010\"\u001a\u00020\u00062\b\b\u0002\u0010#\u001a\u00020\b2\b\b\u0002\u0010\u000b\u001a\u00020\n2\b\b\u0002\u0010$\u001a\u00020\u00042\b\b\u0002\u0010\r\u001a\u00020\u00062\b\b\u0002\u0010\u000e\u001a\u00020\b2\b\b\u0002\u0010\u0011\u001a\u00020\u00102\b\b\u0002\u0010\u0012\u001a\u00020\u0006ø\u0001\u0000¢\u0006\u0004\b,\u0010-Jz\u0010'\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\b\b\u0002\u0010!\u001a\u00020\u00042\b\b\u0002\u0010\"\u001a\u00020\u00062\b\b\u0002\u0010#\u001a\u00020\b2\b\b\u0002\u0010$\u001a\u00020\u00042\b\b\u0002\u0010\r\u001a\u00020\u00062\b\b\u0002\u0010\u000e\u001a\u00020\b2\b\b\u0002\u0010\u0011\u001a\u00020\u00102\f\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u001a0\u00192\b\b\u0002\u0010\u0012\u001a\u00020\u0006H\u0007ø\u0001\u0000¢\u0006\u0004\b.\u0010/J\u0090\u0001\u0010'\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\b\b\u0002\u0010!\u001a\u00020\u00042\b\b\u0002\u0010\"\u001a\u00020\u00062\b\b\u0002\u0010\u001c\u001a\u00020\u00062\b\b\u0002\u0010#\u001a\u00020\b2\b\b\u0002\u0010\u000b\u001a\u00020\n2\b\b\u0002\u0010$\u001a\u00020\u00042\b\b\u0002\u0010\r\u001a\u00020\u00062\b\b\u0002\u0010\u000e\u001a\u00020\b2\b\b\u0002\u0010\u0011\u001a\u00020\u00102\u000e\b\u0002\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u001a0\u00192\b\b\u0002\u0010\u0012\u001a\u00020\u0006H\u0007ø\u0001\u0000¢\u0006\u0004\b0\u00101J\u000f\u00103\u001a\u000202H\u0016¢\u0006\u0004\b3\u00104R\u001d\u0010\u0003\u001a\u00020\u00028\u0006ø\u0001\u0000ø\u0001\u0001¢\u0006\f\n\u0004\b\u0003\u00105\u001a\u0004\b6\u00107R\u0017\u0010\u0005\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b\u0005\u00105\u001a\u0004\b8\u00107R\u001d\u0010\u0007\u001a\u00020\u00068\u0006ø\u0001\u0000ø\u0001\u0001¢\u0006\f\n\u0004\b\u0007\u00105\u001a\u0004\b9\u00107R\u0017\u0010\t\u001a\u00020\b8\u0006¢\u0006\f\n\u0004\b\t\u0010:\u001a\u0004\b;\u0010<R\u0017\u0010\u000b\u001a\u00020\n8\u0006¢\u0006\f\n\u0004\b\u000b\u0010=\u001a\u0004\b>\u0010?R\u0017\u0010\f\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b\f\u00105\u001a\u0004\b@\u00107R\u001d\u0010\r\u001a\u00020\u00068\u0006ø\u0001\u0000ø\u0001\u0001¢\u0006\f\n\u0004\b\r\u00105\u001a\u0004\bA\u00107R\u0017\u0010\u000e\u001a\u00020\b8\u0006¢\u0006\f\n\u0004\b\u000e\u0010:\u001a\u0004\bB\u0010<R\u001d\u0010\u0011\u001a\u00020\u00108\u0006ø\u0001\u0000ø\u0001\u0001¢\u0006\f\n\u0004\b\u0011\u0010C\u001a\u0004\bD\u0010ER\u001d\u0010\u0012\u001a\u00020\u00068\u0006ø\u0001\u0000ø\u0001\u0001¢\u0006\f\n\u0004\b\u0012\u00105\u001a\u0004\bF\u00107R$\u0010G\u001a\n\u0012\u0004\u0012\u00020\u001a\u0018\u00010\u00198\u0002@\u0002X\u0082\u000e¢\u0006\f\n\u0004\bG\u0010H\u0012\u0004\bI\u0010 R(\u0010\u001c\u001a\u00020\u00068\u0000@\u0000X\u0080\u000eø\u0001\u0000ø\u0001\u0001¢\u0006\u0012\n\u0004\b\u001c\u00105\u001a\u0004\bJ\u00107\"\u0004\bK\u0010LR*\u0010\u0017\u001a\u00020\u00162\u0006\u0010M\u001a\u00020\u00168\u0006@BX\u0087\u000e¢\u0006\u0012\n\u0004\b\u0017\u0010N\u0012\u0004\bQ\u0010 \u001a\u0004\bO\u0010PR \u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u001a0\u00198GX\u0087\u0004¢\u0006\f\u0012\u0004\bT\u0010 \u001a\u0004\bR\u0010SR\u0017\u0010U\u001a\u00020\b8F¢\u0006\f\u0012\u0004\bV\u0010 \u001a\u0004\bU\u0010<\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u0006W"}, d2 = {"Landroidx/compose/ui/input/pointer/PointerInputChange;", "", "Landroidx/compose/ui/input/pointer/PointerId;", TtmlNode.ATTR_ID, "", "uptimeMillis", "Landroidx/compose/ui/geometry/Offset;", "position", "", "pressed", "", "pressure", "previousUptimeMillis", "previousPosition", "previousPressed", "isInitiallyConsumed", "Landroidx/compose/ui/input/pointer/PointerType;", LinkHeader.Parameters.Type, "scrollDelta", "<init>", "(JJJZFJJZZIJLkotlin/jvm/internal/h;)V", "(JJJZJJZZIJLkotlin/jvm/internal/h;)V", "Landroidx/compose/ui/input/pointer/ConsumedData;", "consumed", "(JJJZJJZLandroidx/compose/ui/input/pointer/ConsumedData;ILkotlin/jvm/internal/h;)V", "", "Landroidx/compose/ui/input/pointer/HistoricalChange;", "historical", "originalEventPosition", "(JJJZFJJZZILjava/util/List;JJLkotlin/jvm/internal/h;)V", "Lx6/t0;", "consume", "()V", "currentTime", "currentPosition", "currentPressed", "previousTime", "copy-Ezr-O64", "(JJJZJJZLandroidx/compose/ui/input/pointer/ConsumedData;I)Landroidx/compose/ui/input/pointer/PointerInputChange;", "copy", "copy-JKmWfYY", "(JJJZJJZIJ)Landroidx/compose/ui/input/pointer/PointerInputChange;", "copy-0GkPj7c", "(JJJZJJZLandroidx/compose/ui/input/pointer/ConsumedData;IJ)Landroidx/compose/ui/input/pointer/PointerInputChange;", "copy-Tn9QgHE", "(JJJZFJJZIJ)Landroidx/compose/ui/input/pointer/PointerInputChange;", "copy-OHpmEuE", "(JJJZJJZILjava/util/List;J)Landroidx/compose/ui/input/pointer/PointerInputChange;", "copy-8H9lfAM", "(JJJJZFJJZILjava/util/List;J)Landroidx/compose/ui/input/pointer/PointerInputChange;", "", "toString", "()Ljava/lang/String;", "J", "getId-J3iCeTQ", "()J", "getUptimeMillis", "getPosition-F1C5BW0", "Z", "getPressed", "()Z", "F", "getPressure", "()F", "getPreviousUptimeMillis", "getPreviousPosition-F1C5BW0", "getPreviousPressed", "I", "getType-T8wyACA", "()I", "getScrollDelta-F1C5BW0", "_historical", "Ljava/util/List;", "get_historical$annotations", "getOriginalEventPosition-F1C5BW0$ui_release", "setOriginalEventPosition-k-4lQ0M$ui_release", "(J)V", "<set-?>", "Landroidx/compose/ui/input/pointer/ConsumedData;", "getConsumed", "()Landroidx/compose/ui/input/pointer/ConsumedData;", "getConsumed$annotations", "getHistorical", "()Ljava/util/List;", "getHistorical$annotations", "isConsumed", "isConsumed$annotations", "ui_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class PointerInputChange {
    public static final int $stable = 0;
    private List<HistoricalChange> _historical;
    private ConsumedData consumed;
    private final long id;
    private long originalEventPosition;
    private final long position;
    private final boolean pressed;
    private final float pressure;
    private final long previousPosition;
    private final boolean previousPressed;
    private final long previousUptimeMillis;
    private final long scrollDelta;
    private final int type;
    private final long uptimeMillis;

    public /* synthetic */ PointerInputChange(long j10, long j11, long j12, boolean z, float f10, long j13, long j14, boolean z5, boolean z10, int i10, long j15, h hVar) {
        this(j10, j11, j12, z, f10, j13, j14, z5, z10, i10, j15);
    }

    /* JADX INFO: renamed from: copy-0GkPj7c$default, reason: not valid java name */
    public static /* synthetic */ PointerInputChange m4504copy0GkPj7c$default(PointerInputChange pointerInputChange, long j10, long j11, long j12, boolean z, long j13, long j14, boolean z5, ConsumedData consumedData, int i10, long j15, int i11, Object obj) {
        long j16;
        long j17 = (i11 & 1) != 0 ? pointerInputChange.id : j10;
        long j18 = (i11 & 2) != 0 ? pointerInputChange.uptimeMillis : j11;
        long j19 = (i11 & 4) != 0 ? pointerInputChange.position : j12;
        boolean z10 = (i11 & 8) != 0 ? pointerInputChange.pressed : z;
        long j20 = (i11 & 16) != 0 ? pointerInputChange.previousUptimeMillis : j13;
        long j21 = (i11 & 32) != 0 ? pointerInputChange.previousPosition : j14;
        boolean z11 = (i11 & 64) != 0 ? pointerInputChange.previousPressed : z5;
        int i12 = (i11 & 256) != 0 ? pointerInputChange.type : i10;
        if ((i11 & 512) != 0) {
            j16 = pointerInputChange.scrollDelta;
            j17 = j17;
        } else {
            j16 = j15;
        }
        return pointerInputChange.m4510copy0GkPj7c(j17, j18, j19, z10, j20, j21, z11, consumedData, i12, j16);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX INFO: renamed from: copy-8H9lfAM$default, reason: not valid java name */
    public static /* synthetic */ PointerInputChange m4505copy8H9lfAM$default(PointerInputChange pointerInputChange, long j10, long j11, long j12, long j13, boolean z, float f10, long j14, long j15, boolean z5, int i10, List list, long j16, int i11, Object obj) {
        long j17;
        List list2;
        long j18 = (i11 & 1) != 0 ? pointerInputChange.id : j10;
        long j19 = (i11 & 2) != 0 ? pointerInputChange.uptimeMillis : j11;
        long j20 = (i11 & 4) != 0 ? pointerInputChange.position : j12;
        long j21 = (i11 & 8) != 0 ? pointerInputChange.originalEventPosition : j13;
        boolean z10 = (i11 & 16) != 0 ? pointerInputChange.pressed : z;
        float f11 = (i11 & 32) != 0 ? pointerInputChange.pressure : f10;
        long j22 = (i11 & 64) != 0 ? pointerInputChange.previousUptimeMillis : j14;
        long j23 = (i11 & 128) != 0 ? pointerInputChange.previousPosition : j15;
        long j24 = j18;
        boolean z11 = (i11 & 256) != 0 ? pointerInputChange.previousPressed : z5;
        int i12 = (i11 & 512) != 0 ? pointerInputChange.type : i10;
        boolean z12 = z11;
        List historical = (i11 & 1024) != 0 ? pointerInputChange.getHistorical() : list;
        if ((i11 & 2048) != 0) {
            list2 = historical;
            j17 = pointerInputChange.scrollDelta;
        } else {
            j17 = j16;
            list2 = historical;
        }
        return pointerInputChange.m4511copy8H9lfAM(j24, j19, j20, j21, z10, f11, j22, j23, z12, i12, list2, j17);
    }

    /* JADX INFO: renamed from: copy-JKmWfYY$default, reason: not valid java name */
    public static /* synthetic */ PointerInputChange m4507copyJKmWfYY$default(PointerInputChange pointerInputChange, long j10, long j11, long j12, boolean z, long j13, long j14, boolean z5, int i10, long j15, int i11, Object obj) {
        long j16;
        long j17;
        long j18 = (i11 & 1) != 0 ? pointerInputChange.id : j10;
        long j19 = (i11 & 2) != 0 ? pointerInputChange.uptimeMillis : j11;
        long j20 = (i11 & 4) != 0 ? pointerInputChange.position : j12;
        boolean z10 = (i11 & 8) != 0 ? pointerInputChange.pressed : z;
        long j21 = (i11 & 16) != 0 ? pointerInputChange.previousUptimeMillis : j13;
        long j22 = (i11 & 32) != 0 ? pointerInputChange.previousPosition : j14;
        boolean z11 = (i11 & 64) != 0 ? pointerInputChange.previousPressed : z5;
        int i12 = (i11 & 128) != 0 ? pointerInputChange.type : i10;
        if ((i11 & 256) != 0) {
            j16 = pointerInputChange.scrollDelta;
            j17 = j18;
        } else {
            j16 = j15;
            j17 = j18;
        }
        return pointerInputChange.m4513copyJKmWfYY(j17, j19, j20, z10, j21, j22, z11, i12, j16);
    }

    /* JADX INFO: renamed from: copy-OHpmEuE$default, reason: not valid java name */
    public static /* synthetic */ PointerInputChange m4508copyOHpmEuE$default(PointerInputChange pointerInputChange, long j10, long j11, long j12, boolean z, long j13, long j14, boolean z5, int i10, List list, long j15, int i11, Object obj) {
        long j16;
        long j17 = (i11 & 1) != 0 ? pointerInputChange.id : j10;
        long j18 = (i11 & 2) != 0 ? pointerInputChange.uptimeMillis : j11;
        long j19 = (i11 & 4) != 0 ? pointerInputChange.position : j12;
        boolean z10 = (i11 & 8) != 0 ? pointerInputChange.pressed : z;
        long j20 = (i11 & 16) != 0 ? pointerInputChange.previousUptimeMillis : j13;
        long j21 = (i11 & 32) != 0 ? pointerInputChange.previousPosition : j14;
        boolean z11 = (i11 & 64) != 0 ? pointerInputChange.previousPressed : z5;
        int i12 = (i11 & 128) != 0 ? pointerInputChange.type : i10;
        if ((i11 & 512) != 0) {
            j16 = pointerInputChange.scrollDelta;
            j17 = j17;
        } else {
            j16 = j15;
        }
        return pointerInputChange.m4514copyOHpmEuE(j17, j18, j19, z10, j20, j21, z11, i12, list, j16);
    }

    /* JADX INFO: renamed from: copy-Tn9QgHE$default, reason: not valid java name */
    public static /* synthetic */ PointerInputChange m4509copyTn9QgHE$default(PointerInputChange pointerInputChange, long j10, long j11, long j12, boolean z, float f10, long j13, long j14, boolean z5, int i10, long j15, int i11, Object obj) {
        long j16;
        long j17;
        long j18 = (i11 & 1) != 0 ? pointerInputChange.id : j10;
        long j19 = (i11 & 2) != 0 ? pointerInputChange.uptimeMillis : j11;
        long j20 = (i11 & 4) != 0 ? pointerInputChange.position : j12;
        boolean z10 = (i11 & 8) != 0 ? pointerInputChange.pressed : z;
        float f11 = (i11 & 16) != 0 ? pointerInputChange.pressure : f10;
        long j21 = (i11 & 32) != 0 ? pointerInputChange.previousUptimeMillis : j13;
        long j22 = (i11 & 64) != 0 ? pointerInputChange.previousPosition : j14;
        boolean z11 = (i11 & 128) != 0 ? pointerInputChange.previousPressed : z5;
        int i12 = (i11 & 256) != 0 ? pointerInputChange.type : i10;
        if ((i11 & 512) != 0) {
            j16 = pointerInputChange.scrollDelta;
            j17 = j18;
        } else {
            j16 = j15;
            j17 = j18;
        }
        return pointerInputChange.m4515copyTn9QgHE(j17, j19, j20, z10, f11, j21, j22, z11, i12, j16);
    }

    @e
    public static /* synthetic */ void getConsumed$annotations() {
    }

    public static /* synthetic */ void getHistorical$annotations() {
    }

    private static /* synthetic */ void get_historical$annotations() {
    }

    public static /* synthetic */ void isConsumed$annotations() {
    }

    public final void consume() {
        this.consumed.setDownChange(true);
        this.consumed.setPositionChange(true);
    }

    @e
    /* JADX INFO: renamed from: copy-0GkPj7c, reason: not valid java name */
    public final PointerInputChange m4510copy0GkPj7c(long id, long currentTime, long currentPosition, boolean currentPressed, long previousTime, long previousPosition, boolean previousPressed, ConsumedData consumed, int type, long scrollDelta) {
        PointerInputChange pointerInputChange = new PointerInputChange(id, currentTime, currentPosition, currentPressed, this.pressure, previousTime, previousPosition, previousPressed, consumed.getDownChange() || consumed.getPositionChange(), type, getHistorical(), scrollDelta, this.originalEventPosition, null);
        this.consumed = consumed;
        return pointerInputChange;
    }

    /* JADX INFO: renamed from: copy-8H9lfAM, reason: not valid java name */
    public final PointerInputChange m4511copy8H9lfAM(long id, long currentTime, long currentPosition, long originalEventPosition, boolean currentPressed, float pressure, long previousTime, long previousPosition, boolean previousPressed, int type, List<HistoricalChange> historical, long scrollDelta) {
        PointerInputChange pointerInputChange = new PointerInputChange(id, currentTime, currentPosition, currentPressed, pressure, previousTime, previousPosition, previousPressed, false, type, historical, scrollDelta, originalEventPosition, null);
        pointerInputChange.consumed = this.consumed;
        return pointerInputChange;
    }

    @e
    /* JADX INFO: renamed from: copy-Ezr-O64, reason: not valid java name */
    public final /* synthetic */ PointerInputChange m4512copyEzrO64(long id, long currentTime, long currentPosition, boolean currentPressed, long previousTime, long previousPosition, boolean previousPressed, ConsumedData consumed, int type) {
        PointerInputChange pointerInputChange = new PointerInputChange(id, currentTime, currentPosition, currentPressed, this.pressure, previousTime, previousPosition, previousPressed, consumed.getDownChange() || consumed.getPositionChange(), type, getHistorical(), this.scrollDelta, this.originalEventPosition, null);
        this.consumed = consumed;
        return pointerInputChange;
    }

    /* JADX INFO: renamed from: copy-JKmWfYY, reason: not valid java name */
    public final PointerInputChange m4513copyJKmWfYY(long id, long currentTime, long currentPosition, boolean currentPressed, long previousTime, long previousPosition, boolean previousPressed, int type, long scrollDelta) {
        return m4505copy8H9lfAM$default(this, id, currentTime, currentPosition, 0L, currentPressed, this.pressure, previousTime, previousPosition, previousPressed, type, getHistorical(), scrollDelta, 8, null);
    }

    /* JADX INFO: renamed from: copy-OHpmEuE, reason: not valid java name */
    public final PointerInputChange m4514copyOHpmEuE(long id, long currentTime, long currentPosition, boolean currentPressed, long previousTime, long previousPosition, boolean previousPressed, int type, List<HistoricalChange> historical, long scrollDelta) {
        return m4505copy8H9lfAM$default(this, id, currentTime, currentPosition, 0L, currentPressed, this.pressure, previousTime, previousPosition, previousPressed, type, historical, scrollDelta, 8, null);
    }

    /* JADX INFO: renamed from: copy-Tn9QgHE, reason: not valid java name */
    public final PointerInputChange m4515copyTn9QgHE(long id, long currentTime, long currentPosition, boolean currentPressed, float pressure, long previousTime, long previousPosition, boolean previousPressed, int type, long scrollDelta) {
        PointerInputChange pointerInputChange = new PointerInputChange(id, currentTime, currentPosition, currentPressed, pressure, previousTime, previousPosition, previousPressed, false, type, getHistorical(), scrollDelta, this.originalEventPosition, null);
        pointerInputChange.consumed = this.consumed;
        return pointerInputChange;
    }

    public final ConsumedData getConsumed() {
        return this.consumed;
    }

    public final List<HistoricalChange> getHistorical() {
        List<HistoricalChange> list = this._historical;
        return list == null ? z.f19728i : list;
    }

    /* JADX INFO: renamed from: getId-J3iCeTQ, reason: not valid java name and from getter */
    public final long getId() {
        return this.id;
    }

    /* JADX INFO: renamed from: getOriginalEventPosition-F1C5BW0$ui_release, reason: not valid java name and from getter */
    public final long getOriginalEventPosition() {
        return this.originalEventPosition;
    }

    /* JADX INFO: renamed from: getPosition-F1C5BW0, reason: not valid java name and from getter */
    public final long getPosition() {
        return this.position;
    }

    public final boolean getPressed() {
        return this.pressed;
    }

    public final float getPressure() {
        return this.pressure;
    }

    /* JADX INFO: renamed from: getPreviousPosition-F1C5BW0, reason: not valid java name and from getter */
    public final long getPreviousPosition() {
        return this.previousPosition;
    }

    public final boolean getPreviousPressed() {
        return this.previousPressed;
    }

    public final long getPreviousUptimeMillis() {
        return this.previousUptimeMillis;
    }

    /* JADX INFO: renamed from: getScrollDelta-F1C5BW0, reason: not valid java name and from getter */
    public final long getScrollDelta() {
        return this.scrollDelta;
    }

    /* JADX INFO: renamed from: getType-T8wyACA, reason: not valid java name and from getter */
    public final int getType() {
        return this.type;
    }

    public final long getUptimeMillis() {
        return this.uptimeMillis;
    }

    public final boolean isConsumed() {
        return this.consumed.getDownChange() || this.consumed.getPositionChange();
    }

    /* JADX INFO: renamed from: setOriginalEventPosition-k-4lQ0M$ui_release, reason: not valid java name */
    public final void m4522setOriginalEventPositionk4lQ0M$ui_release(long j10) {
        this.originalEventPosition = j10;
    }

    public String toString() {
        return "PointerInputChange(id=" + ((Object) PointerId.m4502toStringimpl(this.id)) + ", uptimeMillis=" + this.uptimeMillis + ", position=" + ((Object) Offset.m3244toStringimpl(this.position)) + ", pressed=" + this.pressed + ", pressure=" + this.pressure + ", previousUptimeMillis=" + this.previousUptimeMillis + ", previousPosition=" + ((Object) Offset.m3244toStringimpl(this.previousPosition)) + ", previousPressed=" + this.previousPressed + ", isConsumed=" + isConsumed() + ", type=" + ((Object) PointerType.m4579toStringimpl(this.type)) + ", historical=" + getHistorical() + ",scrollDelta=" + ((Object) Offset.m3244toStringimpl(this.scrollDelta)) + ')';
    }

    public /* synthetic */ PointerInputChange(long j10, long j11, long j12, boolean z, float f10, long j13, long j14, boolean z5, boolean z10, int i10, List list, long j15, long j16, h hVar) {
        this(j10, j11, j12, z, f10, j13, j14, z5, z10, i10, (List<HistoricalChange>) list, j15, j16);
    }

    @e
    public /* synthetic */ PointerInputChange(long j10, long j11, long j12, boolean z, long j13, long j14, boolean z5, ConsumedData consumedData, int i10, h hVar) {
        this(j10, j11, j12, z, j13, j14, z5, consumedData, i10);
    }

    public /* synthetic */ PointerInputChange(long j10, long j11, long j12, boolean z, long j13, long j14, boolean z5, boolean z10, int i10, long j15, h hVar) {
        this(j10, j11, j12, z, j13, j14, z5, z10, i10, j15);
    }

    private PointerInputChange(long j10, long j11, long j12, boolean z, float f10, long j13, long j14, boolean z5, boolean z10, int i10, long j15) {
        this.id = j10;
        this.uptimeMillis = j11;
        this.position = j12;
        this.pressed = z;
        this.pressure = f10;
        this.previousUptimeMillis = j13;
        this.previousPosition = j14;
        this.previousPressed = z5;
        this.type = i10;
        this.scrollDelta = j15;
        this.originalEventPosition = Offset.INSTANCE.m3252getZeroF1C5BW0();
        this.consumed = new ConsumedData(z10, z10);
    }

    public /* synthetic */ PointerInputChange(long j10, long j11, long j12, boolean z, float f10, long j13, long j14, boolean z5, boolean z10, int i10, long j15, int i11, h hVar) {
        this(j10, j11, j12, z, f10, j13, j14, z5, z10, (i11 & 512) != 0 ? PointerType.INSTANCE.m4584getTouchT8wyACA() : i10, (i11 & 1024) != 0 ? Offset.INSTANCE.m3252getZeroF1C5BW0() : j15, (h) null);
    }

    public /* synthetic */ PointerInputChange(long j10, long j11, long j12, boolean z, long j13, long j14, boolean z5, boolean z10, int i10, long j15, int i11, h hVar) {
        this(j10, j11, j12, z, j13, j14, z5, z10, (i11 & 256) != 0 ? PointerType.INSTANCE.m4584getTouchT8wyACA() : i10, (i11 & 512) != 0 ? Offset.INSTANCE.m3252getZeroF1C5BW0() : j15, (h) null);
    }

    private PointerInputChange(long j10, long j11, long j12, boolean z, long j13, long j14, boolean z5, boolean z10, int i10, long j15) {
        this(j10, j11, j12, z, 1.0f, j13, j14, z5, z10, i10, j15, (h) null);
    }

    public /* synthetic */ PointerInputChange(long j10, long j11, long j12, boolean z, long j13, long j14, boolean z5, ConsumedData consumedData, int i10, int i11, h hVar) {
        this(j10, j11, j12, z, j13, j14, z5, consumedData, (i11 & 256) != 0 ? PointerType.INSTANCE.m4584getTouchT8wyACA() : i10, (h) null);
    }

    private PointerInputChange(long j10, long j11, long j12, boolean z, long j13, long j14, boolean z5, ConsumedData consumedData, int i10) {
        this(j10, j11, j12, z, 1.0f, j13, j14, z5, consumedData.getDownChange() || consumedData.getPositionChange(), i10, Offset.INSTANCE.m3252getZeroF1C5BW0(), (h) null);
    }

    private PointerInputChange(long j10, long j11, long j12, boolean z, float f10, long j13, long j14, boolean z5, boolean z10, int i10, List<HistoricalChange> list, long j15, long j16) {
        this(j10, j11, j12, z, f10, j13, j14, z5, z10, i10, j15, (h) null);
        this._historical = list;
        this.originalEventPosition = j16;
    }
}
