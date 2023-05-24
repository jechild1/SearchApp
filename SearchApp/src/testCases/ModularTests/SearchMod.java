package testCases.ModularTests;

import java.util.NoSuchElementException;

import org.testng.Assert;
import org.testng.Reporter;

import pageFactories.SearchPageFactory;
import testCases.SearchBaseTestScriptConfig;

/**
 * Modular script to perform the search function on the Main search page.
 * 
 * @author Jesse Childress
 *
 */
public class SearchMod extends SearchBaseTestScriptConfig {

	/**
	 * This search method simply enters the passed in search terms and performs a
	 * search. It does NOT handle domains nor does it select the API to use. By default, it unselects all domains, which searches the entire app.
	 * 
	 * @param searchText
	 */
	public void search(String searchText) {

		Reporter.log("Beginning Search Modular Test..." + System.lineSeparator()
				+ "This is for the main search page from login...", true);

		SearchPageFactory searchPF = new SearchPageFactory();

		searchPF.unselectAllDomains();
		
		searchPF.setSearchField(searchText);

		// Ensure that the text is in the field
		Assert.assertEquals(searchPF.readSearchField(), searchText);

		// Ensure correct API is selected.
		searchPF.setAPIDefault(DEFAULT_API);

		searchPF.clickSearchMagnifyingGlass();

	}

	/**
	 * This search method enters the passed in search terms and selects the correct
	 * API to use, corresponding to the passed in API. Selects are DEFAULT and LFQA.
	 * It does NOT handle domains. By default, it unselects all domains, which searches the entire app.
	 * 
	 * @param searchText
	 * @param api        (DEFAULT or LFQA)
	 */
	public void search(String searchText, String api) {

		Reporter.log("Beginning Search Modular Test..." + System.lineSeparator()
				+ "This is for the main search page from login...", true);

		SearchPageFactory searchPF = new SearchPageFactory();

		searchPF.unselectAllDomains();
		
		searchPF.setSearchField(searchText);

		// Ensure that the text is in the field
		Assert.assertEquals(searchPF.readSearchField(), searchText);

		// Ensure correct API is selected.
		if (api.equalsIgnoreCase("DEFAULT")) {
			searchPF.setAPIDefault(true);
		} else if (api.equalsIgnoreCase("LFQA")) {
			searchPF.setLFQADefault(true);
		} else {
			throw new NoSuchElementException("You must pass in a valid API. Selections are DEFAULT and LFQA");
		}

		searchPF.clickSearchMagnifyingGlass();

	}

	/**
	 * This search method enters the passed in search terms and selects the correct
	 * API to use, corresponding to the passed in API. Selects are DEFAULT and LFQA.
	 * It will handle selecting all of the domains that are passed in.
	 * 
	 * @param searchText the text that you'd like to search on
	 * @param API        either LFQA or DEFAULT
	 * @param Domain(s)    String array that contains domains. Case has to be correct
	 */
	public void search(String searchText, String api, String... domains) {

		Reporter.log("Beginning Search Modular Test..." + System.lineSeparator()
				+ "This is for the main search page from login...", true);

		SearchPageFactory searchPF = new SearchPageFactory();

		// Select the domains that should be used in the search
		searchPF.selectDomain(domains);

		// Set the search field.
		searchPF.setSearchField(searchText);

		// Ensure that the text is in the field
		Assert.assertEquals(searchPF.readSearchField(), searchText);

		// Ensure correct API is selected.
		if (api.equalsIgnoreCase("DEFAULT")) {
			searchPF.setAPIDefault(true);
		} else if (api.equalsIgnoreCase("LFQA")) {
			searchPF.setLFQADefault(true);
		} else {
			throw new NoSuchElementException("You must pass in a valid API. Selections are DEFAULT and LFQA");
		}

		searchPF.clickSearchMagnifyingGlass();

	}
}
