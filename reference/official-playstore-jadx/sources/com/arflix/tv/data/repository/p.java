package com.arflix.tv.data.repository;

import androidx.compose.runtime.Composer;
import com.arflix.tv.ui.screens.settings.SettingsScreenKt;
import java.io.Serializable;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public final /* synthetic */ class p implements r7.p {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f7683i = 1;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ int f7684l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ Serializable f7685m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ Object f7686n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final /* synthetic */ x6.o f7687o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ Object f7688p;

    public /* synthetic */ p(String str, List list, r7.a aVar, r7.a aVar2, int i10) {
        this.f7685m = str;
        this.f7686n = list;
        this.f7687o = aVar;
        this.f7688p = aVar2;
        this.f7684l = i10;
    }

    @Override // r7.p
    public final Object invoke(Object obj, Object obj2) {
        switch (this.f7683i) {
            case 0:
                return IptvRepository.fetchXtreamFullEpg$lambda$6((kotlin.jvm.internal.i0) this.f7685m, (kotlin.jvm.internal.i0) this.f7686n, this.f7684l, (r7.l) this.f7687o, (IptvRepository) this.f7688p, ((Integer) obj).intValue(), ((Boolean) obj2).booleanValue());
            default:
                int iIntValue = ((Integer) obj2).intValue();
                return SettingsScreenKt.InputModalLegacy$lambda$7((String) this.f7685m, (List) this.f7686n, (r7.a) this.f7687o, (r7.a) this.f7688p, this.f7684l, (Composer) obj, iIntValue);
        }
    }

    public /* synthetic */ p(kotlin.jvm.internal.i0 i0Var, kotlin.jvm.internal.i0 i0Var2, int i10, r7.l lVar, IptvRepository iptvRepository) {
        this.f7685m = i0Var;
        this.f7686n = i0Var2;
        this.f7684l = i10;
        this.f7687o = lVar;
        this.f7688p = iptvRepository;
    }
}
