package io.ktor.websocket;

import androidx.fragment.app.a2;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.s;
import kotlin.collections.x;
import kotlin.text.o;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\u001a\u0014\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0004¨\u0006\u0005"}, d2 = {"parseWebSocketExtensions", "", "Lio/ktor/websocket/WebSocketExtensionHeader;", "value", "", "ktor-websockets"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class WebSocketExtensionHeaderKt {
    public static final List<WebSocketExtensionHeader> parseWebSocketExtensions(String str) {
        List listY0 = o.y0(str, new String[]{","}, 0, 6);
        ArrayList arrayList = new ArrayList(s.U(listY0, 10));
        Iterator it = listY0.iterator();
        while (it.hasNext()) {
            List listY02 = o.y0((String) it.next(), new String[]{";"}, 0, 6);
            String string = o.L0((String) x.m0(listY02)).toString();
            List listH0 = x.h0(listY02, 1);
            ArrayList arrayList2 = new ArrayList(s.U(listH0, 10));
            Iterator it2 = listH0.iterator();
            while (it2.hasNext()) {
                a2.x((String) it2.next(), arrayList2);
            }
            arrayList.add(new WebSocketExtensionHeader(string, arrayList2));
        }
        return arrayList;
    }
}
