package retrofit2.converter.gson;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonWriter;
import gb.d0;
import gb.l0;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.util.regex.Pattern;
import retrofit2.l;
import xb.i;
import xb.j;
import xc.d;

/* JADX INFO: loaded from: classes5.dex */
public final class b implements l {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public static final d0 f21488m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public static final Charset f21489n;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final Gson f21490i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final TypeAdapter f21491l;

    static {
        Pattern pattern = d0.f15633d;
        f21488m = d.i0("application/json; charset=UTF-8");
        f21489n = Charset.forName("UTF-8");
    }

    public b(Gson gson, TypeAdapter typeAdapter) {
        this.f21490i = gson;
        this.f21491l = typeAdapter;
    }

    @Override // retrofit2.l
    public final Object w(Object obj) throws IOException {
        j jVar = new j();
        JsonWriter jsonWriterNewJsonWriter = this.f21490i.newJsonWriter(new OutputStreamWriter(new i(jVar), f21489n));
        this.f21491l.write(jsonWriterNewJsonWriter, obj);
        jsonWriterNewJsonWriter.close();
        return new l0(f21488m, jVar.c0(jVar.f22759l), 1);
    }
}
