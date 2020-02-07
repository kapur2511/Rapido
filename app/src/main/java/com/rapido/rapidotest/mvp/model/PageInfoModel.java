/*
 * Copyright (C) 11-May-2019 Cricbuzz.com
 * All rights reserved.
 *
 * http://www.cricbuzz.com
 * @author: kshitiz.kapur
 */

package com.rapido.rapidotest.mvp.model;

public class PageInfoModel {

    private int totalResults;

    private int resultsPerPage;

    public int getTotalResults ()
    {
        return totalResults;
    }

    public void setTotalResults (int totalResults)
    {
        this.totalResults = totalResults;
    }

    public int getResultsPerPage ()
    {
        return resultsPerPage;
    }

    public void setResultsPerPage (int resultsPerPage)
    {
        this.resultsPerPage = resultsPerPage;
    }

    @Override
    public String toString()
    {
        return "PageInfo [totalResults = "+totalResults+", resultsPerPage = "+resultsPerPage+"]";
    }
}
