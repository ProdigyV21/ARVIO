package androidx.core.app;

import android.app.Person;
import android.graphics.drawable.Icon;
import androidx.core.graphics.drawable.IconCompat;

/* JADX INFO: loaded from: classes3.dex */
public abstract class v1 {
    public static w1 a(Person person) {
        CharSequence name = person.getName();
        IconCompat iconCompatB = person.getIcon() != null ? IconCompat.b(person.getIcon()) : null;
        String uri = person.getUri();
        String key = person.getKey();
        boolean zIsBot = person.isBot();
        boolean zIsImportant = person.isImportant();
        w1 w1Var = new w1();
        w1Var.f2061a = name;
        w1Var.f2062b = iconCompatB;
        w1Var.f2063c = uri;
        w1Var.f2064d = key;
        w1Var.f2065e = zIsBot;
        w1Var.f2066f = zIsImportant;
        return w1Var;
    }

    public static Person b(w1 w1Var) {
        Person.Builder name = new Person.Builder().setName(w1Var.f2061a);
        IconCompat iconCompat = w1Var.f2062b;
        Icon iconF = null;
        if (iconCompat != null) {
            iconCompat.getClass();
            iconF = j0.c.f(iconCompat, null);
        }
        return name.setIcon(iconF).setUri(w1Var.f2063c).setKey(w1Var.f2064d).setBot(w1Var.f2065e).setImportant(w1Var.f2066f).build();
    }
}
