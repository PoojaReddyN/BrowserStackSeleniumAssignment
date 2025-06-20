Feature: Scrape and Translate Articles from El País Opinion Section

  Scenario: Fetch first 5 articles and translate their titles
    Given I open the browser and navigate to the El País Opinion section
    And  I am on the El País Opinion page
    When I scrape the first five articles
    Then I should see article titles and content in Spanish
    And the titles should be translated to English and printed
    And images should be downloaded locally
    And The headers should be analyzed accordingly
    And The driver should quit
