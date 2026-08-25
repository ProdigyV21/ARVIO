package androidx.core.app;

import android.app.Notification;
import android.app.Person;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes3.dex */
public abstract class s0 {
    public static Parcelable a(Person person) {
        return person;
    }

    public static Notification.MessagingStyle.Message b(CharSequence charSequence, long j10, Person person) {
        return new Notification.MessagingStyle.Message(charSequence, j10, person);
    }
}
