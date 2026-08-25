package coil.request;

import android.graphics.drawable.Drawable;
import coil.target.Target;
import kotlin.Metadata;
import r7.l;
import x6.t0;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\u0017\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0019\u0010\u0005\u001a\u00020\u00042\b\u0010\u0003\u001a\u0004\u0018\u00010\u0002H\u0016¢\u0006\u0004\b\u0005\u0010\u0006J\u0019\u0010\b\u001a\u00020\u00042\b\u0010\u0007\u001a\u0004\u0018\u00010\u0002H\u0016¢\u0006\u0004\b\b\u0010\u0006J\u0017\u0010\n\u001a\u00020\u00042\u0006\u0010\t\u001a\u00020\u0002H\u0016¢\u0006\u0004\b\n\u0010\u0006¨\u0006\u000b"}, d2 = {"coil/request/ImageRequest$Builder$target$4", "Lcoil/target/Target;", "Landroid/graphics/drawable/Drawable;", "placeholder", "Lx6/t0;", "onStart", "(Landroid/graphics/drawable/Drawable;)V", "error", "onError", "result", "onSuccess", "coil-base_release"}, k = 1, mv = {1, 9, 0}, xi = 176)
public final class ImageRequest$Builder$target$4 implements Target {
    final /* synthetic */ l<Drawable, t0> $onError;
    final /* synthetic */ l<Drawable, t0> $onStart;
    final /* synthetic */ l<Drawable, t0> $onSuccess;

    /* JADX WARN: Multi-variable type inference failed */
    public ImageRequest$Builder$target$4(l<? super Drawable, t0> lVar, l<? super Drawable, t0> lVar2, l<? super Drawable, t0> lVar3) {
        this.$onStart = lVar;
        this.$onError = lVar2;
        this.$onSuccess = lVar3;
    }

    @Override // coil.target.Target
    public void onError(Drawable error) {
        this.$onError.invoke(error);
    }

    @Override // coil.target.Target
    public void onStart(Drawable placeholder) {
        this.$onStart.invoke(placeholder);
    }

    @Override // coil.target.Target
    public void onSuccess(Drawable result) {
        this.$onSuccess.invoke(result);
    }
}
