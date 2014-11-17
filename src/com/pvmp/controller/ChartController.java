package com.pvmp.controller;

import java.util.ArrayList;

import android.content.Context;

import com.github.mikephil.charting.charts.PieChart;
import com.pvmp.models.Deputy;
import com.pvmp.models.PVMPmodel;
import com.pvmp.models.Party;
import com.pvmp.models.Proposition;
import com.pvmp.models.Vote;
import com.pvmp.models.Voting;
import com.pvmp.util.Util;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

public class ChartController 
{
	public static final String ALL_VOTES = "Todos";
	public static final String YES_VOTES = "Sim";
	public static final String NO_VOTES = "Não";
	private PVMPmodel model;
	private VotingController votingController;
	
	public ChartController(Context _context)
	{
		this.model = new PVMPmodel(_context);
		this.votingController = new VotingController(_context);
	}
	
	public ArrayList<Vote> selectVotesByType (ArrayList<Vote> _votes, String _tag)
	{
		ArrayList<Vote> votes = new ArrayList<Vote>();
		for (Vote vote : _votes) 
		{
			String voteResult = vote.getResult().trim();
			
			if(voteResult.equals(_tag))
			{
				votes.add(vote);
			}
		}
		return votes;
	}
	
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
	
	public ArrayList<float[]> calculatePartiesResultPercentage (ArrayList<Vote> _votes, String _tag)
	{
		ArrayList<Vote> votes = this.selectVotesByType(_votes, _tag);
		float totalVotes = (float) votes.size();
		ArrayList<Deputy> deputies = new ArrayList<Deputy>();
		ArrayList<Party> parties = new ArrayList<Party>();
		ArrayList<float[]> results = new ArrayList<float[]>();
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
				float [] result = {party.getNumber(), 1};
				results.add(result);
			}
			else
			{
				aux = 0;
				for (int i = 0; i < results.size(); i++)
				{
					float[] result = new float[]{results.get(i)[0], results.get(i)[1]};
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
					float[] result = {party.getNumber(), 1};
					results.add(result);
				}
			}
		}
		aux = 0;
		for (float[] result : results) {
			result[1] = (result[1]*100f)/totalVotes;
			results.set(aux, result);
			aux++;
		}
		
		for (int i = 0; i < results.size(); i++) {
			Util.debug("Partido: " + this.model.getPartyAcronym(results.get(i)[0])
						+ ". Qtd: " + results.get(i)[1]);
		}

		return results;
	}
	
	public PieChart prepareGraphicData (Proposition _proposition, PieChart _chart, String _tag)
	{
		int contador;
		Voting voting = new Voting();
		ArrayList<Vote> votes = new ArrayList<Vote>();
		ArrayList<float[]> results = new ArrayList<float[]>();
		ArrayList<Float> votesPercentage = new ArrayList<Float>();
		ArrayList<String> slicesTitles = new ArrayList<String>();
		ArrayList<Entry> slicesValues = new ArrayList<Entry>();
		float totalOthers = 0;
		PieDataSet pieDataSet;
		
		voting = this.votingController.getPropositionVoting(_proposition);
		votes = this.model.getVotingVotes(voting);
		
		if (_tag.equals(ALL_VOTES)) 
		{
			votesPercentage = this.calculateVotesResultPercentage(votes);
			slicesTitles.add("Sim");
			slicesTitles.add("Não");
		}
		else 
		{
			results = calculatePartiesResultPercentage(votes, _tag);
			for (int i = 0; i < results.size(); i++) 
			{
				float actualValue = results.get(i)[1];
				
				if (actualValue >= 10) 
				{
					slicesTitles.add(this.model.getPartyAcronym(results.get(i)[0]));
					votesPercentage.add(results.get(i)[1]);
				}
				else 
				{
					totalOthers += actualValue;
				}
			}
			slicesTitles.add("Outros");
			votesPercentage.add(totalOthers);
		}
		
		contador = 0;
		for (float votePercentage : votesPercentage) 
		{
			slicesValues.add(new Entry(votePercentage, contador));
			contador++;
		}
		
		pieDataSet = new PieDataSet(slicesValues, "");
		if (_tag.equals(ALL_VOTES))
			pieDataSet.setColors(ColorTemplate.COLORFUL_COLORS);
		else if(_tag.equals(YES_VOTES))
			pieDataSet.setColors(ColorTemplate.VORDIPLOM_COLORS);
		else
			pieDataSet.setColors(ColorTemplate.JOYFUL_COLORS);
		pieDataSet.setSliceSpace(0f);
		_chart.setData(new PieData(slicesTitles, pieDataSet));
		
		return _chart;
	}
}
