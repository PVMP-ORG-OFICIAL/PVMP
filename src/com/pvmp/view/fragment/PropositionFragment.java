package com.pvmp.view.fragment;

import java.util.ArrayList;

import android.content.Context;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.ToggleButton;
import android.widget.ViewFlipper;

import com.github.mikephil.charting.charts.PieChart;
import com.pvmp.main.R;
import com.pvmp.controller.ChartController;
import com.pvmp.controller.FeedbackController;
import com.pvmp.model.Feedback;
import com.pvmp.model.Proposition;
import com.pvmp.view.ChartView;
import com.pvmp.view.PVMPView;

public class PropositionFragment extends FragmentView {
	private Context context;
	private PVMPView view;
	private ArrayList<Proposition> propositions;
	private TextView authorName, categoryName;
	private ViewFlipper viewFlipper;
	private int limit, target, count;
	private String opinion = "";
	private PieChart yesNoVotesChart, yesVotesChart, noVotesChart;
	private ToggleButton button_like, button_dislike, button_clown;
	private ScrollView propositionScrollView;
	private FeedbackController feedbackController;
	private Feedback existingFeedback;

	private GestureDetector gesturedetector = null;
	private static final int SWIPE_MIN_DISTANCE = 100;
	private static final int SWIPE_MAX_OFF_PATH = 100;
	private static final int SWIPE_THRESHOLD_VELOCITY = 80;

	@Override
	public View onCreateView(LayoutInflater _inflater, ViewGroup _container,
			Bundle _savedInstanceState) {
		View rootView = _inflater.inflate(R.layout.proposition_fragment,
				_container, false);

		this.view = (PVMPView) getActivity();
		this.context = this.view.getApplicationContext();
		this.feedbackController = new FeedbackController(this.context);
		this.existingFeedback = new Feedback();
		this.propositions = PVMPView.propositions;
		this.limit = propositions.size();
		
		if (_savedInstanceState == null)
			this.count = 0;
		else
			this.count = _savedInstanceState.getInt("countValue");
		
		this.setGestureDetector(rootView);
		this.buildScreenComponent(rootView);
		this.updateScreenComponent();
		this.viewFlipper = (ViewFlipper) rootView
				.findViewById(R.id.proposition_flipper);

		return rootView;
	}
	
	@Override
	public void onSaveInstanceState (Bundle outState) 
	{
		super.onSaveInstanceState(outState);
		outState.putInt("countValue", this.count);
	}

	@Override
	public void onPause() {
		super.onPause();
		this.takeFeedback();

		PVMPView.gesturedetector = new GestureDetector(
				view.getApplicationContext(),
				new GestureDetector.SimpleOnGestureListener() {
					@Override
					public boolean onDown(MotionEvent e) {
						return true;
					}
				});
	}

	public void setGestureDetector(View _rootView) {
		PVMPView.gesturedetector = new GestureDetector(getActivity(),
				new GestureDetector.SimpleOnGestureListener() {

					@Override
					public boolean onDown(MotionEvent e) {
						return true;
					}

					@Override
					public boolean onFling(MotionEvent e1, MotionEvent e2,
							float velocityX, float velocityY) {
						float dX = e2.getX() - e1.getX();
						float dY = e1.getY() - e2.getY();

						if (Math.abs(dY) < SWIPE_MAX_OFF_PATH
								&& Math.abs(velocityX) >= SWIPE_THRESHOLD_VELOCITY
								&& Math.abs(dX) >= SWIPE_MIN_DISTANCE) {
							if (dX > 0) {
								HandlePrevious();
							} else {
								HandleNext();
							}

						}
						return super.onFling(e1, e2, velocityX, velocityY);
					}
				});

		_rootView.setOnTouchListener(new View.OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				return true;
			}
		});
	}

	@Override
	public void buildScreenComponent(View _view) {
		this.categoryName = (TextView) _view
				.findViewById(R.id.proposition_category);
		this.authorName = (TextView) _view.findViewById(R.id.author_name);
		this.propositionScrollView = (ScrollView) _view
				.findViewById(R.id.proposition_scroll_view);
		this.yesNoVotesChart = (PieChart) _view
				.findViewById(R.id.yes_no_votes_chart);
		this.yesVotesChart = (PieChart) _view
				.findViewById(R.id.yes_votes_chart);
		this.noVotesChart = (PieChart) _view.findViewById(R.id.no_votes_chart);
		this.button_like = (ToggleButton) _view
				.findViewById(R.id.toggleButton_like);
		this.button_dislike = (ToggleButton) _view
				.findViewById(R.id.toggleButton_dislike);
		this.button_clown = (ToggleButton) _view
				.findViewById(R.id.toggleButton_clown);

		this.button_like.setOnClickListener(new HandleLike());
		this.button_dislike.setOnClickListener(new HandleDislike());
		this.button_clown.setOnClickListener(new HandleClown());
	}

	public void updateScreenComponent() {
		String textAuthor = "Autor: " + propositions.get(this.count).getAuthor();
		String textMenu = "Ementa: " + propositions.get(this.count).getMenu();

		this.propositionScrollView.fullScroll(ScrollView.FOCUS_UP);

		this.yesNoVotesChart = ChartView.createChart(
				propositions.get(this.count), this.yesNoVotesChart,
				"Resultado", ChartController.ALL_VOTES, this.context);
		this.yesVotesChart = ChartView.createChart(
				propositions.get(this.count), this.yesVotesChart, "Sim",
				ChartController.YES_VOTES, this.context);
		this.noVotesChart = ChartView.createChart(propositions.get(this.count),
				this.noVotesChart, "Não", ChartController.NO_VOTES,
				this.context);

		//this.textPropositionCount.setText("#" + (this.count + 1));
		this.authorName.setText(textAuthor);
		this.categoryName.setText(textMenu);
		if(PVMPView.user != null)
		{
			this.existingFeedback = this.feedbackController.selectFeedback(
					PVMPView.user, propositions.get(this.count).getId());
			this.updateFeedback(existingFeedback);
		}
		else
		{
			this.button_clown.setChecked(false);
			this.button_dislike.setChecked(false);
			this.button_like.setChecked(false);
		}
	}

	public void takeFeedback() {
		this.target = propositions.get(this.count).getId();

		if (button_like.isChecked()) {
			this.opinion = "l";
		} else if (button_dislike.isChecked()) {
			this.opinion = "d";
		} else if (button_clown.isChecked()) {
			this.opinion = "c";
		} else {
			if (this.existingFeedback != null && PVMPView.user != null) {
				this.feedbackController.deleteFeedback(PVMPView.user,
						this.target);
			}
			return;
		}

		if (!opinion.equals("") && PVMPView.user != null) {
			if (this.existingFeedback != null) {
				this.feedbackController.editFeedback(this.opinion,
						PVMPView.user, this.target);
			} else {
				this.feedbackController.saveFeedback(this.opinion,
						PVMPView.user, this.target);
			}
		}
	}

	public void setOpinionButtons(boolean like, boolean dislike, boolean clown) {
		this.button_like.setChecked(like);
		this.button_dislike.setChecked(dislike);
		this.button_clown.setChecked(clown);
	}

	public void updateFeedback(Feedback _feedback) {
		if (_feedback == null) {
			this.setOpinionButtons(false, false, false);
			return;
		} else if (_feedback.getOpinion().equals("l")) {
			this.setOpinionButtons(true, false, false);
		} else if (_feedback.getOpinion().equals("d")) {
			this.setOpinionButtons(false, true, false);
		} else if (_feedback.getOpinion().equals("c")) {
			this.setOpinionButtons(false, false, true);
		}
	}

	public void HandleNext() {
		takeFeedback();
		if (this.count < limit - 1 ) {
			this.count++;
		} else {
			this.count = 0;
		}
		Integer idVoting = this.propositions.get(this.count).getVoting().getIdVoting();
		if (idVoting.equals(65) || idVoting.equals(44) || idVoting.equals(12) || idVoting.equals(62)
				|| idVoting.equals(18) || idVoting.equals(48) || idVoting.equals(50) || idVoting.equals(63)) {
			this.HandleNext();
			return;
		}
		viewFlipper.setInAnimation(this.context,
				R.anim.in_from_right);
		viewFlipper.setOutAnimation(this.context,
				R.anim.out_to_left);

		updateScreenComponent();
		viewFlipper.showNext();
	}

	public void HandlePrevious() {

		takeFeedback();
		if (this.count > 0) {
			this.count--;
		} else {
			this.count = limit - 1;
		}
		Integer idVoting = this.propositions.get(this.count).getVoting().getIdVoting();
		if (idVoting.equals(65) || idVoting.equals(44) || idVoting.equals(12) || idVoting.equals(62)
				|| idVoting.equals(18) || idVoting.equals(48) || idVoting.equals(50) || idVoting.equals(63)) {
			this.HandlePrevious();
			return;
		}
		viewFlipper.setInAnimation(this.context,
				R.anim.in_from_left);
		viewFlipper.setOutAnimation(this.context,
				R.anim.out_to_right);

		updateScreenComponent();
		viewFlipper.showPrevious();

	}

	private class HandleLike implements View.OnClickListener {

		@Override
		public void onClick(View v) {
			button_dislike.setChecked(false);
			button_clown.setChecked(false);
		}
	}

	private class HandleDislike implements View.OnClickListener {

		@Override
		public void onClick(View v) {
			button_like.setChecked(false);
			button_clown.setChecked(false);
		}
	}

	private class HandleClown implements View.OnClickListener {

		@Override
		public void onClick(View v) {
			button_like.setChecked(false);
			button_dislike.setChecked(false);
		}
	}
}
