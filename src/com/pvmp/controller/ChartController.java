package com.pvmp.controller;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.Color;

import com.github.mikephil.charting.charts.PieChart;
import com.pvmp.model.Deputy;
import com.pvmp.model.Party;
import com.pvmp.model.Proposition;
import com.pvmp.model.Vote;
import com.pvmp.model.Voting;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

public class ChartController 
{
	public static final String ALL_VOTES = "Todos";
	public static final String YES_VOTES = "Sim";
	public static final String NO_VOTES = "Não";
	private DeputyController deputyController;
	private VoteController voteController;
	
	public ChartController(Context _context)
	{
		this.deputyController = new DeputyController(_context);
		this.voteController = new VoteController(_context);
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
		
		deputies = this.deputyController.getDeputiesThatVotedOnSession(votes);
		for (int i = 0; i < deputies.size(); i++) {
			Party party = new Party();
			party = deputies.get(i).getParty();
			
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
		
		voting = _proposition.getVoting();
		votes = this.voteController.getVotingVotes(voting);
		
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
					slicesTitles.add(Party.parseNumber((results.get(i)[0])));
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
		
		int[] colors_chart = {Color.parseColor("#30ba8f"),
							  Color.parseColor("#b63528"),
							  Color.parseColor("#ff8c69"),
							  Color.parseColor("#21abcd"),
							  Color.parseColor("#5d8aa8"),
							  Color.parseColor("#de5d83"),
							  Color.parseColor("#ffbf00")};
		
		pieDataSet = new PieDataSet(slicesValues, "");
		if (_tag.equals(ALL_VOTES))
			pieDataSet.setColors(colors_chart);
		else if(_tag.equals(YES_VOTES))
			pieDataSet.setColors(colors_chart);
		else
			pieDataSet.setColors(colors_chart);
		pieDataSet.setSliceSpace(0f);
		_chart.setData(new PieData(slicesTitles, pieDataSet));
		
		return _chart;
	}
}
