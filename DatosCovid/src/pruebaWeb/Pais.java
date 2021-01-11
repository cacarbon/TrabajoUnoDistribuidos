package pruebaWeb;

import java.util.List;

public class Pais implements Comparable<Pais> {
	
	private String updated;
	private String country;
//	private List<String> countryInfo;
	private InfoPais countryInfo;
	private String cases;
	private String todayCases;
	private String deaths;
	private String todayDeaths;
	private String recovered;
	private String todayRecovered;
	private String active;
	private String critical;
	private String casesPerOneMillion;
	private String deathsPerOneMillion;
	private String tests;
	private String testsPerOneMillion;
	private String population;
	private String continent;
	private String oneCasePerPeople;
	private String oneDeathPerPeople;
	private String oneTestPerPeople;
	private String activePerOneMillion;
	private String recoveredPerOneMillion;
	private String criticalPerOneMillion;
	
	
	
	
	public Pais(String updated, String country, InfoPais countryInfo, String cases, String todayCases,
			String deaths, String todayDeaths, String recovered, String todayRecovered, String active, String critical,
			String casesPerOneMillion, String deathsPerOneMillion, String tests, String testsPerOneMillion,
			String population, String continent, String oneCasePerPeople, String oneDeathPerPeople,
			String oneTestPerPeople, String activePerOneMillion, String recoveredPerOneMillion,
			String criticalPerOneMillion) {
		super();
		this.updated = updated;
		this.country = country;
		this.countryInfo = countryInfo;
		this.cases = cases;
		this.todayCases = todayCases;
		this.deaths = deaths;
		this.todayDeaths = todayDeaths;
		this.recovered = recovered;
		this.todayRecovered = todayRecovered;
		this.active = active;
		this.critical = critical;
		this.casesPerOneMillion = casesPerOneMillion;
		this.deathsPerOneMillion = deathsPerOneMillion;
		this.tests = tests;
		this.testsPerOneMillion = testsPerOneMillion;
		this.population = population;
		this.continent = continent;
		this.oneCasePerPeople = oneCasePerPeople;
		this.oneDeathPerPeople = oneDeathPerPeople;
		this.oneTestPerPeople = oneTestPerPeople;
		this.activePerOneMillion = activePerOneMillion;
		this.recoveredPerOneMillion = recoveredPerOneMillion;
		this.criticalPerOneMillion = criticalPerOneMillion;
	}
	
	public String getUpdated() {
		return updated;
	}
	public void setUpdated(String updated) {
		this.updated = updated;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public InfoPais getCountryInfo() {
		return countryInfo;
	}
	public void setCountryInfo(InfoPais countryInfo) {
		this.countryInfo = countryInfo;
	}
	public String getCases() {
		return cases;
	}
	public void setCases(String cases) {
		this.cases = cases;
	}
	public String getTodayCases() {
		return todayCases;
	}
	public void setTodayCases(String todayCases) {
		this.todayCases = todayCases;
	}
	public String getDeaths() {
		return deaths;
	}
	public void setDeaths(String deaths) {
		this.deaths = deaths;
	}
	public String getTodayDeaths() {
		return todayDeaths;
	}
	public void setTodayDeaths(String todayDeaths) {
		this.todayDeaths = todayDeaths;
	}
	public String getRecovered() {
		return recovered;
	}
	public void setRecovered(String recovered) {
		this.recovered = recovered;
	}
	public String getTodayRecovered() {
		return todayRecovered;
	}
	public void setTodayRecovered(String todayRecovered) {
		this.todayRecovered = todayRecovered;
	}
	public String getActive() {
		return active;
	}
	public void setActive(String active) {
		this.active = active;
	}
	public String getCritical() {
		return critical;
	}
	public void setCritical(String critical) {
		this.critical = critical;
	}
	public String getCasesPerOneMillion() {
		return casesPerOneMillion;
	}
	public void setCasesPerOneMillion(String casesPerOneMillion) {
		this.casesPerOneMillion = casesPerOneMillion;
	}
	public String getDeathsPerOneMillion() {
		return deathsPerOneMillion;
	}
	public void setDeathsPerOneMillion(String deathsPerOneMillion) {
		this.deathsPerOneMillion = deathsPerOneMillion;
	}
	public String getTests() {
		return tests;
	}
	public void setTests(String tests) {
		this.tests = tests;
	}
	public String getTestsPerOneMillion() {
		return testsPerOneMillion;
	}
	public void setTestsPerOneMillion(String testsPerOneMillion) {
		this.testsPerOneMillion = testsPerOneMillion;
	}
	public String getPopulation() {
		return population;
	}
	public void setPopulation(String population) {
		this.population = population;
	}
	public String getContinent() {
		return continent;
	}
	public void setContinent(String continent) {
		this.continent = continent;
	}
	public String getOneCasePerPeople() {
		return oneCasePerPeople;
	}
	public void setOneCasePerPeople(String oneCasePerPeople) {
		this.oneCasePerPeople = oneCasePerPeople;
	}
	public String getOneDeathPerPeople() {
		return oneDeathPerPeople;
	}
	public void setOneDeathPerPeople(String oneDeathPerPeople) {
		this.oneDeathPerPeople = oneDeathPerPeople;
	}
	public String getOneTestPerPeople() {
		return oneTestPerPeople;
	}
	public void setOneTestPerPeople(String oneTestPerPeople) {
		this.oneTestPerPeople = oneTestPerPeople;
	}
	public String getActivePerOneMillion() {
		return activePerOneMillion;
	}
	public void setActivePerOneMillion(String activePerOneMillion) {
		this.activePerOneMillion = activePerOneMillion;
	}
	public String getRecoveredPerOneMillion() {
		return recoveredPerOneMillion;
	}
	public void setRecoveredPerOneMillion(String recoveredPerOneMillion) {
		this.recoveredPerOneMillion = recoveredPerOneMillion;
	}
	public String getCriticalPerOneMillion() {
		return criticalPerOneMillion;
	}
	public void setCriticalPerOneMillion(String criticalPerOneMillion) {
		this.criticalPerOneMillion = criticalPerOneMillion;
	}
	
	public String toString() {
		String cadena="";
		cadena=cadena+ "Pais: "+this.getCountry()+"\n";
		cadena=cadena+ this.getCountryInfo().toString();
		cadena=cadena+ "Casos totales: "+this.getCases()+"\n";
		cadena=cadena+ "Casos hoy: "+this.getTodayCases()+"\n";
		cadena=cadena+ "Fallecimientos totales: "+this.getDeaths()+"\n";
		cadena=cadena+ "Fallecimientos hoy: "+this.getTodayDeaths()+"\n";
		cadena=cadena+ "Recuperados en total: "+this.getRecovered()+"\n";
		cadena=cadena+ "Recuperados hoy: "+this.getTodayRecovered()+"\n";
		cadena=cadena+ "Casos activos en total: "+this.getActive()+"\n";
		cadena=cadena+ "Casos criticos en total: "+this.getActive()+"\n";
		return cadena;
		
	}

	@Override
	public int compareTo(Pais pais) {
		// TODO Auto-generated method stub
		int n=0;
		try {
			int intThis=Integer.parseInt(this.getCases());
			int intPais=Integer.parseInt(pais.getCases());
			n=intPais-intThis;
		}catch (NumberFormatException e){
			n=0;
		}
		return n;
	}
	

}
