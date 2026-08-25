package androidx.activity;

import android.view.View;
import android.view.inputmethod.InputMethodManager;
import java.lang.reflect.Field;

/* JADX INFO: loaded from: classes.dex */
public final class b0 extends y {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Field f916a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Field f917b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final Field f918c;

    public b0(Field field, Field field2, Field field3) {
        this.f916a = field;
        this.f917b = field2;
        this.f918c = field3;
    }

    @Override // androidx.activity.y
    public final boolean a(InputMethodManager inputMethodManager) {
        try {
            this.f918c.set(inputMethodManager, null);
            return true;
        } catch (IllegalAccessException unused) {
            return false;
        }
    }

    @Override // androidx.activity.y
    public final Object b(InputMethodManager inputMethodManager) {
        try {
            return this.f916a.get(inputMethodManager);
        } catch (IllegalAccessException unused) {
            return null;
        }
    }

    @Override // androidx.activity.y
    public final View c(InputMethodManager inputMethodManager) {
        try {
            return (View) this.f917b.get(inputMethodManager);
        } catch (ClassCastException | IllegalAccessException unused) {
            return null;
        }
    }
}
