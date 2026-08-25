package androidx.compose.ui.window;

import kotlin.Metadata;
import kotlin.jvm.internal.r;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"<anonymous>", "", "invoke", "()Ljava/lang/Boolean;"}, k = 3, mv = {1, 8, 0}, xi = 48)
public final class PopupLayout$canCalculatePosition$2 extends r implements r7.a<Boolean> {
    final /* synthetic */ PopupLayout this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public PopupLayout$canCalculatePosition$2(PopupLayout popupLayout) {
        super(0);
        this.this$0 = popupLayout;
    }

    @Override // r7.a
    public final Boolean invoke() {
        return Boolean.valueOf((this.this$0.getParentLayoutCoordinates() == null || this.this$0.m5937getPopupContentSizebOM6tXw() == null) ? false : true);
    }
}
