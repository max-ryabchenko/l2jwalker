package com.l2jwalker.util;

import com.l2jwalker.dao.support.OrderByDirection;
import com.l2jwalker.dao.support.SearchMode;
import com.l2jwalker.dao.support.SearchTemplate;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import java.io.Serializable;

public class SearchParameters implements Serializable {
    public static final String ASCENDING = "ascending";
    public static final String DESCENDING = "descending";
    private static final long serialVersionUID = 1L;
    private static final Logger log = Logger.getLogger(SearchParameters.class);
    private int pageNumber = 1;
    private int pageSize = 6;
    private SearchMode searchMode = SearchMode.ANYWHERE;
    private String sortColumnKey;
    private String sortOrder = ASCENDING;
    private String searchPattern;
    private boolean caseSensitive;
    private String namedQuery;

    /**
     * Returns the page number used to limit the search. It is always positive (starts at 1).
     */
    public int getPageNumber() {
        return pageNumber;
    }

    /**
     * Limit the search to the passed page number. Must be positive (starts at 1). Its default value is 1.
     */
    public void setPageNumber(int pageNumber) {
        if (pageNumber <= 0) {
            log.warn("trying to access invalid page number: " + pageNumber);
            pageNumber = 1;
        }

        this.pageNumber = pageNumber;
    }

    /**
     * Return the max number of search items that can be displayed per page.
     */
    public int getPageSize() {
        return pageSize;
    }

    /**
     * Set the max number of items that can be displayed per page. The page size must be positive (starts at 1). It is
     * used to limit the search.
     */
    public void setPageSize(int pageSize) {
        if (pageSize > 0) {
            this.pageSize = pageSize;
        } else {
            log.warn("trying to set an invalid page size: " + pageSize);
        }
    }

    public SearchMode getSearchMode() {
        return searchMode;
    }

    /**
     * Set the search mode as a String.
     *
     * @param searchMode
     */
    public void setSearchMode(SearchMode searchMode) {
        this.searchMode = searchMode;
    }

    /**
     * Returns the column name used to sort the search result.
     */
    public String getSortColumnKey() {
        return sortColumnKey;
    }

    /**
     * Indicates that search result must be sort by the passed sortColumnKey.
     *
     * @param sortColumnKey
     */
    public void setSortColumnKey(String sortColumnKey) {
        this.sortColumnKey = sortColumnKey;
    }

    public boolean hasSortColumnKey() {
        return StringUtils.isNotBlank(sortColumnKey);
    }

    /**
     * Return the sort id that is ASCENDING, DESCENDING or an empty String.
     */
    public String getSortOrder() {
        return sortOrder;
    }

    /**
     * Set the sort id.
     *
     * @param sortOrder either ASCENDING, DESCENDING or an empty String
     */
    public void setSortOrder(String sortOrder) {
        if (ASCENDING.equalsIgnoreCase(sortOrder) || DESCENDING.equalsIgnoreCase(sortOrder) || "".equals(sortOrder)) {
            this.sortOrder = sortOrder;
        }
    }

    /**
     * Return the Sort Order for the passed columns. It is used by the view.
     */
    public String getSortOrderFor(String sortColumnKey) {
        if (getSortColumnKey().equalsIgnoreCase(sortColumnKey)) {
            return getSortOrder();
        }

        return "";
    }

    /**
     * Return the reverse Sort Order for the passed columns. It is used by the view.
     *
     * @return DESCENDING if sort id is ASCENDING; ASCENDING otherwise.
     */
    public String getReverseSortOrderFor(String sortColumnKey) {
        if (ASCENDING.equals(getSortOrderFor(sortColumnKey))) {
            return DESCENDING;
        }

        return ASCENDING;
    }

    /**
     * Returns the search pattern that must be applied to all string fields when executing the search.
     */
    public String getSearchPattern() {
        return searchPattern;
    }

    /**
     * Set the search pattern to apply to all string fields for executing the search.
     *
     * @param searchPattern
     */
    public void setSearchPattern(String searchPattern) {
        this.searchPattern = searchPattern;
    }

    public boolean hasSearchPattern() {
        return StringUtils.isNotBlank(searchPattern);
    }

    /**
     * Return true for case sensitive search, false for case insensitive search.
     */
    public boolean getCaseSensitive() {
        return caseSensitive;
    }

    /**
     * Set the case sensitiveness of the search. Its default value is false.
     *
     * @param caseSensitive true for case sensitive search, false for case insensitive search.
     */
    public void setCaseSensitive(boolean caseSensitive) {
        this.caseSensitive = caseSensitive;
    }

    /**
     * Return the named query that must be used to perform the search.
     */
    public String getNamedQuery() {
        return namedQuery;
    }

    /**
     * Set the named query that must be used to perform the search.
     *
     * @param namedQuery an hibernate named query.
     */
    public void setNamedQuery(String namedQuery) {
        this.namedQuery = namedQuery;
    }

    public boolean hasNamedQuery() {
        return StringUtils.isNotBlank(namedQuery);
    }

    /**
     * Convert this searchParameters instance to a SearchTemplate. Copy all the property and calculate the first and max
     * results properties depending on the pageSize and pageNumber properties of the passed searchParameters.
     *
     * @return a searchTemplate to be used with a manager finder method.
     */
    public SearchTemplate toSearchTemplate() {
        SearchTemplate searchTemplate = new SearchTemplate();
        searchTemplate.setSearchMode(getSearchMode());
        searchTemplate.setNamedQuery(getNamedQuery());
        searchTemplate.setSearchPattern(getSearchPattern());
        searchTemplate.setCaseSensitive(getCaseSensitive());
        OrderByDirection direction = null;
        if (DESCENDING.equals(getSortOrder())) {
            direction = OrderByDirection.DESC;
        } else if (ASCENDING.equals(getSortOrder())) {
            direction = OrderByDirection.ASC;
        }
        if (getSortColumnKey() != null) {
            searchTemplate.addOrderBy(getSortColumnKey(), direction);
        }
        searchTemplate.setFirstResult(getPageSize() * (getPageNumber() - 1));
        searchTemplate.setMaxResults(getPageSize());
        return searchTemplate;
    }
}
