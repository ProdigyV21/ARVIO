package retrofit2.converter.gson;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import fi.iki.elonen.f;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import retrofit2.k;
import retrofit2.l;
import retrofit2.u0;

/* JADX INFO: loaded from: classes5.dex */
public final class a extends k {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Gson f21487a;

    public a(Gson gson) {
        this.f21487a = gson;
    }

    public static a c() {
        return new a(new Gson());
    }

    @Override // retrofit2.k
    public final l a(Type type) {
        TypeToken<?> typeToken = TypeToken.get(type);
        Gson gson = this.f21487a;
        return new b(gson, gson.getAdapter(typeToken));
    }

    @Override // retrofit2.k
    public final l b(Type type, Annotation[] annotationArr, u0 u0Var) {
        TypeToken<?> typeToken = TypeToken.get(type);
        Gson gson = this.f21487a;
        return new f(gson, gson.getAdapter(typeToken));
    }
}
