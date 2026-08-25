package com.arflix.tv.data.repository;

import kotlin.Metadata;
import x6.t0;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0003\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"Lka/k0;", "Lx6/d0;", "Lcom/arflix/tv/data/repository/HomeServerConnection;", "<anonymous>", "(Lka/k0;)Lx6/d0;"}, k = 3, mv = {2, 3, 0})
@f7.e(c = "com.arflix.tv.data.repository.HomeServerRepository$pollHomeServerCodeAuth$2", f = "HomeServerRepository.kt", l = {532, 536, 543}, m = "invokeSuspend", v = 2)
public final class HomeServerRepository$pollHomeServerCodeAuth$2 extends f7.j implements r7.p<ka.k0, d7.d<? super x6.d0>, Object> {
    final /* synthetic */ String $displayName;
    final /* synthetic */ String $preferredServerUrl;
    final /* synthetic */ PlexPinAuthSession $session;
    int I$0;
    private /* synthetic */ Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    Object L$4;
    Object L$5;
    int label;
    final /* synthetic */ HomeServerRepository this$0;

    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[HomeServerKind.values().length];
            try {
                iArr[HomeServerKind.PLEX.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[HomeServerKind.JELLYFIN.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public HomeServerRepository$pollHomeServerCodeAuth$2(PlexPinAuthSession plexPinAuthSession, HomeServerRepository homeServerRepository, String str, String str2, d7.d<? super HomeServerRepository$pollHomeServerCodeAuth$2> dVar) {
        super(2, dVar);
        this.$session = plexPinAuthSession;
        this.this$0 = homeServerRepository;
        this.$preferredServerUrl = str;
        this.$displayName = str2;
    }

    @Override // f7.a
    public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
        HomeServerRepository$pollHomeServerCodeAuth$2 homeServerRepository$pollHomeServerCodeAuth$2 = new HomeServerRepository$pollHomeServerCodeAuth$2(this.$session, this.this$0, this.$preferredServerUrl, this.$displayName, dVar);
        homeServerRepository$pollHomeServerCodeAuth$2.L$0 = obj;
        return homeServerRepository$pollHomeServerCodeAuth$2;
    }

    /* JADX WARN: Code restructure failed: missing block: B:43:0x00e5, code lost:
    
        if (r11 != r5) goto L45;
     */
    /* JADX WARN: Removed duplicated region for block: B:41:0x00cd A[Catch: all -> 0x001c, TryCatch #0 {all -> 0x001c, blocks: (B:8:0x0018, B:27:0x0081, B:15:0x002f, B:45:0x00e8, B:18:0x004e, B:34:0x00b9, B:36:0x00c0, B:39:0x00c7, B:41:0x00cd, B:42:0x00d1, B:21:0x0061, B:24:0x0072, B:28:0x0086, B:29:0x0099, B:30:0x009a), top: B:50:0x000c }] */
    @Override // f7.a
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r11) {
        /*
            Method dump skipped, instruction units count: 250
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.repository.HomeServerRepository$pollHomeServerCodeAuth$2.invokeSuspend(java.lang.Object):java.lang.Object");
    }

    @Override // r7.p
    public final Object invoke(ka.k0 k0Var, d7.d<? super x6.d0> dVar) {
        return ((HomeServerRepository$pollHomeServerCodeAuth$2) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
    }
}
