// SubtitleSettingsFragment.kt  — simplified example
class SubtitleSettingsFragment : BottomSheetDialogFragment() {

    private val repo by lazy { SubtitlePrefsRepository(requireContext()) }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, saved: Bundle?): View {
        val view = inflater.inflate(R.layout.fragment_subtitle_settings, container, false)

        val sizeOptions = SubtitleFontSize.values()
        // bind RadioGroup / RecyclerView with size options
        // on selection: repo.save(currentPrefs.copy(fontSize = selected.sp))

        return view
    }
}