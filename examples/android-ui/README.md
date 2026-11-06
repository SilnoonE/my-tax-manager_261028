# 선택창 UI 코드 발췌

실제 내세금관리 앱에서 사용하는 선택창 UI 부분입니다. 공개 예시 패키지로 바꾸고 Activity에 종속된 확장 함수는 제외했습니다. 저장·결제·계정·서명·암호화 코드는 포함하지 않습니다. 독립 실행 프로젝트가 아니며 AndroidX AppCompat와 Material Components가 있는 Android 앱에 통합하는 발췌입니다.

## 파일별 역할

| 파일 | 역할 |
|---|---|
| `LedgerChoiceAdapter.kt` | 선택 전용 밝은 Context, 접힌 값과 펼친 목록 행 생성 |
| `res/layout/ledger_choice_selected.xml` | 접힌 선택 값, 최소 48dp 높이와 글자 크기 |
| `res/layout/ledger_choice_option.xml` | 흰 목록 행, 진한 글자, 긴 항목 줄바꿈 |
| `res/drawable/ledger_choice_popup.xml` | 팝업 바깥 면·테두리·둥근 모서리 |
| `res/drawable/ledger_choice_row.xml` | 흰 행 배경과 누름 ripple |
| `res/values/choice_styles.xml` | 목록·대화상자·날짜창의 선택 전용 테마 |

## 통합 예시

리소스를 앱의 `res` 아래에 넣고 Kotlin 파일의 패키지와 `R` 참조를 통합할 앱에 맞춰야 합니다. 현재처럼 하위 패키지를 사용하면 앱 namespace의 `R`을 import하세요.

```kotlin
// Activity 내부, 실제 앱에 맞는 본문 글자색을 전달합니다.
val menuContext = LedgerChoiceContext(this)
val spinner = Spinner(menuContext, Spinner.MODE_DROPDOWN).apply {
    adapter = LedgerChoiceAdapter(menuContext, choices, bodyTextColor)
    setPopupBackgroundResource(R.drawable.ledger_choice_popup)
    dropDownWidth = ViewGroup.LayoutParams.MATCH_PARENT
}
```

`getView()`는 접힌 값, `getDropDownView()`는 펼친 행을 생성합니다. 본문 다크 테마의 글자색과 흰 목록의 글자색을 분리하는 이유입니다. `convertView` 재사용 이후에도 색을 명시해 이전 행의 스타일이 남지 않게 합니다. `setDropDownViewResource`는 펼친 행의 XML을 지정합니다.

화면 전체를 이 밝은 테마로 교체하지 않고 선택 위젯에만 전달합니다. 날짜창은 버튼이 만들어진 뒤 색을 지정해야 하므로 [날짜 버튼 개선 설명](../../docs/ui-case-study.md)을 함께 참고하세요.
