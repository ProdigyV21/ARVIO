package coil.compose;

import android.content.Context;
import android.view.Scale;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.ui.geometry.Size;
import androidx.compose.ui.graphics.painter.Painter;
import androidx.compose.ui.layout.ContentScale;
import androidx.compose.ui.platform.AndroidCompositionLocals_androidKt;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.IntSizeKt;
import coil.compose.AsyncImagePainter;
import coil.request.ImageRequest;
import coil.request.NullRequestDataException;
import kotlin.Metadata;
import kotlin.jvm.internal.p;
import kotlin.jvm.internal.r;
import qb.d;
import r7.a;
import r7.l;
import x6.t0;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000f\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0007\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\b\u001a\u0019\u0010\u0003\u001a\u00020\u00022\b\u0010\u0001\u001a\u0004\u0018\u00010\u0000H\u0001¢\u0006\u0004\b\u0003\u0010\u0004\u001a9\u0010\u000b\u001a\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\n0\t2\b\u0010\u0006\u001a\u0004\u0018\u00010\u00052\b\u0010\u0007\u001a\u0004\u0018\u00010\u00052\b\u0010\b\u001a\u0004\u0018\u00010\u0005H\u0001¢\u0006\u0004\b\u000b\u0010\f\u001a_\u0010\u0014\u001a\u0010\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u000e\u0018\u00010\t2\u0014\u0010\u000f\u001a\u0010\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\u000e\u0018\u00010\t2\u0014\u0010\u0011\u001a\u0010\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020\u000e\u0018\u00010\t2\u0014\u0010\u0013\u001a\u0010\u0012\u0004\u0012\u00020\u0012\u0012\u0004\u0012\u00020\u000e\u0018\u00010\tH\u0001¢\u0006\u0004\b\u0014\u0010\u0015\u001a\u0013\u0010\u0018\u001a\u00020\u0017*\u00020\u0016H\u0001¢\u0006\u0004\b\u0018\u0010\u0019\u001a!\u0010\u001f\u001a\u00020\u001b*\u00020\u001a2\u0006\u0010\u001c\u001a\u00020\u001bH\u0000ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u001d\u0010\u001e\u001a!\u0010\"\u001a\u00020\u001b*\u00020\u001a2\u0006\u0010 \u001a\u00020\u001bH\u0000ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b!\u0010\u001e\u001a\"\u0010%\u001a\u00020\u001b*\u00020\u001b2\f\u0010$\u001a\b\u0012\u0004\u0012\u00020\u001b0#H\u0080\b¢\u0006\u0004\b%\u0010&\u001a\u0019\u0010+\u001a\u00020(*\u00020'H\u0000ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b)\u0010*\"\u001d\u0010,\u001a\u00020\u001a8\u0000X\u0080\u0004ø\u0001\u0001¢\u0006\f\n\u0004\b,\u0010-\u001a\u0004\b.\u0010/\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b\u0019¨\u00060"}, d2 = {"", "model", "Lcoil/request/ImageRequest;", "requestOf", "(Ljava/lang/Object;Landroidx/compose/runtime/Composer;I)Lcoil/request/ImageRequest;", "Landroidx/compose/ui/graphics/painter/Painter;", "placeholder", "error", "fallback", "Lkotlin/Function1;", "Lcoil/compose/AsyncImagePainter$State;", "transformOf", "(Landroidx/compose/ui/graphics/painter/Painter;Landroidx/compose/ui/graphics/painter/Painter;Landroidx/compose/ui/graphics/painter/Painter;)Lr7/l;", "Lcoil/compose/AsyncImagePainter$State$Loading;", "Lx6/t0;", "onLoading", "Lcoil/compose/AsyncImagePainter$State$Success;", "onSuccess", "Lcoil/compose/AsyncImagePainter$State$Error;", "onError", "onStateOf", "(Lr7/l;Lr7/l;Lr7/l;)Lr7/l;", "Landroidx/compose/ui/layout/ContentScale;", "Lcoil/size/Scale;", "toScale", "(Landroidx/compose/ui/layout/ContentScale;)Lcoil/size/Scale;", "Landroidx/compose/ui/unit/Constraints;", "", "width", "constrainWidth-K40F9xA", "(JF)F", "constrainWidth", "height", "constrainHeight-K40F9xA", "constrainHeight", "Lkotlin/Function0;", "block", "takeOrElse", "(FLr7/a;)F", "Landroidx/compose/ui/geometry/Size;", "Landroidx/compose/ui/unit/IntSize;", "toIntSize-uvyYCjk", "(J)J", "toIntSize", "ZeroConstraints", "J", "getZeroConstraints", "()J", "coil-compose-base_release"}, k = 2, mv = {1, 9, 0}, xi = 48)
public final class UtilsKt {
    private static final long ZeroConstraints = Constraints.INSTANCE.m5654fixedJhjzzOo(0, 0);

    /* JADX INFO: renamed from: coil.compose.UtilsKt$onStateOf$1, reason: invalid class name */
    @Metadata(d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0005\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u0000H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"Lcoil/compose/AsyncImagePainter$State;", "state", "Lx6/t0;", "invoke", "(Lcoil/compose/AsyncImagePainter$State;)V", "<anonymous>"}, k = 3, mv = {1, 9, 0})
    public static final class AnonymousClass1 extends r implements l<AsyncImagePainter.State, t0> {
        final /* synthetic */ l<AsyncImagePainter.State.Error, t0> $onError;
        final /* synthetic */ l<AsyncImagePainter.State.Loading, t0> $onLoading;
        final /* synthetic */ l<AsyncImagePainter.State.Success, t0> $onSuccess;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public AnonymousClass1(l<? super AsyncImagePainter.State.Loading, t0> lVar, l<? super AsyncImagePainter.State.Success, t0> lVar2, l<? super AsyncImagePainter.State.Error, t0> lVar3) {
            super(1);
            this.$onLoading = lVar;
            this.$onSuccess = lVar2;
            this.$onError = lVar3;
        }

        @Override // r7.l
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            invoke((AsyncImagePainter.State) obj);
            return t0.f22605a;
        }

        public final void invoke(AsyncImagePainter.State state) {
            l<AsyncImagePainter.State.Error, t0> lVar;
            if (state instanceof AsyncImagePainter.State.Loading) {
                l<AsyncImagePainter.State.Loading, t0> lVar2 = this.$onLoading;
                if (lVar2 != null) {
                    lVar2.invoke(state);
                    return;
                }
                return;
            }
            if (state instanceof AsyncImagePainter.State.Success) {
                l<AsyncImagePainter.State.Success, t0> lVar3 = this.$onSuccess;
                if (lVar3 != null) {
                    lVar3.invoke(state);
                    return;
                }
                return;
            }
            if (!(state instanceof AsyncImagePainter.State.Error) || (lVar = this.$onError) == null) {
                return;
            }
            lVar.invoke(state);
        }
    }

    /* JADX INFO: renamed from: coil.compose.UtilsKt$transformOf$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0003"}, d2 = {"<anonymous>", "Lcoil/compose/AsyncImagePainter$State;", "state", "invoke"}, k = 3, mv = {1, 9, 0}, xi = 48)
    public static final class C06431 extends r implements l<AsyncImagePainter.State, AsyncImagePainter.State> {
        final /* synthetic */ Painter $error;
        final /* synthetic */ Painter $fallback;
        final /* synthetic */ Painter $placeholder;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C06431(Painter painter, Painter painter2, Painter painter3) {
            super(1);
            this.$placeholder = painter;
            this.$fallback = painter2;
            this.$error = painter3;
        }

        @Override // r7.l
        public final AsyncImagePainter.State invoke(AsyncImagePainter.State state) {
            if (state instanceof AsyncImagePainter.State.Loading) {
                Painter painter = this.$placeholder;
                AsyncImagePainter.State.Loading loading = (AsyncImagePainter.State.Loading) state;
                return painter != null ? loading.copy(painter) : loading;
            }
            if (!(state instanceof AsyncImagePainter.State.Error)) {
                return state;
            }
            AsyncImagePainter.State.Error error = (AsyncImagePainter.State.Error) state;
            if (error.getResult().getThrowable() instanceof NullRequestDataException) {
                Painter painter2 = this.$fallback;
                return painter2 != null ? AsyncImagePainter.State.Error.copy$default(error, painter2, null, 2, null) : error;
            }
            Painter painter3 = this.$error;
            return painter3 != null ? AsyncImagePainter.State.Error.copy$default(error, painter3, null, 2, null) : error;
        }
    }

    /* JADX INFO: renamed from: constrainHeight-K40F9xA, reason: not valid java name */
    public static final float m6061constrainHeightK40F9xA(long j10, float f10) {
        return d.m(f10, Constraints.m5647getMinHeightimpl(j10), Constraints.m5645getMaxHeightimpl(j10));
    }

    /* JADX INFO: renamed from: constrainWidth-K40F9xA, reason: not valid java name */
    public static final float m6062constrainWidthK40F9xA(long j10, float f10) {
        return d.m(f10, Constraints.m5648getMinWidthimpl(j10), Constraints.m5646getMaxWidthimpl(j10));
    }

    public static final long getZeroConstraints() {
        return ZeroConstraints;
    }

    public static final l<AsyncImagePainter.State, t0> onStateOf(l<? super AsyncImagePainter.State.Loading, t0> lVar, l<? super AsyncImagePainter.State.Success, t0> lVar2, l<? super AsyncImagePainter.State.Error, t0> lVar3) {
        if (lVar == null && lVar2 == null && lVar3 == null) {
            return null;
        }
        return new AnonymousClass1(lVar, lVar2, lVar3);
    }

    public static final ImageRequest requestOf(Object obj, Composer composer, int i10) {
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1151830858, i10, -1, "coil.compose.requestOf (Utils.kt:21)");
        }
        if (obj instanceof ImageRequest) {
            ImageRequest imageRequest = (ImageRequest) obj;
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            return imageRequest;
        }
        ImageRequest imageRequestBuild = new ImageRequest.Builder((Context) composer.consume(AndroidCompositionLocals_androidKt.getLocalContext())).data(obj).build();
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return imageRequestBuild;
    }

    public static final float takeOrElse(float f10, a<Float> aVar) {
        return (Float.isInfinite(f10) || Float.isNaN(f10)) ? ((Number) aVar.invoke()).floatValue() : f10;
    }

    /* JADX INFO: renamed from: toIntSize-uvyYCjk, reason: not valid java name */
    public static final long m6063toIntSizeuvyYCjk(long j10) {
        return IntSizeKt.IntSize(t7.a.M(Size.m3305getWidthimpl(j10)), t7.a.M(Size.m3302getHeightimpl(j10)));
    }

    public static final Scale toScale(ContentScale contentScale) {
        ContentScale.Companion companion = ContentScale.INSTANCE;
        return p.a(contentScale, companion.getFit()) ? true : p.a(contentScale, companion.getInside()) ? Scale.FIT : Scale.FILL;
    }

    public static final l<AsyncImagePainter.State, AsyncImagePainter.State> transformOf(Painter painter, Painter painter2, Painter painter3) {
        return (painter == null && painter2 == null && painter3 == null) ? AsyncImagePainter.INSTANCE.getDefaultTransform() : new C06431(painter, painter3, painter2);
    }
}
