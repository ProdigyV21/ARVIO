package com.arflix.tv.data.repository;

/* JADX INFO: loaded from: classes3.dex */
public final /* synthetic */ class s implements r7.p {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f7700i;

    public /* synthetic */ s(int i10) {
        this.f7700i = i10;
    }

    @Override // r7.p
    public final Object invoke(Object obj, Object obj2) {
        int i10 = this.f7700i;
        int iIntValue = ((Integer) obj).intValue();
        boolean zBooleanValue = ((Boolean) obj2).booleanValue();
        switch (i10) {
            case 0:
                return IptvRepository.fetchXtreamEpgListingsAsync$lambda$0(iIntValue, zBooleanValue);
            default:
                return IptvRepository.fetchXtreamFullEpgListingsAsync$lambda$0(iIntValue, zBooleanValue);
        }
    }
}
