package l5;

import android.util.Base64OutputStream;
import androidx.work.impl.WorkDatabase;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.Callable;
import java.util.zip.GZIPOutputStream;
import org.json.JSONArray;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes4.dex */
public final /* synthetic */ class b implements Callable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f20030a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ Object f20031b;

    public /* synthetic */ b(Object obj, int i10) {
        this.f20030a = i10;
        this.f20031b = obj;
    }

    private final Object a() {
        c cVar = (c) this.f20031b;
        synchronized (cVar) {
            ((g) cVar.f20032a.get()).g(System.currentTimeMillis(), ((t5.e) cVar.f20034c.get()).a());
        }
        return null;
    }

    @Override // java.util.concurrent.Callable
    public final Object call() {
        String string;
        switch (this.f20030a) {
            case 0:
                c cVar = (c) this.f20031b;
                synchronized (cVar) {
                    try {
                        g gVar = (g) cVar.f20032a.get();
                        ArrayList arrayListC = gVar.c();
                        gVar.b();
                        JSONArray jSONArray = new JSONArray();
                        for (int i10 = 0; i10 < arrayListC.size(); i10++) {
                            a aVar = (a) arrayListC.get(i10);
                            JSONObject jSONObject = new JSONObject();
                            jSONObject.put("agent", aVar.f20028a);
                            jSONObject.put("dates", new JSONArray((Collection) aVar.f20029b));
                            jSONArray.put(jSONObject);
                        }
                        JSONObject jSONObject2 = new JSONObject();
                        jSONObject2.put("heartbeats", jSONArray);
                        jSONObject2.put("version", "2");
                        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                        Base64OutputStream base64OutputStream = new Base64OutputStream(byteArrayOutputStream, 11);
                        try {
                            GZIPOutputStream gZIPOutputStream = new GZIPOutputStream(base64OutputStream);
                            try {
                                gZIPOutputStream.write(jSONObject2.toString().getBytes("UTF-8"));
                                gZIPOutputStream.close();
                                base64OutputStream.close();
                                string = byteArrayOutputStream.toString("UTF-8");
                            } finally {
                                try {
                                    break;
                                } catch (Throwable th) {
                                }
                            }
                        } finally {
                            try {
                                break;
                            } catch (Throwable th2) {
                            }
                        }
                    } catch (Throwable th3) {
                        throw th3;
                    }
                }
                return string;
            case 1:
                return a();
            default:
                WorkDatabase workDatabase = ((androidx.work.impl.utils.f) this.f20031b).f7011a;
                Long lQ = workDatabase.l().q("next_alarm_manager_id");
                int iLongValue = lQ != null ? (int) lQ.longValue() : 0;
                workDatabase.l().j(new androidx.work.impl.model.d("next_alarm_manager_id", Long.valueOf(iLongValue != Integer.MAX_VALUE ? iLongValue + 1 : 0)));
                return Integer.valueOf(iLongValue);
        }
    }
}
