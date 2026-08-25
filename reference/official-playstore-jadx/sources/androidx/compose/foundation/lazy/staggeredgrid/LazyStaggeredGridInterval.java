package androidx.compose.foundation.lazy.staggeredgrid;

import androidx.compose.foundation.lazy.layout.LazyLayoutIntervalContent;
import androidx.compose.runtime.Composer;
import androidx.media3.extractor.text.ttml.TtmlNode;
import io.ktor.http.ContentDisposition;
import io.ktor.http.LinkHeader;
import kotlin.Metadata;
import r7.l;
import r7.r;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\f\b\u0000\u0018\u00002\u00020\u0001B\u009a\u0001\u0012#\u0010\b\u001a\u001f\u0012\u0013\u0012\u00110\u0003¢\u0006\f\b\u0004\u0012\b\b\u0005\u0012\u0004\b\b(\u0006\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u0002\u0012#\u0010\t\u001a\u001f\u0012\u0013\u0012\u00110\u0003¢\u0006\f\b\u0004\u0012\b\b\u0005\u0012\u0004\b\b(\u0006\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u0002\u0012#\u0010\u000b\u001a\u001f\u0012\u0013\u0012\u00110\u0003¢\u0006\f\b\u0004\u0012\b\b\u0005\u0012\u0004\b\b(\u0006\u0012\u0004\u0012\u00020\n\u0018\u00010\u0002\u0012\"\u0010\u0011\u001a\u001e\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u000e0\f¢\u0006\u0002\b\u000f¢\u0006\u0002\b\u0010¢\u0006\u0004\b\u0012\u0010\u0013R7\u0010\b\u001a\u001f\u0012\u0013\u0012\u00110\u0003¢\u0006\f\b\u0004\u0012\b\b\u0005\u0012\u0004\b\b(\u0006\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u00028\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\b\u0010\u0014\u001a\u0004\b\u0015\u0010\u0016R7\u0010\t\u001a\u001f\u0012\u0013\u0012\u00110\u0003¢\u0006\f\b\u0004\u0012\b\b\u0005\u0012\u0004\b\b(\u0006\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u00028\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\t\u0010\u0014\u001a\u0004\b\u0017\u0010\u0016R4\u0010\u000b\u001a\u001f\u0012\u0013\u0012\u00110\u0003¢\u0006\f\b\u0004\u0012\b\b\u0005\u0012\u0004\b\b(\u0006\u0012\u0004\u0012\u00020\n\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\u000b\u0010\u0014\u001a\u0004\b\u0018\u0010\u0016R3\u0010\u0011\u001a\u001e\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u000e0\f¢\u0006\u0002\b\u000f¢\u0006\u0002\b\u00108\u0006¢\u0006\f\n\u0004\b\u0011\u0010\u0019\u001a\u0004\b\u001a\u0010\u001b¨\u0006\u001c"}, d2 = {"Landroidx/compose/foundation/lazy/staggeredgrid/LazyStaggeredGridInterval;", "Landroidx/compose/foundation/lazy/layout/LazyLayoutIntervalContent$Interval;", "Lkotlin/Function1;", "", "Lx6/y;", ContentDisposition.Parameters.Name, "index", "", "key", LinkHeader.Parameters.Type, "Landroidx/compose/foundation/lazy/staggeredgrid/StaggeredGridItemSpan;", TtmlNode.TAG_SPAN, "Lkotlin/Function2;", "Landroidx/compose/foundation/lazy/staggeredgrid/LazyStaggeredGridItemScope;", "Lx6/t0;", "Landroidx/compose/runtime/Composable;", "Lx6/n;", "item", "<init>", "(Lr7/l;Lr7/l;Lr7/l;Lr7/r;)V", "Lr7/l;", "getKey", "()Lr7/l;", "getType", "getSpan", "Lr7/r;", "getItem", "()Lr7/r;", "foundation_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class LazyStaggeredGridInterval implements LazyLayoutIntervalContent.Interval {
    public static final int $stable = 0;
    private final r<LazyStaggeredGridItemScope, Integer, Composer, Integer, t0> item;
    private final l<Integer, Object> key;
    private final l<Integer, StaggeredGridItemSpan> span;
    private final l<Integer, Object> type;

    /* JADX WARN: Multi-variable type inference failed */
    public LazyStaggeredGridInterval(l<? super Integer, ? extends Object> lVar, l<? super Integer, ? extends Object> lVar2, l<? super Integer, StaggeredGridItemSpan> lVar3, r<? super LazyStaggeredGridItemScope, ? super Integer, ? super Composer, ? super Integer, t0> rVar) {
        this.key = lVar;
        this.type = lVar2;
        this.span = lVar3;
        this.item = rVar;
    }

    public final r<LazyStaggeredGridItemScope, Integer, Composer, Integer, t0> getItem() {
        return this.item;
    }

    @Override // androidx.compose.foundation.lazy.layout.LazyLayoutIntervalContent.Interval
    public l<Integer, Object> getKey() {
        return this.key;
    }

    public final l<Integer, StaggeredGridItemSpan> getSpan() {
        return this.span;
    }

    @Override // androidx.compose.foundation.lazy.layout.LazyLayoutIntervalContent.Interval
    public l<Integer, Object> getType() {
        return this.type;
    }
}
