package org.jsoup;

import java.net.URL;
import org.jsoup.helper.RequestAuthenticator;

/* JADX INFO: loaded from: classes5.dex */
public abstract /* synthetic */ class a {
    public static Connection a(Connection connection, RequestAuthenticator requestAuthenticator) {
        throw new UnsupportedOperationException();
    }

    public static Connection b(Connection connection, String str) {
        return connection.newRequest().url(str);
    }

    public static Connection c(Connection connection, URL url) {
        return connection.newRequest().url(url);
    }
}
