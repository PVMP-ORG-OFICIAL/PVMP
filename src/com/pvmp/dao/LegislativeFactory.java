package com.pvmp.dao;

import com.pvmp.models.Proposition;
import com.pvmp.models.User;
import com.pvmp.util.Util;

public class LegislativeFactory {

	public static DAOAbstract getLegislative (String legislativeModel)
	{
		if(legislativeModel.equals("Proposition"))
			return new Proposition();
		if(legislativeModel.equals("User"))
			return new User();

		Util.debug("Pass a valid model: LegislativeFactory Error");
		return null;
	}

}
