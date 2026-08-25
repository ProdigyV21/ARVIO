package coil.compose;

import a0.c;
import android.graphics.drawable.Drawable;
import android.view.Dimension;
import android.view.Dimensions;
import android.view.View;
import androidx.compose.material3.d;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.ui.geometry.Size;
import androidx.compose.ui.graphics.ImageBitmap;
import androidx.compose.ui.graphics.drawscope.DrawScope;
import androidx.compose.ui.graphics.painter.Painter;
import androidx.compose.ui.graphics.vector.ImageVector;
import androidx.compose.ui.layout.ContentScale;
import androidx.compose.ui.platform.InspectionModeKt;
import coil.ImageLoader;
import coil.compose.AsyncImagePainter;
import coil.request.ImageRequest;
import coil.transition.TransitionTarget;
import com.google.android.gms.cast.MediaTrack;
import io.ktor.http.ContentDisposition;
import kotlin.KotlinNothingValueException;
import kotlin.Metadata;
import r2.a;
import r7.l;
import x6.t0;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000}\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0001\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0004*\u0001,\u001a§\u0001\u0010\u0017\u001a\u00020\u00142\b\u0010\u0001\u001a\u0004\u0018\u00010\u00002\u0006\u0010\u0003\u001a\u00020\u00022\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00042\u0016\b\u0002\u0010\u000b\u001a\u0010\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\n\u0018\u00010\b2\u0016\b\u0002\u0010\r\u001a\u0010\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\n\u0018\u00010\b2\u0016\b\u0002\u0010\u000f\u001a\u0010\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\n\u0018\u00010\b2\b\b\u0002\u0010\u0011\u001a\u00020\u00102\b\b\u0002\u0010\u0013\u001a\u00020\u0012H\u0007ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u0015\u0010\u0016\u001ai\u0010\u0017\u001a\u00020\u00142\b\u0010\u0001\u001a\u0004\u0018\u00010\u00002\u0006\u0010\u0003\u001a\u00020\u00022\u0014\b\u0002\u0010\u0019\u001a\u000e\u0012\u0004\u0012\u00020\u0018\u0012\u0004\u0012\u00020\u00180\b2\u0016\b\u0002\u0010\u001a\u001a\u0010\u0012\u0004\u0012\u00020\u0018\u0012\u0004\u0012\u00020\n\u0018\u00010\b2\b\b\u0002\u0010\u0011\u001a\u00020\u00102\b\b\u0002\u0010\u0013\u001a\u00020\u0012H\u0007ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u001b\u0010\u001c\u001a\u0017\u0010\u001f\u001a\u00020\n2\u0006\u0010\u001e\u001a\u00020\u001dH\u0002¢\u0006\u0004\b\u001f\u0010 \u001a!\u0010%\u001a\u00020$2\u0006\u0010\"\u001a\u00020!2\b\b\u0002\u0010#\u001a\u00020!H\u0002¢\u0006\u0004\b%\u0010&\u001a\u001b\u0010+\u001a\u0004\u0018\u00010(*\u00020'H\u0002ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b)\u0010*\"\u0014\u0010-\u001a\u00020,8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b-\u0010.\"\u001b\u00102\u001a\u00020/*\u00020'8BX\u0082\u0004ø\u0001\u0001¢\u0006\u0006\u001a\u0004\b0\u00101\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b\u0019¨\u00063"}, d2 = {"", "model", "Lcoil/ImageLoader;", "imageLoader", "Landroidx/compose/ui/graphics/painter/Painter;", "placeholder", "error", "fallback", "Lkotlin/Function1;", "Lcoil/compose/AsyncImagePainter$State$Loading;", "Lx6/t0;", "onLoading", "Lcoil/compose/AsyncImagePainter$State$Success;", "onSuccess", "Lcoil/compose/AsyncImagePainter$State$Error;", "onError", "Landroidx/compose/ui/layout/ContentScale;", "contentScale", "Landroidx/compose/ui/graphics/FilterQuality;", "filterQuality", "Lcoil/compose/AsyncImagePainter;", "rememberAsyncImagePainter-3HmZ8SU", "(Ljava/lang/Object;Lcoil/ImageLoader;Landroidx/compose/ui/graphics/painter/Painter;Landroidx/compose/ui/graphics/painter/Painter;Landroidx/compose/ui/graphics/painter/Painter;Lr7/l;Lr7/l;Lr7/l;Landroidx/compose/ui/layout/ContentScale;ILandroidx/compose/runtime/Composer;II)Lcoil/compose/AsyncImagePainter;", "rememberAsyncImagePainter", "Lcoil/compose/AsyncImagePainter$State;", "transform", "onState", "rememberAsyncImagePainter-5jETZwI", "(Ljava/lang/Object;Lcoil/ImageLoader;Lr7/l;Lr7/l;Landroidx/compose/ui/layout/ContentScale;ILandroidx/compose/runtime/Composer;II)Lcoil/compose/AsyncImagePainter;", "Lcoil/request/ImageRequest;", "request", "validateRequest", "(Lcoil/request/ImageRequest;)V", "", ContentDisposition.Parameters.Name, MediaTrack.ROLE_DESCRIPTION, "", "unsupportedData", "(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/Void;", "Landroidx/compose/ui/geometry/Size;", "Lcoil/size/Size;", "toSizeOrNull-uvyYCjk", "(J)Lcoil/size/Size;", "toSizeOrNull", "coil/compose/AsyncImagePainterKt$fakeTransitionTarget$1", "fakeTransitionTarget", "Lcoil/compose/AsyncImagePainterKt$fakeTransitionTarget$1;", "", "isPositive-uvyYCjk", "(J)Z", "isPositive", "coil-compose-base_release"}, k = 2, mv = {1, 9, 0}, xi = 48)
public final class AsyncImagePainterKt {
    private static final AsyncImagePainterKt$fakeTransitionTarget$1 fakeTransitionTarget = new TransitionTarget() { // from class: coil.compose.AsyncImagePainterKt$fakeTransitionTarget$1
        @Override // coil.transition.TransitionTarget
        public Drawable getDrawable() {
            return null;
        }

        @Override // coil.transition.TransitionTarget
        public /* bridge */ /* synthetic */ View getView() {
            return (View) m6037getView();
        }

        @Override // coil.target.Target
        public final /* synthetic */ void onError(Drawable drawable) {
            a.a(this, drawable);
        }

        @Override // coil.target.Target
        public final /* synthetic */ void onStart(Drawable drawable) {
            a.b(this, drawable);
        }

        @Override // coil.target.Target
        public final /* synthetic */ void onSuccess(Drawable drawable) {
            a.c(this, drawable);
        }

        /* JADX INFO: renamed from: getView, reason: collision with other method in class */
        public Void m6037getView() {
            throw new UnsupportedOperationException();
        }
    };

    /* JADX INFO: renamed from: isPositive-uvyYCjk, reason: not valid java name */
    private static final boolean m6033isPositiveuvyYCjk(long j10) {
        return ((double) Size.m3305getWidthimpl(j10)) >= 0.5d && ((double) Size.m3302getHeightimpl(j10)) >= 0.5d;
    }

    /* JADX INFO: renamed from: rememberAsyncImagePainter-3HmZ8SU, reason: not valid java name */
    public static final AsyncImagePainter m6034rememberAsyncImagePainter3HmZ8SU(Object obj, ImageLoader imageLoader, Painter painter, Painter painter2, Painter painter3, l<? super AsyncImagePainter.State.Loading, t0> lVar, l<? super AsyncImagePainter.State.Success, t0> lVar2, l<? super AsyncImagePainter.State.Error, t0> lVar3, ContentScale contentScale, int i10, Composer composer, int i11, int i12) {
        composer.startReplaceableGroup(2140758544);
        if ((i12 & 4) != 0) {
            painter = null;
        }
        Painter painter4 = (i12 & 8) != 0 ? null : painter2;
        Painter painter5 = (i12 & 16) != 0 ? painter4 : painter3;
        l<? super AsyncImagePainter.State.Loading, t0> lVar4 = (i12 & 32) != 0 ? null : lVar;
        l<? super AsyncImagePainter.State.Success, t0> lVar5 = (i12 & 64) != 0 ? null : lVar2;
        l<? super AsyncImagePainter.State.Error, t0> lVar6 = (i12 & 128) == 0 ? lVar3 : null;
        ContentScale fit = (i12 & 256) != 0 ? ContentScale.INSTANCE.getFit() : contentScale;
        int iM3947getDefaultFilterQualityfv9h1I = (i12 & 512) != 0 ? DrawScope.INSTANCE.m3947getDefaultFilterQualityfv9h1I() : i10;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(2140758544, i11, -1, "coil.compose.rememberAsyncImagePainter (AsyncImagePainter.kt:95)");
        }
        int i13 = i11 >> 12;
        AsyncImagePainter asyncImagePainterM6035rememberAsyncImagePainter5jETZwI = m6035rememberAsyncImagePainter5jETZwI(obj, imageLoader, UtilsKt.transformOf(painter, painter4, painter5), UtilsKt.onStateOf(lVar4, lVar5, lVar6), fit, iM3947getDefaultFilterQualityfv9h1I, composer, (57344 & i13) | 72 | (i13 & 458752), 0);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        composer.endReplaceableGroup();
        return asyncImagePainterM6035rememberAsyncImagePainter5jETZwI;
    }

    /* JADX INFO: renamed from: rememberAsyncImagePainter-5jETZwI, reason: not valid java name */
    public static final AsyncImagePainter m6035rememberAsyncImagePainter5jETZwI(Object obj, ImageLoader imageLoader, l<? super AsyncImagePainter.State, ? extends AsyncImagePainter.State> lVar, l<? super AsyncImagePainter.State, t0> lVar2, ContentScale contentScale, int i10, Composer composer, int i11, int i12) {
        composer.startReplaceableGroup(-2020614074);
        if ((i12 & 4) != 0) {
            lVar = AsyncImagePainter.INSTANCE.getDefaultTransform();
        }
        if ((i12 & 8) != 0) {
            lVar2 = null;
        }
        if ((i12 & 16) != 0) {
            contentScale = ContentScale.INSTANCE.getFit();
        }
        if ((i12 & 32) != 0) {
            i10 = DrawScope.INSTANCE.m3947getDefaultFilterQualityfv9h1I();
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-2020614074, i11, -1, "coil.compose.rememberAsyncImagePainter (AsyncImagePainter.kt:136)");
        }
        ImageRequest imageRequestRequestOf = UtilsKt.requestOf(obj, composer, 8);
        validateRequest(imageRequestRequestOf);
        composer.startReplaceableGroup(-492369756);
        Object objRememberedValue = composer.rememberedValue();
        if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
            objRememberedValue = new AsyncImagePainter(imageRequestRequestOf, imageLoader);
            composer.updateRememberedValue(objRememberedValue);
        }
        composer.endReplaceableGroup();
        AsyncImagePainter asyncImagePainter = (AsyncImagePainter) objRememberedValue;
        asyncImagePainter.setTransform$coil_compose_base_release(lVar);
        asyncImagePainter.setOnState$coil_compose_base_release(lVar2);
        asyncImagePainter.setContentScale$coil_compose_base_release(contentScale);
        asyncImagePainter.m6031setFilterQualityvDHp3xo$coil_compose_base_release(i10);
        asyncImagePainter.setPreview$coil_compose_base_release(((Boolean) composer.consume(InspectionModeKt.getLocalInspectionMode())).booleanValue());
        asyncImagePainter.setImageLoader$coil_compose_base_release(imageLoader);
        asyncImagePainter.setRequest$coil_compose_base_release(imageRequestRequestOf);
        asyncImagePainter.onRemembered();
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        composer.endReplaceableGroup();
        return asyncImagePainter;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: toSizeOrNull-uvyYCjk, reason: not valid java name */
    public static final android.view.Size m6036toSizeOrNulluvyYCjk(long j10) {
        if (j10 == Size.INSTANCE.m3313getUnspecifiedNHjbRc()) {
            return android.view.Size.ORIGINAL;
        }
        if (!m6033isPositiveuvyYCjk(j10)) {
            return null;
        }
        float fM3305getWidthimpl = Size.m3305getWidthimpl(j10);
        Dimension Dimension = (Float.isInfinite(fM3305getWidthimpl) || Float.isNaN(fM3305getWidthimpl)) ? Dimension.Undefined.INSTANCE : Dimensions.Dimension(t7.a.M(Size.m3305getWidthimpl(j10)));
        float fM3302getHeightimpl = Size.m3302getHeightimpl(j10);
        return new android.view.Size(Dimension, (Float.isInfinite(fM3302getHeightimpl) || Float.isNaN(fM3302getHeightimpl)) ? Dimension.Undefined.INSTANCE : Dimensions.Dimension(t7.a.M(Size.m3302getHeightimpl(j10))));
    }

    private static final Void unsupportedData(String str, String str2) {
        throw new IllegalArgumentException(d.n("Unsupported type: ", str, ". ", str2));
    }

    public static /* synthetic */ Void unsupportedData$default(String str, String str2, int i10, Object obj) {
        if ((i10 & 2) != 0) {
            str2 = c.l("If you wish to display this ", str, ", use androidx.compose.foundation.Image.");
        }
        return unsupportedData(str, str2);
    }

    private static final void validateRequest(ImageRequest imageRequest) {
        Object data = imageRequest.getData();
        if (data instanceof ImageRequest.Builder) {
            unsupportedData("ImageRequest.Builder", "Did you forget to call ImageRequest.Builder.build()?");
            throw new KotlinNothingValueException();
        }
        if (data instanceof ImageBitmap) {
            unsupportedData$default("ImageBitmap", null, 2, null);
            throw new KotlinNothingValueException();
        }
        if (data instanceof ImageVector) {
            unsupportedData$default("ImageVector", null, 2, null);
            throw new KotlinNothingValueException();
        }
        if (data instanceof Painter) {
            unsupportedData$default("Painter", null, 2, null);
            throw new KotlinNothingValueException();
        }
        if (imageRequest.getTarget() != null) {
            throw new IllegalArgumentException("request.target must be null.");
        }
    }
}
