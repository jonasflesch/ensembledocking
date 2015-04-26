package br.com.jonasflesch.ensembledocking.model;

/**
 * Created by jonasflesch on 4/26/15.
 */
public class Mdp {
	private String title;
	private String cpp;
	private String constraints;
	private String integrator;
	private Integer tinit;
	/**ps*/
	private Double dt;
	/**total 100 ps*/
	private Integer nsteps;
	private Integer nstcomm;
	private Integer nstxout;
	private Integer nstvout;
	private Integer nstfout;
	private Integer nstlog;
	private Integer nstenergy;
	private Integer nstlist;
	private String ns_type;
	private Double rlist;
	private String coulombtype;
	private Double rcoulomb;
	private String vdwtype;
	private Double rvdw;
	private Double fourierspacing;
	private Integer fourier_nx;
	private Integer fourier_ny;
	private Integer fourier_nz;
	private Integer pme_order;
	private Double ewald_rtol;
	private String optimize_fft;
	private String Tcoupl;
	private Double[] tau_t;
	private String[] tc_grps;
	private Integer[] ref_t;
	private String Pcoupl;
	private String pcoupltype;
	private Double tau_p;
	private Double compressibility;
	private Double ref_p;
	private String gen_vel;
	private Double gen_temp;
	private Integer gen_seed;
	private String define;
	private Integer emtol;
	private Double emstep;

	public void setTo100ps(){
		title = "fws";
		cpp = "/usr/bin/cpp";
		constraints = "all-bonds";
		integrator = "md";
		tinit = 0;
		dt =  0.002;
		nsteps = 5000;
		nstcomm = 1;
		nstxout =  500;
		nstvout = 0;
		nstfout =  0;
		nstlog =  100;
		nstenergy = 100;
		nstlist = 10;
		ns_type = "grid";
		rlist = 1.0;
		coulombtype = "PME";
		rcoulomb =  1.0;
		vdwtype = "Cut-off";
		rvdw = 1.4;
		fourierspacing =  0.12;
		fourier_nx = 0;
		fourier_ny =  0;
		fourier_nz =  0;
		pme_order =  4;
		ewald_rtol =  1e-5;
		optimize_fft = "yes";
		Tcoupl = "berendsen";
		tau_t =  new Double[]{0.1, 0.1};
		tc_grps = new String[]{"Protein", "SOL"};
		ref_t = new Integer[]{300, 300};
		Pcoupl = "parrinello-rahman";
		pcoupltype = "isotropic";
		tau_p =  0.5;
		compressibility =  4.5e-5;
		ref_p =  1.0;
		gen_vel = "yes";
		gen_temp = 300.0;
		gen_seed =  173529;
	}

	public void setToEm(){
		cpp =  "/lib/cpp";
		define = "-DFLEX_SPC";
		constraints = "none";
		integrator = "steep";
		nsteps = 500;
		emtol =  2000;
		emstep =  0.01;
		nstcomm             =  1;
		ns_type             =  "grid";
		rlist               =  1.0;
		rcoulomb            =  1.0;
		rvdw                =  1.0;
		Tcoupl              =  "no";
		Pcoupl              =  "no";
		gen_vel             =  "no";
	}

	public void setToPr(){
		title = "Yo";
		cpp                 =  "/lib/cpp";
		define              =  "-DPOSRES";
		constraints         =  "all-bonds";
		integrator          =  "md";
		dt                  =  0.002;
		nsteps              =  500;
		nstcomm             =  1;
		nstxout             =  250;
		nstvout             =  1000;
		nstfout             =  0;
		nstlog              =  100;
		nstenergy           =  100;
		nstlist             =  10;
		ns_type             =  "grid";
		rlist               =  1.0;
		coulombtype        =  "PME";
		rcoulomb            =  1.0;
		vdwtype             =  "cut-off";
		rvdw                =  1.4;
		fourierspacing        =  0.12;
		fourier_nx        =  0;
		fourier_ny        =  0;
		fourier_nz        =  0;
		pme_order        =  4;
		ewald_rtol        =  1e-5;
		optimize_fft        =  "yes";

		Tcoupl              =  "berendsen";
		tau_t               =  new Double[]{0.1,0.1};
		tc_grps             =  new String[]{"Protein", "SOL"};
		ref_t               =  new Integer[]{100, 100};
		Pcoupl              =  "parrinello-rahman";
		pcoupltype          =  "isotropic";
		tau_p               =  0.5;
		compressibility     =  4.5e-5;
		ref_p               =  1.0;
		gen_vel             =  "yes";
		gen_temp            =  100.0;
		gen_seed            =  173529;

	}

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

	public String getDefine() {
		return define;
	}

	public void setDefine(String define) {
		this.define = define;
	}

	public Integer getEmtol() {
		return emtol;
	}

	public void setEmtol(Integer emtol) {
		this.emtol = emtol;
	}

	public Double getEmstep() {
		return emstep;
	}

	public void setEmstep(Double emstep) {
		this.emstep = emstep;
	}
}
