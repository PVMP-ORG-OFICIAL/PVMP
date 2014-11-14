package com.pvmp.view.fragment;

import java.util.ArrayList;

import com.github.mikephil.charting.charts.PieChart;
import com.pvmp.R;
import com.pvmp.controller.PVMPController;
import com.pvmp.models.Proposition;
import com.pvmp.models.PropositionWrapper;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.ToggleButton;
import android.widget.ViewFlipper;

public class PropositionActivity extends Activity{
	
	private PVMPController controller;
	private ArrayList<Proposition> propositions = new ArrayList<Proposition>();
	private TextView textPropositionCount;
	private TextView categoryName;
	private ViewFlipper viewFlipper;
	private PropositionWrapper propositionWrapper;
	private int limit;
	private int count = 0;
	private float firstX;
	private float lastX;
	private Button button_next;
	private Button button_previous;
	private PieChart yesNoVotesChart;
	private PieChart yesVotesChart;
	private PieChart noVotesChart;
	private ScrollView propositionScrollView;
	private ToggleButton tb1, tb2, tb3;
	
	public PropositionActivity()
	{}
	
	@Override
	public void onCreate(Bundle _savedInstanceState) 
	{
		super.onCreate(_savedInstanceState);
		
		if(_savedInstanceState != null)
		{
			count = _savedInstanceState.getInt("count");
		}
		//this.view = (PVMPView) 
		setContentView(R.layout.proposition_fragment);
		this.viewFlipper = (ViewFlipper) findViewById(R.id.proposition_flipper);
		
		this.propositionWrapper = (PropositionWrapper) getIntent().getSerializableExtra("propositions");
		this.propositions = propositionWrapper.getPropositions();
		
		this.limit = propositions.size();
		//this.proposition = (Proposition) getIntent().getSerializableExtra("proposition");
		this.controller = new PVMPController(getApplicationContext());
		this.buildScreenComponent();
		this.updateScreenComponent();
		
		
		return;
	}
	
	public void buildScreenComponent() 
	{
		this.tb1 = (ToggleButton) findViewById(R.id.toggleButton_like);
		this.tb2 = (ToggleButton) findViewById(R.id.toggleButton_dislike);
		this.tb3 = (ToggleButton) findViewById(R.id.toggleButton_clown);
		this.categoryName = (TextView) findViewById(R.id.proposition_category);
		this.textPropositionCount = (TextView) findViewById(R.id.proposition_count2);
		this.button_next = (Button) findViewById(R.id.button_next);
		this.button_previous = (Button) findViewById(R.id.button_previous);
		this.propositionScrollView = (ScrollView)findViewById(R.id.proposition_scroll_view);
		this.yesNoVotesChart = (PieChart) findViewById(R.id.yes_no_votes_chart);
		this.yesVotesChart = (PieChart) findViewById(R.id.yes_votes_chart);
		this.noVotesChart = (PieChart) findViewById(R.id.no_votes_chart);
		
		this.button_next.setOnClickListener(new HandleNext());
		this.button_previous.setOnClickListener(new HandlePrevious());
		this.tb1.setOnClickListener(new HandleLike());
		this.tb2.setOnClickListener(new HandleDislike());
		this.tb3.setOnClickListener(new HandleClown());
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
	
	@Override
	public void onSaveInstanceState (Bundle _savedInstanceState)
	{
		int currentProposition = count;
		_savedInstanceState.putInt("count", currentProposition);
	}
	
	/*public boolean onTouchEvent(MotionEvent touchEvent)
	{
		switch(touchEvent.getAction())
		{
			case MotionEvent.ACTION_DOWN:
				firstX = touchEvent.getX();
				break;
			case MotionEvent.ACTION_UP:
				lastX = touchEvent.getX();
				
				if(firstX > lastX)
				{
					if(count < limit - 1)
					{
						count++;
					}
					else 
					{
						count = 0;
					}
					viewFlipper.setInAnimation(getApplicationContext(), R.anim.in_from_right);
					viewFlipper.setOutAnimation(getApplicationContext(), R.anim.out_to_left);
				
					updateScreenComponent();
					viewFlipper.showNext();
				}
				else
				{
					if(count > 0)
					{
						count--;
					}
					else 
					{
						count = limit - 1;
					}
					viewFlipper.setInAnimation(getApplicationContext(), R.anim.in_from_left);
					viewFlipper.setOutAnimation(getApplicationContext(), R.anim.out_to_right);
				
					updateScreenComponent();
					viewFlipper.showPrevious();
				}
		}
		return false;
	}*/
	
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
			viewFlipper.setInAnimation(getApplicationContext(), R.anim.in_from_right);
			viewFlipper.setOutAnimation(getApplicationContext(), R.anim.out_to_left);
		
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
			viewFlipper.setInAnimation(getApplicationContext(), R.anim.in_from_left);
			viewFlipper.setOutAnimation(getApplicationContext(), R.anim.out_to_right);
		
			updateScreenComponent();
			viewFlipper.showPrevious();
		}
		
	}
	
	private class HandleLike implements View.OnClickListener
	{
		
		@Override
		public void onClick(View v)
		{
			tb2.setChecked(false);
			tb3.setChecked(false);
		}
	}
	
	private class HandleDislike implements View.OnClickListener
	{
		
		@Override
		public void onClick(View v)
		{
			tb1.setChecked(false);
			tb3.setChecked(false);
		}
	}
	
	private class HandleClown implements View.OnClickListener
	{
		
		@Override
		public void onClick(View v)
		{
			tb1.setChecked(false);
			tb2.setChecked(false);
		}
	}
}
