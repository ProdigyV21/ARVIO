package io.ktor.util;

import d7.k;
import io.ktor.http.ContentDisposition;
import io.ktor.http.auth.AuthScheme;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import ka.m0;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import kotlin.text.a;
import ma.o;
import r7.l;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0012\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0007\u001aD\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\u0000\u0012\u0004\u0012\u00020\u00070\u00022\u0006\u0010\u0001\u001a\u00020\u00002!\u0010\u0006\u001a\u001d\u0012\u0013\u0012\u00110\u0000¢\u0006\f\b\u0003\u0012\b\b\u0004\u0012\u0004\b\b(\u0005\u0012\u0004\u0012\u00020\u00000\u0002¢\u0006\u0004\b\b\u0010\t\u001a3\u0010\r\u001a\u00020\u00072\u0006\u0010\n\u001a\u00020\u00002\u0006\u0010\u0001\u001a\u00020\u00002\u0012\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\u0000\u0012\u0004\u0012\u00020\u00000\u0002H\u0002¢\u0006\u0004\b\u000b\u0010\f\u001a\u0015\u0010\u000f\u001a\u00020\u00072\u0006\u0010\u000e\u001a\u00020\u0007¢\u0006\u0004\b\u000f\u0010\u0010\u001a\u0015\u0010\u0012\u001a\u00020\u00112\u0006\u0010\u0004\u001a\u00020\u0000¢\u0006\u0004\b\u0012\u0010\u0013\u001a\r\u0010\u0014\u001a\u00020\u0000¢\u0006\u0004\b\u0014\u0010\u0015\u001a\u000f\u0010\u0017\u001a\u00020\u0000H\u0002¢\u0006\u0004\b\u0016\u0010\u0015¨\u0006\u0018"}, d2 = {"", "algorithm", "Lkotlin/Function1;", "Lx6/y;", ContentDisposition.Parameters.Name, "value", "salt", "", "getDigestFunction", "(Ljava/lang/String;Lr7/l;)Lr7/l;", "text", "getDigest$CryptoKt__CryptoJvmKt", "(Ljava/lang/String;Ljava/lang/String;Lr7/l;)[B", "getDigest", "bytes", "sha1", "([B)[B", "Lio/ktor/util/Digest;", AuthScheme.Digest, "(Ljava/lang/String;)Lio/ktor/util/Digest;", "generateNonce", "()Ljava/lang/String;", "generateNonceBlocking$CryptoKt__CryptoJvmKt", "generateNonceBlocking", "ktor-utils"}, k = 5, mv = {1, 8, 0}, xi = 48, xs = "io/ktor/util/CryptoKt")
final /* synthetic */ class CryptoKt__CryptoJvmKt {

    /* JADX INFO: renamed from: io.ktor.util.CryptoKt__CryptoJvmKt$getDigestFunction$1, reason: invalid class name */
    @Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\u000e\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "e", "", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class AnonymousClass1 extends r implements l<String, byte[]> {
        final /* synthetic */ String $algorithm;
        final /* synthetic */ l<String, String> $salt;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public AnonymousClass1(String str, l<? super String, String> lVar) {
            super(1);
            this.$algorithm = str;
            this.$salt = lVar;
        }

        @Override // r7.l
        public final byte[] invoke(String str) {
            return CryptoKt__CryptoJvmKt.getDigest$CryptoKt__CryptoJvmKt(str, this.$algorithm, this.$salt);
        }
    }

    public static final Digest Digest(String str) {
        return DigestImpl.m6720boximpl(DigestImpl.m6722constructorimpl(MessageDigest.getInstance(str)));
    }

    public static final String generateNonce() {
        String str = (String) o.a(NonceKt.getSeedChannel().b());
        return str != null ? str : generateNonceBlocking$CryptoKt__CryptoJvmKt();
    }

    private static final String generateNonceBlocking$CryptoKt__CryptoJvmKt() {
        NonceKt.ensureNonceGeneratorRunning();
        return (String) m0.s(k.f14688i, new CryptoKt__CryptoJvmKt$generateNonceBlocking$1(null));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final byte[] getDigest$CryptoKt__CryptoJvmKt(String str, String str2, l<? super String, String> lVar) throws NoSuchAlgorithmException {
        MessageDigest messageDigest = MessageDigest.getInstance(str2);
        String str3 = (String) lVar.invoke(str);
        Charset charset = a.f19924a;
        messageDigest.update(str3.getBytes(charset));
        return messageDigest.digest(str.getBytes(charset));
    }

    public static final l<String, byte[]> getDigestFunction(String str, l<? super String, String> lVar) {
        return new AnonymousClass1(str, lVar);
    }

    public static final byte[] sha1(byte[] bArr) {
        return MessageDigest.getInstance("SHA1").digest(bArr);
    }
}
