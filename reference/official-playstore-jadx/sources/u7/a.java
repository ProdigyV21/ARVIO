package u7;

import kotlin.reflect.m;

/* JADX INFO: loaded from: classes5.dex */
public abstract class a implements d {
    private Object value;

    public a(Object obj) {
        this.value = obj;
    }

    public boolean beforeChange(m<?> mVar, Object obj, Object obj2) {
        return true;
    }

    @Override // u7.c
    public Object getValue(Object obj, m<?> mVar) {
        return this.value;
    }

    @Override // u7.d
    public void setValue(Object obj, m<?> mVar, Object obj2) {
        Object obj3 = this.value;
        if (beforeChange(mVar, obj3, obj2)) {
            this.value = obj2;
            afterChange(mVar, obj3, obj2);
        }
    }

    public String toString() {
        return androidx.compose.material3.d.p(new StringBuilder("ObservableProperty(value="), this.value, ')');
    }

    public void afterChange(m mVar, Object obj, Object obj2) {
    }
}
