package androidx.compose.foundation.lazy.staggeredgrid;

import androidx.compose.foundation.lazy.layout.LazyLayoutIntervalContent;
import androidx.compose.foundation.lazy.layout.MutableIntervalList;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.media3.extractor.text.ttml.TtmlNode;
import io.ktor.http.ContentDisposition;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.l;
import r7.q;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000`\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0000\u0018\u00002\u00020\u00012\b\u0012\u0004\u0012\u00020\u00030\u0002B \u0012\u0017\u0010\u0007\u001a\u0013\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00050\u0004¢\u0006\u0002\b\u0006¢\u0006\u0004\b\b\u0010\tJK\u0010\u0011\u001a\u00020\u00052\b\u0010\u000b\u001a\u0004\u0018\u00010\n2\b\u0010\f\u001a\u0004\u0018\u00010\n2\b\u0010\u000e\u001a\u0004\u0018\u00010\r2\u001c\u0010\u0007\u001a\u0018\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u00050\u0004¢\u0006\u0002\b\u0010¢\u0006\u0002\b\u0006H\u0016¢\u0006\u0004\b\u0011\u0010\u0012J¹\u0001\u0010\u001a\u001a\u00020\u00052\u0006\u0010\u0014\u001a\u00020\u00132#\u0010\u000b\u001a\u001f\u0012\u0013\u0012\u00110\u0013¢\u0006\f\b\u0015\u0012\b\b\u0016\u0012\u0004\b\b(\u0017\u0012\u0004\u0012\u00020\n\u0018\u00010\u00042#\u0010\f\u001a\u001f\u0012\u0013\u0012\u00110\u0013¢\u0006\f\b\u0015\u0012\b\b\u0016\u0012\u0004\b\b(\u0017\u0012\u0006\u0012\u0004\u0018\u00010\n0\u00042#\u0010\u000e\u001a\u001f\u0012\u0013\u0012\u00110\u0013¢\u0006\f\b\u0015\u0012\b\b\u0016\u0012\u0004\b\b(\u0017\u0012\u0004\u0012\u00020\r\u0018\u00010\u000421\u0010\u0019\u001a-\u0012\u0004\u0012\u00020\u000f\u0012\u0013\u0012\u00110\u0013¢\u0006\f\b\u0015\u0012\b\b\u0016\u0012\u0004\b\b(\u0017\u0012\u0004\u0012\u00020\u00050\u0018¢\u0006\u0002\b\u0010¢\u0006\u0002\b\u0006H\u0016¢\u0006\u0004\b\u001a\u0010\u001bR \u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u00030\u001c8\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\u001d\u0010\u001e\u001a\u0004\b\u001f\u0010 R\u0017\u0010\"\u001a\u00020!8\u0006¢\u0006\f\n\u0004\b\"\u0010#\u001a\u0004\b$\u0010%¨\u0006&"}, d2 = {"Landroidx/compose/foundation/lazy/staggeredgrid/LazyStaggeredGridIntervalContent;", "Landroidx/compose/foundation/lazy/staggeredgrid/LazyStaggeredGridScope;", "Landroidx/compose/foundation/lazy/layout/LazyLayoutIntervalContent;", "Landroidx/compose/foundation/lazy/staggeredgrid/LazyStaggeredGridInterval;", "Lkotlin/Function1;", "Lx6/t0;", "Lx6/n;", "content", "<init>", "(Lr7/l;)V", "", "key", "contentType", "Landroidx/compose/foundation/lazy/staggeredgrid/StaggeredGridItemSpan;", TtmlNode.TAG_SPAN, "Landroidx/compose/foundation/lazy/staggeredgrid/LazyStaggeredGridItemScope;", "Landroidx/compose/runtime/Composable;", "item", "(Ljava/lang/Object;Ljava/lang/Object;Landroidx/compose/foundation/lazy/staggeredgrid/StaggeredGridItemSpan;Lr7/q;)V", "", "count", "Lx6/y;", ContentDisposition.Parameters.Name, "index", "Lkotlin/Function2;", "itemContent", "items", "(ILr7/l;Lr7/l;Lr7/l;Lr7/r;)V", "Landroidx/compose/foundation/lazy/layout/MutableIntervalList;", "intervals", "Landroidx/compose/foundation/lazy/layout/MutableIntervalList;", "getIntervals", "()Landroidx/compose/foundation/lazy/layout/MutableIntervalList;", "Landroidx/compose/foundation/lazy/staggeredgrid/LazyStaggeredGridSpanProvider;", "spanProvider", "Landroidx/compose/foundation/lazy/staggeredgrid/LazyStaggeredGridSpanProvider;", "getSpanProvider", "()Landroidx/compose/foundation/lazy/staggeredgrid/LazyStaggeredGridSpanProvider;", "foundation_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class LazyStaggeredGridIntervalContent extends LazyLayoutIntervalContent<LazyStaggeredGridInterval> implements LazyStaggeredGridScope {
    public static final int $stable = 8;
    private final MutableIntervalList<LazyStaggeredGridInterval> intervals = new MutableIntervalList<>();
    private final LazyStaggeredGridSpanProvider spanProvider = new LazyStaggeredGridSpanProvider(getIntervals());

    /* JADX INFO: renamed from: androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGridIntervalContent$item$2, reason: invalid class name */
    @Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\u0010\u0000\u001a\u0004\u0018\u00010\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "it", "", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class AnonymousClass2 extends r implements l<Integer, Object> {
        final /* synthetic */ Object $contentType;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass2(Object obj) {
            super(1);
            this.$contentType = obj;
        }

        @Override // r7.l
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            return invoke(((Number) obj).intValue());
        }

        public final Object invoke(int i10) {
            return this.$contentType;
        }
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGridIntervalContent$item$4, reason: invalid class name */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0006\u001a\u00020\u0003*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u0001H\u000b¢\u0006\u0004\b\u0004\u0010\u0005"}, d2 = {"Landroidx/compose/foundation/lazy/staggeredgrid/LazyStaggeredGridItemScope;", "", "it", "Lx6/t0;", "invoke", "(Landroidx/compose/foundation/lazy/staggeredgrid/LazyStaggeredGridItemScope;ILandroidx/compose/runtime/Composer;I)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class AnonymousClass4 extends r implements r7.r<LazyStaggeredGridItemScope, Integer, Composer, Integer, t0> {
        final /* synthetic */ q<LazyStaggeredGridItemScope, Composer, Integer, t0> $content;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public AnonymousClass4(q<? super LazyStaggeredGridItemScope, ? super Composer, ? super Integer, t0> qVar) {
            super(4);
            this.$content = qVar;
        }

        @Override // r7.r
        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2, Object obj3, Object obj4) {
            invoke((LazyStaggeredGridItemScope) obj, ((Number) obj2).intValue(), (Composer) obj3, ((Number) obj4).intValue());
            return t0.f22605a;
        }

        public final void invoke(LazyStaggeredGridItemScope lazyStaggeredGridItemScope, int i10, Composer composer, int i11) {
            if ((i11 & 14) == 0) {
                i11 |= composer.changed(lazyStaggeredGridItemScope) ? 4 : 2;
            }
            if ((i11 & 651) == 130 && composer.getSkipping()) {
                composer.skipToGroupEnd();
                return;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(657818596, i11, -1, "androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGridIntervalContent.item.<anonymous> (LazyStaggeredGridIntervalContent.kt:47)");
            }
            this.$content.invoke(lazyStaggeredGridItemScope, composer, Integer.valueOf(i11 & 14));
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
    }

    public LazyStaggeredGridIntervalContent(l<? super LazyStaggeredGridScope, t0> lVar) {
        lVar.invoke(this);
    }

    public final LazyStaggeredGridSpanProvider getSpanProvider() {
        return this.spanProvider;
    }

    @Override // androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGridScope
    public void item(Object key, Object contentType, StaggeredGridItemSpan span, q<? super LazyStaggeredGridItemScope, ? super Composer, ? super Integer, t0> content) {
        items(1, key != null ? new LazyStaggeredGridIntervalContent$item$1$1(key) : null, new AnonymousClass2(contentType), span != null ? new LazyStaggeredGridIntervalContent$item$3$1(span) : null, ComposableLambdaKt.composableLambdaInstance(657818596, true, new AnonymousClass4(content)));
    }

    @Override // androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGridScope
    public void items(int count, l<? super Integer, ? extends Object> key, l<? super Integer, ? extends Object> contentType, l<? super Integer, StaggeredGridItemSpan> span, r7.r<? super LazyStaggeredGridItemScope, ? super Integer, ? super Composer, ? super Integer, t0> itemContent) {
        getIntervals().addInterval(count, new LazyStaggeredGridInterval(key, contentType, span, itemContent));
    }

    @Override // androidx.compose.foundation.lazy.layout.LazyLayoutIntervalContent
    public MutableIntervalList<LazyStaggeredGridInterval> getIntervals() {
        return this.intervals;
    }
}
