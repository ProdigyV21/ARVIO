package com.arflix.tv.data.repository;

import com.arflix.tv.data.api.StremioMetaPreview;
import com.arflix.tv.data.model.Addon;
import com.arflix.tv.data.model.AddonCatalog;
import com.arflix.tv.data.repository.SportsRepository;
import kotlin.Metadata;
import x6.t0;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\u0010\u0003\u001a\u0010\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u0002\u0018\u00010\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"Lka/k0;", "Lx6/x;", "", "<anonymous>", "(Lka/k0;)Lx6/x;"}, k = 3, mv = {2, 3, 0})
@f7.e(c = "com.arflix.tv.data.repository.SportsRepository$loadSportsCategoryArtworkOverrides$2$1$1", f = "SportsRepository.kt", l = {306}, m = "invokeSuspend", v = 2)
public final class SportsRepository$loadSportsCategoryArtworkOverrides$2$1$1 extends f7.j implements r7.p<ka.k0, d7.d<? super x6.x>, Object> {
    final /* synthetic */ Addon $addon;
    final /* synthetic */ SportsRepository.SportsCategoryDef $sport;
    int label;
    final /* synthetic */ SportsRepository this$0;

    /* JADX INFO: renamed from: com.arflix.tv.data.repository.SportsRepository$loadSportsCategoryArtworkOverrides$2$1$1$1, reason: invalid class name */
    @Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\u0010\u0003\u001a\u0010\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u0002\u0018\u00010\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"Lka/k0;", "Lx6/x;", "", "<anonymous>", "(Lka/k0;)Lx6/x;"}, k = 3, mv = {2, 3, 0})
    @f7.e(c = "com.arflix.tv.data.repository.SportsRepository$loadSportsCategoryArtworkOverrides$2$1$1$1", f = "SportsRepository.kt", l = {308}, m = "invokeSuspend", v = 2)
    public static final class AnonymousClass1 extends f7.j implements r7.p<ka.k0, d7.d<? super x6.x>, Object> {
        final /* synthetic */ Addon $addon;
        final /* synthetic */ SportsRepository.SportsCategoryDef $sport;
        Object L$0;
        int label;
        final /* synthetic */ SportsRepository this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(SportsRepository sportsRepository, Addon addon, SportsRepository.SportsCategoryDef sportsCategoryDef, d7.d<? super AnonymousClass1> dVar) {
            super(2, dVar);
            this.this$0 = sportsRepository;
            this.$addon = addon;
            this.$sport = sportsCategoryDef;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final String invokeSuspend$lambda$0(StremioMetaPreview stremioMetaPreview) {
            String background = stremioMetaPreview.getBackground();
            if (background != null) {
                if (kotlin.text.o.h0(background)) {
                    background = null;
                }
                if (background != null) {
                    return background;
                }
            }
            String poster = stremioMetaPreview.getPoster();
            if (poster == null || kotlin.text.o.h0(poster)) {
                return null;
            }
            return poster;
        }

        @Override // f7.a
        public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
            return new AnonymousClass1(this.this$0, this.$addon, this.$sport, dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) throws Exception {
            int i10 = this.label;
            if (i10 == 0) {
                k2.c.G(obj);
                AddonCatalog addonCatalog = (AddonCatalog) kotlin.collections.x.o0(this.this$0.candidateCatalogs(this.$addon, this.$sport.getId()));
                if (addonCatalog != null) {
                    SportsRepository sportsRepository = this.this$0;
                    Addon addon = this.$addon;
                    this.L$0 = null;
                    this.label = 1;
                    obj = sportsRepository.loadCatalogMetas(addon, addonCatalog, this);
                    e7.a aVar = e7.a.f15033i;
                    if (obj == aVar) {
                        return aVar;
                    }
                }
                return null;
            }
            if (i10 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            k2.c.G(obj);
            String str = (String) ga.r.K(ga.r.Q(new ga.p((Iterable) obj, 3), new f(8)));
            if (str != null) {
                return new x6.x(this.$sport.getId(), str);
            }
            return null;
        }

        @Override // r7.p
        public final Object invoke(ka.k0 k0Var, d7.d<? super x6.x> dVar) {
            return ((AnonymousClass1) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SportsRepository$loadSportsCategoryArtworkOverrides$2$1$1(SportsRepository sportsRepository, Addon addon, SportsRepository.SportsCategoryDef sportsCategoryDef, d7.d<? super SportsRepository$loadSportsCategoryArtworkOverrides$2$1$1> dVar) {
        super(2, dVar);
        this.this$0 = sportsRepository;
        this.$addon = addon;
        this.$sport = sportsCategoryDef;
    }

    @Override // f7.a
    public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
        return new SportsRepository$loadSportsCategoryArtworkOverrides$2$1$1(this.this$0, this.$addon, this.$sport, dVar);
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) throws Throwable {
        int i10 = this.label;
        if (i10 != 0) {
            if (i10 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            k2.c.G(obj);
            return obj;
        }
        k2.c.G(obj);
        AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.this$0, this.$addon, this.$sport, null);
        this.label = 1;
        Object objA = ka.m0.A(1500L, anonymousClass1, this);
        e7.a aVar = e7.a.f15033i;
        return objA == aVar ? aVar : objA;
    }

    @Override // r7.p
    public final Object invoke(ka.k0 k0Var, d7.d<? super x6.x> dVar) {
        return ((SportsRepository$loadSportsCategoryArtworkOverrides$2$1$1) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
    }
}
