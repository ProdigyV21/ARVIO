package androidx.compose.ui.focus;

import kotlin.Metadata;
import r7.l;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0000\u0018\u00002\u00020\u0001B \u0012\u0017\u0010\u0006\u001a\u0013\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00040\u0002¢\u0006\u0002\b\u0005¢\u0006\u0004\b\u0007\u0010\bJ\u0017\u0010\u000b\u001a\u00020\u00042\u0006\u0010\n\u001a\u00020\tH\u0016¢\u0006\u0004\b\u000b\u0010\fR(\u0010\u0006\u001a\u0013\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00040\u0002¢\u0006\u0002\b\u00058\u0006¢\u0006\f\n\u0004\b\u0006\u0010\r\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u0010"}, d2 = {"Landroidx/compose/ui/focus/FocusOrderToProperties;", "Landroidx/compose/ui/focus/FocusPropertiesScope;", "Lkotlin/Function1;", "Landroidx/compose/ui/focus/FocusOrder;", "Lx6/t0;", "Lx6/n;", "focusOrderReceiver", "<init>", "(Lr7/l;)V", "Landroidx/compose/ui/focus/FocusProperties;", "focusProperties", "apply", "(Landroidx/compose/ui/focus/FocusProperties;)V", "Lr7/l;", "getFocusOrderReceiver", "()Lr7/l;", "ui_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class FocusOrderToProperties implements FocusPropertiesScope {
    public static final int $stable = 0;
    private final l<FocusOrder, t0> focusOrderReceiver;

    /* JADX WARN: Multi-variable type inference failed */
    public FocusOrderToProperties(l<? super FocusOrder, t0> lVar) {
        this.focusOrderReceiver = lVar;
    }

    @Override // androidx.compose.ui.focus.FocusPropertiesScope
    public void apply(FocusProperties focusProperties) {
        this.focusOrderReceiver.invoke(new FocusOrder(focusProperties));
    }

    public final l<FocusOrder, t0> getFocusOrderReceiver() {
        return this.focusOrderReceiver;
    }
}
