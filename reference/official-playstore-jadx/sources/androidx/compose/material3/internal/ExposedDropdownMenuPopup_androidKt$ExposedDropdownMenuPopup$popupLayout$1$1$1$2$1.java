package androidx.compose.material3.internal;

import androidx.compose.ui.unit.IntSize;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.l;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0005\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u0000H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"Landroidx/compose/ui/unit/IntSize;", "it", "Lx6/t0;", "invoke-ozmzZPI", "(J)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
public final class ExposedDropdownMenuPopup_androidKt$ExposedDropdownMenuPopup$popupLayout$1$1$1$2$1 extends r implements l<IntSize, t0> {
    final /* synthetic */ PopupLayout $this_apply;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ExposedDropdownMenuPopup_androidKt$ExposedDropdownMenuPopup$popupLayout$1$1$1$2$1(PopupLayout popupLayout) {
        super(1);
        this.$this_apply = popupLayout;
    }

    @Override // r7.l
    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        m2320invokeozmzZPI(((IntSize) obj).getPackedValue());
        return t0.f22605a;
    }

    /* JADX INFO: renamed from: invoke-ozmzZPI, reason: not valid java name */
    public final void m2320invokeozmzZPI(long j10) {
        this.$this_apply.m2322setPopupContentSizefhxjrPA(IntSize.m5836boximpl(j10));
        this.$this_apply.updatePosition();
    }
}
