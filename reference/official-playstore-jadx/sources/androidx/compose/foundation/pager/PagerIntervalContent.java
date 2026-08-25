package androidx.compose.foundation.pager;

import androidx.compose.foundation.lazy.layout.LazyLayoutIntervalContent;
import androidx.compose.runtime.Composer;
import io.ktor.http.ContentDisposition;
import kotlin.Metadata;
import r7.l;
import r7.r;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\n\b\u0000\u0018\u00002\u00020\u0001B_\u0012#\u0010\b\u001a\u001f\u0012\u0013\u0012\u00110\u0003¢\u0006\f\b\u0004\u0012\b\b\u0005\u0012\u0004\b\b(\u0006\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u0002\u00121\u0010\u000e\u001a-\u0012\u0004\u0012\u00020\n\u0012\u0013\u0012\u00110\u0003¢\u0006\f\b\u0004\u0012\b\b\u0005\u0012\u0004\b\b(\u0006\u0012\u0004\u0012\u00020\u000b0\t¢\u0006\u0002\b\f¢\u0006\u0002\b\r¢\u0006\u0004\b\u000f\u0010\u0010R7\u0010\b\u001a\u001f\u0012\u0013\u0012\u00110\u0003¢\u0006\f\b\u0004\u0012\b\b\u0005\u0012\u0004\b\b(\u0006\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u00028\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\b\u0010\u0011\u001a\u0004\b\u0012\u0010\u0013RB\u0010\u000e\u001a-\u0012\u0004\u0012\u00020\n\u0012\u0013\u0012\u00110\u0003¢\u0006\f\b\u0004\u0012\b\b\u0005\u0012\u0004\b\b(\u0006\u0012\u0004\u0012\u00020\u000b0\t¢\u0006\u0002\b\f¢\u0006\u0002\b\r8\u0006¢\u0006\f\n\u0004\b\u000e\u0010\u0014\u001a\u0004\b\u0015\u0010\u0016¨\u0006\u0017"}, d2 = {"Landroidx/compose/foundation/pager/PagerIntervalContent;", "Landroidx/compose/foundation/lazy/layout/LazyLayoutIntervalContent$Interval;", "Lkotlin/Function1;", "", "Lx6/y;", ContentDisposition.Parameters.Name, "page", "", "key", "Lkotlin/Function2;", "Landroidx/compose/foundation/pager/PagerScope;", "Lx6/t0;", "Landroidx/compose/runtime/Composable;", "Lx6/n;", "item", "<init>", "(Lr7/l;Lr7/r;)V", "Lr7/l;", "getKey", "()Lr7/l;", "Lr7/r;", "getItem", "()Lr7/r;", "foundation_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class PagerIntervalContent implements LazyLayoutIntervalContent.Interval {
    public static final int $stable = 0;
    private final r<PagerScope, Integer, Composer, Integer, t0> item;
    private final l<Integer, Object> key;

    /* JADX WARN: Multi-variable type inference failed */
    public PagerIntervalContent(l<? super Integer, ? extends Object> lVar, r<? super PagerScope, ? super Integer, ? super Composer, ? super Integer, t0> rVar) {
        this.key = lVar;
        this.item = rVar;
    }

    public final r<PagerScope, Integer, Composer, Integer, t0> getItem() {
        return this.item;
    }

    @Override // androidx.compose.foundation.lazy.layout.LazyLayoutIntervalContent.Interval
    public l<Integer, Object> getKey() {
        return this.key;
    }

    @Override // androidx.compose.foundation.lazy.layout.LazyLayoutIntervalContent.Interval
    public final /* synthetic */ l getType() {
        return LazyLayoutIntervalContent.Interval.CC.b(this);
    }
}
