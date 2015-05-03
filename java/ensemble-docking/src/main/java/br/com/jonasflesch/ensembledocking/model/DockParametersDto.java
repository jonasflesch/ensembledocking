package br.com.jonasflesch.ensembledocking.model;

import java.io.File;

/**
 * Created by jonasflesch on 5/3/15.
 */
public class DockParametersDto {

	private File pdbFileLigand;
	private File pdbFileReceptor;

	private Integer molecularDynamicsSteps;
	private Integer molecularDynamicsOut;

	public File getPdbFileLigand() {
		return pdbFileLigand;
	}

	public void setPdbFileLigand(File pdbFileLigand) {
		this.pdbFileLigand = pdbFileLigand;
	}

	public File getPdbFileReceptor() {
		return pdbFileReceptor;
	}

	public void setPdbFileReceptor(File pdbFileReceptor) {
		this.pdbFileReceptor = pdbFileReceptor;
	}

	public Integer getMolecularDynamicsSteps() {
		return molecularDynamicsSteps;
	}

	public void setMolecularDynamicsSteps(Integer molecularDynamicsSteps) {
		this.molecularDynamicsSteps = molecularDynamicsSteps;
	}

	public Integer getMolecularDynamicsOut() {
		return molecularDynamicsOut;
	}

	public void setMolecularDynamicsOut(Integer molecularDynamicsOut) {
		this.molecularDynamicsOut = molecularDynamicsOut;
	}
}
