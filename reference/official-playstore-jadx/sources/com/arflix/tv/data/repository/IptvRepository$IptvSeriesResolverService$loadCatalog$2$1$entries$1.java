package com.arflix.tv.data.repository;

import com.arflix.tv.data.repository.IptvRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import x6.t0;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"Lka/k0;", "", "Lcom/arflix/tv/data/repository/IptvRepository$ResolverSeriesEntry;", "<anonymous>", "(Lka/k0;)Ljava/util/List;"}, k = 3, mv = {2, 3, 0})
@f7.e(c = "com.arflix.tv.data.repository.IptvRepository$IptvSeriesResolverService$loadCatalog$2$1$entries$1", f = "IptvRepository.kt", l = {3726}, m = "invokeSuspend", v = 2)
public final class IptvRepository$IptvSeriesResolverService$loadCatalog$2$1$entries$1 extends f7.j implements r7.p<ka.k0, d7.d<? super List<? extends IptvRepository.ResolverSeriesEntry>>, Object> {
    final /* synthetic */ IptvRepository.XtreamCredentials $creds;
    final /* synthetic */ long $fetchStart;
    int label;
    final /* synthetic */ IptvRepository this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public IptvRepository$IptvSeriesResolverService$loadCatalog$2$1$entries$1(IptvRepository iptvRepository, IptvRepository.XtreamCredentials xtreamCredentials, long j10, d7.d<? super IptvRepository$IptvSeriesResolverService$loadCatalog$2$1$entries$1> dVar) {
        super(2, dVar);
        this.this$0 = iptvRepository;
        this.$creds = xtreamCredentials;
        this.$fetchStart = j10;
    }

    @Override // f7.a
    public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
        return new IptvRepository$IptvSeriesResolverService$loadCatalog$2$1$entries$1(this.this$0, this.$creds, this.$fetchStart, dVar);
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
        int i10 = this.label;
        if (i10 == 0) {
            k2.c.G(obj);
            IptvRepository iptvRepository = this.this$0;
            IptvRepository.XtreamCredentials xtreamCredentials = this.$creds;
            this.label = 1;
            obj = iptvRepository.getXtreamSeriesList(xtreamCredentials, true, false, this);
            e7.a aVar = e7.a.f15033i;
            if (obj == aVar) {
                return aVar;
            }
        } else {
            if (i10 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            k2.c.G(obj);
        }
        List<IptvRepository.XtreamSeriesItem> list = (List) obj;
        System.err.println("[VOD-Resolver] loadCatalog: got " + list.size() + " raw series in " + (System.currentTimeMillis() - this.$fetchStart) + "ms, building entries...");
        IptvRepository iptvRepository2 = this.this$0;
        ArrayList arrayList = new ArrayList();
        for (IptvRepository.XtreamSeriesItem xtreamSeriesItem : list) {
            Integer seriesId = xtreamSeriesItem.getSeriesId();
            IptvRepository.ResolverSeriesEntry resolverSeriesEntry = null;
            if (seriesId != null) {
                int iIntValue = seriesId.intValue();
                String name = xtreamSeriesItem.getName();
                String string = name != null ? kotlin.text.o.L0(name).toString() : null;
                String str = string == null ? "" : string;
                if (!kotlin.text.o.h0(str)) {
                    String strNormalizeLookupText = iptvRepository2.normalizeLookupText(str);
                    Set setExtractTitleTokensFromNormalized = iptvRepository2.extractTitleTokensFromNormalized(strNormalizeLookupText);
                    String canonicalTitleKeyFromTokens = iptvRepository2.toCanonicalTitleKeyFromTokens(setExtractTitleTokensFromNormalized);
                    String strNormalizeTmdbId = iptvRepository2.normalizeTmdbId(xtreamSeriesItem.getTmdb());
                    String strNormalizeImdbId = iptvRepository2.normalizeImdbId(xtreamSeriesItem.getImdb());
                    String name2 = xtreamSeriesItem.getName();
                    if (name2 == null) {
                        name2 = "";
                    }
                    resolverSeriesEntry = new IptvRepository.ResolverSeriesEntry(iIntValue, str, strNormalizeLookupText, canonicalTitleKeyFromTokens, setExtractTitleTokensFromNormalized, strNormalizeTmdbId, strNormalizeImdbId, iptvRepository2.parseYear(name2));
                }
            }
            if (resolverSeriesEntry != null) {
                arrayList.add(resolverSeriesEntry);
            }
        }
        return arrayList;
    }

    @Override // r7.p
    public final Object invoke(ka.k0 k0Var, d7.d<? super List<IptvRepository.ResolverSeriesEntry>> dVar) {
        return ((IptvRepository$IptvSeriesResolverService$loadCatalog$2$1$entries$1) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
    }
}
