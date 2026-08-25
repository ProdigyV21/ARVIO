package io.github.jan.supabase.gotrue.mfa;

import com.arflix.tv.ui.theme.AnimationConstants;
import d7.d;
import f7.c;
import f7.e;
import io.github.jan.supabase.gotrue.user.UserMfaFactor;
import io.github.jan.supabase.gotrue.user.UserSession;
import java.util.List;
import kotlin.Metadata;
import na.j;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000`\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0000\bv\u0018\u00002\u00020\u0001JB\u0010\t\u001a\b\u0012\u0004\u0012\u00028\u00000\b\"\u0004\b\u0000\u0010\u00022\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u00032\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0005H¦@¢\u0006\u0004\b\t\u0010\nJ\u0018\u0010\r\u001a\u00020\f2\u0006\u0010\u000b\u001a\u00020\u0005H¦@¢\u0006\u0004\b\r\u0010\u000eJ\u0018\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\u000b\u001a\u00020\u0005H¦@¢\u0006\u0004\b\u0010\u0010\u000eJ*\u0010\u0015\u001a\u00020\u00142\u0006\u0010\u000b\u001a\u00020\u00052\u0006\u0010\u0011\u001a\u00020\u00052\b\b\u0002\u0010\u0013\u001a\u00020\u0012H\u0096@¢\u0006\u0004\b\u0015\u0010\u0016J2\u0010\u0018\u001a\u00020\u00142\u0006\u0010\u000b\u001a\u00020\u00052\u0006\u0010\u0017\u001a\u00020\u00052\u0006\u0010\u0011\u001a\u00020\u00052\b\b\u0002\u0010\u0013\u001a\u00020\u0012H¦@¢\u0006\u0004\b\u0018\u0010\u0019J\u0016\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u001b0\u001aH¦@¢\u0006\u0004\b\u001c\u0010\u001dJ\u000f\u0010\u001f\u001a\u00020\u001eH&¢\u0006\u0004\b\u001f\u0010 R\u001a\u0010\"\u001a\b\u0012\u0004\u0012\u00020\u00120!8&X¦\u0004¢\u0006\u0006\u001a\u0004\b\"\u0010#R\u0014\u0010$\u001a\u00020\u00128VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b$\u0010%R\u001a\u0010(\u001a\b\u0012\u0004\u0012\u00020\u001b0\u001a8&X¦\u0004¢\u0006\u0006\u001a\u0004\b&\u0010'R\u0014\u0010*\u001a\u00020\u00128VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b)\u0010%R\u001a\u0010,\u001a\b\u0012\u0004\u0012\u00020\u00120!8&X¦\u0004¢\u0006\u0006\u001a\u0004\b+\u0010#\u0082\u0001\u0001-¨\u0006."}, d2 = {"Lio/github/jan/supabase/gotrue/mfa/MfaApi;", "", "Response", "Lio/github/jan/supabase/gotrue/mfa/FactorType;", "factorType", "", "issuer", "friendlyName", "Lio/github/jan/supabase/gotrue/mfa/MfaFactor;", "enroll", "(Lio/github/jan/supabase/gotrue/mfa/FactorType;Ljava/lang/String;Ljava/lang/String;Ld7/d;)Ljava/lang/Object;", "factorId", "Lx6/t0;", "unenroll", "(Ljava/lang/String;Ld7/d;)Ljava/lang/Object;", "Lio/github/jan/supabase/gotrue/mfa/MfaChallenge;", "createChallenge", "code", "", "saveSession", "Lio/github/jan/supabase/gotrue/user/UserSession;", "createChallengeAndVerify", "(Ljava/lang/String;Ljava/lang/String;ZLd7/d;)Ljava/lang/Object;", "challengeId", "verifyChallenge", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ZLd7/d;)Ljava/lang/Object;", "", "Lio/github/jan/supabase/gotrue/user/UserMfaFactor;", "retrieveFactorsForCurrentUser", "(Ld7/d;)Ljava/lang/Object;", "Lio/github/jan/supabase/gotrue/mfa/MfaLevel;", "getAuthenticatorAssuranceLevel", "()Lio/github/jan/supabase/gotrue/mfa/MfaLevel;", "Lna/j;", "isMfaEnabledFlow", "()Lna/j;", "isMfaEnabled", "()Z", "getVerifiedFactors", "()Ljava/util/List;", "verifiedFactors", "getLoggedInUsingMfa", "loggedInUsingMfa", "getLoggedInUsingMfaFlow", "loggedInUsingMfaFlow", "Lio/github/jan/supabase/gotrue/mfa/MfaApiImpl;", "gotrue-kt_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public interface MfaApi {

    @Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
    public static final class DefaultImpls {
        /* JADX WARN: Removed duplicated region for block: B:8:0x0014  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public static java.lang.Object createChallengeAndVerify(io.github.jan.supabase.gotrue.mfa.MfaApi r8, java.lang.String r9, java.lang.String r10, boolean r11, d7.d<? super io.github.jan.supabase.gotrue.user.UserSession> r12) {
            /*
                boolean r0 = r12 instanceof io.github.jan.supabase.gotrue.mfa.MfaApi.AnonymousClass1
                if (r0 == 0) goto L14
                r0 = r12
                io.github.jan.supabase.gotrue.mfa.MfaApi$createChallengeAndVerify$1 r0 = (io.github.jan.supabase.gotrue.mfa.MfaApi.AnonymousClass1) r0
                int r1 = r0.label
                r2 = -2147483648(0xffffffff80000000, float:-0.0)
                r3 = r1 & r2
                if (r3 == 0) goto L14
                int r1 = r1 - r2
                r0.label = r1
            L12:
                r6 = r0
                goto L1a
            L14:
                io.github.jan.supabase.gotrue.mfa.MfaApi$createChallengeAndVerify$1 r0 = new io.github.jan.supabase.gotrue.mfa.MfaApi$createChallengeAndVerify$1
                r0.<init>(r12)
                goto L12
            L1a:
                java.lang.Object r12 = r6.result
                int r0 = r6.label
                r1 = 2
                r2 = 1
                e7.a r7 = e7.a.f15033i
                if (r0 == 0) goto L4b
                if (r0 == r2) goto L34
                if (r0 != r1) goto L2c
                k2.c.G(r12)
                return r12
            L2c:
                java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
                java.lang.String r9 = "call to 'resume' before 'invoke' with coroutine"
                r8.<init>(r9)
                throw r8
            L34:
                boolean r11 = r6.Z$0
                java.lang.Object r8 = r6.L$2
                r10 = r8
                java.lang.String r10 = (java.lang.String) r10
                java.lang.Object r8 = r6.L$1
                r9 = r8
                java.lang.String r9 = (java.lang.String) r9
                java.lang.Object r8 = r6.L$0
                io.github.jan.supabase.gotrue.mfa.MfaApi r8 = (io.github.jan.supabase.gotrue.mfa.MfaApi) r8
                k2.c.G(r12)
            L47:
                r2 = r9
                r4 = r10
                r5 = r11
                goto L5f
            L4b:
                k2.c.G(r12)
                r6.L$0 = r8
                r6.L$1 = r9
                r6.L$2 = r10
                r6.Z$0 = r11
                r6.label = r2
                java.lang.Object r12 = r8.createChallenge(r9, r6)
                if (r12 != r7) goto L47
                goto L75
            L5f:
                io.github.jan.supabase.gotrue.mfa.MfaChallenge r12 = (io.github.jan.supabase.gotrue.mfa.MfaChallenge) r12
                java.lang.String r3 = r12.getId()
                r9 = 0
                r6.L$0 = r9
                r6.L$1 = r9
                r6.L$2 = r9
                r6.label = r1
                r1 = r8
                java.lang.Object r8 = r1.verifyChallenge(r2, r3, r4, r5, r6)
                if (r8 != r7) goto L76
            L75:
                return r7
            L76:
                return r8
            */
            throw new UnsupportedOperationException("Method not decompiled: io.github.jan.supabase.gotrue.mfa.MfaApi.DefaultImpls.createChallengeAndVerify(io.github.jan.supabase.gotrue.mfa.MfaApi, java.lang.String, java.lang.String, boolean, d7.d):java.lang.Object");
        }

        public static /* synthetic */ Object createChallengeAndVerify$default(MfaApi mfaApi, String str, String str2, boolean z, d dVar, int i10, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: createChallengeAndVerify");
            }
            if ((i10 & 4) != 0) {
                z = true;
            }
            return mfaApi.createChallengeAndVerify(str, str2, z, dVar);
        }

        public static /* synthetic */ Object enroll$default(MfaApi mfaApi, FactorType factorType, String str, String str2, d dVar, int i10, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: enroll");
            }
            if ((i10 & 2) != 0) {
                str = null;
            }
            if ((i10 & 4) != 0) {
                str2 = null;
            }
            return mfaApi.enroll(factorType, str, str2, dVar);
        }

        public static boolean getLoggedInUsingMfa(MfaApi mfaApi) {
            return mfaApi.getAuthenticatorAssuranceLevel().getCurrent() == AuthenticatorAssuranceLevel.AAL2;
        }

        public static boolean isMfaEnabled(MfaApi mfaApi) {
            return mfaApi.getAuthenticatorAssuranceLevel().getNext() == AuthenticatorAssuranceLevel.AAL2;
        }

        public static /* synthetic */ Object verifyChallenge$default(MfaApi mfaApi, String str, String str2, String str3, boolean z, d dVar, int i10, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: verifyChallenge");
            }
            if ((i10 & 8) != 0) {
                z = true;
            }
            return mfaApi.verifyChallenge(str, str2, str3, z, dVar);
        }
    }

    /* JADX INFO: renamed from: io.github.jan.supabase.gotrue.mfa.MfaApi$createChallengeAndVerify$1, reason: invalid class name */
    @Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
    @e(c = "io.github.jan.supabase.gotrue.mfa.MfaApi$DefaultImpls", f = "MfaApi.kt", l = {AnimationConstants.STAGGER_SECTION, 81}, m = "createChallengeAndVerify")
    public static final class AnonymousClass1 extends c {
        Object L$0;
        Object L$1;
        Object L$2;
        boolean Z$0;
        int label;
        /* synthetic */ Object result;

        public AnonymousClass1(d<? super AnonymousClass1> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return DefaultImpls.createChallengeAndVerify(null, null, null, false, this);
        }
    }

    Object createChallenge(String str, d<? super MfaChallenge> dVar);

    Object createChallengeAndVerify(String str, String str2, boolean z, d<? super UserSession> dVar);

    <Response> Object enroll(FactorType<Response> factorType, String str, String str2, d<? super MfaFactor<Response>> dVar);

    MfaLevel getAuthenticatorAssuranceLevel();

    boolean getLoggedInUsingMfa();

    j<Boolean> getLoggedInUsingMfaFlow();

    List<UserMfaFactor> getVerifiedFactors();

    boolean isMfaEnabled();

    j<Boolean> isMfaEnabledFlow();

    Object retrieveFactorsForCurrentUser(d<? super List<UserMfaFactor>> dVar);

    Object unenroll(String str, d<? super t0> dVar);

    Object verifyChallenge(String str, String str2, String str3, boolean z, d<? super UserSession> dVar);
}
