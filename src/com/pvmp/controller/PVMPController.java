/**
* @file PVMPController.java
* @brief
*/
package com.pvmp.controller;

import java.util.ArrayList;

import android.content.Context;
import android.database.sqlite.SQLiteException;
import android.graphics.Typeface;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.github.mikephil.charting.utils.Legend;
import com.github.mikephil.charting.utils.Legend.LegendPosition;
import com.pvmp.models.ModelSubjectInterface;
import com.pvmp.models.Proposition;
import com.pvmp.models.User;
import com.pvmp.models.Vote;
import com.pvmp.models.Voting;
import com.pvmp.view.ViewObserverInterface;
import com.pvmp.util.Util;
import com.pvmp.models.PVMPmodel;

/**
* @class PVMPController
* @brief
*/
public class PVMPController implements ControllerInterface
{
	ViewObserverInterface view;
	PVMPmodel model;

	public PVMPController()
	{
	}

	/**
	* @param _context
	* @brief 
	*/
	public PVMPController(Context _context)
	{
		this.model = new PVMPmodel(_context);
	}

	/**
	* @param _view
	* @brief 
	*/
	public void setView(ViewObserverInterface _view)
	{
		this.view = _view;
	}

	/**
	* @param _model
	* @brief 
	*/
	public void setModel(ModelSubjectInterface _model)
	{
		//this.model = _model;
	}

	@Override
	public void openApplication()
	{

		User user = this.model.getUser("default_user","S");
		Util.debug("PVMPController: start openApplication.");

		//Verify if has user default
		if (user != null)
		{
			Util.debug("PVMPController: go to HOME");
			this.view.displayFragment(ViewObserverInterface.CATEGORY);
			return;
		}
		
		Util.debug("PVMPController: go to LOGIN");
		this.view.enableDrawer(false);
		this.view.enableScreenInteraction(false);
		this.view.displayFragment(ViewObserverInterface.LOGIN);
		
		return;
	}

	@Override
	public void displayListProposition()
	{
		return;	
	}

	//public void displayFragment();
	@Override
	public void registerUser(User _user)
	{
		Util.debug("PVMPController: Prepare for register new user.");
		try {
			this.model.saveUser(_user);
		}catch (SQLiteException sqlE) {
			sqlE.printStackTrace();
		}

		return;
	}
	public void callDisplayFragment (int fragmentIndex)
	{
		Util.debug("PVMPController: User saved on database, change view now");
		this.view.displayFragment(fragmentIndex);
		Util.debug("PVMPController: finish register");
	}
	
	public void editUser(User _user) {
		this.model.editUser(_user);
	}
	
	public void deleteUser(User _user) {
		this.model.removeUser(_user);
	}
	
	@Override
	public User openSession()
	{
		Util.debug("PVMPController: openSession");
		
		User user = this.model.getUser("default_user","S");
		
		if(user == null)
		{
			Util.debug("PVMPController: getDefaultUser Problem");
		}

		return user;
	}
	/**
	 * @param _userName
	 * @param _password
	 * @brief
	 * */
	public User verifyMatchingUserPassword (String _userName, String _password) 
	{
		if (_userName == null || _password == null)
		{
			throw new NullPointerException ("Null pointer at PVMPController.verifyMatchingUserPassword().");
		}

		User user = this.model.verifyMatchingUserPassword(_userName, _password);
		
		return user;
	}

	@Override
	public ArrayList<Proposition> getPropositions(String _columnName, String _value) 
	{
		//_value might be null
		if (_columnName == null)
		{
			throw new NullPointerException ("Null pointer at PVMPController.getPropositions().");
		}
		
		ArrayList<Proposition> propositions = new ArrayList<Proposition>();
		
		propositions = this.model.getPropositions(_columnName, _value);
		
		return propositions;
	}
	
	/**
	 * @param _votes
	 * @brief Calculates the percentage of "Yes votes" and "No votes" a voting has and 
	 * 		  returns them into an ArrayList.
	 * */
	public ArrayList<Float> calculateVotesResultPercentage (ArrayList<Vote> _votes)
	{
		if (_votes == null)
		{
			throw new NullPointerException ("Null pointer at PVMPController.calculateVotes().");
		}
		
		ArrayList<Float> results = new ArrayList<Float>();
		
		Float yesVotes = 0f, noVotes = 0f, totalVotes = 0f;
		Float yesPercentage, noPercentage;
		
		for (int i = 0; i < _votes.size(); i++)
		{
			String currentResult = new String();
			currentResult = _votes.get(i).getResult().trim();
			
			if (currentResult.equals("Sim"))
			{
				yesVotes++;
			}
			else if (currentResult.equals("Não"))
			{
				noVotes++;
			}
		}
		totalVotes = yesVotes + noVotes;
		
		yesPercentage = (yesVotes*100)/totalVotes;
		noPercentage = (noVotes*100)/totalVotes;
		
		results.add(yesPercentage); results.add(noPercentage);
		
		return results;
	}
	
	public PieChart prepareGraphicData (Proposition _proposition, PieChart _chart)
	{
		Voting voting = new Voting();
		ArrayList<Vote> votes = new ArrayList<Vote>();
		ArrayList<Float> votesPercentage = new ArrayList<Float>();
		ArrayList<String> slicesTitles = new ArrayList<String>();
		ArrayList<Entry> slicesValues = new ArrayList<Entry>();
		PieDataSet pieDataSet;
		
		voting = this.model.getPropositionVoting(_proposition);
		votes = this.model.getVotingVotes(voting);
		votesPercentage = this.calculateVotesResultPercentage(votes);
		
		slicesTitles.add("Sim");
		slicesTitles.add("Não");
		
		slicesValues.add(new Entry(votesPercentage.get(0), 0));
		slicesValues.add(new Entry(votesPercentage.get(1), 1));
		
		pieDataSet = new PieDataSet(slicesValues, "");
		pieDataSet.setColors(ColorTemplate.COLORFUL_COLORS);
		pieDataSet.setSliceSpace(2f);
		_chart.setData(new PieData(slicesTitles, pieDataSet));
		
		return _chart;
	}
	/**
	 * @brief Create a graphic... (Incomplete)
	 * */
	public PieChart createGraphic (Proposition _proposition, PieChart _chart, String _centerText)
	{
		_chart = prepareGraphicData(_proposition, _chart);
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
	    _chart.animateXY(800, 800);
        
	    Legend legend = _chart.getLegend();
	    legend.setPosition(LegendPosition.RIGHT_OF_CHART);
	    legend.setFormSize(15f);
	    
	    return _chart;
	}
}
