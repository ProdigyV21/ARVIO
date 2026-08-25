package com.arflix.tv.ui.screens.settings;

import java.util.List;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\u0002\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"Lka/k0;", "", "", "<anonymous>", "(Lka/k0;)Ljava/util/List;"}, k = 3, mv = {2, 3, 0})
@f7.e(c = "com.arflix.tv.ui.screens.settings.SettingsViewModel$loadIptvGroupsForPlaylist$pagedGroups$1", f = "SettingsViewModel.kt", l = {}, m = "invokeSuspend", v = 2)
public final class SettingsViewModel$loadIptvGroupsForPlaylist$pagedGroups$1 extends f7.j implements r7.p<ka.k0, d7.d<? super List<? extends String>>, Object> {
    final /* synthetic */ String $playlistId;
    int label;
    final /* synthetic */ SettingsViewModel this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SettingsViewModel$loadIptvGroupsForPlaylist$pagedGroups$1(SettingsViewModel settingsViewModel, String str, d7.d<? super SettingsViewModel$loadIptvGroupsForPlaylist$pagedGroups$1> dVar) {
        super(2, dVar);
        this.this$0 = settingsViewModel;
        this.$playlistId = str;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean invokeSuspend$lambda$0(String str, x6.j0 j0Var) {
        return kotlin.jvm.internal.p.a((String) j0Var.f22587i, str) && ((Number) j0Var.f22589m).intValue() > 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final String invokeSuspend$lambda$1(x6.j0 j0Var) {
        String string = kotlin.text.o.L0((String) j0Var.f22588l).toString();
        return kotlin.text.o.h0(string) ? "Ungrouped" : string;
    }

    @Override // f7.a
    public final d7.d<x6.t0> create(Object obj, d7.d<?> dVar) {
        return new SettingsViewModel$loadIptvGroupsForPlaylist$pagedGroups$1(this.this$0, this.$playlistId, dVar);
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        k2.c.G(obj);
        return ga.r.U(ga.r.G(new ga.t(ga.r.I(new ga.p(this.this$0.iptvRepository.pagedPlaylistGroupCounts(), 3), new h4(this.$playlistId, 1)), new i4(1))));
    }

    @Override // r7.p
    public final Object invoke(ka.k0 k0Var, d7.d<? super List<String>> dVar) {
        return ((SettingsViewModel$loadIptvGroupsForPlaylist$pagedGroups$1) create(k0Var, dVar)).invokeSuspend(x6.t0.f22605a);
    }
}
