package com.google.firebase.crashlytics.internal.network;

import androidx.compose.foundation.c;
import androidx.compose.material3.d;
import com.google.firebase.crashlytics.internal.Logger;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import javax.net.ssl.HttpsURLConnection;

/* JADX INFO: loaded from: classes4.dex */
public class HttpGetRequest {
    private static final int DEFAULT_TIMEOUT_MS = 10000;
    private static final String METHOD_GET = "GET";
    private static final int READ_BUFFER_SIZE = 8192;
    private final Map<String, String> headers = new HashMap();
    private final Map<String, String> queryParams;
    private final String url;

    public HttpGetRequest(String str, Map<String, String> map) {
        this.url = str;
        this.queryParams = map;
    }

    private String createParamsString(Map<String, String> map) throws UnsupportedEncodingException {
        StringBuilder sb2 = new StringBuilder();
        Iterator<Map.Entry<String, String>> it = map.entrySet().iterator();
        Map.Entry<String, String> next = it.next();
        sb2.append(next.getKey());
        sb2.append("=");
        sb2.append(next.getValue() != null ? URLEncoder.encode(next.getValue(), "UTF-8") : "");
        while (it.hasNext()) {
            Map.Entry<String, String> next2 = it.next();
            sb2.append("&");
            sb2.append(next2.getKey());
            sb2.append("=");
            sb2.append(next2.getValue() != null ? URLEncoder.encode(next2.getValue(), "UTF-8") : "");
        }
        return sb2.toString();
    }

    private String createUrlWithParams(String str, Map<String, String> map) throws UnsupportedEncodingException {
        String strCreateParamsString = createParamsString(map);
        if (strCreateParamsString.isEmpty()) {
            return str;
        }
        if (!str.contains("?")) {
            return c.t(str, "?", strCreateParamsString);
        }
        if (!str.endsWith("&")) {
            strCreateParamsString = "&".concat(strCreateParamsString);
        }
        return d.m(str, strCreateParamsString);
    }

    private String readStream(InputStream inputStream) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
        char[] cArr = new char[8192];
        StringBuilder sb2 = new StringBuilder();
        while (true) {
            int i10 = bufferedReader.read(cArr);
            if (i10 == -1) {
                return sb2.toString();
            }
            sb2.append(cArr, 0, i10);
        }
    }

    public HttpResponse execute() throws Throwable {
        HttpsURLConnection httpsURLConnection;
        InputStream inputStream = null;
        String stream = null;
        inputStream = null;
        try {
            String strCreateUrlWithParams = createUrlWithParams(this.url, this.queryParams);
            Logger.getLogger().v("GET Request URL: " + strCreateUrlWithParams);
            httpsURLConnection = (HttpsURLConnection) new URL(strCreateUrlWithParams).openConnection();
            try {
                httpsURLConnection.setReadTimeout(10000);
                httpsURLConnection.setConnectTimeout(10000);
                httpsURLConnection.setRequestMethod(METHOD_GET);
                for (Map.Entry<String, String> entry : this.headers.entrySet()) {
                    httpsURLConnection.addRequestProperty(entry.getKey(), entry.getValue());
                }
                httpsURLConnection.connect();
                int responseCode = httpsURLConnection.getResponseCode();
                InputStream inputStream2 = httpsURLConnection.getInputStream();
                if (inputStream2 != null) {
                    try {
                        stream = readStream(inputStream2);
                    } catch (Throwable th) {
                        th = th;
                        inputStream = inputStream2;
                        if (inputStream != null) {
                            inputStream.close();
                        }
                        if (httpsURLConnection != null) {
                            httpsURLConnection.disconnect();
                        }
                        throw th;
                    }
                }
                if (inputStream2 != null) {
                    inputStream2.close();
                }
                httpsURLConnection.disconnect();
                return new HttpResponse(responseCode, stream);
            } catch (Throwable th2) {
                th = th2;
            }
        } catch (Throwable th3) {
            th = th3;
            httpsURLConnection = null;
        }
    }

    public HttpGetRequest header(String str, String str2) {
        this.headers.put(str, str2);
        return this;
    }

    public HttpGetRequest header(Map.Entry<String, String> entry) {
        return header(entry.getKey(), entry.getValue());
    }
}
