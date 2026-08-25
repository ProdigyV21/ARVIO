package androidx.compose.ui.platform;

import android.view.View;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001R\u0016\u0010\u0002\u001a\u0004\u0018\u00010\u00038VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005R\u0016\u0010\u0006\u001a\u0004\u0018\u00010\u00078VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\b\u0010\tø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\nÀ\u0006\u0003"}, d2 = {"Landroidx/compose/ui/platform/ViewRootForInspector;", "", "subCompositionView", "Landroidx/compose/ui/platform/AbstractComposeView;", "getSubCompositionView", "()Landroidx/compose/ui/platform/AbstractComposeView;", "viewRoot", "Landroid/view/View;", "getViewRoot", "()Landroid/view/View;", "ui_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public interface ViewRootForInspector {

    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class DefaultImpls {
        @Deprecated
        public static AbstractComposeView getSubCompositionView(ViewRootForInspector viewRootForInspector) {
            return r.a(viewRootForInspector);
        }

        @Deprecated
        public static View getViewRoot(ViewRootForInspector viewRootForInspector) {
            return r.b(viewRootForInspector);
        }
    }

    AbstractComposeView getSubCompositionView();

    View getViewRoot();
}
