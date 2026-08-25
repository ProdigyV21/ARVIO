package com.arflix.tv.data.api;

import kotlin.Metadata;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\u001a\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u0004\u0018\u00010\u0002H\u0000¨\u0006\u0003"}, d2 = {"asAddonMetadataText", "", "Lcom/google/gson/JsonElement;", "app_playRelease"}, k = 2, mv = {2, 3, 0}, xi = 48)
public final class StreamApiKt {
    /* JADX WARN: Removed duplicated region for block: B:25:0x0071  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.String asAddonMetadataText(com.google.gson.JsonElement r9) {
        /*
            r0 = 0
            if (r9 != 0) goto L5
            goto L8d
        L5:
            boolean r1 = r9.isJsonPrimitive()
            if (r1 == 0) goto L38
            com.google.gson.JsonPrimitive r9 = r9.getAsJsonPrimitive()
            boolean r1 = r9.isString()
            if (r1 == 0) goto L1a
            java.lang.String r9 = r9.getAsString()
            goto L72
        L1a:
            boolean r1 = r9.isNumber()
            if (r1 == 0) goto L29
            java.lang.Number r9 = r9.getAsNumber()
            java.lang.String r9 = r9.toString()
            goto L72
        L29:
            boolean r1 = r9.isBoolean()
            if (r1 == 0) goto L71
            boolean r9 = r9.getAsBoolean()
            java.lang.String r9 = java.lang.String.valueOf(r9)
            goto L72
        L38:
            boolean r1 = r9.isJsonArray()
            if (r1 == 0) goto L71
            com.google.gson.JsonArray r9 = r9.getAsJsonArray()
            java.util.ArrayList r1 = new java.util.ArrayList
            r1.<init>()
            java.util.Iterator r9 = r9.iterator()
        L4b:
            boolean r2 = r9.hasNext()
            if (r2 == 0) goto L61
            java.lang.Object r2 = r9.next()
            com.google.gson.JsonElement r2 = (com.google.gson.JsonElement) r2
            java.lang.String r2 = asAddonMetadataText(r2)
            if (r2 == 0) goto L4b
            r1.add(r2)
            goto L4b
        L61:
            java.util.List r3 = kotlin.collections.x.g0(r1)
            r7 = 0
            r8 = 62
            java.lang.String r4 = ", "
            r5 = 0
            r6 = 0
            java.lang.String r9 = kotlin.collections.x.u0(r3, r4, r5, r6, r7, r8)
            goto L72
        L71:
            r9 = r0
        L72:
            if (r9 == 0) goto L8d
            java.lang.CharSequence r9 = kotlin.text.o.L0(r9)
            java.lang.String r9 = r9.toString()
            if (r9 == 0) goto L8d
            boolean r1 = kotlin.text.o.h0(r9)
            if (r1 != 0) goto L8d
            java.lang.String r1 = "null"
            boolean r1 = r9.equalsIgnoreCase(r1)
            if (r1 != 0) goto L8d
            return r9
        L8d:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.api.StreamApiKt.asAddonMetadataText(com.google.gson.JsonElement):java.lang.String");
    }
}
