package br.com.jonasflesch.ensembledocking.model;

/**
 * Created by jonasflesch on 4/26/15.
 */
public class Mdp {
	private String title = "fws";
	private String cpp = "/usr/bin/cpp";
	private String constraints = "all-bonds";
	private String integrator = "md";
	private Integer tinit = 0;
	/**ps*/
	private Double dt =  0.002;
	/**total 100 ps*/
	private Integer nsteps = 50000;
	private Integer nstcomm = 1;
	private Integer nstxout =  500;
	private Integer nstvout = 0;
	private Integer nstfout =  0;
	private Integer nstlog =  100;
	private Integer nstenergy = 100;
	private Integer nstlist = 10;
	private String ns_type = "grid";
	private Double rlist = 1.0;
	private String coulombtype = "PME";
	private Double rcoulomb =  1.0;
	private String vdwtype = "Cut-off";
	private Double rvdw = 1.4;
	private Double fourierspacing =  0.12;
	private Integer fourier_nx = 0;
	private Integer fourier_ny =  0;
	private Integer fourier_nz =  0;
	private Integer pme_order =  4;
	private Double ewald_rtol =  1e-5;
	private String optimize_fft = "yes";
	private String Tcoupl = "berendsen";
	private Double[] tau_t =  {0.1, 0.1};
	private String[] tc_grps = {"Protein", "SOL"};
	private Integer[] ref_t = {300, 300};
	private String Pcoupl = "parrinello-rahman";
	private String pcoupltype = "isotropic";
	private Double tau_p =  0.5;
	private Double compressibility =  4.5e-5;
	private Double ref_p =  1.0;
	private String gen_vel = "yes";
	private Double gen_temp = 300.0;
	private Integer gen_seed =  173529;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getCpp() {
		return cpp;
	}

	public void setCpp(String cpp) {
		this.cpp = cpp;
	}

	public String getConstraints() {
		return constraints;
	}

	public void setConstraints(String constraints) {
		this.constraints = constraints;
	}

	public String getIntegrator() {
		return integrator;
	}

	public void setIntegrator(String integrator) {
		this.integrator = integrator;
	}

	public Integer getTinit() {
		return tinit;
	}

	public void setTinit(Integer tinit) {
		this.tinit = tinit;
	}

	public Double getDt() {
		return dt;
	}

	public void setDt(Double dt) {
		this.dt = dt;
	}

	public Integer getNsteps() {
		return nsteps;
	}

	public void setNsteps(Integer nsteps) {
		this.nsteps = nsteps;
	}

	public Integer getNstcomm() {
		return nstcomm;
	}

	public void setNstcomm(Integer nstcomm) {
		this.nstcomm = nstcomm;
	}

	public Integer getNstxout() {
		return nstxout;
	}

	public void setNstxout(Integer nstxout) {
		this.nstxout = nstxout;
	}

	public Integer getNstvout() {
		return nstvout;
	}

	public void setNstvout(Integer nstvout) {
		this.nstvout = nstvout;
	}

	public Integer getNstfout() {
		return nstfout;
	}

	public void setNstfout(Integer nstfout) {
		this.nstfout = nstfout;
	}

	public Integer getNstlog() {
		return nstlog;
	}

	public void setNstlog(Integer nstlog) {
		this.nstlog = nstlog;
	}

	public Integer getNstenergy() {
		return nstenergy;
	}

	public void setNstenergy(Integer nstenergy) {
		this.nstenergy = nstenergy;
	}

	public Integer getNstlist() {
		return nstlist;
	}

	public void setNstlist(Integer nstlist) {
		this.nstlist = nstlist;
	}

	public String getNs_type() {
		return ns_type;
	}

	public void setNs_type(String ns_type) {
		this.ns_type = ns_type;
	}

	public Double getRlist() {
		return rlist;
	}

	public void setRlist(Double rlist) {
		this.rlist = rlist;
	}

	public String getCoulombtype() {
		return coulombtype;
	}

	public void setCoulombtype(String coulombtype) {
		this.coulombtype = coulombtype;
	}

	public Double getRcoulomb() {
		return rcoulomb;
	}

	public void setRcoulomb(Double rcoulomb) {
		this.rcoulomb = rcoulomb;
	}

	public String getVdwtype() {
		return vdwtype;
	}

	public void setVdwtype(String vdwtype) {
		this.vdwtype = vdwtype;
	}

	public Double getRvdw() {
		return rvdw;
	}

	public void setRvdw(Double rvdw) {
		this.rvdw = rvdw;
	}

	public Double getFourierspacing() {
		return fourierspacing;
	}

	public void setFourierspacing(Double fourierspacing) {
		this.fourierspacing = fourierspacing;
	}

	public Integer getFourier_nx() {
		return fourier_nx;
	}

	public void setFourier_nx(Integer fourier_nx) {
		this.fourier_nx = fourier_nx;
	}

	public Integer getFourier_ny() {
		return fourier_ny;
	}

	public void setFourier_ny(Integer fourier_ny) {
		this.fourier_ny = fourier_ny;
	}

	public Integer getFourier_nz() {
		return fourier_nz;
	}

	public void setFourier_nz(Integer fourier_nz) {
		this.fourier_nz = fourier_nz;
	}

	public Integer getPme_order() {
		return pme_order;
	}

	public void setPme_order(Integer pme_order) {
		this.pme_order = pme_order;
	}

	public Double getEwald_rtol() {
		return ewald_rtol;
	}

	public void setEwald_rtol(Double ewald_rtol) {
		this.ewald_rtol = ewald_rtol;
	}

	public String getOptimize_fft() {
		return optimize_fft;
	}

	public void setOptimize_fft(String optimize_fft) {
		this.optimize_fft = optimize_fft;
	}

	public String getTcoupl() {
		return Tcoupl;
	}

	public void setTcoupl(String tcoupl) {
		Tcoupl = tcoupl;
	}

	public Double[] getTau_t() {
		return tau_t;
	}

	public void setTau_t(Double[] tau_t) {
		this.tau_t = tau_t;
	}

	public String[] getTc_grps() {
		return tc_grps;
	}

	public void setTc_grps(String[] tc_grps) {
		this.tc_grps = tc_grps;
	}

	public Integer[] getRef_t() {
		return ref_t;
	}

	public void setRef_t(Integer[] ref_t) {
		this.ref_t = ref_t;
	}

	public String getPcoupl() {
		return Pcoupl;
	}

	public void setPcoupl(String pcoupl) {
		Pcoupl = pcoupl;
	}

	public String getPcoupltype() {
		return pcoupltype;
	}

	public void setPcoupltype(String pcoupltype) {
		this.pcoupltype = pcoupltype;
	}

	public Double getTau_p() {
		return tau_p;
	}

	public void setTau_p(Double tau_p) {
		this.tau_p = tau_p;
	}

	public Double getCompressibility() {
		return compressibility;
	}

	public void setCompressibility(Double compressibility) {
		this.compressibility = compressibility;
	}

	public Double getRef_p() {
		return ref_p;
	}

	public void setRef_p(Double ref_p) {
		this.ref_p = ref_p;
	}

	public String getGen_vel() {
		return gen_vel;
	}

	public void setGen_vel(String gen_vel) {
		this.gen_vel = gen_vel;
	}

	public Double getGen_temp() {
		return gen_temp;
	}

	public void setGen_temp(Double gen_temp) {
		this.gen_temp = gen_temp;
	}

	public Integer getGen_seed() {
		return gen_seed;
	}

	public void setGen_seed(Integer gen_seed) {
		this.gen_seed = gen_seed;
	}

}
