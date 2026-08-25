package io.ktor.server.config;

import androidx.compose.foundation.c;
import java.util.Map;
import kotlin.Metadata;
import kotlin.text.o;
import kotlin.text.u;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010%\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a\u001f\u0010\u0003\u001a\u00020\u00002\u0006\u0010\u0001\u001a\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u0000H\u0002¢\u0006\u0004\b\u0003\u0010\u0004\u001a+\u0010\n\u001a\u00020\t2\u0006\u0010\u0005\u001a\u00020\u00002\u0012\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\u0000\u0012\u0004\u0012\u00020\u00070\u0006H\u0002¢\u0006\u0004\b\n\u0010\u000b¨\u0006\f"}, d2 = {"", "root", "relative", "combine", "(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;", "input", "", "", "listElements", "Lx6/t0;", "findListElements", "(Ljava/lang/String;Ljava/util/Map;)V", "ktor-server-core"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class MapApplicationConfigKt {
    /* JADX INFO: Access modifiers changed from: private */
    public static final String combine(String str, String str2) {
        return str.length() == 0 ? str2 : c.m('.', str, str2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void findListElements(String str, Map<String, Integer> map) {
        int iE0 = o.e0(str, '.', 0, false, 6);
        while (iE0 != str.length()) {
            int i10 = iE0 + 1;
            int iE02 = o.e0(str, '.', i10, false, 4);
            if (iE02 == -1) {
                iE02 = str.length();
            }
            Integer numR = u.R(str.substring(i10, iE02));
            if (numR != null) {
                int iIntValue = numR.intValue();
                String strSubstring = str.substring(0, iE0);
                int iMax = iIntValue + 1;
                Integer num = map.get(strSubstring);
                if (num != null) {
                    iMax = Math.max(num.intValue(), iMax);
                }
                map.put(strSubstring, Integer.valueOf(iMax));
            }
            iE0 = iE02;
        }
    }
}
