package coil.compose;

import android.content.Context;
import androidx.compose.runtime.Composer;
import androidx.compose.ui.platform.AndroidCompositionLocals_androidKt;
import coil.request.ImageRequest;
import kotlin.Metadata;
import r7.l;
import r7.p;
import x6.e;
import x6.j0;
import x6.t0;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000F\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\t\u001a\u001a\u0010\u0003\u001a\u00020\u00022\b\u0010\u0001\u001a\u0004\u0018\u00010\u0000H\u0087\b¢\u0006\u0004\b\u0003\u0010\u0004\u001ag\u0010\u0003\u001a\u00020\u00022\b\u0010\u0001\u001a\u0004\u0018\u00010\u00002H\u0010\r\u001aD\u0012\u001a\u0012\u0018\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\t0\u0006j\u0002`\n\u0012\u001a\u0012\u0018\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\t0\u0006j\u0002`\n\u0012\u0004\u0012\u00020\u000b0\u0005j\u0002`\fH\u0087\bø\u0001\u0000¢\u0006\u0004\b\u0003\u0010\u000e\u001a3\u0010\u0003\u001a\u00020\u00022\b\u0010\u0001\u001a\u0004\u0018\u00010\u00002\u0017\u0010\u0013\u001a\u0013\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020\u00110\u000f¢\u0006\u0002\b\u0012H\u0087\b¢\u0006\u0004\b\u0003\u0010\u0014\u001a\u0080\u0001\u0010\u0003\u001a\u00020\u00022\b\u0010\u0001\u001a\u0004\u0018\u00010\u00002H\u0010\r\u001aD\u0012\u001a\u0012\u0018\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\t0\u0006j\u0002`\n\u0012\u001a\u0012\u0018\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\t0\u0006j\u0002`\n\u0012\u0004\u0012\u00020\u000b0\u0005j\u0002`\f2\u0017\u0010\u0013\u001a\u0013\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020\u00110\u000f¢\u0006\u0002\b\u0012H\u0087\bø\u0001\u0000¢\u0006\u0004\b\u0003\u0010\u0015\u001a\u0018\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0016\u001a\u00020\bH\u0087\b¢\u0006\u0004\b\u0003\u0010\u0017\u001ae\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0016\u001a\u00020\b2H\u0010\r\u001aD\u0012\u001a\u0012\u0018\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\t0\u0006j\u0002`\n\u0012\u001a\u0012\u0018\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\t0\u0006j\u0002`\n\u0012\u0004\u0012\u00020\u000b0\u0005j\u0002`\fH\u0087\bø\u0001\u0000¢\u0006\u0004\b\u0003\u0010\u0018*\\\b\u0002\u0010\u0019\"\u0014\u0012\u0004\u0012\u0002`\n\u0012\u0004\u0012\u0002`\n\u0012\u0004\u0012\u00020\u000b0\u00052@\u0012\u001a\u0012\u0018\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\t0\u0006j\u0002`\n\u0012\u001a\u0012\u0018\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\t0\u0006j\u0002`\n\u0012\u0004\u0012\u00020\u000b0\u0005*0\b\u0002\u0010\u001a\"\u0014\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\t0\u00062\u0014\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\t0\u0006\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u001b"}, d2 = {"", "data", "Lcoil/compose/AsyncImagePainter;", "rememberImagePainter", "(Ljava/lang/Object;Landroidx/compose/runtime/Composer;I)Lcoil/compose/AsyncImagePainter;", "Lkotlin/Function2;", "Lx6/j0;", "Lcoil/compose/AsyncImagePainter$State;", "Lcoil/request/ImageRequest;", "Landroidx/compose/ui/geometry/Size;", "Lcoil/compose/Snapshot;", "", "Lcoil/compose/ExecuteCallback;", "onExecute", "(Ljava/lang/Object;Lr7/p;Landroidx/compose/runtime/Composer;I)Lcoil/compose/AsyncImagePainter;", "Lkotlin/Function1;", "Lcoil/request/ImageRequest$Builder;", "Lx6/t0;", "Lx6/n;", "builder", "(Ljava/lang/Object;Lr7/l;Landroidx/compose/runtime/Composer;I)Lcoil/compose/AsyncImagePainter;", "(Ljava/lang/Object;Lr7/p;Lr7/l;Landroidx/compose/runtime/Composer;I)Lcoil/compose/AsyncImagePainter;", "request", "(Lcoil/request/ImageRequest;Landroidx/compose/runtime/Composer;I)Lcoil/compose/AsyncImagePainter;", "(Lcoil/request/ImageRequest;Lr7/p;Landroidx/compose/runtime/Composer;I)Lcoil/compose/AsyncImagePainter;", "ExecuteCallback", "Snapshot", "coil-compose-singleton_release"}, k = 2, mv = {1, 9, 0}, xi = 48)
public final class SingletonImagePainterKt {
    @e
    public static final AsyncImagePainter rememberImagePainter(Object obj, Composer composer, int i10) {
        composer.startReplaceableGroup(1998134191);
        AsyncImagePainter asyncImagePainterM6055rememberAsyncImagePainter19ie5dc = SingletonAsyncImagePainterKt.m6055rememberAsyncImagePainter19ie5dc(obj, null, null, null, 0, composer, 8, 30);
        composer.endReplaceableGroup();
        return asyncImagePainterM6055rememberAsyncImagePainter19ie5dc;
    }

    @e
    public static final AsyncImagePainter rememberImagePainter(Object obj, p<? super j0, ? super j0, Boolean> pVar, Composer composer, int i10) {
        composer.startReplaceableGroup(2090701729);
        AsyncImagePainter asyncImagePainterM6055rememberAsyncImagePainter19ie5dc = SingletonAsyncImagePainterKt.m6055rememberAsyncImagePainter19ie5dc(obj, null, null, null, 0, composer, 8, 30);
        composer.endReplaceableGroup();
        return asyncImagePainterM6055rememberAsyncImagePainter19ie5dc;
    }

    @e
    public static final AsyncImagePainter rememberImagePainter(Object obj, l<? super ImageRequest.Builder, t0> lVar, Composer composer, int i10) {
        composer.startReplaceableGroup(309201794);
        ImageRequest.Builder builderData = new ImageRequest.Builder((Context) composer.consume(AndroidCompositionLocals_androidKt.getLocalContext())).data(obj);
        lVar.invoke(builderData);
        AsyncImagePainter asyncImagePainterM6055rememberAsyncImagePainter19ie5dc = SingletonAsyncImagePainterKt.m6055rememberAsyncImagePainter19ie5dc(builderData.build(), null, null, null, 0, composer, 8, 30);
        composer.endReplaceableGroup();
        return asyncImagePainterM6055rememberAsyncImagePainter19ie5dc;
    }

    @e
    public static final AsyncImagePainter rememberImagePainter(Object obj, p<? super j0, ? super j0, Boolean> pVar, l<? super ImageRequest.Builder, t0> lVar, Composer composer, int i10) {
        composer.startReplaceableGroup(305839348);
        ImageRequest.Builder builderData = new ImageRequest.Builder((Context) composer.consume(AndroidCompositionLocals_androidKt.getLocalContext())).data(obj);
        lVar.invoke(builderData);
        AsyncImagePainter asyncImagePainterM6055rememberAsyncImagePainter19ie5dc = SingletonAsyncImagePainterKt.m6055rememberAsyncImagePainter19ie5dc(builderData.build(), null, null, null, 0, composer, 8, 30);
        composer.endReplaceableGroup();
        return asyncImagePainterM6055rememberAsyncImagePainter19ie5dc;
    }

    @e
    public static final AsyncImagePainter rememberImagePainter(ImageRequest imageRequest, Composer composer, int i10) {
        composer.startReplaceableGroup(2091320589);
        AsyncImagePainter asyncImagePainterM6055rememberAsyncImagePainter19ie5dc = SingletonAsyncImagePainterKt.m6055rememberAsyncImagePainter19ie5dc(imageRequest, null, null, null, 0, composer, 8, 30);
        composer.endReplaceableGroup();
        return asyncImagePainterM6055rememberAsyncImagePainter19ie5dc;
    }

    @e
    public static final AsyncImagePainter rememberImagePainter(ImageRequest imageRequest, p<? super j0, ? super j0, Boolean> pVar, Composer composer, int i10) {
        composer.startReplaceableGroup(-2003443841);
        AsyncImagePainter asyncImagePainterM6055rememberAsyncImagePainter19ie5dc = SingletonAsyncImagePainterKt.m6055rememberAsyncImagePainter19ie5dc(imageRequest, null, null, null, 0, composer, 8, 30);
        composer.endReplaceableGroup();
        return asyncImagePainterM6055rememberAsyncImagePainter19ie5dc;
    }
}
