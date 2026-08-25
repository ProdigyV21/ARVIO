package coil;

import android.content.Logger;
import android.content.Logs;
import d7.j;
import ka.g0;
import kotlin.Metadata;
import kotlinx.coroutines.CoroutineExceptionHandler;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000!\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003*\u0001\u0000\b\n\u0018\u00002\u00020\u00012\u00020\u0002J\u001f\u0010\b\u001a\u00020\u00072\u0006\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u0005H\u0016¢\u0006\u0004\b\b\u0010\t¨\u0006\n"}, d2 = {"coil/RealImageLoader$special$$inlined$CoroutineExceptionHandler$1", "Ld7/a;", "Lkotlinx/coroutines/CoroutineExceptionHandler;", "Ld7/j;", "context", "", "exception", "Lx6/t0;", "handleException", "(Ld7/j;Ljava/lang/Throwable;)V", "kotlinx-coroutines-core"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final class RealImageLoader$special$$inlined$CoroutineExceptionHandler$1 extends d7.a implements CoroutineExceptionHandler {
    final /* synthetic */ RealImageLoader this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public RealImageLoader$special$$inlined$CoroutineExceptionHandler$1(g0 g0Var, RealImageLoader realImageLoader) {
        super(g0Var);
        this.this$0 = realImageLoader;
    }

    @Override // kotlinx.coroutines.CoroutineExceptionHandler
    public void handleException(j context, Throwable exception) {
        Logger logger = this.this$0.getLogger();
        if (logger != null) {
            Logs.log(logger, "RealImageLoader", exception);
        }
    }
}
