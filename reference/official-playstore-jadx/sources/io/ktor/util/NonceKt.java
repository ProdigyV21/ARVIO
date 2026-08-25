package io.ktor.util;

import io.ktor.http.ContentDisposition;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Iterator;
import java.util.List;
import ka.j0;
import ka.j2;
import ka.m0;
import ka.n1;
import ka.v1;
import ka.x0;
import kotlin.Metadata;
import kotlin.collections.x;
import m2.f0;
import ma.j;
import t7.a;
import vc.c;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u001a\u000f\u0010\u0001\u001a\u00020\u0000H\u0000¢\u0006\u0004\b\u0001\u0010\u0002\u001a\u000f\u0010\u0004\u001a\u00020\u0003H\u0002¢\u0006\u0004\b\u0004\u0010\u0005\u001a\u001d\u0010\b\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0006H\u0002¢\u0006\u0004\b\b\u0010\t\"\u0014\u0010\n\u001a\u00020\u00068\u0002X\u0082T¢\u0006\u0006\n\u0004\b\n\u0010\u000b\"\u001a\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00060\f8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\r\u0010\u000e\"\u0014\u0010\u0010\u001a\u00020\u000f8\u0002X\u0082T¢\u0006\u0006\n\u0004\b\u0010\u0010\u0011\"\u0014\u0010\u0012\u001a\u00020\u000f8\u0002X\u0082T¢\u0006\u0006\n\u0004\b\u0012\u0010\u0011\"\u0014\u0010\u0013\u001a\u00020\u000f8\u0002X\u0082T¢\u0006\u0006\n\u0004\b\u0013\u0010\u0011\" \u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00060\u00148\u0000X\u0080\u0004¢\u0006\f\n\u0004\b\u0015\u0010\u0016\u001a\u0004\b\u0017\u0010\u0018\"\u0014\u0010\u001a\u001a\u00020\u00198\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u001a\u0010\u001b\"\u001a\u0010\u001d\u001a\u00020\u001c8\u0002X\u0082\u0004¢\u0006\f\n\u0004\b\u001d\u0010\u001e\u0012\u0004\b\u001f\u0010\u0002¨\u0006 "}, d2 = {"Lx6/t0;", "ensureNonceGeneratorRunning", "()V", "Ljava/security/SecureRandom;", "lookupSecureRandom", "()Ljava/security/SecureRandom;", "", ContentDisposition.Parameters.Name, "getInstanceOrNull", "(Ljava/lang/String;)Ljava/security/SecureRandom;", NonceKt.SHA1PRNG, "Ljava/lang/String;", "", "SECURE_RANDOM_PROVIDERS", "Ljava/util/List;", "", "SECURE_RESEED_PERIOD", "I", "SECURE_NONCE_COUNT", "INSECURE_NONCE_COUNT_FACTOR", "Lma/j;", "seedChannel", "Lma/j;", "getSeedChannel", "()Lma/j;", "Lka/j0;", "NonceGeneratorCoroutineName", "Lka/j0;", "Lka/v1;", "nonceGeneratorJob", "Lka/v1;", "getNonceGeneratorJob$annotations", "ktor-utils"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class NonceKt {
    private static final int INSECURE_NONCE_COUNT_FACTOR = 4;
    private static final j0 NonceGeneratorCoroutineName;
    private static final int SECURE_NONCE_COUNT = 8;
    private static final int SECURE_RESEED_PERIOD = 30000;
    private static final String SHA1PRNG = "SHA1PRNG";
    private static final v1 nonceGeneratorJob;
    private static final List<String> SECURE_RANDOM_PROVIDERS = a.E("NativePRNGNonBlocking", "WINDOWS-PRNG", "DRBG");
    private static final j<String> seedChannel = f0.a(1024, 0, 6);

    static {
        j0 j0Var = new j0("nonce-generator");
        NonceGeneratorCoroutineName = j0Var;
        nonceGeneratorJob = m0.o(2, x0.f19655d.plus(j2.f19602i).plus(j0Var), n1.f19624i, new NonceKt$nonceGeneratorJob$1(null));
    }

    public static final void ensureNonceGeneratorRunning() {
        nonceGeneratorJob.start();
    }

    private static final SecureRandom getInstanceOrNull(String str) {
        try {
            return str != null ? SecureRandom.getInstance(str) : new SecureRandom();
        } catch (NoSuchAlgorithmException unused) {
            return null;
        }
    }

    public static /* synthetic */ SecureRandom getInstanceOrNull$default(String str, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            str = null;
        }
        return getInstanceOrNull(str);
    }

    private static /* synthetic */ void getNonceGeneratorJob$annotations() {
    }

    public static final j<String> getSeedChannel() {
        return seedChannel;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final SecureRandom lookupSecureRandom() {
        SecureRandom instanceOrNull;
        String property = System.getProperty("io.ktor.random.secure.random.provider");
        if (property != null && (instanceOrNull = getInstanceOrNull(property)) != null) {
            return instanceOrNull;
        }
        Iterator<String> it = SECURE_RANDOM_PROVIDERS.iterator();
        while (it.hasNext()) {
            SecureRandom instanceOrNull2 = getInstanceOrNull(it.next());
            if (instanceOrNull2 != null) {
                return instanceOrNull2;
            }
        }
        c.d("io.ktor.util.random").g("None of the " + x.u0(SECURE_RANDOM_PROVIDERS, ", ", null, null, null, 62) + " found, fallback to default");
        SecureRandom instanceOrNull$default = getInstanceOrNull$default(null, 1, null);
        if (instanceOrNull$default != null) {
            return instanceOrNull$default;
        }
        throw new IllegalStateException("No SecureRandom implementation found");
    }
}
