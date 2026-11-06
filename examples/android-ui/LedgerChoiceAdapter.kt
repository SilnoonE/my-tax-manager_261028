package example.mytaxmanager.ui

import android.content.Context
import android.graphics.Color
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.appcompat.view.ContextThemeWrapper

// Only selection surfaces use this light theme; the surrounding screen keeps its theme.
class LedgerChoiceContext(base: Context) : ContextThemeWrapper(base, R.style.Theme_Ledger_WhiteChoices)
// Activity-specific extension omitted from this public UI excerpt.

class LedgerChoiceAdapter(context: Context, values: List<String>, private val selectedTextColor: Int) :
    ArrayAdapter<String>(context, R.layout.ledger_choice_selected, values) {
    init { setDropDownViewResource(R.layout.ledger_choice_option) }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View =
        (super.getView(position, convertView, parent) as TextView).apply {
            setTextColor(selectedTextColor)
            setBackgroundColor(Color.TRANSPARENT)
        }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View =
        (super.getDropDownView(position, convertView, parent) as TextView).apply {
            setTextColor(Color.rgb(18, 35, 61))
            setBackgroundResource(R.drawable.ledger_choice_row)
        }
}
