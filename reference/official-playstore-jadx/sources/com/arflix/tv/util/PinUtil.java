package com.arflix.tv.util;

import androidx.compose.foundation.c;
import j$.util.Base64;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.List;
import kotlin.Metadata;
import kotlin.text.o;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\u0012\n\u0002\b\u0002\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bJ\u000e\u0010\f\u001a\u00020\u000b2\u0006\u0010\r\u001a\u00020\u000bJ\u000e\u0010\u000e\u001a\u00020\u000b2\u0006\u0010\n\u001a\u00020\u000bJ\u0018\u0010\u000f\u001a\u00020\t2\u0006\u0010\u0010\u001a\u00020\u000b2\b\u0010\u0011\u001a\u0004\u0018\u00010\u000bJ\u0018\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00132\u0006\u0010\n\u001a\u00020\u000bH\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0015"}, d2 = {"Lcom/arflix/tv/util/PinUtil;", "", "<init>", "()V", "MIN_LENGTH", "", "MAX_LENGTH", "SALT_LENGTH", "isValidPin", "", "pin", "", "formatPinInput", "input", "hashPin", "verifyPin", "inputPin", "storedHashedPin", "computeHash", "", "salt", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class PinUtil {
    public static final int $stable = 0;
    public static final PinUtil INSTANCE = new PinUtil();
    private static final int MAX_LENGTH = 5;
    private static final int MIN_LENGTH = 4;
    private static final int SALT_LENGTH = 16;

    private PinUtil() {
    }

    private final byte[] computeHash(byte[] salt, String pin) throws NoSuchAlgorithmException {
        MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
        messageDigest.update(salt);
        return messageDigest.digest(pin.getBytes(kotlin.text.a.f19924a));
    }

    public final String formatPinInput(String input) throws IOException {
        StringBuilder sb2 = new StringBuilder();
        int length = input.length();
        for (int i10 = 0; i10 < length; i10++) {
            char cCharAt = input.charAt(i10);
            if (Character.isDigit(cCharAt)) {
                sb2.append(cCharAt);
            }
        }
        return o.I0(5, sb2.toString());
    }

    public final String hashPin(String pin) throws NoSuchAlgorithmException {
        if (!isValidPin(pin)) {
            throw new IllegalArgumentException("Invalid PIN");
        }
        byte[] bArr = new byte[16];
        new SecureRandom().nextBytes(bArr);
        return c.t(Base64.getEncoder().encodeToString(bArr), "$", Base64.getEncoder().encodeToString(computeHash(bArr, pin)));
    }

    public final boolean isValidPin(String pin) {
        int length = pin.length();
        if (4 <= length && length < 6) {
            for (int i10 = 0; i10 < pin.length(); i10++) {
                if (Character.isDigit(pin.charAt(i10))) {
                }
            }
            return true;
        }
        return false;
    }

    public final boolean verifyPin(String inputPin, String storedHashedPin) {
        if (storedHashedPin != null && isValidPin(inputPin)) {
            try {
                List listY0 = o.y0(storedHashedPin, new String[]{"$"}, 0, 6);
                if (listY0.size() == 2) {
                    byte[] bArrDecode = Base64.getDecoder().decode((String) listY0.get(0));
                    return Arrays.equals(computeHash(bArrDecode, inputPin), Base64.getDecoder().decode((String) listY0.get(1)));
                }
            } catch (IllegalArgumentException unused) {
            }
        }
        return false;
    }
}
