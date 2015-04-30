package br.com.jonasflesch.ensembledocking.model;

/**
 * Created by jonasflesch on 4/27/15.
 */
public class DockingResultDto {
	private String bestDockingImage;
	private String freeEnergyOverTimeChart;

	public String getBestDockingImage() {
		return bestDockingImage;
	}

	public void setBestDockingImage(String bestDockingImage) {
		this.bestDockingImage = bestDockingImage;
	}

	public String getFreeEnergyOverTimeChart() {
		return freeEnergyOverTimeChart;
	}

	public void setFreeEnergyOverTimeChart(String freeEnergyOverTimeChart) {
		this.freeEnergyOverTimeChart = freeEnergyOverTimeChart;
	}
}
