package com.example.compositiongame.presentation

import android.content.res.ColorStateList
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.example.compositiongame.R


@BindingAdapter("setPercentOfRightAnswers")
fun setPercentOfRightAnswers(prb: ProgressBar, progress: Int) {
    prb.progress = progress
}

@BindingAdapter("setMinPercentOfRightAnswers")
fun setMinPercentOfRightAnswers(pb: ProgressBar, progress: Int) {
    pb.secondaryProgress = progress
}

@BindingAdapter("enoughRightAnswers")
fun enoughRightAnswers(tv: TextView, enough: Boolean) {
    tv.setTextColor(
        tv.context.getColor(
            if (enough)
                R.color.green
            else
                R.color.red
        )
    )
}

@BindingAdapter("enoughPercentRightAnswers")
fun enoughPercentRightAnswers(pb: ProgressBar, enough: Boolean) {
    pb.progressTintList =
        ColorStateList.valueOf(
            pb.context.getColor(
                if (enough)
                    R.color.green
                else
                    R.color.red
            )
        )
}

@BindingAdapter("requiredRightAnswers")
fun requiredRightAnswers(tv: TextView, count: Int) {
    tv.text = String.format(
        tv.context.getString(R.string.required_right_answers), count
    )
}

@BindingAdapter("countRightAnswers")
fun countRightAnswers(tv: TextView, count: Int) {
    tv.text = String.format(
        tv.context.getString(R.string.count_of_right_answers), count
    )
}

@BindingAdapter("requiredPercentOfRightAnswers")
fun requiredPercentOfRightAnswers(tv: TextView, percent: Int) {
    tv.text = String.format(
        tv.context.getString(R.string.required_right_answers_percent), percent
    )
}

@BindingAdapter("percentOfRightAnswers")
fun percentOfRightAnswers(tv: TextView, percent: Int) {
    tv.text = String.format(
        tv.context.getString(R.string.percent_of_right_answers), percent
    )
}

@BindingAdapter("setEmoji")
fun setEmoji(imv: ImageView, winner: Boolean) {
    imv.setImageResource(
        if (winner) R.drawable.ic_smile
        else R.drawable.ic_sad
    )
}

@BindingAdapter("numberAsText")
fun numberAsText(tv: TextView, number: Int) {
    tv.text = number.toString()
}

@BindingAdapter("onOptionClickListener")
fun bindOnOptionClickListener(tv: TextView, clickListener: OptionClickListener) {
    tv.setOnClickListener {
        clickListener.onClick(tv.text.toString().toInt())
    }
}
interface OptionClickListener{
    fun onClick(value:Int)
}




