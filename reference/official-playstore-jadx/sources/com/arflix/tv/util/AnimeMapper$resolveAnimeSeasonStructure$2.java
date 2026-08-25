package com.arflix.tv.util;

import com.google.android.gms.cast.MediaError;
import d7.d;
import f7.e;
import f7.j;
import ka.k0;
import kotlin.Metadata;
import r7.p;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lcom/arflix/tv/util/AnimeSeasonStructure;", "<anonymous>", "(Lka/k0;)Lcom/arflix/tv/util/AnimeSeasonStructure;"}, k = 3, mv = {2, 3, 0})
@e(c = "com.arflix.tv.util.AnimeMapper$resolveAnimeSeasonStructure$2", f = "AnimeMapper.kt", l = {1142, MediaError.DetailedErrorCode.DASH_MANIFEST_NO_PERIODS, 426, 432}, m = "invokeSuspend", v = 2)
public final class AnimeMapper$resolveAnimeSeasonStructure$2 extends j implements p<k0, d<? super AnimeSeasonStructure>, Object> {
    final /* synthetic */ int $tmdbId;
    int I$0;
    int I$1;
    int I$2;
    int I$3;
    int I$4;
    int I$5;
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    Object L$4;
    Object L$5;
    Object L$6;
    Object L$7;
    Object L$8;
    Object L$9;
    int label;
    final /* synthetic */ AnimeMapper this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AnimeMapper$resolveAnimeSeasonStructure$2(AnimeMapper animeMapper, int i10, d<? super AnimeMapper$resolveAnimeSeasonStructure$2> dVar) {
        super(2, dVar);
        this.this$0 = animeMapper;
        this.$tmdbId = i10;
    }

    @Override // f7.a
    public final d<t0> create(Object obj, d<?> dVar) {
        return new AnimeMapper$resolveAnimeSeasonStructure$2(this.this$0, this.$tmdbId, dVar);
    }

    /* JADX WARN: Code restructure failed: missing block: B:59:0x01a1, code lost:
    
        if (r0 == r8) goto L60;
     */
    /* JADX WARN: Code restructure failed: missing block: B:86:0x0123, code lost:
    
        r2 = null;
     */
    /* JADX WARN: Removed duplicated region for block: B:33:0x00bc  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x00d5 A[Catch: Exception -> 0x0214, TryCatch #0 {Exception -> 0x0214, blocks: (B:9:0x001e, B:61:0x01a4, B:62:0x01b3, B:64:0x01b9, B:66:0x01c6, B:68:0x01cc, B:69:0x01d0, B:72:0x01df, B:73:0x01e8, B:75:0x01ee, B:76:0x020a, B:14:0x0056, B:42:0x0110, B:44:0x0114, B:47:0x0125, B:35:0x00cf, B:37:0x00d5, B:39:0x00e2, B:49:0x012a, B:50:0x013a, B:52:0x0140, B:54:0x0168, B:55:0x016c, B:58:0x0174, B:15:0x005d, B:31:0x00b8, B:34:0x00be, B:18:0x006d, B:26:0x00a1, B:28:0x00a6, B:79:0x0210, B:80:0x0213, B:21:0x0074, B:25:0x0092), top: B:83:0x000c, inners: #1 }] */
    /* JADX WARN: Removed duplicated region for block: B:44:0x0114 A[Catch: Exception -> 0x0214, TryCatch #0 {Exception -> 0x0214, blocks: (B:9:0x001e, B:61:0x01a4, B:62:0x01b3, B:64:0x01b9, B:66:0x01c6, B:68:0x01cc, B:69:0x01d0, B:72:0x01df, B:73:0x01e8, B:75:0x01ee, B:76:0x020a, B:14:0x0056, B:42:0x0110, B:44:0x0114, B:47:0x0125, B:35:0x00cf, B:37:0x00d5, B:39:0x00e2, B:49:0x012a, B:50:0x013a, B:52:0x0140, B:54:0x0168, B:55:0x016c, B:58:0x0174, B:15:0x005d, B:31:0x00b8, B:34:0x00be, B:18:0x006d, B:26:0x00a1, B:28:0x00a6, B:79:0x0210, B:80:0x0213, B:21:0x0074, B:25:0x0092), top: B:83:0x000c, inners: #1 }] */
    /* JADX WARN: Removed duplicated region for block: B:45:0x0122  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x0125 A[Catch: Exception -> 0x0214, TryCatch #0 {Exception -> 0x0214, blocks: (B:9:0x001e, B:61:0x01a4, B:62:0x01b3, B:64:0x01b9, B:66:0x01c6, B:68:0x01cc, B:69:0x01d0, B:72:0x01df, B:73:0x01e8, B:75:0x01ee, B:76:0x020a, B:14:0x0056, B:42:0x0110, B:44:0x0114, B:47:0x0125, B:35:0x00cf, B:37:0x00d5, B:39:0x00e2, B:49:0x012a, B:50:0x013a, B:52:0x0140, B:54:0x0168, B:55:0x016c, B:58:0x0174, B:15:0x005d, B:31:0x00b8, B:34:0x00be, B:18:0x006d, B:26:0x00a1, B:28:0x00a6, B:79:0x0210, B:80:0x0213, B:21:0x0074, B:25:0x0092), top: B:83:0x000c, inners: #1 }] */
    /* JADX WARN: Removed duplicated region for block: B:49:0x012a A[Catch: Exception -> 0x0214, TryCatch #0 {Exception -> 0x0214, blocks: (B:9:0x001e, B:61:0x01a4, B:62:0x01b3, B:64:0x01b9, B:66:0x01c6, B:68:0x01cc, B:69:0x01d0, B:72:0x01df, B:73:0x01e8, B:75:0x01ee, B:76:0x020a, B:14:0x0056, B:42:0x0110, B:44:0x0114, B:47:0x0125, B:35:0x00cf, B:37:0x00d5, B:39:0x00e2, B:49:0x012a, B:50:0x013a, B:52:0x0140, B:54:0x0168, B:55:0x016c, B:58:0x0174, B:15:0x005d, B:31:0x00b8, B:34:0x00be, B:18:0x006d, B:26:0x00a1, B:28:0x00a6, B:79:0x0210, B:80:0x0213, B:21:0x0074, B:25:0x0092), top: B:83:0x000c, inners: #1 }] */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:40:0x010c -> B:42:0x0110). Please report as a decompilation issue!!! */
    @Override // f7.a
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r17) {
        /*
            Method dump skipped, instruction units count: 533
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.util.AnimeMapper$resolveAnimeSeasonStructure$2.invokeSuspend(java.lang.Object):java.lang.Object");
    }

    @Override // r7.p
    public final Object invoke(k0 k0Var, d<? super AnimeSeasonStructure> dVar) {
        return ((AnimeMapper$resolveAnimeSeasonStructure$2) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
    }
}
