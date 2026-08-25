package com.discord.org.webrtc;

import java.util.Map;

/* JADX INFO: loaded from: classes4.dex */
public class RTCStats {
    private final String id;
    private final Map<String, Object> members;
    private final long timestampUs;
    private final String type;

    public RTCStats(long j10, String str, String str2, Map<String, Object> map) {
        this.timestampUs = j10;
        this.type = str;
        this.id = str2;
        this.members = map;
    }

    private static void appendValue(StringBuilder sb2, Object obj) {
        if (!(obj instanceof Object[])) {
            if (!(obj instanceof String)) {
                sb2.append(obj);
                return;
            }
            sb2.append('\"');
            sb2.append(obj);
            sb2.append('\"');
            return;
        }
        Object[] objArr = (Object[]) obj;
        sb2.append('[');
        for (int i10 = 0; i10 < objArr.length; i10++) {
            if (i10 != 0) {
                sb2.append(", ");
            }
            appendValue(sb2, objArr[i10]);
        }
        sb2.append(']');
    }

    public static RTCStats create(long j10, String str, String str2, Map map) {
        return new RTCStats(j10, str, str2, map);
    }

    public String getId() {
        return this.id;
    }

    public Map<String, Object> getMembers() {
        return this.members;
    }

    public double getTimestampUs() {
        return this.timestampUs;
    }

    public String getType() {
        return this.type;
    }

    public String toString() {
        StringBuilder sbS = androidx.compose.material3.d.s("{ timestampUs: ");
        sbS.append(this.timestampUs);
        sbS.append(", type: ");
        sbS.append(this.type);
        sbS.append(", id: ");
        sbS.append(this.id);
        for (Map.Entry<String, Object> entry : this.members.entrySet()) {
            sbS.append(", ");
            sbS.append(entry.getKey());
            sbS.append(": ");
            appendValue(sbS, entry.getValue());
        }
        sbS.append(" }");
        return sbS.toString();
    }
}
