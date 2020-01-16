package com.test.healthapplication

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.fanhl.flamebarchart.TravelChart
import com.test.healthapplication.adapter.RecyclerAdapter
import com.test.healthapplication.model.Item
import com.test.healthapplication.util.NumberTranslator
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    private val twelveThousand = "12000"
    private val one: String = "1"
    private lateinit var mMainActivityViewModel: MainActivityViewModel
    private lateinit var mAdapter: RecyclerAdapter

    companion object {
        private val TAG = MainActivity::class.java.simpleName
        private const val DEFAULT_CHART_VALUE = 3

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mMainActivityViewModel = ViewModelProviders.of(this).get(MainActivityViewModel::class.java)
        mMainActivityViewModel.init()

        initWeeklyHistoryPart()
        initGoalPart()
        initSuggestionPart()
        initChart()

        mMainActivityViewModel.getSuggestions()?.observe(this,
            Observer<List<String>> {

                mAdapter.notifyDataSetChanged()

            })


    }

    private fun initChart() {
        rebindData()
        chart_health.addOnXAxisChangeListeners(object : TravelChart.DefaultOnXAxisChangeListener() {
            override fun onCurrentXAxisChanged(currentXAxis: Int) {
                Log.d(TAG, "onCurrentXAxisChanged: currentXAxis:$currentXAxis")
            }

            override fun onCurrentXAxisOffsetChanged(
                currentXAxis: Int,
                currentXAxisOffset: Float,
                velocity: Float
            ) {
                Log.d(
                    TAG,
                    "onCurrentXAxisOffsetChanged: currentXAxis:$currentXAxis,currentXAxisOffset:$currentXAxisOffset,velocity:$velocity"
                )
            }

            override fun oScrollEnd(currentXAxis: Int) {

                updateDateSteps(currentXAxis)


            }

            private fun updateDateSteps(currentXAxis: Int) {

                when (currentXAxis) {
                    0 -> {
                        txt_date.text =
                            NumberTranslator.toPersian("14") + " تیر _ " + NumberTranslator.toPersian(
                                "7"
                            ) + " تیر"
                        txt_steps.text = NumberTranslator.toPersian("2000") + " قدم"

                    }
                    1 -> {
                        txt_date.text =
                            NumberTranslator.toPersian("21") + " تیر _ " + NumberTranslator.toPersian(
                                "14"
                            ) + " تیر"

                        txt_steps.text = NumberTranslator.toPersian("1600") + " قدم"

                    }
                    2 -> {
                        txt_date.text =
                            NumberTranslator.toPersian("21") + " تیر _ " + NumberTranslator.toPersian(
                                "28"
                            ) + " تیر"

                        txt_steps.text = NumberTranslator.toPersian("2300") + " قدم"

                    }
                    3 -> {
                        txt_date.text =
                            NumberTranslator.toPersian("28") + " تیر _ " + NumberTranslator.toPersian(
                                "4"
                            ) + " مرداد"

                        txt_steps.text = NumberTranslator.toPersian("1800") + " قدم"

                    }
                    4 -> {
                        txt_date.text =
                            NumberTranslator.toPersian("4") + " مرداد _ " + NumberTranslator.toPersian(
                                "10"
                            ) + " مرداد"
                        txt_steps.text = NumberTranslator.toPersian("800") + " قدم"

                    }
                    5 -> {
                        txt_date.text =
                            NumberTranslator.toPersian("10") + " مرداد _ " + NumberTranslator.toPersian(
                                "16"
                            ) + " مرداد"
                        txt_steps.text = NumberTranslator.toPersian("2600") + " قدم"

                    }
                    6 -> {
                        txt_date.text =
                            NumberTranslator.toPersian("16") + " مرداد _ " + NumberTranslator.toPersian(
                                "22"
                            ) + " مرداد"
                        txt_steps.text = NumberTranslator.toPersian("300") + " قدم"

                    }


                }

            }
        })
        chart_health.setXAxis(DEFAULT_CHART_VALUE)
        chart_health.xLabelTextColor = Color.BLACK
    }

    private fun initSuggestionPart() {

        layout_title_suggestion.findViewById<ImageView>(R.id.img_title)
            .setImageDrawable(resources.getDrawable(R.drawable.ic_lamp))
        layout_title_suggestion.findViewById<TextView>(R.id.txt_title).text =
            resources.getString(R.string.suggestions)
        mAdapter = RecyclerAdapter(mMainActivityViewModel.getSuggestions()?.value!!, listener = {})
        (rc_suggestion as RecyclerView).layoutManager = LinearLayoutManager(this)
        (rc_suggestion as RecyclerView).adapter = mAdapter

    }

    private fun initGoalPart() {
        layout_title_goal.findViewById<ImageView>(R.id.img_title)
            .setImageDrawable(resources.getDrawable(R.drawable.ic_goal))
        layout_title_goal.findViewById<TextView>(R.id.txt_title).text =
            resources.getString(R.string.goal)

        layout_subtitle_goal.findViewById<ImageView>(R.id.img_arrow)
            .setImageDrawable(resources.getDrawable(R.drawable.ic_back))
        layout_subtitle_goal.findViewById<TextView>(R.id.txt_right_side).text =
            resources.getString(R.string.daily_goal)
        layout_subtitle_goal.findViewById<TextView>(R.id.txt_left_side).text =
            NumberTranslator.toPersian(twelveThousand) + " "+resources.getString(R.string.step)

    }

    private fun initWeeklyHistoryPart() {
        layout_title_weekly_history.findViewById<ImageView>(R.id.img_title)
            .setImageDrawable(resources.getDrawable(R.drawable.ic_leg))

        layout_title_weekly_history.findViewById<TextView>(R.id.txt_title).text =
            resources.getString(R.string.weekly_history)

        layout_subtitle_weekly_history.findViewById<ImageView>(R.id.img_arrow).visibility =
            View.INVISIBLE
        layout_subtitle_weekly_history.findViewById<TextView>(R.id.txt_right_side).text =
            resources.getString(R.string.average_move)
        layout_subtitle_weekly_history.findViewById<TextView>(R.id.txt_left_side).text =
            NumberTranslator.toPersian(one) +" "+ resources.getString(R.string.kilometer)

    }


    private fun rebindData() {
        chart_health.data = TravelChart.DefaultData<Item>().apply {
            list.add(Item(15, 0.5f))
            list.add(Item(21, 0.3f))
            list.add(Item(28, 0.7f))
            list.add(Item(4, 0.4f))
            list.add(Item(10, 0.2f))
            list.add(Item(16, 0.9f))
            list.add(Item(22, 0.1f))

        }
    }

}
