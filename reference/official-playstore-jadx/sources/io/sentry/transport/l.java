package io.sentry.transport;

import java.net.Authenticator;
import java.net.PasswordAuthentication;

/* JADX INFO: loaded from: classes5.dex */
public final class l extends Authenticator {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final String f17924a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final String f17925b;

    public l(String str, String str2) {
        ac.b.V(str, "user is required");
        this.f17924a = str;
        ac.b.V(str2, "password is required");
        this.f17925b = str2;
    }

    @Override // java.net.Authenticator
    public final PasswordAuthentication getPasswordAuthentication() {
        if (getRequestorType() != Authenticator.RequestorType.PROXY) {
            return null;
        }
        return new PasswordAuthentication(this.f17924a, this.f17925b.toCharArray());
    }
}
