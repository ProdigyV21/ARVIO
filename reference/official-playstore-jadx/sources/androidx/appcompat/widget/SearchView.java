package androidx.appcompat.widget;

import android.app.PendingIntent;
import android.app.SearchableInfo;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.database.Cursor;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.Editable;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.style.ImageSpan;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.view.inputmethod.InputMethodManager;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;
import androidx.credentials.exceptions.publickeycredential.DomExceptionUtils;
import androidx.media3.extractor.ts.PsExtractor;
import com.arvio.tv.R;
import java.lang.reflect.Method;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes.dex */
public class SearchView extends o2 implements n.c {

    /* JADX INFO: renamed from: u0, reason: collision with root package name */
    public static final v3 f1452u0;
    public final View A;
    public final View B;
    public final View C;
    public final ImageView D;
    public final ImageView E;
    public final ImageView F;
    public final ImageView G;
    public final View H;
    public z3 I;
    public final Rect J;
    public final Rect K;
    public final int[] L;
    public final int[] M;
    public final ImageView N;
    public final Drawable O;
    public final int P;
    public final int Q;
    public final Intent R;
    public final Intent S;
    public final CharSequence T;
    public b U;
    public a V;
    public View.OnFocusChangeListener W;

    /* JADX INFO: renamed from: a0, reason: collision with root package name */
    public c f1453a0;
    public View.OnClickListener b0;
    public boolean c0;

    /* JADX INFO: renamed from: d0, reason: collision with root package name */
    public boolean f1454d0;

    /* JADX INFO: renamed from: e0, reason: collision with root package name */
    public androidx.cursoradapter.widget.b f1455e0;

    /* JADX INFO: renamed from: f0, reason: collision with root package name */
    public boolean f1456f0;

    /* JADX INFO: renamed from: g0, reason: collision with root package name */
    public CharSequence f1457g0;

    /* JADX INFO: renamed from: h0, reason: collision with root package name */
    public boolean f1458h0;

    /* JADX INFO: renamed from: i0, reason: collision with root package name */
    public boolean f1459i0;

    /* JADX INFO: renamed from: j0, reason: collision with root package name */
    public int f1460j0;

    /* JADX INFO: renamed from: k0, reason: collision with root package name */
    public boolean f1461k0;

    /* JADX INFO: renamed from: l0, reason: collision with root package name */
    public String f1462l0;

    /* JADX INFO: renamed from: m0, reason: collision with root package name */
    public CharSequence f1463m0;

    /* JADX INFO: renamed from: n0, reason: collision with root package name */
    public boolean f1464n0;

    /* JADX INFO: renamed from: o0, reason: collision with root package name */
    public int f1465o0;

    /* JADX INFO: renamed from: p0, reason: collision with root package name */
    public SearchableInfo f1466p0;

    /* JADX INFO: renamed from: q0, reason: collision with root package name */
    public Bundle f1467q0;

    /* JADX INFO: renamed from: r0, reason: collision with root package name */
    public final o3 f1468r0;

    /* JADX INFO: renamed from: s0, reason: collision with root package name */
    public final o3 f1469s0;

    /* JADX INFO: renamed from: t0, reason: collision with root package name */
    public final WeakHashMap f1470t0;
    public final SearchAutoComplete z;

    public static class SearchAutoComplete extends t {

        /* JADX INFO: renamed from: o, reason: collision with root package name */
        public int f1471o;

        /* JADX INFO: renamed from: p, reason: collision with root package name */
        public SearchView f1472p;

        /* JADX INFO: renamed from: q, reason: collision with root package name */
        public boolean f1473q;

        /* JADX INFO: renamed from: r, reason: collision with root package name */
        public final y3 f1474r;

        public SearchAutoComplete(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            this.f1474r = new y3(this);
            this.f1471o = getThreshold();
        }

        private int getSearchViewTextMinWidthDp() {
            Configuration configuration = getResources().getConfiguration();
            int i10 = configuration.screenWidthDp;
            int i11 = configuration.screenHeightDp;
            if (i10 >= 960 && i11 >= 720 && configuration.orientation == 2) {
                return 256;
            }
            if (i10 >= 600) {
                return PsExtractor.AUDIO_STREAM;
            }
            if (i10 < 640 || i11 < 480) {
                return 160;
            }
            return PsExtractor.AUDIO_STREAM;
        }

        public final void a() {
            if (Build.VERSION.SDK_INT >= 29) {
                u3.b(this, 1);
                if (enoughToFilter()) {
                    showDropDown();
                    return;
                }
                return;
            }
            v3 v3Var = SearchView.f1452u0;
            v3Var.getClass();
            v3.a();
            Method method = v3Var.f1800c;
            if (method != null) {
                try {
                    method.invoke(this, Boolean.TRUE);
                } catch (Exception unused) {
                }
            }
        }

        @Override // android.widget.AutoCompleteTextView
        public final boolean enoughToFilter() {
            return this.f1471o <= 0 || super.enoughToFilter();
        }

        @Override // androidx.appcompat.widget.t, android.widget.TextView, android.view.View
        public final InputConnection onCreateInputConnection(EditorInfo editorInfo) {
            InputConnection inputConnectionOnCreateInputConnection = super.onCreateInputConnection(editorInfo);
            if (this.f1473q) {
                y3 y3Var = this.f1474r;
                removeCallbacks(y3Var);
                post(y3Var);
            }
            return inputConnectionOnCreateInputConnection;
        }

        @Override // android.view.View
        public final void onFinishInflate() {
            super.onFinishInflate();
            setMinWidth((int) TypedValue.applyDimension(1, getSearchViewTextMinWidthDp(), getResources().getDisplayMetrics()));
        }

        @Override // android.widget.AutoCompleteTextView, android.widget.TextView, android.view.View
        public final void onFocusChanged(boolean z, int i10, Rect rect) {
            super.onFocusChanged(z, i10, rect);
            SearchView searchView = this.f1472p;
            searchView.v(searchView.f1454d0);
            searchView.post(searchView.f1468r0);
            if (searchView.z.hasFocus()) {
                searchView.k();
            }
        }

        @Override // android.widget.AutoCompleteTextView, android.widget.TextView, android.view.View
        public final boolean onKeyPreIme(int i10, KeyEvent keyEvent) {
            if (i10 == 4) {
                if (keyEvent.getAction() == 0 && keyEvent.getRepeatCount() == 0) {
                    KeyEvent.DispatcherState keyDispatcherState = getKeyDispatcherState();
                    if (keyDispatcherState != null) {
                        keyDispatcherState.startTracking(keyEvent, this);
                    }
                    return true;
                }
                if (keyEvent.getAction() == 1) {
                    KeyEvent.DispatcherState keyDispatcherState2 = getKeyDispatcherState();
                    if (keyDispatcherState2 != null) {
                        keyDispatcherState2.handleUpEvent(keyEvent);
                    }
                    if (keyEvent.isTracking() && !keyEvent.isCanceled()) {
                        this.f1472p.clearFocus();
                        setImeVisibility(false);
                        return true;
                    }
                }
            }
            return super.onKeyPreIme(i10, keyEvent);
        }

        @Override // android.widget.AutoCompleteTextView, android.widget.TextView, android.view.View
        public final void onWindowFocusChanged(boolean z) {
            super.onWindowFocusChanged(z);
            if (z && this.f1472p.hasFocus() && getVisibility() == 0) {
                this.f1473q = true;
                Context context = getContext();
                v3 v3Var = SearchView.f1452u0;
                if (context.getResources().getConfiguration().orientation == 2) {
                    a();
                }
            }
        }

        @Override // android.widget.AutoCompleteTextView
        public final void performCompletion() {
        }

        @Override // android.widget.AutoCompleteTextView
        public final void replaceText(CharSequence charSequence) {
        }

        public void setImeVisibility(boolean z) {
            InputMethodManager inputMethodManager = (InputMethodManager) getContext().getSystemService("input_method");
            y3 y3Var = this.f1474r;
            if (!z) {
                this.f1473q = false;
                removeCallbacks(y3Var);
                inputMethodManager.hideSoftInputFromWindow(getWindowToken(), 0);
            } else {
                if (!inputMethodManager.isActive(this)) {
                    this.f1473q = true;
                    return;
                }
                this.f1473q = false;
                removeCallbacks(y3Var);
                inputMethodManager.showSoftInput(this, 0);
            }
        }

        public void setSearchView(SearchView searchView) {
            this.f1472p = searchView;
        }

        @Override // android.widget.AutoCompleteTextView
        public void setThreshold(int i10) {
            super.setThreshold(i10);
            this.f1471o = i10;
        }
    }

    public interface a {
        boolean a();
    }

    public interface b {
        boolean a();

        boolean b();
    }

    public interface c {
        boolean a();

        boolean b();
    }

    static {
        v3 v3Var = null;
        if (Build.VERSION.SDK_INT < 29) {
            v3 v3Var2 = new v3();
            v3Var2.f1798a = null;
            v3Var2.f1799b = null;
            v3Var2.f1800c = null;
            v3.a();
            try {
                Method declaredMethod = AutoCompleteTextView.class.getDeclaredMethod("doBeforeTextChanged", null);
                v3Var2.f1798a = declaredMethod;
                declaredMethod.setAccessible(true);
            } catch (NoSuchMethodException unused) {
            }
            try {
                Method declaredMethod2 = AutoCompleteTextView.class.getDeclaredMethod("doAfterTextChanged", null);
                v3Var2.f1799b = declaredMethod2;
                declaredMethod2.setAccessible(true);
            } catch (NoSuchMethodException unused2) {
            }
            try {
                Method method = AutoCompleteTextView.class.getMethod("ensureImeVisible", Boolean.TYPE);
                v3Var2.f1800c = method;
                method.setAccessible(true);
            } catch (NoSuchMethodException unused3) {
            }
            v3Var = v3Var2;
        }
        f1452u0 = v3Var;
    }

    public SearchView(Context context) {
        this(context, null);
    }

    private int getPreferredHeight() {
        return getContext().getResources().getDimensionPixelSize(R.dimen.abc_search_view_preferred_height);
    }

    private int getPreferredWidth() {
        return getContext().getResources().getDimensionPixelSize(R.dimen.abc_search_view_preferred_width);
    }

    private void setQuery(CharSequence charSequence) {
        SearchAutoComplete searchAutoComplete = this.z;
        searchAutoComplete.setText(charSequence);
        searchAutoComplete.setSelection(TextUtils.isEmpty(charSequence) ? 0 : charSequence.length());
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void clearFocus() {
        this.f1459i0 = true;
        super.clearFocus();
        SearchAutoComplete searchAutoComplete = this.z;
        searchAutoComplete.clearFocus();
        searchAutoComplete.setImeVisibility(false);
        this.f1459i0 = false;
    }

    public int getImeOptions() {
        return this.z.getImeOptions();
    }

    public int getInputType() {
        return this.z.getInputType();
    }

    public int getMaxWidth() {
        return this.f1460j0;
    }

    public CharSequence getQuery() {
        return this.z.getText();
    }

    public CharSequence getQueryHint() {
        CharSequence charSequence = this.f1457g0;
        if (charSequence != null) {
            return charSequence;
        }
        SearchableInfo searchableInfo = this.f1466p0;
        return (searchableInfo == null || searchableInfo.getHintId() == 0) ? this.T : getContext().getText(this.f1466p0.getHintId());
    }

    public int getSuggestionCommitIconResId() {
        return this.Q;
    }

    public int getSuggestionRowLayout() {
        return this.P;
    }

    public androidx.cursoradapter.widget.b getSuggestionsAdapter() {
        return this.f1455e0;
    }

    public final Intent i(String str, Uri uri, String str2, String str3) {
        Intent intent = new Intent(str);
        intent.addFlags(268435456);
        if (uri != null) {
            intent.setData(uri);
        }
        intent.putExtra("user_query", this.f1463m0);
        if (str3 != null) {
            intent.putExtra("query", str3);
        }
        if (str2 != null) {
            intent.putExtra("intent_extra_data_key", str2);
        }
        Bundle bundle = this.f1467q0;
        if (bundle != null) {
            intent.putExtra("app_data", bundle);
        }
        intent.setComponent(this.f1466p0.getSearchActivity());
        return intent;
    }

    public final Intent j(Intent intent, SearchableInfo searchableInfo) {
        ComponentName searchActivity = searchableInfo.getSearchActivity();
        Intent intent2 = new Intent("android.intent.action.SEARCH");
        intent2.setComponent(searchActivity);
        PendingIntent activity = PendingIntent.getActivity(getContext(), 0, intent2, 1107296256);
        Bundle bundle = new Bundle();
        Bundle bundle2 = this.f1467q0;
        if (bundle2 != null) {
            bundle.putParcelable("app_data", bundle2);
        }
        Intent intent3 = new Intent(intent);
        Resources resources = getResources();
        String string = searchableInfo.getVoiceLanguageModeId() != 0 ? resources.getString(searchableInfo.getVoiceLanguageModeId()) : "free_form";
        String string2 = searchableInfo.getVoicePromptTextId() != 0 ? resources.getString(searchableInfo.getVoicePromptTextId()) : null;
        String string3 = searchableInfo.getVoiceLanguageId() != 0 ? resources.getString(searchableInfo.getVoiceLanguageId()) : null;
        int voiceMaxResults = searchableInfo.getVoiceMaxResults() != 0 ? searchableInfo.getVoiceMaxResults() : 1;
        intent3.putExtra("android.speech.extra.LANGUAGE_MODEL", string);
        intent3.putExtra("android.speech.extra.PROMPT", string2);
        intent3.putExtra("android.speech.extra.LANGUAGE", string3);
        intent3.putExtra("android.speech.extra.MAX_RESULTS", voiceMaxResults);
        intent3.putExtra("calling_package", searchActivity != null ? searchActivity.flattenToShortString() : null);
        intent3.putExtra("android.speech.extra.RESULTS_PENDINGINTENT", activity);
        intent3.putExtra("android.speech.extra.RESULTS_PENDINGINTENT_BUNDLE", bundle);
        return intent3;
    }

    public final void k() {
        int i10 = Build.VERSION.SDK_INT;
        SearchAutoComplete searchAutoComplete = this.z;
        if (i10 >= 29) {
            u3.a(searchAutoComplete);
            return;
        }
        v3 v3Var = f1452u0;
        v3Var.getClass();
        v3.a();
        Method method = v3Var.f1798a;
        if (method != null) {
            try {
                method.invoke(searchAutoComplete, null);
            } catch (Exception unused) {
            }
        }
        v3Var.getClass();
        v3.a();
        Method method2 = v3Var.f1799b;
        if (method2 != null) {
            try {
                method2.invoke(searchAutoComplete, null);
            } catch (Exception unused2) {
            }
        }
    }

    public final void l() {
        SearchAutoComplete searchAutoComplete = this.z;
        if (!TextUtils.isEmpty(searchAutoComplete.getText())) {
            searchAutoComplete.setText("");
            searchAutoComplete.requestFocus();
            searchAutoComplete.setImeVisibility(true);
        } else if (this.c0) {
            a aVar = this.V;
            if (aVar == null || !aVar.a()) {
                clearFocus();
                v(true);
            }
        }
    }

    public final boolean m(int i10) {
        int position;
        String strI;
        c cVar = this.f1453a0;
        if (cVar != null && cVar.b()) {
            return false;
        }
        Cursor cursor = this.f1455e0.f2408m;
        if (cursor != null && cursor.moveToPosition(i10)) {
            Intent intentI = null;
            try {
                int i11 = c4.H;
                String strI2 = c4.i(cursor, cursor.getColumnIndex("suggest_intent_action"));
                if (strI2 == null) {
                    strI2 = this.f1466p0.getSuggestIntentAction();
                }
                if (strI2 == null) {
                    strI2 = "android.intent.action.SEARCH";
                }
                String strI3 = c4.i(cursor, cursor.getColumnIndex("suggest_intent_data"));
                if (strI3 == null) {
                    strI3 = this.f1466p0.getSuggestIntentData();
                }
                if (strI3 != null && (strI = c4.i(cursor, cursor.getColumnIndex("suggest_intent_data_id"))) != null) {
                    strI3 = strI3 + DomExceptionUtils.SEPARATOR + Uri.encode(strI);
                }
                intentI = i(strI2, strI3 == null ? null : Uri.parse(strI3), c4.i(cursor, cursor.getColumnIndex("suggest_intent_extra_data")), c4.i(cursor, cursor.getColumnIndex("suggest_intent_query")));
            } catch (RuntimeException e5) {
                try {
                    position = cursor.getPosition();
                } catch (RuntimeException unused) {
                    position = -1;
                }
                Log.w("SearchView", "Search suggestions cursor at row " + position + " returned exception.", e5);
            }
            if (intentI != null) {
                try {
                    getContext().startActivity(intentI);
                } catch (RuntimeException e6) {
                    Log.e("SearchView", "Failed launch activity: " + intentI, e6);
                }
            }
        }
        SearchAutoComplete searchAutoComplete = this.z;
        searchAutoComplete.setImeVisibility(false);
        searchAutoComplete.dismissDropDown();
        return true;
    }

    public final void n(int i10) {
        c cVar = this.f1453a0;
        if (cVar == null || !cVar.a()) {
            Editable text = this.z.getText();
            Cursor cursor = this.f1455e0.f2408m;
            if (cursor == null) {
                return;
            }
            if (!cursor.moveToPosition(i10)) {
                setQuery(text);
                return;
            }
            String strD = this.f1455e0.d(cursor);
            if (strD != null) {
                setQuery(strD);
            } else {
                setQuery(text);
            }
        }
    }

    public final void o(CharSequence charSequence) {
        setQuery(charSequence);
    }

    @Override // n.c
    public final void onActionViewCollapsed() {
        SearchAutoComplete searchAutoComplete = this.z;
        searchAutoComplete.setText("");
        searchAutoComplete.setSelection(searchAutoComplete.length());
        this.f1463m0 = "";
        clearFocus();
        v(true);
        searchAutoComplete.setImeOptions(this.f1465o0);
        this.f1464n0 = false;
    }

    @Override // n.c
    public final void onActionViewExpanded() {
        if (this.f1464n0) {
            return;
        }
        this.f1464n0 = true;
        SearchAutoComplete searchAutoComplete = this.z;
        int imeOptions = searchAutoComplete.getImeOptions();
        this.f1465o0 = imeOptions;
        searchAutoComplete.setImeOptions(imeOptions | 33554432);
        searchAutoComplete.setText("");
        setIconified(false);
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onDetachedFromWindow() {
        removeCallbacks(this.f1468r0);
        post(this.f1469s0);
        super.onDetachedFromWindow();
    }

    @Override // androidx.appcompat.widget.o2, android.view.ViewGroup, android.view.View
    public final void onLayout(boolean z, int i10, int i11, int i12, int i13) {
        super.onLayout(z, i10, i11, i12, i13);
        if (z) {
            SearchAutoComplete searchAutoComplete = this.z;
            int[] iArr = this.L;
            searchAutoComplete.getLocationInWindow(iArr);
            int[] iArr2 = this.M;
            getLocationInWindow(iArr2);
            int i14 = iArr[1] - iArr2[1];
            int i15 = iArr[0] - iArr2[0];
            int width = searchAutoComplete.getWidth() + i15;
            int height = searchAutoComplete.getHeight() + i14;
            Rect rect = this.J;
            rect.set(i15, i14, width, height);
            int i16 = rect.left;
            int i17 = rect.right;
            int i18 = i13 - i11;
            Rect rect2 = this.K;
            rect2.set(i16, 0, i17, i18);
            z3 z3Var = this.I;
            if (z3Var == null) {
                z3 z3Var2 = new z3(rect2, rect, searchAutoComplete);
                this.I = z3Var2;
                setTouchDelegate(z3Var2);
            } else {
                z3Var.f1851b.set(rect2);
                Rect rect3 = z3Var.f1853d;
                rect3.set(rect2);
                int i19 = -z3Var.f1854e;
                rect3.inset(i19, i19);
                z3Var.f1852c.set(rect);
            }
        }
    }

    @Override // androidx.appcompat.widget.o2, android.view.View
    public final void onMeasure(int i10, int i11) {
        int i12;
        if (this.f1454d0) {
            super.onMeasure(i10, i11);
            return;
        }
        int mode = View.MeasureSpec.getMode(i10);
        int size = View.MeasureSpec.getSize(i10);
        if (mode == Integer.MIN_VALUE) {
            int i13 = this.f1460j0;
            size = i13 > 0 ? Math.min(i13, size) : Math.min(getPreferredWidth(), size);
        } else if (mode == 0) {
            size = this.f1460j0;
            if (size <= 0) {
                size = getPreferredWidth();
            }
        } else if (mode == 1073741824 && (i12 = this.f1460j0) > 0) {
            size = Math.min(i12, size);
        }
        int mode2 = View.MeasureSpec.getMode(i11);
        int size2 = View.MeasureSpec.getSize(i11);
        if (mode2 == Integer.MIN_VALUE) {
            size2 = Math.min(getPreferredHeight(), size2);
        } else if (mode2 == 0) {
            size2 = getPreferredHeight();
        }
        super.onMeasure(View.MeasureSpec.makeMeasureSpec(size, 1073741824), View.MeasureSpec.makeMeasureSpec(size2, 1073741824));
    }

    @Override // android.view.View
    public final void onRestoreInstanceState(Parcelable parcelable) {
        if (!(parcelable instanceof x3)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        x3 x3Var = (x3) parcelable;
        super.onRestoreInstanceState(x3Var.f22549i);
        v(x3Var.f1834m);
        requestLayout();
    }

    @Override // android.view.View
    public final Parcelable onSaveInstanceState() {
        x3 x3Var = new x3(super.onSaveInstanceState());
        x3Var.f1834m = this.f1454d0;
        return x3Var;
    }

    @Override // android.view.View
    public final void onWindowFocusChanged(boolean z) {
        super.onWindowFocusChanged(z);
        post(this.f1468r0);
    }

    public final void p() {
        SearchAutoComplete searchAutoComplete = this.z;
        Editable text = searchAutoComplete.getText();
        if (text == null || TextUtils.getTrimmedLength(text) <= 0) {
            return;
        }
        b bVar = this.U;
        if (bVar != null) {
            text.toString();
            if (bVar.a()) {
                return;
            }
        }
        if (this.f1466p0 != null) {
            getContext().startActivity(i("android.intent.action.SEARCH", null, null, text.toString()));
        }
        searchAutoComplete.setImeVisibility(false);
        searchAutoComplete.dismissDropDown();
    }

    public final void q() {
        boolean zIsEmpty = TextUtils.isEmpty(this.z.getText());
        int i10 = (!zIsEmpty || (this.c0 && !this.f1464n0)) ? 0 : 8;
        ImageView imageView = this.F;
        imageView.setVisibility(i10);
        Drawable drawable = imageView.getDrawable();
        if (drawable != null) {
            drawable.setState(!zIsEmpty ? ViewGroup.ENABLED_STATE_SET : ViewGroup.EMPTY_STATE_SET);
        }
    }

    public final void r() {
        int[] iArr = this.z.hasFocus() ? ViewGroup.FOCUSED_STATE_SET : ViewGroup.EMPTY_STATE_SET;
        Drawable background = this.B.getBackground();
        if (background != null) {
            background.setState(iArr);
        }
        Drawable background2 = this.C.getBackground();
        if (background2 != null) {
            background2.setState(iArr);
        }
        invalidate();
    }

    @Override // android.view.ViewGroup, android.view.View
    public final boolean requestFocus(int i10, Rect rect) {
        if (this.f1459i0 || !isFocusable()) {
            return false;
        }
        if (this.f1454d0) {
            return super.requestFocus(i10, rect);
        }
        boolean zRequestFocus = this.z.requestFocus(i10, rect);
        if (zRequestFocus) {
            v(false);
        }
        return zRequestFocus;
    }

    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    public final void s() {
        CharSequence queryHint = getQueryHint();
        CharSequence charSequence = queryHint;
        if (queryHint == null) {
            charSequence = "";
        }
        boolean z = this.c0;
        SearchAutoComplete searchAutoComplete = this.z;
        CharSequence charSequence2 = charSequence;
        if (z) {
            Drawable drawable = this.O;
            charSequence2 = charSequence;
            if (drawable != null) {
                int textSize = (int) (((double) searchAutoComplete.getTextSize()) * 1.25d);
                drawable.setBounds(0, 0, textSize, textSize);
                SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder("   ");
                spannableStringBuilder.setSpan(new ImageSpan(drawable), 1, 2, 33);
                spannableStringBuilder.append(charSequence);
                charSequence2 = spannableStringBuilder;
            }
        }
        searchAutoComplete.setHint(charSequence2);
    }

    public void setAppSearchData(Bundle bundle) {
        this.f1467q0 = bundle;
    }

    public void setIconified(boolean z) {
        if (z) {
            l();
            return;
        }
        v(false);
        SearchAutoComplete searchAutoComplete = this.z;
        searchAutoComplete.requestFocus();
        searchAutoComplete.setImeVisibility(true);
        View.OnClickListener onClickListener = this.b0;
        if (onClickListener != null) {
            onClickListener.onClick(this);
        }
    }

    public void setIconifiedByDefault(boolean z) {
        if (this.c0 == z) {
            return;
        }
        this.c0 = z;
        v(z);
        s();
    }

    public void setImeOptions(int i10) {
        this.z.setImeOptions(i10);
    }

    public void setInputType(int i10) {
        this.z.setInputType(i10);
    }

    public void setMaxWidth(int i10) {
        this.f1460j0 = i10;
        requestLayout();
    }

    public void setOnCloseListener(a aVar) {
        this.V = aVar;
    }

    public void setOnQueryTextFocusChangeListener(View.OnFocusChangeListener onFocusChangeListener) {
        this.W = onFocusChangeListener;
    }

    public void setOnQueryTextListener(b bVar) {
        this.U = bVar;
    }

    public void setOnSearchClickListener(View.OnClickListener onClickListener) {
        this.b0 = onClickListener;
    }

    public void setOnSuggestionListener(c cVar) {
        this.f1453a0 = cVar;
    }

    public void setQueryHint(CharSequence charSequence) {
        this.f1457g0 = charSequence;
        s();
    }

    public void setQueryRefinementEnabled(boolean z) {
        this.f1458h0 = z;
        androidx.cursoradapter.widget.b bVar = this.f1455e0;
        if (bVar instanceof c4) {
            ((c4) bVar).z = z ? 2 : 1;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:34:0x0098  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void setSearchableInfo(android.app.SearchableInfo r7) {
        /*
            r6 = this;
            r6.f1466p0 = r7
            r0 = 0
            r1 = 1
            androidx.appcompat.widget.SearchView$SearchAutoComplete r2 = r6.z
            if (r7 == 0) goto L65
            int r7 = r7.getSuggestThreshold()
            r2.setThreshold(r7)
            android.app.SearchableInfo r7 = r6.f1466p0
            int r7 = r7.getImeOptions()
            r2.setImeOptions(r7)
            android.app.SearchableInfo r7 = r6.f1466p0
            int r7 = r7.getInputType()
            r3 = r7 & 15
            if (r3 != r1) goto L31
            r3 = -65537(0xfffffffffffeffff, float:NaN)
            r7 = r7 & r3
            android.app.SearchableInfo r3 = r6.f1466p0
            java.lang.String r3 = r3.getSuggestAuthority()
            if (r3 == 0) goto L31
            r3 = 589824(0x90000, float:8.2652E-40)
            r7 = r7 | r3
        L31:
            r2.setInputType(r7)
            androidx.cursoradapter.widget.b r7 = r6.f1455e0
            if (r7 == 0) goto L3b
            r7.c(r0)
        L3b:
            android.app.SearchableInfo r7 = r6.f1466p0
            java.lang.String r7 = r7.getSuggestAuthority()
            if (r7 == 0) goto L62
            androidx.appcompat.widget.c4 r7 = new androidx.appcompat.widget.c4
            android.content.Context r3 = r6.getContext()
            android.app.SearchableInfo r4 = r6.f1466p0
            java.util.WeakHashMap r5 = r6.f1470t0
            r7.<init>(r3, r6, r4, r5)
            r6.f1455e0 = r7
            r2.setAdapter(r7)
            androidx.cursoradapter.widget.b r7 = r6.f1455e0
            androidx.appcompat.widget.c4 r7 = (androidx.appcompat.widget.c4) r7
            boolean r3 = r6.f1458h0
            if (r3 == 0) goto L5f
            r3 = 2
            goto L60
        L5f:
            r3 = r1
        L60:
            r7.z = r3
        L62:
            r6.s()
        L65:
            android.app.SearchableInfo r7 = r6.f1466p0
            r3 = 0
            if (r7 == 0) goto L98
            boolean r7 = r7.getVoiceSearchEnabled()
            if (r7 == 0) goto L98
            android.app.SearchableInfo r7 = r6.f1466p0
            boolean r7 = r7.getVoiceSearchLaunchWebSearch()
            if (r7 == 0) goto L7b
            android.content.Intent r0 = r6.R
            goto L85
        L7b:
            android.app.SearchableInfo r7 = r6.f1466p0
            boolean r7 = r7.getVoiceSearchLaunchRecognizer()
            if (r7 == 0) goto L85
            android.content.Intent r0 = r6.S
        L85:
            if (r0 == 0) goto L98
            android.content.Context r7 = r6.getContext()
            android.content.pm.PackageManager r7 = r7.getPackageManager()
            r4 = 65536(0x10000, float:9.1835E-41)
            android.content.pm.ResolveInfo r7 = r7.resolveActivity(r0, r4)
            if (r7 == 0) goto L98
            goto L99
        L98:
            r1 = r3
        L99:
            r6.f1461k0 = r1
            if (r1 == 0) goto La3
            java.lang.String r7 = "nm"
            r2.setPrivateImeOptions(r7)
        La3:
            boolean r7 = r6.f1454d0
            r6.v(r7)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.widget.SearchView.setSearchableInfo(android.app.SearchableInfo):void");
    }

    public void setSubmitButtonEnabled(boolean z) {
        this.f1456f0 = z;
        v(this.f1454d0);
    }

    public void setSuggestionsAdapter(androidx.cursoradapter.widget.b bVar) {
        this.f1455e0 = bVar;
        this.z.setAdapter(bVar);
    }

    public final void t() {
        this.C.setVisibility(((this.f1456f0 || this.f1461k0) && !this.f1454d0 && (this.E.getVisibility() == 0 || this.G.getVisibility() == 0)) ? 0 : 8);
    }

    public final void u(boolean z) {
        boolean z5 = this.f1456f0;
        this.E.setVisibility((!z5 || !(z5 || this.f1461k0) || this.f1454d0 || !hasFocus() || (!z && this.f1461k0)) ? 8 : 0);
    }

    public final void v(boolean z) {
        this.f1454d0 = z;
        int i10 = 8;
        int i11 = z ? 0 : 8;
        boolean zIsEmpty = TextUtils.isEmpty(this.z.getText());
        this.D.setVisibility(i11);
        u(!zIsEmpty);
        this.A.setVisibility(z ? 8 : 0);
        ImageView imageView = this.N;
        imageView.setVisibility((imageView.getDrawable() == null || this.c0) ? 8 : 0);
        q();
        if (this.f1461k0 && !this.f1454d0 && zIsEmpty) {
            this.E.setVisibility(8);
            i10 = 0;
        }
        this.G.setVisibility(i10);
        t();
    }

    public SearchView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R.attr.searchViewStyle);
    }

    public SearchView(Context context, AttributeSet attributeSet, int i10) {
        super(context, attributeSet, i10);
        this.J = new Rect();
        this.K = new Rect();
        this.L = new int[2];
        this.M = new int[2];
        this.f1468r0 = new o3(this, 0);
        this.f1469s0 = new o3(this, 1);
        this.f1470t0 = new WeakHashMap();
        r3 r3Var = new r3(this);
        s3 s3Var = new s3(this);
        t3 t3Var = new t3(this);
        w0 w0Var = new w0(this, 1);
        p2 p2Var = new p2(this, 1);
        n3 n3Var = new n3(this);
        int[] iArr = j.a.f19036u;
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, iArr, i10, 0);
        androidx.appcompat.app.i1 i1Var = new androidx.appcompat.app.i1(context, typedArrayObtainStyledAttributes);
        androidx.core.view.b2.g(this, context, iArr, attributeSet, typedArrayObtainStyledAttributes, i10);
        LayoutInflater.from(context).inflate(typedArrayObtainStyledAttributes.getResourceId(9, R.layout.abc_search_view), (ViewGroup) this, true);
        SearchAutoComplete searchAutoComplete = (SearchAutoComplete) findViewById(R.id.search_src_text);
        this.z = searchAutoComplete;
        searchAutoComplete.setSearchView(this);
        this.A = findViewById(R.id.search_edit_frame);
        View viewFindViewById = findViewById(R.id.search_plate);
        this.B = viewFindViewById;
        View viewFindViewById2 = findViewById(R.id.submit_area);
        this.C = viewFindViewById2;
        ImageView imageView = (ImageView) findViewById(R.id.search_button);
        this.D = imageView;
        ImageView imageView2 = (ImageView) findViewById(R.id.search_go_btn);
        this.E = imageView2;
        ImageView imageView3 = (ImageView) findViewById(R.id.search_close_btn);
        this.F = imageView3;
        ImageView imageView4 = (ImageView) findViewById(R.id.search_voice_btn);
        this.G = imageView4;
        ImageView imageView5 = (ImageView) findViewById(R.id.search_mag_icon);
        this.N = imageView5;
        viewFindViewById.setBackground(i1Var.T(10));
        viewFindViewById2.setBackground(i1Var.T(14));
        imageView.setImageDrawable(i1Var.T(13));
        imageView2.setImageDrawable(i1Var.T(7));
        imageView3.setImageDrawable(i1Var.T(4));
        imageView4.setImageDrawable(i1Var.T(16));
        imageView5.setImageDrawable(i1Var.T(13));
        this.O = i1Var.T(12);
        kotlin.reflect.b0.E(imageView, getResources().getString(R.string.abc_searchview_description_search));
        this.P = typedArrayObtainStyledAttributes.getResourceId(15, R.layout.abc_search_dropdown_item_icons_2line);
        this.Q = typedArrayObtainStyledAttributes.getResourceId(5, 0);
        imageView.setOnClickListener(r3Var);
        imageView3.setOnClickListener(r3Var);
        imageView2.setOnClickListener(r3Var);
        imageView4.setOnClickListener(r3Var);
        searchAutoComplete.setOnClickListener(r3Var);
        searchAutoComplete.addTextChangedListener(n3Var);
        searchAutoComplete.setOnEditorActionListener(t3Var);
        searchAutoComplete.setOnItemClickListener(w0Var);
        searchAutoComplete.setOnItemSelectedListener(p2Var);
        searchAutoComplete.setOnKeyListener(s3Var);
        searchAutoComplete.setOnFocusChangeListener(new p3(this));
        setIconifiedByDefault(typedArrayObtainStyledAttributes.getBoolean(8, true));
        int dimensionPixelSize = typedArrayObtainStyledAttributes.getDimensionPixelSize(1, -1);
        if (dimensionPixelSize != -1) {
            setMaxWidth(dimensionPixelSize);
        }
        this.T = typedArrayObtainStyledAttributes.getText(6);
        this.f1457g0 = typedArrayObtainStyledAttributes.getText(11);
        int i11 = typedArrayObtainStyledAttributes.getInt(3, -1);
        if (i11 != -1) {
            setImeOptions(i11);
        }
        int i12 = typedArrayObtainStyledAttributes.getInt(2, -1);
        if (i12 != -1) {
            setInputType(i12);
        }
        setFocusable(typedArrayObtainStyledAttributes.getBoolean(0, true));
        i1Var.b0();
        Intent intent = new Intent("android.speech.action.WEB_SEARCH");
        this.R = intent;
        intent.addFlags(268435456);
        intent.putExtra("android.speech.extra.LANGUAGE_MODEL", "web_search");
        Intent intent2 = new Intent("android.speech.action.RECOGNIZE_SPEECH");
        this.S = intent2;
        intent2.addFlags(268435456);
        View viewFindViewById3 = findViewById(searchAutoComplete.getDropDownAnchor());
        this.H = viewFindViewById3;
        if (viewFindViewById3 != null) {
            viewFindViewById3.addOnLayoutChangeListener(new q3(this));
        }
        v(this.c0);
        s();
    }
}
