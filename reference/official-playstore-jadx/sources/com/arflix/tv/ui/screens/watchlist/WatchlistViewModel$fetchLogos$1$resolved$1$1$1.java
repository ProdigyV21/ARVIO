package com.arflix.tv.ui.screens.watchlist;

import com.arflix.tv.data.model.MediaItem;
import com.google.android.gms.cast.MediaError;
import ka.k0;
import kotlin.Metadata;
import r7.p;
import x6.t0;
import x6.x;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\u0010\u0003\u001a\u0010\u0012\u0004\u0012\u00020\u0002\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"Lka/k0;", "Lx6/x;", "", "<anonymous>", "(Lka/k0;)Lx6/x;"}, k = 3, mv = {2, 3, 0})
@f7.e(c = "com.arflix.tv.ui.screens.watchlist.WatchlistViewModel$fetchLogos$1$resolved$1$1$1", f = "WatchlistViewModel.kt", l = {1292, MediaError.DetailedErrorCode.BREAK_SEEK_INTERCEPTOR_ERROR}, m = "invokeSuspend", v = 2)
public final class WatchlistViewModel$fetchLogos$1$resolved$1$1$1 extends f7.j implements p<k0, d7.d<? super x>, Object> {
    final /* synthetic */ MediaItem $item;
    final /* synthetic */ ua.f $limiter;
    int I$0;
    int I$1;
    int I$2;
    private /* synthetic */ Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    int label;
    final /* synthetic */ WatchlistViewModel this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public WatchlistViewModel$fetchLogos$1$resolved$1$1$1(ua.f fVar, MediaItem mediaItem, WatchlistViewModel watchlistViewModel, d7.d<? super WatchlistViewModel$fetchLogos$1$resolved$1$1$1> dVar) {
        super(2, dVar);
        this.$limiter = fVar;
        this.$item = mediaItem;
        this.this$0 = watchlistViewModel;
    }

    @Override // f7.a
    public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
        WatchlistViewModel$fetchLogos$1$resolved$1$1$1 watchlistViewModel$fetchLogos$1$resolved$1$1$1 = new WatchlistViewModel$fetchLogos$1$resolved$1$1$1(this.$limiter, this.$item, this.this$0, dVar);
        watchlistViewModel$fetchLogos$1$resolved$1$1$1.L$0 = obj;
        return watchlistViewModel$fetchLogos$1$resolved$1$1$1;
    }

    /* JADX WARN: Can't wrap try/catch for region: R(10:0|2|(1:(1:(9:6|43|7|23|28|(1:30)(1:31)|32|33|34)(2:11|12))(1:13))(3:14|(1:17)|21)|41|18|45|19|(7:22|23|28|(0)(0)|32|33|34)|21|(1:(0))) */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x0084, code lost:
    
        r0 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x0085, code lost:
    
        r1 = r9;
        r9 = r0;
        r0 = r7;
     */
    /* JADX WARN: Removed duplicated region for block: B:30:0x0093  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x0094  */
    @Override // f7.a
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r9) throws java.lang.Throwable {
        /*
            r8 = this;
            java.lang.Object r0 = r8.L$0
            ka.k0 r0 = (ka.k0) r0
            int r1 = r8.label
            r2 = 2
            r3 = 1
            r4 = 0
            r5 = 0
            e7.a r6 = e7.a.f15033i
            if (r1 == 0) goto L40
            if (r1 == r3) goto L2d
            if (r1 != r2) goto L25
            java.lang.Object r0 = r8.L$3
            ka.k0 r0 = (ka.k0) r0
            java.lang.Object r0 = r8.L$2
            java.lang.String r0 = (java.lang.String) r0
            java.lang.Object r1 = r8.L$1
            ua.f r1 = (ua.f) r1
            k2.c.G(r9)     // Catch: java.lang.Throwable -> L22
            goto L81
        L22:
            r9 = move-exception
            goto L88
        L25:
            java.lang.IllegalStateException r9 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r9.<init>(r0)
            throw r9
        L2d:
            int r0 = r8.I$0
            java.lang.Object r1 = r8.L$3
            com.arflix.tv.ui.screens.watchlist.WatchlistViewModel r1 = (com.arflix.tv.ui.screens.watchlist.WatchlistViewModel) r1
            java.lang.Object r3 = r8.L$2
            com.arflix.tv.data.model.MediaItem r3 = (com.arflix.tv.data.model.MediaItem) r3
            java.lang.Object r7 = r8.L$1
            ua.f r7 = (ua.f) r7
            k2.c.G(r9)
            r9 = r7
            goto L5f
        L40:
            k2.c.G(r9)
            ua.f r9 = r8.$limiter
            com.arflix.tv.data.model.MediaItem r1 = r8.$item
            com.arflix.tv.ui.screens.watchlist.WatchlistViewModel r7 = r8.this$0
            r8.L$0 = r0
            r8.L$1 = r9
            r8.L$2 = r1
            r8.L$3 = r7
            r8.I$0 = r5
            r8.label = r3
            java.lang.Object r0 = r9.a(r8)
            if (r0 != r6) goto L5c
            goto L7d
        L5c:
            r3 = r1
            r0 = r5
            r1 = r7
        L5f:
            java.lang.String r7 = com.arflix.tv.ui.screens.watchlist.WatchlistViewModelKt.watchlistLogoKey(r3)     // Catch: java.lang.Throwable -> La0
            com.arflix.tv.data.repository.MediaRepository r1 = com.arflix.tv.ui.screens.watchlist.WatchlistViewModel.access$getMediaRepository$p(r1)     // Catch: java.lang.Throwable -> L84
            r8.L$0 = r4     // Catch: java.lang.Throwable -> L84
            r8.L$1 = r9     // Catch: java.lang.Throwable -> L84
            r8.L$2 = r7     // Catch: java.lang.Throwable -> L84
            r8.L$3 = r4     // Catch: java.lang.Throwable -> L84
            r8.I$0 = r0     // Catch: java.lang.Throwable -> L84
            r8.I$1 = r5     // Catch: java.lang.Throwable -> L84
            r8.I$2 = r5     // Catch: java.lang.Throwable -> L84
            r8.label = r2     // Catch: java.lang.Throwable -> L84
            java.lang.Object r0 = r1.getLogoUrl(r3, r8)     // Catch: java.lang.Throwable -> L84
            if (r0 != r6) goto L7e
        L7d:
            return r6
        L7e:
            r1 = r9
            r9 = r0
            r0 = r7
        L81:
            java.lang.String r9 = (java.lang.String) r9     // Catch: java.lang.Throwable -> L22
            goto L8e
        L84:
            r0 = move-exception
            r1 = r9
            r9 = r0
            r0 = r7
        L88:
            x6.c0 r2 = new x6.c0     // Catch: java.lang.Throwable -> L9e
            r2.<init>(r9)     // Catch: java.lang.Throwable -> L9e
            r9 = r2
        L8e:
            boolean r2 = r9 instanceof x6.c0     // Catch: java.lang.Throwable -> L9e
            if (r2 == 0) goto L94
            goto L95
        L94:
            r4 = r9
        L95:
            x6.x r9 = new x6.x     // Catch: java.lang.Throwable -> L9e
            r9.<init>(r0, r4)     // Catch: java.lang.Throwable -> L9e
            r1.release()
            return r9
        L9e:
            r9 = move-exception
            goto La3
        La0:
            r0 = move-exception
            r1 = r9
            r9 = r0
        La3:
            r1.release()
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.screens.watchlist.WatchlistViewModel$fetchLogos$1$resolved$1$1$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }

    @Override // r7.p
    public final Object invoke(k0 k0Var, d7.d<? super x> dVar) {
        return ((WatchlistViewModel$fetchLogos$1$resolved$1$1$1) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
    }
}
