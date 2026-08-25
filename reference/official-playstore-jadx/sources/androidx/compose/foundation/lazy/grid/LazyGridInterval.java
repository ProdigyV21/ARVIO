package androidx.compose.foundation.lazy.grid;

import androidx.compose.foundation.lazy.layout.LazyLayoutIntervalContent;
import androidx.compose.runtime.Composer;
import androidx.media3.extractor.text.ttml.TtmlNode;
import io.ktor.http.ContentDisposition;
import io.ktor.http.LinkHeader;
import kotlin.Metadata;
import r7.l;
import r7.p;
import r7.r;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u000e\b\u0000\u0018\u00002\u00020\u0001B\u0094\u0001\u0012#\u0010\b\u001a\u001f\u0012\u0013\u0012\u00110\u0003¢\u0006\f\b\u0004\u0012\b\b\u0005\u0012\u0004\b\b(\u0006\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u0002\u0012\u001d\u0010\r\u001a\u0019\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u000b0\t¢\u0006\u0002\b\f\u0012#\u0010\u000e\u001a\u001f\u0012\u0013\u0012\u00110\u0003¢\u0006\f\b\u0004\u0012\b\b\u0005\u0012\u0004\b\b(\u0006\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u0002\u0012\"\u0010\u0012\u001a\u001e\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00100\t¢\u0006\u0002\b\u0011¢\u0006\u0002\b\f¢\u0006\u0004\b\u0013\u0010\u0014R7\u0010\b\u001a\u001f\u0012\u0013\u0012\u00110\u0003¢\u0006\f\b\u0004\u0012\b\b\u0005\u0012\u0004\b\b(\u0006\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u00028\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\b\u0010\u0015\u001a\u0004\b\u0016\u0010\u0017R.\u0010\r\u001a\u0019\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u000b0\t¢\u0006\u0002\b\f8\u0006¢\u0006\f\n\u0004\b\r\u0010\u0018\u001a\u0004\b\u0019\u0010\u001aR7\u0010\u000e\u001a\u001f\u0012\u0013\u0012\u00110\u0003¢\u0006\f\b\u0004\u0012\b\b\u0005\u0012\u0004\b\b(\u0006\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u00028\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\u000e\u0010\u0015\u001a\u0004\b\u001b\u0010\u0017R3\u0010\u0012\u001a\u001e\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00100\t¢\u0006\u0002\b\u0011¢\u0006\u0002\b\f8\u0006¢\u0006\f\n\u0004\b\u0012\u0010\u001c\u001a\u0004\b\u001d\u0010\u001e¨\u0006\u001f"}, d2 = {"Landroidx/compose/foundation/lazy/grid/LazyGridInterval;", "Landroidx/compose/foundation/lazy/layout/LazyLayoutIntervalContent$Interval;", "Lkotlin/Function1;", "", "Lx6/y;", ContentDisposition.Parameters.Name, "index", "", "key", "Lkotlin/Function2;", "Landroidx/compose/foundation/lazy/grid/LazyGridItemSpanScope;", "Landroidx/compose/foundation/lazy/grid/GridItemSpan;", "Lx6/n;", TtmlNode.TAG_SPAN, LinkHeader.Parameters.Type, "Landroidx/compose/foundation/lazy/grid/LazyGridItemScope;", "Lx6/t0;", "Landroidx/compose/runtime/Composable;", "item", "<init>", "(Lr7/l;Lr7/p;Lr7/l;Lr7/r;)V", "Lr7/l;", "getKey", "()Lr7/l;", "Lr7/p;", "getSpan", "()Lr7/p;", "getType", "Lr7/r;", "getItem", "()Lr7/r;", "foundation_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class LazyGridInterval implements LazyLayoutIntervalContent.Interval {
    public static final int $stable = 0;
    private final r<LazyGridItemScope, Integer, Composer, Integer, t0> item;
    private final l<Integer, Object> key;
    private final p<LazyGridItemSpanScope, Integer, GridItemSpan> span;
    private final l<Integer, Object> type;

    /* JADX WARN: Multi-variable type inference failed */
    public LazyGridInterval(l<? super Integer, ? extends Object> lVar, p<? super LazyGridItemSpanScope, ? super Integer, GridItemSpan> pVar, l<? super Integer, ? extends Object> lVar2, r<? super LazyGridItemScope, ? super Integer, ? super Composer, ? super Integer, t0> rVar) {
        this.key = lVar;
        this.span = pVar;
        this.type = lVar2;
        this.item = rVar;
    }

    public final r<LazyGridItemScope, Integer, Composer, Integer, t0> getItem() {
        return this.item;
    }

    @Override // androidx.compose.foundation.lazy.layout.LazyLayoutIntervalContent.Interval
    public l<Integer, Object> getKey() {
        return this.key;
    }

    public final p<LazyGridItemSpanScope, Integer, GridItemSpan> getSpan() {
        return this.span;
    }

    @Override // androidx.compose.foundation.lazy.layout.LazyLayoutIntervalContent.Interval
    public l<Integer, Object> getType() {
        return this.type;
    }
}
