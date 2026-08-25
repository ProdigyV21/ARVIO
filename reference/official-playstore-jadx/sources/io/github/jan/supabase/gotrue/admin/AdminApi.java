package io.github.jan.supabase.gotrue.admin;

import d7.d;
import db.b0;
import e7.a;
import io.github.jan.supabase.gotrue.SignOutScope;
import io.github.jan.supabase.gotrue.admin.AdminUserBuilder;
import io.github.jan.supabase.gotrue.user.UserInfo;
import io.github.jan.supabase.gotrue.user.UserMfaFactor;
import java.util.List;
import kotlin.Metadata;
import r7.l;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000d\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\bv\u0018\u00002\u00020\u0001J)\u0010\b\u001a\u00020\u00072\u0017\u0010\u0006\u001a\u0013\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00040\u0002¢\u0006\u0002\b\u0005H¦@¢\u0006\u0004\b\b\u0010\tJ)\u0010\u000b\u001a\u00020\u00072\u0017\u0010\u0006\u001a\u0013\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u00040\u0002¢\u0006\u0002\b\u0005H¦@¢\u0006\u0004\b\u000b\u0010\tJ\"\u0010\u0010\u001a\u00020\u00042\u0006\u0010\r\u001a\u00020\f2\b\b\u0002\u0010\u000f\u001a\u00020\u000eH¦@¢\u0006\u0004\b\u0010\u0010\u0011J\"\u0010\u0012\u001a\u00020\u00042\u0006\u0010\r\u001a\u00020\f2\b\b\u0002\u0010\u000f\u001a\u00020\u000eH\u0096@¢\u0006\u0004\b\u0012\u0010\u0011J.\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00070\u00162\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\u00132\n\b\u0002\u0010\u0015\u001a\u0004\u0018\u00010\u0013H¦@¢\u0006\u0004\b\u0017\u0010\u0018J\u0018\u0010\u001a\u001a\u00020\u00072\u0006\u0010\u0019\u001a\u00020\fH¦@¢\u0006\u0004\b\u001a\u0010\u001bJ\u0018\u0010\u001c\u001a\u00020\u00042\u0006\u0010\u0019\u001a\u00020\fH¦@¢\u0006\u0004\b\u001c\u0010\u001bJ0\u0010!\u001a\u00020\u00042\u0006\u0010\u001d\u001a\u00020\f2\n\b\u0002\u0010\u001e\u001a\u0004\u0018\u00010\f2\n\b\u0002\u0010 \u001a\u0004\u0018\u00010\u001fH¦@¢\u0006\u0004\b!\u0010\"J1\u0010$\u001a\u00020\u00072\u0006\u0010\u0019\u001a\u00020\f2\u0017\u0010\u0006\u001a\u0013\u0012\u0004\u0012\u00020#\u0012\u0004\u0012\u00020\u00040\u0002¢\u0006\u0002\b\u0005H¦@¢\u0006\u0004\b$\u0010%J\u001e\u0010'\u001a\b\u0012\u0004\u0012\u00020&0\u00162\u0006\u0010\u0019\u001a\u00020\fH¦@¢\u0006\u0004\b'\u0010\u001bJ \u0010)\u001a\u00020\u00042\u0006\u0010\u0019\u001a\u00020\f2\u0006\u0010(\u001a\u00020\fH¦@¢\u0006\u0004\b)\u0010*\u0082\u0001\u0001+¨\u0006,"}, d2 = {"Lio/github/jan/supabase/gotrue/admin/AdminApi;", "", "Lkotlin/Function1;", "Lio/github/jan/supabase/gotrue/admin/AdminUserBuilder$Email;", "Lx6/t0;", "Lx6/n;", "builder", "Lio/github/jan/supabase/gotrue/user/UserInfo;", "createUserWithEmail", "(Lr7/l;Ld7/d;)Ljava/lang/Object;", "Lio/github/jan/supabase/gotrue/admin/AdminUserBuilder$Phone;", "createUserWithPhone", "", "jwt", "Lio/github/jan/supabase/gotrue/SignOutScope;", "scope", "signOut", "(Ljava/lang/String;Lio/github/jan/supabase/gotrue/SignOutScope;Ld7/d;)Ljava/lang/Object;", "logout", "", "page", "perPage", "", "retrieveUsers", "(Ljava/lang/Integer;Ljava/lang/Integer;Ld7/d;)Ljava/lang/Object;", "uid", "retrieveUserById", "(Ljava/lang/String;Ld7/d;)Ljava/lang/Object;", "deleteUser", "email", "redirectTo", "Ldb/b0;", "data", "inviteUserByEmail", "(Ljava/lang/String;Ljava/lang/String;Ldb/b0;Ld7/d;)Ljava/lang/Object;", "Lio/github/jan/supabase/gotrue/admin/AdminUserUpdateBuilder;", "updateUserById", "(Ljava/lang/String;Lr7/l;Ld7/d;)Ljava/lang/Object;", "Lio/github/jan/supabase/gotrue/user/UserMfaFactor;", "retrieveFactors", "factorId", "deleteFactor", "(Ljava/lang/String;Ljava/lang/String;Ld7/d;)Ljava/lang/Object;", "Lio/github/jan/supabase/gotrue/admin/AdminApiImpl;", "gotrue-kt_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public interface AdminApi {

    @Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
    public static final class DefaultImpls {
        public static /* synthetic */ Object inviteUserByEmail$default(AdminApi adminApi, String str, String str2, b0 b0Var, d dVar, int i10, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: inviteUserByEmail");
            }
            if ((i10 & 2) != 0) {
                str2 = null;
            }
            if ((i10 & 4) != 0) {
                b0Var = null;
            }
            return adminApi.inviteUserByEmail(str, str2, b0Var, dVar);
        }

        public static Object logout(AdminApi adminApi, String str, SignOutScope signOutScope, d<? super t0> dVar) {
            Object objSignOut = adminApi.signOut(str, signOutScope, dVar);
            return objSignOut == a.f15033i ? objSignOut : t0.f22605a;
        }

        public static /* synthetic */ Object logout$default(AdminApi adminApi, String str, SignOutScope signOutScope, d dVar, int i10, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: logout");
            }
            if ((i10 & 2) != 0) {
                signOutScope = SignOutScope.LOCAL;
            }
            return adminApi.logout(str, signOutScope, dVar);
        }

        public static /* synthetic */ Object retrieveUsers$default(AdminApi adminApi, Integer num, Integer num2, d dVar, int i10, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: retrieveUsers");
            }
            if ((i10 & 1) != 0) {
                num = null;
            }
            if ((i10 & 2) != 0) {
                num2 = null;
            }
            return adminApi.retrieveUsers(num, num2, dVar);
        }

        public static /* synthetic */ Object signOut$default(AdminApi adminApi, String str, SignOutScope signOutScope, d dVar, int i10, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: signOut");
            }
            if ((i10 & 2) != 0) {
                signOutScope = SignOutScope.LOCAL;
            }
            return adminApi.signOut(str, signOutScope, dVar);
        }
    }

    Object createUserWithEmail(l<? super AdminUserBuilder.Email, t0> lVar, d<? super UserInfo> dVar);

    Object createUserWithPhone(l<? super AdminUserBuilder.Phone, t0> lVar, d<? super UserInfo> dVar);

    Object deleteFactor(String str, String str2, d<? super t0> dVar);

    Object deleteUser(String str, d<? super t0> dVar);

    Object inviteUserByEmail(String str, String str2, b0 b0Var, d<? super t0> dVar);

    Object logout(String str, SignOutScope signOutScope, d<? super t0> dVar);

    Object retrieveFactors(String str, d<? super List<UserMfaFactor>> dVar);

    Object retrieveUserById(String str, d<? super UserInfo> dVar);

    Object retrieveUsers(Integer num, Integer num2, d<? super List<UserInfo>> dVar);

    Object signOut(String str, SignOutScope signOutScope, d<? super t0> dVar);

    Object updateUserById(String str, l<? super AdminUserUpdateBuilder, t0> lVar, d<? super UserInfo> dVar);
}
