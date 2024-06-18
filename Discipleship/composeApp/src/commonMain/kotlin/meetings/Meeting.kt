package meetings

import user.Disciple

data class Meeting(
    val date: String?,
    val time: String?,
    val disciple: Disciple,
    val note: String?
) {
}