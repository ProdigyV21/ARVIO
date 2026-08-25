package androidx.leanback.widget;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.media.SoundPool;
import android.os.Handler;
import android.speech.SpeechRecognizer;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.SparseIntArray;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.arvio.tv.R;

/* JADX INFO: loaded from: classes3.dex */
public class SearchBar extends RelativeLayout {
    public static final /* synthetic */ int K = 0;
    public final int A;
    public final int B;
    public SpeechRecognizer C;
    public i1 D;
    public boolean E;
    public SoundPool F;
    public final SparseIntArray G;
    public boolean H;
    public final Context I;
    public b J;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public a f3003i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public SearchEditText f3004l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public SpeechOrbView f3005m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public ImageView f3006n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public String f3007o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public String f3008p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public String f3009q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public Drawable f3010r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public final Handler f3011s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public final InputMethodManager f3012t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public boolean f3013u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public Drawable f3014v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public final int f3015w;
    public final int x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public final int f3016y;
    public final int z;

    public interface a {
        void a();

        void b();

        void c();
    }

    public interface b {
        void a();
    }

    public SearchBar(Context context, AttributeSet attributeSet) {
        super(context, attributeSet, 0);
        this.f3011s = new Handler();
        this.f3013u = false;
        this.G = new SparseIntArray();
        this.H = false;
        this.I = context;
        Resources resources = getResources();
        LayoutInflater.from(getContext()).inflate(R.layout.lb_search_bar, (ViewGroup) this, true);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, getResources().getDimensionPixelSize(R.dimen.lb_search_bar_height));
        layoutParams.addRule(10, -1);
        setLayoutParams(layoutParams);
        setBackgroundColor(0);
        setClipChildren(false);
        this.f3007o = "";
        this.f3012t = (InputMethodManager) context.getSystemService("input_method");
        this.x = resources.getColor(R.color.lb_search_bar_text_speech_mode);
        this.f3015w = resources.getColor(R.color.lb_search_bar_text);
        this.B = resources.getInteger(R.integer.lb_search_bar_speech_mode_background_alpha);
        this.A = resources.getInteger(R.integer.lb_search_bar_text_mode_background_alpha);
        this.z = resources.getColor(R.color.lb_search_bar_hint_speech_mode);
        this.f3016y = resources.getColor(R.color.lb_search_bar_hint);
    }

    public final void a() {
        this.f3012t.hideSoftInputFromWindow(this.f3004l.getWindowToken(), 0);
    }

    public final void b() {
        if (this.H) {
            return;
        }
        if (!hasFocus()) {
            requestFocus();
        }
        if (this.D != null) {
            this.f3004l.setText("");
            this.f3004l.setHint("");
            this.D.a();
            this.H = true;
            return;
        }
        if (this.C == null) {
            return;
        }
        if (getContext().checkCallingOrSelfPermission("android.permission.RECORD_AUDIO") != 0) {
            b bVar = this.J;
            if (bVar == null) {
                throw new IllegalStateException("android.permission.RECORD_AUDIO required for search");
            }
            bVar.a();
            return;
        }
        this.H = true;
        this.f3004l.setText("");
        Intent intent = new Intent("android.speech.action.RECOGNIZE_SPEECH");
        intent.putExtra("android.speech.extra.LANGUAGE_MODEL", "free_form");
        intent.putExtra("android.speech.extra.PARTIAL_RESULTS", true);
        this.C.setRecognitionListener(new c1(this));
        this.E = true;
        this.C.startListening(intent);
    }

    public final void c() {
        if (this.H) {
            this.f3004l.setText(this.f3007o);
            this.f3004l.setHint(this.f3008p);
            this.H = false;
            if (this.D != null || this.C == null) {
                return;
            }
            this.f3005m.c();
            if (this.E) {
                this.C.cancel();
                this.E = false;
            }
            this.C.setRecognitionListener(null);
        }
    }

    public final void d() {
        String string = getResources().getString(R.string.lb_search_bar_hint);
        if (!TextUtils.isEmpty(this.f3009q)) {
            string = this.f3005m.isFocused() ? getResources().getString(R.string.lb_search_bar_hint_with_title_speech, this.f3009q) : getResources().getString(R.string.lb_search_bar_hint_with_title, this.f3009q);
        } else if (this.f3005m.isFocused()) {
            string = getResources().getString(R.string.lb_search_bar_hint_speech);
        }
        this.f3008p = string;
        SearchEditText searchEditText = this.f3004l;
        if (searchEditText != null) {
            searchEditText.setHint(string);
        }
    }

    public final void e(boolean z) {
        if (z) {
            this.f3014v.setAlpha(this.B);
            boolean zIsFocused = this.f3005m.isFocused();
            int i10 = this.z;
            if (zIsFocused) {
                this.f3004l.setTextColor(i10);
                this.f3004l.setHintTextColor(i10);
            } else {
                this.f3004l.setTextColor(this.x);
                this.f3004l.setHintTextColor(i10);
            }
        } else {
            this.f3014v.setAlpha(this.A);
            this.f3004l.setTextColor(this.f3015w);
            this.f3004l.setHintTextColor(this.f3016y);
        }
        d();
    }

    public Drawable getBadgeDrawable() {
        return this.f3010r;
    }

    public CharSequence getHint() {
        return this.f3008p;
    }

    public String getTitle() {
        return this.f3009q;
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.F = new SoundPool(2, 1, 0);
        int[] iArr = {R.raw.lb_voice_failure, R.raw.lb_voice_open, R.raw.lb_voice_no_input, R.raw.lb_voice_success};
        for (int i10 = 0; i10 < 4; i10++) {
            int i11 = iArr[i10];
            this.G.put(i11, this.F.load(this.I, i11, 1));
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onDetachedFromWindow() {
        c();
        this.F.release();
        super.onDetachedFromWindow();
    }

    @Override // android.view.View
    public final void onFinishInflate() {
        super.onFinishInflate();
        this.f3014v = ((RelativeLayout) findViewById(R.id.lb_search_bar_items)).getBackground();
        this.f3004l = (SearchEditText) findViewById(R.id.lb_search_text_editor);
        ImageView imageView = (ImageView) findViewById(R.id.lb_search_bar_badge);
        this.f3006n = imageView;
        Drawable drawable = this.f3010r;
        if (drawable != null) {
            imageView.setImageDrawable(drawable);
        }
        this.f3004l.setOnFocusChangeListener(new u0(this, 0));
        this.f3004l.addTextChangedListener(new w0(this, new v0(this, 0)));
        this.f3004l.setOnKeyboardDismissListener(new x0(this));
        this.f3004l.setOnEditorActionListener(new a1(this));
        this.f3004l.setPrivateImeOptions("escapeNorth,voiceDismiss");
        SpeechOrbView speechOrbView = (SpeechOrbView) findViewById(R.id.lb_search_bar_speech_orb);
        this.f3005m = speechOrbView;
        speechOrbView.setOnOrbClickedListener(new b1(this));
        this.f3005m.setOnFocusChangeListener(new u0(this, 1));
        e(hasFocus());
        d();
    }

    public void setBadgeDrawable(Drawable drawable) {
        this.f3010r = drawable;
        ImageView imageView = this.f3006n;
        if (imageView != null) {
            imageView.setImageDrawable(drawable);
            if (drawable != null) {
                this.f3006n.setVisibility(0);
            } else {
                this.f3006n.setVisibility(8);
            }
        }
    }

    @Override // android.view.View
    public void setNextFocusDownId(int i10) {
        this.f3005m.setNextFocusDownId(i10);
        this.f3004l.setNextFocusDownId(i10);
    }

    public void setPermissionListener(b bVar) {
        this.J = bVar;
    }

    public void setSearchAffordanceColors(f1 f1Var) {
        SpeechOrbView speechOrbView = this.f3005m;
        if (speechOrbView != null) {
            speechOrbView.setNotListeningOrbColors(f1Var);
        }
    }

    public void setSearchAffordanceColorsInListening(f1 f1Var) {
        SpeechOrbView speechOrbView = this.f3005m;
        if (speechOrbView != null) {
            speechOrbView.setListeningOrbColors(f1Var);
        }
    }

    public void setSearchBarListener(a aVar) {
        this.f3003i = aVar;
    }

    public void setSearchQuery(String str) {
        c();
        this.f3004l.setText(str);
        setSearchQueryInternal(str);
    }

    public void setSearchQueryInternal(String str) {
        if (TextUtils.equals(this.f3007o, str)) {
            return;
        }
        this.f3007o = str;
        a aVar = this.f3003i;
        if (aVar != null) {
            aVar.c();
        }
    }

    @Deprecated
    public void setSpeechRecognitionCallback(i1 i1Var) {
        this.D = i1Var;
        if (i1Var != null && this.C != null) {
            throw new IllegalStateException("Can't have speech recognizer and request");
        }
    }

    public void setSpeechRecognizer(SpeechRecognizer speechRecognizer) {
        c();
        SpeechRecognizer speechRecognizer2 = this.C;
        if (speechRecognizer2 != null) {
            speechRecognizer2.setRecognitionListener(null);
            if (this.E) {
                this.C.cancel();
                this.E = false;
            }
        }
        this.C = speechRecognizer;
        if (this.D != null && speechRecognizer != null) {
            throw new IllegalStateException("Can't have speech recognizer and request");
        }
    }

    public void setTitle(String str) {
        this.f3009q = str;
        d();
    }
}
