package com.arflix.tv.ui.screens.search;

import com.arflix.tv.data.model.Category;
import com.arflix.tv.data.repository.MediaRepository;
import com.arflix.tv.data.repository.PersonMediaSearchResult;
import java.util.ArrayList;
import java.util.List;
import ka.k0;
import kotlin.Metadata;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"Lka/k0;", "", "Lcom/arflix/tv/data/model/Category;", "<anonymous>", "(Lka/k0;)Ljava/util/List;"}, k = 3, mv = {2, 3, 0})
@f7.e(c = "com.arflix.tv.ui.screens.search.SearchViewModel$search$1$1$1$peopleDeferred$1", f = "SearchViewModel.kt", l = {287}, m = "invokeSuspend", v = 2)
public final class SearchViewModel$search$1$1$1$peopleDeferred$1 extends f7.j implements r7.p<k0, d7.d<? super List<? extends Category>>, Object> {
    final /* synthetic */ String $query;
    int label;
    final /* synthetic */ SearchViewModel this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SearchViewModel$search$1$1$1$peopleDeferred$1(SearchViewModel searchViewModel, String str, d7.d<? super SearchViewModel$search$1$1$1$peopleDeferred$1> dVar) {
        super(2, dVar);
        this.this$0 = searchViewModel;
        this.$query = str;
    }

    @Override // f7.a
    public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
        return new SearchViewModel$search$1$1$1$peopleDeferred$1(this.this$0, this.$query, dVar);
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
        SearchViewModel$search$1$1$1$peopleDeferred$1 searchViewModel$search$1$1$1$peopleDeferred$1;
        int i10 = this.label;
        if (i10 == 0) {
            k2.c.G(obj);
            if (kotlin.text.u.L(this.this$0.cachedPeopleQuery, this.$query, true) && !this.this$0.cachedPeopleResults.isEmpty()) {
                return this.this$0.cachedPeopleResults;
            }
            MediaRepository mediaRepository = this.this$0.mediaRepository;
            String str = this.$query;
            this.label = 1;
            searchViewModel$search$1$1$1$peopleDeferred$1 = this;
            obj = MediaRepository.searchPeopleKnownFor$default(mediaRepository, str, 0, searchViewModel$search$1$1$1$peopleDeferred$1, 2, null);
            e7.a aVar = e7.a.f15033i;
            if (obj == aVar) {
                return aVar;
            }
        } else {
            if (i10 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            k2.c.G(obj);
            searchViewModel$search$1$1$1$peopleDeferred$1 = this;
        }
        Iterable<PersonMediaSearchResult> iterable = (Iterable) obj;
        ArrayList arrayList = new ArrayList(kotlin.collections.s.U(iterable, 10));
        for (PersonMediaSearchResult personMediaSearchResult : iterable) {
            arrayList.add(new Category(a0.c.i(personMediaSearchResult.getPersonId(), "person_"), personMediaSearchResult.getName(), personMediaSearchResult.getItems()));
        }
        searchViewModel$search$1$1$1$peopleDeferred$1.this$0.cachedPeopleQuery = searchViewModel$search$1$1$1$peopleDeferred$1.$query;
        searchViewModel$search$1$1$1$peopleDeferred$1.this$0.cachedPeopleResults = arrayList;
        return arrayList;
    }

    @Override // r7.p
    public final Object invoke(k0 k0Var, d7.d<? super List<Category>> dVar) {
        return ((SearchViewModel$search$1$1$1$peopleDeferred$1) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
    }
}
