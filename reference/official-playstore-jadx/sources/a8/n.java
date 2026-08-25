package a8;

import java.lang.reflect.Field;

/* JADX INFO: loaded from: classes5.dex */
public final class n extends k2.c {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Field f227b;

    public n(Field field) {
        this.f227b = field;
    }

    @Override // k2.c
    public final String c() {
        StringBuilder sb2 = new StringBuilder();
        Field field = this.f227b;
        sb2.append(p8.a0.a(field.getName()));
        sb2.append("()");
        sb2.append(m8.c.b(field.getType()));
        return sb2.toString();
    }
}
