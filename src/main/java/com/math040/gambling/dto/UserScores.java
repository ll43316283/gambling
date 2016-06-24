package com.math040.gambling.dto;

import java.util.ArrayList;
import java.util.List;

public class UserScores {
	
	private String name;
	
	private List<BetScore> datasets = new ArrayList<>();
	
	public UserScores(){
		
	}
	
	public UserScores(String name) {
		super();
		this.name = name;
	}
 
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<BetScore> getDatasets() {
		return datasets;
	}

	public void setDatasets(List<BetScore> datasets) {
		this.datasets = datasets;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserScores other = (UserScores) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	
	
	
}
