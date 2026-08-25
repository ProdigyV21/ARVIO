package com.typesafe.config.impl;

import com.typesafe.config.ConfigException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Reader;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;

/* JADX INFO: loaded from: classes4.dex */
public class d1 extends f1 {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final URL f14518e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public String f14519f = null;

    public d1(URL url) {
        this.f14518e = url;
    }

    @Override // com.typesafe.config.impl.f1
    public final int d() {
        String str = this.f14519f;
        if (str != null) {
            if (str.equals("application/json")) {
                return 1;
            }
            if (this.f14519f.equals("text/x-java-properties")) {
                return 3;
            }
            if (this.f14519f.equals("application/hocon")) {
                return 2;
            }
            if (x.f()) {
                f1.r("'" + this.f14519f + "' isn't a known content type");
            }
        }
        return 0;
    }

    @Override // com.typesafe.config.impl.f1
    public w1 e() {
        String externalForm = this.f14518e.toExternalForm();
        return new w1(externalForm, -1, -1, 3, externalForm, null, null);
    }

    @Override // com.typesafe.config.impl.f1
    public final int g() {
        return y.e(this.f14518e.getPath());
    }

    @Override // com.typesafe.config.impl.f1
    public final Reader o() {
        throw new ConfigException.BugOrBroken(null, "reader() without options should not be called on ParseableURL");
    }

    @Override // com.typesafe.config.impl.f1
    public final Reader p(l6.l lVar) throws FileNotFoundException {
        URL url = this.f14518e;
        try {
            if (x.f()) {
                f1.r("Loading config from a URL: " + url.toExternalForm());
            }
            URLConnection uRLConnectionOpenConnection = url.openConnection();
            int i10 = lVar.f20038a;
            String str = null;
            if (i10 != 0) {
                int iC = h.f0.c(i10);
                if (iC == 0) {
                    str = "application/json";
                } else if (iC == 1) {
                    str = "application/hocon";
                } else if (iC == 2) {
                    str = "text/x-java-properties";
                }
            }
            if (str != null) {
                uRLConnectionOpenConnection.setRequestProperty("Accept", str);
            }
            uRLConnectionOpenConnection.connect();
            String contentType = uRLConnectionOpenConnection.getContentType();
            this.f14519f = contentType;
            if (contentType != null) {
                if (x.f()) {
                    f1.r("URL sets Content-Type: '" + this.f14519f + "'");
                }
                String strTrim = this.f14519f.trim();
                this.f14519f = strTrim;
                int iIndexOf = strTrim.indexOf(59);
                if (iIndexOf >= 0) {
                    this.f14519f = this.f14519f.substring(0, iIndexOf);
                }
            }
            return f1.c(uRLConnectionOpenConnection.getInputStream());
        } catch (FileNotFoundException e5) {
            throw e5;
        } catch (IOException e6) {
            throw new ConfigException.BugOrBroken(e6, "Cannot load config from URL: " + url.toExternalForm());
        }
    }

    @Override // com.typesafe.config.impl.f1
    public l6.m q(String str) {
        URL url;
        URL url2 = this.f14518e;
        if (new File(str).isAbsolute()) {
            url = null;
        } else {
            try {
                url = url2.toURI().resolve(new URI(str)).toURL();
            } catch (IllegalArgumentException | MalformedURLException | URISyntaxException unused) {
                url = null;
            }
        }
        if (url == null) {
            return null;
        }
        return f1.i(url, this.f14533b.d(null));
    }

    @Override // com.typesafe.config.impl.f1
    public final String toString() {
        return getClass().getSimpleName() + "(" + this.f14518e.toExternalForm() + ")";
    }
}
