package coil.compose;

import android.content.Context;
import androidx.compose.runtime.Composer;
import androidx.compose.ui.platform.AndroidCompositionLocals_androidKt;
import coil.ImageLoader;
import coil.request.ImageRequest;
import kotlin.Metadata;
import r7.l;
import r7.p;
import x6.e;
import x6.j0;
import x6.t0;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\\\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\u001a\"\u0010\u0005\u001a\u00020\u00042\b\u0010\u0001\u001a\u0004\u0018\u00010\u00002\u0006\u0010\u0003\u001a\u00020\u0002H\u0087\b¢\u0006\u0004\b\u0005\u0010\u0006\u001ao\u0010\u0005\u001a\u00020\u00042\b\u0010\u0001\u001a\u0004\u0018\u00010\u00002\u0006\u0010\u0003\u001a\u00020\u00022H\u0010\u000f\u001aD\u0012\u001a\u0012\u0018\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u000b0\bj\u0002`\f\u0012\u001a\u0012\u0018\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u000b0\bj\u0002`\f\u0012\u0004\u0012\u00020\r0\u0007j\u0002`\u000eH\u0087\bø\u0001\u0000¢\u0006\u0004\b\u0005\u0010\u0010\u001a;\u0010\u0005\u001a\u00020\u00042\b\u0010\u0001\u001a\u0004\u0018\u00010\u00002\u0006\u0010\u0003\u001a\u00020\u00022\u0017\u0010\u0015\u001a\u0013\u0012\u0004\u0012\u00020\u0012\u0012\u0004\u0012\u00020\u00130\u0011¢\u0006\u0002\b\u0014H\u0087\b¢\u0006\u0004\b\u0005\u0010\u0016\u001a\u0088\u0001\u0010\u0005\u001a\u00020\u00042\b\u0010\u0001\u001a\u0004\u0018\u00010\u00002\u0006\u0010\u0003\u001a\u00020\u00022H\u0010\u000f\u001aD\u0012\u001a\u0012\u0018\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u000b0\bj\u0002`\f\u0012\u001a\u0012\u0018\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u000b0\bj\u0002`\f\u0012\u0004\u0012\u00020\r0\u0007j\u0002`\u000e2\u0017\u0010\u0015\u001a\u0013\u0012\u0004\u0012\u00020\u0012\u0012\u0004\u0012\u00020\u00130\u0011¢\u0006\u0002\b\u0014H\u0087\bø\u0001\u0000¢\u0006\u0004\b\u0005\u0010\u0017\u001a \u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0018\u001a\u00020\n2\u0006\u0010\u0003\u001a\u00020\u0002H\u0087\b¢\u0006\u0004\b\u0005\u0010\u0019\u001am\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0018\u001a\u00020\n2\u0006\u0010\u0003\u001a\u00020\u00022H\u0010\u000f\u001aD\u0012\u001a\u0012\u0018\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u000b0\bj\u0002`\f\u0012\u001a\u0012\u0018\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u000b0\bj\u0002`\f\u0012\u0004\u0012\u00020\r0\u0007j\u0002`\u000eH\u0087\bø\u0001\u0000¢\u0006\u0004\b\u0005\u0010\u001a*\\\b\u0002\u0010\u001b\"\u0014\u0012\u0004\u0012\u0002`\f\u0012\u0004\u0012\u0002`\f\u0012\u0004\u0012\u00020\r0\u00072@\u0012\u001a\u0012\u0018\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u000b0\bj\u0002`\f\u0012\u001a\u0012\u0018\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u000b0\bj\u0002`\f\u0012\u0004\u0012\u00020\r0\u0007*>\b\u0007\u0010%\"\u00020\u00042\u00020\u0004B0\b\u001c\u0012\b\b\u001d\u0012\u0004\b\b(\u001e\u0012\"\b\u001f\u0012\u001e\b\u000bB\u001a\b \u0012\f\b!\u0012\b\b\fJ\u0004\b\b(\"\u0012\b\b#\u0012\u0004\b\b($*0\b\u0002\u0010&\"\u0014\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u000b0\b2\u0014\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u000b0\b\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006'"}, d2 = {"", "data", "Lcoil/ImageLoader;", "imageLoader", "Lcoil/compose/AsyncImagePainter;", "rememberImagePainter", "(Ljava/lang/Object;Lcoil/ImageLoader;Landroidx/compose/runtime/Composer;I)Lcoil/compose/AsyncImagePainter;", "Lkotlin/Function2;", "Lx6/j0;", "Lcoil/compose/AsyncImagePainter$State;", "Lcoil/request/ImageRequest;", "Landroidx/compose/ui/geometry/Size;", "Lcoil/compose/Snapshot;", "", "Lcoil/compose/ExecuteCallback;", "onExecute", "(Ljava/lang/Object;Lcoil/ImageLoader;Lr7/p;Landroidx/compose/runtime/Composer;I)Lcoil/compose/AsyncImagePainter;", "Lkotlin/Function1;", "Lcoil/request/ImageRequest$Builder;", "Lx6/t0;", "Lx6/n;", "builder", "(Ljava/lang/Object;Lcoil/ImageLoader;Lr7/l;Landroidx/compose/runtime/Composer;I)Lcoil/compose/AsyncImagePainter;", "(Ljava/lang/Object;Lcoil/ImageLoader;Lr7/p;Lr7/l;Landroidx/compose/runtime/Composer;I)Lcoil/compose/AsyncImagePainter;", "request", "(Lcoil/request/ImageRequest;Lcoil/ImageLoader;Landroidx/compose/runtime/Composer;I)Lcoil/compose/AsyncImagePainter;", "(Lcoil/request/ImageRequest;Lcoil/ImageLoader;Lr7/p;Landroidx/compose/runtime/Composer;I)Lcoil/compose/AsyncImagePainter;", "ExecuteCallback", "Lx6/e;", "message", "ImagePainter has been renamed to AsyncImagePainter.", "replaceWith", "Lx6/a0;", "imports", "coil.compose.AsyncImagePainter", "expression", "AsyncImagePainter", "ImagePainter", "Snapshot", "coil-compose-base_release"}, k = 2, mv = {1, 9, 0}, xi = 48)
public final class ImagePainterKt {
    @e
    public static /* synthetic */ void ImagePainter$annotations() {
    }

    @e
    public static final AsyncImagePainter rememberImagePainter(Object obj, ImageLoader imageLoader, Composer composer, int i10) {
        composer.startReplaceableGroup(699722038);
        AsyncImagePainter asyncImagePainterM6035rememberAsyncImagePainter5jETZwI = AsyncImagePainterKt.m6035rememberAsyncImagePainter5jETZwI(obj, imageLoader, null, null, null, 0, composer, 72, 60);
        composer.endReplaceableGroup();
        return asyncImagePainterM6035rememberAsyncImagePainter5jETZwI;
    }

    @e
    public static final AsyncImagePainter rememberImagePainter(Object obj, ImageLoader imageLoader, p<? super j0, ? super j0, Boolean> pVar, Composer composer, int i10) {
        composer.startReplaceableGroup(-1913684348);
        AsyncImagePainter asyncImagePainterM6035rememberAsyncImagePainter5jETZwI = AsyncImagePainterKt.m6035rememberAsyncImagePainter5jETZwI(obj, imageLoader, null, null, null, 0, composer, 72, 60);
        composer.endReplaceableGroup();
        return asyncImagePainterM6035rememberAsyncImagePainter5jETZwI;
    }

    @e
    public static final AsyncImagePainter rememberImagePainter(Object obj, ImageLoader imageLoader, l<? super ImageRequest.Builder, t0> lVar, Composer composer, int i10) {
        composer.startReplaceableGroup(1750824323);
        ImageRequest.Builder builderData = new ImageRequest.Builder((Context) composer.consume(AndroidCompositionLocals_androidKt.getLocalContext())).data(obj);
        lVar.invoke(builderData);
        AsyncImagePainter asyncImagePainterM6035rememberAsyncImagePainter5jETZwI = AsyncImagePainterKt.m6035rememberAsyncImagePainter5jETZwI(builderData.build(), imageLoader, null, null, null, 0, composer, 72, 60);
        composer.endReplaceableGroup();
        return asyncImagePainterM6035rememberAsyncImagePainter5jETZwI;
    }

    @e
    public static final AsyncImagePainter rememberImagePainter(Object obj, ImageLoader imageLoader, p<? super j0, ? super j0, Boolean> pVar, l<? super ImageRequest.Builder, t0> lVar, Composer composer, int i10) {
        composer.startReplaceableGroup(-976338607);
        ImageRequest.Builder builderData = new ImageRequest.Builder((Context) composer.consume(AndroidCompositionLocals_androidKt.getLocalContext())).data(obj);
        lVar.invoke(builderData);
        AsyncImagePainter asyncImagePainterM6035rememberAsyncImagePainter5jETZwI = AsyncImagePainterKt.m6035rememberAsyncImagePainter5jETZwI(builderData.build(), imageLoader, null, null, null, 0, composer, 72, 60);
        composer.endReplaceableGroup();
        return asyncImagePainterM6035rememberAsyncImagePainter5jETZwI;
    }

    @e
    public static final AsyncImagePainter rememberImagePainter(ImageRequest imageRequest, ImageLoader imageLoader, Composer composer, int i10) {
        composer.startReplaceableGroup(-2028135656);
        AsyncImagePainter asyncImagePainterM6035rememberAsyncImagePainter5jETZwI = AsyncImagePainterKt.m6035rememberAsyncImagePainter5jETZwI(imageRequest, imageLoader, null, null, null, 0, composer, 72, 60);
        composer.endReplaceableGroup();
        return asyncImagePainterM6035rememberAsyncImagePainter5jETZwI;
    }

    @e
    public static final AsyncImagePainter rememberImagePainter(ImageRequest imageRequest, ImageLoader imageLoader, p<? super j0, ? super j0, Boolean> pVar, Composer composer, int i10) {
        composer.startReplaceableGroup(-2123088410);
        AsyncImagePainter asyncImagePainterM6035rememberAsyncImagePainter5jETZwI = AsyncImagePainterKt.m6035rememberAsyncImagePainter5jETZwI(imageRequest, imageLoader, null, null, null, 0, composer, 72, 60);
        composer.endReplaceableGroup();
        return asyncImagePainterM6035rememberAsyncImagePainter5jETZwI;
    }
}
