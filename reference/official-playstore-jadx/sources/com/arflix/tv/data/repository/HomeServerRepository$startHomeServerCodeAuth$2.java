package com.arflix.tv.data.repository;

import com.arflix.tv.data.repository.HomeServerRepository;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import x6.t0;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"Lka/k0;", "Lx6/d0;", "Lcom/arflix/tv/data/repository/PlexPinAuthSession;", "<anonymous>", "(Lka/k0;)Lx6/d0;"}, k = 3, mv = {2, 3, 0})
@f7.e(c = "com.arflix.tv.data.repository.HomeServerRepository$startHomeServerCodeAuth$2", f = "HomeServerRepository.kt", l = {}, m = "invokeSuspend", v = 2)
public final class HomeServerRepository$startHomeServerCodeAuth$2 extends f7.j implements r7.p<ka.k0, d7.d<? super x6.d0>, Object> {
    final /* synthetic */ String $serverUrl;
    private /* synthetic */ Object L$0;
    int label;
    final /* synthetic */ HomeServerRepository this$0;

    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[HomeServerKind.values().length];
            try {
                iArr[HomeServerKind.JELLYFIN.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[HomeServerKind.PLEX.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[HomeServerKind.EMBY.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                iArr[HomeServerKind.UNKNOWN.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public HomeServerRepository$startHomeServerCodeAuth$2(HomeServerRepository homeServerRepository, String str, d7.d<? super HomeServerRepository$startHomeServerCodeAuth$2> dVar) {
        super(2, dVar);
        this.this$0 = homeServerRepository;
        this.$serverUrl = str;
    }

    @Override // f7.a
    public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
        HomeServerRepository$startHomeServerCodeAuth$2 homeServerRepository$startHomeServerCodeAuth$2 = new HomeServerRepository$startHomeServerCodeAuth$2(this.this$0, this.$serverUrl, dVar);
        homeServerRepository$startHomeServerCodeAuth$2.L$0 = obj;
        return homeServerRepository$startHomeServerCodeAuth$2;
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
        Object c0Var;
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        k2.c.G(obj);
        HomeServerRepository homeServerRepository = this.this$0;
        try {
            String strNormalizeServerUrl = homeServerRepository.normalizeServerUrl(this.$serverUrl);
            if (kotlin.text.o.h0(strNormalizeServerUrl)) {
                c0Var = homeServerRepository.startPlexPinAuthInternal();
            } else {
                HomeServerRepository.ServerInfo serverInfoFetchPublicInfo = homeServerRepository.fetchPublicInfo(strNormalizeServerUrl);
                HomeServerKind serverKind = serverInfoFetchPublicInfo.getServerKind();
                if (serverKind == HomeServerKind.UNKNOWN) {
                    serverKind = null;
                }
                if (serverKind == null) {
                    serverKind = homeServerRepository.detectServerKind(serverInfoFetchPublicInfo.getProductName(), serverInfoFetchPublicInfo.getServerName());
                }
                int i10 = WhenMappings.$EnumSwitchMapping$0[serverKind.ordinal()];
                if (i10 == 1) {
                    c0Var = homeServerRepository.startJellyfinQuickConnect(strNormalizeServerUrl);
                } else {
                    if (i10 != 2) {
                        if (i10 == 3) {
                            throw new IllegalStateException("Code sign in is not supported by Emby. Use username and password.");
                        }
                        if (i10 != 4) {
                            throw new NoWhenBranchMatchedException();
                        }
                        throw new IllegalStateException("Could not detect this server. Use username and password, or leave URL empty for Plex.");
                    }
                    c0Var = PlexPinAuthSession.copy$default(homeServerRepository.startPlexPinAuthInternal(), null, null, null, null, 0, 0, null, strNormalizeServerUrl, 127, null);
                }
            }
        } catch (Throwable th) {
            c0Var = new x6.c0(th);
        }
        return new x6.d0(c0Var);
    }

    @Override // r7.p
    public final Object invoke(ka.k0 k0Var, d7.d<? super x6.d0> dVar) {
        return ((HomeServerRepository$startHomeServerCodeAuth$2) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
    }
}
