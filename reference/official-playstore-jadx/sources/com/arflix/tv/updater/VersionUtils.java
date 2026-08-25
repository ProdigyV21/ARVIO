package com.arflix.tv.updater;

import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.p;
import kotlin.text.o;
import kotlin.text.u;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0005J\u001a\u0010\u0007\u001a\n\u0012\u0004\u0012\u00020\t\u0018\u00010\b2\b\u0010\u0006\u001a\u0004\u0018\u00010\u0005H\u0002J\u001a\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\u00052\b\u0010\r\u001a\u0004\u0018\u00010\u0005¨\u0006\u000e"}, d2 = {"Lcom/arflix/tv/updater/VersionUtils;", "", "<init>", "()V", "normalize", "", "raw", "parseVersionParts", "", "", "isRemoteNewer", "", "remote", "local", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class VersionUtils {
    public static final int $stable = 0;
    public static final VersionUtils INSTANCE = new VersionUtils();

    private VersionUtils() {
    }

    private final List<Integer> parseVersionParts(String raw) {
        String strNormalize = normalize(raw);
        if (o.h0(strNormalize)) {
            return null;
        }
        List listX0 = o.x0(strNormalize, new char[]{'.', '-', '_'}, 6);
        ArrayList<String> arrayList = new ArrayList();
        for (Object obj : listX0) {
            if (!o.h0((String) obj)) {
                arrayList.add(obj);
            }
        }
        ArrayList arrayList2 = new ArrayList();
        for (String strSubstring : arrayList) {
            int length = strSubstring.length();
            int i10 = 0;
            while (true) {
                if (i10 >= length) {
                    break;
                }
                if (!Character.isDigit(strSubstring.charAt(i10))) {
                    strSubstring = strSubstring.substring(0, i10);
                    break;
                }
                i10++;
            }
            Integer numR = u.R(strSubstring);
            if (numR != null) {
                arrayList2.add(numR);
            }
        }
        if (arrayList2.isEmpty()) {
            return null;
        }
        return arrayList2;
    }

    public final boolean isRemoteNewer(String remote, String local) {
        List<Integer> versionParts = parseVersionParts(remote);
        List<Integer> versionParts2 = parseVersionParts(local);
        if (versionParts == null || versionParts2 == null) {
            String strNormalize = normalize(remote);
            String strNormalize2 = normalize(local);
            return (o.h0(strNormalize) || o.h0(strNormalize2) || p.a(strNormalize, strNormalize2)) ? false : true;
        }
        int iMax = Math.max(versionParts.size(), versionParts2.size());
        int i10 = 0;
        while (i10 < iMax) {
            int iIntValue = ((i10 < 0 || i10 >= versionParts.size()) ? 0 : versionParts.get(i10)).intValue();
            int iIntValue2 = ((i10 < 0 || i10 >= versionParts2.size()) ? 0 : versionParts2.get(i10)).intValue();
            if (iIntValue != iIntValue2) {
                return iIntValue > iIntValue2;
            }
            i10++;
        }
        return false;
    }

    public final String normalize(String raw) {
        return (raw == null || o.h0(raw)) ? "" : o.r0(o.r0(o.L0(raw).toString(), "v"), "V");
    }
}
