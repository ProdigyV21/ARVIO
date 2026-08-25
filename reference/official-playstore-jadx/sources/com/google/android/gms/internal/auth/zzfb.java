package com.google.android.gms.internal.auth;

import java.io.IOException;

/* JADX INFO: loaded from: classes4.dex */
public final class zzfb extends IOException {
    public static zzfb a() {
        return new zzfb("Protocol message had invalid UTF-8.");
    }

    public static zzfb b() {
        return new zzfb("CodedInputStream encountered an embedded string or message which claimed to have negative size.");
    }

    public static zzfb c() {
        return new zzfb("While parsing a protocol message, the input ended unexpectedly in the middle of a field.  This could mean either that the input has been truncated or that an embedded message misreported its own length.");
    }
}
