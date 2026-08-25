package com.google.android.gms.internal.fido;

import java.io.Serializable;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/* JADX INFO: loaded from: classes4.dex */
public final class w0 extends b implements Serializable {

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final MessageDigest f13833o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final int f13834p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final boolean f13835q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final String f13836r;

    public w0() {
        boolean z;
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            this.f13833o = messageDigest;
            this.f13834p = messageDigest.getDigestLength();
            this.f13836r = "Hashing.sha256()";
            try {
                messageDigest.clone();
                z = true;
            } catch (CloneNotSupportedException unused) {
                z = false;
            }
            this.f13835q = z;
        } catch (NoSuchAlgorithmException e5) {
            throw new AssertionError(e5);
        }
    }

    public final String toString() {
        return this.f13836r;
    }
}
