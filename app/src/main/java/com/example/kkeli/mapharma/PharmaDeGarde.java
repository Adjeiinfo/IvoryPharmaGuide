package com.example.kkeli.mapharma;

import java.util.Date;

/**
 * Created by 150482 on 2016/05/20.
 */
public class PharmaDeGarde extends Pharma {
    private int _id;
    private Date _startDate;
    private Date _endDate;

    //these are all inherited from mother class
    private String _name;
    private String _phone_number;
    private String _email;
    private String _postal_address;
    private String _photograph;
    private String _region;
    private String _town;
    private double _distance;

    public PharmaDeGarde(Date start,Date end ){

    }

    //set start date
    public void setStartDate(Date startDate)
    {
        this._startDate = startDate;
    }

    //set end date
    public void setEndDate(Date endDate)
    {
        this._endDate = endDate;
    }

    //get start date
    public Date getStartDate(){
        return this._startDate;
    }

    //get end date
    public Date getEndDate(){
        return this._endDate;
    }
}

