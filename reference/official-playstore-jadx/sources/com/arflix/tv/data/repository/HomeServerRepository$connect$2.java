package com.arflix.tv.data.repository;

import androidx.media3.common.PlaybackException;
import com.arflix.tv.R;
import com.arflix.tv.data.repository.HomeServerRepository;
import kotlin.Metadata;
import x6.t0;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"Lka/k0;", "Lx6/d0;", "Lcom/arflix/tv/data/repository/HomeServerConnection;", "<anonymous>", "(Lka/k0;)Lx6/d0;"}, k = 3, mv = {2, 3, 0})
@f7.e(c = "com.arflix.tv.data.repository.HomeServerRepository$connect$2", f = "HomeServerRepository.kt", l = {356, 377}, m = "invokeSuspend", v = 2)
public final class HomeServerRepository$connect$2 extends f7.j implements r7.p<ka.k0, d7.d<? super x6.d0>, Object> {
    final /* synthetic */ String $displayName;
    final /* synthetic */ String $password;
    final /* synthetic */ String $rawUrl;
    final /* synthetic */ String $username;
    int I$0;
    private /* synthetic */ Object L$0;
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
    final /* synthetic */ HomeServerRepository this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public HomeServerRepository$connect$2(HomeServerRepository homeServerRepository, String str, String str2, String str3, String str4, d7.d<? super HomeServerRepository$connect$2> dVar) {
        super(2, dVar);
        this.this$0 = homeServerRepository;
        this.$rawUrl = str;
        this.$username = str2;
        this.$displayName = str3;
        this.$password = str4;
    }

    @Override // f7.a
    public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
        HomeServerRepository$connect$2 homeServerRepository$connect$2 = new HomeServerRepository$connect$2(this.this$0, this.$rawUrl, this.$username, this.$displayName, this.$password, dVar);
        homeServerRepository$connect$2.L$0 = obj;
        return homeServerRepository$connect$2;
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
        Object c0Var;
        HomeServerConnection homeServerConnection;
        int i10 = this.label;
        try {
            if (i10 != 0) {
                if (i10 == 1) {
                    homeServerConnection = (HomeServerConnection) this.L$7;
                } else {
                    if (i10 != 2) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    HomeServerConnection homeServerConnection2 = (HomeServerConnection) this.L$8;
                    homeServerConnection = homeServerConnection2;
                }
                k2.c.G(obj);
                c0Var = homeServerConnection;
            } else {
                k2.c.G(obj);
                HomeServerRepository homeServerRepository = this.this$0;
                String str = this.$rawUrl;
                String str2 = this.$username;
                String str3 = this.$displayName;
                String str4 = this.$password;
                String strNormalizeServerUrl = homeServerRepository.normalizeServerUrl(str);
                String string = kotlin.text.o.L0(str2).toString();
                String string2 = kotlin.text.o.L0(str3).toString();
                if (kotlin.text.o.h0(strNormalizeServerUrl)) {
                    throw new IllegalArgumentException(homeServerRepository.context.getString(R.string.homeserver_enter_url).toString());
                }
                if (kotlin.text.o.h0(str4)) {
                    throw new IllegalArgumentException(homeServerRepository.context.getString(R.string.homeserver_enter_password).toString());
                }
                HomeServerRepository.ServerInfo serverInfoFetchPublicInfo = homeServerRepository.fetchPublicInfo(strNormalizeServerUrl);
                HomeServerKind serverKind = serverInfoFetchPublicInfo.getServerKind();
                if (serverKind == HomeServerKind.UNKNOWN) {
                    serverKind = null;
                }
                if (serverKind == null) {
                    serverKind = homeServerRepository.detectServerKind(serverInfoFetchPublicInfo.getProductName(), serverInfoFetchPublicInfo.getServerName());
                }
                HomeServerKind homeServerKind = serverKind;
                HomeServerKind homeServerKind2 = HomeServerKind.PLEX;
                e7.a aVar = e7.a.f15033i;
                if (homeServerKind == homeServerKind2) {
                    HomeServerConnection homeServerConnectionBuildPlexConnection = homeServerRepository.buildPlexConnection(str4, strNormalizeServerUrl, string, serverInfoFetchPublicInfo, string2);
                    this.L$0 = null;
                    this.L$1 = null;
                    this.L$2 = null;
                    this.L$3 = null;
                    this.L$4 = null;
                    this.L$5 = null;
                    this.L$6 = null;
                    this.L$7 = homeServerConnectionBuildPlexConnection;
                    this.I$0 = 0;
                    this.label = 1;
                    Object objSaveConnection = homeServerRepository.saveConnection(homeServerConnectionBuildPlexConnection, this);
                    c0Var = homeServerConnectionBuildPlexConnection;
                    if (objSaveConnection == aVar) {
                        return aVar;
                    }
                } else {
                    if (kotlin.text.o.h0(string)) {
                        throw new IllegalArgumentException(homeServerRepository.context.getString(R.string.homeserver_enter_username).toString());
                    }
                    HomeServerRepository.AuthResponse authResponseAuthenticate = homeServerRepository.authenticate(strNormalizeServerUrl, string, str4);
                    String userId = authResponseAuthenticate.getUserId();
                    if (kotlin.text.o.h0(userId)) {
                        userId = string;
                    }
                    String strCreateConnectionId = homeServerRepository.createConnectionId(strNormalizeServerUrl, homeServerKind, userId);
                    String serverName = serverInfoFetchPublicInfo.getServerName();
                    if (kotlin.text.o.h0(serverName)) {
                        serverName = authResponseAuthenticate.getServerName();
                    }
                    if (kotlin.text.o.h0(serverName)) {
                        serverName = HomeServerRepository.ADDON_NAME;
                    }
                    String serverId = authResponseAuthenticate.getServerId();
                    if (kotlin.text.o.h0(serverId)) {
                        serverId = serverInfoFetchPublicInfo.getServerId();
                    }
                    String str5 = serverId;
                    String userId2 = authResponseAuthenticate.getUserId();
                    String userName = authResponseAuthenticate.getUserName();
                    HomeServerConnection homeServerConnection3 = new HomeServerConnection(true, strCreateConnectionId, strNormalizeServerUrl, string2, serverName, homeServerKind, str5, userId2, kotlin.text.o.h0(userName) ? string : userName, authResponseAuthenticate.getAccessToken(), authResponseAuthenticate.getAccountToken(), null, System.currentTimeMillis(), 2048, null);
                    HomeServerConnection homeServerConnectionCopy = homeServerConnection3.copy((PlaybackException.ERROR_CODE_DECODING_FORMAT_UNSUPPORTED & 1) != 0 ? homeServerConnection3.enabled : false, (PlaybackException.ERROR_CODE_DECODING_FORMAT_UNSUPPORTED & 2) != 0 ? homeServerConnection3.connectionId : null, (PlaybackException.ERROR_CODE_DECODING_FORMAT_UNSUPPORTED & 4) != 0 ? homeServerConnection3.serverUrl : null, (PlaybackException.ERROR_CODE_DECODING_FORMAT_UNSUPPORTED & 8) != 0 ? homeServerConnection3.displayName : null, (PlaybackException.ERROR_CODE_DECODING_FORMAT_UNSUPPORTED & 16) != 0 ? homeServerConnection3.serverName : null, (PlaybackException.ERROR_CODE_DECODING_FORMAT_UNSUPPORTED & 32) != 0 ? homeServerConnection3.serverKind : null, (PlaybackException.ERROR_CODE_DECODING_FORMAT_UNSUPPORTED & 64) != 0 ? homeServerConnection3.serverId : null, (PlaybackException.ERROR_CODE_DECODING_FORMAT_UNSUPPORTED & 128) != 0 ? homeServerConnection3.userId : null, (PlaybackException.ERROR_CODE_DECODING_FORMAT_UNSUPPORTED & 256) != 0 ? homeServerConnection3.userName : null, (PlaybackException.ERROR_CODE_DECODING_FORMAT_UNSUPPORTED & 512) != 0 ? homeServerConnection3.accessToken : null, (PlaybackException.ERROR_CODE_DECODING_FORMAT_UNSUPPORTED & 1024) != 0 ? homeServerConnection3.accountToken : null, (PlaybackException.ERROR_CODE_DECODING_FORMAT_UNSUPPORTED & 2048) != 0 ? homeServerConnection3.collections : homeServerRepository.fetchCollections(homeServerConnection3), (PlaybackException.ERROR_CODE_DECODING_FORMAT_UNSUPPORTED & 4096) != 0 ? homeServerConnection3.lastConnectedAt : 0L);
                    this.L$0 = null;
                    this.L$1 = null;
                    this.L$2 = null;
                    this.L$3 = null;
                    this.L$4 = null;
                    this.L$5 = null;
                    this.L$6 = null;
                    this.L$7 = null;
                    this.L$8 = homeServerConnectionCopy;
                    this.L$9 = null;
                    this.I$0 = 0;
                    this.label = 2;
                    if (homeServerRepository.saveConnection(homeServerConnectionCopy, this) == aVar) {
                        return aVar;
                    }
                    c0Var = homeServerConnectionCopy;
                }
            }
        } catch (Throwable th) {
            c0Var = new x6.c0(th);
        }
        return new x6.d0(c0Var);
    }

    @Override // r7.p
    public final Object invoke(ka.k0 k0Var, d7.d<? super x6.d0> dVar) {
        return ((HomeServerRepository$connect$2) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
    }
}
