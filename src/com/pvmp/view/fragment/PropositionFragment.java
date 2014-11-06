package com.pvmp.view.fragment;

import java.util.ArrayList;

import com.pvmp.R;
import com.pvmp.models.Proposition;
import com.pvmp.view.PVMPView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.ViewFlipper;

public class PropositionFragment extends FragmentView 
{
	private PVMPView view;
	private ArrayList<Proposition> propositions;
	private TextView textPropositionCount;
	private TextView categoryName;
	private ViewFlipper viewFlipper;
	private Button nextButton;
	private Button previousButton;
	private int limit;
	private int count;

	@Override
	public View onCreateView(LayoutInflater _inflater, ViewGroup _container, Bundle _savedInstanceState) 
	{
		View rootView = _inflater.inflate(R.layout.proposition_fragment, _container, false);
 
		this.view = (PVMPView) getActivity();
		this.propositions = PVMPView.propositions;
		this.limit = propositions.size();
		count = 0;
		
		this.buildScreenComponent(rootView);
		this.updateScreenComponent();
		
		return rootView;
	}

	@Override
	public void buildScreenComponent(View _view) 
	{
		this.categoryName = (TextView) _view.findViewById(R.id.proposition_category);
		this.textPropositionCount = (TextView) _view.findViewById(R.id.proposition_count);
		this.nextButton = (Button) _view.findViewById(R.id.button_next);
		this.previousButton = (Button) _view.findViewById(R.id.button_previous);
		this.viewFlipper = (ViewFlipper) _view.findViewById(R.id.proposition_flipper);
		
		this.nextButton.setOnClickListener(new HandleNext());
		this.previousButton.setOnClickListener(new HandlePrevious());
	}

	public void updateScreenComponent()
	{
		//Testing if the propositions' IDs are correct based on the category clicked.
		String text = Integer.toString(propositions.get(count).getId());
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
