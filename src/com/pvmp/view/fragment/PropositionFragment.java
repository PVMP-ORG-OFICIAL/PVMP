package com.pvmp.view.fragment;

import java.util.ArrayList;

import com.github.mikephil.charting.charts.PieChart;
import com.pvmp.R;
import com.pvmp.controller.FeedbackController;
import com.pvmp.controller.PVMPController;
import com.pvmp.models.Feedback;
import com.pvmp.models.Proposition;
import com.pvmp.util.MessageHandling;
import com.pvmp.view.PVMPView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.ToggleButton;
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
	private String opinion = ""; 
	int target;
	private Button button_next;
	private Button button_previous;
	private PieChart yesNoVotesChart, yesVotesChart, noVotesChart;
	private ToggleButton button_like, button_dislike, button_clown;
	private ScrollView propositionScrollView;
	private FeedbackController feedbackController;
	private Feedback existingFeedback;
	//private float firstX;
	//private float currentX;

	@Override
	public View onCreateView(LayoutInflater _inflater, ViewGroup _container, Bundle _savedInstanceState) 
	{
		View rootView = _inflater.inflate(R.layout.proposition_fragment, _container, false);
 
		this.view = (PVMPView) getActivity();
		this.controller = new PVMPController(this.view.getApplicationContext());
		this.feedbackController = new FeedbackController(this.view.getApplicationContext());
		this.existingFeedback = new Feedback();
		this.propositions = PVMPView.propositions;
		this.limit = propositions.size();
		this.count = 0;
		
		this.buildScreenComponent(rootView);
		this.updateScreenComponent();
		this.viewFlipper = (ViewFlipper) rootView.findViewById(R.id.proposition_flipper);
		
		return rootView;
	}
	
	@Override
	public void onPause() {
		super.onPause();
		this.takeFeedback();
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
		this.button_like = (ToggleButton) _view.findViewById(R.id.toggleButton_like);
		this.button_dislike = (ToggleButton) _view.findViewById(R.id.toggleButton_dislike);
		this.button_clown = (ToggleButton) _view.findViewById(R.id.toggleButton_clown);
		
		this.button_next.setOnClickListener(new HandleNext());
		this.button_previous.setOnClickListener(new HandlePrevious());
		this.button_like.setOnClickListener(new HandleLike());
		this.button_dislike.setOnClickListener(new HandleDislike());
		this.button_clown.setOnClickListener(new HandleClown());
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
		this.existingFeedback = this.feedbackController.selectFeedback(PVMPView.user, propositions.get(count).getId());
		this.updateFeedback(existingFeedback);
	}
	
	public void takeFeedback()
	{
		this.target = propositions.get(count).getId();
		
		if(button_like.isChecked())
		{
			this.opinion = "l";
		}
		else if(button_dislike.isChecked())
		{
			this.opinion = "d";
		}
		else if(button_clown.isChecked())
		{
			this.opinion = "c";
		}
		else {
			if(this.existingFeedback != null)
			{
				this.feedbackController.deleteFeedback(PVMPView.user, this.target);
			}
			return;
		}
		
		if(!opinion.equals(""))
		{
			if(this.existingFeedback != null)
			{
				this.feedbackController.editFeedback(this.opinion, PVMPView.user, this.target);
			}
			else
			{
				this.feedbackController.saveFeedback(this.opinion, PVMPView.user, this.target);
			}
		}
	}
	
	public void setOpinionButtons(boolean like, boolean dislike, boolean clown)
	{
		this.button_like.setChecked(like);
		this.button_dislike.setChecked(dislike);
		this.button_clown.setChecked(clown);
	}
	
	public void updateFeedback(Feedback _feedback)
	{
		if(_feedback == null)
		{
			this.setOpinionButtons(false, false, false);
			MessageHandling.showToast("Setou falso", view.getApplicationContext());
			return;
		}
		else if(_feedback.getOpinion().equals("l"))
		{
			this.setOpinionButtons(true, false, false);
		}
		else if(_feedback.getOpinion().equals("d"))
		{
			this.setOpinionButtons(false, true, false);
		}
		else if (_feedback.getOpinion().equals("c"))
		{
			this.setOpinionButtons(false, false, true);
		}
	}
	
	private class HandleNext implements View.OnClickListener
	{
		@Override
		public void onClick(View v) 
		{
			takeFeedback();
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
			takeFeedback();
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
	
	private class HandleLike implements View.OnClickListener
	{
		
		@Override
		public void onClick(View v)
		{
			button_dislike.setChecked(false);
			button_clown.setChecked(false);
		}
	}
	
	private class HandleDislike implements View.OnClickListener
	{
		
		@Override
		public void onClick(View v)
		{
			button_like.setChecked(false);
			button_clown.setChecked(false);
		}
	}
	
	private class HandleClown implements View.OnClickListener
	{
		
		@Override
		public void onClick(View v)
		{
			button_like.setChecked(false);
			button_dislike.setChecked(false);
		}
	}
}
