package androidx.compose.foundation.lazy.grid;

import androidx.compose.runtime.Composer;
import androidx.media3.extractor.text.ttml.TtmlNode;
import io.ktor.http.ContentDisposition;
import kotlin.Metadata;
import r7.l;
import r7.p;
import r7.q;
import r7.r;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@LazyGridScopeMarker
@Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\bw\u0018\u00002\u00020\u0001Jb\u0010\r\u001a\u00020\n2\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00012\u001b\b\u0002\u0010\u0007\u001a\u0015\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0003¢\u0006\u0002\b\u00062\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00012\u001c\u0010\f\u001a\u0018\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\n0\u0003¢\u0006\u0002\b\u000b¢\u0006\u0002\b\u0006H&¢\u0006\u0004\b\r\u0010\u000eJÊ\u0001\u0010\u0016\u001a\u00020\n2\u0006\u0010\u0010\u001a\u00020\u000f2%\b\u0002\u0010\u0002\u001a\u001f\u0012\u0013\u0012\u00110\u000f¢\u0006\f\b\u0011\u0012\b\b\u0012\u0012\u0004\b\b(\u0013\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u000320\b\u0002\u0010\u0007\u001a*\u0012\u0004\u0012\u00020\u0004\u0012\u0013\u0012\u00110\u000f¢\u0006\f\b\u0011\u0012\b\b\u0012\u0012\u0004\b\b(\u0013\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0014¢\u0006\u0002\b\u00062%\b\u0002\u0010\b\u001a\u001f\u0012\u0013\u0012\u00110\u000f¢\u0006\f\b\u0011\u0012\b\b\u0012\u0012\u0004\b\b(\u0013\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u000321\u0010\u0015\u001a-\u0012\u0004\u0012\u00020\t\u0012\u0013\u0012\u00110\u000f¢\u0006\f\b\u0011\u0012\b\b\u0012\u0012\u0004\b\b(\u0013\u0012\u0004\u0012\u00020\n0\u0014¢\u0006\u0002\b\u000b¢\u0006\u0002\b\u0006H&¢\u0006\u0004\b\u0016\u0010\u0017\u0082\u0001\u0001\u0018ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u0019À\u0006\u0001"}, d2 = {"Landroidx/compose/foundation/lazy/grid/LazyGridScope;", "", "key", "Lkotlin/Function1;", "Landroidx/compose/foundation/lazy/grid/LazyGridItemSpanScope;", "Landroidx/compose/foundation/lazy/grid/GridItemSpan;", "Lx6/n;", TtmlNode.TAG_SPAN, "contentType", "Landroidx/compose/foundation/lazy/grid/LazyGridItemScope;", "Lx6/t0;", "Landroidx/compose/runtime/Composable;", "content", "item", "(Ljava/lang/Object;Lr7/l;Ljava/lang/Object;Lr7/q;)V", "", "count", "Lx6/y;", ContentDisposition.Parameters.Name, "index", "Lkotlin/Function2;", "itemContent", "items", "(ILr7/l;Lr7/p;Lr7/l;Lr7/r;)V", "Landroidx/compose/foundation/lazy/grid/LazyGridIntervalContent;", "foundation_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public interface LazyGridScope {

    /* JADX INFO: renamed from: androidx.compose.foundation.lazy.grid.LazyGridScope$-CC, reason: invalid class name */
    public abstract /* synthetic */ class CC {
        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ void a(LazyGridScope lazyGridScope, Object obj, l lVar, Object obj2, q qVar, int i10, Object obj3) {
            if (obj3 != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: item");
            }
            if ((i10 & 1) != 0) {
                obj = null;
            }
            if ((i10 & 2) != 0) {
                lVar = null;
            }
            if ((i10 & 4) != 0) {
                obj2 = null;
            }
            lazyGridScope.item(obj, lVar, obj2, qVar);
        }

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ void b(LazyGridScope lazyGridScope, int i10, l lVar, p pVar, l lVar2, r rVar, int i11, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: items");
            }
            if ((i11 & 2) != 0) {
                lVar = null;
            }
            if ((i11 & 4) != 0) {
                pVar = null;
            }
            if ((i11 & 8) != 0) {
                lVar2 = AnonymousClass1.INSTANCE;
            }
            lazyGridScope.items(i10, lVar, pVar, lVar2, rVar);
        }
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.lazy.grid.LazyGridScope$items$1, reason: invalid class name */
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

    void item(Object key, l<? super LazyGridItemSpanScope, GridItemSpan> span, Object contentType, q<? super LazyGridItemScope, ? super Composer, ? super Integer, t0> content);

    void items(int count, l<? super Integer, ? extends Object> key, p<? super LazyGridItemSpanScope, ? super Integer, GridItemSpan> span, l<? super Integer, ? extends Object> contentType, r<? super LazyGridItemScope, ? super Integer, ? super Composer, ? super Integer, t0> itemContent);
}
