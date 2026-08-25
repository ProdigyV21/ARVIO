package androidx.core.app;

import android.app.Notification;
import android.app.Person;

/* JADX INFO: loaded from: classes3.dex */
public abstract class e1 {
    public static Notification.Builder a(Notification.Builder builder, Person person) {
        return builder.addPerson(person);
    }

    public static Notification.Action.Builder b(Notification.Action.Builder builder, int i10) {
        return builder.setSemanticAction(i10);
    }
}
