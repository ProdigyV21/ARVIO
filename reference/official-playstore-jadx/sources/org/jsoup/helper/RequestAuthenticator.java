package org.jsoup.helper;

import java.net.Authenticator;
import java.net.PasswordAuthentication;
import java.net.URL;

/* JADX INFO: loaded from: classes5.dex */
@FunctionalInterface
public interface RequestAuthenticator {

    public static class Context {
        private final String realm;
        private final Authenticator.RequestorType type;
        private final URL url;

        public Context(URL url, Authenticator.RequestorType requestorType, String str) {
            this.url = url;
            this.type = requestorType;
            this.realm = str;
        }

        public PasswordAuthentication credentials(String str, String str2) {
            return new PasswordAuthentication(str, str2.toCharArray());
        }

        public boolean isProxy() {
            return this.type == Authenticator.RequestorType.PROXY;
        }

        public boolean isServer() {
            return this.type == Authenticator.RequestorType.SERVER;
        }

        public String realm() {
            return this.realm;
        }

        public Authenticator.RequestorType type() {
            return this.type;
        }

        public URL url() {
            return this.url;
        }
    }

    PasswordAuthentication authenticate(Context context);
}
