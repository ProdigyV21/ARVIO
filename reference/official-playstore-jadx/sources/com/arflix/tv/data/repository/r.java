package com.arflix.tv.data.repository;

/* JADX INFO: loaded from: classes3.dex */
public final /* synthetic */ class r implements r7.l {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f7698i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ int f7699l;

    public /* synthetic */ r(int i10, int i11) {
        this.f7698i = i11;
        this.f7699l = i10;
    }

    @Override // r7.l
    public final Object invoke(Object obj) {
        switch (this.f7698i) {
            case 0:
                return Boolean.valueOf(IptvRepository.findEpisodeVodFromVodCatalogFallbackSources$lambda$2(this.f7699l, (x6.j0) obj));
            default:
                ((Integer) obj).intValue();
                throw new IndexOutOfBoundsException(a0.c.k("Collection doesn't contain element at index ", this.f7699l, '.'));
        }
    }
}
