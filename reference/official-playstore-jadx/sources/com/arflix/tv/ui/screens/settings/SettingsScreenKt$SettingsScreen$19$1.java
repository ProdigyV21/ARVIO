package com.arflix.tv.ui.screens.settings;

import android.net.Uri;
import androidx.compose.runtime.MutableIntState;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.State;
import com.arflix.tv.data.repository.IptvPlaylistEntry;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
@f7.e(c = "com.arflix.tv.ui.screens.settings.SettingsScreenKt$SettingsScreen$19$1", f = "SettingsScreen.kt", l = {}, m = "invokeSuspend", v = 2)
public final class SettingsScreenKt$SettingsScreen$19$1 extends f7.j implements r7.p<ka.k0, d7.d<? super x6.t0>, Object> {
    final /* synthetic */ MutableIntState $editingIptvIndex$delegate;
    final /* synthetic */ MutableState<Boolean> $iptvEditEnabled$delegate;
    final /* synthetic */ MutableState<String> $iptvEditEpg$delegate;
    final /* synthetic */ MutableState<String> $iptvEditName$delegate;
    final /* synthetic */ MutableState<String> $iptvEditUrl$delegate;
    final /* synthetic */ MutableState<String> $iptvEditXtreamPass$delegate;
    final /* synthetic */ MutableState<String> $iptvEditXtreamUser$delegate;
    final /* synthetic */ MutableState<Boolean> $showIptvInput$delegate;
    final /* synthetic */ State<SettingsUiState> $uiState$delegate;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SettingsScreenKt$SettingsScreen$19$1(MutableState<Boolean> mutableState, MutableIntState mutableIntState, MutableState<String> mutableState2, MutableState<String> mutableState3, MutableState<String> mutableState4, MutableState<Boolean> mutableState5, MutableState<String> mutableState6, MutableState<String> mutableState7, State<SettingsUiState> state, d7.d<? super SettingsScreenKt$SettingsScreen$19$1> dVar) {
        super(2, dVar);
        this.$showIptvInput$delegate = mutableState;
        this.$editingIptvIndex$delegate = mutableIntState;
        this.$iptvEditName$delegate = mutableState2;
        this.$iptvEditUrl$delegate = mutableState3;
        this.$iptvEditEpg$delegate = mutableState4;
        this.$iptvEditEnabled$delegate = mutableState5;
        this.$iptvEditXtreamUser$delegate = mutableState6;
        this.$iptvEditXtreamPass$delegate = mutableState7;
        this.$uiState$delegate = state;
    }

    @Override // f7.a
    public final d7.d<x6.t0> create(Object obj, d7.d<?> dVar) {
        return new SettingsScreenKt$SettingsScreen$19$1(this.$showIptvInput$delegate, this.$editingIptvIndex$delegate, this.$iptvEditName$delegate, this.$iptvEditUrl$delegate, this.$iptvEditEpg$delegate, this.$iptvEditEnabled$delegate, this.$iptvEditXtreamUser$delegate, this.$iptvEditXtreamPass$delegate, this.$uiState$delegate, dVar);
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
        String strI;
        String m3uUrl;
        Uri uri;
        String queryParameter;
        String queryParameter2;
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        k2.c.G(obj);
        if (SettingsScreenKt.SettingsScreen$lambda$85(this.$showIptvInput$delegate)) {
            IptvPlaylistEntry iptvPlaylistEntry = (IptvPlaylistEntry) kotlin.collections.x.p0(this.$editingIptvIndex$delegate.getIntValue(), SettingsScreenKt.SettingsScreen$lambda$7(this.$uiState$delegate).getIptvPlaylists());
            MutableState<String> mutableState = this.$iptvEditName$delegate;
            if (iptvPlaylistEntry == null || (strI = iptvPlaylistEntry.getName()) == null) {
                strI = a0.c.i(this.$editingIptvIndex$delegate.getIntValue() + 2, "List ");
                if (this.$editingIptvIndex$delegate.getIntValue() < 0) {
                    strI = null;
                }
                if (strI == null) {
                    strI = a0.c.i(SettingsScreenKt.SettingsScreen$lambda$7(this.$uiState$delegate).getIptvPlaylists().size() + 1, "List ");
                }
            }
            mutableState.setValue(strI);
            if (iptvPlaylistEntry == null || (m3uUrl = iptvPlaylistEntry.getM3uUrl()) == null) {
                m3uUrl = "";
            }
            try {
                uri = Uri.parse(m3uUrl);
            } catch (Exception unused) {
                uri = null;
            }
            if (uri == null || (queryParameter = uri.getQueryParameter("username")) == null) {
                queryParameter = "";
            }
            if (uri == null || (queryParameter2 = uri.getQueryParameter("password")) == null) {
                queryParameter2 = "";
            }
            if (!kotlin.text.o.T(m3uUrl, "get.php", false) || kotlin.text.o.h0(queryParameter) || kotlin.text.o.h0(queryParameter2)) {
                this.$iptvEditUrl$delegate.setValue(m3uUrl);
                this.$iptvEditXtreamUser$delegate.setValue("");
                this.$iptvEditXtreamPass$delegate.setValue("");
            } else {
                this.$iptvEditUrl$delegate.setValue(uri.getScheme() + "://" + uri.getHost() + (uri.getPort() > 0 ? a0.c.i(uri.getPort(), ":") : ""));
                this.$iptvEditXtreamUser$delegate.setValue(queryParameter);
                this.$iptvEditXtreamPass$delegate.setValue(queryParameter2);
            }
            MutableState<String> mutableState2 = this.$iptvEditEpg$delegate;
            String str = iptvPlaylistEntry != null ? SettingsScreenKt.settingsEpgInput(iptvPlaylistEntry) : null;
            mutableState2.setValue(str != null ? str : "");
            SettingsScreenKt.SettingsScreen$lambda$101(this.$iptvEditEnabled$delegate, iptvPlaylistEntry != null ? iptvPlaylistEntry.getEnabled() : true);
        } else {
            this.$editingIptvIndex$delegate.setIntValue(-1);
            this.$iptvEditName$delegate.setValue("");
            this.$iptvEditUrl$delegate.setValue("");
            this.$iptvEditEpg$delegate.setValue("");
            SettingsScreenKt.SettingsScreen$lambda$101(this.$iptvEditEnabled$delegate, true);
            this.$iptvEditXtreamUser$delegate.setValue("");
            this.$iptvEditXtreamPass$delegate.setValue("");
        }
        return x6.t0.f22605a;
    }

    @Override // r7.p
    public final Object invoke(ka.k0 k0Var, d7.d<? super x6.t0> dVar) {
        return ((SettingsScreenKt$SettingsScreen$19$1) create(k0Var, dVar)).invokeSuspend(x6.t0.f22605a);
    }
}
