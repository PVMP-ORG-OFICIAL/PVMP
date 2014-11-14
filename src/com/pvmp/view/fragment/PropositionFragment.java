package com.pvmp.view.fragment;

import java.util.ArrayList;

import com.github.mikephil.charting.charts.PieChart;
import com.pvmp.R;
import com.pvmp.controller.PVMPController;
import com.pvmp.models.Proposition;
import com.pvmp.view.PVMPView;

import android.app.ActionBar.Tab;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.ViewFlipper;

public class PropositionFragment extends FragmentView 
{
	private PVMPView view;
	private PVMPController controller;
	private ArrayList<Proposition> propositions;
	private TextView textPropositionCount;
	private TextView categoryName;
	private ViewFlipper viewFlipper;
	private int limit;
	private int count;
	private Button button_next;
	private Button button_previous;
	private PieChart yesNoVotesChart;
	private PieChart yesVotesChart;
	private PieChart noVotesChart;
	private ScrollView propositionScrollView;
	private TextView feedback_bar;
	//private float firstX;
	//private float currentX;

	@Override
	public View onCreateView(LayoutInflater _inflater, ViewGroup _container, Bundle _savedInstanceState) 
	{
		View rootView = _inflater.inflate(R.layout.proposition_fragment, _container, false);
 
		this.view = (PVMPView) getActivity();
		this.controller = new PVMPController(this.view.getApplicationContext());
		this.propositions = PVMPView.propositions;
		this.limit = propositions.size();
		this.count = 0;
		
		this.buildScreenComponent(rootView);
		this.updateScreenComponent();
		this.viewFlipper = (ViewFlipper) rootView.findViewById(R.id.proposition_flipper);
		
		return rootView;
	}

	@Override
	public void buildScreenComponent(View _view) 
	{
		this.categoryName = (TextView) _view.findViewById(R.id.proposition_category);
		this.textPropositionCount = (TextView) _view.findViewById(R.id.proposition_count2);
		this.button_next = (Button) _view.findViewById(R.id.button_next);
		this.button_previous = (Button) _view.findViewById(R.id.button_previous);
		this.propositionScrollView = (ScrollView) _view.findViewById(R.id.proposition_scroll_view);
		this.yesNoVotesChart = (PieChart) _view.findViewById(R.id.yes_no_votes_chart);
		this.yesVotesChart = (PieChart) _view.findViewById(R.id.yes_votes_chart);
		this.noVotesChart = (PieChart) _view.findViewById(R.id.no_votes_chart);
		
		this.button_next.setOnClickListener(new HandleNext());
		this.button_previous.setOnClickListener(new HandlePrevious());
	}

	public void updateScreenComponent()
	{
		//Testing if the propositions' IDs are correct based on the category clicked.
		String text = (propositions.get(count).getMenu());
		
		this.propositionScrollView.fullScroll(ScrollView.FOCUS_UP);
		this.yesNoVotesChart = this.controller.createGraphic(propositions.get(count), this.yesNoVotesChart, "Resultado", PVMPController.ALL_VOTES);
		this.yesVotesChart = this.controller.createGraphic(propositions.get(count), this.yesVotesChart, "Sim", PVMPController.YES_VOTES);
		this.noVotesChart = this.controller.createGraphic(propositions.get(count), this.noVotesChart, "Não", PVMPController.NO_VOTES);
		this.textPropositionCount.setText("#"+(count+1));
		this.categoryName.setText(text);
	}
	
	private class HandleNext implements View.OnClickListener
	{
		@Override
		public void onClick(View v) 
		{
			if(count < limit - 1)
			{
				count++;
			}
			else 
			{
				count = 0;
			}
			viewFlipper.setInAnimation(view.getApplicationContext(), R.anim.in_from_right);
			viewFlipper.setOutAnimation(view.getApplicationContext(), R.anim.out_to_left);
		
			updateScreenComponent();
			viewFlipper.showNext();
		}
		
	}
	
	private class HandlePrevious implements View.OnClickListener
	{

		@Override
		public void onClick(View v) 
		{
			if(count > 0)
			{
				count--;
			}
			else 
			{
				count = limit - 1;
			}
			viewFlipper.setInAnimation(view.getApplicationContext(), R.anim.in_from_left);
			viewFlipper.setOutAnimation(view.getApplicationContext(), R.anim.out_to_right);
		
			updateScreenComponent();
			viewFlipper.showPrevious();
		}
		
	}
}
