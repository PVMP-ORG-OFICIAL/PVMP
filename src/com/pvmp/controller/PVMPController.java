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
import com.pvmp.models.Deputy;
import com.pvmp.models.ModelSubjectInterface;
import com.pvmp.models.Party;
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
	public static final String YES_VOTES = "Sim";
	public static final String NO_VOTES = "Não";
	private ViewObserverInterface view;
	private PVMPmodel model;

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
	 * 
	 * */
	public ArrayList<Vote> selectVotesByType (ArrayList<Vote> _votes, String _vote)
	{
		ArrayList<Vote> votes = new ArrayList<Vote>();
		for (Vote vote : _votes) 
		{
			String voteResult = vote.getResult().trim();
			
			if(voteResult.equals(_vote))
			{
				votes.add(vote);
			}
		}
		return votes;
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
		
		float yesVotes = 0f, noVotes = 0f, totalVotes = 0f;
		float yesPercentage, noPercentage;
		
		yesVotes = (float) this.selectVotesByType(_votes, YES_VOTES).size();
		noVotes = (float) this.selectVotesByType(_votes, NO_VOTES).size();

		totalVotes = yesVotes + noVotes;
		
		yesPercentage = (yesVotes*100f)/totalVotes;
		noPercentage = (noVotes*100f)/totalVotes;
		
		results.add(yesPercentage); results.add(noPercentage);
		
		return results;
	}
	
	/**
	 * 
	 * */
	//This little kid here need to (and will) be refatored. (Not done yet)
	public ArrayList<float[]> calculatePartiesResultPercentage (ArrayList<Vote> _votes, String _vote)
	{
		float votesNumber = (float) this.selectVotesByType(_votes, _vote).size();
		ArrayList<Vote> votes = this.selectVotesByType(_votes, _vote);
		ArrayList<Deputy> deputies = new ArrayList<Deputy>();
		ArrayList<Party> parties = new ArrayList<Party>();
		ArrayList<int[]> results = new ArrayList<int[]>();
		int aux = 0;
		
		
		for(Vote vote : votes)
		{
			Deputy deputy = new Deputy();
			deputy = this.model.getDeputyVoteOnSession(vote);
			if (deputy != null)
			deputies.add(deputy);
		}
		
		for(Deputy deputy : deputies)
		{
			Party party = new Party();
			party = this.model.getDeputyParty(deputy);
			parties.add(party);
		}
		
		for(Party party : parties)
		{
			if (results.size() == 0)
			{
				int [] result = {party.getNumber(), 1};
				results.add(result);
			}
			else
			{
				aux = 0;
				for (int i = 0; i < results.size(); i++)
				{
					int[] result = new int[]{results.get(i)[0], results.get(i)[1]};
					if(result[0] == party.getNumber())
					{
						result[1]++;
						results.set(i, result);
						aux++;
						break;
					}
				}
				if(aux == 0)
				{
					int [] result = {party.getNumber(), 1};
					results.add(result);
				}
			}
		}
		
		for (int i = 0; i < results.size(); i++) {
			Util.debug("Partido: " + this.model.getPartyAcronym(results.get(i)[0])
						+ ". Qtd: " + results.get(i)[1]);
		}
		
		return null;
	}
	
	/**
	 * */
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
		//calculatePartiesResultPercentage(votes, YES_VOTES);
		votesPercentage = this.calculateVotesResultPercentage(votes);
		
		slicesTitles.add("Sim");
		slicesTitles.add("Não");
		
		slicesValues.add(new Entry(votesPercentage.get(0), 0));
		slicesValues.add(new Entry(votesPercentage.get(1), 1));
		
		pieDataSet = new PieDataSet(slicesValues, "");
		pieDataSet.setColors(ColorTemplate.COLORFUL_COLORS);
		pieDataSet.setSliceSpace(0f);
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
	    
	    return _chart;
	}
}
