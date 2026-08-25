package androidx.compose.foundation.lazy;

import androidx.compose.runtime.Composer;
import io.ktor.http.ContentDisposition;
import kotlin.Metadata;
import r7.l;
import r7.q;
import r7.r;
import x6.e;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@LazyScopeMarker
@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\bg\u0018\u00002\u00020\u0001JE\u0010\n\u001a\u00020\u00062\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00012\n\b\u0002\u0010\u0003\u001a\u0004\u0018\u00010\u00012\u001c\u0010\t\u001a\u0018\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u0004¢\u0006\u0002\b\u0007¢\u0006\u0002\b\bH\u0016¢\u0006\u0004\b\n\u0010\u000bJ9\u0010\n\u001a\u00020\u00062\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00012\u001c\u0010\t\u001a\u0018\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u0004¢\u0006\u0002\b\u0007¢\u0006\u0002\b\bH\u0017¢\u0006\u0004\b\n\u0010\fJ\u0098\u0001\u0010\u0014\u001a\u00020\u00062\u0006\u0010\u000e\u001a\u00020\r2%\b\u0002\u0010\u0002\u001a\u001f\u0012\u0013\u0012\u00110\r¢\u0006\f\b\u000f\u0012\b\b\u0010\u0012\u0004\b\b(\u0011\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u00042%\b\u0002\u0010\u0003\u001a\u001f\u0012\u0013\u0012\u00110\r¢\u0006\f\b\u000f\u0012\b\b\u0010\u0012\u0004\b\b(\u0011\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u000421\u0010\u0013\u001a-\u0012\u0004\u0012\u00020\u0005\u0012\u0013\u0012\u00110\r¢\u0006\f\b\u000f\u0012\b\b\u0010\u0012\u0004\b\b(\u0011\u0012\u0004\u0012\u00020\u00060\u0012¢\u0006\u0002\b\u0007¢\u0006\u0002\b\bH\u0016¢\u0006\u0004\b\u0014\u0010\u0015Jq\u0010\u0014\u001a\u00020\u00062\u0006\u0010\u000e\u001a\u00020\r2%\b\u0002\u0010\u0002\u001a\u001f\u0012\u0013\u0012\u00110\r¢\u0006\f\b\u000f\u0012\b\b\u0010\u0012\u0004\b\b(\u0011\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u000421\u0010\u0013\u001a-\u0012\u0004\u0012\u00020\u0005\u0012\u0013\u0012\u00110\r¢\u0006\f\b\u000f\u0012\b\b\u0010\u0012\u0004\b\b(\u0011\u0012\u0004\u0012\u00020\u00060\u0012¢\u0006\u0002\b\u0007¢\u0006\u0002\b\bH\u0017¢\u0006\u0004\b\u0014\u0010\u0016JE\u0010\u0017\u001a\u00020\u00062\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00012\n\b\u0002\u0010\u0003\u001a\u0004\u0018\u00010\u00012\u001c\u0010\t\u001a\u0018\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u0004¢\u0006\u0002\b\u0007¢\u0006\u0002\b\bH'¢\u0006\u0004\b\u0017\u0010\u000bø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u0018À\u0006\u0003"}, d2 = {"Landroidx/compose/foundation/lazy/LazyListScope;", "", "key", "contentType", "Lkotlin/Function1;", "Landroidx/compose/foundation/lazy/LazyItemScope;", "Lx6/t0;", "Landroidx/compose/runtime/Composable;", "Lx6/n;", "content", "item", "(Ljava/lang/Object;Ljava/lang/Object;Lr7/q;)V", "(Ljava/lang/Object;Lr7/q;)V", "", "count", "Lx6/y;", ContentDisposition.Parameters.Name, "index", "Lkotlin/Function2;", "itemContent", "items", "(ILr7/l;Lr7/l;Lr7/r;)V", "(ILr7/l;Lr7/r;)V", "stickyHeader", "foundation_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public interface LazyListScope {

    /* JADX INFO: renamed from: androidx.compose.foundation.lazy.LazyListScope$-CC, reason: invalid class name */
    public abstract /* synthetic */ class CC {
        public static void a(LazyListScope lazyListScope, Object obj, Object obj2, q qVar) {
            throw new IllegalStateException("The method is not implemented");
        }

        public static void c(LazyListScope lazyListScope, int i10, l lVar, l lVar2, r rVar) {
            throw new IllegalStateException("The method is not implemented");
        }

        public static /* synthetic */ void d(LazyListScope lazyListScope, int i10, l lVar, r rVar) {
            lazyListScope.items(i10, lVar, AnonymousClass2.INSTANCE, rVar);
        }

        public static /* synthetic */ void i(LazyListScope lazyListScope, Object obj, Object obj2, q qVar, int i10, Object obj3) {
            if (obj3 != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: item");
            }
            if ((i10 & 1) != 0) {
                obj = null;
            }
            if ((i10 & 2) != 0) {
                obj2 = null;
            }
            lazyListScope.item(obj, obj2, qVar);
        }

        public static /* synthetic */ void j(LazyListScope lazyListScope, Object obj, q qVar, int i10, Object obj2) {
            if (obj2 != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: item");
            }
            if ((i10 & 1) != 0) {
                obj = null;
            }
            lazyListScope.item(obj, qVar);
        }

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ void k(LazyListScope lazyListScope, int i10, l lVar, l lVar2, r rVar, int i11, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: items");
            }
            if ((i11 & 2) != 0) {
                lVar = null;
            }
            if ((i11 & 4) != 0) {
                lVar2 = AnonymousClass1.INSTANCE;
            }
            lazyListScope.items(i10, lVar, lVar2, rVar);
        }

        public static /* synthetic */ void l(LazyListScope lazyListScope, int i10, l lVar, r rVar, int i11, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: items");
            }
            if ((i11 & 2) != 0) {
                lVar = null;
            }
            lazyListScope.items(i10, lVar, rVar);
        }

        public static /* synthetic */ void m(LazyListScope lazyListScope, Object obj, Object obj2, q qVar, int i10, Object obj3) {
            if (obj3 != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: stickyHeader");
            }
            if ((i10 & 1) != 0) {
                obj = null;
            }
            if ((i10 & 2) != 0) {
                obj2 = null;
            }
            lazyListScope.stickyHeader(obj, obj2, qVar);
        }
    }

    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class DefaultImpls {
        @Deprecated
        public static void item(LazyListScope lazyListScope, Object obj, Object obj2, q<? super LazyItemScope, ? super Composer, ? super Integer, t0> qVar) {
            CC.a(lazyListScope, obj, obj2, qVar);
        }

        @Deprecated
        public static void items(LazyListScope lazyListScope, int i10, l<? super Integer, ? extends Object> lVar, l<? super Integer, ? extends Object> lVar2, r<? super LazyItemScope, ? super Integer, ? super Composer, ? super Integer, t0> rVar) {
            CC.c(lazyListScope, i10, lVar, lVar2, rVar);
        }
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.lazy.LazyListScope$items$1, reason: invalid class name */
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

    /* JADX INFO: renamed from: androidx.compose.foundation.lazy.LazyListScope$items$2, reason: invalid class name */
    @Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\u0010\u0000\u001a\u0004\u0018\u00010\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "it", "", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class AnonymousClass2 extends kotlin.jvm.internal.r implements l<Integer, Object> {
        public static final AnonymousClass2 INSTANCE = new AnonymousClass2();

        public AnonymousClass2() {
            super(1);
        }

        public final Object invoke(int i10) {
            return null;
        }

        @Override // r7.l
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            return invoke(((Number) obj).intValue());
        }
    }

    void item(Object key, Object contentType, q<? super LazyItemScope, ? super Composer, ? super Integer, t0> content);

    @e
    /* synthetic */ void item(Object key, q content);

    void items(int count, l<? super Integer, ? extends Object> key, l<? super Integer, ? extends Object> contentType, r<? super LazyItemScope, ? super Integer, ? super Composer, ? super Integer, t0> itemContent);

    @e
    /* synthetic */ void items(int count, l key, r itemContent);

    void stickyHeader(Object key, Object contentType, q<? super LazyItemScope, ? super Composer, ? super Integer, t0> content);
}
