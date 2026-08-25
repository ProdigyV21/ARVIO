package com.arflix.tv.ui.screens.watchlist;

import com.arflix.tv.data.model.MediaItem;
import com.arflix.tv.data.model.MediaType;
import ka.k0;
import kotlin.Metadata;
import r7.p;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lcom/arflix/tv/data/model/MediaItem;", "<anonymous>", "(Lka/k0;)Lcom/arflix/tv/data/model/MediaItem;"}, k = 3, mv = {2, 3, 0})
@f7.e(c = "com.arflix.tv.ui.screens.watchlist.WatchlistViewModel$hydrateTrackerItems$2$1$1", f = "WatchlistViewModel.kt", l = {1292, 786, 787}, m = "invokeSuspend", v = 2)
public final class WatchlistViewModel$hydrateTrackerItems$2$1$1 extends f7.j implements p<k0, d7.d<? super MediaItem>, Object> {
    final /* synthetic */ int $index;
    final /* synthetic */ MediaItem $item;
    final /* synthetic */ ua.f $limiter;
    int I$0;
    int I$1;
    int I$2;
    int I$3;
    private /* synthetic */ Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    int label;
    final /* synthetic */ WatchlistViewModel this$0;

    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[MediaType.values().length];
            try {
                iArr[MediaType.MOVIE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[MediaType.TV.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public WatchlistViewModel$hydrateTrackerItems$2$1$1(ua.f fVar, MediaItem mediaItem, WatchlistViewModel watchlistViewModel, int i10, d7.d<? super WatchlistViewModel$hydrateTrackerItems$2$1$1> dVar) {
        super(2, dVar);
        this.$limiter = fVar;
        this.$item = mediaItem;
        this.this$0 = watchlistViewModel;
        this.$index = i10;
    }

    @Override // f7.a
    public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
        WatchlistViewModel$hydrateTrackerItems$2$1$1 watchlistViewModel$hydrateTrackerItems$2$1$1 = new WatchlistViewModel$hydrateTrackerItems$2$1$1(this.$limiter, this.$item, this.this$0, this.$index, dVar);
        watchlistViewModel$hydrateTrackerItems$2$1$1.L$0 = obj;
        return watchlistViewModel$hydrateTrackerItems$2$1$1;
    }

    /* JADX WARN: Can't wrap try/catch for region: R(9:0|2|(1:68)|(1:(1:(1:(16:7|8|9|29|30|41|61|42|43|44|64|49|(1:51)(1:52)|53|54|55)(2:12|13))(16:14|15|16|39|30|41|61|42|43|44|64|49|(0)(0)|53|54|55))(1:17))(3:18|(1:21)|37)|63|22|(1:(2:25|(14:28|29|30|41|61|42|43|44|64|49|(0)(0)|53|54|55))(2:33|34))(2:35|(14:38|39|30|41|61|42|43|44|64|49|(0)(0)|53|54|55))|37|(1:(0))) */
    /* JADX WARN: Code restructure failed: missing block: B:31:0x00c7, code lost:
    
        r0 = th;
     */
    /* JADX WARN: Code restructure failed: missing block: B:32:0x00c8, code lost:
    
        r3 = r8;
        r4 = r10;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:51:0x0162  */
    /* JADX WARN: Removed duplicated region for block: B:52:0x0163 A[Catch: all -> 0x01b8, TryCatch #2 {all -> 0x01b8, blocks: (B:49:0x015c, B:53:0x01b2, B:52:0x0163), top: B:64:0x015c }] */
    /* JADX WARN: Type inference failed for: r2v2, types: [com.arflix.tv.data.model.MediaItem] */
    /* JADX WARN: Type inference failed for: r3v0 */
    /* JADX WARN: Type inference failed for: r3v1 */
    /* JADX WARN: Type inference failed for: r3v10 */
    /* JADX WARN: Type inference failed for: r3v17 */
    /* JADX WARN: Type inference failed for: r3v18 */
    /* JADX WARN: Type inference failed for: r3v19 */
    /* JADX WARN: Type inference failed for: r3v2 */
    /* JADX WARN: Type inference failed for: r3v20 */
    /* JADX WARN: Type inference failed for: r3v5 */
    @Override // f7.a
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r54) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 449
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.screens.watchlist.WatchlistViewModel$hydrateTrackerItems$2$1$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }

    @Override // r7.p
    public final Object invoke(k0 k0Var, d7.d<? super MediaItem> dVar) {
        return ((WatchlistViewModel$hydrateTrackerItems$2$1$1) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
    }
}
