package io.ktor.util;

import com.google.common.util.concurrent.r0;
import d7.d;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.concurrent.TimeUnit;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import kotlin.Metadata;
import kotlin.jvm.internal.h;
import kotlin.jvm.internal.r;
import kotlin.text.o;
import r7.a;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0012\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u000e\n\u0002\u0010\b\n\u0002\b\u0003\u0018\u00002\u00020\u0001B3\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0004\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0006\u0012\u000e\b\u0002\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00040\b¢\u0006\u0004\b\n\u0010\u000bB5\b\u0016\u0012\u0006\u0010\r\u001a\u00020\f\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0004\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0006\u0012\u000e\b\u0002\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00040\b¢\u0006\u0004\b\n\u0010\u000eJ\u0013\u0010\u000f\u001a\u00020\u0004H\u0096@ø\u0001\u0000¢\u0006\u0004\b\u000f\u0010\u0010J\u001b\u0010\u0013\u001a\u00020\u00122\u0006\u0010\u0011\u001a\u00020\u0004H\u0096@ø\u0001\u0000¢\u0006\u0004\b\u0013\u0010\u0014R\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\u0015\u001a\u0004\b\u0016\u0010\u0017R\u0017\u0010\u0005\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b\u0005\u0010\u0018\u001a\u0004\b\u0019\u0010\u001aR\u0017\u0010\u0007\u001a\u00020\u00068\u0006¢\u0006\f\n\u0004\b\u0007\u0010\u001b\u001a\u0004\b\u001c\u0010\u001dR\u001d\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00040\b8\u0006¢\u0006\f\n\u0004\b\t\u0010\u001e\u001a\u0004\b\u001f\u0010 R\u0014\u0010\"\u001a\u00020!8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\"\u0010#\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006$"}, d2 = {"Lio/ktor/util/StatelessHmacNonceManager;", "Lio/ktor/util/NonceManager;", "Ljavax/crypto/spec/SecretKeySpec;", "keySpec", "", "algorithm", "", "timeoutMillis", "Lkotlin/Function0;", "nonceGenerator", "<init>", "(Ljavax/crypto/spec/SecretKeySpec;Ljava/lang/String;JLr7/a;)V", "", "key", "([BLjava/lang/String;JLr7/a;)V", "newNonce", "(Ld7/d;)Ljava/lang/Object;", "nonce", "", "verifyNonce", "(Ljava/lang/String;Ld7/d;)Ljava/lang/Object;", "Ljavax/crypto/spec/SecretKeySpec;", "getKeySpec", "()Ljavax/crypto/spec/SecretKeySpec;", "Ljava/lang/String;", "getAlgorithm", "()Ljava/lang/String;", "J", "getTimeoutMillis", "()J", "Lr7/a;", "getNonceGenerator", "()Lr7/a;", "", "macLength", "I", "ktor-utils"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class StatelessHmacNonceManager implements NonceManager {
    private final String algorithm;
    private final SecretKeySpec keySpec;
    private final int macLength;
    private final a<String> nonceGenerator;
    private final long timeoutMillis;

    /* JADX INFO: renamed from: io.ktor.util.StatelessHmacNonceManager$1, reason: invalid class name */
    @Metadata(d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u000e\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class AnonymousClass1 extends r implements a<String> {
        public static final AnonymousClass1 INSTANCE = new AnonymousClass1();

        public AnonymousClass1() {
            super(0);
        }

        @Override // r7.a
        public final String invoke() {
            return CryptoKt.generateNonce();
        }
    }

    /* JADX INFO: renamed from: io.ktor.util.StatelessHmacNonceManager$2, reason: invalid class name */
    @Metadata(d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u000e\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class AnonymousClass2 extends r implements a<String> {
        public static final AnonymousClass2 INSTANCE = new AnonymousClass2();

        public AnonymousClass2() {
            super(0);
        }

        @Override // r7.a
        public final String invoke() {
            return CryptoKt.generateNonce();
        }
    }

    public StatelessHmacNonceManager(SecretKeySpec secretKeySpec, String str, long j10, a<String> aVar) throws NoSuchAlgorithmException, InvalidKeyException {
        this.keySpec = secretKeySpec;
        this.algorithm = str;
        this.timeoutMillis = j10;
        this.nonceGenerator = aVar;
        Mac mac = Mac.getInstance(str);
        mac.init(secretKeySpec);
        this.macLength = mac.getMacLength();
    }

    public final String getAlgorithm() {
        return this.algorithm;
    }

    public final SecretKeySpec getKeySpec() {
        return this.keySpec;
    }

    public final a<String> getNonceGenerator() {
        return this.nonceGenerator;
    }

    public final long getTimeoutMillis() {
        return this.timeoutMillis;
    }

    @Override // io.ktor.util.NonceManager
    public Object newNonce(d<? super String> dVar) throws NoSuchAlgorithmException, InvalidKeyException {
        String str = (String) this.nonceGenerator.invoke();
        long jNanoTime = System.nanoTime();
        r0.c(16);
        String strO0 = o.o0(16, Long.toString(jNanoTime, 16));
        Mac mac = Mac.getInstance(this.algorithm);
        mac.init(this.keySpec);
        mac.update((str + ':' + strO0).getBytes(kotlin.text.a.f19926c));
        return str + '+' + strO0 + '+' + CryptoKt.hex(mac.doFinal());
    }

    @Override // io.ktor.util.NonceManager
    public Object verifyNonce(String str, d<? super Boolean> dVar) throws NoSuchAlgorithmException, InvalidKeyException {
        List listX0 = o.x0(str, new char[]{'+'}, 6);
        if (listX0.size() != 3) {
            return Boolean.FALSE;
        }
        String str2 = (String) listX0.get(0);
        String str3 = (String) listX0.get(1);
        String str4 = (String) listX0.get(2);
        if (str2.length() < 8) {
            return Boolean.FALSE;
        }
        if (str4.length() != this.macLength * 2) {
            return Boolean.FALSE;
        }
        if (str3.length() != 16) {
            return Boolean.FALSE;
        }
        r0.c(16);
        if (TimeUnit.MILLISECONDS.toNanos(this.timeoutMillis) + Long.parseLong(str3, 16) < System.nanoTime()) {
            return Boolean.FALSE;
        }
        Mac mac = Mac.getInstance(this.algorithm);
        mac.init(this.keySpec);
        mac.update((str2 + ':' + str3).getBytes(kotlin.text.a.f19926c));
        String strHex = CryptoKt.hex(mac.doFinal());
        int iMin = Math.min(strHex.length(), str4.length());
        int i10 = 0;
        for (int i11 = 0; i11 < iMin; i11++) {
            if (strHex.charAt(i11) == str4.charAt(i11)) {
                i10++;
            }
        }
        return Boolean.valueOf(i10 == this.macLength * 2);
    }

    public /* synthetic */ StatelessHmacNonceManager(SecretKeySpec secretKeySpec, String str, long j10, a aVar, int i10, h hVar) {
        this(secretKeySpec, (i10 & 2) != 0 ? "HmacSHA256" : str, (i10 & 4) != 0 ? 60000L : j10, (a<String>) ((i10 & 8) != 0 ? AnonymousClass1.INSTANCE : aVar));
    }

    public /* synthetic */ StatelessHmacNonceManager(byte[] bArr, String str, long j10, a aVar, int i10, h hVar) {
        this(bArr, (i10 & 2) != 0 ? "HmacSHA256" : str, (i10 & 4) != 0 ? 60000L : j10, (a<String>) ((i10 & 8) != 0 ? AnonymousClass2.INSTANCE : aVar));
    }

    public StatelessHmacNonceManager(byte[] bArr, String str, long j10, a<String> aVar) {
        this(new SecretKeySpec(bArr, str), str, j10, aVar);
    }
}
