package com.google.android.gms.common.server.response;

import com.google.android.gms.common.internal.t;

/* JADX INFO: loaded from: classes4.dex */
public abstract class d extends c implements t3.b {
    @Override // android.os.Parcelable
    public final int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        if (!getClass().isInstance(obj)) {
            return false;
        }
        c cVar = (c) obj;
        for (a aVar : getFieldMappings().values()) {
            if (isFieldSet(aVar)) {
                if (!cVar.isFieldSet(aVar) || !t.l(getFieldValue(aVar), cVar.getFieldValue(aVar))) {
                    return false;
                }
            } else if (cVar.isFieldSet(aVar)) {
                return false;
            }
        }
        return true;
    }

    @Override // com.google.android.gms.common.server.response.c
    public Object getValueObject(String str) {
        return null;
    }

    public int hashCode() {
        int iHashCode = 0;
        for (a aVar : getFieldMappings().values()) {
            if (isFieldSet(aVar)) {
                Object fieldValue = getFieldValue(aVar);
                t.i(fieldValue);
                iHashCode = (iHashCode * 31) + fieldValue.hashCode();
            }
        }
        return iHashCode;
    }

    @Override // com.google.android.gms.common.server.response.c
    public boolean isPrimitiveFieldSet(String str) {
        return false;
    }
}
