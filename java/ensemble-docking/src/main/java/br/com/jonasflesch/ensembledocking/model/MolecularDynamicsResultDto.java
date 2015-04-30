package br.com.jonasflesch.ensembledocking.model;

/**
 * Created by jonasflesch on 4/27/15.
 */
public class MolecularDynamicsResultDto {

	private String[] conformations;

	private Integer steps;

	public String[] getConformations() {
		return conformations;
	}

	public void setConformations(String[] conformations) {
		this.conformations = conformations;
	}

	public Integer getSteps() {
		return steps;
	}

	public void setSteps(Integer steps) {
		this.steps = steps;
	}
}
