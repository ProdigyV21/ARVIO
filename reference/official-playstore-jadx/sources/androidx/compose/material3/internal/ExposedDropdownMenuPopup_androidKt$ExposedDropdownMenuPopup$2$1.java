package androidx.compose.material3.internal;

import androidx.compose.ui.unit.LayoutDirection;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.a;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\b\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0003\u001a\u00020\u0000H\n¢\u0006\u0004\b\u0001\u0010\u0002"}, d2 = {"Lx6/t0;", "invoke", "()V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
public final class ExposedDropdownMenuPopup_androidKt$ExposedDropdownMenuPopup$2$1 extends r implements a<t0> {
    final /* synthetic */ LayoutDirection $layoutDirection;
    final /* synthetic */ a<t0> $onDismissRequest;
    final /* synthetic */ PopupLayout $popupLayout;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ExposedDropdownMenuPopup_androidKt$ExposedDropdownMenuPopup$2$1(PopupLayout popupLayout, a<t0> aVar, LayoutDirection layoutDirection) {
        super(0);
        this.$popupLayout = popupLayout;
        this.$onDismissRequest = aVar;
        this.$layoutDirection = layoutDirection;
    }

    @Override // r7.a
    public /* bridge */ /* synthetic */ Object invoke() {
        m2319invoke();
        return t0.f22605a;
    }

    /* JADX INFO: renamed from: invoke, reason: collision with other method in class */
    public final void m2319invoke() {
        this.$popupLayout.updateParameters(this.$onDismissRequest, this.$layoutDirection);
    }
}
