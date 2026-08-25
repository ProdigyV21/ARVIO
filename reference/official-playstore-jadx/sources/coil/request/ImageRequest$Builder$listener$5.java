package coil.request;

import coil.request.ImageRequest;
import kotlin.Metadata;
import r7.l;
import r7.p;
import x6.t0;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000'\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0017\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002H\u0016¢\u0006\u0004\b\u0005\u0010\u0006J\u0017\u0010\u0007\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002H\u0016¢\u0006\u0004\b\u0007\u0010\u0006J\u001f\u0010\n\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\t\u001a\u00020\bH\u0016¢\u0006\u0004\b\n\u0010\u000bJ\u001f\u0010\r\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\t\u001a\u00020\fH\u0016¢\u0006\u0004\b\r\u0010\u000e¨\u0006\u000f"}, d2 = {"coil/request/ImageRequest$Builder$listener$5", "Lcoil/request/ImageRequest$Listener;", "Lcoil/request/ImageRequest;", "request", "Lx6/t0;", "onStart", "(Lcoil/request/ImageRequest;)V", "onCancel", "Lcoil/request/ErrorResult;", "result", "onError", "(Lcoil/request/ImageRequest;Lcoil/request/ErrorResult;)V", "Lcoil/request/SuccessResult;", "onSuccess", "(Lcoil/request/ImageRequest;Lcoil/request/SuccessResult;)V", "coil-base_release"}, k = 1, mv = {1, 9, 0}, xi = 176)
public final class ImageRequest$Builder$listener$5 implements ImageRequest.Listener {
    final /* synthetic */ l<ImageRequest, t0> $onCancel;
    final /* synthetic */ p<ImageRequest, ErrorResult, t0> $onError;
    final /* synthetic */ l<ImageRequest, t0> $onStart;
    final /* synthetic */ p<ImageRequest, SuccessResult, t0> $onSuccess;

    /* JADX WARN: Multi-variable type inference failed */
    public ImageRequest$Builder$listener$5(l<? super ImageRequest, t0> lVar, l<? super ImageRequest, t0> lVar2, p<? super ImageRequest, ? super ErrorResult, t0> pVar, p<? super ImageRequest, ? super SuccessResult, t0> pVar2) {
        this.$onStart = lVar;
        this.$onCancel = lVar2;
        this.$onError = pVar;
        this.$onSuccess = pVar2;
    }

    @Override // coil.request.ImageRequest.Listener
    public void onCancel(ImageRequest request) {
        this.$onCancel.invoke(request);
    }

    @Override // coil.request.ImageRequest.Listener
    public void onError(ImageRequest request, ErrorResult result) {
        this.$onError.invoke(request, result);
    }

    @Override // coil.request.ImageRequest.Listener
    public void onStart(ImageRequest request) {
        this.$onStart.invoke(request);
    }

    @Override // coil.request.ImageRequest.Listener
    public void onSuccess(ImageRequest request, SuccessResult result) {
        this.$onSuccess.invoke(request, result);
    }
}
