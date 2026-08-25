package com.arflix.tv.data.repository;

import android.content.Context;
import com.arflix.tv.BuildConfig;
import com.arflix.tv.data.api.TraktApi;
import com.arflix.tv.data.model.CatalogDiscoveryResult;
import dagger.hilt.android.qualifiers.ApplicationContext;
import gb.s0;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import javax.inject.Inject;
import javax.inject.Singleton;
import kotlin.Metadata;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;

/* JADX INFO: loaded from: classes3.dex */
@Singleton
@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0007\u0018\u00002\u00020\u0001B#\b\u0007\u0012\b\b\u0001\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0007\u001a\u00020\u0006¢\u0006\u0004\b\b\u0010\tJ\u001e\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\r0\f2\u0006\u0010\u000b\u001a\u00020\nH\u0082@¢\u0006\u0004\b\u000e\u0010\u000fJ\u001d\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\r0\f2\u0006\u0010\u000b\u001a\u00020\nH\u0002¢\u0006\u0004\b\u0010\u0010\u0011J\u0017\u0010\u0013\u001a\u00020\n2\u0006\u0010\u0012\u001a\u00020\nH\u0002¢\u0006\u0004\b\u0013\u0010\u0014J\u001f\u0010\u0017\u001a\u00020\u00162\u0006\u0010\u000b\u001a\u00020\n2\u0006\u0010\u0015\u001a\u00020\rH\u0002¢\u0006\u0004\b\u0017\u0010\u0018J$\u0010\u001b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\r0\f0\u00192\u0006\u0010\u000b\u001a\u00020\nH\u0086@¢\u0006\u0004\b\u001a\u0010\u000fR\u0014\u0010\u0003\u001a\u00020\u00028\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0003\u0010\u001cR\u0014\u0010\u0005\u001a\u00020\u00048\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0005\u0010\u001dR\u0014\u0010\u0007\u001a\u00020\u00068\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0007\u0010\u001e¨\u0006\u001f"}, d2 = {"Lcom/arflix/tv/data/repository/CatalogDiscoveryRepository;", "", "Landroid/content/Context;", "context", "Lcom/arflix/tv/data/api/TraktApi;", "traktApi", "Lgb/h0;", "okHttpClient", "<init>", "(Landroid/content/Context;Lcom/arflix/tv/data/api/TraktApi;Lgb/h0;)V", "", "query", "", "Lcom/arflix/tv/data/model/CatalogDiscoveryResult;", "searchTraktLists", "(Ljava/lang/String;Ld7/d;)Ljava/lang/Object;", "searchMdblistLists", "(Ljava/lang/String;)Ljava/util/List;", "url", "normalizePosterUrl", "(Ljava/lang/String;)Ljava/lang/String;", "result", "", "relevanceScore", "(Ljava/lang/String;Lcom/arflix/tv/data/model/CatalogDiscoveryResult;)I", "Lx6/d0;", "searchCatalogLists-gIAlu-s", "searchCatalogLists", "Landroid/content/Context;", "Lcom/arflix/tv/data/api/TraktApi;", "Lgb/h0;", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class CatalogDiscoveryRepository {
    public static final int $stable = 8;
    private final Context context;
    private final gb.h0 okHttpClient;
    private final TraktApi traktApi;

    /* JADX INFO: renamed from: com.arflix.tv.data.repository.CatalogDiscoveryRepository$searchTraktLists$1, reason: invalid class name */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @f7.e(c = "com.arflix.tv.data.repository.CatalogDiscoveryRepository", f = "CatalogDiscoveryRepository.kt", l = {66}, m = "searchTraktLists", v = 2)
    public static final class AnonymousClass1 extends f7.c {
        Object L$0;
        int label;
        /* synthetic */ Object result;

        public AnonymousClass1(d7.d<? super AnonymousClass1> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return CatalogDiscoveryRepository.this.searchTraktLists(null, this);
        }
    }

    @Inject
    public CatalogDiscoveryRepository(@ApplicationContext Context context, TraktApi traktApi, gb.h0 h0Var) {
        this.context = context;
        this.traktApi = traktApi;
        this.okHttpClient = h0Var;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final String normalizePosterUrl(String url) {
        String string = kotlin.text.o.L0(url).toString();
        return kotlin.text.o.h0(string) ? "" : (kotlin.text.u.P(string, "http://", true) || kotlin.text.u.P(string, "https://", true)) ? string : kotlin.text.u.P(string, "//", false) ? "https:".concat(string) : "https://".concat(string);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final int relevanceScore(String query, CatalogDiscoveryResult result) {
        StringBuilder sb2 = new StringBuilder();
        String title = result.getTitle();
        Locale locale = Locale.ROOT;
        sb2.append(title.toLowerCase(locale));
        sb2.append(' ');
        String description = result.getDescription();
        if (description == null) {
            description = "";
        }
        sb2.append(description.toLowerCase(locale));
        String string = sb2.toString();
        List listH = CatalogDiscoveryRepoRegexes.INSTANCE.getNON_ALPHA_NUM_REGEX().h(query.toLowerCase(locale));
        ArrayList arrayList = new ArrayList();
        for (Object obj : listH) {
            if (((String) obj).length() >= 3) {
                arrayList.add(obj);
            }
        }
        List<String> listG0 = kotlin.collections.x.g0(arrayList);
        if (listG0.isEmpty()) {
            return 0;
        }
        int i10 = 0;
        for (String str : listG0) {
            i10 += (kotlin.text.o.T(string, str, false) ? 1 : 0) + (kotlin.text.o.T(result.getTitle(), str, true) ? 4 : 0);
        }
        return i10;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final List<CatalogDiscoveryResult> searchMdblistLists(String query) throws UnsupportedEncodingException {
        String strEncode = URLEncoder.encode(query, kotlin.text.a.f19924a.name());
        gb.j0 j0Var = new gb.j0();
        j0Var.i("https://mdblist.com/toplists/?public_list_name=" + strEncode + "&preferences=bot_test_message");
        j0Var.f15724c.h("User-Agent", BuildConfig.GITHUB_REPO);
        j0Var.d();
        gb.p0 p0VarD = this.okHttpClient.a(j0Var.b()).d();
        try {
            boolean zK = p0VarD.k();
            kotlin.collections.z zVar = kotlin.collections.z.f19728i;
            if (!zK) {
                p0VarD.close();
                return zVar;
            }
            s0 s0Var = p0VarD.f15786q;
            String strX = s0Var != null ? s0Var.x() : null;
            if (strX == null) {
                strX = "";
            }
            p0VarD.close();
            if (!kotlin.text.o.h0(strX)) {
                ga.b bVar = new ga.b(new ga.h(ga.r.Q(new ga.p(Jsoup.parse(strX, "https://mdblist.com").select("article.related-list-card"), 3), new b(this, 3))), new f(17));
                if (bVar.hasNext()) {
                    Object next = bVar.next();
                    if (!bVar.hasNext()) {
                        return Collections.singletonList(next);
                    }
                    ArrayList arrayList = new ArrayList();
                    arrayList.add(next);
                    while (bVar.hasNext()) {
                        arrayList.add(bVar.next());
                    }
                    return arrayList;
                }
            }
            return zVar;
        } finally {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:43:0x00e0  */
    /* JADX WARN: Removed duplicated region for block: B:57:0x0117  */
    /* JADX WARN: Removed duplicated region for block: B:70:0x015f  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final com.arflix.tv.data.model.CatalogDiscoveryResult searchMdblistLists$lambda$1(com.arflix.tv.data.repository.CatalogDiscoveryRepository r17, org.jsoup.nodes.Element r18) {
        /*
            Method dump skipped, instruction units count: 487
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.repository.CatalogDiscoveryRepository.searchMdblistLists$lambda$1(com.arflix.tv.data.repository.CatalogDiscoveryRepository, org.jsoup.nodes.Element):com.arflix.tv.data.model.CatalogDiscoveryResult");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean searchMdblistLists$lambda$1$10(String str) {
        return !kotlin.text.o.h0(str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final String searchMdblistLists$lambda$1$8(Element element) {
        String strAttr = element.attr("abs:src");
        return kotlin.text.o.h0(strAttr) ? element.attr("src") : strAttr;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final String searchMdblistLists$lambda$2(CatalogDiscoveryResult catalogDiscoveryResult) {
        return catalogDiscoveryResult.getSourceUrl().toLowerCase(Locale.ROOT);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0014  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object searchTraktLists(java.lang.String r12, d7.d<? super java.util.List<com.arflix.tv.data.model.CatalogDiscoveryResult>> r13) {
        /*
            r11 = this;
            boolean r0 = r13 instanceof com.arflix.tv.data.repository.CatalogDiscoveryRepository.AnonymousClass1
            if (r0 == 0) goto L14
            r0 = r13
            com.arflix.tv.data.repository.CatalogDiscoveryRepository$searchTraktLists$1 r0 = (com.arflix.tv.data.repository.CatalogDiscoveryRepository.AnonymousClass1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L14
            int r1 = r1 - r2
            r0.label = r1
        L12:
            r8 = r0
            goto L1a
        L14:
            com.arflix.tv.data.repository.CatalogDiscoveryRepository$searchTraktLists$1 r0 = new com.arflix.tv.data.repository.CatalogDiscoveryRepository$searchTraktLists$1
            r0.<init>(r13)
            goto L12
        L1a:
            java.lang.Object r13 = r8.result
            int r0 = r8.label
            r1 = 1
            if (r0 == 0) goto L33
            if (r0 != r1) goto L2b
            java.lang.Object r12 = r8.L$0
            java.lang.String r12 = (java.lang.String) r12
            k2.c.G(r13)
            goto L56
        L2b:
            java.lang.IllegalStateException r12 = new java.lang.IllegalStateException
            java.lang.String r13 = "call to 'resume' before 'invoke' with coroutine"
            r12.<init>(r13)
            throw r12
        L33:
            k2.c.G(r13)
            r13 = r1
            com.arflix.tv.data.api.TraktApi r1 = r11.traktApi
            com.arflix.tv.util.Constants r0 = com.arflix.tv.util.Constants.INSTANCE
            java.lang.String r2 = r0.getTRAKT_CLIENT_ID()
            r0 = 0
            r8.L$0 = r0
            r8.label = r13
            r3 = 0
            r5 = 0
            r6 = 40
            r7 = 0
            r9 = 42
            r10 = 0
            r4 = r12
            java.lang.Object r13 = com.arflix.tv.data.api.h.Q(r1, r2, r3, r4, r5, r6, r7, r8, r9, r10)
            e7.a r12 = e7.a.f15033i
            if (r13 != r12) goto L56
            return r12
        L56:
            java.lang.Iterable r13 = (java.lang.Iterable) r13
            ga.p r12 = new ga.p
            r0 = 3
            r12.<init>(r13, r0)
            com.arflix.tv.data.repository.b r13 = new com.arflix.tv.data.repository.b
            r0 = 0
            r13.<init>(r11, r0)
            ga.i r12 = ga.r.Q(r12, r13)
            com.arflix.tv.data.repository.f r13 = new com.arflix.tv.data.repository.f
            r0 = 13
            r13.<init>(r0)
            ga.b r0 = new ga.b
            ga.h r1 = new ga.h
            r1.<init>(r12)
            r0.<init>(r1, r13)
            boolean r12 = r0.hasNext()
            if (r12 != 0) goto L82
            kotlin.collections.z r12 = kotlin.collections.z.f19728i
            return r12
        L82:
            java.lang.Object r12 = r0.next()
            boolean r13 = r0.hasNext()
            if (r13 != 0) goto L91
            java.util.List r12 = java.util.Collections.singletonList(r12)
            return r12
        L91:
            java.util.ArrayList r13 = new java.util.ArrayList
            r13.<init>()
            r13.add(r12)
        L99:
            boolean r12 = r0.hasNext()
            if (r12 == 0) goto La7
            java.lang.Object r12 = r0.next()
            r13.add(r12)
            goto L99
        La7:
            return r13
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.repository.CatalogDiscoveryRepository.searchTraktLists(java.lang.String, d7.d):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:20:0x0035 A[PHI: r1
      0x0035: PHI (r1v7 java.lang.String) = (r1v5 java.lang.String), (r1v23 java.lang.String) binds: [B:29:0x004b, B:18:0x0032] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:21:0x0037  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final com.arflix.tv.data.model.CatalogDiscoveryResult searchTraktLists$lambda$0(com.arflix.tv.data.repository.CatalogDiscoveryRepository r14, com.arflix.tv.data.api.TraktListSearchResult r15) {
        /*
            Method dump skipped, instruction units count: 280
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.repository.CatalogDiscoveryRepository.searchTraktLists$lambda$0(com.arflix.tv.data.repository.CatalogDiscoveryRepository, com.arflix.tv.data.api.TraktListSearchResult):com.arflix.tv.data.model.CatalogDiscoveryResult");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean searchTraktLists$lambda$0$6(String str) {
        return !kotlin.text.o.h0(str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final String searchTraktLists$lambda$1(CatalogDiscoveryResult catalogDiscoveryResult) {
        return catalogDiscoveryResult.getSourceUrl().toLowerCase(Locale.ROOT);
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /* JADX INFO: renamed from: searchCatalogLists-gIAlu-s, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object m6112searchCatalogListsgIAlus(java.lang.String r5, d7.d<? super x6.d0> r6) {
        /*
            r4 = this;
            boolean r0 = r6 instanceof com.arflix.tv.data.repository.CatalogDiscoveryRepository$searchCatalogLists$1
            if (r0 == 0) goto L13
            r0 = r6
            com.arflix.tv.data.repository.CatalogDiscoveryRepository$searchCatalogLists$1 r0 = (com.arflix.tv.data.repository.CatalogDiscoveryRepository$searchCatalogLists$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            com.arflix.tv.data.repository.CatalogDiscoveryRepository$searchCatalogLists$1 r0 = new com.arflix.tv.data.repository.CatalogDiscoveryRepository$searchCatalogLists$1
            r0.<init>(r4, r6)
        L18:
            java.lang.Object r6 = r0.result
            int r1 = r0.label
            r2 = 1
            if (r1 == 0) goto L31
            if (r1 != r2) goto L29
            java.lang.Object r5 = r0.L$0
            java.lang.String r5 = (java.lang.String) r5
            k2.c.G(r6)
            goto L49
        L29:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r6)
            throw r5
        L31:
            k2.c.G(r6)
            ra.c r6 = ka.x0.f19655d
            com.arflix.tv.data.repository.CatalogDiscoveryRepository$searchCatalogLists$2 r1 = new com.arflix.tv.data.repository.CatalogDiscoveryRepository$searchCatalogLists$2
            r3 = 0
            r1.<init>(r5, r4, r3)
            r0.L$0 = r3
            r0.label = r2
            java.lang.Object r6 = ka.m0.y(r6, r1, r0)
            e7.a r5 = e7.a.f15033i
            if (r6 != r5) goto L49
            return r5
        L49:
            x6.d0 r6 = (x6.d0) r6
            java.lang.Object r5 = r6.f22580i
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.repository.CatalogDiscoveryRepository.m6112searchCatalogListsgIAlus(java.lang.String, d7.d):java.lang.Object");
    }
}
