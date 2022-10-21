package com.example.compositiongame.presentation

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.example.compositiongame.R

@BindingAdapter("requiredRightAnswers")
fun requiredRightAnswers(tv: TextView, count: Int) {
    tv.text = String.format(
        tv.context.getString(R.string.required_right_answers),
        count
    )
}

@BindingAdapter("countRightAnswers")
fun countRightAnswers(tv: TextView, count: Int) {
    tv.text = String.format(
        tv.context.getString(R.string.count_of_right_answers),
        count
    )
}

@BindingAdapter("requiredPercentOfRightAnswers")
fun requiredPercentOfRightAnswers(tv: TextView, percent: Int) {
    tv.text = String.format(
        tv.context.getString(R.string.required_right_answers_percent),
        percent
    )
}

@BindingAdapter("percentOfRightAnswers")
fun percentOfRightAnswers(tv: TextView, percent: Int) {
    tv.text = String.format(
        tv.context.getString(R.string.percent_of_right_answers),
        percent
    )
}

@BindingAdapter("setEmoji")
fun setEmoji(imv: ImageView, winner: Boolean) {
    imv.setImageResource(
        if (winner)
            R.drawable.ic_smile
        else
            R.drawable.ic_sad
    )
}




