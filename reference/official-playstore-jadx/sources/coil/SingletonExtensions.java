package coil;

import android.content.CoilUtils;
import android.content.Context;
import android.widget.ImageView;
import androidx.media3.extractor.text.ttml.TtmlNode;
import coil.request.Disposable;
import coil.request.ImageRequest;
import coil.request.ImageResult;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.l;
import x6.e;
import x6.t0;

/* JADX INFO: renamed from: coil.-SingletonExtensions, reason: invalid class name */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\u001aC\u0010\u000b\u001a\u00020\n*\u00020\u00002\b\u0010\u0002\u001a\u0004\u0018\u00010\u00012\b\b\u0002\u0010\u0004\u001a\u00020\u00032\u0019\b\u0002\u0010\t\u001a\u0013\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0005¢\u0006\u0002\b\bH\u0086\b¢\u0006\u0004\b\u000b\u0010\f\u001a\u0014\u0010\r\u001a\u00020\u0007*\u00020\u0000H\u0086\b¢\u0006\u0004\b\r\u0010\u000e\u001aC\u0010\u000f\u001a\u00020\n*\u00020\u00002\b\u0010\u0002\u001a\u0004\u0018\u00010\u00012\b\b\u0002\u0010\u0004\u001a\u00020\u00032\u0019\b\u0002\u0010\t\u001a\u0013\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0005¢\u0006\u0002\b\bH\u0087\b¢\u0006\u0004\b\u000f\u0010\f\u001a\u0014\u0010\u0010\u001a\u00020\u0007*\u00020\u0000H\u0087\b¢\u0006\u0004\b\u0010\u0010\u000e\"\u0016\u0010\u0004\u001a\u00020\u0003*\u00020\u00118Æ\u0002¢\u0006\u0006\u001a\u0004\b\u0012\u0010\u0013\"\u0018\u0010\u0017\u001a\u0004\u0018\u00010\u0014*\u00020\u00008Æ\u0002¢\u0006\u0006\u001a\u0004\b\u0015\u0010\u0016\"!\u0010\u001a\u001a\u0004\u0018\u00010\u0014*\u00020\u00008Æ\u0002X\u0087\u0004¢\u0006\f\u0012\u0004\b\u0019\u0010\u000e\u001a\u0004\b\u0018\u0010\u0016¨\u0006\u001b"}, d2 = {"Landroid/widget/ImageView;", "", "data", "Lcoil/ImageLoader;", "imageLoader", "Lkotlin/Function1;", "Lcoil/request/ImageRequest$Builder;", "Lx6/t0;", "Lx6/n;", "builder", "Lcoil/request/Disposable;", "load", "(Landroid/widget/ImageView;Ljava/lang/Object;Lcoil/ImageLoader;Lr7/l;)Lcoil/request/Disposable;", "dispose", "(Landroid/widget/ImageView;)V", "loadAny", "clear", "Landroid/content/Context;", "getImageLoader", "(Landroid/content/Context;)Lcoil/ImageLoader;", "Lcoil/request/ImageResult;", "getResult", "(Landroid/widget/ImageView;)Lcoil/request/ImageResult;", "result", "getMetadata", "getMetadata$annotations", TtmlNode.TAG_METADATA, "coil-singleton_release"}, k = 2, mv = {1, 9, 0}, xi = 48)
public final class SingletonExtensions {

    /* JADX INFO: renamed from: coil.-SingletonExtensions$load$1, reason: invalid class name */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0004\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lcoil/request/ImageRequest$Builder;", "Lx6/t0;", "invoke", "(Lcoil/request/ImageRequest$Builder;)V", "<anonymous>"}, k = 3, mv = {1, 9, 0})
    public static final class AnonymousClass1 extends r implements l<ImageRequest.Builder, t0> {
        public static final AnonymousClass1 INSTANCE = new AnonymousClass1();

        public AnonymousClass1() {
            super(1);
        }

        public final void invoke(ImageRequest.Builder builder) {
        }

        @Override // r7.l
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            invoke((ImageRequest.Builder) obj);
            return t0.f22605a;
        }
    }

    /* JADX INFO: renamed from: coil.-SingletonExtensions$loadAny$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0004\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lcoil/request/ImageRequest$Builder;", "Lx6/t0;", "invoke", "(Lcoil/request/ImageRequest$Builder;)V", "<anonymous>"}, k = 3, mv = {1, 9, 0})
    public static final class C06401 extends r implements l<ImageRequest.Builder, t0> {
        public static final C06401 INSTANCE = new C06401();

        public C06401() {
            super(1);
        }

        public final void invoke(ImageRequest.Builder builder) {
        }

        @Override // r7.l
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            invoke((ImageRequest.Builder) obj);
            return t0.f22605a;
        }
    }

    @e
    public static final void clear(ImageView imageView) {
        CoilUtils.dispose(imageView);
    }

    public static final void dispose(ImageView imageView) {
        CoilUtils.dispose(imageView);
    }

    public static final ImageLoader getImageLoader(Context context) {
        return Coil.imageLoader(context);
    }

    public static final ImageResult getMetadata(ImageView imageView) {
        return CoilUtils.result(imageView);
    }

    @e
    public static /* synthetic */ void getMetadata$annotations(ImageView imageView) {
    }

    public static final ImageResult getResult(ImageView imageView) {
        return CoilUtils.result(imageView);
    }

    public static final Disposable load(ImageView imageView, Object obj, ImageLoader imageLoader, l<? super ImageRequest.Builder, t0> lVar) {
        ImageRequest.Builder builderTarget = new ImageRequest.Builder(imageView.getContext()).data(obj).target(imageView);
        lVar.invoke(builderTarget);
        return imageLoader.enqueue(builderTarget.build());
    }

    public static /* synthetic */ Disposable load$default(ImageView imageView, Object obj, ImageLoader imageLoader, l lVar, int i10, Object obj2) {
        if ((i10 & 2) != 0) {
            imageLoader = Coil.imageLoader(imageView.getContext());
        }
        if ((i10 & 4) != 0) {
            lVar = AnonymousClass1.INSTANCE;
        }
        ImageRequest.Builder builderTarget = new ImageRequest.Builder(imageView.getContext()).data(obj).target(imageView);
        lVar.invoke(builderTarget);
        return imageLoader.enqueue(builderTarget.build());
    }

    @e
    public static final Disposable loadAny(ImageView imageView, Object obj, ImageLoader imageLoader, l<? super ImageRequest.Builder, t0> lVar) {
        ImageRequest.Builder builderTarget = new ImageRequest.Builder(imageView.getContext()).data(obj).target(imageView);
        lVar.invoke(builderTarget);
        return imageLoader.enqueue(builderTarget.build());
    }

    public static /* synthetic */ Disposable loadAny$default(ImageView imageView, Object obj, ImageLoader imageLoader, l lVar, int i10, Object obj2) {
        if ((i10 & 2) != 0) {
            imageLoader = Coil.imageLoader(imageView.getContext());
        }
        if ((i10 & 4) != 0) {
            lVar = C06401.INSTANCE;
        }
        ImageRequest.Builder builderTarget = new ImageRequest.Builder(imageView.getContext()).data(obj).target(imageView);
        lVar.invoke(builderTarget);
        return imageLoader.enqueue(builderTarget.build());
    }
}
