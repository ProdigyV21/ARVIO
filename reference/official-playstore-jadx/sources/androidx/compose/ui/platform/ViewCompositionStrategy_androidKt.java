package androidx.compose.ui.platform;

import androidx.lifecycle.w;
import androidx.lifecycle.y;
import kotlin.Metadata;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a%\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u00042\u0006\u0010\u0001\u001a\u00020\u00002\u0006\u0010\u0003\u001a\u00020\u0002H\u0002¢\u0006\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Landroidx/compose/ui/platform/AbstractComposeView;", "view", "Landroidx/lifecycle/r;", "lifecycle", "Lkotlin/Function0;", "Lx6/t0;", "installForLifecycle", "(Landroidx/compose/ui/platform/AbstractComposeView;Landroidx/lifecycle/r;)Lr7/a;", "ui_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class ViewCompositionStrategy_androidKt {

    /* JADX INFO: renamed from: androidx.compose.ui.platform.ViewCompositionStrategy_androidKt$installForLifecycle$2, reason: invalid class name */
    @Metadata(d1 = {"\u0000\b\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0003\u001a\u00020\u0000H\n¢\u0006\u0004\b\u0001\u0010\u0002"}, d2 = {"Lx6/t0;", "invoke", "()V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class AnonymousClass2 extends kotlin.jvm.internal.r implements r7.a<t0> {
        final /* synthetic */ androidx.lifecycle.r $lifecycle;
        final /* synthetic */ w $observer;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass2(androidx.lifecycle.r rVar, w wVar) {
            super(0);
            this.$lifecycle = rVar;
            this.$observer = wVar;
        }

        @Override // r7.a
        public /* bridge */ /* synthetic */ Object invoke() {
            m5001invoke();
            return t0.f22605a;
        }

        /* JADX INFO: renamed from: invoke, reason: collision with other method in class */
        public final void m5001invoke() {
            this.$lifecycle.removeObserver(this.$observer);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final r7.a<t0> installForLifecycle(final AbstractComposeView abstractComposeView, androidx.lifecycle.r rVar) {
        if (rVar.getCurrentState().compareTo(androidx.lifecycle.q.f3266i) > 0) {
            w wVar = new w() { // from class: androidx.compose.ui.platform.p
                @Override // androidx.lifecycle.w
                public final void onStateChanged(y yVar, androidx.lifecycle.p pVar) {
                    ViewCompositionStrategy_androidKt.installForLifecycle$lambda$1(abstractComposeView, yVar, pVar);
                }
            };
            rVar.addObserver(wVar);
            return new AnonymousClass2(rVar, wVar);
        }
        throw new IllegalStateException(("Cannot configure " + abstractComposeView + " to disposeComposition at Lifecycle ON_DESTROY: " + rVar + "is already destroyed").toString());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void installForLifecycle$lambda$1(AbstractComposeView abstractComposeView, y yVar, androidx.lifecycle.p pVar) {
        if (pVar == androidx.lifecycle.p.ON_DESTROY) {
            abstractComposeView.disposeComposition();
        }
    }
}
