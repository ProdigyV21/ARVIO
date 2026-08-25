package androidx.compose.foundation.lazy;

import androidx.compose.foundation.lazy.LazyListScope;
import androidx.compose.foundation.lazy.layout.LazyLayoutIntervalContent;
import androidx.compose.foundation.lazy.layout.MutableIntervalList;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import io.ktor.http.ContentDisposition;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.z;
import kotlin.jvm.internal.r;
import r7.l;
import r7.q;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000^\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010!\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0004\b\u0000\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u00012\u00020\u0003B \u0012\u0017\u0010\u0007\u001a\u0013\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00050\u0004¢\u0006\u0002\b\u0006¢\u0006\u0004\b\b\u0010\tJ\u0094\u0001\u0010\u0016\u001a\u00020\u00052\u0006\u0010\u000b\u001a\u00020\n2#\u0010\u0010\u001a\u001f\u0012\u0013\u0012\u00110\n¢\u0006\f\b\f\u0012\b\b\r\u0012\u0004\b\b(\u000e\u0012\u0004\u0012\u00020\u000f\u0018\u00010\u00042#\u0010\u0011\u001a\u001f\u0012\u0013\u0012\u00110\n¢\u0006\f\b\f\u0012\b\b\r\u0012\u0004\b\b(\u000e\u0012\u0006\u0012\u0004\u0018\u00010\u000f0\u000421\u0010\u0015\u001a-\u0012\u0004\u0012\u00020\u0013\u0012\u0013\u0012\u00110\n¢\u0006\f\b\f\u0012\b\b\r\u0012\u0004\b\b(\u000e\u0012\u0004\u0012\u00020\u00050\u0012¢\u0006\u0002\b\u0014¢\u0006\u0002\b\u0006H\u0016¢\u0006\u0004\b\u0016\u0010\u0017JA\u0010\u0018\u001a\u00020\u00052\b\u0010\u0010\u001a\u0004\u0018\u00010\u000f2\b\u0010\u0011\u001a\u0004\u0018\u00010\u000f2\u001c\u0010\u0007\u001a\u0018\u0012\u0004\u0012\u00020\u0013\u0012\u0004\u0012\u00020\u00050\u0004¢\u0006\u0002\b\u0014¢\u0006\u0002\b\u0006H\u0016¢\u0006\u0004\b\u0018\u0010\u0019JA\u0010\u001a\u001a\u00020\u00052\b\u0010\u0010\u001a\u0004\u0018\u00010\u000f2\b\u0010\u0011\u001a\u0004\u0018\u00010\u000f2\u001c\u0010\u0007\u001a\u0018\u0012\u0004\u0012\u00020\u0013\u0012\u0004\u0012\u00020\u00050\u0004¢\u0006\u0002\b\u0014¢\u0006\u0002\b\u0006H\u0017¢\u0006\u0004\b\u001a\u0010\u0019R \u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u00020\u001b8\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\u001c\u0010\u001d\u001a\u0004\b\u001e\u0010\u001fR\u001e\u0010!\u001a\n\u0012\u0004\u0012\u00020\n\u0018\u00010 8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b!\u0010\"R\u0017\u0010&\u001a\b\u0012\u0004\u0012\u00020\n0#8F¢\u0006\u0006\u001a\u0004\b$\u0010%¨\u0006'"}, d2 = {"Landroidx/compose/foundation/lazy/LazyListIntervalContent;", "Landroidx/compose/foundation/lazy/layout/LazyLayoutIntervalContent;", "Landroidx/compose/foundation/lazy/LazyListInterval;", "Landroidx/compose/foundation/lazy/LazyListScope;", "Lkotlin/Function1;", "Lx6/t0;", "Lx6/n;", "content", "<init>", "(Lr7/l;)V", "", "count", "Lx6/y;", ContentDisposition.Parameters.Name, "index", "", "key", "contentType", "Lkotlin/Function2;", "Landroidx/compose/foundation/lazy/LazyItemScope;", "Landroidx/compose/runtime/Composable;", "itemContent", "items", "(ILr7/l;Lr7/l;Lr7/r;)V", "item", "(Ljava/lang/Object;Ljava/lang/Object;Lr7/q;)V", "stickyHeader", "Landroidx/compose/foundation/lazy/layout/MutableIntervalList;", "intervals", "Landroidx/compose/foundation/lazy/layout/MutableIntervalList;", "getIntervals", "()Landroidx/compose/foundation/lazy/layout/MutableIntervalList;", "", "_headerIndexes", "Ljava/util/List;", "", "getHeaderIndexes", "()Ljava/util/List;", "headerIndexes", "foundation_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class LazyListIntervalContent extends LazyLayoutIntervalContent<LazyListInterval> implements LazyListScope {
    public static final int $stable = 8;
    private List<Integer> _headerIndexes;
    private final MutableIntervalList<LazyListInterval> intervals = new MutableIntervalList<>();

    /* JADX INFO: renamed from: androidx.compose.foundation.lazy.LazyListIntervalContent$item$1, reason: invalid class name */
    @Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "<anonymous parameter 0>", "", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class AnonymousClass1 extends r implements l<Integer, Object> {
        final /* synthetic */ Object $key;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(Object obj) {
            super(1);
            this.$key = obj;
        }

        @Override // r7.l
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            return invoke(((Number) obj).intValue());
        }

        public final Object invoke(int i10) {
            return this.$key;
        }
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.lazy.LazyListIntervalContent$item$2, reason: invalid class name */
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

    /* JADX INFO: renamed from: androidx.compose.foundation.lazy.LazyListIntervalContent$item$3, reason: invalid class name */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0006\u001a\u00020\u0003*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u0001H\u000b¢\u0006\u0004\b\u0004\u0010\u0005"}, d2 = {"Landroidx/compose/foundation/lazy/LazyItemScope;", "", "it", "Lx6/t0;", "invoke", "(Landroidx/compose/foundation/lazy/LazyItemScope;ILandroidx/compose/runtime/Composer;I)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class AnonymousClass3 extends r implements r7.r<LazyItemScope, Integer, Composer, Integer, t0> {
        final /* synthetic */ q<LazyItemScope, Composer, Integer, t0> $content;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public AnonymousClass3(q<? super LazyItemScope, ? super Composer, ? super Integer, t0> qVar) {
            super(4);
            this.$content = qVar;
        }

        @Override // r7.r
        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2, Object obj3, Object obj4) {
            invoke((LazyItemScope) obj, ((Number) obj2).intValue(), (Composer) obj3, ((Number) obj4).intValue());
            return t0.f22605a;
        }

        public final void invoke(LazyItemScope lazyItemScope, int i10, Composer composer, int i11) {
            if ((i11 & 14) == 0) {
                i11 |= composer.changed(lazyItemScope) ? 4 : 2;
            }
            if ((i11 & 651) == 130 && composer.getSkipping()) {
                composer.skipToGroupEnd();
                return;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1010194746, i11, -1, "androidx.compose.foundation.lazy.LazyListIntervalContent.item.<anonymous> (LazyListIntervalContent.kt:58)");
            }
            this.$content.invoke(lazyItemScope, composer, Integer.valueOf(i11 & 14));
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
    }

    public LazyListIntervalContent(l<? super LazyListScope, t0> lVar) {
        lVar.invoke(this);
    }

    public final List<Integer> getHeaderIndexes() {
        List<Integer> list = this._headerIndexes;
        return list == null ? z.f19728i : list;
    }

    @Override // androidx.compose.foundation.lazy.LazyListScope
    public final /* synthetic */ void item(Object obj, q qVar) {
        item(obj, null, qVar);
    }

    @Override // androidx.compose.foundation.lazy.LazyListScope
    public final /* synthetic */ void items(int i10, l lVar, r7.r rVar) {
        LazyListScope.CC.d(this, i10, lVar, rVar);
    }

    @Override // androidx.compose.foundation.lazy.LazyListScope
    public void stickyHeader(Object key, Object contentType, q<? super LazyItemScope, ? super Composer, ? super Integer, t0> content) {
        List arrayList = this._headerIndexes;
        if (arrayList == null) {
            arrayList = new ArrayList();
            this._headerIndexes = arrayList;
        }
        arrayList.add(Integer.valueOf(getIntervals().getSize()));
        item(key, contentType, content);
    }

    @Override // androidx.compose.foundation.lazy.layout.LazyLayoutIntervalContent
    public MutableIntervalList<LazyListInterval> getIntervals() {
        return this.intervals;
    }

    @Override // androidx.compose.foundation.lazy.LazyListScope
    public void item(Object key, Object contentType, q<? super LazyItemScope, ? super Composer, ? super Integer, t0> content) {
        getIntervals().addInterval(1, new LazyListInterval(key != null ? new AnonymousClass1(key) : null, new AnonymousClass2(contentType), ComposableLambdaKt.composableLambdaInstance(-1010194746, true, new AnonymousClass3(content))));
    }

    @Override // androidx.compose.foundation.lazy.LazyListScope
    public void items(int count, l<? super Integer, ? extends Object> key, l<? super Integer, ? extends Object> contentType, r7.r<? super LazyItemScope, ? super Integer, ? super Composer, ? super Integer, t0> itemContent) {
        getIntervals().addInterval(count, new LazyListInterval(key, contentType, itemContent));
    }
}
