package com.arflix.tv.data.repository;

import com.arflix.tv.data.model.IptvProgram;
import java.util.LinkedHashMap;

/* JADX INFO: loaded from: classes3.dex */
public final /* synthetic */ class j implements r7.p {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f7653i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ LinkedHashMap f7654l;

    public /* synthetic */ j(LinkedHashMap linkedHashMap, int i10) {
        this.f7653i = i10;
        this.f7654l = linkedHashMap;
    }

    @Override // r7.p
    public final Object invoke(Object obj, Object obj2) {
        String str = (String) obj;
        IptvProgram iptvProgram = (IptvProgram) obj2;
        switch (this.f7653i) {
            case 0:
                return IptvEpgIndex.loadNowNext$lambda$0(this.f7654l, str, iptvProgram);
            default:
                return IptvEpgIndex.loadWindow$lambda$0(this.f7654l, str, iptvProgram);
        }
    }
}
