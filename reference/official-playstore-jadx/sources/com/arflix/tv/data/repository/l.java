package com.arflix.tv.data.repository;

import java.io.File;
import java.io.FilenameFilter;

/* JADX INFO: loaded from: classes3.dex */
public final /* synthetic */ class l implements FilenameFilter {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f7662a;

    public /* synthetic */ l(int i10) {
        this.f7662a = i10;
    }

    @Override // java.io.FilenameFilter
    public final boolean accept(File file, String str) {
        switch (this.f7662a) {
            case 0:
                return IptvRepository.cleanupStaleEpgTempFiles$lambda$0$0(file, str);
            default:
                return IptvRepository.cleanupIptvCacheDirectory$lambda$0$0(file, str);
        }
    }
}
