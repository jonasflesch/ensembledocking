package br.com.jonasflesch.ensembledocking.core;

import org.junit.Test;

import javax.inject.Inject;
import java.io.File;
import java.io.IOException;

import static org.junit.Assert.assertTrue;

/**
 * Created by jonasflesch on 4/20/15.
 */
public class GromacsCallerTest  extends AbstractTest {

	@Inject
	private GromacsCaller gromacsCaller;

	@Test
	public void pdb2gmx() throws IOException, InterruptedException {
		copyFile("free.pdb");

		String inputFilePath = directory.getPath() + File.separator + "free.pdb";

		gromacsCaller.pdb2gmx(inputFilePath);

		assertTrue("Coordinates file should be created", new File(directory.getPath() + File.separator + "free.gro").exists());
		assertTrue("Topology file should be created", new File(directory.getPath() + File.separator + "free.top").exists());
	}

	@Test
	public void editconf() throws IOException, InterruptedException {
		copyFile("free.gro");

		String inputFilePath = directory.getPath() + File.separator + "free.gro";

		gromacsCaller.editconf(inputFilePath);

		assertTrue("New gro file should be created", new File(directory.getPath() + File.separator + "free_edt.gro").exists());
	}

	@Test
	public void genbox() throws IOException, InterruptedException {
		copyFile("free_edt.gro");
		copyFile("free.top");

		String groFilePath = directory.getPath() + File.separator + "free_edt.gro";
		String topFilePath = directory.getPath() + File.separator + "free.top";

		gromacsCaller.genbox(groFilePath, topFilePath);

		assertTrue("New gro box file should be created", new File(directory.getPath() + File.separator + "free_box.gro").exists());
	}

	@Test
	public void gromppMinimizationEnergy() throws IOException, InterruptedException {
		copyFile("free_box.gro");
		copyFile("free_box.top");
		copyFile("em.mdp");

		String groBoxFilePath = directory.getPath() + File.separator + "free_box.gro";
		String topFilePath = directory.getPath() + File.separator + "free_box.top";
		String mdpFilePath = directory.getPath() + File.separator + "em.mdp";
		String tprOutputFile = directory.getPath() + File.separator + "free_em.tpr";

		gromacsCaller.gromppMinimizationEnergy(mdpFilePath, groBoxFilePath, tprOutputFile, topFilePath);

		assertTrue("New tpr file should be created", new File(tprOutputFile).exists());
	}

	@Test
	public void mdrun() throws IOException, InterruptedException {
		copyFile("free_em.tpr");

		String tprFilePath = directory.getPath() + File.separator + "free_em.tpr";
		String trrFilePath = directory.getPath() + File.separator + "free_em.trr";
		String groFilePath = directory.getPath() + File.separator + "free_em.gro";

		gromacsCaller.mdrun(tprFilePath, trrFilePath, groFilePath);

		assertTrue("New trr file should be created", new File(trrFilePath).exists());
		assertTrue("New gro file should be created", new File(groFilePath).exists());
	}

	@Test
	public void gromppMdWithPositionRestraint() throws IOException, InterruptedException {
		copyFile("pr.mdp");
		copyFile("free_em.gro");
		copyFile("free_box.top");
		copyFile("posre.itp");

		String mdpFilePath = directory.getPath() + File.separator + "pr.mdp";
		String groEmFilePath = directory.getPath() + File.separator + "free_em.gro";
		String topBoxFilePath = directory.getPath() + File.separator + "free_box.top";
		String tprFilePath = directory.getPath() + File.separator + "free_pr.tpr";

		gromacsCaller.gromppMdWithPositionRestraint(mdpFilePath, tprFilePath, groEmFilePath, topBoxFilePath);

		assertTrue("New tpr file should be created", new File(tprFilePath).exists());
	}

}