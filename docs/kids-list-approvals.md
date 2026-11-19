# Unrated titles on Android kids profiles

From the kids profile, open **Settings > Catalogs**, edit a personal Trakt or
MDBList row, and choose **Approve an unrated title**. Enter the parent PIN and
select one title. The title must still belong to that list and TMDB must
successfully report no certification. A failed lookup, an unreadable
certification, or a known rating cannot be approved through this flow.

Approval applies only to that title on that profile. It doesn't extend to other
titles in the row, including titles the list owner adds later. Details and
playback recheck the rating for an exception. A successful refresh removes
approvals for missing titles, including an empty list. Removing or replacing
the row revokes its approvals immediately. A failed list refresh doesn't
pretend the list is empty.

Approvals stay on this Android device. Syncing a catalog or opening it on the
web does not grant an exception there.
