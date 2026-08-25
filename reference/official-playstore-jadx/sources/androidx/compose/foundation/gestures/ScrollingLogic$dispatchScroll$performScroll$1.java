package androidx.compose.foundation.gestures;

import androidx.compose.ui.geometry.Offset;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.l;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0001H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "Landroidx/compose/ui/geometry/Offset;", "delta", "invoke-MK-Hz9U", "(J)J"}, k = 3, mv = {1, 8, 0}, xi = 48)
public final class ScrollingLogic$dispatchScroll$performScroll$1 extends r implements l<Offset, Offset> {
    final /* synthetic */ int $source;
    final /* synthetic */ ScrollScope $this_dispatchScroll;
    final /* synthetic */ ScrollingLogic this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ScrollingLogic$dispatchScroll$performScroll$1(ScrollingLogic scrollingLogic, int i10, ScrollScope scrollScope) {
        super(1);
        this.this$0 = scrollingLogic;
        this.$source = i10;
        this.$this_dispatchScroll = scrollScope;
    }

    @Override // r7.l
    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        return Offset.m3225boximpl(m384invokeMKHz9U(((Offset) obj).getPackedValue()));
    }

    /* JADX INFO: renamed from: invoke-MK-Hz9U, reason: not valid java name */
    public final long m384invokeMKHz9U(long j10) {
        long jM4416dispatchPreScrollOzD1aCk = this.this$0.nestedScrollDispatcher.m4416dispatchPreScrollOzD1aCk(j10, this.$source);
        long jM3240minusMKHz9U = Offset.m3240minusMKHz9U(j10, jM4416dispatchPreScrollOzD1aCk);
        ScrollingLogic scrollingLogic = this.this$0;
        float fM381toFloatk4lQ0M = scrollingLogic.m381toFloatk4lQ0M(scrollingLogic.m377reverseIfNeededMKHz9U(scrollingLogic.m378singleAxisOffsetMKHz9U(jM3240minusMKHz9U)));
        ScrollingLogic scrollingLogic2 = this.this$0;
        long jM377reverseIfNeededMKHz9U = scrollingLogic2.m377reverseIfNeededMKHz9U(scrollingLogic2.m382toOffsettuRUvjQ(this.$this_dispatchScroll.scrollBy(fM381toFloatk4lQ0M)));
        return Offset.m3241plusMKHz9U(Offset.m3241plusMKHz9U(jM4416dispatchPreScrollOzD1aCk, jM377reverseIfNeededMKHz9U), this.this$0.nestedScrollDispatcher.m4414dispatchPostScrollDzOQY0M(jM377reverseIfNeededMKHz9U, Offset.m3240minusMKHz9U(jM3240minusMKHz9U, jM377reverseIfNeededMKHz9U), this.$source));
    }
}
