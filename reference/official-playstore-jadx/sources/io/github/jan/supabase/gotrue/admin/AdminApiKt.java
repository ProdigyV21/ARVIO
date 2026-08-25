package io.github.jan.supabase.gotrue.admin;

import d7.d;
import fb.b;
import io.github.jan.supabase.gotrue.admin.LinkType;
import kotlin.Metadata;
import kotlin.jvm.internal.p;
import r7.l;
import x6.t0;
import x6.x;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u001aa\u0010\r\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\f0\u000b\"\n\b\u0000\u0010\u0001\u0018\u0001*\u00020\u0000*\u00020\u00022\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u00032\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00052\u0019\b\b\u0010\n\u001a\u0013\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\b0\u0007¢\u0006\u0002\b\tH\u0086H¢\u0006\u0004\b\r\u0010\u000e¨\u0006\u000f"}, d2 = {"Lio/github/jan/supabase/gotrue/admin/LinkType$Config;", "C", "Lio/github/jan/supabase/gotrue/admin/AdminApi;", "Lio/github/jan/supabase/gotrue/admin/LinkType;", "linkType", "", "redirectTo", "Lkotlin/Function1;", "Lx6/t0;", "Lx6/n;", "config", "Lx6/x;", "Lio/github/jan/supabase/gotrue/user/UserInfo;", "generateLinkFor", "(Lio/github/jan/supabase/gotrue/admin/AdminApi;Lio/github/jan/supabase/gotrue/admin/LinkType;Ljava/lang/String;Lr7/l;Ld7/d;)Ljava/lang/Object;", "gotrue-kt_release"}, k = 2, mv = {1, 9, 0}, xi = 48)
public final class AdminApiKt {
    public static final <C extends LinkType.Config> Object generateLinkFor(AdminApi adminApi, LinkType<C> linkType, String str, l<? super C, t0> lVar, d<? super x> dVar) {
        linkType.createConfig(lVar);
        b bVar = db.b.f14877d.f14879b;
        p.h();
        throw null;
    }

    public static Object generateLinkFor$default(AdminApi adminApi, LinkType linkType, String str, l lVar, d dVar, int i10, Object obj) {
        linkType.createConfig(lVar);
        b bVar = db.b.f14877d.f14879b;
        p.h();
        throw null;
    }
}
