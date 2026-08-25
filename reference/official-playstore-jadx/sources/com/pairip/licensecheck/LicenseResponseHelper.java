package com.pairip.licensecheck;

import android.os.Bundle;
import android.util.Base64;
import java.nio.charset.Charset;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.Signature;
import java.security.SignatureException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class LicenseResponseHelper {
    private static final String KEY_FACTORY_ALGORITHM = "RSA";
    private static final String PAYLOAD_LICENSE_DATA = "LICENSE_DATA";
    private static final String PAYLOAD_PACKAGE_NAME = "packageName";
    private static final String PAYLOAD_REPEATED_CHECK_DURATION_TO_RETRY_MILLIS = "repeatedCheckDurationToRetryMillis";
    private static final String PAYLOAD_REPEATED_CHECK_TIME_TO_RETRY_MILLIS = "repeatedCheckTimeToRetryMillis";
    private static final String SIGNATURE_ALGORITHM = "SHA256withRSA";
    private static final Charset UTF_8 = Charset.forName("UTF-8");

    public static void validateResponse(Bundle responsePayload, String packageName) throws LicenseCheckException {
        try {
            String[] jwsPartsForLicenseData = getJwsPartsForLicenseData(responsePayload);
            JSONObject jSONObjectBase64ToJson = base64ToJson(jwsPartsForLicenseData[0]);
            JSONObject jSONObjectBase64ToJson2 = base64ToJson(jwsPartsForLicenseData[1]);
            String str = jwsPartsForLicenseData[2];
            String str2 = jwsPartsForLicenseData[0] + "." + jwsPartsForLicenseData[1];
            if (!jSONObjectBase64ToJson.getString("alg").equals("RS256")) {
                throw new LicenseCheckException("Response must be signed with RS256 algorithm.");
            }
            verifySignature(str2, str, SIGNATURE_ALGORITHM, getPublicKey());
            if (!jSONObjectBase64ToJson2.getString(PAYLOAD_PACKAGE_NAME).equals(packageName)) {
                throw new LicenseCheckException("Package name doesn't match.");
            }
        } catch (JSONException e5) {
            throw new LicenseCheckException("Could not decode json", e5);
        }
    }

    public static RepeatedCheckMetadata getRepeatedCheckMetadata(Bundle responsePayload) throws LicenseCheckException {
        try {
            JSONObject jSONObjectBase64ToJson = base64ToJson(getJwsPartsForLicenseData(responsePayload)[1]);
            if (jSONObjectBase64ToJson.has(PAYLOAD_REPEATED_CHECK_DURATION_TO_RETRY_MILLIS) && jSONObjectBase64ToJson.has(PAYLOAD_REPEATED_CHECK_TIME_TO_RETRY_MILLIS)) {
                return new RepeatedCheckMetadata(jSONObjectBase64ToJson.getLong(PAYLOAD_REPEATED_CHECK_DURATION_TO_RETRY_MILLIS), jSONObjectBase64ToJson.getLong(PAYLOAD_REPEATED_CHECK_TIME_TO_RETRY_MILLIS));
            }
            return null;
        } catch (IllegalArgumentException e5) {
            throw new LicenseCheckException("Invalid repeated check payload", e5);
        } catch (JSONException e6) {
            throw new LicenseCheckException("Could not decode json", e6);
        }
    }

    private static String[] getJwsPartsForLicenseData(Bundle responsePayload) throws LicenseCheckException {
        String string = responsePayload.getString(PAYLOAD_LICENSE_DATA);
        if (string == null) {
            throw new LicenseCheckException("Invalid response");
        }
        String[] strArrSplit = string.split("\\.", -1);
        if (strArrSplit.length == 3) {
            return strArrSplit;
        }
        throw new LicenseCheckException("Invalid response");
    }

    private static JSONObject base64ToJson(String input) throws LicenseCheckException {
        try {
            return new JSONObject(new String(Base64.decode(input, 8), UTF_8));
        } catch (IllegalArgumentException | JSONException e5) {
            throw new LicenseCheckException("Invalid response", e5);
        }
    }

    private static void verifySignature(String signedData, String signature, String signatureAlgorithm, PublicKey publicKey) throws LicenseCheckException {
        try {
            Signature signature2 = Signature.getInstance(signatureAlgorithm);
            signature2.initVerify(publicKey);
            signature2.update(signedData.getBytes(UTF_8));
            if (signature2.verify(Base64.decode(signature, 8))) {
            } else {
                throw new LicenseCheckException("Signature verification failed.");
            }
        } catch (IllegalArgumentException e5) {
            throw new LicenseCheckException("Could not base64 decode returned signature", e5);
        } catch (InvalidKeyException e6) {
            throw new LicenseCheckException("Could not sign data with the public key", e6);
        } catch (NoSuchAlgorithmException e10) {
            throw new LicenseCheckException(String.format("Could not find %s algorithm on the device", signatureAlgorithm), e10);
        } catch (SignatureException e11) {
            throw new LicenseCheckException("Could not parse returned signature.", e11);
        }
    }

    private static PublicKey getPublicKey() throws LicenseCheckException {
        try {
            return KeyFactory.getInstance(KEY_FACTORY_ALGORITHM).generatePublic(new X509EncodedKeySpec(Base64.decode(LicenseClient.getLicensePubKey(), 0)));
        } catch (IllegalArgumentException e5) {
            throw new LicenseCheckException("Could not decode public key", e5);
        } catch (NoSuchAlgorithmException e6) {
            throw new LicenseCheckException(String.format("%s algorithm not found on device", KEY_FACTORY_ALGORITHM), e6);
        } catch (InvalidKeySpecException e10) {
            throw new LicenseCheckException("Could not create key specification from the public key", e10);
        }
    }

    private LicenseResponseHelper() {
    }
}
