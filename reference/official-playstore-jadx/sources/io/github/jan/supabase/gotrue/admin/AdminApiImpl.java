package io.github.jan.supabase.gotrue.admin;

import androidx.media3.extractor.ts.PsExtractor;
import d7.d;
import db.b0;
import e7.a;
import f7.c;
import f7.e;
import io.github.jan.supabase.gotrue.Auth;
import io.github.jan.supabase.gotrue.AuthImpl;
import io.github.jan.supabase.gotrue.AuthenticatedSupabaseApi;
import io.github.jan.supabase.gotrue.SignOutScope;
import io.github.jan.supabase.gotrue.admin.AdminApi;
import io.ktor.http.ContentType;
import java.util.LinkedHashMap;
import kotlin.Metadata;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000p\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0001\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005J \u0010\u000b\u001a\u00020\n2\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\bH\u0096@¢\u0006\u0004\b\u000b\u0010\fJ)\u0010\u0012\u001a\u00020\u00112\u0017\u0010\u0010\u001a\u0013\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\n0\r¢\u0006\u0002\b\u000fH\u0096@¢\u0006\u0004\b\u0012\u0010\u0013J)\u0010\u0015\u001a\u00020\u00112\u0017\u0010\u0010\u001a\u0013\u0012\u0004\u0012\u00020\u0014\u0012\u0004\u0012\u00020\n0\r¢\u0006\u0002\b\u000fH\u0096@¢\u0006\u0004\b\u0015\u0010\u0013J*\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u00110\u00192\b\u0010\u0017\u001a\u0004\u0018\u00010\u00162\b\u0010\u0018\u001a\u0004\u0018\u00010\u0016H\u0096@¢\u0006\u0004\b\u001a\u0010\u001bJ\u0018\u0010\u001d\u001a\u00020\u00112\u0006\u0010\u001c\u001a\u00020\u0006H\u0096@¢\u0006\u0004\b\u001d\u0010\u001eJ\u0018\u0010\u001f\u001a\u00020\n2\u0006\u0010\u001c\u001a\u00020\u0006H\u0096@¢\u0006\u0004\b\u001f\u0010\u001eJ,\u0010$\u001a\u00020\n2\u0006\u0010 \u001a\u00020\u00062\b\u0010!\u001a\u0004\u0018\u00010\u00062\b\u0010#\u001a\u0004\u0018\u00010\"H\u0096@¢\u0006\u0004\b$\u0010%J1\u0010'\u001a\u00020\u00112\u0006\u0010\u001c\u001a\u00020\u00062\u0017\u0010\u0010\u001a\u0013\u0012\u0004\u0012\u00020&\u0012\u0004\u0012\u00020\n0\r¢\u0006\u0002\b\u000fH\u0096@¢\u0006\u0004\b'\u0010(J \u0010*\u001a\u00020\n2\u0006\u0010\u001c\u001a\u00020\u00062\u0006\u0010)\u001a\u00020\u0006H\u0096@¢\u0006\u0004\b*\u0010+J\u001e\u0010-\u001a\b\u0012\u0004\u0012\u00020,0\u00192\u0006\u0010\u001c\u001a\u00020\u0006H\u0096@¢\u0006\u0004\b-\u0010\u001eR\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010.\u001a\u0004\b/\u00100R\u0017\u00102\u001a\u0002018\u0006¢\u0006\f\n\u0004\b2\u00103\u001a\u0004\b4\u00105¨\u00066"}, d2 = {"Lio/github/jan/supabase/gotrue/admin/AdminApiImpl;", "Lio/github/jan/supabase/gotrue/admin/AdminApi;", "Lio/github/jan/supabase/gotrue/Auth;", "gotrue", "<init>", "(Lio/github/jan/supabase/gotrue/Auth;)V", "", "jwt", "Lio/github/jan/supabase/gotrue/SignOutScope;", "scope", "Lx6/t0;", "signOut", "(Ljava/lang/String;Lio/github/jan/supabase/gotrue/SignOutScope;Ld7/d;)Ljava/lang/Object;", "Lkotlin/Function1;", "Lio/github/jan/supabase/gotrue/admin/AdminUserBuilder$Email;", "Lx6/n;", "builder", "Lio/github/jan/supabase/gotrue/user/UserInfo;", "createUserWithEmail", "(Lr7/l;Ld7/d;)Ljava/lang/Object;", "Lio/github/jan/supabase/gotrue/admin/AdminUserBuilder$Phone;", "createUserWithPhone", "", "page", "perPage", "", "retrieveUsers", "(Ljava/lang/Integer;Ljava/lang/Integer;Ld7/d;)Ljava/lang/Object;", "uid", "retrieveUserById", "(Ljava/lang/String;Ld7/d;)Ljava/lang/Object;", "deleteUser", "email", "redirectTo", "Ldb/b0;", "data", "inviteUserByEmail", "(Ljava/lang/String;Ljava/lang/String;Ldb/b0;Ld7/d;)Ljava/lang/Object;", "Lio/github/jan/supabase/gotrue/admin/AdminUserUpdateBuilder;", "updateUserById", "(Ljava/lang/String;Lr7/l;Ld7/d;)Ljava/lang/Object;", "factorId", "deleteFactor", "(Ljava/lang/String;Ljava/lang/String;Ld7/d;)Ljava/lang/Object;", "Lio/github/jan/supabase/gotrue/user/UserMfaFactor;", "retrieveFactors", "Lio/github/jan/supabase/gotrue/Auth;", "getGotrue", "()Lio/github/jan/supabase/gotrue/Auth;", "Lio/github/jan/supabase/gotrue/AuthenticatedSupabaseApi;", "api", "Lio/github/jan/supabase/gotrue/AuthenticatedSupabaseApi;", "getApi", "()Lio/github/jan/supabase/gotrue/AuthenticatedSupabaseApi;", "gotrue-kt_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final class AdminApiImpl implements AdminApi {
    private final AuthenticatedSupabaseApi api;
    private final Auth gotrue;

    /* JADX INFO: renamed from: io.github.jan.supabase.gotrue.admin.AdminApiImpl$createUserWithEmail$1, reason: invalid class name */
    @Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
    @e(c = "io.github.jan.supabase.gotrue.admin.AdminApiImpl", f = "AdminApi.kt", l = {190, 197}, m = "createUserWithEmail")
    public static final class AnonymousClass1 extends c {
        int label;
        /* synthetic */ Object result;

        public AnonymousClass1(d<? super AnonymousClass1> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return AdminApiImpl.this.createUserWithEmail(null, this);
        }
    }

    /* JADX INFO: renamed from: io.github.jan.supabase.gotrue.admin.AdminApiImpl$createUserWithPhone$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
    @e(c = "io.github.jan.supabase.gotrue.admin.AdminApiImpl", f = "AdminApi.kt", l = {190, 197}, m = "createUserWithPhone")
    public static final class C16941 extends c {
        int label;
        /* synthetic */ Object result;

        public C16941(d<? super C16941> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return AdminApiImpl.this.createUserWithPhone(null, this);
        }
    }

    /* JADX INFO: renamed from: io.github.jan.supabase.gotrue.admin.AdminApiImpl$retrieveFactors$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
    @e(c = "io.github.jan.supabase.gotrue.admin.AdminApiImpl", f = "AdminApi.kt", l = {PsExtractor.PRIVATE_STREAM_1, 194}, m = "retrieveFactors")
    public static final class C16951 extends c {
        int label;
        /* synthetic */ Object result;

        public C16951(d<? super C16951> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return AdminApiImpl.this.retrieveFactors(null, this);
        }
    }

    /* JADX INFO: renamed from: io.github.jan.supabase.gotrue.admin.AdminApiImpl$retrieveUserById$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
    @e(c = "io.github.jan.supabase.gotrue.admin.AdminApiImpl", f = "AdminApi.kt", l = {PsExtractor.PRIVATE_STREAM_1, 194}, m = "retrieveUserById")
    public static final class C16961 extends c {
        int label;
        /* synthetic */ Object result;

        public C16961(d<? super C16961> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return AdminApiImpl.this.retrieveUserById(null, this);
        }
    }

    /* JADX INFO: renamed from: io.github.jan.supabase.gotrue.admin.AdminApiImpl$retrieveUsers$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
    @e(c = "io.github.jan.supabase.gotrue.admin.AdminApiImpl", f = "AdminApi.kt", l = {PsExtractor.PRIVATE_STREAM_1, 193}, m = "retrieveUsers")
    public static final class C16971 extends c {
        int label;
        /* synthetic */ Object result;

        public C16971(d<? super C16971> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return AdminApiImpl.this.retrieveUsers(null, null, this);
        }
    }

    /* JADX INFO: renamed from: io.github.jan.supabase.gotrue.admin.AdminApiImpl$updateUserById$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
    @e(c = "io.github.jan.supabase.gotrue.admin.AdminApiImpl", f = "AdminApi.kt", l = {190, 197}, m = "updateUserById")
    public static final class C16981 extends c {
        int label;
        /* synthetic */ Object result;

        public C16981(d<? super C16981> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return AdminApiImpl.this.updateUserById(null, null, this);
        }
    }

    public AdminApiImpl(Auth auth) {
        this.gotrue = auth;
        this.api = ((AuthImpl) auth).getApi();
    }

    /* JADX WARN: Code restructure failed: missing block: B:20:0x0062, code lost:
    
        if (r9 == r5) goto L21;
     */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    @Override // io.github.jan.supabase.gotrue.admin.AdminApi
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.Object createUserWithEmail(r7.l<? super io.github.jan.supabase.gotrue.admin.AdminUserBuilder.Email, x6.t0> r8, d7.d<? super io.github.jan.supabase.gotrue.user.UserInfo> r9) throws io.github.jan.supabase.exceptions.SupabaseEncodingException {
        /*
            r7 = this;
            boolean r0 = r9 instanceof io.github.jan.supabase.gotrue.admin.AdminApiImpl.AnonymousClass1
            if (r0 == 0) goto L13
            r0 = r9
            io.github.jan.supabase.gotrue.admin.AdminApiImpl$createUserWithEmail$1 r0 = (io.github.jan.supabase.gotrue.admin.AdminApiImpl.AnonymousClass1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            io.github.jan.supabase.gotrue.admin.AdminApiImpl$createUserWithEmail$1 r0 = new io.github.jan.supabase.gotrue.admin.AdminApiImpl$createUserWithEmail$1
            r0.<init>(r9)
        L18:
            java.lang.Object r9 = r0.result
            int r1 = r0.label
            r2 = 2
            r3 = 0
            r4 = 1
            e7.a r5 = e7.a.f15033i
            if (r1 == 0) goto L37
            if (r1 == r4) goto L33
            if (r1 != r2) goto L2b
            k2.c.G(r9)
            goto L65
        L2b:
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
            java.lang.String r9 = "call to 'resume' before 'invoke' with coroutine"
            r8.<init>(r9)
            throw r8
        L33:
            k2.c.G(r9)
            goto L5a
        L37:
            k2.c.G(r9)
            io.github.jan.supabase.gotrue.admin.AdminUserBuilder$Email r9 = new io.github.jan.supabase.gotrue.admin.AdminUserBuilder$Email
            r9.<init>(r3, r4, r3)
            r8.invoke(r9)
            io.github.jan.supabase.gotrue.AuthenticatedSupabaseApi r8 = r7.api
            io.ktor.http.ContentType$Application r1 = io.ktor.http.ContentType.Application.INSTANCE
            io.ktor.http.ContentType r1 = r1.getJson()
            io.github.jan.supabase.gotrue.admin.AdminApiImpl$createUserWithEmail$$inlined$postJson$default$1 r6 = new io.github.jan.supabase.gotrue.admin.AdminApiImpl$createUserWithEmail$$inlined$postJson$default$1
            r6.<init>(r1, r9)
            r0.label = r4
            java.lang.String r9 = "admin/users"
            java.lang.Object r9 = r8.request(r9, r6, r0)
            if (r9 != r5) goto L5a
            goto L64
        L5a:
            io.ktor.client.statement.HttpResponse r9 = (io.ktor.client.statement.HttpResponse) r9
            r0.label = r2
            java.lang.Object r9 = io.ktor.client.statement.HttpResponseKt.bodyAsText$default(r9, r3, r0, r4, r3)
            if (r9 != r5) goto L65
        L64:
            return r5
        L65:
            java.lang.String r9 = (java.lang.String) r9
            db.b r8 = io.github.jan.supabase.UtilsKt.getSupabaseJson()     // Catch: kotlinx.serialization.MissingFieldException -> L79
            r8.getClass()     // Catch: kotlinx.serialization.MissingFieldException -> L79
            io.github.jan.supabase.gotrue.user.UserInfo$Companion r0 = io.github.jan.supabase.gotrue.user.UserInfo.INSTANCE     // Catch: kotlinx.serialization.MissingFieldException -> L79
            ya.h r0 = r0.serializer()     // Catch: kotlinx.serialization.MissingFieldException -> L79
            java.lang.Object r8 = r8.c(r9, r0)     // Catch: kotlinx.serialization.MissingFieldException -> L79
            return r8
        L79:
            io.github.jan.supabase.exceptions.SupabaseEncodingException r8 = new io.github.jan.supabase.exceptions.SupabaseEncodingException
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r1 = "Couldn't decode payload as "
            r0.<init>(r1)
            java.lang.Class<io.github.jan.supabase.gotrue.user.UserInfo> r1 = io.github.jan.supabase.gotrue.user.UserInfo.class
            kotlin.jvm.internal.m0 r2 = kotlin.jvm.internal.l0.f19747a
            kotlin.reflect.d r1 = r2.b(r1)
            java.lang.String r1 = r1.r()
            r0.append(r1)
            java.lang.String r1 = ". Input: "
            r0.append(r1)
            java.lang.String r1 = ""
            r2 = 0
            java.lang.String r3 = "\n"
            java.lang.String r9 = kotlin.text.u.O(r9, r3, r1, r2)
            r0.append(r9)
            java.lang.String r9 = r0.toString()
            r8.<init>(r9)
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: io.github.jan.supabase.gotrue.admin.AdminApiImpl.createUserWithEmail(r7.l, d7.d):java.lang.Object");
    }

    /* JADX WARN: Code restructure failed: missing block: B:20:0x0062, code lost:
    
        if (r9 == r5) goto L21;
     */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    @Override // io.github.jan.supabase.gotrue.admin.AdminApi
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.Object createUserWithPhone(r7.l<? super io.github.jan.supabase.gotrue.admin.AdminUserBuilder.Phone, x6.t0> r8, d7.d<? super io.github.jan.supabase.gotrue.user.UserInfo> r9) throws io.github.jan.supabase.exceptions.SupabaseEncodingException {
        /*
            r7 = this;
            boolean r0 = r9 instanceof io.github.jan.supabase.gotrue.admin.AdminApiImpl.C16941
            if (r0 == 0) goto L13
            r0 = r9
            io.github.jan.supabase.gotrue.admin.AdminApiImpl$createUserWithPhone$1 r0 = (io.github.jan.supabase.gotrue.admin.AdminApiImpl.C16941) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            io.github.jan.supabase.gotrue.admin.AdminApiImpl$createUserWithPhone$1 r0 = new io.github.jan.supabase.gotrue.admin.AdminApiImpl$createUserWithPhone$1
            r0.<init>(r9)
        L18:
            java.lang.Object r9 = r0.result
            int r1 = r0.label
            r2 = 2
            r3 = 0
            r4 = 1
            e7.a r5 = e7.a.f15033i
            if (r1 == 0) goto L37
            if (r1 == r4) goto L33
            if (r1 != r2) goto L2b
            k2.c.G(r9)
            goto L65
        L2b:
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
            java.lang.String r9 = "call to 'resume' before 'invoke' with coroutine"
            r8.<init>(r9)
            throw r8
        L33:
            k2.c.G(r9)
            goto L5a
        L37:
            k2.c.G(r9)
            io.github.jan.supabase.gotrue.admin.AdminUserBuilder$Phone r9 = new io.github.jan.supabase.gotrue.admin.AdminUserBuilder$Phone
            r9.<init>(r3, r4, r3)
            r8.invoke(r9)
            io.github.jan.supabase.gotrue.AuthenticatedSupabaseApi r8 = r7.api
            io.ktor.http.ContentType$Application r1 = io.ktor.http.ContentType.Application.INSTANCE
            io.ktor.http.ContentType r1 = r1.getJson()
            io.github.jan.supabase.gotrue.admin.AdminApiImpl$createUserWithPhone$$inlined$postJson$default$1 r6 = new io.github.jan.supabase.gotrue.admin.AdminApiImpl$createUserWithPhone$$inlined$postJson$default$1
            r6.<init>(r1, r9)
            r0.label = r4
            java.lang.String r9 = "admin/users"
            java.lang.Object r9 = r8.request(r9, r6, r0)
            if (r9 != r5) goto L5a
            goto L64
        L5a:
            io.ktor.client.statement.HttpResponse r9 = (io.ktor.client.statement.HttpResponse) r9
            r0.label = r2
            java.lang.Object r9 = io.ktor.client.statement.HttpResponseKt.bodyAsText$default(r9, r3, r0, r4, r3)
            if (r9 != r5) goto L65
        L64:
            return r5
        L65:
            java.lang.String r9 = (java.lang.String) r9
            db.b r8 = io.github.jan.supabase.UtilsKt.getSupabaseJson()     // Catch: kotlinx.serialization.MissingFieldException -> L79
            r8.getClass()     // Catch: kotlinx.serialization.MissingFieldException -> L79
            io.github.jan.supabase.gotrue.user.UserInfo$Companion r0 = io.github.jan.supabase.gotrue.user.UserInfo.INSTANCE     // Catch: kotlinx.serialization.MissingFieldException -> L79
            ya.h r0 = r0.serializer()     // Catch: kotlinx.serialization.MissingFieldException -> L79
            java.lang.Object r8 = r8.c(r9, r0)     // Catch: kotlinx.serialization.MissingFieldException -> L79
            return r8
        L79:
            io.github.jan.supabase.exceptions.SupabaseEncodingException r8 = new io.github.jan.supabase.exceptions.SupabaseEncodingException
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r1 = "Couldn't decode payload as "
            r0.<init>(r1)
            java.lang.Class<io.github.jan.supabase.gotrue.user.UserInfo> r1 = io.github.jan.supabase.gotrue.user.UserInfo.class
            kotlin.jvm.internal.m0 r2 = kotlin.jvm.internal.l0.f19747a
            kotlin.reflect.d r1 = r2.b(r1)
            java.lang.String r1 = r1.r()
            r0.append(r1)
            java.lang.String r1 = ". Input: "
            r0.append(r1)
            java.lang.String r1 = ""
            r2 = 0
            java.lang.String r3 = "\n"
            java.lang.String r9 = kotlin.text.u.O(r9, r3, r1, r2)
            r0.append(r9)
            java.lang.String r9 = r0.toString()
            r8.<init>(r9)
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: io.github.jan.supabase.gotrue.admin.AdminApiImpl.createUserWithPhone(r7.l, d7.d):java.lang.Object");
    }

    @Override // io.github.jan.supabase.gotrue.admin.AdminApi
    public Object deleteFactor(String str, String str2, d<? super t0> dVar) {
        Object objRequest = this.api.request(androidx.compose.material3.d.n("admin/users/", str, "/factors/", str2), new AdminApiImpl$deleteFactor$$inlined$delete$default$1(), dVar);
        return objRequest == a.f15033i ? objRequest : t0.f22605a;
    }

    @Override // io.github.jan.supabase.gotrue.admin.AdminApi
    public Object deleteUser(String str, d<? super t0> dVar) {
        Object objRequest = this.api.request(androidx.compose.material3.d.C("admin/users/", str), new AdminApiImpl$deleteUser$$inlined$delete$default$1(), dVar);
        return objRequest == a.f15033i ? objRequest : t0.f22605a;
    }

    public final AuthenticatedSupabaseApi getApi() {
        return this.api;
    }

    public final Auth getGotrue() {
        return this.gotrue;
    }

    @Override // io.github.jan.supabase.gotrue.admin.AdminApi
    public Object inviteUserByEmail(String str, String str2, b0 b0Var, d<? super t0> dVar) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        if (b0Var != null) {
        }
        Object objRequest = this.api.request("invite", new AdminApiImpl$inviteUserByEmail$$inlined$postJson$1(ContentType.Application.INSTANCE.getJson(), new b0(linkedHashMap), str2), dVar);
        return objRequest == a.f15033i ? objRequest : t0.f22605a;
    }

    @Override // io.github.jan.supabase.gotrue.admin.AdminApi
    public Object logout(String str, SignOutScope signOutScope, d<? super t0> dVar) {
        return AdminApi.DefaultImpls.logout(this, str, signOutScope, dVar);
    }

    /* JADX WARN: Code restructure failed: missing block: B:20:0x005a, code lost:
    
        if (r8 == r4) goto L21;
     */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    @Override // io.github.jan.supabase.gotrue.admin.AdminApi
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.Object retrieveFactors(java.lang.String r7, d7.d<? super java.util.List<io.github.jan.supabase.gotrue.user.UserMfaFactor>> r8) throws io.github.jan.supabase.exceptions.SupabaseEncodingException {
        /*
            r6 = this;
            boolean r0 = r8 instanceof io.github.jan.supabase.gotrue.admin.AdminApiImpl.C16951
            if (r0 == 0) goto L13
            r0 = r8
            io.github.jan.supabase.gotrue.admin.AdminApiImpl$retrieveFactors$1 r0 = (io.github.jan.supabase.gotrue.admin.AdminApiImpl.C16951) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            io.github.jan.supabase.gotrue.admin.AdminApiImpl$retrieveFactors$1 r0 = new io.github.jan.supabase.gotrue.admin.AdminApiImpl$retrieveFactors$1
            r0.<init>(r8)
        L18:
            java.lang.Object r8 = r0.result
            int r1 = r0.label
            r2 = 2
            r3 = 1
            e7.a r4 = e7.a.f15033i
            if (r1 == 0) goto L36
            if (r1 == r3) goto L32
            if (r1 != r2) goto L2a
            k2.c.G(r8)
            goto L5d
        L2a:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            java.lang.String r8 = "call to 'resume' before 'invoke' with coroutine"
            r7.<init>(r8)
            throw r7
        L32:
            k2.c.G(r8)
            goto L51
        L36:
            k2.c.G(r8)
            io.github.jan.supabase.gotrue.AuthenticatedSupabaseApi r8 = r6.api
            java.lang.String r1 = "admin/users/"
            java.lang.String r5 = "/factors"
            java.lang.String r7 = a0.c.l(r1, r7, r5)
            io.github.jan.supabase.gotrue.admin.AdminApiImpl$retrieveFactors$$inlined$get$default$1 r1 = new io.github.jan.supabase.gotrue.admin.AdminApiImpl$retrieveFactors$$inlined$get$default$1
            r1.<init>()
            r0.label = r3
            java.lang.Object r8 = r8.request(r7, r1, r0)
            if (r8 != r4) goto L51
            goto L5c
        L51:
            io.ktor.client.statement.HttpResponse r8 = (io.ktor.client.statement.HttpResponse) r8
            r0.label = r2
            r7 = 0
            java.lang.Object r8 = io.ktor.client.statement.HttpResponseKt.bodyAsText$default(r8, r7, r0, r3, r7)
            if (r8 != r4) goto L5d
        L5c:
            return r4
        L5d:
            java.lang.String r8 = (java.lang.String) r8
            db.b r7 = io.github.jan.supabase.UtilsKt.getSupabaseJson()     // Catch: kotlinx.serialization.MissingFieldException -> L77
            r7.getClass()     // Catch: kotlinx.serialization.MissingFieldException -> L77
            cb.d r0 = new cb.d     // Catch: kotlinx.serialization.MissingFieldException -> L77
            io.github.jan.supabase.gotrue.user.UserMfaFactor$Companion r1 = io.github.jan.supabase.gotrue.user.UserMfaFactor.INSTANCE     // Catch: kotlinx.serialization.MissingFieldException -> L77
            ya.h r1 = r1.serializer()     // Catch: kotlinx.serialization.MissingFieldException -> L77
            r2 = 0
            r0.<init>(r1, r2)     // Catch: kotlinx.serialization.MissingFieldException -> L77
            java.lang.Object r7 = r7.c(r8, r0)     // Catch: kotlinx.serialization.MissingFieldException -> L77
            return r7
        L77:
            io.github.jan.supabase.exceptions.SupabaseEncodingException r7 = new io.github.jan.supabase.exceptions.SupabaseEncodingException
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r1 = "Couldn't decode payload as "
            r0.<init>(r1)
            java.lang.Class<java.util.List> r1 = java.util.List.class
            kotlin.jvm.internal.m0 r2 = kotlin.jvm.internal.l0.f19747a
            kotlin.reflect.d r1 = r2.b(r1)
            java.lang.String r1 = r1.r()
            r0.append(r1)
            java.lang.String r1 = ". Input: "
            r0.append(r1)
            java.lang.String r1 = ""
            r2 = 0
            java.lang.String r3 = "\n"
            java.lang.String r8 = kotlin.text.u.O(r8, r3, r1, r2)
            r0.append(r8)
            java.lang.String r8 = r0.toString()
            r7.<init>(r8)
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: io.github.jan.supabase.gotrue.admin.AdminApiImpl.retrieveFactors(java.lang.String, d7.d):java.lang.Object");
    }

    /* JADX WARN: Code restructure failed: missing block: B:20:0x0058, code lost:
    
        if (r7 == r4) goto L21;
     */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    @Override // io.github.jan.supabase.gotrue.admin.AdminApi
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.Object retrieveUserById(java.lang.String r6, d7.d<? super io.github.jan.supabase.gotrue.user.UserInfo> r7) throws io.github.jan.supabase.exceptions.SupabaseEncodingException {
        /*
            r5 = this;
            boolean r0 = r7 instanceof io.github.jan.supabase.gotrue.admin.AdminApiImpl.C16961
            if (r0 == 0) goto L13
            r0 = r7
            io.github.jan.supabase.gotrue.admin.AdminApiImpl$retrieveUserById$1 r0 = (io.github.jan.supabase.gotrue.admin.AdminApiImpl.C16961) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            io.github.jan.supabase.gotrue.admin.AdminApiImpl$retrieveUserById$1 r0 = new io.github.jan.supabase.gotrue.admin.AdminApiImpl$retrieveUserById$1
            r0.<init>(r7)
        L18:
            java.lang.Object r7 = r0.result
            int r1 = r0.label
            r2 = 2
            r3 = 1
            e7.a r4 = e7.a.f15033i
            if (r1 == 0) goto L36
            if (r1 == r3) goto L32
            if (r1 != r2) goto L2a
            k2.c.G(r7)
            goto L5b
        L2a:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r7)
            throw r6
        L32:
            k2.c.G(r7)
            goto L4f
        L36:
            k2.c.G(r7)
            io.github.jan.supabase.gotrue.AuthenticatedSupabaseApi r7 = r5.api
            java.lang.String r1 = "admin/users/"
            java.lang.String r6 = androidx.compose.material3.d.C(r1, r6)
            io.github.jan.supabase.gotrue.admin.AdminApiImpl$retrieveUserById$$inlined$get$default$1 r1 = new io.github.jan.supabase.gotrue.admin.AdminApiImpl$retrieveUserById$$inlined$get$default$1
            r1.<init>()
            r0.label = r3
            java.lang.Object r7 = r7.request(r6, r1, r0)
            if (r7 != r4) goto L4f
            goto L5a
        L4f:
            io.ktor.client.statement.HttpResponse r7 = (io.ktor.client.statement.HttpResponse) r7
            r0.label = r2
            r6 = 0
            java.lang.Object r7 = io.ktor.client.statement.HttpResponseKt.bodyAsText$default(r7, r6, r0, r3, r6)
            if (r7 != r4) goto L5b
        L5a:
            return r4
        L5b:
            java.lang.String r7 = (java.lang.String) r7
            db.b r6 = io.github.jan.supabase.UtilsKt.getSupabaseJson()     // Catch: kotlinx.serialization.MissingFieldException -> L6f
            r6.getClass()     // Catch: kotlinx.serialization.MissingFieldException -> L6f
            io.github.jan.supabase.gotrue.user.UserInfo$Companion r0 = io.github.jan.supabase.gotrue.user.UserInfo.INSTANCE     // Catch: kotlinx.serialization.MissingFieldException -> L6f
            ya.h r0 = r0.serializer()     // Catch: kotlinx.serialization.MissingFieldException -> L6f
            java.lang.Object r6 = r6.c(r7, r0)     // Catch: kotlinx.serialization.MissingFieldException -> L6f
            return r6
        L6f:
            io.github.jan.supabase.exceptions.SupabaseEncodingException r6 = new io.github.jan.supabase.exceptions.SupabaseEncodingException
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r1 = "Couldn't decode payload as "
            r0.<init>(r1)
            java.lang.Class<io.github.jan.supabase.gotrue.user.UserInfo> r1 = io.github.jan.supabase.gotrue.user.UserInfo.class
            kotlin.jvm.internal.m0 r2 = kotlin.jvm.internal.l0.f19747a
            kotlin.reflect.d r1 = r2.b(r1)
            java.lang.String r1 = r1.r()
            r0.append(r1)
            java.lang.String r1 = ". Input: "
            r0.append(r1)
            java.lang.String r1 = ""
            r2 = 0
            java.lang.String r3 = "\n"
            java.lang.String r7 = kotlin.text.u.O(r7, r3, r1, r2)
            r0.append(r7)
            java.lang.String r7 = r0.toString()
            r6.<init>(r7)
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: io.github.jan.supabase.gotrue.admin.AdminApiImpl.retrieveUserById(java.lang.String, d7.d):java.lang.Object");
    }

    /* JADX WARN: Code restructure failed: missing block: B:20:0x006b, code lost:
    
        if (r8 == r4) goto L21;
     */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    @Override // io.github.jan.supabase.gotrue.admin.AdminApi
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.Object retrieveUsers(java.lang.Integer r6, java.lang.Integer r7, d7.d<? super java.util.List<io.github.jan.supabase.gotrue.user.UserInfo>> r8) {
        /*
            r5 = this;
            boolean r0 = r8 instanceof io.github.jan.supabase.gotrue.admin.AdminApiImpl.C16971
            if (r0 == 0) goto L13
            r0 = r8
            io.github.jan.supabase.gotrue.admin.AdminApiImpl$retrieveUsers$1 r0 = (io.github.jan.supabase.gotrue.admin.AdminApiImpl.C16971) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            io.github.jan.supabase.gotrue.admin.AdminApiImpl$retrieveUsers$1 r0 = new io.github.jan.supabase.gotrue.admin.AdminApiImpl$retrieveUsers$1
            r0.<init>(r8)
        L18:
            java.lang.Object r8 = r0.result
            int r1 = r0.label
            r2 = 2
            r3 = 1
            e7.a r4 = e7.a.f15033i
            if (r1 == 0) goto L36
            if (r1 == r3) goto L32
            if (r1 != r2) goto L2a
            k2.c.G(r8)
            goto L6e
        L2a:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r7)
            throw r6
        L32:
            k2.c.G(r8)
            goto L4b
        L36:
            k2.c.G(r8)
            io.github.jan.supabase.gotrue.AuthenticatedSupabaseApi r8 = r5.api
            io.github.jan.supabase.gotrue.admin.AdminApiImpl$retrieveUsers$$inlined$get$1 r1 = new io.github.jan.supabase.gotrue.admin.AdminApiImpl$retrieveUsers$$inlined$get$1
            r1.<init>(r6, r7)
            r0.label = r3
            java.lang.String r6 = "admin/users"
            java.lang.Object r8 = r8.request(r6, r1, r0)
            if (r8 != r4) goto L4b
            goto L6d
        L4b:
            io.ktor.client.statement.HttpResponse r8 = (io.ktor.client.statement.HttpResponse) r8
            io.ktor.client.call.HttpClientCall r6 = r8.getCall()
            java.lang.Class<db.b0> r7 = db.b0.class
            kotlin.reflect.q r8 = kotlin.jvm.internal.l0.a(r7)
            java.lang.reflect.Type r1 = kotlin.reflect.b0.t(r8)
            kotlin.jvm.internal.m0 r3 = kotlin.jvm.internal.l0.f19747a
            kotlin.reflect.d r7 = r3.b(r7)
            io.ktor.util.reflect.TypeInfo r7 = io.ktor.util.reflect.TypeInfoJvmKt.typeInfoImpl(r1, r7, r8)
            r0.label = r2
            java.lang.Object r8 = r6.bodyNullable(r7, r0)
            if (r8 != r4) goto L6e
        L6d:
            return r4
        L6e:
            if (r8 == 0) goto Laf
            db.b0 r8 = (db.b0) r8
            db.b r6 = io.github.jan.supabase.UtilsKt.getSupabaseJson()
            java.lang.String r7 = "users"
            java.lang.Object r7 = r8.get(r7)
            db.m r7 = (db.m) r7
            if (r7 == 0) goto L97
            r6.getClass()
            cb.d r8 = new cb.d
            io.github.jan.supabase.gotrue.user.UserInfo$Companion r0 = io.github.jan.supabase.gotrue.user.UserInfo.INSTANCE
            ya.h r0 = r0.serializer()
            r1 = 0
            r8.<init>(r0, r1)
            java.lang.Object r6 = r6.f(r8, r7)
            java.util.List r6 = (java.util.List) r6
            return r6
        L97:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.StringBuilder r7 = new java.lang.StringBuilder
            java.lang.String r0 = "Didn't get users json field on method retrieveUsers. Full body: "
            r7.<init>(r0)
            r7.append(r8)
            java.lang.String r7 = r7.toString()
            java.lang.String r7 = r7.toString()
            r6.<init>(r7)
            throw r6
        Laf:
            java.lang.NullPointerException r6 = new java.lang.NullPointerException
            java.lang.String r7 = "null cannot be cast to non-null type kotlinx.serialization.json.JsonObject"
            r6.<init>(r7)
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: io.github.jan.supabase.gotrue.admin.AdminApiImpl.retrieveUsers(java.lang.Integer, java.lang.Integer, d7.d):java.lang.Object");
    }

    @Override // io.github.jan.supabase.gotrue.admin.AdminApi
    public Object signOut(String str, SignOutScope signOutScope, d<? super t0> dVar) {
        Object objRequest = this.api.request("logout", new AdminApiImpl$signOut$$inlined$post$1(signOutScope, str), dVar);
        return objRequest == a.f15033i ? objRequest : t0.f22605a;
    }

    /* JADX WARN: Code restructure failed: missing block: B:20:0x007c, code lost:
    
        if (r1 == r6) goto L21;
     */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0017  */
    @Override // io.github.jan.supabase.gotrue.admin.AdminApi
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.Object updateUserById(java.lang.String r20, r7.l<? super io.github.jan.supabase.gotrue.admin.AdminUserUpdateBuilder, x6.t0> r21, d7.d<? super io.github.jan.supabase.gotrue.user.UserInfo> r22) throws io.github.jan.supabase.exceptions.SupabaseEncodingException {
        /*
            r19 = this;
            r0 = r19
            r1 = r22
            boolean r2 = r1 instanceof io.github.jan.supabase.gotrue.admin.AdminApiImpl.C16981
            if (r2 == 0) goto L17
            r2 = r1
            io.github.jan.supabase.gotrue.admin.AdminApiImpl$updateUserById$1 r2 = (io.github.jan.supabase.gotrue.admin.AdminApiImpl.C16981) r2
            int r3 = r2.label
            r4 = -2147483648(0xffffffff80000000, float:-0.0)
            r5 = r3 & r4
            if (r5 == 0) goto L17
            int r3 = r3 - r4
            r2.label = r3
            goto L1c
        L17:
            io.github.jan.supabase.gotrue.admin.AdminApiImpl$updateUserById$1 r2 = new io.github.jan.supabase.gotrue.admin.AdminApiImpl$updateUserById$1
            r2.<init>(r1)
        L1c:
            java.lang.Object r1 = r2.result
            int r3 = r2.label
            r4 = 2
            r5 = 1
            e7.a r6 = e7.a.f15033i
            if (r3 == 0) goto L3a
            if (r3 == r5) goto L36
            if (r3 != r4) goto L2e
            k2.c.G(r1)
            goto L7f
        L2e:
            java.lang.IllegalStateException r1 = new java.lang.IllegalStateException
            java.lang.String r2 = "call to 'resume' before 'invoke' with coroutine"
            r1.<init>(r2)
            throw r1
        L36:
            k2.c.G(r1)
            goto L73
        L3a:
            k2.c.G(r1)
            io.github.jan.supabase.gotrue.admin.AdminUserUpdateBuilder r7 = new io.github.jan.supabase.gotrue.admin.AdminUserUpdateBuilder
            r17 = 511(0x1ff, float:7.16E-43)
            r18 = 0
            r8 = 0
            r9 = 0
            r10 = 0
            r11 = 0
            r12 = 0
            r13 = 0
            r14 = 0
            r15 = 0
            r16 = 0
            r7.<init>(r8, r9, r10, r11, r12, r13, r14, r15, r16, r17, r18)
            r1 = r21
            r1.invoke(r7)
            io.github.jan.supabase.gotrue.AuthenticatedSupabaseApi r1 = r0.api
            java.lang.String r3 = "admin/users/"
            r8 = r20
            java.lang.String r3 = androidx.compose.material3.d.C(r3, r8)
            io.ktor.http.ContentType$Application r8 = io.ktor.http.ContentType.Application.INSTANCE
            io.ktor.http.ContentType r8 = r8.getJson()
            io.github.jan.supabase.gotrue.admin.AdminApiImpl$updateUserById$$inlined$putJson$default$1 r9 = new io.github.jan.supabase.gotrue.admin.AdminApiImpl$updateUserById$$inlined$putJson$default$1
            r9.<init>(r8, r7)
            r2.label = r5
            java.lang.Object r1 = r1.request(r3, r9, r2)
            if (r1 != r6) goto L73
            goto L7e
        L73:
            io.ktor.client.statement.HttpResponse r1 = (io.ktor.client.statement.HttpResponse) r1
            r2.label = r4
            r3 = 0
            java.lang.Object r1 = io.ktor.client.statement.HttpResponseKt.bodyAsText$default(r1, r3, r2, r5, r3)
            if (r1 != r6) goto L7f
        L7e:
            return r6
        L7f:
            java.lang.String r1 = (java.lang.String) r1
            db.b r2 = io.github.jan.supabase.UtilsKt.getSupabaseJson()     // Catch: kotlinx.serialization.MissingFieldException -> L93
            r2.getClass()     // Catch: kotlinx.serialization.MissingFieldException -> L93
            io.github.jan.supabase.gotrue.user.UserInfo$Companion r3 = io.github.jan.supabase.gotrue.user.UserInfo.INSTANCE     // Catch: kotlinx.serialization.MissingFieldException -> L93
            ya.h r3 = r3.serializer()     // Catch: kotlinx.serialization.MissingFieldException -> L93
            java.lang.Object r1 = r2.c(r1, r3)     // Catch: kotlinx.serialization.MissingFieldException -> L93
            return r1
        L93:
            io.github.jan.supabase.exceptions.SupabaseEncodingException r2 = new io.github.jan.supabase.exceptions.SupabaseEncodingException
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            java.lang.String r4 = "Couldn't decode payload as "
            r3.<init>(r4)
            java.lang.Class<io.github.jan.supabase.gotrue.user.UserInfo> r4 = io.github.jan.supabase.gotrue.user.UserInfo.class
            kotlin.jvm.internal.m0 r5 = kotlin.jvm.internal.l0.f19747a
            kotlin.reflect.d r4 = r5.b(r4)
            java.lang.String r4 = r4.r()
            r3.append(r4)
            java.lang.String r4 = ". Input: "
            r3.append(r4)
            java.lang.String r4 = ""
            r5 = 0
            java.lang.String r6 = "\n"
            java.lang.String r1 = kotlin.text.u.O(r1, r6, r4, r5)
            r3.append(r1)
            java.lang.String r1 = r3.toString()
            r2.<init>(r1)
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: io.github.jan.supabase.gotrue.admin.AdminApiImpl.updateUserById(java.lang.String, r7.l, d7.d):java.lang.Object");
    }
}
