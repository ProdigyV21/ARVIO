package androidx.compose.ui.input.pointer;

import io.ktor.http.LinkHeader;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.h;
import t.p;
import t.q;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0002\u0018\u00002\u00020\u0001:\u0001\u0011B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u001d\u0010\t\u001a\u00020\b2\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u0006¢\u0006\u0004\b\t\u0010\nJ\r\u0010\f\u001a\u00020\u000b¢\u0006\u0004\b\f\u0010\u0003R\u001a\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u000e0\r8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u000f\u0010\u0010¨\u0006\u0012"}, d2 = {"Landroidx/compose/ui/input/pointer/PointerInputChangeEventProducer;", "", "<init>", "()V", "Landroidx/compose/ui/input/pointer/PointerInputEvent;", "pointerInputEvent", "Landroidx/compose/ui/input/pointer/PositionCalculator;", "positionCalculator", "Landroidx/compose/ui/input/pointer/InternalPointerEvent;", "produce", "(Landroidx/compose/ui/input/pointer/PointerInputEvent;Landroidx/compose/ui/input/pointer/PositionCalculator;)Landroidx/compose/ui/input/pointer/InternalPointerEvent;", "Lx6/t0;", "clear", "Lt/p;", "Landroidx/compose/ui/input/pointer/PointerInputChangeEventProducer$PointerInputData;", "previousPointerInputData", "Lt/p;", "PointerInputData", "ui_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
final class PointerInputChangeEventProducer {
    private final p previousPointerInputData = new p((Object) null);

    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000e\b\u0002\u0018\u00002\u00020\u0001B'\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u0012\u0006\u0010\t\u001a\u00020\b¢\u0006\u0004\b\n\u0010\u000bR\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\f\u001a\u0004\b\r\u0010\u000eR\u001d\u0010\u0005\u001a\u00020\u00048\u0006ø\u0001\u0000ø\u0001\u0001¢\u0006\f\n\u0004\b\u0005\u0010\f\u001a\u0004\b\u000f\u0010\u000eR\u0017\u0010\u0007\u001a\u00020\u00068\u0006¢\u0006\f\n\u0004\b\u0007\u0010\u0010\u001a\u0004\b\u0011\u0010\u0012R\u001d\u0010\t\u001a\u00020\b8\u0006ø\u0001\u0000ø\u0001\u0001¢\u0006\f\n\u0004\b\t\u0010\u0013\u001a\u0004\b\u0014\u0010\u0015\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u0006\u0016"}, d2 = {"Landroidx/compose/ui/input/pointer/PointerInputChangeEventProducer$PointerInputData;", "", "", "uptime", "Landroidx/compose/ui/geometry/Offset;", "positionOnScreen", "", "down", "Landroidx/compose/ui/input/pointer/PointerType;", LinkHeader.Parameters.Type, "<init>", "(JJZILkotlin/jvm/internal/h;)V", "J", "getUptime", "()J", "getPositionOnScreen-F1C5BW0", "Z", "getDown", "()Z", "I", "getType-T8wyACA", "()I", "ui_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class PointerInputData {
        private final boolean down;
        private final long positionOnScreen;
        private final int type;
        private final long uptime;

        public /* synthetic */ PointerInputData(long j10, long j11, boolean z, int i10, h hVar) {
            this(j10, j11, z, i10);
        }

        public final boolean getDown() {
            return this.down;
        }

        /* JADX INFO: renamed from: getPositionOnScreen-F1C5BW0, reason: not valid java name and from getter */
        public final long getPositionOnScreen() {
            return this.positionOnScreen;
        }

        /* JADX INFO: renamed from: getType-T8wyACA, reason: not valid java name and from getter */
        public final int getType() {
            return this.type;
        }

        public final long getUptime() {
            return this.uptime;
        }

        private PointerInputData(long j10, long j11, boolean z, int i10) {
            this.uptime = j10;
            this.positionOnScreen = j11;
            this.down = z;
            this.type = i10;
        }
    }

    public final void clear() {
        this.previousPointerInputData.b();
    }

    public final InternalPointerEvent produce(PointerInputEvent pointerInputEvent, PositionCalculator positionCalculator) {
        long uptime;
        boolean down;
        long jMo4588screenToLocalMKHz9U;
        p pVar = new p(pointerInputEvent.getPointers().size());
        List<PointerInputEventData> pointers = pointerInputEvent.getPointers();
        int size = pointers.size();
        for (int i10 = 0; i10 < size; i10++) {
            PointerInputEventData pointerInputEventData = pointers.get(i10);
            PointerInputData pointerInputData = (PointerInputData) this.previousPointerInputData.d(pointerInputEventData.m4533getIdJ3iCeTQ());
            if (pointerInputData == null) {
                down = false;
                uptime = pointerInputEventData.getUptime();
                jMo4588screenToLocalMKHz9U = pointerInputEventData.m4535getPositionF1C5BW0();
            } else {
                uptime = pointerInputData.getUptime();
                down = pointerInputData.getDown();
                jMo4588screenToLocalMKHz9U = positionCalculator.mo4588screenToLocalMKHz9U(pointerInputData.getPositionOnScreen());
            }
            pVar.g(pointerInputEventData.m4533getIdJ3iCeTQ(), new PointerInputChange(pointerInputEventData.m4533getIdJ3iCeTQ(), pointerInputEventData.getUptime(), pointerInputEventData.m4535getPositionF1C5BW0(), pointerInputEventData.getDown(), pointerInputEventData.getPressure(), uptime, jMo4588screenToLocalMKHz9U, down, false, pointerInputEventData.m4538getTypeT8wyACA(), pointerInputEventData.getHistorical(), pointerInputEventData.m4537getScrollDeltaF1C5BW0(), pointerInputEventData.m4534getOriginalEventPositionF1C5BW0(), null));
            if (pointerInputEventData.getDown()) {
                this.previousPointerInputData.g(pointerInputEventData.m4533getIdJ3iCeTQ(), new PointerInputData(pointerInputEventData.getUptime(), pointerInputEventData.m4536getPositionOnScreenF1C5BW0(), pointerInputEventData.getDown(), pointerInputEventData.m4538getTypeT8wyACA(), null));
            } else {
                p pVar2 = this.previousPointerInputData;
                int iB = u.a.b(pVar2.f21858l, pVar2.f21860n, pointerInputEventData.m4533getIdJ3iCeTQ());
                if (iB >= 0) {
                    Object[] objArr = pVar2.f21859m;
                    Object obj = objArr[iB];
                    Object obj2 = q.f21861a;
                    if (obj != obj2) {
                        objArr[iB] = obj2;
                        pVar2.f21857i = true;
                    }
                }
            }
        }
        return new InternalPointerEvent(pVar, pointerInputEvent);
    }
}
