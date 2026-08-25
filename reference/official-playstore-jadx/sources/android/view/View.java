package android.view;

import android.view.ViewSizeResolver;
import android.view.ViewTreeObserver;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.l;
import x6.t0;

/* JADX INFO: renamed from: coil.size.ViewSizeResolver$size$3$1, reason: from Kotlin metadata */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0007\u001a\u00020\u0004\"\b\b\u0000\u0010\u0001*\u00020\u00002\b\u0010\u0003\u001a\u0004\u0018\u00010\u0002H\n¢\u0006\u0004\b\u0005\u0010\u0006"}, d2 = {"Landroid/view/View;", "T", "", "it", "Lx6/t0;", "invoke", "(Ljava/lang/Throwable;)V", "<anonymous>"}, k = 3, mv = {1, 9, 0})
public final class View extends r implements l<Throwable, t0> {
    final /* synthetic */ ViewSizeResolver$size$3$preDrawListener$1 $preDrawListener;
    final /* synthetic */ ViewTreeObserver $viewTreeObserver;
    final /* synthetic */ ViewSizeResolver<android.view.View> this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public View(ViewSizeResolver viewSizeResolver, ViewTreeObserver viewTreeObserver, ViewSizeResolver$size$3$preDrawListener$1 viewSizeResolver$size$3$preDrawListener$1) {
        super(1);
        this.this$0 = viewSizeResolver;
        this.$viewTreeObserver = viewTreeObserver;
        this.$preDrawListener = viewSizeResolver$size$3$preDrawListener$1;
    }

    @Override // r7.l
    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((Throwable) obj);
        return t0.f22605a;
    }

    public final void invoke(Throwable th) {
        ViewSizeResolver.CC.g(this.this$0, this.$viewTreeObserver, this.$preDrawListener);
    }
}
