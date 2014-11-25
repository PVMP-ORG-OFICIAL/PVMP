package com.pvmp.view;

import android.content.Context;
import android.graphics.Typeface;

import com.github.mikephil.charting.charts.PieChart;
import com.pvmp.controller.ChartController;
import com.pvmp.model.Proposition;

public class ChartView 
{
	
	public ChartView()
	{

	}
	
	public static PieChart createChart (Proposition _proposition, PieChart _chart,
			   String _centerText, String _tag, Context _context)
	{
		_chart = new ChartController(_context).prepareGraphicData(_proposition, _chart, _tag);
		_chart.setDescription("");

		Typeface tf = Typeface.defaultFromStyle(Typeface.BOLD_ITALIC);
		Typeface tfCenter = Typeface.SANS_SERIF;

		_chart.setValueTypeface(tf);
		_chart.setUsePercentValues(true);
		_chart.setValueTextSize(15f);
		_chart.setCenterText(_centerText);
		_chart.setCenterTextTypeface(tfCenter);
		_chart.setCenterTextSize(22f);
		
		_chart.setHoleRadius(45f); 
		_chart.setTransparentCircleRadius(50f);
		_chart.setRotationEnabled(false);
		
		_chart.setScrollContainer(true);
		
		if (_tag == ChartController.ALL_VOTES)
			_chart.animateXY(700, 700);
		else
			_chart.animateXY(1, 1);

		return _chart;
	}
}
