package androidx.compose.foundation.gestures;

import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.input.nestedscroll.NestedScrollSource;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.l;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0001H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "Landroidx/compose/ui/geometry/Offset;", "delta", "invoke-MK-Hz9U", "(J)J"}, k = 3, mv = {1, 8, 0}, xi = 48)
public final class ScrollingLogic$doFlingAnimation$2$outerScopeScroll$1 extends r implements l<Offset, Offset> {
    final /* synthetic */ ScrollScope $$this$scroll;
    final /* synthetic */ ScrollingLogic this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ScrollingLogic$doFlingAnimation$2$outerScopeScroll$1(ScrollingLogic scrollingLogic, ScrollScope scrollScope) {
        super(1);
        this.this$0 = scrollingLogic;
        this.$$this$scroll = scrollScope;
    }

    @Override // r7.l
    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        return Offset.m3225boximpl(m385invokeMKHz9U(((Offset) obj).getPackedValue()));
    }

    /* JADX INFO: renamed from: invoke-MK-Hz9U, reason: not valid java name */
    public final long m385invokeMKHz9U(long j10) {
        ScrollingLogic scrollingLogic = this.this$0;
        return scrollingLogic.m377reverseIfNeededMKHz9U(scrollingLogic.m373dispatchScroll3eAAhYA(this.$$this$scroll, scrollingLogic.m377reverseIfNeededMKHz9U(j10), NestedScrollSource.INSTANCE.m4426getFlingWNlRxjI()));
    }
}
