package com.arflix.tv.util;

import android.security.keystore.KeyGenParameterSpec;
import android.util.Base64;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.InvalidAlgorithmParameterException;
import java.security.Key;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.util.List;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.GCMParameterSpec;
import kotlin.Metadata;
import kotlin.text.o;
import kotlin.text.u;
import x6.c0;
import x6.d0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u000b\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\u0005J\u0016\u0010\u000e\u001a\u00020\u00052\u0006\u0010\u000f\u001a\u00020\u00052\u0006\u0010\u0010\u001a\u00020\u0005J\u001a\u0010\u0011\u001a\u0004\u0018\u00010\u00052\b\u0010\r\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0010\u001a\u00020\u0005J\u0010\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0010\u001a\u00020\u0005H\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\bX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0014"}, d2 = {"Lcom/arflix/tv/util/SecureStorage;", "", "<init>", "()V", "ANDROID_KEYSTORE", "", "TRANSFORMATION", "GCM_TAG_LENGTH", "", "IV_LENGTH", "PREFIX", "isEncrypted", "", "value", "encrypt", "plainText", "alias", "decrypt", "getOrCreateKey", "Ljavax/crypto/SecretKey;", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class SecureStorage {
    public static final int $stable = 0;
    private static final String ANDROID_KEYSTORE = "AndroidKeyStore";
    private static final int GCM_TAG_LENGTH = 128;
    public static final SecureStorage INSTANCE = new SecureStorage();
    private static final int IV_LENGTH = 12;
    private static final String PREFIX = "enc:v1:";
    private static final String TRANSFORMATION = "AES/GCM/NoPadding";

    private SecureStorage() {
    }

    private final SecretKey getOrCreateKey(String alias) throws NoSuchAlgorithmException, UnrecoverableKeyException, IOException, KeyStoreException, CertificateException, NoSuchProviderException, InvalidAlgorithmParameterException {
        KeyStore keyStore = KeyStore.getInstance("AndroidKeyStore");
        keyStore.load(null);
        Key key = keyStore.getKey(alias, null);
        SecretKey secretKey = key instanceof SecretKey ? (SecretKey) key : null;
        if (secretKey != null) {
            return secretKey;
        }
        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES", "AndroidKeyStore");
        keyGenerator.init(new KeyGenParameterSpec.Builder(alias, 3).setBlockModes("GCM").setEncryptionPaddings("NoPadding").build());
        return keyGenerator.generateKey();
    }

    public final String decrypt(String value, String alias) {
        Object c0Var;
        if (value == null || o.h0(value)) {
            return null;
        }
        if (!u.P(value, PREFIX, false)) {
            return value;
        }
        try {
            List listX0 = o.x0(o.r0(value, PREFIX), new char[]{':'}, 2);
            if (listX0.size() != 2) {
                return null;
            }
            byte[] bArrDecode = Base64.decode((String) listX0.get(0), 2);
            byte[] bArrDecode2 = Base64.decode((String) listX0.get(1), 2);
            if (bArrDecode.length != 12) {
                return null;
            }
            Cipher cipher = Cipher.getInstance(TRANSFORMATION);
            cipher.init(2, getOrCreateKey(alias), new GCMParameterSpec(128, bArrDecode));
            c0Var = new String(cipher.doFinal(bArrDecode2), StandardCharsets.UTF_8);
        } catch (Throwable th) {
            c0Var = new c0(th);
        }
        return (String) (c0Var instanceof c0 ? null : c0Var);
    }

    public final String encrypt(String plainText, String alias) {
        Object c0Var;
        if (o.h0(plainText) || u.P(plainText, PREFIX, false)) {
            return plainText;
        }
        try {
            Cipher cipher = Cipher.getInstance(TRANSFORMATION);
            cipher.init(1, getOrCreateKey(alias));
            byte[] iv = cipher.getIV();
            byte[] bArrDoFinal = cipher.doFinal(plainText.getBytes(StandardCharsets.UTF_8));
            c0Var = PREFIX + Base64.encodeToString(iv, 2) + ":" + Base64.encodeToString(bArrDoFinal, 2);
        } catch (Throwable th) {
            c0Var = new c0(th);
        }
        Object obj = plainText;
        if (d0.a(c0Var) == null) {
            obj = c0Var;
        }
        return (String) obj;
    }

    public final boolean isEncrypted(String value) {
        return value != null && u.P(value, PREFIX, false);
    }
}
