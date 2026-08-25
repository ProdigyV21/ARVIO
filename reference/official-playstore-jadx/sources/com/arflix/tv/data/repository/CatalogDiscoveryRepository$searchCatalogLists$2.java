package com.arflix.tv.data.repository;

import com.arflix.tv.R;
import com.arflix.tv.data.model.CatalogDiscoveryResult;
import com.google.common.util.concurrent.r0;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.CancellationException;
import kotlin.Metadata;
import x6.t0;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0004\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0004\u0010\u0005"}, d2 = {"Lka/k0;", "Lx6/d0;", "", "Lcom/arflix/tv/data/model/CatalogDiscoveryResult;", "<anonymous>", "(Lka/k0;)Lx6/d0;"}, k = 3, mv = {2, 3, 0})
@f7.e(c = "com.arflix.tv.data.repository.CatalogDiscoveryRepository$searchCatalogLists$2", f = "CatalogDiscoveryRepository.kt", l = {32}, m = "invokeSuspend", v = 2)
public final class CatalogDiscoveryRepository$searchCatalogLists$2 extends f7.j implements r7.p<ka.k0, d7.d<? super x6.d0>, Object> {
    final /* synthetic */ String $query;
    Object L$0;
    int label;
    final /* synthetic */ CatalogDiscoveryRepository this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CatalogDiscoveryRepository$searchCatalogLists$2(String str, CatalogDiscoveryRepository catalogDiscoveryRepository, d7.d<? super CatalogDiscoveryRepository$searchCatalogLists$2> dVar) {
        super(2, dVar);
        this.$query = str;
        this.this$0 = catalogDiscoveryRepository;
    }

    @Override // f7.a
    public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
        return new CatalogDiscoveryRepository$searchCatalogLists$2(this.$query, this.this$0, dVar);
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [int] */
    /* JADX WARN: Type inference failed for: r0v1 */
    /* JADX WARN: Type inference failed for: r0v15 */
    /* JADX WARN: Type inference failed for: r0v16 */
    /* JADX WARN: Type inference failed for: r0v17 */
    /* JADX WARN: Type inference failed for: r0v2, types: [java.lang.String] */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:22:0x0048 -> B:58:0x004e). Please report as a decompilation issue!!! */
    @Override // f7.a
    public final Object invokeSuspend(Object obj) throws Exception {
        Object c0Var;
        final ?? r02 = this.label;
        Object obj2 = kotlin.collections.z.f19728i;
        try {
            if (r02 == 0) {
                k2.c.G(obj);
                String string = kotlin.text.o.L0(this.$query).toString();
                if (string.length() < 2) {
                    return new x6.d0(obj2);
                }
                CatalogDiscoveryRepository catalogDiscoveryRepository = this.this$0;
                this.L$0 = string;
                this.label = 1;
                obj = catalogDiscoveryRepository.searchTraktLists(string, this);
                e7.a aVar = e7.a.f15033i;
                r02 = string;
                if (obj == aVar) {
                    return aVar;
                }
            } else {
                if (r02 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                String str = (String) this.L$0;
                k2.c.G(obj);
                r02 = str;
            }
        } catch (Exception e5) {
            if (e5 instanceof CancellationException) {
                throw e5;
            }
            obj = new x6.c0(e5);
            r02 = r02;
        }
        try {
            c0Var = this.this$0.searchMdblistLists(r02);
        } catch (Exception e6) {
            if (e6 instanceof CancellationException) {
                throw e6;
            }
            c0Var = new x6.c0(e6);
        }
        boolean z = obj instanceof x6.c0;
        Collection collection = (Collection) (z ? obj2 : obj);
        boolean z5 = c0Var instanceof x6.c0;
        if (!z5) {
            obj2 = c0Var;
        }
        ArrayList arrayListI0 = kotlin.collections.x.I0(collection, (Iterable) obj2);
        HashSet hashSet = new HashSet();
        ArrayList arrayList = new ArrayList();
        for (Object obj3 : arrayListI0) {
            if (hashSet.add(((CatalogDiscoveryResult) obj3).getSourceUrl().toLowerCase(Locale.ROOT))) {
                arrayList.add(obj3);
            }
        }
        final CatalogDiscoveryRepository catalogDiscoveryRepository2 = this.this$0;
        final Comparator comparator = new Comparator() { // from class: com.arflix.tv.data.repository.CatalogDiscoveryRepository$searchCatalogLists$2$invokeSuspend$$inlined$compareByDescending$1
            /* JADX WARN: Multi-variable type inference failed */
            @Override // java.util.Comparator
            public final int compare(T t2, T t10) {
                return r0.e(Boolean.valueOf(catalogDiscoveryRepository2.relevanceScore(r02, (CatalogDiscoveryResult) t10) > 0), Boolean.valueOf(catalogDiscoveryRepository2.relevanceScore(r02, (CatalogDiscoveryResult) t2) > 0));
            }
        };
        final Comparator comparator2 = new Comparator() { // from class: com.arflix.tv.data.repository.CatalogDiscoveryRepository$searchCatalogLists$2$invokeSuspend$$inlined$thenByDescending$1
            /* JADX WARN: Multi-variable type inference failed */
            @Override // java.util.Comparator
            public final int compare(T t2, T t10) {
                int iCompare = comparator.compare(t2, t10);
                if (iCompare != 0) {
                    return iCompare;
                }
                Integer likes = ((CatalogDiscoveryResult) t10).getLikes();
                if (likes == null) {
                    likes = num;
                }
                Integer likes2 = ((CatalogDiscoveryResult) t2).getLikes();
                return r0.e(likes, likes2 != null ? likes2 : 0);
            }
        };
        final CatalogDiscoveryRepository catalogDiscoveryRepository3 = this.this$0;
        final Comparator comparator3 = new Comparator() { // from class: com.arflix.tv.data.repository.CatalogDiscoveryRepository$searchCatalogLists$2$invokeSuspend$$inlined$thenByDescending$2
            /* JADX WARN: Multi-variable type inference failed */
            @Override // java.util.Comparator
            public final int compare(T t2, T t10) {
                int iCompare = comparator2.compare(t2, t10);
                if (iCompare != 0) {
                    return iCompare;
                }
                return r0.e(Integer.valueOf(catalogDiscoveryRepository3.relevanceScore(r02, (CatalogDiscoveryResult) t10)), Integer.valueOf(catalogDiscoveryRepository3.relevanceScore(r02, (CatalogDiscoveryResult) t2)));
            }
        };
        List listX0 = kotlin.collections.x.X0(kotlin.collections.x.W0(arrayList, new Comparator() { // from class: com.arflix.tv.data.repository.CatalogDiscoveryRepository$searchCatalogLists$2$invokeSuspend$$inlined$thenByDescending$3
            /* JADX WARN: Multi-variable type inference failed */
            @Override // java.util.Comparator
            public final int compare(T t2, T t10) {
                int iCompare = comparator3.compare(t2, t10);
                if (iCompare != 0) {
                    return iCompare;
                }
                Integer itemCount = ((CatalogDiscoveryResult) t10).getItemCount();
                if (itemCount == null) {
                    itemCount = num;
                }
                Integer itemCount2 = ((CatalogDiscoveryResult) t2).getItemCount();
                return r0.e(itemCount, itemCount2 != null ? itemCount2 : 0);
            }
        }), 24);
        boolean zIsEmpty = listX0.isEmpty();
        Object c0Var2 = listX0;
        c0Var2 = listX0;
        c0Var2 = listX0;
        if (zIsEmpty && z && z5) {
            Throwable thA = x6.d0.a(obj);
            if (thA == null && (thA = x6.d0.a(c0Var)) == null) {
                thA = new IllegalStateException(this.this$0.context.getString(R.string.catalog_failed_search));
            }
            c0Var2 = new x6.c0(thA);
        }
        return new x6.d0(c0Var2);
    }

    @Override // r7.p
    public final Object invoke(ka.k0 k0Var, d7.d<? super x6.d0> dVar) {
        return ((CatalogDiscoveryRepository$searchCatalogLists$2) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
    }
}
