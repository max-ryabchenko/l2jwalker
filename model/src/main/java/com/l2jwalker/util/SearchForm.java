package com.l2jwalker.util;

import com.l2jwalker.dao.support.DateRange;
import com.l2jwalker.dao.support.SearchTemplate;
import javolution.util.FastList;
import javolution.util.FastMap;
import org.hibernate.Criteria;
import org.hibernate.criterion.Junction;
import org.hibernate.criterion.Restrictions;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static com.l2jwalker.util.NullRestriction.NullRestrictionKind.IS_NOT_NULL;
import static com.l2jwalker.util.NullRestriction.NullRestrictionKind.IS_NULL;
import static org.hibernate.criterion.Restrictions.isNotNull;
import static org.hibernate.criterion.Restrictions.isNull;

public class SearchForm implements Serializable {
    private static final long serialVersionUID = 1L;
    private SearchParameters searchParameters = new SearchParameters();

    /**
     * @return the {@link SearchParameters} controlling search meta attributes (id, pagination, etc.)
     */
    public SearchParameters getSp() {
        return searchParameters;
    }

    /**
     * Override it in subclass in id to provide specific {@link com.l2jwalker.dao.support.DateRange} criteria to search.
     */
    protected List<DateRange<?>> getDateRanges() {
        return new FastList<DateRange<?>>();
    }

    /**
     * Override it in subclass in id to provide specific {@link NullRestriction} criteria in search.
     */
    protected List<NullRestriction> getNullRestrictions() {
        return new FastList<NullRestriction>();
    }

    protected Map<String, List<Integer>> getInRestrictions() {
        return new FastMap<String, List<Integer>>();
    }

    protected Map<String, Set<String>> getLikeRestrictions() {
        return new FastMap<String, Set<String>>();
    }

    protected Junction postCriteria(Criteria criteria) {
        return Restrictions.disjunction();
    }

    protected Junction getDisjunction() {
        return Restrictions.disjunction();
    }

    protected List<AliasInfo> getAliasInfo() {
        return new FastList<AliasInfo>();
    }

    /**
     * Copy this search form information to a new {@link com.l2jwalker.dao.support.SearchTemplate} and returns it.
     */
    public SearchTemplate toSearchTemplate() {
        // search meta parameters
        SearchTemplate searchTemplate = searchParameters.toSearchTemplate();

        // date ranges
        searchTemplate.setDateRanges(getDateRanges());

        // null/not null criterion
        for (NullRestriction nr : getNullRestrictions()) {
            if (nr.getRestriction() == IS_NULL) {
                searchTemplate.addCriterion(isNull(nr.getProperty()));
            } else if (nr.getRestriction() == IS_NOT_NULL) {
                searchTemplate.addCriterion(isNotNull(nr.getProperty()));
            }
        }
        for (String key : getInRestrictions().keySet()) {
            searchTemplate.addCriterion(Restrictions.in(key, getInRestrictions().get(key)));
        }

        searchTemplate.addCriterion(getDisjunction());

        searchTemplate.addAlias(getAliasInfo());

        return searchTemplate;
    }

    public static class AliasInfo {
        public final String associationPath;

        public final String alias;

        public final int criteriaSpecification;

        public AliasInfo(String associationPath, String alias, int criteriaSpecification) {
            this.associationPath = associationPath;
            this.alias = alias;
            this.criteriaSpecification = criteriaSpecification;
        }
    }
}
