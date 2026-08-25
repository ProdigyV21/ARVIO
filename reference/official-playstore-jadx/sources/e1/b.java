package e1;

import android.text.Editable;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.view.inputmethod.InputConnectionWrapper;
import android.widget.EditText;
import androidx.emoji2.text.o;

/* JADX INFO: loaded from: classes3.dex */
public final class b extends InputConnectionWrapper {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final EditText f15003a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final a1.a f15004b;

    public b(EditText editText, InputConnection inputConnection, EditorInfo editorInfo) {
        a1.a aVar = new a1.a();
        super(inputConnection, false);
        this.f15003a = editText;
        this.f15004b = aVar;
        if (o.c()) {
            o.a().h(editorInfo);
        }
    }

    @Override // android.view.inputmethod.InputConnectionWrapper, android.view.inputmethod.InputConnection
    public final boolean deleteSurroundingText(int i10, int i11) {
        Editable editableText = this.f15003a.getEditableText();
        this.f15004b.getClass();
        return a1.a.m(this, editableText, i10, i11, false) || super.deleteSurroundingText(i10, i11);
    }

    @Override // android.view.inputmethod.InputConnectionWrapper, android.view.inputmethod.InputConnection
    public final boolean deleteSurroundingTextInCodePoints(int i10, int i11) {
        Editable editableText = this.f15003a.getEditableText();
        this.f15004b.getClass();
        return a1.a.m(this, editableText, i10, i11, true) || super.deleteSurroundingTextInCodePoints(i10, i11);
    }
}
