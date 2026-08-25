package androidx.compose.ui.text.font;

import androidx.compose.ui.text.platform.SynchronizedObject;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.l;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0005\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u0000H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"Landroidx/compose/ui/text/font/TypefaceResult;", "finalResult", "Lx6/t0;", "invoke", "(Landroidx/compose/ui/text/font/TypefaceResult;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
public final class TypefaceRequestCache$runCached$currentTypefaceResult$1 extends r implements l<TypefaceResult, t0> {
    final /* synthetic */ TypefaceRequest $typefaceRequest;
    final /* synthetic */ TypefaceRequestCache this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public TypefaceRequestCache$runCached$currentTypefaceResult$1(TypefaceRequestCache typefaceRequestCache, TypefaceRequest typefaceRequest) {
        super(1);
        this.this$0 = typefaceRequestCache;
        this.$typefaceRequest = typefaceRequest;
    }

    @Override // r7.l
    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((TypefaceResult) obj);
        return t0.f22605a;
    }

    public final void invoke(TypefaceResult typefaceResult) {
        SynchronizedObject lock = this.this$0.getLock();
        TypefaceRequestCache typefaceRequestCache = this.this$0;
        TypefaceRequest typefaceRequest = this.$typefaceRequest;
        synchronized (lock) {
            try {
                if (typefaceResult.getCacheable()) {
                    typefaceRequestCache.resultCache.put(typefaceRequest, typefaceResult);
                } else {
                    typefaceRequestCache.resultCache.remove(typefaceRequest);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }
}
