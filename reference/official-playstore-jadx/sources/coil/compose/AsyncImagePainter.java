package coil.compose;

import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.Precision;
import android.view.SizeResolver;
import androidx.compose.runtime.MutableFloatState;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.PrimitiveSnapshotStateKt;
import androidx.compose.runtime.RememberObserver;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.runtime.SnapshotStateKt__SnapshotStateKt;
import androidx.compose.ui.geometry.Size;
import androidx.compose.ui.graphics.AndroidImageBitmap_androidKt;
import androidx.compose.ui.graphics.ColorFilter;
import androidx.compose.ui.graphics.drawscope.DrawScope;
import androidx.compose.ui.graphics.painter.BitmapPainterKt;
import androidx.compose.ui.graphics.painter.Painter;
import androidx.compose.ui.layout.ContentScale;
import coil.ImageLoader;
import coil.compose.AsyncImagePainter;
import coil.request.ErrorResult;
import coil.request.ImageRequest;
import coil.request.ImageResult;
import coil.request.SuccessResult;
import coil.target.Target;
import coil.transition.CrossfadeTransition;
import coil.transition.Transition;
import com.google.accompanist.drawablepainter.DrawablePainter;
import d7.d;
import f7.e;
import f7.j;
import k2.c;
import ka.k0;
import ka.l0;
import ka.m0;
import ka.p2;
import ka.x0;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.internal.h;
import kotlin.jvm.internal.p;
import kotlin.jvm.internal.r;
import na.b0;
import na.c0;
import na.k;
import na.q0;
import na.y0;
import oa.i;
import pa.q;
import r7.a;
import r7.l;
import x6.o;
import x6.t0;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\u0088\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u001c\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b!\b\u0007\u0018\u0000 }2\u00020\u00012\u00020\u0002:\u0002}~B\u0019\b\u0000\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0005¢\u0006\u0004\b\u0007\u0010\bJ\u0013\u0010\u000b\u001a\u00020\n*\u00020\tH\u0014¢\u0006\u0004\b\u000b\u0010\fJ\u0017\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\u000e\u001a\u00020\rH\u0014¢\u0006\u0004\b\u0010\u0010\u0011J\u0019\u0010\u0014\u001a\u00020\u000f2\b\u0010\u0013\u001a\u0004\u0018\u00010\u0012H\u0014¢\u0006\u0004\b\u0014\u0010\u0015J\u000f\u0010\u0016\u001a\u00020\nH\u0016¢\u0006\u0004\b\u0016\u0010\u0017J\u000f\u0010\u0018\u001a\u00020\nH\u0016¢\u0006\u0004\b\u0018\u0010\u0017J\u000f\u0010\u0019\u001a\u00020\nH\u0016¢\u0006\u0004\b\u0019\u0010\u0017J\u000f\u0010\u001a\u001a\u00020\nH\u0002¢\u0006\u0004\b\u001a\u0010\u0017J\u0017\u0010\u001b\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0003H\u0002¢\u0006\u0004\b\u001b\u0010\u001cJ\u0017\u0010\u001f\u001a\u00020\n2\u0006\u0010\u001e\u001a\u00020\u001dH\u0002¢\u0006\u0004\b\u001f\u0010 J!\u0010$\u001a\u0004\u0018\u00010#2\u0006\u0010!\u001a\u00020\u001d2\u0006\u0010\"\u001a\u00020\u001dH\u0002¢\u0006\u0004\b$\u0010%J\u0013\u0010'\u001a\u00020\u001d*\u00020&H\u0002¢\u0006\u0004\b'\u0010(J\u0013\u0010*\u001a\u00020\u0001*\u00020)H\u0002¢\u0006\u0004\b*\u0010+R\u0018\u0010-\u001a\u0004\u0018\u00010,8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b-\u0010.R\u001d\u00101\u001a\b\u0012\u0004\u0012\u0002000/8\u0002X\u0082\u0004ø\u0001\u0000¢\u0006\u0006\n\u0004\b1\u00102R/\u0010:\u001a\u0004\u0018\u00010\u00012\b\u00103\u001a\u0004\u0018\u00010\u00018B@BX\u0082\u008e\u0002¢\u0006\u0012\n\u0004\b4\u00105\u001a\u0004\b6\u00107\"\u0004\b8\u00109R+\u0010\u000e\u001a\u00020\r2\u0006\u00103\u001a\u00020\r8B@BX\u0082\u008e\u0002¢\u0006\u0012\n\u0004\b;\u0010<\u001a\u0004\b=\u0010>\"\u0004\b?\u0010@R/\u0010\u0013\u001a\u0004\u0018\u00010\u00122\b\u00103\u001a\u0004\u0018\u00010\u00128B@BX\u0082\u008e\u0002¢\u0006\u0012\n\u0004\bA\u00105\u001a\u0004\bB\u0010C\"\u0004\bD\u0010ER$\u0010G\u001a\u00020\u001d2\u0006\u0010F\u001a\u00020\u001d8\u0002@BX\u0082\u000e¢\u0006\f\n\u0004\bG\u0010H\"\u0004\bI\u0010 R(\u0010J\u001a\u0004\u0018\u00010\u00012\b\u0010F\u001a\u0004\u0018\u00010\u00018\u0002@BX\u0082\u000e¢\u0006\f\n\u0004\bJ\u0010K\"\u0004\bL\u00109R.\u0010N\u001a\u000e\u0012\u0004\u0012\u00020\u001d\u0012\u0004\u0012\u00020\u001d0M8\u0000@\u0000X\u0080\u000e¢\u0006\u0012\n\u0004\bN\u0010O\u001a\u0004\bP\u0010Q\"\u0004\bR\u0010SR0\u0010T\u001a\u0010\u0012\u0004\u0012\u00020\u001d\u0012\u0004\u0012\u00020\n\u0018\u00010M8\u0000@\u0000X\u0080\u000e¢\u0006\u0012\n\u0004\bT\u0010O\u001a\u0004\bU\u0010Q\"\u0004\bV\u0010SR\"\u0010X\u001a\u00020W8\u0000@\u0000X\u0080\u000e¢\u0006\u0012\n\u0004\bX\u0010Y\u001a\u0004\bZ\u0010[\"\u0004\b\\\u0010]R+\u0010_\u001a\u00020^8\u0000@\u0000X\u0080\u000eø\u0001\u0000ø\u0001\u0001ø\u0001\u0002¢\u0006\u0012\n\u0004\b_\u0010`\u001a\u0004\ba\u0010b\"\u0004\bc\u0010dR\"\u0010e\u001a\u00020\u000f8\u0000@\u0000X\u0080\u000e¢\u0006\u0012\n\u0004\be\u0010f\u001a\u0004\bg\u0010h\"\u0004\bi\u0010jR+\u0010o\u001a\u00020\u001d2\u0006\u00103\u001a\u00020\u001d8F@BX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\bk\u00105\u001a\u0004\bl\u0010m\"\u0004\bn\u0010 R+\u0010\u0004\u001a\u00020\u00032\u0006\u00103\u001a\u00020\u00038F@@X\u0086\u008e\u0002¢\u0006\u0012\n\u0004\bp\u00105\u001a\u0004\bq\u0010r\"\u0004\bs\u0010tR+\u0010\u0006\u001a\u00020\u00052\u0006\u00103\u001a\u00020\u00058F@@X\u0086\u008e\u0002¢\u0006\u0012\n\u0004\bu\u00105\u001a\u0004\bv\u0010w\"\u0004\bx\u0010yR\u001d\u0010|\u001a\u0002008VX\u0096\u0004ø\u0001\u0000ø\u0001\u0001ø\u0001\u0002¢\u0006\u0006\u001a\u0004\bz\u0010{\u0082\u0002\u000f\n\u0002\b\u0019\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u0006\u007f"}, d2 = {"Lcoil/compose/AsyncImagePainter;", "Landroidx/compose/ui/graphics/painter/Painter;", "Landroidx/compose/runtime/RememberObserver;", "Lcoil/request/ImageRequest;", "request", "Lcoil/ImageLoader;", "imageLoader", "<init>", "(Lcoil/request/ImageRequest;Lcoil/ImageLoader;)V", "Landroidx/compose/ui/graphics/drawscope/DrawScope;", "Lx6/t0;", "onDraw", "(Landroidx/compose/ui/graphics/drawscope/DrawScope;)V", "", "alpha", "", "applyAlpha", "(F)Z", "Landroidx/compose/ui/graphics/ColorFilter;", "colorFilter", "applyColorFilter", "(Landroidx/compose/ui/graphics/ColorFilter;)Z", "onRemembered", "()V", "onForgotten", "onAbandoned", "clear", "updateRequest", "(Lcoil/request/ImageRequest;)Lcoil/request/ImageRequest;", "Lcoil/compose/AsyncImagePainter$State;", "input", "updateState", "(Lcoil/compose/AsyncImagePainter$State;)V", "previous", "current", "Lcoil/compose/CrossfadePainter;", "maybeNewCrossfadePainter", "(Lcoil/compose/AsyncImagePainter$State;Lcoil/compose/AsyncImagePainter$State;)Lcoil/compose/CrossfadePainter;", "Lcoil/request/ImageResult;", "toState", "(Lcoil/request/ImageResult;)Lcoil/compose/AsyncImagePainter$State;", "Landroid/graphics/drawable/Drawable;", "toPainter", "(Landroid/graphics/drawable/Drawable;)Landroidx/compose/ui/graphics/painter/Painter;", "Lka/k0;", "rememberScope", "Lka/k0;", "Lna/q0;", "Landroidx/compose/ui/geometry/Size;", "drawSize", "Lna/q0;", "<set-?>", "painter$delegate", "Landroidx/compose/runtime/MutableState;", "getPainter", "()Landroidx/compose/ui/graphics/painter/Painter;", "setPainter", "(Landroidx/compose/ui/graphics/painter/Painter;)V", "painter", "alpha$delegate", "Landroidx/compose/runtime/MutableFloatState;", "getAlpha", "()F", "setAlpha", "(F)V", "colorFilter$delegate", "getColorFilter", "()Landroidx/compose/ui/graphics/ColorFilter;", "setColorFilter", "(Landroidx/compose/ui/graphics/ColorFilter;)V", "value", "_state", "Lcoil/compose/AsyncImagePainter$State;", "set_state", "_painter", "Landroidx/compose/ui/graphics/painter/Painter;", "set_painter", "Lkotlin/Function1;", "transform", "Lr7/l;", "getTransform$coil_compose_base_release", "()Lr7/l;", "setTransform$coil_compose_base_release", "(Lr7/l;)V", "onState", "getOnState$coil_compose_base_release", "setOnState$coil_compose_base_release", "Landroidx/compose/ui/layout/ContentScale;", "contentScale", "Landroidx/compose/ui/layout/ContentScale;", "getContentScale$coil_compose_base_release", "()Landroidx/compose/ui/layout/ContentScale;", "setContentScale$coil_compose_base_release", "(Landroidx/compose/ui/layout/ContentScale;)V", "Landroidx/compose/ui/graphics/FilterQuality;", "filterQuality", "I", "getFilterQuality-f-v9h1I$coil_compose_base_release", "()I", "setFilterQuality-vDHp3xo$coil_compose_base_release", "(I)V", "isPreview", "Z", "isPreview$coil_compose_base_release", "()Z", "setPreview$coil_compose_base_release", "(Z)V", "state$delegate", "getState", "()Lcoil/compose/AsyncImagePainter$State;", "setState", "state", "request$delegate", "getRequest", "()Lcoil/request/ImageRequest;", "setRequest$coil_compose_base_release", "(Lcoil/request/ImageRequest;)V", "imageLoader$delegate", "getImageLoader", "()Lcoil/ImageLoader;", "setImageLoader$coil_compose_base_release", "(Lcoil/ImageLoader;)V", "getIntrinsicSize-NH-jbRc", "()J", "intrinsicSize", "Companion", "State", "coil-compose-base_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final class AsyncImagePainter extends Painter implements RememberObserver {
    public static final int $stable = 0;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final l<State, State> DefaultTransform = AsyncImagePainter$Companion$DefaultTransform$1.INSTANCE;
    private Painter _painter;
    private State _state;
    private ContentScale contentScale;
    private int filterQuality;

    /* JADX INFO: renamed from: imageLoader$delegate, reason: from kotlin metadata */
    private final MutableState imageLoader;
    private boolean isPreview;
    private l<? super State, t0> onState;
    private k0 rememberScope;

    /* JADX INFO: renamed from: request$delegate, reason: from kotlin metadata */
    private final MutableState request;

    /* JADX INFO: renamed from: state$delegate, reason: from kotlin metadata */
    private final MutableState state;
    private l<? super State, ? extends State> transform;
    private final q0<Size> drawSize = y0.b(Size.m3293boximpl(Size.INSTANCE.m3314getZeroNHjbRc()));

    /* JADX INFO: renamed from: painter$delegate, reason: from kotlin metadata */
    private final MutableState painter = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(null, null, 2, null);

    /* JADX INFO: renamed from: alpha$delegate, reason: from kotlin metadata */
    private final MutableFloatState alpha = PrimitiveSnapshotStateKt.mutableFloatStateOf(1.0f);

    /* JADX INFO: renamed from: colorFilter$delegate, reason: from kotlin metadata */
    private final MutableState colorFilter = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(null, null, 2, null);

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R#\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00050\u00048\u0006¢\u0006\f\n\u0004\b\u0006\u0010\u0007\u001a\u0004\b\b\u0010\t¨\u0006\n"}, d2 = {"Lcoil/compose/AsyncImagePainter$Companion;", "", "<init>", "()V", "Lkotlin/Function1;", "Lcoil/compose/AsyncImagePainter$State;", "DefaultTransform", "Lr7/l;", "getDefaultTransform", "()Lr7/l;", "coil-compose-base_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(h hVar) {
            this();
        }

        public final l<State, State> getDefaultTransform() {
            return AsyncImagePainter.DefaultTransform;
        }

        private Companion() {
        }
    }

    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b7\u0018\u00002\u00020\u0001:\u0004\u0007\b\t\nB\u0007\b\u0004¢\u0006\u0002\u0010\u0002R\u0014\u0010\u0003\u001a\u0004\u0018\u00010\u0004X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006\u0082\u0001\u0004\u000b\f\r\u000e¨\u0006\u000f"}, d2 = {"Lcoil/compose/AsyncImagePainter$State;", "", "()V", "painter", "Landroidx/compose/ui/graphics/painter/Painter;", "getPainter", "()Landroidx/compose/ui/graphics/painter/Painter;", "Empty", "Error", "Loading", "Success", "Lcoil/compose/AsyncImagePainter$State$Empty;", "Lcoil/compose/AsyncImagePainter$State$Error;", "Lcoil/compose/AsyncImagePainter$State$Loading;", "Lcoil/compose/AsyncImagePainter$State$Success;", "coil-compose-base_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static abstract class State {
        public static final int $stable = 0;

        @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\bÇ\n\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0013\u0010\u0007\u001a\u00020\b2\b\u0010\t\u001a\u0004\u0018\u00010\nHÖ\u0003J\t\u0010\u000b\u001a\u00020\fHÖ\u0001J\t\u0010\r\u001a\u00020\u000eHÖ\u0001R\u0016\u0010\u0003\u001a\u0004\u0018\u00010\u00048VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u000f"}, d2 = {"Lcoil/compose/AsyncImagePainter$State$Empty;", "Lcoil/compose/AsyncImagePainter$State;", "()V", "painter", "Landroidx/compose/ui/graphics/painter/Painter;", "getPainter", "()Landroidx/compose/ui/graphics/painter/Painter;", "equals", "", "other", "", "hashCode", "", "toString", "", "coil-compose-base_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
        public static final /* data */ class Empty extends State {
            public static final int $stable = 0;
            public static final Empty INSTANCE = new Empty();

            private Empty() {
                super(null);
            }

            public boolean equals(Object other) {
                return this == other || (other instanceof Empty);
            }

            @Override // coil.compose.AsyncImagePainter.State
            public Painter getPainter() {
                return null;
            }

            public int hashCode() {
                return -1515560141;
            }

            public String toString() {
                return "Empty";
            }
        }

        @Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0087\b\u0018\u00002\u00020\u0001B\u0017\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u000b\u0010\u000b\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\t\u0010\f\u001a\u00020\u0005HÆ\u0003J\u001f\u0010\r\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011HÖ\u0003J\t\u0010\u0012\u001a\u00020\u0013HÖ\u0001J\t\u0010\u0014\u001a\u00020\u0015HÖ\u0001R\u0016\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u0016"}, d2 = {"Lcoil/compose/AsyncImagePainter$State$Error;", "Lcoil/compose/AsyncImagePainter$State;", "painter", "Landroidx/compose/ui/graphics/painter/Painter;", "result", "Lcoil/request/ErrorResult;", "(Landroidx/compose/ui/graphics/painter/Painter;Lcoil/request/ErrorResult;)V", "getPainter", "()Landroidx/compose/ui/graphics/painter/Painter;", "getResult", "()Lcoil/request/ErrorResult;", "component1", "component2", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "coil-compose-base_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
        public static final /* data */ class Error extends State {
            public static final int $stable = 8;
            private final Painter painter;
            private final ErrorResult result;

            public Error(Painter painter, ErrorResult errorResult) {
                super(null);
                this.painter = painter;
                this.result = errorResult;
            }

            public static /* synthetic */ Error copy$default(Error error, Painter painter, ErrorResult errorResult, int i10, Object obj) {
                if ((i10 & 1) != 0) {
                    painter = error.painter;
                }
                if ((i10 & 2) != 0) {
                    errorResult = error.result;
                }
                return error.copy(painter, errorResult);
            }

            /* JADX INFO: renamed from: component1, reason: from getter */
            public final Painter getPainter() {
                return this.painter;
            }

            /* JADX INFO: renamed from: component2, reason: from getter */
            public final ErrorResult getResult() {
                return this.result;
            }

            public final Error copy(Painter painter, ErrorResult result) {
                return new Error(painter, result);
            }

            public boolean equals(Object other) {
                if (this == other) {
                    return true;
                }
                if (!(other instanceof Error)) {
                    return false;
                }
                Error error = (Error) other;
                return p.a(this.painter, error.painter) && p.a(this.result, error.result);
            }

            @Override // coil.compose.AsyncImagePainter.State
            public Painter getPainter() {
                return this.painter;
            }

            public final ErrorResult getResult() {
                return this.result;
            }

            public int hashCode() {
                Painter painter = this.painter;
                return this.result.hashCode() + ((painter == null ? 0 : painter.hashCode()) * 31);
            }

            public String toString() {
                return "Error(painter=" + this.painter + ", result=" + this.result + ')';
            }
        }

        @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0087\b\u0018\u00002\u00020\u0001B\u000f\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0004J\u000b\u0010\u0007\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0015\u0010\b\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fHÖ\u0003J\t\u0010\r\u001a\u00020\u000eHÖ\u0001J\t\u0010\u000f\u001a\u00020\u0010HÖ\u0001R\u0016\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0011"}, d2 = {"Lcoil/compose/AsyncImagePainter$State$Loading;", "Lcoil/compose/AsyncImagePainter$State;", "painter", "Landroidx/compose/ui/graphics/painter/Painter;", "(Landroidx/compose/ui/graphics/painter/Painter;)V", "getPainter", "()Landroidx/compose/ui/graphics/painter/Painter;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "coil-compose-base_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
        public static final /* data */ class Loading extends State {
            public static final int $stable = 8;
            private final Painter painter;

            public Loading(Painter painter) {
                super(null);
                this.painter = painter;
            }

            public static /* synthetic */ Loading copy$default(Loading loading, Painter painter, int i10, Object obj) {
                if ((i10 & 1) != 0) {
                    painter = loading.painter;
                }
                return loading.copy(painter);
            }

            /* JADX INFO: renamed from: component1, reason: from getter */
            public final Painter getPainter() {
                return this.painter;
            }

            public final Loading copy(Painter painter) {
                return new Loading(painter);
            }

            public boolean equals(Object other) {
                if (this == other) {
                    return true;
                }
                return (other instanceof Loading) && p.a(this.painter, ((Loading) other).painter);
            }

            @Override // coil.compose.AsyncImagePainter.State
            public Painter getPainter() {
                return this.painter;
            }

            public int hashCode() {
                Painter painter = this.painter;
                if (painter == null) {
                    return 0;
                }
                return painter.hashCode();
            }

            public String toString() {
                return "Loading(painter=" + this.painter + ')';
            }
        }

        @Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0087\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\t\u0010\f\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\r\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011HÖ\u0003J\t\u0010\u0012\u001a\u00020\u0013HÖ\u0001J\t\u0010\u0014\u001a\u00020\u0015HÖ\u0001R\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u0016"}, d2 = {"Lcoil/compose/AsyncImagePainter$State$Success;", "Lcoil/compose/AsyncImagePainter$State;", "painter", "Landroidx/compose/ui/graphics/painter/Painter;", "result", "Lcoil/request/SuccessResult;", "(Landroidx/compose/ui/graphics/painter/Painter;Lcoil/request/SuccessResult;)V", "getPainter", "()Landroidx/compose/ui/graphics/painter/Painter;", "getResult", "()Lcoil/request/SuccessResult;", "component1", "component2", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "coil-compose-base_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
        public static final /* data */ class Success extends State {
            public static final int $stable = 8;
            private final Painter painter;
            private final SuccessResult result;

            public Success(Painter painter, SuccessResult successResult) {
                super(null);
                this.painter = painter;
                this.result = successResult;
            }

            public static /* synthetic */ Success copy$default(Success success, Painter painter, SuccessResult successResult, int i10, Object obj) {
                if ((i10 & 1) != 0) {
                    painter = success.painter;
                }
                if ((i10 & 2) != 0) {
                    successResult = success.result;
                }
                return success.copy(painter, successResult);
            }

            /* JADX INFO: renamed from: component1, reason: from getter */
            public final Painter getPainter() {
                return this.painter;
            }

            /* JADX INFO: renamed from: component2, reason: from getter */
            public final SuccessResult getResult() {
                return this.result;
            }

            public final Success copy(Painter painter, SuccessResult result) {
                return new Success(painter, result);
            }

            public boolean equals(Object other) {
                if (this == other) {
                    return true;
                }
                if (!(other instanceof Success)) {
                    return false;
                }
                Success success = (Success) other;
                return p.a(this.painter, success.painter) && p.a(this.result, success.result);
            }

            @Override // coil.compose.AsyncImagePainter.State
            public Painter getPainter() {
                return this.painter;
            }

            public final SuccessResult getResult() {
                return this.result;
            }

            public int hashCode() {
                return this.result.hashCode() + (this.painter.hashCode() * 31);
            }

            public String toString() {
                return "Success(painter=" + this.painter + ", result=" + this.result + ')';
            }
        }

        public /* synthetic */ State(h hVar) {
            this();
        }

        public abstract Painter getPainter();

        private State() {
        }
    }

    /* JADX INFO: renamed from: coil.compose.AsyncImagePainter$onRemembered$1, reason: invalid class name */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {1, 9, 0})
    @e(c = "coil.compose.AsyncImagePainter$onRemembered$1", f = "AsyncImagePainter.kt", l = {244}, m = "invokeSuspend")
    public static final class AnonymousClass1 extends j implements r7.p<k0, d<? super t0>, Object> {
        int label;

        /* JADX INFO: renamed from: coil.compose.AsyncImagePainter$onRemembered$1$1, reason: invalid class name and collision with other inner class name */
        @Metadata(d1 = {"\u0000\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "Lcoil/request/ImageRequest;", "invoke"}, k = 3, mv = {1, 9, 0}, xi = 48)
        public static final class C01281 extends r implements a<ImageRequest> {
            final /* synthetic */ AsyncImagePainter this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public C01281(AsyncImagePainter asyncImagePainter) {
                super(0);
                this.this$0 = asyncImagePainter;
            }

            @Override // r7.a
            public final ImageRequest invoke() {
                return this.this$0.getRequest();
            }
        }

        /* JADX INFO: renamed from: coil.compose.AsyncImagePainter$onRemembered$1$2, reason: invalid class name */
        @Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u008a@"}, d2 = {"<anonymous>", "Lcoil/compose/AsyncImagePainter$State;", "it", "Lcoil/request/ImageRequest;"}, k = 3, mv = {1, 9, 0}, xi = 48)
        @e(c = "coil.compose.AsyncImagePainter$onRemembered$1$2", f = "AsyncImagePainter.kt", l = {243}, m = "invokeSuspend")
        public static final class AnonymousClass2 extends j implements r7.p<ImageRequest, d<? super State>, Object> {
            Object L$0;
            int label;
            final /* synthetic */ AsyncImagePainter this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public AnonymousClass2(AsyncImagePainter asyncImagePainter, d<? super AnonymousClass2> dVar) {
                super(2, dVar);
                this.this$0 = asyncImagePainter;
            }

            @Override // f7.a
            public final d<t0> create(Object obj, d<?> dVar) {
                return new AnonymousClass2(this.this$0, dVar);
            }

            @Override // r7.p
            public final Object invoke(ImageRequest imageRequest, d<? super State> dVar) {
                return ((AnonymousClass2) create(imageRequest, dVar)).invokeSuspend(t0.f22605a);
            }

            @Override // f7.a
            public final Object invokeSuspend(Object obj) {
                AsyncImagePainter asyncImagePainter;
                int i10 = this.label;
                if (i10 == 0) {
                    c.G(obj);
                    asyncImagePainter = this.this$0;
                    ImageLoader imageLoader = asyncImagePainter.getImageLoader();
                    AsyncImagePainter asyncImagePainter2 = this.this$0;
                    ImageRequest imageRequestUpdateRequest = asyncImagePainter2.updateRequest(asyncImagePainter2.getRequest());
                    this.L$0 = asyncImagePainter;
                    this.label = 1;
                    obj = imageLoader.execute(imageRequestUpdateRequest, this);
                    e7.a aVar = e7.a.f15033i;
                    if (obj == aVar) {
                        return aVar;
                    }
                } else {
                    if (i10 != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    asyncImagePainter = (AsyncImagePainter) this.L$0;
                    c.G(obj);
                }
                return asyncImagePainter.toState((ImageResult) obj);
            }
        }

        /* JADX INFO: renamed from: coil.compose.AsyncImagePainter$onRemembered$1$3, reason: invalid class name */
        @Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
        public /* synthetic */ class AnonymousClass3 implements k, kotlin.jvm.internal.j {
            final /* synthetic */ AsyncImagePainter $tmp0;

            public AnonymousClass3(AsyncImagePainter asyncImagePainter) {
                this.$tmp0 = asyncImagePainter;
            }

            public final Object emit(State state, d<? super t0> dVar) {
                Object objInvokeSuspend$updateState = AnonymousClass1.invokeSuspend$updateState(this.$tmp0, state, dVar);
                return objInvokeSuspend$updateState == e7.a.f15033i ? objInvokeSuspend$updateState : t0.f22605a;
            }

            public final boolean equals(Object obj) {
                if ((obj instanceof k) && (obj instanceof kotlin.jvm.internal.j)) {
                    return p.a(getFunctionDelegate(), ((kotlin.jvm.internal.j) obj).getFunctionDelegate());
                }
                return false;
            }

            @Override // kotlin.jvm.internal.j
            public final o<?> getFunctionDelegate() {
                return new kotlin.jvm.internal.a(this.$tmp0);
            }

            public final int hashCode() {
                return getFunctionDelegate().hashCode();
            }

            @Override // na.k
            public /* bridge */ /* synthetic */ Object emit(Object obj, d dVar) {
                return emit((State) obj, (d<? super t0>) dVar);
            }
        }

        public AnonymousClass1(d<? super AnonymousClass1> dVar) {
            super(2, dVar);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final /* synthetic */ Object invokeSuspend$updateState(AsyncImagePainter asyncImagePainter, State state, d dVar) {
            asyncImagePainter.updateState(state);
            return t0.f22605a;
        }

        @Override // f7.a
        public final d<t0> create(Object obj, d<?> dVar) {
            return AsyncImagePainter.this.new AnonymousClass1(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            int i10 = this.label;
            if (i10 == 0) {
                c.G(obj);
                na.j jVarSnapshotFlow = SnapshotStateKt.snapshotFlow(new C01281(AsyncImagePainter.this));
                AnonymousClass2 anonymousClass2 = new AnonymousClass2(AsyncImagePainter.this, null);
                int i11 = c0.f20542a;
                i iVarP = y0.p(jVarSnapshotFlow, new b0(anonymousClass2, (d) null));
                AnonymousClass3 anonymousClass3 = new AnonymousClass3(AsyncImagePainter.this);
                this.label = 1;
                Object objCollect = iVarP.collect(anonymousClass3, this);
                e7.a aVar = e7.a.f15033i;
                if (objCollect == aVar) {
                    return aVar;
                }
            } else {
                if (i10 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                c.G(obj);
            }
            return t0.f22605a;
        }

        @Override // r7.p
        public final Object invoke(k0 k0Var, d<? super t0> dVar) {
            return ((AnonymousClass1) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
        }
    }

    public AsyncImagePainter(ImageRequest imageRequest, ImageLoader imageLoader) {
        State.Empty empty = State.Empty.INSTANCE;
        this._state = empty;
        this.transform = DefaultTransform;
        this.contentScale = ContentScale.INSTANCE.getFit();
        this.filterQuality = DrawScope.INSTANCE.m3947getDefaultFilterQualityfv9h1I();
        this.state = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(empty, null, 2, null);
        this.request = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(imageRequest, null, 2, null);
        this.imageLoader = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(imageLoader, null, 2, null);
    }

    private final void clear() {
        k0 k0Var = this.rememberScope;
        if (k0Var != null) {
            l0.b(k0Var, null);
        }
        this.rememberScope = null;
    }

    private final float getAlpha() {
        return this.alpha.getFloatValue();
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final ColorFilter getColorFilter() {
        return (ColorFilter) this.colorFilter.getValue();
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final Painter getPainter() {
        return (Painter) this.painter.getValue();
    }

    private final CrossfadePainter maybeNewCrossfadePainter(State previous, State current) {
        ImageResult result;
        if (!(current instanceof State.Success)) {
            if (current instanceof State.Error) {
                result = ((State.Error) current).getResult();
            }
            return null;
        }
        result = ((State.Success) current).getResult();
        Transition transitionCreate = result.getRequest().getTransitionFactory().create(AsyncImagePainterKt.fakeTransitionTarget, result);
        if (transitionCreate instanceof CrossfadeTransition) {
            CrossfadeTransition crossfadeTransition = (CrossfadeTransition) transitionCreate;
            return new CrossfadePainter(previous instanceof State.Loading ? previous.getPainter() : null, current.getPainter(), this.contentScale, crossfadeTransition.getDurationMillis(), ((result instanceof SuccessResult) && ((SuccessResult) result).getIsPlaceholderCached()) ? false : true, crossfadeTransition.getPreferExactIntrinsicSize());
        }
        return null;
    }

    private final void setAlpha(float f10) {
        this.alpha.setFloatValue(f10);
    }

    private final void setColorFilter(ColorFilter colorFilter) {
        this.colorFilter.setValue(colorFilter);
    }

    private final void setPainter(Painter painter) {
        this.painter.setValue(painter);
    }

    private final void setState(State state) {
        this.state.setValue(state);
    }

    private final void set_painter(Painter painter) {
        this._painter = painter;
        setPainter(painter);
    }

    private final void set_state(State state) {
        this._state = state;
        setState(state);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Painter toPainter(Drawable drawable) {
        return drawable instanceof BitmapDrawable ? BitmapPainterKt.m4013BitmapPainterQZhYCtY$default(AndroidImageBitmap_androidKt.asImageBitmap(((BitmapDrawable) drawable).getBitmap()), 0L, 0L, this.filterQuality, 6, null) : new DrawablePainter(drawable.mutate());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final State toState(ImageResult imageResult) {
        if (imageResult instanceof SuccessResult) {
            SuccessResult successResult = (SuccessResult) imageResult;
            return new State.Success(toPainter(successResult.getDrawable()), successResult);
        }
        if (!(imageResult instanceof ErrorResult)) {
            throw new NoWhenBranchMatchedException();
        }
        Drawable drawable = imageResult.getDrawable();
        return new State.Error(drawable != null ? toPainter(drawable) : null, (ErrorResult) imageResult);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final ImageRequest updateRequest(ImageRequest request) {
        ImageRequest.Builder builderTarget = ImageRequest.newBuilder$default(request, null, 1, null).target(new Target() { // from class: coil.compose.AsyncImagePainter$updateRequest$$inlined$target$default$1
            @Override // coil.target.Target
            public void onError(Drawable error) {
            }

            @Override // coil.target.Target
            public void onStart(Drawable placeholder) {
                AsyncImagePainter asyncImagePainter = this.this$0;
                asyncImagePainter.updateState(new AsyncImagePainter.State.Loading(placeholder != null ? asyncImagePainter.toPainter(placeholder) : null));
            }

            @Override // coil.target.Target
            public void onSuccess(Drawable result) {
            }
        });
        if (request.getDefined().getSizeResolver() == null) {
            builderTarget.size(new SizeResolver() { // from class: coil.compose.AsyncImagePainter$updateRequest$2$1
                @Override // android.view.SizeResolver
                public final Object size(d<? super android.view.Size> dVar) {
                    final q0 q0Var = this.this$0.drawSize;
                    return y0.l(new na.j<android.view.Size>() { // from class: coil.compose.AsyncImagePainter$updateRequest$2$1$size$$inlined$mapNotNull$1

                        /* JADX INFO: renamed from: coil.compose.AsyncImagePainter$updateRequest$2$1$size$$inlined$mapNotNull$1$2, reason: invalid class name */
                        @Metadata(d1 = {"\u0000\f\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0006\u001a\u00020\u0003\"\u0004\b\u0000\u0010\u0000\"\u0004\b\u0001\u0010\u00012\u0006\u0010\u0002\u001a\u00028\u0000H\u008a@¢\u0006\u0004\b\u0004\u0010\u0005"}, d2 = {"T", "R", "value", "Lx6/t0;", "emit", "(Ljava/lang/Object;Ld7/d;)Ljava/lang/Object;", "<anonymous>"}, k = 3, mv = {1, 9, 0})
                        public static final class AnonymousClass2<T> implements k {
                            final /* synthetic */ k $this_unsafeFlow;

                            /* JADX INFO: renamed from: coil.compose.AsyncImagePainter$updateRequest$2$1$size$$inlined$mapNotNull$1$2$1, reason: invalid class name */
                            @Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
                            @e(c = "coil.compose.AsyncImagePainter$updateRequest$2$1$size$$inlined$mapNotNull$1$2", f = "AsyncImagePainter.kt", l = {225}, m = "emit")
                            public static final class AnonymousClass1 extends f7.c {
                                Object L$0;
                                int label;
                                /* synthetic */ Object result;

                                public AnonymousClass1(d dVar) {
                                    super(dVar);
                                }

                                @Override // f7.a
                                public final Object invokeSuspend(Object obj) {
                                    this.result = obj;
                                    this.label |= Integer.MIN_VALUE;
                                    return AnonymousClass2.this.emit(null, this);
                                }
                            }

                            public AnonymousClass2(k kVar) {
                                this.$this_unsafeFlow = kVar;
                            }

                            /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
                            @Override // na.k
                            /*
                                Code decompiled incorrectly, please refer to instructions dump.
                                To view partially-correct add '--show-bad-code' argument
                            */
                            public final java.lang.Object emit(java.lang.Object r6, d7.d r7) {
                                /*
                                    r5 = this;
                                    boolean r0 = r7 instanceof coil.compose.AsyncImagePainter$updateRequest$2$1$size$$inlined$mapNotNull$1.AnonymousClass2.AnonymousClass1
                                    if (r0 == 0) goto L13
                                    r0 = r7
                                    coil.compose.AsyncImagePainter$updateRequest$2$1$size$$inlined$mapNotNull$1$2$1 r0 = (coil.compose.AsyncImagePainter$updateRequest$2$1$size$$inlined$mapNotNull$1.AnonymousClass2.AnonymousClass1) r0
                                    int r1 = r0.label
                                    r2 = -2147483648(0xffffffff80000000, float:-0.0)
                                    r3 = r1 & r2
                                    if (r3 == 0) goto L13
                                    int r1 = r1 - r2
                                    r0.label = r1
                                    goto L18
                                L13:
                                    coil.compose.AsyncImagePainter$updateRequest$2$1$size$$inlined$mapNotNull$1$2$1 r0 = new coil.compose.AsyncImagePainter$updateRequest$2$1$size$$inlined$mapNotNull$1$2$1
                                    r0.<init>(r7)
                                L18:
                                    java.lang.Object r7 = r0.result
                                    int r1 = r0.label
                                    r2 = 1
                                    if (r1 == 0) goto L2d
                                    if (r1 != r2) goto L25
                                    k2.c.G(r7)
                                    goto L49
                                L25:
                                    java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
                                    java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
                                    r6.<init>(r7)
                                    throw r6
                                L2d:
                                    k2.c.G(r7)
                                    na.k r7 = r5.$this_unsafeFlow
                                    androidx.compose.ui.geometry.Size r6 = (androidx.compose.ui.geometry.Size) r6
                                    long r3 = r6.getPackedValue()
                                    coil.size.Size r6 = coil.compose.AsyncImagePainterKt.m6032access$toSizeOrNulluvyYCjk(r3)
                                    if (r6 == 0) goto L49
                                    r0.label = r2
                                    java.lang.Object r6 = r7.emit(r6, r0)
                                    e7.a r7 = e7.a.f15033i
                                    if (r6 != r7) goto L49
                                    return r7
                                L49:
                                    x6.t0 r6 = x6.t0.f22605a
                                    return r6
                                */
                                throw new UnsupportedOperationException("Method not decompiled: coil.compose.AsyncImagePainter$updateRequest$2$1$size$$inlined$mapNotNull$1.AnonymousClass2.emit(java.lang.Object, d7.d):java.lang.Object");
                            }
                        }

                        @Override // na.j
                        public Object collect(k kVar, d dVar2) {
                            Object objCollect = q0Var.collect(new AnonymousClass2(kVar), dVar2);
                            return objCollect == e7.a.f15033i ? objCollect : t0.f22605a;
                        }
                    }, dVar);
                }
            });
        }
        if (request.getDefined().getScale() == null) {
            builderTarget.scale(UtilsKt.toScale(this.contentScale));
        }
        if (request.getDefined().getPrecision() != Precision.EXACT) {
            builderTarget.precision(Precision.INEXACT);
        }
        return builderTarget.build();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void updateState(State input) {
        State state = this._state;
        State state2 = (State) this.transform.invoke(input);
        set_state(state2);
        Painter painterMaybeNewCrossfadePainter = maybeNewCrossfadePainter(state, state2);
        if (painterMaybeNewCrossfadePainter == null) {
            painterMaybeNewCrossfadePainter = state2.getPainter();
        }
        set_painter(painterMaybeNewCrossfadePainter);
        if (this.rememberScope != null && state.getPainter() != state2.getPainter()) {
            Object painter = state.getPainter();
            RememberObserver rememberObserver = painter instanceof RememberObserver ? (RememberObserver) painter : null;
            if (rememberObserver != null) {
                rememberObserver.onForgotten();
            }
            Object painter2 = state2.getPainter();
            RememberObserver rememberObserver2 = painter2 instanceof RememberObserver ? (RememberObserver) painter2 : null;
            if (rememberObserver2 != null) {
                rememberObserver2.onRemembered();
            }
        }
        l<? super State, t0> lVar = this.onState;
        if (lVar != null) {
            lVar.invoke(state2);
        }
    }

    @Override // androidx.compose.ui.graphics.painter.Painter
    public boolean applyAlpha(float alpha) {
        setAlpha(alpha);
        return true;
    }

    @Override // androidx.compose.ui.graphics.painter.Painter
    public boolean applyColorFilter(ColorFilter colorFilter) {
        setColorFilter(colorFilter);
        return true;
    }

    /* JADX INFO: renamed from: getContentScale$coil_compose_base_release, reason: from getter */
    public final ContentScale getContentScale() {
        return this.contentScale;
    }

    /* JADX INFO: renamed from: getFilterQuality-f-v9h1I$coil_compose_base_release, reason: not valid java name and from getter */
    public final int getFilterQuality() {
        return this.filterQuality;
    }

    public final ImageLoader getImageLoader() {
        return (ImageLoader) this.imageLoader.getValue();
    }

    @Override // androidx.compose.ui.graphics.painter.Painter
    /* JADX INFO: renamed from: getIntrinsicSize-NH-jbRc */
    public long mo4010getIntrinsicSizeNHjbRc() {
        Painter painter = getPainter();
        return painter != null ? painter.mo4010getIntrinsicSizeNHjbRc() : Size.INSTANCE.m3313getUnspecifiedNHjbRc();
    }

    public final l<State, t0> getOnState$coil_compose_base_release() {
        return this.onState;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final ImageRequest getRequest() {
        return (ImageRequest) this.request.getValue();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final State getState() {
        return (State) this.state.getValue();
    }

    public final l<State, State> getTransform$coil_compose_base_release() {
        return this.transform;
    }

    /* JADX INFO: renamed from: isPreview$coil_compose_base_release, reason: from getter */
    public final boolean getIsPreview() {
        return this.isPreview;
    }

    @Override // androidx.compose.runtime.RememberObserver
    public void onAbandoned() {
        clear();
        Object obj = this._painter;
        RememberObserver rememberObserver = obj instanceof RememberObserver ? (RememberObserver) obj : null;
        if (rememberObserver != null) {
            rememberObserver.onAbandoned();
        }
    }

    @Override // androidx.compose.ui.graphics.painter.Painter
    public void onDraw(DrawScope drawScope) {
        this.drawSize.setValue(Size.m3293boximpl(drawScope.mo3916getSizeNHjbRc()));
        Painter painter = getPainter();
        if (painter != null) {
            painter.m4016drawx_KDEd0(drawScope, drawScope.mo3916getSizeNHjbRc(), getAlpha(), getColorFilter());
        }
    }

    @Override // androidx.compose.runtime.RememberObserver
    public void onForgotten() {
        clear();
        Object obj = this._painter;
        RememberObserver rememberObserver = obj instanceof RememberObserver ? (RememberObserver) obj : null;
        if (rememberObserver != null) {
            rememberObserver.onForgotten();
        }
    }

    @Override // androidx.compose.runtime.RememberObserver
    public void onRemembered() {
        if (this.rememberScope != null) {
            return;
        }
        p2 p2VarD = m0.d();
        x0 x0Var = x0.f19652a;
        pa.e eVarA = l0.a(d7.h.a(p2VarD, ((la.d) q.f21294a).f20077n));
        this.rememberScope = eVarA;
        Object obj = this._painter;
        RememberObserver rememberObserver = obj instanceof RememberObserver ? (RememberObserver) obj : null;
        if (rememberObserver != null) {
            rememberObserver.onRemembered();
        }
        if (!this.isPreview) {
            m0.p(eVarA, null, 0, new AnonymousClass1(null), 3);
        } else {
            Drawable placeholder = ImageRequest.newBuilder$default(getRequest(), null, 1, null).defaults(getImageLoader().getDefaults()).build().getPlaceholder();
            updateState(new State.Loading(placeholder != null ? toPainter(placeholder) : null));
        }
    }

    public final void setContentScale$coil_compose_base_release(ContentScale contentScale) {
        this.contentScale = contentScale;
    }

    /* JADX INFO: renamed from: setFilterQuality-vDHp3xo$coil_compose_base_release, reason: not valid java name */
    public final void m6031setFilterQualityvDHp3xo$coil_compose_base_release(int i10) {
        this.filterQuality = i10;
    }

    public final void setImageLoader$coil_compose_base_release(ImageLoader imageLoader) {
        this.imageLoader.setValue(imageLoader);
    }

    public final void setOnState$coil_compose_base_release(l<? super State, t0> lVar) {
        this.onState = lVar;
    }

    public final void setPreview$coil_compose_base_release(boolean z) {
        this.isPreview = z;
    }

    public final void setRequest$coil_compose_base_release(ImageRequest imageRequest) {
        this.request.setValue(imageRequest);
    }

    public final void setTransform$coil_compose_base_release(l<? super State, ? extends State> lVar) {
        this.transform = lVar;
    }
}
