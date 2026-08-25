package androidx.compose.ui.input.pointer;

import androidx.compose.runtime.collection.MutableVector;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.geometry.Size;
import androidx.compose.ui.geometry.SizeKt;
import androidx.compose.ui.node.DelegatableNodeKt;
import androidx.compose.ui.node.f;
import androidx.compose.ui.platform.ViewConfiguration;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.DpRect;
import androidx.compose.ui.unit.IntSize;
import d7.d;
import d7.j;
import d7.m;
import io.ktor.http.ContentDisposition;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CancellationException;
import ka.k;
import ka.l;
import ka.m0;
import ka.v1;
import kotlin.Metadata;
import kotlin.jvm.internal.h;
import r7.p;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0086\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0010\u0007\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0000\u0018\u00002\u00020\u00012\u00020\u00022\u00020\u00032\u00020\u0004:\u0001OB0\u0012'\u0010\n\u001a#\b\u0001\u0012\u0004\u0012\u00020\u0003\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u0006\u0012\u0006\u0012\u0004\u0018\u00010\b0\u0005¢\u0006\u0002\b\t¢\u0006\u0004\b\u000b\u0010\fJ4\u0010\u0012\u001a\u00020\u00072\u0006\u0010\u000e\u001a\u00020\r2\u001a\u0010\u0011\u001a\u0016\u0012\f\u0012\n\u0012\u0002\b\u00030\u0010R\u00020\u0000\u0012\u0004\u0012\u00020\u00070\u000fH\u0082\b¢\u0006\u0004\b\u0012\u0010\u0013J\u001f\u0010\u0016\u001a\u00020\u00072\u0006\u0010\u0015\u001a\u00020\u00142\u0006\u0010\u000e\u001a\u00020\rH\u0002¢\u0006\u0004\b\u0016\u0010\u0017J\u000f\u0010\u0018\u001a\u00020\u0007H\u0016¢\u0006\u0004\b\u0018\u0010\u0019J\u000f\u0010\u001a\u001a\u00020\u0007H\u0016¢\u0006\u0004\b\u001a\u0010\u0019J\u000f\u0010\u001b\u001a\u00020\u0007H\u0016¢\u0006\u0004\b\u001b\u0010\u0019J\u000f\u0010\u001c\u001a\u00020\u0007H\u0016¢\u0006\u0004\b\u001c\u0010\u0019J*\u0010!\u001a\u00020\u00072\u0006\u0010\u0015\u001a\u00020\u00142\u0006\u0010\u000e\u001a\u00020\r2\u0006\u0010\u001e\u001a\u00020\u001dH\u0016ø\u0001\u0000¢\u0006\u0004\b\u001f\u0010 J\u000f\u0010\"\u001a\u00020\u0007H\u0016¢\u0006\u0004\b\"\u0010\u0019J?\u0010%\u001a\u00028\u0000\"\u0004\b\u0000\u0010#2'\u0010\u0011\u001a#\b\u0001\u0012\u0004\u0012\u00020$\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u0006\u0012\u0006\u0012\u0004\u0018\u00010\b0\u0005¢\u0006\u0002\b\tH\u0096@¢\u0006\u0004\b%\u0010&Rl\u0010\n\u001a#\b\u0001\u0012\u0004\u0012\u00020\u0003\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u0006\u0012\u0006\u0012\u0004\u0018\u00010\b0\u0005¢\u0006\u0002\b\t2'\u0010'\u001a#\b\u0001\u0012\u0004\u0012\u00020\u0003\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u0006\u0012\u0006\u0012\u0004\u0018\u00010\b0\u0005¢\u0006\u0002\b\t8\u0016@VX\u0096\u000e¢\u0006\u0012\n\u0004\b\n\u0010(\u001a\u0004\b)\u0010*\"\u0004\b+\u0010\fR\u0018\u0010-\u001a\u0004\u0018\u00010,8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b-\u0010.R\u0016\u0010/\u001a\u00020\u00148\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b/\u00100R\"\u00102\u001a\u0010\u0012\f\u0012\n\u0012\u0002\b\u00030\u0010R\u00020\u0000018\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b2\u00103R\"\u00104\u001a\u0010\u0012\f\u0012\n\u0012\u0002\b\u00030\u0010R\u00020\u0000018\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b4\u00103R\u0018\u00105\u001a\u0004\u0018\u00010\u00148\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b5\u00100R\u001c\u00106\u001a\u00020\u001d8\u0002@\u0002X\u0082\u000eø\u0001\u0000ø\u0001\u0001¢\u0006\u0006\n\u0004\b6\u00107R\"\u00109\u001a\u0002088\u0016@\u0016X\u0096\u000e¢\u0006\u0012\n\u0004\b9\u0010:\u001a\u0004\b;\u0010<\"\u0004\b=\u0010>R\u0014\u0010B\u001a\u00020?8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b@\u0010AR\u0014\u0010D\u001a\u00020?8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\bC\u0010AR\u0014\u0010H\u001a\u00020E8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\bF\u0010GR\u001a\u0010K\u001a\u00020\u001d8VX\u0096\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\u0006\u001a\u0004\bI\u0010JR\u001a\u0010N\u001a\u00020L8VX\u0096\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\u0006\u001a\u0004\bM\u0010J\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u0006P"}, d2 = {"Landroidx/compose/ui/input/pointer/SuspendingPointerInputModifierNodeImpl;", "Landroidx/compose/ui/Modifier$Node;", "Landroidx/compose/ui/input/pointer/SuspendingPointerInputModifierNode;", "Landroidx/compose/ui/input/pointer/PointerInputScope;", "Landroidx/compose/ui/unit/Density;", "Lkotlin/Function2;", "Ld7/d;", "Lx6/t0;", "", "Lx6/n;", "pointerInputHandler", "<init>", "(Lr7/p;)V", "Landroidx/compose/ui/input/pointer/PointerEventPass;", "pass", "Lkotlin/Function1;", "Landroidx/compose/ui/input/pointer/SuspendingPointerInputModifierNodeImpl$PointerEventHandlerCoroutine;", "block", "forEachCurrentPointerHandler", "(Landroidx/compose/ui/input/pointer/PointerEventPass;Lr7/l;)V", "Landroidx/compose/ui/input/pointer/PointerEvent;", "pointerEvent", "dispatchPointerEvent", "(Landroidx/compose/ui/input/pointer/PointerEvent;Landroidx/compose/ui/input/pointer/PointerEventPass;)V", "onDetach", "()V", "onDensityChange", "onViewConfigurationChange", "resetPointerInputHandler", "Landroidx/compose/ui/unit/IntSize;", "bounds", "onPointerEvent-H0pRuoY", "(Landroidx/compose/ui/input/pointer/PointerEvent;Landroidx/compose/ui/input/pointer/PointerEventPass;J)V", "onPointerEvent", "onCancelPointerInput", "R", "Landroidx/compose/ui/input/pointer/AwaitPointerEventScope;", "awaitPointerEventScope", "(Lr7/p;Ld7/d;)Ljava/lang/Object;", "value", "Lr7/p;", "getPointerInputHandler", "()Lr7/p;", "setPointerInputHandler", "Lka/v1;", "pointerInputJob", "Lka/v1;", "currentEvent", "Landroidx/compose/ui/input/pointer/PointerEvent;", "Landroidx/compose/runtime/collection/MutableVector;", "pointerHandlers", "Landroidx/compose/runtime/collection/MutableVector;", "dispatchingPointerHandlers", "lastPointerEvent", "boundsSize", "J", "", "interceptOutOfBoundsChildEvents", "Z", "getInterceptOutOfBoundsChildEvents", "()Z", "setInterceptOutOfBoundsChildEvents", "(Z)V", "", "getDensity", "()F", "density", "getFontScale", "fontScale", "Landroidx/compose/ui/platform/ViewConfiguration;", "getViewConfiguration", "()Landroidx/compose/ui/platform/ViewConfiguration;", "viewConfiguration", "getSize-YbymL2g", "()J", ContentDisposition.Parameters.Size, "Landroidx/compose/ui/geometry/Size;", "getExtendedTouchPadding-NH-jbRc", "extendedTouchPadding", "PointerEventHandlerCoroutine", "ui_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class SuspendingPointerInputModifierNodeImpl extends Modifier.Node implements SuspendingPointerInputModifierNode, PointerInputScope, Density {
    public static final int $stable = 0;
    private boolean interceptOutOfBoundsChildEvents;
    private PointerEvent lastPointerEvent;
    private p<? super PointerInputScope, ? super d<? super t0>, ? extends Object> pointerInputHandler;
    private v1 pointerInputJob;
    private PointerEvent currentEvent = SuspendingPointerInputFilterKt.EmptyPointerEvent;
    private final MutableVector<PointerEventHandlerCoroutine<?>> pointerHandlers = new MutableVector<>(new PointerEventHandlerCoroutine[16], 0);
    private final MutableVector<PointerEventHandlerCoroutine<?>> dispatchingPointerHandlers = new MutableVector<>(new PointerEventHandlerCoroutine[16], 0);
    private long boundsSize = IntSize.INSTANCE.m5849getZeroYbymL2g();

    @Metadata(d1 = {"\u0000¤\u0001\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0003\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0082\u0004\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u00022\u00020\u00032\b\u0012\u0004\u0012\u00028\u00000\u0004B\u0015\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00028\u00000\u0004¢\u0006\u0004\b\u0006\u0010\u0007J\u0017\u0010\f\u001a\u00020\t*\u00020\bH\u0097\u0001ø\u0001\u0000¢\u0006\u0004\b\n\u0010\u000bJ\u0017\u0010\f\u001a\u00020\t*\u00020\rH\u0097\u0001ø\u0001\u0000¢\u0006\u0004\b\u000e\u0010\u000fJ\u0017\u0010\u0012\u001a\u00020\b*\u00020\rH\u0097\u0001ø\u0001\u0000¢\u0006\u0004\b\u0010\u0010\u0011J\u001a\u0010\u0012\u001a\u00020\b*\u00020\u0013H\u0097\u0001ø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\b\u0014\u0010\u0015J\u001a\u0010\u0012\u001a\u00020\b*\u00020\tH\u0097\u0001ø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\b\u0014\u0010\u0016J\u0017\u0010\u001b\u001a\u00020\u0018*\u00020\u0017H\u0097\u0001ø\u0001\u0000¢\u0006\u0004\b\u0019\u0010\u001aJ\u0017\u0010\u001d\u001a\u00020\u0013*\u00020\bH\u0097\u0001ø\u0001\u0000¢\u0006\u0004\b\u001c\u0010\u0015J\u0017\u0010\u001d\u001a\u00020\u0013*\u00020\rH\u0097\u0001ø\u0001\u0000¢\u0006\u0004\b\u001e\u0010\u0011J\u0014\u0010!\u001a\u00020 *\u00020\u001fH\u0097\u0001¢\u0006\u0004\b!\u0010\"J\u0017\u0010$\u001a\u00020\u0017*\u00020\u0018H\u0097\u0001ø\u0001\u0000¢\u0006\u0004\b#\u0010\u001aJ\u0017\u0010'\u001a\u00020\r*\u00020\bH\u0097\u0001ø\u0001\u0000¢\u0006\u0004\b%\u0010&J\u001a\u0010'\u001a\u00020\r*\u00020\u0013H\u0097\u0001ø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\b(\u0010&J\u001a\u0010'\u001a\u00020\r*\u00020\tH\u0097\u0001ø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\b(\u0010)J\u001d\u0010/\u001a\u00020.2\u0006\u0010+\u001a\u00020*2\u0006\u0010-\u001a\u00020,¢\u0006\u0004\b/\u00100J\u0017\u00103\u001a\u00020.2\b\u00102\u001a\u0004\u0018\u000101¢\u0006\u0004\b3\u00104J\u001d\u00107\u001a\u00020.2\f\u00106\u001a\b\u0012\u0004\u0012\u00028\u000005H\u0016¢\u0006\u0004\b7\u00108J\u0018\u00109\u001a\u00020*2\u0006\u0010-\u001a\u00020,H\u0096@¢\u0006\u0004\b9\u0010:JI\u0010B\u001a\u0004\u0018\u00018\u0001\"\u0004\b\u0001\u0010;2\u0006\u0010=\u001a\u00020<2'\u0010A\u001a#\b\u0001\u0012\u0004\u0012\u00020\u0002\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00010\u0004\u0012\u0006\u0012\u0004\u0018\u00010?0>¢\u0006\u0002\b@H\u0096@¢\u0006\u0004\bB\u0010CJG\u0010D\u001a\u00028\u0001\"\u0004\b\u0001\u0010;2\u0006\u0010=\u001a\u00020<2'\u0010A\u001a#\b\u0001\u0012\u0004\u0012\u00020\u0002\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00010\u0004\u0012\u0006\u0012\u0004\u0018\u00010?0>¢\u0006\u0002\b@H\u0096@¢\u0006\u0004\bD\u0010CR\u001a\u0010\u0005\u001a\b\u0012\u0004\u0012\u00028\u00000\u00048\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0005\u0010ER\u001e\u0010G\u001a\n\u0012\u0004\u0012\u00020*\u0018\u00010F8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bG\u0010HR\u0016\u0010I\u001a\u00020,8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bI\u0010JR\u001a\u0010L\u001a\u00020K8\u0016X\u0096\u0004¢\u0006\f\n\u0004\bL\u0010M\u001a\u0004\bN\u0010OR\u0014\u0010R\u001a\u00020\u00138\u0016X\u0097\u0005¢\u0006\u0006\u001a\u0004\bP\u0010QR\u0014\u0010T\u001a\u00020\u00138\u0016X\u0097\u0005¢\u0006\u0006\u001a\u0004\bS\u0010QR\u0014\u0010W\u001a\u00020*8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\bU\u0010VR\u001a\u0010[\u001a\u00020X8VX\u0096\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\u0006\u001a\u0004\bY\u0010ZR\u0014\u0010_\u001a\u00020\\8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b]\u0010^R\u001a\u0010a\u001a\u00020\u00178VX\u0096\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\u0006\u001a\u0004\b`\u0010Z\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u0006b"}, d2 = {"Landroidx/compose/ui/input/pointer/SuspendingPointerInputModifierNodeImpl$PointerEventHandlerCoroutine;", "R", "Landroidx/compose/ui/input/pointer/AwaitPointerEventScope;", "Landroidx/compose/ui/unit/Density;", "Ld7/d;", "completion", "<init>", "(Landroidx/compose/ui/input/pointer/SuspendingPointerInputModifierNodeImpl;Ld7/d;)V", "Landroidx/compose/ui/unit/Dp;", "", "roundToPx-0680j_4", "(F)I", "roundToPx", "Landroidx/compose/ui/unit/TextUnit;", "roundToPx--R2X_6o", "(J)I", "toDp-GaN1DYA", "(J)F", "toDp", "", "toDp-u2uoSUM", "(F)F", "(I)F", "Landroidx/compose/ui/geometry/Size;", "Landroidx/compose/ui/unit/DpSize;", "toDpSize-k-rfVVM", "(J)J", "toDpSize", "toPx-0680j_4", "toPx", "toPx--R2X_6o", "Landroidx/compose/ui/unit/DpRect;", "Landroidx/compose/ui/geometry/Rect;", "toRect", "(Landroidx/compose/ui/unit/DpRect;)Landroidx/compose/ui/geometry/Rect;", "toSize-XkaWNTQ", "toSize", "toSp-0xMU5do", "(F)J", "toSp", "toSp-kPz2Gy4", "(I)J", "Landroidx/compose/ui/input/pointer/PointerEvent;", "event", "Landroidx/compose/ui/input/pointer/PointerEventPass;", "pass", "Lx6/t0;", "offerPointerEvent", "(Landroidx/compose/ui/input/pointer/PointerEvent;Landroidx/compose/ui/input/pointer/PointerEventPass;)V", "", "cause", "cancel", "(Ljava/lang/Throwable;)V", "Lx6/d0;", "result", "resumeWith", "(Ljava/lang/Object;)V", "awaitPointerEvent", "(Landroidx/compose/ui/input/pointer/PointerEventPass;Ld7/d;)Ljava/lang/Object;", "T", "", "timeMillis", "Lkotlin/Function2;", "", "Lx6/n;", "block", "withTimeoutOrNull", "(JLr7/p;Ld7/d;)Ljava/lang/Object;", "withTimeout", "Ld7/d;", "Lka/k;", "pointerAwaiter", "Lka/k;", "awaitPass", "Landroidx/compose/ui/input/pointer/PointerEventPass;", "Ld7/j;", "context", "Ld7/j;", "getContext", "()Ld7/j;", "getDensity", "()F", "density", "getFontScale", "fontScale", "getCurrentEvent", "()Landroidx/compose/ui/input/pointer/PointerEvent;", "currentEvent", "Landroidx/compose/ui/unit/IntSize;", "getSize-YbymL2g", "()J", ContentDisposition.Parameters.Size, "Landroidx/compose/ui/platform/ViewConfiguration;", "getViewConfiguration", "()Landroidx/compose/ui/platform/ViewConfiguration;", "viewConfiguration", "getExtendedTouchPadding-NH-jbRc", "extendedTouchPadding", "ui_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public final class PointerEventHandlerCoroutine<R> implements AwaitPointerEventScope, Density, d<R> {
        private final /* synthetic */ SuspendingPointerInputModifierNodeImpl $$delegate_0;
        private final d<R> completion;
        private k<? super PointerEvent> pointerAwaiter;
        private PointerEventPass awaitPass = PointerEventPass.Main;
        private final j context = d7.k.f14688i;

        /* JADX WARN: Multi-variable type inference failed */
        public PointerEventHandlerCoroutine(d<? super R> dVar) {
            this.completion = dVar;
            this.$$delegate_0 = SuspendingPointerInputModifierNodeImpl.this;
        }

        @Override // androidx.compose.ui.input.pointer.AwaitPointerEventScope
        public Object awaitPointerEvent(PointerEventPass pointerEventPass, d<? super PointerEvent> dVar) {
            l lVar = new l(1, t7.a.A(dVar));
            lVar.s();
            this.awaitPass = pointerEventPass;
            this.pointerAwaiter = lVar;
            return lVar.r();
        }

        public final void cancel(Throwable cause) {
            k<? super PointerEvent> kVar = this.pointerAwaiter;
            if (kVar != null) {
                kVar.cancel(cause);
            }
            this.pointerAwaiter = null;
        }

        @Override // d7.d
        public j getContext() {
            return this.context;
        }

        @Override // androidx.compose.ui.input.pointer.AwaitPointerEventScope
        public PointerEvent getCurrentEvent() {
            return SuspendingPointerInputModifierNodeImpl.this.currentEvent;
        }

        @Override // androidx.compose.ui.unit.Density
        public float getDensity() {
            return this.$$delegate_0.getDensity();
        }

        @Override // androidx.compose.ui.input.pointer.AwaitPointerEventScope
        /* JADX INFO: renamed from: getExtendedTouchPadding-NH-jbRc */
        public long mo4429getExtendedTouchPaddingNHjbRc() {
            return SuspendingPointerInputModifierNodeImpl.this.mo276getExtendedTouchPaddingNHjbRc();
        }

        @Override // androidx.compose.ui.unit.FontScaling
        public float getFontScale() {
            return this.$$delegate_0.getFontScale();
        }

        @Override // androidx.compose.ui.input.pointer.AwaitPointerEventScope
        /* JADX INFO: renamed from: getSize-YbymL2g */
        public long mo4430getSizeYbymL2g() {
            return SuspendingPointerInputModifierNodeImpl.this.boundsSize;
        }

        @Override // androidx.compose.ui.input.pointer.AwaitPointerEventScope
        public ViewConfiguration getViewConfiguration() {
            return SuspendingPointerInputModifierNodeImpl.this.getViewConfiguration();
        }

        public final void offerPointerEvent(PointerEvent event, PointerEventPass pass) {
            k<? super PointerEvent> kVar;
            if (pass != this.awaitPass || (kVar = this.pointerAwaiter) == null) {
                return;
            }
            this.pointerAwaiter = null;
            kVar.resumeWith(event);
        }

        @Override // d7.d
        public void resumeWith(Object result) {
            MutableVector mutableVector = SuspendingPointerInputModifierNodeImpl.this.pointerHandlers;
            SuspendingPointerInputModifierNodeImpl suspendingPointerInputModifierNodeImpl = SuspendingPointerInputModifierNodeImpl.this;
            synchronized (mutableVector) {
                suspendingPointerInputModifierNodeImpl.pointerHandlers.remove(this);
            }
            this.completion.resumeWith(result);
        }

        @Override // androidx.compose.ui.unit.Density
        /* JADX INFO: renamed from: roundToPx--R2X_6o */
        public int mo278roundToPxR2X_6o(long j10) {
            SuspendingPointerInputModifierNodeImpl suspendingPointerInputModifierNodeImpl = this.$$delegate_0;
            suspendingPointerInputModifierNodeImpl.getClass();
            return androidx.compose.ui.unit.a.a(suspendingPointerInputModifierNodeImpl, j10);
        }

        @Override // androidx.compose.ui.unit.Density
        /* JADX INFO: renamed from: roundToPx-0680j_4 */
        public int mo279roundToPx0680j_4(float f10) {
            SuspendingPointerInputModifierNodeImpl suspendingPointerInputModifierNodeImpl = this.$$delegate_0;
            suspendingPointerInputModifierNodeImpl.getClass();
            return androidx.compose.ui.unit.a.b(suspendingPointerInputModifierNodeImpl, f10);
        }

        @Override // androidx.compose.ui.unit.FontScaling
        /* JADX INFO: renamed from: toDp-GaN1DYA */
        public float mo280toDpGaN1DYA(long j10) {
            SuspendingPointerInputModifierNodeImpl suspendingPointerInputModifierNodeImpl = this.$$delegate_0;
            suspendingPointerInputModifierNodeImpl.getClass();
            return androidx.compose.ui.unit.b.a(suspendingPointerInputModifierNodeImpl, j10);
        }

        @Override // androidx.compose.ui.unit.Density
        /* JADX INFO: renamed from: toDp-u2uoSUM */
        public float mo281toDpu2uoSUM(float f10) {
            SuspendingPointerInputModifierNodeImpl suspendingPointerInputModifierNodeImpl = this.$$delegate_0;
            suspendingPointerInputModifierNodeImpl.getClass();
            return androidx.compose.ui.unit.a.c(suspendingPointerInputModifierNodeImpl, f10);
        }

        @Override // androidx.compose.ui.unit.Density
        /* JADX INFO: renamed from: toDpSize-k-rfVVM */
        public long mo283toDpSizekrfVVM(long j10) {
            SuspendingPointerInputModifierNodeImpl suspendingPointerInputModifierNodeImpl = this.$$delegate_0;
            suspendingPointerInputModifierNodeImpl.getClass();
            return androidx.compose.ui.unit.a.e(suspendingPointerInputModifierNodeImpl, j10);
        }

        @Override // androidx.compose.ui.unit.Density
        /* JADX INFO: renamed from: toPx--R2X_6o */
        public float mo284toPxR2X_6o(long j10) {
            SuspendingPointerInputModifierNodeImpl suspendingPointerInputModifierNodeImpl = this.$$delegate_0;
            suspendingPointerInputModifierNodeImpl.getClass();
            return androidx.compose.ui.unit.a.f(suspendingPointerInputModifierNodeImpl, j10);
        }

        @Override // androidx.compose.ui.unit.Density
        /* JADX INFO: renamed from: toPx-0680j_4 */
        public float mo285toPx0680j_4(float f10) {
            SuspendingPointerInputModifierNodeImpl suspendingPointerInputModifierNodeImpl = this.$$delegate_0;
            suspendingPointerInputModifierNodeImpl.getClass();
            return androidx.compose.ui.unit.a.g(suspendingPointerInputModifierNodeImpl, f10);
        }

        @Override // androidx.compose.ui.unit.Density
        public Rect toRect(DpRect dpRect) {
            SuspendingPointerInputModifierNodeImpl suspendingPointerInputModifierNodeImpl = this.$$delegate_0;
            suspendingPointerInputModifierNodeImpl.getClass();
            return androidx.compose.ui.unit.a.h(suspendingPointerInputModifierNodeImpl, dpRect);
        }

        @Override // androidx.compose.ui.unit.Density
        /* JADX INFO: renamed from: toSize-XkaWNTQ */
        public long mo286toSizeXkaWNTQ(long j10) {
            SuspendingPointerInputModifierNodeImpl suspendingPointerInputModifierNodeImpl = this.$$delegate_0;
            suspendingPointerInputModifierNodeImpl.getClass();
            return androidx.compose.ui.unit.a.i(suspendingPointerInputModifierNodeImpl, j10);
        }

        @Override // androidx.compose.ui.unit.FontScaling
        /* JADX INFO: renamed from: toSp-0xMU5do */
        public long mo287toSp0xMU5do(float f10) {
            SuspendingPointerInputModifierNodeImpl suspendingPointerInputModifierNodeImpl = this.$$delegate_0;
            suspendingPointerInputModifierNodeImpl.getClass();
            return androidx.compose.ui.unit.b.b(suspendingPointerInputModifierNodeImpl, f10);
        }

        @Override // androidx.compose.ui.unit.Density
        /* JADX INFO: renamed from: toSp-kPz2Gy4 */
        public long mo288toSpkPz2Gy4(float f10) {
            SuspendingPointerInputModifierNodeImpl suspendingPointerInputModifierNodeImpl = this.$$delegate_0;
            suspendingPointerInputModifierNodeImpl.getClass();
            return androidx.compose.ui.unit.a.j(suspendingPointerInputModifierNodeImpl, f10);
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
        /* JADX WARN: Type inference failed for: r6v0, types: [long] */
        /* JADX WARN: Type inference failed for: r6v1, types: [ka.v1] */
        /* JADX WARN: Type inference failed for: r6v4, types: [ka.v1] */
        /* JADX WARN: Type inference failed for: r6v8 */
        /* JADX WARN: Type inference failed for: r6v9 */
        @Override // androidx.compose.ui.input.pointer.AwaitPointerEventScope
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public <T> java.lang.Object withTimeout(long r6, r7.p<? super androidx.compose.ui.input.pointer.AwaitPointerEventScope, ? super d7.d<? super T>, ? extends java.lang.Object> r8, d7.d<? super T> r9) {
            /*
                r5 = this;
                boolean r0 = r9 instanceof androidx.compose.ui.input.pointer.SuspendingPointerInputModifierNodeImpl$PointerEventHandlerCoroutine$withTimeout$1
                if (r0 == 0) goto L13
                r0 = r9
                androidx.compose.ui.input.pointer.SuspendingPointerInputModifierNodeImpl$PointerEventHandlerCoroutine$withTimeout$1 r0 = (androidx.compose.ui.input.pointer.SuspendingPointerInputModifierNodeImpl$PointerEventHandlerCoroutine$withTimeout$1) r0
                int r1 = r0.label
                r2 = -2147483648(0xffffffff80000000, float:-0.0)
                r3 = r1 & r2
                if (r3 == 0) goto L13
                int r1 = r1 - r2
                r0.label = r1
                goto L18
            L13:
                androidx.compose.ui.input.pointer.SuspendingPointerInputModifierNodeImpl$PointerEventHandlerCoroutine$withTimeout$1 r0 = new androidx.compose.ui.input.pointer.SuspendingPointerInputModifierNodeImpl$PointerEventHandlerCoroutine$withTimeout$1
                r0.<init>(r5, r9)
            L18:
                java.lang.Object r9 = r0.result
                int r1 = r0.label
                r2 = 1
                if (r1 == 0) goto L33
                if (r1 != r2) goto L2b
                java.lang.Object r6 = r0.L$0
                ka.v1 r6 = (ka.v1) r6
                k2.c.G(r9)     // Catch: java.lang.Throwable -> L29
                goto L6c
            L29:
                r7 = move-exception
                goto L72
            L2b:
                java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
                java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
                r6.<init>(r7)
                throw r6
            L33:
                k2.c.G(r9)
                r3 = 0
                int r9 = (r6 > r3 ? 1 : (r6 == r3 ? 0 : -1))
                if (r9 > 0) goto L4d
                ka.k<? super androidx.compose.ui.input.pointer.PointerEvent> r9 = r5.pointerAwaiter
                if (r9 == 0) goto L4d
                androidx.compose.ui.input.pointer.PointerEventTimeoutCancellationException r1 = new androidx.compose.ui.input.pointer.PointerEventTimeoutCancellationException
                r1.<init>(r6)
                x6.c0 r3 = new x6.c0
                r3.<init>(r1)
                r9.resumeWith(r3)
            L4d:
                androidx.compose.ui.input.pointer.SuspendingPointerInputModifierNodeImpl r9 = androidx.compose.ui.input.pointer.SuspendingPointerInputModifierNodeImpl.this
                ka.k0 r9 = r9.getCoroutineScope()
                androidx.compose.ui.input.pointer.SuspendingPointerInputModifierNodeImpl$PointerEventHandlerCoroutine$withTimeout$job$1 r1 = new androidx.compose.ui.input.pointer.SuspendingPointerInputModifierNodeImpl$PointerEventHandlerCoroutine$withTimeout$job$1
                r3 = 0
                r1.<init>(r6, r5, r3)
                r6 = 3
                r7 = 0
                ka.o2 r6 = ka.m0.p(r9, r3, r7, r1, r6)
                r0.L$0 = r6     // Catch: java.lang.Throwable -> L29
                r0.label = r2     // Catch: java.lang.Throwable -> L29
                java.lang.Object r9 = r8.invoke(r5, r0)     // Catch: java.lang.Throwable -> L29
                e7.a r7 = e7.a.f15033i
                if (r9 != r7) goto L6c
                return r7
            L6c:
                androidx.compose.ui.input.pointer.CancelTimeoutCancellationException r7 = androidx.compose.ui.input.pointer.CancelTimeoutCancellationException.INSTANCE
                r6.cancel(r7)
                return r9
            L72:
                androidx.compose.ui.input.pointer.CancelTimeoutCancellationException r8 = androidx.compose.ui.input.pointer.CancelTimeoutCancellationException.INSTANCE
                r6.cancel(r8)
                throw r7
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.compose.ui.input.pointer.SuspendingPointerInputModifierNodeImpl.PointerEventHandlerCoroutine.withTimeout(long, r7.p, d7.d):java.lang.Object");
        }

        /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
        @Override // androidx.compose.ui.input.pointer.AwaitPointerEventScope
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public <T> java.lang.Object withTimeoutOrNull(long r5, r7.p<? super androidx.compose.ui.input.pointer.AwaitPointerEventScope, ? super d7.d<? super T>, ? extends java.lang.Object> r7, d7.d<? super T> r8) {
            /*
                r4 = this;
                boolean r0 = r8 instanceof androidx.compose.ui.input.pointer.SuspendingPointerInputModifierNodeImpl$PointerEventHandlerCoroutine$withTimeoutOrNull$1
                if (r0 == 0) goto L13
                r0 = r8
                androidx.compose.ui.input.pointer.SuspendingPointerInputModifierNodeImpl$PointerEventHandlerCoroutine$withTimeoutOrNull$1 r0 = (androidx.compose.ui.input.pointer.SuspendingPointerInputModifierNodeImpl$PointerEventHandlerCoroutine$withTimeoutOrNull$1) r0
                int r1 = r0.label
                r2 = -2147483648(0xffffffff80000000, float:-0.0)
                r3 = r1 & r2
                if (r3 == 0) goto L13
                int r1 = r1 - r2
                r0.label = r1
                goto L18
            L13:
                androidx.compose.ui.input.pointer.SuspendingPointerInputModifierNodeImpl$PointerEventHandlerCoroutine$withTimeoutOrNull$1 r0 = new androidx.compose.ui.input.pointer.SuspendingPointerInputModifierNodeImpl$PointerEventHandlerCoroutine$withTimeoutOrNull$1
                r0.<init>(r4, r8)
            L18:
                java.lang.Object r8 = r0.result
                int r1 = r0.label
                r2 = 1
                if (r1 == 0) goto L2d
                if (r1 != r2) goto L25
                k2.c.G(r8)     // Catch: androidx.compose.ui.input.pointer.PointerEventTimeoutCancellationException -> L3c
                return r8
            L25:
                java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
                java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
                r5.<init>(r6)
                throw r5
            L2d:
                k2.c.G(r8)
                r0.label = r2     // Catch: androidx.compose.ui.input.pointer.PointerEventTimeoutCancellationException -> L3c
                java.lang.Object r5 = r4.withTimeout(r5, r7, r0)     // Catch: androidx.compose.ui.input.pointer.PointerEventTimeoutCancellationException -> L3c
                e7.a r6 = e7.a.f15033i
                if (r5 != r6) goto L3b
                return r6
            L3b:
                return r5
            L3c:
                r5 = 0
                return r5
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.compose.ui.input.pointer.SuspendingPointerInputModifierNodeImpl.PointerEventHandlerCoroutine.withTimeoutOrNull(long, r7.p, d7.d):java.lang.Object");
        }

        @Override // androidx.compose.ui.unit.Density
        /* JADX INFO: renamed from: toDp-u2uoSUM */
        public float mo282toDpu2uoSUM(int i10) {
            SuspendingPointerInputModifierNodeImpl suspendingPointerInputModifierNodeImpl = this.$$delegate_0;
            suspendingPointerInputModifierNodeImpl.getClass();
            return androidx.compose.ui.unit.a.d(suspendingPointerInputModifierNodeImpl, i10);
        }

        @Override // androidx.compose.ui.unit.Density
        /* JADX INFO: renamed from: toSp-kPz2Gy4 */
        public long mo289toSpkPz2Gy4(int i10) {
            SuspendingPointerInputModifierNodeImpl suspendingPointerInputModifierNodeImpl = this.$$delegate_0;
            suspendingPointerInputModifierNodeImpl.getClass();
            return androidx.compose.ui.unit.a.k(suspendingPointerInputModifierNodeImpl, i10);
        }
    }

    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[PointerEventPass.values().length];
            try {
                iArr[PointerEventPass.Initial.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[PointerEventPass.Final.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[PointerEventPass.Main.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public SuspendingPointerInputModifierNodeImpl(p<? super PointerInputScope, ? super d<? super t0>, ? extends Object> pVar) {
        this.pointerInputHandler = pVar;
    }

    private final void dispatchPointerEvent(PointerEvent pointerEvent, PointerEventPass pass) {
        MutableVector<PointerEventHandlerCoroutine<?>> mutableVector;
        int size;
        synchronized (this.pointerHandlers) {
            MutableVector<PointerEventHandlerCoroutine<?>> mutableVector2 = this.dispatchingPointerHandlers;
            mutableVector2.addAll(mutableVector2.getSize(), this.pointerHandlers);
        }
        try {
            int i10 = WhenMappings.$EnumSwitchMapping$0[pass.ordinal()];
            if (i10 == 1 || i10 == 2) {
                MutableVector<PointerEventHandlerCoroutine<?>> mutableVector3 = this.dispatchingPointerHandlers;
                int size2 = mutableVector3.getSize();
                if (size2 > 0) {
                    PointerEventHandlerCoroutine<?>[] content = mutableVector3.getContent();
                    int i11 = 0;
                    do {
                        content[i11].offerPointerEvent(pointerEvent, pass);
                        i11++;
                    } while (i11 < size2);
                }
            } else if (i10 == 3 && (size = (mutableVector = this.dispatchingPointerHandlers).getSize()) > 0) {
                int i12 = size - 1;
                PointerEventHandlerCoroutine<?>[] content2 = mutableVector.getContent();
                do {
                    content2[i12].offerPointerEvent(pointerEvent, pass);
                    i12--;
                } while (i12 >= 0);
            }
        } finally {
            this.dispatchingPointerHandlers.clear();
        }
    }

    private final void forEachCurrentPointerHandler(PointerEventPass pass, r7.l<? super PointerEventHandlerCoroutine<?>, t0> block) {
        MutableVector<PointerEventHandlerCoroutine<?>> mutableVector;
        int size;
        synchronized (this.pointerHandlers) {
            MutableVector<PointerEventHandlerCoroutine<?>> mutableVector2 = this.dispatchingPointerHandlers;
            mutableVector2.addAll(mutableVector2.getSize(), this.pointerHandlers);
        }
        try {
            int i10 = WhenMappings.$EnumSwitchMapping$0[pass.ordinal()];
            if (i10 == 1 || i10 == 2) {
                MutableVector<PointerEventHandlerCoroutine<?>> mutableVector3 = this.dispatchingPointerHandlers;
                int size2 = mutableVector3.getSize();
                if (size2 > 0) {
                    PointerEventHandlerCoroutine<?>[] content = mutableVector3.getContent();
                    int i11 = 0;
                    do {
                        block.invoke(content[i11]);
                        i11++;
                    } while (i11 < size2);
                }
            } else if (i10 == 3 && (size = (mutableVector = this.dispatchingPointerHandlers).getSize()) > 0) {
                int i12 = size - 1;
                PointerEventHandlerCoroutine<?>[] content2 = mutableVector.getContent();
                do {
                    block.invoke(content2[i12]);
                    i12--;
                } while (i12 >= 0);
            }
        } finally {
            this.dispatchingPointerHandlers.clear();
        }
    }

    @Override // androidx.compose.ui.input.pointer.PointerInputScope
    public <R> Object awaitPointerEventScope(p<? super AwaitPointerEventScope, ? super d<? super R>, ? extends Object> pVar, d<? super R> dVar) {
        l lVar = new l(1, t7.a.A(dVar));
        lVar.s();
        PointerEventHandlerCoroutine pointerEventHandlerCoroutine = new PointerEventHandlerCoroutine(lVar);
        synchronized (this.pointerHandlers) {
            this.pointerHandlers.add(pointerEventHandlerCoroutine);
            new m(t7.a.A(t7.a.m(pointerEventHandlerCoroutine, pointerEventHandlerCoroutine, pVar)), e7.a.f15033i).resumeWith(t0.f22605a);
        }
        lVar.c(new SuspendingPointerInputModifierNodeImpl$awaitPointerEventScope$2$2(pointerEventHandlerCoroutine));
        return lVar.r();
    }

    @Override // androidx.compose.ui.unit.Density
    public float getDensity() {
        return DelegatableNodeKt.requireLayoutNode(this).getDensity().getDensity();
    }

    @Override // androidx.compose.ui.input.pointer.PointerInputScope
    /* JADX INFO: renamed from: getExtendedTouchPadding-NH-jbRc */
    public long mo276getExtendedTouchPaddingNHjbRc() {
        long jI = androidx.compose.ui.unit.a.i(this, getViewConfiguration().mo4756getMinimumTouchTargetSizeMYxV2XQ());
        long boundsSize = getBoundsSize();
        return SizeKt.Size(Math.max(0.0f, Size.m3305getWidthimpl(jI) - IntSize.m5844getWidthimpl(boundsSize)) / 2.0f, Math.max(0.0f, Size.m3302getHeightimpl(jI) - IntSize.m5843getHeightimpl(boundsSize)) / 2.0f);
    }

    @Override // androidx.compose.ui.unit.FontScaling
    public float getFontScale() {
        return DelegatableNodeKt.requireLayoutNode(this).getDensity().getFontScale();
    }

    @Override // androidx.compose.ui.input.pointer.PointerInputScope
    public boolean getInterceptOutOfBoundsChildEvents() {
        return this.interceptOutOfBoundsChildEvents;
    }

    @Override // androidx.compose.ui.input.pointer.SuspendingPointerInputModifierNode
    public p<PointerInputScope, d<? super t0>, Object> getPointerInputHandler() {
        return this.pointerInputHandler;
    }

    @Override // androidx.compose.ui.input.pointer.PointerInputScope
    /* JADX INFO: renamed from: getSize-YbymL2g, reason: from getter */
    public long getBoundsSize() {
        return this.boundsSize;
    }

    @Override // androidx.compose.ui.input.pointer.PointerInputScope
    public ViewConfiguration getViewConfiguration() {
        return DelegatableNodeKt.requireLayoutNode(this).getViewConfiguration();
    }

    @Override // androidx.compose.ui.node.PointerInputModifierNode
    public final /* synthetic */ boolean interceptOutOfBoundsChildEvents() {
        return f.a(this);
    }

    @Override // androidx.compose.ui.node.PointerInputModifierNode
    public void onCancelPointerInput() {
        PointerEvent pointerEvent = this.lastPointerEvent;
        if (pointerEvent == null) {
            return;
        }
        List<PointerInputChange> changes = pointerEvent.getChanges();
        int size = changes.size();
        for (int i10 = 0; i10 < size; i10++) {
            if (changes.get(i10).getPressed()) {
                List<PointerInputChange> changes2 = pointerEvent.getChanges();
                ArrayList arrayList = new ArrayList(changes2.size());
                int size2 = changes2.size();
                for (int i11 = 0; i11 < size2; i11++) {
                    PointerInputChange pointerInputChange = changes2.get(i11);
                    arrayList.add(new PointerInputChange(pointerInputChange.getId(), pointerInputChange.getUptimeMillis(), pointerInputChange.getPosition(), false, pointerInputChange.getPressure(), pointerInputChange.getUptimeMillis(), pointerInputChange.getPosition(), pointerInputChange.getPressed(), pointerInputChange.getPressed(), 0, 0L, 1536, (h) null));
                }
                PointerEvent pointerEvent2 = new PointerEvent(arrayList);
                this.currentEvent = pointerEvent2;
                dispatchPointerEvent(pointerEvent2, PointerEventPass.Initial);
                dispatchPointerEvent(pointerEvent2, PointerEventPass.Main);
                dispatchPointerEvent(pointerEvent2, PointerEventPass.Final);
                this.lastPointerEvent = null;
                return;
            }
        }
    }

    @Override // androidx.compose.ui.node.PointerInputModifierNode
    public void onDensityChange() {
        resetPointerInputHandler();
    }

    @Override // androidx.compose.ui.Modifier.Node
    public void onDetach() {
        resetPointerInputHandler();
        super.onDetach();
    }

    @Override // androidx.compose.ui.node.PointerInputModifierNode
    /* JADX INFO: renamed from: onPointerEvent-H0pRuoY */
    public void mo148onPointerEventH0pRuoY(PointerEvent pointerEvent, PointerEventPass pass, long bounds) {
        this.boundsSize = bounds;
        if (pass == PointerEventPass.Initial) {
            this.currentEvent = pointerEvent;
        }
        if (this.pointerInputJob == null) {
            this.pointerInputJob = m0.p(getCoroutineScope(), null, 4, new SuspendingPointerInputModifierNodeImpl$onPointerEvent$1(this, null), 1);
        }
        dispatchPointerEvent(pointerEvent, pass);
        List<PointerInputChange> changes = pointerEvent.getChanges();
        int size = changes.size();
        int i10 = 0;
        while (true) {
            if (i10 >= size) {
                pointerEvent = null;
                break;
            } else if (!PointerEventKt.changedToUpIgnoreConsumed(changes.get(i10))) {
                break;
            } else {
                i10++;
            }
        }
        this.lastPointerEvent = pointerEvent;
    }

    @Override // androidx.compose.ui.node.PointerInputModifierNode
    public void onViewConfigurationChange() {
        resetPointerInputHandler();
    }

    @Override // androidx.compose.ui.input.pointer.SuspendingPointerInputModifierNode
    public void resetPointerInputHandler() {
        v1 v1Var = this.pointerInputJob;
        if (v1Var != null) {
            v1Var.cancel((CancellationException) new PointerInputResetException());
            this.pointerInputJob = null;
        }
    }

    @Override // androidx.compose.ui.unit.Density
    /* JADX INFO: renamed from: roundToPx--R2X_6o */
    public final /* synthetic */ int mo278roundToPxR2X_6o(long j10) {
        return androidx.compose.ui.unit.a.a(this, j10);
    }

    @Override // androidx.compose.ui.unit.Density
    /* JADX INFO: renamed from: roundToPx-0680j_4 */
    public final /* synthetic */ int mo279roundToPx0680j_4(float f10) {
        return androidx.compose.ui.unit.a.b(this, f10);
    }

    @Override // androidx.compose.ui.input.pointer.PointerInputScope
    public void setInterceptOutOfBoundsChildEvents(boolean z) {
        this.interceptOutOfBoundsChildEvents = z;
    }

    @Override // androidx.compose.ui.input.pointer.SuspendingPointerInputModifierNode
    public void setPointerInputHandler(p<? super PointerInputScope, ? super d<? super t0>, ? extends Object> pVar) {
        resetPointerInputHandler();
        this.pointerInputHandler = pVar;
    }

    @Override // androidx.compose.ui.node.PointerInputModifierNode
    public final /* synthetic */ boolean sharePointerInputWithSiblings() {
        return f.d(this);
    }

    @Override // androidx.compose.ui.unit.FontScaling
    /* JADX INFO: renamed from: toDp-GaN1DYA */
    public final /* synthetic */ float mo280toDpGaN1DYA(long j10) {
        return androidx.compose.ui.unit.b.a(this, j10);
    }

    @Override // androidx.compose.ui.unit.Density
    /* JADX INFO: renamed from: toDp-u2uoSUM */
    public final /* synthetic */ float mo281toDpu2uoSUM(float f10) {
        return androidx.compose.ui.unit.a.c(this, f10);
    }

    @Override // androidx.compose.ui.unit.Density
    /* JADX INFO: renamed from: toDpSize-k-rfVVM */
    public final /* synthetic */ long mo283toDpSizekrfVVM(long j10) {
        return androidx.compose.ui.unit.a.e(this, j10);
    }

    @Override // androidx.compose.ui.unit.Density
    /* JADX INFO: renamed from: toPx--R2X_6o */
    public final /* synthetic */ float mo284toPxR2X_6o(long j10) {
        return androidx.compose.ui.unit.a.f(this, j10);
    }

    @Override // androidx.compose.ui.unit.Density
    /* JADX INFO: renamed from: toPx-0680j_4 */
    public final /* synthetic */ float mo285toPx0680j_4(float f10) {
        return androidx.compose.ui.unit.a.g(this, f10);
    }

    @Override // androidx.compose.ui.unit.Density
    public final /* synthetic */ Rect toRect(DpRect dpRect) {
        return androidx.compose.ui.unit.a.h(this, dpRect);
    }

    @Override // androidx.compose.ui.unit.Density
    /* JADX INFO: renamed from: toSize-XkaWNTQ */
    public final /* synthetic */ long mo286toSizeXkaWNTQ(long j10) {
        return androidx.compose.ui.unit.a.i(this, j10);
    }

    @Override // androidx.compose.ui.unit.FontScaling
    /* JADX INFO: renamed from: toSp-0xMU5do */
    public final /* synthetic */ long mo287toSp0xMU5do(float f10) {
        return androidx.compose.ui.unit.b.b(this, f10);
    }

    @Override // androidx.compose.ui.unit.Density
    /* JADX INFO: renamed from: toSp-kPz2Gy4 */
    public final /* synthetic */ long mo288toSpkPz2Gy4(float f10) {
        return androidx.compose.ui.unit.a.j(this, f10);
    }

    @Override // androidx.compose.ui.unit.Density
    /* JADX INFO: renamed from: toDp-u2uoSUM */
    public final /* synthetic */ float mo282toDpu2uoSUM(int i10) {
        return androidx.compose.ui.unit.a.d(this, i10);
    }

    @Override // androidx.compose.ui.unit.Density
    /* JADX INFO: renamed from: toSp-kPz2Gy4 */
    public final /* synthetic */ long mo289toSpkPz2Gy4(int i10) {
        return androidx.compose.ui.unit.a.k(this, i10);
    }
}
