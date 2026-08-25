package com.arflix.tv.util;

import com.arflix.tv.R;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.r;
import kotlin.text.m;
import kotlin.text.o;
import kotlin.text.u;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\"\n\u0002\b\u0003\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0015\u0010\u0006\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u0006\u0010\u0007J#\u0010\u000b\u001a\u0004\u0018\u00010\n2\u0006\u0010\u0005\u001a\u00020\u00042\b\b\u0002\u0010\t\u001a\u00020\bH\u0007¢\u0006\u0004\b\u000b\u0010\fR\u0014\u0010\u000e\u001a\u00020\r8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u000e\u0010\u000fR\u001a\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00040\u00108\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0011\u0010\u0012¨\u0006\u0013"}, d2 = {"Lcom/arflix/tv/util/AuthEmailValidator;", "", "<init>", "()V", "", "email", "normalize", "(Ljava/lang/String;)Ljava/lang/String;", "", "rejectDisposable", "", "validate", "(Ljava/lang/String;Z)Ljava/lang/Integer;", "Lkotlin/text/m;", "emailRegex", "Lkotlin/text/m;", "", "blockedDomains", "Ljava/util/Set;", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class AuthEmailValidator {
    public static final AuthEmailValidator INSTANCE = new AuthEmailValidator();
    private static final m emailRegex = new m("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,63}$", 0);
    private static final Set<String> blockedDomains = r.p0(new String[]{"example.com", "example.net", "example.org", "invalid", "localhost", "mailinator.com", "guerrillamail.com", "guerrillamail.net", "10minutemail.com", "tempmail.com", "temp-mail.org", "yopmail.com", "trashmail.com", "trashmail.at", "trashmail.io", "sharklasers.com", "throwam.com", "dispostable.com", "mailnull.com", "spamgourmet.com", "discard.email", "fakeinbox.com", "spamherelots.com", "maildrop.cc"});
    public static final int $stable = 8;

    private AuthEmailValidator() {
    }

    public static /* synthetic */ Integer validate$default(AuthEmailValidator authEmailValidator, String str, boolean z, int i10, Object obj) {
        if ((i10 & 2) != 0) {
            z = true;
        }
        return authEmailValidator.validate(str, z);
    }

    public final String normalize(String email) {
        return o.L0(email).toString().toLowerCase(Locale.ROOT);
    }

    public final Integer validate(String email, boolean rejectDisposable) {
        String strNormalize = normalize(email);
        if (o.h0(strNormalize)) {
            return Integer.valueOf(R.string.auth_email_required);
        }
        if (strNormalize.length() > 254 || !emailRegex.e(strNormalize)) {
            return Integer.valueOf(R.string.auth_email_invalid);
        }
        String strH0 = o.H0(strNormalize, "@");
        String strD0 = o.D0(strNormalize, "@", "");
        if (o.h0(strH0) || o.h0(strD0)) {
            return Integer.valueOf(R.string.auth_email_real_required);
        }
        if (rejectDisposable && blockedDomains.contains(strD0)) {
            return Integer.valueOf(R.string.auth_email_real_required);
        }
        if (rejectDisposable && (u.K(strD0, ".invalid", false) || u.K(strD0, ".test", false) || u.K(strD0, ".local", false))) {
            return Integer.valueOf(R.string.auth_email_real_required);
        }
        List listY0 = o.y0(strD0, new String[]{"."}, 0, 6);
        if (listY0.isEmpty()) {
            return null;
        }
        Iterator it = listY0.iterator();
        while (it.hasNext()) {
            if (o.h0((String) it.next())) {
                return Integer.valueOf(R.string.auth_email_invalid);
            }
        }
        return null;
    }
}
