package com.google.firebase.crashlytics.internal.settings;

import a0.c;
import android.text.TextUtils;
import com.google.firebase.crashlytics.internal.Logger;
import com.google.firebase.crashlytics.internal.common.CrashlyticsCore;
import com.google.firebase.crashlytics.internal.network.HttpGetRequest;
import com.google.firebase.crashlytics.internal.network.HttpRequestFactory;
import com.google.firebase.crashlytics.internal.network.HttpResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes4.dex */
class DefaultSettingsSpiCall implements SettingsSpiCall {
    static final String ACCEPT_JSON_VALUE = "application/json";
    static final String ANDROID_CLIENT_TYPE = "android";
    static final String BUILD_VERSION_PARAM = "build_version";
    static final String CRASHLYTICS_USER_AGENT = "Crashlytics Android SDK/";
    static final String DISPLAY_VERSION_PARAM = "display_version";
    static final String HEADER_ACCEPT = "Accept";
    static final String HEADER_CLIENT_TYPE = "X-CRASHLYTICS-API-CLIENT-TYPE";
    static final String HEADER_CLIENT_VERSION = "X-CRASHLYTICS-API-CLIENT-VERSION";
    static final String HEADER_DEVICE_MODEL = "X-CRASHLYTICS-DEVICE-MODEL";
    static final String HEADER_GOOGLE_APP_ID = "X-CRASHLYTICS-GOOGLE-APP-ID";
    static final String HEADER_INSTALLATION_ID = "X-CRASHLYTICS-INSTALLATION-ID";
    static final String HEADER_OS_BUILD_VERSION = "X-CRASHLYTICS-OS-BUILD-VERSION";
    static final String HEADER_OS_DISPLAY_VERSION = "X-CRASHLYTICS-OS-DISPLAY-VERSION";
    static final String HEADER_USER_AGENT = "User-Agent";
    static final String INSTANCE_PARAM = "instance";
    static final String SOURCE_PARAM = "source";
    private final Logger logger;
    private final HttpRequestFactory requestFactory;
    private final String url;

    public DefaultSettingsSpiCall(String str, HttpRequestFactory httpRequestFactory) {
        this(str, httpRequestFactory, Logger.getLogger());
    }

    private HttpGetRequest applyHeadersTo(HttpGetRequest httpGetRequest, SettingsRequest settingsRequest) {
        applyNonNullHeader(httpGetRequest, HEADER_GOOGLE_APP_ID, settingsRequest.googleAppId);
        applyNonNullHeader(httpGetRequest, HEADER_CLIENT_TYPE, "android");
        applyNonNullHeader(httpGetRequest, HEADER_CLIENT_VERSION, CrashlyticsCore.getVersion());
        applyNonNullHeader(httpGetRequest, HEADER_ACCEPT, ACCEPT_JSON_VALUE);
        applyNonNullHeader(httpGetRequest, HEADER_DEVICE_MODEL, settingsRequest.deviceModel);
        applyNonNullHeader(httpGetRequest, HEADER_OS_BUILD_VERSION, settingsRequest.osBuildVersion);
        applyNonNullHeader(httpGetRequest, HEADER_OS_DISPLAY_VERSION, settingsRequest.osDisplayVersion);
        applyNonNullHeader(httpGetRequest, HEADER_INSTALLATION_ID, settingsRequest.installIdProvider.getInstallIds().getCrashlyticsInstallId());
        return httpGetRequest;
    }

    private void applyNonNullHeader(HttpGetRequest httpGetRequest, String str, String str2) {
        if (str2 != null) {
            httpGetRequest.header(str, str2);
        }
    }

    private JSONObject getJsonObjectFrom(String str) {
        try {
            return new JSONObject(str);
        } catch (Exception e5) {
            this.logger.w("Failed to parse settings JSON from " + this.url, e5);
            this.logger.w("Settings response " + str);
            return null;
        }
    }

    private Map<String, String> getQueryParamsFor(SettingsRequest settingsRequest) {
        HashMap map = new HashMap();
        map.put(BUILD_VERSION_PARAM, settingsRequest.buildVersion);
        map.put(DISPLAY_VERSION_PARAM, settingsRequest.displayVersion);
        map.put(SOURCE_PARAM, Integer.toString(settingsRequest.source));
        String str = settingsRequest.instanceId;
        if (!TextUtils.isEmpty(str)) {
            map.put(INSTANCE_PARAM, str);
        }
        return map;
    }

    public HttpGetRequest createHttpGetRequest(Map<String, String> map) {
        return this.requestFactory.buildHttpGetRequest(this.url, map).header(HEADER_USER_AGENT, CRASHLYTICS_USER_AGENT + CrashlyticsCore.getVersion()).header("X-CRASHLYTICS-DEVELOPER-TOKEN", "470fa2b4ae81cd56ecbcda9735803434cec591fa");
    }

    public JSONObject handleResponse(HttpResponse httpResponse) {
        int iCode = httpResponse.code();
        this.logger.v("Settings response code was: " + iCode);
        if (requestWasSuccessful(iCode)) {
            return getJsonObjectFrom(httpResponse.body());
        }
        Logger logger = this.logger;
        StringBuilder sbS = c.s(iCode, "Settings request failed; (status: ", ") from ");
        sbS.append(this.url);
        logger.e(sbS.toString());
        return null;
    }

    @Override // com.google.firebase.crashlytics.internal.settings.SettingsSpiCall
    public JSONObject invoke(SettingsRequest settingsRequest, boolean z) {
        if (!z) {
            throw new RuntimeException("An invalid data collection token was used.");
        }
        try {
            Map<String, String> queryParamsFor = getQueryParamsFor(settingsRequest);
            HttpGetRequest httpGetRequestApplyHeadersTo = applyHeadersTo(createHttpGetRequest(queryParamsFor), settingsRequest);
            this.logger.d("Requesting settings from " + this.url);
            this.logger.v("Settings query params were: " + queryParamsFor);
            return handleResponse(httpGetRequestApplyHeadersTo.execute());
        } catch (IOException e5) {
            this.logger.e("Settings request failed.", e5);
            return null;
        }
    }

    public boolean requestWasSuccessful(int i10) {
        return i10 == 200 || i10 == 201 || i10 == 202 || i10 == 203;
    }

    public DefaultSettingsSpiCall(String str, HttpRequestFactory httpRequestFactory, Logger logger) {
        if (str == null) {
            throw new IllegalArgumentException("url must not be null.");
        }
        this.logger = logger;
        this.requestFactory = httpRequestFactory;
        this.url = str;
    }
}
