package com.arflix.tv.data.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u000e\n\u0000\u001a\f\u0010\u0000\u001a\u00020\u0001*\u00020\u0001H\u0002¨\u0006\u0002"}, d2 = {"toDisplayTitle", "", "app_playRelease"}, k = 2, mv = {2, 3, 0}, xi = 48)
public final class CatalogRepositoryKt {
    /* JADX INFO: Access modifiers changed from: private */
    public static final String toDisplayTitle(String str) {
        List listX0 = kotlin.text.o.x0(str.replace('-', ' '), new char[]{' '}, 6);
        ArrayList arrayList = new ArrayList();
        for (Object obj : listX0) {
            if (!kotlin.text.o.h0((String) obj)) {
                arrayList.add(obj);
            }
        }
        String strU0 = kotlin.collections.x.u0(arrayList, " ", null, null, new f(21), 30);
        return kotlin.text.o.h0(strU0) ? "Custom Catalog" : strU0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final CharSequence toDisplayTitle$lambda$1(String str) {
        if (str.length() <= 0) {
            return str;
        }
        return ((Object) String.valueOf(str.charAt(0)).toUpperCase(Locale.ROOT)) + str.substring(1);
    }
}
