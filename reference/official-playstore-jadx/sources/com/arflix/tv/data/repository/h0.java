package com.arflix.tv.data.repository;

import java.util.Map;

/* JADX INFO: loaded from: classes3.dex */
public final /* synthetic */ class h0 implements r7.l {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f7645i;

    public /* synthetic */ h0(int i10) {
        this.f7645i = i10;
    }

    @Override // r7.l
    public final Object invoke(Object obj) {
        switch (this.f7645i) {
            case 0:
                return StreamRepositoryKt.streamAddonConfigurationRevision$lambda$0(((Byte) obj).byteValue());
            case 1:
                return TraktRepository.searchTmdbWatchlistMatch$lambda$3((x6.x) obj);
            default:
                return TraktRepository.encodeDismissedMap$lambda$0((Map.Entry) obj);
        }
    }
}
