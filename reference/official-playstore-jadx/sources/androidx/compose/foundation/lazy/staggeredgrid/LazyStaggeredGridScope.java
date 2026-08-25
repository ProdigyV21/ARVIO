package androidx.compose.foundation.lazy.staggeredgrid;

import androidx.compose.runtime.Composer;
import androidx.media3.extractor.text.ttml.TtmlNode;
import io.ktor.http.ContentDisposition;
import kotlin.Metadata;
import r7.l;
import r7.q;
import r7.r;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@LazyStaggeredGridScopeMarker
@Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\bw\u0018\u00002\u00020\u0001JQ\u0010\f\u001a\u00020\b2\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00012\n\b\u0002\u0010\u0003\u001a\u0004\u0018\u00010\u00012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00042\u001c\u0010\u000b\u001a\u0018\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b0\u0006¢\u0006\u0002\b\t¢\u0006\u0002\b\nH&¢\u0006\u0004\b\f\u0010\rJ¿\u0001\u0010\u0015\u001a\u00020\b2\u0006\u0010\u000f\u001a\u00020\u000e2%\b\u0002\u0010\u0002\u001a\u001f\u0012\u0013\u0012\u00110\u000e¢\u0006\f\b\u0010\u0012\b\b\u0011\u0012\u0004\b\b(\u0012\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u00062%\b\u0002\u0010\u0003\u001a\u001f\u0012\u0013\u0012\u00110\u000e¢\u0006\f\b\u0010\u0012\b\b\u0011\u0012\u0004\b\b(\u0012\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u00062%\b\u0002\u0010\u0005\u001a\u001f\u0012\u0013\u0012\u00110\u000e¢\u0006\f\b\u0010\u0012\b\b\u0011\u0012\u0004\b\b(\u0012\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u000621\u0010\u0014\u001a-\u0012\u0004\u0012\u00020\u0007\u0012\u0013\u0012\u00110\u000e¢\u0006\f\b\u0010\u0012\b\b\u0011\u0012\u0004\b\b(\u0012\u0012\u0004\u0012\u00020\b0\u0013¢\u0006\u0002\b\t¢\u0006\u0002\b\nH&¢\u0006\u0004\b\u0015\u0010\u0016\u0082\u0001\u0001\u0017ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u0018À\u0006\u0001"}, d2 = {"Landroidx/compose/foundation/lazy/staggeredgrid/LazyStaggeredGridScope;", "", "key", "contentType", "Landroidx/compose/foundation/lazy/staggeredgrid/StaggeredGridItemSpan;", TtmlNode.TAG_SPAN, "Lkotlin/Function1;", "Landroidx/compose/foundation/lazy/staggeredgrid/LazyStaggeredGridItemScope;", "Lx6/t0;", "Landroidx/compose/runtime/Composable;", "Lx6/n;", "content", "item", "(Ljava/lang/Object;Ljava/lang/Object;Landroidx/compose/foundation/lazy/staggeredgrid/StaggeredGridItemSpan;Lr7/q;)V", "", "count", "Lx6/y;", ContentDisposition.Parameters.Name, "index", "Lkotlin/Function2;", "itemContent", "items", "(ILr7/l;Lr7/l;Lr7/l;Lr7/r;)V", "Landroidx/compose/foundation/lazy/staggeredgrid/LazyStaggeredGridIntervalContent;", "foundation_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public interface LazyStaggeredGridScope {

    /* JADX INFO: renamed from: androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGridScope$-CC, reason: invalid class name */
    public abstract /* synthetic */ class CC {
        public static /* synthetic */ void a(LazyStaggeredGridScope lazyStaggeredGridScope, Object obj, Object obj2, StaggeredGridItemSpan staggeredGridItemSpan, q qVar, int i10, Object obj3) {
            if (obj3 != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: item");
            }
            if ((i10 & 1) != 0) {
                obj = null;
            }
            if ((i10 & 2) != 0) {
                obj2 = null;
            }
            if ((i10 & 4) != 0) {
                staggeredGridItemSpan = null;
            }
            lazyStaggeredGridScope.item(obj, obj2, staggeredGridItemSpan, qVar);
        }

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ void b(LazyStaggeredGridScope lazyStaggeredGridScope, int i10, l lVar, l lVar2, l lVar3, r rVar, int i11, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: items");
            }
            if ((i11 & 2) != 0) {
                lVar = null;
            }
            if ((i11 & 4) != 0) {
                lVar2 = AnonymousClass1.INSTANCE;
            }
            if ((i11 & 8) != 0) {
                lVar3 = null;
            }
            lazyStaggeredGridScope.items(i10, lVar, lVar2, lVar3, rVar);
        }
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGridScope$items$1, reason: invalid class name */
    @Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0001\n\u0000\n\u0002\u0010\b\n\u0000\u0010\u0000\u001a\u0004\u0018\u00010\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "it", "", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class AnonymousClass1 extends kotlin.jvm.internal.r implements l {
        public static final AnonymousClass1 INSTANCE = new AnonymousClass1();

        public AnonymousClass1() {
            super(1);
        }

        public final Void invoke(int i10) {
            return null;
        }

        @Override // r7.l
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            return invoke(((Number) obj).intValue());
        }
    }

    void item(Object key, Object contentType, StaggeredGridItemSpan span, q<? super LazyStaggeredGridItemScope, ? super Composer, ? super Integer, t0> content);

    void items(int count, l<? super Integer, ? extends Object> key, l<? super Integer, ? extends Object> contentType, l<? super Integer, StaggeredGridItemSpan> span, r<? super LazyStaggeredGridItemScope, ? super Integer, ? super Composer, ? super Integer, t0> itemContent);
}
