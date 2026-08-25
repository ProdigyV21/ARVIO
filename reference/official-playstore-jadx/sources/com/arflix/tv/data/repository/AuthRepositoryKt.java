package com.arflix.tv.data.repository;

import androidx.media3.extractor.text.ttml.TtmlNode;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import io.ktor.http.ContentDisposition;
import java.util.Iterator;
import java.util.Map;
import kotlin.Metadata;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000&\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0013\n\u0002\u0010\t\n\u0002\b\u0002\u001a\u0012\u0010\u0000\u001a\u0004\u0018\u00010\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u0002\u001a\u0017\u0010\u0004\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0002\u001a\u00020\u0003H\u0000¢\u0006\u0002\u0010\u0006\u001a\u0010\u0010\u0007\u001a\u00020\u00052\u0006\u0010\u0002\u001a\u00020\u0003H\u0000\u001a\u0010\u0010\b\u001a\u00020\u00052\u0006\u0010\u0002\u001a\u00020\u0003H\u0000\u001a \u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\n2\u0006\u0010\f\u001a\u00020\n2\u0006\u0010\r\u001a\u00020\nH\u0000\u001a\u001a\u0010\u000e\u001a\u00020\n2\u0006\u0010\u000f\u001a\u00020\u00032\b\u0010\u0010\u001a\u0004\u0018\u00010\u0003H\u0002\u001a\u0010\u0010\u0011\u001a\u00020\u00032\u0006\u0010\u0012\u001a\u00020\u0003H\u0002\u001a\f\u0010\u0013\u001a\u00020\n*\u00020\u0001H\u0002\u001a\f\u0010\u0014\u001a\u00020\n*\u00020\u0001H\u0002\u001a\f\u0010\u0015\u001a\u00020\n*\u00020\u0001H\u0002\u001a\u0014\u0010\u0016\u001a\u00020\n*\u00020\u00012\u0006\u0010\u0017\u001a\u00020\u0003H\u0002\u001a\u0014\u0010\u0018\u001a\u00020\n*\u00020\u00012\u0006\u0010\u0017\u001a\u00020\u0003H\u0002\u001a\u0016\u0010\u0019\u001a\u0004\u0018\u00010\u0001*\u00020\u00012\u0006\u0010\u0017\u001a\u00020\u0003H\u0002\u001a\u0014\u0010\u001a\u001a\u00020\u0005*\u00020\u00012\u0006\u0010\u0017\u001a\u00020\u0003H\u0002\u001a\u0014\u0010\u001b\u001a\u00020\u0003*\u00020\u00012\u0006\u0010\u0017\u001a\u00020\u0003H\u0002\u001a\u0014\u0010\u001c\u001a\u00020\u0005*\u00020\u00012\u0006\u0010\u0017\u001a\u00020\u0003H\u0002\u001a\u0014\u0010\u001d\u001a\u00020\u001e*\u00020\u00012\u0006\u0010\u0017\u001a\u00020\u0003H\u0002\u001a\u0014\u0010\u001f\u001a\u00020\n*\u00020\u00012\u0006\u0010\u0017\u001a\u00020\u0003H\u0002¨\u0006 "}, d2 = {"parseJsonObject", "Lcom/google/gson/JsonObject;", "payload", "", "accountSyncPayloadProfileCount", "", "(Ljava/lang/String;)Ljava/lang/Integer;", "accountSyncPayloadScopedCoverage", "accountSyncPayloadRestoreRank", "accountSyncPayloadSaveSucceeded", "", "accountSyncSaved", "userSettingsSaved", "profileAddonsSaved", "accountSyncPayloadsMatch", "expected", "actual", "safePostgrestError", TtmlNode.TAG_BODY, "isPlaceholderCloudProfile", "hasFullAccountSnapshotShape", "hasConfiguredAccountState", "objectHasNonEmptyArray", "key", "objectHasConfiguredIptvProfile", "getObject", "arraySize", "stringValue", "intValue", "longValue", "", "booleanValue", "app_playRelease"}, k = 2, mv = {2, 3, 0}, xi = 48)
public final class AuthRepositoryKt {
    /* JADX WARN: Removed duplicated region for block: B:19:0x0031  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.Integer accountSyncPayloadProfileCount(java.lang.String r3) {
        /*
            com.google.gson.JsonObject r3 = parseJsonObject(r3)
            r0 = 0
            if (r3 != 0) goto L8
            return r0
        L8:
            java.lang.String r1 = "profiles"
            boolean r2 = r3.has(r1)
            if (r2 != 0) goto L11
            return r0
        L11:
            com.google.gson.JsonElement r3 = r3.get(r1)
            if (r3 == 0) goto L31
            boolean r1 = r3.isJsonNull()
            if (r1 != 0) goto L24
            boolean r1 = r3.isJsonArray()
            if (r1 == 0) goto L24
            r0 = r3
        L24:
            if (r0 == 0) goto L31
            com.google.gson.JsonArray r3 = r0.getAsJsonArray()
            if (r3 == 0) goto L31
            int r3 = r3.size()
            goto L32
        L31:
            r3 = 0
        L32:
            java.lang.Integer r3 = java.lang.Integer.valueOf(r3)
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.repository.AuthRepositoryKt.accountSyncPayloadProfileCount(java.lang.String):java.lang.Integer");
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x0026  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x003e  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final int accountSyncPayloadRestoreRank(java.lang.String r6) {
        /*
            com.google.gson.JsonObject r6 = parseJsonObject(r6)
            r0 = 0
            if (r6 != 0) goto L8
            return r0
        L8:
            java.lang.String r1 = "profiles"
            com.google.gson.JsonElement r2 = r6.get(r1)
            r3 = 0
            if (r2 == 0) goto L26
            boolean r4 = r2.isJsonNull()
            if (r4 != 0) goto L1e
            boolean r4 = r2.isJsonArray()
            if (r4 == 0) goto L1e
            goto L1f
        L1e:
            r2 = r3
        L1f:
            if (r2 == 0) goto L26
            com.google.gson.JsonArray r2 = r2.getAsJsonArray()
            goto L27
        L26:
            r2 = r3
        L27:
            boolean r1 = r6.has(r1)
            if (r1 == 0) goto L3a
            if (r2 == 0) goto L34
            int r1 = r2.size()
            goto L35
        L34:
            r1 = r0
        L35:
            java.lang.Integer r1 = java.lang.Integer.valueOf(r1)
            goto L3b
        L3a:
            r1 = r3
        L3b:
            r4 = 1
            if (r1 != 0) goto L40
        L3e:
            r2 = r0
            goto L70
        L40:
            int r5 = r1.intValue()
            if (r5 <= r4) goto L48
            r2 = r4
            goto L70
        L48:
            int r5 = r1.intValue()
            if (r5 != r4) goto L3e
            if (r2 == 0) goto L3e
            com.google.gson.JsonElement r2 = r2.get(r0)
            if (r2 == 0) goto L3e
            boolean r5 = r2.isJsonNull()
            if (r5 != 0) goto L63
            boolean r5 = r2.isJsonObject()
            if (r5 == 0) goto L63
            r3 = r2
        L63:
            if (r3 == 0) goto L3e
            com.google.gson.JsonObject r2 = r3.getAsJsonObject()
            if (r2 == 0) goto L3e
            boolean r2 = isPlaceholderCloudProfile(r2)
            r2 = r2 ^ r4
        L70:
            boolean r3 = hasConfiguredAccountState(r6)
            boolean r6 = hasFullAccountSnapshotShape(r6)
            if (r1 == 0) goto L81
            int r5 = r1.intValue()
            if (r5 > 0) goto L81
            return r0
        L81:
            if (r1 == 0) goto L8e
            int r0 = r1.intValue()
            if (r0 <= r4) goto L8e
            if (r6 == 0) goto L8e
            r6 = 80
            return r6
        L8e:
            if (r1 == 0) goto L99
            int r0 = r1.intValue()
            if (r0 <= r4) goto L99
            r6 = 70
            return r6
        L99:
            if (r2 != 0) goto L9d
            if (r3 == 0) goto La2
        L9d:
            if (r6 == 0) goto La2
            r6 = 50
            return r6
        La2:
            if (r2 != 0) goto Lb6
            if (r3 == 0) goto La7
            goto Lb6
        La7:
            if (r1 != 0) goto Lae
            if (r6 == 0) goto Lae
            r6 = 30
            return r6
        Lae:
            if (r1 != 0) goto Lb3
            r6 = 20
            return r6
        Lb3:
            r6 = 10
            return r6
        Lb6:
            r6 = 40
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.repository.AuthRepositoryKt.accountSyncPayloadRestoreRank(java.lang.String):int");
    }

    public static final boolean accountSyncPayloadSaveSucceeded(boolean z, boolean z5, boolean z10) {
        return z || z5 || z10;
    }

    /* JADX WARN: Removed duplicated region for block: B:35:0x0063  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x006f  */
    /* JADX WARN: Removed duplicated region for block: B:50:0x00ab  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final int accountSyncPayloadScopedCoverage(java.lang.String r12) {
        /*
            Method dump skipped, instruction units count: 225
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.repository.AuthRepositoryKt.accountSyncPayloadScopedCoverage(java.lang.String):int");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean accountSyncPayloadsMatch(String str, String str2) {
        Object c0Var;
        if (str2 == null || kotlin.text.o.h0(str2)) {
            return false;
        }
        if (kotlin.jvm.internal.p.a(str, str2)) {
            return true;
        }
        try {
            JSONObject jSONObject = new JSONObject(str);
            JSONObject jSONObject2 = new JSONObject(str2);
            jSONObject.remove("updatedAt");
            jSONObject2.remove("updatedAt");
            c0Var = Boolean.valueOf(kotlin.jvm.internal.p.a(jSONObject.toString(), jSONObject2.toString()));
        } catch (Throwable th) {
            c0Var = new x6.c0(th);
        }
        Object obj = Boolean.FALSE;
        if (c0Var instanceof x6.c0) {
            c0Var = obj;
        }
        return ((Boolean) c0Var).booleanValue();
    }

    private static final int arraySize(JsonObject jsonObject, String str) {
        JsonArray asJsonArray;
        JsonElement jsonElement = jsonObject.get(str);
        if (jsonElement == null) {
            return 0;
        }
        if (jsonElement.isJsonNull() || !jsonElement.isJsonArray()) {
            jsonElement = null;
        }
        if (jsonElement == null || (asJsonArray = jsonElement.getAsJsonArray()) == null) {
            return 0;
        }
        return asJsonArray.size();
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x001b  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static final boolean booleanValue(com.google.gson.JsonObject r1, java.lang.String r2) {
        /*
            com.google.gson.JsonElement r1 = r1.get(r2)     // Catch: java.lang.Throwable -> L21
            if (r1 == 0) goto L1b
            boolean r2 = r1.isJsonNull()     // Catch: java.lang.Throwable -> L21
            if (r2 != 0) goto L13
            boolean r2 = r1.isJsonPrimitive()     // Catch: java.lang.Throwable -> L21
            if (r2 == 0) goto L13
            goto L14
        L13:
            r1 = 0
        L14:
            if (r1 == 0) goto L1b
            boolean r1 = r1.getAsBoolean()     // Catch: java.lang.Throwable -> L21
            goto L1c
        L1b:
            r1 = 0
        L1c:
            java.lang.Boolean r1 = java.lang.Boolean.valueOf(r1)     // Catch: java.lang.Throwable -> L21
            goto L28
        L21:
            r1 = move-exception
            x6.c0 r2 = new x6.c0
            r2.<init>(r1)
            r1 = r2
        L28:
            java.lang.Boolean r2 = java.lang.Boolean.FALSE
            boolean r0 = r1 instanceof x6.c0
            if (r0 == 0) goto L2f
            r1 = r2
        L2f:
            java.lang.Boolean r1 = (java.lang.Boolean) r1
            boolean r1 = r1.booleanValue()
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.repository.AuthRepositoryKt.booleanValue(com.google.gson.JsonObject, java.lang.String):boolean");
    }

    private static final JsonObject getObject(JsonObject jsonObject, String str) {
        JsonElement jsonElement = jsonObject.get(str);
        if (jsonElement != null) {
            if (jsonElement.isJsonNull() || !jsonElement.isJsonObject()) {
                jsonElement = null;
            }
            if (jsonElement != null) {
                return jsonElement.getAsJsonObject();
            }
        }
        return null;
    }

    private static final boolean hasConfiguredAccountState(JsonObject jsonObject) {
        return arraySize(jsonObject, "addons") > 0 || !kotlin.text.o.h0(stringValue(jsonObject, "iptvM3uUrl")) || objectHasNonEmptyArray(jsonObject, "addonsByProfile") || objectHasNonEmptyArray(jsonObject, "watchlistByProfile") || objectHasConfiguredIptvProfile(jsonObject, "iptvByProfile");
    }

    private static final boolean hasFullAccountSnapshotShape(JsonObject jsonObject) {
        return jsonObject.has("profileSettingsById") || jsonObject.has("addonsByProfile") || jsonObject.has("catalogsByProfile") || jsonObject.has("iptvByProfile") || jsonObject.has("watchlistByProfile");
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x001e  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static final int intValue(com.google.gson.JsonObject r1, java.lang.String r2) {
        /*
            r0 = 0
            com.google.gson.JsonElement r1 = r1.get(r2)     // Catch: java.lang.Throwable -> L14
            if (r1 == 0) goto L1e
            boolean r2 = r1.isJsonNull()     // Catch: java.lang.Throwable -> L14
            if (r2 != 0) goto L16
            boolean r2 = r1.isJsonPrimitive()     // Catch: java.lang.Throwable -> L14
            if (r2 == 0) goto L16
            goto L17
        L14:
            r1 = move-exception
            goto L24
        L16:
            r1 = 0
        L17:
            if (r1 == 0) goto L1e
            int r1 = r1.getAsInt()     // Catch: java.lang.Throwable -> L14
            goto L1f
        L1e:
            r1 = r0
        L1f:
            java.lang.Integer r1 = java.lang.Integer.valueOf(r1)     // Catch: java.lang.Throwable -> L14
            goto L2a
        L24:
            x6.c0 r2 = new x6.c0
            r2.<init>(r1)
            r1 = r2
        L2a:
            java.lang.Integer r2 = java.lang.Integer.valueOf(r0)
            boolean r0 = r1 instanceof x6.c0
            if (r0 == 0) goto L33
            r1 = r2
        L33:
            java.lang.Number r1 = (java.lang.Number) r1
            int r1 = r1.intValue()
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.repository.AuthRepositoryKt.intValue(com.google.gson.JsonObject, java.lang.String):int");
    }

    private static final boolean isPlaceholderCloudProfile(JsonObject jsonObject) {
        return kotlin.text.u.L(kotlin.text.o.L0(stringValue(jsonObject, ContentDisposition.Parameters.Name)).toString(), "Profile 1", true) && intValue(jsonObject, "avatarId") == 0 && longValue(jsonObject, "avatarImageVersion") <= 0 && !booleanValue(jsonObject, "isKidsProfile") && !booleanValue(jsonObject, "isLocked") && kotlin.text.o.h0(kotlin.text.o.L0(stringValue(jsonObject, "pin")).toString());
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x001f  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static final long longValue(com.google.gson.JsonObject r2, java.lang.String r3) {
        /*
            r0 = 0
            com.google.gson.JsonElement r2 = r2.get(r3)     // Catch: java.lang.Throwable -> L15
            if (r2 == 0) goto L1f
            boolean r3 = r2.isJsonNull()     // Catch: java.lang.Throwable -> L15
            if (r3 != 0) goto L17
            boolean r3 = r2.isJsonPrimitive()     // Catch: java.lang.Throwable -> L15
            if (r3 == 0) goto L17
            goto L18
        L15:
            r2 = move-exception
            goto L25
        L17:
            r2 = 0
        L18:
            if (r2 == 0) goto L1f
            long r2 = r2.getAsLong()     // Catch: java.lang.Throwable -> L15
            goto L20
        L1f:
            r2 = r0
        L20:
            java.lang.Long r2 = java.lang.Long.valueOf(r2)     // Catch: java.lang.Throwable -> L15
            goto L2b
        L25:
            x6.c0 r3 = new x6.c0
            r3.<init>(r2)
            r2 = r3
        L2b:
            java.lang.Long r3 = java.lang.Long.valueOf(r0)
            boolean r0 = r2 instanceof x6.c0
            if (r0 == 0) goto L34
            r2 = r3
        L34:
            java.lang.Number r2 = (java.lang.Number) r2
            long r2 = r2.longValue()
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.repository.AuthRepositoryKt.longValue(com.google.gson.JsonObject, java.lang.String):long");
    }

    private static final boolean objectHasConfiguredIptvProfile(JsonObject jsonObject, String str) {
        JsonObject asJsonObject;
        JsonObject object = getObject(jsonObject, str);
        if (object == null) {
            return false;
        }
        Iterator<T> it = object.entrySet().iterator();
        while (it.hasNext()) {
            JsonElement jsonElement = (JsonElement) ((Map.Entry) it.next()).getValue();
            if (jsonElement != null) {
                if (jsonElement.isJsonNull() || !jsonElement.isJsonObject()) {
                    jsonElement = null;
                }
                if (jsonElement != null && (asJsonObject = jsonElement.getAsJsonObject()) != null && (!kotlin.text.o.h0(stringValue(asJsonObject, "m3uUrl")) || !kotlin.text.o.h0(stringValue(asJsonObject, "epgUrl")) || arraySize(asJsonObject, "playlists") > 0 || arraySize(asJsonObject, "favoriteChannels") > 0 || arraySize(asJsonObject, "favoriteGroups") > 0)) {
                    return true;
                }
            }
        }
        return false;
    }

    private static final boolean objectHasNonEmptyArray(JsonObject jsonObject, String str) {
        JsonObject object = getObject(jsonObject, str);
        if (object == null) {
            return false;
        }
        Iterator<T> it = object.entrySet().iterator();
        while (it.hasNext()) {
            JsonElement jsonElement = (JsonElement) ((Map.Entry) it.next()).getValue();
            if (jsonElement != null && !jsonElement.isJsonNull() && jsonElement.isJsonArray() && jsonElement.getAsJsonArray().size() > 0) {
                return true;
            }
        }
        return false;
    }

    private static final JsonObject parseJsonObject(String str) {
        try {
            return new JsonParser().parse(str).getAsJsonObject();
        } catch (JsonSyntaxException | IllegalStateException unused) {
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final String safePostgrestError(String str) {
        JSONObject jSONObject;
        String strOptString;
        String strOptString2;
        if (kotlin.text.o.h0(str)) {
            return "empty response";
        }
        String str2 = null;
        try {
            jSONObject = new JSONObject(str);
        } catch (JSONException unused) {
            jSONObject = null;
        }
        if (jSONObject != null && (strOptString2 = jSONObject.optString("message")) != null) {
            if (kotlin.text.o.h0(strOptString2)) {
                strOptString2 = null;
            }
            if (strOptString2 != null) {
                return strOptString2;
            }
        }
        if (jSONObject != null && (strOptString = jSONObject.optString("error")) != null && !kotlin.text.o.h0(strOptString)) {
            str2 = strOptString;
        }
        return str2 == null ? kotlin.text.o.I0(180, str) : str2;
    }

    private static final String stringValue(JsonObject jsonObject, String str) {
        Object c0Var;
        try {
            JsonElement jsonElement = jsonObject.get(str);
            c0Var = null;
            if (jsonElement != null) {
                if (jsonElement.isJsonNull() || !jsonElement.isJsonPrimitive()) {
                    jsonElement = null;
                }
                if (jsonElement != null) {
                    c0Var = jsonElement.getAsString();
                }
            }
            if (c0Var == null) {
                c0Var = "";
            }
        } catch (Throwable th) {
            c0Var = new x6.c0(th);
        }
        return (String) (c0Var instanceof x6.c0 ? "" : c0Var);
    }
}
