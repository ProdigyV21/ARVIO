package com.arflix.tv.data.repository;

import androidx.fragment.app.a2;
import com.arflix.tv.data.repository.IptvRepository;
import com.google.gson.JsonObject;
import java.util.LinkedHashMap;
import java.util.List;
import kotlin.Metadata;
import x6.t0;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"Lka/k0;", "", "Lcom/arflix/tv/data/repository/IptvRepository$XtreamSeriesEpisode;", "<anonymous>", "(Lka/k0;)Ljava/util/List;"}, k = 3, mv = {2, 3, 0})
@f7.e(c = "com.arflix.tv.data.repository.IptvRepository$loadXtreamSeriesEpisodes$2$created$1", f = "IptvRepository.kt", l = {4837, 8689}, m = "invokeSuspend", v = 2)
public final class IptvRepository$loadXtreamSeriesEpisodes$2$created$1 extends f7.j implements r7.p<ka.k0, d7.d<? super List<? extends IptvRepository.XtreamSeriesEpisode>>, Object> {
    final /* synthetic */ IptvRepository.XtreamCredentials $creds;
    final /* synthetic */ boolean $fast;
    final /* synthetic */ int $seriesId;
    int I$0;
    int I$1;
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    Object L$4;
    int label;
    final /* synthetic */ IptvRepository this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public IptvRepository$loadXtreamSeriesEpisodes$2$created$1(IptvRepository.XtreamCredentials xtreamCredentials, int i10, IptvRepository iptvRepository, boolean z, d7.d<? super IptvRepository$loadXtreamSeriesEpisodes$2$created$1> dVar) {
        super(2, dVar);
        this.$creds = xtreamCredentials;
        this.$seriesId = i10;
        this.this$0 = iptvRepository;
        this.$fast = z;
    }

    @Override // f7.a
    public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
        return new IptvRepository$loadXtreamSeriesEpisodes$2$created$1(this.$creds, this.$seriesId, this.this$0, this.$fast, dVar);
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
        ua.a aVar;
        List list;
        IptvRepository iptvRepository;
        int i10;
        LinkedHashMap linkedHashMap;
        Integer num;
        int i11 = this.label;
        e7.a aVar2 = e7.a.f15033i;
        if (i11 == 0) {
            k2.c.G(obj);
            String strP = androidx.compose.foundation.c.p(this.$seriesId, "&action=get_series_info&series_id=", a2.D(this.$creds.getBaseUrl(), "/player_api.php?username=", this.$creds.getUsername(), "&password=", this.$creds.getPassword()));
            IptvRepository iptvRepository2 = this.this$0;
            gb.h0 xtreamLookupHttpClient = this.$fast ? iptvRepository2.getXtreamLookupHttpClient() : iptvRepository2.getIptvHttpClient();
            this.L$0 = null;
            this.label = 1;
            obj = iptvRepository2.requestJson(strP, JsonObject.class, xtreamLookupHttpClient, this);
            if (obj != aVar2) {
            }
            return aVar2;
        }
        if (i11 != 1) {
            if (i11 != 2) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            i10 = this.I$0;
            iptvRepository = (IptvRepository) this.L$4;
            aVar = (ua.a) this.L$3;
            list = (List) this.L$2;
            k2.c.G(obj);
            try {
                linkedHashMap = new LinkedHashMap(iptvRepository.cachedXtreamSeriesEpisodes);
                linkedHashMap.put(new Integer(i10), list);
                while (linkedHashMap.size() > iptvRepository.maxSeriesEpisodeCacheEntries && (num = (Integer) kotlin.collections.x.n0(linkedHashMap.keySet())) != null) {
                    linkedHashMap.remove(new Integer(num.intValue()));
                }
                iptvRepository.cachedXtreamSeriesEpisodes = linkedHashMap;
                aVar.b(null);
                return list;
            } catch (Throwable th) {
                aVar.b(null);
                throw th;
            }
        }
        k2.c.G(obj);
        JsonObject jsonObject = (JsonObject) obj;
        if (jsonObject == null) {
            return kotlin.collections.z.f19728i;
        }
        List xtreamSeriesEpisodes = this.this$0.parseXtreamSeriesEpisodes(jsonObject);
        if (this.$fast || xtreamSeriesEpisodes.isEmpty()) {
            return xtreamSeriesEpisodes;
        }
        aVar = this.this$0.xtreamSeriesEpisodeCacheMutex;
        IptvRepository iptvRepository3 = this.this$0;
        int i12 = this.$seriesId;
        this.L$0 = null;
        this.L$1 = null;
        this.L$2 = xtreamSeriesEpisodes;
        this.L$3 = aVar;
        this.L$4 = iptvRepository3;
        this.I$0 = i12;
        this.I$1 = 0;
        this.label = 2;
        if (aVar.c(this) != aVar2) {
            list = xtreamSeriesEpisodes;
            iptvRepository = iptvRepository3;
            i10 = i12;
            linkedHashMap = new LinkedHashMap(iptvRepository.cachedXtreamSeriesEpisodes);
            linkedHashMap.put(new Integer(i10), list);
            while (linkedHashMap.size() > iptvRepository.maxSeriesEpisodeCacheEntries) {
                linkedHashMap.remove(new Integer(num.intValue()));
            }
            iptvRepository.cachedXtreamSeriesEpisodes = linkedHashMap;
            aVar.b(null);
            return list;
        }
        return aVar2;
    }

    @Override // r7.p
    public final Object invoke(ka.k0 k0Var, d7.d<? super List<IptvRepository.XtreamSeriesEpisode>> dVar) {
        return ((IptvRepository$loadXtreamSeriesEpisodes$2$created$1) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
    }
}
