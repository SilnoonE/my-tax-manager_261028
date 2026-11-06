// Standalone educational example. No production data or storage code.
data class ChoiceSelection(val options: List<String>, val selectedIndex: Int)

fun resolveChoice(choices: List<String>, savedValue: String, draftValue: String? = null): ChoiceSelection {
    val options = (choices + savedValue).distinct()
    val selected = options.indexOf(draftValue ?: savedValue).coerceAtLeast(0)
    return ChoiceSelection(options, selected)
}

fun main() {
    val saved = resolveChoice(listOf("서비스", "임대료", "서비스"), "사용자 지정")
    check(saved.options == listOf("서비스", "임대료", "사용자 지정"))
    check(saved.options[saved.selectedIndex] == "사용자 지정")
    val editing = resolveChoice(listOf("서비스", "임대료"), "서비스", "임대료")
    check(editing.options[editing.selectedIndex] == "임대료")
    val staleDraft = resolveChoice(listOf("서비스"), "서비스", "목록에 없는 초안")
    check(staleDraft.selectedIndex == 0)
    println("Choice selection examples passed.")
}
