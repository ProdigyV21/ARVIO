package com.google.android.gms.cast;

import androidx.credentials.exceptions.publickeycredential.DomExceptionUtils;
import com.google.android.gms.cast.internal.CastUtils;
import java.util.Collection;
import java.util.Locale;

/* JADX INFO: loaded from: classes4.dex */
final class zzt {
    private final String zza;
    private final String zzb;
    private final Collection zzc;

    public /* synthetic */ zzt(String str, String str2, Collection collection, boolean z, boolean z5, byte[] bArr) {
        this.zza = str;
        this.zzb = str2;
        this.zzc = collection;
    }

    public final /* synthetic */ String zza() {
        StringBuilder sb2 = new StringBuilder(this.zza);
        String str = this.zzb;
        if (str != null) {
            String upperCase = str.toUpperCase(Locale.ROOT);
            if (!upperCase.matches("[A-F0-9]+")) {
                throw new IllegalArgumentException("Invalid application ID: ".concat(str));
            }
            sb2.append(DomExceptionUtils.SEPARATOR);
            sb2.append(upperCase);
        }
        Collection<String> collection = this.zzc;
        boolean z = false;
        if (collection != null) {
            if (collection.isEmpty()) {
                throw new IllegalArgumentException("Must specify at least one namespace");
            }
            boolean z5 = str != null;
            if (str == null) {
                sb2.append(DomExceptionUtils.SEPARATOR);
            }
            sb2.append(DomExceptionUtils.SEPARATOR);
            boolean z10 = true;
            for (String str2 : collection) {
                CastUtils.throwIfInvalidNamespace(str2);
                if (!z10) {
                    sb2.append(",");
                }
                sb2.append(CastUtils.zzc(str2));
                z10 = false;
            }
            z = z5;
        } else if (str != null) {
            z = true;
        }
        if (true != z && collection == null) {
            sb2.append(DomExceptionUtils.SEPARATOR);
        }
        if (collection == null) {
            sb2.append(DomExceptionUtils.SEPARATOR);
        }
        sb2.append("//ALLOW_IPV6");
        return sb2.toString();
    }
}
